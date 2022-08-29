package org.richard.neo.university;

import org.neo4j.ogm.session.Session;

public class DepartmentServiceImpl extends Neo4jServiceImpl<Department> {

    private final Session session;

    public DepartmentServiceImpl(Session session) {
        super(session);
        this.session = session;
    }

//    // MATCH (n:Department) RETURN n LIMIT 25
//    Optional<Department> findByName(String name) {
//        var result = session.query(
//            """
//                MATCH(n: Department {name: $name}) RETURN n
//                """,
//            Map.of("name", name)
//        );
//        List<Department> departments = new ArrayList<>();
//        for (Map<String, Object> next : result) {
//            Set<String> strings = next.keySet();
//            for (String string : strings) {
//                Object o = next.get(string);
//                Department department = (Department) o;
//                departments.add(department);
//            }
//        }
//        if (departments.size() > 0) {
//            return Optional.of(departments.get(0));
//        }
//        return Optional.empty();
//    }

    // ensures only one item exists all the time.
//    @Override
//    public Department createOrUpdate(Department entity) {
//        Optional<Department> maybeEntity = findByName(entity.getName());
//        if (maybeEntity.isEmpty()) {
//            return super.createOrUpdate(entity);
//        }
//        Department department = maybeEntity.get();
//        department.setName(entity.getName());
////        if(entity.getSubjects().size() > 0) {
////            Set<Subject> subjects = entity.getSubjects().stream()
////                .map(subjectService::createOrUpdate)
////                .collect(Collectors.toSet());
////        }
//        return super.createOrUpdate(department);
//    }

    @Override
    Class<Department> getEntityType() {
        return Department.class;
    }
}
