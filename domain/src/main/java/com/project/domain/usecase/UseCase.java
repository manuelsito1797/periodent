package com.project.domain.usecase;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface UseCase<R> {

    interface Callback<R> {

        void onSuccess(R result);

        void onError(Throwable throwable);
    }

    void execute(Callback<R> callback);
}
