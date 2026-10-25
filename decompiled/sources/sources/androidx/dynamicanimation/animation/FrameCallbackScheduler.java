package androidx.dynamicanimation.animation;

/* loaded from: classes3.dex */
public interface FrameCallbackScheduler {
    boolean isCurrentThread();

    void postFrameCallback(Runnable r1);
}
