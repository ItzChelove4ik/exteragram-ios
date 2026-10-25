package com.android.dex;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex;
import com.android.dex.util.Unsigned;

/* loaded from: classes4.dex */
public class MethodHandle implements Comparable<MethodHandle> {
    private final Dex dex;
    private final int fieldOrMethodId;
    private final MethodHandleType methodHandleType;
    private final int unused1;
    private final int unused2;

    /* renamed from: com.android.dex.MethodHandle$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$dex$MethodHandle$MethodHandleType = null;

        static {
            int[] r0 = new int[MethodHandleType.values().length];
            $SwitchMap$com$android$dex$MethodHandle$MethodHandleType = r0;
            r0[MethodHandleType.METHOD_HANDLE_TYPE_STATIC_PUT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$android$dex$MethodHandle$MethodHandleType[MethodHandleType.METHOD_HANDLE_TYPE_STATIC_GET.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$android$dex$MethodHandle$MethodHandleType[MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_PUT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$android$dex$MethodHandle$MethodHandleType[MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_GET.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public enum MethodHandleType extends Enum<MethodHandleType> {
        private static final /* synthetic */ MethodHandleType[] $VALUES = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INSTANCE_GET = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INSTANCE_PUT = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INVOKE_DIRECT = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INVOKE_INSTANCE = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INVOKE_INTERFACE = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_INVOKE_STATIC = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_STATIC_GET = null;
        public static final MethodHandleType METHOD_HANDLE_TYPE_STATIC_PUT = null;
        private final int value;

        static {
            MethodHandleType r0 = new MethodHandleType("METHOD_HANDLE_TYPE_STATIC_PUT", 0, 0);
            METHOD_HANDLE_TYPE_STATIC_PUT = r0;
            MethodHandleType r1 = new MethodHandleType("METHOD_HANDLE_TYPE_STATIC_GET", 1, 1);
            METHOD_HANDLE_TYPE_STATIC_GET = r1;
            MethodHandleType r2 = new MethodHandleType("METHOD_HANDLE_TYPE_INSTANCE_PUT", 2, 2);
            METHOD_HANDLE_TYPE_INSTANCE_PUT = r2;
            MethodHandleType r3 = new MethodHandleType("METHOD_HANDLE_TYPE_INSTANCE_GET", 3, 3);
            METHOD_HANDLE_TYPE_INSTANCE_GET = r3;
            MethodHandleType r4 = new MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_STATIC", 4, 4);
            METHOD_HANDLE_TYPE_INVOKE_STATIC = r4;
            MethodHandleType r5 = new MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_INSTANCE", 5, 5);
            METHOD_HANDLE_TYPE_INVOKE_INSTANCE = r5;
            MethodHandleType r6 = new MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_DIRECT", 6, 6);
            METHOD_HANDLE_TYPE_INVOKE_DIRECT = r6;
            MethodHandleType r7 = new MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR", 7, 7);
            METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR = r7;
            MethodHandleType r8 = new MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_INTERFACE", 8, 8);
            METHOD_HANDLE_TYPE_INVOKE_INTERFACE = r8;
            $VALUES = new MethodHandleType[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
        }

        MethodHandleType(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static /* synthetic */ int access$000(MethodHandleType r0) {
            return r0.value;
        }

        public static MethodHandleType fromValue(int r5) {
            MethodHandleType[] r0 = values();
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L8;
            MethodHandleType r3 = r0[r2];
            if (r3.value == r5) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r3;
        L8:
            f$$ExternalSyntheticBUOutline1.m(String.valueOf(r5));
            return null;
        }

        public static MethodHandleType valueOf(String r1) {
            return (MethodHandleType) Enum.valueOf(MethodHandleType.class, r1);
        }

        public static MethodHandleType[] values() {
            return (MethodHandleType[]) $VALUES.clone();
        }

        public boolean isField() {
            int r2 = AnonymousClass1.$SwitchMap$com$android$dex$MethodHandle$MethodHandleType[ordinal()];
            if (r2 != 1) goto L5;
        L12:
            return true;
        L5:
            if (r2 == 2) goto L12;
            if (r2 == 3) goto L12;
            if (r2 == 4) goto L12;
            return false;
        }
    }

    public MethodHandle(Dex r1, MethodHandleType r2, int r3, int r4, int r5) {
        this.dex = r1;
        this.methodHandleType = r2;
        this.unused1 = r3;
        this.fieldOrMethodId = r4;
        this.unused2 = r5;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(MethodHandle r3) {
        MethodHandleType r0 = this.methodHandleType;
        MethodHandleType r1 = r3.methodHandleType;
        if (r0 == r1) goto L7;
        return r0.compareTo(r1);
    L7:
        return Unsigned.compare(this.fieldOrMethodId, r3.fieldOrMethodId);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(MethodHandle r1) {
        return compareTo2(r1);
    }

    public int getFieldOrMethodId() {
        return this.fieldOrMethodId;
    }

    public MethodHandleType getMethodHandleType() {
        return this.methodHandleType;
    }

    public int getUnused1() {
        return this.unused1;
    }

    public int getUnused2() {
        return this.unused2;
    }

    public String toString() {
        Dex r0 = this.dex;
        MethodHandleType r1 = this.methodHandleType;
        if (r0 == null) goto L5;
        StringBuilder r02 = new StringBuilder();
        r02.append(r1);
        r02.append(" ");
        boolean r12 = this.methodHandleType.isField();
        Dex r2 = this.dex;
        if (r12 == false) goto L9;
        MethodId r3 = r2.fieldIds().get(this.fieldOrMethodId);
    L10:
        r02.append(r3);
        return r02.toString();
    L9:
        r3 = r2.methodIds().get(this.fieldOrMethodId);
        goto L10
    L5:
        return r1 + " " + this.fieldOrMethodId;
    }

    public void writeTo(Dex.Section r2) {
        r2.writeUnsignedShort(MethodHandleType.access$000(this.methodHandleType));
        r2.writeUnsignedShort(this.unused1);
        r2.writeUnsignedShort(this.fieldOrMethodId);
        r2.writeUnsignedShort(this.unused2);
    }
}
