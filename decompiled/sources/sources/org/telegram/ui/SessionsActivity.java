package org.telegram.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Business.BusinessChatbotController;
import org.telegram.ui.Business.ChatbotSheet;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.SessionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.SessionBottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class SessionsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private final int VIEW_TYPE_HEADER;
    private final int VIEW_TYPE_INFO;
    private final int VIEW_TYPE_SCANQR;
    private final int VIEW_TYPE_SESSION;
    private final int VIEW_TYPE_SETTINGS;
    private final int VIEW_TYPE_TEXT;
    private int botSessionsEndRow;
    private int botSessionsStartRow;
    private ArrayList<TL_account.TL_connectedBot> bots;
    private TLRPC.TL_authorization currentSession;
    private int currentSessionRow;
    private int currentSessionSectionRow;
    private int currentType;
    private Delegate delegate;
    private EmptyTextProgressView emptyView;
    private boolean fragmentOpened;
    private FlickerLoadingView globalFlickerLoadingView;
    private boolean highlightLinkDesktopDevice;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private boolean loading;
    private int noOtherSessionsRow;
    private int otherSessionsEndRow;
    private int otherSessionsSectionRow;
    private int otherSessionsStartRow;
    private int otherSessionsTerminateDetail;
    private ArrayList<TLObject> passwordSessions;
    private int passwordSessionsDetailRow;
    private int passwordSessionsEndRow;
    private int passwordSessionsSectionRow;
    private int passwordSessionsStartRow;
    private int qrCodeDividerRow;
    private int qrCodeRow;
    private int repeatLoad;
    private int rowCount;
    private ArrayList<TLObject> sessions;
    private int terminateAllSessionsDetailRow;

    @Keep
    private int terminateAllSessionsRow;
    private int ttlDays;
    private int ttlDivideRow;
    private int ttlHeaderRow;

    @Keep
    private int ttlRow;
    private UndoView undoView;

    /* renamed from: org.telegram.ui.SessionsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SessionsActivity this$0;

        public AnonymousClass1(SessionsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.SessionsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ SessionsActivity this$0;

        public AnonymousClass2(SessionsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r2) {
            if (r2 != SessionsActivity.m18812$$Nest$fgetterminateAllSessionsRow(this.this$0)) goto L7;
            return Integer.valueOf(Theme.multAlpha(getThemedColor(Theme.key_text_RedRegular), 0.1f));
        L7:
            return Integer.valueOf(getThemedColor(Theme.key_listSelector));
        }
    }

    /* renamed from: org.telegram.ui.SessionsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayoutManager {
        final /* synthetic */ SessionsActivity this$0;

        public AnonymousClass3(SessionsActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.SessionsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends UndoView {
        final /* synthetic */ SessionsActivity this$0;

        public static /* synthetic */ void $r8$lambda$5dCgd3KgszYdZCfVViwVAyJSk6k(AnonymousClass4 r0, TLRPC.TL_error r1, TLRPC.TL_authorization r2) {
            r0.lambda$hide$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$b1XRCMrM8V1Zo14GNwt7cBoEDbQ(AnonymousClass4 r0, TLRPC.TL_authorization r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$hide$1(r1, r2, r3);
        }

        public AnonymousClass4(SessionsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$hide$0(TLRPC.TL_error r1, TLRPC.TL_authorization r2) {
            if (r1 != null) goto L8;
            SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).remove(r2);
            SessionsActivity.m18802$$Nest$fgetpasswordSessions(this.this$0).remove(r2);
            SessionsActivity.m18820$$Nest$mupdateRows(this.this$0);
            if (SessionsActivity.m18796$$Nest$fgetlistAdapter(this.this$0) == null) goto L6;
            SessionsActivity.m18796$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
        L6:
            this.this$0.loadSessions(true);
            return;
        }

        private /* synthetic */ void lambda$hide$1(TLRPC.TL_authorization r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new SessionsActivity$4$$ExternalSyntheticLambda1(this, r3, r1));
        }

        @Override // org.telegram.ui.Components.UndoView
        public void hide(boolean r5, int r6) {
            if (r5 == false) goto L4;
        L6:
            super.hide(r5, r6);
            return;
        L4:
            if (getCurrentInfoObject() == null) goto L6;
            TLRPC.TL_authorization r0 = (TLRPC.TL_authorization) getCurrentInfoObject();
            TL_account.resetAuthorization r1 = new TL_account.resetAuthorization();
            r1.hash = r0.hash;
            ConnectionsManager.getInstance(SessionsActivity.access$000(this.this$0)).sendRequest(r1, new SessionsActivity$4$$ExternalSyntheticLambda0(this, r0));
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.SessionsActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements SessionBottomSheet.Callback {
        final /* synthetic */ SessionsActivity this$0;

        public static /* synthetic */ void $r8$lambda$6oFTbtlLhw2Ss2ob7HUq6zByC2o(AnonymousClass5 r0, TLRPC.TL_error r1, TLRPC.TL_authorization r2) {
            r0.lambda$onSessionTerminated$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$MqhEaezZKH7pFjT5yHEf_ZweUNk(AnonymousClass5 r0, TLRPC.TL_authorization r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onSessionTerminated$1(r1, r2, r3);
        }

        public AnonymousClass5(SessionsActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSessionTerminated$0(TLRPC.TL_error r1, TLRPC.TL_authorization r2) {
            if (r1 != null) goto L7;
            SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).remove(r2);
            SessionsActivity.m18802$$Nest$fgetpasswordSessions(this.this$0).remove(r2);
            SessionsActivity.m18820$$Nest$mupdateRows(this.this$0);
            if (SessionsActivity.m18796$$Nest$fgetlistAdapter(this.this$0) == null) goto L8;
            SessionsActivity.m18796$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            return;
        L8:
            return;
        }

        private /* synthetic */ void lambda$onSessionTerminated$1(TLRPC.TL_authorization r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new SessionsActivity$5$$ExternalSyntheticLambda1(this, r3, r1));
        }

        @Override // org.telegram.ui.SessionBottomSheet.Callback
        public void onSessionTerminated(TLRPC.TL_authorization r4) {
            TL_account.resetAuthorization r0 = new TL_account.resetAuthorization();
            r0.hash = r4.hash;
            ConnectionsManager.getInstance(SessionsActivity.access$100(this.this$0)).sendRequest(r0, new SessionsActivity$5$$ExternalSyntheticLambda0(this, r4));
        }
    }

    /* renamed from: org.telegram.ui.SessionsActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements CameraScanActivity.CameraScanActivityDelegate {
        private TLRPC.TL_error error;
        private TLObject response;
        final /* synthetic */ SessionsActivity this$0;

        /* renamed from: $r8$lambda$-ehyo9R8BHFGR1BrWYIR1YxOIFo, reason: not valid java name */
        public static /* synthetic */ void m18821$r8$lambda$ehyo9R8BHFGR1BrWYIR1YxOIFo(AnonymousClass6 r0, TLObject r1, TLRPC.TL_error r2, Runnable r3) {
            r0.lambda$processQr$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$JZi7Wx_r1mdl_JT79QVcSgMlti0(AnonymousClass6 r0, String r1, Runnable r2) {
            r0.lambda$processQr$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$QVwea_wvsgxht0SpZycwkqDKEeU(AnonymousClass6 r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$processQr$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$cL6lqyQo1Ar4hoZ2zAytCJn8OYw(AnonymousClass6 r0) {
            r0.lambda$processQr$3();
        }

        public static /* synthetic */ void $r8$lambda$xgAYEgnhjGkIapp2UWJLx0mPmZI(AnonymousClass6 r0) {
            r0.lambda$didFindQr$0();
        }

        public AnonymousClass6(SessionsActivity r1) {
            this.this$0 = r1;
            this.response = null;
            this.error = null;
        }

        private /* synthetic */ void lambda$didFindQr$0() {
            String r0 = this.error.text;
            if (r0 != null) goto L5;
        L7:
            String r02 = LocaleController.getString(R.string.ErrorOccurred) + "\n" + this.error.text;
        L8:
            AlertsCreator.showSimpleAlert(this.this$0, LocaleController.getString(R.string.AuthAnotherClient), r02);
            return;
        L5:
            if (r0.equals("AUTH_TOKEN_EXCEPTION") == false) goto L7;
            r02 = LocaleController.getString(R.string.AccountAlreadyLoggedIn);
            goto L8
        }

        private /* synthetic */ void lambda$processQr$1(TLObject r1, TLRPC.TL_error r2, Runnable r3) {
            this.response = r1;
            this.error = r2;
            r3.run();
        }

        private /* synthetic */ void lambda$processQr$2(Runnable r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new SessionsActivity$6$$ExternalSyntheticLambda4(this, r3, r4, r2));
        }

        private /* synthetic */ void lambda$processQr$3() {
            AlertsCreator.showSimpleAlert(this.this$0, LocaleController.getString(R.string.AuthAnotherClient), LocaleController.getString(R.string.ErrorOccurred));
        }

        private /* synthetic */ void lambda$processQr$4(String r3, Runnable r4) {
            byte[] r32 = Base64.decode(r3.substring(17).replaceAll("\\/", "_").replaceAll("\\+", "-"), 8);     // Catch: Exception -> L5
            TLRPC.TL_auth_acceptLoginToken r0 = new TLRPC.TL_auth_acceptLoginToken();     // Catch: Exception -> L5
            r0.token = r32;     // Catch: Exception -> L5
            this.this$0.getConnectionsManager().sendRequest(r0, new SessionsActivity$6$$ExternalSyntheticLambda2(this, r4));     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e("Failed to pass qr code auth", e);
            AndroidUtilities.runOnUIThread(new SessionsActivity$6$$ExternalSyntheticLambda3(this));
            r4.run();
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void didFindQr(String r4) {
            TLObject r42 = this.response;
            if ((r42 instanceof TLRPC.TL_authorization) == false) goto L11;
            TLRPC.TL_authorization r0 = (TLRPC.TL_authorization) r42;
            boolean r43 = ((TLRPC.TL_authorization) r42).password_pending;
            SessionsActivity r1 = this.this$0;
            if (r43 == false) goto L7;
            SessionsActivity.m18802$$Nest$fgetpasswordSessions(r1).add(0, r0);
            SessionsActivity.m18818$$Nest$fputrepeatLoad(this.this$0, 4);
            this.this$0.loadSessions(false);
        L8:
            SessionsActivity.m18820$$Nest$mupdateRows(this.this$0);
            SessionsActivity.m18796$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            SessionsActivity.m18817$$Nest$fgetundoView(this.this$0).showWithAction(0, 11, this.response);
            return;
        L7:
            SessionsActivity.m18810$$Nest$fgetsessions(r1).add(0, r0);
            goto L8
        L11:
            if (this.error == null) goto L14;
            AndroidUtilities.runOnUIThread(new SessionsActivity$6$$ExternalSyntheticLambda0(this));
            return;
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public boolean processQr(String r2, Runnable r3) {
            this.response = null;
            this.error = null;
            AndroidUtilities.runOnUIThread(new SessionsActivity$6$$ExternalSyntheticLambda1(this, r2, r3), 750);
            return true;
        }
    }

    public interface Delegate {
        void sessionsLoaded();
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ SessionsActivity this$0;

        public ListAdapter(SessionsActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
            setHasStableIds(true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return SessionsActivity.m18809$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r6) {
            if (r6 != SessionsActivity.m18812$$Nest$fgetterminateAllSessionsRow(this.this$0)) goto L8;
            int r5 = Objects.hash(new Object[]{0, 0});
        L6:
            return r5;
        L8:
            if (r6 != SessionsActivity.m18811$$Nest$fgetterminateAllSessionsDetailRow(this.this$0)) goto L11;
            r5 = Objects.hash(new Object[]{0, 1});
            goto L6
        L11:
            if (r6 != SessionsActivity.m18801$$Nest$fgetotherSessionsTerminateDetail(this.this$0)) goto L14;
            r5 = Objects.hash(new Object[]{0, 2});
            goto L6
        L14:
            if (r6 != SessionsActivity.m18803$$Nest$fgetpasswordSessionsDetailRow(this.this$0)) goto L17;
            r5 = Objects.hash(new Object[]{0, 3});
            goto L6
        L17:
            if (r6 != SessionsActivity.m18807$$Nest$fgetqrCodeDividerRow(this.this$0)) goto L20;
            r5 = Objects.hash(new Object[]{0, 4});
            goto L6
        L20:
            if (r6 != SessionsActivity.m18814$$Nest$fgetttlDivideRow(this.this$0)) goto L23;
            r5 = Objects.hash(new Object[]{0, 5});
            goto L6
        L23:
            if (r6 != SessionsActivity.m18797$$Nest$fgetnoOtherSessionsRow(this.this$0)) goto L26;
            r5 = Objects.hash(new Object[]{0, 6});
            goto L6
        L26:
            if (r6 != SessionsActivity.m18793$$Nest$fgetcurrentSessionSectionRow(this.this$0)) goto L29;
            r5 = Objects.hash(new Object[]{0, 7});
            goto L6
        L29:
            if (r6 != SessionsActivity.m18799$$Nest$fgetotherSessionsSectionRow(this.this$0)) goto L32;
            r5 = Objects.hash(new Object[]{0, 8});
            goto L6
        L32:
            if (r6 != SessionsActivity.m18805$$Nest$fgetpasswordSessionsSectionRow(this.this$0)) goto L35;
            r5 = Objects.hash(new Object[]{0, 9});
            goto L6
        L35:
            if (r6 != SessionsActivity.m18815$$Nest$fgetttlHeaderRow(this.this$0)) goto L38;
            r5 = Objects.hash(new Object[]{0, 10});
            goto L6
        L38:
            if (r6 != SessionsActivity.m18792$$Nest$fgetcurrentSessionRow(this.this$0)) goto L41;
            r5 = Objects.hash(new Object[]{0, 11});
            goto L6
        L41:
            if (r6 < SessionsActivity.m18800$$Nest$fgetotherSessionsStartRow(this.this$0)) goto L51;
            if (r6 >= SessionsActivity.m18798$$Nest$fgetotherSessionsEndRow(this.this$0)) goto L51;
            TLObject r52 = (TLObject) SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).get(r6 - SessionsActivity.m18800$$Nest$fgetotherSessionsStartRow(this.this$0));
            if ((r52 instanceof TLRPC.TL_authorization) == false) goto L48;
            r5 = Objects.hash(new Object[]{1, Long.valueOf(((TLRPC.TL_authorization) r52).hash)});
            goto L6
        L48:
            if ((r52 instanceof TLRPC.TL_webAuthorization) == false) goto L71;
            r5 = Objects.hash(new Object[]{1, Long.valueOf(((TLRPC.TL_webAuthorization) r52).hash)});
        L71:
            r5 = Objects.hash(new Object[]{0, -1});
        L51:
            if (r6 < SessionsActivity.m18789$$Nest$fgetbotSessionsStartRow(this.this$0)) goto L56;
            if (r6 >= SessionsActivity.m18788$$Nest$fgetbotSessionsEndRow(this.this$0)) goto L56;
            r5 = Objects.hash(new Object[]{3, Long.valueOf(((TL_account.TL_connectedBot) SessionsActivity.m18790$$Nest$fgetbots(this.this$0).get(r6 - SessionsActivity.m18789$$Nest$fgetbotSessionsStartRow(this.this$0))).bot_id)});
        L56:
            if (r6 < SessionsActivity.m18806$$Nest$fgetpasswordSessionsStartRow(this.this$0)) goto L66;
            if (r6 >= SessionsActivity.m18804$$Nest$fgetpasswordSessionsEndRow(this.this$0)) goto L66;
            TLObject r53 = (TLObject) SessionsActivity.m18802$$Nest$fgetpasswordSessions(this.this$0).get(r6 - SessionsActivity.m18806$$Nest$fgetpasswordSessionsStartRow(this.this$0));
            if ((r53 instanceof TLRPC.TL_authorization) == false) goto L63;
            r5 = Objects.hash(new Object[]{2, Long.valueOf(((TLRPC.TL_authorization) r53).hash)});
            goto L6
        L63:
            if ((r53 instanceof TLRPC.TL_webAuthorization) == false) goto L71;
            r5 = Objects.hash(new Object[]{2, Long.valueOf(((TLRPC.TL_webAuthorization) r53).hash)});
        L66:
            if (r6 != SessionsActivity.m18808$$Nest$fgetqrCodeRow(this.this$0)) goto L69;
            r5 = Objects.hash(new Object[]{0, 12});
            goto L6
        L69:
            if (r6 != SessionsActivity.m18816$$Nest$fgetttlRow(this.this$0)) goto L71;
            r5 = Objects.hash(new Object[]{0, 13});
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != SessionsActivity.m18812$$Nest$fgetterminateAllSessionsRow(this.this$0)) goto L6;
            return 0;
        L6:
            if (r3 != SessionsActivity.m18811$$Nest$fgetterminateAllSessionsDetailRow(this.this$0)) goto L8;
            return 1;
        L8:
            if (r3 != SessionsActivity.m18801$$Nest$fgetotherSessionsTerminateDetail(this.this$0)) goto L10;
            return 1;
        L10:
            if (r3 != SessionsActivity.m18803$$Nest$fgetpasswordSessionsDetailRow(this.this$0)) goto L12;
            return 1;
        L12:
            if (r3 != SessionsActivity.m18807$$Nest$fgetqrCodeDividerRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r3 != SessionsActivity.m18814$$Nest$fgetttlDivideRow(this.this$0)) goto L16;
            return 1;
        L16:
            if (r3 != SessionsActivity.m18797$$Nest$fgetnoOtherSessionsRow(this.this$0)) goto L19;
            return 1;
        L19:
            if (r3 != SessionsActivity.m18793$$Nest$fgetcurrentSessionSectionRow(this.this$0)) goto L21;
            return 2;
        L21:
            if (r3 != SessionsActivity.m18799$$Nest$fgetotherSessionsSectionRow(this.this$0)) goto L23;
            return 2;
        L23:
            if (r3 != SessionsActivity.m18805$$Nest$fgetpasswordSessionsSectionRow(this.this$0)) goto L25;
            return 2;
        L25:
            if (r3 != SessionsActivity.m18815$$Nest$fgetttlHeaderRow(this.this$0)) goto L28;
            return 2;
        L28:
            if (r3 != SessionsActivity.m18792$$Nest$fgetcurrentSessionRow(this.this$0)) goto L30;
            return 4;
        L30:
            if (r3 < SessionsActivity.m18800$$Nest$fgetotherSessionsStartRow(this.this$0)) goto L34;
            if (r3 >= SessionsActivity.m18798$$Nest$fgetotherSessionsEndRow(this.this$0)) goto L34;
            return 4;
        L34:
            if (r3 < SessionsActivity.m18789$$Nest$fgetbotSessionsStartRow(this.this$0)) goto L38;
            if (r3 >= SessionsActivity.m18788$$Nest$fgetbotSessionsEndRow(this.this$0)) goto L38;
            return 4;
        L38:
            if (r3 < SessionsActivity.m18806$$Nest$fgetpasswordSessionsStartRow(this.this$0)) goto L43;
            if (r3 >= SessionsActivity.m18804$$Nest$fgetpasswordSessionsEndRow(this.this$0)) goto L43;
            return 4;
        L43:
            if (r3 != SessionsActivity.m18808$$Nest$fgetqrCodeRow(this.this$0)) goto L47;
            return 5;
        L47:
            if (r3 != SessionsActivity.m18816$$Nest$fgetttlRow(this.this$0)) goto L50;
            return 6;
        L50:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != SessionsActivity.m18812$$Nest$fgetterminateAllSessionsRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 < SessionsActivity.m18800$$Nest$fgetotherSessionsStartRow(this.this$0)) goto L9;
            if (r22 >= SessionsActivity.m18798$$Nest$fgetotherSessionsEndRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 < SessionsActivity.m18789$$Nest$fgetbotSessionsStartRow(this.this$0)) goto L13;
            if (r22 >= SessionsActivity.m18788$$Nest$fgetbotSessionsEndRow(this.this$0)) goto L13;
            return true;
        L13:
            if (r22 < SessionsActivity.m18806$$Nest$fgetpasswordSessionsStartRow(this.this$0)) goto L17;
            if (r22 >= SessionsActivity.m18804$$Nest$fgetpasswordSessionsEndRow(this.this$0)) goto L17;
            return true;
        L17:
            if (r22 != SessionsActivity.m18792$$Nest$fgetcurrentSessionRow(this.this$0)) goto L19;
            return true;
        L19:
            if (r22 == SessionsActivity.m18816$$Nest$fgetttlRow(this.this$0)) goto L29;
            return false;
        L29:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            int r0 = r5.getItemViewType();
            boolean r1 = false;
            if (r0 == 0) goto L124;
            if (r0 != 1) goto L6;
            TextInfoPrivacyCell r52 = (TextInfoPrivacyCell) r5.itemView;
            r52.setFixedSize(0);
            int r02 = SessionsActivity.m18811$$Nest$fgetterminateAllSessionsDetailRow(this.this$0);
            SessionsActivity r12 = this.this$0;
            if (r6 == r02) goto L95;
            int r03 = SessionsActivity.m18801$$Nest$fgetotherSessionsTerminateDetail(r12);
            SessionsActivity r13 = this.this$0;
            if (r6 != r03) goto L113;
            if (SessionsActivity.m18794$$Nest$fgetcurrentType(r13) == 0) goto L105;
            r52.setText(LocaleController.getString(R.string.TerminateWebSessionInfo));
            return;
        L105:
            if (SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).isEmpty() == false) goto L108;
            r52.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L108:
            r52.setText(LocaleController.getString(R.string.SessionsListInfo));
            return;
        L113:
            if (r6 != SessionsActivity.m18803$$Nest$fgetpasswordSessionsDetailRow(r13)) goto L117;
            r52.setText(LocaleController.getString(R.string.LoginAttemptsInfo));
            return;
        L117:
            if (r6 != SessionsActivity.m18807$$Nest$fgetqrCodeDividerRow(this.this$0)) goto L119;
        L122:
            r52.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r52.setFixedSize(12);
            return;
        L119:
            if (r6 == SessionsActivity.m18814$$Nest$fgetttlDivideRow(this.this$0)) goto L122;
            if (r6 == SessionsActivity.m18797$$Nest$fgetnoOtherSessionsRow(this.this$0)) goto L122;
            return;
        L95:
            if (SessionsActivity.m18794$$Nest$fgetcurrentType(r12) != 0) goto L98;
            r52.setText(LocaleController.getString(R.string.ClearOtherSessionsHelp));
            return;
        L98:
            r52.setText(LocaleController.getString(R.string.ClearOtherWebSessionsHelp));
            return;
        L6:
            if (r0 != 2) goto L8;
            HeaderCell r53 = (HeaderCell) r5.itemView;
            if (r6 != SessionsActivity.m18793$$Nest$fgetcurrentSessionSectionRow(this.this$0)) goto L76;
            r53.setText(LocaleController.getString(R.string.CurrentSession));
            return;
        L76:
            int r04 = SessionsActivity.m18799$$Nest$fgetotherSessionsSectionRow(this.this$0);
            SessionsActivity r14 = this.this$0;
            if (r6 != r04) goto L85;
            if (SessionsActivity.m18794$$Nest$fgetcurrentType(r14) != 0) goto L82;
            r53.setText(LocaleController.getString(R.string.OtherSessions));
            return;
        L82:
            r53.setText(LocaleController.getString(R.string.OtherWebSessions));
            return;
        L85:
            if (r6 != SessionsActivity.m18805$$Nest$fgetpasswordSessionsSectionRow(r14)) goto L89;
            r53.setText(LocaleController.getString(R.string.LoginAttempts));
            return;
        L89:
            if (r6 != SessionsActivity.m18815$$Nest$fgetttlHeaderRow(this.this$0)) goto L141;
            r53.setText(LocaleController.getString(R.string.TerminateOldSessionHeader));
            return;
        L141:
            return;
        L8:
            if (r0 == 5) goto L136;
            View r54 = r5.itemView;
            if (r0 == 6) goto L61;
            SessionCell r55 = (SessionCell) r54;
            int r05 = SessionsActivity.m18792$$Nest$fgetcurrentSessionRow(this.this$0);
            SessionsActivity r3 = this.this$0;
            if (r6 != r05) goto L27;
            TLRPC.TL_authorization r62 = SessionsActivity.m18791$$Nest$fgetcurrentSession(r3);
            SessionsActivity r06 = this.this$0;
            if (r62 != null) goto L17;
            r55.showStub(SessionsActivity.m18795$$Nest$fgetglobalFlickerLoadingView(r06));
            return;
        L17:
            TLRPC.TL_authorization r63 = SessionsActivity.m18791$$Nest$fgetcurrentSession(r06);
            if (SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).isEmpty() == true) goto L20;
        L23:
            r1 = true;
        L24:
            r55.setSession(r63, r1);
            return;
        L20:
            if (SessionsActivity.m18802$$Nest$fgetpasswordSessions(this.this$0).isEmpty() == false) goto L23;
            if (SessionsActivity.m18808$$Nest$fgetqrCodeRow(this.this$0) == (-1)) goto L24;
        L27:
            if (r6 < SessionsActivity.m18800$$Nest$fgetotherSessionsStartRow(r3)) goto L36;
            if (r6 >= SessionsActivity.m18798$$Nest$fgetotherSessionsEndRow(this.this$0)) goto L36;
            TLObject r07 = (TLObject) SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).get(r6 - SessionsActivity.m18800$$Nest$fgetotherSessionsStartRow(this.this$0));
            if (r6 == (SessionsActivity.m18798$$Nest$fgetotherSessionsEndRow(this.this$0) - 1)) goto L33;
            r1 = true;
        L33:
            r55.setSession(r07, r1);
            return;
        L36:
            if (r6 < SessionsActivity.m18789$$Nest$fgetbotSessionsStartRow(this.this$0)) goto L53;
            if (r6 >= SessionsActivity.m18788$$Nest$fgetbotSessionsEndRow(this.this$0)) goto L53;
            int r08 = r6 - SessionsActivity.m18789$$Nest$fgetbotSessionsStartRow(this.this$0);
            if (SessionsActivity.m18790$$Nest$fgetbots(this.this$0) == null) goto L137;
            if (r08 >= 0) goto L43;
            return;
        L43:
            if (r08 >= SessionsActivity.m18790$$Nest$fgetbots(this.this$0).size()) goto L144;
            TL_account.TL_connectedBot r09 = (TL_account.TL_connectedBot) SessionsActivity.m18790$$Nest$fgetbots(this.this$0).get(r08);
            if (r6 != (SessionsActivity.m18788$$Nest$fgetbotSessionsEndRow(this.this$0) - 1)) goto L48;
        L50:
            r55.setSession(r09, r1);
            return;
        L48:
            if (r6 == (SessionsActivity.m18798$$Nest$fgetotherSessionsEndRow(this.this$0) - 1)) goto L50;
            r1 = true;
            goto L50
        L144:
            return;
        L137:
            return;
        L53:
            if (r6 >= SessionsActivity.m18806$$Nest$fgetpasswordSessionsStartRow(this.this$0)) goto L55;
            return;
        L55:
            if (r6 >= SessionsActivity.m18804$$Nest$fgetpasswordSessionsEndRow(this.this$0)) goto L140;
            TLObject r010 = (TLObject) SessionsActivity.m18802$$Nest$fgetpasswordSessions(this.this$0).get(r6 - SessionsActivity.m18806$$Nest$fgetpasswordSessionsStartRow(this.this$0));
            if (r6 == (SessionsActivity.m18804$$Nest$fgetpasswordSessionsEndRow(this.this$0) - 1)) goto L59;
            r1 = true;
        L59:
            r55.setSession(r010, r1);
            return;
        L140:
            return;
        L61:
            TextSettingsCell r56 = (TextSettingsCell) r54;
            if (SessionsActivity.m18813$$Nest$fgetttlDays(this.this$0) > 30) goto L64;
        L66:
            int r64 = SessionsActivity.m18813$$Nest$fgetttlDays(this.this$0);
            SessionsActivity r4 = this.this$0;
            if (r64 != 365) goto L69;
            String r42 = LocaleController.formatPluralString("Years", SessionsActivity.m18813$$Nest$fgetttlDays(r4) / 365, new Object[0]);
        L70:
            r56.setTextAndValue(LocaleController.getString(R.string.IfInactiveFor), r42, true, false);
            return;
        L69:
            r42 = LocaleController.formatPluralString("Weeks", SessionsActivity.m18813$$Nest$fgetttlDays(r4) / 7, new Object[0]);
            goto L70
        L64:
            if (SessionsActivity.m18813$$Nest$fgetttlDays(this.this$0) > 183) goto L66;
            r42 = LocaleController.formatPluralString("Months", SessionsActivity.m18813$$Nest$fgetttlDays(this.this$0) / 30, new Object[0]);
            goto L70
        L136:
            return;
        L124:
            TextCell r57 = (TextCell) r5.itemView;
            if (r6 != SessionsActivity.m18812$$Nest$fgetterminateAllSessionsRow(this.this$0)) goto L133;
            int r65 = Theme.key_text_RedRegular;
            r57.setColors(r65, r65);
            r57.setTag(Integer.valueOf(r65));
            if (SessionsActivity.m18794$$Nest$fgetcurrentType(this.this$0) != 0) goto L130;
            r57.setTextAndIcon(LocaleController.getString(R.string.TerminateAllSessions), R.drawable.msg_block2, false);
            return;
        L130:
            r57.setTextAndIcon(LocaleController.getString(R.string.TerminateAllWebSessions), R.drawable.msg_block2, false);
            return;
        L133:
            if (r6 != SessionsActivity.m18808$$Nest$fgetqrCodeRow(this.this$0)) goto L143;
            int r66 = Theme.key_windowBackgroundWhiteBlueText4;
            r57.setColors(r66, r66);
            r57.setTag(Integer.valueOf(r66));
            r57.setTextAndIcon(LocaleController.getString(R.string.AuthAnotherClient), R.drawable.msg_qrcode, !SessionsActivity.m18810$$Nest$fgetsessions(this.this$0).isEmpty());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextCell(this.mContext);
        L18:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 != 1) goto L6;
            r12 = new TextInfoPrivacyCell(this.mContext);
            goto L18
        L6:
            if (r2 != 2) goto L8;
            r12 = new HeaderCell(this.mContext);
            goto L18
        L8:
            if (r2 != 5) goto L10;
            r12 = new ScanQRCodeView(this.this$0, this.mContext);
            goto L18
        L10:
            if (r2 == 6) goto L12;
            r12 = new SessionCell(this.mContext, SessionsActivity.m18794$$Nest$fgetcurrentType(this.this$0));
            goto L18
        L12:
            r12 = new TextSettingsCell(this.mContext);
            goto L18
        }
    }

    public class ScanQRCodeView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
        ButtonWithCounterView buttonTextView;
        CellFlickerDrawable flickerDrawable;
        BackupImageView imageView;
        TextView textView;
        final /* synthetic */ SessionsActivity this$0;

        /* renamed from: org.telegram.ui.SessionsActivity$ScanQRCodeView$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ ScanQRCodeView this$1;
            final /* synthetic */ SessionsActivity val$this$0;

            public AnonymousClass1(ScanQRCodeView r1, SessionsActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r2) {
                if (this.this$1.imageView.getImageReceiver().getLottieAnimation() != null) goto L5;
                return;
            L5:
                if (this.this$1.imageView.getImageReceiver().getLottieAnimation().isRunning() == true) goto L9;
                this.this$1.imageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false);
                this.this$1.imageView.getImageReceiver().getLottieAnimation().restart();
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$CXzIqlyxXn30X8tfA7udFsnqeEM(ScanQRCodeView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public ScanQRCodeView(SessionsActivity r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            this.flickerDrawable = new CellFlickerDrawable();
            BackupImageView r0 = new BackupImageView(r14);
            this.imageView = r0;
            addView(r0, LayoutHelper.createFrame(120, 120.0f, 1, 0.0f, 16.0f, 0.0f, 0.0f));
            CellFlickerDrawable r02 = this.flickerDrawable;
            r02.repeatEnabled = false;
            r02.animationSpeedScale = 1.2f;
            this.imageView.setOnClickListener(new AnonymousClass1(this, r13));
            int r03 = Theme.key_windowBackgroundWhiteBlackText;
            Theme.getColor(r03);
            int r2 = Theme.key_windowBackgroundWhite;
            Theme.getColor(r2);
            Theme.getColor(Theme.key_featuredStickers_addButton);
            Theme.getColor(r2);
            LinkSpanDrawable.LinksTextView r22 = new LinkSpanDrawable.LinksTextView(r14);
            this.textView = r22;
            addView(r22, LayoutHelper.createFrame(-1, -2.0f, 0, 36.0f, 152.0f, 36.0f, 0.0f));
            this.textView.setGravity(1);
            this.textView.setTextColor(Theme.getColor(r03));
            this.textView.setTextSize(1, 15.0f);
            this.textView.setTypeface(AndroidUtilities.regular());
            this.textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
            this.textView.setHighlightColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkSelection));
            String r04 = LocaleController.getString(R.string.AuthAnotherClientInfo4);
            SpannableStringBuilder r23 = new SpannableStringBuilder(r04);
            int r5 = r04.indexOf(42);
            int r6 = r5 + 1;
            int r05 = r04.indexOf(42, r6);
            if (r5 == (-1)) goto L7;
            if (r05 == (-1)) goto L7;
            if (r5 == r05) goto L7;
            this.textView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
            r23.replace(r05, r05 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r23.replace(r5, r6, _UrlKt.FRAGMENT_ENCODE_SET);
            r23.setSpan(new URLSpanNoUnderline(LocaleController.getString(R.string.AuthAnotherClientDownloadClientUrl)), r5, r05 - 1, 33);
        L7:
            String r06 = r23.toString();
            int r52 = r06.indexOf(42);
            int r62 = r52 + 1;
            int r07 = r06.indexOf(42, r62);
            if (r52 == (-1)) goto L12;
            if (r07 == (-1)) goto L12;
            if (r52 == r07) goto L12;
            this.textView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
            r23.replace(r07, r07 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r23.replace(r52, r62, _UrlKt.FRAGMENT_ENCODE_SET);
            r23.setSpan(new URLSpanNoUnderline(LocaleController.getString(R.string.AuthAnotherWebClientUrl)), r52, r07 - 1, 33);
        L12:
            this.textView.setText(r23);
            this.buttonTextView = new ButtonWithCounterView(r14, SessionsActivity.access$200(r13));
            SpannableStringBuilder r132 = new SpannableStringBuilder();
            r132.append(".  ").append(LocaleController.getString(R.string.LinkDesktopDevice));
            r132.setSpan(new ColoredImageSpan(ContextCompat.getDrawable(getContext(), R.drawable.msg_mini_qr)), 0, 1, 0);
            this.buttonTextView.setText(r132, false);
            this.buttonTextView.setOnClickListener(new SessionsActivity$ScanQRCodeView$$ExternalSyntheticLambda0(this));
            addView(this.buttonTextView, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 15.0f, 16.0f, 16.0f));
            setSticker();
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            int r22 = this.this$0.getParentActivity().checkSelfPermission("android.permission.CAMERA");
            SessionsActivity r1 = this.this$0;
            if (r22 == 0) goto L9;
            r1.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 34);
            return;
        L9:
            SessionsActivity.m18819$$Nest$mopenCameraScanActivity(r1);
        }

        private void setSticker() {
            TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(SessionsActivity.access$500(this.this$0)).getStickerSetByName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
            if (r0 != null) goto L5;
            r0 = MediaDataController.getInstance(SessionsActivity.access$600(this.this$0)).getStickerSetByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
        L5:
            TLRPC.TL_messages_stickerSet r7 = r0;
            SvgHelper.SvgDrawable r02 = null;
            if (r7 != null) goto L8;
        L10:
            TLRPC.Document r2 = null;
        L11:
            if (r2 == null) goto L13;
            r02 = DocumentObject.getSvgThumb(r2.thumbs, Theme.key_emptyListPlaceholder, 0.2f);
        L13:
            SvgHelper.SvgDrawable r6 = r02;
            if (r6 == null) goto L16;
            r6.overrideWidthAndHeight(512, 512);
        L16:
            if (r2 == null) goto L19;
            this.imageView.setImage(ImageLocation.getForDocument(r2), "130_130", "tgs", r6, r7);
            this.imageView.getImageReceiver().setAutoRepeat(2);
            return;
        L19:
            MediaDataController r8 = MediaDataController.getInstance(SessionsActivity.access$700(this.this$0));
            if (r7 != null) goto L22;
            boolean r22 = true;
        L23:
            r8.loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, r22);
            return;
        L22:
            r22 = false;
            goto L23
        L8:
            if (r7.documents.size() <= 6) goto L10;
            r2 = r7.documents.get(6);
            goto L11
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 == NotificationCenter.diceStickersDidLoad) goto L5;
            return;
        L5:
            if (AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME.equals((String) r3[0]) == false) goto L9;
            setSticker();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            setSticker();
            NotificationCenter.getInstance(SessionsActivity.access$300(this.this$0)).addObserver(this, NotificationCenter.diceStickersDidLoad);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(SessionsActivity.access$400(this.this$0)).removeObserver(this, NotificationCenter.diceStickersDidLoad);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(276.0f), TLObject.FLAG_30));
        }
    }

    public static /* synthetic */ void $r8$lambda$64iFicpthKD3r3Uw9dh0juS5ecI(SessionsActivity r0, AlertDialog r1, TLRPC.TL_authorization r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$createView$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$6Ev57Z8ML1Sb5fob164cttYcBdg(SessionsActivity r0, AlertDialog r1, TLRPC.TL_webAuthorization r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$createView$18(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$AhgtNho1OL9OHdHASrvDKSbBPok(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$EOykBtq6dWfMdBytDSQUy8b45YU(SessionsActivity r0, TL_account.connectedBots r1) {
        r0.lambda$onFragmentCreate$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$HoDF2amOk05uiZa6A69FMjBnFPY(SessionsActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$createView$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$N86fUsB8ypPdSGpjs7H8DmghcvE(SessionsActivity r0, boolean r1) {
        r0.lambda$loadSessions$24(r1);
    }

    /* renamed from: $r8$lambda$O3ZsR5U5cx1wND7e9x-jT5KaQpE, reason: not valid java name */
    public static /* synthetic */ void m18782$r8$lambda$O3ZsR5U5cx1wND7e9xjT5KaQpE(boolean[] r3, View r4) {
        if (r4.isEnabled() == true) goto L5;
        return;
    L5:
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$Q8b1zlKzeSZM3wF38sVNag0DyMM(SessionsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RKQct2NqyfjK8sVrfHA3IxmTEDU(SessionsActivity r0, AlertDialog.Builder r1, View r2) {
        r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Sy6JZq6RrW36MMBYnJfhL7xCIs8(SessionsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TXzFWsWQXQbtkSvf5SpF3UEhqVI(SessionsActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadSessions$26(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$UCbEpuLcCk0DEq6I-hCohwUlA44, reason: not valid java name */
    public static /* synthetic */ void m18783$r8$lambda$UCbEpuLcCk0DEq6IhCohwUlA44(SessionsActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VtLOIpB_H0mOrNlZuIu84d0R6r0(SessionsActivity r0, Boolean r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$WXgNVoxePT0Bxryvy_3LcXXUwNQ(SessionsActivity r0, AlertDialog r1, TLRPC.TL_error r2, TLRPC.TL_webAuthorization r3) {
        r0.lambda$createView$17(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$X-2zxl5yNBC70IUfQ62qy9vk9Gk, reason: not valid java name */
    public static /* synthetic */ void m18784$r8$lambda$X2zxl5yNBC70IUfQ62qy9vk9Gk(SessionsActivity r0, AlertDialog r1, TLRPC.TL_error r2, TLRPC.TL_authorization r3) {
        r0.lambda$createView$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$b57Ob2kpisp4Z02gY3BiGpETRgw(SessionsActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$loadSessions$25(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$cxk0jMZd57nb6Sa8ZZ8e2l3LXgA(SessionsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$eKHPIByc7UYsph3HMtfA73fvYvs(SessionsActivity r0, View r1, int r2) {
        r0.lambda$createView$20(r1, r2);
    }

    /* renamed from: $r8$lambda$goFbNDQKml2hH2Cxp-ZdY7lrHcY, reason: not valid java name */
    public static /* synthetic */ void m18785$r8$lambda$goFbNDQKml2hH2CxpZdY7lrHcY(SessionsActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadSessions$23(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$h7ujV8NUJ6BvfWSJVk-0oytKLDc, reason: not valid java name */
    public static /* synthetic */ void m18786$r8$lambda$h7ujV8NUJ6BvfWSJVk0oytKLDc(SessionsActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$loadSessions$22(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$imsJGENXHkcQso2iV77kmde4buE(SessionsActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$createView$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kscg92UQj4JEtnDpv5usp342Xa0(SessionsActivity r0) {
        r0.lambda$createView$3();
    }

    /* renamed from: $r8$lambda$mQ2NZX6M2hl9-fV08veVaMAyDmY, reason: not valid java name */
    public static /* synthetic */ void m18787$r8$lambda$mQ2NZX6M2hl9fV08veVaMAyDmY(SessionsActivity r0, int r1, boolean[] r2, AlertDialog r3, int r4) {
        r0.lambda$createView$19(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ofiPh7rpgmcTxBjjSkBbpUzIkcw(SessionsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sATdOrhjW9qA8deaxEkyRGPRRgQ(SessionsActivity r0, int r1) {
        r0.lambda$createView$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$tlH7Lx5uvHWqtBF6RukQAiLvjio(SessionsActivity r0, boolean r1) {
        r0.lambda$loadSessions$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$z76BhdOHQCDiFSwLISrJy5X8k7Y(SessionsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultFragment$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zJxUWLrTjpkyQhMdnt1kJ8aYH5g(SessionsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$9(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbotSessionsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18788$$Nest$fgetbotSessionsEndRow(SessionsActivity r0) {
        return r0.botSessionsEndRow;
    }

    /* renamed from: -$$Nest$fgetbotSessionsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18789$$Nest$fgetbotSessionsStartRow(SessionsActivity r0) {
        return r0.botSessionsStartRow;
    }

    /* renamed from: -$$Nest$fgetbots, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18790$$Nest$fgetbots(SessionsActivity r0) {
        return r0.bots;
    }

    /* renamed from: -$$Nest$fgetcurrentSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_authorization m18791$$Nest$fgetcurrentSession(SessionsActivity r0) {
        return r0.currentSession;
    }

    /* renamed from: -$$Nest$fgetcurrentSessionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18792$$Nest$fgetcurrentSessionRow(SessionsActivity r0) {
        return r0.currentSessionRow;
    }

    /* renamed from: -$$Nest$fgetcurrentSessionSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18793$$Nest$fgetcurrentSessionSectionRow(SessionsActivity r0) {
        return r0.currentSessionSectionRow;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18794$$Nest$fgetcurrentType(SessionsActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetglobalFlickerLoadingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlickerLoadingView m18795$$Nest$fgetglobalFlickerLoadingView(SessionsActivity r0) {
        return r0.globalFlickerLoadingView;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m18796$$Nest$fgetlistAdapter(SessionsActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetnoOtherSessionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18797$$Nest$fgetnoOtherSessionsRow(SessionsActivity r0) {
        return r0.noOtherSessionsRow;
    }

    /* renamed from: -$$Nest$fgetotherSessionsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18798$$Nest$fgetotherSessionsEndRow(SessionsActivity r0) {
        return r0.otherSessionsEndRow;
    }

    /* renamed from: -$$Nest$fgetotherSessionsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18799$$Nest$fgetotherSessionsSectionRow(SessionsActivity r0) {
        return r0.otherSessionsSectionRow;
    }

    /* renamed from: -$$Nest$fgetotherSessionsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18800$$Nest$fgetotherSessionsStartRow(SessionsActivity r0) {
        return r0.otherSessionsStartRow;
    }

    /* renamed from: -$$Nest$fgetotherSessionsTerminateDetail, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18801$$Nest$fgetotherSessionsTerminateDetail(SessionsActivity r0) {
        return r0.otherSessionsTerminateDetail;
    }

    /* renamed from: -$$Nest$fgetpasswordSessions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18802$$Nest$fgetpasswordSessions(SessionsActivity r0) {
        return r0.passwordSessions;
    }

    /* renamed from: -$$Nest$fgetpasswordSessionsDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18803$$Nest$fgetpasswordSessionsDetailRow(SessionsActivity r0) {
        return r0.passwordSessionsDetailRow;
    }

    /* renamed from: -$$Nest$fgetpasswordSessionsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18804$$Nest$fgetpasswordSessionsEndRow(SessionsActivity r0) {
        return r0.passwordSessionsEndRow;
    }

    /* renamed from: -$$Nest$fgetpasswordSessionsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18805$$Nest$fgetpasswordSessionsSectionRow(SessionsActivity r0) {
        return r0.passwordSessionsSectionRow;
    }

    /* renamed from: -$$Nest$fgetpasswordSessionsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18806$$Nest$fgetpasswordSessionsStartRow(SessionsActivity r0) {
        return r0.passwordSessionsStartRow;
    }

    /* renamed from: -$$Nest$fgetqrCodeDividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18807$$Nest$fgetqrCodeDividerRow(SessionsActivity r0) {
        return r0.qrCodeDividerRow;
    }

    /* renamed from: -$$Nest$fgetqrCodeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18808$$Nest$fgetqrCodeRow(SessionsActivity r0) {
        return r0.qrCodeRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18809$$Nest$fgetrowCount(SessionsActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsessions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18810$$Nest$fgetsessions(SessionsActivity r0) {
        return r0.sessions;
    }

    /* renamed from: -$$Nest$fgetterminateAllSessionsDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18811$$Nest$fgetterminateAllSessionsDetailRow(SessionsActivity r0) {
        return r0.terminateAllSessionsDetailRow;
    }

    /* renamed from: -$$Nest$fgetterminateAllSessionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18812$$Nest$fgetterminateAllSessionsRow(SessionsActivity r0) {
        return r0.terminateAllSessionsRow;
    }

    /* renamed from: -$$Nest$fgetttlDays, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18813$$Nest$fgetttlDays(SessionsActivity r0) {
        return r0.ttlDays;
    }

    /* renamed from: -$$Nest$fgetttlDivideRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18814$$Nest$fgetttlDivideRow(SessionsActivity r0) {
        return r0.ttlDivideRow;
    }

    /* renamed from: -$$Nest$fgetttlHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18815$$Nest$fgetttlHeaderRow(SessionsActivity r0) {
        return r0.ttlHeaderRow;
    }

    /* renamed from: -$$Nest$fgetttlRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18816$$Nest$fgetttlRow(SessionsActivity r0) {
        return r0.ttlRow;
    }

    /* renamed from: -$$Nest$fgetundoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoView m18817$$Nest$fgetundoView(SessionsActivity r0) {
        return r0.undoView;
    }

    /* renamed from: -$$Nest$fputrepeatLoad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18818$$Nest$fputrepeatLoad(SessionsActivity r0, int r1) {
        r0.repeatLoad = r1;
    }

    /* renamed from: -$$Nest$mopenCameraScanActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18819$$Nest$mopenCameraScanActivity(SessionsActivity r0) {
        r0.openCameraScanActivity();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18820$$Nest$mupdateRows(SessionsActivity r0) {
        r0.updateRows();
    }

    public SessionsActivity(int r2) {
        this.sessions = new ArrayList();
        this.passwordSessions = new ArrayList();
        this.bots = new ArrayList();
        this.repeatLoad = 0;
        this.VIEW_TYPE_TEXT = 0;
        this.VIEW_TYPE_INFO = 1;
        this.VIEW_TYPE_HEADER = 2;
        this.VIEW_TYPE_SESSION = 4;
        this.VIEW_TYPE_SCANQR = 5;
        this.VIEW_TYPE_SETTINGS = 6;
        this.currentType = r2;
    }

    public static /* synthetic */ int access$000(SessionsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(SessionsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(SessionsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$300(SessionsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(SessionsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(SessionsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(SessionsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(SessionsActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$createView$10(TLRPC.TL_error r2, TLObject r3) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (r2 == null) goto L7;
    L9:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
    L10:
        loadSessions(false);
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L9;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.AllWebSessionsTerminated)).show();
        goto L10
    }

    private /* synthetic */ void lambda$createView$11(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda23(this, r3, r2));
    }

    private /* synthetic */ void lambda$createView$12(AlertDialog r2, int r3) {
        if (this.currentType != 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TLRPC.TL_auth_resetAuthorizations(), new SessionsActivity$$ExternalSyntheticLambda16(this));
        return;
    L6:
        TL_account.resetWebAuthorizations r22 = new TL_account.resetWebAuthorizations();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new SessionsActivity$$ExternalSyntheticLambda17(this));
    }

    private /* synthetic */ void lambda$createView$13(int r2) {
        this.bots.remove(r2);
        updateRows();
        ListAdapter r1 = this.listAdapter;
        if (r1 == null) goto L6;
        r1.notifyDataSetChanged();
        return;
    }

    private /* synthetic */ void lambda$createView$15(AlertDialog r1, TLRPC.TL_error r2, TLRPC.TL_authorization r3) {
        r1.dismiss();     // Catch: Exception -> L4
    L6:
        if (r2 != null) goto L13;
        this.sessions.remove(r3);
        this.passwordSessions.remove(r3);
        updateRows();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L14;
        r0.notifyDataSetChanged();
        return;
    L14:
        return;
    L13:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$createView$16(AlertDialog r1, TLRPC.TL_authorization r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda25(this, r1, r4, r2));
    }

    private /* synthetic */ void lambda$createView$17(AlertDialog r1, TLRPC.TL_error r2, TLRPC.TL_webAuthorization r3) {
        r1.dismiss();     // Catch: Exception -> L4
    L6:
        if (r2 != null) goto L13;
        this.sessions.remove(r3);
        updateRows();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L14;
        r0.notifyDataSetChanged();
        return;
    L14:
        return;
    L13:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$createView$18(AlertDialog r1, TLRPC.TL_webAuthorization r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda22(this, r1, r4, r2));
    }

    private /* synthetic */ void lambda$createView$19(int r4, boolean[] r5, AlertDialog r6, int r7) {
        if (getParentActivity() == null) goto L20;
        AlertDialog r62 = new AlertDialog(getParentActivity(), 3);
        r62.setCanCancel(false);
        r62.show();
        if (this.currentType != 0) goto L15;
        int r52 = this.otherSessionsStartRow;
        if (r4 >= r52) goto L10;
    L12:
        TLRPC.TL_authorization r42 = (TLRPC.TL_authorization) this.passwordSessions.get(r4 - this.passwordSessionsStartRow);
    L13:
        TL_account.resetAuthorization r53 = new TL_account.resetAuthorization();
        r53.hash = r42.hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r53, new SessionsActivity$$ExternalSyntheticLambda20(this, r62, r42));
        return;
    L10:
        if (r4 >= this.otherSessionsEndRow) goto L12;
        r42 = (TLRPC.TL_authorization) this.sessions.get(r4 - r52);
        goto L13
    L15:
        TLRPC.TL_webAuthorization r43 = (TLRPC.TL_webAuthorization) this.sessions.get(r4 - this.otherSessionsStartRow);
        TL_account.resetWebAuthorization r0 = new TL_account.resetWebAuthorization();
        r0.hash = r43.hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SessionsActivity$$ExternalSyntheticLambda21(this, r62, r43));
        if (r5[0] == false) goto L19;
        MessagesController.getInstance(this.currentAccount).blockPeer(r43.bot_id);
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$createView$2(AlertDialog.Builder r2, View r3) {
        r2.getDismissRunnable().run();
        Integer r22 = (Integer) r3.getTag();
        if (r22.intValue() != 0) goto L6;
        int r23 = 7;
    L15:
        TL_account.setAuthorizationTTL r32 = new TL_account.setAuthorizationTTL();
        r32.authorization_ttl_days = r23;
        this.ttlDays = r23;
        ListAdapter r24 = this.listAdapter;
        if (r24 == null) goto L18;
        r24.notifyDataSetChanged();
    L18:
        getConnectionsManager().sendRequest(r32, new SessionsActivity$$ExternalSyntheticLambda13());
        return;
    L6:
        if (r22.intValue() != 1) goto L9;
        r23 = 90;
        goto L15
    L9:
        if (r22.intValue() != 2) goto L12;
        r23 = 183;
        goto L15
    L12:
        if (r22.intValue() != 3) goto L14;
        r23 = 365;
        goto L15
    L14:
        r23 = 0;
        goto L15
    }

    private /* synthetic */ void lambda$createView$20(View r21, int r22) {
        boolean r4 = true;
        if (r22 != this.ttlRow) goto L28;
        if (getParentActivity() == null) goto L134;
        int r1 = this.ttlDays;
        if (r1 > 7) goto L11;
        int r12 = 0;
    L17:
        AlertDialog.Builder r2 = new AlertDialog.Builder(getParentActivity());
        r2.setTitle(LocaleController.getString(R.string.SessionsSelfDestruct));
        String[] r6 = {LocaleController.formatPluralString("Weeks", 1, new Object[0]), LocaleController.formatPluralString("Months", 3, new Object[0]), LocaleController.formatPluralString("Months", 6, new Object[0]), LocaleController.formatPluralString("Years", 1, new Object[0])};
        LinearLayout r8 = new LinearLayout(getParentActivity());
        r8.setOrientation(1);
        r2.setView(r8);
        int r9 = 0;
    L19:
        if (r9 >= 4) goto L25;
        RadioColorCell r10 = new RadioColorCell(getParentActivity());
        r10.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r10.setTag(Integer.valueOf(r9));
        r10.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        String r11 = r6[r9];
        if (r12 != r9) goto L23;
        boolean r122 = true;
    L24:
        r10.setTextAndValue(r11, r122);
        r8.addView(r10);
        r10.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r10.setOnClickListener(new SessionsActivity$$ExternalSyntheticLambda5(this, r2));
        r9 = r9 + 1;
        goto L19
    L23:
        r122 = false;
        goto L24
    L25:
        r2.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r2.create());
        return;
    L11:
        if (r1 > 93) goto L14;
        r12 = 1;
        goto L17
    L14:
        if (r1 > 183) goto L16;
        r12 = 2;
        goto L17
    L16:
        r12 = 3;
        goto L17
    L134:
        return;
    L28:
        if (r22 != this.terminateAllSessionsRow) goto L59;
        if (getParentActivity() == null) goto L135;
        ArrayList<TL_account.TL_connectedBot> r13 = this.bots;
        if (r13 != null) goto L35;
    L50:
        AlertDialog.Builder r14 = new AlertDialog.Builder(getParentActivity());
        if (this.currentType != 0) goto L53;
        r14.setMessage(LocaleController.getString(R.string.AreYouSureSessions));
        r14.setTitle(LocaleController.getString(R.string.AreYouSureSessionsTitle));
        String r23 = LocaleController.getString(R.string.Terminate);
    L54:
        r14.setPositiveButton(r23, new SessionsActivity$$ExternalSyntheticLambda7(this));
        r14.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r15 = r14.create();
        showDialog(r15);
        TextView r0 = (TextView) r15.getButton(-1);
        if (r0 == null) goto L130;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L130:
        return;
    L53:
        r14.setMessage(LocaleController.getString(R.string.AreYouSureWebSessions));
        r14.setTitle(LocaleController.getString(R.string.TerminateWebSessionsTitle));
        r23 = LocaleController.getString(R.string.Disconnect);
        goto L54
    L35:
        if (r13.isEmpty() == true) goto L50;
        SpannableStringBuilder r16 = new SpannableStringBuilder();
        ArrayList<TL_account.TL_connectedBot> r24 = this.bots;
        int r3 = r24.size();
        int r42 = 0;
    L37:
        if (r42 >= r3) goto L48;
        TL_account.TL_connectedBot r62 = r24.get(r42);
        r42 = r42 + 1;
        TLRPC.User r63 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r62.bot_id));
        if (r63 == null) goto L37;
        String r7 = UserObject.getPublicUsername(r63);
        if (TextUtils.isEmpty(r7) == false) goto L44;
        r16.append(UserObject.getUserName(r63));
        goto L37
    L44:
        if (r16.length() <= 0) goto L46;
        r16.append(", ");
    L46:
        SpannableStringBuilder r64 = new SpannableStringBuilder("@").append(r7);
        r64.setSpan(new URLSpanNoUnderline("https://t.me/" + r7), 0, r64.length(), 33);
        r16.append(r64);
        goto L37
    L48:
        AlertsCreator.showAlertWithCheckbox(getContext(), LocaleController.getString(R.string.AreYouSureSessionsTitle), LocaleController.getString(R.string.AreYouSureSessions), LocaleController.formatSpannable(R.string.AlsoTerminateChatbot, new Object[]{r16}), LocaleController.getString(R.string.Terminate), new SessionsActivity$$ExternalSyntheticLambda6(this), this.resourceProvider);
        return;
    L135:
        return;
    L59:
        if (r22 < this.botSessionsStartRow) goto L73;
        if (r22 >= this.botSessionsEndRow) goto L73;
        if (getParentActivity() == null) goto L136;
        ArrayList<TL_account.TL_connectedBot> r25 = this.bots;
        if (r25 != null) goto L68;
        return;
    L68:
        if (r25.isEmpty() == true) goto L137;
        int r17 = r22 - this.botSessionsStartRow;
        new ChatbotSheet(getContext(), this.bots.get(r17), new SessionsActivity$$ExternalSyntheticLambda8(this, r17), this.resourceProvider).show();
        return;
    L137:
        return;
    L136:
        return;
    L73:
        if (r22 < this.otherSessionsStartRow) goto L77;
        if (r22 >= this.otherSessionsEndRow) goto L77;
    L83:
        if (getParentActivity() != null) goto L86;
        return;
    L86:
        if (this.currentType == 0) goto L88;
        AlertDialog.Builder r26 = new AlertDialog.Builder(getParentActivity());
        boolean[] r72 = new boolean[1];
        if (this.currentType != 0) goto L102;
        r26.setMessage(LocaleController.getString(R.string.TerminateSessionText));
        r26.setTitle(LocaleController.getString(R.string.AreYouSureSessionTitle));
        CharSequence r43 = LocaleController.getString(R.string.Terminate);
    L115:
        r26.setPositiveButton(r43, new SessionsActivity$$ExternalSyntheticLambda10(this, r22, r72));
        r26.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r18 = r26.create();
        showDialog(r18);
        TextView r02 = (TextView) r18.getButton(-1);
        if (r02 == null) goto L133;
        r02.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L133:
        return;
    L102:
        TLRPC.TL_webAuthorization r82 = (TLRPC.TL_webAuthorization) this.sessions.get(r22 - this.otherSessionsStartRow);
        r26.setMessage(LocaleController.formatString("TerminateWebSessionText", R.string.TerminateWebSessionText, new Object[]{r82.domain}));
        r26.setTitle(LocaleController.getString(R.string.TerminateWebSessionTitle));
        CharSequence r92 = LocaleController.getString(R.string.Disconnect);
        FrameLayout r102 = new FrameLayout(getParentActivity());
        TLRPC.User r83 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r82.bot_id));
        if (r83 == null) goto L105;
        String r84 = UserObject.getFirstName(r83);
    L106:
        CheckBoxCell r123 = new CheckBoxCell(getParentActivity(), 1);
        r123.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r123.setText(LocaleController.formatString("TerminateWebSessionStop", R.string.TerminateWebSessionStop, new Object[]{r84}), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        if (LocaleController.isRTL == false) goto L109;
        int r44 = AndroidUtilities.dp(16.0f);
    L111:
        if (LocaleController.isRTL == false) goto L113;
        int r85 = AndroidUtilities.dp(8.0f);
    L114:
        r123.setPadding(r44, 0, r85, 0);
        r102.addView(r123, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        r123.setOnClickListener(new SessionsActivity$$ExternalSyntheticLambda9(r72));
        r26.setCustomViewOffset(16);
        r26.setView(r102);
        r43 = r92;
        goto L115
    L113:
        r85 = AndroidUtilities.dp(16.0f);
        goto L114
    L109:
        r44 = AndroidUtilities.dp(8.0f);
        goto L111
    L105:
        r84 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L106
    L88:
        if (r22 != this.currentSessionRow) goto L90;
        TLRPC.TL_authorization r19 = this.currentSession;
    L97:
        showSessionBottomSheet(r19, r4);
        return;
    L90:
        int r27 = this.otherSessionsStartRow;
        if (r22 >= r27) goto L93;
    L96:
        r19 = (TLRPC.TL_authorization) this.passwordSessions.get(r22 - this.passwordSessionsStartRow);
    L95:
        r4 = false;
        goto L97
    L93:
        if (r22 >= this.otherSessionsEndRow) goto L96;
        r19 = (TLRPC.TL_authorization) this.sessions.get(r22 - r27);
    L77:
        if (r22 < this.passwordSessionsStartRow) goto L81;
        if (r22 < this.passwordSessionsEndRow) goto L83;
    L81:
        if (r22 == this.currentSessionRow) goto L83;
    }

    private /* synthetic */ void lambda$createView$3() {
        BusinessChatbotController.getInstance(this.currentAccount).invalidate(true);
    }

    private /* synthetic */ void lambda$createView$4(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda27(this));
    }

    private /* synthetic */ void lambda$createView$5(TLRPC.TL_error r2, TLObject r3) {
        if (getParentActivity() == null) goto L12;
        if (r2 == null) goto L7;
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L11;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.AllSessionsTerminated)).show();
        loadSessions(false);
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$createView$6(TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda24(this, r5, r4));
        int r42 = 0;
    L4:
        if (r42 >= 16) goto L10;
        UserConfig r52 = UserConfig.getInstance(r42);
        if (r52.isClientActivated() == false) goto L9;
        r52.registeredForPush = false;
        r52.saveConfig(false);
        MessagesController.getInstance(r42).registerForPush(SharedConfig.pushType, SharedConfig.pushString);
        ConnectionsManager.getInstance(r42).setUserId(r52.getClientUserId());
    L9:
        r42 = r42 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$createView$7(Boolean r4) {
        if (r4 != null) goto L4;
    L10:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TLRPC.TL_auth_resetAuthorizations(), new SessionsActivity$$ExternalSyntheticLambda15(this));
        return;
    L4:
        if (r4.booleanValue() == false) goto L10;
        ArrayList<TL_account.TL_connectedBot> r42 = this.bots;
        if (r42 == null) goto L10;
        if (r42.isEmpty() == true) goto L10;
        TL_account.updateConnectedBot r43 = new TL_account.updateConnectedBot();
        r43.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.bots.get(0).bot_id);
        r43.deleted = true;
        r43.recipients = new TL_account.TL_inputBusinessBotRecipients();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r43, new SessionsActivity$$ExternalSyntheticLambda14(this));
        goto L10
    }

    private /* synthetic */ void lambda$createView$8(TLRPC.TL_error r2, TLObject r3) {
        if (getParentActivity() == null) goto L12;
        if (r2 == null) goto L7;
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L11;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.AllSessionsTerminated)).show();
        loadSessions(false);
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$createView$9(TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda26(this, r5, r4));
        int r42 = 0;
    L4:
        if (r42 >= 16) goto L10;
        UserConfig r52 = UserConfig.getInstance(r42);
        if (r52.isClientActivated() == false) goto L9;
        r52.registeredForPush = false;
        r52.saveConfig(false);
        MessagesController.getInstance(r42).registerForPush(SharedConfig.pushType, SharedConfig.pushString);
        ConnectionsManager.getInstance(r42).setUserId(r52.getClientUserId());
    L9:
        r42 = r42 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$loadSessions$21(boolean r1) {
        loadSessions(r1);
    }

    private /* synthetic */ void lambda$loadSessions$22(TLRPC.TL_error r4, TLObject r5, boolean r6) {
        int r0 = 0;
        this.loading = false;
        ListAdapter r1 = this.listAdapter;
        if (r1 == null) goto L5;
        r1.getItemCount();
    L5:
        if (r4 != null) goto L19;
        this.sessions.clear();
        this.passwordSessions.clear();
        TL_account.authorizations r52 = (TL_account.authorizations) r5;
        int r42 = r52.authorizations.size();
    L7:
        if (r0 >= r42) goto L16;
        TLRPC.TL_authorization r12 = r52.authorizations.get(r0);
        if ((r12.flags & 1) == 0) goto L12;
        this.currentSession = r12;
    L15:
        r0 = r0 + 1;
        goto L7
    L12:
        if (r12.password_pending == false) goto L14;
        this.passwordSessions.add(r12);
        goto L15
    L14:
        this.sessions.add(r12);
        goto L15
    L16:
        this.ttlDays = r52.authorization_ttl_days;
        updateRows();
        Delegate r43 = this.delegate;
        if (r43 == null) goto L19;
        r43.sessionsLoaded();
    L19:
        ListAdapter r44 = this.listAdapter;
        if (r44 == null) goto L22;
        r44.notifyDataSetChanged();
    L22:
        Delegate r45 = this.delegate;
        if (r45 == null) goto L25;
        r45.sessionsLoaded();
    L25:
        int r46 = this.repeatLoad;
        if (r46 <= 0) goto L35;
        int r47 = r46 - 1;
        this.repeatLoad = r47;
        if (r47 <= 0) goto L36;
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda19(this, r6), 2500);
        return;
    L36:
        return;
    }

    private /* synthetic */ void lambda$loadSessions$23(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda11(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$loadSessions$24(boolean r1) {
        loadSessions(r1);
    }

    private /* synthetic */ void lambda$loadSessions$25(TLRPC.TL_error r3, TLObject r4, boolean r5) {
        this.loading = false;
        if (r3 != null) goto L5;
        this.sessions.clear();
        TL_account.webAuthorizations r42 = (TL_account.webAuthorizations) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        this.sessions.addAll(r42.authorizations);
        updateRows();
    L5:
        ListAdapter r32 = this.listAdapter;
        if (r32 == null) goto L8;
        r32.notifyDataSetChanged();
    L8:
        Delegate r33 = this.delegate;
        if (r33 == null) goto L11;
        r33.sessionsLoaded();
    L11:
        int r34 = this.repeatLoad;
        if (r34 <= 0) goto L17;
        int r35 = r34 - 1;
        this.repeatLoad = r35;
        if (r35 <= 0) goto L18;
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda18(this, r5), 2500);
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$loadSessions$26(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SessionsActivity$$ExternalSyntheticLambda12(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$onFragmentCreate$0(TL_account.connectedBots r1) {
        if (r1 == null) goto L9;
        this.bots = r1.connected_bots;
        if (this.listAdapter == null) goto L8;
        updateRows();
        this.listAdapter.notifyDataSetChanged();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultFragment$27(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        getParentActivity().startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private void openCameraScanActivity() {
        CameraScanActivity.showAsSheet(this, false, 2, new AnonymousClass6(this));
    }

    private void showSessionBottomSheet(TLRPC.TL_authorization r3, boolean r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        new SessionBottomSheet(this, r3, r4, new AnonymousClass5(this)).show();
    }

    private void updateRows() {
        this.rowCount = 0;
        this.currentSessionSectionRow = -1;
        this.currentSessionRow = -1;
        this.terminateAllSessionsRow = -1;
        this.terminateAllSessionsDetailRow = -1;
        this.passwordSessionsSectionRow = -1;
        this.passwordSessionsStartRow = -1;
        this.passwordSessionsEndRow = -1;
        this.passwordSessionsDetailRow = -1;
        this.otherSessionsSectionRow = -1;
        this.otherSessionsStartRow = -1;
        this.otherSessionsEndRow = -1;
        this.botSessionsStartRow = -1;
        this.botSessionsEndRow = -1;
        this.otherSessionsTerminateDetail = -1;
        this.noOtherSessionsRow = -1;
        this.qrCodeRow = -1;
        this.qrCodeDividerRow = -1;
        this.ttlHeaderRow = -1;
        this.ttlRow = -1;
        this.ttlDivideRow = -1;
        int r2 = this.currentType;
        if (r2 != 0) goto L6;
        this.qrCodeRow = 0;
        this.rowCount = 1 + 1;
        this.qrCodeDividerRow = 1;
    L6:
        if (this.loading == false) goto L11;
        if (r2 != 0) goto L46;
        int r0 = this.rowCount;
        int r1 = r0 + 1;
        this.currentSessionSectionRow = r0;
        this.rowCount = r0 + 2;
        this.currentSessionRow = r1;
        return;
    L46:
        return;
    L11:
        if (this.currentSession == null) goto L14;
        int r02 = this.rowCount;
        int r22 = r02 + 1;
        this.currentSessionSectionRow = r02;
        this.rowCount = r02 + 2;
        this.currentSessionRow = r22;
    L14:
        if (this.passwordSessions.isEmpty() == true) goto L16;
    L25:
        int r03 = this.rowCount;
        int r23 = r03 + 1;
        this.terminateAllSessionsRow = r03;
        this.rowCount = r03 + 2;
        this.terminateAllSessionsDetailRow = r23;
        this.noOtherSessionsRow = -1;
    L27:
        if (this.passwordSessions.isEmpty() == true) goto L30;
        int r04 = this.rowCount;
        int r12 = r04 + 1;
        this.rowCount = r12;
        this.passwordSessionsSectionRow = r04;
        this.passwordSessionsStartRow = r12;
        int r13 = r12 + this.passwordSessions.size();
        this.passwordSessionsEndRow = r13;
        this.rowCount = r13 + 1;
        this.passwordSessionsDetailRow = r13;
    L30:
        if (this.sessions.isEmpty() == true) goto L37;
        int r05 = this.rowCount;
        this.rowCount = r05 + 1;
        this.otherSessionsSectionRow = r05;
        ArrayList<TL_account.TL_connectedBot> r06 = this.bots;
        if (r06 != null) goto L34;
    L36:
        int r07 = this.rowCount;
        this.otherSessionsStartRow = r07;
        this.otherSessionsEndRow = r07 + this.sessions.size();
        int r08 = this.rowCount + this.sessions.size();
        this.rowCount = r08 + 1;
        this.otherSessionsTerminateDetail = r08;
    L43:
        if (this.ttlDays <= 0) goto L47;
        int r09 = this.rowCount;
        this.ttlHeaderRow = r09;
        int r24 = r09 + 2;
        this.ttlRow = r09 + 1;
        this.rowCount = r09 + 3;
        this.ttlDivideRow = r24;
        return;
    L47:
        return;
    L34:
        if (r06.isEmpty() == true) goto L36;
        int r010 = this.rowCount;
        this.botSessionsStartRow = r010;
        int r011 = r010 + this.bots.size();
        this.rowCount = r011;
        this.botSessionsEndRow = r011;
        goto L36
    L37:
        ArrayList<TL_account.TL_connectedBot> r012 = this.bots;
        if (r012 == null) goto L43;
        if (r012.isEmpty() == true) goto L43;
        int r013 = this.rowCount;
        int r14 = r013 + 1;
        this.rowCount = r14;
        this.otherSessionsSectionRow = r013;
        this.botSessionsStartRow = r14;
        int r15 = r14 + this.bots.size();
        this.botSessionsEndRow = r15;
        this.rowCount = r15 + 1;
        this.otherSessionsTerminateDetail = r15;
        goto L43
    L16:
        if (this.sessions.isEmpty() == false) goto L25;
        this.terminateAllSessionsRow = -1;
        this.terminateAllSessionsDetailRow = -1;
        if (this.currentType != 1) goto L21;
    L24:
        int r014 = this.rowCount;
        this.rowCount = r014 + 1;
        this.noOtherSessionsRow = r014;
        goto L27
    L21:
        if (this.currentSession != null) goto L24;
        this.noOtherSessionsRow = -1;
        goto L27
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        FlickerLoadingView r0 = new FlickerLoadingView(r10);
        this.globalFlickerLoadingView = r0;
        r0.setIsSingleCell(true);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r02 = this.currentType;
        ActionBar r2 = this.actionBar;
        if (r02 != 0) goto L5;
        r2.setTitle(LocaleController.getString(R.string.Devices));
    L6:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        INavigationLayout r03 = this.parentLayout;
        if (r03 != null) goto L9;
    L11:
        this.listAdapter = new ListAdapter(this, r10);
        FrameLayout r04 = new FrameLayout(r10);
        this.fragmentView = r04;
        r04.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        EmptyTextProgressView r22 = new EmptyTextProgressView(r10);
        this.emptyView = r22;
        r22.showProgress();
        r04.addView(this.emptyView, LayoutHelper.createFrame(-1, -1, 17));
        AnonymousClass2 r23 = new AnonymousClass2(this, r10);
        this.listView = r23;
        r23.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setLayoutManager(new AnonymousClass3(this, r10, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setAnimateEmptyView(true, 0);
        r04.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        DefaultItemAnimator r1 = new DefaultItemAnimator();
        r1.setDurations(150);
        CubicBezierInterpolator r24 = CubicBezierInterpolator.DEFAULT;
        r1.setMoveInterpolator(r24);
        r1.setTranslationInterpolator(r24);
        this.listView.setItemAnimator(r1);
        this.listView.setOnItemClickListener(new SessionsActivity$$ExternalSyntheticLambda3(this));
        if (this.currentType != 0) goto L14;
        AnonymousClass4 r12 = new AnonymousClass4(this, r10);
        this.undoView = r12;
        r04.addView(r12, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
    L14:
        updateRows();
        return this.fragmentView;
    L9:
        if (r03.isRightLayout() == false) goto L11;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L11
    L5:
        r2.setTitle(LocaleController.getString(R.string.WebSessionsTitle));
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.newSessionReceived) goto L6;
        loadSessions(true);
        return;
    }

    public int getSessionsCount() {
        int r1 = 0;
        if (this.sessions.size() == 0) goto L5;
    L7:
        int r0 = this.sessions.size();
        if (this.currentType != 0) goto L11;
        r1 = 1;
    L11:
        return r0 + r1;
    L5:
        if (this.loading == false) goto L7;
        return 0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, HeaderCell.class, SessionCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        int r26 = Theme.key_text_RedRegular;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r21 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SessionCell.class}, new String[]{"nameTextView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{SessionCell.class}, new String[]{"onlineTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        int r35 = Theme.key_windowBackgroundWhiteGrayText3;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{SessionCell.class}, new String[]{"onlineTextView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SessionCell.class}, new String[]{"detailTextView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SessionCell.class}, new String[]{"detailExTextView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_undo_background));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoImageView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoTextView"}, null, null, null, r26));
        int r17 = Theme.key_undo_infoColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"infoTextView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"textPaint"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"progressPaint"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{UndoView.class}, new String[]{"leftImageView"}, null, null, null, r17));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public void loadSessions(boolean r4) {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (r4 == true) goto L8;
        this.loading = true;
    L8:
        if (this.currentType != 0) goto L11;
        TL_account.getAuthorizations r0 = new TL_account.getAuthorizations();
        int r42 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SessionsActivity$$ExternalSyntheticLambda0(this, r4));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r42, this.classGuid);
        return;
    L11:
        TL_account.getWebAuthorizations r02 = new TL_account.getWebAuthorizations();
        int r43 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new SessionsActivity$$ExternalSyntheticLambda1(this, r4));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r43, this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        UndoView r2 = this.undoView;
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        updateRows();
        loadSessions(false);
        if (this.currentType != 0) goto L5;
        BusinessChatbotController.getInstance(this.currentAccount).load(new SessionsActivity$$ExternalSyntheticLambda2(this));
    L5:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.newSessionReceived);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.newSessionReceived);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
        UndoView r0 = this.undoView;
        if (r0 == null) goto L6;
        r0.setTranslationY(-r4);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        UndoView r2 = this.undoView;
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r2, String[] r3, int[] r4) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (r2 == 34) goto L8;
        return;
    L8:
        if (r4.length > 0) goto L10;
    L13:
        new AlertDialog.Builder(getParentActivity()).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.QRCodePermissionNoCameraWithHint))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new SessionsActivity$$ExternalSyntheticLambda4(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).setTopAnimation(R.raw.permission_request_camera, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).show();
        return;
    L10:
        if (r4[0] != 0) goto L13;
        openCameraScanActivity();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r2, boolean r3) {
        super.onTransitionAnimationEnd(r2, r3);
        if (r2 == false) goto L12;
        if (r3 == true) goto L16;
        this.fragmentOpened = true;
        int r22 = 0;
    L7:
        if (r22 >= this.listView.getChildCount()) goto L17;
        View r32 = this.listView.getChildAt(r22);
        if ((r32 instanceof ScanQRCodeView) == false) goto L11;
        ((ScanQRCodeView) r32).buttonTextView.invalidate();
    L11:
        r22 = r22 + 1;
        goto L7
    L17:
        return;
    L16:
        return;
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public SessionsActivity setHighlightLinkDesktopDevice() {
        this.highlightLinkDesktopDevice = true;
        return this;
    }
}
