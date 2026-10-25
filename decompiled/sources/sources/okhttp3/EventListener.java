package okhttp3;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\t\b&\u0018\u0000 N2\u00020\u0001:\u0003MNOB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J+\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u00130\u0011H\u0016J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J+\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\u0011\u0010\u0018\u001a\r\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u00130\u0011H\u0016J \u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012H\u0016J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001a\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J*\u0010\"\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J2\u0010%\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010(\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*H\u0016J\u0018\u0010+\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010-\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u00101\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00102\u001a\u000203H\u0016J\u0018\u00104\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u00105\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u00106\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u000208H\u0016J\u0010\u00109\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010:\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00102\u001a\u000203H\u0016J\u0018\u0010;\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010<\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010=\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010>\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010?\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u000208H\u0016J\u0018\u0010@\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u000208H\u0016J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010B\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010C\u001a\u000208H\u0016J \u0010D\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u00020GH\u0016J\"\u0010H\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010I\u001a\u0002082\b\u0010J\u001a\u0004\u0018\u00010/H\u0016J\u0011\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0000H\u0086\u0002¨\u0006P"}, d2 = {"Lokhttp3/EventListener;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "callStart", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", "dispatcherQueueStart", "dispatcher", "Lokhttp3/Dispatcher;", "dispatcherQueueEnd", "proxySelectStart", "url", "Lokhttp3/HttpUrl;", "proxySelectEnd", "proxies", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/net/Proxy;", "Lkotlin/jvm/JvmSuppressWildcards;", "dnsStart", "domainName", _UrlKt.FRAGMENT_ENCODE_SET, "dnsEnd", "inetAddressList", "Ljava/net/InetAddress;", "connectStart", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "proxy", "secureConnectStart", "secureConnectEnd", "handshake", "Lokhttp3/Handshake;", "connectEnd", "protocol", "Lokhttp3/Protocol;", "connectFailed", "ioe", "Ljava/io/IOException;", "connectionAcquired", "connection", "Lokhttp3/Connection;", "connectionReleased", "requestHeadersStart", "requestHeadersEnd", PluginsConstants.REQUEST, "Lokhttp3/Request;", "requestBodyStart", "requestBodyEnd", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "requestFailed", "responseHeadersStart", "responseHeadersEnd", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "responseBodyStart", "responseBodyEnd", "responseFailed", "callEnd", "callFailed", "canceled", "satisfactionFailure", "cacheHit", "cacheMiss", "cacheConditionalHit", "cachedResponse", "retryDecision", "exception", "retry", _UrlKt.FRAGMENT_ENCODE_SET, "followUpDecision", "networkResponse", "nextRequest", "plus", "other", "Factory", "Companion", "AggregateEventListener", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class EventListener {
    public static final Companion Companion = null;

    @JvmField
    public static final EventListener NONE = null;

    @Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J+\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0011\u0010\u0015\u001a\r\u0012\t\u0012\u00070\u0017¢\u0006\u0002\b\u00180\u0016H\u0016J\u0018\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J+\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0011\u0010\u001d\u001a\r\u0012\t\u0012\u00070\u001e¢\u0006\u0002\b\u00180\u0016H\u0016J \u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017H\u0016J\u0010\u0010#\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010$\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J*\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J2\u0010*\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00101\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u00102\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u00106\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00107\u001a\u000208H\u0016J\u0018\u00109\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010:\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010;\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010?\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00107\u001a\u000208H\u0016J\u0018\u0010@\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010A\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010B\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010C\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010D\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010<\u001a\u00020=H\u0016J\u0018\u0010E\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010F\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010G\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010H\u001a\u00020=H\u0016J \u0010I\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010J\u001a\u00020,2\u0006\u0010K\u001a\u00020LH\u0016J\"\u0010M\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010N\u001a\u00020=2\b\u0010O\u001a\u0004\u0018\u000104H\u0016R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006P"}, d2 = {"Lokhttp3/EventListener$AggregateEventListener;", "Lokhttp3/EventListener;", "eventListeners", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Lokhttp3/EventListener;)V", "getEventListeners", "()[Lokhttp3/EventListener;", "[Lokhttp3/EventListener;", "callStart", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", "dispatcherQueueStart", "dispatcher", "Lokhttp3/Dispatcher;", "dispatcherQueueEnd", "proxySelectStart", "url", "Lokhttp3/HttpUrl;", "proxySelectEnd", "proxies", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/net/Proxy;", "Lkotlin/jvm/JvmSuppressWildcards;", "dnsStart", "domainName", _UrlKt.FRAGMENT_ENCODE_SET, "dnsEnd", "inetAddressList", "Ljava/net/InetAddress;", "connectStart", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "proxy", "secureConnectStart", "secureConnectEnd", "handshake", "Lokhttp3/Handshake;", "connectEnd", "protocol", "Lokhttp3/Protocol;", "connectFailed", "ioe", "Ljava/io/IOException;", "connectionAcquired", "connection", "Lokhttp3/Connection;", "connectionReleased", "requestHeadersStart", "requestHeadersEnd", PluginsConstants.REQUEST, "Lokhttp3/Request;", "requestBodyStart", "requestBodyEnd", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "requestFailed", "responseHeadersStart", "responseHeadersEnd", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "responseBodyStart", "responseBodyEnd", "responseFailed", "callEnd", "callFailed", "canceled", "satisfactionFailure", "cacheHit", "cacheMiss", "cacheConditionalHit", "cachedResponse", "retryDecision", "exception", "retry", _UrlKt.FRAGMENT_ENCODE_SET, "followUpDecision", "networkResponse", "nextRequest", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class AggregateEventListener extends EventListener {
        private final EventListener[] eventListeners;

        public AggregateEventListener(EventListener[] r1) {
            this.eventListeners = r1;
        }

        @Override // okhttp3.EventListener
        public void cacheConditionalHit(Call r4, Response r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].cacheConditionalHit(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void cacheHit(Call r4, Response r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].cacheHit(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void cacheMiss(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].cacheMiss(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void callEnd(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].callEnd(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void callFailed(Call r4, IOException r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].callFailed(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void callStart(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].callStart(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void canceled(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].canceled(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void connectEnd(Call r4, InetSocketAddress r5, Proxy r6, Protocol r7) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].connectEnd(r4, r5, r6, r7);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void connectFailed(Call r9, InetSocketAddress r10, Proxy r11, Protocol r12, IOException r13) {
            EventListener[] r8 = this.eventListeners;
            int r0 = r8.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r8[r1].connectFailed(r9, r10, r11, r12, r13);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void connectStart(Call r4, InetSocketAddress r5, Proxy r6) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].connectStart(r4, r5, r6);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void connectionAcquired(Call r4, Connection r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].connectionAcquired(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void connectionReleased(Call r4, Connection r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].connectionReleased(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void dispatcherQueueEnd(Call r4, Dispatcher r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].dispatcherQueueEnd(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void dispatcherQueueStart(Call r4, Dispatcher r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].dispatcherQueueStart(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void dnsEnd(Call r4, String r5, List<InetAddress> r6) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].dnsEnd(r4, r5, r6);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void dnsStart(Call r4, String r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].dnsStart(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void followUpDecision(Call r4, Response r5, Request r6) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].followUpDecision(r4, r5, r6);
            r1 = r1 + 1;
            goto L3
        }

        public final EventListener[] getEventListeners() {
            return this.eventListeners;
        }

        @Override // okhttp3.EventListener
        public void proxySelectEnd(Call r4, HttpUrl r5, List<Proxy> r6) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].proxySelectEnd(r4, r5, r6);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void proxySelectStart(Call r4, HttpUrl r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].proxySelectStart(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void requestBodyEnd(Call r4, long r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].requestBodyEnd(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void requestBodyStart(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].requestBodyStart(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void requestFailed(Call r4, IOException r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].requestFailed(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void requestHeadersEnd(Call r4, Request r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].requestHeadersEnd(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void requestHeadersStart(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].requestHeadersStart(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void responseBodyEnd(Call r4, long r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].responseBodyEnd(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void responseBodyStart(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].responseBodyStart(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void responseFailed(Call r4, IOException r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].responseFailed(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void responseHeadersEnd(Call r4, Response r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].responseHeadersEnd(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void responseHeadersStart(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].responseHeadersStart(r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void retryDecision(Call r4, IOException r5, boolean r6) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].retryDecision(r4, r5, r6);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void satisfactionFailure(Call r4, Response r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].satisfactionFailure(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void secureConnectEnd(Call r4, Handshake r5) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].secureConnectEnd(r4, r5);
            r1 = r1 + 1;
            goto L3
        }

        @Override // okhttp3.EventListener
        public void secureConnectStart(Call r4) {
            EventListener[] r3 = this.eventListeners;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].secureConnectStart(r4);
            r1 = r1 + 1;
            goto L3
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/EventListener$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NONE", "Lokhttp3/EventListener;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lokhttp3/EventListener$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "create", "Lokhttp3/EventListener;", "call", "Lokhttp3/Call;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Factory {
        EventListener create(Call r1);
    }

    static {
        Companion = new Companion(null);
        NONE = new EventListener$Companion$NONE$1();
    }

    public EventListener() {
    }

    public void cacheConditionalHit(Call r1, Response r2) {
    }

    public void cacheHit(Call r1, Response r2) {
    }

    public void cacheMiss(Call r1) {
    }

    public void callEnd(Call r1) {
    }

    public void callFailed(Call r1, IOException r2) {
    }

    public void callStart(Call r1) {
    }

    public void canceled(Call r1) {
    }

    public void connectEnd(Call r1, InetSocketAddress r2, Proxy r3, Protocol r4) {
    }

    public void connectFailed(Call r1, InetSocketAddress r2, Proxy r3, Protocol r4, IOException r5) {
    }

    public void connectStart(Call r1, InetSocketAddress r2, Proxy r3) {
    }

    public void connectionAcquired(Call r1, Connection r2) {
    }

    public void connectionReleased(Call r1, Connection r2) {
    }

    public void dispatcherQueueEnd(Call r1, Dispatcher r2) {
    }

    public void dispatcherQueueStart(Call r1, Dispatcher r2) {
    }

    public void dnsEnd(Call r1, String r2, List<InetAddress> r3) {
    }

    public void dnsStart(Call r1, String r2) {
    }

    public void followUpDecision(Call r1, Response r2, Request r3) {
    }

    public final EventListener plus(EventListener r5) {
        EventListener r0 = NONE;
        if (this != r0) goto L6;
        return r5;
    L6:
        if ((this instanceof AggregateEventListener) == false) goto L8;
        EventListener[] r1 = ((AggregateEventListener) this).getEventListeners();
    L9:
        if (r5 != r0) goto L12;
        return this;
    L12:
        if ((r5 instanceof AggregateEventListener) == false) goto L14;
        EventListener[] r4 = ((AggregateEventListener) r5).getEventListeners();
    L16:
        return new AggregateEventListener((EventListener[]) ArraysKt.plus(r1, r4));
    L14:
        r4 = new EventListener[]{r5};
        goto L16
    L8:
        r1 = new EventListener[]{this};
        goto L9
    }

    public void proxySelectEnd(Call r1, HttpUrl r2, List<Proxy> r3) {
    }

    public void proxySelectStart(Call r1, HttpUrl r2) {
    }

    public void requestBodyEnd(Call r1, long r2) {
    }

    public void requestBodyStart(Call r1) {
    }

    public void requestFailed(Call r1, IOException r2) {
    }

    public void requestHeadersEnd(Call r1, Request r2) {
    }

    public void requestHeadersStart(Call r1) {
    }

    public void responseBodyEnd(Call r1, long r2) {
    }

    public void responseBodyStart(Call r1) {
    }

    public void responseFailed(Call r1, IOException r2) {
    }

    public void responseHeadersEnd(Call r1, Response r2) {
    }

    public void responseHeadersStart(Call r1) {
    }

    public void retryDecision(Call r1, IOException r2, boolean r3) {
    }

    public void satisfactionFailure(Call r1, Response r2) {
    }

    public void secureConnectEnd(Call r1, Handshake r2) {
    }

    public void secureConnectStart(Call r1) {
    }
}
