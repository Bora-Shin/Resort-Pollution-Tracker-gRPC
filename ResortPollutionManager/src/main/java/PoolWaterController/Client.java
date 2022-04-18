package PoolWaterController;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.ServiceInfo;
import PoolWaterController.PoolWaterGrpc.PoolWaterStub;
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
		// int port = 50052;
		String host = "localhost";

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		PoolWaterStub asyncStub = PoolWaterGrpc.newStub(channel);

		try {
			// preparing message to send (request) - client streaming
			StreamObserver<evacuate> responseObserver = new StreamObserver<evacuate>() {

				@Override
				public void onNext(evacuate value) {
					logger.info(value.getEvacuateMsg());

				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();

				}

				@Override
				public void onCompleted() {
					// TODO Auto-generated method stub

				}

			};

			StreamObserver<phLevel> requestObserver = asyncStub.stopPoolEntry(responseObserver);
			try {

				
					for(int i = 0; i < 5; i++) {
					// current PH level
					int min = 1;
					int max = 12;
					int currentPhLevel = (int) Math.floor(Math.random() * (max - min + 1) + min);

					requestObserver.onNext(phLevel.newBuilder().setCurrentPhLevel(currentPhLevel).build());
					}

				requestObserver.onCompleted();
				Thread.sleep(5000);

			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (StatusRuntimeException e) {
			logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());

			return;
		} finally {

			// clean up: shutdown the channel
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		}
	}
}
