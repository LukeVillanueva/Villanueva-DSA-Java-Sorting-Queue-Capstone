
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HospitalQueue {

    static Scanner Input = new Scanner(System.in);

    static ArrayList<Integer> PatientNumbers = new ArrayList<>();
    static ArrayList<String> PatientNames = new ArrayList<>();
    static ArrayList<String> PatientPriorities = new ArrayList<>();
    static ArrayList<String> PatientStatuses = new ArrayList<>();

    static Queue<Integer> EmergencyQueue = new LinkedList<>();
    static Queue<Integer> UrgentQueue = new LinkedList<>();
    static Queue<Integer> NormalQueue = new LinkedList<>();

    static int NextPatientNumber = 1;
    static int Choice = 0;
    static boolean IsRunning = true;

    public static void main(String[] args) {

        while (IsRunning) {
            DisplayMenu();
            Choice = GetMenuChoice();

            switch (Choice) {
                case 1:
                    RegisterPatient();
                    break;
                case 2:
                    ViewWaitingQueue();
                    break;
                case 3:
                    ServeNextPatient();
                    break;
                case 4:
                    ViewNextPatient();
                    break;
                case 5:
                    SearchPatient();
                    break;
                case 6:
                    SortPatientsByPriority();
                    break;
                case 7:
                    IsRunning = false;
                    System.out.println("\nExiting Hospital Queuing System...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please choose 1-7.");
            }
        }

        Input.close();
    }

    public static void DisplayMenu() {
        System.out.println("\n==================================");
        System.out.println("     HOSPITAL QUEUING SYSTEM");
        System.out.println("==================================");
        System.out.println("1. Register Patient");
        System.out.println("2. View Waiting Queue ");
        System.out.println("3. Serve Next Patient");
        System.out.println("4. View Next Patient");
        System.out.println("5. Search Patient by Number");
        System.out.println("6. Sort Patients by Priority");
        System.out.println("7. Exit");
        System.out.println("==================================");
        System.out.print("Enter your choice: ");
    }

    public static int GetMenuChoice() {
        while (!Input.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            Input.next();
        }

        int MenuChoice = Input.nextInt();
        Input.nextLine();

        return MenuChoice;
    }

    public static void RegisterPatient() {
        System.out.println("\n===== REGISTER PATIENT =====");

        System.out.print("Enter patient name: ");
        String PatientName = Input.nextLine().trim();

        if (PatientName.isEmpty()) {
            System.out.println("Patient name cannot be empty.");
            return;
        }

        System.out.println("\nSelect patient priority:");
        System.out.println("1. Emergency");
        System.out.println("2. Urgent");
        System.out.println("3. Normal");
        System.out.print("Enter priority: ");

        int PriorityChoice;

        while (!Input.hasNextInt()) {
            System.out.print("Invalid input. Enter 1, 2, or 3: ");
            Input.next();
        }

        PriorityChoice = Input.nextInt();
        Input.nextLine();

        String PatientPriority;

        switch (PriorityChoice) {
            case 1:
                PatientPriority = "Emergency";
                break;
            case 2:
                PatientPriority = "Urgent";
                break;
            case 3:
                PatientPriority = "Normal";
                break;
            default:
                System.out.println("Invalid priority. Registration cancelled.");
                return;
        }

        int NewPatientNumber = NextPatientNumber++;

        PatientNumbers.add(NewPatientNumber);
        PatientNames.add(PatientName);
        PatientPriorities.add(PatientPriority);
        PatientStatuses.add("Waiting");

        if (PatientPriority.equals("Emergency")) {
            EmergencyQueue.add(NewPatientNumber);
        } else if (PatientPriority.equals("Urgent")) {
            UrgentQueue.add(NewPatientNumber);
        } else {
            NormalQueue.add(NewPatientNumber);
        }

        System.out.println("\nPatient registered successfully!");
        System.out.println("Patient Number: P-"
                + String.format("%03d", NewPatientNumber));
        System.out.println("Patient Name: " + PatientName);
        System.out.println("Priority: " + PatientPriority);
        System.out.println("Status: Waiting");
    }

    public static void ViewWaitingQueue() {
        ArrayList<Integer> SortedPatientNumbers = new ArrayList<>();

        SortedPatientNumbers.addAll(EmergencyQueue);
        SortedPatientNumbers.addAll(UrgentQueue);
        SortedPatientNumbers.addAll(NormalQueue);

        if (SortedPatientNumbers.isEmpty()) {
            System.out.println("\nNo patients are waiting.");
            return;
        }

        // Sort patient numbers from lowest to highest
        for (int I = 0; I < SortedPatientNumbers.size() - 1; I++) {
            for (int J = 0;
                    J < SortedPatientNumbers.size() - 1 - I; J++) {

                if (SortedPatientNumbers.get(J)
                        > SortedPatientNumbers.get(J + 1)) {

                    int Temp = SortedPatientNumbers.get(J);

                    SortedPatientNumbers.set(
                            J, SortedPatientNumbers.get(J + 1));

                    SortedPatientNumbers.set(J + 1, Temp);
                }
            }
        }

        System.out.println("\n===== WAITING QUEUE (BY NUMBER) =====");

        for (int PatientNumber : SortedPatientNumbers) {
            int Index = PatientNumbers.indexOf(PatientNumber);

            System.out.println(
                    "P-" + String.format("%03d", PatientNumber)
                    + " | Name: " + PatientNames.get(Index)
                    + " | Priority: " + PatientPriorities.get(Index)
                    + " | Status: " + PatientStatuses.get(Index));
        }
    }

    public static void SortPatientsByPriority() {
        if (EmergencyQueue.isEmpty()
                && UrgentQueue.isEmpty()
                && NormalQueue.isEmpty()) {

            System.out.println("\nNo patients are waiting.");
            return;
        }

        System.out.println("\n===== WAITING QUEUE (BY PRIORITY) =====");

        System.out.println("\n--- EMERGENCY ---");
        DisplayPatientQueue(EmergencyQueue);

        System.out.println("\n--- URGENT ---");
        DisplayPatientQueue(UrgentQueue);

        System.out.println("\n--- NORMAL ---");
        DisplayPatientQueue(NormalQueue);
    }

    public static void DisplayPatientQueue(Queue<Integer> PatientQueue) {
        if (PatientQueue.isEmpty()) {
            System.out.println("No patients.");
            return;
        }

        int Position = 1;

        for (int PatientNumber : PatientQueue) {
            int Index = PatientNumbers.indexOf(PatientNumber);

            System.out.println(
                    Position + ". P-"
                    + String.format("%03d", PatientNumber)
                    + " | Name: " + PatientNames.get(Index)
                    + " | Priority: " + PatientPriorities.get(Index)
                    + " | Status: " + PatientStatuses.get(Index));

            Position++;
        }
    }

    public static void ServeNextPatient() {
        Integer PatientNumber = null;

        if (!EmergencyQueue.isEmpty()) {
            PatientNumber = EmergencyQueue.poll();
        } else if (!UrgentQueue.isEmpty()) {
            PatientNumber = UrgentQueue.poll();
        } else if (!NormalQueue.isEmpty()) {
            PatientNumber = NormalQueue.poll();
        }

        if (PatientNumber == null) {
            System.out.println("\nNo patients are waiting to be served.");
            return;
        }

        int Index = PatientNumbers.indexOf(PatientNumber);
        PatientStatuses.set(Index, "Being Served");

        System.out.println("\n===== SERVING NEXT PATIENT =====");
        System.out.println("Patient Number: P-"
                + String.format("%03d", PatientNumber));
        System.out.println("Patient Name: " + PatientNames.get(Index));
        System.out.println("Priority: " + PatientPriorities.get(Index));
        System.out.println("Status: Being Served");
    }

    public static void ViewNextPatient() {
        Integer PatientNumber = null;

        if (!EmergencyQueue.isEmpty()) {
            PatientNumber = EmergencyQueue.peek();
        } else if (!UrgentQueue.isEmpty()) {
            PatientNumber = UrgentQueue.peek();
        } else if (!NormalQueue.isEmpty()) {
            PatientNumber = NormalQueue.peek();
        }

        if (PatientNumber == null) {
            System.out.println("\nNo patients are waiting.");
            return;
        }

        int Index = PatientNumbers.indexOf(PatientNumber);

        System.out.println("\n===== NEXT PATIENT =====");
        System.out.println("Patient Number: P-"
                + String.format("%03d", PatientNumber));
        System.out.println("Patient Name: " + PatientNames.get(Index));
        System.out.println("Priority: " + PatientPriorities.get(Index));
        System.out.println("Status: " + PatientStatuses.get(Index));
    }

    public static void SearchPatient() {
        System.out.println("\n===== SEARCH PATIENT =====");
        System.out.print("Enter patient number (e.g., 1 for P-001): ");

        while (!Input.hasNextInt()) {
            System.out.print("Invalid input. Enter the numeric patient number: ");
            Input.next();
        }

        int SearchNumber = Input.nextInt();
        Input.nextLine();

        int Index = PatientNumbers.indexOf(SearchNumber);

        if (Index == -1) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("\nPatient found!");
        System.out.println("Patient Number: P-"
                + String.format("%03d", PatientNumbers.get(Index)));
        System.out.println("Patient Name: " + PatientNames.get(Index));
        System.out.println("Priority: " + PatientPriorities.get(Index));
        System.out.println("Status: " + PatientStatuses.get(Index));
    }
}
