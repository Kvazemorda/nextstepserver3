package com.nextstepserver.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false) private int id;
    @Basic @Column private String name;
    @Basic @Column private String email;
    @OneToMany(fetch = FetchType.LAZY) private Set<TargetEntity> targets = new HashSet<>();
    @ManyToMany private Set<FamilyEntity> familyEntitySet = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY) private Set<BalanceEntity> balanceEntities = new HashSet<>();

    public PersonEntity() {
    }

    public PersonEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Set<TargetEntity> getTargets() {
        return targets;
    }
    public void setTargets(Set<TargetEntity> targets) {
        this.targets = targets;
    }


    public Set<FamilyEntity> getFamilyEntitySet() {
        return familyEntitySet;
    }
    public void setFamilyEntitySet(Set<FamilyEntity> familyEntitySet) {
        this.familyEntitySet = familyEntitySet;
    }

    public Set<BalanceEntity> getBalanceEntities() {
        return balanceEntities;
    }

    public void setBalanceEntities(Set<BalanceEntity> balanceEntities) {
        this.balanceEntities = balanceEntities;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", targetsById=" + targets +
                '}';
    }
}
