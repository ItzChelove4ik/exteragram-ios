package androidx.room.support;

import androidx.room.support.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Object[] f$1;

    public /* synthetic */ AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda2(String r1, Object[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.$r8$lambda$SMNH0gv9S0v8WmyrE5xRwPUUMRQ(this.f$0, this.f$1, (SupportSQLiteDatabase) r2);
    }
}
