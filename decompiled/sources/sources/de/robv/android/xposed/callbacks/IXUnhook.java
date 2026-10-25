package de.robv.android.xposed.callbacks;

/* loaded from: classes.dex */
public interface IXUnhook<T> {
    T getCallback();

    void unhook();
}
