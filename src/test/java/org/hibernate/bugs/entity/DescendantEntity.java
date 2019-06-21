package org.hibernate.bugs.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "descendant")
@DiscriminatorValue("DESCENDANT")
public class DescendantEntity extends BaseEntity {
    private String surname;

    public DescendantEntity() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
