package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.ui.Components.BitmapShaderTools;

/* loaded from: classes7.dex */
public class VoIPBackgroundProvider {
    private final Paint darkPaint;
    private final BitmapShaderTools darkShaderTools;
    private final Paint darkVideoPaint;
    private int degree;
    private boolean hasVideo;
    private boolean isReveal;
    private final BitmapShaderTools lightShaderTools;
    private BitmapShaderTools revealDarkShaderTools;
    private BitmapShaderTools revealShaderTools;
    public final float scale;
    private int totalHeight;
    private int totalWidth;
    private final List<View> views;
    private final Paint whiteVideoPaint;

    /* renamed from: org.telegram.ui.Components.voip.VoIPBackgroundProvider$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPBackgroundProvider this$0;

        public AnonymousClass1(VoIPBackgroundProvider r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            VoIPBackgroundProvider.m13948$$Nest$fputhasVideo(this.this$0, false);
            VoIPBackgroundProvider.m13945$$Nest$fgetdarkPaint(this.this$0).setAlpha(35);
            VoIPBackgroundProvider.m13946$$Nest$fgetdarkVideoPaint(this.this$0).setAlpha(102);
            VoIPBackgroundProvider.m13947$$Nest$fgetwhiteVideoPaint(this.this$0).setAlpha(35);
            this.this$0.invalidateViews();
        }
    }

    /* renamed from: $r8$lambda$9oG720Z-SXPTRI3waDI7unArRvE, reason: not valid java name */
    public static /* synthetic */ void m13944$r8$lambda$9oG720ZSXPTRI3waDI7unArRvE(VoIPBackgroundProvider r0, ValueAnimator r1) {
        r0.lambda$setHasVideo$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$KnXY2IJToD0CGoQIyTcZTnbdJuw(VoIPBackgroundProvider r0, ValueAnimator r1) {
        r0.lambda$setHasVideo$0(r1);
    }

    /* renamed from: -$$Nest$fgetdarkPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13945$$Nest$fgetdarkPaint(VoIPBackgroundProvider r0) {
        return r0.darkPaint;
    }

    /* renamed from: -$$Nest$fgetdarkVideoPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13946$$Nest$fgetdarkVideoPaint(VoIPBackgroundProvider r0) {
        return r0.darkVideoPaint;
    }

    /* renamed from: -$$Nest$fgetwhiteVideoPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13947$$Nest$fgetwhiteVideoPaint(VoIPBackgroundProvider r0) {
        return r0.whiteVideoPaint;
    }

    /* renamed from: -$$Nest$fputhasVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13948$$Nest$fputhasVideo(VoIPBackgroundProvider r0, boolean r1) {
        r0.hasVideo = r1;
    }

    public VoIPBackgroundProvider() {
        BitmapShaderTools r0 = new BitmapShaderTools(80, 80);
        this.lightShaderTools = r0;
        BitmapShaderTools r2 = new BitmapShaderTools(80, 80);
        this.darkShaderTools = r2;
        this.totalWidth = 0;
        this.totalHeight = 0;
        Paint r1 = new Paint(1);
        this.whiteVideoPaint = r1;
        Paint r4 = new Paint(1);
        this.darkVideoPaint = r4;
        Paint r5 = new Paint(1);
        this.darkPaint = r5;
        this.views = new ArrayList();
        this.scale = 1.12f;
        r2.setBounds(0.0f, 0.0f, 80.0f, 80.0f);
        r0.setBounds(0.0f, 0.0f, 80.0f, 80.0f);
        r1.setColor(-1);
        r1.setAlpha(35);
        r4.setColor(-16777216);
        r4.setAlpha(102);
        r5.setColor(-16777216);
        r5.setAlpha(35);
        r2.paint.setAlpha(180);
    }

    private /* synthetic */ void lambda$setHasVideo$0(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        int r1 = (int) (35.0f * r42);
        this.darkPaint.setAlpha(r1);
        this.darkVideoPaint.setAlpha((int) (r42 * 102.0f));
        this.whiteVideoPaint.setAlpha(r1);
        invalidateViews();
    }

    private /* synthetic */ void lambda$setHasVideo$1(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.darkShaderTools.paint.setAlpha((int) (180.0f * r32));
        this.lightShaderTools.paint.setAlpha((int) (r32 * 255.0f));
        invalidateViews();
    }

    public void attach(View r1) {
        this.views.add(r1);
    }

    public void detach(View r1) {
        this.views.remove(r1);
    }

    public Canvas getDarkCanvas() {
        return this.darkShaderTools.getCanvas();
    }

    public Paint getDarkPaint() {
        if (this.hasVideo == false) goto L7;
        return this.darkVideoPaint;
    L7:
        return this.darkShaderTools.paint;
    }

    public Paint getDarkPaint(boolean r1) {
        if (r1 == false) goto L6;
        return this.darkPaint;
    L6:
        return getDarkPaint();
    }

    public int getDegree() {
        return this.degree;
    }

    public Canvas getLightCanvas() {
        return this.lightShaderTools.getCanvas();
    }

    public Paint getLightPaint() {
        if (this.hasVideo == false) goto L7;
        return this.whiteVideoPaint;
    L7:
        return this.lightShaderTools.paint;
    }

    public Canvas getRevealCanvas() {
        return this.revealShaderTools.getCanvas();
    }

    public Paint getRevealDarkPaint() {
        return this.revealDarkShaderTools.paint;
    }

    public Canvas getRevealDrakCanvas() {
        return this.revealDarkShaderTools.getCanvas();
    }

    public Paint getRevealPaint() {
        return this.revealShaderTools.paint;
    }

    public void invalidateViews() {
        Iterator<View> r1 = this.views.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().invalidate();
        goto L4
    }

    public boolean isReveal() {
        return this.isReveal;
    }

    public void setDarkTranslation(float r8, float r9) {
        float r0 = this.totalHeight * 1.12f;
        float r4 = -r8;
        float r5 = -r9;
        this.darkShaderTools.setMatrix(r4 - ((r0 - this.totalWidth) / 2.0f), r5 - ((r0 - this.totalHeight) / 2.0f), r0 / this.darkShaderTools.getBitmap().getHeight(), this.degree);
        this.revealDarkShaderTools.setBounds(r4, r5, this.totalWidth - r8, this.totalHeight - r9);
    }

    public void setDegree(int r1) {
        this.degree = r1;
        invalidateViews();
    }

    public void setHasVideo(boolean r5) {
        if (this.hasVideo == false) goto L6;
        if (r5 == true) goto L6;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r0.addUpdateListener(new VoIPBackgroundProvider$$ExternalSyntheticLambda0(this));
        r0.setInterpolator(new LinearInterpolator());
        r0.setDuration(80);
        r0.addListener(new AnonymousClass1(this));
        r0.start();
        ValueAnimator r52 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r52.addUpdateListener(new VoIPBackgroundProvider$$ExternalSyntheticLambda1(this));
        r52.setInterpolator(new LinearInterpolator());
        r52.setStartDelay(80);
        r52.setDuration(80);
        r52.start();
    L7:
        invalidateViews();
        return;
    L6:
        this.hasVideo = r5;
        goto L7
    }

    public void setLightTranslation(float r8, float r9) {
        int r2 = this.totalHeight;
        float r3 = ((r2 * 1.12f) - this.totalWidth) / 2.0f;
        float r5 = ((r2 * 1.12f) - r2) / 2.0f;
        float r22 = -r8;
        float r4 = -r9;
        this.lightShaderTools.setMatrix(r22 - r3, r4 - r5, (this.totalHeight * 1.12f) / this.lightShaderTools.getBitmap().getHeight(), this.degree);
        this.revealShaderTools.setBounds(r22, r4, this.totalWidth - r8, this.totalHeight - r9);
    }

    public void setReveal(boolean r1) {
        this.isReveal = r1;
    }

    public void setTotalSize(int r2, int r3) {
        this.totalWidth = r2;
        this.totalHeight = r3;
        int r22 = r2 / 4;
        int r32 = r3 / 4;
        this.revealShaderTools = new BitmapShaderTools(r22, r32);
        BitmapShaderTools r0 = new BitmapShaderTools(r22, r32);
        this.revealDarkShaderTools = r0;
        r0.paint.setAlpha(180);
    }
}
