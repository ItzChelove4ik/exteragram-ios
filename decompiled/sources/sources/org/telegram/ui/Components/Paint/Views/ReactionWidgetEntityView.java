package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.Reactions.ReactionImageHolder;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Stories.StoryReactionWidgetBackground;

/* loaded from: classes7.dex */
public class ReactionWidgetEntityView extends EntityView {
    Size baseSize;
    AnimatedFloat crossfadeBackgrounds;
    ReactionsLayoutInBubble.VisibleReaction currentReaction;
    private float drawScale;
    boolean mirror;
    ReactionImageHolder nextReactionHolder;
    StoryReactionWidgetBackground outBackground;
    AnimatedFloat progressToNext;
    ReactionImageHolder reactionHolder;
    StoryReactionWidgetBackground storyReactionWidgetBackground;

    /* renamed from: org.telegram.ui.Components.Paint.Views.ReactionWidgetEntityView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ReactionWidgetEntityView this$0;
        final /* synthetic */ boolean[] val$mirrored;

        public AnonymousClass1(ReactionWidgetEntityView r1, boolean[] r2) {
            this.this$0 = r1;
            this.val$mirrored = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            boolean[] r32 = this.val$mirrored;
            if (r32[0] == true) goto L5;
            r32[0] = true;
            ReactionWidgetEntityView r33 = this.this$0;
            r33.storyReactionWidgetBackground.setMirror(r33.mirror, false);
        L5:
            this.this$0.setRotationY(0.0f);
            ReactionWidgetEntityView.m11581$$Nest$fputdrawScale(this.this$0, 1.0f);
        }
    }

    public class StickerViewSelectionView extends EntityView.SelectionView {
        private RectF arcRect;
        final /* synthetic */ ReactionWidgetEntityView this$0;

        public StickerViewSelectionView(ReactionWidgetEntityView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.arcRect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            super.onDraw(r14);
            int r8 = r14.getSaveCount();
            float r1 = getShowAlpha();
            if (r1 > 0.0f) goto L6;
            return;
        L6:
            if (r1 >= 1.0f) goto L8;
            r14.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r1 * 255.0f), 31);
        L8:
            float r12 = AndroidUtilities.dp(1.0f);
            float r7 = AndroidUtilities.dpf2(5.66f);
            float r10 = (r12 + r7) + AndroidUtilities.dp(15.0f);
            float r11 = (getMeasuredWidth() / 2) - r10;
            float r122 = r10 + (2.0f * r11);
            this.arcRect.set(r10, r10, r122, r122);
            r14.drawArc(this.arcRect, 0.0f, 180.0f, false, this.paint);
            r14.drawArc(this.arcRect, 180.0f, 180.0f, false, this.paint);
            float r112 = r11 + r10;
            r14.drawCircle(r10, r112, r7, this.dotStrokePaint);
            r14.drawCircle(r10, r112, r7 - AndroidUtilities.dp(1.0f), this.dotPaint);
            r14.drawCircle(r122, r112, r7, this.dotStrokePaint);
            r14.drawCircle(r122, r112, r7 - AndroidUtilities.dp(1.0f), this.dotPaint);
            r14.restoreToCount(r8);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.SelectionView
        public int pointInsideHandle(float r7, float r8) {
            float r0 = AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(19.5f);
            float r02 = r0 + r1;
            float r4 = r02 * 2.0f;
            float r2 = ((getMeasuredHeight() - r4) / 2.0f) + r02;
            if (r7 <= (r02 - r1)) goto L13;
            if (r8 <= (r2 - r1)) goto L13;
            if (r7 >= (r02 + r1)) goto L13;
            if (r8 >= (r2 + r1)) goto L13;
            return 1;
        L13:
            if (r7 <= (((getMeasuredWidth() - r4) + r02) - r1)) goto L23;
            if (r8 <= (r2 - r1)) goto L23;
            if (r7 >= ((r02 + (getMeasuredWidth() - r4)) + r1)) goto L23;
            if (r8 >= (r2 + r1)) goto L23;
            return 2;
        L23:
            if ((Math.pow(r7 - r6, 2.0d) + Math.pow(r8 - r6, 2.0d)) >= Math.pow(getMeasuredWidth() / 2.0f, 2.0d)) goto L26;
            return 3;
        L26:
            return 0;
        }
    }

    /* renamed from: $r8$lambda$ojrvi9Uia8d-12pM6JcgAH06cGM, reason: not valid java name */
    public static /* synthetic */ void m11580$r8$lambda$ojrvi9Uia8d12pM6JcgAH06cGM(ReactionWidgetEntityView r0, boolean[] r1, ValueAnimator r2) {
        r0.lambda$mirror$0(r1, r2);
    }

    /* renamed from: -$$Nest$fputdrawScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11581$$Nest$fputdrawScale(ReactionWidgetEntityView r0, float r1) {
        r0.drawScale = r1;
    }

    public ReactionWidgetEntityView(Context r1, PointF r2, Size r3) {
        super(r1, r2);
        this.storyReactionWidgetBackground = new StoryReactionWidgetBackground(this);
        this.outBackground = new StoryReactionWidgetBackground(this);
        this.reactionHolder = new ReactionImageHolder(this);
        this.nextReactionHolder = new ReactionImageHolder(this);
        this.progressToNext = new AnimatedFloat(this);
        AnimatedFloat r12 = new AnimatedFloat(this);
        this.crossfadeBackgrounds = r12;
        this.drawScale = 1.0f;
        this.baseSize = r3;
        r12.set(1.0f, true);
        this.progressToNext.set(1.0f, true);
        List<TLRPC.TL_availableReaction> r13 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsList();
        ReactionImageHolder r22 = this.reactionHolder;
        ReactionsLayoutInBubble.VisibleReaction r14 = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(findHeartReaction(r13));
        this.currentReaction = r14;
        r22.setVisibleReaction(r14);
        updatePosition();
    }

    private String findHeartReaction(List<TLRPC.TL_availableReaction> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L11;
        if (r4.get(r0).title.equals("Red Heart") == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r4.get(r0).reaction;
    L11:
        return r4.get(0).reaction;
    }

    private /* synthetic */ void lambda$mirror$0(boolean[] r7, ValueAnimator r8) {
        float r82 = ((Float) r8.getAnimatedValue()).floatValue();
        if (r82 >= 0.5f) goto L7;
        float r83 = r82 / 0.5f;
        setRotationY(90.0f * r83);
        this.drawScale = ((1.0f - r83) * 0.3f) + 0.7f;
        invalidate();
        return;
    L7:
        if (r7[0] == true) goto L9;
        r7[0] = true;
        this.storyReactionWidgetBackground.setMirror(this.mirror, false);
    L9:
        float r84 = (r82 - 0.5f) / 0.5f;
        setRotationY((1.0f - r84) * (-90.0f));
        this.drawScale = (r84 * 0.3f) + 0.7f;
        invalidate();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public boolean allowHaptic() {
        return false;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public boolean allowLongPressOnSelected() {
        return true;
    }

    public void changeStyle(boolean r3) {
        StoryReactionWidgetBackground r0 = this.storyReactionWidgetBackground;
        if (r3 == true) goto L5;
        r0.nextStyle();
    L9:
        invalidate();
        return;
    L5:
        this.outBackground = r0;
        this.storyReactionWidgetBackground = new StoryReactionWidgetBackground(this);
        if (this.outBackground.isDarkStyle() == true) goto L8;
        this.storyReactionWidgetBackground.nextStyle();
    L8:
        this.storyReactionWidgetBackground.setMirror(this.mirror, false);
        this.storyReactionWidgetBackground.updateShadowLayer(getScaleX());
        this.crossfadeBackgrounds.set(0.0f, true);
        goto L9
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public EntityView.SelectionView createSelectionView() {
        return new StickerViewSelectionView(this, getContext());
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        int r0 = getPadding();
        float r1 = this.crossfadeBackgrounds.set(1.0f);
        if (r1 != 1.0f) goto L5;
        this.outBackground = null;
    L5:
        r9.save();
        float r3 = this.drawScale;
        r9.scale(r3, r3, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
        StoryReactionWidgetBackground r32 = this.outBackground;
        if (r32 == null) goto L8;
        r32.setAlpha((int) ((1.0f - r1) * 255.0f));
        StoryReactionWidgetBackground r33 = this.outBackground;
        Size r6 = this.baseSize;
        r33.setBounds(r0, r0, ((int) r6.width) - r0, ((int) r6.height) - r0);
        this.outBackground.draw(r9);
    L8:
        this.storyReactionWidgetBackground.setAlpha((int) (r1 * 255.0f));
        StoryReactionWidgetBackground r12 = this.storyReactionWidgetBackground;
        Size r34 = this.baseSize;
        r12.setBounds(r0, r0, ((int) r34.width) - r0, ((int) r34.height) - r0);
        this.storyReactionWidgetBackground.draw(r9);
        Rect r13 = AndroidUtilities.rectTmp2;
        float r02 = (this.storyReactionWidgetBackground.getBounds().width() * 0.61f) / 2.0f;
        r13.set((int) (this.storyReactionWidgetBackground.getBounds().centerX() - r02), (int) (this.storyReactionWidgetBackground.getBounds().centerY() - r02), (int) (this.storyReactionWidgetBackground.getBounds().centerX() + r02), (int) (this.storyReactionWidgetBackground.getBounds().centerY() + r02));
        float r03 = this.progressToNext.set(1.0f);
        this.reactionHolder.setBounds(r13);
        this.nextReactionHolder.setBounds(r13);
        ReactionImageHolder r35 = this.reactionHolder;
        if (this.storyReactionWidgetBackground.isDarkStyle() == false) goto L11;
        int r4 = -1;
    L12:
        r35.setColor(r4);
        if (r03 != 1.0f) goto L15;
        this.reactionHolder.draw(r9);
    L16:
        r9.restore();
        return;
    L15:
        r9.save();
        float r2 = 1.0f - r03;
        r9.scale(r2, r2, r13.centerX(), r13.top);
        this.nextReactionHolder.setAlpha(r2);
        this.nextReactionHolder.draw(r9);
        r9.restore();
        r9.save();
        r9.scale(r03, r03, r13.centerX(), r13.bottom);
        this.reactionHolder.setAlpha(r03);
        this.reactionHolder.draw(r9);
        r9.restore();
        goto L16
    L11:
        r4 = -16777216;
        goto L12
    }

    public ReactionsLayoutInBubble.VisibleReaction getCurrentReaction() {
        return this.currentReaction;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getMaxScale() {
        return 1.8f;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getMinScale() {
        return 0.5f;
    }

    public int getPadding() {
        return (int) ((this.baseSize.height - AndroidUtilities.dp(84.0f)) / 2.0f);
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        float r1 = getMeasuredWidth() * (getScale() + 0.4f);
        float r4 = r1 / 2.0f;
        float r12 = r1 * r02;
        return new RectOld((getPositionX() - r4) * r02, (getPositionY() - r4) * r02, r12, r12);
    L5:
        return new RectOld();
    }

    public boolean isDark() {
        return this.storyReactionWidgetBackground.isDarkStyle();
    }

    public boolean isMirrored() {
        return this.mirror;
    }

    public void mirror(boolean r3) {
        boolean r0 = !this.mirror;
        this.mirror = r0;
        if (r3 == true) goto L6;
        this.storyReactionWidgetBackground.setMirror(r0, r3);
        return;
    L6:
        boolean[] r32 = {false};
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r02.addUpdateListener(new ReactionWidgetEntityView$$ExternalSyntheticLambda0(this, r32));
        r02.addListener(new AnonymousClass1(this, r32));
        r02.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r02.setDuration(350);
        r02.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.reactionHolder.onAttachedToWindow(true);
        this.nextReactionHolder.onAttachedToWindow(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.reactionHolder.onAttachedToWindow(false);
        this.nextReactionHolder.onAttachedToWindow(false);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) this.baseSize.width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) this.baseSize.height, TLObject.FLAG_30));
    }

    public void setCurrentReaction(ReactionsLayoutInBubble.VisibleReaction r2, boolean r3) {
        if (Objects.equals(this.currentReaction, r2) == false) goto L5;
        return;
    L5:
        if (r3 == true) goto L8;
        this.currentReaction = r2;
        this.reactionHolder.setVisibleReaction(r2);
        invalidate();
        return;
    L8:
        this.currentReaction = r2;
        this.nextReactionHolder.setVisibleReaction(r2);
        ReactionImageHolder r22 = this.reactionHolder;
        this.reactionHolder = this.nextReactionHolder;
        this.nextReactionHolder = r22;
        this.progressToNext.set(0.0f, true);
        invalidate();
    }

    @Override // android.view.View
    public void setScaleX(float r2) {
        if (getScaleX() == r2) goto L6;
        super.setScaleX(r2);
        this.storyReactionWidgetBackground.updateShadowLayer(r2);
        invalidate();
        return;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public void updatePosition() {
        Size r0 = this.baseSize;
        float r1 = r0.width / 2.0f;
        float r02 = r0.height / 2.0f;
        setX(getPositionX() - r1);
        setY(getPositionY() - r02);
        updateSelectionView();
    }
}
