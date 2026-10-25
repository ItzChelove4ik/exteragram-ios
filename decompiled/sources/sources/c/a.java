package c;

/* loaded from: classes4.dex */
public abstract class a {
    public static void a(String r2) {
        System.loadLibrary(r2);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        a$$ExternalSyntheticBUOutline0.m("Failed to load native library: ", r2, th);
    }
}
