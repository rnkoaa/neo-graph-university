package org.richard.neo.university;

import org.neo4j.ogm.session.Session;

public class TeacherServiceImpl extends Neo4jServiceImpl<Teacher> {

    protected TeacherServiceImpl(Session session) {
        super(session);
    }

    @Override
    Class<Teacher> getEntityType() {
        return Teacher.class;
    }
}
