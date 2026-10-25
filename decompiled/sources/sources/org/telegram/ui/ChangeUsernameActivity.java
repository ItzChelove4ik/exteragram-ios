package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;

/* loaded from: classes6.dex */
public class ChangeUsernameActivity extends BaseFragment {
    private static Paint dragPaint;
    private static Paint linkBackgroundActive;
    private static Paint linkBackgroundInactive;
    private Adapter adapter;
    private long botId;
    private int checkReqId;
    private Runnable checkRunnable;
    private View doneButton;
    private UsernameCell editableUsernameCell;
    private UsernameHelpCell helpCell;
    private boolean ignoreCheck;
    private InputCell inputCell;
    private ItemTouchHelper itemTouchHelper;
    private String lastCheckName;
    private boolean lastNameAvailable;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private ArrayList<String> loadingUsernames;
    private boolean needReorder;
    private ArrayList<TLRPC.TL_username> notEditableUsernames;
    private LinkSpanDrawable.LinksTextView statusTextView;
    private String username;
    private ArrayList<TLRPC.TL_username> usernames;

    /* renamed from: org.telegram.ui.ChangeUsernameActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChangeUsernameActivity this$0;

        public AnonymousClass1(ChangeUsernameActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r2 != 1) goto L10;
            ChangeUsernameActivity.m7166$$Nest$msendReorder(this.this$0);
            ChangeUsernameActivity.m7165$$Nest$msaveName(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChangeUsernameActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements RecyclerListView.OnItemClickListener {
        final /* synthetic */ ChangeUsernameActivity this$0;

        public static /* synthetic */ void $r8$lambda$C_NFFtMcIqXgs_PPt7G0oCrwrxg(AnonymousClass2 r0, String r1, int r2, boolean r3, TLRPC.TL_username r4, boolean r5, TLObject r6, TLRPC.TL_error r7) {
            r0.lambda$onItemClick$2(r1, r2, r3, r4, r5, r6, r7);
        }

        public static /* synthetic */ void $r8$lambda$FPNZwYSXILItJ6y6yJhiHC7Mg7s(AnonymousClass2 r0, TLRPC.TL_username r1, int r2, View r3, AlertDialog r4, int r5) {
            r0.lambda$onItemClick$3(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$JOQ_WjjLFGFTiitgmkwgopLUnhY(AlertDialog r0, int r1) {
            r0.dismiss();
        }

        public static /* synthetic */ void $r8$lambda$bQC9jpnxIMDNbFAsaaGYNf1k8Wo(AnonymousClass2 r0, TLRPC.TL_username r1, boolean r2, AlertDialog r3, int r4) {
            r0.lambda$onItemClick$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$r1qTc8f5yizEwwP9pO1KFSbb1Aw(AnonymousClass2 r0, String r1, TLObject r2, int r3, boolean r4, TLRPC.TL_error r5, TLRPC.TL_username r6, boolean r7) {
            r0.lambda$onItemClick$1(r1, r2, r3, r4, r5, r6, r7);
        }

        public AnonymousClass2(ChangeUsernameActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(TLRPC.TL_username r1, boolean r2, AlertDialog r3, int r4) {
            this.this$0.toggleUsername(r1, r2, true);
        }

        private /* synthetic */ void lambda$onItemClick$1(String r2, TLObject r3, int r4, boolean r5, TLRPC.TL_error r6, TLRPC.TL_username r7, boolean r8) {
            ChangeUsernameActivity.m7152$$Nest$fgetloadingUsernames(this.this$0).remove(r2);
            if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L5;
            this.this$0.toggleUsername(r4, r5);
        L10:
            TLRPC.User r22 = MessagesController.getInstance(ChangeUsernameActivity.access$100(this.this$0)).getUser(Long.valueOf(ChangeUsernameActivity.m7164$$Nest$mgetUserId(this.this$0)));
            this.this$0.getMessagesController().updateUsernameActiveness(r22, r7.username, r7.active);
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0) != 0) goto L13;
            return;
        L13:
            if (ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0) == null) goto L35;
            ArrayList r42 = ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0);
            int r52 = r42.size();
            int r72 = 0;
        L15:
            if (r72 >= r52) goto L19;
            Object r82 = r42.get(r72);
            r72 = r72 + 1;
            if (((TLRPC.TL_username) r82).active == false) goto L15;
            return;
        L19:
            ArrayList r43 = ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0);
            int r53 = r43.size();
            int r73 = 0;
        L20:
            if (r73 >= r53) goto L24;
            Object r83 = r43.get(r73);
            r73 = r73 + 1;
            TLRPC.TL_username r84 = (TLRPC.TL_username) r83;
            if (r84.editable == false) goto L20;
        L25:
            if (r84 == null) goto L36;
            this.this$0.toggleUsername(r84, true, false);
            this.this$0.getMessagesController().updateUsernameActiveness(r22, r84.username, r84.active);
            return;
        L36:
            return;
        L24:
            r84 = null;
            goto L25
        L35:
            return;
        L5:
            if (r6 != null) goto L7;
        L9:
            this.this$0.toggleUsername(r7, r8, true);
            goto L10
        L7:
            if ("USERNAMES_ACTIVE_TOO_MUCH".equals(r6.text) == false) goto L9;
            r7.active = r5;
            this.this$0.toggleUsername(r4, r5);
            new AlertDialog.Builder(this.this$0.getContext(), this.this$0.getResourceProvider()).setTitle(LocaleController.getString(R.string.UsernameActivateErrorTitle)).setMessage(LocaleController.getString(R.string.UsernameActivateErrorMessage)).setPositiveButton(LocaleController.getString(R.string.OK), new ChangeUsernameActivity$2$$ExternalSyntheticLambda4(this, r7, r8)).show();
            goto L10
        }

        private /* synthetic */ void lambda$onItemClick$2(String r10, int r11, boolean r12, TLRPC.TL_username r13, boolean r14, TLObject r15, TLRPC.TL_error r16) {
            AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$2$$ExternalSyntheticLambda3(this, r10, r15, r11, r12, r16, r13, r14));
        }

        /* JADX WARN: Multi-variable type inference failed */
        private /* synthetic */ void lambda$onItemClick$3(TLRPC.TL_username r8, int r9, View r10, AlertDialog r11, int r12) {
            boolean r6 = r8.active;
            String r2 = r8.username;
            boolean r4 = !r6;
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0) != 0) goto L5;
            TL_account.toggleUsername r112 = new TL_account.toggleUsername();
            r112.username = r2;
            r112.active = r4;
            TL_bots.toggleUsername r113 = r112;
        L6:
            this.this$0.getConnectionsManager().sendRequest(r113, new ChangeUsernameActivity$2$$ExternalSyntheticLambda2(this, r2, r9, r4, r8, r6));
            ChangeUsernameActivity.m7152$$Nest$fgetloadingUsernames(this.this$0).add(r8.username);
            ((UsernameCell) r10).setLoading(true);
            return;
        L5:
            TL_bots.toggleUsername r114 = new TL_bots.toggleUsername();
            r114.bot = MessagesController.getInstance(ChangeUsernameActivity.access$000(this.this$0)).getInputUser(ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0));
            r114.username = r2;
            r114.active = r4;
            r113 = r114;
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
        public void onItemClick(View r8, int r9) {
            if ((r8 instanceof UsernameCell) == false) goto L33;
            UsernameCell r0 = (UsernameCell) r8;
            TLRPC.TL_username r2 = r0.currentUsername;
            if (r2 != null) goto L7;
            return;
        L7:
            if (r0.loading == false) goto L10;
            return;
        L10:
            if (r2.editable == true) goto L12;
        L15:
            AlertDialog.Builder r02 = new AlertDialog.Builder(this.this$0.getContext(), this.this$0.getResourceProvider());
            if (r2.active == false) goto L19;
            int r1 = R.string.UsernameDeactivateLink;
        L20:
            AlertDialog.Builder r03 = r02.setTitle(LocaleController.getString(r1));
            if (r2.active == false) goto L24;
            int r12 = R.string.UsernameDeactivateLinkProfileMessage;
        L25:
            AlertDialog.Builder r04 = r03.setMessage(LocaleController.getString(r12));
            if (r2.active == false) goto L29;
            int r13 = R.string.Hide;
        L28:
            String r14 = LocaleController.getString(r13);
            r04.setPositiveButton(r14, new ChangeUsernameActivity$2$$ExternalSyntheticLambda0(this, r2, r9, r8)).setNegativeButton(LocaleController.getString(R.string.Cancel), new ChangeUsernameActivity$2$$ExternalSyntheticLambda1()).show();
            return;
        L29:
            r13 = R.string.Show;
            goto L28
        L24:
            r12 = R.string.UsernameActivateLinkProfileMessage;
            goto L25
        L19:
            r1 = R.string.UsernameActivateLink;
            goto L20
        L12:
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0) != 0) goto L15;
            ChangeUsernameActivity.m7151$$Nest$fgetlistView(this.this$0).smoothScrollToPosition(0);
            ChangeUsernameActivity.m7163$$Nest$mfocusUsernameField(this.this$0, true);
            return;
        L33:
            if ((r8 instanceof InputCell) == false) goto L37;
            ChangeUsernameActivity.m7163$$Nest$mfocusUsernameField(this.this$0, true);
            return;
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ ChangeUsernameActivity this$0;

        /* renamed from: org.telegram.ui.ChangeUsernameActivity$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends UsernameCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$1 = r1;
                super(r2, r3);
                this.isProfile = true;
            }

            @Override // org.telegram.ui.ChangeUsernameActivity.UsernameCell
            public String getUsernameEditable() {
                return ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0);
            }
        }

        private Adapter(ChangeUsernameActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(ChangeUsernameActivity r1, ChangeUsernameActivityIA r2) {
            this(r1);
        }

        private void swapListElements(List<TLRPC.TL_username> r2, int r3, int r4) {
            TLRPC.TL_username r1 = r2.get(r3);
            r2.set(r3, r2.get(r4));
            r2.set(r4, r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            updateSections();
            if (ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size() <= 0) goto L5;
            int r1 = ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size() + 2;
        L7:
            return r1 + 3;
        L5:
            r1 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r5) {
            if (r5 != 0) goto L6;
            return 0;
        L6:
            if (r5 != 1) goto L9;
            return 3;
        L9:
            if (r5 != 2) goto L11;
            return 1;
        L11:
            if (r5 != 3) goto L14;
            return 0;
        L14:
            if (r5 == (getItemCount() - 1)) goto L17;
            return 4;
        L17:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 4) goto L6;
            return true;
        L6:
            return false;
        }

        public void moveElement(int r4, int r5) {
            int r0 = r4 - 4;
            int r1 = r5 - 4;
            if (r0 < ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size()) goto L5;
            return;
        L5:
            if (r1 >= ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size()) goto L13;
            ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).add(r1, (TLRPC.TL_username) ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).remove(r0));
            notifyItemMoved(r4, r5);
            int r42 = 0;
        L9:
            if (r42 >= ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size()) goto L14;
            notifyItemChanged(r42 + 4);
            r42 = r42 + 1;
            goto L9
        L14:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
            int r0 = r12.getItemViewType();
            if (r0 != 0) goto L5;
            HeaderCell r122 = (HeaderCell) r12.itemView;
            if (r13 == 0) goto L34;
            int r11 = R.string.UsernamesProfileHeader;
        L39:
            r122.setText(LocaleController.getString(r11));
            return;
        L34:
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0) == 0) goto L36;
            r11 = R.string.BotSetPublicLinkHeader;
            goto L39
        L36:
            r11 = R.string.SetUsernameHeader;
            goto L39
        L5:
            if (r0 != 2) goto L7;
            TextInfoPrivacyCell r123 = (TextInfoPrivacyCell) r12.itemView;
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0) == 0) goto L28;
            int r112 = R.string.BotUsernamesHelp;
        L29:
            r123.setText(LocaleController.getString(r112));
            return;
        L28:
            r112 = R.string.UsernamesProfileHelp;
            goto L29
        L7:
            if (r0 != 3) goto L9;
            ChangeUsernameActivity.m7157$$Nest$fputignoreCheck(this.this$0, true);
            ChangeUsernameActivity r132 = this.this$0;
            InputCell r124 = (InputCell) r12.itemView;
            ChangeUsernameActivity.m7158$$Nest$fputinputCell(r132, r124);
            r124.field.setText(ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$0));
            ChangeUsernameActivity.m7157$$Nest$fputignoreCheck(this.this$0, false);
            return;
        L9:
            if (r0 == 4) goto L11;
            return;
        L11:
            TLRPC.TL_username r6 = (TLRPC.TL_username) ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).get(r13 - 4);
            UsernameCell r5 = (UsernameCell) r12.itemView;
            boolean r125 = r6.editable;
            ChangeUsernameActivity r02 = this.this$0;
            if (r125 == false) goto L15;
            ChangeUsernameActivity.m7155$$Nest$fputeditableUsernameCell(r02, r5);
        L18:
            if (r13 >= (getItemCount() - 2)) goto L20;
            boolean r7 = true;
        L21:
            r5.set(r6, r7, false, ChangeUsernameActivity.m7147$$Nest$fgetbotId(this.this$0));
            return;
        L20:
            r7 = false;
            goto L21
        L15:
            if (ChangeUsernameActivity.m7149$$Nest$fgeteditableUsernameCell(r02) != r5) goto L18;
            ChangeUsernameActivity.m7155$$Nest$fputeditableUsernameCell(this.this$0, null);
            goto L18
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 0) goto L22;
            if (r4 != 1) goto L6;
            ChangeUsernameActivity r2 = this.this$0;
            UsernameHelpCell r32 = new UsernameHelpCell(r2, r2.getContext());
            r32.setTag(-33024);
            return new RecyclerListView.Holder(r32);
        L6:
            if (r4 == 2) goto L18;
            if (r4 != 3) goto L10;
            ChangeUsernameActivity r22 = this.this$0;
            return new RecyclerListView.Holder(new InputCell(r22, r22.getContext()));
        L10:
            if (r4 == 4) goto L14;
            return null;
        L14:
            return new RecyclerListView.Holder(new AnonymousClass1(this, this.this$0.getContext(), this.this$0.getResourceProvider()));
        L18:
            return new RecyclerListView.Holder(new TextInfoPrivacyCell(this.this$0.getContext()));
        L22:
            return new RecyclerListView.Holder(new HeaderCell(this.this$0.getContext()));
        }

        public void swapElements(int r7, int r8) {
            int r2 = r7 - 4;
            int r3 = r8 - 4;
            if (r2 < ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size()) goto L5;
            return;
        L5:
            if (r3 >= ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size()) goto L16;
            if (r7 == r8) goto L9;
            ChangeUsernameActivity.m7159$$Nest$fputneedReorder(this.this$0, true);
        L9:
            swapListElements(ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0), r2, r3);
            notifyItemMoved(r7, r8);
            int r22 = ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size() + 3;
            if (r7 == r22) goto L12;
            if (r8 == r22) goto L12;
            return;
        L12:
            notifyItemChanged(r7, 3);
            notifyItemChanged(r8, 3);
            return;
        }

        public void updateSections() {
            if (ChangeUsernameActivity.m7151$$Nest$fgetlistView(this.this$0) == null) goto L18;
            ArrayList<Long> r0 = ChangeUsernameActivity.m7151$$Nest$fgetlistView(this.this$0).forcedSections;
            ChangeUsernameActivity r1 = this.this$0;
            if (r0 == null) goto L8;
            ChangeUsernameActivity.m7151$$Nest$fgetlistView(r1).forcedSections.clear();
        L10:
            if (ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size() > 0) goto L12;
            return;
        L12:
            if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L14;
            int r02 = 4;
        L15:
            ChangeUsernameActivity.m7151$$Nest$fgetlistView(this.this$0).forcedSections.add(Long.valueOf(AndroidUtilities.pack(r02, ChangeUsernameActivity.m7154$$Nest$fgetusernames(this.this$0).size() + 3)));
            return;
        L14:
            r02 = 3;
            goto L15
        L8:
            ChangeUsernameActivity.m7151$$Nest$fgetlistView(r1).forcedSections = new ArrayList();
            goto L10
        }
    }

    public class InputCell extends FrameLayout {
        public EditTextBoldCursor field;
        final /* synthetic */ ChangeUsernameActivity this$0;
        public TextView tme;

        /* renamed from: org.telegram.ui.ChangeUsernameActivity$InputCell$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            final /* synthetic */ InputCell this$1;
            final /* synthetic */ ChangeUsernameActivity val$this$0;

            public AnonymousClass1(InputCell r1, ChangeUsernameActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            private void updateUsernameCell(String r2) {
                if (ChangeUsernameActivity.m7149$$Nest$fgeteditableUsernameCell(this.this$1.this$0) == null) goto L7;
                if (r2 == null) goto L8;
                ChangeUsernameActivity.m7149$$Nest$fgeteditableUsernameCell(this.this$1.this$0).updateUsername(ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0));
                return;
            L8:
                return;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r4) {
                if (ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0).startsWith("@") == false) goto L6;
                ChangeUsernameActivity r42 = this.this$1.this$0;
                ChangeUsernameActivity.m7161$$Nest$fputusername(r42, ChangeUsernameActivity.m7153$$Nest$fgetusername(r42).substring(1));
            L6:
                if (ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0).length() <= 0) goto L11;
                String r43 = "https://" + MessagesController.getInstance(ChangeUsernameActivity.access$200(this.this$1.this$0)).linkPrefix + "/" + ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0);
                String r0 = LocaleController.formatString("UsernameHelpLink", R.string.UsernameHelpLink, new Object[]{r43});
                int r1 = r0.indexOf(r43);
                SpannableStringBuilder r2 = new SpannableStringBuilder(r0);
                if (r1 < 0) goto L12;
                r2.setSpan(new LinkSpan(this.this$1.this$0, r43), r1, r43.length() + r1, 33);
                return;
            L12:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                String r22 = ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0);
                ChangeUsernameActivity r32 = this.this$1.this$0;
                if (r1 != null) goto L5;
                String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            L6:
                ChangeUsernameActivity.m7161$$Nest$fputusername(r32, r12);
                updateUsernameCell(r22);
                return;
            L5:
                r12 = r1.toString();
                goto L6
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
                String r22 = ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$1.this$0);
                ChangeUsernameActivity r32 = this.this$1.this$0;
                if (r1 != null) goto L5;
                String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            L6:
                ChangeUsernameActivity.m7161$$Nest$fputusername(r32, r12);
                updateUsernameCell(r22);
                if (ChangeUsernameActivity.m7150$$Nest$fgetignoreCheck(this.this$1.this$0) == false) goto L9;
                return;
            L9:
                ChangeUsernameActivity r0 = this.this$1.this$0;
                ChangeUsernameActivity.m7162$$Nest$mcheckUserName(r0, ChangeUsernameActivity.m7153$$Nest$fgetusername(r0), false);
                return;
            L5:
                r12 = r1.toString();
                goto L6
            }
        }

        public static /* synthetic */ boolean $r8$lambda$Sdo0phiegMh700iIicefk4sgbgs(InputCell r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$0(r1, r2, r3);
        }

        public InputCell(ChangeUsernameActivity r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            LinearLayout r142 = new LinearLayout(getContext());
            r142.setOrientation(0);
            EditTextBoldCursor r1 = new EditTextBoldCursor(getContext());
            this.field = r1;
            r1.setTextSize(1, 17.0f);
            this.field.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
            EditTextBoldCursor r12 = this.field;
            int r4 = Theme.key_windowBackgroundWhiteBlackText;
            r12.setTextColor(Theme.getColor(r4));
            this.field.setBackgroundDrawable(null);
            this.field.setMaxLines(1);
            this.field.setLines(1);
            this.field.setPadding(0, 0, 0, 0);
            this.field.setSingleLine(true);
            EditTextBoldCursor r15 = this.field;
            int r6 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r5 = 5;
        L6:
            r15.setGravity(r5 | 16);
            this.field.setInputType(180224);
            this.field.setImeOptions(6);
            this.field.setHint(LocaleController.getString(R.string.UsernameLinkPlaceholder));
            this.field.setCursorColor(Theme.getColor(r4));
            this.field.setCursorSize(AndroidUtilities.dp(19.0f));
            this.field.setCursorWidth(1.5f);
            this.field.setOnEditorActionListener(new ChangeUsernameActivity$InputCell$$ExternalSyntheticLambda0(this));
            this.field.setText(ChangeUsernameActivity.m7153$$Nest$fgetusername(r13));
            this.field.addTextChangedListener(new AnonymousClass1(this, r13));
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(r13) == 0) goto L9;
            this.field.setEnabled(false);
        L9:
            TextView r16 = new TextView(getContext());
            this.tme = r16;
            r16.setMaxLines(1);
            this.tme.setLines(1);
            this.tme.setPadding(0, 0, 0, 0);
            this.tme.setSingleLine(true);
            this.tme.setText(r13.getMessagesController().linkPrefix + "/");
            this.tme.setTextSize(1, 17.0f);
            this.tme.setTextColor(Theme.getColor(r4));
            TextView r0 = this.tme;
            if (LocaleController.isRTL == false) goto L12;
            r6 = 5;
        L12:
            r0.setGravity(r6 | 16);
            r142.addView(this.tme, LayoutHelper.createLinear(-2, -1, 0.0f, 16, 21, 0, 0, 0));
            r142.addView(this.field, LayoutHelper.createLinear(-2, -1, 1.0f, 16, 0, 0, 21, 0));
            addView(r142, LayoutHelper.createFrame(-1, -1, 48));
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(r13) == 0) goto L16;
            this.field.setAlpha(0.6f);
            this.tme.setAlpha(0.6f);
            return;
        L16:
            return;
        L5:
            r5 = 3;
            goto L6
        }

        private /* synthetic */ boolean lambda$new$0(TextView r1, int r2, KeyEvent r3) {
            if (r2 == 6) goto L5;
            return false;
        L5:
            if (ChangeUsernameActivity.m7148$$Nest$fgetdoneButton(this.this$0) == null) goto L10;
            ChangeUsernameActivity.m7148$$Nest$fgetdoneButton(this.this$0).performClick();
            return true;
        L10:
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }
    }

    public class LinkSpan extends ClickableSpan {
        final /* synthetic */ ChangeUsernameActivity this$0;
        private String url;

        public LinkSpan(ChangeUsernameActivity r1, String r2) {
            this.this$0 = r1;
            this.url = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.url));     // Catch: Exception -> L6
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L11;
            BulletinFactory.createCopyLinkBulletin(this.this$0).show();     // Catch: Exception -> L6
            return;
        L11:
            return;
        L6:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        final /* synthetic */ ChangeUsernameActivity this$0;

        public TouchHelperCallback(ChangeUsernameActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
            r2.itemView.setTag(R.id.dragging, null);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 4) goto L10;
            if (((UsernameCell) r3.itemView).active == false) goto L10;
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        L10:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r2, RecyclerView.ViewHolder r3, RecyclerView.ViewHolder r4) {
            if (r3.getItemViewType() != r4.getItemViewType()) goto L11;
            View r22 = r4.itemView;
            if ((r22 instanceof UsernameCell) == true) goto L7;
        L9:
            ChangeUsernameActivity.m7146$$Nest$fgetadapter(this.this$0).swapElements(r3.getAdapterPosition(), r4.getAdapterPosition());
            return true;
        L7:
            if (((UsernameCell) r22).active == true) goto L9;
            return false;
        L11:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            ChangeUsernameActivity r0 = this.this$0;
            if (r4 != 0) goto L5;
            ChangeUsernameActivity.m7166$$Nest$msendReorder(r0);
        L6:
            super.onSelectedChanged(r3, r4);
            if (r3 == null) goto L14;
            View r2 = r3.itemView;
            int r32 = R.id.dragging;
            if (r4 != 2) goto L11;
            Boolean r42 = Boolean.TRUE;
        L12:
            r2.setTag(r32, r42);
            return;
        L11:
            r42 = null;
            goto L12
        L14:
            return;
        L5:
            ChangeUsernameActivity.m7151$$Nest$fgetlistView(r0).cancelClickRunnables(false);
            r3.itemView.setPressed(true);
            goto L6
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public static class UsernameCell extends FrameLayout {
        public boolean active;
        private AnimatedFloat activeFloat;
        private AnimatedTextView activeView;
        private ValueAnimator activeViewTextColorAnimator;
        private float activeViewTextColorT;
        private long botId;
        public TLRPC.TL_username currentUsername;
        public boolean editable;
        public boolean isProfile;
        private Drawable[] linkDrawables;
        public boolean loading;
        public ValueAnimator loadingAnimator;
        private CircularProgressDrawable loadingDrawable;
        public float loadingFloat;
        private ImageView loadingView;
        private Theme.ResourcesProvider resourcesProvider;
        private boolean useDivider;
        private AnimatedFloat useDividerAlpha;
        private SimpleTextView usernameView;

        /* renamed from: org.telegram.ui.ChangeUsernameActivity$UsernameCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ UsernameCell this$0;
            final /* synthetic */ boolean val$loading;

            public AnonymousClass1(UsernameCell r1, boolean r2) {
                this.this$0 = r1;
                this.val$loading = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ImageView r12 = UsernameCell.m7171$$Nest$fgetloadingView(this.this$0);
                if (this.val$loading == false) goto L5;
                int r0 = 0;
            L6:
                r12.setVisibility(r0);
                return;
            L5:
                r0 = 8;
                goto L6
            }
        }

        public static /* synthetic */ void $r8$lambda$20hfBlWnhPB65JUm7ps7sU_bIA8(UsernameCell r0, ValueAnimator r1) {
            r0.lambda$animateValueTextColor$1(r1);
        }

        /* renamed from: $r8$lambda$nBVHU-hY1yJVbXrl8rxXh4UjcaQ, reason: not valid java name */
        public static /* synthetic */ void m7170$r8$lambda$nBVHUhY1yJVbXrl8rxXh4UjcaQ(UsernameCell r0, ValueAnimator r1) {
            r0.lambda$setLoading$0(r1);
        }

        /* renamed from: -$$Nest$fgetloadingView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m7171$$Nest$fgetloadingView(UsernameCell r0) {
            return r0.loadingView;
        }

        public UsernameCell(Context r12, Theme.ResourcesProvider r13) {
            super(r12);
            this.isProfile = false;
            this.useDividerAlpha = new AnimatedFloat(this, 300, CubicBezierInterpolator.DEFAULT);
            this.activeFloat = new AnimatedFloat(this, 400, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.resourcesProvider = r13;
            setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, r13));
            SimpleTextView r1 = new SimpleTextView(getContext());
            this.usernameView = r1;
            r1.setTextSize(16);
            this.usernameView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
            this.usernameView.setEllipsizeByGradient(true);
            addView(this.usernameView, LayoutHelper.createFrame(-1, -2.0f, 48, 70.0f, 9.0f, 0.0f, 50.0f));
            this.loadingView = new ImageView(getContext());
            this.loadingDrawable = new CircularProgressDrawable(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(1.35f), Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, r13));
            this.loadingView.setScaleType(ImageView.ScaleType.CENTER);
            this.loadingView.setImageDrawable(this.loadingDrawable);
            this.loadingView.setAlpha(0.0f);
            this.loadingView.setVisibility(0);
            this.loadingDrawable.setBounds(0, 0, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
            addView(this.loadingView, LayoutHelper.createFrame(14, 14.0f, 48, 70.0f, 35.0f, 0.0f, 0.0f));
            AnimatedTextView r14 = new AnimatedTextView(getContext(), false, true, true);
            this.activeView = r14;
            r14.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r13));
            this.activeView.setAnimationProperties(0.4f, 0, 120, CubicBezierInterpolator.EASE_OUT);
            this.activeView.setTextSize(AndroidUtilities.dp(13.0f));
            addView(this.activeView, LayoutHelper.createFrame(-1, -2.0f, 48, 70.0f, 23.0f, 0.0f, 0.0f));
            Drawable[] r122 = {ContextCompat.getDrawable(r12, R.drawable.msg_link_1).mutate(), ContextCompat.getDrawable(r12, R.drawable.msg_link_2).mutate()};
            this.linkDrawables = r122;
            Drawable r123 = r122[0];
            PorterDuff.Mode r15 = PorterDuff.Mode.MULTIPLY;
            r123.setColorFilter(new PorterDuffColorFilter(-1, r15));
            this.linkDrawables[1].setColorFilter(new PorterDuffColorFilter(-1, r15));
            ChangeUsernameActivity.m7168$$Nest$sfgetlinkBackgroundActive().setColor(Theme.getColor(Theme.key_featuredStickers_addButton, r13));
            ChangeUsernameActivity.m7169$$Nest$sfgetlinkBackgroundInactive().setColor(Theme.getColor(Theme.key_chats_unreadCounterMuted, r13));
        }

        private void animateValueTextColor(boolean r3, boolean r4) {
            ValueAnimator r0 = this.activeViewTextColorAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.activeViewTextColorAnimator = null;
        L5:
            float r02 = 0.0f;
            if (r4 == false) goto L12;
            float r42 = this.activeViewTextColorT;
            if (r3 == false) goto L10;
            r02 = 1.0f;
        L10:
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r42, r02});
            this.activeViewTextColorAnimator = r32;
            r32.addUpdateListener(new ChangeUsernameActivity$UsernameCell$$ExternalSyntheticLambda1(this));
            this.activeViewTextColorAnimator.setDuration(120);
            this.activeViewTextColorAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.activeViewTextColorAnimator.start();
            return;
        L12:
            if (r3 == false) goto L14;
            r02 = 1.0f;
        L14:
            this.activeViewTextColorT = r02;
            int r33 = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider), Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, this.resourcesProvider), this.activeViewTextColorT);
            this.loadingDrawable.setColor(r33);
            this.activeView.setTextColor(r33);
        }

        private /* synthetic */ void lambda$animateValueTextColor$1(ValueAnimator r3) {
            this.activeViewTextColorT = ((Float) r3.getAnimatedValue()).floatValue();
            int r32 = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider), Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, this.resourcesProvider), this.activeViewTextColorT);
            this.loadingDrawable.setColor(r32);
            this.activeView.setTextColor(r32);
        }

        private /* synthetic */ void lambda$setLoading$0(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            this.loadingFloat = r32;
            this.activeView.setTranslationX(r32 * AndroidUtilities.dp(16.0f));
            this.loadingView.setAlpha(this.loadingFloat);
        }

        public String getUsernameEditable() {
            return null;
        }

        @Override // android.view.View
        public void onDraw(Canvas r17) {
            super.onDraw(r17);
            AnimatedFloat r2 = this.activeFloat;
            float r5 = 1.0f;
            if (this.active == false) goto L5;
            float r3 = 1.0f;
        L6:
            float r7 = r2.set(r3);
            if (r7 >= 1.0f) goto L10;
            r17.drawCircle(AndroidUtilities.dp(35.0f), AndroidUtilities.dp(29.0f), AndroidUtilities.dp(16.0f), ChangeUsernameActivity.m7169$$Nest$sfgetlinkBackgroundInactive());
            this.linkDrawables[1].setAlpha((int) ((1.0f - r7) * 255.0f));
            this.linkDrawables[1].setBounds(AndroidUtilities.dp(35.0f) - (this.linkDrawables[1].getIntrinsicWidth() / 2), AndroidUtilities.dp(29.0f) - (this.linkDrawables[1].getIntrinsicHeight() / 2), AndroidUtilities.dp(35.0f) + (this.linkDrawables[1].getIntrinsicWidth() / 2), AndroidUtilities.dp(29.0f) + (this.linkDrawables[1].getIntrinsicHeight() / 2));
            this.linkDrawables[1].draw(r17);
        L10:
            if (r7 <= 0.0f) goto L12;
            int r32 = (int) (255.0f * r7);
            ChangeUsernameActivity.m7168$$Nest$sfgetlinkBackgroundActive().setAlpha(r32);
            r17.drawCircle(AndroidUtilities.dp(35.0f), AndroidUtilities.dp(29.0f), AndroidUtilities.dp(16.0f) * r7, ChangeUsernameActivity.m7168$$Nest$sfgetlinkBackgroundActive());
            this.linkDrawables[0].setAlpha(r32);
            this.linkDrawables[0].setBounds(AndroidUtilities.dp(35.0f) - (this.linkDrawables[0].getIntrinsicWidth() / 2), AndroidUtilities.dp(29.0f) - (this.linkDrawables[0].getIntrinsicHeight() / 2), AndroidUtilities.dp(35.0f) + (this.linkDrawables[0].getIntrinsicWidth() / 2), AndroidUtilities.dp(29.0f) + (this.linkDrawables[0].getIntrinsicHeight() / 2));
            this.linkDrawables[0].draw(r17);
        L12:
            AnimatedFloat r22 = this.useDividerAlpha;
            if (this.useDivider == true) goto L16;
            r5 = 0.0f;
        L16:
            float r23 = r22.set(r5);
            if (r23 <= 0.0f) goto L19;
            int r8 = Theme.dividerPaint.getAlpha();
            Theme.dividerPaint.setAlpha((int) (r8 * r23));
            r17.drawRect(AndroidUtilities.dp(70.0f), getHeight() - 1, getWidth(), getHeight(), Theme.dividerPaint);
            Theme.dividerPaint.setAlpha(r8);
        L19:
            ChangeUsernameActivity.m7167$$Nest$sfgetdragPaint().setColor(Theme.getColor(Theme.key_stickers_menu));
            ChangeUsernameActivity.m7167$$Nest$sfgetdragPaint().setAlpha((int) (ChangeUsernameActivity.m7167$$Nest$sfgetdragPaint().getAlpha() * r7));
            RectF r24 = AndroidUtilities.rectTmp;
            r24.set(getWidth() - AndroidUtilities.dp(37.0f), AndroidUtilities.dp(25.0f), getWidth() - AndroidUtilities.dp(21.0f), AndroidUtilities.dp(27.0f));
            r17.drawRoundRect(r24, AndroidUtilities.dp(0.3f), AndroidUtilities.dp(0.3f), ChangeUsernameActivity.m7167$$Nest$sfgetdragPaint());
            r24.set(getWidth() - AndroidUtilities.dp(37.0f), AndroidUtilities.dp(31.0f), getWidth() - AndroidUtilities.dp(21.0f), AndroidUtilities.dp(33.0f));
            r17.drawRoundRect(r24, AndroidUtilities.dp(0.3f), AndroidUtilities.dp(0.3f), ChangeUsernameActivity.m7167$$Nest$sfgetdragPaint());
            return;
        L5:
            r3 = 0.0f;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
        }

        public void set(TLRPC.TL_username r7, boolean r8, boolean r9) {
            set(r7, r8, r9, 0);
        }

        public void set(TLRPC.TL_username r4, boolean r5, boolean r6, long r7) {
            this.currentUsername = r4;
            this.useDivider = r5;
            this.botId = r7;
            invalidate();
            boolean r0 = false;
            if (this.currentUsername != null) goto L6;
            this.active = false;
            this.editable = false;
            return;
        L6:
            this.active = r4.active;
            if (r7 == 0) goto L9;
        L11:
            boolean r52 = false;
        L12:
            this.editable = r52;
            updateUsername(r4.username);
            boolean r42 = this.isProfile;
            AnimatedTextView r53 = this.activeView;
            if (r42 == false) goto L24;
            if (this.editable == false) goto L19;
            int r43 = R.string.UsernameProfileLinkEditable;
        L22:
            r53.setText(LocaleController.getString(r43), r6, !this.active);
        L33:
            if (this.active == false) goto L35;
        L36:
            r0 = true;
        L37:
            animateValueTextColor(r0, r6);
            return;
        L35:
            if (this.editable == false) goto L37;
        L19:
            if (this.active == false) goto L21;
            r43 = R.string.UsernameProfileLinkActive;
            goto L22
        L21:
            r43 = R.string.UsernameProfileLinkInactive;
            goto L22
        L24:
            if (this.editable == false) goto L28;
            int r44 = R.string.UsernameLinkEditable;
        L31:
            r53.setText(LocaleController.getString(r44), r6, !this.active);
            goto L33
        L28:
            if (this.active == false) goto L30;
            r44 = R.string.UsernameLinkActive;
            goto L31
        L30:
            r44 = R.string.UsernameLinkInactive;
            goto L31
        L9:
            if (r4.editable == false) goto L11;
            r52 = true;
            goto L12
        }

        public void setLoading(boolean r5) {
            if (this.loading == r5) goto L13;
            this.loading = r5;
            ValueAnimator r0 = this.loadingAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
        L7:
            this.loadingView.setVisibility(0);
            float r02 = this.loadingFloat;
            if (r5 == false) goto L10;
            float r2 = 1.0f;
        L11:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r2});
            this.loadingAnimator = r03;
            r03.addUpdateListener(new ChangeUsernameActivity$UsernameCell$$ExternalSyntheticLambda0(this));
            this.loadingAnimator.addListener(new AnonymousClass1(this, r5));
            this.loadingAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.loadingAnimator.setDuration(200);
            this.loadingAnimator.start();
            return;
        L10:
            r2 = 0.0f;
            goto L11
        }

        public void update() {
            TLRPC.TL_username r1 = this.currentUsername;
            if (r1 == null) goto L6;
            set(r1, this.useDivider, true, this.botId);
            return;
        }

        public void updateUsername(String r6) {
            if (this.editable == false) goto L6;
            r6 = getUsernameEditable();
        L6:
            if (TextUtils.isEmpty(r6) == false) goto L9;
            SpannableStringBuilder r62 = new SpannableStringBuilder("@");
            SpannableString r0 = new SpannableString(LocaleController.getString(R.string.UsernameLinkPlaceholder));
            r0.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, this.resourcesProvider)), 0, r0.length(), 33);
            r62.append(r0);
            this.usernameView.setText(r62);
            return;
        L9:
            this.usernameView.setText("@" + r6);
        }
    }

    public class UsernameHelpCell extends FrameLayout {
        private Integer height;
        private ValueAnimator heightUpdateAnimator;
        private LinkSpanDrawable.LinksTextView text1View;
        private LinkSpanDrawable.LinksTextView text2View;
        final /* synthetic */ ChangeUsernameActivity this$0;

        /* renamed from: org.telegram.ui.ChangeUsernameActivity$UsernameHelpCell$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
            final /* synthetic */ UsernameHelpCell this$1;
            final /* synthetic */ ChangeUsernameActivity val$this$0;

            /* renamed from: org.telegram.ui.ChangeUsernameActivity$UsernameHelpCell$1$1, reason: invalid class name and collision with other inner class name */
            public class C00431 extends ClickableSpan {
                final /* synthetic */ AnonymousClass1 this$2;

                public C00431(AnonymousClass1 r1) {
                    this.this$2 = r1;
                }

                @Override // android.text.style.ClickableSpan
                public void onClick(View r3) {
                    Browser.openUrl(this.this$2.getContext(), "https://fragment.com/username/" + ChangeUsernameActivity.m7153$$Nest$fgetusername(this.this$2.this$1.this$0));
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint r1) {
                    super.updateDrawState(r1);
                    r1.setUnderlineText(false);
                }
            }

            public AnonymousClass1(UsernameHelpCell r1, Context r2, ChangeUsernameActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r6v0, types: [org.telegram.ui.ChangeUsernameActivity$UsernameHelpCell$1, org.telegram.ui.Components.LinkSpanDrawable$LinksTextView] */
            /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.CharSequence] */
            /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.CharSequence] */
            /* JADX WARN: Type inference failed for: r7v3, types: [android.text.SpannableStringBuilder] */
            @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
            public void setText(CharSequence r7, TextView.BufferType r8) {
                if (r7 == 0) goto L10;
                r7 = AndroidUtilities.replaceTags(r7.toString());
                int r0 = r7.toString().indexOf(10);
                int r2 = 0;
                if (r0 < 0) goto L6;
                r7.replace(r0, r0 + 1, " ");
                r7.setSpan(new ForegroundColorSpan(this.this$1.this$0.getThemedColor(Theme.key_text_RedRegular)), 0, r0, 33);
            L6:
                TypefaceSpan[] r02 = (TypefaceSpan[]) r7.getSpans(0, r7.length(), TypefaceSpan.class);
            L8:
                if (r2 >= r02.length) goto L10;
                r7.setSpan(new C00431(this), r7.getSpanStart(r02[r2]), r7.getSpanEnd(r02[r2]), 33);
                r7.removeSpan(r02[r2]);
                r2 = r2 + 1;
            L10:
                super.setText(r7, r8);
            }
        }

        public static /* synthetic */ void $r8$lambda$lGbVoOiauOr62g2g2tO9SpnCvKw(UsernameHelpCell r0, float r1, float r2, int r3, int r4, ValueAnimator r5) {
            r0.lambda$update$0(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$mupdate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7172$$Nest$mupdate(UsernameHelpCell r0) {
            r0.update();
        }

        public UsernameHelpCell(ChangeUsernameActivity r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            ChangeUsernameActivity.m7156$$Nest$fputhelpCell(r13, this);
            setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(17.0f));
            setClipChildren(false);
            LinkSpanDrawable.LinksTextView r1 = new LinkSpanDrawable.LinksTextView(r14);
            this.text1View = r1;
            r1.setTextSize(1, 15.0f);
            LinkSpanDrawable.LinksTextView r12 = this.text1View;
            int r4 = Theme.key_windowBackgroundWhiteGrayText8;
            r12.setTextColor(Theme.getColor(r4));
            LinkSpanDrawable.LinksTextView r15 = this.text1View;
            int r6 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r5 = 5;
        L6:
            r15.setGravity(r5);
            LinkSpanDrawable.LinksTextView r16 = this.text1View;
            int r52 = Theme.key_windowBackgroundWhiteLinkText;
            r16.setLinkTextColor(Theme.getColor(r52));
            LinkSpanDrawable.LinksTextView r17 = this.text1View;
            int r8 = Theme.key_windowBackgroundWhiteLinkSelection;
            r17.setHighlightColor(Theme.getColor(r8));
            this.text1View.setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), 0);
            AnonymousClass1 r18 = new AnonymousClass1(this, r14, r13);
            ChangeUsernameActivity.m7160$$Nest$fputstatusTextView(r13, r18);
            this.text2View = r18;
            r18.setTextSize(1, 15.0f);
            this.text2View.setTextColor(Theme.getColor(r4));
            LinkSpanDrawable.LinksTextView r142 = this.text2View;
            if (LocaleController.isRTL == false) goto L9;
            r6 = 5;
        L9:
            r142.setGravity(r6);
            this.text2View.setLinkTextColor(Theme.getColor(r52));
            this.text2View.setHighlightColor(Theme.getColor(r8));
            this.text2View.setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), 0);
            addView(this.text1View, LayoutHelper.createFrame(-1, -2, 48));
            addView(this.text2View, LayoutHelper.createFrame(-1, -2, 48));
            if (ChangeUsernameActivity.m7147$$Nest$fgetbotId(r13) == 0) goto L18;
            String r132 = LocaleController.getString(R.string.BotUsernameHelp);
            SpannableStringBuilder r143 = new SpannableStringBuilder(r132);
            int r3 = r132.indexOf(42);
            int r133 = r132.lastIndexOf(42);
            if (r3 == (-1)) goto L16;
            if (r133 == (-1)) goto L16;
            if (r3 == r133) goto L16;
            r143.replace(r133, r133 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r143.replace(r3, r3 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r143.setSpan(new URLSpanNoUnderline("https://fragment.com"), r3, r133 - 1, 33);
        L16:
            this.text1View.setText(r143);
            return;
        L18:
            this.text1View.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.UsernameHelp)));
            return;
        L5:
            r5 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$update$0(float r2, float r3, int r4, int r5, ValueAnimator r6) {
            float r62 = ((Float) r6.getAnimatedValue()).floatValue();
            this.text1View.setTranslationY(AndroidUtilities.lerp(r2, r3, r62));
            this.height = Integer.valueOf(AndroidUtilities.lerp(r4, r5, r62));
            requestLayout();
        }

        private void update() {
            if (this.text2View.getVisibility() != 0) goto L5;
            this.text2View.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(9999999, Integer.MIN_VALUE));
        L5:
            ValueAnimator r0 = this.heightUpdateAnimator;
            if (r0 == null) goto L8;
            r0.cancel();
        L8:
            Integer r02 = this.height;
            if (r02 != null) goto L12;
            int r03 = getMeasuredHeight();
        L11:
            int r5 = r03;
            int r04 = AndroidUtilities.dp(27.0f) + this.text1View.getHeight();
            if (this.text2View.getVisibility() == 0) goto L16;
        L18:
            int r1 = 0;
        L19:
            int r6 = r04 + r1;
            float r3 = this.text1View.getTranslationY();
            if (this.text2View.getVisibility() == 0) goto L22;
        L25:
            float r05 = 0.0f;
        L24:
            float r4 = r05;
            ValueAnimator r06 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.heightUpdateAnimator = r06;
            r06.addUpdateListener(new ChangeUsernameActivity$UsernameHelpCell$$ExternalSyntheticLambda0(this, r3, r4, r5, r6));
            this.heightUpdateAnimator.setDuration(200);
            this.heightUpdateAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.heightUpdateAnimator.start();
            return;
        L22:
            if (TextUtils.isEmpty(this.text2View.getText()) == true) goto L25;
            r05 = this.text2View.getMeasuredHeight() + AndroidUtilities.dp(8.0f);
            goto L24
        L16:
            if (TextUtils.isEmpty(this.text2View.getText()) == true) goto L18;
            r1 = this.text2View.getMeasuredHeight() + AndroidUtilities.dp(8.0f);
            goto L19
        L12:
            r03 = r02.intValue();
            goto L11
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            Integer r0 = this.height;
            if (r0 == null) goto L6;
            r3 = View.MeasureSpec.makeMeasureSpec(r0.intValue(), TLObject.FLAG_30);
        L6:
            super.onMeasure(r2, r3);
        }
    }

    public static /* synthetic */ boolean $r8$lambda$DmCwAT3BcvVq81X99BQkldG5VPk(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$KmmGnFYO-zLb4KyP0UyeWoLu1-s, reason: not valid java name */
    public static /* synthetic */ void m7140$r8$lambda$KmmGnFYOzLb4KyP0UyeWoLu1s(ChangeUsernameActivity r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$N37FnL0Ya3LjMvunopZBfY2u964(ChangeUsernameActivity r0, AlertDialog r1, TLRPC.User r2) {
        r0.lambda$saveName$6(r1, r2);
    }

    /* renamed from: $r8$lambda$SNR-fLEjWyHbslaaoLaKPLsu85g, reason: not valid java name */
    public static /* synthetic */ void m7141$r8$lambda$SNRfLEjWyHbslaaoLaKPLsu85g(ChangeUsernameActivity r0, AlertDialog r1) {
        r0.lambda$saveName$8(r1);
    }

    /* renamed from: $r8$lambda$XaRoXjGXHXO3jZucqjODHe-Ox28, reason: not valid java name */
    public static /* synthetic */ void m7142$r8$lambda$XaRoXjGXHXO3jZucqjODHeOx28(ChangeUsernameActivity r0, AlertDialog r1, TL_account.updateUsername r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$saveName$10(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Xn4tn3AcYbiM3mWO24uvH9vqZoo(ChangeUsernameActivity r0, AlertDialog r1) {
        r0.lambda$saveName$7(r1);
    }

    /* renamed from: $r8$lambda$_-ydbcXAUkhu5C0az44zHUqe_nE, reason: not valid java name */
    public static /* synthetic */ void m7143$r8$lambda$_ydbcXAUkhu5C0az44zHUqe_nE(ChangeUsernameActivity r0, String r1, TL_account.checkUsername r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$checkUserName$4(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$g-8M7qAD5CWY_PT1c0lFVdfkMpU, reason: not valid java name */
    public static /* synthetic */ void m7144$r8$lambda$g8M7qAD5CWY_PT1c0lFVdfkMpU(ChangeUsernameActivity r0, String r1, TLRPC.TL_error r2, TLObject r3, TL_account.checkUsername r4) {
        r0.lambda$checkUserName$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$hSex5QytEELIp0ePvgPyBhApDmc(ChangeUsernameActivity r0, String r1) {
        r0.lambda$checkUserName$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$jsp9hhvEnr0_vUQ0UOX5EysEU7o(ChangeUsernameActivity r0, AlertDialog r1, TLRPC.TL_error r2, TL_account.updateUsername r3) {
        r0.lambda$saveName$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ueYFMwHRMzl33kgfebD0XQf_1Z8(ChangeUsernameActivity r0, int r1, DialogInterface r2) {
        r0.lambda$saveName$11(r1, r2);
    }

    /* renamed from: $r8$lambda$zq5vETz5Wdi_jfJWX-9-XSOXLMw, reason: not valid java name */
    public static /* synthetic */ void m7145$r8$lambda$zq5vETz5Wdi_jfJWX9XSOXLMw(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m7146$$Nest$fgetadapter(ChangeUsernameActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetbotId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m7147$$Nest$fgetbotId(ChangeUsernameActivity r2) {
        return r2.botId;
    }

    /* renamed from: -$$Nest$fgetdoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m7148$$Nest$fgetdoneButton(ChangeUsernameActivity r0) {
        return r0.doneButton;
    }

    /* renamed from: -$$Nest$fgeteditableUsernameCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UsernameCell m7149$$Nest$fgeteditableUsernameCell(ChangeUsernameActivity r0) {
        return r0.editableUsernameCell;
    }

    /* renamed from: -$$Nest$fgetignoreCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7150$$Nest$fgetignoreCheck(ChangeUsernameActivity r0) {
        return r0.ignoreCheck;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m7151$$Nest$fgetlistView(ChangeUsernameActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloadingUsernames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7152$$Nest$fgetloadingUsernames(ChangeUsernameActivity r0) {
        return r0.loadingUsernames;
    }

    /* renamed from: -$$Nest$fgetusername, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m7153$$Nest$fgetusername(ChangeUsernameActivity r0) {
        return r0.username;
    }

    /* renamed from: -$$Nest$fgetusernames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7154$$Nest$fgetusernames(ChangeUsernameActivity r0) {
        return r0.usernames;
    }

    /* renamed from: -$$Nest$fputeditableUsernameCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7155$$Nest$fputeditableUsernameCell(ChangeUsernameActivity r0, UsernameCell r1) {
        r0.editableUsernameCell = r1;
    }

    /* renamed from: -$$Nest$fputhelpCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7156$$Nest$fputhelpCell(ChangeUsernameActivity r0, UsernameHelpCell r1) {
        r0.helpCell = r1;
    }

    /* renamed from: -$$Nest$fputignoreCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7157$$Nest$fputignoreCheck(ChangeUsernameActivity r0, boolean r1) {
        r0.ignoreCheck = r1;
    }

    /* renamed from: -$$Nest$fputinputCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7158$$Nest$fputinputCell(ChangeUsernameActivity r0, InputCell r1) {
        r0.inputCell = r1;
    }

    /* renamed from: -$$Nest$fputneedReorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7159$$Nest$fputneedReorder(ChangeUsernameActivity r0, boolean r1) {
        r0.needReorder = r1;
    }

    /* renamed from: -$$Nest$fputstatusTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7160$$Nest$fputstatusTextView(ChangeUsernameActivity r0, LinkSpanDrawable.LinksTextView r1) {
        r0.statusTextView = r1;
    }

    /* renamed from: -$$Nest$fputusername, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7161$$Nest$fputusername(ChangeUsernameActivity r0, String r1) {
        r0.username = r1;
    }

    /* renamed from: -$$Nest$mcheckUserName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7162$$Nest$mcheckUserName(ChangeUsernameActivity r0, String r1, boolean r2) {
        return r0.checkUserName(r1, r2);
    }

    /* renamed from: -$$Nest$mfocusUsernameField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7163$$Nest$mfocusUsernameField(ChangeUsernameActivity r0, boolean r1) {
        r0.focusUsernameField(r1);
    }

    /* renamed from: -$$Nest$mgetUserId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m7164$$Nest$mgetUserId(ChangeUsernameActivity r2) {
        return r2.getUserId();
    }

    /* renamed from: -$$Nest$msaveName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7165$$Nest$msaveName(ChangeUsernameActivity r0) {
        r0.saveName();
    }

    /* renamed from: -$$Nest$msendReorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7166$$Nest$msendReorder(ChangeUsernameActivity r0) {
        r0.sendReorder();
    }

    /* renamed from: -$$Nest$sfgetdragPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7167$$Nest$sfgetdragPaint() {
        return dragPaint;
    }

    /* renamed from: -$$Nest$sfgetlinkBackgroundActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7168$$Nest$sfgetlinkBackgroundActive() {
        return linkBackgroundActive;
    }

    /* renamed from: -$$Nest$sfgetlinkBackgroundInactive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7169$$Nest$sfgetlinkBackgroundInactive() {
        return linkBackgroundInactive;
    }

    static {
        linkBackgroundActive = new Paint(1);
        linkBackgroundInactive = new Paint(1);
        dragPaint = new Paint(1);
    }

    public ChangeUsernameActivity() {
        this(null);
    }

    public ChangeUsernameActivity(Bundle r3) {
        super(r3);
        this.username = _UrlKt.FRAGMENT_ENCODE_SET;
        this.notEditableUsernames = new ArrayList();
        this.usernames = new ArrayList();
        this.loadingUsernames = new ArrayList();
        if (r3 == null) goto L6;
        this.botId = r3.getLong("bot_id");
        return;
    }

    public static /* synthetic */ int access$000(ChangeUsernameActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(ChangeUsernameActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(ChangeUsernameActivity r0) {
        return r0.currentAccount;
    }

    private boolean checkUserName(String r7, boolean r8) {
        if (r7 != null) goto L5;
    L7:
        LinkSpanDrawable.LinksTextView r1 = this.statusTextView;
        if (r1 != null) goto L10;
    L16:
        if (r8 == true) goto L18;
    L20:
        Runnable r12 = this.checkRunnable;
        if (r12 == null) goto L25;
        AndroidUtilities.cancelRunOnUIThread(r12);
        this.checkRunnable = null;
        this.lastCheckName = null;
        if (this.checkReqId == 0) goto L25;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkReqId, true);
    L25:
        this.lastNameAvailable = false;
        if (r7 != null) goto L28;
    L74:
        if (r7 != null) goto L76;
    L105:
        if (r8 == false) goto L107;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.UsernameInvalidShort));
    L112:
        return false;
    L107:
        LinkSpanDrawable.LinksTextView r72 = this.statusTextView;
        if (r72 == null) goto L112;
        r72.setText(LocaleController.getString(R.string.UsernameInvalidShort));
        LinkSpanDrawable.LinksTextView r73 = this.statusTextView;
        int r82 = Theme.key_text_RedRegular;
        r73.setTag(Integer.valueOf(r82));
        this.statusTextView.setTextColor(Theme.getColor(r82));
        UsernameHelpCell r6 = this.helpCell;
        if (r6 == null) goto L112;
        UsernameHelpCell.m7172$$Nest$mupdate(r6);
        goto L112
    L76:
        if (r7.length() < 4) goto L105;
        if (r7.length() <= 32) goto L88;
        if (r8 == false) goto L82;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.UsernameInvalidLong));
    L87:
        return false;
    L82:
        LinkSpanDrawable.LinksTextView r74 = this.statusTextView;
        if (r74 == null) goto L87;
        r74.setText(LocaleController.getString(R.string.UsernameInvalidLong));
        LinkSpanDrawable.LinksTextView r75 = this.statusTextView;
        int r83 = Theme.key_text_RedRegular;
        r75.setTag(Integer.valueOf(r83));
        this.statusTextView.setTextColor(Theme.getColor(r83));
        UsernameHelpCell r62 = this.helpCell;
        if (r62 == null) goto L87;
        UsernameHelpCell.m7172$$Nest$mupdate(r62);
        goto L87
    L88:
        if (r8 == true) goto L104;
        String r84 = getUser().username;
        if (r84 != null) goto L92;
        r84 = _UrlKt.FRAGMENT_ENCODE_SET;
    L92:
        boolean r85 = r7.equals(r84);
        LinkSpanDrawable.LinksTextView r13 = this.statusTextView;
        if (r85 == false) goto L99;
        if (r13 == null) goto L98;
        r13.setText(LocaleController.formatString("UsernameAvailable", R.string.UsernameAvailable, new Object[]{r7}));
        LinkSpanDrawable.LinksTextView r76 = this.statusTextView;
        int r86 = Theme.key_windowBackgroundWhiteGreenText;
        r76.setTag(Integer.valueOf(r86));
        this.statusTextView.setTextColor(Theme.getColor(r86));
        UsernameHelpCell r63 = this.helpCell;
        if (r63 == null) goto L98;
        UsernameHelpCell.m7172$$Nest$mupdate(r63);
    L98:
        return true;
    L99:
        if (r13 == null) goto L103;
        r13.setText(LocaleController.getString(R.string.UsernameChecking));
        LinkSpanDrawable.LinksTextView r87 = this.statusTextView;
        int r14 = Theme.key_windowBackgroundWhiteGrayText8;
        r87.setTag(Integer.valueOf(r14));
        this.statusTextView.setTextColor(Theme.getColor(r14));
        UsernameHelpCell r88 = this.helpCell;
        if (r88 == null) goto L103;
        UsernameHelpCell.m7172$$Nest$mupdate(r88);
    L103:
        this.lastCheckName = r7;
        ChangeUsernameActivity$$ExternalSyntheticLambda9 r89 = new ChangeUsernameActivity$$ExternalSyntheticLambda9(this, r7);
        this.checkRunnable = r89;
        AndroidUtilities.runOnUIThread(r89, 300);
    L104:
        return true;
    L28:
        if (r7.startsWith("_") == false) goto L30;
    L68:
        LinkSpanDrawable.LinksTextView r77 = this.statusTextView;
        if (r77 == null) goto L73;
        r77.setText(LocaleController.getString(R.string.UsernameInvalid));
        LinkSpanDrawable.LinksTextView r78 = this.statusTextView;
        int r810 = Theme.key_text_RedRegular;
        r78.setTag(Integer.valueOf(r810));
        this.statusTextView.setTextColor(Theme.getColor(r810));
        UsernameHelpCell r64 = this.helpCell;
        if (r64 == null) goto L73;
        UsernameHelpCell.m7172$$Nest$mupdate(r64);
    L73:
        return false;
    L30:
        if (r7.endsWith("_") == true) goto L68;
        int r15 = 0;
    L34:
        if (r15 >= r7.length()) goto L74;
        char r3 = r7.charAt(r15);
        if (r15 != 0) goto L47;
        if (r3 < '0') goto L47;
        if (r3 > '9') goto L47;
        if (r8 == false) goto L41;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.UsernameInvalidStartNumber));
    L46:
        return false;
    L41:
        LinkSpanDrawable.LinksTextView r79 = this.statusTextView;
        if (r79 == null) goto L46;
        r79.setText(LocaleController.getString(R.string.UsernameInvalidStartNumber));
        LinkSpanDrawable.LinksTextView r710 = this.statusTextView;
        int r811 = Theme.key_text_RedRegular;
        r710.setTag(Integer.valueOf(r811));
        this.statusTextView.setTextColor(Theme.getColor(r811));
        UsernameHelpCell r65 = this.helpCell;
        if (r65 == null) goto L46;
        UsernameHelpCell.m7172$$Nest$mupdate(r65);
    L47:
        if (r3 < '0') goto L50;
        if (r3 > '9') goto L50;
    L67:
        r15 = r15 + 1;
    L50:
        if (r3 < 'a') goto L54;
        if (r3 <= 'z') goto L67;
    L54:
        if (r3 < 'A') goto L58;
        if (r3 <= 'Z') goto L67;
    L58:
        if (r3 == '_') goto L67;
        if (r8 == false) goto L61;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.UsernameInvalid));
    L66:
        return false;
    L61:
        LinkSpanDrawable.LinksTextView r711 = this.statusTextView;
        if (r711 == null) goto L66;
        r711.setText(LocaleController.getString(R.string.UsernameInvalid));
        LinkSpanDrawable.LinksTextView r712 = this.statusTextView;
        int r812 = Theme.key_text_RedRegular;
        r712.setTag(Integer.valueOf(r812));
        this.statusTextView.setTextColor(Theme.getColor(r812));
        UsernameHelpCell r66 = this.helpCell;
        if (r66 == null) goto L66;
        UsernameHelpCell.m7172$$Nest$mupdate(r66);
        goto L66
    L18:
        if (r7.length() != 0) goto L20;
        return true;
    L10:
        if (TextUtils.isEmpty(r7) == true) goto L12;
        int r32 = 0;
    L13:
        r1.setVisibility(r32);
        UsernameHelpCell r16 = this.helpCell;
        if (r16 == null) goto L16;
        UsernameHelpCell.m7172$$Nest$mupdate(r16);
        goto L16
    L12:
        r32 = 8;
        goto L13
    L5:
        if (r7.startsWith("@") == false) goto L7;
        r7 = r7.substring(1);
        goto L7
    }

    private void focusUsernameField(boolean r3) {
        InputCell r0 = this.inputCell;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.field.isFocused() == true) goto L7;
        EditTextBoldCursor r02 = this.inputCell.field;
        r02.setSelection(r02.length());
    L7:
        this.inputCell.field.requestFocus();
        if (r3 == false) goto L12;
        AndroidUtilities.showKeyboard(this.inputCell.field);
        return;
    }

    private TLRPC.User getUser() {
        long r0 = this.botId;
        int r1 = this.currentAccount;
        if (r0 == 0) goto L7;
        return MessagesController.getInstance(r1).getUser(Long.valueOf(this.botId));
    L7:
        return UserConfig.getInstance(r1).getCurrentUser();
    }

    private long getUserId() {
        long r0 = this.botId;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return UserConfig.getInstance(this.currentAccount).getClientUserId();
    }

    private /* synthetic */ void lambda$checkUserName$3(String r3, TLRPC.TL_error r4, TLObject r5, TL_account.checkUsername r6) {
        this.checkReqId = 0;
        String r1 = this.lastCheckName;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.equals(r3) == false) goto L40;
        if (r4 != null) goto L17;
        if ((r5 instanceof TLRPC.TL_boolTrue) == false) goto L17;
        LinkSpanDrawable.LinksTextView r42 = this.statusTextView;
        if (r42 == null) goto L14;
        r42.setText(LocaleController.formatString("UsernameAvailable", R.string.UsernameAvailable, new Object[]{r3}));
        LinkSpanDrawable.LinksTextView r32 = this.statusTextView;
        int r43 = Theme.key_windowBackgroundWhiteGreenText;
        r32.setTag(Integer.valueOf(r43));
        this.statusTextView.setTextColor(Theme.getColor(r43));
        UsernameHelpCell r33 = this.helpCell;
        if (r33 == null) goto L14;
        UsernameHelpCell.m7172$$Nest$mupdate(r33);
    L14:
        this.lastNameAvailable = true;
        return;
    L17:
        if (this.statusTextView != null) goto L19;
    L37:
        this.lastNameAvailable = false;
        return;
    L19:
        if (r4 != null) goto L21;
    L25:
        if (r4 != null) goto L27;
    L33:
        this.statusTextView.setText(LocaleController.getString(R.string.UsernameInUse));
        LinkSpanDrawable.LinksTextView r34 = this.statusTextView;
        int r44 = Theme.key_text_RedRegular;
        r34.setTag(Integer.valueOf(r44));
        this.statusTextView.setTextColor(Theme.getColor(r44));
    L34:
        UsernameHelpCell r35 = this.helpCell;
        if (r35 == null) goto L37;
        UsernameHelpCell.m7172$$Nest$mupdate(r35);
        goto L37
    L27:
        if ("USERNAME_PURCHASE_AVAILABLE".equals(r4.text) == false) goto L33;
        int r45 = r6.username.length();
        LinkSpanDrawable.LinksTextView r52 = this.statusTextView;
        if (r45 != 4) goto L31;
        r52.setText(LocaleController.getString(R.string.UsernameInvalidShortPurchase));
    L32:
        LinkSpanDrawable.LinksTextView r36 = this.statusTextView;
        int r46 = Theme.key_windowBackgroundWhiteGrayText8;
        r36.setTag(Integer.valueOf(r46));
        this.statusTextView.setTextColor(Theme.getColor(r46));
        goto L34
    L31:
        r52.setText(LocaleController.getString(R.string.UsernameInUsePurchase));
        goto L32
    L21:
        if ("USERNAME_INVALID".equals(r4.text) == false) goto L25;
        if (r6.username.length() != 4) goto L25;
        this.statusTextView.setText(LocaleController.getString(R.string.UsernameInvalidShort));
        LinkSpanDrawable.LinksTextView r37 = this.statusTextView;
        int r47 = Theme.key_text_RedRegular;
        r37.setTag(Integer.valueOf(r47));
        this.statusTextView.setTextColor(Theme.getColor(r47));
        goto L34
    }

    private /* synthetic */ void lambda$checkUserName$4(String r7, TL_account.checkUsername r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$$ExternalSyntheticLambda11(this, r7, r10, r9, r8));
    }

    private /* synthetic */ void lambda$checkUserName$5(String r4) {
        TL_account.checkUsername r0 = new TL_account.checkUsername();
        r0.username = r4;
        this.checkReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChangeUsernameActivity$$ExternalSyntheticLambda10(this, r4, r0), 2);
    }

    private /* synthetic */ void lambda$createView$1() {
        String r0 = this.username;
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
        this.ignoreCheck = true;
        if (this.usernames.size() <= 0) goto L12;
        r02 = false;
    L12:
        focusUsernameField(r02);
        this.ignoreCheck = false;
        return;
    L5:
        if (r0.length() > 0) goto L8;
    }

    private /* synthetic */ void lambda$saveName$10(AlertDialog r2, TL_account.updateUsername r3, TLObject r4, TLRPC.TL_error r5) {
        if (r5 != null) goto L6;
        AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$$ExternalSyntheticLambda5(this, r2, (TLRPC.User) r4));
        return;
    L6:
        if ("USERNAME_NOT_MODIFIED".equals(r5.text) == false) goto L10;
        AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$$ExternalSyntheticLambda6(this, r2));
        return;
    L10:
        if ("USERNAME_PURCHASE_AVAILABLE".equals(r5.text) == false) goto L12;
    L16:
        AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$$ExternalSyntheticLambda7(this, r2));
        return;
    L12:
        if ("USERNAME_INVALID".equals(r5.text) == true) goto L16;
        AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$$ExternalSyntheticLambda8(this, r2, r5, r3));
    }

    private /* synthetic */ void lambda$saveName$11(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$saveName$6(AlertDialog r4, TLRPC.User r5) {
        r4.dismiss();     // Catch: Exception -> L4
    L6:
        ArrayList<TLRPC.User> r42 = new ArrayList();
        r42.add(r5);
        MessagesController.getInstance(this.currentAccount).putUsers(r42, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r42, null, false, true);
        UserConfig.getInstance(this.currentAccount).saveConfig(true);
        finishFragment();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$saveName$7(AlertDialog r1) {
        r1.dismiss();     // Catch: Exception -> L4
    L6:
        finishFragment();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$saveName$8(AlertDialog r1) {
        r1.dismiss();     // Catch: Exception -> L4
    L6:
        shakeIfOff();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$saveName$9(AlertDialog r2, TLRPC.TL_error r3, TL_account.updateUsername r4) {
        r2.dismiss();     // Catch: Exception -> L4
    L6:
        AlertsCreator.processError(this.currentAccount, r3, this, r4, new Object[0]);
        shakeIfOff();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private void saveName() {
        if (this.botId == 0) goto L7;
        finishFragment();
        return;
    L7:
        if (this.username.startsWith("@") == false) goto L10;
        this.username = this.username.substring(1);
    L10:
        if (this.username.isEmpty() == false) goto L12;
    L15:
        TLRPC.User r0 = getUser();
        if (getParentActivity() == null) goto L28;
        if (r0 == null) goto L29;
        String r02 = UserObject.getPublicUsername(r0);
        if (r02 != null) goto L23;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        if (r02.equals(this.username) == false) goto L26;
        finishFragment();
        return;
    L26:
        AlertDialog r03 = new AlertDialog(getParentActivity(), 3);
        TL_account.updateUsername r1 = new TL_account.updateUsername();
        r1.username = this.username;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        int r12 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new ChangeUsernameActivity$$ExternalSyntheticLambda3(this, r03, r1), 2);
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r12, this.classGuid);
        r03.setOnCancelListener(new ChangeUsernameActivity$$ExternalSyntheticLambda4(this, r12));
        r03.show();
        return;
    L29:
        return;
    L28:
        return;
    L12:
        if (checkUserName(this.username, false) == true) goto L15;
        shakeIfOff();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void sendReorder() {
        if (this.needReorder == true) goto L5;
        return;
    L5:
        int r0 = 0;
        this.needReorder = false;
        ArrayList<String> r1 = new ArrayList();
        int r2 = 0;
    L7:
        if (r2 >= this.notEditableUsernames.size()) goto L13;
        if (this.notEditableUsernames.get(r2).active == false) goto L11;
        r1.add(this.notEditableUsernames.get(r2).username);
    L11:
        r2 = r2 + 1;
    L13:
        if (r0 >= this.usernames.size()) goto L19;
        if (this.usernames.get(r0).active == false) goto L17;
        r1.add(this.usernames.get(r0).username);
    L17:
        r0 = r0 + 1;
        goto L13
    L19:
        if (this.botId != 0) goto L21;
        TL_account.reorderUsernames r02 = new TL_account.reorderUsernames();
        r02.order = r1;
        TL_bots.reorderUsernames r03 = r02;
    L22:
        getConnectionsManager().sendRequest(r03, new ChangeUsernameActivity$$ExternalSyntheticLambda2());
        updateUser();
        return;
    L21:
        TL_bots.reorderUsernames r04 = new TL_bots.reorderUsernames();
        r04.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botId);
        r04.order = r1;
        r03 = r04;
        goto L22
    }

    private void updateUser() {
        ArrayList<TLRPC.TL_username> r0 = new ArrayList();
        r0.addAll(this.notEditableUsernames);
        r0.addAll(this.usernames);
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(getUserId()));
        r1.usernames = r0;
        MessagesController.getInstance(this.currentAccount).putUser(r1, false, true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.Username));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(getUserId()));
        if (r0 != null) goto L6;
        r0 = getUser();
    L6:
        if (r0 == null) goto L39;
        this.username = null;
        int r3 = 0;
        if (r0.usernames == null) goto L19;
        int r2 = 0;
    L11:
        if (r2 >= r0.usernames.size()) goto L19;
        TLRPC.TL_username r4 = r0.usernames.get(r2);
        if (r4 == null) goto L17;
        if (r4.editable == false) goto L17;
        this.username = r4.username;
    L17:
        r2 = r2 + 1;
    L19:
        if (this.username != null) goto L24;
        String r22 = r0.username;
        if (r22 == null) goto L24;
        this.username = r22;
    L24:
        if (this.username != null) goto L26;
        this.username = _UrlKt.FRAGMENT_ENCODE_SET;
    L26:
        this.notEditableUsernames.clear();
        this.usernames.clear();
        int r23 = 0;
    L28:
        if (r23 >= r0.usernames.size()) goto L34;
        if (r0.usernames.get(r23).active == false) goto L32;
        this.usernames.add(r0.usernames.get(r23));
    L32:
        r23 = r23 + 1;
    L34:
        if (r3 >= r0.usernames.size()) goto L39;
        if (r0.usernames.get(r3).active == true) goto L38;
        this.usernames.add(r0.usernames.get(r3));
    L38:
        r3 = r3 + 1;
    L39:
        this.fragmentView = new FrameLayout(r7);
        RecyclerListView r02 = new RecyclerListView(r7);
        this.listView = r02;
        r02.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.fragmentView.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        RecyclerListView r03 = this.listView;
        LinearLayoutManager r24 = new LinearLayoutManager(r7);
        this.layoutManager = r24;
        r03.setLayoutManager(r24);
        RecyclerListView r72 = this.listView;
        Adapter r04 = new Adapter(this, null);
        this.adapter = r04;
        r72.setAdapter(r04);
        this.listView.setSelectorDrawableColor(getThemedColor(Theme.key_listSelector));
        ItemTouchHelper r73 = new ItemTouchHelper(new TouchHelperCallback(this));
        this.itemTouchHelper = r73;
        r73.attachToRecyclerView(this.listView);
        ((FrameLayout) this.fragmentView).addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.fragmentView.setOnTouchListener(new ChangeUsernameActivity$$ExternalSyntheticLambda0());
        this.listView.setOnItemClickListener(new AnonymousClass2(this));
        AndroidUtilities.runOnUIThread(new ChangeUsernameActivity$$ExternalSyntheticLambda1(this), 40);
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = new ArrayList();
        r0.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r0.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r0.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r0.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == true) goto L6;
        focusUsernameField(false);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        focusUsernameField(false);
        return;
    }

    public void shakeIfOff() {
        if (this.listView != null) goto L5;
        return;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.listView.getChildCount()) goto L19;
        View r1 = this.listView.getChildAt(r0);
        if ((r1 instanceof HeaderCell) == false) goto L13;
        if (r0 != 0) goto L13;
        AndroidUtilities.shakeViewSpring(((HeaderCell) r1).getTextView());
    L18:
        r0 = r0 + 1;
    L13:
        if ((r1 instanceof UsernameHelpCell) == false) goto L16;
        AndroidUtilities.shakeViewSpring(r1);
        goto L18
    L16:
        if ((r1 instanceof InputCell) == false) goto L18;
        InputCell r12 = (InputCell) r1;
        AndroidUtilities.shakeViewSpring(r12.field);
        AndroidUtilities.shakeViewSpring(r12.tme);
        goto L18
    L19:
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
    }

    public void toggleUsername(int r2, boolean r3) {
        toggleUsername(r2, r3, false);
    }

    public void toggleUsername(int r6, boolean r7, boolean r8) {
        int r0 = r6 - 4;
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (r0 >= this.usernames.size()) goto L58;
        TLRPC.TL_username r02 = this.usernames.get(r0);
        if (r02 == null) goto L59;
        r02.active = r7;
        int r1 = -1;
        int r2 = 0;
        if (r7 == false) goto L22;
        int r72 = 0;
    L14:
        if (r72 >= this.usernames.size()) goto L19;
        if (this.usernames.get(r72).active == false) goto L20;
        r72 = r72 + 1;
    L20:
        if (r72 < 0) goto L33;
        int r73 = Math.max(0, r72 - 1);
    L31:
        r1 = r73 + 4;
    L33:
        if (this.listView != null) goto L35;
    L44:
        if (r1 < 0) goto L56;
        if (r6 == r1) goto L57;
        this.adapter.moveElement(r6, r1);
        return;
    L57:
        return;
    L56:
        return;
    L35:
        if (r2 >= this.listView.getChildCount()) goto L44;
        View r74 = this.listView.getChildAt(r2);
        if (this.listView.getChildAdapterPosition(r74) == r6) goto L38;
        r2 = r2 + 1;
        goto L35
    L38:
        if (r8 == false) goto L41;
        AndroidUtilities.shakeView(r74);
    L41:
        if ((r74 instanceof UsernameCell) == false) goto L44;
        UsernameCell r75 = (UsernameCell) r74;
        r75.setLoading(this.loadingUsernames.contains(r02.username));
        r75.update();
        goto L44
    L19:
        r72 = -1;
        goto L20
    L22:
        int r3 = -1;
        int r76 = 0;
    L24:
        if (r76 >= this.usernames.size()) goto L29;
        if (this.usernames.get(r76).active == false) goto L28;
        r3 = r76;
    L28:
        r76 = r76 + 1;
        goto L24
    L29:
        if (r3 < 0) goto L33;
        r73 = Math.min(this.usernames.size() - 1, r3 + 1);
        goto L31
    L59:
        return;
    }

    public void toggleUsername(TLRPC.TL_username r3, boolean r4, boolean r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.usernames.size()) goto L10;
        if (this.usernames.get(r0) == r3) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        toggleUsername(r0 + 4, r4, r5);
        return;
    }
}
