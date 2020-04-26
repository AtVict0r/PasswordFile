import java.io.File;  // Import the File class to create file
import java.io.FileWriter;   // Import the FileWriter class to store data in file
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // Import the ArrayList class to store list of data with undetermined size

class MyFile {
  private String folderName = null;
  private String fileName = null;
  private final String fileFormat = ".txt";
  private String filePath = null;

  private File newDir = null;
  private File newFile = null;

  public MyFile(String folderNameString){
    // Create new directory using argument as directory name
    newDir = new File(folderNameString);
    createDir();
  }
  
  public MyFile(String folderNameString, String fileNameString){
    // Create new directory using argument as directory name
    newDir = new File(folderNameString);
    createDir();

    // Create new file using argument as file name
    this.filePath = folderNameString + "/" + fileNameString + fileFormat;   
    newFile = new File(filePath);
    createFile();
  }

  public void createDir() {
    try {
      if(newDir.mkdirs()){
      System.out.println("The folder has been successfully created");
      } /** else {
        System.out.println("File already exists.");
      } **/
    } catch(Exception e) {
      // if any error occurs
      e.printStackTrace();
    }
  }

  public void createFile() {
    try {
      if (newFile.createNewFile()) {
        System.out.println("File created: " + newFile.getName());
      } /** else {
        System.out.println("File already exists.");
      } **/
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void writeFile(ArrayList<String> fileData) {
    try {
      if(newFile.canWrite()){
        FileWriter myWriter = new FileWriter(filePath);
        for(String line : fileData){
          myWriter.write(line + "\n");
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } else {
        System.out.println("The file is not writeable.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public ArrayList<String> readFile() {
    ArrayList<String> myData = new ArrayList<String>(); // Create an ArrayList object
    try {
      if(newFile.canRead()){
        Scanner myReader = new Scanner(newFile);
        while (myReader.hasNextLine()) {
        myData.add(myReader.nextLine());
        }
        myReader.close();
      } else {
        System.out.println("The file is not readable.");
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return myData;
  }

  public boolean getFileInfo() {
    return newFile.exists();
  }

  public void fileInfo() {
    if (newFile.exists()) {
      System.out.println("Absolute path: " + newFile.getAbsolutePath());
      System.out.println("File size in bytes " + newFile.length());
    } else {
      System.out.println("The file does not exist.");
    }
  }

  public void deleteFile(String folderName, String fileName) { 
    if (newFile.delete()) { 
      System.out.println("Deleted the file: " + newFile.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
  } 

  public void deleteFolder(String folderName) {  
    if (newFile.delete()) { 
      System.out.println("Deleted the folder: " + newFile.getName());
    } else {
      System.out.println("Failed to delete the folder.");
    } 
  }  
}