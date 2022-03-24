package application;

import entities.Register;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to CRUD!");

        System.out.print("Type your option: (C/R/U/D) ");
        char option = sc.nextLine().charAt(0);

        if(option == 'C') {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.println("Email: ");
            String email = sc.nextLine();

            Register.createData(name, email);
        }
        else if(option == 'R') {
            System.out.println("Which ID: ");
            int id = sc.nextInt();

            Register.readData(id);
        }
        else if(option == 'U') {
            System.out.println("Which ID to update data? ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.println("Name: ");
            String name = sc.nextLine();

            System.out.println("Email: ");
            String email = sc.nextLine();

            Register.updateData(id, name, email);
        }
        else if(option == 'D') {
            System.out.println("Which ID to delete data? ");
            int id = sc.nextInt();

            Register.deleteData(id);
        }
    }
}
