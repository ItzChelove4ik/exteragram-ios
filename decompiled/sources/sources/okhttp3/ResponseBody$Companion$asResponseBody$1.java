package okhttp3;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"okhttp3/ResponseBody$Companion$asResponseBody$1", "Lokhttp3/ResponseBody;", "contentType", "Lokhttp3/MediaType;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/BufferedSource;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ResponseBody$Companion$asResponseBody$1 extends ResponseBody {
    final /* synthetic */ long $contentLength;
    final /* synthetic */ MediaType $contentType;
    final /* synthetic */ BufferedSource $this_asResponseBody;

    public ResponseBody$Companion$asResponseBody$1(MediaType r1, long r2, BufferedSource r4) {
        this.$contentType = r1;
        this.$contentLength = r2;
        this.$this_asResponseBody = r4;
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.$contentLength;
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.$contentType;
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        return this.$this_asResponseBody;
    }
}
