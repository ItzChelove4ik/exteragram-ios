package androidx.work.impl.utils;

import android.net.NetworkRequest;
import android.os.Build;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"transportTypesCompat", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/net/NetworkRequest;", "getTransportTypesCompat", "(Landroid/net/NetworkRequest;)[I", "capabilitiesCompat", "getCapabilitiesCompat", "defaultCapabilities", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNetworkRequestCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NetworkRequestCompat.kt\nandroidx/work/impl/utils/NetworkRequestCompatKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,176:1\n3856#2:177\n4374#2,2:178\n3856#2:180\n4374#2,2:181\n*S KotlinDebug\n*F\n+ 1 NetworkRequestCompat.kt\nandroidx/work/impl/utils/NetworkRequestCompatKt\n*L\n53#1:177\n53#1:178,2\n95#1:180\n95#1:181,2\n*E\n"})
/* loaded from: classes4.dex */
public abstract class NetworkRequestCompatKt {
    private static final int[] defaultCapabilities = null;

    static {
        defaultCapabilities = new int[]{13, 15, 14};
    }

    public static final /* synthetic */ int[] access$getDefaultCapabilities$p() {
        return defaultCapabilities;
    }

    public static final int[] getCapabilitiesCompat(NetworkRequest r6) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        int[] r1 = {17, 5, 2, 10, 29, 19, 3, 32, 7, 4, 12, 36, 23, 0, 33, 20, 11, 13, 18, 21, 15, 35, 34, 8, 1, 25, 14, 16, 6, 9};
        ArrayList r2 = new ArrayList();
        int r3 = 0;
    L7:
        if (r3 >= 30) goto L13;
        int r4 = r1[r3];
        if (NetworkRequest28.INSTANCE.hasCapability$work_runtime_release(r6, r4) == false) goto L11;
        r2.add(Integer.valueOf(r4));
    L11:
        r3 = r3 + 1;
        goto L7
    L13:
        return CollectionsKt.toIntArray(r2);
    L5:
        return NetworkRequest31.INSTANCE.capabilities(r6);
    }

    public static final int[] getTransportTypesCompat(NetworkRequest r6) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        int[] r1 = {2, 0, 3, 6, 10, 9, 8, 4, 1, 5};
        ArrayList r2 = new ArrayList();
        int r3 = 0;
    L7:
        if (r3 >= 10) goto L13;
        int r4 = r1[r3];
        if (NetworkRequest28.INSTANCE.hasTransport$work_runtime_release(r6, r4) == false) goto L11;
        r2.add(Integer.valueOf(r4));
    L11:
        r3 = r3 + 1;
        goto L7
    L13:
        return CollectionsKt.toIntArray(r2);
    L5:
        return NetworkRequest31.INSTANCE.transportTypes(r6);
    }
}
