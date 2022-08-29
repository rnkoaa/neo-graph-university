package org.richard.neo.university;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Teacher extends Entity implements Nameable {

    private String name;

    @Relationship(type = "TEACHES_CLASS")
    private Set<Course> courses = new HashSet<>();

    @Relationship(type = "TAUGHT_BY", direction = Relationship.INCOMING)
    private Set<Subject> subjects = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }
    public Teacher(String name, Set<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
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
        Teacher teacher = (Teacher) o;
        return name.equals(teacher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "name='" + name + '\'' +
            ", courses=" + courses +
            ", subjects=" + subjects +
            '}';
    }
}
