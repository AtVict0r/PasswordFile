import java.util.ArrayList; // import the ArrayList class
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

class Main { // extends DisplayDate {
  public static void main(String[] args) {
    testClass();
  }

  public static String displayDate() {
    final GregorianCalendar now = new GregorianCalendar(2020, 3, 5); 
        
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM dd, YYYY");
    System.out.println(dateFormatter.format(now.getTime()));

    final String fileNameDate = String.format("%1$tm%1$td%1$ty", now);

    return fileNameDate;
  }

  public static void testClass(){
    ArrayList<String> inputData = new ArrayList<String>();
    inputData.add("12345678");
    inputData.add("abcdefgh");
    inputData.add("ABCDEFGH");

    // Create password object
    MyPass code = new MyPass();
    code.usrLogin();
    if(code.getAuthAllow()){
      System.out.println("Welcome " + code.getUsrNam());
      // Create file object
      MyFile alphaNum = new MyFile(code.getUsrDir(), displayDate());
      alphaNum.writeFile(inputData);
      ArrayList<String> outputData = alphaNum.readFile(); // Create an ArrayList object
      System.out.println(outputData);
      alphaNum.fileInfo();
    }
  }
}
