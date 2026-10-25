package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public interface MediaSourceEventListener {

    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        public static final class ListenerAndHandler {
            public Handler handler;
            public MediaSourceEventListener listener;

            public ListenerAndHandler(Handler r1, MediaSourceEventListener r2) {
                this.handler = r1;
                this.listener = r2;
            }
        }

        public static /* synthetic */ void $r8$lambda$6pNO9KxCp680_uXaxiMfgnUnpBM(Consumer r0, MediaSourceEventListener r1) {
            r0.accept(r1);
        }

        public static /* synthetic */ void $r8$lambda$MkDhO1PQrN_SYW5nSwDKOMrpzXg(EventDispatcher r7, LoadEventInfo r8, MediaLoadData r9, IOException r10, boolean r11, MediaSourceEventListener r12) {
            r12.onLoadError(r7.windowIndex, r7.mediaPeriodId, r8, r9, r10, r11);
        }

        public static /* synthetic */ void $r8$lambda$RGKaOtjX2KMgFilg5dOIxU5Zg1o(EventDispatcher r6, LoadEventInfo r7, MediaLoadData r8, int r9, MediaSourceEventListener r10) {
            r10.onLoadStarted(r6.windowIndex, r6.mediaPeriodId, r7, r8, r9);
        }

        public static /* synthetic */ void $r8$lambda$SyhE931IzGC5Mv4nKNFYLEFNf1Y(EventDispatcher r0, MediaSource.MediaPeriodId r1, MediaLoadData r2, MediaSourceEventListener r3) {
            r3.onUpstreamDiscarded(r0.windowIndex, r1, r2);
        }

        /* renamed from: $r8$lambda$ZIHKe_Lg1hh0l8-iqVf4fKSaIDw, reason: not valid java name */
        public static /* synthetic */ void m838$r8$lambda$ZIHKe_Lg1hh0l8iqVf4fKSaIDw(EventDispatcher r1, MediaLoadData r2, MediaSourceEventListener r3) {
            r3.onDownstreamFormatChanged(r1.windowIndex, r1.mediaPeriodId, r2);
        }

        public static /* synthetic */ void $r8$lambda$fKow8_I34ijIYwgWcGYrirajqR0(EventDispatcher r1, LoadEventInfo r2, MediaLoadData r3, MediaSourceEventListener r4) {
            r4.onLoadCompleted(r1.windowIndex, r1.mediaPeriodId, r2, r3);
        }

        /* renamed from: $r8$lambda$sbgTCxAqCErzXF0AaGrt-a_O2mU, reason: not valid java name */
        public static /* synthetic */ void m839$r8$lambda$sbgTCxAqCErzXF0AaGrta_O2mU(EventDispatcher r1, LoadEventInfo r2, MediaLoadData r3, MediaSourceEventListener r4) {
            r4.onLoadCanceled(r1.windowIndex, r1.mediaPeriodId, r2, r3);
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> r1, int r2, MediaSource.MediaPeriodId r3) {
            this.listenerAndHandlers = r1;
            this.windowIndex = r2;
            this.mediaPeriodId = r3;
        }

        public void addEventListener(Handler r2, MediaSourceEventListener r3) {
            Assertions.checkNotNull(r2);
            Assertions.checkNotNull(r3);
            this.listenerAndHandlers.add(new ListenerAndHandler(r2, r3));
        }

        public void dispatchEvent(Consumer<MediaSourceEventListener> r4) {
            Iterator<ListenerAndHandler> r3 = this.listenerAndHandlers.iterator();
        L4:
            if (r3.hasNext() == false) goto L6;
            ListenerAndHandler r0 = r3.next();
            MediaSourceEventListener r1 = r0.listener;
            Util.postOrRun(r0.handler, new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda6(r4, r1));
            goto L4
        }

        public void downstreamFormatChanged(int r11, Format r12, int r13, Object r14, long r15) {
            downstreamFormatChanged(new MediaLoadData(1, r11, r12, r13, r14, Util.usToMs(r15), -9223372036854775807L));
        }

        public void downstreamFormatChanged(MediaLoadData r2) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, r2));
        }

        public void loadCanceled(LoadEventInfo r12, int r13) {
            loadCanceled(r12, r13, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L);
        }

        public void loadCanceled(LoadEventInfo r11, int r12, int r13, Format r14, int r15, Object r16, long r17, long r19) {
            loadCanceled(r11, new MediaLoadData(r12, r13, r14, r15, r16, Util.usToMs(r17), Util.usToMs(r19)));
        }

        public void loadCanceled(LoadEventInfo r2, MediaLoadData r3) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, r2, r3));
        }

        public void loadCompleted(LoadEventInfo r12, int r13) {
            loadCompleted(r12, r13, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L);
        }

        public void loadCompleted(LoadEventInfo r11, int r12, int r13, Format r14, int r15, Object r16, long r17, long r19) {
            loadCompleted(r11, new MediaLoadData(r12, r13, r14, r15, r16, Util.usToMs(r17), Util.usToMs(r19)));
        }

        public void loadCompleted(LoadEventInfo r2, MediaLoadData r3) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, r2, r3));
        }

        public void loadError(LoadEventInfo r11, int r12, int r13, Format r14, int r15, Object r16, long r17, long r19, IOException r21, boolean r22) {
            loadError(r11, new MediaLoadData(r12, r13, r14, r15, r16, Util.usToMs(r17), Util.usToMs(r19)), r21, r22);
        }

        public void loadError(LoadEventInfo r14, int r15, IOException r16, boolean r17) {
            loadError(r14, r15, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, r16, r17);
        }

        public void loadError(LoadEventInfo r7, MediaLoadData r8, IOException r9, boolean r10) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, r7, r8, r9, r10));
        }

        public void loadStarted(LoadEventInfo r13, int r14, int r15) {
            loadStarted(r13, r14, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, r15);
        }

        public void loadStarted(LoadEventInfo r11, int r12, int r13, Format r14, int r15, Object r16, long r17, long r19, int r21) {
            loadStarted(r11, new MediaLoadData(r12, r13, r14, r15, r16, Util.usToMs(r17), Util.usToMs(r19)), r21);
        }

        public void loadStarted(LoadEventInfo r2, MediaLoadData r3, int r4) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, r2, r3, r4));
        }

        public void removeEventListener(MediaSourceEventListener r4) {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L8;
            ListenerAndHandler r1 = r0.next();
            if (r1.listener != r4) goto L4;
            this.listenerAndHandlers.remove(r1);
            goto L4
        }

        public void upstreamDiscarded(int r11, long r12, long r14) {
            upstreamDiscarded(new MediaLoadData(1, r11, null, 3, null, Util.usToMs(r12), Util.usToMs(r14)));
        }

        public void upstreamDiscarded(MediaLoadData r3) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId), r3));
        }

        public EventDispatcher withParameters(int r2, MediaSource.MediaPeriodId r3) {
            return new EventDispatcher(this.listenerAndHandlers, r2, r3);
        }
    }

    void onDownstreamFormatChanged(int r1, MediaSource.MediaPeriodId r2, MediaLoadData r3);

    void onLoadCanceled(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4);

    void onLoadCompleted(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4);

    void onLoadError(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4, IOException r5, boolean r6);

    void onLoadStarted(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4, int r5);

    void onUpstreamDiscarded(int r1, MediaSource.MediaPeriodId r2, MediaLoadData r3);
}
