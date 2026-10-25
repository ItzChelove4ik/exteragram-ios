package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0018\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0014\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010#\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0096\u0002¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0007H\u0016¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u0003H\u0016¢\u0006\u0004\b'\u0010(R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b*\u0010(R\u001a\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010+R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010,\u001a\u0004\b-\u0010&R\u0016\u0010.\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010,R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\"\u00103\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u001e\u00105\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\"\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R%\u0010A\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030<0/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R!\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00010/8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bB\u0010>\u001a\u0004\bC\u0010DR\u001b\u0010H\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u0010>\u001a\u0004\bG\u0010&R\u0014\u0010L\u001a\u00020I8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u001a\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00030P8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010R¨\u0006T"}, d2 = {"Lkotlinx/serialization/internal/PluginGeneratedSerialDescriptor;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "Lkotlinx/serialization/internal/CachedNames;", _UrlKt.FRAGMENT_ENCODE_SET, "serialName", "Lkotlinx/serialization/internal/GeneratedSerializer;", "generatedSerializer", _UrlKt.FRAGMENT_ENCODE_SET, "elementsCount", "<init>", "(Ljava/lang/String;Lkotlinx/serialization/internal/GeneratedSerializer;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "buildIndices", "()Ljava/util/Map;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "isOptional", _UrlKt.FRAGMENT_ENCODE_SET, "addElement", "(Ljava/lang/String;Z)V", "index", "getElementDescriptor", "(I)Lkotlinx/serialization/descriptors/SerialDescriptor;", "isElementOptional", "(I)Z", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getElementAnnotations", "(I)Ljava/util/List;", "getElementName", "(I)Ljava/lang/String;", "getElementIndex", "(Ljava/lang/String;)I", _UrlKt.FRAGMENT_ENCODE_SET, "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getSerialName", "Lkotlinx/serialization/internal/GeneratedSerializer;", "I", "getElementsCount", "added", _UrlKt.FRAGMENT_ENCODE_SET, "names", "[Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "propertiesAnnotations", "[Ljava/util/List;", "classAnnotations", "Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "elementsOptionality", "[Z", "indices", "Ljava/util/Map;", "Lkotlinx/serialization/KSerializer;", "childSerializers$delegate", "Lkotlin/Lazy;", "getChildSerializers", "()[Lkotlinx/serialization/KSerializer;", "childSerializers", "typeParameterDescriptors$delegate", "getTypeParameterDescriptors$kotlinx_serialization_core", "()[Lkotlinx/serialization/descriptors/SerialDescriptor;", "typeParameterDescriptors", "_hashCode$delegate", "get_hashCode", "_hashCode", "Lkotlinx/serialization/descriptors/SerialKind;", "getKind", "()Lkotlinx/serialization/descriptors/SerialKind;", "kind", "getAnnotations", "()Ljava/util/List;", "annotations", _UrlKt.FRAGMENT_ENCODE_SET, "getSerialNames", "()Ljava/util/Set;", "serialNames", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@PublishedApi
@SourceDebugExtension({"SMAP\nPluginGeneratedSerialDescriptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptor\n+ 2 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n+ 3 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,133:1\n16#2:134\n21#2:135\n16#2:136\n16#2:137\n106#3,10:138\n11693#4:148\n12040#4,3:149\n*S KotlinDebug\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptor\n*L\n75#1:134\n78#1:135\n80#1:136\n81#1:137\n92#1:138,10\n39#1:148\n39#1:149,3\n*E\n"})
/* loaded from: classes.dex */
public class PluginGeneratedSerialDescriptor implements SerialDescriptor, CachedNames {
    private final Lazy _hashCode$delegate;
    private int added;
    private final Lazy childSerializers$delegate;
    private List<Annotation> classAnnotations;
    private final int elementsCount;
    private final boolean[] elementsOptionality;
    private final GeneratedSerializer<?> generatedSerializer;
    private Map<String, Integer> indices;
    private final String[] names;
    private final List<Annotation>[] propertiesAnnotations;
    private final String serialName;
    private final Lazy typeParameterDescriptors$delegate;

    public static SerialDescriptor[] $r8$lambda$4mtRWLjW2TpRxsL3JlSZxIGYDBM(PluginGeneratedSerialDescriptor r4) {
        GeneratedSerializer<?> r42 = r4.generatedSerializer;
        if (r42 == null) goto L9;
        KSerializer<?>[] r43 = r42.typeParametersSerializers();
        if (r43 == null) goto L9;
        ArrayList r0 = new ArrayList(r43.length);
        int r1 = r43.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L11;
        r0.add(r43[r2].getDescriptor());
        r2 = r2 + 1;
    L11:
        return Platform_commonKt.compactArray(r0);
    L9:
        r0 = null;
        goto L11
    }

    public static int $r8$lambda$OUwXCx5ZKsS4eSDhLpIVIjQNys4(PluginGeneratedSerialDescriptor r1) {
        return PluginGeneratedSerialDescriptorKt.hashCodeImpl(r1, r1.getTypeParameterDescriptors$kotlinx_serialization_core());
    }

    public static KSerializer[] $r8$lambda$r9j3qqfSiBWrCfo_6pw28Hdd2Aw(PluginGeneratedSerialDescriptor r0) {
        GeneratedSerializer<?> r02 = r0.generatedSerializer;
        if (r02 == null) goto L9;
        KSerializer<?>[] r03 = r02.childSerializers();
        if (r03 == null) goto L9;
        return r03;
    L9:
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }

    public PluginGeneratedSerialDescriptor(String r2, GeneratedSerializer<?> r3, int r4) {
        this.serialName = r2;
        this.generatedSerializer = r3;
        this.elementsCount = r4;
        this.added = -1;
        String[] r22 = new String[r4];
        int r32 = 0;
    L3:
        if (r32 >= r4) goto L5;
        r22[r32] = "[UNINITIALIZED]";
        r32 = r32 + 1;
        goto L3
    L5:
        this.names = r22;
        int r23 = this.elementsCount;
        this.propertiesAnnotations = new List[r23];
        this.elementsOptionality = new boolean[r23];
        this.indices = MapsKt.emptyMap();
        LazyThreadSafetyMode r24 = LazyThreadSafetyMode.PUBLICATION;
        this.childSerializers$delegate = LazyKt.lazy(r24, new PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda0(this));
        this.typeParameterDescriptors$delegate = LazyKt.lazy(r24, new PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda1(this));
        this._hashCode$delegate = LazyKt.lazy(r24, new PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda2(this));
    }

    private final Map<String, Integer> buildIndices() {
        HashMap r0 = new HashMap();
        int r1 = this.names.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.put(this.names[r2], Integer.valueOf(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private final KSerializer<?>[] getChildSerializers() {
        return (KSerializer[]) this.childSerializers$delegate.getValue();
    }

    private final int get_hashCode() {
        return ((Number) this._hashCode$delegate.getValue()).intValue();
    }

    public final void addElement(String r3, boolean r4) {
        String[] r0 = this.names;
        int r1 = this.added + 1;
        this.added = r1;
        r0[r1] = r3;
        this.elementsOptionality[r1] = r4;
        this.propertiesAnnotations[r1] = null;
        if (r1 != (this.elementsCount - 1)) goto L6;
        this.indices = buildIndices();
        return;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof PluginGeneratedSerialDescriptor) == true) goto L8;
        return false;
    L8:
        SerialDescriptor r3 = (SerialDescriptor) r7;
        if (Intrinsics.areEqual(getSerialName(), r3.getSerialName()) == true) goto L12;
        return false;
    L12:
        if (Arrays.equals(getTypeParameterDescriptors$kotlinx_serialization_core(), ((PluginGeneratedSerialDescriptor) r7).getTypeParameterDescriptors$kotlinx_serialization_core()) == true) goto L15;
        return false;
    L15:
        if (getElementsCount() == r3.getElementsCount()) goto L17;
        return false;
    L17:
        int r72 = getElementsCount();
        int r1 = 0;
    L18:
        if (r1 >= r72) goto L26;
        if (Intrinsics.areEqual(getElementDescriptor(r1).getSerialName(), r3.getElementDescriptor(r1).getSerialName()) == false) goto L21;
        if (Intrinsics.areEqual(getElementDescriptor(r1).getKind(), r3.getElementDescriptor(r1).getKind()) == false) goto L24;
        r1 = r1 + 1;
        goto L18
    L24:
        return false;
    L21:
        return false;
    L26:
        return true;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getAnnotations() {
        List<Annotation> r0 = this.classAnnotations;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return CollectionsKt.emptyList();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int r1) {
        List<Annotation> r0 = this.propertiesAnnotations[r1];
        if (r0 == null) goto L5;
        return r0;
    L5:
        return CollectionsKt.emptyList();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int r1) {
        return getChildSerializers()[r1].getDescriptor();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String r1) {
        Integer r0 = this.indices.get(r1);
        if (r0 != null) goto L5;
        return -3;
    L5:
        return r0.intValue();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int r1) {
        return this.names[r1];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public final int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return StructureKind.CLASS.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    public Set<String> getSerialNames() {
        return this.indices.keySet();
    }

    public final SerialDescriptor[] getTypeParameterDescriptors$kotlinx_serialization_core() {
        return (SerialDescriptor[]) this.typeParameterDescriptors$delegate.getValue();
    }

    public int hashCode() {
        return get_hashCode();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isElementOptional(int r1) {
        return this.elementsOptionality[r1];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* bridge */ boolean isInline() {
        return super.isInline();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* bridge */ boolean isNullable() {
        return super.isNullable();
    }

    public String toString() {
        return PluginGeneratedSerialDescriptorKt.toStringImpl(this);
    }
}
