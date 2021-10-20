package pl.maciejczulak.aroundtheworld.trip.cotroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.trip.model.Test;
import pl.maciejczulak.aroundtheworld.trip.repository.TestRepo;
import pl.maciejczulak.aroundtheworld.trip.service.TestService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trip/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    public TestRepo testRepo;
    public TestService testService;

    public TestController(TestRepo testRepo, TestService testService) {
        this.testRepo = testRepo;
        this.testService = testService;
    }

    //@PostMapping
    //ResponseEntity<Test> addTest(@RequestBody Test toAdd){
    //    log.info("add test");
     //   Test result = testRepo.save(toAdd);
     //   return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    //}

    @PostMapping
    Test addTests(@RequestBody Test toAdd){
        log.info("add test");
        return testService.addTest(toAdd);
    }

    @GetMapping
    ResponseEntity<List<Test>> readAllTest(){
        log.info("read all tests");
        return ResponseEntity.ok(testRepo.findAll());
    }


}
