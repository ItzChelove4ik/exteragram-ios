package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class MetaDataStore {
    private static final Charset UTF_8 = null;
    private final FileStore fileStore;

    /* renamed from: com.google.firebase.crashlytics.internal.metadata.MetaDataStore$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 extends JSONObject {
        final /* synthetic */ String val$userId;

        public AnonymousClass1(String r2) {
            this.val$userId = r2;
            put("userId", r2);
        }
    }

    static {
        UTF_8 = Charset.forName("UTF-8");
    }

    public MetaDataStore(FileStore r1) {
        this.fileStore = r1;
    }

    private static Map<String, String> jsonToKeysData(String r4) {
        JSONObject r0 = new JSONObject(r4);
        HashMap r42 = new HashMap();
        Iterator<String> r1 = r0.keys();
    L4:
        if (r1.hasNext() == false) goto L6;
        String r2 = r1.next();
        r42.put(r2, valueOrNull(r0, r2));
        goto L4
    L6:
        return r42;
    }

    private static List<RolloutAssignment> jsonToRolloutsState(String r7) {
        JSONArray r72 = new JSONObject(r7).getJSONArray("rolloutsState");
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r72.length()) goto L11;
        String r2 = r72.getString(r1);
        r0.add(RolloutAssignment.create(r2));     // Catch: Exception -> L8
    L10:
        r1 = r1 + 1;
    L8:
        e = move-exception;
        Logger.getLogger().w("Failed de-serializing rollouts state. " + r2, e);
        goto L10
    L11:
        return r0;
    }

    private String jsonToUserId(String r1) {
        return valueOrNull(new JSONObject(r1), "userId");
    }

    private static String keysDataToJson(Map<String, String> r1) {
        return new JSONObject(r1).toString();
    }

    private static String rolloutsStateToJson(List<RolloutAssignment> r6) {
        HashMap r0 = new HashMap();
        JSONArray r1 = new JSONArray();
        int r2 = 0;
    L4:
        if (r2 >= r6.size()) goto L11;
        r1.put(new JSONObject(RolloutAssignment.ROLLOUT_ASSIGNMENT_JSON_ENCODER.encode(r6.get(r2))));     // Catch: JSONException -> L8
    L10:
        r2 = r2 + 1;
    L8:
        e = move-exception;
        Logger.getLogger().w("Exception parsing rollout assignment!", e);
        goto L10
    L11:
        r0.put("rolloutsState", r1);
        return new JSONObject(r0).toString();
    }

    private static void safeDeleteCorruptFile(File r3) {
        if (r3.exists() == true) goto L5;
        return;
    L5:
        if (r3.delete() == false) goto L9;
        Logger.getLogger().i("Deleted corrupt file: " + r3.getAbsolutePath());
        return;
    }

    private static void safeDeleteCorruptFile(File r1, String r2) {
        if (r1.exists() == true) goto L5;
        return;
    L5:
        if (r1.delete() == false) goto L9;
        Logger.getLogger().i(String.format("Deleted corrupt file: %s\nReason: %s", new Object[]{r1.getAbsolutePath(), r2}));
        return;
    }

    private static String userIdToJson(String r1) {
        return new AnonymousClass1(r1).toString();
    }

    private static String valueOrNull(JSONObject r2, String r3) {
        if (r2.isNull(r3) == false) goto L5;
        return null;
    L5:
        return r2.optString(r3, null);
    }

    public File getInternalKeysFileForSession(String r2) {
        return this.fileStore.getSessionFile(r2, "internal-keys");
    }

    public File getKeysFileForSession(String r2) {
        return this.fileStore.getSessionFile(r2, "keys");
    }

    public File getRolloutsStateForSession(String r2) {
        return this.fileStore.getSessionFile(r2, "rollouts-state");
    }

    public File getUserDataFileForSession(String r2) {
        return this.fileStore.getSessionFile(r2, "user-data");
    }

    /* JADX WARN: Type inference failed for: r8v3, types: [int] */
    public Map<String, String> readKeyData(String r7, boolean r8) {
        if (r8 == false) goto L5;
        File r6 = getInternalKeysFileForSession(r7);
    L7:
        if (r6.exists() == false) goto L29;
        ?? r82 = (r6.length() > 0 ? 1 : (r6.length() == 0 ? 0 : -1));
        if (r82 == 0) goto L29;
        Closeable r72 = null;
        FileInputStream r83 = new FileInputStream(r6);     // Catch: Throwable -> L20 Exception -> L22
        Map<String, String> r62 = jsonToKeysData(CommonUtils.streamToString(r83));     // Catch: Throwable -> L16 Exception -> L18
        CommonUtils.closeOrLog(r83, "Failed to close user metadata file.");
        return r62;
    L18:
        Exception e = e;
    L24:
        Logger.getLogger().w("Error deserializing user metadata.", e);     // Catch: Throwable -> L16
        safeDeleteCorruptFile(r6);     // Catch: Throwable -> L16
        CommonUtils.closeOrLog(r83, "Failed to close user metadata file.");
        return Collections.EMPTY_MAP;
    L16:
        th = th;
        r72 = r82;
    L27:
        CommonUtils.closeOrLog(r72, "Failed to close user metadata file.");
        throw th;
    L22:
        e = move-exception;
        r83 = null;
        e = e;
    L20:
        th = th;
    L29:
        safeDeleteCorruptFile(r6, "The file has a length of zero for session: " + r7);
        return Collections.EMPTY_MAP;
    L5:
        r6 = getKeysFileForSession(r7);
        goto L7
    }

    public List<RolloutAssignment> readRolloutsState(String r7) {
        File r6 = getRolloutsStateForSession(r7);
        if (r6.exists() == true) goto L5;
    L24:
        safeDeleteCorruptFile(r6, "The file has a length of zero for session: " + r7);
        return Collections.EMPTY_LIST;
    L5:
        if (r6.length() == 0) goto L24;
        FileInputStream r2 = null;
        FileInputStream r3 = new FileInputStream(r6);     // Catch: Throwable -> L16 Exception -> L18
        List<RolloutAssignment> r22 = jsonToRolloutsState(CommonUtils.streamToString(r3));     // Catch: Throwable -> L12 Exception -> L14
        Logger.getLogger().d("Loaded rollouts state:\n" + r22 + "\nfor session " + r7);     // Catch: Throwable -> L12 Exception -> L14
        CommonUtils.closeOrLog(r3, "Failed to close rollouts state file.");
        return r22;
    L14:
        e = e;
        r2 = r3;
    L19:
        Logger.getLogger().w("Error deserializing rollouts state.", e);     // Catch: Throwable -> L16
        safeDeleteCorruptFile(r6);     // Catch: Throwable -> L16
        CommonUtils.closeOrLog(r2, "Failed to close rollouts state file.");
        return Collections.EMPTY_LIST;
    L12:
        th = th;
        r2 = r3;
    L22:
        CommonUtils.closeOrLog(r2, "Failed to close rollouts state file.");
        throw th;
    L18:
        e = e;
    L16:
        th = th;
        goto L22
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    public String readUserId(String r10) {
        File r2 = getUserDataFileForSession(r10);
        Closeable r4 = null;
        if (r2.exists() == false) goto L24;
        ?? r3 = (r2.length() > 0 ? 1 : (r2.length() == 0 ? 0 : -1));
        if (r3 == 0) goto L24;
        FileInputStream r32 = new FileInputStream(r2);     // Catch: Throwable -> L15 Exception -> L17
        String r9 = jsonToUserId(CommonUtils.streamToString(r32));     // Catch: Throwable -> L11 Exception -> L13
        Logger.getLogger().d("Loaded userId " + r9 + " for session " + r10);     // Catch: Throwable -> L11 Exception -> L13
        CommonUtils.closeOrLog(r32, "Failed to close user metadata file.");
        return r9;
    L13:
        e = e;
    L19:
        Logger.getLogger().w("Error deserializing user metadata.", e);     // Catch: Throwable -> L11
        safeDeleteCorruptFile(r2);     // Catch: Throwable -> L11
        CommonUtils.closeOrLog(r32, "Failed to close user metadata file.");
        return null;
    L17:
        e = e;
        r32 = null;
    L15:
        th = th;
    L22:
        CommonUtils.closeOrLog(r4, "Failed to close user metadata file.");
        throw th;
    L11:
        th = th;
        r4 = r3;
    L24:
        Logger.getLogger().d("No userId set for session " + r10);
        safeDeleteCorruptFile(r2);
        return null;
    }

    public void writeKeyData(String r2, Map<String, String> r3) {
        writeKeyData(r2, r3, false);
    }

    public void writeKeyData(String r5, Map<String, String> r6, boolean r7) {
        if (r7 == false) goto L5;
        File r4 = getInternalKeysFileForSession(r5);
    L6:
        BufferedWriter r52 = null;
        String r62 = keysDataToJson(r6);     // Catch: Throwable -> L15 Exception -> L17
        BufferedWriter r72 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r4), UTF_8));     // Catch: Throwable -> L15 Exception -> L17
        r72.write(r62);     // Catch: Throwable -> L11 Exception -> L13
        r72.flush();     // Catch: Throwable -> L11 Exception -> L13
        CommonUtils.closeOrLog(r72, "Failed to close key/value metadata file.");
        return;
    L13:
        Exception e = e;
    L19:
        Logger.getLogger().w("Error serializing key/value metadata.", e);     // Catch: Throwable -> L11
        safeDeleteCorruptFile(r4);     // Catch: Throwable -> L11
        CommonUtils.closeOrLog(r72, "Failed to close key/value metadata file.");
        return;
    L11:
        th = th;
        r52 = r72;
    L22:
        CommonUtils.closeOrLog(r52, "Failed to close key/value metadata file.");
        throw th;
    L17:
        e = move-exception;
        r72 = null;
        e = e;
    L15:
        th = th;
        goto L22
    L5:
        r4 = getKeysFileForSession(r5);
        goto L6
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public void writeRolloutState(String r6, List<RolloutAssignment> r7) {
        File r5 = getRolloutsStateForSession(r6);
        ?? r1 = r7.isEmpty();
        if (r1 == 0) goto L6;
        safeDeleteCorruptFile(r5, "Rollout state is empty for session: " + r6);
        return;
    L6:
        Closeable r62 = null;
        String r72 = rolloutsStateToJson(r7);     // Catch: Throwable -> L15 Exception -> L17
        BufferedWriter r12 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r5), UTF_8));     // Catch: Throwable -> L15 Exception -> L17
        r12.write(r72);     // Catch: Throwable -> L11 Exception -> L13
        r12.flush();     // Catch: Throwable -> L11 Exception -> L13
        CommonUtils.closeOrLog(r12, "Failed to close rollouts state file.");
        return;
    L13:
        Exception e = e;
    L19:
        Logger.getLogger().w("Error serializing rollouts state.", e);     // Catch: Throwable -> L11
        safeDeleteCorruptFile(r5);     // Catch: Throwable -> L11
        CommonUtils.closeOrLog(r12, "Failed to close rollouts state file.");
        return;
    L11:
        th = th;
        r62 = r1;
    L22:
        CommonUtils.closeOrLog(r62, "Failed to close rollouts state file.");
        throw th;
    L17:
        e = move-exception;
        r12 = null;
        e = e;
    L15:
        th = th;
        goto L22
    }

    public void writeUserData(String r5, String r6) {
        File r4 = getUserDataFileForSession(r5);
        BufferedWriter r52 = null;
        String r62 = userIdToJson(r6);     // Catch: Throwable -> L11 Exception -> L13
        BufferedWriter r1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r4), UTF_8));     // Catch: Throwable -> L11 Exception -> L13
        r1.write(r62);     // Catch: Throwable -> L7 Exception -> L9
        r1.flush();     // Catch: Throwable -> L7 Exception -> L9
        CommonUtils.closeOrLog(r1, "Failed to close user metadata file.");
        return;
    L9:
        e = e;
        r52 = r1;
    L14:
        Logger.getLogger().w("Error serializing user metadata.", e);     // Catch: Throwable -> L11
        CommonUtils.closeOrLog(r52, "Failed to close user metadata file.");
        return;
    L7:
        th = th;
        r52 = r1;
    L17:
        CommonUtils.closeOrLog(r52, "Failed to close user metadata file.");
        throw th;
    L13:
        e = e;
    L11:
        th = th;
        goto L17
    }
}
