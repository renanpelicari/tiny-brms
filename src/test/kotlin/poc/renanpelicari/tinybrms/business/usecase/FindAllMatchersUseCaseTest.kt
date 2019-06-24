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
import poc.renanpelicari.tinybrms.dataprovider.repository.MatcherRepository

@ExtendWith(MockKExtension::class)
internal class FindAllMatchersUseCaseTest {

    @InjectMockKs
    lateinit var findAllMatchersUseCase: FindAllMatchersUseCase

    @MockK
    lateinit var matcherRepository: MatcherRepository

    var list = listOf(CreationUtils.createMatcher(), CreationUtils.createMatcher())

    @BeforeEach
    fun setup() {
        every { matcherRepository.findAll() } returns list.toList()
    }

    @Test
    fun `When findAll attributes should return a list of Attributes`() {
        val listOfmatchers = findAllMatchersUseCase.execute()

        assertThat(list).containsExactlyElementsOf(listOfmatchers)

        verify(exactly = 1) { matcherRepository.findAll() }
    }
}