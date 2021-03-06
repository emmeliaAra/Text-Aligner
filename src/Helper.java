/**
 * This class an Abstract class that provide different methods to help achieve the alignment process.
 */
public abstract class  Helper {

    /**
     * Splits the given string into sub parts when space appears but also stores the spaces.
     * @param text to split when spaces appear
     * @return the array that is made from the strings that are separated by the spaces.
     */
    public static String[] split(String text) {
        return text.split("((?<=\\s)|(?=\\s+))");
    }

    /**
     * Converts an array of String to a single string. Add the Break line symbol whenever a new line is added.
     * @param stringArray the array to convert to a string
     * @return a string.
     */
    public static String convertStringArrayToString(String[] stringArray) {
        StringBuilder text = new StringBuilder();
        for (String element:stringArray) {
            text.append(element + "\n");
        }
        return text.toString();
    }

    /**
     * This method is used to print empty spaces and stay in the same line.
     * @param numOfSpaces the number of spaces to be printed.
     */
    public static void printSpaces(int numOfSpaces) {
        if (numOfSpaces > 0) {
            String emptySpaces = new String(new char[numOfSpaces]).replace("\0", " ");
            System.out.print(emptySpaces);
        }
    }
}
