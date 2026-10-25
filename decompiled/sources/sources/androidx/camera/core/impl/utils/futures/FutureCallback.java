package androidx.camera.core.impl.utils.futures;

/* loaded from: classes3.dex */
public interface FutureCallback<V> {
    void onFailure(Throwable r1);

    void onSuccess(V r1);
}
