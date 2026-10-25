package com.google.firebase.sessions;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/google/firebase/sessions/SessionData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/firebase/sessions/SessionData;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes.dex */
public /* synthetic */ class SessionData$$serializer implements GeneratedSerializer<SessionData> {
    public static final SessionData$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        SessionData$$serializer r0 = new SessionData$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.google.firebase.sessions.SessionData", r0, 3);
        r1.addElement("sessionDetails", false);
        r1.addElement("backgroundTime", true);
        r1.addElement("processDataMap", true);
        descriptor = r1;
    }

    private SessionData$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        Lazy[] r5 = SessionData.access$get$childSerializers$cp();
        return new KSerializer[]{SessionDetails$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(Time$$serializer.INSTANCE), BuiltinSerializersKt.getNullable((KSerializer) r5[2].getValue())};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SessionData deserialize(Decoder r18) {
        SerialDescriptor r0 = descriptor;
        CompositeDecoder r1 = r18.beginStructure(r0);
        Lazy[] r2 = SessionData.access$get$childSerializers$cp();
        SessionDetails r7 = null;
        if (r1.decodeSequentially() == false) goto L5;
        SessionDetails r3 = (SessionDetails) r1.decodeSerializableElement(r0, 0, SessionDetails$$serializer.INSTANCE, null);
        Time r5 = (Time) r1.decodeNullableSerializableElement(r0, 1, Time$$serializer.INSTANCE, null);
        Map r15 = (Map) r1.decodeNullableSerializableElement(r0, 2, (DeserializationStrategy) r2[2].getValue(), null);
        SessionDetails r13 = r3;
        int r12 = 7;
        Time r14 = r5;
    L19:
        r1.endStructure(r0);
        return new SessionData(r12, r13, r14, r15, null);
    L5:
        boolean r10 = true;
        int r32 = 0;
        Time r8 = null;
        Map r9 = null;
    L6:
        if (r10 == false) goto L18;
        int r11 = r1.decodeElementIndex(r0);
        if (r11 != (-1)) goto L9;
        r10 = false;
        goto L6
    L9:
        if (r11 != 0) goto L10;
        r7 = (SessionDetails) r1.decodeSerializableElement(r0, 0, SessionDetails$$serializer.INSTANCE, r7);
        r32 = r32 | 1;
        goto L6
    L10:
        if (r11 != 1) goto L11;
        r8 = (Time) r1.decodeNullableSerializableElement(r0, 1, Time$$serializer.INSTANCE, r8);
        r32 = r32 | 2;
        goto L6
    L11:
        if (r11 != 2) goto L14;
        r9 = (Map) r1.decodeNullableSerializableElement(r0, 2, (DeserializationStrategy) r2[2].getValue(), r9);
        r32 = r32 | 4;
        goto L6
    L14:
        throw new UnknownFieldException(r11);
    L18:
        r12 = r32;
        r13 = r7;
        r14 = r8;
        r15 = r9;
        goto L19
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder r1) {
        return deserialize(r1);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(Encoder r1, SessionData r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        SessionData.write$Self$com_google_firebase_firebase_sessions(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (SessionData) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
