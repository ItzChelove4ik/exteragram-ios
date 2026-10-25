package com.google.firebase.encoders.json;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import j$.util.DesugarTimeZone;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public final class JsonDataEncoderBuilder implements EncoderConfig<JsonDataEncoderBuilder> {
    private static final ValueEncoder<Boolean> BOOLEAN_ENCODER = null;
    private static final ObjectEncoder<Object> DEFAULT_FALLBACK_ENCODER = null;
    private static final ValueEncoder<String> STRING_ENCODER = null;
    private static final TimestampEncoder TIMESTAMP_ENCODER = null;
    private ObjectEncoder<Object> fallbackEncoder;
    private boolean ignoreNullValues;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    /* renamed from: com.google.firebase.encoders.json.JsonDataEncoderBuilder$1, reason: invalid class name */
    public class AnonymousClass1 implements DataEncoder {
        final /* synthetic */ JsonDataEncoderBuilder this$0;

        public AnonymousClass1(JsonDataEncoderBuilder r1) {
            this.this$0 = r1;
        }

        @Override // com.google.firebase.encoders.DataEncoder
        public String encode(Object r2) {
            StringWriter r0 = new StringWriter();
            encode(r2, r0);     // Catch: IOException -> L6
        L5:
            return r0.toString();
        }

        @Override // com.google.firebase.encoders.DataEncoder
        public void encode(Object r7, Writer r8) {
            JsonValueObjectEncoderContext r0 = new JsonValueObjectEncoderContext(r8, JsonDataEncoderBuilder.access$100(this.this$0), JsonDataEncoderBuilder.access$200(this.this$0), JsonDataEncoderBuilder.access$300(this.this$0), JsonDataEncoderBuilder.access$400(this.this$0));
            r0.add(r7, false);
            r0.close();
        }
    }

    public static final class TimestampEncoder implements ValueEncoder<Date> {
        private static final DateFormat rfc339 = null;

        static {
            SimpleDateFormat r0 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            rfc339 = r0;
            r0.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        }

        private TimestampEncoder() {
        }

        public /* synthetic */ TimestampEncoder(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.firebase.encoders.ValueEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((Date) r1, (ValueEncoderContext) r2);
        }

        public void encode(Date r1, ValueEncoderContext r2) {
            r2.add(rfc339.format(r1));
        }
    }

    public static /* synthetic */ void $r8$lambda$QQBApgngvprMtuXnTsuy4oq0rmA(Boolean r0, ValueEncoderContext r1) {
        r1.add(r0.booleanValue());
    }

    public static /* synthetic */ void $r8$lambda$bnoichjKU0v4bRcIU8iyVmXGe3M(Object r2, ObjectEncoderContext r3) {
        throw new EncodingException("Couldn't find encoder for type " + r2.getClass().getCanonicalName());
    }

    /* renamed from: $r8$lambda$d_jsZwDTLPk8-ISgWdlcpn6aj10, reason: not valid java name */
    public static /* synthetic */ void m1951$r8$lambda$d_jsZwDTLPk8ISgWdlcpn6aj10(String r0, ValueEncoderContext r1) {
        r1.add(r0);
    }

    static {
        DEFAULT_FALLBACK_ENCODER = new JsonDataEncoderBuilder$$ExternalSyntheticLambda0();
        STRING_ENCODER = new JsonDataEncoderBuilder$$ExternalSyntheticLambda1();
        BOOLEAN_ENCODER = new JsonDataEncoderBuilder$$ExternalSyntheticLambda2();
        TIMESTAMP_ENCODER = new TimestampEncoder(null);
    }

    public JsonDataEncoderBuilder() {
        this.objectEncoders = new HashMap();
        this.valueEncoders = new HashMap();
        this.fallbackEncoder = DEFAULT_FALLBACK_ENCODER;
        this.ignoreNullValues = false;
        registerEncoder(String.class, STRING_ENCODER);
        registerEncoder(Boolean.class, BOOLEAN_ENCODER);
        registerEncoder(Date.class, TIMESTAMP_ENCODER);
    }

    public static /* synthetic */ Map access$100(JsonDataEncoderBuilder r0) {
        return r0.objectEncoders;
    }

    public static /* synthetic */ Map access$200(JsonDataEncoderBuilder r0) {
        return r0.valueEncoders;
    }

    public static /* synthetic */ ObjectEncoder access$300(JsonDataEncoderBuilder r0) {
        return r0.fallbackEncoder;
    }

    public static /* synthetic */ boolean access$400(JsonDataEncoderBuilder r0) {
        return r0.ignoreNullValues;
    }

    public DataEncoder build() {
        return new AnonymousClass1(this);
    }

    public JsonDataEncoderBuilder configureWith(Configurator r1) {
        r1.configure(this);
        return this;
    }

    public JsonDataEncoderBuilder ignoreNullValues(boolean r1) {
        this.ignoreNullValues = r1;
        return this;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public /* bridge */ /* synthetic */ EncoderConfig registerEncoder(Class r1, ObjectEncoder r2) {
        return registerEncoder(r1, r2);
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public <T> JsonDataEncoderBuilder registerEncoder(Class<T> r2, ObjectEncoder<? super T> r3) {
        this.objectEncoders.put(r2, r3);
        this.valueEncoders.remove(r2);
        return this;
    }

    public <T> JsonDataEncoderBuilder registerEncoder(Class<T> r2, ValueEncoder<? super T> r3) {
        this.valueEncoders.put(r2, r3);
        this.objectEncoders.remove(r2);
        return this;
    }
}
