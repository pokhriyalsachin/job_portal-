package com.jobPortal.Job.Portal.dto;


import com.jobPortal.Job.Portal.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "{user.name.absent}")
    private String name;

    @Email(message = "{user.email.invalid}")
    @NotBlank(message ="{user.email.absent}")
    private String email;


    @NotBlank(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "{user.password.invalid}")
    private String password;

    private AccountType accountType;
    private Long profileId;

    public Long getId() {
        return id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }





    public UserDTO(Long id, String name, String email, String password, AccountType accountType,Long profileId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.profileId= profileId;
    }

    public User toEntity(){
        return new User(this.id,this.name,this.email,this.password,this.accountType,this.profileId);
    }
}
