import java.util.LinkedList;
import java.util.List;

public class TextAlignWrapper {

    private int lineLength;
    private String[] words;

    public TextAlignWrapper(int lineLength, String[] words, char alignMode)
    {
        this.lineLength = lineLength;
        this.words = words;

        //Call the correct method based on the alignMode.
        callAlignMethod(alignMode);
    }

    public void callAlignMethod(char alignMode)
    {
        switch (alignMode) {
            case 'L': leftAlignment();
                      break;
            case 'R': rightAlignment();
                break;
            case 'C': //centreAlignment();
                break;
            default: System.out.print("Not Ready yet");
        }
    }

    public void leftAlignment(){
        //TODO -> new line and next character is \\n....
        int charCounter = 0;
        for (String word: words) {
            //Check if the character is the new break line symbol.
            if(word.matches("\\n")) {
                System.out.println();
                charCounter = 0;
                continue;
            }
            //Break it into 4 different cases.
            //Case 1 - Write on a new line (counter == 0) and character not whiteSpace
            //Case 2 - Write on a new line and next character is space
            //Case 3 - Write on a used line with enough space for the new word
            //Case 4 - Write on a used line with no space for the new word - So change line and fist word cannot be space.
            if(charCounter == 0 && !word.matches("\\s+"))
            {
                if(word.length() >= lineLength)
                    System.out.println(word);
                else {
                    System.out.print(word);
                    charCounter += word.length();
                }
            }else if(charCounter == 0 && word.matches("\\s+"))
                continue;
            else if(word.length() <= lineLength - charCounter)
            {
                System.out.print(word);
                charCounter += word.length();
            }else if(word.length() > lineLength - charCounter && !word.matches("\\s+")){
                System.out.print("\n" + word);
                charCounter = word.length();
            }
        }
    }

    public void rightAlignment(){

        int characterCount = 0;
        List<String> wordsToPrint = new LinkedList<>();
        StringBuilder tempLine = new StringBuilder();
        for (String word : words) {
            //Case 1 - If the char is a new line add the current line to the list and start in a new one
            //Case 2 - If new line and word is bigger than the line length
            //Case 3 - Used line and words fits
            //Case 4 - Used line and word does not fits.

            if(word.matches("\\n"))
            {
                characterCount = 0;
                wordsToPrint.add(tempLine.toString());
                tempLine = new StringBuilder();
            }
            else if(characterCount == 0 && word.length() >= lineLength){

                    wordsToPrint.add(word);
                    tempLine = new StringBuilder();
            }else {
                if(characterCount + word.length() <=lineLength)
                {
                    tempLine.append(word);
                    characterCount += word.length();
                }else {
                    wordsToPrint.add(tempLine.toString());
                    //If new word is space then do not add it at the beginning of the new line.
                    if(!word.matches("\\s+")){
                        characterCount = word.length();
                        tempLine = new StringBuilder(word);
                    }else {
                        tempLine = new StringBuilder();
                        characterCount =0;
                    }
                }
            }
        }
        for (String line: wordsToPrint) {
            //Remove the last space if any.
            if(line.lastIndexOf(" ") == line.length()-1 && line.length()>0)
                line = line.substring(0, line.length() - 1);

            int space = lineLength - line.length();
            printEmptySpaces(space);
                System.out.println(line);
        }
    }

    public void printEmptySpaces(int numOfSpaces)
    {
        for (int i = 0; i < numOfSpaces ; i++)
            System.out.print(" ");

    }

    public void centreAlignment()
    {
        int wordsCounter = 0;
        int currentLineCharCount = 0;
        List<String> wordsToBePrinted = new LinkedList<>();

        while (wordsCounter < words.length)
        {

            //Case 1 - Empty line and read space or new line symbol
            //Case 2 - Empty line and word is greater than the limit
            //Case 3 - Used Line and read new line symbol
            //Case 4 - Write on a used line with enough space for the new word
            //Case 5 - Write on a used line with not enough space for the new word
            if(currentLineCharCount == 0 && (words[wordsCounter].matches("\\s+") ||  words[wordsCounter].matches("\\n")))

                wordsCounter ++;
            else if(currentLineCharCount ==0 && words[wordsCounter].length() >=0)
            {
                System.out.print(words[wordsCounter]);
                wordsCounter++;
            }else if(currentLineCharCount !=0 && words[wordsCounter].matches("\\n"))
            {
                //if the last element is space remove it
                if(wordsToBePrinted.get(wordsToBePrinted.size()-1).matches("\\s+"))
                {
                    currentLineCharCount --;
                    wordsToBePrinted.remove(wordsToBePrinted.size()-1);
                }

            }
        }

    }


}
