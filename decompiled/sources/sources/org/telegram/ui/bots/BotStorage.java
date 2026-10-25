package org.telegram.ui.bots;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public class BotStorage {
    public final int account;
    public final long bot_id;
    public final Context context;
    public final boolean secured;
    public String storage_id;
    public final long user_id;

    /* renamed from: org.telegram.ui.bots.BotStorage$1StorageCell, reason: invalid class name */
    public class C1StorageCell extends FrameLayout {
        private final String id;
        private final boolean needDivider;
        private final RadioButton radioButton;
        final /* synthetic */ BotStorage this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: -$$Nest$fgetid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m21347$$Nest$fgetid(C1StorageCell r0) {
            return r0.id;
        }

        public C1StorageCell(BotStorage r10, StorageConfig r11, boolean r12, Context r13) {
            this.this$0 = r10;
            this.val$context = r13;
            super(r13);
            this.id = r11.storage_id;
            RadioButton r102 = new RadioButton(r13);
            this.radioButton = r102;
            r102.setSize(AndroidUtilities.dp(20.0f));
            r102.setColor(Theme.getColor(Theme.key_dialogRadioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
            addView(r102, LayoutHelper.createFrame(22, 22.0f, 19, 20.0f, 0.0f, 0.0f, 0.0f));
            TextView r103 = TextHelper.makeTextView(r13, 16.0f, Theme.key_windowBackgroundWhiteBlackText, true);
            r103.setText(r11.user_name);
            addView(r103, LayoutHelper.createLinear(-1, -2, 7, 62, 9, 8, 0));
            TextView r104 = TextHelper.makeTextView(r13, 14.0f, Theme.key_windowBackgroundWhiteGrayText, false);
            r104.setText(LocaleController.formatString(R.string.BotRestoreStorageCreatedAt, new Object[]{LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.formatSmallDateChat(r11.created_at / 1000), LocaleController.getInstance().getFormatterDay().format(new Date(r11.created_at / 1000))})}));
            addView(r104, LayoutHelper.createLinear(-1, -2, 7, 62, 32, 8, 0));
            this.needDivider = r12;
            setWillNotDraw(!r12);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.needDivider == false) goto L6;
            r8.drawLine(AndroidUtilities.dp(62.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        }

        public void setChecked(boolean r2) {
            this.radioButton.setChecked(r2, true);
        }
    }

    public static class StorageConfig {
        long created_at;
        long edited_at;
        String storage_id;
        long user_id;
        String user_name;

        public StorageConfig() {
        }
    }

    public static /* synthetic */ void $r8$lambda$1QeqdZGUGcNLkOPCyxkRxBxR_Dk(boolean[] r1, Utilities.Callback r2, String[] r3, BottomSheet r4, View r5) {
        if (r1[0] == true) goto L6;
        if (r2 == null) goto L6;
        r1[0] = true;
        r2.run(r3[0]);
    L6:
        r4.dismiss();
    }

    /* renamed from: $r8$lambda$2Tr-ejV4DZFTKINv8zN0Fm9U-Nc, reason: not valid java name */
    public static /* synthetic */ boolean m21344$r8$lambda$2TrejV4DZFTKINv8zN0Fm9UNc(HashSet r2, StorageConfig r3) {
        return !r2.contains(Long.valueOf(r3.user_id));
    }

    public static /* synthetic */ void $r8$lambda$F0LUj9F8WXBbvF1WTUfplcMfhfg(String[] r4, StorageConfig r5, ArrayList r6, ButtonWithCounterView r7, View r8) {
        boolean r82 = false;
        r4[0] = r5.storage_id;
        int r52 = r6.size();
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L6;
        Object r1 = r6.get(r0);
        r0 = r0 + 1;
        C1StorageCell r12 = (C1StorageCell) r1;
        r12.setChecked(TextUtils.equals(C1StorageCell.m21347$$Nest$fgetid(r12), r4[0]));
        goto L3
    L6:
        if (r4[0] == null) goto L8;
        r82 = true;
    L8:
        r7.setEnabled(r82);
    }

    /* renamed from: $r8$lambda$WR9erIHrU4gsykerJmxz-H3I8b0, reason: not valid java name */
    public static /* synthetic */ boolean m21345$r8$lambda$WR9erIHrU4gsykerJmxzH3I8b0(HashSet r2, StorageConfig r3) {
        return !r2.contains(Long.valueOf(r3.user_id));
    }

    /* renamed from: $r8$lambda$_XiV6ygf3hnFTc2t7ybF1Gj-LJQ, reason: not valid java name */
    public static /* synthetic */ void m21346$r8$lambda$_XiV6ygf3hnFTc2t7ybF1GjLJQ(boolean[] r1, Utilities.Callback r2, DialogInterface r3) {
        if (r1[0] == true) goto L7;
        if (r2 == null) goto L8;
        r1[0] = true;
        r2.run(null);
        return;
    L8:
        return;
    }

    public BotStorage(Context r1, int r2, long r3, long r5, boolean r7) {
        this.context = r1;
        this.account = r2;
        this.bot_id = r5;
        this.user_id = r3;
        this.secured = r7;
    }

    private byte[] getBytes(File r7) {
        FileInputStream r0 = new FileInputStream(r7);
        int r1 = (int) r7.length();
        if (this.secured == false) goto L5;
        int r2 = r0.read();
        byte[] r4 = new byte[r2];
        r1 = (r1 - 1) - r2;
        r0.read(r4);
    L18:
        byte[] r12 = new byte[r1];     // Catch: OutOfMemoryError -> L15
        r0.read(r12);
        r0.close();
        if (this.secured == true) goto L20;
        return r12;
    L20:
        Cipher r02 = Cipher.getInstance("AES/GCM/NoPadding");     // Catch: Exception -> L11
        r02.init(2, getSecretKey(), new GCMParameterSpec(128, r4));     // Catch: Exception -> L11
        return r02.doFinal(r12);
    L11:
        e = move-exception;
        FileLog.e(e);
        this.setBytes(r7, "{}".getBytes());
        GlShader$$ExternalSyntheticBUOutline1.m("UNKNOWN_ERROR");
        return null;
    L15:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("QUOTA_EXCEEDED");
        return null;
    L5:
        r4 = null;
        goto L18
    }

    private File getConfigFile() {
        return new File(getDir(), "secure_config.json");
    }

    public static File getDir() {
        File r0 = ApplicationLoader.applicationContext.getFilesDir();     // Catch: Exception -> L13
        if (r0 == null) goto L12;
        File r1 = new File(r0, "apps_storage/");     // Catch: Exception -> L13
        r1.mkdirs();     // Catch: Exception -> L13
        if (r0.exists() == false) goto L7;
    L8:
        if (r0.canWrite() == false) goto L12;
        return r1;
    L7:
        if (r0.mkdirs() == true) goto L8;
    L12:
        return new File(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private File getFile(String r9) {
        File r1 = getDir();
        StringBuilder r2 = new StringBuilder();
        Object r92 = r9;
        if (this.secured == true) goto L6;
        r92 = Long.valueOf(this.user_id);
    L6:
        r2.append(r92);
        r2.append("_");
        r2.append(this.bot_id);
        boolean r3 = this.secured;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 == false) goto L9;
        String r32 = "_s";
    L10:
        r2.append(r32);
        File r0 = new File(r1, r2.toString());
        File r22 = getDir();
        StringBuilder r33 = new StringBuilder();
        r33.append(this.bot_id);
        if (this.secured == false) goto L13;
        r4 = "_s";
    L13:
        r33.append(r4);
        File r12 = new File(r22, r33.toString());
        if (r0.exists() == true) goto L20;
        if (r12.exists() == false) goto L20;
        r12.renameTo(r0);
        return r0;
    L20:
        if (this.secured == false) goto L26;
        File r13 = new File(getDir(), this.user_id + "_" + this.bot_id + "_s");
        if (r0.exists() == true) goto L26;
        if (r13.exists() == false) goto L26;
        r13.renameTo(r0);
    L26:
        return r0;
    L9:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    private JSONObject getJSON() {
        return getJSON(getFile());
    }

    private JSONObject getJSON(File r5) {
        if (r5.exists() == false) goto L13;
        if (r5.length() > 5242880) goto L13;
        return new JSONObject(new String(getBytes(r5)));
    L9:
        e = move-exception;
        FileLog.e(e);
        return new JSONObject();
    L13:
        return new JSONObject();
    }

    private byte[] getRawBytes(File r3) {
        FileInputStream r2 = new FileInputStream(r3);
        byte[] r32 = new byte[(int) r3.length()];     // Catch: OutOfMemoryError -> L6
        r2.read(r32);
        r2.close();
        return r32;
    L6:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("QUOTA_EXCEEDED");
        return null;
    }

    private SecretKey getSecretKey() {
        KeyStore r0 = KeyStore.getInstance("AndroidKeyStore");
        r0.load(null);
        if (r0.containsAlias("MiniAppsKey") == true) goto L6;
        KeyGenerator r5 = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        r5.init(new KeyGenParameterSpec.Builder("MiniAppsKey", 3).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).setUserAuthenticationRequired(false).build());
        r5.generateKey();
    L6:
        return (SecretKey) r0.getKey("MiniAppsKey", null);
    }

    public static boolean isSecuredSupported() {
        return true;
    }

    private HashMap<String, StorageConfig> readConfig() {
        HashMap<String, StorageConfig> r0 = new HashMap();
        JSONObject r1 = new JSONObject(new String(getRawBytes(getConfigFile())));     // Catch: Exception -> L8
        Iterator<String> r7 = r1.keys();     // Catch: Exception -> L8
    L4:
        if (r7.hasNext() == false) goto L10;
        String r2 = r7.next();     // Catch: Exception -> L8
        JSONObject r3 = r1.getJSONObject(r2);     // Catch: Exception -> L8
        StorageConfig r4 = new StorageConfig();     // Catch: Exception -> L8
        r4.storage_id = r2;     // Catch: Exception -> L8
        r4.user_id = r3.getLong("user_id");     // Catch: Exception -> L8
        r4.user_name = r3.getString("user_name");     // Catch: Exception -> L8
        r4.created_at = r3.getLong("created_at");     // Catch: Exception -> L8
        r4.edited_at = r3.getLong("edited_at");     // Catch: Exception -> L8
        r0.put(r2, r4);     // Catch: Exception -> L8
        goto L4
    L10:
        return r0;
    L8:
        e = move-exception;
        FileLog.e(e);
        return r0;
    }

    private void saveConfig(HashMap<String, StorageConfig> r7) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L8
        Iterator<Map.Entry<String, StorageConfig>> r72 = r7.entrySet().iterator();     // Catch: Exception -> L8
    L4:
        if (r72.hasNext() == false) goto L6;
        Map.Entry<String, StorageConfig> r1 = r72.next();     // Catch: Exception -> L8
        JSONObject r2 = new JSONObject();     // Catch: Exception -> L8
        r2.put("user_id", r1.getValue().user_id);     // Catch: Exception -> L8
        r2.put("user_name", r1.getValue().user_name);     // Catch: Exception -> L8
        r2.put("created_at", r1.getValue().created_at);     // Catch: Exception -> L8
        r2.put("edited_at", r1.getValue().edited_at);     // Catch: Exception -> L8
        r0.put(r1.getKey(), r2);     // Catch: Exception -> L8
        goto L4
    L6:
        saveRawBytes(getConfigFile(), r0.toString().getBytes());     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private void saveRawBytes(File r1, byte[] r2) {
        FileOutputStream r0 = new FileOutputStream(r1);
        r0.write(r2);
        r0.close();
    }

    private void setBytes(File r3, byte[] r4) {
        FileOutputStream r0 = new FileOutputStream(r3);
        if (this.secured == true) goto L11;
    L9:
        r0.write(r4);
        r0.close();
        return;
    L11:
        Cipher r32 = Cipher.getInstance("AES/GCM/NoPadding");     // Catch: Exception -> L6
        r32.init(1, getSecretKey());     // Catch: Exception -> L6
        byte[] r2 = r32.getIV();     // Catch: Exception -> L6
        r0.write(r2.length);     // Catch: Exception -> L6
        r0.write(r2);     // Catch: Exception -> L6
        r4 = r32.doFinal(r4);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("UNKNOWN_ERROR");
    }

    private void setJSON(JSONObject r5) {
        byte[] r52 = r5.toString().getBytes();     // Catch: Exception -> L13 OutOfMemoryError -> L15
    L5:
        if (r52.length <= 5242880) goto L21;
        GlShader$$ExternalSyntheticBUOutline1.m("QUOTA_EXCEEDED");
        return;
    L21:
        setBytes(getFile(), r52);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("UNKNOWN_ERROR");
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("UNKNOWN_ERROR");
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("QUOTA_EXCEEDED");
    }

    public void clear() {
        setJSON(new JSONObject());
    }

    public File getFile() {
        if (this.secured == false) goto L16;
        if (TextUtils.isEmpty(this.storage_id) == false) goto L16;
        HashMap<String, StorageConfig> r0 = readConfig();
        Iterator<Map.Entry<String, StorageConfig>> r1 = r0.entrySet().iterator();
    L8:
        if (r1.hasNext() == false) goto L13;
        Map.Entry<String, StorageConfig> r2 = r1.next();
        if (r2.getValue().user_id != this.user_id) goto L8;
        this.storage_id = r2.getKey();
    L13:
        if (TextUtils.isEmpty(this.storage_id) == false) goto L16;
        this.storage_id = UUID.randomUUID().toString();
        StorageConfig r12 = new StorageConfig();
        r12.storage_id = this.storage_id;
        r12.user_id = this.user_id;
        r12.user_name = DialogObject.getName(UserConfig.getInstance(this.account).getCurrentUser());
        long r22 = System.currentTimeMillis();
        r12.edited_at = r22;
        r12.created_at = r22;
        r0.put(this.storage_id, r12);
        saveConfig(r0);
    L16:
        return getFile(this.storage_id);
    }

    public Pair<String, Boolean> getKey(String r7) {
        if (this.secured == true) goto L5;
    L9:
        JSONObject r0 = getJSON();
        String r1 = r0.optString(r7);
        boolean r3 = false;
        if (this.secured == false) goto L35;
        if (r1 != null) goto L35;
        if (r0.keys().hasNext() == true) goto L35;
        HashSet r02 = new HashSet();
        int r2 = 0;
    L16:
        if (r2 >= 16) goto L21;
        UserConfig r4 = UserConfig.getInstance(r2);
        if (r4.isClientActivated() == false) goto L20;
        r02.add(Long.valueOf(r4.getClientUserId()));
    L20:
        r2 = r2 + 1;
        goto L16
    L21:
        Iterator r03 = ((Set) readConfig().values().stream().filter(new BotStorage$$ExternalSyntheticLambda0(r02)).collect(Collectors.toSet())).iterator();
    L23:
        if (r03.hasNext() == false) goto L35;
        File r22 = getFile(((StorageConfig) r03.next()).storage_id);     // Catch: Exception -> L32
        if (r22.exists() == false) goto L23;
        JSONObject r23 = getJSON(r22);     // Catch: Exception -> L32
        if (r23 == null) goto L23;
        if (r23.has(r7) == false) goto L23;
        r3 = true;
    L32:
        e = move-exception;
        FileLog.e(e);
    L35:
        return new Pair(r1, Boolean.valueOf(r3));
    L5:
        if (isSecuredSupported() == true) goto L9;
        GlShader$$ExternalSyntheticBUOutline1.m("UNSUPPORTED");
        return null;
    }

    public List<StorageConfig> getStoragesWithKey(String r6) {
        if (this.secured == false) goto L10;
        if (isSecuredSupported() == true) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("UNSUPPORTED");
        return null;
    L10:
        if (getJSON().keys().hasNext() == true) goto L33;
        ArrayList r0 = new ArrayList();
        HashSet r1 = new HashSet();
        int r2 = 0;
    L13:
        if (r2 >= 16) goto L18;
        UserConfig r3 = UserConfig.getInstance(r2);
        if (r3.isClientActivated() == false) goto L17;
        r1.add(Long.valueOf(r3.getClientUserId()));
    L17:
        r2 = r2 + 1;
        goto L13
    L18:
        Iterator r12 = ((Set) readConfig().values().stream().filter(new BotStorage$$ExternalSyntheticLambda1(r1)).collect(Collectors.toSet())).iterator();
    L20:
        if (r12.hasNext() == false) goto L32;
        StorageConfig r22 = (StorageConfig) r12.next();
        File r32 = getFile(r22.storage_id);     // Catch: Exception -> L30
        if (r32.exists() == false) goto L20;
        JSONObject r33 = getJSON(r32);     // Catch: Exception -> L30
        if (r33 == null) goto L20;
        if (r33.has(r6) == false) goto L20;
        r0.add(r22);     // Catch: Exception -> L30
    L30:
        e = move-exception;
        FileLog.e(e);
        goto L20
    L32:
        return r0;
    L33:
        GlShader$$ExternalSyntheticBUOutline1.m("STORAGE_NOT_EMPTY");
        return null;
    }

    public void restoreFrom(String r5) {
        if (this.secured == false) goto L10;
        if (isSecuredSupported() == true) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("UNSUPPORTED");
        return;
    L10:
        if (getJSON().keys().hasNext() == true) goto L24;
        HashSet r0 = new HashSet();
        int r1 = 0;
    L13:
        if (r1 >= 16) goto L18;
        UserConfig r2 = UserConfig.getInstance(r1);
        if (r2.isClientActivated() == false) goto L17;
        r0.add(Long.valueOf(r2.getClientUserId()));
    L17:
        r1 = r1 + 1;
        goto L13
    L18:
        HashMap<String, StorageConfig> r02 = readConfig();
        StorageConfig r52 = r02.get(r5);
        if (r52 == null) goto L22;
        r52.user_id = this.user_id;
        r52.user_name = DialogObject.getName(UserConfig.getInstance(this.account).getCurrentUser());
        r52.edited_at = System.currentTimeMillis();
        saveConfig(r02);
        this.storage_id = r52.storage_id;
        return;
    L22:
        GlShader$$ExternalSyntheticBUOutline1.m("STORAGE_NOT_FOUND");
        return;
    L24:
        GlShader$$ExternalSyntheticBUOutline1.m("STORAGE_NOT_EMPTY");
    }

    public void setKey(String r4, String r5) {
        if (this.secured == false) goto L10;
        if (isSecuredSupported() == true) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("UNSUPPORTED");
        return;
    L10:
        if ((r4.length() + r5.length()) > 5242880) goto L29;
        JSONObject r0 = getJSON();
        r0.put(r4, r5);     // Catch: Exception -> L26
        if (r0.length() > 10) goto L16;
    L20:
        setJSON(r0);
        if (this.secured == false) goto L36;
        HashMap<String, StorageConfig> r42 = readConfig();     // Catch: Exception -> L31
        StorageConfig r52 = r42.get(this.storage_id);     // Catch: Exception -> L31
        if (r52 == null) goto L37;
        r52.edited_at = System.currentTimeMillis();     // Catch: Exception -> L31
        saveConfig(r42);     // Catch: Exception -> L31
        return;
    L37:
        return;
    L38:
        return;
    L36:
        return;
    L16:
        if (this.secured == false) goto L20;
        GlShader$$ExternalSyntheticBUOutline1.m("QUOTA_EXCEEDED");
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
        GlShader$$ExternalSyntheticBUOutline1.m("UNKNOWN_ERROR");
        return;
    L29:
        GlShader$$ExternalSyntheticBUOutline1.m("QUOTA_EXCEEDED");
    }

    public void showChooseStorage(Context r22, List<StorageConfig> r23, Utilities.Callback<String> r24) {
        BaseFragment r3 = LaunchActivity.getSafeLastFragment();
        if (r3 == null) goto L5;
        Theme.ResourcesProvider r32 = r3.getResourceProvider();
    L6:
        boolean r4 = true;
        String[] r5 = new String[1];
        boolean[] r6 = new boolean[1];
        boolean r8 = false;
        BottomSheet.Builder r7 = new BottomSheet.Builder(r22, false, r32);
        LinearLayout r9 = new LinearLayout(r22);
        r9.setOrientation(1);
        BackupImageView r10 = new BackupImageView(r22);
        TLRPC.User r11 = MessagesController.getInstance(this.account).getUser(Long.valueOf(this.bot_id));
        AvatarDrawable r12 = new AvatarDrawable();
        r12.setInfo(r11);
        r10.setForUserOrChat(r11, r12);
        r9.addView(r10, LayoutHelper.createLinear(80, 80, 49, 0, 21, 0, 13));
        int r102 = Theme.key_windowBackgroundWhiteBlackText;
        TextView r122 = TextHelper.makeTextView(r22, 20.0f, r102, true);
        r122.setText(LocaleController.getString(R.string.BotRestoreStorageTitle));
        r122.setGravity(17);
        r9.addView(r122, LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 10));
        TextView r103 = TextHelper.makeTextView(r22, 14.0f, r102, false);
        r103.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotRestoreStorageText, new Object[]{DialogObject.getDialogTitle(r11)})));
        r103.setGravity(17);
        r9.addView(r103, LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 19));
        TextInfoPrivacyCell r104 = new TextInfoPrivacyCell(r22, r32);
        r104.setBackground(new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray, r32)), Theme.getThemedDrawableByKey(r22, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow, r32)));
        r104.setFixedSize(12);
        r9.addView(r104, LayoutHelper.createLinear(-1, 12, 7, 0, 0, 0, 0));
        HeaderCell r105 = new HeaderCell(r22, r32);
        r105.setText(LocaleController.getString(R.string.BotRestoreStorageHeader));
        r9.addView(r105, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 0));
        ButtonWithCounterView r106 = new ButtonWithCounterView(r22, r32);
        ArrayList r112 = new ArrayList();
        int r123 = 0;
    L8:
        if (r123 >= r23.size()) goto L14;
        StorageConfig r14 = r23.get(r123);
        boolean r17 = r4;
        if (r123 >= (r23.size() - 1)) goto L12;
        boolean r42 = r17;
    L13:
        C1StorageCell r15 = new C1StorageCell(this, r14, r42, r22);
        r15.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r32), 2));
        r15.setOnClickListener(new BotStorage$$ExternalSyntheticLambda2(r5, r14, r112, r106));
        r9.addView(r15, LayoutHelper.createLinear(-1, 56));
        r112.add(r15);
        r123 = r123 + 1;
        r4 = r17;
        r8 = false;
        goto L8
    L12:
        r42 = r8;
        goto L13
    L14:
        boolean r172 = r4;
        r106.setText(LocaleController.getString(R.string.BotRestoreStorageButton), false);
        if (r5[0] == null) goto L17;
        boolean r43 = r172;
    L18:
        r106.setEnabled(r43);
        r9.addView(r106, LayoutHelper.createLinear(-1, 48, 7, 8, 8, 8, 4));
        r7.setCustomView(r9);
        BottomSheet r0 = r7.create();
        r0.fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r32));
        r106.setOnClickListener(new BotStorage$$ExternalSyntheticLambda3(r6, r24, r5, r0));
        r0.setOnDismissListener(new BotStorage$$ExternalSyntheticLambda4(r6, r24));
        r0.show();
        return;
    L17:
        r43 = false;
        goto L18
    L5:
        r32 = null;
        goto L6
    }
}
