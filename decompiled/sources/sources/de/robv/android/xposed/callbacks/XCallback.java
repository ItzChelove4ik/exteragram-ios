package de.robv.android.xposed.callbacks;

/* loaded from: classes.dex */
public abstract class XCallback implements Comparable<XCallback> {
    public static final int PRIORITY_DEFAULT = 50;
    public static final int PRIORITY_HIGHEST = 10000;
    public static final int PRIORITY_LOWEST = -10000;
    public final int priority;

    public static abstract class Param {
        public Param() {
        }
    }

    @Deprecated
    public XCallback() {
        this.priority = 50;
    }

    public XCallback(int r1) {
        this.priority = r1;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(XCallback r3) {
        if (this != r3) goto L5;
        return 0;
    L5:
        int r0 = r3.priority;
        int r1 = this.priority;
        if (r0 == r1) goto L10;
        return Integer.compare(r0, r1);
    L10:
        if (System.identityHashCode(this) >= System.identityHashCode(r3)) goto L13;
        return -1;
    L13:
        return 1;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(XCallback r1) {
        return compareTo2(r1);
    }
}
