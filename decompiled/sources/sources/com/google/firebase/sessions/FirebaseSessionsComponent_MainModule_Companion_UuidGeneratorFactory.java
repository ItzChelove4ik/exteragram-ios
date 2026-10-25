package com.google.firebase.sessions;

import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class FirebaseSessionsComponent_MainModule_Companion_UuidGeneratorFactory implements Factory<UuidGenerator> {

    public static final class InstanceHolder {
        static final FirebaseSessionsComponent_MainModule_Companion_UuidGeneratorFactory INSTANCE = null;

        static {
            INSTANCE = new FirebaseSessionsComponent_MainModule_Companion_UuidGeneratorFactory();
        }
    }

    public FirebaseSessionsComponent_MainModule_Companion_UuidGeneratorFactory() {
    }

    public static FirebaseSessionsComponent_MainModule_Companion_UuidGeneratorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static UuidGenerator uuidGenerator() {
        return (UuidGenerator) Preconditions.checkNotNullFromProvides(FirebaseSessionsComponent.MainModule.Companion.uuidGenerator());
    }

    @Override // javax.inject.Provider
    public UuidGenerator get() {
        return uuidGenerator();
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
