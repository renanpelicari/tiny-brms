package poc.renanpelicari.tinybrms.dataprovider.domain

data class RulesValidation(
        val rulesValidation: Set<RuleValidation>,
        val allowedValues: Set<AllowedAttribute>
)
