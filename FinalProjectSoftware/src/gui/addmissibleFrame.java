package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class addmissibleFrame extends JFrame{
	
	JPanel contentPane;
	JPanel mainPanel;
	JTextArea mainTextArea;

	public addmissibleFrame(String string) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);
		mainPanel=  new JPanel();
		contentPane.add(mainPanel);
		mainTextArea = new JTextArea();
		mainTextArea.setLineWrap(true);
		mainTextArea.setEditable(false);
		mainTextArea.setText(string);
		mainPanel.add(mainTextArea);
		

        this.setMinimumSize(new Dimension(600,600));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
	}

}
