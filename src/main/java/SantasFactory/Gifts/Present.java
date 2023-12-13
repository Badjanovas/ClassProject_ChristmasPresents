package SantasFactory.Gifts;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Present {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private Double weight;
    private String color;

    @ManyToOne
    @JoinColumn(name = "goodKid_id")
    private GoodKid goodKid;

    public Present(String name, Integer price, String description, Double weight, String color) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.color = color;
    }
}
