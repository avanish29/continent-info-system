package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class AthleteResponse implements Serializable {
	private static final long serialVersionUID = 135789244146228114L;
	private String id;
	private String athlete;
	private int age;
	private String country;
	private int year;
	private LocalDate date;
	private String sport;
	private int gold;
	private int silver;
	private int bronze;
	private int total;
}
