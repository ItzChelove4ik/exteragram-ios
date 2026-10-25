package androidx.datastore.preferences.protobuf;

import java.util.List;

/* loaded from: classes.dex */
public interface LazyStringList extends List {
    void add(ByteString r1);

    Object getRaw(int r1);

    List<?> getUnderlyingElements();
}
