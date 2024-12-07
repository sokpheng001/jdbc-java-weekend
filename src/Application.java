import repository.UserRepositoryImpl;

public class Application {
    public static void main(String[] args) {
        new UserRepositoryImpl()
                .getAllUsers()
                .forEach(System.out::println);
    }
}