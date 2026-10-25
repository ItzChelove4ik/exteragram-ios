package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StaticLayoutEx;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIPNotificationsLayout extends LinearLayout {
    VoIPBackgroundProvider backgroundProvider;
    boolean lockAnimation;
    Runnable onViewsUpdated;
    TextPaint textPaint;
    TransitionSet transitionSet;
    ArrayList<NotificationView> viewToAdd;
    ArrayList<NotificationView> viewToRemove;
    HashMap<String, NotificationView> viewsByTag;
    boolean wasChanged;

    /* renamed from: org.telegram.ui.Components.voip.VoIPNotificationsLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends Visibility {
        final /* synthetic */ VoIPNotificationsLayout this$0;

        public AnonymousClass1(VoIPNotificationsLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Visibility
        public Animator onAppear(ViewGroup r3, View r4, TransitionValues r5, TransitionValues r6) {
            AnimatorSet r2 = new AnimatorSet();
            r4.setAlpha(0.0f);
            r4.setScaleY(0.6f);
            r4.setScaleX(0.6f);
            r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_X, new float[]{0.6f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_Y, new float[]{0.6f, 1.0f})});
            r2.setInterpolator(CubicBezierInterpolator.EASE_OUT_BACK);
            return r2;
        }

        @Override // android.transition.Visibility
        public Animator onDisappear(ViewGroup r4, View r5, TransitionValues r6, TransitionValues r7) {
            AnimatorSet r3 = new AnimatorSet();
            if ((r5 instanceof NotificationView) == false) goto L5;
            ((NotificationView) r5).ignoreShader = true;
        L5:
            r3.playTogether(new Animator[]{ObjectAnimator.ofFloat(r5, View.ALPHA, new float[]{0.7f, 0.0f}), ObjectAnimator.ofFloat(r5, View.SCALE_X, new float[]{1.0f, 0.6f}), ObjectAnimator.ofFloat(r5, View.SCALE_Y, new float[]{1.0f, 0.6f})});
            r3.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r3;
        }
    }

    public static class NotificationView extends FrameLayout {
        private final VoIPBackgroundProvider backgroundProvider;
        private final RectF bgRect;
        ImageView iconView;
        boolean ignoreShader;
        public String tag;
        TextView textView;

        public NotificationView(Context r9, VoIPBackgroundProvider r10, int r11) {
            super(r9);
            this.bgRect = new RectF();
            setFocusable(true);
            setFocusableInTouchMode(true);
            this.backgroundProvider = r10;
            r10.attach(this);
            ImageView r102 = new ImageView(r9);
            this.iconView = r102;
            addView(r102, LayoutHelper.createFrame(24, 24.0f, 16, 8.0f, 2.0f, 8.0f, 2.0f));
            TextView r103 = new TextView(r9);
            this.textView = r103;
            r103.setTextColor(-1);
            float r104 = 14.0f;
            this.textView.setTextSize(1, 14.0f);
            View r92 = this.textView;
            if (r11 == 0) goto L6;
            r104 = 36.0f;
        L6:
            addView(r92, LayoutHelper.createFrame(-2, -2.0f, 16, r104, 2.0f, 14.0f, 2.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            this.bgRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.backgroundProvider.setDarkTranslation(getX() + ((View) getParent()).getX(), getY() + ((View) getParent()).getY());
            int r6 = this.backgroundProvider.getDarkPaint(this.ignoreShader).getAlpha();
            r9.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), r6, 31);
            this.backgroundProvider.getDarkPaint(this.ignoreShader).setAlpha(255);
            r9.drawRoundRect(this.bgRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.backgroundProvider.getDarkPaint(this.ignoreShader));
            this.backgroundProvider.getDarkPaint(this.ignoreShader).setAlpha(r6);
            if (this.backgroundProvider.isReveal() == false) goto L5;
            int r92 = this.backgroundProvider.getRevealDarkPaint().getAlpha();
            this.backgroundProvider.getRevealDarkPaint().setAlpha(255);
            r9.drawRoundRect(this.bgRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.backgroundProvider.getRevealDarkPaint());
            this.backgroundProvider.getRevealDarkPaint().setAlpha(r92);
        L5:
            r9.restore();
            super.dispatchDraw(r9);
        }

        public void setText(CharSequence r13) {
            int r4 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(120.0f);
            StaticLayout r132 = StaticLayoutEx.createStaticLayout(r13, this.textView.getPaint(), r4, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r4, 10);
            if (r132 == null) goto L8;
            int r0 = 0;
            r4 = 0;
        L6:
            if (r0 >= r132.getLineCount()) goto L8;
            r4 = (int) Math.max(r4, Math.ceil(r132.getLineWidth(r0)));
            r0 = r0 + 1;
        L8:
            this.textView.setMaxWidth(r4);
            this.textView.setText(r13);
        }
    }

    public static /* synthetic */ void $r8$lambda$oH1WlV_FrHKgW6SMv122v0jfMls(VoIPNotificationsLayout r0) {
        r0.lambda$lock$0();
    }

    public VoIPNotificationsLayout(Context r3, VoIPBackgroundProvider r4) {
        super(r3);
        this.viewsByTag = new HashMap();
        this.viewToAdd = new ArrayList();
        this.viewToRemove = new ArrayList();
        this.textPaint = new TextPaint();
        setOrientation(1);
        this.backgroundProvider = r4;
        TransitionSet r32 = new TransitionSet();
        this.transitionSet = r32;
        r32.addTransition(new Fade(2).setDuration(150)).addTransition(new ChangeBounds().setDuration(200)).addTransition(new AnonymousClass1(this).setDuration(200));
        this.transitionSet.setOrdering(0);
        this.textPaint.setTextSize(AndroidUtilities.dp(14.0f));
    }

    private /* synthetic */ void lambda$lock$0() {
        this.lockAnimation = false;
        runDelayed();
    }

    private void lock() {
        this.lockAnimation = true;
        AndroidUtilities.runOnUIThread(new VoIPNotificationsLayout$$ExternalSyntheticLambda0(this), 700);
    }

    private void runDelayed() {
        if (this.viewToAdd.isEmpty() == false) goto L8;
        if (this.viewToRemove.isEmpty() == false) goto L8;
        return;
    L8:
        if (getParent() == null) goto L10;
        TransitionManager.beginDelayedTransition(this, this.transitionSet);
    L10:
        int r0 = 0;
        int r1 = 0;
    L12:
        if (r1 >= this.viewToAdd.size()) goto L21;
        NotificationView r2 = this.viewToAdd.get(r1);
        int r3 = 0;
    L15:
        if (r3 >= this.viewToRemove.size()) goto L20;
        if (r2.tag.equals(this.viewToRemove.get(r3).tag) == true) goto L18;
        r3 = r3 + 1;
        goto L15
    L18:
        this.viewToAdd.remove(r1);
        this.viewToRemove.remove(r3);
        r1 = r1 - 1;
    L20:
        r1 = r1 + 1;
        goto L12
    L21:
        int r12 = 0;
    L23:
        if (r12 >= this.viewToAdd.size()) goto L25;
        addView(this.viewToAdd.get(r12), LayoutHelper.createLinear(-2, -2, 1, 4, 0, 0, 4));
        r12 = r12 + 1;
        goto L23
    L25:
        int r13 = 0;
    L27:
        if (r13 >= this.viewToRemove.size()) goto L29;
        removeView(this.viewToRemove.get(r13));
        r13 = r13 + 1;
        goto L27
    L29:
        this.viewsByTag.clear();
    L31:
        if (r0 >= getChildCount()) goto L33;
        NotificationView r14 = (NotificationView) getChildAt(r0);
        this.viewsByTag.put(r14.tag, r14);
        r0 = r0 + 1;
        goto L31
    L33:
        this.viewToAdd.clear();
        this.viewToRemove.clear();
        lock();
        Runnable r10 = this.onViewsUpdated;
        if (r10 == null) goto L44;
        r10.run();
        return;
    }

    public void addNotification(int r8, String r9, String r10, boolean r11) {
        if (this.viewsByTag.get(r10) == null) goto L5;
        return;
    L5:
        NotificationView r112 = new NotificationView(getContext(), this.backgroundProvider, r8);
        r112.tag = r10;
        r112.setText(r9);
        r112.iconView.setImageResource(r8);
        this.viewsByTag.put(r10, r112);
        if (this.lockAnimation == false) goto L9;
        this.viewToAdd.add(r112);
        return;
    L9:
        this.wasChanged = true;
        addView(r112, LayoutHelper.createLinear(-2, -2, 1, 4, 0, 0, 4));
    }

    public void animateLayoutChanges() {
        if (this.wasChanged == false) goto L5;
        lock();
    L5:
        this.wasChanged = false;
    }

    public void beforeLayoutChanges() {
        this.wasChanged = false;
        if (this.lockAnimation == false) goto L5;
        return;
    L5:
        if (getParent() == null) goto L9;
        TransitionManager.beginDelayedTransition(this, this.transitionSet);
        return;
    }

    public CharSequence ellipsize(CharSequence r3) {
        if (r3 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return TextUtils.ellipsize(r3, this.textPaint, AndroidUtilities.dp(300.0f), TextUtils.TruncateAt.END);
    }

    public int getChildsHight() {
        int r2 = getChildCount();
        if (r2 <= 0) goto L5;
        int r0 = AndroidUtilities.dp(16.0f);
    L7:
        return r0 + (r2 * AndroidUtilities.dp(32.0f));
    L5:
        r0 = 0;
        goto L7
    }

    public void removeNotification(String r2) {
        NotificationView r22 = this.viewsByTag.remove(r2);
        this.backgroundProvider.detach(r22);
        if (r22 != null) goto L5;
        return;
    L5:
        if (this.lockAnimation == true) goto L7;
        this.wasChanged = true;
        removeView(r22);
        return;
    L7:
        if (this.viewToAdd.remove(r22) == true) goto L14;
        this.viewToRemove.add(r22);
        return;
    }

    public void setOnViewsUpdated(Runnable r1) {
        this.onViewsUpdated = r1;
    }
}
