package kotlin.collections.builders;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0019\bF\u0012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\bV¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0082\u0080\u0004R\u0017\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/collections/builders/SerializedMap;", "Ljava/io/Externalizable;", "map", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/Map;)V", "()V", "writeExternal", _UrlKt.FRAGMENT_ENCODE_SET, "output", "Ljava/io/ObjectOutput;", "readExternal", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInput;", "readResolve", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class SerializedMap implements Externalizable {
    public static final Companion Companion = null;
    private static final long serialVersionUID = 0;
    private Map<?, ?> map;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/collections/builders/SerializedMap$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serialVersionUID", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public SerializedMap() {
        this(MapsKt.emptyMap());
    }

    public SerializedMap(Map<?, ?> r1) {
        this.map = r1;
    }

    private final Object readResolve() {
        return this.map;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput r6) {
        byte r0 = r6.readByte();
        if (r0 != 0) goto L14;
        int r02 = r6.readInt();
        if (r02 < 0) goto L11;
        Map r1 = MapsKt.createMapBuilder(r02);
        int r2 = 0;
    L7:
        if (r2 >= r02) goto L9;
        r1.put(r6.readObject(), r6.readObject());
        r2 = r2 + 1;
        goto L7
    L9:
        this.map = MapsKt.build(r1);
        return;
    L11:
        SerializedMap$$ExternalSyntheticBUOutline0.m("Illegal size value: ", r02);
        return;
    L14:
        throw new InvalidObjectException("Unsupported flags value: " + r0);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput r3) {
        r3.writeByte(0);
        r3.writeInt(this.map.size());
        Iterator<Map.Entry<?, ?>> r2 = this.map.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        Map.Entry<?, ?> r0 = r2.next();
        r3.writeObject(r0.getKey());
        r3.writeObject(r0.getValue());
        goto L4
    }
}
