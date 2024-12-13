package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import implementations.BSTree;
import implementations.BSTreeNode;
import utilities.Iterator;

public class WordTracker {

	// init variables
	static BSTree<String> wordTree = new BSTree<String>();

	static String fileName;
	static String outputType;
	static String outputFile;

	public static void main(String[] args) {

		// store args in variables
		fileName = args[0];
		outputType = args[1];
		outputFile = "";
		if (args.length > 2) {
			outputFile = args[3];
		}

		// reads given file to list of lines
		ArrayList<String> lineList = readFile(fileName);

		// Loads list of lines into BSTree
		loadTree(lineList);

		fileName = "res/otherTest.txt";
		lineList = readFile(fileName);

		loadTree(lineList);

		Iterator<String> wordTreeIterator = wordTree.inorderIterator();

		while (wordTreeIterator.hasNext()) {
			if (outputType.equals("-pf")) {
				String word = wordTreeIterator.next();
				BSTreeNode<String> treeWord = wordTree.search(word);
				String occurrences = treeWord.getOccurrences();

				String[] splitOccurrences = occurrences.split(":");
				String output = "Key : ===" + word + "===" + " found in file: ";
				String files = "";
				int counter = 0;
				while (counter < splitOccurrences.length) {
					String currentFile = splitOccurrences[counter];
					files += currentFile + ", ";
					counter += 2;
				}
				System.out.println(output + files);

			} else if (outputType.equals("-pl")) {
				String word = wordTreeIterator.next();

				BSTreeNode<String> treeWord = wordTree.search(word);
				String occurrences = treeWord.getOccurrences();

				String[] splitOccurrences = occurrences.split(":");
				String output = "Key : ===" + word + "===" + " ";
				int frequency = 0;
				int counter = 0;
				while (counter < splitOccurrences.length) {
					String currentFile = splitOccurrences[counter];
					String linesInFile = splitOccurrences[counter + 1];
					counter += 2;
					output += " " + currentFile + " on lines: " + linesInFile;
				}

				System.out.println(output.replaceAll("#", Integer.toString(frequency)));

			} else if (outputType.equals("-po")) {
				String word = wordTreeIterator.next();

				BSTreeNode<String> treeWord = wordTree.search(word);
				String occurrences = treeWord.getOccurrences();

				String[] splitOccurrences = occurrences.split(":");
				String output = "Key : ===" + word + "===" + " number of entries: #";
				int frequency = 0;
				int counter = 0;
				while (counter < splitOccurrences.length) {
					String currentFile = splitOccurrences[counter];
					String linesInFile = splitOccurrences[counter + 1];
					frequency += splitOccurrences[counter + 1].split(",").length;
					counter += 2;
					output += " " + currentFile + " on lines: " + linesInFile;
				}

				System.out.println(output.replaceAll("#", Integer.toString(frequency)));

			}
		}

	}

	private static void loadTree(ArrayList<String> lineList) {
		// for every line in file
		for (int i = 0; i < lineList.size(); i++) {
			// split line by spaces
			String[] words = lineList.get(i).split(" ");

			// for every word in the line
			for (String word : words) {

				BSTreeNode<String> treeWord;

				// remove all punctuation attached to the word and make it lowercase
				String formattedword = word.replaceAll("\\p{Punct}", "").toLowerCase();

				// try catch for null pointer exception
				// throws null pointer in the tree is empty
				try {
					// search the tree for word
					treeWord = wordTree.search(formattedword);
					// if word is not in the tree
					if (treeWord == null) {

						// add word to tree
						wordTree.add(formattedword);
						// find the BSTreeNode for the added word
						treeWord = wordTree.search(formattedword);
						// add an occurrence for current file and line number
						treeWord.setOccurrences(fileName + ":" + (i + 1));

					} else {
						// if the word is already in the tree, get its occurrences
						String prevOccurences = treeWord.getOccurrences();
						// split by files
						String[] splitOccurrences = prevOccurences.split(":");

						int counter = 0;
						boolean found = false;
						// loop through occurrences
						while (counter < splitOccurrences.length) {
							// if filename is in occurrences
							if (fileName.equals(splitOccurrences[counter])) {
								// add the line number to the occurrences
								splitOccurrences[counter + 1] += "," + (i + 1);
								found = true;
							}
							counter += 2;
						}
						// if file is not in occurrences add new file and line number
						if (!found) {
							treeWord.setOccurrences(prevOccurences + ":" + fileName + ":" + (i + 1));
						} else {
							// add occurrences back together and save to BSTreeNode
							treeWord.setOccurrences(String.join(":", splitOccurrences));
						}

					}
				} catch (Exception e) {
					// if tree is empty, add word to tree with file and current line
					wordTree.add(formattedword);
					treeWord = wordTree.search(formattedword);

					treeWord.setOccurrences(fileName + ":" + (i + 1));
				}

			}
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
