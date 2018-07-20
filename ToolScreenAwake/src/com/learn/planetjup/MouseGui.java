package com.learn.planetjup;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MouseGui implements ActionListener {
	private JFrame mainFrame;
	private JPanel panel;
	private JLabel textLabel;
	private JTextField inputText;
	private JButton submitButton;
	private MouseThread thread = null;

	public MouseGui() {
		textLabel = new JLabel("Minutes");

		inputText = new JTextField(5);
		inputText.setEditable(true);
		inputText.setHorizontalAlignment(JTextField.CENTER);

		submitButton = new JButton("Start");
		submitButton.addActionListener(this);

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(textLabel);
		panel.add(inputText);
		panel.add(submitButton);

		mainFrame = new JFrame("Mouse");
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainFrame.add(panel);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.pack();
		
		mainFrame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if (thread != null)
		    	{
		    		thread.setDone();
		    		try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    	}
		    }
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int minutes = 0;
		String input = inputText.getText();
		
		try {
			minutes = Integer.parseInt(input);

			thread = new MouseThread(minutes);
			thread.start();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, "Enter valid number", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}