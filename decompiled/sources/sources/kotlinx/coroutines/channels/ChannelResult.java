package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087@\u0018\u0000  *\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003!\" B\u0013\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0006\u0010\u0005J\r\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\u0005J\u000f\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u0012\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u001f\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002¨\u0006#"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrNull-impl", "getOrNull", "getOrThrow-impl", "getOrThrow", _UrlKt.FRAGMENT_ENCODE_SET, "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "exceptionOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "getHolder$annotations", "()V", "isSuccess-impl", "isSuccess", "isClosed-impl", "isClosed", "Companion", "Failed", "Closed", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
@SourceDebugExtension({"SMAP\nChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channel.kt\nkotlinx/coroutines/channels/ChannelResult\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1529:1\n1#2:1530\n*E\n"})
/* loaded from: classes.dex */
public final class ChannelResult<T> {
    public static final Companion Companion = null;
    private static final Failed failed = null;
    private final Object holder;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Closed;", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/Throwable;)V", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @PublishedApi
    public static final class Closed extends Failed {

        @JvmField
        public final Throwable cause;

        public Closed(Throwable r1) {
            this.cause = r1;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof Closed) == true) goto L5;
            return false;
        L5:
            if (Intrinsics.areEqual(this.cause, ((Closed) r2).cause) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            Throwable r0 = this.cause;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.hashCode();
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0001\u0010\b2\u0006\u0010\t\u001a\u0002H\bH\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0001\u0010\bH\u0007¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0001\u0010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "failed", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "success", "Lkotlinx/coroutines/channels/ChannelResult;", "E", "value", "success-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "failure", "failure-PtdJZtk", "()Ljava/lang/Object;", "closed", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "closed-JP2dKIU", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: closed-JP2dKIU, reason: not valid java name */
        public final <E> Object m3545closedJP2dKIU(Throwable r1) {
            return ChannelResult.m3535constructorimpl(new Closed(r1));
        }

        /* renamed from: failure-PtdJZtk, reason: not valid java name */
        public final <E> Object m3546failurePtdJZtk() {
            return ChannelResult.m3535constructorimpl(ChannelResult.access$getFailed$cp());
        }

        /* renamed from: success-JP2dKIU, reason: not valid java name */
        public final <E> Object m3547successJP2dKIU(E r1) {
            return ChannelResult.m3535constructorimpl(r1);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0011\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Failed;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @PublishedApi
    public static class Failed {
        public Failed() {
        }

        public String toString() {
            return "Failed";
        }
    }

    static {
        Companion = new Companion(null);
        failed = new Failed();
    }

    @PublishedApi
    private /* synthetic */ ChannelResult(Object r1) {
        this.holder = r1;
    }

    public static final /* synthetic */ Failed access$getFailed$cp() {
        return failed;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ChannelResult m3534boximpl(Object r1) {
        return new ChannelResult(r1);
    }

    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m3535constructorimpl(Object r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m3536equalsimpl(Object r2, Object r3) {
        if ((r3 instanceof ChannelResult) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((ChannelResult) r3).m3544unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m3537exceptionOrNullimpl(Object r2) {
        if ((r2 instanceof Closed) == false) goto L5;
        Closed r22 = (Closed) r2;
    L6:
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.cause;
    L5:
        r22 = null;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrNull-impl, reason: not valid java name */
    public static final T m3538getOrNullimpl(Object r1) {
        if ((r1 instanceof Failed) == true) goto L5;
        return r1;
    L5:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrThrow-impl, reason: not valid java name */
    public static final T m3539getOrThrowimpl(Object r2) {
        if ((r2 instanceof Failed) == true) goto L6;
        return r2;
    L6:
        if ((r2 instanceof Closed) == false) goto L12;
        Throwable r22 = ((Closed) r2).cause;
        if (r22 != null) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("Trying to call 'getOrThrow' on a channel closed without a cause");
        return null;
    L11:
        throw r22;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Trying to call 'getOrThrow' on a failed result of a non-closed channel");
        return null;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m3540hashCodeimpl(Object r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return r0.hashCode();
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m3541isClosedimpl(Object r0) {
        return r0 instanceof Closed;
    }

    /* renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m3542isSuccessimpl(Object r0) {
        return !(r0 instanceof Failed);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m3543toStringimpl(Object r2) {
        if ((r2 instanceof Closed) == false) goto L7;
        return ((Closed) r2).toString();
    L7:
        return "Value(" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m3536equalsimpl(this.holder, r1);
    }

    public int hashCode() {
        return m3540hashCodeimpl(this.holder);
    }

    public String toString() {
        return m3543toStringimpl(this.holder);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m3544unboximpl() {
        return this.holder;
    }
}
