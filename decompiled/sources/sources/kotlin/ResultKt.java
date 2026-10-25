package kotlin;

import kotlin.Result;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0081\u0080\u0004\u001a\u0017\u0010\u0004\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u0006H\u0081\u0080\u0004¢\u0006\u0002\u0010\u0007\u001a,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\f\u001aA\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\t*\u0002H\r2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\t0\u000e¢\u0006\u0002\b\u000fH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001f\u0010\u0011\u001a\u0002H\r\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0006H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0012\u001a\\\u0010\u0013\u001a\u0002H\t\"\u0004\b\u0000\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0010\u001a1\u0010\u0017\u001a\u0002H\t\"\u0004\b\u0000\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062\u0006\u0010\u0018\u001a\u0002H\tH\u0087\u0088\u0004¢\u0006\u0002\u0010\u0019\u001a\u0085\u0001\u0010\u001a\u001a\u0002H\t\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\t0\u000e2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0002\u0010\u001d\u001a^\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0010\u001aQ\u0010 \u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001ab\u0010!\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b\u0000\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0010\u001aU\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b\u0000\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001aX\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010#\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u00050\u000eH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0010\u001aX\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010#\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00050\u000eH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"createFailure", _UrlKt.FRAGMENT_ENCODE_SET, "exception", _UrlKt.FRAGMENT_ENCODE_SET, "throwOnFailure", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "runCatching", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "T", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "onFailure", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "fold", "onSuccess", "value", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "map", "transform", "mapCatching", "recover", "recoverCatching", "action", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Result.kt\nkotlin/ResultKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,342:1\n1#2:343\n*E\n"})
/* loaded from: classes.dex */
public final class ResultKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final Object createFailure(Throwable r1) {
        return new Result.Failure(r1);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> R fold(Object r1, Function1<? super T, ? extends R> r2, Function1<? super Throwable, ? extends R> r3) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r1);
        if (r0 != null) goto L7;
        return r2.invoke(r1);
    L7:
        return r3.invoke(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> R getOrDefault(Object r1, R r2) {
        if (Result.m2024isFailureimpl(r1) == false) goto L5;
        return r2;
    L5:
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> R getOrElse(Object r1, Function1<? super Throwable, ? extends R> r2) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r1);
        if (r0 != null) goto L6;
        return r1;
    L6:
        return r2.invoke(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> T getOrThrow(Object r0) {
        throwOnFailure(r0);
        return r0;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> Object map(Object r1, Function1<? super T, ? extends R> r2) {
        if (Result.m2025isSuccessimpl(r1) == false) goto L7;
        return Result.m2018constructorimpl(r2.invoke(r1));
    L7:
        return Result.m2018constructorimpl(r1);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> Object mapCatching(Object r1, Function1<? super T, ? extends R> r2) {
        if (Result.m2025isSuccessimpl(r1) == false) goto L10;
        return Result.m2018constructorimpl(r2.invoke(r1));
    L6:
        th = move-exception;
        Result.Companion r22 = Result.Companion;
        return Result.m2018constructorimpl(createFailure(th));
    L10:
        return Result.m2018constructorimpl(r1);
    }

    @SinceKotlin(version = "1.3")
    @IgnorableReturnValue
    @InlineOnly
    private static final <T> Object onFailure(Object r1, Function1<? super Throwable, Unit> r2) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r1);
        if (r0 == null) goto L5;
        r2.invoke(r0);
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.3")
    @IgnorableReturnValue
    @InlineOnly
    private static final <T> Object onSuccess(Object r1, Function1<? super T, Unit> r2) {
        if (Result.m2025isSuccessimpl(r1) == false) goto L5;
        r2.invoke(r1);
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> Object recover(Object r1, Function1<? super Throwable, ? extends R> r2) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r1);
        if (r0 != null) goto L6;
        return r1;
    L6:
        return Result.m2018constructorimpl(r2.invoke(r0));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> Object recoverCatching(Object r1, Function1<? super Throwable, ? extends R> r2) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r1);
        if (r0 != null) goto L10;
        return r1;
    L10:
        return Result.m2018constructorimpl(r2.invoke(r0));
    L7:
        th = move-exception;
        Result.Companion r22 = Result.Companion;
        return Result.m2018constructorimpl(createFailure(th));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T, R> Object runCatching(T r1, Function1<? super T, ? extends R> r2) {
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L4
        return Result.m2018constructorimpl(r2.invoke(r1));
    L4:
        th = move-exception;
        Result.Companion r22 = Result.Companion;
        return Result.m2018constructorimpl(createFailure(th));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R> Object runCatching(Function0<? extends R> r1) {
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L4
        return Result.m2018constructorimpl(r1.invoke());
    L4:
        th = move-exception;
        Result.Companion r02 = Result.Companion;
        return Result.m2018constructorimpl(createFailure(th));
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final void throwOnFailure(Object r1) {
        if ((r1 instanceof Result.Failure) == true) goto L6;
        return;
    L6:
        throw ((Result.Failure) r1).exception;
    }
}
