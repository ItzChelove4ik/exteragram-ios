package org.telegram.messenger;

import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.support.LongSparseLongArray;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.bots.WebViewRequestProps;

/* loaded from: classes5.dex */
public class BotGuardHelper extends BaseController {
    private static volatile BotGuardHelper[] Instance;
    private final LongSparseLongArray queryIdToBotId;

    public static class GuardBotDecisionResultNotification {
        public final long dialogId;
        public final long guardBotId;
        public final long queryId;
        public final TLRPC.JoinChatBotResult result;

        public GuardBotDecisionResultNotification(long r1, long r3, long r5, TLRPC.JoinChatBotResult r7) {
            this.dialogId = r1;
            this.guardBotId = r3;
            this.queryId = r5;
            this.result = r7;
        }
    }

    public static /* synthetic */ void $r8$lambda$5T2vd7ORetE9dGIkFHTcq8YC89g(BotGuardHelper r0, long r1, long r3, long r5) {
        r0.lambda$openGuardBotWebApp$0(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$lHMHGpv_N9TnvvMs9VvUn2h0FRM() {
    }

    static {
        Instance = new BotGuardHelper[16];
    }

    private BotGuardHelper(int r1) {
        super(r1);
        this.queryIdToBotId = new LongSparseLongArray();
    }

    public static BotGuardHelper getInstance(int r3) {
        BotGuardHelper r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(BotForumHelper.class);
        BotGuardHelper r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        BotGuardHelper[] r03 = Instance;     // Catch: Throwable -> L9
        BotGuardHelper r2 = new BotGuardHelper(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(BotForumHelper.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$openGuardBotWebApp$0(long r9, long r11, long r13) {
        openGuardBotWebApp(r9, r11, r13, true);
        SharedPrefsHelper.setWebViewConfirmShown(this.currentAccount, r11, true);
    }

    private void openGuardBotWebApp(long r21, long r23, long r25, boolean r27) {
        if (LaunchActivity.instance == null) goto L21;
        BaseFragment r8 = LaunchActivity.getLastFragment();
        if (r8 != null) goto L8;
        return;
    L8:
        TLRPC.User r9 = getMessagesController().getUser(Long.valueOf(r23));
        if (r27 == false) goto L11;
        this.queryIdToBotId.put(r25, r23);
        BaseFragment r3 = LaunchActivity.getLastFragment();
        WebViewRequestProps r1 = WebViewRequestProps.of(this.currentAccount, r21, r23, null, null, 5, 0, 0, false, null, false, null, null, 0, false, false);
        r1.queryId = r25;
        BotWebViewSheet r2 = new BotWebViewSheet(LaunchActivity.instance, null);
        r2.setDefaultFullsize(false);
        r2.setNeedsContext(true);
        r2.setParentActivity(LaunchActivity.instance);
        r2.requestWebView(r3, r1);
        r2.show();
        return;
    L11:
        if (SharedPrefsHelper.isWebViewConfirmShown(this.currentAccount, r23) == false) goto L13;
    L17:
        openGuardBotWebApp(r21, r23, r25, true);
        return;
    L13:
        if (getMessagesController().whitelistedBots.contains(Long.valueOf(r23)) == true) goto L17;
        AlertsCreator.createBotLaunchAlert(r8, r9, new BotGuardHelper$$ExternalSyntheticLambda0(this, r21, r23, r25), new BotGuardHelper$$ExternalSyntheticLambda1());
        return;
    }

    public void closeGuardBotWebApp(long r11, long r13, TLRPC.JoinChatBotResult r15) {
        long r3 = this.queryIdToBotId.get(r13, 0);
        getNotificationCenter().postNotificationName(NotificationCenter.guardBotDecisionResult, new Object[]{new GuardBotDecisionResultNotification(r11, r3, r13, r15)});
        HashSet<BotWebViewSheet> r0 = BotWebViewSheet.activeSheets;
        if (r0 == null) goto L14;
        Iterator<BotWebViewSheet> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L15;
        BotWebViewSheet r1 = r02.next();
        if (r1.isGuardBotTab(r11, r13) == false) goto L6;
        r1.dismiss();
        return;
    L15:
        return;
    }

    public void openGuardBotWebApp(long r9, long r11, long r13) {
        openGuardBotWebApp(r9, r11, r13, false);
    }
}
