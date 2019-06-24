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
import poc.renanpelicari.tinybrms.business.usecase.DeleteMatcherUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllMatchersUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterMatcherUseCase

@ExtendWith(SpringExtension::class)
@WebMvcTest(MatcherController::class)
internal class MatcherControllerTest(@Autowired val mvc: MockMvc) {

    @MockkBean
    private lateinit var registerMatcherUseCase: RegisterMatcherUseCase

    @MockkBean
    private lateinit var findAllMatchersUseCase: FindAllMatchersUseCase

    @MockkBean
    private lateinit var deleteMatcherUseCase: DeleteMatcherUseCase

    private val matcher = CreationUtils.createMatcher()
    private val list = mutableListOf(matcher)
    private var jsonMatcher = CreationUtils.createMatcherJson()

    @Test
    fun `when register a Matcher should return a Matcher`() {

        every { registerMatcherUseCase.execute(matcher) } returns matcher

        mvc.perform(post("/api/v1/matcher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMatcher))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(matcher.name)))
                .andExpect(jsonPath("$.description", equalTo(matcher.description)))
                .andExpect(jsonPath("$.expression", equalTo(matcher.expression.name)))
                .andExpect(jsonPath("$.values", hasSize<Any>(matcher.values.size)))
                .andExpect(jsonPath("$.values[0]", equalTo(matcher.values.first())))

        verify(exactly = 1) { registerMatcherUseCase.execute(matcher) }
    }

    @Test
    fun `when findAll Attributes should return a mutable list of Attributes`() {

        every { findAllMatchersUseCase.execute() } returns list

        mvc.perform(get("/api/v1/matcher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(1)))
                .andExpect(jsonPath("$[0].name", equalTo(matcher.name)))
                .andExpect(jsonPath("$[0].description", equalTo(matcher.description)))
                .andExpect(jsonPath("$[0].expression", equalTo(matcher.expression.name)))
                .andExpect(jsonPath("$[0].values", hasSize<Any>(matcher.values.size)))
                .andExpect(jsonPath("$[0].values[0]", equalTo(matcher.values.first())))

        verify(exactly = 1) { findAllMatchersUseCase.execute() }
    }

    @Test
    fun `when delete an Attribute by id should return Ok`() {

        every { deleteMatcherUseCase.execute("1") } answers { nothing }

        mvc.perform(delete("/api/v1/matcher/1"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteMatcherUseCase.execute("1") }

    }

    @Test
    fun `when delete all Attributes should return Ok`() {

        every { deleteMatcherUseCase.execute() } answers { nothing }

        mvc.perform(delete("/api/v1/matcher"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteMatcherUseCase.execute() }

    }
}