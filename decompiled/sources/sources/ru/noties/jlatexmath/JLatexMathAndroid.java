package ru.noties.jlatexmath;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.InputStream;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class JLatexMathAndroid {
    private static final String BASE = "org/scilab/forge/jlatexmath/";

    @SuppressLint({"StaticFieldLeak"})
    private static Context sContext;

    static {
    }

    private JLatexMathAndroid() {
    }

    private static Context context() {
        Context r0 = sContext;
        if (r0 == null) goto L5;
        return r0;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Please call `#init(Context)` method to initialize jLatexMath");
        return null;
    }

    public static InputStream getResourceAsStream(String r3) {
        return context().getAssets().open(BASE + r3);
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static void init(Context r0) {
        sContext = r0.getApplicationContext();
    }

    public static Typeface loadTypeface(String r3) {
        return Typeface.createFromAsset(context().getAssets(), BASE + r3);
    }
}
