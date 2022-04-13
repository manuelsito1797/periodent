package com.project.domain.gateway;

import java.util.List;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface DsGateway<T> {

    void create(T requestModel);

    T read(int id);

    List<T> readAll();

    void update(T requestModel);

    void delete(int id);
}
