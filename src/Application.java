import model.CreateUserReq;
import model.UpdateUserReq;
import model.User;
import model.UserResponseDto;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class Application {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = userRepository.updateUserById(1,
                new UpdateUserReq("Kim Chansokpheng","kimchansokpheng123@gmail.com"));
        UserResponseDto responseDto = new UserResponseDto(
                user.getUserName(),
                user.getEmail(),
                user.getDeleted()
        );
        System.out.println(responseDto);
    }
}
