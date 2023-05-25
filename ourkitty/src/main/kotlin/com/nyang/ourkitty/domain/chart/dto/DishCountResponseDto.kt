package com.nyang.ourkitty.domain.chart.dto

data class DishCountResponseDto(
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    val date: Int,
    val batteryAmount: Int,
    val foodAmount: Int,
    val catCount: Int,
    val noTnrCount: Int,
) {
}