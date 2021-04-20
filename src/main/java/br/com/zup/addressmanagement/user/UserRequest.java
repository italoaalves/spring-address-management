package br.com.zup.addressmanagement.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dob;

    @Deprecated
    public UserRequest() {
    }

    public UserRequest(String name, String email, String cpf, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
    }

    public User toUser() {
        return new User(this.name, this.email, this.cpf, this.dob);
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public LocalDate getDob() {
        return this.dob;
    }
}
