package com.emax.dms.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
public class Device extends AbstractPersistable<Long> {

    private String name;

    private String description;

}
