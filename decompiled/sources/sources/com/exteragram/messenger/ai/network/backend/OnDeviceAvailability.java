package com.exteragram.messenger.ai.network.backend;

import android.os.Build;
import android.text.TextUtils;
import com.exteragram.messenger.ai.AiConfig;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.prompt.Generation;
import com.google.mlkit.genai.prompt.GenerationConfig;
import com.google.mlkit.genai.prompt.GenerativeModel;
import com.google.mlkit.genai.prompt.ModelConfig;
import com.google.mlkit.genai.prompt.java.GenerativeModelFutures;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;

/* loaded from: classes4.dex */
public abstract class OnDeviceAvailability {
    private static final AtomicInteger configGeneration = null;
    private static final Map<Integer, Integer> downloadPercents = null;
    private static final Set<Integer> downloadingModels = null;
    private static volatile String modelName;
    private static volatile int modelNameGeneration;
    private static volatile boolean otherModelSupported;
    private static final List<Runnable> pendingCallbacks = null;
    private static boolean recheckRequested;
    private static final AtomicBoolean refreshedOnce = null;
    private static final AtomicBoolean refreshing = null;
    private static final AtomicBoolean restored = null;
    private static final Map<Integer, Integer> sessionStatuses = null;
    private static volatile int status;
    private static volatile boolean supportedThisSession;
    private static volatile boolean systemPromptSupported;
    private static volatile boolean thinkingSupported;
    private static volatile int tokenLimit;
    private static final AtomicBoolean warmedUp = null;

    /* renamed from: com.exteragram.messenger.ai.network.backend.OnDeviceAvailability$1, reason: invalid class name */
    public class AnonymousClass1 implements DownloadCallback {
        final /* synthetic */ DownloadListener val$listener;
        final /* synthetic */ int val$modelKey;
        final /* synthetic */ long[] val$total;

        public AnonymousClass1(long[] r1, DownloadListener r2, int r3) {
            this.val$total = r1;
            this.val$listener = r2;
            this.val$modelKey = r3;
        }

        @Override // com.google.mlkit.genai.common.DownloadCallback
        public void onDownloadFailed(GenAiException r1) {
            FileLog.e("AI_ON_DEVICE_DOWNLOAD_FAILED", r1);
        }

        @Override // com.google.mlkit.genai.common.DownloadCallback
        public void onDownloadProgress(long r6) {
            long r1 = this.val$total[0];
            if (r1 <= 0) goto L5;
            float r0 = Math.min(1.0f, r6 / r1);
        L7:
            if (r0 >= 0.0f) goto L9;
            int r12 = -1;
        L10:
            Integer r2 = (Integer) OnDeviceAvailability.m1031$$Nest$sfgetdownloadPercents().put(Integer.valueOf(this.val$modelKey), Integer.valueOf(r12));
            if (r2 != null) goto L13;
        L15:
            OnDeviceAvailability.m1032$$Nest$smnotifyServicesUpdated();
            this.val$listener.onProgress(r0, r6);
            return;
        L13:
            if (r2.intValue() != r12) goto L15;
            return;
        L9:
            r12 = (int) (100.0f * r0);
            goto L10
        L5:
            r0 = -1.0f;
            goto L7
        }

        @Override // com.google.mlkit.genai.common.DownloadCallback
        public void onDownloadStarted(long r3) {
            this.val$total[0] = r3;
            this.val$listener.onStarted(r3);
        }
    }

    public interface DownloadListener {
        void onCompleted();

        void onFailed(Exception r1);

        void onProgress(float r1, long r2);

        void onStarted(long r1);
    }

    /* renamed from: $r8$lambda$KicUEHGdAl2FIno-2ohu8c5fiD0, reason: not valid java name */
    public static /* synthetic */ void m1029$r8$lambda$KicUEHGdAl2FIno2ohu8c5fiD0() {
        GenerativeModel r1 = null;
        r1 = createModel();     // Catch: Throwable -> L6 InterruptedException -> L11
        GenerativeModelFutures.from(r1).warmup().get(30, TimeUnit.SECONDS);     // Catch: Throwable -> L6 InterruptedException -> L11
        closeQuietly(r1);
        return;
    L9:
        th = move-exception;
        closeQuietly(r1);
        throw th;
    L6:
        th = move-exception;
        warmedUp.set(false);     // Catch: Throwable -> L9
        FileLog.e("AI_ON_DEVICE_WARMUP", th);     // Catch: Throwable -> L9
    L8:
        closeQuietly(r1);
        return;
    L11:
        Thread.currentThread().interrupt();     // Catch: Throwable -> L9
        warmedUp.set(false);     // Catch: Throwable -> L9
        goto L8
    }

    public static /* synthetic */ void $r8$lambda$Yv1A38WI11jz5Zzz_QcDbhaaM4E() {
        check();
    }

    public static /* synthetic */ void $r8$lambda$edA7BpxC7ENFv9jOjeLPcqoKwKs(Runnable r0, ExecutorService r1) {
        r0.run();     // Catch: Throwable -> L5
        r1.shutdown();
        return;
    L5:
        th = move-exception;
        r1.shutdown();
        throw th;
    }

    public static /* synthetic */ void $r8$lambda$gRihwTHakZKAHuErdKTQDB0RBd8() {
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.servicesUpdated, new Object[0]);
    }

    /* renamed from: $r8$lambda$zfvooT6TwoW5Ai-QwCsZshX1_Yg, reason: not valid java name */
    public static /* synthetic */ void m1030$r8$lambda$zfvooT6TwoW5AiQwCsZshX1_Yg(int r0, DownloadListener r1) {
        runDownload(r0, r1);
    }

    /* renamed from: -$$Nest$sfgetdownloadPercents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m1031$$Nest$sfgetdownloadPercents() {
        return downloadPercents;
    }

    /* renamed from: -$$Nest$smnotifyServicesUpdated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1032$$Nest$smnotifyServicesUpdated() {
        notifyServicesUpdated();
    }

    static {
        refreshing = new AtomicBoolean();
        downloadingModels = ConcurrentHashMap.newKeySet();
        downloadPercents = new ConcurrentHashMap();
        sessionStatuses = new ConcurrentHashMap();
        restored = new AtomicBoolean();
        refreshedOnce = new AtomicBoolean();
        warmedUp = new AtomicBoolean();
        configGeneration = new AtomicInteger();
        pendingCallbacks = new ArrayList();
        status = -1;
    }

    private static void apply(int r2) {
        if (isStatusSupported(r2) == false) goto L6;
        supportedThisSession = true;
    L6:
        if (status != r2) goto L8;
        return;
    L8:
        status = r2;
        AiConfig.getEditor().putInt("onDeviceFeatureStatus", r2).apply();
        notifyServicesUpdated();
    }

    private static void check() {
    L2:
        AtomicInteger r0 = configGeneration;
        int r1 = r0.get();
        int r2 = getModelKey();
        int r3 = checkStatus(r1);
        if (r1 != r0.get()) goto L2;
        sessionStatuses.put(Integer.valueOf(r2), Integer.valueOf(r3));
        if (r3 != 0) goto L9;
        updateOtherModelSupported(r2);
    L9:
        if (modelNameGeneration != r1) goto L11;
    L13:
        apply(r3);
        List<Runnable> r22 = pendingCallbacks;
        monitor-enter(r22);
        int r4 = 0;
        if (recheckRequested == false) goto L18;
    L28:
        recheckRequested = false;     // Catch: Throwable -> L26
        monitor-exit(r22);     // Catch: Throwable -> L26
        goto L2
    L18:
        if (r1 != r0.get()) goto L28;
        refreshing.set(false);     // Catch: Throwable -> L26
        ArrayList r02 = new ArrayList(r22);     // Catch: Throwable -> L26
        r22.clear();     // Catch: Throwable -> L26
        monitor-exit(r22);     // Catch: Throwable -> L26
        int r12 = r02.size();
    L23:
        if (r4 >= r12) goto L25;
        Object r23 = r02.get(r4);
        r4 = r4 + 1;
        runOnDone((Runnable) r23);
        goto L23
    L25:
        return;
    L26:
        th = move-exception;
        throw th;
    L11:
        if (modelName == null) goto L13;
        modelName = null;
        AiConfig.getEditor().remove("onDeviceModelName").apply();
        notifyServicesUpdated();
        goto L13
    }

    private static int checkStatus(int r7) {
        GenerativeModel r1 = createModel();     // Catch: Throwable -> L20
        GenerativeModelFutures r2 = GenerativeModelFutures.from(r1);     // Catch: Throwable -> L11
        Integer r3 = r2.checkStatus().get(10, TimeUnit.SECONDS);     // Catch: Throwable -> L11
        if (r3 != null) goto L7;
        int r0 = -1;
    L9:
        if (r0 != 3) goto L14;
        readMetadata(r2, r7);     // Catch: Throwable -> L11
    L18:
        closeQuietly(r1);
        return r0;
    L14:
        if (isStatusSupported(r0) == false) goto L18;
        if (readModelName(r2, r7) == false) goto L18;
        notifyServicesUpdated();     // Catch: Throwable -> L11
        goto L18
    L7:
        r0 = r3.intValue();     // Catch: Throwable -> L11
    L11:
        th = th;
    L28:
        FileLog.e("AI_ON_DEVICE_STATUS_FAILED", th);     // Catch: Throwable -> L25
        closeQuietly(r1);
        return 0;
    L25:
        th = move-exception;
        closeQuietly(r1);
        throw th;
    L20:
        th = th;
        r1 = null;
        goto L28
    }

    private static void closeQuietly(GenerativeModel r0) {
        if (r0 != null) goto L7;
        return;
    L7:
        r0.close();     // Catch: Throwable -> L6
        return;
    }

    public static GenerativeModel createModel() {
        return createModel(getModelKey());
    }

    private static GenerativeModel createModel(int r3) {
        ModelConfig.Builder r0 = ModelConfig.builder();
        int r2 = 1;
        if ((r3 & 2) == 0) goto L5;
        int r1 = 1;
    L6:
        r0.setReleaseStage(r1);
        if ((r3 & 1) != 0) goto L10;
        r2 = 2;
    L10:
        r0.setPreference(r2);
        GenerationConfig.Builder r32 = new GenerationConfig.Builder();
        r32.setModelConfig(r0.build());
        return Generation.INSTANCE.getClient(r32.build());
    L5:
        r1 = 0;
        goto L6
    }

    public static void download(int r2, DownloadListener r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L7;
        r3.onFailed(new IllegalStateException("unsupported android version"));
        return;
    L7:
        if (downloadingModels.add(Integer.valueOf(r2)) == true) goto L9;
        return;
    L9:
        downloadPercents.remove(Integer.valueOf(r2));
        notifyServicesUpdated();
        runOnWorker(new OnDeviceAvailability$$ExternalSyntheticLambda4(r2, r3));
    }

    public static void ensureMetadata(GenerativeModelFutures r1, int r2) {
        ensureRestored();
        if (tokenLimit <= 0) goto L5;
        return;
    L5:
        readMetadata(r1, r2);
    }

    private static void ensureRestored() {
        if (restored.compareAndSet(false, true) == false) goto L6;
        status = AiConfig.getPreferences().getInt("onDeviceFeatureStatus", -1);
        modelName = AiConfig.getPreferences().getString("onDeviceModelName", null);
        thinkingSupported = AiConfig.getPreferences().getBoolean("onDeviceThinkingSupported", false);
        systemPromptSupported = AiConfig.getPreferences().getBoolean("onDeviceSystemPromptSupported", false);
        tokenLimit = AiConfig.getPreferences().getInt("onDeviceTokenLimit", 0);
        otherModelSupported = AiConfig.getPreferences().getBoolean("onDeviceOtherModelSupported", false);
        return;
    }

    public static int getConfigGeneration() {
        return configGeneration.get();
    }

    public static int getDownloadPercent() {
        Integer r0 = downloadPercents.get(Integer.valueOf(getModelKey()));
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.intValue();
    }

    public static int getModelKey() {
        if (AiConfig.getOnDevicePreviewModel() == false) goto L5;
        int r0 = 2;
    L7:
        return r0 | (AiConfig.getOnDeviceFastModel() ? 1 : 0);
    L5:
        r0 = 0;
        goto L7
    }

    public static String getModelName() {
        ensureRestored();
        return modelName;
    }

    public static int getStatus() {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return 0;
    L5:
        ensureRestored();
        if (refreshedOnce.compareAndSet(false, true) == false) goto L9;
        refresh(null);
    L9:
        return status;
    }

    public static int getTokenLimit() {
        ensureRestored();
        return tokenLimit;
    }

    public static boolean isDownloadInProgress() {
        if (isDownloading() == false) goto L5;
        return true;
    L5:
        if (getStatus() == 2) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isDownloading() {
        return downloadingModels.contains(Integer.valueOf(getModelKey()));
    }

    public static boolean isReady() {
        if (getStatus() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isStatusSupported(int r2) {
        if (r2 == 3) goto L10;
        if (r2 == 1) goto L10;
        if (r2 == 2) goto L10;
        return false;
    L10:
        return true;
    }

    public static boolean isSupported() {
        if (isStatusSupported(getStatus()) == false) goto L5;
        return true;
    L5:
        if (supportedThisSession == false) goto L7;
        return true;
    L7:
        if (otherModelSupported == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isSystemPromptSupported() {
        ensureRestored();
        return systemPromptSupported;
    }

    public static boolean isThinkingSupported() {
        ensureRestored();
        return thinkingSupported;
    }

    public static boolean needsDownload() {
        if (getStatus() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private static void notifyServicesUpdated() {
        AndroidUtilities.runOnUIThread(new OnDeviceAvailability$$ExternalSyntheticLambda1());
    }

    public static void onModelConfigChanged(Runnable r2) {
        ensureRestored();
        configGeneration.incrementAndGet();
        warmedUp.set(false);
        thinkingSupported = false;
        systemPromptSupported = false;
        tokenLimit = 0;
        AiConfig.getEditor().remove("onDeviceThinkingSupported").remove("onDeviceSystemPromptSupported").remove("onDeviceTokenLimit").apply();
        refresh(r2);
    }

    private static int probeStatus(int r4) {
        GenerativeModel r42 = createModel(r4);     // Catch: Throwable -> L11
        Integer r0 = GenerativeModelFutures.from(r42).checkStatus().get(10, TimeUnit.SECONDS);     // Catch: Throwable -> L9
        if (r0 != null) goto L6;
        int r02 = -1;
    L7:
        closeQuietly(r42);
        return r02;
    L6:
        r02 = r0.intValue();     // Catch: Throwable -> L9
    L9:
        th = th;
    L19:
        FileLog.e("AI_ON_DEVICE_PROBE_FAILED", th);     // Catch: Throwable -> L16
        closeQuietly(r42);
        return 0;
    L16:
        th = move-exception;
        closeQuietly(r42);
        throw th;
    L11:
        th = th;
        r42 = null;
        goto L19
    }

    private static void readMetadata(GenerativeModelFutures r7, int r8) {
        TimeUnit r0 = TimeUnit.SECONDS;
        boolean r1 = readModelName(r7, r8);
        boolean r4 = Boolean.TRUE.equals(r7.isThinkingModeAvailable().get(10, r0));     // Catch: Exception -> L10
        if (r8 == configGeneration.get()) goto L7;
        return;
    L7:
        if (r4 == thinkingSupported) goto L38;
        thinkingSupported = r4;     // Catch: Exception -> L10
        AiConfig.getEditor().putBoolean("onDeviceThinkingSupported", r4).apply();     // Catch: Exception -> L10
        r1 = true;
    L38:
        boolean r42 = Boolean.TRUE.equals(r7.isSystemPromptAvailable().get(10, r0));     // Catch: Exception -> L19
        if (r8 == configGeneration.get()) goto L16;
        return;
    L16:
        if (r42 == systemPromptSupported) goto L36;
        systemPromptSupported = r42;     // Catch: Exception -> L19
        AiConfig.getEditor().putBoolean("onDeviceSystemPromptSupported", r42).apply();     // Catch: Exception -> L19
    L36:
        Integer r72 = r7.getTokenLimit().get(10, r0);     // Catch: Exception -> L31
        if (r8 != configGeneration.get()) goto L44;
        if (r72 != null) goto L26;
    L33:
        if (r1 == false) goto L45;
        notifyServicesUpdated();
        return;
    L45:
        return;
    L26:
        if (r72.intValue() <= 0) goto L33;
        if (r72.intValue() == tokenLimit) goto L33;
        tokenLimit = r72.intValue();     // Catch: Exception -> L31
        AiConfig.getEditor().putInt("onDeviceTokenLimit", r72.intValue()).apply();     // Catch: Exception -> L31
        goto L33
    L44:
        return;
    L31:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_TOKEN_LIMIT", e);
    L19:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_SYSTEM_PROMPT", e);
    L10:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_THINKING", e);
        goto L38
    }

    private static boolean readModelName(GenerativeModelFutures r4, int r5) {
        String r42 = r4.getBaseModelName().get(10, TimeUnit.SECONDS);     // Catch: Exception -> L14
        if (r5 == configGeneration.get()) goto L6;
    L16:
        return false;
    L6:
        if (TextUtils.isEmpty(r42) == true) goto L16;
        modelNameGeneration = r5;     // Catch: Exception -> L14
        if (r42.equals(modelName) == false) goto L11;
        return false;
    L11:
        modelName = r42;     // Catch: Exception -> L14
        AiConfig.getEditor().putString("onDeviceModelName", r42).apply();     // Catch: Exception -> L14
        return true;
    L14:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_MODEL_NAME", e);
        return false;
    }

    public static void refresh(Runnable r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L6;
        apply(0);
        runOnDone(r3);
        return;
    L6:
        List<Runnable> r0 = pendingCallbacks;
        monitor-enter(r0);
        if (r3 == null) goto L13;
        r0.add(r3);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L13:
        if (refreshing.compareAndSet(false, true) == true) goto L17;
        recheckRequested = true;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L17:
        monitor-exit(r0);     // Catch: Throwable -> L10
        ensureRestored();
        refreshedOnce.set(true);
        runOnWorker(new OnDeviceAvailability$$ExternalSyntheticLambda0());
    }

    public static void report(int r2, int r3) {
        ensureRestored();
        refreshedOnce.set(true);
        if (r3 != configGeneration.get()) goto L6;
        apply(r2);
        return;
    }

    private static void runDownload(int r10, DownloadListener r11) {
        GenerativeModel r2 = null;
        GenerativeModel r3 = createModel(r10);     // Catch: Throwable -> L21 Exception -> L23 InterruptedException -> L26
    L34:
        GenerativeModelFutures r4 = GenerativeModelFutures.from(r3);     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
        long[] r5 = {0};     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
        r4.download(new AnonymousClass1(r5, r11, r10)).get();     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
        int r42 = r4.checkStatus().get(10, TimeUnit.SECONDS).intValue();     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
        if (r10 != getModelKey()) goto L14;
        refresh(null);     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
    L14:
        if (r42 != 3) goto L17;
        r11.onProgress(1.0f, r5[0]);     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
        r11.onCompleted();     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
    L19:
        closeQuietly(r3);
        downloadPercents.remove(Integer.valueOf(r10));
        downloadingModels.remove(Integer.valueOf(r10));
        notifyServicesUpdated();
        return;
    L17:
        if (r42 == 2) goto L19;
        r11.onFailed(new IllegalStateException("model is not available after download, status=" + r42));     // Catch: Throwable -> L7 Exception -> L9 InterruptedException -> L11
    L11:
        e = e;
        r2 = r3;
    L27:
        Thread.currentThread().interrupt();     // Catch: Throwable -> L21
        FileLog.e("AI_ON_DEVICE_DOWNLOAD", e);     // Catch: Throwable -> L21
        r11.onFailed(e);     // Catch: Throwable -> L21
    L25:
        closeQuietly(r2);
        downloadPercents.remove(Integer.valueOf(r10));
        downloadingModels.remove(Integer.valueOf(r10));
        notifyServicesUpdated();
        return;
    L9:
        e = e;
        r2 = r3;
    L24:
        FileLog.e("AI_ON_DEVICE_DOWNLOAD", e);     // Catch: Throwable -> L21
        r11.onFailed(e);     // Catch: Throwable -> L21
    L7:
        th = th;
        r2 = r3;
    L30:
        closeQuietly(r2);
        downloadPercents.remove(Integer.valueOf(r10));
        downloadingModels.remove(Integer.valueOf(r10));
        notifyServicesUpdated();
        throw th;
    L21:
        th = th;
    L26:
        e = e;
    L23:
        e = e;
        goto L24
    }

    private static void runOnDone(Runnable r0) {
        if (r0 == null) goto L5;
        AndroidUtilities.runOnUIThread(r0);
        return;
    }

    private static void runOnWorker(Runnable r2) {
        ExecutorService r0 = Executors.newSingleThreadExecutor();
        r0.execute(new OnDeviceAvailability$$ExternalSyntheticLambda3(r2, r0));
    }

    private static void updateOtherModelSupported(int r4) {
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 > 3) goto L14;
        if (r1 == true) goto L14;
        if (r0 == r4) goto L12;
        Map<Integer, Integer> r12 = sessionStatuses;
        Integer r2 = r12.get(Integer.valueOf(r0));
        if (r2 != null) goto L11;
        r2 = Integer.valueOf(probeStatus(r0));
        r12.put(Integer.valueOf(r0), r2);
    L11:
        r1 = isStatusSupported(r2.intValue());
    L12:
        r0 = r0 + 1;
    L14:
        if (r1 == otherModelSupported) goto L21;
        otherModelSupported = r1;
        AiConfig.getEditor().putBoolean("onDeviceOtherModelSupported", r1).apply();
        notifyServicesUpdated();
        return;
    }

    public static void warmup() {
        if (AiConfig.ON_DEVICE_SERVICE.getId().equals(AiConfig.getSelectedServiceId()) == true) goto L5;
        return;
    L5:
        if (isReady() == true) goto L8;
        return;
    L8:
        if (warmedUp.compareAndSet(false, true) == false) goto L14;
        runOnWorker(new OnDeviceAvailability$$ExternalSyntheticLambda2());
        return;
    }
}
