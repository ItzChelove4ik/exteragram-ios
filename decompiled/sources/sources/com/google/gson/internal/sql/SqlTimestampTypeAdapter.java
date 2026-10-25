package com.google.gson.internal.sql;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.sql.Timestamp;
import java.util.Date;

/* loaded from: classes.dex */
class SqlTimestampTypeAdapter extends TypeAdapter<Timestamp> {
    static final TypeAdapterFactory FACTORY = null;
    private final TypeAdapter<Date> dateTypeAdapter;

    /* renamed from: com.google.gson.internal.sql.SqlTimestampTypeAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements TypeAdapterFactory {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r2, TypeToken<T> r3) {
            if (r3.getRawType() == Timestamp.class) goto L5;
            return null;
        L5:
            return new SqlTimestampTypeAdapter(r2.getAdapter(Date.class), null);
        }
    }

    static {
        FACTORY = new AnonymousClass1();
    }

    private SqlTimestampTypeAdapter(TypeAdapter<Date> r1) {
        this.dateTypeAdapter = r1;
    }

    public /* synthetic */ SqlTimestampTypeAdapter(TypeAdapter r1, AnonymousClass1 r2) {
        this(r1);
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ Timestamp read(JsonReader r1) {
        return read2(r1);
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read, reason: avoid collision after fix types in other method */
    public Timestamp read2(JsonReader r3) {
        Date r2 = this.dateTypeAdapter.read(r3);
        if (r2 != null) goto L5;
        return null;
    L5:
        return new Timestamp(r2.getTime());
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter r1, Timestamp r2) {
        write2(r1, r2);
    }

    /* renamed from: write, reason: avoid collision after fix types in other method */
    public void write2(JsonWriter r1, Timestamp r2) {
        this.dateTypeAdapter.write(r1, r2);
    }
}
