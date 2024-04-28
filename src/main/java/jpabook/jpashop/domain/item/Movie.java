package jpabook.jpashop.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
public class Movie extends Item{

    private String director;
    private String actor;
}
