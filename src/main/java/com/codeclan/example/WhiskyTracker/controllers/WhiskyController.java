package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;





@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam Optional<Integer> year,
            @RequestParam Optional<Distillery> distillery,
            @RequestParam Optional<Integer> age){
        if(year.isPresent()){
            return new ResponseEntity<>(whiskyRepository.findByYear(year.get()), HttpStatus.OK);
        } else if (distillery.isPresent() && age.isPresent()) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryAndAge(distillery.get(), age.get()), HttpStatus.OK);

        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }



// this is one way of hitting the MVP, but t's intentionally limited to specific arguments, ie age and distillery
// preferable to use the get all/conditional in some cases, eg when only one may be present - giving up part way thru
//
//    @GetMapping(value = "/whiskies/{age}/{distillery}")
//    public ResponseEntity getWhisky(@PathVariable Long id, @PathVariable int age, @PathVariable Distillery distillery){
////        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
//
//
//

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/whiskies")
    public ResponseEntity<Whisky> createWhisky(@RequestBody Whisky whisky){
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }

}
