package com.mabaya.assignment.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor
public abstract class DataObject {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private long id;

}
