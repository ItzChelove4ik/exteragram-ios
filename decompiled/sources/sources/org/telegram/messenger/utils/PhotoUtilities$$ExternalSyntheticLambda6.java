package org.telegram.messenger.utils;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.INavigationLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.PhotoSize f$1;
    public final /* synthetic */ TLRPC.PhotoSize f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ INavigationLayout f$4;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda6(int r1, TLRPC.PhotoSize r2, TLRPC.PhotoSize r3, Runnable r4, INavigationLayout r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        PhotoUtilities.m5197$r8$lambda$rf7IQp9Jz1wkQHGw2xBg1dde9Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
