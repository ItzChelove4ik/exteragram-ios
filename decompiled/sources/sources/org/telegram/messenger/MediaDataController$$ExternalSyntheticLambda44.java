package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda44 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ SharedPreferences f$1;
    public final /* synthetic */ boolean[] f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda44(MediaDataController r1, SharedPreferences r2, boolean[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MediaDataController.m4452$r8$lambda$qZ_xRLvisWOZWDs6CgMEEvkERw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
