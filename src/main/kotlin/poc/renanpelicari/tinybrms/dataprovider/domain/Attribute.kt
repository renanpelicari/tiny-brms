package poc.renanpelicari.tinybrms.dataprovider.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Attribute(

        @Id
        val _id: String = "",

        @Field("name")
        val name: String,

        @Field("description")
        val description: String
)
