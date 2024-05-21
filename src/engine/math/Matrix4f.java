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
	
	public Matrix4f initTranslationMatrix(float tx, float ty, float tz) {
		m = new float[][]{
				{1, 0, 0, tx},
				{0, 1, 0, ty},
				{0, 0, 1, tz},
				{0, 0, 0, 1}
		};
		
		return this;
	}
	
	public Matrix4f initRotationMatrix(float x, float y, float z) {
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
				
		rx.m = new float[][]{
			{1, 		0, 					0, 				0},
			{0, (float)Math.cos(x), (float)-Math.sin(x), 	0},
			{0, (float)Math.sin(x), (float)Math.cos(x), 	0},
			{0, 		0,		  			0, 				1}
		};
		
		ry.m = new float[][]{
			{(float)Math.cos(y), 	0, 	(float)Math.sin(y), 	0},
			{0, 					1,		 	0, 				0},
			{(float)-Math.sin(y), 	0, 	(float)Math.cos(y), 	0},
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
	
	public Matrix4f initScaleMatrix(float sx, float sy, float sz) {
		m = new float[][]{
			{sx, 0, 0, 0},
			{0, sy, 0, 0},
			{0, 0, sz, 0},
			{0, 0,  0, 1}
		};
		
		return this;
	}
	
	public Matrix4f initProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
		float aspectRatio = width / height;
		float tanHalfFOV = (float)Math.tan(Math.toRadians(fov / 2));
		float zRange = zNear - zFar;
		
		m = new float[][]{
				{1.f / (tanHalfFOV * aspectRatio), 			0, 					0, 								0},
				{		0, 							1.f / tanHalfFOV, 			0, 								0},
				{		0, 									0, 			-(zNear + zFar) / zRange, (2 * zFar * zNear) / zRange},
				{		0, 									0, 					1, 							0}
		};
		
		return this;
	}
	
	public Matrix4f initViewMatrix(Vector3f forward, Vector3f up) {
		Vector3f f = forward;
		f.normalize();
		
		Vector3f r = up; // right vector
		r.normalize();
		r = r.cross(f);
		
		Vector3f u = f.cross(r); // up vector
		
		m = new float[][]{
			{r.getX(), r.getY(), r.getZ(), 	0},
			{u.getX(), u.getY(), u.getZ(), 	0},
			{f.getX(), f.getY(), f.getZ(), 	0},
			{	0, 		0,  		0, 		1}
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
