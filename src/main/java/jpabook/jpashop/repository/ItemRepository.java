package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;
    
    //상품 저장 
    public void itemSave(Item item) { //아이템은 저장할떄까지 아이템 아이디가 없다? 그래서 일단 없으면 머지로 된다.
        if (item.getId() == null) {
            em.persist(item);
        }else {
            em.merge(item);
        }
    }
    
    //상품 한개 찾기
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //상품 여러개 찾기
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
