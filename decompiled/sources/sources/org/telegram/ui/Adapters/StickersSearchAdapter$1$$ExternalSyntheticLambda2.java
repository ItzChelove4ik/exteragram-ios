package org.telegram.ui.Adapters;

import android.util.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.StickersSearchAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickersSearchAdapter$1$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ StickersSearchAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_messages_getStickers f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ LongSparseArray f$3;

    public /* synthetic */ StickersSearchAdapter$1$$ExternalSyntheticLambda2(StickersSearchAdapter.AnonymousClass1 r1, TLRPC.TL_messages_getStickers r2, ArrayList r3, LongSparseArray r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StickersSearchAdapter.AnonymousClass1.$r8$lambda$m4aeHofrhemXoARXEkuXcvBI1Wc(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
