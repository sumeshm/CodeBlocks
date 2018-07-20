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
import javax.swing.JTextField;

public class MouseGui implements ActionListener {
	private int maxTime = 0;
	private GuiState state = GuiState.WAITING;
	private JFrame mainFrame;
	private JPanel panelWaiting;
	private JPanel panelRunning;
	private JLabel textLabel;
	private JTextField inputText;
	private JButton startButton;
	private JButton stopButton;
	private MouseThread thread = null;

	enum GuiState {
		WAITING, RUNNING;
	}

	public MouseGui() {
		createRunningGui();
		createWaitingGui();

		mainFrame = new JFrame("Mouse");
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				stopThread();
			}
		});

		changeState(GuiState.WAITING);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (state == GuiState.WAITING)
		{
			startThread(inputText.getText());
		} else {
			stopThread();
		}
	}
	
	private void startThread(String input)
	{
		try {
			maxTime = Integer.parseInt(input);
			thread = new MouseThread(maxTime);
			thread.start();

			changeState(GuiState.RUNNING);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panelWaiting, "Enter valid number", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void createRunningGui()
	{
		textLabel = new JLabel("Minutes");

		inputText = new JTextField(5);
		inputText.setEditable(true);
		inputText.setHorizontalAlignment(JTextField.CENTER);

		startButton = new JButton("Start");
		startButton.addActionListener(this);

		panelWaiting = new JPanel();
		panelWaiting.setLayout(new FlowLayout());
		panelWaiting.add(textLabel);
		panelWaiting.add(inputText);
		panelWaiting.add(startButton);
	}
	
	private void createWaitingGui()
	{
		stopButton = new JButton("Stop");
		stopButton.addActionListener(this);

		panelRunning = new JPanel();
		panelRunning.setLayout(new FlowLayout());
		panelRunning.add(stopButton);
	}

	private void stopThread()
	{
		if (thread != null) {
			thread.setDone();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		changeState(GuiState.WAITING);
	}

	private void changeState(GuiState newState)
	{
		this.state = newState;
		updateGui();
	}

	private void updateGui()
	{
		switch (state) {
		case WAITING:
			maxTime = 0;
			inputText.setText("");
			mainFrame.remove(panelRunning);
			mainFrame.add(panelWaiting);
			break;
		case RUNNING:
			mainFrame.remove(panelWaiting);
			mainFrame.add(panelRunning);
			break;
		}

		mainFrame.setVisible(true);
		mainFrame.pack();
		mainFrame.getContentPane().repaint();
	}
}
