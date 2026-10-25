package retrofit2;

import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.BuiltInConverters;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/* loaded from: classes3.dex */
public final class Retrofit {
    final HttpUrl baseUrl;
    final List<CallAdapter.Factory> callAdapterFactories;
    final Call.Factory callFactory;
    final Executor callbackExecutor;
    final List<Converter.Factory> converterFactories;
    final int defaultCallAdapterFactoriesSize;
    final int defaultConverterFactoriesSize;
    private final ConcurrentHashMap<Method, Object> serviceMethodCache;
    final boolean validateEagerly;

    /* renamed from: retrofit2.Retrofit$1, reason: invalid class name */
    public class AnonymousClass1 implements InvocationHandler {
        private final Object[] emptyArgs;
        final /* synthetic */ Retrofit this$0;
        final /* synthetic */ Class val$service;

        public AnonymousClass1(Retrofit r1, Class r2) {
            this.this$0 = r1;
            this.val$service = r2;
            this.emptyArgs = new Object[0];
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object r3, Method r4, Object[] r5) {
            if (r4.getDeclaringClass() == Object.class) goto L5;
            if (r5 != null) goto L9;
            r5 = this.emptyArgs;
        L9:
            Reflection r0 = Platform.reflection;
            if (r0.isDefaultMethod(r4) == false) goto L14;
            return r0.invokeDefaultMethod(r4, this.val$service, r3, r5);
        L14:
            return this.this$0.loadServiceMethod(this.val$service, r4).invoke(r3, r5);
        L5:
            return r4.invoke(this, r5);
        }
    }

    public static final class Builder {
        private HttpUrl baseUrl;
        private final List<CallAdapter.Factory> callAdapterFactories;
        private Call.Factory callFactory;
        private Executor callbackExecutor;
        private final List<Converter.Factory> converterFactories;
        private boolean validateEagerly;

        public Builder() {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
        }

        public Builder addConverterFactory(Converter.Factory r3) {
            List<Converter.Factory> r0 = this.converterFactories;
            Objects.requireNonNull(r3, "factory == null");
            r0.add(r3);
            return this;
        }

        public Builder baseUrl(String r2) {
            Objects.requireNonNull(r2, "baseUrl == null");
            return baseUrl(HttpUrl.get(r2));
        }

        public Builder baseUrl(HttpUrl r3) {
            Objects.requireNonNull(r3, "baseUrl == null");
            if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r3.pathSegments().get(r0.size() - 1)) == false) goto L6;
            this.baseUrl = r3;
            return this;
        L6:
            Native$$ExternalSyntheticBUOutline5.m("baseUrl must end in /: ", r3);
            return null;
        }

        public Retrofit build() {
            if (this.baseUrl == null) goto L12;
            Call.Factory r0 = this.callFactory;
            if (r0 != null) goto L7;
            r0 = new OkHttpClient();
        L7:
            Call.Factory r2 = r0;
            Executor r02 = this.callbackExecutor;
            if (r02 != null) goto L10;
            r02 = Platform.callbackExecutor;
        L10:
            Executor r8 = r02;
            BuiltInFactories r03 = Platform.builtInFactories;
            ArrayList r1 = new ArrayList(this.callAdapterFactories);
            List<? extends CallAdapter.Factory> r3 = r03.createDefaultCallAdapterFactories(r8);
            r1.addAll(r3);
            List<? extends Converter.Factory> r04 = r03.createDefaultConverterFactories();
            int r5 = r04.size();
            ArrayList r4 = new ArrayList((this.converterFactories.size() + 1) + r5);
            r4.add(new BuiltInConverters());
            r4.addAll(this.converterFactories);
            r4.addAll(r04);
            return new Retrofit(r2, this.baseUrl, Collections.unmodifiableList(r4), r5, Collections.unmodifiableList(r1), r3.size(), r8, this.validateEagerly);
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("Base URL required.");
            return null;
        }

        public Builder callFactory(Call.Factory r2) {
            Objects.requireNonNull(r2, "factory == null");
            this.callFactory = r2;
            return this;
        }

        public Builder client(OkHttpClient r2) {
            Objects.requireNonNull(r2, "client == null");
            return callFactory(r2);
        }
    }

    public Retrofit(Call.Factory r2, HttpUrl r3, List<Converter.Factory> r4, int r5, List<CallAdapter.Factory> r6, int r7, Executor r8, boolean r9) {
        this.serviceMethodCache = new ConcurrentHashMap();
        this.callFactory = r2;
        this.baseUrl = r3;
        this.converterFactories = r4;
        this.defaultConverterFactoriesSize = r5;
        this.callAdapterFactories = r6;
        this.defaultCallAdapterFactoriesSize = r7;
        this.callbackExecutor = r8;
        this.validateEagerly = r9;
    }

    private void validateServiceInterface(Class<?> r7) {
        if (r7.isInterface() == false) goto L28;
        ArrayDeque r0 = new ArrayDeque(1);
        r0.add(r7);
    L6:
        if (r0.isEmpty() == true) goto L16;
        Class<?> r1 = (Class) r0.removeFirst();
        if (r1.getTypeParameters().length != 0) goto L9;
        Collections.addAll(r0, r1.getInterfaces());
        goto L6
    L9:
        StringBuilder r6 = new StringBuilder("Type parameters are unsupported on ");
        r6.append(r1.getName());
        if (r1 == r7) goto L13;
        r6.append(" which is an interface of ");
        r6.append(r7.getName());
    L13:
        throw new IllegalArgumentException(r6.toString());
    L16:
        if (this.validateEagerly == false) goto L27;
        Reflection r02 = Platform.reflection;
        Method[] r12 = r7.getDeclaredMethods();
        int r2 = r12.length;
        int r3 = 0;
    L18:
        if (r3 >= r2) goto L37;
        Method r4 = r12[r3];
        if (r02.isDefaultMethod(r4) == true) goto L26;
        if (Modifier.isStatic(r4.getModifiers()) == true) goto L26;
        if (r4.isSynthetic() == true) goto L26;
        loadServiceMethod(r7, r4);
    L26:
        r3 = r3 + 1;
        goto L18
    L37:
        return;
    L27:
        return;
    L28:
        f$$ExternalSyntheticBUOutline1.m("API declarations must be interfaces.");
    }

    public CallAdapter<?, ?> callAdapter(Type r2, Annotation[] r3) {
        return nextCallAdapter(null, r2, r3);
    }

    public <T> T create(Class<T> r4) {
        validateServiceInterface(r4);
        return (T) Proxy.newProxyInstance(r4.getClassLoader(), new Class[]{r4}, new AnonymousClass1(this, r4));
    }

    public ServiceMethod<?> loadServiceMethod(Class<?> r3, Method r4) {
    L2:
        Object r0 = this.serviceMethodCache.get(r4);
        if ((r0 instanceof ServiceMethod) == true) goto L5;
        if (r0 != null) goto L24;
        Object r1 = new Object();
        monitor-enter(r1);
        r0 = this.serviceMethodCache.putIfAbsent(r4, r1);     // Catch: Throwable -> L15
        if (r0 == null) goto L39;
        monitor-exit(r1);     // Catch: Throwable -> L15
        goto L24
    L39:
        ServiceMethod<?> r32 = ServiceMethod.parseAnnotations(this, r3, r4);     // Catch: Throwable -> L17
        this.serviceMethodCache.put(r4, r32);     // Catch: Throwable -> L15
        monitor-exit(r1);     // Catch: Throwable -> L15
        return r32;
    L17:
        th = move-exception;
        this.serviceMethodCache.remove(r4);     // Catch: Throwable -> L15
        throw th;     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    L24:
        monitor-enter(r0);
        Object r12 = this.serviceMethodCache.get(r4);     // Catch: Throwable -> L29
        if (r12 != null) goto L31;
        monitor-exit(r0);     // Catch: Throwable -> L29
        goto L2
    L31:
        ServiceMethod<?> r13 = (ServiceMethod) r12;     // Catch: Throwable -> L29
        monitor-exit(r0);     // Catch: Throwable -> L29
        return r13;
    L29:
        th = move-exception;
        throw th;
    L5:
        return (ServiceMethod) r0;
    }

    public CallAdapter<?, ?> nextCallAdapter(CallAdapter.Factory r5, Type r6, Annotation[] r7) {
        Objects.requireNonNull(r6, "returnType == null");
        Objects.requireNonNull(r7, "annotations == null");
        int r0 = this.callAdapterFactories.indexOf(r5) + 1;
        int r1 = this.callAdapterFactories.size();
        int r2 = r0;
    L3:
        if (r2 >= r1) goto L8;
        CallAdapter<?, ?> r3 = this.callAdapterFactories.get(r2).get(r6, r7, this);
        if (r3 != null) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L8:
        StringBuilder r72 = new StringBuilder("Could not locate call adapter for ");
        r72.append(r6);
        r72.append(".\n");
        if (r5 == null) goto L14;
        r72.append("  Skipped:");
        int r52 = 0;
    L11:
        if (r52 >= r0) goto L13;
        r72.append("\n   * ");
        r72.append(this.callAdapterFactories.get(r52).getClass().getName());
        r52 = r52 + 1;
        goto L11
    L13:
        r72.append('\n');
    L14:
        r72.append("  Tried:");
        int r53 = this.callAdapterFactories.size();
    L15:
        if (r0 >= r53) goto L18;
        r72.append("\n   * ");
        r72.append(this.callAdapterFactories.get(r0).getClass().getName());
        r0 = r0 + 1;
        goto L15
    L18:
        throw new IllegalArgumentException(r72.toString());
    }

    public <T> Converter<T, RequestBody> nextRequestBodyConverter(Converter.Factory r5, Type r6, Annotation[] r7, Annotation[] r8) {
        Objects.requireNonNull(r6, "type == null");
        Objects.requireNonNull(r7, "parameterAnnotations == null");
        Objects.requireNonNull(r8, "methodAnnotations == null");
        int r0 = this.converterFactories.indexOf(r5) + 1;
        int r1 = this.converterFactories.size();
        int r2 = r0;
    L3:
        if (r2 >= r1) goto L8;
        Converter<T, RequestBody> r3 = (Converter<T, RequestBody>) this.converterFactories.get(r2).requestBodyConverter(r6, r7, r8, this);
        if (r3 != null) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L8:
        StringBuilder r72 = new StringBuilder("Could not locate RequestBody converter for ");
        r72.append(r6);
        r72.append(".\n");
        if (r5 == null) goto L14;
        r72.append("  Skipped:");
        int r52 = 0;
    L11:
        if (r52 >= r0) goto L13;
        r72.append("\n   * ");
        r72.append(this.converterFactories.get(r52).getClass().getName());
        r52 = r52 + 1;
        goto L11
    L13:
        r72.append('\n');
    L14:
        r72.append("  Tried:");
        int r53 = this.converterFactories.size();
    L15:
        if (r0 >= r53) goto L18;
        r72.append("\n   * ");
        r72.append(this.converterFactories.get(r0).getClass().getName());
        r0 = r0 + 1;
        goto L15
    L18:
        throw new IllegalArgumentException(r72.toString());
    }

    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(Converter.Factory r5, Type r6, Annotation[] r7) {
        Objects.requireNonNull(r6, "type == null");
        Objects.requireNonNull(r7, "annotations == null");
        int r0 = this.converterFactories.indexOf(r5) + 1;
        int r1 = this.converterFactories.size();
        int r2 = r0;
    L3:
        if (r2 >= r1) goto L8;
        Converter<ResponseBody, T> r3 = (Converter<ResponseBody, T>) this.converterFactories.get(r2).responseBodyConverter(r6, r7, this);
        if (r3 != null) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L8:
        StringBuilder r72 = new StringBuilder("Could not locate ResponseBody converter for ");
        r72.append(r6);
        r72.append(".\n");
        if (r5 == null) goto L14;
        r72.append("  Skipped:");
        int r52 = 0;
    L11:
        if (r52 >= r0) goto L13;
        r72.append("\n   * ");
        r72.append(this.converterFactories.get(r52).getClass().getName());
        r52 = r52 + 1;
        goto L11
    L13:
        r72.append('\n');
    L14:
        r72.append("  Tried:");
        int r53 = this.converterFactories.size();
    L15:
        if (r0 >= r53) goto L18;
        r72.append("\n   * ");
        r72.append(this.converterFactories.get(r0).getClass().getName());
        r0 = r0 + 1;
        goto L15
    L18:
        throw new IllegalArgumentException(r72.toString());
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type r2, Annotation[] r3, Annotation[] r4) {
        return nextRequestBodyConverter(null, r2, r3, r4);
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type r2, Annotation[] r3) {
        return nextResponseBodyConverter(null, r2, r3);
    }

    public <T> Converter<T, String> stringConverter(Type r4, Annotation[] r5) {
        Objects.requireNonNull(r4, "type == null");
        Objects.requireNonNull(r5, "annotations == null");
        int r0 = this.converterFactories.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        Converter<T, String> r2 = (Converter<T, String>) this.converterFactories.get(r1).stringConverter(r4, r5, this);
        if (r2 != null) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L9:
        return BuiltInConverters.ToStringConverter.INSTANCE;
    }
}
