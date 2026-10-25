package org.telegram.ui.Components.Paint.Views;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LocationMarker$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ LocationMarker f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LocationMarker$$ExternalSyntheticLambda0(LocationMarker r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        LocationMarker.m11517$r8$lambda$mKKWCTrba1vsE2rllRPjvjgU6U(this.f$0, this.f$1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
