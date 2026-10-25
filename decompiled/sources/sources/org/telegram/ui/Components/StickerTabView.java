package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SvgHelper;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class StickerTabView extends FrameLayout {
    private static int indexPointer;
    public float dragOffset;
    ValueAnimator dragOffsetAnimator;
    boolean expanded;
    boolean hasSavedLeft;
    ImageView iconView;
    BackupImageView imageView;
    public final int index;
    public boolean inited;
    public boolean isChatSticker;
    float lastLeft;
    boolean roundImage;
    public SvgHelper.SvgDrawable svgThumb;
    TextView textView;
    private float textWidth;
    public int type;
    View visibleView;

    /* renamed from: org.telegram.ui.Components.StickerTabView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        final /* synthetic */ StickerTabView this$0;

        public AnonymousClass1(StickerTabView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r1, TextView.BufferType r2) {
            super.setText(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerTabView$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ StickerTabView this$0;
        final /* synthetic */ ViewGroup val$parent;

        public AnonymousClass2(StickerTabView r1, ViewGroup r2) {
            this.this$0 = r1;
            this.val$parent = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            StickerTabView r0 = this.this$0;
            r0.dragOffset = ((Float) r2.getAnimatedValue()).floatValue();
            this.this$0.invalidate();
            this.val$parent.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerTabView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerTabView this$0;
        final /* synthetic */ ViewGroup val$parent;

        public AnonymousClass3(StickerTabView r1, ViewGroup r2) {
            this.this$0 = r1;
            this.val$parent = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StickerTabView r22 = this.this$0;
            r22.dragOffset = 0.0f;
            r22.invalidate();
            this.val$parent.invalidate();
        }
    }

    /* renamed from: $r8$lambda$d8GZ94Bvidd1BLh5RVA5MaA-Kbk, reason: not valid java name */
    public static /* synthetic */ void m13070$r8$lambda$d8GZ94Bvidd1BLh5RVA5MaAKbk(StickerTabView r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$new$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public StickerTabView(Context r8, int r9) {
        super(r8);
        this.type = r9;
        int r0 = indexPointer;
        indexPointer = r0 + 1;
        this.index = r0;
        if (r9 != 2) goto L5;
        BackupImageView r92 = new BackupImageView(getContext());
        this.imageView = r92;
        r92.setLayerNum(1);
        this.imageView.setAspectFit(false);
        this.imageView.setRoundRadius(AndroidUtilities.dp(6.0f));
        addView(this.imageView, LayoutHelper.createFrame(26, 26, 17));
        this.visibleView = this.imageView;
    L8:
        AnonymousClass1 r93 = new AnonymousClass1(this, r8);
        this.textView = r93;
        r93.addOnLayoutChangeListener(new StickerTabView$$ExternalSyntheticLambda0(this));
        this.textView.setLines(1);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setTextSize(1, 11.0f);
        this.textView.setGravity(1);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 81, 8.0f, 0.0f, 8.0f, 10.0f));
        this.textView.setVisibility(8);
        return;
    L5:
        if (r9 != 1) goto L7;
        ImageView r94 = new ImageView(r8);
        this.iconView = r94;
        r94.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.iconView, LayoutHelper.createFrame(24, 24, 17));
        this.visibleView = this.iconView;
        goto L8
    L7:
        BackupImageView r95 = new BackupImageView(getContext());
        this.imageView = r95;
        r95.setLayerNum(1);
        this.imageView.setAspectFit(true);
        this.imageView.setRoundRadius(AndroidUtilities.dp(6.0f));
        addView(this.imageView, LayoutHelper.createFrame(26, 26, 17));
        this.visibleView = this.imageView;
        goto L8
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        TextView r12 = this.textView;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.getLayout() == null) goto L9;
        this.textWidth = this.textView.getLayout().getLineWidth(0);
        return;
    }

    public void animateIfPositionChanged(ViewGroup r5) {
        float r0 = getLeft();
        float r1 = this.lastLeft;
        if (r0 != r1) goto L5;
    L10:
        this.hasSavedLeft = false;
        return;
    L5:
        if (this.hasSavedLeft == false) goto L10;
        this.dragOffset = r1 - getLeft();
        ValueAnimator r02 = this.dragOffsetAnimator;
        if (r02 == null) goto L9;
        r02.removeAllListeners();
        this.dragOffsetAnimator.cancel();
    L9:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{this.dragOffset, 0.0f});
        this.dragOffsetAnimator = r03;
        r03.addUpdateListener(new AnonymousClass2(this, r5));
        this.dragOffsetAnimator.addListener(new AnonymousClass3(this, r5));
        this.dragOffsetAnimator.start();
        goto L10
    }

    public float getTextWidth() {
        return this.textWidth;
    }

    public void saveXPosition() {
        this.lastLeft = getLeft();
        this.hasSavedLeft = true;
        invalidate();
    }

    public void setExpanded(boolean r6) {
        int r0 = this.type;
        if (r0 == 2) goto L31;
        this.expanded = r6;
        if (r0 != 1) goto L8;
        float r2 = 24.0f;
    L9:
        if (r0 != 1) goto L11;
        float r02 = 38.0f;
    L12:
        ViewGroup.LayoutParams r3 = this.visibleView.getLayoutParams();
        if (r6 == false) goto L15;
        float r4 = r02;
    L16:
        r3.width = AndroidUtilities.dp(r4);
        ViewGroup.LayoutParams r32 = this.visibleView.getLayoutParams();
        if (r6 == false) goto L19;
        r2 = r02;
    L19:
        r32.height = AndroidUtilities.dp(r2);
        TextView r03 = this.textView;
        if (r6 == false) goto L22;
        int r62 = 0;
    L23:
        r03.setVisibility(r62);
        if (this.type != 1) goto L26;
        return;
    L26:
        if (this.roundImage == false) goto L30;
        this.imageView.setRoundRadius(AndroidUtilities.dp(this.visibleView.getLayoutParams().width / 2.0f));
        return;
    L30:
        return;
    L22:
        r62 = 8;
        goto L23
    L15:
        r4 = r2;
        goto L16
    L11:
        r02 = 44.0f;
        goto L12
    L8:
        r2 = 26.0f;
        goto L9
    }

    public void setRoundImage() {
        this.roundImage = true;
    }

    public void updateExpandProgress(float r10) {
        int r0 = this.type;
        if (r0 != 2) goto L6;
        return;
    L6:
        if (this.expanded == true) goto L8;
        this.visibleView.setTranslationX(0.0f);
        this.visibleView.setTranslationY(0.0f);
        this.visibleView.setScaleX(1.0f);
        this.visibleView.setScaleY(1.0f);
        return;
    L8:
        if (r0 != 1) goto L10;
        float r4 = 24.0f;
    L11:
        if (r0 != 1) goto L13;
        float r02 = 38.0f;
    L14:
        float r2 = 1.0f - r10;
        this.visibleView.setTranslationY((((AndroidUtilities.dp(36.0f - r4) / 2.0f) - (AndroidUtilities.dp(86.0f - r02) / 2.0f)) * r2) - (AndroidUtilities.dp(8.0f) * r10));
        this.visibleView.setTranslationX(((AndroidUtilities.dp(33.0f - r4) / 2.0f) - (AndroidUtilities.dp(ScrollSlidingTabStrip.EXPANDED_WIDTH - r02) / 2.0f)) * r2);
        this.textView.setAlpha(Math.max(0.0f, (r10 - 0.5f) / 0.5f));
        this.textView.setTranslationY((-AndroidUtilities.dp(40.0f)) * r2);
        this.textView.setTranslationX((-AndroidUtilities.dp(12.0f)) * r2);
        this.visibleView.setPivotX(0.0f);
        this.visibleView.setPivotY(0.0f);
        float r42 = ((r4 / r02) * r2) + r10;
        this.visibleView.setScaleX(r42);
        this.visibleView.setScaleY(r42);
        return;
    L13:
        r02 = 44.0f;
        goto L14
    L10:
        r4 = 26.0f;
        goto L11
    }
}
