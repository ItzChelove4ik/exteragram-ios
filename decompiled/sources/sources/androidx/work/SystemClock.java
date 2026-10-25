package androidx.work;

/* loaded from: classes.dex */
public class SystemClock implements Clock {
    public SystemClock() {
    }

    @Override // androidx.work.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
