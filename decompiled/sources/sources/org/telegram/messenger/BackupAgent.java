package org.telegram.messenger;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupManager;
import android.app.backup.SharedPreferencesBackupHelper;

/* loaded from: classes.dex */
public class BackupAgent extends BackupAgentHelper {
    private static BackupManager backupManager;

    public BackupAgent() {
    }

    public static void requestBackup() {
        if (backupManager != null) goto L5;
        backupManager = new BackupManager(ApplicationLoader.applicationContext);
    L5:
        backupManager.dataChanged();
    }

    @Override // android.app.backup.BackupAgent
    public void onCreate() {
        addHelper("prefs", new SharedPreferencesBackupHelper(this, new String[]{"saved_tokens", "saved_tokens_login"}));
    }
}
