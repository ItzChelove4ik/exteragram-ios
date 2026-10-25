package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.os.Build;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioOffloadSupport;
import androidx.media3.exoplayer.audio.DefaultAudioSink;

/* loaded from: classes4.dex */
public final class DefaultAudioOffloadSupportProvider implements DefaultAudioSink.AudioOffloadSupportProvider {
    private final Context context;
    private Boolean isOffloadVariableRateSupported;

    public static final class Api29 {
        public static AudioOffloadSupport getOffloadedPlaybackSupport(AudioFormat r0, AudioAttributes r1, boolean r2) {
            if (AudioManager.isOffloadedPlaybackSupported(r0, r1) == true) goto L7;
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        L7:
            return new AudioOffloadSupport.Builder().setIsFormatSupported(true).setIsSpeedChangeSupported(r2).build();
        }
    }

    public static final class Api31 {
        public static AudioOffloadSupport getOffloadedPlaybackSupport(AudioFormat r3, AudioAttributes r4, boolean r5) {
            int r32 = AudioManager.getPlaybackOffloadSupport(r3, r4);
            if (r32 == 0) goto L5;
            AudioOffloadSupport.Builder r42 = new AudioOffloadSupport.Builder();
            if (Build.VERSION.SDK_INT > 32) goto L9;
        L11:
            boolean r33 = false;
        L13:
            return r42.setIsFormatSupported(true).setIsGaplessSupported(r33).setIsSpeedChangeSupported(r5).build();
        L9:
            if (r32 != 2) goto L11;
            r33 = true;
            goto L13
        L5:
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
    }

    public DefaultAudioOffloadSupportProvider(Context r1) {
        if (r1 != null) goto L5;
        Context r12 = null;
    L6:
        this.context = r12;
        return;
    L5:
        r12 = r1.getApplicationContext();
        goto L6
    }

    private boolean isOffloadVariableRateSupported(Context r2) {
        Boolean r0 = this.isOffloadVariableRateSupported;
        if (r0 != null) goto L5;
        if (r2 == null) goto L14;
        String r22 = AudioManagerCompat.getAudioManager(r2).getParameters("offloadVariableRateSupported");
        if (r22 != null) goto L10;
    L12:
        boolean r23 = false;
    L13:
        this.isOffloadVariableRateSupported = Boolean.valueOf(r23);
    L16:
        return this.isOffloadVariableRateSupported.booleanValue();
    L10:
        if (r22.equals("offloadVariableRateSupported=1") == false) goto L12;
        r23 = true;
        goto L13
    L14:
        this.isOffloadVariableRateSupported = Boolean.FALSE;
        goto L16
    L5:
        return r0.booleanValue();
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioOffloadSupportProvider
    public AudioOffloadSupport getAudioOffloadSupport(Format r4, androidx.media3.common.AudioAttributes r5) {
        Assertions.checkNotNull(r4);
        Assertions.checkNotNull(r5);
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 29) goto L28;
        if (r4.sampleRate == (-1)) goto L28;
        boolean r3 = isOffloadVariableRateSupported(this.context);
        int r1 = MimeTypes.getEncoding((String) Assertions.checkNotNull(r4.sampleMimeType), r4.codecs);
        if (r1 == 0) goto L26;
        if (r0 < Util.getApiLevelThatAudioFormatIntroducedAudioEncoding(r1)) goto L26;
        int r2 = Util.getAudioTrackChannelConfig(r4.channelCount);
        if (r2 == 0) goto L15;
        AudioFormat r42 = Util.getAudioFormat(r4.sampleRate, r2, r1);     // Catch: IllegalArgumentException -> L23
        if (r0 < 31) goto L22;
        return Api31.getOffloadedPlaybackSupport(r42, r5.getAudioAttributesV21().audioAttributes, r3);
    L22:
        return Api29.getOffloadedPlaybackSupport(r42, r5.getAudioAttributesV21().audioAttributes, r3);
    L24:
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    L15:
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    L26:
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    L28:
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    }
}
