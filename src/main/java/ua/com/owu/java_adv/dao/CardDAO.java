package ua.com.owu.java_adv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.owu.java_adv.models.entity.Card;

import java.util.List;

public interface CardDAO extends JpaRepository<Card, Integer> {

    @Query ("select c from Card c join fetch c.user")
    List<Card> customQueryCardsWithUser();
}
