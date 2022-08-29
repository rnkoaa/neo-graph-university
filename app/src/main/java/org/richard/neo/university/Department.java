package org.richard.neo.university;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Department extends Entity implements Nameable {

    @Index(unique = true)
    private String name;

    @Relationship(type = "CURRICULUM")
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public String toString() {
        return "Department{" +
            "name='" + name + '\'' +
            ", subjects=" + subjects +
            '}';
    }

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, Set<Subject> subjects) {
        this(name);
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        var mutableSubjects = new HashSet<>(subjects);
        mutableSubjects.add(subject);
        subjects = Set.copyOf(mutableSubjects);
    }
}
