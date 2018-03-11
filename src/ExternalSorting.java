import sun.java2d.SurfaceDataProxy.CountdownTracker;

public class ExternalSorting {

	private File file;
	private int maxMemory = 3;
	private int countTmpFile = 1;

	public ExternalSorting(String fileName, String fileExtension, String filePath) {
		this.file = new File(fileName, fileExtension, filePath);
	}

	public void sort() {
		int initialLine = 1;
		int finalLine = 3;

		for (Integer[] values = this.file.read(initialLine, finalLine); values != null; countTmpFile++) {
			Sort.mergeSort(values, 0, values.length - 1);

			for (int value : values) {
				File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/",
						String.valueOf(value));
			}
			initialLine += maxMemory;
			finalLine += maxMemory;

			values = this.file.read(initialLine, finalLine);
		}

		// percorrer os temp files de 2 em 2
		// pegar cada elemento do arq e comparar com o outro
		// vai gerar um terceiro file temp, se gerar soma mais um

		/*
		 * String leftELement, rightElement;
		 * 
		 * for (int i = 1, j = 1, file = 1; file <= countTmpFile; i++) { }
		 */

		for (int fileOne = 1, fileTwo = 2; fileTwo < countTmpFile; fileOne += 2, fileTwo += 2) {
			// verificando se ja foi ordenado tudo
			if (!file.existFile("/var/www/html/study/external-sorting/tmp-files/", "tmp_" + fileTwo + ".txt")) {
				break;
			}

			int lineFileOne = 1;
			int lineFileTwo = 1;
			// percorrer os elementos
			String elementOne = File.readLine("tmp_" + fileOne, "txt",
					"/var/www/html/study/external-sorting/tmp-files/", lineFileOne);
			String elementTwo = File.readLine("tmp_" + fileTwo, "txt",
					"/var/www/html/study/external-sorting/tmp-files/", lineFileTwo);

			while (elementOne != null || elementTwo != null) {
				if (elementOne == null) {
					File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/",
							elementTwo);
					lineFileTwo++;
					elementTwo = File.readLine("tmp_" + fileTwo, "txt", "/var/www/html/study/external-sorting/tmp-files/",
							lineFileTwo);
					continue;
				} else if (elementTwo == null) {
					File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/",
							elementOne);
					lineFileOne++;
					elementOne = File.readLine("tmp_" + fileOne, "txt", "/var/www/html/study/external-sorting/tmp-files/",
							lineFileOne);
					continue;
				}

				if (Integer.parseInt(elementOne) < Integer.parseInt(elementTwo) || elementTwo == null) {
					File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/",
							elementOne);
					lineFileOne++;
				} else {
					File.write("tmp_" + countTmpFile, "txt", "/var/www/html/study/external-sorting/tmp-files/",
							elementTwo);
					lineFileTwo++;
				}
				elementOne = File.readLine("tmp_" + fileOne, "txt", "/var/www/html/study/external-sorting/tmp-files/",
						lineFileOne);
				elementTwo = File.readLine("tmp_" + fileTwo, "txt", "/var/www/html/study/external-sorting/tmp-files/",
						lineFileTwo);
			}
			countTmpFile++;
		}

	}

}
