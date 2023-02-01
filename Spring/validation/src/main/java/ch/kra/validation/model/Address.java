package ch.kra.validation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Locality cannot be blank")
    private String locality;

    @NotBlank(message = "Street cannot be blank")
    private String street;

}
