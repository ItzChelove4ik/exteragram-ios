package androidx.work.impl.utils;

import android.net.NetworkRequest;
import androidx.work.Logger;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u001d\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\tH\u0000¢\u0006\u0002\b\rJ\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/utils/NetworkRequest28;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "hasCapability", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Landroid/net/NetworkRequest;", "capability", _UrlKt.FRAGMENT_ENCODE_SET, "hasCapability$work_runtime_release", "hasTransport", "transport", "hasTransport$work_runtime_release", "createNetworkRequest", "capabilities", _UrlKt.FRAGMENT_ENCODE_SET, "transports", "createNetworkRequestCompat", "Landroidx/work/impl/utils/NetworkRequestCompat;", "createNetworkRequestCompat$work_runtime_release", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNetworkRequestCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NetworkRequestCompat.kt\nandroidx/work/impl/utils/NetworkRequest28\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,176:1\n13493#2,2:177\n13493#2,2:179\n13493#2,2:181\n*S KotlinDebug\n*F\n+ 1 NetworkRequestCompat.kt\nandroidx/work/impl/utils/NetworkRequest28\n*L\n119#1:177,2\n136#1:179,2\n153#1:181,2\n*E\n"})
/* loaded from: classes4.dex */
public final class NetworkRequest28 {
    public static final NetworkRequest28 INSTANCE = null;

    static {
        INSTANCE = new NetworkRequest28();
    }

    private NetworkRequest28() {
    }

    @JvmStatic
    public static final NetworkRequest createNetworkRequest(int[] r12, int[] r13) {
        NetworkRequest.Builder r0 = new NetworkRequest.Builder();
        int r1 = r12.length;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r1) goto L11;
        int r5 = r12[r3];
        r0.addCapability(r5);     // Catch: IllegalArgumentException -> L8
    L10:
        r3 = r3 + 1;
    L8:
        e = move-exception;
        Logger.get().warning(NetworkRequestCompat.Companion.getTAG(), "Ignoring adding capability '" + r5 + '\'', e);
        goto L10
    L11:
        int[] r14 = NetworkRequestCompatKt.access$getDefaultCapabilities$p();
        int r32 = r14.length;
        int r52 = 0;
    L12:
        if (r52 >= r32) goto L20;
        int r6 = r14[r52];
        if (ArraysKt.contains(r12, r6) == true) goto L19;
        r0.removeCapability(r6);     // Catch: IllegalArgumentException -> L17
    L17:
        e = move-exception;
        Logger.get().warning(NetworkRequestCompat.Companion.getTAG(), "Ignoring removing default capability '" + r6 + '\'', e);
    L19:
        r52 = r52 + 1;
        goto L12
    L20:
        int r122 = r13.length;
    L21:
        if (r2 >= r122) goto L24;
        r0.addTransportType(r13[r2]);
        r2 = r2 + 1;
        goto L21
    L24:
        return r0.build();
    }

    public final NetworkRequestCompat createNetworkRequestCompat$work_runtime_release(int[] r1, int[] r2) {
        return new NetworkRequestCompat(createNetworkRequest(r1, r2));
    }

    public final boolean hasCapability$work_runtime_release(NetworkRequest r1, int r2) {
        return r1.hasCapability(r2);
    }

    public final boolean hasTransport$work_runtime_release(NetworkRequest r1, int r2) {
        return r1.hasTransport(r2);
    }
}
