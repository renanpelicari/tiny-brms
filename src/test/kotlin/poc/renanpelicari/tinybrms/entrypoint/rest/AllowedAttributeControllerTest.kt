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
import poc.renanpelicari.tinybrms.business.usecase.DeleteAllowedAttributeUseCase
import poc.renanpelicari.tinybrms.business.usecase.FindAllAllowedAttributesUseCase
import poc.renanpelicari.tinybrms.business.usecase.RegisterAllowedAttributeUseCase

@ExtendWith(SpringExtension::class)
@WebMvcTest(AllowedAttributeController::class)
internal class AllowedAttributeControllerTest(@Autowired val mvc: MockMvc) {

    @MockkBean
    lateinit var registerAllowedAttributeUseCase: RegisterAllowedAttributeUseCase

    @MockkBean
    lateinit var findAllAllowedAttributesUseCase: FindAllAllowedAttributesUseCase

    @MockkBean
    lateinit var deleteAllowedAttributeUseCase: DeleteAllowedAttributeUseCase

    private val allowedAttribute = CreationUtils.createAllowedAttribute()
    private val list = mutableListOf(allowedAttribute)
    private var jsonAllowedAttribute = CreationUtils.createAllowedAttributeJson()

    @Test
    fun `when register an AllowedAttribute should return an AllowedAttribute`() {

        every { registerAllowedAttributeUseCase.execute(allowedAttribute) } returns allowedAttribute


        mvc.perform(post("/api/v1/allowed/attribute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAllowedAttribute))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attribute", equalTo(allowedAttribute.attribute)))
                .andExpect(jsonPath("$.description", equalTo(allowedAttribute.description)))
                .andExpect(jsonPath("$.values", hasSize<Any>(allowedAttribute.values.size)))
                .andExpect(jsonPath("$.values[0]", equalTo(allowedAttribute.values.first())))
                .andExpect(jsonPath("$.values[1]", equalTo(allowedAttribute.values.elementAt(1))))
                .andExpect(jsonPath("$.values[2]", equalTo(allowedAttribute.values.elementAt(2))))

        verify(exactly = 1) { registerAllowedAttributeUseCase.execute(allowedAttribute) }

    }

    @Test
    fun `when findAll AllowedAttribute should return a mutable list of AllowedAttribute`() {

        every { findAllAllowedAttributesUseCase.execute() } returns list

        mvc.perform(get("/api/v1/allowed/attribute"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(1)))
                .andExpect(jsonPath("$[0].attribute", equalTo(allowedAttribute.attribute)))
                .andExpect(jsonPath("$[0].description", equalTo(allowedAttribute.description)))
                .andExpect(jsonPath("$[0].values", hasSize<Any>(allowedAttribute.values.size)))
                .andExpect(jsonPath("$[0].values[0]", equalTo(allowedAttribute.values.first())))
                .andExpect(jsonPath("$[0].values[1]", equalTo(allowedAttribute.values.elementAt(1))))
                .andExpect(jsonPath("$[0].values[2]", equalTo(allowedAttribute.values.elementAt(2))))

        verify(exactly = 1) { findAllAllowedAttributesUseCase.execute() }
    }

    @Test
    fun `when delete an AllowedAttribute by id should return Ok`() {

        every { deleteAllowedAttributeUseCase.execute("1") } answers { nothing }

        mvc.perform(delete("/api/v1/allowed/attribute/1"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteAllowedAttributeUseCase.execute("1") }

    }

    @Test
    fun `when delete all Attributes should return Ok`() {

        every { deleteAllowedAttributeUseCase.execute() } answers { nothing }

        mvc.perform(delete("/api/v1/allowed/attribute"))
                .andExpect(status().isOk())

        verify(exactly = 1) { deleteAllowedAttributeUseCase.execute() }

    }
}