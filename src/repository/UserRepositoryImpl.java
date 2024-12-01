package repository;

import model.CreateUserReq;
import model.UpdateUserReq;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserRepositoryImpl implements UserRepository{
    private final String url = "jdbc:postgresql://localhost:5432/media_db";
    private final String userName = "postgres";
    private final String password = "123";
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try{
            Connection connection =
                    DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("""
SELECT * FROM users WHERE is_deleted = false""");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDeleted(resultSet.getBoolean("is_deleted"));
                userList.add(user);
            }
            connection.close();
            statement.close();
            return userList;
        }catch (Exception exception){
            System.out.println("Error during get all users: " + exception.getMessage());
        }
        return List.of();
    }

    @Override
    public User getUserById(int id) {
        try{
            Connection connection = DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement("""
SELECT * FROM users where id = ?""");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDeleted(resultSet.getBoolean("is_deleted"));
            }
            return user;
        }catch (Exception exception){
            System.out.println("Error during get user by ID: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public User insertUser(CreateUserReq createUserReq) {
        try{
            Connection connection = DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
INSERT INTO users(id, user_name, email, password, is_deleted)
VALUES (?,?,?,?,?)"""
            );
            int userId = new Random().nextInt(9999999);
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,createUserReq.userName());
            preparedStatement.setString(3,createUserReq.email());
            preparedStatement.setString(4,createUserReq.password());
            preparedStatement.setBoolean(5,false);
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected>0 ? "User has been created":"Cannot create user";
            System.out.println(message);
            if(rowAffected>0){
                return new User(userId, createUserReq.userName(), createUserReq.email(), createUserReq.password(), false);
            }
            return null;
        }catch (Exception exception){
            System.out.println("Error during insert user data: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public User updateUserById(int id, UpdateUserReq updateUserReq) {
        try{
            Connection connection = DriverManager.getConnection(url,userName,password);
            PreparedStatement pre = connection.prepareStatement("""
UPDATE users
SET user_name = ?,
    email = ?
WHERE id = ?""");
            if(updateUserReq.userName()!=""){
                pre.setString(1,updateUserReq.userName());
            }
            if(updateUserReq.email()!=""){
                pre.setString(2,updateUserReq.email());
            }
            pre.setInt(3,id);
            int rowAffected = pre.executeUpdate();
            if(rowAffected>0){
                System.out.println("Updated user");
                return getUserById(id);
            }else {
                System.out.println("Updated user failed");
            }
        }catch (Exception exception){
            System.out.println("Error during update user by ID: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public Integer deleteUserById(int id) {
        try{
            Connection connection = DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement("""
DELETE FROM users WHERE id = ?""");
            preparedStatement.setInt(1,id);
            int rowDeleted = preparedStatement.executeUpdate();
            String message = rowDeleted>0 ? "User with ID: " + id + " has been deleted":"User not Found";
            System.out.println(message);
            return rowDeleted;
        }catch (Exception exception){
            System.out.println("Error during delete user by id: " + exception.getMessage()) ;
        }
        return 0;
    }
}
