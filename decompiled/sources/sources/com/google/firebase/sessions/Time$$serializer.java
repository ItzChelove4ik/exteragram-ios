package com.google.firebase.sessions;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/google/firebase/sessions/Time.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/firebase/sessions/Time;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes.dex */
public /* synthetic */ class Time$$serializer implements GeneratedSerializer<Time> {
    public static final Time$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        Time$$serializer r0 = new Time$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.google.firebase.sessions.Time", r0, 3);
        r1.addElement("ms", false);
        r1.addElement("us", true);
        r1.addElement("seconds", true);
        descriptor = r1;
    }

    private Time$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        LongSerializer r0 = LongSerializer.INSTANCE;
        return new KSerializer[]{r0, r0, r0};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final Time deserialize(Decoder r23) {
        SerialDescriptor r0 = descriptor;
        CompositeDecoder r1 = r23.beginStructure(r0);
        if (r1.decodeSequentially() == false) goto L5;
        long r5 = r1.decodeLongElement(r0, 0);
        long r7 = r1.decodeLongElement(r0, 1);
        long r19 = r1.decodeLongElement(r0, 2);
        int r14 = 7;
        long r15 = r5;
        long r17 = r7;
    L19:
        r1.endStructure(r0);
        return new Time(r14, r15, r17, r19, null);
    L5:
        long r6 = 0;
        boolean r12 = true;
        int r2 = 0;
        long r8 = 0;
        long r10 = 0;
    L6:
        if (r12 == false) goto L18;
        int r13 = r1.decodeElementIndex(r0);
        if (r13 != (-1)) goto L9;
        r12 = false;
        goto L6
    L9:
        if (r13 != 0) goto L10;
        r8 = r1.decodeLongElement(r0, 0);
        r2 = r2 | 1;
        goto L6
    L10:
        if (r13 != 1) goto L11;
        r10 = r1.decodeLongElement(r0, 1);
        r2 = r2 | 2;
        goto L6
    L11:
        if (r13 != 2) goto L14;
        r6 = r1.decodeLongElement(r0, 2);
        r2 = r2 | 4;
        goto L6
    L14:
        throw new UnknownFieldException(r13);
    L18:
        r14 = r2;
        r19 = r6;
        r15 = r8;
        r17 = r10;
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

    public final void serialize(Encoder r1, Time r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        Time.write$Self$com_google_firebase_firebase_sessions(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (Time) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
