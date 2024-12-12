package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordTracker {

	public static void main(String[] args) {

		String fileName = args[0];
		String outputType = args[1];
		String outputFile = "";
		if (args.length > 2) {
			outputFile = args[3];
		}

		ArrayList<String> lineList = readFile(fileName);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println(lineList.get(i));
		}

	}

	public static ArrayList<String> readFile(String fileName) {
		ArrayList<String> fileList = new ArrayList<String>();

		try {
			File file = new File(fileName);
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				fileList.add(data.trim());
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return fileList;
	}
}
