package me.vkryl.core;

/* loaded from: classes.dex */
public abstract class BitwiseUtils {
    public static boolean hasFlag(int r0, int r1) {
        if ((r0 & r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static int setFlag(int r0, int r1, boolean r2) {
        if (r2 == false) goto L6;
        return r0 | r1;
    L6:
        return r0 & (~r1);
    }
}
