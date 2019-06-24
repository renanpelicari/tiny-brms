package poc.renanpelicari.tinybrms.business.usecase

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import poc.renanpelicari.tinybrms.dataprovider.repository.RuleRepository

@ExtendWith(MockKExtension::class)
internal class DeleteRuleUseCaseTest {

    @InjectMockKs
    lateinit var deleteRuleUseCase: DeleteRuleUseCase

    @MockK
    lateinit var ruleRepository: RuleRepository


    @BeforeEach
    fun setup() {
        every { ruleRepository.deleteById("1") } answers { nothing }
        every { ruleRepository.deleteAll() } answers { nothing }
    }

    @Test
    fun `Should delete an Attribute by ID`() {
        deleteRuleUseCase.execute("1")

        verify(exactly = 1) { ruleRepository.deleteById("1") }
    }

    @Test
    fun `Should delete all Attributes`() {
        deleteRuleUseCase.execute()

        verify(exactly = 1) { ruleRepository.deleteAll() }
    }
}