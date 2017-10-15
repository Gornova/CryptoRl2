package it.crypto2.effect;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main implements Runnable {

	private JButton button;

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Main());
	}

	@Override
	public void run() {
		button = new JButton("Shake with this Button  ");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shakeButton();
			}
		});
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(button, BorderLayout.NORTH);

		frame.setSize(240, 160);
		frame.setVisible(true);
	}

	private void shakeButton() {
		final Insets margin = button.getMargin();
		final int delay = 75;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					try {
						setButtonMargin(new Insets(margin.top, margin.left + 3, margin.bottom, margin.right - 2));
						Thread.sleep(delay);
						setButtonMargin(margin);
						Thread.sleep(delay);
						setButtonMargin(new Insets(margin.top, margin.left - 2, margin.bottom, margin.right + 3));
						Thread.sleep(delay);
						setButtonMargin(margin);
						Thread.sleep(delay);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	private void setButtonMargin(final Insets margin) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				button.setMargin(margin);
			}
		});
	}
}