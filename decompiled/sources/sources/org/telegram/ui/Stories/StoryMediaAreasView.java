package org.telegram.ui.Stories;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineHeightSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.LocationMarker;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Shaker$$ExternalSyntheticLambda0;
import org.telegram.ui.EmojiAnimationsOverlay;
import org.telegram.ui.LocationActivity;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes7.dex */
public abstract class StoryMediaAreasView extends FrameLayout implements View.OnClickListener {
    private final Path clipPath;
    private final Paint cutPaint;
    private HintView2 hintView;
    private final FrameLayout hintsContainer;
    private ArrayList<TL_stories.MediaArea> lastMediaAreas;
    private AreaView lastSelectedArea;
    private boolean malicious;
    Matrix matrix;
    private Bitmap parentBitmap;
    public final AnimatedFloat parentHighlightAlpha;
    public final AnimatedFloat parentHighlightScaleAlpha;
    private View parentView;
    float[] point;
    private final float[] radii;
    private final Rect rect;
    private final RectF rectF;
    private Theme.ResourcesProvider resourcesProvider;
    private AreaView selectedArea;
    private boolean shined;

    /* renamed from: org.telegram.ui.Stories.StoryMediaAreasView$1, reason: invalid class name */
    public class AnonymousClass1 extends LocationActivity {
        final /* synthetic */ StoryMediaAreasView this$0;

        public AnonymousClass1(StoryMediaAreasView r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.LocationActivity
        public boolean disablePermissionCheck() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryMediaAreasView$2, reason: invalid class name */
    public class AnonymousClass2 implements LineHeightSpan {
        final /* synthetic */ StoryMediaAreasView this$0;

        public AnonymousClass2(StoryMediaAreasView r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(CharSequence r1, int r2, int r3, int r4, int r5, Paint.FontMetricsInt r6) {
            r6.ascent -= AndroidUtilities.dp(2.0f);
            r6.top -= AndroidUtilities.dp(2.0f);
        }
    }

    public static class AreaView extends View {
        public final ButtonBounce bounce;
        private boolean bounceOnTap;
        private final Path clipPath;
        private LinearGradient gradient;
        private final Matrix gradientMatrix;
        private final Paint gradientPaint;
        public final AnimatedFloat highlightAlpha;
        public final TL_stories.MediaArea mediaArea;
        private boolean ripple;
        private final Drawable rippleDrawable;
        private boolean scaleOnTap;
        private final Runnable shineRunnable;
        private boolean shining;
        private long startTime;
        private LinearGradient strokeGradient;
        private final Paint strokeGradientPaint;
        private boolean supportsBounds;
        private boolean supportsShining;

        public static /* synthetic */ void $r8$lambda$QXrOKF0Fe06RuXHp2SNwypY5mKw(AreaView r0) {
            r0.shineInternal();
        }

        /* renamed from: -$$Nest$fgetbounceOnTap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19762$$Nest$fgetbounceOnTap(AreaView r0) {
            return r0.bounceOnTap;
        }

        /* renamed from: -$$Nest$fgetscaleOnTap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19763$$Nest$fgetscaleOnTap(AreaView r0) {
            return r0.scaleOnTap;
        }

        /* renamed from: -$$Nest$fgetsupportsBounds, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19764$$Nest$fgetsupportsBounds(AreaView r0) {
            return r0.supportsBounds;
        }

        public AreaView(Context r10, View r11, TL_stories.MediaArea r12) {
            super(r10);
            boolean r0 = true;
            this.gradientPaint = new Paint(1);
            Paint r102 = new Paint(1);
            this.strokeGradientPaint = r102;
            this.gradientMatrix = new Matrix();
            Drawable r1 = Theme.createSelectorDrawable(1174405119, 2);
            this.rippleDrawable = r1;
            this.bounce = new ButtonBounce(this);
            this.supportsBounds = false;
            this.supportsShining = false;
            this.shining = false;
            this.clipPath = new Path();
            this.shineRunnable = new StoryMediaAreasView$AreaView$$ExternalSyntheticLambda0(this);
            this.mediaArea = r12;
            boolean r3 = r12 instanceof TL_stories.TL_mediaAreaGeoPoint;
            if (r3 == false) goto L5;
        L10:
            boolean r4 = true;
        L11:
            this.supportsBounds = r4;
            if (r3 == false) goto L14;
        L17:
            boolean r42 = true;
        L18:
            this.supportsShining = r42;
            if (r3 == false) goto L21;
        L26:
            this.scaleOnTap = r0;
            this.ripple = r0;
            this.bounceOnTap = r0;
            this.highlightAlpha = new AnimatedFloat(r11, 0, 120, new LinearInterpolator());
            r102.setStyle(Paint.Style.STROKE);
            r1.setCallback(this);
            return;
        L21:
            if ((r12 instanceof TL_stories.TL_mediaAreaVenue) == true) goto L26;
            if ((r12.coordinates.flags & 1) != 0) goto L26;
            r0 = false;
            goto L26
        L14:
            if ((r12 instanceof TL_stories.TL_mediaAreaVenue) == true) goto L17;
            r42 = false;
            goto L18
        L5:
            if ((r12 instanceof TL_stories.TL_mediaAreaVenue) == true) goto L10;
            if ((r12 instanceof TL_stories.TL_mediaAreaUrl) == true) goto L10;
            r4 = false;
            goto L11
        }

        private void shineInternal() {
            if (this.supportsShining == true) goto L5;
            return;
        L5:
            this.shining = true;
            this.startTime = System.currentTimeMillis();
            Shader.TileMode r17 = Shader.TileMode.CLAMP;
            this.gradient = new LinearGradient(0.0f, 0.0f, 40.0f, 0.0f, new int[]{16777215, 771751935, 771751935, 16777215}, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, r17);
            this.strokeGradient = new LinearGradient(0.0f, 0.0f, 40.0f, 0.0f, new int[]{16777215, 553648127, 553648127, 16777215}, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, r17);
            invalidate();
        }

        public void customDraw(Canvas r1) {
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L9;
            if ((getParent() instanceof View) == false) goto L7;
            ButtonBounce r0 = this.bounce;
            View r2 = (View) getParent();
            Objects.requireNonNull(r2);
            r0.setAdditionalInvalidate(new Shaker$$ExternalSyntheticLambda0(r2));
        L7:
            this.bounce.setPressed(true);
            this.rippleDrawable.setHotspot(r5.getX(), r5.getY());
            this.rippleDrawable.setState(new int[]{R.attr.state_pressed, R.attr.state_enabled});
        L13:
            super.dispatchTouchEvent(r5);
            return true;
        L9:
            if (r5.getAction() != 1) goto L11;
        L12:
            this.bounce.setPressed(false);
            this.rippleDrawable.setState(new int[0]);
            goto L13
        L11:
            if (r5.getAction() != 3) goto L13;
            goto L12
        }

        public void drawAbove(Canvas r6) {
            if (this.ripple == true) goto L5;
            return;
        L5:
            float r0 = getInnerRadius();
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(0.0f, 0.0f, getWidth(), getHeight());
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r1, r0, r0, Path.Direction.CW);
            r6.save();
            r6.clipPath(this.clipPath);
            this.rippleDrawable.setBounds(0, 0, getWidth(), getHeight());
            this.rippleDrawable.draw(r6);
            r6.restore();
        }

        public float getInnerRadius() {
            if ((getParent() instanceof View) == false) goto L15;
            TL_stories.MediaArea r0 = this.mediaArea;
            if (r0 == null) goto L15;
            TL_stories.MediaAreaCoordinates r02 = r0.coordinates;
            if (r02 == null) goto L15;
            if ((r02.flags & 1) == 0) goto L13;
            return (float) (((r02.radius / 100.0d) * getWidth()) / getScaleX());
        L13:
            return getMeasuredHeight() * 0.2f;
        L15:
            return getMeasuredHeight() * 0.2f;
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            float r0 = getInnerRadius();
            drawAbove(r7);
            if (this.supportsShining == true) goto L5;
            return;
        L5:
            if (this.shining == true) goto L7;
            return;
        L7:
            if (this.gradient == null) goto L16;
            float r1 = getMeasuredWidth() * 0.7f;
            float r2 = (System.currentTimeMillis() - this.startTime) / 600.0f;
            float r3 = ((getMeasuredWidth() + r1) * r2) - r1;
            if (r2 < 1.0f) goto L12;
            this.shining = false;
            return;
        L12:
            this.gradientMatrix.reset();
            this.gradientMatrix.postScale(r1 / 40.0f, 1.0f);
            this.gradientMatrix.postTranslate(r3, 0.0f);
            this.gradient.setLocalMatrix(this.gradientMatrix);
            this.gradientPaint.setShader(this.gradient);
            RectF r12 = AndroidUtilities.rectTmp;
            r12.set(0.0f, 0.0f, getWidth(), getHeight());
            r7.drawRoundRect(r12, r0, r0, this.gradientPaint);
            this.strokeGradient.setLocalMatrix(this.gradientMatrix);
            this.strokeGradientPaint.setShader(this.strokeGradient);
            float r22 = AndroidUtilities.dpf2(1.5f);
            this.strokeGradientPaint.setStrokeWidth(r22);
            float r23 = r22 / 2.0f;
            r12.inset(r23, r23);
            float r02 = r0 - r23;
            r7.drawRoundRect(r12, r02, r02, this.strokeGradientPaint);
            invalidate();
            return;
        }

        public void shine() {
            if (this.supportsShining == true) goto L5;
            return;
        L5:
            AndroidUtilities.cancelRunOnUIThread(this.shineRunnable);
            AndroidUtilities.runOnUIThread(this.shineRunnable, 400);
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.rippleDrawable) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static class FitViewWidget extends FrameLayout {
        public final View child;
        public final TL_stories.MediaArea mediaArea;

        public FitViewWidget(Context r1, View r2, TL_stories.MediaArea r3) {
            super(r1);
            this.mediaArea = r3;
            this.child = r2;
            addView(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            this.child.measure(r8, r9);
            int r0 = (this.child.getMeasuredWidth() - this.child.getPaddingLeft()) - this.child.getPaddingRight();
            int r1 = (this.child.getMeasuredHeight() - this.child.getPaddingTop()) - this.child.getPaddingBottom();
            float r02 = r0;
            float r5 = r02 / 2.0f;
            this.child.setPivotX(r2.getPaddingLeft() + r5);
            float r12 = r1;
            float r6 = r12 / 2.0f;
            this.child.setPivotY(r2.getPaddingTop() + r6);
            int r82 = View.MeasureSpec.getSize(r8);
            int r92 = View.MeasureSpec.getSize(r9);
            setMeasuredDimension(r82, r92);
            float r83 = r82;
            float r93 = r92;
            float r03 = Math.min(r83 / r02, r93 / r12);
            this.child.setTranslationX((r83 / 2.0f) - (r5 + r1.getPaddingLeft()));
            this.child.setTranslationY((r93 / 2.0f) - (r6 + r8.getPaddingTop()));
            this.child.setScaleX(r03);
            this.child.setScaleY(r03);
        }
    }

    /* renamed from: $r8$lambda$ITr887v-AcWY5EVVCm4Bu_sONPY, reason: not valid java name */
    public static /* synthetic */ void m19760$r8$lambda$ITr887vAcWY5EVVCm4Bu_sONPY(StoryMediaAreasView r0) {
        r0.lambda$onClick$0();
    }

    public static /* synthetic */ void $r8$lambda$lSjkKXQgJgJ4hJ_JfkX54CJR3sY(StoryMediaAreasView r0, HintView2 r1) {
        r0.lambda$onClick$1(r1);
    }

    /* renamed from: $r8$lambda$ohvTbY-6YdLpI30TRk5rYhvHO-8, reason: not valid java name */
    public static /* synthetic */ void m19761$r8$lambda$ohvTbY6YdLpI30TRk5rYhvHO8(StoryMediaAreasView r0, View r1) {
        r0.lambda$onClick$2(r1);
    }

    public StoryMediaAreasView(Context r11, View r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.lastSelectedArea = null;
        this.selectedArea = null;
        this.hintView = null;
        this.matrix = new Matrix();
        this.point = new float[2];
        this.rect = new Rect();
        this.rectF = new RectF();
        Paint r2 = new Paint(1);
        this.cutPaint = r2;
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r2.setColor(-1);
        this.clipPath = new Path();
        this.radii = new float[8];
        this.shined = false;
        this.parentView = r12;
        this.resourcesProvider = r13;
        this.parentHighlightAlpha = new AnimatedFloat(r12, 0, 120, new LinearInterpolator());
        this.parentHighlightScaleAlpha = new AnimatedFloat(r12, 0, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
        setClipChildren(false);
        FrameLayout r22 = new FrameLayout(r11);
        this.hintsContainer = r22;
        addView(r22);
        setLayerType(2, null);
    }

    private void drawHighlight(Canvas r18) {
        AnimatedFloat r1 = this.parentHighlightAlpha;
        AreaView r2 = this.selectedArea;
        if (r2 != null) goto L5;
    L9:
        boolean r22 = false;
    L10:
        float r10 = r1.set(r22);
        AreaView r12 = this.selectedArea;
        if (r12 != null) goto L13;
    L15:
        boolean r11 = false;
    L16:
        float r122 = this.parentHighlightScaleAlpha.set(r11);
        if (r10 <= 0.0f) goto L35;
        Canvas r13 = r18;
        r13.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
        r13.drawColor(Theme.multAlpha(402653184, r10));
        int r23 = 0;
    L20:
        if (r23 >= getChildCount()) goto L34;
        View r3 = getChildAt(r23);
        if (r3 == this.hintsContainer) goto L33;
        AnimatedFloat r4 = ((AreaView) r3).highlightAlpha;
        AreaView r5 = this.selectedArea;
        if (r3 == r5) goto L26;
    L28:
        boolean r52 = false;
    L29:
        float r42 = r4.set(r52);
        if (r42 <= 0.0f) goto L33;
        r13.save();
        this.rectF.set(r3.getX(), r3.getY(), r3.getX() + r3.getMeasuredWidth(), r3.getY() + r3.getMeasuredHeight());
        r13.rotate(r3.getRotation(), this.rectF.centerX(), this.rectF.centerY());
        this.cutPaint.setAlpha((int) (r42 * 255.0f));
        RectF r32 = this.rectF;
        r13.drawRoundRect(r32, r32.height() * 0.2f, this.rectF.height() * 0.2f, this.cutPaint);
        r13.restore();
        goto L33
    L26:
        if (AreaView.m19764$$Nest$fgetsupportsBounds(r5) == false) goto L28;
        r52 = true;
    L33:
        r23 = r23 + 1;
        goto L20
    L34:
        r13.restore();
    L37:
        if (r11 == true) goto L41;
        if (r122 > 0.0f) goto L41;
    L56:
        Bitmap r14 = this.parentBitmap;
        if (r14 == null) goto L59;
        r14.recycle();
        this.parentBitmap = null;
    L59:
        invalidate();
        return;
    L41:
        if (this.lastSelectedArea == null) goto L56;
        if (this.parentBitmap != null) goto L46;
        this.parentBitmap = getPlayingBitmap();
    L46:
        if (this.parentBitmap == null) goto L59;
        r13.drawColor(Theme.multAlpha(805306368, r122));
        r13.save();
        this.clipPath.rewind();
        this.rectF.set(this.lastSelectedArea.getX(), this.lastSelectedArea.getY(), this.lastSelectedArea.getX() + this.lastSelectedArea.getMeasuredWidth(), this.lastSelectedArea.getY() + this.lastSelectedArea.getMeasuredHeight());
        if (AreaView.m19762$$Nest$fgetbounceOnTap(this.lastSelectedArea) == false) goto L50;
        float r33 = this.lastSelectedArea.bounce.getScale(0.05f);
    L51:
        float r34 = AndroidUtilities.lerp(1.0f, r33 * 1.05f, r122);
        r13.scale(r34, r34, this.rectF.centerX(), this.rectF.centerY());
        r13.rotate(this.lastSelectedArea.getRotation(), this.rectF.centerX(), this.rectF.centerY());
        TL_stories.MediaAreaCoordinates r53 = this.lastSelectedArea.mediaArea.coordinates;
        if ((r53.flags & 1) == 0) goto L54;
        float r43 = (float) ((r53.radius / 100.0d) * r4.getMeasuredWidth());
    L55:
        this.clipPath.addRoundRect(this.rectF, r43, r43, Path.Direction.CW);
        r13.clipPath(this.clipPath);
        RectF r44 = AndroidUtilities.rectTmp;
        r44.set(0.0f, 0.0f, getWidth(), getHeight());
        this.rect.set(0, 0, this.parentBitmap.getWidth(), this.parentBitmap.getHeight());
        r13.rotate(-this.lastSelectedArea.getRotation(), this.rectF.centerX(), this.rectF.centerY());
        r13.drawBitmap(this.parentBitmap, this.rect, r44, null);
        r13.restore();
        r13.save();
        r13.translate(this.lastSelectedArea.getX(), this.lastSelectedArea.getY());
        r13.rotate(this.lastSelectedArea.getRotation(), this.lastSelectedArea.getPivotX(), this.lastSelectedArea.getPivotY());
        r13.scale(this.lastSelectedArea.getScaleX() * r34, this.lastSelectedArea.getScaleY() * r34, this.lastSelectedArea.getPivotX(), this.lastSelectedArea.getPivotY());
        this.lastSelectedArea.drawAbove(r13);
        r13.restore();
        goto L59
    L54:
        r43 = r4.getMeasuredHeight() * 0.2f;
        goto L55
    L50:
        r33 = 1.0f;
        goto L51
    L35:
        r13 = r18;
        goto L37
    L13:
        if (AreaView.m19763$$Nest$fgetscaleOnTap(r12) == false) goto L15;
        r11 = true;
        goto L16
    L5:
        if (AreaView.m19764$$Nest$fgetsupportsBounds(r2) == false) goto L9;
        if (AreaView.m19763$$Nest$fgetscaleOnTap(this.selectedArea) == true) goto L9;
        r22 = true;
        goto L10
    }

    public static ArrayList<TL_stories.MediaArea> getMediaAreasFor(StoryEntry r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (r3.mediaEntities == null) goto L19;
        ArrayList<TL_stories.MediaArea> r0 = new ArrayList();
        int r1 = 0;
    L8:
        if (r1 >= r3.mediaEntities.size()) goto L13;
        if ((r3.mediaEntities.get(r1).mediaArea instanceof TL_stories.TL_mediaAreaSuggestedReaction) == false) goto L12;
        r0.add(r3.mediaEntities.get(r1).mediaArea);
    L12:
        r1 = r1 + 1;
        goto L8
    L13:
        return r0;
    L19:
        return null;
    }

    private /* synthetic */ void lambda$onClick$0() {
        HintView2 r0 = this.hintView;
        if (r0 == null) goto L5;
        r0.hide();
        this.hintView = null;
    L5:
        onHintVisible(false);
    }

    private /* synthetic */ void lambda$onClick$1(HintView2 r2) {
        this.hintsContainer.removeView(r2);
        if (r2 != this.hintView) goto L6;
        this.selectedArea = null;
        invalidate();
        onHintVisible(false);
        return;
    }

    private /* synthetic */ void lambda$onClick$2(View r1) {
        onClick(this.selectedArea);
    }

    private void onClickAway() {
        HintView2 r0 = this.hintView;
        if (r0 == null) goto L5;
        r0.hide();
        this.hintView = null;
    L5:
        this.selectedArea = null;
        invalidate();
        onHintVisible(false);
        if (this.malicious == false) goto L14;
        int r1 = 0;
    L9:
        if (r1 >= getChildCount()) goto L18;
        View r2 = getChildAt(r1);
        if (r2 == this.hintsContainer) goto L13;
        r2.setClickable(false);
    L13:
        r1 = r1 + 1;
        goto L9
    L18:
        return;
    }

    private static boolean rotatedRectContainsPoint(float r6, float r7, float r8, float r9, float r10, float r11, float r12) {
        float r112 = r11 - r6;
        double r62 = Math.toRadians(-r10);
        double r102 = r112;
        double r2 = r12 - r7;
        float r122 = (float) ((Math.cos(r62) * r102) - (Math.sin(r62) * r2));
        float r63 = (float) ((r102 * Math.sin(r62)) + (r2 * Math.cos(r62)));
        if (r122 >= ((-r8) / 2.0f)) goto L5;
        return false;
    L5:
        if (r122 <= (r8 / 2.0f)) goto L7;
        return false;
    L7:
        if (r63 >= ((-r9) / 2.0f)) goto L9;
        return false;
    L9:
        if (r63 > (r9 / 2.0f)) goto L16;
        return true;
    L16:
        return false;
    }

    public void closeHint() {
        HintView2 r0 = this.hintView;
        if (r0 == null) goto L5;
        r0.hide();
        this.hintView = null;
    L5:
        this.selectedArea = null;
        invalidate();
        onHintVisible(false);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r3, View r4, long r5) {
        if (r4 != this.hintsContainer) goto L6;
        drawHighlight(r3);
    L9:
        return super.drawChild(r3, r4, r5);
    L6:
        if ((r4 instanceof AreaView) == false) goto L9;
        r3.save();
        r3.translate(r4.getLeft(), r4.getTop());
        r3.concat(r4.getMatrix());
        ((AreaView) r4).customDraw(r3);
        r3.restore();
        goto L9
    }

    public Bitmap getPlayingBitmap() {
        return null;
    }

    public boolean hasAreaAboveAt(float r12, float r13) {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L13;
        View r2 = getChildAt(r1);
        if ((r2 instanceof StoryReactionWidgetView) == false) goto L11;
        float r9 = r12;
        float r10 = r13;
        if (rotatedRectContainsPoint(r2.getTranslationX(), r2.getTranslationY(), r2.getMeasuredWidth(), r2.getMeasuredHeight(), r2.getRotation(), r9, r10) == false) goto L12;
        return true;
    L12:
        r1 = r1 + 1;
        r12 = r9;
        r13 = r10;
        goto L4
    L11:
        r9 = r12;
        r10 = r13;
        goto L12
    L13:
        return false;
    }

    public boolean hasClickableViews(float r7, float r8) {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L21;
        View r2 = getChildAt(r1);
        if (r2 == this.hintsContainer) goto L20;
        if ((r2 instanceof StoryReactionWidgetView) == false) goto L20;
        r2.getMatrix().invert(this.matrix);
        float[] r3 = this.point;
        r3[0] = r7;
        r3[1] = r8;
        this.matrix.mapPoints(r3);
        if (this.point[0] < r2.getLeft()) goto L20;
        if (this.point[0] > r2.getRight()) goto L20;
        if (this.point[1] < r2.getTop()) goto L20;
        if (this.point[1] > r2.getBottom()) goto L20;
        return true;
    L20:
        r1 = r1 + 1;
        goto L4
    L21:
        return false;
    }

    public boolean hasSelected() {
        if (this.selectedArea == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasSelectedForScale() {
        AreaView r0 = this.selectedArea;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (AreaView.m19763$$Nest$fgetscaleOnTap(r0) == false) goto L7;
        return true;
    L7:
        if (AreaView.m19764$$Nest$fgetsupportsBounds(this.selectedArea) == false) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View r19) {
        if ((r19 instanceof AreaView) == true) goto L6;
        return;
    L6:
        if ((r19 instanceof StoryReactionWidgetView) == false) goto L9;
        showEffect((StoryReactionWidgetView) r19);
        return;
    L9:
        AreaView r2 = this.selectedArea;
        boolean r6 = false;
        if (r2 != r19) goto L33;
        AndroidUtilities.runOnUIThread(new StoryMediaAreasView$$ExternalSyntheticLambda0(this), 200);
        TL_stories.MediaArea r1 = this.selectedArea.mediaArea;
        if ((r1 instanceof TL_stories.TL_mediaAreaChannelPost) == false) goto L16;
        Bundle r12 = new Bundle();
        r12.putLong("chat_id", ((TL_stories.TL_mediaAreaChannelPost) this.selectedArea.mediaArea).channel_id);
        r12.putInt("message_id", ((TL_stories.TL_mediaAreaChannelPost) this.selectedArea.mediaArea).msg_id);
        presentFragment(new ChatActivity(r12));
        this.selectedArea = null;
        invalidate();
        return;
    L16:
        if ((r1 instanceof TL_stories.TL_mediaAreaUrl) == false) goto L20;
        Browser.openUrl(getContext(), ((TL_stories.TL_mediaAreaUrl) this.selectedArea.mediaArea).url);
        this.selectedArea = null;
        invalidate();
        return;
    L20:
        if ((r1 instanceof TL_stories.TL_mediaAreaStarGift) == false) goto L23;
        String r13 = ((TL_stories.TL_mediaAreaStarGift) r1).slug;
        Browser.openUrl(getContext(), "https://" + MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix + "/nft/" + r13);
        this.selectedArea = null;
        invalidate();
        return;
    L23:
        AnonymousClass1 r14 = new AnonymousClass1(this, 3);
        r14.fromStories = true;
        r14.searchStories(this.selectedArea.mediaArea);
        r14.setResourceProvider(this.resourcesProvider);
        TLRPC.TL_message r22 = new TLRPC.TL_message();
        TL_stories.MediaArea r3 = this.selectedArea.mediaArea;
        if ((r3 instanceof TL_stories.TL_mediaAreaVenue) == false) goto L27;
        TL_stories.TL_mediaAreaVenue r32 = (TL_stories.TL_mediaAreaVenue) r3;
        TLRPC.TL_messageMediaVenue r4 = new TLRPC.TL_messageMediaVenue();
        r4.venue_id = r32.venue_id;
        r4.venue_type = r32.venue_type;
        r4.title = r32.title;
        r4.address = r32.address;
        r4.provider = r32.provider;
        r4.geo = r32.geo;
        r22.media = r4;
    L29:
        r14.setSharingAllowed(false);
        r14.setMessageObject(new MessageObject(UserConfig.selectedAccount, r22, false, false));
        presentFragment(r14);
        this.selectedArea = null;
        invalidate();
        return;
    L27:
        if ((r3 instanceof TL_stories.TL_mediaAreaGeoPoint) == false) goto L31;
        r14.setInitialMaxZoom(true);
        TL_stories.TL_mediaAreaGeoPoint r33 = (TL_stories.TL_mediaAreaGeoPoint) this.selectedArea.mediaArea;
        TLRPC.TL_messageMediaGeo r42 = new TLRPC.TL_messageMediaGeo();
        r42.geo = r33.geo;
        r22.media = r42;
        goto L29
    L31:
        this.selectedArea = null;
        invalidate();
        return;
    L33:
        if (r2 != null) goto L35;
    L38:
        AreaView r15 = (AreaView) r19;
        this.lastSelectedArea = r15;
        this.selectedArea = r15;
        invalidate();
        HintView2 r16 = this.hintView;
        if (r16 == null) goto L41;
        r16.hide();
        this.hintView = null;
    L41:
        HintView2 r17 = new HintView2(getContext()).setSelectorColor(687865855).setJointPx(0.0f, this.selectedArea.getTranslationX() - AndroidUtilities.dp(8.0f)).setDuration(5000);
        this.hintView = r17;
        SpannableStringBuilder r23 = new SpannableStringBuilder();
        TL_stories.MediaArea r8 = this.selectedArea.mediaArea;
        if ((r8 instanceof TL_stories.TL_mediaAreaChannelPost) == false) goto L45;
        r23.append(LocaleController.getString(org.telegram.messenger.R.string.StoryViewMessage));
    L51:
        boolean r82 = false;
    L52:
        SpannableString r9 = new SpannableString(">");
        ColoredImageSpan r132 = new ColoredImageSpan(org.telegram.messenger.R.drawable.photos_arrow);
        if (r82 == false) goto L55;
        float r162 = 1.0f;
    L56:
        float r34 = AndroidUtilities.dp(r162);
        if (r82 == false) goto L60;
        float r163 = 0.0f;
    L61:
        r132.translate(r34, AndroidUtilities.dp(r163));
        r9.setSpan(r132, 0, r9.length(), 33);
        SpannableString r35 = new SpannableString("<");
        ColoredImageSpan r5 = new ColoredImageSpan(org.telegram.messenger.R.drawable.attach_arrow_right);
        if (r82 == false) goto L64;
        float r164 = -1.0f;
    L65:
        float r7 = AndroidUtilities.dp(r164);
        if (r82 == false) goto L69;
        float r172 = 0.0f;
    L70:
        r5.translate(r7, AndroidUtilities.dp(r172));
        r5.setScale(-1.0f, 1.0f);
        r35.setSpan(r5, 0, r35.length(), 33);
        if (AndroidUtilities.isRTL(r23) == false) goto L73;
        r9 = r35;
    L73:
        AndroidUtilities.replaceCharSequence(">", r23, r9);
        r17.setText(r23);
        r17.setOnHiddenListener(new StoryMediaAreasView$$ExternalSyntheticLambda1(this, r17));
        if (r82 == false) goto L76;
        int r24 = 100;
    L77:
        float r25 = r24;
        if ((this.selectedArea.getTranslationY() - AndroidUtilities.dp(r25)) >= AndroidUtilities.dp(100.0f)) goto L80;
        r6 = true;
    L80:
        if (r6 == false) goto L82;
        int r36 = 1;
    L83:
        r17.setDirection(r36);
        AreaView r37 = this.selectedArea;
        if ((r37.mediaArea instanceof TL_stories.TL_mediaAreaChannelPost) == true) goto L86;
    L93:
        AreaView r38 = this.selectedArea;
        if (r6 == false) goto L96;
        r17.setTranslationY(r38.getTranslationY() + (this.selectedArea.getMeasuredHeight() / 2.0f));
    L97:
        r17.setOnClickListener(new StoryMediaAreasView$$ExternalSyntheticLambda2(this));
        r17.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.hintsContainer.addView(r17, LayoutHelper.createFrame(-1, r25));
        r17.show();
        onHintVisible(true);
        return;
    L96:
        r17.setTranslationY((r38.getTranslationY() - (this.selectedArea.getMeasuredHeight() / 2.0f)) - AndroidUtilities.dp(r25));
        goto L97
    L86:
        if (r6 == false) goto L91;
        if ((r37.getTranslationY() + (this.selectedArea.getMeasuredHeight() / 2.0f)) <= (getMeasuredHeight() - AndroidUtilities.dp(120.0f))) goto L93;
    L92:
        r17.setTranslationY(this.selectedArea.getTranslationY() - (this.selectedArea.getMeasuredHeight() / 3.0f));
        goto L97
    L91:
        if (((r37.getTranslationY() - (this.selectedArea.getMeasuredHeight() / 2.0f)) - AndroidUtilities.dp(r25)) >= AndroidUtilities.dp(120.0f)) goto L93;
    L82:
        r36 = 3;
        goto L83
    L76:
        r24 = 50;
        goto L77
    L69:
        r172 = 1.0f;
        goto L70
    L64:
        r164 = -2.0f;
        goto L65
    L60:
        r163 = 1.0f;
        goto L61
    L55:
        r162 = 2.0f;
        goto L56
    L45:
        if ((r8 instanceof TL_stories.TL_mediaAreaStarGift) == false) goto L48;
        r23.append(LocaleController.getString(org.telegram.messenger.R.string.StoryViewGift));
        goto L51
    L48:
        if ((r8 instanceof TL_stories.TL_mediaAreaUrl) == false) goto L50;
        r17.setMultilineText(true);
        r23.append(LocaleController.getString(org.telegram.messenger.R.string.StoryOpenLink));
        r23.append("\n");
        TL_stories.TL_mediaAreaUrl r83 = (TL_stories.TL_mediaAreaUrl) this.selectedArea.mediaArea;
        int r92 = r23.length();
        r23.append(TextUtils.ellipsize(r83.url, this.hintView.getTextPaint(), AndroidUtilities.displaySize.x * 0.6f, TextUtils.TruncateAt.END));
        r23.setSpan(new RelativeSizeSpan(0.85f), r92, r23.length(), 33);
        r23.setSpan(new ForegroundColorSpan(Theme.multAlpha(-1, 0.6f)), r92, r23.length(), 33);
        r23.setSpan(new AnonymousClass2(this), r92, r23.length(), 33);
        r17.setInnerPadding(11.0f, 7.0f, 11.0f, 7.0f);
        r82 = true;
        goto L52
    L50:
        r23.append(LocaleController.getString(org.telegram.messenger.R.string.StoryViewLocation));
        goto L51
    L35:
        if (this.malicious == false) goto L38;
        onClickAway();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap r0 = this.parentBitmap;
        if (r0 == null) goto L6;
        r0.recycle();
        this.parentBitmap = null;
        return;
    }

    public abstract void onHintVisible(boolean r1);

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L15;
        View r1 = getChildAt(r0);
        if (r1 != this.hintsContainer) goto L9;
        r1.layout(0, 0, r13 - r11, r14 - r12);
    L14:
        r0 = r0 + 1;
        goto L4
    L9:
        if ((r1 instanceof AreaView) == false) goto L12;
        AreaView r15 = (AreaView) r1;
        int r2 = r15.getMeasuredWidth();
        int r5 = r15.getMeasuredHeight();
        r15.layout((-r2) / 2, (-r5) / 2, r2 / 2, r5 / 2);
        r15.setTranslationX((float) ((r15.mediaArea.coordinates.x / 100.0d) * getMeasuredWidth()));
        r15.setTranslationY((float) ((r15.mediaArea.coordinates.y / 100.0d) * getMeasuredHeight()));
        r15.setRotation((float) r15.mediaArea.coordinates.rotation);
        goto L14
    L12:
        if ((r1 instanceof FitViewWidget) == false) goto L14;
        FitViewWidget r16 = (FitViewWidget) r1;
        int r22 = r16.getMeasuredWidth();
        int r52 = r16.getMeasuredHeight();
        r16.layout((-r22) / 2, (-r52) / 2, r22 / 2, r52 / 2);
        r16.setTranslationX((float) ((r16.mediaArea.coordinates.x / 100.0d) * getMeasuredWidth()));
        r16.setTranslationY((float) ((r16.mediaArea.coordinates.y / 100.0d) * getMeasuredHeight()));
        r16.setRotation((float) r16.mediaArea.coordinates.rotation);
        goto L14
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r11, int r12) {
        int r112 = View.MeasureSpec.getSize(r11);
        int r122 = View.MeasureSpec.getSize(r12);
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L15;
        View r1 = getChildAt(r0);
        FrameLayout r2 = this.hintsContainer;
        if (r1 != r2) goto L9;
        r2.measure(View.MeasureSpec.makeMeasureSpec(r112, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30));
    L14:
        r0 = r0 + 1;
        goto L4
    L9:
        if ((r1 instanceof AreaView) == false) goto L12;
        AreaView r13 = (AreaView) getChildAt(r0);
        r13.measure(View.MeasureSpec.makeMeasureSpec((int) Math.ceil((r13.mediaArea.coordinates.w / 100.0d) * r112), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) Math.ceil((r13.mediaArea.coordinates.h / 100.0d) * r122), TLObject.FLAG_30));
        goto L14
    L12:
        if ((r1 instanceof FitViewWidget) == false) goto L14;
        FitViewWidget r14 = (FitViewWidget) getChildAt(r0);
        r14.measure(View.MeasureSpec.makeMeasureSpec((int) Math.ceil((r14.mediaArea.coordinates.w / 100.0d) * r112), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) Math.ceil((r14.mediaArea.coordinates.h / 100.0d) * r122), TLObject.FLAG_30));
        goto L14
    L15:
        setMeasuredDimension(r112, r122);
    }

    public void onStoryItemUpdated(TL_stories.StoryItem r4, boolean r5) {
        if (r4 == null) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= getChildCount()) goto L15;
        if ((getChildAt(r0) instanceof StoryReactionWidgetView) == false) goto L10;
        ((StoryReactionWidgetView) getChildAt(r0)).setViews(r4.views, r5);
    L10:
        r0 = r0 + 1;
        goto L6
    L15:
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (getChildCount() == 0) goto L14;
        HintView2 r0 = this.hintView;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.shown() == true) goto L10;
        return false;
    L10:
        if (r3.getAction() != 1) goto L12;
        onClickAway();
    L12:
        super.onTouchEvent(r3);
        return true;
    L14:
        return false;
    }

    public abstract void presentFragment(BaseFragment r1);

    public void set(TL_stories.StoryItem r10, ArrayList<TL_stories.MediaArea> r11, EmojiAnimationsOverlay r12) {
        ArrayList<TL_stories.MediaArea> r0 = this.lastMediaAreas;
        if (r11 != r0) goto L9;
        if (r11 == null) goto L21;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r11.size() != this.lastMediaAreas.size()) goto L9;
        return;
    L21:
        return;
    L9:
        HintView2 r02 = this.hintView;
        if (r02 == null) goto L12;
        r02.hide();
        this.hintView = null;
    L12:
        int r2 = 0;
    L14:
        if (r2 >= getChildCount()) goto L19;
        View r3 = getChildAt(r2);
        if (r3 == this.hintsContainer) goto L18;
        removeView(r3);
        r2 = r2 - 1;
    L18:
        r2 = r2 + 1;
        goto L14
    L19:
        this.selectedArea = null;
        this.parentHighlightScaleAlpha.set(0.0f, true);
        invalidate();
        onHintVisible(false);
        this.malicious = false;
        this.lastMediaAreas = r11;
        if (r11 == null) goto L51;
        this.shined = false;
        int r1 = 0;
    L24:
        if (r1 >= r11.size()) goto L41;
        TL_stories.MediaArea r22 = r11.get(r1);
        if (r22 == null) goto L40;
        if (r22.coordinates == null) goto L40;
        if ((r22 instanceof TL_stories.TL_mediaAreaSuggestedReaction) == false) goto L36;
        StoryReactionWidgetView r32 = new StoryReactionWidgetView(getContext(), this, (TL_stories.TL_mediaAreaSuggestedReaction) r22, r12);
        if (r10 == null) goto L34;
        r32.setViews(r10.views, false);
    L34:
        ScaleStateListAnimator.apply(r32);
        View r33 = r32;
    L39:
        r33.setOnClickListener(this);
        addView(r33);
        TL_stories.MediaAreaCoordinates r23 = r22.coordinates;
        double r5 = r23.w;
        double r24 = r23.h;
        goto L40
    L36:
        if ((r22 instanceof TL_stories.TL_mediaAreaWeather) == false) goto L38;
        TL_stories.TL_mediaAreaWeather r34 = (TL_stories.TL_mediaAreaWeather) r22;
        Weather.State r52 = new Weather.State();
        r52.emoji = r34.emoji;
        r52.temperature = (float) r34.temperature_c;
        LocationMarker r6 = new LocationMarker(getContext(), 1, AndroidUtilities.density, 0);
        r6.setMaxWidth(AndroidUtilities.displaySize.x);
        r6.setIsVideo(true);
        r6.setCodeEmoji(UserConfig.selectedAccount, r52.getEmoji());
        r6.setText(r52.getTemperature());
        r6.setType(3, r34.color);
        r33 = new FitViewWidget(getContext(), r6, r22);
        goto L39
    L38:
        r33 = new AreaView(getContext(), this.parentView, r22);
    L40:
        r1 = r1 + 1;
        goto L24
    L41:
        this.malicious = false;
        this.hintsContainer.bringToFront();
        return;
    }

    public void set(TL_stories.StoryItem r2, EmojiAnimationsOverlay r3) {
        if (r2 == null) goto L4;
        ArrayList<TL_stories.MediaArea> r0 = r2.media_areas;
    L5:
        set(r2, r0, r3);
        return;
    L4:
        r0 = null;
        goto L5
    }

    public void shine() {
        if (this.shined == true) goto L12;
        this.shined = true;
        int r0 = 0;
    L7:
        if (r0 >= getChildCount()) goto L16;
        View r1 = getChildAt(r0);
        if ((r1 instanceof AreaView) == false) goto L11;
        ((AreaView) r1).shine();
    L11:
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    }

    public abstract void showEffect(StoryReactionWidgetView r1);
}
