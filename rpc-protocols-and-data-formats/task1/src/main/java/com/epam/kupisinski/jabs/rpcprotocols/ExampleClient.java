package com.epam.kupisinski.jabs.rpcprotocols;

import com.epam.kupisinski.jabs.rpcprotocols.example.ExampleServiceGrpc;
import com.epam.kupisinski.jabs.rpcprotocols.example.RevertRequest;
import com.epam.kupisinski.jabs.rpcprotocols.example.RevertResponse;
import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ExampleClient {

  private final ExampleServiceGrpc.ExampleServiceBlockingStub client;

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel =
        Grpc.newChannelBuilder("localhost:50001", InsecureChannelCredentials.create()).build();
    try {
      ExampleClient instance = new ExampleClient(channel);

      String message = "test";
      String revertedMessage = instance.revert(message);
      System.out.printf("Reverted message for '%s' is '%s'%n", message, revertedMessage);
    } finally {
      channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }
  }

  public ExampleClient(Channel channel) {
    client = ExampleServiceGrpc.newBlockingStub(channel);
  }

  public String revert(String message) {
    log.debug("Sending request to revert '{}'", message);
    RevertResponse response = client.revert(RevertRequest.newBuilder().setMessage(message).build());
    log.debug("Received response: {}", response);
    return response.getRevertedMessage();
  }
}
