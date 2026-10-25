package kotlinx.serialization.modules;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001Bî\u0001\u0012\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012*\u0010\u0007\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00020\u0002\u0012.\u0010\u000b\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u001c\u0012\u001a\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\t0\bj\u0006\u0012\u0002\b\u0003`\n0\u0002\u0012&\u0010\r\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00020\u0002\u0012A\u0010\u0013\u001a=\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012/\u0012-\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00110\bj\u0006\u0012\u0002\b\u0003`\u00120\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017JA\u0010\u001c\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\"\b\b\u0000\u0010\u0018*\u00020\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u001aH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b!\u0010\"R$\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010#R8\u0010\u0007\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010#R<\u0010\u000b\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u001c\u0012\u001a\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\t0\bj\u0006\u0012\u0002\b\u0003`\n0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010#R4\u0010\r\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010#RO\u0010\u0013\u001a=\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012/\u0012-\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00110\bj\u0006\u0012\u0002\b\u0003`\u00120\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010#R\u001a\u0010\u0015\u001a\u00020\u00148\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u0015\u0010$\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lkotlinx/serialization/modules/SerialModuleImpl;", "Lkotlinx/serialization/modules/SerializersModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", _UrlKt.FRAGMENT_ENCODE_SET, "class2ContextualFactory", "Lkotlinx/serialization/KSerializer;", "polyBase2Serializers", "Lkotlin/Function1;", "Lkotlinx/serialization/SerializationStrategy;", "Lkotlinx/serialization/modules/PolymorphicSerializerProvider;", "polyBase2DefaultSerializerProvider", _UrlKt.FRAGMENT_ENCODE_SET, "polyBase2NamedSerializers", "Lkotlin/ParameterName;", "name", "className", "Lkotlinx/serialization/DeserializationStrategy;", "Lkotlinx/serialization/modules/PolymorphicDeserializerProvider;", "polyBase2DefaultDeserializerProvider", _UrlKt.FRAGMENT_ENCODE_SET, "hasInterfaceContextualSerializers", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Z)V", "T", "kClass", _UrlKt.FRAGMENT_ENCODE_SET, "typeArgumentsSerializers", "getContextual", "(Lkotlin/reflect/KClass;Ljava/util/List;)Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "dumpTo", "(Lkotlinx/serialization/modules/SerializersModuleCollector;)V", "Ljava/util/Map;", "Z", "getHasInterfaceContextualSerializers$kotlinx_serialization_core", "()Z", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSerializersModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerialModuleImpl\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n1#1,245:1\n221#2,2:246\n221#2:248\n221#2:249\n222#2:251\n222#2:252\n221#2,2:253\n221#2,2:255\n78#3:250\n*S KotlinDebug\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerialModuleImpl\n*L\n186#1:246,2\n196#1:248\n197#1:249\n197#1:251\n196#1:252\n206#1:253,2\n210#1:255,2\n201#1:250\n*E\n"})
/* loaded from: classes.dex */
public final class SerialModuleImpl extends SerializersModule {
    private final Map<KClass<?>, Object> class2ContextualFactory;
    private final boolean hasInterfaceContextualSerializers;
    private final Map<KClass<?>, Function1<String, DeserializationStrategy<?>>> polyBase2DefaultDeserializerProvider;
    private final Map<KClass<?>, Function1<?, SerializationStrategy<?>>> polyBase2DefaultSerializerProvider;
    private final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers;

    @JvmField
    public final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers;

    /* JADX WARN: Multi-variable type inference failed */
    public SerialModuleImpl(Map<KClass<?>, Object> r2, Map<KClass<?>, ? extends Map<KClass<?>, ? extends KSerializer<?>>> r3, Map<KClass<?>, ? extends Function1<?, ? extends SerializationStrategy<?>>> r4, Map<KClass<?>, ? extends Map<String, ? extends KSerializer<?>>> r5, Map<KClass<?>, ? extends Function1<? super String, ? extends DeserializationStrategy<?>>> r6, boolean r7) {
        super(null);
        this.class2ContextualFactory = r2;
        this.polyBase2Serializers = r3;
        this.polyBase2DefaultSerializerProvider = r4;
        this.polyBase2NamedSerializers = r5;
        this.polyBase2DefaultDeserializerProvider = r6;
        this.hasInterfaceContextualSerializers = r7;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public void dumpTo(SerializersModuleCollector r6) {
        Iterator<Map.Entry<KClass<?>, Object>> r0 = this.class2ContextualFactory.entrySet().iterator();
        if (r0.hasNext() == true) goto L20;
        Iterator<Map.Entry<KClass<?>, Map<KClass<?>, KSerializer<?>>>> r02 = this.polyBase2Serializers.entrySet().iterator();
    L6:
        if (r02.hasNext() == false) goto L11;
        Map.Entry<KClass<?>, Map<KClass<?>, KSerializer<?>>> r1 = r02.next();
        KClass<?> r2 = r1.getKey();
        Iterator<Map.Entry<KClass<?>, KSerializer<?>>> r12 = r1.getValue().entrySet().iterator();
    L9:
        if (r12.hasNext() == false) goto L6;
        Map.Entry<KClass<?>, KSerializer<?>> r3 = r12.next();
        r6.polymorphic(r2, r3.getKey(), r3.getValue());
        goto L9
    L11:
        Iterator<Map.Entry<KClass<?>, Function1<?, SerializationStrategy<?>>>> r03 = this.polyBase2DefaultSerializerProvider.entrySet().iterator();
    L13:
        if (r03.hasNext() == false) goto L15;
        Map.Entry<KClass<?>, Function1<?, SerializationStrategy<?>>> r13 = r03.next();
        r6.polymorphicDefaultSerializer(r13.getKey(), (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r13.getValue(), 1));
        goto L13
    L15:
        Iterator<Map.Entry<KClass<?>, Function1<String, DeserializationStrategy<?>>>> r5 = this.polyBase2DefaultDeserializerProvider.entrySet().iterator();
    L17:
        if (r5.hasNext() == false) goto L19;
        Map.Entry<KClass<?>, Function1<String, DeserializationStrategy<?>>> r04 = r5.next();
        r6.polymorphicDefaultDeserializer(r04.getKey(), (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r04.getValue(), 1));
        goto L17
    L19:
        return;
    L20:
        Map.Entry<KClass<?>, Object> r52 = r0.next();
        KClass<?> r62 = r52.getKey();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r52.getValue());
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> KSerializer<T> getContextual(KClass<T> r1, List<? extends KSerializer<?>> r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.class2ContextualFactory.get(r1));
        return null;
    }
}
