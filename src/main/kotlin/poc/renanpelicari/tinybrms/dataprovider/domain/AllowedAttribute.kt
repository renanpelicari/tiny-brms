package poc.renanpelicari.tinybrms.dataprovider.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class AllowedAttribute(

        @Id
        val _id: String = "",

        @Field("attribute")
        val attribute: String,

        @Field("description")
        val description: String,

        @Field("values")
        val values: Set<String>
)
