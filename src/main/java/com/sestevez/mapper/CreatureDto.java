package com.sestevez.mapper;

import java.util.Objects;

public class CreatureDto {

    private String type;
    private int age;
    private String name;

    public CreatureDto() {
    }

    public CreatureDto(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public static CreatureDto fromEntity(Creature entity) {
        return new CreatureDto(entity.name, entity.age, entity.type);
    }

    public Creature toEntity() {
        return new Creature(name, age, type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatureDto)) return false;
        CreatureDto that = (CreatureDto) o;
        return age == that.age &&
                type.equals(that.type) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, age, name);
    }

}
