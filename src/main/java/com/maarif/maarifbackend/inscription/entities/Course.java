package com.maarif.maarifbackend.inscription.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdCoursse;
    private String DesignationCoursse;
    private String AbreviationCoursse;

    @ManyToOne
    private Classe classe;

}
