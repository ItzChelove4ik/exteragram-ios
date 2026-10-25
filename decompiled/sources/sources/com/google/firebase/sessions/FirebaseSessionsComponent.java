package com.google.firebase.sessions;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.datastore.DataStoreFile;
import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.DataMigration;
import androidx.datastore.core.DataStore;
import androidx.datastore.core.DataStoreFactory;
import androidx.datastore.core.MultiProcessDataStoreFactory;
import androidx.datastore.core.Serializer;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.settings.SessionConfigs;
import com.google.firebase.sessions.settings.SessionConfigsSerializer;
import j$.io.FileRetargetClass;
import j$.nio.file.Files;
import j$.nio.file.attribute.FileAttribute;
import java.io.File;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okhttp3.internal.url._UrlKt;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\ba\u0018\u00002\u00020\u0001:\u0002\n\u000bR\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/sessions/FirebaseSessions;", "getFirebaseSessions", "()Lcom/google/firebase/sessions/FirebaseSessions;", "firebaseSessions", "Lcom/google/firebase/sessions/SharedSessionRepository;", "getSharedSessionRepository", "()Lcom/google/firebase/sessions/SharedSessionRepository;", "sharedSessionRepository", "Builder", "MainModule", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface FirebaseSessionsComponent {

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H'J\u0012\u0010\u0004\u001a\u00020\u00002\b\b\u0001\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\u0006\u001a\u00020\u00002\b\b\u0001\u0010\u0006\u001a\u00020\u0005H'J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH'J\u0016\u0010\u000b\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH'J\b\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsComponent$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "appContext", "Landroid/content/Context;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "blockingDispatcher", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "firebaseInstallationsApi", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "transportFactoryProvider", "Lcom/google/firebase/inject/Provider;", "Lcom/google/android/datatransport/TransportFactory;", "build", "Lcom/google/firebase/sessions/FirebaseSessionsComponent;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Builder {
        Builder appContext(Context r1);

        Builder backgroundDispatcher(@Background CoroutineContext r1);

        Builder blockingDispatcher(@Blocking CoroutineContext r1);

        FirebaseSessionsComponent build();

        Builder firebaseApp(FirebaseApp r1);

        Builder firebaseInstallationsApi(FirebaseInstallationsApi r1);

        Builder transportFactoryProvider(Provider<TransportFactory> r1);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bg\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsComponent$MainModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface MainModule {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0007J(\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\\\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00180\r\"\u0004\b\u0000\u0010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001c2\u0014\b\u0002\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u001f0\u001e2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020$H\u0002¨\u0006*"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsComponent$MainModule$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "timeProvider", "Lcom/google/firebase/sessions/TimeProvider;", "uuidGenerator", "Lcom/google/firebase/sessions/UuidGenerator;", "applicationInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "sessionConfigsDataStore", "Landroidx/datastore/core/DataStore;", "Lcom/google/firebase/sessions/settings/SessionConfigs;", "appContext", "Landroid/content/Context;", "blockingDispatcher", "Lkotlin/coroutines/CoroutineContext;", "sessionDataStore", "Lcom/google/firebase/sessions/SessionData;", "sessionDataSerializer", "Lcom/google/firebase/sessions/SessionDataSerializer;", "createDataStore", "T", "serializer", "Landroidx/datastore/core/Serializer;", "corruptionHandler", "Landroidx/datastore/core/handlers/ReplaceFileCorruptionHandler;", "migrations", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/core/DataMigration;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "produceFile", "Lkotlin/Function0;", "Ljava/io/File;", "loadDataStoreSharedCounter", _UrlKt.FRAGMENT_ENCODE_SET, "prepDataStoreFile", _UrlKt.FRAGMENT_ENCODE_SET, "dataStoreFile", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = null;

            public static File $r8$lambda$CQa851ZUn6poN7q8ihJBNkhBkqc(Context r1) {
                File r12 = DataStoreFile.dataStoreFile(r1, "firebaseSessions/sessionConfigsDataStore.data");
                $$INSTANCE.prepDataStoreFile(r12);
                return r12;
            }

            /* renamed from: $r8$lambda$HxILT9gq3-17AGuVs0BV9CenvfM, reason: not valid java name */
            public static File m1977$r8$lambda$HxILT9gq317AGuVs0BV9CenvfM(Context r1) {
                File r12 = DataStoreFile.dataStoreFile(r1, "firebaseSessions/sessionDataStore.data");
                $$INSTANCE.prepDataStoreFile(r12);
                return r12;
            }

            /* renamed from: $r8$lambda$PDHpRuMQXkUZ157dV--HswdwpAg, reason: not valid java name */
            public static SessionConfigs m1978$r8$lambda$PDHpRuMQXkUZ157dVHswdwpAg(CorruptionException r2) {
                Log.w("FirebaseSessions", "CorruptionException in session configs DataStore", r2);
                return SessionConfigsSerializer.INSTANCE.getDefaultValue();
            }

            /* renamed from: $r8$lambda$un_PGJosXsgIuP9JGxZjMJM-lHE, reason: not valid java name */
            public static SessionData m1979$r8$lambda$un_PGJosXsgIuP9JGxZjMJMlHE(SessionDataSerializer r2, CorruptionException r3) {
                Log.w("FirebaseSessions", "CorruptionException in session data DataStore", r3);
                return r2.getDefaultValue();
            }

            static {
                $$INSTANCE = new Companion();
            }

            private Companion() {
            }

            private final <T> DataStore<T> createDataStore(Serializer<T> r7, ReplaceFileCorruptionHandler<T> r8, List<? extends DataMigration<T>> r9, CoroutineScope r10, Function0<? extends File> r11) {
                if (loadDataStoreSharedCounter() == false) goto L7;
                return MultiProcessDataStoreFactory.INSTANCE.create(r7, r8, r9, r10, r11);
            L7:
                return DataStoreFactory.INSTANCE.create(r7, r8, r9, r10, r11);
            }

            public static /* synthetic */ DataStore createDataStore$default(Companion r6, Serializer r7, ReplaceFileCorruptionHandler r8, List r9, CoroutineScope r10, Function0 r11, int r12, Object r13) {
                if ((r12 & 4) == 0) goto L6;
                r9 = CollectionsKt.emptyList();
            L6:
                return r6.createDataStore(r7, r8, r9, r10, r11);
            }

            private final boolean loadDataStoreSharedCounter() {
                System.loadLibrary("datastore_shared_counter");     // Catch: Throwable -> L5
                return true;
            L5:
                return false;
            }

            private final void prepDataStoreFile(File r4) {
                File r3 = r4.getParentFile();
                if (r3 != null) goto L6;
                return;
            L6:
                if (r3.exists() == false) goto L17;
                if (r3.isDirectory() == true) goto L17;
                if (Intrinsics.areEqual(r3.getName(), "firebaseSessions") == false) goto L17;
                if (r3.delete() == true) goto L17;
                ZipFileSystem$$ExternalSyntheticBUOutline0.m("Failed to delete conflicting file: ", r3);
                return;
            L17:
                if (r3.isDirectory() == false) goto L20;
                return;
            L20:
                if (Build.VERSION.SDK_INT < 26) goto L27;
                Files.createDirectories(FileRetargetClass.toPath(r3), new FileAttribute[0]);     // Catch: Exception -> L23
                return;
            L23:
                e = move-exception;
                throw new IOException("Failed to create directory: " + r3, e);
            L27:
                if (r3.mkdirs() == false) goto L29;
                return;
            L29:
                if (r3.isDirectory() == true) goto L38;
                ZipFileSystem$$ExternalSyntheticBUOutline0.m("Failed to create directory: ", r3);
                return;
            }

            public final ApplicationInfo applicationInfo(FirebaseApp r1) {
                return SessionEvents.INSTANCE.getApplicationInfo(r1);
            }

            public final DataStore<SessionConfigs> sessionConfigsDataStore(Context r9, @Blocking CoroutineContext r10) {
                return createDataStore$default(this, SessionConfigsSerializer.INSTANCE, new ReplaceFileCorruptionHandler(new FirebaseSessionsComponent$MainModule$Companion$$ExternalSyntheticLambda0()), null, CoroutineScopeKt.CoroutineScope(r10), new FirebaseSessionsComponent$MainModule$Companion$$ExternalSyntheticLambda1(r9), 4, null);
            }

            public final DataStore<SessionData> sessionDataStore(Context r9, @Blocking CoroutineContext r10, SessionDataSerializer r11) {
                return createDataStore$default(this, r11, new ReplaceFileCorruptionHandler(new FirebaseSessionsComponent$MainModule$Companion$$ExternalSyntheticLambda2(r11)), null, CoroutineScopeKt.CoroutineScope(r10), new FirebaseSessionsComponent$MainModule$Companion$$ExternalSyntheticLambda3(r9), 4, null);
            }

            public final TimeProvider timeProvider() {
                return TimeProviderImpl.INSTANCE;
            }

            public final UuidGenerator uuidGenerator() {
                return UuidGeneratorImpl.INSTANCE;
            }
        }

        static {
            Companion = Companion.$$INSTANCE;
        }
    }

    FirebaseSessions getFirebaseSessions();

    SharedSessionRepository getSharedSessionRepository();
}
