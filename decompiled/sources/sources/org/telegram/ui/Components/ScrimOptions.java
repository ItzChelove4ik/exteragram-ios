package org.telegram.ui.Components;

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
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes7.dex */
public class ScrimOptions extends Dialog {
    private Bitmap blurBitmap;
    private Paint blurBitmapPaint;
    private BitmapShader blurBitmapShader;
    private Matrix blurMatrix;
    private final FrameLayout containerView;
    public final Context context;
    public final int currentAccount;
    private boolean dismissing;
    private final BlurredBackgroundDrawableViewFactory iBlur3Factory;
    private final BlurredBackgroundSourceBitmap iBlur3SourceBitmap;
    private boolean isGroup;
    private ValueAnimator openAnimator;
    private float openProgress;
    private ItemOptions options;
    private boolean optionsAtCenter;
    private FrameLayout optionsContainer;
    private View optionsView;
    public final Theme.ResourcesProvider resourcesProvider;
    private ChatMessageCell scrimCell;
    private Drawable scrimDrawable;
    private Drawable scrimDrawableBackground;
    private float scrimDrawableSh;
    private float scrimDrawableSw;
    private float scrimDrawableTx1;
    private float scrimDrawableTx2;
    private float scrimDrawableTy1;
    private float scrimDrawableTy2;
    private final FrameLayout windowView;

    /* renamed from: org.telegram.ui.Components.ScrimOptions$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ScrimOptions this$0;

        public AnonymousClass1(ScrimOptions r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            if (ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L5;
        L7:
            Canvas r2 = r9;
        L8:
            super.dispatchDraw(r2);
            if (ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0) == null) goto L15;
            ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0).setAlpha((int) (ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r2.save();
            r2.translate(ScrimOptions.m12515$$Nest$fgetscrimDrawableTx2(this.this$0) + (ScrimOptions.m12514$$Nest$fgetscrimDrawableTx1(this.this$0) * ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0)), ScrimOptions.m12517$$Nest$fgetscrimDrawableTy2(this.this$0) + (ScrimOptions.m12516$$Nest$fgetscrimDrawableTy1(this.this$0) * ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0)));
            float r92 = AndroidUtilities.lerp(AndroidUtilities.lerp(Math.min(ScrimOptions.m12513$$Nest$fgetscrimDrawableSw(this.this$0), ScrimOptions.m12512$$Nest$fgetscrimDrawableSh(this.this$0)), Math.max(ScrimOptions.m12513$$Nest$fgetscrimDrawableSw(this.this$0), ScrimOptions.m12512$$Nest$fgetscrimDrawableSh(this.this$0)), 0.75f), 1.0f, ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0));
            r2.scale(r92, r92, ((-ScrimOptions.m12515$$Nest$fgetscrimDrawableTx2(this.this$0)) + ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0).getBounds().left) + ((ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0).getBounds().width() / 2.0f) * ScrimOptions.m12513$$Nest$fgetscrimDrawableSw(this.this$0)), ((-ScrimOptions.m12517$$Nest$fgetscrimDrawableTy2(this.this$0)) + ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0).getBounds().top) + ((ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0).getBounds().height() / 2.0f) * ScrimOptions.m12512$$Nest$fgetscrimDrawableSh(this.this$0)));
            if (ScrimOptions.m12511$$Nest$fgetscrimDrawableBackground(this.this$0) == null) goto L13;
            ScrimOptions.m12511$$Nest$fgetscrimDrawableBackground(this.this$0).setAlpha((int) (ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            ScrimOptions.m12511$$Nest$fgetscrimDrawableBackground(this.this$0).draw(r2);
        L13:
            ScrimOptions.m12510$$Nest$fgetscrimDrawable(this.this$0).draw(r2);
            r2.restore();
            return;
        L15:
            return;
        L5:
            if (ScrimOptions.m12504$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L7;
            ScrimOptions.m12506$$Nest$fgetblurMatrix(this.this$0).reset();
            float r0 = getWidth() / ScrimOptions.m12503$$Nest$fgetblurBitmap(this.this$0).getWidth();
            ScrimOptions.m12506$$Nest$fgetblurMatrix(this.this$0).postScale(r0, r0);
            ScrimOptions.m12505$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(ScrimOptions.m12506$$Nest$fgetblurMatrix(this.this$0));
            ScrimOptions.m12504$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r2 = r9;
            r2.drawRect(0.0f, 0.0f, getWidth(), getHeight(), ScrimOptions.m12504$$Nest$fgetblurBitmapPaint(this.this$0));
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

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.this$0.layout();
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            ScrimOptions.m12520$$Nest$mcheckBitmapMatrix(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrimOptions$2, reason: invalid class name */
    public class AnonymousClass2 implements OnApplyWindowInsetsListener {
        final /* synthetic */ ScrimOptions this$0;

        public AnonymousClass2(ScrimOptions r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
            Insets r42 = AndroidUtilities.getDefaultWindowInsets(r5, false);
            ScrimOptions.m12507$$Nest$fgetcontainerView(this.this$0).setPadding(r42.left, r42.top, r42.right, r42.bottom);
            ScrimOptions.m12518$$Nest$fgetwindowView(this.this$0).requestLayout();
            return WindowInsetsCompat.CONSUMED;
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrimOptions$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ScrimOptions this$0;
        final /* synthetic */ Runnable val$after;
        final /* synthetic */ boolean val$open;

        public AnonymousClass3(ScrimOptions r1, boolean r2, Runnable r3) {
            this.this$0 = r1;
            this.val$open = r2;
            this.val$after = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            ScrimOptions r42 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            ScrimOptions.m12519$$Nest$fputopenProgress(r42, r0);
            ScrimOptions.m12509$$Nest$fgetoptionsView(this.this$0).setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0)));
            ScrimOptions.m12509$$Nest$fgetoptionsView(this.this$0).setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0)));
            ScrimOptions.m12509$$Nest$fgetoptionsView(this.this$0).setAlpha(ScrimOptions.m12508$$Nest$fgetopenProgress(this.this$0));
            ScrimOptions.m12518$$Nest$fgetwindowView(this.this$0).invalidate();
            ScrimOptions.m12507$$Nest$fgetcontainerView(this.this$0).invalidate();
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

    /* renamed from: org.telegram.ui.Components.ScrimOptions$4, reason: invalid class name */
    public class AnonymousClass4 extends Drawable {
        private int alpha;
        final /* synthetic */ ScrimOptions this$0;
        final /* synthetic */ Paint val$backgroundPaint;
        final /* synthetic */ Paint val$bitmapPaint;
        final /* synthetic */ ChatMessageCell val$cell;
        final /* synthetic */ Bitmap val$finalBitmap;
        final /* synthetic */ StaticLayout val$finalLayout;
        final /* synthetic */ LinkPath val$path;
        final /* synthetic */ RectF val$pathBounds;
        final /* synthetic */ int[] val$pos;
        final /* synthetic */ int[] val$pos2;

        public AnonymousClass4(ScrimOptions r1, LinkPath r2, int[] r3, ChatMessageCell r4, int[] r5, Bitmap r6, RectF r7, Paint r8, Paint r9, StaticLayout r10) {
            this.this$0 = r1;
            this.val$path = r2;
            this.val$pos2 = r3;
            this.val$cell = r4;
            this.val$pos = r5;
            this.val$finalBitmap = r6;
            this.val$pathBounds = r7;
            this.val$bitmapPaint = r8;
            this.val$backgroundPaint = r9;
            this.val$finalLayout = r10;
            this.alpha = 255;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r6) {
            if (this.alpha > 0) goto L5;
            return;
        L5:
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getBounds());
            r0.left -= LinkPath.getRadius() / 2.0f;
            r6.save();
            r6.saveLayerAlpha(r0, this.alpha, 31);
            int[] r02 = this.val$pos2;
            r6.translate(r02[0], r02[1]);
            ChatMessageCell r03 = this.val$cell;
            if (r03 != null) goto L8;
        L18:
            r6.drawPath(this.val$path, this.val$backgroundPaint);
        L19:
            r6.clipPath(this.val$path);
            this.val$finalLayout.draw(r6);
            r6.restore();
            return;
        L8:
            if (r03.drawBackgroundInParent() == false) goto L18;
            MessageDrawable r04 = this.val$cell.currentBackgroundDrawable;
            if (r04 != null) goto L12;
        L14:
            int[] r05 = this.val$pos2;
            r6.translate(-r05[0], -r05[1]);
            int[] r06 = this.val$pos;
            r6.translate(r06[0], r06[1] + this.val$cell.getPaddingTop());
            this.val$cell.drawBackgroundInternal(r6, true);
            int[] r07 = this.val$pos;
            r6.translate(-r07[0], (-r07[1]) - this.val$cell.getPaddingTop());
            int[] r08 = this.val$pos2;
            r6.translate(r08[0], r08[1]);
        L16:
            if (this.val$finalBitmap == null) goto L19;
            r6.save();
            Bitmap r09 = this.val$finalBitmap;
            RectF r1 = this.val$pathBounds;
            r6.drawBitmap(r09, r1.left, r1.top, this.val$bitmapPaint);
            r6.restore();
            goto L19
        L12:
            if (r04.getPaint() == null) goto L14;
            r6.save();
            this.val$cell.setBackgroundTopY(true);
            r6.translate(0.0f, -this.val$cell.currentBackgroundDrawable.getTopY());
            r6.drawPaint(this.val$cell.currentBackgroundDrawable.getPaint());
            r6.restore();
            goto L16
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: $r8$lambda$-KSK2VnZCT1plBzAnxNOtiUT_Tc, reason: not valid java name */
    public static /* synthetic */ void m12501$r8$lambda$KSK2VnZCT1plBzAnxNOtiUT_Tc(ScrimOptions r0) {
        r0.lambda$dismiss$1();
    }

    /* renamed from: $r8$lambda$5okuEI5-kBVD8thRuOVOGMqmDs4, reason: not valid java name */
    public static /* synthetic */ void m12502$r8$lambda$5okuEI5kBVD8thRuOVOGMqmDs4(ScrimOptions r0) {
        r0.lambda$dismissFast$3();
    }

    public static /* synthetic */ void $r8$lambda$7AwtP5SUIkivLwGZFKBd5UkJZxg(View r6, Utilities.Callback2 r7, Bitmap r8) {
        if (r6.getWidth() > 0) goto L5;
    L16:
        ColorMatrix r62 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L19;
        float r0 = 0.04f;
    L20:
        AndroidUtilities.adjustSaturationColorMatrix(r62, r0);
        float r2 = -0.07f;
        if (Theme.isCurrentThemeDark() == false) goto L23;
        float r02 = -0.04f;
    L24:
        AndroidUtilities.adjustBrightnessColorMatrix(r62, r02);
        Bitmap r63 = AndroidUtilities.applyColorMatrix(r8, r62);
        r63.setHasAlpha(false);
        ColorMatrix r03 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L27;
        float r3 = 2.0f;
    L28:
        r03.setSaturation(r3);
        if (Theme.isCurrentThemeDark() == false) goto L31;
        r2 = -0.2f;
    L31:
        AndroidUtilities.adjustBrightnessColorMatrix(r03, r2);
        Bitmap r04 = AndroidUtilities.applyColorMatrix(r8, r03);
        r04.setHasAlpha(false);
        r8.recycle();
        r7.run(r63, r04);
        return;
    L27:
        r3 = 3.0f;
        goto L28
    L23:
        r02 = -0.07f;
        goto L24
    L19:
        r0 = 0.25f;
        goto L20
    L5:
        if (r6.getHeight() <= 0) goto L16;
        r6.getLocationOnScreen(new int[2]);
        int r22 = Utilities.clamp((int) ((r0[0] / AndroidUtilities.displaySize.x) * r8.getWidth()), r8.getWidth(), 0);
        int r05 = Utilities.clamp((int) ((r0[1] / ((AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight) + AndroidUtilities.navigationBarHeight)) * r8.getHeight()), r8.getHeight(), 0);
        int r32 = Utilities.clamp((int) ((r6.getWidth() / AndroidUtilities.displaySize.x) * r8.getWidth()), r8.getWidth() - r22, 0);
        int r64 = Utilities.clamp((int) ((r6.getHeight() / ((AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight) + AndroidUtilities.navigationBarHeight)) * r8.getHeight()), r8.getHeight() - r05, 0);
        if (r22 != 0) goto L13;
        if (r05 != 0) goto L13;
        if (r32 != r8.getWidth()) goto L13;
        if (r64 == r8.getHeight()) goto L16;
    L13:
        if (r32 <= 0) goto L16;
        if (r64 <= 0) goto L16;
        r8 = Bitmap.createBitmap(r8, r22, r05, r32, r64);
        goto L16
    }

    public static /* synthetic */ void $r8$lambda$GrsoXrDW8EWO3sAuRZ1ZZZicLRY(ScrimOptions r0, View r1, Bitmap r2, Bitmap r3) {
        r0.lambda$prepareBlur$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$dV3nazUis3TjzvHIPVGavKU5zrA(ScrimOptions r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$kNHRGmCyPglcQVZnn_Mrtk9L5fs(ScrimOptions r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$r5tJunqnqO2QT5KvzVGgx5yrRSc(ScrimOptions r0) {
        r0.lambda$dismiss$2();
    }

    public static /* synthetic */ void $r8$lambda$t4hDgimCWIj8tgunowf0q1ImPVw(Utilities.Callback2 r5, Bitmap r6) {
        ColorMatrix r0 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r1 = 0.04f;
    L6:
        AndroidUtilities.adjustSaturationColorMatrix(r0, r1);
        float r2 = -0.07f;
        if (Theme.isCurrentThemeDark() == false) goto L9;
        float r12 = -0.04f;
    L10:
        AndroidUtilities.adjustBrightnessColorMatrix(r0, r12);
        Bitmap r02 = AndroidUtilities.applyColorMatrix(r6, r0);
        r02.setHasAlpha(false);
        ColorMatrix r3 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L13;
        float r4 = 2.0f;
    L14:
        r3.setSaturation(r4);
        if (Theme.isCurrentThemeDark() == false) goto L17;
    L20:
        Bitmap r22 = AndroidUtilities.applyColorMatrix(r6, r3);
        r22.setHasAlpha(false);
        r6.recycle();
        r5.run(r02, r22);
        return;
    L17:
        if (Theme.isCurrentThemeDark() == false) goto L19;
        r2 = -0.2f;
    L19:
        AndroidUtilities.adjustBrightnessColorMatrix(r3, r2);
        goto L20
    L13:
        r4 = 3.0f;
        goto L14
    L9:
        r12 = -0.07f;
        goto L10
    L5:
        r1 = 0.25f;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$yv7wNa6bO98we6eLDG9M5pkfQzE(ScrimOptions r0) {
        r0.lambda$dismissFast$4();
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m12503$$Nest$fgetblurBitmap(ScrimOptions r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12504$$Nest$fgetblurBitmapPaint(ScrimOptions r0) {
        return r0.blurBitmapPaint;
    }

    /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m12505$$Nest$fgetblurBitmapShader(ScrimOptions r0) {
        return r0.blurBitmapShader;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m12506$$Nest$fgetblurMatrix(ScrimOptions r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12507$$Nest$fgetcontainerView(ScrimOptions r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12508$$Nest$fgetopenProgress(ScrimOptions r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetoptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12509$$Nest$fgetoptionsView(ScrimOptions r0) {
        return r0.optionsView;
    }

    /* renamed from: -$$Nest$fgetscrimDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m12510$$Nest$fgetscrimDrawable(ScrimOptions r0) {
        return r0.scrimDrawable;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m12511$$Nest$fgetscrimDrawableBackground(ScrimOptions r0) {
        return r0.scrimDrawableBackground;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableSh, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12512$$Nest$fgetscrimDrawableSh(ScrimOptions r0) {
        return r0.scrimDrawableSh;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableSw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12513$$Nest$fgetscrimDrawableSw(ScrimOptions r0) {
        return r0.scrimDrawableSw;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableTx1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12514$$Nest$fgetscrimDrawableTx1(ScrimOptions r0) {
        return r0.scrimDrawableTx1;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableTx2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12515$$Nest$fgetscrimDrawableTx2(ScrimOptions r0) {
        return r0.scrimDrawableTx2;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableTy1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12516$$Nest$fgetscrimDrawableTy1(ScrimOptions r0) {
        return r0.scrimDrawableTy1;
    }

    /* renamed from: -$$Nest$fgetscrimDrawableTy2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12517$$Nest$fgetscrimDrawableTy2(ScrimOptions r0) {
        return r0.scrimDrawableTy2;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12518$$Nest$fgetwindowView(ScrimOptions r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12519$$Nest$fputopenProgress(ScrimOptions r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$mcheckBitmapMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12520$$Nest$mcheckBitmapMatrix(ScrimOptions r0) {
        r0.checkBitmapMatrix();
    }

    public ScrimOptions(Context r3, Theme.ResourcesProvider r4) {
        super(r3, R.style.TransparentDialog);
        this.currentAccount = UserConfig.selectedAccount;
        this.scrimDrawableSw = 1.0f;
        this.scrimDrawableSh = 1.0f;
        this.dismissing = false;
        this.context = r3;
        this.resourcesProvider = r4;
        AnonymousClass1 r42 = new AnonymousClass1(this, r3);
        this.windowView = r42;
        r42.setOnClickListener(new ScrimOptions$$ExternalSyntheticLambda5(this));
        SizeNotifierFrameLayout r1 = new SizeNotifierFrameLayout(r3);
        this.containerView = r1;
        r1.setClipToPadding(false);
        r42.addView(r1, LayoutHelper.createFrame(-1, -1, 119));
        BlurredBackgroundSourceBitmap r32 = new BlurredBackgroundSourceBitmap();
        this.iBlur3SourceBitmap = r32;
        BlurredBackgroundDrawableViewFactory r0 = new BlurredBackgroundDrawableViewFactory(r32);
        this.iBlur3Factory = r0;
        r0.setSourceRootView(new ViewPositionWatcher(r42), r42);
        ViewCompat.setOnApplyWindowInsetsListener(r42, new AnonymousClass2(this));
    }

    private void animateOpenTo(boolean r4, float r5, Runnable r6) {
        ValueAnimator r52 = this.openAnimator;
        if (r52 == null) goto L5;
        r52.cancel();
    L5:
        float r53 = this.openProgress;
        if (r4 == false) goto L8;
        float r0 = 1.0f;
    L9:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{r53, r0});
        this.openAnimator = r54;
        r54.addUpdateListener(new ScrimOptions$$ExternalSyntheticLambda4(this));
        this.openAnimator.addListener(new AnonymousClass3(this, r4, r6));
        this.openAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.openAnimator.setDuration(350);
        this.openAnimator.start();
        return;
    L8:
        r0 = 0.0f;
        goto L9
    }

    private void animateOpenTo(boolean r2, Runnable r3) {
        animateOpenTo(r2, 1.0f, r3);
    }

    private void checkBitmapMatrix() {
        Blur3Utils.checkBitmapSourceMatrixScale(this.iBlur3SourceBitmap, this.windowView);
        View r2 = this.optionsView;
        if (r2 == null) goto L6;
        r2.invalidate();
        return;
    }

    private /* synthetic */ void lambda$animateOpenTo$5(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.openProgress = r42;
        this.optionsView.setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, r42));
        this.optionsView.setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, this.openProgress));
        this.optionsView.setAlpha(this.openProgress);
        this.windowView.invalidate();
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$dismiss$1() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismiss$2() {
        AndroidUtilities.runOnUIThread(new ScrimOptions$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$dismissFast$3() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismissFast$4() {
        AndroidUtilities.runOnUIThread(new ScrimOptions$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$prepareBlur$6(View r3, Bitmap r4, Bitmap r5) {
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
        this.blurMatrix = new Matrix();
        this.iBlur3SourceBitmap.setBitmap(r5);
        checkBitmapMatrix();
    }

    public static void makeGlobalBlurBitmaps(View r1, Utilities.Callback2<Bitmap, Bitmap> r2) {
        if (r1 != null) goto L5;
        makeGlobalBlurBitmaps(r2);
        return;
    L5:
        AndroidUtilities.makeGlobalBlurBitmap(new ScrimOptions$$ExternalSyntheticLambda8(r1, r2), 15.0f);
    }

    public static void makeGlobalBlurBitmaps(Utilities.Callback2<Bitmap, Bitmap> r1) {
        AndroidUtilities.makeGlobalBlurBitmap(new ScrimOptions$$ExternalSyntheticLambda1(r1), 15.0f);
    }

    private void prepareBlur(View r2) {
        if (r2 == null) goto L4;
        r2.setVisibility(4);
    L4:
        makeGlobalBlurBitmaps(new ScrimOptions$$ExternalSyntheticLambda2(this, r2));
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.dismissing == false) goto L5;
        return;
    L5:
        this.dismissing = true;
        animateOpenTo(false, new ScrimOptions$$ExternalSyntheticLambda3(this));
        this.windowView.invalidate();
    }

    public void dismissFast() {
        if (this.dismissing == false) goto L5;
        return;
    L5:
        this.dismissing = true;
        animateOpenTo(false, 2.0f, new ScrimOptions$$ExternalSyntheticLambda0(this));
        this.windowView.invalidate();
    }

    public FrameLayout getWindowView() {
        return this.windowView;
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        return !this.dismissing;
    }

    public void layout() {
        Drawable r0 = this.scrimDrawable;
        if (r0 == null) goto L28;
        Rect r02 = r0.getBounds();
        if (this.optionsContainer == null) goto L29;
        float r2 = r02.left;
        float r3 = this.scrimDrawableTx2;
        float r22 = r2 + r3;
        float r4 = r02.right + r3;
        float r32 = r02.top;
        float r5 = this.scrimDrawableTy2;
        float r33 = r32 + r5;
        float r03 = r02.bottom + r5;
        boolean r7 = true;
        if (this.optionsAtCenter == true) goto L17;
        float r1 = r4 - r1.getMeasuredWidth();
        float r10 = AndroidUtilities.dp(8.0f);
        View r102 = this.optionsView;
        if (r1 >= r10) goto L11;
        r102.setPivotX(AndroidUtilities.dp(6.0f));
        this.optionsContainer.setX(Math.min(this.containerView.getWidth() - this.optionsContainer.getWidth(), r22 - AndroidUtilities.dp(10.0f)) - this.containerView.getX());
        boolean r12 = false;
    L12:
        FrameLayout r52 = this.optionsContainer;
        if (r12 == false) goto L15;
        float r23 = ((r52.getX() + this.optionsContainer.getWidth()) - AndroidUtilities.dp(6.0f)) - r4;
    L16:
        this.scrimDrawableTx1 = r23;
        this.scrimDrawableTy1 = 0.0f;
    L19:
        if (this.scrimDrawableBackground == null) goto L21;
        int r24 = AndroidUtilities.dp(21.0f);
    L22:
        float r04 = r03 + r24;
        float r25 = this.optionsContainer.getMeasuredHeight() + r04;
        float r42 = this.windowView.getMeasuredHeight() - AndroidUtilities.dp(16.0f);
        View r43 = this.optionsView;
        if (r25 <= r42) goto L25;
        r43.setPivotY(r43.getMeasuredHeight() - AndroidUtilities.dp(6.0f));
        this.optionsContainer.setY(((r33 - AndroidUtilities.dp(4.0f)) - this.optionsContainer.getMeasuredHeight()) - this.containerView.getY());
    L26:
        this.options.setSwipebackGravity(r12, r7);
        return;
    L25:
        r43.setPivotY(AndroidUtilities.dp(6.0f));
        this.optionsContainer.setY(Math.min((this.windowView.getHeight() - this.optionsContainer.getMeasuredHeight()) - AndroidUtilities.dp(16.0f), r04) - this.containerView.getY());
        r7 = false;
        goto L26
    L21:
        r24 = 0;
        goto L22
    L15:
        r23 = (r52.getX() + AndroidUtilities.dp(10.0f)) - r22;
        goto L16
    L11:
        r102.setPivotX(r102.getMeasuredWidth() - AndroidUtilities.dp(6.0f));
        this.optionsContainer.setX(Math.max(AndroidUtilities.dp(8.0f), (AndroidUtilities.dp(4.0f) + r4) - this.optionsContainer.getMeasuredWidth()) - this.containerView.getX());
        r12 = true;
        goto L12
    L17:
        r12 = false;
        goto L19
    L29:
        return;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        Window r42 = getWindow();
        r42.setWindowAnimations(R.style.DialogNoAnimation);
        setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
        WindowManager.LayoutParams r0 = r42.getAttributes();
        r0.width = -1;
        r0.height = -1;
        r0.gravity = 119;
        r0.dimAmount = 0.0f;
        int r1 = r0.flags & (-3);
        r0.softInputMode = 16;
        r0.flags = r1 | (-1945959040);
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r0);
        r42.setAttributes(r0);
        this.windowView.setSystemUiVisibility(256);
        AndroidUtilities.setLightNavigationBar(this.windowView, !Theme.isCurrentThemeDark());
    }

    public void setItemOptions(ItemOptions r5) {
        this.options = r5.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.06f)).setBlurBackground(this.iBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(this.resourcesProvider), false);
        this.optionsView = r5.getLayout();
        FrameLayout r52 = new FrameLayout(this.context);
        this.optionsContainer = r52;
        r52.addView(this.optionsView, LayoutHelper.createFrame(-2, -2.0f));
        this.containerView.addView(this.optionsContainer, LayoutHelper.createFrame(-2, -2.0f));
    }

    public void setOptionsAtCenter() {
        ((FrameLayout.LayoutParams) this.optionsContainer.getLayoutParams()).gravity = 1;
        this.optionsAtCenter = true;
    }

    public void setScrim(ChatMessageCell r2, CharacterStyle r3, CharSequence r4) {
        setScrim(r2, r3, r4, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.text.Layout] */
    /* JADX WARN: Type inference failed for: r12v15, types: [android.graphics.Canvas] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4, types: [android.text.Layout, android.text.StaticLayout] */
    /* JADX WARN: Type inference failed for: r14v8, types: [android.text.StaticLayout] */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.graphics.Path, org.telegram.ui.Components.CornerPath, org.telegram.ui.Components.LinkPath] */
    /* JADX WARN: Type inference failed for: r7v13, types: [android.graphics.Path, org.telegram.ui.Components.LinkPath] */
    public void setScrim(ChatMessageCell r29, CharacterStyle r30, CharSequence r31, boolean r32) {
        if (r29 == null) goto L179;
        this.scrimCell = r29;
        int r12 = 0;
        if (r29.getCurrentMessagesGroup() == null) goto L8;
        boolean r2 = true;
    L9:
        this.isGroup = r2;
        MessageObject r22 = r29.getMessageObject();
        if (r29.getExplanationLayout() != null) goto L12;
    L14:
        boolean r3 = false;
    L15:
        if (r3 == false) goto L19;
        if (r32 == false) goto L19;
        float r6 = r29.getExplanationX();
        float r7 = r29.getExplanationY();
        ArrayList<MessageObject.TextLayoutBlock> r8 = r29.getExplanationLayout().textLayoutBlocks;
        float r9 = r29.getExplanationLayout().textXOffset;
    L22:
        if (r8 != null) goto L25;
        r6 = r29.getTextX();
        r7 = r29.getTextY() + r29.transitionYOffsetForDrawables;
        r8 = r22.textLayoutBlocks;
        r9 = r22.textXOffset;
    L25:
        if (r8 == null) goto L51;
        int r15 = 0;
    L28:
        if (r15 >= r8.size()) goto L51;
        MessageObject.TextLayoutBlock r13 = r8.get(r15);
        StaticLayout r14 = r13.textLayout;
        if (r14 != null) goto L34;
    L31:
        boolean r20 = r3;
    L50:
        r15 = r15 + 1;
        r6 = r6;
        r3 = r20;
        r7 = r7;
        r12 = 0;
        goto L28
    L34:
        if ((r14.getText() instanceof Spanned) == false) goto L31;
        r20 = r3;
        CharacterStyle[] r33 = (CharacterStyle[]) ((Spanned) r14.getText()).getSpans(r12, r14.getText().length(), CharacterStyle.class);
        if (r33 == null) goto L50;
        int r11 = r12;
    L41:
        if (r11 >= r33.length) goto L50;
        if (r33[r11] == r30) goto L44;
        r11 = r11 + 1;
        goto L41
    L44:
        int r34 = ((Spanned) r14.getText()).getSpanStart(r30);
        int r112 = ((Spanned) r14.getText()).getSpanEnd(r30);
        if (r13.isRtl() == false) goto L47;
        float r122 = r6;
        float r21 = r7;
        int r62 = (int) Math.ceil(r9);
    L48:
        float r63 = r62 + r122;
        float r72 = r21 + (r13.padTop + r13.textYOffset(r8, r29.transitionParams));
        int r82 = r13.originalWidth;
        ?? r142 = r14;
    L52:
        if (r142 == 0) goto L54;
    L75:
        if (r142 == 0) goto L77;
    L105:
        if (r142 != 0) goto L112;
        if (r22 == null) goto L112;
        RichMessageLayout r92 = r22.richLayout;
        if (r92 == null) goto L112;
        RichMessageLayout.FoundLink r93 = r92.findLink(r30);
        if (r93 == null) goto L112;
        r142 = r93.layout;
        r34 = r93.start;
        r112 = r93.end;
        r82 = r93.originalWidth;
        r63 = r29.getTextX() + r93.x;
        r72 = r29.getTextY() + r93.y;
    L112:
        int r27 = r82;
        int r83 = r34;
        float r35 = r63;
        int r64 = r112;
        if (r142 != 0) goto L118;
        if (r20 == false) goto L118;
        if (r32 == true) goto L118;
        setScrim(r29, r30, r31, true);
        return;
    L118:
        if (r142 == 0) goto L180;
        if (r31 == null) goto L127;
        int r0 = r142.getLineForOffset(r83);
        float r123 = r72 + r142.getLineTop(r0);
        float r132 = r142.getPrimaryHorizontal(r83);
        float r02 = r142.getLineWidth(r0);
        ?? r73 = new LinkPath(true);
        r73.setCurrentLayout(r142, r83, 0.0f);
        r142.getSelectionPath(r83, r64, r73);
        RectF r152 = new RectF();
        r73.computeBounds(r152, true);
        StaticLayout r143 = MessageObject.makeStaticLayout(r31, r142.getPaint(), r142.getWidth(), 1.0f, 0.0f, false);
        r64 = r31.length();
        float r5 = r143.getWidth();
        float r74 = 0.0f;
        int r84 = 0;
    L123:
        if (r84 >= r143.getLineCount()) goto L125;
        r5 = Math.min(r5, r143.getLineLeft(r84));
        r74 = Math.max(r74, r143.getLineRight(r84));
        r84 = r84 + 1;
        goto L123
    L125:
        r35 = r35 + Math.max(0.0f, Math.min(r132, r02 - Math.max(0.0f, r74 - r5)));
        RectF r202 = r152;
        r83 = 0;
        float r153 = r123;
        StaticLayout r144 = r143;
    L126:
        ?? r03 = r144;
        float r145 = r35;
        Paint r36 = new Paint(1);
        if (r22.isOutOwner() == false) goto L131;
        int r52 = Theme.key_chat_outBubble;
    L132:
        r36.setColor(Theme.getColor(r52, this.resourcesProvider));
        r36.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(5.0f)));
        ?? r53 = new LinkPath(true);
        r53.setUseCornerPathImplementation(true);
        r53.setCurrentLayout(r03, r83, 0.0f);
        r03.getSelectionPath(r83, r64, r53);
        r53.closeRects();
        RectF r75 = new RectF();
        r53.computeBounds(r75, true);
        int r94 = (int) (r75.width() + LinkPath.getRadius());
        if (r29.drawBackgroundInParent() == false) goto L139;
        if (r94 <= 0) goto L139;
        float r17 = 0.0f;
        if (r75.height() <= 0.0f) goto L138;
        Bitmap r10 = Bitmap.createBitmap(r94, (int) r75.height(), Bitmap.Config.ALPHA_8);
        ?? r124 = new Canvas(r10);
        Layout r322 = r03;
        Paint r04 = new Paint(1);
        r04.setColor(-1);
        r124.drawRect(0.0f, 0.0f, r94, r75.height(), r04);
        Paint r95 = new Paint(1);
        r95.setColor(-1);
        r95.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(5.0f)));
        r95.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r124.translate(-r75.left, -r75.top);
        r124.drawPath(r53, r95);
        Bitmap r16 = r10;
    L141:
        Paint r05 = new Paint(3);
        r05.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r29.setupTextColors();
        TextPaint r96 = new TextPaint(r322.getPaint());
        r96.set(r322.getPaint());
        SpannableStringBuilder r1 = new SpannableStringBuilder(AnimatedEmojiSpan.cloneSpans(r322.getText(), -1, r96.getFontMetricsInt()));
        if (r83 <= 0) goto L144;
        int r133 = 0;
        r1.setSpan(new ForegroundColorSpan(0), 0, r83, 33);
    L146:
        if (r64 >= r1.length()) goto L149;
        r1.setSpan(new ForegroundColorSpan(r133), r64, r1.length(), 33);
    L149:
        if (r22.totalAnimatedEmojiCount < 4) goto L151;
        float r125 = -1.0f;
    L152:
        StaticLayout r102 = MessageObject.makeStaticLayout(r1, r96, r27, 1.0f, r125, false);
        int[] r18 = new int[2];
        r29.getLocationOnScreen(r18);
        this.scrimDrawable = new AnonymousClass4(this, r53, new int[]{r18[0] + ((int) r145), r18[1] + ((int) r153)}, r29, r18, r16, r75, r05, r36, r102);
        int r06 = (int) (((r18[0] + r145) + r75.left) + (LinkPath.getRadius() / 2.0f));
        int r23 = (int) ((r18[1] + r153) + r75.top);
        this.scrimDrawable.setBounds(r06, r23, ((int) r75.width()) + r06, ((int) r75.height()) + r23);
        if (r31 == null) goto L177;
        float r07 = r06;
        if ((r75.width() + r07) <= (AndroidUtilities.displaySize.x - AndroidUtilities.dp(8.0f))) goto L157;
        this.scrimDrawableTx2 -= (r07 + r75.width()) - (AndroidUtilities.displaySize.x - AndroidUtilities.dp(8.0f));
    L157:
        float r08 = r23;
        if ((r75.height() + r08) <= (((AndroidUtilities.displaySize.y - AndroidUtilities.statusBarHeight) - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(8.0f))) goto L160;
        this.scrimDrawableTy2 -= (r08 + r75.height()) - (((AndroidUtilities.displaySize.y - AndroidUtilities.statusBarHeight) - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(8.0f));
    L160:
        if (r202 == null) goto L178;
        this.scrimDrawableSw = r202.width() / r75.width();
        this.scrimDrawableSh = r202.height() / r75.height();
        return;
    L178:
        return;
    L177:
        return;
    L151:
        r125 = r17;
        goto L152
    L144:
        r133 = 0;
        goto L146
    L138:
        r322 = r03;
    L140:
        r16 = null;
    L139:
        r322 = r03;
        r17 = 0.0f;
        goto L140
    L131:
        r52 = Theme.key_chat_inBubble;
        goto L132
    L127:
        r153 = r72;
        r202 = null;
        r144 = r142;
        goto L126
    L180:
        return;
    L77:
        if (r22.isTodo() == false) goto L79;
    L80:
        ArrayList<ChatMessageCell.PollButton> r97 = r29.getPollButtons();
        if (r97 == null) goto L105;
        int r126 = 0;
        r142 = r142;
    L84:
        if (r126 >= r97.size()) goto L105;
        ChatMessageCell.PollButton r134 = r97.get(r126);
        StaticLayout r154 = r134.title;
        if (r154 != null) goto L89;
        int r212 = r34;
    L88:
        float r232 = r63;
        float r222 = r72;
    L102:
        r34 = r212;
        r72 = r222;
        r63 = r232;
    L103:
        r126 = r126 + 1;
        r142 = r142;
        goto L84
    L89:
        r212 = r34;
        if ((r154.getText() instanceof Spanned) == false) goto L88;
        r232 = r63;
        r222 = r72;
        CharacterStyle[] r37 = (CharacterStyle[]) ((Spanned) r154.getText()).getSpans(0, r154.getText().length(), CharacterStyle.class);
        if (r37 == null) goto L102;
        int r65 = 0;
    L97:
        if (r65 >= r37.length) goto L102;
        if (r37[r65] == r30) goto L100;
        r65 = r65 + 1;
        goto L97
    L100:
        r34 = ((Spanned) r154.getText()).getSpanStart(r30);
        int r66 = ((Spanned) r154.getText()).getSpanEnd(r30);
        float r76 = r134.titleX;
        r112 = r66;
        r63 = r76;
        r72 = r134.titleY;
        r82 = r154.getWidth();
        r142 = r154;
        goto L103
    L79:
        if (r22.isPoll() == false) goto L105;
    L54:
        if (r29.getDescriptionlayout() == null) goto L75;
        StaticLayout r98 = r29.getDescriptionlayout();
        int r127 = 0;
        r142 = r142;
    L56:
        if (r127 != 0) goto L75;
        if (r98 != null) goto L60;
    L58:
        int r213 = r34;
    L72:
        r34 = r213;
    L73:
        r127 = r127 + 1;
        r142 = r142;
        goto L56
    L60:
        if ((r98.getText() instanceof Spanned) == false) goto L58;
        r213 = r34;
        CharacterStyle[] r135 = (CharacterStyle[]) ((Spanned) r98.getText()).getSpans(0, r98.getText().length(), CharacterStyle.class);
        if (r135 == null) goto L72;
        int r38 = 0;
    L67:
        if (r38 >= r135.length) goto L72;
        if (r135[r38] == r30) goto L70;
        r38 = r38 + 1;
        goto L67
    L70:
        r34 = ((Spanned) r98.getText()).getSpanStart(r30);
        int r67 = ((Spanned) r98.getText()).getSpanEnd(r30);
        float r77 = r29.getDescriptionLayoutX();
        r112 = r67;
        r63 = r77;
        r72 = r29.getDescriptionLayoutY();
        r82 = r98.getWidth();
        r142 = r98;
        goto L73
    L47:
        r122 = r6;
        r21 = r7;
        r62 = 0;
    L51:
        r20 = r3;
        r63 = r6;
        r72 = r7;
        r34 = 0;
        r82 = 0;
        r112 = 0;
        r142 = 0;
    L19:
        if (r29.getCaptionLayout() == null) goto L21;
        r6 = r29.getCaptionX();
        r7 = r29.getCaptionY();
        r8 = r29.getCaptionLayout().textLayoutBlocks;
        r9 = r29.getCaptionLayout().textXOffset;
        goto L22
    L21:
        r6 = 0.0f;
        r7 = 0.0f;
        r8 = null;
        r9 = 0.0f;
        goto L22
    L12:
        if (r22.expandedExplanation == false) goto L14;
        r3 = true;
        goto L15
    L8:
        r2 = false;
        goto L9
    }

    public void setScrimDrawable(Drawable r7, int r8, int r9) {
        BlurredBackgroundDrawable r0 = this.iBlur3Factory.create().setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(this.resourcesProvider)).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true).setRadius(AndroidUtilities.dp(16.0f));
        this.scrimDrawableBackground = r0;
        this.scrimDrawable = r7;
        Point r72 = AndroidUtilities.displaySize;
        int r2 = (r72.x - r8) / 2;
        int r73 = (r72.y - r9) / 2;
        int r82 = r8 + r2;
        int r92 = r9 + r73;
        r0.setBounds(r2 - AndroidUtilities.dp(8.0f), r73 - AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f) + r82, AndroidUtilities.dp(8.0f) + r92);
        this.scrimDrawable.setBounds(r2, r73, r82, r92);
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
