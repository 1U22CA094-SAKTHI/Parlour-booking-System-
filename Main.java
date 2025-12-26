import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Parlour Booking System");
        System.out.println("1. View Services");
        System.out.println("2. Book Appointment");

        int choice = sc.nextInt();

        if (choice == 1) {
            viewServices();
        } else if (choice == 2) {
            bookAppointment(sc);
        }
    }

    static void viewServices() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM services");

            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + " " +
                    rs.getString(2) + " Rs." +
                    rs.getInt(3)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void bookAppointment(Scanner sc) {
        try {
            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Service ID: ");
            int sid = sc.nextInt();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO bookings(name, service_id) VALUES (?,?)"
            );
            ps.setString(1, name);
            ps.setInt(2, sid);

            ps.executeUpdate();
            System.out.println("Appointment Booked Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
