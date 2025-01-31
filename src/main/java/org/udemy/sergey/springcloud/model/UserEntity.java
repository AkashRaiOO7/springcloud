package org.udemy.sergey.springcloud.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Size(min=2, max = 15)
    @NotEmpty
    private String firstName;
    @Size(min=2, max = 15)
    @NotEmpty
    private String lastName;
    @Email
    private String email;
    private String userId;
}
