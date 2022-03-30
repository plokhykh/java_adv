package ua.com.owu.java_adv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.java_adv.models.entity.Card;

public interface CardDAO extends JpaRepository<Card, Integer> {
}
