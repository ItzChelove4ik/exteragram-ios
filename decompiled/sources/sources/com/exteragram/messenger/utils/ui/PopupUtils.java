package com.exteragram.messenger.utils.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes4.dex */
public abstract class PopupUtils {

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onClick(int r1);
    }

    public interface OnMultiSelectListener {
        void onClick(boolean[] r1);
    }

    /* renamed from: $r8$lambda$6jb6sQCYPJdYYKDI25r3PRtH-Z4, reason: not valid java name */
    public static /* synthetic */ void m1797$r8$lambda$6jb6sQCYPJdYYKDI25r3PRtHZ4(AlertDialog.Builder r0, OnItemClickListener r1, DialogInterface r2, int r3) {
        r0.getDismissRunnable().run();
        r1.onClick(r3);
    }

    public static /* synthetic */ void $r8$lambda$82yGHJGHEfp_DRvEkaoSvyOG5NY(AlertDialog.Builder r0, OnItemClickListener r1, DialogInterface r2, int r3) {
        r0.getDismissRunnable().run();
        r1.onClick(r3);
    }

    /* renamed from: $r8$lambda$RTfkaKFFZM5qIJnQKtbp3r-qWks, reason: not valid java name */
    public static /* synthetic */ CharSequence[] m1798$r8$lambda$RTfkaKFFZM5qIJnQKtbp3rqWks(int r0) {
        return new CharSequence[r0];
    }

    public static /* synthetic */ void $r8$lambda$jpgBnZwRK_QOSpnMgBY4clbw2fE(LinearLayout r2, OnMultiSelectListener r3, AlertDialog r4, int r5) {
        int r42 = r2.getChildCount();
        boolean[] r52 = new boolean[r42];
        int r0 = 0;
    L3:
        if (r0 >= r42) goto L5;
        r52[r0] = ((CheckBoxCell) r2.getChildAt(r0)).isChecked();
        r0 = r0 + 1;
        goto L3
    L5:
        r3.onClick(r52);
    }

    /* renamed from: $r8$lambda$mmJcbiSq33fgrEuatbB4zTeZu-A, reason: not valid java name */
    public static /* synthetic */ void m1799$r8$lambda$mmJcbiSq33fgrEuatbB4zTeZuA(AlertDialog.Builder r0, OnItemClickListener r1, View r2) {
        Integer r22 = (Integer) r2.getTag();
        r0.getDismissRunnable().run();
        r1.onClick(r22.intValue());
    }

    /* renamed from: $r8$lambda$z1b0pagw-ziYdH7thyzsuD653eo, reason: not valid java name */
    public static /* synthetic */ void m1800$r8$lambda$z1b0pagwziYdH7thyzsuD653eo(CheckBoxCell r1, View r2) {
        r1.setChecked(!r1.isChecked(), true);
    }

    public static void showDialog(CharSequence[] r8, String r9, int r10, Context r11, OnItemClickListener r12) {
        showDialog(r8, null, r9, r10, r11, r12, null, true);
    }

    public static void showDialog(CharSequence[] r8, int[] r9, String r10, int r11, Context r12, OnItemClickListener r13) {
        showDialog(r8, r9, r10, r11, r12, r13, null, true);
    }

    public static void showDialog(CharSequence[] r5, int[] r6, String r7, int r8, Context r9, OnItemClickListener r10, Theme.ResourcesProvider r11, boolean r12) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r9, r11);
        r0.setTitle(r7);
        if (r12 == false) goto L12;
        LinearLayout r62 = new LinearLayout(r9);
        r62.setOrientation(1);
        r0.setView(r62);
        int r1 = 0;
    L6:
        if (r1 >= r5.length) goto L16;
        RadioColorCell r2 = new RadioColorCell(r9);
        r2.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r2.setTag(Integer.valueOf(r1));
        r2.setCheckColor(Theme.getColor(Theme.key_radioBackground, r11), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r11));
        CharSequence r3 = r5[r1];
        if (r8 != r1) goto L10;
        boolean r4 = true;
    L11:
        r2.setTextAndValue(r3, r4);
        r2.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r62.addView(r2);
        r2.setOnClickListener(new PopupUtils$$ExternalSyntheticLambda0(r0, r10));
        r1 = r1 + 1;
        goto L6
    L10:
        r4 = false;
    L16:
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.show();
        return;
    L12:
        if (r6 == null) goto L14;
        r0.setItems(r5, r6, new PopupUtils$$ExternalSyntheticLambda1(r0, r10));
    L15:
        r0.create();
        goto L16
    L14:
        r0.setItems(r5, new PopupUtils$$ExternalSyntheticLambda2(r0, r10));
        goto L15
    }

    public static void showDialogWithoutRadio(ArrayList<? extends CharSequence> r8, String r9, Context r10, OnItemClickListener r11) {
        showDialog((CharSequence[]) r8.stream().map(new PopupUtils$$ExternalSyntheticLambda5()).toArray(new PopupUtils$$ExternalSyntheticLambda6()), null, r9, -1, r10, r11, null, false);
    }

    public static void showMultiSelectDialog(CharSequence[] r12, boolean[] r13, String r14, Context r15, OnMultiSelectListener r16, Theme.ResourcesProvider r17) {
        AlertDialog.Builder r6 = new AlertDialog.Builder(r15, r17);
        r6.setTitle(r14);
        LinearLayout r7 = new LinearLayout(r15);
        r7.setOrientation(1);
        int r10 = 0;
    L4:
        if (r10 >= r12.length) goto L16;
        CheckBoxCell r0 = new CheckBoxCell(r15, 4, 21, true, r17);
        r0.getCheckBoxRound().setColor(Theme.key_switch2TrackChecked, Theme.key_radioBackground, Theme.key_checkboxCheck);
        CharSequence r1 = r12[r10];
        if (r10 < r13.length) goto L8;
    L10:
        boolean r2 = false;
    L12:
        if (r10 >= (r12.length - 1)) goto L14;
        boolean r3 = true;
    L15:
        r0.setText(r1, null, r2, r3);
        r0.setOnClickListener(new PopupUtils$$ExternalSyntheticLambda3(r0));
        r0.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r17), 2));
        r7.addView(r0, LayoutHelper.createLinear(-1, -2));
        r10 = r10 + 1;
        goto L4
    L14:
        r3 = false;
        goto L15
    L8:
        if (r13[r10] == false) goto L10;
        r2 = true;
        goto L12
    L16:
        r6.setView(r7);
        r6.setPositiveButton(LocaleController.getString(R.string.OK), new PopupUtils$$ExternalSyntheticLambda4(r7, r16));
        r6.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r6.show();
    }
}
