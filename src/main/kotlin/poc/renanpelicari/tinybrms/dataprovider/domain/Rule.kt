package poc.renanpelicari.tinybrms.dataprovider.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Rule(

        @Id
        val _id: String = "",

        @Field("name")
        val name: String,

        @Field("description")
        val description: String,

        @Field("matchers")
        val matchers: Set<Matcher>,

        @Field("allowedValues")
        val allowedValues: Set<AllowedAttribute>
)
