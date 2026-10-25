package com.google.gson.internal.sql;

import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public abstract class SqlTypesSupport {
    public static final DefaultDateTypeAdapter.DateType<? extends Date> DATE_DATE_TYPE = null;
    public static final TypeAdapterFactory DATE_FACTORY = null;
    public static final List<TypeAdapterFactory> SQL_TYPE_FACTORIES = null;
    public static final boolean SUPPORTS_SQL_TYPES = false;
    public static final DefaultDateTypeAdapter.DateType<? extends Date> TIMESTAMP_DATE_TYPE = null;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY = null;
    public static final TypeAdapterFactory TIME_FACTORY = null;

    /* renamed from: com.google.gson.internal.sql.SqlTypesSupport$1, reason: invalid class name */
    public class AnonymousClass1 extends DefaultDateTypeAdapter.DateType<java.sql.Date> {
        public AnonymousClass1(Class r1) {
            super(r1);
        }

        @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
        public java.sql.Date deserialize(Date r3) {
            return new java.sql.Date(r3.getTime());
        }

        @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
        public /* bridge */ /* synthetic */ Date deserialize(Date r1) {
            return deserialize(r1);
        }
    }

    /* renamed from: com.google.gson.internal.sql.SqlTypesSupport$2, reason: invalid class name */
    public class AnonymousClass2 extends DefaultDateTypeAdapter.DateType<Timestamp> {
        public AnonymousClass2(Class r1) {
            super(r1);
        }

        @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
        public Timestamp deserialize(Date r3) {
            return new Timestamp(r3.getTime());
        }

        @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
        public /* bridge */ /* synthetic */ Date deserialize(Date r1) {
            return deserialize(r1);
        }
    }

    static {
        Class.forName("java.sql.Date");     // Catch: ClassNotFoundException -> L5
        boolean r2 = true;
    L6:
        SUPPORTS_SQL_TYPES = r2;
        if (r2 == false) goto L9;
        DATE_DATE_TYPE = new AnonymousClass1(java.sql.Date.class);
        TIMESTAMP_DATE_TYPE = new AnonymousClass2(Timestamp.class);
        TypeAdapterFactory r22 = SqlDateTypeAdapter.FACTORY;
        DATE_FACTORY = r22;
        TypeAdapterFactory r3 = SqlTimeTypeAdapter.FACTORY;
        TIME_FACTORY = r3;
        TypeAdapterFactory r4 = SqlTimestampTypeAdapter.FACTORY;
        TIMESTAMP_FACTORY = r4;
        SQL_TYPE_FACTORIES = Collections.unmodifiableList(Arrays.asList(new TypeAdapterFactory[]{r3, r22, r4}));
        return;
    L9:
        DATE_DATE_TYPE = null;
        TIMESTAMP_DATE_TYPE = null;
        DATE_FACTORY = null;
        TIME_FACTORY = null;
        TIMESTAMP_FACTORY = null;
        SQL_TYPE_FACTORIES = Collections.EMPTY_LIST;
        return;
    L5:
        r2 = false;
        goto L6
    }
}
