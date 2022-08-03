package me.livly.catto.cat

import me.livly.catto.catgeneration.CatGeneration
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

const val CatTableName = "cats"

@Entity
@Table(name = CatTableName)
@SQLDelete(sql = "UPDATE $CatTableName SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
class Cat(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_id_sequence")
    @SequenceGenerator(name = "cat_id_sequence")
    var id: Long,
    var name: String,
    var age: UInt,
    var description: String? = null,

    @ManyToOne()
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    var generation: CatGeneration,

    @CreationTimestamp
    var createdAt: Date,

    @UpdateTimestamp
    var updatedAt: Date,

    var deletedAt: Date? = null,
) {
    companion object {
        fun fromID(id: Long): Cat {
            val now = Date()
            return Cat(
                id = id,
                name = "",
                age = 0u,
                description = "",
                generation = CatGeneration(
                    id = 0,
                    description = "",
                    generation = 0,
                    createdAt = now,
                    updatedAt = now
                ),
                createdAt = now,
                updatedAt = now
            )
        }
    }
}