package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ ChatEditTypeActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.TL_channels_checkUsername f$4;

    public /* synthetic */ ChatEditTypeActivity$$ExternalSyntheticLambda24(ChatEditTypeActivity r1, String r2, TLRPC.TL_error r3, TLObject r4, TLRPC.TL_channels_checkUsername r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatEditTypeActivity.$r8$lambda$3eXDRwatXm1BKhbOZAjSnK_sRow(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
