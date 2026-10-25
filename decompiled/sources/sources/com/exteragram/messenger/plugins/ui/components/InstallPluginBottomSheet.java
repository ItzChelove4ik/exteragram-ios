package com.exteragram.messenger.plugins.ui.components;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.components.CheckBoxRow;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.pip.PipController;
import com.exteragram.messenger.plugins.ui.components.PluginRequirementsView;
import com.exteragram.messenger.plugins.ui.components.RequiredPluginCell;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.translator.core.TranslationError;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.exteragram.messenger.utils.ui.UIUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

@Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 z2\u00020\u0001:\u0002yzB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u00107\u001a\u00020\u001bH\u0014J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0014J(\u0010<\u001a\u00020=2\u0016\u0010>\u001a\u0012\u0012\u0004\u0012\u00020@0?j\b\u0012\u0004\u0012\u00020@`A2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020+H\u0002J\u0018\u0010F\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020+H\u0002J\u0010\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u001bH\u0002J\u0010\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u00020=2\u0006\u0010N\u001a\u00020\u001dH\u0016J \u0010O\u001a\u00020=2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020D2\u0006\u0010S\u001a\u00020LH\u0014J\u0010\u0010T\u001a\u00020=2\u0006\u0010U\u001a\u000202H\u0002J\b\u0010V\u001a\u00020=H\u0002J\u0010\u0010W\u001a\u00020=2\u0006\u0010X\u001a\u00020\rH\u0002J\u0010\u0010Y\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010\\\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010]\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010^\u001a\u00020=2\u0006\u0010_\u001a\u00020+H\u0002J\b\u0010`\u001a\u00020=H\u0002J\u0010\u0010a\u001a\u00020=2\u0006\u0010b\u001a\u00020\u001fH\u0002J\u0010\u0010c\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\b\u0010d\u001a\u00020=H\u0002J\b\u0010e\u001a\u00020=H\u0002J\b\u0010f\u001a\u00020=H\u0002J6\u0010g\u001a\u00020=2\u0006\u0010I\u001a\u00020+2\u0006\u0010h\u001a\u00020+2\u0006\u0010i\u001a\u00020+2\u0014\u0010j\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010+\u0012\u0004\u0012\u00020=0kH\u0002J\u001e\u0010l\u001a\u00020=2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020-012\u0006\u0010n\u001a\u00020\rH\u0002J\u0010\u0010o\u001a\u00020=2\u0006\u0010p\u001a\u00020\rH\u0002J\b\u0010q\u001a\u00020=H\u0002J\b\u0010r\u001a\u00020=H\u0002J\u0018\u0010s\u001a\u00020=2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010t\u001a\u00020=2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010u\u001a\u00020vH\u0002J\b\u0010w\u001a\u00020=H\u0016J\b\u0010x\u001a\u00020=H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020206X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006{"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;", "Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "validationResult", "Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;", PluginsConstants.PARAMS, "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;", "<init>", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "enableAfterInstallation", _UrlKt.FRAGMENT_ENCODE_SET, "installing", "cancellationRequested", "delayedLoadingRunnable", "Ljava/lang/Runnable;", "currentHint", "Lorg/telegram/ui/Stories/recorder/HintView2;", "isUpdate", "button", "Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;", "contentReady", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "titleText", _UrlKt.FRAGMENT_ENCODE_SET, "sheetContainer", "Landroid/widget/FrameLayout;", "headerView", "Landroid/view/View;", "descriptionView", "actionsPill", "Landroid/widget/LinearLayout;", "translateButton", "Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;", "bottomContainer", "enableAfterInstallRow", "errorView", "errorTextView", "Landroid/widget/TextView;", "errorText", _UrlKt.FRAGMENT_ENCODE_SET, "nameText", "Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;", "descriptionText", "translating", Common.ASSET_REQUIREMENTS, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;", "unknownSourceBlocked", "blocked", "requirementDelegate", "Lorg/telegram/messenger/Utilities$Callback;", "getTitle", "createAdapter", "Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;", "listView", "Lorg/telegram/ui/Components/RecyclerListView;", "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "pipRow", "id", _UrlKt.FRAGMENT_ENCODE_SET, "requirement", "wheelRow", "fileName", PluginsConstants.Settings.TYPE_HEADER, "text", "spacer", "heightDp", _UrlKt.FRAGMENT_ENCODE_SET, "onViewCreated", "containerView", "onPreDraw", "canvas", "Landroid/graphics/Canvas;", "top", "progressToFullView", "onRequirementAction", "info", "checkRequirementsSatisfied", "applyButtonState", "animated", "buildHeader", "activity", "Landroid/app/Activity;", "buildDescription", "buildError", "showError", "message", "scrollToError", "showSourceHint", PluginsConstants.Settings.VIEW, "createEnableAfterInstallRow", "openPluginFile", "checkTranslatable", "onTranslateClick", "translateText", "fromLanguage", "toLanguage", "done", "Lkotlin/Function1;", "onTranslationFinished", "texts", "success", "showTexts", "translated", "onTextTransitionUpdate", "onInstallClick", "showSuccessBulletin", "showSimpleSuccessBulletin", "bf", "Lorg/telegram/ui/Components/BulletinFactory;", "dismiss", "onSwipeStarts", "PluginInstallParams", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInstallPluginBottomSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1369:1\n1#2:1370\n1807#3,3:1371\n1915#3,2:1377\n3938#4:1374\n4474#4,2:1375\n*S KotlinDebug\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet\n*L\n411#1:1371,3\n1002#1:1377,2\n941#1:1374\n941#1:1375,2\n*E\n"})
/* loaded from: classes.dex */
public final class InstallPluginBottomSheet extends BottomSheetWithRecyclerListView {
    public static final Companion Companion = null;
    private static final float SECTION_GAP = 12.0f;
    private LinearLayout actionsPill;
    private UniversalAdapter adapter;
    private boolean blocked;
    private LinearLayout bottomContainer;
    private final ButtonWithCounterView button;
    private volatile boolean cancellationRequested;
    private boolean contentReady;
    private HintView2 currentHint;
    private Runnable delayedLoadingRunnable;
    private final TranslatableText descriptionText;
    private View descriptionView;
    private View enableAfterInstallRow;
    private boolean enableAfterInstallation;
    private String errorText;
    private TextView errorTextView;
    private View errorView;
    private final BaseFragment fragment;
    private View headerView;
    private boolean installing;
    private final boolean isUpdate;
    private final TranslatableText nameText;
    private final PluginInstallParams params;
    private final Plugin plugin;
    private final Utilities.Callback<RequiredPluginInfo> requirementDelegate;
    private final List<RequiredPluginInfo> requirements;
    private FrameLayout sheetContainer;
    private CharSequence titleText;
    private PluginTranslateButton translateButton;
    private boolean translating;
    private final boolean unknownSourceBlocked;

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$1", "Lorg/telegram/ui/ActionBar/BottomSheet$BottomSheetDelegate;", "canDismiss", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends BottomSheet.BottomSheetDelegate {
        final /* synthetic */ InstallPluginBottomSheet this$0;

        public AnonymousClass1(InstallPluginBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegate, org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public boolean canDismiss() {
            return !InstallPluginBottomSheet.access$getInstalling$p(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$7", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet$7, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass7 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ InstallPluginBottomSheet this$0;

        public AnonymousClass7(InstallPluginBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L5;
            this.this$0.dismiss();
            return;
        L5:
            if (r2 == 1) goto L7;
            return;
        L7:
            InstallPluginBottomSheet.access$openPluginFile(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SECTION_GAP", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 &2\u00020\u0001:\u0001&B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u001c\u0010 \u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\t\"\u0004\b\"\u0010\u000bR\u001c\u0010#\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\t\"\u0004\b%\u0010\u000b¨\u0006'"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;", _UrlKt.FRAGMENT_ENCODE_SET, "filePath", _UrlKt.FRAGMENT_ENCODE_SET, "trusted", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Z)V", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "getTrusted", "()Z", "setTrusted", "(Z)V", "iconDrawable", "Landroid/graphics/drawable/Drawable;", "getIconDrawable", "()Landroid/graphics/drawable/Drawable;", "setIconDrawable", "(Landroid/graphics/drawable/Drawable;)V", "requiredPlugins", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;", "getRequiredPlugins", "()Ljava/util/List;", "setRequiredPlugins", "(Ljava/util/List;)V", "bundledPackages", "getBundledPackages", "setBundledPackages", "blockedTitle", "getBlockedTitle", "setBlockedTitle", "blockedSubtitle", "getBlockedSubtitle", "setBlockedSubtitle", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PluginInstallParams {
        public static final Companion Companion = null;
        private String blockedSubtitle;
        private String blockedTitle;
        private List<String> bundledPackages;
        private String filePath;
        private Drawable iconDrawable;
        private List<RequiredPluginInfo> requiredPlugins;
        private boolean trusted;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "of", "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;", "messageObject", "Lorg/telegram/messenger/MessageObject;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final PluginInstallParams of(MessageObject r6) {
                String r5 = ChatUtils.getInstance().getPathToMessage(r6);
                boolean r1 = true;
                boolean r2 = false;
                if (r6.isForwarded() == false) goto L14;
                Long r62 = r6.getForwardedFromId();
                if (r62 == null) goto L23;
                BadgesController r0 = BadgesController.INSTANCE;
                if (r0.isTrusted(-r62.longValue()) == false) goto L9;
            L12:
                r2 = r1;
                goto L23
            L9:
                if (r0.isExtera(-r62.longValue()) == true) goto L12;
            L11:
                r1 = false;
            L23:
                return new PluginInstallParams(r5, r2);
            L14:
                if (r6.isFromChannel() == false) goto L23;
                if (r6.isFromChat() == true) goto L23;
                long r3 = -r6.getDialogId();
                BadgesController r63 = BadgesController.INSTANCE;
                if (r63.isTrusted(r3) == true) goto L12;
                if (r63.isExtera(r3) == false) goto L11;
                goto L11
            }
        }

        static {
            Companion = new Companion(null);
        }

        public PluginInstallParams(String r1, boolean r2) {
            this.filePath = r1;
            this.trusted = r2;
        }

        @JvmStatic
        public static final PluginInstallParams of(MessageObject r1) {
            return Companion.of(r1);
        }

        public final String getBlockedSubtitle() {
            return this.blockedSubtitle;
        }

        public final String getBlockedTitle() {
            return this.blockedTitle;
        }

        public final List<String> getBundledPackages() {
            return this.bundledPackages;
        }

        public final String getFilePath() {
            return this.filePath;
        }

        public final Drawable getIconDrawable() {
            return this.iconDrawable;
        }

        public final List<RequiredPluginInfo> getRequiredPlugins() {
            return this.requiredPlugins;
        }

        public final boolean getTrusted() {
            return this.trusted;
        }

        public final void setBlockedSubtitle(String r1) {
            this.blockedSubtitle = r1;
        }

        public final void setBlockedTitle(String r1) {
            this.blockedTitle = r1;
        }

        public final void setBundledPackages(List<String> r1) {
            this.bundledPackages = r1;
        }

        public final void setFilePath(String r1) {
            this.filePath = r1;
        }

        public final void setIconDrawable(Drawable r1) {
            this.iconDrawable = r1;
        }

        public final void setRequiredPlugins(List<RequiredPluginInfo> r1) {
            this.requiredPlugins = r1;
        }

        public final void setTrusted(boolean r1) {
            this.trusted = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1", "Lorg/telegram/ui/Components/EffectsTextView;", "onMeasure", _UrlKt.FRAGMENT_ENCODE_SET, "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet$buildDescription$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01581 extends EffectsTextView {
        final /* synthetic */ InstallPluginBottomSheet this$0;

        public C01581(Activity r1, InstallPluginBottomSheet r2) {
            this.this$0 = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.EffectsTextView, org.telegram.ui.Components.spoilers.SpoilersTextView, android.widget.TextView, android.view.View
        public void onDraw(Canvas r2) {
            int r0 = InstallPluginBottomSheet.access$getDescriptionText$p(this.this$0).beforeDraw(r2);
            super.onDraw(r2);
            InstallPluginBottomSheet.access$getDescriptionText$p(this.this$0).afterDraw(r2, r0);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, r3);
            setMeasuredDimension(getMeasuredWidth(), InstallPluginBottomSheet.access$getDescriptionText$p(this.this$0).getHeight(getMeasuredHeight()));
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$onInstallClick$4", "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;", "onProgress", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "isCancelled", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nInstallPluginBottomSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$onInstallClick$4\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1369:1\n1#2:1370\n*E\n"})
    /* renamed from: com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet$onInstallClick$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass4 implements PipController.InstallerDelegate {
        final /* synthetic */ InstallPluginBottomSheet this$0;

        public static void $r8$lambda$CRoqHnY2Bzd4KgYV55JhE9rdRbw(InstallPluginBottomSheet r3, String r4) {
            Runnable r0 = InstallPluginBottomSheet.access$getDelayedLoadingRunnable$p(r3);
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L5:
            InstallPluginBottomSheet.access$setDelayedLoadingRunnable$p(r3, null);
            if (InstallPluginBottomSheet.access$getCancellationRequested$p(r3) == false) goto L8;
            return;
        L8:
            InstallPluginBottomSheet.access$getButton$p(r3).setLoading(false);
            InstallPluginBottomSheet.access$getButton$p(r3).setText(LocaleController.getString(R.string.Cancel), true);
            InstallPluginBottomSheet.access$getButton$p(r3).setSubText(r4, true);
        }

        public AnonymousClass4(InstallPluginBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // com.exteragram.messenger.plugins.pip.PipController.InstallerDelegate
        public boolean isCancelled() {
            return InstallPluginBottomSheet.access$getCancellationRequested$p(this.this$0);
        }

        @Override // com.exteragram.messenger.plugins.pip.PipController.InstallerDelegate
        public void onProgress(String r2) {
            AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$onInstallClick$4$$ExternalSyntheticLambda0(this.this$0, r2));
        }
    }

    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1", "Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;", "onSuccess", _UrlKt.FRAGMENT_ENCODE_SET, "translatedText", _UrlKt.FRAGMENT_ENCODE_SET, "onFailed", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nInstallPluginBottomSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1369:1\n1#2:1370\n*E\n"})
    /* renamed from: com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet$translateText$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01591 implements TranslatorUtils.TranslateCallback {
        final /* synthetic */ Function1<String, Unit> $finish;

        /* JADX WARN: Multi-variable type inference failed */
        public C01591(Function1<? super String, Unit> r1) {
            this.$finish = r1;
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onFailed() {
            this.$finish.invoke(null);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public /* bridge */ /* synthetic */ void onFailed(TranslationError r1) {
            super.onFailed(r1);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public /* bridge */ /* synthetic */ void onReqId(int r1) {
            super.onReqId(r1);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(String r3) {
            Function1<String, Unit> r2 = this.$finish;
            if (r3 != null) goto L5;
        L7:
            r3 = null;
        L8:
            r2.invoke(r3);
            return;
        L5:
            if (StringsKt.isBlank(r3) == true) goto L7;
            goto L7
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public /* bridge */ /* synthetic */ void onSuccess(TLObject r1, TLRPC.TL_error r2) {
            super.onSuccess(r1, r2);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public /* bridge */ /* synthetic */ void onSuccess(TLRPC.TL_textWithEntities r1) {
            super.onSuccess(r1);
        }
    }

    /* renamed from: $r8$lambda$--hFGMA5QevyK28Idv1Y9ba9cxI, reason: not valid java name */
    public static void m1596$r8$lambda$hFGMA5QevyK28Idv1Y9ba9cxI(InstallPluginBottomSheet r1) {
        r1.button.setLoading(true);
    }

    public static void $r8$lambda$0bzmhvmogRnBU8vwylqF8Odp7Is(InstallPluginBottomSheet r0, View r1, int r2) {
        UniversalAdapter r12 = r0.adapter;
        if (r12 == null) goto L15;
        UItem r13 = r12.getItem(r2);
        if (r13 == null) goto L16;
        Object r14 = r13.object2;
        if ((r14 instanceof String) == false) goto L10;
        String r15 = (String) r14;
    L11:
        if (r15 == null) goto L17;
        Browser.openUrl(r0.getContext(), r15);
        return;
    L17:
        return;
    L10:
        r15 = null;
        goto L11
    L16:
        return;
    }

    public static void $r8$lambda$0iFSAj_4pVkRlrIMq9sW2Re2HQQ(InstallPluginBottomSheet r4, String r5) {
        Runnable r0 = r4.delayedLoadingRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        r4.delayedLoadingRunnable = null;
        r4.button.setLoading(false);
        r4.setCancelable(true);
        r4.setCanDismissWithSwipe(true);
        r4.setCanDismissWithTouchOutside(true);
        r4.installing = false;
        if (r4.cancellationRequested == false) goto L9;
        r4.cancellationRequested = false;
        r4.applyButtonState(true);
        return;
    L9:
        if (r5 == null) goto L15;
        r4.applyButtonState(true);
        if (StringsKt.contains$default(r5, "Installation cancelled", false, 2, null) == true) goto L22;
        r4.showError(r5);
        return;
    L22:
        return;
    L15:
        if (r4.enableAfterInstallation == true) goto L17;
    L20:
        r4.dismiss();
        r4.showSuccessBulletin(r4.fragment, r4.plugin);
        return;
    L17:
        if (ExteraConfig.getPluginsSafeMode() == true) goto L20;
        r4.installing = true;
        r4.setCanDismissWithSwipe(false);
        r4.setCanDismissWithTouchOutside(false);
        r4.button.setLoading(true);
        PluginsController.Companion.getInstance().setPluginEnabled(r4.plugin.getId(), true, new InstallPluginBottomSheet$$ExternalSyntheticLambda24(r4));
    }

    public static void $r8$lambda$1mwQKeA8NTran0o7I5m7IQPO56g(LinearLayout r1, InstallPluginBottomSheet r2) {
        r1.performClick();
        SharedPreferences.Editor r12 = ExteraConfig.getEditor();
        if (r2.params.getTrusted() == false) goto L5;
        String r22 = "trusted_source_hint";
    L6:
        r12.putBoolean(r22, true).apply();
        return;
    L5:
        r22 = "unknown_source_hint";
        goto L6
    }

    public static Unit $r8$lambda$4owLgGt87wFgmWYGGJXyRImSMkc(Ref.BooleanRef r1, TranslatableText r2, Ref.IntRef r3, InstallPluginBottomSheet r4, List r5, String r6) {
        if (r6 != null) goto L5;
        r1.element = true;
    L6:
        int r22 = r3.element - 1;
        r3.element = r22;
        if (r22 != 0) goto L10;
        r4.onTranslationFinished(r5, !r1.element);
    L10:
        return Unit.INSTANCE;
    L5:
        r2.setTranslation(r6);
        goto L6
    }

    public static void $r8$lambda$51uVpX6e938kFdqF76dtHaIpIp8(View r4, InstallPluginBottomSheet r5, HintView2 r6) {
        int[] r1 = new int[2];
        r4.getLocationInWindow(r1);
        int[] r0 = new int[2];
        r5.container.getLocationInWindow(r0);
        r1[0] = r1[0] - r0[0];
        int r3 = r1[1] - r0[1];
        r1[1] = r3;
        r6.setTranslationY((r3 - AndroidUtilities.dp(100.0f)) - AndroidUtilities.dp(6.0f));
        r6.setJointPx(0.0f, (-AndroidUtilities.dp(32.0f)) + (r1[0] + (r4.getMeasuredWidth() / 2.0f)));
        r6.setDuration(5500);
        r6.show();
    }

    /* renamed from: $r8$lambda$7PvEkq0ZY-75y_UTl5UgLN3mEoc, reason: not valid java name */
    public static void m1597$r8$lambda$7PvEkq0ZY75y_UTl5UgLN3mEoc(RequiredPluginInfo r1, InstallPluginBottomSheet r2, String r3) {
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda11(r3, r1, r2));
    }

    /* renamed from: $r8$lambda$9k86gw9Mzua9-n0HXczcoHJ-H2M, reason: not valid java name */
    public static CharSequence m1598$r8$lambda$9k86gw9Mzua9n0HXczcoHJH2M(InstallPluginBottomSheet r2, String r3) {
        return LocaleUtils.fullyFormatText(r3, r2.fragment, new InstallPluginBottomSheet$$ExternalSyntheticLambda5(r2));
    }

    /* renamed from: $r8$lambda$GdDIZfVsbBJ4Wc-NT-ZutfZirDU, reason: not valid java name */
    public static void m1599$r8$lambda$GdDIZfVsbBJ4WcNTZutfZirDU(InstallPluginBottomSheet r0, RequiredPluginInfo r1) {
        r0.onRequirementAction(r1);
    }

    /* renamed from: $r8$lambda$JDAKNeU10Fil-bAnsaIZ2uI7PKk, reason: not valid java name */
    public static void m1600$r8$lambda$JDAKNeU10FilbAnsaIZ2uI7PKk(InstallPluginBottomSheet r0, View r1) {
        String r12 = r0.errorText;
        if (r12 != null) goto L6;
        return;
    L6:
        if (AndroidUtilities.addToClipboard(r12) == false) goto L9;
        BulletinFactory.of(r0.topBulletinContainer, r0.resourcesProvider).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    public static void $r8$lambda$JcFMrAkO6mT6xcKCfpOjvc83vx8(InstallPluginBottomSheet r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        int r52 = r5 - r3;
        if (r0.recyclerListView.getPaddingBottom() == r52) goto L6;
        RecyclerListView r12 = r0.recyclerListView;
        int r02 = r0.backgroundPaddingLeft;
        r12.setPadding(r02, 0, r02, r52);
        return;
    }

    public static void $r8$lambda$KlFP3p1GoTtkXZ1lBR4je9x2kFM(InstallPluginBottomSheet r0, View r1) {
        r0.showSourceHint(r1);
    }

    public static void $r8$lambda$PcrlDgT4JGXCfZRinXFErAkdLs0(AtomicBoolean r3, TLRPC.TL_messages_stickerSet r4, Plugin r5, Runnable r6, InstallPluginBottomSheet r7, String r8, BulletinFactory r9) {
        if (r3.get() == true) goto L36;
        TLRPC.Document r0 = null;
        if (r4 == null) goto L8;
        ArrayList<TLRPC.Document> r1 = r4.documents;
    L9:
        if (r1 != null) goto L11;
    L17:
        if (r0 != null) goto L19;
        return;
    L19:
        if (r3.getAndSet(true) == true) goto L35;
        AndroidUtilities.cancelRunOnUIThread(r6);
        if (r7.isUpdate == false) goto L23;
        int r32 = R.string.PluginUpdated;
    L24:
        SpannableStringBuilder r33 = AndroidUtilities.replaceTags(LocaleController.formatString(r32, new Object[]{r8}));
        PluginsController.Companion r42 = PluginsController.Companion;
        Plugin r62 = r42.getInstance().getPlugins().get(r5.getId());
        if (r62 != null) goto L27;
    L31:
        Bulletin r34 = r9.createSimpleBulletin(r0, r33);
    L32:
        r34.show();
        return;
    L27:
        if (r42.getInstance().hasPluginSettings(r5.getId()) == false) goto L31;
        if (r62.isEnabled() == false) goto L31;
        r34 = r9.createEmojiBulletin(r0, r33, LocaleController.getString(R.string.Settings), new InstallPluginBottomSheet$$ExternalSyntheticLambda14(r5));
        goto L32
    L23:
        r32 = R.string.PluginInstalled;
        goto L24
    L35:
        return;
    L11:
        if (r4.documents.isEmpty() == true) goto L17;
        int r12 = r5.getIndex();
        if (r12 < 0) goto L17;
        if (r12 >= r4.documents.size()) goto L17;
        r0 = r4.documents.get(r12);
        goto L17
    L8:
        r1 = null;
        goto L9
    }

    public static void $r8$lambda$Pi0Yg3kmZq9UkRULiFYt6kZYc_8(InstallPluginBottomSheet r1, String r2) {
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda29(r1, r2));
    }

    public static Unit $r8$lambda$QmIdZAyHeJKamuDsNUC71z3zbGQ(Ref.BooleanRef r1, Function1 r2, String r3) {
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda0(r1, r2, r3));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$S6yUHpGKTwONSUPNmjtxj1U2NIs(String r2, RequiredPluginInfo r3, InstallPluginBottomSheet r4) {
        if (r2 != null) goto L17;
        Plugin r22 = PluginsController.Companion.getInstance().getPlugins().get(r3.id);
        if (r22 == null) goto L6;
        String r1 = r22.getVersion();
    L7:
        r3.currentVersion = r1;
        if (r22 == null) goto L11;
        String r23 = r22.getName();
        if (r23 == null) goto L11;
    L12:
        r3.name = r23;
        r3.state = 3;
        r4.errorText = null;
        UniversalAdapter r24 = r4.adapter;
        if (r24 == null) goto L15;
        r24.update(true);
    L15:
        r4.checkRequirementsSatisfied();
        return;
    L11:
        r23 = r3.name;
        goto L12
    L6:
        r1 = null;
        goto L7
    L17:
        r4.showError(r2);
    }

    public static void $r8$lambda$SEi4h0JXOWTPvYm8mfQFdJHPNZk(InstallPluginBottomSheet r0, View r1) {
        r0.onTranslateClick();
    }

    /* renamed from: $r8$lambda$U5co24RCnkNfmnx5ohZVPCpsK-0, reason: not valid java name */
    public static void m1601$r8$lambda$U5co24RCnkNfmnx5ohZVPCpsK0(InstallPluginBottomSheet r3, String r4) {
        r3.installing = false;
        r3.setCanDismissWithSwipe(true);
        r3.setCanDismissWithTouchOutside(true);
        r3.button.setLoading(false);
        if (r4 == null) goto L6;
        r3.applyButtonState(true);
        r3.showError(r4);
        return;
    L6:
        r3.dismiss();
        r3.showSuccessBulletin(r3.fragment, r3.plugin);
    }

    public static void $r8$lambda$ZQHzvKNFHqfqpJsWcYEwFWcaMDg(Plugin r1) {
        PluginsController.Companion.openPluginSettings(r1.getId());
    }

    public static void $r8$lambda$_keszDIvYRSPiS1ENLcwZrfDATw(InstallPluginBottomSheet r1, String r2) {
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda12(r1, r2));
    }

    public static void $r8$lambda$aPNjQGCz0SRiyiVD9F8ZjNUC_0c(Plugin r1) {
        PluginsController.Companion.openPluginSettings(r1.getId());
    }

    public static void $r8$lambda$axbWnxDrBj8SEXz68q0_0EeZ7WI(View r5) {
        FrameLayout.LayoutParams r1 = null;
        if ((r5 instanceof HeaderCell) == false) goto L5;
        HeaderCell r52 = (HeaderCell) r5;
    L6:
        if (r52 == null) goto L21;
        TextView r0 = r52.getTextView();
        ViewGroup.LayoutParams r2 = r0.getLayoutParams();
        if ((r2 instanceof FrameLayout.LayoutParams) == false) goto L11;
        r1 = (FrameLayout.LayoutParams) r2;
    L11:
        if (r1 == null) goto L22;
        int r22 = AndroidUtilities.dp(6.0f);
        int r3 = AndroidUtilities.dp(34.0f);
        if (r1.topMargin == r22) goto L16;
    L19:
        r1.topMargin = r22;
        r0.setMinHeight(r3);
        r52.requestLayout();
        return;
    L16:
        if (r0.getMinHeight() != r3) goto L19;
        return;
    L22:
        return;
    L21:
        return;
    L5:
        r52 = null;
        goto L6
    }

    public static void $r8$lambda$bCqSZM6hxYuEJX0VdupAXrW9z48(AtomicBoolean r1, InstallPluginBottomSheet r2, Plugin r3, BulletinFactory r4) {
        if (r1.getAndSet(true) == true) goto L6;
        r2.showSimpleSuccessBulletin(r3, r4);
        return;
    }

    public static void $r8$lambda$cj8Fxmv3WzdlRmOhCU2DoQ437yc(Ref.BooleanRef r1, Function1 r2, String r3) {
        if (r1.element == true) goto L6;
        r1.element = true;
        r2.invoke(r3);
        return;
    }

    /* renamed from: $r8$lambda$dgq_OwmZilX4u_QAT-t01iXTPS0, reason: not valid java name */
    public static void m1602$r8$lambda$dgq_OwmZilX4u_QATt01iXTPS0(TranslatableText r1, InstallPluginBottomSheet r2, String r3) {
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda23(r3, r1, r2));
    }

    public static CharSequence $r8$lambda$q4p5RiDrgsviqy7JLiU9TlMNu8Q(String r0) {
        return r0;
    }

    public static void $r8$lambda$qWRuZ2J8_oT7EeUQTT2otMF0ew0(AtomicBoolean r8, Plugin r9, Runnable r10, InstallPluginBottomSheet r11, String r12, BulletinFactory r13, TLRPC.TL_messages_stickerSet r14) {
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda28(r8, r14, r9, r10, r11, r12, r13));
    }

    /* renamed from: $r8$lambda$sYEAZbpbDxpv-Jl6detG-O0ze-s, reason: not valid java name */
    public static void m1603$r8$lambda$sYEAZbpbDxpvJl6detGO0zes(InstallPluginBottomSheet r0, View r1) {
        r0.openPluginFile();
    }

    public static Unit $r8$lambda$uW8h013qngMyO9QW7bp8hpiNB2E(InstallPluginBottomSheet r0, boolean r1) {
        r0.enableAfterInstallation = r1;
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$wQhmu7AbcThMqfCYx86e6RzypGU(InstallPluginBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static void $r8$lambda$xwESlfoHFGWhHXNA_r6mweAjQ8I(String r1, TranslatableText r2, InstallPluginBottomSheet r3) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.length() != 0) goto L7;
        return;
    L7:
        if (Intrinsics.areEqual(r1, TranslateController.UNKNOWN_LANGUAGE) == false) goto L9;
        return;
    L9:
        if (TranslatorUtils.isRestrictedLanguage(r1) == true) goto L16;
        r2.setLanguage(r1);
        PluginTranslateButton r12 = r3.translateButton;
        if (r12 == null) goto L17;
        r12.setVisibility(0);
        return;
    L17:
        return;
    }

    /* renamed from: $r8$lambda$zFYNOl6mxRm-m1IcxZRTUXyOo-o, reason: not valid java name */
    public static void m1604$r8$lambda$zFYNOl6mxRmm1IcxZRTUXyOoo(InstallPluginBottomSheet r0, View r1) {
        r0.onInstallClick();
    }

    static {
        Companion = new Companion(null);
    }

    public InstallPluginBottomSheet(BaseFragment r18, PluginsController.PluginValidationResult r19, PluginInstallParams r20) {
        super(r18.getParentActivity(), r18, false, false, false, true, BottomSheetWithRecyclerListView.ActionBarType.FADING, r18.getResourceProvider());
        this.fragment = r18;
        this.params = r20;
        Plugin r3 = r19.getPlugin();
        if (r3 == null) goto L39;
        this.plugin = r3;
        this.isUpdate = PluginsController.Companion.getInstance().getPlugins().containsKey(r3.getId());
        this.nameText = new TranslatableText(r3.getName(), new InstallPluginBottomSheet$$ExternalSyntheticLambda6(), new InstallPluginBottomSheet$nameText$2(this));
        this.descriptionText = new TranslatableText(r3.getDescription(), new InstallPluginBottomSheet$$ExternalSyntheticLambda7(this), new InstallPluginBottomSheet$descriptionText$2(this));
        List<RequiredPluginInfo> r4 = r20.getRequiredPlugins();
        if (r4 == null) goto L8;
        List<RequiredPluginInfo> r42 = CollectionsKt.toList(r4);
        if (r42 == null) goto L8;
    L9:
        this.requirements = r42;
        if (r20.getTrusted() == false) goto L12;
    L14:
        boolean r43 = false;
    L15:
        this.unknownSourceBlocked = r43;
        if (r43 == false) goto L18;
    L21:
        boolean r1 = true;
    L22:
        this.blocked = r1;
        this.requirementDelegate = new InstallPluginBottomSheet$$ExternalSyntheticLambda8(this);
        setDelegate(new AnonymousClass1(this));
        Activity r12 = r18.getParentActivity();
        this.occupyNavigationBar = true;
        setApplyBottomPadding(false);
        this.topPadding = 0.2f;
        int r2 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r2));
        ActionBar r44 = this.actionBar;
        r44.setBackgroundColor(getThemedColor(r2));
        r44.setCastShadows(false);
        this.showShadow = false;
        this.titleText = r3.getName();
        updateTitle();
        this.takeTranslationIntoAccount = true;
        RecyclerListView r45 = this.recyclerListView;
        r45.setClipChildren(false);
        r45.setClipToPadding(false);
        r45.setOverScrollMode(2);
        int r7 = this.backgroundPaddingLeft;
        r45.setPadding(r7, 0, r7, 0);
        r45.setSections();
        InstallPluginBottomSheet$3$1 r72 = new InstallPluginBottomSheet$3$1(this);
        r72.setSupportsChangeAnimations(false);
        r72.setDelayAnimations(false);
        r72.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r72.setDurations(350);
        r45.setItemAnimator(r72);
        this.recyclerListView.setOnItemClickListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda9(this));
        this.headerView = buildHeader(r12);
        this.descriptionView = buildDescription(r12);
        this.errorView = buildError(r12);
        checkTranslatable();
        ButtonWithCounterView r46 = new ButtonWithCounterView(r12, true, this.resourcesProvider);
        r46.setRound();
        this.button = r46;
        applyButtonState(false);
        LinearLayout r73 = new LinearLayout(r12);
        r73.setOrientation(1);
        r73.setBackground(UIUtil.createBottomFade(getThemedColor(r2)));
        r73.setPadding(0, 0, 0, AndroidUtilities.navigationBarHeight);
        LinearLayout.LayoutParams r22 = new LinearLayout.LayoutParams(-1, AndroidUtilities.dp(48.0f));
        int r8 = this.backgroundPaddingLeft + AndroidUtilities.dp(SECTION_GAP);
        r22.setMargins(r8, AndroidUtilities.dp(SECTION_GAP), r8, AndroidUtilities.dp(SECTION_GAP));
        Unit r82 = Unit.INSTANCE;
        r73.addView(r46, r22);
        if (r3.isEnabled() == false) goto L25;
    L31:
        r73.addOnLayoutChangeListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda10(this));
        this.bottomContainer = r73;
        FrameLayout r13 = this.sheetContainer;
        if (r13 == null) goto L34;
        r13.addView(r73, LayoutHelper.createFrame(-1, -2, 80));
    L34:
        ActionBarMenu r14 = this.actionBar.createMenu();
        r14.setPadding(0, 0, AndroidUtilities.dp(3.5f), 0);
        r14.addItem(1, R.drawable.msg_openin);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass7(this));
        this.contentReady = true;
        UniversalAdapter r15 = this.adapter;
        if (r15 == null) goto L37;
        r15.update(false);
    L37:
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L25:
        if (ExteraConfig.getPluginsSafeMode() == true) goto L31;
        View r16 = createEnableAfterInstallRow(r12);
        if (this.blocked == false) goto L29;
        int r23 = 8;
    L30:
        r16.setVisibility(r23);
        r73.addView(r16, LayoutHelper.createLinear(-2, -2, 1, 0.0f, 0.0f, 0.0f, 8.0f));
        this.enableAfterInstallRow = r16;
        goto L31
    L29:
        r23 = 0;
        goto L30
    L18:
        if (r20.getBlockedTitle() != null) goto L21;
        r1 = false;
        goto L22
    L12:
        if (ExteraConfig.getPluginsUnknownSources() == true) goto L14;
        r43 = true;
    L8:
        r42 = CollectionsKt.emptyList();
        goto L9
    L39:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    }

    public static final /* synthetic */ ButtonWithCounterView access$getButton$p(InstallPluginBottomSheet r0) {
        return r0.button;
    }

    public static final /* synthetic */ boolean access$getCancellationRequested$p(InstallPluginBottomSheet r0) {
        return r0.cancellationRequested;
    }

    public static final /* synthetic */ ViewGroup access$getContainerView$p$s126290054(InstallPluginBottomSheet r0) {
        return r0.containerView;
    }

    public static final /* synthetic */ Runnable access$getDelayedLoadingRunnable$p(InstallPluginBottomSheet r0) {
        return r0.delayedLoadingRunnable;
    }

    public static final /* synthetic */ TranslatableText access$getDescriptionText$p(InstallPluginBottomSheet r0) {
        return r0.descriptionText;
    }

    public static final /* synthetic */ boolean access$getInstalling$p(InstallPluginBottomSheet r0) {
        return r0.installing;
    }

    public static final /* synthetic */ TranslatableText access$getNameText$p(InstallPluginBottomSheet r0) {
        return r0.nameText;
    }

    public static final /* synthetic */ void access$onTextTransitionUpdate(InstallPluginBottomSheet r0) {
        r0.onTextTransitionUpdate();
    }

    public static final /* synthetic */ void access$openPluginFile(InstallPluginBottomSheet r0) {
        r0.openPluginFile();
    }

    public static final /* synthetic */ void access$setDelayedLoadingRunnable$p(InstallPluginBottomSheet r0, Runnable r1) {
        r0.delayedLoadingRunnable = r1;
    }

    private final void applyButtonState(boolean r7) {
        ButtonWithCounterView r0 = this.button;
        r0.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        if (this.unknownSourceBlocked == false) goto L5;
        String r1 = LocaleController.getString(R.string.PluginInstallBlocked);
    L7:
        if (this.unknownSourceBlocked == false) goto L9;
        String r2 = LocaleController.getString(R.string.PluginInstallBlockedInfo);
    L11:
        if (this.blocked == false) goto L15;
        if (r1 == null) goto L15;
        r0.setColor(ColorUtils.compositeColors(ColorUtils.setAlphaComponent(getThemedColor(Theme.key_featuredStickers_addButton), 180), getThemedColor(Theme.key_windowBackgroundWhite)));
        r0.setText(r1, r7);
        r0.setSubText(r2, r7);
        r0.setOnClickListener(null);
        r0.setClickable(false);
        r0.setFocusable(false);
        return;
    L15:
        r0.setColor(getThemedColor(Theme.key_featuredStickers_addButton));
        if (this.isUpdate == false) goto L18;
        int r12 = R.string.UpdatePlugin;
    L19:
        r0.setText(LocaleController.getString(r12), r7);
        r0.setSubText(null, r7);
        r0.setFocusable(true);
        r0.setOnClickListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda13(this));
        return;
    L18:
        r12 = R.string.InstallPlugin;
        goto L19
    L9:
        r2 = this.params.getBlockedSubtitle();
        goto L11
    L5:
        r1 = this.params.getBlockedTitle();
        goto L7
    }

    private final View buildDescription(Activity r5) {
        C01581 r0 = new C01581(r5, this);
        r0.setGravity(3);
        r0.setTypeface(AndroidUtilities.regular());
        r0.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r0.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        r0.setTextSize(1, 15.0f);
        r0.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r0.setText(LocaleUtils.fullyFormatText(this.plugin.getDescription(), this.fragment, new InstallPluginBottomSheet$$ExternalSyntheticLambda5(this)));
        r0.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(SECTION_GAP));
        this.descriptionText.setView(r0);
        return r0;
    }

    private final View buildError(Activity r9) {
        int r0 = getThemedColor(Theme.key_text_RedRegular);
        int r1 = AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp());
        TextView r2 = new TextView(r9);
        r2.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        r2.setTextSize(1, SECTION_GAP);
        r2.setTextColor(r0);
        r2.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(SECTION_GAP));
        r2.setBackground(Theme.createRoundRectDrawable(r1, r1, ColorUtils.compositeColors(AndroidUtilities.multiplyAlphaComponent(r0, 0.1f), getThemedColor(Theme.key_windowBackgroundWhite))));
        ScaleStateListAnimator.apply(r2, 0.02f, 1.5f);
        r2.setOnClickListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda15(this));
        this.errorTextView = r2;
        FrameLayout r8 = new FrameLayout(r9);
        r8.setPadding(AndroidUtilities.dp(SECTION_GAP), 0, AndroidUtilities.dp(SECTION_GAP), AndroidUtilities.dp(SECTION_GAP));
        r8.addView(r2, LayoutHelper.createFrame(-1, -2.0f));
        return r8;
    }

    private final View buildHeader(Activity r22) {
        LinearLayout r2 = new LinearLayout(r22);
        r2.setOrientation(1);
        r2.setClipChildren(false);
        r2.setClipToPadding(false);
        Drawable r5 = this.params.getIconDrawable();
        if (r5 == null) goto L5;
    L10:
        FrameLayout r9 = new FrameLayout(r22);
        r9.setClipChildren(false);
        r9.setClipToPadding(false);
        BackupImageView r11 = new BackupImageView(r22);
        r11.setOutlineProvider(ViewOutlineProviderImpl.boundsWithRoundRect(AndroidUtilities.dpf2(SECTION_GAP)));
        r11.setClipToOutline(true);
        r11.getImageReceiver().setAutoRepeat(1);
        r9.addView(r11, LayoutHelper.createFrame(78, 78, 17));
        FrameLayout r10 = new FrameLayout(r22);
        r10.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(28.0f), getThemedColor(Theme.key_windowBackgroundGray)));
        FrameLayout r12 = new FrameLayout(r22);
        r12.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(24.0f), getThemedColor(Theme.key_featuredStickers_addButton)));
        r10.addView(r12, LayoutHelper.createFrame(24, 24, 17));
        ImageView r122 = new ImageView(r22);
        r122.setImageResource(R.drawable.plugins_filled);
        r122.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_featuredStickers_buttonText), PorterDuff.Mode.SRC_IN));
        r122.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r10.addView(r122, LayoutHelper.createFrame(16, 16, 17));
        r9.addView(r10, LayoutHelper.createFrame(28, 28.0f, 8388693, 0.0f, 0.0f, -4.0f, -4.0f));
        r2.addView(r9, LayoutHelper.createLinear(78, 78, 1, 0.0f, 28.0f, 0.0f, 0.0f));
        if (r5 == null) goto L13;
        r11.setImageDrawable(r5);
    L14:
        InstallPluginBottomSheet$buildHeader$pluginNameView$1 r52 = new InstallPluginBottomSheet$buildHeader$pluginNameView$1(r22, this);
        r52.setGravity(1);
        r52.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r52.setTextSize(1, 18.0f);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setText(this.plugin.getName());
        this.nameText.setView(r52);
        r2.addView(r52, LayoutHelper.createLinear(-1, -2, 0, 40.0f, 16.0f, 40.0f, 0.0f));
        EffectsTextView r53 = new EffectsTextView(r22, this.fragment.getResourceProvider());
        r53.setGravity(1);
        r53.setTypeface(AndroidUtilities.regular());
        r53.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r53.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        r53.setTextSize(1, 14.0f);
        r53.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        SpannableStringBuilder r92 = new SpannableStringBuilder(LocaleController.getString(R.string.PluginVersion)).append(" ");
        int r102 = r92.length();
        if (this.isUpdate == false) goto L26;
        Plugin r112 = PluginsController.Companion.getInstance().getPlugins().get(this.plugin.getId());
        if (r112 == null) goto L27;
        String r113 = r112.getVersion();
        String r123 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r113 != null) goto L21;
        r113 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        String r13 = this.plugin.getVersion();
        if (r13 == null) goto L25;
        r123 = r13;
    L25:
        r92.append(r113).append(" -> ").append(r123);
        r92 = LocaleUtils.replaceArrows(getContext(), r92, R.drawable.msg_mini_arrow_mediathin);
        r92.setSpan(new StrikethroughSpan(), r102, r113.length() + r102, 33);
    L27:
        r92.append(" • ").append(LocaleUtils.formatWithUsernames(this.plugin.getAuthor(), this.fragment, new InstallPluginBottomSheet$$ExternalSyntheticLambda5(this)));
        r53.setText(r92);
        r2.addView(r53, LayoutHelper.createLinear(-1, -2, 0, 21.0f, 4.0f, 21.0f, 0.0f));
        if (this.params.getTrusted() == false) goto L30;
        int r54 = R.drawable.trusted_mini;
    L32:
        if (this.params.getTrusted() == false) goto L34;
        int r93 = Theme.key_windowBackgroundWhiteGreenText;
    L35:
        int r94 = getThemedColor(r93);
        if (this.params.getTrusted() == false) goto L39;
        int r103 = R.string.PluginSourceTrusted;
    L38:
        String r104 = LocaleController.getString(r103);
        LinearLayout r114 = new LinearLayout(r22);
        ScaleStateListAnimator.apply(r114, 0.05f, 1.5f);
        r114.setOrientation(0);
        r114.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), ColorUtils.compositeColors(AndroidUtilities.multiplyAlphaComponent(r94, 0.1f), getThemedColor(Theme.key_windowBackgroundWhite))));
        r114.setPadding(AndroidUtilities.dp(SECTION_GAP), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(6.0f));
        r114.setGravity(17);
        ImageView r6 = new ImageView(r22);
        r6.setImageResource(r54);
        r6.setColorFilter(new PorterDuffColorFilter(r94, PorterDuff.Mode.SRC_IN));
        r114.addView(r6, LayoutHelper.createLinear(14, 14, 16, 0.0f, 0.0f, 6.0f, 0.0f));
        TextView r55 = new TextView(r22);
        r55.setTypeface(AndroidUtilities.regular());
        r55.setTextColor(r94);
        r55.setTextSize(1, 13.0f);
        r55.setText(r104);
        r114.addView(r55);
        r2.addView(r114, LayoutHelper.createLinear(-2, -2, 17, 0.0f, SECTION_GAP, 0.0f, 0.0f));
        r114.setOnClickListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda17(this));
        int r56 = getThemedColor(Theme.key_windowBackgroundWhiteGrayIcon);
        ImageView r62 = new ImageView(r22);
        ScaleStateListAnimator.apply(r62, 0.15f, 1.5f);
        Drawable r8 = ContextCompat.getDrawable(r22, R.drawable.msg_openin);
        if (r8 == null) goto L43;
        Drawable r82 = r8.mutate();
    L44:
        r62.setImageDrawable(r82);
        r62.setColorFilter(new PorterDuffColorFilter(r56, PorterDuff.Mode.MULTIPLY));
        r62.setScaleType(ImageView.ScaleType.CENTER);
        r62.setOnClickListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda18(this));
        int r83 = Theme.key_dialogButtonSelector;
        r62.setBackground(Theme.createSelectorDrawable(getThemedColor(r83), 1, AndroidUtilities.dp(20.0f)));
        PluginTranslateButton r95 = new PluginTranslateButton(r22, r56);
        ScaleStateListAnimator.apply(r95, 0.15f, 1.5f);
        r95.setVisibility(8);
        r95.setOnClickListener(new InstallPluginBottomSheet$$ExternalSyntheticLambda19(this));
        r95.setBackground(Theme.createSelectorDrawable(getThemedColor(r83), 1, AndroidUtilities.dp(20.0f)));
        this.translateButton = r95;
        LinearLayout r3 = new LinearLayout(r22);
        r3.setOrientation(0);
        int r57 = AndroidUtilities.dp(2.0f);
        r3.setPadding(r57, r57, r57, r57);
        BlurredBackgroundSourceColor r58 = new BlurredBackgroundSourceColor();
        r58.setColor(getThemedColor(Theme.key_windowBackgroundGray));
        BlurredBackgroundDrawable r59 = r58.createDrawable().setColorProvider(BlurredBackgroundProviderImpl.topPanelChatActivity(this.resourcesProvider)).setRadius(AndroidUtilities.dpf2(22.0f));
        r59.setShadowAlpha(0.0f);
        r3.setBackground(r59);
        r3.addView(r95, LayoutHelper.createLinear(40, 40));
        r3.addView(r62, LayoutHelper.createLinear(40, 40));
        this.actionsPill = r3;
        if (this.params.getTrusted() == false) goto L49;
        if (ExteraConfig.getPreferences().getBoolean("trusted_source_hint", false) == true) goto L49;
    L52:
        AndroidUtilities.runOnUIThread(new InstallPluginBottomSheet$$ExternalSyntheticLambda20(r114, this), 600);
    L53:
        FrameLayout r0 = new FrameLayout(r22);
        r0.setClipChildren(false);
        r0.setClipToPadding(false);
        r0.addView(r2, LayoutHelper.createFrame(-1, -2.0f));
        r0.addView(r3, LayoutHelper.createFrame(-2, 44.0f, 53, 0.0f, 14.0f, SECTION_GAP, 0.0f));
        return r0;
    L49:
        if (this.params.getTrusted() == true) goto L53;
        if (ExteraConfig.getPreferences().getBoolean("unknown_source_hint", false) == true) goto L53;
    L43:
        r82 = null;
        goto L44
    L39:
        r103 = R.string.PluginSourceUnknown;
        goto L38
    L34:
        r93 = Theme.key_text_RedRegular;
        goto L35
    L30:
        r54 = R.drawable.unknown_mini;
        goto L32
    L26:
        r92.append(this.plugin.getVersion());
        goto L27
    L13:
        MediaDataController.getInstance(UserConfig.selectedAccount).setPlaceholderImageByIndex(r11, this.plugin.getPack(), this.plugin.getIndex(), "150_150", this.resourcesProvider);
        goto L14
    L5:
        if (this.plugin.getPack() != null) goto L7;
    L9:
        ImageView r510 = new ImageView(r22);
        r510.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r510.setImageResource(R.drawable.plugins_filled);
        r510.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_featuredStickers_buttonText), PorterDuff.Mode.SRC_IN));
        r510.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(78.0f), getThemedColor(Theme.key_featuredStickers_addButton)));
        int r96 = AndroidUtilities.dp(16.0f);
        r510.setPadding(r96, r96, r96, r96);
        r2.addView(r510, LayoutHelper.createLinear(78, 78, 1, 0.0f, 28.0f, 0.0f, 0.0f));
        goto L14
    L7:
        if (this.plugin.getIndex() < 0) goto L9;
        goto L9
    }

    private final void checkRequirementsSatisfied() {
        if (this.unknownSourceBlocked == false) goto L5;
        return;
    L5:
        if (this.blocked == true) goto L7;
        return;
    L7:
        if (this.requirements.isEmpty() == true) goto L28;
        List<RequiredPluginInfo> r0 = this.requirements;
        if ((r0 instanceof Collection) == true) goto L11;
    L13:
        Iterator<T> r02 = r0.iterator();
    L15:
        if (r02.hasNext() == false) goto L19;
        if (((RequiredPluginInfo) r02.next()).isSatisfied() == true) goto L15;
        return;
    L19:
        this.blocked = false;
        applyButtonState(true);
        View r2 = this.enableAfterInstallRow;
        if (r2 == null) goto L29;
        r2.setVisibility(0);
        return;
    L29:
        return;
    L11:
        if (r0.isEmpty() == false) goto L13;
    }

    private final void checkTranslatable() {
        if (LanguageDetector.hasSupport() == false) goto L15;
        TranslatableText[] r0 = {this.nameText, this.descriptionText};
        int r1 = 0;
    L7:
        if (r1 >= 2) goto L20;
        TranslatableText r2 = r0[r1];
        String r3 = r2.getOriginal();
        if (r3 == null) goto L14;
        if (StringsKt.isBlank(r3) == true) goto L14;
        LanguageDetector.detectLanguage(r3, new InstallPluginBottomSheet$$ExternalSyntheticLambda3(r2, this), null);
    L14:
        r1 = r1 + 1;
        goto L7
    L20:
        return;
    }

    private final View createEnableAfterInstallRow(Activity r5) {
        CheckBoxRow r0 = new CheckBoxRow(r5, LocaleController.getString(R.string.EnableAfterInstallation), this.enableAfterInstallation, this.resourcesProvider);
        r0.setOnCheckedChange(new InstallPluginBottomSheet$$ExternalSyntheticLambda21(this));
        return r0;
    }

    private final void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        if (this.contentReady == false) goto L48;
        View r72 = this.headerView;
        if (r72 == null) goto L8;
        r6.add(UItem.asCustomShadow(r72));
        r6.add(spacer(20.0f));
    L8:
        View r73 = this.descriptionView;
        if (r73 == null) goto L12;
        r6.add(UItem.asCustom(r73));
    L12:
        if (this.requirements.isEmpty() == true) goto L17;
        r6.add(spacer(SECTION_GAP));
        r6.add(header(LocaleController.getString(R.string.PluginRequired)));
        Iterator<RequiredPluginInfo> r74 = this.requirements.iterator();
    L15:
        if (r74.hasNext() == false) goto L17;
        r6.add(RequiredPluginCell.Factory.Companion.asRequirement(r74.next(), this.requirementDelegate));
    L17:
        List<String> r75 = this.plugin.getRequirements();
        List<String> r1 = r75;
        int r2 = 0;
        if (r1 != null) goto L20;
    L26:
        List<String> r76 = this.params.getBundledPackages();
        if (r76 == null) goto L38;
        if (r76.isEmpty() == false) goto L32;
        r76 = null;
    L32:
        if (r76 == null) goto L38;
        r6.add(spacer(SECTION_GAP));
        r6.add(header(LocaleController.getString(R.string.PluginBundledPackages)));
        Iterator<T> r77 = r76.iterator();
    L35:
        if (r77.hasNext() == false) goto L38;
        int r12 = r2 + 1;
        r6.add(wheelRow(r2 + 1500, (String) r77.next()));
        r2 = r12;
    L38:
        if (this.errorText == null) goto L46;
        View r78 = this.errorView;
        if (r78 == null) goto L47;
        r6.add(spacer(SECTION_GAP));
        r6.add(UItem.asCustomShadow(r78));
        return;
    L47:
        return;
    L46:
        return;
    L20:
        if (r1.isEmpty() == true) goto L26;
        r6.add(spacer(SECTION_GAP));
        r6.add(header(LocaleController.getString(R.string.PluginRequirements)));
        Iterator<T> r79 = r75.iterator();
        int r13 = 0;
    L24:
        if (r79.hasNext() == false) goto L26;
        int r3 = r13 + 1;
        r6.add(pipRow(r13 + 1000, (String) r79.next()));
        r13 = r3;
        goto L24
    }

    private final UItem header(CharSequence r1) {
        return UItem.asHeader(r1).onBind(new InstallPluginBottomSheet$$ExternalSyntheticLambda27());
    }

    private final void onInstallClick() {
        if (this.installing == true) goto L5;
        ConcurrentHashMap<String, PluginsController.PluginsEngine> r0 = PluginsController.Companion.getEngines();
        String r2 = this.plugin.getEngine();
        if (r2 != null) goto L11;
        r2 = PluginsConstants.PYTHON;
    L11:
        PluginsController.PluginsEngine r02 = r0.get(r2);
        if (r02 == null) goto L19;
        this.installing = true;
        this.cancellationRequested = false;
        setCanDismissWithSwipe(false);
        setCanDismissWithTouchOutside(false);
        Runnable r1 = this.delayedLoadingRunnable;
        if (r1 == null) goto L17;
        AndroidUtilities.cancelRunOnUIThread(r1);
    L17:
        InstallPluginBottomSheet$$ExternalSyntheticLambda1 r12 = new InstallPluginBottomSheet$$ExternalSyntheticLambda1(this);
        this.delayedLoadingRunnable = r12;
        AndroidUtilities.runOnUIThread(r12, 250);
        r02.loadPluginFromFile(this.params.getFilePath(), this.plugin, new InstallPluginBottomSheet$$ExternalSyntheticLambda2(this), new AnonymousClass4(this));
        return;
    L19:
        return;
    L5:
        if (this.cancellationRequested == true) goto L13;
        this.cancellationRequested = true;
        this.button.setLoading(true);
        this.button.setSubText(null, true);
        return;
    }

    private final void onRequirementAction(RequiredPluginInfo r5) {
        if (r5.isSatisfied() == false) goto L6;
        return;
    L6:
        if (r5.state != 1) goto L9;
        PluginsController.Companion.getInstance().setPluginEnabled(r5.id, true, new InstallPluginBottomSheet$$ExternalSyntheticLambda30(r5, this));
        return;
    L9:
        String r52 = r5.downloadUrl;
        if (r52 != null) goto L12;
        return;
    L12:
        dismiss();
        Browser.openUrl(getContext(), r52);
    }

    private final void onTextTransitionUpdate() {
        ViewGroup r0 = this.containerView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private final void onTranslateClick() {
        PluginTranslateButton r0 = this.translateButton;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.translating == true) goto L41;
        int r3 = 0;
        if (r0.getState() != 2) goto L12;
        showTexts(false);
        r0.setState(0);
        return;
    L12:
        TranslatableText[] r1 = {this.nameText, this.descriptionText};
        ArrayList r9 = new ArrayList();
        int r4 = 0;
    L13:
        if (r4 >= 2) goto L21;
        TranslatableText r5 = r1[r4];
        if (r5.getLanguage() == null) goto L19;
        if (r5.getTranslation() != null) goto L19;
        r9.add(r5);
    L19:
        r4 = r4 + 1;
        goto L13
    L21:
        if (r9.isEmpty() == false) goto L24;
        showTexts(true);
        r0.setState(2);
        return;
    L24:
        this.translating = true;
        r0.setState(1);
        String r02 = TranslatorUtils.getResolvedTargetLanguageCode();
        Ref.IntRef r7 = new Ref.IntRef();
        r7.element = r9.size();
        Ref.BooleanRef r52 = new Ref.BooleanRef();
        int r12 = r9.size();
    L25:
        if (r3 >= r12) goto L42;
        Object r2 = r9.get(r3);
        r3 = r3 + 1;
        TranslatableText r6 = (TranslatableText) r2;
        r6.startLoading();
        String r22 = r6.getOriginal();
        if (r22 != null) goto L29;
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L29:
        String r42 = r6.getLanguage();
        if (r42 != null) goto L32;
        r42 = "auto";
    L32:
        translateText(r22, r42, r02, new InstallPluginBottomSheet$$ExternalSyntheticLambda4(r52, r6, r7, this, r9));
        goto L25
    L42:
        return;
    }

    private final void onTranslationFinished(List<TranslatableText> r2, boolean r3) {
        this.translating = false;
        if (r3 == false) goto L7;
        showTexts(true);
        PluginTranslateButton r1 = this.translateButton;
        if (r1 == null) goto L13;
        r1.setState(2);
    L13:
        Iterator<T> r12 = r2.iterator();
    L15:
        if (r12.hasNext() == false) goto L17;
        ((TranslatableText) r12.next()).stopLoading();
        goto L15
    L17:
        return;
    L7:
        PluginTranslateButton r32 = this.translateButton;
        if (r32 == null) goto L11;
        r32.setState(0);
    L11:
        if (isShowing() == false) goto L13;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
        goto L13
    }

    private final void openPluginFile() {
        if (this.installing == true) goto L23;
        File r0 = new File(this.params.getFilePath());
        if (r0.exists() == true) goto L9;
        return;
    L9:
        if (PluginFileViewer.canOpen(r0) == false) goto L20;
        String r1 = r0.getName();
        if (StringsKt.contains$default(r1, '.', false, 2, null) == true) goto L14;
        r1 = null;
    L14:
        if (r1 != null) goto L17;
        r1 = this.plugin.getId() + PluginsConstants.PLUGINS_EXT;
    L17:
        if (PluginFileViewer.INSTANCE.open(this.fragment, r0, r1) == false) goto L22;
        dismiss();
        return;
    L22:
        return;
    L20:
        PluginFileViewer.openExternally(this.fragment, r0, r0.getName());
        return;
    }

    private final UItem pipRow(int r4, String r5) {
        String r3 = StringsKt.trim(r5).toString();
        PluginRequirementsView.Companion r52 = PluginRequirementsView.Companion;
        String r0 = r52.requirementName(r3);
        String r53 = r52.requirementUrl(r3);
        String r1 = null;
        if (r53 == null) goto L5;
        String r32 = StringsKt.trim(StringsKt.removePrefix(r3, r0)).toString();
        if (r32.length() == 0) goto L10;
        r1 = r32;
    L10:
        UItem r33 = UItem.asButton(r4, r0, r1);
        r33.object2 = r53;
        return r33;
    L5:
        return UItem.asSettingsCell(r4, r3, null);
    }

    private final void scrollToError() {
        RecyclerView.Adapter r0 = this.recyclerListView.getAdapter();
        if (r0 == null) goto L6;
        this.recyclerListView.smoothScrollToPosition(r0.getItemCount() - 1);
        return;
    }

    private final void showError(String r4) {
        String r42 = StringsKt.trim(r4).toString();
        if (this.errorText != null) goto L5;
        boolean r0 = true;
    L6:
        this.errorText = r42;
        TextView r2 = this.errorTextView;
        if (r2 == null) goto L9;
        r2.setText(r42);
    L9:
        UniversalAdapter r43 = this.adapter;
        if (r43 == null) goto L12;
        r43.update(true);
    L12:
        if (r0 == false) goto L15;
        scrollToError();
        return;
    L15:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private final void showSimpleSuccessBulletin(Plugin r4, BulletinFactory r5) {
        String r0 = r4.getName();
        if (this.isUpdate == false) goto L5;
        int r3 = R.string.PluginUpdated;
    L6:
        String r32 = LocaleController.formatString(r3, new Object[]{r0});
        PluginsController.Companion r02 = PluginsController.Companion;
        Plugin r1 = r02.getInstance().getPlugins().get(r4.getId());
        if (r1 != null) goto L9;
    L14:
        r5.createSimpleBulletin(R.raw.contact_check, r32).show();
        return;
    L9:
        if (r02.getInstance().hasPluginSettings(r4.getId()) == false) goto L14;
        if (r1.isEnabled() == false) goto L14;
        r5.createSimpleBulletin(R.raw.contact_check, r32, LocaleController.getString(R.string.Settings), new InstallPluginBottomSheet$$ExternalSyntheticLambda25(r4)).show();
        return;
    L5:
        r3 = R.string.PluginInstalled;
        goto L6
    }

    private final void showSourceHint(View r10) {
        HintView2 r0 = this.currentHint;
        if (r0 == null) goto L5;
        r0.hide();
    L5:
        this.currentHint = null;
        if (this.params.getTrusted() == false) goto L8;
        int r02 = R.string.PluginSourceTrustedInfo;
    L9:
        String r03 = LocaleController.getString(r02);
        HintView2 r04 = new HintView2(getContext(), 3).setMultilineText(true).setBgColor(getThemedColor(Theme.key_undo_background)).setTextColor(getThemedColor(Theme.key_undo_infoColor)).setText(AndroidUtilities.replaceTags(r03)).setTextAlign(Layout.Alignment.ALIGN_CENTER).allowBlur(true).setRounding(SECTION_GAP);
        r04.setMaxWidthPx(HintView2.cutInFancyHalf(r04.getText(), r04.getTextPaint()));
        this.container.addView(r04, LayoutHelper.createFrame(-1, 100.0f, 55, 32.0f, 0.0f, 32.0f, 0.0f));
        this.currentHint = r04;
        this.container.post(new InstallPluginBottomSheet$$ExternalSyntheticLambda22(r10, this, r04));
        return;
    L8:
        r02 = R.string.PluginSourceUnknownInfo;
        goto L9
    }

    private final void showSuccessBulletin(BaseFragment r10, Plugin r11) {
        BulletinFactory r6 = BulletinFactory.of(r10);
        String r5 = r11.getName();
        if (r11.getPack() != null) goto L5;
    L8:
        showSimpleSuccessBulletin(r11, r6);
        return;
    L5:
        if (r11.getIndex() < 0) goto L8;
        TLRPC.TL_inputStickerSetShortName r102 = new TLRPC.TL_inputStickerSetShortName();
        r102.short_name = r11.getPack();
        AtomicBoolean r1 = new AtomicBoolean(false);
        InstallPluginBottomSheet$$ExternalSyntheticLambda31 r3 = new InstallPluginBottomSheet$$ExternalSyntheticLambda31(r1, this, r11, r6);
        AndroidUtilities.runOnUIThread(r3, 300);
        MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSet(r102, 0, true, new InstallPluginBottomSheet$$ExternalSyntheticLambda32(r1, r11, r3, this, r5, r6));
    }

    private final void showTexts(boolean r2) {
        this.nameText.show(r2);
        this.descriptionText.show(r2);
        TranslatableText r0 = this.nameText;
        if (r2 == false) goto L7;
        String r22 = r0.getTranslation();
        if (r22 != null) goto L8;
        r22 = this.nameText.getOriginal();
    L8:
        this.titleText = r22;
        updateTitle();
        return;
    L7:
        r22 = r0.getOriginal();
        goto L8
    }

    private final UItem spacer(float r1) {
        UItem r0 = UItem.asSpace(AndroidUtilities.dp(r1));
        r0.transparent = true;
        return r0;
    }

    private final void translateText(String r2, String r3, String r4, Function1<? super String, Unit> r5) {
        TranslatorUtils.translate(r2, r3, r4, new C01591(new InstallPluginBottomSheet$$ExternalSyntheticLambda16(new Ref.BooleanRef(), r5)));
    }

    private final UItem wheelRow(int r10, String r11) {
        if (StringsKt.endsWith(r11, ".whl", true) == false) goto L5;
        String r9 = r11;
    L6:
        if (r9 == null) goto L10;
        String r3 = StringsKt.dropLast(r9, 4);
        if (r3 == null) goto L10;
        List r92 = StringsKt.split$default(r3, new String[]{"-"}, false, 0, 6, null);
    L11:
        if (r92 == null) goto L18;
        if (r92.size() < 2) goto L18;
        return UItem.asSettingsCell(r10, StringsKt.replace$default((String) r92.get(0), '_', SignatureVisitor.SUPER, false, 4, null), (CharSequence) r92.get(1));
    L18:
        return UItem.asSettingsCell(r10, r11, null);
    L10:
        r92 = null;
        goto L11
    L5:
        r9 = null;
        goto L6
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        Context r2 = getContext();
        int r3 = this.currentAccount;
        BaseFragment r1 = getBaseFragment();
        if (r1 == null) goto L6;
        int r12 = r1.getClassGuid();
    L5:
        int r4 = r12;
        UniversalAdapter r0 = new UniversalAdapter(r9, r2, r3, r4, true, new InstallPluginBottomSheet$$ExternalSyntheticLambda26(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    L6:
        r12 = 0;
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        HintView2 r0 = this.currentHint;
        if (r0 == null) goto L5;
        r0.hide();
    L5:
        this.currentHint = null;
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        CharSequence r0 = this.titleText;
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onPreDraw(Canvas r4, int r5, float r6) {
        LinearLayout r3 = this.actionsPill;
        if (r3 == null) goto L11;
        float r42 = 1.0f - r6;
        r3.setAlpha(r42);
        int r52 = r3.getChildCount();
        int r0 = 0;
    L5:
        if (r0 >= r52) goto L15;
        View r1 = r3.getChildAt(r0);
        if (r42 <= 0.5f) goto L9;
        boolean r2 = true;
    L10:
        r1.setClickable(r2);
        r0 = r0 + 1;
        goto L5
    L9:
        r2 = false;
        goto L10
    L15:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onSwipeStarts() {
        HintView2 r0 = this.currentHint;
        if (r0 == null) goto L5;
        r0.hide();
    L5:
        this.currentHint = null;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout r1) {
        super.onViewCreated(r1);
        this.sheetContainer = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public /* bridge */ /* synthetic */ void setLastVisible(boolean r1) {
        super.setLastVisible(r1);
    }
}
