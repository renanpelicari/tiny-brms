package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.domain.*
import poc.renanpelicari.tinybrms.dataprovider.repository.RuleRepository

@Service
@Transactional
class RuleValidationUseCase(val ruleRepository: RuleRepository) {

    fun execute(attributesPair: List<Pair<String, Set<String>>>): RulesValidation {

        val rulesForAttributes = ruleRepository.findRulesByMatchersAttributeNameIn(attributesPair.getNamesOnly())

        val rulesValidation = rulesForAttributes.map { rule ->

            val matchersValidation = getMatchersValidationByRule(rule, attributesPair)

            val isValid = matchersValidation.all { it.validation }

            val ruleAllowedValues = when (isValid) {
                true -> rule.allowedValues
                false -> emptySet()
            }

            RuleValidation(
                    _id = rule._id,
                    name = rule.name,
                    description = rule.description,
                    matchersValidation = matchersValidation,
                    validation = isValid,
                    allowedValues = ruleAllowedValues
            )

        }.toSet()

        return RulesValidation(
                rulesValidation = rulesValidation,
                allowedValues = getMatchedAllowedValues(rulesValidation.map { it.allowedValues }.flatten())
        )
    }

    fun getMatchedAllowedValues(allowedAttributes: List<AllowedAttribute>): Set<AllowedAttribute> {

        val mapAttribute = mutableMapOf<String, AllowedAttribute>()

        allowedAttributes.map {

            if (mapAttribute.containsKey(it.attribute)) {

                val allowedAttributeInRule = mapAttribute[it.attribute]

                val matchedValues = allowedAttributeInRule!!.values
                        .filter { value -> it.values.contains(value) }
                        .toSet()

                val allowedAttribute = AllowedAttribute(
                        _id = allowedAttributeInRule._id,
                        attribute = allowedAttributeInRule.attribute,
                        description = allowedAttributeInRule.description,
                        values = matchedValues
                )

                mapAttribute[it.attribute] = allowedAttribute

            } else {
                mapAttribute[it.attribute] = it
            }
        }

        return allowedAttributes.toSet()
    }

    internal fun List<Pair<String, Set<String>>>.getNamesOnly() = map { it.first }

    internal fun getMatchersValidationByRule(rule: Rule, attributesPair: List<Pair<String, Set<String>>>) =
            rule.matchers.map { matcher ->
                getMatchersValidationByMatcher(attributesPair, matcher)
            }.flatten()

    internal fun getMatchersValidationByMatcher(attributesPair: List<Pair<String, Set<String>>>, matcher: Matcher) =
            attributesPair
                    .filter { it.first == matcher.attribute.name }
                    .map {
                        val isValid = matcher.expression.validate(
                                expectedValues = matcher.values,
                                receivedValues = it.second
                        )

                        MatcherValidation(
                                attribute = matcher.attribute,
                                matcher = matcher,
                                validation = isValid
                        )
                    }
}

