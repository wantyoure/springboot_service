package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("album") //분류 할 수 있도록 만들 수 있는 것
public class Album extends Item{

    private String artist;
    private String etc;
}
