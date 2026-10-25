package org.telegram.messenger.voip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes6.dex */
public class VoIPActionsReceiver extends BroadcastReceiver {
    public VoIPActionsReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r6, Intent r7) {
        if (r7.hasExtra("group_call_invite_msg_id") == false) goto L5;
    L8:
        String r0 = r6.getPackageName();
        if ((r0 + ".END_CALL").equals(r7.getAction()) == false) goto L17;
        if (r7.hasExtra("group_call_invite_msg_id") == false) goto L14;
        VoIPGroupNotification.decline(r6, r7.getIntExtra("currentAccount", 0), r7.getIntExtra("group_call_invite_msg_id", 0));
        return;
    L14:
        VoIPPreNotificationService.decline(r6, 1);
        return;
    L17:
        if ((r0 + ".DECLINE_CALL").equals(r7.getAction()) == false) goto L25;
        if (r7.hasExtra("group_call_invite_msg_id") == false) goto L22;
        VoIPGroupNotification.decline(r6, r7.getIntExtra("currentAccount", 0), r7.getIntExtra("group_call_invite_msg_id", 0));
        return;
    L22:
        VoIPPreNotificationService.decline(r6, 4);
        return;
    L25:
        if ((r0 + ".ANSWER_CALL").equals(r7.getAction()) == false) goto L33;
        if (r7.hasExtra("group_call_invite_msg_id") == false) goto L30;
        VoIPGroupNotification.answer(r6, r7.getIntExtra("currentAccount", 0), r7.getIntExtra("group_call_invite_msg_id", 0));
        return;
    L30:
        VoIPPreNotificationService.answer(r6);
        return;
    L33:
        if ((r0 + ".HIDE_CALL").equals(r7.getAction()) == true) goto L35;
        return;
    L35:
        if (r7.hasExtra("group_call_invite_msg_id") == false) goto L38;
        VoIPGroupNotification.hide(r6, r7.getIntExtra("currentAccount", 0), r7.getIntExtra("group_call_invite_msg_id", 0));
        return;
    L38:
        VoIPPreNotificationService.dismiss(r6, false);
        return;
    L5:
        if (VoIPService.getSharedInstance() == null) goto L8;
        VoIPService.getSharedInstance().handleNotificationAction(r7);
    }
}
