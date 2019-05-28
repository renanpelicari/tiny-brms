package poc.renanpelicari.tinybrms.entrypoint.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import poc.renanpelicari.tinybrms.business.usecase.RuleValidationUseCase
import poc.renanpelicari.tinybrms.entrypoint.vo.RuleValidationVo
import poc.renanpelicari.tinybrms.entrypoint.vo.toPairList

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/v1/rule/validation"])
class RuleValidationController(val ruleValidationUseCase: RuleValidationUseCase) {

    @GetMapping
    fun findAll(@RequestBody @Validated ruleValidationVos: List<RuleValidationVo>) =
            ruleValidationUseCase.execute(ruleValidationVos.toPairList())
}
