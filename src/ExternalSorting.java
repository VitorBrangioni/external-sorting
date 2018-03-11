import java.util.Arrays;

public class ExternalSorting {
	
	private File file;
	private int maxMemory = 3;
	private int countTmpFile = 0;
	
	public ExternalSorting(String fileName, String fileExtension, String filePath) {
		this.file = new File(fileName, fileExtension, filePath);
	}
	
	public void sort() {

		int initialLine = 1;
		int finalLine = 3;
		
		Integer[] values = this.file.read(initialLine, finalLine);
		
		while (values != null) {
			System.out.println("ANTES DO SORT: ");
			System.out.println(Arrays.toString(values));
			
			Sort.mergeSort(values, 0, values.length -1);
			
			System.out.println("DEPOIS DO SORT: ");
			System.out.println(Arrays.toString(values));
			
			for(int value: values) {
				File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/", String.valueOf(value));
			}
			
			
			initialLine += maxMemory;
			finalLine += maxMemory;
			
			values = this.file.read(initialLine, finalLine);
			countTmpFile++;
		}

	}
	
}
