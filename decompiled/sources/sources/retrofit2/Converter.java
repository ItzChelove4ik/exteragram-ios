package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/* loaded from: classes3.dex */
public interface Converter<F, T> {

    public static abstract class Factory {
        public Factory() {
        }

        public static Type getParameterUpperBound(int r0, ParameterizedType r1) {
            return Utils.getParameterUpperBound(r0, r1);
        }

        public static Class<?> getRawType(Type r0) {
            return Utils.getRawType(r0);
        }

        public Converter<?, RequestBody> requestBodyConverter(Type r1, Annotation[] r2, Annotation[] r3, Retrofit r4) {
            return null;
        }

        public abstract Converter<ResponseBody, ?> responseBodyConverter(Type r1, Annotation[] r2, Retrofit r3);

        public Converter<?, String> stringConverter(Type r1, Annotation[] r2, Retrofit r3) {
            return null;
        }
    }

    T convert(F r1);
}
