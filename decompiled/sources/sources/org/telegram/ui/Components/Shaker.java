package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.view.View;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class Shaker {
    private final Runnable invalidate;
    private final float r;
    private final long start;
    private final float sx;
    private final float sy;

    public Shaker(View r2) {
        Objects.requireNonNull(r2);
        this(new Shaker$$ExternalSyntheticLambda0(r2));
    }

    public Shaker(Runnable r3) {
        this.start = System.currentTimeMillis();
        this.invalidate = r3;
        this.r = AndroidUtilities.lerp(5.0f, 9.0f, Utilities.clamp01(Utilities.fastRandom.nextFloat()));
        this.sx = AndroidUtilities.lerp(2.5f, 5.0f, Utilities.clamp01(Utilities.fastRandom.nextFloat()));
        this.sy = AndroidUtilities.lerp(2.5f, 5.2f, Utilities.clamp01(Utilities.fastRandom.nextFloat()));
    }

    public void concat(Canvas r2, float r3) {
        concat(r2, r3, 0.0f, 0.0f);
    }

    public void concat(Canvas r8, float r9, float r10, float r11) {
        float r0 = (System.currentTimeMillis() - this.start) / 1000.0f;
        r8.translate(r10, r11);
        r8.rotate((((float) Math.sin((this.r * r0) * 3.141592653589793d)) * 1.0f) * r9);
        r8.translate((((float) Math.cos((this.sx * r0) * 3.141592653589793d)) * AndroidUtilities.dp(0.5f)) * r9, (((float) Math.sin((r0 * this.sy) * 3.141592653589793d)) * AndroidUtilities.dp(0.5f)) * r9);
        r8.translate(-r10, -r11);
        if (r9 <= 0.0f) goto L8;
        Runnable r7 = this.invalidate;
        if (r7 == null) goto L9;
        r7.run();
        return;
    L9:
        return;
    }
}
