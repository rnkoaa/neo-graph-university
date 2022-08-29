package org.richard.neo.university;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Class")
public class Course extends Entity implements Nameable{

    private String name;

    @Relationship(type = "SUBJECT_TAUGHT")
    private Subject subject;

    @Relationship(type = "TEACHES_CLASS", direction = Relationship.INCOMING)
    private Teacher teacher;

    @Relationship(type = "ENROLLED", direction = Relationship.INCOMING)
    private Set<Enrollment> enrollments = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
