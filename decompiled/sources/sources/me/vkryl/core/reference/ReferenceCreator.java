package me.vkryl.core.reference;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public interface ReferenceCreator<T> {
    default Reference<T> newReference(T r1) {
        return new WeakReference(r1);
    }
}
