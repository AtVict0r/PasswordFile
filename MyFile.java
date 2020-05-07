import java.io.File;  // Import the File class to create file
import java.io.FileWriter;   // Import the FileWriter class to store data in file
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // Import the ArrayList class to store list of data with undetermined size
import java.util.Arrays; // Import the Arrays class

class MyFile {
  private String dirName = null;
  private final String fileFormat = ".txt";
  private String filePath = null;

  private File newDir = null;
  private File newFile = null;

  public MyFile(String folderNameString){
    // Create new directory using argument as directory name
    newDir = new File(folderNameString);
    createDir();
    this.dirName = folderNameString;
  }
  
  public MyFile(String folderNameString, String fileNameString){
    // Create new directory using argument as directory name
    newDir = new File(folderNameString);
    createDir();
    this.dirName = folderNameString;

    // Create new file using argument as file name
    this.filePath = this.dirName + "/" + fileNameString + fileFormat;   
    newFile = new File(filePath);
    createFile();
  }

  public void createDir() {
    try {
      if(newDir.mkdirs()){
      System.out.println("The user has been successfully created");
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
        System.out.println("List created: " + newFile.getName());
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
        System.out.println("Saved List.");
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

  public boolean getDirInfo() {
    return newDir.exists();
  }

  public boolean isEmpty(){
    //System.out.println("File size in bytes " + newFile.length());
    if(newFile.length() > 1){
      return false;
    }
    return true;
  }

  public String getFileSize(String fileNameString){
    File getFileSize = new File(this.dirName + "/" + fileNameString);
    if(getFileSize.length() != 0){
      return "Not Empty";
    }
    return "Empty";
  }

  public void filePath() {
    if (newFile.exists()) {
      System.out.println("Absolute path: " + newFile.getAbsolutePath());
    } else {
      System.out.println("The file does not exist.");
    }
  }

  public ArrayList<String> getFileNames(){
    String[] tempFileNameArr = newDir.list();
    Arrays.sort(tempFileNameArr);
    ArrayList<String> myFiles = new ArrayList<String>(Arrays.asList(tempFileNameArr));
    return myFiles;
  }

  public void deleteFile() { 
    if (newFile.delete()) { 
      System.out.println("Deleted the file: " + newFile.getName());
    } else {
      System.out.println("Failed to deleten file: " + newFile.getName());
    } 
  } 

  public boolean deleteDir() {
    String[] myFiles = newDir.list();
    for(String trashFile : myFiles){
      File deleteFile = new File(this.dirName + "/" + trashFile);
      if (deleteFile.delete()) { 
      System.out.println("Deleted file: " + deleteFile.getName() + " from " + this.dirName);
      } else {
      System.out.println("Failed to delete file: " + deleteFile.getName());
      } 
    }
    if (newDir.delete()) { 
      System.out.println("Removed User: " + newDir.getName());
      return true;
    } else {
      System.out.println("Failed to Remove User: " + newDir.getName());
      return false;
    } 
  }  
}