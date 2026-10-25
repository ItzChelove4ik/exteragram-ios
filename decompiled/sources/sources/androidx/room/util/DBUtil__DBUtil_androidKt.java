package androidx.room.util;

import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.TransactionElement;
import androidx.room.coroutines.RunBlockingUninterruptible_androidKt;
import androidx.sqlite.SQLiteConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001aB\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\u0006H\u0087@¢\u0006\u0004\b\t\u0010\n\u001aA\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\u0006H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a<\u0010\u000f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u001c\u0010\b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006H\u0087@¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001c\u0010\u0012\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\u0080@¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0007¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"R", "Landroidx/room/RoomDatabase;", "db", _UrlKt.FRAGMENT_ENCODE_SET, "isReadOnly", "inTransaction", "Lkotlin/Function1;", "Landroidx/sqlite/SQLiteConnection;", "block", "performSuspending", "(Landroidx/room/RoomDatabase;ZZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performBlocking", "(Landroidx/room/RoomDatabase;ZZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "performInTransactionSuspending", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "(Landroidx/room/RoomDatabase;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/io/File;", "databaseFile", _UrlKt.FRAGMENT_ENCODE_SET, "readVersion", "(Ljava/io/File;)I", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/util/DBUtil")
@SourceDebugExtension({"SMAP\nDBUtil.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt\n+ 2 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt\n*L\n1#1,259:1\n115#1,2:260\n118#1:264\n115#1,2:265\n118#1:269\n48#2:262\n67#2:263\n48#2:267\n67#2:268\n*S KotlinDebug\n*F\n+ 1 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt\n*L\n53#1:260,2\n53#1:264\n101#1:265,2\n101#1:269\n54#1:262\n54#1:263\n102#1:267\n102#1:268\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class DBUtil__DBUtil_androidKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1", f = "DBUtil.android.kt", i = {}, l = {72}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1, reason: invalid class name */
    public static final class AnonymousClass1<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function1<SQLiteConnection, R> $block;
        final /* synthetic */ CoroutineContext $context;
        final /* synthetic */ RoomDatabase $db;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ boolean $isReadOnly;
        int label;

        @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1$1", f = "DBUtil.android.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nDBUtil.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performBlocking$1$1\n+ 2 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt\n*L\n1#1,259:1\n48#2:260\n67#2:261\n*S KotlinDebug\n*F\n+ 1 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performBlocking$1$1\n*L\n77#1:260\n77#1:261\n*E\n"})
        /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
            final /* synthetic */ Function1<SQLiteConnection, R> $block;
            final /* synthetic */ RoomDatabase $db;
            final /* synthetic */ boolean $inTransaction;
            final /* synthetic */ boolean $isReadOnly;
            int label;

            /* JADX WARN: Multi-variable type inference failed */
            public C00101(RoomDatabase r1, boolean r2, boolean r3, Function1<? super SQLiteConnection, ? extends R> r4, Continuation<? super C00101> r5) {
                this.$db = r1;
                this.$inTransaction = r2;
                this.$isReadOnly = r3;
                this.$block = r4;
                super(2, r5);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
                return new C00101(this.$db, this.$inTransaction, this.$isReadOnly, this.$block, r8);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
                return invoke(r1, (Continuation) r2);
            }

            public final Object invoke(CoroutineScope r1, Continuation<? super R> r2) {
                return ((C00101) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r10) {
                Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int r1 = this.label;
                if (r1 == 0) goto L9;
                if (r1 != 1) goto L7;
                ResultKt.throwOnFailure(r10);
                return r10;
            L7:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L9:
                ResultKt.throwOnFailure(r10);
                if (this.$db.inCompatibilityMode() == false) goto L14;
                if (this.$db.inTransaction() == false) goto L14;
            L16:
                boolean r4 = false;
            L17:
                RoomDatabase r6 = this.$db;
                boolean r5 = this.$isReadOnly;
                DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1 r3 = new DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1(r4, r5, r6, null, this.$block);
                this.label = 1;
                Object r9 = r6.useConnection(r5, r3, this);
                if (r9 != r0) goto L20;
                return r0;
            L20:
                return r9;
            L14:
                if (this.$inTransaction == false) goto L16;
                r4 = true;
                goto L17
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(CoroutineContext r1, RoomDatabase r2, boolean r3, boolean r4, Function1<? super SQLiteConnection, ? extends R> r5, Continuation<? super AnonymousClass1> r6) {
            this.$context = r1;
            this.$db = r2;
            this.$inTransaction = r3;
            this.$isReadOnly = r4;
            this.$block = r5;
            super(2, r6);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
            return new AnonymousClass1(this.$context, this.$db, this.$inTransaction, this.$isReadOnly, this.$block, r9);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super R> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r10) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r10);
            return r10;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r10);
            CoroutineContext r102 = this.$context;
            C00101 r3 = new C00101(this.$db, this.$inTransaction, this.$isReadOnly, this.$block, null);
            this.label = 1;
            Object r9 = BuildersKt.withContext(r102, r3, this);
            if (r9 != r0) goto L12;
            return r0;
        L12:
            return r9;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt", f = "DBUtil.android.kt", i = {2, 2}, l = {97, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED}, m = "performInTransactionSuspending", n = {"db", "block"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performInTransactionSuspending$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01261<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C01261(Continuation<? super C01261> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DBUtil.performInTransactionSuspending(null, null, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\n"}, d2 = {"<anonymous>", "R"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performInTransactionSuspending$2", f = "DBUtil.android.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nDBUtil.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performInTransactionSuspending$2\n+ 2 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt\n*L\n1#1,259:1\n48#2:260\n67#2:261\n*S KotlinDebug\n*F\n+ 1 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performInTransactionSuspending$2\n*L\n98#1:260\n98#1:261\n*E\n"})
    /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performInTransactionSuspending$2, reason: invalid class name */
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        final /* synthetic */ RoomDatabase $db;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(RoomDatabase r1, Function1<? super Continuation<? super R>, ? extends Object> r2, Continuation<? super AnonymousClass2> r3) {
            this.$db = r1;
            this.$block = r2;
            super(1, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r3) {
            return new AnonymousClass2(this.$db, this.$block, r3);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object r1) {
            return invoke((Continuation) r1);
        }

        public final Object invoke(Continuation<? super R> r1) {
            return ((AnonymousClass2) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r10) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r10);
            return r10;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r10);
            RoomDatabase r6 = this.$db;
            DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1 r3 = new DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1(true, false, r6, null, this.$block);
            this.label = 1;
            Object r9 = r6.useConnection(false, r3, this);
            if (r9 != r0) goto L12;
            return r0;
        L12:
            return r9;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt", f = "DBUtil.android.kt", i = {1, 1, 1, 1}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED}, m = "performSuspending", n = {"db", "block", "isReadOnly", "inTransaction"}, s = {"L$0", "L$1", "Z$0", "Z$1"})
    /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performSuspending$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01271<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public C01271(Continuation<? super C01271> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DBUtil.performSuspending(null, false, false, null, this);
        }
    }

    public static final Object getCoroutineContext(RoomDatabase r1, boolean r2, Continuation<? super CoroutineContext> r3) {
        TransactionElement r32 = (TransactionElement) r3.getContext().get(TransactionElement.Key);
        if (r32 == null) goto L5;
        CoroutineContext r33 = r32.getTransactionDispatcher$room_runtime();
    L7:
        if (r1.inCompatibilityMode() == false) goto L16;
        if (r33 != null) goto L10;
        if (r2 == false) goto L15;
        return r1.getTransactionContext$room_runtime();
    L15:
        return r1.getQueryContext();
    L10:
        return r1.getQueryContext().plus(r33);
    L16:
        CoroutineContext r12 = r1.getQueryContext();
        if (r33 != null) goto L21;
        r33 = EmptyCoroutineContext.INSTANCE;
    L21:
        return r12.plus(r33);
    L5:
        r33 = null;
        goto L7
    }

    public static final <R> R performBlocking(RoomDatabase r8, boolean r9, boolean r10, Function1<? super SQLiteConnection, ? extends R> r11) {
        r8.assertNotMainThread();
        r8.assertNotSuspendingTransaction();
        CoroutineContext r0 = r8.getSuspendingTransactionContext().get();
        if (r0 != null) goto L6;
        r0 = EmptyCoroutineContext.INSTANCE;
    L6:
        return (R) RunBlockingUninterruptible_androidKt.runBlockingUninterruptible(new AnonymousClass1(r0, r8, r10, r9, r11, null));
    }

    public static final <R> Object performInTransactionSuspending(RoomDatabase r11, Function1<? super Continuation<? super R>, ? extends Object> r12, Continuation<? super R> r13) {
        if ((r13 instanceof C01261) == false) goto L8;
        C01261 r1 = (C01261) r13;
        int r2 = r1.label;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L8;
        r1.label = r2 - Integer.MIN_VALUE;
    L7:
        C01261 r6 = r1;
        Object r0 = r6.result;
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r14 = r6.label;
        if (r14 == 0) goto L24;
        if (r14 == 1) goto L22;
        if (r14 == 2) goto L20;
        if (r14 == 3) goto L19;
        if (r14 != 4) goto L17;
        ResultKt.throwOnFailure(r0);
        return r0;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        Function1<? super Continuation<? super R>, ? extends Object> r15 = (Function1) r6.L$1;
        RoomDatabase r3 = (RoomDatabase) r6.L$0;
        ResultKt.throwOnFailure(r0);
    L44:
        DBUtil__DBUtil_androidKt$performInTransactionSuspending$$inlined$compatCoroutineExecute$DBUtil__DBUtil_androidKt$1 r4 = new DBUtil__DBUtil_androidKt$performInTransactionSuspending$$inlined$compatCoroutineExecute$DBUtil__DBUtil_androidKt$1(null, r3, r15);
        r6.L$0 = null;
        r6.L$1 = null;
        r6.label = 4;
        Object r02 = BuildersKt.withContext((CoroutineContext) r0, r4, r6);
        if (r02 != r7) goto L47;
    L46:
        return r7;
    L47:
        return r02;
    L20:
        ResultKt.throwOnFailure(r0);
        return r0;
    L22:
        ResultKt.throwOnFailure(r0);
        return r0;
    L24:
        ResultKt.throwOnFailure(r0);
        if (r11.inCompatibilityMode() == false) goto L31;
        AnonymousClass2 r03 = new AnonymousClass2(r11, r12, null);
        r6.label = 1;
        Object r04 = RoomDatabaseKt.withTransactionContext(r11, r03, r6);
        if (r04 == r7) goto L46;
        return r04;
    L31:
        if (r11.inCompatibilityMode() == true) goto L33;
    L40:
        r6.L$0 = r11;
        r6.L$1 = r12;
        r6.label = 3;
        r0 = DBUtil.getCoroutineContext(r11, true, r6);
        if (r0 == r7) goto L46;
        r3 = r11;
        r15 = r12;
        goto L44
    L33:
        if (r11.isOpenInternal$room_runtime() == false) goto L40;
        if (r11.inTransaction() == false) goto L40;
        DBUtil__DBUtil_androidKt$performInTransactionSuspending$lambda$3$$inlined$internalPerform$1 r05 = new DBUtil__DBUtil_androidKt$performInTransactionSuspending$lambda$3$$inlined$internalPerform$1(true, false, r11, null, r12);
        r6.label = 2;
        Object r06 = r11.useConnection(false, r05, r6);
        if (r06 == r7) goto L46;
        return r06;
    L8:
        r1 = new C01261(r13);
        goto L7
    }

    public static final <R> Object performSuspending(RoomDatabase r14, boolean r15, boolean r16, Function1<? super SQLiteConnection, ? extends R> r17, Continuation<? super R> r18) {
        if ((r18 instanceof C01271) == false) goto L8;
        C01271 r1 = (C01271) r18;
        int r2 = r1.label;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L8;
        r1.label = r2 - Integer.MIN_VALUE;
    L7:
        C01271 r6 = r1;
        Object r0 = r6.result;
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r6.label;
        if (r12 == 0) goto L22;
        if (r12 == 1) goto L20;
        if (r12 == 2) goto L18;
        if (r12 != 3) goto L16;
        ResultKt.throwOnFailure(r0);
        return r0;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        boolean r142 = r6.Z$1;
        r15 = r6.Z$0;
        Function1<? super SQLiteConnection, ? extends R> r13 = (Function1) r6.L$1;
        RoomDatabase r4 = (RoomDatabase) r6.L$0;
        ResultKt.throwOnFailure(r0);
        boolean r122 = r142;
        Function1<? super SQLiteConnection, ? extends R> r132 = r13;
        RoomDatabase r10 = r4;
    L36:
        DBUtil__DBUtil_androidKt$performSuspending$$inlined$compatCoroutineExecute$DBUtil__DBUtil_androidKt$1 r8 = new DBUtil__DBUtil_androidKt$performSuspending$$inlined$compatCoroutineExecute$DBUtil__DBUtil_androidKt$1(null, r10, r15, r122, r132);
        r6.L$0 = null;
        r6.L$1 = null;
        r6.label = 3;
        Object r143 = BuildersKt.withContext((CoroutineContext) r0, r8, r6);
        if (r143 != r7) goto L39;
    L38:
        return r7;
    L39:
        return r143;
    L20:
        ResultKt.throwOnFailure(r0);
        return r0;
    L22:
        ResultKt.throwOnFailure(r0);
        if (r14.inCompatibilityMode() == true) goto L25;
    L32:
        r6.L$0 = r14;
        r6.L$1 = r17;
        r6.Z$0 = r15;
        r6.Z$1 = r16;
        r6.label = 2;
        Object r42 = DBUtil.getCoroutineContext(r14, r16, r6);
        if (r42 == r7) goto L38;
        r10 = r14;
        r0 = r42;
        r122 = r16;
        r132 = r17;
        goto L36
    L25:
        if (r14.isOpenInternal$room_runtime() == false) goto L32;
        if (r14.inTransaction() == false) goto L32;
        DBUtil__DBUtil_androidKt$performSuspending$lambda$1$$inlined$internalPerform$1 r02 = new DBUtil__DBUtil_androidKt$performSuspending$lambda$1$$inlined$internalPerform$1(r16, r15, r14, null, r17);
        r6.label = 1;
        Object r144 = r14.useConnection(r15, r02, r6);
        if (r144 == r7) goto L38;
        return r144;
    L8:
        r1 = new C01271(r18);
        goto L7
    }

    public static final int readVersion(File r7) {
        FileChannel r1 = new FileInputStream(r7).getChannel();
        ByteBuffer r0 = ByteBuffer.allocate(4);     // Catch: Throwable -> L8
        r1.tryLock(60, 4, true);     // Catch: Throwable -> L8
        r1.position(60);     // Catch: Throwable -> L8
        if (r1.read(r0) != 4) goto L11;
        r0.rewind();     // Catch: Throwable -> L8
        int r72 = r0.getInt();     // Catch: Throwable -> L8
        CloseableKt.closeFinally(r1, null);
        return r72;
    L11:
        throw new IOException("Bad database header, unable to read 4 bytes at offset 60");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);
        throw th;
    }
}
