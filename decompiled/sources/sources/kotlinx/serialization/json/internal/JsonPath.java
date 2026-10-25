package kotlinx.serialization.json.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.JsonConfiguration;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0002#$B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\bJ\r\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\bJ\r\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u0018R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u001e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lkotlinx/serialization/json/internal/JsonPath;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "<init>", "(Lkotlinx/serialization/json/JsonConfiguration;)V", _UrlKt.FRAGMENT_ENCODE_SET, "resize", "()V", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "sd", "pushDescriptor", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)V", _UrlKt.FRAGMENT_ENCODE_SET, "index", "updateDescriptorIndex", "(I)V", PluginsConstants.Settings.KEY, "updateCurrentMapKey", "(Ljava/lang/Object;)V", "resetCurrentMapKey", "popDescriptor", _UrlKt.FRAGMENT_ENCODE_SET, "getPath", "()Ljava/lang/String;", "toString", "Lkotlinx/serialization/json/JsonConfiguration;", _UrlKt.FRAGMENT_ENCODE_SET, "currentObjectPath", "[Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "indicies", "[I", "currentDepth", "I", "Tombstone", "RedactedKey", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class JsonPath {
    private final JsonConfiguration configuration;
    private int currentDepth;
    private Object[] currentObjectPath;
    private int[] indicies;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/serialization/json/internal/JsonPath$RedactedKey;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class RedactedKey {
        public static final RedactedKey INSTANCE = null;

        static {
            INSTANCE = new RedactedKey();
        }

        private RedactedKey() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/serialization/json/internal/JsonPath$Tombstone;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Tombstone {
        public static final Tombstone INSTANCE = null;

        static {
            INSTANCE = new Tombstone();
        }

        private Tombstone() {
        }
    }

    public JsonPath(JsonConfiguration r4) {
        this.configuration = r4;
        this.currentObjectPath = new Object[8];
        int[] r0 = new int[8];
        int r1 = 0;
    L4:
        if (r1 >= 8) goto L6;
        r0[r1] = -1;
        r1 = r1 + 1;
        goto L4
    L6:
        this.indicies = r0;
        this.currentDepth = -1;
    }

    private final void resize() {
        int r0 = this.currentDepth * 2;
        this.currentObjectPath = Arrays.copyOf(this.currentObjectPath, r0);
        int[] r3 = new int[r0];
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1] = -1;
        r1 = r1 + 1;
        goto L3
    L5:
        ArraysKt.copyInto$default(this.indicies, r3, 0, 0, 0, 14, null);
        this.indicies = r3;
    }

    public final String getPath() {
        StringBuilder r0 = new StringBuilder("$");
        int r1 = this.currentDepth + 1;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L22;
        Object r3 = this.currentObjectPath[r2];
        if ((r3 instanceof SerialDescriptor) == false) goto L15;
        SerialDescriptor r32 = (SerialDescriptor) r3;
        boolean r4 = Intrinsics.areEqual(r32.getKind(), StructureKind.LIST.INSTANCE);
        int[] r5 = this.indicies;
        if (r4 == true) goto L9;
        int r42 = r5[r2];
        if (r42 < 0) goto L20;
        r0.append(".");
        r0.append(r32.getElementName(r42));
    L20:
        r2 = r2 + 1;
        goto L3
    L9:
        if (r5[r2] == (-1)) goto L20;
        r0.append("[");
        r0.append(this.indicies[r2]);
        r0.append("]");
        goto L20
    L15:
        if (r3 != RedactedKey.INSTANCE) goto L18;
        r0.append("[<debug info disabled>]");
        goto L20
    L18:
        if (r3 == Tombstone.INSTANCE) goto L20;
        r0.append("['");
        r0.append(r3);
        r0.append("']");
        goto L20
    L22:
        return r0.toString();
    }

    public final void popDescriptor() {
        int r0 = this.currentDepth;
        int[] r1 = this.indicies;
        if (r1[r0] != (-2)) goto L5;
        r1[r0] = -1;
        this.currentDepth = r0 - 1;
    L5:
        int r02 = this.currentDepth;
        if (r02 == (-1)) goto L9;
        this.currentDepth = r02 - 1;
        return;
    }

    public final void pushDescriptor(SerialDescriptor r3) {
        int r0 = this.currentDepth + 1;
        this.currentDepth = r0;
        if (r0 != this.currentObjectPath.length) goto L5;
        resize();
    L5:
        this.currentObjectPath[r0] = r3;
    }

    public final void resetCurrentMapKey() {
        int[] r0 = this.indicies;
        int r1 = this.currentDepth;
        if (r0[r1] != (-2)) goto L6;
        this.currentObjectPath[r1] = Tombstone.INSTANCE;
        return;
    }

    public String toString() {
        return getPath();
    }

    public final void updateCurrentMapKey(Object r5) {
        int[] r0 = this.indicies;
        int r1 = this.currentDepth;
        if (r0[r1] == (-2)) goto L7;
        int r12 = r1 + 1;
        this.currentDepth = r12;
        if (r12 != this.currentObjectPath.length) goto L7;
        resize();
    L7:
        Object[] r02 = this.currentObjectPath;
        int r13 = this.currentDepth;
        if (this.configuration.getExceptionsWithDebugInfo() == true) goto L11;
        r5 = RedactedKey.INSTANCE;
    L11:
        r02[r13] = r5;
        this.indicies[this.currentDepth] = -2;
    }

    public final void updateDescriptorIndex(int r2) {
        this.indicies[this.currentDepth] = r2;
    }
}
