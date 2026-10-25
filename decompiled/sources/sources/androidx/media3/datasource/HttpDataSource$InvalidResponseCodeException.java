package androidx.media3.datasource;

import com.google.android.gms.cast.CastStatusCodes;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class HttpDataSource$InvalidResponseCodeException extends HttpDataSource$HttpDataSourceException {
    public final Map<String, List<String>> headerFields;
    public final byte[] responseBody;
    public final int responseCode;
    public final String responseMessage;

    public HttpDataSource$InvalidResponseCodeException(int r9, String r10, IOException r11, Map<String, List<String>> r12, DataSpec r13, byte[] r14) {
        super("Response code: " + r9, r11, r13, CastStatusCodes.APPLICATION_NOT_FOUND, 1);
        this.responseCode = r9;
        this.responseMessage = r10;
        this.headerFields = r12;
        this.responseBody = r14;
    }
}
