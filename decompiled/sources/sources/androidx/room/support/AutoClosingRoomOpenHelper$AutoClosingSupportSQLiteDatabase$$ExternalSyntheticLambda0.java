package androidx.room.support;

import android.content.ContentValues;
import androidx.room.support.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ContentValues f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Object[] f$4;

    public /* synthetic */ AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda0(String r1, int r2, ContentValues r3, String r4, Object[] r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r7) {
        return Integer.valueOf(AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.$r8$lambda$t6vekZ07UyW_LkprcrJYW9Tn6e8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (SupportSQLiteDatabase) r7));
    }
}
