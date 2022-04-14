package PoolWaterController;

import java.io.IOException;
import java.util.ArrayList;

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
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			@Override
			public void onNext(phLevel value) {
				
				list.add(value.getCurrentPhLevel());
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				
				String evacuateMsg = "The Average Ph level for the last 5 monitoring period is ";
				
				int sum = 0;
				for(int ph: list) {
					sum += ph;
				}
				double averagePh = (double) (sum/list.size());
				if(averagePh > 8) {
					evacuateMsg += "TOO HIGH. ("+averagePh+") Swimmers are at risk of skin rashes. Please evacuate the pool and add liquid acid or dry acid.";
				}else {
					evacuateMsg += "WELL MAINTAINED. ("+averagePh+") Enjoy swimming!";
				}
				
				evacuate.Builder responseBuilder = evacuate.newBuilder();
				
				responseBuilder.setEvacuateMsg(evacuateMsg);
				responseObserver.onNext(responseBuilder.build());
				responseObserver.onCompleted();
			}
			
		};
		
	}

}
