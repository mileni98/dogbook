package smpous.DogService.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dog {

    @Id @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Date birthDate;

    @Property
    private String race;

    @Property
    private List<Long> children;

    @Relationship
    private List<Dog> parents;
    
}
