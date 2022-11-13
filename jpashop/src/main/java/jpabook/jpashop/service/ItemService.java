package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 위에서 readonly True로 잡혀있어서 오버라이딩 해줘야 save 가능함
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 트랙잭션이 commit 되면, flush 진행
     * flush 과정에서 변경된 엔티티가 무엇인지 찾아냄
     * 바뀐 내용에 대해 JPA가 업데이트 쿼리를 날림
     * ** 이것이 변경 감지 기능임 **
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // item id 가지고 repository에서 아이템 호출 > 영속 상태의 엔티티를 찾아옴
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
