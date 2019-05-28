package poc.renanpelicari.tinybrms.entrypoint.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import poc.renanpelicari.tinybrms.business.usecase.DeleteAttributeUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllAttributesUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterAttributeUseCase
import poc.renanpelicari.tinybrms.entrypoint.vo.AttributeVo
import poc.renanpelicari.tinybrms.entrypoint.vo.toDomain

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/v1/attribute"])
class AttributeController(val registerRegisterAttributeUseCase: RegisterAttributeUseCase,
                          val findAllAttributesUseCase: FindAllAttributesUseCase,
                          val deleteAttributeUseCase: DeleteAttributeUseCase) {

    @PostMapping
    fun register(@RequestBody @Validated attributeVo: AttributeVo) =
            registerRegisterAttributeUseCase.execute(
                    attributeVo.toDomain()
            )

    @GetMapping
    fun findAll() = findAllAttributesUseCase.execute()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: String) = deleteAttributeUseCase.execute(id)

    @DeleteMapping
    fun deleteAll() = deleteAttributeUseCase.execute()
}
