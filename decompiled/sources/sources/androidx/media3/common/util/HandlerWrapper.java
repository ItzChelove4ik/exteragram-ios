package androidx.media3.common.util;

import android.os.Looper;

/* loaded from: classes3.dex */
public interface HandlerWrapper {

    public interface Message {
        void sendToTarget();
    }

    Looper getLooper();

    boolean hasMessages(int r1);

    Message obtainMessage(int r1);

    Message obtainMessage(int r1, int r2, int r3);

    Message obtainMessage(int r1, int r2, int r3, Object r4);

    Message obtainMessage(int r1, Object r2);

    boolean post(Runnable r1);

    void removeCallbacksAndMessages(Object r1);

    void removeMessages(int r1);

    boolean sendEmptyMessage(int r1);

    boolean sendEmptyMessageAtTime(int r1, long r2);

    boolean sendMessageAtFrontOfQueue(Message r1);
}
