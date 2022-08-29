package org.richard.neo.university;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Subject extends Entity implements Nameable {

    @Index(unique = true)
    private String name;

    @Relationship(type = "CURRICULUM", direction = Relationship.INCOMING)
    private Department department;

    @Relationship(type = "TAUGHT_BY")
    private Set<Teacher> teachers = new HashSet<>();

    @Relationship(type = "SUBJECT_TAUGHT", direction = "INCOMING")
    private Set<Course> courses = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, Department department) {
        this(name);
        this.department = department;
    }
    public Subject(String name, Set<Teacher> teachers) {
        this(name);
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subject subject = (Subject) o;
        return name.equals(subject.name) && Objects.equals(department, subject.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department);
    }
}
