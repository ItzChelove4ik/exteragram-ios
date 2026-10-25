package org.telegram.tgnet;

/* loaded from: classes.dex */
public abstract class AbstractSerializedData implements InputSerializedData, OutputSerializedData {
    private TLDataSourceType dataSourceType;

    public AbstractSerializedData() {
        this.dataSourceType = TLDataSourceType.UNKNOWN;
    }

    @Override // org.telegram.tgnet.InputSerializedData
    public TLDataSourceType getDataSourceType() {
        return this.dataSourceType;
    }

    @Override // org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.OutputSerializedData
    public abstract int getPosition();

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract int length();

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract boolean readBool(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract byte readByte(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract byte[] readByteArray(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract NativeByteBuffer readByteBuffer(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract void readBytes(byte[] r1, boolean r2);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract byte[] readData(int r1, boolean r2);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract double readDouble(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract float readFloat(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract int readInt32(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract long readInt64(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract String readString(boolean r1);

    @Override // org.telegram.tgnet.InputSerializedData
    public abstract int remaining();

    public void setDataSourceType(TLDataSourceType r1) {
        this.dataSourceType = r1;
    }

    @Override // org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.OutputSerializedData
    public abstract void skip(int r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeBool(boolean r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeByte(byte r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeByte(int r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeByteArray(byte[] r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeByteArray(byte[] r1, int r2, int r3);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeByteBuffer(NativeByteBuffer r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeBytes(byte[] r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeBytes(byte[] r1, int r2, int r3);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeDouble(double r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeFloat(float r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeInt32(int r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeInt64(long r1);

    @Override // org.telegram.tgnet.OutputSerializedData
    public abstract void writeString(String r1);
}
