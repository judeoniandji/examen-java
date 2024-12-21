package com.commande.services;

import com.commande.repositories.BaseRepository;

import java.util.List;

public abstract class BaseService<T> {
    protected final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void update(T entity) {
        repository.update(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public T findById(Long id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }
}
