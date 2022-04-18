package RoomWaterDispenser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import RoomWaterDispenser.RoomWaterDispenserGrpc.RoomWaterDispenserImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class RoomWaterDispenserService extends RoomWaterDispenserImplBase {

	private static final Logger logger = Logger.getLogger(RoomWaterDispenserService.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {

		RoomWaterDispenserService waterDispenserService = new RoomWaterDispenserService();

		int port = 50054;
		String service_type = "_http._tcp.local.";
		String service_name = "RoomWaterDispenserServiceServer";
		SimpleServiceRegistration ssr = new SimpleServiceRegistration();
		ssr.run(port, service_type, service_name);

		try {
			Server server = ServerBuilder.forPort(port).addService(waterDispenserService).build().start();

			System.out.println("\nServer V1.2 Started");

			server.awaitTermination();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Room Water Dispenser Server started, listening on " + port);
	}

	@Override
	public void filterExpiry(lastReplaced request, StreamObserver<expired> responseObserver) {

		// checking when was the filter last replaced
		String lastReplacedStr = request.getLastReplacedDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date lastReplacedDate;
		try {
			lastReplacedDate = dateFormat.parse(lastReplacedStr);

			// checking when is the filtr expiry date
			Date expiryDate = addDays(lastReplacedDate, 30);

			// checking today's date
			Date today = Calendar.getInstance().getTime();

			String expiry = "The water dispenser filter expiry date: " + expiryDate;
			if (expiryDate.compareTo(today) <= 0) {
				long late = today.getTime() - expiryDate.getTime();
				expiry += " Please replace the filter immediately. It has been "
						+ TimeUnit.DAYS.convert(late, TimeUnit.MILLISECONDS) + " days since the filter expired.";
			} else {
				long remaining = expiryDate.getTime() - today.getTime();
				expiry += " Remaining days until the filter needs to be replaced: "
						+ TimeUnit.DAYS.convert(remaining, TimeUnit.MILLISECONDS);
			}

			expired reply = expired.newBuilder().setExpiry(expiry).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Date addDays(Date date, int days) {
		date.setTime(date.getTime() + days * 1000L * 60L * 60L * 24L);
		return date;
	}
}
