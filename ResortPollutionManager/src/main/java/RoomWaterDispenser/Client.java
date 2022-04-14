package RoomWaterDispenser;

import java.util.concurrent.TimeUnit;

import RoomWaterDispenser.RoomWaterDispenserGrpc.RoomWaterDispenserBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		
		// Build a channel - a channel connects the client to the server
		int port = 50054;
		String host = "localhost";
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		//preparing message to send (request) - server streaming part
		lastReplaced roomFilter = lastReplaced.newBuilder().setRoom(102).setLastReplacedDate("2022-03-01").build();
		
		//retrieving reply from service (response)
		RoomWaterDispenserBlockingStub blockingStub = RoomWaterDispenserGrpc.newBlockingStub(channel);
		
		expired response = blockingStub.filterExpiry(roomFilter);
		
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Clean up : Shutdown the channel
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
				
	}

}
