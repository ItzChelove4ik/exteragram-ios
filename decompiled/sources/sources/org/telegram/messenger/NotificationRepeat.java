package org.telegram.messenger;

import android.app.IntentService;
import android.content.Intent;

/* loaded from: classes.dex */
public class NotificationRepeat extends IntentService {
    public static /* synthetic */ void $r8$lambda$k6fTVUQMHljLxxUCcMtQcfqECD4(int r0) {
        NotificationsController.getInstance(r0).repeatNotificationMaybe();
    }

    public NotificationRepeat() {
        super("NotificationRepeat");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent r2) {
        if (r2 == null) goto L9;
        int r1 = r2.getIntExtra("currentAccount", UserConfig.selectedAccount);
        if (UserConfig.isValidAccount(r1) == true) goto L7;
        return;
    L7:
        AndroidUtilities.runOnUIThread(new NotificationRepeat$$ExternalSyntheticLambda0(r1));
        return;
    }
}
