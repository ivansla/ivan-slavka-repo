package test.sprites;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import test.Texture;
import test.TextureManager;

public class ImageSprite extends Sprite {
	public static final class Cutout {
		private FloatBuffer[] textureBuffers;
		private float frameWidth, frameHeight;
		private int cols, rows;
		private int startX, startY;

		/**
		 * Constructs a cutout that divides the texture into blocks of the given
		 * dimensions and takes out the given number of blocks horizontally from
		 * the texture. The remainder of the texture will remain unused.
		 * @param frameWidth	The width of each block.
		 * @param frameHeight	The height of each block.
		 * @param frameCount	The number of blocks to cut out of the image.
		 */
		public Cutout(float frameWidth, float frameHeight, int frameCount) {
			this(frameWidth, frameHeight, frameCount, 1, 0, 0);
		}

		/**
		 * Constructs a cutout that divides the texture into blocks of the given
		 * dimensions. You can specify the number of columns and rows and it will
		 * cut out a grid of blocks from the texture with the given information.
		 * The remainder of the texture will remain unused.
		 * @param frameWidth	The width of each block.
		 * @param frameHeight	The height of each block.
		 * @param frameCountW	The number of horizontal blocks.
		 * @param frameCountH	The number of vertical blocks.
		 */
		public Cutout(float frameWidth, float frameHeight, int frameCountW, int frameCountH) {
			this(frameWidth, frameHeight, frameCountW, frameCountH, 0, 0);
		}

		/**
		 * Constructs a cutout with the information given. This constructor is an
		 * extension to the {@code Cutout(float, float, int, int)} constructor
		 * that gives you the option to start at a given point in the texture.
		 * This can be useful if you are using large atlas textures.
		 * @param frameWidth	The width of each block.
		 * @param frameHeight	The height of each block.
		 * @param cols	The number of horizontal blocks.
		 * @param rows	The number of vertical blocks.
		 * @param startX	The x coordination of the starting pixel.
		 * @param startY	The y coordination of the starting pixel.
		 */
		public Cutout(float frameWidth, float frameHeight, int cols, int rows, int startX, int startY) {
			this.frameWidth = frameWidth;
			this.frameHeight = frameHeight;
			this.cols = cols;
			this.rows = rows;
			this.startX = startX;
			this.startY = startY;
		}

		/**
		 * Gets the frame (block) width associated with this cutout.
		 * @return	The frame width.
		 */
		public float getFrameWidth() {
			return this.frameWidth;
		}

		/**
		 * Gets the frame (block) height associated with this cutout.
		 * @return	The frame height.
		 */
		public float getFrameHeight() {
			return this.frameHeight;
		}

		/**
		 * Determines whether the texture buffers for this cutout have already
		 * been generated.
		 * @return	{@code true} if buffers are generated or {@code false} otherwise.
		 */
		boolean isGenerated() { return this.textureBuffers != null; }

		void generate(int w, int h) {
			this.textureBuffers = new FloatBuffer[this.cols * this.rows];

			for(int row = 0; row < this.rows; row++)
				for(int col = 0; col < this.cols; col++) {
					final float x1 = (this.startX + this.frameWidth * col) / w;
					final float x2 = (this.startX + this.frameWidth * (col + 1)) / w;
					final float y1 = (this.startY + this.frameHeight * row) / h;
					final float y2 = (this.startY + this.frameHeight * (row + 1)) / h;

					final float texture[] = {
							x1, y1,
							x2, y1,
							x1, y2,
							x2, y2,
					};

					final ByteBuffer ibb = ByteBuffer.allocateDirect(texture.length * 4);
					ibb.order(ByteOrder.nativeOrder());
					final FloatBuffer textureBuffer = ibb.asFloatBuffer();
					textureBuffer.put(texture);
					textureBuffer.position(0);
					this.textureBuffers[row * this.cols + col] = textureBuffer;
				}
		}
	}

	private Texture frames = null;
	private Cutout cutout = null;
	private int resId = -1;
	private int currentFrame;

	/**
	 * Constructs an {@code ImageSprite} with the given texture and {@code Cutout}. When
	 * you create an {@code ImageSprite}, make sure that the corresponding texture exists
	 * and is loaded beforehand. See {@link com.annahid.libs.artenus.TextureManager} for more details.
	 * @param resourceId	The resource identifier for the texture. This can be for an
	 * ordinary image (png, jpeg, etc.) or an SVG file.
	 * @param co	The cutout instructor to generate frames.
	 * @see com.annahid.libs.artenus.TextureManager
	 */
	public ImageSprite(int resourceId, Cutout co) {
		super();
		this.cutout = co;
		this.resId = resourceId;
		this.currentFrame = 0;
	}

	/**
	 * Gets the texture associated with this {@code ImageSprite}.
	 * @return	The associated {@code Texture}.
	 */
	public Texture getTexture() {
		return this.frames;
	}

	/**
	 * Sets the current frame for this {@code ImageSprite}. Frames are determined by
	 * the associated {@code ImageSprite.Cutout}.
	 * @param index	The frame index to change to.
	 */
	public void gotoFrame(int index) {
		this.currentFrame = index;
	}

	/**
	 * Gets the current frame for this {@code ImageSprite}. Frames are determined by
	 * the associated {@code ImageSprite.Cutout}.
	 * @return	The current frame.
	 */
	public int getCurrentFrame() {
		return this.currentFrame;
	}

	@Override
	public void render(GL10 gl) {
		if(this.frames == null) {
			this.frames = TextureManager.getTexture(this.resId);
			return;
		}

		if(this.alpha != 0) {
			if(!this.cutout.isGenerated())
				this.cutout.generate(this.frames.getWidth(), this.frames.getHeight());

			final float width = this.scale.x * this.cutout.frameWidth;
			final float height = this.scale.y * this.cutout.frameHeight;

			this.frames.prepare(gl, this.cutout.textureBuffers[this.currentFrame], GL10.GL_CLAMP_TO_EDGE);
			this.frames.draw(gl, this.pos.x, this.pos.y, width, height, this.rotation);
		}
	}
}
