package org.telegram.messenger.utils;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.FileLocation[] f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.FileLocation[] f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda2(ChatActivity r1, TLRPC.FileLocation[] r2, String r3, TLRPC.FileLocation[] r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        PhotoUtilities.$r8$lambda$Mx2YzVeYn5DUhfZ5dqRwyzRUmJI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
