package com.caterpillar.toyrobotapi.controller;


import com.caterpillar.toyrobotapi.dto.ToyRobotRequest;
import com.caterpillar.toyrobotapi.service.ToyRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/command", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ToyRobotController {

    private final ToyRobotService toyRobotService;

    @Autowired
    public ToyRobotController(ToyRobotService toyRobotService) {
        this.toyRobotService = toyRobotService;
    }

    /**
     * PostMapping is chosen as it places the Toy and then moves it. So something is getting created in the
     * server everytime.
     * @param toyRobotRequest
     * @return
     */
    @PostMapping
    ResponseEntity<String> moveToy(@RequestBody ToyRobotRequest toyRobotRequest) {
        String toyRobotResponse = toyRobotService.moveToy(toyRobotRequest);
        return new ResponseEntity<>(toyRobotResponse, HttpStatus.CREATED);
    }
}
