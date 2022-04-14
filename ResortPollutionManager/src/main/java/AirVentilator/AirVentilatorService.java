package AirVentilator;

import java.io.IOException;
import java.util.logging.Logger;

import AirVentilator.AirVentilationGrpc.AirVentilationImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AirVentilatorService extends AirVentilationImplBase{
	
	private static final Logger logger = Logger.getLogger(AirVentilatorService.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {
		
		AirVentilatorService airVentService = new AirVentilatorService();
		
		int port = 50051;
		String service_type = "_grpc._tcp.local.";
		String service_name = "GrpcServer";
		SimpleServiceRegistration ssr = new SimpleServiceRegistration();
		ssr.run(port, service_type, service_name);

		try {
			Server server = ServerBuilder.forPort(port).addService(airVentService).build().start();

			System.out.println("\nServer V1.2 Started");
			
			server.awaitTermination();
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Air Ventilator Server started, listening on " + port);
		
	}
	
	// server streaming rpc
	@Override
	public void airVentilator(everyHour request, StreamObserver<ventilator> responseObserver) {
		
		ventilator.Builder responseBuilder = ventilator.newBuilder();
		
		
		// client sends the frequency of air ventilation 
		int frequency = request.getHours();
		
		// server sending hourly status of the air ventilator ( number of responses are set by the client )
		for(int hour = 0; hour<=frequency; hour++) {
			String startVentilator = "Air Ventilation is currently ";
						
			if(hour == frequency) {
				startVentilator+= "ON. Windows are OPEN. ";
			}else {
				startVentilator+= "OFF. All windows are CLOSED. (" + (frequency-hour) +" hours left until next air ventilation.)";
			}
			
			responseBuilder.setStartVentilator(startVentilator);
			responseObserver.onNext(responseBuilder.build());
		}
		
		// to indicate the message is finished
		responseObserver.onCompleted();
		
	}
}
