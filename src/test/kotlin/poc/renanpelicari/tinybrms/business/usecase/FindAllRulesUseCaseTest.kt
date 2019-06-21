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
import poc.renanpelicari.tinybrms.dataprovider.domain.Rule
import poc.renanpelicari.tinybrms.dataprovider.repository.RuleRepository

@ExtendWith(MockKExtension::class)
internal class FindAllRulesUseCaseTest {

    @InjectMockKs
    lateinit var findAllRulesUseCase: FindAllRulesUseCase

    @MockK
    lateinit var ruleRepository: RuleRepository

    private val list: List<Rule> = listOf(CreationUtils.createRule())

    @BeforeEach
    fun setup() {
        every { ruleRepository.findAll() } returns list
    }

    @Test
    fun `When findAll rules should return a list of Rules`() {
        val listOfRules = findAllRulesUseCase.execute()

        assertThat(list).containsExactlyElementsOf(listOfRules)

        verify(exactly = 1) { ruleRepository.findAll() }
    }

}