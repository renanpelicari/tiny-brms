package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.domain.Attribute
import poc.renanpelicari.tinybrms.dataprovider.repository.AttributeRepository

@Service
@Transactional
class FindAllAttributesUseCase(val attributeRepository: AttributeRepository) {

    fun execute(): List<Attribute> = attributeRepository.findAll().sortedBy { attr -> attr.name }
}
