package retrofit2.converter.gson;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* loaded from: classes3.dex */
public final class GsonConverterFactory extends Converter.Factory {
    private final Gson gson;
    private final boolean streaming;

    private GsonConverterFactory(Gson r1, boolean r2) {
        this.gson = r1;
        this.streaming = r2;
    }

    public static GsonConverterFactory create(Gson r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("gson == null");
        return null;
    L4:
        return new GsonConverterFactory(r2, false);
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type r1, Annotation[] r2, Annotation[] r3, Retrofit r4) {
        TypeAdapter r12 = this.gson.getAdapter(TypeToken.get(r1));
        return new GsonRequestBodyConverter(this.gson, r12, this.streaming);
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type r1, Annotation[] r2, Retrofit r3) {
        TypeAdapter r12 = this.gson.getAdapter(TypeToken.get(r1));
        return new GsonResponseBodyConverter(this.gson, r12);
    }
}
