package pl.maciejczulak.aroundtheworld.trip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDTO {
    public Integer id;
    public String name;
    public Integer countryId;
    public Integer continentId;
}
