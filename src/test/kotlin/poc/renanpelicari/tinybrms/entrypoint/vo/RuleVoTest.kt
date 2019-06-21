package poc.renanpelicari.tinybrms.entrypoint.vo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import poc.renanpelicari.tinybrms.CreationUtils

internal class RuleVoTest {

    private val ruleVo = CreationUtils.createRuleVo()

    @Test
    fun `Should return a Rule domain`() {
        val rule = ruleVo.toDomain()

        assertThat(ruleVo).isEqualToIgnoringGivenFields(rule, rule._id)
    }
}