package poc.renanpelicari.tinybrms.entrypoint.vo

data class RuleValidationVo(
        val attribute: String,
        val values: Set<String>
)

internal fun List<RuleValidationVo>.toPairList() = map { it.toPair() }

internal fun RuleValidationVo.toPair() = Pair(this.attribute, this.values)
