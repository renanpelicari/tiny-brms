package poc.renanpelicari.tinybrms.dataprovider.domain

data class MatcherValidation(
        val attribute: Attribute,
        val matcher: Matcher,
        val validation: Boolean
)
