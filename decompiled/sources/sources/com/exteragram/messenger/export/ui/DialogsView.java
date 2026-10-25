package com.exteragram.messenger.export.ui;

import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes4.dex */
public class DialogsView extends BasePreferencesActivity {
    private ArrayList<ParsedDialogObject> dialogObjects;
    private final String mainPath;

    public enum DialogsItem extends Enum<DialogsItem> {
        private static final /* synthetic */ DialogsItem[] $VALUES = null;
        public static final DialogsItem GENERAL_HEADER = null;

        private static /* synthetic */ DialogsItem[] $values() {
            return new DialogsItem[]{GENERAL_HEADER};
        }

        static {
            GENERAL_HEADER = new DialogsItem("GENERAL_HEADER", 0);
            $VALUES = $values();
        }

        DialogsItem(String r1, int r2) {
        }

        public static DialogsItem valueOf(String r1) {
            return (DialogsItem) Enum.valueOf(DialogsItem.class, r1);
        }

        public static DialogsItem[] values() {
            return (DialogsItem[]) $VALUES.clone();
        }
    }

    public static class ParsedDialogObject {

        @SerializedName("id")
        public long id;

        @SerializedName("left")
        public boolean left;

        @SerializedName("name")
        public String name;

        @SerializedName("relativePath")
        public String path;

        private ParsedDialogObject() {
        }
    }

    public static class ParsedUserInfo {

        @SerializedName("about")
        public String about;

        @SerializedName("chats")
        public ParsedDialogObject[] chats;

        private ParsedUserInfo() {
        }
    }

    public DialogsView(String r1) {
        this.mainPath = r1;
    }

    private void loadDialogs() {
        String r0 = FileManager.readFileContent(new File(this.mainPath, "result.json"));
        if (r0 != null) goto L18;
        BulletinFactory.of(this).createErrorBulletin("Failed to read result.json!").show();
        this.dialogObjects = new ArrayList();
        return;
    L18:
        ParsedUserInfo r02 = (ParsedUserInfo) ExteraConfig.getGSON().fromJson(r0, ParsedUserInfo.class);     // Catch: Throwable -> L12
        if (r02 != null) goto L9;
    L14:
        this.dialogObjects = new ArrayList();     // Catch: Throwable -> L12
        return;
    L9:
        if (r02.chats == null) goto L14;
        this.dialogObjects = new ArrayList(Arrays.asList(r02.chats));     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        FileLog.e("Export read from file failed!", th);
        BulletinFactory.of(this).createErrorBulletin("Failed to parse result.json!").show();
        this.dialogObjects = new ArrayList();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r5, UniversalAdapter r6) {
        r5.add(UItem.asHeader("Dialogs"));
        ArrayList<ParsedDialogObject> r62 = this.dialogObjects;
        if (r62 != null) goto L5;
    L16:
        r5.add(UItem.asShadow("No dialogs found in the export file."));
        return;
    L5:
        if (r62.isEmpty() == true) goto L16;
        int r0 = 0;
    L9:
        if (r0 >= this.dialogObjects.size()) goto L15;
        boolean r3 = true;
        UItem r1 = UItem.asButton((DialogsItem.values().length + 1) + r0, this.dialogObjects.get(r0).name);
        if (r0 < (this.dialogObjects.size() - 1)) goto L14;
        r3 = false;
    L14:
        r5.add(r1.showDivider(r3));
        r0 = r0 + 1;
        goto L9
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return "Dialogs";
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = DialogsItem.values().length + 1;
        int r12 = r1.id;
        if (r12 < r22) goto L11;
        int r13 = r12 - r22;
        ArrayList<ParsedDialogObject> r23 = this.dialogObjects;
        if (r23 == null) goto L12;
        if (r13 >= 0) goto L8;
        return;
    L8:
        if (r13 >= r23.size()) goto L14;
        presentFragment(new ChatViewer(this.mainPath + "/" + this.dialogObjects.get(r13).path));
        return;
    L14:
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        loadDialogs();
        return true;
    }
}
