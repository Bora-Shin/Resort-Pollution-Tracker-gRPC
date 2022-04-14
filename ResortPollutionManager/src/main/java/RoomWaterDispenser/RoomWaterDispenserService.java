package RoomWaterDispenser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import RoomWaterDispenser.RoomWaterDispenserGrpc.RoomWaterDispenserImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class RoomWaterDispenserService extends RoomWaterDispenserImplBase{

	public static void main(String[] args) throws IOException, InterruptedException {
		
		RoomWaterDispenserService service4 = new RoomWaterDispenserService();
		
		int port = 50054;
		Server server = ServerBuilder.forPort(port).addService(service4).build().start();

		System.out.println("Checking Water Dispensier Expiry, listening on " + port);

		server.awaitTermination();
	}
	
	
		@Override
		public void filterExpiry(lastReplaced request, StreamObserver<expired> responseObserver) {
			
			// checking when was the filter last replaced
			String lastReplacedStr = request.getLastReplacedDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
			Date lastReplacedDate;
			try {
				lastReplacedDate = dateFormat.parse(lastReplacedStr);

			
			// checking when is the filtr expiry date
			Date expiryDate = addDays(lastReplacedDate, 30);

			// checking today's date
			Date today = Calendar.getInstance().getTime();
			
			String expiry = "The water dispenser filter expiry date: " + expiryDate;
			if(expiryDate.compareTo(today)<=0) {
				long late = today.getTime() - expiryDate.getTime();
				expiry +=" Please replace the filter immediately. It has been " + TimeUnit.DAYS.convert(late, TimeUnit.MILLISECONDS) +" days since the filter expired.";
			}else {
				long remaining = expiryDate.getTime() - today.getTime();
				expiry +=" Remaining days until the filter needs to be replaced: " + TimeUnit.DAYS.convert(remaining, TimeUnit.MILLISECONDS);
			}
			
			System.out.println(expiry);
			expired responseBuilder = expired.newBuilder().setExpiry(expiry).build();
			responseObserver.onNext(responseBuilder);
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
