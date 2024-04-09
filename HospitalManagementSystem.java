package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";

    private static final String username = "root";

    private static final String password = "Sarang@5900";

    public static void main(String [] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);

            while (true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1 :
                        //add patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2 :
                        //view patient
                        patient.viewPatient();
                        System.out.println();
                        break;
                    case 3 :
                        // view doctor
                        doctor.viewDoctor();
                        System.out.println();
                        break;
                    case 4 :
                         // book appointment
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5 :
                        // exit
                        System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM");
                        return;
                    default :
                        System.out.println("Enter Valid Choice!!");
                        break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.println("Enter patient Id: ");
        int patient_id = scanner.nextInt();

        System.out.println("Enter Doctor id: ");
        int doctor_id = scanner.nextInt();

        System.out.println("Enter appointment Date (YYYY-MM-DD) : ");
        String appointmentDate = scanner.next();

        if(patient.getPatientById(patient_id) && doctor.getDoctorById(doctor_id)){
            if(checkDoctorAvailability(doctor_id, appointmentDate, connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);

                    preparedStatement.setInt(1, patient_id);
                    preparedStatement.setInt(2, doctor_id);
                    preparedStatement.setString(3, appointmentDate);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected>0){
                        System.out.println("Appointment Booked!!");
                    } else {
                        System.out.println("Failed to Book Appointment!!");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor is not available on this date!!");
            }
        } else {
            System.out.println("Either Doctor or Patient Doesn't exist!!!");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int count = resultSet.getInt(1);
                if(count == 0){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
