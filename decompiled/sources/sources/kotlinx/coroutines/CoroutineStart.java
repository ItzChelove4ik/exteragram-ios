package kotlinx.coroutines;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J[\u0010\u000e\u001a\u00020\r\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006¢\u0006\u0002\b\t2\u0006\u0010\u000b\u001a\u00028\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H\u0087\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CoroutineStart;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "R", "T", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "receiver", "completion", _UrlKt.FRAGMENT_ENCODE_SET, "invoke", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", _UrlKt.FRAGMENT_ENCODE_SET, "isLazy", "()Z", "isLazy$annotations", "()V", PluginsConstants.Strategy.DEFAULT, "LAZY", "ATOMIC", "UNDISPATCHED", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum CoroutineStart extends Enum<CoroutineStart> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ CoroutineStart[] $VALUES = null;
    public static final CoroutineStart ATOMIC = null;
    public static final CoroutineStart DEFAULT = null;
    public static final CoroutineStart LAZY = null;
    public static final CoroutineStart UNDISPATCHED = null;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[CoroutineStart.values().length];
            r0[CoroutineStart.DEFAULT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L13:
            r0[CoroutineStart.ATOMIC.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L19:
            r0[CoroutineStart.UNDISPATCHED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L15:
            r0[CoroutineStart.LAZY.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L7:
            $EnumSwitchMapping$0 = r0;
        }
    }

    private static final /* synthetic */ CoroutineStart[] $values() {
        return new CoroutineStart[]{DEFAULT, LAZY, ATOMIC, UNDISPATCHED};
    }

    static {
        DEFAULT = new CoroutineStart(PluginsConstants.Strategy.DEFAULT, 0);
        LAZY = new CoroutineStart("LAZY", 1);
        ATOMIC = new CoroutineStart("ATOMIC", 2);
        UNDISPATCHED = new CoroutineStart("UNDISPATCHED", 3);
        CoroutineStart[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    CoroutineStart(String r1, int r2) {
    }

    public static CoroutineStart valueOf(String r1) {
        return (CoroutineStart) Enum.valueOf(CoroutineStart.class, r1);
    }

    public static CoroutineStart[] values() {
        return (CoroutineStart[]) $VALUES.clone();
    }

    public final <R, T> void invoke(Function2<? super R, ? super Continuation<? super T>, ? extends Object> r2, R r3, Continuation<? super T> r4) {
        int r1 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (r1 != 1) goto L5;
        CancellableKt.startCoroutineCancellable(r2, r3, r4);
        return;
    L5:
        if (r1 != 2) goto L7;
        ContinuationKt.startCoroutine(r2, r3, r4);
        return;
    L7:
        if (r1 != 3) goto L9;
        UndispatchedKt.startCoroutineUndispatched(r2, r3, r4);
        return;
    L9:
        if (r1 != 4) goto L11;
        return;
    L11:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
    }

    public final boolean isLazy() {
        if (this != LAZY) goto L6;
        return true;
    L6:
        return false;
    }
}
