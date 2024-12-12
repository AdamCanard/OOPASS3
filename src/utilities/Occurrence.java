package utilities;

import java.util.ArrayList;

public class Occurrence {
	private String fileName;
	private ArrayList<Integer> lineNumbers;

	public Occurrence(String fileName, ArrayList<Integer> lineNumbers) {
		this.fileName = fileName;
		this.lineNumbers = lineNumbers;
	}

	public String getFileName() {
		return this.fileName;
	}

	public ArrayList<Integer> getLineNumbers() {
		return this.lineNumbers;
	}

	public void addLineNumber(Integer lineNumber) {
		this.lineNumbers.add(lineNumber);
	}

}