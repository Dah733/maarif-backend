package com.maarif.maarifbackend.inscription.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class TempStudent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTempStudent;
    private String nomPrenomTempStudent;
    private String nomPereTempStudent;
    private String nomMereTempStudent;
    private Long NNI;
    @Temporal(TemporalType.DATE)
    private Date dateNaisTempStudent;
    private String lieuNaisTempStudent;
    private String photoTempStudent;

//    @ManyToOne
//    private ModaliteDePaiment modaliteDePaiment;

    @ManyToOne
    private Classe classe;
}
