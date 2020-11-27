package net.avanishkpandey.universum.continentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region implements Serializable {
    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Region name is required.")
    private String name;

    @Column(name = "region_area", nullable = false)
    private BigDecimal regionArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="continent_id")
    private Continent continent;

    @OneToMany(mappedBy="region", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Country> countries = new ArrayList<>();
}
