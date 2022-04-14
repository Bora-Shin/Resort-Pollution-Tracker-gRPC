 package RoomAirController;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import RoomAirController.RoomAirGrpc.RoomAirBlockingStub;
import RoomAirController.RoomAirGrpc.RoomAirStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		
		// Build a channel - a channel connects the client to the server
		int port = 50053;
		String host = "localhost";
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		// specific to the service
		RoomAirBlockingStub blockingStub = RoomAirGrpc.newBlockingStub(channel);
		RoomAirStub asyncStub = RoomAirGrpc.newStub(channel);
					
		StreamObserver<hourlyAirTracker> responseObserver = new StreamObserver<hourlyAirTracker>() {

			@Override
			public void onNext(hourlyAirTracker value) {
				System.out.println("<Room Temperature>\n" + value.getTemperature());
				System.out.println("<Air Quality in the Room>\n" + value.getAqi());
				System.out.println("<Air Purifier>\n" + value.getAirPurifier());
				System.out.println("<Carbon Monoxide>\n" + value.getCarbonMonoxide());
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				System.out.println("... Room air monitoring is completed ...");
				
			}
			
		};
		
		StreamObserver<roomNum> requestObserver = asyncStub.controllRoomAir(responseObserver);
		
		try {
			// these are just variables created for the purpose of simplifying this project.
			// in real life, I would like this part to be linked to thermometer to collect room temperature data.
			int minTemp = 0;
			int maxTemp = 50;
			int temperature = (int) Math.floor(Math.random()*(maxTemp-minTemp+1)+minTemp);
			
			// generating random Air Quality Index (AQI) for this project
			int min = 0;
			int max = 500;
			int aqi = (int) Math.floor(Math.random()*(max-min+1)+min);
			
			// generating random carbon Monoxide level for this project
			min = 0;
			max = 200;
			int carbonMonox = (int) Math.floor(Math.random() * (max - min + 1) + min);
			
			requestObserver.onNext(roomNum.newBuilder().setRoom(102).setTemperature(temperature).setAqi(aqi).setCarbonMonoxide(carbonMonox).build());
			requestObserver.onCompleted();
			Thread.sleep(3000);
			
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		// Clean up : Shutdown the channel
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);

	}
}
