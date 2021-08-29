public class SpiralObj {
	private int d;
	private String[][] grid;
	
	public SpiralObj (int d) {
		this.d = d;
		this.grid = fillGrid(d);
	}
	
	public String[][] fillGrid (int d) {
		String[][] gr = new String[d][d];
		if (d > 0) {
			for (int i = 0; i < gr.length; i++) {
				for (int j = 0; j < gr[0].length; j++) {
					gr[i][j] = "-";
				}
			}
			int startR = 0;
			int endR = d-1;
			int startC = 0;
			int endC = d-1;
			while (startR <= endR && startC <= endC) {
				for (int c = startC; c <= endC; c++) {
					gr[startR][c] = "*";
				}
				startR++;
				if (startC >= 1) {
					startC++;
				}
				for (int r = startR; r <= endR; r++) {
					gr[r][endC] = "*";
				}
				startR++;
				endC--;
				for (int c = endC; c >= startC; c--) {
					gr[endR][c] = "*";
				}
				endC--;
				endR--;
				for (int r = endR; r >= startR; r--) {
					gr[r][startC] = "*";
				}
				endR--;
				startC++;
			}
			if (d%4 == 2) {
				gr[d/2][d/2-1] = "-";
			}
		} else {
			gr = null;
		}
		
		return gr;
	}
	
	public String toString () {
		String str = "";
		if (this.d > 0) {
			for (int i = 0; i < this.grid[0].length; i++) {
				for (int j = 0; j < this.grid.length; j++) {
					str = str.concat(this.grid[i][j] + " ");
				}
				str = str.concat("\n");
			}
		} else {
			str = " \n";
		}
		return str;
	}
}
