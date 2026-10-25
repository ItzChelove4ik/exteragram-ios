package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegateTimestamp;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LivePlayer$$ExternalSyntheticLambda13 implements RequestDelegateTimestamp {
    public final /* synthetic */ LivePlayer f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ int f$6;

    public /* synthetic */ LivePlayer$$ExternalSyntheticLambda13(LivePlayer r1, String r2, long r3, long r5, long r7, int r9, int r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r7;
        this.f$5 = r9;
        this.f$6 = r10;
    }

    @Override // org.telegram.tgnet.RequestDelegateTimestamp
    public final void run(TLObject r15, TLRPC.TL_error r16, long r17) {
        LivePlayer.m19366$r8$lambda$pKlFJXH7vedGr8xjSWCClbHf_g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r15, r16, r17);
    }
}
