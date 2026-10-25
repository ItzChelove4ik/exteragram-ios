package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aj\u0010\r\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00010\u00002O\u0010\u000b\u001aK\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\"c\u0010\u0018\u001aQ\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002j\u0002`\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019\"\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c\"\u0014\u0010\u001d\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001c\"\u0014\u0010\u001e\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001c\"\u0014\u0010\u001f\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u001c\"\u001a\u0010 \u001a\u00020\u001a8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010\u001c\u001a\u0004\b!\u0010\"*¢\u0001\b\u0007\u0010%\"M\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0014\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030#¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00010\u00022M\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0014\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030#¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00010\u0002*¢\u0001\b\u0007\u0010&\"M\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00022M\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002*Ú\u0001\b\u0000\u0010(\"i\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030#¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b('\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00020\u00022i\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030#¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b('\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00020\u0002¨\u0006)"}, d2 = {"Lkotlinx/coroutines/CancellableContinuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Lkotlin/coroutines/CoroutineContext;", "context", "onCancellation", _UrlKt.FRAGMENT_ENCODE_SET, "tryResume", "(Lkotlinx/coroutines/CancellableContinuation;Lkotlin/jvm/functions/Function3;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "trySelectInternalResult", "Lkotlinx/coroutines/selects/TrySelectDetailedResult;", "TrySelectDetailedResult", "(I)Lkotlinx/coroutines/selects/TrySelectDetailedResult;", "clauseObject", "param", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "DUMMY_PROCESS_RESULT_FUNCTION", "Lkotlin/jvm/functions/Function3;", "Lkotlinx/coroutines/internal/Symbol;", "STATE_REG", "Lkotlinx/coroutines/internal/Symbol;", "STATE_COMPLETED", "STATE_CANCELLED", "NO_RESULT", "PARAM_CLAUSE_0", "getPARAM_CLAUSE_0", "()Lkotlinx/coroutines/internal/Symbol;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "RegistrationFunction", "ProcessResultFunction", "internalResult", "OnCancellationConstructor", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class SelectKt {
    private static final Function3<Object, Object, Object, Object> DUMMY_PROCESS_RESULT_FUNCTION = null;
    private static final Symbol NO_RESULT = null;
    private static final Symbol PARAM_CLAUSE_0 = null;
    private static final Symbol STATE_CANCELLED = null;
    private static final Symbol STATE_COMPLETED = null;
    private static final Symbol STATE_REG = null;

    static {
        DUMMY_PROCESS_RESULT_FUNCTION = SelectKt$DUMMY_PROCESS_RESULT_FUNCTION$1.INSTANCE;
        STATE_REG = new Symbol("STATE_REG");
        STATE_COMPLETED = new Symbol("STATE_COMPLETED");
        STATE_CANCELLED = new Symbol("STATE_CANCELLED");
        NO_RESULT = new Symbol("NO_RESULT");
        PARAM_CLAUSE_0 = new Symbol("PARAM_CLAUSE_0");
    }

    private static final TrySelectDetailedResult TrySelectDetailedResult(int r3) {
        if (r3 == 0) goto L18;
        if (r3 == 1) goto L16;
        if (r3 == 2) goto L14;
        if (r3 != 3) goto L12;
        return TrySelectDetailedResult.ALREADY_SELECTED;
    L12:
        throw new IllegalStateException(("Unexpected internal result: " + r3).toString());
    L14:
        return TrySelectDetailedResult.CANCELLED;
    L16:
        return TrySelectDetailedResult.REREGISTER;
    L18:
        return TrySelectDetailedResult.SUCCESSFUL;
    }

    public static final /* synthetic */ TrySelectDetailedResult access$TrySelectDetailedResult(int r0) {
        return TrySelectDetailedResult(r0);
    }

    public static final /* synthetic */ Function3 access$getDUMMY_PROCESS_RESULT_FUNCTION$p() {
        return DUMMY_PROCESS_RESULT_FUNCTION;
    }

    public static final /* synthetic */ Symbol access$getNO_RESULT$p() {
        return NO_RESULT;
    }

    public static final /* synthetic */ Symbol access$getSTATE_CANCELLED$p() {
        return STATE_CANCELLED;
    }

    public static final /* synthetic */ Symbol access$getSTATE_COMPLETED$p() {
        return STATE_COMPLETED;
    }

    public static final /* synthetic */ Symbol access$getSTATE_REG$p() {
        return STATE_REG;
    }

    public static final /* synthetic */ boolean access$tryResume(CancellableContinuation r0, Function3 r1) {
        return tryResume(r0, r1);
    }

    public static final Symbol getPARAM_CLAUSE_0() {
        return PARAM_CLAUSE_0;
    }

    private static final boolean tryResume(CancellableContinuation<? super Unit> r2, Function3<? super Throwable, Object, ? super CoroutineContext, Unit> r3) {
        Object r32 = r2.tryResume(Unit.INSTANCE, null, r3);
        if (r32 != null) goto L6;
        return false;
    L6:
        r2.completeResume(r32);
        return true;
    }
}
