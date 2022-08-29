package org.richard.neo.university;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "StudyBuddy")
public class StudyBuddy extends Entity {

    @Relationship(type = "BUDDY")
    private List<Student> buddies = new ArrayList<>();

    @JsonProperty("course")
    private Course course;

    public List<Student> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<Student> buddies) {
        this.buddies = buddies;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
