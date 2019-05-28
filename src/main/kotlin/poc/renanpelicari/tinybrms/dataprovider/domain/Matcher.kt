package poc.renanpelicari.tinybrms.dataprovider.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import poc.renanpelicari.tinybrms.dataprovider.enums.Expression

@Document
data class Matcher(

        @Id
        val _id: String = "",

        @Field("name")
        val name: String,

        @Field("description")
        val description: String,

        @Field("attribute")
        val attribute: Attribute,

        @Field("expression")
        val expression: Expression,

        @Field("values")
        val values: Set<String>
)
