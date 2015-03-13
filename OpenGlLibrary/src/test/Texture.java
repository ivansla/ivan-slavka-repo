package test;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class Texture {

	/**
	 * The OpenGL ES texture name associated with this texture.
	 */
	protected int textureId;

	/**
	 * The horizontal and vertical dimensions of the image.
	 */
	protected int width, height;

	/**
	 * The resource identifier for the image we want to load.
	 */
	int resourceId;

	/**
	 * Whether or not we should generate mip maps.
	 */
	boolean mipmaps;

	Texture(int resourceId, boolean mipmaps) {
		this.resourceId = resourceId;
		this.textureId = -1;
		this.mipmaps = mipmaps;
	}

	Texture(int resourceId) {
		this(resourceId, false);
	}

	/**
	 * Generates a new OpenGL ES texture name (identifier).
	 * @return The newly generated texture name.
	 */
	private static int newTextureID() {
		int[] temp = new int[1];
		GLES10.glGenTextures(1, temp, 0);
		return temp[0];
	}

	public final int getWidth() {
		return this.width;
	}

	public final int getHeight() {
		return this.height;
	}

	public final void load(Context context) {
		// Load the bitmap from resources.
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inScaled = false;
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), this.resourceId, opts);

		// Update this texture instance's width and height.
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();

		// Create and bind a new texture name.
		this.textureId = newTextureID();
		GLES10.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);

		// Load the texture into our texture name.
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);

		// Set magnification filter to bilinear interpolation.
		GLES10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		if(this.mipmaps) {
			// If mipmaps are requested, generate mipmaps and set minification filter to trilinear filtering.
			GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
			GLES10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
		}
		else GLES10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);

		// Recycle the bitmap.
		bmp.recycle();
	}

	/**
	 * Deletes the texture name and marks this instance as unloaded.
	 */
	public final void destroy() {
		GLES10.glDeleteTextures(1, new int[] {this.textureId}, 0);

		// Setting this value to -1 indicates that it is unloaded.
		this.textureId = -1;
	}

	public final boolean isLoaded() {
		return this.textureId >= 0;
	}

	public final void prepare(GL10 gl, FloatBuffer textureBuffer, int wrap) {
		// Enable 2D texture
		gl.glEnable(GL10.GL_TEXTURE_2D);

		// Bind our texture name
		gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);

		// Set texture wrap methods
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, wrap);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, wrap);

		// Enable texture coordinate arrays and load (activate) ours
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
	}

	public final void draw(GL10 gl, float x, float y, float w, float h, float rot) {
		gl.glPushMatrix();
		gl.glTranslatef(x, y, 0);
		gl.glRotatef(rot, 0, 0, 1);
		gl.glScalef(w, h, 0); // Scaling will be performed first.
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glPopMatrix();
	}
}
