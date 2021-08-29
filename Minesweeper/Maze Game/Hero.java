import java.awt.Color;
import java.awt.GradientPaint;

public class Hero {
	
	private Location loc;
	private int size;
	private Color color;
	private int direction;
	
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	
	
	public Hero (Location loc, int size, Color color, int dir) {
		this.loc = loc;
		this.size = size;
		this.color = color;
		this.direction = dir;
	}
	
	public Color getColor () {
		return this.color;
	}
	
	public Location getLoc () {
		return this.loc;
	}
	
	public int getSize () {
		return this.size;
	}
	
	public int getDir () {
		return this.direction;
	}
	
	public void move (char[][] maze, int key) {
		int r = this.getLoc().getRow();
		int c = this.getLoc().getCol();
		
		if (key == 87 || key == 38) { // Moving Forwards
			if (this.direction == this.UP) {
				if (r > 0 && !(maze[r-1][c] == '#' || maze[r-1][c] == 'M' || maze[r-1][c] == 'G' || maze[r-1][c] == 'B')) {
					getLoc().setRow(-1);
				}
			}
			else if (this.direction == this.RIGHT) {
				if (c < maze[0].length-1 && !(maze[r][c+1] == '#' || maze[r][c+1] == 'M' || maze[r][c+1] == 'G' || maze[r][c+1] == 'B')) {
					getLoc().setCol(1);
				}
			}
			else if (this.direction == this.DOWN) {
				if (r < maze.length-1 && !(maze[r+1][c] == '#' || maze[r+1][c] == 'M' || maze[r+1][c] == 'G' || maze[r+1][c] == 'B')) {
					getLoc().setRow(1);
				}
			}
			else if (this.direction == this.LEFT) {
				if (c > 0 && !(maze[r][c-1] == '#' || maze[r][c-1] == 'M' || maze[r][c-1] == 'G' || maze[r][c-1] == 'B')) {
					getLoc().setCol(-1);
				}
			}
		}
		else if (key == 83 || key == 40) { //Moving Backwards
			if (this.direction == this.UP) {
				if (r < maze.length-1 && !(maze[r+1][c] == '#' || maze[r+1][c] == 'M' || maze[r+1][c] == 'B' || maze[r+1][c] == 'G')) {
					getLoc().setRow(1);
				}
			}
			else if (this.direction == this.RIGHT) {
				if (c > 0 && !(maze[r][c-1] == '#' || maze[r][c-1] == 'M' || maze[r][c-1] == 'B' || maze[r][c-1] == 'G')) {
					getLoc().setCol(-1);
				}
			}
			else if (this.direction == this.DOWN) {
				if (r > 0 && !(maze[r-1][c] == '#' || maze[r-1][c] == 'M' || maze[r-1][c] == 'B' || maze[r-1][c] == 'G')) {
					getLoc().setRow(-1);
				}
			}
			else if (this.direction == this.LEFT) {
				if (c < maze[0].length-1 && !(maze[r][c+1] == '#' || maze[r][c+1] == 'M' || maze[r][c+1] == 'B' || maze[r][c+1] == 'G')) {
					getLoc().setCol(1);
				}
			}
		}
		else if (key == 68 || key == 39) { //Turning Right
			if (this.direction == this.LEFT) {
				this.direction = this.UP;
			} else {
				this.direction++;
			}
		}
		else if (key == 65 || key == 37) { //Turning Left
			if (this.direction == this.UP) {
				this.direction = this.LEFT;
			} else {
				this.direction--;
			}
		}
	}
	
}
