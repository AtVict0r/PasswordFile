/**
import java.text.SimpleDateFormat; // Import the SimpleDateFormat class to change the format of date
import java.util.GregorianCalendar; // Import the GregorianCalendar class to get system time

class DisplayDate {
  public static String displayDate() {
    final GregorianCalendar now = new GregorianCalendar(2020, 3, 5); 
        
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM dd, YYYY");
    System.out.println(dateFormatter.format(now.getTime()));

    final String fileNameDate = String.format("%d_%d_%d", (now.get(GregorianCalendar.MONTH ) + 1), now.get(GregorianCalendar.DATE), now.get(GregorianCalendar.YEAR));

    return fileNameDate;
  }

  /**
  public DisplayDate(int Day) {
    GregorianCalendar now = new GregorianCalendar(); 
        
    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM d, yyyy");
    System.out.println(dateFormatter.format(now.getTime()));
        
  }

  public DisplayDate() {
    GregorianCalendar now = new GregorianCalendar(); 
        
    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM d, yyyy");
    System.out.println(dateFormatter.format(now.getTime()));
        
  }

  public DisplayDate() {
    GregorianCalendar now = new GregorianCalendar(); 
        
    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMMM d, yyyy");
    System.out.println(dateFormatter.format(now.getTime()));
        
  }
}
**/