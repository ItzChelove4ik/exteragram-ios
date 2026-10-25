package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

/* loaded from: classes.dex */
public final class FirebaseOptions {
    private final String apiKey;
    private final String applicationId;
    private final String databaseUrl;
    private final String gaTrackingId;
    private final String gcmSenderId;
    private final String projectId;
    private final String recaptchaSiteKey;
    private final String storageBucket;

    private FirebaseOptions(String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(r3), "ApplicationId must be set.");
        this.applicationId = r3;
        this.apiKey = r4;
        this.databaseUrl = r5;
        this.gaTrackingId = r6;
        this.gcmSenderId = r7;
        this.storageBucket = r8;
        this.recaptchaSiteKey = r9;
        this.projectId = r10;
    }

    public static FirebaseOptions fromResource(Context r10) {
        StringResourceValueReader r0 = new StringResourceValueReader(r10);
        String r2 = r0.getString("google_app_id");
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return null;
    L7:
        return new FirebaseOptions(r2, r0.getString("google_api_key"), r0.getString("firebase_database_url"), r0.getString("ga_trackingId"), r0.getString("gcm_defaultSenderId"), r0.getString("google_storage_bucket"), r0.getString("recaptcha_site_key"), r0.getString("project_id"));
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof FirebaseOptions) == true) goto L5;
        return false;
    L5:
        FirebaseOptions r42 = (FirebaseOptions) r4;
        if (Objects.equal(this.applicationId, r42.applicationId) == true) goto L8;
    L23:
        return false;
    L8:
        if (Objects.equal(this.apiKey, r42.apiKey) == false) goto L23;
        if (Objects.equal(this.databaseUrl, r42.databaseUrl) == false) goto L23;
        if (Objects.equal(this.gaTrackingId, r42.gaTrackingId) == false) goto L23;
        if (Objects.equal(this.gcmSenderId, r42.gcmSenderId) == false) goto L23;
        if (Objects.equal(this.storageBucket, r42.storageBucket) == false) goto L23;
        if (Objects.equal(this.recaptchaSiteKey, r42.recaptchaSiteKey) == false) goto L23;
        if (Objects.equal(this.projectId, r42.projectId) == false) goto L23;
        return true;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getGcmSenderId() {
        return this.gcmSenderId;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.applicationId, this.apiKey, this.databaseUrl, this.gaTrackingId, this.gcmSenderId, this.storageBucket, this.recaptchaSiteKey, this.projectId});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.applicationId).add("apiKey", this.apiKey).add("databaseUrl", this.databaseUrl).add("gcmSenderId", this.gcmSenderId).add("storageBucket", this.storageBucket).add("recaptchaSiteKey", this.recaptchaSiteKey).add("projectId", this.projectId).toString();
    }
}
