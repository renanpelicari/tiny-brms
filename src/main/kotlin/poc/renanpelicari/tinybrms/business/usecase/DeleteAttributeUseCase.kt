package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.repository.AttributeRepository

@Service
@Transactional
class DeleteAttributeUseCase(val attributeRepository: AttributeRepository) {

    fun execute(id: String) = attributeRepository.deleteById(id)

    fun execute() = attributeRepository.deleteAll()
}
