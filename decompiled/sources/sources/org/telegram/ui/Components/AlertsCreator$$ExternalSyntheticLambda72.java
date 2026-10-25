package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda72 implements Utilities.Callback2 {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.Photo f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda72(int r1, long r2, TLRPC.Photo r4, Context r5, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r9, Object r10) {
        AlertsCreator.m8777$r8$lambda$ry1PicWvO88M1rZD53DUmoPJM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Integer) r9, (String) r10);
    }
}
