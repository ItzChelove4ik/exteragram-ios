package com.exteragram.messenger.ai.ui.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.data.Suggestions;
import com.exteragram.messenger.ai.network.Client;
import com.exteragram.messenger.ai.network.GenerationCallback;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b>\u0018\u0000 y2\u00020\u0001:\u0004xyz{B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020&H\u0016J(\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u0018H\u0016J\u0010\u00100\u001a\u00020(2\u0006\u00101\u001a\u000202H\u0014J\b\u00103\u001a\u00020\u000bH\u0016J\u001e\u00104\u001a\u00020+2\f\u00105\u001a\b\u0012\u0004\u0012\u000207062\u0006\u00108\u001a\u000209H\u0014J0\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u0002072\u0006\u0010'\u001a\u00020(2\u0006\u0010<\u001a\u00020\u00182\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>H\u0014J\b\u0010@\u001a\u00020+H\u0016J\b\u0010A\u001a\u00020+H\u0016J\b\u0010B\u001a\u00020+H\u0016J\u0018\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020\u00132\u0006\u0010E\u001a\u00020\u0013H\u0016J\u0010\u0010F\u001a\u00020+2\u0006\u00101\u001a\u000202H\u0002J\b\u0010G\u001a\u00020+H\u0002J\b\u0010H\u001a\u00020+H\u0002J\u0010\u0010I\u001a\u00020+2\u0006\u0010J\u001a\u00020\u0013H\u0002J\u0010\u0010K\u001a\u00020+2\u0006\u0010L\u001a\u00020\u0018H\u0002J\u0010\u0010M\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u0018H\u0002J\u001c\u0010N\u001a\u00020\u00182\b\u0010O\u001a\u0004\u0018\u00010\u000b2\b\u0010P\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010Q\u001a\u00020\u0018H\u0002J\b\u0010R\u001a\u00020+H\u0002J\u0010\u0010S\u001a\u00020+2\u0006\u0010T\u001a\u00020\u0003H\u0002J\u0010\u0010U\u001a\u00020+2\u0006\u0010V\u001a\u00020\u0003H\u0002J\u0010\u0010W\u001a\u00020+2\u0006\u0010T\u001a\u00020\u0003H\u0002J\u0012\u0010X\u001a\u0004\u0018\u00010\u00032\u0006\u0010T\u001a\u00020\u0003H\u0002J\b\u0010Y\u001a\u00020\u0013H\u0002J\u0010\u0010Z\u001a\u00020\u00132\u0006\u0010O\u001a\u00020\u000bH\u0002J\u001c\u0010[\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\\\u001a\u00020\u00182\b\u0010]\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010^\u001a\u00020+2\b\u0010_\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010`\u001a\u00020+2\u0006\u0010a\u001a\u00020\rH\u0002J\b\u0010b\u001a\u00020+H\u0002J\b\u0010c\u001a\u00020+H\u0002J\b\u0010d\u001a\u00020+H\u0002J\b\u0010e\u001a\u00020+H\u0002J\b\u0010f\u001a\u00020+H\u0002J\u0014\u0010g\u001a\u0004\u0018\u00010\r2\b\u0010h\u001a\u0004\u0018\u00010\u000bH\u0002J \u0010i\u001a\u00020\u00132\u0006\u0010O\u001a\u00020\u000b2\u0006\u0010P\u001a\u00020\u000b2\u0006\u0010j\u001a\u00020\u000bH\u0002J\u0010\u0010k\u001a\u00020\u00132\u0006\u0010h\u001a\u00020\u000bH\u0002J\u0010\u0010l\u001a\u00020\u00132\u0006\u0010h\u001a\u00020\u000bH\u0002J\u0010\u0010m\u001a\u00020\u00132\u0006\u0010h\u001a\u00020\u000bH\u0002J\b\u0010n\u001a\u00020+H\u0002J\u0010\u0010n\u001a\u00020+2\u0006\u0010o\u001a\u00020\u0013H\u0002J\u001a\u0010n\u001a\u00020+2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u0010o\u001a\u00020\u0013H\u0002J\u0012\u0010p\u001a\u0004\u0018\u00010\u000b2\u0006\u00101\u001a\u000202H\u0002J\b\u0010q\u001a\u00020+H\u0002J\b\u0010r\u001a\u00020+H\u0002J\b\u0010s\u001a\u00020\u0013H\u0002J\u0012\u0010t\u001a\u00020\u000b2\b\u0010u\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010v\u001a\u00020\u000b2\b\u0010w\u001a\u0004\u0018\u00010\u000bH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006|"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "currentService", "Lcom/exteragram/messenger/ai/data/Service;", "<init>", "(Lcom/exteragram/messenger/ai/data/Service;)V", "clipChangedListener", "Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;", "clipboardManager", "Landroid/content/ClipboardManager;", "pasteString", _UrlKt.FRAGMENT_ENCODE_SET, "pasteInput", "Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;", "urlCell", "Lorg/telegram/ui/Cells/EditTextCell;", "modelCell", "keyCell", "hasChanges", _UrlKt.FRAGMENT_ENCODE_SET, "updatingFields", "isTesting", "forceCustomPreset", "selectedPresetIndex", _UrlKt.FRAGMENT_ENCODE_SET, "shiftDp", "keyboardInset", "initialUrl", "initialModel", "initialKey", "initialReasoningEnabled", "reasoningEnabled", "testingClient", "Lcom/exteragram/messenger/ai/network/Client;", "testingRequestId", "testingProgressDialog", "Lorg/telegram/ui/ActionBar/AlertDialog;", "onInsetsInternal", "Landroidx/core/view/WindowInsetsCompat;", PluginsConstants.Settings.VIEW, "Landroid/view/View;", "windowInsets", "onInsets", _UrlKt.FRAGMENT_ENCODE_SET, "left", "top", "right", "bottom", "createView", "context", "Landroid/content/Context;", "getTitle", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onResume", "onPause", "onFragmentDestroy", "onTransitionAnimationEnd", "isOpen", "backward", "createFields", "initializeState", "updateFormState", "setTesting", "testing", "applyPreset", "index", "getPresetTitle", "findPresetIndex", "url", "model", "getCustomPresetIndex", "saveConfig", "testAndSave", "service", "useExistingService", "existingService", "applySelection", "findExistingService", "validateFields", "isValidServiceUrl", "getFieldForError", "errorCode", "errorMessage", "showFieldError", "cell", "applyParsedServiceInput", PluginsConstants.Settings.TYPE_INPUT, "showTestingProgressDialog", "cancelTesting", "stopTestingRequest", "hideTestingProgressDialog", "clearTestingState", "parseServiceInput", "text", "isLikelyService", PluginsConstants.Settings.KEY, "isLikelyServiceUrl", "isLikelyModel", "isLikelyApiKey", "updateClipboardState", "updateList", "readClipboardText", "confirmDeleteService", "deleteCurrentService", "hasFields", "getFieldText", "field", "safeString", "value", "ServiceItem", "Companion", "ServicePreset", "ParsedServiceInput", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEditServiceActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EditServiceActivity.kt\ncom/exteragram/messenger/ai/ui/activities/EditServiceActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,653:1\n1915#2,2:654\n296#2,2:657\n1586#2:660\n1661#2,3:661\n777#2:664\n873#2,2:665\n1#3:656\n29#4:659\n*S KotlinDebug\n*F\n+ 1 EditServiceActivity.kt\ncom/exteragram/messenger/ai/ui/activities/EditServiceActivity\n*L\n143#1:654,2\n451#1:657,2\n544#1:660\n544#1:661,3\n544#1:664\n544#1:665,2\n471#1:659\n*E\n"})
/* loaded from: classes4.dex */
public final class EditServiceActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private static final ServicePreset[] SERVICE_PRESETS = null;
    private final ClipboardManager.OnPrimaryClipChangedListener clipChangedListener;
    private ClipboardManager clipboardManager;
    private final Service currentService;
    private boolean forceCustomPreset;
    private boolean hasChanges;
    private String initialKey;
    private String initialModel;
    private boolean initialReasoningEnabled;
    private String initialUrl;
    private boolean isTesting;
    private EditTextCell keyCell;
    private int keyboardInset;
    private EditTextCell modelCell;
    private ParsedServiceInput pasteInput;
    private String pasteString;
    private boolean reasoningEnabled;
    private int selectedPresetIndex;
    private int shiftDp;
    private Client testingClient;
    private AlertDialog testingProgressDialog;
    private String testingRequestId;
    private boolean updatingFields;
    private EditTextCell urlCell;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SERVICE_PRESETS", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;", "[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;", _UrlKt.FRAGMENT_ENCODE_SET, "url", _UrlKt.FRAGMENT_ENCODE_SET, "model", PluginsConstants.Settings.KEY, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "getModel", "getKey", "hasServiceFields", _UrlKt.FRAGMENT_ENCODE_SET, "differsFrom", "currentUrl", "currentModel", "currentKey", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ParsedServiceInput {
        private final String key;
        private final String model;
        private final String url;

        public ParsedServiceInput(String r1, String r2, String r3) {
            this.url = r1;
            this.model = r2;
            this.key = r3;
        }

        public final boolean differsFrom(String r2, String r3, String r4) {
            String r0 = this.url;
            if (r0 != null) goto L5;
        L9:
            String r22 = this.model;
            if (r22 != null) goto L12;
        L16:
            String r23 = this.key;
            if (r23 != null) goto L19;
            return false;
        L19:
            if (r23.length() != 0) goto L22;
            return false;
        L22:
            if (Intrinsics.areEqual(this.key, r4) == true) goto L29;
            return true;
        L29:
            return false;
        L12:
            if (r22.length() == 0) goto L16;
            if (Intrinsics.areEqual(this.model, r3) == true) goto L16;
            return true;
        L5:
            if (r0.length() == 0) goto L9;
            if (Intrinsics.areEqual(this.url, r2) == true) goto L9;
            return true;
        }

        public final String getKey() {
            return this.key;
        }

        public final String getModel() {
            return this.model;
        }

        public final String getUrl() {
            return this.url;
        }

        public final boolean hasServiceFields() {
            if (TextUtils.isEmpty(this.url) == true) goto L5;
            return true;
        L5:
            if (TextUtils.isEmpty(this.model) == false) goto L11;
            return false;
        L11:
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "PRESET_GEMINI", "PRESET_OPENAI", "PRESET_OPENROUTER", "PRESET_PERPLEXITY", "PRESET_CUSTOM", "PASTE", "SAVE", "DELETE", "REASONING", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum ServiceItem extends Enum<ServiceItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ServiceItem[] $VALUES = null;
        public static final ServiceItem DELETE = null;
        public static final ServiceItem PASTE = null;
        public static final ServiceItem PRESET_CUSTOM = null;
        public static final ServiceItem PRESET_GEMINI = null;
        public static final ServiceItem PRESET_OPENAI = null;
        public static final ServiceItem PRESET_OPENROUTER = null;
        public static final ServiceItem PRESET_PERPLEXITY = null;
        public static final ServiceItem REASONING = null;
        public static final ServiceItem SAVE = null;
        private final int id;

        private static final /* synthetic */ ServiceItem[] $values() {
            return new ServiceItem[]{PRESET_GEMINI, PRESET_OPENAI, PRESET_OPENROUTER, PRESET_PERPLEXITY, PRESET_CUSTOM, PASTE, SAVE, DELETE, REASONING};
        }

        static {
            PRESET_GEMINI = new ServiceItem("PRESET_GEMINI", 0);
            PRESET_OPENAI = new ServiceItem("PRESET_OPENAI", 1);
            PRESET_OPENROUTER = new ServiceItem("PRESET_OPENROUTER", 2);
            PRESET_PERPLEXITY = new ServiceItem("PRESET_PERPLEXITY", 3);
            PRESET_CUSTOM = new ServiceItem("PRESET_CUSTOM", 4);
            PASTE = new ServiceItem("PASTE", 5);
            SAVE = new ServiceItem("SAVE", 6);
            DELETE = new ServiceItem("DELETE", 7);
            REASONING = new ServiceItem("REASONING", 8);
            ServiceItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ServiceItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<ServiceItem> getEntries() {
            return $ENTRIES;
        }

        public static ServiceItem valueOf(String r1) {
            return (ServiceItem) Enum.valueOf(ServiceItem.class, r1);
        }

        public static ServiceItem[] values() {
            return (ServiceItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "url", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getUrl", "getModel", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ServicePreset {
        private final String model;
        private final String name;
        private final String url;

        public ServicePreset(String r1, String r2, String r3) {
            this.name = r1;
            this.url = r2;
            this.model = r3;
        }

        public final String getModel() {
            return this.model;
        }

        public final String getName() {
            return this.name;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ServiceItem.values().length];
            r0[ServiceItem.PRESET_GEMINI.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L25:
            r0[ServiceItem.PRESET_OPENAI.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L39:
            r0[ServiceItem.PRESET_OPENROUTER.ordinal()] = 3;     // Catch: NoSuchFieldError -> L16
        L29:
            r0[ServiceItem.PRESET_PERPLEXITY.ordinal()] = 4;     // Catch: NoSuchFieldError -> L17
        L31:
            r0[ServiceItem.PRESET_CUSTOM.ordinal()] = 5;     // Catch: NoSuchFieldError -> L18
        L23:
            r0[ServiceItem.PASTE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L19
        L33:
            r0[ServiceItem.SAVE.ordinal()] = 7;     // Catch: NoSuchFieldError -> L20
        L35:
            r0[ServiceItem.DELETE.ordinal()] = 8;     // Catch: NoSuchFieldError -> L21
        L27:
            r0[ServiceItem.REASONING.ordinal()] = 9;     // Catch: NoSuchFieldError -> L22
        L12:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$1", "Lorg/telegram/ui/Cells/EditTextCell;", "onTextChanged", _UrlKt.FRAGMENT_ENCODE_SET, "newText", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.EditServiceActivity$createFields$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EditTextCell {
        final /* synthetic */ EditServiceActivity this$0;

        public AnonymousClass1(Context r8, EditServiceActivity r9, String r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r8, r10, false, false, 128, r11);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r1) {
            EditServiceActivity.access$updateFormState(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$3", "Lorg/telegram/ui/Cells/EditTextCell;", "onTextChanged", _UrlKt.FRAGMENT_ENCODE_SET, "newText", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.EditServiceActivity$createFields$3, reason: invalid class name */
    public static final class AnonymousClass3 extends EditTextCell {
        final /* synthetic */ EditServiceActivity this$0;

        public AnonymousClass3(Context r8, EditServiceActivity r9, String r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r8, r10, false, false, 64, r11);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r1) {
            EditServiceActivity.access$updateFormState(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$5", "Lorg/telegram/ui/Cells/EditTextCell;", "onTextChanged", _UrlKt.FRAGMENT_ENCODE_SET, "newText", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.EditServiceActivity$createFields$5, reason: invalid class name */
    public static final class AnonymousClass5 extends EditTextCell {
        final /* synthetic */ EditServiceActivity this$0;

        public AnonymousClass5(Context r8, EditServiceActivity r9, String r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r8, r10, false, false, 256, r11);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r1) {
            EditServiceActivity.access$updateFormState(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2", "Lcom/exteragram/messenger/ai/network/GenerationCallback;", "onChunk", _UrlKt.FRAGMENT_ENCODE_SET, "string", _UrlKt.FRAGMENT_ENCODE_SET, "onResponse", "finalResponse", "onError", "errorCode", _UrlKt.FRAGMENT_ENCODE_SET, "errorMessage", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.EditServiceActivity$testAndSave$2, reason: invalid class name */
    public static final class AnonymousClass2 implements GenerationCallback {
        final /* synthetic */ Service $service;
        final /* synthetic */ EditServiceActivity this$0;

        public static void $r8$lambda$IiF_AungF8sXl5BJJVqdLioh6QU(EditServiceActivity r2, Service r3) {
            EditServiceActivity.access$clearTestingState(r2);
            if (EditServiceActivity.access$getCurrentService$p(r2) == null) goto L5;
            AiController.getInstance().updateService(EditServiceActivity.access$getCurrentService$p(r2), r3);
        L6:
            EditServiceActivity.access$applySelection(r2, r3);
            return;
        L5:
            AiController.getInstance().addService(r3);
            goto L6
        }

        public static void $r8$lambda$pRZeN_KicN8khW_IJzcsWQJulRY(EditServiceActivity r0, int r1, String r2) {
            EditServiceActivity.access$clearTestingState(r0);
            AiController.showErrorBulletin(r0, r1, r2);
            EditServiceActivity.access$showFieldError(r0, EditServiceActivity.access$getFieldForError(r0, r1, r2));
        }

        public AnonymousClass2(EditServiceActivity r1, Service r2) {
            this.this$0 = r1;
            this.$service = r2;
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onChunk(String r1) {
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onError(int r2, String r3) {
            AndroidUtilities.runOnUIThread(new EditServiceActivity$testAndSave$2$$ExternalSyntheticLambda0(this.this$0, r2, r3));
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onResponse(String r2) {
            AndroidUtilities.runOnUIThread(new EditServiceActivity$testAndSave$2$$ExternalSyntheticLambda1(this.this$0, this.$service));
        }
    }

    public static void $r8$lambda$16DHgHjQFg621J5IV517E4afcTI(EditServiceActivity r0) {
        r0.updateClipboardState();
    }

    public static boolean $r8$lambda$1SCL0CnuTbI82uTDAeg_HX1M5zw(EditServiceActivity r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
    L8:
        EditTextCell r12 = r0.keyCell;
        EditTextCell r22 = null;
        if (r12 != null) goto L11;
        r12 = null;
    L11:
        r12.editText.requestFocus();
        EditTextCell r02 = r0.keyCell;
        if (r02 == null) goto L15;
        r22 = r02;
    L15:
        AndroidUtilities.showKeyboard(r22.editText);
        return true;
    L5:
        if (r2 == 6) goto L8;
        return false;
    }

    /* renamed from: $r8$lambda$CdZ809pBHO3u-h3ZzxpJc0JEu18, reason: not valid java name */
    public static void m1087$r8$lambda$CdZ809pBHO3uh3ZzxpJc0JEu18(EditServiceActivity r0, boolean r1) {
        r0.reasoningEnabled = r1;
    }

    public static void $r8$lambda$LyjYmxXvUoag5N9K1FIKslL2SH0(EditServiceActivity r0, DialogInterface r1) {
        r0.cancelTesting();
    }

    public static boolean $r8$lambda$_m8vjYxg1LD6Lz_KbmGMzrOsdn8(EditServiceActivity r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        r0.saveConfig();
        return true;
    L6:
        return false;
    }

    public static boolean $r8$lambda$lscJkIKf7n3nnFMVVhpBpnjxbUM(EditServiceActivity r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
    L8:
        EditTextCell r12 = r0.modelCell;
        EditTextCell r22 = null;
        if (r12 != null) goto L11;
        r12 = null;
    L11:
        r12.editText.requestFocus();
        EditTextCell r02 = r0.modelCell;
        if (r02 == null) goto L15;
        r22 = r02;
    L15:
        AndroidUtilities.showKeyboard(r22.editText);
        return true;
    L5:
        if (r2 == 6) goto L8;
        return false;
    }

    public static void $r8$lambda$rUqfvXf4VbxG9eWlgbCm0fJDEno(EditServiceActivity r0, AlertDialog r1, int r2) {
        r0.deleteCurrentService();
    }

    public static void $r8$lambda$wYDNLouUWvFNTh3qP5BBmNXhyr4(EditServiceActivity r4) {
        View r42 = r4.getListView$TMessagesProj().findFocus();
        if (r42 == null) goto L6;
        r42.requestRectangleOnScreen(new Rect(0, 0, r42.getWidth(), r42.getHeight()), false);
        return;
    }

    static {
        Companion = new Companion(null);
        SERVICE_PRESETS = new ServicePreset[]{new ServicePreset("Gemini", "https://generativelanguage.googleapis.com/v1beta", "gemini-3.5-flash"), new ServicePreset("OpenAI", "https://api.openai.com/v1", "gpt-5.4-mini"), new ServicePreset("OpenRouter", "https://openrouter.ai/api/v1", "openai/gpt-5.4-mini"), new ServicePreset("Perplexity", "https://api.perplexity.ai/v1/agent", "perplexity/glm-5.3-flash"), new ServicePreset(null, null, null)};
    }

    @JvmOverloads
    public EditServiceActivity() {
        this(null, 1, null);
    }

    @JvmOverloads
    public EditServiceActivity(Service r1) {
        this.currentService = r1;
        this.clipChangedListener = new EditServiceActivity$$ExternalSyntheticLambda4(this);
        this.shiftDp = -4;
    }

    public /* synthetic */ EditServiceActivity(Service r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = null;
    L5:
        this(r1);
    }

    public static final /* synthetic */ void access$applySelection(EditServiceActivity r0, Service r1) {
        r0.applySelection(r1);
    }

    public static final /* synthetic */ void access$clearTestingState(EditServiceActivity r0) {
        r0.clearTestingState();
    }

    public static final /* synthetic */ Service access$getCurrentService$p(EditServiceActivity r0) {
        return r0.currentService;
    }

    public static final /* synthetic */ EditTextCell access$getFieldForError(EditServiceActivity r0, int r1, String r2) {
        return r0.getFieldForError(r1, r2);
    }

    public static final /* synthetic */ void access$showFieldError(EditServiceActivity r0, EditTextCell r1) {
        r0.showFieldError(r1);
    }

    public static final /* synthetic */ void access$updateFormState(EditServiceActivity r0) {
        r0.updateFormState();
    }

    private final void applyParsedServiceInput(ParsedServiceInput r4) {
        this.updatingFields = true;
        String r0 = r4.getUrl();
        EditTextCell r1 = null;
        if (r0 != null) goto L5;
    L11:
        String r02 = r4.getModel();
        if (r02 != null) goto L14;
    L20:
        String r03 = r4.getKey();
        if (r03 != null) goto L23;
    L30:
        this.forceCustomPreset = false;
        this.updatingFields = false;
        updateFormState();
        return;
    L23:
        if (r03.length() == 0) goto L30;
        EditTextCell r04 = this.keyCell;
        if (r04 == null) goto L29;
        r1 = r04;
    L29:
        r1.setText(r4.getKey());
        goto L30
    L14:
        if (r02.length() == 0) goto L20;
        EditTextCell r05 = this.modelCell;
        if (r05 != null) goto L19;
        r05 = null;
    L19:
        r05.setText(r4.getModel());
        goto L20
    L5:
        if (r0.length() == 0) goto L11;
        EditTextCell r06 = this.urlCell;
        if (r06 != null) goto L10;
        r06 = null;
    L10:
        r06.setText(r4.getUrl());
        goto L11
    }

    private final void applyPreset(int r5) {
        ServicePreset r52 = SERVICE_PRESETS[r5];
        EditTextCell r3 = null;
        if (r52.getUrl() != null) goto L5;
    L22:
        this.forceCustomPreset = true;
        this.updatingFields = true;
        EditTextCell r53 = this.urlCell;
        if (r53 != null) goto L25;
        r53 = null;
    L25:
        r53.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        EditTextCell r54 = this.modelCell;
        if (r54 != null) goto L28;
        r54 = null;
    L28:
        r54.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.updatingFields = false;
        updateFormState();
        EditTextCell r55 = this.urlCell;
        if (r55 != null) goto L31;
        r55 = null;
    L31:
        r55.editText.requestFocus();
        EditTextCell r4 = this.urlCell;
        if (r4 == null) goto L35;
        r3 = r4;
    L35:
        AndroidUtilities.showKeyboard(r3.editText);
        return;
    L5:
        if (r52.getModel() == null) goto L22;
        this.forceCustomPreset = false;
        this.updatingFields = true;
        EditTextCell r0 = this.urlCell;
        if (r0 != null) goto L10;
        r0 = null;
    L10:
        r0.setText(r52.getUrl());
        EditTextCell r02 = this.modelCell;
        if (r02 != null) goto L13;
        r02 = null;
    L13:
        r02.setText(r52.getModel());
        this.updatingFields = false;
        updateFormState();
        EditTextCell r56 = this.keyCell;
        if (r56 != null) goto L16;
        r56 = null;
    L16:
        r56.editText.requestFocus();
        EditTextCell r42 = this.keyCell;
        if (r42 == null) goto L20;
        r3 = r42;
    L20:
        AndroidUtilities.showKeyboard(r3.editText);
    }

    private final void applySelection(Service r3) {
        AiConfig.setSelectedServices(r3);
        getNotificationCenter().postNotificationName(NotificationCenter.servicesUpdated, new Object[0]);
        finishFragment();
    }

    private final void cancelTesting() {
        stopTestingRequest();
        clearTestingState();
    }

    private final void clearTestingState() {
        hideTestingProgressDialog();
        setTesting(false);
        this.testingRequestId = null;
        this.testingClient = null;
    }

    private final void confirmDeleteService() {
        if (this.currentService != null) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L15;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.Delete));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.DeleteServiceInfo, new Object[]{this.currentService.getShortModel()})));
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new EditServiceActivity$$ExternalSyntheticLambda7(this));
        TextView r2 = null;
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r0.create();
        showDialog(r02);
        View r3 = r02.getButton(-1);
        if ((r3 instanceof TextView) == false) goto L10;
        r2 = (TextView) r3;
    L10:
        if (r2 == null) goto L14;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L14:
        return;
    }

    private final void createFields(Context r5) {
        AnonymousClass1 r2 = new AnonymousClass1(r5, this, LocaleController.getString(R.string.ServiceURL), this.resourceProvider);
        r2.editText.setInputType(524305);
        r2.editText.setRawInputType(524305);
        r2.editText.setImeOptions(268435461);
        r2.editText.setOnEditorActionListener(new EditServiceActivity$$ExternalSyntheticLambda0(this));
        this.urlCell = r2;
        AnonymousClass3 r3 = new AnonymousClass3(r5, this, LocaleController.getString(R.string.ServiceModel), this.resourceProvider);
        r3.editText.setInputType(524289);
        r3.editText.setRawInputType(524289);
        r3.editText.setImeOptions(268435461);
        r3.editText.setOnEditorActionListener(new EditServiceActivity$$ExternalSyntheticLambda1(this));
        this.modelCell = r3;
        AnonymousClass5 r22 = new AnonymousClass5(r5, this, LocaleController.getString(R.string.ServiceKey), this.resourceProvider);
        r22.editText.setInputType(524433);
        r22.editText.setRawInputType(524433);
        r22.editText.setImeOptions(268435462);
        r22.editText.setOnEditorActionListener(new EditServiceActivity$$ExternalSyntheticLambda2(this));
        this.keyCell = r22;
    }

    private final void deleteCurrentService() {
        Service r0 = this.currentService;
        if (r0 == null) goto L7;
        boolean r2 = true;
        if (r0.isSelected() != true) goto L7;
    L9:
        if (this.currentService != null) goto L11;
        return;
    L11:
        if (AiController.getInstance().removeService(this.currentService) == false) goto L19;
        if (r2 == false) goto L16;
        AiConfig.clearSelectedService();
        if (AiController.getInstance().isServicesEmpty() == true) goto L16;
        AiConfig.setSelectedServices(AiController.getInstance().getAll().get(0));
    L16:
        getNotificationCenter().postNotificationName(NotificationCenter.servicesUpdated, new Object[0]);
        finishFragment();
        return;
    L19:
        return;
    L7:
        r2 = false;
        goto L9
    }

    private final Service findExistingService(Service r3) {
        Iterator<T> r2 = AiController.getInstance().getAll().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (Intrinsics.areEqual((Service) r0, r3) == false) goto L4;
    L10:
        return (Service) r0;
    L8:
        r0 = null;
        goto L10
    }

    private final int findPresetIndex(String r5, String r6) {
        int r0 = SERVICE_PRESETS.length - 1;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        ServicePreset r2 = SERVICE_PRESETS[r1];
        if (TextUtils.equals(r5, r2.getUrl()) == false) goto L9;
        if (TextUtils.equals(r6, r2.getModel()) == false) goto L9;
        return r1;
    L9:
        r1 = r1 + 1;
        goto L3
    L11:
        return getCustomPresetIndex();
    }

    private final int getCustomPresetIndex() {
        return SERVICE_PRESETS.length - 1;
    }

    private final EditTextCell getFieldForError(int r4, String r5) {
        switch(r4) {
            case 400: goto L20;
            case 401: goto L16;
            case 402: goto L4;
            case 403: goto L16;
            case 404: goto L5;
            default: goto L4;
        };
    L4:
        return null;
    L5:
        if (r5 != null) goto L7;
    L12:
        EditTextCell r3 = this.urlCell;
        if (r3 != null) goto L15;
        return null;
    L15:
        return r3;
    L7:
        if (StringsKt.contains$default(r5.toLowerCase(Locale.ROOT), "model", false, 2, null) == false) goto L12;
        EditTextCell r32 = this.modelCell;
        if (r32 != null) goto L11;
        return null;
    L11:
        return r32;
    L16:
        EditTextCell r33 = this.keyCell;
        if (r33 != null) goto L19;
        return null;
    L19:
        return r33;
    L20:
        EditTextCell r34 = this.modelCell;
        if (r34 != null) goto L23;
        return null;
    L23:
        return r34;
    }

    private final String getFieldText(EditTextCell r1) {
        if (r1 == null) goto L11;
        CharSequence r0 = r1.getText();
        if (r0 == null) goto L13;
        String r02 = r0.toString();
        if (r02 == null) goto L14;
        String r03 = StringsKt.trim(r02).toString();
        if (r03 == null) goto L15;
        return r03;
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private final String getPresetTitle(int r1) {
        String r0 = SERVICE_PRESETS[r1].getName();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return LocaleController.getString(R.string.ServiceProviderCustom);
    }

    private final boolean hasFields() {
        if (this.urlCell != null) goto L5;
        return false;
    L5:
        if (this.modelCell != null) goto L7;
        return false;
    L7:
        if (this.keyCell == null) goto L13;
        return true;
    L13:
        return false;
    }

    private final void hideTestingProgressDialog() {
        AlertDialog r0 = this.testingProgressDialog;
        if (r0 != null) goto L8;
    L5:
        this.testingProgressDialog = null;
        return;
    L8:
        r0.dismiss();     // Catch: Exception -> L7
        goto L5
    }

    private final void initializeState() {
        Service r0 = this.currentService;
        if (r0 != null) goto L5;
        r0 = AiConfig.DEFAULT_SERVICE;
    L5:
        this.initialUrl = r0.getUrl();
        this.initialModel = r0.getModel();
        this.initialKey = r0.getKey();
        boolean r02 = r0.isReasoningEnabled();
        this.initialReasoningEnabled = r02;
        this.reasoningEnabled = r02;
        EditTextCell r03 = this.urlCell;
        EditTextCell r1 = null;
        if (r03 != null) goto L8;
        r03 = null;
    L8:
        r03.setText(safeString(this.initialUrl));
        EditTextCell r04 = this.modelCell;
        if (r04 != null) goto L11;
        r04 = null;
    L11:
        r04.setText(safeString(this.initialModel));
        EditTextCell r05 = this.keyCell;
        if (r05 == null) goto L15;
        r1 = r05;
    L15:
        r1.setText(safeString(this.initialKey));
        boolean r06 = false;
        this.hasChanges = false;
        int r12 = findPresetIndex(this.initialUrl, this.initialModel);
        this.selectedPresetIndex = r12;
        if (r12 != getCustomPresetIndex()) goto L18;
        r06 = true;
    L18:
        this.forceCustomPreset = r06;
    }

    private final boolean isLikelyApiKey(String r2) {
        if (r2.length() > 0) goto L5;
        return false;
    L5:
        if (r2.length() >= 20) goto L7;
        return false;
    L7:
        if (r2.length() <= 512) goto L9;
        return false;
    L9:
        if (new Regex("[A-Za-z0-9_.\\-]+").matches(r2) == false) goto L16;
        return true;
    L16:
        return false;
    }

    private final boolean isLikelyModel(String r2) {
        if (r2.length() > 0) goto L5;
        return false;
    L5:
        if (r2.length() <= 128) goto L7;
        return false;
    L7:
        if (new Regex(".*\\s+.*").matches(r2) == true) goto L13;
        return true;
    L13:
        return false;
    }

    private final boolean isLikelyService(String r1, String r2, String r3) {
        if (isLikelyServiceUrl(r1) == true) goto L5;
        return false;
    L5:
        if (isLikelyModel(r2) == true) goto L7;
        return false;
    L7:
        if (isLikelyApiKey(r3) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private final boolean isLikelyServiceUrl(String r4) {
        if (r4.length() > 0) goto L5;
    L10:
        return false;
    L5:
        if (StringsKt.startsWith$default(r4, "https://", false, 2, null) == false) goto L7;
        return true;
    L7:
        if (StringsKt.startsWith$default(r4, "http://", false, 2, null) == false) goto L10;
        return true;
    }

    private final boolean isValidServiceUrl(String r4) {
        if (r4.length() != 0) goto L5;
        return false;
    L5:
        Uri r3 = Uri.parse(r4);
        String r42 = r3.getScheme();
        if (StringsKt.equals("http", r42, true) == false) goto L8;
    L9:
        String r32 = r3.getHost();
        if (r32 != null) goto L12;
    L15:
        return false;
    L12:
        if (r32.length() == 0) goto L15;
        return true;
    L8:
        if (StringsKt.equals("https", r42, true) == false) goto L15;
        goto L9
    }

    private final ParsedServiceInput parseServiceInput(String r11) {
        if (r11 != null) goto L5;
    L39:
        return null;
    L5:
        if (r11.length() == 0) goto L39;
        String r112 = StringsKt.trim(r11).toString();
        if (r112.length() != 0) goto L10;
        return null;
    L10:
        int r2 = 0;
        List<String> r1 = new Regex("\\r?\\n").split(r112, 0);
        ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        Iterator<T> r12 = r1.iterator();
    L12:
        if (r12.hasNext() == false) goto L14;
        r3.add(StringsKt.trim((String) r12.next()).toString());
        goto L12
    L14:
        ArrayList r13 = new ArrayList();
        int r4 = r3.size();
        int r5 = 0;
    L15:
        if (r5 >= r4) goto L20;
        Object r6 = r3.get(r5);
        r5 = r5 + 1;
        if (((String) r6).length() <= 0) goto L15;
        r13.add(r6);
        goto L15
    L20:
        if (r13.size() >= 3) goto L22;
    L25:
        int r32 = r13.size() - 3;
        if (r32 < 0) goto L34;
    L27:
        int r62 = r2 + 1;
        int r8 = r2 + 2;
        if (isLikelyService((String) r13.get(r2), (String) r13.get(r62), (String) r13.get(r8)) == true) goto L30;
        if (r2 == r32) goto L34;
        r2 = r62;
        goto L27
    L30:
        return new ParsedServiceInput((String) r13.get(r2), (String) r13.get(r62), (String) r13.get(r8));
    L34:
        if (r13.size() != 1) goto L39;
        if (isLikelyApiKey(r112) == false) goto L39;
        return new ParsedServiceInput(null, null, r112);
    L22:
        if (isLikelyService((String) r13.get(0), (String) r13.get(1), (String) r13.get(2)) == false) goto L25;
        return new ParsedServiceInput((String) r13.get(0), (String) r13.get(1), (String) r13.get(2));
    }

    private final String readClipboardText(Context r3) {
        ClipboardManager r2 = this.clipboardManager;
        if (r2 == null) goto L15;
        ClipData r22 = r2.getPrimaryClip();
        if (r22 == null) goto L15;
        if (r22.getItemCount() > 0) goto L17;
        return null;
    L17:
        CharSequence r23 = r22.getItemAt(0).coerceToText(r3);     // Catch: Exception -> L16
        if (r23 == null) goto L15;
        return r23.toString();
    L15:
        return null;
    }

    private final String safeString(String r1) {
        if (r1 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r1;
    }

    private final void saveConfig() {
        if (this.isTesting == false) goto L5;
        return;
    L5:
        if (validateFields() == false) goto L39;
        EditTextCell r1 = this.urlCell;
        EditTextCell r2 = null;
        if (r1 != null) goto L10;
        r1 = null;
    L10:
        String r12 = getFieldText(r1);
        EditTextCell r3 = this.modelCell;
        if (r3 != null) goto L13;
        r3 = null;
    L13:
        String r32 = getFieldText(r3);
        EditTextCell r4 = this.keyCell;
        if (r4 == null) goto L17;
        r2 = r4;
    L17:
        Service r0 = new Service(r12, r32, getFieldText(r2), this.reasoningEnabled);
        Service r13 = findExistingService(r0);
        Service r22 = this.currentService;
        if (r22 != null) goto L20;
    L22:
        boolean r23 = false;
    L23:
        if (r13 != null) goto L25;
    L32:
        if (r13 == null) goto L36;
        if (r23 == true) goto L36;
        applySelection(r13);
        return;
    L36:
        testAndSave(r0);
        return;
    L25:
        if (Intrinsics.areEqual(r13, this.currentService) == true) goto L32;
        if (this.currentService == null) goto L30;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.ServiceDuplicate)).show();
        return;
    L30:
        useExistingService(r13);
        return;
    L20:
        if (r22.isReasoningEnabled() == this.reasoningEnabled) goto L22;
        r23 = true;
        goto L23
    }

    private final void setTesting(boolean r1) {
        this.isTesting = r1;
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L6;
        r0.update(true);
        return;
    }

    private final void showFieldError(EditTextCell r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r2, r0);
        r2.editText.requestFocus();
        AndroidUtilities.showKeyboard(r2.editText);
    }

    private final void showTestingProgressDialog() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog r0 = new AlertDialog(getParentActivity(), 3);
        r0.setOnCancelListener(new EditServiceActivity$$ExternalSyntheticLambda5(this));
        this.testingProgressDialog = r0;
        showDialog(r0);
    }

    private final void stopTestingRequest() {
        if (this.testingClient == null) goto L13;
        String r0 = this.testingRequestId;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.length() == 0) goto L16;
        Client r02 = this.testingClient;
        if (r02 == null) goto L15;
        r02.stopRequest(this.testingRequestId);
        return;
    L15:
        return;
    L16:
        return;
    }

    private final void testAndSave(Service r3) {
        Client.Builder r0 = new Client.Builder();
        r0.serviceOverride(r3);
        r0.roleOverride(Suggestions.ASSISTANT.getRole());
        this.testingClient = r0.build();
        showTestingProgressDialog();
        setTesting(true);
        Client r02 = this.testingClient;
        if (r02 == null) goto L5;
        String r32 = r02.getResponse("Say 'hi'.", new AnonymousClass2(this, r3));
    L6:
        this.testingRequestId = r32;
        return;
    L5:
        r32 = null;
        goto L6
    }

    private final void updateClipboardState() {
        updateClipboardState(true);
    }

    private final void updateClipboardState(Context r10, boolean r11) {
        if (this.clipboardManager == null) goto L49;
        if (r10 != null) goto L6;
        return;
    L6:
        if (hasFields() == false) goto L55;
        ParsedServiceInput r0 = this.pasteInput;
        boolean r1 = false;
        if (r0 == null) goto L11;
        boolean r3 = true;
    L12:
        if (r0 != null) goto L14;
    L16:
        boolean r02 = false;
    L17:
        String r102 = readClipboardText(r10);
        ParsedServiceInput r4 = parseServiceInput(r102);
        if (r4 == null) goto L31;
        EditTextCell r6 = this.urlCell;
        if (r6 != null) goto L22;
        r6 = null;
    L22:
        String r62 = getFieldText(r6);
        EditTextCell r7 = this.modelCell;
        if (r7 != null) goto L25;
        r7 = null;
    L25:
        String r72 = getFieldText(r7);
        EditTextCell r8 = this.keyCell;
        if (r8 != null) goto L29;
        r8 = null;
    L29:
        if (r4.differsFrom(r62, r72, getFieldText(r8)) == false) goto L31;
        this.pasteInput = r4;
        this.pasteString = r102;
    L32:
        ParsedServiceInput r103 = this.pasteInput;
        if (r103 == null) goto L35;
        boolean r42 = true;
    L36:
        if (r103 != null) goto L38;
    L40:
        if (r11 == true) goto L42;
        return;
    L42:
        if (this.fragmentView == null) goto L52;
        if (r3 != r42) goto L45;
        if (r02 != r1) goto L45;
        return;
    L45:
        UniversalAdapter r9 = getListView$TMessagesProj().adapter;
        if (r9 == null) goto L54;
        r9.update(true);
        return;
    L54:
        return;
    L52:
        return;
    L38:
        if (r103.hasServiceFields() != true) goto L40;
        r1 = true;
        goto L40
    L35:
        r42 = false;
    L31:
        this.pasteInput = null;
        this.pasteString = null;
        goto L32
    L14:
        if (r0.hasServiceFields() != true) goto L16;
        r02 = true;
        goto L17
    L11:
        r3 = false;
        goto L12
    L55:
        return;
    }

    private final void updateClipboardState(boolean r2) {
        View r0 = this.fragmentView;
        if (r0 == null) goto L6;
        Context r02 = r0.getContext();
        if (r02 == null) goto L6;
    L7:
        updateClipboardState(r02, r2);
        return;
    L6:
        r02 = getContext();
        goto L7
    }

    private final void updateFormState() {
        if (this.updatingFields == false) goto L5;
        return;
    L5:
        if (hasFields() == false) goto L70;
        boolean r0 = this.hasChanges;
        int r1 = this.selectedPresetIndex;
        ParsedServiceInput r2 = this.pasteInput;
        boolean r3 = false;
        if (r2 == null) goto L10;
        boolean r5 = true;
    L11:
        if (r2 != null) goto L13;
    L15:
        boolean r22 = false;
    L16:
        EditTextCell r6 = this.urlCell;
        EditTextCell r7 = null;
        if (r6 != null) goto L20;
        r6 = null;
    L20:
        if (TextUtils.equals(getFieldText(r6), safeString(this.initialUrl)) == false) goto L35;
        EditTextCell r62 = this.modelCell;
        if (r62 != null) goto L25;
        r62 = null;
    L25:
        if (TextUtils.equals(getFieldText(r62), safeString(this.initialModel)) == false) goto L35;
        EditTextCell r63 = this.keyCell;
        if (r63 != null) goto L30;
        r63 = null;
    L30:
        if (TextUtils.equals(getFieldText(r63), safeString(this.initialKey)) == false) goto L35;
        if (this.reasoningEnabled != this.initialReasoningEnabled) goto L35;
        boolean r64 = false;
    L36:
        this.hasChanges = r64;
        EditTextCell r65 = this.urlCell;
        if (r65 != null) goto L39;
        r65 = null;
    L39:
        String r66 = getFieldText(r65);
        EditTextCell r8 = this.modelCell;
        if (r8 == null) goto L43;
        r7 = r8;
    L43:
        int r67 = findPresetIndex(r66, getFieldText(r7));
        if (this.forceCustomPreset == false) goto L46;
        r67 = getCustomPresetIndex();
    L46:
        this.selectedPresetIndex = r67;
        updateClipboardState(false);
        ParsedServiceInput r68 = this.pasteInput;
        if (r68 == null) goto L49;
        boolean r72 = true;
    L50:
        if (r68 == null) goto L55;
        if (r68.hasServiceFields() != true) goto L55;
        r3 = true;
    L55:
        if (this.fragmentView != null) goto L57;
        return;
    L57:
        if (getListView$TMessagesProj().adapter != null) goto L59;
        return;
    L59:
        if (r0 == this.hasChanges) goto L61;
    L64:
        getListView$TMessagesProj().adapter.update(true);
        return;
    L61:
        if (r1 != this.selectedPresetIndex) goto L64;
        if (r5 != r72) goto L64;
        if (r22 != r3) goto L64;
        return;
    L49:
        r72 = false;
    L35:
        r64 = true;
        goto L36
    L13:
        if (r2.hasServiceFields() != true) goto L15;
        r22 = true;
        goto L16
    L10:
        r5 = false;
        goto L11
    }

    private final void useExistingService(Service r3) {
        boolean r0 = r3.isReasoningEnabled();
        boolean r1 = this.reasoningEnabled;
        if (r0 == r1) goto L5;
        r3.setReasoningEnabled(r1);
        AiController.getInstance().saveServices();
    L5:
        applySelection(r3);
        BulletinFactory.global().createSimpleBulletin(R.raw.chats_infotip, LocaleController.getString(R.string.ServiceAlreadyExists), LocaleController.getString(R.string.ServiceAlreadyExistsInfo)).show();
    }

    private final boolean validateFields() {
        EditTextCell r0 = this.urlCell;
        EditTextCell r1 = null;
        if (r0 != null) goto L6;
        r0 = null;
    L6:
        if (isValidServiceUrl(getFieldText(r0)) == true) goto L13;
        EditTextCell r02 = this.urlCell;
        if (r02 == null) goto L11;
        r1 = r02;
    L11:
        showFieldError(r1);
        return false;
    L13:
        EditTextCell r03 = this.modelCell;
        if (r03 != null) goto L17;
        r03 = null;
    L17:
        if (getFieldText(r03).length() != 0) goto L24;
        EditTextCell r04 = this.modelCell;
        if (r04 == null) goto L22;
        r1 = r04;
    L22:
        showFieldError(r1);
        return false;
    L24:
        EditTextCell r05 = this.keyCell;
        if (r05 != null) goto L28;
        r05 = null;
    L28:
        if (getFieldText(r05).length() != 0) goto L35;
        EditTextCell r06 = this.keyCell;
        if (r06 == null) goto L33;
        r1 = r06;
    L33:
        showFieldError(r1);
        return false;
    L35:
        return true;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r3) {
        Object r0 = r3.getSystemService("clipboard");
        if ((r0 instanceof ClipboardManager) == false) goto L5;
        ClipboardManager r02 = (ClipboardManager) r0;
    L6:
        this.clipboardManager = r02;
        createFields(r3);
        initializeState();
        updateClipboardState(r3, false);
        View r32 = super.createView(r3);
        this.actionBar.setAllowOverlayTitle(true);
        return r32;
    L5:
        r02 = null;
        goto L6
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        if (SettingsRegistry.markAsNewFeature("AI-Service-Reasoning") == false) goto L6;
        CharSequence r1 = LocaleUtils.applyNewSpan(LocaleController.getString(R.string.AIReasoning));
    L5:
        CharSequence r2 = r1;
        r17.add(UItem.asHeader(LocaleController.getString(R.string.ServiceProvider)));
        Iterator<Integer> r12 = ArraysKt.getIndices(SERVICE_PRESETS).iterator();
    L8:
        boolean r122 = false;
        boolean r15 = true;
        if (r12.hasNext() == false) goto L26;
        int r3 = ((IntIterator) r12).nextInt();
        if (r3 == 0) goto L21;
        if (r3 != 1) goto L14;
        int r4 = ServiceItem.PRESET_OPENAI.getId();
    L22:
        UItem r42 = UItem.asRadio(r4, getPresetTitle(r3));
        if (r3 != this.selectedPresetIndex) goto L25;
        r122 = true;
    L25:
        r17.add(r42.setChecked(r122).setEnabled(!this.isTesting));
        goto L8
    L14:
        if (r3 != 2) goto L16;
        r4 = ServiceItem.PRESET_OPENROUTER.getId();
        goto L22
    L16:
        if (r3 == 3) goto L18;
        r4 = ServiceItem.PRESET_CUSTOM.getId();
        goto L22
    L18:
        r4 = ServiceItem.PRESET_PERPLEXITY.getId();
        goto L22
    L21:
        r4 = ServiceItem.PRESET_GEMINI.getId();
        goto L22
    L26:
        r17.add(UItem.asShadow(LocaleController.getString(R.string.ServicesInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.ServiceInfo)));
        EditTextCell r13 = this.urlCell;
        EditTextCell r32 = null;
        if (r13 != null) goto L29;
        r13 = null;
    L29:
        r17.add(UItem.asCustom(r13));
        EditTextCell r14 = this.modelCell;
        if (r14 != null) goto L32;
        r14 = null;
    L32:
        r17.add(UItem.asCustom(r14));
        EditTextCell r16 = this.keyCell;
        if (r16 == null) goto L36;
        r32 = r16;
    L36:
        r17.add(UItem.asCustom(r32));
        UItem r19 = BasePreferencesActivity.uCheck$TMessagesProj$default(this, ServiceItem.REASONING, r2, this.reasoningEnabled, LocaleController.getString(R.string.AIReasoningInfo), 0, false, null, false, 0, 464, null);
        r19.setEnabled(!this.isTesting);
        r17.add(r19);
        r17.add(UItem.asShadow());
        ParsedServiceInput r110 = this.pasteInput;
        if (r110 == null) goto L44;
        ServiceItem r111 = ServiceItem.PASTE;
        if (r110.hasServiceFields() == false) goto L41;
        int r22 = R.string.ServicePasteService;
    L42:
        UItem r112 = BasePreferencesActivity.uButton$TMessagesProj$default(this, r111, LocaleController.getString(r22), R.drawable.msg_copy, null, null, null, false, null, false, true, null, 1464, null);
        r112.setEnabled(!this.isTesting);
        r17.add(r112);
        r122 = true;
        goto L44
    L41:
        r22 = R.string.ServicePasteKey;
    L44:
        if (this.hasChanges == false) goto L47;
        UItem r113 = BasePreferencesActivity.uButton$TMessagesProj$default(this, ServiceItem.SAVE, LocaleController.getString(R.string.ServiceTestAndSave), R.drawable.ic_ab_done, null, null, null, false, null, false, true, null, 1464, null);
        r113.setEnabled(!this.isTesting);
        r17.add(r113);
        r122 = true;
    L47:
        if (this.currentService == null) goto L49;
        UItem r114 = BasePreferencesActivity.uButton$TMessagesProj$default(this, ServiceItem.DELETE, LocaleController.getString(R.string.Delete), R.drawable.msg_delete, null, null, null, false, null, true, false, null, 1720, null);
        r114.setEnabled(!this.isTesting);
        r17.add(r114);
    L50:
        ExtensionsKt.addIf(r17, r15, UItem.asShadow());
        return;
    L49:
        r15 = r122;
        goto L50
    L6:
        r1 = LocaleController.getString(R.string.AIReasoning);
        goto L5
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        if (this.currentService == null) goto L5;
        int r0 = R.string.EditService;
    L7:
        return LocaleController.getString(r0);
    L5:
        r0 = R.string.NewService;
        goto L7
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (this.isTesting == true) goto L35;
        int r22 = r1.id;
        if (r22 > 0) goto L7;
        return;
    L7:
        if (r22 > ServiceItem.getEntries().size()) goto L38;
        ServiceItem r23 = ServiceItem.getEntries().get(r1.id - 1);
        switch(WhenMappings.$EnumSwitchMapping$0[r23.ordinal()]) {
            case 1: goto L33;
            case 2: goto L31;
            case 3: goto L29;
            case 4: goto L27;
            case 5: goto L25;
            case 6: goto L19;
            case 7: goto L17;
            case 8: goto L15;
            case 9: goto L13;
            default: goto L11;
        };
    L11:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new EditServiceActivity$$ExternalSyntheticLambda6(this));
        updateFormState();
        return;
    L15:
        confirmDeleteService();
        return;
    L17:
        saveConfig();
        return;
    L19:
        ParsedServiceInput r12 = this.pasteInput;
        if (r12 != null) goto L22;
        r12 = parseServiceInput(this.pasteString);
    L22:
        if (r12 == null) goto L37;
        applyParsedServiceInput(r12);
        return;
    L37:
        return;
    L25:
        applyPreset(4);
        return;
    L27:
        applyPreset(3);
        return;
    L29:
        applyPreset(2);
        return;
    L31:
        applyPreset(1);
        return;
    L33:
        applyPreset(0);
        return;
    L38:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        stopTestingRequest();
        hideTestingProgressDialog();
        this.testingRequestId = null;
        this.testingClient = null;
        super.onFragmentDestroy();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r2, int r3, int r4, int r5) {
        super.onInsets(r2, r3, r4, r5 + this.keyboardInset);
        if (this.keyboardInset <= 0) goto L6;
        getListView$TMessagesProj().post(new EditServiceActivity$$ExternalSyntheticLambda3(this));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public WindowInsetsCompat onInsetsInternal(View r3, WindowInsetsCompat r4) {
        Insets r0 = r4.getInsets(WindowInsetsCompat.Type.navigationBars() | WindowInsetsCompat.Type.statusBars());
        this.keyboardInset = Math.max(0, r4.getInsets(WindowInsetsCompat.Type.ime()).bottom - r0.bottom);
        return super.onInsetsInternal(r3, r4);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        ClipboardManager r0 = this.clipboardManager;
        if (r0 == null) goto L5;
        r0.removePrimaryClipChangedListener(this.clipChangedListener);
    L5:
        super.onPause();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ClipboardManager r0 = this.clipboardManager;
        if (r0 == null) goto L5;
        r0.removePrimaryClipChangedListener(this.clipChangedListener);
        r0.addPrimaryClipChangedListener(this.clipChangedListener);
    L5:
        updateClipboardState();
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == true) goto L16;
        EditTextCell r02 = this.keyCell;
        EditTextCell r1 = null;
        if (r02 != null) goto L10;
        r02 = null;
    L10:
        r02.editText.requestFocus();
        EditTextCell r3 = this.keyCell;
        if (r3 == null) goto L14;
        r1 = r3;
    L14:
        AndroidUtilities.showKeyboard(r1.editText);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == true) goto L4;
        return;
    L4:
        EditTextCell r12 = this.keyCell;
        EditTextCell r22 = null;
        if (r12 != null) goto L7;
        r12 = null;
    L7:
        r12.editText.requestFocus();
        EditTextCell r0 = this.keyCell;
        if (r0 == null) goto L11;
        r22 = r0;
    L11:
        AndroidUtilities.showKeyboard(r22.editText);
    }
}
