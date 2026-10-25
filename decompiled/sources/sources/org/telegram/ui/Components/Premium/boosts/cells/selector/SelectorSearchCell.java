package org.telegram.ui.Components.Premium.boosts.cells.selector;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class SelectorSearchCell extends ScrollView {
    public ArrayList<GroupCreateSpan> allSpans;
    private final LinearGradient bottomGradient;
    private final AnimatedFloat bottomGradientAlpha;
    private final Matrix bottomGradientMatrix;
    private final Paint bottomGradientPaint;
    public float containerHeight;
    private GroupCreateSpan currentDeletingSpan;
    private EditTextBoldCursor editText;
    private int fieldY;
    private int hintTextWidth;
    private boolean ignoreScrollEvent;
    private boolean ignoreTextChange;
    private Utilities.Callback<String> onSearchTextChange;
    private int prevResultContainerHeight;
    private final Theme.ResourcesProvider resourcesProvider;
    public int resultContainerHeight;
    private boolean scroll;
    public SpansContainer spansContainer;
    private final LinearGradient topGradient;
    private final AnimatedFloat topGradientAlpha;
    private final Matrix topGradientMatrix;
    private final Paint topGradientPaint;
    private Runnable updateHeight;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        final /* synthetic */ SelectorSearchCell this$0;

        public AnonymousClass1(SelectorSearchCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (SelectorSearchCell.m12175$$Nest$fgetcurrentDeletingSpan(this.this$0) == null) goto L6;
            SelectorSearchCell.m12175$$Nest$fgetcurrentDeletingSpan(this.this$0).cancelDeleteAnimation();
            SelectorSearchCell.m12184$$Nest$fputcurrentDeletingSpan(this.this$0, null);
        L6:
            if (r3.getAction() != 0) goto L11;
            if (AndroidUtilities.showKeyboard(this) == true) goto L11;
            this.this$0.fullScroll(130);
            clearFocus();
            requestFocus();
        L11:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ SelectorSearchCell this$0;

        public AnonymousClass2(SelectorSearchCell r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (SelectorSearchCell.m12180$$Nest$fgetignoreTextChange(this.this$0) == false) goto L6;
            return;
        L6:
            if (SelectorSearchCell.m12181$$Nest$fgetonSearchTextChange(this.this$0) == null) goto L10;
            if (r2 == null) goto L11;
            SelectorSearchCell.m12181$$Nest$fgetonSearchTextChange(this.this$0).run(r2.toString());
            return;
        L11:
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnKeyListener {
        final /* synthetic */ SelectorSearchCell this$0;
        final /* synthetic */ Runnable val$afterDelete;
        final /* synthetic */ HashSet val$selectedIds;
        private boolean wasEmpty;

        public AnonymousClass3(SelectorSearchCell r1, HashSet r2, Runnable r3) {
            this.this$0 = r1;
            this.val$selectedIds = r2;
            this.val$afterDelete = r3;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View r2, int r3, KeyEvent r4) {
            if (r3 != 67) goto L19;
            boolean r32 = true;
            if (r4.getAction() != 0) goto L12;
            if (SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).length() == 0) goto L10;
            r32 = false;
        L10:
            this.wasEmpty = r32;
            goto L19
        L12:
            if (r4.getAction() != 1) goto L19;
            if (this.wasEmpty == false) goto L19;
            if (this.this$0.allSpans.isEmpty() == true) goto L19;
            ArrayList<GroupCreateSpan> r22 = this.this$0.allSpans;
            SelectorSearchCell.m12189$$Nest$monDeleteSpanClicked(this.this$0, r22.get(r22.size() - 1), this.val$selectedIds, this.val$afterDelete);
            return true;
        L19:
            return false;
        }
    }

    public class SpansContainer extends ViewGroup {
        private View addingSpan;
        private ArrayList<View> animAddingSpans;
        private ArrayList<View> animRemovingSpans;
        private boolean animationStarted;
        private ArrayList<Animator> animators;
        private AnimatorSet currentAnimation;
        private final int heightDp;
        private final int padDp;
        private final int padXDp;
        private final int padYDp;
        private final ArrayList<View> removingSpans;
        final /* synthetic */ SelectorSearchCell this$0;

        /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell$SpansContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ GroupCreateSpan val$span;

            public AnonymousClass1(SpansContainer r1, GroupCreateSpan r2) {
                this.this$1 = r1;
                this.val$span = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                this.this$1.removeView(this.val$span);
                SpansContainer.m12190$$Nest$fgetremovingSpans(this.this$1).clear();
                SpansContainer.m12193$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m12192$$Nest$fputanimationStarted(this.this$1, false);
                SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                if (SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$1.this$0) == null) goto L6;
                SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$1.this$0).run();
            L6:
                if (SelectorSearchCell.m12182$$Nest$fgetscroll(this.this$1.this$0) == false) goto L9;
                this.this$1.this$0.fullScroll(130);
                SelectorSearchCell.m12188$$Nest$fputscroll(this.this$1.this$0, false);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell$SpansContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ ArrayList val$toDelete;

            public AnonymousClass2(SpansContainer r1, ArrayList r2) {
                this.this$1 = r1;
                this.val$toDelete = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                int r0 = 0;
            L3:
                int r1 = this.val$toDelete.size();
                SpansContainer r2 = this.this$1;
                if (r0 >= r1) goto L6;
                r2.removeView((View) this.val$toDelete.get(r0));
                r0 = r0 + 1;
                goto L3
            L6:
                SpansContainer.m12191$$Nest$fputaddingSpan(r2, null);
                SpansContainer.m12190$$Nest$fgetremovingSpans(this.this$1).clear();
                SpansContainer.m12193$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m12192$$Nest$fputanimationStarted(this.this$1, false);
                SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                if (SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$1.this$0) == null) goto L10;
                SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$1.this$0).run();
            L10:
                if (SelectorSearchCell.m12182$$Nest$fgetscroll(this.this$1.this$0) == false) goto L14;
                this.this$1.this$0.fullScroll(130);
                SelectorSearchCell.m12188$$Nest$fputscroll(this.this$1.this$0, false);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell$SpansContainer$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ ArrayList val$spans;

            public AnonymousClass3(SpansContainer r1, ArrayList r2) {
                this.this$1 = r1;
                this.val$spans = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                int r0 = 0;
            L3:
                int r1 = this.val$spans.size();
                SpansContainer r2 = this.this$1;
                if (r0 >= r1) goto L6;
                r2.removeView((View) this.val$spans.get(r0));
                r0 = r0 + 1;
                goto L3
            L6:
                SpansContainer.m12190$$Nest$fgetremovingSpans(r2).clear();
                SpansContainer.m12193$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m12192$$Nest$fputanimationStarted(this.this$1, false);
                SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                if (SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$1.this$0) == null) goto L10;
                SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$1.this$0).run();
            L10:
                if (SelectorSearchCell.m12182$$Nest$fgetscroll(this.this$1.this$0) == false) goto L14;
                this.this$1.this$0.fullScroll(130);
                SelectorSearchCell.m12188$$Nest$fputscroll(this.this$1.this$0, false);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$qcCxBIQWcQBDcTYILDKVAeMEHsg(SpansContainer r0) {
            r0.lambda$onMeasure$0();
        }

        /* renamed from: -$$Nest$fgetremovingSpans, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12190$$Nest$fgetremovingSpans(SpansContainer r0) {
            return r0.removingSpans;
        }

        /* renamed from: -$$Nest$fputaddingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12191$$Nest$fputaddingSpan(SpansContainer r0, View r1) {
            r0.addingSpan = r1;
        }

        /* renamed from: -$$Nest$fputanimationStarted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12192$$Nest$fputanimationStarted(SpansContainer r0, boolean r1) {
            r0.animationStarted = r1;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12193$$Nest$fputcurrentAnimation(SpansContainer r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        public SpansContainer(SelectorSearchCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.animAddingSpans = new ArrayList();
            this.animRemovingSpans = new ArrayList();
            this.animators = new ArrayList();
            this.removingSpans = new ArrayList();
            this.padDp = 14;
            this.padYDp = 4;
            this.padXDp = 6;
            this.heightDp = 28;
        }

        private /* synthetic */ void lambda$onMeasure$0() {
            this.this$0.fullScroll(130);
        }

        private void setupEndValues() {
            AnimatorSet r0 = this.currentAnimation;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            int r02 = 0;
            int r1 = 0;
        L7:
            if (r1 >= this.animAddingSpans.size()) goto L10;
            this.animAddingSpans.get(r1).setScaleX(1.0f);
            this.animAddingSpans.get(r1).setScaleY(1.0f);
            this.animAddingSpans.get(r1).setAlpha(1.0f);
            r1 = r1 + 1;
        L10:
            if (r02 >= this.animRemovingSpans.size()) goto L12;
            this.animRemovingSpans.get(r02).setScaleX(0.0f);
            this.animRemovingSpans.get(r02).setScaleY(0.0f);
            this.animRemovingSpans.get(r02).setAlpha(0.0f);
            r02 = r02 + 1;
            goto L10
        L12:
            this.animAddingSpans.clear();
            this.animRemovingSpans.clear();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r22 = getChildCount();
            int r42 = 0;
        L3:
            if (r42 >= r22) goto L5;
            View r52 = getChildAt(r42);
            r52.layout(0, 0, r52.getMeasuredWidth(), r52.getMeasuredHeight());
            r42 = r42 + 1;
            goto L3
        }

        @Override // android.view.View
        public void onMeasure(int r18, int r19) {
            int r1 = getChildCount();
            int r2 = View.MeasureSpec.getSize(r18);
            float r3 = 28.0f;
            int r4 = r2 - AndroidUtilities.dp(28.0f);
            int r6 = AndroidUtilities.dp(10.0f);
            int r5 = AndroidUtilities.dp(10.0f);
            int r8 = 0;
            int r9 = 0;
            int r10 = 0;
        L3:
            Property r11 = View.TRANSLATION_Y;
            Property r12 = View.TRANSLATION_X;
            if (r8 >= r1) goto L36;
            View r15 = getChildAt(r8);
            float r182 = r3;
            if ((r15 instanceof GroupCreateSpan) == true) goto L8;
            int r16 = r1;
        L35:
            r8 = r8 + 1;
            r3 = r182;
            r1 = r16;
            goto L3
        L8:
            r15.measure(View.MeasureSpec.makeMeasureSpec(r2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r182), TLObject.FLAG_30));
            boolean r32 = this.removingSpans.contains(r15);
            if (r32 == true) goto L14;
            if ((r15.getMeasuredWidth() + r9) <= r4) goto L14;
            r6 = r6 + (r15.getMeasuredHeight() + AndroidUtilities.dp(4.0f));
            r9 = 0;
        L14:
            if ((r15.getMeasuredWidth() + r10) <= r4) goto L16;
            r5 = r5 + (r15.getMeasuredHeight() + AndroidUtilities.dp(4.0f));
            r10 = 0;
        L16:
            int r13 = AndroidUtilities.dp(14.0f) + r9;
            if (this.animationStarted == true) goto L20;
            if (r32 == false) goto L22;
            r15.setTranslationX(AndroidUtilities.dp(14.0f) + r10);
            r15.setTranslationY(r5);
            goto L20
        L22:
            if (this.removingSpans.isEmpty() == true) goto L30;
            float r132 = r13;
            if (r15.getTranslationX() == r132) goto L26;
            r16 = r1;
            this.animators.add(ObjectAnimator.ofFloat(r15, r12, new float[]{r132}));
        L27:
            float r7 = r6;
            if (r15.getTranslationY() == r7) goto L32;
            this.animators.add(ObjectAnimator.ofFloat(r15, r11, new float[]{r7}));
        L32:
            if (r32 == true) goto L34;
            r9 = r9 + (r15.getMeasuredWidth() + AndroidUtilities.dp(6.0f));
        L34:
            r10 = r10 + (r15.getMeasuredWidth() + AndroidUtilities.dp(6.0f));
            goto L35
        L26:
            r16 = r1;
            goto L27
        L30:
            r16 = r1;
            r15.setTranslationX(r13);
            r15.setTranslationY(r6);
        L20:
            r16 = r1;
            goto L32
        L36:
            float r183 = r3;
            if (AndroidUtilities.isTablet() == false) goto L39;
            int r14 = AndroidUtilities.dp(376.0f) / 3;
        L41:
            if ((r4 - r9) >= r14) goto L44;
            r6 = r6 + AndroidUtilities.dp(36.0f);
            r9 = 0;
        L44:
            if ((r4 - r10) >= r14) goto L46;
            r5 = r5 + AndroidUtilities.dp(36.0f);
        L46:
            SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r4 - r9, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r183), TLObject.FLAG_30));
            EditTextBoldCursor r17 = SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0);
            if (SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).getMeasuredWidth() <= SelectorSearchCell.m12178$$Nest$fgethintTextWidth(this.this$0)) goto L49;
            boolean r33 = true;
        L50:
            r17.setHintVisible(r33, true);
            if (this.animationStarted == true) goto L74;
            int r52 = r5 + AndroidUtilities.dp(38.0f);
            int r92 = r9 + AndroidUtilities.dp(16.0f);
            SelectorSearchCell.m12185$$Nest$fputfieldY(this.this$0, r6);
            if (this.currentAnimation == null) goto L66;
            int r62 = r6 + AndroidUtilities.dp(38.0f);
            SelectorSearchCell r110 = this.this$0;
            r110.resultContainerHeight = r62;
            float r42 = r62;
            if (r110.containerHeight == r42) goto L57;
            this.animators.add(r110.getContainerHeightAnimator(r42));
        L57:
            float r34 = r92;
            if (SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).getTranslationX() == r34) goto L61;
            this.animators.add(ObjectAnimator.ofFloat(SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0), r12, new float[]{r34}));
        L61:
            if (SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).getTranslationY() == SelectorSearchCell.m12177$$Nest$fgetfieldY(this.this$0)) goto L63;
            this.animators.add(ObjectAnimator.ofFloat(SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0), r11, new float[]{SelectorSearchCell.m12177$$Nest$fgetfieldY(this.this$0)}));
        L63:
            SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(false);
            this.currentAnimation.playTogether(this.animators);
            this.currentAnimation.setDuration(180);
            this.currentAnimation.setInterpolator(new LinearInterpolator());
            this.currentAnimation.start();
            this.animationStarted = true;
            if (SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$0) == null) goto L72;
            SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$0).run();
        L72:
            SelectorSearchCell r111 = this.this$0;
            SelectorSearchCell.m12187$$Nest$fputprevResultContainerHeight(r111, r111.resultContainerHeight);
        L83:
            setMeasuredDimension(r2, (int) this.this$0.containerHeight);
            return;
        L66:
            SelectorSearchCell r112 = this.this$0;
            r112.resultContainerHeight = r52;
            r112.containerHeight = r52;
            SelectorSearchCell.m12176$$Nest$fgeteditText(r112).setTranslationX(r92);
            SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).setTranslationY(SelectorSearchCell.m12177$$Nest$fgetfieldY(this.this$0));
            if (SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$0) == null) goto L70;
            SelectorSearchCell.m12183$$Nest$fgetupdateHeight(this.this$0).run();
        L70:
            if (SelectorSearchCell.m12182$$Nest$fgetscroll(this.this$0) == false) goto L72;
            post(new SelectorSearchCell$SpansContainer$$ExternalSyntheticLambda0(this));
            SelectorSearchCell.m12188$$Nest$fputscroll(this.this$0, false);
            goto L72
        L74:
            if (this.currentAnimation == null) goto L83;
            if (SelectorSearchCell.m12179$$Nest$fgetignoreScrollEvent(this.this$0) == true) goto L81;
            if (this.removingSpans.isEmpty() == false) goto L81;
            SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).bringPointIntoView(SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).getSelectionStart());
        L81:
            if (SelectorSearchCell.m12182$$Nest$fgetscroll(this.this$0) == false) goto L83;
            this.this$0.fullScroll(130);
            SelectorSearchCell.m12188$$Nest$fputscroll(this.this$0, false);
            goto L83
        L49:
            r33 = false;
            goto L50
        L39:
            Point r113 = AndroidUtilities.displaySize;
            r14 = (Math.min(r113.x, r113.y) - AndroidUtilities.dp(154.0f)) / 3;
            goto L41
        }

        public void removeAllSpans(boolean r7) {
            SelectorSearchCell.m12186$$Nest$fputignoreScrollEvent(this.this$0, true);
            ArrayList r0 = new ArrayList(this.this$0.allSpans);
            this.removingSpans.clear();
            this.removingSpans.addAll(this.this$0.allSpans);
            this.this$0.allSpans.clear();
            int r2 = 0;
            int r3 = 0;
        L4:
            if (r3 >= r0.size()) goto L6;
            ((GroupCreateSpan) r0.get(r3)).setOnClickListener(null);
            r3 = r3 + 1;
            goto L4
        L6:
            setupEndValues();
            if (r7 == false) goto L12;
            this.animationStarted = false;
            AnimatorSet r72 = new AnimatorSet();
            this.currentAnimation = r72;
            r72.addListener(new AnonymousClass3(this, r0));
            this.animators.clear();
            this.animAddingSpans.clear();
            this.animRemovingSpans.clear();
        L10:
            if (r2 >= r0.size()) goto L17;
            GroupCreateSpan r73 = (GroupCreateSpan) r0.get(r2);
            this.animAddingSpans.add(r73);
            this.animators.add(ObjectAnimator.ofFloat(r73, View.SCALE_X, new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(r73, View.SCALE_Y, new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(r73, View.ALPHA, new float[]{1.0f, 0.0f}));
            r2 = r2 + 1;
        L17:
            requestLayout();
            return;
        L12:
            int r74 = 0;
        L14:
            if (r74 >= r0.size()) goto L16;
            removeView((View) r0.get(r74));
            r74 = r74 + 1;
            goto L14
        L16:
            this.removingSpans.clear();
            this.currentAnimation = null;
            this.animationStarted = false;
            SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(true);
            goto L17
        }

        public void removeSpan(GroupCreateSpan r5) {
            SelectorSearchCell.m12186$$Nest$fputignoreScrollEvent(this.this$0, true);
            this.this$0.allSpans.remove(r5);
            r5.setOnClickListener(null);
            setupEndValues();
            this.animationStarted = false;
            AnimatorSet r0 = new AnimatorSet();
            this.currentAnimation = r0;
            r0.addListener(new AnonymousClass1(this, r5));
            this.removingSpans.clear();
            this.removingSpans.add(r5);
            this.animAddingSpans.clear();
            this.animRemovingSpans.clear();
            this.animAddingSpans.add(r5);
            this.animators.clear();
            ArrayList<Animator> r02 = this.animators;
            Property r3 = View.SCALE_X;
            r02.add(ObjectAnimator.ofFloat(r5, r3, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r03 = this.animators;
            Property r32 = View.SCALE_Y;
            r03.add(ObjectAnimator.ofFloat(r5, r32, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r04 = this.animators;
            Property r2 = View.ALPHA;
            r04.add(ObjectAnimator.ofFloat(r5, r2, new float[]{1.0f, 0.0f}));
            requestLayout();
        }

        public void updateSpans(ArrayList<GroupCreateSpan> r9, ArrayList<GroupCreateSpan> r10, boolean r11) {
            SelectorSearchCell.m12186$$Nest$fputignoreScrollEvent(this.this$0, true);
            this.this$0.allSpans.removeAll(r9);
            this.this$0.allSpans.addAll(r10);
            this.removingSpans.clear();
            this.removingSpans.addAll(r9);
            int r0 = 0;
            int r2 = 0;
        L4:
            if (r2 >= r9.size()) goto L6;
            r9.get(r2).setOnClickListener(null);
            r2 = r2 + 1;
            goto L4
        L6:
            setupEndValues();
            if (r11 == false) goto L16;
            this.animationStarted = false;
            AnimatorSet r112 = new AnimatorSet();
            this.currentAnimation = r112;
            r112.addListener(new AnonymousClass2(this, r9));
            this.animators.clear();
            this.animAddingSpans.clear();
            this.animRemovingSpans.clear();
            int r113 = 0;
        L9:
            int r1 = r9.size();
            Property r3 = View.ALPHA;
            Property r4 = View.SCALE_Y;
            Property r5 = View.SCALE_X;
            if (r113 >= r1) goto L12;
            GroupCreateSpan r12 = r9.get(r113);
            this.animRemovingSpans.add(r12);
            this.animators.add(ObjectAnimator.ofFloat(r12, r5, new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(r12, r4, new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(r12, r3, new float[]{1.0f, 0.0f}));
            r113 = r113 + 1;
            goto L9
        L12:
            int r92 = 0;
        L14:
            if (r92 >= r10.size()) goto L22;
            GroupCreateSpan r114 = r10.get(r92);
            this.animAddingSpans.add(r114);
            this.animators.add(ObjectAnimator.ofFloat(r114, r5, new float[]{0.01f, 1.0f}));
            this.animators.add(ObjectAnimator.ofFloat(r114, r4, new float[]{0.01f, 1.0f}));
            this.animators.add(ObjectAnimator.ofFloat(r114, r3, new float[]{0.0f, 1.0f}));
            r92 = r92 + 1;
        L22:
            if (r0 >= r10.size()) goto L24;
            addView(r10.get(r0));
            r0 = r0 + 1;
            goto L22
        L24:
            requestLayout();
            return;
        L16:
            int r115 = 0;
        L18:
            if (r115 >= r9.size()) goto L20;
            removeView(r9.get(r115));
            r115 = r115 + 1;
            goto L18
        L20:
            this.addingSpan = null;
            this.removingSpans.clear();
            this.currentAnimation = null;
            this.animationStarted = false;
            SelectorSearchCell.m12176$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(true);
            goto L22
        }
    }

    public static /* synthetic */ void $r8$lambda$E59JrizczpPztTd4EEPstEe2Vs0(SelectorSearchCell r0, HashSet r1, Runnable r2, View r3) {
        r0.lambda$updateSpans$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$sohTv8pK23FmYXOTHGFTfklc_3I(SelectorSearchCell r0, ValueAnimator r1) {
        r0.lambda$getContainerHeightAnimator$1(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentDeletingSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateSpan m12175$$Nest$fgetcurrentDeletingSpan(SelectorSearchCell r0) {
        return r0.currentDeletingSpan;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m12176$$Nest$fgeteditText(SelectorSearchCell r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgetfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12177$$Nest$fgetfieldY(SelectorSearchCell r0) {
        return r0.fieldY;
    }

    /* renamed from: -$$Nest$fgethintTextWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12178$$Nest$fgethintTextWidth(SelectorSearchCell r0) {
        return r0.hintTextWidth;
    }

    /* renamed from: -$$Nest$fgetignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12179$$Nest$fgetignoreScrollEvent(SelectorSearchCell r0) {
        return r0.ignoreScrollEvent;
    }

    /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12180$$Nest$fgetignoreTextChange(SelectorSearchCell r0) {
        return r0.ignoreTextChange;
    }

    /* renamed from: -$$Nest$fgetonSearchTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m12181$$Nest$fgetonSearchTextChange(SelectorSearchCell r0) {
        return r0.onSearchTextChange;
    }

    /* renamed from: -$$Nest$fgetscroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12182$$Nest$fgetscroll(SelectorSearchCell r0) {
        return r0.scroll;
    }

    /* renamed from: -$$Nest$fgetupdateHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12183$$Nest$fgetupdateHeight(SelectorSearchCell r0) {
        return r0.updateHeight;
    }

    /* renamed from: -$$Nest$fputcurrentDeletingSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12184$$Nest$fputcurrentDeletingSpan(SelectorSearchCell r0, GroupCreateSpan r1) {
        r0.currentDeletingSpan = r1;
    }

    /* renamed from: -$$Nest$fputfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12185$$Nest$fputfieldY(SelectorSearchCell r0, int r1) {
        r0.fieldY = r1;
    }

    /* renamed from: -$$Nest$fputignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12186$$Nest$fputignoreScrollEvent(SelectorSearchCell r0, boolean r1) {
        r0.ignoreScrollEvent = r1;
    }

    /* renamed from: -$$Nest$fputprevResultContainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12187$$Nest$fputprevResultContainerHeight(SelectorSearchCell r0, int r1) {
        r0.prevResultContainerHeight = r1;
    }

    /* renamed from: -$$Nest$fputscroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12188$$Nest$fputscroll(SelectorSearchCell r0, boolean r1) {
        r0.scroll = r1;
    }

    /* renamed from: -$$Nest$monDeleteSpanClicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12189$$Nest$monDeleteSpanClicked(SelectorSearchCell r0, View r1, HashSet r2, Runnable r3) {
        r0.onDeleteSpanClicked(r1, r2, r3);
    }

    public SelectorSearchCell(Context r27, Theme.ResourcesProvider r28, Runnable r29) {
        super(r27);
        this.allSpans = new ArrayList();
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.topGradientAlpha = new AnimatedFloat(this, 0, 300, r6);
        Shader.TileMode r16 = Shader.TileMode.CLAMP;
        LinearGradient r9 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(8.0f), new int[]{-16777216, 0}, new float[]{0.0f, 1.0f}, r16);
        this.topGradient = r9;
        Paint r10 = new Paint(1);
        this.topGradientPaint = r10;
        this.topGradientMatrix = new Matrix();
        this.bottomGradientAlpha = new AnimatedFloat(this, 0, 300, r6);
        LinearGradient r18 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(8.0f), new int[]{0, -16777216}, new float[]{0.0f, 1.0f}, r16);
        this.bottomGradient = r18;
        Paint r2 = new Paint(1);
        this.bottomGradientPaint = r2;
        this.bottomGradientMatrix = new Matrix();
        r10.setShader(r9);
        PorterDuff.Mode r4 = PorterDuff.Mode.DST_OUT;
        r10.setXfermode(new PorterDuffXfermode(r4));
        r2.setShader(r18);
        r2.setXfermode(new PorterDuffXfermode(r4));
        this.resourcesProvider = r28;
        this.updateHeight = r29;
        setVerticalScrollBarEnabled(false);
        AndroidUtilities.setScrollViewEdgeEffectColor(this, Theme.getColor(Theme.key_windowBackgroundWhite));
        SpansContainer r0 = new SpansContainer(this, r27);
        this.spansContainer = r0;
        addView(r0, LayoutHelper.createFrame(-1, -2.0f));
        AnonymousClass1 r02 = new AnonymousClass1(this, r27);
        this.editText = r02;
        if (Build.VERSION.SDK_INT < 25) goto L5;
        r02.setRevealOnFocusHint(false);
    L5:
        this.editText.setTextSize(1, 16.0f);
        this.editText.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r28));
        this.editText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r28));
        EditTextBoldCursor r03 = this.editText;
        int r22 = Theme.key_groupcreate_cursor;
        r03.setCursorColor(Theme.getColor(r22, r28));
        this.editText.setHandlesColor(Theme.getColor(r22, r28));
        this.editText.setCursorWidth(1.5f);
        EditTextBoldCursor r04 = this.editText;
        r04.setInputType(r04.getInputType() | 176);
        this.editText.setSingleLine(true);
        this.editText.setBackgroundDrawable(null);
        this.editText.setVerticalScrollBarEnabled(false);
        this.editText.setHorizontalScrollBarEnabled(false);
        this.editText.setTextIsSelectable(false);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setImeOptions(268435462);
        EditTextBoldCursor r05 = this.editText;
        if (LocaleController.isRTL == false) goto L8;
        int r23 = 5;
    L9:
        r05.setGravity(r23 | 16);
        this.spansContainer.addView(this.editText);
        this.editText.setHintText(LocaleController.getString(R.string.Search));
        this.hintTextWidth = (int) this.editText.getPaint().measureText(LocaleController.getString(R.string.Search));
        this.editText.addTextChangedListener(new AnonymousClass2(this));
        return;
    L8:
        r23 = 3;
        goto L9
    }

    private /* synthetic */ void lambda$getContainerHeightAnimator$1(ValueAnimator r1) {
        setContainerHeight(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$updateSpans$0(HashSet r1, Runnable r2, View r3) {
        onDeleteSpanClicked(r3, r1, r2);
    }

    private void onDeleteSpanClicked(View r3, HashSet<Long> r4, Runnable r5) {
        if (this.allSpans.contains(r3) == true) goto L5;
        return;
    L5:
        GroupCreateSpan r32 = (GroupCreateSpan) r3;
        if (r32.isDeleting() == false) goto L9;
        this.currentDeletingSpan = null;
        this.spansContainer.removeSpan(r32);
        r4.remove(Long.valueOf(r32.getUid()));
        r5.run();
        return;
    L9:
        GroupCreateSpan r42 = this.currentDeletingSpan;
        if (r42 == null) goto L12;
        r42.cancelDeleteAnimation();
        this.currentDeletingSpan = null;
    L12:
        this.currentDeletingSpan = r32;
        r32.startDeleteAnimation();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        float r3 = getScrollY();
        r15.saveLayerAlpha(0.0f, r3, getWidth(), getHeight() + r0, 255, 31);
        super.dispatchDraw(r15);
        r15.save();
        float r152 = this.topGradientAlpha.set(canScrollVertically(-1));
        this.topGradientMatrix.reset();
        this.topGradientMatrix.postTranslate(0.0f, r3);
        this.topGradient.setLocalMatrix(this.topGradientMatrix);
        this.topGradientPaint.setAlpha((int) (r152 * 255.0f));
        r15.drawRect(0.0f, r3, getWidth(), AndroidUtilities.dp(8.0f) + r0, this.topGradientPaint);
        float r2 = this.bottomGradientAlpha.set(canScrollVertically(1));
        this.bottomGradientMatrix.reset();
        this.bottomGradientMatrix.postTranslate(0.0f, (getHeight() + r0) - AndroidUtilities.dp(8.0f));
        this.bottomGradient.setLocalMatrix(this.bottomGradientMatrix);
        this.bottomGradientPaint.setAlpha((int) (r2 * 255.0f));
        r15.drawRect(0.0f, (getHeight() + r0) - AndroidUtilities.dp(8.0f), getWidth(), r0 + getHeight(), this.bottomGradientPaint);
        r15.restore();
        r15.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return super.dispatchTouchEvent(r1);
    }

    public Animator getContainerHeightAnimator(float r4) {
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.containerHeight, r4});
        r42.addUpdateListener(new SelectorSearchCell$$ExternalSyntheticLambda1(this));
        return r42;
    }

    public EditTextBoldCursor getEditText() {
        return this.editText;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(150.0f), Integer.MIN_VALUE));
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View r4, Rect r5, boolean r6) {
        if (this.ignoreScrollEvent == false) goto L6;
        this.ignoreScrollEvent = false;
        return false;
    L6:
        r5.offset(r4.getLeft() - r4.getScrollX(), r4.getTop() - r4.getScrollY());
        r5.top += this.fieldY + AndroidUtilities.dp(20.0f);
        r5.bottom += this.fieldY + AndroidUtilities.dp(50.0f);
        return super.requestChildRectangleOnScreen(r4, r5, r6);
    }

    public void setContainerHeight(float r1) {
        this.containerHeight = r1;
        SpansContainer r0 = this.spansContainer;
        if (r0 == null) goto L6;
        r0.requestLayout();
        return;
    }

    public void setHintText(String r1, boolean r2) {
        this.editText.setHintText(r1, r2);
    }

    public void setOnSearchTextChange(Utilities.Callback<String> r1) {
        this.onSearchTextChange = r1;
    }

    public void setText(CharSequence r2) {
        this.ignoreTextChange = true;
        this.editText.setText(r2);
        this.ignoreTextChange = false;
    }

    public void updateSpans(boolean r21, HashSet<Long> r22, Runnable r23, List<TLRPC.TL_help_country> r24) {
        MessagesController r3 = MessagesController.getInstance(UserConfig.selectedAccount);
        ArrayList<GroupCreateSpan> r4 = new ArrayList();
        ArrayList<GroupCreateSpan> r5 = new ArrayList();
        int r7 = 0;
    L4:
        if (r7 >= this.allSpans.size()) goto L9;
        GroupCreateSpan r8 = this.allSpans.get(r7);
        if (r22.contains(Long.valueOf(r8.getUid())) == true) goto L8;
        r4.add(r8);
    L8:
        r7 = r7 + 1;
        goto L4
    L9:
        Iterator<Long> r72 = r22.iterator();
    L11:
        if (r72.hasNext() == false) goto L35;
        Long r82 = r72.next();
        long r9 = r82.longValue();
        int r11 = 0;
    L14:
        if (r11 >= this.allSpans.size()) goto L20;
        if (this.allSpans.get(r11).getUid() == r9) goto L11;
        r11 = r11 + 1;
        goto L14
    L20:
        if (r9 < 0) goto L22;
        Object r83 = r3.getUser(r82);
    L23:
        if (r24 == null) goto L30;
        Iterator<TLRPC.TL_help_country> r112 = r24.iterator();
    L26:
        if (r112.hasNext() == false) goto L30;
        TLRPC.TL_help_country r12 = r112.next();
        if (r12.default_name.hashCode() != r9) goto L26;
        Object r16 = r12;
    L31:
        if (r16 == null) goto L11;
        GroupCreateSpan r14 = new GroupCreateSpan(getContext(), r16, null, true, this.resourcesProvider);
        r14.setOnClickListener(new SelectorSearchCell$$ExternalSyntheticLambda0(this, r22, r23));
        r5.add(r14);
    L30:
        r16 = r83;
        goto L31
    L22:
        r83 = r3.getChat(Long.valueOf(-r9));
        goto L23
    L35:
        if (r4.isEmpty() == true) goto L37;
    L38:
        this.spansContainer.updateSpans(r4, r5, r21);
    L39:
        this.editText.setOnKeyListener(new AnonymousClass3(this, r22, r23));
        return;
    L37:
        if (r5.isEmpty() == true) goto L39;
        goto L38
    }
}
