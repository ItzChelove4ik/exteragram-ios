package com.exteragram.messenger.nowplaying;

import com.exteragram.messenger.nowplaying.NowPlayingController;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/exteragram/messenger/nowplaying/NowPlayingController.ItunesTrack.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingController$ItunesTrack$$serializer implements GeneratedSerializer<NowPlayingController.ItunesTrack> {
    public static final NowPlayingController$ItunesTrack$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        NowPlayingController$ItunesTrack$$serializer r0 = new NowPlayingController$ItunesTrack$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.exteragram.messenger.nowplaying.NowPlayingController.ItunesTrack", r0, 6);
        r1.addElement("trackName", true);
        r1.addElement("artistName", true);
        r1.addElement("collectionName", true);
        r1.addElement("artworkUrl100", true);
        r1.addElement("previewUrl", true);
        r1.addElement("trackTimeMillis", true);
        descriptor = r1;
    }

    private NowPlayingController$ItunesTrack$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        StringSerializer r7 = StringSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(r7), BuiltinSerializersKt.getNullable(r7), BuiltinSerializersKt.getNullable(r7), BuiltinSerializersKt.getNullable(r7), BuiltinSerializersKt.getNullable(r7), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final NowPlayingController.ItunesTrack deserialize(Decoder r26) {
        SerialDescriptor r0 = descriptor;
        CompositeDecoder r1 = r26.beginStructure(r0);
        int r3 = 5;
        String r9 = null;
        if (r1.decodeSequentially() == false) goto L5;
        StringSerializer r2 = StringSerializer.INSTANCE;
        String r8 = (String) r1.decodeNullableSerializableElement(r0, 0, r2, null);
        String r7 = (String) r1.decodeNullableSerializableElement(r0, 1, r2, null);
        String r6 = (String) r1.decodeNullableSerializableElement(r0, 2, r2, null);
        String r4 = (String) r1.decodeNullableSerializableElement(r0, 3, r2, null);
        String r22 = (String) r1.decodeNullableSerializableElement(r0, 4, r2, null);
        Long r23 = (Long) r1.decodeNullableSerializableElement(r0, 5, LongSerializer.INSTANCE, null);
        String r21 = r4;
        int r17 = 63;
        String r20 = r6;
        String r19 = r7;
        String r18 = r8;
    L21:
        r1.endStructure(r0);
        return new NowPlayingController.ItunesTrack(r17, r18, r19, r20, r21, r22, r23, null);
    L5:
        boolean r15 = true;
        int r24 = 0;
        String r10 = null;
        String r11 = null;
        String r12 = null;
        String r13 = null;
        Long r14 = null;
    L6:
        if (r15 == false) goto L20;
        int r82 = r1.decodeElementIndex(r0);
        switch(r82) {
            case -1: goto L19;
            case 0: goto L17;
            case 1: goto L16;
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L11;
            default: goto L10;
        };
    L10:
        throw new UnknownFieldException(r82);
    L17:
        r9 = (String) r1.decodeNullableSerializableElement(r0, 0, StringSerializer.INSTANCE, r9);
        r24 = r24 | 1;
    L18:
        r3 = 5;
        goto L6
    L19:
        r15 = false;
        goto L18
    L11:
        r14 = (Long) r1.decodeNullableSerializableElement(r0, r3, LongSerializer.INSTANCE, r14);
        r24 = r24 | 32;
        goto L6
    L13:
        r13 = (String) r1.decodeNullableSerializableElement(r0, 4, StringSerializer.INSTANCE, r13);
        r24 = r24 | 16;
        goto L6
    L14:
        r12 = (String) r1.decodeNullableSerializableElement(r0, 3, StringSerializer.INSTANCE, r12);
        r24 = r24 | 8;
        goto L6
    L15:
        r11 = (String) r1.decodeNullableSerializableElement(r0, 2, StringSerializer.INSTANCE, r11);
        r24 = r24 | 4;
        goto L6
    L16:
        r10 = (String) r1.decodeNullableSerializableElement(r0, 1, StringSerializer.INSTANCE, r10);
        r24 = r24 | 2;
        goto L6
    L20:
        r17 = r24;
        r18 = r9;
        r19 = r10;
        r20 = r11;
        r21 = r12;
        r22 = r13;
        r23 = r14;
        goto L21
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder r1) {
        return deserialize(r1);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(Encoder r1, NowPlayingController.ItunesTrack r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        NowPlayingController.ItunesTrack.write$Self$TMessagesProj(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (NowPlayingController.ItunesTrack) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return super.typeParametersSerializers();
    }
}
