package it.raffo.processing;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES2;

import processing.core.PApplet;
import processing.opengl.PGL;
import processing.opengl.PJOGL;
import processing.opengl.PShader;

public class TestOpenGL extends PApplet
{
	public static void main(String[] args)
	{
		PApplet.main("it.raffo.processing.TestOpenGL");
	}

	PShader		shader;

	float		a;
	float[]		positions;
	float[]		colors;

	int[]		indices;
	FloatBuffer	posBuffer;
	FloatBuffer	colorBuffer;

	IntBuffer	indexBuffer;
	int			posVboId;
	int			colorVboId;

	int			indexVboId;
	int			posLoc;

	int			colorLoc;
	PJOGL		pgl;

	GL2ES2		gl;

	FloatBuffer allocateDirectFloatBuffer(int n)
	{
		return ByteBuffer.allocateDirect(n * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
	}

	IntBuffer allocateDirectIntBuffer(int n)
	{
		return ByteBuffer.allocateDirect(n * Integer.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer();
	}

	@Override
	public void draw()
	{
		this.background(0);

		// Geometry transformations from Processing are automatically passed to the
		// shader
		// as long as the uniforms in the shader have the right names.
		this.translate(this.width / 2, this.height / 2);
		this.rotateX(this.a);
		this.rotateY(this.a * 2);

		this.updateGeometry();

		this.pgl = (PJOGL) this.beginPGL();
		this.gl = this.pgl.gl.getGL2ES2();

		this.shader.bind();
		this.gl.glEnableVertexAttribArray(this.posLoc);
		this.gl.glEnableVertexAttribArray(this.colorLoc);

		// Copy vertex data to VBOs
		this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.posVboId);
		this.gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * this.positions.length, this.posBuffer,
				GL.GL_DYNAMIC_DRAW);
		this.gl.glVertexAttribPointer(this.posLoc, 4, GL.GL_FLOAT, false, 4 * Float.BYTES, 0);
		this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.colorVboId);
		this.gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * this.colors.length, this.colorBuffer,
				GL.GL_DYNAMIC_DRAW);
		this.gl.glVertexAttribPointer(this.colorLoc, 4, GL.GL_FLOAT, false, 4 * Float.BYTES, 0);
		this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		// Draw the triangle elements
		this.gl.glBindBuffer(PGL.ELEMENT_ARRAY_BUFFER, this.indexVboId);
		this.pgl.bufferData(PGL.ELEMENT_ARRAY_BUFFER, Integer.BYTES * this.indices.length, this.indexBuffer,
				GL.GL_DYNAMIC_DRAW);
		this.gl.glDrawElements(PGL.TRIANGLES, this.indices.length, GL.GL_UNSIGNED_INT, 0);
		this.gl.glBindBuffer(PGL.ELEMENT_ARRAY_BUFFER, 0);
		this.gl.glDisableVertexAttribArray(this.posLoc);
		this.gl.glDisableVertexAttribArray(this.colorLoc);

		this.shader.unbind();

		this.endPGL();

		this.a += 0.01;
	}

	@Override
	public void settings()
	{

		this.size(800, 600, P3D);
	}

	@Override
	public void setup()
	{
		URL resourceFrag = this.getClass().getResource("/resources/frag.glsl");
		URL resourceVert = this.getClass().getResource("/resources/vert.glsl");
		System.out.println("Frag file:: " + resourceFrag.getFile());
		System.out.println("Vert file:: " + resourceVert.getFile());

		this.shader = this.loadShader(resourceFrag.getFile(), resourceVert.getFile());

		this.positions = new float[32];
		this.colors = new float[32];
		this.indices = new int[12];

		this.posBuffer = this.allocateDirectFloatBuffer(32);
		this.colorBuffer = this.allocateDirectFloatBuffer(32);
		this.indexBuffer = this.allocateDirectIntBuffer(12);

		this.pgl = (PJOGL) this.beginPGL();
		this.gl = this.pgl.gl.getGL2ES2();

		// Get GL ids for all the buffers
		IntBuffer intBuffer = IntBuffer.allocate(3);
		this.gl.glGenBuffers(3, intBuffer);
		this.posVboId = intBuffer.get(0);
		this.colorVboId = intBuffer.get(1);
		this.indexVboId = intBuffer.get(2);

		// Get the location of the attribute variables.
		this.shader.bind();
		this.posLoc = this.gl.glGetAttribLocation(this.shader.glProgram, "position");
		this.colorLoc = this.gl.glGetAttribLocation(this.shader.glProgram, "color");
		this.shader.unbind();

		this.endPGL();
	}

	public void updateGeometry()
	{
		// Vertex 1
		this.positions[0] = -200;
		this.positions[1] = -200;
		this.positions[2] = 0;
		this.positions[3] = 1;

		this.colors[0] = 1.0f;
		this.colors[1] = 0.0f;
		this.colors[2] = 0.0f;
		this.colors[3] = 1.0f;

		// Vertex 2
		this.positions[4] = +200;
		this.positions[5] = -200;
		this.positions[6] = 0;
		this.positions[7] = 1;

		this.colors[4] = 1.0f;
		this.colors[5] = 1.0f;
		this.colors[6] = 0.0f;
		this.colors[7] = 1.0f;

		// Vertex 3
		this.positions[8] = -200;
		this.positions[9] = +200;
		this.positions[10] = 0;
		this.positions[11] = 1;

		this.colors[8] = 0.0f;
		this.colors[9] = 1.0f;
		this.colors[10] = 0.0f;
		this.colors[11] = 1.0f;

		// Vertex 4
		this.positions[12] = +200;
		this.positions[13] = +200;
		this.positions[14] = 0;
		this.positions[15] = 1;

		this.colors[12] = 0.0f;
		this.colors[13] = 1.0f;
		this.colors[14] = 1.0f;
		this.colors[15] = 1.0f;

		// Vertex 5
		this.positions[16] = -200;
		this.positions[17] = -200 * cos(HALF_PI);
		this.positions[18] = -200 * sin(HALF_PI);
		this.positions[19] = 1;

		this.colors[16] = 0.0f;
		this.colors[17] = 0.0f;
		this.colors[18] = 1.0f;
		this.colors[19] = 1.0f;

		// Vertex 6
		this.positions[20] = +200;
		this.positions[21] = -200 * cos(HALF_PI);
		this.positions[22] = -200 * sin(HALF_PI);
		this.positions[23] = 1;

		this.colors[20] = 1.0f;
		this.colors[21] = 0.0f;
		this.colors[22] = 1.0f;
		this.colors[23] = 1.0f;

		// Vertex 7
		this.positions[24] = -200;
		this.positions[25] = +200 * cos(HALF_PI);
		this.positions[26] = +200 * sin(HALF_PI);
		this.positions[27] = 1;

		this.colors[24] = 0.0f;
		this.colors[25] = 0.0f;
		this.colors[26] = 0.0f;
		this.colors[27] = 1.0f;

		// Vertex 8
		this.positions[28] = +200;
		this.positions[29] = +200 * cos(HALF_PI);
		this.positions[30] = +200 * sin(HALF_PI);
		this.positions[31] = 1;

		this.colors[28] = 1.0f;
		this.colors[29] = 1.0f;
		this.colors[30] = 1.1f;
		this.colors[31] = 1.0f;

		// Triangle 1
		this.indices[0] = 0;
		this.indices[1] = 1;
		this.indices[2] = 2;

		// Triangle 2
		this.indices[3] = 2;
		this.indices[4] = 3;
		this.indices[5] = 1;

		// Triangle 3
		this.indices[6] = 4;
		this.indices[7] = 5;
		this.indices[8] = 6;

		// Triangle 4
		this.indices[9] = 6;
		this.indices[10] = 7;
		this.indices[11] = 5;

		this.posBuffer.rewind();
		this.posBuffer.put(this.positions);
		this.posBuffer.rewind();

		this.colorBuffer.rewind();
		this.colorBuffer.put(this.colors);
		this.colorBuffer.rewind();

		this.indexBuffer.rewind();
		this.indexBuffer.put(this.indices);
		this.indexBuffer.rewind();
	}

}
