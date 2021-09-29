package com.maarif.maarifbackend.inscription.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdClasse;
    private String DesignationClasse;
    private String AbreviationClasse;
    private String Annee;


    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Course> course;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Student> students;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<TempStudent> tempStudent;
}
