import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITask extends JPanel implements ActionListener {
	
	JFrame frame;
	JMenuBar menuBar;
	JPanel buttonPanel, bigPanel;
	GridLayout buttonLayout, bigLayout;
	JButton north, south, east, west, reset;
	JMenu fontOptions, fontSizeOptions, tColorOptions, bColorOptions, oColorOptions;
	JMenuItem[] fontOption, fontSizeOption, tColorOption, bColorOption, oColorOption;
	String[] fontNames, tColorNames,bColorNames, oColorNames;
	JTextArea textArea;
	Font currFont;
	int currFontSize;
	Font[] fonts;
	int[] fontSizes;
	Color[] bColors, tColors, oColors;
	Color currBColor, currTColor, currOColor, currTBColor;
	
	final Color B_COLOR = Color.WHITE, T_COLOR = Color.BLACK, O_COLOR = Color.MAGENTA;
	final int FONT_SIZE = 15;
	final Font FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
	
	
	public GUITask () {
		
		currFont = FONT;
		currFontSize = FONT_SIZE;
		currBColor = B_COLOR;
		currTColor = T_COLOR;
		currOColor = O_COLOR;
		
		frame = new JFrame("GUI Project");
		frame.setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		fontOptions = new JMenu("Font");
		fontOptions.setFont(FONT);
		fontSizeOptions = new JMenu("Font Size");
		fontSizeOptions.setFont(FONT);
		tColorOptions = new JMenu("Font Color");
		tColorOptions.setFont(FONT);
		bColorOptions = new JMenu("Background");
		bColorOptions.setFont(FONT);
		oColorOptions = new JMenu("Outline");
		oColorOptions.setFont(FONT);
		
		fontOption = new JMenuItem[3];
		fontSizeOption = new JMenuItem[3];
		tColorOption = new JMenuItem[3];
		bColorOption = new JMenuItem[3];
		oColorOption = new JMenuItem[4];
		
		menuBar.setLayout(new GridLayout(1, 6));
		menuBar.add(fontOptions);
		menuBar.add(fontSizeOptions);
		menuBar.add(tColorOptions);
		menuBar.add(bColorOptions);
		menuBar.add(oColorOptions);
		
		fontNames = new String[] {"Arial", "Times New Roman", "Comic Sans MS"};
		tColorNames = new String[] {"Black", "Red", "Random"};
		bColorNames = new String[] {"White", "Yellow", "Random"};
		oColorNames = new String[] {"No Color", "Magenta", "Blue", "Random"};
		
		fonts = new Font[3];
		fontSizes = new int[] {10, 15, 26};
		
		for (int i = 0; i < fontNames.length; i++) {
			fonts[i] = new Font(fontNames[i], Font.PLAIN, currFontSize);
			fontOption[i] = new JMenuItem(fontNames[i]);
			fontOption[i].setFont(fonts[i]);
			fontOption[i].setForeground(T_COLOR);
			fontOption[i].addActionListener(this);
			fontOptions.add(fontOption[i]);
		}
		
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizeOption[i] = new JMenuItem(String.valueOf(fontSizes[i]));
			fontSizeOption[i].setFont(FONT);
			fontSizeOption[i].addActionListener(this);
			fontSizeOptions.add(fontSizeOption[i]);
		}
		
		oColors = new Color[] {null, Color.MAGENTA, Color.BLUE, null};
		for (int i = 0; i < oColors.length; i++) {
			oColorOption[i] = new JMenuItem(oColorNames[i]);
			oColorOption[i].addActionListener(this);
			oColorOption[i].setFont(FONT);
			if (i != 0 && i != 3) {
				oColorOption[i].setBorder(new LineBorder(oColors[i]));
			}
			oColorOptions.add(oColorOption[i]);
		}
		
		bColors = new Color[] {Color.WHITE, Color.YELLOW, null};
		for (int i = 0; i < bColors.length; i++) {
			bColorOption[i] = new JMenuItem(bColorNames[i]);
			bColorOption[i].setFont(FONT);
			bColorOption[i].addActionListener(this);
			if (i != 2) {
				bColorOption[i].setBackground(bColors[i]);
			}
			bColorOptions.add(bColorOption[i]);
		}
		
		tColors = new Color[] {Color.BLACK, Color.RED, null};
		for (int i = 0; i < tColors.length; i++) {
			tColorOption[i] = new JMenuItem(tColorNames[i]);
			tColorOption[i].setFont(FONT);
			tColorOption[i].addActionListener(this);
			if (i != 2) {
				tColorOption[i].setForeground(tColors[i]);
			}
			tColorOptions.add(tColorOption[i]);
		}
		
		reset = new JButton("Reset");
		reset.setFont(FONT);
		reset.addActionListener(this);
		menuBar.add(reset);
		
		north = new JButton("North");
		north.setFont(FONT);
		north.addActionListener(this);
		south = new JButton("South");
		south.setFont(FONT);
		south.addActionListener(this);
		east = new JButton("East");
		east.setFont(FONT);
		east.addActionListener(this);
		west = new JButton("West");
		west.setFont(FONT);
		west.addActionListener(this);
		
		buttonPanel = new JPanel();
		buttonLayout = new GridLayout(1, 4);
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(north);
		buttonPanel.add(south);
		buttonPanel.add(east);
		buttonPanel.add(west);
		
		north.setBorder(new LineBorder(O_COLOR));
		south.setBorder(new LineBorder(O_COLOR));
		east.setBorder(new LineBorder(O_COLOR));
		west.setBorder(new LineBorder(O_COLOR));
		reset.setBorder(new LineBorder(O_COLOR));
		
		textArea = new JTextArea();
		textArea.setBackground(B_COLOR);
		textArea.setForeground(T_COLOR);
		textArea.setFont(FONT);
		
		bigLayout = new GridLayout(1, 2);
		bigPanel = new JPanel();
		bigPanel.setLayout(bigLayout);
		bigPanel.add(buttonPanel);
		bigPanel.add(menuBar);
		
		frame.add(bigPanel, BorderLayout.NORTH);
		frame.add(textArea, BorderLayout.CENTER);
		frame.setSize(1500, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String[] args) {
		GUITask app = new GUITask();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == north) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(1, 4);
			bigLayout = new GridLayout(1, 2);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menuBar);
			bigPanel.remove(buttonPanel);
			bigPanel.add(buttonPanel);
			
			menuBar.setLayout(new GridLayout(1, 2));
			menuBar.remove(fontOptions);
			menuBar.remove(fontSizeOptions);
			menuBar.remove(tColorOptions);
			menuBar.remove(bColorOptions);
			menuBar.remove(oColorOptions);
			menuBar.remove(reset);
			
			menuBar.add(fontOptions);
			menuBar.add(fontSizeOptions);
			menuBar.add(tColorOptions);
			menuBar.add(bColorOptions);
			menuBar.add(oColorOptions);
			menuBar.add(reset);
			
			bigPanel.add(menuBar);
			
			frame.add(bigPanel, BorderLayout.NORTH);
		}
		if (e.getSource() == south) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(1, 2);
			bigLayout = new GridLayout(1, 2);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menuBar);
			bigPanel.remove(buttonPanel);
			bigPanel.add(buttonPanel);
			menuBar.setLayout(new GridLayout(1, 2));

			menuBar.remove(fontOptions);
			menuBar.remove(fontSizeOptions);
			menuBar.remove(tColorOptions);
			menuBar.remove(bColorOptions);
			menuBar.remove(oColorOptions);
			menuBar.remove(reset);
			
			menuBar.add(fontOptions);
			menuBar.add(fontSizeOptions);
			menuBar.add(tColorOptions);
			menuBar.add(bColorOptions);
			menuBar.add(oColorOptions);
			menuBar.add(reset);
			
			bigPanel.add(menuBar);
			
			frame.add(bigPanel, BorderLayout.SOUTH);
		}
		if (e.getSource() == east) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(4, 1);
			bigLayout = new GridLayout(2, 1);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			menuBar.setLayout(new GridLayout(6, 1));

			menuBar.remove(fontOptions);
			menuBar.remove(fontSizeOptions);
			menuBar.remove(tColorOptions);
			menuBar.remove(bColorOptions);
			menuBar.remove(oColorOptions);
			menuBar.remove(reset);
			
			menuBar.add(fontOptions);
			menuBar.add(fontSizeOptions);
			menuBar.add(tColorOptions);
			menuBar.add(bColorOptions);
			menuBar.add(oColorOptions);
			menuBar.add(reset);
			
			frame.add(bigPanel, BorderLayout.EAST);
		}
		if (e.getSource() == west) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(4, 1);
			bigLayout = new GridLayout(2, 1);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menuBar);
			bigPanel.remove(buttonPanel);
			bigPanel.add(buttonPanel);
			menuBar.setLayout(new GridLayout(6, 1));
			menuBar.remove(fontOptions);
			
			menuBar.remove(fontOptions);
			menuBar.remove(fontSizeOptions);
			menuBar.remove(tColorOptions);
			menuBar.remove(bColorOptions);
			menuBar.remove(oColorOptions);
			menuBar.remove(reset);
			
			menuBar.add(fontOptions);
			menuBar.add(fontSizeOptions);
			menuBar.add(tColorOptions);
			menuBar.add(bColorOptions);
			menuBar.add(oColorOptions);
			menuBar.add(reset);
			
			bigPanel.add(menuBar);
			
			frame.add(bigPanel, BorderLayout.WEST);
		}
		
		for (int i = 0; i < fontOption.length; i++) {
			if (e.getSource() == fontOption[i]) {
				currFont = new Font(fonts[i].getName(), Font.PLAIN, currFontSize);
				textArea.setFont(currFont);
				north.setFont(currFont);
				south.setFont(currFont);
				east.setFont(currFont);
				west.setFont(currFont);
				reset.setFont(currFont);
				fontOptions.setFont(currFont);
				fontSizeOptions.setFont(currFont);
				tColorOptions.setFont(currFont);
				bColorOptions.setFont(currFont);
				oColorOptions.setFont(currFont);
			}
		}
		
		for (int i = 0; i < fontSizeOption.length; i++) {
			if (e.getSource() == fontSizeOption[i]) {
				currFont = new Font(currFont.getName(), Font.PLAIN, fontSizes[i]);
				textArea.setFont(currFont);
			}
		}
		
		for (int i = 0; i < tColorOption.length; i++) {
			if (e.getSource() == tColorOption[i]) {
				if (i == 2) {
					int r = (int)(Math.random()*256), g = (int)(Math.random()*256), b = (int)(Math.random()*256);
					currTColor = new Color(r, g, b);
				} else {
					currTColor = tColors[i];
				}
				textArea.setForeground(currTColor);
			}
		}
		
		for (int i = 0; i < bColorOption.length; i++) {
			if (e.getSource() == bColorOption[i]) {
				if (i == 2) {
					int r = (int)(Math.random()*256), g = (int)(Math.random()*256), b = (int)(Math.random()*256);
					currBColor = new Color(r, g, b);
				} else {
					currBColor = bColors[i];
				}
				textArea.setBackground(currBColor);
			}
		}
		
		for (int i = 0; i < oColorOption.length; i++) {
			if (e.getSource() == oColorOption[i]) {
				if (i == 0) {
					north.setBorder(null);
					south.setBorder(null);
					east.setBorder(null);
					west.setBorder(null);
					reset.setBorder(null);
				} else {
					if (i == 3) {
						int r = (int)(Math.random()*256), g = (int)(Math.random()*256), b = (int)(Math.random()*256);
						currOColor = new Color(r, g, b);
					}
					else {
						currOColor = oColors[i];
					}
					north.setBorder(new LineBorder(currOColor));
					south.setBorder(new LineBorder(currOColor));
					east.setBorder(new LineBorder(currOColor));
					west.setBorder(new LineBorder(currOColor));
					reset.setBorder(new LineBorder(currOColor));
				}
			}
		}
		
		if (e.getSource() == reset) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(1, 4);
			bigLayout = new GridLayout(1, 2);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menuBar);
			bigPanel.remove(buttonPanel);
			bigPanel.add(buttonPanel);
			menuBar.setLayout(new GridLayout(1, 2));

			menuBar.remove(fontOptions);
			menuBar.remove(fontSizeOptions);
			menuBar.remove(tColorOptions);
			menuBar.remove(bColorOptions);
			menuBar.remove(oColorOptions);
			menuBar.remove(reset);
			
			menuBar.add(fontOptions);
			menuBar.add(fontSizeOptions);
			menuBar.add(tColorOptions);
			menuBar.add(bColorOptions);
			menuBar.add(oColorOptions);
			menuBar.add(reset);
			
			bigPanel.add(menuBar);
			
			frame.add(bigPanel, BorderLayout.NORTH);
			
			textArea.setText(null);
			textArea.setBackground(B_COLOR);
			textArea.setForeground(T_COLOR);
			textArea.setFont(FONT);
			north.setFont(FONT);
			south.setFont(FONT);
			east.setFont(FONT);
			west.setFont(FONT);
			reset.setFont(FONT);
			fontOptions.setFont(FONT);
			fontSizeOptions.setFont(FONT);
			tColorOptions.setFont(FONT);
			bColorOptions.setFont(FONT);
			oColorOptions.setFont(FONT);
			north.setBorder(new LineBorder(O_COLOR));
			south.setBorder(new LineBorder(O_COLOR));
			east.setBorder(new LineBorder(O_COLOR));
			west.setBorder(new LineBorder(O_COLOR));
			reset.setBorder(new LineBorder(O_COLOR));
			
			currTColor = T_COLOR;
			textArea.setForeground(currTColor);
			north.setForeground(currTColor);
			south.setForeground(currTColor);
			east.setForeground(currTColor);
			west.setForeground(currTColor);
			reset.setForeground(currTColor);
			fontOptions.setForeground(currTColor);
			fontSizeOptions.setForeground(currTColor);
			tColorOptions.setForeground(currTColor);
			bColorOptions.setForeground(currTColor);
			oColorOptions.setForeground(currTColor);
			
			currBColor = B_COLOR;
			textArea.setBackground(currBColor);
			north.setBackground(null);
			south.setBackground(null);
			east.setBackground(null);
			west.setBackground(null);
			reset.setBackground(null);
			fontOptions.setBackground(currBColor);
			fontSizeOptions.setBackground(currBColor);
			tColorOptions.setBackground(currBColor);
			bColorOptions.setBackground(currBColor);
			oColorOptions.setBackground(currBColor);
			
		}
		
		frame.revalidate();
	}
}
