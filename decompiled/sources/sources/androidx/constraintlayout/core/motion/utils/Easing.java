package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes3.dex */
public class Easing {
    public static String[] NAMED_EASING;
    static Easing sDefault;
    String mStr;

    static {
        sDefault = new Easing();
        NAMED_EASING = new String[]{"standard", "accelerate", "decelerate", "linear"};
    }

    public Easing() {
        this.mStr = "identity";
    }

    public String toString() {
        return this.mStr;
    }
}
