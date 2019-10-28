package br.org.restaurantepopular.data_db.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    val id: String,
    val name: String,
    val description: String,
    @Column
    @CreationTimestamp
    val createdAt: Date? = null
)

