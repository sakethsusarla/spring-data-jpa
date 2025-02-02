package com.practice.jpahibernatebatchinsertupdate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Pod.TABLE_NAME)
public class Pod {
    static final String TABLE_NAME = "pods";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 20, unique = true, updatable = false)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "pod", cascade = CascadeType.ALL)
    @Size(min = 1, max = 10)
    private List<Container> containers;

    public Pod() {
    }

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

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pod pod = (Pod) o;
        return Objects.equals(id, pod.id) && Objects.equals(name, pod.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Pod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", containers=" + containers +
                '}';
    }
}
