package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.repository.RuleRepository

@Service
@Transactional
class DeleteRuleUseCase(val ruleRepository: RuleRepository) {

    fun execute(id: String) = ruleRepository.deleteById(id)

    fun execute() = ruleRepository.deleteAll()
}
