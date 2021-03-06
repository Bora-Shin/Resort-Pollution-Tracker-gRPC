package PoolWaterController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import PoolWaterController.PoolWaterGrpc.PoolWaterImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class PoolWaterControllerService extends PoolWaterImplBase{
	
	private static final Logger logger = Logger.getLogger(PoolWaterControllerService.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {
		
		PoolWaterControllerService poolControlService = new PoolWaterControllerService();
		
		int port = 50052;
		String service_type = "_http._tcp.local.";
		String service_name = "PoolWaterControllerServiceServer";
		SimpleServiceRegistration ssr = new SimpleServiceRegistration();
		ssr.run(port, service_type, service_name);

		try {
			Server server = ServerBuilder.forPort(port).addService(poolControlService).build().start();

			System.out.println("\nServer V1.2 Started");
			
			server.awaitTermination();
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Pool Water Control Server started, listening on " + port);
	}

	
	// client streaming rpc
	@Override
	public StreamObserver<phLevel> stopPoolEntry (StreamObserver<evacuate> responseObserver){
		
		return new StreamObserver<phLevel>() {
			
			ArrayList<Integer> list = new ArrayList();
			
			@Override
			public void onNext(phLevel value) {
				
				list.add(value.getCurrentPhLevel());
				System.out.println(value.getCurrentPhLevel());
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				
				String evacuateMsg = "The Average Ph level for the last "+list.size() +" monitoring periods is ";
				
				double sum = 0;
				for(int ph: list) {
					sum += (double) ph;
				}
				double averagePh = sum/(list.size());
				if(averagePh > 8) {
					evacuateMsg += "TOO HIGH. ("+averagePh+") \nSwimmers are at risk of skin rashes. \nPlease evacuate the pool and add liquid acid or dry acid.";
				}else if(averagePh < 7){
					evacuateMsg +="TOO LOW. ("+averagePh+")\n It causes discomfort for swimmers like burning eyes. \nPlease add sodium carbonate.";
				}else {
					evacuateMsg += "WELL MAINTAINED. ("+averagePh+") \nEnjoy swimming!";
				}
				
							
				evacuate reply = evacuate.newBuilder().setEvacuateMsg(evacuateMsg).build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}
			
		};
		
	}

}
