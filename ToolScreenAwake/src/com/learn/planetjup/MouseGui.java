package com.learn.planetjup;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MouseGui implements ActionListener, MouseListener, IUpdateListener {
	private int maxTime = 0;
	private GuiState state = GuiState.WAITING;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel textLabel;
	private JTextField inputText;
	private JButton submitButton;
	private MouseThread thread = null;
	private ImageIcon playIcon = null;
	private ImageIcon stopIcon = null;

	private static final int TEXT_WIDTH = 8;
	private static final int WIDTH = 220;
	private static final int HEIGHT = 90;
	private static final int INSET = 8;

	enum GuiState {
		WAITING, RUNNING;
	}

	public MouseGui() {
		textLabel = new JLabel("Minutes", JLabel.CENTER);

		inputText = new JTextField(TEXT_WIDTH);
		inputText.setEditable(true);
		inputText.setHorizontalAlignment(JTextField.CENTER);
		inputText.addMouseListener(this);
		inputText.setMargin(new Insets(INSET, INSET, INSET, INSET));

		playIcon = new ImageIcon(getClass().getResource("/play.png"));
		stopIcon = new ImageIcon(getClass().getResource("/stop.png"));
		
		submitButton = new JButton();
		submitButton.addActionListener(this);
		submitButton.setBorderPainted(false);

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

	public void awake() {
		startThread("0");
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
	public void mouseClicked(MouseEvent e) {
		if (state == GuiState.WAITING)
		{
			inputText.setText("");
		}
	}

	@Override
	public void timeUpdate(Integer lapsedTime, Integer maxTime) {
		String postFix = (maxTime == 0) ? "-" : maxTime.toString();
		inputText.setText(lapsedTime + " / " + postFix);
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
			textLabel.setText("Minutes");
			inputText.setText("0");
			inputText.setEditable(true);
			submitButton.setIcon(playIcon);
			break;
		case RUNNING:
			textLabel.setText("Minutes Lapsed");
			inputText.setEditable(false);
			timeUpdate(0, maxTime);
			submitButton.setIcon(stopIcon);
			break;
		}

		mainFrame.getContentPane().repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
