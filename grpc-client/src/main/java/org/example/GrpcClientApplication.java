package org.example;

import org.example.client.GrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class GrpcClientApplication {

    private final GrpcClient grpcClient = new GrpcClient();
    
    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

}
