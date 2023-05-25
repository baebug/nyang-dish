package com.nyang.ourkitty.entity

import com.nyang.ourkitty.common.ReportState
import javax.persistence.*

@Entity
@Table(name = "report_table")
class ReportEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    val client: ClientEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    val dish: DishEntity,

    val reportTitle: String,
    val reportCategory: String,
    val reportContent: String,
    val locationCode: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reportId: Long? = null,

    var reportDescription: String = "",
    var reportState: String = ReportState.답변중.code,

    @OneToMany(mappedBy = "report", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val reportImageList: List<ReportImageEntity> = listOf(),
) : BaseEntity() {

    fun complete(reportDescription: String?) {
        reportDescription?.let { this.reportDescription = it }
        this.reportState = ReportState.답변완료.code
    }

}