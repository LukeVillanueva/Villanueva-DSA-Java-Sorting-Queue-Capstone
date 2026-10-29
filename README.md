# Villanueva-DSA-Java-Sorting-Queue-Capstone

**Hospital Queuing System**

**Project Description**

The Hospital Queuing System is a console-based Java application that manages patient registration and waiting queues. It organizes patients according to three priority levels: Emergency, Urgent, and Normal.

The system assigns each registered patient a unique patient number and records their name, priority, and status. It allows users to view waiting patients by patient number or priority, serve the next patient, view the next patient, and search for patients by number.

Features

Register Patient – Records the patient's name and selected priority.

Automatic Patient Numbering – Assigns unique patient numbers in the format P-001, P-002, and so on.

View Waiting Queue – Displays waiting patients sorted by patient number in ascending order.

Sort Patients by Priority – Displays patients in Emergency, Urgent, and Normal groups.

Serve Next Patient – Removes the next patient from the appropriate queue according to priority.

View Next Patient – Displays the next patient to be served without removing them from the queue.

Search Patient by Number – Finds a registered patient and displays their information and status.

Input Validation – Checks for invalid menu choices, invalid priority selections, and empty patient names.

Exit Program – Allows users to safely exit the application.

**Priority Levels**

Priority

Description

Emergency

Highest priority; served first.

Urgent

Served after Emergency patients.

Normal

Served when no Emergency or Urgent patients are waiting.

Patients within the same priority level are served in FIFO (First In, First Out) order.

Technologies Used

Programming Language: Java

Interface: Console / Terminal

Data Structures: ArrayList, Queue, and LinkedList

Input Handling: Scanner

Programming Approach: Procedural programming using main() and static methods

How the System Works

The user registers a patient and selects a priority level.

The system assigns a unique patient number and stores the patient's information.

The patient is added to the queue corresponding to their priority.

The user can view waiting patients by patient number or sort the displayed queue by priority.

When serving a patient, the system checks Emergency first, followed by Urgent and Normal.

The patient's status changes to Being Served, and the patient is removed from the waiting queue.

The user can search for a patient by number to view their current recorded status.

How to Run the Program

Requirements

Java Development Kit (JDK) installed

A Java-compatible terminal or IDE

Steps

Save the source code as HospitalQueue.java.

Open a terminal in the folder containing the Java file.

Compile the program:

javac HospitalQueue.java

Run the program:

java HospitalQueue

Select an option from the displayed menu to use the system.

Data Storage

The application stores patient information in parallel ArrayList collections. Patient numbers, names, priorities, and statuses use matching indexes to represent each patient's record.

Three separate Queue<Integer> collections manage Emergency, Urgent, and Normal patients. These queues store patient numbers and maintain FIFO order within each priority level.

Note: Patient records are stored in memory and are not saved to a file or database. The records are lost when the program terminates.

Limitations

The application runs only in the console.

Patient records are not permanently stored.

Patients are searched by their numeric patient number.

The system records a patient's status as Being Served but does not provide a separate option to mark a consultation as completed.

Project Structure

HospitalQueuingSystem/
└── HospitalQueue.java

**Conclusion**

The Hospital Queuing System demonstrates the use of Java collections, queues, input validation, searching, and procedural programming. It provides a simple way to organize patient records and manage waiting lines according to medical priority while maintaining FIFO order within each priority group.
