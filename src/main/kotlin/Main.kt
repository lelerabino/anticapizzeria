package com.gamasoft.anticapizzeria

import com.gamasoft.anticapizzeria.application.Application
import com.gamasoft.anticapizzeria.writeModel.*

fun main(args: Array<String>) {

    println("Antica Pizzeria! Best Pizza outside Naples")

    val pn = "0755 123456"
    Application().apply {
        val errors = listOf(
            StartOrder(pn),
            CreateItem("pizza margherita","pizza margherita descr", 5.0),
            AddItem(pn, "pizza margherita", 2),
            AddAddress(pn,"via Roma 15"),
            Confirm(pn)
        ).processCommands()

        println("Errors: $errors")
    }
}


