package poc.renanpelicari.tinybrms.entrypoint.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import poc.renanpelicari.tinybrms.business.usecase.DeleteMatcherUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllMatchersUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterMatcherUseCase
import poc.renanpelicari.tinybrms.entrypoint.vo.MatcherVo
import poc.renanpelicari.tinybrms.entrypoint.vo.toDomain

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/v1/matcher"])
class MatcherController(val registerMatcherUseCase: RegisterMatcherUseCase,
                        val findAllMatchersUseCase: FindAllMatchersUseCase,
                        val deleteMatcherUseCase: DeleteMatcherUseCase) {

    @PostMapping
    fun register(@RequestBody @Validated matcherRequestVo: MatcherVo) =
            registerMatcherUseCase.execute(
                    matcherRequestVo.toDomain()
            )

    @GetMapping
    fun findAll() = findAllMatchersUseCase.execute()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: String) = deleteMatcherUseCase.execute(id)

    @DeleteMapping
    fun deleteAll() = deleteMatcherUseCase.execute()
}
