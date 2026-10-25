package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.text.TextPaint;
import android.view.View;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ProfileGalleryView;

/* loaded from: classes6.dex */
public abstract class AvatarPreviewPagerIndicator extends View implements ProfileGalleryView.Callback {
    private float alpha;
    private float[] alphas;
    private final ValueAnimator animator;
    private final float[] animatorValues;
    private final Paint backgroundPaint;
    private final Paint barPaint;
    private final GradientDrawable bottomOverlayGradient;
    private final Rect bottomOverlayRect;
    private float currentAnimationValue;
    private int currentLoadingAnimationDirection;
    private float currentLoadingAnimationProgress;
    private float currentProgress;
    private final RectF indicatorRect;
    private boolean isOverlaysVisible;
    int lastCurrentItem;
    private long lastTime;
    private int overlayCountVisible;
    Path path;
    private final float[] pressedOverlayAlpha;
    private final GradientDrawable[] pressedOverlayGradient;
    private final boolean[] pressedOverlayVisible;
    private int previousSelectedPotision;
    private float previousSelectedProgress;
    protected ProfileGalleryView profileGalleryView;
    private float progressToCounter;
    private final RectF rect;
    RectF rectF;
    private final Paint selectedBarPaint;
    private int selectedPosition;
    private final int statusBarHeight;
    TextPaint textPaint;
    String title;
    private final GradientDrawable topOverlayGradient;
    private final Rect topOverlayRect;

    /* renamed from: org.telegram.ui.AvatarPreviewPagerIndicator$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ AvatarPreviewPagerIndicator this$0;

        public AnonymousClass1(AvatarPreviewPagerIndicator r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (AvatarPreviewPagerIndicator.m6247$$Nest$fgetisOverlaysVisible(this.this$0) == true) goto L6;
            this.this$0.setVisibility(8);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.setVisibility(0);
        }
    }

    /* renamed from: $r8$lambda$clB-3631wZpcCbTiNFjHmLNYl9M, reason: not valid java name */
    public static /* synthetic */ void m6246$r8$lambda$clB3631wZpcCbTiNFjHmLNYl9M(AvatarPreviewPagerIndicator r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetisOverlaysVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6247$$Nest$fgetisOverlaysVisible(AvatarPreviewPagerIndicator r0) {
        return r0.isOverlaysVisible;
    }

    public AvatarPreviewPagerIndicator(Context r9) {
        super(r9);
        this.indicatorRect = new RectF();
        this.statusBarHeight = 0;
        this.overlayCountVisible = 1;
        this.topOverlayRect = new Rect();
        this.bottomOverlayRect = new Rect();
        this.rect = new RectF();
        this.animatorValues = new float[]{0.0f, 1.0f};
        this.path = new Path();
        this.rectF = new RectF();
        this.pressedOverlayGradient = new GradientDrawable[2];
        this.pressedOverlayVisible = new boolean[2];
        this.pressedOverlayAlpha = new float[2];
        this.alpha = 0.0f;
        this.alphas = null;
        this.previousSelectedPotision = -1;
        this.currentLoadingAnimationDirection = 1;
        this.lastCurrentItem = -1;
        Paint r3 = new Paint(1);
        this.barPaint = r3;
        r3.setColor(1442840575);
        Paint r32 = new Paint(1);
        this.selectedBarPaint = r32;
        r32.setColor(-1);
        GradientDrawable r33 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{1107296256, 0});
        this.topOverlayGradient = r33;
        r33.setShape(0);
        GradientDrawable r34 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{1107296256, 0});
        this.bottomOverlayGradient = r34;
        r34.setShape(0);
        int r35 = 0;
    L3:
        if (r35 >= 2) goto L8;
        if (r35 != 0) goto L6;
        GradientDrawable.Orientation r4 = GradientDrawable.Orientation.LEFT_RIGHT;
    L7:
        this.pressedOverlayGradient[r35] = new GradientDrawable(r4, new int[]{838860800, 0});
        this.pressedOverlayGradient[r35].setShape(0);
        r35 = r35 + 1;
        goto L3
    L6:
        r4 = GradientDrawable.Orientation.RIGHT_LEFT;
        goto L7
    L8:
        Paint r92 = new Paint(1);
        this.backgroundPaint = r92;
        r92.setColor(-16777216);
        r92.setAlpha(66);
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r93;
        r93.setDuration(250);
        r93.setInterpolator(CubicBezierInterpolator.EASE_BOTH);
        r93.addUpdateListener(new AvatarPreviewPagerIndicator$$ExternalSyntheticLambda0(this));
        r93.addListener(new AnonymousClass1(this));
        TextPaint r94 = new TextPaint(1);
        this.textPaint = r94;
        r94.setColor(-1);
        this.textPaint.setTypeface(Typeface.SANS_SERIF);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setTextSize(AndroidUtilities.dpf2(15.0f));
    }

    private String getCurrentTitle() {
        if (this.lastCurrentItem == this.profileGalleryView.getCurrentItem()) goto L6;
        this.title = this.profileGalleryView.getAdapter().getPageTitle(this.profileGalleryView.getCurrentItem()).toString();
        this.lastCurrentItem = this.profileGalleryView.getCurrentItem();
    L6:
        return this.title;
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r2) {
        float[] r0 = this.animatorValues;
        float r22 = r2.getAnimatedFraction();
        this.currentAnimationValue = r22;
        setAlphaValue(AndroidUtilities.lerp(r0, r22), true);
    }

    public ProfileGalleryView getProfileGalleryView() {
        return this.profileGalleryView;
    }

    @Override // org.telegram.ui.Components.ProfileGalleryView.Callback
    public void onDown(boolean r3) {
        this.pressedOverlayVisible[!r3 ? 1 : 0] = true;
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public void onDraw(Canvas r26) {
        int r3 = 0;
    L3:
        float r6 = 0.0f;
        if (r3 >= 2) goto L9;
        float r5 = this.pressedOverlayAlpha[r3];
        if (r5 <= 0.0f) goto L8;
        this.pressedOverlayGradient[r3].setAlpha((int) (r5 * 255.0f));
        this.pressedOverlayGradient[r3].draw(r26);
    L8:
        r3 = r3 + 1;
        goto L3
    L9:
        this.topOverlayGradient.draw(r26);
        r26.drawRect(this.topOverlayRect, this.backgroundPaint);
        int r32 = this.profileGalleryView.getRealCount();
        this.selectedPosition = this.profileGalleryView.getRealPosition();
        float[] r7 = this.alphas;
        if (r7 != null) goto L12;
    L13:
        float[] r72 = new float[r32];
        this.alphas = r72;
        Arrays.fill(r72, 0.0f);
    L14:
        long r73 = SystemClock.elapsedRealtime();
        long r9 = r73 - this.lastTime;
        if (r9 >= 0) goto L17;
    L18:
        r9 = 17;
    L19:
        this.lastTime = r73;
        float r8 = 8.0f;
        if (r32 <= 1) goto L98;
        if (r32 > 20) goto L98;
        int r14 = this.overlayCountVisible;
        if (r14 != 0) goto L25;
        this.alpha = 0.0f;
        this.overlayCountVisible = 3;
    L28:
        if (this.overlayCountVisible != 2) goto L30;
        this.barPaint.setAlpha((int) (this.alpha * 85.0f));
        this.selectedBarPaint.setAlpha((int) (this.alpha * 255.0f));
    L30:
        int r2 = ((getMeasuredWidth() - AndroidUtilities.dp(10.0f)) - AndroidUtilities.dp((r32 - 1) * 2)) / r32;
        int r4 = AndroidUtilities.dp(8.0f);
        int r142 = 0;
        boolean r17 = false;
    L32:
        if (r142 >= r32) goto L69;
        int r74 = AndroidUtilities.dp((r142 * 2) + 5) + (r2 * r142);
        float r19 = r8;
        if (r142 == this.previousSelectedPotision) goto L36;
    L39:
        float r24 = r6;
        int r62 = 85;
        if (r142 == this.selectedPosition) goto L42;
    L57:
        float r82 = 1.0f;
    L58:
        float r75 = r74;
        this.rect.set(r75, r4, (r2 * r82) + r75, AndroidUtilities.dp(2.0f) + r4);
        if (r142 != this.selectedPosition) goto L61;
        this.alphas[r142] = 0.75f;
    L64:
        RectF r52 = this.rect;
        float r63 = AndroidUtilities.dp(1.0f);
        float r76 = AndroidUtilities.dp(1.0f);
        if (r142 != this.selectedPosition) goto L67;
        Paint r83 = this.selectedBarPaint;
    L68:
        r26.drawRoundRect(r52, r63, r76, r83);
        r142 = r142 + 1;
        r8 = r19;
        r6 = 0.0f;
        goto L32
    L67:
        r83 = this.barPaint;
        goto L68
    L61:
        if (this.overlayCountVisible != 3) goto L64;
        this.barPaint.setAlpha((int) (AndroidUtilities.lerp(r62, 255, CubicBezierInterpolator.EASE_BOTH.getInterpolation(this.alphas[r142])) * this.alpha));
        goto L64
    L42:
        if (this.profileGalleryView.isCurrentItemVideo() == false) goto L56;
        r82 = this.profileGalleryView.getCurrentItemProgress();
        this.currentProgress = r82;
        if (r82 > r24) goto L48;
        if (this.profileGalleryView.isLoadingCurrentVideo() == false) goto L48;
    L49:
        float r53 = this.currentLoadingAnimationProgress;
        int r64 = this.currentLoadingAnimationDirection;
        float r54 = r53 + ((r64 * r9) / 500.0f);
        this.currentLoadingAnimationProgress = r54;
        if (r54 <= 1.0f) goto L53;
        this.currentLoadingAnimationProgress = 1.0f;
        this.currentLoadingAnimationDirection = r64 * (-1);
    L55:
        this.rect.set(r74, r4, r74 + r2, AndroidUtilities.dp(2.0f) + r4);
        this.barPaint.setAlpha((int) (((this.currentLoadingAnimationProgress * 48.0f) + 85.0f) * this.alpha));
        r26.drawRoundRect(this.rect, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), this.barPaint);
    L38:
        r62 = 80;
        r17 = true;
        goto L58
    L53:
        if (r54 > r24) goto L55;
        this.currentLoadingAnimationProgress = r24;
        this.currentLoadingAnimationDirection = r64 * (-1);
    L48:
        if (this.currentLoadingAnimationProgress <= r24) goto L55;
    L56:
        this.currentProgress = 1.0f;
        goto L57
    L36:
        if (Math.abs(this.previousSelectedProgress - 1.0f) <= 1.0E-4f) goto L39;
        r82 = this.previousSelectedProgress;
        r26.save();
        float r12 = r74;
        float r55 = r4;
        float r15 = r74 + r2;
        r26.clipRect((r2 * r82) + r12, r55, r15, r4 + AndroidUtilities.dp(2.0f));
        this.rect.set(r12, r55, r15, AndroidUtilities.dp(2.0f) + r4);
        this.barPaint.setAlpha((int) (this.alpha * 85.0f));
        r26.drawRoundRect(this.rect, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), this.barPaint);
        r26.restore();
        goto L38
    L69:
        float r192 = r8;
        float r20 = 180.0f;
        int r22 = this.overlayCountVisible;
        if (r22 != 2) goto L79;
        float r23 = this.alpha;
        if (r23 >= 1.0f) goto L77;
        float r25 = r23 + (r9 / 180.0f);
        this.alpha = r25;
        if (r25 <= 1.0f) goto L76;
        this.alpha = 1.0f;
    L76:
        int r27 = 20;
        r17 = true;
    L99:
        if (r32 <= r27) goto L101;
    L102:
        float r28 = this.textPaint.measureText(getCurrentTitle());
        this.indicatorRect.right = getMeasuredWidth() - AndroidUtilities.dp(r192);
        RectF r42 = this.indicatorRect;
        r42.left = r42.right - (r28 + AndroidUtilities.dpf2(16.0f));
        this.indicatorRect.top = AndroidUtilities.dp(r192);
        RectF r29 = this.indicatorRect;
        r29.bottom = r29.top + AndroidUtilities.dp(26.0f);
        float r210 = AndroidUtilities.dpf2(12.0f);
        r26.save();
        if (r32 <= 20) goto L105;
        boolean r33 = true;
    L107:
        if (r33 == false) goto L111;
        float r56 = this.progressToCounter;
        if (r56 == 1.0f) goto L111;
        this.progressToCounter = r56 + (r9 / 150.0f);
    L115:
        float r34 = this.progressToCounter;
        if (r34 < 1.0f) goto L119;
        this.progressToCounter = 1.0f;
    L122:
        float r35 = this.progressToCounter;
        r26.scale(r35, r35, this.indicatorRect.centerX(), this.indicatorRect.centerY());
        r26.drawRoundRect(this.indicatorRect, r210, r210, this.backgroundPaint);
        r26.drawText(getCurrentTitle(), this.indicatorRect.centerX(), this.indicatorRect.top + AndroidUtilities.dpf2(18.5f), this.textPaint);
        r26.restore();
    L123:
        int r211 = 0;
    L124:
        if (r211 >= 2) goto L141;
        boolean r1 = this.pressedOverlayVisible[r211];
        float[] r36 = this.pressedOverlayAlpha;
        if (r1 == false) goto L134;
        float r13 = r36[r211];
        if (r13 >= 1.0f) goto L140;
        float r16 = r13 + (r9 / r20);
        r36[r211] = r16;
        if (r16 <= 1.0f) goto L132;
        r36[r211] = 1.0f;
    L132:
        r17 = true;
    L140:
        r211 = r211 + 1;
        goto L124
    L134:
        float r18 = r36[r211];
        if (r18 <= 0.0f) goto L140;
        float r110 = r18 - (r9 / r20);
        r36[r211] = r110;
        if (r110 >= 0.0f) goto L139;
        r36[r211] = 0.0f;
    L139:
        r17 = true;
        goto L140
    L141:
        if (r17 == false) goto L158;
        postInvalidateOnAnimation();
        return;
    L158:
        return;
    L119:
        if (r34 > 0.0f) goto L121;
        this.progressToCounter = 0.0f;
        goto L122
    L121:
        invalidate();
    L111:
        if (r33 == true) goto L115;
        float r37 = this.progressToCounter;
        if (r37 == 0.0f) goto L115;
        this.progressToCounter = r37 - (r9 / 150.0f);
        goto L115
    L105:
        r33 = false;
        goto L107
    L101:
        if (this.progressToCounter == 0.0f) goto L123;
    L77:
        this.overlayCountVisible = 3;
    L97:
        r27 = 20;
        goto L99
    L79:
        if (r22 != 3) goto L97;
        int r212 = 0;
    L81:
        float[] r43 = this.alphas;
        if (r212 >= r43.length) goto L97;
        if (r212 == this.selectedPosition) goto L94;
        float r57 = r43[r212];
        if (r57 <= 0.0f) goto L94;
        float r58 = r57 - (r9 / 500.0f);
        r43[r212] = r58;
        if (r58 > 0.0f) goto L92;
        r43[r212] = 0.0f;
        if (r212 != this.previousSelectedPotision) goto L92;
        this.previousSelectedPotision = -1;
    L92:
        r17 = true;
    L96:
        r212 = r212 + 1;
    L94:
        if (r212 != this.previousSelectedPotision) goto L96;
        this.previousSelectedPotision = -1;
        goto L96
    L25:
        if (r14 != 1) goto L28;
        this.alpha = 0.0f;
        this.overlayCountVisible = 2;
    L98:
        r192 = 8.0f;
        r20 = 180.0f;
        r27 = 20;
        r17 = false;
        goto L99
    L17:
        if (r9 <= 20) goto L19;
    L12:
        if (r7.length == r32) goto L14;
        goto L13
    }

    @Override // android.view.View
    public void onMeasure(int r7, int r8) {
        super.onMeasure(r7, r8);
        this.path.reset();
        this.rectF.set(0.0f, 0.0f, getMeasuredHeight(), getMeasuredWidth());
        this.path.addRoundRect(this.rectF, new float[]{AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CCW);
    }

    @Override // org.telegram.ui.Components.ProfileGalleryView.Callback
    public void onPhotosLoaded() {
    }

    @Override // org.telegram.ui.Components.ProfileGalleryView.Callback
    public void onRelease() {
        Arrays.fill(this.pressedOverlayVisible, false);
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public void onSizeChanged(int r6, int r7, int r8, int r9) {
        int r82 = ActionBar.getCurrentActionBarHeight();
        this.topOverlayRect.set(0, 0, r6, (int) (r82 * 0.5f));
        this.bottomOverlayRect.set(0, (int) (r7 - (AndroidUtilities.dp(72.0f) * 0.5f)), r6, r7);
        this.topOverlayGradient.setBounds(0, this.topOverlayRect.bottom, r6, r82 + AndroidUtilities.dp(16.0f));
        this.bottomOverlayGradient.setBounds(0, (r7 - AndroidUtilities.dp(72.0f)) - AndroidUtilities.dp(24.0f), r6, this.bottomOverlayRect.top);
        int r92 = r6 / 5;
        this.pressedOverlayGradient[0].setBounds(0, 0, r92, r7);
        this.pressedOverlayGradient[1].setBounds(r6 - r92, 0, r6, r7);
    }

    @Override // org.telegram.ui.Components.ProfileGalleryView.Callback
    public void onVideoSet() {
        invalidate();
    }

    public void saveCurrentPageProgress() {
        this.previousSelectedProgress = this.currentProgress;
        this.previousSelectedPotision = this.selectedPosition;
        this.currentLoadingAnimationProgress = 0.0f;
        this.currentLoadingAnimationDirection = 1;
    }

    public void setAlphaValue(float r4, boolean r5) {
        int r0 = (int) (255.0f * r4);
        this.topOverlayGradient.setAlpha(r0);
        this.bottomOverlayGradient.setAlpha(r0);
        this.backgroundPaint.setAlpha((int) (66.0f * r4));
        this.barPaint.setAlpha((int) (85.0f * r4));
        this.selectedBarPaint.setAlpha(r0);
        this.alpha = r4;
        if (r5 == true) goto L5;
        this.currentAnimationValue = r4;
    L5:
        invalidate();
    }

    public void setProfileGalleryView(ProfileGalleryView r1) {
        this.profileGalleryView = r1;
    }
}
