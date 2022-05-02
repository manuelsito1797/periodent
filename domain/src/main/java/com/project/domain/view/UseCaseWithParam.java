package com.project.domain.view;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public interface UseCaseWithParam<P,R> {
    interface Callback<P,R> {
        void show(P param, Presenter<R> presenter);
    }

    void execute(P param, Presenter<R> presenter);
}
