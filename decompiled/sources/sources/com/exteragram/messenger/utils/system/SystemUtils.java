package com.exteragram.messenger.utils.system;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.ui.UIUtil;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public abstract class SystemUtils {
    private static final List<ExternalApp> externalApps = null;
    public static Boolean isLensAvailable;
    private static volatile boolean lastExternalAudioOutput;
    private static volatile long lastExternalAudioOutputCheck;
    public static String lensActivityName;

    public static class ExternalApp {
        private final String activityName;
        private final Drawable appIcon;
        private final String appName;
        private final String packageName;

        public ExternalApp(String r1, String r2) {
            this.packageName = r1;
            this.activityName = r2;
            this.appIcon = fetchIcon();
            this.appName = fetchName();
        }

        private Drawable fetchIcon() {
            Drawable r4 = ApplicationLoader.applicationContext.getPackageManager().getApplicationIcon(this.packageName);     // Catch: Exception -> L4
            return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), UIUtil.drawableToBitmap(r4, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f)));
        L5:
            return ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_media);
        }

        private String fetchName() {
            PackageManager r0 = ApplicationLoader.applicationContext.getPackageManager();
            ApplicationInfo r1 = null;
            r1 = r0.getApplicationInfo(this.packageName, 0);     // Catch: Exception -> L5
            String r4 = null;
        L6:
            if (r1 != null) goto L9;
            return r4;
        L9:
            return (String) r0.getApplicationLabel(r1);
        L5:
            r4 = LocaleController.getString(R.string.NumberUnknown);
            goto L6
        }

        public Drawable getIcon() {
            return this.appIcon;
        }

        public String getName() {
            return this.appName;
        }

        public void open(Activity r5, String r6) {
            Intent r1 = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + r6));     // Catch: Exception -> L5
            r1.setClassName(this.packageName, this.activityName);     // Catch: Exception -> L5
            r1.addFlags(268435456);     // Catch: Exception -> L5
            r5.startActivityForResult(r1, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }
    }

    static {
        externalApps = new ArrayList();
        isLensAvailable = null;
        lastExternalAudioOutputCheck = -1;
    }

    private static void addExternalApp(String r2, String r3) {
        if (isAppInstalled(r2) == false) goto L6;
        externalApps.add(new ExternalApp(r2, r3));
        return;
    }

    public static void addFileToClipboard(File r4, Runnable r5) {
        Context r0 = ApplicationLoader.applicationContext;     // Catch: Exception -> L4
        ((ClipboardManager) r0.getSystemService("clipboard")).setPrimaryClip(ClipData.newUri(r0.getContentResolver(), "label", FileProvider.getUriForFile(r0, ApplicationLoader.getApplicationId() + ".provider", r4)));     // Catch: Exception -> L4
        r5.run();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static int awm() {
        return R.raw.awm;
    }

    public static void checkLensAvailability() {
        Iterator<ResolveInfo> r0 = ApplicationLoader.applicationContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.SEND").setDataAndType(Uri.parse("content://" + ApplicationLoader.getApplicationId() + ".provider"), "image/jpeg").setPackage("com.google.android.googlequicksearchbox"), 0).iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        String r1 = r0.next().activityInfo.name;
        if (r1.contains("Lens") == false) goto L4;
        isLensAvailable = Boolean.TRUE;
        lensActivityName = r1;
    L9:
        if (isLensAvailable != null) goto L15;
        isLensAvailable = Boolean.FALSE;
        return;
    }

    public static List<ExternalApp> getExternalApps() {
        updateExternalApps();
        return externalApps;
    }

    public static File getFileFromBitmap(Bitmap r4) {
        File r0 = new File(AndroidUtilities.getCacheDir(), "temp.jpeg");
        r0.createNewFile();
        FileOutputStream r1 = new FileOutputStream(r0);
        r4.compress(Bitmap.CompressFormat.JPEG, 100, r1);     // Catch: Throwable -> L6
        r1.close();
        return r0;
    L6:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    public static int getRoundAudioBitrate() {
        return RemoteUtils.getIntConfigValue("round_audio_bitrate", 64).intValue();
    }

    public static int getRoundVideoBitrate() {
        return RemoteUtils.getIntConfigValue("round_video_bitrate", 1000).intValue();
    }

    public static long getRoundVideoMaxDurationMs() {
        return Utilities.clamp(83886080000L / ((Math.max(1, getRoundVideoBitrate()) + Math.max(0, getRoundAudioBitrate())) * 1024), 60000, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public static int getRoundVideoResolution() {
        return RemoteUtils.getIntConfigValue("round_video_resolution", 512).intValue();
    }

    public static boolean hasBiometrics() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 29) goto L17;
        BiometricManager r1 = SystemUtils$$ExternalSyntheticApiModelOutline1.m(ApplicationLoader.applicationContext.getSystemService(SystemUtils$$ExternalSyntheticApiModelOutline0.m()));
        if (r1 != null) goto L8;
        return false;
    L8:
        if (r0 < 30) goto L14;
        if (r1.canAuthenticate(255) != 0) goto L12;
        return true;
    L12:
        return false;
    L14:
        if (r1.canAuthenticate() != 0) goto L16;
        return true;
    L16:
        return false;
    L17:
        FingerprintManager r02 = (FingerprintManager) ApplicationLoader.applicationContext.getSystemService(FingerprintManager.class);
        if (r02 != null) goto L21;
        return false;
    L21:
        if (r02.isHardwareDetected() == true) goto L23;
    L25:
        return false;
    L23:
        if (r02.hasEnrolledFingerprints() == false) goto L25;
        return true;
    }

    public static boolean hasExternalAudioOutput(AudioManager r8) {
        boolean r0 = false;
        if (r8 != null) goto L5;
        return false;
    L5:
        long r1 = SystemClock.elapsedRealtime();
        long r3 = lastExternalAudioOutputCheck;
        if (r3 < 0) goto L35;
        if ((r1 - r3) >= 500) goto L35;
        return lastExternalAudioOutput;
    L35:
        AudioDeviceInfo[] r82 = r8.getDevices(2);     // Catch: Exception -> L32
        int r32 = r82.length;     // Catch: Exception -> L32
        int r4 = 0;
    L13:
        if (r4 >= r32) goto L30;
        AudioDeviceInfo r5 = r82[r4];     // Catch: Exception -> L32
        int r6 = r5.getType();     // Catch: Exception -> L32
        if (r6 == 8) goto L27;
        if (r6 == 7) goto L27;
        if (r6 == 26) goto L27;
        if (r6 == 27) goto L27;
        if (r6 == 4) goto L27;
        if (r6 == 3) goto L27;
    L29:
        r4 = r4 + 1;
    L27:
        if (r5.isSink() == false) goto L29;
        r0 = true;
    L30:
        lastExternalAudioOutput = r0;
        lastExternalAudioOutputCheck = r1;
        return r0;
    L32:
        e = move-exception;
        FileLog.e(e);
        return lastExternalAudioOutput;
    }

    public static boolean isAppInstalled(String r2) {
        ApplicationLoader.applicationContext.getPackageManager().getApplicationInfo(r2, 0);     // Catch: PackageManager.NameNotFoundException -> L6
        return true;
    L6:
        return false;
    }

    public static boolean isAudioPermissionGranted() {
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return isPermissionGranted("android.permission.READ_MEDIA_AUDIO");
    L7:
        return isStoragePermissionGranted();
    }

    public static boolean isImagesAndVideoPermissionGranted() {
        if (Build.VERSION.SDK_INT < 33) goto L13;
        if (isImagesPermissionGranted() == true) goto L7;
        return false;
    L7:
        if (isVideoPermissionGranted() == false) goto L14;
        return true;
    L14:
        return false;
    L13:
        return isStoragePermissionGranted();
    }

    public static boolean isImagesPermissionGranted() {
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return isPermissionGranted("android.permission.READ_MEDIA_IMAGES");
    L7:
        return isStoragePermissionGranted();
    }

    public static boolean isLensAvailable() {
        if (isLensAvailable != null) goto L9;
        checkLensAvailability();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        isLensAvailable = Boolean.FALSE;
    L9:
        return isLensAvailable.booleanValue();
    }

    public static boolean isPermissionGranted(String r1) {
        if (ApplicationLoader.applicationContext.checkSelfPermission(r1) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT < 33) goto L15;
        if (isImagesPermissionGranted() == true) goto L7;
        return false;
    L7:
        if (isVideoPermissionGranted() == true) goto L9;
        return false;
    L9:
        if (isAudioPermissionGranted() == false) goto L17;
        return true;
    L17:
        return false;
    L15:
        return isPermissionGranted("android.permission.READ_EXTERNAL_STORAGE");
    }

    public static boolean isVideoPermissionGranted() {
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return isPermissionGranted("android.permission.READ_MEDIA_VIDEO");
    L7:
        return isStoragePermissionGranted();
    }

    public static void requestAudioPermission(Activity r3) {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        requestPermissions(r3, 4, new String[]{"android.permission.READ_MEDIA_AUDIO"});
        return;
    L6:
        requestPermissions(r3, 4, new String[]{"android.permission.READ_EXTERNAL_STORAGE"});
    }

    public static void requestImagesAndVideoPermission(Activity r1) {
        requestImagesAndVideoPermission(r1, 4);
    }

    public static void requestImagesAndVideoPermission(Activity r2, int r3) {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        requestPermissions(r2, r3, new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"});
        return;
    L6:
        requestPermissions(r2, r3, new String[]{"android.permission.READ_EXTERNAL_STORAGE"});
    }

    public static void requestImagesPermission(Activity r1) {
        requestImagesPermission(r1, 4);
    }

    public static void requestImagesPermission(Activity r2, int r3) {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        requestPermissions(r2, r3, new String[]{"android.permission.READ_MEDIA_IMAGES"});
        return;
    L6:
        requestPermissions(r2, r3, new String[]{"android.permission.READ_EXTERNAL_STORAGE"});
    }

    public static void requestPermissions(Activity r0, int r1, String... r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        r0.requestPermissions(r2, r1);
    }

    public static void requestStoragePermission(Activity r4) {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        requestPermissions(r4, 4, new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.READ_MEDIA_AUDIO"});
        return;
    L6:
        requestPermissions(r4, 4, new String[]{"android.permission.READ_EXTERNAL_STORAGE"});
    }

    public static void shareImageWithGoogleLens(Activity r3, Uri r4) {
        r3.startActivity(new Intent("android.intent.action.SEND").addFlags(1).setClassName("com.google.android.googlequicksearchbox", lensActivityName).setDataAndType(r4, "image/jpeg").putExtra("android.intent.extra.STREAM", r4));     // Catch: ActivityNotFoundException -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private static void updateExternalApps() {
        List<ExternalApp> r0 = externalApps;
        if (r0.isEmpty() == true) goto L5;
        r0.clear();
    L5:
        Intent r02 = new Intent("android.intent.action.VIEW", Uri.parse("tel:00000000000"));
        Iterator<ResolveInfo> r03 = ApplicationLoader.applicationContext.getPackageManager().queryIntentActivities(r02, 0).iterator();
    L7:
        if (r03.hasNext() == false) goto L9;
        ActivityInfo r1 = r03.next().activityInfo;
        addExternalApp(r1.packageName, r1.name);
        goto L7
    }
}
