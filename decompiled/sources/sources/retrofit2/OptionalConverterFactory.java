package retrofit2;

import android.annotation.TargetApi;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import okhttp3.ResponseBody;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.Converter;

@TargetApi(24)
@IgnoreJRERequirement
/* loaded from: classes3.dex */
public final class OptionalConverterFactory extends Converter.Factory {

    @IgnoreJRERequirement
    /* loaded from: classes7.dex */
    public static final class OptionalConverter<T> implements Converter<ResponseBody, Optional<T>> {
        private final Converter<ResponseBody, T> delegate;

        public OptionalConverter(Converter<ResponseBody, T> r1) {
            this.delegate = r1;
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ Object convert(ResponseBody r1) {
            return convert2(r1);
        }

        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public Optional<T> convert2(ResponseBody r1) {
            return Optional.ofNullable(this.delegate.convert(r1));
        }
    }

    public OptionalConverterFactory() {
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type r2, Annotation[] r3, Retrofit r4) {
        if (Converter.Factory.getRawType(r2) == Optional.class) goto L7;
        return null;
    L7:
        return new OptionalConverter(r4.responseBodyConverter(Converter.Factory.getParameterUpperBound(0, (ParameterizedType) r2), r3));
    }
}
