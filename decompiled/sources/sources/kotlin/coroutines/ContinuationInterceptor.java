package kotlin.coroutines;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ$\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H¦\u0080\u0004J\u0016\u0010\u0006\u001a\u00020\u00072\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0096\u0080\u0004J)\u0010\b\u001a\u0004\u0018\u0001H\t\"\b\b\u0000\u0010\t*\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0096\u0082\u0004¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/CoroutineContext$Element;", "interceptContinuation", "Lkotlin/coroutines/Continuation;", "T", "continuation", "releaseInterceptedContinuation", _UrlKt.FRAGMENT_ENCODE_SET, "get", "E", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "Key", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ContinuationInterceptor extends CoroutineContext.Element {
    public static final Key Key = null;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R> R fold(ContinuationInterceptor r0, R r1, Function2<? super R, ? super CoroutineContext.Element, ? extends R> r2) {
            return (R) CoroutineContext.Element.DefaultImpls.fold(r0, r1, r2);
        }

        public static <E extends CoroutineContext.Element> E get(ContinuationInterceptor r2, CoroutineContext.Key<E> r3) {
            if ((r3 instanceof AbstractCoroutineContextKey) == false) goto L11;
            AbstractCoroutineContextKey r32 = (AbstractCoroutineContextKey) r3;
            if (r32.isSubKey$kotlin_stdlib(r2.getKey()) == false) goto L9;
            E r22 = (E) r32.tryCast$kotlin_stdlib(r2);
            if (r22 == null) goto L9;
            return r22;
        L9:
            return null;
        L11:
            if (ContinuationInterceptor.Key != r3) goto L13;
            return r2;
        L13:
            return null;
        }

        public static CoroutineContext minusKey(ContinuationInterceptor r1, CoroutineContext.Key<?> r2) {
            if ((r2 instanceof AbstractCoroutineContextKey) == false) goto L11;
            AbstractCoroutineContextKey r22 = (AbstractCoroutineContextKey) r2;
            if (r22.isSubKey$kotlin_stdlib(r1.getKey()) == true) goto L7;
            return r1;
        L7:
            if (r22.tryCast$kotlin_stdlib(r1) != null) goto L9;
            return r1;
        L9:
            return EmptyCoroutineContext.INSTANCE;
        L11:
            if (ContinuationInterceptor.Key == r2) goto L13;
            return r1;
        L13:
            return EmptyCoroutineContext.INSTANCE;
        }

        public static CoroutineContext plus(ContinuationInterceptor r0, CoroutineContext r1) {
            return CoroutineContext.Element.DefaultImpls.plus(r0, r1);
        }

        public static void releaseInterceptedContinuation(ContinuationInterceptor r0, Continuation<?> r1) {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/ContinuationInterceptor$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/ContinuationInterceptor;", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Key implements CoroutineContext.Key<ContinuationInterceptor> {
        static final /* synthetic */ Key $$INSTANCE = null;

        static {
            $$INSTANCE = new Key();
        }

        private Key() {
        }
    }

    static {
        Key = Key.$$INSTANCE;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> r1);

    <T> Continuation<T> interceptContinuation(Continuation<? super T> r1);

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    CoroutineContext minusKey(CoroutineContext.Key<?> r1);

    void releaseInterceptedContinuation(Continuation<?> r1);
}
