package org.kodluyoruz.webuygulamasi.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Before;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name="item")
@Data
@NoArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="item_name", nullable=false, unique = true)
	private String name;
	
	@Column(name="price", nullable=false)
	private float price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@PrePersist
	public void setTimestamps() {
		this.createdAt = new Date();
	}


}
