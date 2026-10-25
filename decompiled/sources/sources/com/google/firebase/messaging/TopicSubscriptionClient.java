package com.google.firebase.messaging;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline2;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class TopicSubscriptionClient {
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallationsApi;
    private final FirebaseMessaging firebaseMessaging;

    public TopicSubscriptionClient(FirebaseApp r1, FirebaseMessaging r2, FirebaseInstallationsApi r3) {
        this.firebaseInstallationsApi = r3;
        this.firebaseApp = r1;
        this.firebaseMessaging = r2;
    }

    private static <T> T awaitTask(Task<T> r3) {
        return (T) Tasks.await(r3, 30, TimeUnit.SECONDS);
    L4:
        e = move-exception;
        throw new IOException("SERVICE_NOT_AVAILABLE", e);
    L7:
        e = move-exception;
        Throwable r0 = e.getCause();
        if ((r0 instanceof IOException) == true) goto L17;
        if ((r0 instanceof RuntimeException) == false) goto L15;
        throw ((RuntimeException) r0);
    L15:
        throw new IOException(e);
    L17:
        throw ((IOException) r0);
    }

    public static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseMessaging", 3);
    }

    private void performTopicOperation(String r7, String r8, String r9, String r10) {
        if (r8 == null) goto L44;
        if (r9 == null) goto L44;
        String r0 = this.firebaseApp.getOptions().getProjectId();
        String r1 = this.firebaseApp.getOptions().getApiKey();
        if (r0 == null) goto L42;
        URL r2 = new URL("https://fcmregistrations.googleapis.com/v1/projects/" + r0 + "/registrations/" + r9 + "/topicSubscriptions/" + r7 + ":" + r10);
        if (isDebugLogEnabled() == false) goto L9;
        Log.d("FirebaseMessaging", "Topic " + r10 + " for: " + r7 + " with url: " + r2);
    L9:
        HttpURLConnection r6 = createConnection(r2);
        r6.setRequestMethod("POST");
        r6.setRequestProperty("x-goog-api-key", r1);
        r6.setRequestProperty("x-goog-firebase-installations-auth", r8);
        r6.setDoOutput(false);
        int r82 = r6.getResponseCode();     // Catch: Throwable -> L35 IOException -> L37
        r6.disconnect();
        if (r82 < 200) goto L20;
        if (r82 >= 300) goto L20;
        if (isDebugLogEnabled() == false) goto L49;
        Log.d("FirebaseMessaging", "Topic " + r10 + " for: " + r7 + " succeeded.");
        return;
    L49:
        return;
    L20:
        if (r82 == 404) goto L31;
        if (r82 == 403) goto L31;
        if (r82 < 500) goto L29;
        Model$$ExternalSyntheticBUOutline0.m("INTERNAL_SERVER_ERROR");
        return;
    L29:
        throw new IOException("Topic " + r10 + " failed with status: " + r82);
    L31:
        if (isDebugLogEnabled() == false) goto L33;
        Log.d("FirebaseMessaging", "Topic " + r10 + " failed: " + r6.getResponseMessage());
    L33:
        ZipFilesKt$$ExternalSyntheticBUOutline2.m("Topic ", r10, " failed: ", r6.getResponseMessage());
        return;
    L37:
        e = move-exception;
        throw new IOException("SERVICE_NOT_AVAILABLE", e);     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        r6.disconnect();
        throw th;
    L42:
        Model$$ExternalSyntheticBUOutline0.m("Project ID or API Key is missing");
        return;
    L44:
        Model$$ExternalSyntheticBUOutline0.m("FIS auth token or FIS ID is empty");
    }

    public HttpURLConnection createConnection(URL r1) {
        return (HttpURLConnection) r1.openConnection();
    }

    public void subscribe(String r4) {
        String r0 = ((InstallationTokenResult) awaitTask(this.firebaseInstallationsApi.getToken(false))).getToken();
        this.firebaseMessaging.blockingRegister(false);
        performTopicOperation(r4, r0, (String) awaitTask(this.firebaseInstallationsApi.getId()), "subscribe");
    }

    public void unsubscribe(String r4) {
        String r0 = ((InstallationTokenResult) awaitTask(this.firebaseInstallationsApi.getToken(false))).getToken();
        this.firebaseMessaging.blockingRegister(false);
        performTopicOperation(r4, r0, (String) awaitTask(this.firebaseInstallationsApi.getId()), "unsubscribe");
    }
}
