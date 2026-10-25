package com.google.android.material.circularreveal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.math.MathUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mvel2.asm.Opcodes;

/* loaded from: classes5.dex */
public class CircularRevealHelper {
    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    private static final boolean DEBUG = false;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY = 2;
    private boolean buildingCircularRevealCache;
    private Paint debugPaint;
    private final Delegate delegate;
    private boolean hasCircularRevealCache;
    private Drawable overlayDrawable;
    private CircularRevealWidget.RevealInfo revealInfo;
    private final Paint revealPaint;
    private final Path revealPath;
    private final Paint scrimPaint;
    private final View view;

    public interface Delegate {
        void actualDraw(Canvas r1);

        boolean actualIsOpaque();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Strategy {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CircularRevealHelper(Delegate r3) {
        this.delegate = r3;
        View r32 = (View) r3;
        this.view = r32;
        r32.setWillNotDraw(false);
        this.revealPath = new Path();
        this.revealPaint = new Paint(7);
        Paint r33 = new Paint(1);
        this.scrimPaint = r33;
        r33.setColor(0);
    }

    private void drawDebugCircle(Canvas r4, int r5, float r6) {
        this.debugPaint.setColor(r5);
        this.debugPaint.setStrokeWidth(r6);
        CircularRevealWidget.RevealInfo r52 = this.revealInfo;
        r4.drawCircle(r52.centerX, r52.centerY, r52.radius - (r6 / 2.0f), this.debugPaint);
    }

    private void drawDebugMode(Canvas r5) {
        this.delegate.actualDraw(r5);
        if (shouldDrawScrim() == false) goto L6;
        CircularRevealWidget.RevealInfo r0 = this.revealInfo;
        r5.drawCircle(r0.centerX, r0.centerY, r0.radius, this.scrimPaint);
    L6:
        if (shouldDrawCircularReveal() == false) goto L8;
        drawDebugCircle(r5, -16777216, 10.0f);
        drawDebugCircle(r5, Opcodes.V_PREVIEW, 5.0f);
    L8:
        drawOverlayDrawable(r5);
    }

    private void drawOverlayDrawable(Canvas r5) {
        if (shouldDrawOverlayDrawable() == false) goto L6;
        Rect r0 = this.overlayDrawable.getBounds();
        float r1 = this.revealInfo.centerX - (r0.width() / 2.0f);
        float r2 = this.revealInfo.centerY - (r0.height() / 2.0f);
        r5.translate(r1, r2);
        this.overlayDrawable.draw(r5);
        r5.translate(-r1, -r2);
        return;
    }

    private float getDistanceToFurthestCorner(CircularRevealWidget.RevealInfo r7) {
        return MathUtils.distanceToFurthestCorner(r7.centerX, r7.centerY, 0.0f, 0.0f, this.view.getWidth(), this.view.getHeight());
    }

    private void invalidateRevealInfo() {
        this.view.invalidate();
    }

    private boolean shouldDrawCircularReveal() {
        CircularRevealWidget.RevealInfo r1 = this.revealInfo;
        if (r1 != null) goto L5;
    L8:
        boolean r12 = true;
    L10:
        return !r12;
    L5:
        if (r1.isInvalid() == true) goto L8;
        r12 = false;
        goto L10
    }

    private boolean shouldDrawOverlayDrawable() {
        if (this.buildingCircularRevealCache == false) goto L5;
        return false;
    L5:
        if (this.overlayDrawable != null) goto L7;
        return false;
    L7:
        if (this.revealInfo == null) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean shouldDrawScrim() {
        if (this.buildingCircularRevealCache == false) goto L5;
        return false;
    L5:
        if (Color.alpha(this.scrimPaint.getColor()) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void buildCircularRevealCache() {
    }

    public void destroyCircularRevealCache() {
    }

    public void draw(Canvas r14) {
        boolean r0 = shouldDrawCircularReveal();
        Delegate r1 = this.delegate;
        if (r0 == false) goto L8;
        r1.actualDraw(r14);
        if (shouldDrawScrim() == false) goto L7;
        r14.drawRect(0.0f, 0.0f, this.view.getWidth(), this.view.getHeight(), this.scrimPaint);
        Canvas r7 = r14;
    L11:
        drawOverlayDrawable(r7);
        return;
    L7:
        r7 = r14;
        goto L11
    L8:
        r7 = r14;
        r1.actualDraw(r7);
        if (shouldDrawScrim() == false) goto L11;
        r7.drawRect(0.0f, 0.0f, this.view.getWidth(), this.view.getHeight(), this.scrimPaint);
        goto L11
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.overlayDrawable;
    }

    public int getCircularRevealScrimColor() {
        return this.scrimPaint.getColor();
    }

    public CircularRevealWidget.RevealInfo getRevealInfo() {
        CircularRevealWidget.RevealInfo r0 = this.revealInfo;
        if (r0 != null) goto L6;
        return null;
    L6:
        CircularRevealWidget.RevealInfo r1 = new CircularRevealWidget.RevealInfo(r0);
        if (r1.isInvalid() == false) goto L9;
        r1.radius = getDistanceToFurthestCorner(r1);
    L9:
        return r1;
    }

    public boolean isOpaque() {
        if (this.delegate.actualIsOpaque() == true) goto L5;
        return false;
    L5:
        if (shouldDrawCircularReveal() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public void setCircularRevealOverlayDrawable(Drawable r1) {
        this.overlayDrawable = r1;
        this.view.invalidate();
    }

    public void setCircularRevealScrimColor(int r2) {
        this.scrimPaint.setColor(r2);
        this.view.invalidate();
    }

    public void setRevealInfo(CircularRevealWidget.RevealInfo r3) {
        if (r3 != null) goto L4;
        this.revealInfo = null;
    L11:
        invalidateRevealInfo();
        return;
    L4:
        CircularRevealWidget.RevealInfo r0 = this.revealInfo;
        if (r0 != null) goto L7;
        this.revealInfo = new CircularRevealWidget.RevealInfo(r3);
    L9:
        if (MathUtils.geq(r3.radius, getDistanceToFurthestCorner(r3), 1.0E-4f) == false) goto L11;
        this.revealInfo.radius = Float.MAX_VALUE;
        goto L11
    L7:
        r0.set(r3);
        goto L9
    }
}
