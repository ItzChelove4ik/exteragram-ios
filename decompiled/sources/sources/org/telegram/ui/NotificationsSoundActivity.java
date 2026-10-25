package org.telegram.ui;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.ringtone.RingtoneDataStore;
import org.telegram.messenger.ringtone.RingtoneUploader;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CreationTextCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class NotificationsSoundActivity extends BaseFragment implements ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate, NotificationCenter.NotificationCenterDelegate {
    Adapter adapter;
    ChatAvatarContainer avatarContainer;
    ChatAttachAlert chatAttachAlert;
    int currentType;
    long dialogId;
    int dividerRow;
    int dividerRow2;
    Ringtone lastPlayedRingtone;
    RecyclerListView listView;
    Theme.ResourcesProvider resourcesProvider;
    int rowCount;
    Tone selectedTone;
    boolean selectedToneChanged;
    SparseArray<Tone> selectedTones;
    NumberTextView selectedTonesCountTextView;
    ArrayList<Tone> serverTones;
    int serverTonesEndRow;
    int serverTonesHeaderRow;
    int serverTonesStartRow;
    private int stableIds;
    private Tone startSelectedTone;
    ArrayList<Tone> systemTones;
    int systemTonesEndRow;
    int systemTonesHeaderRow;
    int systemTonesStartRow;
    private final int tonesStreamType;
    long topicId;
    int uploadRow;
    ArrayList<Tone> uploadingTones;

    /* renamed from: org.telegram.ui.NotificationsSoundActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ NotificationsSoundActivity this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: $r8$lambda$9dEoxcfNPK3HxYn2T9Y3cCs---Y, reason: not valid java name */
        public static /* synthetic */ void m16346$r8$lambda$9dEoxcfNPK3HxYn2T9Y3cCsY(AnonymousClass1 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$1(r1, r2);
        }

        /* renamed from: $r8$lambda$ugWLPNT-PhGiocKqw-uTFr7BFCY, reason: not valid java name */
        public static /* synthetic */ void m16347$r8$lambda$ugWLPNTPhGiocKqwuTFr7BFCY(TLObject r0, TLRPC.TL_error r1) {
        }

        public static /* synthetic */ void $r8$lambda$xWE5qciL3UkVkrmxKSjTxAxHNUY(AlertDialog r0, int r1) {
            r0.dismiss();
        }

        public AnonymousClass1(NotificationsSoundActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private void deleteSelectedMessages() {
            ArrayList r0 = new ArrayList();
            int r2 = 0;
        L3:
            int r3 = this.this$0.selectedTones.size();
            NotificationsSoundActivity r4 = this.this$0;
            if (r2 >= r3) goto L17;
            Tone r32 = r4.selectedTones.valueAt(r2);
            TLRPC.Document r42 = r32.document;
            if (r42 == null) goto L9;
            r0.add(r42);
            this.this$0.getMediaDataController().ringtoneDataStore.remove(r32.document);
        L9:
            if (r32.uri == null) goto L13;
            RingtoneUploader r43 = this.this$0.getMediaDataController().ringtoneUploaderHashMap.get(r32.uri);
            if (r43 == null) goto L13;
            r43.cancel();
        L13:
            NotificationsSoundActivity r44 = this.this$0;
            if (r32 != r44.selectedTone) goto L16;
            NotificationsSoundActivity.m16343$$Nest$fputstartSelectedTone(r44, null);
            NotificationsSoundActivity r45 = this.this$0;
            r45.selectedTone = r45.systemTones.get(0);
            this.this$0.selectedToneChanged = true;
        L16:
            this.this$0.serverTones.remove(r32);
            this.this$0.uploadingTones.remove(r32);
            r2 = r2 + 1;
            goto L3
        L17:
            r4.getMediaDataController().ringtoneDataStore.saveTones();
            int r22 = 0;
        L19:
            if (r22 >= r0.size()) goto L24;
            TLRPC.Document r33 = (TLRPC.Document) r0.get(r22);
            TL_account.saveRingtone r46 = new TL_account.saveRingtone();
            TLRPC.TL_inputDocument r6 = new TLRPC.TL_inputDocument();
            r46.id = r6;
            r6.id = r33.id;
            r6.access_hash = r33.access_hash;
            byte[] r34 = r33.file_reference;
            r6.file_reference = r34;
            if (r34 != null) goto L23;
            r6.file_reference = new byte[0];
        L23:
            r46.unsave = true;
            this.this$0.getConnectionsManager().sendRequest(r46, new NotificationsSoundActivity$1$$ExternalSyntheticLambda2());
            r22 = r22 + 1;
            goto L19
        L24:
            NotificationsSoundActivity.m16344$$Nest$mhideActionMode(this.this$0);
            NotificationsSoundActivity.m16345$$Nest$mupdateRows(this.this$0);
            this.this$0.adapter.notifyDataSetChanged();
        }

        private /* synthetic */ void lambda$onItemClick$1(AlertDialog r1, int r2) {
            deleteSelectedMessages();
            r1.dismiss();
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r6) {
            if (r6 != (-1)) goto L10;
            boolean r62 = NotificationsSoundActivity.access$000(this.this$0).isActionModeShowed();
            NotificationsSoundActivity r5 = this.this$0;
            if (r62 == false) goto L8;
            NotificationsSoundActivity.m16344$$Nest$mhideActionMode(r5);
            return;
        L8:
            r5.finishFragment();
            return;
        L10:
            int r2 = 0;
            if (r6 != 1) goto L17;
            AlertDialog.Builder r63 = new AlertDialog.Builder(this.this$0.getParentActivity(), this.this$0.resourcesProvider);
            r63.setTitle(LocaleController.formatPluralString("DeleteTones", this.this$0.selectedTones.size(), new Object[0]));
            r63.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("DeleteTonesMessage", this.this$0.selectedTones.size(), new Object[0])));
            r63.setNegativeButton(LocaleController.getString(R.string.Cancel), new NotificationsSoundActivity$1$$ExternalSyntheticLambda0());
            r63.setPositiveButton(LocaleController.getString(R.string.Delete), new NotificationsSoundActivity$1$$ExternalSyntheticLambda1(this));
            TextView r64 = (TextView) r63.show().getButton(-1);
            if (r64 == null) goto L38;
            r64.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.this$0.resourcesProvider));
            return;
        L38:
            return;
        L17:
            if (r6 == 2) goto L19;
            return;
        L19:
            if (this.this$0.selectedTones.size() != 1) goto L23;
            Intent r65 = new Intent(this.val$context, LaunchActivity.class);
            r65.setAction("android.intent.action.SEND");
            Uri r1 = this.this$0.selectedTones.valueAt(0).getUriForShare(NotificationsSoundActivity.access$100(this.this$0));
            if (r1 == null) goto L33;
            r65.putExtra("android.intent.extra.STREAM", r1);
            this.val$context.startActivity(r65);
        L33:
            NotificationsSoundActivity.m16344$$Nest$mhideActionMode(this.this$0);
            NotificationsSoundActivity.m16345$$Nest$mupdateRows(this.this$0);
            this.this$0.adapter.notifyDataSetChanged();
            return;
        L23:
            Intent r66 = new Intent(this.val$context, LaunchActivity.class);
            r66.setAction("android.intent.action.SEND_MULTIPLE");
            ArrayList<? extends Parcelable> r12 = new ArrayList();
        L25:
            if (r2 >= this.this$0.selectedTones.size()) goto L31;
            Uri r3 = this.this$0.selectedTones.valueAt(r2).getUriForShare(NotificationsSoundActivity.access$200(this.this$0));
            if (r3 == null) goto L29;
            r12.add(r3);
        L29:
            r2 = r2 + 1;
            goto L25
        L31:
            if (r12.isEmpty() == true) goto L33;
            r66.putParcelableArrayListExtra("android.intent.extra.STREAM", r12);
            this.val$context.startActivity(r66);
            goto L33
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ NotificationsSoundActivity this$0;

        private Adapter(NotificationsSoundActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(NotificationsSoundActivity r1, NotificationsSoundActivityIA r2) {
            this(r1);
        }

        private Tone getTone(int r3) {
            NotificationsSoundActivity r2 = this.this$0;
            int r0 = r2.systemTonesStartRow;
            if (r3 >= r0) goto L5;
        L8:
            int r02 = r2.serverTonesStartRow;
            if (r3 >= r02) goto L11;
            return null;
        L11:
            if (r3 < r2.serverTonesEndRow) goto L13;
            return null;
        L13:
            return r2.serverTones.get(r3 - r02);
        L5:
            if (r3 >= r2.systemTonesEndRow) goto L8;
            return r2.systemTones.get(r3 - r0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r2) {
            if (getTone(r2) != null) goto L5;
            NotificationsSoundActivity r1 = this.this$0;
            if (r2 != r1.serverTonesHeaderRow) goto L11;
            return 1;
        L11:
            if (r2 != r1.systemTonesHeaderRow) goto L15;
            return 2;
        L15:
            if (r2 != r1.uploadRow) goto L19;
            return 3;
        L19:
            if (r2 != r1.dividerRow) goto L23;
            return 4;
        L23:
            if (r2 != r1.dividerRow2) goto L27;
            return 5;
        L27:
            throw new RuntimeException();
        L5:
            return r0.stableId;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            NotificationsSoundActivity r0 = this.this$0;
            if (r3 < r0.systemTonesStartRow) goto L9;
            if (r3 >= r0.systemTonesEndRow) goto L9;
            return 0;
        L9:
            if (r3 != r0.serverTonesHeaderRow) goto L11;
            return 1;
        L11:
            if (r3 != r0.systemTonesHeaderRow) goto L14;
            return 1;
        L14:
            if (r3 != r0.uploadRow) goto L18;
            return 2;
        L18:
            if (r3 != r0.dividerRow) goto L20;
            return 3;
        L20:
            if (r3 != r0.dividerRow2) goto L23;
            return 3;
        L23:
            return super.getItemViewType(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L5;
            return true;
        L5:
            if (r1.getItemViewType() == 2) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            int r0 = r8.getItemViewType();
            boolean r1 = false;
            if (r0 == 0) goto L18;
            if (r0 != 1) goto L6;
            HeaderCell r82 = (HeaderCell) r8.itemView;
            NotificationsSoundActivity r7 = this.this$0;
            if (r9 != r7.serverTonesHeaderRow) goto L15;
            r82.setText(LocaleController.getString(R.string.TelegramTones));
            return;
        L15:
            if (r9 != r7.systemTonesHeaderRow) goto L47;
            r82.setText(LocaleController.getString(R.string.SystemTones));
            return;
        L47:
            return;
        L6:
            if (r0 != 2) goto L49;
            CreationTextCell r83 = (CreationTextCell) r8.itemView;
            Drawable r92 = r83.getContext().getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r02 = r83.getContext().getResources().getDrawable(R.drawable.poll_add_plus);
            int r3 = Theme.getColor(Theme.key_switchTrackChecked, this.this$0.resourcesProvider);
            PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
            r92.setColorFilter(new PorterDuffColorFilter(r3, r4));
            r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck, this.this$0.resourcesProvider), r4));
            r83.setTextAndIcon(LocaleController.getString(R.string.UploadSound), new CombinedDrawable(r92, r02), false);
            return;
        L49:
            return;
        L18:
            ToneCell r84 = (ToneCell) r8.itemView;
            NotificationsSoundActivity r03 = this.this$0;
            int r32 = r03.systemTonesStartRow;
            if (r9 >= r32) goto L21;
        L23:
            Tone r04 = null;
        L24:
            NotificationsSoundActivity r33 = this.this$0;
            int r42 = r33.serverTonesStartRow;
            if (r9 >= r42) goto L27;
        L29:
            if (r04 != null) goto L31;
            return;
        L31:
            if (r84.tone != r04) goto L33;
            boolean r34 = true;
        L34:
            NotificationsSoundActivity r43 = this.this$0;
            if (r04 != r43.selectedTone) goto L37;
            boolean r5 = true;
        L39:
            if (r43.selectedTones.get(r04.stableId) == null) goto L41;
            boolean r44 = true;
        L42:
            r84.tone = r04;
            ToneCell.m16350$$Nest$fgettextView(r84).setText(r04.title);
            if (r9 == (this.this$0.systemTonesEndRow - 1)) goto L45;
            r1 = true;
        L45:
            ToneCell.m16351$$Nest$fputneedDivider(r84, r1);
            ToneCell.m16349$$Nest$fgetradioButton(r84).setChecked(r5, r34);
            ToneCell.m16348$$Nest$fgetcheckBox(r84).setChecked(r44, r34);
            return;
        L41:
            r44 = false;
            goto L42
        L37:
            r5 = false;
            goto L39
        L33:
            r34 = false;
            goto L34
        L27:
            if (r9 >= r33.serverTonesEndRow) goto L29;
            r04 = r33.serverTones.get(r9 - r42);
            goto L29
        L21:
            if (r9 >= r03.systemTonesEndRow) goto L23;
            r04 = r03.systemTones.get(r9 - r32);
            goto L24
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            Context r32 = r3.getContext();
            if (r4 != 0) goto L5;
            View r42 = new ToneCell(r32, this.this$0.resourcesProvider);
            r42.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.this$0.resourcesProvider));
            View r43 = r42;
        L12:
            r43.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r43);
        L5:
            if (r4 != 2) goto L7;
            CreationTextCell r44 = new CreationTextCell(r32, 70, this.this$0.resourcesProvider);
            r44.startPadding = 61;
            r44.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.this$0.resourcesProvider));
            r43 = r44;
            goto L12
        L7:
            if (r4 == 3) goto L9;
            View r45 = new HeaderCell(r32, this.this$0.resourcesProvider);
            r45.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.this$0.resourcesProvider));
            r43 = r45;
            goto L12
        L9:
            r43 = new ShadowSectionCell(r32, this.this$0.resourcesProvider);
            goto L12
        }
    }

    public static class Tone {
        TLRPC.Document document;
        public boolean fromServer;
        boolean isSystemDefault;
        boolean isSystemNoSound;
        int localId;
        int stableId;
        String title;
        String uri;

        private Tone() {
        }

        public /* synthetic */ Tone(NotificationsSoundActivityIA r1) {
            this();
        }

        public Uri getUriForShare(int r4) {
            if (TextUtils.isEmpty(this.uri) == false) goto L5;
            TLRPC.Document r0 = this.document;
            if (r0 == null) goto L21;
            String r1 = r0.file_name_fixed;
            String r02 = FileLoader.getDocumentExtension(r0);
            if (r02 == null) goto L25;
            String r03 = r02.toLowerCase();
            if (r1.endsWith(r03) == true) goto L13;
            r1 = r1 + "." + r03;
        L13:
            File r04 = new File(AndroidUtilities.getCacheDir(), r1);
            if (r04.exists() == true) goto L20;
            AndroidUtilities.copyFile(FileLoader.getInstance(r4).getPathToAttach(this.document), r04);     // Catch: IOException -> L17
        L17:
            e = move-exception;
            e.printStackTrace();
        L20:
            return Uri.fromFile(r04);
        L25:
            return null;
        L21:
            return null;
        L5:
            return Uri.fromFile(new File(this.uri));
        }
    }

    public static class ToneCell extends FrameLayout {
        private CheckBox2 checkBox;
        private boolean needDivider;
        private RadioButton radioButton;
        private TextView textView;
        Tone tone;

        /* renamed from: -$$Nest$fgetcheckBox, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CheckBox2 m16348$$Nest$fgetcheckBox(ToneCell r0) {
            return r0.checkBox;
        }

        /* renamed from: -$$Nest$fgetradioButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RadioButton m16349$$Nest$fgetradioButton(ToneCell r0) {
            return r0.radioButton;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m16350$$Nest$fgettextView(ToneCell r0) {
            return r0.textView;
        }

        /* renamed from: -$$Nest$fputneedDivider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16351$$Nest$fputneedDivider(ToneCell r0, boolean r1) {
            r0.needDivider = r1;
        }

        public ToneCell(Context r14, Theme.ResourcesProvider r15) {
            super(r14);
            RadioButton r0 = new RadioButton(r14);
            this.radioButton = r0;
            r0.setSize(AndroidUtilities.dp(20.0f));
            this.radioButton.setColor(Theme.getColor(Theme.key_radioBackground, r15), Theme.getColor(Theme.key_radioBackgroundChecked, r15));
            RadioButton r02 = this.radioButton;
            boolean r1 = LocaleController.isRTL;
            int r2 = 5;
            if (r1 == false) goto L5;
            int r4 = 5;
        L6:
            int r7 = r4 | 16;
            int r42 = 20;
            if (r1 == false) goto L9;
            int r5 = 0;
        L10:
            float r8 = r5;
            if (r1 == true) goto L14;
            r42 = 0;
        L14:
            addView(r02, LayoutHelper.createFrame(22, 22.0f, r7, r8, 0.0f, r42, 0.0f));
            CheckBox2 r03 = new CheckBox2(r14, 24, r15);
            this.checkBox = r03;
            r03.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            this.checkBox.setDrawUnchecked(false);
            this.checkBox.setDrawBackgroundAsArc(3);
            CheckBox2 r04 = this.checkBox;
            boolean r12 = LocaleController.isRTL;
            if (r12 == false) goto L17;
            int r43 = 5;
        L18:
            int r72 = r43 | 16;
            int r44 = 18;
            if (r12 == false) goto L21;
            int r52 = 0;
        L22:
            float r82 = r52;
            if (r12 == true) goto L26;
            r44 = 0;
        L26:
            addView(r04, LayoutHelper.createFrame(26, 26.0f, r72, r82, 0.0f, r44, 0.0f));
            this.checkBox.setChecked(true, false);
            TextView r05 = new TextView(r14);
            this.textView = r05;
            r05.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r15));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            TextView r142 = this.textView;
            if (LocaleController.isRTL == false) goto L29;
            int r152 = 5;
        L30:
            r142.setGravity(r152 | 16);
            TextView r143 = this.textView;
            boolean r153 = LocaleController.isRTL;
            if (r153 == true) goto L34;
            r2 = 3;
        L34:
            int r53 = r2 | 16;
            int r06 = 61;
            if (r153 == false) goto L37;
            int r22 = 23;
        L38:
            float r6 = r22;
            if (r153 == true) goto L42;
            r06 = 23;
        L42:
            addView(r143, LayoutHelper.createFrame(-2, -2.0f, r53, r6, 0.0f, r06, 0.0f));
            return;
        L37:
            r22 = 61;
            goto L38
        L29:
            r152 = 3;
            goto L30
        L21:
            r52 = 18;
            goto L22
        L17:
            r43 = 3;
            goto L18
        L9:
            r5 = 20;
            goto L10
        L5:
            r4 = 3;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (this.needDivider == false) goto L14;
            float r1 = 60.0f;
            if (LocaleController.isRTL == false) goto L7;
            float r0 = 0.0f;
        L8:
            float r4 = AndroidUtilities.dp(r0);
            float r5 = getHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == true) goto L12;
            r1 = 0.0f;
        L12:
            r10.drawLine(r4, r5, r02 - AndroidUtilities.dp(r1), getHeight() - 1, Theme.dividerPaint);
            return;
        L7:
            r0 = 60.0f;
            goto L8
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setClassName("android.widget.RadioButton");
            r2.setCheckable(true);
            r2.setChecked(this.radioButton.isChecked());
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: $r8$lambda$97ZcdVHkY-g223tNnbycBRIqZAU, reason: not valid java name */
    public static /* synthetic */ boolean m16342$r8$lambda$97ZcdVHkYg223tNnbycBRIqZAU(NotificationsSoundActivity r0, View r1, int r2) {
        return r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$ErfWyB3apZ72fvBrQqjwr6le79s(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$gpcS7O_45IozSlnRvnGv0GQruWw(NotificationsSoundActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$1(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fputstartSelectedTone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16343$$Nest$fputstartSelectedTone(NotificationsSoundActivity r0, Tone r1) {
        r0.startSelectedTone = r1;
    }

    /* renamed from: -$$Nest$mhideActionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16344$$Nest$mhideActionMode(NotificationsSoundActivity r0) {
        r0.hideActionMode();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16345$$Nest$mupdateRows(NotificationsSoundActivity r0) {
        r0.updateRows();
    }

    public NotificationsSoundActivity(Bundle r3, Theme.ResourcesProvider r4) {
        super(r3);
        this.serverTones = new ArrayList();
        this.systemTones = new ArrayList();
        this.uploadingTones = new ArrayList();
        this.stableIds = 100;
        this.selectedTones = new SparseArray();
        this.currentType = -1;
        this.tonesStreamType = 4;
        this.topicId = 0;
        this.resourcesProvider = r4;
    }

    public static /* synthetic */ ActionBar access$000(NotificationsSoundActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$100(NotificationsSoundActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(NotificationsSoundActivity r0) {
        return r0.currentAccount;
    }

    private void checkSelection(Tone r3) {
        if (this.selectedTones.get(r3.stableId) == null) goto L6;
        this.selectedTones.remove(r3.stableId);
    L8:
        updateActionMode();
        Adapter r2 = this.adapter;
        r2.notifyItemRangeChanged(0, r2.getItemCount());
        return;
    L6:
        if (r3.fromServer == false) goto L10;
        this.selectedTones.put(r3.stableId, r3);
        goto L8
    }

    public static String findRingtonePathByName(String r6) {
        if (r6 != null) goto L14;
        return null;
    L14:
        RingtoneManager r1 = new RingtoneManager(ApplicationLoader.applicationContext);     // Catch: Throwable -> L11
        r1.setType(2);     // Catch: Throwable -> L11
        Cursor r12 = r1.getCursor();     // Catch: Throwable -> L11
    L6:
        if (r12.moveToNext() == false) goto L13;
        String r3 = r12.getString(1);     // Catch: Throwable -> L11
        String r4 = r12.getString(2) + "/" + r12.getString(0);     // Catch: Throwable -> L11
        if (r6.equalsIgnoreCase(r3) == false) goto L6;
        return r4;
    L13:
        return null;
    L11:
        th = move-exception;
        FileLog.e(th);
        goto L13
    }

    private void hideActionMode() {
        this.selectedTones.clear();
        Adapter r0 = this.adapter;
        r0.notifyItemRangeChanged(0, r0.getItemCount());
        updateActionMode();
    }

    private /* synthetic */ void lambda$createView$1(Context r9, View r10, int r11) {
        if (r11 != this.uploadRow) goto L5;
        NotificationsSoundActivity r3 = this;
        Context r2 = r9;
        ChatAttachAlert r1 = new ChatAttachAlert(r2, r3, false, false, true, this.resourcesProvider);
        r3.chatAttachAlert = r1;
        r1.setSoundPicker();
        r3.chatAttachAlert.init();
        r3.chatAttachAlert.show();
    L7:
        if ((r10 instanceof ToneCell) == false) goto L49;
        ToneCell r102 = (ToneCell) r10;
        if (r3.actionBar.isActionModeShowed() == false) goto L11;
    L46:
        r3.checkSelection(r102.tone);
        return;
    L11:
        if (r102.tone == null) goto L46;
        Ringtone r8 = r3.lastPlayedRingtone;
        if (r8 == null) goto L48;
        r8.stop();
    L48:
        Tone r112 = r102.tone;     // Catch: Exception -> L20
        if (r112.isSystemDefault == false) goto L22;
        Ringtone r113 = RingtoneManager.getRingtone(r2.getApplicationContext(), RingtoneManager.getDefaultUri(2));     // Catch: Exception -> L20
        r113.setStreamType(4);     // Catch: Exception -> L20
        r3.lastPlayedRingtone = r113;     // Catch: Exception -> L20
        r113.play();     // Catch: Exception -> L20
    L44:
        r3.startSelectedTone = null;
        r3.selectedTone = r102.tone;
        r3.selectedToneChanged = true;
        Adapter r92 = r3.adapter;
        r92.notifyItemRangeChanged(0, r92.getItemCount());
        return;
    L22:
        String r0 = r112.uri;     // Catch: Exception -> L20
        if (r0 == null) goto L28;
        if (r112.fromServer == true) goto L28;
        Ringtone r114 = RingtoneManager.getRingtone(r2.getApplicationContext(), Uri.parse(r102.tone.uri));     // Catch: Exception -> L20
        r114.setStreamType(4);     // Catch: Exception -> L20
        r3.lastPlayedRingtone = r114;     // Catch: Exception -> L20
        r114.play();     // Catch: Exception -> L20
    L28:
        if (r112.fromServer == false) goto L44;
        if (TextUtils.isEmpty(r0) == true) goto L34;
        File r115 = new File(r102.tone.uri);     // Catch: Exception -> L20
        if (r115.exists() == false) goto L34;
    L35:
        if (r115 != null) goto L37;
        r115 = r3.getFileLoader().getPathToAttach(r102.tone.document);     // Catch: Exception -> L20
    L37:
        if (r115 != null) goto L39;
    L41:
        FileLoader r116 = r3.getFileLoader();     // Catch: Exception -> L20
        TLRPC.Document r02 = r102.tone.document;     // Catch: Exception -> L20
        r116.loadFile(r02, r02, 3, 0);     // Catch: Exception -> L20
        goto L44
    L39:
        if (r115.exists() == false) goto L41;
        Ringtone r117 = RingtoneManager.getRingtone(r2.getApplicationContext(), Uri.parse(r115.toString()));     // Catch: Exception -> L20
        r117.setStreamType(4);     // Catch: Exception -> L20
        r3.lastPlayedRingtone = r117;     // Catch: Exception -> L20
        r117.play();     // Catch: Exception -> L20
    L34:
        r115 = null;
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L44
    L49:
        return;
    L5:
        r3 = this;
        r2 = r9;
        goto L7
    }

    private /* synthetic */ boolean lambda$createView$2(View r2, int r3) {
        if ((r2 instanceof ToneCell) == false) goto L5;
        ToneCell r22 = (ToneCell) r2;
        checkSelection(r22.tone);
        r22.performHapticFeedback(0);
    L5:
        return false;
    }

    private void loadTones() {
        getMediaDataController().ringtoneDataStore.loadUserRingtones(false);
        this.serverTones.clear();
        this.systemTones.clear();
        int r0 = 0;
    L4:
        if (r0 >= getMediaDataController().ringtoneDataStore.userRingtones.size()) goto L15;
        RingtoneDataStore.CachedTone r2 = getMediaDataController().ringtoneDataStore.userRingtones.get(r0);
        Tone r5 = new Tone(null);
        int r6 = this.stableIds;
        this.stableIds = r6 + 1;
        r5.stableId = r6;
        r5.fromServer = true;
        r5.localId = r2.localId;
        TLRPC.Document r4 = r2.document;
        r5.title = r4.file_name_fixed;
        r5.document = r4;
        trimTitle(r5);
        r5.uri = r2.localUri;
        Tone r42 = this.startSelectedTone;
        if (r42 == null) goto L14;
        TLRPC.Document r43 = r42.document;
        if (r43 == null) goto L14;
        TLRPC.Document r22 = r2.document;
        if (r22 == null) goto L14;
        if (r43.id != r22.id) goto L14;
        this.startSelectedTone = null;
        this.selectedTone = r5;
    L14:
        this.serverTones.add(r5);
        r0 = r0 + 1;
        goto L4
    L15:
        RingtoneManager r02 = new RingtoneManager(ApplicationLoader.applicationContext);
        r02.setType(2);
        Cursor r03 = r02.getCursor();
        Tone r52 = new Tone(null);
        int r62 = this.stableIds;
        this.stableIds = r62 + 1;
        r52.stableId = r62;
        r52.title = LocaleController.getString(R.string.NoSound);
        r52.isSystemNoSound = true;
        this.systemTones.add(r52);
        Tone r63 = new Tone(null);
        int r7 = this.stableIds;
        this.stableIds = r7 + 1;
        r63.stableId = r7;
        r63.title = LocaleController.getString(R.string.DefaultRingtone);
        r63.isSystemDefault = true;
        this.systemTones.add(r63);
        Tone r72 = this.startSelectedTone;
        if (r72 != null) goto L18;
    L22:
        Tone r53 = this.startSelectedTone;
        if (r53 == null) goto L30;
        if (r53.document != null) goto L30;
        if (r53.uri.equals("Default") == false) goto L30;
        this.startSelectedTone = null;
        this.selectedTone = r63;
    L30:
        if (r03.moveToNext() == false) goto L40;
        String r54 = r03.getString(1);
        String r73 = r03.getString(2) + "/" + r03.getString(0);
        Tone r8 = new Tone(null);
        int r9 = this.stableIds;
        this.stableIds = r9 + 1;
        r8.stableId = r9;
        r8.title = r54;
        r8.uri = r73;
        Tone r55 = this.startSelectedTone;
        if (r55 == null) goto L38;
        if (r55.document != null) goto L38;
        if (r55.uri.equals(r73) == false) goto L38;
        this.startSelectedTone = null;
        this.selectedTone = r8;
    L38:
        this.systemTones.add(r8);
        goto L30
    L40:
        if (getMediaDataController().ringtoneDataStore.isLoaded() == true) goto L42;
    L44:
        updateRows();
        return;
    L42:
        if (this.selectedTone != null) goto L44;
        this.selectedTone = r63;
        this.selectedToneChanged = true;
        goto L44
    L18:
        if (r72.document != null) goto L22;
        if (r72.uri.equals("NoSound") == false) goto L22;
        this.startSelectedTone = null;
        this.selectedTone = r52;
        goto L22
    }

    public static String trimTitle(TLRPC.Document r2, String r3) {
        if (r3 == null) goto L7;
        int r0 = r3.lastIndexOf(46);
        if (r0 == (-1)) goto L7;
        r3 = r3.substring(0, r0);
    L7:
        if (TextUtils.isEmpty(r3) == false) goto L11;
        if (r2 == null) goto L11;
        return LocaleController.formatString("SoundNameEmpty", R.string.SoundNameEmpty, new Object[]{LocaleController.formatDateChat(r2.date, true)});
    L11:
        return r3;
    }

    private void trimTitle(Tone r2) {
        r2.title = trimTitle(r2.document, r2.title);
    }

    private void updateActionMode() {
        if (this.selectedTones.size() <= 0) goto L6;
        this.selectedTonesCountTextView.setNumber(this.selectedTones.size(), this.actionBar.isActionModeShowed());
        this.actionBar.showActionMode();
        return;
    L6:
        this.actionBar.hideActionMode();
    }

    private void updateRows() {
        this.serverTonesStartRow = -1;
        this.serverTonesEndRow = -1;
        this.uploadRow = -1;
        this.dividerRow = -1;
        this.systemTonesHeaderRow = -1;
        this.systemTonesStartRow = -1;
        this.systemTonesEndRow = -1;
        this.rowCount = 1;
        this.serverTonesHeaderRow = 0;
        if (this.serverTones.isEmpty() == true) goto L5;
        int r0 = this.rowCount;
        this.serverTonesStartRow = r0;
        int r02 = r0 + this.serverTones.size();
        this.rowCount = r02;
        this.serverTonesEndRow = r02;
    L5:
        int r03 = this.rowCount;
        int r1 = r03 + 1;
        this.uploadRow = r03;
        this.rowCount = r03 + 2;
        this.dividerRow = r1;
        if (this.systemTones.isEmpty() == true) goto L8;
        int r04 = this.rowCount;
        int r12 = r04 + 1;
        this.rowCount = r12;
        this.systemTonesHeaderRow = r04;
        this.systemTonesStartRow = r12;
        int r13 = r12 + this.systemTones.size();
        this.rowCount = r13;
        this.systemTonesEndRow = r13;
    L8:
        int r05 = this.rowCount;
        this.rowCount = r05 + 1;
        this.dividerRow2 = r05;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r19) {
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_avatar_actionBarSelectorBlue, this.resourcesProvider), false);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_actionBarDefaultIcon, this.resourcesProvider), false);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this, r19));
        if (this.dialogId != 0) goto L19;
        int r2 = this.currentType;
        if (r2 != 1) goto L7;
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsSoundPrivate));
    L34:
        ActionBarMenu r22 = this.actionBar.createActionMode();
        NumberTextView r5 = new NumberTextView(r22.getContext());
        this.selectedTonesCountTextView = r5;
        r5.setTextSize(18);
        this.selectedTonesCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedTonesCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon, this.resourcesProvider));
        r22.addView(this.selectedTonesCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 72, 0, 0, 0));
        this.selectedTonesCountTextView.setOnTouchListener(new NotificationsSoundActivity$$ExternalSyntheticLambda0());
        r22.addItemWithWidth(2, R.drawable.msg_forward, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.ShareFile));
        r22.addItemWithWidth(1, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete));
        FrameLayout r23 = new FrameLayout(r19);
        this.fragmentView = r23;
        r23.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        RecyclerListView r52 = new RecyclerListView(r19);
        this.listView = r52;
        r52.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        r23.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        Adapter r24 = new Adapter(this, null);
        this.adapter = r24;
        r24.setHasStableIds(true);
        this.listView.setAdapter(this.adapter);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        this.listView.setLayoutManager(new LinearLayoutManager(r19));
        this.listView.setOnItemClickListener(new NotificationsSoundActivity$$ExternalSyntheticLambda1(this, r19));
        this.listView.setOnItemLongClickListener(new NotificationsSoundActivity$$ExternalSyntheticLambda2(this));
        loadTones();
        updateRows();
        return this.fragmentView;
    L7:
        if (r2 != 0) goto L9;
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsSoundGroup));
        goto L34
    L9:
        if (r2 != 2) goto L12;
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsSoundChannels));
        goto L34
    L12:
        if (r2 != 3) goto L15;
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsSoundStories));
        goto L34
    L15:
        if (r2 != 5) goto L17;
    L18:
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsSoundReactions));
        goto L34
    L17:
        if (r2 != 4) goto L34;
    L19:
        ChatAvatarContainer r25 = new ChatAvatarContainer(r19, null, false, this.resourcesProvider);
        this.avatarContainer = r25;
        r25.setOccupyStatusBar(!AndroidUtilities.isTablet());
        ActionBar r26 = this.actionBar;
        ChatAvatarContainer r9 = this.avatarContainer;
        if (this.inPreviewMode == true) goto L23;
        float r10 = 56.0f;
    L24:
        r26.addView(r9, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r10, 0.0f, 40.0f, 0.0f));
        if (this.dialogId < 0) goto L27;
        TLRPC.User r27 = getMessagesController().getUser(Long.valueOf(this.dialogId));
        if (r27 == null) goto L33;
        this.avatarContainer.setUserAvatar(r27);
        this.avatarContainer.setTitle(ContactsController.formatName(r27.first_name, r27.last_name));
    L33:
        this.avatarContainer.setSubtitle(LocaleController.getString(R.string.NotificationsSound));
        goto L34
    L27:
        if (this.topicId == 0) goto L29;
        TLRPC.TL_forumTopic r28 = getMessagesController().getTopicsController().findTopic(-this.dialogId, this.topicId);
        ForumUtilities.setTopicIcon(this.avatarContainer.getAvatarImageView(), r28, false, true, this.resourcesProvider);
        this.avatarContainer.setTitle(r28.title);
        goto L33
    L29:
        TLRPC.Chat r29 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        this.avatarContainer.setChatAvatar(r29);
        this.avatarContainer.setTitle(r29.title);
        goto L33
    L23:
        r10 = 0.0f;
        goto L24
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 != NotificationCenter.onUserRingtonesUpdated) goto L47;
        HashMap r82 = new HashMap();
        int r102 = 0;
    L5:
        int r0 = this.serverTones.size();
        ArrayList<Tone> r1 = this.serverTones;
        if (r102 >= r0) goto L8;
        r82.put(Integer.valueOf(r1.get(r102).localId), this.serverTones.get(r102));
        r102 = r102 + 1;
        goto L5
    L8:
        r1.clear();
        int r103 = 0;
    L10:
        if (r103 >= getMediaDataController().ringtoneDataStore.userRingtones.size()) goto L32;
        RingtoneDataStore.CachedTone r02 = getMediaDataController().ringtoneDataStore.userRingtones.get(r103);
        Tone r2 = new Tone(null);
        Tone r3 = (Tone) r82.get(Integer.valueOf(r02.localId));
        if (r3 != null) goto L14;
        int r32 = this.stableIds;
        this.stableIds = r32 + 1;
        r2.stableId = r32;
    L18:
        r2.fromServer = true;
        r2.localId = r02.localId;
        TLRPC.Document r33 = r02.document;
        if (r33 == null) goto L21;
        r2.title = r33.file_name_fixed;
    L22:
        r2.document = r02.document;
        trimTitle(r2);
        r2.uri = r02.localUri;
        Tone r34 = this.startSelectedTone;
        if (r34 == null) goto L31;
        TLRPC.Document r35 = r34.document;
        if (r35 == null) goto L31;
        TLRPC.Document r03 = r02.document;
        if (r03 == null) goto L31;
        if (r35.id != r03.id) goto L31;
        this.startSelectedTone = null;
        this.selectedTone = r2;
    L31:
        this.serverTones.add(r2);
        r103 = r103 + 1;
        goto L10
    L21:
        r2.title = new File(r02.localUri).getName();
        goto L22
    L14:
        if (r3 != this.selectedTone) goto L16;
        this.selectedTone = r2;
    L16:
        r2.stableId = r3.stableId;
        goto L18
    L32:
        updateRows();
        this.adapter.notifyDataSetChanged();
        if (getMediaDataController().ringtoneDataStore.isLoaded() == true) goto L35;
        return;
    L35:
        if (this.selectedTone == null) goto L37;
        return;
    L37:
        if (this.systemTones.size() <= 0) goto L50;
        this.startSelectedTone = null;
        this.selectedTone = this.systemTones.get(0);
        return;
    L50:
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
    public void didSelectFiles(ArrayList<String> r1, String r2, ArrayList<TLRPC.MessageEntity> r3, ArrayList<MessageObject> r4, boolean r5, int r6, int r7, long r8, boolean r10, long r11) {
        int r32 = 0;
    L4:
        if (r32 >= r1.size()) goto L6;
        getMediaDataController().uploadRingtone(r1.get(r32));
        r32 = r32 + 1;
        goto L4
    L6:
        getNotificationCenter().postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r11, int r12, Intent r13) {
        if (r11 != 21) goto L57;
        if (r13 == null) goto L60;
        boolean r3 = true;
        boolean r4 = false;
        if (r13.getData() == null) goto L28;
        String r112 = AndroidUtilities.getPath(r13.getData());
        if (r112 != null) goto L11;
    L47:
        if (r4 == false) goto L58;
        ChatAttachAlert r10 = this.chatAttachAlert;
        if (r10 == null) goto L59;
        r10.dismiss();
        return;
    L59:
        return;
    L58:
        return;
    L11:
        if (r112.startsWith("content://") == false) goto L13;
        r112 = MediaController.copyFileToCache(r13.getData(), HlsSegmentFormat.MP3);
    L13:
        File r122 = new File(r112);
        ChatAttachAlert r132 = this.chatAttachAlert;
        if (r132 == null) goto L19;
        if (r132.getDocumentLayout().isRingtone(r122) == true) goto L24;
    L23:
        r3 = false;
    L24:
        if (r3 == false) goto L26;
        getMediaDataController().uploadRingtone(r112);
        getNotificationCenter().postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
    L26:
        r4 = r3;
        goto L47
    L19:
        if (r122.exists() == false) goto L23;
        if (r122.length() <= 0) goto L23;
    L28:
        if (r13.getClipData() == null) goto L47;
        ClipData r113 = r13.getClipData();
        int r133 = 0;
        boolean r5 = false;
    L31:
        if (r133 >= r113.getItemCount()) goto L46;
        Uri r6 = r113.getItemAt(r133).getUri();
        String r7 = r6.toString();
        if (r7.startsWith("content://") == false) goto L35;
        r7 = MediaController.copyFileToCache(r6, HlsSegmentFormat.MP3);
    L35:
        File r62 = new File(r7);
        ChatAttachAlert r8 = this.chatAttachAlert;
        if (r8 == null) goto L41;
        if (r8.getDocumentLayout().isRingtone(r62) == false) goto L45;
    L44:
        getMediaDataController().uploadRingtone(r7);
        getNotificationCenter().postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
        r5 = true;
    L45:
        r133 = r133 + 1;
        goto L31
    L41:
        if (r62.exists() == false) goto L45;
        if (r62.length() <= 0) goto L45;
    L46:
        r4 = r5;
        goto L47
    L60:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        if (getArguments() == null) goto L5;
        this.dialogId = getArguments().getLong("dialog_id", 0);
        this.topicId = getArguments().getLong("topic_id", 0);
        this.currentType = getArguments().getInt("type", -1);
    L5:
        long r3 = this.dialogId;
        if (r3 == 0) goto L8;
        String r0 = NotificationsController.getSharedPrefKey(r3, this.topicId);
        String r32 = "sound_document_id_" + r0;
        String r02 = "sound_path_" + r0;
    L27:
        SharedPreferences r4 = getNotificationsSettings();
        long r5 = r4.getLong(r32, 0);
        String r03 = r4.getString(r02, "NoSound");
        Tone r33 = new Tone(null);
        this.startSelectedTone = r33;
        if (r5 == 0) goto L30;
        r33.document = new TLRPC.TL_document();
        this.startSelectedTone.document.id = r5;
    L32:
        return super.onFragmentCreate();
    L30:
        r33.uri = r03;
        goto L32
    L8:
        int r04 = this.currentType;
        if (r04 != 1) goto L11;
        r02 = "GlobalSoundPath";
        r32 = "GlobalSoundDocId";
        goto L27
    L11:
        if (r04 != 0) goto L14;
        r02 = "GroupSoundPath";
        r32 = "GroupSoundDocId";
        goto L27
    L14:
        if (r04 != 2) goto L17;
        r02 = "ChannelSoundPath";
        r32 = "ChannelSoundDocId";
        goto L27
    L17:
        if (r04 != 3) goto L20;
        r02 = "StoriesSoundPath";
        r32 = "StoriesSoundDocId";
        goto L27
    L20:
        if (r04 != 4) goto L22;
    L26:
        r02 = "ReactionSoundPath";
        r32 = "ReactionSoundDocId";
        goto L27
    L22:
        if (r04 == 5) goto L26;
        GlShader$$ExternalSyntheticBUOutline1.m("Unsupported type");
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.selectedTone != null) goto L5;
        return;
    L5:
        if (this.selectedToneChanged == false) goto L47;
        SharedPreferences.Editor r0 = getNotificationsSettings().edit();
        if (this.dialogId == 0) goto L9;
        String r1 = "sound_" + NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        String r5 = "sound_path_" + NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        String r6 = "sound_document_id_" + NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        r0.putBoolean("sound_enabled_" + NotificationsController.getSharedPrefKey(this.dialogId, this.topicId), true);
    L28:
        Tone r2 = this.selectedTone;
        if (r2.fromServer == false) goto L34;
        TLRPC.Document r7 = r2.document;
        if (r7 == null) goto L34;
        r0.putLong(r6, r7.id);
        r0.putString(r1, this.selectedTone.title);
        r0.putString(r5, "NoSound");
    L40:
        r0.apply();
        if (this.dialogId == 0) goto L44;
        getNotificationsController().updateServerNotificationsSettings(this.dialogId, this.topicId);
        return;
    L44:
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
        getNotificationCenter().postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
        return;
    L34:
        if (r2.uri == null) goto L37;
        r0.putString(r1, r2.title);
        r0.putString(r5, this.selectedTone.uri);
        r0.remove(r6);
        goto L40
    L37:
        if (r2.isSystemDefault == false) goto L39;
        r0.putString(r1, "Default");
        r0.putString(r5, "Default");
        r0.remove(r6);
        goto L40
    L39:
        r0.putString(r1, "NoSound");
        r0.putString(r5, "NoSound");
        r0.remove(r6);
        goto L40
    L9:
        int r12 = this.currentType;
        if (r12 != 1) goto L12;
        r1 = "GlobalSound";
        r5 = "GlobalSoundPath";
        r6 = "GlobalSoundDocId";
        goto L28
    L12:
        if (r12 != 0) goto L15;
        r1 = "GroupSound";
        r5 = "GroupSoundPath";
        r6 = "GroupSoundDocId";
        goto L28
    L15:
        if (r12 != 2) goto L18;
        r1 = "ChannelSound";
        r5 = "ChannelSoundPath";
        r6 = "ChannelSoundDocId";
        goto L28
    L18:
        if (r12 != 3) goto L21;
        r1 = "StoriesSound";
        r5 = "StoriesSoundPath";
        r6 = "StoriesSoundDocId";
        goto L28
    L21:
        if (r12 != 5) goto L23;
    L27:
        r1 = "ReactionSound";
        r5 = "ReactionSoundPath";
        r6 = "ReactionSoundDocId";
        goto L28
    L23:
        if (r12 == 4) goto L27;
        GlShader$$ExternalSyntheticBUOutline1.m("Unsupported type");
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setClipToPadding(false);
        this.listView.setPadding(0, 0, 0, r4);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        getNotificationCenter().removeObserver(this, NotificationCenter.onUserRingtonesUpdated);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        getNotificationCenter().addObserver(this, NotificationCenter.onUserRingtonesUpdated);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
    public void startDocumentSelectActivity() {
        Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
        r0.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);     // Catch: Exception -> L4
        r0.setType("audio/mpeg");     // Catch: Exception -> L4
        startActivityForResult(r0, 21);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }
}
