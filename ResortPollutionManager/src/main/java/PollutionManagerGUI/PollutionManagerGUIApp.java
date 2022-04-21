package PollutionManagerGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.ServiceInfo;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
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
	private static AirVentilationGrpc.AirVentilationStub asyncStubAir;
	private static PoolWaterGrpc.PoolWaterStub asyncStubPool;
	private static RoomAirGrpc.RoomAirStub asyncStubRoom;
	private static RoomWaterDispenserGrpc.RoomWaterDispenserBlockingStub blockingStubFilter;
	private static ManagedChannel channel1;

	private JFrame frame;
	private JLabel label;
	private JTextArea output1, output2, output3, output4;
	private JTextField entryRoomNum1, entryRoomNum2, entryRoomNum3;
	private JTextField entryTemp1, entryAqi1, entryCarbonm1;
	private JTextField entryTemp2, entryAqi2, entryCarbonm2;
	private JTextField entryTemp3, entryAqi3, entryCarbonm3;
	private JTextField entryRoomNum, entryReplaced;
	private JButton button;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					PollutionManagerGUIApp gui = new PollutionManagerGUIApp();
					gui.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}
	
	public PollutionManagerGUIApp(){
		
		String host = "localhost";
		String service_type = "_http._tcp.local.";
		
		ServiceInfo serviceInfo1;
		serviceInfo1 = AirVentilator.SimpleServiceDiscovery.runjmDNS(service_type);
		int port1 = serviceInfo1.getPort();
		channel1 = ManagedChannelBuilder.forAddress(host, port1).usePlaintext().build();
		asyncStubAir = AirVentilationGrpc.newStub(channel1);
		
		ServiceInfo serviceInfo2;
		serviceInfo2 = PoolWaterController.SimpleServiceDiscovery.runjmDNS(service_type);
		int port2 = serviceInfo2.getPort();
		ManagedChannel channel2 = ManagedChannelBuilder.forAddress(host, port2).usePlaintext().build();
		asyncStubPool = PoolWaterGrpc.newStub(channel2);
		
		ServiceInfo serviceInfo3;
		serviceInfo3 = RoomAirController.SimpleServiceDiscovery.runjmDNS(service_type);
		int port3 = serviceInfo3.getPort();
		ManagedChannel channel3 = ManagedChannelBuilder.forAddress(host, port3).usePlaintext().build();
		asyncStubRoom = RoomAirGrpc.newStub(channel3);
		
		ServiceInfo serviceInfo4;
		serviceInfo4 = RoomWaterDispenser.SimpleServiceDiscovery.runjmDNS(service_type);
		int port4 = serviceInfo4.getPort();
		ManagedChannel channel4 = ManagedChannelBuilder.forAddress(host, port4).usePlaintext().build();
		blockingStubFilter = RoomWaterDispenserGrpc.newBlockingStub(channel4);
		
		initialize();
		
		
	}
	


	// panel 1
	private JPanel getAirVentJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(new Insets(50, 50, 0, 50)));

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

		Integer[] entryHours = { 1, 2, 3, 4, 5};
		final JComboBox<Integer> timerHours = new JComboBox<Integer>(entryHours);
		timerHours.setMaximumSize(timerHours.getPreferredSize());
		timerHours.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(timerHours, c);

		output1 = new JTextArea("", 30, 3);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(output1, c);
		output1.setEditable(false);

		button = new JButton("Set timer");
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



				try {
					// preparing message to send (request) - server streaming
					everyHour frequency = everyHour.newBuilder().setHours((int) timerHours.getSelectedItem()).build();

					// retrieving reply from service (response) - server steaming

					StreamObserver<ventilator> responseObserverVent = new StreamObserver<ventilator>() {

						@Override
						public void onNext(ventilator value) {
							logger.info(value.getStartVentilator());
							output1.append(String.valueOf(value.getStartVentilator()));
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onError(Throwable t) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onCompleted() {
							// TODO Auto-generated method stub

						}

					};

					asyncStubAir.airVentilator(frequency, responseObserverVent);

					try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;

				} 
				
				finally {
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
		label.setHorizontalAlignment(JLabel.CENTER);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		label = new JLabel("Period 1");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		label = new JLabel("Period 2");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);

		panel.add(label, c);
		label = new JLabel("Period 3");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		label = new JLabel("Period 4");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		label = new JLabel("Period 5");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);


		Integer[] ph = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		final JComboBox<Integer> phLevel1 = new JComboBox<Integer>(ph);
		phLevel1.setMaximumSize(phLevel1.getPreferredSize());
		phLevel1.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(phLevel1, c);

		final JComboBox<Integer> phLevel2 = new JComboBox<Integer>(ph);
		phLevel2.setMaximumSize(phLevel2.getPreferredSize());
		phLevel2.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 3;
		panel.add(phLevel2, c);

		final JComboBox<Integer> phLevel3 = new JComboBox<Integer>(ph);
		phLevel3.setMaximumSize(phLevel3.getPreferredSize());
		phLevel3.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(phLevel3, c);

		final JComboBox<Integer> phLevel4 = new JComboBox<Integer>(ph);
		phLevel4.setMaximumSize(phLevel4.getPreferredSize());
		phLevel4.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 5;
		panel.add(phLevel4, c);

		final JComboBox<Integer> phLevel5 = new JComboBox<Integer>(ph);
		phLevel5.setMaximumSize(phLevel5.getPreferredSize());
		phLevel5.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 6;
		panel.add(phLevel5, c);

		output2 = new JTextArea("", 30, 3);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 9;
		panel.add(output2, c);
		output2.setEditable(false);

		button = new JButton("Average PH Level for the monitoring period");
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.gridx = 1;
		c.gridy = 7;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				try {
					// preparing message to send (request) - client streaming
					StreamObserver<evacuate> responseObserverPool = new StreamObserver<evacuate>() {

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

					StreamObserver<phLevel> requestObserverPool = asyncStubPool.stopPoolEntry(responseObserverPool);
					try {

						requestObserverPool.onNext(
								phLevel.newBuilder().setCurrentPhLevel((int) phLevel1.getSelectedItem()).build());
						requestObserverPool.onNext(
								phLevel.newBuilder().setCurrentPhLevel((int) phLevel2.getSelectedItem()).build());
						requestObserverPool.onNext(
								phLevel.newBuilder().setCurrentPhLevel((int) phLevel3.getSelectedItem()).build());
						requestObserverPool.onNext(
								phLevel.newBuilder().setCurrentPhLevel((int) phLevel4.getSelectedItem()).build());
						requestObserverPool.onNext(
								phLevel.newBuilder().setCurrentPhLevel((int) phLevel5.getSelectedItem()).build());

						requestObserverPool.onCompleted();
						Thread.sleep(5000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;
				} 
//				finally {
//
//					// clean up: shutdown the channel
//					try {
//						channel2.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}

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

		// room 1
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

		Integer[] roomNums = {101, 102, 103, 201, 202, 203, 301, 302, 303};
		final JComboBox<Integer> entryRoomNum1 = new JComboBox<Integer>(roomNums);
		entryRoomNum1.setMaximumSize(entryRoomNum1.getPreferredSize());
		entryRoomNum1.setAlignmentX(Component.CENTER_ALIGNMENT);
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

		entryTemp1 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 1;
		panel.add(entryTemp1, c);

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

		entryAqi1 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(entryAqi1, c);

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

		entryCarbonm1 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 2;
		panel.add(entryCarbonm1, c);

		// room 2
		label = new JLabel("Room Number");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		final JComboBox<Integer> entryRoomNum2 = new JComboBox<Integer>(roomNums);
		entryRoomNum2.setMaximumSize(entryRoomNum2.getPreferredSize());
		entryRoomNum2.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 3;
		panel.add(entryRoomNum2, c);

		label = new JLabel("Temperature");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryTemp2 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 3;
		panel.add(entryTemp2, c);

		label = new JLabel("AQI");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryAqi2 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(entryAqi2, c);

		label = new JLabel("Carbon Monoxide");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryCarbonm2 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 4;
		panel.add(entryCarbonm2, c);

		// room 3
		label = new JLabel("Room Number");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		final JComboBox<Integer> entryRoomNum3 = new JComboBox<Integer>(roomNums);
		entryRoomNum3.setMaximumSize(entryRoomNum3.getPreferredSize());
		entryRoomNum3.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 5;
		panel.add(entryRoomNum3, c);

		label = new JLabel("Temperature");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryTemp3 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 5;
		panel.add(entryTemp3, c);

		label = new JLabel("AQI");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryAqi3 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 6;
		panel.add(entryAqi3, c);

		label = new JLabel("Carbon Monoxide");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(label, c);

		entryCarbonm3 = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 6;
		panel.add(entryCarbonm3, c);

		output3 = new JTextArea("", 30, 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 9;
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
		c.gridy = 7;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				try {
					StreamObserver<hourlyAirTracker> responseObserverRoom = new StreamObserver<hourlyAirTracker>() {

						@Override
						public void onNext(hourlyAirTracker value) {
							logger.info("\n... Room air is currently being monitored ...");
							output3.append(
									String.valueOf("\n" + value.getTemperature()
											+ "\n" + value.getAirPurifier() + "\n" + value.getCarbonMonoxide()));

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

					StreamObserver<roomNum> requestObserverRoom = asyncStubRoom.controllRoomAir(responseObserverRoom);

					try {

						requestObserverRoom.onNext(roomNum.newBuilder().setRoom((int)entryRoomNum1.getSelectedItem())
								.setTemperature(Integer.parseInt(entryTemp1.getText()))
								.setAqi(Integer.parseInt(entryAqi1.getText()))
								.setCarbonMonoxide(Integer.parseInt(entryCarbonm1.getText())).build());
						requestObserverRoom.onNext(roomNum.newBuilder().setRoom((int)entryRoomNum2.getSelectedItem())
								.setTemperature(Integer.parseInt(entryTemp2.getText()))
								.setAqi(Integer.parseInt(entryAqi2.getText()))
								.setCarbonMonoxide(Integer.parseInt(entryCarbonm2.getText())).build());
						requestObserverRoom.onNext(roomNum.newBuilder().setRoom((int)entryRoomNum3.getSelectedItem())
								.setTemperature(Integer.parseInt(entryTemp3.getText()))
								.setAqi(Integer.parseInt(entryAqi3.getText()))
								.setCarbonMonoxide(Integer.parseInt(entryCarbonm3.getText())).build());

						requestObserverRoom.onCompleted();
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

				} 
//				finally {
//					// Clean up : Shutdown the channel
//					try {
//						channel3.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}

			}

		});
		panel.add(button, c);
		return panel;
	}

	// panel 4
	private JPanel getWaterDispenserJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
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

		entryRoomNum = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(entryRoomNum, c);

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
		panel.add(label, c);

		entryReplaced = new JTextField("YYYY-MM-DD", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 1;
		panel.add(entryReplaced, c);

		output4 = new JTextArea("", 30, 3);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 4;
		c.gridheight = 2;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(output4, c);
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


				try {

					// preparing message to send (request) - server streaming part
					lastReplaced roomFilter = lastReplaced.newBuilder()
							.setRoom(Integer.parseInt(entryRoomNum.getText()))
							.setLastReplacedDate(entryReplaced.getText()).build();

					expired response = blockingStubFilter.filterExpiry(roomFilter);
					logger.info(response.getExpiry());
					output4.setText(String.valueOf(response.getExpiry()));

				} catch (StatusRuntimeException e1) {
					logger.log(Level.WARNING, "RPC failed: {0}", e1.getStatus());

					return;
				} 
//				finally {
//					// Clean up : Shutdown the channel
//					try {
//						channel4.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}

			}

		});
		panel.add(button, c);
		return panel;
	}

	private void initialize() {

		frame = new JFrame();
		frame.setTitle("Bora's Resort Pollution Tracking App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon image = new ImageIcon(
				new ImageIcon("shamrock.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
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
		

	}

}
