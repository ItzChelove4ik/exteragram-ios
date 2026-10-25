package androidx.camera.core.impl;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public interface Observable<T> {

    public interface Observer<T> {
        void onError(Throwable r1);

        void onNewData(T r1);
    }

    void addObserver(Executor r1, Observer<? super T> r2);

    ListenableFuture<T> fetchData();

    void removeObserver(Observer<? super T> r1);
}
