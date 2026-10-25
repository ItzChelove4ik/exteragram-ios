package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.camera.camera2.compat.quirk.Device$$ExternalSyntheticApiModelOutline0;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.GlassOutlineStyle;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.debug.DebugActivity;
import com.exteragram.messenger.preferences.MainPreferencesActivity;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import com.google.android.material.timepicker.TimeModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.AuthTokensHelper;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SharedConfig$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.SharedPrefsHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SettingsSearchCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugController;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.HintsController;
import org.telegram.ui.Components.IconBackgroundColors;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Paint.PersistColorPalette;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.DualCameraView;
import org.telegram.ui.TON.TONIntroActivity;
import org.telegram.ui.bots.BotBiometry;
import org.telegram.ui.bots.BotDownloads;
import org.telegram.ui.bots.BotLocation;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.bots.SetupEmojiStatusSheet;
import org.telegram.utils.settings.SharedSettings;

/* loaded from: classes6.dex */
public class SettingsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, ImageUpdater.ImageUpdaterDelegate, MainTabsActivity.TabFragmentDelegate, FactorAnimator.Target {
    private View actionBarBackground;
    private boolean actionBarVisible;
    private ValueAnimator actionBarVisibleAnimator;
    private int additionNavigationBarHeight;
    private final BoolAnimator animatorSearchPageVisible;
    private TLRPC.FileLocation avatar;
    private AnimatorSet avatarAnimation;
    private TLRPC.FileLocation avatarBig;
    private FrameLayout avatarContainer;
    private AvatarDrawable avatarDrawable;
    private RadialProgressView avatarProgressView;
    int avatarUploadingRequest;
    private BackupImageView avatarView;
    private FrameLayout cameraBackground;
    private FrameLayout cameraButton;
    private ImageView cameraImageView;
    private SizeNotifierFrameLayout contentView;
    public boolean hasMainTabs;
    private IBlur3Capture iBlur3Capture;
    private boolean iBlur3Invalidated;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private boolean ignoreClearViews;
    private ImageUpdater imageUpdater;
    private UniversalRecyclerView listView;
    private MainTabsActivityController mainTabsActivityController;
    private boolean mainTabsHiddenByScroll;
    private View navigationBar;
    private int navigationBarHeight;
    private ActionBarMenuItem otherItem;
    private String query;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private ProfileActivity.SearchAdapter search;
    private ActionBarMenuItem searchItem;
    private TextView subtitleView;
    private TextView titleView;
    private FrameLayout topView;
    private TextView versionView;
    private int versionViewPressCount;

    /* renamed from: org.telegram.ui.SettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends SizeNotifierFrameLayout {
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass1(SettingsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L23:
            super.dispatchDraw(r6);
            SettingsActivity r0 = this.this$0;
            if (r0.hasMainTabs == true) goto L27;
            AndroidUtilities.drawNavigationBarProtection(r6, this, r0.getThemedColor(Theme.key_windowBackgroundWhite), SettingsActivity.m18843$$Nest$fgetnavigationBarHeight(this.this$0));
            return;
        L27:
            return;
        L5:
            if (SettingsActivity.m18844$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L23;
            SettingsActivity.m18850$$Nest$mblur3_InvalidateBlur(this.this$0);
            int r02 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            if (SettingsActivity.m18841$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L15;
            if (SettingsActivity.m18841$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L15;
            RecordingCanvas r2 = SettingsActivity.m18841$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r02, r1);
            r2.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L13;
            SettingsActivity.m18844$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r2, -3);
        L13:
            SettingsActivity.m18841$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
        L15:
            if (SettingsActivity.m18840$$Nest$fgetiBlur3SourceGlass(this.this$0) != null) goto L17;
        L22:
            SettingsActivity.m18847$$Nest$fputiBlur3Invalidated(this.this$0, false);
            goto L23
        L17:
            if (SettingsActivity.m18840$$Nest$fgetiBlur3SourceGlass(this.this$0).inRecording() == true) goto L22;
            RecordingCanvas r03 = SettingsActivity.m18840$$Nest$fgetiBlur3SourceGlass(this.this$0).beginRecording(r02, r1);
            r03.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L21;
            SettingsActivity.m18844$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r03, -2);
        L21:
            SettingsActivity.m18840$$Nest$fgetiBlur3SourceGlass(this.this$0).endRecording();
            goto L22
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawBlurRect(Canvas r7, float r8, Rect r9, Paint r10, boolean r11) {
            if (Build.VERSION.SDK_INT < 29) goto L11;
            if (SharedConfig.chatBlurEnabled() == false) goto L11;
            if (SettingsActivity.m18841$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L11;
            r7.save();
            r7.translate(0.0f, -r8);
            SettingsActivity.m18841$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).draw(r7, r9.left, r9.top + r8, r9.right, r9.bottom + r8);
            r7.restore();
            int r6 = r10.getAlpha();
            r10.setAlpha(178);
            r7.drawRect(r9, r10);
            r10.setAlpha(r6);
            return;
        L11:
            r7.drawRect(r9, r10);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            super.updateColors();
            this.this$0.updateColors();
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass2(SettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r2 != 2) goto L10;
            SettingsActivity.m18851$$Nest$mpresentSettingFragment(this.this$0, new LogoutActivity());
            return;
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass3(SettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            SettingsActivity.m18835$$Nest$fgetanimatorSearchPageVisible(this.this$0).setValue(false, true);
            SettingsActivity.m18852$$Nest$mupdateActionBarVisible(this.this$0);
            SettingsActivity.m18842$$Nest$fgetlistView(this.this$0).adapter.update(false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            SettingsActivity.m18835$$Nest$fgetanimatorSearchPageVisible(this.this$0).setValue(true, true);
            SettingsActivity.m18845$$Nest$fgetsearch(this.this$0).prepareExteraItems();
            ProfileActivity.SearchAdapter r0 = SettingsActivity.m18845$$Nest$fgetsearch(this.this$0);
            SettingsActivity.m18849$$Nest$fputquery(this.this$0, _UrlKt.FRAGMENT_ENCODE_SET);
            r0.search(_UrlKt.FRAGMENT_ENCODE_SET);
            SettingsActivity.m18852$$Nest$mupdateActionBarVisible(this.this$0);
            SettingsActivity.m18842$$Nest$fgetlistView(this.this$0).adapter.update(false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            ProfileActivity.SearchAdapter r0 = SettingsActivity.m18845$$Nest$fgetsearch(this.this$0);
            SettingsActivity r1 = this.this$0;
            String r22 = r2.getText().toString();
            SettingsActivity.m18849$$Nest$fputquery(r1, r22);
            r0.search(r22);
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ProfileActivity.SearchAdapter {
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass4(SettingsActivity r1, BaseFragment r2, Context r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            SettingsActivity.m18842$$Nest$fgetlistView(this.this$0).adapter.update(true);
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass5(SettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r4, int r5, int r6) {
            SettingsActivity.m18852$$Nest$mupdateActionBarVisible(this.this$0);
            if (r6 == 0) goto L12;
            boolean r0 = true;
            boolean r42 = r4.canScrollVertically(1);
            SettingsActivity r1 = this.this$0;
            if (BottomNavigationBar.floating() == false) goto L9;
            if (r6 <= 0) goto L9;
            if (r42 == false) goto L9;
        L10:
            SettingsActivity.m18848$$Nest$fputmainTabsHiddenByScroll(r1, r0);
            this.this$0.updateMainTabsVisibility();
        L9:
            r0 = false;
        L12:
            if (SettingsActivity.m18842$$Nest$fgetlistView(this.this$0).scrollingByUser == false) goto L15;
            AndroidUtilities.hideKeyboard(this.this$0.fragmentView);
        L15:
            if (Build.VERSION.SDK_INT >= 31) goto L17;
            return;
        L17:
            if (SettingsActivity.m18844$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L21;
            SettingsActivity.m18844$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r5, r6);
            SettingsActivity.m18850$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends View {
        private final Paint blurScrimPaint;
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass6(SettingsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.blurScrimPaint = new Paint(1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            int r0 = SettingsActivity.access$000(this.this$0).getHeight();
            Rect r4 = AndroidUtilities.rectTmp2;
            r4.set(0, 0, getMeasuredWidth(), r0);
            this.blurScrimPaint.setColor(Theme.getColor(Theme.key_actionBarDefault, SettingsActivity.access$100(this.this$0)));
            SettingsActivity.m18839$$Nest$fgetcontentView(this.this$0).drawBlurRect(r8, 0.0f, r4, this.blurScrimPaint, true);
            if (this.this$0.getParentLayout() == null) goto L6;
            this.this$0.getParentLayout().drawHeaderShadow(r8, r0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends RadialProgressView {
        private Paint paint;
        final /* synthetic */ SettingsActivity this$0;

        public AnonymousClass7(SettingsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            Paint r12 = new Paint(1);
            this.paint = r12;
            r12.setColor(1426063360);
        }

        @Override // org.telegram.ui.Components.RadialProgressView, android.view.View
        public void onDraw(Canvas r5) {
            if (SettingsActivity.m18838$$Nest$fgetavatarView(this.this$0) != null) goto L5;
        L7:
            super.onDraw(r5);
            return;
        L5:
            if (SettingsActivity.m18838$$Nest$fgetavatarView(this.this$0).getImageReceiver().hasNotThumb() == false) goto L7;
            this.paint.setAlpha((int) (SettingsActivity.m18838$$Nest$fgetavatarView(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f));
            r5.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, getMeasuredWidth() / 2.0f, this.paint);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends ShareAlert {
        final /* synthetic */ SettingsActivity this$0;

        public static /* synthetic */ void $r8$lambda$SuBojj_xdcerivc62c7p3mIbSWY(AnonymousClass8 r0, LongSparseArray r1, int r2) {
            r0.lambda$onSend$0(r1, r2);
        }

        public AnonymousClass8(SettingsActivity r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        private /* synthetic */ void lambda$onSend$0(LongSparseArray r10, int r11) {
            Activity r1 = this.this$0.getParentActivity();
            SizeNotifierFrameLayout r2 = SettingsActivity.m18839$$Nest$fgetcontentView(this.this$0);
            int r3 = r10.size();
            if (r10.size() != 1) goto L5;
            long r4 = ((TLRPC.Dialog) r10.valueAt(0)).id;
        L6:
            BulletinFactory.createInviteSentBulletin(r1, r2, r3, r4, r11, getThemedColor(Theme.key_undo_background), getThemedColor(Theme.key_undo_infoColor)).show();
            return;
        L5:
            r4 = 0;
            goto L6
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r1, int r2, TLRPC.TL_forumTopic r3, boolean r4) {
            if (r4 == true) goto L4;
            return;
        L4:
            AndroidUtilities.runOnUIThread(new SettingsActivity$8$$ExternalSyntheticLambda0(this, r1, r2), 250);
        }
    }

    /* renamed from: org.telegram.ui.SettingsActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ SettingsActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass9(SettingsActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            SettingsActivity.m18846$$Nest$fputavatarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (SettingsActivity.m18836$$Nest$fgetavatarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (SettingsActivity.m18837$$Nest$fgetavatarProgressView(this.this$0) != null) goto L8;
            return;
        L8:
            if (this.val$show == true) goto L10;
            SettingsActivity.m18837$$Nest$fgetavatarProgressView(this.this$0).setVisibility(4);
        L10:
            SettingsActivity.m18846$$Nest$fputavatarAnimation(this.this$0, null);
        }
    }

    public static class AccountCell extends LinearLayout implements Theme.Colorable {
        private ImageView arrowView;
        private AvatarDrawable avatarDrawable;
        private BackupImageView avatarView;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable botDrawable;
        private TextView counterView;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiStatusDrawable;
        private final Theme.ResourcesProvider resourcesProvider;
        private SimpleTextView textView;

        /* renamed from: org.telegram.ui.SettingsActivity$AccountCell$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ AccountCell this$0;

            public AnonymousClass1(AccountCell r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                AccountCell.m18853$$Nest$fgetbotDrawable(this.this$0).attach();
                AccountCell.m18854$$Nest$fgetemojiStatusDrawable(this.this$0).attach();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                AccountCell.m18853$$Nest$fgetbotDrawable(this.this$0).detach();
                AccountCell.m18854$$Nest$fgetemojiStatusDrawable(this.this$0).detach();
            }
        }

        public static class Factory extends UItem.UItemFactory<AccountCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(int r1, int r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.intValue = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((AccountCell) r1).set(r2.intValue);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r1, UItem r2) {
                if (r1.intValue != r2.intValue) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public AccountCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new AccountCell(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.id != r2.id) goto L6;
                return true;
            L6:
                return false;
            }
        }

        /* renamed from: -$$Nest$fgetbotDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m18853$$Nest$fgetbotDrawable(AccountCell r0) {
            return r0.botDrawable;
        }

        /* renamed from: -$$Nest$fgetemojiStatusDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m18854$$Nest$fgetemojiStatusDrawable(AccountCell r0) {
            return r0.emojiStatusDrawable;
        }

        public AccountCell(Context r9, Theme.ResourcesProvider r10) {
            super(r9);
            this.resourcesProvider = r10;
            setOrientation(0);
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r1 = new BackupImageView(r9);
            this.avatarView = r1;
            r1.setRoundRadius(ExteraConfig.getAvatarCorners(28.0f));
            SimpleTextView r12 = new SimpleTextView(r9);
            this.textView = r12;
            NotificationCenter.listenEmojiLoading(r12);
            this.textView.setTextSize(15);
            this.textView.setTypeface(AndroidUtilities.bold());
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r10));
            this.botDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.textView, AndroidUtilities.dp(24.0f), 7);
            this.emojiStatusDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.textView, AndroidUtilities.dp(24.0f), 7);
            this.textView.addOnAttachStateChangeListener(new AnonymousClass1(this));
            TextView r13 = new TextView(r9);
            this.counterView = r13;
            r13.setPadding(AndroidUtilities.dp(6.66f), 0, AndroidUtilities.dp(6.66f), 0);
            this.counterView.setTextSize(1, 11.0f);
            this.counterView.setTypeface(AndroidUtilities.bold());
            this.counterView.setGravity(17);
            this.counterView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r10));
            this.counterView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r10)));
            ImageView r0 = new ImageView(r9);
            this.arrowView = r0;
            r0.setImageResource(R.drawable.msg_arrowright);
            this.arrowView.setScaleType(ImageView.ScaleType.CENTER);
            this.arrowView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, r10), PorterDuff.Mode.SRC_IN));
            boolean r92 = LocaleController.isRTL;
            SimpleTextView r102 = this.textView;
            if (r92 == false) goto L6;
            r102.setGravity(21);
            this.arrowView.setScaleX(-1.0f);
            addView(this.arrowView, LayoutHelper.createLinear(24, 24, 0.0f, 19, 12, 0, 0, 0));
            addView(this.counterView, LayoutHelper.createLinear(-2, 20, 0.0f, 16, 0, 0, 0, 0));
            addView(this.textView, LayoutHelper.createLinear(0, -1, 1.0f, 119, 18, 0, 0, 0));
            addView(this.avatarView, LayoutHelper.createLinear(28, 28, 21, 18, 0, 18, 0));
            return;
        L6:
            r102.setGravity(19);
            addView(this.avatarView, LayoutHelper.createLinear(28, 28, 19, 18, 0, 18, 0));
            addView(this.textView, LayoutHelper.createLinear(0, -1, 1.0f, 119, 0, 0, 18, 0));
            addView(this.counterView, LayoutHelper.createLinear(-2, 20, 0.0f, 16, 0, 0, 0, 0));
            addView(this.arrowView, LayoutHelper.createLinear(24, 24, 0.0f, 21, 0, 0, 12, 0));
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void set(int r10) {
            TLRPC.User r0 = UserConfig.getInstance(r10).getCurrentUser();
            this.avatarDrawable.setInfo(r10, r0);
            this.avatarView.getImageReceiver().setCurrentAccount(r10);
            this.avatarView.setForUserOrChat(r0, this.avatarDrawable);
            int r4 = 0;
            this.textView.setText(Emoji.replaceEmoji(UserObject.getUserName(r0), this.textView.getPaint().getFontMetricsInt(), false));
            this.botDrawable.setCurrentAccount(r10);
            this.emojiStatusDrawable.setCurrentAccount(r10);
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.botDrawable;
            int r2 = Theme.key_profile_verifiedBackground;
            r1.setColor(Integer.valueOf(Theme.getColor(r2, this.resourcesProvider)));
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r12 = null;
            if (r0 == null) goto L7;
            long r5 = r0.bot_verification_icon;
            if (r5 == 0) goto L7;
            this.botDrawable.set(r5, false);
        L8:
            Long r3 = UserObject.getEmojiStatusDocumentId(r0);
            this.emojiStatusDrawable.setColor(Integer.valueOf(Theme.getColor(r2, this.resourcesProvider)));
            if (r3 == null) goto L11;
            this.emojiStatusDrawable.set(r3.longValue(), false);
        L16:
            SimpleTextView r02 = this.textView;
            if (this.botDrawable.isEmpty() == true) goto L19;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r22 = this.botDrawable;
        L20:
            r02.setLeftDrawable(r22);
            SimpleTextView r03 = this.textView;
            if (this.emojiStatusDrawable.isEmpty() == true) goto L23;
            r12 = this.emojiStatusDrawable;
        L23:
            r03.setRightDrawable(r12);
            int r102 = MessagesStorage.getInstance(r10).getMainUnreadCount();
            TextView r04 = this.counterView;
            if (r102 > 0) goto L27;
            r4 = 8;
        L27:
            r04.setVisibility(r4);
            this.counterView.setText(LocaleController.formatNumber(r102, ','));
            return;
        L19:
            r22 = null;
            goto L20
        L11:
            if (r0 != null) goto L13;
        L15:
            this.emojiStatusDrawable.set(null, false);
            goto L16
        L13:
            if (r0.premium == false) goto L15;
            this.emojiStatusDrawable.set(getContext().getResources().getDrawable(R.drawable.msg_premium_liststar).mutate(), false);
        L7:
            this.botDrawable.set(null, false);
            goto L8
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.counterView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
            this.arrowView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            this.emojiStatusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_profile_verifiedBackground, this.resourcesProvider)));
        }
    }

    public static class SettingCell extends LinearLayout implements Theme.Colorable {
        private final Background iconBackground;
        private final FrameLayout iconLayout;
        private final ImageView iconView;
        private final boolean mini;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView subtitleView;
        private final LinearLayout textLayout;
        private final TextView titleView;
        private boolean twoLines;
        private final TextView valueView;

        public static class Background extends Drawable {
            private boolean border;
            private int bottomColor;
            private LinearGradient gradient;
            private final Matrix matrix;
            private final Paint paint;
            private LinearGradient strokeGradient;
            private final Paint strokePaint;
            private int topColor;

            public Background() {
                this.paint = new Paint(1);
                Paint r0 = new Paint(1);
                this.strokePaint = r0;
                this.matrix = new Matrix();
                r0.setStyle(Paint.Style.STROKE);
                LinearGradient r2 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(28.0f), new int[]{1308622847, 0, 452984831}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
                this.strokeGradient = r2;
                r0.setShader(r2);
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r7) {
                float r0 = AndroidUtilities.dp(10.0f);
                RectF r1 = AndroidUtilities.rectTmp;
                r1.set(getBounds());
                this.matrix.reset();
                this.matrix.postTranslate(r1.left, r1.top);
                r7.drawRoundRect(r1, r0, r0, this.paint);
                if (this.border == false) goto L6;
                float r2 = AndroidUtilities.dp(1.0f);
                this.strokePaint.setStrokeWidth(r2);
                this.matrix.reset();
                this.matrix.postTranslate(r1.left, r1.top);
                float r22 = r2 / 2.0f;
                r1.inset(r22, r22);
                r7.drawRoundRect(r1, r0, r0, this.strokePaint);
                return;
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
            }

            public void setColor(int r2, int r3) {
                setColor(r2, r3, Theme.isCurrentThemeMonet());
            }

            public void setColor(int r10, int r11, boolean r12) {
                this.topColor = r10;
                this.bottomColor = r11;
                int r0 = Theme.getColor(Theme.key_chats_actionBackground);
                float r5 = AndroidUtilities.dp(28.0f);
                if (r12 == false) goto L5;
                r10 = r0;
            L5:
                if (r12 == false) goto L7;
                r11 = r0;
            L7:
                LinearGradient r1 = new LinearGradient(0.0f, 0.0f, 0.0f, r5, new int[]{r10, r11}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
                this.gradient = r1;
                this.paint.setShader(r1);
                invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }

            public void setDrawBorder(boolean r2) {
                if (r2 == true) goto L4;
            L8:
                boolean r22 = false;
            L9:
                this.border = r22;
                return;
            L4:
                if (Theme.isCurrentThemeMonet() == true) goto L8;
                if (ExteraConfig.getGlassOutlineStyle() != GlassOutlineStyle.GLARE) goto L8;
                r22 = true;
                goto L9
            }

            public void updateColors() {
                if (this.gradient == null) goto L6;
                setColor(this.topColor, this.bottomColor);
                return;
            }
        }

        public static class Factory extends UItem.UItemFactory<SettingCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(int r7, int r8, int r9, int r10, CharSequence r11) {
                return of(r7, r8, r9, r10, r11, null, null);
            }

            public static UItem of(int r7, int r8, int r9, int r10, CharSequence r11, CharSequence r12) {
                return of(r7, r8, r9, r10, r11, r12, null);
            }

            public static UItem of(int r2, int r3, int r4, int r5, CharSequence r6, CharSequence r7, CharSequence r8) {
                UItem r72 = UItem.ofFactory(Factory.class);
                r72.id = r2;
                r72.iconResId = r5;
                r72.text = r6;
                r72.textValue = r8;
                r72.longValue = (r3 & 4294967295L) | (r4 << 32);
                return r72;
            }

            public static UItem ofBot(TLRPC.TL_attachMenuBot r3, int r4, int r5, int r6) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = Long.hashCode(r3.bot_id);
                r0.object = r3;
                r0.iconResId = r6;
                r0.text = r3.short_name;
                r0.longValue = (r4 & 4294967295L) | (r5 << 32);
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
                long r102 = r9.longValue;
                SettingCell r0 = (SettingCell) r8;
                r0.set((int) r102, (int) (r102 >>> 32), r9.iconResId, r9.text, r9.subtext, r9.textValue);
                ImageView r7 = r0.getIconView();
                if (r9.id != (-1)) goto L5;
                ImageView.ScaleType r82 = ImageView.ScaleType.CENTER_CROP;
            L6:
                r7.setScaleType(r82);
                return;
            L5:
                r82 = ImageView.ScaleType.CENTER;
                goto L6
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public SettingCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new SettingCell(r1, r5);
            }
        }

        public SettingCell(Context r2, Theme.ResourcesProvider r3) {
            this(r2, r3, false);
        }

        public SettingCell(Context r18, Theme.ResourcesProvider r19, boolean r20) {
            super(r18);
            this.resourcesProvider = r19;
            this.mini = r20;
            setOrientation(0);
            FrameLayout r3 = new FrameLayout(r18);
            this.iconLayout = r3;
            Background r4 = new Background();
            this.iconBackground = r4;
            r3.setBackground(r4);
            ImageView r42 = new ImageView(r18);
            this.iconView = r42;
            r42.setScaleType(ImageView.ScaleType.CENTER_CROP);
            r3.addView(r42, LayoutHelper.createFrame(24, 24, 17));
            LinearLayout r43 = new LinearLayout(r18);
            this.textLayout = r43;
            r43.setOrientation(1);
            TextView r6 = new TextView(r18);
            this.titleView = r6;
            r6.setTextSize(1, 16.0f);
            r6.setTypeface(AndroidUtilities.regular());
            r43.addView(r6, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
            TextView r62 = new TextView(r18);
            this.subtitleView = r62;
            r62.setTextSize(1, 13.0f);
            r62.setTypeface(AndroidUtilities.regular());
            r43.addView(r62, LayoutHelper.createLinear(-1, -2, 0.0f, 4.0f, 0.0f, 0.0f));
            TextView r63 = new TextView(r18);
            this.valueView = r63;
            r63.setTextSize(1, 16.0f);
            r63.setTypeface(AndroidUtilities.regular());
            if (LocaleController.isRTL == false) goto L13;
            addView(r63, LayoutHelper.createLinear(-2, -2, 16, 20, 0, 0, 0));
            if (r20 == false) goto L7;
            int r15 = 12;
        L8:
            addView(r43, LayoutHelper.createLinear(0, -2, 1.0f, 23, 20, 0, r15, 0));
            if (r20 == false) goto L11;
            int r14 = 9;
        L12:
            addView(r3, LayoutHelper.createLinear(28, 28, 21, 0, 0, r14, 0));
        L21:
            updateColors();
            return;
        L11:
            r14 = 18;
            goto L12
        L7:
            r15 = 18;
            goto L8
        L13:
            if (r20 == false) goto L15;
            int r12 = 9;
        L16:
            addView(r3, LayoutHelper.createLinear(28, 28, 19, r12, 0, 0, 0));
            if (r20 == false) goto L19;
            int r13 = 12;
        L20:
            addView(r43, LayoutHelper.createLinear(0, -2, 1.0f, 23, r13, 0, 20, 0));
            addView(r63, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 20, 0));
            goto L21
        L19:
            r13 = 18;
            goto L20
        L15:
            r12 = 18;
            goto L16
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        public ImageView getIconView() {
            return this.iconView;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30);
            if (this.mini == false) goto L6;
            float r0 = 44.0f;
        L9:
            super.onMeasure(r22, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0), TLObject.FLAG_30));
            return;
        L6:
            if (this.twoLines == false) goto L8;
            r0 = 60.0f;
            goto L9
        L8:
            r0 = 50.0f;
            goto L9
        }

        public void set(int r7, int r8, int r9, CharSequence r10, CharSequence r11, CharSequence r12) {
            FrameLayout r0 = this.iconLayout;
            int r1 = 8;
            if (r9 == 0) goto L5;
            int r3 = 0;
        L6:
            r0.setVisibility(r3);
            TextView r02 = this.titleView;
            float r32 = 0.0f;
            if (r9 != 0) goto L9;
            float r5 = AndroidUtilities.dp(2.0f);
        L10:
            r02.setTranslationX(r5);
            TextView r03 = this.subtitleView;
            if (r9 != 0) goto L13;
            r32 = AndroidUtilities.dp(2.0f);
        L13:
            r03.setTranslationX(r32);
            this.iconBackground.setColor(r7, r8);
            this.iconView.setImageResource(r9);
            this.titleView.setText(r10);
            TextView r72 = this.subtitleView;
            boolean r82 = TextUtils.isEmpty(r11);
            this.twoLines = !r82;
            if (r82 == true) goto L16;
            r1 = 0;
        L16:
            r72.setVisibility(r1);
            this.subtitleView.setText(r11);
            setValue(r12);
            return;
        L9:
            r5 = 0.0f;
            goto L10
        L5:
            r3 = 8;
            goto L6
        }

        public void setValue(CharSequence r3) {
            TextView r0 = this.valueView;
            if (TextUtils.isEmpty(r3) == true) goto L5;
            int r1 = 0;
        L6:
            r0.setVisibility(r1);
            this.valueView.setText(r3);
            return;
        L5:
            r1 = 8;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider));
            this.valueView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, this.resourcesProvider));
            Background r0 = this.iconBackground;
            Theme.ResourcesProvider r1 = this.resourcesProvider;
            if (r1 == null) goto L5;
            boolean r12 = r1.isDark();
        L6:
            r0.setDrawBorder(r12);
            boolean r02 = Theme.isCurrentThemeMonet();
            int r13 = Theme.getColor(Theme.key_chats_actionIcon);
            ImageView r2 = this.iconView;
            if (r02 == true) goto L10;
            r13 = -1;
        L10:
            r2.setColorFilter(r13, PorterDuff.Mode.SRC_IN);
            this.iconBackground.updateColors();
            return;
        L5:
            r12 = Theme.isCurrentThemeDark();
            goto L6
        }
    }

    public static class SuggestionCell extends LinearLayout implements Theme.Colorable {
        private ButtonWithCounterView no;
        private final Theme.ResourcesProvider resourcesProvider;
        private LinkSpanDrawable.LinksTextView textView;
        private SpoilersTextView titleView;
        private ButtonWithCounterView yes;

        public static class Factory extends UItem.UItemFactory<SuggestionCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(CharSequence r1, CharSequence r2, CharSequence r3, View.OnClickListener r4, CharSequence r5, View.OnClickListener r6) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.text = r1;
                r0.subtext = r2;
                r0.textValue = r3;
                r0.clickCallback = r4;
                r0.animatedText = r5;
                r0.clickCallback2 = r6;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
                ((SuggestionCell) r8).set(r9.text, r9.subtext, r9.textValue, r9.clickCallback, r9.animatedText, r9.clickCallback2);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public SuggestionCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new SuggestionCell(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        public SuggestionCell(Context r12, Theme.ResourcesProvider r13) {
            super(r12);
            this.resourcesProvider = r13;
            setOrientation(1);
            SpoilersTextView r1 = new SpoilersTextView(r12);
            this.titleView = r1;
            r1.setTextSize(1, 15.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            SpoilersTextView r0 = this.titleView;
            int r14 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r14, r13));
            this.titleView.setGravity(17);
            addView(this.titleView, LayoutHelper.createLinear(-1, -2, 55, 32, 20, 32, 0));
            LinkSpanDrawable.LinksTextView r02 = TextHelper.makeLinkTextView(r12, 13.0f, r14, false, r13);
            this.textView = r02;
            r02.setGravity(17);
            addView(this.textView, LayoutHelper.createLinear(-1, -2, 55, 32.0f, 9.33f, 32.0f, 0.0f));
            LinearLayout r03 = new LinearLayout(r12);
            r03.setOrientation(0);
            this.no = new ButtonWithCounterView(r12, r13).setRound();
            this.yes = new ButtonWithCounterView(r12, r13).setRound();
            r03.addView(this.no, LayoutHelper.createLinear(0, 42, 1.0f, 112, 0, 0, 12, 0));
            r03.addView(this.yes, LayoutHelper.createLinear(0, 42, 1.0f, 112, 0, 0, 0, 0));
            addView(r03, LayoutHelper.createLinear(-1, -2, 55, 24, 18, 24, 16));
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        public void set(CharSequence r4, CharSequence r5, CharSequence r6, View.OnClickListener r7, CharSequence r8, View.OnClickListener r9) {
            SpoilersTextView r0 = this.titleView;
            int r2 = 0;
            r0.setText(Emoji.replaceEmoji(r4, r0.getPaint().getFontMetricsInt(), false));
            LinkSpanDrawable.LinksTextView r42 = this.textView;
            r42.setText(Emoji.replaceEmoji(r5, r42.getPaint().getFontMetricsInt(), false));
            ButtonWithCounterView r43 = this.no;
            if (TextUtils.isEmpty(r6) == false) goto L5;
            r2 = 8;
        L5:
            r43.setVisibility(r2);
            this.no.setText(r6);
            this.no.setOnClickListener(r7);
            this.yes.setText(r8);
            this.yes.setOnClickListener(r9);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            SpoilersTextView r0 = this.titleView;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            this.textView.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        }
    }

    public static /* synthetic */ void $r8$lambda$0YgUeh6q6De4WWdKjoe84zuFJP8(SettingsActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: $r8$lambda$8hhi2x4PD9f6hQcpVE0hX-HtuF4, reason: not valid java name */
    public static /* synthetic */ void m18822$r8$lambda$8hhi2x4PD9f6hQcpVE0hXHtuF4(SettingsActivity r0, ValueAnimator r1) {
        r0.lambda$updateActionBarVisible$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$9Q7wC5GPsCEq5Sw13kZIXy2dYac(int r0, DialogInterface r1, int r2) {
        int r12 = 2 - r2;
        if (r12 != r0) goto L6;
        SharedConfig.overrideDevicePerformanceClass(-1);
        return;
    L6:
        SharedConfig.overrideDevicePerformanceClass(r12);
    }

    /* renamed from: $r8$lambda$A_AHllNO8ggUTSXh3-u_pZL8jYQ, reason: not valid java name */
    public static /* synthetic */ void m18823$r8$lambda$A_AHllNO8ggUTSXh3u_pZL8jYQ(SettingsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$openDebugMenu$20(r1, r2);
    }

    /* renamed from: $r8$lambda$E-Iunxmops8411FCreHyEW1-zYE, reason: not valid java name */
    public static /* synthetic */ void m18824$r8$lambda$EIunxmops8411FCreHyEW1zYE(SettingsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$openDebugMenu$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FF_lUvV2JJuqs6JDH1StsK_LWfk(SettingsActivity r0, View r1) {
        r0.lambda$fillItems$11(r1);
    }

    /* renamed from: $r8$lambda$G2MOok-eGRCtFTYaiu420g1Zgjg, reason: not valid java name */
    public static /* synthetic */ void m18825$r8$lambda$G2MOokeGRCtFTYaiu420g1Zgjg(SettingsActivity r0, TLRPC.TL_error r1, TLObject r2, String r3) {
        r0.lambda$didUploadPhoto$23(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$G3-DXYHX2NcLWEp3eGqimkckOo0, reason: not valid java name */
    public static /* synthetic */ void m18826$r8$lambda$G3DXYHX2NcLWEp3eGqimkckOo0(SettingsActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HBri1lUwAY_zrYWb5OMF05pYP7s(SettingsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HuJ13nlksXAZwiY916SP_bD8wgU(SettingsActivity r0) {
        r0.lambda$createView$3();
    }

    public static /* synthetic */ void $r8$lambda$IXBdIoAiIkTjki6t_2sbAKUTS54(SettingsActivity r0) {
        r0.lambda$onLongClick$17();
    }

    public static /* synthetic */ void $r8$lambda$R2KSasE94PQAacIUhOjx6CjJdd4(SettingsActivity r0, TLRPC.InputFile r1, TLRPC.InputFile r2, TLRPC.VideoSize r3, double r4, String r6, TLRPC.PhotoSize r7, TLRPC.PhotoSize r8) {
        r0.lambda$didUploadPhoto$25(r1, r2, r3, r4, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$UffchZ-kJStPtntoKL3OeU4Qq3Q, reason: not valid java name */
    public static /* synthetic */ void m18827$r8$lambda$UffchZkJStPtntoKL3OeU4Qq3Q(SettingsActivity r0, View r1) {
        r0.lambda$fillItems$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$V12FTnnMx5G5k_wW_EKvPJ7QzZo(SettingsActivity r0, View r1) {
        r0.lambda$fillItems$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZE47s89U9nSH3nmxmUTUScYNxN8(SettingsActivity r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$didUploadPhoto$24(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$b-tiAlgFgKskjD8IqJo9rTTkaQ0, reason: not valid java name */
    public static /* synthetic */ void m18828$r8$lambda$btiAlgFgKskjD8IqJo9rTTkaQ0(SettingsActivity r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$bY74nDloMU376k4kE8vPWAn4ygE(SettingsActivity r0, TLRPC.TL_attachMenuBot r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$onClick$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$cWsNBIIPSOi1BtMHvlu_TBSOEOI(SettingsActivity r0, TLRPC.TL_attachMenuBot r1, Boolean r2) {
        r0.lambda$onClick$16(r1, r2);
    }

    /* renamed from: $r8$lambda$dkzya7ANpQJhkyUzT9wKNE_x-iQ, reason: not valid java name */
    public static /* synthetic */ boolean m18829$r8$lambda$dkzya7ANpQJhkyUzT9wKNE_xiQ(SettingsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$eG-ZAiVz9ru-YPrIvPktXidXr74, reason: not valid java name */
    public static /* synthetic */ void m18830$r8$lambda$eGZAiVz9ruYPrIvPktXidXr74(SettingsActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadSessionsCount$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$habZ6uJV7eFNnohgSDY8uOsuPTk(SettingsActivity r0, DialogInterface r1, int r2) {
        r0.lambda$openDebugMenu$22(r1, r2);
    }

    /* renamed from: $r8$lambda$hishHXE0W-JHsgIcZpIlK8zQkh0, reason: not valid java name */
    public static /* synthetic */ void m18831$r8$lambda$hishHXE0WJHsgIcZpIlK8zQkh0(SettingsActivity r0) {
        r0.lambda$fillItems$9();
    }

    /* renamed from: $r8$lambda$lrkxH2z-Mk_Vom-IfgeME6Q4QSc, reason: not valid java name */
    public static /* synthetic */ void m18832$r8$lambda$lrkxH2zMk_VomIfgeME6Q4QSc(SettingsActivity r0, View r1) {
        r0.lambda$fillItems$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$oRgmyxcY_XMy2IoMSE1GC3ewhZw(SettingsActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$p5nE7q5mv1ZsXbISNbHHvepggts(SettingsActivity r0, String r1) {
        r0.lambda$onLongClick$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$qS3RyeU0Z3HL4DI9Dxk1Sc8y0jY(SettingsActivity r0, View r1) {
        r0.lambda$fillItems$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$sEss7fQXvchLU0IeLN81xcRLXN8(SettingsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadSessionsCount$1(r1, r2);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$vtPL8UshIYFGNSpFWbyovHZii1g(SettingsActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    /* renamed from: $r8$lambda$w4CL1gIVquG1EPi9FPGRA2W-2kY, reason: not valid java name */
    public static /* synthetic */ void m18833$r8$lambda$w4CL1gIVquG1EPi9FPGRA2W2kY(SettingsActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$wP8owmPdSSGJetjE9FgLZBKzIqY(DialogInterface r0) {
    }

    /* renamed from: $r8$lambda$yAGU7DJZp0zRfL6pg-599vxn7zc, reason: not valid java name */
    public static /* synthetic */ void m18834$r8$lambda$yAGU7DJZp0zRfL6pg599vxn7zc(SettingsActivity r0, TLRPC.TL_attachMenuBot r1) {
        r0.lambda$onClick$14(r1);
    }

    /* renamed from: -$$Nest$fgetanimatorSearchPageVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m18835$$Nest$fgetanimatorSearchPageVisible(SettingsActivity r0) {
        return r0.animatorSearchPageVisible;
    }

    /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m18836$$Nest$fgetavatarAnimation(SettingsActivity r0) {
        return r0.avatarAnimation;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m18837$$Nest$fgetavatarProgressView(SettingsActivity r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetavatarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m18838$$Nest$fgetavatarView(SettingsActivity r0) {
        return r0.avatarView;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m18839$$Nest$fgetcontentView(SettingsActivity r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m18840$$Nest$fgetiBlur3SourceGlass(SettingsActivity r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m18841$$Nest$fgetiBlur3SourceGlassFrosted(SettingsActivity r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m18842$$Nest$fgetlistView(SettingsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetnavigationBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18843$$Nest$fgetnavigationBarHeight(SettingsActivity r0) {
        return r0.navigationBarHeight;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m18844$$Nest$fgetscrollableViewNoiseSuppressor(SettingsActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProfileActivity.SearchAdapter m18845$$Nest$fgetsearch(SettingsActivity r0) {
        return r0.search;
    }

    /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18846$$Nest$fputavatarAnimation(SettingsActivity r0, AnimatorSet r1) {
        r0.avatarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18847$$Nest$fputiBlur3Invalidated(SettingsActivity r0, boolean r1) {
        r0.iBlur3Invalidated = r1;
    }

    /* renamed from: -$$Nest$fputmainTabsHiddenByScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18848$$Nest$fputmainTabsHiddenByScroll(SettingsActivity r0, boolean r1) {
        r0.mainTabsHiddenByScroll = r1;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18849$$Nest$fputquery(SettingsActivity r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18850$$Nest$mblur3_InvalidateBlur(SettingsActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mpresentSettingFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18851$$Nest$mpresentSettingFragment(SettingsActivity r0, BaseFragment r1) {
        r0.presentSettingFragment(r1);
    }

    /* renamed from: -$$Nest$mupdateActionBarVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18852$$Nest$mupdateActionBarVisible(SettingsActivity r0) {
        r0.updateActionBarVisible();
    }

    public SettingsActivity() {
        this(null);
    }

    public SettingsActivity(Bundle r7) {
        super(r7);
        this.animatorSearchPageVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 350);
        this.versionViewPressCount = 0;
        this.avatarUploadingRequest = -1;
        ArrayList<RectF> r6 = new ArrayList();
        this.iBlur3Positions = r6;
        RectF r72 = new RectF();
        this.iBlur3PositionActionBar = r72;
        RectF r0 = new RectF();
        this.iBlur3PositionMainTabs = r0;
        r6.add(r72);
        r6.add(r0);
        if (Build.VERSION.SDK_INT < 31) goto L6;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = new BlurredBackgroundSourceRenderNode(null);
        return;
    L6:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
    }

    public static /* synthetic */ ActionBar access$000(SettingsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(SettingsActivity r0) {
        return r0.resourceProvider;
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L18;
        int r1 = AndroidUtilities.dp(48.0f);
        this.iBlur3PositionActionBar.set(0.0f, -r1, this.fragmentView.getMeasuredWidth(), this.actionBar.getMeasuredHeight() + r1);
        MainTabsUiHelper.setBlurBounds(this.iBlur3PositionMainTabs, this.fragmentView, this.navigationBarHeight);
        RectF r12 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L10;
        float r0 = 0.0f;
    L11:
        r12.inset(0.0f, r0);
        DownscaleScrollableNoiseSuppressor r02 = this.scrollableViewNoiseSuppressor;
        ArrayList<RectF> r13 = this.iBlur3Positions;
        if (this.hasMainTabs == false) goto L14;
        int r2 = 2;
    L15:
        r02.setupRenderNodes(r13, r2);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    L14:
        r2 = 1;
        goto L15
    L10:
        r0 = -AndroidUtilities.dp(48.0f);
        goto L11
    }

    private void checkUi_menuItems() {
        FragmentFloatingButton.setAnimatedVisibility(this.otherItem, 1.0f - this.animatorSearchPageVisible.getFloatValue());
        ImageView r0 = this.actionBar.getBackButton();
        if (this.hasMainTabs == false) goto L5;
        float r1 = 0.0f;
    L6:
        FragmentFloatingButton.setAnimatedVisibility(r0, AndroidUtilities.lerp(r1, 1.0f, this.animatorSearchPageVisible.getFloatValue()));
        return;
    L5:
        r1 = 1.0f;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fillItems(ArrayList<UItem> r20, UniversalAdapter r21) {
        if (this.searchItem.isSearchFieldVisible2() == false) goto L6;
        r20.add(UItem.asSpace(ActionBar.getCurrentActionBarHeight()));
        this.search.fillItems(r20);
        return;
    L6:
        r20.add(UItem.asCustomShadow(this.topView, 188));
        Set<String> r2 = getMessagesController().pendingSuggestions;
        if (r2.contains("PREMIUM_GRACE") == false) goto L10;
        r20.add(SuggestionCell.Factory.of(LocaleController.getString(R.string.GraceSuggestionTitle), LocaleController.getString(R.string.GraceSuggestionMessage), null, null, LocaleController.getString(R.string.GraceSuggestionButton), new SettingsActivity$$ExternalSyntheticLambda17(this)));
        r20.add(UItem.asShadow(null));
    L24:
        if (Theme.isCurrentThemeMonet() == false) goto L26;
        int r22 = R.drawable.ic_foreground_solid;
    L27:
        r20.add(SettingCell.Factory.of(-1, -1560528, -1560528, r22, LocaleController.getString(R.string.Preferences)));
        r20.add(UItem.asShadow(null));
        IconBackgroundColors r23 = IconBackgroundColors.BLUE;
        r20.add(SettingCell.Factory.of(1, r23.top, r23.bottom, R.drawable.settings_account, LocaleController.getString(R.string.SettingsAccount), LocaleController.getString(R.string.SettingsAccountInfo)));
        IconBackgroundColors r24 = IconBackgroundColors.ORANGE;
        r20.add(SettingCell.Factory.of(2, r24.top, r24.bottom, R.drawable.settings_chat, LocaleController.getString(R.string.SettingsChat), LocaleController.getString(R.string.SettingsChatInfo)));
        IconBackgroundColors r25 = IconBackgroundColors.GREEN;
        r20.add(SettingCell.Factory.of(3, r25.top, r25.bottom, R.drawable.settings_privacy, LocaleController.getString(R.string.SettingsPrivacySecurity), LocaleController.getString(R.string.SettingsPrivacySecurityInfo)));
        IconBackgroundColors r26 = IconBackgroundColors.RED;
        r20.add(SettingCell.Factory.of(5, r26.top, r26.bottom, R.drawable.settings_sounds, LocaleController.getString(R.string.SettingsNotifications), LocaleController.getString(R.string.SettingsNotificationsInfo)));
        IconBackgroundColors r27 = IconBackgroundColors.BLUE_DEEP;
        r20.add(SettingCell.Factory.of(6, r27.top, r27.bottom, R.drawable.settings_data, LocaleController.getString(R.string.SettingsData), LocaleController.getString(R.string.SettingsDataInfo)));
        IconBackgroundColors r28 = IconBackgroundColors.BLUE_ALT;
        r20.add(SettingCell.Factory.of(7, r28.top, r28.bottom, R.drawable.settings_folders, LocaleController.getString(R.string.SettingsFolders), LocaleController.getString(R.string.SettingsFoldersInfo)));
        IconBackgroundColors r29 = IconBackgroundColors.CYAN;
        r20.add(SettingCell.Factory.of(8, r29.top, r29.bottom, R.drawable.settings_devices, LocaleController.getString(R.string.SettingsDevices), LocaleController.getString(R.string.SettingsDevicesInfo), getDevicesCount()));
        IconBackgroundColors r210 = IconBackgroundColors.ORANGE_DEEP;
        r20.add(SettingCell.Factory.of(9, r210.top, r210.bottom, R.drawable.settings_power, LocaleController.getString(R.string.SettingsPowerSaving), LocaleController.getString(R.string.SettingsPowerSavingInfo)));
        IconBackgroundColors r211 = IconBackgroundColors.PURPLE;
        r20.add(SettingCell.Factory.of(10, r211.top, r211.bottom, R.drawable.settings_language, LocaleController.getString(R.string.SettingsLanguage), null, LocaleController.getCurrentLanguageName()));
        r20.add(UItem.asShadow(null));
        if (getMessagesController().premiumFeaturesBlocked() == true) goto L30;
        r20.add(SettingCell.Factory.of(11, -4826625, -10388225, R.drawable.settings_premium, LocaleController.getString(R.string.TelegramPremium)));
    L30:
        boolean r212 = getMessagesController().starsPurchaseAvailable();
        CharSequence r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r212 == false) goto L39;
        StarsController r213 = StarsController.getInstance(this.currentAccount);
        long r9 = r213.getBalance().amount;
        int r14 = R.drawable.settings_stars;
        String r15 = LocaleController.getString(R.string.TelegramStars);
        if (r213.balanceAvailable() == true) goto L35;
    L37:
        CharSequence r17 = _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        r20.add(SettingCell.Factory.of(12, -1071598, -1608430, r14, r15, null, r17));
        goto L39
    L35:
        if (r9 <= 0) goto L37;
        r17 = StarsIntroActivity.formatStarsAmount(r213.getBalance(), 0.85f, ' ');
    L39:
        StarsController.getInstance(this.currentAccount, true).getBalance();
        if (BuildVars.isBetaApp() == false) goto L42;
    L47:
        StarsController r214 = StarsController.getTonInstance(this.currentAccount);
        long r10 = r214.getBalance().amount;
        int r152 = R.drawable.settings_gram_24;
        String r16 = LocaleController.getString(R.string.MyTON);
        if (r214.balanceAvailable() == true) goto L50;
    L52:
        r20.add(SettingCell.Factory.of(13, -14965523, -15431455, r152, r16, null, r3));
    L53:
        TLRPC.TL_attachMenuBots r215 = MediaDataController.getInstance(UserConfig.selectedAccount).getAttachMenuBots();
        if (r215 == null) goto L67;
        ArrayList<TLRPC.TL_attachMenuBot> r5 = r215.bots;
        if (r5 == null) goto L67;
        if (r5.isEmpty() == true) goto L67;
        ArrayList<TLRPC.TL_attachMenuBot> r216 = r215.bots;
        int r52 = r216.size();
        int r6 = 0;
    L60:
        if (r6 >= r52) goto L67;
        TLRPC.TL_attachMenuBot r7 = r216.get(r6);
        r6 = r6 + 1;
        TLRPC.TL_attachMenuBot r72 = r7;
        if (r72.show_in_side_menu == false) goto L60;
        if (r72.bot_id != 1985737506) goto L60;
        UItem r8 = SettingCell.Factory.ofBot(r72, -14965523, -15431455, R.drawable.settings_wallet);
        r8.object = r72;
        r20.add(r8);
    L67:
        if (getMessagesController().premiumFeaturesBlocked() == true) goto L70;
        r20.add(SettingCell.Factory.of(15, -765355, -2148011, R.drawable.settings_business, LocaleController.getString(R.string.TelegramBusiness)));
    L70:
        if (getMessagesController().premiumPurchaseBlocked() == true) goto L73;
        r20.add(SettingCell.Factory.of(16, -816335, -1940716, R.drawable.settings_gift, LocaleController.getString(R.string.SendAGift)));
    L73:
        if (r20.get(r20.size() - 1).viewType == 7) goto L75;
        r20.add(UItem.asShadow(null));
    L75:
        r20.add(UItem.asHeader(LocaleController.getString(R.string.SettingsHelp)));
        IconBackgroundColors r217 = IconBackgroundColors.ORANGE;
        r20.add(SettingCell.Factory.of(17, r217.top, r217.bottom, R.drawable.settings_ask, LocaleController.getString(R.string.AskAQuestion)));
        IconBackgroundColors r218 = IconBackgroundColors.BLUE_LIGHT;
        r20.add(SettingCell.Factory.of(18, r218.top, r218.bottom, R.drawable.settings_faq, LocaleController.getString(R.string.TelegramFAQ)));
        IconBackgroundColors r219 = IconBackgroundColors.PURPLE;
        r20.add(SettingCell.Factory.of(23, r219.top, r219.bottom, R.drawable.settings_features, LocaleController.getString(R.string.TelegramFeatures)));
        IconBackgroundColors r220 = IconBackgroundColors.GREEN;
        r20.add(SettingCell.Factory.of(19, r220.top, r220.bottom, R.drawable.settings_policy, LocaleController.getString(R.string.PrivacyPolicy)));
        if (SharedSettings.experimentalSettingsAllowed.get() == false) goto L79;
        r20.add(UItem.asShadow(null));
        r20.add(UItem.asHeader("Experimental"));
        r20.add(SettingCell.Factory.of(24, -765355, -2148011, 0, LocaleController.getString(R.string.RoundVideoSettings)));
    L79:
        if (BuildVars.LOGS_ENABLED == false) goto L81;
    L82:
        r20.add(UItem.asShadow(null));
        r20.add(UItem.asHeader(LocaleController.getString(R.string.SettingsDebug)));
        r20.add(SettingCell.Factory.of(20, -11154873, -14175180, 0, LocaleController.getString(R.string.DebugSendLogs)));
        r20.add(SettingCell.Factory.of(21, -11154873, -14175180, 0, LocaleController.getString(R.string.DebugSendLastLogs)));
        r20.add(SettingCell.Factory.of(22, -765355, -2148011, 0, LocaleController.getString(R.string.DebugClearLogs), null, FileLog.getLogDirSize()));
    L83:
        r20.add(UItem.asCustomShadow(this.versionView));
        return;
    L81:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L83;
    L50:
        if (r10 <= 0) goto L52;
        r3 = StarsIntroActivity.formatStarsAmount(r214.getBalance(), 0.85f, ' ');
        goto L52
    L42:
        if (StarsController.getInstance(this.currentAccount, true).balanceAvailable() == false) goto L53;
        if (StarsController.getInstance(this.currentAccount, true).hasTransactions() == true) goto L47;
        if (StarsController.getInstance(this.currentAccount, true).getBalance().positive() == false) goto L53;
    L26:
        r22 = R.drawable.ic_foreground;
        goto L27
    L10:
        if (r2.contains("VALIDATE_PHONE_NUMBER") == false) goto L21;
        if (getUserConfig().getCurrentUser() == null) goto L21;
        String r221 = PhoneFormat.getInstance().format("+" + getUserConfig().getCurrentUser().phone);
        String r32 = LocaleController.formatString(R.string.CheckPhoneNumber, new Object[]{r221});
        if (ExteraConfig.getHidePhoneNumber() == false) goto L18;
        int r53 = r32.indexOf(r221);
        if (r53 < 0) goto L18;
        SpannableStringBuilder r62 = new SpannableStringBuilder(r32);
        TextStyleSpan.TextStyleRun r33 = new TextStyleSpan.TextStyleRun();
        r33.flags |= 256;
        r62.setSpan(new TextStyleSpan(r33), r53, r221.length() + r53, 33);
        String r82 = r62;
    L19:
        r20.add(SuggestionCell.Factory.of(r82, AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.CheckPhoneNumberInfo), new SettingsActivity$$ExternalSyntheticLambda18(this)), LocaleController.getString(R.string.CheckPhoneNumberNo), new SettingsActivity$$ExternalSyntheticLambda19(this), StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.CheckPhoneNumberYes2)), new SettingsActivity$$ExternalSyntheticLambda20(this)));
        r20.add(UItem.asShadow(null));
    L18:
        r82 = r32;
    L21:
        if (r2.contains("VALIDATE_PASSWORD") == false) goto L24;
        r20.add(SuggestionCell.Factory.of(LocaleController.getString(R.string.YourPasswordHeader), LocaleController.getString(R.string.YourPasswordRemember), LocaleController.getString(R.string.YourPasswordRememberNo), new SettingsActivity$$ExternalSyntheticLambda21(this), LocaleController.getString(R.string.YourPasswordRememberYes), new SettingsActivity$$ExternalSyntheticLambda22(this)));
        r20.add(UItem.asShadow(null));
        goto L24
    }

    private String getDevicesCount() {
        int r2 = getMessagesController().lastKnownSessionsCount;
        if (r2 > 0) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return String.format(LocaleController.getInstance().getCurrentLocale(), TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r2)});
    }

    private /* synthetic */ void lambda$createView$2() {
        this.listView.postOnAnimation(new SettingsActivity$$ExternalSyntheticLambda8(this));
    }

    private /* synthetic */ void lambda$createView$3() {
        MessagesController.getInstance(this.currentAccount).deleteUserPhoto(null);
    }

    private /* synthetic */ void lambda$createView$5(View r4) {
        TLRPC.User r42 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        if (r42 != null) goto L5;
        r42 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
    L5:
        if (r42 != null) goto L7;
        return;
    L7:
        ImageUpdater r0 = this.imageUpdater;
        TLRPC.UserProfilePhoto r43 = r42.photo;
        if (r43 != null) goto L10;
    L14:
        boolean r44 = false;
    L15:
        r0.openMenu(r44, new SettingsActivity$$ExternalSyntheticLambda15(this), new SettingsActivity$$ExternalSyntheticLambda16(), 0);
        return;
    L10:
        if (r43.photo_big == null) goto L14;
        if ((r43 instanceof TLRPC.TL_userProfilePhotoEmpty) == true) goto L14;
        r44 = true;
        goto L15
    }

    private /* synthetic */ void lambda$createView$6(View r2) {
        int r22 = this.versionViewPressCount + 1;
        this.versionViewPressCount = r22;
        if (r22 < 2) goto L5;
    L11:
        openDebugMenu();
        return;
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L11;
        Toast.makeText(getParentActivity(), LocaleController.getString(R.string.DebugMenuLongPress), 0).show();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$didUploadPhoto$23(TLRPC.TL_error r13, TLObject r14, String r15) {
        this.avatarUploadingRequest = -1;
        if (r13 != null) goto L35;
        TLRPC.User r132 = getMessagesController().getUser(Long.valueOf(getUserConfig().getClientUserId()));
        if (r132 != null) goto L10;
        r132 = getUserConfig().getCurrentUser();
        if (r132 != null) goto L9;
        return;
    L9:
        getMessagesController().putUser(r132, false);
    L11:
        TLRPC.TL_photos_photo r142 = (TLRPC.TL_photos_photo) r14;
        ArrayList<TLRPC.PhotoSize> r3 = r142.photo.sizes;
        TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(r3, 150);
        TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r3, 800);
        if (r142.photo.video_sizes.isEmpty() == false) goto L14;
        TLRPC.VideoSize r5 = null;
    L15:
        TLRPC.TL_userProfilePhoto r6 = new TLRPC.TL_userProfilePhoto();
        r132.photo = r6;
        r6.photo_id = r142.photo.id;
        if (r4 == null) goto L18;
        r6.photo_small = r4.location;
    L18:
        if (r32 == null) goto L20;
        r6.photo_big = r32.location;
    L20:
        if (r4 != null) goto L22;
    L24:
        if (r5 == null) goto L27;
        if (r15 == null) goto L27;
        new File(r15).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r5, "mp4", true));
    L31:
        getMessagesController().getDialogPhotos(r132.id).addPhotoAtStart(r142.photo);
        ArrayList r152 = new ArrayList();
        r152.add(r132);
        getMessagesStorage().putUsersAndChats(r152, null, false, true);
        TLRPC.UserFull r153 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r153 == null) goto L34;
        r153.profile_photo = r142.photo;
        getMessagesStorage().updateUserInfo(r153, false);
    L34:
        setInfo(r132);
    L27:
        if (r32 == null) goto L31;
        if (this.avatarBig == null) goto L31;
        FileLoader.getInstance(this.currentAccount).getPathToAttach(this.avatarBig, true).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r32, true));
        goto L31
    L22:
        if (this.avatar == null) goto L24;
        FileLoader.getInstance(this.currentAccount).getPathToAttach(this.avatar, true).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r4, true));
        ImageLoader.getInstance().replaceImageInCache(this.avatar.volume_id + "_" + this.avatar.local_id + "@90_90", r4.location.volume_id + "_" + r4.location.local_id + "@90_90", ImageLocation.getForUserOrChat(this.currentAccount, r132, 1), false);
        goto L24
    L14:
        r5 = FileLoader.getClosestVideoSizeWithSize(r142.photo.video_sizes, 1000);
        goto L15
    L10:
        getUserConfig().setCurrentUser(r132);
    L35:
        this.avatar = null;
        this.avatarBig = null;
        showAvatarProgress(false, true);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_ALL)});
        getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        getUserConfig().saveConfig(true);
    }

    private /* synthetic */ void lambda$didUploadPhoto$24(String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SettingsActivity$$ExternalSyntheticLambda25(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$didUploadPhoto$25(TLRPC.InputFile r2, TLRPC.InputFile r3, TLRPC.VideoSize r4, double r5, String r7, TLRPC.PhotoSize r8, TLRPC.PhotoSize r9) {
        if (r2 != null) goto L9;
        if (r3 != null) goto L9;
        if (r4 != null) goto L9;
        TLRPC.FileLocation r22 = r8.location;
        this.avatar = r22;
        this.avatarBig = r9.location;
        this.avatarView.setImage(ImageLocation.getForLocal(r22), "90_90", this.avatarDrawable, null);
        showAvatarProgress(true, false);
    L19:
        this.actionBar.createMenu().requestLayout();
        return;
    L9:
        if (this.avatar != null) goto L11;
        return;
    L11:
        TLRPC.TL_photos_uploadProfilePhoto r82 = new TLRPC.TL_photos_uploadProfilePhoto();
        if (r2 == null) goto L14;
        r82.file = r2;
        r82.flags |= 1;
    L14:
        if (r3 == null) goto L16;
        r82.video = r3;
        int r23 = r82.flags;
        r82.video_start_ts = r5;
        r82.flags = r23 | 6;
    L16:
        if (r4 == null) goto L18;
        r82.video_emoji_markup = r4;
        r82.flags |= 16;
    L18:
        this.avatarUploadingRequest = getConnectionsManager().sendRequest(r82, new SettingsActivity$$ExternalSyntheticLambda23(this, r7));
        goto L19
    }

    private /* synthetic */ void lambda$fillItems$10(View r2) {
        presentFragment(new ActionIntroActivity(3));
    }

    private /* synthetic */ void lambda$fillItems$11(View r3) {
        getMessagesController().removeSuggestion(0, "VALIDATE_PHONE_NUMBER");
    }

    private /* synthetic */ void lambda$fillItems$12(View r3) {
        presentFragment(new TwoStepVerificationSetupActivity(8, null));
    }

    private /* synthetic */ void lambda$fillItems$13(View r3) {
        getMessagesController().removeSuggestion(0, "VALIDATE_PASSWORD");
    }

    private /* synthetic */ void lambda$fillItems$8(View r3) {
        Browser.openUrl(getContext(), getMessagesController().premiumManageSubscriptionUrl);
        getMessagesController().removeSuggestion(0, "PREMIUM_GRACE");
    }

    private /* synthetic */ void lambda$fillItems$9() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.CheckPhoneNumberLearnMoreUrl));
    }

    private /* synthetic */ void lambda$loadSessionsCount$0(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L11;
        int r12 = ((TL_account.authorizations) r2).authorizations.size();
        if (getMessagesController().lastKnownSessionsCount == r12) goto L12;
        getMessagesController().lastKnownSessionsCount = r12;
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L13;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L14;
        r02.update(true);
        return;
    L14:
        return;
    L13:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$loadSessionsCount$1(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new SettingsActivity$$ExternalSyntheticLambda26(this, r3, r2));
    }

    private /* synthetic */ void lambda$onClick$14(TLRPC.TL_attachMenuBot r5) {
        r5.side_menu_disclaimer_needed = false;
        r5.inactive = false;
        LaunchActivity.showAttachMenuBot(LaunchActivity.instance, this.currentAccount, r5, null, true);
        MediaDataController.getInstance(this.currentAccount).updateAttachMenuBotsInCache();
    }

    private /* synthetic */ void lambda$onClick$15(TLRPC.TL_attachMenuBot r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new SettingsActivity$$ExternalSyntheticLambda29(this, r1));
    }

    private /* synthetic */ void lambda$onClick$16(TLRPC.TL_attachMenuBot r4, Boolean r5) {
        TLRPC.TL_messages_toggleBotInAttachMenu r52 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r52.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r4.bot_id);
        r52.enabled = true;
        r52.write_allowed = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new SettingsActivity$$ExternalSyntheticLambda24(this, r4), 66);
    }

    private /* synthetic */ void lambda$onLongClick$17() {
        this.listView.adapter.update(true);
    }

    private /* synthetic */ void lambda$onLongClick$18(String r1) {
        AndroidUtilities.addToClipboard(r1);
        BulletinFactory.of(this).createCopyLinkBulletin().show();
    }

    private /* synthetic */ void lambda$openDebugMenu$19(TLObject r1, TLRPC.TL_error r2) {
        getMessagesController().loadAppConfig();
    }

    private /* synthetic */ void lambda$openDebugMenu$20(TLObject r2, TLRPC.TL_error r3) {
        TLRPC.TL_help_dismissSuggestion r22 = new TLRPC.TL_help_dismissSuggestion();
        r22.suggestion = "VALIDATE_PASSWORD";
        r22.peer = new TLRPC.TL_inputPeerEmpty();
        getConnectionsManager().sendRequest(r22, new SettingsActivity$$ExternalSyntheticLambda30(this));
    }

    private /* synthetic */ void lambda$openDebugMenu$22(DialogInterface r38, int r39) {
        int r4 = 0;
        if (r39 != 0) goto L6;
        getUserConfig().syncContacts = true;
        getUserConfig().saveConfig(false);
        getContactsController().forceImportContacts();
        return;
    L6:
        long r5 = 0;
        if (r39 != 1) goto L11;
        getContactsController().loadContacts(false, 0);
        return;
    L11:
        if (r39 != 2) goto L15;
        getContactsController().resetImportedContacts();
        return;
    L15:
        if (r39 != 3) goto L19;
        getMessagesController().forceResetDialogs();
        return;
    L19:
        if (r39 != 4) goto L28;
        BuildVars.LOGS_ENABLED = !BuildVars.LOGS_ENABLED;
        ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0).edit().putBoolean("logsEnabled", BuildVars.LOGS_ENABLED).commit();
        this.listView.adapter.update(true);
        if (BuildVars.LOGS_ENABLED == false) goto L303;
        FileLog.d("app start time = " + ApplicationLoader.startTime);
        FileLog.d("buildVersion = " + ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0).versionCode);     // Catch: Exception -> L25
        return;
    L25:
        e = move-exception;
        FileLog.e(e);
        return;
    L303:
        return;
    L28:
        if (r39 != 5) goto L32;
        SharedConfig.toggleInappCamera();
        return;
    L32:
        if (r39 != 6) goto L56;
        getMessagesStorage().clearSentMedia();
        SharedConfig.setNoSoundHintShowed(false);
        MessagesController.getGlobalMainSettings().edit().remove("archivehint").remove("proximityhint").remove("archivehint_l").remove("searchpostsnew").remove("speedhint").remove("gifhint").remove("reminderhint").remove("soundHint").remove("themehint").remove("bganimationhint").remove("filterhint").remove("n_0").remove("storyprvhint").remove("storyhint").remove("storyhint2").remove("storydualhint").remove("storysvddualhint").remove("stories_camera").remove("dualcam").remove("dualmatrix").remove("dual_available").remove("archivehint").remove("askNotificationsAfter").remove("askNotificationsDuration").remove("viewoncehint").remove("voicepausehint").remove("taptostorysoundhint").remove("nothanos").remove("voiceoncehint").remove("savedhint").remove("savedsearchhint").remove("savedsearchtaghint").remove("newppsms").remove("monetizationadshint").remove("seekSpeedHintShowed").remove("unsupport_video/av01").remove("statusgiftpage").remove("multistorieshint").remove("trimvoicehint").remove("taptostoryhighlighthint").remove("proxycheckstatusip").remove("callmiconstart").remove("showchattagsinfo").remove("language_showed2").remove("aihintshown").remove("savedmsgschatshint").apply();
        HintsController.resetAll();
        SharedPrefsHelper.cleanupAccount(this.currentAccount);
        MessagesController.getEmojiSettings(this.currentAccount).edit().remove("featured_hidden").remove("emoji_featured_hidden").commit();
        MessagesController.getGlobalNotificationsSettings().edit().remove("disable_sharing_learn").remove("askedAboutFSILockscreen").apply();
        SharedConfig.textSelectionHintShows = 0;
        SharedConfig.lockRecordAudioVideoHint = 0;
        SharedConfig.stickersReorderingHintUsed = false;
        SharedConfig.forwardingOptionsHintShown = false;
        SharedConfig.replyingOptionsHintShown = false;
        SharedConfig.messageSeenHintCount = 3;
        SharedConfig.emojiInteractionsHintCount = 3;
        SharedConfig.dayNightThemeSwitchHintCount = 3;
        SharedConfig.fastScrollHintCount = 3;
        SharedConfig.stealthModeSendMessageConfirm = 2;
        SharedConfig.updateStealthModeSendMessageConfirm(2);
        SharedConfig.setStoriesReactionsLongPressHintUsed(false);
        SharedConfig.setStoriesIntroShown(false);
        SharedConfig.setMultipleReactionsPromoShowed(false);
        ChatThemeController.getInstance(this.currentAccount).clearCache();
        getNotificationCenter().postNotificationName(NotificationCenter.newSuggestionsAvailable, new Object[0]);
        RestrictedLanguagesSelectActivity.cleanup();
        PersistColorPalette.getInstance(this.currentAccount).cleanup();
        SharedPreferences r0 = getMessagesController().getMainSettings();
        SharedPreferences.Editor r2 = r0.edit();
        r2.remove("peerColors").remove("profilePeerColors").remove("boostingappearance").remove("bizbothint").remove("movecaptionhint");
        Iterator<String> r02 = r0.getAll().keySet().iterator();
    L35:
        if (r02.hasNext() == false) goto L47;
        String r3 = r02.next();
        if (r3.contains("show_gift_for_") == true) goto L46;
        if (r3.contains("bdayhint_") == true) goto L46;
        if (r3.contains("bdayanim_") == true) goto L46;
        if (r3.startsWith("ask_paid_message_") == true) goto L46;
        if (r3.startsWith("topicssidetabs") == false) goto L35;
    L46:
        r2.remove(r3);
        goto L35
    L47:
        r2.apply();
        SharedPreferences.Editor r03 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        Iterator<String> r1 = MessagesController.getNotificationsSettings(this.currentAccount).getAll().keySet().iterator();
    L49:
        if (r1.hasNext() == false) goto L53;
        String r22 = r1.next();
        if (r22.startsWith("dialog_bar_botver") == false) goto L49;
        r03.remove(r22);
        goto L49
    L53:
        r03.apply();
        return;
    L56:
        if (r39 != 7) goto L60;
        VoIPHelper.showCallDebugSettings(getParentActivity());
        return;
    L60:
        if (r39 != 8) goto L64;
        SharedConfig.toggleRoundCamera16to9();
        return;
    L64:
        if (r39 != 9) goto L68;
        ((LaunchActivity) getParentActivity()).checkAppUpdate(true, null);
        return;
    L68:
        if (r39 != 10) goto L72;
        getMessagesStorage().readAllDialogs(-1);
        return;
    L72:
        if (r39 != 11) goto L76;
        SharedConfig.toggleDisableVoiceAudioEffects();
        return;
    L76:
        if (r39 != 12) goto L80;
        SharedConfig.pendingAppUpdate = null;
        SharedConfig.saveConfig();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.appUpdateAvailable, new Object[0]);
        return;
    L80:
        if (r39 != 13) goto L282;
        Set<String> r04 = getMessagesController().pendingSuggestions;
        r04.add("VALIDATE_PHONE_NUMBER");
        r04.add("VALIDATE_PASSWORD");
        getNotificationCenter().postNotificationName(NotificationCenter.newSuggestionsAvailable, new Object[0]);
        return;
    L282:
        if (r39 != 14) goto L89;
        ApplicationLoader.applicationContext.deleteDatabase("webview.db");
        ApplicationLoader.applicationContext.deleteDatabase("webviewCache.db");
        WebStorage.getInstance().deleteAllData();
        WebView r05 = new WebView(ApplicationLoader.applicationContext);     // Catch: Exception -> L281
        r05.clearHistory();     // Catch: Exception -> L281
        r05.destroy();     // Catch: Exception -> L281
        return;
    L89:
        if (r39 != 15) goto L93;
        CookieManager r06 = CookieManager.getInstance();
        r06.removeAllCookies(null);
        r06.flush();
        return;
    L93:
        if (r39 != 16) goto L101;
        SharedConfig.toggleDebugWebView();
        Activity r07 = getParentActivity();
        if (SharedConfig.debugWebView == false) goto L97;
        int r12 = R.string.DebugMenuWebViewDebugEnabled;
    L98:
        Toast.makeText(r07, LocaleController.getString(r12), 0).show();
        return;
    L97:
        r12 = R.string.DebugMenuWebViewDebugDisabled;
        goto L98
    L101:
        if (r39 != 17) goto L108;
        SharedConfig.toggleForceDisableTabletMode();
        Activity r08 = getParentActivity();
        if (r08 == null) goto L105;
        Intent r13 = r08.getPackageManager().getLaunchIntentForPackage(r08.getPackageName());
        r08.finishAffinity();
        r08.startActivity(r13);
    L105:
        System.exit(0);
        return;
    L108:
        if (r39 != 18) goto L112;
        FloatingDebugController.setActive((LaunchActivity) getParentActivity(), !FloatingDebugController.isActive());
        return;
    L112:
        if (r39 != 19) goto L116;
        getMessagesController().loadAppConfig();
        TLRPC.TL_help_dismissSuggestion r09 = new TLRPC.TL_help_dismissSuggestion();
        r09.suggestion = "VALIDATE_PHONE_NUMBER";
        r09.peer = new TLRPC.TL_inputPeerEmpty();
        getConnectionsManager().sendRequest(r09, new SettingsActivity$$ExternalSyntheticLambda27(this));
        return;
    L116:
        if (r39 != 20) goto L164;
        int r010 = ConnectionsManager.CPU_COUNT;
        int r23 = ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getMemoryClass();
        StringBuilder r7 = new StringBuilder();
        long r8 = 0;
        long r122 = 0;
        long r14 = 0;
        long r16 = 0;
        long r18 = 0;
        long r20 = 0;
        long r222 = 0;
        long r24 = 0;
    L119:
        if (r4 >= r010) goto L131;
        long r28 = r5;
        Long r52 = AndroidUtilities.getSysInfoLong("/sys/devices/system/cpu/cpu" + r4 + "/cpufreq/cpuinfo_min_freq");
        Long r53 = AndroidUtilities.getSysInfoLong("/sys/devices/system/cpu/cpu" + r4 + "/cpufreq/cpuinfo_cur_freq");
        Long r54 = AndroidUtilities.getSysInfoLong("/sys/devices/system/cpu/cpu" + r4 + "/cpufreq/cpuinfo_max_freq");
        Long r6 = AndroidUtilities.getSysInfoLong("/sys/devices/system/cpu/cpu" + r4 + "/cpu_capacity");
        r7.append("#");
        r7.append(r4);
        r7.append(" ");
        int r33 = r4;
        if (r52 == null) goto L123;
        r7.append("min=");
        Long r36 = r54;
        r7.append(r52.longValue() / 1000);
        r7.append(" ");
        r8 = r8 + (r52.longValue() / 1000);
        r122 = r122 + 1;
    L124:
        if (r53 == null) goto L126;
        r7.append("cur=");
        r7.append(r53.longValue() / 1000);
        r7.append(" ");
        r14 = r14 + (r53.longValue() / 1000);
        r16 = r16 + 1;
    L126:
        if (r36 == null) goto L128;
        r7.append("max=");
        r7.append(r36.longValue() / 1000);
        r7.append(" ");
        r18 = r18 + (r36.longValue() / 1000);
        r20 = r20 + 1;
    L128:
        if (r6 == null) goto L130;
        r7.append("cpc=");
        r7.append(r6);
        r7.append(" ");
        r222 = r222 + r6.longValue();
        r24 = r24 + 1;
    L130:
        r7.append("\n");
        r4 = r33 + 1;
        r5 = r28;
        goto L119
    L123:
        r36 = r54;
        goto L124
    L131:
        long r282 = r5;
        StringBuilder r42 = new StringBuilder();
        r42.append(Build.MANUFACTURER);
        r42.append(", ");
        r42.append(Build.MODEL);
        r42.append(" (");
        r42.append(Build.PRODUCT);
        r42.append(", ");
        r42.append(Build.DEVICE);
        r42.append(")  (android ");
        int r62 = Build.VERSION.SDK_INT;
        r42.append(r62);
        r42.append(")\n");
        if (r62 < 31) goto L134;
        r42.append("SoC: ");
        r42.append(Device$$ExternalSyntheticApiModelOutline0.m());
        r42.append(", ");
        r42.append(SharedConfig$$ExternalSyntheticApiModelOutline0.m());
        r42.append("\n");
    L134:
        String r55 = AndroidUtilities.getSysInfoString("/sys/kernel/gpu/gpu_model");
        if (r55 == null) goto L145;
        r42.append("GPU: ");
        r42.append(r55);
        Long r56 = AndroidUtilities.getSysInfoLong("/sys/kernel/gpu/gpu_min_clock");
        Long r11 = AndroidUtilities.getSysInfoLong("/sys/kernel/gpu/gpu_mm_min_clock");
        Long r30 = AndroidUtilities.getSysInfoLong("/sys/kernel/gpu/gpu_max_clock");
        if (r56 == null) goto L139;
        r42.append(", min=");
        long r332 = r8;
        r42.append(r56.longValue() / 1000);
    L140:
        if (r11 == null) goto L142;
        r42.append(", mmin=");
        r42.append(r11.longValue() / 1000);
    L142:
        if (r30 == null) goto L144;
        r42.append(", max=");
        r42.append(r30.longValue() / 1000);
    L144:
        r42.append("\n");
    L146:
        ConfigurationInfo r57 = ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getDeviceConfigurationInfo();
        r42.append("GLES Version: ");
        r42.append(r57.getGlEsVersion());
        r42.append("\nMemory: class=");
        r42.append(AndroidUtilities.formatFileSize(r23 * 1048576));
        ActivityManager.MemoryInfo r25 = new ActivityManager.MemoryInfo();
        ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getMemoryInfo(r25);
        r42.append(", total=");
        r42.append(AndroidUtilities.formatFileSize(r25.totalMem));
        r42.append(", avail=");
        r42.append(AndroidUtilities.formatFileSize(r25.availMem));
        r42.append(", low?=");
        r42.append(r25.lowMemory);
        r42.append(" (threshold=");
        r42.append(AndroidUtilities.formatFileSize(r25.threshold));
        r42.append(")\nCurrent class: ");
        r42.append(SharedConfig.performanceClassName(SharedConfig.getDevicePerformanceClass()));
        r42.append(", measured: ");
        r42.append(SharedConfig.performanceClassName(SharedConfig.measureDevicePerformanceClass()));
        if (r62 < 31) goto L149;
        r42.append(", suggest=");
        r42.append(ProfileActivity$16$$ExternalSyntheticApiModelOutline0.m());
    L149:
        r42.append("\n");
        r42.append(r010);
        r42.append(" CPUs");
        if (r122 <= r282) goto L153;
        r42.append(", avgMinFreq=");
        r42.append(r332 / r122);
    L153:
        if (r16 <= r282) goto L156;
        r42.append(", avgCurFreq=");
        r42.append(r14 / r16);
    L156:
        if (r20 <= r282) goto L159;
        r42.append(", avgMaxFreq=");
        r42.append(r18 / r20);
    L159:
        if (r24 <= r282) goto L161;
        r42.append(", avgCapacity=");
        r42.append(r222 / r24);
    L161:
        r42.append("\n");
        r42.append(r7);
        listCodecs(MediaController.VIDEO_MIME_TYPE, r42);
        listCodecs("video/hevc", r42);
        listCodecs("video/x-vnd.on2.vp8", r42);
        listCodecs("video/x-vnd.on2.vp9", r42);
        showDialog(new AnonymousClass8(this, getParentActivity(), null, r42.toString(), false, null, false));
        return;
    L139:
        r332 = r8;
        goto L140
    L145:
        r332 = r8;
        goto L146
    L164:
        if (r39 != 21) goto L189;
        AlertDialog.Builder r011 = new AlertDialog.Builder(getParentActivity(), this.resourceProvider);
        r011.setTitle("Force performance class");
        int r15 = SharedConfig.getDevicePerformanceClass();
        int r26 = SharedConfig.measureDevicePerformanceClass();
        if (r15 != 2) goto L168;
        String r58 = "**HIGH**";
    L169:
        String r63 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r26 != 2) goto L172;
        String r112 = " (measured)";
    L173:
        SpannableStringBuilder r59 = AndroidUtilities.replaceTags(r58.concat(r112));
        if (r15 != 1) goto L176;
        String r113 = "**AVERAGE**";
    L177:
        if (r26 != 1) goto L179;
        String r123 = " (measured)";
    L180:
        SpannableStringBuilder r114 = AndroidUtilities.replaceTags(r113.concat(r123));
        if (r15 != 0) goto L183;
        String r17 = "**LOW**";
    L184:
        if (r26 != 0) goto L186;
        r63 = " (measured)";
    L186:
        r011.setItems(new CharSequence[]{r59, r114, AndroidUtilities.replaceTags(r17.concat(r63))}, new SettingsActivity$$ExternalSyntheticLambda28(r26));
        r011.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        r011.show();
        return;
    L183:
        r17 = "LOW";
        goto L184
    L179:
        r123 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L180
    L176:
        r113 = "AVERAGE";
        goto L177
    L172:
        r112 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L173
    L168:
        r58 = "HIGH";
        goto L169
    L189:
        if (r39 != 22) goto L193;
        SharedConfig.toggleRoundCamera();
        return;
    L193:
        if (r39 != 23) goto L202;
        boolean r012 = DualCameraView.dualAvailableStatic(getContext());
        MessagesController.getGlobalMainSettings().edit().putBoolean("dual_available", !r012).apply();
        Activity r19 = getParentActivity();     // Catch: Exception -> L281
        if (r012 == true) goto L198;
        int r013 = R.string.DebugMenuDualOnToast;     // Catch: Exception -> L281
    L199:
        Toast.makeText(r19, LocaleController.getString(r013), 0).show();     // Catch: Exception -> L281
        return;
    L198:
        r013 = R.string.DebugMenuDualOffToast;     // Catch: Exception -> L281
        goto L199
    L202:
        if (r39 != 24) goto L208;
        SharedConfig.toggleSurfaceInStories();
    L205:
        if (r4 >= getParentLayout().getFragmentStack().size()) goto L310;
        getParentLayout().getFragmentStack().get(r4).clearSheets();
        r4 = r4 + 1;
        goto L205
    L310:
        return;
    L208:
        if (r39 != 25) goto L212;
        SharedConfig.togglePhotoViewerBlur();
        return;
    L212:
        if (r39 != 26) goto L216;
        SharedConfig.togglePaymentByInvoice();
        return;
    L216:
        if (r39 != 27) goto L220;
        getMediaDataController().loadAttachMenuBots(false, true);
        return;
    L220:
        if (r39 != 28) goto L224;
        SharedConfig.toggleUseCamera2(this.currentAccount);
        return;
    L224:
        if (r39 != 29) goto L228;
        BotBiometry.clear();
        BotLocation.clear();
        BotDownloads.clear();
        SetupEmojiStatusSheet.clear();
        return;
    L228:
        if (r39 != 30) goto L232;
        AuthTokensHelper.clearLogInTokens();
        return;
    L232:
        if (r39 != 31) goto L236;
        SharedConfig.toggleUseNewBlur();
        return;
    L236:
        if (r39 != 32) goto L240;
        SharedConfig.toggleBrowserAdaptableColors();
        return;
    L240:
        if (r39 != 33) goto L244;
        SharedConfig.toggleDebugVideoQualities();
        return;
    L244:
        if (r39 != 34) goto L248;
        SharedConfig.toggleUseSystemBoldFont();
        return;
    L248:
        if (r39 != 35) goto L252;
        MessagesController.getInstance(this.currentAccount).loadAppConfig(true);
        return;
    L252:
        if (r39 != 36) goto L256;
        SharedConfig.toggleForceForumTabs();
        return;
    L256:
        if (r39 != 37) goto L260;
        FileLog.getInstance().dumpMemory(true);
        return;
    L260:
        if (r39 != 38) goto L264;
        SharedConfig.toggleFastWallpaperDisabled();
        return;
    L264:
        if (r39 != 39) goto L270;
        SharedConfig.toggleFrameMetricsEnabled();
        LaunchActivity r014 = LaunchActivity.instance;
        if (r014 == null) goto L305;
        r014.checkFrameMetrics();
        return;
    L305:
        return;
    L270:
        if (r39 != 40) goto L274;
        SharedPreferences.Editor r015 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
        boolean r110 = !SharedConfig.shadowsInSections;
        SharedConfig.shadowsInSections = r110;
        r015.putBoolean("shadowsInSections", r110).apply();
        return;
    L274:
        if (r39 != 41) goto L278;
        SharedPreferences.Editor r016 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
        boolean r111 = !SharedConfig.debugViewMetrics;
        SharedConfig.debugViewMetrics = r111;
        r016.putBoolean("debugViewMetrics", r111).apply();
        return;
    L278:
        if (r39 != 42) goto L306;
        SharedSettings.experimentalSettingsAllowed.toggle();
        this.listView.adapter.update(true);
        return;
    L306:
        return;
    }

    private /* synthetic */ void lambda$updateActionBarVisible$7(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.actionBar.getTitlesContainer().setAlpha(r22);
        this.actionBarBackground.setAlpha(r22);
    }

    private void listCodecs(String r14, StringBuilder r15) {
        int r0 = MediaCodecList.getCodecCount();     // Catch: Exception -> L66
        ArrayList r1 = new ArrayList();     // Catch: Exception -> L66
        ArrayList r2 = new ArrayList();     // Catch: Exception -> L66
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r4 >= r0) goto L24;
        MediaCodecInfo r5 = MediaCodecList.getCodecInfoAt(r4);     // Catch: Exception -> L66
        if (r5 == null) goto L22;
        String[] r6 = r5.getSupportedTypes();     // Catch: Exception -> L66
        if (r6 == null) goto L22;
        int r7 = 0;
    L13:
        if (r7 >= r6.length) goto L22;
        if (r6[r7].equals(r14) == true) goto L17;
        r7 = r7 + 1;     // Catch: Exception -> L66
        goto L13
    L17:
        if (r5.isEncoder() == false) goto L19;
        ArrayList r52 = r2;
    L20:
        r52.add(Integer.valueOf(r4));     // Catch: Exception -> L66
        goto L22
    L19:
        r52 = r1;
    L22:
        r4 = r4 + 1;     // Catch: Exception -> L66
        goto L4
    L24:
        if (r1.isEmpty() == true) goto L26;
    L28:
        r15.append("\n");     // Catch: Exception -> L66
        r15.append(r1.size());     // Catch: Exception -> L66
        r15.append("+");     // Catch: Exception -> L66
        r15.append(r2.size());     // Catch: Exception -> L66
        r15.append(" ");     // Catch: Exception -> L66
        r15.append(r14.substring(6));     // Catch: Exception -> L66
        r15.append(" codecs:\n");     // Catch: Exception -> L66
        int r02 = 0;
    L31:
        if (r02 >= r1.size()) goto L47;
        if (r02 <= 0) goto L34;
        r15.append("\n");     // Catch: Exception -> L66
    L34:
        MediaCodecInfo r42 = MediaCodecList.getCodecInfoAt(((Integer) r1.get(r02)).intValue());     // Catch: Exception -> L66
        r15.append("{d} ");     // Catch: Exception -> L66
        r15.append(r42.getName());     // Catch: Exception -> L66
        r15.append(" (");     // Catch: Exception -> L66
        if (Build.VERSION.SDK_INT < 29) goto L45;
        if (r42.isHardwareAccelerated() == false) goto L40;
        r15.append("gpu");     // Catch: Exception -> L66
    L40:
        if (r42.isSoftwareOnly() == false) goto L43;
        r15.append("cpu");     // Catch: Exception -> L66
    L43:
        if (r42.isVendor() == false) goto L45;
        r15.append(", v");     // Catch: Exception -> L66
    L45:
        MediaCodecInfo.CodecCapabilities r43 = r42.getCapabilitiesForType(r14);     // Catch: Exception -> L66
        r15.append("; mi=");     // Catch: Exception -> L66
        r15.append(r43.getMaxSupportedInstances());     // Catch: Exception -> L66
        r15.append(")");     // Catch: Exception -> L66
        r02 = r02 + 1;     // Catch: Exception -> L66
    L47:
        if (r3 >= r2.size()) goto L64;
        if (r3 <= 0) goto L50;
    L51:
        r15.append("\n");     // Catch: Exception -> L66
    L52:
        MediaCodecInfo r03 = MediaCodecList.getCodecInfoAt(((Integer) r2.get(r3)).intValue());     // Catch: Exception -> L66
        r15.append("{e} ");     // Catch: Exception -> L66
        r15.append(r03.getName());     // Catch: Exception -> L66
        r15.append(" (");     // Catch: Exception -> L66
        if (Build.VERSION.SDK_INT < 29) goto L63;
        if (r03.isHardwareAccelerated() == false) goto L58;
        r15.append("gpu");     // Catch: Exception -> L66
    L58:
        if (r03.isSoftwareOnly() == false) goto L61;
        r15.append("cpu");     // Catch: Exception -> L66
    L61:
        if (r03.isVendor() == false) goto L63;
        r15.append(", v");     // Catch: Exception -> L66
    L63:
        MediaCodecInfo.CodecCapabilities r04 = r03.getCapabilitiesForType(r14);     // Catch: Exception -> L66
        r15.append("; mi=");     // Catch: Exception -> L66
        r15.append(r04.getMaxSupportedInstances());     // Catch: Exception -> L66
        r15.append(")");     // Catch: Exception -> L66
        r3 = r3 + 1;     // Catch: Exception -> L66
        goto L47
    L50:
        if (r1.isEmpty() == true) goto L52;
    L64:
        r15.append("\n");     // Catch: Exception -> L66
        return;
    L26:
        if (r2.isEmpty() == false) goto L28;
        return;
    }

    private void loadSessionsCount() {
        TL_account.getAuthorizations r0 = new TL_account.getAuthorizations();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SettingsActivity$$ExternalSyntheticLambda14(this));
    }

    private WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
        Insets r52 = AndroidUtilities.getDefaultWindowInsets(r5, false);
        this.navigationBarHeight = r52.bottom;
        int r53 = r52.top;
        int r0 = MainTabsUiHelper.getFloatingTabsPadding(this.hasMainTabs);
        this.listView.setPadding(0, r53 + AndroidUtilities.dp(12.0f), 0, (this.navigationBarHeight + this.additionNavigationBarHeight) + r0);
        return WindowInsetsCompat.CONSUMED;
    }

    private void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        Object r22 = r1.object;
        if ((r22 instanceof TLRPC.TL_attachMenuBot) == false) goto L14;
        TLRPC.TL_attachMenuBot r23 = (TLRPC.TL_attachMenuBot) r22;
        if (r23.inactive == false) goto L7;
    L11:
        WebAppDisclaimerAlert.show(getContext(), new SettingsActivity$$ExternalSyntheticLambda12(this, r23), null, null);
        return;
    L7:
        if (r23.side_menu_disclaimer_needed == true) goto L11;
        LaunchActivity.showAttachMenuBot(LaunchActivity.instance, this.currentAccount, r23, null, true);
        return;
    L14:
        if (r1.instanceOf(SettingsSearchCell.Factory.class) == false) goto L26;
        Object r24 = r1.object;
        if ((r24 instanceof ProfileActivity.SearchAdapter.SearchResult) == false) goto L19;
        ((ProfileActivity.SearchAdapter.SearchResult) r24).open(getParentLayout());
    L21:
        Object r12 = r1.object;
        if (r12 == null) goto L80;
        this.search.addRecent(r12);
        return;
    L80:
        return;
    L19:
        if ((r24 instanceof MessagesController.FaqSearchResult) == false) goto L21;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.openArticle, new Object[]{this.search.faqWebPage, ((MessagesController.FaqSearchResult) r24).url});
        goto L21
    L26:
        switch(r1.id) {
            case -1: goto L78;
            case 0: goto L82;
            case 1: goto L76;
            case 2: goto L74;
            case 3: goto L72;
            case 4: goto L82;
            case 5: goto L70;
            case 6: goto L68;
            case 7: goto L66;
            case 8: goto L64;
            case 9: goto L62;
            case 10: goto L60;
            case 11: goto L58;
            case 12: goto L56;
            case 13: goto L54;
            case 14: goto L82;
            case 15: goto L52;
            case 16: goto L50;
            case 17: goto L48;
            case 18: goto L46;
            case 19: goto L44;
            case 20: goto L42;
            case 21: goto L40;
            case 22: goto L36;
            case 23: goto L31;
            case 24: goto L28;
            default: goto L82;
        };
    L28:
        presentFragment(new RoundVideoSettingsActivity());
        return;
    L36:
        FileLog.cleanupLogs();
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L81;
        r0.adapter.update(true);
        return;
    L81:
        return;
    L40:
        ProfileActivity.sendLogs(getParentActivity(), true);
        return;
    L42:
        ProfileActivity.sendLogs(getParentActivity(), false);
        return;
    L44:
        Browser.openUrl(getParentActivity(), LocaleController.getString(R.string.PrivacyPolicyUrl));
        return;
    L46:
        Browser.openUrl(getParentActivity(), LocaleController.getString(R.string.TelegramFaqUrl));
        return;
    L48:
        showDialog(AlertsCreator.createSupportAlert(this, this.resourceProvider));
        return;
    L50:
        UserSelectorBottomSheet.open(0, BirthdayController.getInstance(UserConfig.selectedAccount).getState());
        return;
    L52:
        presentSettingFragment(new PremiumPreviewFragment(1, "settings"));
        return;
    L54:
        presentSettingFragment(new TONIntroActivity());
        return;
    L56:
        presentSettingFragment(new StarsIntroActivity());
        return;
    L58:
        presentSettingFragment(new PremiumPreviewFragment("settings"));
        return;
    L60:
        presentSettingFragment(new LanguageSelectActivity());
        return;
    L62:
        presentSettingFragment(new LiteModeSettingsActivity());
        return;
    L64:
        presentSettingFragment(new SessionsActivity(0));
        return;
    L66:
        presentSettingFragment(new FiltersSetupActivity());
        return;
    L68:
        presentSettingFragment(new DataSettingsActivity());
        return;
    L70:
        presentSettingFragment(new NotificationsSettingsActivity());
        return;
    L72:
        presentSettingFragment(new PrivacySettingsActivity());
        return;
    L74:
        presentSettingFragment(new ThemeActivity(0));
        return;
    L76:
        presentSettingFragment(new UserInfoActivity());
        return;
    L78:
        presentSettingFragment(new MainPreferencesActivity());
        return;
    L82:
        return;
    L31:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L34;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L34:
        Browser.openUrl(getContext(), LocaleController.getString(R.string.TelegramFeaturesUrl));
    }

    private boolean onLongClick(UItem r3, View r4, int r5, float r6, float r7) {
        Object r52 = r3.object;
        if ((r52 instanceof TLRPC.TL_attachMenuBot) == false) goto L7;
        BotWebViewSheet.deleteBot(this.currentAccount, ((TLRPC.TL_attachMenuBot) r52).bot_id, new SettingsActivity$$ExternalSyntheticLambda9(this));
        return true;
    L7:
        if (r3.id != (-1)) goto L11;
        presentFragment(new DebugActivity());
        return true;
    L11:
        if (r3.instanceOf(SettingsSearchCell.Factory.class) == false) goto L23;
        Object r32 = r3.object;
        if ((r32 instanceof ProfileActivity.SearchAdapter.SearchResult) == false) goto L16;
        String r33 = ((ProfileActivity.SearchAdapter.SearchResult) r32).link;
    L20:
        if (TextUtils.isEmpty(r33) == true) goto L25;
        ItemOptions.makeOptions(this, r4).add(R.drawable.msg_link2, LocaleController.getString(R.string.CopyLink), new SettingsActivity$$ExternalSyntheticLambda10(this, r33)).setScrimViewBackground(this.listView.getClipBackground(r4)).show();
        return true;
    L25:
        return false;
    L16:
        if ((r32 instanceof MessagesController.FaqSearchResult) == false) goto L18;
        r33 = ((MessagesController.FaqSearchResult) r32).url;
        goto L20
    L18:
        r33 = null;
        goto L20
    L23:
        return false;
    }

    private void presentSettingFragment(BaseFragment r4) {
        if (AndroidUtilities.isTablet() == false) goto L16;
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L16;
        if (r0.getRightActionBarLayout() == null) goto L16;
        INavigationLayout r3 = LaunchActivity.instance.getRightActionBarLayout();
        if (r3.getFragmentStack().isEmpty() == false) goto L11;
    L14:
        r3.presentFragment(new INavigationLayout.NavigationParams(r4).setNoAnimation(true).forceRightLayout());
        return;
    L11:
        if ((r3.getFragmentStack().size() - 1) <= 0) goto L13;
        r3.removeFragmentFromStack(r3.getFragmentStack().get(0));
        goto L11
    L13:
        r3.closeLastFragment(false);
    L16:
        presentFragment(r4);
    }

    private void showAvatarProgress(boolean r7, boolean r8) {
        if (this.avatarProgressView != null) goto L5;
        return;
    L5:
        AnimatorSet r0 = this.avatarAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.avatarAnimation = null;
    L9:
        if (r8 == false) goto L16;
        AnimatorSet r82 = new AnimatorSet();
        this.avatarAnimation = r82;
        RadialProgressView r3 = this.avatarProgressView;
        if (r7 == false) goto L13;
        r3.setVisibility(0);
        this.avatarAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.avatarProgressView, View.ALPHA, new float[]{1.0f})});
    L14:
        this.avatarAnimation.setDuration(180);
        this.avatarAnimation.addListener(new AnonymousClass9(this, r7));
        this.avatarAnimation.start();
        return;
    L13:
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, View.ALPHA, new float[]{0.0f})});
        goto L14
    L16:
        RadialProgressView r83 = this.avatarProgressView;
        if (r7 == false) goto L20;
        r83.setAlpha(1.0f);
        this.avatarProgressView.setVisibility(0);
        return;
    L20:
        r83.setAlpha(0.0f);
        this.avatarProgressView.setVisibility(4);
    }

    private void updateActionBarVisible() {
        updateActionBarVisible(false, true);
    }

    private void updateActionBarVisible(boolean r6, boolean r7) {
        if (this.searchItem.isSearchFieldVisible2() == false) goto L6;
    L4:
        boolean r0 = true;
    L14:
        if (this.actionBarVisible != r0) goto L17;
        if (r6 == true) goto L17;
        return;
    L17:
        this.actionBarVisible = r0;
        ValueAnimator r62 = this.actionBarVisibleAnimator;
        if (r62 == null) goto L20;
        r62.cancel();
        this.actionBarVisibleAnimator = null;
    L20:
        ActionBar r63 = this.actionBar;
        float r3 = 0.0f;
        if (r7 == true) goto L31;
        FrameLayout r64 = r63.getTitlesContainer();
        if (r0 == false) goto L25;
        float r72 = 1.0f;
    L26:
        r64.setAlpha(r72);
        View r5 = this.actionBarBackground;
        if (r0 == false) goto L29;
        r3 = 1.0f;
    L29:
        r5.setAlpha(r3);
        return;
    L25:
        r72 = 0.0f;
        goto L26
    L31:
        float r65 = r63.getTitlesContainer().getAlpha();
        if (r0 == false) goto L34;
        r3 = 1.0f;
    L34:
        ValueAnimator r66 = ValueAnimator.ofFloat(new float[]{r65, r3});
        this.actionBarVisibleAnimator = r66;
        r66.addUpdateListener(new SettingsActivity$$ExternalSyntheticLambda11(this));
        this.actionBarVisibleAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBarVisibleAnimator.setDuration(420);
        this.actionBarVisibleAnimator.start();
        return;
    L6:
        if (this.listView.getChildCount() <= 0) goto L12;
        View r02 = this.listView.getChildAt(0);
        if (this.listView.getChildAdapterPosition(r02) > 0) goto L4;
        if ((r02.getY() + r02.getHeight()) < this.actionBar.getHeight()) goto L4;
    L12:
        r0 = false;
        goto L14
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public boolean canParentTabsSlide(MotionEvent r1, boolean r2) {
        return isSwipeBackEnabled(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void clearViews() {
        if (this.ignoreClearViews == false) goto L5;
        return;
    L5:
        super.clearViews();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(this.hasMainTabs);
        this.contentView = new AnonymousClass1(this, r15);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setUseContainerForTitles();
        this.actionBar.setTitle(LocaleController.getString(R.string.Settings));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        this.actionBar.setAddToContainer(false);
        this.actionBar.setOccupyStatusBar(true);
        this.actionBar.setBackgroundColor(0);
        this.actionBar.setBackground(null);
        ActionBarMenu r0 = this.actionBar.createMenu();
        ActionBarMenuItem r3 = r0.addItem(0, R.drawable.outline_header_search, this.resourceProvider).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass3(this));
        this.searchItem = r3;
        r3.setSearchFieldHint(LocaleController.getString(R.string.Search));
        ActionBarMenuItem r02 = r0.addItem(1, R.drawable.ic_ab_other);
        this.otherItem = r02;
        r02.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.otherItem.addSubItem(2, R.drawable.msg_leave, LocaleController.getString(R.string.LogOut));
        AnonymousClass4 r03 = new AnonymousClass4(this, this, r15);
        this.search = r03;
        r03.loadFaqWebPage();
        loadSessionsCount();
        UniversalRecyclerView r04 = new UniversalRecyclerView(this, new SettingsActivity$$ExternalSyntheticLambda1(this), new SettingsActivity$$ExternalSyntheticLambda2(this), new SettingsActivity$$ExternalSyntheticLambda3(this));
        this.listView = r04;
        r04.adapter.setApplyBackground(false);
        this.listView.setSections();
        this.listView.setPadding(0, AndroidUtilities.statusBarHeight + AndroidUtilities.dp(12.0f), 0, (AndroidUtilities.navigationBarHeight + this.additionNavigationBarHeight) + MainTabsUiHelper.getFloatingTabsPadding(this.hasMainTabs));
        this.listView.setClipToPadding(false);
        this.listView.addOnScrollListener(new AnonymousClass5(this));
        UniversalRecyclerView r32 = this.listView;
        SizeNotifierFrameLayout r4 = this.contentView;
        Objects.requireNonNull(r32);
        this.iBlur3Capture = new ViewGroupPartRenderer(r32, r4, new CallLogActivity$$ExternalSyntheticLambda6(r32));
        this.listView.addEdgeEffectListener(new SettingsActivity$$ExternalSyntheticLambda4(this));
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass6 r05 = new AnonymousClass6(this, r15);
        this.actionBarBackground = r05;
        this.contentView.addView(r05, LayoutHelper.createFrame(-1, 200, 48));
        this.contentView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 55));
        ImageUpdater r06 = new ImageUpdater(true, 0, true);
        this.imageUpdater = r06;
        r06.setOpenWithFrontfaceCamera(true);
        ImageUpdater r07 = this.imageUpdater;
        r07.parentFragment = this;
        r07.setDelegate(this);
        this.topView = new FrameLayout(r15);
        FrameLayout r08 = new FrameLayout(r15);
        this.avatarContainer = r08;
        this.topView.addView(r08, LayoutHelper.createFrame(120, 120.0f, 49, 0.0f, 11.0f, 0.0f, 0.0f));
        this.avatarContainer.setOnClickListener(new SettingsActivity$$ExternalSyntheticLambda5(this));
        ScaleStateListAnimator.apply(this.avatarContainer);
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r09 = new BackupImageView(r15);
        this.avatarView = r09;
        r09.setRoundRadius(ExteraConfig.getAvatarCorners(90.0f));
        this.avatarContainer.addView(this.avatarView, LayoutHelper.createFrame(90, 90.0f, 49, 0.0f, 15.0f, 0.0f, 0.0f));
        AnonymousClass7 r010 = new AnonymousClass7(this, r15);
        this.avatarProgressView = r010;
        r010.setSize(AndroidUtilities.dp(26.0f));
        this.avatarProgressView.setProgressColor(-1);
        this.avatarProgressView.setNoProgress(false);
        this.avatarContainer.addView(this.avatarProgressView, LayoutHelper.createFrame(90, 90.0f, 49, 0.0f, 15.0f, 0.0f, 0.0f));
        showAvatarProgress(false, false);
        FrameLayout r011 = new FrameLayout(r15);
        this.cameraButton = r011;
        r011.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        this.cameraButton.setClipToPadding(false);
        this.cameraBackground = new FrameLayout(r15);
        ImageView r012 = new ImageView(r15);
        this.cameraImageView = r012;
        r012.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.cameraImageView.setImageResource(R.drawable.filled_premium_camera);
        this.cameraBackground.addView(this.cameraImageView, LayoutHelper.createFrame(22, 22, 17));
        this.cameraButton.addView(this.cameraBackground, LayoutHelper.createFrame(30, 30.0f));
        this.avatarContainer.addView(this.cameraButton, LayoutHelper.createFrame(34, 34.0f, 49, 33.0f, 76.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(this.cameraButton);
        TextView r013 = new TextView(r15);
        this.titleView = r013;
        NotificationCenter.listenEmojiLoading(r013);
        this.titleView.setTextSize(1, 22.0f);
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setGravity(17);
        this.titleView.setSingleLine();
        TextView r014 = this.titleView;
        TextUtils.TruncateAt r33 = TextUtils.TruncateAt.END;
        r014.setEllipsize(r33);
        this.topView.addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 49, 16.0f, 126.33299f, 16.0f, 0.0f));
        TextView r015 = new TextView(r15);
        this.subtitleView = r015;
        r015.setTextSize(1, 13.0f);
        this.subtitleView.setTypeface(AndroidUtilities.regular());
        this.subtitleView.setGravity(17);
        this.subtitleView.setSingleLine();
        this.subtitleView.setEllipsize(r33);
        this.subtitleView.setTextIsSelectable(true);
        this.topView.addView(this.subtitleView, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 156.0f, 0.0f, 0.0f));
        TextView r016 = new TextView(r15);
        this.versionView = r016;
        r016.setTextSize(1, 14.0f);
        this.versionView.setTypeface(AndroidUtilities.regular());
        this.versionView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText4));
        this.versionView.setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f));
        this.versionView.setGravity(17);
        this.versionView.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector), 2));
        this.versionView.setOnClickListener(new SettingsActivity$$ExternalSyntheticLambda6(this));
        this.navigationBar = new View(r15);
        updateActionBarVisible(true, false);
        this.listView.adapter.update(false);
        setInfo();
        updateColors();
        checkUi_menuItems();
        ViewCompat.setOnApplyWindowInsetsListener(this.contentView, new SettingsActivity$$ExternalSyntheticLambda7(this));
        SizeNotifierFrameLayout r152 = this.contentView;
        this.fragmentView = r152;
        return r152;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.starBalanceUpdated) goto L9;
        setInfo();
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L18;
        r0.adapter.update(true);
        return;
    L18:
        return;
    L9:
        if (r1 != NotificationCenter.updateInterfaces) goto L13;
        setInfo();
        return;
    L13:
        if (r1 != NotificationCenter.newSuggestionsAvailable) goto L19;
        UniversalRecyclerView r02 = this.listView;
        if (r02 == null) goto L20;
        r02.adapter.update(true);
        return;
    L20:
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(0.0f);
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r5, TLRPC.InputFile r6, double r7, String r9, TLRPC.PhotoSize r10, TLRPC.PhotoSize r11, boolean r12, TLRPC.VideoSize r13) {
        AndroidUtilities.runOnUIThread(new SettingsActivity$$ExternalSyntheticLambda0(this, r5, r6, r13, r7, r9, r11, r10));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public BlurredBackgroundSourceRenderNode getGlassSource() {
        return this.iBlur3SourceGlass;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return !this.animatorSearchPageVisible.getValue();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.actionBar.isSearchFieldVisible() == false) goto L9;
        if (r2 == false) goto L10;
        this.actionBar.closeSearchField();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L5;
        checkUi_menuItems();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().addObserver(this, NotificationCenter.starBalanceUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.newSuggestionsAvailable);
        Bundle r0 = this.arguments;
        if (r0 == null) goto L5;
        this.hasMainTabs = r0.getBoolean("hasMainTabs", false);
    L5:
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(this.hasMainTabs);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().removeObserver(this, NotificationCenter.starBalanceUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.newSuggestionsAvailable);
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void onParentScrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.mainTabsHiddenByScroll = false;
        updateMainTabsVisibility();
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void onUploadProgressChanged(float r1) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(r1);
    }

    public void openDebugMenu() {
        AlertDialog.Builder r1 = new AlertDialog.Builder(getParentActivity(), this.resourceProvider);
        r1.setTitle(LocaleController.getString(R.string.DebugMenu));
        String r2 = LocaleController.getString(R.string.DebugMenuImportContacts);
        String r3 = LocaleController.getString(R.string.DebugMenuReloadContacts);
        String r4 = LocaleController.getString(R.string.DebugMenuResetContacts);
        String r5 = LocaleController.getString(R.string.DebugMenuResetDialogs);
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        String r6 = "DebugMenuDisableLogs";
        int r7 = R.string.DebugMenuDisableLogs;
    L5:
        String r62 = LocaleController.getString(r6, r7);
        if (SharedConfig.inAppCamera == false) goto L11;
        String r72 = "DebugMenuDisableCamera";
        int r8 = R.string.DebugMenuDisableCamera;
    L10:
        String r73 = LocaleController.getString(r72, r8);
        String r82 = LocaleController.getString("DebugMenuClearMediaCache", R.string.DebugMenuClearMediaCache);
        String r9 = LocaleController.getString(R.string.DebugMenuCallSettings);
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L15;
        String r10 = LocaleController.getString("DebugMenuCheckAppUpdate", R.string.DebugMenuCheckAppUpdate);
    L16:
        String r12 = LocaleController.getString("DebugMenuReadAllDialogs", R.string.DebugMenuReadAllDialogs);
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L19;
        String r13 = null;
    L24:
        if (BuildVars.DEBUG_VERSION == false) goto L26;
        String r14 = "Clean app update";
    L27:
        boolean r15 = BuildVars.DEBUG_PRIVATE_VERSION;
        if (r15 == false) goto L30;
        String r16 = "Reset suggestions";
    L31:
        if (r15 == false) goto L33;
        String r152 = LocaleController.getString(R.string.DebugMenuClearWebViewCache);
    L34:
        String r17 = LocaleController.getString(R.string.DebugMenuClearWebViewCookies);
        if (SharedConfig.debugWebView == false) goto L37;
        int r18 = R.string.DebugMenuDisableWebViewDebug;
    L38:
        String r182 = LocaleController.getString(r18);
        if (AndroidUtilities.isTabletInternal() == true) goto L41;
    L46:
        String r19 = null;
    L48:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L50;
        String r20 = null;
    L55:
        boolean r21 = BuildVars.DEBUG_PRIVATE_VERSION;
        if (r21 == false) goto L58;
        String r22 = "Force remove premium suggestions";
    L59:
        if (r21 == false) goto L61;
        String r23 = "Share device info";
    L62:
        if (r21 == false) goto L64;
        String r24 = "Force performance class";
    L65:
        if (r21 == true) goto L67;
    L72:
        String r212 = null;
    L74:
        if (DualCameraView.dualAvailableStatic(getContext()) == false) goto L76;
        String r25 = "DebugMenuDualOff";
    L77:
        String r252 = LocaleController.getString(r25);
        if (BuildVars.DEBUG_VERSION == true) goto L80;
        String r26 = null;
    L85:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L87;
        String r27 = null;
    L92:
        if (SharedConfig.payByInvoice == true) goto L94;
        String r28 = "Enable Invoice Payment";
    L96:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L99;
        String r29 = "Update Attach Bots";
    L101:
        if (SharedConfig.isUsingCamera2(this.currentAccount) == true) goto L103;
        String r11 = "Use Camera 2 API";
    L105:
        if (BuildVars.DEBUG_VERSION == false) goto L107;
        String r31 = "Clear Mini Apps Permissions and Files";
    L109:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L111;
        String r32 = "Clear all login tokens";
    L113:
        if (SharedConfig.canBlurChat() == false) goto L120;
        String r33 = r3;
        if (Build.VERSION.SDK_INT >= 31) goto L117;
    L121:
        String r34 = null;
    L123:
        if (SharedConfig.adaptableColorInBrowser == false) goto L125;
        String r35 = "Disabled adaptive browser colors";
    L127:
        if (SharedConfig.debugVideoQualities == false) goto L130;
        String r36 = "Disable video qualities debug";
    L131:
        String r38 = r34;
        if (Build.VERSION.SDK_INT >= 28) goto L134;
        String r210 = null;
    L140:
        if (SharedConfig.forceForumTabs == true) goto L142;
        String r39 = "Force Forum Tabs";
    L144:
        if (BuildVars.DEBUG_VERSION == true) goto L146;
        String r40 = null;
    L151:
        if (SharedConfig.frameMetricsEnabled == false) goto L153;
        String r41 = "hide frame metrics";
    L155:
        if (BuildVars.DEBUG_VERSION == true) goto L157;
        String r42 = null;
    L162:
        if (BuildVars.DEBUG_VERSION == true) goto L164;
        String r43 = null;
    L169:
        if (SharedSettings.experimentalSettingsAllowed.get() == false) goto L172;
        String r44 = "hide experimental settings";
    L173:
        r1.setItems(new CharSequence[]{r2, r33, r4, r5, r62, r73, r82, r9, null, r10, r12, r13, r14, r16, r152, r17, r182, r19, r20, r22, r23, r24, r212, r252, r26, r27, r28, r29, r11, r31, r32, r38, r35, r36, r210, "Reload app config", r39, "Make Memory Dump", r40, r41, r42, r43, r44}, new SettingsActivity$$ExternalSyntheticLambda13(this));
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r1.create());
        return;
    L172:
        r44 = "show experimental settings";
        goto L173
    L164:
        if (SharedConfig.debugViewMetrics == false) goto L166;
        r43 = "disable debug view metrics";
        goto L169
    L166:
        r43 = "enable debug view metrics";
        goto L169
    L157:
        if (SharedConfig.shadowsInSections == false) goto L159;
        r42 = "disable shadows in settings";
        goto L162
    L159:
        r42 = "enable shadows in settings";
        goto L162
    L153:
        r41 = "show frame metrics";
        goto L155
    L146:
        if (SharedConfig.fastWallpaperDisabled == false) goto L148;
        r40 = "enable wallpaper shader";
        goto L151
    L148:
        r40 = "disable wallpaper shader";
        goto L151
    L142:
        r39 = "Do Not Force Forum Tabs";
        goto L144
    L134:
        if (SharedConfig.useSystemBoldFont == false) goto L136;
        int r211 = R.string.DebugMenuDontUseSystemBoldFont;
    L137:
        r210 = LocaleController.getString(r211);
        goto L140
    L136:
        r211 = R.string.DebugMenuUseSystemBoldFont;
        goto L137
    L130:
        r36 = "Enable video qualities debug";
        goto L131
    L125:
        r35 = "Enable adaptive browser colors";
        goto L127
    L117:
        if (SharedConfig.useNewBlur == false) goto L119;
        r34 = "back to cpu blur";
        goto L123
    L119:
        r34 = "use new gpu blur";
        goto L123
    L120:
        r33 = r3;
        goto L121
    L111:
        r32 = null;
        goto L113
    L107:
        r31 = null;
        goto L109
    L103:
        r11 = "Use old Camera 1 API";
        goto L105
    L99:
        r29 = null;
        goto L101
    L94:
        r28 = "Disable Invoice Payment";
        goto L96
    L87:
        if (SharedConfig.photoViewerBlur == false) goto L89;
        r27 = "do not blur in photoviewer";
        goto L92
    L89:
        r27 = "blur in photoviewer";
        goto L92
    L80:
        if (SharedConfig.useSurfaceInStories == false) goto L82;
        r26 = "back to TextureView in stories";
        goto L85
    L82:
        r26 = "use SurfaceView in stories";
        goto L85
    L76:
        r25 = "DebugMenuDualOn";
        goto L77
    L67:
        if (InstantCameraView.allowBigSizeCameraDebug() == true) goto L72;
        if (SharedConfig.bigCameraForRound == true) goto L71;
        r212 = "Force big camera for round";
        goto L74
    L71:
        r212 = "Disable big camera for round";
        goto L74
    L64:
        r24 = null;
        goto L65
    L61:
        r23 = null;
        goto L62
    L58:
        r22 = null;
        goto L59
    L50:
        if (SharedConfig.isFloatingDebugActive == false) goto L52;
        int r202 = R.string.FloatingDebugDisable;
    L53:
        r20 = LocaleController.getString(r202);
        goto L55
    L52:
        r202 = R.string.FloatingDebugEnable;
        goto L53
    L41:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L46;
        if (SharedConfig.forceDisableTabletMode == false) goto L45;
        r19 = "Enable tablet mode";
        goto L48
    L45:
        r19 = "Disable tablet mode";
        goto L48
    L37:
        r18 = R.string.DebugMenuEnableWebViewDebug;
        goto L38
    L33:
        r152 = null;
        goto L34
    L30:
        r16 = null;
        goto L31
    L26:
        r14 = null;
        goto L27
    L19:
        if (SharedConfig.disableVoiceAudioEffects == false) goto L21;
        r13 = "Enable voip audio effects";
        goto L24
    L21:
        r13 = "Disable voip audio effects";
        goto L24
    L15:
        r10 = null;
        goto L16
    L11:
        r72 = "DebugMenuEnableCamera";
        r8 = R.string.DebugMenuEnableCamera;
        goto L10
    L6:
        r6 = "DebugMenuEnableLogs";
        r7 = R.string.DebugMenuEnableLogs;
        goto L5
    }

    public void setInfo() {
        setInfo(getUserConfig().getCurrentUser());
    }

    public void setInfo(TLRPC.User r5) {
        if (this.avatarView != null) goto L6;
        return;
    L6:
        if (this.avatarUploadingRequest == (-1)) goto L8;
        return;
    L8:
        this.avatarDrawable.setInfo(r5);
        this.avatarView.setForUserOrChat(r5, this.avatarDrawable);
        this.titleView.setText(Emoji.replaceEmoji(UserObject.getUserName(r5), this.titleView.getPaint().getFontMetricsInt(), false));
        StringBuilder r0 = new StringBuilder();
        if (r5 != null) goto L11;
    L13:
        String r52 = UserObject.getPublicUsername(r5);
        if (r52 != null) goto L16;
    L19:
        this.subtitleView.setText(r0);
        this.versionView.setText(AppUtils.getVersionText());
        return;
    L16:
        if (TextUtils.isEmpty(r0) == true) goto L18;
        r0.append(" • ");
    L18:
        r0.append("@");
        r0.append(r52);
        goto L19
    L11:
        if (ExteraConfig.getHidePhoneNumber() == true) goto L13;
        r0.append(PhoneFormat.getInstance().format("+" + r5.phone));
        goto L13
    }

    public void setMainTabsActivityController(MainTabsActivityController r1) {
        this.mainTabsActivityController = r1;
    }

    public void updateColors() {
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTitleColor(getThemedColor(r1));
        this.actionBar.setItemsColor(getThemedColor(r1), false);
        SizeNotifierFrameLayout r02 = this.contentView;
        int r2 = Theme.key_windowBackgroundGray;
        r02.setBackgroundColor(getThemedColor(r2));
        this.titleView.setTextColor(getThemedColor(r1));
        this.subtitleView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        this.cameraButton.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(32.0f), getThemedColor(r2)));
        this.cameraBackground.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(30.0f), getThemedColor(Theme.key_featuredStickers_addButton)));
        this.cameraImageView.setColorFilter(getThemedColor(Theme.key_chats_actionIcon));
        this.searchItem.updateColor();
        int r03 = getThemedColor(Theme.key_windowBackgroundWhite);
        this.navigationBar.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(r03, 0.0f), r03}));
        this.actionBarBackground.invalidate();
        this.listView.invalidate();
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void updateMainTabsVisibility() {
        MainTabsActivityController r0 = this.mainTabsActivityController;
        if (r0 != null) goto L5;
        return;
    L5:
        if (BottomNavigationBar.visible() == true) goto L7;
    L9:
        boolean r2 = false;
    L10:
        r0.setTabsVisible(r2);
        return;
    L7:
        if (this.mainTabsHiddenByScroll == true) goto L9;
        r2 = true;
        goto L10
    }
}
