package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.collection.ArrayMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class VersionedParcel {
    protected final ArrayMap<String, Class> mParcelizerCache;
    protected final ArrayMap<String, Method> mReadCache;
    protected final ArrayMap<String, Method> mWriteCache;

    public VersionedParcel(ArrayMap<String, Method> r1, ArrayMap<String, Method> r2, ArrayMap<String, Class> r3) {
        this.mReadCache = r1;
        this.mWriteCache = r2;
        this.mParcelizerCache = r3;
    }

    private Class findParcelClass(Class<? extends VersionedParcelable> r4) {
        Class r0 = this.mParcelizerCache.get(r4.getName());
        if (r0 != null) goto L6;
        Class<?> r02 = Class.forName(String.format("%s.%sParcelizer", new Object[]{r4.getPackage().getName(), r4.getSimpleName()}), false, r4.getClassLoader());
        this.mParcelizerCache.put(r4.getName(), r02);
        return r02;
    L6:
        return r0;
    }

    private Method getReadMethod(String r4) {
        Method r0 = this.mReadCache.get(r4);
        if (r0 != null) goto L6;
        System.currentTimeMillis();
        Method r02 = Class.forName(r4, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[]{VersionedParcel.class});
        this.mReadCache.put(r4, r02);
        return r02;
    L6:
        return r0;
    }

    private Method getWriteMethod(Class r4) {
        Method r0 = this.mWriteCache.get(r4.getName());
        if (r0 != null) goto L6;
        Class r02 = findParcelClass(r4);
        System.currentTimeMillis();
        Method r03 = r02.getDeclaredMethod("write", new Class[]{r4, VersionedParcel.class});
        this.mWriteCache.put(r4.getName(), r03);
        return r03;
    L6:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void writeVersionedParcelableCreator(VersionedParcelable r2) {
        writeString(findParcelClass(r2.getClass()).getName());
        return;
    L5:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m(r2.getClass().getSimpleName().concat(" does not have a Parcelizer"), e);
    }

    public abstract void closeField();

    public abstract VersionedParcel createSubParcel();

    public boolean isStream() {
        return false;
    }

    public abstract boolean readBoolean();

    public boolean readBoolean(boolean r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return readBoolean();
    }

    public abstract byte[] readByteArray();

    public byte[] readByteArray(byte[] r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return readByteArray();
    }

    public abstract CharSequence readCharSequence();

    public CharSequence readCharSequence(CharSequence r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return readCharSequence();
    }

    public abstract boolean readField(int r1);

    public <T extends VersionedParcelable> T readFromParcel(String r1, VersionedParcel r2) {
        return (T) getReadMethod(r1).invoke(null, new Object[]{r2});
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered ClassNotFoundException", e);
        return null;
    L16:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered IllegalAccessException", e);
        return null;
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered NoSuchMethodException", e);
        return null;
    L10:
        e = move-exception;
        if ((e.getCause() instanceof RuntimeException) == true) goto L15;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered InvocationTargetException", e);
        return null;
    L15:
        throw ((RuntimeException) e.getCause());
    }

    public abstract int readInt();

    public int readInt(int r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return readInt();
    }

    public abstract <T extends Parcelable> T readParcelable();

    public <T extends Parcelable> T readParcelable(T r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return (T) readParcelable();
    }

    public abstract String readString();

    public String readString(String r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return readString();
    }

    public <T extends VersionedParcelable> T readVersionedParcelable() {
        String r0 = readString();
        if (r0 != null) goto L7;
        return null;
    L7:
        return (T) readFromParcel(r0, createSubParcel());
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T r1, int r2) {
        if (readField(r2) == true) goto L6;
        return r1;
    L6:
        return (T) readVersionedParcelable();
    }

    public abstract void setOutputField(int r1);

    public void setSerializationFlags(boolean r1, boolean r2) {
    }

    public abstract void writeBoolean(boolean r1);

    public void writeBoolean(boolean r1, int r2) {
        setOutputField(r2);
        writeBoolean(r1);
    }

    public abstract void writeByteArray(byte[] r1);

    public void writeByteArray(byte[] r1, int r2) {
        setOutputField(r2);
        writeByteArray(r1);
    }

    public abstract void writeCharSequence(CharSequence r1);

    public void writeCharSequence(CharSequence r1, int r2) {
        setOutputField(r2);
        writeCharSequence(r1);
    }

    public abstract void writeInt(int r1);

    public void writeInt(int r1, int r2) {
        setOutputField(r2);
        writeInt(r1);
    }

    public abstract void writeParcelable(Parcelable r1);

    public void writeParcelable(Parcelable r1, int r2) {
        setOutputField(r2);
        writeParcelable(r1);
    }

    public abstract void writeString(String r1);

    public void writeString(String r1, int r2) {
        setOutputField(r2);
        writeString(r1);
    }

    public <T extends VersionedParcelable> void writeToParcel(T r2, VersionedParcel r3) {
        getWriteMethod(r2.getClass()).invoke(null, new Object[]{r2, r3});     // Catch: ClassNotFoundException -> L4 NoSuchMethodException -> L7 InvocationTargetException -> L10 IllegalAccessException -> L17
        return;
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered ClassNotFoundException", e);
        return;
    L17:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered IllegalAccessException", e);
        return;
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered NoSuchMethodException", e);
        return;
    L10:
        e = move-exception;
        if ((e.getCause() instanceof RuntimeException) == true) goto L16;
        Make$Map$$ExternalSyntheticBUOutline0.m("VersionedParcel encountered InvocationTargetException", e);
        return;
    L16:
        throw ((RuntimeException) e.getCause());
    }

    public void writeVersionedParcelable(VersionedParcelable r2) {
        if (r2 != null) goto L5;
        writeString(null);
        return;
    L5:
        writeVersionedParcelableCreator(r2);
        VersionedParcel r0 = createSubParcel();
        writeToParcel(r2, r0);
        r0.closeField();
    }

    public void writeVersionedParcelable(VersionedParcelable r1, int r2) {
        setOutputField(r2);
        writeVersionedParcelable(r1);
    }
}
