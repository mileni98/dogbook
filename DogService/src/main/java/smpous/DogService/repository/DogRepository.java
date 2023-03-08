package smpous.DogService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import smpous.DogService.model.Dog;

public interface DogRepository extends Neo4jRepository<Dog, Long> {

    Optional<Dog> findByName(String name);
    public List<Dog> findAll();
    public Dog getById(Long id);

}
