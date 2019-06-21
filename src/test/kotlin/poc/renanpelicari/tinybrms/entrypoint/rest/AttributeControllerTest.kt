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
import poc.renanpelicari.tinybrms.business.usecase.DeleteAttributeUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllAttributesUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterAttributeUseCase

@ExtendWith(SpringExtension::class)
@WebMvcTest(AttributeController::class)
internal class AttributeControllerTest(@Autowired val mvc: MockMvc) {

    @MockkBean
    lateinit var registerAttributeUseCase: RegisterAttributeUseCase

    @MockkBean
    lateinit var findAllAttributesUseCase: FindAllAttributesUseCase

    @MockkBean
    lateinit var deleteAttributeUseCase: DeleteAttributeUseCase

    private val attribute = CreationUtils.createAttribute()
    private val list = listOf(attribute)
    private var jsonAttribute = CreationUtils.createAttributeJson()

    @Test
    fun `when register an Attribute should return an Attribute`() {

        every { registerAttributeUseCase.execute(attribute) } returns attribute.copy(_id = "1")

        mvc.perform(post("/api/v1/attribute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAttribute))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._id", equalTo("1")))
                .andExpect(jsonPath("$.name", equalTo(attribute.name)))
                .andExpect(jsonPath("$.description", equalTo(attribute.description)))

        verify(exactly = 1) { registerAttributeUseCase.execute(attribute) }

    }

    @Test
    fun `when findAll Attributes should return a list of Attributes`() {

        every { findAllAttributesUseCase.execute() } returns list

        mvc.perform(get("/api/v1/attribute"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(1)))
                .andExpect(jsonPath("$[0].name", equalTo(attribute.name)))
                .andExpect(jsonPath("$[0].description", equalTo(attribute.description)))

        verify(exactly = 1) { findAllAttributesUseCase.execute() }
    }

    @Test
    fun `when delete an Attribute by id should return Ok`() {

        every { deleteAttributeUseCase.execute("1") } answers { nothing }

        mvc.perform(delete("/api/v1/attribute/1"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteAttributeUseCase.execute("1") }

    }

    @Test
    fun `when delete all Attributes should return Ok`() {

        every { deleteAttributeUseCase.execute() } answers { nothing }

        mvc.perform(delete("/api/v1/attribute"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteAttributeUseCase.execute() }

    }

}