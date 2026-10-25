package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.HashMap;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda482 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ HashMap f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda482(MessagesController r1, HashMap r2, String r3, LongSparseArray r4, long r5, int r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        MessagesController.$r8$lambda$fRW7JO_HrBrM6MIGgMUj_pV_d0A(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
