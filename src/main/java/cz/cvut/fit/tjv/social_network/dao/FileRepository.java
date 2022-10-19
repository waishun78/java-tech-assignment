/*
 * Copyright (c) 2022. Czech Technical University in Prague, Faculty of Information Technology.
 *
 * Project Social Network. Created for Java Technology course.
 *
 *  Authors:
 *  Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *  Jan Blizničenko (jan.bliznicenko@fit.cvut.cz)
 *  Filip Glazar (glazafil@fit.cvut.cz)
 *
 *  This code is intended for educational purposes only.
 *
 */

package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.DomainEntity;
import cz.cvut.fit.tjv.social_network.domain.GenerateValueWhenSave;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Data layer with file-based storage, supports CRUD.
 * @param <T> entity type
 * @param <ID> primary key type
 */
public abstract class FileRepository<T extends DomainEntity<ID>, ID> implements CrudRepository<T, ID> {
    private final Path storage;
    private final transient Random rnd = new Random();
    private final Class<T> entityType;

    protected FileRepository(Class<T> entityType, Path storage) {
        this.entityType = entityType;
        this.storage = storage;
    }

    public T save(T entity) {
        try {
            var data = loadData();
            data.remove(entity);
            for (var m : entity.getClass().getMethods()) {
                if (m.getAnnotation(GenerateValueWhenSave.class) != null && m.getName().startsWith("set")) {
                    try {
                        m.invoke(entity, rnd.nextLong()); //UUID suits better the purpose; however, it would complicate integration with SQL DB
                    } catch (IllegalAccessException|IllegalArgumentException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
            data.add(entity);
            System.out.println("data");
            System.out.println(data);
            saveData(data);
            return entity;
        }  catch (final IOException e) {
            throw new RuntimeException("Error when working with file storage", e);
        } catch (final ClassNotFoundException|ClassCastException e) {
            throw new RuntimeException("File storage does not contain valid data stored by this application", e);
        }
    }

    public boolean existsById(ID id) {
        return findById(id).isPresent();
    }

    /**
     * Find the entity by the primary key value. If the storage contains multiple distinct entities with the same primary key value, one of them is selected randomly.
     * @param id primary key value
     * @return an entity with given primary key or an empty Optional
     */
    public Optional<T> findById(ID id) {
        return findAll().stream().filter(i -> i.getId().equals(id)).findAny();
    }

    /**
     * @return a collection of entities loaded from file storage with type assignable to T
     */
    public Collection<T> findAll() {
        try {
            return loadData();
        }  catch (final IOException e) {
            throw new RuntimeException("Error when working with file storage", e);
        } catch (final ClassNotFoundException|ClassCastException e) {
            throw new RuntimeException("File storage does not contain valid data stored by this application", e);
        }
    }

    public void deleteById(ID id) {
        try {
            var data = findAll();
            data.removeIf(i -> i.getId().equals(id));
            saveData(data);
        } catch (final IOException e) {
            throw new RuntimeException("Error when working with file storage", e);
        } catch (final ClassNotFoundException|ClassCastException e) {
            throw new RuntimeException("File storage does not contain valid data stored by this application", e);
        }
    }

    private Set<T> loadData() throws IOException, ClassNotFoundException, ClassCastException {
            return loadAllData().stream()
                    .filter(i -> entityType.isAssignableFrom(i.getClass()))
                    .map(i -> (T)i)
                    .collect(Collectors.toSet());
    }

    private Collection<?> loadAllData() throws IOException, ClassNotFoundException, ClassCastException {
        try (final ObjectInputStream inStream = new ObjectInputStream(Files.newInputStream(storage))) {
            var readData = inStream.readObject();
            Collection<? extends DomainEntity> data = (Collection<? extends DomainEntity>) readData;
            return data;
        } catch (NoSuchFileException | EOFException e) {
            return new HashSet<>();
        }
    }

    private void saveData(Collection data) throws IOException, ClassNotFoundException, ClassCastException {
        var loadedData = loadAllData();
        try (final ObjectOutputStream outStream = new ObjectOutputStream(Files.newOutputStream(storage))) {
            loadedData.addAll(data);
            outStream.writeObject(loadedData);
        }
    }
}
