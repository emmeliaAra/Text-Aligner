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

        int wordsCounter = 0;
        int currentLineCharCount = 0;
        int wordsCheckPoint = 0;
        int wordsToBePrinted = 0;

        while (wordsCounter < words.length) {

            //Check if this is a new line
            //Case 1 - Empty line and read space or new line symbol
            //Case 2 - Empty line and word is greater than the limit
            //Case 3 - Used Line and read new line symbol
            //Case 4 - Write on a used line with enough space for the new word
            //Case 5 - Write on a used line with not enough space for the new word
            if (currentLineCharCount == 0 && (words[wordsCounter].matches("\\s+") || words[wordsCounter].matches("\\n")))
            {
                wordsCounter++;
                wordsCheckPoint++;
                continue;
            }else if(currentLineCharCount == 0 && words[wordsCounter].length() >= lineLength)
            {
                System.out.println(words[wordsCounter]);
                wordsCounter++;
                wordsCheckPoint++;

            }else if(currentLineCharCount !=0 && words [wordsCounter].matches("\\n")){

                printEmptySpaces(lineLength - currentLineCharCount);
                for (int i = wordsCheckPoint; i < wordsCheckPoint + wordsToBePrinted; i++) {
                    System.out.print(words[i]);
                }
                wordsCheckPoint += wordsToBePrinted +1;
                currentLineCharCount = 0;
                wordsToBePrinted = 0;
                wordsCounter ++;
                System.out.println();

            }else if(currentLineCharCount + words[wordsCounter].length() <= lineLength) {
                currentLineCharCount += words[wordsCounter].length();
                wordsToBePrinted++;
                wordsCounter++;
            }else if(currentLineCharCount + words[wordsCounter].length() > lineLength) {
                int tempWordsToPrint = wordsToBePrinted;
                int spaces =  lineLength - currentLineCharCount;

                //if the last word is space don't print it
                if(words[wordsCounter -1].matches("\\s+"))
                {
                    tempWordsToPrint = wordsToBePrinted -1;
                    spaces = lineLength - currentLineCharCount +1;
                }
                printEmptySpaces(spaces);
                for (int i = wordsCheckPoint; i <= wordsCheckPoint + tempWordsToPrint; i++) {
                    System.out.print(words[i]);
                }

                wordsCheckPoint += wordsToBePrinted;
                currentLineCharCount = 0;
                wordsToBePrinted = 0;
                System.out.println();
            }
        }
    }

    public void printEmptySpaces(int numOfSpaces)
    {
        for (int i = 0; i < numOfSpaces ; i++)
            System.out.print(" ");

    }


}
