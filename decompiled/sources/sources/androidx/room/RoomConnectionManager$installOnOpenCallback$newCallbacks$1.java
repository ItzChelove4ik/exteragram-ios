package androidx.room;

import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/room/RoomConnectionManager$installOnOpenCallback$newCallbacks$1", "Landroidx/room/RoomDatabase$Callback;", "onOpen", _UrlKt.FRAGMENT_ENCODE_SET, "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class RoomConnectionManager$installOnOpenCallback$newCallbacks$1 extends RoomDatabase.Callback {
    final /* synthetic */ Function1<SupportSQLiteDatabase, Unit> $onOpen;

    /* JADX WARN: Multi-variable type inference failed */
    public RoomConnectionManager$installOnOpenCallback$newCallbacks$1(Function1<? super SupportSQLiteDatabase, Unit> r1) {
        this.$onOpen = r1;
    }

    @Override // androidx.room.RoomDatabase.Callback
    public void onOpen(SupportSQLiteDatabase r1) {
        this.$onOpen.invoke(r1);
    }
}
