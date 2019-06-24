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
import poc.renanpelicari.tinybrms.dataprovider.repository.RuleRepository

@ExtendWith(MockKExtension::class)
internal class RegisterRuleUseCaseTest {

    @InjectMockKs
    lateinit var registerRuleUseCase: RegisterRuleUseCase

    @MockK
    lateinit var ruleRepository: RuleRepository


    private val rule = CreationUtils.createRule()

    @BeforeEach
    fun setup() {
        every { ruleRepository.save(rule) } returns rule.copy(_id = "1")
    }

    @Test
    fun `When save a Rule should return a Rule `() {
        val ruleSaved = registerRuleUseCase.execute(rule)

        assertEquals(ruleSaved._id, "1")
        assertEquals(ruleSaved.description, rule.description)
        assertEquals(ruleSaved.matchers, rule.matchers)
        assertEquals(ruleSaved.allowedValues, rule.allowedValues)

        verify(exactly = 1) { ruleRepository.save(rule) }
    }
}