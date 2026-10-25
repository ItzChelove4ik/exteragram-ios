package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import androidx.work.WorkInfo$State;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkSpecDao_Impl$$ExternalSyntheticLambda14 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ WorkInfo$State f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ WorkSpecDao_Impl$$ExternalSyntheticLambda14(String r1, WorkInfo$State r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r3) {
        return Integer.valueOf(WorkSpecDao_Impl.$r8$lambda$3YgOuD96vxpWzCW8tqVMWPtOMeA(this.f$0, this.f$1, this.f$2, (SQLiteConnection) r3));
    }
}
