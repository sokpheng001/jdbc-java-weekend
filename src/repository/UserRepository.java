package repository;

import model.CreateUserReq;
import model.UpdateUserReq;
import model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(int id);
    User insertUser(CreateUserReq createUserReq);
    User updateUserById(int id, UpdateUserReq updateUserReq);
    Integer deleteUserById(int id);
}
