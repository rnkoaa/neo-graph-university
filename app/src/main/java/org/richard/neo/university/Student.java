package org.richard.neo.university;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Student extends Entity implements Nameable {

    private String name;

    @Relationship(type = "ENROLLED")
    private Set<Enrollment> enrollments = new HashSet<>();

    @Relationship(type = "BUDDY", direction = Relationship.INCOMING)
    private Set<StudyBuddy> studyBuddies = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Set<StudyBuddy> getStudyBuddies() {
        return studyBuddies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setStudyBuddies(Set<StudyBuddy> studyBuddies) {
        this.studyBuddies = studyBuddies;
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }
}
