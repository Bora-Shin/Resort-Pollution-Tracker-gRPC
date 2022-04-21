package PollutionManagerGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.ServiceInfo;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AirVentilator.AirVentilationGrpc;
import AirVentilator.everyHour;
import AirVentilator.ventilator;
import PoolWaterController.PoolWaterGrpc;
import PoolWaterController.evacuate;
import PoolWaterController.phLevel;
import RoomAirController.RoomAirGrpc;
import RoomAirController.hourlyAirTracker;
import RoomAirController.roomNum;
import RoomWaterDispenser.RoomWaterDispenserGrpc;
import RoomWaterDispenser.expired;
import RoomWaterDispenser.lastReplaced;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class PollutionManagerGUIApp {

	private static final Logger logger = Logger.getLogger(PollutionManagerGUIApp.class.getName());
	private static String host = "localhost";
	private static String service_type = "_http._tcp.local.";

	private JLabel label;
	private JTextField entryHours, output1;
	private JTextField entryPH, output2;
	private JTextField entryRoomNum1, entryTemp, entryAqi, entryCarbonm, output3;
	private JTextField entryRoomNum2, entryReplaced, output4;
	private JButton button;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					PollutionManagerGUIApp gui = new PollutionManagerGUIApp();
					gui.build();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	// panel 1
	private JPanel getAirVentJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;

		label = new JLabel("-- Set the timer to the Air Ventilator --");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("ventilation.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		label.setIcon(icon);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Biome", Font.BOLD, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);

		label = new JLabel("Hours");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryHours = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(entryHours, c);

		label = new JLabel("Status");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(label, c);

		output1 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(output1, c);
		output1.setEditable(false);

		button = new JButton("Set timer");
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.gridx = 2;
		c.gridy = 2;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ServiceInfo serviceInfo1;
				serviceInfo1 = AirVentilator.SimpleServiceDiscovery.run(service_type);
				int port1 = serviceInfo1.getPort();
				ManagedChannel channel1 = ManagedChannelBuilder.forAddress(host, port1).usePlaintext().build();
				AirVentilationGrpc.AirVentilationBlockingStub blockingStub = AirVentilationGrpc
						.newBlockingStub(channel1);

				try {
					// preparing message to send (request) - server streaming
					everyHour frequency = everyHour.newBuilder().setHours(Integer.parseInt(entryHours.getText()))
							.build();

					// retrieving reply from service (response) - server steaming

					try {
						Iterator<ventilator> responses = blockingStub.airVentilator(frequency);
						while (responses.hasNext()) {
							ventilator individualResponse = responses.next();
							logger.info(individualResponse.getStartVentilator());
							output1.setText(String.valueOf(individualResponse.getStartVentilator()));

						}

					} catch (StatusRuntimeException e1) {
						e1.printStackTrace();
					}

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;

				} finally {
					// Clean up : Shutdown the channel
					try {
						channel1.shutdown().awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		panel.add(button, c);

		return panel;
	}

	// panel 2
	private JPanel getPoolWaterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;

		label = new JLabel("-- Pool Water PH Level Controller --");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("pool.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		label.setIcon(icon);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Biome", Font.BOLD, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);

		label = new JLabel("PH Level");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryPH = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(entryPH, c);
////////////////////////////////////////////////////set 5 PH levels to GUI

		label = new JLabel("Status");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(label, c);

		output2 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(output2, c);
		output2.setEditable(false);

		button = new JButton("Average PH Level for the monitoring period");
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.gridx = 2;
		c.gridy = 2;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ServiceInfo serviceInfo2;
				serviceInfo2 = PoolWaterController.SimpleServiceDiscovery.runjmDNS(service_type);
				int port2 = serviceInfo2.getPort();
				ManagedChannel channel2 = ManagedChannelBuilder.forAddress(host, port2).usePlaintext().build();
				PoolWaterGrpc.PoolWaterStub asyncStub = PoolWaterGrpc.newStub(channel2);

				try {
					// preparing message to send (request) - client streaming
					StreamObserver<evacuate> responseObserver = new StreamObserver<evacuate>() {

						@Override
						public void onNext(evacuate value) {
							logger.info(value.getEvacuateMsg());
							output2.setText(String.valueOf(value.getEvacuateMsg()));
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

						for (int i = 0; i < 5; i++) {
							// current PH level
							int min = 1;
							int max = 12;
							int currentPhLevel = (int) Math.floor(Math.random() * (max - min + 1) + min);

							requestObserver.onNext(phLevel.newBuilder().setCurrentPhLevel(currentPhLevel).build());

////////////////////////////////////////////////////set 5 PH levels to GUI
						}

						requestObserver.onCompleted();
						Thread.sleep(5000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;
				} finally {

					// clean up: shutdown the channel
					try {
						channel2.shutdown().awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		panel.add(button, c);
		return panel;
	}

	// panel 3
	private JPanel getRoomAirMonitorJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;

		label = new JLabel("-- Room Air Controller --");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("bedroom.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		label.setIcon(icon);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Biome", Font.BOLD, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(label, c);

		label = new JLabel("Room Number");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryRoomNum1 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(entryRoomNum1, c);

		label = new JLabel("Temperature");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryTemp = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 1;
		panel.add(entryTemp, c);

		label = new JLabel("AQI");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryAqi = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(entryAqi, c);

		label = new JLabel("Carbon Monoxide");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryCarbonm = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 2;
		panel.add(entryCarbonm, c);

		output3 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 5;
		panel.add(output3, c);
		output3.setEditable(false);
		output3.setBorder(null);

		button = new JButton("Room Air Controller ON");
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.gridwidth = 4;
		c.gridheight = 2;
		c.gridx = 1;
		c.gridy = 3;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ServiceInfo serviceInfo3;
				serviceInfo3 = RoomAirController.SimpleServiceDiscovery.runjmDNS(service_type);
				int port3 = serviceInfo3.getPort();
				ManagedChannel channel3 = ManagedChannelBuilder.forAddress(host, port3).usePlaintext().build();
				RoomAirGrpc.RoomAirStub asyncStub = RoomAirGrpc.newStub(channel3);

				try {
					StreamObserver<hourlyAirTracker> responseObserver = new StreamObserver<hourlyAirTracker>() {

						@Override
						public void onNext(hourlyAirTracker value) {
							logger.info("\n... Room air is currently being monitored ...");
							logger.info("\n<Room temperature>\n" + value.getTemperature() + "\n<Air Quality>\n"
									+ value.getAqi() + "\n<Air Purifier>\n" + value.getAirPurifier()
									+ "\n<Carbon Monoxide Alarm>\n" + value.getCarbonMonoxide());

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
						for (int i = 0; i < 5; i++) {
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

							requestObserver.onNext(roomNum.newBuilder().setRoom(102).setTemperature(temperature)
									.setAqi(aqi).setCarbonMonoxide(carbonMonox).build());

						}
						requestObserver.onCompleted();
						Thread.sleep(3000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;

				} finally {
					// Clean up : Shutdown the channel
					try {
						channel3.shutdown().awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		panel.add(button, c);
		return panel;
	}

	// panel 4
	private JPanel getWaterDispenserJPanel() {
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;

		label = new JLabel("-- Water Dispenser Filter Monitor --");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("water-pollution.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		label.setIcon(icon);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Biome", Font.BOLD, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 0;
		panel4.add(label, c);

		label = new JLabel("Room Number");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel4.add(label, c);

		entryRoomNum2 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		panel4.add(entryRoomNum2, c);

		label = new JLabel("Replaced");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel4.add(label, c);

		entryReplaced = new JTextField("YYYY-MM-DD", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 1;
		panel4.add(entryReplaced, c);

		label = new JLabel("Status");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		panel4.add(label, c);

		output4 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 4;
		panel4.add(output4, c);
		output4.setEditable(false);

		button = new JButton("Check Expiry");
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.gridwidth = 4;
		c.gridheight = 2;
		c.gridx = 1;
		c.gridy = 2;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceInfo serviceInfo4;
				serviceInfo4 = RoomWaterDispenser.SimpleServiceDiscovery.runjmDNS(service_type);
				int port4 = serviceInfo4.getPort();
				ManagedChannel channel4 = ManagedChannelBuilder.forAddress(host, port4).usePlaintext().build();
				RoomWaterDispenserGrpc.RoomWaterDispenserBlockingStub blockingStub = RoomWaterDispenserGrpc
						.newBlockingStub(channel4);

				try {

					// preparing message to send (request) - server streaming part
					lastReplaced roomFilter = lastReplaced.newBuilder().setRoom(102).setLastReplacedDate("2022-03-01")
							.build();

					expired response = blockingStub.filterExpiry(roomFilter);
					logger.info(response.getExpiry());

				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;
				} finally {
					// Clean up : Shutdown the channel
					try {
						channel4.shutdown().awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		panel4.add(button, c);
		return panel4;
	}

	private void build() {

		JFrame frame = new JFrame();
		frame.setTitle("Bora's Resort Pollution Tracking App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon image = new ImageIcon(new ImageIcon("shamrock.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); 
		frame.setIconImage(image.getImage()); // change icon of this

		frame.setSize(800, 1000);
		frame.getContentPane().setBackground(new Color(242, 255, 230)); // change color of background
		frame.setResizable(false);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 700, 900);
		tabbedPane.setBorder(new EmptyBorder(new Insets(100, 100, 50, 50)));
		frame.getContentPane().setLayout(null);

		tabbedPane.addTab("Air Ventilator", null, getAirVentJPanel(), null);
		tabbedPane.addTab("Pool Water", null, getPoolWaterPanel(), null);
		tabbedPane.addTab("Room Air Monitor", null, getRoomAirMonitorJPanel(), null);
		tabbedPane.addTab("Water Dispenser", null, getWaterDispenserJPanel(), null);

		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);

	}

}
