package okhttp3;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0016J \u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0016J\"\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u0017"}, d2 = {"Lokhttp3/WebSocketListener;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "onOpen", _UrlKt.FRAGMENT_ENCODE_SET, "webSocket", "Lokhttp3/WebSocket;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "onMessage", "text", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "Lokio/ByteString;", "onClosing", "code", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "onClosed", "onFailure", "t", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class WebSocketListener {
    public WebSocketListener() {
    }

    public void onClosed(WebSocket r1, int r2, String r3) {
    }

    public void onClosing(WebSocket r1, int r2, String r3) {
    }

    public void onFailure(WebSocket r1, Throwable r2, Response r3) {
    }

    public void onMessage(WebSocket r1, String r2) {
    }

    public void onMessage(WebSocket r1, ByteString r2) {
    }

    public void onOpen(WebSocket r1, Response r2) {
    }
}
