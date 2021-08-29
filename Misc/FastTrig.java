
public class FastTrig {
	
	static final int precision = 1000; // gradations per degree, adjust to suit

	static final int modulus = 360*precision;
	static final float[] sin = new float[modulus]; // lookup table
	static { 
	    // a static initializer fills the table
	    // in this implementation, units are in degrees
	    for (int i = 0; i<sin.length; i++) {
	        sin[i]=(float)Math.sin((i*Math.PI)/(precision*180));
	    }
	}
	// Private function for table lookup
	private static float sinLookup(int a) {
	    return a>=0 ? sin[a%(modulus)] : -sin[-a%(modulus)];
	}

	// These are your working functions:
	public static float sin(float d) {
	    return sinLookup((int)(d * precision + 0.5f));
	}
	public static float cos(float a) {
	    return sinLookup((int)((a+90f) * precision + 0.5f));
	}
	
	public static double pow (double base, double power) {
		double output = 1;
		
		for (int i = 1; i <= power; i++) {
			output *= base;
		}
		
		return output;
	}
	
	public static void main (String[] args) {
		FastTrig app = new FastTrig();
		System.out.println(sin(-80));
	}
	
}
