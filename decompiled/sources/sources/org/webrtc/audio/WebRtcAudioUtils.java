package org.webrtc.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import org.webrtc.Logging;

/* loaded from: classes6.dex */
final class WebRtcAudioUtils {
    private static final String TAG = "WebRtcAudioUtilsExternal";

    public WebRtcAudioUtils() {
    }

    @TargetApi(24)
    public static String audioEncodingToString(int r2) {
        if (r2 != 0) goto L4;
        return "INVALID";
    L4:
        switch(r2) {
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L6;
        };
    L7:
        return "MP3";
    L9:
        return "DTS_HD";
    L11:
        return "DTS";
    L13:
        return "AC3";
    L14:
        return "PCM_FLOAT";
    L16:
        return "PCM_8BIT";
    L18:
        return "PCM_16BIT";
    L6:
        return "Invalid encoding: " + r2;
    }

    @TargetApi(24)
    public static String audioSourceToString(int r0) {
        switch(r0) {
            case 0: goto L23;
            case 1: goto L21;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            case 8: goto L3;
            case 9: goto L7;
            case 10: goto L5;
            default: goto L3;
        };
    L3:
        return "INVALID";
    L5:
        return "VOICE_PERFORMANCE";
    L7:
        return "UNPROCESSED";
    L9:
        return "VOICE_COMMUNICATION";
    L11:
        return "VOICE_RECOGNITION";
    L13:
        return "CAMCORDER";
    L15:
        return "VOICE_CALL";
    L17:
        return "VOICE_DOWNLINK";
    L19:
        return "VOICE_UPLINK";
    L21:
        return "MIC";
    L23:
        return PluginsConstants.Strategy.DEFAULT;
    }

    public static String channelMaskToString(int r1) {
        if (r1 != 12) goto L5;
        return "IN_STEREO";
    L5:
        if (r1 == 16) goto L8;
        return "INVALID";
    L8:
        return "IN_MONO";
    }

    public static String deviceTypeToString(int r0) {
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

    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    private static boolean hasMicrophone(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.microphone");
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

    public static void logAudioState(String r0, Context r1, AudioManager r2) {
        logDeviceInfo(r0);
        logAudioStateBasic(r0, r1, r2);
        logAudioStateVolume(r0, r2);
        logAudioDeviceInfo(r0, r2);
    }

    private static void logAudioStateBasic(String r2, Context r3, AudioManager r4) {
        Logging.d(r2, "Audio State: audio mode: " + modeToString(r4.getMode()) + ", has mic: " + hasMicrophone(r3) + ", mic muted: " + r4.isMicrophoneMute() + ", music active: " + r4.isMusicActive() + ", speakerphone: " + r4.isSpeakerphoneOn() + ", BT SCO: " + r4.isBluetoothScoOn());
    }

    private static void logAudioStateVolume(String r7, AudioManager r8) {
        int[] r1 = {0, 3, 2, 4, 5, 1};
        Logging.d(r7, "Audio State: ");
        boolean r2 = r8.isVolumeFixed();
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
}
