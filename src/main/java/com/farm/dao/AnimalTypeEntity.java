package com.farm.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "animal_type", schema = "farm", catalog = "")
public class AnimalTypeEntity {
    private int typeId;
    private String typeName;
    private String typeImg;

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

    @Basic
    @Column(name = "type_img", nullable = true, length = 255)
    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalTypeEntity that = (AnimalTypeEntity) o;
        return typeId == that.typeId &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(typeImg, that.typeImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName, typeImg);
    }
}
