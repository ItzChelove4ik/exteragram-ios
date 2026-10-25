package com.exteragram.messenger.preferences;

import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import com.exteragram.messenger.utils.ui.PopupUtils;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;

@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0010\n\u0002\b\u0015\b&\u0018\u00002\u00020\u0001:\u0002\u0080\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020/H\u0002J\b\u00101\u001a\u00020/H\u0002J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020'H\u0002J\u0010\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020)H\u0002J\b\u00106\u001a\u00020/H\u0016J\b\u00107\u001a\u00020/H\u0016J\b\u00108\u001a\u000209H&J\b\u0010:\u001a\u00020/H\u0014J\u000e\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020=J=\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u000e\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020C0B2\u0006\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u00020GH\u0004¢\u0006\u0002\u0010HJ[\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u000e\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020C0B2\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u00020G2\b\b\u0002\u0010K\u001a\u00020'2\b\b\u0002\u0010L\u001a\u00020'H\u0005¢\u0006\u0002\u0010MJ\b\u0010N\u001a\u00020/H\u0004J#\u0010O\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020'0QH\u0000¢\u0006\u0002\bRJ\b\u0010S\u001a\u00020'H\u0014J\b\u0010T\u001a\u00020'H\u0014J\b\u0010U\u001a\u00020'H\u0016J\u001e\u0010V\u001a\u00020/2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020@0!2\u0006\u0010X\u001a\u00020YH$J0\u0010Z\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u0006\u0010[\u001a\u00020+2\u0006\u0010\\\u001a\u00020=2\u0006\u0010]\u001a\u00020)2\u0006\u0010^\u001a\u00020)H\u0014J0\u0010_\u001a\u00020'2\u0006\u0010?\u001a\u00020@2\u0006\u0010[\u001a\u00020+2\u0006\u0010\\\u001a\u00020=2\u0006\u0010]\u001a\u00020)2\u0006\u0010^\u001a\u00020)H\u0014J\u0018\u0010`\u001a\u00020/2\u0006\u0010[\u001a\u00020+2\u0006\u0010a\u001a\u000209H\u0004J\b\u0010b\u001a\u00020'H\u0017J(\u0010c\u001a\u00020/2\u0006\u0010d\u001a\u00020=2\u0006\u0010e\u001a\u00020=2\u0006\u0010f\u001a\u00020=2\u0006\u0010g\u001a\u00020=H\u0016J\u0010\u0010h\u001a\u00020=2\u0006\u0010i\u001a\u00020=H\u0014Ji\u0010j\u001a\u00020@2\n\u0010k\u001a\u0006\u0012\u0002\b\u00030l2\u0006\u0010m\u001a\u00020C2\u0006\u0010n\u001a\u00020'2\n\b\u0002\u0010o\u001a\u0004\u0018\u00010C2\b\b\u0002\u0010p\u001a\u00020=2\b\b\u0002\u0010q\u001a\u00020'2\n\b\u0002\u0010r\u001a\u0004\u0018\u0001092\b\b\u0002\u0010s\u001a\u00020'2\b\b\u0002\u0010t\u001a\u00020=H\u0000¢\u0006\u0002\buJe\u0010j\u001a\u00020@2\u0006\u0010v\u001a\u00020=2\u0006\u0010m\u001a\u00020C2\u0006\u0010n\u001a\u00020'2\n\b\u0002\u0010o\u001a\u0004\u0018\u00010C2\b\b\u0002\u0010p\u001a\u00020=2\b\b\u0002\u0010q\u001a\u00020'2\n\b\u0002\u0010r\u001a\u0004\u0018\u0001092\b\b\u0002\u0010s\u001a\u00020'2\b\b\u0002\u0010t\u001a\u00020=H\u0000¢\u0006\u0002\buJ\u0087\u0001\u0010w\u001a\u00020@2\n\u0010k\u001a\u0006\u0012\u0002\b\u00030l2\u0006\u0010m\u001a\u00020C2\b\b\u0002\u0010t\u001a\u00020=2\n\b\u0002\u0010x\u001a\u0004\u0018\u00010C2\n\b\u0002\u0010o\u001a\u0004\u0018\u00010C2\n\b\u0002\u0010y\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010q\u001a\u00020'2\n\b\u0002\u0010r\u001a\u0004\u0018\u0001092\b\b\u0002\u0010z\u001a\u00020'2\b\b\u0002\u0010{\u001a\u00020'2\n\b\u0002\u0010|\u001a\u0004\u0018\u00010'H\u0000¢\u0006\u0004\b}\u0010~J\u0083\u0001\u0010w\u001a\u00020@2\u0006\u0010v\u001a\u00020=2\u0006\u0010m\u001a\u00020C2\b\b\u0002\u0010t\u001a\u00020=2\n\b\u0002\u0010x\u001a\u0004\u0018\u00010C2\n\b\u0002\u0010o\u001a\u0004\u0018\u00010C2\n\b\u0002\u0010y\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010q\u001a\u00020'2\n\b\u0002\u0010r\u001a\u0004\u0018\u0001092\b\b\u0002\u0010z\u001a\u00020'2\b\b\u0002\u0010{\u001a\u00020'2\n\b\u0002\u0010|\u001a\u0004\u0018\u00010'H\u0000¢\u0006\u0004\b}\u0010\u007fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0081\u0001"}, d2 = {"Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/ui/ActionBar/BaseFragment;", "<init>", "()V", "listView", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "getListView$TMessagesProj", "()Lorg/telegram/ui/Components/UniversalRecyclerView;", "setListView$TMessagesProj", "(Lorg/telegram/ui/Components/UniversalRecyclerView;)V", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "getLayoutManager", "()Landroidx/recyclerview/widget/LinearLayoutManager;", "setLayoutManager", "(Landroidx/recyclerview/widget/LinearLayoutManager;)V", "fadeView", "Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;", "getFadeView", "()Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;", "setFadeView", "(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;)V", "recyclerViewState", "Landroid/os/Parcelable;", "scrollableViewNoiseSuppressor", "Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;", "iBlur3SourceColor", "Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;", "iBlur3SourceGlass", "Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;", "iBlur3Capture", "Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;", "iBlur3Positions", "Ljava/util/ArrayList;", "Landroid/graphics/RectF;", "iBlur3PositionActionBar", "headerFadeAnimator", "Landroid/animation/ValueAnimator;", "headerFadeShown", _UrlKt.FRAGMENT_ENCODE_SET, "headerFadeAlpha", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "clearViews", _UrlKt.FRAGMENT_ENCODE_SET, "blur3_InvalidateBlur", "updateTopFade", "updateHeaderFade", "animated", "setHeaderFadeAlpha", "alpha", "onResume", "onPause", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "initializeOptionStrings", "scrollToItem", "itemId", _UrlKt.FRAGMENT_ENCODE_SET, "showListDialog", PluginsConstants.Settings.ITEM, "Lorg/telegram/ui/Components/UItem;", "options", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "title", "currentValue", "onSelected", "Lcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;", "(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V", "icons", _UrlKt.FRAGMENT_ENCODE_SET, "useRadioCell", "skipIfValueNotUpdated", "(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZ)V", "showRestartBulletin", "toggleBooleanSettingAndRefresh", "stateSetter", "Landroidx/core/util/Consumer;", "toggleBooleanSettingAndRefresh$TMessagesProj", "needHideTitle", "hasHeaderFade", "isLightStatusBar", "fillItems", PluginsConstants.Settings.ITEMS, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.VIEW, "position", "x", "y", "onLongClick", "showCopyLinkOptions", "settingLink", "isSupportEdgeToEdge", "onInsets", "left", "top", "right", "bottom", "getListTopPadding", "topInset", "uCheck", "enumEntry", _UrlKt.FRAGMENT_ENCODE_SET, "text", "checked", "subtext", "checkBoxIcon", "searchable", "linkAlias", PluginsConstants.Settings.MULTILINE, "icon", "uCheck$TMessagesProj", "id", "uButton", "value", "showDivider", PluginsConstants.Settings.RED, PluginsConstants.Settings.ACCENT, "prioritizeTitleOverValue", "uButton$TMessagesProj", "(Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;", "(ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;", "HeaderContentView", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBasePreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BasePreferencesActivity.kt\ncom/exteragram/messenger/preferences/BasePreferencesActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,519:1\n1#2:520\n*E\n"})
/* loaded from: classes.dex */
public abstract class BasePreferencesActivity extends BaseFragment {
    private ChatActivityFadeView fadeView;
    private float headerFadeAlpha;
    private ValueAnimator headerFadeAnimator;
    private boolean headerFadeShown;
    private IBlur3Capture iBlur3Capture;
    private final RectF iBlur3PositionActionBar;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    protected LinearLayoutManager layoutManager;
    public UniversalRecyclerView listView;
    private Parcelable recyclerViewState;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014J0\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0014J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\bH\u0016¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;", "Landroid/widget/FrameLayout;", "Lorg/telegram/ui/ActionBar/Theme$Colorable;", "context", "Landroid/content/Context;", "<init>", "(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/content/Context;)V", "onMeasure", _UrlKt.FRAGMENT_ENCODE_SET, "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "onLayout", "changed", _UrlKt.FRAGMENT_ENCODE_SET, "left", "top", "right", "bottom", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "updateColors", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public final class HeaderContentView extends FrameLayout implements Theme.Colorable {
        final /* synthetic */ BasePreferencesActivity this$0;

        public HeaderContentView(BasePreferencesActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L14:
            super.dispatchDraw(r4);
            return;
        L5:
            if (BasePreferencesActivity.access$getScrollableViewNoiseSuppressor$p(this.this$0) == null) goto L14;
            if (BasePreferencesActivity.access$getIBlur3SourceGlass$p(this.this$0) == null) goto L14;
            BasePreferencesActivity.access$blur3_InvalidateBlur(this.this$0);
            if (BasePreferencesActivity.access$getIBlur3SourceGlass$p(this.this$0).inRecording() == true) goto L14;
            RecordingCanvas r0 = BasePreferencesActivity.access$getIBlur3SourceGlass$p(this.this$0).beginRecording(getMeasuredWidth(), getMeasuredHeight());
            r0.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundGray));
            if (SharedConfig.chatBlurEnabled() == false) goto L13;
            BasePreferencesActivity.access$getScrollableViewNoiseSuppressor$p(this.this$0).draw(r0, -2);
        L13:
            BasePreferencesActivity.access$getIBlur3SourceGlass$p(this.this$0).endRecording();
            goto L14
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            BasePreferencesActivity.access$updateHeaderFade(this.this$0, false);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            BasePreferencesActivity.access$updateTopFade(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            int r0 = this.this$0.getThemedColor(Theme.key_windowBackgroundGray);
            setBackgroundColor(r0);
            BasePreferencesActivity.access$getIBlur3SourceColor$p(this.this$0).setColor(r0);
            BasePreferencesActivity.access$updateTopFade(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1", "Lorg/telegram/ui/Components/Bulletin$Delegate;", "getTopOffset", _UrlKt.FRAGMENT_ENCODE_SET, "tag", "getBottomOffset", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.BasePreferencesActivity$onResume$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 implements Bulletin.Delegate {
        final /* synthetic */ BasePreferencesActivity this$0;

        public AnonymousClass1(BasePreferencesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return this.this$0.getBottomInset();
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight();
        }
    }

    public static boolean $r8$lambda$2XkPzyMLgPEoFCyfwWpI27jGeHQ(BasePreferencesActivity r0, Canvas r1, View r2, long r3) {
        return r0.getListView$TMessagesProj().drawChild(r1, r2, r3);
    }

    public static void $r8$lambda$Ij3MPNxngjiBZQnGGMNJHMrKwXI(BasePreferencesActivity r2, ItemOptions r3, String r4) {
        r2.showDialog(new BasePreferencesActivity$showCopyLinkOptions$1$2$1(r4, r2, r3.getContext(), r2.resourceProvider));
    }

    public static void $r8$lambda$J8lpDwhj5CURYfoZlw4B6f0uhHk(BasePreferencesActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: $r8$lambda$JDWUecVaZ6cx-VlL81WxsQKWYP8, reason: not valid java name */
    public static void m1636$r8$lambda$JDWUecVaZ6cxVlL81WxsQKWYP8(boolean r0, int r1, PopupUtils.OnItemClickListener r2, BasePreferencesActivity r3, UItem r4, CharSequence[] r5, int r6) {
        if (r0 == false) goto L5;
        if (r1 != r6) goto L5;
        return;
    L5:
        r2.onClick(r6);
        View r02 = r3.getListView$TMessagesProj().findViewByItemId(r4.id);
        if ((r02 instanceof TextCell) == false) goto L8;
        ((TextCell) r02).setValue(r5[r6], true);
    L8:
        UniversalAdapter r03 = r3.getListView$TMessagesProj().adapter;
        if (r03 == null) goto L12;
        r03.update(true);
        return;
    }

    public static void $r8$lambda$PohbPrV8pAWTGoLi_kTQMvfJnRM(BasePreferencesActivity r0, ValueAnimator r1) {
        r0.setHeaderFadeAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    public static void $r8$lambda$k7IrsXGeug19Q0QXMcoRuKyd6Qo(BasePreferencesActivity r2) {
        Intent r0 = r2.getContext().getPackageManager().getLaunchIntentForPackage(r2.getContext().getPackageName());
        if (r0 == null) goto L5;
        ComponentName r02 = r0.getComponent();
    L6:
        Intent r03 = Intent.makeRestartActivityTask(r02);
        r03.setPackage(r2.getContext().getPackageName());
        r2.getContext().startActivity(r03);
        Runtime.getRuntime().exit(0);
        return;
    L5:
        r02 = null;
        goto L6
    }

    public static void $r8$lambda$tX0yOM5R7_7cm6q9veWRk3xEkrM(UniversalRecyclerView r1, BasePreferencesActivity r2) {
        r1.postOnAnimation(new BasePreferencesActivity$$ExternalSyntheticLambda11(r2));
    }

    /* renamed from: $r8$lambda$ulwajnBn1zBqckjd2-PkYxxVeDs, reason: not valid java name */
    public static int m1637$r8$lambda$ulwajnBn1zBqckjd2PkYxxVeDs(BasePreferencesActivity r0, int r1) {
        return r0.getListView$TMessagesProj().findPositionByItemId(r1);
    }

    /* renamed from: $r8$lambda$vJl1gTG8-BK9M2VG8YWhm5C9OQc, reason: not valid java name */
    public static void m1638$r8$lambda$vJl1gTG8BK9M2VG8YWhm5C9OQc(String r0, BasePreferencesActivity r1) {
        if (AndroidUtilities.addToClipboard(r0) == false) goto L6;
        BulletinFactory.of(r1).createCopyBulletin(LocaleController.getString(R.string.LinkCopied)).show();
        return;
    }

    public BasePreferencesActivity() {
        int r0 = Build.VERSION.SDK_INT;
        BlurredBackgroundSourceRenderNode r1 = null;
        if (r0 < 31) goto L5;
        DownscaleScrollableNoiseSuppressor r3 = new DownscaleScrollableNoiseSuppressor();
    L6:
        this.scrollableViewNoiseSuppressor = r3;
        BlurredBackgroundSourceColor r32 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r32;
        if (r0 < 31) goto L9;
        r1 = new BlurredBackgroundSourceRenderNode(r32);
    L9:
        this.iBlur3SourceGlass = r1;
        ArrayList<RectF> r02 = new ArrayList();
        this.iBlur3Positions = r02;
        RectF r12 = new RectF();
        this.iBlur3PositionActionBar = r12;
        r02.add(r12);
        return;
    L5:
        r3 = null;
        goto L6
    }

    public static final /* synthetic */ void access$blur3_InvalidateBlur(BasePreferencesActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    public static final /* synthetic */ BlurredBackgroundSourceColor access$getIBlur3SourceColor$p(BasePreferencesActivity r0) {
        return r0.iBlur3SourceColor;
    }

    public static final /* synthetic */ BlurredBackgroundSourceRenderNode access$getIBlur3SourceGlass$p(BasePreferencesActivity r0) {
        return r0.iBlur3SourceGlass;
    }

    public static final /* synthetic */ DownscaleScrollableNoiseSuppressor access$getScrollableViewNoiseSuppressor$p(BasePreferencesActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    public static final /* synthetic */ void access$updateHeaderFade(BasePreferencesActivity r0, boolean r1) {
        r0.updateHeaderFade(r1);
    }

    public static final /* synthetic */ void access$updateTopFade(BasePreferencesActivity r0) {
        r0.updateTopFade();
    }

    private final void blur3_InvalidateBlur() {
        if (this.iBlur3Capture != null) goto L6;
        return;
    L6:
        if (this.fragmentView != null) goto L9;
        return;
    L9:
        if (Build.VERSION.SDK_INT >= 31) goto L11;
        return;
    L11:
        if (this.scrollableViewNoiseSuppressor != null) goto L13;
        return;
    L13:
        if (SharedConfig.chatBlurEnabled() == false) goto L21;
        int r0 = this.fragmentView.getMeasuredWidth();
        int r1 = this.fragmentView.getMeasuredHeight();
        int r2 = AndroidUtilities.dp(48.0f);
        this.iBlur3PositionActionBar.set(0.0f, -r2, r0, this.actionBar.getMeasuredHeight() + r2);
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3Positions, 1);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, r0, r1);
        return;
    }

    private final void setHeaderFadeAlpha(float r2) {
        this.headerFadeAlpha = r2;
        ChatActivityFadeView r1 = this.fadeView;
        if (r1 == null) goto L6;
        r1.setFadeTopAlpha(MathKt.roundToInt(r2 * 255.0f));
        return;
    }

    public static /* synthetic */ void showListDialog$default(BasePreferencesActivity r12, UItem r13, CharSequence[] r14, int[] r15, String r16, int r17, PopupUtils.OnItemClickListener r18, boolean r19, boolean r20, int r21, Object r22) {
        if (r22 == null) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: showListDialog");
        return;
    L5:
        if ((r21 & 64) == 0) goto L10;
        if (r15 != null) goto L8;
        boolean r1 = true;
    L9:
        boolean r10 = r1;
    L12:
        if ((r21 & 128) == 0) goto L15;
        boolean r11 = true;
    L16:
        r12.showListDialog(r13, r14, r15, r16, r17, r18, r10, r11);
        return;
    L15:
        r11 = r20;
        goto L16
    L8:
        r1 = false;
        goto L9
    L10:
        r10 = r19;
        goto L12
    }

    public static /* synthetic */ UItem uButton$TMessagesProj$default(BasePreferencesActivity r2, int r3, CharSequence r4, int r5, CharSequence r6, CharSequence r7, Boolean r8, boolean r9, String r10, boolean r11, boolean r12, Boolean r13, int r14, Object r15) {
        if (r15 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: uButton");
        return null;
    L4:
        if ((r14 & 4) == 0) goto L7;
        r5 = 0;
    L7:
        if ((r14 & 8) == 0) goto L10;
        r6 = null;
    L10:
        if ((r14 & 16) == 0) goto L13;
        r7 = null;
    L13:
        if ((r14 & 32) == 0) goto L16;
        r8 = null;
    L16:
        if ((r14 & 64) == 0) goto L19;
        r9 = true;
    L19:
        if ((r14 & 128) == 0) goto L22;
        r10 = null;
    L22:
        if ((r14 & 256) == 0) goto L25;
        r11 = false;
    L25:
        if ((r14 & 512) == 0) goto L28;
        r12 = false;
    L28:
        if ((r14 & 1024) == 0) goto L31;
        r13 = null;
    L31:
        return r2.uButton$TMessagesProj(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ UItem uButton$TMessagesProj$default(BasePreferencesActivity r2, Enum r3, CharSequence r4, int r5, CharSequence r6, CharSequence r7, Boolean r8, boolean r9, String r10, boolean r11, boolean r12, Boolean r13, int r14, Object r15) {
        if (r15 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: uButton");
        return null;
    L4:
        if ((r14 & 4) == 0) goto L7;
        r5 = 0;
    L7:
        if ((r14 & 8) == 0) goto L10;
        r6 = null;
    L10:
        if ((r14 & 16) == 0) goto L13;
        r7 = null;
    L13:
        if ((r14 & 32) == 0) goto L16;
        r8 = null;
    L16:
        if ((r14 & 64) == 0) goto L19;
        r9 = true;
    L19:
        if ((r14 & 128) == 0) goto L22;
        r10 = null;
    L22:
        if ((r14 & 256) == 0) goto L25;
        r11 = false;
    L25:
        if ((r14 & 512) == 0) goto L28;
        r12 = false;
    L28:
        if ((r14 & 1024) == 0) goto L31;
        r13 = null;
    L31:
        return r2.uButton$TMessagesProj(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ UItem uCheck$TMessagesProj$default(BasePreferencesActivity r3, int r4, CharSequence r5, boolean r6, CharSequence r7, int r8, boolean r9, String r10, boolean r11, int r12, int r13, Object r14) {
        if (r14 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: uCheck");
        return null;
    L4:
        if ((r13 & 8) == 0) goto L7;
        r7 = null;
    L7:
        if ((r13 & 16) == 0) goto L10;
        r8 = 0;
    L10:
        if ((r13 & 32) == 0) goto L13;
        r9 = true;
    L13:
        if ((r13 & 64) == 0) goto L16;
        r10 = null;
    L16:
        if ((r13 & 128) == 0) goto L19;
        r11 = true;
    L19:
        if ((r13 & 256) == 0) goto L22;
        r12 = 0;
    L22:
        return r3.uCheck$TMessagesProj(r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ UItem uCheck$TMessagesProj$default(BasePreferencesActivity r3, Enum r4, CharSequence r5, boolean r6, CharSequence r7, int r8, boolean r9, String r10, boolean r11, int r12, int r13, Object r14) {
        if (r14 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: uCheck");
        return null;
    L4:
        if ((r13 & 8) == 0) goto L7;
        r7 = null;
    L7:
        if ((r13 & 16) == 0) goto L10;
        r8 = 0;
    L10:
        if ((r13 & 32) == 0) goto L13;
        r9 = true;
    L13:
        if ((r13 & 64) == 0) goto L16;
        r10 = null;
    L16:
        if ((r13 & 128) == 0) goto L19;
        r11 = true;
    L19:
        if ((r13 & 256) == 0) goto L22;
        r12 = 0;
    L22:
        return r3.uCheck$TMessagesProj(r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    private final void updateHeaderFade(boolean r4) {
        if (this.fadeView == null) goto L22;
        boolean r0 = getListView$TMessagesProj().canScrollVertically(-1);
        if (this.headerFadeShown != r0) goto L8;
        return;
    L8:
        this.headerFadeShown = r0;
        ValueAnimator r1 = this.headerFadeAnimator;
        if (r1 == null) goto L11;
        r1.cancel();
    L11:
        this.headerFadeAnimator = null;
        float r12 = 0.0f;
        if (r4 == true) goto L17;
        if (r0 == false) goto L15;
        r12 = 1.0f;
    L15:
        setHeaderFadeAlpha(r12);
        return;
    L17:
        float r42 = this.headerFadeAlpha;
        if (r0 == false) goto L20;
        r12 = 1.0f;
    L20:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, r12});
        r43.addUpdateListener(new BasePreferencesActivity$$ExternalSyntheticLambda3(this));
        r43.setDuration(320);
        r43.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.headerFadeAnimator = r43;
        r43.start();
        return;
    }

    private final void updateTopFade() {
        ChatActivityFadeView r0 = this.fadeView;
        if (r0 != null) goto L5;
        return;
    L5:
        ChatHeaderUiHelper.setupChatTopFade(r0, this.actionBar, getThemedColor(Theme.key_windowBackgroundGray), this.actionBar.getMeasuredHeight(), true);
        this.fadeView.setFadeTopAlpha(MathKt.roundToInt(this.headerFadeAlpha * 255.0f));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void clearViews() {
        if (this.fragmentView != null) goto L5;
    L7:
        super.clearViews();
        return;
    L5:
        if (this.layoutManager == null) goto L7;
        this.recyclerViewState = getLayoutManager().onSaveInstanceState();
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        initializeOptionStrings();
        ActionBar r0 = this.actionBar;
        r0.setBackButtonImage(R.drawable.ic_ab_back);
        r0.setAllowOverlayTitle(false);
        r0.setTitle(getTitle());
        r0.setActionBarMenuOnItemClick(new BasePreferencesActivity$createView$1$1(this));
        r0.setCastShadows(false);
        r0.setAddToContainer(false);
        ViewGroup r02 = new HeaderContentView(this, r10);
        int r2 = Theme.key_windowBackgroundGray;
        r02.setBackgroundColor(getThemedColor(r2));
        ActionBar r3 = this.actionBar;
        if (r3.menu != null) goto L5;
        r3.createMenu();
    L5:
        this.iBlur3SourceColor.setColor(getThemedColor(r2));
        BlurredBackgroundSourceRenderNode r32 = this.iBlur3SourceGlass;
        if (r32 != null) goto L8;
    L13:
        BlurredBackgroundDrawableViewFactory r5 = new BlurredBackgroundDrawableViewFactory(this.iBlur3SourceColor);
    L14:
        r5.setSourceRootView(new ViewPositionWatcher(r02), r02);
        ActionBar r33 = this.actionBar;
        r33.setGlassPadding(AndroidUtilities.dp(12.0f));
        r33.setGlassTitleTextSize(20);
        r33.setupGlass(r5, BlurredBackgroundProviderImpl.topPanelChatActivity(getResourceProvider()));
        ChatHeaderUiHelper.applyChatHeaderGlassStyle(r33, true);
        int r6 = Theme.key_windowBackgroundWhiteBlackText;
        r33.setTitleColor(getThemedColor(r6));
        r33.setItemsColor(getThemedColor(r6), false);
        r33.setItemsBackgroundColor(getThemedColor(Theme.key_listSelector), false);
        UniversalRecyclerView r34 = new UniversalRecyclerView(this, new BasePreferencesActivity$$ExternalSyntheticLambda6(this), new BasePreferencesActivity$$ExternalSyntheticLambda7(this), new BasePreferencesActivity$$ExternalSyntheticLambda8(this));
        r34.setSections();
        if (needHideTitle() == false) goto L17;
        this.actionBar.setAdaptiveBackground(r34, true);
    L17:
        r34.adapter.setApplyBackground(false);
        r34.setClipToPadding(false);
        r34.setPadding(0, getListTopPadding(AndroidUtilities.statusBarHeight), 0, 0);
        LinearLayoutManager r62 = new LinearLayoutManager(r10, 1, false);
        setLayoutManager(r62);
        r34.setLayoutManager(r62);
        Parcelable r52 = this.recyclerViewState;
        if (r52 == null) goto L20;
        getLayoutManager().onRestoreInstanceState(r52);
        this.recyclerViewState = null;
    L20:
        r34.addOnScrollListener(new BasePreferencesActivity$createView$6$3(this));
        r34.addEdgeEffectListener(new BasePreferencesActivity$$ExternalSyntheticLambda9(r34, this));
        setListView$TMessagesProj(r34);
        this.iBlur3Capture = new ViewGroupPartRenderer(getListView$TMessagesProj(), r02, new BasePreferencesActivity$$ExternalSyntheticLambda10(this));
        r02.addView(getListView$TMessagesProj(), LayoutHelper.createFrame(-1, -1.0f));
        if (hasHeaderFade() == false) goto L23;
        ChatActivityFadeView r35 = new ChatActivityFadeView(r10);
        r35.setupColorKey(r2);
        r35.setFadeTopAlpha(0);
        this.fadeView = r35;
        r02.addView(r35, LayoutHelper.createFrameMatchParent());
    L23:
        r02.addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 48));
        this.fragmentView = r02;
        return r02;
    L8:
        if (Build.VERSION.SDK_INT >= 31) goto L11;
        r32 = null;
    L11:
        if (r32 == null) goto L13;
        r5 = new BlurredBackgroundDrawableViewFactory(r32);
        r5.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        goto L14
    }

    public abstract void fillItems(ArrayList<UItem> r1, UniversalAdapter r2);

    public final ChatActivityFadeView getFadeView() {
        return this.fadeView;
    }

    public final LinearLayoutManager getLayoutManager() {
        LinearLayoutManager r0 = this.layoutManager;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public int getListTopPadding(int r1) {
        return r1 + ActionBar.getCurrentActionBarHeight();
    }

    public final UniversalRecyclerView getListView$TMessagesProj() {
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public abstract String getTitle();

    public boolean hasHeaderFade() {
        return true;
    }

    public void initializeOptionStrings() {
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L7;
        return super.isLightStatusBar();
    L7:
        return ChatHeaderUiHelper.isLightChatStatusBar(r0, getThemedColor(Theme.key_windowBackgroundGray), true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @Deprecated(message = "Deprecated in Java")
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public boolean needHideTitle() {
        return false;
    }

    public abstract void onClick(UItem r1, View r2, int r3, float r4, float r5);

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        UniversalRecyclerView r12 = getListView$TMessagesProj();
        r12.setPadding(0, getListTopPadding(r2), 0, r4);
        r12.setClipToPadding(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        String r12 = SettingsRegistry.getInstance().getFirstSettingLink(getClass(), r1);
        if (r12 == null) goto L6;
        showCopyLinkOptions(r2, r12);
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L5;
        r0.update(false);
    L5:
        Bulletin.addDelegate(this, new AnonymousClass1(this));
    }

    public final void scrollToItem(int r4) {
        if (this.listView != null) goto L5;
        return;
    L5:
        int r0 = getListView$TMessagesProj().findPositionByItemId(r4);
        if (r0 < 0) goto L14;
        UniversalAdapter r1 = getListView$TMessagesProj().adapter;
        if (r1 == null) goto L10;
        int r12 = r1.getItemCount();
    L11:
        if (r0 >= r12) goto L14;
        getLayoutManager().scrollToPositionWithOffset(r0, AndroidUtilities.dp(80.0f));
        getListView$TMessagesProj().highlightRow(new BasePreferencesActivity$$ExternalSyntheticLambda2(this, r4));
        return;
    L10:
        r12 = 0;
    L14:
        SettingsRegistry.getInstance().onSettingNotFound(this);
    }

    public final void setFadeView(ChatActivityFadeView r1) {
        this.fadeView = r1;
    }

    public final void setLayoutManager(LinearLayoutManager r1) {
        this.layoutManager = r1;
    }

    public final void setListView$TMessagesProj(UniversalRecyclerView r1) {
        this.listView = r1;
    }

    public final void showCopyLinkOptions(View r5, String r6) {
        ItemOptions r0 = ItemOptions.makeOptions(this, r5);
        r0.setLongPressSelectionEnabled(false);
        r0.add(R.drawable.msg_copy, LocaleController.getString(R.string.CopyLink), new BasePreferencesActivity$$ExternalSyntheticLambda4(r6, this));
        r0.add(R.drawable.msg_share, LocaleController.getString(R.string.ShareLink), new BasePreferencesActivity$$ExternalSyntheticLambda5(this, r0, r6));
        r0.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r5));
        r0.show();
    }

    public final void showListDialog(UItem r12, CharSequence[] r13, String r14, int r15, PopupUtils.OnItemClickListener r16) {
        showListDialog$default(this, r12, r13, null, r14, r15, r16, false, false, 192, null);
    }

    @JvmOverloads
    public final void showListDialog(UItem r12, CharSequence[] r13, int[] r14, String r15, int r16, PopupUtils.OnItemClickListener r17) {
        showListDialog$default(this, r12, r13, r14, r15, r16, r17, false, false, 192, null);
    }

    @JvmOverloads
    public final void showListDialog(UItem r12, CharSequence[] r13, int[] r14, String r15, int r16, PopupUtils.OnItemClickListener r17, boolean r18) {
        showListDialog$default(this, r12, r13, r14, r15, r16, r17, r18, false, 128, null);
    }

    @JvmOverloads
    public final void showListDialog(UItem r8, CharSequence[] r9, int[] r10, String r11, int r12, PopupUtils.OnItemClickListener r13, boolean r14, boolean r15) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        PopupUtils.showDialog(r9, r10, r11, r12, getContext(), new BasePreferencesActivity$$ExternalSyntheticLambda0(r15, r12, r13, this, r8, r9), this.resourceProvider, r14);
    }

    public final void showRestartBulletin() {
        BulletinFactory.of(this).createSimpleBulletin(R.raw.info, LocaleController.getString(R.string.RestartRequired), LocaleController.getString(R.string.BotUnblock), new BasePreferencesActivity$$ExternalSyntheticLambda1(this)).show();
    }

    public final void toggleBooleanSettingAndRefresh$TMessagesProj(UItem r5, Consumer<Boolean> r6) {
        View r0 = getListView$TMessagesProj().findViewByItemId(r5.id);
        boolean r1 = !r5.checked;
        r6.accept(Boolean.valueOf(r1));
        r5.setChecked(r1);
        if ((r0 instanceof CheckBoxCell) == false) goto L6;
        ((CheckBoxCell) r0).setChecked(r1, true);
    L8:
        UniversalAdapter r4 = getListView$TMessagesProj().adapter;
        if (r4 == null) goto L12;
        r4.update(true);
        return;
    L12:
        return;
    L6:
        if ((r0 instanceof TextCheckCell) == false) goto L8;
        ((TextCheckCell) r0).setChecked(r1);
        goto L8
    }

    public final UItem uButton$TMessagesProj(int r9, CharSequence r10, int r11, CharSequence r12, CharSequence r13, Boolean r14, boolean r15, String r16, boolean r17, boolean r18, Boolean r19) {
        if (r13 != null) goto L5;
    L8:
        if (r12 != null) goto L10;
    L14:
        if (r12 != null) goto L16;
    L19:
        if (r11 == 0) goto L21;
        UItem r92 = UItem.asButton(r9, r11, r10);
    L22:
        if (r17 == false) goto L24;
        r92.red();
    L24:
        if (r18 == false) goto L26;
        r92.accent();
    L26:
        if (r15 == false) goto L28;
        r92.setSearchable(this);
    L28:
        if (r16 == null) goto L30;
        r92.setLinkAlias(r16, this);
    L30:
        if (r14 == null) goto L32;
        r92.showDivider(r14.booleanValue());
    L32:
        if (r19 == null) goto L34;
        r92.prioritizeTitleOverValue(r19.booleanValue());
    L34:
        return r92;
    L21:
        r92 = UItem.asButton(r9, r10);
        goto L22
    L16:
        if (r12.length() == 0) goto L19;
        r92 = UItem.asButton(r9, r10, r12);
        goto L22
    L10:
        if (r12.length() == 0) goto L14;
        if (r11 == 0) goto L14;
        r92 = UItem.asButton(r9, r11, r10, r12);
        goto L22
    L5:
        if (r13.length() == 0) goto L8;
        r92 = UItem.asButtonWithSubtext(r9, r11, r10, r13, 64, 60);
        goto L22
    }

    public final UItem uButton$TMessagesProj(Enum<?> r13, CharSequence r14, int r15, CharSequence r16, CharSequence r17, Boolean r18, boolean r19, String r20, boolean r21, boolean r22, Boolean r23) {
        return uButton$TMessagesProj(r13.ordinal() + 1, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23);
    }

    public final UItem uCheck$TMessagesProj(int r1, CharSequence r2, boolean r3, CharSequence r4, int r5, boolean r6, String r7, boolean r8, int r9) {
        if (r4 == null) goto L4;
        UItem r12 = UItem.asCheck(r1, r2, r4, r8);
    L5:
        r12.setChecked(r3);
        if (r9 == 0) goto L8;
        r12.iconResId = r9;
    L8:
        if (r5 == 0) goto L10;
        r12.setCheckBoxIcon(r5);
    L10:
        if (r6 == false) goto L12;
        r12.setSearchable(this);
    L12:
        if (r7 == null) goto L14;
        r12.setLinkAlias(r7, this);
    L14:
        return r12;
    L4:
        r12 = UItem.asCheck(r1, r2);
        goto L5
    }

    public final UItem uCheck$TMessagesProj(Enum<?> r11, CharSequence r12, boolean r13, CharSequence r14, int r15, boolean r16, String r17, boolean r18, int r19) {
        return uCheck$TMessagesProj(r11.ordinal() + 1, r12, r13, r14, r15, r16, r17, r18, r19);
    }
}
