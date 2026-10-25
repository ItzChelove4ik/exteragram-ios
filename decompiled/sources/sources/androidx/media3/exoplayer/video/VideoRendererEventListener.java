package androidx.media3.exoplayer.video;

import android.os.Handler;
import android.os.SystemClock;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;

/* loaded from: classes4.dex */
public interface VideoRendererEventListener {

    public static final class EventDispatcher {
        private final Handler handler;
        private final VideoRendererEventListener listener;

        public static /* synthetic */ void $r8$lambda$3d5qzAJzOG_kuEfp2g3VIBP3hOw(EventDispatcher r6, String r7, long r8, long r10) {
            ((VideoRendererEventListener) Util.castNonNull(r6.listener)).onVideoDecoderInitialized(r7, r8, r10);
        }

        public static /* synthetic */ void $r8$lambda$BS3v4lfsbIsC1pS1t4MOpgJmItM(EventDispatcher r0, Exception r1) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoCodecError(r1);
        }

        /* renamed from: $r8$lambda$FATKILHOILtblmax_24wXXpjB-o, reason: not valid java name */
        public static /* synthetic */ void m851$r8$lambda$FATKILHOILtblmax_24wXXpjBo(EventDispatcher r0, int r1, long r2) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onDroppedFrames(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$R8pPANH_yLr6RjVhX7e7MTZhKvw(EventDispatcher r0, DecoderCounters r1) {
            r0.getClass();
            r1.ensureUpdated();
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoDisabled(r1);
        }

        public static /* synthetic */ void $r8$lambda$RCDE6TO4DjhzG9D1RvBEW0QdJ1Q(EventDispatcher r0, DecoderCounters r1) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoEnabled(r1);
        }

        public static /* synthetic */ void $r8$lambda$hYpsisbOw8XuChb4SVjo3uhAwzQ(EventDispatcher r0, VideoSize r1) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoSizeChanged(r1);
        }

        /* renamed from: $r8$lambda$kuS-aucIvnEpIGflCbxQMWvwx9A, reason: not valid java name */
        public static /* synthetic */ void m852$r8$lambda$kuSaucIvnEpIGflCbxQMWvwx9A(EventDispatcher r0, long r1, int r3) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoFrameProcessingOffset(r1, r3);
        }

        public static /* synthetic */ void $r8$lambda$mox6ykqhlpdIdUZdrU6RYbR_khI(EventDispatcher r0, Format r1, DecoderReuseEvaluation r2) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoInputFormatChanged(r1, r2);
        }

        /* renamed from: $r8$lambda$pJR4DhexRjdqGlem2_e-y6dQsHw, reason: not valid java name */
        public static /* synthetic */ void m853$r8$lambda$pJR4DhexRjdqGlem2_ey6dQsHw(EventDispatcher r0, String r1) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onVideoDecoderReleased(r1);
        }

        public static /* synthetic */ void $r8$lambda$uPa6eK25oSO4e0vXNWIOHayyzMU(EventDispatcher r0, Object r1, long r2) {
            ((VideoRendererEventListener) Util.castNonNull(r0.listener)).onRenderedFirstFrame(r1, r2);
        }

        public EventDispatcher(Handler r1, VideoRendererEventListener r2) {
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

        public void decoderInitialized(String r9, long r10, long r12) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, r9, r10, r12));
            return;
        }

        public void decoderReleased(String r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, r3));
            return;
        }

        public void disabled(DecoderCounters r3) {
            r3.ensureUpdated();
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9(this, r3));
            return;
        }

        public void droppedFrames(int r3, long r4) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, r3, r4));
            return;
        }

        public void enabled(DecoderCounters r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(this, r3));
            return;
        }

        public void inputFormatChanged(Format r3, DecoderReuseEvaluation r4) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(this, r3, r4));
            return;
        }

        public void renderedFirstFrame(Object r5) {
            if (this.handler == null) goto L6;
            long r0 = SystemClock.elapsedRealtime();
            this.handler.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, r5, r0));
            return;
        }

        public void reportVideoFrameProcessingOffset(long r3, int r5) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6(this, r3, r5));
            return;
        }

        public void videoCodecError(Exception r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, r3));
            return;
        }

        public void videoSizeChanged(VideoSize r3) {
            Handler r0 = this.handler;
            if (r0 == null) goto L6;
            r0.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, r3));
            return;
        }
    }

    void onDroppedFrames(int r1, long r2);

    void onRenderedFirstFrame(Object r1, long r2);

    void onVideoCodecError(Exception r1);

    void onVideoDecoderInitialized(String r1, long r2, long r4);

    void onVideoDecoderReleased(String r1);

    void onVideoDisabled(DecoderCounters r1);

    void onVideoEnabled(DecoderCounters r1);

    void onVideoFrameProcessingOffset(long r1, int r3);

    void onVideoInputFormatChanged(Format r1, DecoderReuseEvaluation r2);

    void onVideoSizeChanged(VideoSize r1);
}
