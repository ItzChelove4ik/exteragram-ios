package org.telegram.ui.web;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.adblock.WebAdBlocker;
import com.exteragram.messenger.adblock.ui.AdBlockMenuItem;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.OKLCH;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.GradientClip;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes6.dex */
public abstract class WebActionBar extends FrameLayout {
    private ValueAnimator addressAnimator;
    public int addressBackgroundColor;
    public final Paint addressBackgroundPaint;
    public final FrameLayout addressContainer;
    public final EditTextBoldCursor addressEditText;
    public final Paint addressRoundPaint;
    public int addressTextColor;
    public boolean addressing;
    public float addressingProgress;
    public final ImageView backButton;
    public final BackDrawable backButtonDrawable;
    public final Drawable backButtonSelector;
    private boolean backButtonShown;
    private int backgroundColor;
    public final Paint[] backgroundPaint;
    public final ImageView clearButton;
    public final Drawable clearButtonSelector;
    public final GradientClip clip;
    private ValueAnimator colorAnimator;
    public boolean[] colorSet;
    public boolean drawShadow;
    public final ImageView forwardButton;
    public final ForwardDrawable forwardButtonDrawable;
    public final Drawable forwardButtonSelector;
    private int fromBackgroundColor;
    public boolean hasForward;
    public boolean hasLoaded;
    public int height;
    public int iconColor;
    public boolean isLocal;
    public boolean isMenuShown;
    public boolean isTonsite;
    public final LinearLayout leftmenu;
    public final LineProgressView lineProgressView;
    public boolean longClicked;
    private Runnable longPressRunnable;
    public int menuBackgroundColor;
    public final ImageView menuButton;
    public final Drawable menuButtonSelector;
    public int menuIconColor;
    private Utilities.Callback<Integer> menuListener;
    public int menuTextColor;
    private int menuType;
    private boolean occupyStatusBar;
    private long pressTime;
    private float pressX;
    private float pressY;
    public final float[] progress;
    public final Paint[] progressBackgroundPaint;
    public final RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    public final LinearLayout rightmenu;
    private int rippleColor;
    public float scale;
    public final Paint scrimPaint;
    private ValueAnimator searchAnimator;
    public final FrameLayout searchContainer;
    public final EditTextBoldCursor searchEditText;
    private int searchEngineIndex;
    public boolean searching;
    public float searchingProgress;
    public final Paint[] shadowPaint;
    public int textColor;
    public final TextPaint titlePaint;
    public float titleProgress;
    public final Title[] titles;
    private Utilities.Callback<String> urlCallback;

    /* renamed from: org.telegram.ui.web.WebActionBar$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        final /* synthetic */ WebActionBar this$0;

        public AnonymousClass1(WebActionBar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotY(0.0f);
            setPivotX(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ WebActionBar this$0;

        public AnonymousClass2(WebActionBar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotY(0.0f);
            setPivotX(getMeasuredWidth());
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$3, reason: invalid class name */
    public class AnonymousClass3 extends ForwardDrawable {
        final /* synthetic */ WebActionBar this$0;

        public AnonymousClass3(WebActionBar r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // org.telegram.ui.web.WebActionBar.ForwardDrawable
        public void setState(boolean r1) {
            super.setState(r1);
            ImageView r0 = this.this$0.forwardButton;
            if (r1 == false) goto L6;
            int r12 = R.string.PollCollapse;
        L7:
            r0.setContentDescription(LocaleController.getString(r12));
            return;
        L6:
            r12 = R.string.Forward;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextBoldCursor {
        final /* synthetic */ WebActionBar this$0;

        public AnonymousClass4(WebActionBar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getAction() != 0) goto L8;
            if (AndroidUtilities.showKeyboard(this) == true) goto L8;
            clearFocus();
            requestFocus();
        L8:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$5, reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ WebActionBar this$0;

        public AnonymousClass5(WebActionBar r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            ImageView r0 = this.this$0.clearButton;
            if (r4.length() > 0) goto L5;
        L7:
            boolean r1 = false;
        L8:
            AndroidUtilities.updateViewShow(r0, r1, true, true);
            this.this$0.onSearchUpdated(r4.toString());
            return;
        L5:
            if (this.this$0.searching == false) goto L7;
            r1 = true;
            goto L8
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$6, reason: invalid class name */
    public class AnonymousClass6 extends EditTextBoldCursor {
        final /* synthetic */ WebActionBar this$0;

        public AnonymousClass6(WebActionBar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getAction() != 0) goto L8;
            if (AndroidUtilities.showKeyboard(this) == true) goto L8;
            clearFocus();
            requestFocus();
        L8:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ WebActionBar this$0;
        final /* synthetic */ int val$backgroundColor;
        final /* synthetic */ float val$toDark;

        public AnonymousClass7(WebActionBar r1, int r2, float r3) {
            this.this$0 = r1;
            this.val$backgroundColor = r2;
            this.val$toDark = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.this$0.setColors(this.val$backgroundColor, this.val$toDark, false);
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ WebActionBar this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass8(WebActionBar r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            WebActionBar r32 = this.this$0;
            if (r32.searching == true) goto L5;
            r32.searchEditText.setVisibility(8);
            this.this$0.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L5:
            WebActionBar r33 = this.this$0;
            EditTextBoldCursor r0 = r33.searchEditText;
            if (this.val$show == false) goto L8;
            float r1 = 1.0f;
        L9:
            r33.searchingProgress = r1;
            r0.setAlpha(r1);
            this.this$0.invalidate();
            WebActionBar r34 = this.this$0;
            if (r34.searching == false) goto L13;
            r34.searchEditText.requestFocus();
            AndroidUtilities.showKeyboard(this.this$0.searchEditText);
            return;
        L13:
            r34.searchEditText.clearFocus();
            AndroidUtilities.hideKeyboard(this.this$0.searchEditText);
            return;
        L8:
            r1 = 0.0f;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.web.WebActionBar$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ WebActionBar this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass9(WebActionBar r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            WebActionBar r32 = this.this$0;
            if (r32.addressing == true) goto L5;
            r32.addressEditText.setVisibility(8);
        L5:
            WebActionBar r33 = this.this$0;
            EditTextBoldCursor r0 = r33.addressEditText;
            if (this.val$show == false) goto L8;
            float r1 = 1.0f;
        L9:
            r33.addressingProgress = r1;
            r0.setAlpha(r1);
            WebActionBar r34 = this.this$0;
            r34.onAddressingProgress(r34.addressingProgress);
            this.this$0.menuButton.setTranslationX(AndroidUtilities.dp(56.0f) * this.this$0.addressingProgress);
            this.this$0.forwardButton.setTranslationX(AndroidUtilities.dp(112.0f) * this.this$0.addressingProgress);
            this.this$0.invalidate();
            return;
        L8:
            r1 = 0.0f;
            goto L9
        }
    }

    public class ForwardDrawable extends Drawable {
        private AnimatedFloat animatedState;
        private final Paint paint;
        private final Path path;
        private boolean state;
        final /* synthetic */ WebActionBar this$0;

        public ForwardDrawable(WebActionBar r9) {
            this.this$0 = r9;
            this.path = new Path();
            Paint r92 = new Paint(1);
            this.paint = r92;
            r92.setStyle(Paint.Style.STROKE);
            r92.setStrokeJoin(Paint.Join.ROUND);
            r92.setStrokeCap(Paint.Cap.ROUND);
            this.animatedState = new AnimatedFloat(new WebActionBar$ForwardDrawable$$ExternalSyntheticLambda0(this), 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            float r0 = this.animatedState.set(!this.state);
            float r1 = getBounds().centerX();
            float r2 = getBounds().centerY();
            float r3 = getBounds().width();
            float r4 = 0.57f * r3;
            this.path.rewind();
            float r7 = r4 / 2.0f;
            this.path.moveTo(r1 - AndroidUtilities.lerp(r7, (-r4) / 2.0f, r0), r2);
            float r72 = r7 + r1;
            this.path.lineTo(r72, r2);
            float r5 = r72 - (0.27f * r3);
            float r8 = (0.54f * r3) / 2.0f;
            this.path.moveTo(r5, r2 - r8);
            this.path.lineTo(r72, r2);
            this.path.lineTo(r5, r8 + r2);
            r11.save();
            this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r11.translate(0.0f, ((-r3) * 0.1f) * r0);
            r11.rotate(r0 * 90.0f, r1, r2);
            r11.drawPath(this.path, this.paint);
            r11.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        public void setColor(int r2) {
            this.paint.setColor(r2);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setState(boolean r1) {
            this.state = r1;
            invalidateSelf();
        }
    }

    public class Title {
        public final AnimatedFloat animatedDangerous;
        public boolean isDangerous;
        public final AnimatedTextView.AnimatedTextDrawable subtitle;
        public int subtitleColor;
        final /* synthetic */ WebActionBar this$0;
        public final AnimatedTextView.AnimatedTextDrawable title;
        public final Drawable warningDrawable;
        public int warningDrawableColor;

        public Title(WebActionBar r11) {
            this.this$0 = r11;
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.title = r0;
            AnimatedTextView.AnimatedTextDrawable r2 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.subtitle = r2;
            this.animatedDangerous = new AnimatedFloat(r11, 0, 300, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.isDangerous = false;
            r0.ignoreRTL = true;
            r0.setTextSize(AndroidUtilities.dp(18.33f));
            r0.setScaleProperty(0.6f);
            r0.setTypeface(AndroidUtilities.bold());
            r0.setEllipsizeByGradient(false);
            r0.setCallback(r11);
            r0.setOverrideFullWidth(9999999);
            r2.ignoreRTL = true;
            r2.setTextSize(AndroidUtilities.dp(14.0f));
            r2.setEllipsizeByGradient(false);
            r2.setCallback(r11);
            r2.setOverrideFullWidth(9999999);
            this.warningDrawable = r11.getContext().getResources().getDrawable(R.drawable.warning_sign).mutate();
        }

        public void draw(Canvas r9, float r10, float r11, float r12) {
            this.this$0.rect.set(0.0f, 0.0f, r10, r11);
            r9.saveLayerAlpha(this.this$0.rect, (int) (r12 * 255.0f), 31);
            float r122 = this.title.isNotEmpty() * this.subtitle.isNotEmpty();
            r9.save();
            float r4 = 0.82f * r11;
            r9.translate(0.0f, (-AndroidUtilities.dp(1.0f)) + ((1.0f - this.this$0.scale) * r4));
            r9.translate(0.0f, (-AndroidUtilities.dp(4.0f)) * r122);
            float r5 = this.this$0.scale * AndroidUtilities.lerp(1.0f, 0.86f, r122);
            r9.scale(r5, r5, 0.0f, 0.0f);
            this.title.setBounds(0.0f, 0.0f, r10, r11);
            this.title.draw(r9);
            r9.restore();
            float r52 = this.animatedDangerous.set(this.isDangerous);
            r9.save();
            r9.translate(0.0f, (((-AndroidUtilities.dp(1.0f)) + ((r4 * (1.0f - this.this$0.scale)) * r122)) + (AndroidUtilities.dp(14.0f) * r122)) - (AndroidUtilities.dp(4.0f) * (1.0f - r122)));
            float r3 = this.this$0.scale * AndroidUtilities.lerp(1.15f, 0.9f, r122);
            r9.scale(r3, r3, 0.0f, 0.0f);
            this.subtitle.setTextColor(ColorUtils.blendARGB(this.subtitleColor, Theme.getColor(Theme.key_text_RedBold), r52));
            if (r52 > 0.0f) goto L5;
        L8:
            this.subtitle.setBounds(AndroidUtilities.dp(20.0f) * r52, 0.0f, r10, r11);
            this.subtitle.draw(r9);
            r9.restore();
            this.this$0.rect.set(r10 - AndroidUtilities.dp(12.0f), 0.0f, r10, r11);
            WebActionBar r8 = this.this$0;
            r8.clip.draw(r9, r8.rect, 2, 1.0f);
            r9.restore();
            return;
        L5:
            if (this.warningDrawableColor == this.subtitle.getTextColor()) goto L7;
            Drawable r123 = this.warningDrawable;
            int r6 = this.subtitle.getTextColor();
            this.warningDrawableColor = r6;
            r123.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.SRC_IN));
        L7:
            this.warningDrawable.setAlpha((int) (255.0f * r52));
            this.warningDrawable.setBounds(0, ((int) (r11 - AndroidUtilities.dp(16.0f))) / 2, AndroidUtilities.dp(16.0f), ((int) (AndroidUtilities.dp(16.0f) + r11)) / 2);
            this.warningDrawable.draw(r9);
            goto L8
        }
    }

    /* renamed from: $r8$lambda$-Igo2HEVp2PBgmsGIpwMhKVxJ9s, reason: not valid java name */
    public static /* synthetic */ void m22053$r8$lambda$Igo2HEVp2PBgmsGIpwMhKVxJ9s(WebActionBar r0, int r1, float r2, float r3, ValueAnimator r4) {
        r0.lambda$setColors$8(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$-Seganux2apFkf9ZBT0cDTx3SFg, reason: not valid java name */
    public static /* synthetic */ void m22054$r8$lambda$Seganux2apFkf9ZBT0cDTx3SFg(WebActionBar r0, Integer r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$-zKzXttVJ6e13AUVkJ7gntuI9R8, reason: not valid java name */
    public static /* synthetic */ void m22055$r8$lambda$zKzXttVJ6e13AUVkJ7gntuI9R8(ActionBarMenuSubItem r0, WebInstantView.Loader r1) {
        if (r1.getWebPage() == null) goto L5;
        boolean r12 = true;
    L6:
        r0.setEnabled(r12);
        ViewPropertyAnimator r13 = r0.animate();
        if (r0.isEnabled() == false) goto L9;
        float r02 = 1.0f;
    L10:
        r13.alpha(r02);
        return;
    L9:
        r02 = 0.5f;
        goto L10
    L5:
        r12 = false;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$8hq5X8lNwZJy_Kxsw9wcZB_VEI8(WebActionBar r0, View r1) {
        r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$8tKySbHMzCCKexwKSCYkgCJcCaI(WebActionBar r0) {
        r0.lambda$new$11();
    }

    /* renamed from: $r8$lambda$D-VwzxNhM05WpxP10VlKw6Vt6-c, reason: not valid java name */
    public static /* synthetic */ void m22056$r8$lambda$DVwzxNhM05WpxP10VlKw6Vt6c(WebActionBar r0) {
        r0.showAddressKeyboard();
    }

    public static /* synthetic */ boolean $r8$lambda$MMTLL_ZZIcmyDc1LaP8ajaFzFY8(WebActionBar r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$6(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Na6YU72g-mcRrG1aklgnVGeR-B0, reason: not valid java name */
    public static /* synthetic */ void m22057$r8$lambda$Na6YU72gmcRrG1aklgnVGeRB0(WebActionBar r0, ValueAnimator r1) {
        r0.lambda$showSearch$9(r1);
    }

    /* renamed from: $r8$lambda$UPssCKKae4rkVsIb-Dr9o2VmqDM, reason: not valid java name */
    public static /* synthetic */ void m22058$r8$lambda$UPssCKKae4rkVsIbDr9o2VmqDM(WebActionBar r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ Runnable $r8$lambda$egHQmh17gSsJK_U_MMDQErTp7rE(WebActionBar r0, Integer r1) {
        return r0.lambda$new$1(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$hub12xKw5x2gbyEoQB0eJ8JbG_w(WebActionBar r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jIDGYoiPinq7eFfgceowD24h6U8(WebActionBar r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$jthgz7M4zNnEmbF0ckuKm7eUMag(WebActionBar r0, ValueAnimator r1) {
        r0.lambda$showAddress$10(r1);
    }

    public WebActionBar(Context r27, Theme.ResourcesProvider r28) {
        super(r27);
        this.rect = new RectF();
        this.titles = new Title[2];
        this.titleProgress = 0.0f;
        this.progress = new float[2];
        this.colorSet = new boolean[3];
        this.backgroundPaint = new Paint[2];
        this.progressBackgroundPaint = new Paint[2];
        this.shadowPaint = new Paint[2];
        this.scrimPaint = new Paint(1);
        this.addressBackgroundPaint = new Paint(1);
        this.addressRoundPaint = new Paint(1);
        TextPaint r5 = new TextPaint(1);
        this.titlePaint = r5;
        this.isMenuShown = false;
        this.height = AndroidUtilities.dp(56.0f);
        this.scale = 1.0f;
        this.searchingProgress = 0.0f;
        this.addressingProgress = 0.0f;
        this.menuType = -1;
        this.clip = new GradientClip();
        this.longPressRunnable = new WebActionBar$$ExternalSyntheticLambda2(this);
        this.longClicked = false;
        this.resourcesProvider = r28;
        r5.setTypeface(AndroidUtilities.bold());
        r5.setTextSize(AndroidUtilities.dp(18.33f));
        int r52 = 0;
    L3:
        if (r52 >= 2) goto L5;
        this.backgroundPaint[r52] = new Paint(1);
        this.progressBackgroundPaint[r52] = new Paint(1);
        this.shadowPaint[r52] = new Paint(1);
        r52 = r52 + 1;
        goto L3
    L5:
        FrameLayout r53 = new FrameLayout(r27);
        this.searchContainer = r53;
        addView(r53, LayoutHelper.createFrame(-1, 56, 87));
        FrameLayout r12 = new FrameLayout(r27);
        this.addressContainer = r12;
        addView(r12, LayoutHelper.createFrame(-1, 56, 87));
        AnonymousClass1 r13 = new AnonymousClass1(this, r27);
        this.leftmenu = r13;
        r13.setOrientation(0);
        addView(r13, LayoutHelper.createFrame(-2, 56, 83));
        ImageView r14 = new ImageView(r27);
        this.backButton = r14;
        r14.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
        ImageView.ScaleType r11 = ImageView.ScaleType.CENTER;
        r14.setScaleType(r11);
        BackDrawable r3 = new BackDrawable(false);
        this.backButtonDrawable = r3;
        r3.setAnimationTime(200.0f);
        r3.setRotation(1.0f, false);
        r14.setImageDrawable(r3);
        Drawable r8 = Theme.createSelectorDrawable(1090519039);
        this.backButtonSelector = r8;
        r14.setBackground(r8);
        r13.addView(r14, LayoutHelper.createLinear(54, 56));
        AnonymousClass2 r9 = new AnonymousClass2(this, r27);
        this.rightmenu = r9;
        r9.setOrientation(0);
        addView(r9, LayoutHelper.createFrame(-2, 56, 85));
        ImageView r142 = new ImageView(r27);
        this.forwardButton = r142;
        r142.setScaleType(r11);
        AnonymousClass3 r15 = new AnonymousClass3(this);
        this.forwardButtonDrawable = r15;
        r142.setImageDrawable(r15);
        r15.setState(false);
        Drawable r152 = Theme.createSelectorDrawable(1090519039);
        this.forwardButtonSelector = r152;
        r142.setBackground(r152);
        r9.addView(r142, LayoutHelper.createLinear(54, 56));
        ImageView r143 = new ImageView(r27);
        this.menuButton = r143;
        r143.setScaleType(r11);
        r143.setImageResource(R.drawable.ic_ab_other);
        r143.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_IN));
        r143.setOnClickListener(new WebActionBar$$ExternalSyntheticLambda3(this, r28));
        Drawable r32 = Theme.createSelectorDrawable(1090519039);
        this.menuButtonSelector = r32;
        r143.setBackground(r32);
        r143.setContentDescription(LocaleController.getString("AccDescrMoreOptions", R.string.AccDescrMoreOptions));
        r9.addView(r143, LayoutHelper.createLinear(54, 56));
        AnonymousClass4 r33 = new AnonymousClass4(this, r27);
        this.searchEditText = r33;
        r33.setVisibility(8);
        r33.setAlpha(0.0f);
        r33.setTextSize(1, 18.0f);
        r33.setSingleLine(true);
        r33.setHint(LocaleController.getString(R.string.Search));
        r33.setBackgroundResource(0);
        r33.setCursorWidth(1.5f);
        r33.setGravity(112);
        r33.setClipToPadding(true);
        r33.setPadding(AndroidUtilities.dp(58.0f), 0, AndroidUtilities.dp(112.0f), 0);
        r33.setTranslationY(-AndroidUtilities.dp(0.66f));
        r33.setInputType(r33.getInputType() | 524288);
        r33.setImeOptions(33554435);
        r33.setTextIsSelectable(false);
        r33.setOnEditorActionListener(new WebActionBar$$ExternalSyntheticLambda4(this));
        r33.addTextChangedListener(new AnonymousClass5(this));
        r53.addView(r33, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass6 r34 = new AnonymousClass6(this, r27);
        this.addressEditText = r34;
        r34.setVisibility(8);
        r34.setAlpha(0.0f);
        r34.setTextSize(1, 15.66f);
        r34.setSingleLine(true);
        this.searchEngineIndex = SharedConfig.searchEngineType;
        r34.setHint(LocaleController.formatString(R.string.AddressPlaceholder, new Object[]{SearchEngine.getCurrent().name}));
        r34.setBackgroundResource(0);
        r34.setCursorWidth(1.5f);
        r34.setGravity(112);
        r34.setInputType(r34.getInputType() | 524304);
        r34.setImeOptions(33554434);
        r34.setTextIsSelectable(false);
        r34.setOnEditorActionListener(new WebActionBar$$ExternalSyntheticLambda5(this));
        r12.addView(r34, LayoutHelper.createFrame(-1, -1.0f, 119, 48.0f, 0.0f, 12.0f, 0.0f));
        ImageView r35 = new ImageView(r27);
        this.clearButton = r35;
        r35.setScaleType(r11);
        r35.setImageResource(R.drawable.ic_close_white);
        Drawable r54 = Theme.createSelectorDrawable(1090519039);
        this.clearButtonSelector = r54;
        r35.setBackground(r54);
        r35.setVisibility(8);
        r35.setAlpha(0.0f);
        r35.setOnClickListener(new WebActionBar$$ExternalSyntheticLambda6(this));
        addView(r35, LayoutHelper.createFrame(54, 56, 85));
        LineProgressView r36 = new LineProgressView(r27);
        this.lineProgressView = r36;
        r36.setHideAfterMaxProgress(true);
        r36.setShowAnimationBehavior(1);
        r36.setHideAnimationBehavior(2);
        r36.setPivotX(0.0f);
        r36.setPivotY(AndroidUtilities.dp(2.0f));
        r36.setVisibility(4);
        addView(r36, LayoutHelper.createFrame(-1, 2, 87));
        setWillNotDraw(false);
        this.titles[0] = new Title(this);
        this.titles[1] = new Title(this);
        int r1 = Theme.key_iv_background;
        setColors(Theme.getColor(r1, r28), false);
        setMenuColors(Theme.getColor(r1, r28));
    }

    private /* synthetic */ void lambda$new$0(Integer r1) {
        this.menuListener.run(r1);
    }

    private /* synthetic */ Runnable lambda$new$1(Integer r2) {
        return new WebActionBar$$ExternalSyntheticLambda12(this, r2);
    }

    private /* synthetic */ void lambda$new$11() {
        this.longClicked = true;
        if (getParent() == null) goto L9;
        getParent().requestDisallowInterceptTouchEvent(true);
    L9:
        performHapticFeedback(0, 1);     // Catch: Exception -> L8
        return;
    }

    private /* synthetic */ void lambda$new$3() {
        this.isMenuShown = false;
    }

    private /* synthetic */ void lambda$new$4(Theme.ResourcesProvider r14, View r15) {
        if ((getParent() instanceof ViewGroup) == true) goto L5;
        return;
    L5:
        WebActionBar$$ExternalSyntheticLambda9 r4 = new WebActionBar$$ExternalSyntheticLambda9(this);
        ItemOptions r5 = ItemOptions.makeOptions((ViewGroup) getParent(), this.menuButton);
        boolean r6 = false;
        r5.setDimAlpha(0);
        boolean r7 = Theme.isCurrentThemeMonet();
        if (r7 == false) goto L8;
    L10:
        boolean r8 = false;
    L11:
        int r9 = -1;
        if (r7 == false) goto L14;
        int r10 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r14);
    L17:
        if (r7 == false) goto L19;
        r9 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r14);
    L22:
        if (r7 == false) goto L24;
        int r11 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r14);
    L25:
        r5.setColors(r9, r11);
        r5.translate(0.0f, -AndroidUtilities.dp(52.0f));
        r5.setMinWidth(200);
        if (r7 == false) goto L28;
        int r92 = Theme.getColor(Theme.key_listSelector, r14);
    L29:
        r5.setSelectorColor(r92);
        r5.setBackgroundColor(r10);
        if (r7 == false) goto L32;
        int r142 = Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator, r14);
    L35:
        r5.setGapBackgroundColor(r142);
        int r143 = this.menuType;
        if (r143 != 0) goto L38;
        r5.add(R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp), (Runnable) r4.run(3));
        r5.add(R.drawable.msg_search, LocaleController.getString(R.string.Search), (Runnable) r4.run(1));
        r5.addIf(!this.isLocal, R.drawable.msg_share, LocaleController.getString(R.string.ShareFile), (Runnable) r4.run(2));
        r5.add(R.drawable.msg_settings_old, LocaleController.getString(R.string.Settings), (Runnable) r4.run(4));
    L63:
        r5.setOnDismiss(new WebActionBar$$ExternalSyntheticLambda11(this));
        r5.show();
        this.isMenuShown = true;
        return;
    L38:
        if (r143 != 1) goto L63;
        if (this.isTonsite == true) goto L43;
        AdBlockMenuItem.addTo(r5, getAdBlocker());
        r5.add(R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp), (Runnable) r4.run(3));
        r5.addGap();
    L43:
        if (this.hasForward == false) goto L45;
        r5.add(R.drawable.msg_arrow_forward, LocaleController.getString(R.string.WebForward), (Runnable) r4.run(9));
    L45:
        WebInstantView.Loader r144 = getInstantViewLoader();
        if (r144 != null) goto L48;
    L59:
        r5.add(R.drawable.msg_reset, LocaleController.getString(R.string.Refresh), (Runnable) r4.run(5));
        r5.add(R.drawable.msg_search, LocaleController.getString(R.string.Search), (Runnable) r4.run(1));
        r5.add(R.drawable.msg_saved, LocaleController.getString(R.string.WebBookmark), (Runnable) r4.run(6));
        r5.add(R.drawable.msg_share, LocaleController.getString(R.string.ShareFile), (Runnable) r4.run(2));
        r5.addGap();
        if (BrowserHistory.getHistory().isEmpty() == true) goto L62;
        r5.add(R.drawable.menu_views_recent, LocaleController.getString(R.string.WebHistory), (Runnable) r4.run(8));
    L62:
        r5.add(R.drawable.menu_browser_bookmarks, LocaleController.getString(R.string.WebBookmarks), (Runnable) r4.run(7));
        r5.add(R.drawable.msg_settings_old, LocaleController.getString(R.string.Settings), (Runnable) r4.run(4));
        goto L63
    L48:
        if (r144.isDone() == true) goto L50;
    L51:
        r5.add(R.drawable.menu_instant_view, LocaleController.getString(R.string.OpenLocalInstantView), (Runnable) r4.run(10));
        ActionBarMenuSubItem r1 = r5.getLast();
        if (r144.getWebPage() == null) goto L54;
        r6 = true;
    L54:
        r1.setEnabled(r6);
        if (r1.isEnabled() == false) goto L57;
        float r62 = 1.0f;
    L58:
        r1.setAlpha(r62);
        r5.setOnDismiss(r144.listen(new WebActionBar$$ExternalSyntheticLambda10(r1, r144)));
        goto L59
    L57:
        r62 = 0.5f;
        goto L58
    L50:
        if (r144.getWebPage() == null) goto L59;
    L32:
        if (r8 == false) goto L34;
        r142 = -15592942;
        goto L35
    L34:
        r142 = -986896;
        goto L35
    L28:
        r92 = Theme.blendOver(r10, Theme.multAlpha(r9, 0.1f));
        goto L29
    L24:
        r11 = Theme.multAlpha(r9, 0.6f);
        goto L25
    L19:
        if (r8 == true) goto L22;
        r9 = -16777216;
        goto L22
    L14:
        if (r8 == false) goto L16;
        r10 = -14737633;
        goto L17
    L16:
        r10 = -1;
        goto L17
    L8:
        if (Theme.isCurrentThemeDark() == false) goto L10;
        r8 = true;
        goto L11
    }

    private /* synthetic */ boolean lambda$new$5(TextView r1, int r2, KeyEvent r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.getAction() != 1) goto L8;
        if (r3.getKeyCode() != 84) goto L8;
    L11:
        AndroidUtilities.hideKeyboard(this.searchEditText);
        return false;
    L8:
        if (r3.getAction() == 0) goto L10;
        return false;
    L10:
        if (r3.getKeyCode() == 66) goto L11;
        return false;
    }

    private /* synthetic */ boolean lambda$new$6(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 2) goto L8;
        Utilities.Callback<String> r12 = this.urlCallback;
        if (r12 == null) goto L7;
        r12.run(this.addressEditText.getText().toString());
    L7:
        showAddress(false, true);
    L8:
        return false;
    }

    private /* synthetic */ void lambda$new$7(View r1) {
        this.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private /* synthetic */ void lambda$setColors$8(int r2, float r3, float r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        setColors(ColorUtils.blendARGB(this.fromBackgroundColor, r2, r52), AndroidUtilities.lerp(r3, r4, r52), false);
    }

    private /* synthetic */ void lambda$showAddress$10(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.addressingProgress = r32;
        onAddressingProgress(r32);
        this.addressEditText.setAlpha(this.addressingProgress);
        this.menuButton.setTranslationX(AndroidUtilities.dp(56.0f) * this.addressingProgress);
        this.forwardButton.setTranslationX(AndroidUtilities.dp(112.0f) * this.addressingProgress);
        invalidate();
    }

    private /* synthetic */ void lambda$showSearch$9(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.searchingProgress = r22;
        this.searchEditText.setAlpha(r22);
        invalidate();
    }

    private void showAddressKeyboard() {
        boolean r0 = this.addressing;
        EditTextBoldCursor r1 = this.addressEditText;
        if (r0 == false) goto L6;
        r1.requestFocus();
        AndroidUtilities.showKeyboard(this.addressEditText);
        return;
    L6:
        r1.clearFocus();
        AndroidUtilities.hideKeyboard(this.addressEditText);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r13) {
        drawBackground(r13, topPadding() + this.height, 1.0f, 1.0f, this.drawShadow);
        float r12 = this.leftmenu.getRight();
        float r132 = this.rightmenu.getLeft();
        float r0 = topPadding();
        float r8 = topPadding() + this.height;
        if (this.titleProgress >= 1.0f) goto L6;
        r13.save();
        float r3 = (getWidth() * this.titleProgress) - (AndroidUtilities.dp(30.0f) * Utilities.clamp01(this.titleProgress * 2.0f));
        r13.translate(r12 + r3, r0);
        AndroidUtilities.lerp(1.0f, 0.5f, this.titleProgress);
        this.titles[0].draw(r13, (r132 - r12) - r3, r8 - r0, (1.0f - this.titleProgress) * (1.0f - this.searchingProgress));
        r13.restore();
    L6:
        if (this.titleProgress <= 0.0f) goto L9;
        float r32 = getWidth() * this.titleProgress;
        r13.save();
        r13.clipRect(0.0f, 0.0f, r32, getHeight());
        r13.translate(r12, r0);
        r13.translate(AndroidUtilities.dp(-12.0f) * (1.0f - this.titleProgress), 0.0f);
        float r33 = AndroidUtilities.lerp(1.0f, 0.5f, 1.0f - this.titleProgress);
        float r5 = r8 - r0;
        r13.scale(r33, r33, 0.0f, r5 / 2.0f);
        this.titles[1].draw(r13, r132 - r12, r5, (this.titleProgress * (1.0f - this.searchingProgress)) * (1.0f - this.addressingProgress));
        r13.restore();
    L9:
        if (this.addressingProgress <= 0.0f) goto L11;
        int r122 = this.addressBackgroundPaint.getAlpha();
        this.addressBackgroundPaint.setAlpha((int) (r122 * this.addressingProgress));
        r13.drawRect(0.0f, 0.0f, getWidth(), topPadding() + this.height, this.addressBackgroundPaint);
        this.addressBackgroundPaint.setAlpha(r122);
        float r34 = (r0 + r8) / 2.0f;
        float r123 = AndroidUtilities.dp(42.0f) / 2.0f;
        this.rect.set(AndroidUtilities.dp(6.0f), r34 - r123, AndroidUtilities.lerp(r132, getWidth() - AndroidUtilities.dp(6.0f), this.addressingProgress), r34 + r123);
        int r124 = this.addressRoundPaint.getAlpha();
        this.addressRoundPaint.setAlpha((int) (r124 * this.addressingProgress));
        r13.drawRoundRect(this.rect, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), this.addressRoundPaint);
        this.addressRoundPaint.setAlpha(r124);
    L11:
        this.rect.set(0.0f, r0, getWidth(), r8);
        r13.save();
        r13.clipRect(this.rect);
        super.dispatchDraw(r13);
        r13.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r9) {
        if (r9.getAction() != 0) goto L14;
        this.longClicked = false;
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        if (r9.getX() > this.leftmenu.getRight()) goto L7;
    L23:
        this.pressX = r9.getX();
        return super.dispatchTouchEvent(r9);
    L7:
        if (r9.getX() >= this.rightmenu.getLeft()) goto L23;
        if (isSearching() == true) goto L23;
        if (isAddressing() == true) goto L23;
        this.pressX = r9.getX();
        this.pressY = r9.getY();
        this.pressTime = System.currentTimeMillis();
        AndroidUtilities.runOnUIThread(this.longPressRunnable, (long) (ViewConfiguration.getLongPressTimeout() * 0.8f));
        goto L23
    L14:
        if (r9.getAction() != 2) goto L19;
        if ((System.currentTimeMillis() - this.pressTime) <= (ViewConfiguration.getLongPressTimeout() * 0.8f)) goto L19;
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        this.longClicked = true;
        onScrolledProgress((r9.getX() - this.pressX) / (getWidth() * 0.8f));
        getParent().requestDisallowInterceptTouchEvent(true);
    L19:
        if (r9.getAction() != 1) goto L21;
    L22:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        this.pressTime = 0;
        goto L23
    L21:
        if (r9.getAction() != 3) goto L23;
        goto L22
    }

    public void drawBackground(Canvas r11, float r12, float r13, float r14, boolean r15) {
        float r0 = Math.max(AndroidUtilities.dp(0.66f), 1);
        float r2 = r12 - r0;
        float r3 = getWidth() * this.titleProgress;
        this.rect.set(0.0f, 0.0f, getWidth(), r12);
        int r4 = this.backgroundPaint[1].getAlpha();
        this.backgroundPaint[1].setAlpha((int) (r4 * r13));
        r11.drawRect(this.rect, this.backgroundPaint[1]);
        this.backgroundPaint[1].setAlpha(r4);
        if (this.titleProgress <= 0.0f) goto L7;
        this.rect.set(0.0f, 0.0f, this.progress[1] * getWidth(), r12);
        int r42 = this.progressBackgroundPaint[1].getAlpha();
        this.progressBackgroundPaint[1].setAlpha((int) (((r42 * r13) * (1.0f - this.searchingProgress)) * (1.0f - this.addressingProgress)));
        r11.drawRect(this.rect, this.progressBackgroundPaint[1]);
        this.progressBackgroundPaint[1].setAlpha(r42);
        if (r15 == false) goto L7;
        this.rect.set(0.0f, r2, r3, r2 + r0);
        int r43 = this.shadowPaint[1].getAlpha();
        this.shadowPaint[1].setAlpha((int) (((r43 * r13) * r14) * (1.0f - this.addressingProgress)));
        r11.drawRect(this.rect, this.shadowPaint[1]);
        this.shadowPaint[1].setAlpha(r43);
    L7:
        float r1 = this.titleProgress;
        if (r1 >= 1.0f) goto L10;
        this.scrimPaint.setColor(Theme.multAlpha(1610612736, (1.0f - r1) * r13));
        this.rect.set(0.0f, 0.0f, r3, r12);
        r11.drawRect(this.rect, this.scrimPaint);
        this.rect.set(r3, 0.0f, getWidth(), r12);
        int r16 = this.backgroundPaint[0].getAlpha();
        this.backgroundPaint[0].setAlpha((int) (r16 * r13));
        r11.drawRect(this.rect, this.backgroundPaint[0]);
        this.backgroundPaint[0].setAlpha(r16);
    L10:
        this.rect.set(r3, 0.0f, (this.progress[0] * getWidth()) + r3, r12);
        int r122 = this.progressBackgroundPaint[0].getAlpha();
        this.progressBackgroundPaint[0].setAlpha((int) (((((1.0f - Utilities.clamp01(this.titleProgress * 4.0f)) * r122) * r13) * (1.0f - this.searchingProgress)) * (1.0f - this.addressingProgress)));
        r11.drawRect(this.rect, this.progressBackgroundPaint[0]);
        this.progressBackgroundPaint[0].setAlpha(r122);
        if (r15 == false) goto L14;
        this.rect.set(r3, r2, getWidth() + r3, r0 + r2);
        int r123 = this.shadowPaint[0].getAlpha();
        this.shadowPaint[0].setAlpha((int) (((r123 * r13) * r14) * (1.0f - this.addressingProgress)));
        r11.drawRect(this.rect, this.shadowPaint[0]);
        this.shadowPaint[0].setAlpha(r123);
        return;
    }

    public WebAdBlocker getAdBlocker() {
        return null;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getBackgroundColor(int r1) {
        return this.backgroundPaint[r1].getColor();
    }

    public WebInstantView.Loader getInstantViewLoader() {
        return null;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public String getTitle() {
        CharSequence r1 = this.titles[0].title.getText();
        if (r1 != null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r1.toString();
    }

    public boolean isAddressing() {
        return this.addressing;
    }

    public boolean isSearching() {
        return this.searching;
    }

    public void occupyStatusBar(boolean r1) {
        this.occupyStatusBar = r1;
    }

    public abstract void onAddressColorsChanged(int r1, int r2);

    public void onAddressingProgress(float r4) {
        this.backButtonDrawable.setColor(ColorUtils.blendARGB(this.textColor, this.addressTextColor, this.addressingProgress));
        this.backButtonDrawable.setRotatedColor(ColorUtils.blendARGB(this.textColor, this.addressTextColor, this.addressingProgress));
        this.backButton.invalidate();
    }

    public abstract void onColorsUpdated();

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(topPadding() + AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }

    public abstract void onScrolledProgress(float r1);

    public abstract void onSearchUpdated(String r1);

    public void setBackButton(boolean r2) {
        this.backButtonShown = r2;
        if (isSearching() == false) goto L5;
        return;
    L5:
        if (isAddressing() == true) goto L13;
        BackDrawable r22 = this.backButtonDrawable;
        if (this.backButtonShown == false) goto L9;
        float r1 = 0.0f;
    L10:
        r22.setRotation(r1, true);
        return;
    L9:
        r1 = 1.0f;
        goto L10
    }

    public void setBackButtonCached(boolean r1) {
        this.backButtonShown = r1;
    }

    public void setBackgroundColor(int r6, int r7) {
        if (this.colorSet[r6] == true) goto L5;
    L7:
        this.colorSet[r6] = true;
        this.backgroundPaint[r6].setColor(r7);
        if (AndroidUtilities.computePerceivedBrightness(r7) > 0.721f) goto L10;
        float r0 = 1.0f;
    L11:
        int r1 = ColorUtils.blendARGB(-16777216, -1, r0);
        this.progressBackgroundPaint[r6].setColor(Theme.blendOver(r7, Theme.multAlpha(r1, AndroidUtilities.lerp(0.05f, 0.1f, r0))));
        this.shadowPaint[r6].setColor(Theme.blendOver(r7, Theme.multAlpha(r1, AndroidUtilities.lerp(0.14f, 0.24f, r0))));
        this.titles[r6].title.setTextColor(r1);
        Title r02 = this.titles[r6];
        r02.subtitleColor = Theme.blendOver(r7, Theme.multAlpha(r1, 0.6f));
        Title r72 = this.titles[r6];
        r72.subtitle.setTextColor(ColorUtils.blendARGB(r72.subtitleColor, Theme.getColor(Theme.key_text_RedBold), this.titles[r6].animatedDangerous.get()));
        invalidate();
        return;
    L10:
        r0 = 0.0f;
        goto L11
    L5:
        if (this.backgroundPaint[r6].getColor() != r7) goto L7;
    }

    public void setColors(int r6, float r7, boolean r8) {
        boolean[] r0 = this.colorSet;
        if (r0[2] == true) goto L5;
    L7:
        float r3 = 1.0f;
        if (r8 == true) goto L17;
        r0[2] = true;
        if (r7 < 0.0f) goto L12;
    L15:
        int r2 = ColorUtils.blendARGB(-16777216, -1, r7);
        this.textColor = r2;
        this.iconColor = Theme.multAlpha(r2, 0.55f);
        this.backgroundColor = r6;
        this.addressBackgroundColor = ColorUtils.blendARGB(-1, -16777216, r7);
        int r02 = ColorUtils.blendARGB(-1, -16777216, 1.0f - r7);
        this.addressTextColor = r02;
        onAddressColorsChanged(this.addressBackgroundColor, r02);
        this.addressBackgroundPaint.setColor(this.addressBackgroundColor);
        this.addressRoundPaint.setColor(Theme.blendOver(this.addressBackgroundColor, Theme.multAlpha(this.textColor, AndroidUtilities.lerp(0.07f, 0.2f, r7))));
        this.addressEditText.setHintTextColor(Theme.multAlpha(this.addressTextColor, 0.6f));
        this.addressEditText.setTextColor(this.addressTextColor);
        this.addressEditText.setCursorColor(this.addressTextColor);
        this.addressEditText.setHandlesColor(this.addressTextColor);
        this.lineProgressView.setProgressColor(Theme.getColor(Theme.key_iv_ab_progress, this.resourcesProvider));
        this.backButtonDrawable.setColor(ColorUtils.blendARGB(this.textColor, this.addressTextColor, this.addressingProgress));
        this.backButtonDrawable.setRotatedColor(ColorUtils.blendARGB(this.textColor, this.addressTextColor, this.addressingProgress));
        this.forwardButtonDrawable.setColor(this.textColor);
        ImageView r72 = this.menuButton;
        int r22 = this.textColor;
        PorterDuff.Mode r32 = PorterDuff.Mode.SRC_IN;
        r72.setColorFilter(new PorterDuffColorFilter(r22, r32));
        this.forwardButton.setColorFilter(new PorterDuffColorFilter(this.textColor, r32));
        this.clearButton.setColorFilter(new PorterDuffColorFilter(this.textColor, r32));
        int r62 = Theme.blendOver(r6, Theme.multAlpha(this.textColor, 0.22f));
        this.rippleColor = r62;
        Theme.setSelectorDrawableColor(this.backButtonSelector, r62, true);
        Theme.setSelectorDrawableColor(this.forwardButtonSelector, this.rippleColor, true);
        Theme.setSelectorDrawableColor(this.menuButtonSelector, this.rippleColor, true);
        Theme.setSelectorDrawableColor(this.clearButtonSelector, this.rippleColor, true);
        this.searchEditText.setHintTextColor(Theme.multAlpha(this.textColor, 0.6f));
        this.searchEditText.setTextColor(this.textColor);
        this.searchEditText.setCursorColor(this.textColor);
        this.searchEditText.setHandlesColor(this.textColor);
        onColorsUpdated();
        invalidate();
        return;
    L12:
        if (AndroidUtilities.computePerceivedBrightness(r6) > 0.721f) goto L14;
        r7 = 1.0f;
        goto L15
    L14:
        r7 = 0.0f;
        goto L15
    L17:
        ValueAnimator r73 = this.colorAnimator;
        if (r73 == null) goto L20;
        r73.cancel();
    L20:
        int r74 = this.backgroundColor;
        this.fromBackgroundColor = r74;
        if (AndroidUtilities.computePerceivedBrightness(r74) > 0.721f) goto L23;
        float r75 = 1.0f;
    L25:
        if (AndroidUtilities.computePerceivedBrightness(r6) <= 0.721f) goto L28;
        r3 = 0.0f;
    L28:
        ValueAnimator r82 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.colorAnimator = r82;
        r82.addUpdateListener(new WebActionBar$$ExternalSyntheticLambda1(this, r6, r75, r3));
        this.colorAnimator.addListener(new AnonymousClass7(this, r6, r3));
        this.colorAnimator.start();
        return;
    L23:
        r75 = 0.0f;
        goto L25
    L5:
        if (this.backgroundColor != r6) goto L7;
    }

    public void setColors(int r2, boolean r3) {
        setColors(r2, -1.0f, r3);
    }

    public void setHasForward(boolean r1) {
        this.hasForward = r1;
    }

    public void setHeight(int r7) {
        if (this.height == r7) goto L6;
        this.height = r7;
        float r72 = (float) Math.pow(r7 / AndroidUtilities.dp(56.0f), 0.5d);
        this.scale = r72;
        this.leftmenu.setScaleX(r72);
        this.leftmenu.setScaleY(this.scale);
        this.leftmenu.setTranslationX(AndroidUtilities.dp(42.0f) * (1.0f - this.scale));
        this.leftmenu.setTranslationY(AndroidUtilities.dp(-12.0f) * (1.0f - this.scale));
        this.rightmenu.setScaleX(this.scale);
        this.rightmenu.setScaleY(this.scale);
        this.rightmenu.setTranslationX((-AndroidUtilities.dp(42.0f)) * (1.0f - this.scale));
        this.rightmenu.setTranslationY(AndroidUtilities.dp(-12.0f) * (1.0f - this.scale));
        this.lineProgressView.setTranslationY(this.height - AndroidUtilities.dp(56.0f));
        invalidate();
        return;
    }

    public void setIsDangerous(int r2, boolean r3, boolean r4) {
        Title r22 = this.titles[r2];
        if (r22.isDangerous == r3) goto L13;
        r22.isDangerous = r3;
        if (r4 == true) goto L11;
        AnimatedFloat r23 = r22.animatedDangerous;
        if (r3 == false) goto L9;
        float r32 = 1.0f;
    L10:
        r23.set(r32, true);
        goto L11
    L9:
        r32 = 0.0f;
    L11:
        invalidate();
        return;
    }

    public void setIsLoaded(boolean r1) {
        this.hasLoaded = r1;
    }

    public void setIsLocal(boolean r1) {
        this.isLocal = r1;
    }

    public void setIsTonsite(boolean r1) {
        this.isTonsite = r1;
    }

    public void setMenuColors(int r6) {
        boolean r0 = false;
        if (OKLCH.rgb2oklch(OKLCH.rgb(r6))[0] >= 0.5d) goto L5;
        r0 = true;
    L5:
        int r62 = -1;
        if (r0 == false) goto L8;
        int r2 = -16777216;
    L9:
        this.menuBackgroundColor = r2;
        if (r0 == true) goto L13;
        r62 = -16777216;
    L13:
        this.menuTextColor = r62;
        this.menuIconColor = Theme.multAlpha(r62, 0.6f);
        return;
    L8:
        r2 = -1;
        goto L9
    }

    public void setMenuListener(Utilities.Callback<Integer> r1) {
        this.menuListener = r1;
    }

    public void setMenuType(int r2) {
        if (this.menuType == r2) goto L6;
        this.menuType = r2;
        return;
    }

    public void setProgress(float r2) {
        setProgress(0, r2);
    }

    public void setProgress(int r2, float r3) {
        this.progress[r2] = r3;
        invalidate();
    }

    public void setSubtitle(int r3, String r4, boolean r5) {
        CharSequence r0 = this.titles[r3].subtitle.getText();
        if (r0 != null) goto L5;
    L8:
        CharSequence r42 = Emoji.replaceEmoji(r4, this.titles[r3].subtitle.getPaint().getFontMetricsInt(), false);
        this.titles[r3].subtitle.setText(r42, r5);
        return;
    L5:
        if (TextUtils.equals(r0.toString(), r4) == false) goto L8;
    }

    public void setTitle(int r3, String r4, boolean r5) {
        CharSequence r0 = this.titles[r3].title.getText();
        if (r0 != null) goto L5;
    L8:
        CharSequence r42 = Emoji.replaceEmoji(r4, this.titles[r3].title.getPaint().getFontMetricsInt(), false);
        this.titles[r3].title.setText(r42, r5);
        return;
    L5:
        if (TextUtils.equals(r0.toString(), r4) == false) goto L8;
    }

    public void setTransitionProgress(float r1) {
        this.titleProgress = r1;
        invalidate();
    }

    public void showAddress(String r3, Utilities.Callback<String> r4) {
        this.addressEditText.setText(r3);
        EditTextBoldCursor r32 = this.addressEditText;
        r32.setSelection(0, r32.getText().length());
        this.addressEditText.setScrollX(0);
        this.urlCallback = r4;
        showAddress(true, true);
    }

    public void showAddress(boolean r6, boolean r7) {
        if (this.addressing != r6) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.addressAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.addressing = r6;
        if (r6 == false) goto L13;
        int r02 = this.searchEngineIndex;
        int r1 = SharedConfig.searchEngineType;
        if (r02 == r1) goto L13;
        this.searchEngineIndex = r1;
        this.addressEditText.setHint(LocaleController.formatString(R.string.AddressPlaceholder, new Object[]{SearchEngine.getCurrent().name}));
    L13:
        int r12 = 0;
        float r2 = 0.0f;
        if (r7 == false) goto L25;
        this.addressEditText.setVisibility(0);
        BackDrawable r72 = this.backButtonDrawable;
        if (this.backButtonShown == true) goto L20;
        if (r6 == true) goto L20;
        float r4 = 1.0f;
    L21:
        r72.setRotation(r4, true);
        float r73 = this.addressingProgress;
        if (r6 == false) goto L24;
        r2 = 1.0f;
    L24:
        ValueAnimator r74 = ValueAnimator.ofFloat(new float[]{r73, r2});
        this.addressAnimator = r74;
        r74.addUpdateListener(new WebActionBar$$ExternalSyntheticLambda7(this));
        this.addressAnimator.addListener(new AnonymousClass9(this, r6));
        this.addressAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.addressAnimator.setDuration(360);
        this.addressAnimator.start();
    L42:
        AndroidUtilities.cancelRunOnUIThread(new WebActionBar$$ExternalSyntheticLambda8(this));
        WebActionBar$$ExternalSyntheticLambda8 r62 = new WebActionBar$$ExternalSyntheticLambda8(this);
        if (this.addressing == false) goto L45;
        long r03 = 100;
    L46:
        AndroidUtilities.runOnUIThread(r62, r03);
        return;
    L45:
        r03 = 0;
    L20:
        r4 = 0.0f;
        goto L21
    L25:
        if (r6 == false) goto L27;
        float r75 = 1.0f;
    L28:
        this.addressingProgress = r75;
        onAddressingProgress(r75);
        invalidate();
        EditTextBoldCursor r76 = this.addressEditText;
        if (r6 == false) goto L31;
        float r42 = 1.0f;
    L32:
        r76.setAlpha(r42);
        EditTextBoldCursor r77 = this.addressEditText;
        if (r6 == true) goto L36;
        r12 = 8;
    L36:
        r77.setVisibility(r12);
        this.menuButton.setTranslationX(AndroidUtilities.dp(56.0f) * this.addressingProgress);
        this.forwardButton.setTranslationX(AndroidUtilities.dp(112.0f) * this.addressingProgress);
        BackDrawable r78 = this.backButtonDrawable;
        if (this.backButtonShown == true) goto L41;
        if (r6 == true) goto L41;
        r2 = 1.0f;
    L41:
        r78.setRotation(r2, true);
        goto L42
    L31:
        r42 = 0.0f;
        goto L32
    L27:
        r75 = 0.0f;
        goto L28
    }

    public void showSearch(boolean r7, boolean r8) {
        if (this.searching != r7) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.searchAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.searching = r7;
        boolean r02 = false;
        float r1 = 0.0f;
        if (r8 == false) goto L20;
        this.searchEditText.setVisibility(0);
        BackDrawable r4 = this.backButtonDrawable;
        if (this.backButtonShown == true) goto L15;
        if (r7 == true) goto L15;
        float r5 = 1.0f;
    L16:
        r4.setRotation(r5, true);
        float r42 = this.searchingProgress;
        if (r7 == false) goto L19;
        r1 = 1.0f;
    L19:
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{r42, r1});
        this.searchAnimator = r12;
        r12.addUpdateListener(new WebActionBar$$ExternalSyntheticLambda0(this));
        this.searchAnimator.addListener(new AnonymousClass8(this, r7));
        this.searchAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.searchAnimator.setDuration(320);
        this.searchAnimator.start();
    L40:
        AndroidUtilities.updateViewShow(this.forwardButton, !r7, true, r8);
        AndroidUtilities.updateViewShow(this.menuButton, !r7, true, r8);
        ImageView r72 = this.clearButton;
        if (this.searchEditText.length() > 0) goto L43;
    L45:
        AndroidUtilities.updateViewShow(r72, r02, true, r8);
        return;
    L43:
        if (this.searching == false) goto L45;
        r02 = true;
    L15:
        r5 = 0.0f;
        goto L16
    L20:
        if (r7 == false) goto L22;
        float r43 = 1.0f;
    L23:
        this.searchingProgress = r43;
        invalidate();
        EditTextBoldCursor r44 = this.searchEditText;
        if (r7 == false) goto L26;
        float r52 = 1.0f;
    L27:
        r44.setAlpha(r52);
        EditTextBoldCursor r45 = this.searchEditText;
        if (r7 == false) goto L30;
        int r53 = 0;
    L31:
        r45.setVisibility(r53);
        BackDrawable r46 = this.backButtonDrawable;
        if (this.backButtonShown == true) goto L36;
        if (r7 == true) goto L36;
        r1 = 1.0f;
    L36:
        r46.setRotation(r1, true);
        boolean r13 = this.searching;
        EditTextBoldCursor r2 = this.searchEditText;
        if (r13 == false) goto L39;
        r2.requestFocus();
        AndroidUtilities.showKeyboard(this.searchEditText);
        goto L40
    L39:
        r2.clearFocus();
        AndroidUtilities.hideKeyboard(this.searchEditText);
        goto L40
    L30:
        r53 = 8;
        goto L31
    L26:
        r52 = 0.0f;
        goto L27
    L22:
        r43 = 0.0f;
        goto L23
    }

    public void swap() {
        Title[] r0 = this.titles;
        Title r2 = r0[0];
        r0[0] = r0[1];
        r0[1] = r2;
        float[] r02 = this.progress;
        float r22 = r02[0];
        r02[0] = r02[1];
        r02[1] = r22;
        int r03 = getBackgroundColor(0);
        setBackgroundColor(0, getBackgroundColor(1));
        setBackgroundColor(1, r03);
        invalidate();
    }

    public int topPadding() {
        if (this.occupyStatusBar == true) goto L5;
        return 0;
    L5:
        return AndroidUtilities.statusBarHeight;
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public boolean verifyDrawable(Drawable r1) {
        return true;
    }
}
