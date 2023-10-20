package com.epam.kupisinski.jabs.rpcprotocols

import com.epam.kupisinski.jabs.rpcprotocols.example.ExampleServiceGrpcKt.ExampleServiceStub
import io.grpc.Grpc
import io.grpc.InsecureChannelCredentials
import io.grpc.ManagedChannel
import io.grpc.StatusException
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import java.io.Closeable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class KotlinExampleClient(private val channel: ManagedChannel) : Closeable {

    private val client: ExampleServiceStub = ExampleServiceStub(channel)

    fun revert(msg: String) {
        runBlocking {
            val request = revertRequest { message = msg }
            try {
                val response = client.revert(request);
                println("Response for request '${request.message}' is '${response.revertedMessage}'")
            } catch (e: StatusException) {
                println("RPC failed: ${e.status}")
            }
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}

fun main() {
    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use { dispatcher ->
        val channel = Grpc.newChannelBuilder("localhost:50001", InsecureChannelCredentials.create())
            .executor(dispatcher.asExecutor()).build()
        KotlinExampleClient(channel).use {
            val msg = "test";
            it.revert(msg)
        }
    }
}