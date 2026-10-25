package androidx.emoji2.text.flatbuffer;

/* loaded from: classes.dex */
public abstract class Utf8 {
    private static Utf8 DEFAULT;

    public Utf8() {
    }

    public static Utf8 getDefault() {
        if (DEFAULT != null) goto L6;
        DEFAULT = new Utf8Safe();
    L6:
        return DEFAULT;
    }
}
