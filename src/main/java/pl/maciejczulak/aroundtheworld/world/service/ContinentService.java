package pl.maciejczulak.aroundtheworld.world.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {
    private static final Logger log = LoggerFactory.getLogger(ContinentService.class);
    public ContinentRepo continentRepo;
    public ContinentService(ContinentRepo continentRepo) {
        this.continentRepo = continentRepo;
    }

    public List<Continent> getContinentsList() {
        log.info("Getting continents list");
       return continentRepo.findAll();
    }

    public Optional<Continent> getContinent(Integer id) {
        Optional<Continent> continentOptional = continentRepo.findById(id);
        if(continentOptional.isEmpty()){
            log.info("Continent with id={} not found", id); }
        log.info("Getting continent with id={}", id);
        return continentOptional;
    }

    public Continent addContinent(Continent continent) {
        log.info("Succesfully saved continent {} to database", continent.getName());
        return continentRepo.save(continent);
    }

    public Continent updateContinent (Integer id, Continent continent){
        Optional<Continent> continentOptional = continentRepo.findById(id);
        if(continentOptional.isEmpty()){
            log.info("Continent with id={} not found", id);
        return null;}
        continent.setId(id);
        continentRepo.save(continent);
        log.info("Continent with id={} successfully updated", id);
        return continent;
    }

    public void deleteContinent(Integer id) {
        Optional<Continent> continentOptional = continentRepo.findById(id);
        continentOptional.ifPresentOrElse(continent -> {
                    continentRepo.delete(continent);
                    log.info("Continent with id={} deleted from database", id);
                },
                () -> log.info("Continent with id={} not found", id)
        );
    }
}


