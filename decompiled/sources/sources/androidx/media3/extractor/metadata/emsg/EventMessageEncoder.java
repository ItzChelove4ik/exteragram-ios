package androidx.media3.extractor.metadata.emsg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class EventMessageEncoder {
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final DataOutputStream dataOutputStream;

    public EventMessageEncoder() {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream(512);
        this.byteArrayOutputStream = r0;
        this.dataOutputStream = new DataOutputStream(r0);
    }

    private static void writeNullTerminatedString(DataOutputStream r0, String r1) {
        r0.writeBytes(r1);
        r0.writeByte(0);
    }

    public byte[] encode(EventMessage r4) {
        this.byteArrayOutputStream.reset();
        writeNullTerminatedString(this.dataOutputStream, r4.schemeIdUri);     // Catch: IOException -> L9
        String r0 = r4.value;     // Catch: IOException -> L9
        if (r0 != null) goto L7;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        writeNullTerminatedString(this.dataOutputStream, r0);     // Catch: IOException -> L9
        this.dataOutputStream.writeLong(r4.durationMs);     // Catch: IOException -> L9
        this.dataOutputStream.writeLong(r4.id);     // Catch: IOException -> L9
        this.dataOutputStream.write(r4.messageData);     // Catch: IOException -> L9
        this.dataOutputStream.flush();     // Catch: IOException -> L9
        return this.byteArrayOutputStream.toByteArray();
    L9:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
