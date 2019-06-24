package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.CreationUtils
import poc.renanpelicari.tinybrms.dataprovider.repository.AllowedAttributeRepository

@ExtendWith(MockKExtension::class)
internal class FindAllAllowedAttributesUseCaseTest {

    @InjectMockKs
    lateinit var findAllAllowedAttributesUseCase: FindAllAllowedAttributesUseCase

    @MockK
    lateinit var allowedAttributeRepository: AllowedAttributeRepository

    private val allowedAtributes = CreationUtils.createAllowedAttribute()
    val list = listOf(allowedAtributes)

    @BeforeEach
    fun setup() {
        every { allowedAttributeRepository.findAll() } returns list.toList()
    }

    @Test
    fun `When findAll AllowedAttributes should return a list of AllowedAttributes`() {
        val listOfAllowedAttributes = findAllAllowedAttributesUseCase.execute()

        assertThat(list).containsExactlyElementsOf(listOfAllowedAttributes)

        verify(exactly = 1) { allowedAttributeRepository.findAll() }
    }

}