package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import j$.time.Duration;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.MonthDay;
import j$.time.OffsetDateTime;
import j$.time.OffsetTime;
import j$.time.Period;
import j$.time.Year;
import j$.time.YearMonth;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.ZonedDateTime;

/* loaded from: classes5.dex */
final class JavaTimeTypeAdapters implements TypeAdapters.FactorySupplier {
    private static final TypeAdapter<Duration> DURATION = null;
    private static final TypeAdapter<Instant> INSTANT = null;
    static final TypeAdapterFactory JAVA_TIME_FACTORY = null;
    private static final TypeAdapter<LocalDate> LOCAL_DATE = null;
    public static final TypeAdapter<LocalTime> LOCAL_TIME = null;
    private static final TypeAdapter<MonthDay> MONTH_DAY = null;
    private static final TypeAdapter<Period> PERIOD = null;
    private static final TypeAdapter<Year> YEAR = null;
    private static final TypeAdapter<YearMonth> YEAR_MONTH = null;
    private static final TypeAdapter<ZoneId> ZONE_ID = null;

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$1, reason: invalid class name */
    public class AnonymousClass1 extends TypeAdapters.IntegerFieldsTypeAdapter<Duration> {
        public AnonymousClass1(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Duration create(long[] r3) {
            return Duration.ofSeconds(r3[0], r3[1]);
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ Duration create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(Duration r5) {
            return new long[]{r5.getSeconds(), r5.getNano()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(Duration r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$10, reason: invalid class name */
    public class AnonymousClass10 extends TypeAdapters.IntegerFieldsTypeAdapter<Year> {
        public AnonymousClass10(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Year create(long[] r1) {
            return Year.of(Math.toIntExact(r1[0]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ Year create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(Year r3) {
            return new long[]{r3.getValue()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(Year r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$11, reason: invalid class name */
    public class AnonymousClass11 extends TypeAdapters.IntegerFieldsTypeAdapter<YearMonth> {
        public AnonymousClass11(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public YearMonth create(long[] r3) {
            return YearMonth.of(Math.toIntExact(r3[0]), Math.toIntExact(r3[1]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ YearMonth create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(YearMonth r5) {
            return new long[]{r5.getYear(), r5.getMonthValue()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(YearMonth r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$12, reason: invalid class name */
    public class AnonymousClass12 extends TypeAdapter<ZoneId> {
        public AnonymousClass12() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public ZoneId read(JsonReader r4) {
            r4.beginObject();
            String r3 = null;
            Integer r0 = null;
        L4:
            if (r4.peek() == JsonToken.END_OBJECT) goto L12;
            String r1 = r4.nextName();
            r1.getClass();
            if (r1.equals("totalSeconds") == false) goto L8;
            r0 = Integer.valueOf(r4.nextInt());
            goto L4
        L8:
            if (r1.equals("id") == false) goto L9;
            r3 = r4.nextString();
            goto L4
        L9:
            r4.skipValue();
            goto L4
        L12:
            r4.endObject();
            if (r3 != null) goto L15;
            if (r0 == null) goto L20;
            return ZoneOffset.ofTotalSeconds(r0.intValue());
        L20:
            throw new JsonSyntaxException("Missing id or totalSeconds field; at path " + r4.getPreviousPath());
        L15:
            return ZoneId.of(r3);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ ZoneId read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, ZoneId r4) {
            if ((r4 instanceof ZoneOffset) == false) goto L6;
            r3.beginObject();
            r3.name("totalSeconds");
            r3.value(((ZoneOffset) r4).getTotalSeconds());
            r3.endObject();
            return;
        L6:
            r3.beginObject();
            r3.name("id");
            r3.value(r4.getId());
            r3.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, ZoneId r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$13, reason: invalid class name */
    public class AnonymousClass13 extends TypeAdapter<ZonedDateTime> {
        final /* synthetic */ TypeAdapter val$localDateTimeAdapter;
        final /* synthetic */ TypeAdapter val$zoneIdAdapter;
        final /* synthetic */ TypeAdapter val$zoneOffsetAdapter;

        public AnonymousClass13(TypeAdapter r1, TypeAdapter r2, TypeAdapter r3) {
            this.val$localDateTimeAdapter = r1;
            this.val$zoneOffsetAdapter = r2;
            this.val$zoneIdAdapter = r3;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public ZonedDateTime read(JsonReader r10) {
            r10.beginObject();
            LocalDateTime r0 = null;
            ZoneOffset r1 = null;
            ZoneId r2 = null;
        L4:
            if (r10.peek() == JsonToken.END_OBJECT) goto L25;
            String r3 = r10.nextName();
            r3.getClass();
            char r8 = 65535;
            switch(r3.hashCode()) {
                case -1019779949: goto L17;
                case 3744684: goto L13;
                case 1792749467: goto L9;
                default: goto L20;
            };
        L20:
            switch(r8) {
                case 0: goto L24;
                case 1: goto L23;
                case 2: goto L22;
                default: goto L21;
            };
        L22:
            r0 = (LocalDateTime) this.val$localDateTimeAdapter.read(r10);
            goto L4
        L23:
            r2 = (ZoneId) this.val$zoneIdAdapter.read(r10);
            goto L4
        L24:
            r1 = (ZoneOffset) this.val$zoneOffsetAdapter.read(r10);
            goto L4
        L21:
            r10.skipValue();
            goto L4
        L9:
            if (r3.equals("dateTime") == false) goto L20;
            r8 = 2;
            goto L20
        L13:
            if (r3.equals("zone") == false) goto L20;
            r8 = 1;
            goto L20
        L17:
            if (r3.equals("offset") == false) goto L20;
            r8 = 0;
            goto L20
        L25:
            r10.endObject();
            return ZonedDateTime.ofInstant((LocalDateTime) JavaTimeTypeAdapters.access$000(r0, "dateTime", r10), (ZoneOffset) JavaTimeTypeAdapters.access$000(r1, "offset", r10), (ZoneId) JavaTimeTypeAdapters.access$000(r2, "zone", r10));
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ ZonedDateTime read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, ZonedDateTime r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            r3.beginObject();
            r3.name("dateTime");
            this.val$localDateTimeAdapter.write(r3, r4.toLocalDateTime());
            r3.name("offset");
            this.val$zoneOffsetAdapter.write(r3, r4.getOffset());
            r3.name("zone");
            this.val$zoneIdAdapter.write(r3, r4.getZone());
            r3.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, ZonedDateTime r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$14, reason: invalid class name */
    public class AnonymousClass14 implements TypeAdapterFactory {
        public AnonymousClass14() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r2, TypeToken<T> r3) {
            Class<? super T> r1 = r3.getRawType();
            if (r1.getName().startsWith("java.time.") == true) goto L6;
            return null;
        L6:
            if (r1 != Duration.class) goto L10;
            return JavaTimeTypeAdapters.access$100();
        L10:
            if (r1 != Instant.class) goto L14;
            return JavaTimeTypeAdapters.access$200();
        L14:
            if (r1 != LocalDate.class) goto L18;
            return JavaTimeTypeAdapters.access$300();
        L18:
            if (r1 != LocalTime.class) goto L22;
            return (TypeAdapter<T>) JavaTimeTypeAdapters.LOCAL_TIME;
        L22:
            if (r1 != LocalDateTime.class) goto L26;
            return JavaTimeTypeAdapters.access$400(r2);
        L26:
            if (r1 != MonthDay.class) goto L30;
            return JavaTimeTypeAdapters.access$500();
        L30:
            if (r1 != OffsetDateTime.class) goto L34;
            return JavaTimeTypeAdapters.access$600(r2);
        L34:
            if (r1 != OffsetTime.class) goto L38;
            return JavaTimeTypeAdapters.access$700(r2);
        L38:
            if (r1 != Period.class) goto L42;
            return JavaTimeTypeAdapters.access$800();
        L42:
            if (r1 != Year.class) goto L46;
            return JavaTimeTypeAdapters.access$900();
        L46:
            if (r1 != YearMonth.class) goto L50;
            return JavaTimeTypeAdapters.access$1000();
        L50:
            if (r1 == ZoneId.class) goto L60;
            if (r1 == ZoneOffset.class) goto L60;
            if (r1 == ZonedDateTime.class) goto L57;
            return null;
        L57:
            return JavaTimeTypeAdapters.access$1200(r2);
        L60:
            return JavaTimeTypeAdapters.access$1100();
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$2, reason: invalid class name */
    public class AnonymousClass2 extends TypeAdapters.IntegerFieldsTypeAdapter<Instant> {
        public AnonymousClass2(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Instant create(long[] r3) {
            return Instant.ofEpochSecond(r3[0], r3[1]);
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ Instant create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(Instant r5) {
            return new long[]{r5.getEpochSecond(), r5.getNano()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(Instant r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$3, reason: invalid class name */
    public class AnonymousClass3 extends TypeAdapters.IntegerFieldsTypeAdapter<LocalDate> {
        public AnonymousClass3(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public LocalDate create(long[] r4) {
            return LocalDate.of(Math.toIntExact(r4[0]), Math.toIntExact(r4[1]), Math.toIntExact(r4[2]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ LocalDate create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(LocalDate r7) {
            return new long[]{r7.getYear(), r7.getMonthValue(), r7.getDayOfMonth()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(LocalDate r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$4, reason: invalid class name */
    public class AnonymousClass4 extends TypeAdapters.IntegerFieldsTypeAdapter<LocalTime> {
        public AnonymousClass4(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public LocalTime create(long[] r5) {
            return LocalTime.of(Math.toIntExact(r5[0]), Math.toIntExact(r5[1]), Math.toIntExact(r5[2]), Math.toIntExact(r5[3]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ LocalTime create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(LocalTime r9) {
            return new long[]{r9.getHour(), r9.getMinute(), r9.getSecond(), r9.getNano()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(LocalTime r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$5, reason: invalid class name */
    public class AnonymousClass5 extends TypeAdapter<LocalDateTime> {
        final /* synthetic */ TypeAdapter val$localDateAdapter;
        final /* synthetic */ TypeAdapter val$localTimeAdapter;

        public AnonymousClass5(TypeAdapter r1, TypeAdapter r2) {
            this.val$localDateAdapter = r1;
            this.val$localTimeAdapter = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public LocalDateTime read(JsonReader r7) {
            r7.beginObject();
            LocalDate r0 = null;
            LocalTime r1 = null;
        L4:
            if (r7.peek() == JsonToken.END_OBJECT) goto L12;
            String r2 = r7.nextName();
            r2.getClass();
            if (r2.equals("date") == false) goto L8;
            r0 = (LocalDate) this.val$localDateAdapter.read(r7);
            goto L4
        L8:
            if (r2.equals("time") == false) goto L9;
            r1 = (LocalTime) this.val$localTimeAdapter.read(r7);
            goto L4
        L9:
            r7.skipValue();
            goto L4
        L12:
            r7.endObject();
            return LocalDateTime.of((LocalDate) JavaTimeTypeAdapters.access$000(r0, "date", r7), (LocalTime) JavaTimeTypeAdapters.access$000(r1, "time", r7));
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ LocalDateTime read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, LocalDateTime r4) {
            r3.beginObject();
            r3.name("date");
            this.val$localDateAdapter.write(r3, r4.toLocalDate());
            r3.name("time");
            this.val$localTimeAdapter.write(r3, r4.toLocalTime());
            r3.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, LocalDateTime r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$6, reason: invalid class name */
    public class AnonymousClass6 extends TypeAdapters.IntegerFieldsTypeAdapter<MonthDay> {
        public AnonymousClass6(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public MonthDay create(long[] r3) {
            return MonthDay.of(Math.toIntExact(r3[0]), Math.toIntExact(r3[1]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ MonthDay create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(MonthDay r5) {
            return new long[]{r5.getMonthValue(), r5.getDayOfMonth()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(MonthDay r1) {
            return integerValues2(r1);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$7, reason: invalid class name */
    public class AnonymousClass7 extends TypeAdapter<OffsetDateTime> {
        final /* synthetic */ TypeAdapter val$localDateTimeAdapter;
        final /* synthetic */ TypeAdapter val$zoneOffsetAdapter;

        public AnonymousClass7(TypeAdapter r1, TypeAdapter r2) {
            this.val$localDateTimeAdapter = r1;
            this.val$zoneOffsetAdapter = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public OffsetDateTime read(JsonReader r7) {
            r7.beginObject();
            LocalDateTime r0 = null;
            ZoneOffset r1 = null;
        L4:
            if (r7.peek() == JsonToken.END_OBJECT) goto L12;
            String r2 = r7.nextName();
            r2.getClass();
            if (r2.equals("offset") == false) goto L8;
            r1 = (ZoneOffset) this.val$zoneOffsetAdapter.read(r7);
            goto L4
        L8:
            if (r2.equals("dateTime") == false) goto L9;
            r0 = (LocalDateTime) this.val$localDateTimeAdapter.read(r7);
            goto L4
        L9:
            r7.skipValue();
            goto L4
        L12:
            r7.endObject();
            return OffsetDateTime.of((LocalDateTime) JavaTimeTypeAdapters.access$000(r0, "dateTime", r7), (ZoneOffset) JavaTimeTypeAdapters.access$000(r1, "offset", r7));
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ OffsetDateTime read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, OffsetDateTime r4) {
            r3.beginObject();
            r3.name("dateTime");
            this.val$localDateTimeAdapter.write(r3, r4.toLocalDateTime());
            r3.name("offset");
            this.val$zoneOffsetAdapter.write(r3, r4.getOffset());
            r3.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, OffsetDateTime r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$8, reason: invalid class name */
    public class AnonymousClass8 extends TypeAdapter<OffsetTime> {
        final /* synthetic */ TypeAdapter val$localTimeAdapter;
        final /* synthetic */ TypeAdapter val$zoneOffsetAdapter;

        public AnonymousClass8(TypeAdapter r1, TypeAdapter r2) {
            this.val$localTimeAdapter = r1;
            this.val$zoneOffsetAdapter = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public OffsetTime read(JsonReader r7) {
            r7.beginObject();
            LocalTime r0 = null;
            ZoneOffset r1 = null;
        L4:
            if (r7.peek() == JsonToken.END_OBJECT) goto L12;
            String r2 = r7.nextName();
            r2.getClass();
            if (r2.equals("offset") == false) goto L8;
            r1 = (ZoneOffset) this.val$zoneOffsetAdapter.read(r7);
            goto L4
        L8:
            if (r2.equals("time") == false) goto L9;
            r0 = (LocalTime) this.val$localTimeAdapter.read(r7);
            goto L4
        L9:
            r7.skipValue();
            goto L4
        L12:
            r7.endObject();
            return OffsetTime.of((LocalTime) JavaTimeTypeAdapters.access$000(r0, "time", r7), (ZoneOffset) JavaTimeTypeAdapters.access$000(r1, "offset", r7));
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ OffsetTime read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, OffsetTime r4) {
            r3.beginObject();
            r3.name("time");
            this.val$localTimeAdapter.write(r3, r4.toLocalTime());
            r3.name("offset");
            this.val$zoneOffsetAdapter.write(r3, r4.getOffset());
            r3.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, OffsetTime r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.JavaTimeTypeAdapters$9, reason: invalid class name */
    public class AnonymousClass9 extends TypeAdapters.IntegerFieldsTypeAdapter<Period> {
        public AnonymousClass9(String... r1) {
            super(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Period create(long[] r4) {
            return Period.of(Math.toIntExact(r4[0]), Math.toIntExact(r4[1]), Math.toIntExact(r4[2]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ Period create(long[] r1) {
            return create(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(Period r7) {
            return new long[]{r7.getYears(), r7.getMonths(), r7.getDays()};
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(Period r1) {
            return integerValues2(r1);
        }
    }

    static {
        DURATION = new AnonymousClass1(new String[]{"seconds", "nanos"});
        INSTANT = new AnonymousClass2(new String[]{"seconds", "nanos"});
        LOCAL_DATE = new AnonymousClass3(new String[]{"year", "month", "day"});
        LOCAL_TIME = new AnonymousClass4(new String[]{"hour", "minute", "second", "nano"});
        MONTH_DAY = new AnonymousClass6(new String[]{"month", "day"});
        PERIOD = new AnonymousClass9(new String[]{"years", "months", "days"});
        YEAR = new AnonymousClass10(new String[]{"year"});
        YEAR_MONTH = new AnonymousClass11(new String[]{"year", "month"});
        ZONE_ID = new AnonymousClass12().nullSafe();
        JAVA_TIME_FACTORY = new AnonymousClass14();
    }

    public JavaTimeTypeAdapters() {
    }

    public static /* synthetic */ Object access$000(Object r0, String r1, JsonReader r2) {
        return requireNonNullField(r0, r1, r2);
    }

    public static /* synthetic */ TypeAdapter access$100() {
        return DURATION;
    }

    public static /* synthetic */ TypeAdapter access$1000() {
        return YEAR_MONTH;
    }

    public static /* synthetic */ TypeAdapter access$1100() {
        return ZONE_ID;
    }

    public static /* synthetic */ TypeAdapter access$1200(Gson r0) {
        return zonedDateTime(r0);
    }

    public static /* synthetic */ TypeAdapter access$200() {
        return INSTANT;
    }

    public static /* synthetic */ TypeAdapter access$300() {
        return LOCAL_DATE;
    }

    public static /* synthetic */ TypeAdapter access$400(Gson r0) {
        return localDateTime(r0);
    }

    public static /* synthetic */ TypeAdapter access$500() {
        return MONTH_DAY;
    }

    public static /* synthetic */ TypeAdapter access$600(Gson r0) {
        return offsetDateTime(r0);
    }

    public static /* synthetic */ TypeAdapter access$700(Gson r0) {
        return offsetTime(r0);
    }

    public static /* synthetic */ TypeAdapter access$800() {
        return PERIOD;
    }

    public static /* synthetic */ TypeAdapter access$900() {
        return YEAR;
    }

    private static TypeAdapter<LocalDateTime> localDateTime(Gson r2) {
        return new AnonymousClass5(r2.getAdapter(LocalDate.class), r2.getAdapter(LocalTime.class)).nullSafe();
    }

    private static TypeAdapter<OffsetDateTime> offsetDateTime(Gson r2) {
        return new AnonymousClass7(localDateTime(r2), r2.getAdapter(ZoneOffset.class)).nullSafe();
    }

    private static TypeAdapter<OffsetTime> offsetTime(Gson r2) {
        return new AnonymousClass8(r2.getAdapter(LocalTime.class), r2.getAdapter(ZoneOffset.class)).nullSafe();
    }

    private static <T> T requireNonNullField(T r2, String r3, JsonReader r4) {
        if (r2 == null) goto L5;
        return r2;
    L5:
        throw new JsonSyntaxException("Missing " + r3 + " field; at path " + r4.getPreviousPath());
    }

    private static TypeAdapter<ZonedDateTime> zonedDateTime(Gson r3) {
        return new AnonymousClass13(localDateTime(r3), r3.getAdapter(ZoneOffset.class), r3.getAdapter(ZoneId.class)).nullSafe();
    }

    @Override // com.google.gson.internal.bind.TypeAdapters.FactorySupplier
    public TypeAdapterFactory get() {
        return JAVA_TIME_FACTORY;
    }
}
