import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JuliaSets extends JPanel implements AdjustmentListener, MouseListener, ActionListener {
	
	JFrame frame;
	static double a = 0, b = 0;
	static float hue = 0, saturation = 0, brightness = 0;
	JScrollBar aBar, bBar, hueBar, satBar, brightBar, zoomBar, nBar;
	JPanel scrollPanel, labelPanel, superPanel, buttonPanel;
	JLabel aLabel, bLabel, hueLabel, satLabel, brightLabel, zoomLabel, nLabel;
	static double zoom = 1;
	static float maxIterations = 300;
	static int width = 1000, height = 645;
	static BufferedImage image;
	static int n = 1;
	int pixels = 1;
	static FastTrig trig = new FastTrig();
	JButton clearButton, saveButton;
	JFileChooser fileChooser;
	
	public JuliaSets () {
		String currentDir = System.getProperty("user.dir");
		fileChooser = new JFileChooser(currentDir);
		
		frame = new JFrame("Julia Set Program");
		frame.add(this);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//orientation, start value, extent of the doodad, min value, max value	
		aBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		a = aBar.getValue()/1000.0;
		aBar.addAdjustmentListener(this);
		aBar.addMouseListener(this);
		
		bBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		b = bBar.getValue()/1000.0;
		bBar.addAdjustmentListener(this);
		bBar.addMouseListener(this);
		
		hueBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
		hue = (float) (hueBar.getValue()/1000.0);
		hueBar.addAdjustmentListener(this);
		hueBar.addMouseListener(this);
		
		satBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
		saturation = (float) (satBar.getValue()/1000.0);
		satBar.addAdjustmentListener(this);
		satBar.addMouseListener(this);
		
		brightBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
		brightness = (float) (brightBar.getValue()/1000.0);
		brightBar.addAdjustmentListener(this);
		brightBar.addMouseListener(this);
		
		zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 500, 10000);
		zoom = zoomBar.getValue()/1000.0;
		zoomBar.addAdjustmentListener(this);
		zoomBar.addMouseListener(this);
		
		nBar = new JScrollBar(JScrollBar.HORIZONTAL, 2, 0, 2, 10);
		n = nBar.getValue();
		nBar.addAdjustmentListener(this);
		nBar.addMouseListener(this);
		
		aLabel = new JLabel("A: " + a);
		bLabel = new JLabel("B: " + b);
		hueLabel = new JLabel("Hue: " + hue);
		satLabel = new JLabel("Saturation: " + saturation);
		brightLabel = new JLabel("Brightness: " + brightness + "  ");
		zoomLabel = new JLabel("Zoom: " + zoom);
		nLabel = new JLabel("N: " + n);
		
		clearButton = new JButton();
		clearButton.setText("CLEAR");
		clearButton.addActionListener(this);
		
		saveButton = new JButton();
		saveButton.setText("SAVE");
		saveButton.addActionListener(this);
		
		GridLayout grid = new GridLayout(7, 1);
		
		labelPanel = new JPanel();
		labelPanel.setLayout(grid);
		labelPanel.add(nLabel);
		labelPanel.add(aLabel);
		labelPanel.add(bLabel);
		labelPanel.add(hueLabel);
		labelPanel.add(satLabel);
		labelPanel.add(brightLabel);
		labelPanel.add(zoomLabel);
		labelPanel.setMaximumSize(new Dimension(160, 120));
		
		scrollPanel = new JPanel();
		scrollPanel.setLayout(grid);
		scrollPanel.add(nBar);
		scrollPanel.add(aBar);
		scrollPanel.add(bBar);
		scrollPanel.add(hueBar);
		scrollPanel.add(satBar);
		scrollPanel.add(brightBar);
		scrollPanel.add(zoomBar);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.add(clearButton);
		buttonPanel.add(saveButton);
		buttonPanel.setMaximumSize(new Dimension(100, 120));
		
		superPanel = new JPanel();
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.LINE_AXIS));
		superPanel.add(buttonPanel);
		superPanel.add(labelPanel);
		superPanel.add(scrollPanel);
		
		frame.add(superPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		image = drawJulia(g);
		g.drawImage(image, 0, 0, null);
		
	}
	
	public BufferedImage drawJulia (Graphics g) {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int w = 0; w < width; w+=pixels) {
			for (int h = 0; h < height; h+=pixels) {
				Runnable worker = new MyRunnable(w, h);
				executor.execute(worker);
			}
		}
		executor.shutdown();
		
		while (!executor.isTerminated()) {
			 
		}
		
		return image;
	}
	
	public static void main (String[] args) {
		JuliaSets app = new JuliaSets();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e.getSource() == aBar) {
			a = aBar.getValue()/1000.0;
			aLabel.setText("A: " + a);
		}
		if (e.getSource() == bBar) {
			b = bBar.getValue()/1000.0;
			bLabel.setText("B: " + b);
		}
		if (e.getSource() == hueBar) {
			hue = (float) (hueBar.getValue()/1000.0);
			hueLabel.setText("Hue: " + hue);
		}
		if (e.getSource() == satBar) {
			saturation = (float) (satBar.getValue()/1000.0);
			satLabel.setText("Saturation: " + saturation);
		}
		if  (e.getSource() == brightBar) {
			brightness = (float) (brightBar.getValue()/1000.0);
			brightLabel.setText("Brightness: " + brightness + "  ");
		}
		if (e.getSource() == zoomBar) {
			zoom = zoomBar.getValue()/1000.0;
			zoomLabel.setText("Zoom: " + zoom);
		}
		if (e.getSource() == nBar) {
			n = nBar.getValue();
			nLabel.setText("N: " + n);
		}
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pixels = 5;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pixels = 1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static double approxAtan(double z)
	{
	    final float n1 = 0.97239411f;
	    final float n2 = -0.19194795f;
	    return (n1 + n2 * z * z) * z;
	}
	
	public static double approxAtan2(double zy, double zx)
	{
	    if (zx != 0.0)
	    {
	        if (Math.abs(zx) > Math.abs(zy))
	        {
	            double z = zy / zx;
	            if (zx > 0.0)
	            {
	                // atan2(y,x) = atan(y/x) if x > 0
	                return approxAtan(z);
	            }
	            else if (zy >= 0.0)
	            {
	                // atan2(y,x) = atan(y/x) + PI if x < 0, y >= 0
	                return (float) (approxAtan(z) + Math.PI);
	            }
	            else
	            {
	                // atan2(y,x) = atan(y/x) - PI if x < 0, y < 0
	                return (float) (approxAtan(z) - Math.PI);
	            }
	        }
	        else // Use property atan(y/x) = PI/2 - atan(x/y) if |y/x| > 1.
	        {
	            final double z = zx / zy;
	            if (zy > 0.0)
	            {
	                // atan2(y,x) = PI/2 - atan(x/y) if |y/x| > 1, y > 0
	                return (float) (-approxAtan(z) + Math.PI/2);
	            }
	            else
	            {
	                // atan2(y,x) = -PI/2 - atan(x/y) if |y/x| > 1, y < 0
	                return (float) (-approxAtan(z) - Math.PI/2);
	            }
	        }
	    }
	    else
	    {
	        if (zy > 0.0) // x = 0, y > 0
	        {
	            return (float) (Math.PI/2);
	        }
	        else if (zy < 0.0) // x = 0, y < 0
	        {
	            return (float) (0-Math.PI/2);
	        }
	    }
	    return 0.0f; // x,y = 0. Could return NaN instead.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {			
			if (image != null) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.png", "png");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						String st = file.getAbsolutePath();
						if (st.indexOf(".png")>=0) {
							st = st.substring(0,st.length()-4);
						}
						ImageIO.write(image,"png",new File(st+".png"));
					} catch (IOException e1) {
						
					}
				}
			}
		}
		if (e.getSource() == clearButton) {
			aBar.setValue(0000);
			bBar.setValue(0000);
			hueBar.setValue(1000);
			satBar.setValue(1000);
			brightBar.setValue(1000);
			zoomBar.setValue(1000);
		}
	}
	
	public static class MyRunnable implements Runnable {
		
		private int w;
		private int h;
		
		public MyRunnable (int w, int h) {
			this.w = w;
			this.h = h;
		}
		
		@Override
		public void run() {
			float iteration = maxIterations;
			double zx = 1.5*(w-width/2.0)/(0.5*zoom*width);
			double zy = (h-height/2.0)/(0.5*zoom*height);
			while ((zx*zx + zy*zy < 6.0) && iteration > 0.0) {
				double temp = Math.pow((zx*zx + zy*zy), n/2.0) * trig.cos((float)(n*approxAtan2(zy, zx)*180/Math.PI)) + a;
				zy = Math.pow((zx*zx + zy*zy), n/2.0) * trig.sin((float)(n*approxAtan2(zy, zx)*180/Math.PI)) + b;
				zx = temp;
				iteration--;
			}
			int c;
			if (iteration > 0) {
				c = Color.HSBtoRGB(((maxIterations/iteration)%1)+hue, saturation, brightness);
			
			} else {
				c = Color.HSBtoRGB(((maxIterations/iteration)%1)+hue, saturation, 0);
			}
			image.setRGB(w, h, c);
		}
		
	}
	
}
