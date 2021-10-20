package pl.maciejczulak.aroundtheworld.trip.service;

import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.trip.model.Test;
import pl.maciejczulak.aroundtheworld.trip.repository.TestRepo;

@Service
public class TestService {
    public TestRepo testRepo;

    public TestService(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    public Test addTest(Test toAdd) {
       if (toAdd.getDesc()==null){toAdd.setDesc("Empty desc");}
        testRepo.save(toAdd);
        return toAdd;

    }
}
