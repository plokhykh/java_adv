package ua.com.owu.java_adv.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String avatar;
    private String email;


    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public User(String name, String avatar, String email) {
        this.name = name;
        this.avatar = avatar;
        this.email = email;
    }


}
