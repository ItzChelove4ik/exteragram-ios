package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/* loaded from: classes.dex */
public final class DefaultDateTypeAdapter<T extends Date> extends TypeAdapter<T> {
    public static final TypeAdapterFactory DEFAULT_STYLE_FACTORY = null;
    private final List<DateFormat> dateFormats;
    private final DateType<T> dateType;

    /* renamed from: com.google.gson.internal.bind.DefaultDateTypeAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements TypeAdapterFactory {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r2, TypeToken<T> r3) {
            if (r3.getRawType() == Date.class) goto L5;
            return null;
        L5:
            return new DefaultDateTypeAdapter(DateType.DATE, 2, 2, null);
        }

        public String toString() {
            return "DefaultDateTypeAdapter#DEFAULT_STYLE_FACTORY";
        }
    }

    public static abstract class DateType<T extends Date> {
        public static final DateType<Date> DATE = null;
        private final Class<T> dateClass;

        /* renamed from: com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType$1, reason: invalid class name */
        public class AnonymousClass1 extends DateType<Date> {
            public AnonymousClass1(Class r1) {
                super(r1);
            }

            @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
            public Date deserialize(Date r1) {
                return r1;
            }
        }

        static {
            DATE = new AnonymousClass1(Date.class);
        }

        public DateType(Class<T> r1) {
            this.dateClass = r1;
        }

        private TypeAdapterFactory createFactory(DefaultDateTypeAdapter<T> r1) {
            return TypeAdapters.newFactory(this.dateClass, r1);
        }

        public final TypeAdapterFactory createAdapterFactory(int r3, int r4) {
            return createFactory(new DefaultDateTypeAdapter(this, r3, r4, null));
        }

        public final TypeAdapterFactory createAdapterFactory(String r3) {
            return createFactory(new DefaultDateTypeAdapter(this, r3, null));
        }

        public abstract T deserialize(Date r1);
    }

    static {
        DEFAULT_STYLE_FACTORY = new AnonymousClass1();
    }

    private DefaultDateTypeAdapter(DateType<T> r2, int r3, int r4) {
        ArrayList r0 = new ArrayList();
        this.dateFormats = r0;
        Objects.requireNonNull(r2);
        this.dateType = r2;
        Locale r1 = Locale.US;
        r0.add(DateFormat.getDateTimeInstance(r3, r4, r1));
        if (Locale.getDefault().equals(r1) == true) goto L6;
        r0.add(DateFormat.getDateTimeInstance(r3, r4));
    L6:
        if (JavaVersion.isJava9OrLater() == false) goto L9;
        r0.add(PreJava9DateFormatProvider.getUsDateTimeFormat(r3, r4));
        return;
    }

    public /* synthetic */ DefaultDateTypeAdapter(DateType r1, int r2, int r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    private DefaultDateTypeAdapter(DateType<T> r2, String r3) {
        ArrayList r0 = new ArrayList();
        this.dateFormats = r0;
        Objects.requireNonNull(r2);
        this.dateType = r2;
        Locale r22 = Locale.US;
        r0.add(new SimpleDateFormat(r3, r22));
        if (Locale.getDefault().equals(r22) == true) goto L6;
        r0.add(new SimpleDateFormat(r3));
        return;
    }

    public /* synthetic */ DefaultDateTypeAdapter(DateType r1, String r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    private Date deserializeToDate(JsonReader r5) {
        String r0 = r5.nextString();
        List<DateFormat> r1 = this.dateFormats;
        monitor-enter(r1);
        Iterator<DateFormat> r4 = this.dateFormats.iterator();     // Catch: Throwable -> L12
    L6:
        if (r4.hasNext() == false) goto L18;
        DateFormat r2 = r4.next();     // Catch: Throwable -> L12
        TimeZone r3 = r2.getTimeZone();     // Catch: Throwable -> L12
        Date r42 = r2.parse(r0);     // Catch: Throwable -> L14 ParseException -> L17
    L9:
        r2.setTimeZone(r3);     // Catch: Throwable -> L12
        monitor-exit(r1);     // Catch: Throwable -> L12
        return r42;
    L14:
        th = move-exception;
        r2.setTimeZone(r3);     // Catch: Throwable -> L12
        throw th;     // Catch: Throwable -> L12
    L17:
        r2.setTimeZone(r3);     // Catch: Throwable -> L12
        goto L6
    L18:
        monitor-exit(r1);     // Catch: Throwable -> L12
        return ISO8601Utils.parse(r0, new ParsePosition(0));
    L21:
        e = move-exception;
        TypeAdapters$17$$ExternalSyntheticBUOutline0.m(r0, "' as Date; at path ", r5.getPreviousPath(), e);
        return null;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ Object read(JsonReader r1) {
        return read(r1);
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader r3) {
        if (r3.peek() != JsonToken.NULL) goto L6;
        r3.nextNull();
        return null;
    L6:
        Date r32 = deserializeToDate(r3);
        return (T) this.dateType.deserialize(r32);
    }

    public String toString() {
        DateFormat r3 = this.dateFormats.get(0);
        if ((r3 instanceof SimpleDateFormat) == false) goto L7;
        return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) r3).toPattern() + ')';
    L7:
        return "DefaultDateTypeAdapter(" + r3.getClass().getSimpleName() + ')';
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter r1, Object r2) {
        write(r1, (Date) r2);
    }

    public void write(JsonWriter r3, Date r4) {
        if (r4 != null) goto L5;
        r3.nullValue();
        return;
    L5:
        DateFormat r0 = this.dateFormats.get(0);
        List<DateFormat> r2 = this.dateFormats;
        monitor-enter(r2);
        String r42 = r0.format(r4);     // Catch: Throwable -> L11
        monitor-exit(r2);     // Catch: Throwable -> L11
        r3.value(r42);
        return;
    L11:
        th = move-exception;
        throw th;
    }
}
