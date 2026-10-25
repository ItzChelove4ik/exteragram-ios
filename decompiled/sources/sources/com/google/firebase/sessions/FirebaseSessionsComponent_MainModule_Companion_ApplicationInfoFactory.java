package com.google.firebase.sessions;

import com.google.firebase.FirebaseApp;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Preconditions;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class FirebaseSessionsComponent_MainModule_Companion_ApplicationInfoFactory implements Factory<ApplicationInfo> {
    private final Provider<FirebaseApp> firebaseAppProvider;

    private FirebaseSessionsComponent_MainModule_Companion_ApplicationInfoFactory(Provider<FirebaseApp> r1) {
        this.firebaseAppProvider = r1;
    }

    public static ApplicationInfo applicationInfo(FirebaseApp r1) {
        return (ApplicationInfo) Preconditions.checkNotNullFromProvides(FirebaseSessionsComponent.MainModule.Companion.applicationInfo(r1));
    }

    public static FirebaseSessionsComponent_MainModule_Companion_ApplicationInfoFactory create(Provider<FirebaseApp> r1) {
        return new FirebaseSessionsComponent_MainModule_Companion_ApplicationInfoFactory(r1);
    }

    @Override // javax.inject.Provider
    public ApplicationInfo get() {
        return applicationInfo(this.firebaseAppProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
