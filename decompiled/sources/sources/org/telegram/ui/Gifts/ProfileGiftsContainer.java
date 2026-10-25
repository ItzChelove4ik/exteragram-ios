package org.telegram.ui.Gifts;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.CallLogActivity$$ExternalSyntheticLambda6;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.ProfileGiftsView;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public abstract class ProfileGiftsContainer extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final HashMap<Pair<Integer, Long>, CharSequence> cachedLastEmojis = null;
    private CharSequence addCollectionTabText;
    private final CharSequence addGiftsText;
    private final BoolAnimator animatorBottomButtonVisibility;
    private int backgroundColor;
    private final FrameLayout bulletinContainer;
    private final ButtonWithCounterView button;
    private final FrameLayout buttonContainer;
    private int buttonContainerHeightDp;
    private int buttonContainerOffset;
    private final CheckBox2 checkbox;
    private final LinearLayout checkboxLayout;
    private int checkboxRequestId;
    private final TextView checkboxTextView;
    public final StarsController.GiftsCollections collections;
    private final int currentAccount;
    public ItemOptions currentMenu;
    private final long dialogId;
    private int externalPaddingTop;
    private final BaseFragment fragment;
    public IBlur3Capture iBlur3Capture;
    private ViewGroup iBlur3CaptureParent;
    private final StarsController.GiftsList list;
    private int pendingScrollToCollectionId;
    private boolean reorderingCollections;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Runnable sendCollectionsOrder;
    private final CharSequence sendGiftsToFriendsText;
    private final ViewPagerFixed.TabsView tabsView;
    private final ViewPagerFixed viewPager;
    private int visibleHeight;

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed {
        final /* synthetic */ ProfileGiftsContainer this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass1(ProfileGiftsContainer r1, Context r2, BaseFragment r3) {
            this.this$0 = r1;
            this.val$fragment = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void addMoreTabs() {
            if (this.this$0.canAdd() == true) goto L5;
            return;
        L5:
            if (ProfileGiftsContainer.m14937$$Nest$fgettabsView(this.this$0) != null) goto L7;
            return;
        L7:
            if (ProfileGiftsContainer.m14929$$Nest$fgetaddCollectionTabText(this.this$0) != null) goto L9;
            SpannableStringBuilder r0 = new SpannableStringBuilder("+ " + LocaleController.getString(R.string.Gift2NewCollection));
            ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.poll_add_plus);
            r1.spaceScaleX = 0.8f;
            r0.setSpan(r1, 0, 1, 33);
            ProfileGiftsContainer.m14939$$Nest$fputaddCollectionTabText(this.this$0, r0);
        L9:
            ProfileGiftsContainer.m14937$$Nest$fgettabsView(this.this$0).addTab(-1, ProfileGiftsContainer.m14929$$Nest$fgetaddCollectionTabText(this.this$0));
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScroll(MotionEvent r1) {
            return !this.this$0.isReordering();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r2) {
            super.onTabAnimationUpdate(r2);
            this.this$0.updateButton();
            BaseFragment r22 = this.val$fragment;
            if ((r22 instanceof ProfileActivity) == false) goto L7;
            ((ProfileActivity) r22).updateSelectedMediaTabText();
            View r23 = this.val$fragment.getFragmentView();
            if (r23 == null) goto L7;
            r23.invalidate();
        L7:
            this.this$0.updateTabsY();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabScrollEnd(int r1) {
            super.onTabScrollEnd(r1);
            this.this$0.updateButton();
            BaseFragment r0 = this.val$fragment;
            if ((r0 instanceof ProfileActivity) == false) goto L6;
            ((ProfileActivity) r0).updateSelectedMediaTabText();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed.Adapter {
        final /* synthetic */ ProfileGiftsContainer this$0;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass2(ProfileGiftsContainer r1, int r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$currentAccount = r2;
            this.val$resourcesProvider = r3;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void applyReorder(ArrayList<Integer> r8) {
            ArrayList<Integer> r0 = new ArrayList();
            int r1 = r8.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L10;
            Integer r4 = r8.get(r3);
            r3 = r3 + 1;
            Integer r42 = r4;
            int r5 = r42.intValue();
            if (r5 == (-1)) goto L3;
            if (r5 == (-2)) goto L3;
            r0.add(r42);
            goto L3
        L10:
            this.this$0.collections.reorder(r0);
            Page r82 = this.this$0.getCurrentPage();
            if (r82 != null) goto L13;
        L17:
            AndroidUtilities.cancelRunOnUIThread(ProfileGiftsContainer.m14936$$Nest$fgetsendCollectionsOrder(this.this$0));
            AndroidUtilities.runOnUIThread(ProfileGiftsContainer.m14936$$Nest$fgetsendCollectionsOrder(this.this$0), 1000);
            return;
        L13:
            if (r82.isCollection == false) goto L16;
            r2 = this.this$0.collections.indexOf(r82.list.collectionId) + 1;
        L16:
            ProfileGiftsContainer.m14937$$Nest$fgettabsView(this.this$0).selectTab(r2, r2, 0.0f);
            goto L17
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r3, int r4, int r5) {
            Page r32 = (Page) r3;
            ProfileGiftsContainer r0 = this.this$0;
            if (r5 != 0) goto L5;
            StarsController.GiftsList r42 = ProfileGiftsContainer.m14934$$Nest$fgetlist(r0);
            boolean r52 = false;
        L6:
            r32.bind(r52, r42);
            r32.setVisibleHeight(ProfileGiftsContainer.m14938$$Nest$fgetvisibleHeight(this.this$0));
            r32.setHasTabs(!this.this$0.collections.getCollections().isEmpty());
            return;
        L5:
            r42 = r0.collections.getListByIndex(r4 - 1);
            r52 = true;
            goto L6
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public boolean canReorder(int r1) {
            if (r1 != 0) goto L5;
            return false;
        L5:
            return true;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r3) {
            if (r3 != (-1)) goto L7;
            return null;
        L7:
            return new Page(this.this$0, this.val$currentAccount, this.val$resourcesProvider);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return this.this$0.collections.getCollections().size() + 1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemId(int r1) {
            if (r1 != 0) goto L6;
            return -2;
        L6:
            return this.this$0.collections.getCollections().get(r1 - 1).collection_id;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public CharSequence getItemTitle(int r5) {
            if (r5 == 0) goto L4;
            TL_stars.TL_starGiftCollection r4 = this.this$0.collections.getCollections().get(r5 - 1);
            if (r4 != null) goto L9;
            return null;
        L9:
            SpannableStringBuilder r52 = new SpannableStringBuilder(r4.title);
            if (r4.icon == null) goto L12;
            TextPaint r1 = new TextPaint(1);
            r1.setTextSize(AndroidUtilities.dp(16.0f));
            SpannableStringBuilder r2 = new SpannableStringBuilder("e ");
            r2.setSpan(new AnimatedEmojiSpan(r4.icon, r1.getFontMetricsInt()), 0, 1, 33);
            r52.insert(0, r2);
        L12:
            return r52;
        L4:
            return LocaleController.getString(R.string.Gift2CollectionAll);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 0;
        L5:
            return 1;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$3, reason: invalid class name */
    public class AnonymousClass3 extends Drawable {
        private final Drawable bg;
        private final Rect bgBounds;
        final /* synthetic */ ProfileGiftsContainer this$0;

        public AnonymousClass3(ProfileGiftsContainer r3) {
            this.this$0 = r3;
            this.bg = Theme.createRoundRectDrawable(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), ProfileGiftsContainer.m14930$$Nest$fgetbackgroundColor(r3));
            this.bgBounds = new Rect();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            this.bgBounds.set(getBounds());
            this.bgBounds.inset(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(8.0f));
            this.bg.setBounds(this.bgBounds);
            this.bg.draw(r4);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.bg.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$4, reason: invalid class name */
    public class AnonymousClass4 extends ShareAlert {
        final /* synthetic */ ProfileGiftsContainer this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass4(ProfileGiftsContainer r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8, BaseFragment r9) {
            this.this$0 = r1;
            this.val$fragment = r9;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r7, int r8, TLRPC.TL_forumTopic r9, boolean r10) {
            if (r10 == false) goto L26;
            BulletinFactory r82 = BulletinFactory.of(this.val$fragment);
            if (r82 != null) goto L7;
            return;
        L7:
            if (r7.size() != 1) goto L19;
            long r2 = r7.keyAt(0);
            if (r2 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L11;
            r82.createSimpleBulletin(R.raw.saved_messages, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCollectionSharedToSavedMessages, new Object[0])), 5000).hideAfterBottomSheet(false).show();
        L23:
            this.this$0.performHapticFeedback(3);     // Catch: Exception -> L22
            return;
        L27:
            return;
        L11:
            int r0 = this.currentAccount;
            if (r2 >= 0) goto L18;
            TLRPC.Chat r72 = MessagesController.getInstance(r0).getChat(Long.valueOf(-r2));
            int r02 = R.raw.forward;
            int r22 = R.string.GiftCollectionSharedTo;
            if (r9 == null) goto L16;
            String r73 = r9.title;
        L17:
            r82.createSimpleBulletin(r02, AndroidUtilities.replaceTags(LocaleController.formatString(r22, new Object[]{r73})), 5000).hideAfterBottomSheet(false).show();
            goto L23
        L16:
            r73 = r72.title;
            goto L17
        L18:
            TLRPC.User r74 = MessagesController.getInstance(r0).getUser(Long.valueOf(r2));
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCollectionSharedTo, new Object[]{r74.first_name})), 5000).hideAfterBottomSheet(false).show();
            goto L23
        L19:
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatPluralString("GiftCollectionSharedToManyChats", r7.size(), new Object[]{Integer.valueOf(r7.size())}))).hideAfterBottomSheet(false).show();
            goto L23
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$5, reason: invalid class name */
    public class AnonymousClass5 extends EditTextCaption {
        AnimatedTextView.AnimatedTextDrawable limit;
        AnimatedColor limitColor;
        private int limitCount;
        final /* synthetic */ ProfileGiftsContainer this$0;

        public AnonymousClass5(ProfileGiftsContainer r8, Context r9, Theme.ResourcesProvider r10) {
            this.this$0 = r8;
            super(r9, r10);
            this.limitColor = new AnimatedColor(this);
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.limit = r0;
            r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.limit.setTextSize(AndroidUtilities.dp(15.33f));
            this.limit.setCallback(this);
            this.limit.setGravity(5);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
            AnimatedColor r1 = this.limitColor;
            if (this.limitCount >= 0) goto L5;
            int r2 = Theme.key_text_RedRegular;
        L6:
            r0.setTextColor(r1.set(Theme.getColor(r2, ProfileGiftsContainer.m14935$$Nest$fgetresourcesProvider(this.this$0))));
            this.limit.setBounds(getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            this.limit.draw(r6);
            return;
        L5:
            r2 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            if (this.limit == null) goto L10;
            this.limitCount = 12 - r1.length();
            this.limit.cancelAnimation();
            AnimatedTextView.AnimatedTextDrawable r12 = this.limit;
            int r22 = this.limitCount;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r22 > 4) goto L8;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        L8:
            r12.setText(r42);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$6, reason: invalid class name */
    public class AnonymousClass6 implements TextView.OnEditorActionListener {
        final /* synthetic */ ProfileGiftsContainer this$0;
        final /* synthetic */ View val$currentFocus;
        final /* synthetic */ AlertDialog[] val$dialog;
        final /* synthetic */ EditTextCaption val$editText;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public AnonymousClass6(ProfileGiftsContainer r1, EditTextCaption r2, Utilities.Callback r3, AlertDialog[] r4, View r5) {
            this.this$0 = r1;
            this.val$editText = r2;
            this.val$whenDone = r3;
            this.val$dialog = r4;
            this.val$currentFocus = r5;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView r3, int r4, KeyEvent r5) {
            if (r4 != 6) goto L18;
            String r32 = this.val$editText.getText().toString();
            if (r32.length() > 0) goto L7;
        L16:
            AndroidUtilities.shakeView(this.val$editText);
            return true;
        L7:
            if (r32.length() > 12) goto L16;
            this.val$whenDone.run(r32);
            AlertDialog r33 = this.val$dialog[0];
            if (r33 == null) goto L12;
            r33.dismiss();
        L12:
            View r2 = this.val$currentFocus;
            if (r2 == null) goto L15;
            r2.requestFocus();
        L15:
            return true;
        L18:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$7, reason: invalid class name */
    public class AnonymousClass7 implements TextWatcher {
        boolean ignoreTextChange;
        final /* synthetic */ ProfileGiftsContainer this$0;
        final /* synthetic */ EditTextCaption val$editText;

        public AnonymousClass7(ProfileGiftsContainer r1, EditTextCaption r2) {
            this.this$0 = r1;
            this.val$editText = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            if (this.ignoreTextChange == false) goto L6;
            return;
        L6:
            if (r3.length() <= 12) goto L14;
            this.ignoreTextChange = true;
            r3.delete(12, r3.length());
            AndroidUtilities.shakeView(this.val$editText);
            this.val$editText.performHapticFeedback(3, 2);     // Catch: Exception -> L11
        L9:
            this.ignoreTextChange = false;
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static class Page extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
        private final int currentAccount;
        private FrameLayout emptyView1;
        private TextView emptyView1Button;
        private LinearLayout emptyView1Layout;
        private TextView emptyView1Title;
        private FrameLayout emptyView2;
        private ButtonWithCounterView emptyView2Button;
        private LinearLayout emptyView2Layout;
        private TextView emptyView2Subtitle;
        private TextView emptyView2Title;
        private boolean hasTabs;
        public IBlur3Capture iBlur3Capture;
        public boolean isCollection;
        public StarsController.GiftsList list;
        private final UniversalRecyclerView listView;
        private final ProfileGiftsContainer parent;
        private final ItemTouchHelper reorder;
        private boolean reordering;
        private final Theme.ResourcesProvider resourcesProvider;
        private int visibleHeight;

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends UniversalRecyclerView {
            final /* synthetic */ Page this$0;
            final /* synthetic */ ProfileGiftsContainer val$parent;

            public AnonymousClass1(Page r1, Context r2, int r3, int r4, boolean r5, Utilities.Callback2 r6, Utilities.Callback5 r7, Utilities.Callback5Return r8, Theme.ResourcesProvider r9, int r10, int r11, ProfileGiftsContainer r12) {
                this.this$0 = r1;
                this.val$parent = r12;
                super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                super.onLayout(r1, r2, r3, r4, r5);
                this.val$parent.updateTabsY();
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.OnScrollListener {
            final /* synthetic */ Page this$0;
            final /* synthetic */ ProfileGiftsContainer val$parent;

            public AnonymousClass2(Page r1, ProfileGiftsContainer r2) {
                this.this$0 = r1;
                this.val$parent = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                if (this.this$0.isAttachedToWindow() == true) goto L5;
            L9:
                this.val$parent.updateTabsY();
                return;
            L5:
                if (Page.m14952$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == true) goto L7;
            L8:
                this.this$0.list.load();
                goto L9
            L7:
                if (Page.m14955$$Nest$misLoadingVisible(this.this$0) == false) goto L9;
                goto L8
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$3, reason: invalid class name */
        public class AnonymousClass3 extends DefaultItemAnimator {
            final /* synthetic */ Page this$0;
            final /* synthetic */ ProfileGiftsContainer val$parent;

            public AnonymousClass3(Page r1, ProfileGiftsContainer r2) {
                this.this$0 = r1;
                this.val$parent = r2;
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onAddAnimationUpdate(RecyclerView.ViewHolder r1) {
                super.onAddAnimationUpdate(r1);
                this.val$parent.updateTabsY();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
                super.onChangeAnimationUpdate(r1);
                this.val$parent.updateTabsY();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                super.onMoveAnimationUpdate(r1);
                this.val$parent.updateTabsY();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onRemoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                super.onRemoveAnimationUpdate(r1);
                this.val$parent.updateTabsY();
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$4, reason: invalid class name */
        public class AnonymousClass4 extends ItemTouchHelper.Callback {
            final /* synthetic */ Page this$0;
            final /* synthetic */ ProfileGiftsContainer val$parent;

            public AnonymousClass4(Page r1, ProfileGiftsContainer r2) {
                this.this$0 = r1;
                this.val$parent = r2;
            }

            private boolean canReorder(TL_stars.SavedStarGift r4) {
                if (Page.m14954$$Nest$fgetreordering(this.this$0) == true) goto L6;
                return false;
            L6:
                if (this.this$0.list != ProfileGiftsContainer.m14934$$Nest$fgetlist(this.val$parent)) goto L12;
                if (r4 != null) goto L9;
            L11:
                return false;
            L9:
                if (r4.pinned_to_top == false) goto L11;
                return true;
            L12:
                return true;
            }

            private TL_stars.SavedStarGift getSavedGift(RecyclerView.ViewHolder r1) {
                View r0 = r1.itemView;
                if ((r0 instanceof GiftSheet.GiftCell) == true) goto L5;
                return null;
            L5:
                return ((GiftSheet.GiftCell) r0).getSavedGift();
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
                super.clearView(r1, r2);
                r2.itemView.setPressed(false);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
                if (canReorder(getSavedGift(r2)) == false) goto L7;
                return ItemTouchHelper.Callback.makeMovementFlags(15, 0);
            L7:
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean isItemViewSwipeEnabled() {
                return Page.m14954$$Nest$fgetreordering(this.this$0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean isLongPressDragEnabled() {
                return Page.m14954$$Nest$fgetreordering(this.this$0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean onMove(RecyclerView r3, RecyclerView.ViewHolder r4, RecyclerView.ViewHolder r5) {
                Page r32 = this.this$0;
                if (r32.list != null) goto L5;
            L25:
                return false;
            L5:
                if (Page.m14954$$Nest$fgetreordering(r32) == false) goto L25;
                if (canReorder(getSavedGift(r4)) == false) goto L25;
                if (canReorder(getSavedGift(r5)) == false) goto L25;
                int r33 = r4.getAdapterPosition();
                int r42 = r5.getAdapterPosition();
                Page r52 = this.this$0;
                if (r52.isCollection == false) goto L15;
                r52.list.reorder(r33, r42);
                this.val$parent.collections.updateIcon(this.this$0.list.collectionId);
            L16:
                Page.m14952$$Nest$fgetlistView(this.this$0).adapter.notifyItemMoved(r33, r42);
                Page.m14952$$Nest$fgetlistView(this.this$0).adapter.updateWithoutNotify();
                if (this.this$0.isCollection == false) goto L19;
                ProfileGiftsContainer.m14940$$Nest$mfillTabs(this.val$parent, true);
            L19:
                BaseFragment r2 = LaunchActivity.getSafeLastFragment();
                if ((r2 instanceof ProfileActivity) == false) goto L24;
                ProfileGiftsView r22 = ((ProfileActivity) r2).giftsView;
                if (r22 == null) goto L24;
                r22.update();
            L24:
                return true;
            L15:
                r52.list.reorderPinned(r33, r42);
                goto L16
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
                Page r0 = this.this$0;
                if (r4 != 0) goto L8;
                StarsController.GiftsList r02 = r0.list;
                if (r02 == null) goto L12;
                r02.reorderDone();
            L12:
                super.onSelectedChanged(r3, r4);
                return;
            L8:
                if (Page.m14952$$Nest$fgetlistView(r0) == null) goto L10;
                Page.m14952$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            L10:
                if (r3 == null) goto L12;
                r3.itemView.setPressed(true);
                goto L12
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$5, reason: invalid class name */
        public class AnonymousClass5 extends ScrollView {
            final /* synthetic */ Page this$0;

            public AnonymousClass5(Page r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(260.0f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatedEmojiDrawable {
            final /* synthetic */ Page this$0;

            public AnonymousClass6(Page r1, int r2, int r3, TLRPC.Document r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicHeight() {
                return AndroidUtilities.dp(24.0f);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicWidth() {
                return AndroidUtilities.dp(24.0f);
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$7, reason: invalid class name */
        public class AnonymousClass7 extends StarGiftSheet {
            final /* synthetic */ Page this$0;

            public AnonymousClass7(Page r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6) {
                this.this$0 = r1;
                super(r2, r3, r4, r6);
            }

            @Override // org.telegram.ui.Stars.StarGiftSheet, org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
            public BulletinFactory getBulletinFactory() {
                return BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(Page.m14953$$Nest$fgetparent(this.this$0)));
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$8, reason: invalid class name */
        public class AnonymousClass8 extends StarGiftSheet {
            final /* synthetic */ Page this$0;

            public AnonymousClass8(Page r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6) {
                this.this$0 = r1;
                super(r2, r3, r4, r6);
            }

            @Override // org.telegram.ui.Stars.StarGiftSheet, org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
            public BulletinFactory getBulletinFactory() {
                return BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(Page.m14953$$Nest$fgetparent(this.this$0)));
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$Page$9, reason: invalid class name */
        public class AnonymousClass9 extends StarGiftSheet {
            final /* synthetic */ Page this$0;

            public AnonymousClass9(Page r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6) {
                this.this$0 = r1;
                super(r2, r3, r4, r6);
            }

            @Override // org.telegram.ui.Stars.StarGiftSheet, org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
            public BulletinFactory getBulletinFactory() {
                return BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(Page.m14953$$Nest$fgetparent(this.this$0)));
            }
        }

        public static /* synthetic */ void $r8$lambda$1znETsFgfGmNMdKts_dCznPfSNY(Page r0) {
            r0.lambda$onItemLongPress$15();
        }

        public static /* synthetic */ void $r8$lambda$2fawzvId6Z3MobYgG1gyHUudQqg(ItemOptions r0, ItemOptions r1) {
            r0.openSwipeback(r1);
        }

        public static /* synthetic */ void $r8$lambda$4BKIWhrnVJc8pF61m5XQj5n9mFw(Page r0, TL_stars.SavedStarGift r1, GiftSheet.GiftCell r2) {
            r0.lambda$onItemLongPress$20(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$5K04S4qBktclC3QIS2HDQBeFVVE(Page r0, boolean r1, TL_stars.TL_starGiftCollection r2, TL_stars.SavedStarGift r3, ItemOptions r4, View r5) {
            r0.lambda$onItemLongPress$10(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$Bqu9rNGugJHg3wwWM6c84NDNVYw(Page r0, View r1) {
            r0.lambda$updateEmptyView$0(r1);
        }

        /* renamed from: $r8$lambda$Ijd-ImJbnHdtlS_dfKcob_qbo0g, reason: not valid java name */
        public static /* synthetic */ void m14942$r8$lambda$IjdImJbnHdtlS_dfKcob_qbo0g(Page r0, TL_stars.SavedStarGift r1, ItemOptions r2) {
            r0.lambda$onItemLongPress$22(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$MhzbdqMW90OKrDTuebGODWmSzXY(Page r0, TL_stars.SavedStarGift r1) {
            r0.lambda$onItemLongPress$16(r1);
        }

        public static /* synthetic */ void $r8$lambda$PwLSUVw96umYpGP6GekH6Qrw8Fo(Page r0, String r1) {
            r0.lambda$onItemLongPress$17(r1);
        }

        public static /* synthetic */ void $r8$lambda$U9Vhj3NG1oGp1BAr5gCh56D1Ebs(Page r0, TL_stars.SavedStarGift r1) {
            r0.lambda$onItemLongPress$18(r1);
        }

        /* renamed from: $r8$lambda$VR72YYxj6_9aX1v_H_ML7ek-OJI, reason: not valid java name */
        public static /* synthetic */ void m14943$r8$lambda$VR72YYxj6_9aX1v_H_ML7ekOJI(ItemOptions r0) {
            r0.closeSwipeback();
        }

        /* renamed from: $r8$lambda$WJYhHrXj1RsH-uKouPTl8m2kf-g, reason: not valid java name */
        public static /* synthetic */ void m14944$r8$lambda$WJYhHrXj1RsHuKouPTl8m2kfg(Page r0, TL_stars.SavedStarGift r1, TL_stars.TL_starGiftCollection r2) {
            r0.lambda$onItemLongPress$7(r1, r2);
        }

        /* renamed from: $r8$lambda$YYm4dmUeH1XsfAc6FR-ssdgIqyo, reason: not valid java name */
        public static /* synthetic */ void m14945$r8$lambda$YYm4dmUeH1XsfAc6FRssdgIqyo(Page r0, TL_stars.SavedStarGift r1, TL_stars.TL_starGiftUnique r2, long r3, boolean r5) {
            r0.lambda$onItemClick$5(r1, r2, r3, r5);
        }

        public static /* synthetic */ void $r8$lambda$_ypp0IUpfhe8swVFcajevjqLVJs(Page r0, TL_stars.SavedStarGift r1) {
            r0.lambda$onItemLongPress$21(r1);
        }

        public static /* synthetic */ void $r8$lambda$eSfYhAhPvlsyyZslCxGuXO9Bp7Y(Page r0, TL_stars.SavedStarGift r1, GiftSheet.GiftCell r2, View r3) {
            r0.lambda$onItemLongPress$13(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$fYFEyvwAF3TdbCBvLBQ4-9ii-ZQ, reason: not valid java name */
        public static /* synthetic */ void m14946$r8$lambda$fYFEyvwAF3TdbCBvLBQ49iiZQ(BaseFragment r1) {
            ((ProfileActivity) r1).scrollToSharedMedia(true);
        }

        public static /* synthetic */ void $r8$lambda$hveDSgW10PchZb4jQeCA0UMql38(Page r0) {
            r0.lambda$onItemLongPress$14();
        }

        public static /* synthetic */ BulletinFactory $r8$lambda$k_o9eMF5D3sBicZiWbiQKbDZB3I(Page r0, View r1, boolean r2) {
            return r0.lambda$onItemLongPress$12(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$opQ1jUHON6afGJsjllfGQ8C2mHM(Page r0, ItemOptions r1, TL_stars.SavedStarGift r2, View r3) {
            r0.lambda$onItemLongPress$9(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$pWv_mISsJJsqdJQyA3oS4Ucl638(Page r0) {
            r0.lambda$onItemClick$4();
        }

        /* renamed from: $r8$lambda$rvoqsG1zxEtK-xr7O6rqyQT9Og4, reason: not valid java name */
        public static /* synthetic */ void m14947$r8$lambda$rvoqsG1zxEtKxr7O6rqyQT9Og4(Page r0) {
            r0.lambda$onItemLongPress$19();
        }

        /* renamed from: $r8$lambda$sAh20Rizn-aETvfCJNaLnAP9_QQ, reason: not valid java name */
        public static /* synthetic */ void m14948$r8$lambda$sAh20RiznaETvfCJNaLnAP9_QQ(Page r0, View r1) {
            r0.lambda$updateEmptyView$1(r1);
        }

        /* renamed from: $r8$lambda$uzCrtTWl-euqZt97jxzK3U8bU3A, reason: not valid java name */
        public static /* synthetic */ void m14949$r8$lambda$uzCrtTWleuqZt97jxzK3U8bU3A(Page r0, int r1) {
            r0.lambda$fillItems$3(r1);
        }

        /* renamed from: $r8$lambda$wkNf8HwGIwW-QpJnlVIQQwYTfDk, reason: not valid java name */
        public static /* synthetic */ void m14950$r8$lambda$wkNf8HwGIwWQpJnlVIQQwYTfDk(Page r0, TL_stars.SavedStarGift r1, String r2) {
            r0.lambda$onItemLongPress$8(r1, r2);
        }

        /* renamed from: -$$Nest$fgethasTabs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14951$$Nest$fgethasTabs(Page r0) {
            return r0.hasTabs;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ UniversalRecyclerView m14952$$Nest$fgetlistView(Page r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetparent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProfileGiftsContainer m14953$$Nest$fgetparent(Page r0) {
            return r0.parent;
        }

        /* renamed from: -$$Nest$fgetreordering, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14954$$Nest$fgetreordering(Page r0) {
            return r0.reordering;
        }

        /* renamed from: -$$Nest$misLoadingVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14955$$Nest$misLoadingVisible(Page r0) {
            return r0.isLoadingVisible();
        }

        /* renamed from: -$$Nest$msetReordering, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14956$$Nest$msetReordering(Page r0, boolean r1) {
            r0.setReordering(r1);
        }

        public Page(ProfileGiftsContainer r14, int r15, Theme.ResourcesProvider r16) {
            super(r14.getContext());
            this.visibleHeight = AndroidUtilities.displaySize.y;
            Context r2 = r14.getContext();
            this.parent = r14;
            this.currentAccount = r15;
            this.resourcesProvider = r16;
            AnonymousClass1 r0 = new AnonymousClass1(this, r2, r15, 0, false, new ProfileGiftsContainer$Page$$ExternalSyntheticLambda0(this), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda1(this), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda2(this), r16, 3, 1, r14);
            this.listView = r0;
            r0.adapter.setApplyBackground(false);
            r0.setSelectorType(9);
            r0.setSelectorDrawableColor(0);
            r0.setPadding(AndroidUtilities.dp(9.0f), ProfileGiftsContainer.m14932$$Nest$fgetexternalPaddingTop(r14), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(86.0f));
            r0.setClipToPadding(false);
            r0.setClipChildren(false);
            addView(r0, LayoutHelper.createFrame(-1, -1, 119));
            r0.addOnScrollListener(new AnonymousClass2(this, r14));
            AnonymousClass3 r22 = new AnonymousClass3(this, r14);
            r22.setSupportsChangeAnimations(false);
            r22.setDelayAnimations(false);
            r22.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r22.setDurations(350);
            r0.setItemAnimator(r22);
            ItemTouchHelper r23 = new ItemTouchHelper(new AnonymousClass4(this, r14));
            this.reorder = r23;
            r23.attachToRecyclerView(r0);
            updateEmptyView();
        }

        private boolean isLoadingVisible() {
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L10;
            if ((this.listView.getChildAt(r1) instanceof FlickerLoadingView) == true) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            return true;
        L10:
            return false;
        }

        private /* synthetic */ void lambda$fillItems$3(int r1) {
            UniversalRecyclerView r0 = this.listView;
            if (r0 == null) goto L6;
            r0.setSpanCount(r1);
            return;
        }

        private /* synthetic */ void lambda$onItemClick$4() {
            update(false);
        }

        private /* synthetic */ void lambda$onItemClick$5(TL_stars.SavedStarGift r5, TL_stars.TL_starGiftUnique r6, long r7, boolean r9) {
            this.list.gifts.remove(r5);
            update(true);
            long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
            ProfileGiftsContainer r02 = this.parent;
            if (r7 != r0) goto L5;
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(r02)).createSimpleBulletin(r6.getDocument(), LocaleController.getString(R.string.BoughtResoldGiftTitle), LocaleController.formatString(R.string.BoughtResoldGiftText, new Object[]{r6.title + " #" + LocaleController.formatNumber(r6.num, ',')})).hideAfterBottomSheet(false).show();
        L6:
            LaunchActivity r4 = LaunchActivity.instance;
            if (r4 == null) goto L10;
            r4.getFireworksOverlay().start(true);
            return;
        L10:
            return;
        L5:
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(r02)).createSimpleBulletin(r6.getDocument(), LocaleController.getString(R.string.BoughtResoldGiftToTitle), LocaleController.formatString(R.string.BoughtResoldGiftToText, new Object[]{DialogObject.getShortName(this.currentAccount, r7)})).hideAfterBottomSheet(false).show();
            goto L6
        }

        private /* synthetic */ void lambda$onItemLongPress$10(boolean r3, TL_stars.TL_starGiftCollection r4, TL_stars.SavedStarGift r5, ItemOptions r6, View r7) {
            ProfileGiftsContainer r72 = this.parent;
            if (r3 == true) goto L5;
            r72.collections.addGift(r4.collection_id, r5, true);
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).createSimpleMultiBulletin(r5.gift.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2AddedToCollection, new Object[]{StarGiftSheet.getGiftName(r5.gift), r4.title}))).show();
        L6:
            r6.dismiss();
            this.parent.updateTabsShown(true);
            return;
        L5:
            r72.collections.removeGift(r4.collection_id, r5);
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).createSimpleMultiBulletin(r5.gift.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2RemovedFromCollection, new Object[]{StarGiftSheet.getGiftName(r5.gift), r4.title}))).show();
            goto L6
        }

        private /* synthetic */ BulletinFactory lambda$onItemLongPress$12(View r2, boolean r3) {
            ((GiftSheet.GiftCell) r2).setPinned(r3, true);
            this.listView.scrollToPosition(0);
            return BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent));
        }

        private /* synthetic */ void lambda$onItemLongPress$13(TL_stars.SavedStarGift r12, GiftSheet.GiftCell r13, View r14) {
            if (r12.unsaved == false) goto L5;
            r12.unsaved = false;
            r13.setStarsGift(r12, true, false);
            TL_stars.saveStarGift r132 = new TL_stars.saveStarGift();
            r132.stargift = this.list.getInput(r12);
            r132.unsave = r12.unsaved;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r132, null, 64);
        L5:
            boolean r133 = r12.pinned_to_top;
            boolean r0 = !r133;
            if (this.list.togglePinned(r12, r0, false) == false) goto L9;
            new UnpinSheet(getContext(), ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent), r12, this.resourcesProvider, new ProfileGiftsContainer$Page$$ExternalSyntheticLambda25(this, r14, r0)).show();
            return;
        L9:
            ProfileGiftsContainer r122 = this.parent;
            if (r133 == true) goto L12;
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(r122)).createSimpleBulletin(R.raw.ic_pin, LocaleController.getString(R.string.Gift2PinnedTitle), LocaleController.getString(R.string.Gift2PinnedSubtitle)).show();
        L13:
            ((GiftSheet.GiftCell) r14).setPinned(r0, true);
            this.listView.scrollToPosition(0);
            return;
        L12:
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(r122)).createSimpleBulletin(R.raw.ic_unpin, LocaleController.getString(R.string.Gift2Unpinned)).show();
            goto L13
        }

        private /* synthetic */ void lambda$onItemLongPress$14() {
            setReordering(true);
        }

        private /* synthetic */ void lambda$onItemLongPress$15() {
            setReordering(true);
        }

        private /* synthetic */ void lambda$onItemLongPress$16(TL_stars.SavedStarGift r8) {
            new AnonymousClass7(this, getContext(), this.currentAccount, ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent), this.resourcesProvider).set(r8, null).toggleWear(false);
        }

        private /* synthetic */ void lambda$onItemLongPress$17(String r1) {
            AndroidUtilities.addToClipboard(r1);
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).createCopyLinkBulletin(false).show();
        }

        private /* synthetic */ void lambda$onItemLongPress$18(TL_stars.SavedStarGift r8) {
            new AnonymousClass8(this, getContext(), this.currentAccount, ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent), this.resourcesProvider).set(r8, null).onSharePressed(null);
        }

        private /* synthetic */ void lambda$onItemLongPress$19() {
            setReordering(true);
        }

        private /* synthetic */ void lambda$onItemLongPress$20(TL_stars.SavedStarGift r4, GiftSheet.GiftCell r5) {
            if (this.isCollection == false) goto L5;
        L9:
            r4.unsaved = !r4.unsaved;
            r5.setStarsGift(r4, true, this.isCollection);
            this.parent.collections.updateGiftsUnsaved(r4, r4.unsaved);
            TL_stars.saveStarGift r52 = new TL_stars.saveStarGift();
            r52.stargift = this.list.getInput(r4);
            r52.unsave = r4.unsaved;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, null);
            return;
        L5:
            if (r4.pinned_to_top == false) goto L9;
            if (r4.unsaved == true) goto L9;
            r5.setPinned(false, true);
            this.list.togglePinned(r4, false, false);
            goto L9
        }

        private /* synthetic */ void lambda$onItemLongPress$21(TL_stars.SavedStarGift r8) {
            new AnonymousClass9(this, getContext(), this.currentAccount, ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent), this.resourcesProvider).set(r8, null).openTransfer();
        }

        private /* synthetic */ void lambda$onItemLongPress$22(TL_stars.SavedStarGift r3, ItemOptions r4) {
            this.parent.collections.removeGift(this.list.collectionId, r3);
            r4.dismiss();
            this.parent.updateTabsShown(true);
            TL_stars.TL_starGiftCollection r42 = this.parent.collections.findById(this.list.collectionId);
            if (r42 == null) goto L6;
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).createSimpleMultiBulletin(r3.gift.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2RemovedFromCollection, new Object[]{StarGiftSheet.getGiftName(r3.gift), r42.title}))).show();
            return;
        }

        private /* synthetic */ void lambda$onItemLongPress$7(TL_stars.SavedStarGift r5, TL_stars.TL_starGiftCollection r6) {
            this.parent.collections.addGift(r6.collection_id, r5, true);
            ProfileGiftsContainer.m14940$$Nest$mfillTabs(this.parent, true);
            ViewPagerFixed.TabsView r0 = ProfileGiftsContainer.m14937$$Nest$fgettabsView(this.parent);
            int r1 = r6.collection_id;
            r0.scrollToTab(r1, this.parent.collections.indexOf(r1) + 1);
            if ((ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent) instanceof ProfileActivity) == false) goto L5;
            ((ProfileActivity) ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).scrollToSharedMedia(true);
        L5:
            this.parent.updateTabsShown(true);
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).createSimpleMultiBulletin(r5.gift.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2AddedToCollection, new Object[]{StarGiftSheet.getGiftName(r5.gift), r6.title}))).show();
        }

        private /* synthetic */ void lambda$onItemLongPress$8(TL_stars.SavedStarGift r3, String r4) {
            this.parent.collections.createCollection(r4, new ProfileGiftsContainer$Page$$ExternalSyntheticLambda26(this, r3));
        }

        private /* synthetic */ void lambda$onItemLongPress$9(ItemOptions r1, TL_stars.SavedStarGift r2, View r3) {
            r1.dismiss();
            ProfileGiftsContainer.m14941$$Nest$mopenEnterNameAlert(this.parent, null, new ProfileGiftsContainer$Page$$ExternalSyntheticLambda24(this, r2));
        }

        private /* synthetic */ void lambda$updateEmptyView$0(View r1) {
            StarsController.GiftsList r0 = this.list;
            if (r0 == null) goto L6;
            r0.resetFilters();
            return;
        }

        private /* synthetic */ void lambda$updateEmptyView$1(View r1) {
            this.parent.addGifts();
        }

        private void setReordering(boolean r5) {
            if (this.reordering == r5) goto L25;
            this.reordering = r5;
            ProfileGiftsContainer r0 = this.parent;
            r0.updatedReordering(r0.isReordering());
            int r1 = 0;
        L6:
            int r2 = this.listView.getChildCount();
            UniversalRecyclerView r3 = this.listView;
            if (r1 >= r2) goto L12;
            View r22 = r3.getChildAt(r1);
            if ((r22 instanceof GiftSheet.GiftCell) == false) goto L11;
            ((GiftSheet.GiftCell) r22).setReordering(r5, true);
        L11:
            r1 = r1 + 1;
            goto L6
        L12:
            UniversalAdapter r4 = r3.adapter;
            if (r4 == null) goto L15;
            r4.updateWithoutNotify();
        L15:
            if (r5 == false) goto L23;
            BaseFragment r42 = LaunchActivity.getSafeLastFragment();
            if ((r42 instanceof ProfileActivity) == false) goto L24;
            ((ProfileActivity) r42).scrollToSharedMedia(false);
            AndroidUtilities.runOnUIThread(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda3(r42));
            return;
        L24:
            return;
        L23:
            return;
        }

        private void updateEmptyView() {
            FrameLayout r0 = this.emptyView1;
            if (r0 == null) goto L5;
            removeView(r0);
        L5:
            FrameLayout r02 = this.emptyView2;
            if (r02 == null) goto L9;
            removeView(r02);
        L9:
            if (ProfileGiftsContainer.m14934$$Nest$fgetlist(this.parent) != this.list) goto L12;
            this.emptyView2 = null;
            this.emptyView2Title = null;
            this.emptyView2Subtitle = null;
            this.emptyView2Button = null;
            this.emptyView2Layout = null;
            this.emptyView1 = new FrameLayout(getContext());
            LinearLayout r03 = new LinearLayout(getContext());
            this.emptyView1Layout = r03;
            r03.setOrientation(1);
            this.emptyView1.addView(this.emptyView1Layout, LayoutHelper.createFrame(-2, -2, 17));
            BackupImageView r04 = new BackupImageView(getContext());
            r04.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f)));
            this.emptyView1Layout.addView(r04, LayoutHelper.createLinear(120, 120, 1, 0, 0, 0, 0));
            TextView r05 = new TextView(getContext());
            this.emptyView1Title = r05;
            r05.setTextSize(1, 17.0f);
            this.emptyView1Title.setTypeface(AndroidUtilities.bold());
            this.emptyView1Title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.emptyView1Title.setText(LocaleController.getString(R.string.ProfileGiftsNotFoundTitle));
            this.emptyView1Layout.addView(this.emptyView1Title, LayoutHelper.createLinear(-2, -2, 1, 0, 12, 0, 0));
            TextView r06 = new TextView(getContext());
            this.emptyView1Button = r06;
            r06.setTextSize(1, 14.0f);
            TextView r07 = this.emptyView1Button;
            int r1 = Theme.key_featuredStickers_addButton;
            r07.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            this.emptyView1Button.setText(LocaleController.getString(R.string.ProfileGiftsNotFoundButton));
            this.emptyView1Button.setOnClickListener(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda20(this));
            this.emptyView1Button.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f));
            this.emptyView1Button.setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.1f), 4, 4));
            ScaleStateListAnimator.apply(this.emptyView1Button);
            this.emptyView1Layout.addView(this.emptyView1Button, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
            addView(this.emptyView1, LayoutHelper.createFrame(-1, -1, 119));
            this.listView.setEmptyView(this.emptyView1);
            return;
        L12:
            this.emptyView1 = null;
            this.emptyView1Title = null;
            this.emptyView1Button = null;
            this.emptyView1Layout = null;
            this.emptyView2 = new FrameLayout(getContext());
            LinearLayout r08 = new LinearLayout(getContext());
            this.emptyView2Layout = r08;
            r08.setOrientation(1);
            this.emptyView2.addView(this.emptyView2Layout, LayoutHelper.createFrame(-2, -2, 17));
            TextView r09 = new TextView(getContext());
            this.emptyView2Title = r09;
            r09.setTextSize(1, 20.0f);
            this.emptyView2Title.setTypeface(AndroidUtilities.bold());
            this.emptyView2Title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.emptyView2Title.setText(LocaleController.getString(R.string.Gift2CollectionEmptyTitle));
            this.emptyView2Layout.addView(this.emptyView2Title, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 0));
            TextView r010 = new TextView(getContext());
            this.emptyView2Subtitle = r010;
            r010.setTextSize(1, 14.0f);
            this.emptyView2Subtitle.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider));
            this.emptyView2Subtitle.setText(LocaleController.getString(R.string.Gift2CollectionEmptyText));
            this.emptyView2Layout.addView(this.emptyView2Subtitle, LayoutHelper.createLinear(-2, -2, 1, 0, 10, 0, 0));
            ButtonWithCounterView r011 = new ButtonWithCounterView(getContext(), this.resourcesProvider);
            this.emptyView2Button = r011;
            int r2 = 0;
            r011.setText(LocaleController.getString(R.string.Gift2CollectionEmptyButton), false);
            this.emptyView2Layout.addView(this.emptyView2Button, LayoutHelper.createLinear(200, 44, 1, 0, 19, 0, 12));
            this.emptyView2Button.setOnClickListener(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda21(this));
            addView(this.emptyView2, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, -12.0f, 0.0f, 0.0f));
            this.listView.setEmptyView(this.emptyView2);
            LinearLayout r012 = this.emptyView2Layout;
            if (r012 != null) goto L15;
            return;
        L15:
            if (this.parent.collections.isMine() == true) goto L18;
            r2 = 8;
        L18:
            r012.setVisibility(r2);
        }

        public void bind(boolean r1, StarsController.GiftsList r2) {
            this.isCollection = r1;
            this.list = r2;
            if (r2 == null) goto L5;
            r2.load();
        L5:
            int r12 = 0;
            update(false);
            LinearLayout r22 = this.emptyView2Layout;
            if (r22 != null) goto L8;
            return;
        L8:
            if (this.parent.collections.isMine() == true) goto L11;
            r12 = 8;
        L11:
            r22.setVisibility(r12);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 == NotificationCenter.starUserGiftsLoaded) goto L5;
            return;
        L5:
            if (r3[1] != this.list) goto L21;
            update(true);
            if (this.list != null) goto L10;
            return;
        L10:
            if (isAttachedToWindow() == true) goto L12;
            return;
        L12:
            if (this.listView.canScrollVertically(1) == true) goto L14;
        L15:
            this.list.load();
            return;
        L14:
            if (isLoadingVisible() == true) goto L15;
            return;
        }

        public void fillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
            StarsController.GiftsList r132 = this.list;
            if (r132 != null) goto L6;
            return;
        L6:
            if (r132.hasFilters() == true) goto L8;
        L14:
            StarsController.GiftsList r133 = this.list;
            if (r133 == null) goto L20;
            int r134 = r133.totalCount;
            if (r134 == 0) goto L20;
            int r135 = Math.min(3, r134);
        L21:
            int r136 = Math.max(1, r135);
            StarsController.GiftsList r2 = this.list;
            if (r2 == null) goto L47;
            ArrayList<TL_stars.SavedStarGift> r22 = r2.gifts;
            int r4 = r22.size();
            int r5 = 3;
            int r6 = 0;
        L24:
            if (r6 >= r4) goto L36;
            TL_stars.SavedStarGift r7 = r22.get(r6);
            r6 = r6 + 1;
            TL_stars.SavedStarGift r72 = r7;
            UItem r8 = GiftSheet.GiftCell.Factory.asStarGift(0, r72, true, false, this.isCollection);
            if (this.reordering == true) goto L28;
        L32:
            boolean r73 = false;
        L33:
            r12.add(r8.setReordering(r73));
            r5 = r5 - 1;
            if (r5 != 0) goto L24;
            r5 = 3;
            goto L24
        L28:
            if (this.list == ProfileGiftsContainer.m14934$$Nest$fgetlist(this.parent)) goto L30;
        L31:
            r73 = true;
            goto L33
        L30:
            if (r72.pinned_to_top == false) goto L32;
        L36:
            StarsController.GiftsList r23 = this.list;
            if (r23.loading == false) goto L39;
        L40:
            int r24 = 0;
        L41:
            if (r5 > 0) goto L43;
            int r42 = 3;
        L44:
            if (r24 >= r42) goto L47;
            r24 = r24 + 1;
            r12.add(UItem.asFlicker(r24, 34).setSpanCount(1));
            goto L41
        L43:
            r42 = r5;
            goto L44
        L39:
            if (r23.endReached == false) goto L40;
        L47:
            if (ProfileGiftsContainer.m14934$$Nest$fgetlist(this.parent) != this.list) goto L53;
            r12.add(UItem.asSpace(AndroidUtilities.dp(20.0f)));
            if (ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L51;
            r12.add(TextFactory.asText(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider), 17, 14.0f, LocaleController.getString(R.string.ProfileGiftsInfo), false, AndroidUtilities.dp(24.0f), 0));
        L51:
            r12.add(UItem.asSpace(AndroidUtilities.dp(82.0f)));
        L56:
            if (r12.isEmpty() == true) goto L63;
            if (this.hasTabs == false) goto L60;
            float r0 = 42.0f;
        L61:
            r12.add(0, UItem.asSpace(AndroidUtilities.dp(r0)));
            goto L63
        L60:
            r0 = 12.0f;
        L63:
            if (this.listView.getSpanCount() == r136) goto L65;
            AndroidUtilities.runOnUIThread(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda22(this, r136));
        L65:
            ProfileGiftsContainer r122 = this.parent;
            if (r122 == null) goto L75;
            r122.updateTabsY();
            ProfileGiftsContainer r11 = this.parent;
            Objects.requireNonNull(r11);
            r11.post(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda23(r11));
            return;
        L75:
            return;
        L53:
            if (r12.isEmpty() == true) goto L56;
            r12.add(UItem.asSpace(AndroidUtilities.dp(82.0f)));
        L20:
            r135 = 3;
            goto L21
        L8:
            if (this.list.gifts.size() > 0) goto L14;
            StarsController.GiftsList r137 = this.list;
            if (r137.endReached == false) goto L14;
            if (r137.loading == true) goto L14;
        }

        public float getTabsHeight() {
            int r0 = 0;
        L4:
            if (r0 >= this.listView.getChildCount()) goto L14;
            View r1 = this.listView.getChildAt(r0);
            int r3 = this.listView.getChildAdapterPosition(r1);
            if ((r1 instanceof GiftSheet.GiftCell) == false) goto L10;
            if (r3 != 0) goto L13;
            return Math.max(0.0f, r1.getY());
        L13:
            r0 = r0 + 1;
            goto L4
        L10:
            if (r3 != 0) goto L13;
            return Math.max(0.0f, r1.getY() + (r1.getHeight() * r1.getAlpha()));
        L14:
            return 0.0f;
        }

        public boolean isReordering() {
            return this.reordering;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
        }

        public void onItemClick(UItem r7, View r8, int r9, float r10, float r11) {
            if (this.list == null) goto L30;
            Object r72 = r7.object;
            if ((r72 instanceof TL_stars.SavedStarGift) == false) goto L28;
            TL_stars.SavedStarGift r73 = (TL_stars.SavedStarGift) r72;
            if (this.reordering == true) goto L10;
            new StarGiftSheet(getContext(), this.currentAccount, ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent), this.resourcesProvider).setOnGiftUpdatedListener(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda4(this)).setOnBoughtGift(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda5(this, r73)).set(r73, this.list).show();
            return;
        L10:
            if (this.isCollection == false) goto L13;
            return;
        L13:
            if ((r73.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L32;
            boolean r82 = r73.pinned_to_top;
            boolean r92 = !r82;
            if (r82 == true) goto L21;
            if (r73.unsaved == false) goto L21;
            r73.unsaved = false;
            TL_stars.saveStarGift r112 = new TL_stars.saveStarGift();
            r112.stargift = this.list.getInput(r73);
            r112.unsave = r73.unsaved;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r112, null, 64);
        L21:
            if (this.list.togglePinned(r73, r92, true) == false) goto L23;
            BulletinFactory.of(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent)).createSimpleBulletin(R.raw.chats_infotip, LocaleController.formatPluralStringComma("GiftsPinLimit", MessagesController.getInstance(this.currentAccount).stargiftsPinnedToTopLimit)).show();
        L23:
            if (r82 == true) goto L29;
            this.listView.scrollToPosition(0);
            return;
        L29:
            return;
        L32:
            return;
        L28:
            return;
        }

        public boolean onItemLongPress(UItem r24, View r25, int r26, float r27, float r28) {
            int r7 = 0;
            if (this.list != null) goto L6;
            return false;
        L6:
            if ((r25 instanceof GiftSheet.GiftCell) == false) goto L107;
            Object r0 = r24.object;
            if ((r0 instanceof TL_stars.SavedStarGift) == false) goto L107;
            GiftSheet.GiftCell r8 = (GiftSheet.GiftCell) r25;
            TL_stars.SavedStarGift r4 = (TL_stars.SavedStarGift) r0;
            ItemOptions r5 = ItemOptions.makeOptions(ProfileGiftsContainer.m14933$$Nest$fgetfragment(this.parent), r25, true);
            ProfileGiftsContainer r02 = this.parent;
            r02.currentMenu = r5;
            if (r02.collections.isMine() == true) goto L12;
        L27:
            boolean r03 = r4.gift instanceof TL_stars.TL_starGiftUnique;
            ProfileGiftsContainer r2 = this.parent;
            if (r03 == false) goto L74;
            if (r2.canReorder() == false) goto L47;
            if (this.isCollection == true) goto L47;
            if (r4.unsaved == true) goto L36;
        L37:
            boolean r04 = r4.pinned_to_top;
            if (r04 == false) goto L40;
            int r22 = R.drawable.msg_unpin;
        L41:
            if (r04 == false) goto L44;
            int r05 = R.string.Gift2Unpin;
        L45:
            r5.add(r22, LocaleController.getString(r05), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda14(this, r4, r8, r25));
            r5.addIf(r4.pinned_to_top, R.drawable.tabs_reorder, LocaleController.getString(R.string.Gift2Reorder), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda15(this));
        L51:
            TL_stars.StarGift r06 = r4.gift;
            TL_stars.TL_starGiftUnique r23 = (TL_stars.TL_starGiftUnique) r06;
            if (r06.slug == null) goto L54;
            String r07 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/nft/" + r4.gift.slug;
        L56:
            if (StarGiftSheet.isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(r23.owner_id)) == false) goto L65;
            boolean r29 = StarGiftSheet.isWorn(this.currentAccount, r23);
            if (r29 == false) goto L60;
            int r3 = R.drawable.menu_takeoff;
        L61:
            if (r29 == false) goto L63;
            int r210 = R.string.Gift2Unwear;
        L64:
            r5.add(r3, LocaleController.getString(r210), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda17(this, r4));
            goto L65
        L63:
            r210 = R.string.Gift2Wear;
            goto L64
        L60:
            r3 = R.drawable.menu_wear;
        L65:
            if (r07 == null) goto L67;
            boolean r211 = true;
        L68:
            r5.addIf(r211, R.drawable.msg_link2, LocaleController.getString(R.string.CopyLink), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda18(this, r07));
            if (r07 == null) goto L71;
            boolean r08 = true;
        L72:
            r5.addIf(r08, R.drawable.msg_share, LocaleController.getString(R.string.ShareFile), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda19(this, r4));
        L79:
            if (StarGiftSheet.isMineWithActions(this.currentAccount, ProfileGiftsContainer.m14931$$Nest$fgetdialogId(this.parent)) == false) goto L88;
            boolean r09 = r4.unsaved;
            if (r09 == false) goto L83;
            int r212 = R.drawable.msg_message;
        L84:
            if (r09 == false) goto L86;
            int r010 = R.string.Gift2ShowGift;
        L87:
            r5.add(r212, LocaleController.getString(r010), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda8(this, r4, r8));
            goto L88
        L86:
            r010 = R.string.Gift2HideGift;
            goto L87
        L83:
            r212 = R.drawable.menu_hide_gift;
        L88:
            TL_stars.StarGift r011 = r4.gift;
            if ((r011 instanceof TL_stars.TL_starGiftUnique) == false) goto L96;
            if (DialogObject.getPeerDialogId(((TL_stars.TL_starGiftUnique) r011).owner_id) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L93;
            boolean r012 = true;
        L94:
            r5.addIf(r012, R.drawable.menu_transfer, LocaleController.getString(R.string.Gift2TransferOption), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda9(this, r4));
            goto L96
        L93:
            r012 = false;
        L96:
            if (this.parent.collections.isMine() == true) goto L98;
        L100:
            boolean r1 = true;
            boolean r13 = false;
        L102:
            if (r5.getItemsCount() > 0) goto L104;
            return r13;
        L104:
            r5.setGravity(5);
            r5.setBlur(r1);
            r5.allowMoveScrim();
            Point r013 = AndroidUtilities.displaySize;
            int r014 = Math.min(r013.x, r013.y);
            r5.animateToSize(r014 - AndroidUtilities.dp(32.0f), (int) (r014 * 0.6f));
            r5.hideScrimUnder();
            r5.forceBottom(true);
            r5.show();
            r8.imageView.getImageReceiver().startAnimation(true);
            return true;
        L98:
            if (this.isCollection == false) goto L100;
            r1 = true;
            r13 = false;
            r5.add(R.drawable.msg_removefolder, LocaleController.getString(R.string.Gift2RemoveFromCollection), true, new ProfileGiftsContainer$Page$$ExternalSyntheticLambda10(this, r4, r5)).makeMultiline(false).cutTextInFancyHalf();
            goto L102
        L71:
            r08 = false;
            goto L72
        L67:
            r211 = false;
            goto L68
        L54:
            r07 = null;
            goto L56
        L44:
            r05 = R.string.Gift2Pin;
            goto L45
        L40:
            r22 = R.drawable.msg_pin;
            goto L41
        L36:
            if (r4.pinned_to_top == false) goto L37;
        L47:
            if (this.parent.canReorder() == false) goto L51;
            if (this.isCollection == false) goto L51;
            r5.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.Gift2Reorder), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda16(this));
            goto L51
        L74:
            if (r2.canReorder() == false) goto L79;
            if (this.isCollection == false) goto L79;
            r5.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.Gift2Reorder), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda7(this));
            goto L79
        L12:
            if (this.isCollection == true) goto L14;
            this.parent.collections.getCollections().size();
        L14:
            ItemOptions r10 = r5.makeSwipeback();
            r10.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda6(r5));
            r10.addGap();
            AnonymousClass5 r015 = new AnonymousClass5(this, getContext());
            LinearLayout r11 = new LinearLayout(getContext());
            r015.addView(r11);
            r11.setOrientation(1);
            r10.addView(r015, LayoutHelper.createLinear(-1, -2));
            if ((this.parent.collections.getCollections().size() + 1) >= MessagesController.getInstance(this.currentAccount).config.stargiftsCollectionsLimit.get()) goto L17;
            ActionBarMenuSubItem r016 = new ActionBarMenuSubItem(getContext(), false, false, this.resourcesProvider);
            r016.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            int r213 = Theme.key_actionBarDefaultSubmenuItem;
            float r242 = 18.0f;
            r016.setColors(Theme.getColor(r213, this.resourcesProvider), Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, this.resourcesProvider));
            r016.setSelectorColor(Theme.multAlpha(Theme.getColor(r213, this.resourcesProvider), 0.12f));
            r016.setTextAndIcon(LocaleController.getString(R.string.Gift2NewCollection), R.drawable.menu_folder_add);
            r016.setOnClickListener(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda11(this, r5, r4));
            r11.addView(r016, LayoutHelper.createLinear(-1, -2));
        L18:
            ArrayList<TL_stars.TL_starGiftCollection> r9 = this.parent.collections.getCollections();
            int r15 = r9.size();
            int r017 = 0;
        L19:
            if (r017 >= r15) goto L26;
            int r16 = r017 + 1;
            TL_stars.TL_starGiftCollection r32 = r9.get(r017);
            boolean r214 = this.parent.collections.getListById(r32.collection_id).contains(r4);
            ActionBarMenuSubItem r17 = new ActionBarMenuSubItem(getContext(), 2, false, false, this.resourcesProvider);
            r17.setChecked(r214);
            r17.setPadding(AndroidUtilities.dp(r242), r7, AndroidUtilities.dp(r242), r7);
            int r12 = Theme.key_actionBarDefaultSubmenuItem;
            r17.setColors(Theme.getColor(r12, this.resourcesProvider), Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, this.resourcesProvider));
            r17.setSelectorColor(Theme.multAlpha(Theme.getColor(r12, this.resourcesProvider), 0.12f));
            if (r32.icon == null) goto L24;
            AnonymousClass6 r72 = new AnonymousClass6(this, 3, this.currentAccount, r32.icon);
            r72.addViewListening(r17.getImageView());
            r17.setTextAndIcon(r32.title, 0, r72);
        L25:
            r17.setOnClickListener(new ProfileGiftsContainer$Page$$ExternalSyntheticLambda12(this, r214, r32, r4, r5));
            r11.addView(r17, LayoutHelper.createLinear(-1, -2));
            r017 = r16;
            r7 = 0;
            goto L19
        L24:
            r17.setTextAndIcon(r32.title, R.drawable.msg_folders);
            goto L25
        L26:
            r5.add(R.drawable.msg_addfolder, LocaleController.getString(R.string.Gift2AddToCollection), new ProfileGiftsContainer$Page$$ExternalSyntheticLambda13(r5, r10));
            r5.addGap();
            goto L27
        L17:
            r242 = 18.0f;
        L107:
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setVisibleHeight(this.visibleHeight);
        }

        public void resetReordering() {
            if (this.reordering == true) goto L5;
            return;
        L5:
            StarsController.GiftsList r0 = this.list;
            if (r0 == null) goto L8;
            r0.sendPinnedOrder();
        L8:
            setReordering(false);
        }

        public void setHasTabs(boolean r3) {
            if (this.hasTabs != r3) goto L5;
            return;
        L5:
            this.hasTabs = r3;
            boolean r32 = this.listView.canScrollVertically(-1);
            this.listView.adapter.update(true);
            if (r32 == true) goto L8;
            this.listView.scrollToPosition(0);
        L8:
            this.parent.updateTabsY();
        }

        public void setVisibleHeight(int r6) {
            this.visibleHeight = r6;
            float r62 = Utilities.clamp01(AndroidUtilities.ilerp(r6, AndroidUtilities.dp(150.0f), AndroidUtilities.dp(220.0f)));
            float r0 = AndroidUtilities.lerp(0.6f, 1.0f, r62);
            LinearLayout r1 = this.emptyView1Layout;
            if (r1 == null) goto L5;
            r1.setAlpha(r62);
            this.emptyView1Layout.setScaleX(r0);
            this.emptyView1Layout.setScaleY(r0);
        L5:
            FrameLayout r12 = this.emptyView1;
            if (r12 == null) goto L8;
            r12.setTranslationY((-(getMeasuredHeight() - this.visibleHeight)) / 2.0f);
        L8:
            LinearLayout r13 = this.emptyView2Layout;
            if (r13 == null) goto L11;
            r13.setAlpha(r62);
            this.emptyView2Layout.setScaleX(r0);
            this.emptyView2Layout.setScaleY(r0);
        L11:
            FrameLayout r63 = this.emptyView2;
            if (r63 == null) goto L15;
            r63.setTranslationY((-(getMeasuredHeight() - this.visibleHeight)) / 2.0f);
            return;
        }

        public void update(boolean r3) {
            UniversalRecyclerView r0 = this.listView;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.adapter == null) goto L13;
            boolean r02 = r0.canScrollVertically(-1);
            this.listView.adapter.update(r3);
            if (r02 == true) goto L12;
            this.listView.scrollToPosition(0);
            return;
        L12:
            return;
        }

        public void updateColors() {
            if (this.emptyView1 == null) goto L6;
            this.emptyView1Title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            TextView r0 = this.emptyView1Button;
            int r1 = Theme.key_featuredStickers_addButton;
            r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            this.emptyView1Button.setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.1f), 4, 4));
            return;
        L6:
            this.emptyView2Title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.emptyView2Subtitle.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider));
            this.emptyView2Button.updateColors();
        }
    }

    public static class TextFactory extends UItem.UItemFactory<LinkSpanDrawable.LinksTextView> {

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$TextFactory$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
            final /* synthetic */ TextFactory this$0;

            public AnonymousClass1(TextFactory r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
            }
        }

        static {
            UItem.UItemFactory.setup(new TextFactory());
        }

        public TextFactory() {
        }

        public static UItem asText(int r1, int r2, float r3, CharSequence r4, boolean r5, int r6, int r7) {
            UItem r0 = UItem.ofFactory(TextFactory.class);
            r0.text = r4;
            r0.intValue = r2;
            r0.longValue = r1;
            r0.floatValue = r3;
            r0.pad = r6;
            r0.iconResId = r7;
            r0.accent = r5;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            LinkSpanDrawable.LinksTextView r12 = (LinkSpanDrawable.LinksTextView) r1;
            r12.setGravity(r2.intValue);
            r12.setTextColor((int) r2.longValue);
            r12.setTextSize(1, r2.floatValue);
            if (r2.accent == false) goto L5;
            Typeface r0 = AndroidUtilities.bold();
        L6:
            r12.setTypeface(r0);
            int r02 = r2.pad;
            r12.setPadding(r02, 0, r02, r2.iconResId);
            r12.setText(r2.text);
            return;
        L5:
            r0 = null;
            goto L6
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public LinkSpanDrawable.LinksTextView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new AnonymousClass1(this, r1);
        }
    }

    public static class UnpinSheet extends BottomSheet {
        long selectedGift;

        /* renamed from: org.telegram.ui.Gifts.ProfileGiftsContainer$UnpinSheet$1, reason: invalid class name */
        public class AnonymousClass1 extends UniversalRecyclerView {
            final /* synthetic */ UnpinSheet this$0;

            public AnonymousClass1(UnpinSheet r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.RecyclerListView
            public Integer getSelectorColor(int r1) {
                return 0;
            }
        }

        public static /* synthetic */ void $r8$lambda$FXBTuScgBzl6Pr4J46noBekenNs(UnpinSheet r0, StarsController.GiftsList r1, ArrayList r2, UniversalAdapter r3) {
            r0.lambda$new$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$dYFPZBO4wu3QSRLMJEf3TstmYmc(UnpinSheet r0, StarsController.GiftsList r1, TL_stars.SavedStarGift r2, Utilities.Callback0Return r3, View r4) {
            r0.lambda$new$2(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$mrYWyEfd-lt-wSEjCdhPscDMBMc, reason: not valid java name */
        public static /* synthetic */ void m14957$r8$lambda$mrYWyEfdltwSEjCdhPscDMBMc(UnpinSheet r0, ButtonWithCounterView r1, UItem r2, View r3, Integer r4, Float r5, Float r6) {
            r0.lambda$new$1(r1, r2, r3, r4, r5, r6);
        }

        public UnpinSheet(Context r18, long r19, TL_stars.SavedStarGift r21, Theme.ResourcesProvider r22, Utilities.Callback0Return<BulletinFactory> r23) {
            super(r18, false, r22);
            this.selectedGift = 0;
            fixNavigationBar();
            LinearLayout r10 = new LinearLayout(r18);
            r10.setOrientation(1);
            TextView r0 = TextHelper.makeTextView(r18, 20.0f, Theme.key_windowBackgroundWhiteBlackText, true, r22);
            r0.setText(LocaleController.getString(R.string.Gift2UnpinAlertTitle));
            r10.addView(r0, LayoutHelper.createLinear(-1, -2, 22.0f, 12.0f, 22.0f, 0.0f));
            TextView r02 = TextHelper.makeTextView(r18, 14.0f, Theme.key_windowBackgroundWhiteGrayText, false, r22);
            r02.setText(LocaleController.getString(R.string.Gift2UnpinAlertSubtitle));
            r10.addView(r02, LayoutHelper.createLinear(-1, -2, 22.0f, 4.33f, 22.0f, 10.0f));
            ButtonWithCounterView r11 = new ButtonWithCounterView(r18, r22);
            StarsController.GiftsList r12 = StarsController.getInstance(this.currentAccount).getProfileGiftsList(r19);
            AnonymousClass1 r03 = new AnonymousClass1(this, r18, this.currentAccount, 0, new ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda0(this, r12), new ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda1(this, r11), null, r22);
            r03.setSpanCount(3);
            r03.setOverScrollMode(2);
            r03.setScrollEnabled(false);
            r10.addView(r03, LayoutHelper.createLinear(-1, -2, 11.0f, 0.0f, 11.0f, 0.0f));
            r11.setText(LocaleController.getString(R.string.Gift2UnpinAlertButton), false);
            r10.addView(r11, LayoutHelper.createLinear(-1, 48, 22.0f, 9.0f, 22.0f, 9.0f));
            r11.setEnabled(false);
            r11.setOnClickListener(new ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda2(this, r12, r21, r23));
            setCustomView(r10);
        }

        private /* synthetic */ void lambda$new$0(StarsController.GiftsList r9, ArrayList r10, UniversalAdapter r11) {
            ArrayList<TL_stars.SavedStarGift> r92 = r9.gifts;
            int r112 = r92.size();
            int r1 = 0;
        L3:
            if (r1 >= r112) goto L11;
            TL_stars.SavedStarGift r2 = r92.get(r1);
            r1 = r1 + 1;
            TL_stars.SavedStarGift r22 = r2;
            if (r22.pinned_to_top == false) goto L3;
            UItem r3 = PeerColorActivity.GiftCell.Factory.asGiftCell(r22);
            if (this.selectedGift != r22.gift.id) goto L9;
            boolean r23 = true;
        L10:
            r10.add(r3.setChecked(r23).setSpanCount(1));
            goto L3
        L9:
            r23 = false;
            goto L10
        }

        private /* synthetic */ void lambda$new$1(ButtonWithCounterView r5, UItem r6, View r7, Integer r8, Float r9, Float r10) {
            long r82 = ((TL_stars.SavedStarGift) r6.object).gift.id;
            if (this.selectedGift != r82) goto L5;
            this.selectedGift = 0;
        L7:
            if (this.selectedGift == 0) goto L9;
            boolean r62 = true;
        L10:
            r5.setEnabled(r62);
            if ((r7.getParent() instanceof ViewGroup) == false) goto L23;
            ViewGroup r52 = (ViewGroup) r7.getParent();
            int r63 = 0;
        L14:
            if (r63 >= r52.getChildCount()) goto L27;
            View r72 = r52.getChildAt(r63);
            if ((r72 instanceof PeerColorActivity.GiftCell) == false) goto L22;
            PeerColorActivity.GiftCell r73 = (PeerColorActivity.GiftCell) r72;
            if (this.selectedGift != r73.getGiftId()) goto L20;
            boolean r102 = true;
        L21:
            r73.setSelected(r102, true);
            goto L22
        L20:
            r102 = false;
        L22:
            r63 = r63 + 1;
            goto L14
        L27:
            return;
        L23:
            return;
        L9:
            r62 = false;
            goto L10
        L5:
            this.selectedGift = r82;
            goto L7
        }

        private /* synthetic */ void lambda$new$2(StarsController.GiftsList r7, TL_stars.SavedStarGift r8, Utilities.Callback0Return r9, View r10) {
            ArrayList<TL_stars.SavedStarGift> r102 = r7.getPinned();
            int r1 = 0;
        L4:
            if (r1 >= r102.size()) goto L9;
            if (r102.get(r1).gift.id == this.selectedGift) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            TL_stars.SavedStarGift r2 = r102.get(r1);
        L10:
            if (r2 != null) goto L12;
            return;
        L12:
            r2.pinned_to_top = false;
            r102.set(r1, r8);
            r8.pinned_to_top = true;
            r7.setPinned(r102);
            dismiss();
            ((BulletinFactory) r9.run()).createSimpleBulletin(R.raw.ic_pin, LocaleController.formatString(R.string.Gift2ReplacedPinTitle, new Object[]{StarGiftSheet.getGiftName(r8.gift)}), LocaleController.formatString(R.string.Gift2ReplacedPinSubtitle, new Object[]{StarGiftSheet.getGiftName(r2.gift)})).show();
            return;
        L9:
            r1 = -1;
            r2 = null;
            goto L10
        }
    }

    public static /* synthetic */ void $r8$lambda$0ExCHB2qa2NiDqdBnwan1uXcG1A(ProfileGiftsContainer r0) {
        r0.lambda$new$12();
    }

    public static /* synthetic */ void $r8$lambda$1ee1LjmpysGeJLPt5p0M8BcnUwM(ProfileGiftsContainer r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.lambda$new$14(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$6gNdInxBVLmXzwGabzY9seUAg6k(ProfileGiftsContainer r0, EditTextCaption r1, Activity r2, DialogInterface r3) {
        r0.lambda$openEnterNameAlert$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ESIyPBchTZKskIVx5Yk1VRMSXqQ(ProfileGiftsContainer r0, Canvas r1, RectF r2) {
        r0.lambda$initBlurCapture$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EYS0j3tFmPHxAhotTj5_z9Jm_zk(ProfileGiftsContainer r0, int r1, Page r2, ArrayList r3) {
        r0.lambda$addGifts$21(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$GMpwj5Ts3mHYDueT_KEGE5qeZfQ(ProfileGiftsContainer r0, TL_stars.TL_starGiftCollection r1) {
        r0.lambda$createCollection$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$HGXLJ2bQdDYNtuoDGUnONnEuUXI(EditTextCaption r2, Utilities.Callback r3, AlertDialog r4, int r5) {
        String r52 = r2.getText().toString();
        if (r52.length() > 0) goto L5;
    L9:
        AndroidUtilities.shakeView(r2);
        return;
    L5:
        if (r52.length() > 12) goto L9;
        r3.run(r52);
        r4.dismiss();
    }

    /* renamed from: $r8$lambda$PDJWy25l-XRdVtRGPrzAh8_Svq0, reason: not valid java name */
    public static /* synthetic */ void m14922$r8$lambda$PDJWy25lXRdVtRGPrzAh8_Svq0(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$PiflF7dP_XL2XId7FDu0rHYSHs0(ProfileGiftsContainer r0, BaseFragment r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$9(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$QVcXD97-c7Dv-c_xpBgbco0INzM, reason: not valid java name */
    public static /* synthetic */ void m14923$r8$lambda$QVcXD97c7Dvc_xpBgbco0INzM(Page r1, int r2) {
        Page.m14952$$Nest$fgetlistView(r1).scrollBy(0, r2);
    }

    /* renamed from: $r8$lambda$Y_hy7wx-b_R6l3jq-f9bz9a6WDA, reason: not valid java name */
    public static /* synthetic */ void m14924$r8$lambda$Y_hy7wxb_R6l3jqf9bz9a6WDA(EditTextCaption r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ boolean $r8$lambda$bEzDNpga9c41IzwYlyeh3jZ7Fbo(StarsController.GiftsList r0, int r1, Runnable r2, View r3) {
        r0.forceTypeIncludeFlag(r1, true);
        r2.run();
        return true;
    }

    /* renamed from: $r8$lambda$cQlluNxrZ_MzLWpB54LUlYuWz-Y, reason: not valid java name */
    public static /* synthetic */ Boolean m14925$r8$lambda$cQlluNxrZ_MzLWpB54LUlYuWzY(ProfileGiftsContainer r0, Integer r1, Integer r2) {
        return r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ Boolean $r8$lambda$dkHZH0MrRxrCHzdgqlEWIsLrSQo(ProfileGiftsContainer r0, int r1, BaseFragment r2, Context r3, Theme.ResourcesProvider r4, Integer r5, View r6) {
        return r0.lambda$new$7(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$efZ8a2puKJJaEOtEfUskLcHKX1U(ProfileGiftsContainer r0, TLRPC.TL_error r1, BaseFragment r2) {
        r0.lambda$new$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$f9ksTmDUAzgQSn2BZ7GDxk7YS8g(ProfileGiftsContainer r0, TL_stars.TL_starGiftCollection r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$i8HPAreaJhMObMdMoUxFKqye6hg(ProfileGiftsContainer r0, boolean r1, int r2, View r3) {
        r0.lambda$new$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$kpih-kMUdIQNLn3ZRWv3Iie32bA, reason: not valid java name */
    public static /* synthetic */ void m14926$r8$lambda$kpihkMUdIQNLn3ZRWv3Iie32bA(BaseFragment r1) {
        ((ProfileActivity) r1).scrollToSharedMedia(true);
    }

    /* renamed from: $r8$lambda$mUgy-IzyBmMB9T5WRvkdr9jR_7g, reason: not valid java name */
    public static /* synthetic */ void m14927$r8$lambda$mUgyIzyBmMB9T5WRvkdr9jR_7g(ProfileGiftsContainer r0, int r1, String r2, TL_stars.TL_starGiftCollection r3, Context r4, Theme.ResourcesProvider r5, BaseFragment r6) {
        r0.lambda$new$2(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$mtoQEMJ-pkk1uam7V8B1Vb8JrQE, reason: not valid java name */
    public static /* synthetic */ void m14928$r8$lambda$mtoQEMJpkk1uam7V8B1Vb8JrQE(ProfileGiftsContainer r0) {
        r0.lambda$new$5();
    }

    public static /* synthetic */ void $r8$lambda$ni4K4h_wnetxIAY9FyDYS8jyRt8(ProfileGiftsContainer r0, BaseFragment r1, int r2, View r3) {
        r0.lambda$new$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$tG0_7QHGLKjmgVicLbI9g1UvoKo(ProfileGiftsContainer r0, String r1) {
        r0.lambda$createCollection$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$tYVcLJaoSuZkHNIzrEi_rPUek2E(StarsController.GiftsList r0, int r1, Runnable r2, View r3) {
        r0.toggleTypeIncludeFlag(r1);
        r2.run();
    }

    public static /* synthetic */ void $r8$lambda$wsNxAcafa1REuCWUn53tXUYeKbQ(ProfileGiftsContainer r0, int r1, TL_stars.TL_starGiftCollection r2) {
        r0.lambda$new$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zNeVEn3Wnf1gUlCW9bCeOj8aYG4(ProfileGiftsContainer r0, TL_stars.TL_starGiftCollection r1, String r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaddCollectionTabText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m14929$$Nest$fgetaddCollectionTabText(ProfileGiftsContainer r0) {
        return r0.addCollectionTabText;
    }

    /* renamed from: -$$Nest$fgetbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14930$$Nest$fgetbackgroundColor(ProfileGiftsContainer r0) {
        return r0.backgroundColor;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m14931$$Nest$fgetdialogId(ProfileGiftsContainer r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetexternalPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14932$$Nest$fgetexternalPaddingTop(ProfileGiftsContainer r0) {
        return r0.externalPaddingTop;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m14933$$Nest$fgetfragment(ProfileGiftsContainer r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetlist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsController.GiftsList m14934$$Nest$fgetlist(ProfileGiftsContainer r0) {
        return r0.list;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m14935$$Nest$fgetresourcesProvider(ProfileGiftsContainer r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsendCollectionsOrder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m14936$$Nest$fgetsendCollectionsOrder(ProfileGiftsContainer r0) {
        return r0.sendCollectionsOrder;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed.TabsView m14937$$Nest$fgettabsView(ProfileGiftsContainer r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$fgetvisibleHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14938$$Nest$fgetvisibleHeight(ProfileGiftsContainer r0) {
        return r0.visibleHeight;
    }

    /* renamed from: -$$Nest$fputaddCollectionTabText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14939$$Nest$fputaddCollectionTabText(ProfileGiftsContainer r0, CharSequence r1) {
        r0.addCollectionTabText = r1;
    }

    /* renamed from: -$$Nest$mfillTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14940$$Nest$mfillTabs(ProfileGiftsContainer r0, boolean r1) {
        r0.fillTabs(r1);
    }

    /* renamed from: -$$Nest$mopenEnterNameAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14941$$Nest$mopenEnterNameAlert(ProfileGiftsContainer r0, String r1, Utilities.Callback r2) {
        r0.openEnterNameAlert(r1, r2);
    }

    static {
        cachedLastEmojis = new HashMap();
    }

    public ProfileGiftsContainer(BaseFragment r26, Context r27, int r28, long r29, Theme.ResourcesProvider r31) {
        super(r27);
        this.checkboxRequestId = -1;
        this.sendCollectionsOrder = new ProfileGiftsContainer$$ExternalSyntheticLambda3(this);
        this.animatorBottomButtonVisibility = new BoolAnimator(0, new ProfileGiftsContainer$$ExternalSyntheticLambda4(this), CubicBezierInterpolator.EASE_OUT_QUINT, 380, true);
        this.visibleHeight = AndroidUtilities.displaySize.y;
        this.fragment = r26;
        this.currentAccount = r28;
        if (DialogObject.isEncryptedDialog(r29) == false) goto L8;
        TLRPC.EncryptedChat r8 = MessagesController.getInstance(r28).getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r29)));
        if (r8 == null) goto L7;
        this.dialogId = r8.user_id;
    L9:
        StarsController.getInstance(r28).invalidateProfileGifts(this.dialogId);
        StarsController.GiftsList r6 = StarsController.getInstance(r28).getProfileGiftsList(this.dialogId);
        this.list = r6;
        StarsController.GiftsCollections r5 = StarsController.getInstance(r28).getProfileGiftCollectionsList(this.dialogId, true);
        this.collections = r5;
        r5.all = r6;
        r6.shown = true;
        if ((r26 instanceof ProfileActivity) == true) goto L12;
    L14:
        r6.resetFilters();
    L15:
        r6.load();
        this.resourcesProvider = r31;
        AnonymousClass1 r52 = new AnonymousClass1(this, r27, r26);
        this.viewPager = r52;
        r52.setAllowDisallowInterceptTouch(true);
        r52.setAdapter(new AnonymousClass2(this, r28, r31));
        addView(r52, LayoutHelper.createFrame(-1, -1, 119));
        ViewPagerFixed.TabsView r11 = r52.createTabsView(true, 10);
        this.tabsView = r11;
        r11.setColors(Theme.key_profile_tabSelectedLine, Theme.key_windowBackgroundWhiteBlackText, Theme.key_profile_tabText, Theme.key_profile_tabSelector, Theme.key_actionBarDefault);
        r11.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r11.setClipToPadding(false);
        r11.tabMarginDp = 12;
        r11.setPreTabClick(new ProfileGiftsContainer$$ExternalSyntheticLambda5(this));
        r11.setOnTabLongClick(new ProfileGiftsContainer$$ExternalSyntheticLambda6(this, r28, r26, r27, r31));
        addView(r11, LayoutHelper.createFrame(-1, 42, 48));
        BlurredBackgroundSourceColor r53 = new BlurredBackgroundSourceColor();
        int r112 = Theme.key_windowBackgroundWhite;
        r53.setColor(Theme.getColor(r112, r31));
        BlurredBackgroundDrawableViewFactory r12 = new BlurredBackgroundDrawableViewFactory(r53);
        ProfileActivity.Button2 r54 = new ProfileActivity.Button2(r27);
        BlurredBackgroundDrawable r113 = r12.create(r54, new BlurredBackgroundColorProviderThemed(r31, r112));
        r113.setPadding(AndroidUtilities.dp(8.0f));
        r113.setRadius(AndroidUtilities.dp(22.0f));
        r54.setBackground(r113);
        ScaleStateListAnimator.apply(r54, 0.02f, 1.2f);
        FrameLayout r114 = new FrameLayout(r27);
        this.buttonContainer = r114;
        FrameLayout.LayoutParams r15 = LayoutHelper.createFrame(-1, 60, 87);
        r15.bottomMargin += AndroidUtilities.navigationBarHeight;
        addView(r114, r15);
        r114.addView(r54, LayoutHelper.createFrame(-2, 60, 1));
        FrameLayout r115 = new FrameLayout(r27);
        this.bulletinContainer = r115;
        LinearLayout r152 = new LinearLayout(r27);
        this.checkboxLayout = r152;
        r152.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        r152.setClipToPadding(false);
        r152.setOrientation(0);
        r152.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r31), 24, 24));
        CheckBox2 r7 = new CheckBox2(r27, 24, r31);
        this.checkbox = r7;
        r7.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        r7.setDrawUnchecked(true);
        r7.setChecked(false, false);
        r7.setDrawBackgroundAsArc(10);
        r152.addView(r7, LayoutHelper.createLinear(26, 26, 16, 0, 0, 0, 0));
        TextView r82 = new TextView(r27);
        this.checkboxTextView = r82;
        r82.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r31));
        r82.setTextSize(1, 14.0f);
        r82.setText(LocaleController.getString(R.string.Gift2ChannelNotify));
        r152.addView(r82, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
        r54.addView(r152, LayoutHelper.createFrame(-2, 38.0f, 17, 0.0f, 6.0f, 0.0f, 6.0f));
        ScaleStateListAnimator.apply(r152, 0.025f, 1.5f);
        r152.setOnClickListener(new ProfileGiftsContainer$$ExternalSyntheticLambda7(this, r26, r28));
        Boolean r3 = r6.chat_notifications_enabled;
        if (r3 == null) goto L18;
        r7.setChecked(r3.booleanValue(), false);
    L18:
        TLRPC.User r32 = MessagesController.getInstance(r28).getUser(Long.valueOf(this.dialogId));
        if (this.dialogId < 0) goto L27;
        if (r32 != null) goto L22;
    L26:
        boolean r33 = false;
    L28:
        StringBuilder r72 = new StringBuilder("G ");
        if (r33 == false) goto L35;
        long r83 = this.dialogId;
        if (r83 >= 0) goto L34;
        int r84 = R.string.ProfileGiftsSendChannel;
    L33:
        String r85 = LocaleController.getString(r84);
    L36:
        r72.append(r85);
        SpannableStringBuilder r62 = new SpannableStringBuilder(r72.toString());
        r62.setSpan(new ColoredImageSpan(R.drawable.filled_gift_simple), 0, 1, 33);
        this.sendGiftsToFriendsText = r62;
        SpannableStringBuilder r73 = new SpannableStringBuilder("+ " + LocaleController.getString(R.string.ProfileGiftsAdd));
        r73.setSpan(new ColoredImageSpan(R.drawable.filled_add_album), 0, 1, 33);
        this.addGiftsText = r73;
        ButtonWithCounterView r74 = new ButtonWithCounterView(r27, r31);
        this.button = r74;
        r74.setUseWrapContent(true);
        r74.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r74.setRoundRadius(AndroidUtilities.dp(19.0f));
        r74.setText(r62, false);
        r74.setStateListAnimator(null);
        r54.addView(r74, LayoutHelper.createFrame(-2, -1, 17));
        r54.setOnClickListener(new ProfileGiftsContainer$$ExternalSyntheticLambda8(this, r33, r28));
        int r2 = 8;
        if (canSwitchNotify() == false) goto L39;
        int r0 = 8;
    L40:
        r74.setVisibility(r0);
        if (canSwitchNotify() == false) goto L43;
        r2 = 0;
    L43:
        r152.setVisibility(r2);
        this.buttonContainerHeightDp = 60;
        addView(r115, LayoutHelper.createFrame(-1, 200, 87));
        updateColors();
        updateTabsShown(false);
        return;
    L39:
        r0 = 0;
        goto L40
    L34:
        r85 = LocaleController.formatString(R.string.ProfileGiftsSendUser, new Object[]{DialogObject.getShortName(r83)});
        goto L36
    L35:
        r84 = R.string.ProfileGiftsSend;
        goto L33
    L22:
        if (UserObject.isUserSelf(r32) == true) goto L26;
        if (UserObject.isBot(r32) == true) goto L26;
    L27:
        r33 = true;
        goto L28
    L12:
        if (((ProfileActivity) r26).openGiftsUpgradable == false) goto L14;
        r6.setFilters(4);
        goto L15
    L7:
        this.dialogId = r29;
        goto L9
    L8:
        this.dialogId = r29;
        goto L9
    }

    private void checkScrollToCollection() {
        if (this.pendingScrollToCollectionId <= 0) goto L21;
        ArrayList<TL_stars.TL_starGiftCollection> r0 = this.collections.getCollections();
        int r2 = 0;
    L7:
        if (r2 >= r0.size()) goto L12;
        if (r0.get(r2).collection_id == this.pendingScrollToCollectionId) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        TL_stars.TL_starGiftCollection r02 = r0.get(r2);
    L13:
        if (r2 < 0) goto L19;
        if (r02 == null) goto L20;
        this.pendingScrollToCollectionId = 0;
        this.tabsView.scrollToTab(r02.collection_id, r2 + 1);
        return;
    L20:
        return;
    L19:
        return;
    L12:
        r2 = -1;
        r02 = null;
        goto L13
    }

    private void fillTabs(boolean r3) {
        ViewPagerFixed r0 = this.viewPager;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.tabsView == null) goto L10;
        r0.fillTabs(r3);
        checkScrollToCollection();
        return;
    }

    private float hasTabs() {
        if (this.viewPager.getViewPages() == null) goto L14;
        View[] r7 = this.viewPager.getViewPages();
        int r0 = r7.length;
        int r3 = 0;
        float r4 = 0.0f;
    L5:
        if (r3 >= r0) goto L16;
        View r5 = r7[r3];
        if ((r5 instanceof Page) == false) goto L13;
        if (Page.m14951$$Nest$fgethasTabs((Page) r5) == false) goto L11;
        float r52 = 1.0f;
    L12:
        r4 = r4 + r52;
        goto L13
    L11:
        r52 = 0.0f;
    L13:
        r3 = r3 + 1;
    L16:
        return MathUtils.clamp(r4, 0.0f, 1.0f);
    L14:
        r4 = 0.0f;
        goto L16
    }

    private /* synthetic */ void lambda$addGifts$21(int r3, Page r4, ArrayList r5) {
        this.collections.addGifts(r3, r5, true);
        r4.update(true);
        fillTabs(true);
        updateTabsShown(true);
        TL_stars.TL_starGiftCollection r32 = this.collections.findById(r3);
        if (r32 != null) goto L5;
        return;
    L5:
        if (r5.size() <= 1) goto L9;
        TL_stars.SavedStarGift r42 = (TL_stars.SavedStarGift) r5.get(0);
        Bulletin r2 = BulletinFactory.of(this.fragment).createSimpleMultiBulletin(r42.gift.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2AddedToCollectionMany", r5.size(), new Object[]{r32.title})));
        r2.hideAfterBottomSheet = false;
        r2.show();
        return;
    L9:
        if (r5.size() != 1) goto L13;
        TL_stars.SavedStarGift r43 = (TL_stars.SavedStarGift) r5.get(0);
        Bulletin r22 = BulletinFactory.of(this.fragment).createSimpleMultiBulletin(r43.gift.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2AddedToCollection, new Object[]{StarGiftSheet.getGiftName(r43.gift), r32.title})));
        r22.hideAfterBottomSheet = false;
        r22.show();
        return;
    }

    private /* synthetic */ void lambda$createCollection$19(TL_stars.TL_starGiftCollection r4) {
        fillTabs(true);
        ViewPagerFixed.TabsView r1 = this.tabsView;
        int r42 = r4.collection_id;
        r1.scrollToTab(r42, this.collections.indexOf(r42) + 1);
        BaseFragment r43 = this.fragment;
        if ((r43 instanceof ProfileActivity) == false) goto L5;
        ((ProfileActivity) r43).scrollToSharedMedia(true);
    L5:
        updateTabsShown(true);
    }

    private /* synthetic */ void lambda$createCollection$20(String r3) {
        this.collections.createCollection(r3, new ProfileGiftsContainer$$ExternalSyntheticLambda25(this));
    }

    private /* synthetic */ void lambda$initBlurCapture$24(Canvas r10, RectF r11) {
        View[] r0 = this.viewPager.getViewPages();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L11;
        View r3 = r0[r2];
        if ((r3 instanceof Page) == false) goto L10;
        Page r32 = (Page) r3;
        if (r32.iBlur3Capture != null) goto L9;
        UniversalRecyclerView r5 = Page.m14952$$Nest$fgetlistView(r32);
        ViewGroup r6 = this.iBlur3CaptureParent;
        UniversalRecyclerView r7 = Page.m14952$$Nest$fgetlistView(r32);
        Objects.requireNonNull(r7);
        r32.iBlur3Capture = new ViewGroupPartRenderer(r5, r6, new CallLogActivity$$ExternalSyntheticLambda6(r7));
    L9:
        r32.iBlur3Capture.capture(r10, r11);
    L10:
        r2 = r2 + 1;
        goto L3
    }

    private /* synthetic */ Boolean lambda$new$1(Integer r1, Integer r2) {
        resetReordering();
        if (r1.intValue() != (-1)) goto L7;
        createCollection();
        return Boolean.TRUE;
    L7:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$new$10(BaseFragment r5, int r6, View r7) {
        this.checkbox.setChecked(!r7.isChecked(), true);
        boolean r72 = this.checkbox.isChecked();
        BulletinFactory r0 = BulletinFactory.of(r5);
        if (r72 == false) goto L5;
        int r2 = R.raw.silent_unmute;
    L6:
        if (r72 == false) goto L8;
        int r3 = R.string.Gift2ChannelNotifyChecked;
    L9:
        r0.createSimpleBulletinDetail(r2, LocaleController.getString(r3)).show();
        this.list.chat_notifications_enabled = Boolean.valueOf(r72);
        if (this.checkboxRequestId < 0) goto L12;
        ConnectionsManager.getInstance(r6).cancelRequest(this.checkboxRequestId, true);
        this.checkboxRequestId = -1;
    L12:
        TL_stars.toggleChatStarGiftNotifications r02 = new TL_stars.toggleChatStarGiftNotifications();
        r02.peer = MessagesController.getInstance(r6).getInputPeer(this.dialogId);
        r02.enabled = r72;
        ConnectionsManager.getInstance(r6).sendRequest(r02, new ProfileGiftsContainer$$ExternalSyntheticLambda17(this, r5));
        return;
    L8:
        r3 = R.string.Gift2ChannelNotifyNotChecked;
        goto L9
    L5:
        r2 = R.raw.silent_mute;
        goto L6
    }

    private /* synthetic */ void lambda$new$11(boolean r8, int r9, View r10) {
        if (this.collections.isMine() == true) goto L5;
    L9:
        if (r8 == false) goto L12;
        new GiftSheet(getContext(), r9, this.dialogId, null, null).setBirthday(BirthdayController.getInstance(r9).isToday(this.dialogId)).show();
        return;
    L12:
        UserSelectorBottomSheet.open(2, 0, BirthdayController.getInstance(r9).getState());
        return;
    L5:
        if (this.viewPager.getCurrentPosition() == 0) goto L9;
        addGifts();
    }

    private /* synthetic */ void lambda$new$12() {
        this.collections.sendOrder();
    }

    private /* synthetic */ void lambda$new$14(int r1, float r2, float r3, FactorAnimator r4) {
        updateButton();
    }

    private /* synthetic */ void lambda$new$2(int r12, String r13, TL_stars.TL_starGiftCollection r14, Context r15, Theme.ResourcesProvider r16, BaseFragment r17) {
        String r5 = MessagesController.getInstance(r12).linkPrefix + "/" + r13 + "/c/" + r14.collection_id;
        new AnonymousClass4(this, r15, null, r5, false, r5, false, r16, r17).show();
    }

    private /* synthetic */ void lambda$new$3(TL_stars.TL_starGiftCollection r3, String r4) {
        this.collections.rename(r3.collection_id, r4);
        r3.title = r4;
        fillTabs(true);
    }

    private /* synthetic */ void lambda$new$4(TL_stars.TL_starGiftCollection r3) {
        openEnterNameAlert(r3.title, new ProfileGiftsContainer$$ExternalSyntheticLambda23(this, r3));
    }

    private /* synthetic */ void lambda$new$5() {
        setReorderingCollections(true);
    }

    private /* synthetic */ void lambda$new$6(int r4, TL_stars.TL_starGiftCollection r5) {
        if (r4 == (-1)) goto L10;
        this.collections.removeCollection(r5.collection_id);
        fillTabs(true);
        ViewPagerFixed.TabsView r1 = this.tabsView;
        if (r4 >= this.collections.getCollections().size()) goto L8;
        r4 = r4 + 1;
    L8:
        r1.scrollToTab(-1, r4);
        updateTabsShown(true);
        return;
    }

    private /* synthetic */ Boolean lambda$new$7(int r15, BaseFragment r16, Context r17, Theme.ResourcesProvider r18, Integer r19, View r20) {
        int r1 = -1;
        if (r19.intValue() == (-1)) goto L27;
        if (r19.intValue() == (-2)) goto L27;
        if (r19.intValue() == 0) goto L27;
        if (this.reorderingCollections == true) goto L27;
        int r0 = 0;
    L13:
        if (r0 >= this.collections.getCollections().size()) goto L18;
        if (this.collections.getCollections().get(r0).collection_id == r19.intValue()) goto L16;
        r0 = r0 + 1;
        goto L13
    L16:
        TL_stars.TL_starGiftCollection r6 = this.collections.getCollections().get(r0);
        r1 = r0;
    L19:
        String r5 = DialogObject.getPublicUsername(MessagesController.getInstance(r15).getUserOrChat(this.dialogId));
        boolean r02 = this.collections.isMine();
        if (TextUtils.isEmpty(r5) == false) goto L24;
        if (r02 == true) goto L24;
        return Boolean.FALSE;
    L24:
        ItemOptions r152 = ItemOptions.makeOptions(r16, r20).setScrimViewBackground(new AnonymousClass3(this)).addIf(r02, R.drawable.menu_gift_add, LocaleController.getString(R.string.Gift2CollectionsAdd), new ProfileGiftsContainer$$ExternalSyntheticLambda12(this)).addIf(!TextUtils.isEmpty(r5), R.drawable.msg_share, LocaleController.getString(R.string.Gift2CollectionsShare), new ProfileGiftsContainer$$ExternalSyntheticLambda13(this, r15, r5, r6, r17, r18, r16)).addIf(r02, R.drawable.msg_edit, LocaleController.getString(R.string.Gift2CollectionsRename), new ProfileGiftsContainer$$ExternalSyntheticLambda14(this, r6)).addIf(r02, R.drawable.tabs_reorder, LocaleController.getString(R.string.Gift2CollectionsReorder), new ProfileGiftsContainer$$ExternalSyntheticLambda15(this)).addIf(r02, R.drawable.msg_delete, LocaleController.getString(R.string.Gift2CollectionsDelete), true, new ProfileGiftsContainer$$ExternalSyntheticLambda16(this, r1, r6));
        this.currentMenu = r152;
        r152.show();
        return Boolean.TRUE;
    L18:
        r6 = null;
    L27:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$new$8(TLRPC.TL_error r2, BaseFragment r3) {
        this.checkboxRequestId = -1;
        if (r2 == null) goto L6;
        BulletinFactory.of(r3).showForError(r2);
        return;
    }

    private /* synthetic */ void lambda$new$9(BaseFragment r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ProfileGiftsContainer$$ExternalSyntheticLambda24(this, r3, r1));
    }

    private /* synthetic */ void lambda$openEnterNameAlert$17(EditTextCaption r1, Activity r2, DialogInterface r3) {
        AndroidUtilities.hideKeyboard(r1);
        AndroidUtilities.requestAdjustResize(r2, this.fragment.getClassGuid());
    }

    private void openEnterNameAlert(String r19, Utilities.Callback<String> r20) {
        Context r7 = getContext();
        Activity r8 = AndroidUtilities.findActivity(r7);
        if (r8 == null) goto L5;
        View r5 = r8.getCurrentFocus();
    L6:
        AlertDialog[] r4 = new AlertDialog[1];
        AlertDialog.Builder r11 = new AlertDialog.Builder(r7, this.resourcesProvider);
        if (r19 == null) goto L9;
        r11.setTitle(LocaleController.getString(R.string.Gift2EditCollectionNameTitle));
    L10:
        AnonymousClass5 r2 = new AnonymousClass5(this, r7, this.resourcesProvider);
        r2.lineYFix = true;
        r2.setOnEditorActionListener(new AnonymousClass6(this, r2, r20, r4, r5));
        MediaDataController.getInstance(this.currentAccount).fetchNewEmojiKeywords(AndroidUtilities.getCurrentKeyboardLanguage(), true);
        r2.setTextSize(1, 18.0f);
        r2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r2.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, this.resourcesProvider));
        r2.setHintText(LocaleController.getString(R.string.Gift2NewCollectionHint));
        r2.setFocusable(true);
        r2.setInputType(147457);
        r2.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, this.resourcesProvider), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, this.resourcesProvider), Theme.getColor(Theme.key_text_RedRegular, this.resourcesProvider));
        r2.setImeOptions(6);
        r2.setBackgroundDrawable(null);
        r2.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        r2.addTextChangedListener(new AnonymousClass7(this, r2));
        LinearLayout r0 = new LinearLayout(r7);
        r0.setOrientation(1);
        r2.setText(r19);
        r0.addView(r2, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r11.makeCustomMaxHeight();
        r11.setView(r0);
        r11.setWidth(AndroidUtilities.dp(292.0f));
        if (r19 == null) goto L13;
        int r02 = R.string.Edit;
    L14:
        r11.setPositiveButton(LocaleController.getString(r02), new ProfileGiftsContainer$$ExternalSyntheticLambda19(r2, r20));
        r11.setNegativeButton(LocaleController.getString(R.string.Cancel), new ProfileGiftsContainer$$ExternalSyntheticLambda20());
        r4[0] = r11.create();
        ItemOptions r03 = this.currentMenu;
        if (r03 == null) goto L19;
        ActionBarPopupWindow r04 = r03.actionBarPopupWindow;
        if (r04 == null) goto L19;
        r04.setSoftInputMode(48);
    L19:
        AndroidUtilities.requestAdjustNothing(r8, this.fragment.getClassGuid());
        r4[0].setOnDismissListener(new ProfileGiftsContainer$$ExternalSyntheticLambda21(this, r2, r8));
        r4[0].setOnShowListener(new ProfileGiftsContainer$$ExternalSyntheticLambda22(r2));
        r4[0].show();
        r4[0].setDismissDialogByButtons(false);
        r4[0].getButton(-1);
        r2.setSelection(r2.getText().length());
        return;
    L13:
        r02 = R.string.Create;
        goto L14
    L9:
        r11.setTitle(LocaleController.getString(R.string.Gift2NewCollectionTitle));
        r11.setMessage(LocaleController.getString(R.string.Gift2NewCollectionText));
        goto L10
    L5:
        r5 = null;
        goto L6
    }

    public static void setGiftFilterOptionsClickListeners(View r1, StarsController.GiftsList r2, Runnable r3, int r4) {
        r1.setOnClickListener(new ProfileGiftsContainer$$ExternalSyntheticLambda9(r2, r4, r3));
        r1.setOnLongClickListener(new ProfileGiftsContainer$$ExternalSyntheticLambda10(r2, r4, r3));
    }

    private boolean shouldHideButton(int r3) {
        if (r3 != 0) goto L5;
        return false;
    L5:
        int r32 = r3 - 1;
        if (r32 >= 0) goto L8;
    L15:
        return true;
    L8:
        if (r32 >= this.collections.getCollections().size()) goto L15;
        StarsController.GiftsList r2 = this.collections.getListByIndex(r32);
        if (r2 != null) goto L14;
        return true;
    L14:
        return r2.gifts.isEmpty();
    }

    public void addGifts() {
        Page r0 = getCurrentPage();
        if (r0 == null) goto L11;
        StarsController.GiftsList r1 = r0.list;
        if (r1 != null) goto L7;
        return;
    L7:
        if (r0.isCollection == false) goto L13;
        int r7 = r1.collectionId;
        new SelectGiftsBottomSheet(this.fragment, this.dialogId, r7, new ProfileGiftsContainer$$ExternalSyntheticLambda18(this, r7, r0)).show();
        return;
    L13:
        return;
    }

    public boolean canAdd() {
        if (this.collections.isMine() == true) goto L6;
        return false;
    L6:
        if (this.collections.getCollections().size() >= MessagesController.getInstance(this.currentAccount).config.stargiftsCollectionsLimit.get()) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean canFilter() {
        return true;
    }

    public boolean canFilterHidden() {
        if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        return true;
    L7:
        if (this.dialogId < 0) goto L11;
        return false;
    L11:
        return ChatObject.canUserDoAction(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId)), 5);
    }

    public boolean canReorder() {
        long r0 = this.dialogId;
        if (r0 < 0) goto L14;
        if (r0 != 0) goto L7;
        return true;
    L7:
        if (r0 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L15;
        return false;
    L15:
        return true;
    L14:
        return ChatObject.canUserDoAction(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId)), 5);
    }

    public boolean canScroll(boolean r4) {
        ViewPagerFixed r0 = this.viewPager;
        if (r4 == false) goto L9;
        if (r0.getCurrentPosition() < this.collections.getCollections().size()) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r0.getCurrentPosition() > 0) goto L11;
        return true;
    L11:
        return false;
    }

    public boolean canSwitchNotify() {
        if (this.dialogId < 0) goto L6;
        return false;
    L6:
        if (this.list.chat_notifications_enabled == null) goto L9;
        return true;
    L9:
        return false;
    }

    public void createCollection() {
        openEnterNameAlert(null, new ProfileGiftsContainer$$ExternalSyntheticLambda11(this));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        int r2 = 8;
        if (r7 != NotificationCenter.starUserGiftsLoaded) goto L19;
        if (((Long) r9[0]).longValue() != this.dialogId) goto L38;
        ButtonWithCounterView r72 = this.button;
        if (canSwitchNotify() == false) goto L10;
        int r82 = 8;
    L11:
        r72.setVisibility(r82);
        LinearLayout r73 = this.checkboxLayout;
        if (canSwitchNotify() == false) goto L14;
        r2 = 0;
    L14:
        r73.setVisibility(r2);
        this.buttonContainerHeightDp = 60;
        Boolean r74 = this.list.chat_notifications_enabled;
        if (r74 == null) goto L36;
        this.checkbox.setChecked(r74.booleanValue(), true);
        return;
    L36:
        return;
    L10:
        r82 = 0;
        goto L11
    L38:
        return;
    L19:
        if (r7 != NotificationCenter.starUserGiftCollectionsLoaded) goto L26;
        if (((Long) r9[0]).longValue() != this.dialogId) goto L39;
        fillTabs(true);
        updateTabsShown(true);
        return;
    L39:
        return;
    L26:
        if (r7 != NotificationCenter.updateInterfaces) goto L37;
        ButtonWithCounterView r75 = this.button;
        if (canSwitchNotify() == false) goto L30;
        int r83 = 8;
    L31:
        r75.setVisibility(r83);
        LinearLayout r76 = this.checkboxLayout;
        if (canSwitchNotify() == false) goto L34;
        r2 = 0;
    L34:
        r76.setVisibility(r2);
        this.buttonContainerHeightDp = 60;
        setVisibleHeight(this.visibleHeight);
        return;
    L30:
        r83 = 0;
        goto L31
    }

    public int getBottomOffset() {
        float r0 = this.buttonContainer.getTranslationY() - ((((-this.buttonContainer.getTop()) + Math.max(AndroidUtilities.dp(240.0f), this.visibleHeight)) - AndroidUtilities.dp(this.buttonContainerHeightDp)) - 1);
        if (this.visibleHeight >= AndroidUtilities.dp(240.0f)) goto L6;
        r0 = r0 + Math.min(AndroidUtilities.dp(240.0f) - this.visibleHeight, AndroidUtilities.dp(this.buttonContainerHeightDp));
    L6:
        return (int) (AndroidUtilities.dp(this.buttonContainerHeightDp) - r0);
    }

    public StarsController.GiftsList getCurrentList() {
        Page r0 = getCurrentPage();
        if (r0 == null) goto L7;
        return r0.list;
    L7:
        return this.list;
    }

    public RecyclerListView getCurrentListView() {
        Page r0 = getCurrentPage();
        if (r0 != null) goto L5;
        return null;
    L5:
        return Page.m14952$$Nest$fgetlistView(r0);
    }

    public Page getCurrentPage() {
        View r0 = this.viewPager.getCurrentView();
        if (r0 != null) goto L7;
        return null;
    L7:
        return (Page) r0;
    }

    public int getGiftsCount() {
        Page r0 = getCurrentPage();
        if (r0 == null) goto L11;
        StarsController.GiftsList r02 = r0.list;
        if (r02 == this.list) goto L11;
        if (r02 == null) goto L16;
        int r03 = r02.totalCount;
        if (r03 <= 0) goto L16;
        return r03;
    L16:
        long r04 = this.dialogId;
        int r1 = this.currentAccount;
        if (r04 < 0) goto L23;
        TLRPC.UserFull r5 = MessagesController.getInstance(r1).getUserFull(this.dialogId);
        if (r5 != null) goto L21;
        return 0;
    L21:
        return r5.stargifts_count;
    L23:
        TLRPC.ChatFull r52 = MessagesController.getInstance(r1).getChatFull(-this.dialogId);
        if (r52 != null) goto L26;
        return 0;
    L26:
        return r52.stargifts_count;
    L11:
        StarsController.GiftsList r05 = this.list;
        if (r05 == null) goto L16;
        int r06 = r05.totalCount;
        if (r06 <= 0) goto L16;
        return r06;
    }

    public CharSequence getLastEmojis(Paint.FontMetricsInt r10) {
        if (this.list != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        Pair<Integer, Long> r0 = new Pair(Integer.valueOf(UserConfig.selectedAccount), Long.valueOf(this.dialogId));
        if (this.list.gifts.isEmpty() == true) goto L8;
        HashSet r2 = new HashSet();
        ArrayList r3 = new ArrayList();
        int r5 = 0;
    L15:
        if (r3.size() >= 3) goto L27;
        if (r5 >= this.list.gifts.size()) goto L27;
        TLRPC.Document r6 = this.list.gifts.get(r5).gift.getDocument();
        if (r6 == null) goto L25;
        if (r2.contains(Long.valueOf(r6.id)) == true) goto L25;
        r2.add(Long.valueOf(r6.id));
        r3.add(r6);
    L25:
        r5 = r5 + 1;
    L27:
        if (r3.isEmpty() == false) goto L29;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L29:
        SpannableStringBuilder r9 = new SpannableStringBuilder(" ");
        int r1 = 0;
    L31:
        if (r1 >= r3.size()) goto L33;
        TLRPC.Document r22 = (TLRPC.Document) r3.get(r1);
        SpannableStringBuilder r52 = new SpannableStringBuilder(MessageObject.getEmoji(r22));
        r52.setSpan(new AnimatedEmojiSpan(r22, 0.9f, r10), 0, r52.length(), 33);
        r9.append(r52);
        r1 = r1 + 1;
        goto L31
    L33:
        cachedLastEmojis.put(r0, r9);
        return r9;
    L8:
        if (this.list.loading == false) goto L12;
        CharSequence r92 = cachedLastEmojis.get(r0);
        if (r92 == null) goto L12;
        return r92;
    L12:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public long getLastEmojisHash() {
        StarsController.GiftsList r0 = this.list;
        long r1 = 0;
        if (r0 != null) goto L5;
    L17:
        return r1;
    L5:
        if (r0.gifts.isEmpty() == true) goto L17;
        HashSet r02 = new HashSet();
        int r3 = 0;
        int r4 = 0;
    L9:
        if (r3 >= 3) goto L17;
        if (r4 >= this.list.gifts.size()) goto L17;
        TLRPC.Document r5 = this.list.gifts.get(r4).gift.getDocument();
        if (r5 == null) goto L16;
        r02.add(Long.valueOf(r5.id));
        r1 = Objects.hash(new Object[]{Long.valueOf(r1), Long.valueOf(r5.id)});
        r3 = r3 + 1;
    L16:
        r4 = r4 + 1;
        goto L9
    }

    public float getTabsHeight() {
        float r1 = 0.0f;
        if (this.viewPager.getViewPages() == null) goto L10;
        View[] r6 = this.viewPager.getViewPages();
        int r0 = r6.length;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L10;
        View r3 = r6[r2];
        if ((r3 instanceof Page) == false) goto L9;
        r1 = r1 + ((1.0f - (r3.getTranslationX() / r3.getWidth())) * ((Page) r3).getTabsHeight());
    L9:
        r2 = r2 + 1;
    L10:
        return r1;
    }

    public float getTabsVisibility() {
        ViewPagerFixed.TabsView r0 = this.tabsView;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getAlpha();
    }

    public void initBlurCapture(ViewGroup r1) {
        this.iBlur3CaptureParent = r1;
        this.iBlur3Capture = new ProfileGiftsContainer$$ExternalSyntheticLambda2(this);
    }

    public boolean isReordering() {
        if (this.reorderingCollections == false) goto L5;
        return true;
    L5:
        Page r2 = getCurrentPage();
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r2.isReordering() == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftCollectionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        Page r0 = getCurrentPage();
        if (r0 == null) goto L5;
        r0.update(false);
    L5:
        fillTabs(false);
        updateTabsShown(false);
        StarsController.GiftsList r02 = this.list;
        if (r02 == null) goto L8;
        r02.shown = true;
        r02.load();
    L8:
        StarsController.GiftsCollections r2 = this.collections;
        if (r2 == null) goto L12;
        r2.shown = true;
        r2.load();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        Page r0 = getCurrentPage();
        resetReordering();
        if (r0 == null) goto L5;
        r0.resetReordering();
    L5:
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftCollectionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        StarsController.GiftsList r02 = this.list;
        if (r02 == null) goto L8;
        r02.shown = false;
    L8:
        StarsController.GiftsCollections r2 = this.collections;
        if (r2 == null) goto L12;
        r2.shown = false;
        return;
    }

    public abstract int processColor(int r1);

    public void resetReordering() {
        Page r0 = getCurrentPage();
        if (r0 == null) goto L5;
        r0.resetReordering();
    L5:
        setReorderingCollections(false);
    }

    public void scrollToCollectionId(int r1) {
        this.pendingScrollToCollectionId = r1;
        checkScrollToCollection();
    }

    public void setButtonOffset(int r2) {
        if (this.buttonContainerOffset == r2) goto L6;
        this.buttonContainerOffset = r2;
        updateButton();
        return;
    }

    public void setPaddingTop(int r10) {
        if (this.externalPaddingTop == r10) goto L15;
        this.externalPaddingTop = r10;
        View[] r102 = this.viewPager.getViewPages();
        int r0 = r102.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L10;
        View r2 = r102[r1];
        if ((r2 instanceof Page) == false) goto L9;
        Page r22 = (Page) r2;
        int r3 = Page.m14952$$Nest$fgetlistView(r22).getPaddingTop();
        Page.m14952$$Nest$fgetlistView(r22).setPadding(AndroidUtilities.dp(9.0f), this.externalPaddingTop, AndroidUtilities.dp(9.0f), AndroidUtilities.dp(86.0f));
        int r32 = r3 - Page.m14952$$Nest$fgetlistView(r22).getPaddingTop();
        AndroidUtilities.doOnLayout(Page.m14952$$Nest$fgetlistView(r22), new ProfileGiftsContainer$$ExternalSyntheticLambda1(r22, r32));
    L9:
        r1 = r1 + 1;
        goto L5
    L10:
        updateTabsY();
        updateButton();
        return;
    }

    public void setReordering(boolean r1) {
        Page r0 = getCurrentPage();
        if (r0 == null) goto L6;
        Page.m14956$$Nest$msetReordering(r0, r1);
        return;
    }

    public void setReorderingCollections(boolean r4) {
        if (this.reorderingCollections == r4) goto L14;
        this.reorderingCollections = r4;
        updatedReordering(isReordering());
        this.tabsView.setReordering(r4);
        if (r4 == false) goto L10;
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if ((r0 instanceof ProfileActivity) == false) goto L10;
        ((ProfileActivity) r0).scrollToSharedMedia(false);
        AndroidUtilities.runOnUIThread(new ProfileGiftsContainer$$ExternalSyntheticLambda0(r0));
    L10:
        if (r4 == true) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.sendCollectionsOrder);
        AndroidUtilities.runOnUIThread(this.sendCollectionsOrder);
        return;
    L13:
        return;
    }

    public void setVisibleHeight(int r5) {
        this.visibleHeight = r5;
        updateButton();
        ViewPagerFixed r52 = this.viewPager;
        if (r52 == null) goto L10;
        View[] r53 = r52.getViewPages();
        int r0 = r53.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        View r2 = r53[r1];
        if ((r2 instanceof Page) == false) goto L9;
        ((Page) r2).setVisibleHeight(this.visibleHeight);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void updateButton() {
        ViewPagerFixed r0 = this.viewPager;
        if (r0 != null) goto L5;
        return;
    L5:
        int r02 = r0.getCurrentPosition();
        int r1 = this.viewPager.getNextPosition();
        ViewPagerFixed r2 = this.viewPager;
        float r4 = 1.0f;
        if (r02 != r1) goto L13;
        if (shouldHideButton(r2.getCurrentPosition()) == true) goto L11;
        r4 = 0.0f;
    L11:
        float r03 = (AndroidUtilities.dp(68.0f) + 2) * r4;
    L21:
        float r04 = r03 + ((((-this.buttonContainer.getTop()) + this.visibleHeight) - AndroidUtilities.dp(this.buttonContainerHeightDp)) - 1);
        BoolAnimator r12 = this.animatorBottomButtonVisibility;
        int r6 = 0;
        if (this.visibleHeight <= AndroidUtilities.dp(184.0f)) goto L24;
        boolean r3 = true;
    L25:
        r12.setValue(r3, true);
        float r13 = this.animatorBottomButtonVisibility.getFloatValue();
        float r05 = AndroidUtilities.lerp(AndroidUtilities.dp(60.0f) + r04, r04, r13);
        this.bulletinContainer.setTranslationY(r05 - AndroidUtilities.dp(200.0f));
        this.buttonContainer.setTranslationY(r05 - this.buttonContainerOffset);
        this.buttonContainer.setAlpha(r13);
        FrameLayout r06 = this.buttonContainer;
        if (r13 > 0.0f) goto L29;
        r6 = 4;
    L29:
        r06.setVisibility(r6);
        ButtonWithCounterView r07 = this.button;
        if (this.collections.isMine() == true) goto L32;
    L35:
        CharSequence r7 = this.sendGiftsToFriendsText;
    L36:
        r07.setText(r7, true);
        Bulletin.updateCurrentPosition();
        return;
    L32:
        if (this.viewPager.getPositionAnimated() < 0.5f) goto L35;
        r7 = this.addGiftsText;
        goto L36
    L24:
        r3 = false;
        goto L25
    L13:
        if (shouldHideButton(r2.getCurrentPosition()) == false) goto L15;
        float r08 = 1.0f;
    L16:
        float r09 = r08 * this.viewPager.getCurrentPositionAlpha();
        if (shouldHideButton(this.viewPager.getNextPosition()) == true) goto L20;
        r4 = 0.0f;
    L20:
        r03 = (r09 + (r4 * this.viewPager.getNextPositionAlpha())) * (AndroidUtilities.dp(68.0f) + 2);
        goto L21
    L15:
        r08 = 0.0f;
        goto L16
    }

    public void updateColors() {
        this.button.updateColors();
        this.button.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(19.0f), processColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider))));
        View[] r0 = this.viewPager.getViewPages();
        if (r0 == null) goto L11;
        int r1 = 0;
    L6:
        if (r1 >= r0.length) goto L11;
        View r2 = r0[r1];
        if (r2 == null) goto L10;
        ((Page) r2).updateColors();
    L10:
        r1 = r1 + 1;
    L11:
        this.checkboxTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        this.checkboxLayout.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 24, 24));
    }

    public void updateTabsShown(boolean r5) {
        int r0 = 0;
        if (this.collections.getCollections().isEmpty() == true) goto L5;
    L8:
        boolean r52 = true;
    L10:
        if (this.viewPager.getViewPages() == null) goto L17;
        View[] r4 = this.viewPager.getViewPages();
        int r1 = r4.length;
    L12:
        if (r0 >= r1) goto L21;
        View r2 = r4[r0];
        if ((r2 instanceof Page) == false) goto L16;
        ((Page) r2).setHasTabs(r52);
    L16:
        r0 = r0 + 1;
        goto L12
    L21:
        return;
    L17:
        return;
    L5:
        if (canAdd() == true) goto L8;
        r52 = false;
        goto L10
    }

    public void updateTabsY() {
        if (this.tabsView != null) goto L5;
        return;
    L5:
        float r0 = Math.min(this.externalPaddingTop, getTabsHeight() - AndroidUtilities.dp(42.0f));
        float r1 = Utilities.clamp01(AndroidUtilities.ilerp(r0 - this.externalPaddingTop, -AndroidUtilities.dp(42.0f), 0.0f));
        float r2 = AndroidUtilities.lerp(0.9f, 1.0f, r1);
        this.tabsView.setTranslationY(r0);
        this.tabsView.setScaleX(r2);
        this.tabsView.setScaleY(r2);
        this.tabsView.setAlpha(r1 * hasTabs());
    }

    public abstract void updatedReordering(boolean r1);
}
