package com.farm.dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "animal_type", schema = "farm", catalog = "")
public class AnimalTypeEntity {
    private int typeId;
    private String typeName;
    private Collection<AnimalEntity> animalsByTypeId;

    @Id
    @Column(name = "type_id", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 25)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalTypeEntity that = (AnimalTypeEntity) o;
        return typeId == that.typeId &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }

    @OneToMany(mappedBy = "animalTypeByAnimalType")
    public Collection<AnimalEntity> getAnimalsByTypeId() {
        return animalsByTypeId;
    }

    public void setAnimalsByTypeId(Collection<AnimalEntity> animalsByTypeId) {
        this.animalsByTypeId = animalsByTypeId;
    }
}
