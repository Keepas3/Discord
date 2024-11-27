package extra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Titlebar extends JPanel {
	public Titlebar() {
		setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Discord");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
       
        add(titleLabel, BorderLayout.CENTER);
       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
         

       // add(closeButton, BorderLayout.EAST);
//        JButton minimizedButton = new JButton("-");
//       // minimizedButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        minimizedButton.setBackground(Color.BLACK);
//        minimizedButton.setForeground(Color.WHITE);
//        minimizedButton.setFocusPainted(false);
//        minimizedButton.addActionListener(e -> minimizedWindow());
//        buttonPanel.add(minimizedButton);
        
        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0)); // Close the application
        buttonPanel.add(closeButton);
        
        add(buttonPanel, BorderLayout.EAST);
    }
//	public void minimizedWindow() {
//		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//        frame.setState(Frame.ICONIFIED);
//	}
}
