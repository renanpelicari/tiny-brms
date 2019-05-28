package poc.renanpelicari.tinybrms.entrypoint.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import poc.renanpelicari.tinybrms.business.usecase.DeleteAllowedAttributeUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllAllowedAttributesUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterAllowedAttributeUseCase
import poc.renanpelicari.tinybrms.entrypoint.vo.AllowedAttributeVo
import poc.renanpelicari.tinybrms.entrypoint.vo.toDomain

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/v1/allowed/attribute"])
class AllowedAttributeController(val registerRegisterAllowedttributeUseCase: RegisterAllowedAttributeUseCase,
                                 val findAllAllowedAttributesUseCase: FindAllAllowedAttributesUseCase,
                                 val deleteAllowedAttributeUseCase: DeleteAllowedAttributeUseCase) {

    @PostMapping
    fun register(@RequestBody @Validated allowedAttributeVo: AllowedAttributeVo) =
            registerRegisterAllowedttributeUseCase.execute(
                    allowedAttributeVo.toDomain()
            )

    @GetMapping
    fun findAll() = findAllAllowedAttributesUseCase.execute()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: String) = deleteAllowedAttributeUseCase.execute(id)

    @DeleteMapping
    fun deleteAll() = deleteAllowedAttributeUseCase.execute()
}
