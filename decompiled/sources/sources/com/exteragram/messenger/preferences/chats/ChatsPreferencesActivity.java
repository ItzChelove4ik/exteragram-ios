package com.exteragram.messenger.preferences.chats;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.CameraType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.StickerTimeMode;
import com.exteragram.messenger.VideoMessagesCamera;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.ui.activities.AiPreferencesActivity;
import com.exteragram.messenger.camera.CameraXSession;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.SwitchGroup;
import com.exteragram.messenger.preferences.chats.components.DoubleTapCell;
import com.exteragram.messenger.preferences.chats.components.MessagesPreviewCell;
import com.exteragram.messenger.preferences.chats.components.SliderPreviewCell;
import com.exteragram.messenger.preferences.chats.components.StickerShapeCell;
import com.exteragram.messenger.preferences.components.AltSeekbar;
import com.exteragram.messenger.speech.VoiceRecognitionController;
import com.exteragram.messenger.speech.ui.RecognitionModelDialogs;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.chats.DoubleTapUtils;
import com.exteragram.messenger.utils.chats.SwipeAction;
import com.exteragram.messenger.utils.ui.PopupUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.recorder.DualCameraView;
import org.telegram.ui.ThemeActivity;

@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 E2\u00020\u0001:\u0002EFB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0014J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0006H\u0002J\u001e\u0010+\u001a\u00020#2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u0010/\u001a\u000200H\u0014J0\u00101\u001a\u00020#2\u0006\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020%2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J\b\u00109\u001a\u00020#H\u0002J\u0012\u0010:\u001a\u0004\u0018\u00010\u00142\u0006\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020<H\u0002J\u0010\u0010>\u001a\u00020#2\u0006\u00102\u001a\u00020.H\u0002J\b\u0010D\u001a\u00020#H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006G"}, d2 = {"Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "stickerTimeModes", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/CharSequence;", "doubleTapActions", "doubleTapOutActions", "bottomButtonOptions", "videoMessagesCameraOptions", "doubleTapSeekDurationOptions", "cameraTypeOptions", "recognitionLanguageOptions", "resetItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "stickerSizeCell", "Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;", "doubleTapCell", "Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;", "stickerShapeCell", "Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;", "messagesPreviewCell", "Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;", "replyElements", "Lcom/exteragram/messenger/preferences/SwitchGroup;", "hideReactions", "widePosts", "aiFeatures", "quickTransitions", "messageMenu", "cameraSettings", "pauseOnMinimize", "initializeOptionStrings", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "swipeActionsValue", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "updateReplySettings", "handleDoubleTapActionButtonClick", "outOwner", _UrlKt.FRAGMENT_ENCODE_SET, "isSeamlessSwitchingAvailable", "handleSpeechRecognitionLanguageClick", "downloadedRecognitionModels", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;", "getDownloadedRecognitionModels", "()Ljava/util/List;", "handleDeleteRecognitionModelClick", "Companion", "ChatsItem", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nChatsPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChatsPreferencesActivity.kt\ncom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,568:1\n1586#2:569\n1661#2,3:570\n2792#2,3:599\n296#2,2:602\n37#3,2:573\n41#4,12:575\n41#4,12:587\n*S KotlinDebug\n*F\n+ 1 ChatsPreferencesActivity.kt\ncom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity\n*L\n259#1:569\n259#1:570,3\n546#1:599,3\n549#1:602,2\n261#1:573,2\n401#1:575,12\n212#1:587,12\n*E\n"})
/* loaded from: classes4.dex */
public final class ChatsPreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private static final List<String> languageCodes = null;
    private final SwitchGroup aiFeatures;
    private CharSequence[] bottomButtonOptions;
    private final SwitchGroup cameraSettings;
    private CharSequence[] cameraTypeOptions;
    private CharSequence[] doubleTapActions;
    private DoubleTapCell doubleTapCell;
    private CharSequence[] doubleTapOutActions;
    private CharSequence[] doubleTapSeekDurationOptions;
    private final SwitchGroup hideReactions;
    private final SwitchGroup messageMenu;
    private MessagesPreviewCell messagesPreviewCell;
    private final SwitchGroup pauseOnMinimize;
    private final SwitchGroup quickTransitions;
    private CharSequence[] recognitionLanguageOptions;
    private final SwitchGroup replyElements;
    private ActionBarMenuItem resetItem;
    private StickerShapeCell stickerShapeCell;
    private SliderPreviewCell stickerSizeCell;
    private CharSequence[] stickerTimeModes;
    private CharSequence[] videoMessagesCameraOptions;
    private final SwitchGroup widePosts;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\bO\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010P\u001a\u00020Q¢\u0006\b\n\u0000\u001a\u0004\bR\u0010Sj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bO¨\u0006T"}, d2 = {"Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "STICKER_SIZE", "STICKER_TIME", "REPLY_ELEMENTS", "REPLY_COLORS", "REPLY_EMOJI", "REPLY_BACKGROUND", "STICKER_SHAPE", "AI", "CHAT_SETTINGS", "UNLIMITED_RECENT_STICKERS", "HIDE_REACTIONS", "DOUBLE_TAP", "DOUBLE_TAP_ACTION", "DOUBLE_TAP_ACTION_OUT_OWNER", "SWIPE_ACTIONS", "BOTTOM_BUTTON", "WIDE_POSTS", "WIDE_POSTS_FEED", "WIDE_POSTS_CHANNELS", "AI_FEATURES", "AI_FEATURES_EDITOR", "AI_FEATURES_SUMMARIES", "AI_FEATURES_INSTANT_VIEW_SUMMARIES", "ADMIN_SHORTCUTS", "QUICK_TRANSITIONS", "QUICK_TRANSITION_FOR_CHANNELS", "QUICK_TRANSITION_FOR_TOPICS", "DISABLE_GREETING_STICKER", "HIDE_KEYBOARD_ON_SCROLL", "ADD_COMMA_AFTER_MENTION", "INLINE_MATH_RESULT", "HIDE_SEND_AS_PEER", "MESSAGES_PREVIEW", "REMOVE_MESSAGE_TAIL", "REPLACE_EDITED_WITH_ICON", "SHOW_ONLINE_STATUS", "HIDE_SHARE_BUTTON", "SHOW_RESULTS_BEFORE_VOTING", "MESSAGE_MENU", "COPY_PHOTO", "SAVE", "REPEAT", "CLEAR", "HISTORY", "REPORT", "GENERATE", "DETAILS", "GROUP_MESSAGE_MENU", "GROUPS", "CHANNELS", "PRIVATE_CHATS", "SPEECH_RECOGNITION_LANGUAGE", "POST_PROCESSING_WITH_AI", "DELETE_RECOGNITION_MODEL", "CAMERA_TYPE", "CAMERA_SETTINGS", "DUAL_CAMERA", "EXTENDED_FRAMES_PER_SECOND", "CAMERA_STABILIZATION", "CAMERA_MIRROR_MODE", "VIDEO_MESSAGES_CAMERA", "REMEMBER_LAST_USED_CAMERA", "START_WITH_WIDE_ANGLE_CAMERA", "ZOOM_SLIDER", "STATIC_ZOOM", "ALWAYS_SEND_IN_HD", "HIDE_CAMERA_TILE", "DOUBLE_TAP_SEEK_DURATION", "PREFER_ORIGINAL_QUALITY", "SWIPE_TO_PIP", "UNMUTE_WITH_VOLUME_BUTTONS", "PAUSE_ON_MINIMIZE", "PAUSE_ON_MINIMIZE_VIDEO", "PAUSE_ON_MINIMIZE_VOICE", "PAUSE_ON_MINIMIZE_ROUND", "SHOW_FORWARDS_COUNT", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum ChatsItem extends Enum<ChatsItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ChatsItem[] $VALUES = null;
        public static final ChatsItem ADD_COMMA_AFTER_MENTION = null;
        public static final ChatsItem ADMIN_SHORTCUTS = null;
        public static final ChatsItem AI = null;
        public static final ChatsItem AI_FEATURES = null;
        public static final ChatsItem AI_FEATURES_EDITOR = null;
        public static final ChatsItem AI_FEATURES_INSTANT_VIEW_SUMMARIES = null;
        public static final ChatsItem AI_FEATURES_SUMMARIES = null;
        public static final ChatsItem ALWAYS_SEND_IN_HD = null;
        public static final ChatsItem BOTTOM_BUTTON = null;
        public static final ChatsItem CAMERA_MIRROR_MODE = null;
        public static final ChatsItem CAMERA_SETTINGS = null;
        public static final ChatsItem CAMERA_STABILIZATION = null;
        public static final ChatsItem CAMERA_TYPE = null;
        public static final ChatsItem CHANNELS = null;
        public static final ChatsItem CHAT_SETTINGS = null;
        public static final ChatsItem CLEAR = null;
        public static final ChatsItem COPY_PHOTO = null;
        public static final ChatsItem DELETE_RECOGNITION_MODEL = null;
        public static final ChatsItem DETAILS = null;
        public static final ChatsItem DISABLE_GREETING_STICKER = null;
        public static final ChatsItem DOUBLE_TAP = null;
        public static final ChatsItem DOUBLE_TAP_ACTION = null;
        public static final ChatsItem DOUBLE_TAP_ACTION_OUT_OWNER = null;
        public static final ChatsItem DOUBLE_TAP_SEEK_DURATION = null;
        public static final ChatsItem DUAL_CAMERA = null;
        public static final ChatsItem EXTENDED_FRAMES_PER_SECOND = null;
        public static final ChatsItem GENERATE = null;
        public static final ChatsItem GROUPS = null;
        public static final ChatsItem GROUP_MESSAGE_MENU = null;
        public static final ChatsItem HIDE_CAMERA_TILE = null;
        public static final ChatsItem HIDE_KEYBOARD_ON_SCROLL = null;
        public static final ChatsItem HIDE_REACTIONS = null;
        public static final ChatsItem HIDE_SEND_AS_PEER = null;
        public static final ChatsItem HIDE_SHARE_BUTTON = null;
        public static final ChatsItem HISTORY = null;
        public static final ChatsItem INLINE_MATH_RESULT = null;
        public static final ChatsItem MESSAGES_PREVIEW = null;
        public static final ChatsItem MESSAGE_MENU = null;
        public static final ChatsItem PAUSE_ON_MINIMIZE = null;
        public static final ChatsItem PAUSE_ON_MINIMIZE_ROUND = null;
        public static final ChatsItem PAUSE_ON_MINIMIZE_VIDEO = null;
        public static final ChatsItem PAUSE_ON_MINIMIZE_VOICE = null;
        public static final ChatsItem POST_PROCESSING_WITH_AI = null;
        public static final ChatsItem PREFER_ORIGINAL_QUALITY = null;
        public static final ChatsItem PRIVATE_CHATS = null;
        public static final ChatsItem QUICK_TRANSITIONS = null;
        public static final ChatsItem QUICK_TRANSITION_FOR_CHANNELS = null;
        public static final ChatsItem QUICK_TRANSITION_FOR_TOPICS = null;
        public static final ChatsItem REMEMBER_LAST_USED_CAMERA = null;
        public static final ChatsItem REMOVE_MESSAGE_TAIL = null;
        public static final ChatsItem REPEAT = null;
        public static final ChatsItem REPLACE_EDITED_WITH_ICON = null;
        public static final ChatsItem REPLY_BACKGROUND = null;
        public static final ChatsItem REPLY_COLORS = null;
        public static final ChatsItem REPLY_ELEMENTS = null;
        public static final ChatsItem REPLY_EMOJI = null;
        public static final ChatsItem REPORT = null;
        public static final ChatsItem SAVE = null;
        public static final ChatsItem SHOW_FORWARDS_COUNT = null;
        public static final ChatsItem SHOW_ONLINE_STATUS = null;
        public static final ChatsItem SHOW_RESULTS_BEFORE_VOTING = null;
        public static final ChatsItem SPEECH_RECOGNITION_LANGUAGE = null;
        public static final ChatsItem START_WITH_WIDE_ANGLE_CAMERA = null;
        public static final ChatsItem STATIC_ZOOM = null;
        public static final ChatsItem STICKER_SHAPE = null;
        public static final ChatsItem STICKER_SIZE = null;
        public static final ChatsItem STICKER_TIME = null;
        public static final ChatsItem SWIPE_ACTIONS = null;
        public static final ChatsItem SWIPE_TO_PIP = null;
        public static final ChatsItem UNLIMITED_RECENT_STICKERS = null;
        public static final ChatsItem UNMUTE_WITH_VOLUME_BUTTONS = null;
        public static final ChatsItem VIDEO_MESSAGES_CAMERA = null;
        public static final ChatsItem WIDE_POSTS = null;
        public static final ChatsItem WIDE_POSTS_CHANNELS = null;
        public static final ChatsItem WIDE_POSTS_FEED = null;
        public static final ChatsItem ZOOM_SLIDER = null;
        private final int id;

        private static final /* synthetic */ ChatsItem[] $values() {
            return new ChatsItem[]{STICKER_SIZE, STICKER_TIME, REPLY_ELEMENTS, REPLY_COLORS, REPLY_EMOJI, REPLY_BACKGROUND, STICKER_SHAPE, AI, CHAT_SETTINGS, UNLIMITED_RECENT_STICKERS, HIDE_REACTIONS, DOUBLE_TAP, DOUBLE_TAP_ACTION, DOUBLE_TAP_ACTION_OUT_OWNER, SWIPE_ACTIONS, BOTTOM_BUTTON, WIDE_POSTS, WIDE_POSTS_FEED, WIDE_POSTS_CHANNELS, AI_FEATURES, AI_FEATURES_EDITOR, AI_FEATURES_SUMMARIES, AI_FEATURES_INSTANT_VIEW_SUMMARIES, ADMIN_SHORTCUTS, QUICK_TRANSITIONS, QUICK_TRANSITION_FOR_CHANNELS, QUICK_TRANSITION_FOR_TOPICS, DISABLE_GREETING_STICKER, HIDE_KEYBOARD_ON_SCROLL, ADD_COMMA_AFTER_MENTION, INLINE_MATH_RESULT, HIDE_SEND_AS_PEER, MESSAGES_PREVIEW, REMOVE_MESSAGE_TAIL, REPLACE_EDITED_WITH_ICON, SHOW_ONLINE_STATUS, HIDE_SHARE_BUTTON, SHOW_RESULTS_BEFORE_VOTING, MESSAGE_MENU, COPY_PHOTO, SAVE, REPEAT, CLEAR, HISTORY, REPORT, GENERATE, DETAILS, GROUP_MESSAGE_MENU, GROUPS, CHANNELS, PRIVATE_CHATS, SPEECH_RECOGNITION_LANGUAGE, POST_PROCESSING_WITH_AI, DELETE_RECOGNITION_MODEL, CAMERA_TYPE, CAMERA_SETTINGS, DUAL_CAMERA, EXTENDED_FRAMES_PER_SECOND, CAMERA_STABILIZATION, CAMERA_MIRROR_MODE, VIDEO_MESSAGES_CAMERA, REMEMBER_LAST_USED_CAMERA, START_WITH_WIDE_ANGLE_CAMERA, ZOOM_SLIDER, STATIC_ZOOM, ALWAYS_SEND_IN_HD, HIDE_CAMERA_TILE, DOUBLE_TAP_SEEK_DURATION, PREFER_ORIGINAL_QUALITY, SWIPE_TO_PIP, UNMUTE_WITH_VOLUME_BUTTONS, PAUSE_ON_MINIMIZE, PAUSE_ON_MINIMIZE_VIDEO, PAUSE_ON_MINIMIZE_VOICE, PAUSE_ON_MINIMIZE_ROUND, SHOW_FORWARDS_COUNT};
        }

        static {
            STICKER_SIZE = new ChatsItem("STICKER_SIZE", 0);
            STICKER_TIME = new ChatsItem("STICKER_TIME", 1);
            REPLY_ELEMENTS = new ChatsItem("REPLY_ELEMENTS", 2);
            REPLY_COLORS = new ChatsItem("REPLY_COLORS", 3);
            REPLY_EMOJI = new ChatsItem("REPLY_EMOJI", 4);
            REPLY_BACKGROUND = new ChatsItem("REPLY_BACKGROUND", 5);
            STICKER_SHAPE = new ChatsItem("STICKER_SHAPE", 6);
            AI = new ChatsItem("AI", 7);
            CHAT_SETTINGS = new ChatsItem("CHAT_SETTINGS", 8);
            UNLIMITED_RECENT_STICKERS = new ChatsItem("UNLIMITED_RECENT_STICKERS", 9);
            HIDE_REACTIONS = new ChatsItem("HIDE_REACTIONS", 10);
            DOUBLE_TAP = new ChatsItem("DOUBLE_TAP", 11);
            DOUBLE_TAP_ACTION = new ChatsItem("DOUBLE_TAP_ACTION", 12);
            DOUBLE_TAP_ACTION_OUT_OWNER = new ChatsItem("DOUBLE_TAP_ACTION_OUT_OWNER", 13);
            SWIPE_ACTIONS = new ChatsItem("SWIPE_ACTIONS", 14);
            BOTTOM_BUTTON = new ChatsItem("BOTTOM_BUTTON", 15);
            WIDE_POSTS = new ChatsItem("WIDE_POSTS", 16);
            WIDE_POSTS_FEED = new ChatsItem("WIDE_POSTS_FEED", 17);
            WIDE_POSTS_CHANNELS = new ChatsItem("WIDE_POSTS_CHANNELS", 18);
            AI_FEATURES = new ChatsItem("AI_FEATURES", 19);
            AI_FEATURES_EDITOR = new ChatsItem("AI_FEATURES_EDITOR", 20);
            AI_FEATURES_SUMMARIES = new ChatsItem("AI_FEATURES_SUMMARIES", 21);
            AI_FEATURES_INSTANT_VIEW_SUMMARIES = new ChatsItem("AI_FEATURES_INSTANT_VIEW_SUMMARIES", 22);
            ADMIN_SHORTCUTS = new ChatsItem("ADMIN_SHORTCUTS", 23);
            QUICK_TRANSITIONS = new ChatsItem("QUICK_TRANSITIONS", 24);
            QUICK_TRANSITION_FOR_CHANNELS = new ChatsItem("QUICK_TRANSITION_FOR_CHANNELS", 25);
            QUICK_TRANSITION_FOR_TOPICS = new ChatsItem("QUICK_TRANSITION_FOR_TOPICS", 26);
            DISABLE_GREETING_STICKER = new ChatsItem("DISABLE_GREETING_STICKER", 27);
            HIDE_KEYBOARD_ON_SCROLL = new ChatsItem("HIDE_KEYBOARD_ON_SCROLL", 28);
            ADD_COMMA_AFTER_MENTION = new ChatsItem("ADD_COMMA_AFTER_MENTION", 29);
            INLINE_MATH_RESULT = new ChatsItem("INLINE_MATH_RESULT", 30);
            HIDE_SEND_AS_PEER = new ChatsItem("HIDE_SEND_AS_PEER", 31);
            MESSAGES_PREVIEW = new ChatsItem("MESSAGES_PREVIEW", 32);
            REMOVE_MESSAGE_TAIL = new ChatsItem("REMOVE_MESSAGE_TAIL", 33);
            REPLACE_EDITED_WITH_ICON = new ChatsItem("REPLACE_EDITED_WITH_ICON", 34);
            SHOW_ONLINE_STATUS = new ChatsItem("SHOW_ONLINE_STATUS", 35);
            HIDE_SHARE_BUTTON = new ChatsItem("HIDE_SHARE_BUTTON", 36);
            SHOW_RESULTS_BEFORE_VOTING = new ChatsItem("SHOW_RESULTS_BEFORE_VOTING", 37);
            MESSAGE_MENU = new ChatsItem("MESSAGE_MENU", 38);
            COPY_PHOTO = new ChatsItem("COPY_PHOTO", 39);
            SAVE = new ChatsItem("SAVE", 40);
            REPEAT = new ChatsItem("REPEAT", 41);
            CLEAR = new ChatsItem("CLEAR", 42);
            HISTORY = new ChatsItem("HISTORY", 43);
            REPORT = new ChatsItem("REPORT", 44);
            GENERATE = new ChatsItem("GENERATE", 45);
            DETAILS = new ChatsItem("DETAILS", 46);
            GROUP_MESSAGE_MENU = new ChatsItem("GROUP_MESSAGE_MENU", 47);
            GROUPS = new ChatsItem("GROUPS", 48);
            CHANNELS = new ChatsItem("CHANNELS", 49);
            PRIVATE_CHATS = new ChatsItem("PRIVATE_CHATS", 50);
            SPEECH_RECOGNITION_LANGUAGE = new ChatsItem("SPEECH_RECOGNITION_LANGUAGE", 51);
            POST_PROCESSING_WITH_AI = new ChatsItem("POST_PROCESSING_WITH_AI", 52);
            DELETE_RECOGNITION_MODEL = new ChatsItem("DELETE_RECOGNITION_MODEL", 53);
            CAMERA_TYPE = new ChatsItem("CAMERA_TYPE", 54);
            CAMERA_SETTINGS = new ChatsItem("CAMERA_SETTINGS", 55);
            DUAL_CAMERA = new ChatsItem("DUAL_CAMERA", 56);
            EXTENDED_FRAMES_PER_SECOND = new ChatsItem("EXTENDED_FRAMES_PER_SECOND", 57);
            CAMERA_STABILIZATION = new ChatsItem("CAMERA_STABILIZATION", 58);
            CAMERA_MIRROR_MODE = new ChatsItem("CAMERA_MIRROR_MODE", 59);
            VIDEO_MESSAGES_CAMERA = new ChatsItem("VIDEO_MESSAGES_CAMERA", 60);
            REMEMBER_LAST_USED_CAMERA = new ChatsItem("REMEMBER_LAST_USED_CAMERA", 61);
            START_WITH_WIDE_ANGLE_CAMERA = new ChatsItem("START_WITH_WIDE_ANGLE_CAMERA", 62);
            ZOOM_SLIDER = new ChatsItem("ZOOM_SLIDER", 63);
            STATIC_ZOOM = new ChatsItem("STATIC_ZOOM", 64);
            ALWAYS_SEND_IN_HD = new ChatsItem("ALWAYS_SEND_IN_HD", 65);
            HIDE_CAMERA_TILE = new ChatsItem("HIDE_CAMERA_TILE", 66);
            DOUBLE_TAP_SEEK_DURATION = new ChatsItem("DOUBLE_TAP_SEEK_DURATION", 67);
            PREFER_ORIGINAL_QUALITY = new ChatsItem("PREFER_ORIGINAL_QUALITY", 68);
            SWIPE_TO_PIP = new ChatsItem("SWIPE_TO_PIP", 69);
            UNMUTE_WITH_VOLUME_BUTTONS = new ChatsItem("UNMUTE_WITH_VOLUME_BUTTONS", 70);
            PAUSE_ON_MINIMIZE = new ChatsItem("PAUSE_ON_MINIMIZE", 71);
            PAUSE_ON_MINIMIZE_VIDEO = new ChatsItem("PAUSE_ON_MINIMIZE_VIDEO", 72);
            PAUSE_ON_MINIMIZE_VOICE = new ChatsItem("PAUSE_ON_MINIMIZE_VOICE", 73);
            PAUSE_ON_MINIMIZE_ROUND = new ChatsItem("PAUSE_ON_MINIMIZE_ROUND", 74);
            SHOW_FORWARDS_COUNT = new ChatsItem("SHOW_FORWARDS_COUNT", 75);
            ChatsItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ChatsItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<ChatsItem> getEntries() {
            return $ENTRIES;
        }

        public static ChatsItem valueOf(String r1) {
            return (ChatsItem) Enum.valueOf(ChatsItem.class, r1);
        }

        public static ChatsItem[] values() {
            return (ChatsItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SWIPE_ACTIONS_SHOWN", _UrlKt.FRAGMENT_ENCODE_SET, "languageCodes", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ChatsItem.values().length];
            r0[ChatsItem.STICKER_TIME.ordinal()] = 1;     // Catch: NoSuchFieldError -> L77
        L173:
            r0[ChatsItem.REPLY_ELEMENTS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L78
        L185:
            r0[ChatsItem.REPLY_COLORS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L79
        L195:
            r0[ChatsItem.REPLY_EMOJI.ordinal()] = 4;     // Catch: NoSuchFieldError -> L80
        L199:
            r0[ChatsItem.REPLY_BACKGROUND.ordinal()] = 5;     // Catch: NoSuchFieldError -> L81
        L209:
            r0[ChatsItem.AI.ordinal()] = 6;     // Catch: NoSuchFieldError -> L82
        L221:
            r0[ChatsItem.CHAT_SETTINGS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L83
        L241:
            r0[ChatsItem.UNLIMITED_RECENT_STICKERS.ordinal()] = 8;     // Catch: NoSuchFieldError -> L84
        L251:
            r0[ChatsItem.HIDE_REACTIONS.ordinal()] = 9;     // Catch: NoSuchFieldError -> L85
        L253:
            r0[ChatsItem.CHANNELS.ordinal()] = 10;     // Catch: NoSuchFieldError -> L86
        L263:
            r0[ChatsItem.GROUPS.ordinal()] = 11;     // Catch: NoSuchFieldError -> L87
        L281:
            r0[ChatsItem.PRIVATE_CHATS.ordinal()] = 12;     // Catch: NoSuchFieldError -> L88
        L291:
            r0[ChatsItem.DOUBLE_TAP_ACTION.ordinal()] = 13;     // Catch: NoSuchFieldError -> L89
        L149:
            r0[ChatsItem.DOUBLE_TAP_ACTION_OUT_OWNER.ordinal()] = 14;     // Catch: NoSuchFieldError -> L90
        L159:
            r0[ChatsItem.SWIPE_ACTIONS.ordinal()] = 15;     // Catch: NoSuchFieldError -> L91
        L181:
            r0[ChatsItem.BOTTOM_BUTTON.ordinal()] = 16;     // Catch: NoSuchFieldError -> L92
        L193:
            r0[ChatsItem.WIDE_POSTS.ordinal()] = 17;     // Catch: NoSuchFieldError -> L93
        L197:
            r0[ChatsItem.WIDE_POSTS_FEED.ordinal()] = 18;     // Catch: NoSuchFieldError -> L94
        L211:
            r0[ChatsItem.WIDE_POSTS_CHANNELS.ordinal()] = 19;     // Catch: NoSuchFieldError -> L95
        L231:
            r0[ChatsItem.AI_FEATURES.ordinal()] = 20;     // Catch: NoSuchFieldError -> L96
        L243:
            r0[ChatsItem.AI_FEATURES_EDITOR.ordinal()] = 21;     // Catch: NoSuchFieldError -> L97
        L245:
            r0[ChatsItem.AI_FEATURES_SUMMARIES.ordinal()] = 22;     // Catch: NoSuchFieldError -> L98
        L255:
            r0[ChatsItem.AI_FEATURES_INSTANT_VIEW_SUMMARIES.ordinal()] = 23;     // Catch: NoSuchFieldError -> L99
        L273:
            r0[ChatsItem.ADMIN_SHORTCUTS.ordinal()] = 24;     // Catch: NoSuchFieldError -> L100
        L283:
            r0[ChatsItem.QUICK_TRANSITIONS.ordinal()] = 25;     // Catch: NoSuchFieldError -> L101
        L285:
            r0[ChatsItem.QUICK_TRANSITION_FOR_CHANNELS.ordinal()] = 26;     // Catch: NoSuchFieldError -> L102
        L151:
            r0[ChatsItem.QUICK_TRANSITION_FOR_TOPICS.ordinal()] = 27;     // Catch: NoSuchFieldError -> L103
        L171:
            r0[ChatsItem.DISABLE_GREETING_STICKER.ordinal()] = 28;     // Catch: NoSuchFieldError -> L104
        L183:
            r0[ChatsItem.HIDE_KEYBOARD_ON_SCROLL.ordinal()] = 29;     // Catch: NoSuchFieldError -> L105
        L187:
            r0[ChatsItem.ADD_COMMA_AFTER_MENTION.ordinal()] = 30;     // Catch: NoSuchFieldError -> L106
        L201:
            r0[ChatsItem.INLINE_MATH_RESULT.ordinal()] = 31;     // Catch: NoSuchFieldError -> L107
        L223:
            r0[ChatsItem.HIDE_SEND_AS_PEER.ordinal()] = 32;     // Catch: NoSuchFieldError -> L108
        L233:
            r0[ChatsItem.REPLACE_EDITED_WITH_ICON.ordinal()] = 33;     // Catch: NoSuchFieldError -> L109
        L235:
            r0[ChatsItem.SHOW_ONLINE_STATUS.ordinal()] = 34;     // Catch: NoSuchFieldError -> L110
        L247:
            r0[ChatsItem.SHOW_FORWARDS_COUNT.ordinal()] = 35;     // Catch: NoSuchFieldError -> L111
        L265:
            r0[ChatsItem.REMOVE_MESSAGE_TAIL.ordinal()] = 36;     // Catch: NoSuchFieldError -> L112
        L275:
            r0[ChatsItem.SHOW_RESULTS_BEFORE_VOTING.ordinal()] = 37;     // Catch: NoSuchFieldError -> L113
        L277:
            r0[ChatsItem.HIDE_SHARE_BUTTON.ordinal()] = 38;     // Catch: NoSuchFieldError -> L114
        L287:
            r0[ChatsItem.MESSAGE_MENU.ordinal()] = 39;     // Catch: NoSuchFieldError -> L115
        L161:
            r0[ChatsItem.COPY_PHOTO.ordinal()] = 40;     // Catch: NoSuchFieldError -> L116
        L175:
            r0[ChatsItem.SAVE.ordinal()] = 41;     // Catch: NoSuchFieldError -> L117
        L177:
            r0[ChatsItem.REPEAT.ordinal()] = 42;     // Catch: NoSuchFieldError -> L118
        L189:
            r0[ChatsItem.CLEAR.ordinal()] = 43;     // Catch: NoSuchFieldError -> L119
        L213:
            r0[ChatsItem.HISTORY.ordinal()] = 44;     // Catch: NoSuchFieldError -> L120
        L225:
            r0[ChatsItem.REPORT.ordinal()] = 45;     // Catch: NoSuchFieldError -> L121
        L227:
            r0[ChatsItem.GENERATE.ordinal()] = 46;     // Catch: NoSuchFieldError -> L122
        L237:
            r0[ChatsItem.DETAILS.ordinal()] = 47;     // Catch: NoSuchFieldError -> L123
        L257:
            r0[ChatsItem.GROUP_MESSAGE_MENU.ordinal()] = 48;     // Catch: NoSuchFieldError -> L124
        L267:
            r0[ChatsItem.SPEECH_RECOGNITION_LANGUAGE.ordinal()] = 49;     // Catch: NoSuchFieldError -> L125
        L269:
            r0[ChatsItem.POST_PROCESSING_WITH_AI.ordinal()] = 50;     // Catch: NoSuchFieldError -> L126
        L279:
            r0[ChatsItem.DELETE_RECOGNITION_MODEL.ordinal()] = 51;     // Catch: NoSuchFieldError -> L127
        L153:
            r0[ChatsItem.CAMERA_TYPE.ordinal()] = 52;     // Catch: NoSuchFieldError -> L128
        L165:
            r0[ChatsItem.CAMERA_SETTINGS.ordinal()] = 53;     // Catch: NoSuchFieldError -> L129
        L167:
            r0[ChatsItem.DUAL_CAMERA.ordinal()] = 54;     // Catch: NoSuchFieldError -> L130
        L179:
            r0[ChatsItem.EXTENDED_FRAMES_PER_SECOND.ordinal()] = 55;     // Catch: NoSuchFieldError -> L131
        L203:
            r0[ChatsItem.CAMERA_STABILIZATION.ordinal()] = 56;     // Catch: NoSuchFieldError -> L132
        L215:
            r0[ChatsItem.CAMERA_MIRROR_MODE.ordinal()] = 57;     // Catch: NoSuchFieldError -> L133
        L217:
            r0[ChatsItem.START_WITH_WIDE_ANGLE_CAMERA.ordinal()] = 58;     // Catch: NoSuchFieldError -> L134
        L229:
            r0[ChatsItem.VIDEO_MESSAGES_CAMERA.ordinal()] = 59;     // Catch: NoSuchFieldError -> L135
        L249:
            r0[ChatsItem.REMEMBER_LAST_USED_CAMERA.ordinal()] = 60;     // Catch: NoSuchFieldError -> L136
        L259:
            r0[ChatsItem.ZOOM_SLIDER.ordinal()] = 61;     // Catch: NoSuchFieldError -> L137
        L261:
            r0[ChatsItem.STATIC_ZOOM.ordinal()] = 62;     // Catch: NoSuchFieldError -> L138
        L271:
            r0[ChatsItem.ALWAYS_SEND_IN_HD.ordinal()] = 63;     // Catch: NoSuchFieldError -> L139
        L289:
            r0[ChatsItem.HIDE_CAMERA_TILE.ordinal()] = 64;     // Catch: NoSuchFieldError -> L140
        L155:
            r0[ChatsItem.DOUBLE_TAP_SEEK_DURATION.ordinal()] = 65;     // Catch: NoSuchFieldError -> L141
        L157:
            r0[ChatsItem.PREFER_ORIGINAL_QUALITY.ordinal()] = 66;     // Catch: NoSuchFieldError -> L142
        L169:
            r0[ChatsItem.SWIPE_TO_PIP.ordinal()] = 67;     // Catch: NoSuchFieldError -> L143
        L191:
            r0[ChatsItem.UNMUTE_WITH_VOLUME_BUTTONS.ordinal()] = 68;     // Catch: NoSuchFieldError -> L144
        L205:
            r0[ChatsItem.PAUSE_ON_MINIMIZE.ordinal()] = 69;     // Catch: NoSuchFieldError -> L145
        L207:
            r0[ChatsItem.PAUSE_ON_MINIMIZE_VIDEO.ordinal()] = 70;     // Catch: NoSuchFieldError -> L146
        L219:
            r0[ChatsItem.PAUSE_ON_MINIMIZE_VOICE.ordinal()] = 71;     // Catch: NoSuchFieldError -> L147
        L239:
            r0[ChatsItem.PAUSE_ON_MINIMIZE_ROUND.ordinal()] = 72;     // Catch: NoSuchFieldError -> L148
        L75:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"com/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2", "Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;", "updateStickerPreview", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.chats.ChatsPreferencesActivity$createView$2, reason: invalid class name */
    public static final class AnonymousClass2 extends StickerShapeCell {
        final /* synthetic */ ChatsPreferencesActivity this$0;

        public AnonymousClass2(Context r1, ChatsPreferencesActivity r2) {
            this.this$0 = r2;
            super(r1);
        }

        @Override // com.exteragram.messenger.preferences.chats.components.StickerShapeCell
        public void updateStickerPreview() {
            ChatsPreferencesActivity.m1683access$getParentLayout$p$s146837972(this.this$0).rebuildFragments(0);
            SliderPreviewCell r2 = ChatsPreferencesActivity.access$getStickerSizeCell$p(this.this$0);
            if (r2 == null) goto L6;
            r2.invalidate();
            return;
        }
    }

    /* renamed from: $r8$lambda$3bAEwhc4N0xTYjnM3bIRxyA-8OI, reason: not valid java name */
    public static boolean m1672$r8$lambda$3bAEwhc4N0xTYjnM3bIRxyA8OI() {
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_2) goto L6;
        return true;
    L6:
        return false;
    }

    public static void $r8$lambda$4KvpqlaYRwOjBXeSe628W1EL_Hc(ChatsPreferencesActivity r3, UItem r4, int r5) {
        String r0 = ExteraConfig.getRecognitionLanguage();
        String r52 = languageCodes.get(r5);
        if (Intrinsics.areEqual(r0, r52) == true) goto L38;
        ChatsPreferencesActivity$$ExternalSyntheticLambda44 r02 = new ChatsPreferencesActivity$$ExternalSyntheticLambda44(r52, r3, r4);
        if (Intrinsics.areEqual(r52, "none") == true) goto L30;
        List<VoiceRecognitionController.RecognitionModel> r42 = r3.getDownloadedRecognitionModels();
        if ((r42 instanceof Collection) == true) goto L10;
    L12:
        Iterator<T> r43 = r42.iterator();
    L14:
        if (r43.hasNext() == false) goto L18;
        if (Intrinsics.areEqual(((VoiceRecognitionController.RecognitionModel) r43.next()).getLanguage(), r52) == false) goto L14;
    L18:
        Iterator<T> r44 = VoiceRecognitionController.getInstance().listAvailableModels("vosk").iterator();
    L20:
        if (r44.hasNext() == false) goto L24;
        Object r1 = r44.next();
        if (Intrinsics.areEqual(((VoiceRecognitionController.RecognitionModel) r1).getLanguage(), r52) == false) goto L20;
    L25:
        VoiceRecognitionController.RecognitionModel r12 = (VoiceRecognitionController.RecognitionModel) r1;
        if (r12 != null) goto L28;
        return;
    L28:
        RecognitionModelDialogs.showDownloadDialog(r3, r52, r12, r02);
        return;
    L24:
        r1 = null;
        goto L25
    L10:
        if (r42.isEmpty() == false) goto L12;
    L30:
        r02.run();
        return;
    }

    public static void $r8$lambda$84uyN27AjsN4umQceiXh1BS5wFQ(boolean r0) {
        ExteraConfig.setInlineMathResult(r0);
    }

    /* renamed from: $r8$lambda$DhYH9Loy50Ur_v1N0SzJ-KvEr-w, reason: not valid java name */
    public static Unit m1673$r8$lambda$DhYH9Loy50Ur_v1N0SzJKvErw(ChatsPreferencesActivity r1) {
        r1.parentLayout.rebuildFragments(AndroidUtilities.isTablet() ? 1 : 0);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$F-wjjc_fyoE9NwsY91GTOwPnJXA, reason: not valid java name */
    public static void m1674$r8$lambda$Fwjjc_fyoE9NwsY91GTOwPnJXA(boolean r0) {
        ExteraConfig.setHideKeyboardOnScroll(r0);
    }

    public static void $r8$lambda$FWitcGe3P3jEExkXLxXfYkSVZ4s(ChatsPreferencesActivity r1, int r2) {
        ExteraConfig.setStickerTimeMode(StickerTimeMode.getEntries().get(r2));
        SliderPreviewCell r22 = r1.stickerSizeCell;
        if (r22 == null) goto L5;
        r22.invalidate();
    L5:
        r1.parentLayout.rebuildFragments(0);
    }

    public static boolean $r8$lambda$FaZY3S7n_AQyadXG2RJ9uhDMbLM() {
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L6;
        return true;
    L6:
        return false;
    }

    public static void $r8$lambda$IBjc4AxXgFeekyR4FCGvh48KrBM(boolean r0) {
        ExteraConfig.setHideCameraTile(r0);
    }

    public static void $r8$lambda$ITAZA42M2h_IgK5X0rWrMWGRPTw(boolean r0) {
        ExteraConfig.setStaticZoom(r0);
    }

    public static void $r8$lambda$JUuCcMDTxW8ABK84fpnnPE2XeIY(ChatsPreferencesActivity r0, int r1) {
        ExteraConfig.setDoubleTapActionOutOwner(r1);
        r0.handleDoubleTapActionButtonClick(true);
    }

    public static void $r8$lambda$Jt8nFFlJ2sJgv3YcBe3mz0E9m84(boolean r0) {
        ExteraConfig.setShowOnlineStatus(r0);
    }

    public static void $r8$lambda$K6yOQX4Iotc1La7JOQr7CkKi4RE(int r1) {
        ExteraConfig.setCameraType(CameraType.getEntries().get(r1));
    }

    /* renamed from: $r8$lambda$KS8V5vv1M6-Glb4i2Hv8jWz-QKs, reason: not valid java name */
    public static void m1675$r8$lambda$KS8V5vv1M6Glb4i2Hv8jWzQKs(ChatsPreferencesActivity r3, View r4) {
        AndroidUtilities.updateViewVisibilityAnimated(r4, false, 0.5f, true);
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{ExteraConfig.getStickerSize(), 12.0f});
        r42.setDuration(200);
        r42.addUpdateListener(new ChatsPreferencesActivity$$ExternalSyntheticLambda11(r3));
        r42.start();
    }

    public static void $r8$lambda$KWHRbL1zWGfxJYpycook0LEXYMk(boolean r0) {
        ExteraConfig.setDisableGreetingSticker(r0);
    }

    public static void $r8$lambda$RVDr4oiCTKYRnls7MiRc08oPCs4(boolean r0) {
        ExteraConfig.setAddCommaAfterMention(r0);
    }

    public static void $r8$lambda$SIECvH1woGtCA1yKWk5reWi9U30(boolean r2) {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("rounddual_available", r2);
        r0.apply();
    }

    public static void $r8$lambda$Sf9ddikJ8W132h_hhZTZGz2P0lc(ChatsPreferencesActivity r1, ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        ExteraConfig.setStickerSize(r22);
        SliderPreviewCell r0 = r1.stickerSizeCell;
        if (r0 == null) goto L7;
        AltSeekbar r02 = r0.seekBar;
        if (r02 == null) goto L7;
        r02.setProgress(r22);
    L7:
        SliderPreviewCell r12 = r1.stickerSizeCell;
        if (r12 == null) goto L11;
        r12.invalidate();
        return;
    }

    public static boolean $r8$lambda$X3U9hE5HrzHECcL8uiPCLxnqhKQ() {
        return AiController.canUseAI();
    }

    public static void $r8$lambda$YOefROm89BQul7bk9OuWlbZeuIY(boolean r0) {
        ExteraConfig.setSwipeToPip(r0);
    }

    public static void $r8$lambda$YgkHDV87EEG0n9Tj5_Fjka7kHfE(int r0) {
        ExteraConfig.setDoubleTapSeekDuration(r0);
    }

    /* renamed from: $r8$lambda$YmwtL7Jnn4wPbPnHNiL-8CRFfn8, reason: not valid java name */
    public static void m1676$r8$lambda$YmwtL7Jnn4wPbPnHNiL8CRFfn8(boolean r0) {
        ExteraConfig.setAlwaysSendInHD(r0);
    }

    public static void $r8$lambda$Z6TQ1sAjTbJhiJ9r9nzpIdA6Di4(ChatsPreferencesActivity r1, VoiceRecognitionController.RecognitionModel r2) {
        if (Intrinsics.areEqual(ExteraConfig.getRecognitionLanguage(), r2.getLanguage()) == false) goto L5;
        ExteraConfig.setRecognitionLanguage("none");
    L5:
        r1.getListView$TMessagesProj().adapter.update(true);
    }

    public static void $r8$lambda$Zyay2o4VkHEizG1V7GsE0WSPGh4(ChatsPreferencesActivity r1, float r2) {
        ExteraConfig.setStickerSize(r2);
        ActionBarMenuItem r12 = r1.resetItem;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.getVisibility() == 0) goto L9;
        AndroidUtilities.updateViewVisibilityAnimated(r12, true, 0.5f, true);
        return;
    }

    public static Unit $r8$lambda$aTj7CImeY3w9zA5fuudmfkAIdbA(ChatsPreferencesActivity r1) {
        r1.parentLayout.rebuildFragments(0);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$aa1yxM530VFz38JNCenQ6qK8Bvw(ChatsPreferencesActivity r1) {
        r1.parentLayout.rebuildFragments(0);
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$akHl3KMik9FeBwEWS8MBWmnzpGA(int r1) {
        ExteraConfig.setVideoMessagesCamera(VideoMessagesCamera.getEntries().get(r1));
    }

    /* renamed from: $r8$lambda$cefdfm2DsK_hsuAL4lE-7qv2ngo, reason: not valid java name */
    public static void m1677$r8$lambda$cefdfm2DsK_hsuAL4lE7qv2ngo(String r2, ChatsPreferencesActivity r3, UItem r4) {
        ExteraConfig.setRecognitionLanguage(r2);
        View r42 = r3.getListView$TMessagesProj().findViewByItemId(r4.id);
        if ((r42 instanceof TextCell) == false) goto L5;
        ((TextCell) r42).setValue(TranslatorUtils.getLanguageTitleSystem(r2), true);
    L5:
        r3.getListView$TMessagesProj().adapter.update(true);
    }

    public static boolean $r8$lambda$cwgJ6NDBTXZo7m4A2flYIMZg0d8(ChatsPreferencesActivity r0) {
        return DualCameraView.roundDualAvailableStatic(r0.getContext());
    }

    public static boolean $r8$lambda$cwwkBzNQ_86D_YBCMcm07TVXMxc(ChatsPreferencesActivity r0) {
        return r0.isSeamlessSwitchingAvailable();
    }

    public static void $r8$lambda$e5Kk4sGPAnA2M99NVlDFUs_0ORk(boolean r0) {
        ExteraConfig.setHideShareButton(r0);
    }

    public static void $r8$lambda$e5yImsZcbNNudRLUTsJ96kZ_7uU(boolean r0) {
        ExteraConfig.setQuickAdminShortcuts(r0);
    }

    public static void $r8$lambda$hF42LPDlT6bkWXq6lqrQM9LyN0k(boolean r0) {
        ExteraConfig.setPostprocessingWithAi(r0);
    }

    /* renamed from: $r8$lambda$i6-s6ekwNSnZB1-jtZpy38OQM0U, reason: not valid java name */
    public static void m1678$r8$lambda$i6s6ekwNSnZB1jtZpy38OQM0U(boolean r0) {
        ExteraConfig.setReplaceEditedWithIcon(r0);
    }

    public static void $r8$lambda$jQK7uxuHuR8vfwDoLjdXtOPVSGQ(boolean r0) {
        ExteraConfig.setShowForwardsCount(r0);
    }

    /* renamed from: $r8$lambda$mvxO-JVz3LNxAun88Vdg-UhdHUg, reason: not valid java name */
    public static void m1679$r8$lambda$mvxOJVz3LNxAun88VdgUhdHUg(ChatsPreferencesActivity r0, int r1) {
        ExteraConfig.setBottomButton(r1);
        r0.parentLayout.rebuildFragments(0);
    }

    public static void $r8$lambda$n_JNEUv_iWTJTwOshwVLxai8B9A(boolean r0) {
        ExteraConfig.setUnmuteWithVolumeButtons(r0);
    }

    public static void $r8$lambda$oohFinv130Q8KjzojqiDzlgwZUQ(ChatsPreferencesActivity r0, int r1) {
        ExteraConfig.setDoubleTapAction(r1);
        r0.handleDoubleTapActionButtonClick(false);
    }

    /* renamed from: $r8$lambda$pt-mrvuvGas_kvhbEd0CpPWJ-68, reason: not valid java name */
    public static void m1680$r8$lambda$ptmrvuvGas_kvhbEd0CpPWJ68(boolean r0) {
        ExteraConfig.setPreferOriginalQuality(r0);
    }

    /* renamed from: $r8$lambda$rR-MO8U8uc7_oFt32JaMqyj-xMo, reason: not valid java name */
    public static void m1681$r8$lambda$rRMO8U8uc7_oFt32JaMqyjxMo(boolean r0) {
        ExteraConfig.setHideSendAsPeer(r0);
    }

    public static void $r8$lambda$t1rKI3d162q2kgs4LDbxto5laeg(boolean r0) {
        ExteraConfig.setZoomSlider(r0);
    }

    public static void $r8$lambda$tvEZwVOqKFvPNLRgA8qtPQuHALY(boolean r0) {
        ExteraConfig.setGroupMessageMenu(r0);
    }

    public static void $r8$lambda$wB9LWbf3JBoM3xg7yrDn07WEQe0(boolean r0) {
        ExteraConfig.setUnlimitedRecentStickers(r0);
    }

    public static void $r8$lambda$x2TCZYohYSAJWFK2_jyE3RNzTBo(boolean r0) {
        ExteraConfig.setRemoveMessageTail(r0);
    }

    /* renamed from: $r8$lambda$zJvc4yMuQVZnix3o-g2FSjex8BM, reason: not valid java name */
    public static void m1682$r8$lambda$zJvc4yMuQVZnix3og2FSjex8BM(boolean r0) {
        ExteraConfig.setShowResultsBeforeVoting(r0);
    }

    public static void $r8$lambda$zMv4m2tabIIQBo2Yx_P3u61iU1c(boolean r0) {
        ExteraConfig.setRememberLastUsedCamera(r0);
    }

    static {
        Companion = new Companion(null);
        languageCodes = CollectionsKt.listOf(new String[]{"none", "en", "es", "zh", "hi", "fa", "fr", "ru", "pt", "de", "ja", "ko", "it", "uk", "gu", "pl", "nl", "tr", "vi", "cs", "uz", "eo", "kk", "tg", "ca"});
    }

    public ChatsPreferencesActivity() {
        SwitchGroup r0 = new SwitchGroup(this, ChatsItem.REPLY_ELEMENTS, R.string.RepliesTitle, null, "replyElements", false, 40, null);
        r0.onChanged(new ChatsPreferencesActivity$replyElements$1$1(this));
        SwitchGroup.add$default(r0, ChatsItem.REPLY_COLORS, R.string.BackgroundColors, ChatsPreferencesActivity$replyElements$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r0, ChatsItem.REPLY_EMOJI, R.string.Emoji, ChatsPreferencesActivity$replyElements$1$3.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r0, ChatsItem.REPLY_BACKGROUND, R.string.ReplyBackground, ChatsPreferencesActivity$replyElements$1$4.INSTANCE, null, 8, null);
        this.replyElements = r0;
        SwitchGroup r1 = new SwitchGroup(this, ChatsItem.HIDE_REACTIONS, R.string.HideReactions, null, "hideReactions", false, 40, null);
        r1.onChanged(new ChatsPreferencesActivity$hideReactions$1$1(this));
        SwitchGroup.add$default(r1, ChatsItem.CHANNELS, R.string.ChannelsTab, ChatsPreferencesActivity$hideReactions$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r1, ChatsItem.GROUPS, R.string.SaveToGalleryGroups, ChatsPreferencesActivity$hideReactions$1$3.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r1, ChatsItem.PRIVATE_CHATS, R.string.PrivateChats, ChatsPreferencesActivity$hideReactions$1$4.INSTANCE, null, 8, null);
        this.hideReactions = r1;
        SwitchGroup r2 = new SwitchGroup(this, ChatsItem.WIDE_POSTS, R.string.WidePosts, null, "widePosts", false, 40, null);
        r2.onChanged(new ChatsPreferencesActivity$$ExternalSyntheticLambda2(this));
        SwitchGroup.add$default(r2, ChatsItem.WIDE_POSTS_FEED, R.string.Feed, ChatsPreferencesActivity$widePosts$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r2, ChatsItem.WIDE_POSTS_CHANNELS, R.string.ChannelsTab, ChatsPreferencesActivity$widePosts$1$3.INSTANCE, null, 8, null);
        this.widePosts = r2;
        SwitchGroup r02 = new SwitchGroup(this, ChatsItem.AI_FEATURES, R.string.AIFeatures, null, "aiFeatures", false, 40, null);
        r02.onChanged(new ChatsPreferencesActivity$$ExternalSyntheticLambda3(this));
        SwitchGroup.add$default(r02, ChatsItem.AI_FEATURES_EDITOR, R.string.AIFeaturesEditor, ChatsPreferencesActivity$aiFeatures$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r02, ChatsItem.AI_FEATURES_SUMMARIES, R.string.AIFeaturesSummaries, ChatsPreferencesActivity$aiFeatures$1$3.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r02, ChatsItem.AI_FEATURES_INSTANT_VIEW_SUMMARIES, R.string.AIFeaturesInstantViewSummaries, ChatsPreferencesActivity$aiFeatures$1$4.INSTANCE, null, 8, null);
        this.aiFeatures = r02;
        SwitchGroup r22 = new SwitchGroup(this, ChatsItem.QUICK_TRANSITIONS, R.string.QuickTransitions, null, "quickTransitions", false, 40, null);
        SwitchGroup.add$default(r22, ChatsItem.QUICK_TRANSITION_FOR_CHANNELS, R.string.FilterChannels, ChatsPreferencesActivity$quickTransitions$1$1.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r22, ChatsItem.QUICK_TRANSITION_FOR_TOPICS, R.string.Topics, ChatsPreferencesActivity$quickTransitions$1$2.INSTANCE, null, 8, null);
        this.quickTransitions = r22;
        SwitchGroup r03 = new SwitchGroup(this, ChatsItem.MESSAGE_MENU, R.string.MessageMenu, null, "messageMenu", false, 40, null);
        r03.onChanged(new ChatsPreferencesActivity$$ExternalSyntheticLambda4(this));
        SwitchGroup.add$default(r03, ChatsItem.COPY_PHOTO, R.string.CopyPhoto, ChatsPreferencesActivity$messageMenu$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r03, ChatsItem.SAVE, R.string.Save, ChatsPreferencesActivity$messageMenu$1$3.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r03, ChatsItem.REPEAT, R.string.Repeat, ChatsPreferencesActivity$messageMenu$1$4.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r03, ChatsItem.CLEAR, R.string.Clear, ChatsPreferencesActivity$messageMenu$1$5.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r03, ChatsItem.HISTORY, R.string.MessageHistory, ChatsPreferencesActivity$messageMenu$1$6.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r03, ChatsItem.REPORT, R.string.ReportChat, ChatsPreferencesActivity$messageMenu$1$7.INSTANCE, null, 8, null);
        SwitchGroup.addIf$default(r03, new ChatsPreferencesActivity$$ExternalSyntheticLambda5(), ChatsItem.GENERATE, R.string.Generate, ChatsPreferencesActivity$messageMenu$1$9.INSTANCE, null, 16, null);
        SwitchGroup.add$default(r03, ChatsItem.DETAILS, R.string.Details, ChatsPreferencesActivity$messageMenu$1$10.INSTANCE, null, 8, null);
        this.messageMenu = r03;
        SwitchGroup r23 = new SwitchGroup(this, ChatsItem.CAMERA_SETTINGS, R.string.ExtendedSettings, null, "cameraSettings", false, 40, null);
        SwitchGroup.addIf$default(r23, new ChatsPreferencesActivity$$ExternalSyntheticLambda6(this), ChatsItem.DUAL_CAMERA, R.string.SeamlessSwitching, new ChatsPreferencesActivity$$ExternalSyntheticLambda7(this), new ChatsPreferencesActivity$$ExternalSyntheticLambda8(), null, 32, null);
        SwitchGroup.add$default(r23, ChatsItem.EXTENDED_FRAMES_PER_SECOND, R.string.ExtendedFramesPerSecond, ChatsPreferencesActivity$cameraSettings$1$4.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r23, ChatsItem.CAMERA_STABILIZATION, R.string.CameraStabilization, ChatsPreferencesActivity$cameraSettings$1$5.INSTANCE, null, 8, null);
        SwitchGroup.addIf$default(r23, new ChatsPreferencesActivity$$ExternalSyntheticLambda9(), ChatsItem.CAMERA_MIRROR_MODE, R.string.CameraMirrorMode, ChatsPreferencesActivity$cameraSettings$1$7.INSTANCE, null, 16, null);
        SwitchGroup.addIf$default(r23, new ChatsPreferencesActivity$$ExternalSyntheticLambda10(), ChatsItem.START_WITH_WIDE_ANGLE_CAMERA, R.string.StartWithWideAngleCamera, ChatsPreferencesActivity$cameraSettings$1$9.INSTANCE, null, 16, null);
        r23.markNew("Camera-ExtendedSettings-StartWithWideAngle");
        this.cameraSettings = r23;
        SwitchGroup r3 = new SwitchGroup(this, ChatsItem.PAUSE_ON_MINIMIZE, R.string.PauseOnMinimize, null, "pauseOnMinimize", false, 40, null);
        SwitchGroup.add$default(r3, ChatsItem.PAUSE_ON_MINIMIZE_VIDEO, R.string.PauseOnMinimizeVideo, ChatsPreferencesActivity$pauseOnMinimize$1$1.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r3, ChatsItem.PAUSE_ON_MINIMIZE_VOICE, R.string.PauseOnMinimizeVoice, ChatsPreferencesActivity$pauseOnMinimize$1$2.INSTANCE, null, 8, null);
        SwitchGroup.add$default(r3, ChatsItem.PAUSE_ON_MINIMIZE_ROUND, R.string.PauseOnMinimizeRound, ChatsPreferencesActivity$pauseOnMinimize$1$3.INSTANCE, null, 8, null);
        this.pauseOnMinimize = r3;
    }

    /* renamed from: access$getParentLayout$p$s-146837972, reason: not valid java name */
    public static final /* synthetic */ INavigationLayout m1683access$getParentLayout$p$s146837972(ChatsPreferencesActivity r0) {
        return r0.parentLayout;
    }

    public static final /* synthetic */ SliderPreviewCell access$getStickerSizeCell$p(ChatsPreferencesActivity r0) {
        return r0.stickerSizeCell;
    }

    public static final /* synthetic */ void access$updateReplySettings(ChatsPreferencesActivity r0) {
        r0.updateReplySettings();
    }

    private final List<VoiceRecognitionController.RecognitionModel> getDownloadedRecognitionModels() {
        return VoiceRecognitionController.getInstance().listDownloadedModels("vosk");
    }

    private final void handleDeleteRecognitionModelClick() {
        RecognitionModelDialogs.showDeleteFlow(this, getDownloadedRecognitionModels(), new ChatsPreferencesActivity$$ExternalSyntheticLambda43(this));
    }

    private final DoubleTapCell handleDoubleTapActionButtonClick(boolean r2) {
        DoubleTapCell r1 = this.doubleTapCell;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r2 == false) goto L7;
        int r22 = 2;
    L8:
        r1.updateIcons(r22, true);
        r1.invalidate();
        return r1;
    L7:
        r22 = 1;
        goto L8
    }

    private final void handleSpeechRecognitionLanguageClick(UItem r8) {
        CharSequence[] r0 = this.recognitionLanguageOptions;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        PopupUtils.showDialog(r0, null, LocaleController.getString(R.string.RecognitionLanguage), languageCodes.indexOf(ExteraConfig.getRecognitionLanguage()), getContext(), new ChatsPreferencesActivity$$ExternalSyntheticLambda42(this, r8));
    }

    private final boolean isSeamlessSwitchingAvailable() {
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L7;
        if (CameraXSession.isSeamlessSwitchingAvailable(getContext()) == false) goto L7;
        return true;
    L7:
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_2) goto L9;
        return false;
    L9:
        if (DualCameraView.dualAvailableStatic(getContext()) == false) goto L15;
        return true;
    L15:
        return false;
    }

    private final CharSequence swipeActionsValue() {
        List<SwipeAction> r0 = SwipeAction.enabled();
        if (r0.isEmpty() == true) goto L5;
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        int r2 = RangesKt.coerceAtMost(r0.size(), 5);
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L16;
        if (r3 <= 0) goto L10;
        r1.append(' ');
    L10:
        Drawable r4 = ContextCompat.getDrawable(getContext(), r0.get(r3).iconRes);
        if (r4 == null) goto L14;
        int r5 = r1.length();
        r1.append('*');
        ColoredImageSpan r6 = new ColoredImageSpan(r4.mutate());
        r6.setSize(AndroidUtilities.dp(18.0f));
        r1.setSpan(r6, r5, r1.length(), 33);
    L14:
        r3 = r3 + 1;
        goto L7
    L16:
        if (r0.size() <= r2) goto L18;
        r1.append(" +").append(String.valueOf(r0.size() - r2));
    L18:
        return r1;
    L5:
        return LocaleController.getString(R.string.Disable);
    }

    private final void updateReplySettings() {
        SliderPreviewCell r0 = this.stickerSizeCell;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        this.parentLayout.rebuildFragments(0);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        SliderPreviewCell r0 = new SliderPreviewCell(this.parentLayout, r12, ChatsItem.STICKER_SIZE.getId(), 4, 20, ExteraConfig.getStickerSize(), LocaleController.getString(R.string.StickerSize), LocaleController.getString(R.string.StickerSizeLeft), LocaleController.getString(R.string.StickerSizeRight), false);
        r0.setListener(new ChatsPreferencesActivity$$ExternalSyntheticLambda0(this));
        this.stickerSizeCell = r0;
        this.stickerShapeCell = new AnonymousClass2(r12, this);
        this.doubleTapCell = new DoubleTapCell(r12);
        this.messagesPreviewCell = new MessagesPreviewCell(r12, this.parentLayout, 1);
        View r122 = super.createView(r12);
        int r2 = 0;
        ActionBarMenuItem r02 = this.actionBar.createMenu().addItem(0, R.drawable.msg_reset);
        r02.setContentDescription(LocaleController.getString(R.string.Reset));
        if (ExteraConfig.getStickerSize() != 12.0f) goto L5;
        r2 = 8;
    L5:
        r02.setVisibility(r2);
        r02.setTag(null);
        r02.setOnClickListener(new ChatsPreferencesActivity$$ExternalSyntheticLambda1(this));
        this.resetItem = r02;
        return r122;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r18, UniversalAdapter r19) {
        r18.add(UItem.asCustom(ChatsItem.STICKER_SIZE.getId(), this.stickerSizeCell).setLinkAlias("stickerSize", this));
        ChatsItem r1 = ChatsItem.STICKER_TIME;
        String r2 = LocaleController.getString(R.string.StickerTimeMode);
        CharSequence[] r3 = this.stickerTimeModes;
        if (r3 != null) goto L5;
        r3 = null;
    L5:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r1, r2, 0, r3[ExteraConfig.getStickerTimeMode().ordinal()], null, null, false, "stickerTime", false, false, null, 1908, null));
        this.replyElements.fill(r18);
        r18.add(UItem.asShadow());
        r18.add(UItem.asHeader(LocaleController.getString(R.string.StickerShape)));
        r18.add(UItem.asCustom(ChatsItem.STICKER_SHAPE.getId(), this.stickerShapeCell).setLinkAlias("stickerShape", this));
        r18.add(UItem.asShadow());
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.AI, LocaleController.getString(R.string.AIChat), R.drawable.ai_chat, null, LocaleController.getString(R.string.AIChatInfo), null, false, "aiChat", false, false, null, 1896, null));
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.CHAT_SETTINGS, LocaleController.getString(R.string.ChatSettings), R.drawable.msg_discussion, null, LocaleController.getString(R.string.ChatSettingsInfo), null, false, "chatSettings", false, false, null, 1832, null));
        r18.add(UItem.asShadow());
        r18.add(UItem.asHeader(LocaleController.getString(R.string.StickersName)));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.UNLIMITED_RECENT_STICKERS, LocaleController.getString(R.string.UnlimitedRecentStickers), ExteraConfig.getUnlimitedRecentStickers(), null, 0, false, "unlimitedRecentStickers", false, 0, 440, null));
        this.hideReactions.fill(r18);
        r18.add(UItem.asShadow(LocaleController.getString(R.string.HideReactionsInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.Gestures)));
        r18.add(UItem.asCustom(ChatsItem.DOUBLE_TAP.getId(), this.doubleTapCell));
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.DOUBLE_TAP_ACTION, LocaleController.getString(R.string.DoubleTapIncoming), 0, DoubleTapUtils.getDoubleTapActionLabel(ExteraConfig.getDoubleTapAction(), false), null, null, false, "doubleTapIncoming", false, false, null, 1908, null));
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.DOUBLE_TAP_ACTION_OUT_OWNER, LocaleController.getString(R.string.DoubleTapOutgoing), 0, DoubleTapUtils.getDoubleTapActionLabel(ExteraConfig.getDoubleTapActionOutOwner(), true), null, null, false, "doubleTapOutgoing", false, false, null, 1908, null));
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.SWIPE_ACTIONS, LocaleController.getString(R.string.SwipeActions), 0, swipeActionsValue(), null, null, false, "swipeActions", false, false, null, 1908, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.DoubleTapInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.MainTabsChats)));
        ChatsItem r12 = ChatsItem.BOTTOM_BUTTON;
        String r22 = LocaleController.getString(R.string.BottomButton);
        CharSequence[] r32 = this.bottomButtonOptions;
        if (r32 != null) goto L8;
        r32 = null;
    L8:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r12, r22, 0, r32[ExteraConfig.getBottomButton()], null, null, false, "bottomButton", false, false, null, 1908, null));
        this.widePosts.fill(r18);
        this.aiFeatures.fill(r18);
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.ADMIN_SHORTCUTS, LocaleController.getString(R.string.AdminShortcuts), ExteraConfig.getQuickAdminShortcuts(), null, 0, false, "adminShortcuts", false, 0, 440, null));
        this.quickTransitions.fill(r18);
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.DISABLE_GREETING_STICKER, LocaleController.getString(R.string.DisableGreetingSticker), ExteraConfig.getDisableGreetingSticker(), null, 0, false, "disableGreetingSticker", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.HIDE_KEYBOARD_ON_SCROLL, LocaleController.getString(R.string.HideKeyboardOnScroll), ExteraConfig.getHideKeyboardOnScroll(), null, 0, false, "hideKeyboardOnScroll", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.ADD_COMMA_AFTER_MENTION, LocaleController.getString(R.string.AddCommaAfterMention), ExteraConfig.getAddCommaAfterMention(), null, 0, false, "addCommaAfterMention", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.INLINE_MATH_RESULT, LocaleController.getString(R.string.InlineMathResult), ExteraConfig.getInlineMathResult(), LocaleController.getString(R.string.InlineMathResultHint), 0, false, "inlineMathResult", false, 0, 432, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.HIDE_SEND_AS_PEER, LocaleController.getString(R.string.HideSendAsPeer), ExteraConfig.getHideSendAsPeer(), null, 0, false, "hideSendAsPeer", false, 0, 440, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.HideSendAsPeerInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.MessagesChartTitle)));
        r18.add(UItem.asCustom(ChatsItem.MESSAGES_PREVIEW.getId(), this.messagesPreviewCell));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.REMOVE_MESSAGE_TAIL, LocaleController.getString(R.string.RemoveMessageTail), ExteraConfig.getRemoveMessageTail(), null, 0, false, "removeMessageTail", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.REPLACE_EDITED_WITH_ICON, LocaleController.formatString(R.string.ReplaceEditedWithIcon, new Object[]{LocaleController.getString(R.string.EditedMessage)}), ExteraConfig.getReplaceEditedWithIcon(), null, 0, false, "replaceEditedWithIcon", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.SHOW_ONLINE_STATUS, LocaleController.getString(R.string.ShowOnlineStatus), ExteraConfig.getShowOnlineStatus(), null, 0, false, "showOnlineStatus", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.SHOW_FORWARDS_COUNT, LocaleController.getString(R.string.ShowForwardsCount), ExteraConfig.getShowForwardsCount(), null, 0, false, "showForwardsCount", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.HIDE_SHARE_BUTTON, LocaleController.formatString(R.string.HideShareButton, new Object[]{LocaleController.getString(R.string.ShareFile)}), ExteraConfig.getHideShareButton(), null, 0, false, "hideShareButton", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.SHOW_RESULTS_BEFORE_VOTING, LocaleController.getString(R.string.ShowPollResultsBeforeVoting), ExteraConfig.getShowResultsBeforeVoting(), LocaleController.getString(R.string.ShowPollResultsBeforeVotingHint), 0, false, "showResultsBeforeVoting", false, 0, 432, null));
        this.messageMenu.fill(r18);
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.GROUP_MESSAGE_MENU, LocaleController.getString(R.string.GroupMessageMenu), ExteraConfig.getGroupMessageMenu(), null, 0, false, "groupMessageMenu", false, 0, 440, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.GroupMessageMenuInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.PremiumPreviewVoiceToText)));
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.SPEECH_RECOGNITION_LANGUAGE, LocaleController.getString(R.string.RecognitionLanguage), 0, TranslatorUtils.getLanguageTitleSystem(ExteraConfig.getRecognitionLanguage()), null, null, false, "recognitionLanguage", false, false, null, 1908, null));
        if (AiController.canUseAI() == true) goto L11;
    L13:
        boolean r122 = false;
    L14:
        ExtensionsKt.addIf(r18, r122, BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.POST_PROCESSING_WITH_AI, LocaleController.getString(R.string.PostProcessingWithAi), ExteraConfig.getPostprocessingWithAi(), LocaleController.getString(R.string.PostProcessingWithAiInfo), 0, false, "postprocessingWithAi", false, 0, 432, null));
        ExtensionsKt.addIf(r18, !getDownloadedRecognitionModels().isEmpty(), BasePreferencesActivity.uButton$TMessagesProj$default(this, ChatsItem.DELETE_RECOGNITION_MODEL, LocaleController.getString(R.string.DeleteRecognitionModel), R.drawable.msg_delete, null, null, null, false, "deleteRecognitionModel", true, false, null, 1656, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.RecognitionInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.VoipCamera)));
        ChatsItem r13 = ChatsItem.CAMERA_TYPE;
        String r23 = LocaleController.getString(R.string.CameraType);
        CharSequence[] r33 = this.cameraTypeOptions;
        if (r33 != null) goto L17;
        r33 = null;
    L17:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r13, r23, 0, r33[ExteraConfig.getCameraType().ordinal()], null, null, false, "cameraType", false, false, null, 1908, null));
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_1) goto L22;
        this.cameraSettings.fill(r18);
        if (isSeamlessSwitchingAvailable() == true) goto L22;
        SharedPreferences.Editor r14 = MessagesController.getGlobalMainSettings().edit();
        boolean r15 = false;
        r14.putBoolean("rounddual_available", false);
        r14.apply();
    L23:
        ChatsItem r16 = ChatsItem.VIDEO_MESSAGES_CAMERA;
        String r24 = LocaleController.getString(R.string.VideoMessagesCamera);
        CharSequence[] r34 = this.videoMessagesCameraOptions;
        if (r34 != null) goto L26;
        r34 = null;
    L26:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r16, r24, 0, r34[ExteraConfig.getVideoMessagesCamera().ordinal()], null, null, false, "videoMessagesCamera", false, false, null, 1908, null));
        if (ExteraConfig.getVideoMessagesCamera() == VideoMessagesCamera.ASK) goto L29;
        r15 = true;
    L29:
        ExtensionsKt.addIf(r18, r15, BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.REMEMBER_LAST_USED_CAMERA, LocaleController.getString(R.string.RememberLastUsedCamera), ExteraConfig.getRememberLastUsedCamera(), LocaleController.getString(R.string.RememberLastUsedCameraInfo), 0, false, "rememberLastUsedCamera", false, 0, 432, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.ZOOM_SLIDER, LocaleController.getString(R.string.ZoomSlider), ExteraConfig.getZoomSlider(), LocaleController.getString(R.string.ZoomSliderInfo), 0, false, "zoomSlider", false, 0, 432, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.STATIC_ZOOM, LocaleController.getString(R.string.StaticZoom), ExteraConfig.getStaticZoom(), null, 0, false, "staticZoom", false, 0, 440, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.StaticZoomInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.AutoDownloadPhotos)));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.ALWAYS_SEND_IN_HD, LocaleController.getString(R.string.AlwaysSendInHD), ExteraConfig.getAlwaysSendInHD(), null, 0, false, "alwaysSendInHD", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.HIDE_CAMERA_TILE, LocaleController.getString(R.string.HideCameraTile), ExteraConfig.getHideCameraTile(), null, 0, false, "hideCameraTile", false, 0, 440, null));
        r18.add(UItem.asShadow(LocaleController.getString(R.string.HideCameraTileInfo)));
        r18.add(UItem.asHeader(LocaleController.getString(R.string.AutoDownloadVideos)));
        ChatsItem r17 = ChatsItem.DOUBLE_TAP_SEEK_DURATION;
        String r25 = LocaleController.getString(R.string.DoubleTapSeekDuration);
        CharSequence[] r35 = this.doubleTapSeekDurationOptions;
        if (r35 != null) goto L32;
        CharSequence[] r152 = null;
    L33:
        r18.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r17, r25, 0, r152[ExteraConfig.getDoubleTapSeekDuration()], null, null, false, "doubleTapSeekDuration", false, false, null, 1908, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.PREFER_ORIGINAL_QUALITY, LocaleController.getString(R.string.PreferOriginalQuality), ExteraConfig.getPreferOriginalQuality(), null, 0, false, "preferOriginalQuality", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.SWIPE_TO_PIP, LocaleController.getString(R.string.SwipeToPip), ExteraConfig.getSwipeToPip(), null, 0, false, "swipeToPip", false, 0, 440, null));
        r18.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, ChatsItem.UNMUTE_WITH_VOLUME_BUTTONS, LocaleController.getString(R.string.UnmuteWithVolumeButtons), ExteraConfig.getUnmuteWithVolumeButtons(), LocaleController.getString(R.string.UnmuteWithVolumeButtonsInfo), 0, false, "unmuteWithVolumeButtons", false, 0, 432, null));
        this.pauseOnMinimize.fill(r18);
        r18.add(UItem.asShadow(LocaleController.getString(R.string.PauseOnMinimizeInfo)));
        return;
    L32:
        r152 = r35;
    L22:
        r15 = false;
        goto L23
    L11:
        if (VoiceRecognitionController.isCustomRecognitionEnabled() == false) goto L13;
        r122 = true;
        goto L14
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.SearchAllChatsShort);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void initializeOptionStrings() {
        this.stickerTimeModes = new CharSequence[]{LocaleController.getString(R.string.Default), LocaleController.getString(R.string.StickerTimeSide), LocaleController.getString(R.string.StickerTimeHidden)};
        this.doubleTapActions = DoubleTapUtils.getDoubleTapActions(false);
        this.doubleTapOutActions = DoubleTapUtils.getDoubleTapActions(true);
        this.bottomButtonOptions = new CharSequence[]{LocaleController.getString(R.string.Hide), LocaleController.getString(R.string.ChannelMuteNoCaps), LocaleController.getString(R.string.ChannelDiscussNoCaps)};
        this.videoMessagesCameraOptions = new CharSequence[]{LocaleController.getString(R.string.VideoMessagesCameraFront), LocaleController.getString(R.string.VideoMessagesCameraRear), LocaleController.getString(R.string.VideoMessagesCameraAsk)};
        this.doubleTapSeekDurationOptions = new CharSequence[]{LocaleController.formatPluralString("Seconds", 5, new Object[0]), LocaleController.formatPluralString("Seconds", 10, new Object[0]), LocaleController.formatPluralString("Seconds", 15, new Object[0]), LocaleController.formatPluralString("Seconds", 30, new Object[0])};
        this.cameraTypeOptions = new CharSequence[]{"Camera 1", "Camera 2", "Camera X"};
        List<String> r0 = languageCodes;
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator<T> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        r1.add(RecognitionModelDialogs.getRecognitionLanguageOption((String) r02.next()));
        goto L4
    L6:
        this.recognitionLanguageOptions = (CharSequence[]) r1.toArray(new CharSequence[0]);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r12, View r13, int r14, float r15, float r16) {
        int r2 = r12.id;
        if (r2 > 0) goto L5;
        return;
    L5:
        if (r2 <= ChatsItem.getEntries().size()) goto L8;
        return;
    L8:
        switch(WhenMappings.$EnumSwitchMapping$0[ChatsItem.getEntries().get(r12.id - 1).ordinal()]) {
            case 1: goto L125;
            case 2: goto L123;
            case 3: goto L123;
            case 4: goto L123;
            case 5: goto L123;
            case 6: goto L121;
            case 7: goto L119;
            case 8: goto L117;
            case 9: goto L115;
            case 10: goto L115;
            case 11: goto L115;
            case 12: goto L115;
            case 13: goto L109;
            case 14: goto L103;
            case 15: goto L101;
            case 16: goto L96;
            case 17: goto L94;
            case 18: goto L94;
            case 19: goto L94;
            case 20: goto L92;
            case 21: goto L92;
            case 22: goto L92;
            case 23: goto L92;
            case 24: goto L90;
            case 25: goto L88;
            case 26: goto L88;
            case 27: goto L88;
            case 28: goto L86;
            case 29: goto L84;
            case 30: goto L82;
            case 31: goto L80;
            case 32: goto L78;
            case 33: goto L74;
            case 34: goto L70;
            case 35: goto L65;
            case 36: goto L61;
            case 37: goto L59;
            case 38: goto L55;
            case 39: goto L53;
            case 40: goto L53;
            case 41: goto L53;
            case 42: goto L53;
            case 43: goto L53;
            case 44: goto L53;
            case 45: goto L53;
            case 46: goto L53;
            case 47: goto L53;
            case 48: goto L51;
            case 49: goto L49;
            case 50: goto L47;
            case 51: goto L45;
            case 52: goto L40;
            case 53: goto L38;
            case 54: goto L38;
            case 55: goto L38;
            case 56: goto L38;
            case 57: goto L38;
            case 58: goto L38;
            case 59: goto L33;
            case 60: goto L31;
            case 61: goto L29;
            case 62: goto L27;
            case 63: goto L25;
            case 64: goto L23;
            case 65: goto L18;
            case 66: goto L16;
            case 67: goto L14;
            case 68: goto L12;
            case 69: goto L10;
            case 70: goto L10;
            case 71: goto L10;
            case 72: goto L10;
            default: goto L136;
        };
    L10:
        this.pauseOnMinimize.onClick(r12);
        return;
    L12:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda33());
        return;
    L14:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda32());
        return;
    L16:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda31());
        return;
    L18:
        CharSequence[] r22 = this.doubleTapSeekDurationOptions;
        if (r22 != null) goto L21;
        r22 = null;
    L21:
        showListDialog(r12, r22, LocaleController.getString(R.string.DoubleTapSeekDuration), ExteraConfig.getDoubleTapSeekDuration(), new ChatsPreferencesActivity$$ExternalSyntheticLambda30());
        return;
    L23:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda29());
        return;
    L25:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda28());
        return;
    L27:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda27());
        return;
    L29:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda26());
        return;
    L31:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda25());
        return;
    L33:
        CharSequence[] r23 = this.videoMessagesCameraOptions;
        if (r23 != null) goto L36;
        r23 = null;
    L36:
        showListDialog(r12, r23, LocaleController.getString(R.string.VideoMessagesCamera), ExteraConfig.getVideoMessagesCamera().ordinal(), new ChatsPreferencesActivity$$ExternalSyntheticLambda24());
        return;
    L38:
        this.cameraSettings.onClick(r12);
        return;
    L40:
        CharSequence[] r24 = this.cameraTypeOptions;
        if (r24 != null) goto L43;
        r24 = null;
    L43:
        showListDialog(r12, r24, LocaleController.getString(R.string.CameraType), ExteraConfig.getCameraType().ordinal(), new ChatsPreferencesActivity$$ExternalSyntheticLambda22());
        return;
    L45:
        handleDeleteRecognitionModelClick();
        return;
    L47:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda21());
        return;
    L49:
        handleSpeechRecognitionLanguageClick(r12);
        return;
    L51:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda20());
        return;
    L53:
        this.messageMenu.onClick(r12);
        return;
    L55:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda19());
        MessagesPreviewCell r0 = this.messagesPreviewCell;
        if (r0 == null) goto L131;
        r0.refreshMessages();
        return;
    L131:
        return;
    L59:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda18());
        this.parentLayout.rebuildFragments(0);
        return;
    L61:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda17());
        Theme.chat_msgInDrawable = null;
        Theme.createChatResources(getParentActivity(), false);
        MessagesPreviewCell r02 = this.messagesPreviewCell;
        if (r02 == null) goto L132;
        r02.refreshMessages();
        return;
    L132:
        return;
    L65:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda16());
        MessagesPreviewCell r1 = this.messagesPreviewCell;
        if (r1 == null) goto L68;
        r1.refreshMessages();
    L68:
        this.parentLayout.rebuildFragments(AndroidUtilities.isTablet() ? 1 : 0);
        return;
    L70:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda15());
        MessagesPreviewCell r03 = this.messagesPreviewCell;
        if (r03 == null) goto L133;
        r03.refreshMessages();
        return;
    L133:
        return;
    L74:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda14());
        MessagesPreviewCell r04 = this.messagesPreviewCell;
        if (r04 == null) goto L134;
        r04.refreshMessages();
        return;
    L134:
        return;
    L78:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda13());
        return;
    L80:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda41());
        return;
    L82:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda40());
        return;
    L84:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda39());
        return;
    L86:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda38());
        return;
    L88:
        this.quickTransitions.onClick(r12);
        return;
    L90:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda37());
        return;
    L92:
        this.aiFeatures.onClick(r12);
        return;
    L94:
        this.widePosts.onClick(r12);
        return;
    L96:
        CharSequence[] r25 = this.bottomButtonOptions;
        if (r25 != null) goto L99;
        r25 = null;
    L99:
        showListDialog(r12, r25, LocaleController.getString(R.string.BottomButton), ExteraConfig.getBottomButton(), new ChatsPreferencesActivity$$ExternalSyntheticLambda36(this));
        return;
    L101:
        presentFragment(new SwipeActionsPreferencesActivity());
        return;
    L103:
        CharSequence[] r17 = this.doubleTapOutActions;
        if (r17 != null) goto L106;
        CharSequence[] r26 = null;
    L107:
        BasePreferencesActivity.showListDialog$default(this, r12, r26, DoubleTapUtils.getDoubleTapIcons(true), LocaleController.getString(R.string.DoubleTapOutgoing), ExteraConfig.getDoubleTapActionOutOwner(), new ChatsPreferencesActivity$$ExternalSyntheticLambda35(this), false, false, 192, null);
        return;
    L106:
        r26 = r17;
        goto L107
    L109:
        CharSequence[] r18 = this.doubleTapActions;
        if (r18 != null) goto L112;
        CharSequence[] r27 = null;
    L113:
        BasePreferencesActivity.showListDialog$default(this, r12, r27, DoubleTapUtils.getDoubleTapIcons(false), LocaleController.getString(R.string.DoubleTapIncoming), ExteraConfig.getDoubleTapAction(), new ChatsPreferencesActivity$$ExternalSyntheticLambda34(this), false, false, 192, null);
        return;
    L112:
        r27 = r18;
        goto L113
    L115:
        this.hideReactions.onClick(r12);
        return;
    L117:
        toggleBooleanSettingAndRefresh$TMessagesProj(r12, new ChatsPreferencesActivity$$ExternalSyntheticLambda23());
        return;
    L119:
        presentFragment(new ThemeActivity(0));
        return;
    L121:
        presentFragment(new AiPreferencesActivity());
        return;
    L123:
        this.replyElements.onClick(r12);
        return;
    L125:
        CharSequence[] r28 = this.stickerTimeModes;
        if (r28 != null) goto L128;
        r28 = null;
    L128:
        showListDialog(r12, r28, LocaleController.getString(R.string.StickerTimeMode), ExteraConfig.getStickerTimeMode().ordinal(), new ChatsPreferencesActivity$$ExternalSyntheticLambda12(this));
        return;
    }
}
