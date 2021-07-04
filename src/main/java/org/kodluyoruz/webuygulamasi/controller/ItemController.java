package org.kodluyoruz.webuygulamasi.controller;

import org.kodluyoruz.webuygulamasi.dto.ItemDto;
import org.kodluyoruz.webuygulamasi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public ResponseEntity<?> index() {
		return ResponseEntity.ok(itemService.list());
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<?> ekle(@PathVariable Integer id) {
		return ResponseEntity.ok(itemService.get(id));
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> ekle(@RequestBody ItemDto itemDto) {
		return ResponseEntity.ok(itemService.add(itemDto));
	}
	
//	@DeleteMapping(value = "/delete/{id}")
//	public ResponseEntity<?> sil(@PathVariable Integer id) {
//		return ResponseEntity.ok(itemService.delete(id));
//	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<?> guncelle(@RequestBody ItemDto itemDto) {
		return ResponseEntity.ok(itemService.update(itemDto));
	}
}
