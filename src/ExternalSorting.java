
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
		
		String[] values = this.file.read(initialLine, finalLine);
		
		while (values != null) {
			Sort.mergeSort(values);
			
			for(String value: values) {
				File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/", value);
			}
			initialLine += maxMemory;
			finalLine += maxMemory;
			
			values = this.file.read(initialLine, finalLine);
			countTmpFile++;
		}
			

		

	}
	
}
