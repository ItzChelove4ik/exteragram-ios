package com.exteragram.messenger.nowplaying;

import com.exteragram.messenger.nowplaying.NowPlayingController;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlinx.serialization.DeserializationStrategy;
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
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/exteragram/messenger/nowplaying/NowPlayingController.ItunesSearchResponse.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesSearchResponse;", "<init>", "()V", "childSerializers", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", _UrlKt.FRAGMENT_ENCODE_SET, "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingController$ItunesSearchResponse$$serializer implements GeneratedSerializer<NowPlayingController.ItunesSearchResponse> {
    public static final NowPlayingController$ItunesSearchResponse$$serializer INSTANCE = null;
    private static final SerialDescriptor descriptor = null;

    static {
        NowPlayingController$ItunesSearchResponse$$serializer r0 = new NowPlayingController$ItunesSearchResponse$$serializer();
        INSTANCE = r0;
        PluginGeneratedSerialDescriptor r1 = new PluginGeneratedSerialDescriptor("com.exteragram.messenger.nowplaying.NowPlayingController.ItunesSearchResponse", r0, 2);
        r1.addElement("resultCount", false);
        r1.addElement("results", false);
        descriptor = r1;
    }

    private NowPlayingController$ItunesSearchResponse$$serializer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, NowPlayingController.ItunesSearchResponse.access$get$childSerializers$cp()[1].getValue()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final NowPlayingController.ItunesSearchResponse deserialize(Decoder r11) {
        SerialDescriptor r10 = descriptor;
        CompositeDecoder r112 = r11.beginStructure(r10);
        Lazy[] r0 = NowPlayingController.ItunesSearchResponse.access$get$childSerializers$cp();
        if (r112.decodeSequentially() == false) goto L5;
        int r1 = r112.decodeIntElement(r10, 0);
        List r02 = (List) r112.decodeSerializableElement(r10, 1, (DeserializationStrategy) r0[1].getValue(), null);
        int r2 = 3;
    L17:
        r112.endStructure(r10);
        return new NowPlayingController.ItunesSearchResponse(r2, r1, r02, null);
    L5:
        boolean r7 = true;
        int r5 = 0;
        int r6 = 0;
        List r12 = null;
    L6:
        if (r7 == false) goto L16;
        int r8 = r112.decodeElementIndex(r10);
        if (r8 != (-1)) goto L9;
        r7 = false;
        goto L6
    L9:
        if (r8 != 0) goto L10;
        r5 = r112.decodeIntElement(r10, 0);
        r6 = r6 | 1;
        goto L6
    L10:
        if (r8 != 1) goto L13;
        r12 = (List) r112.decodeSerializableElement(r10, 1, (DeserializationStrategy) r0[1].getValue(), r12);
        r6 = r6 | 2;
        goto L6
    L13:
        throw new UnknownFieldException(r8);
    L16:
        r02 = r12;
        r1 = r5;
        r2 = r6;
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

    public final void serialize(Encoder r1, NowPlayingController.ItunesSearchResponse r2) {
        SerialDescriptor r0 = descriptor;
        CompositeEncoder r12 = r1.beginStructure(r0);
        NowPlayingController.ItunesSearchResponse.write$Self$TMessagesProj(r2, r12, r0);
        r12.endStructure(r0);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder r1, Object r2) {
        serialize(r1, (NowPlayingController.ItunesSearchResponse) r2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return super.typeParametersSerializers();
    }
}
