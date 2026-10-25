package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¨\u0006\u0005"}, d2 = {"retryTlsHandshake", _UrlKt.FRAGMENT_ENCODE_SET, "e", "Ljava/io/IOException;", "Lokio/IOException;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RetryTlsHandshakeKt {
    public static final boolean retryTlsHandshake(IOException r2) {
        if ((r2 instanceof ProtocolException) == false) goto L6;
        return false;
    L6:
        if ((r2 instanceof InterruptedIOException) == false) goto L9;
        return false;
    L9:
        if ((r2 instanceof SSLHandshakeException) == false) goto L14;
        if ((r2.getCause() instanceof CertificateException) == false) goto L14;
        return false;
    L14:
        if ((r2 instanceof SSLPeerUnverifiedException) == false) goto L17;
        return false;
    L17:
        if ((r2 instanceof SSLException) == false) goto L20;
        return true;
    L20:
        return false;
    }
}
