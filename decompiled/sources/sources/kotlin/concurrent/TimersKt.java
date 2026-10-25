package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a<\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a<\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a<\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a<\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a\u001c\u0010\r\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0081\u0080\u0004\u001aP\u0010\r\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001aN\u0010\r\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001aP\u0010\u0014\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001aN\u0010\u0014\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u001a(\u0010\u0015\u001a\u00020\u00012\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"schedule", "Ljava/util/TimerTask;", "Ljava/util/Timer;", "delay", _UrlKt.FRAGMENT_ENCODE_SET, "action", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "time", "Ljava/util/Date;", "period", "scheduleAtFixedRate", "timer", "name", _UrlKt.FRAGMENT_ENCODE_SET, "daemon", _UrlKt.FRAGMENT_ENCODE_SET, "initialDelay", "startAt", "fixedRateTimer", "timerTask", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "TimersKt")
/* loaded from: classes5.dex */
public final class TimersKt {

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nTimer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timer.kt\nkotlin/concurrent/TimersKt$timerTask$1\n*L\n1#1,149:1\n*E\n"})
    /* renamed from: kotlin.concurrent.TimersKt$timerTask$1, reason: invalid class name */
    public static final class AnonymousClass1 extends TimerTask {
        final /* synthetic */ Function1<TimerTask, Unit> $action;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super TimerTask, Unit> r1) {
            this.$action = r1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.$action.invoke(this);
        }
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String r0, boolean r1, long r2, long r4, Function1<? super TimerTask, Unit> r6) {
        Timer r02 = timer(r0, r1);
        r02.scheduleAtFixedRate(new AnonymousClass1(r6), r2, r4);
        return r02;
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String r0, boolean r1, Date r2, long r3, Function1<? super TimerTask, Unit> r5) {
        Timer r02 = timer(r0, r1);
        r02.scheduleAtFixedRate(new AnonymousClass1(r5), r2, r3);
        return r02;
    }

    public static /* synthetic */ Timer fixedRateTimer$default(String r6, boolean r7, long r8, long r10, Function1 r12, int r13, Object r14) {
        if ((r13 & 1) == 0) goto L6;
        r6 = null;
    L6:
        if ((r13 & 2) == 0) goto L9;
        r7 = false;
    L9:
        if ((r13 & 4) == 0) goto L11;
        r8 = 0;
    L11:
        Timer r0 = timer(r6, r7);
        AnonymousClass1 r1 = new AnonymousClass1(r12);
        r0.scheduleAtFixedRate(r1, r8, r10);
        return r0;
    }

    public static /* synthetic */ Timer fixedRateTimer$default(String r0, boolean r1, Date r2, long r3, Function1 r5, int r6, Object r7) {
        if ((r6 & 1) == 0) goto L6;
        r0 = null;
    L6:
        if ((r6 & 2) == 0) goto L8;
        r1 = false;
    L8:
        Timer r02 = timer(r0, r1);
        r02.scheduleAtFixedRate(new AnonymousClass1(r5), r2, r3);
        return r02;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer r1, long r2, long r4, Function1<? super TimerTask, Unit> r6) {
        AnonymousClass1 r22 = new AnonymousClass1(r6);
        r1.schedule(r22, r2, r4);
        return r22;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer r1, long r2, Function1<? super TimerTask, Unit> r4) {
        AnonymousClass1 r0 = new AnonymousClass1(r4);
        r1.schedule(r0, r2);
        return r0;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer r1, Date r2, long r3, Function1<? super TimerTask, Unit> r5) {
        AnonymousClass1 r0 = new AnonymousClass1(r5);
        r1.schedule(r0, r2, r3);
        return r0;
    }

    @InlineOnly
    private static final TimerTask schedule(Timer r1, Date r2, Function1<? super TimerTask, Unit> r3) {
        AnonymousClass1 r0 = new AnonymousClass1(r3);
        r1.schedule(r0, r2);
        return r0;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(Timer r1, long r2, long r4, Function1<? super TimerTask, Unit> r6) {
        AnonymousClass1 r22 = new AnonymousClass1(r6);
        r1.scheduleAtFixedRate(r22, r2, r4);
        return r22;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(Timer r1, Date r2, long r3, Function1<? super TimerTask, Unit> r5) {
        AnonymousClass1 r0 = new AnonymousClass1(r5);
        r1.scheduleAtFixedRate(r0, r2, r3);
        return r0;
    }

    @PublishedApi
    public static final Timer timer(String r1, boolean r2) {
        if (r1 != null) goto L6;
        return new Timer(r2);
    L6:
        return new Timer(r1, r2);
    }

    @InlineOnly
    private static final Timer timer(String r0, boolean r1, long r2, long r4, Function1<? super TimerTask, Unit> r6) {
        Timer r02 = timer(r0, r1);
        r02.schedule(new AnonymousClass1(r6), r2, r4);
        return r02;
    }

    @InlineOnly
    private static final Timer timer(String r0, boolean r1, Date r2, long r3, Function1<? super TimerTask, Unit> r5) {
        Timer r02 = timer(r0, r1);
        r02.schedule(new AnonymousClass1(r5), r2, r3);
        return r02;
    }

    public static /* synthetic */ Timer timer$default(String r6, boolean r7, long r8, long r10, Function1 r12, int r13, Object r14) {
        if ((r13 & 1) == 0) goto L6;
        r6 = null;
    L6:
        if ((r13 & 2) == 0) goto L9;
        r7 = false;
    L9:
        if ((r13 & 4) == 0) goto L11;
        r8 = 0;
    L11:
        Timer r0 = timer(r6, r7);
        AnonymousClass1 r1 = new AnonymousClass1(r12);
        r0.schedule(r1, r8, r10);
        return r0;
    }

    public static /* synthetic */ Timer timer$default(String r0, boolean r1, Date r2, long r3, Function1 r5, int r6, Object r7) {
        if ((r6 & 1) == 0) goto L6;
        r0 = null;
    L6:
        if ((r6 & 2) == 0) goto L8;
        r1 = false;
    L8:
        Timer r02 = timer(r0, r1);
        r02.schedule(new AnonymousClass1(r5), r2, r3);
        return r02;
    }

    @InlineOnly
    private static final TimerTask timerTask(Function1<? super TimerTask, Unit> r1) {
        return new AnonymousClass1(r1);
    }
}
