package net.avanishkpandey.universum.continentservice.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "olympic_winners")
@Where(clause = "deleted is NULL or deleted != true")
@Getter
@Setter
public class Athlete {
	@Id
	@Column(unique = true, nullable = false, updatable = false, length = 36, name = "guid")
	private String id = UUID.randomUUID().toString();

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdOn = LocalDateTime.now();

	@LastModifiedDate
	private LocalDateTime updatedOn = LocalDateTime.now();

	private Boolean deleted = Boolean.FALSE;

	private String fullName;

	private int age;

	@Column(nullable = false)
	private String country;

	private int year;

	private LocalDate statDate;

	private String sport;

	private int gold;

	private int silver;

	private int bronze;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Athlete athlete = (Athlete) o;

		return id != null && id.equals(athlete.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Athlete(" +
				"id = " + id + ", " +
				"createdOn = " + createdOn + ", " +
				"updatedOn = " + updatedOn + ", " +
				"deleted = " + deleted + ", " +
				"fullName = " + fullName + ", " +
				"age = " + age + ", " +
				"country = " + country + ", " +
				"year = " + year + ", " +
				"statDate = " + statDate + ", " +
				"sport = " + sport + ", " +
				"gold = " + gold + ", " +
				"silver = " + silver + ", " +
				"bronze = " + bronze + ")";
	}
}
