package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.Switch;

/* loaded from: classes6.dex */
public class TextCheckCell extends FrameLayout {
    public static final Property<TextCheckCell, Float> ANIMATION_PROGRESS = null;
    private int animatedColorBackground;
    private Paint animationPaint;
    private float animationProgress;
    private ObjectAnimator animator;
    boolean attached;
    private Switch checkBox;
    public boolean drawCheckRipple;
    private int height;
    private RLottieImageView imageView;
    private boolean isAnimatingToThumbInsteadOfTouch;
    private boolean isMultiline;
    private boolean isRTL;
    public int itemId;
    private float lastTouchX;
    private boolean needDivider;
    private int padding;
    private Theme.ResourcesProvider resourcesProvider;
    private TextView textView;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.TextCheckCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<TextCheckCell> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(TextCheckCell r1) {
            return Float.valueOf(TextCheckCell.m7056$$Nest$fgetanimationProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((TextCheckCell) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(TextCheckCell r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(TextCheckCell r1, float r2) {
            TextCheckCell.m7058$$Nest$msetAnimationProgress(r1, r2);
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextCheckCell$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ TextCheckCell this$0;
        final /* synthetic */ int val$color;

        public AnonymousClass2(TextCheckCell r1, int r2) {
            this.this$0 = r1;
            this.val$color = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            TextCheckCell.m7057$$Nest$fputanimatedColorBackground(this.this$0, 0);
            this.this$0.setBackgroundColor(this.val$color);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextCheckCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ TextCheckCell this$0;
        final /* synthetic */ int val$color;

        public AnonymousClass3(TextCheckCell r1, int r2) {
            this.this$0 = r1;
            this.val$color = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setBackgroundColor(this.val$color);
            TextCheckCell.m7057$$Nest$fputanimatedColorBackground(this.this$0, 0);
            this.this$0.invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetanimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7056$$Nest$fgetanimationProgress(TextCheckCell r0) {
        return r0.animationProgress;
    }

    /* renamed from: -$$Nest$fputanimatedColorBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7057$$Nest$fputanimatedColorBackground(TextCheckCell r0, int r1) {
        r0.animatedColorBackground = r1;
    }

    /* renamed from: -$$Nest$msetAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7058$$Nest$msetAnimationProgress(TextCheckCell r0, float r1) {
        r0.setAnimationProgress(r1);
    }

    static {
        ANIMATION_PROGRESS = new AnonymousClass1("animationProgress");
    }

    public TextCheckCell(Context r2) {
        this(r2, 21);
    }

    public TextCheckCell(Context r3, int r4) {
        this(r3, r4, false, null);
    }

    public TextCheckCell(Context r2, int r3, boolean r4) {
        this(r2, r3, r4, null);
    }

    public TextCheckCell(Context r19, int r20, boolean r21, Theme.ResourcesProvider r22) {
        super(r19);
        this.height = 50;
        this.resourcesProvider = r22;
        this.padding = r20;
        TextView r4 = new TextView(r19);
        this.textView = r4;
        if (r21 == false) goto L5;
        int r5 = Theme.key_dialogTextBlack;
    L6:
        r4.setTextColor(Theme.getColor(r5, r22));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r42 = this.textView;
        int r7 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r52 = 5;
    L10:
        r42.setGravity(r52 | 16);
        TextView r43 = this.textView;
        TextUtils.TruncateAt r53 = TextUtils.TruncateAt.END;
        r43.setEllipsize(r53);
        TextView r44 = this.textView;
        boolean r9 = LocaleController.isRTL;
        if (r9 == false) goto L13;
        int r10 = 5;
    L14:
        int r13 = r10 | 48;
        float r102 = 70.0f;
        if (r9 == false) goto L17;
        float r14 = 70.0f;
    L18:
        if (r9 == false) goto L20;
        float r16 = r20;
    L21:
        addView(r44, LayoutHelper.createFrame(-1, -1.0f, r13, r14, 0.0f, r16, 0.0f));
        TextView r45 = new TextView(r19);
        this.valueTextView = r45;
        if (r21 == false) goto L24;
        int r92 = Theme.key_dialogIcon;
    L25:
        r45.setTextColor(Theme.getColor(r92, r22));
        this.valueTextView.setTypeface(AndroidUtilities.regular());
        this.valueTextView.setTextSize(1, 13.0f);
        TextView r46 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L28;
        int r93 = 5;
    L29:
        r46.setGravity(r93);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setEllipsize(r53);
        TextView r47 = this.valueTextView;
        boolean r54 = LocaleController.isRTL;
        if (r54 == false) goto L32;
        int r94 = 5;
    L33:
        int r132 = r94 | 48;
        if (r54 == false) goto L36;
        float r142 = 70.0f;
    L37:
        if (r54 == false) goto L39;
        r102 = r20;
    L39:
        addView(r47, LayoutHelper.createFrame(-2, -2.0f, r132, r142, 35.0f, r102, 0.0f));
        Switch r2 = new Switch(r19, r22);
        this.checkBox = r2;
        int r1 = Theme.key_switchTrack;
        int r3 = Theme.key_switchTrackChecked;
        int r48 = Theme.key_windowBackgroundWhite;
        r2.setColors(r1, r3, r48, r48);
        Switch r12 = this.checkBox;
        if (LocaleController.isRTL == true) goto L43;
        r7 = 5;
    L43:
        addView(r12, LayoutHelper.createFrame(37, 20.0f, r7 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        setClipChildren(false);
        this.isRTL = LocaleController.isRTL;
        return;
    L36:
        r142 = r20;
        goto L37
    L32:
        r94 = 3;
        goto L33
    L28:
        r93 = 3;
        goto L29
    L24:
        r92 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L25
    L20:
        r16 = 70.0f;
        goto L21
    L17:
        r14 = r20;
        goto L18
    L13:
        r10 = 3;
        goto L14
    L9:
        r52 = 3;
        goto L10
    L5:
        r5 = Theme.key_windowBackgroundWhiteBlackText;
        goto L6
    }

    public TextCheckCell(Context r3, Theme.ResourcesProvider r4) {
        this(r3, 21, false, r4);
    }

    private float getLastTouchX() {
        if (this.isAnimatingToThumbInsteadOfTouch == false) goto L11;
        if (LocaleController.isRTL == false) goto L7;
        int r1 = AndroidUtilities.dp(22.0f);
    L9:
        return r1;
    L7:
        r1 = getMeasuredWidth() - AndroidUtilities.dp(42.0f);
        goto L9
    L11:
        return this.lastTouchX;
    }

    private void setAnimationProgress(float r4) {
        this.animationProgress = r4;
        float r42 = getLastTouchX();
        float r0 = Math.max(r42, getMeasuredWidth() - r42) + AndroidUtilities.dp(40.0f);
        this.checkBox.setOverrideColorProgress(r42, getMeasuredHeight() / 2, r0 * this.animationProgress);
    }

    public Switch getCheckBox() {
        return this.checkBox;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.animatedColorBackground == 0) goto L6;
        float r0 = getLastTouchX();
        float r1 = Math.max(r0, getMeasuredWidth() - r0) + AndroidUtilities.dp(40.0f);
        r8.drawCircle(r0, getMeasuredHeight() / 2, r1 * this.animationProgress, this.animationPaint);
    L6:
        if (this.needDivider == false) goto L30;
        Theme.ResourcesProvider r02 = this.resourcesProvider;
        if (r02 == null) goto L11;
        Paint r03 = r02.getPaint("paintDivider");
    L10:
        Paint r6 = r03;
        if (r6 == null) goto L31;
        RLottieImageView r04 = this.imageView;
        int r12 = 0;
        if (r04 == null) goto L22;
        if (r04.getVisibility() != 0) goto L22;
        if (LocaleController.isRTL == false) goto L20;
    L19:
        int r05 = 0;
    L25:
        int r2 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L28;
        r12 = r05;
    L28:
        r8.drawLine(r05, getMeasuredHeight() - 1, r2 - r12, getMeasuredHeight() - 1, r6);
        return;
    L20:
        r05 = this.padding - AndroidUtilities.dp(3.0f);
    L22:
        if (LocaleController.isRTL == true) goto L19;
        r05 = AndroidUtilities.dp(20.0f);
        goto L25
    L31:
        return;
    L11:
        r03 = Theme.dividerPaint;
        goto L10
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setClassName("android.widget.Switch");
        r3.setCheckable(true);
        r3.setChecked(this.checkBox.isChecked());
        StringBuilder r0 = new StringBuilder();
        r0.append(this.textView.getText());
        if (TextUtils.isEmpty(this.valueTextView.getText()) == true) goto L5;
        r0.append('\n');
        r0.append(this.valueTextView.getText());
    L5:
        r3.setContentDescription(r0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.isMultiline == false) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        return;
    L6:
        int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        if (this.valueTextView.getVisibility() != 0) goto L9;
        float r42 = 64.0f;
    L10:
        super.onMeasure(r32, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r42) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L9:
        r42 = this.height;
        goto L10
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        this.lastTouchX = r2.getX();
        return super.onTouchEvent(r2);
    }

    public void reset() {
        this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setVisibility(8);
        RLottieImageView r0 = this.imageView;
        if (r0 == null) goto L5;
        r0.setVisibility(8);
    L5:
        RLottieImageView r02 = this.imageView;
        if (r02 == null) goto L8;
        r02.setImageDrawable(null);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        this.imageView.setBackground(null);
    L8:
        this.checkBox.setIcon(0);
        this.isMultiline = false;
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        r03.height = -1;
        r03.topMargin = 0;
        this.textView.setLayoutParams(r03);
        int r04 = AndroidUtilities.dp(21.0f);
        this.padding = r04;
        ViewGroup.MarginLayoutParams r1 = (ViewGroup.MarginLayoutParams) this.textView.getLayoutParams();
        boolean r2 = LocaleController.isRTL;
        if (r2 == false) goto L11;
        int r3 = r1.leftMargin;
    L12:
        r1.leftMargin = r3;
        if (r2 == false) goto L15;
        int r22 = r04;
    L16:
        r1.rightMargin = r22;
        ViewGroup.MarginLayoutParams r5 = (ViewGroup.MarginLayoutParams) this.valueTextView.getLayoutParams();
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L19;
        int r23 = r5.leftMargin;
    L20:
        r5.leftMargin = r23;
        if (r12 == true) goto L24;
        r04 = r5.rightMargin;
    L24:
        r5.rightMargin = r04;
        return;
    L19:
        r23 = r04;
        goto L20
    L15:
        r22 = r1.rightMargin;
        goto L16
    L11:
        r3 = r04;
        goto L12
    }

    public void setAnimatingToThumbInsteadOfTouch(boolean r1) {
        this.isAnimatingToThumbInsteadOfTouch = r1;
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        if (this.animatedColorBackground == r2) goto L6;
        clearAnimation();
        this.animatedColorBackground = 0;
        super.setBackgroundColor(r2);
        return;
    }

    public void setBackgroundColorAnimated(boolean r4, int r5) {
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.animator = null;
    L5:
        int r02 = this.animatedColorBackground;
        if (r02 == 0) goto L8;
        setBackgroundColor(r02);
    L8:
        int r1 = 1;
        if (this.animationPaint != null) goto L11;
        this.animationPaint = new Paint(1);
    L11:
        Switch r03 = this.checkBox;
        if (r4 == true) goto L15;
        r1 = 2;
    L15:
        r03.setOverrideColor(r1);
        this.animatedColorBackground = r5;
        this.animationPaint.setColor(r5);
        this.animationProgress = 0.0f;
        ObjectAnimator r42 = ObjectAnimator.ofFloat(this, ANIMATION_PROGRESS, new float[]{0.0f, 1.0f});
        this.animator = r42;
        r42.addListener(new AnonymousClass2(this, r5));
        this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.animator.setDuration(240).start();
    }

    public void setBackgroundColorAnimatedReverse(int r4) {
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.animator = null;
    L5:
        int r02 = this.animatedColorBackground;
        if (r02 != 0) goto L13;
        if ((getBackground() instanceof ColorDrawable) == false) goto L11;
        r02 = ((ColorDrawable) getBackground()).getColor();
        goto L13
    L11:
        r02 = 0;
    L13:
        if (this.animationPaint != null) goto L15;
        this.animationPaint = new Paint(1);
    L15:
        this.animationPaint.setColor(r02);
        setBackgroundColor(r4);
        this.checkBox.setOverrideColor(1);
        this.animatedColorBackground = r4;
        ObjectAnimator r03 = ObjectAnimator.ofFloat(this, ANIMATION_PROGRESS, new float[]{1.0f, 0.0f}).setDuration(240);
        this.animator = r03;
        r03.addListener(new AnonymousClass3(this, r4));
        this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.animator.start();
    }

    public void setCheckBoxIcon(int r1) {
        this.checkBox.setIcon(r1);
    }

    public void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public void setColorfullIcon(int r10, int r11) {
        if (this.imageView != null) goto L16;
        RLottieImageView r0 = new RLottieImageView(getContext());
        this.imageView = r0;
        r0.setScaleType(ImageView.ScaleType.CENTER);
        RLottieImageView r02 = this.imageView;
        if (LocaleController.isRTL == false) goto L7;
        int r1 = 5;
    L8:
        addView(r02, LayoutHelper.createFrame(29, 29.0f, r1 | 16, 19.0f, 0.0f, 19.0f, 0.0f));
        this.padding = AndroidUtilities.dp(65.0f);
        ViewGroup.MarginLayoutParams r03 = (ViewGroup.MarginLayoutParams) this.textView.getLayoutParams();
        int r2 = 70;
        if (LocaleController.isRTL == false) goto L11;
        int r12 = 70;
    L12:
        r03.leftMargin = r12;
        ViewGroup.MarginLayoutParams r04 = (ViewGroup.MarginLayoutParams) this.textView.getLayoutParams();
        if (LocaleController.isRTL == false) goto L15;
        r2 = this.padding;
    L15:
        r04.rightMargin = r2;
        goto L16
    L11:
        r12 = this.padding;
        goto L12
    L7:
        r1 = 3;
    L16:
        this.imageView.setVisibility(0);
        this.imageView.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        this.imageView.setImageResource(r11);
        RLottieImageView r112 = this.imageView;
        if (Theme.isCurrentThemeMonet() == false) goto L19;
        int r13 = Theme.getColor(Theme.key_chats_actionIcon);
    L20:
        r112.setColorFilter(new PorterDuffColorFilter(r13, PorterDuff.Mode.SRC_IN));
        RLottieImageView r113 = this.imageView;
        int r05 = AndroidUtilities.dp(9.0f);
        if (Theme.isCurrentThemeMonet() == false) goto L23;
        r10 = Theme.getColor(Theme.key_chats_actionBackground);
    L23:
        r113.setBackground(Theme.createRoundRectDrawable(r05, r10));
        RLottieImageView r102 = this.imageView;
        if (isEnabled() == false) goto L26;
        float r9 = 1.0f;
    L27:
        r102.setAlpha(r9);
        return;
    L26:
        r9 = 0.5f;
        goto L27
    L19:
        r13 = -1;
        goto L20
    }

    public void setColors(int r3, int r4, int r5, int r6, int r7) {
        this.textView.setTextColor(Theme.getColor(r3, this.resourcesProvider));
        this.checkBox.setColors(r4, r5, r6, r7);
        this.textView.setTag(Integer.valueOf(r3));
    }

    public void setDivider(boolean r1) {
        this.needDivider = r1;
        setWillNotDraw(!r1);
    }

    public void setDrawCheckRipple(boolean r2) {
        this.drawCheckRipple = r2;
        int r0 = RecyclerListView.TAG_ROUND_SECTION;
        if (r2 == false) goto L5;
        Boolean r22 = Boolean.TRUE;
    L6:
        setTag(r0, r22);
        return;
    L5:
        r22 = null;
        goto L6
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        super.setEnabled(r2);
        this.checkBox.setEnabled(r2);
        RLottieImageView r1 = this.imageView;
        if (r1 == null) goto L9;
        if (r2 == false) goto L6;
        float r22 = 1.0f;
    L7:
        r1.setAlpha(r22);
        return;
    L6:
        r22 = 0.5f;
        goto L7
    }

    public void setEnabled(boolean r9, ArrayList<Animator> r10) {
        super.setEnabled(r9);
        TextView r0 = this.textView;
        float r1 = 0.5f;
        if (r10 == null) goto L27;
        if (r9 == false) goto L6;
        float r3 = 1.0f;
    L7:
        float[] r5 = {r3};
        Property r32 = View.ALPHA;
        r10.add(ObjectAnimator.ofFloat(r0, r32, r5));
        Switch r02 = this.checkBox;
        if (r9 == false) goto L10;
        float r52 = 1.0f;
    L11:
        r10.add(ObjectAnimator.ofFloat(r02, r32, new float[]{r52}));
        if (this.valueTextView.getVisibility() != 0) goto L18;
        TextView r03 = this.valueTextView;
        if (r9 == false) goto L16;
        float r53 = 1.0f;
    L17:
        r10.add(ObjectAnimator.ofFloat(r03, r32, new float[]{r53}));
        goto L18
    L16:
        r53 = 0.5f;
    L18:
        RLottieImageView r04 = this.imageView;
        if (r04 != null) goto L21;
        return;
    L21:
        if (r04.getVisibility() != 0) goto L51;
        RLottieImageView r8 = this.imageView;
        if (r9 == false) goto L25;
        r1 = 1.0f;
    L25:
        r10.add(ObjectAnimator.ofFloat(r8, r32, new float[]{r1}));
        return;
    L51:
        return;
    L10:
        r52 = 0.5f;
        goto L11
    L6:
        r3 = 0.5f;
        goto L7
    L27:
        if (r9 == false) goto L29;
        float r102 = 1.0f;
    L30:
        r0.setAlpha(r102);
        Switch r103 = this.checkBox;
        if (r9 == false) goto L33;
        float r05 = 1.0f;
    L34:
        r103.setAlpha(r05);
        if (this.valueTextView.getVisibility() != 0) goto L41;
        TextView r104 = this.valueTextView;
        if (r9 == false) goto L39;
        float r06 = 1.0f;
    L40:
        r104.setAlpha(r06);
        goto L41
    L39:
        r06 = 0.5f;
    L41:
        RLottieImageView r105 = this.imageView;
        if (r105 != null) goto L44;
        return;
    L44:
        if (r105.getVisibility() != 0) goto L53;
        RLottieImageView r82 = this.imageView;
        if (r9 == false) goto L48;
        r1 = 1.0f;
    L48:
        r82.setAlpha(r1);
        return;
    L53:
        return;
    L33:
        r05 = 0.5f;
        goto L34
    L29:
        r102 = 0.5f;
        goto L30
    }

    public void setHeight(int r1) {
        this.height = r1;
    }

    public void setIcon(int r10) {
        if (this.imageView != null) goto L9;
        RLottieImageView r0 = new RLottieImageView(getContext());
        this.imageView = r0;
        r0.setScaleType(ImageView.ScaleType.CENTER);
        RLottieImageView r02 = this.imageView;
        if (LocaleController.isRTL == false) goto L7;
        int r1 = 5;
    L8:
        addView(r02, LayoutHelper.createFrame(24, 24.0f, r1 | 16, 21.0f, 0.0f, 21.0f, 0.0f));
        goto L9
    L7:
        r1 = 3;
    L9:
        int r03 = AndroidUtilities.dp(71.0f);
        this.padding = r03;
        ViewGroup.MarginLayoutParams r12 = (ViewGroup.MarginLayoutParams) this.textView.getLayoutParams();
        boolean r2 = LocaleController.isRTL;
        if (r2 == false) goto L12;
        int r3 = r12.leftMargin;
    L13:
        r12.leftMargin = r3;
        if (r2 == false) goto L16;
        int r22 = r03;
    L17:
        r12.rightMargin = r22;
        ViewGroup.MarginLayoutParams r13 = (ViewGroup.MarginLayoutParams) this.valueTextView.getLayoutParams();
        boolean r23 = LocaleController.isRTL;
        if (r23 == false) goto L20;
        int r32 = r13.leftMargin;
    L21:
        r13.leftMargin = r32;
        if (r23 == true) goto L25;
        r03 = r13.rightMargin;
    L25:
        r13.rightMargin = r03;
        this.imageView.setVisibility(0);
        this.imageView.setImageResource(r10);
        this.imageView.setPadding(0, 0, 0, 0);
        this.imageView.setBackground(null);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        RLottieImageView r102 = this.imageView;
        if (isEnabled() == false) goto L28;
        float r9 = 1.0f;
    L29:
        r102.setAlpha(r9);
        return;
    L28:
        r9 = 0.5f;
        goto L29
    L20:
        r32 = r03;
        goto L21
    L16:
        r22 = r12.rightMargin;
        goto L17
    L12:
        r3 = r03;
        goto L13
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        if (this.drawCheckRipple == false) goto L5;
        this.checkBox.setDrawRipple(r2);
    L5:
        super.setPressed(r2);
    }

    public void setTextAndCheck(CharSequence r4, boolean r5, boolean r6) {
        RLottieImageView r0 = this.imageView;
        if (r0 == null) goto L5;
        r0.setVisibility(8);
    L5:
        AvatarSpan.checkSpansParent(r4, this);
        this.textView.setText(r4);
        this.isMultiline = false;
        this.checkBox.setVisibility(0);
        this.checkBox.setChecked(r5, this.attached);
        this.needDivider = r6;
        this.valueTextView.setVisibility(8);
        FrameLayout.LayoutParams r52 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        r52.height = -1;
        r52.topMargin = 0;
        this.textView.setLayoutParams(r52);
        setWillNotDraw(!r6);
    }

    public void setTextAndValueAndCheck(CharSequence r4, String r5, boolean r6, boolean r7, boolean r8) {
        TextView r0 = this.valueTextView;
        CharSequence r52 = r5;
        if (r5 == null) goto L7;
        boolean r1 = r5.contains("->");
        r52 = r5;
        if (r1 == false) goto L7;
        r52 = LocaleUtils.replaceArrows(getContext(), r5, R.drawable.search_arrow);
    L7:
        r0.setText(r52);
        AvatarSpan.checkSpansParent(r4, this);
        this.textView.setText(r4);
        this.checkBox.setVisibility(0);
        this.checkBox.setChecked(r6, false);
        this.needDivider = r8;
        this.valueTextView.setVisibility(0);
        this.isMultiline = r7;
        TextView r42 = this.valueTextView;
        if (r7 == false) goto L10;
        r42.setLines(0);
        this.valueTextView.setMaxLines(0);
        this.valueTextView.setSingleLine(false);
        this.valueTextView.setEllipsize(null);
        this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(11.0f));
    L11:
        FrameLayout.LayoutParams r43 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        r43.height = -2;
        r43.topMargin = AndroidUtilities.dp(10.0f);
        this.textView.setLayoutParams(r43);
        setWillNotDraw(!r8);
        return;
    L10:
        r42.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.valueTextView.setPadding(0, 0, 0, 0);
        goto L11
    }

    public void setTypeface(Typeface r1) {
        this.textView.setTypeface(r1);
    }

    public void updateRTL() {
        boolean r0 = this.isRTL;
        boolean r1 = LocaleController.isRTL;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.isRTL = r1;
        View r02 = this.imageView;
        int r12 = 3;
        if (r02 == null) goto L12;
        removeView(r02);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.imageView.getLayoutParams();
        if (this.isRTL == false) goto L10;
        int r3 = 5;
    L11:
        r03.gravity = r3 | 16;
        addView(this.imageView, r03);
        goto L12
    L10:
        r3 = 3;
    L12:
        RLottieImageView r04 = this.imageView;
        if (r04 != null) goto L15;
    L17:
        int r05 = this.padding;
    L18:
        removeView(this.textView);
        TextView r32 = this.textView;
        if (this.isRTL == false) goto L21;
        int r4 = 5;
    L22:
        r32.setGravity(r4 | 16);
        View r33 = this.textView;
        boolean r42 = this.isRTL;
        if (r42 == false) goto L25;
        int r5 = 5;
    L26:
        int r8 = r5 | 48;
        float r52 = 70.0f;
        if (r42 == false) goto L29;
        float r9 = 70.0f;
    L30:
        if (r42 == false) goto L32;
        float r11 = r05;
    L33:
        addView(r33, LayoutHelper.createFrame(-1, -1.0f, r8, r9, 0.0f, r11, 0.0f));
        removeView(this.valueTextView);
        TextView r34 = this.valueTextView;
        if (this.isRTL == false) goto L36;
        int r43 = 5;
    L37:
        r34.setGravity(r43);
        View r35 = this.valueTextView;
        boolean r44 = this.isRTL;
        if (r44 == false) goto L40;
        int r6 = 5;
    L41:
        int r92 = r6 | 48;
        if (r44 == false) goto L44;
        float r10 = 70.0f;
    L45:
        if (r44 == false) goto L47;
        r52 = r05;
    L47:
        addView(r35, LayoutHelper.createFrame(-2, -2.0f, r92, r10, 35.0f, r52, 0.0f));
        removeView(this.checkBox);
        View r06 = this.checkBox;
        if (this.isRTL == true) goto L51;
        r12 = 5;
    L51:
        addView(r06, LayoutHelper.createFrame(37, 20.0f, r12 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        return;
    L44:
        r10 = r05;
        goto L45
    L40:
        r6 = 3;
        goto L41
    L36:
        r43 = 3;
        goto L37
    L32:
        r11 = 70.0f;
        goto L33
    L29:
        r9 = r05;
        goto L30
    L25:
        r5 = 3;
        goto L26
    L21:
        r4 = 3;
        goto L22
    L15:
        if (r04.getVisibility() != 0) goto L17;
        r05 = 68;
        goto L18
    }
}
