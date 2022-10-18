package com.baeldung.grpc.client;

import com.baeldung.grpc.HelloRequest;
import com.baeldung.grpc.HelloResponse;
import com.baeldung.grpc.HelloServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.reflection.v1alpha.ServerReflectionGrpc;
import io.grpc.reflection.v1alpha.ServerReflectionRequest;
import io.grpc.reflection.v1alpha.ServerReflectionResponse;
import io.grpc.stub.StreamObserver;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

        ServerReflectionGrpc.ServerReflectionStub reflectionStub = ServerReflectionGrpc.newStub(channel);
        HelloServiceGrpc.HelloServiceBlockingStub stub
          = HelloServiceGrpc.newBlockingStub(channel);

        StreamObserver<ServerReflectionResponse> streamObserver = new StreamObserver<ServerReflectionResponse>() {
            @Override
            public void onNext(ServerReflectionResponse serverReflectionResponse) {
                System.out.println(serverReflectionResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("complete");
            }
        };

        StreamObserver<ServerReflectionRequest> serverReflectionInfo = reflectionStub.serverReflectionInfo(streamObserver);



        ServerReflectionRequest.Builder req = ServerReflectionRequest.newBuilder();

        serverReflectionInfo.onNext(req.build());
        serverReflectionInfo.onCompleted();


//        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
//            .setFirstName("Baeldung")
//            .setLastName("gRPC")
//            .build());
//
//
//
//        System.out.println("Response received from server:\n" + helloResponse);

        channel.shutdown();
    }
}
