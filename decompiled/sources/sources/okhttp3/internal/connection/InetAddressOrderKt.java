package okhttp3.internal.connection;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"reorderForHappyEyeballs", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/net/InetAddress;", "addresses", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInetAddressOrder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InetAddressOrder.kt\nokhttp3/internal/connection/InetAddressOrderKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,46:1\n3301#2,10:47\n*S KotlinDebug\n*F\n+ 1 InetAddressOrder.kt\nokhttp3/internal/connection/InetAddressOrderKt\n*L\n38#1:47,10\n*E\n"})
/* loaded from: classes.dex */
public final class InetAddressOrderKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final List<InetAddress> reorderForHappyEyeballs(List<? extends InetAddress> r5) {
        if (r5.size() < 2) goto L26;
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        Iterator r0 = r5.iterator();
    L7:
        if (r0.hasNext() == false) goto L12;
        Object r3 = r0.next();
        if ((((InetAddress) r3) instanceof Inet6Address) == true) goto L10;
        r2.add(r3);
        goto L7
    L10:
        r1.add(r3);
        goto L7
    L12:
        Pair r02 = new Pair(r1, r2);
        List r12 = (List) r02.component1();
        List r03 = (List) r02.component2();
        if (r12.isEmpty() == false) goto L15;
        return r5;
    L15:
        if (r03.isEmpty() == false) goto L18;
        return r5;
    L18:
        return _UtilCommonKt.interleave(r12, r03);
    L26:
        return r5;
    }
}
