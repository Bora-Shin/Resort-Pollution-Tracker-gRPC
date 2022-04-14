package AirVentilator;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import AirVentilator.AirVentilationGrpc.AirVentilationBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;


public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		
		// Build a channel - a channel connects the client to the server
		int port = 50051;
		String host = "localhost";
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		AirVentilationBlockingStub blockingStub = AirVentilationGrpc.newBlockingStub(channel);
		
		// preparing message to send (request) - server streaming
		everyHour frequency = everyHour.newBuilder().setHours(3).build();
		
		// retrieving reply from service (response) - server steaming

		

		try {
			Iterator<ventilator> responses = blockingStub.airVentilator(frequency);
			while(responses.hasNext()) {
				ventilator individualResponse = responses.next();
				System.out.println(individualResponse.getStartVentilator());
			}
		}catch(StatusRuntimeException e) {
			e.printStackTrace();
		}


		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Clean up : Shutdown the channel
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

}
