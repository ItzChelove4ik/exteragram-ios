package org.webrtc.voiceengine;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public final class WebRtcAudioUtils {
    private static final String[] BLACKLISTED_AEC_MODELS = null;
    private static final String[] BLACKLISTED_NS_MODELS = null;
    private static final String[] BLACKLISTED_OPEN_SL_ES_MODELS = null;
    private static final int DEFAULT_SAMPLE_RATE_HZ = 16000;
    private static final String TAG = "WebRtcAudioUtils";
    private static int defaultSampleRateHz = 16000;
    private static boolean isDefaultSampleRateOverridden;
    private static boolean useWebRtcBasedAcousticEchoCanceler;
    private static boolean useWebRtcBasedNoiseSuppressor;

    static {
        BLACKLISTED_OPEN_SL_ES_MODELS = new String[0];
        BLACKLISTED_AEC_MODELS = new String[0];
        BLACKLISTED_NS_MODELS = new String[0];
    }

    public WebRtcAudioUtils() {
    }

    public static boolean deviceIsBlacklistedForOpenSLESUsage() {
        return Arrays.asList(BLACKLISTED_OPEN_SL_ES_MODELS).contains(Build.MODEL);
    }

    private static String deviceTypeToString(int r0) {
        switch(r0) {
            case 1: goto L47;
            case 2: goto L45;
            case 3: goto L43;
            case 4: goto L41;
            case 5: goto L39;
            case 6: goto L37;
            case 7: goto L35;
            case 8: goto L33;
            case 9: goto L31;
            case 10: goto L29;
            case 11: goto L27;
            case 12: goto L25;
            case 13: goto L23;
            case 14: goto L21;
            case 15: goto L19;
            case 16: goto L17;
            case 17: goto L15;
            case 18: goto L13;
            case 19: goto L11;
            case 20: goto L9;
            case 21: goto L7;
            case 22: goto L5;
            default: goto L3;
        };
    L3:
        return "TYPE_UNKNOWN";
    L5:
        return "TYPE_USB_HEADSET";
    L7:
        return "TYPE_BUS";
    L9:
        return "TYPE_IP";
    L11:
        return "TYPE_AUX_LINE";
    L13:
        return "TYPE_TELEPHONY";
    L15:
        return "TYPE_TV_TUNER";
    L17:
        return "TYPE_FM_TUNER";
    L19:
        return "TYPE_BUILTIN_MIC";
    L21:
        return "TYPE_FM";
    L23:
        return "TYPE_DOCK";
    L25:
        return "TYPE_USB_ACCESSORY";
    L27:
        return "TYPE_USB_DEVICE";
    L29:
        return "TYPE_HDMI_ARC";
    L31:
        return "TYPE_HDMI";
    L33:
        return "TYPE_BLUETOOTH_A2DP";
    L35:
        return "TYPE_BLUETOOTH_SCO";
    L37:
        return "TYPE_LINE_DIGITAL";
    L39:
        return "TYPE_LINE_ANALOG";
    L41:
        return "TYPE_WIRED_HEADPHONES";
    L43:
        return "TYPE_WIRED_HEADSET";
    L45:
        return "TYPE_BUILTIN_SPEAKER";
    L47:
        return "TYPE_BUILTIN_EARPIECE";
    }

    public static List<String> getBlackListedModelsForAecUsage() {
        return Arrays.asList(BLACKLISTED_AEC_MODELS);
    }

    public static List<String> getBlackListedModelsForNsUsage() {
        return Arrays.asList(BLACKLISTED_NS_MODELS);
    }

    public static synchronized int getDefaultSampleRateHz() {
        monitor-enter(WebRtcAudioUtils.class);
        int r1 = defaultSampleRateHz;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    private static boolean hasMicrophone() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.microphone");
    }

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    public static boolean isAutomaticGainControlSupported() {
        return false;
    }

    public static synchronized boolean isDefaultSampleRateOverridden() {
        monitor-enter(WebRtcAudioUtils.class);
        boolean r1 = isDefaultSampleRateOverridden;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    private static boolean isVolumeFixed(AudioManager r0) {
        return r0.isVolumeFixed();
    }

    private static void logAudioDeviceInfo(String r6, AudioManager r7) {
        AudioDeviceInfo[] r72 = r7.getDevices(3);
        if (r72.length == 0) goto L21;
        Logging.d(r6, "Audio Devices: ");
        int r0 = r72.length;
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L25;
        AudioDeviceInfo r2 = r72[r1];
        StringBuilder r3 = new StringBuilder("  ");
        r3.append(deviceTypeToString(r2.getType()));
        if (r2.isSource() == false) goto L10;
        String r4 = "(in): ";
    L11:
        r3.append(r4);
        if (r2.getChannelCounts().length <= 0) goto L15;
        r3.append("channels=");
        r3.append(Arrays.toString(r2.getChannelCounts()));
        r3.append(", ");
    L15:
        if (r2.getEncodings().length <= 0) goto L18;
        r3.append("encodings=");
        r3.append(Arrays.toString(r2.getEncodings()));
        r3.append(", ");
    L18:
        if (r2.getSampleRates().length <= 0) goto L20;
        r3.append("sample rates=");
        r3.append(Arrays.toString(r2.getSampleRates()));
        r3.append(", ");
    L20:
        r3.append("id=");
        r3.append(r2.getId());
        Logging.d(r6, r3.toString());
        r1 = r1 + 1;
        goto L6
    L10:
        r4 = "(out): ";
        goto L11
    L25:
        return;
    }

    public static void logAudioState(String r2) {
        logDeviceInfo(r2);
        AudioManager r0 = (AudioManager) ContextUtils.getApplicationContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        logAudioStateBasic(r2, r0);
        logAudioStateVolume(r2, r0);
        logAudioDeviceInfo(r2, r0);
    }

    private static void logAudioStateBasic(String r2, AudioManager r3) {
        Logging.d(r2, "Audio State: audio mode: " + modeToString(r3.getMode()) + ", has mic: " + hasMicrophone() + ", mic muted: " + r3.isMicrophoneMute() + ", music active: " + r3.isMusicActive() + ", speakerphone: " + r3.isSpeakerphoneOn() + ", BT SCO: " + r3.isBluetoothScoOn());
    }

    private static void logAudioStateVolume(String r7, AudioManager r8) {
        int[] r1 = {0, 3, 2, 4, 5, 1};
        Logging.d(r7, "Audio State: ");
        boolean r2 = isVolumeFixed(r8);
        Logging.d(r7, "  fixed volume=" + r2);
        if (r2 == true) goto L7;
        int r22 = 0;
    L5:
        if (r22 >= 6) goto L9;
        int r3 = r1[r22];
        StringBuilder r4 = new StringBuilder();
        r4.append("  " + streamTypeToString(r3) + ": ");
        r4.append("volume=");
        r4.append(r8.getStreamVolume(r3));
        r4.append(", max=");
        r4.append(r8.getStreamMaxVolume(r3));
        logIsStreamMute(r7, r8, r3, r4);
        Logging.d(r7, r4.toString());
        r22 = r22 + 1;
        goto L5
    L9:
        return;
    }

    public static void logDeviceInfo(String r2) {
        Logging.d(r2, "Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }

    private static void logIsStreamMute(String r0, AudioManager r1, int r2, StringBuilder r3) {
        r3.append(", muted=");
        r3.append(r1.isStreamMute(r2));
    }

    public static String modeToString(int r1) {
        if (r1 != 0) goto L4;
        return "MODE_NORMAL";
    L4:
        if (r1 != 1) goto L6;
        return "MODE_RINGTONE";
    L6:
        if (r1 != 2) goto L8;
        return "MODE_IN_CALL";
    L8:
        if (r1 == 3) goto L11;
        return "MODE_INVALID";
    L11:
        return "MODE_IN_COMMUNICATION";
    }

    public static boolean runningOnEmulator() {
        if (Build.HARDWARE.equals("goldfish") == true) goto L5;
        return false;
    L5:
        if (Build.BRAND.startsWith("generic_") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static synchronized void setDefaultSampleRateHz(int r2) {
        monitor-enter(WebRtcAudioUtils.class);
        isDefaultSampleRateOverridden = true;     // Catch: Throwable -> L8
        defaultSampleRateHz = r2;     // Catch: Throwable -> L8
        monitor-exit(WebRtcAudioUtils.class);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static synchronized void setWebRtcBasedAcousticEchoCanceler(boolean r1) {
        monitor-enter(WebRtcAudioUtils.class);
        useWebRtcBasedAcousticEchoCanceler = r1;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized void setWebRtcBasedAutomaticGainControl(boolean r2) {
        monitor-enter(WebRtcAudioUtils.class);
        Logging.w(TAG, "setWebRtcBasedAutomaticGainControl() is deprecated");     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized void setWebRtcBasedNoiseSuppressor(boolean r1) {
        monitor-enter(WebRtcAudioUtils.class);
        useWebRtcBasedNoiseSuppressor = r1;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static String streamTypeToString(int r1) {
        if (r1 != 0) goto L4;
        return "STREAM_VOICE_CALL";
    L4:
        if (r1 != 1) goto L6;
        return "STREAM_SYSTEM";
    L6:
        if (r1 != 2) goto L8;
        return "STREAM_RING";
    L8:
        if (r1 != 3) goto L10;
        return "STREAM_MUSIC";
    L10:
        if (r1 != 4) goto L12;
        return "STREAM_ALARM";
    L12:
        if (r1 == 5) goto L15;
        return "STREAM_INVALID";
    L15:
        return "STREAM_NOTIFICATION";
    }

    public static synchronized boolean useWebRtcBasedAcousticEchoCanceler() {
        monitor-enter(WebRtcAudioUtils.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (useWebRtcBasedAcousticEchoCanceler == false) goto L9;
        Logging.w(TAG, "Overriding default behavior; now using WebRTC AEC!");     // Catch: Throwable -> L7
    L9:
        boolean r1 = useWebRtcBasedAcousticEchoCanceler;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return r1;
    }

    public static synchronized boolean useWebRtcBasedAutomaticGainControl() {
        monitor-enter(WebRtcAudioUtils.class);
        monitor-exit(WebRtcAudioUtils.class);
        return true;
    }

    public static synchronized boolean useWebRtcBasedNoiseSuppressor() {
        monitor-enter(WebRtcAudioUtils.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (useWebRtcBasedNoiseSuppressor == false) goto L9;
        Logging.w(TAG, "Overriding default behavior; now using WebRTC NS!");     // Catch: Throwable -> L7
    L9:
        boolean r1 = useWebRtcBasedNoiseSuppressor;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioUtils.class);
        return r1;
    }
}
