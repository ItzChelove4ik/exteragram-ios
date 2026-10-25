package kotlin.coroutines;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J)\u0010\u0002\u001a\u0004\u0018\u0001H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0006H¦\u0082\u0004¢\u0006\u0002\u0010\u0007J7\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u0002H\t2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\t0\fH¦\u0080\u0004¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0000H\u0096\u0082\u0004J\u0016\u0010\u0010\u001a\u00020\u00002\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H¦\u0080\u0004¨\u0006\u0013"}, d2 = {"Lkotlin/coroutines/CoroutineContext;", _UrlKt.FRAGMENT_ENCODE_SET, "get", "E", "Lkotlin/coroutines/CoroutineContext$Element;", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "plus", "context", "minusKey", "Key", "Element", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface CoroutineContext {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        public static CoroutineContext $r8$lambda$IMQRUXJxEerD0hyYDf50b5mQ5Cc(CoroutineContext r3, Element r4) {
            CoroutineContext r32 = r3.minusKey(r4.getKey());
            EmptyCoroutineContext r0 = EmptyCoroutineContext.INSTANCE;
            if (r32 != r0) goto L5;
            return r4;
        L5:
            ContinuationInterceptor.Key r1 = ContinuationInterceptor.Key;
            ContinuationInterceptor r2 = (ContinuationInterceptor) r32.get(r1);
            if (r2 == null) goto L8;
            CoroutineContext r33 = r32.minusKey(r1);
            if (r33 != r0) goto L14;
            return new CombinedContext(r4, r2);
        L14:
            return new CombinedContext(new CombinedContext(r33, r4), r2);
        L8:
            return new CombinedContext(r32, r4);
        }

        public static CoroutineContext plus(CoroutineContext r1, CoroutineContext r2) {
            if (r2 != EmptyCoroutineContext.INSTANCE) goto L6;
            return r1;
        L6:
            return (CoroutineContext) r2.fold(r1, new CoroutineContext$DefaultImpls$$ExternalSyntheticLambda0());
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J)\u0010\u0006\u001a\u0004\u0018\u0001H\u0007\"\b\b\u0000\u0010\u0007*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0003H\u0096\u0082\u0004¢\u0006\u0002\u0010\bJ7\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u0002H\n2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\n0\rH\u0096\u0080\u0004¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "get", "E", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "minusKey", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Element extends CoroutineContext {

        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class DefaultImpls {
            public static <R> R fold(Element r0, R r1, Function2<? super R, ? super Element, ? extends R> r2) {
                return r2.invoke(r1, r0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends Element> E get(Element r1, Key<E> r2) {
                if (Intrinsics.areEqual(r1.getKey(), r2) == false) goto L5;
                return r1;
            L5:
                return null;
            }

            public static CoroutineContext minusKey(Element r1, Key<?> r2) {
                if (Intrinsics.areEqual(r1.getKey(), r2) == true) goto L5;
                return r1;
            L5:
                return EmptyCoroutineContext.INSTANCE;
            }

            public static CoroutineContext plus(Element r0, CoroutineContext r1) {
                return DefaultImpls.plus(r0, r1);
            }
        }

        @Override // kotlin.coroutines.CoroutineContext
        <R> R fold(R r1, Function2<? super R, ? super Element, ? extends R> r2);

        @Override // kotlin.coroutines.CoroutineContext
        <E extends Element> E get(Key<E> r1);

        Key<?> getKey();

        @Override // kotlin.coroutines.CoroutineContext
        CoroutineContext minusKey(Key<?> r1);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/coroutines/CoroutineContext$Key;", "E", "Lkotlin/coroutines/CoroutineContext$Element;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Key<E extends Element> {
    }

    <R> R fold(R r1, Function2<? super R, ? super Element, ? extends R> r2);

    <E extends Element> E get(Key<E> r1);

    CoroutineContext minusKey(Key<?> r1);

    CoroutineContext plus(CoroutineContext r1);
}
