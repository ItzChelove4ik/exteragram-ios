package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileUploadOperation$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ FileUploadOperation f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ byte[] f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ long f$8;

    public /* synthetic */ FileUploadOperation$$ExternalSyntheticLambda5(FileUploadOperation r1, int r2, int[] r3, int r4, byte[] r5, int r6, int r7, int r8, long r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r13, TLRPC.TL_error r14) {
        FileUploadOperation.$r8$lambda$hT1DOtLAmK5JBBO8OP_JkfxC5ng(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r13, r14);
    }
}
