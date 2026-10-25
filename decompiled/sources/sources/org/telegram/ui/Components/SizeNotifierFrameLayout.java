package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.text.MeasuredText;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraView$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.pip.source.PipSourceSnapshot$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BlurSettingsBottomSheet;
import org.telegram.ui.ChatBackgroundDrawable;

/* loaded from: classes3.dex */
public class SizeNotifierFrameLayout extends FrameLayout implements Theme.Colorable {
    private static DispatchQueue blurQueue;
    public static boolean drawingBlur;
    private final float DOWN_SCALE;
    private final int TOP_CLIP_OFFSET;
    public AdjustPanLayoutHelper adjustPanLayoutHelper;
    boolean attached;
    private Drawable backgroundDrawable;
    private boolean backgroundMotion;
    private int backgroundTranslationY;
    public View backgroundView;
    private float bgAngle;
    final BlurBackgroundTask blurBackgroundTask;
    public ArrayList<View> blurBehindViews;
    ValueAnimator blurCrossfade;
    public float blurCrossfadeProgress;
    public boolean blurGeneratingTuskIsRunning;
    public boolean blurIsRunning;
    private boolean[] blurNodeInvalidated;
    private boolean[] blurNodeInvalidatedThisFrame;
    private RenderNode[] blurNodes;
    public Paint blurPaintBottom;
    public Paint blurPaintBottom2;
    public Paint blurPaintTop;
    public Paint blurPaintTop2;
    private int bottomClip;
    int count;
    int count2;
    BlurBitmap currentBitmap;
    protected SizeNotifierFrameLayoutDelegate delegate;
    protected final ArrayList<SizeNotifierFrameLayoutDelegate> delegates;
    private float drawnBottomOffset;
    private int emojiHeight;
    public boolean invalidateBlur;
    protected int keyboardHeight;
    private float lastDrawnBottomBlurOffset;
    private final ArrayList<IViewWithInvalidateCallback> lastViews;
    Matrix matrix;
    Matrix matrix2;
    public boolean needBlur;
    public boolean needBlurBottom;
    private boolean occupyStatusBar;
    private Drawable oldBackgroundDrawable;
    private boolean oldBackgroundMotion;
    private WallpaperParallaxEffect parallaxEffect;
    private float parallaxScale;
    private INavigationLayout parentLayout;
    private boolean paused;
    BlurBitmap prevBitmap;
    private Rect rect;
    private Paint selectedBlurPaint;
    private Paint selectedBlurPaint2;
    private boolean skipBackgroundDrawing;
    SnowflakesEffect snowflakesEffect;
    private float themeAnimationValue;
    int times;
    int times2;
    private float translationX;
    private float translationY;
    public ArrayList<BlurBitmap> unusedBitmaps;
    private final ArrayList<IViewWithInvalidateCallback> views;

    /* loaded from: classes7.dex */
    public class BackgroundView extends View {
        final /* synthetic */ SizeNotifierFrameLayout this$0;

        public BackgroundView(SizeNotifierFrameLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.onBackgroundViewInvalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r18) {
            if (SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(this.this$0) != null) goto L5;
            return;
        L5:
            if (SizeNotifierFrameLayout.m13019$$Nest$fgetskipBackgroundDrawing(this.this$0) == true) goto L153;
            Drawable r2 = this.this$0.getNewDrawable();
            boolean r3 = this.this$0.getNewDrawableMotion();
            float r5 = 0.0f;
            if (r2 == SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(this.this$0)) goto L28;
            if (r2 == null) goto L28;
            if (Theme.isAnimatingColor() == false) goto L14;
            SizeNotifierFrameLayout r4 = this.this$0;
            SizeNotifierFrameLayout.m13025$$Nest$fputoldBackgroundDrawable(r4, SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(r4));
            SizeNotifierFrameLayout r42 = this.this$0;
            SizeNotifierFrameLayout.m13026$$Nest$fputoldBackgroundMotion(r42, SizeNotifierFrameLayout.m13011$$Nest$fgetbackgroundMotion(r42));
        L14:
            if ((r2 instanceof MotionBackgroundDrawable) == false) goto L16;
            ((MotionBackgroundDrawable) r2).setParentView(this.this$0.backgroundView);
        L16:
            SizeNotifierFrameLayout.m13023$$Nest$fputbackgroundDrawable(this.this$0, r2);
            SizeNotifierFrameLayout r22 = this.this$0;
            if (r22.attached == true) goto L19;
        L21:
            SizeNotifierFrameLayout r23 = this.this$0;
            if (r23.attached == true) goto L24;
        L26:
            SizeNotifierFrameLayout.m13024$$Nest$fputbackgroundMotion(this.this$0, r3);
            SizeNotifierFrameLayout.m13027$$Nest$fputthemeAnimationValue(this.this$0, 0.0f);
            SizeNotifierFrameLayout r24 = this.this$0;
            r24.onUpdateBackgroundDrawable(SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(r24));
            SizeNotifierFrameLayout.m13028$$Nest$mcheckMotion(this.this$0);
        L30:
            SizeNotifierFrameLayout r25 = this.this$0;
            float r43 = 1.0f;
            SizeNotifierFrameLayout.m13027$$Nest$fputthemeAnimationValue(r25, Utilities.clamp(SizeNotifierFrameLayout.m13020$$Nest$fgetthemeAnimationValue(r25) + (AndroidUtilities.screenRefreshTime / 200.0f), 1.0f, 0.0f));
            int r32 = 0;
        L32:
            if (r32 >= 2) goto L142;
            SizeNotifierFrameLayout r7 = this.this$0;
            if (r32 != 0) goto L36;
            Drawable r72 = SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(r7);
        L37:
            if (r72 != null) goto L40;
            float r16 = r43;
        L140:
            r32 = r32 + 1;
            r43 = r16;
            r5 = 0.0f;
            goto L32
        L40:
            if (r32 == 1) goto L42;
        L46:
            r72.setAlpha(255);
        L47:
            SizeNotifierFrameLayout r8 = this.this$0;
            if (r32 != 0) goto L50;
            boolean r82 = SizeNotifierFrameLayout.m13016$$Nest$fgetoldBackgroundMotion(r8);
        L51:
            if (r82 == false) goto L53;
            float r83 = SizeNotifierFrameLayout.m13017$$Nest$fgetparallaxScale(this.this$0);
            float r9 = SizeNotifierFrameLayout.m13021$$Nest$fgettranslationX(this.this$0);
            float r10 = SizeNotifierFrameLayout.m13022$$Nest$fgettranslationY(this.this$0);
        L55:
            if ((r72 instanceof MotionBackgroundDrawable) == false) goto L79;
            MotionBackgroundDrawable r11 = (MotionBackgroundDrawable) r72;
            boolean r12 = r11.hasPattern();
            SizeNotifierFrameLayout r13 = this.this$0;
            if (r12 == true) goto L59;
            r16 = r43;
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(r13) == 0) goto L76;
            r18.save();
            r18.clipRect(0, 0, getMeasuredWidth(), getRootView().getMeasuredHeight() - SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0));
        L76:
            r11.setTranslationY(SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0));
            r72.setBounds(0, 0, getMeasuredWidth(), (int) ((getRootView().getMeasuredHeight() - SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0)) + r10));
            r72.draw(r18);
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L124;
            r18.restore();
        L124:
            if (r32 != 0) goto L140;
            if (SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(this.this$0) == null) goto L140;
            if (SizeNotifierFrameLayout.m13020$$Nest$fgetthemeAnimationValue(this.this$0) < r16) goto L140;
            SizeNotifierFrameLayout r44 = this.this$0;
            if (r44.attached == true) goto L132;
        L134:
            SizeNotifierFrameLayout r45 = this.this$0;
            if (r45.attached == true) goto L137;
        L139:
            SizeNotifierFrameLayout.m13025$$Nest$fputoldBackgroundDrawable(this.this$0, null);
            SizeNotifierFrameLayout.m13026$$Nest$fputoldBackgroundMotion(this.this$0, false);
            SizeNotifierFrameLayout.m13028$$Nest$mcheckMotion(this.this$0);
            this.this$0.backgroundView.invalidate();
            goto L140
        L137:
            if ((SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(r45) instanceof MotionBackgroundDrawable) == false) goto L139;
            ((MotionBackgroundDrawable) SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(this.this$0)).onDetachedFromWindow();
            goto L139
        L132:
            if ((SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(r44) instanceof ChatBackgroundDrawable) == false) goto L134;
            ((ChatBackgroundDrawable) SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(this.this$0)).onDetachedFromWindow(this.this$0.backgroundView);
            goto L134
        L59:
            if (r13.isActionBarVisible() == false) goto L61;
            int r112 = ActionBar.getCurrentActionBarHeight();
        L63:
            if (this.this$0.isStatusBarVisible() == true) goto L65;
        L67:
            int r122 = 0;
        L68:
            int r113 = r112 + r122;
            if (this.this$0.useRootView() == false) goto L71;
            int r123 = getRootView().getMeasuredHeight() - r113;
        L72:
            float r132 = Math.max(getMeasuredWidth() / r72.getIntrinsicWidth(), r123 / r72.getIntrinsicHeight());
            int r14 = (int) Math.ceil((r72.getIntrinsicWidth() * r132) * r83);
            r16 = r43;
            int r46 = (int) Math.ceil((r72.getIntrinsicHeight() * r132) * r83);
            int r52 = ((getMeasuredWidth() - r14) / 2) + ((int) r9);
            int r84 = ((SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0) + ((r123 - r46) / 2)) + r113) + ((int) r10);
            r18.save();
            r18.clipRect(0, r113, r14, getMeasuredHeight() - SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0));
            r72.setBounds(r52, r84, r14 + r52, r46 + r84);
            r72.draw(r18);
            SizeNotifierFrameLayout.m13029$$Nest$mcheckSnowflake(this.this$0, r18);
            r18.restore();
            goto L124
        L71:
            r123 = getHeight();
            goto L72
        L65:
            if (SizeNotifierFrameLayout.m13014$$Nest$fgetoccupyStatusBar(this.this$0) == false) goto L67;
            r122 = AndroidUtilities.statusBarHeight;
            goto L68
        L61:
            r112 = 0;
            goto L63
        L79:
            r16 = r43;
            if ((r72 instanceof ColorDrawable) == false) goto L88;
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L84;
            r18.save();
            r18.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight() - SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0));
        L84:
            r72.setBounds(0, 0, getMeasuredWidth(), getRootView().getMeasuredHeight());
            r72.draw(r18);
            SizeNotifierFrameLayout.m13029$$Nest$mcheckSnowflake(this.this$0, r18);
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L124;
            r18.restore();
            goto L124
        L88:
            if ((r72 instanceof GradientDrawable) == false) goto L96;
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L92;
            r18.save();
            r18.clipRect(0, 0, getMeasuredWidth(), getRootView().getMeasuredHeight() - SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0));
        L92:
            r72.setBounds(0, SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0), getMeasuredWidth(), SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0) + getRootView().getMeasuredHeight());
            r72.draw(r18);
            SizeNotifierFrameLayout.m13029$$Nest$mcheckSnowflake(this.this$0, r18);
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L124;
            r18.restore();
            goto L124
        L96:
            if ((r72 instanceof BitmapDrawable) == false) goto L116;
            if (((BitmapDrawable) r72).getTileModeX() != Shader.TileMode.REPEAT) goto L101;
            r18.save();
            float r53 = 2.0f / AndroidUtilities.density;
            r18.scale(r53, r53);
            r72.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r53), (int) Math.ceil(getRootView().getMeasuredHeight() / r53));
            r72.draw(r18);
            SizeNotifierFrameLayout.m13029$$Nest$mcheckSnowflake(this.this$0, r18);
            r18.restore();
            goto L124
        L101:
            if (this.this$0.isActionBarVisible() == false) goto L103;
            int r47 = ActionBar.getCurrentActionBarHeight();
        L105:
            if (this.this$0.isStatusBarVisible() == true) goto L107;
        L109:
            int r54 = 0;
        L110:
            int r48 = r47 + r54;
            if (this.this$0.useRootView() == false) goto L113;
            int r55 = getRootView().getMeasuredHeight() - r48;
        L114:
            float r114 = Math.max(getMeasuredWidth() / r72.getIntrinsicWidth(), r55 / r72.getIntrinsicHeight());
            int r124 = (int) Math.ceil((r72.getIntrinsicWidth() * r114) * r83);
            int r85 = (int) Math.ceil((r72.getIntrinsicHeight() * r114) * r83);
            int r115 = ((getMeasuredWidth() - r124) / 2) + ((int) r9);
            int r92 = ((SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0) + ((r55 - r85) / 2)) + r48) + ((int) r10);
            r18.save();
            r18.clipRect(0, r48, r124, getMeasuredHeight() - SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0));
            r72.setBounds(r115, r92, r124 + r115, r85 + r92);
            r72.draw(r18);
            SizeNotifierFrameLayout.m13029$$Nest$mcheckSnowflake(this.this$0, r18);
            r18.restore();
            goto L124
        L113:
            r55 = getHeight();
            goto L114
        L107:
            if (SizeNotifierFrameLayout.m13014$$Nest$fgetoccupyStatusBar(this.this$0) == false) goto L109;
            r54 = AndroidUtilities.statusBarHeight;
            goto L110
        L103:
            r47 = 0;
            goto L105
        L116:
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L119;
            r18.save();
            r18.clipRect(0, 0, getMeasuredWidth(), getRootView().getMeasuredHeight() - SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0));
        L119:
            if ((r72 instanceof ChatBackgroundDrawable) == false) goto L121;
            ((ChatBackgroundDrawable) r72).setParent(this);
        L121:
            float r6 = r83 - r16;
            float r49 = (((-getMeasuredWidth()) * r6) / 2.0f) + r9;
            float r93 = (((-getRootView().getMeasuredHeight()) * r6) / 2.0f) + r10;
            r72.setBounds((int) r49, (int) (SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0) + r93), (int) ((getMeasuredWidth() * r83) + r49), (int) ((SizeNotifierFrameLayout.m13012$$Nest$fgetbackgroundTranslationY(this.this$0) + (getRootView().getMeasuredHeight() * r83)) + r93));
            r72.draw(r18);
            SizeNotifierFrameLayout.m13029$$Nest$mcheckSnowflake(this.this$0, r18);
            if (SizeNotifierFrameLayout.m13013$$Nest$fgetbottomClip(this.this$0) == 0) goto L124;
            r18.restore();
            goto L124
        L53:
            r83 = r43;
            r9 = r5;
            r10 = r9;
            goto L55
        L50:
            r82 = SizeNotifierFrameLayout.m13011$$Nest$fgetbackgroundMotion(r8);
            goto L51
        L42:
            if (SizeNotifierFrameLayout.m13015$$Nest$fgetoldBackgroundDrawable(this.this$0) == null) goto L46;
            if (SizeNotifierFrameLayout.m13018$$Nest$fgetparentLayout(this.this$0) == null) goto L46;
            r72.setAlpha((int) (SizeNotifierFrameLayout.m13020$$Nest$fgetthemeAnimationValue(this.this$0) * 255.0f));
            goto L47
        L36:
            r72 = SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(r7);
            goto L37
        L142:
            if (SizeNotifierFrameLayout.m13020$$Nest$fgetthemeAnimationValue(this.this$0) == r43) goto L152;
            this.this$0.backgroundView.invalidate();
            return;
        L152:
            return;
        L24:
            if ((SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(r23) instanceof MotionBackgroundDrawable) == false) goto L26;
            ((MotionBackgroundDrawable) SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(this.this$0)).onAttachedToWindow();
            goto L26
        L19:
            if ((SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(r22) instanceof ChatBackgroundDrawable) == false) goto L21;
            ((ChatBackgroundDrawable) SizeNotifierFrameLayout.m13010$$Nest$fgetbackgroundDrawable(this.this$0)).onAttachedToWindow(this);
        L28:
            if (SizeNotifierFrameLayout.m13011$$Nest$fgetbackgroundMotion(this.this$0) == r3) goto L30;
            SizeNotifierFrameLayout.m13024$$Nest$fputbackgroundMotion(this.this$0, r3);
            SizeNotifierFrameLayout.m13028$$Nest$mcheckMotion(this.this$0);
            goto L30
        }
    }

    public class BlurBackgroundTask implements Runnable {
        BlurBitmap finalBitmap;
        int radius;
        final /* synthetic */ SizeNotifierFrameLayout this$0;

        /* renamed from: org.telegram.ui.Components.SizeNotifierFrameLayout$BlurBackgroundTask$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ BlurBackgroundTask this$1;
            final /* synthetic */ BlurBitmap val$oldBitmap;

            public AnonymousClass1(BlurBackgroundTask r1, BlurBitmap r2) {
                this.this$1 = r1;
                this.val$oldBitmap = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                SizeNotifierFrameLayout r0 = this.this$1.this$0;
                r0.blurCrossfadeProgress = 1.0f;
                r0.unusedBitmaps.add(this.val$oldBitmap);
                this.this$1.this$0.blurPaintTop2.setShader(null);
                this.this$1.this$0.blurPaintBottom2.setShader(null);
                this.this$1.this$0.invalidateBlurredViews();
                super.onAnimationEnd(r3);
            }
        }

        public static /* synthetic */ void $r8$lambda$45ccLZjP1K7SxYhvPVIv9gBuH2Y(BlurBackgroundTask r0) {
            r0.lambda$run$1();
        }

        /* renamed from: $r8$lambda$ReCsyC-LRMZqPdQk74-VOefFhsY, reason: not valid java name */
        public static /* synthetic */ void m13030$r8$lambda$ReCsyCLRMZqPdQk74VOefFhsY(BlurBackgroundTask r0) {
            r0.lambda$run$2();
        }

        public static /* synthetic */ void $r8$lambda$YxvKRMaQqT5B1jdi1oBi1QfFPIk(BlurBackgroundTask r0, ValueAnimator r1) {
            r0.lambda$run$0(r1);
        }

        private BlurBackgroundTask(SizeNotifierFrameLayout r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BlurBackgroundTask(SizeNotifierFrameLayout r1, SizeNotifierFrameLayoutIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$run$0(ValueAnimator r2) {
            SizeNotifierFrameLayout r0 = this.this$0;
            r0.blurCrossfadeProgress = ((Float) r2.getAnimatedValue()).floatValue();
            this.this$0.invalidateBlurredViews();
        }

        private /* synthetic */ void lambda$run$1() {
            SizeNotifierFrameLayout r1 = this.this$0;
            r1.blurGeneratingTuskIsRunning = false;
            r1.startBlur();
        }

        private /* synthetic */ void lambda$run$2() {
            SizeNotifierFrameLayout r0 = this.this$0;
            if (r0.blurIsRunning == true) goto L9;
            BlurBitmap r02 = this.finalBitmap;
            if (r02 == null) goto L7;
            r02.recycle();
        L7:
            this.this$0.blurGeneratingTuskIsRunning = false;
            return;
        L9:
            BlurBitmap r1 = r0.currentBitmap;
            r0.prevBitmap = r1;
            r0.blurPaintTop2.setShader(r0.blurPaintTop.getShader());
            SizeNotifierFrameLayout r03 = this.this$0;
            r03.blurPaintBottom2.setShader(r03.blurPaintBottom.getShader());
            Bitmap r2 = this.finalBitmap.topBitmap;
            Shader.TileMode r3 = Shader.TileMode.CLAMP;
            BitmapShader r04 = new BitmapShader(r2, r3, r3);
            this.this$0.blurPaintTop.setShader(r04);
            BlurBitmap r05 = this.finalBitmap;
            if (r05.needBlurBottom == true) goto L12;
        L14:
            ValueAnimator r06 = this.this$0.blurCrossfade;
            if (r06 == null) goto L17;
            r06.cancel();
        L17:
            SizeNotifierFrameLayout r07 = this.this$0;
            r07.blurCrossfadeProgress = 0.0f;
            r07.blurCrossfade = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.this$0.blurCrossfade.addUpdateListener(new SizeNotifierFrameLayout$BlurBackgroundTask$$ExternalSyntheticLambda1(this));
            this.this$0.blurCrossfade.addListener(new AnonymousClass1(this, r1));
            this.this$0.blurCrossfade.setDuration(50);
            this.this$0.blurCrossfade.start();
            this.this$0.invalidateBlurredViews();
            SizeNotifierFrameLayout r08 = this.this$0;
            r08.currentBitmap = this.finalBitmap;
            AndroidUtilities.runOnUIThread(new SizeNotifierFrameLayout$BlurBackgroundTask$$ExternalSyntheticLambda2(this), 16);
            return;
        L12:
            if (r05.bottomBitmap == null) goto L14;
            BitmapShader r09 = new BitmapShader(this.finalBitmap.bottomBitmap, r3, r3);
            this.this$0.blurPaintBottom.setShader(r09);
            goto L14
        }

        @Override // java.lang.Runnable
        public void run() {
            long r0 = System.currentTimeMillis();
            Utilities.stackBlurBitmap(this.finalBitmap.topBitmap, this.radius);
            BlurBitmap r2 = this.finalBitmap;
            if (r2.needBlurBottom == false) goto L7;
            Bitmap r22 = r2.bottomBitmap;
            if (r22 == null) goto L7;
            Utilities.stackBlurBitmap(r22, this.radius);
        L7:
            this.this$0.times = (int) (r2.times + (System.currentTimeMillis() - r0));
            SizeNotifierFrameLayout r02 = this.this$0;
            int r1 = r02.count + 1;
            r02.count = r1;
            if (r1 <= 1000) goto L10;
            StringBuilder r03 = new StringBuilder("chat blur generating average time");
            SizeNotifierFrameLayout r12 = this.this$0;
            r03.append(r12.times / r12.count);
            FileLog.d(r03.toString());
            SizeNotifierFrameLayout r04 = this.this$0;
            r04.count = 0;
            r04.times = 0;
        L10:
            AndroidUtilities.runOnUIThread(new SizeNotifierFrameLayout$BlurBackgroundTask$$ExternalSyntheticLambda0(this));
        }
    }

    /* loaded from: classes7.dex */
    public static class BlurBitmap {
        Bitmap bottomBitmap;
        Canvas bottomCanvas;
        float bottomOffset;
        float bottomScaleX;
        float bottomScaleY;
        float drawnListTranslationY;
        public boolean needBlurBottom;
        int pixelFixOffset;
        Bitmap topBitmap;
        Canvas topCanvas;
        float topScaleX;
        float topScaleY;

        private BlurBitmap() {
        }

        public /* synthetic */ BlurBitmap(SizeNotifierFrameLayoutIA r1) {
            this();
        }

        public void recycle() {
            this.topBitmap.recycle();
            Bitmap r1 = this.bottomBitmap;
            if (r1 == null) goto L6;
            r1.recycle();
            return;
        }
    }

    public interface IViewWithInvalidateCallback {
        void listenInvalidate(Runnable r1);
    }

    public static class SimplerCanvas extends Canvas {
        public SimplerCanvas(Bitmap r1) {
            super(r1);
        }

        @Override // android.graphics.Canvas
        public boolean clipPath(Path r1) {
            return false;
        }

        @Override // android.graphics.Canvas
        public boolean clipPath(Path r1, Region.Op r2) {
            return false;
        }

        @Override // android.graphics.Canvas
        public void drawText(CharSequence r1, int r2, int r3, float r4, float r5, Paint r6) {
        }

        @Override // android.graphics.Canvas
        public void drawText(String r1, float r2, float r3, Paint r4) {
        }

        @Override // android.graphics.Canvas
        public void drawText(String r1, int r2, int r3, float r4, float r5, Paint r6) {
        }

        @Override // android.graphics.Canvas
        public void drawText(char[] r1, int r2, int r3, float r4, float r5, Paint r6) {
        }

        @Override // android.graphics.Canvas
        public void drawTextOnPath(String r1, Path r2, float r3, float r4, Paint r5) {
        }

        @Override // android.graphics.Canvas
        public void drawTextOnPath(char[] r1, int r2, int r3, Path r4, float r5, float r6, Paint r7) {
        }

        @Override // android.graphics.Canvas
        public void drawTextRun(MeasuredText r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
        }

        @Override // android.graphics.Canvas
        public void drawTextRun(CharSequence r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
        }

        @Override // android.graphics.Canvas
        public void drawTextRun(char[] r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
        }
    }

    public interface SizeNotifierFrameLayoutDelegate {
        void onSizeChanged(int r1, boolean r2);
    }

    public static /* synthetic */ void $r8$lambda$7VpAsfh3XCuxzriCqsjqtUu3brk(SizeNotifierFrameLayout r0, boolean r1) {
        r0.lambda$notifyHeightChanged$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$DQkImTFa_QFI4LXUUFy8iekkIZI(SizeNotifierFrameLayout r0, int r1, int r2, float r3) {
        r0.lambda$checkMotion$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetbackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m13010$$Nest$fgetbackgroundDrawable(SizeNotifierFrameLayout r0) {
        return r0.backgroundDrawable;
    }

    /* renamed from: -$$Nest$fgetbackgroundMotion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13011$$Nest$fgetbackgroundMotion(SizeNotifierFrameLayout r0) {
        return r0.backgroundMotion;
    }

    /* renamed from: -$$Nest$fgetbackgroundTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13012$$Nest$fgetbackgroundTranslationY(SizeNotifierFrameLayout r0) {
        return r0.backgroundTranslationY;
    }

    /* renamed from: -$$Nest$fgetbottomClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13013$$Nest$fgetbottomClip(SizeNotifierFrameLayout r0) {
        return r0.bottomClip;
    }

    /* renamed from: -$$Nest$fgetoccupyStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13014$$Nest$fgetoccupyStatusBar(SizeNotifierFrameLayout r0) {
        return r0.occupyStatusBar;
    }

    /* renamed from: -$$Nest$fgetoldBackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m13015$$Nest$fgetoldBackgroundDrawable(SizeNotifierFrameLayout r0) {
        return r0.oldBackgroundDrawable;
    }

    /* renamed from: -$$Nest$fgetoldBackgroundMotion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13016$$Nest$fgetoldBackgroundMotion(SizeNotifierFrameLayout r0) {
        return r0.oldBackgroundMotion;
    }

    /* renamed from: -$$Nest$fgetparallaxScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13017$$Nest$fgetparallaxScale(SizeNotifierFrameLayout r0) {
        return r0.parallaxScale;
    }

    /* renamed from: -$$Nest$fgetparentLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ INavigationLayout m13018$$Nest$fgetparentLayout(SizeNotifierFrameLayout r0) {
        return r0.parentLayout;
    }

    /* renamed from: -$$Nest$fgetskipBackgroundDrawing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13019$$Nest$fgetskipBackgroundDrawing(SizeNotifierFrameLayout r0) {
        return r0.skipBackgroundDrawing;
    }

    /* renamed from: -$$Nest$fgetthemeAnimationValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13020$$Nest$fgetthemeAnimationValue(SizeNotifierFrameLayout r0) {
        return r0.themeAnimationValue;
    }

    /* renamed from: -$$Nest$fgettranslationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13021$$Nest$fgettranslationX(SizeNotifierFrameLayout r0) {
        return r0.translationX;
    }

    /* renamed from: -$$Nest$fgettranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13022$$Nest$fgettranslationY(SizeNotifierFrameLayout r0) {
        return r0.translationY;
    }

    /* renamed from: -$$Nest$fputbackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13023$$Nest$fputbackgroundDrawable(SizeNotifierFrameLayout r0, Drawable r1) {
        r0.backgroundDrawable = r1;
    }

    /* renamed from: -$$Nest$fputbackgroundMotion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13024$$Nest$fputbackgroundMotion(SizeNotifierFrameLayout r0, boolean r1) {
        r0.backgroundMotion = r1;
    }

    /* renamed from: -$$Nest$fputoldBackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13025$$Nest$fputoldBackgroundDrawable(SizeNotifierFrameLayout r0, Drawable r1) {
        r0.oldBackgroundDrawable = r1;
    }

    /* renamed from: -$$Nest$fputoldBackgroundMotion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13026$$Nest$fputoldBackgroundMotion(SizeNotifierFrameLayout r0, boolean r1) {
        r0.oldBackgroundMotion = r1;
    }

    /* renamed from: -$$Nest$fputthemeAnimationValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13027$$Nest$fputthemeAnimationValue(SizeNotifierFrameLayout r0, float r1) {
        r0.themeAnimationValue = r1;
    }

    /* renamed from: -$$Nest$mcheckMotion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13028$$Nest$mcheckMotion(SizeNotifierFrameLayout r0) {
        r0.checkMotion();
    }

    /* renamed from: -$$Nest$mcheckSnowflake, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13029$$Nest$mcheckSnowflake(SizeNotifierFrameLayout r0, Canvas r1) {
        r0.checkSnowflake(r1);
    }

    public SizeNotifierFrameLayout(Context r2) {
        this(r2, null);
    }

    public SizeNotifierFrameLayout(Context r3, INavigationLayout r4) {
        super(r3);
        this.rect = new Rect();
        this.delegates = new ArrayList();
        this.occupyStatusBar = true;
        this.parallaxScale = 1.0f;
        this.paused = true;
        this.unusedBitmaps = new ArrayList(10);
        this.blurBehindViews = new ArrayList();
        this.matrix = new Matrix();
        this.matrix2 = new Matrix();
        this.blurPaintTop = new Paint();
        this.blurPaintTop2 = new Paint();
        this.blurPaintBottom = new Paint();
        this.blurPaintBottom2 = new Paint();
        this.DOWN_SCALE = 12.0f;
        this.TOP_CLIP_OFFSET = 34;
        this.themeAnimationValue = 1.0f;
        this.blurBackgroundTask = new BlurBackgroundTask(this, null);
        this.blurNodeInvalidatedThisFrame = new boolean[2];
        this.blurNodeInvalidated = new boolean[2];
        this.lastViews = new ArrayList();
        this.views = new ArrayList();
        setWillNotDraw(false);
        this.parentLayout = r4;
        this.adjustPanLayoutHelper = createAdjustPanLayoutHelper();
    }

    private void checkLayerType() {
    }

    private void checkMotion() {
        if (this.oldBackgroundMotion == true) goto L12;
        if (this.backgroundMotion == true) goto L12;
        WallpaperParallaxEffect r0 = this.parallaxEffect;
        if (r0 == null) goto L22;
        r0.setEnabled(false);
        this.parallaxEffect = null;
        this.parallaxScale = 1.0f;
        this.translationX = 0.0f;
        this.translationY = 0.0f;
        return;
    L22:
        return;
    L12:
        if (this.parallaxEffect != null) goto L19;
        WallpaperParallaxEffect r02 = new WallpaperParallaxEffect(getContext());
        this.parallaxEffect = r02;
        r02.setCallback(new SizeNotifierFrameLayout$$ExternalSyntheticLambda2(this));
        if (getMeasuredWidth() == 0) goto L19;
        if (getMeasuredHeight() == 0) goto L19;
        this.parallaxScale = this.parallaxEffect.getScale(getMeasuredWidth(), getMeasuredHeight());
    L19:
        if (this.paused == true) goto L23;
        this.parallaxEffect.setEnabled(true);
        return;
    }

    private void checkSnowflake(Canvas r3) {
        if (this.backgroundView != null) goto L5;
        return;
    L5:
        if (Theme.canStartHolidayAnimation() == true) goto L7;
        return;
    L7:
        if (LiteMode.isEnabled(32) == true) goto L9;
        return;
    L9:
        if (this.snowflakesEffect != null) goto L11;
        SnowflakesEffect r0 = new SnowflakesEffect(1);
        this.snowflakesEffect = r0;
        r0.setForcedColor(-1);
    L11:
        this.snowflakesEffect.onDraw(this.backgroundView, r3);
    }

    private void drawListWithCallbacks(Canvas r5, boolean r6) {
        if (invalidateOptimized() == true) goto L6;
        drawList(r5, r6, null);
        return;
    L6:
        this.lastViews.clear();
        this.lastViews.addAll(this.views);
        this.views.clear();
        drawList(r5, r6, this.views);
        ArrayList<IViewWithInvalidateCallback> r52 = this.lastViews;
        int r62 = r52.size();
        int r0 = 0;
        int r2 = 0;
    L7:
        if (r2 >= r62) goto L9;
        IViewWithInvalidateCallback r3 = r52.get(r2);
        r2 = r2 + 1;
        r3.listenInvalidate(null);
        goto L7
    L9:
        ArrayList<IViewWithInvalidateCallback> r53 = this.views;
        int r63 = r53.size();
    L10:
        if (r0 >= r63) goto L12;
        IViewWithInvalidateCallback r1 = r53.get(r0);
        r0 = r0 + 1;
        r1.listenInvalidate(new SizeNotifierFrameLayout$$ExternalSyntheticLambda1(this));
        goto L10
    }

    public static float getBlurRadius() {
        int r0 = SharedConfig.getDevicePerformanceClass();
        if (r0 != 1) goto L5;
        return 4.0f;
    L5:
        if (r0 == 2) goto L8;
        return 3.0f;
    L8:
        return 60.0f;
    }

    public static float getRenderNodeScale() {
        int r0 = SharedConfig.getDevicePerformanceClass();
        if (r0 != 1) goto L5;
        int r02 = AndroidUtilities.dp(12.0f);
    L8:
        return r02;
    L5:
        if (r0 == 2) goto L10;
        r02 = AndroidUtilities.dp(15.0f);
        goto L8
    L10:
        return AndroidUtilities.density;
    }

    private /* synthetic */ void lambda$checkMotion$0(int r1, int r2, float r3) {
        this.translationX = r1;
        this.translationY = r2;
        this.bgAngle = r3;
        View r0 = this.backgroundView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$notifyHeightChanged$1(boolean r4) {
        SizeNotifierFrameLayoutDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.onSizeChanged(this.keyboardHeight, r4);
    L5:
        int r02 = 0;
    L7:
        if (r02 >= this.delegates.size()) goto L9;
        this.delegates.get(r02).onSizeChanged(this.keyboardHeight, r4);
        r02 = r02 + 1;
        goto L7
    }

    private void updateBlurShaderPosition(float r6, boolean r7) {
        if (r7 == false) goto L4;
        Paint r0 = this.blurPaintTop;
    L5:
        this.selectedBlurPaint = r0;
        if (r7 == false) goto L8;
        Paint r02 = this.blurPaintTop2;
    L9:
        this.selectedBlurPaint2 = r02;
        if (r7 == false) goto L13;
        r6 = r6 + getTranslationY();
    L13:
        if (this.selectedBlurPaint.getShader() == null) goto L26;
        this.matrix.reset();
        this.matrix2.reset();
        if (r7 == true) goto L19;
        float r62 = -r6;
        BlurBitmap r72 = this.currentBitmap;
        this.matrix.setTranslate(0.0f, (((r72.bottomOffset + r62) - r72.pixelFixOffset) - 34.0f) - (r72.drawnListTranslationY - (getBottomOffset() + getListTranslationY())));
        Matrix r73 = this.matrix;
        BlurBitmap r2 = this.currentBitmap;
        r73.preScale(r2.bottomScaleX, r2.bottomScaleY);
        BlurBitmap r74 = this.prevBitmap;
        if (r74 == null) goto L22;
        this.matrix2.setTranslate(0.0f, (((r62 + r74.bottomOffset) - r74.pixelFixOffset) - 34.0f) - (r74.drawnListTranslationY - (getBottomOffset() + getListTranslationY())));
        Matrix r63 = this.matrix2;
        BlurBitmap r75 = this.prevBitmap;
        r63.preScale(r75.bottomScaleX, r75.bottomScaleY);
    L22:
        this.selectedBlurPaint.getShader().setLocalMatrix(this.matrix);
        if (this.selectedBlurPaint2.getShader() == null) goto L27;
        this.selectedBlurPaint2.getShader().setLocalMatrix(this.matrix);
        return;
    L27:
        return;
    L19:
        float r64 = -r6;
        this.matrix.setTranslate(0.0f, (r64 - this.currentBitmap.pixelFixOffset) - 34.0f);
        Matrix r76 = this.matrix;
        BlurBitmap r22 = this.currentBitmap;
        r76.preScale(r22.topScaleX, r22.topScaleY);
        if (this.prevBitmap == null) goto L22;
        this.matrix2.setTranslate(0.0f, (r64 - r7.pixelFixOffset) - 34.0f);
        Matrix r65 = this.matrix2;
        BlurBitmap r77 = this.prevBitmap;
        r65.preScale(r77.topScaleX, r77.topScaleY);
        goto L22
    L26:
        return;
    L8:
        r02 = this.blurPaintBottom2;
        goto L9
    L4:
        r0 = this.blurPaintBottom;
        goto L5
    }

    public boolean DRAW_USING_RENDERNODE() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return false;
    L5:
        if (SharedConfig.useNewBlur == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void addDelegate(SizeNotifierFrameLayoutDelegate r1) {
        this.delegates.add(r1);
    }

    public AdjustPanLayoutHelper createAdjustPanLayoutHelper() {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        boolean[] r0 = this.blurNodeInvalidatedThisFrame;
        r0[0] = false;
        r0[1] = false;
        if (this.blurIsRunning == false) goto L5;
        startBlur();
    L5:
        super.dispatchDraw(r4);
    }

    public void drawBlurRect(Canvas r9, float r10, Rect r11, Paint r12, boolean r13) {
        if (DRAW_USING_RENDERNODE() == true) goto L5;
    L7:
        int r0 = Theme.key_chat_BlurAlphaSlow;
    L8:
        drawBlurRect(r9, r10, r11, r12, r13, Color.alpha(Theme.getColor(r0, getResourceProvider())));
        return;
    L5:
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L7;
        r0 = Theme.key_chat_BlurAlpha;
        goto L8
    }

    public void drawBlurRect(Canvas r3, float r4, Rect r5, Paint r6, boolean r7, float r8) {
        if (DRAW_USING_RENDERNODE() == true) goto L5;
    L7:
        int r0 = Theme.key_chat_BlurAlphaSlow;
    L8:
        drawBlurRect(r3, r4, r5, r6, r7, AndroidUtilities.lerp(255, Color.alpha(Theme.getColor(r0, getResourceProvider())), r8));
        return;
    L5:
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L7;
        r0 = Theme.key_chat_BlurAlpha;
        goto L8
    }

    public void drawBlurRect(Canvas r19, float r20, Rect r21, Paint r22, boolean r23, int r24) {
        int r5 = r24;
        if (r5 == 255) goto L5;
    L7:
        int r10 = r5;
        if (SharedConfig.chatBlurEnabled() == true) goto L12;
        r19.drawRect(r21, r22);
        return;
    L12:
        if (DRAW_USING_RENDERNODE() == false) goto L57;
        if (r19.isHardwareAccelerated() == true) goto L18;
        r19.drawRect(r21, r22);
        return;
    L18:
        if (this.blurNodes != null) goto L20;
        this.blurNodes = new RenderNode[2];
    L20:
        float r52 = getRenderNodeScale();
        int r11 = !r23 ? 1 : 0;
        if (r23 == false) goto L23;
    L27:
        int r13 = AndroidUtilities.dp(36.0f);
        if (r10 < 255) goto L30;
    L41:
        boolean r17 = true;
        float r242 = 255.0f;
        float r16 = 1.0f;
    L43:
        if (invalidateOptimized() == true) goto L45;
        this.blurNodeInvalidated[r11] = r17;
        invalidateBlurredViews();
    L45:
        r19.save();
        r19.drawRect(r21, r22);
        r19.clipRect(r21);
        RenderNode r4 = this.blurNodes[r11];
        if (r4 != null) goto L48;
    L54:
        r19.restore();
        return;
    L48:
        if (r10 >= 255) goto L54;
        r4.setAlpha(r16 - (r10 / r242));
        if (r23 == false) goto L52;
        r19.translate(0.0f, (-r20) - getTranslationY());
    L53:
        r19.translate(0.0f, -r13);
        r19.scale(r52, r52);
        r19.drawRenderNode(this.blurNodes[r11]);
        goto L54
    L52:
        r19.translate(0.0f, ((-r20) + this.drawnBottomOffset) - (this.lastDrawnBottomBlurOffset - (getBottomOffset() + getListTranslationY())));
        goto L53
    L30:
        if (this.blurNodeInvalidated[r11] == false) goto L41;
        if (this.blurNodeInvalidatedThisFrame[r11] == true) goto L41;
        RenderNode[] r15 = this.blurNodes;
        if (r15[r11] != null) goto L36;
        PipSourceSnapshot$$ExternalSyntheticApiModelOutline0.m();
        r242 = 255.0f;
        r16 = 1.0f;
        r15[r11] = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("blurNode" + r11);
        ColorMatrix r6 = new ColorMatrix();
        r6.setSaturation(2.0f);
        r17 = true;
        this.blurNodes[r11].setRenderEffect(RenderEffect.createChainEffect(RenderEffect.createBlurEffect(getBlurRadiusInternal(), getBlurRadiusInternal(), CameraView$$ExternalSyntheticApiModelOutline0.m()), RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(r6))));
    L37:
        int r42 = getMeasuredWidth();
        int r62 = (ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight) + AndroidUtilities.dp(100.0f);
        this.blurNodes[r11].setPosition(0, 0, (int) (r42 / r52), (int) (((r13 * 2) + r62) / r52));
        RecordingCanvas r43 = this.blurNodes[r11].beginRecording();
        drawingBlur = r17;
        float r7 = r16 / r52;
        r43.scale(r7, r7);
        r43.drawPaint(r22);
        r43.translate(0.0f, r13);
        if (r23 == true) goto L40;
        float r72 = getBottomOffset();
        this.lastDrawnBottomBlurOffset = r72;
        float r73 = r72 - r62;
        this.drawnBottomOffset = r73;
        r43.translate(0.0f, -r73);
    L40:
        drawListWithCallbacks(r43, r23);
        drawingBlur = false;
        this.blurNodes[r11].endRecording();
        this.blurNodeInvalidatedThisFrame[r11] = r17;
        this.blurNodeInvalidated[r11] = false;
        goto L43
    L36:
        r17 = true;
        r242 = 255.0f;
        r16 = 1.0f;
        goto L37
    L23:
        if (this.blurNodeInvalidated[r11] == true) goto L27;
        if (Math.abs(getBottomOffset() - this.lastDrawnBottomBlurOffset) <= 0.1f) goto L27;
        this.blurNodeInvalidated[r11] = true;
        goto L27
    L57:
        if (this.currentBitmap != null) goto L60;
        r19.drawRect(r21, r22);
        return;
    L60:
        updateBlurShaderPosition(r20, r23);
        r22.setAlpha(255);
        if (this.blurCrossfadeProgress != 1.0f) goto L63;
    L65:
        r19.drawRect(r21, r22);
        r19.drawRect(r21, this.selectedBlurPaint);
    L66:
        r22.setAlpha(r10);
        r19.drawRect(r21, r22);
        return;
    L63:
        if (this.selectedBlurPaint2.getShader() == null) goto L65;
        r19.drawRect(r21, r22);
        r19.drawRect(r21, this.selectedBlurPaint2);
        r19.saveLayerAlpha(r21.left, r21.top, r21.right, r21.bottom, (int) (this.blurCrossfadeProgress * 255.0f), 31);
        r19.drawRect(r21, r22);
        r19.drawRect(r21, this.selectedBlurPaint);
        r19.restore();
        goto L66
    L5:
        if (ExteraConfig.getForceBlur() == false) goto L7;
        r5 = 200;
        goto L7
    }

    public void drawList(Canvas r1, boolean r2, ArrayList<IViewWithInvalidateCallback> r3) {
    }

    public Drawable getBackgroundImage() {
        return this.backgroundDrawable;
    }

    public int getBackgroundSizeY() {
        if ((this.backgroundDrawable instanceof ChatBackgroundDrawable) == false) goto L5;
        int r0 = this.backgroundTranslationY;
    L7:
        return getMeasuredHeight() - r0;
    L5:
        r0 = 0;
        goto L7
    }

    public int getBackgroundTranslationY() {
        Drawable r0 = this.backgroundDrawable;
        if ((r0 instanceof MotionBackgroundDrawable) == false) goto L7;
        return this.backgroundTranslationY;
    L7:
        if ((r0 instanceof ChatBackgroundDrawable) == true) goto L9;
        return 0;
    L9:
        return this.backgroundTranslationY;
    }

    public float getBlurRadiusInternal() {
        return getBlurRadius();
    }

    public float getBottomOffset() {
        return getMeasuredHeight();
    }

    public int getBottomPadding() {
        return 0;
    }

    public float getBottomTranslation() {
        return 0.0f;
    }

    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public int getHeightWithKeyboard() {
        return this.keyboardHeight + getMeasuredHeight();
    }

    public int getKeyboardHeight() {
        return this.keyboardHeight;
    }

    public float getListTranslationY() {
        return 0.0f;
    }

    public Drawable getNewDrawable() {
        return Theme.getCachedWallpaperNonBlocking();
    }

    public boolean getNewDrawableMotion() {
        return Theme.isWallpaperMotion();
    }

    public Theme.ResourcesProvider getResourceProvider() {
        return null;
    }

    public int getScrollOffset() {
        return 0;
    }

    public void invalidateBackground() {
        View r0 = this.backgroundView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void invalidateBlur() {
        if (SharedConfig.chatBlurEnabled() == false) goto L13;
        this.invalidateBlur = true;
        if (this.blurIsRunning == true) goto L8;
        return;
    L8:
        if (this.blurGeneratingTuskIsRunning == true) goto L14;
        invalidate();
        return;
    L14:
        return;
    }

    public void invalidateBlurredViews() {
        boolean[] r0 = this.blurNodeInvalidated;
        int r1 = 0;
        r0[0] = true;
        r0[1] = true;
    L4:
        if (r1 >= this.blurBehindViews.size()) goto L6;
        this.blurBehindViews.get(r1).invalidate();
        r1 = r1 + 1;
        goto L4
    }

    public boolean invalidateOptimized() {
        return false;
    }

    public boolean isActionBarVisible() {
        return true;
    }

    public boolean isStatusBarVisible() {
        return true;
    }

    public int measureKeyboardHeight() {
        View r0 = getRootView();
        getWindowVisibleDisplayFrame(this.rect);
        Rect r1 = this.rect;
        if (r1.bottom == 0) goto L5;
    L7:
        int r12 = r0.getHeight();
        if (this.rect.top == 0) goto L10;
        int r2 = AndroidUtilities.statusBarHeight;
    L11:
        int r13 = (r12 - r2) - AndroidUtilities.getViewInset(r0);
        Rect r02 = this.rect;
        int r03 = Math.max(0, r13 - (r02.bottom - r02.top));
        this.keyboardHeight = r03;
        return r03;
    L10:
        r2 = 0;
        goto L11
    L5:
        if (r1.top != 0) goto L7;
        return 0;
    }

    public void notifyHeightChanged() {
        WallpaperParallaxEffect r0 = this.parallaxEffect;
        if (r0 == null) goto L6;
        this.parallaxScale = r0.getScale(getMeasuredWidth(), getMeasuredHeight());
    L6:
        if (this.delegate == null) goto L8;
    L11:
        this.keyboardHeight = measureKeyboardHeight();
        Point r02 = AndroidUtilities.displaySize;
        if (r02.x <= r02.y) goto L14;
        boolean r03 = true;
    L15:
        post(new SizeNotifierFrameLayout$$ExternalSyntheticLambda0(this, r03));
        return;
    L14:
        r03 = false;
        goto L15
    L8:
        if (this.delegates.isEmpty() == false) goto L11;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        if (this.needBlur == true) goto L5;
    L7:
        Drawable r0 = this.backgroundDrawable;
        if ((r0 instanceof ChatBackgroundDrawable) == false) goto L10;
        ((ChatBackgroundDrawable) r0).onAttachedToWindow(this.backgroundView);
    L10:
        Drawable r02 = this.backgroundDrawable;
        if ((r02 instanceof MotionBackgroundDrawable) == false) goto L13;
        ((MotionBackgroundDrawable) r02).onAttachedToWindow();
    L13:
        Drawable r03 = this.oldBackgroundDrawable;
        if ((r03 instanceof ChatBackgroundDrawable) == false) goto L16;
        ((ChatBackgroundDrawable) r03).onAttachedToWindow(this.backgroundView);
    L16:
        Drawable r2 = this.oldBackgroundDrawable;
        if ((r2 instanceof MotionBackgroundDrawable) == false) goto L20;
        ((MotionBackgroundDrawable) r2).onAttachedToWindow();
        return;
    L20:
        return;
    L5:
        if (this.blurIsRunning == true) goto L7;
        this.blurIsRunning = true;
        this.invalidateBlur = true;
        goto L7
    }

    public void onBackgroundViewInvalidate() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        this.blurPaintTop.setShader(null);
        this.blurPaintTop2.setShader(null);
        this.blurPaintBottom.setShader(null);
        this.blurPaintBottom2.setShader(null);
        ValueAnimator r1 = this.blurCrossfade;
        if (r1 == null) goto L5;
        r1.cancel();
    L5:
        BlurBitmap r12 = this.currentBitmap;
        if (r12 == null) goto L8;
        r12.recycle();
        this.currentBitmap = null;
    L8:
        int r13 = 0;
    L9:
        int r2 = this.unusedBitmaps.size();
        ArrayList<BlurBitmap> r3 = this.unusedBitmaps;
        if (r13 >= r2) goto L15;
        if (r3.get(r13) == null) goto L14;
        this.unusedBitmaps.get(r13).recycle();
    L14:
        r13 = r13 + 1;
        goto L9
    L15:
        r3.clear();
        this.blurIsRunning = false;
        Drawable r0 = this.backgroundDrawable;
        if ((r0 instanceof ChatBackgroundDrawable) == false) goto L18;
        ((ChatBackgroundDrawable) r0).onDetachedFromWindow(this.backgroundView);
    L18:
        Drawable r02 = this.oldBackgroundDrawable;
        if ((r02 instanceof ChatBackgroundDrawable) == false) goto L21;
        ((ChatBackgroundDrawable) r02).onDetachedFromWindow(this.backgroundView);
    L21:
        Drawable r03 = this.backgroundDrawable;
        if ((r03 instanceof MotionBackgroundDrawable) == false) goto L24;
        ((MotionBackgroundDrawable) r03).onDetachedFromWindow();
    L24:
        Drawable r4 = this.oldBackgroundDrawable;
        if ((r4 instanceof MotionBackgroundDrawable) == false) goto L31;
        ((MotionBackgroundDrawable) r4).onDetachedFromWindow();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        notifyHeightChanged();
    }

    public void onPause() {
        WallpaperParallaxEffect r0 = this.parallaxEffect;
        if (r0 == null) goto L5;
        r0.setEnabled(false);
    L5:
        this.paused = true;
    }

    public void onResume() {
        WallpaperParallaxEffect r0 = this.parallaxEffect;
        if (r0 == null) goto L5;
        r0.setEnabled(true);
    L5:
        this.paused = false;
    }

    public void onUpdateBackgroundDrawable(Drawable r1) {
    }

    public void removeDelegate(SizeNotifierFrameLayoutDelegate r1) {
        this.delegates.remove(r1);
    }

    public void setBackgroundImage(Drawable r3, boolean r4) {
        if (this.backgroundDrawable != r3) goto L6;
        return;
    L6:
        if (this.backgroundView != null) goto L9;
        BackgroundView r42 = new BackgroundView(this, getContext());
        this.backgroundView = r42;
        addView(r42, 0, LayoutHelper.createFrame(-1, -1.0f));
        checkLayerType();
    L9:
        if ((r3 instanceof MotionBackgroundDrawable) == false) goto L12;
        ((MotionBackgroundDrawable) r3).setParentView(this.backgroundView);
    L12:
        if (this.attached == false) goto L16;
        Drawable r43 = this.backgroundDrawable;
        if ((r43 instanceof ChatBackgroundDrawable) == false) goto L16;
        ((ChatBackgroundDrawable) r43).onDetachedFromWindow(this.backgroundView);
    L16:
        this.backgroundDrawable = r3;
        if (this.attached == false) goto L22;
        if ((r3 instanceof ChatBackgroundDrawable) == false) goto L22;
        ((ChatBackgroundDrawable) r3).onAttachedToWindow(this.backgroundView);
    L22:
        if (this.attached == false) goto L27;
        Drawable r32 = this.backgroundDrawable;
        if ((r32 instanceof MotionBackgroundDrawable) == false) goto L27;
        ((MotionBackgroundDrawable) r32).onDetachedFromWindow();
    L27:
        if (this.attached == false) goto L31;
        Drawable r33 = this.backgroundDrawable;
        if ((r33 instanceof MotionBackgroundDrawable) == false) goto L31;
        ((MotionBackgroundDrawable) r33).onAttachedToWindow();
    L31:
        onUpdateBackgroundDrawable(this.backgroundDrawable);
        checkMotion();
        this.backgroundView.invalidate();
        checkLayerType();
    }

    public void setBackgroundTranslation(int r2) {
        if (r2 == this.backgroundTranslationY) goto L8;
        this.backgroundTranslationY = r2;
        View r1 = this.backgroundView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    L9:
        return;
    }

    public void setBottomClip(int r2) {
        if (r2 == this.bottomClip) goto L8;
        this.bottomClip = r2;
        View r1 = this.backgroundView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    L9:
        return;
    }

    public void setDelegate(SizeNotifierFrameLayoutDelegate r1) {
        this.delegate = r1;
    }

    public void setEmojiKeyboardHeight(int r2) {
        if (this.emojiHeight == r2) goto L8;
        this.emojiHeight = r2;
        View r1 = this.backgroundView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    L9:
        return;
    }

    public void setOccupyStatusBar(boolean r1) {
        this.occupyStatusBar = r1;
    }

    public void setSkipBackgroundDrawing(boolean r2) {
        if (this.skipBackgroundDrawing == r2) goto L8;
        this.skipBackgroundDrawing = r2;
        View r1 = this.backgroundView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    L9:
        return;
    }

    public void startBlur() {
        if (this.blurIsRunning == true) goto L5;
        return;
    L5:
        if (this.blurGeneratingTuskIsRunning == false) goto L7;
        return;
    L7:
        if (this.invalidateBlur == true) goto L9;
        return;
    L9:
        if (SharedConfig.chatBlurEnabled() == true) goto L11;
        return;
    L11:
        if (DRAW_USING_RENDERNODE() == false) goto L14;
        return;
    L14:
        if (Color.alpha(Theme.getColor(Theme.key_chat_BlurAlphaSlow)) == 255) goto L16;
    L18:
        int r2 = getMeasuredWidth();
        int r3 = AndroidUtilities.dp(100.0f) + (ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight);
        if (r2 == 0) goto L56;
        if (r3 == 0) goto L59;
        this.invalidateBlur = false;
        this.blurGeneratingTuskIsRunning = true;
        float r6 = r3;
        int r7 = ((int) (r6 / 12.0f)) + 34;
        float r8 = r2;
        int r0 = (int) (r8 / 12.0f);
        long r9 = System.currentTimeMillis();
        if (this.unusedBitmaps.size() <= 0) goto L25;
        ArrayList<BlurBitmap> r11 = this.unusedBitmaps;
        BlurBitmap r112 = r11.remove(r11.size() - 1);
    L26:
        if (r112 != null) goto L30;
        r112 = new BlurBitmap(null);
        Bitmap.Config r13 = Bitmap.Config.ARGB_8888;
        r112.topBitmap = Bitmap.createBitmap(r0, r7, r13);
        r112.topCanvas = new SimplerCanvas(r112.topBitmap);
        if (this.needBlurBottom == false) goto L33;
        r112.bottomBitmap = Bitmap.createBitmap(r0, r7, r13);
        r112.bottomCanvas = new SimplerCanvas(r112.bottomBitmap);
    L33:
        float r02 = r112.topBitmap.getWidth() / r8;
        float r72 = (r112.topBitmap.getHeight() - 34) / r6;
        int r132 = r112.topCanvas.save();
        r112.pixelFixOffset = getScrollOffset() % 24;
        float r15 = r72 * 10.0f;
        r112.topCanvas.clipRect(1.0f, r15, r112.topBitmap.getWidth(), r112.topBitmap.getHeight() - 1);
        r112.topCanvas.scale(r02, r72);
        r112.topCanvas.translate(0.0f, r15 + r112.pixelFixOffset);
        r112.topScaleX = 1.0f / r02;
        r112.topScaleY = 1.0f / r72;
        drawList(r112.topCanvas, true, null);
        r112.topCanvas.restoreToCount(r132);     // Catch: Exception -> L36
    L39:
        if (this.needBlurBottom == false) goto L41;
        float r03 = r112.bottomBitmap.getWidth() / r8;
        float r4 = (r112.bottomBitmap.getHeight() - 34) / r6;
        r112.needBlurBottom = true;
        r112.bottomOffset = getBottomOffset() - r6;
        r112.drawnListTranslationY = getBottomOffset();
        r112.bottomCanvas.save();
        float r152 = r4 * 10.0f;
        r112.bottomCanvas.clipRect(1.0f, r152, r112.bottomBitmap.getWidth(), r112.bottomBitmap.getHeight() - 1);
        r112.bottomCanvas.scale(r03, r4);
        r112.bottomCanvas.translate(0.0f, (r152 - r112.bottomOffset) + r112.pixelFixOffset);
        r112.bottomScaleX = 1.0f / r03;
        r112.bottomScaleY = 1.0f / r4;
        drawList(r112.bottomCanvas, false, null);
        r112.bottomCanvas.restore();
    L42:
        this.times2 = (int) (this.times2 + (System.currentTimeMillis() - r9));
        int r04 = this.count2 + 1;
        this.count2 = r04;
        if (r04 < 20) goto L46;
        this.count2 = 0;
        this.times2 = 0;
    L46:
        if (blurQueue != null) goto L48;
        blurQueue = new DispatchQueue("BlurQueue");
    L48:
        this.blurBackgroundTask.radius = (int) (((int) (Math.max(6, Math.max(r3, r2) / 180) * 2.5f)) * BlurSettingsBottomSheet.blurRadius);
        BlurBackgroundTask r05 = this.blurBackgroundTask;
        r05.finalBitmap = r112;
        blurQueue.postRunnable(r05);
        return;
    L41:
        r112.needBlurBottom = false;
    L36:
        e = move-exception;
        FileLog.e(e);
        goto L39
    L30:
        r112.topBitmap.eraseColor(0);
        Bitmap r06 = r112.bottomBitmap;
        if (r06 == null) goto L33;
        r06.eraseColor(0);
        goto L33
    L25:
        r112 = null;
        goto L26
    L59:
        return;
    L56:
        return;
    L16:
        if (ExteraConfig.getForceBlur() == true) goto L18;
    }

    public void updateBlurContent() {
        if (DRAW_USING_RENDERNODE() == false) goto L6;
        invalidateBlurredViews();
        return;
    }

    public void updateColors() {
    }

    public boolean useRootView() {
        return true;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != getBackgroundImage()) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
