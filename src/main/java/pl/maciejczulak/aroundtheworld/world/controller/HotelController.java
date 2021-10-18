package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.Airport;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.model.Hotel;
import pl.maciejczulak.aroundtheworld.world.repository.HotelRepo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/world/hotel")
public class HotelController {
    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    public HotelRepo hotelRepo;
    public HotelController(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @PostMapping
    ResponseEntity<Hotel> addHotel(@RequestBody Hotel toAdd){
        log.info("add hotel");
        Hotel result = hotelRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping
    ResponseEntity<List<Hotel>> readAllHotels(){
        log.info("read all hotels");
        return ResponseEntity.ok(hotelRepo.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Hotel> readHotelById(@PathVariable Integer id) {
        log.info("read hotel by Id");
        return hotelRepo.findById(id)
                .map(hotel -> ResponseEntity.ok(hotel))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    ResponseEntity<Hotel> updateHotelById(@PathVariable Integer id, @RequestBody Hotel toUpdate){
        log.info("update hotel by Id");
        if(!hotelRepo.existsById(id)){
            return ResponseEntity.notFound().build();}
        toUpdate.setId(id);
        hotelRepo.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

}
