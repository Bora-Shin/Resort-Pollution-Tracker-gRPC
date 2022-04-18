package AirVentilator;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.ServiceInfo;

import AirVentilator.AirVentilationGrpc.AirVentilationBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class Client {

	private static final Logger logger = Logger.getLogger(Client.class.getName());

	public static void main(String[] args) throws InterruptedException {

		ServiceInfo serviceInfo;
		String service_type = "_http._tcp.local.";
		// Now retrieve the service info - all we are supplying is the service type
		serviceInfo = SimpleServiceDiscovery.run(service_type);
		// Use the serviceInfo to retrieve the port
		int port = serviceInfo.getPort();

		// Build a channel - a channel connects the client to the server
		// int port = 50051;
		String host = "localhost";

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		AirVentilationBlockingStub blockingStub = AirVentilationGrpc.newBlockingStub(channel);

		try {
			// preparing message to send (request) - server streaming
			everyHour frequency = everyHour.newBuilder().setHours(3).build();

			// retrieving reply from service (response) - server steaming

			try {
				Iterator<ventilator> responses = blockingStub.airVentilator(frequency);
				while (responses.hasNext()) {
					ventilator individualResponse = responses.next();
					logger.info(individualResponse.getStartVentilator());

				}
				
			} catch (StatusRuntimeException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
