package PollutionManagerGUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PollutionManagerGUIApp {


	public static void main(String[] args) {
		
		PollutionManagerGUIApp gui = new PollutionManagerGUIApp();
		gui.build();
	}
	
	private void build() {
		
		JFrame frame = new JFrame();
		frame.setTitle("Bora's Resort Pollution Tracking App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set the panel to add buttons
		JPanel panel = new JPanel();
		
		ImageIcon image = new ImageIcon(new ImageIcon("shamrock.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // create an ImageIcon
		frame.setIconImage(image.getImage()); // change icon of this
		
		frame.setSize(600, 600);
		frame.getContentPane().setBackground(new Color(242, 255, 230)); // change color of background
		frame.setResizable(true); 
		frame.setVisible(true); 
		frame.getContentPane().setLayout(null); 
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 560, 541);
		frame.getContentPane().add(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Air Ventilator (Resort)", null, tabbedPane_1, null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Pool Water", null, tabbedPane_2, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Room Air Controller", null, tabbedPane_3, null);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Water Dispenser Filter", null, tabbedPane_4, null);
	}
}
