package org.telegram.ui.Components;

import android.graphics.CornerPathEffect;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import java.lang.ref.WeakReference;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class LinkPath extends CornerPath {
    private static CornerPathEffect roundedEffect;
    private static int roundedEffectRadius;
    private boolean allowReset;
    private int baselineShift;
    public float centerX;
    public float centerY;
    private WeakReference<Layout> currentLayout;
    private int currentLine;
    private float insetHoriz;
    private float insetVert;
    private float lastTop;
    private int lineHeight;
    private float maxX;
    private float maxY;
    private float minX;
    private float minY;
    private boolean useRoundRect;
    private float xOffset;
    private float yOffset;

    public LinkPath() {
        this.lastTop = -1.0f;
        this.allowReset = true;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.useCornerPathImplementation = false;
    }

    public LinkPath(boolean r2) {
        this.lastTop = -1.0f;
        this.allowReset = true;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.useRoundRect = r2;
        this.useCornerPathImplementation = false;
    }

    public static int getRadius() {
        return AndroidUtilities.dp(5.0f);
    }

    public static CornerPathEffect getRoundedEffect() {
        if (roundedEffect != null) goto L5;
    L6:
        int r1 = getRadius();
        roundedEffectRadius = r1;
        roundedEffect = new CornerPathEffect(r1);
    L8:
        return roundedEffect;
    L5:
        if (roundedEffectRadius == getRadius()) goto L8;
        goto L6
    }

    private void superAddRect(float r3, float r4, float r5, float r6, Path.Direction r7) {
        float r0 = this.insetHoriz;
        float r32 = r3 - r0;
        float r1 = this.insetVert;
        float r42 = r4 - r1;
        float r52 = r5 + r0;
        float r62 = r6 + r1;
        this.minX = Math.min(this.minX, Math.min(r32, r52));
        this.minY = Math.min(this.minY, Math.min(r42, r62));
        this.maxX = Math.max(this.maxX, Math.max(r32, r52));
        this.maxY = Math.max(this.maxY, Math.max(r42, r62));
        super.addRect(r32, r42, r52, r62, r7);
    }

    @Override // org.telegram.ui.Components.CornerPath, android.graphics.Path
    public void addRect(float r9, float r10, float r11, float r12, Path.Direction r13) {
        WeakReference<Layout> r1 = this.currentLayout;
        if (r1 != null) goto L5;
    L50:
        superAddRect(r9, r10, r11, r12, r13);
        return;
    L5:
        if (r1.get() == null) goto L50;
        float r14 = this.yOffset;     // Catch: Exception -> L52
        float r2 = r10 + r14;     // Catch: Exception -> L52
        float r15 = r14 + r12;     // Catch: Exception -> L52
        float r3 = this.lastTop;     // Catch: Exception -> L52
        if (r3 != (-1.0f)) goto L11;
        this.lastTop = r2;     // Catch: Exception -> L52
    L13:
        float r32 = this.currentLayout.get().getLineRight(this.currentLine);     // Catch: Exception -> L52
        float r4 = this.currentLayout.get().getLineLeft(this.currentLine);     // Catch: Exception -> L52
        if (r9 < r32) goto L16;
        return;
    L16:
        if (r9 > r4) goto L21;
        if (r11 > r4) goto L21;
        return;
    L21:
        if (r11 > r32) goto L24;
        r32 = r11;
    L24:
        if (r9 < r4) goto L27;
        r4 = r9;
    L27:
        float r5 = this.xOffset;     // Catch: Exception -> L52
        float r42 = r4 + r5;     // Catch: Exception -> L52
        float r33 = r32 + r5;     // Catch: Exception -> L52
        float r7 = 0.0f;
        if (Build.VERSION.SDK_INT < 28) goto L36;
        if ((r15 - r2) <= this.lineHeight) goto L39;
        float r52 = this.yOffset;     // Catch: Exception -> L52
        if (r15 == this.currentLayout.get().getHeight()) goto L34;
        r7 = this.currentLayout.get().getLineBottom(this.currentLine) - this.currentLayout.get().getSpacingAdd();     // Catch: Exception -> L52
    L34:
        r15 = r52 + r7;     // Catch: Exception -> L52
    L39:
        int r53 = this.baselineShift;     // Catch: Exception -> L52
        if (r53 >= 0) goto L42;
        r15 = r15 + r53;     // Catch: Exception -> L52
    L44:
        this.centerX = (r33 + r42) / 2.0f;     // Catch: Exception -> L52
        this.centerY = (r15 + r2) / 2.0f;     // Catch: Exception -> L52
        if (this.useRoundRect == false) goto L48;
        superAddRect(r42 - (getRadius() / 2.0f), r2, r33 + (getRadius() / 2.0f), r15, r13);     // Catch: Exception -> L52
        return;
    L48:
        superAddRect(r42, r2, r33, r15, r13);     // Catch: Exception -> L52
        return;
    L42:
        if (r53 <= 0) goto L44;
        r2 = r2 + r53;     // Catch: Exception -> L52
        goto L44
    L36:
        if (r15 == this.currentLayout.get().getHeight()) goto L38;
        r7 = this.currentLayout.get().getSpacingAdd();     // Catch: Exception -> L52
    L38:
        r15 = r15 - r7;     // Catch: Exception -> L52
        goto L39
    L11:
        if (r3 == r2) goto L13;
        this.lastTop = r2;     // Catch: Exception -> L52
        this.currentLine++;
        goto L13
    }

    public void getBounds(RectF r4) {
        r4.set(this.minX, this.minY, this.maxX, this.maxY);
    }

    @Override // org.telegram.ui.Components.CornerPath, android.graphics.Path
    public void reset() {
        if (this.allowReset == true) goto L5;
        return;
    L5:
        super.reset();
    }

    public void setAllowReset(boolean r1) {
        this.allowReset = r1;
    }

    public void setBaselineShift(int r1) {
        this.baselineShift = r1;
    }

    public void setCurrentLayout(Layout r2, int r3, float r4) {
        setCurrentLayout(r2, r3, 0.0f, r4);
    }

    public void setCurrentLayout(Layout r3, int r4, float r5, float r6) {
        if (r3 != null) goto L6;
        this.currentLayout = new WeakReference(null);
        this.currentLine = 0;
        this.lastTop = -1.0f;
        this.xOffset = r5;
        this.yOffset = r6;
        return;
    L6:
        this.currentLayout = new WeakReference(r3);
        this.currentLine = r3.getLineForOffset(r4);
        this.lastTop = -1.0f;
        this.xOffset = r5;
        this.yOffset = r6;
        if (Build.VERSION.SDK_INT < 28) goto L12;
        int r42 = r3.getLineCount();
        if (r42 <= 0) goto L13;
        int r43 = r42 - 1;
        this.lineHeight = r3.getLineBottom(r43) - r3.getLineTop(r43);
        return;
    L13:
        return;
    }

    public void setInset(float r1, float r2) {
        this.insetVert = r1;
        this.insetHoriz = r2;
    }
}
