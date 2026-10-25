package androidx.datastore.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u0003*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0003¨\u0006\u0004"}, d2 = {"Landroidx/datastore/core/DataMigrationInitializer;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class DataMigrationInitializer<T> {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a3\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\"\u0004\b\u0001\u0010\u00062\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000e0\r¢\u0006\u0002\u0010\u000fJ6\u0010\u0010\u001a\u00020\u000b\"\u0004\b\u0001\u0010\u00062\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000e0\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005H\u0082@¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/datastore/core/DataMigrationInitializer$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "getInitializer", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "T", "Lkotlin/ParameterName;", "name", "api", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "migrations", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/core/DataMigration;", "(Ljava/util/List;)Lkotlin/jvm/functions/Function2;", "runMigrations", "(Ljava/util/List;Landroidx/datastore/core/InitializerApi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDataMigrationInitializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataMigrationInitializer.kt\nandroidx/datastore/core/DataMigrationInitializer$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,72:1\n1855#2,2:73\n1#3:75\n*S KotlinDebug\n*F\n+ 1 DataMigrationInitializer.kt\nandroidx/datastore/core/DataMigrationInitializer$Companion\n*L\n55#1:73,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Object access$runMigrations(Companion r0, List r1, InitializerApi r2, Continuation r3) {
            return r0.runMigrations(r1, r2, r3);
        }

        /* JADX WARN: Type inference failed for: r5v4, types: [T, java.lang.Throwable] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0087 -> B:25:0x006a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x008a -> B:25:0x006a). Please report as a decompilation issue!!! */
        private final <T> Object runMigrations(List<? extends DataMigration<T>> r6, InitializerApi<T> r7, Continuation<? super Unit> r8) {
            if ((r8 instanceof DataMigrationInitializer$Companion$runMigrations$1) == false) goto L7;
            DataMigrationInitializer$Companion$runMigrations$1 r0 = (DataMigrationInitializer$Companion$runMigrations$1) r8;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r5 = r0.result;
            Object r82 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r12 = r0.label;
            if (r12 == 0) goto L20;
            if (r12 == 1) goto L19;
            if (r12 != 2) goto L17;
            Iterator<T> r62 = (Iterator) r0.L$1;
            Ref.ObjectRef r72 = (Ref.ObjectRef) r0.L$0;
            ResultKt.throwOnFailure(r5);     // Catch: Throwable -> L15
        L26:
            if (r62.hasNext() == false) goto L35;
            Function1 r52 = (Function1) r62.next();
            r0.L$0 = r72;     // Catch: Throwable -> L15
            r0.L$1 = r62;     // Catch: Throwable -> L15
            r0.label = 2;     // Catch: Throwable -> L15
            if (r52.invoke(r0) != r82) goto L26;
        L30:
            return r82;
        L35:
            Throwable r53 = (Throwable) r72.element;
            if (r53 == null) goto L38;
            throw r53;
        L38:
            return Unit.INSTANCE;
        L15:
            th = move-exception;
            r5 = (T) th;
            T r13 = r72.element;
            if (r13 != null) goto L34;
            r72.element = r5;
            goto L26
        L34:
            ExceptionsKt.addSuppressed((Throwable) r13, r5);
            goto L26
        L17:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L19:
            List r63 = (List) r0.L$0;
            ResultKt.throwOnFailure(r5);
        L24:
            Ref.ObjectRef r54 = new Ref.ObjectRef();
            r62 = r63.iterator();
            r72 = r54;
            goto L26
        L20:
            ResultKt.throwOnFailure(r5);
            ArrayList r55 = new ArrayList();
            Function2<? super T, ? super Continuation<? super T>, ? extends Object> r14 = new DataMigrationInitializer$Companion$runMigrations$2(r6, r55, null);
            r0.L$0 = r55;
            r0.label = 1;
            if (r7.updateData(r14, r0) == r82) goto L30;
            r63 = r55;
        L7:
            r0 = new DataMigrationInitializer$Companion$runMigrations$1(this, r8);
            goto L8
        }

        public final <T> Function2<InitializerApi<T>, Continuation<? super Unit>, Object> getInitializer(List<? extends DataMigration<T>> r2) {
            return new DataMigrationInitializer$Companion$getInitializer$1(r2, null);
        }
    }

    static {
        Companion = new Companion(null);
    }
}
