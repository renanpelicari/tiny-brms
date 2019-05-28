package poc.renanpelicari.tinybrms.entrypoint.vo

import poc.renanpelicari.tinybrms.dataprovider.domain.Attribute

data class AttributeVo(
        val name: String,
        val description: String
)

internal fun AttributeVo.toDomain() = Attribute(
        name = this.name,
        description = this.description
)
