package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.GroupCreateSectionCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UsersAlertBase;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.GroupCreateActivity;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class InviteMembersBottomSheet extends UsersAlertBase implements NotificationCenter.NotificationCenterDelegate {
    private int additionalHeight;
    private long chatId;
    private ArrayList<TLObject> contacts;
    private int contactsEndRow;
    private int contactsStartRow;
    private int copyLinkRow;
    private AnimatorSet currentAnimation;
    private GroupCreateSpan currentDeletingSpan;
    private GroupCreateActivity.ContactsAddActivityDelegate delegate;
    private InviteMembersBottomSheetDelegate dialogsDelegate;
    private ArrayList<TLRPC.Dialog> dialogsServerOnly;
    private int emptyRow;
    boolean enterEventSent;
    private final FragmentFloatingButton floatingButton;
    private LongSparseArray<TLObject> ignoreUsers;
    TLRPC.TL_chatInviteExported invite;
    private int lastRow;
    boolean linkGenerating;
    private int maxSize;
    private int noContactsStubRow;
    private BaseFragment parentFragment;
    private int rowCount;
    private int scrollViewH;
    private SearchAdapter searchAdapter;
    private int searchAdditionalHeight;
    private LongSparseArray<GroupCreateSpan> selectedContacts;
    private View.OnClickListener spanClickListener;
    private boolean spanEnter;
    private final SpansContainer spansContainer;
    private ValueAnimator spansEnterAnimator;
    private float spansEnterProgress;
    private final ScrollView spansScrollView;
    private float touchSlop;
    float y;

    /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ InviteMembersBottomSheet this$0;

        public AnonymousClass1(InviteMembersBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r4) {
            GroupCreateSpan r42 = (GroupCreateSpan) r4;
            boolean r0 = r42.isDeleting();
            InviteMembersBottomSheet r1 = this.this$0;
            if (r0 == false) goto L7;
            InviteMembersBottomSheet.m11110$$Nest$fputcurrentDeletingSpan(r1, null);
            InviteMembersBottomSheet.m11103$$Nest$fgetselectedContacts(this.this$0).remove(r42.getUid());
            InviteMembersBottomSheet.m11105$$Nest$fgetspansContainer(this.this$0).removeSpan(r42);
            InviteMembersBottomSheet.m11115$$Nest$mspansCountChanged(this.this$0, true);
            AndroidUtilities.updateVisibleRows(this.this$0.listView);
            return;
        L7:
            if (InviteMembersBottomSheet.m11091$$Nest$fgetcurrentDeletingSpan(r1) == null) goto L9;
            InviteMembersBottomSheet.m11091$$Nest$fgetcurrentDeletingSpan(this.this$0).cancelDeleteAnimation();
        L9:
            InviteMembersBottomSheet.m11110$$Nest$fputcurrentDeletingSpan(this.this$0, r42);
            r42.startDeleteAnimation();
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ScrollView {
        final /* synthetic */ InviteMembersBottomSheet this$0;

        public AnonymousClass2(InviteMembersBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = View.MeasureSpec.getSize(r3);
            int r42 = View.MeasureSpec.getSize(r4);
            if (AndroidUtilities.isTablet() == true) goto L7;
            if (r42 > r0) goto L7;
            InviteMembersBottomSheet.m11111$$Nest$fputmaxSize(this.this$0, AndroidUtilities.dp(56.0f));
        L8:
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(this.this$0), Integer.MIN_VALUE));
            return;
        L7:
            InviteMembersBottomSheet.m11111$$Nest$fputmaxSize(this.this$0, AndroidUtilities.dp(144.0f));
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ InviteMembersBottomSheet this$0;
        final /* synthetic */ boolean val$enter;

        public AnonymousClass3(InviteMembersBottomSheet r1, boolean r2) {
            this.this$0 = r1;
            this.val$enter = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            InviteMembersBottomSheet r22 = this.this$0;
            if (this.val$enter == false) goto L5;
            float r0 = 1.0f;
        L6:
            InviteMembersBottomSheet.m11114$$Nest$fputspansEnterProgress(r22, r0);
            InviteMembersBottomSheet.access$000(this.this$0).invalidate();
            if (this.val$enter == true) goto L10;
            InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0).setVisibility(8);
            return;
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends UsersAlertBase.ContainerView {
        float animateToEmptyViewOffset;
        float deltaOffset;
        float emptyViewOffset;
        Paint paint;
        final /* synthetic */ InviteMembersBottomSheet this$0;
        private VerticalPositionAutoAnimator verticalPositionAutoAnimator;

        public AnonymousClass4(InviteMembersBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.paint = new Paint();
        }

        @Override // org.telegram.ui.Components.UsersAlertBase.ContainerView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            InviteMembersBottomSheet r0 = this.this$0;
            int r1 = (r0.scrollOffsetY - InviteMembersBottomSheet.access$500(r0)) + AndroidUtilities.dp(6.0f);
            InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0).setTranslationY(r1 + AndroidUtilities.dp(64.0f));
            float r02 = InviteMembersBottomSheet.m11085$$Nest$fgetadditionalHeight(this.this$0) + InviteMembersBottomSheet.m11102$$Nest$fgetsearchAdditionalHeight(this.this$0);
            if (this.this$0.emptyView.getVisibility() == 0) goto L6;
            this.emptyViewOffset = r02;
            this.animateToEmptyViewOffset = r02;
        L8:
            float r03 = this.emptyViewOffset;
            float r12 = this.animateToEmptyViewOffset;
            if (r03 == r12) goto L21;
            float r2 = this.deltaOffset;
            float r04 = r03 + r2;
            this.emptyViewOffset = r04;
            if (r2 <= 0.0f) goto L16;
            if (r04 <= r12) goto L16;
            this.emptyViewOffset = r12;
        L16:
            if (r2 < 0.0f) goto L18;
        L20:
            invalidate();
            goto L21
        L18:
            if (r04 >= r12) goto L20;
            this.emptyViewOffset = r12;
        L21:
            this.this$0.emptyView.setTranslationY(r0.scrollOffsetY + this.emptyViewOffset);
            super.dispatchDraw(r6);
            return;
        L6:
            if (this.animateToEmptyViewOffset == r02) goto L8;
            this.animateToEmptyViewOffset = r02;
            this.deltaOffset = (r02 - this.emptyViewOffset) * 0.10666667f;
            goto L8
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r11, View r12, long r13) {
            if (r12 != InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0)) goto L7;
            r11.save();
            r11.clipRect(0.0f, r12.getY() - AndroidUtilities.dp(4.0f), getMeasuredWidth(), (r12.getY() + InviteMembersBottomSheet.m11101$$Nest$fgetscrollViewH(this.this$0)) + 1.0f);
            r11.drawColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), (int) (InviteMembersBottomSheet.m11106$$Nest$fgetspansEnterProgress(this.this$0) * 255.0f)));
            this.paint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_divider), (int) (InviteMembersBottomSheet.m11106$$Nest$fgetspansEnterProgress(this.this$0) * 255.0f)));
            r11.drawRect(0.0f, r12.getY() + InviteMembersBottomSheet.m11101$$Nest$fgetscrollViewH(this.this$0), getMeasuredWidth(), (r12.getY() + InviteMembersBottomSheet.m11101$$Nest$fgetscrollViewH(this.this$0)) + 1.0f, this.paint);
            boolean r10 = super.drawChild(r11, r12, r13);
            r11.restore();
            return r10;
        L7:
            return super.drawChild(r11, r12, r13);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            VerticalPositionAutoAnimator r0 = this.verticalPositionAutoAnimator;
            if (r0 == null) goto L6;
            r0.ignoreNextLayout();
            return;
        }

        @Override // android.view.ViewGroup
        public void onViewAdded(View r2) {
            if (r2 == InviteMembersBottomSheet.m11095$$Nest$fgetfloatingButton(this.this$0)) goto L5;
            return;
        L5:
            if (this.verticalPositionAutoAnimator != null) goto L9;
            this.verticalPositionAutoAnimator = VerticalPositionAutoAnimator.attach(r2);
            return;
        }
    }

    public interface InviteMembersBottomSheetDelegate {
        void didSelectDialogs(ArrayList<Long> r1);
    }

    public class ItemAnimator extends DefaultItemAnimator {
        final /* synthetic */ InviteMembersBottomSheet this$0;

        public ItemAnimator(InviteMembersBottomSheet r3) {
            this.this$0 = r3;
            this.translationInterpolator = CubicBezierInterpolator.DEFAULT;
            setMoveDuration(150);
            setAddDuration(150);
            setRemoveDuration(150);
            r3.setShowWithoutAnimation(false);
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ InviteMembersBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f) + InviteMembersBottomSheet.m11085$$Nest$fgetadditionalHeight(this.this$1.this$0), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends StickerEmptyView {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass2(ListAdapter r1, Context r2, View r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.StickerEmptyView, android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                this.stickerView.getImageReceiver().startAnimation();
            }
        }

        private ListAdapter(InviteMembersBottomSheet r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ListAdapter(InviteMembersBottomSheet r1, InviteMembersBottomSheetIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return InviteMembersBottomSheet.m11100$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != InviteMembersBottomSheet.m11089$$Nest$fgetcopyLinkRow(this.this$0)) goto L7;
            return 1;
        L7:
            if (r2 != InviteMembersBottomSheet.m11094$$Nest$fgetemptyRow(this.this$0)) goto L11;
            return 2;
        L11:
            if (r2 < InviteMembersBottomSheet.m11088$$Nest$fgetcontactsStartRow(this.this$0)) goto L17;
            if (r2 >= InviteMembersBottomSheet.m11087$$Nest$fgetcontactsEndRow(this.this$0)) goto L17;
            return 3;
        L17:
            if (r2 != InviteMembersBottomSheet.m11097$$Nest$fgetlastRow(this.this$0)) goto L21;
            return 4;
        L21:
            if (r2 != InviteMembersBottomSheet.m11099$$Nest$fgetnoContactsStubRow(this.this$0)) goto L24;
            return 5;
        L24:
            return 0;
        }

        public TLObject getObject(int r3) {
            InviteMembersBottomSheetDelegate r0 = InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(this.this$0);
            InviteMembersBottomSheet r1 = this.this$0;
            if (r0 == null) goto L11;
            TLRPC.Dialog r32 = (TLRPC.Dialog) InviteMembersBottomSheet.m11093$$Nest$fgetdialogsServerOnly(r1).get(r3 - InviteMembersBottomSheet.m11088$$Nest$fgetcontactsStartRow(this.this$0));
            boolean r02 = DialogObject.isUserDialog(r32.id);
            InviteMembersBottomSheet r2 = this.this$0;
            if (r02 == false) goto L9;
            return MessagesController.getInstance(InviteMembersBottomSheet.access$100(r2)).getUser(Long.valueOf(r32.id));
        L9:
            return MessagesController.getInstance(InviteMembersBottomSheet.access$200(r2)).getChat(Long.valueOf(-r32.id));
        L11:
            return (TLObject) InviteMembersBottomSheet.m11086$$Nest$fgetcontacts(r1).get(r3 - InviteMembersBottomSheet.m11088$$Nest$fgetcontactsStartRow(this.this$0));
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 3) goto L5;
        L9:
            return true;
        L5:
            if (r3.getItemViewType() == 1) goto L9;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            int r0 = r11.getItemViewType();
            if (r0 != 2) goto L5;
            r11.itemView.requestLayout();
            return;
        L5:
            if (r0 != 3) goto L45;
            GroupCreateUserCell r112 = (GroupCreateUserCell) r11.itemView;
            TLObject r02 = getObject(r12);
            Object r1 = r112.getObject();
            if ((r1 instanceof TLRPC.User) == false) goto L11;
            long r13 = ((TLRPC.User) r1).id;
        L14:
            boolean r6 = false;
            if (r12 == InviteMembersBottomSheet.m11087$$Nest$fgetcontactsEndRow(this.this$0)) goto L17;
            boolean r122 = true;
        L18:
            r112.setObject(r02, null, null, r122);
            if ((r02 instanceof TLRPC.User) == false) goto L22;
            long r8 = ((TLRPC.User) r02).id;
        L26:
            if (r8 != 0) goto L28;
            return;
        L28:
            if (InviteMembersBottomSheet.m11096$$Nest$fgetignoreUsers(this.this$0) == null) goto L34;
            if (InviteMembersBottomSheet.m11096$$Nest$fgetignoreUsers(this.this$0).indexOfKey(r8) < 0) goto L34;
            r112.setChecked(true, false);
            r112.setCheckBoxEnabled(false);
            return;
        L34:
            if (InviteMembersBottomSheet.m11103$$Nest$fgetselectedContacts(this.this$0).indexOfKey(r8) < 0) goto L36;
            boolean r10 = true;
        L38:
            if (r13 != r8) goto L40;
            r6 = true;
        L40:
            r112.setChecked(r10, r6);
            r112.setCheckBoxEnabled(true);
            return;
        L36:
            r10 = false;
            goto L38
        L22:
            if ((r02 instanceof TLRPC.Chat) == false) goto L24;
            r8 = -((TLRPC.Chat) r02).id;
            goto L26
        L24:
            r8 = 0;
            goto L26
        L17:
            r122 = false;
            goto L18
        L11:
            if ((r1 instanceof TLRPC.Chat) == false) goto L13;
            r13 = -((TLRPC.Chat) r1).id;
            goto L14
        L13:
            r13 = 0;
            goto L14
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r10, int r11) {
            Context r102 = r10.getContext();
            if (r11 != 2) goto L5;
            View r3 = new AnonymousClass1(this, r102);
        L24:
            return new RecyclerListView.Holder(r3);
        L5:
            if (r11 == 3) goto L18;
            if (r11 != 4) goto L9;
            r3 = new View(r102);
            goto L24
        L9:
            if (r11 == 5) goto L11;
            ManageChatTextCell r32 = new ManageChatTextCell(r102);
            r32.setText(LocaleController.getString(R.string.VoipGroupCopyInviteLink), null, R.drawable.msg_link, 7, true);
            int r9 = Theme.key_dialogTextBlue2;
            r32.setColors(r9, r9);
            r3 = r32;
            goto L24
        L11:
            AnonymousClass2 r33 = new AnonymousClass2(this, r102, null, 0);
            r33.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            r33.subtitle.setVisibility(8);
            InviteMembersBottomSheetDelegate r92 = InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(this.this$0);
            SpoilersTextView r103 = r33.title;
            if (r92 == null) goto L14;
            r103.setText(LocaleController.getString(R.string.FilterNoChats));
        L15:
            r33.setAnimateLayoutChange(true);
            r3 = r33;
            goto L24
        L14:
            r103.setText(LocaleController.getString(R.string.NoContacts));
            goto L15
        L18:
            if (InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(this.this$0) == null) goto L20;
            boolean r93 = true;
        L21:
            r3 = new GroupCreateUserCell(r102, 1, 0, r93);
            goto L24
        L20:
            r93 = false;
            goto L21
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private int currentItemsCount;
        private final SearchAdapterHelper searchAdapterHelper;
        private ArrayList<Object> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        final /* synthetic */ InviteMembersBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$SearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass1(SearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((AndroidUtilities.dp(48.0f) + InviteMembersBottomSheet.m11085$$Nest$fgetadditionalHeight(this.this$1.this$0)) + InviteMembersBottomSheet.m11102$$Nest$fgetsearchAdditionalHeight(this.this$1.this$0), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$2LiCvoCgUZwC0a3nurLV4yArlBM(SearchAdapter r0, ArrayList r1, ArrayList r2) {
            r0.lambda$updateSearchResults$1(r1, r2);
        }

        /* renamed from: $r8$lambda$Og7e_U9seICvtEzWmURCxp-61co, reason: not valid java name */
        public static /* synthetic */ void m11116$r8$lambda$Og7e_U9seICvtEzWmURCxp61co(SearchAdapter r0, String r1) {
            r0.lambda$searchDialogs$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$OngjiZ5bDBqFgszgjMlMy1gmRfo(SearchAdapter r0, String r1) {
            r0.lambda$searchDialogs$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$QP3pMDIa8hbiBB1_E7dv1RMddO8(SearchAdapter r0, int r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$oSs2hc4I-TlOmSTScx47FU-DDCY, reason: not valid java name */
        public static /* synthetic */ void m11117$r8$lambda$oSs2hc4ITlOmSTScx47FUDDCY(SearchAdapter r0, String r1) {
            r0.lambda$searchDialogs$3(r1);
        }

        /* renamed from: -$$Nest$fgetsearchAdapterHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchAdapterHelper m11118$$Nest$fgetsearchAdapterHelper(SearchAdapter r0) {
            return r0.searchAdapterHelper;
        }

        /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m11119$$Nest$fgetsearchResult(SearchAdapter r0) {
            return r0.searchResult;
        }

        public SearchAdapter(InviteMembersBottomSheet r2) {
            this.this$0 = r2;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            SearchAdapterHelper r22 = new SearchAdapterHelper(false);
            this.searchAdapterHelper = r22;
            r22.setDelegate(new InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$new$0(int r3) {
            this.this$0.showItemsAnimated(this.currentItemsCount - 1);
            if (this.searchRunnable == null) goto L5;
        L9:
            notifyDataSetChanged();
            return;
        L5:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L9;
            if (getItemCount() > 2) goto L9;
            this.this$0.emptyView.showProgress(false, true);
            goto L9
        }

        private /* synthetic */ void lambda$searchDialogs$2(String r18) {
            String r1 = r18.trim().toLowerCase();
            if (r1.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), new ArrayList());
            return;
        L6:
            String r2 = LocaleController.getInstance().getTranslitString(r1);
            if (r1.equals(r2) == false) goto L9;
        L10:
            r2 = null;
        L11:
            int r3 = 0;
            boolean r5 = true;
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
            ArrayList<CharSequence> r22 = new ArrayList();
            int r8 = 0;
        L20:
            if (r8 >= InviteMembersBottomSheet.m11086$$Nest$fgetcontacts(this.this$0).size()) goto L57;
            TLObject r9 = (TLObject) InviteMembersBottomSheet.m11086$$Nest$fgetcontacts(this.this$0).get(r8);
            boolean r10 = r9 instanceof TLRPC.User;
            if (r10 == false) goto L24;
            TLRPC.User r11 = (TLRPC.User) r9;
            String r122 = ContactsController.formatName(r11.first_name, r11.last_name).toLowerCase();
            String r112 = UserObject.getPublicUsername(r11);
        L25:
            String r13 = LocaleController.getInstance().getTranslitString(r122);
            if (r122.equals(r13) == false) goto L28;
            r13 = null;
        L28:
            int r14 = r3;
            int r15 = r14;
        L29:
            if (r14 >= r62) goto L55;
            String r32 = r7[r14];
            if (r122.startsWith(r32) == false) goto L33;
        L44:
            r15 = 1;
        L45:
            if (r15 != 0) goto L46;
            r14 = r14 + 1;
            r5 = true;
            goto L29
        L46:
            boolean r4 = true;
            if (r15 != 1) goto L52;
            if (r10 == false) goto L51;
            TLRPC.User r52 = (TLRPC.User) r9;
            r22.add(AndroidUtilities.generateSearchName(r52.first_name, r52.last_name, r32));
        L50:
            Object r102 = null;
        L53:
            r12.add(r9);
        L56:
            r8 = r8 + 1;
            r5 = r4;
            r3 = 0;
            goto L20
        L51:
            r22.add(AndroidUtilities.generateSearchName(((TLRPC.Chat) r9).title, null, r32));
            goto L50
        L52:
            r102 = null;
            r22.add(AndroidUtilities.generateSearchName("@" + r112, null, "@" + r32));
            goto L53
        L33:
            if (r122.contains(" " + r32) == true) goto L44;
            if (r13 != null) goto L36;
        L40:
            if (r112 == null) goto L45;
            if (r112.startsWith(r32) == false) goto L45;
            r15 = 2;
            goto L45
        L36:
            if (r13.startsWith(r32) == true) goto L44;
            if (r13.contains(" " + r32) == false) goto L40;
        L55:
            r4 = r5;
            goto L56
        L24:
            TLRPC.Chat r113 = (TLRPC.Chat) r9;
            r122 = r113.title;
            r112 = ChatObject.getPublicUsername(r113);
            goto L25
        L57:
            updateSearchResults(r12, r22);
            return;
        L14:
            r6 = 0;
            goto L15
        L9:
            if (r2.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$searchDialogs$3(String r13) {
            SearchAdapterHelper r0 = this.searchAdapterHelper;
            boolean r3 = true;
            if (InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(this.this$0) == null) goto L5;
            boolean r1 = true;
        L7:
            if (InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(this.this$0) == null) goto L9;
            boolean r5 = r1;
        L10:
            r0.queryServerSearch(r13, true, r3, true, r5, false, 0, false, 0, 0);
            DispatchQueue r132 = Utilities.searchQueue;
            InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda3 r02 = new InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda3(this, r13);
            this.searchRunnable = r02;
            r132.postRunnable(r02);
            return;
        L9:
            r5 = false;
            goto L10
        L5:
            r1 = true;
            r3 = false;
            goto L7
        }

        private /* synthetic */ void lambda$searchDialogs$4(String r2) {
            AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda2(this, r2));
        }

        private /* synthetic */ void lambda$updateSearchResults$1(ArrayList r2, ArrayList r3) {
            this.searchRunnable = null;
            this.searchResult = r2;
            this.searchResultNames = r3;
            this.searchAdapterHelper.mergeResults(r2);
            this.this$0.showItemsAnimated(this.currentItemsCount - 1);
            notifyDataSetChanged();
            if (this.searchAdapterHelper.isSearchInProgress() == false) goto L5;
            return;
        L5:
            if (getItemCount() > 2) goto L9;
            this.this$0.emptyView.showProgress(false, true);
            return;
        }

        private void updateSearchResults(ArrayList<Object> r2, ArrayList<CharSequence> r3) {
            AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda4(this, r2, r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.searchResult.size();
            int r1 = this.searchAdapterHelper.getLocalServerSearch().size();
            int r2 = this.searchAdapterHelper.getGlobalSearch().size();
            int r02 = r0 + r1;
            if (r2 == 0) goto L5;
            r02 = r02 + (r2 + 1);
        L5:
            int r03 = r02 + 2;
            this.currentItemsCount = r03;
            return r03;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != 0) goto L6;
            return 2;
        L6:
            if (r3 != (this.currentItemsCount - 1)) goto L10;
            return 4;
        L10:
            if ((r3 - 1) != (this.searchResult.size() + this.searchAdapterHelper.getLocalServerSearch().size())) goto L13;
            return 0;
        L13:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
            int r0 = r12.getItemViewType();
            if (r0 != 0) goto L5;
            ((GroupCreateSectionCell) r12.itemView).setText(LocaleController.getString(R.string.GlobalSearch));
            return;
        L5:
            if (r0 != 1) goto L7;
            GroupCreateUserCell r122 = (GroupCreateUserCell) r12.itemView;
            int r02 = this.searchResult.size();
            int r2 = this.searchAdapterHelper.getGlobalSearch().size();
            int r3 = this.searchAdapterHelper.getLocalServerSearch().size();
            int r132 = r13 - 1;
            CharSequence r5 = null;
            if (r132 < 0) goto L15;
            if (r132 >= r02) goto L15;
            TLObject r22 = (TLObject) this.searchResult.get(r132);
        L25:
            if (r22 != null) goto L27;
        L55:
            CharSequence r133 = null;
        L56:
            Object r03 = r122.getObject();
            if ((r03 instanceof TLRPC.User) == false) goto L60;
            long r32 = ((TLRPC.User) r03).id;
        L63:
            r122.setObject(r22, r5, r133);
            if ((r22 instanceof TLRPC.User) == false) goto L67;
            long r8 = ((TLRPC.User) r22).id;
        L71:
            if (r8 == 0) goto L94;
            boolean r04 = false;
            if (InviteMembersBottomSheet.m11096$$Nest$fgetignoreUsers(this.this$0) == null) goto L82;
            if (InviteMembersBottomSheet.m11096$$Nest$fgetignoreUsers(this.this$0).indexOfKey(r8) < 0) goto L82;
            if (r32 != r8) goto L79;
            boolean r11 = true;
        L80:
            r122.setChecked(true, r11);
            r122.setCheckBoxEnabled(false);
            return;
        L79:
            r11 = false;
        L82:
            if (InviteMembersBottomSheet.m11103$$Nest$fgetselectedContacts(this.this$0).indexOfKey(r8) < 0) goto L84;
            boolean r112 = true;
        L86:
            if (r32 != r8) goto L88;
            r04 = true;
        L88:
            r122.setChecked(r112, r04);
            r122.setCheckBoxEnabled(true);
            return;
        L84:
            r112 = false;
            goto L86
        L94:
            return;
        L67:
            if ((r22 instanceof TLRPC.Chat) == false) goto L69;
            r8 = -((TLRPC.Chat) r22).id;
            goto L71
        L69:
            r8 = 0;
            goto L71
        L60:
            if ((r03 instanceof TLRPC.Chat) == false) goto L62;
            r32 = -((TLRPC.Chat) r03).id;
            goto L63
        L62:
            r32 = 0;
            goto L63
        L27:
            if ((r22 instanceof TLRPC.User) == false) goto L29;
            String r33 = ((TLRPC.User) r22).username;
        L31:
            if (r132 >= r02) goto L40;
            r133 = this.searchResultNames.get(r132);
            if (r133 != null) goto L35;
        L39:
            r5 = r133;
            r133 = null;
            goto L56
        L35:
            if (TextUtils.isEmpty(r33) == true) goto L39;
            if (r133.toString().startsWith("@" + r33) == false) goto L39;
        L40:
            if (r132 <= r02) goto L55;
            if (TextUtils.isEmpty(r33) == true) goto L55;
            String r134 = this.searchAdapterHelper.getLastFoundUsername();
            if (r134.startsWith("@") == false) goto L92;
            r134 = r134.substring(1);
        L92:
            SpannableStringBuilder r05 = new SpannableStringBuilder();     // Catch: Exception -> L54
            r05.append("@");     // Catch: Exception -> L54
            r05.append(r33);     // Catch: Exception -> L54
            int r6 = AndroidUtilities.indexOfIgnoreCase(r33, r134);     // Catch: Exception -> L54
            if (r6 == (-1)) goto L53;
            int r135 = r134.length();     // Catch: Exception -> L54
            if (r6 != 0) goto L51;
            r135 = r135 + 1;     // Catch: Exception -> L54
        L52:
            r05.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)), r6, r135 + r6, 33);     // Catch: Exception -> L54
            goto L53
        L51:
            r6 = r6 + 1;     // Catch: Exception -> L54
        L53:
            r133 = r05;
        L54:
            r133 = r33;
            goto L56
        L29:
            r33 = ChatObject.getPublicUsername((TLRPC.Chat) r22);
        L15:
            if (r132 < r02) goto L20;
            if (r132 >= (r3 + r02)) goto L20;
            r22 = this.searchAdapterHelper.getLocalServerSearch().get(r132 - r02);
        L20:
            if (r132 > (r02 + r3)) goto L22;
        L24:
            r22 = null;
            goto L25
        L22:
            if (r132 > ((r2 + r02) + r3)) goto L24;
            r22 = this.searchAdapterHelper.getGlobalSearch().get(((r132 - r02) - r3) - 1);
            goto L25
        L7:
            if (r0 != 2) goto L96;
            r12.itemView.requestLayout();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            Context r22 = r2.getContext();
            if (r3 != 1) goto L5;
            View r1 = new GroupCreateUserCell(r22, 1, 0, false);
        L13:
            return new RecyclerListView.Holder(r1);
        L5:
            if (r3 != 2) goto L7;
            r1 = new AnonymousClass1(this, r22);
            goto L13
        L7:
            if (r3 == 4) goto L9;
            r1 = new GroupCreateSectionCell(r22);
            goto L13
        L9:
            r1 = new View(r22);
            goto L13
        }

        public void searchDialogs(String r15) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            this.searchResult.clear();
            this.searchResultNames.clear();
            this.searchAdapterHelper.mergeResults(null);
            this.searchAdapterHelper.queryServerSearch(null, true, false, false, false, false, 0, false, 0, 0);
            notifyDataSetChanged();
            boolean r0 = TextUtils.isEmpty(r15);
            InviteMembersBottomSheet r1 = this.this$0;
            if (r0 == true) goto L12;
            RecyclerView.Adapter r02 = r1.listView.getAdapter();
            InviteMembersBottomSheet r12 = this.this$0;
            RecyclerView.Adapter r2 = r12.searchListViewAdapter;
            if (r02 == r2) goto L10;
            r12.listView.setAdapter(r2);
        L10:
            this.this$0.emptyView.showProgress(true, false);
            DispatchQueue r03 = Utilities.searchQueue;
            InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda1 r13 = new InviteMembersBottomSheet$SearchAdapter$$ExternalSyntheticLambda1(this, r15);
            this.searchRunnable = r13;
            r03.postRunnable(r13, 300);
            return;
        L12:
            RecyclerView.Adapter r152 = r1.listView.getAdapter();
            InviteMembersBottomSheet r14 = this.this$0;
            RecyclerView.Adapter r04 = r14.listViewAdapter;
            if (r152 == r04) goto L16;
            r14.listView.setAdapter(r04);
            return;
        }
    }

    public class SpansContainer extends ViewGroup {
        boolean addAnimation;
        private int animationIndex;
        private boolean animationStarted;
        private ArrayList<Animator> animators;
        private View removingSpan;
        final /* synthetic */ InviteMembersBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$SpansContainer$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass1(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r1) {
                this.this$1.this$0.listView.updateSelector();
            }
        }

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$SpansContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass2(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                InviteMembersBottomSheet.m11109$$Nest$fputcurrentAnimation(this.this$1.this$0, null);
                this.this$1.requestLayout();
            }
        }

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$SpansContainer$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass3(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                InviteMembersBottomSheet.m11109$$Nest$fputcurrentAnimation(this.this$1.this$0, null);
                SpansContainer.m11121$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: org.telegram.ui.Components.InviteMembersBottomSheet$SpansContainer$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ GroupCreateSpan val$span;

            public AnonymousClass4(SpansContainer r1, GroupCreateSpan r2) {
                this.this$1 = r1;
                this.val$span = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.removeView(this.val$span);
                SpansContainer.m11122$$Nest$fputremovingSpan(this.this$1, null);
                InviteMembersBottomSheet.m11109$$Nest$fputcurrentAnimation(this.this$1.this$0, null);
                SpansContainer.m11121$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: $r8$lambda$EQducrjwTTBBY-vCp_EMjGXsIFA, reason: not valid java name */
        public static /* synthetic */ void m11120$r8$lambda$EQducrjwTTBBYvCp_EMjGXsIFA(SpansContainer r0, ValueAnimator r1) {
            r0.lambda$onMeasure$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$NUS06darmBvpJWHF8mHbwOpgcT8(SpansContainer r0, int r1) {
            r0.lambda$onMeasure$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$hCNRObdnBXX5Fty0KRhieaKceuA(SpansContainer r0, int r1) {
            r0.lambda$onMeasure$2(r1);
        }

        /* renamed from: -$$Nest$fputanimationStarted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11121$$Nest$fputanimationStarted(SpansContainer r0, boolean r1) {
            r0.animationStarted = r1;
        }

        /* renamed from: -$$Nest$fputremovingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11122$$Nest$fputremovingSpan(SpansContainer r0, View r1) {
            r0.removingSpan = r1;
        }

        public SpansContainer(InviteMembersBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.animators = new ArrayList();
            this.animationIndex = -1;
        }

        private /* synthetic */ void lambda$onMeasure$0(ValueAnimator r2) {
            InviteMembersBottomSheet.m11112$$Nest$fputscrollViewH(this.this$0, ((Integer) r2.getAnimatedValue()).intValue());
            InviteMembersBottomSheet.access$400(this.this$0).invalidate();
        }

        private /* synthetic */ void lambda$onMeasure$1(int r2) {
            InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0).smoothScrollTo(0, r2 - InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(this.this$0));
        }

        private /* synthetic */ void lambda$onMeasure$2(int r2) {
            InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0).smoothScrollTo(0, r2 - InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(this.this$0));
        }

        public void addSpan(GroupCreateSpan r4, boolean r5) {
            this.addAnimation = true;
            InviteMembersBottomSheet.m11103$$Nest$fgetselectedContacts(this.this$0).put(r4.getUid(), r4);
            if (InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0) == null) goto L5;
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).setupEndValues();
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).cancel();
        L5:
            this.animationStarted = false;
            if (r5 == false) goto L8;
            InviteMembersBottomSheet.m11109$$Nest$fputcurrentAnimation(this.this$0, new AnimatorSet());
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).addListener(new AnonymousClass3(this));
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).setDuration(150);
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.animators.clear();
            ArrayList<Animator> r52 = this.animators;
            Property r2 = View.SCALE_X;
            r52.add(ObjectAnimator.ofFloat(r4, r2, new float[]{0.01f, 1.0f}));
            ArrayList<Animator> r53 = this.animators;
            Property r22 = View.SCALE_Y;
            r53.add(ObjectAnimator.ofFloat(r4, r22, new float[]{0.01f, 1.0f}));
            ArrayList<Animator> r54 = this.animators;
            Property r1 = View.ALPHA;
            r54.add(ObjectAnimator.ofFloat(r4, r1, new float[]{0.0f, 1.0f}));
        L8:
            addView(r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r22 = getChildCount();
            int r42 = 0;
        L3:
            if (r42 >= r22) goto L5;
            View r52 = getChildAt(r42);
            r52.layout(0, 0, r52.getMeasuredWidth(), r52.getMeasuredHeight());
            r42 = r42 + 1;
            goto L3
        }

        @Override // android.view.View
        public void onMeasure(int r13, int r14) {
            int r142 = getChildCount();
            int r132 = View.MeasureSpec.getSize(r13);
            int r0 = r132 - AndroidUtilities.dp(26.0f);
            int r2 = AndroidUtilities.dp(10.0f);
            int r1 = AndroidUtilities.dp(10.0f);
            int r4 = 0;
            int r5 = 0;
            int r6 = 0;
        L4:
            if (r4 >= r142) goto L34;
            View r8 = getChildAt(r4);
            if ((r8 instanceof GroupCreateSpan) == false) goto L33;
            r8.measure(View.MeasureSpec.makeMeasureSpec(r132, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
            if (r8 == this.removingSpan) goto L14;
            if ((r8.getMeasuredWidth() + r5) <= r0) goto L14;
            r2 = r2 + (r8.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r5 = 0;
        L14:
            if ((r8.getMeasuredWidth() + r6) <= r0) goto L16;
            r1 = r1 + (r8.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r6 = 0;
        L16:
            int r10 = AndroidUtilities.dp(13.0f) + r5;
            if (this.animationStarted == true) goto L30;
            View r11 = this.removingSpan;
            if (r8 != r11) goto L21;
            r8.setTranslationX(AndroidUtilities.dp(13.0f) + r6);
            r8.setTranslationY(r1);
            goto L30
        L21:
            if (r11 == null) goto L28;
            float r102 = r10;
            if (r8.getTranslationX() == r102) goto L25;
            this.animators.add(ObjectAnimator.ofFloat(r8, View.TRANSLATION_X, new float[]{r102}));
        L25:
            float r103 = r2;
            if (r8.getTranslationY() == r103) goto L30;
            this.animators.add(ObjectAnimator.ofFloat(r8, View.TRANSLATION_Y, new float[]{r103}));
            goto L30
        L28:
            r8.setTranslationX(r10);
            r8.setTranslationY(r2);
        L30:
            if (r8 == this.removingSpan) goto L32;
            r5 = r5 + (r8.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L32:
            r6 = r6 + (r8.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L33:
            r4 = r4 + 1;
            goto L4
        L34:
            int r12 = r1 + AndroidUtilities.dp(42.0f);
            int r22 = r2 + AndroidUtilities.dp(42.0f);
            InviteMembersBottomSheetDelegate r143 = InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(this.this$0);
            InviteMembersBottomSheet r02 = this.this$0;
            if (r143 != null) goto L37;
            int r144 = Math.max(0, Math.min(InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(r02), r22) - AndroidUtilities.dp(52.0f));
        L41:
            int r03 = InviteMembersBottomSheet.m11102$$Nest$fgetsearchAdditionalHeight(this.this$0);
            InviteMembersBottomSheet r42 = this.this$0;
            if (InviteMembersBottomSheet.m11092$$Nest$fgetdialogsDelegate(r42) == null) goto L44;
        L46:
            int r52 = 0;
        L47:
            InviteMembersBottomSheet.m11113$$Nest$fputsearchAdditionalHeight(r42, r52);
            if (r144 == InviteMembersBottomSheet.m11085$$Nest$fgetadditionalHeight(this.this$0)) goto L50;
        L51:
            InviteMembersBottomSheet.m11108$$Nest$fputadditionalHeight(this.this$0, r144);
            if (this.this$0.listView.getAdapter() != null) goto L54;
        L60:
            int r145 = Math.min(InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(this.this$0), r22);
            if (InviteMembersBottomSheet.m11101$$Nest$fgetscrollViewH(this.this$0) == r145) goto L64;
            ValueAnimator r04 = ValueAnimator.ofInt(new int[]{InviteMembersBottomSheet.m11101$$Nest$fgetscrollViewH(this.this$0), r145});
            r04.addUpdateListener(new InviteMembersBottomSheet$SpansContainer$$ExternalSyntheticLambda0(this));
            this.animators.add(r04);
        L64:
            if (this.addAnimation == false) goto L69;
            if (r22 <= InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(this.this$0)) goto L69;
            AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$SpansContainer$$ExternalSyntheticLambda1(this, r22));
        L74:
            if (this.animationStarted == true) goto L79;
            if (InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0) == null) goto L79;
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).playTogether(this.animators);
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).addListener(new AnonymousClass2(this));
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).start();
            this.animationStarted = true;
        L79:
            if (InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0) != null) goto L81;
            InviteMembersBottomSheet.m11112$$Nest$fputscrollViewH(this.this$0, r145);
            InviteMembersBottomSheet.access$300(this.this$0).invalidate();
        L81:
            setMeasuredDimension(r132, Math.max(r22, r12));
            this.this$0.listView.setTranslationY(0.0f);
            return;
        L69:
            if (this.addAnimation == true) goto L74;
            if ((InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0).getScrollY() + InviteMembersBottomSheet.m11107$$Nest$fgetspansScrollView(this.this$0).getMeasuredHeight()) <= r22) goto L74;
            AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$SpansContainer$$ExternalSyntheticLambda2(this, r22));
            goto L74
        L54:
            if (this.this$0.listView.getAdapter().getItemCount() <= 0) goto L60;
            RecyclerView.ViewHolder r146 = this.this$0.listView.findViewHolderForAdapterPosition(0);
            if (r146 == null) goto L60;
            this.this$0.listView.getAdapter().notifyItemChanged(0);
            this.this$0.layoutManager.scrollToPositionWithOffset(0, r146.itemView.getTop() - this.this$0.listView.getPaddingTop());
            if (this.this$0.listView.getItemAnimator() == null) goto L60;
            ValueAnimator r147 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r147.addUpdateListener(new AnonymousClass1(this));
            r147.setDuration(this.this$0.listView.getItemAnimator().getChangeDuration()).start();
            goto L60
        L50:
            if (r03 == InviteMembersBottomSheet.m11102$$Nest$fgetsearchAdditionalHeight(this.this$0)) goto L60;
        L44:
            if (InviteMembersBottomSheet.m11103$$Nest$fgetselectedContacts(this.this$0).size() <= 0) goto L46;
            r52 = AndroidUtilities.dp(56.0f);
            goto L47
        L37:
            if (InviteMembersBottomSheet.m11104$$Nest$fgetspanEnter(r02) == false) goto L39;
            r144 = Math.min(InviteMembersBottomSheet.m11098$$Nest$fgetmaxSize(this.this$0), r22);
            goto L41
        L39:
            r144 = 0;
            goto L41
        }

        public void removeSpan(GroupCreateSpan r5) {
            this.addAnimation = false;
            InviteMembersBottomSheet.m11103$$Nest$fgetselectedContacts(this.this$0).remove(r5.getUid());
            r5.setOnClickListener(null);
            if (InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0) == null) goto L5;
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).setupEndValues();
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).cancel();
        L5:
            this.animationStarted = false;
            InviteMembersBottomSheet.m11109$$Nest$fputcurrentAnimation(this.this$0, new AnimatorSet());
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).addListener(new AnonymousClass4(this, r5));
            InviteMembersBottomSheet.m11090$$Nest$fgetcurrentAnimation(this.this$0).setDuration(150);
            this.removingSpan = r5;
            this.animators.clear();
            ArrayList<Animator> r52 = this.animators;
            Property r3 = View.SCALE_X;
            r52.add(ObjectAnimator.ofFloat(this.removingSpan, r3, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r53 = this.animators;
            Property r32 = View.SCALE_Y;
            r53.add(ObjectAnimator.ofFloat(this.removingSpan, r32, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r54 = this.animators;
            Property r2 = View.ALPHA;
            r54.add(ObjectAnimator.ofFloat(this.removingSpan, r2, new float[]{1.0f, 0.0f}));
            requestLayout();
        }
    }

    public static /* synthetic */ void $r8$lambda$6jh1_WG5kvvKNh8h8lqhAaaXHH4(InviteMembersBottomSheet r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$generateLink$7(r1, r2);
    }

    /* renamed from: $r8$lambda$Fb1-t6to_K_YYV62DgWQEE27SOg, reason: not valid java name */
    public static /* synthetic */ void m11082$r8$lambda$Fb1t6to_K_YYV62DgWQEE27SOg(InviteMembersBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$generateLink$8(r1, r2);
    }

    /* renamed from: $r8$lambda$LA0mzCWWlUH7nYB-TdbXowByPDM, reason: not valid java name */
    public static /* synthetic */ void m11083$r8$lambda$LA0mzCWWlUH7nYBTdbXowByPDM(InviteMembersBottomSheet r0, EditTextBoldCursor r1) {
        r0.lambda$onSearchViewTouched$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$UQuUozLWrOjvQRLUEMFnvyZQOrU(InviteMembersBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZYXyfT3W6HQA1ow5VuEhCo_Uw6A(InviteMembersBottomSheet r0, ValueAnimator r1) {
        r0.lambda$spansCountChanged$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$csXRrtmYzAZkjWQ7kX3IOd0WBGw(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$dsXwWPemcmdmI3oksW5D41WsLTM(InviteMembersBottomSheet r0, long r1, BaseFragment r3, LongSparseArray r4, Context r5, View r6, int r7) {
        r0.lambda$new$0(r1, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$mdvIBFi_opMN5ncRNyrix2mdIUI(InviteMembersBottomSheet r0, Context r1, long r2, View r4) {
        r0.lambda$new$2(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$vjJdXhJsr29dh-9ohA-1a_tguyY, reason: not valid java name */
    public static /* synthetic */ void m11084$r8$lambda$vjJdXhJsr29dh9ohA1a_tguyY(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: -$$Nest$fgetadditionalHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11085$$Nest$fgetadditionalHeight(InviteMembersBottomSheet r0) {
        return r0.additionalHeight;
    }

    /* renamed from: -$$Nest$fgetcontacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11086$$Nest$fgetcontacts(InviteMembersBottomSheet r0) {
        return r0.contacts;
    }

    /* renamed from: -$$Nest$fgetcontactsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11087$$Nest$fgetcontactsEndRow(InviteMembersBottomSheet r0) {
        return r0.contactsEndRow;
    }

    /* renamed from: -$$Nest$fgetcontactsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11088$$Nest$fgetcontactsStartRow(InviteMembersBottomSheet r0) {
        return r0.contactsStartRow;
    }

    /* renamed from: -$$Nest$fgetcopyLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11089$$Nest$fgetcopyLinkRow(InviteMembersBottomSheet r0) {
        return r0.copyLinkRow;
    }

    /* renamed from: -$$Nest$fgetcurrentAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m11090$$Nest$fgetcurrentAnimation(InviteMembersBottomSheet r0) {
        return r0.currentAnimation;
    }

    /* renamed from: -$$Nest$fgetcurrentDeletingSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateSpan m11091$$Nest$fgetcurrentDeletingSpan(InviteMembersBottomSheet r0) {
        return r0.currentDeletingSpan;
    }

    /* renamed from: -$$Nest$fgetdialogsDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InviteMembersBottomSheetDelegate m11092$$Nest$fgetdialogsDelegate(InviteMembersBottomSheet r0) {
        return r0.dialogsDelegate;
    }

    /* renamed from: -$$Nest$fgetdialogsServerOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11093$$Nest$fgetdialogsServerOnly(InviteMembersBottomSheet r0) {
        return r0.dialogsServerOnly;
    }

    /* renamed from: -$$Nest$fgetemptyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11094$$Nest$fgetemptyRow(InviteMembersBottomSheet r0) {
        return r0.emptyRow;
    }

    /* renamed from: -$$Nest$fgetfloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentFloatingButton m11095$$Nest$fgetfloatingButton(InviteMembersBottomSheet r0) {
        return r0.floatingButton;
    }

    /* renamed from: -$$Nest$fgetignoreUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m11096$$Nest$fgetignoreUsers(InviteMembersBottomSheet r0) {
        return r0.ignoreUsers;
    }

    /* renamed from: -$$Nest$fgetlastRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11097$$Nest$fgetlastRow(InviteMembersBottomSheet r0) {
        return r0.lastRow;
    }

    /* renamed from: -$$Nest$fgetmaxSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11098$$Nest$fgetmaxSize(InviteMembersBottomSheet r0) {
        return r0.maxSize;
    }

    /* renamed from: -$$Nest$fgetnoContactsStubRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11099$$Nest$fgetnoContactsStubRow(InviteMembersBottomSheet r0) {
        return r0.noContactsStubRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11100$$Nest$fgetrowCount(InviteMembersBottomSheet r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetscrollViewH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11101$$Nest$fgetscrollViewH(InviteMembersBottomSheet r0) {
        return r0.scrollViewH;
    }

    /* renamed from: -$$Nest$fgetsearchAdditionalHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11102$$Nest$fgetsearchAdditionalHeight(InviteMembersBottomSheet r0) {
        return r0.searchAdditionalHeight;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m11103$$Nest$fgetselectedContacts(InviteMembersBottomSheet r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$fgetspanEnter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11104$$Nest$fgetspanEnter(InviteMembersBottomSheet r0) {
        return r0.spanEnter;
    }

    /* renamed from: -$$Nest$fgetspansContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpansContainer m11105$$Nest$fgetspansContainer(InviteMembersBottomSheet r0) {
        return r0.spansContainer;
    }

    /* renamed from: -$$Nest$fgetspansEnterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11106$$Nest$fgetspansEnterProgress(InviteMembersBottomSheet r0) {
        return r0.spansEnterProgress;
    }

    /* renamed from: -$$Nest$fgetspansScrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollView m11107$$Nest$fgetspansScrollView(InviteMembersBottomSheet r0) {
        return r0.spansScrollView;
    }

    /* renamed from: -$$Nest$fputadditionalHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11108$$Nest$fputadditionalHeight(InviteMembersBottomSheet r0, int r1) {
        r0.additionalHeight = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11109$$Nest$fputcurrentAnimation(InviteMembersBottomSheet r0, AnimatorSet r1) {
        r0.currentAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcurrentDeletingSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11110$$Nest$fputcurrentDeletingSpan(InviteMembersBottomSheet r0, GroupCreateSpan r1) {
        r0.currentDeletingSpan = r1;
    }

    /* renamed from: -$$Nest$fputmaxSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11111$$Nest$fputmaxSize(InviteMembersBottomSheet r0, int r1) {
        r0.maxSize = r1;
    }

    /* renamed from: -$$Nest$fputscrollViewH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11112$$Nest$fputscrollViewH(InviteMembersBottomSheet r0, int r1) {
        r0.scrollViewH = r1;
    }

    /* renamed from: -$$Nest$fputsearchAdditionalHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11113$$Nest$fputsearchAdditionalHeight(InviteMembersBottomSheet r0, int r1) {
        r0.searchAdditionalHeight = r1;
    }

    /* renamed from: -$$Nest$fputspansEnterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11114$$Nest$fputspansEnterProgress(InviteMembersBottomSheet r0, float r1) {
        r0.spansEnterProgress = r1;
    }

    /* renamed from: -$$Nest$mspansCountChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11115$$Nest$mspansCountChanged(InviteMembersBottomSheet r0, boolean r1) {
        r0.spansCountChanged(r1);
    }

    public InviteMembersBottomSheet(Context r13, int r14, LongSparseArray<TLObject> r15, long r16, BaseFragment r18, Theme.ResourcesProvider r19) {
        super(r13, false, r14, r19);
        this.contacts = new ArrayList();
        this.selectedContacts = new LongSparseArray();
        this.spansEnterProgress = 0.0f;
        this.spanClickListener = new AnonymousClass1(this);
        this.ignoreUsers = r15;
        this.needSnapToTop = false;
        this.parentFragment = r18;
        this.chatId = r16;
        fixNavigationBar();
        this.searchView.searchEditText.setHint(LocaleController.getString(R.string.SearchForChats));
        this.touchSlop = ViewConfiguration.get(r13).getScaledTouchSlop();
        SearchAdapter r1 = new SearchAdapter(this);
        this.searchAdapter = r1;
        this.searchListViewAdapter = r1;
        RecyclerListView r12 = this.listView;
        ListAdapter r6 = new ListAdapter(this, null);
        this.listViewAdapter = r6;
        r12.setAdapter(r6);
        this.emptyView.showProgress(false, false);
        this.emptyView.setVisibility(8);
        ArrayList<TLRPC.TL_contact> r0 = ContactsController.getInstance(r14).contacts;
        int r17 = 0;
    L4:
        if (r17 >= r0.size()) goto L14;
        TLRPC.User r62 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r0.get(r17).user_id));
        if (r62 == null) goto L13;
        if (r62.self == true) goto L13;
        if (r62.deleted == true) goto L13;
        this.contacts.add(r62);
    L13:
        r17 = r17 + 1;
        goto L4
    L14:
        SpansContainer r10 = new SpansContainer(this, r13);
        this.spansContainer = r10;
        this.listView.setOnItemClickListener(new InviteMembersBottomSheet$$ExternalSyntheticLambda2(this, r16, r18, r15, r13));
        this.listView.setItemAnimator(new ItemAnimator(this));
        updateRows();
        AnonymousClass2 r02 = new AnonymousClass2(this, r13);
        this.spansScrollView = r02;
        r02.setVisibility(8);
        r02.setClipChildren(false);
        r02.addView(r10);
        this.containerView.addView(r02);
        FragmentFloatingButton r03 = new FragmentFloatingButton(r13, r19);
        this.floatingButton = r03;
        r03.setImageResource(R.drawable.floating_check);
        r03.setOnClickListener(new InviteMembersBottomSheet$$ExternalSyntheticLambda3(this, r13, r16));
        r03.setButtonVisible(false, false);
        r03.setContentDescription(LocaleController.getString(R.string.Next));
        this.containerView.addView(r03, FragmentFloatingButton.createDefaultLayoutParams());
        ((ViewGroup.MarginLayoutParams) this.emptyView.getLayoutParams()).topMargin = AndroidUtilities.dp(20.0f);
        ((ViewGroup.MarginLayoutParams) this.emptyView.getLayoutParams()).leftMargin = AndroidUtilities.dp(4.0f);
        ((ViewGroup.MarginLayoutParams) this.emptyView.getLayoutParams()).rightMargin = AndroidUtilities.dp(4.0f);
    }

    public static /* synthetic */ ViewGroup access$000(InviteMembersBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$100(InviteMembersBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(InviteMembersBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$300(InviteMembersBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$400(InviteMembersBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$500(InviteMembersBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    private void generateLink() {
        if (this.linkGenerating == false) goto L5;
        return;
    L5:
        this.linkGenerating = true;
        TLRPC.TL_messages_exportChatInvite r1 = new TLRPC.TL_messages_exportChatInvite();
        r1.legacy_revoke_permanent = true;
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(-this.chatId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new InviteMembersBottomSheet$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$generateLink$7(TLRPC.TL_error r3, TLObject r4) {
        if (r3 != null) goto L10;
        this.invite = (TLRPC.TL_chatInviteExported) r4;
        TLRPC.ChatFull r32 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chatId);
        if (r32 == null) goto L7;
        r32.exported_invite = this.invite;
    L7:
        if (this.invite.link != null) goto L9;
        return;
    L9:
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.invite.link));
        BulletinFactory.createCopyLinkBulletin(this.parentFragment).show();
        dismiss();
    L10:
        this.linkGenerating = false;
    }

    private /* synthetic */ void lambda$generateLink$8(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$$ExternalSyntheticLambda8(this, r3, r2));
    }

    private /* synthetic */ void lambda$new$0(long r4, BaseFragment r6, LongSparseArray r7, Context r8, View r9, int r10) {
        RecyclerView.Adapter r92 = this.listView.getAdapter();
        SearchAdapter r0 = this.searchAdapter;
        TLObject r2 = null;
        if (r92 != r0) goto L21;
        int r42 = SearchAdapter.m11119$$Nest$fgetsearchResult(r0).size();
        int r5 = SearchAdapter.m11118$$Nest$fgetsearchAdapterHelper(this.searchAdapter).getGlobalSearch().size();
        int r62 = SearchAdapter.m11118$$Nest$fgetsearchAdapterHelper(this.searchAdapter).getLocalServerSearch().size();
        int r102 = r10 - 1;
        if (r102 < 0) goto L8;
        if (r102 >= r42) goto L8;
        r2 = (TLObject) SearchAdapter.m11119$$Nest$fgetsearchResult(this.searchAdapter).get(r102);
    L18:
        if (this.dialogsDelegate == null) goto L40;
        this.searchView.closeSearch();
    L40:
        if (r2 != null) goto L42;
        return;
    L42:
        if ((r2 instanceof TLRPC.User) == false) goto L45;
        long r93 = ((TLRPC.User) r2).id;
    L48:
        if (r7 == null) goto L53;
        if (r7.indexOfKey(r93) < 0) goto L53;
        return;
    L53:
        if (r93 != 0) goto L55;
    L58:
        spansCountChanged(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L55:
        if (this.selectedContacts.indexOfKey(r93) < 0) goto L57;
        GroupCreateSpan r43 = this.selectedContacts.get(r93);
        this.selectedContacts.remove(r93);
        this.spansContainer.removeSpan(r43);
        goto L58
    L57:
        GroupCreateSpan r44 = new GroupCreateSpan(r8, r2);
        r44.setOnClickListener(this.spanClickListener);
        this.selectedContacts.put(r93, r44);
        this.spansContainer.addSpan(r44, true);
        goto L58
    L45:
        if ((r2 instanceof TLRPC.Chat) == false) goto L47;
        r93 = -((TLRPC.Chat) r2).id;
        goto L48
    L47:
        r93 = 0;
    L8:
        if (r102 < r42) goto L13;
        if (r102 >= (r62 + r42)) goto L13;
        r2 = SearchAdapter.m11118$$Nest$fgetsearchAdapterHelper(this.searchAdapter).getLocalServerSearch().get(r102 - r42);
    L13:
        if (r102 <= (r42 + r62)) goto L18;
        if (r102 > ((r5 + r42) + r62)) goto L18;
        r2 = SearchAdapter.m11118$$Nest$fgetsearchAdapterHelper(this.searchAdapter).getGlobalSearch().get(((r102 - r42) - r62) - 1);
        goto L18
    L21:
        if (r10 != this.copyLinkRow) goto L36;
        TLRPC.Chat r94 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r4));
        TLRPC.ChatFull r45 = MessagesController.getInstance(this.currentAccount).getChatFull(r4);
        if (r94 == null) goto L27;
        String r52 = ChatObject.getPublicUsername(r94);
        if (TextUtils.isEmpty(r52) == true) goto L27;
        String r46 = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r52;
    L32:
        if (r46 == null) goto L61;
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", r46));
        dismiss();
        BulletinFactory.createCopyLinkBulletin(r6).show();
        goto L40
    L61:
        return;
    L27:
        if (r45 == null) goto L31;
        TLRPC.TL_chatInviteExported r47 = r45.exported_invite;
        if (r47 == null) goto L31;
        r46 = r47.link;
    L31:
        generateLink();
        r46 = null;
        goto L32
    L36:
        if (r10 < this.contactsStartRow) goto L40;
        if (r10 >= this.contactsEndRow) goto L40;
        r2 = ((ListAdapter) this.listViewAdapter).getObject(r10);
        goto L40
    }

    private /* synthetic */ void lambda$new$1(AlertDialog r1, int r2) {
        onAddToGroupDone(0);
    }

    private /* synthetic */ void lambda$new$2(Context r5, long r6, View r8) {
        if (this.dialogsDelegate == null) goto L5;
    L7:
        Activity r52 = AndroidUtilities.findActivity(r5);
        if (r52 != null) goto L10;
        return;
    L10:
        int r0 = 0;
        if (this.dialogsDelegate == null) goto L18;
        ArrayList<Long> r53 = new ArrayList();
    L14:
        if (r0 >= this.selectedContacts.size()) goto L16;
        r53.add(Long.valueOf(this.selectedContacts.keyAt(r0)));
        r0 = r0 + 1;
        goto L14
    L16:
        this.dialogsDelegate.didSelectDialogs(r53);
        dismiss();
        return;
    L18:
        AlertDialog.Builder r82 = new AlertDialog.Builder(r52);
        r82.setTitle(LocaleController.formatPluralString("AddManyMembersAlertTitle", this.selectedContacts.size(), new Object[0]));
        StringBuilder r54 = new StringBuilder();
    L20:
        if (r0 >= this.selectedContacts.size()) goto L29;
        long r1 = this.selectedContacts.keyAt(r0);
        TLRPC.User r12 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1));
        if (r12 == null) goto L28;
        if (r54.length() <= 0) goto L27;
        r54.append(", ");
    L27:
        r54.append("**");
        r54.append(ContactsController.formatName(r12.first_name, r12.last_name));
        r54.append("**");
    L28:
        r0 = r0 + 1;
        goto L20
    L29:
        TLRPC.Chat r62 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r6));
        if (this.selectedContacts.size() <= 5) goto L35;
        SpannableStringBuilder r55 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatPluralString("AddManyMembersAlertNamesText", this.selectedContacts.size(), new Object[]{r62.title})));
        String r63 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.selectedContacts.size())});
        int r7 = TextUtils.indexOf(r55, r63);
        if (r7 < 0) goto L34;
        r55.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r7, r63.length() + r7, 33);
    L34:
        r82.setMessage(r55);
    L36:
        r82.setPositiveButton(LocaleController.getString(R.string.Add), new InviteMembersBottomSheet$$ExternalSyntheticLambda5(this));
        r82.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r82.create();
        r82.show();
        return;
    L35:
        r82.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("AddMembersAlertNamesText", R.string.AddMembersAlertNamesText, new Object[]{r54, r62.title})));
        goto L36
    L5:
        if (this.selectedContacts.isEmpty() == false) goto L7;
    }

    private /* synthetic */ void lambda$onSearchViewTouched$5(EditTextBoldCursor r2) {
        setFocusable(true);
        r2.requestFocus();
        AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$$ExternalSyntheticLambda6(r2));
    }

    private /* synthetic */ void lambda$spansCountChanged$3(ValueAnimator r1) {
        this.spansEnterProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.containerView.invalidate();
    }

    private void onAddToGroupDone(int r6) {
        ArrayList<TLRPC.User> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.selectedContacts.size()) goto L6;
        r0.add(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.selectedContacts.keyAt(r1))));
        r1 = r1 + 1;
        goto L4
    L6:
        GroupCreateActivity.ContactsAddActivityDelegate r12 = this.delegate;
        if (r12 == null) goto L9;
        r12.didSelectUsers(r0, r6);
    L9:
        dismiss();
    }

    private void spansCountChanged(boolean r7) {
        boolean r1 = true;
        if (this.selectedContacts.size() <= 0) goto L5;
        boolean r0 = true;
    L7:
        if (this.spanEnter == r0) goto L33;
        ValueAnimator r3 = this.spansEnterAnimator;
        if (r3 == null) goto L11;
        r3.removeAllListeners();
        this.spansEnterAnimator.cancel();
    L11:
        this.spanEnter = r0;
        if (r0 == false) goto L14;
        this.spansScrollView.setVisibility(0);
    L14:
        float r32 = 0.0f;
        if (r7 == false) goto L20;
        float r5 = this.spansEnterProgress;
        if (r0 == false) goto L19;
        r32 = 1.0f;
    L19:
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{r5, r32});
        this.spansEnterAnimator = r33;
        r33.addUpdateListener(new InviteMembersBottomSheet$$ExternalSyntheticLambda4(this));
        this.spansEnterAnimator.addListener(new AnonymousClass3(this, r0));
        this.spansEnterAnimator.setDuration(150);
        this.spansEnterAnimator.start();
    L25:
        FragmentFloatingButton r02 = this.floatingButton;
        if (this.spanEnter == false) goto L28;
    L31:
        r02.setButtonVisible(r1, r7);
        return;
    L28:
        if (this.dialogsDelegate != null) goto L31;
        r1 = false;
        goto L31
    L20:
        if (r0 == false) goto L22;
        r32 = 1.0f;
    L22:
        this.spansEnterProgress = r32;
        this.containerView.invalidate();
        if (r0 == true) goto L25;
        this.spansScrollView.setVisibility(8);
        goto L25
    L33:
        return;
    L5:
        r0 = false;
        goto L7
    }

    private void updateRows() {
        this.contactsStartRow = -1;
        this.contactsEndRow = -1;
        this.copyLinkRow = -1;
        this.noContactsStubRow = -1;
        this.rowCount = 1;
        this.emptyRow = 0;
        if (this.dialogsDelegate == null) goto L5;
        int r0 = this.dialogsServerOnly.size();
        int r1 = this.rowCount;
        if (r0 == 0) goto L14;
        this.contactsStartRow = r1;
        int r12 = r1 + this.dialogsServerOnly.size();
        this.rowCount = r12;
        this.contactsEndRow = r12;
    L15:
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.lastRow = r02;
        return;
    L14:
        this.rowCount = r1 + 1;
        this.noContactsStubRow = r1;
        goto L15
    L5:
        if (hasLink() == false) goto L7;
        int r03 = this.rowCount;
        this.rowCount = r03 + 1;
        this.copyLinkRow = r03;
    L7:
        int r04 = this.contacts.size();
        int r13 = this.rowCount;
        if (r04 == 0) goto L10;
        this.contactsStartRow = r13;
        int r14 = r13 + this.contacts.size();
        this.rowCount = r14;
        this.contactsEndRow = r14;
        goto L15
    L10:
        this.rowCount = r13 + 1;
        this.noContactsStubRow = r13;
        goto L15
    }

    public boolean canGenerateLink() {
        return true;
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public UsersAlertBase.ContainerView createContainerView(Context r2) {
        return new AnonymousClass4(this, r2);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.dialogsNeedReload) goto L5;
        return;
    L5:
        if (this.dialogsDelegate != null) goto L7;
        return;
    L7:
        if (this.dialogsServerOnly.isEmpty() == false) goto L12;
        this.dialogsServerOnly = new ArrayList(MessagesController.getInstance(this.currentAccount).dialogsServerOnly);
        this.listViewAdapter.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.Components.UsersAlertBase, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogsNeedReload);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        if (this.enterEventSent == false) goto L10;
        Activity r2 = AndroidUtilities.findActivity(getContext());
        if ((r2 instanceof LaunchActivity) == false) goto L11;
        LaunchActivity r22 = (LaunchActivity) r2;
        BaseFragment r23 = r22.getActionBarLayout().getFragmentStack().get(r22.getActionBarLayout().getFragmentStack().size() - 1);
        if ((r23 instanceof ChatActivity) == false) goto L12;
        ((ChatActivity) r23).onEditTextDialogClose(true, true);
        return;
    L12:
        return;
    L11:
        return;
    }

    public boolean hasLink() {
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chatId));
        TLRPC.ChatFull r1 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chatId);
        if (r0 != null) goto L5;
    L6:
        if (r1 == null) goto L12;
        if (r1.exported_invite == null) goto L12;
        return true;
    L12:
        return canGenerateLink();
    L5:
        if (TextUtils.isEmpty(ChatObject.getPublicUsername(r0)) == true) goto L6;
        return true;
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void onSearchViewTouched(MotionEvent r3, EditTextBoldCursor r4) {
        if (r3.getAction() != 0) goto L7;
        this.y = this.scrollOffsetY;
        return;
    L7:
        if (r3.getAction() == 1) goto L9;
        return;
    L9:
        if (Math.abs(this.scrollOffsetY - this.y) < this.touchSlop) goto L11;
        return;
    L11:
        if (this.enterEventSent == true) goto L28;
        Activity r32 = AndroidUtilities.findActivity(getContext());
        if ((r32 instanceof LaunchActivity) == false) goto L15;
        LaunchActivity r33 = (LaunchActivity) r32;
        BaseFragment r34 = r33.getActionBarLayout().getFragmentStack().get(r33.getActionBarLayout().getFragmentStack().size() - 1);
    L17:
        if ((r34 instanceof ChatActivity) == false) goto L24;
        boolean r35 = ((ChatActivity) r34).needEnterText();
        this.enterEventSent = true;
        InviteMembersBottomSheet$$ExternalSyntheticLambda0 r0 = new InviteMembersBottomSheet$$ExternalSyntheticLambda0(this, r4);
        if (r35 == false) goto L21;
        long r2 = 200;
    L22:
        AndroidUtilities.runOnUIThread(r0, r2);
        return;
    L21:
        r2 = 0;
        goto L22
    L24:
        this.enterEventSent = true;
        setFocusable(true);
        r4.requestFocus();
        AndroidUtilities.runOnUIThread(new InviteMembersBottomSheet$$ExternalSyntheticLambda1(r4));
        return;
    L15:
        r34 = null;
        goto L17
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void search(String r1) {
        this.searchAdapter.searchDialogs(r1);
    }

    public void setDelegate(InviteMembersBottomSheetDelegate r1, ArrayList<Long> r2) {
        this.dialogsDelegate = r1;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogsNeedReload);
        this.dialogsServerOnly = new ArrayList(MessagesController.getInstance(this.currentAccount).dialogsServerOnly);
        updateRows();
    }

    public void setDelegate(GroupCreateActivity.ContactsAddActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setSelectedContacts(ArrayList<Long> r12) {
        int r0 = r12.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        Long r3 = r12.get(r2);
        long r4 = r3.longValue();
        boolean r6 = DialogObject.isChatDialog(r4);
        int r7 = this.currentAccount;
        if (r6 == false) goto L7;
        Object r32 = MessagesController.getInstance(r7).getChat(Long.valueOf(-r4));
    L8:
        GroupCreateSpan r42 = new GroupCreateSpan(this.spansContainer.getContext(), r32);
        this.spansContainer.addSpan(r42, false);
        r42.setOnClickListener(this.spanClickListener);
        r2 = r2 + 1;
        goto L3
    L7:
        r32 = MessagesController.getInstance(r7).getUser(r3);
        goto L8
    L9:
        spansCountChanged(false);
        int r122 = this.spansContainer.getChildCount();
        Point r02 = AndroidUtilities.displaySize;
        if (r02.x >= r02.y) goto L12;
        boolean r03 = true;
    L14:
        if (AndroidUtilities.isTablet() == true) goto L18;
        if (r03 == true) goto L18;
        this.maxSize = AndroidUtilities.dp(56.0f);
    L20:
        if (AndroidUtilities.isTablet() == false) goto L23;
        Point r04 = AndroidUtilities.displaySize;
        float r05 = Math.min(r04.x, r04.y) * 0.8f;
    L22:
        int r06 = (int) r05;
    L26:
        int r22 = r06 - AndroidUtilities.dp(26.0f);
        int r43 = AndroidUtilities.dp(10.0f);
        int r5 = 0;
        int r62 = 0;
    L27:
        if (r5 >= r122) goto L36;
        View r72 = this.spansContainer.getChildAt(r5);
        if ((r72 instanceof GroupCreateSpan) == false) goto L35;
        r72.measure(View.MeasureSpec.makeMeasureSpec(r06, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
        if ((r72.getMeasuredWidth() + r62) <= r22) goto L34;
        r43 = r43 + (r72.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
        r62 = 0;
    L34:
        r62 = r62 + (r72.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
    L35:
        r5 = r5 + 1;
        goto L27
    L36:
        int r44 = r43 + AndroidUtilities.dp(42.0f);
        if (this.dialogsDelegate != null) goto L39;
        int r123 = Math.max(0, Math.min(this.maxSize, r44) - AndroidUtilities.dp(52.0f));
    L43:
        int r07 = this.searchAdditionalHeight;
        if (this.selectedContacts.size() <= 0) goto L46;
        r1 = AndroidUtilities.dp(56.0f);
    L46:
        this.searchAdditionalHeight = r1;
        if (r123 != this.additionalHeight) goto L51;
        if (r07 != r1) goto L51;
        return;
    L51:
        this.additionalHeight = r123;
        return;
    L39:
        if (this.spanEnter == false) goto L41;
        r123 = Math.min(this.maxSize, r44);
        goto L43
    L41:
        r123 = 0;
        goto L43
    L23:
        if (r03 == false) goto L25;
        r06 = AndroidUtilities.displaySize.x;
        goto L26
    L25:
        r05 = Math.max(AndroidUtilities.displaySize.x * 0.8f, Math.min(AndroidUtilities.dp(480.0f), AndroidUtilities.displaySize.x));
    L18:
        this.maxSize = AndroidUtilities.dp(144.0f);
        goto L20
    L12:
        r03 = false;
        goto L14
    }
}
