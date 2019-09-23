public class TextAlignWrapper {

    public void leftAlignment(int lineLength, String[] words){

        int charCounter = 0;
        for (String word: words) {
            //Break it into 4 different cases.

            //Case 1 - Write on a new line (counter == 0) and character not whiteSpace
            if(charCounter == 0 && !word.matches("\\s+"))
            {
                if(word.length() >= lineLength)
                    System.out.println(word);
                else {
                    System.out.print(word);
                    charCounter += word.length();
                }
            //Case 2 - Write on a new line and next character is space
            }else if(charCounter == 0 && word.matches("\\s+"))
                continue;
            //Case 3 - Write on a used line with enough space for the new word
            else if(word.length() <= lineLength - charCounter)
            {
                System.out.print(word);
                charCounter += word.length();
            //Case 4 - Write on a used line with no space for the new word - So change line and fist word cannot be space.
            }else if(word.length() > lineLength - charCounter && !word.matches("\\s+")){
                System.out.print("\n" + word);
                charCounter = word.length();
            }
        }
    }


}
