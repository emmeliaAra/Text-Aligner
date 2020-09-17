import java.util.LinkedList;
import java.util.List;

/**
 * This class holds the implementation for the text alignment.
 */
public class TextAlignWrapper {

    private int lineLength;
    private String[] words;

    /**
     * This is the constructor of this class.
     * @param lineLength the max number of characters in a line.
     * @param words the text to align split into single words.
     */
    public TextAlignWrapper(int lineLength, String[] words) {
        this.lineLength = lineLength;
        this.words = words;
    }

    /**
     * This method will be called to align a text.
     * @param alignMode the way that the text will be aligned.
     */
    public void alignText(char alignMode) {
        if(lineLength > 0){
            if (alignMode == 'H') {
                hyphenating();
            }
            else {
                leftRightCenterAlignment(alignMode);
            }
        }
        System.out.println("Done!!!");
    }

    /**
     * This method will perform will divide the text into lines based on the lineLength
     * And then call the printLines method to align the lines either LEFT, RIGHT or CENTRE.
     * Must remain private because it cannot be accessed from outside this class.
     * @param alignmentMode the mode that the text will be aligned.
     */
    private void leftRightCenterAlignment(char alignmentMode) {

        int characterCount = 0;
        List<String> wordsToPrint = new LinkedList<>();
        StringBuilder tempLine = new StringBuilder();

        /*If statements explanation
         *Case 1 - If the char is a new line add the current line to the list and start in a new one
         *Case 2 - If new line and word is a space
         *Case 3 - If new line and word is bigger than the line length
         *Case 4 - Used line and words fits
         *Case 5 - Used line and word does not fits.
         */
        for (String word : words) {

            if (word.matches("\\n")) {
                characterCount = 0;
                wordsToPrint.add(tempLine.toString());
                tempLine = new StringBuilder();
            } else if (characterCount == 0 && word.matches("\\s+")) {
                continue;
            } else if (characterCount == 0 && word.length() >= lineLength) {
                    wordsToPrint.add(word);
                    tempLine = new StringBuilder();
            } else if (characterCount + word.length() <= lineLength) {
                tempLine.append(word);
                characterCount += word.length();
            } else if (characterCount + word.length() > lineLength) {
                wordsToPrint.add(tempLine.toString());
                if (!word.matches("\\s+")) {
                    characterCount = word.length();
                    tempLine = new StringBuilder(word);
                } else {
                    tempLine = new StringBuilder();
                    characterCount = 0;
                }
            }
        }
        //Call this method to
        printLines(alignmentMode, wordsToPrint);
    }

    /**
     * This method will print the lines of text based on the alignMode provided.
     * @param alignMode the mode to align the text.
     * @param wordsToPrint a list of the lines to be printed.
     */
    public void printLines(char alignMode, List<String> wordsToPrint) {
        for (String line: wordsToPrint) {
            if (alignMode == 'L') {
                System.out.println(line);
            } else {
                //Remove the last space if any.
                if (line.lastIndexOf(" ") == line.length() - 1 && line.length() > 0) {
                    line = line.substring(0, line.length() - 1);
                }

                int space = lineLength - line.length();
                if (alignMode == 'C') {
                    space = space / 2 + space % 2;
                }
                Helper.printSpaces(space);
                System.out.println(line);
            }
        }
    }

    /**
     * This method is used to do break the text by hyphenating long words
     * Must remain private because it cannot be accessed from outside this class.
     */
    private void hyphenating() {

        int charCounter = 0;

        /*If statements explanation
         *Case 1 - If the char is a new line change line.
         *Case 2 - If new line and word is a space.
         *Case 3 - If the word fits into the current line
         *Case 4 - If the word does not fit into the current line
         */
        for (int i = 0; i < words.length; i++) {
            if (words[i].matches("\\n")) {
                System.out.println();
                charCounter = 0;
                continue;
            }
            if (charCounter == 0 && words[i].matches("\\s+")) {
                continue;
            } else if (words[i].length() <= lineLength - charCounter) {
                System.out.print(words[i]);
                charCounter += words[i].length();
                if (lineLength == charCounter) {
                    System.out.println();
                    charCounter = 0;
                }
            } else if (words[i].length() > lineLength - charCounter) {
                //Store the numbers of characters that can still fit in the line.
                int partialCharNumber = lineLength - charCounter - 1;
                String partialWord = words[i].substring(0, partialCharNumber);
                words[i] = words[i].substring(partialCharNumber);
                charCounter = 0;
                if (partialCharNumber > 0) {
                    System.out.print(partialWord + "-" + "\n");
                } else {
                    System.out.println();
                }
                //To loop over the remaining word.
                if (words[i].length() != 0) {
                    i = i - 1;
                }

            }
        }
    }
}
