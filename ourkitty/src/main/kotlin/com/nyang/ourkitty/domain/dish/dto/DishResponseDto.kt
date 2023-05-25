package com.nyang.ourkitty.domain.dish.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.nyang.ourkitty.common.BatteryState
import com.nyang.ourkitty.common.DishWeight
import com.nyang.ourkitty.entity.DishEntity
import java.time.LocalDateTime

data class DishResponseDto(
    val dishId: Long,
    val dishName: String,
    val dishProfileImagePath: String,
    val dishLat: Double,
    val dishLong: Double,
    val dishAddress: String,
    val locationCode: String,
    val dishSerialNum: String,
    val dishWeight: Int,
    val dishBatteryState: Int,
    val dishCatCount: Int,
    val dishTnrCount: Int,
    val isDeleted: Boolean,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val createdDate: LocalDateTime,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val updatedDate: LocalDateTime,
) {

    companion object {
        fun of(dish: DishEntity): DishResponseDto {
            return DishResponseDto(
                dishId = dish.dishId!!,
                dishName = dish.dishName,
                dishProfileImagePath = dish.dishProfileImagePath,
                dishLat = dish.dishLat,
                dishLong = dish.dishLong,
                dishAddress = dish.dishAddress,
                locationCode = dish.locationCode,
                dishSerialNum = dish.dishSerialNum,
                dishWeight = DishWeight.values().first { it.code == dish.dishWeight }.amount,
                dishBatteryState = BatteryState.values().first { it.code == dish.dishBatteryState }.amount,
                dishCatCount = dish.dishCatCount,
                dishTnrCount = dish.dishTnrCount,
                isDeleted = dish.isDeleted,
                createdDate = dish.createdDate,
                updatedDate = dish.updatedDate,
            )
        }
    }

}