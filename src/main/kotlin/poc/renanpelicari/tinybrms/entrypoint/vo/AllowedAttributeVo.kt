package poc.renanpelicari.tinybrms.entrypoint.vo

import poc.renanpelicari.tinybrms.dataprovider.domain.AllowedAttribute

data class AllowedAttributeVo(
        val attribute: String,
        val description: String,
        val values: Set<String>
)

internal fun AllowedAttributeVo.toDomain() = AllowedAttribute(
        attribute = this.attribute,
        description = this.description,
        values = this.values
)
