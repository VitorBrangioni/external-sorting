import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

/**
 * Handle file management
 * 
 * @author vitorbrangioni
 *
 */
public class File {

	private String name;
	private String extension;
	private String path = "/var/www/html/study/external-sorting/files/";

	public File(String name, String extension, String path) {
		this.setName(name);
		this.setExtension(extension);
		this.setPath(path);
	}

	public String[] read(int initialLine, int finalLine) {
		String[] lines = new String[finalLine - initialLine +1];
		try (BufferedReader br = new BufferedReader(new FileReader(this.getFileFullName()))) {
			
			String line;
			
			int currentLine = 1;
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (initialLine <= currentLine && finalLine >= currentLine) {
					lines[i] = line;
					i++;
				}
				currentLine++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (lines[0] == null) ? null : lines;
	}

	public static void read(String name, String extersion, String path) {
		// TODO Auto-generated method stub
	}

	public void write(String content) {
		try {
			Files.write(Paths.get(this.getFileFullName()),
						(content + System.lineSeparator()).getBytes(),
						StandardOpenOption.CREATE,StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String name, String extersion, String path, String content) {
		try {
			Files.write(Paths.get(path + name + "." + extersion),
						(content + System.lineSeparator()).getBytes(),
						StandardOpenOption.CREATE,StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		if (extension != null) {
			this.extension = extension;
		}
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		if (path != null) {
			this.path = path;
		}

	}
	
	private String getFileFullName() {
		return this.path + this.name + "." + this.extension;
	}

}
