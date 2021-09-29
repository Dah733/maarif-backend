package com.maarif.maarifbackend.inscription.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdStudent;
    private Long matriculeStudent;
    private String nomPrenomStudent;
    private String nomPereStudent;
    private String nomMereStudent;
    private Long NNI;
    private Long RIM;
    @Temporal(TemporalType.DATE)
    private Date dateNaisStudent;
    private String lieuNaisStudent;
    private String photoStudent;

//    @ManyToOne
//    private ModaliteDePaiment modaliteDePaiment;

    @ManyToOne
    private Classe classe;
}
