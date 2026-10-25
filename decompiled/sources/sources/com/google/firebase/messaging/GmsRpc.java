package com.google.firebase.messaging;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.Rpc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.concurrent.ExecutionException;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
class GmsRpc {
    private final FirebaseApp app;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Provider<HeartBeatInfo> heartbeatInfo;
    private final Metadata metadata;
    private final Rpc rpc;
    private final Provider<UserAgentPublisher> userAgentPublisher;

    /* renamed from: $r8$lambda$2BiID9R3pQ-goZ0D5C67gHAjWVU, reason: not valid java name */
    public static /* synthetic */ String m1966$r8$lambda$2BiID9R3pQgoZ0D5C67gHAjWVU(GmsRpc r1, Task r2) {
        r1.getClass();
        return r1.handleResponse((Bundle) r2.getResult(IOException.class));
    }

    public GmsRpc(FirebaseApp r1, Metadata r2, Rpc r3, Provider<UserAgentPublisher> r4, Provider<HeartBeatInfo> r5, FirebaseInstallationsApi r6) {
        this.app = r1;
        this.metadata = r2;
        this.rpc = r3;
        this.userAgentPublisher = r4;
        this.heartbeatInfo = r5;
        this.firebaseInstallations = r6;
    }

    public GmsRpc(FirebaseApp r8, Metadata r9, Provider<UserAgentPublisher> r10, Provider<HeartBeatInfo> r11, FirebaseInstallationsApi r12) {
        this(r8, r9, new Rpc(r8.getApplicationContext()), r10, r11, r12);
    }

    private static String base64UrlSafe(byte[] r1) {
        return Base64.encodeToString(r1, 11);
    }

    private Task<String> extractResponseWhenComplete(Task<Bundle> r3) {
        return r3.continueWith(new CredentialManager$$ExternalSyntheticLambda0(), new GmsRpc$$ExternalSyntheticLambda0(this));
    }

    private String getHashedFirebaseAppName() {
        String r1 = this.app.getName();
        return base64UrlSafe(MessageDigest.getInstance("SHA-1").digest(r1.getBytes()));
    L5:
        return "[HASH-ERROR]";
    }

    private String handleResponse(Bundle r4) {
        if (r4 == null) goto L19;
        String r1 = r4.getString("registration_id");
        if (r1 == null) goto L7;
        return r1;
    L7:
        String r12 = r4.getString("unregistered");
        if (r12 == null) goto L10;
        return r12;
    L10:
        String r13 = r4.getString("error");
        if ("RST".equals(r13) == true) goto L17;
        if (r13 == null) goto L15;
        Model$$ExternalSyntheticBUOutline0.m(r13);
        return null;
    L15:
        Log.w("FirebaseMessaging", "Unexpected response: " + r4, new Throwable());
        Model$$ExternalSyntheticBUOutline0.m("SERVICE_NOT_AVAILABLE");
        return null;
    L17:
        Model$$ExternalSyntheticBUOutline0.m("INSTANCE_ID_RESET");
        return null;
    L19:
        Model$$ExternalSyntheticBUOutline0.m("SERVICE_NOT_AVAILABLE");
        return null;
    }

    public static boolean isErrorMessageForRetryableError(String r1) {
        if ("SERVICE_NOT_AVAILABLE".equals(r1) == false) goto L5;
        return true;
    L5:
        if ("INTERNAL_SERVER_ERROR".equals(r1) == false) goto L7;
        return true;
    L7:
        if ("InternalServerError".equals(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private void setDefaultAttributesToBundle(String r3, String r4, Bundle r5, boolean r6) {
        r5.putString("scope", r4);
        r5.putString("sender", r3);
        r5.putString("subtype", r3);
        r5.putString("gmp_app_id", this.app.getOptions().getApplicationId());
        r5.putString("gmsv", Integer.toString(this.metadata.getGmsVersionCode()));
        r5.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        r5.putString("app_ver", this.metadata.getAppVersionCode());
        r5.putString("app_ver_name", this.metadata.getAppVersionName());
        r5.putString("firebase-app-name-hash", getHashedFirebaseAppName());
        if (r6 == false) goto L22;
        r5.putString("Goog-Api-Key", this.app.getOptions().getApiKey());
    L22:
        String r32 = ((InstallationTokenResult) Tasks.await(this.firebaseInstallations.getToken(false))).getToken();     // Catch: InterruptedException -> L8 ExecutionException -> L10
        if (TextUtils.isEmpty(r32) == true) goto L12;
        r5.putString("Goog-Firebase-Installations-Auth", r32);     // Catch: InterruptedException -> L8 ExecutionException -> L10
    L15:
        r5.putString("appid", (String) Tasks.await(this.firebaseInstallations.getId()));
        r5.putString("cliv", "fcm-25.1.2");
        HeartBeatInfo r33 = this.heartbeatInfo.get();
        UserAgentPublisher r2 = this.userAgentPublisher.get();
        if (r33 == null) goto L23;
        if (r2 == null) goto L24;
        HeartBeatInfo.HeartBeat r34 = r33.getHeartBeatCode("fire-iid");
        if (r34 == HeartBeatInfo.HeartBeat.NONE) goto L25;
        r5.putString("Firebase-Client-Log-Type", Integer.toString(r34.getCode()));
        r5.putString("Firebase-Client", r2.getUserAgent());
        return;
    L25:
        return;
    L24:
        return;
    L23:
        return;
    L12:
        Log.w("FirebaseMessaging", "FIS auth token is empty");     // Catch: InterruptedException -> L8 ExecutionException -> L10
    L8:
        e = e;
    L14:
        Log.e("FirebaseMessaging", "Failed to get FIS auth token", e);
    L10:
        e = e;
        goto L14
    }

    private Task<Bundle> startRpc(String r1, String r2, Bundle r3, boolean r4) {
        setDefaultAttributesToBundle(r1, r2, r3, r4);     // Catch: Throwable -> L5
        return this.rpc.send(r3);
    L5:
        e = move-exception;
        return Tasks.forException(e);
    }

    public Task<CloudMessage> getProxyNotificationData() {
        return this.rpc.getProxiedNotificationData();
    }

    public Task<String> getToken(boolean r4) {
        return extractResponseWhenComplete(startRpc(Metadata.getDefaultSenderId(this.app), "*", new Bundle(), r4));
    }

    public Task<Void> setRetainProxiedNotifications(boolean r1) {
        return this.rpc.setRetainProxiedNotifications(r1);
    }
}
