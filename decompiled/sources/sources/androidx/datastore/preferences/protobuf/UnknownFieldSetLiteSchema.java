package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
class UnknownFieldSetLiteSchema extends UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> {
    public UnknownFieldSetLiteSchema() {
    }

    /* renamed from: addFixed32, reason: avoid collision after fix types in other method */
    public void addFixed322(UnknownFieldSetLite r1, int r2, int r3) {
        r1.storeField(WireFormat.makeTag(r2, 5), Integer.valueOf(r3));
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void addFixed32(UnknownFieldSetLite r1, int r2, int r3) {
        addFixed322(r1, r2, r3);
    }

    /* renamed from: addFixed64, reason: avoid collision after fix types in other method */
    public void addFixed642(UnknownFieldSetLite r1, int r2, long r3) {
        r1.storeField(WireFormat.makeTag(r2, 1), Long.valueOf(r3));
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void addFixed64(UnknownFieldSetLite r1, int r2, long r3) {
        addFixed642(r1, r2, r3);
    }

    /* renamed from: addGroup, reason: avoid collision after fix types in other method */
    public void addGroup2(UnknownFieldSetLite r1, int r2, UnknownFieldSetLite r3) {
        r1.storeField(WireFormat.makeTag(r2, 3), r3);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void addGroup(UnknownFieldSetLite r1, int r2, UnknownFieldSetLite r3) {
        addGroup2(r1, r2, r3);
    }

    /* renamed from: addLengthDelimited, reason: avoid collision after fix types in other method */
    public void addLengthDelimited2(UnknownFieldSetLite r1, int r2, ByteString r3) {
        r1.storeField(WireFormat.makeTag(r2, 2), r3);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void addLengthDelimited(UnknownFieldSetLite r1, int r2, ByteString r3) {
        addLengthDelimited2(r1, r2, r3);
    }

    /* renamed from: addVarint, reason: avoid collision after fix types in other method */
    public void addVarint2(UnknownFieldSetLite r1, int r2, long r3) {
        r1.storeField(WireFormat.makeTag(r2, 0), Long.valueOf(r3));
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void addVarint(UnknownFieldSetLite r1, int r2, long r3) {
        addVarint2(r1, r2, r3);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public UnknownFieldSetLite getBuilderFromMessage(Object r3) {
        UnknownFieldSetLite r0 = getFromMessage(r3);
        if (r0 != UnknownFieldSetLite.getDefaultInstance()) goto L6;
        UnknownFieldSetLite r02 = UnknownFieldSetLite.newInstance();
        setToMessage2(r3, r02);
        return r02;
    L6:
        return r0;
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ UnknownFieldSetLite getBuilderFromMessage(Object r1) {
        return getBuilderFromMessage(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public UnknownFieldSetLite getFromMessage(Object r1) {
        return ((GeneratedMessageLite) r1).unknownFields;
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ UnknownFieldSetLite getFromMessage(Object r1) {
        return getFromMessage(r1);
    }

    /* renamed from: getSerializedSize, reason: avoid collision after fix types in other method */
    public int getSerializedSize2(UnknownFieldSetLite r1) {
        return r1.getSerializedSize();
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ int getSerializedSize(UnknownFieldSetLite r1) {
        return getSerializedSize2(r1);
    }

    /* renamed from: getSerializedSizeAsMessageSet, reason: avoid collision after fix types in other method */
    public int getSerializedSizeAsMessageSet2(UnknownFieldSetLite r1) {
        return r1.getSerializedSizeAsMessageSet();
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ int getSerializedSizeAsMessageSet(UnknownFieldSetLite r1) {
        return getSerializedSizeAsMessageSet2(r1);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public void makeImmutable(Object r1) {
        getFromMessage(r1).makeImmutable();
    }

    /* renamed from: merge, reason: avoid collision after fix types in other method */
    public UnknownFieldSetLite merge2(UnknownFieldSetLite r1, UnknownFieldSetLite r2) {
        if (UnknownFieldSetLite.getDefaultInstance().equals(r2) == false) goto L6;
        return r1;
    L6:
        if (UnknownFieldSetLite.getDefaultInstance().equals(r1) == false) goto L10;
        return UnknownFieldSetLite.mutableCopyOf(r1, r2);
    L10:
        return r1.mergeFrom(r2);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ UnknownFieldSetLite merge(UnknownFieldSetLite r1, UnknownFieldSetLite r2) {
        return merge2(r1, r2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public UnknownFieldSetLite newBuilder() {
        return UnknownFieldSetLite.newInstance();
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ UnknownFieldSetLite newBuilder() {
        return newBuilder();
    }

    /* renamed from: setBuilderToMessage, reason: avoid collision after fix types in other method */
    public void setBuilderToMessage2(Object r1, UnknownFieldSetLite r2) {
        setToMessage2(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void setBuilderToMessage(Object r1, UnknownFieldSetLite r2) {
        setBuilderToMessage2(r1, r2);
    }

    /* renamed from: setToMessage, reason: avoid collision after fix types in other method */
    public void setToMessage2(Object r1, UnknownFieldSetLite r2) {
        ((GeneratedMessageLite) r1).unknownFields = r2;
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void setToMessage(Object r1, UnknownFieldSetLite r2) {
        setToMessage2(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public boolean shouldDiscardUnknownFields(Reader r1) {
        return false;
    }

    /* renamed from: toImmutable, reason: avoid collision after fix types in other method */
    public UnknownFieldSetLite toImmutable2(UnknownFieldSetLite r1) {
        r1.makeImmutable();
        return r1;
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ UnknownFieldSetLite toImmutable(UnknownFieldSetLite r1) {
        return toImmutable2(r1);
    }

    /* renamed from: writeAsMessageSetTo, reason: avoid collision after fix types in other method */
    public void writeAsMessageSetTo2(UnknownFieldSetLite r1, Writer r2) {
        r1.writeAsMessageSetTo(r2);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void writeAsMessageSetTo(UnknownFieldSetLite r1, Writer r2) {
        writeAsMessageSetTo2(r1, r2);
    }

    /* renamed from: writeTo, reason: avoid collision after fix types in other method */
    public void writeTo2(UnknownFieldSetLite r1, Writer r2) {
        r1.writeTo(r2);
    }

    @Override // androidx.datastore.preferences.protobuf.UnknownFieldSchema
    public /* bridge */ /* synthetic */ void writeTo(UnknownFieldSetLite r1, Writer r2) {
        writeTo2(r1, r2);
    }
}
