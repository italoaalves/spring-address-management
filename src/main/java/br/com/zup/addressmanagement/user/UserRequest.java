package br.com.zup.addressmanagement.user;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserRequest {
    @NotBlank
    private final String name;

    @NotBlank
    private final String email;

    @NotBlank
    private final String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dob;

    public UserRequest(@NotBlank String name, @NotBlank String email, @NotBlank String cpf, @NotBlank LocalDate dob) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
    }

    public User toUser() {
        return new User(this.name, this.cpf, this.email, this.dob);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() { return cpf; }

    public LocalDate getDob() {
        return dob;
    }
}
