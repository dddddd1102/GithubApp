package com.dd.githubapp.common

import com.dd.githubapp.common.ext.otherwise
import com.dd.githubapp.common.ext.yes
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun testBoolean() {

        val resultOtherwise = false.yes {
            1
        }.otherwise {
            2
        }

        Assert.assertEquals(resultOtherwise, 2)
        val result = true.yes {
            1
        }.otherwise {
            2
        }
        Assert.assertEquals(result, 1)
    }

    fun getABoolean() = false

}