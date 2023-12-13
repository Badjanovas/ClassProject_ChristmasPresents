package SantasFactory.Gifts;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class GoodKid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private Long phoneNumber;
    private String address;
    private Integer age;

    @OneToMany(mappedBy = "goodKid", cascade = CascadeType.ALL)
    private List<Present> presents;

    public GoodKid(String name, String lastName, Long phoneNumber, String address, Integer age, List<Present> presents) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.presents = presents;
    }
}
