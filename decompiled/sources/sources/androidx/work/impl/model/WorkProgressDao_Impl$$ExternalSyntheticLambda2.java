package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkProgressDao_Impl$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ WorkProgressDao_Impl f$0;
    public final /* synthetic */ WorkProgress f$1;

    public /* synthetic */ WorkProgressDao_Impl$$ExternalSyntheticLambda2(WorkProgressDao_Impl r1, WorkProgress r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return WorkProgressDao_Impl.m990$r8$lambda$PINVEeB__LLptuNxhS4Ei1b1_s(this.f$0, this.f$1, (SQLiteConnection) r2);
    }
}
