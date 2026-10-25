package org.webrtc.voiceengine;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import java.util.List;
import java.util.UUID;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.SharedConfig;
import org.webrtc.Logging;

/* loaded from: classes7.dex */
public class WebRtcAudioEffects {
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = null;
    private static final UUID AOSP_NOISE_SUPPRESSOR = null;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffects";
    private static AudioEffect.Descriptor[] cachedEffects;
    private AcousticEchoCanceler aec;
    private NoiseSuppressor ns;
    private boolean shouldEnableAec;
    private boolean shouldEnableNs;

    static {
        AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
        AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    }

    private WebRtcAudioEffects() {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    private static void assertTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected condition to be true");
    }

    public static boolean canUseAcousticEchoCanceler() {
        if (isAcousticEchoCancelerSupported() == true) goto L5;
    L11:
        boolean r0 = false;
    L12:
        Logging.d(TAG, "canUseAcousticEchoCanceler: " + r0);
        return r0;
    L5:
        if (WebRtcAudioUtils.useWebRtcBasedAcousticEchoCanceler() == true) goto L11;
        if (isAcousticEchoCancelerBlacklisted() == true) goto L11;
        if (isAcousticEchoCancelerExcludedByUUID() == true) goto L11;
        r0 = true;
        goto L12
    }

    public static boolean canUseNoiseSuppressor() {
        if (isNoiseSuppressorSupported() == true) goto L5;
    L11:
        boolean r0 = false;
    L12:
        Logging.d(TAG, "canUseNoiseSuppressor: " + r0);
        return r0;
    L5:
        if (WebRtcAudioUtils.useWebRtcBasedNoiseSuppressor() == true) goto L11;
        if (isNoiseSuppressorBlacklisted() == true) goto L11;
        if (isNoiseSuppressorExcludedByUUID() == true) goto L11;
        r0 = true;
        goto L12
    }

    public static WebRtcAudioEffects create() {
        return new WebRtcAudioEffects();
    }

    private boolean effectTypeIsVoIP(UUID r1) {
        if (AudioEffect.EFFECT_TYPE_AEC.equals(r1) == false) goto L7;
        if (isAcousticEchoCancelerSupported() == false) goto L7;
        return true;
    L7:
        if (AudioEffect.EFFECT_TYPE_NS.equals(r1) == true) goto L9;
        return false;
    L9:
        if (isNoiseSuppressorSupported() == false) goto L15;
        return true;
    L15:
        return false;
    }

    private static AudioEffect.Descriptor[] getAvailableEffects() {
        AudioEffect.Descriptor[] r0 = cachedEffects;
        if (r0 == null) goto L5;
        return r0;
    L5:
        AudioEffect.Descriptor[] r02 = AudioEffect.queryEffects();
        cachedEffects = r02;
        return r02;
    }

    public static boolean isAcousticEchoCancelerBlacklisted() {
        List<String> r0 = WebRtcAudioUtils.getBlackListedModelsForAecUsage();
        String r1 = Build.MODEL;
        boolean r02 = r0.contains(r1);
        if (r02 == false) goto L5;
        Logging.w(TAG, r1 + " is blacklisted for HW AEC usage!");
    L5:
        return r02;
    }

    private static boolean isAcousticEchoCancelerEffectAvailable() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC);
    }

    private static boolean isAcousticEchoCancelerExcludedByUUID() {
        AudioEffect.Descriptor[] r0 = getAvailableEffects();
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        AudioEffect.Descriptor r4 = r0[r3];
        if (r4.type.equals(AudioEffect.EFFECT_TYPE_AEC) == false) goto L10;
        if (r4.uuid.equals(AOSP_ACOUSTIC_ECHO_CANCELER) == false) goto L10;
        return true;
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        return false;
    }

    public static boolean isAcousticEchoCancelerSupported() {
        return isAcousticEchoCancelerEffectAvailable();
    }

    private static boolean isEffectTypeAvailable(UUID r5) {
        AudioEffect.Descriptor[] r0 = getAvailableEffects();
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = r0.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L12;
        if (r0[r3].type.equals(r5) == true) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static boolean isNoiseSuppressorBlacklisted() {
        List<String> r0 = WebRtcAudioUtils.getBlackListedModelsForNsUsage();
        String r1 = Build.MODEL;
        boolean r02 = r0.contains(r1);
        if (r02 == false) goto L5;
        Logging.w(TAG, r1 + " is blacklisted for HW NS usage!");
    L5:
        return r02;
    }

    private static boolean isNoiseSuppressorEffectAvailable() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS);
    }

    private static boolean isNoiseSuppressorExcludedByUUID() {
        AudioEffect.Descriptor[] r0 = getAvailableEffects();
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        AudioEffect.Descriptor r4 = r0[r3];
        if (r4.type.equals(AudioEffect.EFFECT_TYPE_NS) == false) goto L10;
        if (r4.uuid.equals(AOSP_NOISE_SUPPRESSOR) == false) goto L10;
        return true;
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        return false;
    }

    public static boolean isNoiseSuppressorSupported() {
        return isNoiseSuppressorEffectAvailable();
    }

    public void enable(int r12) {
        Logging.d(TAG, "enable(audioSession=" + r12 + ")");
        boolean r2 = false;
        if (this.aec != null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        if (this.ns != null) goto L9;
        boolean r02 = true;
    L10:
        assertTrue(r02);
        String r6 = "disabled";
        if (isAcousticEchoCancelerSupported() == false) goto L36;
        AcousticEchoCanceler r03 = AcousticEchoCanceler.create(r12);
        this.aec = r03;
        if (r03 == null) goto L34;
        boolean r04 = r03.getEnabled();
        if (this.shouldEnableAec == true) goto L17;
    L21:
        boolean r8 = false;
    L23:
        if (this.aec.setEnabled(r8) == 0) goto L25;
        Logging.e(TAG, "Failed to set the AcousticEchoCanceler state");
    L25:
        StringBuilder r9 = new StringBuilder("AcousticEchoCanceler: was ");
        if (r04 == false) goto L28;
        String r05 = "enabled";
    L29:
        r9.append(r05);
        r9.append(", enable: ");
        r9.append(r8);
        r9.append(", is now: ");
        if (this.aec.getEnabled() == false) goto L32;
        String r06 = "enabled";
    L33:
        r9.append(r06);
        Logging.d(TAG, r9.toString());
        goto L36
    L32:
        r06 = "disabled";
        goto L33
    L28:
        r05 = "disabled";
        goto L29
    L17:
        if (canUseAcousticEchoCanceler() == false) goto L21;
        if (SharedConfig.disableVoiceAudioEffects == true) goto L21;
        r8 = true;
        goto L23
    L34:
        Logging.e(TAG, "Failed to create the AcousticEchoCanceler instance");
    L36:
        if (isNoiseSuppressorSupported() == false) goto L60;
        NoiseSuppressor r122 = NoiseSuppressor.create(r12);
        this.ns = r122;
        if (r122 == null) goto L58;
        boolean r123 = r122.getEnabled();
        if (this.shouldEnableNs == false) goto L47;
        if (canUseNoiseSuppressor() == false) goto L47;
        if (SharedConfig.disableVoiceAudioEffects == true) goto L47;
        r2 = true;
    L47:
        if (this.ns.setEnabled(r2) == 0) goto L49;
        Logging.e(TAG, "Failed to set the NoiseSuppressor state");
    L49:
        StringBuilder r07 = new StringBuilder("NoiseSuppressor: was ");
        if (r123 == false) goto L52;
        String r124 = "enabled";
    L53:
        r07.append(r124);
        r07.append(", enable: ");
        r07.append(r2);
        r07.append(", is now: ");
        if (this.ns.getEnabled() == false) goto L56;
        r6 = "enabled";
    L56:
        r07.append(r6);
        Logging.d(TAG, r07.toString());
        return;
    L52:
        r124 = "disabled";
        goto L53
    L58:
        Logging.e(TAG, "Failed to create the NoiseSuppressor instance");
        return;
    L60:
        return;
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    public void release() {
        Logging.d(TAG, BuildConfig.BUILD_TYPE);
        AcousticEchoCanceler r0 = this.aec;
        if (r0 == null) goto L5;
        r0.release();
        this.aec = null;
    L5:
        NoiseSuppressor r02 = this.ns;
        if (r02 == null) goto L9;
        r02.release();
        this.ns = null;
        return;
    }

    public boolean setAEC(boolean r4) {
        Logging.d(TAG, "setAEC(" + r4 + ")");
        if (canUseAcousticEchoCanceler() == true) goto L7;
        Logging.w(TAG, "Platform AEC is not supported");
        this.shouldEnableAec = false;
        return false;
    L7:
        if (this.aec != null) goto L9;
    L12:
        this.shouldEnableAec = r4;
        return true;
    L9:
        if (r4 == this.shouldEnableAec) goto L12;
        Logging.e(TAG, "Platform AEC state can't be modified while recording");
        return false;
    }

    public boolean setNS(boolean r4) {
        Logging.d(TAG, "setNS(" + r4 + ")");
        if (canUseNoiseSuppressor() == true) goto L7;
        Logging.w(TAG, "Platform NS is not supported");
        this.shouldEnableNs = false;
        return false;
    L7:
        if (this.ns != null) goto L9;
    L12:
        this.shouldEnableNs = r4;
        return true;
    L9:
        if (r4 == this.shouldEnableNs) goto L12;
        Logging.e(TAG, "Platform NS state can't be modified while recording");
        return false;
    }
}
