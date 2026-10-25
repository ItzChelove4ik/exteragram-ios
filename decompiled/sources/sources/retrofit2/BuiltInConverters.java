package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import kotlin.Unit;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.http.Streaming;

/* loaded from: classes3.dex */
final class BuiltInConverters extends Converter.Factory {

    /* loaded from: classes7.dex */
    public static final class BufferingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {
        static final BufferingResponseBodyConverter INSTANCE = null;

        static {
            INSTANCE = new BufferingResponseBodyConverter();
        }

        public BufferingResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ ResponseBody convert(ResponseBody r1) {
            return convert2(r1);
        }

        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public ResponseBody convert2(ResponseBody r1) {
            ResponseBody r0 = Utils.buffer(r1);     // Catch: Throwable -> L5
            r1.close();
            return r0;
        L5:
            th = move-exception;
            r1.close();
            throw th;
        }
    }

    /* loaded from: classes7.dex */
    public static final class RequestBodyConverter implements Converter<RequestBody, RequestBody> {
        static final RequestBodyConverter INSTANCE = null;

        static {
            INSTANCE = new RequestBodyConverter();
        }

        public RequestBodyConverter() {
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ RequestBody convert(RequestBody r1) {
            return convert2(r1);
        }

        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public RequestBody convert2(RequestBody r1) {
            return r1;
        }
    }

    /* loaded from: classes7.dex */
    public static final class StreamingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {
        static final StreamingResponseBodyConverter INSTANCE = null;

        static {
            INSTANCE = new StreamingResponseBodyConverter();
        }

        public StreamingResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ ResponseBody convert(ResponseBody r1) {
            return convert2(r1);
        }

        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public ResponseBody convert2(ResponseBody r1) {
            return r1;
        }
    }

    /* loaded from: classes7.dex */
    public static final class ToStringConverter implements Converter<Object, String> {
        static final ToStringConverter INSTANCE = null;

        static {
            INSTANCE = new ToStringConverter();
        }

        public ToStringConverter() {
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ String convert(Object r1) {
            return convert2(r1);
        }

        @Override // retrofit2.Converter
        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public String convert2(Object r1) {
            return r1.toString();
        }
    }

    /* loaded from: classes7.dex */
    public static final class UnitResponseBodyConverter implements Converter<ResponseBody, Unit> {
        static final UnitResponseBodyConverter INSTANCE = null;

        static {
            INSTANCE = new UnitResponseBodyConverter();
        }

        public UnitResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ Unit convert(ResponseBody r1) {
            return convert2(r1);
        }

        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public Unit convert2(ResponseBody r1) {
            r1.close();
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes7.dex */
    public static final class VoidResponseBodyConverter implements Converter<ResponseBody, Void> {
        static final VoidResponseBodyConverter INSTANCE = null;

        static {
            INSTANCE = new VoidResponseBodyConverter();
        }

        public VoidResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ Void convert(ResponseBody r1) {
            return convert2(r1);
        }

        /* renamed from: convert, reason: avoid collision after fix types in other method */
        public Void convert2(ResponseBody r1) {
            r1.close();
            return null;
        }
    }

    public BuiltInConverters() {
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type r1, Annotation[] r2, Annotation[] r3, Retrofit r4) {
        if (RequestBody.class.isAssignableFrom(Utils.getRawType(r1)) == true) goto L5;
        return null;
    L5:
        return RequestBodyConverter.INSTANCE;
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type r1, Annotation[] r2, Retrofit r3) {
        if (r1 != ResponseBody.class) goto L11;
        if (Utils.isAnnotationPresent(r2, Streaming.class) == false) goto L9;
        return StreamingResponseBodyConverter.INSTANCE;
    L9:
        return BufferingResponseBodyConverter.INSTANCE;
    L11:
        if (r1 != Void.class) goto L15;
        return VoidResponseBodyConverter.INSTANCE;
    L15:
        if (Utils.isUnit(r1) == true) goto L17;
        return null;
    L17:
        return UnitResponseBodyConverter.INSTANCE;
    }
}
