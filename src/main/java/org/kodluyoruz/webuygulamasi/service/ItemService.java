package org.kodluyoruz.webuygulamasi.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.webuygulamasi.config.AppConfig;
import org.kodluyoruz.webuygulamasi.dto.ItemDto;
import org.kodluyoruz.webuygulamasi.exception.ItemNotFoundException;
import org.kodluyoruz.webuygulamasi.model.Item;
import org.kodluyoruz.webuygulamasi.model.Status;
import org.kodluyoruz.webuygulamasi.repository.ItemRepository;
import org.kodluyoruz.webuygulamasi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	Mapper dozerMapper;

//	public ItemDto add(ItemDto itemDto) {
//		Item item = new Item();
//		item.setName(itemDto.getName());
//		item.setPrice(itemDto.getPrice());
//
//		// Normalde epochTime olarak kayıt edilir.
//		// GMT + 3
//		// new Date UTC olarak tarih olusturur.
//		item.setCreatedAt(new Date());
//		itemRepository.save(item);
//		itemDto.setId(item.getId());
//		return itemDto;
//	}

	public ItemDto add(ItemDto itemDto) {
		Item item = dozerMapper.map(itemDto, Item.class);
		Optional<Status> foundStatus = statusRepository.findById(itemDto.getStatus().getId());
		item.setStatus(statusRepository.findById(1).get());
		if (foundStatus.isPresent()) {
			item.setStatus(foundStatus.get());
		}
		// Normalde epochTime olarak kayıt edilir.
		// GMT + 3
		// new Date UTC olarak tarih olusturur.
		// item.setCreatedAt(new Date());
		itemRepository.save(item);
		// itemDto.setId(item.getId());
		return dozerMapper.map(item, ItemDto.class);
	}
	
	public ItemDto get(Integer id) {
		Optional<Item> foundItem = itemRepository.findById(id);
		if (foundItem.isPresent()) {
			return dozerMapper.map(foundItem.get(), ItemDto.class);
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

			itemDtos.add(dozerMapper.map(item, ItemDto.class));
		});
		return itemDtos;
	}
}
