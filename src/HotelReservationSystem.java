//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class HotelReservation {
    static final String url = "jdbc:mysql://localhost:3306/hotelreservation";
    static final String username = "root";
    static final String password = "Login@12345";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loader SUccesfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation", "root", "Login@12345");
            System.out.println("Connection Succesfull");

            while(true) {
                System.out.println("                                         ");
                System.out.println("                                         Welcome to Hotel Reservation System                                         ");
                Scanner sc = new Scanner(System.in);
                System.out.println("                                         Type 1 for New Reservation                                         ");
                System.out.println("                                         Type 2 to View Reservation                                         ");
                System.out.println("                                         Type 3 to Get Room Number                                         ");
                System.out.println("                                         Type 4 to Update Reservation                                         ");
                System.out.println("                                         Type 5 to Delete Reservation                                         ");
                System.out.println("                                         Type 0 to Exit the Setup                                         ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> reserveRoom(con, sc);
                    case 2 -> viewReservation(con);
                    case 3 -> getRoomNumber(con, sc);
                    case 4->  updateReservation(con, sc);
                    case 5 -> deleteReservation(con,sc);
                    default -> { System.out.println("Invalid Choice. Try Again");           return;}
                    case 0 -> {
                        System.out.println("Exiting the Setup");
                        con.close();
                        sc.close();
                        return;
                    }
            } 
        }} catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void reserveRoom(Connection con, Scanner sc) {
        System.out.println("Please Enter Your Name : ");
        String guestName = sc.nextLine();
        System.out.println("Enter Room Number : ");
        int roomNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Your Contact Number : ");
        String contactNumber = sc.nextLine();
        String sql = "INSERT INTO reservations(guest_name ,room_number,conatct_number) values('" + guestName + "', " + roomNumber + " , '" + contactNumber + "' );";

        try {
            Statement stmt = con.createStatement();
            int rowsaffected = stmt.executeUpdate(sql);
            if (rowsaffected > 0) {
                System.out.println("Rows Affected " + rowsaffected);
                System.out.println("Reservation Successfull");
            } else {
                System.out.println("Insertion Failed !!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void viewReservation(Connection con) throws SQLException {
        String sql = "SELECT reservation_id , guest_name, room_number , conatct_number, reservation_date from reservations";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Current Reservations");
            System.out.println("+---------------+--------------+----------------+-----------------+------------------------+");
            System.out.println("| Reservation ID| Name         | Room Number    | Contact Number  | Date                   |");
            System.out.println("+---------------+--------------+----------------+-----------------+------------------------+");

            while(rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                String guestname = rs.getString("guest_name");
                int roomnumber = rs.getInt("room_number");
                String contactnumber = rs.getString("conatct_number");
                String reservationDate = rs.getTimestamp("reservation_date").toString();
                System.out.printf("|%-14d | %-12s | %-14d | %-15s | %-15s  | \n", reservationId, guestname, roomnumber, contactnumber, reservationDate);
            }

            System.out.println("+---------------+--------------+----------------+-----------------+------------------------+");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void getRoomNumber(Connection con, Scanner sc) {
        try {
            System.out.println("Enter Reservtion ID :");
            int reservationID = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Guest Name :");
            String guestName = sc.nextLine();
            String sql = "SELECT * FROM reservations WHERE reservation_id = " + reservationID + " AND guest_name = '" + guestName + "'";

            try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ) {
                if (rs.next()) {
                    int roomNumber = rs.getInt("room_number");
                    System.out.println("The Room Number For the Reservation ID  " +  reservationID  + " and Guest Name " +  guestName + " is " + roomNumber);
                } else {
                    System.out.println("Reservation Not Found for the giver Reservation ID and Guest Name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateReservation (Connection con , Scanner sc)
    {
        System.out.println("Please Enter the Reservation ID to Update : ");
        int reservationID = sc.nextInt();
        sc.nextLine();

     if (!reservationExist(con , reservationID))
     {
        System.out.println("Reservation does not exist with the given ID ");
     }
     else {
        System.out.println("Enter Guest Name to Update : ");
        String newguestName = sc.nextLine();
        System.out.println("Enter Room Number to Update :");
        int newroomNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Contact Number to Update :");
        String newcontactNumber = sc.nextLine();
        String sql = "UPDATE reservations SET guest_name = '" + newguestName +"' , room_number = " + newroomNumber + " , conatct_number = '" + newcontactNumber + "';";
           
            try{
                Statement stmt = con.createStatement();
              int rowsaffected = stmt.executeUpdate(sql);
            if(rowsaffected>0)
            {
                System.out.println("Reservation Updated Successfully");
            }
            else {
                System.out.println("Updation Failed !!");
            }
        }catch(SQLException e){
            e.printStackTrace();}
    }
     }
     private static void deleteReservation(Connection con , Scanner sc)
     { System.out.println("Please Enter the Reservation ID you want to Delete :");
        int reservationID = sc.nextInt();
        sc.nextLine();
        if (!reservationExist(con , reservationID))
        {
           System.out.println("Reservation does not exist with the given ID ");
        }
        else {
           String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationID + ";";
           try {
              Statement stmt = con.createStatement();
              int rowsaffected = stmt.executeUpdate(sql);
              if (rowsaffected > 0) {
                 System.out.println("Reservation Deleted Successfully");
              } else {
                 System.out.println("Deletion Failed !!");
              }
           } catch (SQLException e) {
              e.printStackTrace();
           }
        }
     }

     private static boolean reservationExist(Connection con , int reservationID)
     {
        String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationID + ";";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
             return rs.next(); //if result exist it will return true
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false ;
        }
     }
     public static void exitSetup(Connection con , Scanner sc){
        System.out.println("Thank You For Using Hotel Reservation System");
        try {
            con.close();
            sc.close();
        }catch(SQLException e){
            e.printStackTrace();
     }
}
}
