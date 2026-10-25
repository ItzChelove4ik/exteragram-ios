package okhttp3;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"okhttp3/RequestBody$Companion$toRequestBody$3", "Lokhttp3/RequestBody;", "contentType", "Lokhttp3/MediaType;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "writeTo", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/BufferedSink;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RequestBody$Companion$toRequestBody$3 extends RequestBody {
    final /* synthetic */ int $byteCount;
    final /* synthetic */ MediaType $contentType;
    final /* synthetic */ int $offset;
    final /* synthetic */ byte[] $this_toRequestBody;

    public RequestBody$Companion$toRequestBody$3(MediaType r1, int r2, byte[] r3, int r4) {
        this.$contentType = r1;
        this.$byteCount = r2;
        this.$this_toRequestBody = r3;
        this.$offset = r4;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return this.$byteCount;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.$contentType;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink r3) {
        r3.write(this.$this_toRequestBody, this.$offset, this.$byteCount);
    }
}
