package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public interface CallAdapter<R, T> {

    public static abstract class Factory {
        public Factory() {
        }

        public static Type getParameterUpperBound(int r0, ParameterizedType r1) {
            return Utils.getParameterUpperBound(r0, r1);
        }

        public static Class<?> getRawType(Type r0) {
            return Utils.getRawType(r0);
        }

        public abstract CallAdapter<?, ?> get(Type r1, Annotation[] r2, Retrofit r3);
    }

    T adapt(Call<R> r1);

    Type responseType();
}
