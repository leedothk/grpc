package org.example.runner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.example.service.SampleServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GrpcRunner implements ApplicationRunner
{
    private static final int    PORT   = 3030;
    private static final Server SERVER = ServerBuilder.forPort(PORT)
                                                      .addService(new SampleServiceImpl())
                                                      .build();
 
    @Override
    public void run(ApplicationArguments args) throws Exception 
    {
        //SERVER.start();
        //SERVER.awaitTermination();
    	
    	this.start();
    }
   
    private void start() throws IOException, InterruptedException 
    {
        SERVER.start();

        log.info("GrpcRunner#start - listen port = {}", PORT);

        SERVER.awaitTermination();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> 
        {
        	log.info("GrpcRunner#stop - Stopping gRPC server");
            
        	try 
        	{
                SERVER.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } 
        	catch (InterruptedException e) 
        	{
                e.printStackTrace();
            }
            
            log.info("GrpcRunner#stop - gRPC server was stopped");
        }));
    }
}

