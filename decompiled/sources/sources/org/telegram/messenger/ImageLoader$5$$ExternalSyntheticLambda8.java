package org.telegram.messenger;

import org.telegram.messenger.ImageLoader;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ImageLoader$5$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ImageLoader.AnonymousClass5 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.InputFile f$3;
    public final /* synthetic */ TLRPC.InputEncryptedFile f$4;
    public final /* synthetic */ byte[] f$5;
    public final /* synthetic */ byte[] f$6;
    public final /* synthetic */ long f$7;

    public /* synthetic */ ImageLoader$5$$ExternalSyntheticLambda8(ImageLoader.AnonymousClass5 r1, int r2, String r3, TLRPC.InputFile r4, TLRPC.InputEncryptedFile r5, byte[] r6, byte[] r7, long r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ImageLoader.AnonymousClass5.$r8$lambda$Bsqqpu4ROY59Lf02mMMvM7KgGCU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
