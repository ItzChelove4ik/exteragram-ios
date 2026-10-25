package org.webrtc.audio;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import java.util.UUID;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.telegram.messenger.BuildConfig;
import org.webrtc.Logging;

/* loaded from: classes6.dex */
class WebRtcAudioEffects {
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = null;
    private static final UUID AOSP_NOISE_SUPPRESSOR = null;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffectsExternal";
    private static AudioEffect.Descriptor[] cachedEffects;
    private AcousticEchoCanceler aec;
    private NoiseSuppressor ns;
    private boolean shouldEnableAec;
    private boolean shouldEnableNs;

    static {
        AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
        AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    }

    public WebRtcAudioEffects() {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    private static void assertTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected condition to be true");
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

    public static boolean isAcousticEchoCancelerSupported() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC, AOSP_ACOUSTIC_ECHO_CANCELER);
    }

    private static boolean isEffectTypeAvailable(UUID r6, UUID r7) {
        AudioEffect.Descriptor[] r0 = getAvailableEffects();
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = r0.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L12;
        if (r0[r3].type.equals(r6) == true) goto L10;
        r3 = r3 + 1;
        goto L6
    L10:
        return !r4.uuid.equals(r7);
    L12:
        return false;
    }

    public static boolean isNoiseSuppressorSupported() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS, AOSP_NOISE_SUPPRESSOR);
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
        if (isAcousticEchoCancelerSupported() == false) goto L34;
        AcousticEchoCanceler r03 = AcousticEchoCanceler.create(r12);
        this.aec = r03;
        if (r03 == null) goto L32;
        boolean r04 = r03.getEnabled();
        if (this.shouldEnableAec == true) goto L17;
    L19:
        boolean r8 = false;
    L21:
        if (this.aec.setEnabled(r8) == 0) goto L23;
        Logging.e(TAG, "Failed to set the AcousticEchoCanceler state");
    L23:
        StringBuilder r9 = new StringBuilder("AcousticEchoCanceler: was ");
        if (r04 == false) goto L26;
        String r05 = "enabled";
    L27:
        r9.append(r05);
        r9.append(", enable: ");
        r9.append(r8);
        r9.append(", is now: ");
        if (this.aec.getEnabled() == false) goto L30;
        String r06 = "enabled";
    L31:
        r9.append(r06);
        Logging.d(TAG, r9.toString());
        goto L34
    L30:
        r06 = "disabled";
        goto L31
    L26:
        r05 = "disabled";
        goto L27
    L17:
        if (isAcousticEchoCancelerSupported() == false) goto L19;
        r8 = true;
        goto L21
    L32:
        Logging.e(TAG, "Failed to create the AcousticEchoCanceler instance");
    L34:
        if (isNoiseSuppressorSupported() == false) goto L56;
        NoiseSuppressor r122 = NoiseSuppressor.create(r12);
        this.ns = r122;
        if (r122 == null) goto L54;
        boolean r123 = r122.getEnabled();
        if (this.shouldEnableNs == false) goto L43;
        if (isNoiseSuppressorSupported() == false) goto L43;
        r2 = true;
    L43:
        if (this.ns.setEnabled(r2) == 0) goto L45;
        Logging.e(TAG, "Failed to set the NoiseSuppressor state");
    L45:
        StringBuilder r07 = new StringBuilder("NoiseSuppressor: was ");
        if (r123 == false) goto L48;
        String r124 = "enabled";
    L49:
        r07.append(r124);
        r07.append(", enable: ");
        r07.append(r2);
        r07.append(", is now: ");
        if (this.ns.getEnabled() == false) goto L52;
        r6 = "enabled";
    L52:
        r07.append(r6);
        Logging.d(TAG, r07.toString());
        return;
    L48:
        r124 = "disabled";
        goto L49
    L54:
        Logging.e(TAG, "Failed to create the NoiseSuppressor instance");
        return;
    L56:
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
        if (isAcousticEchoCancelerSupported() == true) goto L7;
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
        if (isNoiseSuppressorSupported() == true) goto L7;
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

    public boolean toggleNS(boolean r5) {
        if (this.ns != null) goto L6;
        Logging.e(TAG, "Attempting to enable or disable nonexistent NoiseSuppressor.");
        return false;
    L6:
        Logging.d(TAG, "toggleNS(" + r5 + ")");
        if (this.ns.setEnabled(r5) != 0) goto L10;
        return true;
    L10:
        return false;
    }
}
