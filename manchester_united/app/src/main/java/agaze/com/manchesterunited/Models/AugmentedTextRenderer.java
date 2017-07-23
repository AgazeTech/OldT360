package agaze.com.manchesterunited.Models;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class AugmentedTextRenderer implements GLSurfaceView.Renderer  {

	private GLText glText;                             // A GLText Instance
	private Context context;                           // Context (from Activity)
	private String textToRender,m_fond;
	private int width = 100;                           // Updated to the Current Width + Height in onSurfaceChanged()
	private int height = 100;
	int m_fondsize;
	int m_xcor,m_ycoor;
	private float[] mProjMatrix = new float[16];
	private float[] mVMatrix = new float[16];
	private float[] mVPMatrix = new float[16];

	public AugmentedTextRenderer(Context context )  {
		super();
		this.context = context;                         // Save Specified Context

	}
	public AugmentedTextRenderer(Context context , String text, String fond, int fondsize , int xcoor, int ycoor, int width, int height)  {
		super();
		this.context = context;                         // Save Specified Context
		textToRender = text;
		m_fondsize = fondsize;
		m_fond = fond;
		m_xcor = xcoor;
		m_ycoor = ycoor;
	}
	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
		// Set the background frame color

		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		// Create the GLText
		glText = new GLText(context.getAssets());

		// Load the font from file (set size + padding), creates the texture
		// NOTE: after a successful call to this the font is ready for rendering!
		glText.load( m_fond, m_fondsize, m_xcor,m_ycoor );  // Create Font (Height: 14 Pixels / X+Y Padding 2 Pixels)

		// enable texture + alpha blending
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void onDrawFrame(GL10 unused) {
		// Redraw background color
		int clearMask = GLES20.GL_COLOR_BUFFER_BIT;

		GLES20.glClear(clearMask);
	
		Matrix.multiplyMM(mVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);
		
		// TEST: render the entire font texture
		glText.drawTexture( width, height, mVPMatrix);            // Draw the Entire Texture
		
		// TEST: render some strings with the font
		//glText.begin( 1.0f, 1.0f, 1.0f, 1.0f, mVPMatrix );         // Begin Text Rendering (Set Color WHITE)
		//glText.drawC("Test String 3D!", 0f, 0f, 0f, 0, -30, 0);
//		glText.drawC( "Test String :)", 0, 0, 0 );          // Draw Test String
		//glText.draw( "Diagonal 1", 40, 40, 40);                // Draw Test String
		//glText.draw( "Column 1", 100, 100, 90);              // Draw Test String
		//glText.end();                                   // End Text Rendering
		
		glText.begin( 0.0f, 0.0f, 1.0f, 1.0f, mVPMatrix );         // Begin Text Rendering (Set Color BLUE)
		glText.draw(textToRender , m_xcor, m_ycoor );        // Draw Test String
		//glText.draw( "The End.", 50, 200 + glText.getCharHeight(), 180);  // Draw Test String
		glText.end();                                   // End Text Rendering
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) { //		gl.glViewport( 0, 0, width, height ); 
		GLES20.glViewport(0, 0, width, height);
		float ratio = (float) width / height;

		// Take into account device orientation
		if (width > height) {
			Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
		}
		else {
			Matrix.frustumM(mProjMatrix, 0, -1, 1, -1/ratio, 1/ratio, 1, 10);
		}
		
		// Save width and height
		this.width = width;                             // Save Current Width
		this.height = height;                           // Save Current Height
		
		int useForOrtho = Math.min(width, height);
		
		//TODO: Is this wrong?
		Matrix.orthoM(mVMatrix, 0,
				-useForOrtho/2,
				useForOrtho/2,
				-useForOrtho/2,
				useForOrtho/2, 0.1f, 100f);
	}
}
