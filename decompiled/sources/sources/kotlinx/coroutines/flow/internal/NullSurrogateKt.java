package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"NULL", "Lkotlinx/coroutines/internal/Symbol;", "UNINITIALIZED", "DONE", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class NullSurrogateKt {

    @JvmField
    public static final Symbol DONE = null;

    @JvmField
    public static final Symbol NULL = null;

    @JvmField
    public static final Symbol UNINITIALIZED = null;

    static {
        NULL = new Symbol("NULL");
        UNINITIALIZED = new Symbol("UNINITIALIZED");
        DONE = new Symbol("DONE");
    }
}
