package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkNameDao_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ WorkNameDao_Impl f$0;
    public final /* synthetic */ WorkName f$1;

    public /* synthetic */ WorkNameDao_Impl$$ExternalSyntheticLambda0(WorkNameDao_Impl r1, WorkName r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return WorkNameDao_Impl.$r8$lambda$dEb2KnOmeWOQKPp7ENoTNKTrqo0(this.f$0, this.f$1, (SQLiteConnection) r2);
    }
}
