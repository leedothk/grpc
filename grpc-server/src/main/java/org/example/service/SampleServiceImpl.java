package org.example.service;

import org.springframework.stereotype.Service;

import com.example.proto.SampleRequest;
import com.example.proto.SampleResponse;
import com.example.proto.SampleServiceGrpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl extends SampleServiceGrpc.SampleServiceImplBase 
{
    @Override
    public void sampleCall(SampleRequest request, StreamObserver<SampleResponse> responseObserver) 
    {
        log.info("SampleServiceImpl#sampleCall - {}, {}", request.getUserId(), request.getMessage());
        
        SampleResponse sampleResponse = SampleResponse.newBuilder()
        		                                      .setMessage("grpc service response")
        		                                      .build();
 
        responseObserver.onNext(sampleResponse);
        responseObserver.onCompleted();
    }
}
