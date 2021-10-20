package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.Hotel;
import pl.maciejczulak.aroundtheworld.world.service.HotelService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/world/hotel")
public class HotelController {
    private static final Logger log = LoggerFactory.getLogger(HotelController.class);
    public HotelService hotelService;
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getHotelsList(){
        log.info("Attempt to get hotels list");
        return hotelService.getHotelsList();
    }

    @GetMapping("/{id}")
    public Optional<Hotel> getHotel(@PathVariable Integer id){
        log.info("Attempt to get hotel with id={}", id);
        return hotelService.getHotel(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel){
        log.info("Attempt to save hotel {} to database", hotel.getName());
        return hotelService.addHotel(hotel);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable Integer id, @RequestBody Hotel toUpdate){
        log.info("Attempt to update hotel with id={}", id);
        return hotelService.updateHotel(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Integer id){
        log.info("Attempt to delete hotel with id={}", id);
        hotelService.deleteHotel(id);
    }
}
