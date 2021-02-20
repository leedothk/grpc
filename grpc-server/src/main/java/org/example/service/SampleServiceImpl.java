package org.example.service;

import org.springframework.stereotype.Service;
import com.levi.yoon.proto.SampleRequest;
import com.levi.yoon.proto.SampleResponse;
import com.levi.yoon.proto.SampleServiceGrpc;
import com.levi.yoon.proto.SampleServiceGrpc.SampleServiceImplBase;

import io.grpc.stub.StreamObserver;

@Service
public class SampleServiceImpl extends SampleServiceGrpc.SampleServiceImplBase {
 
    @Override
    public void sampleCall(SampleRequest request, StreamObserver<SampleResponse> responseObserver) {
        //log.info("SampleServiceImpl#sampleCall - {}, {}", request.getUserId(), request.getMessage());
        
        System.out.println("SampleServiceImpl#sampleCall - " + request.getUserId() + ", " + request.getMessage());
        
        SampleResponse sampleResponse = SampleResponse.newBuilder()
                .setMessage("grpc service response")
                .build();
 
        responseObserver.onNext(sampleResponse);
        responseObserver.onCompleted();
    }
}

