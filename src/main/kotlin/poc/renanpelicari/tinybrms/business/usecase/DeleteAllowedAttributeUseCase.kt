package poc.renanpelicari.tinybrms.business.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.renanpelicari.tinybrms.dataprovider.repository.AllowedAttributeRepository

@Service
@Transactional
class DeleteAllowedAttributeUseCase(val allowedAttributeRepository: AllowedAttributeRepository) {

    fun execute(id: String) = allowedAttributeRepository.deleteById(id)

    fun execute() = allowedAttributeRepository.deleteAll()
}
