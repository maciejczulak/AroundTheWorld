package pl.maciejczulak.aroundtheworld.world.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.world.model.Hotel;
import pl.maciejczulak.aroundtheworld.world.repository.HotelRepo;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private static final Logger log = LoggerFactory.getLogger(HotelService.class);
    public HotelRepo hotelRepo;
    public HotelService(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public List<Hotel> getHotelsList() {
        log.info("Getting hotels list");
        return hotelRepo.findAll();
    }

    public Optional<Hotel> getHotel(Integer id) {
        Optional<Hotel> hotelOptional = hotelRepo.findById(id);
        if (hotelOptional.isEmpty()) {
            log.info("Hotel with id={} not found", id);
        }
        log.info("Hotel city with id={}", id);
        return hotelOptional;
    }

    public Hotel addHotel(Hotel hotel) {
        log.info("Succesfully saved hotel {} to database", hotel.getName());
        return hotelRepo.save(hotel);
    }

    public Hotel updateHotel(Integer id, Hotel hotel) {
        Optional<Hotel> hotelOptional = hotelRepo.findById(id);
        if (hotelOptional.isEmpty()) {
            log.info("Hotel with id={} not found", id);
            return null;
        }
        hotel.setId(id);
        hotelRepo.save(hotel);
        log.info("Hotel with id={} successfully updated", id);
        return hotel;
    }

    public void deleteHotel(Integer id) {
        Optional<Hotel> hotelOptional = hotelRepo.findById(id);
        hotelOptional.ifPresentOrElse(airport -> {
                    hotelRepo.delete(airport);
                    log.info("Hotel with id={} deleted from database", id);
                },
                () -> log.info("Hotel with id={} not found", id)
        );
    }
}
