package ch.kra.gradesubmission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
@RequiredArgsConstructor //Create a constructor with only the required fields (the ones with @Nonnull)
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NonNull
    @Past(message = "The birth date must be in the past")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @JsonIgnore //To avoid that this field is serialized as Json otherwise it will loop
    @OneToMany(
            mappedBy = "student", //Avoid creating a join table as the relation is already defined, usually goes on the side that is not the owner of the relationship
            cascade = CascadeType.ALL //So that if we delete a student all the associate grades are also deleted
    )
    private List<Grade> grades;

    @JsonIgnore //To avoid that this field is serialized as Json otherwise it will loop
    @ManyToMany(mappedBy = "students") //Avoid creating a join table as the relation is already defined is course
    private Set<Course> courses; // A Set of course to enforce that we cannot add the same student to the same course
}
