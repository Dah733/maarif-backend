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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rank implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdRank;
    private String Total;
}
