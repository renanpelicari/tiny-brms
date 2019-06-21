package poc.renanpelicari.tinybrms.entrypoint.vo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import poc.renanpelicari.tinybrms.CreationUtils

internal class AttributeVoTest {

    private val attributeVo = CreationUtils.createAttributeVo()

    @Test
    fun `Should return an Attribute domain`() {
        val attribute = attributeVo.toDomain()

        assertThat(attributeVo).isEqualToIgnoringGivenFields(attribute, attribute._id)
    }
}