package org.example.controller;

import org.example.client.GrpcClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {
	private final GrpcClient grpcClient = new GrpcClient();
	
	@RequestMapping(value = "/home")
    public String test() {
        return grpcClient.sampleCall();
    }
}
