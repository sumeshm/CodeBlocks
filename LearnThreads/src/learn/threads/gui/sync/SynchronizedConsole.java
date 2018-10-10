package learn.threads.gui.sync;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SynchronizedConsole implements ActionListener {

	private final static Object lock = new Object();
	private List<ConsoleWorkerThread> theadList = new ArrayList<>();
	private int count = 100;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	public SynchronizedConsole() {
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);

		JButton submitButton = new JButton("Start new Thread");
		submitButton.addActionListener(this);

		JFrame jFrame = new JFrame();
		jFrame.add(new JLabel("CONSOLE:"), BorderLayout.NORTH);
		jFrame.add(scrollPane, BorderLayout.CENTER);
		jFrame.add(submitButton, BorderLayout.SOUTH);

		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setSize(800, 600);

		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				for (Thread thread : theadList) {
					thread.interrupt();
				}
				
				System.exit(1);
			}
		});
	}

	public void write(int sourceId, String msg) {
		synchronized (lock) {
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");  
			textArea.append("\n [" + formatter.format(new Date()) + "] - " + sourceId + " : " + msg);
			
			JScrollBar vertical = scrollPane.getVerticalScrollBar();
			vertical.setValue( vertical.getMaximum() );

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void clear(int sourceId) {
		synchronized (lock) {
			synchronized (lock) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				textArea.setText("\n [" + formatter.format(new Date()) + "] [" + sourceId + "] - CLEAR console");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ConsoleWorkerThread thread = new ConsoleWorkerThread(count++, this);
		theadList.add(thread);
		thread.start();
	}
}
