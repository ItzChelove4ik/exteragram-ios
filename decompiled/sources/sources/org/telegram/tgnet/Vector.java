package org.telegram.tgnet;

import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.TranscribeButton;

/* loaded from: classes.dex */
public class Vector<T extends TLObject> extends TLObject {
    public static final int constructor = 481674261;
    private final TLDeserializer<T> itemDeserializer;
    public final ArrayList<T> objects;

    /* loaded from: classes6.dex */
    public static class Int extends TLObject {
        public int value;

        public Int(int r1) {
            this.value = r1;
        }

        public static Int TLDeserialize(InputSerializedData r0, int r1, boolean r2) {
            return new Int(r1);
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.value = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(this.value);
        }
    }

    /* loaded from: classes6.dex */
    public static class Long extends TLObject {
        public long value;

        public Long(int r5, int r6) {
            this.value = (r6 & 4294967295L) | (r5 << 32);
        }

        public static Long TLDeserialize(InputSerializedData r1, int r2, boolean r3) {
            return new Long(r2, r1.readInt32(r3));
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.value = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt64(this.value);
        }
    }

    @FunctionalInterface
    public interface TLDeserializer<T extends TLObject> {
        T deserialize(InputSerializedData r1, int r2, boolean r3);
    }

    public Vector(TLDeserializer<T> r2) {
        this.objects = new ArrayList();
        this.itemDeserializer = r2;
    }

    public static <T extends TLObject> Vector<T> TLDeserialize(InputSerializedData r1, int r2, boolean r3, TLDeserializer<T> r4) {
        if (r2 == 481674261) goto L6;
        TLParseException.doThrowOrLog(r1, "Vector", r2, r3);
        return null;
    L6:
        Vector<T> r22 = new Vector(r4);
        r22.readParams(r1, r3);
        return r22;
    }

    public static Vector<Int> TLDeserializeInt(InputSerializedData r1, int r2, boolean r3) {
        if (r2 == 481674261) goto L6;
        TLParseException.doThrowOrLog(r1, "StarGift", r2, r3);
        return null;
    L6:
        Vector<Int> r22 = new Vector(new Vector$$ExternalSyntheticLambda5());
        r22.readParams(r1, r3);
        return r22;
    }

    public static Vector<Int> TLDeserializeLong(InputSerializedData r1, int r2, boolean r3) {
        if (r2 == 481674261) goto L6;
        TLParseException.doThrowOrLog(r1, "Vector", r2, r3);
        return null;
    L6:
        Vector<Int> r22 = new Vector(new Vector$$ExternalSyntheticLambda5());
        r22.readParams(r1, r3);
        return r22;
    }

    private static <T> ArrayList<T> deserialize(InputSerializedData r4, Utilities.CallbackReturn<Boolean, T> r5, boolean r6) {
        int r0 = r4.readInt32(r6);
        if (r0 == 481674261) goto L6;
        TLParseException.doThrowOrLog(r4, "Vector", r0, r6);
        return new ArrayList();
    L6:
        int r1 = r4.readInt32(r6);
        if (validateSize(r1, 1, r4.remaining()) == true) goto L10;
        TLParseException.doThrowOrLog(r4, "VectorWrongSize", r0, r6);
        return new ArrayList();
    L10:
        ArrayList<T> r42 = new ArrayList(r1);
        int r02 = 0;
    L11:
        if (r02 >= r1) goto L13;
        r42.add(r5.run(Boolean.valueOf(r6)));
        r02 = r02 + 1;
        goto L11
    L13:
        return r42;
    }

    public static <T extends TLObject> ArrayList<T> deserialize(InputSerializedData r4, TLDeserializer<T> r5, boolean r6) {
        int r0 = r4.readInt32(r6);
        if (r0 == 481674261) goto L6;
        TLParseException.doThrowOrLog(r4, "Vector", r0, r6);
        return new ArrayList();
    L6:
        int r1 = r4.readInt32(r6);
        if (validateSize(r1, 4, r4.remaining()) == true) goto L10;
        TLParseException.doThrowOrLog(r4, "VectorWrongSize", r0, r6);
        return new ArrayList();
    L10:
        TranscribeButton.AnonymousClass1 r02 = (ArrayList<T>) new ArrayList(r1);
        int r2 = 0;
    L11:
        if (r2 >= r1) goto L16;
        TLObject r3 = r5.deserialize(r4, r4.readInt32(r6), r6);
        if (r3 == null) goto L15;
        r02.add(r3);
    L15:
        r2 = r2 + 1;
        goto L11
    L16:
        return r02;
    }

    public static ArrayList<byte[]> deserializeByteArray(InputSerializedData r1, boolean r2) {
        Objects.requireNonNull(r1);
        return deserialize(r1, new Vector$$ExternalSyntheticLambda6(r1), r2);
    }

    public static ArrayList<Integer> deserializeInt(InputSerializedData r1, boolean r2) {
        Objects.requireNonNull(r1);
        return deserialize(r1, new Vector$$ExternalSyntheticLambda1(r1), r2);
    }

    public static ArrayList<java.lang.Long> deserializeLong(InputSerializedData r1, boolean r2) {
        Objects.requireNonNull(r1);
        return deserialize(r1, new Vector$$ExternalSyntheticLambda0(r1), r2);
    }

    public static ArrayList<String> deserializeString(InputSerializedData r1, boolean r2) {
        Objects.requireNonNull(r1);
        return deserialize(r1, new Vector$$ExternalSyntheticLambda4(r1), r2);
    }

    public static <T extends TLObject> void serialize(OutputSerializedData r2, ArrayList<T> r3) {
        r2.writeInt32(constructor);
        r2.writeInt32(r3.size());
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L6;
        r3.get(r0).serializeToStream(r2);
        r0 = r0 + 1;
        goto L4
    }

    public static <T> void serialize(OutputSerializedData r1, Utilities.Callback<T> r2, ArrayList<T> r3) {
        r1.writeInt32(constructor);
        r1.writeInt32(r3.size());
        int r12 = 0;
    L4:
        if (r12 >= r3.size()) goto L6;
        r2.run(r3.get(r12));
        r12 = r12 + 1;
        goto L4
    }

    public static void serializeByteArray(OutputSerializedData r1, ArrayList<byte[]> r2) {
        Objects.requireNonNull(r1);
        serialize(r1, new Vector$$ExternalSyntheticLambda7(r1), r2);
    }

    public static void serializeInt(OutputSerializedData r1, ArrayList<Integer> r2) {
        Objects.requireNonNull(r1);
        serialize(r1, new Vector$$ExternalSyntheticLambda3(r1), r2);
    }

    public static void serializeLong(OutputSerializedData r1, ArrayList<java.lang.Long> r2) {
        Objects.requireNonNull(r1);
        serialize(r1, new Vector$$ExternalSyntheticLambda2(r1), r2);
    }

    public static void serializeString(OutputSerializedData r1, ArrayList<String> r2) {
        Objects.requireNonNull(r1);
        serialize(r1, new Vector$$ExternalSyntheticLambda8(r1), r2);
    }

    private static boolean validateSize(int r2, int r3, int r4) {
        if (r2 < 0) goto L8;
        if (r3 > 0) goto L5;
        return false;
    L5:
        if ((r2 * r3) > r4) goto L11;
        return true;
    L11:
        return false;
    L8:
        return false;
    }

    @Override // org.telegram.tgnet.TLObject
    public void readParams(InputSerializedData r6, boolean r7) {
        int r0 = r6.readInt32(r7);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.objects.add(this.itemDeserializer.deserialize(r6, r6.readInt32(r7), r7));
        r1 = r1 + 1;
        goto L3
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r1) {
        serialize(r1, this.objects);
    }

    public ArrayList<Integer> toIntArray() {
        ArrayList<Integer> r0 = new ArrayList();
        ArrayList<T> r5 = this.objects;
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        T r3 = r5.get(r2);
        r2 = r2 + 1;
        T r32 = r3;
        if ((r32 instanceof Int) == false) goto L3;
        r0.add(Integer.valueOf(((Int) r32).value));
        goto L3
    L7:
        return r0;
    }
}
