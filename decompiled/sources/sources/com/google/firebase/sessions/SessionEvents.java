package com.google.firebase.sessions;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.google.firebase.FirebaseApp;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.sessions.api.SessionSubscriber;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0013H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001c"}, d2 = {"Lcom/google/firebase/sessions/SessionEvents;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SESSION_EVENT_ENCODER", "Lcom/google/firebase/encoders/DataEncoder;", "getSESSION_EVENT_ENCODER$com_google_firebase_firebase_sessions", "()Lcom/google/firebase/encoders/DataEncoder;", "buildSession", "Lcom/google/firebase/sessions/SessionEvent;", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "sessionDetails", "Lcom/google/firebase/sessions/SessionDetails;", "sessionsSettings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "subscribers", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/sessions/api/SessionSubscriber$Name;", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "firebaseInstallationId", _UrlKt.FRAGMENT_ENCODE_SET, "firebaseAuthenticationToken", "getApplicationInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "toDataCollectionState", "Lcom/google/firebase/sessions/DataCollectionState;", "subscriber", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SessionEvents {
    public static final SessionEvents INSTANCE = null;
    private static final DataEncoder SESSION_EVENT_ENCODER = null;

    static {
        INSTANCE = new SessionEvents();
        SESSION_EVENT_ENCODER = new JsonDataEncoderBuilder().configureWith(AutoSessionEventEncoder.CONFIG).ignoreNullValues(true).build();
    }

    private SessionEvents() {
    }

    private final DataCollectionState toDataCollectionState(SessionSubscriber r1) {
        if (r1 != null) goto L6;
        return DataCollectionState.COLLECTION_SDK_NOT_INSTALLED;
    L6:
        if (r1.isDataCollectionEnabled() == false) goto L10;
        return DataCollectionState.COLLECTION_ENABLED;
    L10:
        return DataCollectionState.COLLECTION_DISABLED;
    }

    public final SessionEvent buildSession(FirebaseApp r14, SessionDetails r15, SessionsSettings r16, Map<SessionSubscriber.Name, ? extends SessionSubscriber> r17, String r18, String r19) {
        return new SessionEvent(EventType.SESSION_START, new SessionInfo(r15.getSessionId(), r15.getFirstSessionId(), r15.getSessionIndex(), r15.getSessionStartTimestampUs(), new DataCollectionStatus(toDataCollectionState(r17.get(SessionSubscriber.Name.PERFORMANCE)), toDataCollectionState(r17.get(SessionSubscriber.Name.CRASHLYTICS)), r16.getSamplingRate()), r18, r19), getApplicationInfo(r14));
    }

    public final ApplicationInfo getApplicationInfo(FirebaseApp r13) {
        Context r12 = r13.getApplicationContext();
        String r1 = r12.getPackageName();
        PackageInfo r122 = r12.getPackageManager().getPackageInfo(r1, 0);
        if (Build.VERSION.SDK_INT < 28) goto L6;
        String r0 = String.valueOf(r122.getLongVersionCode());
    L5:
        String r3 = r0;
        String r8 = r13.getOptions().getApplicationId();
        String r9 = Build.MODEL;
        String r82 = Build.VERSION.RELEASE;
        LogEnvironment r92 = LogEnvironment.LOG_ENVIRONMENT_PROD;
        String r123 = r122.versionName;
        if (r123 != null) goto L10;
        String r2 = r3;
    L11:
        String r4 = Build.MANUFACTURER;
        ProcessDetailsProvider r124 = ProcessDetailsProvider.INSTANCE;
        return new ApplicationInfo(r8, r9, "3.0.7", r82, r92, new AndroidApplicationInfo(r1, r2, r3, r4, r124.getMyProcessDetails(r13.getApplicationContext()), r124.getAppProcessDetails(r13.getApplicationContext())));
    L10:
        r2 = r123;
        goto L11
    L6:
        r0 = String.valueOf(r122.versionCode);
        goto L5
    }

    public final DataEncoder getSESSION_EVENT_ENCODER$com_google_firebase_firebase_sessions() {
        return SESSION_EVENT_ENCODER;
    }
}
