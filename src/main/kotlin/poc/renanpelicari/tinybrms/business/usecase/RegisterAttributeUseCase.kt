package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.domain.Attribute
import poc.renanpelicari.tinybrms.dataprovider.repository.AttributeRepository

@Service
@Transactional
class RegisterAttributeUseCase(val attributeRepository: AttributeRepository) {

    fun execute(attribute: Attribute) = attributeRepository.save(attribute)
}
