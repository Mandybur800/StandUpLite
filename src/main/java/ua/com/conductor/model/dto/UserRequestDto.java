package ua.com.conductor.model.dto;

import ua.com.conductor.anotations.EmailValidation;
import ua.com.conductor.anotations.ValidPassword;

@ValidPassword.List({
        @ValidPassword(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords do not match!"
        )
})
public class UserRequestDto {
    @EmailValidation
    private String email;
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
