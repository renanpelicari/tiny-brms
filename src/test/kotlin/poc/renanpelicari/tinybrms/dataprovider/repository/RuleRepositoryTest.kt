package poc.renanpelicari.tinybrms.dataprovider.repository

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import poc.renanpelicari.tinybrms.CreationUtils

@DataMongoTest
class RuleRepositoryTest @Autowired constructor(
        val mongoTemplate: MongoTemplate , val ruleRepository: RuleRepository){


    private val rule = CreationUtils.createRule()
    private val ruleMatcherAttributeName = rule.matchers.first().attribute.name
    private val names = listOf(ruleMatcherAttributeName)

    @BeforeEach
    fun setup(){
        mongoTemplate.save(rule)
    }

    @Test
    fun `Should return Rules by Matcher Attribute names`(){
        val rules = ruleRepository.findRulesByMatchersAttributeNameIn(names)

        assertThat(rules.size).isEqualTo(1)
        assertNotNull(rules.first()._id)
        assertEquals(rules.first().name, rule.name )
        assertEquals(rules.first().description, rule.description )
        assertEquals(rules.first().allowedValues, rule.allowedValues )
        assertEquals(rules.first().matchers, rule.matchers )

    }

    @Test
    fun `Should return an empty list of rules`(){
        var wrongNames = listOf("name1", "name2", "name3")
        val rules = ruleRepository.findRulesByMatchersAttributeNameIn(wrongNames)

        assertThat(rules.size).isEqualTo(0)
    }
}