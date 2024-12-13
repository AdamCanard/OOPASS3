package appDomain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import implementations.BSTree;
import implementations.BSTreeNode;
import utilities.Iterator;

public class WordTracker {

	static BSTree<String> wordTree = new BSTree<String>();

	static String fileName;
	static String outputType;
	static String outputFile;

	public static void main(String[] args) {

		fileName = args[0];
		outputType = args[1];
		outputFile = "";
		if (args.length > 2) {
			outputFile = args[3];
		}

		if (new File("res/repository.ser").exists()) {
			System.out.println("Loading serialized repository...");
			try {
				FileInputStream Stream = new FileInputStream("repository.ser");
				ObjectInputStream Saved;
				Saved = new ObjectInputStream(Stream);
				wordTree = (BSTree<String>) Saved.readObject();

				Stream.close();
				Saved.close();
			} catch (Exception e) {
				System.out.println("Error loading serialized wordtree.");
			}
		}

		ArrayList<String> lineList = readFile(fileName);

		loadTree(lineList);
		fileName = "res/otherTest.txt";
		lineList = readFile(fileName);

		loadTree(lineList);

		printOutput(outputType, wordTree);

		writeToFile(wordTree, "test.txt");

		serializeWriteBSTree(wordTree);
	}

	private static void printOutput(String outputType, BSTree<String> wordTree) {
		Iterator<String> wordTreeIterator = wordTree.inorderIterator();

		if (outputType.equals("-pf")) {
			while (wordTreeIterator.hasNext()) {
				String element = wordTreeIterator.next();
				String word = getWord(element);
				HashMap<String, String> fileNamesAndLines = getFileNamesAndLines(element);

				for (Map.Entry<String, String> set : fileNamesAndLines.entrySet()) {
					String output = "Key : ===" + word + "===" + " found in file: " + set.getKey();
					System.out.println(output);
				}
			}
		} else if (outputType.equals("-pl")) {
			while (wordTreeIterator.hasNext()) {
				String element = wordTreeIterator.next();
				String word = getWord(element);
				HashMap<String, String> fileNamesAndLines = getFileNamesAndLines(element);

				for (Map.Entry<String, String> set : fileNamesAndLines.entrySet()) {
					String output = "Key : ===" + word + "===" + " found in file: " + set.getKey() + " on lines: "
							+ set.getValue();
					System.out.println(output);
				}
			}
		} else if (outputType.equals("-po")) {
			while (wordTreeIterator.hasNext()) {
				String element = wordTreeIterator.next();
				String word = getWord(element);
				HashMap<String, String> fileNamesAndLines = getFileNamesAndLines(element);
				String frequency = element.split(",")[0];

				for (Map.Entry<String, String> set : fileNamesAndLines.entrySet()) {
					String output = "Key : ===" + word + "===" + " number of entries: " + frequency + " found in file: "
							+ set.getKey() + " on lines: " + set.getValue();
					System.out.println(output);
				}
			}
		}
	}

	private static void writeToFile(BSTree<String> toWrite, String fileName) {
		try {
			FileWriter treeWriter = new FileWriter(fileName);
			Iterator<String> wordTreeIterator = wordTree.inorderIterator();

			if (outputType.equals("-pf")) {
				while (wordTreeIterator.hasNext()) {
					String element = wordTreeIterator.next();
					String word = getWord(element);
					HashMap<String, String> fileNamesAndLines = getFileNamesAndLines(element);

					for (Map.Entry<String, String> set : fileNamesAndLines.entrySet()) {
						String output = "Key : ===" + word + "===" + " found in file: " + set.getKey();
						treeWriter.write(output + "\n");
					}
				}
			} else if (outputType.equals("-pl")) {
				while (wordTreeIterator.hasNext()) {
					String element = wordTreeIterator.next();
					String word = getWord(element);
					HashMap<String, String> fileNamesAndLines = getFileNamesAndLines(element);

					for (Map.Entry<String, String> set : fileNamesAndLines.entrySet()) {
						String output = "Key : ===" + word + "===" + " found in file: " + set.getKey() + " on lines: "
								+ set.getValue();
						treeWriter.write(output + "\n");
					}
				}
			} else if (outputType.equals("-po")) {
				while (wordTreeIterator.hasNext()) {
					String element = wordTreeIterator.next();
					String word = getWord(element);
					HashMap<String, String> fileNamesAndLines = getFileNamesAndLines(element);
					String frequency = element.split(",")[0];

					for (Map.Entry<String, String> set : fileNamesAndLines.entrySet()) {
						String output = "Key : ===" + word + "===" + " number of entries: " + frequency
								+ " found in file: " + set.getKey() + " on lines: " + set.getValue();
						treeWriter.write(output + "\n");
					}
				}
			}

			System.out.println("Successfully written to " + fileName);
		} catch (IOException e) {
			System.out.print("Error while writing to file: ");
			System.out.print(e);
		}
	}

	private static void serializeWriteBSTree(BSTree<String> toWrite) {
		try {
			OutputStream Stream = new FileOutputStream("res/repository.ser");
			ObjectOutputStream Out = new ObjectOutputStream(new BufferedOutputStream(Stream));
			Out.writeObject(wordTree);
			Out.close();
			Stream.close();
			System.out.println("WordTree has been saved in repository.ser.");
		} catch (Exception e) {
			System.out.println("There was an error writing wordTree to repository.ser.");
			System.out.println(e);
		}
	}

	private static String getWord(String element) {
		String word = element.split(",")[1].split("~")[0];
		return word;
	}

	private static HashMap<String, String> getFileNamesAndLines(String element) {
		HashMap<String, String> fileNamesAndLines = new HashMap<>();
		String[] filesSplit = element.split("~")[1].split(";");

		for (String file : filesSplit) {
			String fileName = file.split(":")[0];
			String lineNum = file.split(":")[1];

			if (fileNamesAndLines.containsKey(fileName)) {
				fileNamesAndLines.put(fileName, fileNamesAndLines.get(fileName) + "," + lineNum);
			} else {
				fileNamesAndLines.put(fileName, lineNum);
			}
		}

		return fileNamesAndLines;

	}

	private static void loadTree(ArrayList<String> lineList) {
		for (int i = 0; i < lineList.size(); i++) {
			String[] words = lineList.get(i).split(" ");

			for (String word : words) {

				BSTreeNode<String> treeWord;
				String formattedword = word.replaceAll("\\p{Punct}", "").toLowerCase();

				try {
					treeWord = wordTree.search(formattedword);
					if (treeWord == null) {

						wordTree.add(formattedword);
						treeWord = wordTree.search(formattedword);

						treeWord.setOccurrences(fileName + ":" + (i + 1));

					} else {
						String prevOccurences = treeWord.getOccurrences();
						String[] splitOccurrences = prevOccurences.split(";");

						int counter = 0;
						boolean found = false;
						while (counter < splitOccurrences.length) {
							if (fileName.equals(splitOccurrences[counter])) {
								splitOccurrences[counter + 1] += "," + (i + 1);
								found = true;
							}
							counter += 2;
						}
						// add new file and Occurence
						if (!found) {
							treeWord.setOccurrences(prevOccurences + ";" + fileName + ":" + (i + 1));
						} else {
							treeWord.setOccurrences(String.join(":", splitOccurrences));
						}

						String prevOccurences1 = treeWord.getOccurrences();
						String[] splitOccurrences1 = prevOccurences1.split(";");
					}
				} catch (Exception e) {
					wordTree.add(formattedword);
					treeWord = wordTree.search(formattedword);

					treeWord.setOccurrences(fileName + ":" + (i + 1));
				}

				// word not in tree

			}
		}
	}

	private static String concat(String[] splitString) {
		String output = "";
		for (String s : splitString) {
			output += s + ":";
		}
		return output;
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
