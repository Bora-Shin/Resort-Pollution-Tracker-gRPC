package PollutionManagerGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PollutionManagerGUIApp {
	
	private JLabel label;
	private JTextField entry;
	private JButton button;

	public static void main(String[] args) {
		
		PollutionManagerGUIApp gui = new PollutionManagerGUIApp();
		gui.build();
	}
	
	private void build() {
		
		JFrame frame = new JFrame();
		frame.setTitle("Bora's Resort Pollution Tracking App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
		
		ImageIcon image = new ImageIcon(new ImageIcon("shamrock.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // create an ImageIcon
		frame.setIconImage(image.getImage()); // change icon of this
		
		frame.setSize(600, 600);
		frame.getContentPane().setBackground(new Color(242, 255, 230)); // change color of background
		frame.setResizable(true); 

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 560, 541);
		
		frame.getContentPane().setLayout(null); 
		
		
		// panel 1
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("Air Ventilator", null, panel1, null);
		panel1.setLayout(new BorderLayout(10, 10));
		
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	
		c.fill = GridBagConstraints.HORIZONTAL;
		
		label = new JLabel("-- Set the timer to the Air Ventilator --");
		label.setFont(new Font("Biome", Font.BOLD, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(label, c);
		
		label = new JLabel("Hours: ");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10,10,10,10);
		panel1.add(label, c);
		
		entry = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		panel1.add(entry, c);
		
		label = new JLabel("Status: ");
		label.setFont(new Font("Biome", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.RIGHT);
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(label, c);
		
		entry = new JTextField("", 30);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 2;
		panel1.add(entry, c);
		
		button = new JButton("Set timer");
		c.weightx = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.gridwidth = 4;
		c.gridheight = 2;
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(button, c);
		
			
		
		// panel 2
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Pool Water", null, panel2, null);
		panel2.setLayout(new BorderLayout(10, 10));
		
		JLabel label3 = new JLabel("-- Pool Water PH Level Monitor --");
		panel2.add(label3, BorderLayout.NORTH);
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setFont(new Font("Biome", Font.BOLD, 20));
		label3.setPreferredSize(new Dimension(400, 50));
		label3.setOpaque(false);
		
		// panel 3
		JPanel panel3 = new JPanel();
		tabbedPane.addTab("Room Air Monitor", null, panel3, null);
		panel3.setLayout(new BorderLayout(10, 10));
		
		JLabel label6 = new JLabel("-- Room Air Monitor --");
		panel3.add(label6, BorderLayout.NORTH);
		label6.setHorizontalAlignment(JLabel.CENTER);
		label6.setFont(new Font("Biome", Font.BOLD, 20));
		label6.setPreferredSize(new Dimension(400, 50));
		label6.setOpaque(false);
		
		// panel 4
		JPanel panel4 = new JPanel();
		tabbedPane.addTab("Water Dispenser", null, panel4, null);
		panel4.setLayout(new BorderLayout(10, 10));
		
		JLabel label9 = new JLabel("-- Water Dispenser Filter Manager --");
		panel4.add(label9, BorderLayout.NORTH);
		label9.setHorizontalAlignment(JLabel.CENTER);
		label9.setFont(new Font("Biome", Font.BOLD, 20));
		label9.setPreferredSize(new Dimension(400, 50));
		label9.setOpaque(false);
		
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true); 
	
	}
}
