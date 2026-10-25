package com.google.firebase.crashlytics.internal.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SettingsController implements SettingsProvider {
    private final CachedSettingsIo cachedSettingsIo;
    private final Context context;
    private final CurrentTimeProvider currentTimeProvider;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final AtomicReference<Settings> settings;
    private final SettingsJsonParser settingsJsonParser;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;
    private final AtomicReference<TaskCompletionSource<Settings>> settingsTask;

    /* renamed from: com.google.firebase.crashlytics.internal.settings.SettingsController$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements SuccessContinuation<Void, Void> {
        final /* synthetic */ SettingsController this$0;
        final /* synthetic */ CrashlyticsWorkers val$crashlyticsWorkers;

        public static /* synthetic */ JSONObject $r8$lambda$ZpaHmjgnf1VToWDHDrPNQlZI6DI(AnonymousClass1 r2) {
            return SettingsController.access$700(r2.this$0).invoke(SettingsController.access$300(r2.this$0), true);
        }

        public AnonymousClass1(SettingsController r1, CrashlyticsWorkers r2) {
            this.this$0 = r1;
            this.val$crashlyticsWorkers = r2;
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        public /* bridge */ /* synthetic */ Task<Void> then(Void r1) {
            return then2(r1);
        }

        /* renamed from: then, reason: avoid collision after fix types in other method */
        public Task<Void> then2(Void r5) {
            JSONObject r52 = (JSONObject) this.val$crashlyticsWorkers.network.getExecutor().submit(new SettingsController$1$$ExternalSyntheticLambda0(this)).get();
            if (r52 == null) goto L6;
            Settings r0 = SettingsController.access$000(this.this$0).parseSettingsJson(r52);
            SettingsController.access$100(this.this$0).writeCachedSettings(r0.expiresAtMillis, r52);
            SettingsController.access$200(this.this$0, r52, "Loaded settings: ");
            SettingsController r53 = this.this$0;
            SettingsController.access$400(r53, SettingsController.access$300(r53).instanceId);
            SettingsController.access$500(this.this$0).set(r0);
            ((TaskCompletionSource) SettingsController.access$600(this.this$0).get()).trySetResult(r0);
        L6:
            return Tasks.forResult(null);
        }
    }

    public SettingsController(Context r4, SettingsRequest r5, CurrentTimeProvider r6, SettingsJsonParser r7, CachedSettingsIo r8, SettingsSpiCall r9, DataCollectionArbiter r10) {
        AtomicReference<Settings> r0 = new AtomicReference();
        this.settings = r0;
        this.settingsTask = new AtomicReference(new TaskCompletionSource());
        this.context = r4;
        this.settingsRequest = r5;
        this.currentTimeProvider = r6;
        this.settingsJsonParser = r7;
        this.cachedSettingsIo = r8;
        this.settingsSpiCall = r9;
        this.dataCollectionArbiter = r10;
        r0.set(DefaultSettingsJsonTransform.defaultSettings(r6));
    }

    public static /* synthetic */ SettingsJsonParser access$000(SettingsController r0) {
        return r0.settingsJsonParser;
    }

    public static /* synthetic */ CachedSettingsIo access$100(SettingsController r0) {
        return r0.cachedSettingsIo;
    }

    public static /* synthetic */ void access$200(SettingsController r0, JSONObject r1, String r2) {
        r0.logSettings(r1, r2);
    }

    public static /* synthetic */ SettingsRequest access$300(SettingsController r0) {
        return r0.settingsRequest;
    }

    public static /* synthetic */ boolean access$400(SettingsController r0, String r1) {
        return r0.setStoredBuildInstanceIdentifier(r1);
    }

    public static /* synthetic */ AtomicReference access$500(SettingsController r0) {
        return r0.settings;
    }

    public static /* synthetic */ AtomicReference access$600(SettingsController r0) {
        return r0.settingsTask;
    }

    public static /* synthetic */ SettingsSpiCall access$700(SettingsController r0) {
        return r0.settingsSpiCall;
    }

    public static SettingsController create(Context r18, String r19, IdManager r20, HttpRequestFactory r21, String r22, String r23, FileStore r24, DataCollectionArbiter r25) {
        String r0 = r20.getInstallerPackageName();
        SystemCurrentTimeProvider r4 = new SystemCurrentTimeProvider();
        SettingsJsonParser r5 = new SettingsJsonParser(r4);
        CachedSettingsIo r6 = new CachedSettingsIo(r24);
        DefaultSettingsSpiCall r7 = new DefaultSettingsSpiCall(String.format(Locale.US, "https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/%s/settings", new Object[]{r19}), r21);
        return new SettingsController(r18, new SettingsRequest(r19, r20.getModelName(), r20.getOsBuildVersionString(), r20.getOsDisplayVersionString(), r20, CommonUtils.createInstanceIdFrom(new String[]{CommonUtils.getMappingFileId(r18), r19, r23, r22}), r23, r22, DeliveryMechanism.determineFrom(r0).getId()), r4, r5, r6, r7, r25);
    }

    private Settings getCachedSettingsData(SettingsCacheBehavior r6) {
        Settings r0 = null;
    L16:
        e = e;
    L26:
        Logger.getLogger().e("Failed to get cached settings", e);
        return r0;
    L4:
        if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(r6) == true) goto L25;
        JSONObject r1 = this.cachedSettingsIo.readCachedSettings();     // Catch: Exception -> L16
        if (r1 == null) goto L24;
        Settings r2 = this.settingsJsonParser.parseSettingsJson(r1);     // Catch: Exception -> L16
        if (r2 == null) goto L22;
        logSettings(r1, "Loaded cached settings: ");     // Catch: Exception -> L16
        long r3 = this.currentTimeProvider.getCurrentTimeMillis();     // Catch: Exception -> L16
        if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(r6) == false) goto L12;
    L29:
        Logger.getLogger().v("Returning cached settings.");     // Catch: Exception -> L20
        return r2;
    L20:
        e = e;
        r0 = r2;
        goto L26
    L12:
        if (r2.isExpired(r3) == false) goto L29;
        Logger.getLogger().v("Cached settings have expired.");     // Catch: Exception -> L16
        return null;
    L22:
        Logger.getLogger().e("Failed to parse cached settings data.", null);     // Catch: Exception -> L16
        return null;
    L24:
        Logger.getLogger().d("No cached settings data found.");     // Catch: Exception -> L16
    L25:
        return null;
    }

    private String getStoredBuildInstanceIdentifier() {
        return CommonUtils.getSharedPrefs(this.context).getString("existing_instance_identifier", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private void logSettings(JSONObject r2, String r3) {
        Logger.getLogger().d(r3 + r2.toString());
    }

    @SuppressLint({"CommitPrefEdits"})
    private boolean setStoredBuildInstanceIdentifier(String r2) {
        SharedPreferences.Editor r1 = CommonUtils.getSharedPrefs(this.context).edit();
        r1.putString("existing_instance_identifier", r2);
        r1.apply();
        return true;
    }

    public boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(this.settingsRequest.instanceId);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsProvider
    public Task<Settings> getSettingsAsync() {
        return this.settingsTask.get().getTask();
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsProvider
    public Settings getSettingsSync() {
        return this.settings.get();
    }

    public Task<Void> loadSettingsData(CrashlyticsWorkers r2) {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE, r2);
    }

    public Task<Void> loadSettingsData(SettingsCacheBehavior r3, CrashlyticsWorkers r4) {
        if (buildInstanceIdentifierChanged() == true) goto L8;
        Settings r32 = getCachedSettingsData(r3);
        if (r32 == null) goto L8;
        this.settings.set(r32);
        this.settingsTask.get().trySetResult(r32);
        return Tasks.forResult(null);
    L8:
        Settings r33 = getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
        if (r33 == null) goto L12;
        this.settings.set(r33);
        this.settingsTask.get().trySetResult(r33);
    L12:
        return this.dataCollectionArbiter.waitForDataCollectionPermission().onSuccessTask(r4.common, new AnonymousClass1(this, r4));
    }
}
