/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.richard.neo.university;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class App {

    Session session;
    DepartmentServiceImpl departmentService;
    SchoolServiceImpl schoolService;
    final SubjectServiceImpl subjectService;
    TeacherServiceImpl teacherService;

    public App() {
        Configuration configuration = new Configuration.Builder()
            .uri("bolt://localhost")
            .credentials("neo4j", "xxxxxxxx")
            .build();

        SessionFactory sessionFactory = new SessionFactory(configuration, "org.richard.neo.university");
        this.session = sessionFactory.openSession();
        this.subjectService = new SubjectServiceImpl(this.session);
        departmentService = new DepartmentServiceImpl(this.session);
        schoolService = new SchoolServiceImpl(this.session);
        teacherService = new TeacherServiceImpl(this.session);
    }

    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {
        var app = new App();
        try (var tx = app.session.beginTransaction()) {
            var subjects = Set.of(
                new Subject("Calculus"),
                new Subject("Trigonometry"),
                new Subject("Calculus")
            );

            subjects.forEach(app.subjectService::createOrUpdate);
//            var subjects = Set.of(
//                new Subject("Biology")
//            );
//            var presavedSchool = new School("Hilly Fields Technical College");
//            Set<Teacher> teachers = Set.of(
//                new Teacher("Mr. Balls", Set.of(
//                    new Subject("Physics")
//                )),
//                new Teacher("Ms. Packard-Bell", Set.of(
//                    new Subject("Applied Mathematics")
//                )),
//                new Teacher("Mr. Smith", Set.of(
//                    new Subject("Systems Engineering")
//                )),
//                new Teacher("Mrs. Adenough", Set.of(
//                    new Subject("Chemical Engineering")
//                )),
//                new Teacher("Mr. Van der Graaf", Set.of(
//                    new Subject("Pure Mathematics"),
//                    new Subject("Applied Mathematics")
//                )),
//                new Teacher("Ms. Noethe", Set.of(
//                    new Subject("Earth Science")
//                )),
//                new Teacher("Mrs. Noakes", Set.of(
//                    new Subject("Biology"),
//                    new Subject("Chemistry")
//                )),
//                new Teacher("Mr. Marker", Set.of(
//                    new Subject("Pure Mathematics")
//                )),
//                new Teacher("Ms. Delgado", Set.of(
//                    new Subject("Civil Engineering")
//                )),
//                new Teacher("Mrs. Glass", Set.of(
//                    new Subject("Applied Mathematics")
//                )),
//                new Teacher("Mr. Flint", Set.of(
//                    new Subject("Pure Mathematics")
//                )),
//                new Teacher("Mr. Kearney", Set.of(
//                    new Subject("Physics"),
//                    new Subject("Chemistry")
//                )),
//                new Teacher("Mrs. Forrester"),
//                new Teacher("Mrs. Fischer", Set.of(
//                    new Subject("Biology"),
//                    new Subject("Electrical Engineering")
//                )),
//                new Teacher("Mr. Jameson", Set.of(
//                    new Subject("Mechanical Engineering")
//                ))
//            );
//
//            Set<Subject> modifiableSubjects = teachers.stream()
//                .flatMap(teacher -> teacher.getSubjects().stream())
//                .collect(Collectors.toSet());
//            modifiableSubjects.addAll(subjects);
//
//            var savedSubjects = modifiableSubjects.stream()
//                .map(app.subjectService::createOrUpdate)
//                .collect(Collectors.toMap(Subject::getName, s -> s));
//
//            Set<Teacher> teachersWithSubjects = teachers.stream()
//                .peek(teacher -> {
//                    Set<Subject> teacherSubjects = teacher.getSubjects();
//                    var mappedSubjects = teacherSubjects.stream()
//                        .map(teacherSubject -> savedSubjects.getOrDefault(teacherSubject.getName(), teacherSubject))
//                        .collect(Collectors.toSet());
//                    teacher.setSubjects(mappedSubjects);
//                })
//                .collect(Collectors.toSet());
//
//            var savedTeachers = teachersWithSubjects.stream()
//                .map(app.teacherService::createOrUpdate)
//                .collect(Collectors.toMap(Teacher::getName, s -> s));
//
//            Set<Department> departments = Stream.of(
//                    new Department("Mathematics", Set.of(
//                        savedSubjects.getOrDefault("Applied Mathematics", new Subject("Applied Mathematics")),
//                        savedSubjects.getOrDefault("Pure Mathematics", new Subject("Pure Mathematics"))
//                    )),
//                    new Department("Science", Set.of(
//                        savedSubjects.getOrDefault("Physics", new Subject("Physics")),
//                        savedSubjects.getOrDefault("Chemistry", new Subject("Chemistry")),
//                        savedSubjects.getOrDefault("Earth Science", new Subject("Earth Science"))
//                    )),
//                    new Department("Engineering", Set.of(
//                        savedSubjects.getOrDefault("Mechanical Engineering", new Subject("Mechanical Engineering")),
//                        savedSubjects.getOrDefault("Systems Engineering", new Subject("Systems Engineering")),
//                        savedSubjects.getOrDefault("Chemical Engineering", new Subject("Chemical Engineering")),
//                        savedSubjects.getOrDefault("Civil Engineering", new Subject("Civil Engineering")),
//                        savedSubjects.getOrDefault("Electrical Engineering", new Subject("Electrical Engineering"))
//                    ))
//                )
//                .map(department -> app.departmentService.createOrUpdate(department))
//                .collect(Collectors.toSet());
//            presavedSchool.setTeachers(new HashSet<>(savedTeachers.values()));
//            presavedSchool.setDepartments(departments);
//            presavedSchool.setHeadTeacher(new Teacher("Mr Jameson"));
//            app.schoolService.createOrUpdate(presavedSchool);
            tx.commit();
        }
    }

    School findOrCreateSchool(School school) {
        if (school.getId() != null && school.getId() > 0) {
            return school;
        }

        Set<Teacher> savedTeachers = school.getTeachers().stream()
            .map(teacherService::createOrUpdate)
            .collect(Collectors.toSet());

        school.setTeachers(savedTeachers);
        var maybeSchool = schoolService.findByName(school.getName());
        return maybeSchool.orElse(schoolService.createOrUpdate(school));
    }

    void createTeacher(School school, Teacher teacher) {
        var schoolCopy = school;
        if (school.getId() == null || school.getId() <= 0) {
            var maybeSchool = schoolService.findByName(school.getName());
            schoolCopy = maybeSchool.orElse(schoolService.createOrUpdate(school));
        }

        var teacherCopy = teacher;
        if (teacher.getId() == null || teacher.getId() <= 0) {
            var maybeTeacher = teacherService.findByName(teacher.getName());
            teacherCopy = maybeTeacher.orElse(teacherService.createOrUpdate(teacher));
        }

        schoolCopy.addTeacher(teacherCopy);
        schoolService.createOrUpdate(school);
    }
}
