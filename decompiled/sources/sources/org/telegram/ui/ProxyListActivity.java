package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ProxyDisableCondition;
import com.exteragram.messenger.proxy.ProxyController;
import com.exteragram.messenger.utils.ui.PopupUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.ProxyRotationController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SlideChooseView;

/* loaded from: classes6.dex */
public class ProxyListActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static final ProxyDisableCondition[] PROXY_DISABLE_CONDITIONS = null;
    private boolean checkedProxies;
    private int connectionsHeaderRow;
    private int currentConnectionState;
    private int deleteAllRow;
    private ActionBarMenuItem deleteMenuItem;
    private ItemTouchHelper itemTouchHelper;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int mainHeaderRow;
    private ActionBarMenuItem pinMenuItem;

    @Keep
    private int proxyAddRow;
    private int proxyDisableRow;
    private int proxyDisableShadowRow;
    private int proxyEndRow;
    private List<SharedConfig.ProxyInfo> proxyList;
    private int proxyShadowRow;
    private int proxyStartRow;
    private final View.OnClickListener refreshActionClickListener;
    private boolean refreshActionEnabled;
    private int rotationRow;
    private int rotationTimeoutInfoRow;
    private int rotationTimeoutRow;
    private int rowCount;
    private NumberTextView selectedCountTextView;
    private List<SharedConfig.ProxyInfo> selectedItems;
    private ActionBarMenuItem shareMenuItem;

    @Keep
    private int useProxyRow;
    private boolean useProxySettings;
    private int useProxyShadowRow;

    /* renamed from: org.telegram.ui.ProxyListActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ProxyListActivity this$0;

        public AnonymousClass1(ProxyListActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ProxyListActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ItemTouchHelper.SimpleCallback {
        private boolean orderChanged;
        final /* synthetic */ ProxyListActivity this$0;

        public AnonymousClass2(ProxyListActivity r1, int r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean canDropOver(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3) {
            int r12 = ProxyListActivity.m18374$$Nest$mgetProxyPosition(this.this$0, r2);
            int r22 = ProxyListActivity.m18374$$Nest$mgetProxyPosition(this.this$0, r3);
            if (r12 == (-1)) goto L11;
            if (r22 != (-1)) goto L6;
            return false;
        L6:
            if (ProxyListActivity.m18376$$Nest$misPinnedProxyPos(this.this$0, r12) == true) goto L8;
            return false;
        L8:
            if (ProxyListActivity.m18376$$Nest$misPinnedProxyPos(this.this$0, r22) == false) goto L15;
            return true;
        L15:
            return false;
        L11:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r2, RecyclerView.ViewHolder r3) {
            super.clearView(r2, r3);
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).setDraggingChild(null);
            r3.itemView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            this.orderChanged = false;
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).invalidate();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback, androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
            int r22 = ProxyListActivity.m18374$$Nest$mgetProxyPosition(this.this$0, r3);
            if (r22 != (-1)) goto L6;
            return 0;
        L6:
            if (ProxyListActivity.m18371$$Nest$mcanStartReorder(this.this$0, r22) == false) goto L8;
            int r1 = 3;
        L10:
            return ItemTouchHelper.Callback.makeMovementFlags(r1, 0);
        L8:
            r1 = 0;
            goto L10
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3) {
            int r12 = ProxyListActivity.m18374$$Nest$mgetProxyPosition(this.this$0, r2);
            int r22 = ProxyListActivity.m18374$$Nest$mgetProxyPosition(this.this$0, r3);
            if (r12 == (-1)) goto L10;
            if (r22 != (-1)) goto L6;
            return false;
        L6:
            if (ProxyListActivity.m18377$$Nest$mmovePinnedProxy(this.this$0, r12, r22) == false) goto L13;
            this.orderChanged = true;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r4, int r5) {
            if (r5 == 0) goto L5;
            if (r4 == null) goto L5;
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).setDraggingChild(r4.itemView);
            r4.itemView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(16.0f), Theme.getColor(Theme.key_windowBackgroundWhite)));
            r4.itemView.bringToFront();
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).invalidate();
        L7:
            super.onSelectedChanged(r4, r5);
            return;
        L5:
            if (r5 != 0) goto L7;
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).setDraggingChild(null);
            ProxyListActivity.m18350$$Nest$fgetlistView(this.this$0).invalidate();
            goto L7
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    /* renamed from: org.telegram.ui.ProxyListActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ProxyListActivity this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: $r8$lambda$xhIkOPO-U9quzV_yvuvMWD4rEV8, reason: not valid java name */
        public static /* synthetic */ void m18381$r8$lambda$xhIkOPOU9quzV_yvuvMWD4rEV8(AnonymousClass3 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$0(r1, r2);
        }

        public AnonymousClass3(ProxyListActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private /* synthetic */ void lambda$onItemClick$0(AlertDialog r3, int r4) {
            ProxyController r32 = ProxyController.getInstance();
            Iterator r42 = ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).iterator();
        L4:
            if (r42.hasNext() == false) goto L7;
            r32.deleteProxy((SharedConfig.ProxyInfo) r42.next());
            goto L4
        L7:
            if (r32.getCurrentProxy() != null) goto L9;
            ProxyListActivity.m18368$$Nest$fputuseProxySettings(this.this$0, false);
        L9:
            NotificationCenter r33 = NotificationCenter.getGlobalInstance();
            ProxyListActivity r0 = this.this$0;
            int r1 = NotificationCenter.proxySettingsChanged;
            r33.removeObserver(r0, r1);
            NotificationCenter.getGlobalInstance().postNotificationName(r1, new Object[0]);
            NotificationCenter.getGlobalInstance().addObserver(this.this$0, r1);
            ProxyListActivity.m18380$$Nest$mupdateRows(this.this$0, true);
            if (ProxyListActivity.m18349$$Nest$fgetlistAdapter(this.this$0) != null) goto L12;
            return;
        L12:
            if (ProxyController.getInstance().getCurrentProxy() != null) goto L14;
            ProxyListActivity.m18349$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(ProxyListActivity.m18365$$Nest$fgetuseProxyRow(this.this$0), 0);
        L14:
            ProxyListActivity.m18349$$Nest$fgetlistAdapter(this.this$0).clearSelected();
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r5) {
            if (r5 != (-1)) goto L5;
            boolean r52 = ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).isEmpty();
            ProxyListActivity r4 = this.this$0;
            if (r52 == false) goto L39;
            r4.finishFragment();
            return;
        L39:
            ProxyListActivity.m18349$$Nest$fgetlistAdapter(r4).clearSelected();
            return;
        L5:
            if (r5 == 0) goto L27;
            if (r5 != 1) goto L8;
            StringBuilder r53 = new StringBuilder();
            Iterator r0 = ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).iterator();
        L14:
            if (r0.hasNext() == false) goto L19;
            SharedConfig.ProxyInfo r2 = (SharedConfig.ProxyInfo) r0.next();
            if (r53.length() <= 0) goto L18;
            r53.append("\n\n");
        L18:
            r53.append(ProxyController.getInstance().buildShareLink(r2));
            goto L14
        L19:
            Intent r02 = new Intent("android.intent.action.SEND");
            r02.setType("text/plain");
            r02.putExtra("android.intent.extra.TEXT", r53.toString());
            if (ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).size() <= 1) goto L22;
            int r54 = R.string.ShareLinks;
        L23:
            Intent r55 = Intent.createChooser(r02, LocaleController.getString(r54));
            r55.setFlags(268435456);
            this.val$context.startActivity(r55);
            if (ProxyListActivity.m18349$$Nest$fgetlistAdapter(this.this$0) == null) goto L44;
            ProxyListActivity.m18349$$Nest$fgetlistAdapter(this.this$0).clearSelected();
            return;
        L44:
            return;
        L22:
            r54 = R.string.ShareLink;
            goto L23
        L8:
            if (r5 != 2) goto L46;
            ProxyListActivity.m18378$$Nest$mtoggleSelectedPins(this.this$0);
            return;
        L46:
            return;
        L27:
            AlertDialog.Builder r56 = new AlertDialog.Builder(this.this$0.getParentActivity());
            if (ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).size() <= 1) goto L30;
            int r1 = R.string.DeleteProxyMultiConfirm;
        L31:
            r56.setMessage(LocaleController.getString(r1));
            r56.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            r56.setTitle(LocaleController.getString(R.string.DeleteProxyTitle));
            r56.setPositiveButton(LocaleController.getString(R.string.Delete), new ProxyListActivity$3$$ExternalSyntheticLambda0(this));
            AlertDialog r57 = r56.create();
            this.this$0.showDialog(r57);
            TextView r42 = (TextView) r57.getButton(-1);
            if (r42 == null) goto L45;
            r42.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L45:
            return;
        L30:
            r1 = R.string.DeleteProxyConfirm;
            goto L31
        }
    }

    /* renamed from: org.telegram.ui.ProxyListActivity$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$ProxyDisableCondition = null;

        static {
            int[] r0 = new int[ProxyDisableCondition.values().length];
            $SwitchMap$com$exteragram$messenger$ProxyDisableCondition = r0;
            r0[ProxyDisableCondition.VPN.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$com$exteragram$messenger$ProxyDisableCondition[ProxyDisableCondition.MOBILE_DATA.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ProxyListActivity this$0;

        public static /* synthetic */ void $r8$lambda$qpOKhu4vpyVJ2fSGHKdC4SSLStA(int r0) {
            SharedConfig.proxyRotationTimeout = r0;
            SharedConfig.saveConfig();
        }

        public ListAdapter(ProxyListActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
            setHasStableIds(true);
        }

        private void checkActionMode() {
            int r1 = ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).size();
            boolean r2 = ProxyListActivity.access$000(this.this$0).isActionModeShowed();
            if (r1 <= 0) goto L8;
            ProxyListActivity.m18363$$Nest$fgetselectedCountTextView(this.this$0).setNumber(r1, r2);
            ProxyListActivity.m18379$$Nest$mupdatePinAction(this.this$0);
            if (r2 == true) goto L11;
            ProxyListActivity.access$100(this.this$0).showActionMode();
            notifyItemRangeChanged(ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0) - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), 2);
            return;
        L11:
            return;
        L8:
            if (r2 == false) goto L12;
            ProxyListActivity.access$200(this.this$0).hideActionMode();
            notifyItemRangeChanged(ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0) - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), 2);
            return;
        }

        public void clearSelected() {
            ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).clear();
            notifyItemRangeChanged(ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0) - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), 1);
            checkActionMode();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ProxyListActivity.m18362$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r2) {
            if (r2 != ProxyListActivity.m18367$$Nest$fgetuseProxyShadowRow(this.this$0)) goto L7;
            return -1;
        L7:
            if (r2 != ProxyListActivity.m18357$$Nest$fgetproxyShadowRow(this.this$0)) goto L11;
            return -2;
        L11:
            if (r2 != ProxyListActivity.m18352$$Nest$fgetproxyAddRow(this.this$0)) goto L15;
            return -3;
        L15:
            if (r2 != ProxyListActivity.m18365$$Nest$fgetuseProxyRow(this.this$0)) goto L19;
            return -4;
        L19:
            if (r2 != ProxyListActivity.m18351$$Nest$fgetmainHeaderRow(this.this$0)) goto L23;
            return -16;
        L23:
            if (r2 != ProxyListActivity.m18353$$Nest$fgetproxyDisableRow(this.this$0)) goto L27;
            return -12;
        L27:
            if (r2 != ProxyListActivity.m18354$$Nest$fgetproxyDisableShadowRow(this.this$0)) goto L31;
            return -17;
        L31:
            if (r2 != ProxyListActivity.m18346$$Nest$fgetconnectionsHeaderRow(this.this$0)) goto L35;
            return -6;
        L35:
            if (r2 != ProxyListActivity.m18348$$Nest$fgetdeleteAllRow(this.this$0)) goto L39;
            return -8;
        L39:
            if (r2 != ProxyListActivity.m18359$$Nest$fgetrotationRow(this.this$0)) goto L43;
            return -9;
        L43:
            if (r2 != ProxyListActivity.m18361$$Nest$fgetrotationTimeoutRow(this.this$0)) goto L47;
            return -10;
        L47:
            if (r2 != ProxyListActivity.m18360$$Nest$fgetrotationTimeoutInfoRow(this.this$0)) goto L51;
            return -11;
        L51:
            if (r2 >= ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0)) goto L53;
            return -7;
        L53:
            if (r2 < ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0)) goto L55;
            return -7;
        L55:
            return ((SharedConfig.ProxyInfo) ProxyListActivity.m18356$$Nest$fgetproxyList(this.this$0).get(r2 - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0))).hashCode();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != ProxyListActivity.m18367$$Nest$fgetuseProxyShadowRow(this.this$0)) goto L5;
            return 0;
        L5:
            if (r2 != ProxyListActivity.m18354$$Nest$fgetproxyDisableShadowRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != ProxyListActivity.m18357$$Nest$fgetproxyShadowRow(this.this$0)) goto L10;
            return 0;
        L10:
            if (r2 != ProxyListActivity.m18352$$Nest$fgetproxyAddRow(this.this$0)) goto L12;
            return 1;
        L12:
            if (r2 != ProxyListActivity.m18348$$Nest$fgetdeleteAllRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r2 != ProxyListActivity.m18353$$Nest$fgetproxyDisableRow(this.this$0)) goto L17;
            return 1;
        L17:
            if (r2 != ProxyListActivity.m18365$$Nest$fgetuseProxyRow(this.this$0)) goto L21;
            return 7;
        L21:
            if (r2 != ProxyListActivity.m18359$$Nest$fgetrotationRow(this.this$0)) goto L25;
            return 3;
        L25:
            if (r2 != ProxyListActivity.m18346$$Nest$fgetconnectionsHeaderRow(this.this$0)) goto L27;
            return 2;
        L27:
            if (r2 != ProxyListActivity.m18351$$Nest$fgetmainHeaderRow(this.this$0)) goto L30;
            return 2;
        L30:
            if (r2 != ProxyListActivity.m18361$$Nest$fgetrotationTimeoutRow(this.this$0)) goto L34;
            return 6;
        L34:
            if (r2 >= ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0)) goto L36;
            return 4;
        L36:
            if (r2 >= ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0)) goto L47;
            return 5;
        L47:
            return 4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != ProxyListActivity.m18365$$Nest$fgetuseProxyRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != ProxyListActivity.m18353$$Nest$fgetproxyDisableRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != ProxyListActivity.m18359$$Nest$fgetrotationRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 != ProxyListActivity.m18352$$Nest$fgetproxyAddRow(this.this$0)) goto L11;
            return true;
        L11:
            if (r22 != ProxyListActivity.m18348$$Nest$fgetdeleteAllRow(this.this$0)) goto L13;
            return true;
        L13:
            if (r22 >= ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0)) goto L15;
            return false;
        L15:
            if (r22 < ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0)) goto L26;
            return false;
        L26:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            boolean r1 = true;
            int r2 = 0;
            switch(r7.getItemViewType()) {
                case 1: goto L51;
                case 2: goto L33;
                case 3: goto L29;
                case 4: goto L25;
                case 5: goto L19;
                case 6: goto L12;
                case 7: goto L5;
                default: goto L78;
            };
        L5:
            TextCheckCell r72 = (TextCheckCell) r7.itemView;
            r72.setTextAndCheck(LocaleController.getString(R.string.UseProxySettings), ProxyListActivity.m18366$$Nest$fgetuseProxySettings(this.this$0), false);
            if (ProxyListActivity.m18366$$Nest$fgetuseProxySettings(this.this$0) == false) goto L8;
            int r6 = Theme.key_windowBackgroundChecked;
        L9:
            r72.setBackgroundColor(Theme.getColor(r6));
            return;
        L8:
            r6 = Theme.key_windowBackgroundUnchecked;
            goto L9
        L19:
            TextDetailProxyCell r73 = (TextDetailProxyCell) r7.itemView;
            SharedConfig.ProxyInfo r0 = (SharedConfig.ProxyInfo) ProxyListActivity.m18356$$Nest$fgetproxyList(this.this$0).get(r8 - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0));
            r73.setProxy(r0);
            if (ProxyController.getInstance().getCurrentProxy() != r0) goto L22;
            boolean r3 = true;
        L23:
            r73.setChecked(r3);
            r73.setItemSelected(ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).contains(ProxyListActivity.m18356$$Nest$fgetproxyList(this.this$0).get(r8 - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0))), false);
            r73.setReorderAvailable(ProxyListActivity.m18370$$Nest$mcanShowReorderHandle(this.this$0, r0), false);
            r73.setSelectionEnabled(!ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).isEmpty(), false);
            return;
        L22:
            r3 = false;
            goto L23
        L25:
            TextInfoPrivacyCell r74 = (TextInfoPrivacyCell) r7.itemView;
            if (r8 != ProxyListActivity.m18360$$Nest$fgetrotationTimeoutInfoRow(this.this$0)) goto L73;
            r74.setText(LocaleController.getString(R.string.ProxyRotationTimeoutInfo));
            return;
        L73:
            return;
        L29:
            TextCheckCell r75 = (TextCheckCell) r7.itemView;
            if (r8 != ProxyListActivity.m18359$$Nest$fgetrotationRow(this.this$0)) goto L74;
            String r62 = LocaleController.getString(R.string.UseProxyRotation);
            boolean r82 = SharedConfig.proxyRotationEnabled;
            r75.setTextAndCheck(r62, r82, r82);
            return;
        L74:
            return;
        L33:
            HeaderCell r76 = (HeaderCell) r7.itemView;
            SimpleTextView r02 = r76.getTextView2();
            if (r02 == null) goto L37;
            r02.setOnClickListener(null);
            r02.setBackground(null);
            r02.setPadding(0, 0, 0, 0);
            r02.setTranslationY(0.0f);
        L37:
            if (r8 != ProxyListActivity.m18346$$Nest$fgetconnectionsHeaderRow(this.this$0)) goto L48;
            r76.setText(LocaleController.getString(R.string.ProxyConnections));
            if (ProxyListActivity.m18375$$Nest$mhasCheckingProxies(this.this$0) == false) goto L42;
            int r83 = R.string.Checking;
        L43:
            r76.setText2(LocaleController.getString(r83));
            if (r02 == null) goto L75;
            r02.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r02.setTranslationY(-AndroidUtilities.dpf2(4.0f));
            r02.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3), 0.12f), 7));
            ProxyListActivity.m18369$$Nest$mbindRefreshActionView(this.this$0, r02);
            return;
        L75:
            return;
        L42:
            r83 = R.string.Refresh;
            goto L43
        L48:
            if (r8 != ProxyListActivity.m18351$$Nest$fgetmainHeaderRow(this.this$0)) goto L76;
            r76.setText(LocaleController.getString(R.string.General));
            r76.setText2(null);
            return;
        L76:
            return;
        L51:
            TextSettingsCell r77 = (TextSettingsCell) r7.itemView;
            r77.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            if (r8 != ProxyListActivity.m18353$$Nest$fgetproxyDisableRow(this.this$0)) goto L54;
            boolean r03 = true;
        L55:
            r77.setBetterLayout(r03);
            if (r8 != ProxyListActivity.m18352$$Nest$fgetproxyAddRow(this.this$0)) goto L64;
            String r84 = LocaleController.getString(R.string.AddProxy);
            if (ProxyListActivity.m18348$$Nest$fgetdeleteAllRow(this.this$0) != (-1)) goto L61;
            r1 = false;
        L61:
            r77.setText(r84, r1);
            return;
        L64:
            if (r8 != ProxyListActivity.m18348$$Nest$fgetdeleteAllRow(this.this$0)) goto L68;
            r77.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
            r77.setText(LocaleController.getString(R.string.DeleteAllProxies), false);
            return;
        L68:
            if (r8 != ProxyListActivity.m18353$$Nest$fgetproxyDisableRow(this.this$0)) goto L77;
            r77.setTextAndValue(LocaleController.getString(R.string.ProxyDisableOn), ProxyListActivity.m18373$$Nest$mgetProxyDisableValue(this.this$0), false);
            return;
        L77:
            return;
        L54:
            r03 = false;
            goto L55
        L78:
            return;
        L12:
            if (r8 != ProxyListActivity.m18361$$Nest$fgetrotationTimeoutRow(this.this$0)) goto L72;
            SlideChooseView r63 = (SlideChooseView) r7.itemView;
            ArrayList r78 = new ArrayList(ProxyRotationController.ROTATION_TIMEOUTS);
            String[] r85 = new String[r78.size()];
        L15:
            if (r2 >= r78.size()) goto L17;
            r85[r2] = LocaleController.formatString(R.string.ProxyRotationTimeoutSeconds, new Object[]{r78.get(r2)});
            r2 = r2 + 1;
            goto L15
        L17:
            r63.setCallback(new ProxyListActivity$ListAdapter$$ExternalSyntheticLambda0());
            r63.setOptions(SharedConfig.proxyRotationTimeout, r85);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4, List r5) {
            if (r3.getItemViewType() != 5) goto L14;
            if (r5.isEmpty() == true) goto L14;
            TextDetailProxyCell r32 = (TextDetailProxyCell) r3.itemView;
            SharedConfig.ProxyInfo r42 = (SharedConfig.ProxyInfo) ProxyListActivity.m18356$$Nest$fgetproxyList(this.this$0).get(r4 - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0));
            if (r5.contains(1) == false) goto L10;
            r32.setItemSelected(ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).contains(r42), true);
            r32.setReorderAvailable(ProxyListActivity.m18370$$Nest$mcanShowReorderHandle(this.this$0, r42), true);
        L10:
            if (r5.contains(2) == false) goto L33;
            r32.setReorderAvailable(ProxyListActivity.m18370$$Nest$mcanShowReorderHandle(this.this$0, r42), true);
            r32.setSelectionEnabled(!ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).isEmpty(), true);
            return;
        L33:
            return;
        L14:
            if (r3.getItemViewType() == 3) goto L18;
            if (r3.getItemViewType() == 7) goto L18;
        L31:
            super.onBindViewHolder(r3, r4, r5);
            return;
        L18:
            if (r5.contains(0) == false) goto L31;
            TextCheckCell r33 = (TextCheckCell) r3.itemView;
            int r52 = ProxyListActivity.m18365$$Nest$fgetuseProxyRow(this.this$0);
            ProxyListActivity r0 = this.this$0;
            if (r4 != r52) goto L28;
            r33.setChecked(ProxyListActivity.m18366$$Nest$fgetuseProxySettings(r0));
            boolean r43 = ProxyListActivity.m18366$$Nest$fgetuseProxySettings(this.this$0);
            if (ProxyListActivity.m18366$$Nest$fgetuseProxySettings(this.this$0) == false) goto L24;
            int r2 = Theme.key_windowBackgroundChecked;
        L25:
            r33.setBackgroundColorAnimated(r43, Theme.getColor(r2));
            return;
        L24:
            r2 = Theme.key_windowBackgroundUnchecked;
            goto L25
        L28:
            if (r4 != ProxyListActivity.m18359$$Nest$fgetrotationRow(r0)) goto L34;
            r33.setChecked(SharedConfig.proxyRotationEnabled);
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L4;
            View r72 = new ShadowSectionCell(this.mContext);
        L24:
            r72.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r72);
        L4:
            if (r8 != 1) goto L6;
            r72 = new TextSettingsCell(this.mContext);
            r72.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L24
        L6:
            if (r8 != 2) goto L8;
            HeaderCell r0 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 6, true);
            r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            HeaderCell r02 = r0;
        L17:
            r72 = r02;
            goto L24
        L8:
            if (r8 != 3) goto L10;
            r72 = new TextCheckCell(this.mContext);
            r72.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L24
        L10:
            if (r8 != 4) goto L12;
            r72 = new TextInfoPrivacyCell(this.mContext);
            goto L24
        L12:
            if (r8 != 6) goto L14;
            r72 = new SlideChooseView(this.mContext);
            r72.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L24
        L14:
            if (r8 == 7) goto L16;
            r72 = new TextDetailProxyCell(this.this$0, this.mContext);
            r72.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L24
        L16:
            TextCheckCell r03 = new TextCheckCell(this.mContext);
            r03.setDrawCheckRipple(true);
            r03.setColors(Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked);
            r03.setTypeface(AndroidUtilities.bold());
            r03.setHeight(56);
            r02 = r03;
            goto L17
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            int r0 = r4.getItemViewType();
            if (r0 != 3) goto L5;
        L6:
            TextCheckCell r02 = (TextCheckCell) r4.itemView;
            int r42 = r4.getAdapterPosition();
            int r1 = ProxyListActivity.m18365$$Nest$fgetuseProxyRow(this.this$0);
            ProxyListActivity r2 = this.this$0;
            if (r42 != r1) goto L15;
            r02.setChecked(ProxyListActivity.m18366$$Nest$fgetuseProxySettings(r2));
            if (ProxyListActivity.m18366$$Nest$fgetuseProxySettings(this.this$0) == false) goto L11;
            int r3 = Theme.key_windowBackgroundChecked;
        L12:
            r02.setBackgroundColor(Theme.getColor(r3));
            return;
        L11:
            r3 = Theme.key_windowBackgroundUnchecked;
            goto L12
        L15:
            if (r42 != ProxyListActivity.m18359$$Nest$fgetrotationRow(r2)) goto L19;
            r02.setChecked(SharedConfig.proxyRotationEnabled);
            return;
        L19:
            return;
        L5:
            if (r0 == 7) goto L6;
        }

        public void selectForReorder(int r5) {
            SharedConfig.ProxyInfo r2 = ProxyListActivity.m18372$$Nest$mgetProxyAtPosition(this.this$0, r5);
            if (r2 != null) goto L6;
            return;
        L6:
            if (ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).size() == 1) goto L8;
        L11:
            ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).clear();
            ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).add(r2);
            notifyItemRangeChanged(ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), ProxyListActivity.m18355$$Nest$fgetproxyEndRow(this.this$0) - ProxyListActivity.m18358$$Nest$fgetproxyStartRow(this.this$0), 1);
        L12:
            checkActionMode();
            return;
        L8:
            if (ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).contains(r2) == false) goto L11;
            notifyItemChanged(r5, 1);
            goto L12
        }

        public void toggleSelected(int r4) {
            SharedConfig.ProxyInfo r0 = ProxyListActivity.m18372$$Nest$mgetProxyAtPosition(this.this$0, r4);
            if (r0 != null) goto L5;
            return;
        L5:
            boolean r1 = ProxyListActivity.m18364$$Nest$fgetselectedItems(this.this$0).contains(r0);
            ProxyListActivity r2 = this.this$0;
            if (r1 == false) goto L8;
            ProxyListActivity.m18364$$Nest$fgetselectedItems(r2).remove(r0);
        L9:
            notifyItemChanged(r4, 1);
            checkActionMode();
            return;
        L8:
            ProxyListActivity.m18364$$Nest$fgetselectedItems(r2).add(r0);
            goto L9
        }
    }

    public class TextDetailProxyCell extends FrameLayout {
        private CheckBox2 checkBox;
        private Drawable checkDrawable;
        private ImageView checkImageView;
        private int color;
        private SharedConfig.ProxyInfo currentInfo;
        private boolean isReorderAvailable;
        private boolean isSelected;
        private boolean isSelectionEnabled;
        private Drawable pinDrawable;
        private ImageView reorderImageView;
        private TextView textView;
        final /* synthetic */ ProxyListActivity this$0;
        private TextView valueTextView;

        /* renamed from: org.telegram.ui.ProxyListActivity$TextDetailProxyCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ TextDetailProxyCell this$1;
            final /* synthetic */ boolean val$enabled;

            public AnonymousClass1(TextDetailProxyCell r1, boolean r2) {
                this.this$1 = r1;
                this.val$enabled = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                boolean r22 = this.val$enabled;
                TextDetailProxyCell r1 = this.this$1;
                if (r22 == false) goto L6;
                TextDetailProxyCell.m18384$$Nest$fgetcheckImageView(r1).setVisibility(8);
                return;
            L6:
                TextDetailProxyCell.m18383$$Nest$fgetcheckBox(r1).setVisibility(8);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r4) {
                boolean r42 = this.val$enabled;
                TextDetailProxyCell r0 = this.this$1;
                if (r42 == false) goto L6;
                TextDetailProxyCell.m18383$$Nest$fgetcheckBox(r0).setAlpha(0.0f);
                TextDetailProxyCell.m18383$$Nest$fgetcheckBox(this.this$1).setVisibility(0);
                return;
            L6:
                TextDetailProxyCell.m18384$$Nest$fgetcheckImageView(r0).setAlpha(0.0f);
                TextDetailProxyCell.m18384$$Nest$fgetcheckImageView(this.this$1).setVisibility(0);
            }
        }

        /* renamed from: $r8$lambda$MYf5pl-oXvlfbIjgTJcsgZytBVU, reason: not valid java name */
        public static /* synthetic */ void m18382$r8$lambda$MYf5ploXvlfbIjgTJcsgZytBVU(TextDetailProxyCell r0) {
            r0.lambda$updateReorderHandle$2();
        }

        public static /* synthetic */ void $r8$lambda$_qlH7D9k7n1ML5pit5bO2nkrAYg(TextDetailProxyCell r0, float r1, float r2, ValueAnimator r3) {
            r0.lambda$setSelectionEnabled$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$gcZbxwIEXIflVduoXspbIt5_jxI(TextDetailProxyCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetcheckBox, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CheckBox2 m18383$$Nest$fgetcheckBox(TextDetailProxyCell r0) {
            return r0.checkBox;
        }

        /* renamed from: -$$Nest$fgetcheckImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m18384$$Nest$fgetcheckImageView(TextDetailProxyCell r0) {
            return r0.checkImageView;
        }

        /* renamed from: -$$Nest$fgetcurrentInfo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SharedConfig.ProxyInfo m18385$$Nest$fgetcurrentInfo(TextDetailProxyCell r0) {
            return r0.currentInfo;
        }

        public TextDetailProxyCell(ProxyListActivity r18, Context r19) {
            this.this$0 = r18;
            super(r19);
            TextView r1 = new TextView(r19);
            this.textView = r1;
            r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            TextView r12 = this.textView;
            TextUtils.TruncateAt r3 = TextUtils.TruncateAt.END;
            r12.setEllipsize(r3);
            TextView r13 = this.textView;
            int r6 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r5 = 5;
        L6:
            r13.setGravity(r5 | 16);
            TextView r14 = this.textView;
            boolean r52 = LocaleController.isRTL;
            if (r52 == false) goto L9;
            int r8 = 5;
        L10:
            int r11 = r8 | 48;
            int r82 = 56;
            if (r52 == false) goto L13;
            int r10 = 56;
        L14:
            float r122 = r10;
            if (r52 == false) goto L17;
            int r53 = 21;
        L18:
            addView(r14, LayoutHelper.createFrame(-2, -2.0f, r11, r122, 10.0f, r53, 0.0f));
            TextView r15 = new TextView(r19);
            this.valueTextView = r15;
            r15.setTextSize(1, 13.0f);
            TextView r16 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L21;
            int r9 = 5;
        L22:
            r16.setGravity(r9);
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
            this.valueTextView.setEllipsize(r3);
            this.valueTextView.setPadding(0, 0, 0, 0);
            TextView r17 = this.valueTextView;
            boolean r4 = LocaleController.isRTL;
            if (r4 == false) goto L25;
            int r92 = 5;
        L26:
            int r123 = r92 | 48;
            if (r4 == false) goto L29;
            int r93 = 56;
        L30:
            float r132 = r93;
            if (r4 == false) goto L33;
            r82 = 21;
        L33:
            addView(r17, LayoutHelper.createFrame(-2, -2.0f, r123, r132, 35.0f, r82, 0.0f));
            ImageView r110 = new ImageView(r19);
            this.checkImageView = r110;
            r110.setImageResource(R.drawable.msg_info);
            ImageView r111 = this.checkImageView;
            int r83 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3);
            PorterDuff.Mode r94 = PorterDuff.Mode.MULTIPLY;
            r111.setColorFilter(new PorterDuffColorFilter(r83, r94));
            ImageView r112 = this.checkImageView;
            ImageView.ScaleType r42 = ImageView.ScaleType.CENTER;
            r112.setScaleType(r42);
            this.checkImageView.setContentDescription(LocaleController.getString(R.string.Edit));
            ImageView r113 = this.checkImageView;
            if (LocaleController.isRTL == false) goto L36;
            int r84 = 3;
        L37:
            addView(r113, LayoutHelper.createFrame(48, 48.0f, r84 | 48, 8.0f, 8.0f, 8.0f, 0.0f));
            this.checkImageView.setOnClickListener(new ProxyListActivity$TextDetailProxyCell$$ExternalSyntheticLambda0(this));
            ImageView r114 = new ImageView(r19);
            this.reorderImageView = r114;
            r114.setImageResource(R.drawable.list_reorder);
            this.reorderImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), r94));
            this.reorderImageView.setScaleType(r42);
            this.reorderImageView.setContentDescription(LocaleController.getString(R.string.ProfileBotReorder));
            this.reorderImageView.setVisibility(8);
            ImageView r115 = this.reorderImageView;
            if (LocaleController.isRTL == false) goto L40;
            int r85 = 3;
        L41:
            addView(r115, LayoutHelper.createFrame(48, 48.0f, r85 | 16, 8.0f, 0.0f, 8.0f, 0.0f));
            CheckBox2 r116 = new CheckBox2(r19, 21);
            this.checkBox = r116;
            r116.setColor(Theme.key_checkbox, Theme.key_radioBackground, Theme.key_checkboxCheck);
            this.checkBox.setDrawBackgroundAsArc(14);
            this.checkBox.setVisibility(8);
            CheckBox2 r117 = this.checkBox;
            if (LocaleController.isRTL == false) goto L44;
            r6 = 5;
        L44:
            addView(r117, LayoutHelper.createFrame(24, 24.0f, r6 | 16, 16.0f, 0.0f, 8.0f, 0.0f));
            setWillNotDraw(false);
            return;
        L40:
            r85 = 5;
            goto L41
        L36:
            r84 = 5;
            goto L37
        L29:
            r93 = 21;
            goto L30
        L25:
            r92 = 3;
            goto L26
        L21:
            r9 = 3;
            goto L22
        L17:
            r53 = 56;
            goto L18
        L13:
            r10 = 21;
            goto L14
        L9:
            r8 = 3;
            goto L10
        L5:
            r5 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            this.this$0.presentFragment(new ProxySettingsActivity(this.currentInfo));
        }

        private /* synthetic */ void lambda$setSelectionEnabled$1(float r3, float r4, ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            float r32 = AndroidUtilities.lerp(r3, r4, r52);
            this.textView.setTranslationX(r32);
            this.valueTextView.setTranslationX(r32);
            this.checkImageView.setTranslationX(r32);
            CheckBox2 r42 = this.checkBox;
            if (LocaleController.isRTL == false) goto L5;
            int r0 = AndroidUtilities.dp(32.0f);
        L6:
            r42.setTranslationX(r0 + r32);
            float r43 = (r52 * 0.5f) + 0.5f;
            this.checkBox.setScaleX(r43);
            this.checkBox.setScaleY(r43);
            this.checkBox.setAlpha(r52);
            float r44 = 1.0f - r52;
            float r53 = (r44 * 0.5f) + 0.5f;
            this.checkImageView.setScaleX(r53);
            this.checkImageView.setScaleY(r53);
            this.checkImageView.setAlpha(r44);
            return;
        L5:
            r0 = -AndroidUtilities.dp(32.0f);
            goto L6
        }

        private /* synthetic */ void lambda$updateReorderHandle$2() {
            if (this.isSelectionEnabled == true) goto L5;
        L6:
            this.reorderImageView.setVisibility(8);
        L7:
            this.reorderImageView.setAlpha(1.0f);
            this.reorderImageView.setScaleX(1.0f);
            this.reorderImageView.setScaleY(1.0f);
            return;
        L5:
            if (this.isReorderAvailable == true) goto L7;
            goto L6
        }

        private void updateReorderHandle(boolean r8) {
            int r1 = 0;
            if (this.isSelectionEnabled == true) goto L5;
        L7:
            boolean r0 = false;
        L8:
            this.reorderImageView.animate().cancel();
            if (r8 == true) goto L16;
            ImageView r82 = this.reorderImageView;
            if (r0 == true) goto L14;
            r1 = 8;
        L14:
            r82.setVisibility(r1);
            this.reorderImageView.setAlpha(1.0f);
            this.reorderImageView.setScaleX(1.0f);
            this.reorderImageView.setScaleY(1.0f);
            return;
        L16:
            ImageView r83 = this.reorderImageView;
            if (r0 == false) goto L21;
            r83.setVisibility(0);
            this.reorderImageView.setAlpha(0.0f);
            this.reorderImageView.setScaleX(0.5f);
            this.reorderImageView.setScaleY(0.5f);
            this.reorderImageView.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
            return;
        L21:
            if (r83.getVisibility() != 0) goto L24;
            this.reorderImageView.animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).withEndAction(new ProxyListActivity$TextDetailProxyCell$$ExternalSyntheticLambda1(this)).start();
            return;
        L24:
            return;
        L5:
            if (this.isReorderAvailable == false) goto L7;
            r0 = true;
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateStatus();
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + 1, TLObject.FLAG_30));
        }

        public void setChecked(boolean r5) {
            if (r5 == true) goto L5;
            this.valueTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            return;
        L5:
            if (this.checkDrawable != null) goto L7;
            this.checkDrawable = getResources().getDrawable(R.drawable.proxy_check).mutate();
        L7:
            Drawable r52 = this.checkDrawable;
            if (r52 == null) goto L10;
            r52.setColorFilter(new PorterDuffColorFilter(this.color, PorterDuff.Mode.MULTIPLY));
        L10:
            boolean r53 = LocaleController.isRTL;
            TextView r1 = this.valueTextView;
            if (r53 == false) goto L14;
            r1.setCompoundDrawablesWithIntrinsicBounds(null, null, this.checkDrawable, null);
            return;
        L14:
            r1.setCompoundDrawablesWithIntrinsicBounds(this.checkDrawable, null, null, null);
        }

        public void setItemSelected(boolean r2, boolean r3) {
            if (r2 != this.isSelected) goto L6;
            if (r3 == false) goto L6;
            return;
        L6:
            this.isSelected = r2;
            this.checkBox.setChecked(r2, r3);
        }

        public void setProxy(SharedConfig.ProxyInfo r5) {
            this.textView.setText(ProxyController.getInstance().getDisplayName(r5));
            this.currentInfo = r5;
            if (this.pinDrawable != null) goto L6;
            this.pinDrawable = getContext().getResources().getDrawable(R.drawable.msg_pin_mini).mutate();
            this.textView.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        L6:
            if (ProxyController.getInstance().isPinned(r5) == false) goto L13;
            this.pinDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_pinnedIcon), PorterDuff.Mode.MULTIPLY));
            boolean r52 = LocaleController.isRTL;
            TextView r1 = this.textView;
            if (r52 == false) goto L11;
            r1.setCompoundDrawablesWithIntrinsicBounds(null, null, this.pinDrawable, null);
            return;
        L11:
            r1.setCompoundDrawablesWithIntrinsicBounds(this.pinDrawable, null, null, null);
            return;
        L13:
            this.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }

        public void setReorderAvailable(boolean r3, boolean r4) {
            if (this.isReorderAvailable != r3) goto L12;
            if (r4 == false) goto L11;
            int r0 = this.reorderImageView.getVisibility();
            if (this.isSelectionEnabled == false) goto L9;
            if (r3 == false) goto L9;
            int r1 = 0;
        L10:
            if (r0 != r1) goto L12;
            return;
        L9:
            r1 = 8;
            goto L10
        L11:
            return;
        L12:
            this.isReorderAvailable = r3;
            updateReorderHandle(r4);
        }

        public void setSelectionEnabled(boolean r7, boolean r8) {
            if (this.isSelectionEnabled != r7) goto L6;
            if (r8 == false) goto L6;
            return;
        L6:
            this.isSelectionEnabled = r7;
            if (LocaleController.isRTL == false) goto L10;
            int r0 = -AndroidUtilities.dp(32.0f);
        L9:
            float r02 = r0;
            float r4 = 1.0f;
            if (r8 == true) goto L29;
            if (r7 == true) goto L16;
            r02 = 0.0f;
        L16:
            this.textView.setTranslationX(r02);
            this.valueTextView.setTranslationX(r02);
            this.checkImageView.setTranslationX(r02);
            CheckBox2 r82 = this.checkBox;
            boolean r3 = LocaleController.isRTL;
            int r1 = AndroidUtilities.dp(32.0f);
            if (r3 == true) goto L20;
            r1 = -r1;
        L20:
            r82.setTranslationX(r1 + r02);
            ImageView r83 = this.checkImageView;
            int r03 = 8;
            if (r7 == false) goto L23;
            int r12 = 8;
        L24:
            r83.setVisibility(r12);
            this.checkImageView.setAlpha(1.0f);
            this.checkImageView.setScaleX(1.0f);
            this.checkImageView.setScaleY(1.0f);
            CheckBox2 r84 = this.checkBox;
            if (r7 == false) goto L27;
            r03 = 0;
        L27:
            r84.setVisibility(r03);
            this.checkBox.setAlpha(1.0f);
            this.checkBox.setScaleX(1.0f);
            this.checkBox.setScaleY(1.0f);
            updateReorderHandle(false);
            return;
        L23:
            r12 = 0;
            goto L24
        L29:
            if (r7 == false) goto L31;
            float r85 = 0.0f;
        L32:
            if (r7 == true) goto L35;
            r4 = 0.0f;
        L35:
            ValueAnimator r13 = ValueAnimator.ofFloat(new float[]{r85, r4}).setDuration(200);
            r13.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r13.addUpdateListener(new ProxyListActivity$TextDetailProxyCell$$ExternalSyntheticLambda2(this, 0.0f, r02));
            r13.addListener(new AnonymousClass1(this, r7));
            r13.start();
            updateReorderHandle(true);
            return;
        L31:
            r85 = 1.0f;
            goto L32
        L10:
            r0 = AndroidUtilities.dp(32.0f);
            goto L9
        }

        public void setValue(CharSequence r1) {
            this.valueTextView.setText(r1);
        }

        public void updateStatus() {
            if (ProxyController.getInstance().getCurrentProxy() == this.currentInfo) goto L5;
        L21:
            SharedConfig.ProxyInfo r0 = this.currentInfo;
            if (r0.checking == false) goto L25;
            this.valueTextView.setText(LocaleController.getString(R.string.Checking));
            int r02 = Theme.key_windowBackgroundWhiteGrayText2;
        L32:
            this.color = Theme.getColor(r02);
            this.valueTextView.setTag(Integer.valueOf(r02));
            this.valueTextView.setTextColor(this.color);
            Drawable r03 = this.checkDrawable;
            if (r03 == null) goto L36;
            r03.setColorFilter(new PorterDuffColorFilter(this.color, PorterDuff.Mode.MULTIPLY));
            return;
        L36:
            return;
        L25:
            if (r0.available == false) goto L31;
            long r04 = r0.ping;
            TextView r1 = this.valueTextView;
            if (r04 == 0) goto L29;
            r1.setText(LocaleController.getString(R.string.Available) + ", " + LocaleController.formatString("Ping", R.string.Ping, new Object[]{Long.valueOf(this.currentInfo.ping)}));
        L30:
            r02 = Theme.key_windowBackgroundWhiteGreenText;
            goto L32
        L29:
            r1.setText(LocaleController.getString(R.string.Available));
            goto L30
        L31:
            this.valueTextView.setText(LocaleController.getString(R.string.Unavailable));
            r02 = Theme.key_text_RedRegular;
            goto L32
        L5:
            if (ProxyListActivity.m18366$$Nest$fgetuseProxySettings(this.this$0) == false) goto L21;
            if (ProxyListActivity.m18347$$Nest$fgetcurrentConnectionState(this.this$0) != 3) goto L9;
        L12:
            r02 = Theme.key_windowBackgroundWhiteBlueText6;
            long r6 = this.currentInfo.ping;
            TextView r62 = this.valueTextView;
            if (r6 == 0) goto L15;
            r62.setText(LocaleController.getString(R.string.Connected) + ", " + LocaleController.formatString("Ping", R.string.Ping, new Object[]{Long.valueOf(this.currentInfo.ping)}));
        L16:
            SharedConfig.ProxyInfo r12 = this.currentInfo;
            if (r12.checking == true) goto L32;
            if (r12.available == true) goto L32;
            r12.availableCheckTime = 0;
            goto L32
        L15:
            r62.setText(LocaleController.getString(R.string.Connected));
            goto L16
        L9:
            if (ProxyListActivity.m18347$$Nest$fgetcurrentConnectionState(this.this$0) == 5) goto L12;
            r02 = Theme.key_windowBackgroundWhiteGrayText2;
            this.valueTextView.setText(LocaleController.getString(R.string.Connecting));
            goto L32
        }
    }

    public static /* synthetic */ void $r8$lambda$18ASX0HRHIX6RtQzGdJgGyNdq_Y(ProxyListActivity r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$3d7kdf-24OcbtgKjvYmjCkz_eAw, reason: not valid java name */
    public static /* synthetic */ void m18340$r8$lambda$3d7kdf24OcbtgKjvYmjCkz_eAw(ProxyListActivity r0) {
        r0.updateVisibleCheckCellColors();
    }

    public static /* synthetic */ void $r8$lambda$57y7ZdotTSmgtYRFpkwuBGu1ZR8(ProxyListActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: $r8$lambda$Eutc7WcmHg4GsfuhBE-udXpMy0E, reason: not valid java name */
    public static /* synthetic */ void m18341$r8$lambda$Eutc7WcmHg4GsfuhBEudXpMy0E(ProxyListActivity r0, View r1) {
        r0.lambda$didReceivedNotification$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$Q4MvhcXUL51Lzm8dGd4P8OBtNYA(SharedConfig.ProxyInfo r1, long r2) {
        AndroidUtilities.runOnUIThread(new ProxyListActivity$$ExternalSyntheticLambda10(r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$U02X_0PCCHJy_IhwpQ7ffWcGzsc(ProxyListActivity r0, View r1) {
        r0.lambda$updateVisibleCheckCellColors$8(r1);
    }

    /* renamed from: $r8$lambda$VVYDAde8jIAPlkb1cO-ErRoPYZE, reason: not valid java name */
    public static /* synthetic */ boolean m18342$r8$lambda$VVYDAde8jIAPlkb1cOErRoPYZE(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$d9KJjS3lpAwQxq7l6lkQFv-pYyM, reason: not valid java name */
    public static /* synthetic */ void m18343$r8$lambda$d9KJjS3lpAwQxq7l6lkQFvpYyM(SharedConfig.ProxyInfo r3, long r4) {
        r3.availableCheckTime = SystemClock.elapsedRealtime();
        r3.checking = false;
        if (r4 != (-1)) goto L5;
        r3.available = false;
        r3.ping = 0;
    L6:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxyCheckDone, new Object[]{r3});
        return;
    L5:
        r3.ping = r4;
        r3.available = true;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$fqRBwBoJ76MhWkUIMWMbSQAdYa8(ProxyListActivity r0, boolean[] r1) {
        r0.lambda$showProxyDisableDialog$5(r1);
    }

    /* renamed from: $r8$lambda$l-onUm0VafSbzpwYJZx9y-4fcjY, reason: not valid java name */
    public static /* synthetic */ void m18344$r8$lambda$lonUm0VafSbzpwYJZx9y4fcjY(ProxyListActivity r0, View r1, int r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$u2s1SJl-C5e2N5VBlW1MtueQwf8, reason: not valid java name */
    public static /* synthetic */ boolean m18345$r8$lambda$u2s1SJlC5e2N5VBlW1MtueQwf8(ProxyListActivity r0, View r1, int r2) {
        return r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: -$$Nest$fgetconnectionsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18346$$Nest$fgetconnectionsHeaderRow(ProxyListActivity r0) {
        return r0.connectionsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetcurrentConnectionState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18347$$Nest$fgetcurrentConnectionState(ProxyListActivity r0) {
        return r0.currentConnectionState;
    }

    /* renamed from: -$$Nest$fgetdeleteAllRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18348$$Nest$fgetdeleteAllRow(ProxyListActivity r0) {
        return r0.deleteAllRow;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m18349$$Nest$fgetlistAdapter(ProxyListActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m18350$$Nest$fgetlistView(ProxyListActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmainHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18351$$Nest$fgetmainHeaderRow(ProxyListActivity r0) {
        return r0.mainHeaderRow;
    }

    /* renamed from: -$$Nest$fgetproxyAddRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18352$$Nest$fgetproxyAddRow(ProxyListActivity r0) {
        return r0.proxyAddRow;
    }

    /* renamed from: -$$Nest$fgetproxyDisableRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18353$$Nest$fgetproxyDisableRow(ProxyListActivity r0) {
        return r0.proxyDisableRow;
    }

    /* renamed from: -$$Nest$fgetproxyDisableShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18354$$Nest$fgetproxyDisableShadowRow(ProxyListActivity r0) {
        return r0.proxyDisableShadowRow;
    }

    /* renamed from: -$$Nest$fgetproxyEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18355$$Nest$fgetproxyEndRow(ProxyListActivity r0) {
        return r0.proxyEndRow;
    }

    /* renamed from: -$$Nest$fgetproxyList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m18356$$Nest$fgetproxyList(ProxyListActivity r0) {
        return r0.proxyList;
    }

    /* renamed from: -$$Nest$fgetproxyShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18357$$Nest$fgetproxyShadowRow(ProxyListActivity r0) {
        return r0.proxyShadowRow;
    }

    /* renamed from: -$$Nest$fgetproxyStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18358$$Nest$fgetproxyStartRow(ProxyListActivity r0) {
        return r0.proxyStartRow;
    }

    /* renamed from: -$$Nest$fgetrotationRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18359$$Nest$fgetrotationRow(ProxyListActivity r0) {
        return r0.rotationRow;
    }

    /* renamed from: -$$Nest$fgetrotationTimeoutInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18360$$Nest$fgetrotationTimeoutInfoRow(ProxyListActivity r0) {
        return r0.rotationTimeoutInfoRow;
    }

    /* renamed from: -$$Nest$fgetrotationTimeoutRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18361$$Nest$fgetrotationTimeoutRow(ProxyListActivity r0) {
        return r0.rotationTimeoutRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18362$$Nest$fgetrowCount(ProxyListActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetselectedCountTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NumberTextView m18363$$Nest$fgetselectedCountTextView(ProxyListActivity r0) {
        return r0.selectedCountTextView;
    }

    /* renamed from: -$$Nest$fgetselectedItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m18364$$Nest$fgetselectedItems(ProxyListActivity r0) {
        return r0.selectedItems;
    }

    /* renamed from: -$$Nest$fgetuseProxyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18365$$Nest$fgetuseProxyRow(ProxyListActivity r0) {
        return r0.useProxyRow;
    }

    /* renamed from: -$$Nest$fgetuseProxySettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18366$$Nest$fgetuseProxySettings(ProxyListActivity r0) {
        return r0.useProxySettings;
    }

    /* renamed from: -$$Nest$fgetuseProxyShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18367$$Nest$fgetuseProxyShadowRow(ProxyListActivity r0) {
        return r0.useProxyShadowRow;
    }

    /* renamed from: -$$Nest$fputuseProxySettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18368$$Nest$fputuseProxySettings(ProxyListActivity r0, boolean r1) {
        r0.useProxySettings = r1;
    }

    /* renamed from: -$$Nest$mbindRefreshActionView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18369$$Nest$mbindRefreshActionView(ProxyListActivity r0, SimpleTextView r1) {
        r0.bindRefreshActionView(r1);
    }

    /* renamed from: -$$Nest$mcanShowReorderHandle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18370$$Nest$mcanShowReorderHandle(ProxyListActivity r0, SharedConfig.ProxyInfo r1) {
        return r0.canShowReorderHandle(r1);
    }

    /* renamed from: -$$Nest$mcanStartReorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18371$$Nest$mcanStartReorder(ProxyListActivity r0, int r1) {
        return r0.canStartReorder(r1);
    }

    /* renamed from: -$$Nest$mgetProxyAtPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedConfig.ProxyInfo m18372$$Nest$mgetProxyAtPosition(ProxyListActivity r0, int r1) {
        return r0.getProxyAtPosition(r1);
    }

    /* renamed from: -$$Nest$mgetProxyDisableValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m18373$$Nest$mgetProxyDisableValue(ProxyListActivity r0) {
        return r0.getProxyDisableValue();
    }

    /* renamed from: -$$Nest$mgetProxyPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18374$$Nest$mgetProxyPosition(ProxyListActivity r0, RecyclerView.ViewHolder r1) {
        return r0.getProxyPosition(r1);
    }

    /* renamed from: -$$Nest$mhasCheckingProxies, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18375$$Nest$mhasCheckingProxies(ProxyListActivity r0) {
        return r0.hasCheckingProxies();
    }

    /* renamed from: -$$Nest$misPinnedProxyPos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18376$$Nest$misPinnedProxyPos(ProxyListActivity r0, int r1) {
        return r0.isPinnedProxyPos(r1);
    }

    /* renamed from: -$$Nest$mmovePinnedProxy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18377$$Nest$mmovePinnedProxy(ProxyListActivity r0, int r1, int r2) {
        return r0.movePinnedProxy(r1, r2);
    }

    /* renamed from: -$$Nest$mtoggleSelectedPins, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18378$$Nest$mtoggleSelectedPins(ProxyListActivity r0) {
        r0.toggleSelectedPins();
    }

    /* renamed from: -$$Nest$mupdatePinAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18379$$Nest$mupdatePinAction(ProxyListActivity r0) {
        r0.updatePinAction();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18380$$Nest$mupdateRows(ProxyListActivity r0, boolean r1) {
        r0.updateRows(r1);
    }

    static {
        PROXY_DISABLE_CONDITIONS = ProxyDisableCondition.values();
    }

    public ProxyListActivity() {
        this.selectedItems = new ArrayList();
        this.proxyList = new ArrayList();
        this.refreshActionClickListener = new ProxyListActivity$$ExternalSyntheticLambda7(this);
    }

    private void HeaderChangeNotify() {
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L8;
        int r1 = this.connectionsHeaderRow;
        if (r1 < 0) goto L9;
        r0.notifyItemChanged(r1);
        return;
    L9:
        return;
    }

    public static /* synthetic */ ActionBar access$000(ProxyListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ProxyListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(ProxyListActivity r0) {
        return r0.actionBar;
    }

    private void bindRefreshActionView(SimpleTextView r5) {
        if (hasCheckingProxies() == false) goto L6;
        int r0 = R.string.Checking;
    L7:
        r5.setText(LocaleController.getString(r0));
        if (this.refreshActionEnabled == true) goto L10;
        int r02 = Theme.key_windowBackgroundWhiteGrayText3;
    L15:
        if (this.refreshActionEnabled == true) goto L17;
        float r1 = 0.55f;
    L21:
        Object r2 = r5.getTag();
        if ((r2 instanceof Integer) == true) goto L24;
    L25:
        r5.setTag(Integer.valueOf(r02));
        r5.setTextColor(Theme.getColor(r02));
    L27:
        if (r5.getAlpha() == r1) goto L29;
        r5.setAlpha(r1);
    L29:
        boolean r03 = r5.isClickable();
        boolean r12 = this.refreshActionEnabled;
        if (r03 == r12) goto L32;
        r5.setClickable(r12);
    L32:
        r5.setOnClickListener(this.refreshActionClickListener);
        return;
    L24:
        if (((Integer) r2).intValue() == r02) goto L27;
    L17:
        if (Theme.isCurrentThemeDark() == false) goto L19;
        r1 = 1.0f;
        goto L21
    L19:
        r1 = 0.75f;
        goto L21
    L10:
        if (Theme.isCurrentThemeDark() == false) goto L12;
        r02 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L15
    L12:
        r02 = Theme.key_windowBackgroundWhiteBlackText;
        goto L15
    L6:
        r0 = R.string.Refresh;
        goto L7
    }

    private boolean canShowReorderHandle(SharedConfig.ProxyInfo r2) {
        if (r2 == null) goto L14;
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0.isActionModeShowed() == true) goto L8;
        return false;
    L8:
        if (this.selectedItems.isEmpty() == false) goto L10;
        return false;
    L10:
        if (ProxyController.getInstance().getPinnedCount() > 1) goto L12;
        return false;
    L12:
        if (ProxyController.getInstance().isPinned(r2) == false) goto L20;
        return true;
    L20:
        return false;
    L14:
        return false;
    }

    private boolean canStartReorder(int r3) {
        if (this.actionBar.isActionModeShowed() == true) goto L5;
        return false;
    L5:
        if (ProxyController.getInstance().getPinnedCount() > 1) goto L7;
        return false;
    L7:
        if (isPinnedProxyPos(r3) == false) goto L12;
        return true;
    L12:
        return false;
    }

    private int getItemPosition(View r4, int r5) {
        if (r4 == null) goto L16;
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L16;
        RecyclerView.ViewHolder r02 = r0.findContainingViewHolder(r4);
        if (r02 == null) goto L13;
        int r2 = r02.getAdapterPosition();
        if (r2 != (-1)) goto L11;
        r2 = r02.getLayoutPosition();
    L11:
        if (r2 == (-1)) goto L13;
        return r2;
    L13:
        int r3 = this.listView.getChildAdapterPosition(r4);
        if (r3 == (-1)) goto L16;
        return r3;
    L16:
        return r5;
    }

    private SharedConfig.ProxyInfo getProxyAtPosition(int r2) {
        if (isProxyPosition(r2) == true) goto L5;
        return null;
    L5:
        return this.proxyList.get(r2 - this.proxyStartRow);
    }

    private String getProxyDisableConditionName(ProxyDisableCondition r1) {
        int r0 = AnonymousClass4.$SwitchMap$com$exteragram$messenger$ProxyDisableCondition[r1.ordinal()];
        if (r0 == 1) goto L11;
        if (r0 == 2) goto L9;
        return LocaleController.getString(R.string.ProxyDisableOnWiFi);
    L9:
        return LocaleController.getString(R.string.ProxyDisableOnMobileData);
    L11:
        return LocaleController.getString(R.string.ProxyDisableOnVpn);
    }

    private String getProxyDisableValue() {
        ArrayList r0 = new ArrayList();
        ProxyDisableCondition[] r1 = PROXY_DISABLE_CONDITIONS;
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        ProxyDisableCondition r4 = r1[r3];
        if (ExteraConfig.isProxyDisabledOn(r4) == false) goto L7;
        r0.add(getProxyDisableConditionName(r4));
    L7:
        r3 = r3 + 1;
        goto L3
    L9:
        if (r0.isEmpty() == false) goto L13;
        return LocaleController.getString(R.string.ProxyDisableNever);
    L13:
        if (r0.size() != PROXY_DISABLE_CONDITIONS.length) goto L17;
        return LocaleController.getString(R.string.ProxyDisableAlways);
    L17:
        return TextUtils.join(", ", r0);
    }

    private int getProxyPosition(RecyclerView.ViewHolder r4) {
        if (r4 != null) goto L5;
        return -1;
    L5:
        int r1 = r4.getAdapterPosition();
        if (r1 != (-1)) goto L8;
        r1 = r4.getLayoutPosition();
    L8:
        if (r1 != (-1)) goto L13;
        RecyclerListView r2 = this.listView;
        if (r2 == null) goto L13;
        r1 = r2.getChildAdapterPosition(r4.itemView);
    L13:
        if (isProxyPosition(r1) == false) goto L15;
        return r1;
    L15:
        return -1;
    }

    private int getSelectedPinAction() {
        return ProxyController.getInstance().getSelectedPinAction(this.selectedItems);
    }

    private boolean hasCheckingProxies() {
        Iterator<SharedConfig.ProxyInfo> r1 = this.proxyList.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r1.next().checking == false) goto L4;
        return true;
    L9:
        return false;
    }

    private boolean isPinnedProxyPos(int r2) {
        return ProxyController.getInstance().isPinned(getProxyAtPosition(r2));
    }

    private boolean isProxyPosition(int r2) {
        if (r2 >= this.proxyStartRow) goto L5;
        return false;
    L5:
        if (r2 >= this.proxyEndRow) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isRefreshActionEnabled() {
        if (this.proxyStartRow != (-1)) goto L5;
        return false;
    L5:
        if (hasCheckingProxies() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createView$1(AlertDialog r3, int r4) {
        ProxyController r32 = ProxyController.getInstance();
        r32.clearAll();
        Iterator<SharedConfig.ProxyInfo> r42 = this.proxyList.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        r32.deleteProxy(r42.next());
        goto L4
    L6:
        this.useProxySettings = false;
        NotificationCenter r43 = NotificationCenter.getGlobalInstance();
        int r0 = NotificationCenter.proxySettingsChanged;
        r43.removeObserver(this, r0);
        NotificationCenter.getGlobalInstance().postNotificationName(r0, new Object[0]);
        NotificationCenter.getGlobalInstance().addObserver(this, r0);
        updateRows(true);
        ListAdapter r44 = this.listAdapter;
        if (r44 == null) goto L11;
        r44.notifyItemChanged(this.useProxyRow, 0);
        this.listAdapter.clearSelected();
        return;
    }

    private /* synthetic */ void lambda$createView$2(View r6, int r7) {
        int r72 = getItemPosition(r6, r7);
        if (r72 != this.useProxyRow) goto L25;
        if (ProxyController.getInstance().getCurrentProxy() == null) goto L7;
    L13:
        this.useProxySettings = !this.useProxySettings;
        updateRows(true);
        MessagesController.getGlobalMainSettings();
        TextCheckCell r62 = (TextCheckCell) r6;
        r62.setChecked(this.useProxySettings);
        boolean r73 = this.useProxySettings;
        if (r73 == false) goto L16;
        int r0 = Theme.key_windowBackgroundChecked;
    L17:
        r62.setBackgroundColorAnimated(r73, Theme.getColor(r0));
        SharedPreferences.Editor r63 = MessagesController.getGlobalMainSettings().edit();
        r63.putBoolean("proxy_enabled", this.useProxySettings);
        r63.apply();
        reapplyCurrentProxySettings();
        NotificationCenter r64 = NotificationCenter.getGlobalInstance();
        int r74 = NotificationCenter.proxySettingsChanged;
        r64.removeObserver(this, r74);
        NotificationCenter.getGlobalInstance().postNotificationName(r74, new Object[0]);
        NotificationCenter.getGlobalInstance().addObserver(this, r74);
        int r65 = this.proxyStartRow;
    L19:
        if (r65 >= this.proxyEndRow) goto L76;
        RecyclerListView.Holder r75 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(r65);
        if (r75 == null) goto L23;
        ((TextDetailProxyCell) r75.itemView).updateStatus();
    L23:
        r65 = r65 + 1;
        goto L19
    L76:
        return;
    L16:
        r0 = Theme.key_windowBackgroundUnchecked;
        goto L17
    L7:
        if (this.proxyList.isEmpty() == true) goto L11;
        ProxyController.getInstance().setCurrentProxy(this.proxyList.get(0));
        if (this.useProxySettings == true) goto L13;
        SharedPreferences.Editor r76 = MessagesController.getGlobalMainSettings().edit();
        ProxyController.getInstance().getCurrentProxy().settings.toSharedPreferences(r76);
        r76.commit();
        goto L13
    L11:
        presentFragment(new ProxySettingsActivity());
        return;
    L25:
        if (r72 != this.rotationRow) goto L29;
        boolean r77 = !SharedConfig.proxyRotationEnabled;
        SharedConfig.proxyRotationEnabled = r77;
        ((TextCheckCell) r6).setChecked(r77);
        SharedConfig.saveConfig();
        updateRows(true);
        return;
    L29:
        if (r72 != this.proxyDisableRow) goto L33;
        showProxyDisableDialog();
        return;
    L33:
        if (r72 < this.proxyStartRow) goto L59;
        if (r72 >= this.proxyEndRow) goto L59;
        if (this.selectedItems.isEmpty() == true) goto L40;
        this.listAdapter.toggleSelected(r72);
        return;
    L40:
        SharedConfig.ProxyInfo r66 = this.proxyList.get(r72 - this.proxyStartRow);
        boolean r78 = this.useProxySettings;
        this.useProxySettings = true;
        SharedPreferences.Editor r02 = MessagesController.getGlobalMainSettings().edit();
        r66.settings.toSharedPreferences(r02);
        r02.putBoolean("proxy_enabled", this.useProxySettings);
        r02.apply();
        ProxyController.getInstance().setCurrentProxy(r66);
        int r03 = this.proxyStartRow;
    L42:
        if (r03 >= this.proxyEndRow) goto L51;
        RecyclerListView.Holder r1 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(r03);
        if (r1 == null) goto L50;
        TextDetailProxyCell r12 = (TextDetailProxyCell) r1.itemView;
        if (TextDetailProxyCell.m18385$$Nest$fgetcurrentInfo(r12) != r66) goto L48;
        boolean r4 = true;
    L49:
        r12.setChecked(r4);
        r12.updateStatus();
        goto L50
    L48:
        r4 = false;
    L50:
        r03 = r03 + 1;
        goto L42
    L51:
        updateRows(false);
        RecyclerListView.Holder r67 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(this.useProxyRow);
        if (r67 == null) goto L56;
        TextCheckCell r68 = (TextCheckCell) r67.itemView;
        r68.setChecked(true);
        if (r78 == true) goto L56;
        r68.setBackgroundColorAnimated(true, Theme.getColor(Theme.key_windowBackgroundChecked));
    L56:
        reapplyCurrentProxySettings();
        return;
    L59:
        if (r72 != this.proxyAddRow) goto L63;
        presentFragment(new ProxySettingsActivity());
        return;
    L63:
        if (r72 != this.deleteAllRow) goto L74;
        AlertDialog.Builder r69 = new AlertDialog.Builder(getParentActivity());
        r69.setMessage(LocaleController.getString(R.string.DeleteAllProxiesConfirm));
        r69.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r69.setTitle(LocaleController.getString(R.string.DeleteProxyTitle));
        r69.setPositiveButton(LocaleController.getString(R.string.Delete), new ProxyListActivity$$ExternalSyntheticLambda5(this));
        AlertDialog r610 = r69.create();
        showDialog(r610);
        TextView r5 = (TextView) r610.getButton(-1);
        if (r5 == null) goto L75;
        r5.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L75:
        return;
    }

    private /* synthetic */ boolean lambda$createView$3(View r4, int r5) {
        int r52 = getItemPosition(r4, r5);
        if (r52 >= this.proxyStartRow) goto L5;
    L20:
        return false;
    L5:
        if (r52 >= this.proxyEndRow) goto L20;
        if (this.selectedItems.isEmpty() == false) goto L11;
        this.listAdapter.toggleSelected(r52);
        return true;
    L11:
        if (canStartReorder(r52) == true) goto L13;
    L18:
        this.listAdapter.toggleSelected(r52);
        return true;
    L13:
        if (this.itemTouchHelper == null) goto L18;
        this.listAdapter.selectForReorder(r52);
        this.listView.cancelClickRunnables(true);
        r4.setPressed(false);
        r4.jumpDrawablesToCurrentState();
        RecyclerView.ViewHolder r42 = this.listView.findContainingViewHolder(r4);
        if (getProxyPosition(r42) != r52) goto L17;
        this.itemTouchHelper.startDrag(r42);
    L17:
        return true;
    }

    private /* synthetic */ void lambda$didReceivedNotification$9(View r2) {
        View r1 = this.listView.getChildViewHolder(r2).itemView;
        if ((r1 instanceof TextDetailProxyCell) == false) goto L10;
        TextDetailProxyCell r12 = (TextDetailProxyCell) r1;
        if (TextDetailProxyCell.m18385$$Nest$fgetcurrentInfo(r12) != ProxyController.getInstance().getCurrentProxy()) goto L7;
        boolean r22 = true;
    L8:
        r12.setChecked(r22);
        r12.updateStatus();
        return;
    L7:
        r22 = false;
        goto L8
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (this.refreshActionEnabled == false) goto L6;
        refreshProxyStatus();
        return;
    }

    private /* synthetic */ void lambda$showProxyDisableDialog$5(boolean[] r4) {
        int r0 = 0;
    L3:
        ProxyDisableCondition[] r1 = PROXY_DISABLE_CONDITIONS;
        if (r0 >= r1.length) goto L6;
        ExteraConfig.setProxyDisabledOn(r1[r0], r4[r0]);
        r0 = r0 + 1;
        goto L3
    L6:
        ApplicationLoader.checkProxyForNetworkState();
        ListAdapter r42 = this.listAdapter;
        if (r42 == null) goto L11;
        r42.notifyItemChanged(this.proxyDisableRow);
        return;
    }

    private /* synthetic */ void lambda$updateVisibleCheckCellColors$8(View r8) {
        if ((r8 instanceof TextCheckCell) == true) goto L5;
        return;
    L5:
        TextCheckCell r1 = (TextCheckCell) r8;
        if (this.listView.getChildAdapterPosition(r8) != this.useProxyRow) goto L13;
        r1.setColors(Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked);
        if (this.useProxySettings == false) goto L10;
        int r7 = Theme.key_windowBackgroundChecked;
    L11:
        r1.setBackgroundColor(Theme.getColor(r7));
        return;
    L10:
        r7 = Theme.key_windowBackgroundUnchecked;
        goto L11
    L13:
        r1.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
    }

    private boolean movePinnedProxy(int r7, int r8) {
        if (isPinnedProxyPos(r7) == true) goto L5;
    L17:
        return false;
    L5:
        if (isPinnedProxyPos(r8) == false) goto L17;
        if (r7 == r8) goto L17;
        int r0 = this.proxyStartRow;
        int r2 = r7 - r0;
        int r02 = r8 - r0;
        SharedConfig.ProxyInfo r3 = getProxyAtPosition(r7);
        SharedConfig.ProxyInfo r4 = getProxyAtPosition(r8);
        if (r3 == null) goto L17;
        if (r4 == null) goto L17;
        if (ProxyController.getInstance().movePinnedProxy(r3, r4) == true) goto L15;
        return false;
    L15:
        this.proxyList.remove(r2);
        this.proxyList.add(r02, r3);
        this.listAdapter.notifyItemMoved(r7, r8);
        this.listAdapter.notifyItemRangeChanged(Math.min(r7, r8), Math.abs(r7 - r8) + 1, 1);
        return true;
    }

    private void reapplyCurrentProxySettings() {
        SharedConfig.ProxyInfo r0 = ProxyController.getInstance().getCurrentProxy();
        if (r0 == null) goto L6;
        ConnectionsManager.setProxySettings(this.useProxySettings, r0.settings);
        return;
    }

    private void refreshProxyStatus() {
        if (this.proxyStartRow != (-1)) goto L5;
        return;
    L5:
        if (hasCheckingProxies() == true) goto L25;
        this.checkedProxies = false;
        int r1 = this.proxyList.size();
        int r2 = 0;
        boolean r3 = false;
    L8:
        if (r2 >= r1) goto L14;
        SharedConfig.ProxyInfo r4 = this.proxyList.get(r2);
        if (r4.checking == true) goto L13;
        r3 = true;
        r4.checking = true;
        ConnectionsManager.getInstance(this.currentAccount).checkProxy(r4.settings, new ProxyListActivity$$ExternalSyntheticLambda9(r4));
    L13:
        r2 = r2 + 1;
        goto L8
    L14:
        if (r3 == false) goto L24;
        ListAdapter r12 = this.listAdapter;
        if (r12 == null) goto L18;
        int r22 = this.proxyStartRow;
        r12.notifyItemRangeChanged(r22, this.proxyEndRow - r22);
    L18:
        updateRefreshActionState(false);
        return;
    L24:
        return;
    }

    private void reloadSettingsFromPreferences() {
        boolean r2 = false;
        if (MessagesController.getGlobalMainSettings().getBoolean("proxy_enabled", false) == true) goto L5;
    L7:
        this.useProxySettings = r2;
        return;
    L5:
        if (ProxyController.getInstance().getProxyList().isEmpty() == true) goto L7;
        r2 = true;
        goto L7
    }

    private void showProxyDisableDialog() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        ProxyDisableCondition[] r0 = PROXY_DISABLE_CONDITIONS;
        CharSequence[] r2 = new CharSequence[r0.length];
        boolean[] r3 = new boolean[r0.length];
        int r02 = 0;
    L6:
        ProxyDisableCondition[] r1 = PROXY_DISABLE_CONDITIONS;
        if (r02 >= r1.length) goto L9;
        r2[r02] = getProxyDisableConditionName(r1[r02]);
        r3[r02] = ExteraConfig.isProxyDisabledOn(r1[r02]);
        r02 = r02 + 1;
        goto L6
    L9:
        PopupUtils.showMultiSelectDialog(r2, r3, LocaleController.getString(R.string.ProxyDisableOn), getParentActivity(), new ProxyListActivity$$ExternalSyntheticLambda8(this), getResourceProvider());
    }

    private void toggleSelectedPins() {
        ProxyController.PinOperationResult r0 = ProxyController.getInstance().applySelectedPinAction(this.selectedItems);
        if (r0 != ProxyController.PinOperationResult.LIMIT_REACHED) goto L7;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.formatString("ProxyPinLimitReached", R.string.ProxyPinLimitReached, new Object[]{Integer.valueOf(ProxyController.getInstance().getMaxPinnedProxies())})).show();
        return;
    L7:
        if (r0 != ProxyController.PinOperationResult.CHANGED) goto L12;
        updateRows(true);
        ListAdapter r3 = this.listAdapter;
        if (r3 == null) goto L13;
        r3.clearSelected();
        return;
    L13:
        return;
    }

    private void updatePinAction() {
        if (this.pinMenuItem == null) goto L14;
        int r0 = getSelectedPinAction();
        ActionBarMenuItem r1 = this.pinMenuItem;
        if (r0 != 0) goto L8;
        r1.setVisibility(8);
        return;
    L8:
        r1.setVisibility(0);
        ActionBarMenuItem r3 = this.pinMenuItem;
        if (r0 != 2) goto L11;
        int r02 = R.drawable.msg_unpin;
    L12:
        r3.setIcon(r02);
        return;
    L11:
        r02 = R.drawable.msg_pin;
        goto L12
    }

    private void updateRefreshActionState(boolean r2) {
        boolean r0 = isRefreshActionEnabled();
        if (r2 == false) goto L5;
    L7:
        this.refreshActionEnabled = r0;
        HeaderChangeNotify();
        return;
    L5:
        if (this.refreshActionEnabled != r0) goto L7;
    }

    private void updateRows(boolean r10) {
        boolean r0 = false;
        this.useProxyRow = 0;
        int r2 = 1 + 1;
        this.useProxyShadowRow = 1;
        this.rowCount = r2 + 1;
        this.mainHeaderRow = r2;
        SharedConfig.ProxyInfo r22 = ProxyController.getInstance().getCurrentProxy();
        if (this.useProxySettings == false) goto L11;
        if (r22 == null) goto L11;
        if (ProxyController.getInstance().getProxyList().size() <= 1) goto L11;
        int r23 = this.rowCount;
        int r3 = r23 + 1;
        this.rowCount = r3;
        this.rotationRow = r23;
        if (SharedConfig.proxyRotationEnabled == false) goto L10;
        this.rotationTimeoutRow = r3;
        this.rowCount = r23 + 3;
        this.rotationTimeoutInfoRow = r23 + 2;
    L12:
        int r24 = this.rowCount;
        this.proxyDisableRow = r24;
        this.proxyDisableShadowRow = r24 + 1;
        this.rowCount = r24 + 3;
        this.connectionsHeaderRow = r24 + 2;
        if (r10 == false) goto L28;
        this.proxyList.clear();
        this.proxyList.addAll(ProxyController.getInstance().getProxyList());
        if (this.checkedProxies == true) goto L26;
        Iterator<SharedConfig.ProxyInfo> r25 = this.proxyList.iterator();
    L18:
        if (r25.hasNext() == false) goto L24;
        SharedConfig.ProxyInfo r32 = r25.next();
        if (r32.checking == true) goto L23;
        if (r32.availableCheckTime != 0) goto L18;
    L23:
        r0 = true;
    L24:
        if (r0 == true) goto L26;
        this.checkedProxies = true;
    L26:
        ProxyController.getInstance().sortProxyList(this.proxyList, r0, ProxyController.getInstance().getCurrentProxy());
    L28:
        if (this.proxyList.isEmpty() == true) goto L30;
        int r02 = this.rowCount;
        this.proxyStartRow = r02;
        int r03 = r02 + this.proxyList.size();
        this.rowCount = r03;
        this.proxyEndRow = r03;
    L31:
        int r04 = this.rowCount;
        this.proxyAddRow = r04;
        this.rowCount = r04 + 2;
        this.proxyShadowRow = r04 + 1;
        if (this.proxyList.size() < 10) goto L34;
        int r05 = this.rowCount;
        this.rowCount = r05 + 1;
        this.deleteAllRow = r05;
    L35:
        this.refreshActionEnabled = isRefreshActionEnabled();
        if (r10 == false) goto L45;
        ListAdapter r9 = this.listAdapter;
        if (r9 == null) goto L46;
        r9.notifyDataSetChanged();
        return;
    L46:
        return;
    L45:
        return;
    L34:
        this.deleteAllRow = -1;
        goto L35
    L30:
        this.proxyStartRow = -1;
        this.proxyEndRow = -1;
        goto L31
    L10:
        this.rotationTimeoutRow = -1;
        this.rotationTimeoutInfoRow = -1;
    L11:
        this.rotationRow = -1;
        this.rotationTimeoutRow = -1;
        this.rotationTimeoutInfoRow = -1;
        goto L12
    }

    private void updateVisibleCheckCellColors() {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.forAllChild(new ProxyListActivity$$ExternalSyntheticLambda6(this));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.ProxySettings));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r12);
        FrameLayout r02 = new FrameLayout(r12);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r03 = (FrameLayout) this.fragmentView;
        RecyclerListView r3 = new RecyclerListView(r12);
        this.listView = r3;
        r3.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setTranslationInterpolator(CubicBezierInterpolator.DEFAULT);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r32 = this.listView;
        LinearLayoutManager r4 = new LinearLayoutManager(r12, 1, false);
        this.layoutManager = r4;
        r32.setLayoutManager(r4);
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        ItemTouchHelper r04 = new ItemTouchHelper(new AnonymousClass2(this, 3, 0));
        this.itemTouchHelper = r04;
        r04.attachToRecyclerView(this.listView);
        this.listView.setOnItemClickListener(new ProxyListActivity$$ExternalSyntheticLambda2(this));
        this.listView.setOnItemLongClickListener(new ProxyListActivity$$ExternalSyntheticLambda3(this));
        ActionBarMenu r05 = this.actionBar.createActionMode();
        NumberTextView r33 = new NumberTextView(r05.getContext());
        this.selectedCountTextView = r33;
        r33.setTextSize(18);
        this.selectedCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        r05.addView(this.selectedCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 72, 0, 0, 0));
        this.selectedCountTextView.setOnTouchListener(new ProxyListActivity$$ExternalSyntheticLambda4());
        this.pinMenuItem = r05.addItemWithWidth(2, R.drawable.msg_pin, AndroidUtilities.dp(54.0f));
        ActionBarMenuItem r1 = r05.addItemWithWidth(1, R.drawable.msg_share, AndroidUtilities.dp(54.0f));
        this.shareMenuItem = r1;
        r1.setContentDescription(LocaleController.getString(R.string.StickersShare));
        ActionBarMenuItem r06 = r05.addItemWithWidth(0, R.drawable.msg_delete, AndroidUtilities.dp(54.0f));
        this.deleteMenuItem = r06;
        r06.setContentDescription(LocaleController.getString(R.string.Delete));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass3(this, r12));
        return this.fragmentView;
    L5:
        if (r0.isLayersLayout() == false) goto L7;
        this.actionBar.setOccupyStatusBar(false);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r6, int r7, Object... r8) {
        if (r6 != NotificationCenter.proxyChangedByRotation) goto L7;
        this.listView.forAllChild(new ProxyListActivity$$ExternalSyntheticLambda1(this));
        updateRows(false);
        return;
    L7:
        if (r6 != NotificationCenter.proxySettingsChanged) goto L11;
        reloadSettingsFromPreferences();
        ProxyController.getInstance().loadProxyList();
        updateRows(true);
        return;
    L11:
        if (r6 != NotificationCenter.didUpdateConnectionState) goto L27;
        int r62 = ConnectionsManager.getInstance(r7).getConnectionState();
        if (this.currentConnectionState == r62) goto L58;
        this.currentConnectionState = r62;
        SharedConfig.ProxyInfo r63 = ProxyController.getInstance().getCurrentProxy();
        if (this.listView == null) goto L59;
        if (r63 == null) goto L60;
        int r64 = this.proxyList.indexOf(r63);
        if (r64 < 0) goto L23;
        RecyclerListView.Holder r65 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(r64 + this.proxyStartRow);
        if (r65 == null) goto L23;
        ((TextDetailProxyCell) r65.itemView).updateStatus();
    L23:
        if (this.currentConnectionState != 3) goto L61;
        updateRows(true);
        return;
    L61:
        return;
    L60:
        return;
    L59:
        return;
    L58:
        return;
    L27:
        if (r6 == NotificationCenter.proxyCheckDone) goto L29;
        return;
    L29:
        if (this.listView == null) goto L63;
        int r66 = this.proxyList.indexOf((SharedConfig.ProxyInfo) r8[0]);
        if (r66 < 0) goto L36;
        RecyclerListView.Holder r67 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(r66 + this.proxyStartRow);
        if (r67 == null) goto L36;
        ((TextDetailProxyCell) r67.itemView).updateStatus();
    L36:
        if (this.checkedProxies == true) goto L48;
        Iterator<SharedConfig.ProxyInfo> r68 = this.proxyList.iterator();
    L39:
        if (r68.hasNext() == false) goto L45;
        SharedConfig.ProxyInfo r72 = r68.next();
        if (r72.checking == true) goto L44;
        if (r72.availableCheckTime != 0) goto L39;
    L44:
        boolean r69 = true;
    L46:
        if (r69 == true) goto L49;
        this.checkedProxies = true;
    L49:
        if (r69 == true) goto L52;
        updateRows(true);
        return;
    L52:
        updateRefreshActionState(false);
        return;
    L45:
        r69 = false;
        goto L46
    L48:
        r69 = false;
        goto L49
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ProxyListActivity$$ExternalSyntheticLambda0 r7 = new ProxyListActivity$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r9 = new ArrayList();
        int r17 = Theme.key_windowBackgroundWhite;
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, TextCheckCell.class, HeaderCell.class, TextDetailProxyCell.class}, null, null, null, r17));
        r9.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r31 = Theme.key_windowBackgroundWhiteBlackText;
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r31));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailProxyCell.class}, new String[]{"textView"}, null, null, null, r31));
        r9.add(new ThemeDescription(this.listView, (ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG) | ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailProxyCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText6));
        int r40 = Theme.key_windowBackgroundWhiteGrayText2;
        r9.add(new ThemeDescription(this.listView, (ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG) | ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailProxyCell.class}, new String[]{"valueTextView"}, null, null, null, r40));
        r9.add(new ThemeDescription(this.listView, (ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG) | ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailProxyCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGreenText));
        r9.add(new ThemeDescription(this.listView, (ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG) | ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailProxyCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_text_RedRegular));
        int r49 = Theme.key_windowBackgroundWhiteGrayText3;
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailProxyCell.class}, new String[]{"checkImageView"}, null, null, null, r49));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailProxyCell.class}, new String[]{"reorderImageView"}, null, null, null, r40));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, r31));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, r49));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r31));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r40));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, r17));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, r31));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_windowBackgroundChecked));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_windowBackgroundUnchecked));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_windowBackgroundCheckText));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_switchTrackBlue));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_switchTrackBlueChecked));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_switchTrackBlueThumb));
        r9.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_switchTrackBlueThumbChecked));
        return r9;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.selectedItems.isEmpty() == true) goto L9;
        if (r2 == false) goto L10;
        this.listAdapter.clearSelected();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r1) {
        DownloadController.getInstance(this.currentAccount).checkAutodownloadSettings();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        ProxyController.getInstance().loadProxyList();
        this.currentConnectionState = ConnectionsManager.getInstance(this.currentAccount).getConnectionState();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxyChangedByRotation);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxySettingsChanged);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxyCheckDone);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didUpdateConnectionState);
        reloadSettingsFromPreferences();
        updateRows(true);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxyChangedByRotation);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxySettingsChanged);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxyCheckDone);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didUpdateConnectionState);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (this.listAdapter == null) goto L6;
        reloadSettingsFromPreferences();
        updateRows(true);
        return;
    }
}
