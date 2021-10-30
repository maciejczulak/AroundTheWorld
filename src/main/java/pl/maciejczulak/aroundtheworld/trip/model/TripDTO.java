package pl.maciejczulak.aroundtheworld.trip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDTO {
    private Integer id;

    private Integer startCityId;
    private Integer startCountryId;
    private Integer startContinentId;

    private Integer destCityId;
    private Integer destCountryId;
    private Integer destContinentId;

}
