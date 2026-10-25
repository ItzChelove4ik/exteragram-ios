package com.exteragram.messenger.preferences.chats.components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextPaint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.preferences.components.PreviewBackgroundDrawable;
import com.exteragram.messenger.preferences.components.PreviewColors;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;

/* loaded from: classes4.dex */
public abstract class StickerShapeCell extends LinearLayout implements CustomPreferenceCell {
    private final StickerShape[] stickerShape;

    public static class StickerShape extends FrameLayout {
        private final PreviewBackgroundDrawable backgroundDrawable;
        private final boolean isRounded;
        private final boolean isRoundedAsMsg;
        private float progress;
        private final RectF rect;
        private final TextPaint textPaint;

        public static /* synthetic */ void $r8$lambda$EqwKwMHTz5mB0JBPYFVpVxaivjs(StickerShape r0, ValueAnimator r1) {
            r0.lambda$setSelected$0(r1);
        }

        /* renamed from: -$$Nest$msetSelected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1701$$Nest$msetSelected(StickerShape r0, boolean r1, boolean r2) {
            r0.setSelected(r1, r2);
        }

        public StickerShape(Context r4, boolean r5, boolean r6) {
            super(r4);
            boolean r0 = true;
            TextPaint r42 = new TextPaint(1);
            this.textPaint = r42;
            this.rect = new RectF();
            this.backgroundDrawable = new PreviewBackgroundDrawable(10.0f);
            setWillNotDraw(false);
            this.isRounded = r5;
            this.isRoundedAsMsg = r6;
            r42.setTextSize(AndroidUtilities.dp(13.0f));
            if (r5 == true) goto L7;
            if (r6 == true) goto L7;
            if (ExteraConfig.getStickerShape() != 0) goto L7;
        L15:
            setSelected(r0, false);
            return;
        L7:
            if (r5 == true) goto L9;
        L10:
            if (r6 == true) goto L12;
        L14:
            r0 = false;
            goto L15
        L12:
            if (ExteraConfig.getStickerShape() != 2) goto L14;
        L9:
            if (ExteraConfig.getStickerShape() == 1) goto L15;
            goto L10
        }

        private /* synthetic */ void lambda$setSelected$0(ValueAnimator r1) {
            setProgress(((Float) r1.getAnimatedValue()).floatValue());
        }

        private void setProgress(float r4) {
            this.progress = r4;
            this.textPaint.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), Theme.getColor(Theme.key_windowBackgroundWhiteValueText), r4));
            TextPaint r0 = this.textPaint;
            if (r4 < 0.5f) goto L5;
            Typeface r1 = AndroidUtilities.bold();
        L6:
            r0.setTypeface(r1);
            this.backgroundDrawable.setSelectionProgress(r4);
            invalidate();
            return;
        L5:
            r1 = AndroidUtilities.regular();
            goto L6
        }

        private void setSelected(boolean r3, boolean r4) {
            if (r3 == false) goto L4;
            float r32 = 1.0f;
        L5:
            float r0 = this.progress;
            if (r32 != r0) goto L9;
            if (r4 == false) goto L9;
            return;
        L9:
            if (r4 == false) goto L12;
            ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{r0, r32}).setDuration(250);
            r33.setInterpolator(Easings.easeInOutQuad);
            r33.addUpdateListener(new StickerShapeCell$StickerShape$$ExternalSyntheticLambda0(this));
            r33.start();
            return;
        L12:
            setProgress(r32);
            return;
        L4:
            r32 = 0.0f;
            goto L5
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            this.backgroundDrawable.setBounds(0, 0, getMeasuredWidth(), AndroidUtilities.dp(80.0f));
            this.backgroundDrawable.draw(r9);
            if (this.isRounded == false) goto L7;
            int r0 = R.string.StickerShapeRounded;
        L10:
            r9.drawText(LocaleController.getString(r0), (getMeasuredWidth() - ((int) Math.ceil(this.textPaint.measureText(r0)))) >> 1, AndroidUtilities.dp(102.0f), this.textPaint);
            this.rect.set(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), getMeasuredWidth() - AndroidUtilities.dp(10.0f), AndroidUtilities.dp(70.0f));
            Theme.dialogs_onlineCirclePaint.setColor(PreviewColors.getMockColor(false));
            boolean r02 = this.isRounded;
            if (r02 == false) goto L13;
        L16:
            if (r02 == false) goto L19;
            r9.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Theme.dialogs_onlineCirclePaint);
            return;
        L19:
            Rect r03 = new Rect();
            this.rect.round(r03);
            int r8 = AndroidUtilities.dp(SharedConfig.bubbleRadius);
            float r5 = r8;
            float r82 = r8 / 3;
            ShapeDrawable r2 = new ShapeDrawable(new RoundRectShape(new float[]{r5, r5, r5, r5, r5, r5, r82, r82}, null, null));
            r2.getPaint().setColor(Theme.dialogs_onlineCirclePaint.getColor());
            r2.setBounds(r03);
            r2.draw(r9);
            return;
        L13:
            if (this.isRoundedAsMsg == true) goto L16;
            r9.drawRoundRect(this.rect, AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), Theme.dialogs_onlineCirclePaint);
            return;
        L7:
            if (this.isRoundedAsMsg == false) goto L9;
            r0 = R.string.StickerShapeRoundedMsg;
            goto L10
        L9:
            r0 = R.string.Default;
            goto L10
        }
    }

    public static /* synthetic */ void $r8$lambda$rqQsP5QpIqoVaECtXmjtqxuTJE8(StickerShapeCell r0, int r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public StickerShapeCell(Context r12) {
        super(r12);
        this.stickerShape = new StickerShape[3];
        setOrientation(0);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(13.0f), 0);
        int r2 = 0;
    L3:
        if (r2 >= 3) goto L13;
        boolean r3 = true;
        if (r2 != 1) goto L7;
        boolean r4 = true;
    L9:
        if (r2 == 2) goto L12;
        r3 = false;
    L12:
        this.stickerShape[r2] = new StickerShape(r12, r4, r3);
        ScaleStateListAnimator.apply(this.stickerShape[r2], 0.03f, 1.5f);
        addView(this.stickerShape[r2], LayoutHelper.createLinear(-1, -1, 0.5f, 8, 0, 8, 0));
        this.stickerShape[r2].setOnClickListener(new StickerShapeCell$$ExternalSyntheticLambda0(this, r2));
        r2 = r2 + 1;
        goto L3
    L7:
        r4 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$0(int r6, View r7) {
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L10;
        StickerShape r2 = this.stickerShape[r1];
        if (r7 != r2) goto L8;
        boolean r4 = true;
    L9:
        StickerShape.m1701$$Nest$msetSelected(r2, r4, true);
        r1 = r1 + 1;
        goto L4
    L8:
        r4 = false;
        goto L9
    L10:
        ExteraConfig.setStickerShape(r6);
        updateStickerPreview();
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof StickerShapeCell) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.stickerShape != ((StickerShapeCell) r4).stickerShape) goto L10;
        return true;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        int r0 = 0;
    L4:
        if (r0 >= 3) goto L6;
        this.stickerShape[r0].invalidate();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(130.0f), TLObject.FLAG_30));
    }

    public abstract void updateStickerPreview();
}
