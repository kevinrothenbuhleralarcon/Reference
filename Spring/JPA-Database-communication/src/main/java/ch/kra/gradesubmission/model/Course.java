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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Subject cannot be blank")
    @Column(nullable = false)
    private String subject;

    @NonNull
    @NotBlank(message = "Course code cannot be blank")
    @Column(
            nullable = false,
            unique = true // Indicate that we cannot have 2 courses with the same code
    )
    private String code;

    @NonNull
    @NotBlank(message = "Description cannot be blank")
    @Column(nullable = false)
    private String description;

    @JsonIgnore //To avoid that this field is serialized as Json otherwise it will loop
    @OneToMany(
            mappedBy = "course", //Avoid creating a join table as the relation is already defined, usually goes on the side that is not the owner of the relationship
            cascade = CascadeType.ALL //So that if we delete a student all the associate grades are also deleted
    )
    private List<Grade> grades;

    @JsonIgnore //To avoid that this field is serialized as Json because it's too messy
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Student> students;
}
