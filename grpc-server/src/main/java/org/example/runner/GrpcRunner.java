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
   
//    private void start() throws IOException, InterruptedException 
//    {
//        SERVER.start();
//
//        log.info("GrpcRunner#start - listen port = {}", PORT);
//
//        //SERVER.awaitTermination();
//        
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> 
//        {
//        	log.info("GrpcRunner#addShutdownHook()");
//            
//        	try 
//        	{
//                //SERVER.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//        		SERVER.awaitTermination();
//            } 
//        	catch (InterruptedException e) 
//        	{
//                e.printStackTrace();
//            }
//            
//            log.info("GrpcRunner#stop - gRPC server was stopped");
//        }));
//    }
    
//    private Server server;
//    
//    private void start() throws IOException {
//    	 /* The port on which the server should run */
//    	 int port = 3030;
//    	 server = ServerBuilder.forPort(port).addService(new SampleServiceImpl()).build().start();
//    	 
//    	 log.info("Server started, listening on " + port);
//    	 
//    	 Runtime.getRuntime()
//    	   .addShutdownHook(
//    	     new Thread(
//    	       () -> {
//    	        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
//    	    	   log.info("*** shutting down gRPC server since JVM is shutting down");
//    	        
//    	        GrpcRunner.this.stop();
//    	        
//    	        log.info("*** server shut down");
//    	       }));
//    	}
//    
//    private void stop() {
//        if (server != null) {
//          server.shutdown();
//        }
//      }
    
    public void start() {
    	 Runtime.getRuntime().addShutdownHook(new Thread(SERVER::shutdown));
    	 try {
    		 SERVER.start();
    		 SERVER.awaitTermination();
    	 } catch (IOException e) {
    	  throw new IllegalStateException("Unable to start grpc server.", e);
    	 } catch (InterruptedException e) {
    		 log.error("grpc server was interrupted.", e);
    	  Thread.currentThread().interrupt();
    	 }
    	}
}


