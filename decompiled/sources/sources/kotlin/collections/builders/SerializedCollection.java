package kotlin.collections.builders;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\bF\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\t\bV¢\u0006\u0004\b\u0006\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\n\u0010\u0010\u001a\u00020\u0011H\u0082\u0080\u0004R\u0013\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlin/collections/builders/SerializedCollection;", "Ljava/io/Externalizable;", "collection", _UrlKt.FRAGMENT_ENCODE_SET, "tag", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/Collection;I)V", "()V", "writeExternal", _UrlKt.FRAGMENT_ENCODE_SET, "output", "Ljava/io/ObjectOutput;", "readExternal", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInput;", "readResolve", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/SerializedCollection\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,724:1\n1#2:725\n*E\n"})
/* loaded from: classes5.dex */
public final class SerializedCollection implements Externalizable {
    public static final Companion Companion = null;
    private static final long serialVersionUID = 0;
    public static final int tagList = 0;
    public static final int tagSet = 1;
    private Collection<?> collection;
    private final int tag;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0007X\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlin/collections/builders/SerializedCollection$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serialVersionUID", _UrlKt.FRAGMENT_ENCODE_SET, "tagList", _UrlKt.FRAGMENT_ENCODE_SET, "tagSet", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

    public SerializedCollection() {
        this(CollectionsKt.emptyList(), 0);
    }

    public SerializedCollection(Collection<?> r1, int r2) {
        this.collection = r1;
        this.tag = r2;
    }

    private final Object readResolve() {
        return this.collection;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput r5) {
        byte r0 = r5.readByte();
        int r1 = r0 & 1;
        if ((r0 & (-2)) != 0) goto L24;
        int r02 = r5.readInt();
        if (r02 < 0) goto L22;
        int r2 = 0;
        if (r1 != 0) goto L9;
        List r12 = CollectionsKt.createListBuilder(r02);
    L17:
        if (r2 >= r02) goto L19;
        r12.add(r5.readObject());
        r2 = r2 + 1;
        goto L17
    L19:
        List r52 = CollectionsKt.build(r12);
    L20:
        this.collection = r52;
        return;
    L9:
        if (r1 != 1) goto L14;
        Set r13 = SetsKt.createSetBuilder(r02);
    L11:
        if (r2 >= r02) goto L13;
        r13.add(r5.readObject());
        r2 = r2 + 1;
        goto L11
    L13:
        r52 = SetsKt.build(r13);
        goto L20
    L14:
        SerializedMap$$ExternalSyntheticBUOutline0.m("Unsupported collection type tag: ", r1);
        return;
    L22:
        SerializedMap$$ExternalSyntheticBUOutline0.m("Illegal size value: ", r02);
        return;
    L24:
        SerializedMap$$ExternalSyntheticBUOutline0.m("Unsupported flags value: ", r0);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput r2) {
        r2.writeByte(this.tag);
        r2.writeInt(this.collection.size());
        Iterator<?> r1 = this.collection.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r2.writeObject(r1.next());
        goto L4
    }
}
