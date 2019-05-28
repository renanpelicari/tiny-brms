package poc.renanpelicari.tinybrms.dataprovider.enums

import java.time.LocalDate

enum class Expression {
    CONTAINS {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                expectedValues.contains(receivedValues.first())
    },
    NOT_CONTAINS {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                !expectedValues.contains(receivedValues.first())
    },
    CONTAINS_ALL {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                expectedValues.containsAll(receivedValues)
    },
    EQUAL {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                expectedValues.first() == receivedValues.first()
    },
    EQUAL_NOT_MATCH_CASE {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                expectedValues.first().toLowerCase() == receivedValues.first().toLowerCase()
    },
    HAS_IN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.any { expectedValues.contains(it) }
    },
    MORE_THAN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toBigInteger() > expectedValues.first().toBigInteger()
    },
    LESS_THAN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toBigInteger() < expectedValues.first().toBigInteger()
    },
    EQUAL_OR_MORE_THAN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toBigInteger() >= expectedValues.first().toBigInteger()
    },
    EQUAL_OR_LESS_THAN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toBigInteger() <= expectedValues.first().toBigInteger()
    },
    BEFORE_THAN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toLocalDate().isBefore(expectedValues.first().toLocalDate())

    },
    AFTER_THAN {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toLocalDate().isAfter(expectedValues.first().toLocalDate())
    },
    SAME_DAY {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                receivedValues.first().toLocalDate().isEqual(expectedValues.first().toLocalDate())
    },
    AFTER_DAYS {
        override fun validate(expectedValues: Set<String>, receivedValues: Set<String>) =
                LocalDate.now().plusDays(receivedValues.first().toLong()).isAfter(expectedValues.first().toLocalDate())
    };

    abstract fun validate(expectedValues: Set<String>, receivedValues: Set<String>): Boolean
}

private fun String.toLocalDate() = LocalDate.parse(this)
