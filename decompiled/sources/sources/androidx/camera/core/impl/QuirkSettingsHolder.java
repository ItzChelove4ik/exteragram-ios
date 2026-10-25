package androidx.camera.core.impl;

import androidx.camera.core.Logger;
import androidx.camera.core.impl.Observable;
import androidx.core.util.Consumer;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final class QuirkSettingsHolder {
    public static final QuirkSettings DEFAULT = null;
    private static final QuirkSettingsHolder sInstance = null;
    private final MutableStateObservable<QuirkSettings> mObservable;

    public static class ObserverToConsumerAdapter<T> implements Observable.Observer<T> {
        private final Consumer<T> mDelegate;

        public ObserverToConsumerAdapter(Consumer<T> r1) {
            this.mDelegate = r1;
        }

        @Override // androidx.camera.core.impl.Observable.Observer
        public void onError(Throwable r2) {
            Logger.e("ObserverToConsumerAdapter", "Unexpected error in Observable", r2);
        }

        @Override // androidx.camera.core.impl.Observable.Observer
        public void onNewData(T r1) {
            this.mDelegate.accept(r1);
        }
    }

    static {
        DEFAULT = QuirkSettings.withDefaultBehavior();
        sInstance = new QuirkSettingsHolder();
    }

    public QuirkSettingsHolder() {
        this.mObservable = MutableStateObservable.withInitialState(DEFAULT);
    }

    public static QuirkSettingsHolder instance() {
        return sInstance;
    }

    public QuirkSettings get() {
        return this.mObservable.fetchData().get();
    L4:
        e = move-exception;
        throw new AssertionError("Unexpected error in QuirkSettings StateObservable", e);
    }

    public void observe(Executor r2, Consumer<QuirkSettings> r3) {
        this.mObservable.addObserver(r2, new ObserverToConsumerAdapter(r3));
    }

    public void set(QuirkSettings r1) {
        this.mObservable.setState(r1);
    }
}
