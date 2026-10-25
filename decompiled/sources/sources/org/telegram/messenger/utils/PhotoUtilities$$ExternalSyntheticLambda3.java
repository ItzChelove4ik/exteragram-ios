package org.telegram.messenger.utils;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.FileLocation[] f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ TLRPC.FileLocation[] f$5;
    public final /* synthetic */ long f$6;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda3(TLRPC.TL_error r1, ChatActivity r2, TLObject r3, TLRPC.FileLocation[] r4, String r5, TLRPC.FileLocation[] r6, long r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoUtilities.$r8$lambda$VGyOKEJ4EPt_YZymp46qHxmuRrU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
