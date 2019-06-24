package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.dataprovider.repository.MatcherRepository

@ExtendWith(MockKExtension::class)
internal class DeleteMatcherUseCaseTest {

    @InjectMockKs
    lateinit var deleteMatcherUseCase: DeleteMatcherUseCase

    @MockK
    lateinit var matcherRepository: MatcherRepository


    @BeforeEach
    fun setup() {
        every { matcherRepository.deleteById("1") } answers { nothing }
        every { matcherRepository.deleteAll() } answers { nothing }
    }

    @Test
    fun `Should delete an Attribute by ID`() {
        deleteMatcherUseCase.execute("1")

        verify(exactly = 1) { matcherRepository.deleteById("1") }
    }

    @Test
    fun `Should delete all Attributes`() {
        deleteMatcherUseCase.execute()

        verify(exactly = 1) { matcherRepository.deleteAll() }
    }
}