package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Landroidx/datastore/core/WriteScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$writeData$2", f = "DataStoreImpl.kt", i = {0}, l = {352, 353}, m = "invokeSuspend", n = {"$this$writeScope"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class DataStoreImpl$writeData$2<T> extends SuspendLambda implements Function2<WriteScope<T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ T $newData;
    final /* synthetic */ Ref.IntRef $newVersion;
    final /* synthetic */ boolean $updateCache;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    public DataStoreImpl$writeData$2(Ref.IntRef r1, DataStoreImpl<T> r2, T r3, boolean r4, Continuation<? super DataStoreImpl$writeData$2> r5) {
        this.$newVersion = r1;
        this.this$0 = r2;
        this.$newData = r3;
        this.$updateCache = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
        DataStoreImpl$writeData$2 r0 = new DataStoreImpl$writeData$2(this.$newVersion, this.this$0, this.$newData, this.$updateCache, r8);
        r0.L$0 = r7;
        return r0;
    }

    public final Object invoke(WriteScope<T> r1, Continuation<? super Unit> r2) {
        return ((DataStoreImpl$writeData$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
        return invoke((WriteScope) r1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L9;
        if (r1 != 2) goto L7;
        ResultKt.throwOnFailure(r8);
    L18:
        if (this.$updateCache == false) goto L25;
        DataStoreInMemoryCache r82 = DataStoreImpl.access$getInMemoryCache$p(this.this$0);
        T r12 = this.$newData;
        if (r12 == null) goto L22;
        int r2 = r12.hashCode();
    L23:
        r82.tryUpdate(new Data(r12, r2, this.$newVersion.element));
        goto L25
    L22:
        r2 = 0;
    L25:
        return Unit.INSTANCE;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        Ref.IntRef r13 = (Ref.IntRef) this.L$1;
        WriteScope r4 = (WriteScope) this.L$0;
        ResultKt.throwOnFailure(r8);
    L14:
        r13.element = ((Number) r8).intValue();
        T r83 = this.$newData;
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (r4.writeData(r83, this) != r0) goto L18;
    L16:
        return r0;
    L10:
        ResultKt.throwOnFailure(r8);
        WriteScope r84 = (WriteScope) this.L$0;
        r13 = this.$newVersion;
        InterProcessCoordinator r5 = DataStoreImpl.access$getCoordinator(this.this$0);
        this.L$0 = r84;
        this.L$1 = r13;
        this.label = 1;
        Object r42 = r5.incrementAndGetVersion(this);
        if (r42 == r0) goto L16;
        r4 = r84;
        r8 = r42;
        goto L14
    }
}
