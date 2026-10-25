package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* synthetic */ class BufferedChannel$onReceive$2 extends FunctionReferenceImpl implements Function3<BufferedChannel<?>, Object, Object, Object> {
    public static final BufferedChannel$onReceive$2 INSTANCE = null;

    static {
        INSTANCE = new BufferedChannel$onReceive$2();
    }

    public BufferedChannel$onReceive$2() {
        super(3, BufferedChannel.class, "processResultSelectReceive", "processResultSelectReceive(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(BufferedChannel<?> r1, Object r2, Object r3) {
        return invoke2(r1, r2, r3);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(BufferedChannel<?> r1, Object r2, Object r3) {
        return BufferedChannel.access$processResultSelectReceive(r1, r2, r3);
    }
}
