package org.richard.neo.university;

import org.neo4j.ogm.session.Session;

public class SubjectServiceImpl extends Neo4jServiceImpl<Subject> {

    protected SubjectServiceImpl(Session session) {
        super(session);
    }

    @Override
    Class<Subject> getEntityType() {
        return Subject.class;
    }
}
