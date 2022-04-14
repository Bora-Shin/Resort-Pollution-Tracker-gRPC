package PoolWaterController;

import java.util.concurrent.TimeUnit;

import PoolWaterController.PoolWaterGrpc.PoolWaterStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		
		// Build a channel - a channel connects the client to the server
		int port = 50052;
		String host = "localhost";
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		PoolWaterStub asyncStub = PoolWaterGrpc.newStub(channel);
		
		// preaparing message to send (request) - client streaming
		StreamObserver<evacuate> responseObserver = new StreamObserver<evacuate>() {

			@Override
			public void onNext(evacuate value) {
				System.out.println("Server: The pool water is being monitored.");
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		StreamObserver<phLevel> requestObserver = asyncStub.stopPoolEntry(responseObserver);
		try {
			
			for(int i = 0; i<3; i++) {
				
				
				// current PH level
				int min = 1;
				int max = 12;
				int currentPhLevel = (int) Math.floor(Math.random()*(max-min+1)+min);
			
				requestObserver.onNext(phLevel.newBuilder().setCurrentPhLevel(currentPhLevel).build());
			}
			
			requestObserver.onCompleted();
			Thread.sleep(5000);
			
		}catch(RuntimeException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

		
		// clean up: shutdown the channel
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
				
	}
}
