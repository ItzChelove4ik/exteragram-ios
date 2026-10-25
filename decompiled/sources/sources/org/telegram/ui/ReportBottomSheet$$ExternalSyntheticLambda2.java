package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ byte[] f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ BulletinFactory f$5;
    public final /* synthetic */ Runnable f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda2(Context r1, Theme.ResourcesProvider r2, long r3, byte[] r5, Runnable r6, BulletinFactory r7, Runnable r8, int r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        ReportBottomSheet.m18474$r8$lambda$sk9k1CwXAo34W_LMyeQhzN2BzA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
