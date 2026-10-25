package androidx.work.impl.constraints;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJB\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0013\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0003\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0004\u0010\u0016R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0005\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0006\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0007\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/constraints/NetworkState;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isConnected", "isValidated", "isMetered", "isNotRoaming", "isBlocked", "<init>", "(ZZZZZ)V", "copy", "(ZZZZZ)Landroidx/work/impl/constraints/NetworkState;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "()Z", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NetworkState {
    private final boolean isBlocked;
    private final boolean isConnected;
    private final boolean isMetered;
    private final boolean isNotRoaming;
    private final boolean isValidated;

    public NetworkState(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5) {
        this.isConnected = r1;
        this.isValidated = r2;
        this.isMetered = r3;
        this.isNotRoaming = r4;
        this.isBlocked = r5;
    }

    public static /* synthetic */ NetworkState copy$default(NetworkState r0, boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, int r6, Object r7) {
        if ((r6 & 1) == 0) goto L6;
        r1 = r0.isConnected;
    L6:
        if ((r6 & 2) == 0) goto L9;
        r2 = r0.isValidated;
    L9:
        if ((r6 & 4) == 0) goto L12;
        r3 = r0.isMetered;
    L12:
        if ((r6 & 8) == 0) goto L15;
        r4 = r0.isNotRoaming;
    L15:
        if ((r6 & 16) == 0) goto L17;
        r5 = r0.isBlocked;
    L17:
        boolean r62 = r4;
        boolean r72 = r5;
        boolean r52 = r3;
        boolean r32 = r1;
        return r0.copy(r32, r2, r52, r62, r72);
    }

    public final NetworkState copy(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5) {
        return new NetworkState(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof NetworkState) == true) goto L8;
        return false;
    L8:
        NetworkState r52 = (NetworkState) r5;
        if (this.isConnected == r52.isConnected) goto L12;
        return false;
    L12:
        if (this.isValidated == r52.isValidated) goto L15;
        return false;
    L15:
        if (this.isMetered == r52.isMetered) goto L18;
        return false;
    L18:
        if (this.isNotRoaming == r52.isNotRoaming) goto L21;
        return false;
    L21:
        if (this.isBlocked == r52.isBlocked) goto L23;
        return false;
    L23:
        return true;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.isConnected) * 31) + Boolean.hashCode(this.isValidated)) * 31) + Boolean.hashCode(this.isMetered)) * 31) + Boolean.hashCode(this.isNotRoaming)) * 31) + Boolean.hashCode(this.isBlocked);
    }

    public final boolean isBlocked() {
        return this.isBlocked;
    }

    public final boolean isConnected() {
        return this.isConnected;
    }

    public final boolean isMetered() {
        return this.isMetered;
    }

    public final boolean isNotRoaming() {
        return this.isNotRoaming;
    }

    public final boolean isValidated() {
        return this.isValidated;
    }

    public String toString() {
        return "NetworkState(isConnected=" + this.isConnected + ", isValidated=" + this.isValidated + ", isMetered=" + this.isMetered + ", isNotRoaming=" + this.isNotRoaming + ", isBlocked=" + this.isBlocked + ')';
    }
}
