package poc.renanpelicari.tinybrms

import com.fasterxml.jackson.databind.ObjectMapper
import poc.renanpelicari.tinybrms.dataprovider.domain.AllowedAttribute
import poc.renanpelicari.tinybrms.dataprovider.domain.Attribute
import poc.renanpelicari.tinybrms.dataprovider.domain.Matcher
import poc.renanpelicari.tinybrms.dataprovider.domain.Rule
import poc.renanpelicari.tinybrms.dataprovider.enums.Expression
import poc.renanpelicari.tinybrms.entrypoint.vo.AllowedAttributeVo
import poc.renanpelicari.tinybrms.entrypoint.vo.AttributeVo
import poc.renanpelicari.tinybrms.entrypoint.vo.MatcherVo
import poc.renanpelicari.tinybrms.entrypoint.vo.RuleVo

open class CreationUtils {
    companion object {
        fun createAllowedAttribute(
                _id: String = "",
                attribute: String = "examesFemininos",
                description: String = "Códigos de exames para o sexo feminino",
                values: Set<String> = setOf("MAMO", "MAMD", "UMAM")

        ) = AllowedAttribute(
                _id = _id,
                attribute = attribute,
                description = description,
                values = values
        )

        fun createAllowedAttributeJson(): String {
            return ObjectMapper().writeValueAsString(createAllowedAttribute())
        }

        fun createAllowedAttributeJson(
                _id: String = "1",
                attribute: String = "examesFemininos",
                description: String = "Códigos de exames para o sexo feminino",
                values: Set<String> = setOf("MAMO", "MAMD", "UMAM")
        ): String {
            return ObjectMapper().writeValueAsString(createAllowedAttribute(_id, attribute, description, values))
        }

        fun createAttribute(
                _id: String = "",
                name: String = "sexo",
                description: String = "Sexo do paciente"

        ) = Attribute(
                _id = _id,
                name = name,
                description = description
        )

        fun createAttributeJson(): String {
            return ObjectMapper().writeValueAsString(createAttribute())
        }

        fun createAttributeJson(_id: String = "1",
                                name: String = "sexo",
                                description: String = "Sexo do paciente"): String {
            return ObjectMapper().writeValueAsString(createAttribute(_id, name, description))
        }

        fun createMatcher(
                _id: String = "",
                name: String = "sexoFem",
                description: String = "Sexo Feminino",
                attribute: Attribute = createAttribute(),
                expression: Expression = Expression.EQUAL_NOT_MATCH_CASE,
                values: Set<String> = setOf("F")
        ) = Matcher(
                _id = _id,
                name = name,
                description = description,
                attribute = attribute,
                expression = expression,
                values = values
        )

        fun createMatcherJson(): String {
            return ObjectMapper().writeValueAsString(createMatcher())
        }

        fun createMatcherJson(_id: String = "1",
                              name: String = "sexoFem",
                              description: String = "Sexo Feminino",
                              attribute: Attribute = createAttribute(),
                              expression: Expression = Expression.EQUAL_NOT_MATCH_CASE,
                              values: Set<String> = setOf("F")): String {
            return ObjectMapper().writeValueAsString(createMatcher(_id, name, description, attribute, expression, values))
        }

        fun createRule(
                _id: String = "",
                name: String = "exameFemininos",
                description: String = "Regras para liberação de exames femininos",
                matchers: Set<Matcher> = setOf(createMatcher()),
                allowedValues: Set<AllowedAttribute> = setOf(createAllowedAttribute())

        ) = Rule(
                _id = _id,
                name = name,
                description = description,
                matchers = matchers,
                allowedValues = allowedValues
        )

        fun createRuleJson(): String {
            return ObjectMapper().writeValueAsString(createRule())
        }

        fun createRuleJson(_id: String = "1",
                           name: String = "exameFemininos",
                           description: String = "Regras para liberação de exames femininos",
                           matchers: Set<Matcher> = setOf(createMatcher()),
                           allowedValues: Set<AllowedAttribute> = setOf(createAllowedAttribute())
        ): String {
            return ObjectMapper().writeValueAsString(createRule(
                    _id, name, description, matchers, allowedValues
            ))
        }

        fun createAllowedAttributeVo(
                attribute: String = "examesFemininos",
                description: String = "Códigos de exames para o sexo feminino",
                values: Set<String> = setOf("MAMO", "MAMD", "UMAM", "HOLT", "CARD", "ERGO", "TGO")
        ) = AllowedAttributeVo(
                attribute = attribute,
                description = description,
                values = values
        )

        fun createAttributeVo(
                name: String = "sexo",
                description: String = "Sexo do paciente"
        ) = AttributeVo(
                name = name,
                description = description
        )

        fun createMatcherVo(
                name: String = "sexoFemi",
                description: String = "Sexo Feminino",
                attribute: Attribute = createAttribute(),
                expression: Expression = Expression.EQUAL_NOT_MATCH_CASE,
                values: Set<String> = setOf("F")
        ) = MatcherVo(
                name = name,
                description = description,
                attribute = attribute,
                expression = expression,
                values = values
        )

        fun createRuleVo(
                name: String = "exameFemininos",
                description: String = "Regras para liberação de exames femininos",
                matchers: Set<Matcher> = setOf(createMatcher()),
                allowedValues: Set<AllowedAttribute> = setOf(createAllowedAttribute())
        ) = RuleVo(
                name = name,
                description = description,
                matchers = matchers,
                allowedValues = allowedValues
        )
    }
}