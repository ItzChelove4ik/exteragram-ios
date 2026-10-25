package androidx.media3.exoplayer.upstream;

import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public interface BandwidthMeter {

    public interface EventListener {

        public static final class EventDispatcher {
            private final CopyOnWriteArrayList<HandlerAndListener> listeners;

            public static final class HandlerAndListener {
                private final Handler handler;
                private final EventListener listener;
                private boolean released;

                public HandlerAndListener(Handler r1, EventListener r2) {
                    this.handler = r1;
                    this.listener = r2;
                }

                public static /* synthetic */ EventListener access$000(HandlerAndListener r0) {
                    return r0.listener;
                }

                public static /* synthetic */ boolean access$100(HandlerAndListener r0) {
                    return r0.released;
                }

                public static /* synthetic */ Handler access$200(HandlerAndListener r0) {
                    return r0.handler;
                }

                public void release() {
                    this.released = true;
                }
            }

            /* renamed from: $r8$lambda$GKE-2Ys_1MhN-RoBQ2ySsnFXCbI, reason: not valid java name */
            public static /* synthetic */ void m847$r8$lambda$GKE2Ys_1MhNRoBQ2ySsnFXCbI(HandlerAndListener r0, int r1, long r2, long r4) {
                HandlerAndListener.access$000(r0).onBandwidthSample(r1, r2, r4);
            }

            public EventDispatcher() {
                this.listeners = new CopyOnWriteArrayList();
            }

            public void addListener(Handler r2, EventListener r3) {
                Assertions.checkNotNull(r2);
                Assertions.checkNotNull(r3);
                removeListener(r3);
                this.listeners.add(new HandlerAndListener(r2, r3));
            }

            public void bandwidthSample(int r9, long r10, long r12) {
                Iterator<HandlerAndListener> r8 = this.listeners.iterator();
            L4:
                if (r8.hasNext() == false) goto L10;
                HandlerAndListener r2 = r8.next();
                if (HandlerAndListener.access$100(r2) == true) goto L8;
                int r3 = r9;
                long r4 = r10;
                long r6 = r12;
                HandlerAndListener.access$200(r2).post(new BandwidthMeter$EventListener$EventDispatcher$$ExternalSyntheticLambda0(r2, r3, r4, r6));
            L9:
                r9 = r3;
                r10 = r4;
                r12 = r6;
                goto L4
            L8:
                r3 = r9;
                r4 = r10;
                r6 = r12;
                goto L9
            }

            public void removeListener(EventListener r4) {
                Iterator<HandlerAndListener> r0 = this.listeners.iterator();
            L4:
                if (r0.hasNext() == false) goto L8;
                HandlerAndListener r1 = r0.next();
                if (HandlerAndListener.access$000(r1) != r4) goto L4;
                r1.release();
                this.listeners.remove(r1);
                goto L4
            }
        }

        void onBandwidthSample(int r1, long r2, long r4);
    }

    void addEventListener(Handler r1, EventListener r2);

    long getBitrateEstimate();

    default long getTimeToFirstByteEstimateUs() {
        return -9223372036854775807L;
    }

    TransferListener getTransferListener();

    void removeEventListener(EventListener r1);
}
