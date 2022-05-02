package com.project.domain.view;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public interface UseCase<R> {

    interface Callback<R> {
        void show(Presenter<R> presenter);
    }

    void execute(Presenter<R> presenter);
}
