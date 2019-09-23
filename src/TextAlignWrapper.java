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
            default: System.out.print("emmlua");
        }
    }

    public void leftAlignment(){

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

    public void rightAlignment()
    {
        int totalWordCounter = 0;
        int iterationWordCounter = 0;
        int currentIterationCharacterCounter = 0;


        //CHECK IF ==  ID REQUIRED INSTEAD
        while (totalWordCounter < words.length)
        {
            //Check if iterationWordCounter is less than the lineLength
            if(currentIterationCharacterCounter < lineLength)
            {

            }
        }
    }


}
