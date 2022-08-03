package me.livly.catto.catgeneration

import me.livly.catto.cat.Cat
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

const val CatGenerationTableName = "cat_generations"

@Entity
@Table(name = CatGenerationTableName)
@SQLDelete(sql = "UPDATE $CatGenerationTableName SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
class CatGeneration(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_generation_id_sequence")
    @SequenceGenerator(name = "cat_generation_id_sequence")
    var id: Long,
    var generation: Long,
    var description: String? = null,

    @OneToOne()
    @JoinColumn(name = "father_cat_id", referencedColumnName = "id")
    var fatherCat: Cat? = null,

    @OneToOne()
    @JoinColumn(name = "mother_cat_id", referencedColumnName = "id")
    var motherCat: Cat? = null,

    @CreationTimestamp
    var createdAt: Date,

    @UpdateTimestamp
    var updatedAt: Date,

    var deletedAt: Date? = null,
)
