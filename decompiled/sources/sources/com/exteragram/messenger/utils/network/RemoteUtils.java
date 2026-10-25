package com.exteragram.messenger.utils.network;

import android.content.SharedPreferences;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.MediaError;
import j$.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public abstract class RemoteUtils {
    private static final long CONFIG_REFRESH_INTERVAL = 0;
    private static final long CONFIG_RETRY_INTERVAL = 0;
    private static final long MESSAGES_REQUEST_TIMEOUT = 0;
    private static final AtomicBoolean fetchInProgress = null;
    private static volatile long lastFailedFetchTime;
    private static int messagesRequestGeneration;
    private static final Object messagesRequestLock = null;
    private static Runnable messagesTimeoutRunnable;
    private static ArrayList<Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error>> pendingMessagesCallbacks;
    public static SharedPreferences sharedPreferences;

    /* renamed from: $r8$lambda$0p9UTAdmiW_DBagD4pp-dMeLYUk, reason: not valid java name */
    public static /* synthetic */ void m1790$r8$lambda$0p9UTAdmiW_DBagD4ppdMeLYUk(AtomicBoolean r2, Utilities.Callback2 r3) {
        if (r2.compareAndSet(false, true) == false) goto L6;
        TLRPC.TL_error r22 = new TLRPC.TL_error();
        r22.code = 408;
        r22.text = "REQUEST_TIMEOUT";
        r3.run(null, r22);
        return;
    }

    public static /* synthetic */ void $r8$lambda$3SpRrpMm50vpRClqPPGUprUYYYw(TLRPC.TL_messages_getHistory r4, Runnable r5, int r6, TLRPC.Chat r7) {
        if (r7 != null) goto L4;
    L7:
        TLRPC.TL_error r42 = new TLRPC.TL_error();
        r42.code = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        r42.text = "CHANNEL_RESOLVE_FAILED";
        deliverMessagesResult(r6, null, r42);
        return;
    L4:
        if (r7.id != 2227431611L) goto L7;
        TLRPC.TL_inputPeerChannel r62 = new TLRPC.TL_inputPeerChannel();
        r4.peer = r62;
        r62.channel_id = r7.id;
        r62.access_hash = r7.access_hash;
        r5.run();
    }

    public static /* synthetic */ void $r8$lambda$4QYm8KFjMBfJJ4laGtinXysTpck(AtomicBoolean r0, AccountInstance r1, AtomicInteger r2, AtomicInteger r3, Runnable r4, AtomicReference r5, int r6, Runnable r7) {
        if (r0.get() == false) goto L5;
        return;
    L5:
        r1.getConnectionsManager().cancelRequest(r2.get(), false);
        if (r3.get() >= 3) goto L9;
        AndroidUtilities.runOnUIThread(r4);
        AndroidUtilities.runOnUIThread((Runnable) r5.get(), r6);
        return;
    L9:
        r7.run();
    }

    /* renamed from: $r8$lambda$6r48Eh4Wsu_QezTRhT-68nEzoPU, reason: not valid java name */
    public static /* synthetic */ void m1791$r8$lambda$6r48Eh4Wsu_QezTRhT68nEzoPU() {
    }

    public static /* synthetic */ void $r8$lambda$84O1WLvUC3qDyZTPzn_dgZu6QAQ(TLRPC.TL_messages_search r4, Runnable r5, AtomicReference r6, int r7, AtomicBoolean r8, Utilities.Callback2 r9, TLRPC.Chat r10) {
        if (r10 == null) goto L8;
        if (r10.id != 2227431611L) goto L8;
        TLRPC.TL_inputPeerChannel r82 = new TLRPC.TL_inputPeerChannel();
        r4.peer = r82;
        r82.channel_id = r10.id;
        r82.access_hash = r10.access_hash;
        r5.run();
        AndroidUtilities.runOnUIThread((Runnable) r6.get(), r7);
        return;
    L8:
        if (r8.compareAndSet(false, true) == false) goto L11;
        TLRPC.TL_error r42 = new TLRPC.TL_error();
        r42.code = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        r42.text = "CHANNEL_RESOLVE_FAILED";
        r9.run(null, r42);
        return;
    }

    /* renamed from: $r8$lambda$E3fegTDk-h-tibGgJJ4Zwo5Ymw0, reason: not valid java name */
    public static /* synthetic */ void m1792$r8$lambda$E3fegTDkhtibGgJJ4Zwo5Ymw0(AccountInstance r1, TLRPC.TL_messages_getHistory r2, int r3) {
        r1.getConnectionsManager().sendRequest(r2, new RemoteUtils$$ExternalSyntheticLambda12(r3));
    }

    public static /* synthetic */ void $r8$lambda$LmfFRuJdEdEVAug9vbi59pl5km0(TLRPC.messages_Messages r9, TLRPC.TL_error r10) {
        fetchInProgress.set(false);
        if (r10 != null) goto L32;
        if (r9 == null) goto L32;
        HashSet r102 = new HashSet();
        int r0 = r9.messages.size() - 1;
        boolean r3 = false;
    L7:
        if (r0 < 0) goto L24;
        TLRPC.Message r4 = r9.messages.get(r0);
        if ((r4 instanceof TLRPC.TL_message) == false) goto L23;
        if (r4.message.startsWith("remote_config") == false) goto L23;
        String[] r32 = r4.message.split("\n");
        if (r32.length <= 1) goto L22;
        int r42 = r32.length;
        int r5 = 0;
    L15:
        if (r5 >= r42) goto L22;
        String[] r6 = r32[r5].split("=", 2);
        if (r6.length != 2) goto L21;
        String r7 = r6[0].trim();
        String r62 = r6[1].trim();
        if (r62.equals("null") == true) goto L21;
        updateValue(r7, r62);
        r102.add(r7);
    L21:
        r5 = r5 + 1;
    L22:
        r3 = true;
    L23:
        r0 = r0 - 1;
        goto L7
    L24:
        if (r3 == true) goto L26;
    L30:
        lastFailedFetchTime = System.currentTimeMillis();
        return;
    L26:
        if (r102.isEmpty() == true) goto L30;
        lastFailedFetchTime = 0;
        sharedPreferences.edit().putLong("__last_fetch_attempt_time", System.currentTimeMillis()).apply();
        removeOldPreferences(r102);
        return;
    L32:
        lastFailedFetchTime = System.currentTimeMillis();
    }

    public static /* synthetic */ void $r8$lambda$OnWrYNguU3DJfMISnu8tl4pwRVk(int r2) {
        TLRPC.TL_error r0 = new TLRPC.TL_error();
        r0.code = 408;
        r0.text = "REQUEST_TIMEOUT";
        deliverMessagesResult(r2, null, r0);
    }

    /* renamed from: $r8$lambda$Q2nxzL4QXD-hawk8oMmFpxL7pOE, reason: not valid java name */
    public static /* synthetic */ void m1793$r8$lambda$Q2nxzL4QXDhawk8oMmFpxL7pOE(TLRPC.TL_messages_search r7, AccountInstance r8, Runnable r9, AtomicReference r10, int r11, AtomicBoolean r12, Utilities.Callback2 r13) {
        TLRPC.InputPeer r82 = r8.getMessagesController().getInputPeer(-2227431611L);
        r7.peer = r82;
        if (r82.access_hash == 0) goto L6;
        r9.run();
        AndroidUtilities.runOnUIThread((Runnable) r10.get(), r11);
        return;
    L6:
        ChatUtils.getInstance().resolveChannel("XS6GEcz5ZXMu82UvXQc", new RemoteUtils$$ExternalSyntheticLambda7(r7, r9, r10, r11, r12, r13));
    }

    public static /* synthetic */ void $r8$lambda$UEIegV2IjuUzBjubE_kaeXJS8OE(TLRPC.TL_messages_getHistory r4, AccountInstance r5, Runnable r6, int r7) {
        TLRPC.InputPeer r52 = r5.getMessagesController().getInputPeer(-2227431611L);
        r4.peer = r52;
        if (r52.access_hash == 0) goto L6;
        r6.run();
        return;
    L6:
        ChatUtils.getInstance().resolveChannel("XS6GEcz5ZXMu82UvXQc", new RemoteUtils$$ExternalSyntheticLambda11(r4, r6, r7));
    }

    public static /* synthetic */ void $r8$lambda$VPbxsBgeVDZRlr6n6eb9kvQGVxM(AtomicBoolean r2, AtomicReference r3, Utilities.Callback2 r4, TLObject r5, TLRPC.TL_error r6) {
        if (r2.compareAndSet(false, true) == true) goto L5;
        return;
    L5:
        AndroidUtilities.cancelRunOnUIThread((Runnable) r3.get());
        if (r6 != null) goto L11;
        if (r5 == null) goto L11;
        r4.run((TLRPC.messages_Messages) r5, null);
        return;
    L11:
        r4.run(null, r6);
    }

    /* renamed from: $r8$lambda$Zys4bse1bgzGUgsT-0MB9ycKDLM, reason: not valid java name */
    public static /* synthetic */ void m1794$r8$lambda$Zys4bse1bgzGUgsT0MB9ycKDLM(AtomicBoolean r1, AtomicInteger r2, AtomicInteger r3, AccountInstance r4, TLRPC.TL_messages_search r5, AtomicReference r6, Utilities.Callback2 r7) {
        if (r1.get() == false) goto L5;
        return;
    L5:
        r2.incrementAndGet();
        r3.set(r4.getConnectionsManager().sendRequest(r5, new RemoteUtils$$ExternalSyntheticLambda6(r1, r6, r7)));
    }

    /* renamed from: $r8$lambda$fbLQZhiW34Arj2DTfMUPETN-MPM, reason: not valid java name */
    public static /* synthetic */ void m1795$r8$lambda$fbLQZhiW34Arj2DTfMUPETNMPM(int r1, TLObject r2, TLRPC.TL_error r3) {
        if (r3 != null) goto L8;
        if (r2 == null) goto L8;
        deliverMessagesResult(r1, (TLRPC.messages_Messages) r2, null);
        return;
    L8:
        deliverMessagesResult(r1, null, r3);
    }

    static {
        CONFIG_REFRESH_INTERVAL = Duration.ofMinutes(10).toMillis();
        CONFIG_RETRY_INTERVAL = Duration.ofSeconds(30).toMillis();
        MESSAGES_REQUEST_TIMEOUT = Duration.ofSeconds(60).toMillis();
        fetchInProgress = new AtomicBoolean();
        messagesRequestLock = new Object();
    }

    private static boolean areValuesEqual(Object r0, Object r1) {
        if (r0 == null) goto L7;
        if (r1 == null) goto L7;
        return r0.equals(r1);
    L7:
        if (r0 != r1) goto L10;
        return true;
    L10:
        return false;
    }

    private static void deliverMessagesResult(int r3, TLRPC.messages_Messages r4, TLRPC.TL_error r5) {
        Object r0 = messagesRequestLock;
        monitor-enter(r0);
    L17:
        th = move-exception;
        throw th;
    L5:
        if (r3 != messagesRequestGeneration) goto L19;
        ArrayList<Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error>> r32 = pendingMessagesCallbacks;     // Catch: Throwable -> L17
        if (r32 == null) goto L19;
        Runnable r1 = messagesTimeoutRunnable;     // Catch: Throwable -> L17
        pendingMessagesCallbacks = null;     // Catch: Throwable -> L17
        messagesTimeoutRunnable = null;     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        if (r1 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r1);
    L13:
        int r02 = r32.size();
        int r12 = 0;
    L14:
        if (r12 >= r02) goto L16;
        Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error> r2 = r32.get(r12);
        r12 = r12 + 1;
        r2.run(r4, r5);
        goto L14
    L16:
        return;
    L19:
        monitor-exit(r0);     // Catch: Throwable -> L17
    }

    public static void forceRefresh() {
        initCached();
        lastFailedFetchTime = 0;
        loadConfig();
    }

    public static Boolean getBooleanConfigValue(String r3, boolean r4) {
        SharedPreferences r0 = getPrefs();     // Catch: Exception -> L6
        if (r0 == null) goto L5;
        return Boolean.valueOf(r0.getBoolean(r3, r4));
    L10:
        Object r02 = r0.getAll().get(r3);     // Catch: Exception -> L6
        if ((r02 instanceof String) == false) goto L13;
        boolean r03 = Boolean.parseBoolean((String) r02);     // Catch: Exception -> L6
    L15:
        return Boolean.valueOf(r03);
    L13:
        r03 = r4;
        goto L15
    L5:
        return Boolean.valueOf(r4);
    L6:
        e = move-exception;
        AppUtils.log("Error getting value for key: " + r3, e);
        return Boolean.valueOf(r4);
    }

    public static Float getFloatConfigValue(String r3, float r4) {
        SharedPreferences r0 = getPrefs();     // Catch: Exception -> L6
        if (r0 == null) goto L5;
        Object r02 = r0.getAll().get(r3);     // Catch: Exception -> L6
        if ((r02 instanceof String) == false) goto L13;
        return Float.valueOf(Float.parseFloat((String) r02));
    L13:
        if ((r02 instanceof Float) == false) goto L17;
        return (Float) r02;
    L17:
        if ((r02 instanceof Long) == false) goto L21;
        return Float.valueOf(((Long) r02).floatValue());
    L21:
        if ((r02 instanceof Integer) == false) goto L26;
        return Float.valueOf(((Integer) r02).floatValue());
    L26:
        return Float.valueOf(r4);
    L5:
        return Float.valueOf(r4);
    L6:
        e = move-exception;
        AppUtils.log("Error getting value for key: " + r3, e);
        goto L26
    }

    public static Integer getIntConfigValue(String r3, int r4) {
        SharedPreferences r0 = getPrefs();     // Catch: Exception -> L6
        if (r0 == null) goto L5;
        Object r02 = r0.getAll().get(r3);     // Catch: Exception -> L6
        if ((r02 instanceof String) == false) goto L13;
        return Integer.valueOf(Integer.parseInt((String) r02));
    L13:
        if ((r02 instanceof Long) == false) goto L17;
        return Integer.valueOf(((Long) r02).intValue());
    L17:
        if ((r02 instanceof Integer) == false) goto L22;
        return (Integer) r02;
    L22:
        return Integer.valueOf(r4);
    L5:
        return Integer.valueOf(r4);
    L6:
        e = move-exception;
        AppUtils.log("Error getting int config value for key: " + r3, e);
        goto L22
    }

    public static void getMessages(Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error> r4) {
        Object r0 = messagesRequestLock;
        monitor-enter(r0);
        ArrayList<Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error>> r1 = pendingMessagesCallbacks;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.add(r4);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        int r12 = messagesRequestGeneration + 1;     // Catch: Throwable -> L9
        messagesRequestGeneration = r12;     // Catch: Throwable -> L9
        ArrayList<Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error>> r2 = new ArrayList();     // Catch: Throwable -> L9
        pendingMessagesCallbacks = r2;     // Catch: Throwable -> L9
        r2.add(r4);     // Catch: Throwable -> L9
        RemoteUtils$$ExternalSyntheticLambda8 r42 = new RemoteUtils$$ExternalSyntheticLambda8(r12);     // Catch: Throwable -> L9
        messagesTimeoutRunnable = r42;     // Catch: Throwable -> L9
        AndroidUtilities.runOnUIThread(r42, MESSAGES_REQUEST_TIMEOUT);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (ApplicationLoader.applicationHandler != null) goto L17;
        TLRPC.TL_error r43 = new TLRPC.TL_error();
        r43.code = 503;
        r43.text = "APP_NOT_READY";
        deliverMessagesResult(r12, null, r43);
        return;
    L17:
        AccountInstance r44 = AccountInstance.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_messages_getHistory r02 = new TLRPC.TL_messages_getHistory();
        r02.offset_id = 0;
        r02.limit = 100;
        AndroidUtilities.runOnUIThread(new RemoteUtils$$ExternalSyntheticLambda10(r02, r44, new RemoteUtils$$ExternalSyntheticLambda9(r44, r02, r12), r12));
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private static SharedPreferences getPrefs() {
        if (sharedPreferences != null) goto L6;
        initCached();
    L6:
        return sharedPreferences;
    }

    public static String getStringConfigValue(String r3, String r4) {
        SharedPreferences r0 = getPrefs();     // Catch: Exception -> L9
        if (r0 == null) goto L11;
        Object r02 = r0.getAll().get(r3);     // Catch: Exception -> L9
        if (r02 == null) goto L11;
        return String.valueOf(r02);
    L11:
        return r4;
    L9:
        e = move-exception;
        AppUtils.log("Error getting value for key: " + r3, e);
        return r4;
    }

    public static Set<String> getStringSetConfigValue(String r3, Set<String> r4) {
        SharedPreferences r0 = getPrefs();     // Catch: Exception -> L9
        if (r0 == null) goto L15;
        Object r02 = r0.getAll().get(r3);     // Catch: Exception -> L9
        if ((r02 instanceof Set) == false) goto L12;
        return (Set) r02;
    L12:
        if ((r02 instanceof String) == false) goto L15;
        return new HashSet(Arrays.asList(((String) r02).split(",\\s*")));
    L15:
        return r4;
    L9:
        e = move-exception;
        AppUtils.log("Error getting value for key: " + r3, e);
        return r4;
    }

    public static void init() {
        initCached();
        long r0 = System.currentTimeMillis();
        if (Math.abs(r0 - sharedPreferences.getLong("__last_fetch_attempt_time", 0)) >= CONFIG_REFRESH_INTERVAL) goto L6;
        return;
    L6:
        if (lastFailedFetchTime != 0) goto L8;
    L10:
        loadConfig();
        return;
    L8:
        if (Math.abs(r0 - lastFailedFetchTime) >= CONFIG_RETRY_INTERVAL) goto L10;
    }

    public static void initCached() {
        if (sharedPreferences != null) goto L6;
        sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("exteraremoteconfig", 0);
        return;
    }

    private static void loadConfig() {
        if (fetchInProgress.compareAndSet(false, true) == true) goto L5;
        return;
    L5:
        getMessages(new RemoteUtils$$ExternalSyntheticLambda5());
    }

    private static Object parseConfigValue(String r2) {
        if (r2.matches("-?\\d+") == false) goto L9;
        return Long.valueOf(Long.parseLong(r2));
    L7:
        return Float.valueOf(Float.parseFloat(r2));
    L9:
        if (r2.matches("-?\\d+(\\.\\d+)") == false) goto L13;
        return Float.valueOf(Float.parseFloat(r2));
    L13:
        if (r2.equalsIgnoreCase("true") == false) goto L17;
        return Boolean.TRUE;
    L17:
        if (r2.equalsIgnoreCase("false") == false) goto L21;
        return Boolean.FALSE;
    L21:
        if (r2.startsWith("[") == true) goto L23;
    L30:
        return r2;
    L23:
        if (r2.endsWith("]") == false) goto L30;
        String r22 = r2.substring(1, r2.length() - 1);
        if (r22.isEmpty() == false) goto L29;
        return new HashSet();
    L29:
        return new HashSet(Arrays.asList(r22.split(",\\s*")));
    }

    private static void removeOldPreferences(Set<String> r4) {
        SharedPreferences.Editor r0 = sharedPreferences.edit();
        Iterator<String> r1 = sharedPreferences.getAll().keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L10;
        String r2 = r1.next();
        if (r4.contains(r2) == true) goto L4;
        if ("__last_fetch_attempt_time".equals(r2) == true) goto L4;
        r0.remove(r2);
        goto L4
    L10:
        r0.apply();
    }

    private static void saveConfigValueToPreferences(SharedPreferences.Editor r2, String r3, Object r4) {
        if ((r4 instanceof Long) == false) goto L7;
        r2.putLong(r3, ((Long) r4).longValue());
        return;
    L7:
        if ((r4 instanceof Float) == false) goto L11;
        r2.putFloat(r3, ((Float) r4).floatValue());
        return;
    L11:
        if ((r4 instanceof Boolean) == false) goto L15;
        r2.putBoolean(r3, ((Boolean) r4).booleanValue());
        return;
    L15:
        if ((r4 instanceof Set) == false) goto L19;
        r2.putStringSet(r3, (Set) r4);
        return;
    L19:
        if ((r4 instanceof String) == false) goto L22;
        r2.putString(r3, (String) r4);
        return;
    }

    private static void saveToPreferences(String r1, String r2) {
        SharedPreferences.Editor r0 = sharedPreferences.edit();
        saveConfigValueToPreferences(r0, r1, parseConfigValue(r2));
        r0.apply();
    }

    public static void searchMessages(int r10, String r11, TLRPC.MessagesFilter r12, Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error> r13, int r14) {
        AccountInstance r3 = AccountInstance.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_messages_search r2 = new TLRPC.TL_messages_search();
        r2.q = r11;
        r2.offset_id = 0;
        r2.limit = r10;
        r2.filter = r12;
        AtomicBoolean r7 = new AtomicBoolean(false);
        AtomicInteger r5 = new AtomicInteger();
        AtomicInteger r4 = new AtomicInteger();
        AtomicReference r72 = new AtomicReference(new RemoteUtils$$ExternalSyntheticLambda0());
        RemoteUtils$$ExternalSyntheticLambda1 r9 = new RemoteUtils$$ExternalSyntheticLambda1(r7, r13);
        RemoteUtils$$ExternalSyntheticLambda2 r1 = new RemoteUtils$$ExternalSyntheticLambda2(r7, r5, r4, r3, r2, r72, r13);
        r72.set(new RemoteUtils$$ExternalSyntheticLambda3(r7, r3, r4, r5, r1, r72, r14, r9));
        AndroidUtilities.runOnUIThread(new RemoteUtils$$ExternalSyntheticLambda4(r2, r3, r1, r72, r14, r7, r13));
    }

    public static void searchMessages(String r1, TLRPC.MessagesFilter r2, Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error> r3, int r4) {
        searchMessages(50, r1, r2, r3, r4);
    }

    private static void updateValue(String r2, String r3) {
        if (areValuesEqual(sharedPreferences.getAll().get(r2), parseConfigValue(r3)) == true) goto L6;
        saveToPreferences(r2, r3);
        return;
    }
}
