package poc.renanpelicari.tinybrms.dataprovider.enums


import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class ExpressionTest {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val today = LocalDateTime.now()
    private val yesterday = today.minusDays(1)


    @Test
    fun `Should validate EQUAL_NOT_MATCH_CASE and return true`() {
        val validate = Expression.EQUAL_NOT_MATCH_CASE.validate(setOf("F"), setOf("F"))
        assertTrue(validate)
    }

    @Test
    fun `Should validate EQUAL_NOT_MATCH_CASE and return false`() {
        val validate = Expression.EQUAL_NOT_MATCH_CASE.validate(setOf("F"), setOf("M"))
        assertFalse(validate)
    }

    @Test
    fun `Should validade BETWEEN DOUBLE and return true`() {
        val validate = Expression.BETWEEN_DOUBLE.validate(setOf("10", "50"), setOf("30"))
        val validate1 = Expression.BETWEEN_DOUBLE.validate(setOf("10", "50"), setOf("10"))
        val validate2 = Expression.BETWEEN_DOUBLE.validate(setOf("10", "50"), setOf("50"))

        assertTrue(validate)
        assertTrue(validate1)
        assertTrue(validate2)
    }

    @Test
    fun `Should validade BETWEEN DOUBLE and return false`() {
        val validate = Expression.BETWEEN_DOUBLE.validate(setOf("10", "50"), setOf("1"))
        val validate1 = Expression.BETWEEN_DOUBLE.validate(setOf("10", "50"), setOf("9.9"))
        val validate2 = Expression.BETWEEN_DOUBLE.validate(setOf("10", "50"), setOf("50.1"))

        assertFalse(validate)
        assertFalse(validate1)
        assertFalse(validate2)
    }

    @Test
    fun `Should validade BETWEEN INTEGER and return true`() {
        val validate = Expression.BETWEEN_INTEGER.validate(setOf("10", "50"), setOf("30"))
        val validate1 = Expression.BETWEEN_INTEGER.validate(setOf("10", "50"), setOf("10"))
        val validate2 = Expression.BETWEEN_INTEGER.validate(setOf("10", "50"), setOf("50"))

        assertTrue(validate)
        assertTrue(validate1)
        assertTrue(validate2)
    }

    @Test
    fun `Should validade BETWEEN INTEGER and return false`() {
        val validate = Expression.BETWEEN_INTEGER.validate(setOf("10", "50"), setOf("1"))
        val validate1 = Expression.BETWEEN_INTEGER.validate(setOf("10", "50"), setOf("9"))
        val validate2 = Expression.BETWEEN_INTEGER.validate(setOf("10", "50"), setOf("51"))

        assertFalse(validate)
        assertFalse(validate1)
        assertFalse(validate2)
    }

    @Test
    fun `Should validade CONTAINS and return true`() {
        val validate = Expression.CONTAINS.validate(setOf("M", "F"), setOf("F"))
        val validate2 = Expression.CONTAINS.validate(setOf("10", "20"), setOf("10"))

        assertTrue(validate)
        assertTrue(validate2)

    }

    @Test
    fun `Should validade CONTAINS and return false`() {
        val validate = Expression.CONTAINS.validate(setOf("M", "F"), setOf("G"))
        val validate2 = Expression.CONTAINS.validate(setOf("10", "20"), setOf("15"))

        assertFalse(validate)
        assertFalse(validate2)

    }

    @Test
    fun `Should validade NOT_CONTAINS and return true`() {
        val validate = Expression.NOT_CONTAINS.validate(setOf("M", "F"), setOf("Y"))
        val validate2 = Expression.NOT_CONTAINS.validate(setOf("10", "20"), setOf("15"))

        assertTrue(validate)
        assertTrue(validate2)

    }

    @Test
    fun `Should validade NOT_CONTAINS and return false`() {
        val validate = Expression.NOT_CONTAINS.validate(setOf("M", "F"), setOf("F"))
        val validate2 = Expression.NOT_CONTAINS.validate(setOf("10", "20"), setOf("20"))

        assertFalse(validate)
        assertFalse(validate2)

    }

    @Test
    fun `Should validade CONTAINS_ALL and return true`() {
        val validate = Expression.CONTAINS_ALL.validate(setOf("A", "B", "C", "D"), setOf("A", "B"))
        val validate2 = Expression.CONTAINS_ALL.validate(setOf("10", "20", "30", "40"), setOf("10", "20"))

        assertTrue(validate)
        assertTrue(validate2)

    }

    @Test
    fun `Should validade CONTAINS_ALL and return false`() {
        val validate = Expression.CONTAINS_ALL.validate(setOf("A", "B", "C", "D"), setOf("A", "G"))
        val validate2 = Expression.CONTAINS_ALL.validate(setOf("10", "20", "30", "40", "50"), setOf("20", "35"))

        assertFalse(validate)
        assertFalse(validate2)

    }

    @Test
    fun `Should validade EQUAL and return true`() {
        val validate = Expression.EQUAL.validate(setOf("A"), setOf("A"))
        val validate2 = Expression.EQUAL.validate(setOf("10"), setOf("10"))

        assertTrue(validate)
        assertTrue(validate2)
    }

    @Test
    fun `Should validade EQUAL and return false`() {
        val validate = Expression.EQUAL.validate(setOf("A"), setOf("B"))
        val validate2 = Expression.EQUAL.validate(setOf("10"), setOf("20"))

        assertFalse(validate)
        assertFalse(validate2)
    }

    @Test
    fun `Should validade HAS_IN and return true`() {
        val validate = Expression.HAS_IN.validate(setOf("A", "B", "C"), setOf("A"))
        val validate2 = Expression.HAS_IN.validate(setOf("10", "20", "30"), setOf("10"))

        assertTrue(validate)
        assertTrue(validate2)
    }

    @Test
    fun `Should validade HAS_IN and return false`() {
        val validate = Expression.HAS_IN.validate(setOf("A", "B", "C"), setOf("G"))
        val validate2 = Expression.HAS_IN.validate(setOf("10", "20", "30"), setOf("50"))

        assertFalse(validate)
        assertFalse(validate2)
    }

    @Test
    fun `Should validade MORE_THAN and return true`() {
        val validate = Expression.MORE_THAN.validate(setOf("10"), setOf("30"))

        assertTrue(validate)
    }

    @Test
    fun `Should validade MORE_THAN and return false`() {
        val validate = Expression.MORE_THAN.validate(setOf("80"), setOf("50"))

        assertFalse(validate)
    }

    @Test
    fun `Should validade LESS_THAN and return true`() {
        val validate = Expression.LESS_THAN.validate(setOf("30"), setOf("10"))

        assertTrue(validate)
    }

    @Test
    fun `Should validade LESS_THAN and return false`() {
        val validate = Expression.LESS_THAN.validate(setOf("50"), setOf("80"))

        assertFalse(validate)
    }

    @Test
    fun `Should validade EQUAL_OR_MORE_THAN and return true`() {
        val validate = Expression.EQUAL_OR_MORE_THAN.validate(setOf("10"), setOf("30"))
        val validateEqual = Expression.EQUAL_OR_MORE_THAN.validate(setOf("10"), setOf("10"))

        assertTrue(validate)
        assertTrue(validateEqual)
    }

    @Test
    fun `Should validade EQUAL_OR_MORE_THAN and return false`() {
        val validate = Expression.EQUAL_OR_MORE_THAN.validate(setOf("80"), setOf("50"))

        assertFalse(validate)
    }

    @Test
    fun `Should validade EQUAL_OR_LESS_THAN and return true`() {
        val validate = Expression.EQUAL_OR_LESS_THAN.validate(setOf("30"), setOf("10"))
        val validateEqual = Expression.EQUAL_OR_LESS_THAN.validate(setOf("30"), setOf("30"))

        assertTrue(validate)
        assertTrue(validateEqual)
    }

    @Test
    fun `Should validade EQUAL_OR_LESS_THAN and return false`() {
        val validate = Expression.EQUAL_OR_LESS_THAN.validate(setOf("50"), setOf("80"))

        assertFalse(validate)
    }

    @Test
    fun `Should validade BEFORE_THAN and return true`() {
        val validate = Expression.BEFORE_THAN.validate(setOf(today.format(formatter)),
                setOf(yesterday.format(formatter)))

        assertTrue(validate)
    }

    @Test
    fun `Should validade BEFORE_THAN and return false`() {
        val validate = Expression.BEFORE_THAN.validate(setOf(yesterday.format(formatter)),
                setOf(today.format(formatter)))

        assertFalse(validate)
    }

    @Test
    fun `Should validade AFTER_THAN and return true`() {
        val validate = Expression.AFTER_THAN.validate(setOf(yesterday.format(formatter)),
                setOf(today.format(formatter)))

        assertTrue(validate)
    }

    @Test
    fun `Should validade AFTER_THAN and return false`() {
        val validate = Expression.AFTER_THAN.validate(setOf(today.format(formatter)),
                setOf(yesterday.format(formatter)))

        assertFalse(validate)
    }

    @Test
    fun `Should validade SAME_DAY and return true`() {
        val validate = Expression.SAME_DAY.validate(setOf(today.format(formatter)),
                setOf(today.format(formatter)))

        assertTrue(validate)
    }

    @Test
    fun `Should validade SAME_DAY and return false`() {
        val validate = Expression.SAME_DAY.validate(setOf(today.format(formatter)),
                setOf(yesterday.format(formatter)))

        assertFalse(validate)
    }

    @Test
    fun `Should validade AFTER_DAYS and return true`() {
        val validate = Expression.AFTER_DAYS.validate(setOf(today.format(formatter)), setOf("2"))

        assertTrue(validate)
    }

    @Test
    fun `Should validade AFTER_DAYS and return false`() {
        val afterTomorrow = today.plusDays(2)
        val validate = Expression.AFTER_DAYS.validate(setOf(afterTomorrow.format(formatter)), setOf("1"))

        assertFalse(validate)
    }
}