package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.CreationUtils
import poc.renanpelicari.tinybrms.dataprovider.repository.AttributeRepository

@ExtendWith(MockKExtension::class)
internal class RegisterAttributeUseCaseTest {

    @InjectMockKs
    lateinit var registerAttributeUseCase: RegisterAttributeUseCase

    @MockK
    lateinit var attributeRepository: AttributeRepository

    private val attribute = CreationUtils.createAttribute()

    @BeforeAll
    fun setup() {
        every { attributeRepository.save(attribute) } returns attribute.copy(_id = "1")
    }

    @Test
    fun `When save an Attribute should return an Attribute `() {
        val attributeSaved = registerAttributeUseCase.execute(attribute)

        assertEquals(attributeSaved._id, "1")
        assertEquals(attributeSaved.name, attribute.name)
        assertEquals(attributeSaved.description, attribute.description)

        verify(exactly = 1) { attributeRepository.save(attribute) }
    }

}