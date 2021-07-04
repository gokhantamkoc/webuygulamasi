package org.kodluyoruz.webuygulamasi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kodluyoruz.webuygulamasi.dto.ItemDto;
import org.kodluyoruz.webuygulamasi.exception.ItemNotFoundException;
import org.kodluyoruz.webuygulamasi.model.Item;
import org.kodluyoruz.webuygulamasi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public ItemDto add(ItemDto itemDto) {
		Item item = new Item();
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		itemRepository.save(item);
		itemDto.setId(item.getId());
		return itemDto;
	}
	
	public ItemDto get(Integer id) {
		Optional<Item> foundItem = itemRepository.findById(id);
		if (foundItem.isPresent()) {
			ItemDto itemDto = new ItemDto();
			itemDto.setId(foundItem.get().getId());
			itemDto.setName(foundItem.get().getName());
			itemDto.setPrice(foundItem.get().getPrice());
			return itemDto;
		}
		throw new ItemNotFoundException(id);
	}
	
	public ItemDto update(ItemDto itemDto) {
		Optional<Item> foundItem = itemRepository.findById(itemDto.getId());
		if (foundItem.isPresent()) {
			foundItem.get().setName(itemDto.getName());
			foundItem.get().setPrice(itemDto.getPrice());
			itemRepository.save(foundItem.get());
			return itemDto;
		}
		throw new ItemNotFoundException(itemDto.getId());
	}
	
	public List<ItemDto> list() {
		List<ItemDto> itemDtos =  new ArrayList<ItemDto>();
		itemRepository.findAll().forEach(item -> {
			ItemDto itemDto = new ItemDto();
			itemDto.setId(item.getId());
			itemDto.setName(item.getName());
			itemDto.setPrice(item.getPrice());
			itemDtos.add(itemDto);
		});
		return itemDtos;
	}
}
