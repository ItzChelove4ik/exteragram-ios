package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda174 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ SharedPreferences f$3;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda174(MediaDataController r1, TLRPC.TL_error r2, TLObject r3, SharedPreferences r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.m4431$r8$lambda$bZlxvORYH1W_O2rPaCH7yF5UU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
