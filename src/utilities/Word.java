package utilities;

import java.util.ArrayList;

public class Word {
  private String word;
  private ArrayList<Occurrence> occurrences;

  public Word(String word) {
    this.word = word;
    this.occurrences = new ArrayList<Occurrence>();
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

  public ArrayList<String> getFileNamesAndLineNums() {
    ArrayList<String> fileNamesAndLineNums = new ArrayList<String>();

    for (Occurrence occurrence : this.occurrences) {
      fileNamesAndLineNums.add(
          "found in file: "
              + occurrence.getFileName()
              + " found on lines: "
              + occurrence.getLineNumbers()
              + ",");
    }

    return fileNamesAndLineNums;
  }

  public String printWordAndOccurrences() {
    String output =
        "Key : ===" + this.word + "=== number of entries: " + Integer.toString(this.getFrequency());

    for (String toAppend : this.getFileNamesAndLineNums()) {
      output += toAppend;
    }

    return output;
  }
}
