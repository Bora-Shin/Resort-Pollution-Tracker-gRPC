package RoomWaterDispenser;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.ServiceInfo;

import RoomWaterDispenser.RoomWaterDispenserGrpc.RoomWaterDispenserBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

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
		// int port = 50054;
		String host = "localhost";

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();



		// retrieving reply from service (response)
		RoomWaterDispenserBlockingStub blockingStub = RoomWaterDispenserGrpc.newBlockingStub(channel);

		try {
			
			// preparing message to send (request) - server streaming part
			lastReplaced roomFilter = lastReplaced.newBuilder().setRoom(102).setLastReplacedDate("2022-03-01").build();
			
			expired response = blockingStub.filterExpiry(roomFilter);
			logger.info(response.getExpiry());

		} catch (StatusRuntimeException e) {
			logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());

			return;
		} finally {
			// Clean up : Shutdown the channel
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		}
	}

}
