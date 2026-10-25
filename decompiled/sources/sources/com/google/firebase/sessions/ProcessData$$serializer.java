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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/google/firebase/sessions/ProcessData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/firebase/sessions/ProcessData;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes.dex */
public /* synthetic */ class ProcessData$$serializer implements GeneratedSerializer<ProcessData> {
    public static final ProcessData$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        ProcessData$$serializer r0 = new ProcessData$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.google.firebase.sessions.ProcessData", r0, 2);
        r1.addElement("pid", false);
        r1.addElement("uuid", false);
        descriptor = r1;
    }

    private ProcessData$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final ProcessData deserialize(Decoder r10) {
        SerialDescriptor r9 = descriptor;
        CompositeDecoder r102 = r10.beginStructure(r9);
        if (r102.decodeSequentially() == false) goto L5;
        int r0 = r102.decodeIntElement(r9, 0);
        String r1 = r102.decodeStringElement(r9, 1);
        int r2 = 3;
    L17:
        r102.endStructure(r9);
        return new ProcessData(r2, r0, r1, null);
    L5:
        boolean r6 = true;
        r0 = 0;
        int r5 = 0;
        String r4 = null;
    L6:
        if (r6 == false) goto L16;
        int r7 = r102.decodeElementIndex(r9);
        if (r7 != (-1)) goto L9;
        r6 = false;
        goto L6
    L9:
        if (r7 != 0) goto L10;
        r0 = r102.decodeIntElement(r9, 0);
        r5 = r5 | 1;
        goto L6
    L10:
        if (r7 != 1) goto L13;
        r4 = r102.decodeStringElement(r9, 1);
        r5 = r5 | 2;
        goto L6
    L13:
        throw new UnknownFieldException(r7);
    L16:
        r1 = r4;
        r2 = r5;
        goto L17
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder r1) {
        return deserialize(r1);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(Encoder r1, ProcessData r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        ProcessData.write$Self$com_google_firebase_firebase_sessions(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (ProcessData) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
