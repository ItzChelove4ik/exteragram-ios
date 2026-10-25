package com.google.gson.internal.sql;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* loaded from: classes.dex */
final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    static final TypeAdapterFactory FACTORY = null;
    private final DateFormat format;

    /* renamed from: com.google.gson.internal.sql.SqlDateTypeAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements TypeAdapterFactory {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2) {
            if (r2.getRawType() == Date.class) goto L5;
            return null;
        L5:
            return new SqlDateTypeAdapter(null);
        }
    }

    static {
        FACTORY = new AnonymousClass1();
    }

    private SqlDateTypeAdapter() {
        this.format = new SimpleDateFormat("MMM d, yyyy");
    }

    public /* synthetic */ SqlDateTypeAdapter(AnonymousClass1 r1) {
        this();
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ Date read(JsonReader r1) {
        return read2(r1);
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read, reason: avoid collision after fix types in other method */
    public Date read2(JsonReader r8) {
        if (r8.peek() != JsonToken.NULL) goto L6;
        r8.nextNull();
        return null;
    L6:
        String r1 = r8.nextString();
        monitor-enter(this);
        TimeZone r2 = this.format.getTimeZone();     // Catch: Throwable -> L13
        Date r4 = new Date(this.format.parse(r1).getTime());     // Catch: Throwable -> L15 ParseException -> L17
        this.format.setTimeZone(r2);     // Catch: Throwable -> L13
        monitor-exit(this);     // Catch: Throwable -> L13
        return r4;
    L15:
        th = move-exception;
        this.format.setTimeZone(r2);     // Catch: Throwable -> L13
        throw th;     // Catch: Throwable -> L13
    L17:
        e = move-exception;
        throw new JsonSyntaxException("Failed parsing '" + r1 + "' as SQL Date; at path " + r8.getPreviousPath(), e);     // Catch: Throwable -> L15
    L13:
        th = move-exception;
        throw th;
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter r1, Date r2) {
        write2(r1, r2);
    }

    /* renamed from: write, reason: avoid collision after fix types in other method */
    public void write2(JsonWriter r2, Date r3) {
        if (r3 != null) goto L5;
        r2.nullValue();
        return;
    L5:
        monitor-enter(this);
        String r32 = this.format.format(r3);     // Catch: Throwable -> L10
        monitor-exit(this);     // Catch: Throwable -> L10
        r2.value(r32);
        return;
    L10:
        th = move-exception;
        throw th;
    }
}
