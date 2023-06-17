package com.dms.reps.service;

import com.dms.reps.data.InfractionRepository;
import com.dms.reps.model.Infraction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfractionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InfractionRepository repository;
    private final MongoTemplate mongoTemplate;

    public Optional<Infraction> findInfractionByInfractionName (String infractionName) {
        var findMe = repository.findByInfractionName(infractionName);

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No infraction with that name exists");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<Infraction> findInfractionByInfractionId (Integer infractionId) {
        var findMe = repository.findByInfractionId(infractionId);

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No infraction with that ID exists");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Infraction createNewInfraction (Infraction infraction ) {
        return repository.save(infraction);
    }
    public String deleteInfraction ( Infraction infraction ) throws ResourceNotFoundException {
        try{repository.delete(infraction);}
        catch (Exception e) {
            throw new ResourceNotFoundException("That infraction does not exist");
        } return "${infraction} has been deleted";
    }
}