package poc.renanpelicari.tinybrms.entrypoint.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RuleValidationVoTest {

    private val ruleValidation = RuleValidationVo(attribute = "sexo", values = setOf("M"))
    private val ruleValidation2 = RuleValidationVo(attribute = "peso", values = setOf("55.0"))
    private val listRuleValidation = listOf(ruleValidation, ruleValidation2)

    @Test
    fun `Should return a Pair of a RuleValidationVo list`() {
        val pair = listRuleValidation.toPairList()

        assertEquals(pair.size, listRuleValidation.size)
        assertEquals(pair.first().first, listRuleValidation.first().attribute)
        assertEquals(pair.first().second, listRuleValidation.first().values)
    }

    @Test
    fun `Should return a Pair of a RuleValidationVo`() {
        val pair = ruleValidation.toPair()

        assertEquals(pair.first, ruleValidation.attribute)
        assertEquals(pair.second, ruleValidation.values)
    }

}