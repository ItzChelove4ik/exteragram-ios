package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001JM\u0010\n\u001a\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00028\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H&¢\u0006\u0004\b\n\u0010\u000bJR\u0010\u0012\u001a\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042)\u0010\u0011\u001a%\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00100\fH&¢\u0006\u0004\b\u0012\u0010\u0013JT\u0010\u0018\u001a\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042+\u0010\u0017\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00160\fH&¢\u0006\u0004\b\u0018\u0010\u0013¨\u0006\u0019À\u0006\u0003"}, d2 = {"Lkotlinx/serialization/modules/SerializersModuleCollector;", _UrlKt.FRAGMENT_ENCODE_SET, "Base", "Sub", "Lkotlin/reflect/KClass;", "baseClass", "actualClass", "Lkotlinx/serialization/KSerializer;", "actualSerializer", _UrlKt.FRAGMENT_ENCODE_SET, "polymorphic", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "Lkotlinx/serialization/SerializationStrategy;", "defaultSerializerProvider", "polymorphicDefaultSerializer", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", _UrlKt.FRAGMENT_ENCODE_SET, "className", "Lkotlinx/serialization/DeserializationStrategy;", "defaultDeserializerProvider", "polymorphicDefaultDeserializer", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface SerializersModuleCollector {
    <Base, Sub extends Base> void polymorphic(KClass<Base> r1, KClass<Sub> r2, KSerializer<Sub> r3);

    <Base> void polymorphicDefaultDeserializer(KClass<Base> r1, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> r2);

    <Base> void polymorphicDefaultSerializer(KClass<Base> r1, Function1<? super Base, ? extends SerializationStrategy<? super Base>> r2);
}
