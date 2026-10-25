package org.telegram.ui.bots;

import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheetTabsOverlay;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public abstract class BotWebViewAttachedSheet implements NotificationCenter.NotificationCenterDelegate, BaseFragment.AttachedSheet, BottomSheetTabsOverlay.Sheet {
    private static final SimpleFloatPropertyCompat<BotWebViewAttachedSheet> ACTION_BAR_TRANSITION_PROGRESS_VALUE = null;

    public static /* synthetic */ float $r8$lambda$2ljsgWWd0z2DS7mi0mN7h8I8ujA(BotWebViewAttachedSheet r0) {
        throw null;
    }

    public static /* synthetic */ void $r8$lambda$Z8aan7GPKLiRq3iDgwZVMk5Gl3M(int r16, long r17) {
        SendMessagesHelper.getInstance(r16).sendMessage(SendMessagesHelper.SendMessageParams.of("/privacy", r17, null, null, null, false, null, null, null, true, 0, 0, null, false));
    }

    public static /* synthetic */ void $r8$lambda$frEEu6okCrK_l1W85skt8JpfieA(BotWebViewAttachedSheet r0, float r1) {
        throw null;
    }

    static {
        ACTION_BAR_TRANSITION_PROGRESS_VALUE = new SimpleFloatPropertyCompat("actionBarTransitionProgress", new BotWebViewAttachedSheet$$ExternalSyntheticLambda0(), new BotWebViewAttachedSheet$$ExternalSyntheticLambda1()).setMultiplier(100.0f);
    }

    public static boolean hasPrivacyCommand(TLRPC.UserFull r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        TL_bots.BotInfo r62 = r6.bot_info;
        if (r62 != null) goto L9;
        return false;
    L9:
        if (r62.privacy_policy_url == null) goto L11;
        return true;
    L11:
        ArrayList<TLRPC.BotCommand> r63 = r62.commands;
        int r1 = r63.size();
        int r3 = 0;
    L12:
        if (r3 >= r1) goto L16;
        TLRPC.BotCommand r4 = r63.get(r3);
        r3 = r3 + 1;
        if ("privacy".equals(r4.command) == false) goto L12;
        return true;
    L16:
        return false;
    }

    public static boolean openPrivacy(int r3, long r4) {
        TLRPC.UserFull r0 = MessagesController.getInstance(r3).getUserFull(r4);
        if (r0 != null) goto L5;
        return false;
    L5:
        TL_bots.BotInfo r2 = r0.bot_info;
        if (r2 != null) goto L8;
        return false;
    L8:
        String r22 = r2.privacy_policy_url;
        if (r22 == null) goto L11;
    L13:
        if (r22 == null) goto L16;
        Browser.openUrl(ApplicationLoader.applicationContext, r22);
        return false;
    L16:
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r02 != null) goto L20;
        return false;
    L20:
        if ((r02 instanceof ChatActivity) == true) goto L22;
    L23:
        r02.presentFragment(ChatActivity.of(r4));
    L24:
        AndroidUtilities.runOnUIThread(new BotWebViewAttachedSheet$$ExternalSyntheticLambda2(r3, r4), 150);
        return true;
    L22:
        if (((ChatActivity) r02).getDialogId() == r4) goto L24;
    L11:
        if (hasPrivacyCommand(r0) == true) goto L13;
        r22 = LocaleController.getString(R.string.BotDefaultPrivacyPolicy);
        goto L13
    }
}
