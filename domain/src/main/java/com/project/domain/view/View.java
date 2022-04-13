package com.project.domain.view;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface View<T> {

    void show(T value);

    void showErrorMessage(Throwable throwable);
}
