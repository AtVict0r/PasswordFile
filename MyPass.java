import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner; // Import the Scanner class to read user input

class MyPass {
  private final String folderName = "app";// parent folder to store app data
  private final String fileName = "passwordfile"; // default file to save password and username
  private boolean allowUsr = false; 
  private String usrName = null;
  private String myPass = null; // retrieve password

  private ArrayList<String> inputData = null; // store data (password and username) from file

  private MyFile pass = null; // manipulate java file command
  private Scanner myInput = null; // collect user input

  public MyPass(){
    pass = new MyFile(this.folderName, this.fileName);
    inputData = pass.readFile();
  }

  public void usrLogin(){
    System.out.print("Enter User Name ");
    System.out.print("(Guest Users Enter 'G'/'g'):");
    this.usrName = setClassString().toUpperCase();
    if(this.usrName.contentEquals("G")){
      this.allowUsr = true;
      this.usrName = "GUEST";
    } else if (chkUsrName()){
      System.out.print("User Name Found. Enter password: ");
      entPass();
    } else {
      System.out.print("User Name Not Found!\t");
      usrMenu();
    }
  }

  private void entPass(){
    this.myPass = setClassString();
    if(!chkPass()){
      passMenu();
    }
  }

  private String setClassString(){
    myInput = new Scanner(System.in);
    return myInput.next();
  }

  private boolean chkUsrName(){
    return inputData.contains(this.usrName);
  }

  private boolean chkPass(){
    final String tempPass = inputData.get(inputData.indexOf(this.usrName) + 1); 
    if(tempPass.contentEquals(this.myPass)){
      System.out.println("Password Correct!");
      this.allowUsr = true;
      return true;
    }
    System.out.print("Wrong Password!\t");
    return false;
  }

  // Menu
  private void usrMenu(){
    System.out.println("User Menu: \n1) Re-Enter User Name \n2) Create New User \n3) Exit");
    System.out.print("Choose Option (1, 2, or 3): ");
    switch(myInput.next().charAt(0)){
      case '1':{
        usrLogin();
        break;
      } case '2':{
        //Mak dir
        MyFile usr = new MyFile(this.folderName + "/" + this.usrName);
        //arr = Usr
        inputData.add(this.usrName);
        //arr = Get Pass
        System.out.print("Enter New Password: ");
        inputData.add(myInput.next());
        //writ arr to txt
        pass.writeFile(inputData);
        //set auth
        this.allowUsr = true;
        break;
      } case '3':{
        endProg();
        break;
      } default:{
        System.out.println("Invalid Response!");
        usrMenu();
      }
    }
  }

  private void passMenu(){
    System.out.println("Password Menu: \n1) Re-Enter Password \n2) Create New Password \n3) Re-Enter User Name \n4) Exit");
    System.out.print("Choose Option (1, 2, 3 or 4): ");
    switch(myInput.next().charAt(0)){
      case '1':{
        System.out.print("Enter password: ");
        entPass();
        break;
      } case '2':{
        //arr = Get Pass
        System.out.print("Enter New Password: ");
        inputData.set((inputData.indexOf(this.usrName) + 1), myInput.next());
        //writ arr to txt
        pass.writeFile(inputData);
        //set auth
        this.allowUsr = true;
        break;
      } case '3':{
        usrLogin();
        break;
      } case '4':{
        endProg();
        break;
      } default:{
        System.out.println("Invalid Response!");
        passMenu();
      }
    }
  }

  private void endProg(){
    this.allowUsr = false;
    System.out.println("Ending Program");
  }

  public String getUsrNam(){
    return this.usrName;
  }

  public String getUsrDir(){
    return (this.folderName + "/" + this.usrName + "/");
  }

  public boolean getAuthAllow(){
    return this.allowUsr;
  }
}
