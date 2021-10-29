package pl.maciejczulak.aroundtheworld.trip.service;

import org.junit.jupiter.api.Test;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TripPresentationServiceTest {



    @Test
    void getPromotedTrips() {
        //given
        final Continent europe = new Continent(1, "Europe");
        final Country france  = new Country(1, "France", 1);
        final Country spain  = new Country(2, "Spain", 1);
        final City paris = new City(1, "Paris", 1);
        final City lyon = new City(2, "Lyon", 1);
        final City nice = new City(3, "Nice", 1);
        final City madrid = new City(4, "Madrid", 2);
        final City barcelona = new City(5, "Barcelona", 2);
        final City sevilla = new City(6, "Sevilla", 2);
        final Trip trip1 = new Trip(1, "Francja-elegancja", 4, null, 1, null, null, null, null, null, null, null, true);
        final Trip trip2 = new Trip(1, "Francja-elokwencja", 4, null, 2, null, null, null, null, null, null, null, true);
        final Trip trip3 = new Trip(1, "Słoneczna hiszpania", 1, null, 5, null, null, null, null, null, null, null, true);

        List<Trip> trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);

        //when


        //then


    }
}