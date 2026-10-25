package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/* loaded from: classes3.dex */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    public GsonResponseBodyConverter(Gson r1, TypeAdapter<T> r2) {
        this.gson = r1;
        this.adapter = r2;
    }

    @Override // retrofit2.Converter
    public /* bridge */ /* synthetic */ Object convert(ResponseBody r1) {
        return convert2(r1);
    }

    /* renamed from: convert, reason: avoid collision after fix types in other method */
    public T convert2(ResponseBody r3) {
        JsonReader r0 = this.gson.newJsonReader(r3.charStream());
        T r2 = this.adapter.read(r0);     // Catch: Throwable -> L9
        if (r0.peek() != JsonToken.END_DOCUMENT) goto L8;
        r3.close();
        return r2;
    L8:
        throw new JsonIOException("JSON document was not fully consumed.");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        r3.close();
        throw th;
    }
}
