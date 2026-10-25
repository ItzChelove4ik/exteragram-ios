package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;

/* loaded from: classes4.dex */
public class DefaultAudioTrackProvider implements DefaultAudioSink.AudioTrackProvider {
    public DefaultAudioTrackProvider() {
    }

    private AudioTrack createAudioTrackV23(AudioSink.AudioTrackConfig r4, AudioAttributes r5, int r6, Context r7) {
        AudioFormat r0 = Util.getAudioFormat(r4.sampleRate, r4.channelConfig, r4.encoding);
        android.media.AudioAttributes r52 = getAudioTrackAttributesV21(r5, r4.tunneling);
        AudioTrack.Builder r53 = new AudioTrack.Builder().setAudioAttributes(r52).setAudioFormat(r0).setTransferMode(1).setBufferSizeInBytes(r4.bufferSize).setSessionId(r6);
        int r62 = Build.VERSION.SDK_INT;
        if (r62 < 29) goto L6;
        setOffloadedPlaybackV29(r53, r4.offload);
    L6:
        if (r62 < 34) goto L10;
        if (r7 == null) goto L10;
        r53.setContext(r7);
    L10:
        return customizeAudioTrackBuilder(r53).build();
    }

    private android.media.AudioAttributes getAudioTrackAttributesV21(AudioAttributes r1, boolean r2) {
        if (r2 == false) goto L6;
        return getAudioTrackTunnelingAttributesV21();
    L6:
        return r1.getAudioAttributesV21().audioAttributes;
    }

    private android.media.AudioAttributes getAudioTrackTunnelingAttributesV21() {
        return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
    }

    private void setOffloadedPlaybackV29(AudioTrack.Builder r1, boolean r2) {
        r1.setOffloadedPlayback(r2);
    }

    public AudioTrack.Builder customizeAudioTrackBuilder(AudioTrack.Builder r1) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackProvider
    public final AudioTrack getAudioTrack(AudioSink.AudioTrackConfig r1, androidx.media3.common.AudioAttributes r2, int r3, Context r4) {
        return createAudioTrackV23(r1, r2, r3, r4);
    }
}
