package poc.renanpelicari.tinybrms.entrypoint.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import poc.renanpelicari.tinybrms.business.usecase.DeleteRuleUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllRulesUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterRuleUseCase
import poc.renanpelicari.tinybrms.entrypoint.vo.RuleVo
import poc.renanpelicari.tinybrms.entrypoint.vo.toDomain

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/v1/rule"])
class RuleController(val registerRuleUseCase: RegisterRuleUseCase,
                     val findAllRulesUseCase: FindAllRulesUseCase,
                     val deleteRuleUseCase: DeleteRuleUseCase) {

    @PostMapping
    fun registerRule(@RequestBody @Validated ruleVo: RuleVo) =
            registerRuleUseCase.execute(ruleVo.toDomain())

    @GetMapping
    fun findAll() = findAllRulesUseCase.execute()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: String) = deleteRuleUseCase.execute(id)

    @DeleteMapping
    fun deleteAll() = deleteRuleUseCase.execute()
}
