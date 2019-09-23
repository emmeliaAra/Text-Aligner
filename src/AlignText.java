public class AlignText {

    public static void main(String[]args){

        //Check the size of args array. If size < 2 print appropriate message.
        if(args.length < 2)
            printMessage();
        else{
            //Read the first argument and parse the second to and integer.
            //Catch exception if second arg is not a number.
            String fileName = args[0];
            int lineLength = 0;
            try{
                lineLength = Integer.parseInt(args[1]);
            }catch (NumberFormatException ex)
            {
               printMessage();
            }
            //Set align mode by default to 'L' and then check if there is a third command line argument
            //If there is one get the give alignMode.
            char alignMode = 'L';
            if(args.length == 3)
            {
                if(args[2].length() == 1 && (args[2].equalsIgnoreCase("L") || args[2].equalsIgnoreCase("R") ||
                                             args[2].equalsIgnoreCase("C") || args[2].equalsIgnoreCase("H")))
                    alignMode = args[2].charAt(0);
                else {
                    printMessage();
                    System.exit(0);
                }
            }

            String[] fileContentArray = FileUtil.readFile(fileName);
            System.out.print(fileContentArray.length);
            String fileContent = Helper.convertStringArrayToString(fileContentArray);
            String[] words = Helper.split(fileContent);

            TextAlignWrapper textAlignWrapper = new TextAlignWrapper(lineLength,words,alignMode);
            }
    }

    public static void printMessage()
    {
        System.out.println("usage: java AlignText file_name line_length [align_model]");
    }
}
