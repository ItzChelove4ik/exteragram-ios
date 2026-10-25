package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final /* synthetic */ class WorkSpecDao_Impl$$ExternalSyntheticLambda22 implements Function1 {
    public final /* synthetic */ WorkSpecDao_Impl f$0;
    public final /* synthetic */ WorkSpec f$1;

    public /* synthetic */ WorkSpecDao_Impl$$ExternalSyntheticLambda22(WorkSpecDao_Impl r1, WorkSpec r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return WorkSpecDao_Impl.$r8$lambda$1dPPQOz9wdzZqLrC9ZC41PXLr2A(this.f$0, this.f$1, (SQLiteConnection) r2);
    }
}
