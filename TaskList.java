/**
//Date
//Import the Calendar and SimpleDateFormat class to display date and create file name
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

//Array
//Import the ArrayList class to temporary store data from file
import java.util.ArrayList;

//Input
//Import the Scanner class to read user input
import java.util.Scanner;

public class TaskList {
    public static String displayDate() {
        final GregorianCalendar now = new GregorianCalendar(2020, 3, 5); 
        
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM dd, YYYY");
        System.out.println(dateFormatter.format(now.getTime()));

        final String fileNameDate = String.format("%d_%d_%d", (now.get(GregorianCalendar.MONTH ) + 1), now.get(GregorianCalendar.DATE), now.get(GregorianCalendar.YEAR));

        return fileNameDate;
    }

    public static void readFolderFile(String folderName, String fileName, ArrayList<String> fileData){
        if(checkFileExist(folderName, fileName)){
            readFile(folderName, fileName, fileData);

            for (int i = 0; i < fileData.size(); i+2){
                System.out.println("#" + i+1 + ". " + fileData.get(i)) + "    " + fileData.get(i+1);
            }
        }
    }

    public static void writeFolderFile(String folderName, String fileName){
        if(checkFileExist(folderName, fileName)){
            Scanner myWriter = new Scanner(System.in);
            System.out.println("Add task (Character should be less than 35): ");
            String usrAddTask;
            do {
                usrAddTask = myWriter.nextLine();
            } while (usrAddTask.length() <= 35);
            writeFile(folderName, fileName, usrAddTask);
            writeFile(folderName, fileName, "Incomplete");
        }
    }

    public static void mainMenu(String folderName, String fileName, ArrayList<String> fileData){
        Scanner myInput = new Scanner(System.in);
        System.out.println("Main Menu: \n1)Add Task\n2)Remove Task\n3)Change");
    }

    public static void main(final String[] args) {
        final String sampleName = displayDate();
        final String usr = "default";
        final String folderPath = ("app/" + usr);
        ArrayList<String> taskListData = new ArrayList<String>();
        createDir(folderPath);
        if(createFile(folderPath, sampleName)){
            readFolderFile(folderPath, sampleName, taskListData);
            //getFileInfo(folderPath, sampleName);
            listFolderFile(folderPath);
            

            //deleteFile(folderPath, sampleName);
            //deleteFolder(folderPath);
        }
    }
}
**/