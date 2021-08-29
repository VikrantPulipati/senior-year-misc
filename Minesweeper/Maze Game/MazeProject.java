import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;


public class MazeProject extends JPanel implements KeyListener {
	
	JFrame frame;
	boolean draw3D = false;
	char[][] maze;
	int size = 13;
	Hero hero;
	int startRow = 1;
	int startCol = 0;
	int moves = 0;
	int endRow = 49;
	int endCol = 50;
	int paintLeft = 100;
	ArrayList<String> keyRing = new ArrayList<String>();
	boolean hasMagentaKey = false;
	boolean hasGreenKey = false;
	boolean hasBlueKey = false;
	String dir;
	ArrayList<Location> greenKeys = new ArrayList<Location>(
			Arrays.asList(
					new Location(41, 49), new Location(41, 45), new Location(39, 43), new Location(35, 49),
					new Location(29, 49), new Location(31, 45), new Location(27, 43), new Location(31, 39),
					new Location(31, 31), new Location(47, 39), new Location(43, 33), new Location(49, 33),
					new Location(39, 29), new Location(43, 27), new Location(41, 25), new Location(45, 23),
					new Location(39, 19), new Location(45, 17))
			);
	ArrayList<Location> blueKeys = new ArrayList<Location>(
			Arrays.asList(
					new Location(45, 43), new Location(43, 41), new Location(39, 35), new Location(35, 41),
					new Location(35, 37), new Location(25, 31), new Location(29, 13), new Location(27, 17),
					new Location(23, 23), new Location(21, 17), new Location(19, 41), new Location(19, 47),
					new Location(19, 49), new Location(15, 47), new Location(13, 41), new Location(15, 35),
					new Location(15, 29), new Location(17, 23), new Location(9, 47), new Location(9, 41),
					new Location(9, 29), new Location(5, 47), new Location(5, 39), new Location(3, 35),
					new Location(1, 49), new Location(1, 27), new Location(1, 17))
			);
	ArrayList<Location> magentaKeys = new ArrayList<Location>(
			Arrays.asList(
					new Location(9, 3), new Location(7, 7), new Location(3, 13), new Location(7, 15),
					new Location(5, 19), new Location(11, 25), new Location(13, 13), new Location(17, 3),
					new Location(19, 9), new Location(23, 1), new Location(25, 5), new Location(31, 3),
					new Location(33, 11), new Location(41, 1), new Location(43, 7), new Location(49, 1),
					new Location(49, 9), new Location(45, 15))
			);
	
	public MazeProject () {
		hero = new Hero(new Location(startRow, startCol), size, Color.RED, Hero.RIGHT);
		
		getMaze();
		
		frame = new JFrame("Maze Project");
		frame.add(this);
		frame.setSize(1280, 720);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	public void getMaze () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\maze");
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int rows = 0;
			int cols = 0;
			while ((text = input.readLine()) != null) {
				cols = text.length();
				rows++;
			}
			maze = new char[rows][cols];
			BufferedReader input2 = new BufferedReader(new FileReader(fileName));
			int r = 0;
			while ((text = input2.readLine()) != null) {
				for (int c = 0; c < text.length(); c++) {
					maze[r][c] = text.charAt(c);
				}
				r++;
			}
		} catch (IOException e) {
			System.err.println("Error: File not Found!");
		}
		
		int greenKey = (int)(Math.random()*greenKeys.size());
		int blueKey = (int)(Math.random()*blueKeys.size());
		int magentaKey = (int)(Math.random()*magentaKeys.size());
		maze[greenKeys.get(greenKey).getRow()][greenKeys.get(greenKey).getCol()] = 'g';
		maze[magentaKeys.get(magentaKey).getRow()][magentaKeys.get(magentaKey).getCol()] = 'm';
		maze[blueKeys.get(blueKey).getRow()][blueKeys.get(blueKey).getCol()] = 'b';
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1280, 720);
		
		g2.setColor(Color.GRAY);
		g2.setFont(new Font("Font", Font.PLAIN, 50));
		g2.drawString("Moves: " + moves, 715, 300);
		g2.drawString("Remaining Paint: " + paintLeft + "%", 715, 400);
		
		if (hero.getDir() == Hero.UP) {
			dir = "Up";
		}
		else if (hero.getDir() == Hero.RIGHT) {
			dir = "Right";
		}
		else if (hero.getDir() == Hero.DOWN) {
			dir = "Down";
		}
		else if (hero.getDir() == Hero.LEFT) {
			dir = "Left";
		}
		
		if (!draw3D) {
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 1280, 720);
			
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Font", Font.PLAIN, 50));
			g2.drawString("Location: " + hero.getLoc().getCol() + ", " + hero.getLoc().getRow(), 715, 50);
			g2.drawString("Keys Found:", 715, 150);
			if (hasMagentaKey) {
				g2.setColor(Color.MAGENTA);
				g2.fillRect(715, 175, 50, 50);
				
			}
			if (hasGreenKey) {
				g2.setColor(Color.GREEN);
				g2.fillRect(775, 175, 50, 50);
			}
			if (hasBlueKey) {
				g2.setColor(Color.BLUE);
				g2.fillRect(835, 175, 50, 50);
			}
			g2.setColor(Color.BLACK);
			g2.drawString("Direction: " + dir, 715, 300);
			g2.drawString("Moves: " + moves, 715, 400);
			g2.drawString("Remaining Paint: " + paintLeft + "%", 715, 500);
			
			for (int c = 0; c < maze[0].length; c++) {
				for (int r = 0; r < maze.length; r++) {
					if (maze[r][c] == ' ' || maze[r][c] == 'm' || maze[r][c] == 'g' || maze[r][c] == 'b') {
						g2.setColor(Color.BLACK);
						g2.drawRect(c*size+size, r*size+size, size, size);
					} 
					else if (maze[r][c] == 'F') {
						g2.setColor(Color.YELLOW);
						g2.fillRect(c*size+size, r*size+size, size, size);
					} 
					else if (maze[r][c] == 'B' /*|| maze[r][c] == 'b'*/) {
						g2.setColor(Color.BLUE);
						g2.fillRect(c*size+size, r*size+size, size, size);
					} 
					else if (maze[r][c] == 'G' /*|| maze[r][c] == 'g'*/) {
						g2.setColor(Color.GREEN);
						g2.fillRect(c*size+size, r*size+size, size, size);
					} 
					else if (maze[r][c] == 'M' /*|| maze[r][c] == 'm'*/) {
						g2.setColor(Color.MAGENTA);
						g2.fillRect(c*size+size, r*size+size, size, size);
					} else {
						g2.setColor(Color.BLACK);
						g2.fillRect(c*size+size, r*size+size, size, size);
					}
				}
			}
			g2.setColor(hero.getColor());
			g2.fillRect(hero.getLoc().getCol()*size+size, hero.getLoc().getRow()*size+size, size, size);
		} else {
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 1280, 720);
			
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Font", Font.PLAIN, 50));
			g2.drawString("Location: " + hero.getLoc().getCol() + ", " + hero.getLoc().getRow(), 715, 50);
			g2.drawString("Keys Found:", 715, 150);
			if (hasMagentaKey) {
				g2.setColor(Color.MAGENTA);
				g2.fillRect(715, 175, 50, 50);
				
			}
			if (hasGreenKey) {
				g2.setColor(Color.GREEN);
				g2.fillRect(775, 175, 50, 50);
			}
			if (hasBlueKey) {
				g2.setColor(Color.BLUE);
				g2.fillRect(835, 175, 50, 50);
			}
			g2.setColor(Color.WHITE);
			g2.drawString("Direction: " + dir, 715, 300);
			g2.drawString("Moves: " + moves, 715, 400);
			g2.drawString("Remaining Paint: " + paintLeft + "%", 715, 500);
			
			ArrayList<Wall> walls = getWalls();
			for (int i = 0; i < walls.size(); i++) {
				g2.setPaint(walls.get(i).getPaint());
				g2.fill(walls.get(i).getPoly());
				g2.setColor(Color.BLACK);
				g2.draw(walls.get(i).getPoly());
			}
		}
	}
	
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == 32) {
			draw3D = !draw3D;
		}
		
		int currentRow = hero.getLoc().getRow();
		int currentCol = hero.getLoc().getCol();
		
		hero.move(maze, e.getKeyCode());
		
		if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'm') {
			hasMagentaKey = true;
			maze[hero.getLoc().getRow()][hero.getLoc().getCol()] = ' ';
			repaint();
			JOptionPane.showMessageDialog(frame, "You found the Magenta Key!");
		}
		if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'g') {
			hasGreenKey = true;
			maze[hero.getLoc().getRow()][hero.getLoc().getCol()] = ' ';
			repaint();
			JOptionPane.showMessageDialog(frame, "You found the Green Key!");
		}
		if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'b') {
			hasBlueKey = true;
			maze[hero.getLoc().getRow()][hero.getLoc().getCol()] = ' ';
			repaint();
			JOptionPane.showMessageDialog(frame, "You found the Blue Key!");
		}
		if (e.getKeyCode() == 70) {
			if (paintLeft > 0 && maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == ' ' ||
					maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'm' ||
					maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'g' ||
					maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'b') {
				maze[hero.getLoc().getRow()][hero.getLoc().getCol()] = 'F';
				paintLeft -= 4;
			}
		}
		if (e.getKeyCode() == 69) {
			if (hero.getDir() == Hero.UP) {
				if (maze[hero.getLoc().getRow()-1][hero.getLoc().getCol()] == 'M') {
					if (hasMagentaKey) {
						maze[hero.getLoc().getRow()-1][hero.getLoc().getCol()] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()-1][hero.getLoc().getCol()] == 'G') {
					if (hasGreenKey) {
						maze[hero.getLoc().getRow()-1][hero.getLoc().getCol()] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()-1][hero.getLoc().getCol()] == 'B') {
					if (hasBlueKey) {
						maze[hero.getLoc().getRow()-1][hero.getLoc().getCol()] = ' ';
					}
				}
			}
			if (hero.getDir() == Hero.RIGHT) {
				if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()+1] == 'M') {
					if (hasMagentaKey) {
						maze[hero.getLoc().getRow()][hero.getLoc().getCol()+1] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()+1] == 'G') {
					if (hasGreenKey) {
						maze[hero.getLoc().getRow()][hero.getLoc().getCol()+1] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()+1] == 'B') {
					if (hasBlueKey) {
						maze[hero.getLoc().getRow()][hero.getLoc().getCol()+1] = ' ';
					}
				}
			}
			if (hero.getDir() == Hero.DOWN) {
				if (maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] == 'M') {
					if (hasMagentaKey) {
						maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] == 'G') {
					if (hasGreenKey) {
						maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] == 'B') {
					if (hasBlueKey) {
						maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] = ' ';
					}
				}
			}
			if (hero.getDir() == Hero.LEFT) {
				if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'M') {
					if (hasMagentaKey) {
						maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'G') {
					if (hasGreenKey) {
						maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] = ' ';
					}
				}
				if (maze[hero.getLoc().getRow()][hero.getLoc().getCol()] == 'B') {
					if (hasBlueKey) {
						maze[hero.getLoc().getRow()+1][hero.getLoc().getCol()] = ' ';
					}
				}
			}
		}
		if (!(hero.getLoc().getRow() == currentRow && hero.getLoc().getCol() == currentCol)) {
			moves++;
		}
		repaint();
		
		JButton b = new JButton("CLOSE");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();				
			}
			
		});
		
		if (hero.getLoc().getRow() == endRow && hero.getLoc().getCol() == endCol) {
			frame.dispose();
			JOptionPane.showMessageDialog(frame, "You've made it to the end!\nIt took you " + moves + " moves!");
		}
	}
	
	public void keyReleased (KeyEvent e) {
		
	}
	
	public void keyTyped (KeyEvent e) {
		
	}
	
	public ArrayList<Wall> getWalls () {
		ArrayList<Wall> walls = new ArrayList<Wall>(0);
		int r = hero.getLoc().getRow();
		int c = hero.getLoc().getCol();
		int x = 50;
		int y = 50;
		int shrink = 50;
		if (hero.getDir() == Hero.UP) {
			int fov;
			for (fov = 0; fov < 5; fov++) {
				if (r-fov >= 0 && !(maze[r-fov][c] == '#' || maze[r-fov][c] == 'M' || maze[r-fov][c] == 'G' || maze[r-fov][c] == 'B')) {
					//build ceiling
					int[] ceilingCols = {x+shrink*fov, 650-shrink*fov, 650-shrink*(fov+1), x+shrink*(fov+1)};
					int[] ceilingRows = {y+shrink*fov, y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
					GradientPaint colorCeiling = new GradientPaint(325, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(ceilingRows, ceilingCols, colorCeiling));
					if (maze[r-fov][c] == 'F') {
						walls.get(walls.size()-1).setPaint(325, y+shrink*fov, 325, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					
					//build floor
					int[] floorCols = {x+shrink*fov, x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					int[] floorRows = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					GradientPaint colorFloor = new GradientPaint(325, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(floorRows, floorCols, colorFloor));
					if (maze[r-fov][c] == 'F') {
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					if (maze[r-fov][c] == 'm') {
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
					}
					if (maze[r-fov][c] == 'g') {
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
					}
					if (maze[r-fov][c] == 'b') {
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
					}
					
					//build left
					if (c-1 >= 0) {
						if (maze[r-fov][c-1] == '#' || maze[r-fov][c-1] == 'M' || maze[r-fov][c-1] == 'G' || maze[r-fov][c-1] == 'B') {
							int[] cols = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorLeft = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorLeft));
							if (maze[r-fov][c] == 'F') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-fov][c-1] == 'M') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-fov][c-1] == 'G') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-fov][c-1] == 'B') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
							
						} else {
							int[] cols1 = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall leftWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {x+shrink*fov, x+shrink*(fov+1), x+shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(x+shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topLeftCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {x+shrink*fov, x+shrink*fov, x+shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(x+shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomLeftCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r-fov][c-1] == 'F') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topLeftCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-fov][c-1] == 'm') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-fov][c-1] == 'g') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-fov][c-1] == 'b') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r-fov-1][c-1] == 'M') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r-fov-1][c-1] == 'G') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 0);
							}
							if (maze[r-fov-1][c-1] == 'B') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 0);
							}
							walls.add(leftWall);
							walls.add(topLeftCorner);
							walls.add(bottomLeftCorner);
						}
					}
					//build right
					if (c+1 < maze[0].length) {
						if (maze[r-fov][c+1] == '#' || maze[r-fov][c+1] == 'M' || maze[r-fov][c+1] == 'G' || maze[r-fov][c+1] == 'B') {
							int[] cols = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorRight = new GradientPaint(650-shrink*(fov) , 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorRight));
							if (maze[r-fov][c] == 'F') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-fov][c+1] == 'M') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-fov][c+1] == 'G') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-fov][c+1] == 'B') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(650-shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall rightWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(650-shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topRightCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {650-shrink*fov, 650-shrink*fov, 650-shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(650-shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomRightCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r-fov][c+1] == 'F') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topRightCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-fov][c+1] == 'm') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-fov][c+1] == 'g') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-fov][c+1] == 'b') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r-fov-1][c+1] == 'M') {
								rightWall.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-fov-1][c+1] == 'G') {
								rightWall.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-fov-1][c+1] == 'B') {
								rightWall.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							walls.add(rightWall);
							walls.add(topRightCorner);
							walls.add(bottomRightCorner);
						}
					}
					
					//build back wall
					if (r-fov-1 >= 0) {
						if (maze[r-fov-1][c] == '#' || maze[r-fov-1][c] == 'M' || maze[r-fov-1][c] == 'G' || maze[r-fov-1][c] == 'B') {
							int[] cols = {x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1), x+shrink*(fov+1)};
							int[] rows = {y+shrink*(fov+1), y+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBackWall = new GradientPaint(325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorBackWall));
							if (maze[r-fov][c] == 'F') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 0);
							}
							if (maze[r-fov-1][c] == 'M') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r-fov-1][c] == 'G') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 0);
							}
							if (maze[r-fov-1][c] == 'B') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 0);
							}
						}
					}
					
				} else {			
					break;
				}
			}
		}
		else if (hero.getDir() == Hero.RIGHT) {
			int fov;
			for (fov = 0; fov < 5; fov++) {
				if (c+fov < maze[0].length && !(maze[r][c+fov] == '#' || maze[r][c+fov] == 'M' || maze[r][c+fov] == 'G' || maze[r][c+fov] == 'B')) {
					//build ceiling
					int[] ceilingCols = {x+shrink*fov, 650-shrink*fov, 650-shrink*(fov+1), x+shrink*(fov+1)};
					int[] ceilingRows = {y+shrink*fov, y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
					GradientPaint colorCeiling = new GradientPaint(325, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(ceilingRows, ceilingCols, colorCeiling));
					if (maze[r][c+fov] == 'F') {
						walls.get(walls.size()-1).setPaint(325, y+shrink*fov, 325, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					
					//build floor
					int[] floorCols = {x+shrink*fov, x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					int[] floorRows = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					GradientPaint colorFloor = new GradientPaint(325, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(floorRows, floorCols, colorFloor));
					if (maze[r][c+fov] == 'F') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					if (maze[r][c+fov] == 'm') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
					}
					if (maze[r][c+fov] == 'g') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
					}
					if (maze[r][c+fov] == 'b') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
					}
					//build left
					if (r-1 >= 0) {
						if (maze[r-1][c+fov] == '#' || maze[r-1][c+fov] == 'M' || maze[r-1][c+fov] == 'G' || maze[r-1][c+fov] == 'B') {
							int[] cols = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorLeft = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorLeft));
							if (maze[r][c+fov] == 'F') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-1][c+fov] == 'M') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-1][c+fov] == 'G') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-1][c+fov] == 'B') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall leftWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {x+shrink*fov, x+shrink*(fov+1), x+shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(x+shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topLeftCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {x+shrink*fov, x+shrink*fov, x+shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(x+shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomLeftCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r-1][c+fov] == 'F') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topLeftCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-1][c+fov] == 'm') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-1][c+fov] == 'g') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-1][c+fov] == 'b') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r-1][c+fov+1] == 'M') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r-1][c+fov+1] == 'G') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 0);
							}
							if (maze[r-1][c+fov+1] == 'B') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 0);
							}
							walls.add(leftWall);
							walls.add(topLeftCorner);
							walls.add(bottomLeftCorner);
						}
					}
					//build right
					if (r+1 < maze.length) {
						if (maze[r+1][c+fov] == '#' || maze[r+1][c+fov] == 'M' || maze[r+1][c+fov] == 'G' || maze[r+1][c+fov] == 'B') {
							int[] cols = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorRight = new GradientPaint(650-shrink*(fov) , 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorRight));
							if (maze[r][c+fov] == 'F') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+1][c+fov] == 'M') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+1][c+fov] == 'G') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+1][c+fov] == 'B') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(650-shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall rightWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(650-shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topRightCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {650-shrink*fov, 650-shrink*fov, 650-shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(650-shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomRightCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r+1][c+fov] == 'F') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topRightCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+1][c+fov] == 'm') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+1][c+fov] == 'g') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+1][c+fov] == 'b') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r+1][c+fov+1] == 'm') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 0);							
							}
							if (maze[r+1][c+fov+1] == 'g') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 0);						
							}
							if (maze[r+1][c+fov+1] == 'b') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 0);
							}
							walls.add(rightWall);
							walls.add(topRightCorner);
							walls.add(bottomRightCorner);
						}
					}
					//build back wall
					if (c+fov+1 < maze[0].length) {
						if (maze[r][c+fov+1] == '#' || maze[r][c+fov+1] == 'M' || maze[r][c+fov+1] == 'G' || maze[r][c+fov+1] == 'B') {
							int[] cols = {x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1), x+shrink*(fov+1)};
							int[] rows = {y+shrink*(fov+1), y+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBackWall = new GradientPaint(325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorBackWall));
							if (maze[r][c+fov] == 'F') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 0);
							}
							if (maze[r][c+fov+1] == 'M') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r][c+fov+1] == 'G') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 0);
							}
							if (maze[r][c+fov+1] == 'B') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 0);
							}
						}
					}
					
				} else {					
					break;
				}
			}
		}
		else if (hero.getDir() == Hero.DOWN) {
			int fov;
			for (fov = 0; fov < 5; fov++) {
				if (r+fov < maze.length && !(maze[r+fov][c] == '#' || maze[r+fov][c] == 'M' || maze[r+fov][c] == 'G' || maze[r+fov][c] == 'B')) {
					//build ceiling
					int[] ceilingCols = {x+shrink*fov, 650-shrink*fov, 650-shrink*(fov+1), x+shrink*(fov+1)};
					int[] ceilingRows = {y+shrink*fov, y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
					GradientPaint colorCeiling = new GradientPaint(325, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(ceilingRows, ceilingCols, colorCeiling));
					if (maze[r+fov][c] == 'F') {
						walls.get(walls.size()-1).setPaint(325, y+shrink*fov, 325, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					
					//build floor
					int[] floorCols = {x+shrink*fov, x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					int[] floorRows = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					GradientPaint colorFloor = new GradientPaint(325, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(floorRows, floorCols, colorFloor));
					if (maze[r+fov][c] == 'F') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					if (maze[r+fov][c] == 'm') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
					}
					if (maze[r+fov][c] == 'g') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
					}
					if (maze[r+fov][c] == 'b') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
					}
					
					//build left
					if (c+1 < maze[0].length) {
						if (maze[r+fov][c+1] == '#' || maze[r+fov][c+1] == 'M' || maze[r+fov][c+1] == 'G' || maze[r+fov][c+1] == 'B') {
							int[] cols = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorLeft = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorLeft));
							if (maze[r+fov][c] == 'F') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+fov][c+1] == 'M') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+fov][c+1] == 'G') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+fov][c+1] == 'B') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall leftWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {x+shrink*fov, x+shrink*(fov+1), x+shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(x+shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topLeftCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {x+shrink*fov, x+shrink*fov, x+shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(x+shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomLeftCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r+fov][c+1] == 'F') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topLeftCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+fov][c+1] == 'm') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+fov][c+1] == 'g') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+fov][c+1] == 'b') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r+fov+1][c+1] == 'M') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r+fov+1][c+1] == 'G') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 0);
							}
							if (maze[r+fov+1][c+1] == 'B') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 0);
							}
							walls.add(leftWall);
							walls.add(topLeftCorner);
							walls.add(bottomLeftCorner);
						}
					}
					//build right
					if (c-1 >= 0) {
						if (maze[r+fov][c-1] == '#' || maze[r+fov][c-1] == 'M' || maze[r+fov][c-1] == 'G' || maze[r+fov][c-1] == 'B') {
							int[] cols = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorRight = new GradientPaint(650-shrink*(fov) , 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorRight));
							if (maze[r+fov][c] == 'F') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+fov][c-1] == 'M') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+fov][c-1] == 'G') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+fov][c-1] == 'B') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(650-shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall rightWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(650-shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topRightCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {650-shrink*fov, 650-shrink*fov, 650-shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(650-shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomRightCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r+fov][c-1] == 'F') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topRightCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+fov][c-1] == 'm') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+fov][c-1] == 'g') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+fov][c-1] == 'b') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r+fov+1][c-1] == 'M') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r+fov+1][c-1] == 'G') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 0);
							}
							if (maze[r+fov+1][c-1] == 'B') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 0);
							}
							walls.add(rightWall);
							walls.add(topRightCorner);
							walls.add(bottomRightCorner);
						}
					}
					
					//build back wall
					if (r+fov+1 < maze.length) {
						if (maze[r+fov+1][c] == '#' || maze[r+fov+1][c] == 'M' || maze[r+fov+1][c] == 'G' || maze[r+fov+1][c] == 'B') {
							int[] cols = {x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1), x+shrink*(fov+1)};
							int[] rows = {y+shrink*(fov+1), y+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBackWall = new GradientPaint(325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorBackWall));
							if (maze[r+fov][c] == 'F') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 0);
							}
							if (maze[r+fov+1][c] == 'M') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r+fov+1][c] == 'G') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 0);
							}
							if (maze[r+fov+1][c] == 'B') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 0);
							}
						}
					}
					
				} else {
					break;
				}
			}
		}
		else if (hero.getDir() == Hero.LEFT) {
			int fov;
			for (fov = 0; fov < 5; fov++) {
				if (c-fov >= 0 && !(maze[r][c-fov] == '#' || maze[r][c-fov] == 'M' || maze[r][c-fov] == 'G' || maze[r][c-fov] == 'B')) {
					//build ceiling
					int[] ceilingCols = {x+shrink*fov, 650-shrink*fov, 650-shrink*(fov+1), x+shrink*(fov+1)};
					int[] ceilingRows = {y+shrink*fov, y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
					GradientPaint colorCeiling = new GradientPaint(325, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(ceilingRows, ceilingCols, colorCeiling));
					if (maze[r][c-fov] == 'F') {
						walls.get(walls.size()-1).setPaint(325, y+shrink*fov, 325, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					
					//build floor
					int[] floorCols = {x+shrink*fov, x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					int[] floorRows = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
					GradientPaint colorFloor = new GradientPaint(325, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
					walls.add(new Wall(floorRows, floorCols, colorFloor));
					if (maze[r][c-fov] == 'F') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
					}
					if (maze[r][c-fov] == 'm') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
					}
					if (maze[r][c-fov] == 'g') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
					}
					if (maze[r][c-fov] == 'b') { 
						walls.get(walls.size()-1).setPaint(325, 650-shrink*fov, 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
					}
					
					//build left
					if (r+1 < maze.length) {
						if (maze[r+1][c-fov] == '#' || maze[r+1][c-fov] == 'M' || maze[r+1][c-fov] == 'G' || maze[r+1][c-fov] == 'B') {
							int[] cols = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorLeft = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorLeft));
							if (maze[r][c-fov] == 'F') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+1][c-fov] == 'M') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+1][c-fov] == 'G') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+1][c-fov] == 'B') {
								walls.get(walls.size()-1).setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {x+shrink*(fov), x+shrink*(fov+1), x+shrink*(fov+1), x+shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(x+shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), x+shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall leftWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {x+shrink*fov, x+shrink*(fov+1), x+shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(x+shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topLeftCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {x+shrink*fov, x+shrink*fov, x+shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(x+shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), x+shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomLeftCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r+1][c-fov] == 'F') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topLeftCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r+1][c-fov] == 'm') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r+1][c-fov] == 'g') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r+1][c-fov] == 'b') {
								bottomLeftCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r+1][c-fov-1] == 'M') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
							}
							if (maze[r+1][c-fov-1] == 'G') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
							}
							if (maze[r+1][c-fov-1] == 'B') {
								leftWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
							}
							walls.add(leftWall);
							walls.add(topLeftCorner);
							walls.add(bottomLeftCorner);
						}
					}
					//build right
					if (r-1 >= 0) {
						if (maze[r-1][c-fov] == '#' || maze[r-1][c-fov] == 'M' || maze[r-1][c-fov] == 'G' || maze[r-1][c-fov] == 'B') {
							int[] cols = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows = {y+(shrink*fov), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*fov)};
							GradientPaint colorRight = new GradientPaint(650-shrink*(fov) , 325, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorRight));
							if (maze[r][c-fov] == 'F') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-1][c-fov] == 'M') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-1][c-fov] == 'G') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-1][c-fov] == 'B') {
								walls.get(walls.size()-1).setPaint(650-shrink*fov, 325, 650-shrink*(fov+1), 325, Color.BLUE, shrink, fov, 1);
							}
						} else {
							int[] cols1 = {650-shrink*(fov), 650-shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov)};
							int[] rows1 = {y+(shrink*(fov+1)), y+(shrink*(fov+1)), 650-(shrink*(fov+1)), 650-(shrink*(fov+1))};
							GradientPaint colorCornerWall = new GradientPaint(650-shrink*fov, 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 650-shrink*(fov+1), 325, new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall rightWall = new Wall(rows1, cols1, colorCornerWall);
							int[] cols2 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*fov};
							int[] rows2 = {y+shrink*fov, y+shrink*(fov+1), y+shrink*(fov+1)};
							GradientPaint colorTopCorner = new GradientPaint(650-shrink*fov, y+shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall topRightCorner = new Wall(rows2, cols2, colorTopCorner);
							int[] cols3 = {650-shrink*fov, 650-shrink*fov, 650-shrink*(fov+1)};
							int[] rows3 = {650-shrink*fov, 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBottomCorner = new GradientPaint(650-shrink*fov, 650-shrink*fov, new Color(255-shrink*fov, 255-shrink*fov, 255-shrink*fov), 650-shrink*fov, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							Wall bottomRightCorner = new Wall(rows3, cols3, colorBottomCorner);
							if (maze[r-1][c-fov] == 'F') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.YELLOW, shrink, fov, 0);
								topRightCorner.setPaint(x+shrink*fov, y+shrink*fov, x+shrink*fov, y+shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 1);
							}
							if (maze[r-1][c-fov] == 'm') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 1);
							}
							if (maze[r-1][c-fov] == 'g') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 1);
							}
							if (maze[r-1][c-fov] == 'b') {
								bottomRightCorner.setPaint(x+shrink*fov, 650-shrink*fov, x+shrink*fov, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 1);
							}
							if (maze[r-1][c-fov-1] == 'M') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r-1][c-fov-1] == 'G') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.GREEN, shrink, fov, 0);
							}
							if (maze[r-1][c-fov-1] == 'B') {
								rightWall.setPaint(x+shrink*fov, 325, x+shrink*(fov+1), 325, Color.BLUE, shrink, fov, 0);
							}
							walls.add(rightWall);
							walls.add(topRightCorner);
							walls.add(bottomRightCorner);
						}
					}
					
					//build back wall
					if (c-fov-1 >= 0) {
						if (maze[r][c-fov-1] == '#' || maze[r][c-fov-1] == 'M' || maze[r][c-fov-1] == 'G' || maze[r][c-fov-1] == 'B') {
							int[] cols = {x+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1), x+shrink*(fov+1)};
							int[] rows = {y+shrink*(fov+1), y+shrink*(fov+1), 650-shrink*(fov+1), 650-shrink*(fov+1)};
							GradientPaint colorBackWall = new GradientPaint(325, y+shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)), 325, 650-shrink*(fov+1), new Color(255-shrink*(fov+1), 255-shrink*(fov+1), 255-shrink*(fov+1)));
							walls.add(new Wall(rows, cols, colorBackWall));
							if (maze[r][c-fov] == 'F') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.YELLOW, shrink, fov, 0);
							}
							if (maze[r][c-fov-1] == 'M') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.MAGENTA, shrink, fov, 0);
							}
							if (maze[r][c-fov-1] == 'G') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.GREEN, shrink, fov, 0);
							}
							if (maze[r][c-fov-1] == 'B') {
								walls.get(walls.size()-1).setPaint(325, y+shrink*(fov+1), 325, 650-shrink*(fov+1), Color.BLUE, shrink, fov, 0);
							}							
						}
					}
					
				} else {
					break;
				}
			}
		}
		
		return walls;
	}
	
	public static void main (String[] args) {
		MazeProject app = new MazeProject();
	}
}
