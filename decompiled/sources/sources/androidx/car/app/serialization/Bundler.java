package androidx.car.app.serialization;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public abstract class Bundler {
    private static final Map<Integer, String> BUNDLED_TYPE_NAMES = null;
    private static final Map<Class<?>, String> UNOBFUSCATED_TYPE_NAMES = null;

    public static class CycleDetectedBundlerException extends TracedBundlerException {
        public CycleDetectedBundlerException(String r1, Trace r2) {
            super(r1, r2);
        }
    }

    public static class Frame {
        private final String mDisplay;
        private final Object mObj;

        public Frame(Object r1, String r2) {
            this.mObj = r1;
            this.mDisplay = r2;
        }

        public Object getObj() {
            return this.mObj;
        }

        public String toFlatString() {
            return "[" + this.mDisplay + ", " + Bundler.getUnobfuscatedClassName(this.mObj.getClass()) + "]";
        }

        public String toString() {
            return toFlatString();
        }

        public String toTraceString() {
            return Bundler.getUnobfuscatedClassName(this.mObj.getClass()) + " " + this.mDisplay;
        }
    }

    public static class Trace implements AutoCloseable {
        private final ArrayDeque<Frame> mFrames;
        private String[] mIndents;

        private Trace(Object r2, String r3, ArrayDeque<Frame> r4) {
            this.mFrames = r4;
            if (r2 == null) goto L8;
            Frame r0 = new Frame(r2, r3);
            r4.addFirst(r0);
            if (Log.isLoggable("CarApp.Bun", 2) == false) goto L9;
            Log.v("CarApp.Bun", getIndent(r4.size()) + r0.toTraceString());
            return;
        L9:
            return;
        }

        public static String bundleToString(Bundle r1) {
            return Bundler.getBundledTypeName(r1.getInt("tag_class_type"));
        }

        public static Trace create() {
            return new Trace(null, _UrlKt.FRAGMENT_ENCODE_SET, new ArrayDeque());
        }

        public static Trace fromParent(Object r1, String r2, Trace r3) {
            return new Trace(r1, r2, r3.mFrames);
        }

        private String getIndent(int r3) {
            int r32 = Math.min(r3, 11);
            if (this.mIndents != null) goto L5;
            this.mIndents = new String[12];
        L5:
            String r1 = this.mIndents[r32];
            if (r1 != null) goto L11;
            r1 = repeatChar(' ', r32);
            if (r32 != 11) goto L10;
            r1 = r1 + "...";
        L10:
            this.mIndents[r32] = r1;
        L11:
            return r1;
        }

        private static String repeatChar(char r0, int r1) {
            char[] r12 = new char[r1];
            Arrays.fill(r12, r0);
            return new String(r12);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.mFrames.removeFirst();
        }

        public boolean find(Object r2) {
            Iterator<Frame> r1 = this.mFrames.iterator();
        L4:
            if (r1.hasNext() == false) goto L9;
            if (r1.next().getObj() != r2) goto L4;
            return true;
        L9:
            return false;
        }

        public String toFlatString() {
            StringBuilder r0 = new StringBuilder();
            int r1 = Math.min(this.mFrames.size(), 8);
            Iterator<Frame> r3 = this.mFrames.descendingIterator();
        L4:
            if (r3.hasNext() == false) goto L9;
            int r2 = r1 - 1;
            if (r1 <= 0) goto L9;
            r0.append(r3.next().toFlatString());
            r1 = r2;
        L9:
            if (r3.hasNext() == false) goto L12;
            r0.append("[...]");
        L12:
            return r0.toString();
        }
    }

    public static class TracedBundlerException extends BundlerException {
        public TracedBundlerException(String r2, Trace r3) {
            super(r2 + ", frames: " + r3.toFlatString());
        }

        public TracedBundlerException(String r2, Trace r3, Throwable r4) {
            super(r2 + ", frames: " + r3.toFlatString(), r4);
        }
    }

    static {
        UNOBFUSCATED_TYPE_NAMES = initUnobfuscatedTypeNames();
        BUNDLED_TYPE_NAMES = initBundledTypeNames();
    }

    private static Object deserializeClass(Bundle r3, Trace r4) {
        String r32 = r3.getString("tag_value");
        if (r32 == null) goto L10;
        return Class.forName(r32);
    L6:
        e = move-exception;
        throw new TracedBundlerException("Class name is unknown: ".concat(r32), r4, e);
    L10:
        throw new TracedBundlerException("Class is missing the class name", r4);
    }

    private static Object deserializeCollection(Bundle r3, Collection<Object> r4, Trace r5) {
        ArrayList r32 = r3.getParcelableArrayList("tag_value");
        if (r32 == null) goto L9;
        int r0 = r32.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L7;
        Object r2 = r32.get(r1);
        r1 = r1 + 1;
        r4.add(fromBundle((Bundle) ((Parcelable) r2), r5));
        goto L5
    L7:
        return r4;
    L9:
        throw new TracedBundlerException("Bundle is missing the collection", r5);
    }

    private static Object deserializeEnum(Bundle r6, Trace r7) {
        String r0 = r6.getString("tag_value");
        if (r0 == null) goto L23;
        String r62 = r6.getString("tag_class_name");
        if (r62 == null) goto L21;
        return getClassOrSuperclassMethod(Class.forName(r62), "valueOf", r7).invoke(null, new Object[]{r0});
    L10:
        e = move-exception;
        throw new TracedBundlerException("Enum class [" + r62 + "] not found", r7, e);
    L12:
        e = move-exception;
        throw new TracedBundlerException("Enum value [" + r0 + "] does not exist in enum class [" + r62 + "]", r7, e);
    L8:
        e = move-exception;
        throw new TracedBundlerException("Enum of class [" + r62 + "] missing valueOf method", r7, e);
    L21:
        throw new TracedBundlerException("Missing enum className [" + r62 + "]", r7);
    L23:
        throw new TracedBundlerException("Missing enum name [" + r0 + "]", r7);
    }

    private static Object deserializeIBinder(Bundle r1, Trace r2) {
        IBinder r12 = r1.getBinder("tag_value");
        if (r12 == null) goto L6;
        return r12;
    L6:
        throw new TracedBundlerException("Bundle is missing the binder", r2);
    }

    private static Object deserializeIInterface(Bundle r3, Trace r4) {
        IBinder r0 = r3.getBinder("tag_value");
        if (r0 == null) goto L22;
        String r32 = r3.getString("tag_class_name");
        if (r32 == null) goto L20;
        Object r02 = getClassOrSuperclassMethod(Class.forName(r32), "asInterface", r4).invoke(null, new Object[]{r0});     // Catch: ReflectiveOperationException -> L11 ClassNotFoundException -> L13
        if (r02 == null) goto L10;
        return r02;
    L10:
        throw new TracedBundlerException("Failed to get interface from binder", r4);     // Catch: ReflectiveOperationException -> L11 ClassNotFoundException -> L13
    L13:
        e = move-exception;
        throw new TracedBundlerException("Binder for unknown IInterface: ".concat(r32), r4, e);
    L11:
        e = move-exception;
        throw new TracedBundlerException("Method to create IInterface from a Binder is not accessible for interface: ".concat(r32), r4, e);
    L20:
        throw new TracedBundlerException("Bundle is missing IInterface class name", r4);
    L22:
        throw new TracedBundlerException("Bundle is missing the binder", r4);
    }

    private static Object deserializeImage(Bundle r1, Trace r2) {
        Bundle r12 = r1.getBundle("tag_value");
        if (r12 == null) goto L10;
        IconCompat r13 = IconCompat.createFromBundle(r12);
        if (r13 == null) goto L8;
        return r13;
    L8:
        throw new TracedBundlerException("Failed to create IconCompat from bundle", r2);
    L10:
        throw new TracedBundlerException("IconCompat bundle is null", r2);
    }

    private static Object deserializeList(Bundle r1, Trace r2) {
        return deserializeCollection(r1, new ArrayList(), r2);
    }

    private static Object deserializeMap(Bundle r6, Trace r7) {
        ArrayList r62 = r6.getParcelableArrayList("tag_value");
        if (r62 == null) goto L17;
        HashMap r0 = new HashMap();
        int r1 = r62.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L15;
        Object r3 = r62.get(r2);
        r2 = r2 + 1;
        Bundle r32 = (Bundle) ((Parcelable) r3);
        Bundle r4 = r32.getBundle("tag_1");
        Bundle r33 = r32.getBundle("tag_2");
        if (r4 == null) goto L14;
        Object r42 = fromBundle(r4, r7);
        if (r33 != null) goto L11;
        Object r34 = null;
    L12:
        r0.put(r42, r34);
        goto L5
    L11:
        r34 = fromBundle(r33, r7);
        goto L12
    L14:
        throw new TracedBundlerException("Bundle is missing key", r7);
    L15:
        return r0;
    L17:
        throw new TracedBundlerException("Bundle is missing the map", r7);
    }

    private static Object deserializeObject(Bundle r9, Trace r10) {
        String r1 = r9.getString("tag_class_name");
        if (r1 == null) goto L36;
        Class<?> r2 = Class.forName(r1);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        Constructor<?> r4 = r2.getDeclaredConstructor(null);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        r4.setAccessible(true);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        Object r3 = r4.newInstance(null);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        Iterator<Field> r22 = getFields(r2).iterator();     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
    L5:
        if (r22.hasNext() == false) goto L26;
        Field r42 = r22.next();     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        r42.setAccessible(true);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        String r6 = getFieldName(r42);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        Object r7 = r9.get(r6);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        if (r7 != null) goto L19;
        r7 = r9.get(r6.replaceAll("androidx.core.graphics.drawable.IconCompat", "android.support.v4.graphics.drawable.IconCompat"));     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
    L19:
        if ((r7 instanceof Bundle) == true) goto L20;
        if (r7 != null) goto L5;
        if (Log.isLoggable("CarApp.Bun", 3) == false) goto L5;
        Log.d("CarApp.Bun", "Value is null for field: " + r42);     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        goto L5
    L20:
        r42.set(r3, fromBundle((Bundle) r7, r10));     // Catch: IllegalArgumentException -> L10 ReflectiveOperationException -> L12 NoSuchMethodException -> L14 ClassNotFoundException -> L16
        goto L5
    L26:
        return r3;
    L16:
        e = move-exception;
        throw new TracedBundlerException("Object for unknown class: ".concat(r1), r10, e);
    L10:
        e = move-exception;
        throw new TracedBundlerException("Failed to deserialize class: ".concat(r1), r10, e);
    L14:
        e = move-exception;
        throw new TracedBundlerException("Object missing no args constructor: ".concat(r1), r10, e);
    L12:
        e = move-exception;
        throw new TracedBundlerException("Constructor or field is not accessible: ".concat(r1), r10, e);
    L36:
        throw new TracedBundlerException("Bundle is missing the class name", r10);
    }

    private static Object deserializePerson(Bundle r0) {
        return Person.fromBundle(r0);
    }

    private static Object deserializePrimitive(Bundle r1, Trace r2) {
        Object r12 = r1.get("tag_value");
        if (r12 == null) goto L6;
        return r12;
    L6:
        throw new TracedBundlerException("Bundle is missing the primitive value", r2);
    }

    private static Object deserializeSet(Bundle r1, Trace r2) {
        return deserializeCollection(r1, new HashSet(), r2);
    }

    public static Object fromBundle(Bundle r3) {
        if (Log.isLoggable("CarApp.Bun", 3) == false) goto L6;
        Log.d("CarApp.Bun", "Unbundling " + getBundledTypeName(r3.getInt("tag_class_type")));
    L6:
        return fromBundle(r3, Trace.create());
    }

    private static Object fromBundle(Bundle r3, Trace r4) {
        ClassLoader r1 = Bundler.class.getClassLoader();
        Objects.requireNonNull(r1);
        r3.setClassLoader(r1);
        int r12 = r3.getInt("tag_class_type");
        Trace r42 = Trace.fromParent(r3, Trace.bundleToString(r3), r4);
        switch(r12) {
            case 0: goto L48;
            case 1: goto L44;
            case 2: goto L40;
            case 3: goto L36;
            case 4: goto L32;
            case 5: goto L28;
            case 6: goto L24;
            case 7: goto L20;
            case 8: goto L16;
            case 9: goto L12;
            case 10: goto L8;
            default: goto L5;
        };
    L8:
        Object r32 = deserializePerson(r3);     // Catch: Throwable -> L6
        if (r42 == null) goto L11;
        r42.close();
    L11:
        return r32;
    L12:
        Object r33 = deserializeIBinder(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L15;
        r42.close();
    L15:
        return r33;
    L16:
        Object r34 = deserializeClass(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L19;
        r42.close();
    L19:
        return r34;
    L20:
        Object r35 = deserializeEnum(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L23;
        r42.close();
    L23:
        return r35;
    L24:
        Object r36 = deserializeImage(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L27;
        r42.close();
    L27:
        return r36;
    L28:
        Object r37 = deserializeObject(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L31;
        r42.close();
    L31:
        return r37;
    L32:
        Object r38 = deserializeList(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L35;
        r42.close();
    L35:
        return r38;
    L36:
        Object r39 = deserializeSet(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L39;
        r42.close();
    L39:
        return r39;
    L40:
        Object r310 = deserializeMap(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L43;
        r42.close();
    L43:
        return r310;
    L44:
        Object r311 = deserializeIInterface(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L47;
        r42.close();
    L47:
        return r311;
    L48:
        Object r312 = deserializePrimitive(r3, r42);     // Catch: Throwable -> L6
        if (r42 == null) goto L51;
        r42.close();
    L51:
        return r312;
    L5:
        throw new TracedBundlerException("Unsupported class type in bundle: " + r12, r42);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        if (r42 != null) goto L59;
    L57:
        throw th;
    L59:
        r42.close();     // Catch: Throwable -> L55
    L55:
        th = move-exception;
        th.addSuppressed(th);
        goto L57
    }

    public static String getBundledTypeName(int r1) {
        String r12 = BUNDLED_TYPE_NAMES.get(Integer.valueOf(r1));
        if (r12 != null) goto L6;
        return "unknown";
    L6:
        return r12;
    }

    private static Method getClassOrSuperclassMethod(Class<?> r5, String r6, Trace r7) {
        if (r5 == null) goto L15;
        if (r5 == Object.class) goto L15;
        Method[] r0 = r5.getDeclaredMethods();
        int r1 = r0.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L13;
        Method r3 = r0[r2];
        if (r3.getName().equals(r6) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        r3.setAccessible(true);
        return r3;
    L13:
        return getClassOrSuperclassMethod(r5.getSuperclass(), r6, r7);
    L15:
        throw new TracedBundlerException("No method " + r6 + " in class " + r5, r7);
    }

    public static String getFieldName(String r1, String r2) {
        return r1 + r2;
    }

    public static String getFieldName(Field r1) {
        return getFieldName(r1.getDeclaringClass().getName(), r1.getName());
    }

    private static List<Field> getFields(Class<?> r6) {
        ArrayList r0 = new ArrayList();
        if (r6 != null) goto L5;
    L14:
        return r0;
    L5:
        if (r6 == Object.class) goto L14;
        Field[] r1 = r6.getDeclaredFields();
        int r2 = r1.length;
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L13;
        Field r4 = r1[r3];
        if (Modifier.isStatic(r4.getModifiers()) == true) goto L12;
        r0.add(r4);
    L12:
        r3 = r3 + 1;
        goto L8
    L13:
        r0.addAll(getFields(r6.getSuperclass()));
        goto L14
    }

    public static String getUnobfuscatedClassName(Class<?> r2) {
        String r0 = UNOBFUSCATED_TYPE_NAMES.get(r2);
        if (r0 == null) goto L5;
    L16:
        if (r0 == null) goto L18;
        return r0;
    L18:
        return r2.getSimpleName();
    L5:
        if (List.class.isAssignableFrom(r2) == false) goto L9;
        return "<List>";
    L9:
        if (Map.class.isAssignableFrom(r2) == false) goto L13;
        return "<Map>";
    L13:
        if (Set.class.isAssignableFrom(r2) == false) goto L16;
        return "<Set>";
    }

    private static Map<Integer, String> initBundledTypeNames() {
        ArrayMap r0 = new ArrayMap();
        r0.put(0, "primitive");
        r0.put(1, "iInterface");
        r0.put(9, "iBinder");
        r0.put(2, "map");
        r0.put(3, "set");
        r0.put(4, "list");
        r0.put(5, "object");
        r0.put(6, "image");
        return r0;
    }

    private static Map<Class<?>, String> initUnobfuscatedTypeNames() {
        ArrayMap r0 = new ArrayMap();
        r0.put(Boolean.class, "bool");
        r0.put(Byte.class, "byte");
        r0.put(Short.class, "short");
        r0.put(Integer.class, "int");
        r0.put(Long.class, "long");
        r0.put(Double.class, "double");
        r0.put(Float.class, "float");
        r0.put(String.class, "string");
        r0.put(Parcelable.class, "parcelable");
        r0.put(Map.class, "map");
        r0.put(List.class, "list");
        r0.put(IconCompat.class, "image");
        return r0;
    }

    public static boolean isPrimitiveType(Object r1) {
        if ((r1 instanceof Boolean) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof Byte) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof Character) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof Short) == false) goto L11;
        return true;
    L11:
        if ((r1 instanceof Integer) == false) goto L13;
        return true;
    L13:
        if ((r1 instanceof Long) == false) goto L15;
        return true;
    L15:
        if ((r1 instanceof Double) == false) goto L17;
        return true;
    L17:
        if ((r1 instanceof Float) == false) goto L19;
        return true;
    L19:
        if ((r1 instanceof String) == true) goto L32;
        return false;
    L32:
        return true;
    }

    private static Bundle serializeClass(Class<?> r3) {
        Bundle r0 = new Bundle(2);
        r0.putInt("tag_class_type", 8);
        r0.putString("tag_value", r3.getName());
        return r0;
    }

    private static Bundle serializeCollection(Collection<Object> r6, Trace r7) {
        Bundle r0 = new Bundle(2);
        ArrayList<? extends Parcelable> r1 = new ArrayList();
        Iterator<Object> r62 = r6.iterator();
        int r2 = 0;
    L4:
        if (r62.hasNext() == false) goto L6;
        r1.add(toBundle(r62.next(), "<item " + r2 + ">", r7));
        r2 = r2 + 1;
        goto L4
    L6:
        r0.putParcelableArrayList("tag_value", r1);
        return r0;
    }

    private static Bundle serializeEnum(Object r3, Trace r4) {
        Bundle r0 = new Bundle(3);
        r0.putInt("tag_class_type", 7);
        r0.putString("tag_value", (String) getClassOrSuperclassMethod(r3.getClass(), "name", r4).invoke(r3, null));
        r0.putString("tag_class_name", r3.getClass().getName());
        return r0;
    L6:
        e = move-exception;
        throw new TracedBundlerException("Enum missing name method", r4, e);
    }

    private static Bundle serializeIBinder(IBinder r3) {
        Bundle r0 = new Bundle(2);
        r0.putInt("tag_class_type", 9);
        r0.putBinder("tag_value", r3);
        return r0;
    }

    private static Bundle serializeIInterface(IInterface r4) {
        Bundle r0 = new Bundle(3);
        String r1 = r4.getClass().getName();
        r0.putInt("tag_class_type", 1);
        r0.putBinder("tag_value", r4.asBinder());
        r0.putString("tag_class_name", r1);
        return r0;
    }

    private static Bundle serializeImage(IconCompat r3) {
        Bundle r0 = new Bundle(2);
        r0.putInt("tag_class_type", 6);
        r0.putBundle("tag_value", r3.toBundle());
        return r0;
    }

    private static Bundle serializeList(List<Object> r1, Trace r2) {
        Bundle r12 = serializeCollection(r1, r2);
        r12.putInt("tag_class_type", 4);
        return r12;
    }

    private static Bundle serializeMap(Map<Object, Object> r9, Trace r10) {
        Bundle r0 = new Bundle(2);
        ArrayList<? extends Parcelable> r2 = new ArrayList();
        Iterator<Map.Entry<Object, Object>> r92 = r9.entrySet().iterator();
        int r3 = 0;
    L4:
        if (r92.hasNext() == false) goto L9;
        Map.Entry<Object, Object> r4 = r92.next();
        Bundle r5 = new Bundle(2);
        r5.putBundle("tag_1", toBundle(r4.getKey(), "<key " + r3 + ">", r10));
        if (r4.getValue() == null) goto L8;
        r5.putBundle("tag_2", toBundle(r4.getValue(), "<value " + r3 + ">", r10));
    L8:
        r3 = r3 + 1;
        r2.add(r5);
        goto L4
    L9:
        r0.putInt("tag_class_type", 2);
        r0.putParcelableArrayList("tag_value", r2);
        return r0;
    }

    private static Bundle serializeObject(Object r5, Trace r6) {
        String r0 = r5.getClass().getName();
        r5.getClass().getDeclaredConstructor(null);     // Catch: NoSuchMethodException -> L15
        List<Field> r1 = getFields(r5.getClass());
        Bundle r2 = new Bundle(r1.size() + 2);
        r2.putInt("tag_class_type", 5);
        r2.putString("tag_class_name", r0);
        Iterator<Field> r02 = r1.iterator();
    L6:
        if (r02.hasNext() == false) goto L14;
        Field r12 = r02.next();
        r12.setAccessible(true);
        String r3 = getFieldName(r12);
        Object r4 = r12.get(r5);     // Catch: IllegalAccessException -> L11
        if (r4 == null) goto L6;
        r2.putParcelable(r3, toBundle(r4, r12.getName(), r6));
    L11:
        e = move-exception;
        throw new TracedBundlerException("Field is not accessible: " + r3, r6, e);
    L14:
        return r2;
    L15:
        e = move-exception;
        throw new TracedBundlerException("Class to deserialize is missing a no args constructor: ".concat(r0), r6, e);
    }

    private static Bundle serializePerson(Person r2) {
        Bundle r22 = r2.toBundle();
        r22.putInt("tag_class_type", 10);
        return r22;
    }

    private static Bundle serializePrimitive(Object r3, Trace r4) {
        Bundle r0 = new Bundle(2);
        r0.putInt("tag_class_type", 0);
        if ((r3 instanceof Boolean) == false) goto L7;
        r0.putBoolean("tag_value", ((Boolean) r3).booleanValue());
        return r0;
    L7:
        if ((r3 instanceof Byte) == false) goto L11;
        r0.putByte("tag_value", ((Byte) r3).byteValue());
        return r0;
    L11:
        if ((r3 instanceof Character) == false) goto L15;
        r0.putChar("tag_value", ((Character) r3).charValue());
        return r0;
    L15:
        if ((r3 instanceof Short) == false) goto L19;
        r0.putShort("tag_value", ((Short) r3).shortValue());
        return r0;
    L19:
        if ((r3 instanceof Integer) == false) goto L23;
        r0.putInt("tag_value", ((Integer) r3).intValue());
        return r0;
    L23:
        if ((r3 instanceof Long) == false) goto L27;
        r0.putLong("tag_value", ((Long) r3).longValue());
        return r0;
    L27:
        if ((r3 instanceof Double) == false) goto L31;
        r0.putDouble("tag_value", ((Double) r3).doubleValue());
        return r0;
    L31:
        if ((r3 instanceof Float) == false) goto L35;
        r0.putFloat("tag_value", ((Float) r3).floatValue());
        return r0;
    L35:
        if ((r3 instanceof String) == false) goto L39;
        r0.putString("tag_value", (String) r3);
        return r0;
    L39:
        if ((r3 instanceof Parcelable) == false) goto L43;
        r0.putParcelable("tag_value", (Parcelable) r3);
        return r0;
    L43:
        throw new TracedBundlerException("Unsupported primitive type: ".concat(r3.getClass().getName()), r4);
    }

    private static Bundle serializeSet(Set<Object> r1, Trace r2) {
        Bundle r12 = serializeCollection(r1, r2);
        r12.putInt("tag_class_type", 3);
        return r12;
    }

    public static Bundle toBundle(Object r4) {
        String r0 = getUnobfuscatedClassName(r4.getClass());
        if (Log.isLoggable("CarApp.Bun", 3) == false) goto L6;
        Log.d("CarApp.Bun", "Bundling " + r0);
    L6:
        return toBundle(r4, r0, Trace.create());
    }

    private static Bundle toBundle(Object r1, String r2, Trace r3) {
        if (r1 != null) goto L4;
    L8:
        Trace r22 = Trace.fromParent(r1, r2, r3);
        if (r1 == null) goto L84;
        if ((r1 instanceof IconCompat) == false) goto L19;
        Bundle r12 = serializeImage((IconCompat) r1);     // Catch: Throwable -> L16
        if (r22 == null) goto L15;
        r22.close();
    L15:
        return r12;
    L19:
        if (isPrimitiveType(r1) == false) goto L21;
    L79:
        Bundle r13 = serializePrimitive(r1, r22);     // Catch: Throwable -> L16
        if (r22 == null) goto L82;
        r22.close();
    L82:
        return r13;
    L21:
        if ((r1 instanceof Parcelable) == true) goto L79;
        if ((r1 instanceof IInterface) == false) goto L30;
        Bundle r14 = serializeIInterface((IInterface) r1);     // Catch: Throwable -> L16
        if (r22 == null) goto L28;
        r22.close();
    L28:
        return r14;
    L30:
        if ((r1 instanceof IBinder) == false) goto L36;
        Bundle r15 = serializeIBinder((IBinder) r1);     // Catch: Throwable -> L16
        if (r22 == null) goto L34;
        r22.close();
    L34:
        return r15;
    L36:
        if ((r1 instanceof Map) == false) goto L42;
        Bundle r16 = serializeMap((Map) r1, r22);     // Catch: Throwable -> L16
        if (r22 == null) goto L40;
        r22.close();
    L40:
        return r16;
    L42:
        if ((r1 instanceof List) == false) goto L48;
        Bundle r17 = serializeList((List) r1, r22);     // Catch: Throwable -> L16
        if (r22 == null) goto L46;
        r22.close();
    L46:
        return r17;
    L48:
        if ((r1 instanceof Set) == false) goto L54;
        Bundle r18 = serializeSet((Set) r1, r22);     // Catch: Throwable -> L16
        if (r22 == null) goto L52;
        r22.close();
    L52:
        return r18;
    L54:
        if (r1.getClass().isEnum() == false) goto L60;
        Bundle r19 = serializeEnum(r1, r22);     // Catch: Throwable -> L16
        if (r22 == null) goto L58;
        r22.close();
    L58:
        return r19;
    L60:
        if ((r1 instanceof Class) == false) goto L66;
        Bundle r110 = serializeClass((Class) r1);     // Catch: Throwable -> L16
        if (r22 == null) goto L64;
        r22.close();
    L64:
        return r110;
    L66:
        if (r1.getClass().isArray() == true) goto L78;
        if ((r1 instanceof Person) == false) goto L73;
        Bundle r111 = serializePerson((Person) r1);     // Catch: Throwable -> L16
        if (r22 == null) goto L72;
        r22.close();
    L72:
        return r111;
    L73:
        Bundle r112 = serializeObject(r1, r22);     // Catch: Throwable -> L16
        if (r22 == null) goto L76;
        r22.close();
    L76:
        return r112;
    L78:
        throw new TracedBundlerException("Object serializing contains an array, use a list or a set instead", r22);     // Catch: Throwable -> L16
    L84:
        throw new TracedBundlerException("Bundling of null object is not supported", r22);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        if (r22 != null) goto L92;
    L90:
        throw th;
    L92:
        r22.close();     // Catch: Throwable -> L88
    L88:
        th = move-exception;
        th.addSuppressed(th);
        goto L90
    L4:
        if (r3.find(r1) == false) goto L8;
        throw new CycleDetectedBundlerException("Found cycle while bundling type ".concat(r1.getClass().getSimpleName()), r3);
    }
}
