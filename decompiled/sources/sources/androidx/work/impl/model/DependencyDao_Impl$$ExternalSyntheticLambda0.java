package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class DependencyDao_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ DependencyDao_Impl f$0;
    public final /* synthetic */ Dependency f$1;

    public /* synthetic */ DependencyDao_Impl$$ExternalSyntheticLambda0(DependencyDao_Impl r1, Dependency r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return DependencyDao_Impl.$r8$lambda$bODlJfJugFiPnEHw_lj7V3cbm8M(this.f$0, this.f$1, (SQLiteConnection) r2);
    }
}
