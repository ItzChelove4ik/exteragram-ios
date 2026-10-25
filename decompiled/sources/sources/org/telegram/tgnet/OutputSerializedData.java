package org.telegram.tgnet;

/* loaded from: classes.dex */
public interface OutputSerializedData {
    int getPosition();

    void skip(int r1);

    void writeBool(boolean r1);

    void writeByte(byte r1);

    void writeByte(int r1);

    void writeByteArray(byte[] r1);

    void writeByteArray(byte[] r1, int r2, int r3);

    void writeByteBuffer(NativeByteBuffer r1);

    void writeBytes(byte[] r1);

    void writeBytes(byte[] r1, int r2, int r3);

    void writeDouble(double r1);

    void writeFloat(float r1);

    void writeInt32(int r1);

    void writeInt64(long r1);

    void writeString(String r1);
}
