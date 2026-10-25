package com.google.firebase.sessions.settings;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/google/firebase/sessions/settings/SessionConfigs.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/firebase/sessions/settings/SessionConfigs;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes.dex */
public /* synthetic */ class SessionConfigs$$serializer implements GeneratedSerializer<SessionConfigs> {
    public static final SessionConfigs$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        SessionConfigs$$serializer r0 = new SessionConfigs$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.google.firebase.sessions.settings.SessionConfigs", r0, 5);
        r1.addElement("sessionsEnabled", false);
        r1.addElement("sessionSamplingRate", false);
        r1.addElement("sessionTimeoutSeconds", false);
        r1.addElement("cacheDurationSeconds", false);
        r1.addElement("cacheUpdatedTimeSeconds", false);
        descriptor = r1;
    }

    private SessionConfigs$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        KSerializer<?> r6 = BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE);
        KSerializer<?> r0 = BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE);
        IntSerializer r1 = IntSerializer.INSTANCE;
        return new KSerializer[]{r6, r0, BuiltinSerializersKt.getNullable(r1), BuiltinSerializersKt.getNullable(r1), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SessionConfigs deserialize(Decoder r23) {
        SerialDescriptor r0 = descriptor;
        CompositeDecoder r1 = r23.beginStructure(r0);
        Boolean r8 = null;
        if (r1.decodeSequentially() == false) goto L5;
        Boolean r2 = (Boolean) r1.decodeNullableSerializableElement(r0, 0, BooleanSerializer.INSTANCE, null);
        Double r6 = (Double) r1.decodeNullableSerializableElement(r0, 1, DoubleSerializer.INSTANCE, null);
        IntSerializer r7 = IntSerializer.INSTANCE;
        Integer r5 = (Integer) r1.decodeNullableSerializableElement(r0, 2, r7, null);
        Boolean r16 = r2;
        Integer r19 = (Integer) r1.decodeNullableSerializableElement(r0, 3, r7, null);
        Long r20 = (Long) r1.decodeNullableSerializableElement(r0, 4, LongSerializer.INSTANCE, null);
        Integer r18 = r5;
        Double r17 = r6;
        int r15 = 31;
    L23:
        r1.endStructure(r0);
        return new SessionConfigs(r15, r16, r17, r18, r19, r20, null);
    L5:
        boolean r13 = true;
        int r22 = 0;
        Double r9 = null;
        Integer r10 = null;
        Integer r11 = null;
        Long r12 = null;
    L6:
        if (r13 == false) goto L22;
        int r14 = r1.decodeElementIndex(r0);
        if (r14 != (-1)) goto L9;
        r13 = false;
        goto L6
    L9:
        if (r14 != 0) goto L10;
        r8 = (Boolean) r1.decodeNullableSerializableElement(r0, 0, BooleanSerializer.INSTANCE, r8);
        r22 = r22 | 1;
        goto L6
    L10:
        if (r14 != 1) goto L11;
        r9 = (Double) r1.decodeNullableSerializableElement(r0, 1, DoubleSerializer.INSTANCE, r9);
        r22 = r22 | 2;
        goto L6
    L11:
        if (r14 != 2) goto L12;
        r10 = (Integer) r1.decodeNullableSerializableElement(r0, 2, IntSerializer.INSTANCE, r10);
        r22 = r22 | 4;
        goto L6
    L12:
        if (r14 != 3) goto L13;
        r11 = (Integer) r1.decodeNullableSerializableElement(r0, 3, IntSerializer.INSTANCE, r11);
        r22 = r22 | 8;
        goto L6
    L13:
        if (r14 != 4) goto L16;
        r12 = (Long) r1.decodeNullableSerializableElement(r0, 4, LongSerializer.INSTANCE, r12);
        r22 = r22 | 16;
        goto L6
    L16:
        throw new UnknownFieldException(r14);
    L22:
        r15 = r22;
        r16 = r8;
        r17 = r9;
        r18 = r10;
        r19 = r11;
        r20 = r12;
        goto L23
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder r1) {
        return deserialize(r1);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(Encoder r1, SessionConfigs r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        SessionConfigs.write$Self$com_google_firebase_firebase_sessions(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (SessionConfigs) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
