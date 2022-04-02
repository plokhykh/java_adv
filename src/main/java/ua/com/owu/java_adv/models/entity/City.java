package ua.com.owu.java_adv.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cityName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="user_city",
            joinColumns = @JoinColumn(name="city_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> users;
}
