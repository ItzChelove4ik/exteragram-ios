package org.telegram.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSlider;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PopupSwipeBackLayout;

/* loaded from: classes6.dex */
public class ChooseSpeedLayout {
    ActionBarMenuSlider.SpeedSlider slider;
    ActionBarMenuSubItem[] speedItems;
    public ActionBarPopupWindow.ActionBarPopupWindowLayout speedSwipeBackLayout;

    /* renamed from: org.telegram.ui.ChooseSpeedLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ChooseSpeedLayout this$0;

        public AnonymousClass1(ChooseSpeedLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.ChooseSpeedLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ ChooseSpeedLayout this$0;

        public AnonymousClass2(ChooseSpeedLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    public interface Callback {
        void onSpeedSelected(float r1, boolean r2, boolean r3);
    }

    /* renamed from: $r8$lambda$40btgsxU0SDlced_E-7nrnG-Zms, reason: not valid java name */
    public static /* synthetic */ void m8651$r8$lambda$40btgsxU0SDlced_E7nrnGZms(Callback r1, View r2) {
        r1.onSpeedSelected(1.5f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$MBzCkc7OvFMkHVuIBfMrQcTx7c0(Callback r1, View r2) {
        r1.onSpeedSelected(2.0f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$OJ1f29IWjDJd1OZS1QFCoAQYl7k(Callback r1, Float r2, Boolean r3) {
        r1.onSpeedSelected((r2.floatValue() * 2.3f) + 0.2f, r3.booleanValue(), false);
    }

    public static /* synthetic */ void $r8$lambda$dQFmIvasuGGMOhSWUKqUxaD9kQY(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$diEXhfs8g1cYXmiYqzh1YaDqAho(Callback r1, View r2) {
        r1.onSpeedSelected(1.0f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$iI4IKzvOy1SS872JIvCm3rIf4Rg(Callback r1, View r2) {
        r1.onSpeedSelected(0.5f, true, true);
    }

    /* renamed from: $r8$lambda$tLRORhhlNkRBCnsLz3K-eRXpWoc, reason: not valid java name */
    public static /* synthetic */ void m8652$r8$lambda$tLRORhhlNkRBCnsLz3KeRXpWoc(Callback r1, View r2) {
        r1.onSpeedSelected(0.2f, true, true);
    }

    public ChooseSpeedLayout(Context r13, PopupSwipeBackLayout r14, Callback r15) {
        this.speedItems = new ActionBarMenuSubItem[5];
        ActionBarPopupWindow.ActionBarPopupWindowLayout r1 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r13, 0, null);
        this.speedSwipeBackLayout = r1;
        r1.setFitItems(true);
        ActionBarMenuSubItem r12 = ActionBarMenuItem.addItem(this.speedSwipeBackLayout, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, null);
        r12.setOnClickListener(new ChooseSpeedLayout$$ExternalSyntheticLambda0(r14));
        r12.setColors(-328966, -328966);
        r12.setSelectorColor(268435455);
        AnonymousClass1 r16 = new AnonymousClass1(this, r13);
        r16.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r16.setBackgroundColor(-15198184);
        this.speedSwipeBackLayout.addView(r16);
        LinearLayout.LayoutParams r8 = (LinearLayout.LayoutParams) r16.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r8.gravity = 5;
    L5:
        r8.width = -1;
        r8.height = AndroidUtilities.dp(8.0f);
        r16.setLayoutParams(r8);
        ActionBarMenuSlider.SpeedSlider r17 = new ActionBarMenuSlider.SpeedSlider(r13, null);
        this.slider = r17;
        r17.setMinimumWidth(AndroidUtilities.dp(196.0f));
        this.slider.setDrawShadow(false);
        this.slider.setBackgroundColor(-14540254);
        this.slider.setTextColor(-1);
        this.slider.setOnValueChange(new ChooseSpeedLayout$$ExternalSyntheticLambda1(r15));
        this.speedSwipeBackLayout.addView(this.slider, LayoutHelper.createLinear(-1, 44));
        AnonymousClass2 r18 = new AnonymousClass2(this, r13);
        r18.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r18.setBackgroundColor(-15198184);
        this.speedSwipeBackLayout.addView(r18);
        LinearLayout.LayoutParams r132 = (LinearLayout.LayoutParams) r18.getLayoutParams();
        if (LocaleController.isRTL == false) goto L8;
        r132.gravity = 5;
    L8:
        r132.width = -1;
        r132.height = AndroidUtilities.dp(8.0f);
        r18.setLayoutParams(r132);
        ActionBarMenuSubItem r133 = ActionBarMenuItem.addItem(this.speedSwipeBackLayout, R.drawable.msg_speed_0_2, LocaleController.getString(R.string.SpeedVerySlow), false, null);
        r133.setColors(-328966, -328966);
        r133.setOnClickListener(new ChooseSpeedLayout$$ExternalSyntheticLambda2(r15));
        r133.setSelectorColor(268435455);
        this.speedItems[0] = r133;
        ActionBarMenuSubItem r134 = ActionBarMenuItem.addItem(this.speedSwipeBackLayout, R.drawable.msg_speed_slow, LocaleController.getString(R.string.SpeedSlow), false, null);
        r134.setColors(-328966, -328966);
        r134.setOnClickListener(new ChooseSpeedLayout$$ExternalSyntheticLambda3(r15));
        r134.setSelectorColor(268435455);
        this.speedItems[1] = r134;
        ActionBarMenuSubItem r135 = ActionBarMenuItem.addItem(this.speedSwipeBackLayout, R.drawable.msg_speed_normal, LocaleController.getString(R.string.SpeedNormal), false, null);
        r135.setColors(-328966, -328966);
        r135.setOnClickListener(new ChooseSpeedLayout$$ExternalSyntheticLambda4(r15));
        r135.setSelectorColor(268435455);
        this.speedItems[2] = r135;
        ActionBarMenuSubItem r136 = ActionBarMenuItem.addItem(this.speedSwipeBackLayout, R.drawable.msg_speed_fast, LocaleController.getString(R.string.SpeedFast), false, null);
        r136.setColors(-328966, -328966);
        r136.setOnClickListener(new ChooseSpeedLayout$$ExternalSyntheticLambda5(r15));
        r136.setSelectorColor(268435455);
        this.speedItems[3] = r136;
        ActionBarMenuSubItem r137 = ActionBarMenuItem.addItem(this.speedSwipeBackLayout, R.drawable.msg_speed_superfast, LocaleController.getString(R.string.SpeedVeryFast), false, null);
        r137.setColors(-328966, -328966);
        r137.setOnClickListener(new ChooseSpeedLayout$$ExternalSyntheticLambda6(r15));
        r137.setSelectorColor(268435455);
        this.speedItems[4] = r137;
    }

    public void update(float r5, boolean r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.speedItems.length) goto L28;
        if (r6 == false) goto L26;
        if (r0 != 0) goto L10;
        if (Math.abs(r5 - 0.2f) >= 0.01f) goto L10;
    L25:
        this.speedItems[r0].setColors(-9718023, -9718023);
    L27:
        r0 = r0 + 1;
    L10:
        if (r0 != 1) goto L14;
        if (Math.abs(r5 - 0.5f) < 0.1f) goto L25;
    L14:
        if (r0 != 2) goto L18;
        if (Math.abs(r5 - 1.0f) < 0.1f) goto L25;
    L18:
        if (r0 != 3) goto L22;
        if (Math.abs(r5 - 1.5f) < 0.1f) goto L25;
    L22:
        if (r0 != 4) goto L26;
        if (Math.abs(r5 - 2.0f) < 0.1f) goto L25;
    L26:
        this.speedItems[r0].setColors(-328966, -328966);
        goto L27
    L28:
        this.slider.setSpeed(r5, true);
    }
}
