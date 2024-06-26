package com.webservice.finalProject.controller;


import com.webservice.finalProject.model.Actor;
import com.webservice.finalProject.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable int id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public void addActor(@RequestBody Actor actor) {
        actorService.saveActor(actor);
    }

    @PutMapping("/{id}")
    public void updateActor(@PathVariable int id, @RequestBody Actor actor) {
        actor.setId((long) id);
        actorService.updateActor(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
    }

    @GetMapping("/name/{firstName}&{lastName}")
    public List<Actor> getActorsFuzzy(@PathVariable String firstName, @PathVariable String lastName) {
        return actorService.getActorsFuzzy(firstName,lastName);
    }
}

