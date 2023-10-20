package com.epam.kupisinski.jabs.rpcprotocols;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExampleServer {

  private final int port;
  private final Server server;

  public static void main(String[] args) throws IOException, InterruptedException {
    ExampleServer instance = new ExampleServer(50001);
    instance.start();
  }

  public ExampleServer(int port) {
    this.port = port;
    server =
        Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
            .addService(new ExampleServiceImpl())
            .build();
  }

  public void start() throws IOException, InterruptedException {
    log.info("Starting server at port: {}", this.port);
    server.start();
    addShutdown();
    log.info("Server started. Listening for incoming rpc calls ...");
    waitTermination();
  }

  public void stop() throws InterruptedException {
    log.info("Stopping server ...");
    server.awaitTermination(10, TimeUnit.SECONDS);
    log.info("Server is stopped");
  }

  private void waitTermination() throws InterruptedException {
    server.awaitTermination();
  }

  private void addShutdown() {
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  try {
                    log.info("Stopping server due to JVM shutdown");
                    ExampleServer.this.stop();
                  } catch (InterruptedException e) {
                    log.error("Interruption while stopping the server", e);
                  }
                }));
  }
}
