import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String END_OF_DAY_HOUR = "19 00";

    public static void main(String[] args) {
        // Task 1:
        runApp();
    }

    public static void runApp() {
        String time = getStartTime();
        int timePerPatient = getTimePerPatient();
        int patientNumber = getPatientNumberToCheck();

        int startTimeInMinutes = convertTimeToMinutes(time);
        int minutesToAppointment = getMinutesToAppointment(patientNumber, timePerPatient);

        int timeForAppointmentInMinutes = startTimeInMinutes + minutesToAppointment;

        // Print result
        int timeEndOfAppointmentInMinutes = timeForAppointmentInMinutes + timePerPatient;
        if (isTimeBeforeEndOfDay(timeEndOfAppointmentInMinutes)) {
            System.out.println(convertMinutesToHours(timeForAppointmentInMinutes));
        } else {
            System.out.println("Another day");
        }
    }

    public static boolean isTimeBeforeEndOfDay(int timeInMinutesToCheckPlusTimeForAppointment) {
        // Check if the doctor will have enough time to do the appointment, not only to start it.
        int endOfDayInMinutes = convertTimeToMinutes(END_OF_DAY_HOUR);
        return endOfDayInMinutes >= timeInMinutesToCheckPlusTimeForAppointment;
    }

    public static String convertMinutesToHours(int minutes) {
        int hours = minutes / 60;
        String remainingMinutes = String.valueOf(minutes % 60);
        // add trailing zero for single digit minutes
        if (Integer.parseInt(remainingMinutes) < 10) {
            remainingMinutes = "0" + remainingMinutes;
        }
        return hours + ":" + remainingMinutes;
    }

    public static int getMinutesToAppointment(int numberOfPatients, int timePerPatient) {
        return (numberOfPatients - 1) * timePerPatient;
    }

    public static String getStartTime() {
        System.out.print("Въведете час за започване (формат: час минута): ");
        return scanner.nextLine();
    }

    public static int getTimePerPatient() {
        System.out.print("Въведете времето за което се преглежда един пациент в минути: ");
        return scanner.nextInt();
    }

    public static int getPatientNumberToCheck() {
        System.out.print("Въведете номер на пациента: ");
        return scanner.nextInt();
    }

    public static int convertTimeToMinutes(String time) {
        int hours = Integer.parseInt(time.split(" ")[0]);
        int minutes = Integer.parseInt(time.split(" ")[1]);
        return hours * 60 + minutes;
    }
}

