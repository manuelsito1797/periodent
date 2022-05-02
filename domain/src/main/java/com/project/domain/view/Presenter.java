package com.project.domain.view;

/**
 * @author dhelarius 25/4/2022
 * periodent
 */
@FunctionalInterface
public interface Presenter<R> {
    void onResponse(R response, Throwable throwable);
}
