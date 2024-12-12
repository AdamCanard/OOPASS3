package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import implementations.BSTree;
import implementations.BSTreeNode;
import utilities.Occurrence;
import utilities.Word;

public class WordTracker {

	public static void main(String[] args) {

		BSTree<Word> wordTree = new BSTree<Word>();

		String fileName = args[0];
		String outputType = args[1];
		String outputFile = "";
		if (args.length > 2) {
			outputFile = args[3];
		}

		ArrayList<String> lineList = readFile(fileName);
		for (int i = 0; i < lineList.size(); i++) {
			String[] words = lineList.get(i).split(" ");
			for (String word : words) {
				Word newWord = new Word(word);
				BSTreeNode<Word> treeWord;
				try {
					treeWord = wordTree.search(newWord);
				} catch (Exception e) {
					ArrayList<Integer> lines = new ArrayList<Integer>();
					lines.add(i);
					Occurrence newOccurence = new Occurrence(fileName, lines);
					newWord.addOccurrence(newOccurence);
					wordTree.add(newWord);
					break;
				}

				// word not in tree
				if (treeWord == null) {
					ArrayList<Integer> lines = new ArrayList<Integer>();
					lines.add(i);
					Occurrence newOccurence = new Occurrence(fileName, lines);
					newWord.addOccurrence(newOccurence);
					wordTree.add(newWord);
				} else {
					Word wordFromTree = treeWord.getElement();
					ArrayList<Occurrence> wordOccurrences = wordFromTree.getOccurrences();
					boolean found = false;
					for (int j = 0; j < wordOccurrences.size(); j++) {
						Occurrence occurrence = wordOccurrences.get(j);
						if (occurrence.getFileName().equals(fileName)) {
							occurrence.addLineNumber(i);
							found = true;
						}
					}
					if (!found) {
						ArrayList<Integer> lines = new ArrayList<Integer>();
						lines.add(i);
						Occurrence newOccurence = new Occurrence(fileName, lines);
						wordFromTree.addOccurrence(newOccurence);
					}

				}

			}
		}
		while (!wordTree.isEmpty()) {
			BSTreeNode<Word> node = wordTree.removeMin();
			Word word = node.getElement();
			System.out.println(word.printWordAndOccurrences());
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
