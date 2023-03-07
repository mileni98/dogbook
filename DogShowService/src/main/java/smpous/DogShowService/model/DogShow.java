package smpous.DogShowService.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.ToString;

@lombok.Data
@ToString
@Document(collection = "dog_show")
public class DogShow {

    @Id
    private String id;

    @Field(value = "name")
    private String name;

    @Field(value = "location")
    private String location;

    @Field(value = "date")
    private Date date;

    @Field(value = "status")
    private Boolean finished;

    @Field(value = "closed")
    private Boolean closed;

    @Field(value = "maximumScore")
    private Integer maximumScore;
   
}
