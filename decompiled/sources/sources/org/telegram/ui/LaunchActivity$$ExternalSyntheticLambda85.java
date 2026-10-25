package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda85 implements Utilities.Callback2 {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda85(LaunchActivity r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        LaunchActivity.m15637$r8$lambda$okaSxvwcngEFEQ8vqjWXjGnfOs(this.f$0, this.f$1, (TL_payments.TL_StarGiftAuctionState) r2, (TLRPC.TL_error) r3);
    }
}
