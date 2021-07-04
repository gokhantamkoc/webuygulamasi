package org.kodluyoruz.webuygulamasi.exception;

@SuppressWarnings("serial")
public class ItemNotFoundException extends NotFoundException {
	
	public ItemNotFoundException(Integer id) {
		super(String.format("Ürün bulunamadı! (Id = %s)", id));
	}
}
