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
