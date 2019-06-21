package poc.renanpelicari.tinybrms.entrypoint.vo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import poc.renanpelicari.tinybrms.CreationUtils

internal class MatcherVoTest {


    private val matcherVo = CreationUtils.createMatcherVo()

    @Test
    fun `Should return a Matcher domain`() {
        val matcher = matcherVo.toDomain()

        assertThat(matcherVo).isEqualToIgnoringGivenFields(matcher, matcher._id)
    }
}