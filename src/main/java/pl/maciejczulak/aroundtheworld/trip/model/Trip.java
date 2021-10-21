package pl.maciejczulak.aroundtheworld.trip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer startCityId;
    private Integer startAirportId;
    private Integer destCityId;
    private Integer destAirportId;
    private Integer destHotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long tripLenght;
    private BigDecimal priceForOne;
    private Integer forHowMany;
    private Boolean promoted;

}
