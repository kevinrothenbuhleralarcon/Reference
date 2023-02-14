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
import javax.persistence.OneToMany;
import java.util.List;

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
    @Column(nullable = false)
    private String subject;

    @NonNull
    @Column(nullable = false)
    private String code;

    @NonNull
    @Column(nullable = false)
    private String description;

    @JsonIgnore //To avoid that this field is serialized as Json otherwise it will loop
    @OneToMany(
            mappedBy = "course", //Avoid creating a join table as the relation is already defined, usually goes on the side that is not the owner of the relationship
            cascade = CascadeType.ALL //So that if we delete a student all the associate grades are also deleted
    )
    private List<Grade> grades;
}
