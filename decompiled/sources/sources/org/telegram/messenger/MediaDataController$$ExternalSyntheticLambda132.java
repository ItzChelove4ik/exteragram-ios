package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda132 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ SharedPreferences f$1;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda132(MediaDataController r1, SharedPreferences r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        MediaDataController.m4420$r8$lambda$Veb6eRu9aAYt0yW5GBrdqlGXwg(this.f$0, this.f$1, r2, r3);
    }
}
