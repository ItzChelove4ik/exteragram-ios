package com.google.firebase.sessions;

import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class FirebaseSessionsComponent_MainModule_Companion_TimeProviderFactory implements Factory<TimeProvider> {

    public static final class InstanceHolder {
        static final FirebaseSessionsComponent_MainModule_Companion_TimeProviderFactory INSTANCE = null;

        static {
            INSTANCE = new FirebaseSessionsComponent_MainModule_Companion_TimeProviderFactory();
        }
    }

    public FirebaseSessionsComponent_MainModule_Companion_TimeProviderFactory() {
    }

    public static FirebaseSessionsComponent_MainModule_Companion_TimeProviderFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static TimeProvider timeProvider() {
        return (TimeProvider) Preconditions.checkNotNullFromProvides(FirebaseSessionsComponent.MainModule.Companion.timeProvider());
    }

    @Override // javax.inject.Provider
    public TimeProvider get() {
        return timeProvider();
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
