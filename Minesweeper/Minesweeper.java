import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;

import javax.swing.*;

public class Minesweeper extends JFrame implements ActionListener, MouseListener {
	
	JToggleButton[][] board;
	JPanel boardPanel;
	boolean firstClick = true, gameOn = true;
	int numMines;
	JButton reset;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem beginner, intermediate, expert, insanity;
	ImageIcon mineIcon, flagIcon;
	ImageIcon smileIcon, loseIcon, winIcon, waitIcon;
	ImageIcon[] numbers;
	GraphicsEnvironment ge;
	Font timerFont;
	
	Timer timer;
	JTextField timeField;
	int timePassed;
	
	int dimR, dimC;
	
	public Minesweeper () {
		try {
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			timerFont = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\timer.ttf"));
			ge.registerFont(timerFont);
		} catch (IOException|FontFormatException e) {}
		
		numMines = 10;
		
		mineIcon = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\mine.png");
		mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		numbers = new ImageIcon[8];
		for (int i = 0; i < 8; i++) {
			numbers[i] = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\" + (i+1) + ".png");
			numbers[i] = new ImageIcon(numbers[i].getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		}
		
		flagIcon = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\flag.png");
		flagIcon = new ImageIcon(flagIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		beginner = new JMenuItem("Beginner");
		beginner.addActionListener(this);
		//beginner.setFont(mineFont);
		
		intermediate = new JMenuItem("Intermediate");
		intermediate.addActionListener(this);
		//intermediate.setFont(mineFont);
		
		expert = new JMenuItem("Expert");
		expert.addActionListener(this);
		//expert.setFont(mineFont);
		
		insanity = new JMenuItem("Insanity");
		insanity.addActionListener(this);
		//insanity.setFont(mineFont);
		
		reset = new JButton();
		
		smileIcon = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\smile.png");
		smileIcon = new ImageIcon(smileIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

		winIcon = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\win.png");
		winIcon = new ImageIcon(winIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		waitIcon = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\wait.png");
		waitIcon = new ImageIcon(waitIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		loseIcon = new ImageIcon("D:\\Data Structures\\Data Structures Lessons\\src\\Minesweeper Graphics\\lose.png");
		loseIcon = new ImageIcon(loseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		reset.setIcon(smileIcon);
		reset.addMouseListener(this);
		reset.addActionListener(this);
		
		timeField = new JTextField();
		timeField.setFont(timerFont.deriveFont(18.0f));
		timeField.setEditable(false);
		
		menu = new JMenu("Difficulty");
		menu.add(beginner);
		menu.add(intermediate);
		menu.add(expert);
		menu.add(insanity);
		
		menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.add(reset);
		menuBar.add(timeField);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		dimR = 9;
		dimC = 9;
		createBoard(dimR, dimC);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void createBoard(int rows, int cols) {
		if (boardPanel != null) {
			this.remove(boardPanel);
		}
		boardPanel = new JPanel();
		board = new JToggleButton[rows][cols];
		boardPanel.setLayout(new GridLayout(rows, cols));
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				board[r][c] = new JToggleButton();
				board[r][c].putClientProperty("row", r);
				board[r][c].putClientProperty("column", c);
				board[r][c].putClientProperty("state", 0);
				board[r][c].setBorder(BorderFactory.createBevelBorder(0));
				
				//board[r][c].setFont(mineFont.deriveFont(16f));
				board[r][c].setFocusPainted(false);
				board[r][c].addMouseListener(this);
				boardPanel.add(board[r][c]);
			}
		}
		
		this.add(boardPanel);
		this.setSize(cols*40, rows*40);
		
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == beginner) {
			numMines = 10;
			gameOn = true;
			firstClick = true;
			dimR = 9;
			dimC = 9;
			reset.setIcon(smileIcon);
			createBoard(dimR, dimC);
		}
		if (e.getSource() == intermediate) {
			numMines = 40;
			gameOn = true;
			firstClick = true;
			dimR = 16;
			dimC = 16;
			reset.setIcon(smileIcon);
			createBoard(dimR, dimC);
		}
		if (e.getSource() == expert) {
			numMines = 99;
			gameOn = true;
			firstClick = true;
			dimR = 16;
			dimC = 30;
			reset.setIcon(smileIcon);
			createBoard(dimR, dimC);
		}
		if (e.getSource() == insanity) {
			numMines = 150;
			gameOn = true;
			firstClick = true;
			dimR = 20;
			dimC = 35;
			reset.setIcon(smileIcon);
			createBoard(dimR, dimC);
		}
		if (e.getSource() == reset) {
			timePassed = 0;
			printTimer();
			if (timer != null) {
				timer.cancel();
			}
			
			firstClick = true;
			gameOn = true;
			createBoard(dimR, dimC);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() instanceof JToggleButton) {
			int row = (int) ((JToggleButton)e.getComponent()).getClientProperty("row");
			int col = (int) ((JToggleButton)e.getComponent()).getClientProperty("column");
			
			if (gameOn) {
				if (e.getButton() == MouseEvent.BUTTON1 && board[row][col].isEnabled()) {
					if (firstClick) {
						setMinesAndCounts(row, col);
						firstClick = false;
						timer = new Timer();
						timer.schedule(new UpdateTimer(), 0, 1000);
					}
					
					int state = (int)board[row][col].getClientProperty("state");
					if (state == -1) {
						gameOn = false;
						board[row][col].setContentAreaFilled(false);
						board[row][col].setOpaque(true);
						board[row][col].setBackground(Color.RED);
						board[row][col].setIcon(mineIcon);
						revealMines();
						
						reset.setIcon(loseIcon);
						timer.cancel();
					} else {
						expand(row, col);
						checkWin();
					}
		 		}
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (gameOn) {
						if (!board[row][col].isSelected()) {
							if (board[row][col].getIcon() == null) {
								board[row][col].setIcon(flagIcon);
								board[row][col].setDisabledIcon(flagIcon);
								board[row][col].setEnabled(false);
							} 
							else if (board[row][col].getIcon() != null) {
								board[row][col].setIcon(null);
								board[row][col].setDisabledIcon(null);
								board[row][col].setEnabled(true);
							}
						}
					}
				}
			}
		}
		if (e.getSource() == reset) {
			reset.setIcon(smileIcon);
		}
	}
	
	public void revealMines () {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				int state = (int)board[r][c].getClientProperty("state");
				if (state == -1) {
					board[r][c].setIcon(mineIcon);
					board[r][c].setDisabledIcon(mineIcon);
				}
				board[r][c].setEnabled(false);
			}
		}
	}
	
	public void setMinesAndCounts(int currRow, int currCol) {
		int count = numMines;
		int dimR = board.length;
		int dimC = board[0].length;
		while (count > 0) {
			int randR = (int)(Math.random()*dimR);
			int randC = (int)(Math.random()*dimC);
			int state = (int)((JToggleButton)board[randR][randC]).getClientProperty("state");
			
			if (state != -1 && (Math.abs(randR-currRow) > 1 || Math.abs(randC-currCol) > 1)) {
				board[randR][randC].putClientProperty("state", -1);
				count--;
			}
		}
		
		for (int r = 0; r < dimR; r++) {	//current counting position
			for (int c = 0; c < dimC; c++) {
				int state = (int)((JToggleButton)board[r][c]).getClientProperty("state");
				if (state != -1) {
					count = 0;
					for (int r33 = r-1;  r33 <= r+1; r33++) {	//3x3 window around current counting position
						for (int c33 = c-1;  c33 <= c+1; c33++) {
							try {
								state = (int)((JToggleButton)board[r33][c33]).getClientProperty("state");
								if (state == -1) {
									count++;
								}
							} catch (ArrayIndexOutOfBoundsException e) {}
						}
					}
					board[r][c].putClientProperty("state", count);
				}
				//state = (int)((JToggleButton)board[r][c]).getClientProperty("state");
				//write(r, c, state);
			}
		}
	}
	
	public void expand (int row, int col) {
		if (!board[row][col].isSelected()) {
			board[row][col].setSelected(true);
		}
		int state = (int)board[row][col].getClientProperty("state");
		if (state > 0) {
			write(row, col, state);
		} else {
			for (int r33 = row - 1; r33 <= row + 1; r33++) {
				for (int c33 = col - 1; c33 <= col + 1; c33++) {
					try {
						if (!board[r33][c33].isSelected()) {
							expand(r33, c33);
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
	}
	
	public void checkWin () {
		int dimR = board.length;
		int dimC = board[0].length;
		int totalSpaces = dimR*dimC;
		int count = 0;
		for (int r = 0; r < dimR; r++) {
			for (int c = 0; c < dimC; c++) {
				int state = (int)board[r][c].getClientProperty("state");
				if (board[r][c].isSelected() && state != -1) {
					count++;
				}
			}
		}
		if (numMines == totalSpaces-count) {
			gameOn = false;
			reset.setIcon(winIcon);
			timer.cancel();
		}
	}
	
	public void printTimer () {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		timeField.setText(f.format(new Date(timePassed*1000+18000000)));
	}
	
	public void write (int row, int col, int state) {
		switch(state) {
			case 1:
				board[row][col].setForeground(Color.BLUE);
				break;
			case 2:
				board[row][col].setForeground(Color.GREEN);
				break;
			case 3:
				board[row][col].setForeground(Color.RED);
				break;
			case 4:
				board[row][col].setForeground(new Color(128, 0, 128));
				break;
			case 5:
				board[row][col].setForeground(new Color(128, 0, 0));
				break;
			case 6:
				board[row][col].setForeground(Color.CYAN);
				break;
			case 7:
				board[row][col].setForeground(Color.BLACK);
				break;
			case 8:
				board[row][col].setForeground(Color.GRAY);
				break;
		}
		if (state > 0) {
			//board[row][col].setText("" + state);
			board[row][col].setIcon(numbers[state-1]);
			board[row][col].setDisabledIcon(numbers[state-1]);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gameOn && e.getSource() == reset) {
			reset.setIcon(waitIcon);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main (String[] args) {
		Minesweeper app = new Minesweeper();
	}
	
	public class UpdateTimer extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			timePassed++;
			printTimer();
		}
		
	}
}
