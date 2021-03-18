package com.victor.model.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Component
public class CityDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CityDto() {
    }

    public CityDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CityDto(Long id, @NotEmpty String name, @NotEmpty String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDto cityDto = (CityDto) o;
        return Objects.equals(id, cityDto.id) &&
                Objects.equals(name, cityDto.name) &&
                Objects.equals(description, cityDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
