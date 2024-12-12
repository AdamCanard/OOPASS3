package utilities;

import java.util.ArrayList;

public class Word {
	private String word;
	private ArrayList<Occurrence> occurrences;

	public Word(String word, ArrayList<Occurrence> occurrences) {
		this.word = word;
		this.occurrences = occurrences;
	}

	public String getWord() {
		return this.word;
	}

	public ArrayList<Occurrence> getOccurrences() {
		return this.occurrences;
	}

	public void addOccurrence(Occurrence occurrence) {
		this.occurrences.add(occurrence);
	}

	public Integer getFrequency() {
		Integer frequency = 0;
		for (Occurrence occurrence : this.occurrences) {
			frequency += occurrence.getLineNumbers().size();
		}

		return frequency;
	}
}
