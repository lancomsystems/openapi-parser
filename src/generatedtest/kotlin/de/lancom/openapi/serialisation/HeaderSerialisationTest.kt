/*****************************************************************************
**   C A U T I O N                                                          **
**   This file is auto-generated!                                           **
**   If you want to make changes, please see the README.md file.            **
**   Please do not edit this file directly!                                 **
*****************************************************************************/
package de.lancom.openapi.serialisation

import de.lancom.openapi.entity.Header
import de.lancom.openapi.serialisation.testcases.*
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

// hint:6E7C2D8B
class HeaderSerialisationTest : AbstractSerialisationTest() {
    companion object {

        const val emptyYaml = "{}"

        private val testCases: List<TestCase<Header>> = entityFieldTestCases(
            emptyYaml = emptyYaml,
            fieldTests = listOf(
            )
        )

        @JvmStatic
        fun testCases(): Stream<Arguments> {
            return testCases.toArgumentsStream()
        }
    }
}
