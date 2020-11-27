package net.avanishkpandey.universum.continentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ContinentDTO implements Serializable {
    private Long id;
    private String name;
    private List<RegionDTO> regions;

    public void addRegion(final RegionDTO regionDto) {
        if(regions == null) regions = new ArrayList<>();
        regions.add(regionDto);
    }
}
