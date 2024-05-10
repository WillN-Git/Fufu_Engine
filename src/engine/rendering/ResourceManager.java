package engine.rendering;

import java.io.BufferedReader;
import java.io.FileReader;

public class ResourceManager {
	private static String SHADER_ROOTPATH = "./resources/shaders/";
	
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
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		return shaderSrc.toString();
	}
}
