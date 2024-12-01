package model;

public record UserResponseDto(
        String userName,
        String email,
        Boolean isDeleted
) {
}
