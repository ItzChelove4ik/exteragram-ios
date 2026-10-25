package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import retrofit2.Converter;

/* loaded from: classes7.dex */
final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    static final MediaType MEDIA_TYPE = null;
    private final TypeAdapter<T> adapter;
    private final Gson gson;
    private final boolean streaming;

    static {
        MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");
    }

    public GsonRequestBodyConverter(Gson r1, TypeAdapter<T> r2, boolean r3) {
        this.gson = r1;
        this.adapter = r2;
        this.streaming = r3;
    }

    public static <T> void writeJson(BufferedSink r2, Gson r3, TypeAdapter<T> r4, T r5) {
        JsonWriter r22 = r3.newJsonWriter(new OutputStreamWriter(r2.outputStream(), StandardCharsets.UTF_8));
        r4.write(r22, r5);
        r22.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // retrofit2.Converter
    public /* bridge */ /* synthetic */ RequestBody convert(Object r1) {
        return convert2(r1);
    }

    @Override // retrofit2.Converter
    /* renamed from: convert, reason: avoid collision after fix types in other method */
    public RequestBody convert2(T r3) {
        if (this.streaming == true) goto L5;
        Buffer r0 = new Buffer();
        writeJson(r0, this.gson, this.adapter, r3);
        return RequestBody.create(MEDIA_TYPE, r0.readByteString());
    L5:
        return new GsonStreamingRequestBody(this.gson, this.adapter, r3);
    }
}
