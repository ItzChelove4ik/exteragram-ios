package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.InstallIdProvider;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class IdManager implements InstallIdProvider {
    private static final String FORWARD_SLASH_REGEX = null;
    private static final Pattern ID_PATTERN = null;
    private final Context appContext;
    private final String appIdentifier;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final FirebaseInstallationsApi firebaseInstallations;
    private InstallIdProvider.InstallIds installIds;
    private final InstallerPackageNameProvider installerPackageNameProvider;

    static {
        ID_PATTERN = Pattern.compile("[^\\p{Alnum}]");
        FORWARD_SLASH_REGEX = Pattern.quote("/");
    }

    public IdManager(Context r2, String r3, FirebaseInstallationsApi r4, DataCollectionArbiter r5) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.appContext = r2;
        this.appIdentifier = r3;
        this.firebaseInstallations = r4;
        this.dataCollectionArbiter = r5;
        this.installerPackageNameProvider = new InstallerPackageNameProvider();
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("appIdentifier must not be null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("appContext must not be null");
        throw null;
    }

    private synchronized String createAndCacheCrashlyticsInstallId(String r5, SharedPreferences r6) {
        monitor-enter(this);
        String r1 = formatId(UUID.randomUUID().toString());     // Catch: Throwable -> L7
        Logger.getLogger().v("Created new Crashlytics installation ID: " + r1 + " for FID: " + r5);     // Catch: Throwable -> L7
        r6.edit().putString("crashlytics.installation.id", r1).putString("firebase.installation.id", r5).apply();     // Catch: Throwable -> L7
        monitor-exit(this);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static String createSyntheticFid() {
        return "SYN_" + UUID.randomUUID().toString();
    }

    private static String formatId(String r1) {
        return ID_PATTERN.matcher(r1).replaceAll(_UrlKt.FRAGMENT_ENCODE_SET).toLowerCase(Locale.US);
    }

    public static boolean isSyntheticFid(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.startsWith("SYN_") == false) goto L9;
        return true;
    L9:
        return false;
    }

    private String readCachedCrashlyticsInstallId(SharedPreferences r2) {
        return r2.getString("crashlytics.installation.id", null);
    }

    private String removeForwardSlashesIn(String r2) {
        return r2.replaceAll(FORWARD_SLASH_REGEX, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private boolean shouldRefresh() {
        InstallIdProvider.InstallIds r0 = this.installIds;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.getFirebaseInstallationId() == null) goto L7;
        return false;
    L7:
        if (this.dataCollectionArbiter.isAutomaticDataCollectionEnabled() == true) goto L14;
        return false;
    L14:
        return true;
    }

    public FirebaseInstallationId fetchTrueFid(boolean r7) {
        CrashlyticsWorkers.checkNotMainThread();
        TimeUnit r0 = TimeUnit.MILLISECONDS;
        String r3 = null;
        if (r7 == true) goto L15;
    L8:
        String r72 = null;
    L17:
        r3 = (String) Tasks.await(this.firebaseInstallations.getId(), NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, r0);     // Catch: Exception -> L11
    L14:
        return new FirebaseInstallationId(r3, r72);
    L11:
        e = move-exception;
        Logger.getLogger().w("Error getting Firebase installation id.", e);
        goto L14
    L15:
        r72 = ((InstallationTokenResult) Tasks.await(this.firebaseInstallations.getToken(false), NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, r0)).getToken();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        Logger.getLogger().w("Error getting Firebase authentication token.", e);
        goto L8
    }

    public String getAppIdentifier() {
        return this.appIdentifier;
    }

    @Override // com.google.firebase.crashlytics.internal.common.InstallIdProvider
    public synchronized InstallIdProvider.InstallIds getInstallIds() {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (shouldRefresh() == true) goto L11;
        InstallIdProvider.InstallIds r0 = this.installIds;     // Catch: Throwable -> L9
        monitor-exit(this);
        return r0;
    L11:
        Logger.getLogger().v("Determining Crashlytics installation ID...");     // Catch: Throwable -> L9
        SharedPreferences r3 = CommonUtils.getSharedPrefs(this.appContext);     // Catch: Throwable -> L9
        String r4 = r3.getString("firebase.installation.id", null);     // Catch: Throwable -> L9
        Logger.getLogger().v("Cached Firebase Installation ID: " + r4);     // Catch: Throwable -> L9
        if (this.dataCollectionArbiter.isAutomaticDataCollectionEnabled() == false) goto L25;
        FirebaseInstallationId r2 = fetchTrueFid(false);     // Catch: Throwable -> L9
        Logger.getLogger().v("Fetched Firebase Installation ID: " + r2.getFid());     // Catch: Throwable -> L9
        if (r2.getFid() != null) goto L21;
        if (r4 != null) goto L18;
        String r1 = createSyntheticFid();     // Catch: Throwable -> L9
    L19:
        r2 = new FirebaseInstallationId(r1, null);     // Catch: Throwable -> L9
        goto L21
    L18:
        r1 = r4;
    L21:
        if (Objects.equals(r2.getFid(), r4) == false) goto L23;
        this.installIds = InstallIdProvider.InstallIds.create(readCachedCrashlyticsInstallId(r3), r2);     // Catch: Throwable -> L9
    L28:
        Logger.getLogger().v("Install IDs: " + this.installIds);     // Catch: Throwable -> L9
        InstallIdProvider.InstallIds r02 = this.installIds;     // Catch: Throwable -> L9
        monitor-exit(this);
        return r02;
    L23:
        this.installIds = InstallIdProvider.InstallIds.create(createAndCacheCrashlyticsInstallId(r2.getFid(), r3), r2);     // Catch: Throwable -> L9
        goto L28
    L25:
        if (isSyntheticFid(r4) == false) goto L27;
        this.installIds = InstallIdProvider.InstallIds.createWithoutFid(readCachedCrashlyticsInstallId(r3));     // Catch: Throwable -> L9
        goto L28
    L27:
        this.installIds = InstallIdProvider.InstallIds.createWithoutFid(createAndCacheCrashlyticsInstallId(createSyntheticFid(), r3));     // Catch: Throwable -> L9
        goto L28
    }

    public String getInstallerPackageName() {
        return this.installerPackageNameProvider.getInstallerPackageName(this.appContext);
    }

    public String getModelName() {
        return String.format(Locale.US, "%s/%s", new Object[]{removeForwardSlashesIn(Build.MANUFACTURER), removeForwardSlashesIn(Build.MODEL)});
    }

    public String getOsBuildVersionString() {
        return removeForwardSlashesIn(Build.VERSION.INCREMENTAL);
    }

    public String getOsDisplayVersionString() {
        return removeForwardSlashesIn(Build.VERSION.RELEASE);
    }
}
