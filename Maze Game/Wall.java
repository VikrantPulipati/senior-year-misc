import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Polygon;

public class Wall {
	private int[] rows;
	private int[] cols;
	private GradientPaint paint;
	
	public Wall (int[] rows, int[] cols, GradientPaint paint) {
		this.rows = rows;
		this.cols = cols;
		this.paint = paint;
	}
	
	public Polygon getPoly () {
		Polygon wall = new Polygon(this.cols, this.rows, cols.length);
		return wall;
	}
	
	public GradientPaint getPaint () {
		return this.paint;
	}
	
	public void setPaint (int x1, int y1, int x2, int y2, Color col, int shrink, int fov, int dir) {
		int startR = col.getRed()-shrink*fov;
		int startG = col.getGreen()-shrink*fov;
		int startB = col.getBlue()-shrink*fov;
		int endR = col.getRed()-shrink*(fov+1);
		int endG = col.getGreen()-shrink*(fov+1);
		int endB = col.getBlue()-shrink*(fov+1);
		if (startR < 0) {
			startR = 0;
		}
		if (startG < 0) {
			startG = 0;
		}
		if (startB < 0) {
			startB = 0;
		}
		if (endR < 0) {
			endR = 0;
		}
		if (endG < 0) {
			endG = 0;
		}
		if (endB < 0) {
			endB = 0;
		}
		// dir == 0 : no gradient
		// dir == -1 : reverse
		// dir == 1 : normal
		if (dir == 1) {
			this.paint = new GradientPaint(x1, y1, new Color(startR, startG, startB), x2, y2, new Color(endR, endG, endB));
		}
		else if (dir == -1) {
			this.paint = new GradientPaint(x1, y1, new Color(endR, endG, endB), x2, y2, new Color(startR, startG, startB));
		}
		else if (dir == 0) {
			this.paint = new GradientPaint(x1, y1, new Color(endR, endG, endB), x2, y2, new Color(endR, endG, endB));
		}
	}
}
