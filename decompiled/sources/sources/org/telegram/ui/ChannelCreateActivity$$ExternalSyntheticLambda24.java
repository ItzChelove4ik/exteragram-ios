package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelCreateActivity$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ ChannelCreateActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.TL_channels_checkUsername f$4;

    public /* synthetic */ ChannelCreateActivity$$ExternalSyntheticLambda24(ChannelCreateActivity r1, String r2, TLRPC.TL_error r3, TLObject r4, TLRPC.TL_channels_checkUsername r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelCreateActivity.$r8$lambda$NPCbFzt1N7mNzXjPAfezAQbp4yo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
