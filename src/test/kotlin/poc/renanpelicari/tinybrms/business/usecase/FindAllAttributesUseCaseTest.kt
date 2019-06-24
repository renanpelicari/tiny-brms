package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.CreationUtils
import poc.renanpelicari.tinybrms.dataprovider.repository.AttributeRepository

@ExtendWith(MockKExtension::class)
internal class FindAllAttributesUseCaseTest {

    @InjectMockKs
    lateinit var findAllAttributesUseCase: FindAllAttributesUseCase

    @MockK
    lateinit var attributeRepository: AttributeRepository

    var list = listOf(CreationUtils.createAttribute(), CreationUtils.createAttribute())

    @BeforeAll
    fun setup() {
        every { attributeRepository.findAll() } returns list
    }

    @Test
    fun `When find all Attributes should return a list of Attributes`() {
        val listOfattributes = findAllAttributesUseCase.execute()

        assertThat(list.sortedBy { attr -> attr.name }).containsExactlyElementsOf(listOfattributes)

        verify(exactly = 1) { attributeRepository.findAll() }
    }

}