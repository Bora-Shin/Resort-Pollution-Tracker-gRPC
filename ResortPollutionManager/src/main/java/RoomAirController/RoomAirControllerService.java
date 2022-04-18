package RoomAirController;

import java.io.IOException;
import java.util.logging.Logger;

import RoomAirController.RoomAirGrpc.RoomAirImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class RoomAirControllerService extends RoomAirImplBase {
	
	private static final Logger logger = Logger.getLogger(RoomAirControllerService.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {

		RoomAirControllerService roomAirservice = new RoomAirControllerService();

		int port = 50053;
		String service_type = "_http._tcp.local.";
		String service_name = "RoomAirControllerServiceServer";
		SimpleServiceRegistration ssr = new SimpleServiceRegistration();
		ssr.run(port, service_type, service_name);

		try {
			Server server = ServerBuilder.forPort(port).addService(roomAirservice).build().start();

			System.out.println("\nServer V1.2 Started");
			
			server.awaitTermination();
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Room Air Control Server started, listening on " + port);
	}

	// bi-directional steaming rpc
	public StreamObserver<roomNum> controllRoomAir(StreamObserver<hourlyAirTracker> responseObserver) {

		return new StreamObserver<roomNum>() {

			@Override
			public void onNext(roomNum value) {
				// TODO Auto-generated method stub
				for (int hour = 0; hour < 5; hour++) {

					// temperature
					String temperatureMsg = "Current room temperature: ";
					int temperature = value.getTemperature();
					temperatureMsg += temperature + " degrees. The room is ";
					if (temperature <= 15) {
						temperatureMsg += "cold. Turning the heater ON.";
					} else if (temperature >= 25) {
						temperatureMsg += "hot. Turning the aircon ON.";
					} else {
						temperatureMsg += "moderate.";
					}

					// air purifier
					int aqi = value.getAqi();
					String currentAQI = "Room Air Quality Index: " + aqi;
					String airPurifierStatus = "";
					if (aqi <= 100) {
						airPurifierStatus += "OFF - Air Quality is Good. ";
					} else {
						airPurifierStatus += "ON - Air Quality is Bad. ";
					}

					// carbon monoxide alarm
					int carbonMonox = value.getCarbonMonoxide();
					String carbonMonoxAlarm = "Carbon Monoxide Level: " + carbonMonox + " PPM. Status: ";
					if (carbonMonox <= 50) {
						carbonMonoxAlarm += "NORMAL";
					} else if (carbonMonox < 150) {
						carbonMonoxAlarm += "UNHEALTHY. Please open the window.";
					} else {
						carbonMonoxAlarm += "DANGEROUS. Please leave the room and notify building manager.";
					}

					hourlyAirTracker response = hourlyAirTracker.newBuilder().setTemperature(temperatureMsg)
							.setAqi(currentAQI).setAirPurifier(airPurifierStatus).setCarbonMonoxide(carbonMonoxAlarm)
							.build();
					responseObserver.onNext(response);
				}

			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				// to indicate the message is finished
				responseObserver.onCompleted();

			}

		};

	}

}
