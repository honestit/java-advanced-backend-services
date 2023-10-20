package com.epam.kupisinski.jabs.rpcprotocols;

import com.epam.kupisinski.jabs.rpcprotocols.example.ExampleServiceGrpc;
import com.epam.kupisinski.jabs.rpcprotocols.example.RevertRequest;
import com.epam.kupisinski.jabs.rpcprotocols.example.RevertResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleServiceImpl extends ExampleServiceGrpc.ExampleServiceImplBase {

  @Override
  public void revert(RevertRequest request, StreamObserver<RevertResponse> responseObserver) {
    log.info("Incoming request: {}", request);

    StringBuilder reverted = new StringBuilder();
    request.getMessage().chars().forEach(c -> reverted.insert(0, String.valueOf(c)));

    responseObserver.onNext(
        RevertResponse.newBuilder().setRevertedMessage(reverted.toString()).build());
    responseObserver.onCompleted();
  }
}
