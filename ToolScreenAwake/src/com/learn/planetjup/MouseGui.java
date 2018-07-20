package com.learn.planetjup;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MouseGui implements ActionListener, IUpdateListener {
	private int maxTime = 0;
	private GuiState state = GuiState.WAITING;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel textLabel;
	private JTextField inputText;
	private JButton submitButton;
	private MouseThread thread = null;

	private static final int TEXT_WIDTH = 8;
	private static final int WIDTH = 220;
	private static final int HEIGHT = 70;

	enum GuiState {
		WAITING, RUNNING;
	}

	public MouseGui() {
		textLabel = new JLabel("Minutes", JLabel.CENTER);

		inputText = new JTextField(TEXT_WIDTH);
		inputText.setEditable(true);
		inputText.setHorizontalAlignment(JTextField.CENTER);

		submitButton = new JButton("Start");
		submitButton.addActionListener(this);

		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(textLabel);
		mainPanel.add(inputText);
		mainPanel.add(submitButton);

		mainFrame = new JFrame("Mouse");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		mainFrame.pack();

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				stopThread();
			}
		});

		changeState(GuiState.WAITING);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (state == GuiState.WAITING) {
			startThread(inputText.getText());
		} else {
			stopThread();
		}
	}

	@Override
	public void timeUpdate(Integer lapsedTime, Integer maxTime) {
		inputText.setText(lapsedTime + " / " + maxTime);
	}

	@Override
	public void timeCompleted() {
		thread = null;
		changeState(GuiState.WAITING);
	}

	private void startThread(String input) {
		try {
			maxTime = Integer.parseInt(input);
			thread = new MouseThread(maxTime, this);
			thread.start();

			changeState(GuiState.RUNNING);
		} catch (NumberFormatException e) {
			System.err.println("INPUT ERROR: Invalid number: " + e.getMessage());
			inputText.setText("");
			JOptionPane.showMessageDialog(mainPanel, "Enter valid number", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void stopThread() {
		if (thread != null) {
			thread.setDone();
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println("THREAD ERROR: " + e.getMessage());
			}
		}

		changeState(GuiState.WAITING);
	}

	private void changeState(GuiState newState) {
		this.state = newState;
		updateGui();
	}

	private void updateGui() {
		switch (state) {
		case WAITING:
			maxTime = 0;
			inputText.setText("");
			inputText.setEditable(true);
			submitButton.setText("Start");
			textLabel.setText("Minutes");
			break;
		case RUNNING:
			inputText.setEditable(false);
			inputText.setText("0 / " + maxTime);
			submitButton.setText("Stop");
			textLabel.setText("Minutes Lapsed");

			break;
		}

		mainFrame.getContentPane().repaint();
	}
}
