package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/* loaded from: classes7.dex */
final class GsonStreamingRequestBody<T> extends RequestBody {
    private final TypeAdapter<T> adapter;
    private final Gson gson;
    private final T value;

    public GsonStreamingRequestBody(Gson r1, TypeAdapter<T> r2, T r3) {
        this.gson = r1;
        this.adapter = r2;
        this.value = r3;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return GsonRequestBodyConverter.MEDIA_TYPE;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink r3) {
        GsonRequestBodyConverter.writeJson(r3, this.gson, this.adapter, this.value);
    }
}
