package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final /* synthetic */ class PreferenceDao_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ PreferenceDao_Impl f$0;
    public final /* synthetic */ Preference f$1;

    public /* synthetic */ PreferenceDao_Impl$$ExternalSyntheticLambda0(PreferenceDao_Impl r1, Preference r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return PreferenceDao_Impl.$r8$lambda$pV7ZAv1ABN_GLbYRS_jnXDCXzyk(this.f$0, this.f$1, (SQLiteConnection) r2);
    }
}
