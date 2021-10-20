package pl.maciejczulak.aroundtheworld.world.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private static final Logger log = LoggerFactory.getLogger(CountryService.class);
    public CountryRepo countryRepo;
    public CountryService(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public List<Country> getCountriesList() {
        log.info("Getting countries list");
        return countryRepo.findAll();
    }

    public Optional<Country> getCountry(Integer id) {
        Optional<Country> countryOptional = countryRepo.findById(id);
        if(countryOptional.isEmpty()){
            log.info("Country with id={} not found", id); }
        log.info("Getting country with id={}", id);
        return countryOptional;
    }

    public Country addCountry(Country country) {
        log.info("Succesfully saved country {} to database", country.getName());
        return countryRepo.save(country);
    }

    public Country updateCountry (Integer id, Country country){
        Optional<Country> countryOptional = countryRepo.findById(id);
        if(countryOptional.isEmpty()){
            log.info("Country with id={} not found", id);
            return null;}
        country.setId(id);
        countryRepo.save(country);
        log.info("Country with id={} successfully updated", id);
        return country;
    }

    public void deleteCountry(Integer id) {
        Optional<Country> countryOptional = countryRepo.findById(id);
        countryOptional.ifPresentOrElse(country -> {
                    countryRepo.delete(country);
                    log.info("Country with id={} deleted from database", id);
                },
                () -> log.info("Country with id={} not found", id)
        );
    }

}
