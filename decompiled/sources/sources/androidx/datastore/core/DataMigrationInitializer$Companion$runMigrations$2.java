package androidx.datastore.core;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H\u008a@"}, d2 = {"<anonymous>", "T", "startingData"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2", f = "DataMigrationInitializer.kt", i = {0, 0}, l = {44, 46}, m = "invokeSuspend", n = {"migration", "data"}, s = {"L$2", "L$3"})
@SourceDebugExtension({"SMAP\nDataMigrationInitializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataMigrationInitializer.kt\nandroidx/datastore/core/DataMigrationInitializer$Companion$runMigrations$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,72:1\n1789#2,3:73\n*S KotlinDebug\n*F\n+ 1 DataMigrationInitializer.kt\nandroidx/datastore/core/DataMigrationInitializer$Companion$runMigrations$2\n*L\n43#1:73,3\n*E\n"})
/* loaded from: classes.dex */
public final class DataMigrationInitializer$Companion$runMigrations$2<T> extends SuspendLambda implements Function2<T, Continuation<? super T>, Object> {
    final /* synthetic */ List<Function1<Continuation<? super Unit>, Object>> $cleanUps;
    final /* synthetic */ List<DataMigration<T>> $migrations;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: Multi-variable type inference failed */
    public DataMigrationInitializer$Companion$runMigrations$2(List<? extends DataMigration<T>> r1, List<Function1<Continuation<? super Unit>, Object>> r2, Continuation<? super DataMigrationInitializer$Companion$runMigrations$2> r3) {
        this.$migrations = r1;
        this.$cleanUps = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        DataMigrationInitializer$Companion$runMigrations$2 r0 = new DataMigrationInitializer$Companion$runMigrations$2(this.$migrations, this.$cleanUps, r4);
        r0.L$0 = r3;
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return invoke(r1, (Continuation) r2);
    }

    public final Object invoke(T r1, Continuation<? super T> r2) {
        return ((DataMigrationInitializer$Companion$runMigrations$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L9;
        if (r1 != 2) goto L7;
        Iterator<T> r12 = (Iterator) this.L$1;
        List<Function1<Continuation<? super Unit>, Object>> r5 = (List) this.L$0;
        ResultKt.throwOnFailure(r10);
    L12:
        if (r12.hasNext() == false) goto L24;
        DataMigration r6 = (DataMigration) r12.next();
        this.L$0 = r5;
        this.L$1 = r12;
        this.L$2 = r6;
        this.L$3 = r10;
        this.label = 1;
        Object r7 = r6.shouldMigrate(r10, this);
        if (r7 == r0) goto L21;
        Iterator<T> r8 = r12;
        Object r13 = r10;
        r10 = r7;
        DataMigration r72 = r6;
        Iterator<T> r62 = r8;
    L18:
        if (((Boolean) r10).booleanValue() == false) goto L23;
        r5.add(new DataMigrationInitializer$Companion$runMigrations$2$1$1(r72, null));
        this.L$0 = r5;
        this.L$1 = r62;
        this.L$2 = null;
        this.L$3 = null;
        this.label = 2;
        r10 = r72.migrate(r13, this);
        if (r10 == r0) goto L21;
        r12 = r62;
        goto L12
    L23:
        r10 = r13;
        r12 = r62;
    L21:
        return r0;
    L24:
        return r10;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        r13 = this.L$3;
        DataMigration r52 = (DataMigration) this.L$2;
        r62 = (Iterator) this.L$1;
        List<Function1<Continuation<? super Unit>, Object>> r73 = (List) this.L$0;
        ResultKt.throwOnFailure(r10);
        r72 = r52;
        r5 = r73;
        goto L18
    L10:
        ResultKt.throwOnFailure(r10);
        r10 = this.L$0;
        List<DataMigration<T>> r14 = this.$migrations;
        r5 = this.$cleanUps;
        r12 = r14.iterator();
        goto L12
    }
}
