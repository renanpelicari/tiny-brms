package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.domain.AllowedAttribute
import poc.renanpelicari.tinybrms.dataprovider.repository.AllowedAttributeRepository

@Service
@Transactional
class RegisterAllowedAttributeUseCase(val allowedAttributeRepository: AllowedAttributeRepository) {

    fun execute(allowedAttribute: AllowedAttribute) = allowedAttributeRepository.save(allowedAttribute)
}
