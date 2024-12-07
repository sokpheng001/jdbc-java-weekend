package view;

import model.CreateUserReq;
import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import utils.TableUIConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private static final UserRepository userRepo =
            new UserRepositoryImpl();
    private static int insertOption(){
        System.out.print("[+] Insert option: ");
        return new Scanner(System.in).nextInt();
    }
    private static void option(int opt){
        switch (opt){
            case 1->{
                System.out.println("[+] Get all Users");
                System.out.println(TableUIConfig.getTable(userRepo.getAllUsers()).render());
            }
            case 2->{
                System.out.println("[+] Create new User");
                System.out.println("-".repeat(10));
                System.out.print("[+] Insert username: ");
                String userName = new Scanner(System.in).nextLine();
                System.out.print("[+] Insert Email: ");
                String email = new Scanner(System.in).nextLine();
                System.out.print("[+] Insert Password: ");
                String password  =new Scanner(System.in).nextLine();
                User user = userRepo.insertUser(new CreateUserReq(userName,email,password));
                System.out.println("User has been created as below");
                System.out.println(TableUIConfig.getTable(new ArrayList<>(List.of(user))).render());;
            }
            case 3->{}
            case 4->{
                System.out.print("[+] Insert user ID to delete: ");
                Integer id = new Scanner(System.in).nextInt();
                int id_ = userRepo.deleteUserById(id);
                if(id_==1){
                    System.out.println("[+] User with id: " + id + " has been deleted successfully");
                }
                }
            case 5->{
                System.out.println("[!] Application Closed");
                System.exit(0);
            }
            default -> System.out.println("[!] Invalid option.");
        }
    }
    public static void home(){
        while (true){
            System.out.println("=".repeat(40));
            System.out.println("""
                1. List all Users
                2. Create new User
                3. Update User by ID
                4. Delete User by ID
                5. Exit""");
            System.out.println("=".repeat(40));
            option(insertOption());
        }
    }
}
