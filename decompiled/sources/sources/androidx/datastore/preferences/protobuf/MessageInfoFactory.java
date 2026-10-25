package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
interface MessageInfoFactory {
    boolean isSupported(Class<?> r1);

    MessageInfo messageInfoFor(Class<?> r1);
}
