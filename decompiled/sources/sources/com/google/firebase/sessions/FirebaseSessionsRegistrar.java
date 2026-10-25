package com.google.firebase.sessions;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.datastore.core.MultiProcessDataStoreFactory;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.internal.url._UrlKt;

@Keep
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a0\u0012,\u0012*\u0012\u000e\b\u0001\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0014\u0012\u000e\b\u0001\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u00060\u00060\u0005H\u0016¨\u0006\n"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar;", "Lcom/google/firebase/components/ComponentRegistrar;", "<init>", "()V", "getComponents", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/components/Component;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FirebaseSessionsRegistrar implements ComponentRegistrar {
    private static final Companion Companion = null;

    @Deprecated
    public static final String LIBRARY_NAME = "fire-sessions";
    private static final Qualified<Context> appContext = null;
    private static final Qualified<CoroutineDispatcher> backgroundDispatcher = null;
    private static final Qualified<CoroutineDispatcher> blockingDispatcher = null;
    private static final Qualified<FirebaseApp> firebaseApp = null;
    private static final Qualified<FirebaseInstallationsApi> firebaseInstallationsApi = null;
    private static final Qualified<FirebaseSessionsComponent> firebaseSessionsComponent = null;
    private static final Qualified<TransportFactory> transportFactory = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "LIBRARY_NAME", "Ljava/lang/String;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static FirebaseSessionsComponent $r8$lambda$5nhYzR4AQ6vcZtGeFQH3WHqD5EY(ComponentContainer r2) {
        return DaggerFirebaseSessionsComponent.builder().appContext((Context) r2.get(appContext)).backgroundDispatcher((CoroutineContext) r2.get(backgroundDispatcher)).blockingDispatcher((CoroutineContext) r2.get(blockingDispatcher)).firebaseApp((FirebaseApp) r2.get(firebaseApp)).firebaseInstallationsApi((FirebaseInstallationsApi) r2.get(firebaseInstallationsApi)).transportFactoryProvider(r2.getProvider(transportFactory)).build();
    }

    /* renamed from: $r8$lambda$r7-PKYXSbWzr9mCtWMP1_jMCi4s, reason: not valid java name */
    public static FirebaseSessions m1980$r8$lambda$r7PKYXSbWzr9mCtWMP1_jMCi4s(ComponentContainer r1) {
        return ((FirebaseSessionsComponent) r1.get(firebaseSessionsComponent)).getFirebaseSessions();
    }

    static {
        Companion = new Companion(null);
        appContext = Qualified.unqualified(Context.class);
        firebaseApp = Qualified.unqualified(FirebaseApp.class);
        firebaseInstallationsApi = Qualified.unqualified(FirebaseInstallationsApi.class);
        backgroundDispatcher = Qualified.qualified(Background.class, CoroutineDispatcher.class);
        blockingDispatcher = Qualified.qualified(Blocking.class, CoroutineDispatcher.class);
        transportFactory = Qualified.unqualified(TransportFactory.class);
        firebaseSessionsComponent = Qualified.unqualified(FirebaseSessionsComponent.class);
        MultiProcessDataStoreFactory.INSTANCE.getClass();     // Catch: NoClassDefFoundError -> L5
        return;
    L5:
        Log.w("FirebaseSessions", "Your app is experiencing a known issue in the Android Gradle plugin, see https://issuetracker.google.com/328687152\n\nIt affects Java-only apps using AGP version 8.3.2 and under. To avoid the issue, either:\n\n1. Upgrade Android Gradle plugin to 8.4.0+\n   Follow the guide at https://developer.android.com/build/agp-upgrade-assistant\n\n2. Or, add the Kotlin plugin to your app\n   Follow the guide at https://developer.android.com/kotlin/add-kotlin\n\n3. Or, do the technical workaround described in https://issuetracker.google.com/issues/328687152#comment3");
    }

    public FirebaseSessionsRegistrar() {
    }

    public static final /* synthetic */ Qualified access$getAppContext$cp() {
        return appContext;
    }

    public static final /* synthetic */ Qualified access$getBackgroundDispatcher$cp() {
        return backgroundDispatcher;
    }

    public static final /* synthetic */ Qualified access$getBlockingDispatcher$cp() {
        return blockingDispatcher;
    }

    public static final /* synthetic */ Qualified access$getFirebaseApp$cp() {
        return firebaseApp;
    }

    public static final /* synthetic */ Qualified access$getFirebaseInstallationsApi$cp() {
        return firebaseInstallationsApi;
    }

    public static final /* synthetic */ Qualified access$getFirebaseSessionsComponent$cp() {
        return firebaseSessionsComponent;
    }

    public static final /* synthetic */ Qualified access$getTransportFactory$cp() {
        return transportFactory;
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<? extends Object>> getComponents() {
        return CollectionsKt.listOf(new Component[]{Component.builder(FirebaseSessions.class).name(LIBRARY_NAME).add(Dependency.required(firebaseSessionsComponent)).factory(new FirebaseSessionsRegistrar$$ExternalSyntheticLambda0()).eagerInDefaultApp().build(), Component.builder(FirebaseSessionsComponent.class).name("fire-sessions-component").add(Dependency.required(appContext)).add(Dependency.required(backgroundDispatcher)).add(Dependency.required(blockingDispatcher)).add(Dependency.required(firebaseApp)).add(Dependency.required(firebaseInstallationsApi)).add(Dependency.requiredProvider(transportFactory)).factory(new FirebaseSessionsRegistrar$$ExternalSyntheticLambda1()).build(), LibraryVersionComponent.create(LIBRARY_NAME, "3.0.7")});
    }
}
