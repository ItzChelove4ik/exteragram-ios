package androidx.work;

/* loaded from: classes.dex */
public interface RunnableScheduler {
    void cancel(Runnable r1);

    void scheduleWithDelay(long r1, Runnable r3);
}
