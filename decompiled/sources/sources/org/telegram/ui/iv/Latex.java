package org.telegram.ui.iv;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import ru.noties.jlatexmath.JLatexMathAndroid;
import ru.noties.jlatexmath.JLatexMathDrawable;

/* loaded from: classes7.dex */
public final class Latex {
    private static volatile boolean sInitialized = false;
    public final Bitmap bitmap;
    public final int depth;
    public final int height;
    public final int width;

    static {
    }

    private Latex(Bitmap r1, int r2, int r3, int r4) {
        this.bitmap = r1;
        this.width = r2;
        this.height = r3;
        this.depth = r4;
    }

    private static void ensureInitialized() {
        if (sInitialized == false) goto L5;
        return;
    L5:
        monitor-enter(Latex.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (sInitialized == true) goto L11;
        JLatexMathAndroid.init(ApplicationLoader.applicationContext);     // Catch: Throwable -> L9
        sInitialized = true;     // Catch: Throwable -> L9
    L11:
        monitor-exit(Latex.class);     // Catch: Throwable -> L9
    }

    public static Latex render(String r5, float r6, boolean r7) {
        if (r5 != null) goto L5;
    L23:
        return null;
    L5:
        if (r5.isEmpty() == true) goto L23;
        ensureInitialized();     // Catch: Throwable -> L17
        JLatexMathDrawable r52 = JLatexMathDrawable.builder(r5).textSize(r6).build();     // Catch: Throwable -> L17
        int r62 = r52.getIntrinsicWidth();     // Catch: Throwable -> L17
        int r1 = r52.getIntrinsicHeight();     // Catch: Throwable -> L17
        if (r62 <= 0) goto L21;
        if (r1 <= 0) goto L21;
        Bitmap r2 = Bitmap.createBitmap(r62, r1, Bitmap.Config.ALPHA_8);     // Catch: Throwable -> L17
        int r3 = 0;
        r52.setBounds(0, 0, r62, r1);     // Catch: Throwable -> L17
        r52.draw(new Canvas(r2));     // Catch: Throwable -> L17
        if (r7 == true) goto L24;
    L19:
        return new Latex(r2, r62, r1, r3);
    L24:
        r3 = r52.icon().getIconDepth();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L17
    L21:
        return null;
    L17:
        th = move-exception;
        FileLog.e(th);
        goto L23
    }
}
