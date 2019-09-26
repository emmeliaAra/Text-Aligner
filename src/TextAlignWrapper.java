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
        if(alignMode == 'H')
            hyphenating();
        else
            textAlign(alignMode);
    }

    public void textAlign(char alignmentMode){

        int characterCount = 0;
        List<String> wordsToPrint = new LinkedList<>();
        StringBuilder tempLine = new StringBuilder();
        for (String word : words) {
            //Case 1 - If the char is a new line add the current line to the list and start in a new one
            //Case 2 - If new line and word is a space
            //Case 3 - If new line and word is bigger than the line length
            //Case 4 - Used line and words fits
            //Case 5 - Used line and word does not fits.

            if(word.matches("\\n")) {
                characterCount = 0;
                wordsToPrint.add(tempLine.toString());
                tempLine = new StringBuilder();
            }else if(characterCount == 0 && word.matches("\\s+"))
                continue;
            else if(characterCount == 0 && word.length() >= lineLength){

                    wordsToPrint.add(word);
                    tempLine = new StringBuilder();
            }else if(characterCount + word.length() <=lineLength) {
                tempLine.append(word);
                characterCount += word.length();
            }
            else if(characterCount + word.length() >lineLength){
                wordsToPrint.add(tempLine.toString());
                if(!word.matches("\\s+")){
                    characterCount = word.length();
                    tempLine = new StringBuilder(word);
                }else {
                    tempLine = new StringBuilder();
                    characterCount =0;
                }
            }

        }
        printLines(alignmentMode,wordsToPrint);
    }


    public void hyphenating(){

        int charCounter = 0;
        for (int i=0; i< words.length; i++) {

            //Check if the character is the new break line symbol.
            if(words[i].matches("\\n")) {
                System.out.println();
                charCounter = 0;
                continue;
            }
            if(charCounter == 0 && words[i].matches("\\s+"))
                continue;
            else if(words[i].length() <= lineLength - charCounter)
            {
                System.out.print(words[i]);
                charCounter += words[i].length();
                if(lineLength == charCounter)
                {
                    System.out.println();
                    charCounter=0;
                }
            }else if(words[i].length() > lineLength - charCounter ){

                int partialCharNumber = lineLength - charCounter-1;
                String partialWord = words[i].substring(0,partialCharNumber);
                words[i]= words[i].substring(partialCharNumber);
                charCounter = 0;
                if(partialCharNumber>0)
                    System.out.print(partialWord + "-" + "\n");
                else
                    System.out.println();
                if(words[i].length() !=0)
                    i= i-1;

            }
        }
    }

    public void printLines(char alignMode, List<String> wordsToPrint)
    {
        for (String line: wordsToPrint) {
            if(alignMode == 'L')
                System.out.println(line);
                //Remove the last space if any.
            else{
                if(line.lastIndexOf(" ") == line.length()-1 && line.length()>0)
                    line = line.substring(0, line.length() - 1);

                int space = lineLength - line.length();
                if(alignMode == 'C')
                    space = space/2 + space%2;

                printSpaces(space);
                System.out.println(line);
            }

        }
    }

    public void printSpaces(int numOfSpaces)
    {
        for (int i = 0; i < numOfSpaces ; i++)
            System.out.print(" ");

    }
}
