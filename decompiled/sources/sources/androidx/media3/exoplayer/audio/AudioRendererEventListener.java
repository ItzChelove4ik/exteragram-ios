package androidx.media3.exoplayer.audio;

import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioSink;

/* loaded from: classes4.dex */
public interface AudioRendererEventListener {

    public static final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public static /* synthetic */ void $r8$lambda$1y6wHjNFTGl6rJjbHS56PamKz4s(EventDispatcher r0, boolean r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onSkipSilenceEnabledChanged(r1);
        }

        public static /* synthetic */ void $r8$lambda$6qDnRdoO6AL4FkibZr5XHuoR5lE(EventDispatcher r6, int r7, long r8, long r10) {
            ((AudioRendererEventListener) Util.castNonNull(r6.listener)).onAudioUnderrun(r7, r8, r10);
        }

        public static /* synthetic */ void $r8$lambda$7vTqHG6vg8Qa60IT2j4aNjeGBds(EventDispatcher r0, DecoderCounters r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioEnabled(r1);
        }

        public static /* synthetic */ void $r8$lambda$FlmGccfkha0ag05dECiqBJAuk88(EventDispatcher r0, DecoderCounters r1) {
            r0.getClass();
            r1.ensureUpdated();
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioDisabled(r1);
        }

        /* renamed from: $r8$lambda$MT2KYrt9IIbSc275Q-EkQouA98k, reason: not valid java name */
        public static /* synthetic */ void m821$r8$lambda$MT2KYrt9IIbSc275QEkQouA98k(EventDispatcher r0, long r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioPositionAdvancing(r1);
        }

        /* renamed from: $r8$lambda$PWzZVgP_qcYYwHT9-ex389Si8ck, reason: not valid java name */
        public static /* synthetic */ void m822$r8$lambda$PWzZVgP_qcYYwHT9ex389Si8ck(EventDispatcher r0, AudioSink.AudioTrackConfig r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioTrackReleased(r1);
        }

        /* renamed from: $r8$lambda$SMcLPJsvmTPj121naC_ZP9mxC-8, reason: not valid java name */
        public static /* synthetic */ void m823$r8$lambda$SMcLPJsvmTPj121naC_ZP9mxC8(EventDispatcher r0, Exception r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioCodecError(r1);
        }

        public static /* synthetic */ void $r8$lambda$Z5f042bJXV5vEZXCrKisqN6sD6A(EventDispatcher r0, Format r1, DecoderReuseEvaluation r2) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioInputFormatChanged(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$a9Pub2SxFdfykbxF5Tzjc2Dq5Qo(EventDispatcher r0, AudioSink.AudioTrackConfig r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioTrackInitialized(r1);
        }

        public static /* synthetic */ void $r8$lambda$f22etRvv1rjQk0HuC6bD1GvGTqY(EventDispatcher r0, int r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioSessionIdChanged(r1);
        }

        public static /* synthetic */ void $r8$lambda$fEcWRdluRpf6Sc1acacbn3LSqxQ(EventDispatcher r0, Exception r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioSinkError(r1);
        }

        /* renamed from: $r8$lambda$iU84reV-pQ--7pc5bCRMOvKwD0o, reason: not valid java name */
        public static /* synthetic */ void m824$r8$lambda$iU84reVpQ7pc5bCRMOvKwD0o(EventDispatcher r6, String r7, long r8, long r10) {
            ((AudioRendererEventListener) Util.castNonNull(r6.listener)).onAudioDecoderInitialized(r7, r8, r10);
        }

        public static /* synthetic */ void $r8$lambda$xEOGYixhjKwpE_zNETDBA3ZKZvY(EventDispatcher r0, String r1) {
            ((AudioRendererEventListener) Util.castNonNull(r0.listener)).onAudioDecoderReleased(r1);
        }

        public EventDispatcher(Handler r1, AudioRendererEventListener r2) {
            if (r2 == null) goto L5;
            Handler r12 = (Handler) Assertions.checkNotNull(r1);
        L6:
            this.handler = r12;
            this.listener = r2;
            return;
        L5:
            r12 = null;
            goto L6
        }

        public void audioCodecError(Exception r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, r3));
            return;
        }

        public void audioSessionIdChanged(int r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9(this, r3));
            return;
        }

        public void audioSinkError(Exception r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6(this, r3));
            return;
        }

        public void audioTrackInitialized(AudioSink.AudioTrackConfig r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, r3));
            return;
        }

        public void audioTrackReleased(AudioSink.AudioTrackConfig r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda12(this, r3));
            return;
        }

        public void decoderInitialized(String r9, long r10, long r12) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, r9, r10, r12));
            return;
        }

        public void decoderReleased(String r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, r3));
            return;
        }

        public void disabled(DecoderCounters r3) {
            r3.ensureUpdated();
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda10(this, r3));
            return;
        }

        public void enabled(DecoderCounters r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, r3));
            return;
        }

        public void inputFormatChanged(Format r3, DecoderReuseEvaluation r4) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(this, r3, r4));
            return;
        }

        public void positionAdvancing(long r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda11(this, r3));
            return;
        }

        public void skipSilenceEnabledChanged(boolean r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(this, r3));
            return;
        }

        public void underrun(int r9, long r10, long r12) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, r9, r10, r12));
            return;
        }
    }

    void onAudioCodecError(Exception r1);

    void onAudioDecoderInitialized(String r1, long r2, long r4);

    void onAudioDecoderReleased(String r1);

    void onAudioDisabled(DecoderCounters r1);

    void onAudioEnabled(DecoderCounters r1);

    void onAudioInputFormatChanged(Format r1, DecoderReuseEvaluation r2);

    void onAudioPositionAdvancing(long r1);

    void onAudioSessionIdChanged(int r1);

    void onAudioSinkError(Exception r1);

    void onAudioTrackInitialized(AudioSink.AudioTrackConfig r1);

    void onAudioTrackReleased(AudioSink.AudioTrackConfig r1);

    void onAudioUnderrun(int r1, long r2, long r4);

    void onSkipSilenceEnabledChanged(boolean r1);
}
