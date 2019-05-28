package poc.renanpelicari.tinybrms.entrypoint.vo

import poc.renanpelicari.tinybrms.dataprovider.domain.Attribute
import poc.renanpelicari.tinybrms.dataprovider.domain.Matcher
import poc.renanpelicari.tinybrms.dataprovider.enums.Expression

data class MatcherVo(
        val name: String,
        val description: String,
        val attribute: Attribute,
        val expression: Expression,
        val values: Set<String>
)

internal fun MatcherVo.toDomain() = Matcher(
        name = this.name,
        description = this.description,
        attribute = this.attribute,
        expression = this.expression,
        values = this.values
)
