package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class AutoMessageHeardReceiver extends BroadcastReceiver {
    public static /* synthetic */ void $r8$lambda$35ZRQZRlu8JOsUNNako0NuUXfpQ(AccountInstance r8, long r9, int r11, int r12) {
        AndroidUtilities.runOnUIThread(new AutoMessageHeardReceiver$$ExternalSyntheticLambda2(r8, r8.getMessagesStorage().getUserSync(r9), r11, r9, r12));
    }

    public static /* synthetic */ void $r8$lambda$7lrgVyjPgesrKCh6y8t2oUowExo(AccountInstance r10, long r11, int r13, int r14) {
        AndroidUtilities.runOnUIThread(new AutoMessageHeardReceiver$$ExternalSyntheticLambda3(r10, r10.getMessagesStorage().getChatSync(-r11), r13, r11, r14));
    }

    public static /* synthetic */ void $r8$lambda$JZ0ZVRnE_mudffloArbdprRwmHY(AccountInstance r13, TLRPC.User r14, int r15, long r16, int r18) {
        r13.getMessagesController().putUser(r14, true);
        MessagesController.getInstance(r15).markDialogAsRead(r16, r18, r18, 0, false, 0, 0, true, 0);
        MessagesController.getInstance(r15).markReactionsAsRead(r16, 0);
    }

    public static /* synthetic */ void $r8$lambda$xE10r7__ydkGMeTafaHrWz9rQww(AccountInstance r13, TLRPC.Chat r14, int r15, long r16, int r18) {
        r13.getMessagesController().putChat(r14, true);
        MessagesController.getInstance(r15).markDialogAsRead(r16, r18, r18, 0, false, 0, 0, true, 0);
        MessagesController.getInstance(r15).markReactionsAsRead(r16, 0);
    }

    public AutoMessageHeardReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r17, Intent r18) {
        ApplicationLoader.postInitApplication();
        long r6 = r18.getLongExtra("dialog_id", 0);
        int r9 = r18.getIntExtra("max_id", 0);
        int r8 = r18.getIntExtra("currentAccount", 0);
        if (r6 == 0) goto L23;
        if (r9 != 0) goto L6;
        return;
    L6:
        if (UserConfig.isValidAccount(r8) == false) goto L25;
        AccountInstance r5 = AccountInstance.getInstance(r8);
        if (DialogObject.isUserDialog(r6) == false) goto L16;
        if (r5.getMessagesController().getUser(Long.valueOf(r6)) != null) goto L21;
        Utilities.globalQueue.postRunnable(new AutoMessageHeardReceiver$$ExternalSyntheticLambda0(r5, r6, r8, r9));
        return;
    L21:
        MessagesController.getInstance(r8).markDialogAsRead(r6, r9, r9, 0, false, 0, 0, true, 0);
        MessagesController.getInstance(r8).markReactionsAsRead(r6, 0);
        return;
    L16:
        if (DialogObject.isChatDialog(r6) == false) goto L21;
        if (r5.getMessagesController().getChat(Long.valueOf(-r6)) != null) goto L21;
        Utilities.globalQueue.postRunnable(new AutoMessageHeardReceiver$$ExternalSyntheticLambda1(r5, r6, r8, r9));
        return;
    L25:
        return;
    }
}
