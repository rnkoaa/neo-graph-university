/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.richard.neo.university;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
            .credentials("neo4j", "xxxxx")
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
                new Subject("Biology")
            );
            var presavedSchool = new School("Hilly Fields Technical College");
            Set<Teacher> teachers = Set.of(
                new Teacher("Mr. Balls", Set.of(
                    new Subject("Physics")
                )),
                new Teacher("Ms. Packard-Bell", Set.of(
                    new Subject("Applied Mathematics")
                )),
                new Teacher("Mr. Smith", Set.of(
                    new Subject("Systems Engineering")
                )),
                new Teacher("Mrs. Adenough", Set.of(
                    new Subject("Chemical Engineering")
                )),
                new Teacher("Mr. Van der Graaf", Set.of(
                    new Subject("Pure Mathematics"),
                    new Subject("Applied Mathematics")
                )),
                new Teacher("Ms. Noethe", Set.of(
                    new Subject("Earth Science")
                )),
                new Teacher("Mrs. Noakes", Set.of(
                    new Subject("Biology")
                )),
                new Teacher("Mr. Marker", Set.of(
                    new Subject("Pure Mathematics")
                )),
                new Teacher("Ms. Delgado", Set.of(
                    new Subject("Civil Engineering")
                )),
                new Teacher("Mrs. Glass", Set.of(
                    new Subject("Applied Mathematics")
                )),
                new Teacher("Mr. Flint", Set.of(
                    new Subject("Pure Mathematics")
                )),
                new Teacher("Mr. Kearney", Set.of(
                    new Subject("Physics")
                )),
                new Teacher("Mrs. Forrester"),
                new Teacher("Mrs. Fischer", Set.of(
                    new Subject("Biology"),
                    new Subject("Electrical Engineering")
                )),
                new Teacher("Mr. Jameson", Set.of(
                    new Subject("Mechanical Engineering")
                ))
            );

            // lets save all subjects

            Set<Subject> modifiableSubjects = teachers.stream()
                .flatMap(teacher -> teacher.getSubjects().stream())
                .collect(Collectors.toSet());
            modifiableSubjects.addAll(subjects);

            var savedSubjects = modifiableSubjects.stream()
                .map(app.subjectService::createOrUpdate)
                .collect(Collectors.toMap(Subject::getName, s -> s));

            teachers.stream()
                .map(teacher -> {
                    Set<Subject> teacherSubjects = teacher.getSubjects();
                    return teacher;
                });

//
//            presavedSchool.setTeachers(teachers);
//
//            presavedSchool.setDepartments(Set.of(
//                new Department("Mathematics", Set.of(
//                    new Subject("Pure Mathematics", Set.of(
//                        new Teacher("Mr. Flint"),
//                        new Teacher("Mr. Marker"),
//                        new Teacher("Mr. Van der Graaf")
//                    )),
//                    new Subject("Applied Mathematics", Set.of(
//                        new Teacher("Mrs. Glass"),
//                        new Teacher("Ms. Packard-Bell"),
//                        new Teacher("Mr. Van der Graaf")
//                    ))
//                )),
//                new Department("Science", Set.of(
//                    new Subject("Physics", Set.of(
//                        new Teacher("Mr. Balls"),
//                        new Teacher("Mr. Kearney")
//                    )),
//                    new Subject("Chemistry", Set.of(
//                        new Teacher("Mr. Kearney"),
//                        new Teacher("Mrs. Noakes")
//                    )),
//                    new Subject("Earth Science", Set.of(
//                        new Teacher("Ms. Noethe")
//                    ))
//                )),
//                new Department("Engineering", Set.of(
//                    new Subject("Mechanical Engineering", Set.of(
//                        new Teacher("Mr. Jameson")
//                    )),
//                    new Subject("Systems Engineering", Set.of(
//                        new Teacher("Mr. Smith")
//                    )),
//                    new Subject("Chemical Engineering", Set.of(
//                        new Teacher("Mrs. Adenough")
//                    )),
//                    new Subject("Civil Engineering", Set.of(
//                        new Teacher("Ms. Delgado")
//                    )),
//                    new Subject("Electrical Engineering", Set.of(
//                        new Teacher("Mrs. Fischer")
//                    ))
//                ))
//
//            ));
//            var school = app.findOrCreateSchool(presavedSchool);

//            app.createTeacher(school, new Teacher("Mr. Balls"));
//            app.createTeacher(school, new Teacher("Ms. Packard-Bell"));
//            app.createTeacher(school, new Teacher("Mr. Smith"));
//            app.createTeacher(school, new Teacher("Mrs. Adenough"));
//            app.createTeacher(school, new Teacher("Mr Van der Graaf"));
//            app.createTeacher(school, new Teacher("Ms Noethe"));
//            app.createTeacher(school, new Teacher("Mrs Noakes"));
//            app.createTeacher(school, new Teacher("Mr Marker"));
//            app.createTeacher(school, new Teacher("Ms Delgado"));
//            app.createTeacher(school, new Teacher("Mrs Glass"));
//            app.createTeacher(school, new Teacher("Mr Flint"));
//            app.createTeacher(school, new Teacher("Mr Kearney"));
//            app.createTeacher(school, new Teacher("Mrs Forrester"));
//            app.createTeacher(school, new Teacher("Mrs Fischer"));
//            app.createTeacher(school, new Teacher("Mr Jameson"));

//            app.schoolService.createOrUpdate();
//            var department = new Department("Chemistry");
//
//            Subject subject = new Subject("Computer Science", department);
//            department.addSubject(subject);
//            var subject2 = new Subject("Physical Chemistry", department);
//            department.addSubject(subject2);
//            Department saved = departmentService.createOrUpdate(department);
//            System.out.println(saved);
//
//            Optional<Department> chemistry = departmentService.findByName("Chemistry");
//            assert chemistry.isPresent();
//
//            Department department1 = chemistry.get();
//            System.out.println(department1);
            tx.commit();
        }

//        System.out.println(new App().getGreeting());
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
