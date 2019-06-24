package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.dataprovider.repository.AllowedAttributeRepository

@ExtendWith(MockKExtension::class)
internal class DeleteAttributeUseCaseTest {

    @InjectMockKs
    lateinit var deleteAllowedAttributeUseCase: DeleteAllowedAttributeUseCase

    @MockK
    lateinit var allowedRepository: AllowedAttributeRepository

    @BeforeEach
    fun setUp() {
        every { allowedRepository.deleteById("1") } answers { nothing }
        every { allowedRepository.deleteAll() } answers { nothing }
    }

    @Test
    fun `Should delete an Attribute by ID`() {
        deleteAllowedAttributeUseCase.execute("1")

        verify(exactly = 1) { allowedRepository.deleteById("1") }
    }

    @Test
    fun `Should delete all Attributes`() {
        deleteAllowedAttributeUseCase.execute()

        verify(exactly = 1) { allowedRepository.deleteAll() }
    }
}