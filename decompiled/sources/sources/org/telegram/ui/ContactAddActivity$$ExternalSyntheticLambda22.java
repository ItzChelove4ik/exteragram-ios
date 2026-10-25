package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContactAddActivity$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ ContactAddActivity f$0;
    public final /* synthetic */ TLRPC.FileLocation f$1;
    public final /* synthetic */ TLRPC.InputFile f$2;
    public final /* synthetic */ TLRPC.FileLocation f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ ContactAddActivity$$ExternalSyntheticLambda22(ContactAddActivity r1, TLRPC.FileLocation r2, TLRPC.InputFile r3, TLRPC.FileLocation r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ContactAddActivity.$r8$lambda$45TFOTli08OMsKbiUjniubUwJGw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
