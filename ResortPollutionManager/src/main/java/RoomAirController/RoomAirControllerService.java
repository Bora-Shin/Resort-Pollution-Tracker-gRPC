package RoomAirController;

import java.io.IOException;

import RoomAirController.RoomAirGrpc.RoomAirImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class RoomAirControllerService extends RoomAirImplBase  {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		RoomAirControllerService service3 = new RoomAirControllerService();
		
		int port = 50051;
		Server server = ServerBuilder.forPort(port).addService(service3).build().start();

		System.out.println("Air Quality Tracker started, listening on " + port);

		server.awaitTermination();
	}

	// server steaming rpc
	@Override
	public void controllRoomAir (roomNum request, StreamObserver<hourlyAirTracker> responseObserver) {
		
		hourlyAirTracker.Builder responseBuilder = hourlyAirTracker.newBuilder();
		
		// these are just variables created for the purpose of simplifying this project.
		// in real life, I would like this part to be linked to thermometer to collect room temperature data.
		int minTemp = 0;
		int maxTemp = 50;
		
		// generating random Air Quality Index (AQI) for this project
		int min = 0;
		int max = 500;
		
		
		// server sending message to the room thermometer every hour for 5 hours
		for(int hour = 0; hour <5; hour++) {
			int aqi = (int) Math.floor(Math.random()*(max-min+1)+min);
			String currentAQI = "Room Air Quality Index: " + aqi;	
			
			// temperature
			String temperatureMsg = "Current room temperature: ";
			int temperature = (int) Math.floor(Math.random()*(maxTemp-minTemp+1)+minTemp);
			temperatureMsg += temperature+" degrees. The room is ";
			if (temperature <= 15) {
				temperatureMsg += "cold. Turning the heater ON.";
			}else if(temperature >= 25) {
				temperatureMsg += "hot. Turning the aircon ON.";
			}else {
				temperatureMsg += "moderate.";
			}
			
			// air purifier
			String airPurifierStatus = "";
			if (aqi <= 100) {
				airPurifierStatus += "OFF - Air Quality is Good. ";
			}else {
				airPurifierStatus += "ON - Air Quality is Bad. ";
			}
			
			// carbon monoxide alarm
			min = 0;
			max = 200;
			int carbonMonox = (int) Math.floor(Math.random()*(max-min+1)+min);
			String carbonMonoxAlarm = "Carbon Monoxide Level: " + carbonMonox + " PPM. \nStatus: ";
			if(carbonMonox <=50) {
				carbonMonoxAlarm += "NORMAL";
			}else if(carbonMonox <150) {
				carbonMonoxAlarm += "UNHEALTHY. Please open the window.";
			}else {
				carbonMonoxAlarm += "DANGEROUS. Please leave the room and notify building manager.";
			}
			
			
			
			responseBuilder.setAqi(aqi).setTemperature(temperatureMsg).setAirPurifier(airPurifierStatus).setCarbonMonoxide(carbonMonoxAlarm);
			responseObserver.onNext(responseBuilder.build());
			
		}
		
		// to indicate the message is finished
		responseObserver.onCompleted();
	}
}
