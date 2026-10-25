package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public class MutableStateObservable<T> extends StateObservable<T> {
    private MutableStateObservable(Object r1, boolean r2) {
        super(r1, r2);
    }

    public static <T> MutableStateObservable<T> withInitialState(T r2) {
        return new MutableStateObservable(r2, false);
    }

    public void setState(T r1) {
        updateState(r1);
    }
}
