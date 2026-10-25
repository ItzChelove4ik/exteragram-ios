package org.telegram.messenger;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Debug;
import android.util.Log;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.time.FastDateFormat;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class FileLog {
    private static volatile FileLog Instance = null;
    public static boolean databaseIsMalformed = false;
    private static long dumpedHeap = 0;
    private static HashSet<String> excludeRequests = null;
    private static ExclusionStrategy exclusionStrategy = null;
    private static Gson gson = null;
    private static boolean gsonDisabled = false;
    private static final String mtproto_tag = "MTProto";
    private static HashSet<String> privateFields = null;
    private static final String tag = "tmessages";
    private File currentFile;
    private FastDateFormat dateFormat;
    private FastDateFormat fileDateFormat;
    private boolean initied;
    private boolean initiing;
    private DispatchQueue logQueue;
    private File networkFile;
    private OutputStreamWriter streamWriter;
    private File tlRequestsFile;
    private OutputStreamWriter tlStreamWriter;
    private File tonlibFile;

    /* renamed from: org.telegram.messenger.FileLog$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements ExclusionStrategy {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipClass(Class<?> r1) {
            if (r1.isInstance(DispatchQueue.class) == false) goto L5;
            return true;
        L5:
            if (r1.isInstance(AnimatedFileDrawable.class) == false) goto L7;
            return true;
        L7:
            if (r1.isInstance(ColorStateList.class) == false) goto L9;
            return true;
        L9:
            if (r1.isInstance(Context.class) == true) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipField(FieldAttributes r2) {
            if (FileLog.m4058$$Nest$sfgetprivateFields().contains(r2.getName()) == false) goto L5;
            return true;
        L5:
            if ("message".equalsIgnoreCase(r2.getName()) == true) goto L7;
            return false;
        L7:
            if (String.class.equals(r2.getDeclaredType()) == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    /* loaded from: classes5.dex */
    public static class ByteArrayHexAdapter extends TypeAdapter<byte[]> {
        public ByteArrayHexAdapter() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ byte[] read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public byte[] read2(JsonReader r7) {
            String r6 = r7.nextString();
            int r72 = r6.length();
            byte[] r0 = new byte[r72 / 2];
            int r1 = 0;
        L3:
            if (r1 >= r72) goto L5;
            r0[r1 / 2] = (byte) ((Character.digit(r6.charAt(r1), 16) << 4) + Character.digit(r6.charAt(r1 + 1), 16));
            r1 = r1 + 2;
            goto L3
        L5:
            return r0;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, byte[] r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r5, byte[] r6) {
            if (r6 != null) goto L5;
            r5.nullValue();
            return;
        L5:
            StringBuilder r4 = new StringBuilder((r6.length * 2) + 2);
            r4.append("0x");
            int r0 = r6.length;
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L8;
            r4.append(String.format("%02x", new Object[]{Integer.valueOf(r6[r1] & UByte.MAX_VALUE)}));
            r1 = r1 + 1;
            goto L6
        L8:
            r5.value(r4.toString());
        }
    }

    /* loaded from: classes5.dex */
    public static class IgnoreSentException extends Exception {
        public IgnoreSentException(String r1) {
            super(r1);
        }
    }

    /* loaded from: classes5.dex */
    public static class TLObjectDeserializer implements JsonSerializer<TLObject> {
        private TLObjectDeserializer() {
        }

        public /* synthetic */ TLObjectDeserializer(FileLogIA r1) {
            this();
        }

        @Override // com.google.gson.JsonSerializer
        public /* bridge */ /* synthetic */ JsonElement serialize(TLObject r1, Type r2, JsonSerializationContext r3) {
            return serialize2(r1, r2, r3);
        }

        /* renamed from: serialize, reason: avoid collision after fix types in other method */
        public JsonElement serialize2(TLObject r7, Type r8, JsonSerializationContext r9) {
            JsonObject r6 = new JsonObject();
            String r82 = r7.getClass().getName();
            if (r82.startsWith("org.telegram.tgnet.") == false) goto L5;
            r82 = r82.substring(19);
        L5:
            r6.addProperty("_", r82);
            Field[] r83 = r7.getClass().getFields();     // Catch: Exception -> L13
            int r0 = r83.length;     // Catch: Exception -> L13
            int r1 = 0;
        L7:
            if (r1 >= r0) goto L34;
            Field r2 = r83[r1];     // Catch: Exception -> L13
            if (FileLog.m4058$$Nest$sfgetprivateFields() != null) goto L11;
        L15:
            r2.setAccessible(true);     // Catch: Exception -> L13
            Object r3 = r2.get(r7);     // Catch: Exception -> L13 IllegalAccessException -> L27
            if (r3 == null) goto L29;
            Class<?> r4 = r3.getClass();     // Catch: Exception -> L13 IllegalAccessException -> L27
            if (r4.isInstance(DispatchQueue.class) == true) goto L32;
            if (r4.isInstance(AnimatedFileDrawable.class) == true) goto L32;
            if (r4.isInstance(ColorStateList.class) == true) goto L32;
            if (r4.isInstance(Context.class) == false) goto L29;
        L32:
            r1 = r1 + 1;
        L29:
            JsonElement r32 = r9.serialize(r3);     // Catch: Exception -> L13 IllegalAccessException -> L27
            r6.add(r2.getName(), r32);     // Catch: Exception -> L13 IllegalAccessException -> L27
            goto L32
        L27:
            e = move-exception;
            e.printStackTrace();     // Catch: Exception -> L13
            goto L32
        L11:
            if (FileLog.m4058$$Nest$sfgetprivateFields().contains(r2.getName()) == false) goto L15;
        L34:
            return r6;
        L13:
            e = move-exception;
            e.printStackTrace();
            goto L34
        }
    }

    public static /* synthetic */ void $r8$lambda$0ztTDayoCBQbqNUyey4KiVh2OWE(String r6, Throwable r7) {
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + r6 + "\n");     // Catch: Exception -> L9
        getInstance().streamWriter.write(r7.toString());     // Catch: Exception -> L9
        StackTraceElement[] r62 = r7.getStackTrace();     // Catch: Exception -> L9
        int r72 = 0;
    L5:
        if (r72 >= r62.length) goto L7;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: \tat " + r62[r72] + "\n");     // Catch: Exception -> L9
        r72 = r72 + 1;     // Catch: Exception -> L9
        goto L5
    L7:
        getInstance().streamWriter.flush();     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        e.printStackTrace();
    }

    /* renamed from: $r8$lambda$Cka7Jn8J8_XF-nXWLJvFPiIg4A4, reason: not valid java name */
    public static /* synthetic */ void m4053$r8$lambda$Cka7Jn8J8_XFnXWLJvFPiIg4A4(Throwable r9) {
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " FATAL/tmessages: " + r9 + "\n");     // Catch: Exception -> L15
        StackTraceElement[] r1 = r9.getStackTrace();     // Catch: Exception -> L15
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r3 >= r1.length) goto L7;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " FATAL/tmessages: \tat " + r1[r3] + "\n");     // Catch: Exception -> L15
        r3 = r3 + 1;     // Catch: Exception -> L15
        goto L5
    L7:
        Throwable r92 = r9.getCause();     // Catch: Exception -> L15
        if (r92 == null) goto L13;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: Caused by " + r92 + "\n");     // Catch: Exception -> L15
        StackTraceElement[] r93 = r92.getStackTrace();     // Catch: Exception -> L15
    L11:
        if (r2 >= r93.length) goto L13;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: \tat " + r93[r2] + "\n");     // Catch: Exception -> L15
        r2 = r2 + 1;     // Catch: Exception -> L15
    L13:
        getInstance().streamWriter.flush();     // Catch: Exception -> L15
    L18:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L25;
        System.exit(2);
        return;
    L25:
        return;
    L15:
        e = move-exception;
        e.printStackTrace();
        goto L18
    }

    public static /* synthetic */ void $r8$lambda$Fp_tthXxEDYM8ruhhhIZvuLpt48(String r5) {
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + r5 + "\n");     // Catch: Exception -> L4
        getInstance().streamWriter.flush();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        e.printStackTrace();
    }

    /* renamed from: $r8$lambda$TbpBHKbdIrkiw9Ph1QNW-6DiAvI, reason: not valid java name */
    public static /* synthetic */ void m4054$r8$lambda$TbpBHKbdIrkiw9Ph1QNW6DiAvI(long r4, long r6, int r8, int r9, long r10, String r12, String r13, TLRPC.TL_error r14) {
        String r42 = "requestMsgId=" + r4 + " requestingTime=" + (System.currentTimeMillis() - r6) + " request_token=" + r8 + " account=" + r9;     // Catch: Exception -> L10
        getInstance().tlStreamWriter.write(getInstance().dateFormat.format(r10) + " " + r42);     // Catch: Exception -> L10
        getInstance().tlStreamWriter.write("\n");     // Catch: Exception -> L10
        getInstance().tlStreamWriter.write(r12);     // Catch: Exception -> L10
        getInstance().tlStreamWriter.write("\n");     // Catch: Exception -> L10
        getInstance().tlStreamWriter.write(r13);     // Catch: Exception -> L10
        getInstance().tlStreamWriter.write("\n\n");     // Catch: Exception -> L10
        getInstance().tlStreamWriter.flush();     // Catch: Exception -> L10
        if (r14 == null) goto L8;
        Log.e(mtproto_tag, r42);     // Catch: Exception -> L10
        Log.e(mtproto_tag, r12);     // Catch: Exception -> L10
        Log.e(mtproto_tag, r13);     // Catch: Exception -> L10
        Log.e(mtproto_tag, " ");     // Catch: Exception -> L10
        return;
    L8:
        Log.d(mtproto_tag, r42);     // Catch: Exception -> L10
        Log.d(mtproto_tag, r12);     // Catch: Exception -> L10
        Log.d(mtproto_tag, r13);     // Catch: Exception -> L10
        Log.d(mtproto_tag, " ");     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        e.printStackTrace();
    }

    /* renamed from: $r8$lambda$W1fml6687f73UJ9c-xSD3wMPIB4, reason: not valid java name */
    public static /* synthetic */ void m4055$r8$lambda$W1fml6687f73UJ9cxSD3wMPIB4(String r5) {
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " W/tmessages: " + r5 + "\n");     // Catch: Exception -> L4
        getInstance().streamWriter.flush();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        e.printStackTrace();
    }

    /* renamed from: $r8$lambda$bY-Z1YvZDkuzSWHS46PDhtH0hTE, reason: not valid java name */
    public static /* synthetic */ void m4056$r8$lambda$bYZ1YvZDkuzSWHS46PDhtH0hTE(String r5) {
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " D/tmessages: " + r5 + "\n");     // Catch: Exception -> L4
        getInstance().streamWriter.flush();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        e.printStackTrace();
        if (AndroidUtilities.isENOSPC(e) == false) goto L11;
        LaunchActivity.checkFreeDiscSpaceStatic(1);
        return;
    }

    public static /* synthetic */ void $r8$lambda$dl4NO88LqoO42afmmxifdeuAxfg(Throwable r10) {
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + r10 + "\n");     // Catch: Exception -> L16
        StackTraceElement[] r1 = r10.getStackTrace();     // Catch: Exception -> L16
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r3 >= r1.length) goto L8;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: \tat " + r1[r3] + "\n");     // Catch: Exception -> L16
        r3 = r3 + 1;     // Catch: Exception -> L16
        goto L6
    L8:
        Throwable r102 = r10.getCause();     // Catch: Exception -> L16
        if (r102 == null) goto L14;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: Caused by " + r102 + "\n");     // Catch: Exception -> L16
        StackTraceElement[] r103 = r102.getStackTrace();     // Catch: Exception -> L16
    L12:
        if (r2 >= r103.length) goto L14;
        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: \tat " + r103[r2] + "\n");     // Catch: Exception -> L16
        r2 = r2 + 1;     // Catch: Exception -> L16
    L14:
        getInstance().streamWriter.flush();     // Catch: Exception -> L16
        return;
    L16:
        e = move-exception;
        e.printStackTrace();
    }

    /* renamed from: $r8$lambda$zqRYPgC_YdSVtSz1dAm-Pwy_uUw, reason: not valid java name */
    public static /* synthetic */ void m4057$r8$lambda$zqRYPgC_YdSVtSz1dAmPwy_uUw(long r5, long r7, int r9, String r10) {
        getInstance().tlStreamWriter.write(getInstance().dateFormat.format(r5) + " msgId=" + r7 + " account=" + r9);     // Catch: Exception -> L5
        getInstance().tlStreamWriter.write("\n");     // Catch: Exception -> L5
        getInstance().tlStreamWriter.write(r10);     // Catch: Exception -> L5
        getInstance().tlStreamWriter.write("\n\n");     // Catch: Exception -> L5
        getInstance().tlStreamWriter.flush();     // Catch: Exception -> L5
        Log.d(mtproto_tag, "msgId=" + r7 + " account=" + r9);     // Catch: Exception -> L5
        Log.d(mtproto_tag, r10);     // Catch: Exception -> L5
        Log.d(mtproto_tag, " ");     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        e.printStackTrace();
    }

    /* renamed from: -$$Nest$sfgetprivateFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m4058$$Nest$sfgetprivateFields() {
        return privateFields;
    }

    static {
    }

    private FileLog() {
        this.streamWriter = null;
        this.dateFormat = null;
        this.fileDateFormat = null;
        this.logQueue = null;
        this.currentFile = null;
        this.networkFile = null;
        this.tonlibFile = null;
        this.tlStreamWriter = null;
        this.tlRequestsFile = null;
    }

    private static void checkGson() {
        if (gson != null) goto L11;
        HashSet<String> r0 = new HashSet();
        privateFields = r0;
        r0.add("message");
        privateFields.add("phone");
        privateFields.add("about");
        privateFields.add("status_text");
        privateFields.add("bytes");
        privateFields.add("secret");
        privateFields.add("stripped_thumb");
        privateFields.add("strippedBitmap");
        privateFields.add("networkType");
        privateFields.add("disableFree");
        privateFields.add("mContext");
        privateFields.add(PluginsConstants.MenuItemProperties.PRIORITY);
        privateFields.add("constructor");
        privateFields.add("constructorName");
        privateFields.add("parentRichText");
        privateFields.add("parentBlock");
        int r02 = 0;
    L6:
        if (r02 >= 32) goto L8;
        privateFields.add("FLAG_" + r02);
        r02 = r02 + 1;
        goto L6
    L8:
        HashSet<String> r03 = new HashSet();
        excludeRequests = r03;
        r03.add("TL_upload_getFile");
        excludeRequests.add("TL_upload_getWebFile");
        exclusionStrategy = new AnonymousClass1();
        gson = new GsonBuilder().addSerializationExclusionStrategy(exclusionStrategy).registerTypeAdapter(byte[].class, new ByteArrayHexAdapter()).registerTypeAdapterFactory(RuntimeClassNameTypeAdapterFactory.of(TLObject.class, "type_", exclusionStrategy)).registerTypeHierarchyAdapter(TLObject.class, new TLObjectDeserializer(null)).create();
        return;
    }

    public static void cleanupLogs() {
        ensureInitied();
        File r0 = AndroidUtilities.getLogsDir();
        if (r0 == null) goto L34;
        File[] r02 = r0.listFiles();
        if (r02 == null) goto L28;
        int r1 = r02.length;
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L35;
        File r3 = r02[r2];
        if (BuildVars.LOGS_ENABLED == true) goto L12;
    L26:
        r3.delete();
    L27:
        r2 = r2 + 1;
        goto L8
    L12:
        if (getInstance().currentFile == null) goto L17;
        if (r3.getAbsolutePath().equals(getInstance().currentFile.getAbsolutePath()) == true) goto L27;
    L17:
        if (getInstance().networkFile == null) goto L22;
        if (r3.getAbsolutePath().equals(getInstance().networkFile.getAbsolutePath()) == true) goto L27;
    L22:
        if (getInstance().tonlibFile == null) goto L26;
        if (r3.getAbsolutePath().equals(getInstance().tonlibFile.getAbsolutePath()) == false) goto L26;
    L35:
        return;
    L28:
        return;
    }

    public static void d(String r2) {
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        ensureInitied();
        Log.d(tag, r2);
        if (getInstance().streamWriter == null) goto L9;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda7(r2));
        return;
    L9:
        return;
    }

    public static void disableGson(boolean r0) {
        gsonDisabled = r0;
    }

    public static void dumpANR() {
        StringBuilder r0 = new StringBuilder();
        Iterator<Map.Entry<Thread, StackTraceElement[]>> r1 = Thread.getAllStackTraces().entrySet().iterator();
    L3:
        int r3 = 0;
        if (r1.hasNext() == false) goto L9;
        Map.Entry<Thread, StackTraceElement[]> r2 = r1.next();
        Thread r4 = r2.getKey();
        StackTraceElement[] r22 = r2.getValue();
        r0.append("Thread: ");
        r0.append(r4.getName());
        r0.append("\n");
        int r5 = r22.length;
    L6:
        if (r3 >= r5) goto L8;
        StackTraceElement r6 = r22[r3];
        r0.append("\tat ");
        r0.append(r6);
        r0.append("\n");
        r3 = r3 + 1;
        goto L6
    L8:
        r0.append("\n\n");
        goto L3
    L9:
        e("ANR thread dump\n".concat(r0.toString()));
        getInstance().dumpMemory(false);
    }

    public static void dumpResponseAndRequest(int r12, TLObject r13, TLObject r14, TLRPC.TL_error r15, long r16, long r18, int r20) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        return;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        if (r13 == null) goto L27;
        String r4 = r13.getClass().getSimpleName();
        checkGson();
        if (excludeRequests.contains(r4) == false) goto L23;
        if (r15 != null) goto L23;
        return;
    L23:
        String r9 = "req -> " + r4 + " : " + gson.toJson(r13);     // Catch: Throwable -> L20
        String r132 = "null";
        if (r14 == null) goto L16;
        r132 = "res -> " + r14.getClass().getSimpleName() + " : " + gson.toJson(r14);     // Catch: Throwable -> L20
    L15:
        String r10 = r132;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda6(r16, r18, r20, r12, System.currentTimeMillis(), r9, r10, r15));     // Catch: Throwable -> L20
        return;
    L16:
        if (r15 == null) goto L15;
        r132 = "err -> " + r15.getClass().getSimpleName() + " : " + gson.toJson(r15);     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        e(th);
        return;
    L27:
        return;
    }

    public static void dumpUnparsedMessage(TLObject r9, long r10, int r12) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        return;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        if (r9 == null) goto L19;
        checkGson();     // Catch: Throwable -> L14
        getInstance().dateFormat.format(System.currentTimeMillis());     // Catch: Throwable -> L14
        StringBuilder r1 = new StringBuilder("receive message -> ");     // Catch: Throwable -> L14
        r1.append(r9.getClass().getSimpleName());     // Catch: Throwable -> L14
        r1.append(" : ");     // Catch: Throwable -> L14
        if (gsonDisabled == false) goto L11;
        Object r92 = r9;
    L12:
        r1.append(r92);     // Catch: Throwable -> L14
        String r8 = r1.toString();     // Catch: Throwable -> L14
        long r3 = System.currentTimeMillis();     // Catch: Throwable -> L14
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda1(r3, r10, r12, r8));     // Catch: Throwable -> L14
        return;
    L11:
        r92 = gson.toJson(r9);     // Catch: Throwable -> L14
    L20:
        return;
    L19:
        return;
    }

    public static void e(String r2) {
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        ensureInitied();
        Log.e(tag, r2);
        if (getInstance().streamWriter == null) goto L9;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda3(r2));
        return;
    L9:
        return;
    }

    public static void e(String r2, Throwable r3) {
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        ensureInitied();
        Log.e(tag, r2, r3);
        if (getInstance().streamWriter == null) goto L9;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda4(r2, r3));
        return;
    L9:
        return;
    }

    public static void e(Throwable r6) {
        if (BuildVars.LOGS_ENABLED == true) goto L6;
        return;
    L6:
        if (BuildVars.DEBUG_VERSION == true) goto L8;
    L21:
        ensureInitied();
        r6.printStackTrace();
        if (getInstance().streamWriter == null) goto L24;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda0(r6));
        return;
    L24:
        r6.printStackTrace();
        return;
    L8:
        if (r6.getMessage() == null) goto L21;
        if (r6.getMessage().contains("disk image is malformed") == false) goto L21;
        if (databaseIsMalformed == true) goto L21;
        d("copy malformed files");
        databaseIsMalformed = true;
        File r1 = new File(ApplicationLoader.getFilesDirFixed(), "malformed_database/");
        r1.mkdirs();
        ArrayList<File> r0 = MessagesStorage.getInstance(UserConfig.selectedAccount).getDatabaseFiles();
        int r2 = 0;
    L15:
        if (r2 >= r0.size()) goto L21;
        AndroidUtilities.copyFile(r0.get(r2), new File(r1, r0.get(r2).getName()));     // Catch: IOException -> L18
    L20:
        r2 = r2 + 1;
    L18:
        e = move-exception;
        e(e);
        goto L20
    }

    public static void ensureInitied() {
        getInstance().init();
    }

    public static void fatal(Throwable r1) {
        fatal(r1, true);
    }

    public static void fatal(Throwable r1, boolean r2) {
        if (BuildVars.LOGS_ENABLED == true) goto L6;
        return;
    L6:
        if ((r1 instanceof OutOfMemoryError) == false) goto L8;
        getInstance().dumpMemory(false);
    L8:
        ensureInitied();
        r1.printStackTrace();
        if (getInstance().streamWriter == null) goto L12;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda5(r1));
        return;
    L12:
        r1.printStackTrace();
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L16;
        System.exit(2);
        return;
    }

    public static FileLog getInstance() {
        FileLog r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(FileLog.class);
        FileLog r02 = Instance;     // Catch: Throwable -> L11
        if (r02 != null) goto L13;
        r02 = new FileLog();     // Catch: Throwable -> L11
        Instance = r02;     // Catch: Throwable -> L11
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        r02.init();     // Catch: Throwable -> L11
    L13:
        monitor-exit(FileLog.class);     // Catch: Throwable -> L11
        return r02;
    L11:
        th = move-exception;
        throw th;
    }

    public static String getLogDirSize() {
        File r0 = ApplicationLoader.applicationContext.getExternalFilesDir(null);
        if (r0 != null) goto L7;
        return "N/A";
    L7:
        return AndroidUtilities.formatFileSize(Utilities.getDirSize(new File(r0.getAbsolutePath() + "/logs").getAbsolutePath(), 5, false), true, true);
    }

    public static String getNetworkLogPath() {
        if (BuildVars.LOGS_ENABLED == true) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        File r0 = AndroidUtilities.getLogsDir();     // Catch: Throwable -> L10
        if (r0 != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        getInstance().networkFile = new File(r0, getInstance().fileDateFormat.format(System.currentTimeMillis()) + "_net.txt");     // Catch: Throwable -> L10
        return getInstance().networkFile.getAbsolutePath();
    L10:
        th = move-exception;
        th.printStackTrace();
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String getTonlibLogPath() {
        if (BuildVars.LOGS_ENABLED == true) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        File r0 = AndroidUtilities.getLogsDir();     // Catch: Throwable -> L10
        if (r0 != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        getInstance().tonlibFile = new File(r0, getInstance().dateFormat.format(System.currentTimeMillis()) + "_tonlib.txt");     // Catch: Throwable -> L10
        return getInstance().tonlibFile.getAbsolutePath();
    L10:
        th = move-exception;
        th.printStackTrace();
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private void init() {
        if (this.initied == false) goto L6;
        return;
    L6:
        if (this.initiing == true) goto L8;
    L12:
        this.initiing = true;
        Locale r3 = Locale.US;
        this.dateFormat = FastDateFormat.getInstance("dd_MM_yyyy_HH_mm_ss.SSS", r3);
        FastDateFormat r32 = FastDateFormat.getInstance("dd_MM_yyyy_HH_mm_ss", r3);
        this.fileDateFormat = r32;
        String r33 = r32.format(System.currentTimeMillis());
        File r4 = AndroidUtilities.getLogsDir();     // Catch: Exception -> L18
        if (r4 != null) goto L16;
        return;
    L16:
        this.currentFile = new File(r4, r33 + ".txt");     // Catch: Exception -> L18
        this.tlRequestsFile = new File(r4, r33 + "_mtproto.txt");     // Catch: Exception -> L18
    L28:
        this.logQueue = new DispatchQueue("logQueue");     // Catch: Exception -> L22
        this.currentFile.createNewFile();     // Catch: Exception -> L22
        OutputStreamWriter r5 = new OutputStreamWriter(new FileOutputStream(this.currentFile));     // Catch: Exception -> L22
        this.streamWriter = r5;     // Catch: Exception -> L22
        r5.write("-----start log " + r33 + "-----\n");     // Catch: Exception -> L22
        this.streamWriter.flush();     // Catch: Exception -> L22
        OutputStreamWriter r52 = new OutputStreamWriter(new FileOutputStream(this.tlRequestsFile));     // Catch: Exception -> L22
        this.tlStreamWriter = r52;     // Catch: Exception -> L22
        r52.write("-----start log " + r33 + "-----\n");     // Catch: Exception -> L22
        this.tlStreamWriter.flush();     // Catch: Exception -> L22
    L24:
        this.initied = true;
        return;
    L22:
        e = move-exception;
        e.printStackTrace();
    L18:
        e = move-exception;
        e.printStackTrace();
        goto L28
    L8:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L12;
        Segment$$ExternalSyntheticBUOutline1.m("double init call");
    }

    public static void w(String r2) {
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        ensureInitied();
        Log.w(tag, r2);
        if (getInstance().streamWriter == null) goto L9;
        getInstance().logQueue.postRunnable(new FileLog$$ExternalSyntheticLambda2(r2));
        return;
    L9:
        return;
    }

    public void dumpMemory(boolean r5) {
        if (r5 == false) goto L4;
    L6:
        dumpedHeap = System.currentTimeMillis();
        Debug.dumpHprofData(new File(AndroidUtilities.getLogsDir(), getInstance().dateFormat.format(System.currentTimeMillis()) + "_heap.hprof").getAbsolutePath());     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        e(e);
        return;
    L4:
        if ((System.currentTimeMillis() - dumpedHeap) >= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L6;
    }
}
