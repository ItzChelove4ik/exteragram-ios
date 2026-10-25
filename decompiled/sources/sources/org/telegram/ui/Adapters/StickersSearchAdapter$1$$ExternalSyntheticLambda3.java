package org.telegram.ui.Adapters;

import android.util.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.StickersSearchAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickersSearchAdapter$1$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ StickersSearchAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_messages_getStickers f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ LongSparseArray f$4;

    public /* synthetic */ StickersSearchAdapter$1$$ExternalSyntheticLambda3(StickersSearchAdapter.AnonymousClass1 r1, TLRPC.TL_messages_getStickers r2, TLObject r3, ArrayList r4, LongSparseArray r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersSearchAdapter.AnonymousClass1.$r8$lambda$7ppSvVJi80amOi8QzAo1duJGDeE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
