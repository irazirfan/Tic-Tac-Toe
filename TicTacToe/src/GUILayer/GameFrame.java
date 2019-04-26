package GUILayer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {

	private JPanel pnlInfo,pnlGame;
	private JLabel lblP1,lblP2,lblStatus;
	private JTextField txtP1Name,txtP2Name,txtP1,txtP2;
	private JButton btnPlay,btnRestart,btnExit;
	int player = 1;
	private ArrayList<String> p1cells = new ArrayList<String>();
	private ArrayList<String> p2cells = new ArrayList<String>();
	int move = 0;
	int mode = 0;
	private ArrayList<JButton> btnGames = new ArrayList<JButton>();
	public Random rand = new Random();
	
	public GameFrame(int mode) {
		
		this.mode = mode;
		if(mode==1)
			this.setTitle("Tic Tac Toe MultiPlayer");
		else
			this.setTitle("Tic Tac Toe SinglPlayer");
		
		this.setSize(400, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.addComponent();
		this.addWindowListener(new WindowListener() {
	
				public void windowClosing(WindowEvent we) {
				
				MenuFrame hf = new MenuFrame();
				hf.setVisible(true);
				setVisible(false);
			}
				public void windowActivated(WindowEvent we) {
	
				}
				public void windowClosed(WindowEvent we) {
					
				}
				public void windowDeactivated(WindowEvent we) {
					
				}
				public void windowDeiconified(WindowEvent we) {
					
				}
				public void windowIconified(WindowEvent we) {
					
				}
				public void windowOpened(WindowEvent we) {
					
				}
		});
	}

	private void addComponent() {
		
		pnlInfo = new JPanel(null);
		pnlInfo.setBounds(5,5,385,100);
		pnlInfo.setBackground(Color.lightGray);
		add(pnlInfo);
		this.addInfoPanel();
		
		pnlGame = new JPanel(new GridLayout(3, 3, 2, 2));
		pnlGame.setBounds(5, 110, 385, 255);
		pnlGame.setBackground(Color.DARK_GRAY);
		add(pnlGame);		
	}
	
	private void addInfoPanel() {
		
		lblP1 = new JLabel("Player 1:");
		lblP1.setBounds(5,5,100,20);
		this.pnlInfo.add(lblP1);
		
		txtP1Name = new JTextField("Name");
		txtP1Name.setBounds(75, 5, 100, 20);
		this.pnlInfo.add(txtP1Name);
		
		txtP1 = new JTextField("X");
		txtP1.setBounds(180, 5, 30, 20);
		this.pnlInfo.add(txtP1);
		
		lblStatus = new JLabel("Play");
		lblStatus.setBounds(220, 5, 50, 20);
		this.pnlInfo.add(lblStatus);
		
		lblP2 = new JLabel("Player 2:");
		lblP2.setBounds(5,30,100,20);
		this.pnlInfo.add(lblP2);
		
		txtP2Name = new JTextField("Name");
		txtP2Name.setBounds(75, 30, 100, 20);
		this.pnlInfo.add(txtP2Name);
		
		txtP2 = new JTextField("O");
		txtP2.setBounds(180, 30, 30, 20);
		this.pnlInfo.add(txtP2);
		
		if (mode==-1) {
			
			txtP2.setEnabled(false);
			txtP2Name.setEnabled(false);
			txtP2Name.setText("Computer");
		}
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(5, 60, 70, 30);
		btnPlay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				if(txtP1Name.getText().isEmpty()||txtP2Name.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Enter Player name first");
					return;
				}
				else if(txtP1Name.getText().equals(txtP2Name.getText())) {
					
					JOptionPane.showMessageDialog(null, "Name cannot be same");
					return;
				}
				lblStatus.setVisible(true);
				txtP1.setEnabled(false);
				txtP2.setEnabled(false);
				txtP1Name.setEnabled(false);
				txtP2Name.setEnabled(false);
				btnPlay.setEnabled(false);
				
				addGamePanel();
				setVisible(true);                 // setVisible(true) for panel refreshing;
			}

		});
		this.pnlInfo.add(btnPlay);
		
		btnRestart = new JButton("Restart");
		btnRestart.setBounds(80, 60, 80, 30);
		btnRestart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {

				setVisible(false);
				GameFrame gf = new GameFrame(mode);
				gf.setVisible(true);
			}
		});
		this.pnlInfo.add(btnRestart);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(165, 60, 70, 30);
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {

				System.exit(0);
			}
		});
		this.pnlInfo.add(btnExit);
	}
	
	private void addGamePanel() {
		
		for(int i=1;i<=9;i++) {
			
			JButton btn = new JButton(i+"");
			btnGames.add(btn);
			btn.setFont(new Font(Font.SERIF, Font.BOLD, 20));
			btn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					
					move++;
					JButton btn = (JButton)ae.getSource();
					btn.setEnabled(false);
					btnGames.remove(btn);
					if(move==9) {
						
						lblStatus.setText("Match Draw");
					}
					if (player==1) {
						
						p1cells.add(btn.getText());
						btn.setText(txtP1.getText());
						if (move>4) {
							
							if(CheckWin()) {
								DisableAll();
								return;
							}
						}
						player = 2;
						lblStatus.setBounds(220, 30, 100, 20);
						if (mode==-1 && move!=9) {
							
							MoveByComputer();
							return;
						}
					}
					else {
						
						p2cells.add(btn.getText());
						btn.setText(txtP2.getText());
						
						if (move>4) {
							
							if(CheckWin()) {
								DisableAll();
								return;
							}							
						}
						player = 1;
						lblStatus.setBounds(220, 5, 100, 20);
					}
				}
			});
			this.pnlGame.add(btn);
		}
	}
	
	private void DisableAll() {
		
		for(JButton btn:btnGames) {
			
			btn.setEnabled(false);
		}
	}
	
	private void MoveByComputer() {

		try {
			
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		int index = rand.nextInt(btnGames.size()-1);
		btnGames.get(index).doClick();
		
	}
	
	private boolean CheckWin() {
		
		ArrayList<String> listToCheck = new ArrayList<String>();
		if (player==1) {
			
			listToCheck.addAll(p1cells);
		}
		else {
			
			listToCheck.addAll(p2cells);
		}
		
		if ((listToCheck.contains("1")&&listToCheck.contains("2")&&listToCheck.contains("3"))||
			(listToCheck.contains("4")&&listToCheck.contains("5")&&listToCheck.contains("6"))||
			(listToCheck.contains("7")&&listToCheck.contains("8")&&listToCheck.contains("9"))||
			(listToCheck.contains("1")&&listToCheck.contains("4")&&listToCheck.contains("7"))||
			(listToCheck.contains("2")&&listToCheck.contains("5")&&listToCheck.contains("8"))||
			(listToCheck.contains("3")&&listToCheck.contains("6")&&listToCheck.contains("9"))||
			(listToCheck.contains("1")&&listToCheck.contains("5")&&listToCheck.contains("9"))||
			(listToCheck.contains("3")&&listToCheck.contains("5")&&listToCheck.contains("7"))){
			
			if (player==1) {
				
				lblStatus.setText(txtP1Name.getText()+" wins");
			}
			
			else {
				
				lblStatus.setText(txtP2Name.getText()+" wins");
			}
			return true;
		}
		return false;
	}

}
