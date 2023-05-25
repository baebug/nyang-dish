package com.nyang.ourkitty.domain.dish

import com.nyang.ourkitty.common.AwsS3ImageUploader
import com.nyang.ourkitty.common.DishWeight
import com.nyang.ourkitty.common.dto.ResultDto
import com.nyang.ourkitty.domain.ai.dto.CatCountRequestDto
import com.nyang.ourkitty.domain.ai.dto.ImageRequestDto
import com.nyang.ourkitty.domain.dish.dto.DishImageResponseDto
import com.nyang.ourkitty.domain.dish.dto.DishListResultDto
import com.nyang.ourkitty.domain.dish.dto.DishRequestDto
import com.nyang.ourkitty.domain.dish.dto.DishResponseDto
import com.nyang.ourkitty.domain.dish.repository.*
import com.nyang.ourkitty.entity.DishEntity
import com.nyang.ourkitty.entity.DishImageEntity
import com.nyang.ourkitty.entity.DishTotalLogEntity
import com.nyang.ourkitty.entity.DishWeightLogEntity
import com.nyang.ourkitty.exception.CustomException
import com.nyang.ourkitty.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class DishService(
    private val dishRepository: DishRepository,
    private val dishQuerydslRepository: DishQuerydslRepository,
    private val dishWeightLogRepository: DishWeightLogRepository,
    private val dishCountLogRepository: DishCountLogRepository,
    private val dishTotalLogRepository: DishTotalLogRepository,
    private val dishImageRepository: DishImageRepository,
    private val dishImageQuerydslRepository: DishImageQuerydslRepository,
    private val imageUploader: AwsS3ImageUploader,
) {

    fun getDishList(locationCode: String): DishListResultDto {
        val dishList = dishQuerydslRepository.getDishList(locationCode)
        val centerPos = dishQuerydslRepository.getCenterPos(locationCode)

        val dishResponseDtoList = dishList
            .map(DishResponseDto::of)

        return DishListResultDto(
            data = dishResponseDtoList,
            centerLat = centerPos.lat,
            centerLong = centerPos.long,
        )
    }

    fun getMyDishList(clientId: Long): DishListResultDto {
        return dishQuerydslRepository.getMyDishList(clientId)
    }

    @Transactional
    fun createDish(
        locationCode: String,
        dishRequestDto: DishRequestDto,
        file: MultipartFile?
    ): ResultDto<DishResponseDto> {
        val dish = dishRequestDto.toEntity()

        if (dishRepository.existsByDishSerialNum(dish.dishSerialNum)) {
            throw CustomException(ErrorCode.DUPLICATE_SERIAL_NUM)
        }

        dish.updateLocationCode(locationCode)
        dish.updateDishWeight(DishWeight.PERCENT_100.code)

        if (file != null) {
            val imagePath = imageUploader.uploadImage(file)
            dish.updateProfileImage(imagePath)
        }

        return ResultDto(
            data = DishResponseDto.of(
                dishRepository.save(dish)
            )
        )
    }

    @Transactional
    fun createDishImage(imageRequestDto: ImageRequestDto): ResultDto<Boolean> {
        val dish = getDishBySerialNum(imageRequestDto.dishSerialNum)

        val dishImage = DishImageEntity(
            dish = dish,
            imagePath = imageRequestDto.imagePath,
        )

        dishImageRepository.save(dishImage)

        return ResultDto(
            data = true,
        )
    }

    fun getDish(dishId: Long): ResultDto<DishResponseDto> {

        return ResultDto(
            data = DishResponseDto.of(
                getDishById(dishId)
            )
        )
    }

    @Transactional
    fun modifyDish(dishId: Long, dishRequestDto: DishRequestDto, file: MultipartFile?): ResultDto<DishResponseDto> {
        val dish = getDishById(dishId)
        val updateParam = dishRequestDto.toEntity()

        if (file != null) {
            val imagePath = imageUploader.uploadImage(file)
            dish.updateProfileImage(imagePath)
        }

        dish.update(updateParam)

        return ResultDto(
            data = DishResponseDto.of(
                dishRepository.save(dish)
            )
        )
    }

    @Transactional
    fun updateDishWeight(dishSerialNum: String, dishWeight: String, dishBatteryState: String): ResultDto<Boolean> {
        val dish = getDishBySerialNum(dishSerialNum)
        // 새로 들어온 배터리 정보(0100005) - 이전 배터리 정보(0100004) == 1 --> 배터리 상승 --> noise 발생
        if (dishBatteryState.toInt() - dish.dishBatteryState.toInt() != 1) {
            dish.updateBatteryState(dishBatteryState)
        }
        dish.updateDishWeight(dishWeight)
        dishRepository.save(dish)

        val dishWeightLog = DishWeightLogEntity(
            dish = dish,
            dishWeight = dishWeight,
        )

        dishWeightLogRepository.save(dishWeightLog)

        return ResultDto(
            data = true,
        )
    }

    @Transactional
    fun deleteDish(dishId: Long): ResultDto<Boolean> {
        val dish = getDishById(dishId)
        dish.delete()
        dishRepository.save(dish)

        return ResultDto(
            data = true,
        )
    }

    @Transactional
    fun updateDishCatCount(catCountRequestDto: CatCountRequestDto): ResultDto<Boolean> {
        val dish = getDishBySerialNum(catCountRequestDto.dishSerialNum)
            .apply { updateCatCount(catCountRequestDto.catCount, catCountRequestDto.tnrCount) }

        val log = dishTotalLogRepository.findByDishAndDate(dish, catCountRequestDto.date)
            ?.apply { updateCount(catCountRequestDto.catCount, catCountRequestDto.tnrCount) }
            ?: DishTotalLogEntity(
                dish = dish,
                date = catCountRequestDto.date,
            )

        dishRepository.save(dish)
        dishTotalLogRepository.save(log)

        return ResultDto(
            data = true,
        )
    }

    @Transactional
    fun writeTotalLog() {
        dishQuerydslRepository.getDishList()
            .map {
                dishTotalLogRepository.findByDishAndDate(it, LocalDate.now()) ?: DishTotalLogEntity(it)
            }
            .forEach(dishTotalLogRepository::save)
    }

    fun getDishImageList(dishSerialNum: String, date: LocalDate): ResultDto<List<DishImageResponseDto>> {
        val dishImageDtoList = dishImageQuerydslRepository.getDishImageList(dishSerialNum, date)
            .map(DishImageResponseDto::of)

        return ResultDto(
            data = dishImageDtoList,
            totalCount = dishImageDtoList.size.toLong()
        )
    }

    fun getVisitCatList(dishId: Long): ResultDto<List<DishImageResponseDto>> {
        val dishImageDtoList = dishImageQuerydslRepository.getVisitCatList(dishId)
            .map(DishImageResponseDto::of)

        return ResultDto(
            data = dishImageDtoList,
            totalCount = dishImageDtoList.size.toLong()
        )
    }

    private fun getDishById(dishId: Long): DishEntity {
        return dishQuerydslRepository.getDishById(dishId) ?: throw CustomException(ErrorCode.NOT_FOUND_DISH)
    }

    private fun getDishBySerialNum(dishSerialNum: String): DishEntity {
        return dishQuerydslRepository.getDishBySerialNum(dishSerialNum)
            ?: throw CustomException(ErrorCode.NOT_FOUND_DISH)
    }

}