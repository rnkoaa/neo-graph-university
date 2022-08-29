package org.richard.neo.university;

import java.util.Optional;
import org.neo4j.ogm.session.Session;

public class SubjectServiceImpl extends Neo4jServiceImpl<Subject> {

    private final Session session;

    protected SubjectServiceImpl(Session session) {
        super(session);
        this.session = session;
    }

    // ensures only one item exists all the time.
    @Override
    public Subject createOrUpdate(Subject entity) {
        Optional<Subject> maybeEntity = findByName(entity.getName());
        if (maybeEntity.isEmpty()) {
            return super.createOrUpdate(entity);
        }
        Subject subject = maybeEntity.get();
        subject.setName(entity.getName());
        return super.createOrUpdate(subject);
    }

//    private Optional<Subject> findByName(String name) {
//        var result = session.query(
//            """
//                MATCH(n: Subject {name: $name}) RETURN n
//                """,
//            Map.of("name", name)
//        );
//        List<Subject> subjects = new ArrayList<>();
//        for (Map<String, Object> next : result) {
//            Set<String> strings = next.keySet();
//            for (String string : strings) {
//                Object o = next.get(string);
//                Subject subject = (Subject) o;
//                subjects.add(subject);
//            }
//        }
//        if (subjects.size() > 0) {
//            return Optional.of(subjects.get(0));
//        }
//        return Optional.empty();
//    }

    @Override
    Class<Subject> getEntityType() {
        return Subject.class;
    }
}
