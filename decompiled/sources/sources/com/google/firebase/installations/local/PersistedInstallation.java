package com.google.firebase.installations.local;

import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PersistedInstallation {
    private File dataFile;
    private final FirebaseApp firebaseApp;

    /* loaded from: classes5.dex */
    public enum RegistrationStatus extends Enum<RegistrationStatus> {
        private static final /* synthetic */ RegistrationStatus[] $VALUES = null;
        public static final RegistrationStatus ATTEMPT_MIGRATION = null;
        public static final RegistrationStatus NOT_GENERATED = null;
        public static final RegistrationStatus REGISTERED = null;
        public static final RegistrationStatus REGISTER_ERROR = null;
        public static final RegistrationStatus UNREGISTERED = null;

        private static /* synthetic */ RegistrationStatus[] $values() {
            return new RegistrationStatus[]{ATTEMPT_MIGRATION, NOT_GENERATED, UNREGISTERED, REGISTERED, REGISTER_ERROR};
        }

        static {
            ATTEMPT_MIGRATION = new RegistrationStatus("ATTEMPT_MIGRATION", 0);
            NOT_GENERATED = new RegistrationStatus("NOT_GENERATED", 1);
            UNREGISTERED = new RegistrationStatus("UNREGISTERED", 2);
            REGISTERED = new RegistrationStatus("REGISTERED", 3);
            REGISTER_ERROR = new RegistrationStatus("REGISTER_ERROR", 4);
            $VALUES = $values();
        }

        RegistrationStatus(String r1, int r2) {
        }

        public static RegistrationStatus valueOf(String r1) {
            return (RegistrationStatus) Enum.valueOf(RegistrationStatus.class, r1);
        }

        public static RegistrationStatus[] values() {
            return (RegistrationStatus[]) $VALUES.clone();
        }
    }

    public PersistedInstallation(FirebaseApp r1) {
        this.firebaseApp = r1;
    }

    private File getDataFile() {
        if (this.dataFile != null) goto L26;
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L6:
        if (this.dataFile != null) goto L21;
        String r0 = "PersistedInstallation." + this.firebaseApp.getPersistenceKey() + ".json";     // Catch: Throwable -> L12
        File r1 = new File(this.firebaseApp.getApplicationContext().getNoBackupFilesDir(), r0);     // Catch: Throwable -> L12
        this.dataFile = r1;     // Catch: Throwable -> L12
        if (r1.exists() == false) goto L14;
        File r02 = this.dataFile;     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        return r02;
    L14:
        File r12 = new File(this.firebaseApp.getApplicationContext().getFilesDir(), r0);     // Catch: Throwable -> L12
        if (r12.exists() == false) goto L21;
        if (r12.renameTo(this.dataFile) == true) goto L21;
        Log.e("PersistedInstallation", "Unable to move the file from back up to non back up directory", new IOException("Unable to move the file from back up to non back up directory"));     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        return r12;
    L21:
        monitor-exit(this);     // Catch: Throwable -> L12
    L26:
        return this.dataFile;
    }

    private JSONObject readJSONFromFile() {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        byte[] r2 = new byte[16384];
        FileInputStream r3 = new FileInputStream(getDataFile());     // Catch: Throwable -> L19
    L23:
        int r4 = r3.read(r2, 0, 16384);     // Catch: Throwable -> L10
        if (r4 < 0) goto L7;
        r0.write(r2, 0, r4);     // Catch: Throwable -> L10
        goto L23
    L7:
        JSONObject r5 = new JSONObject(r0.toString());     // Catch: Throwable -> L10
        r3.close();     // Catch: Throwable -> L19 Throwable -> L19
        return r5;
    L10:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L16
    L18:
        throw th;     // Catch: Throwable -> L19 Throwable -> L19
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L19 Throwable -> L19
        goto L18
    L20:
        return new JSONObject();
    }

    public void clearDataFile() {
        getDataFile().delete();
    }

    public PersistedInstallationEntry insertOrUpdatePersistedInstallationEntry(PersistedInstallationEntry r5) {
        JSONObject r0 = new JSONObject();     // Catch: Throwable -> L8
        r0.put("Fid", r5.getFirebaseInstallationId());     // Catch: Throwable -> L8
        r0.put("Status", r5.getRegistrationStatus().ordinal());     // Catch: Throwable -> L8
        r0.put("AuthToken", r5.getAuthToken());     // Catch: Throwable -> L8
        r0.put("RefreshToken", r5.getRefreshToken());     // Catch: Throwable -> L8
        r0.put("TokenCreationEpochInSecs", r5.getTokenCreationEpochInSecs());     // Catch: Throwable -> L8
        r0.put("ExpiresInSecs", r5.getExpiresInSecs());     // Catch: Throwable -> L8
        r0.put("FisError", r5.getFisError());     // Catch: Throwable -> L8
        File r1 = File.createTempFile("PersistedInstallation", "tmp", this.firebaseApp.getApplicationContext().getFilesDir());     // Catch: Throwable -> L8
        FileOutputStream r2 = new FileOutputStream(r1);     // Catch: Throwable -> L8
        r2.write(r0.toString().getBytes("UTF-8"));     // Catch: Throwable -> L8
        r2.close();     // Catch: Throwable -> L8
        if (r1.renameTo(getDataFile()) == true) goto L7;
        throw new IOException("unable to rename the tmpfile to PersistedInstallation");     // Catch: Throwable -> L8
    L7:
        return r5;
    }

    public PersistedInstallationEntry readPersistedInstallationEntryValue() {
        JSONObject r10 = readJSONFromFile();
        String r0 = r10.optString("Fid", null);
        int r2 = r10.optInt("Status", RegistrationStatus.ATTEMPT_MIGRATION.ordinal());
        String r3 = r10.optString("AuthToken", null);
        String r4 = r10.optString("RefreshToken", null);
        long r8 = r10.optLong("TokenCreationEpochInSecs", 0);
        long r5 = r10.optLong("ExpiresInSecs", 0);
        String r102 = r10.optString("FisError", null);
        return PersistedInstallationEntry.builder().setFirebaseInstallationId(r0).setRegistrationStatus(RegistrationStatus.values()[r2]).setAuthToken(r3).setRefreshToken(r4).setTokenCreationEpochInSecs(r8).setExpiresInSecs(r5).setFisError(r102).build();
    }
}
