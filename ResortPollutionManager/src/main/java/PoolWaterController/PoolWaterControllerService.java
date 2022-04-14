package PoolWaterController;

import java.io.IOException;

import PoolWaterController.PoolWaterGrpc.PoolWaterImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class PoolWaterControllerService extends PoolWaterImplBase{

	public static void main(String[] args) throws IOException, InterruptedException {
		
		PoolWaterControllerService service2 = new PoolWaterControllerService();
		
		int port = 50052;

		Server server = ServerBuilder.forPort(port)
				.addService(service2)
				.build()
				.start();

		System.out.println("Pool Water Monitoring started, listening on " + port);

		server.awaitTermination();
	}

	
	// client streaming rpc
	@Override
	public StreamObserver<phLevel> stopPoolEntry (StreamObserver<evacuate> responseObserver){
		
		return new StreamObserver<phLevel>() {

			// current PH level
			int currentPhLevel = 9;
			String evacuateMsg = "!!! ALERT!!! PH level of the pool water is: " + currentPhLevel + ". ";
			
			@Override
			public void onNext(phLevel value) {
					
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				
				evacuateMsg += "The Ph level is ";
				
				if(currentPhLevel > 8) {
					evacuateMsg += "TOO HIGH. Swimmers are at risk of skin rashes. Please evacuate the pool and add liquid acid or dry acid.";
				}
				
				evacuate.Builder responseBuilder = evacuate.newBuilder();
				
				responseBuilder.setEvacuateMsg(evacuateMsg);
				responseObserver.onNext(responseBuilder.build());
				responseObserver.onCompleted();
			}
			
		};
		
	}

}
