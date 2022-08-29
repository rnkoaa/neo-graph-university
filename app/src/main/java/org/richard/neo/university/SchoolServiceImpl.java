package org.richard.neo.university;

import org.neo4j.ogm.session.Session;

public class SchoolServiceImpl extends Neo4jServiceImpl<School> {

    protected SchoolServiceImpl(Session session) {
        super(session);
    }

    @Override
    Class<School> getEntityType() {
        return School.class;
    }
}
