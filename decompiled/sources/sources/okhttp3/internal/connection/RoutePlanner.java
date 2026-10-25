package okhttp3.internal.connection;

import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.HttpUrl;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0014\u0015J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\bH&J\u0014\u0010\u000e\u001a\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0016À\u0006\u0003"}, d2 = {"Lokhttp3/internal/connection/RoutePlanner;", _UrlKt.FRAGMENT_ENCODE_SET, "address", "Lokhttp3/Address;", "getAddress", "()Lokhttp3/Address;", "deferredPlans", "Lkotlin/collections/ArrayDeque;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getDeferredPlans", "()Lkotlin/collections/ArrayDeque;", "isCanceled", _UrlKt.FRAGMENT_ENCODE_SET, "plan", "hasNext", "failedConnection", "Lokhttp3/internal/connection/RealConnection;", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "Plan", "ConnectResult", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public interface RoutePlanner {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010¨\u0006\u001b"}, d2 = {"Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", _UrlKt.FRAGMENT_ENCODE_SET, "plan", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "nextPlan", "throwable", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/connection/RoutePlanner$Plan;Lokhttp3/internal/connection/RoutePlanner$Plan;Ljava/lang/Throwable;)V", "getPlan", "()Lokhttp3/internal/connection/RoutePlanner$Plan;", "getNextPlan", "getThrowable", "()Ljava/lang/Throwable;", "isSuccess", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ConnectResult {
        private final Plan nextPlan;
        private final Plan plan;
        private final Throwable throwable;

        public ConnectResult(Plan r1, Plan r2, Throwable r3) {
            this.plan = r1;
            this.nextPlan = r2;
            this.throwable = r3;
        }

        public /* synthetic */ ConnectResult(Plan r2, Plan r3, Throwable r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 2) == 0) goto L6;
            r3 = null;
        L6:
            if ((r5 & 4) == 0) goto L8;
            r4 = null;
        L8:
            this(r2, r3, r4);
        }

        public static /* synthetic */ ConnectResult copy$default(ConnectResult r0, Plan r1, Plan r2, Throwable r3, int r4, Object r5) {
            if ((r4 & 1) == 0) goto L6;
            r1 = r0.plan;
        L6:
            if ((r4 & 2) == 0) goto L9;
            r2 = r0.nextPlan;
        L9:
            if ((r4 & 4) == 0) goto L12;
            r3 = r0.throwable;
        L12:
            return r0.copy(r1, r2, r3);
        }

        public final Plan component1() {
            return this.plan;
        }

        public final Plan component2() {
            return this.nextPlan;
        }

        public final Throwable component3() {
            return this.throwable;
        }

        public final ConnectResult copy(Plan r1, Plan r2, Throwable r3) {
            return new ConnectResult(r1, r2, r3);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ConnectResult) == true) goto L8;
            return false;
        L8:
            ConnectResult r52 = (ConnectResult) r5;
            if (Intrinsics.areEqual(this.plan, r52.plan) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.nextPlan, r52.nextPlan) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.throwable, r52.throwable) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final Plan getNextPlan() {
            return this.nextPlan;
        }

        public final Plan getPlan() {
            return this.plan;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }

        public int hashCode() {
            int r0 = this.plan.hashCode() * 31;
            Plan r1 = this.nextPlan;
            int r2 = 0;
            if (r1 != null) goto L5;
            int r12 = 0;
        L6:
            int r02 = (r0 + r12) * 31;
            Throwable r3 = this.throwable;
            if (r3 == null) goto L11;
            r2 = r3.hashCode();
        L11:
            return r02 + r2;
        L5:
            r12 = r1.hashCode();
            goto L6
        }

        public final boolean isSuccess() {
            if (this.nextPlan == null) goto L5;
            return false;
        L5:
            if (this.throwable != null) goto L10;
            return true;
        L10:
            return false;
        }

        public String toString() {
            return "ConnectResult(plan=" + this.plan + ", nextPlan=" + this.nextPlan + ", throwable=" + this.throwable + ')';
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean hasNext$default(RoutePlanner r0, RealConnection r1, int r2, Object r3) {
            return RoutePlanner.hasNext$default(r0, r1, r2, r3);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\n\u0010\f\u001a\u0004\u0018\u00010\u0000H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lokhttp3/internal/connection/RoutePlanner$Plan;", _UrlKt.FRAGMENT_ENCODE_SET, "isReady", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "connectTcp", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "connectTlsEtc", "handleSuccess", "Lokhttp3/internal/connection/RealConnection;", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "retry", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Plan {
        /* renamed from: cancel */
        void mo3746cancel();

        /* renamed from: connectTcp */
        ConnectResult mo3750connectTcp();

        /* renamed from: connectTlsEtc */
        ConnectResult mo3751connectTlsEtc();

        /* renamed from: handleSuccess */
        RealConnection mo3747handleSuccess();

        boolean isReady();

        /* renamed from: retry */
        Plan mo3748retry();
    }

    static /* synthetic */ boolean hasNext$default(RoutePlanner r0, RealConnection r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: hasNext");
        return false;
    L4:
        if ((r2 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.hasNext(r1);
    }

    Address getAddress();

    ArrayDeque<Plan> getDeferredPlans();

    boolean hasNext(RealConnection r1);

    boolean isCanceled();

    Plan plan();

    boolean sameHostAndPort(HttpUrl r1);
}
