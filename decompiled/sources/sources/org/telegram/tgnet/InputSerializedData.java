package org.telegram.tgnet;

/* loaded from: classes.dex */
public interface InputSerializedData {
    TLDataSourceType getDataSourceType();

    int getPosition();

    int length();

    boolean readBool(boolean r1);

    byte readByte(boolean r1);

    byte[] readByteArray(boolean r1);

    NativeByteBuffer readByteBuffer(boolean r1);

    void readBytes(byte[] r1, boolean r2);

    byte[] readData(int r1, boolean r2);

    double readDouble(boolean r1);

    float readFloat(boolean r1);

    int readInt32(boolean r1);

    long readInt64(boolean r1);

    String readString(boolean r1);

    int remaining();

    void skip(int r1);
}
