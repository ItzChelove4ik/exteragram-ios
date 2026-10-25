package com.exteragram.messenger.preferences.appearance.components;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.preferences.components.PreviewBackgroundDrawable;
import com.exteragram.messenger.preferences.components.PreviewColors;
import com.exteragram.messenger.utils.ui.FabUiHelper;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;

/* loaded from: classes4.dex */
public abstract class FabShapeCell extends LinearLayout implements CustomPreferenceCell {
    private final FabShape[] fabShape;

    public static class FabShape extends FrameLayout {
        private final PreviewBackgroundDrawable backgroundDrawable;
        private float progress;
        private final RectF rect;
        private final boolean squareFab;

        /* renamed from: $r8$lambda$pZyrO1cECymeelgfIKAe-sg6Zyk, reason: not valid java name */
        public static /* synthetic */ void m1668$r8$lambda$pZyrO1cECymeelgfIKAesg6Zyk(FabShape r0, ValueAnimator r1) {
            r0.lambda$setSelected$0(r1);
        }

        /* renamed from: -$$Nest$msetSelected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1669$$Nest$msetSelected(FabShape r0, boolean r1, boolean r2) {
            r0.setSelected(r1, r2);
        }

        public FabShape(Context r2, boolean r3) {
            super(r2);
            this.rect = new RectF();
            PreviewBackgroundDrawable r22 = new PreviewBackgroundDrawable(12.0f);
            this.backgroundDrawable = r22;
            setWillNotDraw(false);
            this.squareFab = r3;
            setBackground(r22);
            if (r3 == true) goto L5;
        L6:
            if (r3 == false) goto L8;
        L10:
            boolean r23 = false;
        L11:
            setSelected(r23, false);
            return;
        L8:
            if (ExteraConfig.getSquareFab() == true) goto L10;
        L9:
            r23 = true;
            goto L11
        L5:
            if (ExteraConfig.getSquareFab() == true) goto L9;
            goto L6
        }

        private /* synthetic */ void lambda$setSelected$0(ValueAnimator r1) {
            setProgress(((Float) r1.getAnimatedValue()).floatValue());
        }

        private void setProgress(float r1) {
            this.progress = r1;
            this.backgroundDrawable.setSelectionProgress(r1);
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
            r33.addUpdateListener(new FabShapeCell$FabShape$$ExternalSyntheticLambda0(this));
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
        @SuppressLint({"DrawAllocation"})
        public void onDraw(Canvas r18) {
            int r9 = AndroidUtilities.dp(22.0f);
            int r1 = AndroidUtilities.dp(21.0f);
            int r10 = r9 / 2;
            int r12 = 0;
        L4:
            if (r12 >= 2) goto L20;
            if (r12 != 0) goto L7;
            float r2 = 0.0f;
        L8:
            int r15 = r1 + AndroidUtilities.dp(r2);
            Theme.dialogs_onlineCirclePaint.setColor(PreviewColors.getMockColor(false));
            float r13 = r10 * 2;
            r18.drawRoundRect(r9 - r10, r15 - r10, r9 + r10, r15 + r10, ExteraConfig.getAvatarCorners(r13, true), ExteraConfig.getAvatarCorners(r13, true), Theme.dialogs_onlineCirclePaint);
            int r22 = 0;
        L9:
            if (r22 >= 2) goto L19;
            Paint r3 = Theme.dialogs_onlineCirclePaint;
            if (r22 != 0) goto L13;
            boolean r6 = true;
        L14:
            r3.setColor(PreviewColors.getMockColor(r6));
            RectF r32 = this.rect;
            float r4 = AndroidUtilities.dp(41.0f);
            int r5 = r22 * 10;
            float r62 = r15 - AndroidUtilities.dp(7 - r5);
            int r7 = getMeasuredWidth();
            if (r22 != 0) goto L17;
            float r8 = 70.0f;
        L18:
            r32.set(r4, r62, r7 - AndroidUtilities.dp(r8), r15 - AndroidUtilities.dp(3 - r5));
            r18.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            r22 = r22 + 1;
            goto L9
        L17:
            r8 = 55.0f;
            goto L18
        L13:
            r6 = false;
            goto L14
        L19:
            r12 = r12 + 1;
            r1 = r15;
            goto L4
        L7:
            r2 = 32.0f;
            goto L8
        L20:
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
            this.rect.set(getMeasuredWidth() - AndroidUtilities.dp(42.0f), getMeasuredHeight() - AndroidUtilities.dp(12.0f), getMeasuredWidth() - AndroidUtilities.dp(12.0f), getMeasuredHeight() - AndroidUtilities.dp(42.0f));
            RectF r23 = this.rect;
            float r42 = 100.0f;
            if (this.squareFab == false) goto L23;
            float r33 = 9.0f;
        L24:
            float r34 = AndroidUtilities.dp(r33);
            if (this.squareFab == false) goto L27;
            r42 = 9.0f;
        L27:
            r18.drawRoundRect(r23, r34, AndroidUtilities.dp(r42), Theme.dialogs_onlineCirclePaint);
            Drawable r24 = ContextCompat.getDrawable(getContext(), R.drawable.filled_fab_compose_32);
            if (r24 == null) goto L35;
            r24.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), PorterDuff.Mode.SRC_IN));
            float r35 = this.rect.width() / AndroidUtilities.dp(FabUiHelper.getFabSizeDp());
            int r43 = Math.round(r24.getIntrinsicWidth() * r35);
            int r36 = Math.round(r24.getIntrinsicHeight() * r35);
            int r52 = (int) (this.rect.centerX() - (r43 / 2.0f));
            int r0 = (int) (this.rect.centerY() - (r36 / 2.0f));
            r24.setBounds(r52, r0, r43 + r52, r36 + r0);
            r24.draw(r18);
            return;
        L35:
            return;
        L23:
            r33 = 100.0f;
            goto L24
        }
    }

    /* renamed from: $r8$lambda$j2duql-IC-reaBvbTZZbwM76iKo, reason: not valid java name */
    public static /* synthetic */ void m1667$r8$lambda$j2duqlICreaBvbTZZbwM76iKo(FabShapeCell r0, boolean r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public FabShapeCell(Context r13) {
        super(r13);
        this.fabShape = new FabShape[2];
        setWillNotDraw(false);
        setOrientation(0);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(21.0f));
        int r2 = 0;
    L3:
        if (r2 >= 2) goto L9;
        boolean r3 = true;
        if (r2 == 1) goto L8;
        r3 = false;
    L8:
        this.fabShape[r2] = new FabShape(r13, r3);
        ScaleStateListAnimator.apply(this.fabShape[r2], 0.03f, 1.5f);
        addView(this.fabShape[r2], LayoutHelper.createLinear(-1, -1, 0.5f, 8, 0, 8, 0));
        this.fabShape[r2].setOnClickListener(new FabShapeCell$$ExternalSyntheticLambda0(this, r3));
        r2 = r2 + 1;
        goto L3
    }

    private /* synthetic */ void lambda$new$0(boolean r6, View r7) {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L10;
        FabShape r2 = this.fabShape[r1];
        if (r7 != r2) goto L8;
        boolean r4 = true;
    L9:
        FabShape.m1669$$Nest$msetSelected(r2, r4, true);
        r1 = r1 + 1;
        goto L4
    L8:
        r4 = false;
        goto L9
    L10:
        ExteraConfig.setSquareFab(r6);
        rebuildFragments();
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof FabShapeCell) == true) goto L8;
        return false;
    L8:
        return Arrays.equals(this.fabShape, ((FabShapeCell) r2).fabShape);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L6;
        this.fabShape[r0].invalidate();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(110.0f), TLObject.FLAG_30));
    }

    public abstract void rebuildFragments();
}
