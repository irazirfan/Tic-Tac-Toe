package GUILayer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuFrame extends JFrame {

	private JButton btnMulti,btnSingle;
	
	public MenuFrame() {
		
		super("Tic Tac Toe");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new GridLayout());
		this.addComponent();
	}

	private void addComponent() {
		
		btnSingle = new JButton("SinglePlayer");
		btnSingle.setFont(new Font("Serif", Font.BOLD,30));
		btnSingle.setBackground(Color.cyan);
		btnSingle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				GameFrame sp = new GameFrame(-1);
				sp.setVisible(true);
				setVisible(false);
			}
		});
		this.add(btnSingle);
		
		btnMulti = new JButton("MultiPlayer");
		btnMulti.setFont(new Font("Serif", Font.BOLD,30));
		btnMulti.setBackground(Color.CYAN);
		btnMulti.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				GameFrame mp = new GameFrame(1);
				mp.setVisible(true);
				setVisible(false);
			}
		});
		this.add(btnMulti);
	}
	
}
