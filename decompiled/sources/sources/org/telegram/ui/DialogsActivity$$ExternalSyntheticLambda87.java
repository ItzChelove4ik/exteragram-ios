package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda87 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda87(DialogsActivity r1, int r2, TLRPC.Chat r3, long r4, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r8) {
        DialogsActivity.m14373$r8$lambda$MPjwIVLR3pDV862YEhEX_D1864(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8);
    }
}
