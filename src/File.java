import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

	public Integer[] read(int initialLine, int finalLine) {
		List <Integer> newLines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(this.getFileFullName()))) {
			
			String line;
			
			int currentLine = 1;
//			int i = 0;
			while ((line = br.readLine()) != null) {
				if (initialLine <= currentLine && finalLine >= currentLine) {
					newLines.add(Integer.parseInt(line));
//					lines[i] = Integer.parseInt(line);
					
//					i++;
				}
				currentLine++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (newLines.isEmpty()) ? null : newLines.toArray(new Integer[newLines.size()]);
//		return (lines[0] == null) ? null : lines;
	}

	public static void read(String name, String extersion, String path) {
		// TODO Auto-generated method stub
	}
	
	public String readLine(int line) {
		String lineValue = null;
		try (Stream<String> lines = Files.lines(Paths.get(this.getFileFullName()))) {
			lineValue = lines.skip(line - 1).findFirst().get();
		} catch (NoSuchElementException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineValue;
	}
	
	public static String readLine(String name, String extersion, String path, int line) {
		String lineValue = null;
		try (Stream<String> lines = Files.lines(Paths.get(path + name + "." + extersion))) {
			lineValue = lines.skip(line - 1).findFirst().get();
		} catch (NoSuchElementException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineValue;
	}
	
	public boolean existFile(String path, String name) {
		boolean exist = true;
		try (Stream<String> lines = Files.lines(Paths.get(path + name))) {
			lines.findFirst().get();
		} catch (NoSuchFileException e) {
			exist = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exist;
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
