package com.gamasoft.anticapizzeria.eventStore

class EventStoreInMemory : EventStore() {
    private val orderEventCache = mutableMapOf<String, List<OrderEvent>>()
    private val itemEventCache = mutableMapOf<String, List<ItemEvent>>()

    override fun getItemEvents(pk: String) = itemEventCache.getOrDefault(pk, emptyList())

    override fun getOrderEvents(pk: String) = orderEventCache.getOrDefault(pk, emptyList())

    override fun storeEvent(event: Event) {
        when (event) {
            is ItemEvent -> itemEventCache.compute(event.key()) { _, el -> (el ?: emptyList()).plus(event) }
            is OrderEvent -> orderEventCache.compute(event.key()) { _, el -> (el ?: emptyList()).plus(event) }
        }
    }
}


