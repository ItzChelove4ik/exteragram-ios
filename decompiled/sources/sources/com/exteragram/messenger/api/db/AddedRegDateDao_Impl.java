package com.exteragram.messenger.api.db;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.exteragram.messenger.api.dto.AddedRegDateDTO;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/api/db/AddedRegDateDao_Impl;", "Lcom/exteragram/messenger/api/db/AddedRegDateDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfAddedRegDateDTO", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/api/dto/AddedRegDateDTO;", "insert", _UrlKt.FRAGMENT_ENCODE_SET, "addedRegDate", "(Lcom/exteragram/messenger/api/dto/AddedRegDateDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isAdded", _UrlKt.FRAGMENT_ENCODE_SET, "userId", _UrlKt.FRAGMENT_ENCODE_SET, "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AddedRegDateDao_Impl implements AddedRegDateDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<AddedRegDateDTO> __insertAdapterOfAddedRegDateDTO;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"com/exteragram/messenger/api/db/AddedRegDateDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/api/dto/AddedRegDateDTO;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.api.db.AddedRegDateDao_Impl$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends EntityInsertAdapter<AddedRegDateDTO> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r3, AddedRegDateDTO r4) {
            r3.bindLong(1, r4.getUserId());
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, AddedRegDateDTO r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR IGNORE INTO `AddedRegDateDTO` (`userId`) VALUES (?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/api/db/AddedRegDateDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }

    public static Unit $r8$lambda$Io97oZNfeFEOzi2ZwPsP1ngH8Do(AddedRegDateDao_Impl r0, AddedRegDateDTO r1, SQLiteConnection r2) {
        r0.__insertAdapterOfAddedRegDateDTO.insert(r2, r1);
        return Unit.INSTANCE;
    }

    public static boolean $r8$lambda$WU9sfMQkLn_FRxJgVv9GMgGynPQ(String r2, long r3, SQLiteConnection r5) {
        SQLiteStatement r22 = r5.prepare(r2);
        boolean r52 = true;
        r22.bindLong(1, r3);     // Catch: Throwable -> L11
        boolean r4 = false;
        if (r22.step() == false) goto L13;
        if (((int) r22.getLong(0)) != 0) goto L10;
        r52 = false;
    L10:
        r4 = r52;
    L13:
        r22.close();
        return r4;
    L11:
        th = move-exception;
        r22.close();
        throw th;
    }

    static {
        Companion = new Companion(null);
    }

    public AddedRegDateDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfAddedRegDateDTO = new AnonymousClass1();
    }

    @Override // com.exteragram.messenger.api.db.AddedRegDateDao
    public Object insert(AddedRegDateDTO r3, Continuation<? super Unit> r4) {
        Object r2 = DBUtil.performSuspending(this.__db, false, true, new AddedRegDateDao_Impl$$ExternalSyntheticLambda1(this, r3), r4);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.api.db.AddedRegDateDao
    public Object isAdded(long r3, Continuation<? super Boolean> r5) {
        return DBUtil.performSuspending(this.__db, true, false, new AddedRegDateDao_Impl$$ExternalSyntheticLambda0("SELECT EXISTS(SELECT 1 FROM AddedRegDateDTO WHERE userId = ?)", r3), r5);
    }
}
