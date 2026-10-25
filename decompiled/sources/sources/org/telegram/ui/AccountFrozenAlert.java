package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Stars.ExplainStarsSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public abstract class AccountFrozenAlert {
    public static boolean shown;

    public static /* synthetic */ void $r8$lambda$Hem0Woj0iHAo_BNTrUEERkyaAr4(int r2, Context r3, BottomSheet[] r4) {
        String r22 = MessagesController.getInstance(r2).freezeAppealUrl;
        if (r22.startsWith("http://") == false) goto L5;
    L7:
        Browser.openUrl(r3, r22);
        r4[0].dismiss();
        return;
    L5:
        if (r22.startsWith("https://") == true) goto L7;
        r22 = "https://".concat(r22);
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$kDqVBwiRvADnkKtqlwDMBCooVms(DialogInterface r0) {
        shown = false;
    }

    /* renamed from: $r8$lambda$qK2ygQ34-lGrN_wV4rD0SjYR6bI, reason: not valid java name */
    public static /* synthetic */ void m5399$r8$lambda$qK2ygQ34lGrN_wV4rD0SjYR6bI(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$yIU45lpPyIFMh7GAlRAUETMW9uM(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    public static boolean isSpamBot(int r2, TLRPC.User r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        String r32 = UserObject.getPublicUsername(r3);
        if (r32 != null) goto L18;
        return false;
    L18:
        Matcher r22 = Pattern.compile("t\\.me/([a-zA-Z0-9]+)/?").matcher(MessagesController.getInstance(r2).freezeAppealUrl);     // Catch: Exception -> L13
        if (r22.find() == false) goto L15;
        if (r32.equalsIgnoreCase(r22.group(1)) == false) goto L15;
        return true;
    L15:
        return false;
    L13:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static void show(int r2) {
        if (shown == false) goto L6;
        return;
    L6:
        if (UserConfig.selectedAccount != r2) goto L20;
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L11;
        r0 = ApplicationLoader.applicationContext;
    L11:
        if (r0 != null) goto L13;
        return;
    L13:
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L16;
        Theme.ResourcesProvider r12 = r1.getResourceProvider();
    L17:
        show(r0, r2, r12);
        return;
    L16:
        r12 = null;
        goto L17
    }

    public static void show(Context r21, int r22, Theme.ResourcesProvider r23) {
        if (shown == false) goto L5;
        return;
    L5:
        BottomSheet.Builder r2 = new BottomSheet.Builder(r21, false, r23);
        AccountFrozenAlert$$ExternalSyntheticLambda0 r6 = new AccountFrozenAlert$$ExternalSyntheticLambda0(r22, r21, r5);
        LinearLayout r8 = new LinearLayout(r21);
        r8.setOrientation(1);
        r8.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r8.setClipChildren(false);
        r8.setClipToPadding(false);
        RLottieImageView r9 = new RLottieImageView(r21);
        r9.setAnimation(R.raw.media_forbidden, AndroidUtilities.dp(115.0f), AndroidUtilities.dp(115.0f));
        r9.playAnimation();
        r8.addView(r9, LayoutHelper.createLinear(115, 115, 17, 0, 0, 0, 9));
        TextView r92 = new TextView(r21);
        r92.setTypeface(AndroidUtilities.bold());
        r92.setTextSize(1, 20.0f);
        r92.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r23));
        r92.setText(LocaleController.getString(R.string.AccountFrozenTitle));
        r92.setGravity(17);
        r8.addView(r92, LayoutHelper.createLinear(-1, -2, 17, 0, 0, 0, 23));
        ExplainStarsSheet.FeatureCell r93 = new ExplainStarsSheet.FeatureCell(r21, 1, r23);
        r93.set(R.drawable.msg_block2, LocaleController.getString(R.string.AccountFrozen1Title), LocaleController.getString(R.string.AccountFrozen1Text));
        r8.addView(r93, LayoutHelper.createLinear(-1, -2, 17, 0, 0, 0, 0));
        ExplainStarsSheet.FeatureCell r94 = new ExplainStarsSheet.FeatureCell(r21, 1, r23);
        r94.set(R.drawable.menu_privacy, LocaleController.getString(R.string.AccountFrozen2Title), LocaleController.getString(R.string.AccountFrozen2Text));
        r8.addView(r94, LayoutHelper.createLinear(-1, -2, 17, 0, 0, 0, 0));
        ExplainStarsSheet.FeatureCell r95 = new ExplainStarsSheet.FeatureCell(r21, 1, r23);
        r95.set(R.drawable.menu_feature_hourglass, LocaleController.getString(R.string.AccountFrozen3Title), AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.AccountFrozen3Text, new Object[]{LocaleController.formatYearMonthDay(MessagesController.getInstance(r22).freezeUntilDate, true)}), new AccountFrozenAlert$$ExternalSyntheticLambda1(r6)));
        r8.addView(r95, LayoutHelper.createLinear(-1, -2, 17, 0, 0, 0, 0));
        ButtonWithCounterView r7 = new ButtonWithCounterView(r21, true, r23);
        r7.setText(LocaleController.getString(R.string.AccountFrozenButtonAppeal), false);
        r7.setOnClickListener(new AccountFrozenAlert$$ExternalSyntheticLambda2(r6));
        r8.addView(r7, LayoutHelper.createLinear(-1, 48, 7, 0, 13, 0, 4));
        ButtonWithCounterView r62 = new ButtonWithCounterView(r21, false, r23);
        r62.setText(LocaleController.getString(R.string.AccountFrozenButtonUnderstood), false);
        r62.setOnClickListener(new AccountFrozenAlert$$ExternalSyntheticLambda3(r5));
        r8.addView(r62, LayoutHelper.createLinear(-1, 48, 7, 0, 0, 0, 0));
        r2.setCustomView(r8);
        BottomSheet r0 = r2.create();
        BottomSheet[] r5 = {r0};
        r0.useBackgroundTopPadding = false;
        r0.fixNavigationBar();
        shown = true;
        r5[0].show();
        r5[0].setOnDismissListener(new AccountFrozenAlert$$ExternalSyntheticLambda4());
    }
}
