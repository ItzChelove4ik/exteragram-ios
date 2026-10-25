package androidx.media3.datasource;

import com.google.android.gms.cast.CastStatusCodes;

/* loaded from: classes3.dex */
public final class HttpDataSource$InvalidContentTypeException extends HttpDataSource$HttpDataSourceException {
    public final String contentType;

    public HttpDataSource$InvalidContentTypeException(String r4, DataSpec r5) {
        super("Invalid content type: " + r4, r5, CastStatusCodes.NOT_ALLOWED, 1);
        this.contentType = r4;
    }
}
