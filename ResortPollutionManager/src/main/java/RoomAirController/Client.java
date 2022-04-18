package RoomAirController;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.ServiceInfo;

import RoomAirController.RoomAirGrpc.RoomAirBlockingStub;
import RoomAirController.RoomAirGrpc.RoomAirStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class Client {

	private static final Logger logger = Logger.getLogger(Client.class.getName());

	public static void main(String[] args) throws InterruptedException {

		ServiceInfo serviceInfo;
		String service_type = "_http._tcp.local.";
		// Now retrieve the service info - all we are supplying is the service type
		serviceInfo = SimpleServiceDiscovery.runjmDNS(service_type);
		// Use the serviceInfo to retrieve the port
		int port = serviceInfo.getPort();

		// Build a channel - a channel connects the client to the server
		// int port = 50053;
		String host = "localhost";

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// specific to the service
		RoomAirBlockingStub blockingStub = RoomAirGrpc.newBlockingStub(channel);
		RoomAirStub asyncStub = RoomAirGrpc.newStub(channel);

		try {
			StreamObserver<hourlyAirTracker> responseObserver = new StreamObserver<hourlyAirTracker>() {

				@Override
				public void onNext(hourlyAirTracker value) {
					logger.info("\n... Room air is currently being monitored ...");
					logger.info("\n<Room temperature>\n"+value.getTemperature());
					logger.info("\n<Air Quality>\n"+value.getAqi());
					logger.info("\n<Air Purifier>\n"+value.getAirPurifier());
					logger.info("\n<Carbon Monoxide Alarm>\n"+value.getCarbonMonoxide());

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
				// in real life, I would like this part to be linked to thermometer to collect
				// room temperature data.
				for(int i = 0 ; i<5; i++) {
				int minTemp = 0;
				int maxTemp = 50;
				int temperature = (int) Math.floor(Math.random() * (maxTemp - minTemp + 1) + minTemp);

				// generating random Air Quality Index (AQI) for this project
				int min = 0;
				int max = 500;
				int aqi = (int) Math.floor(Math.random() * (max - min + 1) + min);

				// generating random carbon Monoxide level for this project
				min = 0;
				max = 200;
				int carbonMonox = (int) Math.floor(Math.random() * (max - min + 1) + min);

				requestObserver.onNext(roomNum.newBuilder().setRoom(102).setTemperature(temperature).setAqi(aqi)
						.setCarbonMonoxide(carbonMonox).build());
				}
				requestObserver.onCompleted();
				Thread.sleep(3000);

			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		} catch (StatusRuntimeException e) {
			logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());

			return;

		} finally {
			// Clean up : Shutdown the channel
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		}
	}
}
