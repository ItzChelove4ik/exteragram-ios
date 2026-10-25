package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.speech.ui.RecognitionModelDialogs$1$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AttachableDrawable;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.BalanceCloud;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes3.dex */
public class AlertDialog extends Dialog implements Drawable.Callback, NotificationCenter.NotificationCenterDelegate {
    private View aboveMessageView;
    private int additioanalHorizontalPadding;
    private float aspectRatio;
    private int backgroundColor;
    private Rect backgroundPaddings;
    float blurAlpha;
    private boolean blurBehind;
    private Bitmap blurBitmap;
    private Matrix blurMatrix;
    private float blurOpacity;
    private Paint blurPaint;
    private BitmapShader blurShader;
    private boolean blurredBackground;
    private boolean blurredNativeBackground;
    private View bottomView;
    private boolean buttonsInTwoRows;
    protected ViewGroup buttonsLayout;
    private boolean canCacnel;
    private AlertDialog cancelDialog;
    private boolean checkFocusable;
    private AlertDialogView containerView;
    private int[] containerViewLocation;
    private ScrollView contentScrollView;
    private int currentProgress;
    private boolean customMaxHeight;
    private View customView;
    private int customViewHeight;
    private int customViewOffset;
    private int customWidth;
    private int dialogButtonColorKey;
    private float dimAlpha;
    private Paint dimBlurPaint;
    private boolean dimCustom;
    private boolean dimEnabled;
    private boolean dismissDialogByButtons;
    private Runnable dismissRunnable;
    private boolean dismissed;
    private boolean drawBackground;
    private boolean focusable;
    private FrameLayout fullscreenContainerView;
    private int[] itemIcons;
    private ArrayList<AlertDialogCell> itemViews;
    private CharSequence[] items;
    private int lastScreenWidth;
    private LineProgressView lineProgressView;
    private TextView lineProgressViewPercent;
    private CharSequence message;
    private TextView messageTextView;
    private boolean messageTextViewClickable;
    private boolean needStarsBalance;
    private OnButtonClickListener negative2ButtonListener;
    private CharSequence negative2ButtonText;
    private OnButtonClickListener negativeButtonListener;
    private CharSequence negativeButtonText;
    private OnButtonClickListener neutralButtonListener;
    private CharSequence neutralButtonText;
    private boolean notDrawBackgroundOnTopView;
    private OnButtonClickListener onBackButtonListener;
    private DialogInterface.OnCancelListener onCancelListener;
    private DialogInterface.OnClickListener onClickListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private Utilities.Callback<Runnable> overridenDissmissListener;
    private OnButtonClickListener positiveButtonListener;
    private CharSequence positiveButtonText;
    private FrameLayout progressViewContainer;
    private int progressViewStyle;
    private final Theme.ResourcesProvider resourcesProvider;
    private LinearLayout scrollContainer;
    private CharSequence secondTitle;
    private TextView secondTitleTextView;
    private BitmapDrawable[] shadow;
    private AnimatorSet[] shadowAnimation;
    private Drawable shadowDrawable;
    private boolean[] shadowVisibility;
    private Runnable showRunnable;
    private long shownAt;
    private BalanceCloud starsBalanceCloud;
    private CharSequence subtitle;
    private TextView subtitleTextView;
    private CharSequence title;
    private FrameLayout titleContainer;
    private SpoilersTextView titleTextView;
    private boolean topAnimationAutoRepeat;
    private int topAnimationId;
    private boolean topAnimationIsNew;
    private Map<String, Integer> topAnimationLayerColors;
    private int topAnimationSize;
    private int topBackgroundColor;
    private Drawable topDrawable;
    private int topHeight;
    private RLottieImageView topImageView;
    private int topResId;
    private View topView;
    private boolean twoRowsButtonsWhenNeeded;
    private boolean verticalButtons;
    private boolean withCancelDialog;

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ AlertDialog this$0;
        final /* synthetic */ AttachableDrawable val$d;

        public AnonymousClass1(AlertDialog r1, AttachableDrawable r2) {
            this.this$0 = r1;
            this.val$d = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$d.onAttachedToWindow(null);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.val$d.onDetachedFromWindow(null);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends Drawable {
        int size;
        final /* synthetic */ AlertDialog this$0;
        final /* synthetic */ GradientDrawable val$d;

        public AnonymousClass2(AlertDialog r1, GradientDrawable r2) {
            this.this$0 = r1;
            this.val$d = r2;
            this.size = AlertDialog.m5597$$Nest$fgettopAnimationSize(r1) + AndroidUtilities.dp(52.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r8) {
            this.val$d.setBounds((int) ((AlertDialog.m5599$$Nest$fgettopImageView(this.this$0).getWidth() - this.size) / 2.0f), (int) ((AlertDialog.m5599$$Nest$fgettopImageView(this.this$0).getHeight() - this.size) / 2.0f), (int) ((AlertDialog.m5599$$Nest$fgettopImageView(this.this$0).getWidth() + this.size) / 2.0f), (int) ((AlertDialog.m5599$$Nest$fgettopImageView(this.this$0).getHeight() + this.size) / 2.0f));
            this.val$d.draw(r8);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.val$d.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.val$d.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.val$d.setColorFilter(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends ScrollView {
        final /* synthetic */ AlertDialog this$0;

        public AnonymousClass3(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r15, View r16, long r17) {
            boolean r0 = super.drawChild(r15, r16, r17);
            if (AlertDialog.m5590$$Nest$fgetshadow(this.this$0)[0].getPaint().getAlpha() == 0) goto L6;
            r15.drawLine(0.0f, getScrollY(), getMeasuredWidth(), getScrollY(), Theme.forcedDividerPaint);
        L6:
            if (AlertDialog.m5590$$Nest$fgetshadow(this.this$0)[1].getPaint().getAlpha() == 0) goto L8;
            r15.drawLine(0.0f, getScrollY() + getMeasuredHeight(), getMeasuredWidth(), getScrollY() + getMeasuredHeight(), Theme.forcedDividerPaint);
        L8:
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ AlertDialog this$0;

        public AnonymousClass4(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
            int r3 = getChildCount();
            int r4 = r22 - r20;
            int r6 = -1;
            if (AlertDialog.m5570$$Nest$fgetbuttonsInTwoRows(this.this$0) == false) goto L21;
            View r1 = findViewWithTag(-3);
            View r2 = findViewWithTag(-4);
            View r32 = findViewWithTag(-2);
            View r5 = findViewWithTag(-1);
            int r62 = getPaddingLeft();
            int r42 = r4 - getPaddingRight();
            int r7 = Math.max(0, ((r42 - r62) - AndroidUtilities.dp(8.0f)) / 2);
            boolean r8 = LocaleController.isRTL;
            if (r8 == false) goto L7;
            int r9 = r42 - r7;
        L8:
            if (r8 == true) goto L11;
            r62 = r42 - r7;
        L11:
            int r0 = getPaddingTop();
            int r43 = AndroidUtilities.dp(44.0f) + r0;
            if (r1 == null) goto L14;
            r1.layout(r9, r0, r9 + r7, AndroidUtilities.dp(40.0f) + r0);
        L14:
            if (r2 == null) goto L16;
            r2.layout(r62, r0, r62 + r7, AndroidUtilities.dp(40.0f) + r0);
        L16:
            if (r32 == null) goto L18;
            r32.layout(r9, r43, r9 + r7, AndroidUtilities.dp(40.0f) + r43);
        L18:
            if (r5 == null) goto L69;
            r5.layout(r62, r43, r7 + r62, AndroidUtilities.dp(40.0f) + r43);
            return;
        L69:
            return;
        L7:
            r9 = r62;
            goto L8
        L21:
            View r52 = null;
            int r11 = 0;
        L22:
            if (r11 >= r3) goto L80;
            View r12 = getChildAt(r11);
            Integer r13 = (Integer) r12.getTag();
            if (r13 != null) goto L26;
            int r63 = r12.getMeasuredWidth();
            int r132 = r12.getMeasuredHeight();
            if (r52 == null) goto L66;
            int r14 = r52.getLeft() + ((r52.getMeasuredWidth() - r63) / 2);
            int r15 = r52.getTop() + ((r52.getMeasuredHeight() - r132) / 2);
        L67:
            r12.layout(r14, r15, r63 + r14, r132 + r15);
        L68:
            r11 = r11 + 1;
            r6 = -1;
            goto L22
        L66:
            r14 = 0;
            r15 = 0;
            goto L67
        L26:
            if (r13.intValue() != r6) goto L33;
            if (LocaleController.isRTL == false) goto L30;
            r12.layout(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + r12.getMeasuredWidth(), getPaddingTop() + r12.getMeasuredHeight());
        L31:
            r52 = r12;
            goto L68
        L30:
            r12.layout((r4 - getPaddingRight()) - r12.getMeasuredWidth(), getPaddingTop(), r4 - getPaddingRight(), getPaddingTop() + r12.getMeasuredHeight());
            goto L31
        L33:
            if (r13.intValue() != (-2)) goto L45;
            if (LocaleController.isRTL == false) goto L40;
            int r133 = getPaddingLeft();
            if (r52 == null) goto L39;
            r133 = r133 + (r52.getMeasuredWidth() + AndroidUtilities.dp(8.0f));
        L39:
            r12.layout(r133, getPaddingTop(), r12.getMeasuredWidth() + r133, getPaddingTop() + r12.getMeasuredHeight());
            goto L68
        L40:
            int r64 = (r4 - getPaddingRight()) - r12.getMeasuredWidth();
            if (r52 == null) goto L43;
            r64 = r64 - (r52.getMeasuredWidth() + AndroidUtilities.dp(8.0f));
        L43:
            r12.layout(r64, getPaddingTop(), r12.getMeasuredWidth() + r64, getPaddingTop() + r12.getMeasuredHeight());
            goto L68
        L45:
            if (r13.intValue() != (-4)) goto L58;
            View r65 = findViewWithTag(-3);
            if (LocaleController.isRTL == false) goto L53;
            if (r65 != null) goto L51;
            int r66 = r4 - getPaddingRight();
            int r134 = r12.getMeasuredWidth();
        L50:
            int r67 = r66 - r134;
            r12.layout(r67, getPaddingTop(), r12.getMeasuredWidth() + r67, getPaddingTop() + r12.getMeasuredHeight());
            goto L68
        L51:
            r66 = r65.getLeft() - AndroidUtilities.dp(8.0f);
            r134 = r12.getMeasuredWidth();
            goto L50
        L53:
            if (r65 != null) goto L55;
            int r68 = getPaddingLeft();
        L56:
            r12.layout(r68, getPaddingTop(), r12.getMeasuredWidth() + r68, getPaddingTop() + r12.getMeasuredHeight());
            goto L68
        L55:
            r68 = r65.getRight() + AndroidUtilities.dp(8.0f);
            goto L56
        L58:
            if (r13.intValue() != (-3)) goto L68;
            if (LocaleController.isRTL == false) goto L62;
            r12.layout((r4 - getPaddingRight()) - r12.getMeasuredWidth(), getPaddingTop(), r4 - getPaddingRight(), getPaddingTop() + r12.getMeasuredHeight());
            goto L68
        L62:
            r12.layout(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + r12.getMeasuredWidth(), getPaddingTop() + r12.getMeasuredHeight());
            goto L68
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            super.onMeasure(r6, r7);
            int r62 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int r72 = getChildCount();
            int r2 = 0;
            if (AlertDialog.m5570$$Nest$fgetbuttonsInTwoRows(this.this$0) == false) goto L10;
            int r63 = Math.max(0, (r62 - AndroidUtilities.dp(8.0f)) / 2);
        L5:
            if (r2 >= r72) goto L46;
            View r0 = getChildAt(r2);
            if (r0.getTag() == null) goto L9;
            r0.measure(View.MeasureSpec.makeMeasureSpec(r63, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40.0f), TLObject.FLAG_30));
        L9:
            r2 = r2 + 1;
            goto L5
        L46:
            return;
        L10:
            int r02 = 0;
        L11:
            if (r2 >= r72) goto L18;
            View r3 = getChildAt(r2);
            if ((r3 instanceof TextView) == false) goto L17;
            if (r3.getTag() == null) goto L17;
            r02 = r02 + r3.getMeasuredWidth();
        L17:
            r2 = r2 + 1;
            goto L11
        L18:
            if (r02 <= r62) goto L43;
            View r73 = findViewWithTag(-2);
            View r22 = findViewWithTag(-4);
            View r5 = findViewWithTag(-3);
            if (r73 == null) goto L28;
            if (r5 == null) goto L28;
            if (r73.getMeasuredWidth() >= r5.getMeasuredWidth()) goto L26;
            r5.measure(View.MeasureSpec.makeMeasureSpec(r5.getMeasuredWidth() - (r02 - r62), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r5.getMeasuredHeight(), TLObject.FLAG_30));
            return;
        L26:
            r73.measure(View.MeasureSpec.makeMeasureSpec(r73.getMeasuredWidth() - (r02 - r62), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r73.getMeasuredHeight(), TLObject.FLAG_30));
            return;
        L28:
            if (r22 == null) goto L44;
            if (r5 != null) goto L31;
            return;
        L31:
            if (r22.getMeasuredWidth() >= r5.getMeasuredWidth()) goto L34;
            r5.measure(View.MeasureSpec.makeMeasureSpec(r5.getMeasuredWidth() - (r02 - r62), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r5.getMeasuredHeight(), TLObject.FLAG_30));
            return;
        L34:
            r22.measure(View.MeasureSpec.makeMeasureSpec(r22.getMeasuredWidth() - (r02 - r62), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r22.getMeasuredHeight(), TLObject.FLAG_30));
            return;
        L44:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends TextViewWithLoading {
        final /* synthetic */ AlertDialog this$0;

        public AnonymousClass5(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void setEnabled(boolean r1) {
            super.setEnabled(r1);
            if (r1 == false) goto L5;
            float r12 = 1.0f;
        L6:
            setAlpha(r12);
            return;
        L5:
            r12 = 0.5f;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.TextViewWithLoading, android.widget.TextView
        public void setTextColor(int r2) {
            super.setTextColor(r2);
            setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(20.0f), r2));
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends TextViewWithLoading {
        final /* synthetic */ AlertDialog this$0;

        public AnonymousClass6(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void setEnabled(boolean r1) {
            super.setEnabled(r1);
            if (r1 == false) goto L5;
            float r12 = 1.0f;
        L6:
            setAlpha(r12);
            return;
        L5:
            r12 = 0.5f;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.TextViewWithLoading, android.widget.TextView
        public void setTextColor(int r2) {
            super.setTextColor(r2);
            setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(20.0f), r2));
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends TextViewWithLoading {
        final /* synthetic */ AlertDialog this$0;

        public AnonymousClass7(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void setEnabled(boolean r1) {
            super.setEnabled(r1);
            if (r1 == false) goto L5;
            float r12 = 1.0f;
        L6:
            setAlpha(r12);
            return;
        L5:
            r12 = 0.5f;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.TextViewWithLoading, android.widget.TextView
        public void setTextColor(int r2) {
            super.setTextColor(r2);
            setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(20.0f), r2));
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends TextViewWithLoading {
        final /* synthetic */ AlertDialog this$0;

        public AnonymousClass8(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void setEnabled(boolean r1) {
            super.setEnabled(r1);
            if (r1 == false) goto L5;
            float r12 = 1.0f;
        L6:
            setAlpha(r12);
            return;
        L5:
            r12 = 0.5f;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.TextViewWithLoading, android.widget.TextView
        public void setTextColor(int r2) {
            super.setTextColor(r2);
            setBackgroundDrawable(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(20.0f), r2));
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AlertDialog$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ AlertDialog this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass9(AlertDialog r1, int r2) {
            this.this$0 = r1;
            this.val$num = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            if (AlertDialog.m5591$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] != null) goto L5;
            return;
        L5:
            if (AlertDialog.m5591$$Nest$fgetshadowAnimation(this.this$0)[this.val$num].equals(r3) == false) goto L9;
            AlertDialog.m5591$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (AlertDialog.m5591$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] != null) goto L5;
            return;
        L5:
            if (AlertDialog.m5591$$Nest$fgetshadowAnimation(this.this$0)[this.val$num].equals(r3) == false) goto L9;
            AlertDialog.m5591$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] = null;
            return;
        }
    }

    public static class AlertDialogCell extends FrameLayout {
        private ImageView imageView;
        private final Theme.ResourcesProvider resourcesProvider;
        private TextView textView;

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m5650$$Nest$fgetimageView(AlertDialogCell r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m5651$$Nest$fgettextView(AlertDialogCell r0) {
            return r0.textView;
        }

        public AlertDialogCell(Context r6, Theme.ResourcesProvider r7) {
            super(r6);
            this.resourcesProvider = r7;
            setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_dialogButtonSelector), 2));
            setPadding(AndroidUtilities.dp(23.0f), 0, AndroidUtilities.dp(23.0f), 0);
            ImageView r72 = new ImageView(r6);
            this.imageView = r72;
            r72.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogIcon), PorterDuff.Mode.MULTIPLY));
            ImageView r73 = this.imageView;
            int r1 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r0 = 5;
        L6:
            addView(r73, LayoutHelper.createFrame(-2, 40, r0 | 16));
            TextView r74 = new TextView(r6);
            this.textView = r74;
            r74.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setTypeface(AndroidUtilities.regular());
            TextView r62 = this.textView;
            if (LocaleController.isRTL == false) goto L9;
            r1 = 5;
        L9:
            addView(r62, LayoutHelper.createFrame(-2, -2, r1 | 16));
            return;
        L5:
            r0 = 3;
            goto L6
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void setGravity(int r1) {
            this.textView.setGravity(r1);
        }

        public void setTextAndIcon(CharSequence r3, int r4) {
            this.textView.setText(r3);
            ImageView r32 = this.imageView;
            if (r4 == 0) goto L14;
            r32.setImageResource(r4);
            this.imageView.setVisibility(0);
            TextView r2 = this.textView;
            if (LocaleController.isRTL == false) goto L7;
            int r33 = 0;
        L9:
            if (LocaleController.isRTL == false) goto L11;
            int r42 = AndroidUtilities.dp(46.0f);
        L12:
            r2.setPadding(r33, 0, r42, 0);
            return;
        L11:
            r42 = 0;
            goto L12
        L7:
            r33 = AndroidUtilities.dp(46.0f);
            goto L9
        L14:
            r32.setVisibility(4);
            this.textView.setPadding(0, 0, 0, 0);
        }

        public void setTextColor(int r1) {
            this.textView.setTextColor(r1);
        }
    }

    public class AlertDialogView extends LinearLayout {
        private Paint backgroundPaint;
        private AnimatedFloat blurPaintAlpha;
        private boolean inLayout;
        final /* synthetic */ AlertDialog this$0;

        public static /* synthetic */ void $r8$lambda$3x4uBCbBVkvcbKVprzs_N3nVF70(AlertDialogView r0) {
            r0.lambda$onMeasure$0();
        }

        /* renamed from: $r8$lambda$HzOmcjwFKrxi-bdjPArgJ8KpoP0, reason: not valid java name */
        public static /* synthetic */ void m5652$r8$lambda$HzOmcjwFKrxibdjPArgJ8KpoP0(AlertDialogView r0) {
            r0.lambda$onLayout$1();
        }

        public AlertDialogView(AlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.blurPaintAlpha = new AnimatedFloat(0.0f, this);
            this.backgroundPaint = new Paint(1);
        }

        private /* synthetic */ void lambda$onLayout$1() {
            AlertDialog r0 = this.this$0;
            boolean r3 = false;
            if (AlertDialog.m5595$$Nest$fgettitleTextView(r0) != null) goto L5;
        L7:
            boolean r1 = false;
        L8:
            AlertDialog.m5649$$Nest$mrunShadowAnimation(r0, 0, r1);
            AlertDialog r02 = this.this$0;
            if (r02.buttonsLayout != null) goto L11;
        L13:
            AlertDialog.m5649$$Nest$mrunShadowAnimation(r02, 1, r3);
            AlertDialog.m5572$$Nest$fgetcontentScrollView(this.this$0).invalidate();
            return;
        L11:
            if ((AlertDialog.m5572$$Nest$fgetcontentScrollView(r02).getScrollY() + AlertDialog.m5572$$Nest$fgetcontentScrollView(this.this$0).getHeight()) >= AlertDialog.m5588$$Nest$fgetscrollContainer(this.this$0).getBottom()) goto L13;
            r3 = true;
            goto L13
        L5:
            if (AlertDialog.m5572$$Nest$fgetcontentScrollView(this.this$0).getScrollY() <= AlertDialog.m5588$$Nest$fgetscrollContainer(this.this$0).getTop()) goto L7;
            r1 = true;
            goto L8
        }

        private /* synthetic */ void lambda$onMeasure$0() {
            AlertDialog.m5619$$Nest$fputlastScreenWidth(this.this$0, AndroidUtilities.displaySize.x);
            int r0 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(56.0f);
            if (AndroidUtilities.isTablet() == true) goto L5;
            int r1 = AndroidUtilities.dp(356.0f);
        L9:
            Window r2 = this.this$0.getWindow();
            WindowManager.LayoutParams r3 = new WindowManager.LayoutParams();
            r3.copyFrom(r2.getAttributes());
            r3.width = (Math.min(r1, r0) + AlertDialog.m5563$$Nest$fgetbackgroundPaddings(this.this$0).left) + AlertDialog.m5563$$Nest$fgetbackgroundPaddings(this.this$0).right;
            r2.setAttributes(r3);     // Catch: Throwable -> L12
            return;
        L12:
            th = move-exception;
            FileLog.e(th);
            return;
        L5:
            if (AndroidUtilities.isSmallTablet() == false) goto L7;
            r1 = AndroidUtilities.dp(446.0f);
            goto L9
        L7:
            r1 = AndroidUtilities.dp(496.0f);
            goto L9
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            if (AlertDialog.m5578$$Nest$fgetdrawBackground(this.this$0) == true) goto L5;
        L12:
            super.dispatchDraw(r5);
            return;
        L5:
            if (AlertDialog.m5568$$Nest$fgetblurredBackground(this.this$0) == true) goto L12;
            AlertDialog.m5592$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            if (AlertDialog.m5600$$Nest$fgettopView(this.this$0) != null) goto L9;
        L11:
            AlertDialog.m5592$$Nest$fgetshadowDrawable(this.this$0).draw(r5);
            goto L12
        L9:
            if (AlertDialog.m5584$$Nest$fgetnotDrawBackgroundOnTopView(this.this$0) == false) goto L11;
            int r0 = AlertDialog.m5600$$Nest$fgettopView(this.this$0).getBottom();
            r5.save();
            r5.clipRect(0, r0, getMeasuredWidth(), getMeasuredHeight());
            AlertDialog.m5592$$Nest$fgetshadowDrawable(this.this$0).draw(r5);
            r5.restore();
            goto L12
        }

        @Override // android.view.View
        public void draw(Canvas r9) {
            if (AlertDialog.m5568$$Nest$fgetblurredBackground(this.this$0) == true) goto L5;
        L23:
            super.draw(r9);
            return;
        L5:
            if (AlertDialog.m5569$$Nest$fgetblurredNativeBackground(this.this$0) == true) goto L23;
            if (AlertDialog.m5587$$Nest$fgetprogressViewStyle(this.this$0) == 3) goto L9;
        L11:
            float r2 = AndroidUtilities.dp(20.0f);
            AndroidUtilities.rectTmp.set(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
        L12:
            AnimatedFloat r0 = this.blurPaintAlpha;
            if (AlertDialog.m5566$$Nest$fgetblurPaint(this.this$0) == null) goto L15;
            float r1 = 1.0f;
        L16:
            float r02 = r0.set(r1);
            if (AlertDialog.m5566$$Nest$fgetblurPaint(this.this$0) == null) goto L20;
            AlertDialog.m5566$$Nest$fgetblurPaint(this.this$0).setAlpha((int) (r02 * 255.0f));
            r9.drawRoundRect(AndroidUtilities.rectTmp, r2, r2, AlertDialog.m5566$$Nest$fgetblurPaint(this.this$0));
        L20:
            if (AlertDialog.m5576$$Nest$fgetdimBlurPaint(this.this$0) != null) goto L22;
            AlertDialog.m5615$$Nest$fputdimBlurPaint(this.this$0, new Paint(1));
            AlertDialog.m5576$$Nest$fgetdimBlurPaint(this.this$0).setColor(ColorUtils.setAlphaComponent(-16777216, (int) (AlertDialog.m5575$$Nest$fgetdimAlpha(this.this$0) * 255.0f)));
        L22:
            RectF r12 = AndroidUtilities.rectTmp;
            r9.drawRoundRect(r12, r2, r2, AlertDialog.m5576$$Nest$fgetdimBlurPaint(this.this$0));
            this.backgroundPaint.setColor(AlertDialog.m5562$$Nest$fgetbackgroundColor(this.this$0));
            this.backgroundPaint.setAlpha((int) (r4.getAlpha() * ((r02 * (AlertDialog.m5565$$Nest$fgetblurOpacity(this.this$0) - 1.0f)) + 1.0f)));
            r9.drawRoundRect(r12, r2, r2, this.backgroundPaint);
            goto L23
        L15:
            r1 = 0.0f;
            goto L16
        L9:
            if (AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0) == null) goto L11;
            float r03 = AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getWidth() * AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getScaleX();
            float r13 = AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getHeight() * AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getScaleY();
            r2 = Math.min(r03, r13) / 2.0f;
            AndroidUtilities.rectTmp.set((getWidth() - r03) / 2.0f, (getHeight() - r13) / 2.0f, (getWidth() + r03) / 2.0f, (getHeight() + r13) / 2.0f);
            goto L12
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (AlertDialog.m5601$$Nest$fgetwithCancelDialog(this.this$0) == false) goto L7;
            this.this$0.showCancelAlert();
            return false;
        L7:
            return super.onInterceptTouchEvent(r2);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            super.onLayout(r3, r4, r5, r6, r7);
            int r32 = AlertDialog.m5587$$Nest$fgetprogressViewStyle(this.this$0);
            AlertDialog r0 = this.this$0;
            if (r32 != 3) goto L6;
            int r62 = ((r6 - r4) - AlertDialog.m5586$$Nest$fgetprogressViewContainer(r0).getMeasuredWidth()) / 2;
            int r72 = ((r7 - r5) - AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getMeasuredHeight()) / 2;
            AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).layout(r62, r72, AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getMeasuredWidth() + r62, AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getMeasuredHeight() + r72);
        L11:
            getLocationOnScreen(AlertDialog.m5571$$Nest$fgetcontainerViewLocation(this.this$0));
            if (AlertDialog.m5564$$Nest$fgetblurMatrix(this.this$0) != null) goto L14;
            return;
        L14:
            if (AlertDialog.m5567$$Nest$fgetblurShader(this.this$0) == null) goto L18;
            AlertDialog.m5564$$Nest$fgetblurMatrix(this.this$0).reset();
            AlertDialog.m5564$$Nest$fgetblurMatrix(this.this$0).postScale(8.0f, 8.0f);
            AlertDialog.m5564$$Nest$fgetblurMatrix(this.this$0).postTranslate(-AlertDialog.m5571$$Nest$fgetcontainerViewLocation(this.this$0)[0], -AlertDialog.m5571$$Nest$fgetcontainerViewLocation(this.this$0)[1]);
            AlertDialog.m5567$$Nest$fgetblurShader(this.this$0).setLocalMatrix(AlertDialog.m5564$$Nest$fgetblurMatrix(this.this$0));
            return;
        L18:
            return;
        L6:
            if (AlertDialog.m5572$$Nest$fgetcontentScrollView(r0) == null) goto L11;
            if (AlertDialog.m5585$$Nest$fgetonScrollChangedListener(this.this$0) != null) goto L10;
            AlertDialog.m5632$$Nest$fputonScrollChangedListener(this.this$0, new AlertDialog$AlertDialogView$$ExternalSyntheticLambda0(this));
            AlertDialog.m5572$$Nest$fgetcontentScrollView(this.this$0).getViewTreeObserver().addOnScrollChangedListener(AlertDialog.m5585$$Nest$fgetonScrollChangedListener(this.this$0));
        L10:
            AlertDialog.m5585$$Nest$fgetonScrollChangedListener(this.this$0).onScrollChanged();
            goto L11
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r13, int r14) {
            if (AlertDialog.m5587$$Nest$fgetprogressViewStyle(this.this$0) != 3) goto L6;
            AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
            setMeasuredDimension(View.MeasureSpec.getSize(r13), View.MeasureSpec.getSize(r14));
            return;
        L6:
            this.inLayout = true;
            int r132 = View.MeasureSpec.getSize(r13);
            int r0 = View.MeasureSpec.getSize(r14);
            if (AlertDialog.m5574$$Nest$fgetcustomWidth(this.this$0) <= 0) goto L9;
            r132 = (AlertDialog.m5574$$Nest$fgetcustomWidth(this.this$0) + AlertDialog.m5563$$Nest$fgetbackgroundPaddings(this.this$0).left) + AlertDialog.m5563$$Nest$fgetbackgroundPaddings(this.this$0).right;
        L9:
            int r02 = (r0 - getPaddingTop()) - getPaddingBottom();
            int r1 = (r132 - getPaddingLeft()) - getPaddingRight();
            int r3 = View.MeasureSpec.makeMeasureSpec(r1 - AndroidUtilities.dp(48.0f), TLObject.FLAG_30);
            int r4 = View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
            ViewGroup r5 = this.this$0.buttonsLayout;
            if (r5 == null) goto L19;
            int r52 = r5.getChildCount();
            int r7 = 0;
        L12:
            AlertDialog r8 = this.this$0;
            if (r7 >= r52) goto L18;
            View r82 = r8.buttonsLayout.getChildAt(r7);
            if ((r82 instanceof TextView) == false) goto L17;
            ((TextView) r82).setMaxWidth(AndroidUtilities.dp((r1 - AndroidUtilities.dp(24.0f)) / 2));
        L17:
            r7 = r7 + 1;
            goto L12
        L18:
            r8.buttonsLayout.measure(r4, r14);
            LinearLayout.LayoutParams r53 = (LinearLayout.LayoutParams) this.this$0.buttonsLayout.getLayoutParams();
            int r54 = r02 - ((this.this$0.buttonsLayout.getMeasuredHeight() + r53.bottomMargin) + r53.topMargin);
        L21:
            if (AlertDialog.m5589$$Nest$fgetsecondTitleTextView(this.this$0) == null) goto L24;
            AlertDialog.m5589$$Nest$fgetsecondTitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), Integer.MIN_VALUE), r14);
        L24:
            if (AlertDialog.m5595$$Nest$fgettitleTextView(this.this$0) == null) goto L30;
            TextView r72 = AlertDialog.m5589$$Nest$fgetsecondTitleTextView(this.this$0);
            AlertDialog r10 = this.this$0;
            if (r72 == null) goto L28;
            AlertDialog.m5595$$Nest$fgettitleTextView(r10).measure(View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(r3) - AlertDialog.m5589$$Nest$fgetsecondTitleTextView(this.this$0).getMeasuredWidth()) - AndroidUtilities.dp(8.0f), TLObject.FLAG_30), r14);
            goto L30
        L28:
            AlertDialog.m5595$$Nest$fgettitleTextView(r10).measure(r3, r14);
        L30:
            if (AlertDialog.m5594$$Nest$fgettitleContainer(this.this$0) == null) goto L33;
            AlertDialog.m5594$$Nest$fgettitleContainer(this.this$0).measure(r3, r14);
            LinearLayout.LayoutParams r73 = (LinearLayout.LayoutParams) AlertDialog.m5594$$Nest$fgettitleContainer(this.this$0).getLayoutParams();
            r54 = r54 - ((AlertDialog.m5594$$Nest$fgettitleContainer(this.this$0).getMeasuredHeight() + r73.bottomMargin) + r73.topMargin);
        L33:
            if (AlertDialog.m5593$$Nest$fgetsubtitleTextView(this.this$0) == null) goto L36;
            AlertDialog.m5593$$Nest$fgetsubtitleTextView(this.this$0).measure(r3, r14);
            LinearLayout.LayoutParams r74 = (LinearLayout.LayoutParams) AlertDialog.m5593$$Nest$fgetsubtitleTextView(this.this$0).getLayoutParams();
            r54 = r54 - ((AlertDialog.m5593$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredHeight() + r74.bottomMargin) + r74.topMargin);
        L36:
            if (AlertDialog.m5599$$Nest$fgettopImageView(this.this$0) == null) goto L39;
            AlertDialog.m5599$$Nest$fgettopImageView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(AlertDialog.m5598$$Nest$fgettopHeight(this.this$0)), TLObject.FLAG_30));
            r54 = r54 - AlertDialog.m5599$$Nest$fgettopImageView(this.this$0).getMeasuredHeight();
        L39:
            if (AlertDialog.m5600$$Nest$fgettopView(this.this$0) == null) goto L50;
            float r12 = AlertDialog.m5561$$Nest$fgetaspectRatio(this.this$0);
            AlertDialog r102 = this.this$0;
            if (r12 >= 0.0f) goto L43;
            AlertDialog.m5600$$Nest$fgettopView(r102).measure(View.MeasureSpec.makeMeasureSpec(r132, TLObject.FLAG_30), r14);
        L49:
            r54 = r54 - AlertDialog.m5600$$Nest$fgettopView(this.this$0).getMeasuredHeight();
            goto L50
        L43:
            if (AlertDialog.m5561$$Nest$fgetaspectRatio(r102) != 0.0f) goto L46;
            float r142 = r132 / 936.0f;
            float r15 = 354.0f;
        L45:
            int r143 = (int) (r142 * r15);
            AlertDialog.m5600$$Nest$fgettopView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r132, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r143, TLObject.FLAG_30));
            AlertDialog.m5600$$Nest$fgettopView(this.this$0).getLayoutParams().height = r143;
            goto L49
        L46:
            r142 = r132;
            r15 = AlertDialog.m5561$$Nest$fgetaspectRatio(this.this$0);
        L50:
            int r144 = AlertDialog.m5587$$Nest$fgetprogressViewStyle(this.this$0);
            AlertDialog r16 = this.this$0;
            if (r144 != 0) goto L84;
            LinearLayout.LayoutParams r145 = (LinearLayout.LayoutParams) AlertDialog.m5572$$Nest$fgetcontentScrollView(r16).getLayoutParams();
            View r17 = AlertDialog.m5573$$Nest$fgetcustomView(this.this$0);
            AlertDialog r2 = this.this$0;
            if (r17 != null) goto L55;
            CharSequence[] r18 = AlertDialog.m5579$$Nest$fgetitems(r2);
            AlertDialog r22 = this.this$0;
            if (r18 == null) goto L77;
            if (AlertDialog.m5595$$Nest$fgettitleTextView(r22) == null) goto L72;
        L74:
            int r19 = 0;
        L75:
            r145.topMargin = r19;
            r145.bottomMargin = AndroidUtilities.dp(8.0f);
        L83:
            int r55 = r54 - (r145.bottomMargin + r145.topMargin);
            AlertDialog.m5572$$Nest$fgetcontentScrollView(this.this$0).measure(r4, View.MeasureSpec.makeMeasureSpec(r55, Integer.MIN_VALUE));
            r54 = r55 - AlertDialog.m5572$$Nest$fgetcontentScrollView(this.this$0).getMeasuredHeight();
        L96:
            int r03 = ((r02 - r54) + getPaddingTop()) + getPaddingBottom();
            if (AlertDialog.m5596$$Nest$fgettopAnimationIsNew(this.this$0) == false) goto L99;
            int r146 = AndroidUtilities.dp(8.0f);
        L100:
            setMeasuredDimension(r132, r03 - r146);
            this.inLayout = false;
            if (AlertDialog.m5580$$Nest$fgetlastScreenWidth(this.this$0) == AndroidUtilities.displaySize.x) goto L107;
            AndroidUtilities.runOnUIThread(new AlertDialog$AlertDialogView$$ExternalSyntheticLambda1(this));
            return;
        L107:
            return;
        L99:
            r146 = 0;
            goto L100
        L72:
            if (AlertDialog.m5583$$Nest$fgetmessageTextView(this.this$0).getVisibility() != 8) goto L74;
            r19 = AndroidUtilities.dp(8.0f);
            goto L75
        L77:
            if (AlertDialog.m5583$$Nest$fgetmessageTextView(r22).getVisibility() != 0) goto L83;
            if (AlertDialog.m5595$$Nest$fgettitleTextView(this.this$0) != null) goto L81;
            int r110 = AndroidUtilities.dp(19.0f);
        L82:
            r145.topMargin = r110;
            r145.bottomMargin = AndroidUtilities.dp(20.0f);
            goto L83
        L81:
            r110 = 0;
            goto L82
        L55:
            if (AlertDialog.m5595$$Nest$fgettitleTextView(r2) == null) goto L57;
        L61:
            int r111 = 0;
        L62:
            r145.topMargin = r111;
            if (this.this$0.buttonsLayout != null) goto L65;
            int r112 = AndroidUtilities.dp(8.0f);
        L66:
            r145.bottomMargin = r112;
            goto L83
        L65:
            r112 = 0;
            goto L66
        L57:
            if (AlertDialog.m5583$$Nest$fgetmessageTextView(this.this$0).getVisibility() != 8) goto L61;
            if (AlertDialog.m5579$$Nest$fgetitems(this.this$0) != null) goto L61;
            r111 = AndroidUtilities.dp(16.0f);
            goto L62
        L84:
            FrameLayout r147 = AlertDialog.m5586$$Nest$fgetprogressViewContainer(r16);
            AlertDialog r113 = this.this$0;
            if (r147 == null) goto L89;
            AlertDialog.m5586$$Nest$fgetprogressViewContainer(r113).measure(r3, View.MeasureSpec.makeMeasureSpec(r54, Integer.MIN_VALUE));
            LinearLayout.LayoutParams r148 = (LinearLayout.LayoutParams) AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getLayoutParams();
            int r114 = AlertDialog.m5586$$Nest$fgetprogressViewContainer(this.this$0).getMeasuredHeight() + r148.bottomMargin;
            int r149 = r148.topMargin;
        L87:
            r54 = r54 - (r114 + r149);
        L94:
            if (AlertDialog.m5581$$Nest$fgetlineProgressView(this.this$0) == null) goto L96;
            AlertDialog.m5581$$Nest$fgetlineProgressView(this.this$0).measure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(4.0f), TLObject.FLAG_30));
            LinearLayout.LayoutParams r1410 = (LinearLayout.LayoutParams) AlertDialog.m5581$$Nest$fgetlineProgressView(this.this$0).getLayoutParams();
            int r56 = r54 - ((AlertDialog.m5581$$Nest$fgetlineProgressView(this.this$0).getMeasuredHeight() + r1410.bottomMargin) + r1410.topMargin);
            AlertDialog.m5582$$Nest$fgetlineProgressViewPercent(this.this$0).measure(r3, View.MeasureSpec.makeMeasureSpec(r56, Integer.MIN_VALUE));
            LinearLayout.LayoutParams r1411 = (LinearLayout.LayoutParams) AlertDialog.m5582$$Nest$fgetlineProgressViewPercent(this.this$0).getLayoutParams();
            r54 = r56 - ((AlertDialog.m5582$$Nest$fgetlineProgressViewPercent(this.this$0).getMeasuredHeight() + r1411.bottomMargin) + r1411.topMargin);
            goto L96
        L89:
            if (AlertDialog.m5583$$Nest$fgetmessageTextView(r113) == null) goto L94;
            AlertDialog.m5583$$Nest$fgetmessageTextView(this.this$0).measure(r3, View.MeasureSpec.makeMeasureSpec(r54, Integer.MIN_VALUE));
            if (AlertDialog.m5583$$Nest$fgetmessageTextView(this.this$0).getVisibility() == 8) goto L94;
            LinearLayout.LayoutParams r1412 = (LinearLayout.LayoutParams) AlertDialog.m5583$$Nest$fgetmessageTextView(this.this$0).getLayoutParams();
            r114 = AlertDialog.m5583$$Nest$fgetmessageTextView(this.this$0).getMeasuredHeight() + r1412.bottomMargin;
            r149 = r1412.topMargin;
            goto L87
        L19:
            r54 = r02;
            goto L21
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (AlertDialog.m5601$$Nest$fgetwithCancelDialog(this.this$0) == false) goto L6;
            this.this$0.showCancelAlert();
            return false;
        L6:
            super.onTouchEvent(r2);
            return true;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.inLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    public static class Builder {
        private AlertDialog alertDialog;
        private final boolean[] red;

        public Builder(Context r2) {
            this(r2, null);
        }

        public Builder(Context r2, int r3, Theme.ResourcesProvider r4) {
            this.red = new boolean[3];
            if (r2 != null) goto L7;
            r2 = AndroidUtilities.findActivity(LaunchActivity.instance);
            if (r2 != null) goto L7;
            r2 = ApplicationLoader.applicationContext;
        L7:
            this.alertDialog = createAlertDialog(r2, r3, r4);
        }

        public Builder(Context r2, Theme.ResourcesProvider r3) {
            this(r2, 0, r3);
        }

        public Builder(AlertDialog r2) {
            this.red = new boolean[3];
            this.alertDialog = r2;
        }

        public Builder aboveMessageView(View r2) {
            AlertDialog.m5602$$Nest$fputaboveMessageView(this.alertDialog, r2);
            return this;
        }

        public Builder addBottomView(View r2) {
            AlertDialog.m5606$$Nest$fputbottomView(this.alertDialog, r2);
            return this;
        }

        public AlertDialog create() {
            return this.alertDialog;
        }

        public AlertDialog createAlertDialog(Context r1, int r2, Theme.ResourcesProvider r3) {
            return new AlertDialog(r1, r2, r3);
        }

        public Context getContext() {
            return this.alertDialog.getContext();
        }

        public Runnable getDismissRunnable() {
            return AlertDialog.m5577$$Nest$fgetdismissRunnable(this.alertDialog);
        }

        public Builder makeCustomMaxHeight() {
            AlertDialog.m5608$$Nest$fputcustomMaxHeight(this.alertDialog, true);
            return this;
        }

        public Builder makeRed(int r4) {
            int r42 = (-r4) - 1;
            if (r42 < 0) goto L7;
            boolean[] r1 = this.red;
            if (r42 >= r1.length) goto L7;
            r1[r42] = true;
        L7:
            return this;
        }

        public void notDrawBackgroundOnTopView(boolean r2) {
            AlertDialog.m5628$$Nest$fputnotDrawBackgroundOnTopView(this.alertDialog, r2);
            AlertDialog.m5605$$Nest$fputblurredBackground(this.alertDialog, false);
        }

        public Builder overrideDismissListener(Utilities.Callback<Runnable> r2) {
            AlertDialog.m5633$$Nest$fputoverridenDissmissListener(this.alertDialog, r2);
            return this;
        }

        public Builder setAdditionalHorizontalPadding(int r2) {
            AlertDialog.m5603$$Nest$fputadditioanalHorizontalPadding(this.alertDialog, r2);
            return this;
        }

        public Builder setButton(int r2, CharSequence r3, OnButtonClickListener r4) {
            if (r2 != (-4)) goto L5;
            AlertDialog.m5623$$Nest$fputnegative2ButtonText(this.alertDialog, r3);
            AlertDialog.m5622$$Nest$fputnegative2ButtonListener(this.alertDialog, r4);
            return this;
        L5:
            if (r2 != (-3)) goto L7;
            setNeutralButton(r3, r4);
            return this;
        L7:
            if (r2 != (-2)) goto L9;
            setNegativeButton(r3, r4);
            return this;
        L9:
            if (r2 == (-1)) goto L11;
            return this;
        L11:
            setPositiveButton(r3, r4);
            return this;
        }

        public void setButtonsVertical(boolean r1) {
            AlertDialog.m5648$$Nest$fputverticalButtons(this.alertDialog, r1);
        }

        public Builder setCheckFocusable(boolean r2) {
            AlertDialog.m5607$$Nest$fputcheckFocusable(this.alertDialog, r2);
            return this;
        }

        public Builder setCustomViewOffset(int r2) {
            AlertDialog.m5611$$Nest$fputcustomViewOffset(this.alertDialog, r2);
            return this;
        }

        public Builder setDialogButtonColorKey(int r2) {
            AlertDialog.m5613$$Nest$fputdialogButtonColorKey(this.alertDialog, r2);
            return this;
        }

        public Builder setDimAlpha(float r2) {
            AlertDialog.m5614$$Nest$fputdimAlpha(this.alertDialog, r2);
            return this;
        }

        public Builder setDimEnabled(boolean r2) {
            AlertDialog.m5616$$Nest$fputdimEnabled(this.alertDialog, r2);
            return this;
        }

        public Builder setItems(CharSequence[] r2, DialogInterface.OnClickListener r3) {
            AlertDialog.m5618$$Nest$fputitems(this.alertDialog, r2);
            AlertDialog.m5630$$Nest$fputonClickListener(this.alertDialog, r3);
            return this;
        }

        public Builder setItems(CharSequence[] r2, int[] r3, DialogInterface.OnClickListener r4) {
            AlertDialog.m5618$$Nest$fputitems(this.alertDialog, r2);
            AlertDialog.m5617$$Nest$fputitemIcons(this.alertDialog, r3);
            AlertDialog.m5630$$Nest$fputonClickListener(this.alertDialog, r4);
            return this;
        }

        public Builder setMessage(CharSequence r2) {
            AlertDialog.m5620$$Nest$fputmessage(this.alertDialog, r2);
            return this;
        }

        public Builder setMessageTextViewClickable(boolean r2) {
            AlertDialog.m5621$$Nest$fputmessageTextViewClickable(this.alertDialog, r2);
            return this;
        }

        public Builder setNegativeButton(CharSequence r2, OnButtonClickListener r3) {
            AlertDialog.m5625$$Nest$fputnegativeButtonText(this.alertDialog, r2);
            AlertDialog.m5624$$Nest$fputnegativeButtonListener(this.alertDialog, r3);
            return this;
        }

        public Builder setNeutralButton(CharSequence r2, OnButtonClickListener r3) {
            AlertDialog.m5627$$Nest$fputneutralButtonText(this.alertDialog, r2);
            AlertDialog.m5626$$Nest$fputneutralButtonListener(this.alertDialog, r3);
            return this;
        }

        public Builder setOnBackButtonListener(OnButtonClickListener r2) {
            AlertDialog.m5629$$Nest$fputonBackButtonListener(this.alertDialog, r2);
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener r2) {
            this.alertDialog.setOnCancelListener(r2);
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener r2) {
            this.alertDialog.setOnDismissListener(r2);
            return this;
        }

        public Builder setOnPreDismissListener(DialogInterface.OnDismissListener r2) {
            AlertDialog.m5631$$Nest$fputonDismissListener(this.alertDialog, r2);
            return this;
        }

        public Builder setPositiveButton(CharSequence r2, OnButtonClickListener r3) {
            AlertDialog.m5635$$Nest$fputpositiveButtonText(this.alertDialog, r2);
            AlertDialog.m5634$$Nest$fputpositiveButtonListener(this.alertDialog, r3);
            return this;
        }

        public Builder setSubtitle(CharSequence r2) {
            AlertDialog.m5636$$Nest$fputsubtitle(this.alertDialog, r2);
            return this;
        }

        public Builder setTitle(CharSequence r2) {
            AlertDialog.m5637$$Nest$fputtitle(this.alertDialog, r2);
            return this;
        }

        public Builder setTopAnimation(int r7, int r8, boolean r9, int r10) {
            return setTopAnimation(r7, r8, r9, r10, null);
        }

        public Builder setTopAnimation(int r2, int r3, boolean r4, int r5, Map<String, Integer> r6) {
            AlertDialog.m5639$$Nest$fputtopAnimationId(this.alertDialog, r2);
            AlertDialog.m5642$$Nest$fputtopAnimationSize(this.alertDialog, r3);
            AlertDialog.m5638$$Nest$fputtopAnimationAutoRepeat(this.alertDialog, r4);
            AlertDialog.m5643$$Nest$fputtopBackgroundColor(this.alertDialog, r5);
            AlertDialog.m5641$$Nest$fputtopAnimationLayerColors(this.alertDialog, r6);
            return this;
        }

        public Builder setTopAnimationIsNew(boolean r2) {
            AlertDialog.m5640$$Nest$fputtopAnimationIsNew(this.alertDialog, r2);
            return this;
        }

        @Keep
        public Builder setTopImage(int r2, int r3) {
            AlertDialog.m5645$$Nest$fputtopResId(this.alertDialog, r2);
            AlertDialog.m5643$$Nest$fputtopBackgroundColor(this.alertDialog, r3);
            return this;
        }

        public Builder setTopImage(Drawable r2, int r3) {
            AlertDialog.m5644$$Nest$fputtopDrawable(this.alertDialog, r2);
            AlertDialog.m5643$$Nest$fputtopBackgroundColor(this.alertDialog, r3);
            return this;
        }

        public Builder setTopView(View r2) {
            AlertDialog.m5646$$Nest$fputtopView(this.alertDialog, r2);
            return this;
        }

        public void setTopViewAspectRatio(float r1) {
            AlertDialog.m5604$$Nest$fputaspectRatio(this.alertDialog, r1);
        }

        public Builder setView(View r2) {
            return setView(r2, -2);
        }

        public Builder setView(View r2, int r3) {
            AlertDialog.m5609$$Nest$fputcustomView(this.alertDialog, r2);
            AlertDialog.m5610$$Nest$fputcustomViewHeight(this.alertDialog, r3);
            return this;
        }

        public Builder setWidth(int r2) {
            AlertDialog.m5612$$Nest$fputcustomWidth(this.alertDialog, r2);
            return this;
        }

        public AlertDialog show() {
            this.alertDialog.show();
            int r0 = 0;
        L3:
            boolean[] r1 = this.red;
            if (r0 >= r1.length) goto L13;
            if (r1[r0] == false) goto L11;
            TextView r12 = (TextView) this.alertDialog.getButton(-(r0 + 1));
            if (r12 == null) goto L11;
            r12.setTextColor(this.alertDialog.getThemedColor(Theme.key_text_RedBold));
        L11:
            r0 = r0 + 1;
            goto L3
        L13:
            return this.alertDialog;
        }

        public Builder twoRowsButtonsWhenNeeded() {
            AlertDialog.m5647$$Nest$fputtwoRowsButtonsWhenNeeded(this.alertDialog, true);
            return this;
        }
    }

    public interface OnButtonClickListener {
        void onClick(AlertDialog r1, int r2);
    }

    /* renamed from: $r8$lambda$B4fp4rFk_mbEv-WTBZXG5SwPoB0, reason: not valid java name */
    public static /* synthetic */ void m5558$r8$lambda$B4fp4rFk_mbEvWTBZXG5SwPoB0(View r1) {
        if ((r1 instanceof TextViewWithLoading) == false) goto L6;
        ((TextViewWithLoading) r1).setLoading(true, true);
        return;
    }

    /* renamed from: $r8$lambda$DXM_s6CuDfx-TxDGYrvPlfKL79g, reason: not valid java name */
    public static /* synthetic */ void m5559$r8$lambda$DXM_s6CuDfxTxDGYrvPlfKL79g(AlertDialog r0, TextViewWithLoading r1, View r2) {
        r0.lambda$inflateContent$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FXEBdsAyzJG0wArGveg3L0IRLPM(AlertDialog r0, DialogInterface r1) {
        r0.lambda$showCancelAlert$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$KoQ32WBjcO_pBlLSNc6Na6gKXMs(AlertDialog r0, View r1, boolean r2) {
        r0.lambda$makeButtonLoading$10(r1, r2);
    }

    /* renamed from: $r8$lambda$UQkqNvGIpA-9WQ3xZfCNyfS29SE, reason: not valid java name */
    public static /* synthetic */ void m5560$r8$lambda$UQkqNvGIpA9WQ3xZfCNyfS29SE(AlertDialog r0, TextViewWithLoading r1, View r2) {
        r0.lambda$inflateContent$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$c3jilUJATzttMutpPOVyBWF9O6E(AlertDialog r0, View r1) {
        r0.lambda$inflateContent$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$i4y60nxOZ1o_5TDSG1NbFot71Tk(AlertDialog r0, View r1) {
        r0.lambda$inflateContent$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$kW3dnLaIe4bjxj8t1vSTA0XytLk(AlertDialog r0) {
        r0.lambda$dismiss$13();
    }

    public static /* synthetic */ void $r8$lambda$kmzbX9kQQSGWmv3wbWDdeqabd9E(AlertDialog r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$mgRAJWZOc6xEJG7R4rqmm0uuj9Y(AlertDialog r0, View r1) {
        r0.lambda$inflateContent$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$oIZuucIc5F7k41A6qaIo344hDvg(AlertDialog r0, TextViewWithLoading r1, View r2) {
        r0.lambda$inflateContent$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qw5AyPIqsdS8utJ8X9AgUXFUC9Q(AlertDialog r0, Bitmap r1) {
        r0.lambda$inflateContent$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$uo1Y8ueB27IfaLa5iwRKSkEFwDA(AlertDialog r0, AlertDialog r1, int r2) {
        r0.lambda$showCancelAlert$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wDPYCjOl00VlFswMfb0Hnu0Seek(AlertDialog r0, TextViewWithLoading r1, View r2) {
        r0.lambda$inflateContent$5(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaspectRatio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5561$$Nest$fgetaspectRatio(AlertDialog r0) {
        return r0.aspectRatio;
    }

    /* renamed from: -$$Nest$fgetbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5562$$Nest$fgetbackgroundColor(AlertDialog r0) {
        return r0.backgroundColor;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaddings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m5563$$Nest$fgetbackgroundPaddings(AlertDialog r0) {
        return r0.backgroundPaddings;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m5564$$Nest$fgetblurMatrix(AlertDialog r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetblurOpacity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5565$$Nest$fgetblurOpacity(AlertDialog r0) {
        return r0.blurOpacity;
    }

    /* renamed from: -$$Nest$fgetblurPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m5566$$Nest$fgetblurPaint(AlertDialog r0) {
        return r0.blurPaint;
    }

    /* renamed from: -$$Nest$fgetblurShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m5567$$Nest$fgetblurShader(AlertDialog r0) {
        return r0.blurShader;
    }

    /* renamed from: -$$Nest$fgetblurredBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5568$$Nest$fgetblurredBackground(AlertDialog r0) {
        return r0.blurredBackground;
    }

    /* renamed from: -$$Nest$fgetblurredNativeBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5569$$Nest$fgetblurredNativeBackground(AlertDialog r0) {
        return r0.blurredNativeBackground;
    }

    /* renamed from: -$$Nest$fgetbuttonsInTwoRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5570$$Nest$fgetbuttonsInTwoRows(AlertDialog r0) {
        return r0.buttonsInTwoRows;
    }

    /* renamed from: -$$Nest$fgetcontainerViewLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m5571$$Nest$fgetcontainerViewLocation(AlertDialog r0) {
        return r0.containerViewLocation;
    }

    /* renamed from: -$$Nest$fgetcontentScrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollView m5572$$Nest$fgetcontentScrollView(AlertDialog r0) {
        return r0.contentScrollView;
    }

    /* renamed from: -$$Nest$fgetcustomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5573$$Nest$fgetcustomView(AlertDialog r0) {
        return r0.customView;
    }

    /* renamed from: -$$Nest$fgetcustomWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5574$$Nest$fgetcustomWidth(AlertDialog r0) {
        return r0.customWidth;
    }

    /* renamed from: -$$Nest$fgetdimAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5575$$Nest$fgetdimAlpha(AlertDialog r0) {
        return r0.dimAlpha;
    }

    /* renamed from: -$$Nest$fgetdimBlurPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m5576$$Nest$fgetdimBlurPaint(AlertDialog r0) {
        return r0.dimBlurPaint;
    }

    /* renamed from: -$$Nest$fgetdismissRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5577$$Nest$fgetdismissRunnable(AlertDialog r0) {
        return r0.dismissRunnable;
    }

    /* renamed from: -$$Nest$fgetdrawBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5578$$Nest$fgetdrawBackground(AlertDialog r0) {
        return r0.drawBackground;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence[] m5579$$Nest$fgetitems(AlertDialog r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlastScreenWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5580$$Nest$fgetlastScreenWidth(AlertDialog r0) {
        return r0.lastScreenWidth;
    }

    /* renamed from: -$$Nest$fgetlineProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LineProgressView m5581$$Nest$fgetlineProgressView(AlertDialog r0) {
        return r0.lineProgressView;
    }

    /* renamed from: -$$Nest$fgetlineProgressViewPercent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5582$$Nest$fgetlineProgressViewPercent(AlertDialog r0) {
        return r0.lineProgressViewPercent;
    }

    /* renamed from: -$$Nest$fgetmessageTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5583$$Nest$fgetmessageTextView(AlertDialog r0) {
        return r0.messageTextView;
    }

    /* renamed from: -$$Nest$fgetnotDrawBackgroundOnTopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5584$$Nest$fgetnotDrawBackgroundOnTopView(AlertDialog r0) {
        return r0.notDrawBackgroundOnTopView;
    }

    /* renamed from: -$$Nest$fgetonScrollChangedListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewTreeObserver.OnScrollChangedListener m5585$$Nest$fgetonScrollChangedListener(AlertDialog r0) {
        return r0.onScrollChangedListener;
    }

    /* renamed from: -$$Nest$fgetprogressViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m5586$$Nest$fgetprogressViewContainer(AlertDialog r0) {
        return r0.progressViewContainer;
    }

    /* renamed from: -$$Nest$fgetprogressViewStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5587$$Nest$fgetprogressViewStyle(AlertDialog r0) {
        return r0.progressViewStyle;
    }

    /* renamed from: -$$Nest$fgetscrollContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m5588$$Nest$fgetscrollContainer(AlertDialog r0) {
        return r0.scrollContainer;
    }

    /* renamed from: -$$Nest$fgetsecondTitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5589$$Nest$fgetsecondTitleTextView(AlertDialog r0) {
        return r0.secondTitleTextView;
    }

    /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapDrawable[] m5590$$Nest$fgetshadow(AlertDialog r0) {
        return r0.shadow;
    }

    /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet[] m5591$$Nest$fgetshadowAnimation(AlertDialog r0) {
        return r0.shadowAnimation;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m5592$$Nest$fgetshadowDrawable(AlertDialog r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgetsubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5593$$Nest$fgetsubtitleTextView(AlertDialog r0) {
        return r0.subtitleTextView;
    }

    /* renamed from: -$$Nest$fgettitleContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m5594$$Nest$fgettitleContainer(AlertDialog r0) {
        return r0.titleContainer;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilersTextView m5595$$Nest$fgettitleTextView(AlertDialog r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fgettopAnimationIsNew, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5596$$Nest$fgettopAnimationIsNew(AlertDialog r0) {
        return r0.topAnimationIsNew;
    }

    /* renamed from: -$$Nest$fgettopAnimationSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5597$$Nest$fgettopAnimationSize(AlertDialog r0) {
        return r0.topAnimationSize;
    }

    /* renamed from: -$$Nest$fgettopHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5598$$Nest$fgettopHeight(AlertDialog r0) {
        return r0.topHeight;
    }

    /* renamed from: -$$Nest$fgettopImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m5599$$Nest$fgettopImageView(AlertDialog r0) {
        return r0.topImageView;
    }

    /* renamed from: -$$Nest$fgettopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5600$$Nest$fgettopView(AlertDialog r0) {
        return r0.topView;
    }

    /* renamed from: -$$Nest$fgetwithCancelDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5601$$Nest$fgetwithCancelDialog(AlertDialog r0) {
        return r0.withCancelDialog;
    }

    /* renamed from: -$$Nest$fputaboveMessageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5602$$Nest$fputaboveMessageView(AlertDialog r0, View r1) {
        r0.aboveMessageView = r1;
    }

    /* renamed from: -$$Nest$fputadditioanalHorizontalPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5603$$Nest$fputadditioanalHorizontalPadding(AlertDialog r0, int r1) {
        r0.additioanalHorizontalPadding = r1;
    }

    /* renamed from: -$$Nest$fputaspectRatio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5604$$Nest$fputaspectRatio(AlertDialog r0, float r1) {
        r0.aspectRatio = r1;
    }

    /* renamed from: -$$Nest$fputblurredBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5605$$Nest$fputblurredBackground(AlertDialog r0, boolean r1) {
        r0.blurredBackground = r1;
    }

    /* renamed from: -$$Nest$fputbottomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5606$$Nest$fputbottomView(AlertDialog r0, View r1) {
        r0.bottomView = r1;
    }

    /* renamed from: -$$Nest$fputcheckFocusable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5607$$Nest$fputcheckFocusable(AlertDialog r0, boolean r1) {
        r0.checkFocusable = r1;
    }

    /* renamed from: -$$Nest$fputcustomMaxHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5608$$Nest$fputcustomMaxHeight(AlertDialog r0, boolean r1) {
        r0.customMaxHeight = r1;
    }

    /* renamed from: -$$Nest$fputcustomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5609$$Nest$fputcustomView(AlertDialog r0, View r1) {
        r0.customView = r1;
    }

    /* renamed from: -$$Nest$fputcustomViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5610$$Nest$fputcustomViewHeight(AlertDialog r0, int r1) {
        r0.customViewHeight = r1;
    }

    /* renamed from: -$$Nest$fputcustomViewOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5611$$Nest$fputcustomViewOffset(AlertDialog r0, int r1) {
        r0.customViewOffset = r1;
    }

    /* renamed from: -$$Nest$fputcustomWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5612$$Nest$fputcustomWidth(AlertDialog r0, int r1) {
        r0.customWidth = r1;
    }

    /* renamed from: -$$Nest$fputdialogButtonColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5613$$Nest$fputdialogButtonColorKey(AlertDialog r0, int r1) {
        r0.dialogButtonColorKey = r1;
    }

    /* renamed from: -$$Nest$fputdimAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5614$$Nest$fputdimAlpha(AlertDialog r0, float r1) {
        r0.dimAlpha = r1;
    }

    /* renamed from: -$$Nest$fputdimBlurPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5615$$Nest$fputdimBlurPaint(AlertDialog r0, Paint r1) {
        r0.dimBlurPaint = r1;
    }

    /* renamed from: -$$Nest$fputdimEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5616$$Nest$fputdimEnabled(AlertDialog r0, boolean r1) {
        r0.dimEnabled = r1;
    }

    /* renamed from: -$$Nest$fputitemIcons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5617$$Nest$fputitemIcons(AlertDialog r0, int[] r1) {
        r0.itemIcons = r1;
    }

    /* renamed from: -$$Nest$fputitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5618$$Nest$fputitems(AlertDialog r0, CharSequence[] r1) {
        r0.items = r1;
    }

    /* renamed from: -$$Nest$fputlastScreenWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5619$$Nest$fputlastScreenWidth(AlertDialog r0, int r1) {
        r0.lastScreenWidth = r1;
    }

    /* renamed from: -$$Nest$fputmessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5620$$Nest$fputmessage(AlertDialog r0, CharSequence r1) {
        r0.message = r1;
    }

    /* renamed from: -$$Nest$fputmessageTextViewClickable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5621$$Nest$fputmessageTextViewClickable(AlertDialog r0, boolean r1) {
        r0.messageTextViewClickable = r1;
    }

    /* renamed from: -$$Nest$fputnegative2ButtonListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5622$$Nest$fputnegative2ButtonListener(AlertDialog r0, OnButtonClickListener r1) {
        r0.negative2ButtonListener = r1;
    }

    /* renamed from: -$$Nest$fputnegative2ButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5623$$Nest$fputnegative2ButtonText(AlertDialog r0, CharSequence r1) {
        r0.negative2ButtonText = r1;
    }

    /* renamed from: -$$Nest$fputnegativeButtonListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5624$$Nest$fputnegativeButtonListener(AlertDialog r0, OnButtonClickListener r1) {
        r0.negativeButtonListener = r1;
    }

    /* renamed from: -$$Nest$fputnegativeButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5625$$Nest$fputnegativeButtonText(AlertDialog r0, CharSequence r1) {
        r0.negativeButtonText = r1;
    }

    /* renamed from: -$$Nest$fputneutralButtonListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5626$$Nest$fputneutralButtonListener(AlertDialog r0, OnButtonClickListener r1) {
        r0.neutralButtonListener = r1;
    }

    /* renamed from: -$$Nest$fputneutralButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5627$$Nest$fputneutralButtonText(AlertDialog r0, CharSequence r1) {
        r0.neutralButtonText = r1;
    }

    /* renamed from: -$$Nest$fputnotDrawBackgroundOnTopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5628$$Nest$fputnotDrawBackgroundOnTopView(AlertDialog r0, boolean r1) {
        r0.notDrawBackgroundOnTopView = r1;
    }

    /* renamed from: -$$Nest$fputonBackButtonListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5629$$Nest$fputonBackButtonListener(AlertDialog r0, OnButtonClickListener r1) {
        r0.onBackButtonListener = r1;
    }

    /* renamed from: -$$Nest$fputonClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5630$$Nest$fputonClickListener(AlertDialog r0, DialogInterface.OnClickListener r1) {
        r0.onClickListener = r1;
    }

    /* renamed from: -$$Nest$fputonDismissListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5631$$Nest$fputonDismissListener(AlertDialog r0, DialogInterface.OnDismissListener r1) {
        r0.onDismissListener = r1;
    }

    /* renamed from: -$$Nest$fputonScrollChangedListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5632$$Nest$fputonScrollChangedListener(AlertDialog r0, ViewTreeObserver.OnScrollChangedListener r1) {
        r0.onScrollChangedListener = r1;
    }

    /* renamed from: -$$Nest$fputoverridenDissmissListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5633$$Nest$fputoverridenDissmissListener(AlertDialog r0, Utilities.Callback r1) {
        r0.overridenDissmissListener = r1;
    }

    /* renamed from: -$$Nest$fputpositiveButtonListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5634$$Nest$fputpositiveButtonListener(AlertDialog r0, OnButtonClickListener r1) {
        r0.positiveButtonListener = r1;
    }

    /* renamed from: -$$Nest$fputpositiveButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5635$$Nest$fputpositiveButtonText(AlertDialog r0, CharSequence r1) {
        r0.positiveButtonText = r1;
    }

    /* renamed from: -$$Nest$fputsubtitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5636$$Nest$fputsubtitle(AlertDialog r0, CharSequence r1) {
        r0.subtitle = r1;
    }

    /* renamed from: -$$Nest$fputtitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5637$$Nest$fputtitle(AlertDialog r0, CharSequence r1) {
        r0.title = r1;
    }

    /* renamed from: -$$Nest$fputtopAnimationAutoRepeat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5638$$Nest$fputtopAnimationAutoRepeat(AlertDialog r0, boolean r1) {
        r0.topAnimationAutoRepeat = r1;
    }

    /* renamed from: -$$Nest$fputtopAnimationId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5639$$Nest$fputtopAnimationId(AlertDialog r0, int r1) {
        r0.topAnimationId = r1;
    }

    /* renamed from: -$$Nest$fputtopAnimationIsNew, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5640$$Nest$fputtopAnimationIsNew(AlertDialog r0, boolean r1) {
        r0.topAnimationIsNew = r1;
    }

    /* renamed from: -$$Nest$fputtopAnimationLayerColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5641$$Nest$fputtopAnimationLayerColors(AlertDialog r0, Map r1) {
        r0.topAnimationLayerColors = r1;
    }

    /* renamed from: -$$Nest$fputtopAnimationSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5642$$Nest$fputtopAnimationSize(AlertDialog r0, int r1) {
        r0.topAnimationSize = r1;
    }

    /* renamed from: -$$Nest$fputtopBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5643$$Nest$fputtopBackgroundColor(AlertDialog r0, int r1) {
        r0.topBackgroundColor = r1;
    }

    /* renamed from: -$$Nest$fputtopDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5644$$Nest$fputtopDrawable(AlertDialog r0, Drawable r1) {
        r0.topDrawable = r1;
    }

    /* renamed from: -$$Nest$fputtopResId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5645$$Nest$fputtopResId(AlertDialog r0, int r1) {
        r0.topResId = r1;
    }

    /* renamed from: -$$Nest$fputtopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5646$$Nest$fputtopView(AlertDialog r0, View r1) {
        r0.topView = r1;
    }

    /* renamed from: -$$Nest$fputtwoRowsButtonsWhenNeeded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5647$$Nest$fputtwoRowsButtonsWhenNeeded(AlertDialog r0, boolean r1) {
        r0.twoRowsButtonsWhenNeeded = r1;
    }

    /* renamed from: -$$Nest$fputverticalButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5648$$Nest$fputverticalButtons(AlertDialog r0, boolean r1) {
        r0.verticalButtons = r1;
    }

    /* renamed from: -$$Nest$mrunShadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5649$$Nest$mrunShadowAnimation(AlertDialog r0, int r1, boolean r2) {
        r0.runShadowAnimation(r1, r2);
    }

    public AlertDialog(Context r2, int r3) {
        this(r2, r3, null);
    }

    public AlertDialog(Context r6, int r7, Theme.ResourcesProvider r8) {
        super(r6, R.style.TransparentDialog);
        this.customWidth = -1;
        this.customViewHeight = -2;
        this.shadow = new BitmapDrawable[2];
        this.shadowVisibility = new boolean[2];
        this.shadowAnimation = new AnimatorSet[2];
        this.customViewOffset = 12;
        this.dialogButtonColorKey = Theme.key_dialogButton;
        this.topHeight = 132;
        boolean r1 = true;
        this.messageTextViewClickable = true;
        this.canCacnel = true;
        this.dismissDialogByButtons = true;
        this.containerViewLocation = new int[2];
        this.checkFocusable = true;
        this.dismissRunnable = new RecognitionModelDialogs$1$$ExternalSyntheticLambda0(this);
        this.showRunnable = new AlertDialog$$ExternalSyntheticLambda9(this);
        this.itemViews = new ArrayList();
        this.dimEnabled = true;
        this.dimAlpha = 0.5f;
        this.dimCustom = false;
        this.topAnimationAutoRepeat = true;
        float r2 = 0.8f;
        this.blurAlpha = 0.8f;
        this.resourcesProvider = r8;
        this.progressViewStyle = r7;
        int r82 = getThemedColor(Theme.key_dialogBackground);
        this.backgroundColor = r82;
        if (AndroidUtilities.computePerceivedBrightness(r82) >= 0.721f) goto L5;
        boolean r83 = true;
    L7:
        if (supportsNativeBlur() == true) goto L9;
    L11:
        boolean r3 = false;
    L12:
        this.blurredNativeBackground = r3;
        if (r3 == false) goto L15;
    L18:
        if (r83 == false) goto L20;
        boolean r32 = true;
    L21:
        this.blurredBackground = r32;
        this.backgroundPaddings = new Rect();
        if (r7 == 3) goto L24;
    L25:
        Drawable r62 = r6.getResources().getDrawable(R.drawable.popup_fixed_alert4).mutate();
        this.shadowDrawable = r62;
        if (r7 != 3) goto L28;
        r2 = 0.55f;
    L31:
        this.blurOpacity = r2;
        r62.setColorFilter(new PorterDuffColorFilter(this.backgroundColor, PorterDuff.Mode.MULTIPLY));
        this.shadowDrawable.getPadding(this.backgroundPaddings);
    L33:
        if (this.progressViewStyle == 3) goto L36;
        r1 = false;
    L36:
        this.withCancelDialog = r1;
        return;
    L28:
        if (r83 == true) goto L31;
        r2 = 0.985f;
        goto L31
    L24:
        if (this.blurredBackground == false) goto L33;
    L20:
        r32 = false;
        goto L21
    L15:
        if (supportsNativeBlur() == true) goto L20;
        if (LiteMode.isEnabled(256) == false) goto L20;
    L9:
        if (this.progressViewStyle != 0) goto L11;
        r3 = true;
        goto L12
    L5:
        r83 = false;
        goto L7
    }

    private boolean canTextInput(View r5) {
        if (r5.onCheckIsTextEditor() == false) goto L6;
        return true;
    L6:
        if ((r5 instanceof ViewGroup) == true) goto L8;
        return false;
    L8:
        ViewGroup r52 = (ViewGroup) r5;
        int r0 = r52.getChildCount();
    L9:
        if (r0 <= 0) goto L13;
        r0 = r0 - 1;
        if (canTextInput(r52.getChildAt(r0)) == false) goto L9;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$dismiss$13() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    private /* synthetic */ void lambda$inflateContent$1(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$inflateContent$2(View r2) {
        new StarsIntroActivity.StarsOptionsSheet(getContext(), this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$inflateContent$3(View r2) {
        DialogInterface.OnClickListener r0 = this.onClickListener;
        if (r0 == null) goto L5;
        r0.onClick(this, ((Integer) r2.getTag()).intValue());
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$inflateContent$4(TextViewWithLoading r1, View r2) {
        if (r1.isLoading() == true) goto L13;
        OnButtonClickListener r12 = this.positiveButtonListener;
        if (r12 == null) goto L9;
        r12.onClick(this, -1);
    L9:
        if (this.dismissDialogByButtons == false) goto L12;
        dismiss();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$inflateContent$5(TextViewWithLoading r1, View r2) {
        if (r1.isLoading() == true) goto L13;
        OnButtonClickListener r12 = this.negativeButtonListener;
        if (r12 == null) goto L9;
        r12.onClick(this, -2);
    L9:
        if (this.dismissDialogByButtons == false) goto L12;
        cancel();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$inflateContent$6(TextViewWithLoading r1, View r2) {
        if (r1.isLoading() == true) goto L13;
        OnButtonClickListener r12 = this.neutralButtonListener;
        if (r12 == null) goto L9;
        r12.onClick(this, -2);
    L9:
        if (this.dismissDialogByButtons == false) goto L12;
        dismiss();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$inflateContent$7(TextViewWithLoading r1, View r2) {
        if (r1.isLoading() == true) goto L13;
        OnButtonClickListener r12 = this.negative2ButtonListener;
        if (r12 == null) goto L9;
        r12.onClick(this, -2);
    L9:
        if (this.dismissDialogByButtons == false) goto L12;
        cancel();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$inflateContent$8(Bitmap r4) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (this.blurPaint != null) goto L7;
        this.blurPaint = new Paint(1);
    L7:
        this.blurBitmap = r4;
        Bitmap r0 = this.blurBitmap;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        BitmapShader r42 = new BitmapShader(r0, r2, r2);
        this.blurShader = r42;
        this.blurPaint.setShader(r42);
        Matrix r43 = new Matrix();
        this.blurMatrix = r43;
        r43.postScale(8.0f, 8.0f);
        Matrix r44 = this.blurMatrix;
        int[] r02 = this.containerViewLocation;
        r44.postTranslate(-r02[0], -r02[1]);
        this.blurShader.setLocalMatrix(this.blurMatrix);
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$makeButtonLoading$10(View r3, boolean r4) {
        if ((r3 instanceof TextViewWithLoading) == false) goto L5;
        ((TextViewWithLoading) r3).setLoading(false, true);
    L5:
        if (r4 == false) goto L8;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        if (isShowing() == false) goto L8;
        return;
    L8:
        show();     // Catch: Exception -> L7
        return;
    }

    private /* synthetic */ void lambda$showCancelAlert$11(AlertDialog r1, int r2) {
        DialogInterface.OnCancelListener r12 = this.onCancelListener;
        if (r12 == null) goto L5;
        r12.onCancel(this);
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$showCancelAlert$12(DialogInterface r1) {
        this.cancelDialog = null;
    }

    private void runShadowAnimation(int r5, boolean r6) {
        if (r6 == true) goto L4;
    L5:
        if (r6 == false) goto L7;
        return;
    L7:
        if (this.shadowVisibility[r5] == false) goto L27;
    L8:
        this.shadowVisibility[r5] = r6;
        AnimatorSet r0 = this.shadowAnimation[r5];
        if (r0 == null) goto L11;
        r0.cancel();
    L11:
        this.shadowAnimation[r5] = new AnimatorSet();
        BitmapDrawable r02 = this.shadow[r5];
        if (r02 == null) goto L18;
        AnimatorSet r1 = this.shadowAnimation[r5];
        if (r6 == false) goto L16;
        int r62 = 255;
    L17:
        r1.playTogether(new Animator[]{ObjectAnimator.ofInt(r02, "alpha", new int[]{r62})});
        goto L18
    L16:
        r62 = 0;
    L18:
        this.shadowAnimation[r5].setDuration(150);
        this.shadowAnimation[r5].addListener(new AnonymousClass9(this, r5));
        this.shadowAnimation[r5].start();     // Catch: Exception -> L21
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    L27:
        return;
    L4:
        if (this.shadowVisibility[r5] == false) goto L8;
        goto L5
    }

    private void updateLineProgressTextView() {
        this.lineProgressViewPercent.setText(String.format("%d%%", new Object[]{Integer.valueOf(this.currentProgress)}));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L8;
        TextView r0 = this.messageTextView;
        if (r0 == null) goto L9;
        r0.invalidate();
        return;
    L9:
        return;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Utilities.Callback<Runnable> r0 = this.overridenDissmissListener;
        if (r0 == null) goto L7;
        this.overridenDissmissListener = null;
        r0.run(new RecognitionModelDialogs$1$$ExternalSyntheticLambda0(this));
        return;
    L7:
        if (this.dismissed == true) goto L27;
        this.dismissed = true;
        AndroidUtilities.runOnUIThread(new AlertDialog$$ExternalSyntheticLambda0(this));
        DialogInterface.OnDismissListener r02 = this.onDismissListener;
        if (r02 == null) goto L12;
        r02.onDismiss(this);
    L12:
        AlertDialog r03 = this.cancelDialog;
        if (r03 == null) goto L23;
        r03.dismiss();
    L23:
        super.dismiss();     // Catch: Throwable -> L22
    L16:
        AndroidUtilities.cancelRunOnUIThread(this.showRunnable);
        if (this.blurShader == null) goto L25;
        Bitmap r04 = this.blurBitmap;
        if (r04 == null) goto L26;
        r04.recycle();
        this.blurShader = null;
        this.blurPaint = null;
        this.blurBitmap = null;
        return;
    L26:
        return;
    L25:
        return;
    }

    public void dismissUnless(long r5) {
        long r0 = System.currentTimeMillis() - this.shownAt;
        if (r0 >= r5) goto L6;
        AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$1$$ExternalSyntheticLambda0(this), r0 - r5);
        return;
    L6:
        dismiss();
    }

    public View getButton(int r1) {
        ViewGroup r0 = this.buttonsLayout;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.findViewWithTag(Integer.valueOf(r1));
    }

    public ViewGroup getButtonsLayout() {
        return this.buttonsLayout;
    }

    public AlertDialogView getContainerView() {
        return this.containerView;
    }

    public FrameLayout getFullscreenContainerView() {
        return this.fullscreenContainerView;
    }

    public int getItemsCount() {
        return this.itemViews.size();
    }

    public TextView getMessageTextView() {
        return this.messageTextView;
    }

    public BalanceCloud getStarsBalanceCloud() {
        return this.starsBalanceCloud;
    }

    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return null;
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public View inflateContent(boolean r32) {
        AlertDialogView r1 = new AlertDialogView(this, getContext());
        this.containerView = r1;
        r1.setOrientation(1);
        if (this.blurredBackground == true) goto L7;
        if (this.progressViewStyle == 3) goto L7;
    L15:
        if (this.notDrawBackgroundOnTopView == false) goto L17;
        Rect r12 = new Rect();
        this.shadowDrawable.getPadding(r12);
        this.containerView.setPadding(r12.left, r12.top, r12.right, r12.bottom);
        this.drawBackground = true;
    L18:
        View r13 = this.containerView;
        if (this.needStarsBalance == false) goto L28;
        if (this.fullscreenContainerView != null) goto L24;
        FrameLayout r14 = new FrameLayout(getContext());
        this.fullscreenContainerView = r14;
        r14.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda1(this));
    L24:
        if (this.starsBalanceCloud != null) goto L26;
        BalanceCloud r15 = new BalanceCloud(getContext(), UserConfig.selectedAccount, this.resourcesProvider);
        this.starsBalanceCloud = r15;
        ScaleStateListAnimator.apply(r15);
        this.starsBalanceCloud.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda2(this));
    L26:
        AndroidUtilities.removeFromParent(this.containerView);
        AndroidUtilities.removeFromParent(this.starsBalanceCloud);
        this.fullscreenContainerView.addView(this.containerView, LayoutHelper.createFrame(-2, -2, 17));
        this.fullscreenContainerView.addView(this.starsBalanceCloud, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 48.0f, 0.0f, 0.0f));
        r13 = this.fullscreenContainerView;
    L28:
        if (r32 == false) goto L37;
        if (this.needStarsBalance == false) goto L33;
        FrameLayout.LayoutParams r122 = new FrameLayout.LayoutParams(-1, -1);
        r122.gravity = 119;
        setContentView(r13, r122);
        goto L37
    L33:
        if (this.customWidth <= 0) goto L35;
        FrameLayout.LayoutParams r123 = new FrameLayout.LayoutParams(-2, -2);
        r123.gravity = 17;
        setContentView(r13, r123);
        goto L37
    L35:
        setContentView(r13);
    L37:
        if (this.positiveButtonText == null) goto L39;
    L46:
        boolean r124 = true;
    L48:
        if (this.topResId == 0) goto L50;
    L53:
        float r16 = 8.0f;
        RLottieImageView r3 = new RLottieImageView(getContext());
        this.topImageView = r3;
        Drawable r6 = this.topDrawable;
        if (r6 == null) goto L63;
        r3.setImageDrawable(r6);
        Object r33 = this.topDrawable;
        if ((r33 instanceof AttachableDrawable) == false) goto L73;
        AttachableDrawable r34 = (AttachableDrawable) r33;
        this.topImageView.addOnAttachStateChangeListener(new AnonymousClass1(this, r34));
        r34.setParent(this.topImageView);
    L73:
        this.topImageView.setScaleType(ImageView.ScaleType.CENTER);
        if (this.topAnimationIsNew == false) goto L76;
        GradientDrawable r35 = new GradientDrawable();
        r35.setColor(this.topBackgroundColor);
        r35.setCornerRadius(AndroidUtilities.dp(128.0f));
        this.topImageView.setBackground(new AnonymousClass2(this, r35));
        this.topHeight = 92;
    L77:
        boolean r36 = this.topAnimationIsNew;
        RLottieImageView r62 = this.topImageView;
        if (r36 == false) goto L80;
        r62.setTranslationY(AndroidUtilities.dp(16.0f));
    L81:
        this.topImageView.setPadding(0, 0, 0, 0);
        this.containerView.addView(this.topImageView, LayoutHelper.createLinear(-1, this.topHeight, 51, 0, 0, 0, 0));
    L83:
        if (this.title == null) goto L111;
        FrameLayout r37 = new FrameLayout(getContext());
        this.titleContainer = r37;
        this.containerView.addView(r37, LayoutHelper.createLinear(-2, -2, this.topAnimationIsNew ? 1 : 0, 24, 0, 24, 0));
        SpoilersTextView r38 = new SpoilersTextView(getContext(), false);
        this.titleTextView = r38;
        NotificationCenter.listenEmojiLoading(r38);
        SpoilersTextView r39 = this.titleTextView;
        r39.cacheType = 3;
        r39.setText(this.title);
        this.titleTextView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.titleTextView.setTextSize(1, 20.0f);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        SpoilersTextView r310 = this.titleTextView;
        if (this.topAnimationIsNew == false) goto L88;
        int r63 = 1;
    L91:
        r310.setGravity(r63 | 48);
        FrameLayout r311 = this.titleContainer;
        SpoilersTextView r64 = this.titleTextView;
        boolean r10 = this.topAnimationIsNew;
        if (r10 == false) goto L95;
        int r20 = 1;
    L98:
        int r26 = r20 | 48;
        if (r10 == false) goto L102;
        float r30 = 4.0f;
    L109:
        r311.addView(r64, LayoutHelper.createFrame(-2, -2.0f, r26, 0.0f, 19.0f, 0.0f, r30));
        goto L111
    L102:
        if (this.subtitle == null) goto L105;
        int r102 = 2;
    L108:
        r30 = r102;
        goto L109
    L105:
        if (this.items == null) goto L107;
        r102 = 14;
        goto L108
    L107:
        r102 = 10;
        goto L108
    L95:
        if (LocaleController.isRTL == false) goto L97;
        r20 = 5;
        goto L98
    L97:
        r20 = 3;
        goto L98
    L88:
        if (LocaleController.isRTL == false) goto L90;
        r63 = 5;
        goto L91
    L90:
        r63 = 3;
    L111:
        if (this.secondTitle == null) goto L124;
        if (this.title == null) goto L124;
        TextView r312 = new TextView(getContext());
        this.secondTitleTextView = r312;
        r312.setText(this.secondTitle);
        this.secondTitleTextView.setTextColor(getThemedColor(Theme.key_dialogTextGray3));
        this.secondTitleTextView.setTextSize(1, 18.0f);
        TextView r313 = this.secondTitleTextView;
        if (LocaleController.isRTL == false) goto L117;
        int r65 = 3;
    L118:
        r313.setGravity(r65 | 48);
        FrameLayout r314 = this.titleContainer;
        TextView r66 = this.secondTitleTextView;
        if (LocaleController.isRTL == false) goto L121;
        int r103 = 3;
    L122:
        r314.addView(r66, LayoutHelper.createFrame(-2, -2.0f, r103 | 48, 0.0f, 21.0f, 0.0f, 0.0f));
        goto L124
    L121:
        r103 = 5;
        goto L122
    L117:
        r65 = 5;
    L124:
        if (this.subtitle == null) goto L138;
        TextView r315 = new TextView(getContext());
        this.subtitleTextView = r315;
        r315.setText(this.subtitle);
        this.subtitleTextView.setTextColor(getThemedColor(Theme.key_dialogIcon));
        this.subtitleTextView.setTextSize(1, 14.0f);
        TextView r316 = this.subtitleTextView;
        if (LocaleController.isRTL == false) goto L128;
        int r104 = 5;
    L129:
        r316.setGravity(r104 | 48);
        AlertDialogView r317 = this.containerView;
        TextView r105 = this.subtitleTextView;
        if (LocaleController.isRTL == false) goto L132;
        int r202 = 5;
    L133:
        int r262 = r202 | 48;
        float r203 = 20.0f;
        if (this.items == null) goto L136;
        int r302 = 14;
    L137:
        r317.addView(r105, LayoutHelper.createLinear(-2, -2, r262, 24, 0, 24, r302));
    L140:
        if (this.progressViewStyle != 0) goto L142;
        this.shadow[0] = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.header_shadow).mutate();
        this.shadow[1] = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.header_shadow_reverse).mutate();
        this.shadow[0].setAlpha(0);
        this.shadow[1].setAlpha(0);
        this.shadow[0].setCallback(this);
        this.shadow[1].setCallback(this);
        AnonymousClass3 r318 = new AnonymousClass3(this, getContext());
        this.contentScrollView = r318;
        r318.setVerticalScrollBarEnabled(false);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.contentScrollView, getThemedColor(Theme.key_dialogScrollGlow));
        this.containerView.addView(this.contentScrollView, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r319 = new LinearLayout(getContext());
        this.scrollContainer = r319;
        r319.setOrientation(1);
        this.contentScrollView.addView(this.scrollContainer, new FrameLayout.LayoutParams(-1, -2));
    L142:
        EffectsTextView r320 = new EffectsTextView(getContext());
        this.messageTextView = r320;
        NotificationCenter.listenEmojiLoading(r320);
        TextView r321 = this.messageTextView;
        if (this.topAnimationIsNew == false) goto L145;
        int r4 = Theme.key_windowBackgroundWhiteGrayText;
    L146:
        r321.setTextColor(getThemedColor(r4));
        this.messageTextView.setTextSize(1, 16.0f);
        this.messageTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        this.messageTextView.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        if (this.messageTextViewClickable == true) goto L149;
        this.messageTextView.setClickable(false);
        this.messageTextView.setEnabled(false);
    L149:
        TextView r322 = this.messageTextView;
        if (this.topAnimationIsNew == false) goto L153;
        int r42 = 1;
    L156:
        r322.setGravity(r42 | 48);
        int r323 = this.progressViewStyle;
        if (r323 != 2) goto L175;
        AlertDialogView r324 = this.containerView;
        TextView r43 = this.messageTextView;
        if (LocaleController.isRTL == false) goto L161;
        int r106 = 5;
    L162:
        int r263 = r106 | 48;
        if (this.title != null) goto L165;
        int r28 = 19;
    L166:
        r324.addView(r43, LayoutHelper.createLinear(-2, -2, r263, 24, r28, 24, 20));
        LineProgressView r325 = new LineProgressView(getContext());
        this.lineProgressView = r325;
        r325.setProgressType(1);
        this.lineProgressView.setProgress(this.currentProgress / 100.0f, false);
        this.lineProgressView.setProgressColor(getThemedColor(Theme.key_dialogLineProgress));
        this.lineProgressView.setBackColor(getThemedColor(Theme.key_dialogLineProgressBackground));
        this.containerView.addView(this.lineProgressView, LayoutHelper.createLinear(-1, 4, 19, 24, 0, 24, 0));
        TextView r326 = new TextView(getContext());
        this.lineProgressViewPercent = r326;
        r326.setTypeface(AndroidUtilities.bold());
        TextView r327 = this.lineProgressViewPercent;
        if (LocaleController.isRTL == false) goto L169;
        int r44 = 5;
    L170:
        r327.setGravity(r44 | 48);
        this.lineProgressViewPercent.setTextColor(getThemedColor(Theme.key_dialogTextGray2));
        this.lineProgressViewPercent.setTextSize(1, 14.0f);
        AlertDialogView r328 = this.containerView;
        TextView r45 = this.lineProgressViewPercent;
        if (LocaleController.isRTL == false) goto L173;
        int r67 = 5;
    L174:
        r328.addView(r45, LayoutHelper.createLinear(-2, -2, r67 | 48, 23, 4, 23, 24));
        updateLineProgressTextView();
        char r19 = 2;
    L202:
        boolean r329 = TextUtils.isEmpty(this.message);
        TextView r46 = this.messageTextView;
        if (r329 == true) goto L205;
        r46.setText(this.message);
        this.messageTextView.setVisibility(0);
    L207:
        if (this.items == null) goto L220;
        int r330 = 0;
    L209:
        CharSequence[] r68 = this.items;
        if (r330 >= r68.length) goto L220;
        if (r68[r330] == null) goto L219;
        AlertDialogCell r69 = new AlertDialogCell(getContext(), this.resourcesProvider);
        CharSequence r107 = this.items[r330];
        int[] r132 = this.itemIcons;
        if (r132 == null) goto L217;
        int r133 = r132[r330];
    L218:
        r69.setTextAndIcon(r107, r133);
        r69.setTag(Integer.valueOf(r330));
        this.itemViews.add(r69);
        this.scrollContainer.addView(r69, LayoutHelper.createLinear(-1, 50));
        r69.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda3(this));
        goto L219
    L217:
        r133 = 0;
    L219:
        r330 = r330 + 1;
    L220:
        View r331 = this.customView;
        if (r331 == null) goto L227;
        if (r331.getParent() == null) goto L225;
        ((ViewGroup) this.customView.getParent()).removeView(this.customView);
    L225:
        this.scrollContainer.addView(this.customView, LayoutHelper.createLinear(-1, this.customViewHeight));
    L227:
        if (r124 == true) goto L229;
        float r21 = 24.0f;
    L316:
        Window r5 = getWindow();
        WindowManager.LayoutParams r610 = new WindowManager.LayoutParams();
        r610.copyFrom(r5.getAttributes());
        if (this.needStarsBalance == false) goto L320;
        r610.height = -1;
        r610.flags |= 1024;
        r5.setWindowAnimations(R.style.DialogNoAnimation);
    L336:
        View r9 = this.customView;
        if (r9 != null) goto L339;
    L344:
        r610.flags |= 131072;
    L346:
        if (Build.VERSION.SDK_INT < 28) goto L349;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r610, 0);
    L349:
        if (this.blurredBackground == true) goto L351;
    L358:
        r5.setAttributes(r610);
        return r13;
    L351:
        if (supportsNativeBlur() == true) goto L353;
        AndroidUtilities.makeGlobalBlurBitmap(new AlertDialog$$ExternalSyntheticLambda8(this), r16);
        goto L358
    L353:
        if (this.progressViewStyle != 0) goto L358;
        this.blurredNativeBackground = true;
        r5.setBackgroundBlurRadius(50);
        float r47 = AndroidUtilities.dp(r21);
        float[] r11 = new float[8];
        r11[0] = r47;
        r11[1] = r47;
        r11[r19] = r47;
        r11[3] = r47;
        r11[4] = r47;
        r11[5] = r47;
        r11[6] = r47;
        r11[7] = r47;
        ShapeDrawable r92 = new ShapeDrawable(new RoundRectShape(r11, null, null));
        r92.getPaint().setColor(ColorUtils.setAlphaComponent(this.backgroundColor, (int) (this.blurAlpha * 255.0f)));
        r5.setBackgroundDrawable(r92);
        if (this.blurBehind == false) goto L358;
        r610.flags |= 4;
        r610.setBlurBehindRadius(20);
        goto L358
    L339:
        if (this.checkFocusable == false) goto L344;
        if (canTextInput(r9) == false) goto L344;
        r610.flags &= -131073;
        r610.softInputMode = 16;
        goto L346
    L320:
        if (this.progressViewStyle != 3) goto L323;
        r610.width = -1;
        goto L336
    L323:
        if (this.dimEnabled == true) goto L325;
    L327:
        r610.dimAmount = 0.0f;
        r610.flags ^= 2;
    L328:
        int r93 = AndroidUtilities.displaySize.x;
        this.lastScreenWidth = r93;
        int r94 = (r93 - AndroidUtilities.dp(48.0f)) - (this.additioanalHorizontalPadding * 2);
        if (AndroidUtilities.isTablet() == true) goto L331;
        int r108 = AndroidUtilities.dp(356.0f);
    L335:
        int r95 = Math.min(r108, r94);
        Rect r109 = this.backgroundPaddings;
        r610.width = (r95 + r109.left) + r109.right;
        goto L336
    L331:
        if (AndroidUtilities.isSmallTablet() == false) goto L333;
        r108 = AndroidUtilities.dp(446.0f);
        goto L335
    L333:
        r108 = AndroidUtilities.dp(496.0f);
        goto L335
    L325:
        if (this.dimCustom == true) goto L327;
        r610.dimAmount = this.dimAlpha;
        r610.flags |= 2;
        goto L328
    L229:
        if (this.verticalButtons == true) goto L264;
        TextPaint r1010 = new TextPaint();
        r1010.setTextSize(AndroidUtilities.dp(16.0f));
        r1010.setTypeface(AndroidUtilities.bold());
        CharSequence r134 = this.positiveButtonText;
        r21 = 24.0f;
        if (r134 == null) goto L233;
        int r611 = (int) ((r1010.measureText(r134, 0, r134.length()) + AndroidUtilities.dp(24.0f)) + 0.0f);
    L235:
        if (this.negativeButtonText == null) goto L239;
        if (r611 <= 0) goto L238;
        r611 = r611 + AndroidUtilities.dp(r16);
    L238:
        CharSequence r135 = this.negativeButtonText;
        float r22 = 64.0f;
        r611 = (int) (r611 + (r1010.measureText(r135, 0, r135.length()) + AndroidUtilities.dp(24.0f)));
    L241:
        if (this.negative2ButtonText == null) goto L246;
        if (r611 <= 0) goto L244;
        r611 = r611 + AndroidUtilities.dp(r16);
    L244:
        CharSequence r125 = this.negative2ButtonText;
        r611 = (int) (r611 + (r1010.measureText(r125, 0, r125.length()) + AndroidUtilities.dp(24.0f)));
    L246:
        if (this.neutralButtonText == null) goto L251;
        if (r611 <= 0) goto L249;
        r611 = r611 + AndroidUtilities.dp(r16);
    L249:
        CharSequence r126 = this.neutralButtonText;
        r611 = (int) (r611 + (r1010.measureText(r126, 0, r126.length()) + AndroidUtilities.dp(24.0f)));
    L251:
        if (r611 <= (AndroidUtilities.displaySize.x - AndroidUtilities.dp(r22))) goto L266;
        if (this.twoRowsButtonsWhenNeeded == true) goto L255;
    L263:
        this.verticalButtons = true;
        goto L266
    L255:
        if (this.positiveButtonText == null) goto L263;
        if (this.negativeButtonText == null) goto L263;
        if (this.negative2ButtonText == null) goto L263;
        if (this.neutralButtonText == null) goto L263;
        this.buttonsInTwoRows = true;
    L266:
        if (this.verticalButtons == false) goto L268;
        LinearLayout r612 = new LinearLayout(getContext());
        r612.setOrientation(1);
        this.buttonsLayout = r612;
    L269:
        View r613 = this.bottomView;
        ViewGroup r1011 = this.buttonsLayout;
        if (r613 == null) goto L272;
        float r23 = 6.0f;
        r1011.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f));
        this.buttonsLayout.setTranslationY(-AndroidUtilities.dp(6.0f));
    L273:
        AlertDialogView r52 = this.containerView;
        ViewGroup r614 = this.buttonsLayout;
        if (this.buttonsInTwoRows == false) goto L276;
        int r1012 = 96;
    L277:
        r52.addView(r614, LayoutHelper.createLinear(-1, r1012));
        if (this.topAnimationIsNew == false) goto L281;
        this.buttonsLayout.setTranslationY(-AndroidUtilities.dp(r16));
    L281:
        if (this.positiveButtonText == null) goto L287;
        AnonymousClass5 r53 = new AnonymousClass5(this, getContext());
        r53.setMinWidth(AndroidUtilities.dp(r22));
        r53.setTag(-1);
        r53.setTextSize(1, 16.0f);
        r53.setTextColor(getThemedColor(this.dialogButtonColorKey));
        r53.setGravity(17);
        r53.setTypeface(AndroidUtilities.bold());
        r53.setText(this.positiveButtonText);
        float r24 = 12.0f;
        r53.setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(r203), getThemedColor(this.dialogButtonColorKey)));
        r53.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        boolean r127 = this.verticalButtons;
        ViewGroup r136 = this.buttonsLayout;
        if (r127 == false) goto L285;
        r136.addView(r53, LayoutHelper.createLinear(-1, 40, 7));
    L286:
        r53.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda4(this, r53));
    L289:
        if (this.negativeButtonText == null) goto L296;
        AnonymousClass6 r54 = new AnonymousClass6(this, getContext());
        r54.setMinWidth(AndroidUtilities.dp(r22));
        r54.setTag(-2);
        r54.setTextSize(1, 16.0f);
        r54.setTextColor(getThemedColor(this.dialogButtonColorKey));
        r54.setGravity(17);
        r54.setTypeface(AndroidUtilities.bold());
        r54.setEllipsize(TextUtils.TruncateAt.END);
        r54.setSingleLine(true);
        r54.setText(this.negativeButtonText.toString());
        r54.setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(r203), getThemedColor(this.dialogButtonColorKey)));
        r54.setPadding(AndroidUtilities.dp(r24), 0, AndroidUtilities.dp(r24), 0);
        boolean r128 = this.verticalButtons;
        ViewGroup r137 = this.buttonsLayout;
        if (r128 == false) goto L293;
        r137.addView(r54, 0, LayoutHelper.createLinear(-1, 40, 7));
    L294:
        r54.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda5(this, r54));
        goto L296
    L293:
        r137.addView(r54, LayoutHelper.createFrame(-2, 40, 53));
    L296:
        if (this.neutralButtonText == null) goto L303;
        AnonymousClass7 r55 = new AnonymousClass7(this, getContext());
        r55.setMinWidth(AndroidUtilities.dp(r22));
        r55.setTag(-3);
        r55.setTextSize(1, 16.0f);
        r55.setTextColor(getThemedColor(this.dialogButtonColorKey));
        r55.setGravity(17);
        r55.setTypeface(AndroidUtilities.bold());
        r55.setEllipsize(TextUtils.TruncateAt.END);
        r55.setSingleLine(true);
        r55.setText(this.neutralButtonText.toString());
        r55.setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(r203), getThemedColor(this.dialogButtonColorKey)));
        r55.setPadding(AndroidUtilities.dp(r24), 0, AndroidUtilities.dp(r24), 0);
        boolean r129 = this.verticalButtons;
        ViewGroup r138 = this.buttonsLayout;
        if (r129 == false) goto L300;
        r138.addView(r55, 1, LayoutHelper.createLinear(-1, 40, 7));
    L301:
        r55.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda6(this, r55));
        goto L303
    L300:
        r138.addView(r55, LayoutHelper.createFrame(-2, 40, 51));
    L303:
        if (this.negative2ButtonText == null) goto L310;
        AnonymousClass8 r56 = new AnonymousClass8(this, getContext());
        r56.setMinWidth(AndroidUtilities.dp(r22));
        r56.setTag(-4);
        r56.setTextSize(1, 16.0f);
        r56.setTextColor(getThemedColor(this.dialogButtonColorKey));
        r56.setGravity(17);
        r56.setTypeface(AndroidUtilities.bold());
        r56.setEllipsize(TextUtils.TruncateAt.END);
        r56.setSingleLine(true);
        r56.setText(this.negative2ButtonText.toString());
        r56.setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(r203), getThemedColor(this.dialogButtonColorKey)));
        r56.setPadding(AndroidUtilities.dp(r24), 0, AndroidUtilities.dp(r24), 0);
        boolean r1210 = this.verticalButtons;
        ViewGroup r139 = this.buttonsLayout;
        if (r1210 == false) goto L307;
        r139.addView(r56, 0, LayoutHelper.createLinear(-1, 40, 7));
    L308:
        r56.setOnClickListener(new AlertDialog$$ExternalSyntheticLambda7(this, r56));
        goto L310
    L307:
        r139.addView(r56, LayoutHelper.createFrame(-2, 40, 53));
    L310:
        if (this.verticalButtons == false) goto L316;
        int r57 = 1;
    L313:
        if (r57 >= this.buttonsLayout.getChildCount()) goto L316;
        ((ViewGroup.MarginLayoutParams) this.buttonsLayout.getChildAt(r57).getLayoutParams()).topMargin = AndroidUtilities.dp(r23);
        r57 = r57 + 1;
        goto L313
    L285:
        r136.addView(r53, LayoutHelper.createFrame(-2, 40, 53));
        goto L286
    L287:
        r24 = 12.0f;
        goto L289
    L276:
        r1012 = 52;
        goto L277
    L272:
        r23 = 6.0f;
        r1011.setPadding(AndroidUtilities.dp(r16), AndroidUtilities.dp(r16), AndroidUtilities.dp(r16), AndroidUtilities.dp(r16));
        goto L273
    L268:
        this.buttonsLayout = new AnonymousClass4(this, getContext());
        goto L269
    L239:
        r22 = 64.0f;
        goto L241
    L233:
        r611 = 0;
        goto L235
    L264:
        r21 = 24.0f;
        r22 = 64.0f;
        goto L266
    L205:
        r46.setVisibility(8);
        goto L207
    L173:
        r67 = 3;
        goto L174
    L169:
        r44 = 3;
        goto L170
    L165:
        r28 = 0;
        goto L166
    L161:
        r106 = 3;
        goto L162
    L175:
        if (r323 != 3) goto L182;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.progressViewContainer = new FrameLayout(getContext());
        this.backgroundColor = getThemedColor(Theme.key_dialog_inlineProgressBackground);
        if (this.blurredBackground == true) goto L179;
    L180:
        this.progressViewContainer.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), this.backgroundColor));
    L181:
        r19 = 2;
        this.containerView.addView(this.progressViewContainer, LayoutHelper.createLinear(56, 56, 17));
        RadialProgressView r332 = new RadialProgressView(getContext(), this.resourcesProvider);
        r332.setStyle(1);
        r332.setSize(AndroidUtilities.dp(32.0f));
        r332.setProgressColor(getThemedColor(Theme.key_dialog_inlineProgress));
        this.progressViewContainer.addView(r332, LayoutHelper.createFrame(56, 56, 17));
        goto L202
    L179:
        if (this.blurredNativeBackground == false) goto L181;
    L182:
        View r333 = this.aboveMessageView;
        if (r333 == null) goto L185;
        this.scrollContainer.addView(r333, LayoutHelper.createLinear(-1, -2, 22.0f, 4.0f, 22.0f, 12.0f));
    L185:
        LinearLayout r334 = this.scrollContainer;
        TextView r48 = this.messageTextView;
        if (this.topAnimationIsNew == false) goto L189;
        int r58 = 1;
    L192:
        int r264 = r58 | 48;
        if (this.customView == null) goto L195;
    L198:
        int r303 = this.customViewOffset;
    L199:
        r334.addView(r48, LayoutHelper.createLinear(-2, -2, r264, 24, 0, 24, r303));
        View r335 = this.bottomView;
        r19 = 2;
        if (r335 == null) goto L202;
        this.scrollContainer.addView(r335, LayoutHelper.createLinear(-1, -2, 22.0f, 12.0f, 22.0f, 0.0f));
        r19 = 2;
        goto L202
    L195:
        if (this.items != null) goto L198;
        r303 = 0;
        goto L199
    L189:
        if (LocaleController.isRTL == false) goto L191;
        r58 = 5;
        goto L192
    L191:
        r58 = 3;
        goto L192
    L153:
        if (LocaleController.isRTL == false) goto L155;
        r42 = 5;
        goto L156
    L155:
        r42 = 3;
        goto L156
    L145:
        r4 = Theme.key_dialogTextBlack;
        goto L146
    L136:
        r302 = 10;
        goto L137
    L132:
        r202 = 3;
        goto L133
    L128:
        r104 = 3;
        goto L129
    L138:
        r203 = 20.0f;
        goto L140
    L80:
        r62.setTranslationY(0.0f);
        goto L81
    L76:
        this.topImageView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), 0, this.topBackgroundColor));
        goto L77
    L63:
        int r615 = this.topResId;
        if (r615 == 0) goto L66;
        r3.setImageResource(r615);
        goto L73
    L66:
        r3.setAutoRepeat(this.topAnimationAutoRepeat);
        RLottieImageView r336 = this.topImageView;
        int r616 = this.topAnimationId;
        int r1310 = this.topAnimationSize;
        r336.setAnimation(r616, r1310, r1310);
        if (this.topAnimationLayerColors == null) goto L72;
        RLottieDrawable r337 = this.topImageView.getAnimatedDrawable();
        Iterator<Map.Entry<String, Integer>> r617 = this.topAnimationLayerColors.entrySet().iterator();
    L70:
        if (r617.hasNext() == false) goto L72;
        Map.Entry<String, Integer> r1311 = r617.next();
        r337.setLayerColor(r1311.getKey(), r1311.getValue().intValue());
    L72:
        this.topImageView.playAnimation();
        goto L73
    L50:
        if (this.topAnimationId != 0) goto L53;
        if (this.topDrawable != null) goto L53;
        View r1312 = this.topView;
        if (r1312 == null) goto L57;
        r1312.setPadding(0, 0, 0, 0);
        r16 = 8.0f;
        this.containerView.addView(this.topView, LayoutHelper.createLinear(-1, this.topHeight, 51, 0, 0, 0, 0));
        goto L83
    L57:
        r16 = 8.0f;
        goto L83
    L39:
        if (this.negativeButtonText != null) goto L46;
        if (this.negative2ButtonText != null) goto L46;
        if (this.neutralButtonText != null) goto L46;
        r124 = false;
        goto L48
    L17:
        this.containerView.setBackground(null);
        this.containerView.setPadding(0, 0, 0, 0);
        this.containerView.setBackground(this.shadowDrawable);
        this.containerView.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(20.0f)));
        this.containerView.setClipToOutline(true);
        this.drawBackground = false;
    L7:
        if (this.progressViewStyle == 2) goto L15;
        this.containerView.setBackground(null);
        this.containerView.setPadding(0, 0, 0, 0);
        if (this.blurredBackground == true) goto L11;
    L13:
        this.drawBackground = false;
        goto L18
    L11:
        if (this.blurredNativeBackground == true) goto L13;
        this.containerView.setWillNotDraw(false);
        goto L13
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        this.contentScrollView.invalidate();
        this.scrollContainer.invalidate();
    }

    public boolean isDismissed() {
        return this.dismissed;
    }

    public Browser.Progress makeButtonLoading(int r2) {
        return makeButtonLoading(r2, true, true);
    }

    public Browser.Progress makeButtonLoading(int r3, boolean r4, boolean r5) {
        View r32 = getButton(r3);
        if (r5 == false) goto L6;
        this.dismissDialogByButtons = false;
    L6:
        return new Browser.Progress(new AlertDialog$$ExternalSyntheticLambda12(r32), new AlertDialog$$ExternalSyntheticLambda13(this, r32, r4));
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        OnButtonClickListener r0 = this.onBackButtonListener;
        if (r0 == null) goto L6;
        r0.onClick(this, -2);
        return;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r2) {
        super.onCreate(r2);
        inflateContent(true);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }

    public void redPositive() {
        TextView r0 = (TextView) getButton(-1);
        if (r0 == null) goto L6;
        r0.setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        ScrollView r0 = this.contentScrollView;
        if (r0 == null) goto L6;
        r0.postDelayed(r2, r3);
        return;
    }

    public void setBackgroundColor(int r3) {
        this.backgroundColor = r3;
        Drawable r32 = this.shadowDrawable;
        if (r32 == null) goto L6;
        r32.setColorFilter(new PorterDuffColorFilter(this.backgroundColor, PorterDuff.Mode.MULTIPLY));
        return;
    }

    public void setBlurParams(float r1, boolean r2, boolean r3) {
        this.blurAlpha = r1;
        this.blurBehind = r2;
        this.blurredBackground = r3;
    }

    public void setCanCancel(boolean r1) {
        this.canCacnel = r1;
    }

    public void setCancelDialog(boolean r1) {
        this.withCancelDialog = r1;
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean r1) {
        super.setCanceledOnTouchOutside(r1);
    }

    public void setDismissDialogByButtons(boolean r1) {
        this.dismissDialogByButtons = r1;
    }

    public void setFocusable(boolean r3) {
        if (this.focusable != r3) goto L5;
        return;
    L5:
        this.focusable = r3;
        Window r32 = getWindow();
        WindowManager.LayoutParams r0 = r32.getAttributes();
        if (this.focusable == false) goto L8;
        r0.softInputMode = 16;
        r0.flags &= -131073;
    L9:
        r32.setAttributes(r0);
        return;
    L8:
        r0.softInputMode = 48;
        r0.flags |= 131072;
        goto L9
    }

    public void setItemColor(int r2, int r3, int r4) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= this.itemViews.size()) goto L9;
        AlertDialogCell r1 = this.itemViews.get(r2);
        AlertDialogCell.m5651$$Nest$fgettextView(r1).setTextColor(r3);
        AlertDialogCell.m5650$$Nest$fgetimageView(r1).setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        return;
    }

    public void setMessage(CharSequence r2) {
        this.message = r2;
        if (this.messageTextView == null) goto L10;
        boolean r22 = TextUtils.isEmpty(r2);
        TextView r0 = this.messageTextView;
        if (r22 == true) goto L8;
        r0.setText(this.message);
        this.messageTextView.setVisibility(0);
        return;
    L8:
        r0.setVisibility(8);
        return;
    }

    public void setMessageLineSpacing(float r2) {
        TextView r1 = this.messageTextView;
        if (r1 == null) goto L6;
        r1.setLineSpacing(AndroidUtilities.dp(r2), 1.0f);
        return;
    }

    public void setNegativeButton(CharSequence r1, OnButtonClickListener r2) {
        this.negativeButtonText = r1;
        this.negativeButtonListener = r2;
    }

    public void setNeutralButton(CharSequence r1, OnButtonClickListener r2) {
        this.neutralButtonText = r1;
        this.neutralButtonListener = r2;
    }

    @Override // android.app.Dialog
    public void setOnCancelListener(DialogInterface.OnCancelListener r1) {
        this.onCancelListener = r1;
        super.setOnCancelListener(r1);
    }

    public void setPositiveButton(CharSequence r1, OnButtonClickListener r2) {
        this.positiveButtonText = r1;
        this.positiveButtonListener = r2;
    }

    public void setPositiveButtonListener(OnButtonClickListener r1) {
        this.positiveButtonListener = r1;
    }

    public void setProgress(int r3) {
        this.currentProgress = r3;
        LineProgressView r0 = this.lineProgressView;
        if (r0 == null) goto L6;
        r0.setProgress(r3 / 100.0f, true);
        updateLineProgressTextView();
        return;
    }

    public AlertDialog setShowStarsBalance(boolean r1) {
        this.needStarsBalance = r1;
        return this;
    }

    public void setTextColor(int r2) {
        SpoilersTextView r0 = this.titleTextView;
        if (r0 == null) goto L5;
        r0.setTextColor(r2);
    L5:
        TextView r1 = this.messageTextView;
        if (r1 == null) goto L9;
        r1.setTextColor(r2);
        return;
    }

    public void setTextSize(int r3, int r4) {
        SpoilersTextView r0 = this.titleTextView;
        if (r0 == null) goto L5;
        r0.setTextSize(1, r3);
    L5:
        TextView r2 = this.messageTextView;
        if (r2 == null) goto L9;
        r2.setTextSize(1, r4);
        return;
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence r1) {
        this.title = r1;
        SpoilersTextView r0 = this.titleTextView;
        if (r0 == null) goto L6;
        r0.setText(r1);
        return;
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L5;
        return;
    L5:
        this.dismissed = false;
        super.show();
        FrameLayout r0 = this.progressViewContainer;
        if (r0 != null) goto L8;
    L10:
        this.shownAt = System.currentTimeMillis();
        return;
    L8:
        if (this.progressViewStyle != 3) goto L10;
        r0.setScaleX(0.0f);
        this.progressViewContainer.setScaleY(0.0f);
        this.progressViewContainer.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator(1.3f)).setDuration(190).start();
        goto L10
    }

    public void showCancelAlert() {
        if (this.canCacnel == true) goto L5;
        return;
    L5:
        if (this.cancelDialog != null) goto L14;
        Builder r0 = new Builder(getContext(), this.resourcesProvider);
        r0.setTitle(LocaleController.getString(R.string.StopLoadingTitle));
        r0.setMessage(LocaleController.getString(R.string.StopLoading));
        r0.setPositiveButton(LocaleController.getString(R.string.WaitMore), null);
        r0.setNegativeButton(LocaleController.getString(R.string.Stop), new AlertDialog$$ExternalSyntheticLambda10(this));
        r0.setOnDismissListener(new AlertDialog$$ExternalSyntheticLambda11(this));
        this.cancelDialog = r0.show();     // Catch: Exception -> L10
        return;
    L15:
        return;
    }

    public void showDelayed(long r2) {
        AndroidUtilities.cancelRunOnUIThread(this.showRunnable);
        AndroidUtilities.runOnUIThread(this.showRunnable, r2);
    }

    public boolean supportsNativeBlur() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        ScrollView r0 = this.contentScrollView;
        if (r0 == null) goto L6;
        r0.removeCallbacks(r2);
        return;
    }
}
