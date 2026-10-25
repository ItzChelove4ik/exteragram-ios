package androidx.media3.exoplayer.drm;

import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public interface DrmSessionEventListener {

    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        public static final class ListenerAndHandler {
            public Handler handler;
            public DrmSessionEventListener listener;

            public ListenerAndHandler(Handler r1, DrmSessionEventListener r2) {
                this.handler = r1;
                this.listener = r2;
            }
        }

        public static /* synthetic */ void $r8$lambda$FZmhkUJjCJOweJPCCFjZLjJNWtc(EventDispatcher r1, DrmSessionEventListener r2) {
            r2.onDrmKeysRestored(r1.windowIndex, r1.mediaPeriodId);
        }

        /* renamed from: $r8$lambda$QIRI-Za0qXqJkW9wknSHMCcJ650, reason: not valid java name */
        public static /* synthetic */ void m828$r8$lambda$QIRIZa0qXqJkW9wknSHMCcJ650(EventDispatcher r1, DrmSessionEventListener r2) {
            r2.onDrmSessionReleased(r1.windowIndex, r1.mediaPeriodId);
        }

        public static /* synthetic */ void $r8$lambda$V33q3rpm5yjM_PkMti81oPYAbEI(EventDispatcher r1, DrmSessionEventListener r2, int r3) {
            r2.onDrmSessionAcquired(r1.windowIndex, r1.mediaPeriodId, r3);
        }

        public static /* synthetic */ void $r8$lambda$Znt3iJUnES200U3r25lljWYGsxc(EventDispatcher r1, DrmSessionEventListener r2) {
            r2.onDrmKeysLoaded(r1.windowIndex, r1.mediaPeriodId);
        }

        public static /* synthetic */ void $r8$lambda$jiTEqxfNzvbh6q0lTWIdHNoEmvs(EventDispatcher r1, DrmSessionEventListener r2, Exception r3) {
            r2.onDrmSessionManagerError(r1.windowIndex, r1.mediaPeriodId, r3);
        }

        public static /* synthetic */ void $r8$lambda$m76j6LJMU2LZD3pvSKguLNcD1Ec(EventDispatcher r1, DrmSessionEventListener r2) {
            r2.onDrmKeysRemoved(r1.windowIndex, r1.mediaPeriodId);
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> r1, int r2, MediaSource.MediaPeriodId r3) {
            this.listenerAndHandlers = r1;
            this.windowIndex = r2;
            this.mediaPeriodId = r3;
        }

        public void addEventListener(Handler r2, DrmSessionEventListener r3) {
            Assertions.checkNotNull(r2);
            Assertions.checkNotNull(r3);
            this.listenerAndHandlers.add(new ListenerAndHandler(r2, r3));
        }

        public void drmKeysLoaded() {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerAndHandler r1 = r0.next();
            DrmSessionEventListener r2 = r1.listener;
            Util.postOrRun(r1.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, r2));
            goto L4
        }

        public void drmKeysRemoved() {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerAndHandler r1 = r0.next();
            DrmSessionEventListener r2 = r1.listener;
            Util.postOrRun(r1.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, r2));
            goto L4
        }

        public void drmKeysRestored() {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerAndHandler r1 = r0.next();
            DrmSessionEventListener r2 = r1.listener;
            Util.postOrRun(r1.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, r2));
            goto L4
        }

        public void drmSessionAcquired(int r5) {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerAndHandler r1 = r0.next();
            DrmSessionEventListener r2 = r1.listener;
            Util.postOrRun(r1.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, r2, r5));
            goto L4
        }

        public void drmSessionManagerError(Exception r5) {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerAndHandler r1 = r0.next();
            DrmSessionEventListener r2 = r1.listener;
            Util.postOrRun(r1.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, r2, r5));
            goto L4
        }

        public void drmSessionReleased() {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerAndHandler r1 = r0.next();
            DrmSessionEventListener r2 = r1.listener;
            Util.postOrRun(r1.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, r2));
            goto L4
        }

        public void removeEventListener(DrmSessionEventListener r4) {
            Iterator<ListenerAndHandler> r0 = this.listenerAndHandlers.iterator();
        L4:
            if (r0.hasNext() == false) goto L8;
            ListenerAndHandler r1 = r0.next();
            if (r1.listener != r4) goto L4;
            this.listenerAndHandlers.remove(r1);
            goto L4
        }

        public EventDispatcher withParameters(int r2, MediaSource.MediaPeriodId r3) {
            return new EventDispatcher(this.listenerAndHandlers, r2, r3);
        }
    }

    void onDrmKeysLoaded(int r1, MediaSource.MediaPeriodId r2);

    void onDrmKeysRemoved(int r1, MediaSource.MediaPeriodId r2);

    void onDrmKeysRestored(int r1, MediaSource.MediaPeriodId r2);

    void onDrmSessionAcquired(int r1, MediaSource.MediaPeriodId r2, int r3);

    void onDrmSessionManagerError(int r1, MediaSource.MediaPeriodId r2, Exception r3);

    void onDrmSessionReleased(int r1, MediaSource.MediaPeriodId r2);
}
