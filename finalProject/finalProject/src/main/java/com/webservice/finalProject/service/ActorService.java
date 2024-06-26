package com.webservice.finalProject.service;


import com.webservice.finalProject.model.Actor;
import com.webservice.finalProject.Repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(int id) {
        return actorRepository.findById(id);
    }

//    public List<Actor> getActorByName(String name) {
//        return actorRepository.findByName();
//    }

    public void saveActor(Actor actor) {
        actorRepository.save(actor);
    }

    public void updateActor(Actor actor) {
        actorRepository.update(actor);
    }

    public void deleteActor(int id) {
        actorRepository.deleteById(id);
    }

    public List<Actor> getActorsFuzzy(String firstName, String lastName) {
        return actorRepository.findByNameActorsFuzzy(firstName,lastName);
    }

    public List<Actor> getActorsByMovieId(int movieID) {
        return actorRepository.findByMovieId(movieID);
    }
}
