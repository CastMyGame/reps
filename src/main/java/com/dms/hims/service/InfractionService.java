package com.dms.hims.service;

import com.dms.hims.data.InfractionRepository;
import com.dms.hims.data.StudentRepository;
import com.dms.hims.model.Infraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfractionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InfractionRepository repository;

    public InfractionService (InfractionRepository repository,
                              StudentRepository studentRepository) {
        this.repository = repository;
    }

    public Optional<Infraction> findInfractionByName (String name) {
        var findMe = repository.findByInfractionName(name);

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No infraction with that name exists");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<Infraction> findInfractionById (Integer id) {
        var findMe = repository.findByInfractionId(id);

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No infraction with that ID exists");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Infraction createNewInfraction (Infraction infraction) {
        return repository.save(infraction);
    }
}
