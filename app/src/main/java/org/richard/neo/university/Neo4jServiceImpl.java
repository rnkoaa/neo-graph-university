package org.richard.neo.university;

import java.util.Map;
import java.util.Optional;
import org.neo4j.ogm.session.Session;;

public abstract class Neo4jServiceImpl<T extends Entity & Nameable> extends Neo4jService<T> {

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
    private final Session session;

    protected Neo4jServiceImpl(Session session) {
        this.session = session;
    }

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }

    @Override
    public T createOrUpdate(T entity) {
        Optional<T> byName = findByName(entity.getName());
        if (byName.isEmpty()) {
            session.save(entity, DEPTH_ENTITY);
            return find(entity.getId());
        }
        T value = byName.get();
        session.save(value, DEPTH_ENTITY);
        return value;
    }

    @Override
    public Optional<T> findByName(String name) {
        String typeName = getEntityType().getSimpleName();
        T result = session.queryForObject(getEntityType(),
            "MATCH (n: " + typeName + " {name: $name}) RETURN n",
            Map.of(
                "name", name
            )
        );
        return Optional.ofNullable(result);
    }

    abstract Class<T> getEntityType();
}
