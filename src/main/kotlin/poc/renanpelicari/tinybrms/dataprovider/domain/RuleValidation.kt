package poc.renanpelicari.tinybrms.dataprovider.domain

data class RuleValidation(
        val _id: String,
        val name: String,
        val description: String,
        val matchersValidation: List<MatcherValidation>,
        val validation: Boolean,
        val allowedValues: Set<AllowedAttribute>
)
