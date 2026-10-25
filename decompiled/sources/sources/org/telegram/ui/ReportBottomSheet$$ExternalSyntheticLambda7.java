package org.telegram.ui;

import android.content.Context;
import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ ArrayList f$5;
    public final /* synthetic */ boolean[] f$6;
    public final /* synthetic */ Utilities.Callback f$7;
    public final /* synthetic */ BulletinFactory f$8;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda7(Context r1, Theme.ResourcesProvider r2, boolean r3, boolean r4, long r5, ArrayList r7, boolean[] r8, Utilities.Callback r9, BulletinFactory r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r13, Object r14) {
        ReportBottomSheet.$r8$lambda$nRzlwNRI7_41hfGrnqV_4T8NfTI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (TLRPC.ReportResult) r13, (TLRPC.TL_error) r14);
    }
}
