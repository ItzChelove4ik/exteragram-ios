package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private boolean active;
    private JsonValueObjectEncoderContext childContext;
    private final ObjectEncoder<Object> fallbackEncoder;
    private final boolean ignoreNullValues;
    private final JsonWriter jsonWriter;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    public JsonValueObjectEncoderContext(Writer r2, Map<Class<?>, ObjectEncoder<?>> r3, Map<Class<?>, ValueEncoder<?>> r4, ObjectEncoder<Object> r5, boolean r6) {
        this.childContext = null;
        this.active = true;
        this.jsonWriter = new JsonWriter(r2);
        this.objectEncoders = r3;
        this.valueEncoders = r4;
        this.fallbackEncoder = r5;
        this.ignoreNullValues = r6;
    }

    private boolean cannotBeInline(Object r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (r1.getClass().isArray() == false) goto L6;
        return true;
    L6:
        if ((r1 instanceof Collection) == false) goto L8;
        return true;
    L8:
        if ((r1 instanceof Date) == false) goto L10;
        return true;
    L10:
        if ((r1 instanceof Enum) == false) goto L12;
        return true;
    L12:
        if ((r1 instanceof Number) == true) goto L22;
        return false;
    L22:
        return true;
    }

    private JsonValueObjectEncoderContext internalAdd(String r2, Object r3) {
        maybeUnNest();
        this.jsonWriter.name(r2);
        if (r3 != null) goto L7;
        this.jsonWriter.nullValue();
        return this;
    L7:
        return add(r3, false);
    }

    private JsonValueObjectEncoderContext internalAddIgnoreNullValues(String r2, Object r3) {
        if (r3 != null) goto L4;
        return this;
    L4:
        maybeUnNest();
        this.jsonWriter.name(r2);
        return add(r3, false);
    }

    private void maybeUnNest() {
        if (this.active == false) goto L8;
        JsonValueObjectEncoderContext r0 = this.childContext;
        if (r0 == null) goto L10;
        r0.maybeUnNest();
        this.childContext.active = false;
        this.childContext = null;
        this.jsonWriter.endObject();
        return;
    L10:
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Parent context used since this context was created. Cannot use this context anymore.");
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r1, double r2) {
        return add(r1.getName(), r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r1, int r2) {
        return add(r1.getName(), r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r1, long r2) {
        return add(r1.getName(), r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r1, Object r2) {
        return add(r1.getName(), r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r1, boolean r2) {
        return add(r1.getName(), r2);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public /* bridge */ /* synthetic */ ValueEncoderContext add(String r1) {
        return add(r1);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public /* bridge */ /* synthetic */ ValueEncoderContext add(boolean r1) {
        return add(r1);
    }

    public JsonValueObjectEncoderContext add(double r2) {
        maybeUnNest();
        this.jsonWriter.value(r2);
        return this;
    }

    public JsonValueObjectEncoderContext add(int r4) {
        maybeUnNest();
        this.jsonWriter.value(r4);
        return this;
    }

    public JsonValueObjectEncoderContext add(long r2) {
        maybeUnNest();
        this.jsonWriter.value(r2);
        return this;
    }

    public JsonValueObjectEncoderContext add(Object r6, boolean r7) {
        if (r7 == true) goto L4;
    L11:
        if (r6 != null) goto L15;
        this.jsonWriter.nullValue();
        return this;
    L15:
        if ((r6 instanceof Number) == false) goto L18;
        this.jsonWriter.value((Number) r6);
        return this;
    L18:
        int r1 = 0;
        if (r6.getClass().isArray() == false) goto L55;
        if ((r6 instanceof byte[]) == true) goto L23;
        this.jsonWriter.beginArray();
        if ((r6 instanceof int[]) == false) goto L30;
        int[] r62 = (int[]) r6;
        int r72 = r62.length;
    L27:
        if (r1 >= r72) goto L52;
        this.jsonWriter.value(r62[r1]);
        r1 = r1 + 1;
    L52:
        this.jsonWriter.endArray();
        return this;
    L30:
        if ((r6 instanceof long[]) == false) goto L35;
        long[] r63 = (long[]) r6;
        int r73 = r63.length;
    L32:
        if (r1 >= r73) goto L52;
        add(r63[r1]);
        r1 = r1 + 1;
        goto L32
    L35:
        if ((r6 instanceof double[]) == false) goto L40;
        double[] r64 = (double[]) r6;
        int r74 = r64.length;
    L37:
        if (r1 >= r74) goto L52;
        this.jsonWriter.value(r64[r1]);
        r1 = r1 + 1;
        goto L37
    L40:
        if ((r6 instanceof boolean[]) == false) goto L45;
        boolean[] r65 = (boolean[]) r6;
        int r75 = r65.length;
    L42:
        if (r1 >= r75) goto L52;
        this.jsonWriter.value(r65[r1]);
        r1 = r1 + 1;
        goto L42
    L45:
        if ((r6 instanceof Number[]) == false) goto L49;
        Number[] r66 = (Number[]) r6;
        int r76 = r66.length;
        int r0 = 0;
    L47:
        if (r0 >= r76) goto L52;
        add(r66[r0], false);
        r0 = r0 + 1;
        goto L47
    L49:
        Object[] r67 = (Object[]) r6;
        int r77 = r67.length;
        int r02 = 0;
    L50:
        if (r02 >= r77) goto L52;
        add(r67[r02], false);
        r02 = r02 + 1;
        goto L50
    L23:
        return add((byte[]) r6);
    L55:
        if ((r6 instanceof Collection) == false) goto L63;
        this.jsonWriter.beginArray();
        Iterator r68 = ((Collection) r6).iterator();
    L58:
        if (r68.hasNext() == false) goto L60;
        add(r68.next(), false);
        goto L58
    L60:
        this.jsonWriter.endArray();
        return this;
    L63:
        if ((r6 instanceof Map) == false) goto L75;
        this.jsonWriter.beginObject();
        Iterator r69 = ((Map) r6).entrySet().iterator();
    L66:
        if (r69.hasNext() == false) goto L73;
        Map.Entry r78 = (Map.Entry) r69.next();
        Object r03 = r78.getKey();
        add((String) r03, r78.getValue());     // Catch: ClassCastException -> L70
    L70:
        e = move-exception;
        throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", new Object[]{r03, r03.getClass()}), e);
    L73:
        this.jsonWriter.endObject();
        return this;
    L75:
        ObjectEncoder<?> r04 = this.objectEncoders.get(r6.getClass());
        if (r04 != null) goto L78;
        ValueEncoder<?> r05 = this.valueEncoders.get(r6.getClass());
        if (r05 == null) goto L84;
        r05.encode(r6, this);
        return this;
    L84:
        if ((r6 instanceof Enum) == false) goto L92;
        if ((r6 instanceof NumberedEnum) == false) goto L89;
        add(((NumberedEnum) r6).getNumber());
        return this;
    L89:
        add(((Enum) r6).name());
        return this;
    L92:
        return doEncode(this.fallbackEncoder, r6, r7);
    L78:
        return doEncode(r04, r6, r7);
    L4:
        if (cannotBeInline(r6) == false) goto L11;
        if (r6 != null) goto L8;
        Class<?> r610 = null;
    L10:
        throw new EncodingException(String.format("%s cannot be encoded inline", new Object[]{r610}));
    L8:
        r610 = r6.getClass();
        goto L10
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(String r2) {
        maybeUnNest();
        this.jsonWriter.value(r2);
        return this;
    }

    public JsonValueObjectEncoderContext add(String r2, double r3) {
        maybeUnNest();
        this.jsonWriter.name(r2);
        return add(r3);
    }

    public JsonValueObjectEncoderContext add(String r2, int r3) {
        maybeUnNest();
        this.jsonWriter.name(r2);
        return add(r3);
    }

    public JsonValueObjectEncoderContext add(String r2, long r3) {
        maybeUnNest();
        this.jsonWriter.name(r2);
        return add(r3);
    }

    public JsonValueObjectEncoderContext add(String r2, Object r3) {
        if (this.ignoreNullValues == false) goto L7;
        return internalAddIgnoreNullValues(r2, r3);
    L7:
        return internalAdd(r2, r3);
    }

    public JsonValueObjectEncoderContext add(String r2, boolean r3) {
        maybeUnNest();
        this.jsonWriter.name(r2);
        return add(r3);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(boolean r2) {
        maybeUnNest();
        this.jsonWriter.value(r2);
        return this;
    }

    public JsonValueObjectEncoderContext add(byte[] r3) {
        maybeUnNest();
        JsonWriter r0 = this.jsonWriter;
        if (r3 != null) goto L6;
        r0.nullValue();
        return this;
    L6:
        r0.value(Base64.encodeToString(r3, 2));
        return this;
    }

    public void close() {
        maybeUnNest();
        this.jsonWriter.flush();
    }

    public JsonValueObjectEncoderContext doEncode(ObjectEncoder<Object> r2, Object r3, boolean r4) {
        if (r4 == true) goto L4;
        this.jsonWriter.beginObject();
    L4:
        r2.encode(r3, this);
        if (r4 == true) goto L7;
        this.jsonWriter.endObject();
    L7:
        return this;
    }
}
