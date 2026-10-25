package retrofit2;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* loaded from: classes3.dex */
abstract class ParameterHandler<T> {

    /* renamed from: retrofit2.ParameterHandler$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ParameterHandler<Iterable<T>> {
        final /* synthetic */ ParameterHandler this$0;

        public AnonymousClass1(ParameterHandler r1) {
            this.this$0 = r1;
        }

        public void apply(RequestBuilder r3, Iterable<T> r4) {
            if (r4 == null) goto L8;
            Iterator<T> r42 = r4.iterator();
        L6:
            if (r42.hasNext() == false) goto L10;
            T r0 = r42.next();
            this.this$0.apply(r3, r0);
            goto L6
        L10:
            return;
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, Object r2) {
            apply(r1, (Iterable) r2);
        }
    }

    /* renamed from: retrofit2.ParameterHandler$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends ParameterHandler<Object> {
        final /* synthetic */ ParameterHandler this$0;

        public AnonymousClass2(ParameterHandler r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r5, Object r6) {
            if (r6 == null) goto L7;
            int r0 = Array.getLength(r6);
            int r1 = 0;
        L5:
            if (r1 >= r0) goto L9;
            this.this$0.apply(r5, Array.get(r6, r1));
            r1 = r1 + 1;
            goto L5
        L9:
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Method method;
        private final int p;

        public Body(Method r1, int r2, Converter<T, RequestBody> r3) {
            this.method = r1;
            this.p = r2;
            this.converter = r3;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r5, T r6) {
            if (r6 == null) goto L11;
            r5.setBody(this.converter.convert(r6));
            return;
        L7:
            e = move-exception;
            throw Utils.parameterError(this.method, e, this.p, "Unable to convert " + r6 + " to RequestBody", new Object[0]);
        L11:
            throw Utils.parameterError(this.method, this.p, "Body parameter value must not be null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        public Field(String r2, Converter<T, String> r3, boolean r4) {
            Objects.requireNonNull(r2, "name == null");
            this.name = r2;
            this.valueConverter = r3;
            this.encoded = r4;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r2, T r3) {
            if (r3 == null) goto L9;
            String r32 = this.valueConverter.convert(r3);
            if (r32 != null) goto L7;
            return;
        L7:
            r2.addFormField(this.name, r32, this.encoded);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;
        private final int p;
        private final Converter<T, String> valueConverter;

        public FieldMap(Method r1, int r2, Converter<T, String> r3, boolean r4) {
            this.method = r1;
            this.p = r2;
            this.valueConverter = r3;
            this.encoded = r4;
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, Object r2) {
            apply(r1, (Map) r2);
        }

        public void apply(RequestBuilder r7, Map<String, T> r8) {
            if (r8 == null) goto L22;
            Iterator<Map.Entry<String, T>> r82 = r8.entrySet().iterator();
        L6:
            if (r82.hasNext() == false) goto L20;
            Map.Entry<String, T> r1 = r82.next();
            String r2 = r1.getKey();
            if (r2 == null) goto L19;
            T r12 = r1.getValue();
            if (r12 == null) goto L17;
            String r4 = this.valueConverter.convert(r12);
            if (r4 == null) goto L15;
            r7.addFormField(r2, r4, this.encoded);
            goto L6
        L15:
            throw Utils.parameterError(this.method, this.p, "Field map value '" + r12 + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + r2 + "'.", new Object[0]);
        L17:
            throw Utils.parameterError(this.method, this.p, "Field map contained null value for key '" + r2 + "'.", new Object[0]);
        L19:
            throw Utils.parameterError(this.method, this.p, "Field map contained null key.", new Object[0]);
        L20:
            return;
        L22:
            throw Utils.parameterError(this.method, this.p, "Field map was null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Header<T> extends ParameterHandler<T> {
        private final boolean allowUnsafeNonAsciiValues;
        private final String name;
        private final Converter<T, String> valueConverter;

        public Header(String r2, Converter<T, String> r3, boolean r4) {
            Objects.requireNonNull(r2, "name == null");
            this.name = r2;
            this.valueConverter = r3;
            this.allowUnsafeNonAsciiValues = r4;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r2, T r3) {
            if (r3 == null) goto L9;
            String r32 = this.valueConverter.convert(r3);
            if (r32 != null) goto L7;
            return;
        L7:
            r2.addHeader(this.name, r32, this.allowUnsafeNonAsciiValues);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean allowUnsafeNonAsciiValues;
        private final Method method;
        private final int p;
        private final Converter<T, String> valueConverter;

        public HeaderMap(Method r1, int r2, Converter<T, String> r3, boolean r4) {
            this.method = r1;
            this.p = r2;
            this.valueConverter = r3;
            this.allowUnsafeNonAsciiValues = r4;
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, Object r2) {
            apply(r1, (Map) r2);
        }

        public void apply(RequestBuilder r5, Map<String, T> r6) {
            if (r6 == null) goto L18;
            Iterator<Map.Entry<String, T>> r62 = r6.entrySet().iterator();
        L6:
            if (r62.hasNext() == false) goto L16;
            Map.Entry<String, T> r1 = r62.next();
            String r2 = r1.getKey();
            if (r2 == null) goto L15;
            T r12 = r1.getValue();
            if (r12 == null) goto L13;
            r5.addHeader(r2, this.valueConverter.convert(r12), this.allowUnsafeNonAsciiValues);
            goto L6
        L13:
            throw Utils.parameterError(this.method, this.p, "Header map contained null value for key '" + r2 + "'.", new Object[0]);
        L15:
            throw Utils.parameterError(this.method, this.p, "Header map contained null key.", new Object[0]);
        L16:
            return;
        L18:
            throw Utils.parameterError(this.method, this.p, "Header map was null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Headers extends ParameterHandler<okhttp3.Headers> {
        private final Method method;
        private final int p;

        public Headers(Method r1, int r2) {
            this.method = r1;
            this.p = r2;
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, okhttp3.Headers r2) {
            apply2(r1, r2);
        }

        /* renamed from: apply, reason: avoid collision after fix types in other method */
        public void apply2(RequestBuilder r2, okhttp3.Headers r3) {
            if (r3 == null) goto L6;
            r2.addHeaders(r3);
            return;
        L6:
            throw Utils.parameterError(this.method, this.p, "Headers parameter must not be null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final okhttp3.Headers headers;
        private final Method method;
        private final int p;

        public Part(Method r1, int r2, okhttp3.Headers r3, Converter<T, RequestBody> r4) {
            this.method = r1;
            this.p = r2;
            this.headers = r3;
            this.converter = r4;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r4, T r5) {
            if (r5 != null) goto L10;
            return;
        L10:
            r4.addPart(this.headers, this.converter.convert(r5));
            return;
        L7:
            e = move-exception;
            throw Utils.parameterError(this.method, this.p, "Unable to convert " + r5 + " to RequestBody", new Object[]{e});
        }
    }

    /* loaded from: classes7.dex */
    public static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final Method method;
        private final int p;
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;

        public PartMap(Method r1, int r2, Converter<T, RequestBody> r3, String r4) {
            this.method = r1;
            this.p = r2;
            this.valueConverter = r3;
            this.transferEncoding = r4;
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, Object r2) {
            apply(r1, (Map) r2);
        }

        public void apply(RequestBuilder r7, Map<String, T> r8) {
            if (r8 == null) goto L18;
            Iterator<Map.Entry<String, T>> r82 = r8.entrySet().iterator();
        L6:
            if (r82.hasNext() == false) goto L16;
            Map.Entry<String, T> r1 = r82.next();
            String r2 = r1.getKey();
            if (r2 == null) goto L15;
            T r12 = r1.getValue();
            if (r12 == null) goto L13;
            r7.addPart(okhttp3.Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + r2 + "\"", "Content-Transfer-Encoding", this.transferEncoding}), this.valueConverter.convert(r12));
            goto L6
        L13:
            throw Utils.parameterError(this.method, this.p, "Part map contained null value for key '" + r2 + "'.", new Object[0]);
        L15:
            throw Utils.parameterError(this.method, this.p, "Part map contained null key.", new Object[0]);
        L16:
            return;
        L18:
            throw Utils.parameterError(this.method, this.p, "Part map was null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Method method;
        private final String name;
        private final int p;
        private final Converter<T, String> valueConverter;

        public Path(Method r1, int r2, String r3, Converter<T, String> r4, boolean r5) {
            this.method = r1;
            this.p = r2;
            Objects.requireNonNull(r3, "name == null");
            this.name = r3;
            this.valueConverter = r4;
            this.encoded = r5;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r3, T r4) {
            if (r4 == null) goto L6;
            r3.addPathParam(this.name, this.valueConverter.convert(r4), this.encoded);
            return;
        L6:
            throw Utils.parameterError(this.method, this.p, "Path parameter \"" + this.name + "\" value must not be null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        public Query(String r2, Converter<T, String> r3, boolean r4) {
            Objects.requireNonNull(r2, "name == null");
            this.name = r2;
            this.valueConverter = r3;
            this.encoded = r4;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r2, T r3) {
            if (r3 == null) goto L9;
            String r32 = this.valueConverter.convert(r3);
            if (r32 != null) goto L7;
            return;
        L7:
            r2.addQueryParam(this.name, r32, this.encoded);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;
        private final int p;
        private final Converter<T, String> valueConverter;

        public QueryMap(Method r1, int r2, Converter<T, String> r3, boolean r4) {
            this.method = r1;
            this.p = r2;
            this.valueConverter = r3;
            this.encoded = r4;
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, Object r2) {
            apply(r1, (Map) r2);
        }

        public void apply(RequestBuilder r7, Map<String, T> r8) {
            if (r8 == null) goto L22;
            Iterator<Map.Entry<String, T>> r82 = r8.entrySet().iterator();
        L6:
            if (r82.hasNext() == false) goto L20;
            Map.Entry<String, T> r1 = r82.next();
            String r2 = r1.getKey();
            if (r2 == null) goto L19;
            T r12 = r1.getValue();
            if (r12 == null) goto L17;
            String r4 = this.valueConverter.convert(r12);
            if (r4 == null) goto L15;
            r7.addQueryParam(r2, r4, this.encoded);
            goto L6
        L15:
            throw Utils.parameterError(this.method, this.p, "Query map value '" + r12 + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + r2 + "'.", new Object[0]);
        L17:
            throw Utils.parameterError(this.method, this.p, "Query map contained null value for key '" + r2 + "'.", new Object[0]);
        L19:
            throw Utils.parameterError(this.method, this.p, "Query map contained null key.", new Object[0]);
        L20:
            return;
        L22:
            throw Utils.parameterError(this.method, this.p, "Query map was null", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class QueryName<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Converter<T, String> nameConverter;

        public QueryName(Converter<T, String> r1, boolean r2) {
            this.nameConverter = r1;
            this.encoded = r2;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r2, T r3) {
            if (r3 != null) goto L4;
            return;
        L4:
            r2.addQueryParam(this.nameConverter.convert(r3), null, this.encoded);
        }
    }

    /* loaded from: classes7.dex */
    public static final class RawPart extends ParameterHandler<MultipartBody.Part> {
        static final RawPart INSTANCE = null;

        static {
            INSTANCE = new RawPart();
        }

        private RawPart() {
        }

        @Override // retrofit2.ParameterHandler
        public /* bridge */ /* synthetic */ void apply(RequestBuilder r1, MultipartBody.Part r2) {
            apply2(r1, r2);
        }

        /* renamed from: apply, reason: avoid collision after fix types in other method */
        public void apply2(RequestBuilder r1, MultipartBody.Part r2) {
            if (r2 == null) goto L5;
            r1.addPart(r2);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static final class RelativeUrl extends ParameterHandler<Object> {
        private final Method method;
        private final int p;

        public RelativeUrl(Method r1, int r2) {
            this.method = r1;
            this.p = r2;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r2, Object r3) {
            if (r3 == null) goto L6;
            r2.setRelativeUrl(r3);
            return;
        L6:
            throw Utils.parameterError(this.method, this.p, "@Url parameter is null.", new Object[0]);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Tag<T> extends ParameterHandler<T> {
        final Class<T> cls;

        public Tag(Class<T> r1) {
            this.cls = r1;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder r1, T r2) {
            r1.addTag(this.cls, r2);
        }
    }

    public ParameterHandler() {
    }

    public abstract void apply(RequestBuilder r1, T r2);

    public final ParameterHandler<Object> array() {
        return new AnonymousClass2(this);
    }

    public final ParameterHandler<Iterable<T>> iterable() {
        return new AnonymousClass1(this);
    }
}
