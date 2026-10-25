package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditActivity$$ExternalSyntheticLambda64 implements MessagesStorage.LongCallback {
    public final /* synthetic */ ChatEditActivity f$0;
    public final /* synthetic */ AlertDialog f$1;

    public /* synthetic */ ChatEditActivity$$ExternalSyntheticLambda64(ChatEditActivity r1, AlertDialog r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r2) {
        ChatEditActivity.m8265$r8$lambda$dSTKF0SkZcvUfr7F0oPuFT3zRo(this.f$0, this.f$1, r2);
    }
}
