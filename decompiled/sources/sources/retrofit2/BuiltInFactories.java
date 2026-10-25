package retrofit2;

import android.annotation.TargetApi;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/* loaded from: classes3.dex */
class BuiltInFactories {

    @TargetApi(24)
    public static final class Java8 extends BuiltInFactories {
        public Java8() {
        }

        @Override // retrofit2.BuiltInFactories
        public List<? extends CallAdapter.Factory> createDefaultCallAdapterFactories(Executor r3) {
            return Arrays.asList(new CallAdapter.Factory[]{new CompletableFutureCallAdapterFactory(), new DefaultCallAdapterFactory(r3)});
        }

        @Override // retrofit2.BuiltInFactories
        public List<? extends Converter.Factory> createDefaultConverterFactories() {
            return Collections.singletonList(new OptionalConverterFactory());
        }
    }

    public BuiltInFactories() {
    }

    public List<? extends CallAdapter.Factory> createDefaultCallAdapterFactories(Executor r1) {
        return Collections.singletonList(new DefaultCallAdapterFactory(r1));
    }

    public List<? extends Converter.Factory> createDefaultConverterFactories() {
        return Collections.EMPTY_LIST;
    }
}
