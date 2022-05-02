package com.project.app.user.viewmodel;

/**
 * @author dhelarius 1/5/2022
 * periodent
 */
@FunctionalInterface
public interface Callback<R> {
    void onPresent(R response, Throwable throwable);
}
