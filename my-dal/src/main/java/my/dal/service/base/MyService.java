package my.dal.service.base;

import java.util.List;

import javax.validation.constraints.NotNull;

import my.com.common.scalar.base.BaseEntity;

/**
 * MyService
 */
public interface MyService<T extends BaseEntity> {

    public T save(@NotNull T item);
    public T delete(@NotNull T item);
    public T getByPk(@NotNull Long pk);
    public List<T> getAll();
    
}