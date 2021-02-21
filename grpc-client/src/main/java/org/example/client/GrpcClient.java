package org.example.client;

import org.springframework.stereotype.Service;

import com.example.proto.SampleRequest;
import com.example.proto.SampleResponse;
import com.example.proto.SampleServiceGrpc;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrpcClient 
{
    private static final int    PORT = 3030       ;
    public  static final String HOST = "localhost";
    
    private final SampleServiceGrpc.SampleServiceStub asyncStub = SampleServiceGrpc.newStub(ManagedChannelBuilder.forAddress(HOST, PORT)
                                                                                                                 .usePlaintext()
                                                                                                                 .build()
    );
 
    public String sampleCall() 
    {
        final SampleRequest sampleRequest = SampleRequest.newBuilder()
        		                                         .setUserId("lhk")
                                                         .setMessage("grpc request")
                                                         .build();
 
        asyncStub.sampleCall(sampleRequest, new StreamObserver<SampleResponse>() 
        {
            @Override
            public void onNext(SampleResponse value) 
            {
                log.info("GrpcClient#sampleCall - {}", value);
            }
 
            @Override
            public void onError(Throwable t) 
            {
                log.error("GrpcClient#sampleCall - onError");
            }
 
            @Override
            public void onCompleted() 
            {
                log.info("GrpcClient#sampleCall - onCompleted");
            }
        });
        
        return "string";
    }
}
