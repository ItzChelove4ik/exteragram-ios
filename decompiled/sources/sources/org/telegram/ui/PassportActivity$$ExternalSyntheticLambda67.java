package org.telegram.ui;

import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda67 implements DialogInterface.OnClickListener {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda67(PassportActivity r1, TLRPC.TL_secureRequiredType r2, ArrayList r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r7, int r8) {
        PassportActivity.m16418$r8$lambda$t05J2nSKefzTUe6O0irNt2AjWs(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
