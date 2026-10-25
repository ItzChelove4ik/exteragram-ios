package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TasksKt$awaitImpl$2$2 implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellationTokenSource $cancellationTokenSource;

    public TasksKt$awaitImpl$2$2(CancellationTokenSource r1) {
        this.$cancellationTokenSource = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r1) {
        this.$cancellationTokenSource.cancel();
    }
}
