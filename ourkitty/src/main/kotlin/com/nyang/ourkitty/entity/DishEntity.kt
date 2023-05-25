package com.nyang.ourkitty.entity

import com.nyang.ourkitty.common.BatteryState
import com.nyang.ourkitty.common.DishWeight
import javax.persistence.*

@Entity
@Table(name = "dish_table")
class DishEntity(
    var dishName: String,
    var dishAddress: String,
    @Column(unique = true)
    val dishSerialNum: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dishId: Long? = null,

    var dishProfileImagePath: String = "",
    var locationCode: String = "",
    var dishLat: Double = 0.0,
    var dishLong: Double = 0.0,
    var dishWeight: String = DishWeight.PERCENT_100.code,
    var dishBatteryState: String = BatteryState.PERCENT_100.code,
    var dishCatCount: Int = 0,
    var dishTnrCount: Int = 0,
) : BaseEntity() {

    fun updateProfileImage(imagePath: String) {
        this.dishProfileImagePath = imagePath
    }

    fun updateLocationCode(locationCode: String) {
        this.locationCode = locationCode
    }

    fun updateDishWeight(dishWeight: String) {
        this.dishWeight = dishWeight
    }

    fun updateBatteryState(dishBatteryState: String) {
        this.dishBatteryState = dishBatteryState
    }

    fun updateCatCount(catCount: Int, tnrCount: Int) {
        this.dishCatCount = catCount
        this.dishTnrCount = tnrCount
    }

    fun update(param: DishEntity) {
        this.dishName = param.dishName
        this.dishLat = param.dishLat
        this.dishLong = param.dishLong
        this.dishAddress = param.dishAddress
    }

}
