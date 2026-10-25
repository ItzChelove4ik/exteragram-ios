package kotlinx.atomicfu;

import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.TraceBase;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR \u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010\u000f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lkotlinx/atomicfu/AtomicBoolean;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, RegisterSpec.PREFIX, "Lkotlinx/atomicfu/TraceBase;", "trace", "<init>", "(ZLkotlinx/atomicfu/TraceBase;)V", "expect", PluginsConstants.UPDATE, "compareAndSet", "(ZZ)Z", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lkotlinx/atomicfu/TraceBase;", "getTrace", "()Lkotlinx/atomicfu/TraceBase;", "getTrace$annotations", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "_value", "I", "value", "getValue", "()Z", "setValue", "(Z)V", "Companion", "atomicfu"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAtomicFU.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AtomicFU.kt\nkotlinx/atomicfu/AtomicBoolean\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,469:1\n1#2:470\n*E\n"})
/* loaded from: classes5.dex */
public final class AtomicBoolean {
    private static final Companion Companion = null;
    private static final AtomicIntegerFieldUpdater<AtomicBoolean> FU = null;
    private volatile int _value;
    private final TraceBase trace;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R4\u0010\u0004\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/atomicfu/AtomicBoolean$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "FU", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lkotlinx/atomicfu/AtomicBoolean;", "kotlin.jvm.PlatformType", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "atomicfu"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "_value");
    }

    public AtomicBoolean(boolean r1, TraceBase r2) {
        this.trace = r2;
        this._value = r1 ? 1 : 0;
    }

    public final boolean compareAndSet(boolean r4, boolean r5) {
        boolean r0 = FU.compareAndSet(this, r4 ? 1 : 0, r5 ? 1 : 0);
        if (r0 == false) goto L7;
        TraceBase r3 = this.trace;
        if (r3 == TraceBase.None.INSTANCE) goto L7;
        r3.append("CAS(" + r4 + ", " + r5 + ')');
    L7:
        return r0;
    }

    public final boolean getValue() {
        if (this._value == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void setValue(boolean r3) {
        this._value = r3 ? 1 : 0;
        TraceBase r2 = this.trace;
        if (r2 == TraceBase.None.INSTANCE) goto L6;
        r2.append("set(" + r3 + ')');
        return;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
