package retrofit2;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import okhttp3.Request;
import retrofit2.CallAdapter;

/* loaded from: classes3.dex */
final class DefaultCallAdapterFactory extends CallAdapter.Factory {
    private final Executor callbackExecutor;

    /* renamed from: retrofit2.DefaultCallAdapterFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements CallAdapter<Object, Call<?>> {
        final /* synthetic */ DefaultCallAdapterFactory this$0;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Type val$responseType;

        public AnonymousClass1(DefaultCallAdapterFactory r1, Type r2, Executor r3) {
            this.this$0 = r1;
            this.val$responseType = r2;
            this.val$executor = r3;
        }

        @Override // retrofit2.CallAdapter
        public /* bridge */ /* synthetic */ Call<?> adapt(Call<Object> r1) {
            return adapt2(r1);
        }

        @Override // retrofit2.CallAdapter
        /* renamed from: adapt, reason: avoid collision after fix types in other method */
        public Call<?> adapt2(Call<Object> r2) {
            Executor r1 = this.val$executor;
            if (r1 != null) goto L6;
            return r2;
        L6:
            return new ExecutorCallbackCall(r1, r2);
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.val$responseType;
        }
    }

    /* loaded from: classes7.dex */
    public static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        /* renamed from: retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1, reason: invalid class name */
        public class AnonymousClass1 implements Callback<T> {
            final /* synthetic */ ExecutorCallbackCall this$0;
            final /* synthetic */ Callback val$callback;

            /* renamed from: $r8$lambda$XaT8dIL-r1IHUZ1ZMLM8dsl5Tm8, reason: not valid java name */
            public static /* synthetic */ void m22454$r8$lambda$XaT8dILr1IHUZ1ZMLM8dsl5Tm8(AnonymousClass1 r0, Callback r1, Throwable r2) {
                r1.onFailure(r0.this$0, r2);
            }

            /* renamed from: $r8$lambda$iOm3EORbLdO-gznz1K1wf7z_Y7E, reason: not valid java name */
            public static /* synthetic */ void m22455$r8$lambda$iOm3EORbLdOgznz1K1wf7z_Y7E(AnonymousClass1 r1, Callback r2, Response r3) {
                boolean r0 = r1.this$0.delegate.isCanceled();
                ExecutorCallbackCall r12 = r1.this$0;
                if (r0 == false) goto L6;
                r2.onFailure(r12, new IOException("Canceled"));
                return;
            L6:
                r2.onResponse(r12, r3);
            }

            public AnonymousClass1(ExecutorCallbackCall r1, Callback r2) {
                this.this$0 = r1;
                this.val$callback = r2;
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> r3, Throwable r4) {
                this.this$0.callbackExecutor.execute(new DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda1(this, this.val$callback, r4));
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<T> r3, Response<T> r4) {
                this.this$0.callbackExecutor.execute(new DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda0(this, this.val$callback, r4));
            }
        }

        public ExecutorCallbackCall(Executor r1, Call<T> r2) {
            this.callbackExecutor = r1;
            this.delegate = r2;
        }

        @Override // retrofit2.Call
        public void cancel() {
            this.delegate.cancel();
        }

        public /* bridge */ /* synthetic */ Object clone() {
            return mo22453clone();
        }

        @Override // retrofit2.Call
        /* renamed from: clone, reason: collision with other method in class */
        public Call<T> mo22453clone() {
            return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.mo22453clone());
        }

        @Override // retrofit2.Call
        public void enqueue(Callback<T> r3) {
            Objects.requireNonNull(r3, "callback == null");
            this.delegate.enqueue(new AnonymousClass1(this, r3));
        }

        @Override // retrofit2.Call
        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        @Override // retrofit2.Call
        public Request request() {
            return this.delegate.request();
        }
    }

    public DefaultCallAdapterFactory(Executor r1) {
        this.callbackExecutor = r1;
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?, ?> get(Type r3, Annotation[] r4, Retrofit r5) {
        Executor r1 = null;
        if (CallAdapter.Factory.getRawType(r3) == Call.class) goto L6;
        return null;
    L6:
        if ((r3 instanceof ParameterizedType) == false) goto L13;
        Type r32 = Utils.getParameterUpperBound(0, (ParameterizedType) r3);
        if (Utils.isAnnotationPresent(r4, SkipCallbackExecutor.class) == true) goto L12;
        r1 = this.callbackExecutor;
    L12:
        return new AnonymousClass1(this, r32, r1);
    L13:
        f$$ExternalSyntheticBUOutline1.m("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        return null;
    }
}
