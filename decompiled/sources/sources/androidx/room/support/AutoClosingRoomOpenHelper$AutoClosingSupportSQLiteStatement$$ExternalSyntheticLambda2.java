package androidx.room.support;

import androidx.room.support.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteStatement f$0;
    public final /* synthetic */ Function1 f$1;

    public /* synthetic */ AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement$$ExternalSyntheticLambda2(AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteStatement r1, Function1 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteStatement.$r8$lambda$o_YyUWDlwh2377MTf9skJYHZRd4(this.f$0, this.f$1, (SupportSQLiteDatabase) r2);
    }
}
