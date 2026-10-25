package com.google.firebase.messaging;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.cloudmessaging.CloudMessaging;
import com.google.android.gms.cloudmessaging.CloudMessagingClient;
import com.google.android.gms.cloudmessaging.RegisterRequest;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/* loaded from: classes5.dex */
class GmsRegistrationClient {
    private final FirebaseApp app;
    private final CloudMessagingClient client;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final GmsRpc gmsRpc;
    private final Metadata metadata;

    public static /* synthetic */ Task $r8$lambda$OUM1JmnF2iBVOuCeLVZtNrCCf24(GmsRegistrationClient r1, Task r2) {
        r1.getClass();
        if (r2.isSuccessful() == false) goto L6;
    L9:
        return r2;
    L6:
        if (isFidAlreadyUsedException(r2.getException()) == false) goto L9;
        Log.w("FirebaseMessaging", "FID_ALREADY_USED Detected. Retrying with FID cache clearing!");
        r1.firebaseInstallations.clearFidCache();
        return r1.registerInternal();
    }

    public static /* synthetic */ Task $r8$lambda$TQsaqh1pHhsdXIGyEUKAEw9pN1s(GmsRegistrationClient r2, ExecutorService r3, Task r4) {
        r2.getClass();
        if (r4.isSuccessful() == false) goto L5;
        String r42 = ((InstallationTokenResult) r4.getResult()).getToken();
        return r2.firebaseInstallations.getId().continueWithTask(r3, new GmsRegistrationClient$$ExternalSyntheticLambda4(r2, r42));
    L5:
        return Tasks.forException(r2.getOrCreateException(r4));
    }

    /* renamed from: $r8$lambda$VrPDTirHwHxD1EQWet49-g8Xzrc, reason: not valid java name */
    public static /* synthetic */ Task m1964$r8$lambda$VrPDTirHwHxD1EQWet49g8Xzrc(GmsRegistrationClient r1, ExecutorService r2, Task r3) {
        r1.getClass();
        if (r3.isSuccessful() == false) goto L5;
        String r0 = (String) ((Pair) r3.getResult()).first;
        return r1.registerOverV1(r0, (String) ((Pair) r3.getResult()).second).continueWith(r2, new GmsRegistrationClient$$ExternalSyntheticLambda3(r0));
    L5:
        return Tasks.forException(r1.getOrCreateException(r3));
    }

    public static /* synthetic */ Task $r8$lambda$dcA7IYHfufmIHy3QuCUwV4XCcgI(GmsRegistrationClient r1, String r2, Task r3) {
        r1.getClass();
        if (r3.isSuccessful() == true) goto L7;
        return Tasks.forException(r1.getOrCreateException(r3));
    L7:
        return Tasks.forResult(new Pair((String) r3.getResult(), r2));
    }

    /* renamed from: $r8$lambda$yKr-HtNI8gEULM23BN5-3WFltEU, reason: not valid java name */
    public static /* synthetic */ String m1965$r8$lambda$yKrHtNI8gEULM23BN53WFltEU(String r1, Task r2) {
        if (r2.isSuccessful() == false) goto L12;
        String r22 = (String) r2.getResult();
        if (TextUtils.isEmpty(r22) == true) goto L10;
        if (r22.endsWith(r1) == false) goto L10;
        return r1;
    L10:
        throw new ExecutionException(new IllegalArgumentException("Unexpected Error: FID NOT matching!"));
    L12:
        throw new ExecutionException(r2.getException());
    }

    public GmsRegistrationClient(Context r2, FirebaseApp r3, FirebaseInstallationsApi r4, GmsRpc r5, Metadata r6) {
        this(r3, r4, r5, CloudMessaging.getClient(r2), r6);
    }

    public GmsRegistrationClient(FirebaseApp r1, FirebaseInstallationsApi r2, GmsRpc r3, CloudMessagingClient r4, Metadata r5) {
        this.client = r4;
        this.app = r1;
        this.firebaseInstallations = r2;
        this.gmsRpc = r3;
        this.metadata = r5;
    }

    private Task<Pair<String, String>> fetchFidCredentials(ExecutorService r3) {
        return this.firebaseInstallations.getToken(false).continueWithTask(r3, new GmsRegistrationClient$$ExternalSyntheticLambda2(this, r3));
    }

    private Exception getOrCreateException(Task<?> r2) {
        if (r2.getException() == null) goto L7;
        return r2.getException();
    L7:
        return new ExecutionException(new RuntimeException("Unexpected Error"));
    }

    private boolean haveV1RegistrationSupport() {
        if (this.metadata.getGmsVersionCode() < 261200000) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isFidAlreadyUsedException(Exception r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (TextUtils.isEmpty(r1.getMessage()) == false) goto L6;
        return false;
    L6:
        if (r1.getMessage().contains("FID_ALREADY_USED:") == false) goto L12;
        return true;
    L12:
        return false;
    }

    private Task<String> registerInternal() {
        boolean r0 = isV1RegistrationEnabled();
        if (r0 == false) goto L10;
        if (haveV1RegistrationSupport() == false) goto L10;
        ExecutorService r02 = FcmExecutors.newNetworkIOExecutor();
        return fetchFidCredentials(r02).continueWithTask(r02, new GmsRegistrationClient$$ExternalSyntheticLambda1(this, r02));
    L10:
        return this.gmsRpc.getToken(r0);
    }

    private Task<String> registerOverV1(String r9, String r10) {
        String r4 = this.app.getOptions().getApiKey();
        String r3 = this.app.getOptions().getApplicationId();
        RegisterRequest r1 = new RegisterRequest(Metadata.getDefaultSenderId(this.app), r3, r4, r9, r10, "fcm-25.1.2");
        return this.client.register(r1);
    }

    public boolean isV1RegistrationEnabled() {
        Context r3 = this.app.getApplicationContext();
        PackageManager r1 = r3.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L13
        if (r1 == null) goto L11;
        ApplicationInfo r32 = r1.getApplicationInfo(r3.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L13
        Bundle r12 = r32.metaData;     // Catch: PackageManager.NameNotFoundException -> L13
        if (r12 != null) goto L8;
        return false;
    L8:
        if (r12.containsKey("firebase_messaging_installation_id_enabled") == false) goto L17;
        return r32.metaData.getBoolean("firebase_messaging_installation_id_enabled");
    L17:
        return false;
    L11:
        return false;
    L18:
        return false;
    }

    public Task<String> register() {
        return registerInternal().continueWithTask(FcmExecutors.newTaskExecutor(), new GmsRegistrationClient$$ExternalSyntheticLambda0(this));
    }
}
