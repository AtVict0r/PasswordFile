import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner; // Import the Scanner class to read user input
import java.util.Arrays; // Import the Arrays class

class TaskList {
  private MyFile alphaNum = null;
  private Scanner input = null;
  private MyPass usr = null;

  private ArrayList<String> myFileList = null;
  private ArrayList<String> checkList = null;
  private ArrayList<String> statusList = null;

  private String fileName = null;
  private int listSize = 0;

  public TaskList(MyPass auth, String fileNameString){
    this.fileName = fileNameString;
    this.usr = auth;
    this.alphaNum = new MyFile(this.usr.getUsrDir(), this.fileName);
  }

  private char setUsrOpt(){
    this.input = new Scanner(System.in);
    return this.input.next().charAt(0);
  }

  private String setUsrTask(){
    this.input = new Scanner(System.in);
    return this.input.nextLine();
  }

  private int setTaskID(){
    this.input = new Scanner(System.in);
    return this.input.nextInt() - 1;
  }

  private boolean chkID(int taskIndex, int maxSize){
    if((taskIndex >= 0) && (taskIndex < maxSize)){
      return true;
    } else {
      System.out.print("\nInvalid Response! ID " + taskIndex + " Does Not Exist");
      return false;
    }
  }

  public void dispFileList(){
    this.myFileList = this.alphaNum.getFileNames();
    if(!this.alphaNum.getDirInfo()){
      System.out.println("This User Does Not Exist.");
    } else if (this.myFileList.size() == 0){
      System.out.println("This User Does Not Own Any List.");
      //Add New List
      makNewList();
    } else {
      System.out.println("\nShowing " + usr.getUsrNam() + " Lists: ");
      String leftA = "#%-4s %-8s %-9s %n";
      System.out.format(leftA, "ID", "FILENAME", "SIZE");
      for(int i = 0, j = 1; i < this.myFileList.size(); i++, j++){
      String dispFileName = this.myFileList.get(i).substring(0,2) + "/" + this.myFileList.get(i).substring(2,4) + "/" + this.myFileList.get(i).substring(4,6);
      System.out.format(leftA, j, dispFileName, this.alphaNum.getFileSize(this.myFileList.get(i)));
      }
      loadFileMenu();
    }
  }

  private void makNewList(){
    TaskList list = new TaskList(this.usr, this.fileName);
  }

  public void loadFileMenu(){
    System.out.println("\nFile Menu: \n1) Open New File \n2) Copy File to " + this.fileName + " \n3) Display " + usr.getUsrNam() + " File(s) \n4) Remove all Items in " + this.fileName + " \n5) Delete " + this.fileName + " \n6) Back");
    System.out.print("Choose Option (1, 2, 3 or 4): ");
    switch(setUsrOpt()){
      case '1':{
        openFile();
        break;
      } case '2':{
        copyFile(); // unfinished
        break;
      } case '3':{
        dispFileList();
        break;
      } case '4':{
        ArrayList<String> tempOutputData = new ArrayList<String>(0); // Create an ArrayList object
        this.alphaNum.writeFile(tempOutputData);
        break;
      } case '5':{
        if(this.myFileList.size() > 1){
          deleteList();
        } else {
          System.out.println("\nUser Does Not Have Enough Files to Carry Out This Operation.");
        }
        break;
      } case '6':{
        break;
      } default:{
        System.out.println("\nInvalid Response!");
        loadFileMenu();
      }
    }
  }

  private void openFile(){
    System.out.print("\nOpening New File. ");
    System.out.print("Enter File ID: ");
    final int taskIndex = setTaskID();
    if(chkID(taskIndex, this.myFileList.size())){
      this.alphaNum = new MyFile(this.usr.getUsrDir(), this.myFileList.get(taskIndex));
    } else {
      openFile();
    } 
  }

  private void copyFile(){
    // Open File
    // Get arraylist from file
    // Open new file
    // Save arraylist to new file
    System.out.print("\nCopy New File to " + this.fileName);
    System.out.print("Enter File ID: ");
    final int taskIndex = setTaskID();
    if(chkID(taskIndex, this.myFileList.size())){
      System.out.print("\nCopying " + this.myFileList.get(taskIndex).substring(0,6) + " to " + this.fileName);
      this.alphaNum = new MyFile(this.usr.getUsrDir(), this.myFileList.get(taskIndex));
      ArrayList<String> tempOutputData = this.alphaNum.readFile(); // Create an ArrayList object
      this.alphaNum = new MyFile(this.usr.getUsrDir(), this.fileName);
      for(int i = 1; i < tempOutputData.size(); i+=2){
      tempOutputData.set(i, "In Progress");
      }
      this.alphaNum.writeFile(tempOutputData);
    } else {
      copyFile();
    } 
  }

  public void dispList(){
    sortArrIn();
    if(!this.alphaNum.getFileInfo()){
      loadFileMenu();
    } else if (this.listSize == 0){
      System.out.println("There Are No Item(s) In This List.");
      addNewItem();
    } else {
       ArrayList<String> tempOutputData = this.alphaNum.readFile(); // Create an ArrayList object
      String leftA = "#%-4s %-20s %-11s %n";
      System.out.format(leftA, "ID", "ITEM(S)", "STATUS");
      for(int i = 0, j = 1; i < this.listSize; j++, i+=2){
      System.out.format(leftA, j, tempOutputData.get(i), tempOutputData.get(i + 1));
      }
      checkItem();
    }
  } 

  private void checkItem(){
    ArrayList<String> tempOutputData = this.alphaNum.readFile(); // Create an ArrayList object
    final String itemDon = "Completed!";
    int countOccur = 0;
    for(int i = 1; i < this.listSize * 2; i += 2){
      if(itemDon.equals(tempOutputData.get(i))){
        countOccur++;
      }
    }
    System.out.println("Item Completed: " + countOccur + "/" + this.listSize);
  }

  public int getListSize(){
    return this.listSize;
  }

  private void sortArrIn(){
    ArrayList<String> tempOutputData = this.alphaNum.readFile(); // Create an ArrayList object
    setListSize(tempOutputData.size() / 2);
    this.checkList = new ArrayList<String>();
    this.statusList = new ArrayList<String>();
    for(int i = 0; i < this.listSize * 2; i+=2){
      this.checkList.add(tempOutputData.get(i));
      this.statusList.add(tempOutputData.get(i+1));
    }
  }

  private void setListSize(int listSizeInt){
    if(this.listSize != listSizeInt){
      this.listSize = listSizeInt;
    }
  }

  public void sortArrOut(){
    setListSize(this.checkList.size()); 
    ArrayList<String> tempOutputData = new ArrayList<String>(this.listSize * 2); // Create an ArrayList object
    for(int i = 0; i < this.listSize; i++){
      tempOutputData.add(this.checkList.get(i));
      tempOutputData.add(this.statusList.get(i));
    }
    this.alphaNum.writeFile(tempOutputData);
  }

  public void setStatus(){
    System.out.println("\nSet Status Menu: \n1) Set as Completed \n2) Set as In Progress \n3) Back to Main Menu");
    System.out.print("Choose Option (1, 2 or 3): ");
    switch(setUsrOpt()){
      case '1':{
        dispList();
        markList();
        break;
      } case '2':{
        dispList();
        unmarkList();
        break;
      } case '3':{
        break;
      } default:{
        System.out.println("\nInvalid Response!");
        setStatus();
      }
    }
  }

  private void markList(){
    System.out.print("\nMark Item as Complete. ");
    System.out.print("Enter Task ID : ");
    int taskIndex = setTaskID();
    if(chkID(taskIndex, this.statusList.size())){
      this.statusList.set(taskIndex, "Completed!");
      sortArrOut();
      dispList();
      System.out.print("\nMark New Item as Complete (y/n): ");
      switch(setUsrOpt()){
        case 'y':
        case 'Y':{
          markList();
          break;
        }
        case 'n':
        case 'N':
          break;
        default:{
          System.out.println("\nInvalid Response!");
          markList();
        }
      }
    } else {
      markList();
    }
  }

  private void unmarkList(){
    System.out.print("\nMark Item as In Progress. ");
    System.out.print("Enter Item ID : ");
    final int taskIndex = setTaskID();
    if(chkID(taskIndex, this.statusList.size())){
      this.statusList.set(taskIndex, "In Progress");
      sortArrOut();
      dispList();
      System.out.print("\nMark New Item as In Progress (y/n): ");
      switch(setUsrOpt()){
        case 'y':
        case 'Y':{
          unmarkList();
          break;
        }
        case 'n':
        case 'N':
          break;
        default:{
          System.out.println("\nInvalid Response!");
          unmarkList();
        }
      }
    } else {
      unmarkList();
    }
  }

  public void addItemMenu(){
    System.out.println("\nSelect Add Item Type: \n1) Add Item to List \n2) Insert Item in List \n3) Replace Item in List \n4) Back");
    System.out.print("Choose Option (1, 2, 3 or 4): ");
    switch(setUsrOpt()){
      case '1':{
        addItem();
        break;
      } case '2':{
        insertItem();
        break;
      } case '3':{
        replaceItem();
        break;
      } case '4':{
        break;
      } default:{
        System.out.println("\nInvalid Response!");
        addItemMenu();
      }
    }
  }

  private String getItem(){
    final int maxChar = 20;
    String newTask = null;
    do{
      System.out.println("\nWhat do you need to do? (Limit to " + maxChar + " Characters): ");
      newTask = setUsrTask().trim();
      
      if(newTask.length() > maxChar){
        System.out.println("Item Description Has to be " + maxChar + " Characters or Less");
      }
    } while(newTask.length() > maxChar);
    return newTask;
  }

  private void addItem(){
    System.out.print("\nAdd Item to List. ");
    this.checkList.add(getItem());
    this.statusList.add("In Progress");
    sortArrOut();
    dispList();
    addNewItem();
  }

  private void addNewItem(){
    System.out.print("\nAdd New Item to List (y/n): ");
    switch(setUsrOpt()){
      case 'y':
      case 'Y':{
        addItem();
        break;
      }
      case 'n':
      case 'N':
        break;
      default:{
        System.out.println("\nInvalid Response!");
        addNewItem();
      }
    }
  }

  private void insertItem(){
    System.out.print("\nInsert Item in List. ");
    System.out.print("Enter Item ID: ");
    final int taskIndex = setTaskID();
    if(chkID(taskIndex, this.checkList.size())){
      this.checkList.add(taskIndex, getItem());
      this.statusList.add(taskIndex, "In Progress");
      sortArrOut();
      dispList();
      System.out.print("\nInsert New Item in List (y/n): ");
      switch(setUsrOpt()){
        case 'y':
        case 'Y':{
          insertItem();
          break;
        }
        case 'n':
        case 'N':
          break;
        default:{
          System.out.print("\nInvalid Response!");
          insertItem();
        }
      }
    } else {
      insertItem();
    } 
  }

  private void replaceItem(){
    System.out.print("\nReplace Item in List. ");
    System.out.print("Enter Item ID: ");
    final int taskIndex = setTaskID();
    if(chkID(taskIndex, this.checkList.size())){
      this.checkList.set(taskIndex, getItem());
      this.statusList.set(taskIndex, "In Progress");
      sortArrOut();
      dispList();
      System.out.print("\nReplace New Item in List (y/n): ");
      switch(setUsrOpt()){
        case 'y':
        case 'Y':{
          replaceItem();
          break;
        }
        case 'n':
        case 'N':
          break;
        default:{
          System.out.print("\nInvalid Response!");
          replaceItem();
        }
      }
    } else {
      replaceItem();
    } 
  }

  public void removeItemMenu(){
    System.out.println("\nSelect Remove Item Type: \n1) Remove Item from List \n2) Replace Item in List \n3) Delete List \n4) Back");
    System.out.print("Choose Option (1, 2, 3 or 4): ");
    switch(setUsrOpt()){
      case '1':{
        removeItem();
        break;
      } case '2':{
        replaceItem();
        break;
      } case '3':{
        deleteList();
        break;
      } case '4':{
        break;
      } default:{
        System.out.println("\nInvalid Response!");
        removeItemMenu();
      }
    }
  }

  private void removeItem(){
    System.out.print("\nRemove Item from List. ");
    System.out.print("Enter Item ID: ");
    final int taskIndex = setTaskID();
    if(chkID(taskIndex, this.checkList.size())){
      this.checkList.remove(taskIndex);
      this.statusList.remove(taskIndex);
      sortArrOut();
      dispList();
      System.out.print("\nRemove New Item from List (y/n): ");
      switch(setUsrOpt()){
        case 'y':
        case 'Y':{
          removeItem();
          break;
        }
        case 'n':
        case 'N':
          break;
        default:{
          System.out.print("\nInvalid Response!");
          removeItem();
        }
      }
    } else {
      removeItem();
    } 
  }

  private void deleteList(){
    this.alphaNum.deleteFile();
    loadFileMenu();
  }

  public void reorderItem(){
    System.out.print("\nRe-order Item in List. ");
    System.out.print("Enter First Item ID: ");
    final int taskIndex1 = setTaskID();
    System.out.print("Enter Second Item ID: ");
    final int taskIndex2 = setTaskID();
    if((chkID(taskIndex1, this.listSize)) && (chkID(taskIndex2, this.listSize))){
      // Make temp file to save one set of data
      final String tempTask = this.checkList.get(taskIndex1);
      final String tempStatus = this.statusList.get(taskIndex1);
      // Replaced saved set
      this.checkList.set(taskIndex1, this.checkList.get(taskIndex2));
      this.statusList.set(taskIndex1, this.statusList.get(taskIndex2));
      // Recover saved set
      this.checkList.set(taskIndex2, tempTask);
      this.statusList.set(taskIndex2, tempStatus);
      sortArrOut();
      dispList();
      System.out.print("\nRe-order New Item in List (y/n): ");
      switch(setUsrOpt()){
        case 'y':
        case 'Y':{
          reorderItem();
          break;
        }
        case 'n':
        case 'N':
          break;
        default:{
          System.out.print("\nInvalid Response!");
          reorderItem();
        }
      }
    } else {
      reorderItem();
    } 
  }

  public boolean removeUsr(){
    return this.alphaNum.deleteDir();
  }
}