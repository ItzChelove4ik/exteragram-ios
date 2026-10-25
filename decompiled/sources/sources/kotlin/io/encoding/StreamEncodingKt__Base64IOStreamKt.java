package kotlin.io.encoding;

import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\u0004¨\u0006\u0006"}, d2 = {"decodingWith", "Ljava/io/InputStream;", "base64", "Lkotlin/io/encoding/Base64;", "encodingWith", "Ljava/io/OutputStream;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/encoding/StreamEncodingKt")
/* loaded from: classes5.dex */
class StreamEncodingKt__Base64IOStreamKt {
    public StreamEncodingKt__Base64IOStreamKt() {
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    public static final InputStream decodingWith(InputStream r1, Base64 r2) {
        return new DecodeInputStream(r1, r2);
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    public static final OutputStream encodingWith(OutputStream r1, Base64 r2) {
        return new EncodeOutputStream(r1, r2);
    }
}
