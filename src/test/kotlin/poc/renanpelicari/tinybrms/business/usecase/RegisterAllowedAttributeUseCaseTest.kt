package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.CreationUtils
import poc.renanpelicari.tinybrms.dataprovider.repository.AllowedAttributeRepository

@ExtendWith(MockKExtension::class)
internal class RegisterAllowedAttributeUseCaseTest {

    @InjectMockKs
    lateinit var registerAllowedAttributeUseCase: RegisterAllowedAttributeUseCase

    @MockK
    lateinit var allowedAttributeRepository: AllowedAttributeRepository

    private val allowedAttribute = CreationUtils.createAllowedAttribute()

    @BeforeEach
    fun setup() {
        every { allowedAttributeRepository.save(allowedAttribute) } returns allowedAttribute
    }


    @Test
    fun `When save an AllowedAttribute should return an AllowedAttribute `() {
        val allowedAttributeSaved = registerAllowedAttributeUseCase.execute(allowedAttribute)

        assertEquals(allowedAttributeSaved._id, allowedAttribute._id)
        assertEquals(allowedAttributeSaved.attribute, allowedAttribute.attribute)
        assertEquals(allowedAttributeSaved.description, allowedAttribute.description)
        assertEquals(allowedAttributeSaved.values, allowedAttribute.values)

        verify(exactly = 1) { allowedAttributeRepository.save(allowedAttribute) }
    }
}