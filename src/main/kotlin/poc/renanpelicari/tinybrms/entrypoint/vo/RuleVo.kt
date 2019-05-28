package poc.renanpelicari.tinybrms.entrypoint.vo

import poc.renanpelicari.tinybrms.dataprovider.domain.AllowedAttribute
import poc.renanpelicari.tinybrms.dataprovider.domain.Matcher
import poc.renanpelicari.tinybrms.dataprovider.domain.Rule


data class RuleVo(
        val name: String,
        val description: String,
        val matchers: Set<Matcher>,
        val allowedValues: Set<AllowedAttribute>
)

internal fun RuleVo.toDomain() = Rule(
        name = this.name,
        description = this.description,
        matchers = this.matchers,
        allowedValues = this.allowedValues
)
