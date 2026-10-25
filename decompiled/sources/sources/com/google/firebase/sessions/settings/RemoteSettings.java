package com.google.firebase.sessions.settings;

import android.os.Build;
import android.util.Log;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.ApplicationInfo;
import com.google.firebase.sessions.InstallationId;
import com.google.firebase.sessions.TimeProvider;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\b\u0001\u0018\u0000 )2\u00020\u0001:\u0001)B1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012H\u0096@¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0017R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0018R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010 \u001a\u0004\u0018\u00010\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010$\u001a\u0004\u0018\u00010!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0016\u0010(\u001a\u0004\u0018\u00010%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006*"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettings;", "Lcom/google/firebase/sessions/settings/SettingsProvider;", "Lcom/google/firebase/sessions/TimeProvider;", "timeProvider", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "firebaseInstallationsApi", "Lcom/google/firebase/sessions/ApplicationInfo;", "appInfo", "Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;", "configsFetcher", "Lcom/google/firebase/sessions/settings/SettingsCache;", "settingsCache", "<init>", "(Lcom/google/firebase/sessions/TimeProvider;Lcom/google/firebase/installations/FirebaseInstallationsApi;Lcom/google/firebase/sessions/ApplicationInfo;Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;Lcom/google/firebase/sessions/settings/SettingsCache;)V", _UrlKt.FRAGMENT_ENCODE_SET, "s", "sanitize", "(Ljava/lang/String;)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "updateSettings", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/google/firebase/sessions/TimeProvider;", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "Lcom/google/firebase/sessions/ApplicationInfo;", "Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;", "Lcom/google/firebase/sessions/settings/SettingsCache;", "Lkotlinx/coroutines/sync/Mutex;", "fetchInProgress", "Lkotlinx/coroutines/sync/Mutex;", _UrlKt.FRAGMENT_ENCODE_SET, "getSessionEnabled", "()Ljava/lang/Boolean;", "sessionEnabled", "Lkotlin/time/Duration;", "getSessionRestartTimeout-FghU774", "()Lkotlin/time/Duration;", "sessionRestartTimeout", _UrlKt.FRAGMENT_ENCODE_SET, "getSamplingRate", "()Ljava/lang/Double;", "samplingRate", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRemoteSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RemoteSettings.kt\ncom/google/firebase/sessions/settings/RemoteSettings\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,159:1\n116#2,11:160\n*S KotlinDebug\n*F\n+ 1 RemoteSettings.kt\ncom/google/firebase/sessions/settings/RemoteSettings\n*L\n70#1:160,11\n*E\n"})
/* loaded from: classes.dex */
public final class RemoteSettings implements SettingsProvider {
    private static final Companion Companion = null;
    private static final int defaultCacheDuration = 0;
    private static final Regex sanitizeRegex = null;
    private final ApplicationInfo appInfo;
    private final CrashlyticsSettingsFetcher configsFetcher;
    private final Mutex fetchInProgress;
    private final FirebaseInstallationsApi firebaseInstallationsApi;
    private final SettingsCache settingsCache;
    private final TimeProvider timeProvider;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettings$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "defaultCacheDuration", "I", "getDefaultCacheDuration", "()I", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final int getDefaultCacheDuration() {
            return RemoteSettings.access$getDefaultCacheDuration$cp();
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettings", f = "RemoteSettings.kt", i = {0, 1, 2}, l = {165, 78, 95}, m = "updateSettings", n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RemoteSettings this$0;

        public AnonymousClass1(RemoteSettings r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.updateSettings(this);
        }
    }

    static {
        Companion = new Companion(null);
        Duration.Companion r0 = Duration.Companion;
        defaultCacheDuration = (int) Duration.m3389getInWholeSecondsimpl(DurationKt.toDuration(24, DurationUnit.HOURS));
        sanitizeRegex = new Regex("com/google/firebase/sessions//");
    }

    public RemoteSettings(TimeProvider r1, FirebaseInstallationsApi r2, ApplicationInfo r3, CrashlyticsSettingsFetcher r4, SettingsCache r5) {
        this.timeProvider = r1;
        this.firebaseInstallationsApi = r2;
        this.appInfo = r3;
        this.configsFetcher = r4;
        this.settingsCache = r5;
        this.fetchInProgress = MutexKt.Mutex$default(false, 1, null);
    }

    public static final /* synthetic */ Companion access$getCompanion$p() {
        return Companion;
    }

    public static final /* synthetic */ int access$getDefaultCacheDuration$cp() {
        return defaultCacheDuration;
    }

    public static final /* synthetic */ SettingsCache access$getSettingsCache$p(RemoteSettings r0) {
        return r0.settingsCache;
    }

    public static final /* synthetic */ TimeProvider access$getTimeProvider$p(RemoteSettings r0) {
        return r0.timeProvider;
    }

    private final String sanitize(String r2) {
        return sanitizeRegex.replace(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    @Override // com.google.firebase.sessions.settings.SettingsProvider
    public Double getSamplingRate() {
        return this.settingsCache.sessionSamplingRate();
    }

    @Override // com.google.firebase.sessions.settings.SettingsProvider
    public Boolean getSessionEnabled() {
        return this.settingsCache.sessionsEnabled();
    }

    @Override // com.google.firebase.sessions.settings.SettingsProvider
    /* renamed from: getSessionRestartTimeout-FghU774 */
    public Duration mo1985getSessionRestartTimeoutFghU774() {
        Integer r2 = this.settingsCache.sessionRestartTimeout();
        if (r2 == null) goto L6;
        Duration.Companion r0 = Duration.Companion;
        return Duration.m3373boximpl(DurationKt.toDuration(r2.intValue(), DurationUnit.SECONDS));
    L6:
        return null;
    }

    @Override // com.google.firebase.sessions.settings.SettingsProvider
    public Object updateSettings(Continuation<? super Unit> r13) {
        if ((r13 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r13;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r132 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L26;
        if (r2 == 1) goto L25;
        if (r2 == 2) goto L20;
        if (r2 != 3) goto L18;
        Mutex r122 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r132);     // Catch: Throwable -> L16
    L54:
        Unit r133 = Unit.INSTANCE;     // Catch: Throwable -> L16
        r122.unlock(null);
        return Unit.INSTANCE;
    L16:
        Throwable th = th;
    L57:
        r122.unlock(null);
        throw th;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        Mutex r22 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r132);     // Catch: Throwable -> L23
        Object r23 = r132;
        Mutex r134 = r22;
    L45:
        String r24 = ((InstallationId) r23).getFid();     // Catch: Throwable -> L40
        if (Intrinsics.areEqual(r24, _UrlKt.FRAGMENT_ENCODE_SET) == false) goto L50;
        Log.w("FirebaseSessions", "Error getting Firebase Installation ID. Skipping this Session Event.");     // Catch: Throwable -> L40
        Unit r123 = Unit.INSTANCE;     // Catch: Throwable -> L40
        r134.unlock(null);
        return r123;
    L50:
        Map<String, String> r25 = MapsKt.mapOf(new Pair[]{TuplesKt.to("X-Crashlytics-Installation-ID", r24), TuplesKt.to("X-Crashlytics-Device-Model", sanitize(Build.MANUFACTURER + Build.MODEL)), TuplesKt.to("X-Crashlytics-OS-Build-Version", sanitize(Build.VERSION.INCREMENTAL)), TuplesKt.to("X-Crashlytics-OS-Display-Version", sanitize(Build.VERSION.RELEASE)), TuplesKt.to("X-Crashlytics-API-Client-Version", this.appInfo.getSessionSdkVersion())});     // Catch: Throwable -> L40
        Log.d("FirebaseSessions", "Fetching settings from server.");     // Catch: Throwable -> L40
        CrashlyticsSettingsFetcher r4 = this.configsFetcher;     // Catch: Throwable -> L40
        RemoteSettings$updateSettings$2$1 r5 = new RemoteSettings$updateSettings$2$1(this, null);     // Catch: Throwable -> L40
        RemoteSettings$updateSettings$2$2 r124 = new RemoteSettings$updateSettings$2$2(null);     // Catch: Throwable -> L40
        r0.L$0 = r134;     // Catch: Throwable -> L40
        r0.label = 3;     // Catch: Throwable -> L40
        if (r4.doConfigFetch(r25, r5, r124, r0) != r12) goto L53;
    L52:
        return r12;
    L53:
        r122 = r134;
    L23:
        th = th;
        r122 = r22;
        goto L57
    L25:
        Mutex r26 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r132);
        r134 = r26;
    L63:
    L40:
        th = move-exception;
        Mutex r11 = r134;
        th = th;
        r122 = r11;
        goto L57
    L36:
        if (this.settingsCache.hasCacheExpired() == true) goto L42;
        Log.d("FirebaseSessions", "Remote settings cache not expired. Using cached values.");     // Catch: Throwable -> L40
        Unit r125 = Unit.INSTANCE;     // Catch: Throwable -> L40
        r134.unlock(null);
        return r125;
    L42:
        InstallationId.Companion r27 = InstallationId.Companion;     // Catch: Throwable -> L40
        FirebaseInstallationsApi r52 = this.firebaseInstallationsApi;     // Catch: Throwable -> L40
        r0.L$0 = r134;     // Catch: Throwable -> L40
        r0.label = 2;     // Catch: Throwable -> L40
        r23 = r27.create(r52, r0);     // Catch: Throwable -> L40
        if (r23 != r12) goto L45;
    L26:
        ResultKt.throwOnFailure(r132);
        if (this.fetchInProgress.isLocked() == false) goto L29;
    L32:
        r134 = this.fetchInProgress;
        r0.L$0 = r134;
        r0.label = 1;
        if (r134.lock(null, r0) != r12) goto L63;
    L29:
        if (this.settingsCache.hasCacheExpired() == true) goto L32;
        return Unit.INSTANCE;
    L7:
        r0 = new AnonymousClass1(this, r13);
        goto L8
    }
}
