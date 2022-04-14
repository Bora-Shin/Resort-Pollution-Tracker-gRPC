package AirVentilator;

import java.io.IOException;

import AirVentilator.AirVentilationGrpc.AirVentilationImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AirVentilatorService extends AirVentilationImplBase{

	public static void main(String[] args) throws IOException, InterruptedException {
		
		AirVentilatorService service1 = new AirVentilatorService();
		
		int port = 50051;

		Server server = ServerBuilder.forPort(port).addService(service1).build().start();

		System.out.println("Hourly monitoring for air ventilation started, listening on " + port);

		server.awaitTermination();
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
