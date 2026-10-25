package org.telegram.messenger;

import org.telegram.messenger.ImageLoader;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ImageLoader$5$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.InputFile f$2;
    public final /* synthetic */ TLRPC.InputEncryptedFile f$3;
    public final /* synthetic */ byte[] f$4;
    public final /* synthetic */ byte[] f$5;
    public final /* synthetic */ long f$6;

    public /* synthetic */ ImageLoader$5$$ExternalSyntheticLambda7(int r1, String r2, TLRPC.InputFile r3, TLRPC.InputEncryptedFile r4, byte[] r5, byte[] r6, long r7) {
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
        ImageLoader.AnonymousClass5.$r8$lambda$bH0hUPpVEi6rZd3xwmsKt6LUGmU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
