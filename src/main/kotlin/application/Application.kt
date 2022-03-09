package com.gamasoft.anticapizzeria.application

import com.gamasoft.anticapizzeria.eventStore.EventStoreInMemory
import com.gamasoft.anticapizzeria.functional.Invalid
import com.gamasoft.anticapizzeria.writeModel.CmdResult
import com.gamasoft.anticapizzeria.writeModel.Command
import com.gamasoft.anticapizzeria.writeModel.CommandHandler
import com.gamasoft.anticapizzeria.writeModel.DomainError


class Application {

    private val eventStore = EventStoreInMemory()
    private val commandHandler = CommandHandler(eventStore)

    fun List<Command>.processCommands(): List<DomainError> =
        map {
            it.process()
        }
            .filterIsInstance<Invalid<DomainError>>()
            .map { it.err }


    private fun Command.process(): CmdResult {
        return commandHandler.handle(this)
    }
}


