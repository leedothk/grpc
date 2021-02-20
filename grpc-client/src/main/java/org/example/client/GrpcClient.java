package org.example.client;

import org.springframework.stereotype.Service;


import com.levi.yoon.proto.*;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

//@Slf4j
@Service
public class GrpcClient {
    private static final int PORT = 3030;
    public static final String HOST = "localhost";
    private final SampleServiceGrpc.SampleServiceStub asyncStub = SampleServiceGrpc.newStub(
            ManagedChannelBuilder.forAddress(HOST, PORT)
            .usePlaintext()
            .build()
    );
 
    public String sampleCall() {
        final SampleRequest sampleRequest = SampleRequest.newBuilder()
                .setUserId("levi.yoon")
                .setMessage("grpc request")
                .build();
 
        asyncStub.sampleCall(sampleRequest, new StreamObserver<SampleResponse>() {
            @Override
            public void onNext(SampleResponse value) {
                //log.info("GrpcClient#sampleCall - {}", value);
            	System.out.println("11111111111111");
            }
 
            @Override
            public void onError(Throwable t) {
                //log.error("GrpcClient#sampleCall - onError");
            	System.out.println("22222222222222222222");
            }
 
            @Override
            public void onCompleted() {
                //log.info("GrpcClient#sampleCall - onCompleted");
            	System.out.println("3333333333333333333");
            }
        });
        return "string";
    }
}
