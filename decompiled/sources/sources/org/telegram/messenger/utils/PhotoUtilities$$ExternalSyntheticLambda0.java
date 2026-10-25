package org.telegram.messenger.utils;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ImageUpdater;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda0 implements ImageUpdater.ImageUpdaterDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.FileLocation[] f$1;
    public final /* synthetic */ TLRPC.FileLocation[] f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda0(ChatActivity r1, TLRPC.FileLocation[] r2, TLRPC.FileLocation[] r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public final void didUploadPhoto(TLRPC.InputFile r15, TLRPC.InputFile r16, double r17, String r19, TLRPC.PhotoSize r20, TLRPC.PhotoSize r21, boolean r22, TLRPC.VideoSize r23) {
        PhotoUtilities.m5198$r8$lambda$4UaqjCAnbRsMNEcpG9vGjmmDbE(this.f$0, this.f$1, this.f$2, this.f$3, r15, r16, r17, r19, r20, r21, r22, r23);
    }
}
