package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Base64;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.inject.Provider;

/* loaded from: classes.dex */
public class SQLiteEventStore implements EventStore, SynchronizationGuard, ClientHealthMetricsStore {
    private static final Encoding PROTOBUF_ENCODING = null;
    private final EventStoreConfig config;
    private final Clock monotonicClock;
    private final Provider<String> packageName;
    private final SchemaManager schemaManager;
    private final Clock wallClock;

    /* renamed from: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public interface Function<T, U> {
        U apply(T r1);
    }

    /* loaded from: classes4.dex */
    public static class Metadata {
        final String key;
        final String value;

        private Metadata(String r1, String r2) {
            this.key = r1;
            this.value = r2;
        }

        public /* synthetic */ Metadata(String r1, String r2, AnonymousClass1 r3) {
            this(r1, r2);
        }
    }

    public interface Producer<T> {
        T produce();
    }

    /* renamed from: $r8$lambda$-2sc8nDh-80TMJWoAz3fuM4SGoA, reason: not valid java name */
    public static /* synthetic */ TimeWindow m1809$r8$lambda$2sc8nDh80TMJWoAz3fuM4SGoA(long r2, Cursor r4) {
        r4.moveToNext();
        long r0 = r4.getLong(0);
        return TimeWindow.newBuilder().setStartMs(r0).setEndMs(r2).build();
    }

    /* renamed from: $r8$lambda$-c5yRpmc2Pusxok-ESpsItypEVU, reason: not valid java name */
    public static /* synthetic */ List m1810$r8$lambda$c5yRpmc2PusxokESpsItypEVU(SQLiteEventStore r7, TransportContext r8, SQLiteDatabase r9) {
        List<PersistedEvent> r0 = r7.loadEvents(r9, r8, r7.config.getLoadBatchSize());
        Priority[] r1 = Priority.values();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L13;
        Priority r4 = r1[r3];
        if (r4 == r8.getPriority()) goto L11;
        int r5 = r7.config.getLoadBatchSize() - r0.size();
        if (r5 <= 0) goto L13;
        r0.addAll(r7.loadEvents(r9, r8.withPriority(r4), r5));
    L11:
        r3 = r3 + 1;
    L13:
        return r7.join(r0, r7.loadMetadata(r9, r0));
    }

    public static /* synthetic */ List $r8$lambda$5Uvct5cGMjQeavljSmguhr62CK8(Cursor r3) {
        ArrayList r0 = new ArrayList();
    L4:
        if (r3.moveToNext() == false) goto L6;
        r0.add(TransportContext.builder().setBackendName(r3.getString(1)).setPriority(PriorityMapping.valueOf(r3.getInt(2))).setExtras(maybeBase64Decode(r3.getString(3))).build());
        goto L4
    L6:
        return r0;
    }

    public static /* synthetic */ Object $r8$lambda$8LOwVn7k0hj_OWr4Ylfbk4bxfj8(SQLiteEventStore r4, Cursor r5) {
        r4.getClass();
    L4:
        if (r5.moveToNext() == false) goto L6;
        r4.recordLogEventDropped(r5.getInt(0), LogEventDropped.Reason.MAX_RETRIES_REACHED, r5.getString(1));
        goto L4
    L6:
        return null;
    }

    public static /* synthetic */ SQLiteDatabase $r8$lambda$DYhwaGroa6Bx2rA3UTBVIryQQQM(Throwable r2) {
        throw new SynchronizationException("Timed out while trying to open db.", r2);
    }

    public static /* synthetic */ Object $r8$lambda$FPCWqOWJeVGVZGsEViC6KdIzvC4(SQLiteEventStore r4, Cursor r5) {
        r4.getClass();
    L4:
        if (r5.moveToNext() == false) goto L6;
        r4.recordLogEventDropped(r5.getInt(0), LogEventDropped.Reason.MESSAGE_TOO_OLD, r5.getString(1));
        goto L4
    L6:
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$FWBHKI0M7buhnYqPq5piDyOvkh8(SQLiteEventStore r1, String r2, String r3, SQLiteDatabase r4) {
        r1.getClass();
        r4.compileStatement(r2).execute();
        tryWithCursor(r4.rawQuery(r3, null), new SQLiteEventStore$$ExternalSyntheticLambda19(r1));
        r4.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
        return null;
    }

    /* renamed from: $r8$lambda$G8uJ-S54jP30MGd1qBdzDbSf55k, reason: not valid java name */
    public static /* synthetic */ Boolean m1811$r8$lambda$G8uJS54jP30MGd1qBdzDbSf55k(SQLiteEventStore r0, TransportContext r1, SQLiteDatabase r2) {
        Long r12 = r0.getTransportContextId(r2, r1);
        if (r12 != null) goto L7;
        return Boolean.FALSE;
    L7:
        return (Boolean) tryWithCursor(r0.getDb().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{r12.toString()}), new SQLiteEventStore$$ExternalSyntheticLambda18());
    }

    public static /* synthetic */ Object $r8$lambda$GHMqzva9B_XnmPprSGyr5JoZ7b8(long r3, TransportContext r5, SQLiteDatabase r6) {
        ContentValues r0 = new ContentValues();
        r0.put("next_request_ms", Long.valueOf(r3));
        if (r6.update("transport_contexts", r0, "backend_name = ? and priority = ?", new String[]{r5.getBackendName(), String.valueOf(PriorityMapping.toInt(r5.getPriority()))}) >= 1) goto L5;
        r0.put("backend_name", r5.getBackendName());
        r0.put(PluginsConstants.MenuItemProperties.PRIORITY, Integer.valueOf(PriorityMapping.toInt(r5.getPriority())));
        r6.insert("transport_contexts", null, r0);
    L5:
        return null;
    }

    /* renamed from: $r8$lambda$MhWEBJkj-Z48pXSpbh-iwCbV5kA, reason: not valid java name */
    public static /* synthetic */ byte[] m1812$r8$lambda$MhWEBJkjZ48pXSpbhiwCbV5kA(Cursor r6) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r6.moveToNext() == false) goto L6;
        byte[] r3 = r6.getBlob(0);
        r0.add(r3);
        r2 = r2 + r3.length;
        goto L4
    L6:
        byte[] r62 = new byte[r2];
        int r22 = 0;
        int r32 = 0;
    L8:
        if (r22 >= r0.size()) goto L10;
        byte[] r4 = (byte[]) r0.get(r22);
        System.arraycopy(r4, 0, r62, r32, r4.length);
        r32 = r32 + r4.length;
        r22 = r22 + 1;
        goto L8
    L10:
        return r62;
    }

    public static /* synthetic */ List $r8$lambda$OYeq4gZ6BLfEujKfNIuFZurIZak(SQLiteDatabase r2) {
        return (List) tryWithCursor(r2.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda8());
    }

    /* renamed from: $r8$lambda$RbOgr-U7H41WTgNTtm08JTmKZL8, reason: not valid java name */
    public static /* synthetic */ ClientMetrics m1813$r8$lambda$RbOgrU7H41WTgNTtm08JTmKZL8(SQLiteEventStore r5, Map r6, ClientMetrics.Builder r7, Cursor r8) {
        r5.getClass();
    L4:
        if (r8.moveToNext() == false) goto L9;
        String r0 = r8.getString(0);
        LogEventDropped.Reason r1 = r5.convertToReason(r8.getInt(1));
        long r2 = r8.getLong(2);
        if (r6.containsKey(r0) == true) goto L8;
        r6.put(r0, new ArrayList());
    L8:
        ((List) r6.get(r0)).add(LogEventDropped.newBuilder().setReason(r1).setEventsDroppedCount(r2).build());
        goto L4
    L9:
        r5.populateLogSourcesMetrics(r7, r6);
        return r7.setWindow(r5.getTimeWindow()).setGlobalMetrics(r5.getGlobalMetrics()).setAppNamespace(r5.packageName.get()).build();
    }

    public static /* synthetic */ Boolean $r8$lambda$TKobF03u2pdjGMo2w1gTJSqaKCE(Cursor r0) {
        if (r0.getCount() <= 0) goto L5;
        boolean r02 = true;
    L7:
        return Boolean.valueOf(r02);
    L5:
        r02 = false;
        goto L7
    }

    /* renamed from: $r8$lambda$Yygha28Nx4WElzmz7AGC_M3n-iQ, reason: not valid java name */
    public static /* synthetic */ Long m1814$r8$lambda$Yygha28Nx4WElzmz7AGC_M3niQ(SQLiteEventStore r10, EventInternal r11, TransportContext r12, SQLiteDatabase r13) {
        if (r10.isStorageAtLimit() == false) goto L6;
        r10.recordLogEventDropped(1, LogEventDropped.Reason.CACHE_FULL, r11.getTransportName());
        return -1L;
    L6:
        long r0 = r10.ensureTransportContext(r13, r12);
        int r102 = r10.config.getMaxBlobByteSizePerRow();
        byte[] r122 = r11.getEncodedPayload().getBytes();
        int r4 = 1;
        if (r122.length > r102) goto L9;
        boolean r2 = true;
    L10:
        ContentValues r5 = new ContentValues();
        r5.put("context_id", Long.valueOf(r0));
        r5.put("transport_name", r11.getTransportName());
        r5.put("timestamp_ms", Long.valueOf(r11.getEventMillis()));
        r5.put("uptime_ms", Long.valueOf(r11.getUptimeMillis()));
        r5.put("payload_encoding", r11.getEncodedPayload().getEncoding().getName());
        r5.put("code", r11.getCode());
        r5.put("num_attempts", 0);
        r5.put("inline", Boolean.valueOf(r2));
        if (r2 == false) goto L13;
        byte[] r02 = r122;
    L14:
        r5.put("payload", r02);
        r5.put("product_id", r11.getProductId());
        r5.put("pseudonymous_id", r11.getPseudonymousId());
        r5.put("experiment_ids_clear_blob", r11.getExperimentIdsClear());
        r5.put("experiment_ids_encrypted_blob", r11.getExperimentIdsEncrypted());
        long r52 = r13.insert("events", null, r5);
        if (r2 == true) goto L19;
        int r22 = (int) Math.ceil(r122.length / r102);
    L17:
        if (r4 > r22) goto L19;
        byte[] r3 = Arrays.copyOfRange(r122, (r4 - 1) * r102, Math.min(r4 * r102, r122.length));
        ContentValues r7 = new ContentValues();
        r7.put("event_id", Long.valueOf(r52));
        r7.put("sequence_num", Integer.valueOf(r4));
        r7.put("bytes", r3);
        r13.insert("event_payloads", null, r7);
        r4 = r4 + 1;
    L19:
        Iterator<Map.Entry<String, String>> r103 = r11.getMetadata().entrySet().iterator();
    L21:
        if (r103.hasNext() == false) goto L24;
        Map.Entry<String, String> r112 = r103.next();
        ContentValues r123 = new ContentValues();
        r123.put("event_id", Long.valueOf(r52));
        r123.put("name", r112.getKey());
        r123.put("value", r112.getValue());
        r13.insert("event_metadata", null, r123);
        goto L21
    L24:
        return Long.valueOf(r52);
    L13:
        r02 = new byte[0];
        goto L14
    L9:
        r2 = false;
        goto L10
    }

    /* renamed from: $r8$lambda$bBQL4XWV8rqSJshoDiUR95y-_jM, reason: not valid java name */
    public static /* synthetic */ Object m1815$r8$lambda$bBQL4XWV8rqSJshoDiUR95y_jM(SQLiteDatabase r0) {
        r0.beginTransaction();
        return null;
    }

    public static /* synthetic */ Long $r8$lambda$cAnhwi1GNvuKjmAGBcKUf1ZtJ8M(Cursor r2) {
        if (r2.moveToNext() == true) goto L7;
        return null;
    L7:
        return Long.valueOf(r2.getLong(0));
    }

    public static /* synthetic */ Object $r8$lambda$cMN1kDUWPXuzjr1P3QAWoeACqG8(Throwable r2) {
        throw new SynchronizationException("Timed out while trying to acquire the lock.", r2);
    }

    /* renamed from: $r8$lambda$m2Cid-bNtM3iXXm7FvzU6_pgJWc, reason: not valid java name */
    public static /* synthetic */ Object m1816$r8$lambda$m2CidbNtM3iXXm7FvzU6_pgJWc(String r3, LogEventDropped.Reason r4, long r5, SQLiteDatabase r7) {
        if (((Boolean) tryWithCursor(r7.rawQuery("SELECT 1 FROM log_event_dropped WHERE log_source = ? AND reason = ?", new String[]{r3, Integer.toString(r4.getNumber())}), new SQLiteEventStore$$ExternalSyntheticLambda21())).booleanValue() == true) goto L6;
        ContentValues r0 = new ContentValues();
        r0.put("log_source", r3);
        r0.put("reason", Integer.valueOf(r4.getNumber()));
        r0.put("events_dropped_count", Long.valueOf(r5));
        r7.insert("log_event_dropped", null, r0);
        return null;
    L6:
        r7.execSQL("UPDATE log_event_dropped SET events_dropped_count = events_dropped_count + " + r5 + " WHERE log_source = ? AND reason = ?", new String[]{r3, Integer.toString(r4.getNumber())});
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$mObue6vDcpMgJg6tRUgbT0MgVDo(Map r5, Cursor r6) {
    L3:
        if (r6.moveToNext() == false) goto L8;
        long r2 = r6.getLong(0);
        Set r0 = (Set) r5.get(Long.valueOf(r2));
        if (r0 != null) goto L7;
        r0 = new HashSet();
        r5.put(Long.valueOf(r2), r0);
    L7:
        r0.add(new Metadata(r6.getString(1), r6.getString(2), null));
        goto L3
    L8:
        return null;
    }

    public static /* synthetic */ Integer $r8$lambda$oWyA6AJGDizu9ZmA0KcBicBGgCY(SQLiteEventStore r1, long r2, SQLiteDatabase r4) {
        r1.getClass();
        String[] r22 = {String.valueOf(r2)};
        tryWithCursor(r4.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE timestamp_ms < ? GROUP BY transport_name", r22), new SQLiteEventStore$$ExternalSyntheticLambda16(r1));
        return Integer.valueOf(r4.delete("events", "timestamp_ms < ?", r22));
    }

    public static /* synthetic */ Object $r8$lambda$rhfHXis96N9wATghbYmJ6PQO9G0(SQLiteEventStore r6, List r7, TransportContext r8, Cursor r9) {
        r6.getClass();
    L4:
        if (r9.moveToNext() == false) goto L28;
        boolean r0 = false;
        long r1 = r9.getLong(0);
        if (r9.getInt(7) == 0) goto L8;
        r0 = true;
    L8:
        EventInternal.Builder r3 = EventInternal.builder().setTransportName(r9.getString(1)).setEventMillis(r9.getLong(2)).setUptimeMillis(r9.getLong(3));
        if (r0 == false) goto L11;
        r3.setEncodedPayload(new EncodedPayload(toEncoding(r9.getString(4)), r9.getBlob(5)));
    L13:
        if (r9.isNull(6) == true) goto L16;
        r3.setCode(Integer.valueOf(r9.getInt(6)));
    L16:
        if (r9.isNull(8) == true) goto L19;
        r3.setProductId(Integer.valueOf(r9.getInt(8)));
    L19:
        if (r9.isNull(9) == true) goto L22;
        r3.setPseudonymousId(r9.getString(9));
    L22:
        if (r9.isNull(10) == true) goto L25;
        r3.setExperimentIdsClear(r9.getBlob(10));
    L25:
        if (r9.isNull(11) == true) goto L27;
        r3.setExperimentIdsEncrypted(r9.getBlob(11));
    L27:
        r7.add(PersistedEvent.create(r1, r8, r3.build()));
        goto L4
    L11:
        r3.setEncodedPayload(new EncodedPayload(toEncoding(r9.getString(4)), r6.readPayload(r1)));
        goto L13
    L28:
        return null;
    }

    /* renamed from: $r8$lambda$tjc_qlQgTB-9tdANbPFpY4kEz4U, reason: not valid java name */
    public static /* synthetic */ Object m1817$r8$lambda$tjc_qlQgTB9tdANbPFpY4kEz4U(SQLiteEventStore r3, SQLiteDatabase r4) {
        r3.getClass();
        r4.compileStatement("DELETE FROM log_event_dropped").execute();
        r4.compileStatement("UPDATE global_log_event_state SET last_metrics_upload_ms=" + r3.wallClock.getTime()).execute();
        return null;
    }

    public static /* synthetic */ ClientMetrics $r8$lambda$wkPqtdo8abVVVntIT0LL_IpuNhI(SQLiteEventStore r1, String r2, Map r3, ClientMetrics.Builder r4, SQLiteDatabase r5) {
        r1.getClass();
        return (ClientMetrics) tryWithCursor(r5.rawQuery(r2, new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda23(r1, r3, r4));
    }

    public static /* synthetic */ Long $r8$lambda$zCKpHwX4pX2SAl7zbN4WB6el84g(Cursor r2) {
        if (r2.moveToNext() == false) goto L7;
        return Long.valueOf(r2.getLong(0));
    L7:
        return 0L;
    }

    public static /* synthetic */ TimeWindow $r8$lambda$zOq4Ptcu6jxQh2yulOulwMnkbKw(long r2, SQLiteDatabase r4) {
        return (TimeWindow) tryWithCursor(r4.rawQuery("SELECT last_metrics_upload_ms FROM global_log_event_state LIMIT 1", new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda26(r2));
    }

    static {
        PROTOBUF_ENCODING = Encoding.of("proto");
    }

    public SQLiteEventStore(Clock r1, Clock r2, EventStoreConfig r3, SchemaManager r4, Provider<String> r5) {
        this.schemaManager = r4;
        this.wallClock = r1;
        this.monotonicClock = r2;
        this.config = r3;
        this.packageName = r5;
    }

    private LogEventDropped.Reason convertToReason(int r3) {
        LogEventDropped.Reason r2 = LogEventDropped.Reason.REASON_UNKNOWN;
        if (r3 != r2.getNumber()) goto L5;
        return r2;
    L5:
        LogEventDropped.Reason r0 = LogEventDropped.Reason.MESSAGE_TOO_OLD;
        if (r3 != r0.getNumber()) goto L8;
        return r0;
    L8:
        LogEventDropped.Reason r02 = LogEventDropped.Reason.CACHE_FULL;
        if (r3 != r02.getNumber()) goto L11;
        return r02;
    L11:
        LogEventDropped.Reason r03 = LogEventDropped.Reason.PAYLOAD_TOO_BIG;
        if (r3 != r03.getNumber()) goto L14;
        return r03;
    L14:
        LogEventDropped.Reason r04 = LogEventDropped.Reason.MAX_RETRIES_REACHED;
        if (r3 != r04.getNumber()) goto L17;
        return r04;
    L17:
        LogEventDropped.Reason r05 = LogEventDropped.Reason.INVALID_PAYLOD;
        if (r3 != r05.getNumber()) goto L20;
        return r05;
    L20:
        LogEventDropped.Reason r06 = LogEventDropped.Reason.SERVER_ERROR;
        if (r3 != r06.getNumber()) goto L23;
        return r06;
    L23:
        Logging.d("SQLiteEventStore", "%n is not valid. No matched LogEventDropped-Reason found. Treated it as REASON_UNKNOWN", Integer.valueOf(r3));
        return r2;
    }

    private void ensureBeginTransaction(SQLiteDatabase r2) {
        retryIfDbLocked(new SQLiteEventStore$$ExternalSyntheticLambda0(r2), new SQLiteEventStore$$ExternalSyntheticLambda1());
    }

    private long ensureTransportContext(SQLiteDatabase r4, TransportContext r5) {
        Long r3 = getTransportContextId(r4, r5);
        if (r3 != null) goto L5;
        ContentValues r32 = new ContentValues();
        r32.put("backend_name", r5.getBackendName());
        r32.put(PluginsConstants.MenuItemProperties.PRIORITY, Integer.valueOf(PriorityMapping.toInt(r5.getPriority())));
        r32.put("next_request_ms", 0);
        if (r5.getExtras() == null) goto L10;
        r32.put("extras", Base64.encodeToString(r5.getExtras(), 0));
    L10:
        return r4.insert("transport_contexts", null, r32);
    L5:
        return r3.longValue();
    }

    private GlobalMetrics getGlobalMetrics() {
        return GlobalMetrics.newBuilder().setStorageMetrics(StorageMetrics.newBuilder().setCurrentCacheSizeBytes(getByteSize()).setMaxCacheSizeBytes(EventStoreConfig.DEFAULT.getMaxStorageSizeInBytes()).build()).build();
    }

    private long getPageCount() {
        return getDb().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    private long getPageSize() {
        return getDb().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    private TimeWindow getTimeWindow() {
        return (TimeWindow) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda25(this.wallClock.getTime()));
    }

    private Long getTransportContextId(SQLiteDatabase r12, TransportContext r13) {
        StringBuilder r11 = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList r0 = new ArrayList(Arrays.asList(new String[]{r13.getBackendName(), String.valueOf(PriorityMapping.toInt(r13.getPriority()))}));
        if (r13.getExtras() == null) goto L5;
        r11.append(" and extras = ?");
        r0.add(Base64.encodeToString(r13.getExtras(), 0));
    L7:
        return (Long) tryWithCursor(r12.query("transport_contexts", new String[]{"_id"}, r11.toString(), (String[]) r0.toArray(new String[0]), null, null, null), new SQLiteEventStore$$ExternalSyntheticLambda11());
    L5:
        r11.append(" and extras is null");
        goto L7
    }

    private boolean isStorageAtLimit() {
        if ((getPageCount() * getPageSize()) < this.config.getMaxStorageSizeInBytes()) goto L6;
        return true;
    L6:
        return false;
    }

    private List<PersistedEvent> join(List<PersistedEvent> r6, Map<Long, Set<Metadata>> r7) {
        ListIterator<PersistedEvent> r5 = r6.listIterator();
    L4:
        if (r5.hasNext() == false) goto L13;
        PersistedEvent r0 = r5.next();
        if (r7.containsKey(Long.valueOf(r0.getId())) == false) goto L4;
        EventInternal.Builder r1 = r0.getEvent().toBuilder();
        Iterator<Metadata> r2 = r7.get(Long.valueOf(r0.getId())).iterator();
    L10:
        if (r2.hasNext() == false) goto L12;
        Metadata r3 = r2.next();
        r1.addMetadata(r3.key, r3.value);
        goto L10
    L12:
        r5.set(PersistedEvent.create(r0.getId(), r0.getTransportContext(), r1.build()));
        goto L4
    L13:
        return r6;
    }

    private List<PersistedEvent> loadEvents(SQLiteDatabase r24, TransportContext r25, int r26) {
        ArrayList r0 = new ArrayList();
        Long r1 = getTransportContextId(r24, r25);
        if (r1 != null) goto L5;
        return r0;
    L5:
        tryWithCursor(r24.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline", "product_id", "pseudonymous_id", "experiment_ids_clear_blob", "experiment_ids_encrypted_blob"}, "context_id = ?", new String[]{r1.toString()}, null, null, null, String.valueOf(r26)), new SQLiteEventStore$$ExternalSyntheticLambda20(this, r0, r25));
        return r0;
    }

    private Map<Long, Set<Metadata>> loadMetadata(SQLiteDatabase r12, List<PersistedEvent> r13) {
        HashMap r11 = new HashMap();
        StringBuilder r0 = new StringBuilder("event_id IN (");
        int r1 = 0;
    L4:
        if (r1 >= r13.size()) goto L9;
        r0.append(r13.get(r1).getId());
        if (r1 >= (r13.size() - 1)) goto L8;
        r0.append(',');
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        r0.append(')');
        tryWithCursor(r12.query("event_metadata", new String[]{"event_id", "name", "value"}, r0.toString(), null, null, null, null), new SQLiteEventStore$$ExternalSyntheticLambda22(r11));
        return r11;
    }

    private static byte[] maybeBase64Decode(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return Base64.decode(r1, 0);
    }

    private void populateLogSourcesMetrics(ClientMetrics.Builder r3, Map<String, List<LogEventDropped>> r4) {
        Iterator<Map.Entry<String, List<LogEventDropped>>> r2 = r4.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        Map.Entry<String, List<LogEventDropped>> r42 = r2.next();
        r3.addLogSourceMetrics(LogSourceMetrics.newBuilder().setLogSource(r42.getKey()).setLogEventDroppedList(r42.getValue()).build());
        goto L4
    }

    private byte[] readPayload(long r9) {
        return (byte[]) tryWithCursor(getDb().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(r9)}, null, null, "sequence_num"), new SQLiteEventStore$$ExternalSyntheticLambda24());
    }

    private <T> T retryIfDbLocked(Producer<T> r8, Function<Throwable, T> r9) {
        long r0 = this.monotonicClock.getTime();
    L11:
        return r8.produce();
    L5:
        e = move-exception;
        if (this.monotonicClock.getTime() >= (this.config.getCriticalSectionEnterTimeoutMs() + r0)) goto L9;
        SystemClock.sleep(50);
        goto L11
    L9:
        return r9.apply(e);
    }

    private static Encoding toEncoding(String r0) {
        if (r0 != null) goto L6;
        return PROTOBUF_ENCODING;
    L6:
        return Encoding.of(r0);
    }

    private static String toIdList(Iterable<PersistedEvent> r3) {
        StringBuilder r0 = new StringBuilder("(");
        Iterator<PersistedEvent> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        r0.append(r32.next().getId());
        if (r32.hasNext() == false) goto L4;
        r0.append(',');
        goto L4
    L8:
        r0.append(')');
        return r0.toString();
    }

    public static <T> T tryWithCursor(Cursor r0, Function<Cursor, T> r1) {
        T r12 = r1.apply(r0);     // Catch: Throwable -> L5
        r0.close();
        return r12;
    L5:
        th = move-exception;
        r0.close();
        throw th;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public int cleanUp() {
        return ((Integer) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda7(this, this.wallClock.getTime() - this.config.getEventCleanUpAge()))).intValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.schemaManager.close();
    }

    public long getByteSize() {
        return getPageCount() * getPageSize();
    }

    public SQLiteDatabase getDb() {
        SchemaManager r0 = this.schemaManager;
        Objects.requireNonNull(r0);
        return (SQLiteDatabase) retryIfDbLocked(new SQLiteEventStore$$ExternalSyntheticLambda2(r0), new SQLiteEventStore$$ExternalSyntheticLambda3());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public long getNextCallTime(TransportContext r2) {
        return ((Long) tryWithCursor(getDb().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{r2.getBackendName(), String.valueOf(PriorityMapping.toInt(r2.getPriority()))}), new SQLiteEventStore$$ExternalSyntheticLambda5())).longValue();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public boolean hasPendingEventsFor(TransportContext r2) {
        return ((Boolean) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda12(this, r2))).booleanValue();
    }

    public <T> T inTransaction(Function<SQLiteDatabase, T> r1) {
        SQLiteDatabase r0 = getDb();
        r0.beginTransaction();
        T r12 = r1.apply(r0);     // Catch: Throwable -> L6
        r0.setTransactionSuccessful();     // Catch: Throwable -> L6
        r0.endTransaction();
        return r12;
    L6:
        th = move-exception;
        r0.endTransaction();
        throw th;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<TransportContext> loadActiveContexts() {
        return (Iterable) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda4());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<PersistedEvent> loadBatch(TransportContext r2) {
        return (Iterable) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda10(this, r2));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public ClientMetrics loadClientMetrics() {
        ClientMetrics.Builder r0 = ClientMetrics.newBuilder();
        return (ClientMetrics) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda17(this, "SELECT log_source, reason, events_dropped_count FROM log_event_dropped", new HashMap(), r0));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public PersistedEvent persist(TransportContext r5, EventInternal r6) {
        Logging.d("SQLiteEventStore", "Storing event with priority=%s, name=%s for destination %s", new Object[]{r5.getPriority(), r6.getTransportName(), r5.getBackendName()});
        long r0 = ((Long) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda6(this, r6, r5))).longValue();
        if (r0 >= 1) goto L7;
        return null;
    L7:
        return PersistedEvent.create(r0, r5, r6);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordFailure(Iterable<PersistedEvent> r3) {
        if (r3.iterator().hasNext() == true) goto L5;
        return;
    L5:
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda9(this, "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + toIdList(r3), "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name"));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public void recordLogEventDropped(long r2, LogEventDropped.Reason r4, String r5) {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda15(r5, r4, r2));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordNextCallTime(TransportContext r2, long r3) {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda13(r3, r2));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordSuccess(Iterable<PersistedEvent> r3) {
        if (r3.iterator().hasNext() == true) goto L5;
        return;
    L5:
        getDb().compileStatement("DELETE FROM events WHERE _id in " + toIdList(r3)).execute();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public void resetClientMetrics() {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda14(this));
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard
    public <T> T runCriticalSection(SynchronizationGuard.CriticalSection<T> r2) {
        SQLiteDatabase r0 = getDb();
        ensureBeginTransaction(r0);
        T r1 = r2.execute();     // Catch: Throwable -> L6
        r0.setTransactionSuccessful();     // Catch: Throwable -> L6
        r0.endTransaction();
        return r1;
    L6:
        th = move-exception;
        r0.endTransaction();
        throw th;
    }
}
