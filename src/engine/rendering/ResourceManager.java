package engine.rendering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import engine.math.Vector3f;

public class ResourceManager {
	private static String SHADER_ROOTPATH = "./resources/shaders/";
	private static String MESH_ROOTPATH = "./resources/meshes/";
	
	public static String loadShader(String fileName) {
		StringBuilder shaderSrc = new StringBuilder();
		BufferedReader shaderReader = null;

		try
		{
			shaderReader = new BufferedReader(new FileReader(SHADER_ROOTPATH + fileName));
			
			String line;
			while ((line = shaderReader.readLine()) != null) {
				shaderSrc.append(line).append("\n");
			}
			
			shaderReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		return shaderSrc.toString();
	}
	
	public static Mesh loadMesh(String fileName) {
		String[] splittedFileName = fileName.split("\\.");
		String ext = splittedFileName[splittedFileName.length - 1];
		
		if (!ext.equals("obj")) {
			System.err.println("Error : File format not supported for mesh data : " + ext);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		BufferedReader meshReader = null;
		
		try
		{
			meshReader = new BufferedReader(new FileReader(MESH_ROOTPATH + fileName));
			
			String line;
			while ((line = meshReader.readLine()) != null) {
				String[] tokens = line.split(" ");
				tokens = removeEmptyStrings(tokens);
				
				if (tokens.length == 0 || tokens[0].equals("#")) {
					continue;
				} else if (tokens[0].equals("v")) {
					vertices.add(new Vertex(new Vector3f(
							Float.valueOf(tokens[1]), 
							Float.valueOf(tokens[2]), 
							Float.valueOf(tokens[3]))
						));
				} else if (tokens[0].equals("f")) {
					indices.add(Integer.valueOf(tokens[1].split("/")[0]) - 1);
					indices.add(Integer.valueOf(tokens[2].split("/")[0]) - 1);
					indices.add(Integer.valueOf(tokens[3].split("/")[0]) - 1);
					
					// For quads
					if (tokens.length > 4) {
						indices.add(Integer.valueOf(tokens[1].split("/")[0]) - 1);
						indices.add(Integer.valueOf(tokens[2].split("/")[0]) - 1);
						indices.add(Integer.valueOf(tokens[3].split("/")[0]) - 1);
					}
				}
			}
			
			meshReader.close();
			
			Mesh mesh = new Mesh();
			Vertex[] vertexData = new Vertex[vertices.size()];
			vertices.toArray(vertexData);
			
			Integer[] indexData = new Integer[indices.size()];
			indices.toArray(indexData);
			
			mesh.addVertices(vertexData, toIntArray(indexData));
			
			return mesh;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	private static String[] removeEmptyStrings(String[] data) {
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < data.length; i++) {
			if (!data[i].equals(""))
				result.add(data[i]);
		}
		
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	private static int[] toIntArray(Integer[] array) {
		int[] res = new int[array.length];
		
		for (int i = 0; i < array.length; i++) {
			res[i] = array[i].intValue();
		}
		
		return res;
	}
}
