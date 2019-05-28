package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.repository.MatcherRepository

@Service
@Transactional
class DeleteMatcherUseCase(val matcherRepository: MatcherRepository) {

    fun execute(id: String) = matcherRepository.deleteById(id)

    fun execute() = matcherRepository.deleteAll()
}
