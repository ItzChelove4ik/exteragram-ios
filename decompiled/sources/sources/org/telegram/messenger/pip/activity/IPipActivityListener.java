package org.telegram.messenger.pip.activity;

/* loaded from: classes.dex */
public interface IPipActivityListener {
    default void onCompleteEnterToPip() {
    }

    void onCompleteExitFromPip(boolean r1);

    default void onPipStashEnd() {
    }

    default void onPipStashStart() {
    }

    default void onStartEnterToPip() {
    }

    default void onStartExitFromPip(boolean r1) {
    }
}
