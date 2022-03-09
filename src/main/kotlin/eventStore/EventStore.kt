package com.gamasoft.anticapizzeria.eventStore

abstract class EventStore {

    inline fun <reified T:Event> getEvents(pk: String): List<T> =
            when (T::class) {
                OrderEvent::class ->  getOrderEvents(pk) as List<T>
                ItemEvent::class -> getItemEvents(pk) as List<T>
                else -> emptyList()
            }

    abstract fun getOrderEvents(pk: String): List<OrderEvent>

    abstract fun getItemEvents(pk: String): List<ItemEvent>

    abstract fun storeEvent(event: Event)
}