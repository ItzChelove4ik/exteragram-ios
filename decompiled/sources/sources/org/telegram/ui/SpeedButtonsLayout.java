package org.telegram.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;

/* loaded from: classes6.dex */
public class SpeedButtonsLayout extends LinearLayout {
    ActionBarMenuSubItem[] speedItems;

    /* renamed from: org.telegram.ui.SpeedButtonsLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ SpeedButtonsLayout this$0;

        public AnonymousClass1(SpeedButtonsLayout r1, Context r2) {
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

    public static /* synthetic */ void $r8$lambda$ISAFs_BD62d0_KOas1PANgUtmug(Callback r1, View r2) {
        r1.onSpeedSelected(0.5f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$NkJakmtna36ck75NBUt4fsQsZZc(Callback r1, View r2) {
        r1.onSpeedSelected(1.0f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$Uo4umvGuIfIVA62h_AiTFIIaqNA(Callback r1, View r2) {
        r1.onSpeedSelected(0.2f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$_YDtY9vi8ozNAsteIsIS3bSqFes(Callback r1, View r2) {
        r1.onSpeedSelected(2.0f, true, true);
    }

    public static /* synthetic */ void $r8$lambda$cvG0w490dPpuRgbFT4fc0EK75Uc(Callback r1, View r2) {
        r1.onSpeedSelected(1.5f, true, true);
    }

    public SpeedButtonsLayout(Context r9, Callback r10) {
        super(r9);
        this.speedItems = new ActionBarMenuSubItem[5];
        setOrientation(1);
        ActionBarMenuSubItem r2 = ActionBarMenuItem.addItem(this, R.drawable.msg_speed_0_2, LocaleController.getString(R.string.SpeedVerySlow), false, null);
        r2.setColors(-328966, -328966);
        r2.setOnClickListener(new SpeedButtonsLayout$$ExternalSyntheticLambda0(r10));
        r2.setSelectorColor(268435455);
        this.speedItems[0] = r2;
        ActionBarMenuSubItem r22 = ActionBarMenuItem.addItem(this, R.drawable.msg_speed_slow, LocaleController.getString(R.string.SpeedSlow), false, null);
        r22.setColors(-328966, -328966);
        r22.setOnClickListener(new SpeedButtonsLayout$$ExternalSyntheticLambda1(r10));
        r22.setSelectorColor(268435455);
        this.speedItems[1] = r22;
        ActionBarMenuSubItem r1 = ActionBarMenuItem.addItem(this, R.drawable.msg_speed_normal, LocaleController.getString(R.string.SpeedNormal), false, null);
        r1.setColors(-328966, -328966);
        r1.setOnClickListener(new SpeedButtonsLayout$$ExternalSyntheticLambda2(r10));
        r1.setSelectorColor(268435455);
        this.speedItems[2] = r1;
        ActionBarMenuSubItem r12 = ActionBarMenuItem.addItem(this, R.drawable.msg_speed_fast, LocaleController.getString(R.string.SpeedFast), false, null);
        r12.setColors(-328966, -328966);
        r12.setOnClickListener(new SpeedButtonsLayout$$ExternalSyntheticLambda3(r10));
        r12.setSelectorColor(268435455);
        this.speedItems[3] = r12;
        ActionBarMenuSubItem r13 = ActionBarMenuItem.addItem(this, R.drawable.msg_speed_superfast, LocaleController.getString(R.string.SpeedVeryFast), false, null);
        r13.setColors(-328966, -328966);
        r13.setOnClickListener(new SpeedButtonsLayout$$ExternalSyntheticLambda4(r10));
        r13.setSelectorColor(268435455);
        this.speedItems[4] = r13;
        View r102 = new AnonymousClass1(this, r9);
        r102.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r102.setBackgroundColor(-15198184);
        addView(r102);
        LinearLayout.LayoutParams r8 = (LinearLayout.LayoutParams) r102.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r8.gravity = 5;
    L5:
        r8.width = -1;
        r8.height = AndroidUtilities.dp(8.0f);
        r102.setLayoutParams(r8);
    }

    public void update(float r4, boolean r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.speedItems.length) goto L28;
        if (r5 == false) goto L26;
        if (r0 != 0) goto L10;
        if (Math.abs(r4 - 0.2f) >= 0.01f) goto L10;
    L25:
        this.speedItems[r0].setColors(-9718023, -9718023);
    L27:
        r0 = r0 + 1;
    L10:
        if (r0 != 1) goto L14;
        if (Math.abs(r4 - 0.5f) < 0.1f) goto L25;
    L14:
        if (r0 != 2) goto L18;
        if (Math.abs(r4 - 1.0f) < 0.1f) goto L25;
    L18:
        if (r0 != 3) goto L22;
        if (Math.abs(r4 - 1.5f) < 0.1f) goto L25;
    L22:
        if (r0 != 4) goto L26;
        if (Math.abs(r4 - 2.0f) < 0.1f) goto L25;
    L26:
        this.speedItems[r0].setColors(-328966, -328966);
        goto L27
    }
}
