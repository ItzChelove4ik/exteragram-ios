package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class WebAppDisclaimerAlert {
    private AlertDialog alert;
    private CheckBoxCell cell;
    private TextView positiveButton;

    /* renamed from: $r8$lambda$ICLm3wirtfJP4fPzJ1h4N-5G00Y, reason: not valid java name */
    public static /* synthetic */ void m21288$r8$lambda$ICLm3wirtfJP4fPzJ1h4N5G00Y(WebAppDisclaimerAlert r2, View r3) {
        r2.cell.setChecked(!r3.isChecked(), true);
        r2.positiveButton.setEnabled(r2.cell.isChecked());
        ViewPropertyAnimator r32 = r2.positiveButton.animate();
        if (r2.cell.isChecked() == false) goto L5;
        float r22 = 1.0f;
    L6:
        r32.alpha(r22).start();
        return;
    L5:
        r22 = 0.5f;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$LOJMvwvnO12EwW70_zmwaJs0cc4(boolean[] r1, Runnable r2, DialogInterface r3) {
        if (r1[0] == true) goto L8;
        r1[0] = true;
        if (r2 == null) goto L9;
        r2.run();
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$Q_NiEdkicEqGXSReCX1rFNRVGEA(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$uc8P2bw_j9cz9G0pygQnUSUEqzU(Utilities.Callback r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.run(Boolean.TRUE);
        r1[0] = true;
        r2.dismiss();
    }

    /* renamed from: $r8$lambda$z88PYgAkMwb-mftLg9y_okf8OHg, reason: not valid java name */
    public static /* synthetic */ void m21289$r8$lambda$z88PYgAkMwbmftLg9y_okf8OHg(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.WebAppDisclaimerUrl));
    }

    public WebAppDisclaimerAlert() {
    }

    public static void show(Context r16, Utilities.Callback<Boolean> r17, TLRPC.User r18, Runnable r19) {
        WebAppDisclaimerAlert r1 = new WebAppDisclaimerAlert();
        AlertDialog.Builder r2 = new AlertDialog.Builder(r16);
        r2.setTitle(LocaleController.getString(R.string.TermsOfUse));
        LinearLayout r3 = new LinearLayout(r16);
        r3.setOrientation(1);
        TextView r5 = new TextView(r16);
        r5.setLetterSpacing(0.025f);
        r5.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r5.setTextSize(1, 14.0f);
        r3.addView(r5, LayoutHelper.createLinear(-1, -2, 0, 24, 0, 24, 0));
        CheckBoxCell r7 = new CheckBoxCell(r16, 1, null);
        r1.cell = r7;
        r7.getTextView().getLayoutParams().width = -1;
        r1.cell.getTextView().setTextSize(1, 14.0f);
        r3.addView(r1.cell, LayoutHelper.createLinear(-1, 48, 3, 8, 0, 8, 0));
        boolean[] r4 = new boolean[1];
        r5.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.BotWebAppDisclaimerSubtitle)));
        r1.cell.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.BotWebAppDisclaimerCheck), new WebAppDisclaimerAlert$$ExternalSyntheticLambda0(r16)), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        r2.setView(r3);
        r2.setPositiveButton(LocaleController.getString(R.string.Continue), new WebAppDisclaimerAlert$$ExternalSyntheticLambda1(r17, r4));
        r2.setNegativeButton(LocaleController.getString(R.string.Cancel), new WebAppDisclaimerAlert$$ExternalSyntheticLambda2());
        AlertDialog r0 = r2.create();
        r1.alert = r0;
        r0.show();
        TextView r02 = (TextView) r1.alert.getButton(-1);
        r1.positiveButton = r02;
        r02.setEnabled(false);
        r1.positiveButton.setAlpha(0.5f);
        r1.cell.setOnClickListener(new WebAppDisclaimerAlert$$ExternalSyntheticLambda3(r1));
        r1.cell.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 7));
        r1.alert.setOnDismissListener(new WebAppDisclaimerAlert$$ExternalSyntheticLambda4(r4, r19));
    }
}
