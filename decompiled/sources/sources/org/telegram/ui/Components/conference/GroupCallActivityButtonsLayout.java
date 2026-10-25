package org.telegram.ui.Components.conference;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.voip.VoIPToggleButton;

/* loaded from: classes7.dex */
public abstract class GroupCallActivityButtonsLayout extends ViewGroup {
    private final LinkedHashMap<View, ButtonHolder> holders;
    private int lastHeight;
    private int lastWidth;

    public static class ButtonHolder implements FactorAnimator.Target {
        public final BoolAnimator enabled;
        private final Runnable invalidateRunnable;
        private boolean isVisible;
        public final VoIPToggleButton view;
        public final BoolAnimator visibility;
        public final FactorAnimator xAnimator;
        public final FactorAnimator yAnimator;

        /* renamed from: -$$Nest$fgetisVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13699$$Nest$fgetisVisible(ButtonHolder r0) {
            return r0.isVisible;
        }

        /* renamed from: -$$Nest$fputisVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13700$$Nest$fputisVisible(ButtonHolder r0, boolean r1) {
            r0.isVisible = r1;
        }

        private ButtonHolder(VoIPToggleButton r9, Runnable r10) {
            CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.xAnimator = new FactorAnimator(1, this, r4, 350);
            this.yAnimator = new FactorAnimator(2, this, r4, 350);
            this.visibility = new BoolAnimator(0, this, r4, 350, true);
            this.enabled = new BoolAnimator(3, this, r4, 350, true);
            this.isVisible = true;
            this.view = r9;
            this.invalidateRunnable = r10;
        }

        public /* synthetic */ ButtonHolder(VoIPToggleButton r1, Runnable r2, GroupCallActivityButtonsLayoutIA r3) {
            this(r1, r2);
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r4, float r5, float r6, FactorAnimator r7) {
            if (r4 != 1) goto L6;
            this.view.setTranslationX(this.xAnimator.getFactor());
        L6:
            if (r4 != 2) goto L9;
            this.view.setTranslationY(this.yAnimator.getFactor());
        L9:
            if (r4 != 0) goto L16;
            float r0 = this.visibility.getFloatValue() * AndroidUtilities.lerp(0.5f, 1.0f, this.enabled.getFloatValue());
            this.view.setAlpha(r0);
            this.view.setScaleX(AndroidUtilities.lerp(0.3f, 1.0f, r5));
            this.view.setScaleY(AndroidUtilities.lerp(0.3f, 1.0f, r5));
            VoIPToggleButton r02 = this.view;
            if (r5 <= 0.0f) goto L13;
            int r52 = 0;
        L14:
            r02.setVisibility(r52);
            goto L16
        L13:
            r52 = 8;
        L16:
            if (r4 != 3) goto L18;
            float r42 = this.visibility.getFloatValue() * AndroidUtilities.lerp(0.5f, 1.0f, this.enabled.getFloatValue());
            this.view.setAlpha(r42);
        L18:
            Runnable r3 = this.invalidateRunnable;
            if (r3 == null) goto L22;
            r3.run();
            return;
        }
    }

    public GroupCallActivityButtonsLayout(Context r2) {
        super(r2);
        this.holders = new LinkedHashMap(16);
    }

    private void doLayout(boolean r15, boolean r16) {
        int r0 = getMeasuredWidth();
        int r1 = getMeasuredHeight();
        if (r0 <= r1) goto L5;
        boolean r4 = false;
    L6:
        if (r0 <= 0) goto L64;
        if (r1 <= 0) goto L65;
        Iterator<ButtonHolder> r5 = this.holders.values().iterator();
        int r6 = 0;
    L11:
        if (r5.hasNext() == false) goto L15;
        if (ButtonHolder.m13699$$Nest$fgetisVisible(r5.next()) == false) goto L11;
        r6 = r6 + 1;
        goto L11
    L15:
        if (r6 != 0) goto L18;
        r6 = 1;
    L18:
        if (r4 == true) goto L20;
        int r12 = Math.min(AndroidUtilities.dp(50.0f) + Math.max((int) ((r0 - (AndroidUtilities.dp(50.0f) * r6)) / (r6 + 0.333f)), 0), r0 / r6);
        int r7 = AndroidUtilities.dp(76.0f);
        int r13 = (r0 - (r6 * r12)) / 2;
        r0 = r12;
    L21:
        Iterator<Map.Entry<View, ButtonHolder>> r62 = this.holders.entrySet().iterator();
        int r8 = 0;
    L23:
        if (r62.hasNext() == false) goto L54;
        ButtonHolder r9 = r62.next().getValue();
        if (ButtonHolder.m13699$$Nest$fgetisVisible(r9) == false) goto L46;
        if (r4 == true) goto L28;
        int r10 = ((r0 * r8) + r13) + ((r0 - r9.view.getMeasuredWidth()) / 2);
        int r11 = getMeasuredHeight() - AndroidUtilities.dp(76.0f);
    L29:
        if (r16 == true) goto L36;
        if (r15 == true) goto L34;
        if (r9.xAnimator.isAnimating() == false) goto L36;
    L34:
        if (r9.visibility.getValue() == false) goto L36;
        r9.xAnimator.animateTo(r10);
    L37:
        if (r16 == true) goto L44;
        if (r15 == true) goto L42;
        if (r9.yAnimator.isAnimating() == false) goto L44;
    L42:
        if (r9.visibility.getValue() == false) goto L44;
        r9.yAnimator.animateTo(r11);
    L45:
        r8 = r8 + 1;
    L44:
        r9.yAnimator.forceFactor(r11);
    L36:
        r9.xAnimator.forceFactor(r10);
        goto L37
    L28:
        r10 = (getMeasuredWidth() - r0) + ((r0 - r9.view.getMeasuredWidth()) / 2);
        r11 = (r7 * r8) + r13;
    L46:
        BoolAnimator r102 = r9.visibility;
        boolean r112 = ButtonHolder.m13699$$Nest$fgetisVisible(r9);
        if (r16 == true) goto L52;
        if (r15 == false) goto L50;
    L51:
        boolean r92 = true;
    L53:
        r102.setValue(r112, r92);
        goto L23
    L50:
        if (r9.visibility.isAnimating() == true) goto L51;
    L52:
        r92 = false;
        goto L53
    L54:
        invalidate();
        return;
    L20:
        r7 = Math.min(AndroidUtilities.dp(50.0f) + Math.max((int) ((r1 - (AndroidUtilities.dp(50.0f) * r6)) / (r6 + 0.333f)), 0), r1 / r6);
        r13 = (r1 - (r6 * r7)) / 2;
        goto L21
    L65:
        return;
    L64:
        return;
    L5:
        r4 = true;
        goto L6
    }

    public void addButton(VoIPToggleButton r4) {
        addView(r4);
        this.holders.put(r4, new ButtonHolder(r4, new GroupCallActivityButtonsLayout$$ExternalSyntheticLambda0(this), null));
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
    public void onMeasure(int r7, int r8) {
        int r72 = View.MeasureSpec.getSize(r7);
        int r82 = View.MeasureSpec.getSize(r8);
        setMeasuredDimension(r72, r82);
        int r1 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(76.0f), TLObject.FLAG_30);
        int r0 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(76.0f), TLObject.FLAG_30);
        int r2 = getChildCount();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L6;
        getChildAt(r4).measure(r1, r0);
        r4 = r4 + 1;
        goto L3
    L6:
        if (this.lastWidth == r72) goto L8;
    L12:
        doLayout(false, true);
        this.lastWidth = r72;
        this.lastHeight = r82;
        return;
    L8:
        if (this.lastHeight != r82) goto L12;
        doLayout(true, false);
    }

    public void setButtonEnabled(VoIPToggleButton r1, boolean r2, boolean r3) {
        ButtonHolder r0 = this.holders.get(r1);
        if (r0 == null) goto L6;
        r0.enabled.setValue(r2, r3);
        r1.setEnabled(r2);
        return;
    }

    public void setButtonVisibility(VoIPToggleButton r2, boolean r3, boolean r4) {
        ButtonHolder r22 = this.holders.get(r2);
        if (r22 != null) goto L5;
        return;
    L5:
        if (ButtonHolder.m13699$$Nest$fgetisVisible(r22) == r3) goto L9;
        ButtonHolder.m13700$$Nest$fputisVisible(r22, r3);
        doLayout(r4, false);
        return;
    }
}
