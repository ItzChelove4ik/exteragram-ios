package org.telegram.messenger;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebView;
import androidx.core.content.pm.ShortcutManagerCompat;
import com.exteragram.messenger.proxy.ProxyController;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.utils.proxy.ProxySettings;

/* loaded from: classes.dex */
public class SharedConfig {
    private static final int[] LOW_SOC = null;
    public static final int PASSCODE_TYPE_PASSWORD = 1;
    public static final int PASSCODE_TYPE_PIN = 0;
    public static final int PERFORMANCE_CLASS_AVERAGE = 1;
    public static final int PERFORMANCE_CLASS_HIGH = 2;
    public static final int PERFORMANCE_CLASS_LOW = 0;
    public static final int SAVE_TO_GALLERY_FLAG_CHANNELS = 4;
    public static final int SAVE_TO_GALLERY_FLAG_GROUP = 2;
    public static final int SAVE_TO_GALLERY_FLAG_PEER = 1;
    public static boolean adaptableColorInBrowser = false;
    public static boolean allowBigEmoji = false;
    static Boolean allowPreparingHevcPlayers = null;
    public static boolean allowScreenCapture = false;
    private static Boolean animationsEnabled = null;
    public static boolean appLocked = false;
    public static boolean archiveHidden = false;
    public static int autoLockIn = 0;
    public static int badPasscodeTries = 0;
    public static boolean bigCameraForRound = false;
    public static int bubbleRadius = 0;
    public static int callEncryptionHintDisplayedCount = 0;
    public static boolean chatBubbles = false;
    private static int chatSwipeAction = 0;
    private static boolean configLoaded = false;
    public static ProxyInfo currentProxy = null;
    public static int dayNightThemeSwitchHintCount = 0;
    public static int dayNightWallpaperSwitchHint = 0;
    public static boolean debugVideoQualities = false;
    public static boolean debugViewMetrics = false;
    public static boolean debugWebView = false;
    private static int devicePerformanceClass = 0;
    public static boolean directShare = false;
    public static String directShareHash = null;
    public static boolean disableVoiceAudioEffects = false;
    public static int distanceSystemType = 0;
    public static boolean dontAskManageStorage = false;
    public static boolean drawActionBarShadow = false;
    public static SharedPreferences.Editor editor = null;
    public static int emojiInteractionsHintCount = 0;
    public static int fastScrollHintCount = 0;
    public static boolean fastWallpaperDisabled = false;
    public static int fontSize = 0;
    public static boolean fontSizeIsDefault = false;
    public static boolean forceDisableTabletMode = false;
    public static boolean forceForumTabs = false;
    public static boolean forwardingOptionsHintShown = false;
    public static boolean frameMetricsEnabled = false;
    private static String goodHevcEncoder = null;
    public static boolean hasCameraCache = false;
    public static boolean hasEmailLogin = false;
    private static HashSet<String> hevcEncoderWhitelist = null;
    public static boolean inAppCamera = false;
    public static boolean isFloatingDebugActive = false;
    public static boolean isWaitingForPasscodeEnter = false;
    public static int ivFontSize = 0;
    public static int keepMedia = 0;
    public static int lastKeepMediaCheckTime = 0;
    private static int lastLocalId = 0;
    public static int lastLogsCheckTime = 0;
    public static int lastPauseTime = 0;
    public static long lastUpdateCheckTime = 0;
    public static long lastUptimeMillis = 0;
    private static int legacyDevicePerformanceClass = -1;
    public static LiteMode liteMode;
    private static final Object localIdSync = null;
    public static int lockRecordAudioVideoHint;
    public static int mapPreviewType;
    public static int mediaColumnsCount;
    public static int messageSeenHintCount;
    public static boolean multipleReactionsPromoShowed;
    public static boolean nextMediaTap;
    public static boolean noSoundHintShowed;
    public static boolean noiseSupression;
    public static boolean onlyLocalInstantView;
    private static int overrideDevicePerformanceClass;
    public static String passcodeHash;
    public static long passcodeRetryInMs;
    public static byte[] passcodeSalt;
    public static int passcodeType;
    public static int passportConfigHash;
    private static String passportConfigJson;
    private static HashMap<String, String> passportConfigMap;
    public static boolean pauseMusicOnMedia;
    public static boolean pauseMusicOnRecord;
    public static boolean payByInvoice;
    public static TLRPC.TL_help_appUpdate pendingAppUpdate;
    public static int pendingAppUpdateBuildVersion;
    public static boolean photoHighQualityDefault;
    public static boolean photoLiveDefault;
    public static boolean photoViewerBlur;
    public static boolean playOrderReversed;
    public static ArrayList<ProxyInfo> proxyList;
    private static boolean proxyListLoaded;
    public static boolean proxyRotationEnabled;
    public static int proxyRotationTimeout;
    public static byte[] pushAuthKey;
    public static byte[] pushAuthKeyId;
    public static boolean pushStatSent;
    public static String pushString;
    public static long pushStringGetTimeEnd;
    public static long pushStringGetTimeStart;
    public static String pushStringStatus;
    public static int pushType;
    public static boolean raiseToListen;
    public static boolean raiseToSpeak;
    public static boolean readOnlyStorageDirAlertShowed;
    public static boolean recordViaSco;
    public static int repeatMode;
    public static boolean replyingOptionsHintShown;
    public static boolean roundCamera16to9;
    public static boolean saveIncomingPhotos;
    public static boolean saveStreamMedia;
    public static long scheduledHintSeenAt;
    public static int scheduledHintShows;
    public static long scheduledOrNoSoundHintSeenAt;
    public static int scheduledOrNoSoundHintShows;
    public static String searchEngineCustomURLAutocomplete;
    public static String searchEngineCustomURLQuery;
    public static int searchEngineType;
    public static boolean searchMessagesAsListUsed;
    public static boolean shadowsInSections;
    public static boolean showNotificationsForAllAccounts;
    public static boolean shuffleMusic;
    public static boolean sortContactsByName;
    public static boolean sortFilesByName;
    public static int stealthModeSendMessageConfirm;
    public static boolean stickersReorderingHintUsed;
    public static String storageCacheDir;
    public static int storiesColumnsCount;
    public static boolean storiesIntroShown;
    public static boolean storyReactionsLongPressHint;
    public static boolean streamAllVideo;
    public static boolean streamMedia;
    public static boolean streamMkv;
    public static boolean suggestAnimatedEmoji;
    public static int suggestStickers;
    private static final Object sync = null;
    public static int textSelectionHintShows;
    public static boolean updateStickersOrderOnSend;
    public static Boolean useCamera2Force;
    public static boolean useFaceLock;
    public static boolean useFingerprintLock;
    public static boolean useNewBlur;
    public static boolean useSurfaceInStories;
    public static boolean useSystemBoldFont;
    public static boolean useSystemEmoji;
    public static boolean useThreeLinesLayout;

    public static class BackgroundActivityPrefs {
        private static SharedPreferences prefs;

        /* renamed from: -$$Nest$sfputprefs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4970$$Nest$sfputprefs(SharedPreferences r0) {
            prefs = r0;
        }

        public BackgroundActivityPrefs() {
        }

        public static int getDismissedCount() {
            return prefs.getInt("dismissed_count", 0);
        }

        public static long getLastCheckedBackgroundActivity() {
            return prefs.getLong("last_checked", 0);
        }

        public static void increaseDismissedCount() {
            prefs.edit().putInt("dismissed_count", getDismissedCount() + 1).apply();
        }

        public static void setLastCheckedBackgroundActivity(long r2) {
            prefs.edit().putLong("last_checked", r2).apply();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface PasscodeType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface PerformanceClass {
    }

    public static class ProxyInfo {
        public boolean available;
        public long availableCheckTime;
        public boolean checking;
        public long ping;
        public ProxySettings settings;

        public ProxyInfo(ProxySettings r1) {
            this.settings = r1;
        }

        public static long normalizeAvailableCheckTime(long r4) {
            if (r4 > 0) goto L5;
        L8:
            return 0;
        L5:
            if (r4 > SystemClock.elapsedRealtime()) goto L8;
            return r4;
        }
    }

    public static /* synthetic */ void $r8$lambda$5WZZbls8uHKhzrNqXgCrVdV5Lyk() {
        if (readOnlyStorageDirAlertShowed == true) goto L13;
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.getParentActivity() == null) goto L12;
        storageCacheDir = null;
        saveConfig();
        ImageLoader.getInstance().checkMediaPaths(new SharedConfig$$ExternalSyntheticLambda3());
        readOnlyStorageDirAlertShowed = true;
        AlertDialog.Builder r1 = new AlertDialog.Builder(r0.getParentActivity());
        r1.setTitle(LocaleController.getString(R.string.SdCardError));
        r1.setSubtitle(LocaleController.getString(R.string.SdCardErrorDescription));
        r1.setPositiveButton(LocaleController.getString(R.string.DoNotUseSDCard), new SharedConfig$$ExternalSyntheticLambda4());
        AlertDialog r02 = r1.create();
        r02.setCanceledOnTouchOutside(false);
        r02.show();
        return;
    L12:
        return;
    }

    public static /* synthetic */ void $r8$lambda$6gZCUSeINXv3Os6LV1HVwzdvmRA(int r3) {
        long r0 = r3 - 864000;
        File r32 = AndroidUtilities.getLogsDir();     // Catch: Throwable -> L8
        if (r32 != null) goto L6;
        return;
    L6:
        Utilities.clearDir(r32.getAbsolutePath(), 0, r0, false);     // Catch: Throwable -> L8
    L10:
        SharedPreferences.Editor r33 = MessagesController.getGlobalMainSettings().edit();
        r33.putInt("lastLogsCheckTime", lastLogsCheckTime);
        r33.apply();
        return;
    L8:
        th = move-exception;
        FileLog.e(th);
        goto L10
    }

    /* renamed from: $r8$lambda$AgzM-nIFjDK3Boj0GV34UBf8Qd8, reason: not valid java name */
    public static /* synthetic */ void m4968$r8$lambda$AgzMnIFjDK3Boj0GV34UBf8Qd8(AlertDialog r0, int r1) {
    }

    /* renamed from: $r8$lambda$c1nolnMM8hkF5gEowxjTAWjtK-M, reason: not valid java name */
    public static /* synthetic */ void m4969$r8$lambda$c1nolnMM8hkF5gEowxjTAWjtKM() {
    }

    public static /* synthetic */ void $r8$lambda$z1Fq9lJAhS4a7ooIIjzc5xGSw6k() {
        File r0 = new File(Environment.getExternalStorageDirectory(), "exteraGram");     // Catch: Throwable -> L19
        File r1 = new File(r0, "exteraGram Images");     // Catch: Throwable -> L19
        r1.mkdir();     // Catch: Throwable -> L19
        File r2 = new File(r0, "exteraGram Video");     // Catch: Throwable -> L19
        r2.mkdir();     // Catch: Throwable -> L19
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L13;
        if (r1.isDirectory() == false) goto L9;
        new File(r1, ".nomedia").delete();     // Catch: Throwable -> L19
    L9:
        if (r2.isDirectory() == false) goto L23;
        new File(r2, ".nomedia").delete();     // Catch: Throwable -> L19
        return;
    L23:
        return;
    L13:
        if (r1.isDirectory() == false) goto L16;
        AndroidUtilities.createEmptyFile(new File(r1, ".nomedia"));     // Catch: Throwable -> L19
    L16:
        if (r2.isDirectory() == false) goto L24;
        AndroidUtilities.createEmptyFile(new File(r2, ".nomedia"));     // Catch: Throwable -> L19
        return;
    L24:
        return;
    L19:
        th = move-exception;
        FileLog.e(th);
    }

    static {
        HashSet<String> r0 = new HashSet();
        hevcEncoderWhitelist = r0;
        r0.add("c2.exynos.hevc.encoder");
        hevcEncoderWhitelist.add("OMX.Exynos.HEVC.Encoder".toLowerCase());
        pushType = 2;
        pushString = _UrlKt.FRAGMENT_ENCODE_SET;
        pushStringStatus = _UrlKt.FRAGMENT_ENCODE_SET;
        passcodeHash = _UrlKt.FRAGMENT_ENCODE_SET;
        passcodeSalt = new byte[0];
        autoLockIn = 3600;
        useFingerprintLock = true;
        useFaceLock = true;
        keepMedia = CacheByChatsController.KEEP_MEDIA_ONE_MONTH;
        updateStickersOrderOnSend = true;
        photoViewerBlur = true;
        stealthModeSendMessageConfirm = 2;
        lastLocalId = -210000;
        passportConfigJson = _UrlKt.FRAGMENT_ENCODE_SET;
        sync = new Object();
        localIdSync = new Object();
        mapPreviewType = 2;
        searchEngineType = 0;
        if (Build.VERSION.SDK_INT < 30) goto L5;
        boolean r02 = true;
    L6:
        chatBubbles = r02;
        raiseToSpeak = false;
        raiseToListen = true;
        nextMediaTap = true;
        recordViaSco = false;
        adaptableColorInBrowser = true;
        onlyLocalInstantView = false;
        directShare = true;
        inAppCamera = true;
        roundCamera16to9 = true;
        noSoundHintShowed = false;
        streamMedia = true;
        streamAllVideo = false;
        streamMkv = false;
        saveStreamMedia = true;
        pauseMusicOnRecord = false;
        pauseMusicOnMedia = false;
        showNotificationsForAllAccounts = true;
        debugVideoQualities = false;
        fontSize = 16;
        bubbleRadius = 17;
        ivFontSize = 16;
        mediaColumnsCount = 3;
        storiesColumnsCount = 3;
        fastScrollHintCount = 3;
        LOW_SOC = new int[]{-1775228513, 802464304, 802464333, 802464302, 2067362118, 2067362060, 2067362084, 2067362241, 2067362117, 2067361998, -1853602818};
        loadConfig();
        proxyList = new ArrayList();
        drawActionBarShadow = true;
        return;
    L5:
        r02 = false;
        goto L6
    }

    public SharedConfig() {
    }

    public static boolean allowPreparingHevcPlayers() {
        if (allowPreparingHevcPlayers != null) goto L24;
        int r0 = MediaCodecList.getCodecCount();
        boolean r1 = false;
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r2 >= r0) goto L20;
        MediaCodecInfo r4 = MediaCodecList.getCodecInfoAt(r2);
        if (r4.isEncoder() == true) goto L18;
        int r5 = 0;
    L11:
        if (r5 >= r4.getSupportedTypes().length) goto L18;
        if (r4.getSupportedTypes()[r5].contains("video/hevc") == true) goto L14;
        r5 = r5 + 1;
        goto L11
    L14:
        int r42 = r4.getCapabilitiesForType("video/hevc").getMaxSupportedInstances();
        if (r42 <= r3) goto L18;
        r3 = r42;
    L18:
        r2 = r2 + 1;
        goto L5
    L20:
        if (r3 < 8) goto L22;
        r1 = true;
    L22:
        allowPreparingHevcPlayers = Boolean.valueOf(r1);
    L24:
        return allowPreparingHevcPlayers.booleanValue();
    }

    public static boolean animationsEnabled() {
        if (animationsEnabled != null) goto L6;
        animationsEnabled = Boolean.valueOf(MessagesController.getGlobalMainSettings().getBoolean("view_animations", true));
    L6:
        return animationsEnabled.booleanValue();
    }

    public static int buildVersion() {
        return ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0).versionCode;
    L5:
        e = move-exception;
        FileLog.e(e);
        return 0;
    }

    public static boolean canBlurChat() {
        return true;
    }

    public static boolean chatBlurEnabled() {
        if (canBlurChat() == true) goto L5;
        return false;
    L5:
        if (LiteMode.isEnabled(256) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void checkLogsToDelete() {
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        int r0 = (int) (System.currentTimeMillis() / 1000);
        if (Math.abs(r0 - lastLogsCheckTime) >= 3600) goto L8;
        return;
    L8:
        lastLogsCheckTime = r0;
        Utilities.cacheClearQueue.postRunnable(new SharedConfig$$ExternalSyntheticLambda1(r0));
        return;
    }

    public static boolean checkPasscode(String r7) {
        if (passcodeSalt.length != 0) goto L16;
        boolean r0 = Utilities.MD5(r7).equals(passcodeHash);
        if (r0 == true) goto L18;
    L10:
        return r0;
    L18:
        passcodeSalt = new byte[16];     // Catch: Exception -> L8
        Utilities.random.nextBytes(passcodeSalt);     // Catch: Exception -> L8
        byte[] r72 = r7.getBytes(StandardCharsets.UTF_8);     // Catch: Exception -> L8
        int r3 = r72.length + 32;     // Catch: Exception -> L8
        byte[] r4 = new byte[r3];     // Catch: Exception -> L8
        System.arraycopy(passcodeSalt, 0, r4, 0, 16);     // Catch: Exception -> L8
        System.arraycopy(r72, 0, r4, 16, r72.length);     // Catch: Exception -> L8
        System.arraycopy(passcodeSalt, 0, r4, r72.length + 16, 16);     // Catch: Exception -> L8
        passcodeHash = Utilities.bytesToHex(Utilities.computeSHA256(r4, 0, r3));     // Catch: Exception -> L8
        saveConfig();     // Catch: Exception -> L8
        return r0;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    L16:
        byte[] r73 = r7.getBytes(StandardCharsets.UTF_8);     // Catch: Exception -> L13
        int r02 = r73.length + 32;     // Catch: Exception -> L13
        byte[] r32 = new byte[r02];     // Catch: Exception -> L13
        System.arraycopy(passcodeSalt, 0, r32, 0, 16);     // Catch: Exception -> L13
        System.arraycopy(r73, 0, r32, 16, r73.length);     // Catch: Exception -> L13
        System.arraycopy(passcodeSalt, 0, r32, r73.length + 16, 16);     // Catch: Exception -> L13
        return passcodeHash.equals(Utilities.bytesToHex(Utilities.computeSHA256(r32, 0, r02)));
    L13:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static void checkSaveToGalleryFiles() {
        Utilities.globalQueue.postRunnable(new SharedConfig$$ExternalSyntheticLambda6());
    }

    public static void checkSdCard(File r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (storageCacheDir != null) goto L6;
        return;
    L6:
        if (readOnlyStorageDirAlertShowed == false) goto L9;
        return;
    L9:
        if (r1.getPath().startsWith(storageCacheDir) == false) goto L14;
        AndroidUtilities.runOnUIThread(new SharedConfig$$ExternalSyntheticLambda5());
        return;
    }

    public static void clearConfig() {
        saveIncomingPhotos = false;
        appLocked = false;
        passcodeType = 0;
        passcodeRetryInMs = 0;
        lastUptimeMillis = 0;
        badPasscodeTries = 0;
        passcodeHash = _UrlKt.FRAGMENT_ENCODE_SET;
        passcodeSalt = new byte[0];
        autoLockIn = 3600;
        lastPauseTime = 0;
        useFingerprintLock = true;
        isWaitingForPasscodeEnter = false;
        allowScreenCapture = false;
        textSelectionHintShows = 0;
        scheduledOrNoSoundHintShows = 0;
        scheduledOrNoSoundHintSeenAt = 0;
        scheduledHintShows = 0;
        scheduledHintSeenAt = 0;
        lockRecordAudioVideoHint = 0;
        forwardingOptionsHintShown = false;
        replyingOptionsHintShown = false;
        messageSeenHintCount = 3;
        emojiInteractionsHintCount = 3;
        dayNightThemeSwitchHintCount = 3;
        stealthModeSendMessageConfirm = 2;
        dayNightWallpaperSwitchHint = 0;
        saveConfig();
    }

    public static boolean deviceIsAboveAverage() {
        if (getDevicePerformanceClass() < 1) goto L5;
        return true;
    L5:
        return false;
    }

    public static boolean deviceIsAverage() {
        if (getDevicePerformanceClass() > 1) goto L5;
        return true;
    L5:
        return false;
    }

    public static boolean deviceIsHigh() {
        if (getDevicePerformanceClass() < 2) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean deviceIsLow() {
        if (getDevicePerformanceClass() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean enabledRaiseTo(boolean r1) {
        if (raiseToListen == false) goto L9;
        if (r1 == true) goto L6;
        return true;
    L6:
        if (raiseToSpeak == false) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static String findGoodHevcEncoder() {
        if (goodHevcEncoder != null) goto L24;
        int r0 = MediaCodecList.getCodecCount();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L22;
        MediaCodecInfo r3 = MediaCodecList.getCodecInfoAt(r2);
        if (r3.isEncoder() == false) goto L21;
        int r4 = 0;
    L11:
        if (r4 >= r3.getSupportedTypes().length) goto L21;
        if (r3.getSupportedTypes()[r4].contains("video/hevc") == false) goto L20;
        if (r3.isHardwareAccelerated() == false) goto L20;
        if (isWhitelisted(r3) == false) goto L20;
        String r02 = r3.getName();
        goodHevcEncoder = r02;
        return r02;
    L20:
        r4 = r4 + 1;
    L21:
        r2 = r2 + 1;
        goto L5
    L22:
        goodHevcEncoder = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        if (TextUtils.isEmpty(goodHevcEncoder) == false) goto L28;
        return null;
    L28:
        return goodHevcEncoder;
    }

    public static void forwardingOptionsHintHintShowed() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        forwardingOptionsHintShown = true;
        r0.putBoolean("forwardingOptionsHintShown", true);
        r0.apply();
    }

    public static int getChatSwipeAction(int r0) {
        int r02 = chatSwipeAction;
        if (r02 < 0) goto L5;
        return r02;
    L5:
        return 5;
    }

    public static HashMap<String, String> getCountryLangs() {
        if (passportConfigMap != null) goto L13;
        passportConfigMap = new HashMap();
        JSONObject r0 = new JSONObject(passportConfigJson);     // Catch: Throwable -> L10
        Iterator<String> r1 = r0.keys();     // Catch: Throwable -> L10
    L6:
        if (r1.hasNext() == false) goto L13;
        String r2 = r1.next();     // Catch: Throwable -> L10
        passportConfigMap.put(r2.toUpperCase(), r0.getString(r2).toUpperCase());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        FileLog.e(th);
    L13:
        return passportConfigMap;
    }

    public static int getDevicePerformanceClass() {
        int r0 = overrideDevicePerformanceClass;
        if (r0 == (-1)) goto L6;
        return r0;
    L6:
        if (devicePerformanceClass != (-1)) goto L9;
        devicePerformanceClass = measureDevicePerformanceClass();
    L9:
        return devicePerformanceClass;
    }

    public static int getLastLocalId() {
        Object r0 = localIdSync;
        monitor-enter(r0);
        int r1 = lastLocalId;     // Catch: Throwable -> L7
        lastLocalId = r1 - 1;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Deprecated
    public static int getLegacyDevicePerformanceClass() {
        if (legacyDevicePerformanceClass != (-1)) goto L43;
        int r0 = Build.VERSION.SDK_INT;
        int r2 = ConnectionsManager.CPU_COUNT;
        int r3 = ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getMemoryClass();
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
    L6:
        if (r5 >= r2) goto L12;
        RandomAccessFile r9 = new RandomAccessFile(String.format(Locale.ENGLISH, "/sys/devices/system/cpu/cpu%d/cpufreq/cpuinfo_max_freq", new Object[]{Integer.valueOf(r5)}), "r");     // Catch: Throwable -> L44
        String r8 = r9.readLine();     // Catch: Throwable -> L44
        if (r8 == null) goto L10;
        r7 = r7 + (Utilities.parseInt(r8) / 1000);     // Catch: Throwable -> L44
        r6 = r6 + 1;     // Catch: Throwable -> L44
    L10:
        r9.close();     // Catch: Throwable -> L44
    L11:
        r5 = r5 + 1;
        goto L6
    L12:
        if (r6 != 0) goto L14;
        int r52 = -1;
    L16:
        if (r2 > 2) goto L18;
    L41:
        legacyDevicePerformanceClass = 0;
        goto L43
    L18:
        if (r3 <= 100) goto L41;
        if (r2 > 4) goto L24;
        if (r52 == (-1)) goto L24;
        if (r52 <= 1250) goto L41;
    L24:
        if (r2 > 4) goto L33;
        if (r52 > 1300) goto L33;
        if (r3 > 128) goto L33;
        if (r0 <= 24) goto L41;
    L33:
        if (r2 >= 8) goto L35;
    L40:
        legacyDevicePerformanceClass = 1;
        goto L43
    L35:
        if (r3 <= 160) goto L40;
        if (r52 != (-1)) goto L38;
    L39:
        legacyDevicePerformanceClass = 2;
        goto L43
    L38:
        if (r52 <= 2050) goto L40;
    L14:
        r52 = (int) Math.ceil(r7 / r6);
    L43:
        return legacyDevicePerformanceClass;
    }

    public static SharedPreferences getPreferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0);
    }

    public static void increaseBadPasscodeTries() {
        int r0 = badPasscodeTries + 1;
        badPasscodeTries = r0;
        if (r0 < 3) goto L20;
        if (r0 != 3) goto L6;
        passcodeRetryInMs = 5000;
    L19:
        lastUptimeMillis = SystemClock.elapsedRealtime();
        goto L20
    L6:
        if (r0 != 4) goto L8;
        passcodeRetryInMs = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        goto L19
    L8:
        if (r0 != 5) goto L10;
        passcodeRetryInMs = 15000;
        goto L19
    L10:
        if (r0 != 6) goto L12;
        passcodeRetryInMs = 20000;
        goto L19
    L12:
        if (r0 == 7) goto L14;
        passcodeRetryInMs = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
        goto L19
    L14:
        passcodeRetryInMs = 25000;
    L20:
        saveConfig();
    }

    public static void increaseDayNightWallpaperSiwtchHint() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        int r1 = dayNightWallpaperSwitchHint + 1;
        dayNightWallpaperSwitchHint = r1;
        r0.putInt("dayNightWallpaperSwitchHint", r1);
        r0.apply();
    }

    public static void increaseLockRecordAudioVideoHintShowed() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        int r1 = lockRecordAudioVideoHint + 1;
        lockRecordAudioVideoHint = r1;
        r0.putInt("lockRecordAudioVideoHint", r1);
        r0.apply();
    }

    public static void increaseScheduledHintShowed() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        scheduledHintSeenAt = System.currentTimeMillis();
        int r1 = scheduledHintShows + 1;
        scheduledHintShows = r1;
        r0.putInt("scheduledHintShows", r1);
        r0.putLong("scheduledHintSeenAt", scheduledHintSeenAt);
        r0.apply();
    }

    public static void increaseScheduledOrNoSoundHintShowed() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        scheduledOrNoSoundHintSeenAt = System.currentTimeMillis();
        int r1 = scheduledOrNoSoundHintShows + 1;
        scheduledOrNoSoundHintShows = r1;
        r0.putInt("scheduledOrNoSoundHintShows", r1);
        r0.putLong("scheduledOrNoSoundHintSeenAt", scheduledOrNoSoundHintSeenAt);
        r0.apply();
    }

    public static void increaseTextSelectionHintShowed() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        int r1 = textSelectionHintShows + 1;
        textSelectionHintShows = r1;
        r0.putInt("textSelectionHintShows", r1);
        r0.apply();
    }

    public static void incrementCallEncryptionHintDisplayed(int r2) {
        callEncryptionHintDisplayedCount += r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("callEncryptionHintDisplayedCount", callEncryptionHintDisplayedCount);
        r22.apply();
    }

    public static boolean isAppUpdateAvailable() {
        TLRPC.TL_help_appUpdate r0 = pendingAppUpdate;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.document == null) goto L19;
        int r02 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0).versionCode;     // Catch: Exception -> L9
    L12:
        if (pendingAppUpdateBuildVersion != r02) goto L18;
        return true;
    L18:
        return false;
    L9:
        e = move-exception;
        FileLog.e(e);
        r02 = buildVersion();
        goto L12
    L19:
        return false;
    }

    public static boolean isAutoplayGifs() {
        return LiteMode.isEnabled(2048);
    }

    public static boolean isAutoplayVideo() {
        return LiteMode.isEnabled(1024);
    }

    public static boolean isPassportConfigLoaded() {
        if (passportConfigMap == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isProxyEnabled() {
        if (MessagesController.getGlobalMainSettings().getBoolean("proxy_enabled", false) == true) goto L5;
    L8:
        return false;
    L5:
        if (currentProxy == null) goto L8;
        return true;
    }

    public static boolean isSecretMapPreviewSet() {
        return MessagesController.getGlobalMainSettings().contains("mapPreviewType");
    }

    public static boolean isUsingCamera2(int r1) {
        Boolean r0 = useCamera2Force;
        if (r0 != null) goto L11;
        if (MessagesController.getInstance(r1).androidDisableRoundCamera2 == true) goto L8;
        return true;
    L8:
        return false;
    L11:
        return r0.booleanValue();
    }

    private static boolean isWhitelisted(MediaCodecInfo r1) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L7;
        return true;
    L7:
        return hevcEncoderWhitelist.contains(r1.getName().toLowerCase());
    }

    public static void loadConfig() {
        Object r0 = sync;
        monitor-enter(r0);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (configLoaded == false) goto L7;
    L82:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L7:
        if (ApplicationLoader.applicationContext == null) goto L82;
        BackgroundActivityPrefs.m4970$$Nest$sfputprefs(ApplicationLoader.applicationContext.getSharedPreferences("background_activity", 0));     // Catch: Throwable -> L12
        migrateLegacyWebProxyType();     // Catch: Throwable -> L12
        SharedPreferences r1 = ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0);     // Catch: Throwable -> L12
        editor = r1.edit();     // Catch: Throwable -> L12
        saveIncomingPhotos = r1.getBoolean("saveIncomingPhotos", false);     // Catch: Throwable -> L12
        passcodeHash = r1.getString("passcodeHash1", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L12
        appLocked = r1.getBoolean("appLocked", false);     // Catch: Throwable -> L12
        passcodeType = r1.getInt("passcodeType", 0);     // Catch: Throwable -> L12
        passcodeRetryInMs = r1.getLong("passcodeRetryInMs", 0);     // Catch: Throwable -> L12
        lastUptimeMillis = r1.getLong("lastUptimeMillis", 0);     // Catch: Throwable -> L12
        badPasscodeTries = r1.getInt("badPasscodeTries", 0);     // Catch: Throwable -> L12
        autoLockIn = r1.getInt("autoLockIn", 3600);     // Catch: Throwable -> L12
        lastPauseTime = r1.getInt("lastPauseTime", 0);     // Catch: Throwable -> L12
        useFingerprintLock = r1.getBoolean("useFingerprint", true);     // Catch: Throwable -> L12
        allowScreenCapture = r1.getBoolean("allowScreenCapture", false);     // Catch: Throwable -> L12
        lastLocalId = r1.getInt("lastLocalId", -210000);     // Catch: Throwable -> L12
        pushString = r1.getString("pushString2", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L12
        pushType = r1.getInt("pushType", 2);     // Catch: Throwable -> L12
        pushStatSent = r1.getBoolean("pushStatSent", false);     // Catch: Throwable -> L12
        passportConfigJson = r1.getString("passportConfigJson", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L12
        passportConfigHash = r1.getInt("passportConfigHash", 0);     // Catch: Throwable -> L12
        Boolean r8 = null;
        storageCacheDir = r1.getString("storageCacheDir", null);     // Catch: Throwable -> L12
        proxyRotationEnabled = r1.getBoolean("proxyRotationEnabled", false);     // Catch: Throwable -> L12
        proxyRotationTimeout = r1.getInt("proxyRotationTimeout", 1);     // Catch: Throwable -> L12
        String r2 = r1.getString("pushAuthKey", null);     // Catch: Throwable -> L12
        if (TextUtils.isEmpty(r2) == true) goto L15;
        pushAuthKey = Base64.decode(r2, 0);     // Catch: Throwable -> L12
    L15:
        if (passcodeHash.length() > 0) goto L17;
    L19:
        String r22 = r1.getString("passcodeSalt", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L12
        if (r22.length() <= 0) goto L22;
        passcodeSalt = Base64.decode(r22, 0);     // Catch: Throwable -> L12
    L23:
        lastUpdateCheckTime = r1.getLong("appUpdateCheckTime", 0);     // Catch: Throwable -> L12
        String r23 = r1.getString("appUpdate", null);     // Catch: Throwable -> L12 Exception -> L29
        if (r23 == null) goto L32;
        pendingAppUpdateBuildVersion = r1.getInt("appUpdateBuild", buildVersion());     // Catch: Throwable -> L12 Exception -> L29
        byte[] r12 = Base64.decode(r23, 0);     // Catch: Throwable -> L12 Exception -> L29
        if (r12 == null) goto L32;
        SerializedData r24 = new SerializedData(r12);     // Catch: Throwable -> L12 Exception -> L29
        pendingAppUpdate = (TLRPC.TL_help_appUpdate) TLRPC.help_AppUpdate.TLdeserialize(r24, r24.readInt32(false), false);     // Catch: Throwable -> L12 Exception -> L29
        r24.cleanup();     // Catch: Throwable -> L12 Exception -> L29
    L32:
        if (pendingAppUpdate != null) goto L88;
    L56:
        SharedPreferences r13 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);     // Catch: Throwable -> L12
        SaveToGallerySettingsHelper.load(r13);     // Catch: Throwable -> L12
        mapPreviewType = r13.getInt("mapPreviewType", 2);     // Catch: Throwable -> L12
        searchEngineType = r13.getInt("searchEngineType", 0);     // Catch: Throwable -> L12
        raiseToListen = r13.getBoolean("raise_to_listen", true);     // Catch: Throwable -> L12
        raiseToSpeak = r13.getBoolean("raise_to_speak", false);     // Catch: Throwable -> L12
        nextMediaTap = r13.getBoolean("next_media_on_tap", true);     // Catch: Throwable -> L12
        recordViaSco = r13.getBoolean("record_via_sco", false);     // Catch: Throwable -> L12
        adaptableColorInBrowser = r13.getBoolean("adaptableBrowser", false);     // Catch: Throwable -> L12
        onlyLocalInstantView = r13.getBoolean("onlyLocalInstantView", BuildVars.DEBUG_PRIVATE_VERSION);     // Catch: Throwable -> L12
        directShare = r13.getBoolean("direct_share", true);     // Catch: Throwable -> L12
        boolean r25 = r13.getBoolean("shuffleMusic", false);     // Catch: Throwable -> L12
        shuffleMusic = r25;     // Catch: Throwable -> L12
        if (r25 == false) goto L59;
    L61:
        boolean r26 = false;
    L62:
        playOrderReversed = r26;     // Catch: Throwable -> L12
        inAppCamera = r13.getBoolean("inappCamera", true);     // Catch: Throwable -> L12
        hasCameraCache = r13.contains("cameraCache");     // Catch: Throwable -> L12
        roundCamera16to9 = true;     // Catch: Throwable -> L12
        repeatMode = r13.getInt("repeatMode", 0);     // Catch: Throwable -> L12
        if (AndroidUtilities.isTablet() == true) goto L65;
    L67:
        int r9 = 16;
    L68:
        fontSize = r13.getInt("fons_size", r9);     // Catch: Throwable -> L12
        fontSizeIsDefault = !r13.contains("fons_size");     // Catch: Throwable -> L12
        bubbleRadius = r13.getInt("bubbleRadius", 17);     // Catch: Throwable -> L12
        ivFontSize = r13.getInt("iv_font_size", fontSize);     // Catch: Throwable -> L12
        allowBigEmoji = r13.getBoolean("allowBigEmoji", true);     // Catch: Throwable -> L12
        useSystemEmoji = r13.getBoolean("useSystemEmoji", false);     // Catch: Throwable -> L12
        useSystemBoldFont = r13.getBoolean("useSystemBoldFont", false);     // Catch: Throwable -> L12
        forceForumTabs = r13.getBoolean("forceForumTabs", false);     // Catch: Throwable -> L12
        fastWallpaperDisabled = r13.getBoolean("fastWallpaperDisabled", false);     // Catch: Throwable -> L12
        frameMetricsEnabled = r13.getBoolean("frameMetricsEnabled", false);     // Catch: Throwable -> L12
        if (useSystemBoldFont == false) goto L71;
        AndroidUtilities.mediumTypeface = null;     // Catch: Throwable -> L12
    L71:
        streamMedia = r13.getBoolean("streamMedia", true);     // Catch: Throwable -> L12
        saveStreamMedia = r13.getBoolean("saveStreamMedia", true);     // Catch: Throwable -> L12
        pauseMusicOnRecord = r13.getBoolean("pauseMusicOnRecord", true);     // Catch: Throwable -> L12
        pauseMusicOnMedia = r13.getBoolean("pauseMusicOnMedia", true);     // Catch: Throwable -> L12
        forceDisableTabletMode = r13.getBoolean("forceDisableTabletMode", false);     // Catch: Throwable -> L12
        streamAllVideo = r13.getBoolean("streamAllVideo", BuildVars.DEBUG_VERSION);     // Catch: Throwable -> L12
        streamMkv = r13.getBoolean("streamMkv", false);     // Catch: Throwable -> L12
        suggestStickers = r13.getInt("suggestStickers", 0);     // Catch: Throwable -> L12
        suggestAnimatedEmoji = r13.getBoolean("suggestAnimatedEmoji", true);     // Catch: Throwable -> L12
        overrideDevicePerformanceClass = r13.getInt("overrideDevicePerformanceClass", -1);     // Catch: Throwable -> L12
        devicePerformanceClass = r13.getInt("devicePerformanceClass", -1);     // Catch: Throwable -> L12
        sortContactsByName = r13.getBoolean("sortContactsByName", false);     // Catch: Throwable -> L12
        sortFilesByName = r13.getBoolean("sortFilesByName", false);     // Catch: Throwable -> L12
        noSoundHintShowed = r13.getBoolean("noSoundHintShowed", false);     // Catch: Throwable -> L12
        directShareHash = r13.getString("directShareHash2", null);     // Catch: Throwable -> L12
        useThreeLinesLayout = r13.getBoolean("useThreeLinesLayout", false);     // Catch: Throwable -> L12
        archiveHidden = r13.getBoolean("archiveHidden", false);     // Catch: Throwable -> L12
        distanceSystemType = r13.getInt("distanceSystemType", 0);     // Catch: Throwable -> L12
        keepMedia = r13.getInt("keep_media", CacheByChatsController.KEEP_MEDIA_ONE_MONTH);     // Catch: Throwable -> L12
        debugWebView = r13.getBoolean("debugWebView", false);     // Catch: Throwable -> L12
        lastKeepMediaCheckTime = r13.getInt("lastKeepMediaCheckTime", 0);     // Catch: Throwable -> L12
        lastLogsCheckTime = r13.getInt("lastLogsCheckTime", 0);     // Catch: Throwable -> L12
        searchMessagesAsListUsed = r13.getBoolean("searchMessagesAsListUsed", false);     // Catch: Throwable -> L12
        stickersReorderingHintUsed = r13.getBoolean("stickersReorderingHintUsed", false);     // Catch: Throwable -> L12
        storyReactionsLongPressHint = r13.getBoolean("storyReactionsLongPressHint", false);     // Catch: Throwable -> L12
        storiesIntroShown = r13.getBoolean("storiesIntroShown", false);     // Catch: Throwable -> L12
        textSelectionHintShows = r13.getInt("textSelectionHintShows", 0);     // Catch: Throwable -> L12
        scheduledOrNoSoundHintShows = r13.getInt("scheduledOrNoSoundHintShows", 0);     // Catch: Throwable -> L12
        scheduledOrNoSoundHintSeenAt = r13.getLong("scheduledOrNoSoundHintSeenAt", 0);     // Catch: Throwable -> L12
        scheduledHintShows = r13.getInt("scheduledHintShows", 0);     // Catch: Throwable -> L12
        scheduledHintSeenAt = r13.getLong("scheduledHintSeenAt", 0);     // Catch: Throwable -> L12
        forwardingOptionsHintShown = r13.getBoolean("forwardingOptionsHintShown", false);     // Catch: Throwable -> L12
        replyingOptionsHintShown = r13.getBoolean("replyingOptionsHintShown", false);     // Catch: Throwable -> L12
        lockRecordAudioVideoHint = r13.getInt("lockRecordAudioVideoHint", 0);     // Catch: Throwable -> L12
        disableVoiceAudioEffects = r13.getBoolean("disableVoiceAudioEffects", false);     // Catch: Throwable -> L12
        noiseSupression = r13.getBoolean("noiseSupression", false);     // Catch: Throwable -> L12
        chatSwipeAction = r13.getInt("ChatSwipeAction", -1);     // Catch: Throwable -> L12
        messageSeenHintCount = r13.getInt("messageSeenCount", 3);     // Catch: Throwable -> L12
        emojiInteractionsHintCount = r13.getInt("emojiInteractionsHintCount", 3);     // Catch: Throwable -> L12
        dayNightThemeSwitchHintCount = r13.getInt("dayNightThemeSwitchHintCount", 3);     // Catch: Throwable -> L12
        stealthModeSendMessageConfirm = r13.getInt("stealthModeSendMessageConfirm", 2);     // Catch: Throwable -> L12
        mediaColumnsCount = r13.getInt("mediaColumnsCount", 3);     // Catch: Throwable -> L12
        storiesColumnsCount = r13.getInt("storiesColumnsCount", 3);     // Catch: Throwable -> L12
        fastScrollHintCount = r13.getInt("fastScrollHintCount", 3);     // Catch: Throwable -> L12
        dontAskManageStorage = r13.getBoolean("dontAskManageStorage", false);     // Catch: Throwable -> L12
        hasEmailLogin = r13.getBoolean("hasEmailLogin", false);     // Catch: Throwable -> L12
        isFloatingDebugActive = r13.getBoolean("floatingDebugActive", false);     // Catch: Throwable -> L12
        updateStickersOrderOnSend = r13.getBoolean("updateStickersOrderOnSend", true);     // Catch: Throwable -> L12
        dayNightWallpaperSwitchHint = r13.getInt("dayNightWallpaperSwitchHint", 0);     // Catch: Throwable -> L12
        bigCameraForRound = r13.getBoolean("bigCameraForRound", false);     // Catch: Throwable -> L12
        useNewBlur = r13.getBoolean("useNewBlur", true);     // Catch: Throwable -> L12
        if (r13.contains("useCamera2Force_2") == false) goto L75;
        r8 = Boolean.valueOf(r13.getBoolean("useCamera2Force_2", false));     // Catch: Throwable -> L12
    L75:
        useCamera2Force = r8;     // Catch: Throwable -> L12
        if (Build.VERSION.SDK_INT < 30) goto L78;
        boolean r4 = true;
    L79:
        useSurfaceInStories = r13.getBoolean("useSurfaceInStories", r4);     // Catch: Throwable -> L12
        payByInvoice = r13.getBoolean("payByInvoice", false);     // Catch: Throwable -> L12
        photoViewerBlur = r13.getBoolean("photoViewerBlur", true);     // Catch: Throwable -> L12
        multipleReactionsPromoShowed = r13.getBoolean("multipleReactionsPromoShowed", false);     // Catch: Throwable -> L12
        callEncryptionHintDisplayedCount = r13.getInt("callEncryptionHintDisplayedCount", 0);     // Catch: Throwable -> L12
        debugVideoQualities = r13.getBoolean("debugVideoQualities", false);     // Catch: Throwable -> L12
        shadowsInSections = r13.getBoolean("shadowsInSections", false);     // Catch: Throwable -> L12
        debugViewMetrics = r13.getBoolean("debugViewMetrics", false);     // Catch: Throwable -> L12
        photoHighQualityDefault = r13.getBoolean("photoHighQualityDefault", false);     // Catch: Throwable -> L12
        photoLiveDefault = r13.getBoolean("photoLiveDefault", false);     // Catch: Throwable -> L12
        loadDebugConfig(r13);     // Catch: Throwable -> L12
        showNotificationsForAllAccounts = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).getBoolean("AllAccounts", true);     // Catch: Throwable -> L12
        configLoaded = true;     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L78:
        r4 = false;
        goto L79
    L65:
        if (AndroidUtilities.isFold() == true) goto L67;
        r9 = 18;
        goto L68
    L59:
        if (r13.getBoolean("playOrderReversed", false) == false) goto L61;
        r26 = true;
        goto L62
    L88:
        PackageInfo r14 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Throwable -> L12 Exception -> L38
        int r27 = r14.versionCode;     // Catch: Throwable -> L12 Exception -> L38
        String r15 = r14.versionName;     // Catch: Throwable -> L12 Exception -> L36
    L41:
        if (r27 != 0) goto L43;
        r27 = buildVersion();     // Catch: Throwable -> L12 Exception -> L29
    L43:
        if (r15 != null) goto L46;
        r15 = BuildVars.BUILD_VERSION_STRING;     // Catch: Throwable -> L12 Exception -> L29
    L46:
        if (pendingAppUpdateBuildVersion != r27) goto L53;
        String r28 = pendingAppUpdate.version;     // Catch: Throwable -> L12 Exception -> L29
        if (r28 == null) goto L53;
        if (r15.compareTo(r28) >= 0) goto L53;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L56;
    L53:
        pendingAppUpdate = null;     // Catch: Throwable -> L12 Exception -> L29
        AndroidUtilities.runOnUIThread(new SharedConfig$$ExternalSyntheticLambda2());     // Catch: Throwable -> L12 Exception -> L29
    L36:
        e = e;
    L40:
        FileLog.e(e);     // Catch: Throwable -> L12 Exception -> L29
        r15 = null;
    L38:
        e = e;
        r27 = 0;
    L29:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L12
        goto L56
    L22:
        passcodeSalt = new byte[0];     // Catch: Throwable -> L12
        goto L23
    L17:
        if (lastPauseTime != 0) goto L19;
        lastPauseTime = (int) ((SystemClock.elapsedRealtime() / 1000) - 600);     // Catch: Throwable -> L12
        goto L19
    }

    private static void loadDebugConfig(SharedPreferences r2) {
        drawActionBarShadow = r2.getBoolean("drawActionBarShadow", true);
    }

    public static void loadProxyList() {
        if (proxyListLoaded == false) goto L5;
        return;
    L5:
        proxyListLoaded = true;
        ProxyController.getInstance().loadProxyList();
    }

    public static boolean loopStickers() {
        return LiteMode.isEnabled(2);
    }

    public static int measureDevicePerformanceClass() {
        int r0 = Build.VERSION.SDK_INT;
        int r1 = ConnectionsManager.CPU_COUNT;
        int r2 = ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getMemoryClass();
        int r5 = 0;
        if (r0 < 31) goto L13;
        String r4 = SharedConfig$$ExternalSyntheticApiModelOutline0.m();
        if (r4 == null) goto L13;
        int r42 = r4.toUpperCase().hashCode();
        int r6 = 0;
    L7:
        int[] r7 = LOW_SOC;
        if (r6 >= r7.length) goto L13;
        if (r7[r6] == r42) goto L11;
        r6 = r6 + 1;
        goto L7
    L11:
        return 0;
    L13:
        int r43 = 0;
        int r62 = 0;
        int r72 = 0;
    L15:
        if (r43 >= r1) goto L22;
        RandomAccessFile r9 = new RandomAccessFile(String.format(Locale.ENGLISH, "/sys/devices/system/cpu/cpu%d/cpufreq/cpuinfo_max_freq", new Object[]{Integer.valueOf(r43)}), "r");     // Catch: Throwable -> L63
        String r8 = r9.readLine();     // Catch: Throwable -> L63
        if (r8 == null) goto L19;
        r72 = r72 + (Utilities.parseInt(r8) / 1000);     // Catch: Throwable -> L63
        r62 = r62 + 1;     // Catch: Throwable -> L63
    L19:
        r9.close();     // Catch: Throwable -> L63
    L20:
        r43 = r43 + 1;
        goto L15
    L22:
        if (r62 != 0) goto L24;
        int r63 = -1;
    L66:
        ActivityManager.MemoryInfo r73 = new ActivityManager.MemoryInfo();     // Catch: Exception -> L28
        ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getMemoryInfo(r73);     // Catch: Exception -> L28
        long r11 = r73.totalMem;     // Catch: Exception -> L28
    L30:
        if (r1 <= 2) goto L60;
        if (r2 <= 100) goto L60;
        if (r1 > 4) goto L38;
        if (r63 == (-1)) goto L38;
        if (r63 <= 1250) goto L60;
    L38:
        if (r1 > 4) goto L46;
        if (r63 > 1300) goto L46;
        if (r2 > 128) goto L46;
        if (r0 <= 24) goto L60;
    L46:
        if (r11 == (-1)) goto L51;
        if (r11 < 2147483648L) goto L60;
    L51:
        if (r1 >= 8) goto L53;
    L58:
        r5 = 1;
        goto L60
    L53:
        if (r2 <= 160) goto L58;
        if (r63 != (-1)) goto L56;
    L57:
        r5 = 2;
        goto L60
    L56:
        if (r63 <= 2055) goto L58;
    L60:
        if (BuildVars.LOGS_ENABLED == false) goto L62;
        FileLog.d("device performance info selected_class = " + r5 + " (cpu_count = " + r1 + ", freq = " + r63 + ", memoryClass = " + r2 + ", android version " + r0 + ", manufacture " + Build.MANUFACTURER + ", screenRefreshRate=" + AndroidUtilities.screenRefreshRate + ", screenMaxRefreshRate=" + AndroidUtilities.screenMaxRefreshRate + ")");
    L62:
        return r5;
    L28:
        r11 = -1;
        goto L30
    L24:
        r63 = (int) Math.ceil(r72 / r62);
        goto L66
    }

    private static void migrateLegacyWebProxyType() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        if (r0.contains("proxy_type") == false) goto L5;
        return;
    L5:
        if (r0.getBoolean("proxy_web", false) == false) goto L10;
        r0.edit().putInt("proxy_type", ProxySettings.typeToInt(ProxySettings.Type.WEB)).remove("proxy_web").apply();
        return;
    }

    public static void overrideDevicePerformanceClass(int r2) {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        overrideDevicePerformanceClass = r2;
        r0.putInt("overrideDevicePerformanceClass", r2).remove("lite_mode").apply();
        if (liteMode == null) goto L6;
        LiteMode.loadPreference();
        return;
    }

    public static String performanceClassName(int r1) {
        if (r1 != 0) goto L4;
        return "LOW";
    L4:
        if (r1 != 1) goto L6;
        return "AVERAGE";
    L6:
        if (r1 == 2) goto L9;
        return "UNKNOWN";
    L9:
        return "HIGH";
    }

    public static void reloadConfig() {
        configLoaded = false;
        loadConfig();
    }

    public static void removeLockRecordAudioVideoHint() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putInt("lockRecordAudioVideoHint", 3);
        r0.apply();
    }

    public static void removeScheduledHint() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putInt("scheduledHintShows", 3);
        r0.apply();
    }

    public static void removeScheduledOrNoSoundHint() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putInt("scheduledOrNoSoundHintShows", 3);
        r0.apply();
    }

    public static void removeTextSelectionHint() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putInt("textSelectionHintShows", 3);
        r0.apply();
    }

    public static void replyingOptionsHintHintShowed() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        replyingOptionsHintShown = true;
        r0.putBoolean("replyingOptionsHintShown", true);
        r0.apply();
    }

    public static void saveConfig() {
        Object r0 = sync;
        monitor-enter(r0);
        SharedPreferences.Editor r1 = ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0).edit();     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("saveIncomingPhotos", saveIncomingPhotos);     // Catch: Throwable -> L7 Exception -> L9
        r1.putString("passcodeHash1", passcodeHash);     // Catch: Throwable -> L7 Exception -> L9
        byte[] r4 = passcodeSalt;     // Catch: Throwable -> L7 Exception -> L9
        if (r4.length <= 0) goto L11;
        String r42 = Base64.encodeToString(r4, 0);     // Catch: Throwable -> L7 Exception -> L9
    L12:
        r1.putString("passcodeSalt", r42);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("appLocked", appLocked);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("passcodeType", passcodeType);     // Catch: Throwable -> L7 Exception -> L9
        r1.putLong("passcodeRetryInMs", passcodeRetryInMs);     // Catch: Throwable -> L7 Exception -> L9
        r1.putLong("lastUptimeMillis", lastUptimeMillis);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("badPasscodeTries", badPasscodeTries);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("autoLockIn", autoLockIn);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("lastPauseTime", lastPauseTime);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("useFingerprint", useFingerprintLock);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("allowScreenCapture", allowScreenCapture);     // Catch: Throwable -> L7 Exception -> L9
        r1.putString("pushString2", pushString);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("pushType", pushType);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("pushStatSent", pushStatSent);     // Catch: Throwable -> L7 Exception -> L9
        byte[] r43 = pushAuthKey;     // Catch: Throwable -> L7 Exception -> L9
        if (r43 == null) goto L15;
        String r44 = Base64.encodeToString(r43, 0);     // Catch: Throwable -> L7 Exception -> L9
    L16:
        r1.putString("pushAuthKey", r44);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("lastLocalId", lastLocalId);     // Catch: Throwable -> L7 Exception -> L9
        r1.putString("passportConfigJson", passportConfigJson);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("passportConfigHash", passportConfigHash);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("sortContactsByName", sortContactsByName);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("sortFilesByName", sortFilesByName);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("textSelectionHintShows", textSelectionHintShows);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("scheduledOrNoSoundHintShows", scheduledOrNoSoundHintShows);     // Catch: Throwable -> L7 Exception -> L9
        r1.putLong("scheduledOrNoSoundHintSeenAt", scheduledOrNoSoundHintSeenAt);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("scheduledHintShows", scheduledHintShows);     // Catch: Throwable -> L7 Exception -> L9
        r1.putLong("scheduledHintSeenAt", scheduledHintSeenAt);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("forwardingOptionsHintShown", forwardingOptionsHintShown);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("replyingOptionsHintShown", replyingOptionsHintShown);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("lockRecordAudioVideoHint", lockRecordAudioVideoHint);     // Catch: Throwable -> L7 Exception -> L9
        if (TextUtils.isEmpty(storageCacheDir) == true) goto L19;
        String r45 = storageCacheDir;     // Catch: Throwable -> L7 Exception -> L9
    L20:
        r1.putString("storageCacheDir", r45);     // Catch: Throwable -> L7 Exception -> L9
        r1.putBoolean("proxyRotationEnabled", proxyRotationEnabled);     // Catch: Throwable -> L7 Exception -> L9
        r1.putInt("proxyRotationTimeout", proxyRotationTimeout);     // Catch: Throwable -> L7 Exception -> L9
        TLRPC.TL_help_appUpdate r2 = pendingAppUpdate;     // Catch: Throwable -> L7 Exception -> L9
        if (r2 != null) goto L33;
        r1.remove("appUpdate");     // Catch: Throwable -> L7 Exception -> L9
    L25:
        r1.putLong("appUpdateCheckTime", lastUpdateCheckTime);     // Catch: Throwable -> L7 Exception -> L9
        r1.apply();     // Catch: Throwable -> L7 Exception -> L9
        SharedPreferences.Editor r12 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();     // Catch: Throwable -> L7 Exception -> L9
        r12.putBoolean("hasEmailLogin", hasEmailLogin);     // Catch: Throwable -> L7 Exception -> L9
        r12.putBoolean("floatingDebugActive", isFloatingDebugActive);     // Catch: Throwable -> L7 Exception -> L9
        r12.putBoolean("record_via_sco", recordViaSco);     // Catch: Throwable -> L7 Exception -> L9
        r12.apply();     // Catch: Throwable -> L7 Exception -> L9
    L28:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L33:
        SerializedData r46 = new SerializedData(r2.getObjectSize());     // Catch: Throwable -> L7 Exception -> L32
        pendingAppUpdate.serializeToStream(r46);     // Catch: Throwable -> L7 Exception -> L32
        r1.putString("appUpdate", Base64.encodeToString(r46.toByteArray(), 0));     // Catch: Throwable -> L7 Exception -> L32
        r1.putInt("appUpdateBuild", pendingAppUpdateBuildVersion);     // Catch: Throwable -> L7 Exception -> L32
        r46.cleanup();     // Catch: Throwable -> L7 Exception -> L32
        goto L25
    L19:
        r45 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    L15:
        r44 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    L11:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        th = move-exception;
        throw th;
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        goto L28
    }

    public static void saveDebugConfig() {
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putBoolean("drawActionBarShadow", drawActionBarShadow);
    }

    public static void setAnimationsEnabled(boolean r0) {
        animationsEnabled = Boolean.valueOf(r0);
    }

    public static void setDistanceSystemType(int r2) {
        distanceSystemType = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("distanceSystemType", distanceSystemType);
        r22.apply();
        LocaleController.resetImperialSystemType();
    }

    public static void setFastScrollHintCount(int r2) {
        if (fastScrollHintCount == r2) goto L6;
        fastScrollHintCount = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("fastScrollHintCount", fastScrollHintCount).apply();
        return;
    }

    public static void setKeepMedia(int r2) {
        keepMedia = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("keep_media", keepMedia);
        r22.apply();
    }

    public static void setMediaColumnsCount(int r2) {
        if (mediaColumnsCount == r2) goto L6;
        mediaColumnsCount = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("mediaColumnsCount", mediaColumnsCount).apply();
        return;
    }

    public static void setMultipleReactionsPromoShowed(boolean r2) {
        multipleReactionsPromoShowed = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("multipleReactionsPromoShowed", multipleReactionsPromoShowed);
        r22.apply();
    }

    public static boolean setNewAppVersionAvailable(TLRPC.TL_help_appUpdate r4) {
        PackageInfo r1 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L8
        int r2 = r1.versionCode;     // Catch: Exception -> L8
        String r12 = r1.versionName;     // Catch: Exception -> L6
    L11:
        if (r2 != 0) goto L13;
        r2 = buildVersion();
    L13:
        if (r12 != null) goto L15;
        r12 = BuildVars.BUILD_VERSION_STRING;
    L15:
        String r3 = r4.version;
        if (r3 != null) goto L18;
    L22:
        return false;
    L18:
        if (versionBiggerOrEqual(r12, r3) == true) goto L22;
        pendingAppUpdate = r4;
        pendingAppUpdateBuildVersion = r2;
        saveConfig();
        return true;
    L6:
        e = e;
    L10:
        FileLog.e(e);
        r12 = null;
    L8:
        e = e;
        r2 = 0;
        goto L10
    }

    public static void setNoSoundHintShowed(boolean r2) {
        if (noSoundHintShowed != r2) goto L5;
        return;
    L5:
        noSoundHintShowed = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("noSoundHintShowed", noSoundHintShowed);
        r22.apply();
    }

    public static void setPassportConfig(String r1, int r2) {
        passportConfigMap = null;
        passportConfigJson = r1;
        passportConfigHash = r2;
        saveConfig();
        getCountryLangs();
    }

    public static void setPlaybackOrderType(int r3) {
        if (r3 != 2) goto L5;
        shuffleMusic = true;
        playOrderReversed = false;
    L8:
        MediaController.getInstance().checkIsNextMediaFileDownloaded();
        SharedPreferences.Editor r32 = MessagesController.getGlobalMainSettings().edit();
        r32.putBoolean("shuffleMusic", shuffleMusic);
        r32.putBoolean("playOrderReversed", playOrderReversed);
        r32.apply();
        return;
    L5:
        if (r3 != 1) goto L7;
        playOrderReversed = true;
        shuffleMusic = false;
        goto L8
    L7:
        playOrderReversed = false;
        shuffleMusic = false;
        goto L8
    }

    public static void setRepeatMode(int r2) {
        repeatMode = r2;
        if (r2 >= 0) goto L5;
    L6:
        repeatMode = 0;
    L7:
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("repeatMode", repeatMode);
        r22.apply();
        return;
    L5:
        if (r2 <= 2) goto L7;
        goto L6
    }

    public static void setSearchEngineType(int r2) {
        searchEngineType = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("searchEngineType", searchEngineType);
        r22.apply();
    }

    public static void setSearchMessagesAsListUsed(boolean r2) {
        searchMessagesAsListUsed = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("searchMessagesAsListUsed", searchMessagesAsListUsed);
        r22.apply();
    }

    public static void setSecretMapPreviewType(int r2) {
        mapPreviewType = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("mapPreviewType", mapPreviewType);
        r22.apply();
    }

    public static void setStickersReorderingHintUsed(boolean r2) {
        stickersReorderingHintUsed = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("stickersReorderingHintUsed", stickersReorderingHintUsed);
        r22.apply();
    }

    public static void setStoriesColumnsCount(int r2) {
        if (storiesColumnsCount == r2) goto L6;
        storiesColumnsCount = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("storiesColumnsCount", storiesColumnsCount).apply();
        return;
    }

    public static void setStoriesIntroShown(boolean r2) {
        storiesIntroShown = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("storiesIntroShown", storiesIntroShown);
        r22.apply();
    }

    public static void setStoriesReactionsLongPressHintUsed(boolean r2) {
        storyReactionsLongPressHint = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("storyReactionsLongPressHint", storyReactionsLongPressHint);
        r22.apply();
    }

    public static void setSuggestStickers(int r2) {
        suggestStickers = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putInt("suggestStickers", suggestStickers);
        r22.apply();
    }

    public static void setUseThreeLinesLayout(boolean r2) {
        useThreeLinesLayout = r2;
        SharedPreferences.Editor r22 = MessagesController.getGlobalMainSettings().edit();
        r22.putBoolean("useThreeLinesLayout", useThreeLinesLayout);
        r22.apply();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[]{Boolean.TRUE});
    }

    public static void toggleArchiveHidden() {
        archiveHidden = !archiveHidden;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("archiveHidden", archiveHidden);
        r0.apply();
    }

    public static void toggleAutoplayGifs() {
        LiteMode.toggleFlag(2048);
    }

    public static void toggleAutoplayVideo() {
        LiteMode.toggleFlag(1024);
    }

    public static void toggleBigEmoji() {
        allowBigEmoji = !allowBigEmoji;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("allowBigEmoji", allowBigEmoji);
        r0.apply();
    }

    public static void toggleBrowserAdaptableColors() {
        adaptableColorInBrowser = !adaptableColorInBrowser;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("adaptableBrowser", adaptableColorInBrowser);
        r0.apply();
    }

    public static void toggleChatBlur() {
        LiteMode.toggleFlag(256);
    }

    public static void toggleDebugVideoQualities() {
        debugVideoQualities = !debugVideoQualities;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("debugVideoQualities", debugVideoQualities);
        r0.apply();
    }

    public static void toggleDebugWebView() {
        boolean r0 = !debugWebView;
        debugWebView = r0;
        WebView.setWebContentsDebuggingEnabled(r0);
        SharedPreferences.Editor r02 = MessagesController.getGlobalMainSettings().edit();
        r02.putBoolean("debugWebView", debugWebView);
        r02.apply();
    }

    public static void toggleDirectShare() {
        directShare = !directShare;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("direct_share", directShare);
        r0.apply();
        ShortcutManagerCompat.removeAllDynamicShortcuts(ApplicationLoader.applicationContext);
        MediaDataController.getInstance(UserConfig.selectedAccount).buildShortcuts();
    }

    public static void toggleDisableVoiceAudioEffects() {
        disableVoiceAudioEffects = !disableVoiceAudioEffects;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("disableVoiceAudioEffects", disableVoiceAudioEffects);
        r0.apply();
    }

    public static void toggleFastWallpaperDisabled() {
        fastWallpaperDisabled = !fastWallpaperDisabled;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("fastWallpaperDisabled", fastWallpaperDisabled);
        r0.apply();
    }

    public static void toggleForceDisableTabletMode() {
        forceDisableTabletMode = !forceDisableTabletMode;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("forceDisableTabletMode", forceDisableTabletMode);
        r0.apply();
    }

    public static void toggleForceForumTabs() {
        forceForumTabs = !forceForumTabs;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("forceForumTabs", forceForumTabs);
        r0.apply();
    }

    public static void toggleFrameMetricsEnabled() {
        frameMetricsEnabled = !frameMetricsEnabled;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("frameMetricsEnabled", frameMetricsEnabled);
        r0.apply();
    }

    public static void toggleInappCamera() {
        inAppCamera = !inAppCamera;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("inappCamera", inAppCamera);
        r0.apply();
    }

    public static void toggleLocalInstantView() {
        onlyLocalInstantView = !onlyLocalInstantView;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("onlyLocalInstantView", onlyLocalInstantView);
        r0.apply();
    }

    public static void toggleLoopStickers() {
        LiteMode.toggleFlag(2);
    }

    public static void toggleNextMediaTap() {
        nextMediaTap = !nextMediaTap;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("next_media_on_tap", nextMediaTap);
        r0.apply();
    }

    public static void toggleNoiseSupression() {
        noiseSupression = !noiseSupression;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("noiseSupression", noiseSupression);
        r0.apply();
    }

    public static void togglePauseMusicOnMedia() {
        pauseMusicOnMedia = !pauseMusicOnMedia;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("pauseMusicOnMedia", pauseMusicOnMedia);
        r0.apply();
    }

    public static void togglePauseMusicOnRecord() {
        pauseMusicOnRecord = !pauseMusicOnRecord;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("pauseMusicOnRecord", pauseMusicOnRecord);
        r0.apply();
    }

    public static void togglePaymentByInvoice() {
        payByInvoice = !payByInvoice;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putBoolean("payByInvoice", payByInvoice).apply();
    }

    public static void togglePhotoViewerBlur() {
        photoViewerBlur = !photoViewerBlur;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putBoolean("photoViewerBlur", photoViewerBlur).apply();
    }

    public static void toggleRaiseToListen() {
        raiseToListen = !raiseToListen;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("raise_to_listen", raiseToListen);
        r0.apply();
    }

    public static void toggleRaiseToSpeak() {
        raiseToSpeak = !raiseToSpeak;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("raise_to_speak", raiseToSpeak);
        r0.apply();
    }

    public static void toggleRoundCamera() {
        bigCameraForRound = !bigCameraForRound;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putBoolean("bigCameraForRound", bigCameraForRound).apply();
    }

    public static void toggleRoundCamera16to9() {
        roundCamera16to9 = !roundCamera16to9;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("roundCamera16to9", roundCamera16to9);
        r0.apply();
    }

    public static void toggleSaveStreamMedia() {
        saveStreamMedia = !saveStreamMedia;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("saveStreamMedia", saveStreamMedia);
        r0.apply();
    }

    public static void toggleSortContactsByName() {
        sortContactsByName = !sortContactsByName;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("sortContactsByName", sortContactsByName);
        r0.apply();
    }

    public static void toggleSortFilesByName() {
        sortFilesByName = !sortFilesByName;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("sortFilesByName", sortFilesByName);
        r0.apply();
    }

    public static void toggleStreamAllVideo() {
        streamAllVideo = !streamAllVideo;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("streamAllVideo", streamAllVideo);
        r0.apply();
    }

    public static void toggleStreamMedia() {
        streamMedia = !streamMedia;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("streamMedia", streamMedia);
        r0.apply();
    }

    public static void toggleStreamMkv() {
        streamMkv = !streamMkv;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("streamMkv", streamMkv);
        r0.apply();
    }

    public static void toggleSuggestAnimatedEmoji() {
        suggestAnimatedEmoji = !suggestAnimatedEmoji;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("suggestAnimatedEmoji", suggestAnimatedEmoji);
        r0.apply();
    }

    public static void toggleSurfaceInStories() {
        useSurfaceInStories = !useSurfaceInStories;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putBoolean("useSurfaceInStories", useSurfaceInStories).apply();
    }

    public static void toggleUpdateStickersOrderOnSend() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        boolean r1 = !updateStickersOrderOnSend;
        updateStickersOrderOnSend = r1;
        r0.putBoolean("updateStickersOrderOnSend", r1);
        r0.apply();
    }

    public static void toggleUseCamera2(int r3) {
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
        boolean r32 = !isUsingCamera2(r3);
        useCamera2Force = Boolean.valueOf(r32);
        r0.putBoolean("useCamera2Force_2", r32).apply();
    }

    public static void toggleUseNewBlur() {
        useNewBlur = !useNewBlur;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putBoolean("useNewBlur", useNewBlur).apply();
    }

    public static void toggleUseSystemBoldFont() {
        useSystemBoldFont = !useSystemBoldFont;
        AndroidUtilities.mediumTypeface = null;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("useSystemBoldFont", useSystemBoldFont);
        r0.apply();
    }

    public static void toggleUseSystemEmoji() {
        useSystemEmoji = !useSystemEmoji;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("useSystemEmoji", useSystemEmoji);
        r0.apply();
    }

    public static void updateChatListSwipeSetting(int r2) {
        chatSwipeAction = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("ChatSwipeAction", chatSwipeAction).apply();
    }

    public static void updateDayNightThemeSwitchHintCount(int r2) {
        dayNightThemeSwitchHintCount = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("dayNightThemeSwitchHintCount", dayNightThemeSwitchHintCount).apply();
    }

    public static void updateEmojiInteractionsHintCount(int r2) {
        emojiInteractionsHintCount = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("emojiInteractionsHintCount", emojiInteractionsHintCount).apply();
    }

    public static void updateMessageSeenHintCount(int r2) {
        messageSeenHintCount = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("messageSeenCount", messageSeenHintCount).apply();
    }

    public static void updateStealthModeSendMessageConfirm(int r2) {
        stealthModeSendMessageConfirm = r2;
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("stealthModeSendMessageConfirm", stealthModeSendMessageConfirm).apply();
    }

    public static void updateTabletConfig() {
        if (fontSizeIsDefault == false) goto L12;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        if (AndroidUtilities.isTablet() == true) goto L7;
    L9:
        int r1 = 16;
    L10:
        int r12 = r0.getInt("fons_size", r1);
        fontSize = r12;
        ivFontSize = r0.getInt("iv_font_size", r12);
        return;
    L7:
        if (AndroidUtilities.isFold() == true) goto L9;
        r1 = 18;
        goto L10
    }

    public static boolean versionBiggerOrEqual(String r5, String r6) {
        String[] r52 = r5.split("\\.");
        String[] r62 = r6.split("\\.");
        int r1 = 0;
    L4:
        if (r1 >= Math.min(r52.length, r62.length)) goto L11;
        int r2 = Integer.parseInt(r52[r1]);
        int r4 = Integer.parseInt(r62[r1]);
        if (r2 < r4) goto L7;
        if (r2 > r4) goto L9;
        r1 = r1 + 1;
        goto L4
    L9:
        return true;
    L7:
        return false;
    L11:
        return true;
    }
}
