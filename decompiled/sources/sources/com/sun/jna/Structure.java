package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline1;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public abstract class Structure {
    public static final int ALIGN_DEFAULT = 0;
    public static final int ALIGN_GNUC = 2;
    public static final int ALIGN_MSVC = 3;
    public static final int ALIGN_NONE = 1;
    protected static final int CALCULATE_SIZE = -1;
    private static final Logger LOG = null;
    private static final Pointer PLACEHOLDER_MEMORY = null;
    private static final ThreadLocal<Set<Structure>> busy = null;
    static final ReentrantReadWriteLock cacheStructureLock = null;
    static final Map<Class<?>, List<Field>> fieldList = null;
    static final Map<Class<?>, List<String>> fieldOrder = null;
    static final Map<Class<?>, LayoutInfo> layoutInfo = null;
    private static final ThreadLocal<Map<Pointer, Structure>> reads = null;
    static final Map<Class<?>, Boolean> validationMap = null;
    private int actualAlignType;
    private int alignType;
    private Structure[] array;
    private boolean autoRead;
    private boolean autoWrite;
    private String encoding;
    private Pointer memory;
    private final Map<String, NativeStringTracking> nativeStrings;
    private boolean readCalled;
    private int size;
    private int structAlignment;
    private Map<String, StructField> structFields;
    private long typeInfo;
    private TypeMapper typeMapper;

    /* renamed from: com.sun.jna.Structure$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<Map<Pointer, Structure>> {
        public AnonymousClass1() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ Map<Pointer, Structure> initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public synchronized Map<Pointer, Structure> initialValue2() {
            monitor-enter(this);
            HashMap r0 = new HashMap();     // Catch: Throwable -> L6
            monitor-exit(this);
            return r0;
        L6:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: com.sun.jna.Structure$2, reason: invalid class name */
    public class AnonymousClass2 extends ThreadLocal<Set<Structure>> {
        public AnonymousClass2() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ Set<Structure> initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public synchronized Set<Structure> initialValue2() {
            monitor-enter(this);
            StructureSet r0 = new StructureSet();     // Catch: Throwable -> L6
            monitor-exit(this);
            return r0;
        L6:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: com.sun.jna.Structure$3, reason: invalid class name */
    public class AnonymousClass3 extends Pointer {
        public AnonymousClass3(long r1) {
            super(r1);
        }

        @Override // com.sun.jna.Pointer
        public Pointer share(long r1, long r3) {
            return this;
        }
    }

    public static class AutoAllocated extends Memory {
        public AutoAllocated(int r3) {
            super(r3);
            super.clear();
        }

        @Override // com.sun.jna.Memory, com.sun.jna.Pointer
        public String toString() {
            return "auto-" + super.toString();
        }
    }

    public interface ByReference {
    }

    public interface ByValue {
    }

    @FieldOrder({"size", "alignment", "type", "elements"})
    public static class FFIType extends Structure {
        private static final int FFI_TYPE_STRUCT = 13;
        private static final Map<Pointer, FFIType> ffiTypeInfo = null;
        private static final Map<Class, Map<Integer, FFIType>> typeInfoMap = null;
        private static final Map<Class, FFIType> unionHelper = null;
        public short alignment;
        public Pointer elements;
        public size_t size;
        public short type;

        public static class FFITypes {
            private static Pointer ffi_type_double;
            private static Pointer ffi_type_float;
            private static Pointer ffi_type_longdouble;
            private static Pointer ffi_type_pointer;
            private static Pointer ffi_type_sint16;
            private static Pointer ffi_type_sint32;
            private static Pointer ffi_type_sint64;
            private static Pointer ffi_type_sint8;
            private static Pointer ffi_type_uint16;
            private static Pointer ffi_type_uint32;
            private static Pointer ffi_type_uint64;
            private static Pointer ffi_type_uint8;
            private static Pointer ffi_type_void;

            private FFITypes() {
            }

            public static /* synthetic */ Pointer access$1000() {
                return ffi_type_uint8;
            }

            public static /* synthetic */ Pointer access$1100() {
                return ffi_type_sint8;
            }

            public static /* synthetic */ Pointer access$1200() {
                return ffi_type_uint16;
            }

            public static /* synthetic */ Pointer access$1300() {
                return ffi_type_sint16;
            }

            public static /* synthetic */ Pointer access$1400() {
                return ffi_type_uint32;
            }

            public static /* synthetic */ Pointer access$1500() {
                return ffi_type_sint32;
            }

            public static /* synthetic */ Pointer access$1600() {
                return ffi_type_uint64;
            }

            public static /* synthetic */ Pointer access$1700() {
                return ffi_type_sint64;
            }

            public static /* synthetic */ Pointer access$1800() {
                return ffi_type_pointer;
            }

            public static /* synthetic */ Pointer access$1900() {
                return ffi_type_float;
            }

            public static /* synthetic */ Pointer access$2000() {
                return ffi_type_double;
            }

            public static /* synthetic */ Pointer access$2100() {
                return ffi_type_void;
            }

            public static /* synthetic */ Pointer access$2200() {
                return ffi_type_longdouble;
            }
        }

        public static class size_t extends IntegerType {
            private static final long serialVersionUID = 1;

            public size_t() {
                this(0);
            }

            public size_t(long r2) {
                super(Native.SIZE_T_SIZE, r2);
            }
        }

        public static /* synthetic */ Map $r8$lambda$b_ne5t9lqNZj68Q2cYte0XRI88Y(Class r0) {
            return new HashMap();
        }

        static {
            typeInfoMap = new WeakHashMap();
            unionHelper = new WeakHashMap();
            HashMap r0 = new HashMap();
            ffiTypeInfo = r0;
            if (Native.POINTER_SIZE == 0) goto L20;
            if (FFITypes.access$2100() == null) goto L18;
            r0.put(FFITypes.access$2100(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$2100()));
            r0.put(FFITypes.access$1900(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1900()));
            r0.put(FFITypes.access$2000(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$2000()));
            r0.put(FFITypes.access$2200(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$2200()));
            r0.put(FFITypes.access$1000(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1000()));
            r0.put(FFITypes.access$1100(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1100()));
            r0.put(FFITypes.access$1200(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1200()));
            r0.put(FFITypes.access$1300(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1300()));
            r0.put(FFITypes.access$1400(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1400()));
            r0.put(FFITypes.access$1500(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1500()));
            r0.put(FFITypes.access$1600(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1600()));
            r0.put(FFITypes.access$1700(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1700()));
            r0.put(FFITypes.access$1800(), (FFIType) Structure.newInstance(FFIType.class, FFITypes.access$1800()));
            Iterator r02 = r0.values().iterator();
        L8:
            if (r02.hasNext() == false) goto L10;
            ((FFIType) r02.next()).read();
            goto L8
        L10:
            Map<Pointer, FFIType> r03 = ffiTypeInfo;
            FFIType r1 = r03.get(FFITypes.access$2100());
            storeTypeInfo(Void.TYPE, r1);
            storeTypeInfo(Void.class, r03.get(FFITypes.access$2100()));
            FFIType r12 = r03.get(FFITypes.access$1900());
            storeTypeInfo(Float.TYPE, r12);
            storeTypeInfo(Float.class, r03.get(FFITypes.access$1900()));
            FFIType r13 = r03.get(FFITypes.access$2000());
            storeTypeInfo(Double.TYPE, r13);
            storeTypeInfo(Double.class, r03.get(FFITypes.access$2000()));
            FFIType r14 = r03.get(FFITypes.access$1700());
            storeTypeInfo(Long.TYPE, r14);
            storeTypeInfo(Long.class, r03.get(FFITypes.access$1700()));
            FFIType r15 = r03.get(FFITypes.access$1500());
            storeTypeInfo(Integer.TYPE, r15);
            storeTypeInfo(Integer.class, r03.get(FFITypes.access$1500()));
            FFIType r16 = r03.get(FFITypes.access$1300());
            storeTypeInfo(Short.TYPE, r16);
            storeTypeInfo(Short.class, r03.get(FFITypes.access$1300()));
            if (Native.WCHAR_SIZE != 2) goto L14;
            Pointer r17 = FFITypes.access$1200();
        L13:
            FFIType r18 = r03.get(r17);
            storeTypeInfo(Character.TYPE, r18);
            storeTypeInfo(Character.class, r18);
            FFIType r19 = r03.get(FFITypes.access$1100());
            storeTypeInfo(Byte.TYPE, r19);
            storeTypeInfo(Byte.class, r03.get(FFITypes.access$1100()));
            storeTypeInfo(Pointer.class, r03.get(FFITypes.access$1800()));
            storeTypeInfo(String.class, r03.get(FFITypes.access$1800()));
            storeTypeInfo(WString.class, r03.get(FFITypes.access$1800()));
            FFIType r110 = r03.get(FFITypes.access$1400());
            storeTypeInfo(Boolean.TYPE, r110);
            storeTypeInfo(Boolean.class, r03.get(FFITypes.access$1400()));
            return;
        L14:
            r17 = FFITypes.access$1400();
            goto L13
        L18:
            throw new Error("FFI types not initialized");
        L20:
            throw new Error("Native library not initialized");
        }

        public FFIType() {
            this.type = 13;
        }

        public FFIType(FFIType r2) {
            this.type = 13;
            this.size = r2.size;
            this.alignment = r2.alignment;
            this.type = r2.type;
            this.elements = r2.elements;
        }

        public FFIType(Structure r12) {
            this.type = 13;
            Structure.access$2300(r12, true);
            int r2 = 0;
            if ((r12 instanceof Union) == false) goto L38;
            Iterator<StructField> r1 = r12.fields().values().iterator();
            int r5 = 0;
            boolean r6 = false;
            FFIType r4 = null;
        L6:
            if (r1.hasNext() == false) goto L18;
            StructField r7 = r1.next();
            FFIType r8 = r12.getFieldTypeInfo(r7);
            if (isIntegerType(r8) == false) goto L10;
            r6 = true;
        L10:
            if (r4 == null) goto L16;
            int r9 = r7.size;
            if (r5 < r9) goto L16;
            if (r5 != r9) goto L6;
            if (Structure.class.isAssignableFrom(r7.type) == false) goto L6;
        L16:
            r5 = r7.size;
            r4 = r8;
            goto L6
        L18:
            if (Platform.isIntel() == false) goto L24;
            if (Platform.is64Bit() == false) goto L24;
            if (Platform.isWindows() == true) goto L24;
        L27:
            if (r6 == true) goto L29;
        L37:
            Pointer[] r13 = {r4.getPointer(), null};
            unionHelper.put(r12.getClass(), r4);
        L42:
            init(r13);
            write();
            return;
        L29:
            if (isFloatType(r4) == false) goto L37;
            FFIType r14 = new FFIType(r4);
            if (r14.size.intValue() != 4) goto L34;
            r14.type = ffiTypeInfo.get(FFITypes.access$1400()).type;
        L36:
            r14.write();
            r4 = r14;
            goto L37
        L34:
            if (r14.size.intValue() != 8) goto L36;
            r14.type = ffiTypeInfo.get(FFITypes.access$1600()).type;
        L24:
            if (Platform.isARM() == true) goto L27;
            if (Platform.isLoongArch() == false) goto L37;
        L38:
            r13 = new Pointer[r12.fields().size() + 1];
            Iterator<StructField> r0 = r12.fields().values().iterator();
        L40:
            if (r0.hasNext() == false) goto L42;
            r13[r2] = r12.getFieldTypeInfo(r0.next()).getPointer();
            r2 = r2 + 1;
            goto L40
        }

        public FFIType(Object r3, Class<?> r4) {
            this.type = 13;
            int r32 = Array.getLength(r3);
            Pointer[] r0 = new Pointer[r32 + 1];
            Pointer r42 = get(null, r4.getComponentType()).getPointer();
            int r1 = 0;
        L3:
            if (r1 >= r32) goto L5;
            r0[r1] = r42;
            r1 = r1 + 1;
            goto L3
        L5:
            init(r0);
            write();
        }

        public static /* synthetic */ FFIType access$900(Object r0, Class r1) {
            return get(r0, r1);
        }

        public static FFIType get(Object r1) {
            if (r1 != null) goto L6;
            return getTypeInfo(Pointer.class, 0);
        L6:
            if ((r1 instanceof Class) == false) goto L10;
            return get(null, (Class) r1);
        L10:
            return get(r1, r1.getClass());
        }

        private static FFIType get(Object r4, Class<?> r5) {
            int r0 = 0;
            TypeMapper r2 = Native.getTypeMapper(r5);
            if (r2 == null) goto L8;
            ToNativeConverter r22 = r2.getToNativeConverter(r5);
            if (r22 == null) goto L8;
            r5 = r22.nativeType();
        L8:
            if (r5.isArray() == false) goto L10;
            r0 = Array.getLength(r4);
        L10:
            FFIType r02 = getTypeInfo(r5, r0);
            if (r02 == null) goto L13;
            return r02;
        L13:
            ReentrantReadWriteLock r03 = Structure.cacheStructureLock;
            r03.writeLock().lock();
        L46:
            th = move-exception;
            Structure.cacheStructureLock.writeLock().unlock();
            throw th;
        L16:
            if (Platform.HAS_BUFFERS == false) goto L20;
            if (Buffer.class.isAssignableFrom(r5) == false) goto L20;
        L21:
            Map<Class, Map<Integer, FFIType>> r42 = typeInfoMap;     // Catch: Throwable -> L46
            r42.put(r5, r42.get(Pointer.class));     // Catch: Throwable -> L46
            FFIType r43 = r42.get(Pointer.class).get(0);     // Catch: Throwable -> L46
            r03.writeLock().unlock();
            return r43;
        L20:
            if (Callback.class.isAssignableFrom(r5) == true) goto L21;
            if (Structure.class.isAssignableFrom(r5) == false) goto L36;
            if (r4 != null) goto L29;
            r4 = Structure.newInstance(r5, Structure.access$2400());     // Catch: Throwable -> L46
        L29:
            if (ByReference.class.isAssignableFrom(r5) == false) goto L33;
            Map<Class, Map<Integer, FFIType>> r44 = typeInfoMap;     // Catch: Throwable -> L46
            r44.put(r5, r44.get(Pointer.class));     // Catch: Throwable -> L46
            FFIType r45 = r44.get(Pointer.class).get(0);     // Catch: Throwable -> L46
            r03.writeLock().unlock();
            return r45;
        L33:
            FFIType r1 = new FFIType((Structure) r4);     // Catch: Throwable -> L46
            storeTypeInfo(r5, r1);     // Catch: Throwable -> L46
            r03.writeLock().unlock();
            return r1;
        L36:
            r03.writeLock().unlock();
            if (NativeMapped.class.isAssignableFrom(r5) == false) goto L41;
            NativeMappedConverter r52 = NativeMappedConverter.getInstance(r5);
            return get(r52.toNative(r4, new ToNativeContext()), r52.nativeType());
        L41:
            if (r5.isArray() == false) goto L44;
            FFIType r04 = new FFIType(r4, r5);
            storeTypeInfo(r5, Array.getLength(r4), r04);
            return r04;
        L44:
            Native$$ExternalSyntheticBUOutline5.m("Unsupported type ", r5);
            return null;
        }

        private static FFIType getTypeInfo(Class r2, int r3) {
            ReentrantReadWriteLock r0 = Structure.cacheStructureLock;
            r0.readLock().lock();
            Map<Integer, FFIType> r22 = typeInfoMap.get(r2);     // Catch: Throwable -> L10
            if (r22 == null) goto L8;
            FFIType r23 = r22.get(Integer.valueOf(r3));     // Catch: Throwable -> L10
            r0.readLock().unlock();
            return r23;
        L8:
            r0.readLock().unlock();
            return null;
        L10:
            th = move-exception;
            Structure.cacheStructureLock.readLock().unlock();
            throw th;
        }

        private void init(Pointer[] r7) {
            Memory r0 = new Memory(Native.POINTER_SIZE * r7.length);
            this.elements = r0;
            r0.write(0, r7, 0, r7.length);
            write();
        }

        private static boolean isFloatType(FFIType r1) {
            Pointer r12 = r1.getPointer();
            if (r12.equals(FFITypes.access$1900()) == false) goto L5;
            return true;
        L5:
            if (r12.equals(FFITypes.access$2000()) == true) goto L11;
            return false;
        L11:
            return true;
        }

        private static boolean isIntegerType(FFIType r1) {
            Pointer r12 = r1.getPointer();
            if (r12.equals(FFITypes.access$1000()) == false) goto L5;
            return true;
        L5:
            if (r12.equals(FFITypes.access$1100()) == false) goto L7;
            return true;
        L7:
            if (r12.equals(FFITypes.access$1200()) == false) goto L9;
            return true;
        L9:
            if (r12.equals(FFITypes.access$1300()) == false) goto L11;
            return true;
        L11:
            if (r12.equals(FFITypes.access$1400()) == false) goto L13;
            return true;
        L13:
            if (r12.equals(FFITypes.access$1500()) == false) goto L15;
            return true;
        L15:
            if (r12.equals(FFITypes.access$1600()) == false) goto L17;
            return true;
        L17:
            if (r12.equals(FFITypes.access$1700()) == false) goto L19;
            return true;
        L19:
            if (r12.equals(FFITypes.access$1800()) == true) goto L32;
            return false;
        L32:
            return true;
        }

        private static void storeTypeInfo(Class r3, int r4, FFIType r5) {
            ReentrantReadWriteLock r0 = Structure.cacheStructureLock;
            r0.writeLock().lock();
            typeInfoMap.computeIfAbsent(r3, new Structure$FFIType$$ExternalSyntheticLambda0()).put(Integer.valueOf(r4), r5);     // Catch: Throwable -> L6
            r0.writeLock().unlock();
            return;
        L6:
            th = move-exception;
            Structure.cacheStructureLock.writeLock().unlock();
            throw th;
        }

        private static void storeTypeInfo(Class r1, FFIType r2) {
            storeTypeInfo(r1, 0, r2);
        }
    }

    @Target({ElementType.TYPE})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldOrder {
        String[] value();
    }

    public static class LayoutInfo {
        private int alignType;
        private int alignment;
        private final Map<String, StructField> fields;
        private int size;
        private TypeMapper typeMapper;
        private boolean variable;

        private LayoutInfo() {
            this.size = -1;
            this.alignment = 1;
            this.fields = Collections.synchronizedMap(new LinkedHashMap());
            this.alignType = 0;
        }

        public /* synthetic */ LayoutInfo(AnonymousClass1 r1) {
            this();
        }

        public static /* synthetic */ boolean access$200(LayoutInfo r0) {
            return r0.variable;
        }

        public static /* synthetic */ boolean access$202(LayoutInfo r0, boolean r1) {
            r0.variable = r1;
            return r1;
        }

        public static /* synthetic */ int access$300(LayoutInfo r0) {
            return r0.size;
        }

        public static /* synthetic */ int access$302(LayoutInfo r0, int r1) {
            r0.size = r1;
            return r1;
        }

        public static /* synthetic */ int access$400(LayoutInfo r0) {
            return r0.alignType;
        }

        public static /* synthetic */ int access$402(LayoutInfo r0, int r1) {
            r0.alignType = r1;
            return r1;
        }

        public static /* synthetic */ TypeMapper access$500(LayoutInfo r0) {
            return r0.typeMapper;
        }

        public static /* synthetic */ TypeMapper access$502(LayoutInfo r0, TypeMapper r1) {
            r0.typeMapper = r1;
            return r1;
        }

        public static /* synthetic */ int access$600(LayoutInfo r0) {
            return r0.alignment;
        }

        public static /* synthetic */ int access$602(LayoutInfo r0, int r1) {
            r0.alignment = r1;
            return r1;
        }

        public static /* synthetic */ Map access$700(LayoutInfo r0) {
            return r0.fields;
        }
    }

    public static class NativeStringTracking {
        private NativeString peer;
        private final Object value;

        public NativeStringTracking(Object r1) {
            this.value = r1;
        }

        public static /* synthetic */ NativeString access$000(NativeStringTracking r0) {
            return r0.peer;
        }

        public static /* synthetic */ NativeString access$002(NativeStringTracking r0, NativeString r1) {
            r0.peer = r1;
            return r1;
        }

        public static /* synthetic */ Object access$100(NativeStringTracking r0) {
            return r0.value;
        }
    }

    public static class StructField {
        public FromNativeContext context;
        public Field field;
        public boolean isReadOnly;
        public boolean isVolatile;
        public String name;
        public int offset;
        public FromNativeConverter readConverter;
        public int size;
        public Class<?> type;
        public ToNativeConverter writeConverter;

        public StructField() {
            this.size = -1;
            this.offset = -1;
        }

        public String toString() {
            return this.name + "@" + this.offset + "[" + this.size + "] (" + this.type + ")";
        }
    }

    public static class StructureSet extends AbstractCollection<Structure> implements Set<Structure> {
        private int count;
        Structure[] elements;

        public StructureSet() {
        }

        private void ensureCapacity(int r4) {
            Structure[] r0 = this.elements;
            if (r0 != null) goto L7;
            this.elements = new Structure[(r4 * 3) / 2];
            return;
        L7:
            if (r0.length >= r4) goto L10;
            Structure[] r42 = new Structure[(r4 * 3) / 2];
            System.arraycopy(r0, 0, r42, 0, r0.length);
            this.elements = r42;
            return;
        }

        private int indexOf(Structure r5) {
            int r0 = 0;
        L4:
            if (r0 >= this.count) goto L16;
            Structure r1 = this.elements[r0];
            if (r5 == r1) goto L15;
            if (r5.getClass() != r1.getClass()) goto L14;
            if (r5.size() != r1.size()) goto L14;
            if (r5.getPointer().equals(r1.getPointer()) == true) goto L15;
        L14:
            r0 = r0 + 1;
        L15:
            return r0;
        L16:
            return -1;
        }

        public boolean add(Structure r5) {
            if (contains(r5) == true) goto L6;
            ensureCapacity(this.count + 1);
            Structure[] r0 = this.elements;
            int r2 = this.count;
            this.count = r2 + 1;
            r0[r2] = r5;
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean add(Object r1) {
            return add((Structure) r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r1) {
            if (indexOf((Structure) r1) == (-1)) goto L6;
            return true;
        L6:
            return false;
        }

        public Structure[] getElements() {
            return this.elements;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Structure> iterator() {
            int r0 = this.count;
            Structure[] r1 = new Structure[r0];
            if (r0 <= 0) goto L6;
            System.arraycopy(this.elements, 0, r1, 0, r0);
        L6:
            return Arrays.asList(r1).iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object r4) {
            int r42 = indexOf((Structure) r4);
            if (r42 == (-1)) goto L8;
            int r0 = this.count - 1;
            this.count = r0;
            if (r0 < 0) goto L7;
            Structure[] r3 = this.elements;
            r3[r42] = r3[r0];
            r3[r0] = null;
        L7:
            return true;
        L8:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.count;
        }
    }

    public static /* synthetic */ List $r8$lambda$9lm3vt8_J3MWcupjbgJuDluOqOY(Class r8, Class r9) {
        ArrayList r92 = new ArrayList();
        ArrayList r0 = new ArrayList();
    L4:
        if (r8.equals(Structure.class) == true) goto L15;
        Field[] r1 = r8.getDeclaredFields();
        int r2 = r1.length;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L14;
        Field r5 = r1[r4];
        int r6 = r5.getModifiers();
        if (Modifier.isStatic(r6) == true) goto L13;
        if (Modifier.isPublic(r6) == false) goto L13;
        r0.add(r5);
    L13:
        r4 = r4 + 1;
        goto L6
    L14:
        r92.addAll(0, r0);
        r0.clear();
        r8 = r8.getSuperclass();
        goto L4
    L15:
        return r92;
    }

    /* renamed from: $r8$lambda$nj-ZwQrjQTV1uYR6LCM-zhd7P9U, reason: not valid java name */
    public static /* synthetic */ List m2007$r8$lambda$njZwQrjQTV1uYR6LCMzhd7P9U(Structure r0, Class r1) {
        return r0.getFieldOrder();
    }

    public static /* synthetic */ Boolean $r8$lambda$sGfNwEHp_1PyC3MjWDlWDhZGVOM(Structure r2, Class r3) {
        Iterator<Field> r32 = r2.getFieldList().iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        Field r0 = r32.next();
        r2.validateField(r0.getName(), r0.getType());
        goto L4
    L7:
        return Boolean.TRUE;
    }

    static {
        LOG = Logger.getLogger(Structure.class.getName());
        cacheStructureLock = new ReentrantReadWriteLock();
        layoutInfo = new WeakHashMap();
        fieldOrder = new WeakHashMap();
        fieldList = new WeakHashMap();
        validationMap = new WeakHashMap();
        reads = new AnonymousClass1();
        busy = new AnonymousClass2();
        PLACEHOLDER_MEMORY = new AnonymousClass3(0);
    }

    public Structure() {
        this(0);
    }

    public Structure(int r2) {
        this(null, r2);
    }

    public Structure(int r2, TypeMapper r3) {
        this(null, r2, r3);
    }

    public Structure(Pointer r2) {
        this(r2, 0);
    }

    public Structure(Pointer r2, int r3) {
        this(r2, r3, null);
    }

    public Structure(Pointer r4, int r5, TypeMapper r6) {
        this.size = -1;
        this.nativeStrings = new HashMap(8);
        this.autoRead = true;
        this.autoWrite = true;
        setAlignType(r5);
        setStringEncoding(Native.getStringEncoding(getClass()));
        initializeTypeMapper(r6);
        validateFields();
        if (r4 == null) goto L5;
        useMemory(r4, 0, true);
    L6:
        initializeFields();
        return;
    L5:
        allocateMemory(-1);
        goto L6
    }

    public Structure(TypeMapper r3) {
        this(null, 0, r3);
    }

    public static /* synthetic */ void access$2300(Structure r0, boolean r1) {
        r0.ensureAllocated(r1);
    }

    public static /* synthetic */ Pointer access$2400() {
        return PLACEHOLDER_MEMORY;
    }

    private int addPadding(int r2) {
        return addPadding(r2, this.structAlignment);
    }

    private int addPadding(int r2, int r3) {
        if (this.actualAlignType == 1) goto L8;
        int r1 = r2 % r3;
        if (r1 != 0) goto L7;
        return r2;
    L7:
        return r2 + (r3 - r1);
    L8:
        return r2;
    }

    private void allocateMemory(boolean r2) {
        allocateMemory(calculateSize(true, r2));
    }

    public static void autoRead(Structure[] r3) {
        structureArrayCheck(r3);
        int r0 = 0;
        Structure r1 = r3[0];
        if (r1.array != r3) goto L7;
        r1.autoRead();
        return;
    L7:
        if (r0 >= r3.length) goto L12;
        Structure r12 = r3[r0];
        if (r12 == null) goto L11;
        r12.autoRead();
    L11:
        r0 = r0 + 1;
        goto L7
    }

    public static void autoWrite(Structure[] r3) {
        structureArrayCheck(r3);
        int r0 = 0;
        Structure r1 = r3[0];
        if (r1.array != r3) goto L7;
        r1.autoWrite();
        return;
    L7:
        if (r0 >= r3.length) goto L12;
        Structure r12 = r3[r0];
        if (r12 == null) goto L11;
        r12.autoWrite();
    L11:
        r0 = r0 + 1;
        goto L7
    }

    private Class<?> baseClass() {
        if ((this instanceof ByReference) == true) goto L7;
        if ((this instanceof ByValue) == true) goto L7;
    L11:
        return getClass();
    L7:
        if (Structure.class.isAssignableFrom(getClass().getSuperclass()) == false) goto L11;
        return getClass().getSuperclass();
    }

    public static Set<Structure> busy() {
        return busy.get();
    }

    public static List<String> createFieldsOrder(String r0) {
        return Collections.unmodifiableList(Collections.singletonList(r0));
    }

    public static List<String> createFieldsOrder(List<String> r3, List<String> r4) {
        ArrayList r0 = new ArrayList(r3.size() + r4.size());
        r0.addAll(r3);
        r0.addAll(r4);
        return Collections.unmodifiableList(r0);
    }

    public static List<String> createFieldsOrder(List<String> r0, String... r1) {
        return createFieldsOrder(r0, Arrays.asList(r1));
    }

    public static List<String> createFieldsOrder(String... r0) {
        return Collections.unmodifiableList(Arrays.asList(r0));
    }

    private LayoutInfo deriveLayout(boolean r18, boolean r19) {
        List<Field> r0 = getFields(r18);
        LayoutInfo r2 = null;
        if (r0 != null) goto L5;
        return null;
    L5:
        LayoutInfo r3 = new LayoutInfo(null);
        LayoutInfo.access$402(r3, this.alignType);
        LayoutInfo.access$502(r3, this.typeMapper);
        Iterator<Field> r02 = r0.iterator();
        boolean r7 = true;
        int r6 = 0;
    L7:
        if (r02.hasNext() == false) goto L86;
        Field r8 = r02.next();
        int r9 = r8.getModifiers();
        Class<?> r10 = r8.getType();
        if (r10.isArray() == false) goto L11;
        LayoutInfo.access$202(r3, true);
    L11:
        StructField r11 = new StructField();
        r11.isVolatile = Modifier.isVolatile(r9);
        boolean r92 = Modifier.isFinal(r9);
        r11.isReadOnly = r92;
        if (r92 == true) goto L14;
    L18:
        r11.field = r8;
        r11.name = r8.getName();
        r11.type = r10;
        if (Callback.class.isAssignableFrom(r10) == false) goto L26;
        if (r10.isInterface() == true) goto L26;
        Native$$ExternalSyntheticBUOutline2.m("Structure Callback field '", r8.getName(), "' must be an interface");
        return r2;
    L26:
        if (r10.isArray() == false) goto L33;
        if (Structure.class.equals(r10.getComponentType()) == false) goto L33;
        f$$ExternalSyntheticBUOutline1.m("Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined");
        return r2;
    L33:
        if (Modifier.isPublic(r8.getModifiers()) == true) goto L35;
        LayoutInfo r16 = r2;
    L76:
        r7 = false;
        r2 = r16;
        goto L7
    L35:
        Object r93 = getFieldValue(r11.field);
        if (r93 != null) goto L44;
        if (r10.isArray() == false) goto L44;
        if (r18 == true) goto L41;
        return r2;
    L41:
        Segment$$ExternalSyntheticBUOutline1.m("Array fields must be initialized");
        return r2;
    L44:
        if (NativeMapped.class.isAssignableFrom(r10) == false) goto L46;
        NativeMappedConverter r13 = NativeMappedConverter.getInstance(r10);
        Class<?> r14 = r13.nativeType();
        r11.writeConverter = r13;
        r11.readConverter = r13;
        r11.context = new StructureReadContext(this, r8);
        r16 = r2;
    L64:
        if (r93 != null) goto L96;
        r93 = initializeField(r11.field, r10);
    L96:
        r11.size = getNativeSize(r14, r93);     // Catch: IllegalArgumentException -> L79
        int r22 = getNativeAlignment(r14, r93, r7);     // Catch: IllegalArgumentException -> L79
        if (r22 == 0) goto L78;
        LayoutInfo.access$602(r3, Math.max(LayoutInfo.access$600(r3), r22));
        int r72 = r6 % r22;
        if (r72 == 0) goto L72;
        r6 = r6 + (r22 - r72);
    L72:
        if ((this instanceof Union) == false) goto L74;
        r11.offset = 0;
        r6 = Math.max(r6, r11.size);
    L75:
        LayoutInfo.access$700(r3).put(r11.name, r11);
        goto L76
    L74:
        r11.offset = r6;
        r6 = r6 + r11.size;
        goto L75
    L78:
        throw new Error("Field alignment is zero for field '" + r11.name + "' within " + getClass());
    L79:
        e = move-exception;
        if (r18 == false) goto L82;
    L84:
        StringBuilder r23 = new StringBuilder("Invalid Structure field in ");
        r23.append(getClass());
        r23.append(", field name '");
        r23.append(r11.name);
        r23.append("' (");
        r23.append(r11.type);
        String r1 = e.getMessage();
        r23.append("): ");
        r23.append(r1);
        throw new IllegalArgumentException(r23.toString(), e);
    L82:
        if (this.typeMapper != null) goto L84;
        return r16;
    L46:
        TypeMapper r132 = this.typeMapper;
        if (r132 == null) goto L62;
        ToNativeConverter r133 = r132.getToNativeConverter(r10);
        FromNativeConverter r142 = this.typeMapper.getFromNativeConverter(r10);
        if (r133 == null) goto L56;
        if (r142 == null) goto L56;
        r16 = r2;
        r93 = r133.toNative(r93, new StructureWriteContext(this, r11.field));
        if (r93 == null) goto L54;
        Class<?> r24 = r93.getClass();
    L55:
        r11.writeConverter = r133;
        r11.readConverter = r142;
        r11.context = new StructureReadContext(this, r8);
        r14 = r24;
        goto L64
    L54:
        r24 = Pointer.class;
    L56:
        r16 = r2;
        if (r133 != null) goto L60;
        if (r142 != null) goto L60;
    L63:
        r14 = r10;
    L60:
        Buffer$$ExternalSyntheticBUOutline4.m("Structures require bidirectional type conversion for ", r10);
        return r16;
    L62:
        r16 = r2;
        goto L63
    L14:
        if (Platform.RO_FIELDS == false) goto L16;
        r8.setAccessible(true);
        goto L18
    L16:
        Structure$$ExternalSyntheticBUOutline0.m("This VM does not support read-only fields (field '", r8.getName(), "' within ", getClass(), ")");
        return r2;
    L86:
        LayoutInfo r162 = r2;
        if (r6 <= 0) goto L94;
        int r03 = addPadding(r6, LayoutInfo.access$600(r3));
        if ((this instanceof ByValue) == false) goto L92;
        if (r19 == true) goto L92;
        getTypeInfo();
    L92:
        LayoutInfo.access$302(r3, r03);
        return r3;
    L94:
        Native$$ExternalSyntheticBUOutline2.m("Structure ", getClass(), " has unknown or zero size (ensure all fields are public)");
        return r162;
    }

    private void ensureAllocated(boolean r6) {
        if (this.memory != null) goto L7;
        allocateMemory(r6);
        return;
    L7:
        if (this.size != (-1)) goto L16;
        int r62 = calculateSize(true, r6);
        this.size = r62;
        Pointer r0 = this.memory;
        if ((r0 instanceof AutoAllocated) == false) goto L17;
        return;
    L17:
        this.memory = r0.share(0, r62);     // Catch: IndexOutOfBoundsException -> L13
        return;
    L13:
        e = move-exception;
        throw new IllegalArgumentException("Structure exceeds provided memory bounds", e);
    }

    private List<String> fieldOrder() {
        Class<?> r0 = getClass();
        ReentrantReadWriteLock r1 = cacheStructureLock;
        r1.readLock().lock();
        Map<Class<?>, List<String>> r2 = fieldOrder;     // Catch: Throwable -> L14
        List<String> r3 = r2.get(r0);     // Catch: Throwable -> L14
        if (r3 == null) goto L7;
        r1.readLock().unlock();
        return r3;
    L7:
        r1.readLock().unlock();
        r1.writeLock().lock();
        List<String> r4 = r2.computeIfAbsent(r0, new Structure$$ExternalSyntheticLambda3(this));     // Catch: Throwable -> L11
        r1.writeLock().unlock();
        return r4;
    L11:
        th = move-exception;
        cacheStructureLock.writeLock().unlock();
        throw th;
    L14:
        th = move-exception;
        cacheStructureLock.readLock().unlock();
        throw th;
    }

    private String format(Class<?> r1) {
        String r0 = r1.getName();
        return r0.substring(r0.lastIndexOf(".") + 1);
    }

    private static <T> Constructor<T> getPointerConstructor(Class<T> r7) {
        Object[] r72 = r7.getConstructors();
        int r0 = r72.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        Constructor<T> r3 = (Constructor<T>) r72[r2];
        Class<?>[] r4 = r3.getParameterTypes();
        if (r4.length != 1) goto L9;
        if (r4[0].equals(Pointer.class) == false) goto L9;
        return r3;
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        return null;
    }

    public static FFIType getTypeInfo(Object r0) {
        return FFIType.get(r0);
    }

    private Object initializeField(Field r2, Class<?> r3) {
        if (Structure.class.isAssignableFrom(r3) == false) goto L12;
        if (ByReference.class.isAssignableFrom(r3) == true) goto L12;
        Structure r32 = newInstance(r3, PLACEHOLDER_MEMORY);     // Catch: IllegalArgumentException -> L8
        setFieldValue(r2, r32);     // Catch: IllegalArgumentException -> L8
        return r32;
    L8:
        e = move-exception;
        throw new IllegalArgumentException("Can't determine size of nested structure", e);
    L12:
        if (NativeMapped.class.isAssignableFrom(r3) == false) goto L15;
        NativeMapped r33 = NativeMappedConverter.getInstance(r3).defaultValue();
        setFieldValue(r2, r33);
        return r33;
    L15:
        return null;
    }

    private void initializeFields() {
        Iterator<Field> r0 = getFieldList().iterator();
    L4:
        if (r0.hasNext() == false) goto L20;
        Field r1 = r0.next();
        if (r1.get(this) != null) goto L4;
        initializeField(r1, r1.getType());     // Catch: Exception -> L10
    L10:
        e = move-exception;
        Structure$$ExternalSyntheticBUOutline1.m("Exception reading field '", r1.getName(), "' in ", getClass(), e);
        return;
    }

    private void initializeTypeMapper(TypeMapper r1) {
        if (r1 != null) goto L4;
        r1 = Native.getTypeMapper(getClass());
    L4:
        this.typeMapper = r1;
        layoutChanged();
    }

    private void layoutChanged() {
        if (this.size == (-1)) goto L9;
        this.size = -1;
        if ((this.memory instanceof AutoAllocated) == false) goto L7;
        this.memory = null;
    L7:
        ensureAllocated();
        return;
    }

    public static <T extends Structure> T newInstance(Class<T> r1) {
        T r12 = (T) Klass.newInstance(r1);
        if ((r12 instanceof ByValue) == false) goto L5;
        r12.allocateMemory();
    L5:
        return r12;
    }

    private static <T extends Structure> T newInstance(Class<T> r2, long r3) {
        if (r3 != 0) goto L5;
        Pointer r32 = PLACEHOLDER_MEMORY;     // Catch: Throwable -> L10
    L6:
        T r22 = (T) newInstance(r2, r32);
        if (r3 == 0) goto L9;
        r22.conditionalAutoRead();     // Catch: Throwable -> L10
    L9:
        return r22;
    L5:
        r32 = new Pointer(r3);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        LOG.log(Level.WARNING, "JNA: Error creating structure", th);
        return null;
    }

    public static <T extends Structure> T newInstance(Class<T> r2, Pointer r3) {
        Constructor r0 = getPointerConstructor(r2);     // Catch: InvocationTargetException -> L6 IllegalAccessException -> L8 InstantiationException -> L10 SecurityException -> L22
        if (r0 == null) goto L18;
        return (T) r0.newInstance(new Object[]{r3});
    L8:
        e = move-exception;
        throw new IllegalArgumentException("Instantiation of " + r2 + " (Pointer) not allowed, is it public?", e);
    L10:
        e = move-exception;
        throw new IllegalArgumentException("Can't instantiate " + r2, e);
    L6:
        e = move-exception;
        throw new IllegalArgumentException("Exception thrown while instantiating an instance of " + r2, e);
    L18:
        T r22 = (T) newInstance(r2);
        if (r3 == PLACEHOLDER_MEMORY) goto L21;
        r22.useMemory(r3);
    L21:
        return r22;
    }

    public static Map<Pointer, Structure> reading() {
        return reads.get();
    }

    private void setFieldValue(Field r4, Object r5, boolean r6) {
        r4.set(this, r5);     // Catch: IllegalAccessException -> L4
        return;
    L4:
        e = move-exception;
        if (Modifier.isFinal(r4.getModifiers()) == false) goto L12;
        if (r6 == false) goto L11;
        throw new UnsupportedOperationException("This VM does not support Structures with final fields (field '" + r4.getName() + "' within " + getClass() + ")", e);
    L11:
        throw new UnsupportedOperationException("Attempt to write to read-only field '" + r4.getName() + "' within " + getClass(), e);
    L12:
        Structure$$ExternalSyntheticBUOutline1.m("Unexpectedly unable to write to field '", r4.getName(), "' within ", getClass(), e);
    }

    public static int size(Class<? extends Structure> r1) {
        return size(r1, null);
    }

    public static <T extends Structure> int size(Class<T> r3, T r4) {
        ReentrantReadWriteLock r0 = cacheStructureLock;
        r0.readLock().lock();
        LayoutInfo r1 = layoutInfo.get(r3);     // Catch: Throwable -> L16
        r0.readLock().unlock();
        if (r1 != null) goto L7;
    L9:
        int r12 = -1;
    L10:
        if (r12 != (-1)) goto L15;
        if (r4 != null) goto L14;
        r4 = (T) newInstance(r3, PLACEHOLDER_MEMORY);
    L14:
        return r4.size();
    L15:
        return r12;
    L7:
        if (LayoutInfo.access$200(r1) == true) goto L9;
        r12 = LayoutInfo.access$300(r1);
    L16:
        th = move-exception;
        cacheStructureLock.readLock().unlock();
        throw th;
    }

    private static <T extends Comparable<T>> List<T> sort(Collection<? extends T> r1) {
        ArrayList r0 = new ArrayList(r1);
        Collections.sort(r0);
        return r0;
    }

    private static void structureArrayCheck(Structure[] r9) {
        if (ByReference[].class.isAssignableFrom(r9.getClass()) == true) goto L15;
        Pointer r1 = r9[0].getPointer();
        int r0 = r9[0].size();
        int r2 = 1;
    L7:
        if (r2 >= r9.length) goto L16;
        if (r9[r2].getPointer().peer != (r1.peer + (r0 * r2))) goto L11;
        r2 = r2 + 1;
        goto L7
    L11:
        Structure$$ExternalSyntheticBUOutline2.m("Structure array elements must use contiguous memory (bad backing address at Structure array index ", r2, ")");
        return;
    L16:
        return;
    }

    private String toString(int r17, boolean r18, boolean r19) {
        ensureAllocated();
        String r3 = System.lineSeparator();
        String r4 = format(getClass()) + "(" + getPointer() + ")";
        if ((getPointer() instanceof Memory) == true) goto L5;
        r4 = r4 + " (" + size() + " bytes)";
    L5:
        String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r7 = 0;
    L6:
        if (r7 >= r17) goto L9;
        r8 = r8.concat("  ");
        r7 = r7 + 1;
        goto L6
    L9:
        if (r18 == true) goto L11;
        String r6 = "...}";
    L40:
        if (r17 != 0) goto L61;
        if (r19 == false) goto L61;
        String r1 = r6 + r3 + "memory dump" + r3;
        byte[] r0 = getPointer().getByteArray(0, size());
        int r5 = 0;
    L44:
        if (r5 >= r0.length) goto L59;
        int r2 = r5 % 4;
        if (r2 != 0) goto L48;
        r1 = r1.concat("[");
    L48:
        byte r62 = r0[r5];
        if (r62 >= 0) goto L51;
    L53:
        r1 = r1 + Integer.toHexString(r0[r5] & UByte.MAX_VALUE);
        if (r2 != 3) goto L58;
        if (r5 >= (r0.length - 1)) goto L58;
        r1 = r1 + "]" + r3;
    L58:
        r5 = r5 + 1;
        goto L44
    L51:
        if (r62 >= 16) goto L53;
        r1 = r1.concat(MVEL.VERSION_SUB);
        goto L53
    L59:
        r6 = r1.concat("]");
    L61:
        return r4 + " {" + r6;
    L11:
        Iterator<StructField> r10 = fields().values().iterator();
        String r11 = r3;
    L13:
        if (r10.hasNext() == false) goto L39;
        StructField r12 = r10.next();
        Object r13 = getFieldValue(r12.field);
        String r14 = format(r12.type);
        String r112 = r11 + r8;
        if (r12.type.isArray() == false) goto L18;
        if (r13 == null) goto L18;
        r14 = format(r12.type.getComponentType());
        String r52 = "[" + Array.getLength(r13) + "]";
    L19:
        String r53 = r112.concat(String.format("  %s %s%s@0x%X", new Object[]{r14, r12.name, r52, Integer.valueOf(r12.offset)}));
        if ((r13 instanceof Structure) == false) goto L22;
        r13 = ((Structure) r13).toString(r17 + 1, !(r13 instanceof ByReference), r19);
    L22:
        String r54 = r53.concat("=");
        if ((r13 instanceof Long) == false) goto L26;
        String r55 = r54.concat(String.format("0x%08X", new Object[]{(Long) r13}));
    L35:
        String r56 = r55 + r3;
        if (r10.hasNext() == true) goto L38;
        r56 = r56 + r8 + "}";
    L38:
        r11 = r56;
        goto L13
    L26:
        if ((r13 instanceof Integer) == false) goto L29;
        r55 = r54.concat(String.format("0x%04X", new Object[]{(Integer) r13}));
        goto L35
    L29:
        if ((r13 instanceof Short) == false) goto L32;
        r55 = r54.concat(String.format("0x%02X", new Object[]{(Short) r13}));
        goto L35
    L32:
        if ((r13 instanceof Byte) == false) goto L34;
        r55 = r54.concat(String.format("0x%01X", new Object[]{(Byte) r13}));
        goto L35
    L34:
        r55 = r54 + String.valueOf(r13).trim();
    L18:
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L39:
        r6 = r11;
        goto L40
    }

    public static <T extends Structure> T updateStructureByReference(Class<T> r1, T r2, Pointer r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        if (r2 != null) goto L7;
    L11:
        T r22 = (T) reading().get(r3);
        if (r22 != null) goto L14;
    L17:
        T r12 = (T) newInstance(r1, r3);
        r12.conditionalAutoRead();
        return r12;
    L14:
        if (r1.equals(r22.getClass()) == false) goto L17;
        r22.autoRead();
        return r22;
    L7:
        if (r3.equals(r2.getPointer()) == false) goto L11;
        r2.autoRead();
        return r2;
    }

    public static void validate(Class<? extends Structure> r1) {
        r1.getConstructor(null);     // Catch: Throwable -> L5
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("No suitable constructor found for class: ".concat(r1.getName()));
    }

    private void validateField(String r4, Class<?> r5) {
        TypeMapper r0 = this.typeMapper;
        if (r0 == null) goto L9;
        ToNativeConverter r02 = r0.getToNativeConverter(r5);
        if (r02 == null) goto L9;
        validateField(r4, r02.nativeType());
        return;
    L9:
        if (r5.isArray() == false) goto L17;
        validateField(r4, r5.getComponentType());
        return;
    L17:
        getNativeSize(r5);     // Catch: IllegalArgumentException -> L14
        return;
    L14:
        e = move-exception;
        StringBuilder r1 = new StringBuilder("Invalid Structure field in ");
        r1.append(getClass());
        r1.append(", field name '");
        r1.append(r4);
        r1.append("' (");
        r1.append(r5);
        String r3 = e.getMessage();
        r1.append("): ");
        r1.append(r3);
        throw new IllegalArgumentException(r1.toString(), e);
    }

    private void validateFields() {
        ReentrantReadWriteLock r0 = cacheStructureLock;
        r0.readLock().lock();
        Map<Class<?>, Boolean> r1 = validationMap;     // Catch: Throwable -> L14
        if (r1.containsKey(getClass()) == false) goto L7;
        r0.readLock().unlock();
        return;
    L7:
        r0.readLock().unlock();
        r0.writeLock().lock();
        r1.computeIfAbsent(getClass(), new Structure$$ExternalSyntheticLambda4(this));     // Catch: Throwable -> L11
        r0.writeLock().unlock();
        return;
    L11:
        th = move-exception;
        cacheStructureLock.writeLock().unlock();
        throw th;
    L14:
        th = move-exception;
        cacheStructureLock.readLock().unlock();
        throw th;
    }

    private void writeField(StructField r7, Object r8) {
        int r0 = r7.offset;
        Class<?> r1 = r7.type;
        ToNativeConverter r2 = r7.writeConverter;
        if (r2 == null) goto L6;
        r8 = r2.toNative(r8, new StructureWriteContext(this, r7.field));
        r1 = r2.nativeType();
    L6:
        if (String.class == r1) goto L8;
        if (WString.class == r1) goto L8;
    L29:
        this.memory.setValue(r0, r8, r1);     // Catch: IllegalArgumentException -> L22
        return;
    L22:
        e = move-exception;
        StringBuilder r82 = new StringBuilder("Structure field \"");
        r82.append(r7.name);
        r82.append("\" was declared as ");
        r82.append(r7.type);
        if (r7.type != r1) goto L26;
        String r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        r82.append(r72);
        r82.append(", which is not supported within a Structure");
        throw new IllegalArgumentException(r82.toString(), e);
    L26:
        r72 = " (native type " + r1 + ")";
    L8:
        if (r8 == null) goto L19;
        NativeStringTracking r22 = new NativeStringTracking(r8);
        NativeStringTracking r4 = this.nativeStrings.put(r7.name, r22);
        if (r4 != null) goto L12;
    L15:
        if (r1 != WString.class) goto L17;
        NativeString r3 = new NativeString(r8.toString(), true);
    L18:
        NativeStringTracking.access$002(r22, r3);
        r8 = r3.getPointer();
        goto L29
    L17:
        r3 = new NativeString(r8.toString(), this.encoding);
        goto L18
    L12:
        if (r8.equals(NativeStringTracking.access$100(r4)) == false) goto L15;
        NativeStringTracking.access$002(r22, NativeStringTracking.access$000(r4));
        return;
    L19:
        this.nativeStrings.remove(r7.name);
        goto L29
    }

    public void allocateMemory() {
        allocateMemory(false);
    }

    public void allocateMemory(int r2) {
        if (r2 != (-1)) goto L5;
        r2 = calculateSize(false);
    L6:
        if (r2 == (-1)) goto L16;
        Pointer r0 = this.memory;
        if (r0 != null) goto L10;
    L11:
        this.memory = autoAllocate(r2);
    L12:
        this.size = r2;
        return;
    L10:
        if ((r0 instanceof AutoAllocated) == false) goto L12;
    L16:
        return;
    L5:
        if (r2 > 0) goto L6;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Structure size must be greater than zero: ", r2);
    }

    public Memory autoAllocate(int r1) {
        return new AutoAllocated(r1);
    }

    public void autoRead() {
        if (getAutoRead() == false) goto L10;
        read();
        if (this.array == null) goto L12;
        int r0 = 1;
    L7:
        Structure[] r1 = this.array;
        if (r0 >= r1.length) goto L13;
        r1[r0].autoRead();
        r0 = r0 + 1;
        goto L7
    L13:
        return;
    L12:
        return;
    }

    public void autoWrite() {
        if (getAutoWrite() == false) goto L10;
        write();
        if (this.array == null) goto L12;
        int r0 = 1;
    L7:
        Structure[] r1 = this.array;
        if (r0 >= r1.length) goto L13;
        r1[r0].autoWrite();
        r0 = r0 + 1;
        goto L7
    L13:
        return;
    L12:
        return;
    }

    public void cacheTypeInfo(Pointer r3) {
        this.typeInfo = r3.peer;
    }

    public int calculateSize(boolean r2) {
        return calculateSize(r2, false);
    }

    public int calculateSize(boolean r7, boolean r8) {
        Class<?> r0 = getClass();
        ReentrantReadWriteLock r1 = cacheStructureLock;
        r1.readLock().lock();
        Map<Class<?>, LayoutInfo> r2 = layoutInfo;     // Catch: Throwable -> L30
        LayoutInfo r3 = r2.get(r0);     // Catch: Throwable -> L30
        r1.readLock().unlock();
        if (r3 != null) goto L7;
    L10:
        r3 = deriveLayout(r7, r8);
    L11:
        if (r3 == null) goto L28;
        this.structAlignment = LayoutInfo.access$600(r3);
        this.structFields = LayoutInfo.access$700(r3);
        if (LayoutInfo.access$200(r3) == true) goto L27;
        r1.readLock().lock();
    L23:
        th = move-exception;
        cacheStructureLock.readLock().unlock();
        throw th;
    L16:
        if (r2.containsKey(r0) == true) goto L18;
    L21:
        r1.readLock().unlock();     // Catch: Throwable -> L23
        r1.writeLock().lock();     // Catch: Throwable -> L23
        r2.put(r0, r3);     // Catch: Throwable -> L23
        r1.readLock().lock();     // Catch: Throwable -> L23
        r1.writeLock().unlock();     // Catch: Throwable -> L23
    L22:
        r1.readLock().unlock();
        goto L27
    L18:
        if (this.alignType != 0) goto L21;
        if (this.typeMapper == null) goto L22;
    L27:
        return LayoutInfo.access$300(r3);
    L28:
        return -1;
    L7:
        if (this.alignType != LayoutInfo.access$400(r3)) goto L10;
        if (this.typeMapper == LayoutInfo.access$500(r3)) goto L11;
    L30:
        th = move-exception;
        cacheStructureLock.readLock().unlock();
        throw th;
    }

    public void clear() {
        ensureAllocated();
        this.nativeStrings.clear();
        this.memory.clear(size());
    }

    public void conditionalAutoRead() {
        if (this.readCalled == true) goto L6;
        autoRead();
        return;
    }

    public boolean dataEquals(Structure r2) {
        return dataEquals(r2, false);
    }

    public boolean dataEquals(Structure r4, boolean r5) {
        if (r5 == false) goto L4;
        r4.getPointer().clear(r4.size());
        r4.write();
        getPointer().clear(size());
        write();
    L4:
        byte[] r42 = r4.getPointer().getByteArray(0, r4.size());
        byte[] r3 = getPointer().getByteArray(0, size());
        if (r42.length != r3.length) goto L15;
        int r52 = 0;
    L8:
        if (r52 >= r42.length) goto L13;
        if (r42[r52] != r3[r52]) goto L11;
        r52 = r52 + 1;
        goto L8
    L11:
        return false;
    L13:
        return true;
    L15:
        return false;
    }

    public void ensureAllocated() {
        ensureAllocated(false);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof Structure) == true) goto L5;
        return false;
    L5:
        if (r3.getClass() == getClass()) goto L7;
        return false;
    L7:
        if (((Structure) r3).getPointer().equals(getPointer()) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public int fieldOffset(String r1) {
        ensureAllocated();
        StructField r0 = fields().get(r1);
        if (r0 != null) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("No such field: ", r1);
        return 0;
    L5:
        return r0.offset;
    }

    public Map<String, StructField> fields() {
        return this.structFields;
    }

    public boolean getAutoRead() {
        return this.autoRead;
    }

    public boolean getAutoWrite() {
        return this.autoWrite;
    }

    public List<Field> getFieldList() {
        Class<?> r3 = getClass();
        ReentrantReadWriteLock r0 = cacheStructureLock;
        r0.readLock().lock();
        Map<Class<?>, List<Field>> r1 = fieldList;     // Catch: Throwable -> L14
        List<Field> r2 = r1.get(r3);     // Catch: Throwable -> L14
        if (r2 == null) goto L7;
        r0.readLock().unlock();
        return r2;
    L7:
        r0.readLock().unlock();
        r0.writeLock().lock();
        List<Field> r32 = r1.computeIfAbsent(r3, new Structure$$ExternalSyntheticLambda5(r3));     // Catch: Throwable -> L11
        r0.writeLock().unlock();
        return r32;
    L11:
        th = move-exception;
        cacheStructureLock.writeLock().unlock();
        throw th;
    L14:
        th = move-exception;
        cacheStructureLock.readLock().unlock();
        throw th;
    }

    public List<String> getFieldOrder() {
        LinkedList r0 = new LinkedList();
        Class<?> r3 = getClass();
    L4:
        if (r3 == Structure.class) goto L10;
        FieldOrder r1 = (FieldOrder) r3.getAnnotation(FieldOrder.class);
        if (r1 == null) goto L8;
        r0.addAll(0, Arrays.asList(r1.value()));
    L8:
        r3 = r3.getSuperclass();
        goto L4
    L10:
        return Collections.unmodifiableList(r0);
    }

    public FFIType getFieldTypeInfo(StructField r3) {
        Class<?> r0 = r3.type;
        Object r32 = getFieldValue(r3.field);
        TypeMapper r2 = this.typeMapper;
        if (r2 == null) goto L8;
        ToNativeConverter r22 = r2.getToNativeConverter(r0);
        if (r22 == null) goto L8;
        r0 = r22.nativeType();
        r32 = r22.toNative(r32, new ToNativeContext());
    L8:
        return FFIType.access$900(r32, r0);
    }

    public Object getFieldValue(Field r4) {
        return r4.get(this);
    L4:
        e = move-exception;
        Structure$$ExternalSyntheticBUOutline1.m("Exception reading field '", r4.getName(), "' in ", this.getClass(), e);
        return null;
    }

    public List<Field> getFields(boolean r8) {
        ArrayList r0 = new ArrayList(getFieldList());
        HashSet r1 = new HashSet();
        int r2 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        r1.add(((Field) r4).getName());
        goto L3
    L5:
        List<String> r22 = fieldOrder();
        if (r22.size() == r0.size()) goto L19;
        if (r0.size() <= 1) goto L19;
        if (r8 == false) goto L16;
        StringBuilder r32 = new StringBuilder("Structure.getFieldOrder() on ");
        r32.append(getClass());
        if (r22.size() >= r0.size()) goto L13;
        String r7 = " does not provide enough";
    L14:
        r32.append(r7);
        r32.append(" names [");
        r32.append(r22.size());
        r32.append("] (");
        r32.append(sort(r22));
        r32.append(") to match declared fields [");
        r32.append(r0.size());
        r32.append("] (");
        r32.append(sort(r1));
        r32.append(")");
        throw new Error(r32.toString());
    L13:
        r7 = " provides too many";
        goto L14
    L16:
        return null;
    L19:
        if (new HashSet(r22).equals(r1) == false) goto L22;
        sortFields(r0, r22);
        return r0;
    L22:
        StringBuilder r02 = new StringBuilder("Structure.getFieldOrder() on ");
        r02.append(getClass());
        r02.append(" returns names (");
        r02.append(sort(r22));
        List r72 = sort(r1);
        r02.append(") which do not match declared field names (");
        r02.append(r72);
        r02.append(")");
        throw new Error(r02.toString());
    }

    public int getNativeAlignment(Class<?> r4, Object r5, boolean r6) {
        if (NativeMapped.class.isAssignableFrom(r4) == false) goto L5;
        NativeMappedConverter r42 = NativeMappedConverter.getInstance(r4);
        Class<?> r0 = r42.nativeType();
        r5 = r42.toNative(r5, new ToNativeContext());
        r4 = r0;
    L5:
        int r02 = Native.getNativeSize(r4, r5);
        if (r4.isPrimitive() == false) goto L8;
    L52:
        int r3 = this.actualAlignType;
        if (r3 != 1) goto L56;
        return 1;
    L56:
        if (r3 != 3) goto L60;
        return Math.min(8, r02);
    L60:
        if (r3 != 2) goto L75;
        if (r6 == true) goto L63;
    L66:
        r02 = Math.min(Native.MAX_ALIGNMENT, r02);
    L67:
        if (r6 == true) goto L75;
        if (Platform.isAIX() == false) goto L75;
        if (r4 == Double.TYPE) goto L73;
        if (r4 != Double.class) goto L75;
        return 4;
    L73:
        return 4;
    L63:
        if (Platform.isMac() == false) goto L66;
        if (Platform.isPPC() == true) goto L67;
    L75:
        return r02;
    L8:
        if (Long.class == r4) goto L52;
        if (Integer.class == r4) goto L52;
        if (Short.class == r4) goto L52;
        if (Character.class == r4) goto L52;
        if (Byte.class == r4) goto L52;
        if (Boolean.class == r4) goto L52;
        if (Float.class == r4) goto L52;
        if (Double.class == r4) goto L52;
        if (Pointer.class.isAssignableFrom(r4) == false) goto L28;
        if (Function.class.isAssignableFrom(r4) == true) goto L28;
    L51:
        r02 = Native.POINTER_SIZE;
    L28:
        if (Platform.HAS_BUFFERS == false) goto L32;
        if (Buffer.class.isAssignableFrom(r4) == true) goto L51;
    L32:
        if (Callback.class.isAssignableFrom(r4) == true) goto L51;
        if (WString.class == r4) goto L51;
        if (String.class == r4) goto L51;
        if (Structure.class.isAssignableFrom(r4) == false) goto L47;
        if (ByReference.class.isAssignableFrom(r4) == false) goto L43;
        r02 = Native.POINTER_SIZE;
        goto L52
    L43:
        if (r5 != null) goto L45;
        r5 = newInstance(r4, PLACEHOLDER_MEMORY);
    L45:
        r02 = ((Structure) r5).getStructAlignment();
        goto L52
    L47:
        if (r4.isArray() == false) goto L49;
        r02 = getNativeAlignment(r4.getComponentType(), null, r6);
        goto L52
    L49:
        Native$$ExternalSyntheticBUOutline0.m("Type ", r4, " has unknown native alignment");
        return 0;
    }

    public int getNativeSize(Class<?> r2) {
        return getNativeSize(r2, null);
    }

    public int getNativeSize(Class<?> r1, Object r2) {
        return Native.getNativeSize(r1, r2);
    }

    public Pointer getPointer() {
        ensureAllocated();
        return this.memory;
    }

    public String getStringEncoding() {
        return this.encoding;
    }

    public int getStructAlignment() {
        if (this.size != (-1)) goto L6;
        calculateSize(true);
    L6:
        return this.structAlignment;
    }

    public Pointer getTypeInfo() {
        Pointer r0 = getTypeInfo(this).getPointer();
        cacheTypeInfo(r0);
        return r0;
    }

    public TypeMapper getTypeMapper() {
        return this.typeMapper;
    }

    public int hashCode() {
        if (getPointer() == null) goto L7;
        return getPointer().hashCode();
    L7:
        return getClass().hashCode();
    }

    public void read() {
        if (this.memory == PLACEHOLDER_MEMORY) goto L35;
        this.readCalled = true;
        ensureAllocated();
        if (busy().add(this) == false) goto L36;
        boolean r0 = this instanceof ByReference;
        if (r0 == false) goto L30;
        reading().put(getPointer(), this);
    L30:
        Iterator<StructField> r1 = fields().values().iterator();     // Catch: Throwable -> L16
    L12:
        if (r1.hasNext() == false) goto L18;
        readField(r1.next());     // Catch: Throwable -> L16
        goto L12
    L18:
        busy().remove(this);
        if (r0 == true) goto L21;
        return;
    L21:
        if (reading().get(getPointer()) != this) goto L34;
        reading().remove(getPointer());
        return;
    L34:
        return;
    L16:
        th = move-exception;
        busy().remove(this);
        if (r0 == true) goto L27;
    L29:
        throw th;
    L27:
        if (reading().get(getPointer()) != this) goto L29;
        reading().remove(getPointer());
        goto L29
    L36:
        return;
    }

    public Object readField(StructField r10) {
        int r0 = r10.offset;
        Class<?> r1 = r10.type;
        FromNativeConverter r2 = r10.readConverter;
        if (r2 == null) goto L5;
        r1 = r2.nativeType();
    L5:
        Object r4 = null;
        if (Structure.class.isAssignableFrom(r1) == false) goto L8;
    L21:
        Object r3 = getFieldValue(r10.field);
    L22:
        Pointer r5 = this.memory;
        if (r1 != String.class) goto L28;
        Pointer r02 = r5.getPointer(r0);
        if (r02 == null) goto L29;
        r4 = r02.getString(0, this.encoding);
    L29:
        if (r2 == null) goto L36;
        Object r03 = r2.fromNative(r4, r10.context);
        if (r3 != null) goto L33;
    L35:
        r3 = r03;
    L38:
        if (r1.equals(String.class) == false) goto L40;
    L41:
        if (r3 == null) goto L45;
        NativeStringTracking r04 = new NativeStringTracking(r3);
        NativeStringTracking r12 = this.nativeStrings.put(r10.name, r04);
        if (r12 == null) goto L46;
        NativeStringTracking.access$002(r04, NativeStringTracking.access$000(r12));
    L46:
        setFieldValue(r10.field, r3, true);
        return r3;
    L45:
        this.nativeStrings.remove(r10.name);
        goto L46
    L40:
        if (r1.equals(WString.class) == false) goto L46;
    L33:
        if (r3.equals(r03) == false) goto L35;
    L36:
        r3 = r4;
        goto L38
    L28:
        r4 = r5.getValue(r0, r1, r3);
        goto L29
    L8:
        if (Callback.class.isAssignableFrom(r1) == true) goto L21;
        if (Platform.HAS_BUFFERS == false) goto L14;
        if (Buffer.class.isAssignableFrom(r1) == true) goto L21;
    L14:
        if (Pointer.class.isAssignableFrom(r1) == true) goto L21;
        if (NativeMapped.class.isAssignableFrom(r1) == true) goto L21;
        if (r1.isArray() == true) goto L21;
        r3 = null;
        goto L22
    }

    public Object readField(String r2) {
        ensureAllocated();
        StructField r0 = fields().get(r2);
        if (r0 != null) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("No such field: ", r2);
        return null;
    L5:
        return readField(r0);
    }

    public void setAlignType(int r1) {
        this.alignType = r1;
        if (r1 != 0) goto L10;
        r1 = Native.getStructureAlignment(getClass());
        if (r1 != 0) goto L10;
        if (Platform.isWindows() == false) goto L9;
        r1 = 3;
        goto L10
    L9:
        r1 = 2;
    L10:
        this.actualAlignType = r1;
        layoutChanged();
    }

    public void setAutoRead(boolean r1) {
        this.autoRead = r1;
    }

    public void setAutoSynch(boolean r1) {
        setAutoRead(r1);
        setAutoWrite(r1);
    }

    public void setAutoWrite(boolean r1) {
        this.autoWrite = r1;
    }

    public void setFieldValue(Field r2, Object r3) {
        setFieldValue(r2, r3, false);
    }

    public void setStringEncoding(String r1) {
        this.encoding = r1;
    }

    public int size() {
        ensureAllocated();
        return this.size;
    }

    public void sortFields(List<Field> r5, List<String> r6) {
        int r0 = 0;
    L4:
        if (r0 >= r6.size()) goto L13;
        String r1 = r6.get(r0);
        int r2 = 0;
    L7:
        if (r2 >= r5.size()) goto L12;
        if (r1.equals(r5.get(r2).getName()) == true) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        Collections.swap(r5, r0, r2);
    L12:
        r0 = r0 + 1;
        goto L4
    }

    public Structure[] toArray(int r2) {
        return toArray((Structure[]) Array.newInstance(getClass(), r2));
    }

    public Structure[] toArray(Structure[] r9) {
        ensureAllocated();
        Pointer r0 = this.memory;
        if ((r0 instanceof AutoAllocated) == false) goto L7;
        int r1 = r9.length * size();
        if (((Memory) r0).size() >= r1) goto L7;
        useMemory(autoAllocate(r1));
    L7:
        r9[0] = this;
        int r02 = size();
        int r12 = 1;
    L9:
        if (r12 >= r9.length) goto L12;
        Structure r2 = newInstance(getClass(), this.memory.share(r12 * r02, r02));
        r9[r12] = r2;
        r2.conditionalAutoRead();
        r12 = r12 + 1;
        goto L9
    L12:
        if ((this instanceof ByValue) == true) goto L14;
        this.array = r9;
    L14:
        return r9;
    }

    public String toString() {
        return toString(Boolean.getBoolean("jna.dump_memory"));
    }

    public String toString(boolean r3) {
        return toString(0, true, r3);
    }

    public void useMemory(Pointer r2) {
        useMemory(r2, 0);
    }

    public void useMemory(Pointer r2, int r3) {
        useMemory(r2, r3, false);
    }

    public void useMemory(Pointer r9, int r10, boolean r11) {
        this.nativeStrings.clear();     // Catch: IndexOutOfBoundsException -> L15
        if ((this instanceof ByValue) == false) goto L7;
        if (r11 == true) goto L7;
        int r7 = size();     // Catch: IndexOutOfBoundsException -> L15
        byte[] r5 = new byte[r7];     // Catch: IndexOutOfBoundsException -> L15
        r9.read(0, r5, 0, r7);     // Catch: IndexOutOfBoundsException -> L15
        this.memory.write(0, r5, 0, r7);     // Catch: IndexOutOfBoundsException -> L15
    L13:
        this.array = null;     // Catch: IndexOutOfBoundsException -> L15
        this.readCalled = false;     // Catch: IndexOutOfBoundsException -> L15
        return;
    L7:
        if (this.size != (-1)) goto L9;
        this.size = calculateSize(false);     // Catch: IndexOutOfBoundsException -> L15
    L9:
        int r92 = this.size;     // Catch: IndexOutOfBoundsException -> L15
        if (r92 == (-1)) goto L12;
        this.memory = r9.share(r10, r92);     // Catch: IndexOutOfBoundsException -> L15
        goto L13
    L12:
        this.memory = r9.share(r10);     // Catch: IndexOutOfBoundsException -> L15
    L15:
        e = move-exception;
        throw new IllegalArgumentException("Structure exceeds provided memory bounds", e);
    }

    public void write() {
        if (this.memory == PLACEHOLDER_MEMORY) goto L31;
        ensureAllocated();
        if ((this instanceof ByValue) == false) goto L9;
        getTypeInfo();
    L9:
        if (busy().add(this) == true) goto L24;
        return;
    L24:
        Iterator<StructField> r0 = fields().values().iterator();     // Catch: Throwable -> L18
    L12:
        if (r0.hasNext() == false) goto L20;
        StructField r1 = r0.next();     // Catch: Throwable -> L18
        if (r1.isVolatile == true) goto L12;
        writeField(r1);     // Catch: Throwable -> L18
        goto L12
    L20:
        busy().remove(this);
        return;
    L18:
        th = move-exception;
        busy().remove(this);
        throw th;
    }

    public void writeField(StructField r2) {
        if (r2.isReadOnly == false) goto L5;
        return;
    L5:
        writeField(r2, getFieldValue(r2.field));
    }

    public void writeField(String r2) {
        ensureAllocated();
        StructField r0 = fields().get(r2);
        if (r0 == null) goto L6;
        writeField(r0);
        return;
    L6:
        Native$$ExternalSyntheticBUOutline5.m("No such field: ", r2);
    }

    public void writeField(String r2, Object r3) {
        ensureAllocated();
        StructField r0 = fields().get(r2);
        if (r0 == null) goto L6;
        setFieldValue(r0.field, r3);
        writeField(r0, r3);
        return;
    L6:
        Native$$ExternalSyntheticBUOutline5.m("No such field: ", r2);
    }
}
