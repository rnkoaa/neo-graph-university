package org.richard.neo.university;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class School extends Entity implements Nameable {

    private String name;

    @Relationship(type = "DEPARTMENT")
    private Set<Department> departments = new HashSet<>();

    @Relationship(type = "STAFF")
    private Set<Teacher> teachers = new HashSet<>();

    @Relationship(type = "HEAD_TEACHER")
    private Teacher headTeacher ;

    @Relationship(type = "STUDENT")
    private Set<Student> students = new HashSet<>();

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Teacher getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(Teacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }
}
