package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001b\u0010\u0003\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0003\u0010\u0006\u001a\u0013\u0010\b\u001a\u00020\u0007*\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\r\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a/\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001a\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012*\u00020\u0015H\u0080\u0010¢\u0006\u0004\b\u0016\u0010\u0017\"\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u0018*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "context", "newCoroutineContext", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;", "addedContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;", _UrlKt.FRAGMENT_ENCODE_SET, "hasCopyableElements", "(Lkotlin/coroutines/CoroutineContext;)Z", "originalContext", "appendContext", "isNewCoroutine", "foldCopies", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Z)Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "oldValue", "Lkotlinx/coroutines/UndispatchedCoroutine;", "updateUndispatchedCompletion", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)Lkotlinx/coroutines/UndispatchedCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "undispatchedCompletion", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlinx/coroutines/UndispatchedCoroutine;", _UrlKt.FRAGMENT_ENCODE_SET, "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "coroutineName", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class CoroutineContextKt {
    /* renamed from: $r8$lambda$5QiE6d6QaSqAyu-oNAUir6D3p20, reason: not valid java name */
    public static CoroutineContext m3521$r8$lambda$5QiE6d6QaSqAyuoNAUir6D3p20(CoroutineContext r0, CoroutineContext.Element r1) {
        return r0.plus(r1);
    }

    public static boolean $r8$lambda$as3AdS3orsv2Ts0OahuRhLd1z7g(boolean r0, CoroutineContext.Element r1) {
        return r0;
    }

    public static CoroutineContext $r8$lambda$cAutOdCeS_RpPuLeN9mZ0Ij5g4E(Ref.ObjectRef r0, boolean r1, CoroutineContext r2, CoroutineContext.Element r3) {
        return r2.plus(r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.Object] */
    private static final CoroutineContext foldCopies(CoroutineContext r3, CoroutineContext r4, boolean r5) {
        boolean r0 = hasCopyableElements(r3);
        boolean r1 = hasCopyableElements(r4);
        if (r0 == true) goto L7;
        if (r1 == true) goto L7;
        return r3.plus(r4);
    L7:
        Ref.ObjectRef r02 = new Ref.ObjectRef();
        r02.element = r4;
        EmptyCoroutineContext r42 = EmptyCoroutineContext.INSTANCE;
        CoroutineContext r32 = (CoroutineContext) r3.fold(r42, new CoroutineContextKt$$ExternalSyntheticLambda1(r02, r5));
        if (r1 == false) goto L11;
        r02.element = ((CoroutineContext) r02.element).fold(r42, new CoroutineContextKt$$ExternalSyntheticLambda2());
    L11:
        return r32.plus((CoroutineContext) r02.element);
    }

    public static final String getCoroutineName(CoroutineContext r0) {
        return null;
    }

    private static final boolean hasCopyableElements(CoroutineContext r2) {
        return ((Boolean) r2.fold(Boolean.FALSE, new CoroutineContextKt$$ExternalSyntheticLambda0())).booleanValue();
    }

    public static final CoroutineContext newCoroutineContext(CoroutineContext r1, CoroutineContext r2) {
        if (hasCopyableElements(r2) == true) goto L7;
        return r1.plus(r2);
    L7:
        return foldCopies(r1, r2, false);
    }

    public static final CoroutineContext newCoroutineContext(CoroutineScope r1, CoroutineContext r2) {
        CoroutineContext r12 = foldCopies(r1.getCoroutineContext(), r2, true);
        if (r12 != Dispatchers.getDefault()) goto L5;
        return r12;
    L5:
        if (r12.get(ContinuationInterceptor.Key) == null) goto L7;
        return r12;
    L7:
        return r12.plus(Dispatchers.getDefault());
    }

    public static final UndispatchedCoroutine<?> undispatchedCompletion(CoroutineStackFrame r2) {
    L3:
        if ((r2 instanceof DispatchedCoroutine) == true) goto L4;
        r2 = r2.getCallerFrame();
        if (r2 == null) goto L7;
        if ((r2 instanceof UndispatchedCoroutine) == false) goto L3;
        return (UndispatchedCoroutine) r2;
    L7:
        return null;
    L4:
        return null;
    }

    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(Continuation<?> r2, CoroutineContext r3, Object r4) {
        if ((r2 instanceof CoroutineStackFrame) == true) goto L6;
        return null;
    L6:
        if (r3.get(UndispatchedMarker.INSTANCE) == null) goto L11;
        UndispatchedCoroutine<?> r22 = undispatchedCompletion((CoroutineStackFrame) r2);
        if (r22 == null) goto L10;
        r22.saveThreadContext(r3, r4);
    L10:
        return r22;
    L11:
        return null;
    }
}
