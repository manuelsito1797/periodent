package com.project.domain.usecase;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface UseCaseWithParam<P, R> {

    interface Callback<R> {

        void onSuccess(R result);

        void onError(Throwable throwable);
    }

    void execute(P param, Callback<R> callback);
}
