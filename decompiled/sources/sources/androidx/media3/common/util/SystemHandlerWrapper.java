package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.util.HandlerWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
final class SystemHandlerWrapper implements HandlerWrapper {
    private static final List<SystemMessage> messagePool = null;
    private final Handler handler;

    /* renamed from: androidx.media3.common.util.SystemHandlerWrapper$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class SystemMessage implements HandlerWrapper.Message {
        private SystemHandlerWrapper handler;
        private Message message;

        private SystemMessage() {
        }

        public /* synthetic */ SystemMessage(AnonymousClass1 r1) {
            this();
        }

        private void recycle() {
            this.message = null;
            this.handler = null;
            SystemHandlerWrapper.access$100(this);
        }

        public boolean sendAtFrontOfQueue(Handler r2) {
            boolean r22 = r2.sendMessageAtFrontOfQueue((Message) Assertions.checkNotNull(this.message));
            recycle();
            return r22;
        }

        @Override // androidx.media3.common.util.HandlerWrapper.Message
        public void sendToTarget() {
            ((Message) Assertions.checkNotNull(this.message)).sendToTarget();
            recycle();
        }

        public SystemMessage setMessage(Message r1, SystemHandlerWrapper r2) {
            this.message = r1;
            this.handler = r2;
            return this;
        }
    }

    static {
        messagePool = new ArrayList(50);
    }

    public SystemHandlerWrapper(Handler r1) {
        this.handler = r1;
    }

    public static /* synthetic */ void access$100(SystemMessage r0) {
        recycleMessage(r0);
    }

    private static SystemMessage obtainSystemMessage() {
        List<SystemMessage> r0 = messagePool;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.isEmpty() == false) goto L9;
        SystemMessage r1 = new SystemMessage(null);     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L9:
        r1 = r0.remove(r0.size() - 1);     // Catch: Throwable -> L7
        goto L10
    }

    private static void recycleMessage(SystemMessage r3) {
        List<SystemMessage> r0 = messagePool;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.size() >= 50) goto L9;
        r0.add(r3);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public Looper getLooper() {
        return this.handler.getLooper();
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean hasMessages(int r2) {
        if (r2 == 0) goto L4;
        boolean r0 = true;
    L5:
        Assertions.checkArgument(r0);
        return this.handler.hasMessages(r2);
    L4:
        r0 = false;
        goto L5
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int r3) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(r3), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int r3, int r4, int r5) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(r3, r4, r5), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int r3, int r4, int r5, Object r6) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(r3, r4, r5, r6), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int r3, Object r4) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(r3, r4), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean post(Runnable r1) {
        return this.handler.post(r1);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public void removeCallbacksAndMessages(Object r1) {
        this.handler.removeCallbacksAndMessages(r1);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public void removeMessages(int r2) {
        if (r2 == 0) goto L4;
        boolean r0 = true;
    L5:
        Assertions.checkArgument(r0);
        this.handler.removeMessages(r2);
        return;
    L4:
        r0 = false;
        goto L5
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendEmptyMessage(int r1) {
        return this.handler.sendEmptyMessage(r1);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendEmptyMessageAtTime(int r1, long r2) {
        return this.handler.sendEmptyMessageAtTime(r1, r2);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendMessageAtFrontOfQueue(HandlerWrapper.Message r1) {
        return ((SystemMessage) r1).sendAtFrontOfQueue(this.handler);
    }
}
