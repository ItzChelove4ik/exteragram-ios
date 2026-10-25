package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextColorCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatNotificationsPopupWrapper;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.NotificationsSettingsActivity;
import org.telegram.ui.ProfileNotificationsActivity;

/* loaded from: classes6.dex */
public class NotificationsCustomSettingsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ListAdapter adapter;

    @Keep
    public int addExceptionRow;
    private AnimatorSet animatorSet;
    private ArrayList<NotificationsSettingsActivity.NotificationException> autoExceptions;
    private int currentType;

    @Keep
    public int deleteExceptionsRow;
    private EmptyTextProgressView emptyView;
    private ArrayList<NotificationsSettingsActivity.NotificationException> exceptions;
    private HashMap<Long, NotificationsSettingsActivity.NotificationException> exceptionsDict;
    private int exceptionsEnd;
    private int exceptionsStart;
    public boolean expanded;

    @Keep
    public int importantRow;
    private final ArrayList<ItemInner> items;

    @Keep
    public int lightColorRow;
    private RecyclerListView listView;

    @Keep
    public int messagesRow;

    @Keep
    public int newRow;
    private final ArrayList<ItemInner> oldItems;
    private final int[] popupOptions;

    @Keep
    public int popupRow;

    @Keep
    public int previewRow;
    private final int[] priorityOptions;

    @Keep
    public int priorityRow;
    private SearchAdapter searchAdapter;
    private boolean searchWas;
    private boolean searching;
    private int settingsEnd;
    private int settingsStart;
    private boolean showAutoExceptions;

    @Keep
    public int showRow;

    @Keep
    public int showSenderRow;

    @Keep
    public int soundRow;
    private boolean storiesAuto;
    private Boolean storiesEnabled;

    @Keep
    public int storiesRow;
    int topicId;
    private final int[] vibrateLabels;

    @Keep
    public int vibrateRow;

    /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public AnonymousClass1(NotificationsCustomSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public AnonymousClass2(NotificationsCustomSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            NotificationsCustomSettingsActivity.m16275$$Nest$fgetsearchAdapter(this.this$0).searchDialogs(null);
            NotificationsCustomSettingsActivity.m16280$$Nest$fputsearching(this.this$0, false);
            NotificationsCustomSettingsActivity.m16279$$Nest$fputsearchWas(this.this$0, false);
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).setText(LocaleController.getString("NoExceptions", R.string.NoExceptions));
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).setAdapter(NotificationsCustomSettingsActivity.m16267$$Nest$fgetadapter(this.this$0));
            NotificationsCustomSettingsActivity.m16267$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).setFastScrollVisible(true);
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(false);
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).setShowAtCenter(false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            NotificationsCustomSettingsActivity.m16280$$Nest$fputsearching(this.this$0, true);
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).setShowAtCenter(true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r5) {
            if (NotificationsCustomSettingsActivity.m16275$$Nest$fgetsearchAdapter(this.this$0) != null) goto L5;
            return;
        L5:
            String r52 = r5.getText().toString();
            if (r52.length() == 0) goto L10;
            NotificationsCustomSettingsActivity.m16279$$Nest$fputsearchWas(this.this$0, true);
            if (NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0) == null) goto L10;
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).setText(LocaleController.getString("NoResult", R.string.NoResult));
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).showProgress();
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).setAdapter(NotificationsCustomSettingsActivity.m16275$$Nest$fgetsearchAdapter(this.this$0));
            NotificationsCustomSettingsActivity.m16275$$Nest$fgetsearchAdapter(this.this$0).notifyDataSetChanged();
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(true);
        L10:
            NotificationsCustomSettingsActivity.m16275$$Nest$fgetsearchAdapter(this.this$0).searchDialogs(r52);
        }
    }

    /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ChatNotificationsPopupWrapper.Callback {
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;
        final /* synthetic */ ArrayList val$arrayList;
        final /* synthetic */ boolean val$defaultEnabled;
        final /* synthetic */ long val$did;
        final /* synthetic */ NotificationsSettingsActivity.NotificationException val$exception;
        final /* synthetic */ boolean val$newException;
        final /* synthetic */ int val$position;

        /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$3$1, reason: invalid class name */
        public class AnonymousClass1 implements ProfileNotificationsActivity.ProfileNotificationsActivityDelegate {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.ProfileNotificationsActivity.ProfileNotificationsActivityDelegate
            public void didCreateNewException(NotificationsSettingsActivity.NotificationException r1) {
            }

            @Override // org.telegram.ui.ProfileNotificationsActivity.ProfileNotificationsActivityDelegate
            public void didRemoveException(long r1) {
                AnonymousClass3.m16282$$Nest$msetDefault(this.this$1);
            }
        }

        /* renamed from: -$$Nest$msetDefault, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16282$$Nest$msetDefault(AnonymousClass3 r0) {
            r0.setDefault();
        }

        public AnonymousClass3(NotificationsCustomSettingsActivity r1, long r2, boolean r4, NotificationsSettingsActivity.NotificationException r5, boolean r6, int r7, ArrayList r8) {
            this.this$0 = r1;
            this.val$did = r2;
            this.val$defaultEnabled = r4;
            this.val$exception = r5;
            this.val$newException = r6;
            this.val$position = r7;
            this.val$arrayList = r8;
        }

        private void setDefault() {
            if (this.val$newException == false) goto L6;
            return;
        L6:
            if (this.val$arrayList == NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0)) goto L10;
            int r0 = NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0).indexOf(this.val$exception);
            if (r0 < 0) goto L10;
            NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0).remove(r0);
            NotificationsCustomSettingsActivity.m16272$$Nest$fgetexceptionsDict(this.this$0).remove(Long.valueOf(this.val$exception.did));
        L10:
            this.val$arrayList.remove(this.val$exception);
            ArrayList r02 = this.val$arrayList;
            ArrayList r1 = NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0);
            NotificationsCustomSettingsActivity r2 = this.this$0;
            if (r02 != r1) goto L13;
            r2.updateRows(true);
            NotificationsCustomSettingsActivity.m16281$$Nest$mcheckRowsEnabled(this.this$0);
        L14:
            NotificationsCustomSettingsActivity.access$700(this.this$0).closeSearchField();
            return;
        L13:
            r2.updateRows(true);
            NotificationsCustomSettingsActivity.m16275$$Nest$fgetsearchAdapter(this.this$0).notifyItemChanged(this.val$position);
            goto L14
        }

        private void setNotDefault() {
            SharedPreferences r0 = NotificationsCustomSettingsActivity.access$500(this.this$0);
            this.val$exception.hasCustom = r0.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + this.val$exception.did, false);
            this.val$exception.notify = r0.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + this.val$exception.did, 0);
            if (this.val$exception.notify == 0) goto L7;
            int r02 = r0.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + this.val$exception.did, -1);
            if (r02 == (-1)) goto L7;
            this.val$exception.muteUntil = r02;
        L7:
            boolean r03 = this.val$newException;
            NotificationsCustomSettingsActivity r1 = this.this$0;
            if (r03 == false) goto L10;
            NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(r1).add(this.val$exception);
            NotificationsCustomSettingsActivity.m16272$$Nest$fgetexceptionsDict(this.this$0).put(Long.valueOf(this.val$exception.did), this.val$exception);
            this.this$0.updateRows(true);
        L11:
            NotificationsCustomSettingsActivity.access$600(this.this$0).closeSearchField();
            return;
        L10:
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(r1).getAdapter().notifyItemChanged(this.val$position);
            goto L11
        }

        private void update() {
            if (this.this$0.getMessagesController().isDialogMuted(this.val$did, this.this$0.topicId) == this.val$defaultEnabled) goto L6;
            setDefault();
            return;
        L6:
            setNotDefault();
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void muteFor(int r7) {
            NotificationsCustomSettingsActivity r0 = this.this$0;
            if (r7 == 0) goto L5;
            NotificationsCustomSettingsActivity.access$300(r0).muteUntil(this.val$did, this.this$0.topicId, r7);
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L13;
            NotificationsCustomSettingsActivity r72 = this.this$0;
            BulletinFactory.createMuteBulletin(r72, 5, r7, r72.getResourceProvider()).show();
        L13:
            update();
            return;
        L5:
            if (r0.getMessagesController().isDialogMuted(this.val$did, this.this$0.topicId) == false) goto L8;
            toggleMute();
        L8:
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L13;
            NotificationsCustomSettingsActivity r02 = this.this$0;
            BulletinFactory.createMuteBulletin(r02, 4, r7, r02.getResourceProvider()).show();
            goto L13
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void showCustomize() {
            if (this.val$did == 0) goto L6;
            Bundle r0 = new Bundle();
            r0.putLong("dialog_id", this.val$did);
            ProfileNotificationsActivity r1 = new ProfileNotificationsActivity(r0);
            r1.setDelegate(new AnonymousClass1(this));
            this.this$0.presentFragment(r1);
            return;
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void toggleMute() {
            NotificationsCustomSettingsActivity.access$400(this.this$0).muteDialog(this.val$did, this.this$0.topicId, !this.this$0.getMessagesController().isDialogMuted(this.val$did, this.this$0.topicId));
            NotificationsCustomSettingsActivity r0 = this.this$0;
            BulletinFactory.createMuteBulletin(r0, r0.getMessagesController().isDialogMuted(this.val$did, this.this$0.topicId), null).show();
            update();
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void toggleSound() {
            String r0 = NotificationsController.getSharedPrefKey(this.val$did, this.this$0.topicId);
            SharedPreferences r1 = MessagesController.getNotificationsSettings(NotificationsCustomSettingsActivity.access$200(this.this$0));
            boolean r2 = r1.getBoolean("sound_enabled_" + r0, true);
            boolean r4 = !r2;
            r1.edit().putBoolean("sound_enabled_" + r0, r4).apply();
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L6;
            NotificationsCustomSettingsActivity r6 = this.this$0;
            BulletinFactory.createSoundEnabledBulletin(r6, r2 ? 1 : 0, r6.getResourceProvider()).show();
            return;
        }
    }

    /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public AnonymousClass4(NotificationsCustomSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            NotificationsCustomSettingsActivity.m16274$$Nest$fgetlistView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public AnonymousClass5(NotificationsCustomSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.NotificationsCustomSettingsActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public AnonymousClass6(NotificationsCustomSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(NotificationsCustomSettingsActivity.m16268$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            NotificationsCustomSettingsActivity.m16278$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }
    }

    public class ExpandView extends TextCell {
        public ImageView imageView;
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public ExpandView(NotificationsCustomSettingsActivity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            ImageView r0 = new ImageView(r9);
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.setColorFilter(new PorterDuffColorFilter(r8.getThemedColor(Theme.key_windowBackgroundWhiteBlueIcon), PorterDuff.Mode.SRC_IN));
            this.imageView.setImageResource(R.drawable.msg_expand);
            ImageView r82 = this.imageView;
            if (LocaleController.isRTL == false) goto L5;
            int r92 = 3;
        L6:
            addView(r82, LayoutHelper.createFrame(24, 24.0f, r92 | 16, 17.0f, 0.0f, 17.0f, 0.0f));
            return;
        L5:
            r92 = 5;
            goto L6
        }

        @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r42 = r4 - r2;
            if (LocaleController.isRTL == false) goto L5;
            int r12 = AndroidUtilities.dp(17.0f);
        L6:
            int r52 = ((r5 - r3) - AndroidUtilities.dp(24.0f)) / 2;
            this.imageView.layout(r12, r52, AndroidUtilities.dp(24.0f) + r12, AndroidUtilities.dp(24.0f) + r52);
            return;
        L5:
            r12 = r42 - AndroidUtilities.dp(41.0f);
            goto L6
        }

        @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            this.imageView.measure(r1, r2);
        }

        public void set(CharSequence r2, boolean r3, boolean r4) {
            setArrow(r3, true);
            setText(r2, r4);
        }

        public void setArrow(boolean r3, boolean r4) {
            ImageView r2 = this.imageView;
            float r0 = 180.0f;
            if (r4 == false) goto L9;
            ViewPropertyAnimator r22 = r2.animate();
            if (r3 == false) goto L7;
            r0 = 0.0f;
        L7:
            r22.rotation(r0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(340).start();
            return;
        L9:
            if (r3 == false) goto L11;
            r0 = 0.0f;
        L11:
            r2.setRotation(r0);
        }
    }

    public static class ItemInner extends AdapterWithDiffUtils.Item {
        public boolean checked;
        public int color;
        public NotificationsSettingsActivity.NotificationException exception;
        public int id;
        public int resId;
        public CharSequence text;
        public CharSequence text2;

        private ItemInner(int r2) {
            super(r2, true);
        }

        public static ItemInner asButton(int r2, int r3, CharSequence r4) {
            ItemInner r0 = new ItemInner(7);
            r0.id = r2;
            r0.resId = r3;
            r0.text = r4;
            return r0;
        }

        public static ItemInner asCheck(int r2, CharSequence r3, boolean r4) {
            ItemInner r0 = new ItemInner(1);
            r0.id = r2;
            r0.text = r3;
            r0.checked = r4;
            return r0;
        }

        public static ItemInner asCheck2(int r2, int r3, CharSequence r4, CharSequence r5, boolean r6) {
            ItemInner r0 = new ItemInner(6);
            r0.id = r2;
            r0.resId = r3;
            r0.text = r4;
            r0.text2 = r5;
            r0.checked = r6;
            return r0;
        }

        public static ItemInner asColor(CharSequence r2, int r3) {
            ItemInner r0 = new ItemInner(3);
            r0.text = r2;
            r0.color = r3;
            return r0;
        }

        public static ItemInner asException(NotificationsSettingsActivity.NotificationException r2) {
            ItemInner r0 = new ItemInner(2);
            r0.exception = r2;
            return r0;
        }

        public static ItemInner asExpand(CharSequence r2, boolean r3) {
            ItemInner r0 = new ItemInner(8);
            r0.text = r2;
            r0.resId = r3 ? 1 : 0;
            return r0;
        }

        public static ItemInner asHeader(CharSequence r2) {
            ItemInner r0 = new ItemInner(0);
            r0.text = r2;
            return r0;
        }

        public static ItemInner asSetting(int r2, CharSequence r3, CharSequence r4) {
            ItemInner r0 = new ItemInner(5);
            r0.id = r2;
            r0.text = r3;
            r0.text2 = r4;
            return r0;
        }

        public static ItemInner asShadow(int r2, CharSequence r3) {
            ItemInner r0 = new ItemInner(4);
            r0.id = r2;
            r0.text = r3;
            return r0;
        }

        @Override // org.telegram.ui.Components.ListView.AdapterWithDiffUtils.Item
        public boolean contentsEquals(AdapterWithDiffUtils.Item r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L25:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L25;
            ItemInner r52 = (ItemInner) r5;
            if (this.id != r52.id) goto L25;
            if (this.resId != r52.resId) goto L25;
            if (this.color != r52.color) goto L25;
            if (this.checked != r52.checked) goto L25;
            if (Objects.equals(this.text, r52.text) == false) goto L25;
            if (Objects.equals(this.text2, r52.text2) == false) goto L25;
            if (this.exception != r52.exception) goto L25;
            return true;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L27:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L27;
            ItemInner r52 = (ItemInner) r5;
            if (this.id != r52.id) goto L27;
            if (this.color != r52.color) goto L27;
            if (this.viewType == 8) goto L25;
            if (this.resId != r52.resId) goto L27;
            if (Objects.equals(this.text, r52.text) == false) goto L27;
            if (this.viewType == 6) goto L25;
            if (Objects.equals(this.text2, r52.text2) == false) goto L27;
        L25:
            if (this.exception != r52.exception) goto L27;
            return true;
        }
    }

    public class ListAdapter extends AdapterWithDiffUtils {
        private Context mContext;
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public ListAdapter(NotificationsCustomSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 >= 0) goto L4;
            return 5;
        L4:
            if (r2 < NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).size()) goto L7;
            return 5;
        L7:
            return ((ItemInner) NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).get(r2)).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 0) goto L5;
            return false;
        L5:
            if (r0 == 4) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r14, int r15) {
            if (r15 >= 0) goto L4;
            return;
        L4:
            if (r15 >= NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).size()) goto L46;
            ItemInner r0 = (ItemInner) NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).get(r15);
            boolean r1 = true;
            int r152 = r15 + 1;
            if (r152 < NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).size()) goto L9;
        L11:
            boolean r11 = false;
        L13:
            switch(r14.getItemViewType()) {
                case 0: goto L43;
                case 1: goto L41;
                case 2: goto L39;
                case 3: goto L37;
                case 4: goto L31;
                case 5: goto L29;
                case 6: goto L27;
                case 7: goto L21;
                case 8: goto L15;
                default: goto L47;
            };
        L15:
            ExpandView r13 = (ExpandView) r14.itemView;
            r13.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            CharSequence r142 = r0.text;
            if (r0.resId == 1) goto L19;
            r1 = false;
        L19:
            r13.set(r142, r1, r11);
            return;
        L21:
            TextCell r132 = (TextCell) r14.itemView;
            if (r0.resId != 0) goto L25;
            r132.setColors(-1, Theme.key_text_RedRegular);
            r132.setText(_UrlKt.FRAGMENT_ENCODE_SET + r0.text, r11);
            return;
        L25:
            r132.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r132.setTextAndIcon(_UrlKt.FRAGMENT_ENCODE_SET + r0.text, r0.resId, r11);
            return;
        L27:
            NotificationsCheckCell r4 = (NotificationsCheckCell) r14.itemView;
            r4.setDrawLine(true);
            r4.setChecked(r0.checked);
            r4.setTextAndValueAndIconAndCheck(r0.text, r0.text2, r0.resId, r0.checked, 0, false, r11, true);
            return;
        L29:
            ((TextSettingsCell) r14.itemView).setTextAndValue(r0.text, r0.text2, r11);
            return;
        L31:
            TextInfoPrivacyCell r133 = (TextInfoPrivacyCell) r14.itemView;
            if (r0.text != null) goto L35;
            r133.setFixedSize(12);
            r133.setText(null);
            return;
        L35:
            r133.setFixedSize(0);
            r133.setText(r0.text);
            return;
        L37:
            ((TextColorCell) r14.itemView).setTextAndColor(_UrlKt.FRAGMENT_ENCODE_SET + r0.text, r0.color, r11);
            return;
        L39:
            ((UserCell) r14.itemView).setException(r0.exception, null, r11);
            return;
        L41:
            ((TextCheckCell) r14.itemView).setTextAndCheck(_UrlKt.FRAGMENT_ENCODE_SET + r0.text, r0.checked, r11);
            return;
        L43:
            ((HeaderCell) r14.itemView).setText(r0.text);
            return;
        L47:
            return;
        L9:
            if (((ItemInner) NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).get(r152)).viewType == 4) goto L11;
            r11 = true;
            goto L13
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            switch(r8) {
                case 0: goto L11;
                case 1: goto L10;
                case 2: goto L9;
                case 3: goto L8;
                case 4: goto L7;
                case 5: goto L6;
                case 6: goto L5;
                case 7: goto L3;
                case 8: goto L4;
                default: goto L3;
            };
        L3:
            View r72 = new TextCell(this.mContext);
        L13:
            return new RecyclerListView.Holder(r72);
        L4:
            r72 = new ExpandView(this.this$0, this.mContext);
            goto L13
        L5:
            r72 = new NotificationsCheckCell(this.mContext, 21, 64, true, NotificationsCustomSettingsActivity.access$000(this.this$0));
            goto L13
        L6:
            r72 = new TextSettingsCell(this.mContext);
            goto L13
        L7:
            r72 = new TextInfoPrivacyCell(this.mContext);
            goto L13
        L8:
            r72 = new TextColorCell(this.mContext);
            goto L13
        L9:
            r72 = new UserCell(this.mContext, 6, 0, false);
            goto L13
        L10:
            r72 = new TextCheckCell(this.mContext);
            goto L13
        L11:
            r72 = new HeaderCell(this.mContext);
            goto L13
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r7) {
            if (NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0) != 3) goto L5;
        L9:
            int r0 = NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0);
            NotificationsCustomSettingsActivity r2 = this.this$0;
            if (r0 == 3) goto L12;
            boolean r02 = NotificationsCustomSettingsActivity.access$100(r2).isGlobalNotificationsEnabled(NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0));
        L23:
            int r22 = r7.getAdapterPosition();
            if (r22 >= 0) goto L26;
        L28:
            ItemInner r6 = null;
        L29:
            if (r6 != null) goto L31;
        L33:
            int r62 = r7.getItemViewType();
            if (r62 == 0) goto L46;
            if (r62 == 1) goto L44;
            if (r62 != 3) goto L38;
            ((TextColorCell) r7.itemView).setEnabled(r02, null);
            return;
        L38:
            if (r62 != 5) goto L48;
            ((TextSettingsCell) r7.itemView).setEnabled(r02, null);
            return;
        L48:
            return;
        L44:
            ((TextCheckCell) r7.itemView).setEnabled(r02, null);
            return;
        L46:
            ((HeaderCell) r7.itemView).setEnabled(r02, null);
            return;
        L31:
            if (r6.id != 102) goto L33;
            return;
        L26:
            if (r22 >= NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).size()) goto L28;
            r6 = (ItemInner) NotificationsCustomSettingsActivity.m16273$$Nest$fgetitems(this.this$0).get(r22);
            goto L29
        L12:
            if (NotificationsCustomSettingsActivity.m16277$$Nest$fgetstoriesEnabled(r2) != null) goto L14;
        L21:
            r02 = true;
            goto L23
        L14:
            if (NotificationsCustomSettingsActivity.m16277$$Nest$fgetstoriesEnabled(this.this$0).booleanValue() == true) goto L21;
            if (NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0) != null) goto L18;
        L20:
            r02 = false;
            goto L23
        L18:
            if (NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0).isEmpty() == true) goto L20;
        L5:
            if (NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0) != null) goto L7;
            return;
        L7:
            if (NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0).isEmpty() == true) goto L9;
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        private SearchAdapterHelper searchAdapterHelper;
        private ArrayList<NotificationsSettingsActivity.NotificationException> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        final /* synthetic */ NotificationsCustomSettingsActivity this$0;

        public static /* synthetic */ void $r8$lambda$cw1rJchRrO22RUm1b42HnnsHAx8(SearchAdapter r0, String r1) {
            r0.lambda$processSearch$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$ncxVCBNkSwrXTBuDZYs7vg2OUfs(SearchAdapter r0, String r1, ArrayList r2) {
            r0.lambda$processSearch$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$qsGMgPWomyS0oYND_e8sbBqbAZw(SearchAdapter r0, int r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$rus4MTYKCZOFJtYixV_CXK7Ek-8, reason: not valid java name */
        public static /* synthetic */ void m16283$r8$lambda$rus4MTYKCZOFJtYixV_CXK7Ek8(SearchAdapter r0, ArrayList r1, ArrayList r2, ArrayList r3) {
            r0.lambda$updateSearchResults$4(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$vzogBu2A-8mv5iUq74iDA9GCyYU, reason: not valid java name */
        public static /* synthetic */ void m16284$r8$lambda$vzogBu2A8mv5iUq74iDA9GCyYU(SearchAdapter r0, String r1) {
            r0.lambda$searchDialogs$1(r1);
        }

        /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m16285$$Nest$fgetsearchResult(SearchAdapter r0) {
            return r0.searchResult;
        }

        public SearchAdapter(NotificationsCustomSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            this.mContext = r2;
            SearchAdapterHelper r12 = new SearchAdapterHelper(true);
            this.searchAdapterHelper = r12;
            r12.setDelegate(new NotificationsCustomSettingsActivity$SearchAdapter$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$new$0(int r1) {
            if (this.searchRunnable == null) goto L5;
        L7:
            notifyDataSetChanged();
            return;
        L5:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L7;
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).showTextView();
            goto L7
        }

        private /* synthetic */ void lambda$processSearch$2(String r22, ArrayList r23) {
            String r1 = r22.trim().toLowerCase();
            if (r1.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), new ArrayList(), new ArrayList());
            return;
        L6:
            String r2 = LocaleController.getInstance().getTranslitString(r1);
            if (r1.equals(r2) == false) goto L9;
        L10:
            r2 = null;
        L11:
            int r3 = 1;
            int r5 = 0;
            if (r2 == null) goto L14;
            int r6 = 1;
        L15:
            int r62 = r6 + 1;
            String[] r7 = new String[r62];
            r7[0] = r1;
            if (r2 == null) goto L18;
            r7[1] = r2;
        L18:
            ArrayList<Object> r12 = new ArrayList();
            ArrayList<NotificationsSettingsActivity.NotificationException> r24 = new ArrayList();
            ArrayList<CharSequence> r8 = new ArrayList();
            String[] r10 = new String[2];
            int r11 = 0;
        L20:
            if (r11 >= r23.size()) goto L86;
            NotificationsSettingsActivity.NotificationException r13 = (NotificationsSettingsActivity.NotificationException) r23.get(r11);
            if (DialogObject.isEncryptedDialog(r13.did) == false) goto L29;
            String[] r15 = r10;
            TLRPC.EncryptedChat r9 = this.this$0.getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r13.did)));
            if (r9 == null) goto L28;
            int r16 = r5;
            TLRPC.User r4 = this.this$0.getMessagesController().getUser(Long.valueOf(r9.user_id));
            if (r4 == null) goto L47;
            r15[r16] = ContactsController.formatName(r4.first_name, r4.last_name);
            r15[r3] = UserObject.getPublicUsername(r4);
        L47:
            TLRPC.Chat r42 = null;
        L48:
            String r52 = r15[r16];
            r15[r16] = r52.toLowerCase();
            String r92 = LocaleController.getInstance().getTranslitString(r15[r16]);
            String r102 = r15[r16];
            if (r102 != null) goto L51;
        L53:
            int r103 = r16;
            int r17 = r103;
        L54:
            if (r103 >= r62) goto L35;
            String r14 = r7[r103];
            int r18 = r3;
            String r32 = r15[r16];
            int r19 = r62;
            if (r32 != null) goto L58;
            String[] r20 = r7;
        L64:
            if (r92 != null) goto L66;
        L70:
            String r33 = r15[r18];
            if (r33 != null) goto L73;
        L75:
            int r34 = r17;
        L76:
            if (r34 != 0) goto L77;
            r103 = r103 + 1;
            r17 = r34;
            r3 = r18;
            r62 = r19;
            r7 = r20;
            goto L54
        L77:
            int r63 = r18;
            if (r34 != r63) goto L80;
            r8.add(AndroidUtilities.generateSearchName(r52, null, r14));
        L81:
            r24.add(r13);
            if (r42 == null) goto L85;
            r12.add(r42);
        L85:
            r11 = r11 + 1;
            r3 = r63;
            r10 = r15;
            r5 = r16;
            r62 = r19;
            r7 = r20;
            goto L20
        L80:
            r8.add(AndroidUtilities.generateSearchName("@" + r15[r63], null, "@" + r14));
            goto L81
        L73:
            if (r33.startsWith(r14) == false) goto L75;
            r34 = 2;
            goto L76
        L66:
            if (r92.startsWith(r14) == false) goto L68;
        L69:
            r34 = r18;
            goto L76
        L68:
            if (r92.contains(" " + r14) == false) goto L70;
        L58:
            if (r32.startsWith(r14) == true) goto L62;
            r20 = r7;
            if (r15[r16].contains(" " + r14) == true) goto L69;
        L62:
            r20 = r7;
        L35:
            r19 = r62;
            r20 = r7;
            r63 = r3;
            goto L85
        L51:
            if (r102.equals(r92) == false) goto L53;
            r92 = null;
            goto L53
        L28:
            r16 = r5;
            goto L47
        L29:
            r16 = r5;
            r15 = r10;
            boolean r43 = DialogObject.isUserDialog(r13.did);
            NotificationsCustomSettingsActivity r53 = this.this$0;
            if (r43 == false) goto L37;
            TLRPC.User r44 = r53.getMessagesController().getUser(Long.valueOf(r13.did));
            if (r44 == null) goto L35;
            if (r44.deleted == true) goto L35;
            r15[r16] = ContactsController.formatName(r44.first_name, r44.last_name);
            r15[r3] = UserObject.getPublicUsername(r44);
            r42 = r44;
            goto L48
        L37:
            TLRPC.Chat r45 = r53.getMessagesController().getChat(Long.valueOf(-r13.did));
            if (r45 == null) goto L47;
            if (r45.left == true) goto L35;
            if (r45.kicked == true) goto L35;
            if (r45.migrated_to != null) goto L35;
            r15[r16] = r45.title;
            r15[r3] = ChatObject.getPublicUsername(r45);
            r42 = r45;
            goto L48
        L86:
            updateSearchResults(r12, r24, r8);
            return;
        L14:
            r6 = 0;
            goto L15
        L9:
            if (r2.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$3(String r13) {
            SearchAdapterHelper r0 = this.searchAdapterHelper;
            boolean r2 = true;
            if (NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0) != 1) goto L5;
        L7:
            r2 = false;
        L8:
            r0.queryServerSearch(r13, true, r2, true, false, false, 0, false, 0, 0);
            ArrayList r132 = new ArrayList(NotificationsCustomSettingsActivity.m16271$$Nest$fgetexceptions(this.this$0));
            Utilities.searchQueue.postRunnable(new NotificationsCustomSettingsActivity$SearchAdapter$$ExternalSyntheticLambda3(this, r13, r132));
            return;
        L5:
            if (NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0) == 3) goto L7;
            goto L7
        }

        private /* synthetic */ void lambda$searchDialogs$1(String r1) {
            processSearch(r1);
        }

        private /* synthetic */ void lambda$updateSearchResults$4(ArrayList r2, ArrayList r3, ArrayList r4) {
            if (NotificationsCustomSettingsActivity.m16276$$Nest$fgetsearching(this.this$0) == true) goto L5;
            return;
        L5:
            this.searchRunnable = null;
            this.searchResult = r2;
            this.searchResultNames = r3;
            this.searchAdapterHelper.mergeResults(r4);
            if (NotificationsCustomSettingsActivity.m16276$$Nest$fgetsearching(this.this$0) == true) goto L8;
        L10:
            notifyDataSetChanged();
            return;
        L8:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L10;
            NotificationsCustomSettingsActivity.m16270$$Nest$fgetemptyView(this.this$0).showTextView();
            goto L10
        }

        private void processSearch(String r2) {
            AndroidUtilities.runOnUIThread(new NotificationsCustomSettingsActivity$SearchAdapter$$ExternalSyntheticLambda2(this, r2));
        }

        private void updateSearchResults(ArrayList<Object> r2, ArrayList<NotificationsSettingsActivity.NotificationException> r3, ArrayList<CharSequence> r4) {
            AndroidUtilities.runOnUIThread(new NotificationsCustomSettingsActivity$SearchAdapter$$ExternalSyntheticLambda4(this, r3, r4, r2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.searchResult.size();
            ArrayList<TLObject> r2 = this.searchAdapterHelper.getGlobalSearch();
            if (r2.isEmpty() == false) goto L5;
            return r0;
        L5:
            return r0 + (r2.size() + 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != this.searchResult.size()) goto L6;
            return 1;
        L6:
            return 0;
        }

        public Object getObject(int r2) {
            if (r2 >= 0) goto L4;
        L7:
            int r22 = r2 - (this.searchResult.size() + 1);
            ArrayList<TLObject> r0 = this.searchAdapterHelper.getGlobalSearch();
            if (r22 >= 0) goto L10;
            return null;
        L10:
            if (r22 < r0.size()) goto L12;
            return null;
        L12:
            return this.searchAdapterHelper.getGlobalSearch().get(r22);
        L4:
            if (r2 >= this.searchResult.size()) goto L7;
            return this.searchResult.get(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            boolean r1 = true;
            if (r0 == 0) goto L8;
            if (r0 == 1) goto L6;
            return;
        L6:
            ((GraySectionCell) r9.itemView).setText(LocaleController.getString("AddToExceptions", R.string.AddToExceptions));
            return;
        L8:
            UserCell r2 = (UserCell) r9.itemView;
            int r92 = this.searchResult.size();
            ArrayList<NotificationsSettingsActivity.NotificationException> r02 = this.searchResult;
            if (r10 >= r92) goto L16;
            NotificationsSettingsActivity.NotificationException r93 = r02.get(r10);
            CharSequence r03 = this.searchResultNames.get(r10);
            if (r10 != (this.searchResult.size() - 1)) goto L14;
            r1 = false;
        L14:
            r2.setException(r93, r03, r1);
            r2.setAddButtonVisible(false);
            return;
        L16:
            int r102 = r10 - (r02.size() + 1);
            ArrayList<TLObject> r8 = this.searchAdapterHelper.getGlobalSearch();
            TLObject r94 = r8.get(r102);
            String r5 = LocaleController.getString("NotificationsOn", R.string.NotificationsOn);
            if (r102 == (r8.size() - 1)) goto L19;
            boolean r7 = true;
        L20:
            r2.setData(r94, null, r5, 0, r7);
            r2.setAddButtonVisible(true);
            return;
        L19:
            r7 = false;
            goto L20
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 == 0) goto L4;
            View r72 = new GraySectionCell(this.mContext);
            r72.setBackgroundColor(0);
            r72.setTag(-33024);
        L6:
            return new RecyclerListView.Holder(r72);
        L4:
            r72 = new UserCell(this.mContext, 4, 0, false, true);
            goto L6
        }

        public void searchDialogs(String r15) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            if (r15 != null) goto L14;
            this.searchResult.clear();
            this.searchResultNames.clear();
            this.searchAdapterHelper.mergeResults(null);
            SearchAdapterHelper r2 = this.searchAdapterHelper;
            boolean r0 = true;
            if (NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0) != 1) goto L9;
        L11:
            r0 = false;
        L12:
            r2.queryServerSearch(null, true, r0, true, false, false, 0, false, 0, 0);
            notifyDataSetChanged();
            return;
        L9:
            if (NotificationsCustomSettingsActivity.m16269$$Nest$fgetcurrentType(this.this$0) == 3) goto L11;
        L14:
            DispatchQueue r02 = Utilities.searchQueue;
            NotificationsCustomSettingsActivity$SearchAdapter$$ExternalSyntheticLambda1 r1 = new NotificationsCustomSettingsActivity$SearchAdapter$$ExternalSyntheticLambda1(this, r15);
            this.searchRunnable = r1;
            r02.postRunnable(r1, 300);
        }
    }

    /* renamed from: $r8$lambda$-AKGfKixv61yz0lFApFzSTQtvzA, reason: not valid java name */
    public static /* synthetic */ void m16259$r8$lambda$AKGfKixv61yz0lFApFzSTQtvzA(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1, View r2, int r3) {
        r0.lambda$createView$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$0f0fz_yjuDck1xsUgb6NPw0GPE4(NotificationsCustomSettingsActivity r0) {
        r0.lambda$getThemeDescriptions$21();
    }

    public static /* synthetic */ void $r8$lambda$1GiCbYGK8RMkNGzXGQFWYNVT2NM(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1, View r2, int r3) {
        r0.lambda$createView$6(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$5mXetc-kLuEpRSZ3ts0AYsXq9cM, reason: not valid java name */
    public static /* synthetic */ void m16260$r8$lambda$5mXetckLuEpRSZ3ts0AYsXq9cM(NotificationsCustomSettingsActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7, ArrayList r8) {
        r0.lambda$loadExceptions$19(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$D4qKvV9dohXEPXvX_omt9atYvPs(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$DmFD9EXJomPUDEb56QkMQolvlpU(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1, View r2, int r3) {
        r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Erodymr_KVVb2h4gNRSVUcqmSnU(boolean[] r4, int r5, RadioColorCell[] r6, View r7) {
        if (r5 != 1) goto L5;
        boolean r52 = true;
    L6:
        r4[0] = r52;
        int r53 = 0;
    L8:
        if (r53 >= r6.length) goto L17;
        RadioColorCell r1 = r6[r53];
        boolean r2 = r4[0];
        if (r53 != 1) goto L12;
        boolean r3 = true;
    L13:
        if (r2 != r3) goto L15;
        boolean r22 = true;
    L16:
        r1.setChecked(r22, true);
        r53 = r53 + 1;
        goto L8
    L15:
        r22 = false;
        goto L16
    L12:
        r3 = false;
        goto L13
    L17:
        return;
    L5:
        r52 = false;
        goto L6
    }

    /* renamed from: $r8$lambda$F9kAovdFPzNsxEIFPCL5n-m844g, reason: not valid java name */
    public static /* synthetic */ void m16261$r8$lambda$F9kAovdFPzNsxEIFPCL5nm844g(NotificationsCustomSettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$9(r1, r2);
    }

    /* renamed from: $r8$lambda$FZvZKyREfGk77H-AtIBA51UbzGg, reason: not valid java name */
    public static /* synthetic */ boolean m16262$r8$lambda$FZvZKyREfGk77HAtIBA51UbzGg(NotificationsCustomSettingsActivity r0, DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        return r0.lambda$createView$8(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$ITJfyK9M6X_Ku3XUyiKZmcouc8o(NotificationsCustomSettingsActivity r0, ArrayList r1) {
        r0.lambda$loadExceptions$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$LX3cz3BAttQ6xXM8DIRxNVPl7uA(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1, View r2, int r3) {
        r0.lambda$createView$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$OnTv3yGdGImaer175BYuSn80KE8(NotificationsCustomSettingsActivity r0, SharedPreferences r1, String r2, boolean[] r3, AlertDialog r4, int r5) {
        r0.lambda$createView$16(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QHEHPpXFh2aVNBFM7fXy1tv35Nw(NotificationsCustomSettingsActivity r0, View r1, int r2) {
        r0.lambda$createView$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RjrhdBxwSeXdWzQNhlIPnLbsbHA(NotificationsCustomSettingsActivity r0, int r1) {
        r0.lambda$createView$10(r1);
    }

    /* renamed from: $r8$lambda$YgkoPHLZYR4-7SyzYkxt-uWFviU, reason: not valid java name */
    public static /* synthetic */ void m16263$r8$lambda$YgkoPHLZYR47SyzYkxtuWFviU(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1, View r2, boolean r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$aaUre67nFsMeOkaAmnQE1FJj_As(NotificationsCustomSettingsActivity r0, View r1, int r2) {
        r0.lambda$createView$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bxvRTUffRU5A3jxNoHGSR4Ko4xI(NotificationsCustomSettingsActivity r0, View r1, String r2, int r3) {
        r0.lambda$createView$13(r1, r2, r3);
    }

    public static /* synthetic */ double $r8$lambda$cHDCbU2FZFgRI3V3wg3P89qOc48(TLRPC.TL_topPeer r2) {
        return r2.rating;
    }

    /* renamed from: $r8$lambda$fQ9esns0m5ej-s4alLGRVSIfaVI, reason: not valid java name */
    public static /* synthetic */ void m16264$r8$lambda$fQ9esns0m5ejs4alLGRVSIfaVI(NotificationsCustomSettingsActivity r0, Context r1, View r2, int r3, float r4, float r5) {
        r0.lambda$createView$17(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hYU1MAyqe6nMHO7BREOOc2M56X4(NotificationsCustomSettingsActivity r0, View r1, int r2) {
        r0.lambda$createView$11(r1, r2);
    }

    /* renamed from: $r8$lambda$lqCl7Pf6MXH8-wSdM2sLmzj33H8, reason: not valid java name */
    public static /* synthetic */ void m16265$r8$lambda$lqCl7Pf6MXH8wSdM2sLmzj33H8(NotificationsCustomSettingsActivity r0, NotificationsSettingsActivity.NotificationException r1, View r2, boolean r3) {
        r0.lambda$createView$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$yO_0i0ppMNIM-GfOyePHgEZHIpU, reason: not valid java name */
    public static /* synthetic */ double m16266$r8$lambda$yO_0i0ppMNIMGfOyePHgEZHIpU(TLRPC.TL_topPeer r2) {
        return r2.rating;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m16267$$Nest$fgetadapter(NotificationsCustomSettingsActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m16268$$Nest$fgetanimatorSet(NotificationsCustomSettingsActivity r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16269$$Nest$fgetcurrentType(NotificationsCustomSettingsActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m16270$$Nest$fgetemptyView(NotificationsCustomSettingsActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetexceptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16271$$Nest$fgetexceptions(NotificationsCustomSettingsActivity r0) {
        return r0.exceptions;
    }

    /* renamed from: -$$Nest$fgetexceptionsDict, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16272$$Nest$fgetexceptionsDict(NotificationsCustomSettingsActivity r0) {
        return r0.exceptionsDict;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16273$$Nest$fgetitems(NotificationsCustomSettingsActivity r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m16274$$Nest$fgetlistView(NotificationsCustomSettingsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m16275$$Nest$fgetsearchAdapter(NotificationsCustomSettingsActivity r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16276$$Nest$fgetsearching(NotificationsCustomSettingsActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetstoriesEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Boolean m16277$$Nest$fgetstoriesEnabled(NotificationsCustomSettingsActivity r0) {
        return r0.storiesEnabled;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16278$$Nest$fputanimatorSet(NotificationsCustomSettingsActivity r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16279$$Nest$fputsearchWas(NotificationsCustomSettingsActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16280$$Nest$fputsearching(NotificationsCustomSettingsActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$mcheckRowsEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16281$$Nest$mcheckRowsEnabled(NotificationsCustomSettingsActivity r0) {
        r0.checkRowsEnabled();
    }

    public NotificationsCustomSettingsActivity(int r2, ArrayList<NotificationsSettingsActivity.NotificationException> r3, ArrayList<NotificationsSettingsActivity.NotificationException> r4) {
        this(r2, r3, r4, false);
    }

    public NotificationsCustomSettingsActivity(int r9, ArrayList<NotificationsSettingsActivity.NotificationException> r10, ArrayList<NotificationsSettingsActivity.NotificationException> r11, boolean r12) {
        this.showAutoExceptions = true;
        this.exceptionsDict = new HashMap();
        int r0 = 0;
        this.topicId = 0;
        this.vibrateLabels = new int[]{R.string.VibrationDefault, R.string.Short, R.string.VibrationDisabled, R.string.Long, R.string.OnlyIfSilent};
        this.popupOptions = new int[]{R.string.NoPopup, R.string.OnlyWhenScreenOn, R.string.OnlyWhenScreenOff, R.string.AlwaysShowPopup};
        int r2 = R.string.NotificationsPriorityHigh;
        int r3 = R.string.NotificationsPriorityUrgent;
        int r5 = R.string.NotificationsPriorityMedium;
        this.priorityOptions = new int[]{r2, r3, r3, r5, R.string.NotificationsPriorityLow, r5};
        this.newRow = -1;
        this.showRow = -1;
        this.importantRow = -1;
        this.messagesRow = -1;
        this.storiesRow = -1;
        this.previewRow = -1;
        this.showSenderRow = -1;
        this.soundRow = -1;
        this.addExceptionRow = -1;
        this.deleteExceptionsRow = -1;
        this.lightColorRow = -1;
        this.vibrateRow = -1;
        this.popupRow = -1;
        this.priorityRow = -1;
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
        this.currentType = r9;
        this.autoExceptions = r11;
        this.exceptions = r10;
        if (r10 == null) goto L7;
        int r92 = r10.size();
        int r102 = 0;
    L5:
        if (r102 >= r92) goto L7;
        NotificationsSettingsActivity.NotificationException r112 = this.exceptions.get(r102);
        this.exceptionsDict.put(Long.valueOf(r112.did), r112);
        r102 = r102 + 1;
    L7:
        ArrayList<NotificationsSettingsActivity.NotificationException> r93 = this.autoExceptions;
        if (r93 == null) goto L12;
        int r94 = r93.size();
    L10:
        if (r0 >= r94) goto L12;
        NotificationsSettingsActivity.NotificationException r103 = this.autoExceptions.get(r0);
        this.exceptionsDict.put(Long.valueOf(r103.did), r103);
        r0 = r0 + 1;
    L12:
        if (r12 == false) goto L17;
        loadExceptions();
        return;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(NotificationsCustomSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ NotificationsController access$100(NotificationsCustomSettingsActivity r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ int access$200(NotificationsCustomSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ NotificationsController access$300(NotificationsCustomSettingsActivity r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ NotificationsController access$400(NotificationsCustomSettingsActivity r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ SharedPreferences access$500(NotificationsCustomSettingsActivity r0) {
        return r0.getNotificationsSettings();
    }

    public static /* synthetic */ ActionBar access$600(NotificationsCustomSettingsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$700(NotificationsCustomSettingsActivity r0) {
        return r0.actionBar;
    }

    public static boolean areStoriesNotMuted(int r4, long r5) {
        SharedPreferences r0 = MessagesController.getNotificationsSettings(r4);
        if (r0.contains(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r5) == false) goto L7;
        return r0.getBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r5, true);
    L7:
        if (r0.contains("EnableAllStories") == false) goto L11;
        return r0.getBoolean("EnableAllStories", true);
    L11:
        return isTop5Peer(r4, r5);
    }

    private void checkRowsEnabled() {
        if (this.exceptions.isEmpty() == false) goto L5;
    L7:
        int r0 = this.listView.getChildCount();
        ArrayList<Animator> r2 = new ArrayList();
        int r4 = 0;
        if (this.currentType != 3) goto L20;
        Boolean r3 = this.storiesEnabled;
        if (r3 != null) goto L12;
    L19:
        boolean r32 = true;
    L21:
        if (r4 >= r0) goto L50;
        View r6 = this.listView.getChildAt(r4);
        RecyclerListView.Holder r7 = (RecyclerListView.Holder) this.listView.getChildViewHolder(r6);
        int r62 = this.listView.getChildAdapterPosition(r6);
        if (r62 >= 0) goto L25;
    L27:
        ItemInner r63 = null;
    L28:
        if (r63 == null) goto L36;
        int r64 = r63.id;
        if (r64 != 102) goto L32;
    L35:
        boolean r65 = true;
    L37:
        int r8 = r7.getItemViewType();
        if (r8 == 0) goto L47;
        if (r8 == 1) goto L46;
        if (r8 != 3) goto L42;
        ((TextColorCell) r7.itemView).setEnabled(r65, r2);
    L48:
        r4 = r4 + 1;
        goto L21
    L42:
        if (r8 != 5) goto L48;
        ((TextSettingsCell) r7.itemView).setEnabled(r65, r2);
        goto L48
    L46:
        ((TextCheckCell) r7.itemView).setEnabled(r65, r2);
        goto L48
    L47:
        ((HeaderCell) r7.itemView).setEnabled(r65, r2);
        goto L48
    L32:
        if (r64 == 101) goto L35;
        if (r64 == 100) goto L35;
    L36:
        r65 = r32;
        goto L37
    L25:
        if (r62 >= this.items.size()) goto L27;
        r63 = this.items.get(r62);
        goto L28
    L50:
        if (r2.isEmpty() == true) goto L63;
        AnimatorSet r02 = this.animatorSet;
        if (r02 == null) goto L54;
        r02.cancel();
    L54:
        AnimatorSet r03 = new AnimatorSet();
        this.animatorSet = r03;
        r03.playTogether(r2);
        this.animatorSet.addListener(new AnonymousClass6(this));
        this.animatorSet.setDuration(150);
        this.animatorSet.start();
        return;
    L63:
        return;
    L12:
        if (r3.booleanValue() == true) goto L19;
        ArrayList<NotificationsSettingsActivity.NotificationException> r33 = this.exceptions;
        if (r33 != null) goto L16;
    L18:
        r32 = false;
        goto L21
    L16:
        if (r33.isEmpty() == true) goto L18;
    L20:
        r32 = getNotificationsController().isGlobalNotificationsEnabled(this.currentType);
        goto L21
    L5:
        if (this.currentType == 3) goto L7;
    }

    private void deleteException(NotificationsSettingsActivity.NotificationException r9, View r10, int r11) {
        String r112 = NotificationsController.getSharedPrefKey(r9.did, 0);
        getNotificationsSettings().edit().remove(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r112).commit();
        ArrayList<NotificationsSettingsActivity.NotificationException> r113 = this.autoExceptions;
        if (r113 == null) goto L5;
        r113.remove(r9);
    L5:
        ArrayList<NotificationsSettingsActivity.NotificationException> r114 = this.exceptions;
        if (r114 == null) goto L9;
        r114.remove(r9);
    L9:
        if (isTop5Peer(this.currentAccount, r9.did) == false) goto L12;
        r9.auto = true;
        r9.notify = 0;
        this.autoExceptions.add(r9);
    L12:
        if ((r10 instanceof UserCell) == false) goto L14;
        UserCell r102 = (UserCell) r10;
        r102.setException(r9, null, r102.needDivider);
    L14:
        getNotificationsController().updateServerNotificationsSettings(r9.did, 0, false);
        updateRows(true);
    }

    private int getLedColor() {
        int r0 = this.currentType;
        int r1 = -16776961;
        if (r0 != 0) goto L5;
        r1 = getNotificationsSettings().getInt("GroupLed", -16776961);
    L20:
        int r3 = 0;
    L22:
        if (r3 >= 9) goto L28;
        if (TextColorCell.colorsToSave[r3] == r1) goto L26;
        r3 = r3 + 1;
        goto L22
    L26:
        return TextColorCell.colors[r3];
    L28:
        return r1;
    L5:
        if (r0 != 1) goto L7;
        r1 = getNotificationsSettings().getInt("MessagesLed", -16776961);
        goto L20
    L7:
        if (r0 != 2) goto L9;
        r1 = getNotificationsSettings().getInt("ChannelLed", -16776961);
        goto L20
    L9:
        if (r0 != 3) goto L11;
        r1 = getNotificationsSettings().getInt("StoriesLed", -16776961);
        goto L20
    L11:
        if (r0 != 4) goto L13;
    L15:
        r1 = getNotificationsSettings().getInt("ReactionsLed", -16776961);
        goto L20
    L13:
        if (r0 == 5) goto L15;
        goto L15
    }

    private String getPopupOption() {
        int r0 = this.currentType;
        if (r0 == 0) goto L10;
        if (r0 != 1) goto L6;
        int r02 = getNotificationsSettings().getInt("popupAll", 0);
    L11:
        int[] r4 = this.popupOptions;
        return LocaleController.getString(r4[Utilities.clamp(r02, r4.length - 1, 0)]);
    L6:
        if (r0 == 2) goto L8;
        r02 = 0;
        goto L11
    L8:
        r02 = getNotificationsSettings().getInt("popupChannel", 0);
        goto L11
    L10:
        r02 = getNotificationsSettings().getInt("popupGroup", 0);
        goto L11
    }

    private String getPriorityOption() {
        int r0 = this.currentType;
        if (r0 == 0) goto L18;
        if (r0 != 1) goto L6;
        int r02 = getNotificationsSettings().getInt("priority_messages", 1);
    L19:
        int[] r3 = this.priorityOptions;
        return LocaleController.getString(r3[Utilities.clamp(r02, r3.length - 1, 0)]);
    L6:
        if (r0 != 2) goto L8;
        r02 = getNotificationsSettings().getInt("priority_channel", 1);
        goto L19
    L8:
        if (r0 != 3) goto L10;
        r02 = getNotificationsSettings().getInt("priority_stories", 1);
        goto L19
    L10:
        if (r0 != 4) goto L12;
    L14:
        r02 = getNotificationsSettings().getInt("priority_react", 1);
        goto L19
    L12:
        if (r0 == 5) goto L14;
        r02 = 1;
        goto L19
    L18:
        r02 = getNotificationsSettings().getInt("priority_group", 1);
        goto L19
    }

    private String getSound() {
        SharedPreferences r0 = getNotificationsSettings();
        String r1 = LocaleController.getString("SoundDefault", R.string.SoundDefault);
        int r3 = this.currentType;
        if (r3 != 0) goto L5;
        String r12 = r0.getString("GroupSound", r1);
        long r6 = r0.getLong("GroupSoundDocId", 0);
    L18:
        if (r6 == 0) goto L26;
        TLRPC.Document r8 = getMediaDataController().ringtoneDataStore.getDocument(r6);
        if (r8 != null) goto L24;
        return LocaleController.getString("CustomSound", R.string.CustomSound);
    L24:
        return NotificationsSoundActivity.trimTitle(r8, FileLoader.getDocumentFileName(r8));
    L26:
        if (r12.equals("NoSound") == false) goto L30;
        return LocaleController.getString("NoSound", R.string.NoSound);
    L30:
        if (r12.equals("Default") == true) goto L32;
        return r12;
    L32:
        return LocaleController.getString("SoundDefault", R.string.SoundDefault);
    L5:
        if (r3 != 1) goto L7;
        r12 = r0.getString("GlobalSound", r1);
        r6 = r0.getLong("GlobalSoundDocId", 0);
        goto L18
    L7:
        if (r3 != 3) goto L9;
        r12 = r0.getString("StoriesSound", r1);
        r6 = r0.getLong("StoriesSoundDocId", 0);
        goto L18
    L9:
        if (r3 != 4) goto L11;
    L13:
        r12 = r0.getString("ReactionSound", r1);
        r6 = r0.getLong("ReactionSoundDocId", 0);
        goto L18
    L11:
        if (r3 == 5) goto L13;
        r12 = r0.getString("ChannelSound", r1);
        r6 = r0.getLong("ChannelDocId", 0);
        goto L18
    }

    private static boolean isTop5Peer(int r5, long r6) {
        ArrayList r0 = new ArrayList(MediaDataController.getInstance(r5).hints);
        Collections.sort(r0, Comparator.comparingDouble(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda20()));
        int r52 = -1;
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L9;
        if (DialogObject.getPeerDialogId(((TLRPC.TL_topPeer) r0.get(r2)).peer) != r6) goto L8;
        r52 = r2;
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        if (r52 >= 0) goto L11;
    L14:
        return false;
    L11:
        if (r52 < (r0.size() - 5)) goto L14;
        return true;
    }

    private /* synthetic */ void lambda$createView$1(NotificationsSettingsActivity.NotificationException r7, View r8, int r9) {
        updateMute(r7, r8, r9, false, true);
    }

    private /* synthetic */ void lambda$createView$10(int r1) {
        updateRows(true);
    }

    private /* synthetic */ void lambda$createView$11(View r3, int r4) {
        if ((r3 instanceof TextColorCell) == false) goto L10;
        if (r4 >= 0) goto L6;
    L8:
        ((TextColorCell) r3).setTextAndColor(LocaleController.getString("LedColor", R.string.LedColor), getLedColor(), true);
        return;
    L6:
        if (r4 >= this.items.size()) goto L8;
        ItemInner r42 = this.items.get(r4);
        r42.color = getLedColor();
        goto L8
    L10:
        updateRows(true);
    }

    private /* synthetic */ void lambda$createView$12(View r3, int r4) {
        if ((r3 instanceof TextSettingsCell) == false) goto L10;
        if (r4 >= 0) goto L6;
    L8:
        TextSettingsCell r32 = (TextSettingsCell) r3;
        r32.setTextAndValue(LocaleController.getString("PopupNotification", R.string.PopupNotification), getPopupOption(), true, r32.needDivider);
        return;
    L6:
        if (r4 >= this.items.size()) goto L8;
        ItemInner r42 = this.items.get(r4);
        r42.text2 = getPopupOption();
        goto L8
    L10:
        updateRows(true);
    }

    private /* synthetic */ void lambda$createView$13(View r5, String r6, int r7) {
        if ((r5 instanceof TextSettingsCell) == false) goto L11;
        String r62 = LocaleController.getString(this.vibrateLabels[Utilities.clamp(getNotificationsSettings().getInt(r6, 0), this.vibrateLabels.length - 1, 0)]);
        if (r7 >= 0) goto L7;
    L9:
        ((TextSettingsCell) r5).setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), r62, true, true);
        return;
    L7:
        if (r7 >= this.items.size()) goto L9;
        this.items.get(r7).text2 = r62;
        goto L9
    L11:
        updateRows(true);
    }

    private /* synthetic */ void lambda$createView$14(View r3, int r4) {
        if ((r3 instanceof TextSettingsCell) == false) goto L10;
        if (r4 >= 0) goto L6;
    L8:
        TextSettingsCell r32 = (TextSettingsCell) r3;
        r32.setTextAndValue(LocaleController.getString("NotificationsImportance", R.string.NotificationsImportance), getPriorityOption(), true, r32.needDivider);
        return;
    L6:
        if (r4 >= this.items.size()) goto L8;
        ItemInner r42 = this.items.get(r4);
        r42.text2 = getPriorityOption();
        goto L8
    L10:
        updateRows(true);
    }

    private /* synthetic */ void lambda$createView$16(SharedPreferences r1, String r2, boolean[] r3, AlertDialog r4, int r5) {
        SharedPreferences.Editor r12 = r1.edit();
        r12.putBoolean(r2, r3[0]);
        r12.apply();
        updateRows(true);
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
    }

    private /* synthetic */ void lambda$createView$17(Context r29, View r30, int r31, float r32, float r33) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (this.listView.getAdapter() != this.adapter) goto L11;
        if (r31 < 0) goto L11;
        if (r31 >= this.items.size()) goto L11;
        ItemInner r0 = this.items.get(r31);
    L12:
        boolean r2 = true;
        if (r0 != null) goto L15;
    L18:
        int r4 = this.currentType;
        if (r4 != 3) goto L39;
        if (r0 == null) goto L39;
        NotificationsSettingsActivity.NotificationException r11 = r0.exception;
        if (r11 == null) goto L39;
        ItemOptions r12 = ItemOptions.makeOptions(this, r30).setGravity(3);
        if (r11.notify > 0) goto L26;
    L29:
        boolean r13 = true;
    L30:
        ItemOptions r17 = r12.addIf(r13, R.drawable.msg_mute, LocaleController.getString(R.string.NotificationsStoryMute), false, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda5(this, r11, r30, r31));
        if (r11.notify <= 0) goto L33;
    L36:
        boolean r18 = true;
    L37:
        r17.addIf(r18, R.drawable.msg_unmute, LocaleController.getString(R.string.NotificationsStoryUnmute), false, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda11(this, r11, r30, r31)).addIf(!r11.auto, R.drawable.msg_delete, LocaleController.getString("DeleteException", R.string.DeleteException), true, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda12(this, r11, r30, r31)).setScrimViewBackground(this.listView.getClipBackground(r30)).show();
        return;
    L33:
        if (r11.auto == true) goto L36;
        r18 = false;
        goto L37
    L26:
        if (r11.auto == true) goto L29;
        r13 = false;
    L39:
        if (r4 != 3) goto L83;
        RecyclerView.Adapter r42 = this.listView.getAdapter();
        SearchAdapter r112 = this.searchAdapter;
        if (r42 != r112) goto L83;
        Object r02 = r112.getObject(r31);
        if ((r02 instanceof NotificationsSettingsActivity.NotificationException) == false) goto L46;
        NotificationsSettingsActivity.NotificationException r03 = (NotificationsSettingsActivity.NotificationException) r02;
    L45:
        boolean r3 = false;
    L58:
        if (r03 == null) goto L300;
        ItemOptions r113 = ItemOptions.makeOptions(this, r30).setGravity(3);
        if (r03.notify > 0) goto L63;
    L66:
        boolean r122 = true;
    L67:
        ItemOptions r16 = r113.addIf(r122, R.drawable.msg_mute, LocaleController.getString(R.string.NotificationsStoryMute), false, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda13(this, r03, r30, r3));
        if (r03.notify <= 0) goto L70;
    L73:
        boolean r172 = true;
    L74:
        ItemOptions r21 = r16.addIf(r172, R.drawable.msg_unmute, LocaleController.getString(R.string.NotificationsStoryUnmute), false, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda14(this, r03, r30, r3));
        if (r3 == false) goto L77;
    L79:
        boolean r22 = false;
    L80:
        r21.addIf(r22, R.drawable.msg_delete, LocaleController.getString("DeleteException", R.string.DeleteException), true, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda15(this, r03, r30, r31)).setScrimViewBackground(this.listView.getClipBackground(r30)).show();
        return;
    L77:
        if (r03.auto == true) goto L79;
        r22 = true;
        goto L80
    L70:
        if (r03.auto == true) goto L73;
        r172 = false;
        goto L74
    L63:
        if (r03.auto == true) goto L66;
        r122 = false;
        goto L67
    L300:
        return;
    L46:
        boolean r34 = r02 instanceof TLRPC.User;
        if (r34 == false) goto L49;
        long r114 = ((TLRPC.User) r02).id;
    L51:
        if (this.exceptionsDict.containsKey(Long.valueOf(r114)) == false) goto L53;
        r03 = this.exceptionsDict.get(Long.valueOf(r114));
        goto L45
    L53:
        NotificationsSettingsActivity.NotificationException r43 = new NotificationsSettingsActivity.NotificationException();
        r43.story = true;
        r43.did = r114;
        if (r34 == false) goto L56;
        r43.did = ((TLRPC.User) r02).id;
    L57:
        r3 = true;
        r03 = r43;
        goto L58
    L56:
        r43.did = -((TLRPC.Chat) r02).id;
        goto L57
    L49:
        r114 = -((TLRPC.Chat) r02).id;
    L83:
        if (this.listView.getAdapter() == this.searchAdapter) goto L262;
        if (r0 != null) goto L86;
    L88:
        if (r0 == null) goto L301;
        int r44 = r0.id;
        if (r44 != 6) goto L101;
        Bundle r04 = new Bundle();
        r04.putBoolean("onlySelect", true);
        r04.putBoolean("checkCanWrite", false);
        int r23 = this.currentType;
        if (r23 != 0) goto L95;
        r04.putInt("dialogsType", 6);
    L98:
        DialogsActivity r24 = new DialogsActivity(r04);
        r24.setDelegate(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda16(this));
        presentFragment(r24);
        return;
    L95:
        if (r23 != 2) goto L97;
        r04.putInt("dialogsType", 5);
        goto L98
    L97:
        r04.putInt("dialogsType", 4);
        goto L98
    L101:
        if (r44 != 7) goto L107;
        AlertDialog.Builder r05 = new AlertDialog.Builder(getParentActivity());
        r05.setTitle(LocaleController.getString("NotificationsDeleteAllExceptionTitle", R.string.NotificationsDeleteAllExceptionTitle));
        r05.setMessage(LocaleController.getString("NotificationsDeleteAllExceptionAlert", R.string.NotificationsDeleteAllExceptionAlert));
        r05.setPositiveButton(LocaleController.getString("Delete", R.string.Delete), new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda17(this));
        r05.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        AlertDialog r06 = r05.create();
        showDialog(r06);
        TextView r07 = (TextView) r06.getButton(-1);
        if (r07 == null) goto L287;
        r07.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L287:
        return;
    L107:
        if (r44 != 100) goto L109;
    L236:
        boolean r08 = getNotificationsController().isGlobalNotificationsEnabled(this.currentType);
        TextCheckCell r45 = (TextCheckCell) r30;
        this.listView.findViewHolderForAdapterPosition(r31);
        int r46 = this.currentType;
        if (r46 != 3) goto L257;
        SharedPreferences.Editor r09 = getNotificationsSettings().edit();
        Boolean r47 = this.storiesEnabled;
        if (r47 != null) goto L241;
    L243:
        boolean r48 = false;
    L245:
        if (this.storiesAuto == false) goto L248;
        if (r48 == false) goto L248;
        r09.remove("EnableAllStories");
        this.storiesEnabled = null;
    L249:
        r09.apply();
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
        updateRows(true);
        boolean r010 = this.showAutoExceptions;
        if (this.storiesEnabled == null) goto L253;
        r2 = false;
    L253:
        if (r010 == r2) goto L255;
        toggleShowAutoExceptions();
    L255:
        checkRowsEnabled();
        return;
    L248:
        r09.putBoolean("EnableAllStories", !r48);
        this.storiesEnabled = Boolean.valueOf(!r48);
        goto L249
    L241:
        if (r47.booleanValue() == false) goto L243;
        r48 = true;
        goto L245
    L257:
        if (r08 == true) goto L260;
        getNotificationsController().setGlobalNotificationsEnabled(this.currentType, 0);
        updateRows(true);
        return;
    L260:
        AlertsCreator.showCustomNotificationsDialog(this, 0, 0, r46, this.exceptions, this.autoExceptions, this.currentAccount, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda18(this));
        return;
    L109:
        if (r44 == 101) goto L236;
        if (r44 != 3) goto L121;
        if (r30.isEnabled() == false) goto L302;
        Bundle r011 = new Bundle();     // Catch: Exception -> L117
        r011.putInt("type", this.currentType);     // Catch: Exception -> L117
        presentFragment(new NotificationsSoundActivity(r011, getResourceProvider()));     // Catch: Exception -> L117
        return;
    L117:
        e = move-exception;
        FileLog.e(e);
        return;
    L302:
        return;
    L121:
        if (r0.viewType == 3) goto L123;
        if (r44 == 2) goto L129;
        if (r44 == 1) goto L135;
        if (r44 != 4) goto L158;
        if (r30.isEnabled() == false) goto L306;
        showDialog(AlertsCreator.createPrioritySelectDialog(getParentActivity(), 0, 0, this.currentType, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda8(this, r30, r31)));
        return;
    L306:
        return;
    L158:
        if (r44 == 102) goto L160;
        if (r44 != 0) goto L198;
        if (r30.isEnabled() == false) goto L309;
        SharedPreferences r012 = getNotificationsSettings();
        SharedPreferences.Editor r35 = r012.edit();
        int r49 = this.currentType;
        if (r49 != 1) goto L184;
        boolean r013 = r012.getBoolean("EnablePreviewAll", true);
        r35.putBoolean("EnablePreviewAll", !r013);
    L193:
        r35.commit();
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
        if ((r30 instanceof TextCheckCell) == false) goto L296;
        ((TextCheckCell) r30).setChecked(!r013);
        return;
    L296:
        return;
    L184:
        if (r49 != 0) goto L186;
        r013 = r012.getBoolean("EnablePreviewGroup", true);
        r35.putBoolean("EnablePreviewGroup", !r013);
        goto L193
    L186:
        if (r49 != 3) goto L188;
        r013 = !r012.getBoolean("EnableHideStoriesSenders", false);
        r35.putBoolean("EnableHideStoriesSenders", r013);
        goto L193
    L188:
        if (r49 == 4) goto L192;
        if (r49 == 5) goto L192;
        r013 = r012.getBoolean("EnablePreviewChannel", true);
        r35.putBoolean("EnablePreviewChannel", !r013);
    L192:
        r013 = r012.getBoolean("EnableReactionsPreview", true);
        r35.putBoolean("EnableReactionsPreview", !r013);
        goto L193
    L309:
        return;
    L198:
        if (r44 == 103) goto L202;
        if (r44 == 104) goto L202;
        return;
    L202:
        if (LocaleController.isRTL == false) goto L208;
        if (r32 >= AndroidUtilities.dp(76.0f)) goto L206;
    L205:
        boolean r410 = true;
    L210:
        SharedPreferences r6 = getNotificationsSettings();
        int r014 = r0.id;
        if (r410 == false) goto L217;
        if (r014 != 103) goto L214;
        String r015 = "EnableReactionsMessages";
    L215:
        SharedPreferences.Editor r36 = r6.edit();
        r36.putBoolean(r015, !r6.getBoolean(r015, true));
        r36.apply();
        updateRows(true);
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
        return;
    L214:
        r015 = "EnableReactionsStories";
        goto L215
    L217:
        if (r014 != 103) goto L219;
        String r016 = "EnableReactionsMessagesContacts";
    L220:
        LinearLayout r411 = new LinearLayout(r29);
        r411.setOrientation(1);
        boolean[] r7 = {r6.getBoolean(r016, false)};
        RadioColorCell[] r5 = new RadioColorCell[2];
        int r10 = 0;
    L221:
        if (r10 >= 2) goto L234;
        RadioColorCell r115 = new RadioColorCell(r29, getResourceProvider());
        r5[r10] = r115;
        r115.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r5[r10].setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        RadioColorCell r116 = r5[r10];
        if (r10 != 0) goto L225;
        int r123 = R.string.NotifyAboutReactionsFromEveryone;
    L226:
        String r124 = LocaleController.getString(r123);
        if (r10 == 0) goto L229;
        boolean r14 = r7[0];
    L233:
        r116.setTextAndValue(r124, r14);
        r5[r10].setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r411.addView(r5[r10]);
        r5[r10].setOnClickListener(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda9(r7, r10, r5));
        r10 = r10 + 1;
        goto L221
    L229:
        if (r7[0] == true) goto L231;
        r14 = true;
        goto L233
    L231:
        r14 = false;
        goto L233
    L225:
        r123 = R.string.NotifyAboutReactionsFromContacts;
        goto L226
    L234:
        showDialog(new AlertDialog.Builder(getContext(), this.resourceProvider).setTitle(LocaleController.getString(R.string.NotifyAboutReactionsFrom)).setView(r411).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.Save), new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda10(this, r6, r016, r7)).create());
        return;
    L219:
        r016 = "EnableReactionsStoriesContacts";
    L206:
        r410 = false;
        goto L210
    L208:
        if (r32 <= (r30.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L206;
    L160:
        if (r30.isEnabled() == false) goto L307;
        SharedPreferences r412 = getNotificationsSettings();
        if (r412.getBoolean("EnableAllStories", false) == true) goto L308;
        SharedPreferences.Editor r413 = r412.edit();
        if (this.storiesEnabled == null) goto L168;
        r413.remove("EnableAllStories");
        this.storiesEnabled = null;
        this.storiesAuto = true;
        r0.checked = true;
    L170:
        if ((r30 instanceof TextCheckCell) == false) goto L172;
        ((TextCheckCell) r30).setChecked(this.storiesAuto);
    L172:
        r413.commit();
        if (this.storiesAuto == this.showAutoExceptions) goto L175;
        toggleShowAutoExceptions();
    L175:
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
        checkRowsEnabled();
        return;
    L168:
        r413.putBoolean("EnableAllStories", false);
        this.storiesEnabled = Boolean.FALSE;
        this.storiesAuto = false;
        r0.checked = false;
        goto L170
    L308:
        return;
    L307:
        return;
    L135:
        if (r30.isEnabled() == false) goto L305;
        int r017 = this.currentType;
        if (r017 != 1) goto L140;
        String r018 = "vibrate_messages";
    L149:
        showDialog(AlertsCreator.createVibrationSelectDialog(getParentActivity(), 0, 0, r018, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda7(this, r30, r018, r31)));
        return;
    L140:
        if (r017 != 0) goto L142;
        r018 = "vibrate_group";
        goto L149
    L142:
        if (r017 != 3) goto L144;
        r018 = "vibrate_stories";
        goto L149
    L144:
        if (r017 == 4) goto L148;
        if (r017 == 5) goto L148;
        r018 = "vibrate_channel";
    L148:
        r018 = "vibrate_react";
        goto L149
    L305:
        return;
    L129:
        if (r30.isEnabled() == false) goto L304;
        showDialog(AlertsCreator.createPopupSelectDialog(getParentActivity(), this.currentType, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda6(this, r30, r31)));
        return;
    L304:
        return;
    L123:
        if (r30.isEnabled() == false) goto L303;
        showDialog(AlertsCreator.createColorSelectDialog(getParentActivity(), 0, 0, this.currentType, new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda19(this, r30, r31)));
        return;
    L303:
        return;
    L301:
        return;
    L86:
        if (r0.exception == null) goto L88;
    L262:
        RecyclerView.Adapter r37 = this.listView.getAdapter();
        SearchAdapter r414 = this.searchAdapter;
        if (r37 != r414) goto L282;
        Object r019 = r414.getObject(r31);
        if ((r019 instanceof NotificationsSettingsActivity.NotificationException) == false) goto L267;
        NotificationsSettingsActivity.NotificationException r020 = (NotificationsSettingsActivity.NotificationException) r019;
        ArrayList<NotificationsSettingsActivity.NotificationException> r38 = SearchAdapter.m16285$$Nest$fgetsearchResult(this.searchAdapter);
        r2 = false;
    L280:
        boolean r62 = r2;
    L281:
        NotificationsSettingsActivity.NotificationException r52 = r020;
        if (r52 == null) goto L298;
        long r125 = r52.did;
        ChatNotificationsPopupWrapper r117 = new ChatNotificationsPopupWrapper(r29, this.currentAccount, null, true, true, new AnonymousClass3(this, r125, NotificationsController.getInstance(this.currentAccount).isGlobalNotificationsEnabled(r125, false, false), r52, r62, r31, r38), getResourceProvider());
        r117.update(r125, this.topicId, null);
        r117.showAsOptions(this, r30, r32, r33, false);
        return;
    L298:
        return;
    L267:
        boolean r39 = r019 instanceof TLRPC.User;
        if (r39 == false) goto L270;
        long r415 = ((TLRPC.User) r019).id;
    L272:
        if (this.exceptionsDict.containsKey(Long.valueOf(r415)) == false) goto L274;
        r020 = this.exceptionsDict.get(Long.valueOf(r415));
        r2 = false;
    L279:
        r38 = this.exceptions;
        goto L280
    L274:
        NotificationsSettingsActivity.NotificationException r63 = new NotificationsSettingsActivity.NotificationException();
        r63.did = r415;
        if (r39 == false) goto L277;
        r63.did = ((TLRPC.User) r019).id;
    L278:
        r020 = r63;
        goto L279
    L277:
        r63.did = -((TLRPC.Chat) r019).id;
        goto L278
    L270:
        r415 = -((TLRPC.Chat) r019).id;
        goto L272
    L282:
        r020 = r0.exception;
        if (r020.auto == true) goto L310;
        r38 = this.exceptions;
        r62 = false;
        goto L281
    L310:
        return;
    L15:
        if (r0.viewType != 8) goto L18;
        this.expanded = !this.expanded;
        updateRows(true);
        return;
    L11:
        r0 = null;
        goto L12
    }

    private /* synthetic */ void lambda$createView$2(NotificationsSettingsActivity.NotificationException r7, View r8, int r9) {
        updateMute(r7, r8, r9, false, false);
    }

    private /* synthetic */ void lambda$createView$3(NotificationsSettingsActivity.NotificationException r1, View r2, int r3) {
        deleteException(r1, r2, r3);
    }

    private /* synthetic */ void lambda$createView$4(NotificationsSettingsActivity.NotificationException r8, View r9, boolean r10) {
        this.actionBar.closeSearchField();
        updateMute(r8, r9, -1, r10, true);
    }

    private /* synthetic */ void lambda$createView$5(NotificationsSettingsActivity.NotificationException r8, View r9, boolean r10) {
        this.actionBar.closeSearchField();
        updateMute(r8, r9, -1, r10, false);
    }

    private /* synthetic */ void lambda$createView$6(NotificationsSettingsActivity.NotificationException r1, View r2, int r3) {
        deleteException(r1, r2, r3);
    }

    private /* synthetic */ void lambda$createView$7(NotificationsSettingsActivity.NotificationException r3) {
        this.exceptions.add(0, r3);
        updateRows(true);
    }

    private /* synthetic */ boolean lambda$createView$8(DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        int r12 = 0;
        long r22 = ((MessagesStorage.TopicKey) r2.get(0)).dialogId;
        if (this.currentType != 3) goto L29;
        ArrayList<NotificationsSettingsActivity.NotificationException> r42 = this.autoExceptions;
        if (r42 == null) goto L12;
        Iterator<NotificationsSettingsActivity.NotificationException> r43 = r42.iterator();
    L8:
        if (r43.hasNext() == false) goto L12;
        if (r43.next().did != r22) goto L8;
        r43.remove();
    L12:
        ArrayList<NotificationsSettingsActivity.NotificationException> r44 = this.exceptions;
        if (r44 == null) goto L20;
        Iterator<NotificationsSettingsActivity.NotificationException> r45 = r44.iterator();
    L16:
        if (r45.hasNext() == false) goto L20;
        if (r45.next().did != r22) goto L16;
        r45.remove();
    L20:
        NotificationsSettingsActivity.NotificationException r46 = new NotificationsSettingsActivity.NotificationException();
        r46.did = r22;
        r46.story = true;
        Boolean r23 = this.storiesEnabled;
        if (r23 != null) goto L23;
    L25:
        r46.notify = r12;
        if (this.exceptions != null) goto L28;
        this.exceptions = new ArrayList();
    L28:
        this.exceptions.add(r46);
        updateRows(true);
    L30:
        return true;
    L23:
        if (r23.booleanValue() == false) goto L25;
        r12 = Integer.MAX_VALUE;
        goto L25
    L29:
        Bundle r13 = new Bundle();
        r13.putLong("dialog_id", r22);
        r13.putBoolean("exception", true);
        ProfileNotificationsActivity r24 = new ProfileNotificationsActivity(r13, getResourceProvider());
        r24.setDelegate(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda21(this));
        presentFragment(r24, true);
        goto L30
    }

    private /* synthetic */ void lambda$createView$9(AlertDialog r9, int r10) {
        SharedPreferences.Editor r92 = getNotificationsSettings().edit();
        int r102 = this.exceptions.size();
        int r1 = 0;
    L3:
        if (r1 >= r102) goto L12;
        NotificationsSettingsActivity.NotificationException r2 = this.exceptions.get(r1);
        if (this.currentType != 3) goto L7;
        r92.remove(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r2.did);
    L8:
        getMessagesStorage().setDialogFlags(r2.did, 0);
        TLRPC.Dialog r22 = getMessagesController().dialogs_dict.get(r2.did);
        if (r22 == null) goto L11;
        r22.notify_settings = new TLRPC.TL_peerNotifySettings();
    L11:
        r1 = r1 + 1;
        goto L3
    L7:
        r92.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + r2.did).remove(NotificationsSettingsFacade.PROPERTY_CUSTOM + r2.did);
        goto L8
    L12:
        r92.apply();
        int r93 = this.exceptions.size();
        int r103 = 0;
    L13:
        ArrayList<NotificationsSettingsActivity.NotificationException> r12 = this.exceptions;
        if (r103 >= r93) goto L16;
        getNotificationsController().updateServerNotificationsSettings(r12.get(r103).did, this.topicId, false);
        r103 = r103 + 1;
        goto L13
    L16:
        r12.clear();
        this.exceptionsDict.clear();
        updateRows(true);
        getNotificationCenter().postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$21() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof UserCell) == false) goto L9;
        ((UserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private /* synthetic */ void lambda$loadExceptions$19(ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7, ArrayList r8, ArrayList r9, ArrayList r10) {
        getMessagesController().putUsers(r3, true);
        getMessagesController().putChats(r4, true);
        getMessagesController().putEncryptedChats(r5, true);
        int r32 = this.currentType;
        if (r32 != 1) goto L5;
        this.exceptions = r6;
    L11:
        updateRows(true);
        return;
    L5:
        if (r32 != 0) goto L8;
        this.exceptions = r7;
        goto L11
    L8:
        if (r32 != 3) goto L10;
        this.exceptions = r8;
        this.autoExceptions = r9;
        goto L11
    L10:
        this.exceptions = r10;
        goto L11
    }

    private /* synthetic */ void lambda$loadExceptions$20(ArrayList r28) {
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        ArrayList r8 = new ArrayList();
        ArrayList r9 = new ArrayList();
        LongSparseArray r1 = new LongSparseArray();
        ArrayList<Long> r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList<TLRPC.User> r10 = new ArrayList();
        ArrayList<TLRPC.Chat> r11 = new ArrayList();
        ArrayList<TLRPC.EncryptedChat> r12 = new ArrayList();
        long r13 = getUserConfig().clientUserId;
        SharedPreferences r15 = getNotificationsSettings();
        Map<String, ?> r132 = r15.getAll();
        Iterator<Map.Entry<String, ?>> r14 = r132.entrySet().iterator();
    L3:
        Iterator<Map.Entry<String, ?>> r21 = r14;
        if (r14.hasNext() == false) goto L55;
        Map.Entry<String, ?> r18 = r21.next();
        String r142 = r18.getKey();
        ArrayList<TLRPC.Chat> r22 = r11;
        if (r142.startsWith(NotificationsSettingsFacade.PROPERTY_NOTIFY) == false) goto L54;
        ArrayList<TLRPC.User> r23 = r10;
        String r102 = r142.replace(NotificationsSettingsFacade.PROPERTY_NOTIFY, _UrlKt.FRAGMENT_ENCODE_SET);
        Long r112 = Utilities.parseLong(r102);
        ArrayList r143 = r7;
        ArrayList r24 = r8;
        long r72 = r112.longValue();
        if (r72 != 0) goto L10;
    L53:
        ArrayList<TLRPC.EncryptedChat> r26 = r12;
        ArrayList r25 = r143;
    L26:
        r14 = r21;
        r11 = r22;
        r10 = r23;
        r8 = r24;
        r7 = r25;
        r12 = r26;
        goto L3
    L10:
        if (r72 == r13) goto L53;
        r25 = r143;
        NotificationsSettingsActivity.NotificationException r144 = new NotificationsSettingsActivity.NotificationException();
        r144.did = r72;
        r26 = r12;
        r144.hasCustom = r15.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r72, false);
        int r0 = ((Integer) r18.getValue()).intValue();
        r144.notify = r0;
        if (r0 == 0) goto L17;
        Integer r02 = (Integer) r132.get(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + r102);
        if (r02 == null) goto L17;
        r144.muteUntil = r02.intValue();
    L17:
        if (DialogObject.isEncryptedDialog(r72) == false) goto L29;
        int r03 = DialogObject.getEncryptedChatId(r72);
        TLRPC.EncryptedChat r103 = getMessagesController().getEncryptedChat(Integer.valueOf(r03));
        if (r103 != null) goto L21;
        r4.add(Integer.valueOf(r03));
        r1.put(r72, r144);
    L27:
        r5.add(r144);
        goto L26
    L21:
        TLRPC.User r04 = getMessagesController().getUser(Long.valueOf(r103.user_id));
        if (r04 != null) goto L25;
        r2.add(Long.valueOf(r103.user_id));
        r1.put(r103.user_id, r144);
        goto L27
    L25:
        if (r04.deleted == false) goto L27;
    L29:
        if (DialogObject.isUserDialog(r72) == false) goto L37;
        TLRPC.User r05 = getMessagesController().getUser(r112);
        if (r05 != null) goto L34;
        r2.add(r112);
        r1.put(r72, r144);
    L36:
        r5.add(r144);
        goto L26
    L34:
        if (r05.deleted == false) goto L36;
    L37:
        long r104 = -r72;
        TLRPC.Chat r06 = getMessagesController().getChat(Long.valueOf(r104));
        if (r06 != null) goto L41;
        r3.add(Long.valueOf(r104));
        r1.put(r72, r144);
        goto L26
    L41:
        if (r06.left == true) goto L26;
        if (r06.kicked == true) goto L26;
        if (r06.migrated_to != null) goto L26;
        if (ChatObject.isChannel(r06) == true) goto L50;
    L52:
        r6.add(r144);
        goto L26
    L50:
        if (r06.megagroup == true) goto L52;
        r9.add(r144);
        goto L26
    L54:
        r25 = r7;
        r24 = r8;
        r23 = r10;
        r26 = r12;
        goto L26
    L55:
        ArrayList r252 = r7;
        ArrayList r242 = r8;
        ArrayList<TLRPC.User> r232 = r10;
        ArrayList<TLRPC.Chat> r222 = r11;
        ArrayList<TLRPC.EncryptedChat> r262 = r12;
        HashSet r07 = new HashSet();
        Iterator<Map.Entry<String, ?>> r73 = r132.entrySet().iterator();
    L56:
        boolean r105 = true;
        if (r73.hasNext() == false) goto L82;
        Map.Entry<String, ?> r82 = r73.next();
        String r113 = r82.getKey();
        if (r113.startsWith(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY) == false) goto L80;
        Long r114 = Utilities.parseLong(r113.substring(8));     // Catch: Exception -> L174
        long r122 = r114.longValue();     // Catch: Exception -> L174
        if (r122 == 0) goto L80;
        if (r122 == r13) goto L80;
        NotificationsSettingsActivity.NotificationException r145 = new NotificationsSettingsActivity.NotificationException();     // Catch: Exception -> L174
        r145.did = r122;     // Catch: Exception -> L174
        r145.story = true;     // Catch: Exception -> L174
        if (((Boolean) r82.getValue()).booleanValue() == false) goto L68;
        int r83 = 0;
    L69:
        r145.notify = r83;     // Catch: Exception -> L174
        if (DialogObject.isUserDialog(r122) == false) goto L80;
        TLRPC.User r84 = getMessagesController().getUser(r114);     // Catch: Exception -> L174
        if (r84 == null) goto L73;
        if (r84.deleted == true) goto L56;
    L74:
        ArrayList r85 = r252;
        r85.add(r145);     // Catch: Exception -> L173
        r07.add(r114);     // Catch: Exception -> L173
    L81:
        r252 = r85;
        goto L56
    L73:
        r2.add(r114);     // Catch: Exception -> L174
        r1.put(r122, r145);     // Catch: Exception -> L174
        goto L74
    L68:
        r83 = Integer.MAX_VALUE;
    L80:
        r85 = r252;
        goto L81
    L82:
        ArrayList r86 = r252;
        if (r28 == null) goto L100;
        Collections.sort(r28, Comparator.comparingDouble(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda3()));
        int r74 = Math.max(0, r28.size() - 6);
    L86:
        if (r74 >= r28.size()) goto L100;
        long r123 = DialogObject.getPeerDialogId(((TLRPC.TL_topPeer) r28.get(r74)).peer);
        if (r07.contains(Long.valueOf(r123)) == true) goto L97;
        NotificationsSettingsActivity.NotificationException r146 = new NotificationsSettingsActivity.NotificationException();
        r146.did = r123;
        r146.story = r105;
        r146.notify = 0;
        r146.auto = r105;
        if (DialogObject.isUserDialog(r123) == false) goto L97;
        TLRPC.User r106 = getMessagesController().getUser(Long.valueOf(r123));
        if (r106 != null) goto L96;
        r2.add(Long.valueOf(r123));
        r1.put(r123, r146);
    L94:
        ArrayList r253 = r86;
        ArrayList r87 = r242;
        r87.add(0, r146);
        r07.add(Long.valueOf(r123));
    L99:
        r74 = r74 + 1;
        r242 = r87;
        r86 = r253;
        r105 = true;
        goto L86
    L96:
        if (r106.deleted == false) goto L94;
    L97:
        r253 = r86;
        r87 = r242;
    L100:
        ArrayList r254 = r86;
        ArrayList r88 = r242;
        if (r1.size() != 0) goto L179;
        ArrayList<TLRPC.Chat> r32 = r222;
        ArrayList<TLRPC.User> r42 = r232;
        ArrayList<TLRPC.EncryptedChat> r107 = r262;
    L171:
        AndroidUtilities.runOnUIThread(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda4(this, r42, r32, r107, r5, r6, r254, r88, r9));
        return;
    L179:
        if (r4.isEmpty() == false) goto L185;
        r107 = r262;
    L113:
        if (r2.isEmpty() == false) goto L175;
        r42 = r232;
    L124:
        if (r3.isEmpty() == true) goto L131;
        MessagesStorage r08 = getMessagesStorage();     // Catch: Exception -> L118
        String r27 = TextUtils.join(",", r3);     // Catch: Exception -> L118
        r32 = r222;
        r08.getChatsInternal(r27, r32);     // Catch: Exception -> L129
    L135:
        int r09 = r32.size();
        int r124 = 0;
    L136:
        if (r124 >= r09) goto L153;
        TLRPC.Chat r29 = r32.get(r124);
        if (r29.left == true) goto L152;
        if (r29.kicked == true) goto L152;
        if (r29.migrated_to != null) goto L152;
        NotificationsSettingsActivity.NotificationException r75 = (NotificationsSettingsActivity.NotificationException) r1.get(-r29.id);
        r1.remove(-r29.id);
        if (r75 == null) goto L152;
        if (ChatObject.isChannel(r29) == true) goto L149;
    L151:
        r6.add(r75);
        goto L152
    L149:
        if (r29.megagroup == true) goto L151;
        r9.add(r75);
    L152:
        r124 = r124 + 1;
        goto L136
    L153:
        int r010 = r42.size();
        int r125 = 0;
    L154:
        if (r125 >= r010) goto L160;
        TLRPC.User r210 = r42.get(r125);
        if (r210.deleted == true) goto L159;
        r1.remove(r210.id);
    L159:
        r125 = r125 + 1;
        goto L154
    L160:
        int r011 = r107.size();
        int r126 = 0;
    L161:
        if (r126 >= r011) goto L163;
        r1.remove(DialogObject.makeEncryptedDialogId(r107.get(r126).id));
        r126 = r126 + 1;
        goto L161
    L163:
        int r012 = r1.size();
        int r147 = 0;
    L164:
        if (r147 >= r012) goto L171;
        if (DialogObject.isChatDialog(r1.keyAt(r147)) == false) goto L168;
        r6.remove(r1.valueAt(r147));
        r9.remove(r1.valueAt(r147));
    L169:
        r147 = r147 + 1;
        goto L164
    L168:
        r5.remove(r1.valueAt(r147));
    L129:
        e = e;
    L134:
        FileLog.e(e);
    L118:
        e = e;
    L119:
        r32 = r222;
        goto L134
    L131:
        r32 = r222;
        goto L135
    L175:
        r42 = r232;
        getMessagesStorage().getUsersInternal(r2, r42);     // Catch: Exception -> L118
    L120:
        e = e;
        r42 = r232;
    L108:
        e = e;
    L109:
        r32 = r222;
        r42 = r232;
        goto L134
    L185:
        r107 = r262;
        getMessagesStorage().getEncryptedChatsInternal(TextUtils.join(",", r4), r107, r2);     // Catch: Exception -> L108
    L110:
        e = e;
        r107 = r262;
    L132:
        e = e;
        r32 = r222;
        r42 = r232;
        r107 = r262;
        goto L134
    }

    private void loadExceptions() {
        if (this.currentType != 3) goto L5;
        MediaDataController.getInstance(this.currentAccount).loadHints(true);
        ArrayList r0 = new ArrayList(MediaDataController.getInstance(this.currentAccount).hints);
    L6:
        getMessagesStorage().getStorageQueue().postRunnable(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda2(this, r0));
        return;
    L5:
        r0 = null;
        goto L6
    }

    private void updateMute(NotificationsSettingsActivity.NotificationException r17, View r18, int r19, boolean r20, boolean r21) {
        String r3 = NotificationsController.getSharedPrefKey(r17.did, 0);
        SharedPreferences.Editor r4 = getNotificationsSettings().edit();
        boolean r5 = isTop5Peer(this.currentAccount, r17.did);
        if (r21 == false) goto L5;
        int r7 = Integer.MAX_VALUE;
    L6:
        r17.notify = r7;
        if (r17.auto == false) goto L15;
        r17.auto = false;
        r4.putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r3, !r21).commit();
        ArrayList<NotificationsSettingsActivity.NotificationException> r32 = this.autoExceptions;
        if (r32 == null) goto L12;
        r32.remove(r17);
    L12:
        if (this.exceptions != null) goto L14;
        this.exceptions = new ArrayList();
    L14:
        this.exceptions.add(0, r17);
    L30:
        if ((r18 instanceof UserCell) == false) goto L32;
        UserCell r2 = (UserCell) r18;
        r2.setException(r17, null, r2.needDivider);
    L32:
        getNotificationsController().updateServerNotificationsSettings(r17.did, 0, false);
        updateRows(true);
        return;
    L15:
        if (r5 == false) goto L17;
        r4.putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r3, !r21).commit();
        goto L30
    L17:
        Boolean r52 = this.storiesEnabled;
        if (r21 == false) goto L23;
        if (r52 != null) goto L21;
    L26:
        deleteException(r17, r18, r19);
        return;
    L21:
        if (r52.booleanValue() == false) goto L26;
    L28:
        r4.putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r3, !r21).commit();
        goto L30
    L23:
        if (r52 == null) goto L28;
        if (r52.booleanValue() == false) goto L28;
    L5:
        r7 = 0;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.searching = false;
        this.searchWas = false;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r1 = this.currentType;
        ActionBar r3 = this.actionBar;
        if (r1 != (-1)) goto L5;
        r3.setTitle(LocaleController.getString("NotificationsExceptions", R.string.NotificationsExceptions));
    L6:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ArrayList<NotificationsSettingsActivity.NotificationException> r12 = this.exceptions;
        if (r12 != null) goto L9;
    L11:
        this.searchAdapter = new SearchAdapter(this, r8);
        FrameLayout r13 = new FrameLayout(r8);
        this.fragmentView = r13;
        r13.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        EmptyTextProgressView r32 = new EmptyTextProgressView(r8);
        this.emptyView = r32;
        r32.setTextSize(18);
        this.emptyView.setText(LocaleController.getString("NoExceptions", R.string.NoExceptions));
        this.emptyView.showTextView();
        r13.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r33 = new RecyclerListView(r8);
        this.listView = r33;
        r33.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        r13.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r14 = this.listView;
        ListAdapter r2 = new ListAdapter(this, r8);
        this.adapter = r2;
        r14.setAdapter(r2);
        this.listView.setOnItemClickListener(new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda0(this, r8));
        AnonymousClass4 r82 = new AnonymousClass4(this);
        r82.setAddDuration(150);
        r82.setMoveDuration(350);
        r82.setChangeDuration(0);
        r82.setRemoveDuration(0);
        r82.setDelayAnimations(false);
        r82.setMoveInterpolator(new OvershootInterpolator(1.1f));
        r82.setTranslationInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r82.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r82);
        this.listView.setOnScrollListener(new AnonymousClass5(this));
        return this.fragmentView;
    L9:
        if (r12.isEmpty() == true) goto L11;
        this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this)).setSearchFieldHint(LocaleController.getString("Search", R.string.Search));
        goto L11
    L5:
        r3.setTitle(LocaleController.getString("Notifications", R.string.Notifications));
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.notificationsSettingsUpdated) goto L9;
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L12;
        r0.notifyDataSetChanged();
        return;
    L12:
        return;
    L9:
        if (r1 != NotificationCenter.reloadHints) goto L13;
        loadExceptions();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        NotificationsCustomSettingsActivity$$ExternalSyntheticLambda1 r8 = new NotificationsCustomSettingsActivity$$ExternalSyntheticLambda1(this);
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, TextCheckCell.class, TextColorCell.class, TextSettingsCell.class, UserCell.class, NotificationsCheckCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r17 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r17));
        int r37 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        int r47 = Theme.key_switchTrack;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r47));
        int r56 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r56));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r47));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r56));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextColorCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_text_RedRegular));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r5, int r6, Intent r7) {
        super.onActivityResultFragment(r5, r6, r7);
        if (r6 != (-1)) goto L45;
        Uri r62 = (Uri) r7.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
        if (r62 == null) goto L13;
        Ringtone r72 = RingtoneManager.getRingtone(getParentActivity(), r62);
        if (r72 == null) goto L13;
        if (r62.equals(Settings.System.DEFAULT_NOTIFICATION_URI) == false) goto L11;
        String r0 = LocaleController.getString("SoundDefault", R.string.SoundDefault);
    L12:
        r72.stop();
    L14:
        SharedPreferences.Editor r73 = getNotificationsSettings().edit();
        int r1 = this.currentType;
        if (r1 == 1) goto L17;
        if (r1 != 0) goto L28;
        if (r0 == null) goto L26;
        if (r62 == null) goto L26;
        r73.putString("GroupSound", r0);
        r73.putString("GroupSoundPath", r62.toString());
    L41:
        getNotificationsController().deleteNotificationChannelGlobal(this.currentType);
        r73.apply();
        getNotificationsController().updateServerNotificationsSettings(this.currentType);
        RecyclerView.ViewHolder r63 = this.listView.findViewHolderForAdapterPosition(r5);
        if (r63 == null) goto L46;
        this.adapter.onBindViewHolder(r63, r5);
        return;
    L46:
        return;
    L26:
        r73.putString("GroupSound", "NoSound");
        r73.putString("GroupSoundPath", "NoSound");
        goto L41
    L28:
        if (r1 != 2) goto L35;
        if (r0 == null) goto L33;
        if (r62 == null) goto L33;
        r73.putString("ChannelSound", r0);
        r73.putString("ChannelSoundPath", r62.toString());
    L33:
        r73.putString("ChannelSound", "NoSound");
        r73.putString("ChannelSoundPath", "NoSound");
        goto L41
    L35:
        if (r1 != 3) goto L41;
        if (r0 == null) goto L40;
        if (r62 == null) goto L40;
        r73.putString("StoriesSound", r0);
        r73.putString("StoriesSoundPath", r62.toString());
    L40:
        r73.putString("StoriesSound", "NoSound");
        r73.putString("StoriesSoundPath", "NoSound");
        goto L41
    L17:
        if (r0 == null) goto L20;
        if (r62 == null) goto L20;
        r73.putString("GlobalSound", r0);
        r73.putString("GlobalSoundPath", r62.toString());
    L20:
        r73.putString("GlobalSound", "NoSound");
        r73.putString("GlobalSoundPath", "NoSound");
        goto L41
    L11:
        r0 = r72.getTitle(getParentActivity());
    L13:
        r0 = null;
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        updateRows(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        if (this.currentType == 3) goto L5;
    L8:
        updateRows(true);
        return super.onFragmentCreate();
    L5:
        if (getNotificationsSettings().contains("EnableAllStories") == false) goto L7;
        this.storiesEnabled = Boolean.valueOf(getNotificationsSettings().getBoolean("EnableAllStories", true));
        this.storiesAuto = false;
        this.showAutoExceptions = false;
        goto L8
    L7:
        this.storiesEnabled = null;
        this.storiesAuto = true;
        this.showAutoExceptions = true;
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        getNotificationCenter().removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.reloadHints);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        getNotificationCenter().addObserver(this, NotificationCenter.notificationsSettingsUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.reloadHints);
    }

    public void toggleShowAutoExceptions() {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.adapter == null) goto L10;
        this.showAutoExceptions = !this.showAutoExceptions;
        updateRows(true);
        return;
    }

    public void updateRows(boolean r15) {
        this.newRow = -1;
        this.showRow = -1;
        this.importantRow = -1;
        this.messagesRow = -1;
        this.storiesRow = -1;
        this.previewRow = -1;
        this.showSenderRow = -1;
        this.soundRow = -1;
        this.addExceptionRow = -1;
        this.deleteExceptionsRow = -1;
        this.lightColorRow = -1;
        this.popupRow = -1;
        this.vibrateRow = -1;
        this.priorityRow = -1;
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        SharedPreferences r1 = getNotificationsSettings();
        if (this.currentType == (-1)) goto L79;
        this.items.add(ItemInner.asHeader(LocaleController.getString(R.string.NotifyMeAbout)));
        int r2 = this.currentType;
        if (r2 != 3) goto L18;
        this.newRow = this.items.size();
        this.items.add(ItemInner.asCheck(101, LocaleController.getString(R.string.NotifyMeAboutNewStories), r1.getBoolean("EnableAllStories", false)));
        if (r1.getBoolean("EnableAllStories", false) == true) goto L17;
        this.importantRow = this.items.size();
        ArrayList<ItemInner> r22 = this.items;
        String r9 = LocaleController.getString(R.string.NotifyMeAboutImportantStories);
        if (this.storiesAuto == false) goto L15;
        Boolean r10 = this.storiesEnabled;
        if (r10 != null) goto L13;
    L14:
        boolean r102 = true;
    L16:
        r22.add(ItemInner.asCheck(102, r9, r102));
        goto L17
    L13:
        if (r10.booleanValue() == false) goto L14;
    L15:
        r102 = false;
    L17:
        this.items.add(ItemInner.asShadow(-1, LocaleController.getString(R.string.StoryAutoExceptionsInfo)));
    L42:
        this.items.add(ItemInner.asHeader(LocaleController.getString(R.string.SETTINGS)));
        this.settingsStart = this.items.size() - 1;
        int r23 = this.currentType;
        if (r23 != 3) goto L45;
        this.showSenderRow = this.items.size();
        this.items.add(ItemInner.asCheck(0, LocaleController.getString(R.string.NotificationShowSenderNames), !r1.getBoolean("EnableHideStoriesSenders", false)));
    L57:
        this.soundRow = this.items.size();
        this.items.add(ItemInner.asSetting(3, LocaleController.getString("Sound", R.string.Sound), getSound()));
        boolean r24 = this.expanded;
        ArrayList<ItemInner> r103 = this.items;
        if (r24 == false) goto L77;
        this.lightColorRow = r103.size();
        this.items.add(ItemInner.asColor(LocaleController.getString("LedColor", R.string.LedColor), getLedColor()));
        int r25 = this.currentType;
        if (r25 == 0) goto L71;
        if (r25 == 1) goto L70;
        if (r25 == 2) goto L69;
        if (r25 == 3) goto L68;
        if (r25 == 4) goto L67;
        if (r25 == 5) goto L67;
        int r12 = 0;
    L72:
        this.vibrateRow = this.items.size();
        ArrayList<ItemInner> r26 = this.items;
        String r8 = LocaleController.getString("Vibrate", R.string.Vibrate);
        int[] r104 = this.vibrateLabels;
        r26.add(ItemInner.asSetting(1, r8, LocaleController.getString(r104[Utilities.clamp(r12, r104.length - 1, 0)])));
        int r13 = this.currentType;
        if (r13 == 1) goto L75;
        if (r13 == 0) goto L75;
    L76:
        this.priorityRow = this.items.size();
        this.items.add(ItemInner.asSetting(4, LocaleController.getString("NotificationsImportance", R.string.NotificationsImportance), getPriorityOption()));
        this.items.add(ItemInner.asExpand(LocaleController.getString(R.string.NotifyLessOptions), false));
    L78:
        this.settingsEnd = this.items.size() - 1;
        this.items.add(ItemInner.asShadow(-2, null));
    L75:
        this.popupRow = this.items.size();
        this.items.add(ItemInner.asSetting(2, LocaleController.getString("PopupNotification", R.string.PopupNotification), getPopupOption()));
    L67:
        r12 = r1.getInt("vibrate_react", 0);
        goto L72
    L68:
        r12 = r1.getInt("vibrate_stories", 0);
        goto L72
    L69:
        r12 = r1.getInt("vibrate_channel", 0);
        goto L72
    L70:
        r12 = r1.getInt("vibrate_messages", 0);
        goto L72
    L71:
        r12 = r1.getInt("vibrate_group", 0);
        goto L72
    L77:
        r103.add(ItemInner.asExpand(LocaleController.getString(R.string.NotifyMoreOptions), true));
        goto L78
    L45:
        if (r23 == 4) goto L56;
        if (r23 == 5) goto L56;
        if (r23 == 0) goto L54;
        if (r23 == 1) goto L53;
        if (r23 == 2) goto L52;
        boolean r27 = false;
    L55:
        this.previewRow = this.items.size();
        this.items.add(ItemInner.asCheck(0, LocaleController.getString(R.string.MessagePreview), r27));
        goto L57
    L52:
        r27 = r1.getBoolean("EnablePreviewChannel", true);
        goto L55
    L53:
        r27 = r1.getBoolean("EnablePreviewAll", true);
        goto L55
    L54:
        r27 = r1.getBoolean("EnablePreviewGroup", true);
    L56:
        this.showSenderRow = this.items.size();
        this.items.add(ItemInner.asCheck(0, LocaleController.getString(R.string.NotificationShowSenderNames), r1.getBoolean("EnableReactionsPreview", true)));
        goto L57
    L18:
        if (r2 == 4) goto L27;
        if (r2 == 5) goto L27;
        if (r2 != 1) goto L23;
        int r28 = R.string.NotifyMeAboutPrivate;
    L26:
        this.showRow = this.items.size();
        this.items.add(ItemInner.asCheck(100, LocaleController.getString(r28), getNotificationsController().isGlobalNotificationsEnabled(this.currentType)));
        this.items.add(ItemInner.asShadow(-1, null));
        goto L42
    L23:
        if (r2 != 0) goto L25;
        r28 = R.string.NotifyMeAboutGroups;
        goto L26
    L25:
        r28 = R.string.NotifyMeAboutChannels;
    L27:
        this.messagesRow = this.items.size();
        ArrayList<ItemInner> r29 = this.items;
        int r92 = R.drawable.msg_markunread;
        String r105 = LocaleController.getString(R.string.NotifyMeAboutMessagesReactions);
        if (r1.getBoolean("EnableReactionsMessages", true) == true) goto L31;
        int r122 = R.string.NotifyFromNobody;
    L34:
        r29.add(ItemInner.asCheck2(103, r92, r105, LocaleController.getString(r122), r1.getBoolean("EnableReactionsMessages", true)));
        this.storiesRow = this.items.size();
        ArrayList<ItemInner> r210 = this.items;
        int r93 = R.drawable.msg_stories_saved;
        String r106 = LocaleController.getString(R.string.NotifyMeAboutStoriesReactions);
        if (r1.getBoolean("EnableReactionsStories", true) == true) goto L38;
        int r123 = R.string.NotifyFromNobody;
    L41:
        r210.add(ItemInner.asCheck2(104, r93, r106, LocaleController.getString(r123), r1.getBoolean("EnableReactionsStories", true)));
        this.items.add(ItemInner.asShadow(-1, null));
        goto L42
    L38:
        if (r1.getBoolean("EnableReactionsStoriesContacts", false) == false) goto L40;
        r123 = R.string.NotifyFromContacts;
        goto L41
    L40:
        r123 = R.string.NotifyFromEveryone;
        goto L41
    L31:
        if (r1.getBoolean("EnableReactionsMessagesContacts", false) == false) goto L33;
        r122 = R.string.NotifyFromContacts;
        goto L34
    L33:
        r122 = R.string.NotifyFromEveryone;
    L79:
        int r14 = this.currentType;
        if (r14 == 4) goto L110;
        if (r14 == 5) goto L110;
        if (r14 == (-1)) goto L84;
        this.addExceptionRow = this.items.size();
        this.items.add(ItemInner.asButton(6, R.drawable.msg_contact_add, LocaleController.getString("NotificationsAddAnException", R.string.NotificationsAddAnException)));
    L84:
        this.exceptionsStart = this.items.size() - 1;
        if (this.autoExceptions == null) goto L93;
        if (this.showAutoExceptions == false) goto L93;
        int r16 = 0;
    L90:
        if (r16 >= this.autoExceptions.size()) goto L93;
        this.items.add(ItemInner.asException(this.autoExceptions.get(r16)));
        r16 = r16 + 1;
    L93:
        if (this.exceptions == null) goto L98;
        int r17 = 0;
    L96:
        if (r17 >= this.exceptions.size()) goto L98;
        this.items.add(ItemInner.asException(this.exceptions.get(r17)));
        r17 = r17 + 1;
    L98:
        this.exceptionsEnd = this.items.size() - 1;
        if (this.currentType != (-1)) goto L104;
        ArrayList<NotificationsSettingsActivity.NotificationException> r0 = this.exceptions;
        if (r0 != null) goto L103;
    L105:
        ArrayList<NotificationsSettingsActivity.NotificationException> r02 = this.exceptions;
        if (r02 != null) goto L108;
    L111:
        ListAdapter r03 = this.adapter;
        if (r03 == null) goto L120;
        if (r15 == false) goto L116;
        r03.setItems(this.oldItems, this.items);
        return;
    L116:
        r03.notifyDataSetChanged();
        return;
    L120:
        return;
    L108:
        if (r02.isEmpty() == true) goto L111;
        this.deleteExceptionsRow = this.items.size();
        this.items.add(ItemInner.asButton(7, 0, LocaleController.getString("NotificationsDeleteAllException", R.string.NotificationsDeleteAllException)));
        goto L111
    L103:
        if (r0.isEmpty() == true) goto L105;
    L104:
        this.items.add(ItemInner.asShadow(-3, null));
    L110:
        this.exceptionsStart = -1;
        this.exceptionsEnd = -1;
        goto L111
    }
}
