package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline0;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline1;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline2;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline3;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MessagePreviewView;
import org.telegram.ui.Components.Paint.Views.LinkPreview;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.PreviewView;

/* loaded from: classes7.dex */
public class StoryLinkPreviewDialog extends Dialog {
    private final FrameLayout actionBarContainer;
    private final ImageView backgroundView;
    private Bitmap blurBitmap;
    private Paint blurBitmapPaint;
    private BitmapShader blurBitmapShader;
    private Matrix blurMatrix;
    private final MessagePreviewView.ToggleButton captionButton;
    private final LinearLayout containerView;
    private final int currentAccount;
    private boolean dismissing;
    private final Rect insets;
    private LinkPreview.WebPagePreview link;
    private final LinkPreview linkView;
    private ValueAnimator openAnimator;
    private float openProgress;
    private final MessagePreviewView.ToggleButton photoButton;
    private final FrameLayout previewContainer;
    private final FrameLayout previewInnerContainer;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView subtitleTextView;
    private final TextView titleTextView;
    private Utilities.Callback<LinkPreview.WebPagePreview> whenDone;
    private final FrameLayout windowView;

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ StoryLinkPreviewDialog this$0;

        public AnonymousClass1(StoryLinkPreviewDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (StoryLinkPreviewDialog.m11612$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L5;
        L7:
            Canvas r1 = r8;
        L8:
            super.dispatchDraw(r1);
            return;
        L5:
            if (StoryLinkPreviewDialog.m11606$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L7;
            StoryLinkPreviewDialog.m11608$$Nest$fgetblurMatrix(this.this$0).reset();
            float r0 = getWidth() / StoryLinkPreviewDialog.m11605$$Nest$fgetblurBitmap(this.this$0).getWidth();
            StoryLinkPreviewDialog.m11608$$Nest$fgetblurMatrix(this.this$0).postScale(r0, r0);
            StoryLinkPreviewDialog.m11607$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(StoryLinkPreviewDialog.m11608$$Nest$fgetblurMatrix(this.this$0));
            StoryLinkPreviewDialog.m11606$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (StoryLinkPreviewDialog.m11612$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r1 = r8;
            r1.drawRect(0.0f, 0.0f, getWidth(), getHeight(), StoryLinkPreviewDialog.m11606$$Nest$fgetblurBitmapPaint(this.this$0));
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEventPreIme(KeyEvent r3) {
            if (r3 == null) goto L10;
            if (r3.getKeyCode() != 4) goto L10;
            if (r3.getAction() != 1) goto L10;
            this.this$0.onBackPressed();
            return true;
        L10:
            return super.dispatchKeyEventPreIme(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ StoryLinkPreviewDialog this$0;

        public AnonymousClass2(StoryLinkPreviewDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r3), AndroidUtilities.dp(600.0f)), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r4), AndroidUtilities.dp(800.0f)), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        private final Path path;
        private final RectF rect;
        final /* synthetic */ StoryLinkPreviewDialog this$0;

        public AnonymousClass3(StoryLinkPreviewDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            r2.save();
            r2.clipPath(this.path);
            super.draw(r2);
            r2.restore();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            this.path.rewind();
            this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.path.addRoundRect(this.rect, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
            if (StoryLinkPreviewDialog.m11611$$Nest$fgetlinkView(this.this$0) == null) goto L6;
            StoryLinkPreviewDialog.m11611$$Nest$fgetlinkView(this.this$0).setMaxWidth(getMeasuredWidth() - AndroidUtilities.dp(32.0f));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ StoryLinkPreviewDialog this$0;
        private final AnimatedFloat x;
        private final AnimatedFloat y;

        public AnonymousClass4(StoryLinkPreviewDialog r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.x = new AnimatedFloat(this, 0, 350, r6);
            this.y = new AnimatedFloat(this, 0, 350, r6);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != StoryLinkPreviewDialog.m11611$$Nest$fgetlinkView(this.this$0)) goto L7;
            r2.save();
            r2.translate(this.x.set(r3.getX()), this.y.set(r3.getY()));
            StoryLinkPreviewDialog.m11611$$Nest$fgetlinkView(this.this$0).drawInternal(r2);
            r2.restore();
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$5, reason: invalid class name */
    public class AnonymousClass5 extends LinkPreview {
        final /* synthetic */ StoryLinkPreviewDialog this$0;

        public AnonymousClass5(StoryLinkPreviewDialog r1, Context r2, float r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.View
        public void invalidate() {
            StoryLinkPreviewDialog.m11613$$Nest$fgetpreviewInnerContainer(this.this$0).invalidate();
            super.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$6, reason: invalid class name */
    public class AnonymousClass6 implements View.OnApplyWindowInsetsListener {
        final /* synthetic */ StoryLinkPreviewDialog this$0;

        public AnonymousClass6(StoryLinkPreviewDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View r7, WindowInsets r8) {
            int r72 = Build.VERSION.SDK_INT;
            if (r72 < 30) goto L5;
            Insets r1 = r8.getInsets(WindowInsetsCompat.Type.displayCutout() | WindowInsetsCompat.Type.systemBars());
            StoryLinkPreviewDialog.m11610$$Nest$fgetinsets(this.this$0).set(DrawableUtils$$ExternalSyntheticApiModelOutline0.m(r1), DrawableUtils$$ExternalSyntheticApiModelOutline1.m(r1), DrawableUtils$$ExternalSyntheticApiModelOutline2.m(r1), DrawableUtils$$ExternalSyntheticApiModelOutline3.m(r1));
        L6:
            StoryLinkPreviewDialog.m11614$$Nest$fgetwindowView(this.this$0).setPadding(StoryLinkPreviewDialog.m11610$$Nest$fgetinsets(this.this$0).left, StoryLinkPreviewDialog.m11610$$Nest$fgetinsets(this.this$0).top, StoryLinkPreviewDialog.m11610$$Nest$fgetinsets(this.this$0).right, StoryLinkPreviewDialog.m11610$$Nest$fgetinsets(this.this$0).bottom);
            StoryLinkPreviewDialog.m11614$$Nest$fgetwindowView(this.this$0).requestLayout();
            if (r72 < 30) goto L11;
            return WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m();
        L11:
            return r8.consumeSystemWindowInsets();
        L5:
            StoryLinkPreviewDialog.m11610$$Nest$fgetinsets(this.this$0).set(r8.getStableInsetLeft(), r8.getStableInsetTop(), r8.getStableInsetRight(), r8.getStableInsetBottom());
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryLinkPreviewDialog this$0;
        final /* synthetic */ Runnable val$after;
        final /* synthetic */ boolean val$open;

        public AnonymousClass7(StoryLinkPreviewDialog r1, boolean r2, Runnable r3) {
            this.this$0 = r1;
            this.val$open = r2;
            this.val$after = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            StoryLinkPreviewDialog r42 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            StoryLinkPreviewDialog.m11615$$Nest$fputopenProgress(r42, r0);
            StoryLinkPreviewDialog.m11609$$Nest$fgetcontainerView(this.this$0).setAlpha(StoryLinkPreviewDialog.m11612$$Nest$fgetopenProgress(this.this$0));
            StoryLinkPreviewDialog.m11609$$Nest$fgetcontainerView(this.this$0).setScaleX(AndroidUtilities.lerp(0.9f, 1.0f, StoryLinkPreviewDialog.m11612$$Nest$fgetopenProgress(this.this$0)));
            StoryLinkPreviewDialog.m11609$$Nest$fgetcontainerView(this.this$0).setScaleY(AndroidUtilities.lerp(0.9f, 1.0f, StoryLinkPreviewDialog.m11612$$Nest$fgetopenProgress(this.this$0)));
            StoryLinkPreviewDialog.m11614$$Nest$fgetwindowView(this.this$0).invalidate();
            Runnable r3 = this.val$after;
            if (r3 == null) goto L10;
            AndroidUtilities.runOnUIThread(r3);
            return;
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog$8, reason: invalid class name */
    public class AnonymousClass8 extends Drawable {
        final /* synthetic */ StoryLinkPreviewDialog this$0;
        final /* synthetic */ PreviewView val$previewView;

        public AnonymousClass8(StoryLinkPreviewDialog r1, PreviewView r2) {
            this.this$0 = r1;
            this.val$previewView = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.save();
            r3.translate(getBounds().left, getBounds().top);
            this.val$previewView.draw(r3);
            r3.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.val$previewView.getHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.val$previewView.getWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$O8cu2YVWNC2wsrJeFJyrtFMcWVI(StoryLinkPreviewDialog r0, View r1, Bitmap r2) {
        r0.lambda$prepareBlur$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VjlNXW4d42X9hQaOIMf1iSqIsmU(StoryLinkPreviewDialog r0) {
        r0.lambda$dismiss$7();
    }

    /* renamed from: $r8$lambda$YtLWCfJ2ihHUe-NS9EsZGbQFMD4, reason: not valid java name */
    public static /* synthetic */ void m11602$r8$lambda$YtLWCfJ2ihHUeNS9EsZGbQFMD4(StoryLinkPreviewDialog r0, int r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$k2A6lgDdV4lR4BSokQl73_vc30E(StoryLinkPreviewDialog r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$lJWKeGgnE5S-Th5p1JDz11LCvO4, reason: not valid java name */
    public static /* synthetic */ void m11603$r8$lambda$lJWKeGgnE5STh5p1JDz11LCvO4(StoryLinkPreviewDialog r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$lqG8oQEjSvGEsNhtLysgMzPuKic(StoryLinkPreviewDialog r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$4(r1);
    }

    /* renamed from: $r8$lambda$paxJqvRpWwKc3zVVEl70G-ASGVQ, reason: not valid java name */
    public static /* synthetic */ void m11604$r8$lambda$paxJqvRpWwKc3zVVEl70GASGVQ(StoryLinkPreviewDialog r0) {
        r0.lambda$dismiss$6();
    }

    public static /* synthetic */ void $r8$lambda$w7iyGpqFlNovdk8T5csJ8IMxLJs(StoryLinkPreviewDialog r0, int r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m11605$$Nest$fgetblurBitmap(StoryLinkPreviewDialog r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11606$$Nest$fgetblurBitmapPaint(StoryLinkPreviewDialog r0) {
        return r0.blurBitmapPaint;
    }

    /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m11607$$Nest$fgetblurBitmapShader(StoryLinkPreviewDialog r0) {
        return r0.blurBitmapShader;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m11608$$Nest$fgetblurMatrix(StoryLinkPreviewDialog r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m11609$$Nest$fgetcontainerView(StoryLinkPreviewDialog r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m11610$$Nest$fgetinsets(StoryLinkPreviewDialog r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetlinkView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkPreview m11611$$Nest$fgetlinkView(StoryLinkPreviewDialog r0) {
        return r0.linkView;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11612$$Nest$fgetopenProgress(StoryLinkPreviewDialog r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetpreviewInnerContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11613$$Nest$fgetpreviewInnerContainer(StoryLinkPreviewDialog r0) {
        return r0.previewInnerContainer;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11614$$Nest$fgetwindowView(StoryLinkPreviewDialog r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11615$$Nest$fputopenProgress(StoryLinkPreviewDialog r0, float r1) {
        r0.openProgress = r1;
    }

    public StoryLinkPreviewDialog(Context r25, int r26) {
        super(r25, R.style.TransparentDialog);
        DarkThemeResourceProvider r15 = new DarkThemeResourceProvider();
        this.resourcesProvider = r15;
        this.insets = new Rect();
        this.dismissing = false;
        this.currentAccount = r26;
        AnonymousClass1 r3 = new AnonymousClass1(this, r25);
        this.windowView = r3;
        r3.setOnClickListener(new StoryLinkPreviewDialog$$ExternalSyntheticLambda4(this));
        AnonymousClass2 r4 = new AnonymousClass2(this, r25);
        this.containerView = r4;
        r4.setOrientation(1);
        r3.addView(r4, LayoutHelper.createFrame(-2, -2.0f, 17, 8.0f, 8.0f, 8.0f, 8.0f));
        AnonymousClass3 r6 = new AnonymousClass3(this, r25);
        this.previewContainer = r6;
        r6.setWillNotDraw(false);
        r4.addView(r6, LayoutHelper.createLinear(-1, -2, 1.0f, 49, 0, 0, 0, 0));
        FrameLayout r1 = new FrameLayout(r25);
        this.actionBarContainer = r1;
        r1.setBackgroundColor(-14737633);
        r6.addView(r1, LayoutHelper.createFrame(-1, 56, 55));
        TextView r7 = new TextView(r25);
        this.titleTextView = r7;
        r7.setText(LocaleController.getString(R.string.StoryLinkPreviewTitle));
        r7.setTextColor(-1);
        r7.setTextSize(1, 18.0f);
        r7.setTypeface(AndroidUtilities.bold());
        r1.addView(r7, LayoutHelper.createFrame(-1, -2.0f, 55, 18.0f, 8.33f, 18.0f, 0.0f));
        TextView r72 = new TextView(r25);
        this.subtitleTextView = r72;
        r72.setText(LocaleController.getString(R.string.StoryLinkPreviewSubtitle));
        r72.setTextColor(-8421505);
        r72.setTextSize(1, 14.0f);
        r1.addView(r72, LayoutHelper.createFrame(-1, -2.0f, 55, 18.0f, 31.0f, 18.0f, 0.0f));
        AnonymousClass4 r12 = new AnonymousClass4(this, r25);
        this.previewInnerContainer = r12;
        r6.addView(r12, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 56.0f, 0.0f, 0.0f));
        ImageView r62 = new ImageView(r25);
        this.backgroundView = r62;
        r62.setScaleType(ImageView.ScaleType.CENTER_CROP);
        r12.addView(r62, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass5 r63 = new AnonymousClass5(this, r25, AndroidUtilities.density);
        this.linkView = r63;
        r12.addView(r63, LayoutHelper.createFrame(-2, -2, 17));
        ItemOptions r13 = ItemOptions.makeOptions(r3, r15, r3);
        MessagePreviewView.ToggleButton r9 = new MessagePreviewView.ToggleButton(getContext(), R.raw.position_below, LocaleController.getString(R.string.StoryLinkCaptionAbove), R.raw.position_above, LocaleController.getString(R.string.StoryLinkCaptionBelow), r15);
        this.captionButton = r9;
        r9.setOnClickListener(new StoryLinkPreviewDialog$$ExternalSyntheticLambda5(this, r26));
        r13.addView(r9);
        MessagePreviewView.ToggleButton r14 = new MessagePreviewView.ToggleButton(r25, R.raw.media_shrink, LocaleController.getString(R.string.LinkMediaLarger), R.raw.media_enlarge, LocaleController.getString(R.string.LinkMediaSmaller), r15);
        this.photoButton = r14;
        r14.setOnClickListener(new StoryLinkPreviewDialog$$ExternalSyntheticLambda6(this, r26));
        r13.addView(r14);
        r13.addGap();
        r13.add(R.drawable.msg_select, LocaleController.getString(R.string.ApplyChanges), new StoryLinkPreviewDialog$$ExternalSyntheticLambda7(this));
        r13.add(R.drawable.msg_delete, LocaleController.getString(R.string.DoNotLinkPreview), true, new StoryLinkPreviewDialog$$ExternalSyntheticLambda8(this));
        r4.addView(r13.getLayout(), LayoutHelper.createLinear(-2, -2, 0.0f, 85));
        r3.setFitsSystemWindows(true);
        r3.setOnApplyWindowInsetsListener(new AnonymousClass6(this));
    }

    private void animateOpenTo(boolean r5, Runnable r6) {
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r02 = this.openProgress;
        if (r5 == false) goto L8;
        float r1 = 1.0f;
    L9:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.openAnimator = r03;
        r03.addUpdateListener(new StoryLinkPreviewDialog$$ExternalSyntheticLambda3(this));
        this.openAnimator.addListener(new AnonymousClass7(this, r5, r6));
        this.openAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        ValueAnimator r62 = this.openAnimator;
        if (r5 == false) goto L12;
        long r04 = 420;
    L13:
        r62.setDuration(r04);
        this.openAnimator.start();
        return;
    L12:
        r04 = 320;
        goto L13
    L8:
        r1 = 0.0f;
        goto L9
    }

    private /* synthetic */ void lambda$animateOpenTo$4(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.openProgress = r42;
        this.containerView.setAlpha(r42);
        this.containerView.setScaleX(AndroidUtilities.lerp(0.9f, 1.0f, this.openProgress));
        this.containerView.setScaleY(AndroidUtilities.lerp(0.9f, 1.0f, this.openProgress));
        this.windowView.invalidate();
    }

    private /* synthetic */ void lambda$dismiss$6() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismiss$7() {
        AndroidUtilities.runOnUIThread(new StoryLinkPreviewDialog$$ExternalSyntheticLambda0(this));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$new$1(int r3, View r4) {
        LinkPreview.WebPagePreview r42 = this.link;
        boolean r0 = r42.captionAbove;
        r42.captionAbove = !r0;
        this.captionButton.setState(r0, true);
        this.linkView.set(r3, this.link, true);
    }

    private /* synthetic */ void lambda$new$2(int r3, View r4) {
        LinkPreview.WebPagePreview r42 = this.link;
        boolean r0 = r42.largePhoto;
        r42.largePhoto = !r0;
        this.photoButton.setState(r0, true);
        this.linkView.set(r3, this.link, true);
    }

    private /* synthetic */ void lambda$new$3() {
        Utilities.Callback<LinkPreview.WebPagePreview> r0 = this.whenDone;
        if (r0 == null) goto L5;
        r0.run(null);
        this.whenDone = null;
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$prepareBlur$5(View r3, Bitmap r4) {
        if (r3 == null) goto L4;
        r3.setVisibility(0);
    L4:
        this.blurBitmap = r4;
        Paint r32 = new Paint(1);
        this.blurBitmapPaint = r32;
        Bitmap r0 = this.blurBitmap;
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r42 = new BitmapShader(r0, r1, r1);
        this.blurBitmapShader = r42;
        r32.setShader(r42);
        ColorMatrix r33 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L7;
        float r43 = 0.08f;
    L8:
        AndroidUtilities.adjustSaturationColorMatrix(r33, r43);
        if (Theme.isCurrentThemeDark() == false) goto L11;
        float r44 = -0.02f;
    L12:
        AndroidUtilities.adjustBrightnessColorMatrix(r33, r44);
        this.blurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r33));
        this.blurMatrix = new Matrix();
        return;
    L11:
        r44 = -0.07f;
        goto L12
    L7:
        r43 = 0.25f;
        goto L8
    }

    private void prepareBlur(View r2) {
        if (r2 == null) goto L4;
        r2.setVisibility(4);
    L4:
        AndroidUtilities.makeGlobalBlurBitmap(new StoryLinkPreviewDialog$$ExternalSyntheticLambda2(this, r2), 14.0f);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.dismissing == false) goto L5;
        return;
    L5:
        Utilities.Callback<LinkPreview.WebPagePreview> r0 = this.whenDone;
        if (r0 == null) goto L8;
        r0.run(this.link);
        this.whenDone = null;
    L8:
        this.dismissing = true;
        animateOpenTo(false, new StoryLinkPreviewDialog$$ExternalSyntheticLambda1(this));
        this.windowView.invalidate();
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        return !this.dismissing;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r5) {
        super.onCreate(r5);
        Window r52 = getWindow();
        r52.setWindowAnimations(R.style.DialogNoAnimation);
        setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
        WindowManager.LayoutParams r0 = r52.getAttributes();
        r0.width = -1;
        r0.height = -1;
        r0.gravity = 119;
        r0.dimAmount = 0.0f;
        int r1 = r0.flags & (-3);
        r0.softInputMode = 16;
        r0.flags = r1 | (-1945959040);
        if (Build.VERSION.SDK_INT < 28) goto L5;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r0, 1);
    L5:
        r52.setAttributes(r0);
        this.windowView.setSystemUiVisibility(256);
        AndroidUtilities.setLightNavigationBar(this.windowView, !Theme.isCurrentThemeDark());
    }

    public void set(LinkPreview.WebPagePreview r5, Utilities.Callback<LinkPreview.WebPagePreview> r6) {
        this.link = r5;
        if (r5 == null) goto L11;
        TLRPC.WebPage r2 = r5.webpage;
        if (r2 == null) goto L11;
        if (r2.photo == null) goto L9;
    L10:
        boolean r22 = true;
    L12:
        MessagePreviewView.ToggleButton r3 = this.photoButton;
        if (r22 == false) goto L15;
        int r23 = 0;
    L16:
        r3.setVisibility(r23);
        this.linkView.set(this.currentAccount, r5, false);
        this.captionButton.setState(!r5.captionAbove, false);
        this.photoButton.setState(!r5.largePhoto, false);
        this.whenDone = r6;
        return;
    L15:
        r23 = 8;
        goto L16
    L9:
        if (MessageObject.isVideoDocument(r2.document) == true) goto L10;
    L11:
        r22 = false;
        goto L12
    }

    public void setStoryPreviewView(PreviewView r3) {
        this.backgroundView.setImageDrawable(new AnonymousClass8(this, r3));
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L5;
        return;
    L5:
        super.show();
        prepareBlur(null);
        animateOpenTo(true, null);
    }
}
