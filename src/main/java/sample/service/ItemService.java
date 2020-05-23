package sample.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sample.entity.Item;
import sample.repository.ItemRepository;

@Service
public class ItemService {
  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public boolean isItemInStock(String itemId) {
    var i = itemRepository.selectInventoryQuantity(itemId);
    return (i != null && i > 0);
  }

  public Item getItem(String itemId) {
    return itemRepository.selectItem(itemId);
  }

  public List<Item> getItemsByProduct(String productId) {
    return itemRepository.selectItemsByProduct(productId);
  }
}
