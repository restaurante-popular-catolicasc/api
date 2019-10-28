package br.org.restaurantepopular.data_db.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    val id: String,
    val name: String,
    val role: String,
    val code: String,
    val cpf: String,
    @Column
    @CreationTimestamp
    val createdAt: Date? = null
)