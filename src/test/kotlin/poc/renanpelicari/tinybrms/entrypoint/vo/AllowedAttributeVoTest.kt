package poc.renanpelicari.tinybrms.entrypoint.vo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import poc.renanpelicari.tinybrms.CreationUtils

internal class AllowedAttributeVoTest {

    private val allowedAttributeVo = CreationUtils.createAllowedAttributeVo()

    @Test
    fun `Should return an AllowedAttribute domain`() {
        val allowedAttribute = allowedAttributeVo.toDomain()

        assertThat(allowedAttributeVo).isEqualToIgnoringGivenFields(allowedAttribute, allowedAttribute._id)

    }

}