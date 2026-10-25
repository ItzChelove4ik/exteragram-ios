package org.telegram.messenger.voip;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda32 implements RequestDelegate {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda32(ArrayList r1, ArrayList r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        VoIPService.$r8$lambda$R0RWQTd4j2SM8liI7TXJVbAWJ7E(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
