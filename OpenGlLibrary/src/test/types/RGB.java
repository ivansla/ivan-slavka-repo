package test.types;

/**
 * This class represents a color with red, green, and blue components. This class does not
 * provide transparency since it is not to be used for that purpose.
 * @author Hessan Feghhi
 *
 */
public final class RGB {
	public float r;
	public float g;
	public float b;

	public RGB(float cr, float cg, float cb) {
		r = cr;
		g = cg;
		b = cb;
	}

	public RGB(String rgb) {
		r = Integer.parseInt(rgb.substring(0, 2), 16) / 256.0f;
		g = Integer.parseInt(rgb.substring(2, 4), 16) / 256.0f;
		b = Integer.parseInt(rgb.substring(4), 16) / 256.0f;
	}
}
