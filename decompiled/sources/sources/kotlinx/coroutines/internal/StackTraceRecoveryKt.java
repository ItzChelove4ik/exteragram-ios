package kotlinx.coroutines.internal;

import _COROUTINE.ArtificialStackFrames;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0003\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007\"\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u001c\u0010\f\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u000b*\f\b\u0000\u0010\u000e\"\u00020\r2\u00020\r*\f\b\u0000\u0010\u000f\"\u00020\u00052\u00020\u0005¨\u0006\u0010"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "E", "exception", "recoverStackTrace", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Ljava/lang/StackTraceElement;", "ARTIFICIAL_FRAME", "Ljava/lang/StackTraceElement;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "baseContinuationImplClassName", "Ljava/lang/String;", "stackTraceRecoveryClassName", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "CoroutineStackFrame", "StackTraceElement", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStackTraceRecovery.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,210:1\n1880#2,6:211\n12970#2,2:219\n1772#2,6:221\n12970#2,2:227\n1772#2,6:230\n37#3,2:217\n1#4:229\n*S KotlinDebug\n*F\n+ 1 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n39#1:211,6\n127#1:219,2\n137#1:221,6\n169#1:227,2\n190#1:230,6\n102#1:217,2\n*E\n"})
/* loaded from: classes5.dex */
public abstract class StackTraceRecoveryKt {
    private static final StackTraceElement ARTIFICIAL_FRAME = null;
    private static final String baseContinuationImplClassName = null;
    private static final String stackTraceRecoveryClassName = null;

    static {
        ARTIFICIAL_FRAME = new ArtificialStackFrames().coroutineBoundary();
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L5
        Object r02 = Result.m2018constructorimpl(BaseContinuationImpl.class.getCanonicalName());     // Catch: Throwable -> L5
    L8:
        if (Result.m2021exceptionOrNullimpl(r02) == null) goto L11;
        r02 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
    L11:
        baseContinuationImplClassName = (String) r02;
        Object r03 = Result.m2018constructorimpl(StackTraceRecoveryKt.class.getCanonicalName());     // Catch: Throwable -> L14
    L17:
        if (Result.m2021exceptionOrNullimpl(r03) == null) goto L20;
        r03 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
    L20:
        stackTraceRecoveryClassName = (String) r03;
        return;
    L14:
        th = move-exception;
        Result.Companion r1 = Result.Companion;
        r03 = Result.m2018constructorimpl(ResultKt.createFailure(th));
    L5:
        th = move-exception;
        Result.Companion r12 = Result.Companion;
        r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L8
    }

    public static final <E extends Throwable> E recoverStackTrace(E r0) {
        return r0;
    }
}
