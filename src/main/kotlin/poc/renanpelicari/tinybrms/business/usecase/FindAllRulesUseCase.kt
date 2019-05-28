package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.domain.Rule
import poc.renanpelicari.tinybrms.dataprovider.repository.RuleRepository

@Service
@Transactional
class FindAllRulesUseCase(val ruleRepository: RuleRepository) {

    fun execute(): MutableIterable<Rule> = ruleRepository.findAll()
}
