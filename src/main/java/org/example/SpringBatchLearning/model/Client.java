package org.example.SpringBatchLearning.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class Client {

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must be alphabetic")
    protected String name;
    // @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Nickname must be alphabetic")
    protected String nickname;
    @NotNull
    @Range(min = 18, max = 200)
    protected Integer age;
    @NotNull
    @Size(min = 1, max = 50)
    @Email
    protected String email;
    // @NotNull
    protected Double salaryRange;

    @Transient
    protected List<Transaction> transactionList = new ArrayList<>();

}
