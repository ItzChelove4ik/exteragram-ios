package com.exteragram.messenger.plugins.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001HB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001fJ\u0018\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0014J\b\u0010'\u001a\u00020!H\u0002J\u001a\u0010(\u001a\u00020!2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\b\u0010)\u001a\u00020!H\u0002J\b\u0010*\u001a\u00020!H\u0002J\b\u0010+\u001a\u00020!H\u0002J\b\u0010,\u001a\u00020!H\u0002J\u0016\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u00020\u001fJ\u000e\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020\u001fJ*\u00102\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020!2\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020%H\u0002J\u0010\u0010;\u001a\n =*\u0004\u0018\u00010<0<H\u0002J\b\u0010>\u001a\u00020!H\u0002J\b\u0010?\u001a\u00020!H\u0014J\b\u0010@\u001a\u00020!H\u0014J5\u0010A\u001a\u00020!2\u0006\u0010B\u001a\u00020%2\u0006\u0010C\u001a\u00020%2\u0016\u0010D\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010F0E\"\u0004\u0018\u00010FH\u0016¢\u0006\u0002\u0010GR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginCell;", "Landroid/widget/FrameLayout;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "imageView", "Lorg/telegram/ui/Components/BackupImageView;", "pluginNameView", "Landroid/widget/TextView;", "subtitleView", "Lorg/telegram/ui/Components/EffectsTextView;", "descriptionView", "requirementsLayout", "Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView;", "checkBox", "Lorg/telegram/ui/Components/Switch;", "settingsButton", "Landroid/widget/ImageView;", "shareButton", "openInButton", "deleteButton", "pinButton", "headerLayout", "Landroid/widget/LinearLayout;", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "pluginCellDelegate", "Lcom/exteragram/messenger/plugins/ui/components/PluginCellDelegate;", "compactMode", _UrlKt.FRAGMENT_ENCODE_SET, "setCompact", _UrlKt.FRAGMENT_ENCODE_SET, "compact", "onMeasure", "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "updateLayout", "set", "bindNotRespondingState", "updateDeleteButton", "bindErrorState", "bindNormalState", "setChecked", "checked", "animated", "setPinned", "pinned", "createButton", "iconResId", "isRed", "onClickListener", "Landroid/view/View$OnClickListener;", "applyClickAnimation", PluginsConstants.Settings.VIEW, "Landroid/view/View;", "getPluginIconRadiusDp", "createPluginIconOutlineProvider", "Landroid/view/ViewOutlineProvider;", "kotlin.jvm.PlatformType", "updatePluginIconOutlineProvider", "onAttachedToWindow", "onDetachedFromWindow", "didReceivedNotification", "id", "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "Factory", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public final class PluginCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final Switch checkBox;
    private boolean compactMode;
    private final ImageView deleteButton;
    private final EffectsTextView descriptionView;
    private final LinearLayout headerLayout;
    private final BackupImageView imageView;
    private final ImageView openInButton;
    private final ImageView pinButton;
    private Plugin plugin;
    private PluginCellDelegate pluginCellDelegate;
    private final TextView pluginNameView;
    private final PluginRequirementsView requirementsLayout;
    private final ImageView settingsButton;
    private final ImageView shareButton;
    private final EffectsTextView subtitleView;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J2\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u0019H\u0016J\u0019\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0096\u0002J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0016H\u0016¨\u0006 "}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginCell$Factory;", "Lorg/telegram/ui/Components/UItem$UItemFactory;", "Lcom/exteragram/messenger/plugins/ui/components/PluginCell;", "<init>", "()V", "createView", "context", "Landroid/content/Context;", "listView", "Lorg/telegram/ui/Components/RecyclerListView;", "currentAccount", _UrlKt.FRAGMENT_ENCODE_SET, "classGuid", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "bindView", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.VIEW, "Landroid/view/View;", PluginsConstants.Settings.ITEM, "Lorg/telegram/ui/Components/UItem;", PluginsConstants.Settings.TYPE_DIVIDER, _UrlKt.FRAGMENT_ENCODE_SET, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "equals", "a", "b", "contentsEquals", "isClickable", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory extends UItem.UItemFactory<PluginCell> {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginCell$Factory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "asPlugin", "Lorg/telegram/ui/Components/UItem;", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "pluginCellDelegate", "Lcom/exteragram/messenger/plugins/ui/components/PluginCellDelegate;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final UItem asPlugin(Plugin r3, PluginCellDelegate r4) {
                UItem r2 = UItem.ofFactory(Factory.class);
                r2.id = r3.getId().hashCode();
                r2.object = r3;
                r2.object2 = r4;
                boolean r42 = r3.isNotResponding();
                int r1 = 0;
                if (r3.isEnabled() == false) goto L5;
                int r0 = 2;
            L6:
                int r43 = (r42 ? 1 : 0) | r0;
                if (r3.hasError() == false) goto L9;
                r1 = 4;
            L9:
                r2.intValue = r43 | r1;
                return r2;
            L5:
                r0 = 0;
                goto L6
            }
        }

        static {
            Companion = new Companion(null);
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @JvmStatic
        public static final UItem asPlugin(Plugin r1, PluginCellDelegate r2) {
            return Companion.asPlugin(r1, r2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            if ((r1 instanceof PluginCell) == false) goto L13;
            PluginCell r12 = (PluginCell) r1;
            Object r0 = r2.object;
            PluginCellDelegate r42 = null;
            if ((r0 instanceof Plugin) == false) goto L7;
            Plugin r02 = (Plugin) r0;
        L8:
            Object r22 = r2.object2;
            if ((r22 instanceof PluginCellDelegate) == false) goto L11;
            r42 = (PluginCellDelegate) r22;
        L11:
            r12.set(r02, r42);
            return;
        L7:
            r02 = null;
            goto L8
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r3, UItem r4) {
            if (r3.id == r4.id) goto L6;
            return false;
        L6:
            if (r3.intValue == r4.intValue) goto L8;
            return false;
        L8:
            Object r2 = r3.object;
            Plugin r0 = null;
            if ((r2 instanceof Plugin) == false) goto L11;
            Plugin r22 = (Plugin) r2;
        L12:
            if (r22 != null) goto L14;
            return false;
        L14:
            Object r32 = r4.object;
            if ((r32 instanceof Plugin) == false) goto L17;
            r0 = (Plugin) r32;
        L17:
            if (r0 != null) goto L20;
            return false;
        L20:
            return Intrinsics.areEqual(r22, r0);
        L11:
            r22 = null;
            goto L12
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public PluginCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new PluginCell(r1);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static void $r8$lambda$NpWdx7yMfEnXZdYKD0YE3NuqgTE(Plugin r0, View r1) {
        if (AndroidUtilities.addToClipboard(AppUtils.stackTraceToString(r0.getError())) == false) goto L6;
        BulletinFactory.of(LaunchActivity.getSafeLastFragment()).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    public static void $r8$lambda$TkTsTkgHS2XoNteC2HxEG9EpXDk(PluginCell r0, View r1) {
        PluginCellDelegate r02 = r0.pluginCellDelegate;
        if (r02 == null) goto L6;
        r02.openInExternalApp();
        return;
    }

    /* renamed from: $r8$lambda$UYEjnOswzi6c-kAlHthfuslzgoc, reason: not valid java name */
    public static void m1605$r8$lambda$UYEjnOswzi6ckAlHthfuslzgoc(PluginCellDelegate r0, PluginCell r1, View r2) {
        r0.togglePlugin(r1);
    }

    public static void $r8$lambda$gPhyRoTYC0gInhhjd5k0po863gM(PluginCell r0, View r1) {
        PluginCellDelegate r02 = r0.pluginCellDelegate;
        if (r02 == null) goto L6;
        r02.sharePlugin();
        return;
    }

    /* renamed from: $r8$lambda$miXUx0RJdhvQyk3T-fMBgG7YnBI, reason: not valid java name */
    public static void m1606$r8$lambda$miXUx0RJdhvQyk3TfMBgG7YnBI(PluginCell r0, View r1) {
        PluginCellDelegate r12 = r0.pluginCellDelegate;
        if (r12 == null) goto L6;
        r12.pinPlugin(r0);
        return;
    }

    /* renamed from: $r8$lambda$txZU1-9WGsUHbCEQUldGESo9Wwc, reason: not valid java name */
    public static void m1607$r8$lambda$txZU19WGsUHbCEQUldGESo9Wwc(PluginCell r0, View r1) {
        PluginCellDelegate r02 = r0.pluginCellDelegate;
        if (r02 == null) goto L6;
        r02.deletePlugin();
        return;
    }

    public static void $r8$lambda$vFaf3kpNKKpdG19431rjWCjd68Y(PluginCell r0, View r1) {
        PluginCellDelegate r02 = r0.pluginCellDelegate;
        if (r02 == null) goto L6;
        r02.openPluginSettings();
        return;
    }

    /* renamed from: $r8$lambda$ws7I-mX_AXdMw58mZnvhtanEhYQ, reason: not valid java name */
    public static boolean m1608$r8$lambda$ws7ImX_AXdMw58mZnvhtanEhYQ(View r8, MotionEvent r9) {
        int r0 = r9.getAction();
        if (r0 != 0) goto L5;
        r8.setPressed(true);
        r8.animate().scaleX(0.85f).scaleY(0.85f).setDuration(80).start();
        return true;
    L5:
        if (r0 != 1) goto L7;
        r8.setPressed(false);
        if (r9.getX() >= 0.0f) goto L14;
    L20:
        r8.animate().scaleX(1.0f).scaleY(1.0f).setDuration(350).setInterpolator(new OvershootInterpolator(1.5f)).start();
        return true;
    L14:
        if (r9.getX() > r8.getWidth()) goto L20;
        if (r9.getY() < 0.0f) goto L20;
        if (r9.getY() > r8.getHeight()) goto L20;
        r8.performClick();
        goto L20
    L7:
        if (r0 == 3) goto L9;
        return false;
    L9:
        r8.setPressed(false);
        r8.animate().scaleX(1.0f).scaleY(1.0f).setDuration(350).setInterpolator(new OvershootInterpolator(1.5f)).start();
        return true;
    }

    public static void $r8$lambda$y2vqhelF7XUBHwvSinOwdrg10bo(PluginCell r3, int r4) {
        EffectsTextView r0 = r3.subtitleView;
        if (r3.pluginNameView.getLineCount() <= 1) goto L5;
        r4 = 0;
    L5:
        r0.setPadding(0, 0, r4, 0);
    }

    public PluginCell(Context r19) {
        super(r19);
        setClickable(false);
        setClipChildren(false);
        setClipToPadding(false);
        LinearLayout r3 = new LinearLayout(r19);
        r3.setOrientation(1);
        addView(r3, LayoutHelper.createFrame(-1, -2.0f, 119, 16.0f, 16.0f, 16.0f, 8.0f));
        LinearLayout r5 = new LinearLayout(r19);
        r5.setOrientation(1);
        this.headerLayout = r5;
        r3.addView(r5, LayoutHelper.createLinear(-1, -2));
        BackupImageView r8 = new BackupImageView(r19);
        r8.setVisibility(8);
        r8.setOutlineProvider(createPluginIconOutlineProvider());
        r8.setClipToOutline(true);
        r8.getImageReceiver().setAutoRepeat(1);
        this.imageView = r8;
        r5.addView(r8, LayoutHelper.createLinear(56, 56, 51, 0.0f, 0.0f, 0.0f, 12.0f));
        LinearLayout r82 = new LinearLayout(r19);
        r82.setOrientation(1);
        r5.addView(r82, LayoutHelper.createLinear(-1, -2));
        TextView r52 = new TextView(r19);
        r52.setGravity(3);
        r52.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r52.setTextSize(1, 18.0f);
        TextUtils.TruncateAt r11 = TextUtils.TruncateAt.END;
        r52.setEllipsize(r11);
        r52.setTypeface(AndroidUtilities.bold());
        this.pluginNameView = r52;
        r82.addView(r52, LayoutHelper.createLinear(-1, -2));
        EffectsTextView r53 = new EffectsTextView(r19);
        r53.setGravity(3);
        r53.setTypeface(AndroidUtilities.regular());
        r53.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        int r7 = Theme.key_windowBackgroundWhiteLinkText;
        r53.setLinkTextColor(Theme.getColor(r7));
        r53.setTextSize(1, 14.0f);
        r53.setEllipsize(r11);
        r53.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        this.subtitleView = r53;
        r82.addView(r53, LayoutHelper.createLinear(-1, -2, 0.0f, 2.0f, 0.0f, 0.0f));
        EffectsTextView r54 = new EffectsTextView(r19);
        r54.setGravity(3);
        r54.setTypeface(AndroidUtilities.regular());
        r54.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r54.setLinkTextColor(Theme.getColor(r7));
        this.descriptionView = r54;
        r3.addView(r54, LayoutHelper.createLinear(-1, -2, 0, 0, 12, 0, 0));
        PluginRequirementsView r55 = new PluginRequirementsView(r19, null, 2, null);
        r55.setVisibility(8);
        this.requirementsLayout = r55;
        r3.addView(r55, LayoutHelper.createLinear(-1, -2, 0, 0, 12, 0, 0));
        View r56 = new View(r19);
        r56.setBackgroundColor(Theme.getDividerColor(null));
        r3.addView(r56, LayoutHelper.createLinear(-1, 1.0f / AndroidUtilities.density, 0, 0, 12, 0, 8));
        LinearLayout r57 = new LinearLayout(r19);
        r57.setOrientation(0);
        ImageView r72 = createButton(r19, R.drawable.msg_share, false, new PluginCell$$ExternalSyntheticLambda0(this));
        this.shareButton = r72;
        r57.addView(r72, LayoutHelper.createFrame(40, 40.0f, 51, 0.0f, 0.0f, 8.0f, 0.0f));
        ImageView r73 = createButton(r19, R.drawable.msg_openin, false, new PluginCell$$ExternalSyntheticLambda1(this));
        this.openInButton = r73;
        r57.addView(r73, LayoutHelper.createFrame(40, 40.0f, 51, 0.0f, 0.0f, 8.0f, 0.0f));
        ImageView r74 = createButton(r19, R.drawable.msg_pin, false, new PluginCell$$ExternalSyntheticLambda2(this));
        this.pinButton = r74;
        r57.addView(r74, LayoutHelper.createFrame(40, 40.0f, 51, 0.0f, 0.0f, 8.0f, 0.0f));
        ImageView r75 = createButton(r19, R.drawable.msg_settings, false, new PluginCell$$ExternalSyntheticLambda3(this));
        r75.setVisibility(8);
        this.settingsButton = r75;
        r57.addView(r75, LayoutHelper.createFrame(40, 40.0f, 51, 0.0f, 0.0f, 8.0f, 0.0f));
        ImageView r4 = createButton(r19, R.drawable.msg_delete, true, new PluginCell$$ExternalSyntheticLambda4(this));
        this.deleteButton = r4;
        addView(r4, LayoutHelper.createFrame(40, 40.0f, 85, 0.0f, 0.0f, 16.0f, 8.0f));
        r3.addView(r57, LayoutHelper.createFrame(-1, 40, 83));
        Switch r32 = new Switch(r19);
        int r1 = Theme.key_switchTrack;
        int r42 = Theme.key_switchTrackChecked;
        int r58 = Theme.key_windowBackgroundWhite;
        r32.setColors(r1, r42, r58, r58);
        r32.setFocusable(false);
        this.checkBox = r32;
        addView(r32, LayoutHelper.createFrame(37, 40.0f, 53, 0.0f, 16.0f, 24.0f, 0.0f));
        setCompact(ExteraConfig.getPluginsCompactView());
    }

    private final void applyClickAnimation(View r1) {
        r1.setOnTouchListener(new PluginCell$$ExternalSyntheticLambda5());
    }

    private final void bindErrorState() {
        Plugin r0 = this.plugin;
        if (r0 != null) goto L5;
        return;
    L5:
        EffectsTextView r1 = this.descriptionView;
        Throwable r2 = r0.getError();
        if (r2 == null) goto L8;
        String r22 = r2.getLocalizedMessage();
    L9:
        r1.setText(r22);
        this.descriptionView.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.descriptionView.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        this.descriptionView.setTextSize(1, 12.0f);
        this.descriptionView.setOnClickListener(new PluginCell$$ExternalSyntheticLambda8(r0));
        this.checkBox.setVisibility(8);
        updateDeleteButton();
        return;
    L8:
        r22 = null;
        goto L9
    }

    private final void bindNormalState() {
        Plugin r0 = this.plugin;
        if (r0 != null) goto L5;
        return;
    L5:
        this.descriptionView.setText(LocaleUtils.fullyFormatText(r0.getDescription()));
        this.descriptionView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.descriptionView.setTypeface(AndroidUtilities.regular());
        this.descriptionView.setTextSize(1, 15.0f);
        this.descriptionView.setOnClickListener(null);
        this.checkBox.setVisibility(0);
        updateDeleteButton();
    }

    private final void bindNotRespondingState() {
        this.descriptionView.setText(LocaleController.getString(R.string.PluginIsNotResponding));
        this.descriptionView.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.descriptionView.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        this.descriptionView.setTextSize(1, 12.0f);
        this.descriptionView.setOnClickListener(null);
        this.checkBox.setVisibility(8);
        updateDeleteButton();
    }

    private final ImageView createButton(Context r2, int r3, boolean r4, View.OnClickListener r5) {
        ImageView r0 = new ImageView(r2);
        applyClickAnimation(r0);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        r0.setImageResource(r3);
        if (r4 == false) goto L5;
        int r22 = Theme.key_text_RedRegular;
    L6:
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r22), PorterDuff.Mode.MULTIPLY));
        if (r4 == false) goto L9;
        int r1 = Theme.multAlpha(Theme.getColor(Theme.key_text_RedRegular), 0.12f);
    L10:
        r0.setBackground(Theme.createSelectorDrawable(r1, 1, AndroidUtilities.dp(20.0f)));
        r0.setOnClickListener(r5);
        return r0;
    L9:
        r1 = Theme.getColor(Theme.key_dialogButtonSelector);
        goto L10
    L5:
        r22 = Theme.key_windowBackgroundWhiteGrayIcon;
        goto L6
    }

    private final ViewOutlineProvider createPluginIconOutlineProvider() {
        return ViewOutlineProviderImpl.boundsWithRoundRect(AndroidUtilities.dp(getPluginIconRadiusDp()));
    }

    private final int getPluginIconRadiusDp() {
        return Math.max(ExteraConfig.getSectionRadiusDp() - 16, 8);
    }

    private final void updateDeleteButton() {
        Plugin r0 = this.plugin;
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r02 = r0.isNotResponding();
        ImageView r1 = this.deleteButton;
        if (r02 == true) goto L8;
        int r3 = Theme.key_text_RedRegular;
    L9:
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        ImageView r12 = this.deleteButton;
        if (r02 == true) goto L12;
        int r2 = Theme.multAlpha(Theme.getColor(Theme.key_text_RedRegular), 0.12f);
    L13:
        r12.setBackground(Theme.createSelectorDrawable(r2, 1, AndroidUtilities.dp(20.0f)));
        ImageView r5 = this.deleteButton;
        if (r02 == true) goto L16;
        int r03 = R.drawable.msg_delete;
    L17:
        r5.setImageResource(r03);
        return;
    L16:
        r03 = R.drawable.ic_ab_other;
        goto L17
    L12:
        r2 = Theme.getColor(Theme.key_dialogButtonSelector);
        goto L13
    L8:
        r3 = Theme.key_windowBackgroundWhiteGrayIcon;
        goto L9
    }

    private final void updateLayout() {
        Plugin r0 = this.plugin;
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r2 = true;
        LinearLayout r1 = (LinearLayout) this.headerLayout.getChildAt(1);
        this.headerLayout.setOrientation(!this.compactMode ? 1 : 0);
        LinearLayout.LayoutParams r3 = (LinearLayout.LayoutParams) this.imageView.getLayoutParams();
        if (this.compactMode == false) goto L8;
        int r4 = AndroidUtilities.dp(16.0f);
    L9:
        r3.rightMargin = r4;
        if (this.compactMode == false) goto L12;
        int r42 = 0;
    L13:
        r3.bottomMargin = r42;
        if (this.compactMode == false) goto L16;
        int r43 = AndroidUtilities.dp(49.0f);
    L17:
        r3.width = r43;
        if (this.compactMode == false) goto L20;
        int r44 = AndroidUtilities.dp(49.0f);
    L21:
        r3.height = r44;
        LinearLayout.LayoutParams r12 = (LinearLayout.LayoutParams) r1.getLayoutParams();
        boolean r32 = this.compactMode;
        if (r32 == false) goto L24;
        int r45 = 16;
    L25:
        r12.gravity = r45;
        this.pluginNameView.setSingleLine(r32);
        this.subtitleView.setSingleLine(this.compactMode);
        if (r0.getPack() != null) goto L28;
    L30:
        r2 = false;
    L32:
        if (this.compactMode == false) goto L36;
        if (r0.hasError() == true) goto L36;
    L40:
        int r02 = AndroidUtilities.dp(61.0f);
    L42:
        this.pluginNameView.setPadding(0, 0, r02, 0);
        this.pluginNameView.post(new PluginCell$$ExternalSyntheticLambda7(this, r02));
        requestLayout();
        return;
    L36:
        if (this.compactMode == true) goto L41;
        if (r2 == true) goto L41;
        if (r0.hasError() == false) goto L40;
    L41:
        r02 = 0;
        goto L42
    L28:
        if (r0.getIndex() < 0) goto L30;
    L24:
        r45 = 3;
        goto L25
    L20:
        r44 = AndroidUtilities.dp(56.0f);
        goto L21
    L16:
        r43 = AndroidUtilities.dp(56.0f);
        goto L17
    L12:
        r42 = AndroidUtilities.dp(12.0f);
        goto L13
    L8:
        r4 = 0;
        goto L9
    }

    private final void updatePluginIconOutlineProvider() {
        this.imageView.setOutlineProvider(createPluginIconOutlineProvider());
        this.imageView.invalidateOutline();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        Plugin r32 = this.plugin;
        if (r32 != null) goto L6;
        return;
    L6:
        if (r2 != NotificationCenter.pluginSettingsRegistered) goto L8;
    L9:
        Object r42 = ArraysKt.firstOrNull(r4);
        if ((r42 instanceof String) == false) goto L12;
        String r43 = (String) r42;
    L13:
        if (r43 != null) goto L16;
        return;
    L16:
        if (Intrinsics.areEqual(r43, r32.getId()) == false) goto L26;
        ImageView r1 = this.settingsButton;
        if (r2 == NotificationCenter.pluginSettingsRegistered) goto L20;
    L22:
        boolean r22 = false;
    L23:
        AndroidUtilities.updateViewVisibilityAnimated(r1, r22, 0.5f, true, true);
        return;
    L20:
        if (r32.isEnabled() == false) goto L22;
        r22 = true;
        goto L23
    L26:
        return;
    L12:
        r43 = null;
        goto L13
    L8:
        if (r2 == NotificationCenter.pluginSettingsUnregistered) goto L9;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginSettingsRegistered);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginSettingsUnregistered);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginSettingsRegistered);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginSettingsUnregistered);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public final void set(Plugin r9, PluginCellDelegate r10) {
        if (r9 == null) goto L41;
        if (r10 == null) goto L42;
        this.pluginCellDelegate = r10;
        this.plugin = r9;
        updatePluginIconOutlineProvider();
        PluginsController.Companion r0 = PluginsController.Companion;
        setPinned(r0.isPluginPinned(r9.getId()));
        ImageView r1 = this.openInButton;
        int r3 = 8;
        if (r10.canOpenInExternalApp() == false) goto L8;
        int r2 = 0;
    L9:
        r1.setVisibility(r2);
        if (r9.getPack() != null) goto L12;
    L14:
        boolean r12 = false;
    L15:
        BackupImageView r5 = this.imageView;
        if (r12 == false) goto L18;
        r3 = 0;
    L18:
        r5.setVisibility(r3);
        if (r12 == false) goto L21;
        MediaDataController.getInstance(UserConfig.selectedAccount).setPlaceholderImageByIndex(this.imageView, r9.getPack(), r9.getIndex(), "100_100");
    L22:
        this.pluginNameView.setText(r9.getName());
        EffectsTextView r13 = this.subtitleView;
        if (this.compactMode == false) goto L25;
        String r52 = RegisterSpec.PREFIX;
    L26:
        r13.setText(new SpannableStringBuilder(r52).append(r9.getVersion()).append(" • ").append(LocaleUtils.formatWithUsernames(r9.getAuthor())));
        if (r9.isNotResponding() == false) goto L30;
        bindNotRespondingState();
    L33:
        this.requirementsLayout.setRequirements(r9.getRequirements());
        updateLayout();
        this.checkBox.setChecked(r9.isEnabled(), false);
        this.checkBox.setOnClickListener(new PluginCell$$ExternalSyntheticLambda6(r10, this));
        ImageView r8 = this.settingsButton;
        if (r9.isEnabled() == true) goto L36;
    L38:
        boolean r92 = false;
    L39:
        AndroidUtilities.updateViewVisibilityAnimated(r8, r92, 0.5f, true, false);
        return;
    L36:
        if (r0.getInstance().hasPluginSettings(r9.getId()) == false) goto L38;
        r92 = true;
        goto L39
    L30:
        if (r9.hasError() == false) goto L32;
        bindErrorState();
        goto L33
    L32:
        bindNormalState();
        goto L33
    L25:
        r52 = LocaleController.getString(R.string.PluginVersion) + " ";
        goto L26
    L21:
        this.imageView.setImageDrawable(null);
        goto L22
    L12:
        if (r9.getIndex() < 0) goto L14;
        r12 = true;
        goto L15
    L8:
        r2 = 8;
        goto L9
    L42:
        return;
    }

    public final void setChecked(boolean r1, boolean r2) {
        this.checkBox.setChecked(r1, r2);
    }

    public final void setCompact(boolean r2) {
        if (this.compactMode != r2) goto L5;
        return;
    L5:
        this.compactMode = r2;
        updateLayout();
    }

    public final void setPinned(boolean r1) {
        ImageView r0 = this.pinButton;
        if (r1 == false) goto L5;
        int r12 = R.drawable.msg_unpin;
    L6:
        r0.setImageResource(r12);
        return;
    L5:
        r12 = R.drawable.msg_pin;
        goto L6
    }
}
