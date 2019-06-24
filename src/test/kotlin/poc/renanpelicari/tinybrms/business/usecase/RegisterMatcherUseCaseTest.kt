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
import poc.renanpelicari.tinybrms.dataprovider.repository.MatcherRepository

@ExtendWith(MockKExtension::class)
internal class RegisterMatcherUseCaseTest {

    @InjectMockKs
    lateinit var registerMatchersUseCase: RegisterMatcherUseCase

    @MockK
    lateinit var matcherRepository: MatcherRepository

    private val matcher = CreationUtils.createMatcher()

    @BeforeEach
    fun setup() {
        every { matcherRepository.save(matcher) } returns matcher.copy(_id = "1")
    }

    @Test
    fun `When save a Matcher should return a Matcher `() {
        val matcherSaved = registerMatchersUseCase.execute(matcher)

        assertEquals(matcherSaved._id, "1")
        assertEquals(matcherSaved.name, matcher.name)
        assertEquals(matcherSaved.description, matcher.description)
        assertEquals(matcherSaved.expression, matcher.expression)
        assertEquals(matcherSaved.values, matcher.values)

        verify(exactly = 1) { matcherRepository.save(matcher) }
    }

}