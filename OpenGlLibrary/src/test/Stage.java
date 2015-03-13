package test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.example.opengllibrary.R;

public class Stage extends GLSurfaceView{

	private Texture tex;
	private float w, h;
	private int screenWidth, screenHeight;
	private FloatBuffer vertexBuffer;

	public Stage(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);

		this.setRenderer(new MyRenderer());

		this.tex = new Texture(R.drawable.blast);

		float vertices[] = {
				-0.5f, -0.5f,  0.0f,  // 0. left-bottom
				0.5f, -0.5f,  0.0f,  // 1. right-bottom
				-0.5f,  0.5f,  0.0f,  // 2. left-top
				0.5f,  0.5f,  0.0f   // 3. right-top
		};

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		this.vertexBuffer = vbb.asFloatBuffer();
		this.vertexBuffer.put(vertices);
		this.vertexBuffer.position(0);
	}

	private class MyRenderer implements GLSurfaceView.Renderer {
		@Override
		public final void onDrawFrame(GL10 gl) {

			gl.glClear(GLES10.GL_COLOR_BUFFER_BIT);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, Stage.this.vertexBuffer);
			Stage.this.tex.prepare(gl, Stage.this.vertexBuffer, GL10.GL_CLAMP_TO_EDGE);
			Stage.this.tex.draw(gl, Stage.this.w / 2, Stage.this.h / 2, Stage.this.tex.getWidth(), Stage.this.tex.getHeight(), 0);
		}

		@Override
		public final void onSurfaceChanged(GL10 gl, int width, int height) {
			gl.glClearColor(0, 0, 0, 1);

			if(width > height) {
				Stage.this.h = 600;
				Stage.this.w = width * Stage.this.h / height;
			} else {
				Stage.this.w = 600;
				Stage.this.h = height * Stage.this.w / width;
			}

			Stage.this.screenWidth = width;
			Stage.this.screenHeight = height;
			gl.glViewport(0, 0, Stage.this.screenWidth, Stage.this.screenHeight);

			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrthof(0, Stage.this.w, Stage.this.h, 0, -1, 1);
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
		}

		@Override
		public final void onSurfaceCreated(GL10 gl, EGLConfig config) {

			// Set up alpha blending
			gl.glEnable(GL10.GL_ALPHA_TEST);
			gl.glEnable(GL10.GL_BLEND);

			// We will discuss this line later along with textures
			gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

			// We are in 2D. Who needs depth?
			gl.glDisable(GL10.GL_DEPTH_TEST);

			// Enable vertex arrays (we'll use them to draw primitives).
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

			// Enable texture coordinate arrays.
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

			Stage.this.tex.load(Stage.this.getContext());
		}
	}
}


