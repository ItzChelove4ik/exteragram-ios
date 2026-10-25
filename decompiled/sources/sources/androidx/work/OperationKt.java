package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Operation;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/work/Tracer;", "tracer", _UrlKt.FRAGMENT_ENCODE_SET, "label", "Ljava/util/concurrent/Executor;", "executor", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Landroidx/work/Operation;", "launchOperation", "(Landroidx/work/Tracer;Ljava/lang/String;Ljava/util/concurrent/Executor;Lkotlin/jvm/functions/Function0;)Landroidx/work/Operation;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOperation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Operation.kt\nandroidx/work/OperationKt\n+ 2 Tracer.kt\nandroidx/work/TracerKt\n*L\n1#1,71:1\n53#2,9:72\n*S KotlinDebug\n*F\n+ 1 Operation.kt\nandroidx/work/OperationKt\n*L\n48#1:72,9\n*E\n"})
/* loaded from: classes.dex */
public abstract class OperationKt {
    /* renamed from: $r8$lambda$4AmAQmnwY87AwH_dAIVR-wuDub0, reason: not valid java name */
    public static Unit m970$r8$lambda$4AmAQmnwY87AwH_dAIVRwuDub0(Executor r6, Tracer r7, String r8, Function0 r9, MutableLiveData r10, CallbackToFutureAdapter.Completer r11) {
        r6.execute(new OperationKt$$ExternalSyntheticLambda1(r7, r8, r9, r10, r11));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$XKAkIiEN7OgIvwuLUZRQpJhjmyE(Tracer r1, String r2, Function0 r3, MutableLiveData r4, CallbackToFutureAdapter.Completer r5) {
        boolean r0 = r1.isEnabled();
        if (r0 == true) goto L21;
    L19:
        r3.invoke();     // Catch: Throwable -> L10
        Operation.State.SUCCESS r22 = Operation.SUCCESS;     // Catch: Throwable -> L10
        r4.postValue(r22);     // Catch: Throwable -> L10
        r5.set(r22);     // Catch: Throwable -> L10
    L12:
        Unit r23 = Unit.INSTANCE;     // Catch: Throwable -> L6
        if (r0 == false) goto L23;
        r1.endSection();
        return;
    L23:
        return;
    L10:
        th = move-exception;
        r4.postValue(new Operation.State.FAILURE(th));     // Catch: Throwable -> L6
        r5.setException(th);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        if (r0 == false) goto L18;
        r1.endSection();
    L18:
        throw th;
    L21:
        r1.beginSection(r2);     // Catch: Throwable -> L6
        goto L19
    }

    public static final Operation launchOperation(Tracer r6, String r7, Executor r8, Function0<Unit> r9) {
        MutableLiveData r5 = new MutableLiveData(Operation.IN_PROGRESS);
        return new OperationImpl(r5, CallbackToFutureAdapter.getFuture(new OperationKt$$ExternalSyntheticLambda0(r8, r6, r7, r9, r5)));
    }
}
