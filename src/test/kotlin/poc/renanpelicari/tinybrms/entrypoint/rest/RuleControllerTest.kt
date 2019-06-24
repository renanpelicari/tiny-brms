package poc.renanpelicari.tinybrms.entrypoint.rest

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import poc.renanpelicari.tinybrms.CreationUtils
import poc.renanpelicari.tinybrms.business.usecase.DeleteRuleUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllRulesUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterRuleUseCase

@ExtendWith(SpringExtension::class)
@WebMvcTest(RuleController::class)
internal class RuleControllerTest(@Autowired val mvc: MockMvc) {

    @MockkBean
    private lateinit var registerRuleUseCase: RegisterRuleUseCase

    @MockkBean
    private lateinit var findAllRulesUseCase: FindAllRulesUseCase

    @MockkBean
    private lateinit var deleteRuleUseCase: DeleteRuleUseCase

    private val rule = CreationUtils.createRule()
    private val list = mutableListOf(rule)
    private var jsonRule = CreationUtils.createRuleJson()

    @Test
    fun `when register a Rule should return a Rule`() {

        every { registerRuleUseCase.execute(rule) } returns rule

        mvc.perform(post("/api/v1/rule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRule))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(rule.name)))
                .andExpect(jsonPath("$.description", equalTo(rule.description)))

                .andExpect(jsonPath("$.matchers", hasSize<Any>(rule.matchers.size)))
                .andExpect(jsonPath("$.matchers[0].name", equalTo(rule.matchers.first().name)))
                .andExpect(jsonPath("$.matchers[0].description", equalTo(rule.matchers.first().description)))
                .andExpect(jsonPath("$.matchers[0].expression", equalTo(rule.matchers.first().expression.name)))

                .andExpect(jsonPath("$.matchers[0].values", hasSize<Any>(rule.matchers.first().values.size)))
                .andExpect(jsonPath("$.matchers[0].values[0]", equalTo(rule.matchers.first().values.first())))

                .andExpect(jsonPath("$.allowedValues", hasSize<Any>(rule.allowedValues.size)))
                .andExpect(jsonPath("$.allowedValues[0].attribute", equalTo(rule.allowedValues.first().attribute)))
                .andExpect(jsonPath("$.allowedValues[0].description", equalTo(rule.allowedValues.first().description)))

                .andExpect(jsonPath("$.allowedValues[0].values", hasSize<Any>(rule.allowedValues.first().values.size)))
                .andExpect(jsonPath("$.allowedValues[0].values[0]", equalTo(rule.allowedValues.first().values.first())))
                .andExpect(jsonPath("$.allowedValues[0].values[1]", equalTo(rule.allowedValues.first().values.elementAt(1))))
                .andExpect(jsonPath("$.allowedValues[0].values[2]", equalTo(rule.allowedValues.first().values.elementAt(2))))


        verify(exactly = 1) { registerRuleUseCase.execute(rule) }
    }

    @Test
    fun `when findAll Rule should return a mutable list of Rule`() {

        every { findAllRulesUseCase.execute() } returns list

        mvc.perform(get("/api/v1/rule"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(1)))
                .andExpect(jsonPath("$[0].name", equalTo(rule.name)))
                .andExpect(jsonPath("$[0].description", equalTo(rule.description)))

                .andExpect(jsonPath("$[0].matchers", hasSize<Any>(rule.matchers.size)))
                .andExpect(jsonPath("$[0].matchers[0]._id", equalTo(rule.matchers.first()._id)))
                .andExpect(jsonPath("$[0].matchers[0].name", equalTo(rule.matchers.first().name)))
                .andExpect(jsonPath("$[0].matchers[0].description", equalTo(rule.matchers.first().description)))
                .andExpect(jsonPath("$[0].matchers[0].expression", equalTo(rule.matchers.first().expression.name)))

                .andExpect(jsonPath("$[0].matchers[0].values", hasSize<Any>(rule.matchers.first().values.size)))
                .andExpect(jsonPath("$[0].matchers[0].values[0]", equalTo(rule.matchers.first().values.first())))

                .andExpect(jsonPath("$[0].allowedValues", hasSize<Any>(rule.allowedValues.size)))
                .andExpect(jsonPath("$[0].allowedValues[0]._id", equalTo(rule.allowedValues.first()._id)))
                .andExpect(jsonPath("$[0].allowedValues[0].attribute", equalTo(rule.allowedValues.first().attribute)))
                .andExpect(jsonPath("$[0].allowedValues[0].description", equalTo(rule.allowedValues.first().description)))

                .andExpect(jsonPath("$[0].allowedValues[0].values", hasSize<Any>(rule.allowedValues.first().values.size)))
                .andExpect(jsonPath("$[0].allowedValues[0].values[0]", equalTo(rule.allowedValues.first().values.first())))
                .andExpect(jsonPath("$[0].allowedValues[0].values[1]", equalTo(rule.allowedValues.first().values.elementAt(1))))
                .andExpect(jsonPath("$[0].allowedValues[0].values[2]", equalTo(rule.allowedValues.first().values.elementAt(2))))

        verify(exactly = 1) { findAllRulesUseCase.execute() }
    }

    @Test
    fun `when delete an AllowedAttribute by id should return Ok`() {

        every { deleteRuleUseCase.execute("1") } answers { nothing }

        mvc.perform(delete("/api/v1/rule/1"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteRuleUseCase.execute("1") }

    }

    @Test
    fun `when delete all Attributes should return Ok`() {

        every { deleteRuleUseCase.execute() } answers { nothing }

        mvc.perform(delete("/api/v1/rule"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteRuleUseCase.execute() }

    }
}