import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner; // Import the Scanner class to read user input

class Main {
  private static final GregorianCalendar now = new GregorianCalendar();

  public static void main(String[] args) {
    testClass();
  }

  // Main menu
  private static void mainMenu(TaskList list){
    System.out.println("\nCheck List Menu: \n1) Load file(s) \n2) Mark Item(s) as Complete \n3) Add Item(s) to List \n4) Remove Item(s) from List \n5) Reorder Items in List  \n6) Change User \n7) Exit Program ");
    System.out.print("Choose Option (1, 2, 3, 4, 5, 6 or 7): ");
    Scanner input = new Scanner(System.in);
    switch(input.next().charAt(0)){
      case '1':{
        list.dispFileList();
        list.dispList();
        chooseMenu(list);
        // List file with size
        //Select file
        //Copy list
        //Display list
        //Empty list
        //Delete list
        break;
      } case '2':{
        list.setStatus();
        list.dispList();
        chooseMenu(list);
        // Mark as complete
        // In progress
        break;
      } case '3':{
        list.addItemMenu();
        list.dispList();
        chooseMenu(list);
        // Add Task
        // Insert Item
        // Replace Item
        break;
      } case '4':{
        list.removeItemMenu();
        list.dispList();
        chooseMenu(list);
        // Remove Item
        // Replace Item
        //Delete list
        break;
      } case '5':{
        list.reorderItem();
        list.dispList();
        chooseMenu(list);
        // Reorder Item
        break;
      } case '6':{
        chngUsr(list);
        chooseMenu(list);
        // New user
        //Delete user
        break;
      } case '7':{
        // Exit
        System.out.println("\nEnding Program...");
        System.out.println("Goodbye!");
        input.close();
        break;
      } default:{
        System.out.println("Invalid Response!");
        mainMenu(list);
      }
    }
  }

  private static void mainMenuShort(TaskList list){
    System.out.println("\nCheck List Menu: \n1) Load file(s) \n2) Add Item \n3) Change User \n4) Exit");
    System.out.print("Choose Option (1, 2, 3 or 4): ");
    Scanner input = new Scanner(System.in);
    switch(input.next().charAt(0)){
      case '1':{
        list.dispFileList();
        chooseMenu(list);
        // for check list size
        // List file with size
        //Select file
        //Copy list
        //Display list
        //Empty list
        //Delete list
        break;
      } case '2':{
        list.addItemMenu();
        chooseMenu(list);
        // for check list size
        // Add Task
        // Insert Item
        // Replace Item
        break;
      } case '3':{
        chngUsr(list);
        chooseMenu(list);
        // for check list size
        // New user
        //Delete user
        break;
      } case '4':{
        // Exit
        System.out.println("\nEnding Program...");
        System.out.println("Goodbye!");
        input.close();
        break;
      } default:{
        System.out.println("Invalid Response!");
        mainMenuShort(list);
      }
    }
  }

  private static String makNam(){
    final String fileNameDate = String.format("%1$tm%1$td%1$ty", now);

    return fileNameDate;
  }

  private static void displayDate() {    
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM dd, YYYY");
    System.out.println(dateFormatter.format(now.getTime()));
  }

  private static void chooseMenu(TaskList list){
    if(list.getListSize() == 0){
      mainMenuShort(list);
    } else {
      mainMenu(list);
    }
  }

  private static void testClass(){
    // Create password object
    MyPass code = new MyPass();
    code.usrLogin();
    if(code.getAuthAllow()){
      System.out.println("Welcome " + code.getUsrNam());
      displayDate();
      TaskList checkList = new TaskList(code, makNam());
      checkList.dispList();
      chooseMenu(checkList);
    } else {
      System.out.println("Ending Program...");
      System.out.println("Goodbye!");
    }
  } 

  private static void chngUsr(TaskList list){
    System.out.println("\nUser Menu: \n1) Change User \n2) Delete User \n3) Back");
    System.out.print("Choose Option (1, 2 or 3): ");
    Scanner input = new Scanner(System.in);
    switch(input.next().charAt(0)){
      case '1':{
        System.out.println("\nExiting Account...");
        testClass();
        input.close();
        break;
      } case '2':{
        System.out.println("\nDeleting Account...");
        if(list.removeUsr()){
          testClass();
          input.close();
        } else {
          chngUsr(list);
        }
        break;
      } case '3':{
        break;
      } default:{
        System.out.println("\nInvalid Response!");
        chngUsr(list);
      }
    }
  }

}
