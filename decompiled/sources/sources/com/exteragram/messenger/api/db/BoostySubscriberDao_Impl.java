package com.exteragram.messenger.api.db;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0096@¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/exteragram/messenger/api/db/BoostySubscriberDao_Impl;", "Lcom/exteragram/messenger/api/db/BoostySubscriberDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfBoostySubscriberDTO", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", "__converters", "Lcom/exteragram/messenger/api/db/Converters;", "insertAll", _UrlKt.FRAGMENT_ENCODE_SET, "subscribers", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "replaceSubscribers", "getAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BoostySubscriberDao_Impl implements BoostySubscriberDao {
    public static final Companion Companion = null;
    private final Converters __converters;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<BoostySubscriberDTO> __insertAdapterOfBoostySubscriberDTO;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"com/exteragram/messenger/api/db/BoostySubscriberDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.api.db.BoostySubscriberDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<BoostySubscriberDTO> {
        final /* synthetic */ BoostySubscriberDao_Impl this$0;

        public AnonymousClass1(BoostySubscriberDao_Impl r1) {
            this.this$0 = r1;
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r4, BoostySubscriberDTO r5) {
            r4.bindLong(1, r5.getId());
            r4.bindText(2, r5.getName());
            String r0 = BoostySubscriberDao_Impl.access$get__converters$p(this.this$0).fromBigDecimal(r5.getTotalAmountRub());
            if (r0 != null) goto L5;
            r4.bindNull(3);
        L6:
            String r3 = BoostySubscriberDao_Impl.access$get__converters$p(this.this$0).fromBigDecimal(r5.getTotalAmountUsd());
            if (r3 != null) goto L10;
            r4.bindNull(4);
            return;
        L10:
            r4.bindText(4, r3);
            return;
        L5:
            r4.bindText(3, r0);
            goto L6
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, BoostySubscriberDTO r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR REPLACE INTO `BoostySubscriberDTO` (`id`,`name`,`totalAmountRub`,`totalAmountUsd`) VALUES (?,?,?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/api/db/BoostySubscriberDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.BoostySubscriberDao_Impl$replaceSubscribers$2", f = "BoostySubscriberDao_Impl.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.api.db.BoostySubscriberDao_Impl$replaceSubscribers$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ List<BoostySubscriberDTO> $subscribers;
        int label;
        final /* synthetic */ BoostySubscriberDao_Impl this$0;

        public AnonymousClass2(BoostySubscriberDao_Impl r1, List<BoostySubscriberDTO> r2, Continuation<? super AnonymousClass2> r3) {
            this.this$0 = r1;
            this.$subscribers = r2;
            super(1, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r3) {
            return new AnonymousClass2(this.this$0, this.$subscribers, r3);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((AnonymousClass2) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            BoostySubscriberDao_Impl r42 = this.this$0;
            List<BoostySubscriberDTO> r12 = this.$subscribers;
            this.label = 1;
            if (BoostySubscriberDao_Impl.m1097access$replaceSubscribers$s1778592684(r42, r12, this) != r0) goto L12;
            return r0;
        }
    }

    /* renamed from: $r8$lambda$JHrUJzTJ4p8ZR51yxwtYhKm-MyE, reason: not valid java name */
    public static Unit m1094$r8$lambda$JHrUJzTJ4p8ZR51yxwtYhKmMyE(BoostySubscriberDao_Impl r0, List r1, SQLiteConnection r2) {
        r0.__insertAdapterOfBoostySubscriberDTO.insert(r2, r1);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$WkYIsDznQ5-Ez88zSfDo2eDvbGA, reason: not valid java name */
    public static Unit m1095$r8$lambda$WkYIsDznQ5Ez88zSfDo2eDvbGA(String r0, SQLiteConnection r1) {
        SQLiteStatement r02 = r1.prepare(r0);
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    /* renamed from: $r8$lambda$Zcvsac0k4epf4-scrVBHgHHR-Lk, reason: not valid java name */
    public static List m1096$r8$lambda$Zcvsac0k4epf4scrVBHgHHRLk(String r11, BoostySubscriberDao_Impl r12, SQLiteConnection r13) {
        SQLiteStatement r112 = r13.prepare(r11);
        int r132 = SQLiteStatementUtil.getColumnIndexOrThrow(r112, "id");     // Catch: Throwable -> L20
        int r0 = SQLiteStatementUtil.getColumnIndexOrThrow(r112, "name");     // Catch: Throwable -> L20
        int r1 = SQLiteStatementUtil.getColumnIndexOrThrow(r112, "totalAmountRub");     // Catch: Throwable -> L20
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r112, "totalAmountUsd");     // Catch: Throwable -> L20
        ArrayList r3 = new ArrayList();     // Catch: Throwable -> L20
    L5:
        if (r112.step() == false) goto L26;
        long r6 = r112.getLong(r132);     // Catch: Throwable -> L20
        String r8 = r112.getText(r0);     // Catch: Throwable -> L20
        String r5 = null;
        if (r112.isNull(r1) == false) goto L9;
        String r4 = null;
    L10:
        BigDecimal r9 = r12.__converters.toBigDecimal(r4);     // Catch: Throwable -> L20
        if (r9 == null) goto L25;
        if (r112.isNull(r2) == true) goto L17;
        r5 = r112.getText(r2);     // Catch: Throwable -> L20
    L17:
        BigDecimal r10 = r12.__converters.toBigDecimal(r5);     // Catch: Throwable -> L20
        if (r10 == null) goto L23;
        r3.add(new BoostySubscriberDTO(r6, r8, r9, r10));     // Catch: Throwable -> L20
        goto L5
    L23:
        throw new IllegalStateException("Expected NON-NULL 'java.math.BigDecimal', but it was NULL.");     // Catch: Throwable -> L20
    L25:
        throw new IllegalStateException("Expected NON-NULL 'java.math.BigDecimal', but it was NULL.");     // Catch: Throwable -> L20
    L9:
        r4 = r112.getText(r1);     // Catch: Throwable -> L20
        goto L10
    L26:
        r112.close();
        return r3;
    L20:
        th = move-exception;
        r112.close();
        throw th;
    }

    static {
        Companion = new Companion(null);
    }

    public BoostySubscriberDao_Impl(RoomDatabase r2) {
        this.__converters = new Converters();
        this.__db = r2;
        this.__insertAdapterOfBoostySubscriberDTO = new AnonymousClass1(this);
    }

    public static final /* synthetic */ Converters access$get__converters$p(BoostySubscriberDao_Impl r0) {
        return r0.__converters;
    }

    /* renamed from: access$replaceSubscribers$s-1778592684, reason: not valid java name */
    public static final /* synthetic */ Object m1097access$replaceSubscribers$s1778592684(BoostySubscriberDao_Impl r0, List r1, Continuation r2) {
        return super.replaceSubscribers(r1, r2);
    }

    @Override // com.exteragram.messenger.api.db.BoostySubscriberDao
    public Object deleteAll(Continuation<? super Unit> r4) {
        Object r3 = DBUtil.performSuspending(this.__db, false, true, new BoostySubscriberDao_Impl$$ExternalSyntheticLambda2("DELETE FROM BoostySubscriberDTO"), r4);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r3;
    L6:
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.api.db.BoostySubscriberDao
    public Object getAll(Continuation<? super List<BoostySubscriberDTO>> r4) {
        return DBUtil.performSuspending(this.__db, true, false, new BoostySubscriberDao_Impl$$ExternalSyntheticLambda0("SELECT * FROM BoostySubscriberDTO", this), r4);
    }

    @Override // com.exteragram.messenger.api.db.BoostySubscriberDao
    public Object insertAll(List<BoostySubscriberDTO> r3, Continuation<? super Unit> r4) {
        Object r2 = DBUtil.performSuspending(this.__db, false, true, new BoostySubscriberDao_Impl$$ExternalSyntheticLambda1(this, r3), r4);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.api.db.BoostySubscriberDao
    public Object replaceSubscribers(List<BoostySubscriberDTO> r4, Continuation<? super Unit> r5) {
        Object r3 = DBUtil.performInTransactionSuspending(this.__db, new AnonymousClass2(this, r4, null), r5);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r3;
    L6:
        return Unit.INSTANCE;
    }
}
