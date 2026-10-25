package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;

@Deprecated
/* loaded from: classes7.dex */
public class HintView extends FrameLayout {
    private AnimatorSet animatorSet;
    public ImageView arrowImageView;
    private int backgroundColor;
    Paint backgroundPaint;
    private int bottomOffset;
    private int currentType;
    private View currentView;
    private boolean drawPath;
    private float extraTranslationY;
    private boolean hasCloseButton;
    private Runnable hideRunnable;
    private ImageView imageView;
    private boolean isTopArrow;
    private ChatMessageCell messageCell;
    private String overrideText;
    Path path;
    private final Theme.ResourcesProvider resourcesProvider;
    private long showingDuration;
    private int shownY;
    public TextView textView;
    private float translationY;
    private boolean useScale;

    /* renamed from: org.telegram.ui.Components.HintView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ HintView this$0;

        public static /* synthetic */ void $r8$lambda$vSm2pMDP79p0Bd9t4prk4BLLM0Q(AnonymousClass1 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass1(HintView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            this.this$0.hide();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            HintView.m10907$$Nest$fputanimatorSet(this.this$0, null);
            if (HintView.m10905$$Nest$fgethasCloseButton(this.this$0) == true) goto L10;
            HintView r22 = this.this$0;
            HintView$1$$ExternalSyntheticLambda0 r0 = new HintView$1$$ExternalSyntheticLambda0(this);
            HintView.m10909$$Nest$fputhideRunnable(r22, r0);
            if (HintView.m10904$$Nest$fgetcurrentType(this.this$0) != 0) goto L7;
            long r1 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        L8:
            AndroidUtilities.runOnUIThread(r0, r1);
            return;
        L7:
            r1 = 2000;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.HintView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ HintView this$0;

        /* renamed from: $r8$lambda$4GU-85FdRSB97xnE9-3bIKgiPa8, reason: not valid java name */
        public static /* synthetic */ void m10911$r8$lambda$4GU85FdRSB97xnE93bIKgiPa8(AnonymousClass2 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass2(HintView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            this.this$0.hide();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            HintView.m10907$$Nest$fputanimatorSet(this.this$0, null);
            if (HintView.m10905$$Nest$fgethasCloseButton(this.this$0) == true) goto L6;
            HintView r22 = this.this$0;
            HintView$2$$ExternalSyntheticLambda0 r0 = new HintView$2$$ExternalSyntheticLambda0(this);
            HintView.m10909$$Nest$fputhideRunnable(r22, r0);
            AndroidUtilities.runOnUIThread(r0, HintView.m10906$$Nest$fgetshowingDuration(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.HintView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ HintView this$0;

        public AnonymousClass3(HintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setVisibility(4);
            this.this$0.getClass();
            HintView.m10908$$Nest$fputcurrentView(this.this$0, null);
            HintView.m10910$$Nest$fputmessageCell(this.this$0, null);
            HintView.m10907$$Nest$fputanimatorSet(this.this$0, null);
        }
    }

    public interface VisibilityListener {
    }

    public static /* synthetic */ void $r8$lambda$uZ2WgmvWQonSIm3FdKFwOw2Yerc(HintView r0, View r1) {
        r0.lambda$createCloseButton$0(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10904$$Nest$fgetcurrentType(HintView r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgethasCloseButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10905$$Nest$fgethasCloseButton(HintView r0) {
        return r0.hasCloseButton;
    }

    /* renamed from: -$$Nest$fgetshowingDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m10906$$Nest$fgetshowingDuration(HintView r2) {
        return r2.showingDuration;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10907$$Nest$fputanimatorSet(HintView r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputcurrentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10908$$Nest$fputcurrentView(HintView r0, View r1) {
        r0.currentView = r1;
    }

    /* renamed from: -$$Nest$fputhideRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10909$$Nest$fputhideRunnable(HintView r0, Runnable r1) {
        r0.hideRunnable = r1;
    }

    /* renamed from: -$$Nest$fputmessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10910$$Nest$fputmessageCell(HintView r0, ChatMessageCell r1) {
        r0.messageCell = r1;
    }

    public HintView(Context r3, int r4) {
        this(r3, r4, false, null);
    }

    public HintView(Context r2, int r3, Theme.ResourcesProvider r4) {
        this(r2, r3, false, r4);
    }

    public HintView(Context r2, int r3, boolean r4) {
        this(r2, r3, r4, null);
    }

    public HintView(Context r18, int r19, boolean r20, Theme.ResourcesProvider r21) {
        super(r18);
        this.showingDuration = 2000;
        this.resourcesProvider = r21;
        this.currentType = r19;
        this.isTopArrow = r20;
        CorrectlyMeasuringTextView r4 = new CorrectlyMeasuringTextView(r18);
        this.textView = r4;
        int r5 = Theme.key_chat_gifSaveHintText;
        r4.setTextColor(getThemedColor(r5));
        this.textView.setTextSize(1, 14.0f);
        this.textView.setMaxLines(2);
        if (r19 != 7) goto L5;
    L13:
        this.textView.setMaxWidth(AndroidUtilities.dp(310.0f));
    L14:
        int r42 = this.currentType;
        TextView r6 = this.textView;
        if (r42 != 3) goto L24;
        r6.setGravity(19);
        this.textView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(5.0f), getThemedColor(Theme.key_chat_gifSaveHintBackground)));
        this.textView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        TextView r43 = this.textView;
        if (r20 == false) goto L19;
        float r14 = 6.0f;
    L20:
        if (r20 == false) goto L22;
        float r16 = 0.0f;
    L23:
        addView(r43, LayoutHelper.createFrame(-2, 30.0f, 51, 0.0f, r14, 0.0f, r16));
    L36:
        if (r19 != 0) goto L38;
        this.textView.setText(LocaleController.getString(R.string.AutoplayVideoInfo));
        ImageView r2 = new ImageView(r18);
        this.imageView = r2;
        r2.setImageResource(R.drawable.tooltip_sound);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(r5), PorterDuff.Mode.MULTIPLY));
        addView(this.imageView, LayoutHelper.createFrame(38, 34.0f, 51, 7.0f, 7.0f, 0.0f, 0.0f));
    L38:
        ImageView r22 = new ImageView(r18);
        this.arrowImageView = r22;
        if (r20 == false) goto L41;
        int r1 = R.drawable.tooltip_arrow_up;
    L42:
        r22.setImageResource(r1);
        this.arrowImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_gifSaveHintBackground), PorterDuff.Mode.MULTIPLY));
        ImageView r12 = this.arrowImageView;
        if (r20 == false) goto L45;
        int r23 = 48;
    L46:
        addView(r12, LayoutHelper.createFrame(14, 6.0f, r23 | 3, 0.0f, 0.0f, 0.0f, 0.0f));
        return;
    L45:
        r23 = 80;
        goto L46
    L41:
        r1 = R.drawable.tooltip_arrow;
        goto L42
    L22:
        r16 = 6.0f;
        goto L23
    L19:
        r14 = 0.0f;
        goto L20
    L24:
        r6.setGravity(51);
        this.textView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), getThemedColor(Theme.key_chat_gifSaveHintBackground)));
        TextView r44 = this.textView;
        if (this.currentType != 0) goto L27;
        float r62 = 54.0f;
    L28:
        r44.setPadding(AndroidUtilities.dp(r62), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(10.0f));
        TextView r45 = this.textView;
        if (r20 == false) goto L31;
        float r142 = 6.0f;
    L32:
        if (r20 == false) goto L34;
        float r162 = 0.0f;
    L35:
        addView(r45, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, r142, 0.0f, r162));
        goto L36
    L34:
        r162 = 6.0f;
        goto L35
    L31:
        r142 = 0.0f;
        goto L32
    L27:
        r62 = 12.0f;
        goto L28
    L5:
        if (r19 == 8) goto L13;
        if (r19 == 9) goto L13;
        TextView r46 = this.textView;
        if (r19 != 4) goto L12;
        r46.setMaxWidth(AndroidUtilities.dp(280.0f));
        goto L14
    L12:
        r46.setMaxWidth(AndroidUtilities.dp(250.0f));
        goto L14
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$createCloseButton$0(View r1) {
        hide(true);
    }

    private void updatePosition(View r14) {
        measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, Integer.MIN_VALUE));
        int[] r1 = new int[2];
        r14.getLocationInWindow(r1);
        int r3 = r1[1] - AndroidUtilities.dp(4.0f);
        int r5 = this.currentType;
        if (r5 != 4) goto L6;
        int r4 = AndroidUtilities.dp(4.0f);
    L5:
        r3 = r3 + r4;
    L19:
        int r42 = this.currentType;
        int r6 = 0;
        if (r42 == 8) goto L22;
    L34:
        if (r42 != 3) goto L36;
        int r12 = r1[0];
    L37:
        View r142 = (View) getParent();
        r142.getLocationInWindow(r1);
        int r122 = r12 - r1[0];
        int r32 = (r3 - r1[1]) - this.bottomOffset;
        int r123 = r122 + offsetCx();
        int r13 = r142.getMeasuredWidth();
        if (this.isTopArrow == false) goto L44;
        int r2 = this.currentType;
        if (r2 == 6) goto L44;
        if (r2 == 7) goto L44;
        if (r2 == 8) goto L44;
        float r22 = this.extraTranslationY;
        float r33 = AndroidUtilities.dp(44.0f);
        this.translationY = r33;
        setTranslationY(r22 + r33);
    L46:
        if ((getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L48;
        int r23 = ((ViewGroup.MarginLayoutParams) getLayoutParams()).leftMargin;
        int r34 = ((ViewGroup.MarginLayoutParams) getLayoutParams()).rightMargin;
    L50:
        if (this.currentType == 8) goto L52;
    L54:
        int r43 = r142.getMeasuredWidth() / 2;
        int r7 = this.currentType;
        if (r123 <= r43) goto L62;
        if (r7 != 3) goto L61;
        int r15 = (int) (r13 - (getMeasuredWidth() * 1.5f));
        if (r15 < 0) goto L65;
    L60:
        r6 = r15;
    L65:
        setTranslationX(r6);
        float r16 = (r123 - (r23 + r6)) - (this.arrowImageView.getMeasuredWidth() / 2.0f);
        if (this.currentType != 7) goto L68;
        r16 = r16 + AndroidUtilities.dp(2.0f);
    L68:
        this.arrowImageView.setTranslationX(r16);
        if (r123 <= (r142.getMeasuredWidth() / 2)) goto L75;
        if (r16 >= AndroidUtilities.dp(10.0f)) goto L82;
        float r143 = r16 - AndroidUtilities.dp(10.0f);
        setTranslationX(getTranslationX() + r143);
        this.arrowImageView.setTranslationX(r16 - r143);
        return;
    L82:
        return;
    L75:
        if (r16 <= (getMeasuredWidth() - AndroidUtilities.dp(24.0f))) goto L79;
        float r144 = (r16 - getMeasuredWidth()) + AndroidUtilities.dp(24.0f);
        setTranslationX(r144);
        this.arrowImageView.setTranslationX(r16 - r144);
        return;
    L79:
        if (r16 >= AndroidUtilities.dp(10.0f)) goto L83;
        float r145 = r16 - AndroidUtilities.dp(10.0f);
        setTranslationX(getTranslationX() + r145);
        this.arrowImageView.setTranslationX(r16 - r145);
        return;
    L83:
        return;
    L61:
        r6 = (r13 - getMeasuredWidth()) - (r34 + r23);
        goto L65
    L62:
        if (r7 != 3) goto L65;
        r15 = (r123 - (getMeasuredWidth() / 2)) - this.arrowImageView.getMeasuredWidth();
        if (r15 >= 0) goto L60;
    L52:
        if (this.isTopArrow == true) goto L54;
        r6 = (((r13 - r23) - r34) - getMeasuredWidth()) / 2;
        goto L65
    L48:
        r23 = 0;
        r34 = 0;
    L44:
        float r24 = this.extraTranslationY;
        float r35 = r32 - getMeasuredHeight();
        this.translationY = r35;
        setTranslationY(r24 + r35);
        goto L46
    L36:
        r12 = r1[0] + (r14.getMeasuredWidth() / 2);
        goto L37
    L22:
        if (this.isTopArrow == false) goto L34;
        if ((r14 instanceof SimpleTextView) == false) goto L31;
        SimpleTextView r146 = (SimpleTextView) r14;
        Drawable r44 = r146.getRightDrawable();
        int r124 = r1[0];
        if (r44 == null) goto L28;
        int r147 = r44.getBounds().centerX();
    L29:
        r12 = (r124 + r147) - AndroidUtilities.dp(8.0f);
        goto L37
    L28:
        r147 = r146.getTextWidth() / 2;
        goto L29
    L31:
        if ((r14 instanceof TextView) == false) goto L33;
        r12 = (r1[0] + ((TextView) r14).getMeasuredWidth()) - AndroidUtilities.dp(16.5f);
        goto L37
    L33:
        r12 = r1[0];
        goto L37
    L6:
        if (r5 == 6) goto L8;
    L11:
        if (r5 == 7) goto L18;
        if (r5 == 8) goto L14;
    L16:
        if (r5 != 8) goto L19;
        r3 = r3 - AndroidUtilities.dp(10.0f);
        goto L19
    L14:
        if (this.isTopArrow == false) goto L16;
    L18:
        int r45 = r14.getMeasuredHeight() + getMeasuredHeight();
        int r52 = AndroidUtilities.dp(8.0f);
    L10:
        r4 = r45 + r52;
        goto L5
    L8:
        if (this.isTopArrow == false) goto L11;
        r45 = r14.getMeasuredHeight() + getMeasuredHeight();
        r52 = AndroidUtilities.dp(10.0f);
        goto L10
    }

    public void createCloseButton() {
        this.textView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(8.0f));
        this.hasCloseButton = true;
        ImageView r0 = new ImageView(getContext());
        this.imageView = r0;
        r0.setImageResource(R.drawable.msg_mini_close_tooltip);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(ColorUtils.setAlphaComponent(getThemedColor(Theme.key_chat_gifSaveHintText), 125), PorterDuff.Mode.MULTIPLY));
        ImageView r02 = this.imageView;
        boolean r1 = this.isTopArrow;
        if (r1 == false) goto L5;
        float r8 = 3.0f;
    L6:
        if (r1 == false) goto L8;
        float r10 = 0.0f;
    L9:
        addView(r02, LayoutHelper.createFrame(34, 34.0f, 21, 0.0f, r8, 0.0f, r10));
        setOnClickListener(new HintView$$ExternalSyntheticLambda0(this));
        return;
    L8:
        r10 = 3.0f;
        goto L9
    L5:
        r8 = 0.0f;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        if (this.drawPath == true) goto L5;
    L10:
        super.dispatchDraw(r4);
        return;
    L5:
        if (this.path == null) goto L10;
        if (this.backgroundPaint != null) goto L9;
        Paint r0 = new Paint(1);
        this.backgroundPaint = r0;
        r0.setPathEffect(new CornerPathEffect(AndroidUtilities.dpf2(6.0f)));
        this.backgroundPaint.setColor(this.backgroundColor);
    L9:
        r4.drawPath(this.path, this.backgroundPaint);
        goto L10
    }

    public float getBaseTranslationY() {
        return this.translationY;
    }

    public ChatMessageCell getMessageCell() {
        return this.messageCell;
    }

    public void hide() {
        hide(true);
    }

    public void hide(boolean r8) {
        if (getTag() != null) goto L5;
        return;
    L5:
        setTag(null);
        Runnable r1 = this.hideRunnable;
        if (r1 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.hideRunnable = null;
    L8:
        AnimatorSet r12 = this.animatorSet;
        if (r12 == null) goto L11;
        r12.cancel();
        this.animatorSet = null;
    L11:
        if (r8 == false) goto L18;
        AnimatorSet r82 = new AnimatorSet();
        this.animatorSet = r82;
        boolean r0 = this.useScale;
        Property r3 = View.ALPHA;
        if (r0 == false) goto L15;
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r3, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{1.0f, 0.5f}), ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{1.0f, 0.5f})});
        this.animatorSet.setDuration(150);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
    L16:
        this.animatorSet.addListener(new AnonymousClass3(this));
        this.animatorSet.start();
        return;
    L15:
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r3, new float[]{0.0f})});
        this.animatorSet.setDuration(300);
        goto L16
    L18:
        setVisibility(4);
        this.currentView = null;
        this.messageCell = null;
        this.animatorSet = null;
    }

    public boolean isShowing() {
        if (getTag() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public int offsetCx() {
        return 0;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        super.onMeasure(r8, r9);
        if (this.drawPath == false) goto L14;
        int r82 = getMeasuredHeight();
        int r92 = getMeasuredWidth();
        float r0 = this.arrowImageView.getX() + (this.arrowImageView.getMeasuredWidth() / 2.0f);
        Path r1 = this.path;
        if (r1 != null) goto L7;
        this.path = new Path();
    L8:
        boolean r12 = this.isTopArrow;
        Path r3 = this.path;
        if (r12 == false) goto L12;
        r3.moveTo(0.0f, AndroidUtilities.dp(6.0f));
        float r83 = r82;
        this.path.lineTo(0.0f, r83);
        float r93 = r92;
        this.path.lineTo(r93, r83);
        this.path.lineTo(r93, AndroidUtilities.dp(6.0f));
        this.path.lineTo(AndroidUtilities.dp(7.0f) + r0, AndroidUtilities.dp(6.0f));
        this.path.lineTo(r0, -AndroidUtilities.dp(2.0f));
        this.path.lineTo(r0 - AndroidUtilities.dp(7.0f), AndroidUtilities.dp(6.0f));
        this.path.close();
        return;
    L12:
        r3.moveTo(0.0f, r82 - AndroidUtilities.dp(6.0f));
        this.path.lineTo(0.0f, 0.0f);
        float r94 = r92;
        this.path.lineTo(r94, 0.0f);
        this.path.lineTo(r94, r82 - AndroidUtilities.dp(6.0f));
        this.path.lineTo(AndroidUtilities.dp(7.0f) + r0, r82 - AndroidUtilities.dp(6.0f));
        this.path.lineTo(r0, AndroidUtilities.dp(2.0f) + r82);
        this.path.lineTo(r0 - AndroidUtilities.dp(7.0f), r82 - AndroidUtilities.dp(6.0f));
        this.path.close();
        return;
    L7:
        r1.rewind();
        goto L8
    }

    public void setBackgroundColor(int r3, int r4) {
        this.textView.setTextColor(r4);
        this.arrowImageView.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
        TextView r42 = this.textView;
        int r2 = this.currentType;
        if (r2 != 7) goto L5;
    L8:
        float r22 = 6.0f;
    L9:
        r42.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(r22), r3));
        return;
    L5:
        if (r2 == 8) goto L8;
        r22 = 3.0f;
        goto L9
    }

    public void setBottomOffset(int r1) {
        this.bottomOffset = r1;
    }

    public void setExtraTranslationY(float r2) {
        this.extraTranslationY = r2;
        setTranslationY(r2 + this.translationY);
    }

    public void setOverrideText(String r2) {
        this.overrideText = r2;
        this.textView.setText(r2);
        ChatMessageCell r22 = this.messageCell;
        if (r22 == null) goto L6;
        this.messageCell = null;
        showForMessageCell(r22, false);
        return;
    }

    public void setShowingDuration(long r1) {
        this.showingDuration = r1;
    }

    public void setText(CharSequence r1) {
        this.textView.setText(r1);
    }

    public void setUseScale(boolean r1) {
        this.useScale = r1;
    }

    public void setVisibleListener(VisibilityListener r1) {
    }

    public boolean showForMessageCell(ChatMessageCell r18, Object r19, int r20, int r21, boolean r22) {
        int r3 = this.currentType;
        if (r3 == 5) goto L5;
    L8:
        if (r3 == 5) goto L15;
        if (r3 != 0) goto L13;
        if (getTag() == null) goto L13;
    L14:
        return false;
    L13:
        if (this.messageCell == r18) goto L14;
    L15:
        Runnable r32 = this.hideRunnable;
        if (r32 == null) goto L18;
        AndroidUtilities.cancelRunOnUIThread(r32);
        this.hideRunnable = null;
    L18:
        int[] r7 = new int[2];
        r18.getLocationInWindow(r7);
        int r9 = r7[1];
        ((View) getParent()).getLocationInWindow(r7);
        int r92 = r9 - r7[1];
        View r72 = (View) r18.getParent();
        int r10 = this.currentType;
        if (r10 != 0) goto L27;
        ImageReceiver r2 = r18.getPhotoImage();
        r92 = (int) (r92 + r2.getImageY());
        int r23 = (int) r2.getImageHeight();
        int r102 = r92 + r23;
        int r14 = r72.getMeasuredHeight();
        if (r92 > (getMeasuredHeight() + AndroidUtilities.dp(10.0f))) goto L23;
    L26:
        return false;
    L23:
        if (r102 > (r14 + (r23 / 4))) goto L26;
        int r24 = r18.getNoSoundIconCenterX();
        measure(View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE));
    L67:
        int r103 = r72.getMeasuredWidth();
        boolean r11 = this.isTopArrow;
        float r12 = this.extraTranslationY;
        if (r11 == false) goto L70;
        float r93 = AndroidUtilities.dp(44.0f);
        this.translationY = r93;
        setTranslationY(r12 + r93);
    L71:
        int r94 = r18.getLeft() + r24;
        int r112 = AndroidUtilities.dp(19.0f);
        if (this.currentType != 5) goto L75;
        int r5 = Math.max(0, (r24 - (getMeasuredWidth() / 2)) - AndroidUtilities.dp(19.1f));
        setTranslationX(r5);
        r112 = r112 + r5;
    L78:
        float r25 = ((r18.getLeft() + r24) - r112) - (this.arrowImageView.getMeasuredWidth() / 2);
        this.arrowImageView.setTranslationX(r25);
        if (r94 <= (r72.getMeasuredWidth() / 2)) goto L84;
        if (r25 >= AndroidUtilities.dp(10.0f)) goto L89;
        float r52 = r25 - AndroidUtilities.dp(10.0f);
        setTranslationX(getTranslationX() + r52);
        this.arrowImageView.setTranslationX(r25 - r52);
    L89:
        this.messageCell = r18;
        AnimatorSet r1 = this.animatorSet;
        if (r1 == null) goto L92;
        r1.cancel();
        this.animatorSet = null;
    L92:
        setTag(1);
        setVisibility(0);
        if (r22 == false) goto L95;
        AnimatorSet r13 = new AnimatorSet();
        this.animatorSet = r13;
        r13.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{0.0f, 1.0f})});
        this.animatorSet.addListener(new AnonymousClass1(this));
        this.animatorSet.setDuration(300);
        this.animatorSet.start();
    L96:
        return true;
    L95:
        setAlpha(1.0f);
        goto L96
    L84:
        if (r25 <= (getMeasuredWidth() - AndroidUtilities.dp(24.0f))) goto L87;
        float r53 = (r25 - getMeasuredWidth()) + AndroidUtilities.dp(24.0f);
        setTranslationX(r53);
        this.arrowImageView.setTranslationX(r25 - r53);
        goto L89
    L87:
        if (r25 >= AndroidUtilities.dp(10.0f)) goto L89;
        float r54 = r25 - AndroidUtilities.dp(10.0f);
        setTranslationX(getTranslationX() + r54);
        this.arrowImageView.setTranslationX(r25 - r54);
        goto L89
    L75:
        if (r94 <= (r72.getMeasuredWidth() / 2)) goto L77;
        int r104 = (r103 - getMeasuredWidth()) - AndroidUtilities.dp(38.0f);
        setTranslationX(r104);
        r112 = r112 + r104;
        goto L78
    L77:
        setTranslationX(0.0f);
        goto L78
    L70:
        float r95 = r92 - getMeasuredHeight();
        this.translationY = r95;
        setTranslationY(r12 + r95);
        goto L71
    L27:
        if (r10 != 5) goto L46;
        Integer r105 = (Integer) r19;
        r92 = r92 + r21;
        this.shownY = r21;
        MessageObject r26 = r18.getMessageObject();
        if (r26 == null) goto L42;
        if ((MessageObject.getMedia(r26) instanceof TLRPC.TL_messageMediaPoll) == false) goto L42;
        TLRPC.TL_messageMediaPoll r27 = (TLRPC.TL_messageMediaPoll) MessageObject.getMedia(r26);
        if (MessageObject.isVoted(r27) == false) goto L42;
        if (MessageObject.isVoteResultsIsNotEmpty(r27) == true) goto L42;
        TLRPC.Poll r28 = r27.poll;
        if (r28.closed == true) goto L42;
        if (r28.hide_results_until_close == false) goto L42;
        this.textView.setText(LocaleController.getString(R.string.PollResultsWillLater));
    L44:
        measure(View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE));
        r24 = r20;
    L42:
        if (r105.intValue() != (-1)) goto L45;
        this.textView.setText(LocaleController.getString(R.string.PollSelectOption));
        goto L44
    L45:
        return false;
    L46:
        MessageObject r29 = r18.getMessageObject();
        String r106 = this.overrideText;
        TextView r142 = this.textView;
        if (r106 != null) goto L49;
        r142.setText(LocaleController.getString(R.string.HidAccount));
    L50:
        measure(View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE));
        TLRPC.User r107 = r18.getCurrentUser();
        if (r107 != null) goto L53;
    L56:
        r92 = r92 + AndroidUtilities.dp(22.0f);
        if (r29.isOutOwner() == false) goto L59;
    L62:
        if (this.isTopArrow == false) goto L64;
    L66:
        r24 = r18.getForwardNameCenterX();
        goto L67
    L64:
        if (r92 > (getMeasuredHeight() + AndroidUtilities.dp(10.0f))) goto L66;
        return false;
    L59:
        if (r18.isDrawNameLayout() == false) goto L62;
        int r210 = AndroidUtilities.dp(20.0f);
    L55:
        r92 = r92 + r210;
        goto L62
    L53:
        if (r107.id != 0) goto L56;
        r210 = (r18.getMeasuredHeight() - Math.max(0, r18.getBottom() - r72.getMeasuredHeight())) - AndroidUtilities.dp(50.0f);
        goto L55
    L49:
        r142.setText(r106);
        goto L50
    L5:
        if (r21 != this.shownY) goto L8;
        if (this.messageCell == r18) goto L14;
        goto L8
    }

    public boolean showForMessageCell(ChatMessageCell r7, boolean r8) {
        return showForMessageCell(r7, null, 0, 0, r8);
    }

    public boolean showForView(View r7, boolean r8) {
        if (this.currentView == r7) goto L23;
        if (getTag() != null) goto L23;
        Runnable r0 = this.hideRunnable;
        if (r0 == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.hideRunnable = null;
    L10:
        updatePosition(r7);
        this.currentView = r7;
        AnimatorSet r72 = this.animatorSet;
        if (r72 == null) goto L13;
        r72.cancel();
        this.animatorSet = null;
    L13:
        setTag(1);
        setVisibility(0);
        if (r8 == false) goto L20;
        AnimatorSet r82 = new AnimatorSet();
        this.animatorSet = r82;
        boolean r02 = this.useScale;
        Property r3 = View.ALPHA;
        if (r02 == false) goto L18;
        setPivotX(this.arrowImageView.getX() + (this.arrowImageView.getMeasuredWidth() / 2.0f));
        setPivotY(this.arrowImageView.getY() + (this.arrowImageView.getMeasuredHeight() / 2.0f));
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r3, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{0.5f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{0.5f, 1.0f})});
        this.animatorSet.setDuration(350);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L19:
        this.animatorSet.addListener(new AnonymousClass2(this));
        this.animatorSet.start();
    L21:
        return true;
    L18:
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r3, new float[]{0.0f, 1.0f})});
        this.animatorSet.setDuration(300);
        goto L19
    L20:
        setAlpha(1.0f);
    L23:
        if (getTag() == null) goto L25;
        updatePosition(r7);
    L25:
        return false;
    }

    public void updatePosition() {
        View r0 = this.currentView;
        if (r0 != null) goto L5;
        return;
    L5:
        updatePosition(r0);
    }
}
