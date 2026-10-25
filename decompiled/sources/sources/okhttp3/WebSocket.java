package okhttp3;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0012J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&J\u001a\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\tH&J\b\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lokhttp3/WebSocket;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Lokhttp3/Request;", "queueSize", _UrlKt.FRAGMENT_ENCODE_SET, "send", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "Lokio/ByteString;", "close", "code", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "Factory", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface WebSocket {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lokhttp3/WebSocket$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "newWebSocket", "Lokhttp3/WebSocket;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface Factory {
        WebSocket newWebSocket(Request r1, WebSocketListener r2);
    }

    void cancel();

    boolean close(int r1, String r2);

    long queueSize();

    Request request();

    boolean send(String r1);

    boolean send(ByteString r1);
}
