package org.telegram.messenger;

import org.telegram.messenger.MediaController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaController$MediaLoader$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ MediaController.MediaLoader f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.PhotoSize f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ TLRPC.Photo f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.Document f$6;

    public /* synthetic */ MediaController$MediaLoader$$ExternalSyntheticLambda5(MediaController.MediaLoader r1, boolean r2, TLRPC.PhotoSize r3, MessageObject r4, TLRPC.Photo r5, boolean r6, TLRPC.Document r7) {
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
        MediaController.MediaLoader.m4362$r8$lambda$g56jRdVgGl_F7sjueYqj67w5Vg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
