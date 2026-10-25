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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/google/firebase/sessions/SessionDetails.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/firebase/sessions/SessionDetails;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes.dex */
public /* synthetic */ class SessionDetails$$serializer implements GeneratedSerializer<SessionDetails> {
    public static final SessionDetails$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        SessionDetails$$serializer r0 = new SessionDetails$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.google.firebase.sessions.SessionDetails", r0, 4);
        r1.addElement("sessionId", false);
        r1.addElement("firstSessionId", false);
        r1.addElement("sessionIndex", false);
        r1.addElement("sessionStartTimestampUs", false);
        descriptor = r1;
    }

    private SessionDetails$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        StringSerializer r0 = StringSerializer.INSTANCE;
        return new KSerializer[]{r0, r0, IntSerializer.INSTANCE, LongSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SessionDetails deserialize(Decoder r22) {
        SerialDescriptor r0 = descriptor;
        CompositeDecoder r1 = r22.beginStructure(r0);
        if (r1.decodeSequentially() == false) goto L6;
        String r2 = r1.decodeStringElement(r0, 0);
        String r5 = r1.decodeStringElement(r0, 1);
        int r14 = 15;
        int r17 = r1.decodeIntElement(r0, 2);
        String r16 = r5;
        long r18 = r1.decodeLongElement(r0, 3);
    L5:
        String r15 = r2;
        r1.endStructure(r0);
        return new SessionDetails(r14, r15, r16, r17, r18, null);
    L6:
        r2 = null;
        String r9 = null;
        boolean r12 = true;
        long r10 = 0;
        int r7 = 0;
        int r8 = 0;
    L7:
        if (r12 == false) goto L21;
        int r13 = r1.decodeElementIndex(r0);
        if (r13 != (-1)) goto L10;
        r12 = false;
        goto L7
    L10:
        if (r13 != 0) goto L11;
        r2 = r1.decodeStringElement(r0, 0);
        r7 = r7 | 1;
        goto L7
    L11:
        if (r13 != 1) goto L12;
        r9 = r1.decodeStringElement(r0, 1);
        r7 = r7 | 2;
        goto L7
    L12:
        if (r13 != 2) goto L13;
        r8 = r1.decodeIntElement(r0, 2);
        r7 = r7 | 4;
        goto L7
    L13:
        if (r13 != 3) goto L16;
        r10 = r1.decodeLongElement(r0, 3);
        r7 = r7 | 8;
        goto L7
    L16:
        throw new UnknownFieldException(r13);
    L21:
        r14 = r7;
        r17 = r8;
        r16 = r9;
        r18 = r10;
        goto L5
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder r1) {
        return deserialize(r1);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(Encoder r1, SessionDetails r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        SessionDetails.write$Self$com_google_firebase_firebase_sessions(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (SessionDetails) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
