package com.radityalabs.moviefinder

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

class SimpleTest : Spek({
    given("a calculator") {
        val x = 5
        val y = 5

        on("get user service on sucess") {
            it("x = $x addition by y = $y should equal to : 9") {
                assertEquals(10, 10)
            }
        }

        on("multiply") {
            it("x = $x addition by y = $y should equal to : 9") {
                assertEquals(25, 25)
            }
        }
    }
})
