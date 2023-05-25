package com.nyang.ourkitty.domain.management

import com.nyang.ourkitty.common.dto.ResultDto
import com.nyang.ourkitty.domain.auth.dto.JwtContextHolder
import com.nyang.ourkitty.domain.management.dto.ManagementCommentRequestDto
import com.nyang.ourkitty.domain.management.dto.ManagementRequestDto
import com.nyang.ourkitty.domain.management.dto.ManagementResponseDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["관리일지 관련 API"])
@RestController
@RequestMapping("/management")
@CrossOrigin(origins = ["*"])
class ManagementController(
    private val managementService: ManagementService,
) {

    /**
     * @param limit Long
     * @param offset Long
     * @param id Long?      : 냥그릇 ID
     * @return ResponseEntity<ResultDto<List<ManagementResponseDto>>>
     */
    @ApiOperation(value = "관리일지 목록 조회")
    @GetMapping
    fun getManagementList(
        @RequestParam limit: Long, @RequestParam offset: Long, @RequestParam("id") dishId: Long?
    ): ResponseEntity<ResultDto<List<ManagementResponseDto>>> {
        val managementList = managementService.getManagementList(
            dishId = dishId,
            locationCode = JwtContextHolder.locationCode!!,
            limit = limit, offset = offset,
        )

        return ResponseEntity.ok(managementList)
    }

    /**
     * @param managementId Long
     * @return ResponseEntity<ResultDto<ManagementResponseDto>>
     */
    @ApiOperation(value = "관리일지 조회")
    @GetMapping("/{managementId}")
    fun getManagement(@PathVariable("managementId") managementId: Long): ResponseEntity<ResultDto<ManagementResponseDto>> {
        return ResponseEntity.ok(managementService.getManagement(managementId))
    }

    /**
     * @param managementRequestDto ManagementRequestDto
     * @return ResponseEntity<ResultDto<ManagementResponseDto>>
     */
    @ApiOperation(value = "관리일지 작성")
    @PostMapping
    fun createManagement(
        managementRequestDto: ManagementRequestDto, @RequestParam(required = false) files: List<String>?
    ): ResponseEntity<ResultDto<ManagementResponseDto>> {
        val managementResponseDto = managementService.createManagement(
            clientId = JwtContextHolder.clientId!!.toLong(),
            locationCode = JwtContextHolder.locationCode!!,
            managementRequestDto = managementRequestDto,
            files = files
        )

        return ResponseEntity.ok(managementResponseDto)
    }

    /**
     * @param managementId Long
     * @param managementRequestDto ManagementRequestDto
     * @return ResponseEntity<ResultDto<ManagementResponseDto>>
     */
    @ApiOperation(value = "관리일지 수정")
    @PutMapping("/{managementId}")
    fun modifyManagement(
        @PathVariable("managementId") managementId: Long,
        managementRequestDto: ManagementRequestDto,
        @RequestParam(required = false) deleteList: List<Long>?,
        @RequestParam(required = false) insertList: List<String>?
    ): ResponseEntity<ResultDto<ManagementResponseDto>> {
        val managementResponseDto = managementService.modifyManagement(
            managementId = managementId,
            managementRequestDto = managementRequestDto,
            deleteList = deleteList, insertList = insertList
        )

        return ResponseEntity.ok(managementResponseDto)
    }

    /**
     * @param managementId Long
     * @return ResponseEntity<ResultDto<Boolean>>
     */
    @ApiOperation(value = "관리일지 삭제")
    @DeleteMapping("/{managementId}")
    fun deleteManagement(@PathVariable("managementId") managementId: Long): ResponseEntity<ResultDto<Boolean>> {
        managementService.deleteManagement(
            clientId = JwtContextHolder.clientId!!.toLong(),
            managementId = managementId,
            userCode = JwtContextHolder.userCode!!,
            locationCode = JwtContextHolder.locationCode!!,
        )

        return ResponseEntity.ok(ResultDto(true))
    }

    /**
     * @param managementId Long
     * @param managementCommentRequestDto ManagementCommentRequestDto
     * @return ResponseEntity<ResultDto<ManagementResponseDto>>
     */
    @ApiOperation(value = "관리일지 댓글 작성")
    @PostMapping("/{managementId}/comment")
    fun createManagementComment(
        @PathVariable("managementId") managementId: Long,
        managementCommentRequestDto: ManagementCommentRequestDto
    ): ResponseEntity<ResultDto<ManagementResponseDto>> {
        val managementResponseDto = managementService.createManagementComment(
            managementId = managementId,
            clientId = JwtContextHolder.clientId!!.toLong(),
            managementCommentRequestDto = managementCommentRequestDto,
        )

        return ResponseEntity.ok(managementResponseDto)
    }

    /**
     * @param managementId Long
     * @param managementCommentId Long
     * @return ResponseEntity<ResultDto<Boolean>>
     */
    @ApiOperation(value = "관리일지 댓글 삭제")
    @DeleteMapping("/{managementId}/comment/{managementCommentId}")
    fun deleteManagementComment(@PathVariable("managementId") managementId: Long, @PathVariable("managementCommentId") managementCommentId: Long): ResponseEntity<ResultDto<Boolean>> {
        val result = managementService.deleteManagementComment(
            clientId = JwtContextHolder.clientId!!.toLong(),
            managementId = managementId,
            userCode = JwtContextHolder.userCode!!,
            locationCode = JwtContextHolder.locationCode!!,
            managementCommentId = managementCommentId,
        )

        return ResponseEntity.ok(result)
    }

}
