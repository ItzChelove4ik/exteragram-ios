package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
interface Reader {
    int getFieldNumber();

    int getTag();

    <T> void mergeGroupField(T r1, Schema<T> r2, ExtensionRegistryLite r3);

    <T> void mergeMessageField(T r1, Schema<T> r2, ExtensionRegistryLite r3);

    boolean readBool();

    void readBoolList(List<Boolean> r1);

    ByteString readBytes();

    void readBytesList(List<ByteString> r1);

    double readDouble();

    void readDoubleList(List<Double> r1);

    int readEnum();

    void readEnumList(List<Integer> r1);

    int readFixed32();

    void readFixed32List(List<Integer> r1);

    long readFixed64();

    void readFixed64List(List<Long> r1);

    float readFloat();

    void readFloatList(List<Float> r1);

    @Deprecated
    <T> T readGroup(Class<T> r1, ExtensionRegistryLite r2);

    @Deprecated
    <T> void readGroupList(List<T> r1, Schema<T> r2, ExtensionRegistryLite r3);

    int readInt32();

    void readInt32List(List<Integer> r1);

    long readInt64();

    void readInt64List(List<Long> r1);

    <K, V> void readMap(Map<K, V> r1, MapEntryLite.Metadata<K, V> r2, ExtensionRegistryLite r3);

    <T> T readMessage(Class<T> r1, ExtensionRegistryLite r2);

    <T> void readMessageList(List<T> r1, Schema<T> r2, ExtensionRegistryLite r3);

    int readSFixed32();

    void readSFixed32List(List<Integer> r1);

    long readSFixed64();

    void readSFixed64List(List<Long> r1);

    int readSInt32();

    void readSInt32List(List<Integer> r1);

    long readSInt64();

    void readSInt64List(List<Long> r1);

    String readString();

    void readStringList(List<String> r1);

    void readStringListRequireUtf8(List<String> r1);

    String readStringRequireUtf8();

    int readUInt32();

    void readUInt32List(List<Integer> r1);

    long readUInt64();

    void readUInt64List(List<Long> r1);

    boolean skipField();
}
