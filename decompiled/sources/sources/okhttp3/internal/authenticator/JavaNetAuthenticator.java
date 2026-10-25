package okhttp3.internal.authenticator;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.Authenticator;
import okhttp3.Challenge;
import okhttp3.Credentials;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/authenticator/JavaNetAuthenticator;", "Lokhttp3/Authenticator;", "defaultDns", "Lokhttp3/Dns;", "<init>", "(Lokhttp3/Dns;)V", "authenticate", "Lokhttp3/Request;", "route", "Lokhttp3/Route;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "connectToInetAddress", "Ljava/net/InetAddress;", "Ljava/net/Proxy;", "url", "Lokhttp3/HttpUrl;", "dns", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class JavaNetAuthenticator implements Authenticator {
    private final Dns defaultDns;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[Proxy.Type.values().length];
            r0[Proxy.Type.DIRECT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L4:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public JavaNetAuthenticator() {
        this(null, 1, null);
    }

    public JavaNetAuthenticator(Dns r1) {
        this.defaultDns = r1;
    }

    public /* synthetic */ JavaNetAuthenticator(Dns r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = Dns.SYSTEM;
    L5:
        this(r1);
    }

    private final InetAddress connectToInetAddress(Proxy r2, HttpUrl r3, Dns r4) {
        Proxy.Type r1 = r2.type();
        if (r1 != null) goto L5;
        int r12 = -1;
    L7:
        if (r12 != 1) goto L11;
        return (InetAddress) CollectionsKt.first(r4.lookup(r3.host()));
    L11:
        return ((InetSocketAddress) r2.address()).getAddress();
    L5:
        r12 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
        goto L7
    }

    @Override // okhttp3.Authenticator
    public Request authenticate(Route r19, Response r20) {
        List<Challenge> r1 = r20.challenges();
        Request r2 = r20.request();
        HttpUrl r3 = r2.url();
        if (r20.code() != 407) goto L5;
        boolean r4 = true;
    L6:
        if (r19 == null) goto L9;
        Proxy r5 = r19.proxy();
        if (r5 == null) goto L9;
    L10:
        Iterator<Challenge> r12 = r1.iterator();
    L12:
        if (r12.hasNext() == false) goto L31;
        Challenge r7 = r12.next();
        if (StringsKt.equals("Basic", r7.scheme(), true) == false) goto L12;
        if (r19 == null) goto L21;
        Address r8 = r19.address();
        if (r8 == null) goto L21;
        Dns r82 = r8.dns();
        if (r82 == null) goto L21;
    L22:
        if (r4 == false) goto L24;
        InetSocketAddress r9 = (InetSocketAddress) r5.address();
        PasswordAuthentication r83 = java.net.Authenticator.requestPasswordAuthentication(r9.getHostName(), connectToInetAddress(r5, r3, r82), r9.getPort(), r3.scheme(), r7.realm(), r7.scheme(), r3.url(), Authenticator.RequestorType.PROXY);
    L25:
        if (r83 == null) goto L12;
        if (r4 == false) goto L28;
        String r0 = "Proxy-Authorization";
    L30:
        return r2.newBuilder().header(r0, Credentials.basic(r83.getUserName(), new String(r83.getPassword()), r7.charset())).build();
    L28:
        r0 = "Authorization";
        goto L30
    L24:
        r83 = java.net.Authenticator.requestPasswordAuthentication(r3.host(), connectToInetAddress(r5, r3, r82), r3.port(), r3.scheme(), r7.realm(), r7.scheme(), r3.url(), Authenticator.RequestorType.SERVER);
    L21:
        r82 = this.defaultDns;
        goto L22
    L31:
        return null;
    L9:
        r5 = Proxy.NO_PROXY;
        goto L10
    L5:
        r4 = false;
        goto L6
    }
}
