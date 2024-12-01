package model;

public record CreateUserReq(
        String userName,
        String email,
        String password
) { }
