/**
 * Main class used to align a give text from a file.
 */
public class AlignText {

    /**
     * Main method that accepts and validates the arguments.
     * Will either print error message or pass the arguments to the appropriate classes to align the text.
     * @param args an array that contains command line arguments.
     */
    public static void main(String[]args) {

        /*Check the size of args array. If size < 2 or size >3 print appropriate message.
         *OtherWise -> Read the first argument and parse the second to an integer.
         *          -> Catch exception if second arg is not a number.
         *          -> Or throw one if is <0.
         */
        if (args.length < 2) {
            printMessage();
        } else {
            String fileName = args[0];
            int lineLength = 0;
            try {
                lineLength = Integer.parseInt(args[1]);
                if (lineLength < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
               printMessage();
               System.exit(0);
            }
            //Set align mode by default to 'L' and then check if there is a third command line argument
            char alignMode = 'L';
            if (args.length > 2) {
                if (args[2].length() == 1 && (args[2].equalsIgnoreCase("L") || args[2].equalsIgnoreCase("R")
                                            || args[2].equalsIgnoreCase("C") || args[2].equalsIgnoreCase("H"))) {
                    alignMode = args[2].toUpperCase().charAt(0);
                } else {
                    printMessage();
                    System.exit(0);
                }
            }
            String[] fileContentArray = FileUtil.readFile(fileName);
            String fileContent = Helper.convertStringArrayToString(fileContentArray);
            String[] words = Helper.split(fileContent);

            TextAlignWrapper textAlignWrapper = new TextAlignWrapper(lineLength, words);
            textAlignWrapper.alignText(alignMode);
            }
    }

    /**
     * This method prints the error message.
     */
    public static void printMessage() {
        System.out.println("usage: java AlignText file_name line_length [align_mode]");
    }
}
