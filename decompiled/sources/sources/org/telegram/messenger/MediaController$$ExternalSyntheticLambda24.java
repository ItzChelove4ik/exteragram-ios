package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaController$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ MediaController f$0;
    public final /* synthetic */ File f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_document f$3;

    public /* synthetic */ MediaController$$ExternalSyntheticLambda24(MediaController r1, File r2, boolean r3, TLRPC.TL_document r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaController.$r8$lambda$na_DDr_fOREiABHHxXhWpa0I0ss(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
