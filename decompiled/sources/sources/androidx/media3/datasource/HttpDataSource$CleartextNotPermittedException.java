package androidx.media3.datasource;

import com.google.android.gms.cast.CastStatusCodes;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class HttpDataSource$CleartextNotPermittedException extends HttpDataSource$HttpDataSourceException {
    public HttpDataSource$CleartextNotPermittedException(IOException r7, DataSpec r8) {
        super("Cleartext HTTP traffic not permitted. See https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted", r7, r8, CastStatusCodes.MESSAGE_SEND_BUFFER_TOO_FULL, 1);
    }
}
