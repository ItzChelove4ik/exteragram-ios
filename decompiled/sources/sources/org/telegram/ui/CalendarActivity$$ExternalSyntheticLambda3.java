package org.telegram.ui;

import java.util.Calendar;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class CalendarActivity$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ CalendarActivity f$0;
    public final /* synthetic */ Calendar f$1;

    public /* synthetic */ CalendarActivity$$ExternalSyntheticLambda3(CalendarActivity r1, Calendar r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        CalendarActivity.m6480$r8$lambda$Sai1T45kBafASdzcwMW4sQOx_4(this.f$0, this.f$1, r2, r3);
    }
}
