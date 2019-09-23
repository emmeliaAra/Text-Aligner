public class AlignText {

    public static void main(String[]args){
        //Check the size of args array. If size < 2 print appropriate message.
        if(args.length < 2)
            printMessage();
        else{
            //Read the first argument and parse the second to and integer. Catch exception if second arg is not a number.
            String fileName = args[0];
            int lineLength = 0;
            try{
                lineLength = Integer.parseInt(args[1]);
            }catch (NumberFormatException ex)
            {
               printMessage();
            }

            String[] fileContentArray = FileUtil.readFile(fileName);
            String fileContent = Helper.convertStringArrayToString(fileContentArray);
            String[] words = Helper.split(fileContent);

            TextAlignWrapper textAlignWrapper = new TextAlignWrapper();
            textAlignWrapper.leftAlignment(lineLength,words);

            }
    }

    public static void printMessage()
    {
        System.out.println("usage: java AlignText file_name line_length");
    }
}
