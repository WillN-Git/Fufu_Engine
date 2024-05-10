package engine.math;

public class Matrix4f {
	private float[][] m;
	
	public Matrix4f() {
		m = new float[4][4];
	}

	public Matrix4f initIdentity() {
		m = new float[][]{
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
		};
		
		return this;
	}
	
	public Matrix4f initTranslation(float tx, float ty, float tz) {
		m = new float[][]{
				{1, 0, 0, tx},
				{0, 1, 0, ty},
				{0, 0, 1, tz},
				{0, 0, 0, 1}
		};
		
		return this;
	}
	
	public Matrix4f initRotation(float x, float y, float z) {
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
				
		rx.m = new float[][]{
			{1, 		0, 					0, 				0},
			{0, (float)Math.cos(x), (float)-Math.sin(x), 	0},
			{0, (float)Math.sin(x), (float)Math.sin(x), 	0},
			{0, 		0,		  			0, 				1}
		};
		
		ry.m = new float[][]{
			{(float)Math.cos(y), 	0, 	(float)-Math.sin(y), 	0},
			{0, 					1,		 	0, 				0},
			{(float)Math.sin(y), 	0, 	(float)Math.cos(y), 	0},
			{0, 					0,		  	0, 				1}
		};
		
		
		rz.m = new float[][]{
			{(float)Math.cos(z), (float)-Math.sin(z), 	0, 			0},
			{(float)Math.sin(z), (float) Math.cos(z), 	0, 			0},
			{0, 							0, 		  	1, 			0},
			{0, 							0,		  	0, 			1}
		};
		
		m = rz.mul(ry.mul(rx)).getM();
		
		return this;
	}
	
	public Matrix4f initScale(float sx, float sy, float sz) {
		m = new float[][]{
			{sx, 0, 0, 0},
			{0, sy, 0, 0},
			{0, 0, sz, 0},
			{0, 0,  0, 1}
		};
		
		return this;
	}
	
	
	public Matrix4f mul(Matrix4f mat) {
		Matrix4f res = new Matrix4f();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				
				float res_ij = 0;
				for (int k = 0; k < 4; k++) {
					res_ij +=  m[i][k] * mat.get(k, j);
				}
				
				res.set(i, j, res_ij);
			}
		}
		
		return res;
	}
	
	public float get(int x, int y) {
		return m[x][y];
	}
	
	public float[][] getM() {
		return m;
	}

	public void set(int x, int y, float value) {
		m[x][y] = value;
	}
	
	public void setM(float[][] m) {
		this.m = m;
	}
}
