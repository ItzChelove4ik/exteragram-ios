package androidx.room.coroutines;

import androidx.room.Transactor;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001bB[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012B\b\u0002\u0010\u0006\u001a<\b\u0001\u0012\u0018\u0012\u0016\b\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007j\b\u0012\u0002\b\u0003\u0018\u0001`\u000b¢\u0006\u0004\b\f\u0010\rJ@\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\"\u0010\u0016\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0096@¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000RJ\u0010\u0006\u001a<\b\u0001\u0012\u0018\u0012\u0016\b\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007j\b\u0012\u0002\b\u0003\u0018\u0001`\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/room/coroutines/PassthroughConnectionPool;", "Landroidx/room/coroutines/ConnectionPool;", "driver", "Landroidx/sqlite/SQLiteDriver;", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "transactionWrapper", "Lkotlin/Function2;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/coroutines/TransactionWrapper;", "<init>", "(Landroidx/sqlite/SQLiteDriver;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "connection", "Lkotlin/Lazy;", "Landroidx/sqlite/SQLiteConnection;", "useConnection", "R", "isReadOnly", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Landroidx/room/Transactor;", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "ConnectionElement", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PassthroughConnectionPool implements ConnectionPool {
    private final Lazy<SQLiteConnection> connection;
    private final SQLiteDriver driver;
    private final String fileName;
    private final Function2<Function1<? super Continuation<Object>, ? extends Object>, Continuation<Object>, Object> transactionWrapper;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/room/coroutines/PassthroughConnectionPool$ConnectionElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "connectionWrapper", "Landroidx/room/coroutines/PassthroughConnection;", "<init>", "(Landroidx/room/coroutines/PassthroughConnection;)V", "getConnectionWrapper", "()Landroidx/room/coroutines/PassthroughConnection;", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "Key", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ConnectionElement implements CoroutineContext.Element {
        public static final Key Key = null;
        private final PassthroughConnection connectionWrapper;

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/room/coroutines/PassthroughConnectionPool$ConnectionElement$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Landroidx/room/coroutines/PassthroughConnectionPool$ConnectionElement;", "<init>", "()V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Key implements CoroutineContext.Key<ConnectionElement> {
            private Key() {
            }

            public /* synthetic */ Key(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Key = new Key(null);
        }

        public ConnectionElement(PassthroughConnection r1) {
            this.connectionWrapper = r1;
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public <R> R fold(R r1, Function2<? super R, ? super CoroutineContext.Element, ? extends R> r2) {
            return (R) CoroutineContext.Element.DefaultImpls.fold(this, r1, r2);
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> r1) {
            return (E) CoroutineContext.Element.DefaultImpls.get(this, r1);
        }

        public final PassthroughConnection getConnectionWrapper() {
            return this.connectionWrapper;
        }

        @Override // kotlin.coroutines.CoroutineContext.Element
        public CoroutineContext.Key<ConnectionElement> getKey() {
            return Key;
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public CoroutineContext minusKey(CoroutineContext.Key<?> r1) {
            return CoroutineContext.Element.DefaultImpls.minusKey(this, r1);
        }

        @Override // kotlin.coroutines.CoroutineContext
        public CoroutineContext plus(CoroutineContext r1) {
            return CoroutineContext.Element.DefaultImpls.plus(this, r1);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PassthroughConnectionPool$useConnection$2", f = "PassthroughConnectionPool.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.coroutines.PassthroughConnectionPool$useConnection$2, reason: invalid class name */
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ PassthroughConnection $connectionWrapper;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r1, PassthroughConnection r2, Continuation<? super AnonymousClass2> r3) {
            this.$block = r1;
            this.$connectionWrapper = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass2(this.$block, this.$connectionWrapper, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super R> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            Function2<Transactor, Continuation<? super R>, Object> r42 = this.$block;
            PassthroughConnection r12 = this.$connectionWrapper;
            this.label = 1;
            Object r3 = r42.invoke(r12, this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    public static SQLiteConnection $r8$lambda$5PfN97KPsXXXOapSC9IzcedL70w(PassthroughConnectionPool r1) {
        return r1.driver.open(r1.fileName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PassthroughConnectionPool(SQLiteDriver r1, String r2, Function2<? super Function1<? super Continuation<Object>, ? extends Object>, ? super Continuation<Object>, ? extends Object> r3) {
        this.driver = r1;
        this.fileName = r2;
        this.transactionWrapper = r3;
        this.connection = LazyKt.lazy(new PassthroughConnectionPool$$ExternalSyntheticLambda0(this));
    }

    @Override // androidx.room.coroutines.ConnectionPool, java.lang.AutoCloseable
    public void close() {
        if (this.connection.isInitialized() == false) goto L6;
        this.connection.getValue().close();
        return;
    }

    @Override // androidx.room.coroutines.ConnectionPool
    public <R> Object useConnection(boolean r3, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r4, Continuation<? super R> r5) {
        ConnectionElement r32 = (ConnectionElement) r5.getContext().get(ConnectionElement.Key);
        if (r32 == null) goto L5;
        PassthroughConnection r33 = r32.getConnectionWrapper();
    L6:
        if (r33 != null) goto L8;
        PassthroughConnection r34 = new PassthroughConnection(this.transactionWrapper, this.connection.getValue());
        return BuildersKt.withContext(new ConnectionElement(r34), new AnonymousClass2(r4, r34, null), r5);
    L8:
        return r4.invoke(r33, r5);
    L5:
        r33 = null;
        goto L6
    }
}
