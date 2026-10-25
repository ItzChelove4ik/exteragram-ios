package org.telegram.ui.Stories.recorder;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ReplacementSpan;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.ProfileActivity$$ExternalSyntheticLambda71;

/* loaded from: classes3.dex */
public class HintView2 extends View {
    private float arrowHalfWidth;
    private float arrowHeight;
    private float arrowX;
    private float arrowY;
    protected final Paint backgroundPaint;
    private float blurAlpha;
    private Paint blurBackgroundPaint;
    private int blurBitmapHeight;
    private Matrix blurBitmapMatrix;
    private BitmapShader blurBitmapShader;
    private int blurBitmapWidth;
    private int[] blurPos;
    private float blurScale;
    private final ButtonBounce bounce;
    private ValueAnimator bounceAnimator;
    private float bounceT;
    private float bounceX;
    private float bounceY;
    private final RectF bounds;
    private final Rect boundsWithArrow;
    private boolean closeButton;
    private Drawable closeButtonDrawable;
    private float closeButtonMargin;
    private Paint cutSelectorPaint;
    private int direction;
    private boolean drawingMyBlur;
    private long duration;
    private AnimatedEmojiSpan.EmojiGroupedSpans emojiGroupedSpans;
    private boolean firstDraw;
    private boolean flicker;
    private final RectF flickerBounds;
    private Paint flickerFillPaint;
    private LinearGradient flickerGradient;
    private Matrix flickerGradientMatrix;
    private long flickerStart;
    private LinearGradient flickerStrokeGradient;
    private Paint flickerStrokePaint;
    private Path flickerStrokePath;
    private float flickerStrokePathExtrude;
    private boolean hideByTouch;
    private final Runnable hideRunnable;
    private Drawable icon;
    private int iconHeight;
    private boolean iconLeft;
    private int iconMargin;
    private float iconTx;
    private float iconTy;
    private int iconWidth;
    private final RectF innerPadding;
    private float joint;
    private float jointTranslate;
    private LinkSpanDrawable.LinkCollector links;
    private boolean multiline;
    private Runnable onHidden;
    private LinkSpanDrawable.LinksTextView.OnLinkPress onLongPressListener;
    private LinkSpanDrawable.LinksTextView.OnLinkPress onPressListener;
    private final RectF oval;
    protected final Path path;
    private float pathLastHeight;
    private float pathLastWidth;
    private boolean pathSet;
    private LinkSpanDrawable<ClickableSpan> pressedLink;
    private boolean repeatedBounce;
    private boolean roundWithCornerEffect;
    protected float rounding;
    private Drawable selectorDrawable;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private AnimatedFloat show;
    private boolean shown;
    private AnimatedTextView.AnimatedTextDrawable textDrawable;
    private StaticLayout textLayout;
    private Layout.Alignment textLayoutAlignment;
    private float textLayoutHeight;
    private float textLayoutLeft;
    private float textLayoutWidth;
    private int textMaxWidth;
    private final TextPaint textPaint;
    private CharSequence textToSet;
    private float textX;
    private float textY;
    private boolean useAlpha;
    private boolean useBlur;
    private boolean useScale;
    private boolean useTranslate;

    /* renamed from: org.telegram.ui.Stories.recorder.HintView2$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends Drawable {
        final /* synthetic */ HintView2 this$0;

        public AnonymousClass1(HintView2 r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r2) {
            r2.save();
            HintView2 r1 = this.this$0;
            r2.drawPath(r1.path, HintView2.m20044$$Nest$fgetcutSelectorPaint(r1));
            r2.restore();
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

    /* renamed from: org.telegram.ui.Stories.recorder.HintView2$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ HintView2 this$0;

        public AnonymousClass2(HintView2 r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            HintView2.m20045$$Nest$fputbounceT(this.this$0, 1.0f);
            this.this$0.invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$1gX7MFb0oGTE3DVQl3RrinXjRUQ(HintView2 r0, LinkSpanDrawable r1, ClickableSpan r2) {
        r0.lambda$checkTouchLinks$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Qp_wxSmXs3ok2V7rVFRdtcog8oU(HintView2 r0, ValueAnimator r1) {
        r0.lambda$bounceShow$0(r1);
    }

    /* renamed from: $r8$lambda$kx9OBOb1-BqiBoBToRQyklsQtGo, reason: not valid java name */
    public static /* synthetic */ void m20043$r8$lambda$kx9OBOb1BqiBoBToRQyklsQtGo(HintView2 r0, Bitmap r1) {
        r0.lambda$prepareBlur$2(r1);
    }

    /* renamed from: -$$Nest$fgetcutSelectorPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20044$$Nest$fgetcutSelectorPaint(HintView2 r0) {
        return r0.cutSelectorPaint;
    }

    /* renamed from: -$$Nest$fputbounceT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20045$$Nest$fputbounceT(HintView2 r0, float r1) {
        r0.bounceT = r1;
    }

    public HintView2(Context r2) {
        this(r2, 0);
    }

    public HintView2(Context r13, int r14) {
        super(r13);
        this.joint = 0.5f;
        this.jointTranslate = 0.0f;
        this.duration = 3500;
        this.useScale = true;
        this.useTranslate = true;
        this.useAlpha = true;
        this.useBlur = false;
        this.textMaxWidth = -1;
        this.roundWithCornerEffect = true;
        this.rounding = AndroidUtilities.dp(8.0f);
        this.innerPadding = new RectF(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(7.0f));
        this.closeButtonMargin = AndroidUtilities.dp(2.0f);
        this.arrowHalfWidth = AndroidUtilities.dp(7.0f);
        this.arrowHeight = AndroidUtilities.dp(6.0f);
        Paint r3 = new Paint(1);
        this.backgroundPaint = r3;
        this.blurScale = 12.0f;
        this.blurAlpha = 0.25f;
        this.textPaint = new TextPaint(1);
        this.textLayoutAlignment = Layout.Alignment.ALIGN_NORMAL;
        this.links = new LinkSpanDrawable.LinkCollector();
        this.hideByTouch = true;
        this.repeatedBounce = true;
        CubicBezierInterpolator r11 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.show = new AnimatedFloat(this, 350, r11);
        this.iconMargin = AndroidUtilities.dp(2.0f);
        this.hideRunnable = new ProfileActivity$$ExternalSyntheticLambda71(this);
        this.bounceT = 1.0f;
        this.bounce = new ButtonBounce(this, 2.0f, 5.0f);
        this.boundsWithArrow = new Rect();
        this.bounds = new RectF();
        this.flickerBounds = new RectF();
        this.path = new Path();
        this.firstDraw = true;
        this.oval = new RectF();
        this.direction = r14;
        r3.setColor(-433575896);
        r3.setPathEffect(new CornerPathEffect(this.rounding));
        AnimatedTextView.AnimatedTextDrawable r5 = new AnimatedTextView.AnimatedTextDrawable(true, true, false);
        this.textDrawable = r5;
        r5.setAnimationProperties(0.4f, 0, 320, r11);
        this.textDrawable.setCallback(this);
        setTextSize(14.0f);
        setTextColor(-1);
    }

    private void bounceShow() {
        if (this.repeatedBounce == true) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.bounceAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.bounceAnimator = null;
    L8:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.bounceAnimator = r02;
        r02.addUpdateListener(new HintView2$$ExternalSyntheticLambda1(this));
        this.bounceAnimator.addListener(new AnonymousClass2(this));
        this.bounceAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_BACK);
        this.bounceAnimator.setDuration(300);
        this.bounceAnimator.start();
    }

    private boolean checkTouchLinks(MotionEvent r8) {
        if (this.textLayout == null) goto L29;
        ClickableSpan r0 = hitLink((int) r8.getX(), (int) r8.getY());
        if (r0 == null) goto L11;
        if (r8.getAction() != 0) goto L11;
        LinkSpanDrawable<ClickableSpan> r3 = new LinkSpanDrawable(r0, null, r8.getX(), r8.getY());
        this.pressedLink = r3;
        this.links.addLink(r3);
        SpannableString r82 = new SpannableString(this.textLayout.getText());
        int r2 = r82.getSpanStart(this.pressedLink.getSpan());
        int r83 = r82.getSpanEnd(this.pressedLink.getSpan());
        LinkPath r4 = this.pressedLink.obtainNewPath();
        r4.setCurrentLayout(this.textLayout, r2, 0.0f);
        this.textLayout.getSelectionPath(r2, r83, r4);
        invalidate();
        AndroidUtilities.runOnUIThread(new HintView2$$ExternalSyntheticLambda2(this, r3, r0), ViewConfiguration.getLongPressTimeout());
        pause();
        return true;
    L11:
        if (r8.getAction() != 1) goto L26;
        this.links.clear();
        invalidate();
        unpause();
        LinkSpanDrawable<ClickableSpan> r32 = this.pressedLink;
        if (r32 != null) goto L15;
    L24:
        this.pressedLink = null;
        goto L26
    L15:
        if (r32.getSpan() != r0) goto L24;
        LinkSpanDrawable.LinksTextView.OnLinkPress r84 = this.onPressListener;
        LinkSpanDrawable<ClickableSpan> r02 = this.pressedLink;
        if (r84 == null) goto L20;
        r84.run((ClickableSpan) r02.getSpan());
    L22:
        this.pressedLink = null;
        return true;
    L20:
        if (r02.getSpan() == null) goto L22;
        ((ClickableSpan) this.pressedLink.getSpan()).onClick(this);
    L26:
        if (r8.getAction() != 3) goto L29;
        this.links.clear();
        invalidate();
        unpause();
        this.pressedLink = null;
    L29:
        if (this.pressedLink == null) goto L31;
        return true;
    L31:
        return false;
    }

    private boolean checkTouchTap(MotionEvent r5) {
        float r0 = r5.getX();
        float r1 = r5.getY();
        if (r5.getAction() != 0) goto L11;
        if (containsTouch(r5, 0.0f, 0.0f) == false) goto L11;
        this.bounceX = r0;
        this.bounceY = r1;
        this.bounce.setPressed(true);
        Drawable r52 = this.selectorDrawable;
        if (r52 == null) goto L9;
        r52.setHotspot(r0, r1);
        this.selectorDrawable.setState(new int[]{R.attr.state_pressed, R.attr.state_enabled});
    L9:
        return true;
    L11:
        if (r5.getAction() != 1) goto L23;
        if (hasOnClickListeners() == false) goto L16;
        performClick();
    L18:
        this.bounce.setPressed(false);
        Drawable r4 = this.selectorDrawable;
        if (r4 == null) goto L21;
        r4.setState(new int[0]);
    L21:
        return true;
    L16:
        if (this.hideByTouch == false) goto L18;
        hide();
        goto L18
    L23:
        if (r5.getAction() != 3) goto L28;
        this.bounce.setPressed(false);
        Drawable r42 = this.selectorDrawable;
        if (r42 == null) goto L27;
        r42.setState(new int[0]);
    L27:
        return true;
    L28:
        return false;
    }

    public static int cutInFancyHalf(CharSequence r10, TextPaint r11) {
        if (TextUtils.indexOf(r10, '\n') < 0) goto L6;
        return Integer.MAX_VALUE;
    L6:
        int r1 = r10.length() / 2;
        float r4 = 0.0f;
        int r8 = 0;
        int r9 = -1;
        float r6 = 0.0f;
        float r7 = Float.MAX_VALUE;
        float r5 = 0.0f;
    L7:
        if (r8 >= 10) goto L29;
    L8:
        if (r1 <= 0) goto L14;
        if (r1 >= r10.length()) goto L14;
        if (r10.charAt(r1) == ' ') goto L14;
        r1 = r1 + r9;
    L14:
        r5 = measureCorrectly(r10.subSequence(0, r1), r11);
        r6 = measureCorrectly(AndroidUtilities.getTrimmedString(r10.subSequence(r1, r10.length())), r11);
        if (r5 != r4) goto L20;
        if (r6 == r7) goto L29;
    L20:
        if (r5 >= r6) goto L22;
        r1 = r1 + 1;
        r9 = 1;
    L23:
        if (r1 <= 0) goto L29;
        if (r1 >= r10.length()) goto L29;
        r8 = r8 + 1;
        r4 = r5;
        r7 = r6;
        goto L7
    L22:
        r1 = r1 - 1;
        r9 = -1;
    L29:
        return (int) Math.ceil(Math.max(r5, r6));
    }

    public static CharSequence cutInFancyHalfText(CharSequence r12, TextPaint r13) {
        if (TextUtils.indexOf(r12, '\n') < 0) goto L5;
        return r12;
    L5:
        int r1 = r12.length() / 2;
        int r9 = -1;
        int r8 = 0;
        float r7 = Float.MAX_VALUE;
        float r6 = 0.0f;
        int r5 = r1;
    L6:
        int r10 = 1;
        if (r8 >= 10) goto L31;
        r1 = r5;
    L9:
        if (r1 <= 0) goto L15;
        if (r1 >= r12.length()) goto L15;
        if (r12.charAt(r1) == ' ') goto L15;
        r1 = r1 + r9;
    L15:
        float r52 = measureCorrectly(r12.subSequence(0, r1), r13);
        float r92 = measureCorrectly(AndroidUtilities.getTrimmedString(r12.subSequence(r1, r12.length())), r13);
        if (r52 != r6) goto L21;
        if (r92 == r7) goto L31;
    L21:
        if (r52 >= r92) goto L23;
        int r62 = r1 + 1;
    L24:
        if (r62 <= 0) goto L29;
        if (r62 >= r12.length()) goto L29;
        r8 = r8 + 1;
        int r72 = r62;
        r6 = r52;
        r5 = r72;
        r7 = r92;
        r9 = r10;
    L29:
        return r12;
    L23:
        r62 = r1 - 1;
        r10 = -1;
    L31:
        return TextUtils.concat(new CharSequence[]{AndroidUtilities.getTrimmedString(r12.subSequence(0, r1)), "\n", AndroidUtilities.getTrimmedString(r12.subSequence(r1, r12.length()))});
    }

    private void fillPath(Path r10, float r11, float r12, float r13, RectF r14, Rect r15) {
        float r2 = r11 / 2.0f;
        float r3 = r12 / 2.0f;
        float r0 = Math.min(this.rounding, Math.min(r2, r3));
        int r4 = this.direction;
        if (r4 == 1) goto L10;
        if (r4 == 3) goto L10;
        float r22 = Utilities.clamp(AndroidUtilities.lerp(getPaddingTop(), getMeasuredHeight() - getPaddingBottom(), this.joint) + this.jointTranslate, getMeasuredHeight() - getPaddingBottom(), getPaddingTop());
        float r32 = Math.min(Math.max(getPaddingTop(), r22 - r3) + r12, getMeasuredHeight() - getPaddingBottom());
        float r122 = r32 - r12;
        float r7 = this.arrowHalfWidth;
        float r23 = Utilities.clamp(r22, (r32 - r0) - r7, (r122 + r0) + r7);
        if (this.direction != 0) goto L9;
        r14.set(getPaddingLeft() + this.arrowHeight, r122, (getPaddingLeft() + this.arrowHeight) + r11, r32);
    L15:
        float r112 = -r13;
        r14.inset(r112, r112);
        if (r15 == null) goto L18;
        r15.set((int) r14.left, (int) r14.top, (int) r14.right, (int) r14.bottom);
    L18:
        r10.rewind();
        if (this.roundWithCornerEffect == false) goto L21;
        r10.moveTo(r14.left, r14.bottom);
    L23:
        if (this.direction != 0) goto L28;
        r10.lineTo(r14.left, (this.arrowHalfWidth + r23) + AndroidUtilities.dp(2.0f));
        r10.lineTo(r14.left, this.arrowHalfWidth + r23);
        r10.lineTo(r14.left - this.arrowHeight, AndroidUtilities.dp(1.0f) + r23);
        float r113 = r14.left;
        float r33 = this.arrowHeight;
        this.arrowX = r113 - r33;
        this.arrowY = r23;
        r10.lineTo(r113 - r33, r23 - AndroidUtilities.dp(1.0f));
        r10.lineTo(r14.left, r23 - this.arrowHalfWidth);
        r10.lineTo(r14.left, (r23 - this.arrowHalfWidth) - AndroidUtilities.dp(2.0f));
        if (r15 == null) goto L28;
        r15.left = (int) (r15.left - this.arrowHeight);
    L28:
        if (this.roundWithCornerEffect == false) goto L30;
        r10.lineTo(r14.left, r14.top);
    L32:
        if (this.direction != 1) goto L37;
        r10.lineTo((r23 - this.arrowHalfWidth) - AndroidUtilities.dp(2.0f), r14.top);
        r10.lineTo(r23 - this.arrowHalfWidth, r14.top);
        r10.lineTo(r23 - AndroidUtilities.dp(1.0f), r14.top - this.arrowHeight);
        this.arrowX = r23;
        this.arrowY = r14.top - this.arrowHeight;
        r10.lineTo(AndroidUtilities.dp(1.0f) + r23, r14.top - this.arrowHeight);
        r10.lineTo(this.arrowHalfWidth + r23, r14.top);
        r10.lineTo((this.arrowHalfWidth + r23) + AndroidUtilities.dp(2.0f), r14.top);
        if (r15 == null) goto L37;
        r15.top = (int) (r15.top - this.arrowHeight);
    L37:
        if (this.roundWithCornerEffect == false) goto L39;
        r10.lineTo(r14.right, r14.top);
    L41:
        if (this.direction != 2) goto L46;
        r10.lineTo(r14.right, (r23 - this.arrowHalfWidth) - AndroidUtilities.dp(2.0f));
        r10.lineTo(r14.right, r23 - this.arrowHalfWidth);
        r10.lineTo(r14.right + this.arrowHeight, r23 - AndroidUtilities.dp(1.0f));
        float r114 = r14.right;
        float r34 = this.arrowHeight;
        this.arrowX = r114 + r34;
        this.arrowY = r23;
        r10.lineTo(r114 + r34, AndroidUtilities.dp(1.0f) + r23);
        r10.lineTo(r14.right, this.arrowHalfWidth + r23);
        r10.lineTo(r14.right, (this.arrowHalfWidth + r23) + AndroidUtilities.dp(2.0f));
        if (r15 == null) goto L46;
        r15.right = (int) (r15.right + this.arrowHeight);
    L46:
        if (this.roundWithCornerEffect == false) goto L48;
        r10.lineTo(r14.right, r14.bottom);
    L50:
        if (this.direction != 3) goto L54;
        r10.lineTo((this.arrowHalfWidth + r23) + AndroidUtilities.dp(2.0f), r14.bottom);
        r10.lineTo(this.arrowHalfWidth + r23, r14.bottom);
        r10.lineTo(AndroidUtilities.dp(1.0f) + r23, r14.bottom + this.arrowHeight);
        this.arrowX = r23;
        this.arrowY = r14.bottom + this.arrowHeight;
        r10.lineTo(r23 - AndroidUtilities.dp(1.0f), r14.bottom + this.arrowHeight);
        r10.lineTo(r23 - this.arrowHalfWidth, r14.bottom);
        r10.lineTo((r23 - this.arrowHalfWidth) - AndroidUtilities.dp(2.0f), r14.bottom);
        if (r15 == null) goto L54;
        r15.bottom = (int) (r15.bottom + this.arrowHeight);
    L54:
        r10.close();
        this.pathSet = true;
        return;
    L48:
        RectF r115 = this.oval;
        float r35 = r14.right;
        float r02 = r0 * 2.0f;
        float r72 = r14.bottom;
        r115.set(r35 - r02, r72 - r02, r35, r72);
        r10.arcTo(this.oval, 0.0f, 90.0f);
        goto L50
    L39:
        RectF r116 = this.oval;
        float r36 = r14.right;
        float r42 = r0 * 2.0f;
        float r8 = r14.top;
        r116.set(r36 - r42, r8, r36, r42 + r8);
        r10.arcTo(this.oval, 270.0f, 90.0f);
        goto L41
    L30:
        RectF r117 = this.oval;
        float r37 = r14.left;
        float r43 = r14.top;
        float r73 = r0 * 2.0f;
        r117.set(r37, r43, r37 + r73, r73 + r43);
        r10.arcTo(this.oval, 180.0f, 90.0f);
        goto L32
    L21:
        RectF r118 = this.oval;
        float r132 = r14.left;
        float r38 = r14.bottom;
        float r44 = r0 * 2.0f;
        r118.set(r132, r38 - r44, r44 + r132, r38);
        r10.arcTo(this.oval, 90.0f, 90.0f);
        goto L23
    L9:
        r14.set(((getMeasuredWidth() - getPaddingRight()) - this.arrowHeight) - r11, r122, (getMeasuredWidth() - getPaddingRight()) - this.arrowHeight, r32);
    L10:
        float r39 = Utilities.clamp(AndroidUtilities.lerp(getPaddingLeft(), getMeasuredWidth() - getPaddingRight(), this.joint) + this.jointTranslate, getMeasuredWidth() - getPaddingRight(), getPaddingLeft());
        float r24 = Math.min(Math.max(getPaddingLeft(), r39 - r2) + r11, getMeasuredWidth() - getPaddingRight());
        float r119 = r24 - r11;
        float r74 = this.arrowHalfWidth;
        float r310 = Utilities.clamp(r39, (r24 - r0) - r74, (r119 + r0) + r74);
        if (this.direction != 1) goto L13;
        r14.set(r119, getPaddingTop() + this.arrowHeight, r24, (getPaddingTop() + this.arrowHeight) + r12);
    L14:
        r23 = r310;
        goto L15
    L13:
        r14.set(r119, ((getMeasuredHeight() - this.arrowHeight) - getPaddingBottom()) - r12, r24, (getMeasuredHeight() - this.arrowHeight) - getPaddingBottom());
        goto L14
    }

    private int getTextMaxWidth() {
        int r0 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        RectF r1 = this.innerPadding;
        int r02 = r0 - ((int) (r1.left + r1.right));
        int r3 = this.textMaxWidth;
        if (r3 <= 0) goto L6;
        r02 = Math.min(r3, r02);
    L6:
        return Math.max(0, r02);
    }

    private ClickableSpan hitLink(int r6, int r7) {
        StaticLayout r0 = this.textLayout;
        if (r0 != null) goto L5;
        return null;
    L5:
        int r62 = (int) (r6 - this.textX);
        int r72 = (int) (r7 - this.textY);
        int r02 = r0.getLineForVertical(r72);
        float r63 = r62;
        int r2 = this.textLayout.getOffsetForHorizontal(r02, r63);
        float r3 = this.textLayout.getLineLeft(r02);
        if (r3 <= r63) goto L8;
    L18:
        return null;
    L8:
        if ((r3 + this.textLayout.getLineWidth(r02)) < r63) goto L18;
        if (r72 < 0) goto L18;
        if (r72 > this.textLayout.getHeight()) goto L18;
        ClickableSpan[] r5 = (ClickableSpan[]) new SpannableString(this.textLayout.getText()).getSpans(r2, r2, ClickableSpan.class);
        if (r5.length == 0) goto L18;
        if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == true) goto L18;
        return r5[0];
    }

    private /* synthetic */ void lambda$bounceShow$0(ValueAnimator r2) {
        this.bounceT = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
        invalidate();
    }

    private /* synthetic */ void lambda$checkTouchLinks$1(LinkSpanDrawable r3, ClickableSpan r4) {
        LinkSpanDrawable.LinksTextView.OnLinkPress r0 = this.onLongPressListener;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.pressedLink != r3) goto L9;
        r0.run(r4);
        this.pressedLink = null;
        this.links.clear();
        return;
    }

    private /* synthetic */ void lambda$prepareBlur$2(Bitmap r3) {
        this.drawingMyBlur = false;
        this.blurBitmapWidth = r3.getWidth();
        this.blurBitmapHeight = r3.getHeight();
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        this.blurBitmapShader = new BitmapShader(r3, r1, r1);
        this.blurBitmapMatrix = new Matrix();
        Paint r32 = new Paint(1);
        this.blurBackgroundPaint = r32;
        r32.setShader(this.blurBitmapShader);
        this.blurBackgroundPaint.setPathEffect(new CornerPathEffect(this.rounding));
        ColorMatrix r33 = new ColorMatrix();
        r33.setSaturation(1.5f);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r0 = 0.12f;
    L6:
        AndroidUtilities.adjustBrightnessColorMatrix(r33, r0);
        this.blurBackgroundPaint.setColorFilter(new ColorMatrixColorFilter(r33));
        return;
    L5:
        r0 = -0.08f;
        goto L6
    }

    private void makeLayout(CharSequence r9, int r10) {
        this.textLayout = new StaticLayout(r9, this.textPaint, r10, this.textLayoutAlignment, 1.0f, 0.0f, false);
        float r92 = r10;
        float r2 = 0.0f;
        int r1 = 0;
    L4:
        if (r1 >= this.textLayout.getLineCount()) goto L6;
        r92 = Math.min(r92, this.textLayout.getLineLeft(r1));
        r2 = Math.max(r2, this.textLayout.getLineRight(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        this.textLayoutWidth = Math.max(0.0f, r2 - r92);
        this.textLayoutHeight = this.textLayout.getHeight();
        this.textLayoutLeft = r92;
        this.emojiGroupedSpans = AnimatedEmojiSpan.update(0, this, this.emojiGroupedSpans, new Layout[]{this.textLayout});
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static float measureCorrectly(CharSequence r14, Paint r15) {
        float r0 = 0.0f;
        if (r14 != null) goto L6;
        return 0.0f;
    L6:
        if ((r14 instanceof Spanned) == false) goto L8;
        Spanned r1 = (Spanned) r14;
        int r4 = 0;
        TypefaceSpan[] r2 = (TypefaceSpan[]) r1.getSpans(0, r14.length(), TypefaceSpan.class);
        ReplacementSpan[] r3 = (ReplacementSpan[]) r1.getSpans(0, r14.length(), ReplacementSpan.class);
        int r5 = 0;
        int r6 = 0;
    L11:
        if (r5 >= r3.length) goto L13;
        ReplacementSpan r8 = r3[r5];
        Paint r9 = r15;
        r6 = (int) (r6 + Math.max(0.0f, r8.getSize(r9, r10, r11, r12, r15.getFontMetricsInt()) - r9.measureText(r1, r1.getSpanStart(r8), r1.getSpanEnd(r8))));
        r5 = r5 + 1;
        r15 = r9;
        r14 = r14;
        goto L11
    L13:
        CharSequence r10 = r14;
        Paint r92 = r15;
        if (r2 == null) goto L34;
        if (r2.length == 0) goto L34;
        int r142 = 0;
    L20:
        if (r4 >= r2.length) goto L28;
        int r152 = r1.getSpanStart(r2[r4]);
        int r32 = r1.getSpanEnd(r2[r4]);
        int r153 = Math.max(r142, r152);
        if ((r153 - r142) <= 0) goto L24;
        r0 = r0 + r92.measureText(r1, r142, r153);
    L24:
        r142 = Math.max(r153, r32);
        if ((r142 - r153) <= 0) goto L27;
        Typeface r33 = r92.getTypeface();
        r92.setTypeface(r2[r4].getTypeface());
        r0 = r0 + r92.measureText(r1, r153, r142);
        r92.setTypeface(r33);
    L27:
        r4 = r4 + 1;
        goto L20
    L28:
        int r154 = Math.max(r142, r10.length());
        if ((r154 - r142) <= 0) goto L32;
        r0 = r0 + r92.measureText(r1, r142, r154);
    L32:
        return r0 + r6;
    L34:
        return r92.measureText(r10.toString()) + r6;
    L8:
        return r15.measureText(r14.toString());
    }

    private void prepareBlur() {
        if (this.useBlur == true) goto L5;
        return;
    L5:
        this.drawingMyBlur = true;
        AndroidUtilities.makeGlobalBlurBitmap(new HintView2$$ExternalSyntheticLambda0(this), this.blurScale);
    }

    private void updateBlurBounds() {
        if (this.useBlur == true) goto L6;
        return;
    L6:
        if (this.blurBitmapShader != null) goto L8;
        return;
    L8:
        if (this.blurBitmapMatrix != null) goto L11;
        return;
    L11:
        if (this.blurPos != null) goto L13;
        this.blurPos = new int[2];
    L13:
        getLocationOnScreen(this.blurPos);
        this.blurBitmapMatrix.reset();
        Matrix r0 = this.blurBitmapMatrix;
        Point r1 = AndroidUtilities.displaySize;
        r0.postScale(r1.x / this.blurBitmapWidth, (r1.y + AndroidUtilities.statusBarHeight) / this.blurBitmapHeight);
        Matrix r02 = this.blurBitmapMatrix;
        int[] r12 = this.blurPos;
        r02.postTranslate(-r12[0], -r12[1]);
        if (this.show.get() < 1.0f) goto L16;
    L18:
        this.blurBitmapShader.setLocalMatrix(this.blurBitmapMatrix);
        return;
    L16:
        if (this.useScale == false) goto L18;
        float r13 = 1.0f / AndroidUtilities.lerp(0.75f, 1.0f, this.show.get());
        this.blurBitmapMatrix.postScale(r13, r13, this.arrowX, this.arrowY);
        goto L18
    }

    public HintView2 allowBlur(boolean r1) {
        if (r1 == true) goto L4;
    L6:
        boolean r12 = false;
    L7:
        this.useBlur = r12;
        return this;
    L4:
        if (LiteMode.isEnabled(256) == false) goto L6;
        r12 = true;
        goto L7
    }

    public boolean containsTouch(MotionEvent r2, float r3, float r4) {
        return this.bounds.contains(r2.getX() - r3, r2.getY() - r4);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r20) {
        if (this.drawingMyBlur == false) goto L6;
        return;
    L6:
        if (this.multiline == true) goto L8;
    L10:
        AnimatedFloat r1 = this.show;
        int r8 = 1;
        if (this.shown == true) goto L13;
    L15:
        boolean r2 = false;
    L16:
        float r9 = r1.set(r2);
        if (this.firstDraw == false) goto L19;
        this.firstDraw = false;
        invalidate();
    L19:
        float r10 = 0.0f;
        if (r9 > 0.0f) goto L23;
        return;
    L23:
        if (this.multiline == false) goto L25;
        float r12 = this.textLayoutWidth;
    L27:
        if (this.multiline == false) goto L29;
        float r22 = this.textLayoutHeight;
    L31:
        if (this.closeButton == false) goto L37;
        if (this.closeButtonDrawable != null) goto L35;
        Drawable r3 = getContext().getResources().getDrawable(org.telegram.messenger.R.drawable.msg_mini_close_tooltip).mutate();
        this.closeButtonDrawable = r3;
        r3.setColorFilter(new PorterDuffColorFilter(2113929215, PorterDuff.Mode.MULTIPLY));
    L35:
        r12 = r12 + (this.closeButtonMargin + this.closeButtonDrawable.getIntrinsicWidth());
        r22 = Math.max(this.closeButtonDrawable.getIntrinsicHeight(), r22);
    L37:
        if (this.icon == null) goto L39;
        r12 = r12 + (this.iconWidth + this.iconMargin);
        r22 = Math.max(this.iconHeight, r22);
    L39:
        float r122 = r12;
        RectF r13 = this.innerPadding;
        float r32 = (r13.left + r122) + r13.right;
        float r4 = (r13.top + r22) + r13.bottom;
        if (this.pathSet == true) goto L42;
    L47:
        Path r14 = this.path;
        this.pathLastWidth = r32;
        this.pathLastHeight = r4;
        float r33 = r4;
        fillPath(r14, r32, r33, 0.0f, this.bounds, this.boundsWithArrow);
        if (this.flicker == false) goto L50;
        fillPath(this.flickerStrokePath, r32, r33, this.flickerStrokePathExtrude, this.flickerBounds, null);
    L50:
        HintView2 r132 = this;
    L52:
        if (r132.useAlpha == false) goto L54;
        float r142 = r9;
    L55:
        r20.save();
        if (r9 < 1.0f) goto L58;
    L60:
        float r0 = r132.bounce.getScale(0.025f);
        if (r0 == 1.0f) goto L64;
        r20.scale(r0, r0, r132.arrowX, r132.arrowY);
    L64:
        if (r132.bounceT == 1.0f) goto L83;
        int r02 = r132.direction;
        if (r02 == 3) goto L76;
        if (r02 == 1) goto L76;
        if (r02 != 0) goto L71;
        int r03 = r132.getPaddingLeft();
    L72:
        float r5 = (r132.bounceT - 1.0f) * Math.max(r03, AndroidUtilities.dp(24.0f));
        if (r132.direction != 0) goto L75;
        r8 = -1;
    L75:
        r20.translate(r5 * r8, 0.0f);
        goto L83
    L71:
        r03 = r132.getPaddingRight();
    L76:
        if (r02 != 3) goto L78;
        int r04 = r132.getPaddingBottom();
    L79:
        float r52 = (r132.bounceT - 1.0f) * Math.max(r04, AndroidUtilities.dp(24.0f));
        if (r132.direction != 1) goto L82;
        r8 = -1;
    L82:
        r20.translate(0.0f, r52 * r8);
        goto L83
    L78:
        r04 = r132.getPaddingTop();
    L83:
        r132.updateBlurBounds();
        RectF r05 = AndroidUtilities.rectTmp;
        r05.set(r132.bounds);
        float r23 = r132.arrowHeight;
        r05.inset(-r23, -r23);
        Paint r06 = r132.blurBackgroundPaint;
        if (r06 != null) goto L86;
    L88:
        float r15 = r142;
    L89:
        r132.drawBgPath(r20, r15);
        Drawable r07 = r132.selectorDrawable;
        if (r07 == null) goto L92;
        r07.setAlpha((int) (r142 * 255.0f));
        r132.selectorDrawable.setBounds(r132.boundsWithArrow);
        r132.selectorDrawable.draw(r20);
    L92:
        RectF r08 = r132.bounds;
        float r16 = r08.bottom;
        RectF r24 = r132.innerPadding;
        float r82 = ((r16 - r24.bottom) + (r08.top + r24.top)) / 2.0f;
        Drawable r17 = r132.icon;
        if (r17 == null) goto L99;
        boolean r42 = r132.iconLeft;
        float r53 = r132.iconTx;
        if (r42 == false) goto L97;
        float r09 = r08.left;
        float r25 = r24.left;
        float r6 = r132.iconTy;
        float r172 = 255.0f;
        int r92 = r132.iconHeight;
        r17.setBounds((int) ((r53 + r09) + (r25 / 2.0f)), (int) ((r6 + r82) - (r92 / 2.0f)), (int) (((r53 + r09) + (r25 / 2.0f)) + r132.iconWidth), (int) ((r6 + r82) + (r92 / 2.0f)));
        r10 = (r132.iconWidth + r132.iconMargin) + 0.0f;
        float r162 = 2.0f;
    L98:
        r132.icon.setAlpha((int) (r142 * r172));
        r132.icon.draw(r20);
    L101:
        if (r132.multiline == false) goto L106;
        Canvas r010 = r20;
        r010.saveLayerAlpha(0.0f, 0.0f, r132.getWidth(), Math.max(r132.getHeight(), r33), (int) (r142 * r172), 31);
        float r102 = ((r10 + r132.bounds.left) + r132.innerPadding.left) - r132.textLayoutLeft;
        r132.textX = r102;
        float r83 = r82 - (r132.textLayoutHeight / r162);
        r132.textY = r83;
        r010.translate(r102, r83);
        if (r132.links.draw(r010) == false) goto L105;
        r132.invalidate();
    L105:
        r132.textLayout.draw(r010);
        AnimatedEmojiSpan.drawAnimatedEmojis(r010, r132.textLayout, r132.emojiGroupedSpans, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        r010.restore();
    L111:
        if (r132.closeButton == true) goto L113;
    L116:
        r010.restore();
        return;
    L113:
        if (r132.closeButtonDrawable != null) goto L115;
        Drawable r18 = r132.getContext().getResources().getDrawable(org.telegram.messenger.R.drawable.msg_mini_close_tooltip).mutate();
        r132.closeButtonDrawable = r18;
        r18.setColorFilter(new PorterDuffColorFilter(2113929215, PorterDuff.Mode.MULTIPLY));
    L115:
        r132.closeButtonDrawable.setAlpha((int) (r142 * r172));
        Drawable r19 = r132.closeButtonDrawable;
        int r26 = (int) ((r132.bounds.right - (r132.innerPadding.right * 0.66f)) - r19.getIntrinsicWidth());
        int r34 = (int) (r132.bounds.centerY() - (r132.closeButtonDrawable.getIntrinsicHeight() / r162));
        RectF r54 = r132.bounds;
        r19.setBounds(r26, r34, (int) (r54.right - (r132.innerPadding.right * 0.66f)), (int) (r54.centerY() + (r132.closeButtonDrawable.getIntrinsicHeight() / r162)));
        r132.closeButtonDrawable.draw(r010);
        goto L116
    L106:
        r010 = r20;
        CharSequence r110 = r132.textToSet;
        if (r110 == null) goto L109;
        r132.textDrawable.setText(r110, r132.shown);
        r132.textToSet = null;
    L109:
        AnimatedTextView.AnimatedTextDrawable r111 = r132.textDrawable;
        float r27 = r132.bounds.left;
        float r35 = r132.innerPadding.left;
        float r55 = r132.textLayoutHeight;
        r111.setBounds((int) ((r10 + r27) + r35), (int) (r82 - (r55 / r162)), (int) ((r27 + r35) + r122), (int) (r82 + (r55 / r162)));
        r132.textDrawable.setAlpha((int) (r142 * r172));
        r132.textDrawable.draw(r010);
        goto L111
    L97:
        r172 = 255.0f;
        float r011 = r08.right;
        float r28 = r24.right;
        float r62 = r132.iconTy;
        int r103 = r132.iconHeight;
        r162 = 2.0f;
        r17.setBounds((int) (((r53 + r011) - (r28 / 2.0f)) - r132.iconWidth), (int) ((r62 + r82) - (r103 / 2.0f)), (int) ((r53 + r011) - (r28 / 2.0f)), (int) ((r62 + r82) + (r103 / 2.0f)));
        r10 = 0.0f;
        goto L98
    L99:
        r172 = 255.0f;
        r162 = 2.0f;
        goto L101
    L86:
        if (r132.useBlur == false) goto L88;
        r15 = (1.0f - r132.blurAlpha) * r142;
        r06.setAlpha((int) (r142 * 255.0f));
        goto L89
    L58:
        if (r132.useScale == false) goto L60;
        float r012 = AndroidUtilities.lerp(0.75f, 1.0f, r9);
        r20.scale(r012, r012, r132.arrowX, r132.arrowY);
        goto L60
    L54:
        r142 = 1.0f;
        goto L55
    L42:
        if (Math.abs(r32 - this.pathLastWidth) > 0.1f) goto L47;
        if (Math.abs(r4 - this.pathLastHeight) > 0.1f) goto L47;
        r132 = this;
        r33 = r4;
        goto L52
    L29:
        r22 = this.textDrawable.getHeight();
        goto L31
    L25:
        r12 = this.textDrawable.getCurrentWidth();
        goto L27
    L13:
        if (this.firstDraw == true) goto L15;
        r2 = true;
        goto L16
    L8:
        if (this.textLayout != null) goto L10;
    }

    public void drawBgPath(Canvas r6, float r7) {
        Paint r0 = this.blurBackgroundPaint;
        if (r0 == null) goto L5;
        r6.drawPath(this.path, r0);
    L5:
        int r02 = this.shadowColor;
        if (r02 == 0) goto L8;
        this.backgroundPaint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, Theme.multAlpha(r02, r7));
    L8:
        int r03 = this.backgroundPaint.getAlpha();
        this.backgroundPaint.setAlpha((int) (r03 * r7));
        r6.drawPath(this.path, this.backgroundPaint);
        this.backgroundPaint.setAlpha(r03);
        if (this.flicker == false) goto L12;
        int r72 = AndroidUtilities.dp(64.0f);
        float r04 = (-r72) + ((((System.currentTimeMillis() - this.flickerStart) % 4000) / 4000.0f) * ((this.pathLastWidth * 4.0f) + (r72 * 2)));
        this.flickerGradientMatrix.reset();
        this.flickerGradientMatrix.postTranslate(this.bounds.left + r04, 0.0f);
        this.flickerGradient.setLocalMatrix(this.flickerGradientMatrix);
        this.flickerStrokeGradient.setLocalMatrix(this.flickerGradientMatrix);
        r6.drawPath(this.path, this.flickerFillPaint);
        r6.drawPath(this.flickerStrokePath, this.flickerStrokePaint);
        invalidate();
        return;
    }

    public CharSequence getText() {
        CharSequence r0 = this.textToSet;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.multiline == false) goto L8;
        StaticLayout r1 = this.textLayout;
        if (r1 != null) goto L12;
        return null;
    L12:
        return r1.getText();
    L8:
        return this.textDrawable.getText();
    }

    public TextPaint getTextPaint() {
        if (this.multiline == false) goto L7;
        return this.textPaint;
    L7:
        return this.textDrawable.getPaint();
    }

    public void hide() {
        hide(true);
    }

    public void hide(boolean r4) {
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        Runnable r0 = this.onHidden;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        this.shown = false;
        if (r4 == true) goto L8;
        this.show.set(false, false);
    L8:
        invalidate();
        Runnable r42 = this.onHidden;
        if (r42 == null) goto L11;
        AndroidUtilities.runOnUIThread(r42, (long) (this.show.get() * this.show.getDuration()));
    L11:
        this.links.clear();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatedEmojiSpan.release(this, this.emojiGroupedSpans);
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        setMeasuredDimension(View.MeasureSpec.getSize(r3), View.MeasureSpec.getSize(r4));
        this.pathSet = false;
        int r42 = getTextMaxWidth();
        this.textDrawable.setOverrideFullWidth(r42);
        boolean r0 = this.multiline;
        CharSequence r1 = this.textToSet;
        if (r0 == false) goto L15;
        if (r1 != null) goto L9;
        StaticLayout r32 = this.textLayout;
        if (r32 == null) goto L14;
        r1 = r32.getText();
        goto L9
    L14:
        return;
    L9:
        StaticLayout r33 = this.textLayout;
        if (r33 != null) goto L12;
    L13:
        makeLayout(r1, r42);
    L17:
        this.textToSet = null;
        return;
    L12:
        if (r33.getWidth() == r42) goto L17;
    L15:
        if (r1 == null) goto L17;
        this.textDrawable.setText(r1, false);
        goto L17
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        if (this.hideByTouch == true) goto L7;
        if (hasOnClickListeners() == true) goto L7;
    L8:
        return false;
    L7:
        if (this.shown == false) goto L8;
        if (checkTouchLinks(r4) == false) goto L13;
        return true;
    L13:
        if (checkTouchTap(r4) == false) goto L15;
        return true;
    L15:
        return false;
    }

    public void pause() {
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
    }

    public HintView2 setAnimatedTextHacks(boolean r2, boolean r3, boolean r4) {
        this.textDrawable.setHacks(r2, r3, r4);
        return this;
    }

    public HintView2 setArrowSize(float r1, float r2) {
        this.arrowHalfWidth = AndroidUtilities.dpf2(r1);
        this.arrowHeight = AndroidUtilities.dpf2(r2);
        return this;
    }

    public HintView2 setBgColor(int r2) {
        if (this.backgroundPaint.getColor() == r2) goto L5;
        this.backgroundPaint.setColor(r2);
        invalidate();
    L5:
        return this;
    }

    public HintView2 setBounce(boolean r1) {
        this.repeatedBounce = r1;
        return this;
    }

    public HintView2 setCloseButton(boolean r5) {
        this.closeButton = r5;
        if (this.multiline == true) goto L8;
        RectF r52 = this.innerPadding;
        float r0 = 11.0f;
        float r1 = AndroidUtilities.dp(11.0f);
        float r2 = AndroidUtilities.dp(6.0f);
        if (this.closeButton == false) goto L7;
        r0 = 15.0f;
    L7:
        r52.set(r1, r2, AndroidUtilities.dp(r0), AndroidUtilities.dp(7.0f));
    L8:
        return this;
    }

    public HintView2 setDirection(int r1) {
        this.direction = r1;
        return this;
    }

    public HintView2 setDuration(long r1) {
        this.duration = r1;
        return this;
    }

    public HintView2 setFlicker(float r22, int r23) {
        this.flicker = true;
        this.flickerStart = System.currentTimeMillis();
        this.flickerStrokePath = new Path();
        this.flickerStrokePathExtrude = AndroidUtilities.dpf2(r22) / 2.0f;
        this.flickerFillPaint = new Paint(1);
        this.flickerStrokePaint = new Paint(1);
        Shader.TileMode r20 = Shader.TileMode.CLAMP;
        LinearGradient r4 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(64.0f), 0.0f, new int[]{Theme.multAlpha(r23, 0.0f), Theme.multAlpha(r23, 1.0f), Theme.multAlpha(r23, 0.0f)}, new float[]{0.0f, 0.5f, 1.0f}, r20);
        this.flickerStrokeGradient = r4;
        this.flickerStrokePaint.setShader(r4);
        this.flickerGradient = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(64.0f), 0.0f, new int[]{Theme.multAlpha(r23, 0.0f), Theme.multAlpha(r23, 0.5f), Theme.multAlpha(r23, 0.0f)}, new float[]{0.0f, 0.5f, 1.0f}, r20);
        this.flickerGradientMatrix = new Matrix();
        this.flickerFillPaint.setShader(this.flickerGradient);
        this.flickerStrokePaint.setStyle(Paint.Style.STROKE);
        this.flickerStrokePaint.setStrokeJoin(Paint.Join.ROUND);
        this.flickerStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        this.flickerStrokePaint.setStrokeWidth(AndroidUtilities.dpf2(r22));
        return this;
    }

    public HintView2 setHideByTouch(boolean r1) {
        this.hideByTouch = r1;
        return this;
    }

    public HintView2 setIcon(int r4) {
        RLottieDrawable r0 = new RLottieDrawable(r4, AndroidUtilities.dp(34.0f), AndroidUtilities.dp(34.0f));
        r0.start();
        return setIcon(r0);
    }

    public HintView2 setIcon(Drawable r5) {
        Drawable r0 = this.icon;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.icon = r5;
        if (r5 == null) goto L11;
        r5.setCallback(this);
        Drawable r52 = this.icon;
        if ((r52 instanceof RLottieDrawable) == false) goto L10;
        this.duration = Math.max(this.duration, ((RLottieDrawable) r52).getDuration());
    L10:
        this.iconWidth = this.icon.getIntrinsicWidth();
        this.iconHeight = this.icon.getIntrinsicHeight();
        this.iconLeft = true;
    L11:
        return this;
    }

    public HintView2 setIconMargin(int r1) {
        this.iconMargin = AndroidUtilities.dp(r1);
        return this;
    }

    public HintView2 setIconTranslate(float r1, float r2) {
        this.iconTx = r1;
        this.iconTy = r2;
        return this;
    }

    public HintView2 setInnerPadding(float r2, float r3, float r4, float r5) {
        this.innerPadding.set(AndroidUtilities.dpf2(r2), AndroidUtilities.dpf2(r3), AndroidUtilities.dpf2(r4), AndroidUtilities.dpf2(r5));
        return this;
    }

    public HintView2 setJoint(float r4, float r5) {
        if (Math.abs(this.joint - r4) < 1.0f) goto L5;
    L6:
        this.pathSet = false;
        invalidate();
    L7:
        this.joint = r4;
        this.jointTranslate = AndroidUtilities.dp(r5);
        return this;
    L5:
        if (Math.abs(this.jointTranslate - AndroidUtilities.dp(r5)) < 1.0f) goto L7;
        goto L6
    }

    public HintView2 setJointPx(float r3, float r4) {
        if (Math.abs(this.joint - r3) < 1.0f) goto L5;
    L6:
        this.pathSet = false;
        invalidate();
    L7:
        this.joint = r3;
        this.jointTranslate = r4;
        return this;
    L5:
        if (Math.abs(this.jointTranslate - r4) < 1.0f) goto L7;
        goto L6
    }

    public HintView2 setMaxWidth(float r1) {
        this.textMaxWidth = AndroidUtilities.dp(r1);
        return this;
    }

    public HintView2 setMaxWidthPx(int r1) {
        this.textMaxWidth = r1;
        return this;
    }

    public HintView2 setMultilineText(boolean r6) {
        this.multiline = r6;
        RectF r0 = this.innerPadding;
        float r2 = 15.0f;
        if (r6 == false) goto L6;
        r0.set(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(8.0f));
        this.closeButtonMargin = AndroidUtilities.dp(6.0f);
        return this;
    L6:
        float r3 = AndroidUtilities.dp(11.0f);
        float r1 = AndroidUtilities.dp(6.0f);
        if (this.closeButton == true) goto L10;
        r2 = 11.0f;
    L10:
        r0.set(r3, r1, AndroidUtilities.dp(r2), AndroidUtilities.dp(7.0f));
        this.closeButtonMargin = AndroidUtilities.dp(2.0f);
        return this;
    }

    public HintView2 setOnHiddenListener(Runnable r1) {
        this.onHidden = r1;
        return this;
    }

    public HintView2 setRounding(float r4) {
        this.rounding = AndroidUtilities.dp(r4);
        Paint r42 = this.backgroundPaint;
        CornerPathEffect r1 = null;
        if (this.roundWithCornerEffect == false) goto L5;
        CornerPathEffect r0 = new CornerPathEffect(this.rounding);
    L6:
        r42.setPathEffect(r0);
        Paint r43 = this.cutSelectorPaint;
        if (r43 != null) goto L9;
    L13:
        Paint r44 = this.blurBackgroundPaint;
        if (r44 != null) goto L16;
    L19:
        return this;
    L16:
        if (this.roundWithCornerEffect == false) goto L18;
        r1 = new CornerPathEffect(this.rounding);
    L18:
        r44.setPathEffect(r1);
        goto L19
    L9:
        if (this.roundWithCornerEffect == false) goto L11;
        CornerPathEffect r02 = new CornerPathEffect(this.rounding);
    L12:
        r43.setPathEffect(r02);
        goto L13
    L11:
        r02 = null;
        goto L12
    L5:
        r0 = null;
        goto L6
    }

    public HintView2 setRoundingWithCornerEffect(boolean r3) {
        this.roundWithCornerEffect = r3;
        Paint r0 = this.backgroundPaint;
        if (r3 == false) goto L5;
        CornerPathEffect r32 = new CornerPathEffect(this.rounding);
    L6:
        r0.setPathEffect(r32);
        return this;
    L5:
        r32 = null;
        goto L6
    }

    public HintView2 setSelectorColor(int r4) {
        Paint r0 = new Paint(1);
        this.cutSelectorPaint = r0;
        r0.setPathEffect(new CornerPathEffect(this.rounding));
        BaseCell.RippleDrawableSafe r42 = new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r4}), null, new AnonymousClass1(this));
        this.selectorDrawable = r42;
        r42.setCallback(this);
        return this;
    }

    public HintView2 setShadow(float r2, float r3, float r4, int r5) {
        Paint r0 = this.backgroundPaint;
        this.shadowRadius = r2;
        this.shadowDx = r3;
        this.shadowDy = r4;
        this.shadowColor = r5;
        r0.setShadowLayer(r2, r3, r4, r5);
        return this;
    }

    public HintView2 setText(CharSequence r3) {
        if (getMeasuredWidth() >= 0) goto L7;
        this.textToSet = r3;
        return this;
    L7:
        if (this.multiline == true) goto L10;
        this.textDrawable.setText(r3, false);
        return this;
    L10:
        makeLayout(r3, getTextMaxWidth());
        return this;
    }

    public HintView2 setText(CharSequence r3, boolean r4) {
        if (getMeasuredWidth() >= 0) goto L6;
        this.textToSet = r3;
        return this;
    L6:
        AnimatedTextView.AnimatedTextDrawable r0 = this.textDrawable;
        if (LocaleController.isRTL == true) goto L10;
        if (r4 == false) goto L10;
        boolean r42 = true;
    L11:
        r0.setText(r3, r42);
        return this;
    L10:
        r42 = false;
        goto L11
    }

    public HintView2 setTextAlign(Layout.Alignment r1) {
        this.textLayoutAlignment = r1;
        return this;
    }

    public HintView2 setTextColor(int r2) {
        this.textDrawable.setTextColor(r2);
        this.textPaint.setColor(r2);
        return this;
    }

    public HintView2 setTextSize(float r3) {
        this.textDrawable.setTextSize(AndroidUtilities.dpf2(r3));
        this.textPaint.setTextSize(AndroidUtilities.dpf2(r3));
        return this;
    }

    public HintView2 setTextTypeface(Typeface r2) {
        this.textDrawable.setTypeface(r2);
        this.textPaint.setTypeface(r2);
        return this;
    }

    public HintView2 show() {
        prepareBlur();
        if (this.shown == false) goto L5;
        bounceShow();
    L5:
        AndroidUtilities.makeAccessibilityAnnouncement(getText());
        this.shown = true;
        invalidate();
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        long r0 = this.duration;
        if (r0 <= 0) goto L8;
        AndroidUtilities.runOnUIThread(this.hideRunnable, r0);
    L8:
        Runnable r02 = this.onHidden;
        if (r02 == null) goto L11;
        AndroidUtilities.cancelRunOnUIThread(r02);
    L11:
        return this;
    }

    public void show(boolean r1) {
        if (r1 == false) goto L5;
        show();
        return;
    L5:
        hide();
    }

    public boolean shown() {
        return this.shown;
    }

    public void unpause() {
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        long r0 = this.duration;
        if (r0 <= 0) goto L6;
        AndroidUtilities.runOnUIThread(this.hideRunnable, r0);
        return;
    }

    public HintView2 useScale(boolean r1) {
        this.useScale = r1;
        return this;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.textDrawable) goto L5;
        return true;
    L5:
        if (r2 != this.selectorDrawable) goto L7;
        return true;
    L7:
        if (r2 != this.icon) goto L9;
        return true;
    L9:
        if (super.verifyDrawable(r2) == true) goto L17;
        return false;
    L17:
        return true;
    }
}
