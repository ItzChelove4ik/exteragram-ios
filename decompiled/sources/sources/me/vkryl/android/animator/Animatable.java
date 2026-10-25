package me.vkryl.android.animator;

/* loaded from: classes.dex */
public interface Animatable {
    boolean applyAnimation(float r1);

    default void applyChanges() {
    }

    void finishAnimation(boolean r1);

    default boolean hasChanges() {
        return false;
    }

    default void prepareChanges() {
    }
}
