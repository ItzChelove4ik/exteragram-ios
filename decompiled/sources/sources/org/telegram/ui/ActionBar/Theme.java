package org.telegram.ui.ActionBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.StateSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.DividerStyle;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.ui.MonetUtils;
import com.exteragram.messenger.utils.ui.TextPaint;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import kotlin.UByte;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.http.HttpStatusCodesKt;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesController$$ExternalSyntheticLambda83;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.time.SunDate;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.BlurSettingsBottomSheet;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AudioVisualizerDrawable;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChoosingStickerStatusDrawable;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.FragmentContextViewWavesDrawable;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.MsgClockDrawable;
import org.telegram.ui.Components.PathAnimator;
import org.telegram.ui.Components.PlayingGameDrawable;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecordStatusDrawable;
import org.telegram.ui.Components.RoundStatusDrawable;
import org.telegram.ui.Components.ScamDrawable;
import org.telegram.ui.Components.SendingFileDrawable;
import org.telegram.ui.Components.StatusDrawable;
import org.telegram.ui.Components.ThemeEditorView;
import org.telegram.ui.Components.TypingDotsDrawable;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.RoundVideoProgressShadow;
import org.telegram.ui.ThemeActivity;
import org.telegram.ui.ThemePreviewActivity;

/* loaded from: classes3.dex */
public abstract class Theme {
    public static Paint DEBUG_BLUE;
    public static Paint DEBUG_GREEN_40;
    public static Paint DEBUG_GREEN_B0;
    public static Paint DEBUG_GREEN_STROKE;
    public static Paint DEBUG_RED;
    public static Paint DEBUG_RED_STROKE;
    public static int DEFALT_THEME_ACCENT_ID;
    public static final Paint PAINT_CLEAR = null;
    private static final Paint PAINT_FILLING = null;
    private static Method StateListDrawable_getStateDrawableMethod;
    private static SensorEventListener ambientSensorListener;
    private static HashMap<MessageObject, AudioVisualizerDrawable> animatedOutVisualizerDrawables;
    private static SparseIntArray animatingColors;
    public static float autoNightBrighnessThreshold;
    public static String autoNightCityName;
    public static int autoNightDayEndTime;
    public static int autoNightDayStartTime;
    public static int autoNightLastSunCheckDay;
    public static double autoNightLocationLatitude;
    public static double autoNightLocationLongitude;
    public static boolean autoNightScheduleByLocation;
    public static int autoNightSunriseTime;
    public static int autoNightSunsetTime;
    public static Drawable[] avatarDrawables;
    public static Paint avatar_backgroundPaint;
    private static BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
    private static Bitmap blurredBitmap;
    public static Drawable calllog_msgCallDownGreenDrawable;
    public static Drawable calllog_msgCallDownRedDrawable;
    public static Drawable calllog_msgCallUpGreenDrawable;
    public static Drawable calllog_msgCallUpRedDrawable;
    private static boolean canStartHolidayAnimation;
    private static boolean changingWallpaper;
    public static Paint chat_actionBackgroundGradientDarkenPaint;
    public static Paint chat_actionBackgroundPaint;
    public static Paint chat_actionBackgroundSelectedPaint;
    public static TextPaint chat_actionTextPaint;
    public static TextPaint chat_actionTextPaint2;
    public static TextPaint chat_actionTextPaint3;
    public static TextPaint chat_adminPaint;
    public static PorterDuffColorFilter chat_animatedEmojiTextColorFilter;
    public static Drawable chat_attachEmptyDrawable;
    public static TextPaint chat_audioPerformerPaint;
    public static TextPaint chat_audioTimePaint;
    public static TextPaint chat_audioTitlePaint;
    public static TextPaint chat_botButtonPaint;
    public static Drawable chat_botCardDrawable;
    public static Drawable chat_botInlineDrawable;
    public static Drawable chat_botInviteDrawable;
    public static Drawable chat_botLinkDrawable;
    public static Drawable chat_botLockDrawable;
    public static Drawable chat_botWebViewDrawable;
    public static Drawable chat_channelIconDrawable;
    public static Drawable chat_closeIconDrawable;
    public static Drawable chat_commentArrowDrawable;
    public static Drawable chat_commentDrawable;
    public static Drawable chat_commentStickerDrawable;
    public static TextPaint chat_commentTextPaint;
    public static Paint chat_composeBackgroundPaint;
    public static Drawable chat_composeShadowDrawable;
    public static Drawable chat_composeShadowRoundDrawable;
    public static Drawable[] chat_contactDrawable;
    public static TextPaint chat_contactNamePaint;
    public static TextPaint chat_contactPhonePaint;
    public static TextPaint chat_contextResult_descriptionTextPaint;
    public static Drawable chat_contextResult_shadowUnderSwitchDrawable;
    public static TextPaint chat_contextResult_titleTextPaint;
    public static Paint chat_deleteProgressPaint;
    public static Paint chat_docBackPaint;
    public static TextPaint chat_docNamePaint;
    public static TextPaint chat_durationPaint;
    public static TextPaint chat_ephemeralPaint;
    public static TextPaint chat_explanationTextPaint;
    public static Path[] chat_filePath;
    public static Drawable[][] chat_fileStatesDrawable;
    public static Drawable chat_flameIcon;
    public static TextPaint chat_forwardNamePaint;
    public static TextPaint chat_gamePaint;
    public static Drawable chat_gifIcon;
    public static Drawable chat_goIconDrawable;
    public static Drawable chat_gradientLeftDrawable;
    public static Drawable chat_gradientRightDrawable;
    public static TextPaint chat_infoBoldPaint;
    public static TextPaint chat_infoPaint;
    public static Drawable chat_inlineResultAudio;
    public static Drawable chat_inlineResultFile;
    public static Drawable chat_inlineResultLocation;
    public static Paint chat_instantViewButtonPaint;
    public static TextPaint chat_instantViewPaint;
    public static Paint chat_instantViewRectPaint;
    public static TextPaint chat_livePaint;
    public static Drawable chat_livePhoto;
    public static TextPaint chat_locationAddressPaint;
    public static Drawable[] chat_locationDrawable;
    public static TextPaint chat_locationTitlePaint;
    public static Drawable chat_lockIconDrawable;
    public static Paint chat_messageBackgroundSelectedPaint;
    public static Drawable chat_moreIconDrawable;
    private static AudioVisualizerDrawable chat_msgAudioVisualizeDrawable;
    public static TextPaint chat_msgBotButtonPaint;
    public static Drawable chat_msgCallDownGreenDrawable;
    public static Drawable chat_msgCallDownRedDrawable;
    public static Drawable chat_msgCallUpGreenDrawable;
    public static MsgClockDrawable chat_msgClockDrawable;
    public static TextPaint chat_msgCodeBgPaint;
    public static Drawable chat_msgErrorDrawable;
    public static Paint chat_msgErrorPaint;
    public static TextPaint chat_msgGameTextPaint;
    public static Drawable[] chat_msgInCallDrawable;
    public static Drawable[] chat_msgInCallSelectedDrawable;
    public static MessageDrawable chat_msgInDrawable;
    public static Drawable chat_msgInInstantDrawable;
    public static MessageDrawable chat_msgInMediaDrawable;
    public static MessageDrawable chat_msgInMediaSelectedDrawable;
    public static Drawable chat_msgInMenuDrawable;
    public static Drawable chat_msgInMenuSelectedDrawable;
    public static Drawable chat_msgInPinnedDrawable;
    public static Drawable chat_msgInPinnedSelectedDrawable;
    public static Drawable chat_msgInRepliesDrawable;
    public static Drawable chat_msgInRepliesSelectedDrawable;
    public static MessageDrawable chat_msgInSelectedDrawable;
    public static Drawable chat_msgInViewsDrawable;
    public static Drawable chat_msgInViewsSelectedDrawable;
    public static Drawable chat_msgMediaCheckDrawable;
    public static Drawable chat_msgMediaHalfCheckDrawable;
    public static Drawable chat_msgMediaMenuDrawable;
    public static Drawable chat_msgMediaPinnedDrawable;
    public static Drawable chat_msgMediaRepliesDrawable;
    public static Drawable chat_msgMediaViewsDrawable;
    public static Drawable chat_msgNoSoundDrawable;
    public static Drawable[] chat_msgOutCallDrawable;
    public static Drawable[] chat_msgOutCallSelectedDrawable;
    public static Drawable chat_msgOutCheckDrawable;
    public static Drawable chat_msgOutCheckReadDrawable;
    public static Drawable chat_msgOutCheckReadSelectedDrawable;
    public static Drawable chat_msgOutCheckSelectedDrawable;
    public static MessageDrawable chat_msgOutDrawable;
    public static Drawable chat_msgOutHalfCheckDrawable;
    public static Drawable chat_msgOutHalfCheckSelectedDrawable;
    public static Drawable chat_msgOutInstantDrawable;
    public static MessageDrawable chat_msgOutMediaDrawable;
    public static MessageDrawable chat_msgOutMediaSelectedDrawable;
    public static Drawable chat_msgOutMenuDrawable;
    public static Drawable chat_msgOutMenuSelectedDrawable;
    public static Drawable chat_msgOutPinnedDrawable;
    public static Drawable chat_msgOutPinnedSelectedDrawable;
    public static Drawable chat_msgOutRepliesDrawable;
    public static Drawable chat_msgOutRepliesSelectedDrawable;
    public static MessageDrawable chat_msgOutSelectedDrawable;
    public static Drawable chat_msgOutViewsDrawable;
    public static Drawable chat_msgOutViewsSelectedDrawable;
    public static Drawable chat_msgStickerCheckDrawable;
    public static Drawable chat_msgStickerHalfCheckDrawable;
    public static Drawable chat_msgStickerPinnedDrawable;
    public static Drawable chat_msgStickerRepliesDrawable;
    public static Drawable chat_msgStickerViewsDrawable;
    public static TextPaint chat_msgTextCode2Paint;
    public static TextPaint chat_msgTextCode3Paint;
    public static TextPaint chat_msgTextCodePaint;
    public static TextPaint chat_msgTextPaint;
    public static TextPaint[] chat_msgTextPaintEmoji;
    public static TextPaint chat_msgTextPaintOneEmoji;
    public static TextPaint chat_msgTextPaintThreeEmoji;
    public static TextPaint chat_msgTextPaintTwoEmoji;
    public static Drawable chat_msgUnlockDrawable;
    public static Drawable chat_muteIconDrawable;
    public static TextPaint chat_namePaint;
    public static PorterDuffColorFilter chat_outAnimatedEmojiTextColorFilter;
    public static Paint chat_outUrlPaint;
    public static Drawable chat_pencilIconDrawable;
    public static Drawable chat_pluginIcon;
    public static Drawable[] chat_pollCheckDrawable;
    public static Drawable[] chat_pollCrossDrawable;
    public static Drawable[] chat_pollHintDrawable;
    public static Paint chat_pollTimerPaint;
    public static Drawable[] chat_psaHelpDrawable;
    public static TextPaint chat_quoteTextPaint;
    public static Paint chat_radialProgress2Paint;
    public static Paint chat_radialProgressPaint;
    public static Paint chat_radialProgressPausedPaint;
    public static Paint chat_radialProgressPausedSeekbarPaint;
    public static Drawable chat_replyIconDrawable;
    public static Paint chat_replyLinePaint;
    public static TextPaint chat_replyNamePaint;
    public static TextPaint chat_replyTextPaint;
    public static Drawable chat_roundVideoShadow;
    public static Drawable chat_settingsIcon;
    public static Drawable chat_shareIconDrawable;
    public static TextPaint chat_shipmentPaint;
    public static Paint chat_statusPaint;
    public static Paint chat_statusRecordPaint;
    private static StatusDrawable[] chat_status_drawables;
    public static TextPaint chat_stickerCommentCountPaint;
    public static Drawable chat_stickersIcon;
    public static Paint chat_textSearchSelectionPaint;
    public static Paint chat_timeBackgroundPaint;
    public static TextPaint chat_timePaint;
    public static TextPaint chat_titleLabelTextPaint;
    public static TextPaint chat_topicTextPaint;
    public static TextPaint chat_unlockExtendedMediaTextPaint;
    public static Path[] chat_updatePath;
    public static Paint chat_urlPaint;
    public static Paint chat_videoProgressPaint;
    public static Paint checkboxSquare_backgroundPaint;
    public static Paint checkboxSquare_checkPaint;
    public static Paint checkboxSquare_eraserPaint;
    public static int colorsCount;
    public static int currentColor;
    private static SparseIntArray currentColors;
    private static SparseIntArray currentColorsNoAccent;
    private static ThemeInfo currentDayTheme;
    private static ThemeInfo currentNightTheme;
    private static ThemeInfo currentTheme;
    private static final HashMap<String, Integer> defaultChatDrawableColorKeys = null;
    private static final HashMap<String, Drawable> defaultChatDrawables = null;
    private static final HashMap<String, Integer> defaultChatPaintColors = null;
    private static final HashMap<String, Paint> defaultChatPaints = null;
    private static int[] defaultColors;
    private static ThemeInfo defaultTheme;
    public static final int default_shadow_color = 0;
    public static Paint dialogs_actionMessagePaint;
    public static RLottieDrawable dialogs_archiveAvatarDrawable;
    public static boolean dialogs_archiveAvatarDrawableRecolored;
    public static RLottieDrawable dialogs_archiveDrawable;
    public static boolean dialogs_archiveDrawableRecolored;
    public static TextPaint dialogs_archiveTextPaint;
    public static TextPaint dialogs_archiveTextPaintSmall;
    public static Drawable dialogs_checkDrawable;
    public static Drawable dialogs_checkReadDrawable;
    public static Drawable dialogs_clockDrawable;
    public static Drawable dialogs_communityCardsDrawable;
    public static Paint dialogs_countGrayPaint;
    public static Paint dialogs_countPaint;
    public static TextPaint dialogs_countTextPaint;
    public static TextPaint dialogs_countTextPaint2;
    public static Drawable dialogs_errorDrawable;
    public static Paint dialogs_errorPaint;
    public static ScamDrawable dialogs_fakeDrawable;
    public static Drawable dialogs_forum_arrowDrawable;
    public static Drawable dialogs_halfCheckDrawable;
    public static Drawable dialogs_hiddenDrawable;
    public static RLottieDrawable dialogs_hidePsaDrawable;
    public static boolean dialogs_hidePsaDrawableRecolored;
    public static Drawable dialogs_holidayDrawable;
    private static int dialogs_holidayDrawableOffsetX;
    private static int dialogs_holidayDrawableOffsetY;
    public static Drawable dialogs_lock2Drawable;
    public static Drawable dialogs_lockDrawable;
    public static Drawable dialogs_mentionDrawable;
    public static Drawable dialogs_mentionDrawableMuted;
    public static TextPaint dialogs_messageNamePaint;
    public static TextPaint[] dialogs_messagePaint;
    public static TextPaint[] dialogs_messagePrintingPaint;
    public static Drawable dialogs_muteDrawable;
    public static TextPaint[] dialogs_nameEncryptedPaint;
    public static TextPaint[] dialogs_namePaint;
    public static TextPaint dialogs_offlinePaint;
    public static Paint dialogs_onlineCirclePaint;
    public static TextPaint dialogs_onlinePaint;
    public static RLottieDrawable dialogs_pinArchiveDrawable;
    public static Drawable dialogs_pinnedDrawable;
    public static Drawable dialogs_pinnedDrawable2;
    public static Drawable dialogs_pinnedDrawable2Accent;
    public static Paint dialogs_pinnedPaint;
    public static Drawable dialogs_playDrawable;
    public static Drawable dialogs_pollMentionDrawable;
    public static Drawable dialogs_pollMentionDrawableMuted;
    public static Drawable dialogs_reactionsMentionDrawable;
    public static Drawable dialogs_reactionsMentionDrawableMuted;
    public static Drawable dialogs_reorderDrawable;
    public static ScamDrawable dialogs_scamDrawable;
    public static TextPaint dialogs_searchNameEncryptedPaint;
    public static TextPaint dialogs_searchNamePaint;
    public static RLottieDrawable dialogs_swipeCommunityUngroup;
    public static RLottieDrawable dialogs_swipeDeleteDrawable;
    public static RLottieDrawable dialogs_swipeMuteDrawable;
    public static RLottieDrawable dialogs_swipePinDrawable;
    public static RLottieDrawable dialogs_swipeReadDrawable;
    public static RLottieDrawable dialogs_swipeUnmuteDrawable;
    public static RLottieDrawable dialogs_swipeUnpinDrawable;
    public static RLottieDrawable dialogs_swipeUnreadDrawable;
    public static Paint dialogs_tabletSeletedPaint;
    public static Paint dialogs_tagPaint;
    public static TextPaint dialogs_tagTextPaint;
    public static TextPaint dialogs_timePaint;
    public static TextPaint dialogs_timePaintBold;
    public static TextPaint dialogs_timePaintBoldAccent;
    public static RLottieDrawable dialogs_unarchiveDrawable;
    public static Drawable dialogs_unmuteDrawable;
    public static RLottieDrawable dialogs_unpinArchiveDrawable;
    public static Drawable dialogs_verifiedCheckDrawable;
    public static Drawable dialogs_verifiedDrawable;
    public static boolean disallowChangeServiceMessageColor;
    public static Paint dividerExtraPaint;
    public static Paint dividerPaint;
    private static SparseIntArray fallbackKeys;
    public static Paint forcedDividerPaint;
    private static FragmentContextViewWavesDrawable fragmentContextViewWavesDrawable;
    private static boolean hasPreviousTheme;
    private static final ThreadLocal<float[]> hsvTemp1Local = null;
    private static final ThreadLocal<float[]> hsvTemp2Local = null;
    private static final ThreadLocal<float[]> hsvTemp3Local = null;
    private static final ThreadLocal<float[]> hsvTemp4Local = null;
    private static final ThreadLocal<float[]> hsvTemp5Local = null;
    private static boolean isApplyingAccent;
    private static boolean isCustomTheme;
    private static boolean isInNigthMode;
    private static boolean isPatternWallpaper;
    private static boolean isWallpaperMotion;
    public static final int key_actionBarActionModeDefault = 0;
    public static final int key_actionBarActionModeDefaultIcon = 0;
    public static final int key_actionBarActionModeDefaultSelector = 0;
    public static final int key_actionBarActionModeDefaultTop = 0;
    public static final int key_actionBarActionModeReaction = 0;
    public static final int key_actionBarActionModeReactionDot = 0;
    public static final int key_actionBarActionModeReactionText = 0;
    public static final int key_actionBarBrowser = 0;
    public static final int key_actionBarDefault = 0;
    public static final int key_actionBarDefaultArchived = 0;
    public static final int key_actionBarDefaultArchivedIcon = 0;
    public static final int key_actionBarDefaultArchivedSearch = 0;
    public static final int key_actionBarDefaultArchivedSearchPlaceholder = 0;
    public static final int key_actionBarDefaultArchivedSelector = 0;
    public static final int key_actionBarDefaultArchivedTitle = 0;
    public static final int key_actionBarDefaultIcon = 0;
    public static final int key_actionBarDefaultSearch = 0;
    public static final int key_actionBarDefaultSearchPlaceholder = 0;
    public static final int key_actionBarDefaultSelector = 0;
    public static final int key_actionBarDefaultSubmenuBackground = 0;
    public static final int key_actionBarDefaultSubmenuItem = 0;
    public static final int key_actionBarDefaultSubmenuItemIcon = 0;
    public static final int key_actionBarDefaultSubmenuSeparator = 0;
    public static final int key_actionBarDefaultSubtitle = 0;
    public static final int key_actionBarDefaultTitle = 0;
    public static final int key_actionBarTabActiveText = 0;
    public static final int key_actionBarTabLine = 0;
    public static final int key_actionBarTabSelector = 0;
    public static final int key_actionBarTabUnactiveText = 0;
    public static final int key_actionBarWhiteSelector = 0;
    public static final int key_avatar_actionBarIconBlue = 0;
    public static final int key_avatar_actionBarSelectorBlue = 0;
    public static final int key_avatar_background2Blue = 0;
    public static final int key_avatar_background2Cyan = 0;
    public static final int key_avatar_background2Green = 0;
    public static final int key_avatar_background2Orange = 0;
    public static final int key_avatar_background2Pink = 0;
    public static final int key_avatar_background2Red = 0;
    public static final int key_avatar_background2Saved = 0;
    public static final int key_avatar_background2Violet = 0;
    public static final int key_avatar_backgroundActionBarBlue = 0;
    public static final int key_avatar_backgroundArchived = 0;
    public static final int key_avatar_backgroundArchivedHidden = 0;
    public static final int key_avatar_backgroundBlue = 0;
    public static final int key_avatar_backgroundCyan = 0;
    public static final int key_avatar_backgroundGray = 0;
    public static final int key_avatar_backgroundGreen = 0;
    public static final int key_avatar_backgroundInProfileBlue = 0;
    public static final int key_avatar_backgroundOrange = 0;
    public static final int key_avatar_backgroundPink = 0;
    public static final int key_avatar_backgroundRed = 0;
    public static final int key_avatar_backgroundSaved = 0;
    public static final int key_avatar_backgroundViolet = 0;
    public static final int key_avatar_nameInMessageBlue = 0;
    public static final int key_avatar_nameInMessageCyan = 0;
    public static final int key_avatar_nameInMessageGreen = 0;
    public static final int key_avatar_nameInMessageOrange = 0;
    public static final int key_avatar_nameInMessagePink = 0;
    public static final int key_avatar_nameInMessageRed = 0;
    public static final int key_avatar_nameInMessageViolet = 0;
    public static final int key_avatar_subtitleInProfileBlue = 0;
    public static final int key_avatar_text = 0;
    public static final int key_botKeyboard_button_danger = 0;
    public static final int key_botKeyboard_button_primary = 0;
    public static final int key_botKeyboard_button_success = 0;
    public static final int key_bot_loadingIcon = 0;
    public static final int key_buttonNeutral = 0;
    public static final int key_buttonNeutralText = 0;
    public static final int key_calls_callReceivedGreenIcon = 0;
    public static final int key_calls_callReceivedRedIcon = 0;
    public static final int key_changephoneinfo_image2 = 0;
    public static final int key_chat_BlurAlpha = 0;
    public static final int key_chat_BlurAlphaSlow = 0;
    public static final int key_chat_TextSelectionCursor = 0;
    public static final int key_chat_addContact = 0;
    public static final int key_chat_attachActiveTab = 0;
    public static final int key_chat_attachAudioBackground = 0;
    public static final int key_chat_attachCheckBoxBackground = 0;
    public static final int key_chat_attachCheckBoxCheck = 0;
    public static final int key_chat_attachContactBackground = 0;
    public static final int key_chat_attachContactText = 0;
    public static final int key_chat_attachEmptyImage = 0;
    public static final int key_chat_attachGalleryBackground = 0;
    public static final int key_chat_attachIcon = 0;
    public static final int key_chat_attachLocationBackground = 0;
    public static final int key_chat_attachPermissionImage = 0;
    public static final int key_chat_attachPermissionMark = 0;
    public static final int key_chat_attachPermissionText = 0;
    public static final int key_chat_attachPhotoBackground = 0;
    public static final int key_chat_attachPollBackground = 0;
    public static final int key_chat_attachUnactiveTab = 0;
    public static final int key_chat_botButtonText = 0;
    public static final int key_chat_botKeyboardButtonBackground = 0;
    public static final int key_chat_botKeyboardButtonBackgroundPressed = 0;
    public static final int key_chat_botKeyboardButtonText = 0;
    public static final int key_chat_botSwitchToInlineText = 0;
    public static final int key_chat_editMediaButton = 0;
    public static final int key_chat_emojiBottomPanelIcon = 0;
    public static final int key_chat_emojiPanelBackground = 0;
    public static final int key_chat_emojiPanelBackspace = 0;
    public static final int key_chat_emojiPanelEmptyText = 0;
    public static final int key_chat_emojiPanelIcon = 0;
    public static final int key_chat_emojiPanelIconSelected = 0;
    public static final int key_chat_emojiPanelNewTrending = 0;
    public static final int key_chat_emojiPanelShadowLine = 0;
    public static final int key_chat_emojiPanelStickerPackSelector = 0;
    public static final int key_chat_emojiPanelStickerPackSelectorLine = 0;
    public static final int key_chat_emojiPanelStickerSetName = 0;
    public static final int key_chat_emojiPanelStickerSetNameHighlight = 0;
    public static final int key_chat_emojiPanelStickerSetNameIcon = 0;
    public static final int key_chat_emojiPanelTrendingDescription = 0;
    public static final int key_chat_emojiPanelTrendingTitle = 0;
    public static final int key_chat_emojiSearchBackground = 0;
    public static final int key_chat_emojiSearchIcon = 0;
    public static final int key_chat_fieldOverlayText = 0;
    public static final int key_chat_gifSaveHintBackground = 0;
    public static final int key_chat_gifSaveHintText = 0;
    public static final int key_chat_goDownButton = 0;
    public static final int key_chat_goDownButtonCounter = 0;
    public static final int key_chat_goDownButtonCounterBackground = 0;
    public static final int key_chat_inAdminSelectedText = 0;
    public static final int key_chat_inAdminText = 0;
    public static final int key_chat_inArticleCodeBackground = 0;
    public static final int key_chat_inArticleCodeScrollbar = 0;
    public static final int key_chat_inArticleCodeScrollbarBackground = 0;
    public static final int key_chat_inArticleDetailsArrow = 0;
    public static final int key_chat_inArticleDetailsLine = 0;
    public static final int key_chat_inAudioCacheSeekbar = 0;
    public static final int key_chat_inAudioDurationSelectedText = 0;
    public static final int key_chat_inAudioDurationText = 0;
    public static final int key_chat_inAudioPerformerSelectedText = 0;
    public static final int key_chat_inAudioPerformerText = 0;
    public static final int key_chat_inAudioProgress = 0;
    public static final int key_chat_inAudioSeekbar = 0;
    public static final int key_chat_inAudioSeekbarFill = 0;
    public static final int key_chat_inAudioSeekbarSelected = 0;
    public static final int key_chat_inAudioSelectedProgress = 0;
    public static final int key_chat_inAudioTitleText = 0;
    public static final int key_chat_inBubble = 0;
    public static final int key_chat_inBubbleLocationPlaceholder = 0;
    public static final int key_chat_inBubbleSelected = 0;
    public static final int key_chat_inBubbleSelectedOverlay = 0;
    public static final int key_chat_inBubbleShadow = 0;
    public static final int key_chat_inCodeBackground = 0;
    public static final int key_chat_inContactBackground = 0;
    public static final int key_chat_inContactIcon = 0;
    public static final int key_chat_inContactNameText = 0;
    public static final int key_chat_inContactPhoneSelectedText = 0;
    public static final int key_chat_inContactPhoneText = 0;
    public static final int key_chat_inDivider = 0;
    public static final int key_chat_inFileBackground = 0;
    public static final int key_chat_inFileBackgroundSelected = 0;
    public static final int key_chat_inFileInfoSelectedText = 0;
    public static final int key_chat_inFileInfoText = 0;
    public static final int key_chat_inFileNameText = 0;
    public static final int key_chat_inFileProgress = 0;
    public static final int key_chat_inFileProgressSelected = 0;
    public static final int key_chat_inForwardedNameText = 0;
    public static final int key_chat_inGreenCall = 0;
    public static final int key_chat_inInstant = 0;
    public static final int key_chat_inInstantSelected = 0;
    public static final int key_chat_inLoader = 0;
    public static final int key_chat_inLoaderPhoto = 0;
    public static final int key_chat_inLoaderSelected = 0;
    public static final int key_chat_inLocationBackground = 0;
    public static final int key_chat_inLocationIcon = 0;
    public static final int key_chat_inMediaIcon = 0;
    public static final int key_chat_inMediaIconSelected = 0;
    public static final int key_chat_inMenu = 0;
    public static final int key_chat_inMenuSelected = 0;
    public static final int key_chat_inPollCorrectAnswer = 0;
    public static final int key_chat_inPollWrongAnswer = 0;
    public static final int key_chat_inPreviewInstantText = 0;
    public static final int key_chat_inPreviewLine = 0;
    public static final int key_chat_inPsaNameText = 0;
    public static final int key_chat_inQuote = 0;
    public static final int key_chat_inReactionButtonBackground = 0;
    public static final int key_chat_inReactionButtonText = 0;
    public static final int key_chat_inReactionButtonTextSelected = 0;
    public static final int key_chat_inReplyLine = 0;
    public static final int key_chat_inReplyMediaMessageSelectedText = 0;
    public static final int key_chat_inReplyMediaMessageText = 0;
    public static final int key_chat_inReplyMessageText = 0;
    public static final int key_chat_inReplyNameText = 0;
    public static final int key_chat_inSentClock = 0;
    public static final int key_chat_inSentClockSelected = 0;
    public static final int key_chat_inSiteNameText = 0;
    public static final int key_chat_inTableBackground = 0;
    public static final int key_chat_inTableBorder = 0;
    public static final int key_chat_inTextSelectionHighlight = 0;
    public static final int key_chat_inTimeSelectedText = 0;
    public static final int key_chat_inTimeText = 0;
    public static final int key_chat_inVenueInfoSelectedText = 0;
    public static final int key_chat_inVenueInfoText = 0;
    public static final int key_chat_inViaBotNameText = 0;
    public static final int key_chat_inViews = 0;
    public static final int key_chat_inViewsSelected = 0;
    public static final int key_chat_inVoiceSeekbar = 0;
    public static final int key_chat_inVoiceSeekbarFill = 0;
    public static final int key_chat_inVoiceSeekbarSelected = 0;
    public static final int key_chat_inlineResultIcon = 0;
    public static final int key_chat_linkSelectBackground = 0;
    public static final int key_chat_lockIcon = 0;
    public static final int key_chat_mediaInfoText = 0;
    public static final int key_chat_mediaLoaderPhoto = 0;
    public static final int key_chat_mediaLoaderPhotoIcon = 0;
    public static final int key_chat_mediaLoaderPhotoIconSelected = 0;
    public static final int key_chat_mediaLoaderPhotoSelected = 0;
    public static final int key_chat_mediaMenu = 0;
    public static final int key_chat_mediaProgress = 0;
    public static final int key_chat_mediaSentCheck = 0;
    public static final int key_chat_mediaSentClock = 0;
    public static final int key_chat_mediaTimeBackground = 0;
    public static final int key_chat_mediaTimeText = 0;
    public static final int key_chat_mediaViews = 0;
    public static final int key_chat_messageLinkIn = 0;
    public static final int key_chat_messageLinkOut = 0;
    public static final int key_chat_messagePanelBackground = 0;
    public static final int key_chat_messagePanelCancelInlineBot = 0;
    public static final int key_chat_messagePanelCursor = 0;
    public static final int key_chat_messagePanelHint = 0;
    public static final int key_chat_messagePanelIcons = 0;
    public static final int key_chat_messagePanelSend = 0;
    public static final int key_chat_messagePanelShadow = 0;
    public static final int key_chat_messagePanelText = 0;
    public static final int key_chat_messagePanelVoiceBackground = 0;
    public static final int key_chat_messagePanelVoiceDelete = 0;
    public static final int key_chat_messagePanelVoiceDuration = 0;
    public static final int key_chat_messagePanelVoiceLock = 0;
    public static final int key_chat_messagePanelVoiceLockBackground = 0;
    public static final int key_chat_messagePanelVoiceLockShadow = 0;
    public static final int key_chat_messagePanelVoicePressed = 0;
    public static final int key_chat_messageTextIn = 0;
    public static final int key_chat_messageTextOut = 0;
    public static final int key_chat_msgIvButtonDangerIn = 0;
    public static final int key_chat_msgIvButtonDangerInPressed = 0;
    public static final int key_chat_msgIvButtonDangerInText = 0;
    public static final int key_chat_msgIvButtonDangerOut = 0;
    public static final int key_chat_msgIvButtonDangerOutPressed = 0;
    public static final int key_chat_msgIvButtonDangerOutText = 0;
    public static final int key_chat_msgIvButtonDefaultIn = 0;
    public static final int key_chat_msgIvButtonDefaultInPressed = 0;
    public static final int key_chat_msgIvButtonDefaultInText = 0;
    public static final int key_chat_msgIvButtonDefaultInlineIn = 0;
    public static final int key_chat_msgIvButtonDefaultInlineInPressed = 0;
    public static final int key_chat_msgIvButtonDefaultInlineInText = 0;
    public static final int key_chat_msgIvButtonDefaultInlineOut = 0;
    public static final int key_chat_msgIvButtonDefaultInlineOutPressed = 0;
    public static final int key_chat_msgIvButtonDefaultInlineOutText = 0;
    public static final int key_chat_msgIvButtonDefaultOut = 0;
    public static final int key_chat_msgIvButtonDefaultOutPressed = 0;
    public static final int key_chat_msgIvButtonDefaultOutText = 0;
    public static final int key_chat_msgIvButtonPrimaryIn = 0;
    public static final int key_chat_msgIvButtonPrimaryInPressed = 0;
    public static final int key_chat_msgIvButtonPrimaryInText = 0;
    public static final int key_chat_msgIvButtonPrimaryOut = 0;
    public static final int key_chat_msgIvButtonPrimaryOutPressed = 0;
    public static final int key_chat_msgIvButtonPrimaryOutText = 0;
    public static final int key_chat_msgIvButtonSuccessIn = 0;
    public static final int key_chat_msgIvButtonSuccessInPressed = 0;
    public static final int key_chat_msgIvButtonSuccessInText = 0;
    public static final int key_chat_msgIvButtonSuccessOut = 0;
    public static final int key_chat_msgIvButtonSuccessOutPressed = 0;
    public static final int key_chat_msgIvButtonSuccessOutText = 0;
    public static final int key_chat_muteIcon = 0;
    public static final int key_chat_outAdminSelectedText = 0;
    public static final int key_chat_outAdminText = 0;
    public static final int key_chat_outArticleCodeBackground = 0;
    public static final int key_chat_outArticleCodeScrollbar = 0;
    public static final int key_chat_outArticleCodeScrollbarBackground = 0;
    public static final int key_chat_outArticleDetailsArrow = 0;
    public static final int key_chat_outArticleDetailsLine = 0;
    public static final int key_chat_outAudioCacheSeekbar = 0;
    public static final int key_chat_outAudioDurationSelectedText = 0;
    public static final int key_chat_outAudioDurationText = 0;
    public static final int key_chat_outAudioPerformerSelectedText = 0;
    public static final int key_chat_outAudioPerformerText = 0;
    public static final int key_chat_outAudioProgress = 0;
    public static final int key_chat_outAudioSeekbar = 0;
    public static final int key_chat_outAudioSeekbarFill = 0;
    public static final int key_chat_outAudioSeekbarSelected = 0;
    public static final int key_chat_outAudioSelectedProgress = 0;
    public static final int key_chat_outAudioTitleText = 0;
    public static final int key_chat_outBubble = 0;
    public static final int key_chat_outBubbleGradient1 = 0;
    public static final int key_chat_outBubbleGradient2 = 0;
    public static final int key_chat_outBubbleGradient3 = 0;
    public static final int key_chat_outBubbleGradientAnimated = 0;
    public static final int key_chat_outBubbleGradientSelectedOverlay = 0;
    public static final int key_chat_outBubbleLocationPlaceholder = 0;
    public static final int key_chat_outBubbleSelected = 0;
    public static final int key_chat_outBubbleSelectedOverlay = 0;
    public static final int key_chat_outBubbleShadow = 0;
    public static final int key_chat_outCodeBackground = 0;
    public static final int key_chat_outContactBackground = 0;
    public static final int key_chat_outContactIcon = 0;
    public static final int key_chat_outContactNameText = 0;
    public static final int key_chat_outContactPhoneSelectedText = 0;
    public static final int key_chat_outContactPhoneText = 0;
    public static final int key_chat_outDivider = 0;
    public static final int key_chat_outFileBackground = 0;
    public static final int key_chat_outFileBackgroundSelected = 0;
    public static final int key_chat_outFileInfoSelectedText = 0;
    public static final int key_chat_outFileInfoText = 0;
    public static final int key_chat_outFileNameText = 0;
    public static final int key_chat_outFileProgress = 0;
    public static final int key_chat_outFileProgressSelected = 0;
    public static final int key_chat_outForwardedNameText = 0;
    public static final int key_chat_outGreenCall = 0;
    public static final int key_chat_outInstant = 0;
    public static final int key_chat_outInstantSelected = 0;
    public static final int key_chat_outLinkSelectBackground = 0;
    public static final int key_chat_outLoader = 0;
    public static final int key_chat_outLoaderSelected = 0;
    public static final int key_chat_outLocationIcon = 0;
    public static final int key_chat_outMediaIcon = 0;
    public static final int key_chat_outMediaIconSelected = 0;
    public static final int key_chat_outMenu = 0;
    public static final int key_chat_outMenuSelected = 0;
    public static final int key_chat_outPollCorrectAnswer = 0;
    public static final int key_chat_outPollWrongAnswer = 0;
    public static final int key_chat_outPreviewInstantText = 0;
    public static final int key_chat_outPreviewLine = 0;
    public static final int key_chat_outPsaNameText = 0;
    public static final int key_chat_outQuote = 0;
    public static final int key_chat_outReactionButtonBackground = 0;
    public static final int key_chat_outReactionButtonText = 0;
    public static final int key_chat_outReactionButtonTextSelected = 0;
    public static final int key_chat_outReplyLine = 0;
    public static final int key_chat_outReplyLine2 = 0;
    public static final int key_chat_outReplyMediaMessageSelectedText = 0;
    public static final int key_chat_outReplyMediaMessageText = 0;
    public static final int key_chat_outReplyMessageText = 0;
    public static final int key_chat_outReplyNameText = 0;
    public static final int key_chat_outSentCheck = 0;
    public static final int key_chat_outSentCheckRead = 0;
    public static final int key_chat_outSentCheckReadSelected = 0;
    public static final int key_chat_outSentCheckSelected = 0;
    public static final int key_chat_outSentClock = 0;
    public static final int key_chat_outSentClockSelected = 0;
    public static final int key_chat_outSiteNameText = 0;
    public static final int key_chat_outTableBackground = 0;
    public static final int key_chat_outTableBorder = 0;
    public static final int key_chat_outTextSelectionCursor = 0;
    public static final int key_chat_outTextSelectionHighlight = 0;
    public static final int key_chat_outTimeSelectedText = 0;
    public static final int key_chat_outTimeText = 0;
    public static final int key_chat_outVenueInfoSelectedText = 0;
    public static final int key_chat_outVenueInfoText = 0;
    public static final int key_chat_outViaBotNameText = 0;
    public static final int key_chat_outViews = 0;
    public static final int key_chat_outViewsSelected = 0;
    public static final int key_chat_outVoiceSeekbar = 0;
    public static final int key_chat_outVoiceSeekbarFill = 0;
    public static final int key_chat_outVoiceSeekbarSelected = 0;
    public static final int key_chat_previewDurationText = 0;
    public static final int key_chat_previewGameText = 0;
    public static final int key_chat_reactionServiceButtonBackgroundSelected = 0;
    public static final int key_chat_reactionServiceButtonTextSelected = 0;
    public static final int key_chat_recordTime = 0;
    public static final int key_chat_recordVoiceCancel = 0;
    public static final int key_chat_recordedVoiceBackground = 0;
    public static final int key_chat_recordedVoiceDarkerBackground = 0;
    public static final int key_chat_recordedVoiceDot = 0;
    public static final int key_chat_recordedVoicePlayPause = 0;
    public static final int key_chat_recordedVoiceProgress = 0;
    public static final int key_chat_recordedVoiceProgressInner = 0;
    public static final int key_chat_replyPanelClose = 0;
    public static final int key_chat_replyPanelIcons = 0;
    public static final int key_chat_replyPanelLine = 0;
    public static final int key_chat_replyPanelName = 0;
    public static final int key_chat_searchPanelIcons = 0;
    public static final int key_chat_searchPanelText = 0;
    public static final int key_chat_secretChatStatusText = 0;
    public static final int key_chat_secretTimeText = 0;
    public static final int key_chat_selectedBackground = 0;
    public static final int key_chat_sentError = 0;
    public static final int key_chat_sentErrorIcon = 0;
    public static final int key_chat_serviceBackground = 0;
    public static final int key_chat_serviceBackgroundSelected = 0;
    public static final int key_chat_serviceBackgroundSelector = 0;
    public static final int key_chat_serviceIcon = 0;
    public static final int key_chat_serviceLink = 0;
    public static final int key_chat_serviceText = 0;
    public static final int key_chat_status = 0;
    public static final int key_chat_stickerNameText = 0;
    public static final int key_chat_stickerReplyLine = 0;
    public static final int key_chat_stickerReplyMessageText = 0;
    public static final int key_chat_stickerReplyNameText = 0;
    public static final int key_chat_stickerViaBotNameText = 0;
    public static final int key_chat_stickersHintPanel = 0;
    public static final int key_chat_tagAdmin = 0;
    public static final int key_chat_tagCreator = 0;
    public static final int key_chat_textSelectBackground = 0;
    public static final int key_chat_topPanelBackground = 0;
    public static final int key_chat_topPanelClose = 0;
    public static final int key_chat_topPanelLine = 0;
    public static final int key_chat_topPanelMessage = 0;
    public static final int key_chat_topPanelTitle = 0;
    public static final int key_chat_unreadMessagesStartArrowIcon = 0;
    public static final int key_chat_unreadMessagesStartBackground = 0;
    public static final int key_chat_unreadMessagesStartText = 0;
    public static final int key_chat_wallpaper = 0;
    public static final int key_chat_wallpaper_gradient_rotation = 0;
    public static final int key_chat_wallpaper_gradient_to1 = 0;
    public static final int key_chat_wallpaper_gradient_to2 = 0;
    public static final int key_chat_wallpaper_gradient_to3 = 0;
    public static final int key_chats_actionBackground = 0;
    public static final int key_chats_actionIcon = 0;
    public static final int key_chats_actionMessage = 0;
    public static final int key_chats_actionPressedBackground = 0;
    public static final int key_chats_archiveBackground = 0;
    public static final int key_chats_archiveIcon = 0;
    public static final int key_chats_archivePinBackground = 0;
    public static final int key_chats_archivePullDownBackground = 0;
    public static final int key_chats_archivePullDownBackgroundActive = 0;
    public static final int key_chats_archiveText = 0;
    public static final int key_chats_attachMessage = 0;
    public static final int key_chats_date = 0;
    public static final int key_chats_date_bold = 0;
    public static final int key_chats_draft = 0;
    public static final int key_chats_mentionIcon = 0;
    public static final int key_chats_menuBackground = 0;
    public static final int key_chats_menuItemCheck = 0;
    public static final int key_chats_menuItemIcon = 0;
    public static final int key_chats_menuItemText = 0;
    public static final int key_chats_menuName = 0;
    public static final int key_chats_menuPhone = 0;
    public static final int key_chats_menuPhoneCats = 0;
    public static final int key_chats_menuTopBackground = 0;
    public static final int key_chats_menuTopBackgroundCats = 0;
    public static final int key_chats_menuTopShadow = 0;
    public static final int key_chats_menuTopShadowCats = 0;
    public static final int key_chats_message = 0;
    public static final int key_chats_messageArchived = 0;
    public static final int key_chats_message_threeLines = 0;
    public static final int key_chats_muteIcon = 0;
    public static final int key_chats_name = 0;
    public static final int key_chats_nameArchived = 0;
    public static final int key_chats_nameMessage = 0;
    public static final int key_chats_nameMessageArchived = 0;
    public static final int key_chats_nameMessageArchived_threeLines = 0;
    public static final int key_chats_nameMessage_threeLines = 0;
    public static final int key_chats_onlineCircle = 0;
    public static final int key_chats_pinnedIcon = 0;
    public static final int key_chats_pinnedOverlay = 0;
    public static final int key_chats_secretIcon = 0;
    public static final int key_chats_secretName = 0;
    public static final int key_chats_sentCheck = 0;
    public static final int key_chats_sentClock = 0;
    public static final int key_chats_sentError = 0;
    public static final int key_chats_sentErrorIcon = 0;
    public static final int key_chats_sentReadCheck = 0;
    public static final int key_chats_tabUnreadActiveBackground = 0;
    public static final int key_chats_tabUnreadUnactiveBackground = 0;
    public static final int key_chats_tabletSelectedOverlay = 0;
    public static final int key_chats_unreadCounter = 0;
    public static final int key_chats_unreadCounterMuted = 0;
    public static final int key_chats_unreadCounterText = 0;
    public static final int key_chats_verifiedBackground = 0;
    public static final int key_chats_verifiedCheck = 0;
    public static final int key_checkbox = 0;
    public static final int key_checkboxCheck = 0;
    public static final int key_checkboxDisabled = 0;
    public static final int key_checkboxSquareBackground = 0;
    public static final int key_checkboxSquareCheck = 0;
    public static final int key_checkboxSquareDisabled = 0;
    public static final int key_checkboxSquareUnchecked = 0;
    public static final int key_code_comment = 0;
    public static final int key_code_constant = 0;
    public static final int key_code_function = 0;
    public static final int key_code_keyword = 0;
    public static final int key_code_number = 0;
    public static final int key_code_operator = 0;
    public static final int key_code_string = 0;
    public static final int key_color_blue = 0;
    public static final int key_color_cyan = 0;
    public static final int key_color_green = 0;
    public static final int key_color_lightblue = 0;
    public static final int key_color_lightgreen = 0;
    public static final int key_color_orange = 0;
    public static final int key_color_purple = 0;
    public static final int key_color_red = 0;
    public static final int key_color_yellow = 0;
    public static final int key_contacts_inviteBackground = 0;
    public static final int key_contacts_inviteText = 0;
    public static final int key_contextProgressInner1 = 0;
    public static final int key_contextProgressInner2 = 0;
    public static final int key_contextProgressInner3 = 0;
    public static final int key_contextProgressInner4 = 0;
    public static final int key_contextProgressOuter1 = 0;
    public static final int key_contextProgressOuter2 = 0;
    public static final int key_contextProgressOuter3 = 0;
    public static final int key_contextProgressOuter4 = 0;
    public static final int key_dialogBackground = 0;
    public static final int key_dialogBackgroundGray = 0;
    public static final int key_dialogButton = 0;
    public static final int key_dialogButtonSelector = 0;
    public static final int key_dialogCardShadow = 0;
    public static final int key_dialogCheckboxSquareBackground = 0;
    public static final int key_dialogCheckboxSquareCheck = 0;
    public static final int key_dialogCheckboxSquareDisabled = 0;
    public static final int key_dialogCheckboxSquareUnchecked = 0;
    public static final int key_dialogEmptyImage = 0;
    public static final int key_dialogEmptyText = 0;
    public static final int key_dialogFloatingButton = 0;
    public static final int key_dialogFloatingButtonPressed = 0;
    public static final int key_dialogFloatingIcon = 0;
    public static final int key_dialogGiftsBackground = 0;
    public static final int key_dialogGiftsTabText = 0;
    public static final int key_dialogGrayLine = 0;
    public static final int key_dialogIcon = 0;
    public static final int key_dialogInputField = 0;
    public static final int key_dialogInputFieldActivated = 0;
    public static final int key_dialogLineProgress = 0;
    public static final int key_dialogLineProgressBackground = 0;
    public static final int key_dialogLinkSelection = 0;
    public static final int key_dialogRadioBackground = 0;
    public static final int key_dialogRadioBackgroundChecked = 0;
    public static final int key_dialogReactionMentionBackground = 0;
    public static final int key_dialogRoundCheckBox = 0;
    public static final int key_dialogRoundCheckBoxCheck = 0;
    public static final int key_dialogScrollGlow = 0;
    public static final int key_dialogSearchBackground = 0;
    public static final int key_dialogSearchHint = 0;
    public static final int key_dialogSearchIcon = 0;
    public static final int key_dialogSearchText = 0;
    public static final int key_dialogShadowLine = 0;
    public static final int key_dialogSwipeRemove = 0;
    public static final int key_dialogTextBlack = 0;
    public static final int key_dialogTextBlue = 0;
    public static final int key_dialogTextBlue2 = 0;
    public static final int key_dialogTextBlue4 = 0;
    public static final int key_dialogTextGray = 0;
    public static final int key_dialogTextGray2 = 0;
    public static final int key_dialogTextGray3 = 0;
    public static final int key_dialogTextGray4 = 0;
    public static final int key_dialogTextHint = 0;
    public static final int key_dialogTextLink = 0;
    public static final int key_dialogTopBackground = 0;
    public static final int key_dialog_inlineProgress = 0;
    public static final int key_dialog_inlineProgressBackground = 0;
    public static final int key_dialog_liveLocationProgress = 0;
    public static final int key_divider = 0;
    public static final int key_emptyListPlaceholder = 0;
    public static final int key_fastScrollActive = 0;
    public static final int key_fastScrollInactive = 0;
    public static final int key_fastScrollText = 0;
    public static final int key_featuredStickers_addButton = 0;
    public static final int key_featuredStickers_addButton2 = 0;
    public static final int key_featuredStickers_addButtonPressed = 0;
    public static final int key_featuredStickers_addedIcon = 0;
    public static final int key_featuredStickers_buttonProgress = 0;
    public static final int key_featuredStickers_buttonText = 0;
    public static final int key_featuredStickers_removeButtonText = 0;
    public static final int key_featuredStickers_unread = 0;
    public static final int key_files_folderIcon = 0;
    public static final int key_files_folderIconBackground = 0;
    public static final int key_files_iconText = 0;
    public static final int key_fill_RedDark = 0;
    public static final int key_fill_RedNormal = 0;
    public static final int key_gift_ribbon = 0;
    public static final int key_gift_ribbon_soldout = 0;
    public static final int key_glass_defaultIcon = 0;
    public static final int key_glass_defaultText = 0;
    public static final int key_glass_tabSelected = 0;
    public static final int key_glass_tabSelectedText = 0;
    public static final int key_glass_tabUnselected = 0;
    public static final int key_glass_targetMainTabs = 0;
    public static final int key_glass_targetMainTopPanel = 0;
    public static final int key_graySection = 0;
    public static final int key_graySectionText = 0;
    public static final int key_groupcreate_cursor = 0;
    public static final int key_groupcreate_hintText = 0;
    public static final int key_groupcreate_sectionShadow = 0;
    public static final int key_groupcreate_sectionText = 0;
    public static final int key_groupcreate_spanBackground = 0;
    public static final int key_groupcreate_spanDelete = 0;
    public static final int key_groupcreate_spanText = 0;
    public static final int key_inappPlayerBackground = 0;
    public static final int key_inappPlayerClose = 0;
    public static final int key_inappPlayerPerformer = 0;
    public static final int key_inappPlayerPlayPause = 0;
    public static final int key_inappPlayerTitle = 0;
    public static final int key_iv_ab_progress = 0;
    public static final int key_iv_background = 0;
    public static final int key_iv_backgroundGray = 0;
    public static final int key_iv_navigationBackground = 0;
    public static final int key_listSelector = 0;
    public static final int key_location_actionActiveIcon = 0;
    public static final int key_location_actionBackground = 0;
    public static final int key_location_actionIcon = 0;
    public static final int key_location_actionPressedBackground = 0;
    public static final int key_location_liveLocationProgress = 0;
    public static final int key_location_placeLocationBackground = 0;
    public static final int key_location_sendLiveLocationBackground = 0;
    public static final int key_location_sendLiveLocationIcon = 0;
    public static final int key_location_sendLiveLocationText = 0;
    public static final int key_location_sendLocationBackground = 0;
    public static final int key_location_sendLocationIcon = 0;
    public static final int key_location_sendLocationText = 0;
    public static final int key_login_progressInner = 0;
    public static final int key_login_progressOuter = 0;
    public static final int key_passport_authorizeBackground = 0;
    public static final int key_passport_authorizeBackgroundSelected = 0;
    public static final int key_passport_authorizeText = 0;
    public static final int key_picker_badge = 0;
    public static final int key_picker_badgeText = 0;
    public static final int key_picker_disabledButton = 0;
    public static final int key_picker_enabledButton = 0;
    public static final int key_player_actionBarItems = 0;
    public static final int key_player_actionBarSelector = 0;
    public static final int key_player_actionBarSubtitle = 0;
    public static final int key_player_actionBarTitle = 0;
    public static final int key_player_background = 0;
    public static final int key_player_button = 0;
    public static final int key_player_buttonActive = 0;
    public static final int key_player_progress = 0;
    public static final int key_player_progressBackground = 0;
    public static final int key_player_progressCachedBackground = 0;
    public static final int key_player_time = 0;
    public static final int key_pollCreateIcons = 0;
    public static final int key_premiumCoinGradient1 = 0;
    public static final int key_premiumCoinGradient2 = 0;
    public static final int key_premiumGradient0 = 0;
    public static final int key_premiumGradient1 = 0;
    public static final int key_premiumGradient2 = 0;
    public static final int key_premiumGradient3 = 0;
    public static final int key_premiumGradient4 = 0;
    public static final int key_premiumGradientBackground1 = 0;
    public static final int key_premiumGradientBackground2 = 0;
    public static final int key_premiumGradientBackground3 = 0;
    public static final int key_premiumGradientBackground4 = 0;
    public static final int key_premiumGradientBackgroundOverlay = 0;
    public static final int key_premiumGradientBottomSheet1 = 0;
    public static final int key_premiumGradientBottomSheet2 = 0;
    public static final int key_premiumGradientBottomSheet3 = 0;
    public static final int key_premiumStarGradient1 = 0;
    public static final int key_premiumStarGradient2 = 0;
    public static final int key_premiumStartSmallStarsColor = 0;
    public static final int key_premiumStartSmallStarsColor2 = 0;
    public static final int key_profile_actionBackground = 0;
    public static final int key_profile_actionIcon = 0;
    public static final int key_profile_actionPressedBackground = 0;
    public static final int key_profile_creatorIcon = 0;
    public static final int key_profile_status = 0;
    public static final int key_profile_tabSelectedLine = 0;
    public static final int key_profile_tabSelectedText = 0;
    public static final int key_profile_tabSelector = 0;
    public static final int key_profile_tabText = 0;
    public static final int key_profile_title = 0;
    public static final int key_profile_verifiedBackground = 0;
    public static final int key_profile_verifiedCheck = 0;
    public static final int key_progressCircle = 0;
    public static final int key_radioBackground = 0;
    public static final int key_radioBackgroundChecked = 0;
    public static final int key_reactionStarSelector = 0;
    public static final int key_returnToCallBackground = 0;
    public static final int key_returnToCallMutedBackground = 0;
    public static final int key_returnToCallText = 0;
    public static final int key_sessions_devicesImage = 0;
    public static final int key_settings_listSelector = 0;
    public static final int key_share_icon = 0;
    public static final int key_share_linkBackground = 0;
    public static final int key_share_linkText = 0;
    public static final int key_sharedMedia_linkPlaceholder = 0;
    public static final int key_sharedMedia_linkPlaceholderText = 0;
    public static final int key_sharedMedia_photoPlaceholder = 0;
    public static final int key_sharedMedia_startStopLoadIcon = 0;
    public static final int key_sheet_other = 0;
    public static final int key_sheet_scrollUp = 0;
    public static final int key_starsGradient1 = 0;
    public static final int key_starsGradient2 = 0;
    public static final int key_statisticChartActiveLine = 0;
    public static final int key_statisticChartActivePickerChart = 0;
    public static final int key_statisticChartBackZoomColor = 0;
    public static final int key_statisticChartChevronColor = 0;
    public static final int key_statisticChartHintLine = 0;
    public static final int key_statisticChartInactivePickerChart = 0;
    public static final int key_statisticChartLineEmpty = 0;
    public static final int key_statisticChartLine_blue = 0;
    public static final int key_statisticChartLine_cyan = 0;
    public static final int key_statisticChartLine_golden = 0;
    public static final int key_statisticChartLine_green = 0;
    public static final int key_statisticChartLine_indigo = 0;
    public static final int key_statisticChartLine_lightblue = 0;
    public static final int key_statisticChartLine_lightgreen = 0;
    public static final int key_statisticChartLine_orange = 0;
    public static final int key_statisticChartLine_purple = 0;
    public static final int key_statisticChartLine_red = 0;
    public static final int key_statisticChartRipple = 0;
    public static final int key_statisticChartSignature = 0;
    public static final int key_statisticChartSignatureAlpha = 0;
    public static final int key_stickers_menu = 0;
    public static final int key_stickers_menuSelector = 0;
    public static final int key_stories_circle1 = 0;
    public static final int key_stories_circle2 = 0;
    public static final int key_stories_circle_closeFriends1 = 0;
    public static final int key_stories_circle_closeFriends2 = 0;
    public static final int key_stories_circle_dialog1 = 0;
    public static final int key_stories_circle_dialog2 = 0;
    public static final int key_stories_circle_live1 = 0;
    public static final int key_stories_circle_live2 = 0;
    public static final int key_switch2Track = 0;
    public static final int key_switch2TrackChecked = 0;
    public static final int key_switchTrack = 0;
    public static final int key_switchTrackBlue = 0;
    public static final int key_switchTrackBlueChecked = 0;
    public static final int key_switchTrackBlueSelector = 0;
    public static final int key_switchTrackBlueSelectorChecked = 0;
    public static final int key_switchTrackBlueThumb = 0;
    public static final int key_switchTrackBlueThumbChecked = 0;
    public static final int key_switchTrackChecked = 0;
    public static final int key_table_background = 0;
    public static final int key_table_border = 0;
    public static final int key_telegram_color = 0;
    public static final int key_telegram_color_dialogsLogo = 0;
    public static final int key_telegram_color_text = 0;
    public static final int key_text_RedBold = 0;
    public static final int key_text_RedRegular = 0;
    public static final int key_topics_unreadCounter = 0;
    public static final int key_topics_unreadCounterMuted = 0;
    public static final int key_undo_background = 0;
    public static final int key_undo_cancelColor = 0;
    public static final int key_undo_infoColor = 0;
    public static final int key_voipgroup_actionBar = 0;
    public static final int key_voipgroup_actionBarItems = 0;
    public static final int key_voipgroup_actionBarItemsSelector = 0;
    public static final int key_voipgroup_actionBarUnscrolled = 0;
    public static final int key_voipgroup_checkMenu = 0;
    public static final int key_voipgroup_connectingProgress = 0;
    public static final int key_voipgroup_dialogBackground = 0;
    public static final int key_voipgroup_disabledButton = 0;
    public static final int key_voipgroup_disabledButtonActive = 0;
    public static final int key_voipgroup_disabledButtonActiveScrolled = 0;
    public static final int key_voipgroup_inviteMembersBackground = 0;
    public static final int key_voipgroup_lastSeenText = 0;
    public static final int key_voipgroup_lastSeenTextUnscrolled = 0;
    public static final int key_voipgroup_leaveButton = 0;
    public static final int key_voipgroup_leaveButtonScrolled = 0;
    public static final int key_voipgroup_leaveCallMenu = 0;
    public static final int key_voipgroup_listSelector = 0;
    public static final int key_voipgroup_listViewBackground = 0;
    public static final int key_voipgroup_listViewBackgroundUnscrolled = 0;
    public static final int key_voipgroup_listeningText = 0;
    public static final int key_voipgroup_muteButton = 0;
    public static final int key_voipgroup_muteButton2 = 0;
    public static final int key_voipgroup_muteButton3 = 0;
    public static final int key_voipgroup_mutedByAdminGradient = 0;
    public static final int key_voipgroup_mutedByAdminGradient2 = 0;
    public static final int key_voipgroup_mutedByAdminGradient3 = 0;
    public static final int key_voipgroup_mutedByAdminIcon = 0;
    public static final int key_voipgroup_mutedByAdminMuteButton = 0;
    public static final int key_voipgroup_mutedByAdminMuteButtonDisabled = 0;
    public static final int key_voipgroup_mutedIcon = 0;
    public static final int key_voipgroup_mutedIconUnscrolled = 0;
    public static final int key_voipgroup_nameText = 0;
    public static final int key_voipgroup_overlayAlertGradientMuted = 0;
    public static final int key_voipgroup_overlayAlertGradientMuted2 = 0;
    public static final int key_voipgroup_overlayAlertGradientUnmuted = 0;
    public static final int key_voipgroup_overlayAlertGradientUnmuted2 = 0;
    public static final int key_voipgroup_overlayAlertMutedByAdmin = 0;
    public static final int key_voipgroup_overlayAlertMutedByAdmin2 = 0;
    public static final int key_voipgroup_overlayBlue1 = 0;
    public static final int key_voipgroup_overlayBlue2 = 0;
    public static final int key_voipgroup_overlayGreen1 = 0;
    public static final int key_voipgroup_overlayGreen2 = 0;
    public static final int key_voipgroup_rtmpButton = 0;
    public static final int key_voipgroup_scrollUp = 0;
    public static final int key_voipgroup_searchBackground = 0;
    public static final int key_voipgroup_searchPlaceholder = 0;
    public static final int key_voipgroup_searchText = 0;
    public static final int key_voipgroup_soundButton = 0;
    public static final int key_voipgroup_soundButton2 = 0;
    public static final int key_voipgroup_soundButtonActive = 0;
    public static final int key_voipgroup_soundButtonActive2 = 0;
    public static final int key_voipgroup_soundButtonActive2Scrolled = 0;
    public static final int key_voipgroup_soundButtonActiveScrolled = 0;
    public static final int key_voipgroup_speakingText = 0;
    public static final int key_voipgroup_topPanelBlue1 = 0;
    public static final int key_voipgroup_topPanelBlue2 = 0;
    public static final int key_voipgroup_topPanelGray = 0;
    public static final int key_voipgroup_topPanelGreen1 = 0;
    public static final int key_voipgroup_topPanelGreen2 = 0;
    public static final int key_voipgroup_unmuteButton = 0;
    public static final int key_voipgroup_unmuteButton2 = 0;
    public static final int key_voipgroup_windowBackgroundWhiteInputField = 0;
    public static final int key_voipgroup_windowBackgroundWhiteInputFieldActivated = 0;
    public static final int key_wallpaperFileOffset = 0;
    public static final int key_windowBackgroundCheckText = 0;
    public static final int key_windowBackgroundChecked = 0;
    public static final int key_windowBackgroundGray = 0;
    public static final int key_windowBackgroundGrayShadow = 0;
    public static final int key_windowBackgroundUnchecked = 0;
    public static final int key_windowBackgroundWhite = 0;
    public static final int key_windowBackgroundWhiteBlackText = 0;
    public static final int key_windowBackgroundWhiteBlueButton = 0;
    public static final int key_windowBackgroundWhiteBlueHeader = 0;
    public static final int key_windowBackgroundWhiteBlueIcon = 0;
    public static final int key_windowBackgroundWhiteBlueText = 0;
    public static final int key_windowBackgroundWhiteBlueText2 = 0;
    public static final int key_windowBackgroundWhiteBlueText3 = 0;
    public static final int key_windowBackgroundWhiteBlueText4 = 0;
    public static final int key_windowBackgroundWhiteBlueText5 = 0;
    public static final int key_windowBackgroundWhiteBlueText6 = 0;
    public static final int key_windowBackgroundWhiteBlueText7 = 0;
    public static final int key_windowBackgroundWhiteGrayIcon = 0;
    public static final int key_windowBackgroundWhiteGrayText = 0;
    public static final int key_windowBackgroundWhiteGrayText2 = 0;
    public static final int key_windowBackgroundWhiteGrayText3 = 0;
    public static final int key_windowBackgroundWhiteGrayText4 = 0;
    public static final int key_windowBackgroundWhiteGrayText5 = 0;
    public static final int key_windowBackgroundWhiteGrayText6 = 0;
    public static final int key_windowBackgroundWhiteGrayText7 = 0;
    public static final int key_windowBackgroundWhiteGrayText8 = 0;
    public static final int key_windowBackgroundWhiteGreenText = 0;
    public static final int key_windowBackgroundWhiteGreenText2 = 0;
    public static final int key_windowBackgroundWhiteHintText = 0;
    public static final int key_windowBackgroundWhiteInputField = 0;
    public static final int key_windowBackgroundWhiteInputFieldActivated = 0;
    public static final int key_windowBackgroundWhiteLinkSelection = 0;
    public static final int key_windowBackgroundWhiteLinkText = 0;
    public static final int key_windowBackgroundWhiteValueText = 0;
    public static int[] keys_avatar_background;
    public static int[] keys_avatar_background2;
    public static int[] keys_avatar_nameInMessage;
    public static final int[] keys_colors = null;
    private static float lastBrightnessValue;
    private static long lastDelayUpdateTime;
    private static WeakReference<Drawable> lastDrawableToBlur;
    private static int lastLoadingCurrentThemeTime;
    private static int[] lastLoadingThemesTime;
    private static long lastThemeSwitchTime;
    private static Sensor lightSensor;
    private static boolean lightSensorRegistered;
    public static Paint linkSelectionPaint;
    private static int loadingCurrentTheme;
    private static boolean[] loadingRemoteThemes;
    private static Paint maskPaint;
    public static Drawable moveUpDrawable;
    public static final int myMessages2EndIndex = 0;
    public static final int myMessages2StartIndex = 0;
    private static final int[] myMessagesAccentExtraKeys = null;
    public static final int myMessagesBubblesEndIndex = 0;
    public static final int myMessagesBubblesStartIndex = 0;
    public static final int myMessagesEndIndex = 0;
    public static final int myMessagesStartIndex = 0;
    private static ArrayList<ThemeInfo> otherThemes;
    private static int patternIntensity;
    public static PathAnimator playPauseAnimator;
    private static int previousPhase;
    private static ThemeInfo previousTheme;
    public static TextPaint profile_aboutTextPaint;
    public static Drawable profile_verifiedCheckDrawable;
    public static Drawable profile_verifiedDrawable;
    private static long[] remoteThemesHash;
    private static boolean resolvingDividerColor;
    private static RoundVideoProgressShadow roundPlayDrawable;
    public static int selectedAutoNightType;
    private static SensorManager sensorManager;
    private static Bitmap serviceBitmap;
    private static Matrix serviceBitmapMatrix;
    public static BitmapShader serviceBitmapShader;
    private static int serviceMessageColor;
    public static int serviceMessageColorBackup;
    private static int serviceSelectedMessageColor;
    public static int serviceSelectedMessageColorBackup;
    public static boolean shouldDrawGradientIcons;
    private static Runnable switchDayBrightnessRunnable;
    private static boolean switchDayRunnableScheduled;
    private static Runnable switchNightBrightnessRunnable;
    private static boolean switchNightRunnableScheduled;
    private static int switchNightThemeDelay;
    private static boolean switchingNightTheme;
    private static final Object sync = null;
    private static HashSet<Integer> themeAccentExclusionKeys;
    private static Drawable themedWallpaper;
    private static int themedWallpaperFileOffset;
    private static String themedWallpaperLink;
    public static ArrayList<ThemeInfo> themes;
    private static HashMap<String, ThemeInfo> themesDict;
    private static float[] tmpHSV5;
    private static int[] viewPos;
    private static Drawable wallpaper;
    public static Runnable wallpaperLoadTask;

    /* renamed from: org.telegram.ui.ActionBar.Theme$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Theme.m5812$$Nest$sfputswitchDayRunnableScheduled(false);
            Theme.applyDayNightThemeMaybe(false);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends Drawable {
        final /* synthetic */ int val$leftInset;
        final /* synthetic */ int val$rightInset;

        public AnonymousClass10(int r1, int r2) {
            this.val$leftInset = r1;
            this.val$rightInset = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            Rect r0 = getBounds();
            r5.drawCircle((r0.centerX() - this.val$leftInset) + this.val$rightInset, r0.centerY(), ((Math.max(r0.width(), r0.height()) / 2) + this.val$leftInset) + this.val$rightInset, Theme.m5802$$Nest$sfgetmaskPaint());
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$11, reason: invalid class name */
    public class AnonymousClass11 implements SensorEventListener {
        public AnonymousClass11() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor r1, int r2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent r5) {
            float r4 = r5.values[0];
            if (r4 > 0.0f) goto L6;
            r4 = 0.1f;
        L6:
            if (ApplicationLoader.mainInterfacePaused == false) goto L8;
            return;
        L8:
            if (ApplicationLoader.isScreenOn == true) goto L11;
            return;
        L11:
            if (r4 <= 500.0f) goto L13;
            Theme.m5811$$Nest$sfputlastBrightnessValue(1.0f);
        L15:
            if (Theme.m5801$$Nest$sfgetlastBrightnessValue() > Theme.autoNightBrighnessThreshold) goto L26;
            if (MediaController.getInstance().isRecordingOrListeningByProximity() == false) goto L19;
            return;
        L19:
            if (Theme.m5806$$Nest$sfgetswitchDayRunnableScheduled() == false) goto L22;
            Theme.m5812$$Nest$sfputswitchDayRunnableScheduled(false);
            AndroidUtilities.cancelRunOnUIThread(Theme.m5805$$Nest$sfgetswitchDayBrightnessRunnable());
        L22:
            if (Theme.m5808$$Nest$sfgetswitchNightRunnableScheduled() == true) goto L34;
            Theme.m5813$$Nest$sfputswitchNightRunnableScheduled(true);
            AndroidUtilities.runOnUIThread(Theme.m5807$$Nest$sfgetswitchNightBrightnessRunnable(), Theme.m5818$$Nest$smgetAutoNightSwitchThemeDelay());
            return;
        L34:
            return;
        L26:
            if (Theme.m5808$$Nest$sfgetswitchNightRunnableScheduled() == false) goto L29;
            Theme.m5813$$Nest$sfputswitchNightRunnableScheduled(false);
            AndroidUtilities.cancelRunOnUIThread(Theme.m5807$$Nest$sfgetswitchNightBrightnessRunnable());
        L29:
            if (Theme.m5806$$Nest$sfgetswitchDayRunnableScheduled() == true) goto L35;
            Theme.m5812$$Nest$sfputswitchDayRunnableScheduled(true);
            AndroidUtilities.runOnUIThread(Theme.m5805$$Nest$sfgetswitchDayBrightnessRunnable(), Theme.m5818$$Nest$smgetAutoNightSwitchThemeDelay());
            return;
        L35:
            return;
        L13:
            Theme.m5811$$Nest$sfputlastBrightnessValue(((float) Math.ceil((Math.log(r4) * 9.932299613952637d) + 27.05900001525879d)) / 100.0f);
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 extends MessageDrawable {
        final /* synthetic */ SparseIntArray val$colors;

        public AnonymousClass12(int r1, boolean r2, boolean r3, SparseIntArray r4) {
            this.val$colors = r4;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.ActionBar.MessageDrawable
        public int getColor(int r2) {
            int r0 = this.val$colors.indexOfKey(r2);
            if (r0 <= 0) goto L7;
            return this.val$colors.valueAt(r0);
        L7:
            return Theme.m5799$$Nest$sfgetdefaultColors()[r2];
        }

        @Override // org.telegram.ui.ActionBar.MessageDrawable
        public int getCurrentColor(int r1) {
            return this.val$colors.get(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 extends BackgroundGradientDrawable.ListenerAdapter {
        public AnonymousClass13() {
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.ListenerAdapter, org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onSizeReady(int r4, int r5) {
            Point r3 = AndroidUtilities.displaySize;
            boolean r1 = true;
            if (r3.x > r3.y) goto L5;
            boolean r32 = true;
        L6:
            if (r4 <= r5) goto L9;
            r1 = false;
        L9:
            if (r32 != r1) goto L12;
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
            return;
        L12:
            return;
        L5:
            r32 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends BackgroundGradientDrawable.ListenerAdapter {
        public AnonymousClass14() {
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.ListenerAdapter, org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onSizeReady(int r4, int r5) {
            Point r3 = AndroidUtilities.displaySize;
            boolean r1 = true;
            if (r3.x > r3.y) goto L5;
            boolean r32 = true;
        L6:
            if (r4 <= r5) goto L9;
            r1 = false;
        L9:
            if (r32 != r1) goto L12;
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
            return;
        L12:
            return;
        L5:
            r32 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 extends BackgroundGradientDrawable.ListenerAdapter {
        final /* synthetic */ View val$ownerView;
        final /* synthetic */ boolean val$thumb;

        public AnonymousClass15(boolean r1, View r2) {
            this.val$thumb = r1;
            this.val$ownerView = r2;
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.ListenerAdapter, org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onSizeReady(int r5, int r6) {
            if (this.val$thumb == true) goto L13;
            Point r0 = AndroidUtilities.displaySize;
            boolean r2 = false;
            if (r0.x > r0.y) goto L7;
            boolean r02 = true;
        L8:
            if (r5 > r6) goto L10;
            r2 = true;
        L10:
            if (r02 != r2) goto L15;
            this.val$ownerView.invalidate();
            return;
        L15:
            return;
        L7:
            r02 = false;
            goto L8
        L13:
            this.val$ownerView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        public AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Theme.m5813$$Nest$sfputswitchNightRunnableScheduled(false);
            Theme.applyDayNightThemeMaybe(true);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends StateListDrawable {
        public AnonymousClass3() {
        }

        @Override // android.graphics.drawable.DrawableContainer
        public boolean selectDrawable(int r1) {
            return super.selectDrawable(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends StateListDrawable {
        public AnonymousClass4() {
        }

        @Override // android.graphics.drawable.DrawableContainer
        public boolean selectDrawable(int r1) {
            return super.selectDrawable(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends Drawable {
        private final Paint paint;
        final /* synthetic */ int val$color;
        final /* synthetic */ int val$size;
        final /* synthetic */ int val$strokeWidth;

        public AnonymousClass6(int r2, int r3, int r4) {
            this.val$strokeWidth = r2;
            this.val$color = r3;
            this.val$size = r4;
            Paint r42 = new Paint(1);
            this.paint = r42;
            r42.setStyle(Paint.Style.STROKE);
            r42.setStrokeWidth(r2);
            r42.setColor(r3);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            Rect r0 = getBounds();
            r5.drawCircle(r0.centerX(), r0.centerY(), this.val$size / 2.0f, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.val$size + this.val$strokeWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.val$size + this.val$strokeWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.paint.setColorFilter(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends Drawable {
        private RectF rect;
        final /* synthetic */ Paint val$backgroundPaint;
        final /* synthetic */ View val$containerView;
        final /* synthetic */ int val$rad;
        final /* synthetic */ ResourcesProvider val$resourcesProvider;
        final /* synthetic */ View val$view;

        public AnonymousClass7(View r1, View r2, int r3, Paint r4, ResourcesProvider r5) {
            this.val$view = r1;
            this.val$containerView = r2;
            this.val$rad = r3;
            this.val$backgroundPaint = r4;
            this.val$resourcesProvider = r5;
            this.rect = new RectF();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r6) {
            Rect r0 = getBounds();
            this.rect.set(r0.left, r0.top, r0.right, r0.bottom);
            Theme.applyServiceShaderMatrixForView(this.val$view, this.val$containerView);
            RectF r02 = this.rect;
            int r1 = this.val$rad;
            float r2 = r1;
            float r12 = r1;
            Paint r3 = this.val$backgroundPaint;
            if (r3 != null) goto L6;
            r3 = Theme.getThemePaint("paintChatActionBackground", this.val$resourcesProvider);
        L6:
            r6.drawRoundRect(r02, r2, r12, r3);
            ResourcesProvider r03 = this.val$resourcesProvider;
            if (r03 == null) goto L12;
            if (r03.hasGradientService() == false) goto L15;
        L13:
            RectF r04 = this.rect;
            int r13 = this.val$rad;
            r6.drawRoundRect(r04, r13, r13, Theme.getThemePaint("paintChatActionBackgroundDarken", this.val$resourcesProvider));
            return;
        L15:
            return;
        L12:
            if (Theme.hasGradientService() == true) goto L13;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends Drawable {
        RectF rect;
        final /* synthetic */ int val$maskType;
        final /* synthetic */ int val$radius;

        public AnonymousClass8(int r1, int r2) {
            this.val$maskType = r1;
            this.val$radius = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            Rect r0 = getBounds();
            int r1 = this.val$maskType;
            if (r1 != 7) goto L13;
            if (this.rect != null) goto L7;
            this.rect = new RectF();
        L7:
            this.rect.set(r0);
            int r02 = this.val$radius;
            if (r02 > 0) goto L10;
            r02 = AndroidUtilities.dp(6.0f);
        L10:
            float r03 = r02;
            r5.drawRoundRect(this.rect, r03, r03, Theme.m5802$$Nest$sfgetmaskPaint());
            return;
        L13:
            if (r1 != 1) goto L15;
        L21:
            int r4 = this.val$radius;
            if (r4 > 0) goto L24;
            r4 = AndroidUtilities.dp(20.0f);
        L24:
            r5.drawCircle(r0.centerX(), r0.centerY(), r4, Theme.m5802$$Nest$sfgetmaskPaint());
            return;
        L15:
            if (r1 == 6) goto L21;
            if (r1 != 3) goto L20;
            r4 = Math.max(r0.width(), r0.height()) / 2;
            goto L24
        L20:
            r4 = (int) Math.ceil(Math.sqrt(((r0.left - r0.centerX()) * (r0.left - r0.centerX())) + ((r0.top - r0.centerY()) * (r0.top - r0.centerY()))));
            goto L24
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.Theme$9, reason: invalid class name */
    public class AnonymousClass9 extends Drawable {
        private final RectF rectF;
        final /* synthetic */ int val$insetB;
        final /* synthetic */ int val$insetL;
        final /* synthetic */ int val$insetR;
        final /* synthetic */ int val$insetT;
        final /* synthetic */ float val$radius;

        public AnonymousClass9(int r1, int r2, int r3, int r4, float r5) {
            this.val$insetL = r1;
            this.val$insetT = r2;
            this.val$insetR = r3;
            this.val$insetB = r4;
            this.val$radius = r5;
            this.rectF = new RectF();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            this.rectF.set(getBounds());
            RectF r0 = this.rectF;
            r0.left += this.val$insetL;
            r0.top += this.val$insetT;
            r0.right -= this.val$insetR;
            r0.bottom -= this.val$insetB;
            float r3 = this.val$radius;
            r4.drawRoundRect(r0, r3, r3, Theme.m5802$$Nest$sfgetmaskPaint());
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class AdaptiveRipple {
        private static final int defaultBackgroundColorKey = 0;
        private static float[] tempHSV;

        public static class CircleDrawable extends Drawable {
            private static Paint maskPaint;
            private Paint paint;
            private float radius;

            public CircleDrawable(float r2) {
                this.radius = r2;
                if (maskPaint != null) goto L5;
                Paint r22 = new Paint(1);
                maskPaint = r22;
                r22.setColor(-1);
            L5:
                this.paint = maskPaint;
            }

            public CircleDrawable(float r2, int r3) {
                this.radius = r2;
                Paint r22 = new Paint(1);
                this.paint = r22;
                r22.setColor(r3);
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r6) {
                Rect r0 = getBounds();
                if (Math.abs(this.radius - (-1.0f)) >= 0.01f) goto L7;
                int r1 = Math.max(r0.width(), r0.height()) / 2;
            L10:
                r6.drawCircle(r0.centerX(), r0.centerY(), r1, this.paint);
                return;
            L7:
                if (Math.abs(this.radius - (-2.0f)) >= 0.01f) goto L9;
                r1 = (int) Math.ceil(Math.sqrt(((r0.left - r0.centerX()) * (r0.left - r0.centerX())) + ((r0.top - r0.centerY()) * (r0.top - r0.centerY()))));
                goto L10
            L9:
                r1 = AndroidUtilities.dp(this.radius);
                goto L10
            }

            @Override // android.graphics.drawable.Drawable
            @Deprecated
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        static {
            defaultBackgroundColorKey = Theme.key_windowBackgroundWhite;
        }

        private static float[] calcRadii(float... r18) {
            if (r18.length != 0) goto L7;
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        L7:
            if (r18.length != 1) goto L11;
            return new float[]{AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0])};
        L11:
            if (r18.length != 2) goto L15;
            return new float[]{AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[1])};
        L15:
            if (r18.length != 3) goto L19;
            return new float[]{AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[2]), AndroidUtilities.dp(r18[2]), AndroidUtilities.dp(r18[2]), AndroidUtilities.dp(r18[2])};
        L19:
            if (r18.length >= 8) goto L23;
            return new float[]{AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[2]), AndroidUtilities.dp(r18[2]), AndroidUtilities.dp(r18[3]), AndroidUtilities.dp(r18[3])};
        L23:
            return new float[]{AndroidUtilities.dp(r18[0]), AndroidUtilities.dp(r18[1]), AndroidUtilities.dp(r18[2]), AndroidUtilities.dp(r18[3]), AndroidUtilities.dp(r18[4]), AndroidUtilities.dp(r18[5]), AndroidUtilities.dp(r18[6]), AndroidUtilities.dp(r18[7])};
        }

        public static int calcRippleColor(int r6) {
            if (tempHSV != null) goto L5;
            tempHSV = new float[3];
        L5:
            Color.colorToHSV(r6, tempHSV);
            float[] r62 = tempHSV;
            float r1 = r62[1];
            if (r1 > 0.01f) goto L8;
            float r0 = r62[2];
            if (Theme.isCurrentThemeDark() == false) goto L19;
            float r12 = 0.1f;
        L20:
            r62[2] = Math.min(1.0f, Math.max(0.0f, r0 + r12));
        L22:
            return Color.HSVToColor(127, tempHSV);
        L19:
            r12 = -0.1f;
            goto L20
        L8:
            if (Theme.isCurrentThemeDark() == false) goto L10;
            float r2 = 0.25f;
        L11:
            r62[1] = Math.min(1.0f, Math.max(0.0f, r1 + r2));
            float[] r63 = tempHSV;
            float r02 = r63[2];
            if (Theme.isCurrentThemeDark() == false) goto L14;
            float r13 = 0.05f;
        L15:
            r63[2] = Math.min(1.0f, Math.max(0.0f, r02 + r13));
            goto L22
        L14:
            r13 = -0.05f;
            goto L15
        L10:
            r2 = -0.25f;
            goto L11
        }

        public static Drawable circle(int r1) {
            return circle(r1, -1.0f);
        }

        public static Drawable circle(int r0, float r1) {
            return createCircle(calcRippleColor(r0), r1);
        }

        private static Drawable createCircle(int r1, float r2) {
            return createCircle(0, r1, r2);
        }

        private static Drawable createCircle(int r1, int r2, float r3) {
            if (r1 != 0) goto L4;
            CircleDrawable r12 = null;
        L6:
            return createCircle(r12, r2, r3);
        L4:
            r12 = new CircleDrawable(r3, r1);
            goto L6
        }

        private static Drawable createCircle(Drawable r3, int r4, float r5) {
            return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r4}), r3, new CircleDrawable(r5));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Drawable createRect(int r4, int r5, float... r6) {
            ColorDrawable r0 = null;
            if (r4 == 0) goto L9;
            if (hasNonzeroRadii(r6) == false) goto L7;
            ShapeDrawable r1 = new ShapeDrawable(new RoundRectShape(calcRadii(r6), null, null));
            r1.getPaint().setColor(r4);
            r0 = r1;
            goto L9
        L7:
            r0 = new ColorDrawable(r4);
        L9:
            return createRect(r0, r5, r6);
        }

        public static Drawable createRect(int r1, float... r2) {
            return createRect(0, r1, r2);
        }

        private static Drawable createRect(Drawable r4, int r5, float... r6) {
            if (hasNonzeroRadii(r6) == false) goto L5;
            ShapeDrawable r0 = new ShapeDrawable(new RoundRectShape(calcRadii(r6), null, null));
            r0.getPaint().setColor(-1);
        L7:
            return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r5}), r4, r0);
        L5:
            r0 = new ShapeDrawable(new RectShape());
            r0.getPaint().setColor(-1);
            goto L7
        }

        public static Drawable filledCircle() {
            return filledCircle(null, Theme.getColor(defaultBackgroundColorKey), -1.0f);
        }

        public static Drawable filledCircle(Drawable r0, int r1, float r2) {
            return createCircle(r0, calcRippleColor(r1), r2);
        }

        public static Drawable filledRect(int r2) {
            return createRect(r2, calcRippleColor(r2), new float[0]);
        }

        public static Drawable filledRect(int r1, float... r2) {
            return createRect(r1, calcRippleColor(r1), r2);
        }

        public static Drawable filledRectByKey(int r0) {
            return filledRect(Theme.getColor(r0));
        }

        public static Drawable filledRectByKey(int r0, float... r1) {
            return filledRect(Theme.getColor(r0), r1);
        }

        private static boolean hasNonzeroRadii(float... r4) {
            int r1 = 0;
        L4:
            if (r1 >= Math.min(8, r4.length)) goto L10;
            if (r4[r1] > 0.0f) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            return true;
        L10:
            return false;
        }

        public static Drawable rect(int r3) {
            return rect(r3, new float[]{0.0f});
        }

        public static Drawable rect(int r1, float... r2) {
            return createRect(0, calcRippleColor(r1), r2);
        }

        public static Drawable rectByKey(int r0, float... r1) {
            return rect(Theme.getColor(r0), r1);
        }
    }

    public static class BackgroundDrawableSettings {
        public Boolean isCustomTheme;
        public Boolean isPatternWallpaper;
        public Boolean isWallpaperMotion;
        public Drawable themedWallpaper;
        public Drawable wallpaper;

        public BackgroundDrawableSettings() {
        }
    }

    public interface Colorable {
        default int[] getColorKeys() {
            return null;
        }

        void updateColors();
    }

    /* loaded from: classes6.dex */
    public enum IvButtonColors extends Enum<IvButtonColors> {
        private static final /* synthetic */ IvButtonColors[] $VALUES = null;
        public static final IvButtonColors DANGER = null;
        public static final IvButtonColors DEFAULT = null;
        public static final IvButtonColors DEFAULT_IN_TEXT = null;
        public static final IvButtonColors PRIMARY = null;
        public static final IvButtonColors SUCCESS = null;
        private final int backgroundIn;
        private final int backgroundInPressed;
        private final int backgroundOut;
        private final int backgroundOutPressed;
        private final int textIn;
        private final int textOut;

        private static /* synthetic */ IvButtonColors[] $values() {
            return new IvButtonColors[]{DEFAULT, PRIMARY, DANGER, SUCCESS, DEFAULT_IN_TEXT};
        }

        static {
            DEFAULT = new IvButtonColors(PluginsConstants.Strategy.DEFAULT, 0, Theme.key_chat_msgIvButtonDefaultIn, Theme.key_chat_msgIvButtonDefaultInPressed, Theme.key_chat_msgIvButtonDefaultInText, Theme.key_chat_msgIvButtonDefaultOut, Theme.key_chat_msgIvButtonDefaultOutPressed, Theme.key_chat_msgIvButtonDefaultOutText);
            PRIMARY = new IvButtonColors("PRIMARY", 1, Theme.key_chat_msgIvButtonPrimaryIn, Theme.key_chat_msgIvButtonPrimaryInPressed, Theme.key_chat_msgIvButtonPrimaryInText, Theme.key_chat_msgIvButtonPrimaryOut, Theme.key_chat_msgIvButtonPrimaryOutPressed, Theme.key_chat_msgIvButtonPrimaryOutText);
            DANGER = new IvButtonColors("DANGER", 2, Theme.key_chat_msgIvButtonDangerIn, Theme.key_chat_msgIvButtonDangerInPressed, Theme.key_chat_msgIvButtonDangerInText, Theme.key_chat_msgIvButtonDangerOut, Theme.key_chat_msgIvButtonDangerOutPressed, Theme.key_chat_msgIvButtonDangerOutText);
            SUCCESS = new IvButtonColors("SUCCESS", 3, Theme.key_chat_msgIvButtonSuccessIn, Theme.key_chat_msgIvButtonSuccessInPressed, Theme.key_chat_msgIvButtonSuccessInText, Theme.key_chat_msgIvButtonSuccessOut, Theme.key_chat_msgIvButtonSuccessOutPressed, Theme.key_chat_msgIvButtonSuccessOutText);
            DEFAULT_IN_TEXT = new IvButtonColors("DEFAULT_IN_TEXT", 4, Theme.key_chat_msgIvButtonDefaultInlineIn, Theme.key_chat_msgIvButtonDefaultInlineInPressed, Theme.key_chat_msgIvButtonDefaultInlineInText, Theme.key_chat_msgIvButtonDefaultInlineOut, Theme.key_chat_msgIvButtonDefaultInlineOutPressed, Theme.key_chat_msgIvButtonDefaultInlineOutText);
            $VALUES = $values();
        }

        IvButtonColors(String r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8) {
            this.backgroundIn = r3;
            this.backgroundInPressed = r4;
            this.textIn = r5;
            this.backgroundOut = r6;
            this.backgroundOutPressed = r7;
            this.textOut = r8;
        }

        public static IvButtonColors of(TL_keyboard.RichButtonStyle r1) {
            if (r1 == null) goto L16;
            if (r1.bg_primary == false) goto L8;
            return PRIMARY;
        L8:
            if (r1.bg_danger == false) goto L12;
            return DANGER;
        L12:
            if (r1.bg_success == false) goto L16;
            return SUCCESS;
        L16:
            return DEFAULT;
        }

        public static IvButtonColors valueOf(String r1) {
            return (IvButtonColors) Enum.valueOf(IvButtonColors.class, r1);
        }

        public static IvButtonColors[] values() {
            return (IvButtonColors[]) $VALUES.clone();
        }

        public int getBackgroundKey(boolean r1) {
            if (r1 == false) goto L6;
            return this.backgroundOut;
        L6:
            return this.backgroundIn;
        }

        public int getBackgroundPressedKey(boolean r1) {
            if (r1 == false) goto L6;
            return this.backgroundOutPressed;
        L6:
            return this.backgroundInPressed;
        }

        public int getTextKey(boolean r1) {
            if (r1 == false) goto L6;
            return this.textOut;
        L6:
            return this.textIn;
        }
    }

    public static class OverrideWallpaperInfo {
        public long accessHash;
        public int color;
        public long dialogId;
        public String fileName;
        public boolean forBoth;
        public int gradientColor1;
        public int gradientColor2;
        public int gradientColor3;
        public float intensity;
        public boolean isBlurred;
        public boolean isMotion;
        public String originalFileName;
        public ThemeAccent parentAccent;
        public ThemeInfo parentTheme;
        public TLRPC.WallPaper prevUserWallpaper;
        public ArrayList<Integer> requestIds;
        public int rotation;
        public String slug;
        public float uploadingProgress;
        public long wallpaperId;

        /* renamed from: -$$Nest$mdelete, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5823$$Nest$mdelete(OverrideWallpaperInfo r0) {
            r0.delete();
        }

        /* renamed from: -$$Nest$msave, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5824$$Nest$msave(OverrideWallpaperInfo r0) {
            r0.save();
        }

        public OverrideWallpaperInfo() {
            this.fileName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.originalFileName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.slug = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public OverrideWallpaperInfo(OverrideWallpaperInfo r6, ThemeInfo r7, ThemeAccent r8) {
            this.fileName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.originalFileName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.slug = _UrlKt.FRAGMENT_ENCODE_SET;
            this.slug = r6.slug;
            this.color = r6.color;
            this.gradientColor1 = r6.gradientColor1;
            this.gradientColor2 = r6.gradientColor2;
            this.gradientColor3 = r6.gradientColor3;
            this.rotation = r6.rotation;
            this.isBlurred = r6.isBlurred;
            this.isMotion = r6.isMotion;
            this.intensity = r6.intensity;
            this.parentTheme = r7;
            this.parentAccent = r8;
            if (TextUtils.isEmpty(r6.fileName) == false) goto L20;
            this.fileName = _UrlKt.FRAGMENT_ENCODE_SET;
        L10:
            if (TextUtils.isEmpty(r6.originalFileName) == false) goto L12;
            this.originalFileName = _UrlKt.FRAGMENT_ENCODE_SET;
            return;
        L12:
            if (r6.originalFileName.equals(r6.fileName) == false) goto L22;
            this.originalFileName = this.fileName;
            return;
        L22:
            File r72 = new File(ApplicationLoader.getFilesDirFixed(), r6.originalFileName);     // Catch: Exception -> L15
            File r82 = ApplicationLoader.getFilesDirFixed();     // Catch: Exception -> L15
            String r1 = this.parentTheme.generateWallpaperName(this.parentAccent, true);     // Catch: Exception -> L15
            this.originalFileName = r1;     // Catch: Exception -> L15
            AndroidUtilities.copyFile(r72, new File(r82, r1));     // Catch: Exception -> L15
            return;
        L15:
            e = move-exception;
            this.originalFileName = _UrlKt.FRAGMENT_ENCODE_SET;
            FileLog.e(e);
            return;
        L20:
            File r73 = new File(ApplicationLoader.getFilesDirFixed(), r6.fileName);     // Catch: Exception -> L6
            File r12 = ApplicationLoader.getFilesDirFixed();     // Catch: Exception -> L6
            String r2 = this.parentTheme.generateWallpaperName(this.parentAccent, false);     // Catch: Exception -> L6
            this.fileName = r2;     // Catch: Exception -> L6
            AndroidUtilities.copyFile(r73, new File(r12, r2));     // Catch: Exception -> L6
        L6:
            e = move-exception;
            this.fileName = _UrlKt.FRAGMENT_ENCODE_SET;
            FileLog.e(e);
            goto L10
        }

        private void delete() {
            String r0 = getKey();
            ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit().remove(r0).apply();
            new File(ApplicationLoader.getFilesDirFixed(), this.fileName).delete();
            new File(ApplicationLoader.getFilesDirFixed(), this.originalFileName).delete();
        }

        private String getKey() {
            ThemeAccent r0 = this.parentAccent;
            ThemeInfo r1 = this.parentTheme;
            if (r0 == null) goto L7;
            return r1.name + "_" + this.parentAccent.id + "_owp";
        L7:
            return r1.name + "_owp";
        }

        private void save() {
            String r0 = getKey();     // Catch: Throwable -> L8
            SharedPreferences.Editor r1 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();     // Catch: Throwable -> L8
            JSONObject r2 = new JSONObject();     // Catch: Throwable -> L8
            r2.put("wall", this.fileName);     // Catch: Throwable -> L8
            r2.put("owall", this.originalFileName);     // Catch: Throwable -> L8
            r2.put("pColor", this.color);     // Catch: Throwable -> L8
            r2.put("pGrColor", this.gradientColor1);     // Catch: Throwable -> L8
            r2.put("pGrColor2", this.gradientColor2);     // Catch: Throwable -> L8
            r2.put("pGrColor3", this.gradientColor3);     // Catch: Throwable -> L8
            r2.put("pGrAngle", this.rotation);     // Catch: Throwable -> L8
            String r4 = this.slug;     // Catch: Throwable -> L8
            if (r4 != null) goto L6;
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r2.put("wallSlug", r4);     // Catch: Throwable -> L8
            r2.put("wBlur", this.isBlurred);     // Catch: Throwable -> L8
            r2.put("wMotion", this.isMotion);     // Catch: Throwable -> L8
            r2.put("pIntensity", this.intensity);     // Catch: Throwable -> L8
            r1.putString(r0, r2.toString());     // Catch: Throwable -> L8
            r1.apply();     // Catch: Throwable -> L8
            return;
        L8:
            th = move-exception;
            FileLog.e(th);
        }

        public boolean isColor() {
            return "c".equals(this.slug);
        }

        public boolean isDefault() {
            return "d".equals(this.slug);
        }

        public boolean isTheme() {
            return "t".equals(this.slug);
        }

        public void saveOverrideWallpaper() {
            ThemeInfo r0 = this.parentTheme;
            if (r0 == null) goto L14;
            ThemeAccent r1 = this.parentAccent;
            if (r1 == null) goto L7;
        L8:
            if (r1 != null) goto L10;
        L12:
            save();
            return;
        L10:
            if (r1.overrideWallpaper == this) goto L12;
            return;
        L7:
            if (r0.overrideWallpaper == this) goto L8;
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class PatternsLoader implements NotificationCenter.NotificationCenterDelegate {
        private static PatternsLoader loader;
        private int account;
        private HashMap<String, LoadingPattern> watingForLoad;

        public static class LoadingPattern {
            public ArrayList<ThemeAccent> accents;
            public TLRPC.TL_wallPaper pattern;

            private LoadingPattern() {
                this.accents = new ArrayList();
            }

            public /* synthetic */ LoadingPattern(ThemeIA r1) {
                this();
            }
        }

        public static /* synthetic */ void $r8$lambda$3rNQzJ7XM6Kf_cPG0Io0cqq9hz0(PatternsLoader r0, ArrayList r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$new$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$eHsdNJ7yChXMcy4GaCblr_Xsv6k(PatternsLoader r0, ArrayList r1, boolean r2) {
            r0.lambda$checkCurrentWallpaper$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$tbXVHEP65mAyRTJiwgt3l3RCEy0(PatternsLoader r0, LoadingPattern r1) {
            r0.lambda$didReceivedNotification$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$u0wOcgjyCZlAqRxTyz9g5IIxXj4(PatternsLoader r0, ArrayList r1) {
            r0.lambda$new$1(r1);
        }

        private PatternsLoader(ArrayList<ThemeAccent> r3) {
            this.account = UserConfig.selectedAccount;
            if (r3 != null) goto L5;
            return;
        L5:
            Utilities.globalQueue.postRunnable(new Theme$PatternsLoader$$ExternalSyntheticLambda0(this, r3));
        }

        private void checkCurrentWallpaper(ArrayList<ThemeAccent> r2, boolean r3) {
            AndroidUtilities.runOnUIThread(new Theme$PatternsLoader$$ExternalSyntheticLambda2(this, r2, r3));
        }

        private void checkCurrentWallpaperInternal(ArrayList<ThemeAccent> r8, boolean r9) {
            if (r8 != null) goto L4;
        L10:
            HashMap<String, LoadingPattern> r82 = this.watingForLoad;
            if (r9 == false) goto L17;
            if (r82 == null) goto L21;
            NotificationCenter.getInstance(this.account).addObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(this.account).addObserver(this, NotificationCenter.fileLoadFailed);
            Iterator<Map.Entry<String, LoadingPattern>> r83 = this.watingForLoad.entrySet().iterator();
        L15:
            if (r83.hasNext() == false) goto L26;
            LoadingPattern r92 = r83.next().getValue();
            FileLoader.getInstance(this.account).loadFile(ImageLocation.getForDocument(r92.pattern.document), "wallpaper", null, 0, 1);
            goto L15
        L26:
            return;
        L21:
            return;
        L17:
            if (r82 != null) goto L19;
        L22:
            NotificationCenter.getInstance(this.account).removeObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(this.account).removeObserver(this, NotificationCenter.fileLoadFailed);
            return;
        L19:
            if (r82.isEmpty() == true) goto L22;
            return;
        L4:
            if (Theme.m5798$$Nest$sfgetcurrentTheme().themeAccents == null) goto L10;
            if (Theme.m5798$$Nest$sfgetcurrentTheme().themeAccents.isEmpty() == true) goto L10;
            if (r8.contains(Theme.m5798$$Nest$sfgetcurrentTheme().getAccent(false)) == false) goto L10;
            Theme.reloadWallpaper(true);
            goto L10
        }

        public static void createLoader(boolean r8) {
            if (loader == null) goto L6;
            if (r8 == true) goto L6;
            return;
        L6:
            ArrayList r0 = null;
            int r1 = 0;
        L8:
            if (r1 >= 5) goto L40;
            if (r1 != 0) goto L11;
            String r2 = "Blue";
        L21:
            ThemeInfo r22 = (ThemeInfo) Theme.m5810$$Nest$sfgetthemesDict().get(r2);
            if (r22 == null) goto L39;
            ArrayList<ThemeAccent> r3 = r22.themeAccents;
            if (r3 == null) goto L39;
            if (r3.isEmpty() == true) goto L39;
            int r32 = r22.themeAccents.size();
            int r4 = 0;
        L29:
            if (r4 >= r32) goto L39;
            ThemeAccent r5 = r22.themeAccents.get(r4);
            if (r5.id == Theme.DEFALT_THEME_ACCENT_ID) goto L38;
            if (MonetAccentHelper.hasRemotePatternWallpaper(r5) == false) goto L38;
            if (r0 != null) goto L37;
            r0 = new ArrayList();
        L37:
            r0.add(r5);
        L38:
            r4 = r4 + 1;
        L39:
            r1 = r1 + 1;
            goto L8
        L11:
            if (r1 != 1) goto L13;
            r2 = "Dark Blue";
            goto L21
        L13:
            if (r1 != 2) goto L15;
            r2 = "Arctic Blue";
            goto L21
        L15:
            if (r1 == 3) goto L17;
            r2 = "Night";
            goto L21
        L17:
            r2 = "Day";
            goto L21
        L40:
            loader = new PatternsLoader(r0);
        }

        private Bitmap createWallpaperForAccent(Bitmap r18, boolean r19, File r20, ThemeAccent r21) {
            File r2 = r21.getPathToWallpaper();     // Catch: Throwable -> L15
            Drawable r3 = null;
            if (r2 != null) goto L6;
            return null;
        L6:
            ThemeInfo r4 = r21.parentTheme;     // Catch: Throwable -> L15
            SparseIntArray r5 = Theme.getThemeFileValues(null, r4.assetName, null);     // Catch: Throwable -> L15
            Theme.m5816$$Nest$smcheckIsDark(r5, r4);     // Catch: Throwable -> L15
            int r6 = r21.accentColor;     // Catch: Throwable -> L15
            int r7 = (int) r21.backgroundOverrideColor;     // Catch: Throwable -> L15
            long r8 = r21.backgroundGradientOverrideColor1;     // Catch: Throwable -> L15
            int r10 = (int) r8;     // Catch: Throwable -> L15
            if (r10 == 0) goto L9;
        L17:
            r6 = 0;
        L18:
            long r82 = r21.backgroundGradientOverrideColor2;     // Catch: Throwable -> L15
            int r14 = (int) r82;     // Catch: Throwable -> L15
            if (r14 == 0) goto L21;
        L25:
            long r83 = r21.backgroundGradientOverrideColor3;     // Catch: Throwable -> L15
            int r15 = (int) r83;     // Catch: Throwable -> L15
            if (r15 == 0) goto L28;
        L32:
            if (r7 != 0) goto L36;
            int r52 = r5.get(Theme.key_chat_wallpaper);     // Catch: Throwable -> L15
            if (r52 == 0) goto L36;
            r7 = Theme.changeColorAccent(r4, r6, r52);     // Catch: Throwable -> L15
        L36:
            if (r14 == 0) goto L38;
            int r42 = MotionBackgroundDrawable.getPatternColor(r7, r10, r14, r15);     // Catch: Throwable -> L15
        L41:
            if (r18 != null) goto L48;
            Point r53 = AndroidUtilities.displaySize;     // Catch: Throwable -> L15
            int r54 = Math.min(r53.x, r53.y);     // Catch: Throwable -> L15
            Point r62 = AndroidUtilities.displaySize;     // Catch: Throwable -> L15
            int r63 = Math.max(r62.x, r62.y);     // Catch: Throwable -> L15
            if (r19 == false) goto L46;
            Bitmap r0 = SvgHelper.getBitmap(r20, r54, r63, false, SvgHelper.ScaleMode.ByWidth);     // Catch: Throwable -> L15
        L45:
            Bitmap r55 = r0;
        L59:
            if (r3 == null) goto L55;
            Bitmap r64 = Bitmap.createBitmap(r55.getWidth(), r55.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L53
            Canvas r72 = new Canvas(r64);     // Catch: Throwable -> L53
            r3.setBounds(0, 0, r55.getWidth(), r55.getHeight());     // Catch: Throwable -> L53
            r3.draw(r72);     // Catch: Throwable -> L53
            Paint r32 = new Paint(2);     // Catch: Throwable -> L53
            r32.setColorFilter(new PorterDuffColorFilter(r42, PorterDuff.Mode.SRC_IN));     // Catch: Throwable -> L53
            r32.setAlpha((int) (Math.abs(r21.patternIntensity) * 255.0f));     // Catch: Throwable -> L53
            r72.drawBitmap(r55, 0.0f, 0.0f, r32);     // Catch: Throwable -> L53
            r64.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(r2));     // Catch: Throwable -> L53
            return r55;
        L55:
            FileOutputStream r1 = new FileOutputStream(r2);     // Catch: Throwable -> L53
            r55.compress(Bitmap.CompressFormat.PNG, 87, r1);     // Catch: Throwable -> L53
            r1.close();     // Catch: Throwable -> L53
            return r55;
        L53:
            th = th;
        L57:
            FileLog.e(th);
            return r55;
        L46:
            r0 = Theme.m5819$$Nest$smloadScreenSizedBitmap(new FileInputStream(r20), 0);     // Catch: Throwable -> L15
            goto L45
        L48:
            r55 = r18;
            goto L59
        L38:
            if (r10 == 0) goto L40;
            Drawable r43 = new BackgroundGradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r21.backgroundRotation), new int[]{r7, r10});     // Catch: Throwable -> L15
            r42 = AndroidUtilities.getPatternColor(AndroidUtilities.getAverageColor(r7, r10));     // Catch: Throwable -> L15
            r3 = r43;
            goto L41
        L40:
            r3 = new ColorDrawable(r7);     // Catch: Throwable -> L15
            r42 = AndroidUtilities.getPatternColor(r7);     // Catch: Throwable -> L15
            goto L41
        L28:
            if (r83 != 0) goto L32;
            int r84 = r5.get(Theme.key_chat_wallpaper_gradient_to3);     // Catch: Throwable -> L15
            if (r84 == 0) goto L32;
            r15 = Theme.changeColorAccent(r4, r6, r84);     // Catch: Throwable -> L15
            goto L32
        L21:
            if (r82 != 0) goto L25;
            int r85 = r5.get(Theme.key_chat_wallpaper_gradient_to2);     // Catch: Throwable -> L15
            if (r85 == 0) goto L25;
            r14 = Theme.changeColorAccent(r4, r6, r85);     // Catch: Throwable -> L15
            goto L25
        L9:
            if (r8 != 0) goto L17;
            if (r7 == 0) goto L12;
            r6 = r7;
        L12:
            int r86 = r5.get(Theme.key_chat_wallpaper_gradient_to1);     // Catch: Throwable -> L15
            if (r86 == 0) goto L18;
            r10 = Theme.changeColorAccent(r4, r6, r86);     // Catch: Throwable -> L15
        L15:
            th = th;
            r55 = r18;
            goto L57
        }

        private /* synthetic */ void lambda$checkCurrentWallpaper$2(ArrayList r1, boolean r2) {
            checkCurrentWallpaperInternal(r1, r2);
        }

        private /* synthetic */ void lambda$didReceivedNotification$3(LoadingPattern r11) {
            TLRPC.TL_wallPaper r0 = r11.pattern;
            File r1 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r0.document, true);
            int r2 = r11.accents.size();
            Bitmap r3 = null;
            ArrayList<ThemeAccent> r5 = null;
            int r6 = 0;
        L3:
            if (r6 >= r2) goto L10;
            ThemeAccent r7 = r11.accents.get(r6);
            if (r7.patternSlug.equals(r0.slug) == false) goto L9;
            r3 = createWallpaperForAccent(r3, "application/x-tgwallpattern".equals(r0.document.mime_type), r1, r7);
            if (r5 != null) goto L9;
            r5 = new ArrayList();
            r5.add(r7);
        L9:
            r6 = r6 + 1;
            goto L3
        L10:
            if (r3 == null) goto L12;
            r3.recycle();
        L12:
            checkCurrentWallpaper(r5, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v0 */
        /* JADX WARN: Type inference failed for: r12v1 */
        /* JADX WARN: Type inference failed for: r12v2 */
        /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.Boolean] */
        /* JADX WARN: Type inference failed for: r12v4 */
        /* JADX WARN: Type inference failed for: r12v5 */
        /* JADX WARN: Type inference failed for: r12v6 */
        /* JADX WARN: Type inference failed for: r4v2, types: [org.telegram.ui.ActionBar.Theme-IA] */
        /* JADX WARN: Type inference failed for: r4v3 */
        /* JADX WARN: Type inference failed for: r4v7 */
        private /* synthetic */ void lambda$new$0(ArrayList r17, TLObject r18, TLRPC.TL_error r19) {
            if ((r18 instanceof Vector) == false) goto L48;
            Vector r1 = (Vector) r18;
            int r2 = r1.objects.size();
            Bitmap r4 = null;
            ArrayList<ThemeAccent> r6 = null;
            int r5 = 0;
        L6:
            if (r5 >= r2) goto L38;
            TLRPC.WallPaper r8 = (TLRPC.WallPaper) r1.objects.get(r5);
            if ((r8 instanceof TLRPC.TL_wallPaper) == false) goto L37;
            TLRPC.TL_wallPaper r82 = (TLRPC.TL_wallPaper) r8;
            if (r82.pattern == false) goto L37;
            File r7 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r82.document, true);
            int r9 = r17.size();
            Bitmap r11 = r4;
            ?? r12 = r11;
            int r10 = 0;
            ?? r42 = r4;
        L13:
            if (r10 >= r9) goto L35;
            ThemeAccent r14 = (ThemeAccent) r17.get(r10);
            r12 = r12;
            if (r14.patternSlug.equals(r82.slug) == false) goto L33;
            if (r12 != 0) goto L18;
            r12 = Boolean.valueOf(r7.exists());
        L18:
            if (r11 == null) goto L20;
        L29:
            r11 = createWallpaperForAccent(r11, "application/x-tgwallpattern".equals(r82.document.mime_type), r7, r14);
            if (r6 != null) goto L32;
            r6 = new ArrayList();
        L32:
            r6.add(r14);
            goto L33
        L20:
            if (r12.booleanValue() == true) goto L29;
            String r3 = FileLoader.getAttachFileName(r82.document);
            if (this.watingForLoad != null) goto L25;
            this.watingForLoad = new HashMap();
        L25:
            LoadingPattern r15 = this.watingForLoad.get(r3);
            if (r15 != null) goto L28;
            r15 = new LoadingPattern(r42);
            r15.pattern = r82;
            this.watingForLoad.put(r3, r15);
        L28:
            r15.accents.add(r14);
        L33:
            r10 = r10 + 1;
            r42 = 0;
            r12 = r12;
            goto L13
        L35:
            if (r11 == null) goto L37;
            r11.recycle();
        L37:
            r5 = r5 + 1;
            r4 = null;
            goto L6
        L38:
            checkCurrentWallpaper(r6, true);
            return;
        }

        private /* synthetic */ void lambda$new$1(ArrayList r10) {
            int r0 = r10.size();
            ArrayList r1 = null;
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r0) goto L19;
            ThemeAccent r4 = (ThemeAccent) r10.get(r3);
            File r5 = r4.getPathToWallpaper();
            if (r5 != null) goto L7;
        L9:
            if (r1 != null) goto L12;
            r1 = new ArrayList();
        L12:
            if (MonetAccentHelper.hasRemotePatternWallpaper(r4) == false) goto L18;
            if (r1.contains(r4.patternSlug) == true) goto L18;
            r1.add(r4.patternSlug);
        L18:
            r3 = r3 + 1;
            goto L3
        L7:
            if (r5.length() <= 0) goto L9;
            r10.remove(r3);
            r3 = r3 - 1;
            r0 = r0 - 1;
            goto L18
        L19:
            if (r1 != null) goto L21;
            return;
        L21:
            TL_account.getMultiWallPapers r02 = new TL_account.getMultiWallPapers();
            int r32 = r1.size();
        L22:
            if (r2 >= r32) goto L24;
            TLRPC.TL_inputWallPaperSlug r42 = new TLRPC.TL_inputWallPaperSlug();
            r42.slug = (String) r1.get(r2);
            r02.wallpapers.add(r42);
            r2 = r2 + 1;
            goto L22
        L24:
            ConnectionsManager.getInstance(this.account).sendRequest(r02, new Theme$PatternsLoader$$ExternalSyntheticLambda3(this, r10));
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            HashMap<String, LoadingPattern> r42 = this.watingForLoad;
            if (r42 != null) goto L6;
            return;
        L6:
            if (r3 != NotificationCenter.fileLoaded) goto L12;
            LoadingPattern r32 = r42.remove((String) r5[0]);
            if (r32 == null) goto L17;
            Utilities.globalQueue.postRunnable(new Theme$PatternsLoader$$ExternalSyntheticLambda1(this, r32));
            return;
        L17:
            return;
        L12:
            if (r3 == NotificationCenter.fileLoadFailed) goto L14;
            return;
        L14:
            if (r42.remove((String) r5[0]) == null) goto L19;
            checkCurrentWallpaper(null, false);
            return;
        }
    }

    public interface ResourcesProvider {
        default void applyServiceShaderMatrix(int r1, int r2, float r3, float r4) {
            Theme.applyServiceShaderMatrix(r1, r2, r3, r4);
        }

        default ColorFilter getAnimatedEmojiColorFilter() {
            return Theme.chat_animatedEmojiTextColorFilter;
        }

        int getColor(int r1);

        default int getColorOrDefault(int r1) {
            return getColor(r1);
        }

        default int getCurrentColor(int r1) {
            return getColor(r1);
        }

        default Drawable getDrawable(String r1) {
            return null;
        }

        default Paint getPaint(String r1) {
            return Theme.getThemePaint(r1);
        }

        default boolean hasGradientService() {
            return false;
        }

        default boolean isDark() {
            return Theme.isCurrentThemeDark();
        }

        default boolean isMonet() {
            return Theme.isCurrentThemeMonet();
        }

        default void setAnimatedColor(int r1, int r2) {
        }
    }

    public static class RippleRadMaskDrawable extends Drawable {
        boolean invalidatePath;
        private float paddingBottom;
        private float paddingLeft;
        private float paddingRight;
        private float paddingTop;
        private Path path;
        private float[] radii;

        public RippleRadMaskDrawable(float r8, float r9) {
            this(r8, r9, 0, 0, 0, 0);
        }

        public RippleRadMaskDrawable(float r10, float r11, float r12, float r13) {
            this(r10, r11, r12, r13, 0, 0, 0, 0);
        }

        public RippleRadMaskDrawable(float r3, float r4, float r5, float r6, int r7, int r8, int r9, int r10) {
            this.path = new Path();
            float[] r0 = new float[8];
            this.radii = r0;
            this.invalidatePath = true;
            float r32 = AndroidUtilities.dp(r3);
            r0[1] = r32;
            r0[0] = r32;
            float[] r33 = this.radii;
            float r42 = AndroidUtilities.dp(r4);
            r33[3] = r42;
            r33[2] = r42;
            float[] r34 = this.radii;
            float r43 = AndroidUtilities.dp(r5);
            r34[5] = r43;
            r34[4] = r43;
            float[] r35 = this.radii;
            float r44 = AndroidUtilities.dp(r6);
            r35[7] = r44;
            r35[6] = r44;
            this.paddingLeft = AndroidUtilities.dpf2(r7);
            this.paddingTop = AndroidUtilities.dpf2(r8);
            this.paddingRight = AndroidUtilities.dpf2(r9);
            this.paddingBottom = AndroidUtilities.dpf2(r10);
        }

        public RippleRadMaskDrawable(float r10, float r11, int r12, int r13, int r14, int r15) {
            this(r10, r10, r11, r11, r12, r13, r14, r15);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            if (this.invalidatePath == false) goto L5;
            this.invalidatePath = false;
            this.path.reset();
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getBounds());
            r0.set(getBounds().left + this.paddingLeft, getBounds().top + this.paddingTop, getBounds().right - this.paddingRight, getBounds().bottom - this.paddingBottom);
            this.path.addRoundRect(r0, this.radii, Path.Direction.CW);
        L5:
            r7.drawPath(this.path, Theme.m5802$$Nest$sfgetmaskPaint());
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r1) {
            this.invalidatePath = true;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setPadding(float r1, float r2, float r3, float r4) {
            this.paddingLeft = AndroidUtilities.dpf2(r1);
            this.paddingTop = AndroidUtilities.dpf2(r2);
            this.paddingRight = AndroidUtilities.dpf2(r3);
            this.paddingBottom = AndroidUtilities.dpf2(r4);
            this.invalidatePath = true;
            invalidateSelf();
        }

        public void setRadius(float r1, float r2) {
            setRadius(r1, r1, r2, r2);
        }

        public void setRadius(float r4, float r5, float r6, float r7) {
            float[] r0 = this.radii;
            float r42 = AndroidUtilities.dp(r4);
            r0[1] = r42;
            r0[0] = r42;
            float[] r43 = this.radii;
            float r52 = AndroidUtilities.dp(r5);
            r43[3] = r52;
            r43[2] = r52;
            float[] r44 = this.radii;
            float r53 = AndroidUtilities.dp(r6);
            r44[5] = r53;
            r44[4] = r53;
            float[] r45 = this.radii;
            float r54 = AndroidUtilities.dp(r7);
            r45[7] = r54;
            r45[6] = r54;
            this.invalidatePath = true;
            invalidateSelf();
        }
    }

    public static class ThemeAccent {
        public int accentColor;
        public int accentColor2;
        public int account;
        public long backgroundGradientOverrideColor1;
        public long backgroundGradientOverrideColor2;
        public long backgroundGradientOverrideColor3;
        public long backgroundOverrideColor;
        public int backgroundRotation;
        public int id;
        public TLRPC.TL_theme info;
        public boolean isDefault;
        public int myMessagesAccentColor;
        public boolean myMessagesAnimated;
        public int myMessagesGradientAccentColor1;
        public int myMessagesGradientAccentColor2;
        public int myMessagesGradientAccentColor3;
        public OverrideWallpaperInfo overrideWallpaper;
        public ThemeInfo parentTheme;
        public TLRPC.TL_wallPaper pattern;
        public float patternIntensity;
        public boolean patternMotion;
        public String patternSlug;
        private float[] tempHSV;
        public TLRPC.InputFile uploadedFile;
        public TLRPC.InputFile uploadedThumb;
        public String uploadingFile;
        public String uploadingThumb;

        public ThemeAccent() {
            this.backgroundRotation = 45;
            this.patternSlug = _UrlKt.FRAGMENT_ENCODE_SET;
            this.tempHSV = new float[3];
        }

        private int averageColor(SparseIntArray r8, int... r9) {
            int r0 = 0;
            int r1 = 0;
            int r2 = 0;
            int r3 = 0;
            int r4 = 0;
        L4:
            if (r0 >= r9.length) goto L11;
            if (r8.indexOfKey(r9[r0]) < 0) goto L10;
            int r5 = r8.get(r9[r0]);     // Catch: Exception -> L15
            r2 = r2 + Color.red(r5);     // Catch: Exception -> L15
            r3 = r3 + Color.green(r5);     // Catch: Exception -> L15
            r4 = r4 + Color.blue(r5);
            r1 = r1 + 1;
        L10:
            r0 = r0 + 1;
            goto L4
        L11:
            if (r1 != 0) goto L14;
            return 0;
        L14:
            return Color.argb(255, r2 / r1, r3 / r1, r4 / r1);
        }

        private int bubbleSelectedOverlay(int r6, int r7) {
            Color.colorToHSV(r7, this.tempHSV);
            float[] r72 = this.tempHSV;
            float r1 = r72[0];
            Color.colorToHSV(r6, r72);
            float[] r62 = this.tempHSV;
            float r2 = r62[1];
            if (r2 > 0.0f) goto L5;
            r62[0] = r1;
        L5:
            r62[1] = Math.max(0.0f, Math.min(1.0f, r2 + 0.6f));
            float[] r63 = this.tempHSV;
            r63[2] = Math.max(0.0f, Math.min(1.0f, r63[2] - 0.05f));
            return Color.HSVToColor(30, this.tempHSV);
        }

        private int codeBackground(int r7, boolean r8) {
            Color.colorToHSV(r7, this.tempHSV);
            float[] r72 = this.tempHSV;
            if (r8 == false) goto L5;
            r72[1] = Utilities.clamp(r72[1] - 0.08f, 1.0f, 0.0f);
            this.tempHSV[2] = 0.03f;
            int r73 = 64;
        L16:
            return Color.HSVToColor(r73, this.tempHSV);
        L5:
            float r82 = r72[1];
            if (r82 <= 0.0f) goto L13;
            float r4 = r72[2];
            if (r4 >= 1.0f) goto L13;
            if (r4 <= 0.0f) goto L13;
            r72[1] = Math.max(0.0f, Math.min(1.0f, r82 + 0.28f));
            this.tempHSV[2] = Math.max(0.0f, Math.min(1.0f, r7[2] - 0.1f));
        L14:
            r73 = 32;
        L13:
            r72[2] = Math.max(0.0f, Math.min(1.0f, r72[2] - 0.2f));
            goto L14
        }

        private float getHue(int r2) {
            Color.colorToHSV(r2, this.tempHSV);
            return this.tempHSV[0];
        }

        private int linkSelectionBackground(int r5, int r6, boolean r7) {
            Color.colorToHSV(ColorUtils.blendARGB(r5, r6, 0.25f), this.tempHSV);
            float[] r52 = this.tempHSV;
            float r1 = 0.1f;
            r52[1] = Math.max(0.0f, Math.min(1.0f, r52[1] - 0.1f));
            float[] r53 = this.tempHSV;
            float r0 = r53[2];
            if (r7 == true) goto L6;
            r1 = 0.0f;
        L6:
            r53[2] = Math.max(0.0f, Math.min(1.0f, r0 + r1));
            return Color.HSVToColor(51, this.tempHSV);
        }

        private int locationPlaceholderColor(float r7, int r8, boolean r9) {
            if (r9 == false) goto L5;
            return 520093695;
        L5:
            Color.colorToHSV(r8, this.tempHSV);
            float[] r82 = this.tempHSV;
            if (r82[1] <= 0.0f) goto L13;
            float r0 = r82[2];
            if (r0 >= 1.0f) goto L13;
            if (r0 <= 0.0f) goto L13;
            r82[0] = MathUtils.clamp(r82[0] + 0.22f, 0.0f, 1.0f);
            float[] r72 = this.tempHSV;
            r72[1] = MathUtils.clamp(r72[1] - 0.35f, 0.0f, 1.0f);
        L14:
            float[] r73 = this.tempHSV;
            r73[2] = MathUtils.clamp(r73[2] - 0.65f, 0.0f, 1.0f);
            return Color.HSVToColor(90, this.tempHSV);
        L13:
            r82[0] = r7;
            r82[1] = 0.2f;
            goto L14
        }

        private int textSelectionBackground(boolean r6, int r7, int r8) {
            Color.colorToHSV(r8, this.tempHSV);
            float[] r62 = this.tempHSV;
            float r0 = r62[0];
            Color.colorToHSV(r7, r62);
            float[] r63 = this.tempHSV;
            float r1 = r63[1];
            if (r1 <= 0.0f) goto L8;
            float r3 = r63[0];
            if (r3 <= 45.0f) goto L10;
            if (r3 < 85.0f) goto L8;
        L10:
            if (r63[2] <= 0.85f) goto L12;
            float r02 = 0.25f;
        L13:
            r63[1] = Math.max(0.0f, Math.min(1.0f, r1 + r02));
            float[] r64 = this.tempHSV;
            r64[2] = Math.max(0.0f, Math.min(1.0f, r64[2] - 0.15f));
            return Color.HSVToColor(80, this.tempHSV);
        L12:
            r02 = 0.45f;
        L8:
            r63[0] = r0;
            goto L10
        }

        private int textSelectionHandle(int r8, int r9) {
            Color.colorToHSV(r9, this.tempHSV);
            float[] r92 = this.tempHSV;
            float r1 = r92[0];
            Color.colorToHSV(r8, r92);
            float[] r93 = this.tempHSV;
            float r3 = r93[1];
            if (r3 <= 0.0f) goto L8;
            float r5 = r93[0];
            if (r5 > 45.0f) goto L7;
        L9:
            r93[1] = Math.max(0.0f, Math.min(1.0f, r3 + 0.6f));
            float[] r94 = this.tempHSV;
            float r2 = r94[2];
            if (r2 <= 0.7f) goto L12;
            float r32 = 0.25f;
        L13:
            r94[2] = Math.max(0.0f, Math.min(1.0f, r2 - r32));
            return Theme.blendOver(r8, Color.HSVToColor(255, this.tempHSV));
        L12:
            r32 = 0.125f;
            goto L13
        L7:
            if (r5 >= 85.0f) goto L9;
        L8:
            r93[0] = r1;
            goto L9
        }

        public boolean fillAccentColors(SparseIntArray r20, SparseIntArray r21) {
            float[] r4 = Theme.getTempHsv(1);
            float[] r6 = Theme.getTempHsv(2);
            Color.colorToHSV(this.parentTheme.accentBaseColor, r4);
            Color.colorToHSV(this.accentColor, r6);
            boolean r7 = this.parentTheme.isDark();
            if (this.accentColor == this.parentTheme.accentBaseColor) goto L5;
        L6:
            int r8 = 0;
        L8:
            if (r8 >= Theme.m5799$$Nest$sfgetdefaultColors().length) goto L25;
            if (Theme.m5809$$Nest$sfgetthemeAccentExclusionKeys().contains(Integer.valueOf(r8)) == true) goto L24;
            int r9 = r20.indexOfKey(r8);
            if (r9 >= 0) goto L20;
            int r92 = Theme.m5800$$Nest$sfgetfallbackKeys().get(r8, -1);
            if (r92 >= 0) goto L17;
        L19:
            int r93 = Theme.m5799$$Nest$sfgetdefaultColors()[r8];
        L21:
            int r12 = Theme.changeColorAccent(r4, r6, r93, r7, r93);
            if (r12 == r93) goto L24;
            r21.put(r8, r12);
            goto L24
        L17:
            if (r20.indexOfKey(r92) < 0) goto L19;
        L20:
            r93 = r20.valueAt(r9);
        L24:
            r8 = r8 + 1;
        L25:
            int r82 = this.myMessagesAccentColor;
            float r94 = 0.705f;
            if (r82 != 0) goto L32;
            if (this.accentColor != 0) goto L32;
        L30:
            char r16 = 2;
            boolean r5 = false;
        L57:
            if (r82 == 0) goto L65;
            int r122 = this.parentTheme.accentBaseColor;
            if (r122 == 0) goto L61;
            if (r82 == r122) goto L61;
        L64:
            boolean r123 = true;
        L66:
            if (r123 == false) goto L68;
        L71:
            int r13 = this.accentColor2;
            if (r13 == 0) goto L74;
            Color.colorToHSV(r13, r6);
        L75:
            int r132 = Theme.myMessagesStartIndex;
        L77:
            if (r132 >= Theme.myMessagesEndIndex) goto L91;
            int r14 = r20.indexOfKey(r132);
            if (r14 >= 0) goto L86;
            int r142 = Theme.m5800$$Nest$sfgetfallbackKeys().get(r132, -1);
            if (r142 >= 0) goto L83;
        L85:
            int r143 = Theme.m5799$$Nest$sfgetdefaultColors()[r132];
        L87:
            int r15 = Theme.changeColorAccent(r4, r6, r143, r7, r143);
            if (r15 == r143) goto L90;
            r21.put(r132, r15);
        L90:
            r132 = r132 + 1;
            goto L77
        L83:
            if (r20.get(r142, -1) < 0) goto L85;
        L86:
            r143 = r20.valueAt(r14);
            goto L87
        L91:
            int[] r133 = Theme.m5803$$Nest$sfgetmyMessagesAccentExtraKeys();
            int r144 = r133.length;
            int r152 = 0;
        L92:
            if (r152 >= r144) goto L101;
            float r17 = r94;
            int r95 = r133[r152];
            int r3 = r20.indexOfKey(r95);
            if (r3 >= 0) goto L96;
            int r32 = Theme.m5799$$Nest$sfgetdefaultColors()[r95];
        L97:
            int r11 = Theme.changeColorAccent(r4, r6, r32, r7, r32);
            if (r11 == r32) goto L100;
            r21.put(r95, r11);
        L100:
            r152 = r152 + 1;
            r94 = r17;
            goto L92
        L96:
            r32 = r20.valueAt(r3);
            goto L97
        L101:
            float r172 = r94;
            if (r123 == false) goto L119;
            Color.colorToHSV(r82, r6);
            int r33 = Theme.myMessagesBubblesStartIndex;
        L105:
            if (r33 >= Theme.myMessagesBubblesEndIndex) goto L119;
            int r83 = r20.indexOfKey(r33);
            if (r83 >= 0) goto L114;
            int r84 = Theme.m5800$$Nest$sfgetfallbackKeys().get(r33, -1);
            if (r84 >= 0) goto L111;
        L113:
            int r85 = Theme.m5799$$Nest$sfgetdefaultColors()[r33];
        L115:
            int r96 = Theme.changeColorAccent(r4, r6, r85, r7, r85);
            if (r96 == r85) goto L118;
            r21.put(r33, r96);
        L118:
            r33 = r33 + 1;
            goto L105
        L111:
            if (r20.get(r84, -1) < 0) goto L113;
        L114:
            r85 = r20.valueAt(r83);
        L119:
            if (r5 == true) goto L139;
            int r34 = this.myMessagesGradientAccentColor1;
            if (r34 == 0) goto L139;
            int r42 = this.myMessagesGradientAccentColor2;
            int r62 = this.myMessagesAccentColor;
            if (r42 == 0) goto L131;
            int r35 = AndroidUtilities.getAverageColor(AndroidUtilities.getAverageColor(r62, r34), this.myMessagesGradientAccentColor2);
            int r43 = this.myMessagesGradientAccentColor3;
            if (r43 == 0) goto L128;
            r35 = AndroidUtilities.getAverageColor(r35, r43);
        L128:
            if (AndroidUtilities.computePerceivedBrightness(r35) <= r172) goto L130;
            boolean r36 = true;
        L132:
            if (r36 == false) goto L134;
            int r37 = -14606047;
            int r44 = -11184811;
            int r63 = 1291845632;
        L136:
            if (this.accentColor2 != 0) goto L138;
            r21.put(Theme.key_chat_outAudioProgress, r63);
            r21.put(Theme.key_chat_outAudioSelectedProgress, r63);
            r21.put(Theme.key_chat_outAudioSeekbar, r63);
            r21.put(Theme.key_chat_outAudioCacheSeekbar, r63);
            r21.put(Theme.key_chat_outAudioSeekbarSelected, r63);
            r21.put(Theme.key_chat_outAudioSeekbarFill, r37);
            r21.put(Theme.key_chat_outVoiceSeekbar, r63);
            r21.put(Theme.key_chat_outVoiceSeekbarSelected, r63);
            r21.put(Theme.key_chat_outVoiceSeekbarFill, r37);
            r21.put(Theme.key_chat_messageLinkOut, r37);
            r21.put(Theme.key_chat_outForwardedNameText, r37);
            r21.put(Theme.key_chat_outViaBotNameText, r37);
            r21.put(Theme.key_chat_outReplyLine, r37);
            r21.put(Theme.key_chat_outReplyLine2, r37);
            r21.put(Theme.key_chat_outReplyNameText, r37);
            r21.put(Theme.key_chat_outPreviewLine, r37);
            r21.put(Theme.key_chat_outSiteNameText, r37);
            r21.put(Theme.key_chat_outInstant, r37);
            r21.put(Theme.key_chat_outInstantSelected, r37);
            r21.put(Theme.key_chat_outPreviewInstantText, r37);
            r21.put(Theme.key_chat_outViews, r37);
            r21.put(Theme.key_chat_outViewsSelected, r37);
            r21.put(Theme.key_chat_outAudioTitleText, r37);
            r21.put(Theme.key_chat_outFileNameText, r37);
            r21.put(Theme.key_chat_outContactNameText, r37);
            r21.put(Theme.key_chat_outAudioPerformerText, r37);
            r21.put(Theme.key_chat_outAudioPerformerSelectedText, r37);
            r21.put(Theme.key_chat_outSentCheck, r37);
            r21.put(Theme.key_chat_outSentCheckSelected, r37);
            r21.put(Theme.key_chat_outSentCheckRead, r37);
            r21.put(Theme.key_chat_outSentCheckReadSelected, r37);
            r21.put(Theme.key_chat_outSentClock, r37);
            r21.put(Theme.key_chat_outSentClockSelected, r37);
            r21.put(Theme.key_chat_outMenu, r37);
            r21.put(Theme.key_chat_outMenuSelected, r37);
            r21.put(Theme.key_chat_outTimeText, r37);
            r21.put(Theme.key_chat_outTimeSelectedText, r37);
            r21.put(Theme.key_chat_outAudioDurationText, r44);
            r21.put(Theme.key_chat_outAudioDurationSelectedText, r44);
            r21.put(Theme.key_chat_outContactPhoneText, r44);
            r21.put(Theme.key_chat_outContactPhoneSelectedText, r44);
            r21.put(Theme.key_chat_outFileInfoText, r44);
            r21.put(Theme.key_chat_outFileInfoSelectedText, r44);
            r21.put(Theme.key_chat_outVenueInfoText, r44);
            r21.put(Theme.key_chat_outVenueInfoSelectedText, r44);
            r21.put(Theme.key_chat_outLoader, r37);
            r21.put(Theme.key_chat_outLoaderSelected, r37);
            r21.put(Theme.key_chat_outFileProgress, this.myMessagesAccentColor);
            r21.put(Theme.key_chat_outFileProgressSelected, this.myMessagesAccentColor);
            r21.put(Theme.key_chat_outMediaIcon, this.myMessagesAccentColor);
            r21.put(Theme.key_chat_outMediaIconSelected, this.myMessagesAccentColor);
        L138:
            r21.put(Theme.key_chat_outReplyMessageText, r37);
            r21.put(Theme.key_chat_outReplyMediaMessageText, r37);
            r21.put(Theme.key_chat_outReplyMediaMessageSelectedText, r37);
            r21.put(Theme.key_chat_messageTextOut, r37);
            goto L139
        L134:
            r44 = -1118482;
            r63 = 1308622847;
            r37 = -1;
            goto L136
        L130:
            r36 = false;
            goto L132
        L131:
            r36 = Theme.m5822$$Nest$smuseBlackText(r62, r34);
        L139:
            if (r5 == false) goto L147;
            int r38 = Theme.key_chat_outLoader;
            if (r21.indexOfKey(r38) < 0) goto L143;
            int r39 = r21.get(r38);
        L145:
            if (AndroidUtilities.getColorDistance(-1, r39) >= 5000) goto L147;
            r5 = false;
            goto L147
        L143:
            r39 = 0;
        L147:
            int r310 = this.myMessagesAccentColor;
            if (r310 != 0) goto L150;
        L157:
            long r311 = this.backgroundOverrideColor;
            int r64 = (int) r311;
            if (r64 == 0) goto L161;
            r21.put(Theme.key_chat_wallpaper, r64);
        L163:
            long r312 = this.backgroundGradientOverrideColor1;
            int r65 = (int) r312;
            if (r65 == 0) goto L167;
            r21.put(Theme.key_chat_wallpaper_gradient_to1, r65);
        L169:
            long r313 = this.backgroundGradientOverrideColor2;
            int r66 = (int) r313;
            if (r66 == 0) goto L173;
            r21.put(Theme.key_chat_wallpaper_gradient_to2, r66);
        L175:
            long r314 = this.backgroundGradientOverrideColor3;
            int r67 = (int) r314;
            if (r67 == 0) goto L179;
            r21.put(Theme.key_chat_wallpaper_gradient_to3, r67);
        L181:
            int r315 = this.backgroundRotation;
            if (r315 == 45) goto L184;
            r21.put(Theme.key_chat_wallpaper_gradient_rotation, r315);
        L184:
            int r316 = Theme.key_chat_outBubble;
            int r45 = r21.get(r316);
            if (r45 != 0) goto L187;
            r45 = Theme.getColor(r316);
        L187:
            int r317 = Theme.key_chat_inBubble;
            int r68 = r21.get(r317);
            if (r68 != 0) goto L190;
            r68 = Theme.getColor(r317);
        L190:
            TLRPC.TL_theme r318 = this.info;
            if (r318 != null) goto L193;
        L201:
            if (r7 == true) goto L203;
            r21.put(Theme.key_chat_inTextSelectionHighlight, textSelectionBackground(false, r68, this.accentColor));
            r21.put(Theme.key_chat_outTextSelectionHighlight, textSelectionBackground(true, r45, this.accentColor));
            r21.put(Theme.key_chat_outTextSelectionCursor, textSelectionHandle(r45, this.accentColor));
        L203:
            float r319 = getHue(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
            r21.put(Theme.key_chat_outBubbleLocationPlaceholder, locationPlaceholderColor(r319, r45, r7));
            r21.put(Theme.key_chat_inBubbleLocationPlaceholder, locationPlaceholderColor(r319, r68, r7));
            int r320 = Theme.key_chat_messageLinkIn;
            int r86 = r21.get(r320);
            if (r86 != 0) goto L206;
            r86 = Theme.getColor(r320);
        L206:
            int r321 = Theme.key_chat_messageLinkOut;
            int r97 = r21.get(r321);
            if (r97 != 0) goto L209;
            r97 = Theme.getColor(r321);
        L209:
            r21.put(Theme.key_chat_linkSelectBackground, linkSelectionBackground(r86, r68, r7));
            r21.put(Theme.key_chat_outLinkSelectBackground, linkSelectionBackground(r97, r45, r7));
            int r322 = Theme.key_actionBarDefaultSubmenuBackground;
            int r69 = r21.get(r322);
            if (r69 != 0) goto L212;
            r69 = Theme.getColor(r322);
        L212:
            r21.put(Theme.key_actionBarDefaultSubmenuSeparator, Color.argb(Color.alpha(r69), Math.max(0, Color.red(r69) - 10), Math.max(0, Color.green(r69) - 10), Math.max(0, Color.blue(r69) - 10)));
            if (r7 == false) goto L217;
            int r323 = Theme.key_chat_outBubbleGradient1;
            if (r21.get(r323) == 0) goto L217;
            Color.colorToHSV(averageColor(r21, new int[]{r323, Theme.key_chat_outBubbleGradient2, Theme.key_chat_outBubbleGradient3}), this.tempHSV);
            float[] r324 = this.tempHSV;
            r324[1] = Utilities.clamp(r324[1] + 0.1f, 1.0f, 0.0f);
            float[] r325 = this.tempHSV;
            r325[r16] = Utilities.clamp(r325[r16] - 0.8f, 1.0f, 0.0f);
            r21.put(Theme.key_chat_outCodeBackground, Color.HSVToColor(64, this.tempHSV));
        L218:
            Theme.m5815$$Nest$smapplyCalculatedTableColors(r20, r21, r7);
            Theme.m5814$$Nest$smapplyCalculatedArticleCodeColors(r20, r21, r7);
            return !r5;
        L217:
            r21.put(Theme.key_chat_outCodeBackground, codeBackground(r45, r7));
            goto L218
        L193:
            if (r318.emoticon == null) goto L201;
            if (r7 == true) goto L201;
            r21.delete(Theme.key_chat_selectedBackground);
            int r326 = averageColor(r21, new int[]{Theme.key_chat_wallpaper_gradient_to1, Theme.key_chat_wallpaper_gradient_to2, Theme.key_chat_wallpaper_gradient_to3});
            if (r326 != 0) goto L198;
            r326 = averageColor(r21, new int[]{Theme.key_chat_wallpaper});
        L198:
            if (r326 != 0) goto L200;
            r326 = this.accentColor;
        L200:
            int r327 = bubbleSelectedOverlay(r45, r326);
            r21.put(Theme.key_chat_outBubbleSelectedOverlay, r327);
            r21.put(Theme.key_chat_outBubbleGradientSelectedOverlay, r327);
            r21.put(Theme.key_chat_outBubbleSelected, Theme.blendOver(r45, r327));
            int r328 = bubbleSelectedOverlay(r68, this.accentColor);
            r21.put(Theme.key_chat_inBubbleSelectedOverlay, r328);
            r21.put(Theme.key_chat_inBubbleSelected, Theme.blendOver(r68, r328));
            goto L201
        L179:
            if (r314 == 0) goto L181;
            r21.delete(Theme.key_chat_wallpaper_gradient_to3);
            goto L181
        L173:
            if (r313 == 0) goto L175;
            r21.delete(Theme.key_chat_wallpaper_gradient_to2);
            goto L175
        L167:
            if (r312 == 0) goto L169;
            r21.delete(Theme.key_chat_wallpaper_gradient_to1);
            goto L169
        L161:
            if (r311 == 0) goto L163;
            r21.delete(Theme.key_chat_wallpaper);
            goto L163
        L150:
            if (this.myMessagesGradientAccentColor1 == 0) goto L157;
            r21.put(Theme.key_chat_outBubble, r310);
            r21.put(Theme.key_chat_outBubbleGradient1, this.myMessagesGradientAccentColor1);
            int r329 = this.myMessagesGradientAccentColor2;
            if (r329 == 0) goto L156;
            r21.put(Theme.key_chat_outBubbleGradient2, r329);
            int r330 = this.myMessagesGradientAccentColor3;
            if (r330 == 0) goto L156;
            r21.put(Theme.key_chat_outBubbleGradient3, r330);
        L156:
            r21.put(Theme.key_chat_outBubbleGradientAnimated, this.myMessagesAnimated ? 1 : 0);
            goto L157
        L74:
            Color.colorToHSV(r82, r6);
            goto L75
        L68:
            if (this.accentColor2 != 0) goto L71;
            r172 = 0.705f;
        L61:
            int r124 = this.accentColor;
            if (r124 == 0) goto L65;
            if (r124 != r82) goto L64;
        L65:
            r123 = false;
        L32:
            if (this.myMessagesGradientAccentColor1 == 0) goto L30;
            if (r82 != 0) goto L36;
            r82 = this.accentColor;
        L36:
            int r125 = Theme.key_chat_outBubble;
            int r134 = r20.get(r125);
            if (r134 != 0) goto L39;
            r134 = Theme.m5799$$Nest$sfgetdefaultColors()[r125];
        L39:
            int r126 = AndroidUtilities.getColorDistance(r82, Theme.changeColorAccent(r4, r6, r134, r7, r134));
            int r145 = AndroidUtilities.getColorDistance(r82, this.myMessagesGradientAccentColor1);
            int r153 = this.myMessagesGradientAccentColor2;
            r16 = 2;
            int r52 = this.myMessagesAccentColor;
            if (r153 == 0) goto L48;
            int r53 = AndroidUtilities.getAverageColor(AndroidUtilities.getAverageColor(r52, this.myMessagesGradientAccentColor1), this.myMessagesGradientAccentColor2);
            int r154 = this.myMessagesGradientAccentColor3;
            if (r154 == 0) goto L45;
            r53 = AndroidUtilities.getAverageColor(r53, r154);
        L45:
            if (AndroidUtilities.computePerceivedBrightness(r53) <= 0.705f) goto L47;
            boolean r54 = true;
        L49:
            if (r54 == true) goto L51;
        L54:
            r5 = false;
        L55:
            r82 = Theme.m5817$$Nest$smgetAccentColor(r4, r134, r82);
            goto L57
        L51:
            if (r126 > 35000) goto L54;
            if (r145 > 35000) goto L54;
            r5 = true;
            goto L55
        L47:
            r54 = false;
            goto L49
        L48:
            r54 = Theme.m5822$$Nest$smuseBlackText(r52, this.myMessagesGradientAccentColor1);
            goto L49
        L5:
            if (this.accentColor2 == 0) goto L25;
            goto L6
        }

        public File getPathToWallpaper() {
            int r0 = this.id;
            String r1 = this.patternSlug;
            if (r0 >= 100) goto L10;
            if (TextUtils.isEmpty(r1) == false) goto L7;
            return null;
        L7:
            return new File(ApplicationLoader.getFilesDirFixed(), String.format(Locale.US, "%s_%d_%s_v5.jpg", new Object[]{this.parentTheme.getKey(), Integer.valueOf(this.id), this.patternSlug}));
        L10:
            if (TextUtils.isEmpty(r1) == false) goto L12;
            return null;
        L12:
            return new File(ApplicationLoader.getFilesDirFixed(), String.format(Locale.US, "%s_%d_%s_v8_debug.jpg", new Object[]{this.parentTheme.getKey(), Integer.valueOf(this.id), this.patternSlug}));
        }

        public void resetAccentColorsForMyMessagesGiftThemeLight(SparseIntArray r2) {
            int r1 = Theme.myMessagesBubblesStartIndex;
        L4:
            if (r1 >= Theme.myMessagesBubblesEndIndex) goto L6;
            r2.delete(r1);
            r2.put(r1, Theme.m5799$$Nest$sfgetdefaultColors()[r1]);
            r1 = r1 + 1;
            goto L4
        L6:
            int r12 = Theme.myMessagesStartIndex;
        L8:
            if (r12 >= Theme.myMessagesEndIndex) goto L10;
            r2.delete(r12);
            r2.put(r12, Theme.m5799$$Nest$sfgetdefaultColors()[r12]);
            r12 = r12 + 1;
            goto L8
        L10:
            int r13 = Theme.myMessages2StartIndex;
        L12:
            if (r13 >= Theme.myMessages2EndIndex) goto L14;
            r2.delete(r13);
            r2.put(r13, Theme.m5799$$Nest$sfgetdefaultColors()[r13]);
            r13 = r13 + 1;
            goto L12
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x0245 -> B:93:0x0254). Please report as a decompilation issue!!! */
        public File saveToFile() {
            File r0 = AndroidUtilities.getSharingDirectory();
            r0.mkdirs();
            File r1 = new File(r0, String.format(Locale.US, "%s_%d.attheme", new Object[]{this.parentTheme.getKey(), Integer.valueOf(this.id)}));
            FileOutputStream r2 = null;
            SparseIntArray r02 = Theme.getThemeFileValues(null, this.parentTheme.assetName, null);
            SparseIntArray r3 = r02.clone();
            fillAccentColors(r02, r3);
            int r4 = 0;
            if (TextUtils.isEmpty(this.patternSlug) == true) goto L43;
            StringBuilder r03 = new StringBuilder();
            if (this.patternMotion == false) goto L7;
            r03.append("motion");
        L7:
            int r5 = r3.get(Theme.key_chat_wallpaper);
            if (r5 != 0) goto L10;
            r5 = -1;
        L10:
            int r6 = r3.get(Theme.key_chat_wallpaper_gradient_to1);
            if (r6 != 0) goto L13;
            r6 = 0;
        L13:
            int r7 = r3.get(Theme.key_chat_wallpaper_gradient_to2);
            if (r7 != 0) goto L16;
            r7 = 0;
        L16:
            int r8 = r3.get(Theme.key_chat_wallpaper_gradient_to3);
            if (r8 != 0) goto L19;
            r8 = 0;
        L19:
            int r9 = r3.get(Theme.key_chat_wallpaper_gradient_rotation);
            if (r9 != 0) goto L22;
            r9 = 45;
        L22:
            String r52 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r5 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r5 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r5 & 255))}).toLowerCase();
            if (r6 == 0) goto L25;
            String r62 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r6 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r6 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r6 & 255))}).toLowerCase();
        L26:
            if (r7 == 0) goto L28;
            String r72 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r7 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r7 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r7 & 255))}).toLowerCase();
        L29:
            if (r8 == 0) goto L31;
            String r82 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r8 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r8 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r8 & 255))}).toLowerCase();
        L32:
            if (r62 == null) goto L38;
            if (r72 == null) goto L38;
            if (r82 == null) goto L37;
            r52 = r52 + "~" + r62 + "~" + r72 + "~" + r82;
        L40:
            String r13 = "https://attheme.org?slug=" + this.patternSlug + "&intensity=" + ((int) (this.patternIntensity * 100.0f)) + "&bg_color=" + r52;
            if (r03.length() <= 0) goto L44;
            r13 = r13 + "&mode=" + r03;
        L44:
            StringBuilder r04 = new StringBuilder();
        L94:
            if (r4 >= r3.size()) goto L90;
            int r53 = r3.keyAt(r4);
            int r73 = r3.valueAt(r4);
            if (r13 != null) goto L50;
        L58:
            String r54 = ThemeColors.getStringName(r53);
            if (r54 == null) goto L61;
            r04.append(r54);
            r04.append("=");
            r04.append(r73);
            r04.append("\n");
        L61:
            r4 = r4 + 1;
            goto L94
        L50:
            if (Theme.key_chat_wallpaper == r53) goto L61;
            if (Theme.key_chat_wallpaper_gradient_to1 == r53) goto L61;
            if (Theme.key_chat_wallpaper_gradient_to2 == r53) goto L61;
            if (Theme.key_chat_wallpaper_gradient_to3 != r53) goto L58;
        L90:
            FileOutputStream r32 = new FileOutputStream(r1);     // Catch: Throwable -> L75 Exception -> L77
            r32.write(AndroidUtilities.getStringBytes(r04.toString()));     // Catch: Throwable -> L67 Exception -> L69
            if (TextUtils.isEmpty(r13) == true) goto L71;
            r32.write(AndroidUtilities.getStringBytes("WLS=" + r13 + "\n"));     // Catch: Throwable -> L67 Exception -> L69
        L71:
            r32.close();     // Catch: Exception -> L73
            return r1;
        L69:
            e = e;
            r2 = r32;
        L78:
            FileLog.e(e);     // Catch: Throwable -> L75
            if (r2 == null) goto L81;
            r2.close();     // Catch: Exception -> L73
        L93:
        L81:
            return r1;
        L67:
            th = th;
            r2 = r32;
        L82:
            if (r2 != null) goto L88;
        L87:
            throw th;
        L88:
            r2.close();     // Catch: Exception -> L85
        L85:
            e = move-exception;
            FileLog.e(e);
        L77:
            e = e;
        L75:
            th = th;
        L73:
            e = move-exception;
            FileLog.e(e);
            goto L93
        L37:
            r52 = r52 + "~" + r62 + "~" + r72;
        L38:
            if (r62 == null) goto L40;
            r52 = (r52 + "-" + r62) + "&rotation=" + r9;
            goto L40
        L31:
            r82 = null;
            goto L32
        L28:
            r72 = null;
            goto L29
        L25:
            r62 = null;
            goto L26
        L43:
            r13 = null;
            goto L44
        }
    }

    public static class ThemeInfo implements NotificationCenter.NotificationCenterDelegate {
        public int accentBaseColor;
        public LongSparseArray<ThemeAccent> accentsByThemeId;
        public int account;
        public String assetName;
        public boolean badWallpaper;
        public LongSparseArray<ThemeAccent> chatAccentsByThemeId;
        public int currentAccentId;
        public int defaultAccentCount;
        public boolean firstAccentIsDefault;
        public TLRPC.TL_theme info;
        public boolean isBlured;
        private int isDark;
        public boolean isMotion;
        public int lastAccentId;
        public int lastChatThemeId;
        public boolean loaded;
        private String loadingThemeWallpaperName;
        public String name;
        private String newPathToWallpaper;
        public OverrideWallpaperInfo overrideWallpaper;
        public String pathToFile;
        public String pathToWallpaper;
        public int patternBgColor;
        public int patternBgGradientColor1;
        public int patternBgGradientColor2;
        public int patternBgGradientColor3;
        public int patternBgGradientRotation;
        public int patternIntensity;
        public int prevAccentId;
        private int previewBackgroundColor;
        public int previewBackgroundGradientColor1;
        public int previewBackgroundGradientColor2;
        public int previewBackgroundGradientColor3;
        private int previewInColor;
        private int previewOutColor;
        public boolean previewParsed;
        public int previewWallpaperOffset;
        public String slug;
        public int sortIndex;
        public ArrayList<ThemeAccent> themeAccents;
        public SparseArray<ThemeAccent> themeAccentsMap;
        public boolean themeLoaded;
        public TLRPC.InputFile uploadedFile;
        public TLRPC.InputFile uploadedThumb;
        public String uploadingFile;
        public String uploadingThumb;

        /* renamed from: $r8$lambda$0J8BBGaNvYm4fGPo-m8ukTAwUMM, reason: not valid java name */
        public static /* synthetic */ void m5825$r8$lambda$0J8BBGaNvYm4fGPom8ukTAwUMM(ThemeInfo r0, TLObject r1, ThemeInfo r2) {
            r0.lambda$didReceivedNotification$1(r1, r2);
        }

        /* renamed from: $r8$lambda$8BL_TR-Fk6MdGRJ7NNF1qoRaA1k, reason: not valid java name */
        public static /* synthetic */ void m5826$r8$lambda$8BL_TRFk6MdGRJ7NNF1qoRaA1k(ThemeInfo r0, ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$didReceivedNotification$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$NNJJ9oig8NesviXv6JwHHA8VRFk(ThemeInfo r0) {
            r0.onFinishLoadingRemoteTheme();
        }

        public static /* synthetic */ void $r8$lambda$dzPIYzOe8uYoRiT7_jl5LD5uCs0(ThemeInfo r0, File r1) {
            r0.lambda$didReceivedNotification$0(r1);
        }

        /* renamed from: -$$Nest$fgetisDark, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5827$$Nest$fgetisDark(ThemeInfo r0) {
            return r0.isDark;
        }

        /* renamed from: -$$Nest$fputisDark, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5828$$Nest$fputisDark(ThemeInfo r0, int r1) {
            r0.isDark = r1;
        }

        /* renamed from: -$$Nest$fputpreviewBackgroundColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5829$$Nest$fputpreviewBackgroundColor(ThemeInfo r0, int r1) {
            r0.previewBackgroundColor = r1;
        }

        /* renamed from: -$$Nest$fputpreviewInColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5830$$Nest$fputpreviewInColor(ThemeInfo r0, int r1) {
            r0.previewInColor = r1;
        }

        /* renamed from: -$$Nest$fputpreviewOutColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5831$$Nest$fputpreviewOutColor(ThemeInfo r0, int r1) {
            r0.previewOutColor = r1;
        }

        /* renamed from: -$$Nest$misDefaultMainAccent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5832$$Nest$misDefaultMainAccent(ThemeInfo r0) {
            return r0.isDefaultMainAccent();
        }

        /* renamed from: -$$Nest$misDefaultMyMessages, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5833$$Nest$misDefaultMyMessages(ThemeInfo r0) {
            return r0.isDefaultMyMessages();
        }

        /* renamed from: -$$Nest$misDefaultMyMessagesBubbles, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5834$$Nest$misDefaultMyMessagesBubbles(ThemeInfo r0) {
            return r0.isDefaultMyMessagesBubbles();
        }

        /* renamed from: -$$Nest$mloadThemeDocument, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5835$$Nest$mloadThemeDocument(ThemeInfo r0) {
            r0.loadThemeDocument();
        }

        /* renamed from: -$$Nest$mloadWallpapers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5836$$Nest$mloadWallpapers(ThemeInfo r0, SharedPreferences r1) {
            r0.loadWallpapers(r1);
        }

        /* renamed from: -$$Nest$mremoveObservers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5837$$Nest$mremoveObservers(ThemeInfo r0) {
            r0.removeObservers();
        }

        /* renamed from: -$$Nest$msetAccentColorOptions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5838$$Nest$msetAccentColorOptions(ThemeInfo r0, int[] r1, int[] r2, int[] r3, int[] r4, int[] r5, int[] r6, int[] r7, int[] r8, String[] r9, int[] r10, int[] r11) {
            r0.setAccentColorOptions(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        public ThemeInfo() {
            this.patternBgGradientRotation = 45;
            this.loaded = true;
            this.themeLoaded = true;
            this.prevAccentId = -1;
            this.chatAccentsByThemeId = new LongSparseArray();
            this.lastChatThemeId = 0;
            this.lastAccentId = 100;
            this.isDark = -1;
        }

        public ThemeInfo(ThemeInfo r3) {
            this.patternBgGradientRotation = 45;
            this.loaded = true;
            this.themeLoaded = true;
            this.prevAccentId = -1;
            this.chatAccentsByThemeId = new LongSparseArray();
            this.lastChatThemeId = 0;
            this.lastAccentId = 100;
            this.isDark = -1;
            this.name = r3.name;
            this.pathToFile = r3.pathToFile;
            this.pathToWallpaper = r3.pathToWallpaper;
            this.assetName = r3.assetName;
            this.slug = r3.slug;
            this.badWallpaper = r3.badWallpaper;
            this.isBlured = r3.isBlured;
            this.isMotion = r3.isMotion;
            this.patternBgColor = r3.patternBgColor;
            this.patternBgGradientColor1 = r3.patternBgGradientColor1;
            this.patternBgGradientColor2 = r3.patternBgGradientColor2;
            this.patternBgGradientColor3 = r3.patternBgGradientColor3;
            this.patternBgGradientRotation = r3.patternBgGradientRotation;
            this.patternIntensity = r3.patternIntensity;
            this.account = r3.account;
            this.info = r3.info;
            this.loaded = r3.loaded;
            this.uploadingThumb = r3.uploadingThumb;
            this.uploadingFile = r3.uploadingFile;
            this.uploadedThumb = r3.uploadedThumb;
            this.uploadedFile = r3.uploadedFile;
            this.previewBackgroundColor = r3.previewBackgroundColor;
            this.previewBackgroundGradientColor1 = r3.previewBackgroundGradientColor1;
            this.previewBackgroundGradientColor2 = r3.previewBackgroundGradientColor2;
            this.previewBackgroundGradientColor3 = r3.previewBackgroundGradientColor3;
            this.previewWallpaperOffset = r3.previewWallpaperOffset;
            this.previewInColor = r3.previewInColor;
            this.previewOutColor = r3.previewOutColor;
            this.firstAccentIsDefault = r3.firstAccentIsDefault;
            this.previewParsed = r3.previewParsed;
            this.themeLoaded = r3.themeLoaded;
            this.sortIndex = r3.sortIndex;
            this.defaultAccentCount = r3.defaultAccentCount;
            this.accentBaseColor = r3.accentBaseColor;
            this.currentAccentId = r3.currentAccentId;
            this.prevAccentId = r3.prevAccentId;
            this.themeAccentsMap = r3.themeAccentsMap;
            this.themeAccents = r3.themeAccents;
            this.accentsByThemeId = r3.accentsByThemeId;
            this.lastAccentId = r3.lastAccentId;
            this.loadingThemeWallpaperName = r3.loadingThemeWallpaperName;
            this.newPathToWallpaper = r3.newPathToWallpaper;
            this.overrideWallpaper = r3.overrideWallpaper;
        }

        public static boolean accentEquals(ThemeAccent r22, TLRPC.ThemeSettings r23) {
            if (r23.message_colors.size() <= 0) goto L5;
            int r2 = r23.message_colors.get(0).intValue() | (-16777216);
        L7:
            if (r23.message_colors.size() <= 1) goto L9;
            int r5 = r23.message_colors.get(1).intValue() | (-16777216);
        L10:
            if (r2 != r5) goto L13;
            r5 = 0;
        L13:
            if (r23.message_colors.size() <= 2) goto L15;
            int r7 = r23.message_colors.get(2).intValue() | (-16777216);
        L17:
            if (r23.message_colors.size() <= 3) goto L19;
            int r3 = (-16777216) | r23.message_colors.get(3).intValue();
        L20:
            TLRPC.WallPaper r8 = r23.wallpaper;
            if (r8 == null) goto L42;
            TLRPC.WallPaperSettings r82 = r8.settings;
            if (r82 == null) goto L42;
            int r83 = Theme.getWallpaperColor(r82.background_color);
            int r11 = r23.wallpaper.settings.second_background_color;
            long r12 = 4294967296L;
            if (r11 != 0) goto L27;
            long r14 = 4294967296L;
        L28:
            int r112 = r23.wallpaper.settings.third_background_color;
            if (r112 != 0) goto L31;
            long r9 = 4294967296L;
        L32:
            int r113 = r23.wallpaper.settings.fourth_background_color;
            if (r113 == 0) goto L36;
            r12 = Theme.getWallpaperColor(r113);
        L36:
            int r114 = AndroidUtilities.getWallpaperRotation(r23.wallpaper.settings.rotation, false);
            boolean r18 = false;
            TLRPC.WallPaper r4 = r23.wallpaper;
            boolean r19 = true;
            if ((r4 instanceof TLRPC.TL_wallPaperNoFile) == false) goto L39;
        L41:
            String r6 = null;
            float r17 = 0.0f;
        L43:
            long r20 = r9;
            if (r23.accent_color == r22.accentColor) goto L46;
        L72:
            return r18;
        L46:
            if (r23.outbox_accent_color != r22.accentColor2) goto L72;
            if (r2 != r22.myMessagesAccentColor) goto L72;
            if (r5 != r22.myMessagesGradientAccentColor1) goto L72;
            if (r7 != r22.myMessagesGradientAccentColor2) goto L72;
            if (r3 != r22.myMessagesGradientAccentColor3) goto L72;
            if (r23.message_colors_animated != r22.myMessagesAnimated) goto L72;
            if (r83 != r22.backgroundOverrideColor) goto L72;
            if (r14 != r22.backgroundGradientOverrideColor1) goto L72;
            if (r20 != r22.backgroundGradientOverrideColor2) goto L72;
            if (r12 != r22.backgroundGradientOverrideColor3) goto L72;
            if (r114 != r22.backgroundRotation) goto L72;
            if (TextUtils.equals(r6, r22.patternSlug) == false) goto L72;
            if (Math.abs(r17 - r22.patternIntensity) >= 0.001d) goto L72;
            return r19;
        L39:
            if (r4.pattern == false) goto L41;
            r6 = r4.slug;
            r17 = r4.settings.intensity / 100.0f;
            goto L43
        L31:
            r9 = Theme.getWallpaperColor(r112);
            goto L32
        L27:
            r14 = Theme.getWallpaperColor(r11);
        L42:
            r18 = false;
            r19 = true;
            r14 = 0;
            r9 = 0;
            r12 = 0;
            r83 = 0;
            r114 = 0;
            goto L41
        L19:
            r3 = 0;
            goto L20
        L15:
            r7 = 0;
            goto L17
        L9:
            r5 = 0;
            goto L10
        L5:
            r2 = 0;
            goto L7
        }

        private void addObservers() {
            NotificationCenter.getInstance(this.account).addObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(this.account).addObserver(this, NotificationCenter.fileLoadFailed);
        }

        public static ThemeInfo createWithJson(JSONObject r6) {
            if (r6 != null) goto L22;
            return null;
        L22:
            ThemeInfo r4 = new ThemeInfo();     // Catch: Exception -> L8
            r4.name = r6.getString("name");     // Catch: Exception -> L8
            r4.pathToFile = r6.getString("path");     // Catch: Exception -> L8
            if (r6.has("account") == false) goto L11;
            r4.account = r6.getInt("account");     // Catch: Exception -> L8
        L11:
            if (r6.has("info") == false) goto L17;
            SerializedData r2 = new SerializedData(Utilities.hexToBytes(r6.getString("info")));     // Catch: Throwable -> L14
            r4.info = TLRPC.Theme.TLdeserialize(r2, r2.readInt32(true), true);     // Catch: Throwable -> L14
        L14:
            th = move-exception;
            FileLog.e(th);     // Catch: Exception -> L8
        L17:
            if (r6.has("loaded") == false) goto L19;
            r4.loaded = r6.getBoolean("loaded");     // Catch: Exception -> L8
        L19:
            return r4;
        L8:
            e = move-exception;
            FileLog.e(e);
            return null;
        }

        public static ThemeInfo createWithString(String r3) {
            if (TextUtils.isEmpty(r3) == false) goto L5;
            return null;
        L5:
            String[] r32 = r3.split("\\|");
            if (r32.length == 2) goto L8;
            return null;
        L8:
            ThemeInfo r0 = new ThemeInfo();
            r0.name = r32[0];
            r0.pathToFile = r32[1];
            return r0;
        }

        public static void fillAccentValues(ThemeAccent r5, TLRPC.ThemeSettings r6) {
            r5.accentColor = r6.accent_color;
            r5.accentColor2 = r6.outbox_accent_color;
            if (r6.message_colors.size() <= 0) goto L5;
            int r0 = r6.message_colors.get(0).intValue() | (-16777216);
        L6:
            r5.myMessagesAccentColor = r0;
            if (r6.message_colors.size() <= 1) goto L9;
            int r02 = r6.message_colors.get(1).intValue() | (-16777216);
        L10:
            r5.myMessagesGradientAccentColor1 = r02;
            if (r5.myMessagesAccentColor != r02) goto L14;
            r5.myMessagesGradientAccentColor1 = 0;
        L14:
            if (r6.message_colors.size() <= 2) goto L16;
            int r03 = r6.message_colors.get(2).intValue() | (-16777216);
        L17:
            r5.myMessagesGradientAccentColor2 = r03;
            if (r6.message_colors.size() <= 3) goto L20;
            int r04 = r6.message_colors.get(3).intValue() | (-16777216);
        L21:
            r5.myMessagesGradientAccentColor3 = r04;
            r5.myMessagesAnimated = r6.message_colors_animated;
            TLRPC.WallPaper r05 = r6.wallpaper;
            if (r05 == null) goto L53;
            TLRPC.WallPaperSettings r06 = r05.settings;
            if (r06 != null) goto L26;
            return;
        L26:
            if (r06.background_color != 0) goto L28;
            r5.backgroundOverrideColor = 4294967296L;
        L29:
            TLRPC.WallPaperSettings r07 = r6.wallpaper.settings;
            if ((r07.flags & 16) != 0) goto L32;
        L34:
            r5.backgroundGradientOverrideColor1 = Theme.getWallpaperColor(r07.second_background_color);
        L35:
            TLRPC.WallPaperSettings r08 = r6.wallpaper.settings;
            if ((r08.flags & 32) != 0) goto L38;
        L40:
            r5.backgroundGradientOverrideColor2 = Theme.getWallpaperColor(r08.third_background_color);
        L41:
            TLRPC.WallPaperSettings r09 = r6.wallpaper.settings;
            if ((r09.flags & 64) != 0) goto L44;
        L46:
            r5.backgroundGradientOverrideColor3 = Theme.getWallpaperColor(r09.fourth_background_color);
        L47:
            r5.backgroundRotation = AndroidUtilities.getWallpaperRotation(r6.wallpaper.settings.rotation, false);
            TLRPC.WallPaper r62 = r6.wallpaper;
            if ((r62 instanceof TLRPC.TL_wallPaperNoFile) == false) goto L50;
            return;
        L50:
            if (r62.pattern == false) goto L56;
            r5.patternSlug = r62.slug;
            TLRPC.WallPaperSettings r63 = r62.settings;
            r5.patternIntensity = r63.intensity / 100.0f;
            r5.patternMotion = r63.motion;
            return;
        L56:
            return;
        L44:
            if (r09.fourth_background_color != 0) goto L46;
            r5.backgroundGradientOverrideColor3 = 4294967296L;
            goto L47
        L38:
            if (r08.third_background_color != 0) goto L40;
            r5.backgroundGradientOverrideColor2 = 4294967296L;
            goto L41
        L32:
            if (r07.second_background_color != 0) goto L34;
            r5.backgroundGradientOverrideColor1 = 4294967296L;
            goto L35
        L28:
            r5.backgroundOverrideColor = Theme.getWallpaperColor(r0);
            goto L29
        L53:
            return;
        L20:
            r04 = 0;
            goto L21
        L16:
            r03 = 0;
            goto L17
        L9:
            r02 = 0;
            goto L10
        L5:
            r0 = 0;
            goto L6
        }

        private boolean isDefaultMainAccent() {
            if (this.firstAccentIsDefault == true) goto L5;
            return false;
        L5:
            int r0 = this.currentAccentId;
            int r2 = Theme.DEFALT_THEME_ACCENT_ID;
            if (r0 != r2) goto L8;
            return true;
        L8:
            ThemeAccent r02 = this.themeAccentsMap.get(r2);
            ThemeAccent r4 = this.themeAccentsMap.get(this.currentAccentId);
            if (r4 == null) goto L14;
            if (r02 == null) goto L14;
            if (r02.accentColor != r4.accentColor) goto L14;
            return true;
        L14:
            return false;
        }

        private boolean isDefaultMyMessages() {
            if (this.firstAccentIsDefault == true) goto L5;
            return false;
        L5:
            int r0 = this.currentAccentId;
            int r2 = Theme.DEFALT_THEME_ACCENT_ID;
            if (r0 != r2) goto L8;
            return true;
        L8:
            ThemeAccent r02 = this.themeAccentsMap.get(r2);
            ThemeAccent r5 = this.themeAccentsMap.get(this.currentAccentId);
            if (r02 == null) goto L25;
            if (r5 == null) goto L25;
            if (r02.accentColor2 != r5.accentColor2) goto L25;
            if (r02.myMessagesAccentColor != r5.myMessagesAccentColor) goto L25;
            if (r02.myMessagesGradientAccentColor1 != r5.myMessagesGradientAccentColor1) goto L25;
            if (r02.myMessagesGradientAccentColor2 != r5.myMessagesGradientAccentColor2) goto L25;
            if (r02.myMessagesGradientAccentColor3 != r5.myMessagesGradientAccentColor3) goto L25;
            if (r02.myMessagesAnimated != r5.myMessagesAnimated) goto L25;
            return true;
        L25:
            return false;
        }

        private boolean isDefaultMyMessagesBubbles() {
            if (this.firstAccentIsDefault == true) goto L5;
            return false;
        L5:
            int r0 = this.currentAccentId;
            int r2 = Theme.DEFALT_THEME_ACCENT_ID;
            if (r0 != r2) goto L8;
            return true;
        L8:
            ThemeAccent r02 = this.themeAccentsMap.get(r2);
            ThemeAccent r5 = this.themeAccentsMap.get(this.currentAccentId);
            if (r02 == null) goto L23;
            if (r5 == null) goto L23;
            if (r02.myMessagesAccentColor != r5.myMessagesAccentColor) goto L23;
            if (r02.myMessagesGradientAccentColor1 != r5.myMessagesGradientAccentColor1) goto L23;
            if (r02.myMessagesGradientAccentColor2 != r5.myMessagesGradientAccentColor2) goto L23;
            if (r02.myMessagesGradientAccentColor3 != r5.myMessagesGradientAccentColor3) goto L23;
            if (r02.myMessagesAnimated != r5.myMessagesAnimated) goto L23;
            return true;
        L23:
            return false;
        }

        private /* synthetic */ void lambda$didReceivedNotification$0(File r2) {
            createBackground(r2, this.newPathToWallpaper);
            AndroidUtilities.runOnUIThread(new Theme$ThemeInfo$$ExternalSyntheticLambda2(this));
        }

        private /* synthetic */ void lambda$didReceivedNotification$1(TLObject r2, ThemeInfo r3) {
            if ((r2 instanceof TLRPC.TL_wallPaper) == false) goto L6;
            TLRPC.TL_wallPaper r22 = (TLRPC.TL_wallPaper) r2;
            this.loadingThemeWallpaperName = FileLoader.getAttachFileName(r22.document);
            addObservers();
            FileLoader.getInstance(r3.account).loadFile(r22.document, r22, 1, 1);
            return;
        L6:
            onFinishLoadingRemoteTheme();
        }

        private /* synthetic */ void lambda$didReceivedNotification$2(ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new Theme$ThemeInfo$$ExternalSyntheticLambda3(this, r2, r1));
        }

        private void loadOverrideWallpaper(SharedPreferences r4, ThemeAccent r5, String r6) {
            String r42 = r4.getString(r6, null);     // Catch: Throwable -> L16
            if (TextUtils.isEmpty(r42) == true) goto L21;
            JSONObject r62 = new JSONObject(r42);     // Catch: Throwable -> L16
            OverrideWallpaperInfo r43 = new OverrideWallpaperInfo();     // Catch: Throwable -> L16
            r43.fileName = r62.getString("wall");     // Catch: Throwable -> L16
            r43.originalFileName = r62.getString("owall");     // Catch: Throwable -> L16
            r43.color = r62.getInt("pColor");     // Catch: Throwable -> L16
            r43.gradientColor1 = r62.getInt("pGrColor");     // Catch: Throwable -> L16
            r43.gradientColor2 = r62.optInt("pGrColor2");     // Catch: Throwable -> L16
            r43.gradientColor3 = r62.optInt("pGrColor3");     // Catch: Throwable -> L16
            r43.rotation = r62.getInt("pGrAngle");     // Catch: Throwable -> L16
            r43.slug = r62.getString("wallSlug");     // Catch: Throwable -> L16
            r43.isBlurred = r62.getBoolean("wBlur");     // Catch: Throwable -> L16
            r43.isMotion = r62.getBoolean("wMotion");     // Catch: Throwable -> L16
            r43.intensity = (float) r62.getDouble("pIntensity");     // Catch: Throwable -> L16
            r43.parentTheme = this;     // Catch: Throwable -> L16
            r43.parentAccent = r5;     // Catch: Throwable -> L16
            if (r5 == null) goto L9;
            r5.overrideWallpaper = r43;     // Catch: Throwable -> L16
        L11:
            if (r62.has("wallId") == true) goto L13;
            return;
        L13:
            if (r62.getLong("wallId") != 1000001) goto L23;
            r43.slug = "d";     // Catch: Throwable -> L16
            return;
        L23:
            return;
        L9:
            this.overrideWallpaper = r43;     // Catch: Throwable -> L16
            goto L11
        L21:
            return;
        L16:
            th = move-exception;
            FileLog.e(th);
        }

        private void loadThemeDocument() {
            this.loaded = false;
            this.loadingThemeWallpaperName = null;
            this.newPathToWallpaper = null;
            addObservers();
            FileLoader r0 = FileLoader.getInstance(this.account);
            TLRPC.TL_theme r3 = this.info;
            r0.loadFile(r3.document, r3, 1, 1);
        }

        private void loadWallpapers(SharedPreferences r7) {
            ArrayList<ThemeAccent> r0 = this.themeAccents;
            if (r0 != null) goto L5;
        L10:
            loadOverrideWallpaper(r7, null, this.name + "_owp");
            return;
        L5:
            if (r0.isEmpty() == true) goto L10;
            int r02 = this.themeAccents.size();
            int r2 = 0;
        L7:
            if (r2 >= r02) goto L9;
            ThemeAccent r3 = this.themeAccents.get(r2);
            loadOverrideWallpaper(r7, r3, this.name + "_" + r3.id + "_owp");
            r2 = r2 + 1;
            goto L7
        }

        private void onFinishLoadingRemoteTheme() {
            boolean r0 = true;
            this.loaded = true;
            this.previewParsed = false;
            Theme.m5820$$Nest$smsaveOtherThemes(true);
            if (this == Theme.m5798$$Nest$sfgetcurrentTheme()) goto L5;
            return;
        L5:
            if (Theme.m5804$$Nest$sfgetpreviousTheme() != null) goto L13;
            NotificationCenter r2 = NotificationCenter.getGlobalInstance();
            int r3 = NotificationCenter.needSetDayNightTheme;
            if (this == Theme.m5797$$Nest$sfgetcurrentNightTheme()) goto L10;
            r0 = false;
        L10:
            r2.postNotificationName(r3, new Object[]{this, Boolean.valueOf(r0), null, -1, Theme.m5800$$Nest$sfgetfallbackKeys()});
            return;
        }

        private void removeObservers() {
            NotificationCenter.getInstance(this.account).removeObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(this.account).removeObserver(this, NotificationCenter.fileLoadFailed);
        }

        private void setAccentColorOptions(int[] r8, int[] r9, int[] r10, int[] r11, int[] r12, int[] r13, int[] r14, int[] r15, String[] r16, int[] r17, int[] r18) {
            this.defaultAccentCount = r8.length;
            this.themeAccents = new ArrayList();
            this.themeAccentsMap = new SparseArray();
            this.accentsByThemeId = new LongSparseArray();
            int r1 = 0;
        L4:
            if (r1 >= r8.length) goto L58;
            ThemeAccent r2 = new ThemeAccent();
            if (r15 == null) goto L8;
            int r3 = r15[r1];
        L9:
            r2.id = r3;
            if (Theme.isHome(r2) == false) goto L12;
            r2.isDefault = true;
        L12:
            r2.accentColor = r8[r1];
            r2.parentTheme = this;
            if (r9 == null) goto L15;
            r2.myMessagesAccentColor = r9[r1];
        L15:
            if (r10 == null) goto L18;
            r2.myMessagesGradientAccentColor1 = r10[r1];
        L18:
            if (r11 != null) goto L20;
        L25:
            if (r12 != null) goto L27;
        L32:
            if (r13 != null) goto L34;
        L39:
            if (r14 != null) goto L41;
        L46:
            if (r16 == null) goto L49;
            r2.patternIntensity = r18[r1] / 100.0f;
            r2.backgroundRotation = r17[r1];
            r2.patternSlug = r16[r1];
        L49:
            if (Theme.isHome(r2) == false) goto L53;
            if (this.name.equals("Dark Blue") == false) goto L53;
        L54:
            r2.myMessagesAccentColor = -14316059;
            r2.myMessagesGradientAccentColor1 = -12422433;
            r2.myMessagesGradientAccentColor2 = -8304937;
            r2.myMessagesGradientAccentColor3 = -6340950;
            if (this.name.equals("Night") == false) goto L57;
            r2.patternIntensity = -0.57f;
            r2.backgroundOverrideColor = -9666650;
            r2.backgroundGradientOverrideColor1 = -13749173;
            r2.backgroundGradientOverrideColor2 = -8883033;
            r2.backgroundGradientOverrideColor3 = -13421992;
        L57:
            this.themeAccentsMap.put(r2.id, r2);
            this.themeAccents.add(r2);
            r1 = r1 + 1;
        L53:
            if (this.name.equals("Night") == false) goto L57;
        L41:
            if (this.firstAccentIsDefault == true) goto L43;
        L45:
            r2.backgroundGradientOverrideColor3 = r14[r1];
            goto L46
        L43:
            if (r2.id != Theme.DEFALT_THEME_ACCENT_ID) goto L45;
            r2.backgroundGradientOverrideColor3 = 4294967296L;
            goto L46
        L34:
            if (this.firstAccentIsDefault == true) goto L36;
        L38:
            r2.backgroundGradientOverrideColor2 = r13[r1];
            goto L39
        L36:
            if (r2.id != Theme.DEFALT_THEME_ACCENT_ID) goto L38;
            r2.backgroundGradientOverrideColor2 = 4294967296L;
            goto L39
        L27:
            if (this.firstAccentIsDefault == true) goto L29;
        L31:
            r2.backgroundGradientOverrideColor1 = r12[r1];
            goto L32
        L29:
            if (r2.id != Theme.DEFALT_THEME_ACCENT_ID) goto L31;
            r2.backgroundGradientOverrideColor1 = 4294967296L;
            goto L32
        L20:
            if (this.firstAccentIsDefault == true) goto L22;
        L24:
            r2.backgroundOverrideColor = r11[r1];
            goto L25
        L22:
            if (r2.id != Theme.DEFALT_THEME_ACCENT_ID) goto L24;
            r2.backgroundOverrideColor = 4294967296L;
            goto L25
        L8:
            r3 = r1;
            goto L9
        L58:
            this.accentBaseColor = this.themeAccentsMap.get(0).accentColor;
            MonetAccentHelper.appendAccentOptions(this);
        }

        public boolean createBackground(File r10, String r11) {
            Bitmap r102 = AndroidUtilities.getScaledBitmap(AndroidUtilities.dp(640.0f), AndroidUtilities.dp(360.0f), r10.getAbsolutePath(), null, 0);     // Catch: Throwable -> L10
            if (r102 == null) goto L19;
            if (this.patternBgColor == 0) goto L19;
            Bitmap r0 = Bitmap.createBitmap(r102.getWidth(), r102.getHeight(), r102.getConfig());     // Catch: Throwable -> L10
            Canvas r2 = new Canvas(r0);     // Catch: Throwable -> L10
            int r4 = this.patternBgGradientColor2;     // Catch: Throwable -> L10
            if (r4 == 0) goto L12;
            int r42 = MotionBackgroundDrawable.getPatternColor(this.patternBgColor, this.patternBgGradientColor1, r4, this.patternBgGradientColor3);     // Catch: Throwable -> L10
        L17:
            Paint r5 = new Paint(2);     // Catch: Throwable -> L10
            r5.setColorFilter(new PorterDuffColorFilter(r42, PorterDuff.Mode.SRC_IN));     // Catch: Throwable -> L10
            r5.setAlpha((int) ((this.patternIntensity / 100.0f) * 255.0f));     // Catch: Throwable -> L10
            r2.drawBitmap(r102, 0.0f, 0.0f, r5);     // Catch: Throwable -> L10
            r2.setBitmap(null);     // Catch: Throwable -> L10
            r102 = r0;
            goto L19
        L12:
            int r43 = this.patternBgGradientColor1;     // Catch: Throwable -> L10
            int r52 = this.patternBgColor;
            if (r43 == 0) goto L16;
            r42 = AndroidUtilities.getAverageColor(r52, r43);     // Catch: Throwable -> L10
            GradientDrawable r53 = new GradientDrawable(BackgroundGradientDrawable.getGradientOrientation(this.patternBgGradientRotation), new int[]{this.patternBgColor, this.patternBgGradientColor1});     // Catch: Throwable -> L10
            r53.setBounds(0, 0, r0.getWidth(), r0.getHeight());     // Catch: Throwable -> L10
            r53.draw(r2);     // Catch: Throwable -> L10
            goto L17
        L16:
            r42 = AndroidUtilities.getPatternColor(r52);     // Catch: Throwable -> L10
            r2.drawColor(this.patternBgColor);     // Catch: Throwable -> L10
        L19:
            if (this.isBlured == false) goto L21;
            r102 = Utilities.blurWallpaper(r102);     // Catch: Throwable -> L10
        L21:
            FileOutputStream r02 = new FileOutputStream(r11);     // Catch: Throwable -> L10
            if (this.patternBgGradientColor2 == 0) goto L24;
            Bitmap.CompressFormat r9 = Bitmap.CompressFormat.PNG;     // Catch: Throwable -> L10
        L25:
            r102.compress(r9, 87, r02);     // Catch: Throwable -> L10
            r02.close();     // Catch: Throwable -> L10
            return true;
        L24:
            r9 = Bitmap.CompressFormat.JPEG;     // Catch: Throwable -> L10
        L10:
            th = move-exception;
            FileLog.e(th);
            return false;
        }

        public ThemeAccent createNewAccent(long r1, TLRPC.ThemeSettings r3, TLRPC.TL_theme r4, int r5, boolean r6) {
            if (r6 == false) goto L8;
            ThemeAccent r12 = this.chatAccentsByThemeId.get(r1);
            if (r12 == null) goto L6;
            return r12;
        L6:
            int r13 = this.lastChatThemeId + 1;
            this.lastChatThemeId = r13;
            ThemeAccent r2 = createNewAccent(r3);
            r2.id = r13;
            r2.info = r4;
            r2.account = r5;
            this.chatAccentsByThemeId.put(r13, r2);
            return r2;
        L8:
            ThemeAccent r62 = this.accentsByThemeId.get(r1);
            if (r62 == null) goto L11;
            return r62;
        L11:
            int r63 = this.lastAccentId + 1;
            this.lastAccentId = r63;
            ThemeAccent r32 = createNewAccent(r3);
            r32.id = r63;
            r32.info = r4;
            r32.account = r5;
            this.themeAccentsMap.put(r63, r32);
            this.themeAccents.add(0, r32);
            Theme.m5821$$Nest$smsortAccents(this);
            this.accentsByThemeId.put(r1, r32);
            return r32;
        }

        public ThemeAccent createNewAccent(TLRPC.TL_theme r2, int r3) {
            return createNewAccent(r2, r3, false, 0);
        }

        public ThemeAccent createNewAccent(TLRPC.TL_theme r9, int r10, boolean r11, int r12) {
            TLRPC.ThemeSettings r0 = null;
            if (r9 != null) goto L6;
            return null;
        L6:
            if (r12 >= r9.settings.size()) goto L8;
            r0 = r9.settings.get(r12);
        L8:
            long r2 = r9.id;
            return createNewAccent(r2, r0, r9, r10, r11);
        }

        public ThemeAccent createNewAccent(TLRPC.ThemeSettings r2) {
            ThemeAccent r0 = new ThemeAccent();
            fillAccentValues(r0, r2);
            r0.parentTheme = this;
            return r0;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            int r42 = NotificationCenter.fileLoaded;
            if (r3 != r42) goto L5;
        L6:
            String r0 = (String) r5[0];
            TLRPC.TL_theme r1 = this.info;
            if (r1 != null) goto L9;
            return;
        L9:
            if (r1.document != null) goto L11;
            return;
        L11:
            if (r0.equals(this.loadingThemeWallpaperName) == false) goto L15;
            this.loadingThemeWallpaperName = null;
            File r32 = (File) r5[1];
            Utilities.globalQueue.postRunnable(new Theme$ThemeInfo$$ExternalSyntheticLambda0(this, r32));
            return;
        L15:
            if (r0.equals(FileLoader.getAttachFileName(this.info.document)) == false) goto L31;
            removeObservers();
            if (r3 != r42) goto L32;
            File r33 = new File(this.pathToFile);
            TLRPC.TL_theme r43 = this.info;
            ThemeInfo r34 = Theme.fillThemeValues(r33, r43.title, r43);
            if (r34 != null) goto L21;
        L26:
            onFinishLoadingRemoteTheme();
            return;
        L21:
            if (r34.pathToWallpaper == null) goto L26;
            if (new File(r34.pathToWallpaper).exists() == true) goto L26;
            this.patternBgColor = r34.patternBgColor;
            this.patternBgGradientColor1 = r34.patternBgGradientColor1;
            this.patternBgGradientColor2 = r34.patternBgGradientColor2;
            this.patternBgGradientColor3 = r34.patternBgGradientColor3;
            this.patternBgGradientRotation = r34.patternBgGradientRotation;
            this.isBlured = r34.isBlured;
            this.patternIntensity = r34.patternIntensity;
            this.newPathToWallpaper = r34.pathToWallpaper;
            TL_account.getWallPaper r44 = new TL_account.getWallPaper();
            TLRPC.TL_inputWallPaperSlug r52 = new TLRPC.TL_inputWallPaperSlug();
            r52.slug = r34.slug;
            r44.wallpaper = r52;
            ConnectionsManager.getInstance(r34.account).sendRequest(r44, new Theme$ThemeInfo$$ExternalSyntheticLambda1(this, r34));
            return;
        L32:
            return;
        L31:
            return;
        L5:
            if (r3 == NotificationCenter.fileLoadFailed) goto L6;
        }

        public String generateWallpaperName(ThemeAccent r6, boolean r7) {
            if (r6 != null) goto L5;
            r6 = getAccent(false);
        L5:
            if (r6 == null) goto L13;
            StringBuilder r3 = new StringBuilder();
            String r5 = this.name;
            if (r7 == false) goto L10;
            StringBuilder r72 = new StringBuilder();
            r72.append(r5);
            r72.append("_");
            r72.append(r6.id);
            r72.append("_wp_o");
        L11:
            r3.append(r72.toString());
            r3.append(Utilities.random.nextInt());
            r3.append(".jpg");
            return r3.toString();
        L10:
            r72 = new StringBuilder();
            r72.append(r5);
            r72.append("_");
            r72.append(r6.id);
            r72.append("_wp");
            goto L11
        L13:
            StringBuilder r62 = new StringBuilder();
            String r52 = this.name;
            if (r7 == false) goto L17;
            StringBuilder r73 = new StringBuilder();
            r73.append(r52);
            r73.append("_wp_o");
        L18:
            r62.append(r73.toString());
            r62.append(Utilities.random.nextInt());
            r62.append(".jpg");
            return r62.toString();
        L17:
            r73 = new StringBuilder();
            r73.append(r52);
            r73.append("_wp");
            goto L18
        }

        public ThemeAccent getAccent(boolean r5) {
            if (this.themeAccents != null) goto L5;
            return null;
        L5:
            ThemeAccent r0 = this.themeAccentsMap.get(this.currentAccentId);
            if (r0 != null) goto L8;
            return null;
        L8:
            if (r5 == false) goto L14;
            int r52 = this.lastAccentId + 1;
            this.lastAccentId = r52;
            ThemeAccent r1 = new ThemeAccent();
            r1.accentColor = r0.accentColor;
            r1.accentColor2 = r0.accentColor2;
            r1.myMessagesAccentColor = r0.myMessagesAccentColor;
            r1.myMessagesGradientAccentColor1 = r0.myMessagesGradientAccentColor1;
            r1.myMessagesGradientAccentColor2 = r0.myMessagesGradientAccentColor2;
            r1.myMessagesGradientAccentColor3 = r0.myMessagesGradientAccentColor3;
            r1.myMessagesAnimated = r0.myMessagesAnimated;
            r1.backgroundOverrideColor = r0.backgroundOverrideColor;
            r1.backgroundGradientOverrideColor1 = r0.backgroundGradientOverrideColor1;
            r1.backgroundGradientOverrideColor2 = r0.backgroundGradientOverrideColor2;
            r1.backgroundGradientOverrideColor3 = r0.backgroundGradientOverrideColor3;
            r1.backgroundRotation = r0.backgroundRotation;
            r1.patternSlug = r0.patternSlug;
            r1.patternIntensity = r0.patternIntensity;
            r1.patternMotion = r0.patternMotion;
            r1.parentTheme = this;
            OverrideWallpaperInfo r02 = this.overrideWallpaper;
            if (r02 == null) goto L12;
            r1.overrideWallpaper = new OverrideWallpaperInfo(r02, this, r1);
        L12:
            this.prevAccentId = this.currentAccentId;
            r1.id = r52;
            this.currentAccentId = r52;
            this.overrideWallpaper = r1.overrideWallpaper;
            this.themeAccentsMap.put(r52, r1);
            this.themeAccents.add(0, r1);
            Theme.m5821$$Nest$smsortAccents(this);
            return r1;
        L14:
            return r0;
        }

        public int getAccentColor(int r1) {
            ThemeAccent r0 = this.themeAccentsMap.get(r1);
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.accentColor;
        }

        public String getKey() {
            if (this.info == null) goto L7;
            return "remote" + this.info.id;
        L7:
            return this.name;
        }

        public String getName() {
            if ("Blue".equals(this.name) == false) goto L7;
            return LocaleController.getString(R.string.ThemeClassic);
        L7:
            if ("Dark Blue".equals(this.name) == false) goto L11;
            return LocaleController.getString(R.string.ThemeDark);
        L11:
            if ("Arctic Blue".equals(this.name) == false) goto L15;
            return LocaleController.getString(R.string.ThemeArcticBlue);
        L15:
            if ("Day".equals(this.name) == false) goto L19;
            return LocaleController.getString(R.string.ThemeDay);
        L19:
            if ("Night".equals(this.name) == true) goto L21;
            TLRPC.TL_theme r0 = this.info;
            if (r0 == null) goto L27;
            return r0.title;
        L27:
            return this.name;
        L21:
            return LocaleController.getString(R.string.ThemeNight);
        }

        public int getPreviewBackgroundColor() {
            if (this.firstAccentIsDefault == false) goto L9;
            if (this.currentAccentId != Theme.DEFALT_THEME_ACCENT_ID) goto L9;
            return -3155485;
        L9:
            return this.previewBackgroundColor;
        }

        public int getPreviewInColor() {
            if (this.firstAccentIsDefault == false) goto L9;
            if (this.currentAccentId != Theme.DEFALT_THEME_ACCENT_ID) goto L9;
            return -1;
        L9:
            return this.previewInColor;
        }

        public int getPreviewOutColor() {
            if (this.firstAccentIsDefault == false) goto L9;
            if (this.currentAccentId != Theme.DEFALT_THEME_ACCENT_ID) goto L9;
            return -983328;
        L9:
            return this.previewOutColor;
        }

        public JSONObject getSaveJson() {
            JSONObject r0 = new JSONObject();     // Catch: Exception -> L7
            r0.put("name", this.name);     // Catch: Exception -> L7
            r0.put("path", this.pathToFile);     // Catch: Exception -> L7
            r0.put("account", this.account);     // Catch: Exception -> L7
            TLRPC.TL_theme r1 = this.info;     // Catch: Exception -> L7
            if (r1 == null) goto L5;
            SerializedData r2 = new SerializedData(r1.getObjectSize());     // Catch: Exception -> L7
            this.info.serializeToStream(r2);     // Catch: Exception -> L7
            r0.put("info", Utilities.bytesToHex(r2.toByteArray()));     // Catch: Exception -> L7
        L5:
            r0.put("loaded", this.loaded);     // Catch: Exception -> L7
            return r0;
        L7:
            e = move-exception;
            FileLog.e(e);
            return null;
        }

        public boolean hasAccentColors() {
            if (this.defaultAccentCount == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isDark() {
            int r0 = this.isDark;
            if (r0 == (-1)) goto L8;
            if (r0 != 1) goto L6;
            return true;
        L6:
            return false;
        L8:
            if ("Dark Blue".equals(this.name) == false) goto L10;
        L25:
            this.isDark = 1;
        L27:
            if (this.isDark != (-1)) goto L30;
            File r3 = new File(this.pathToFile);
            Theme.m5816$$Nest$smcheckIsDark(Theme.getThemeFileValues(r3, null, new String[1]), this);
        L30:
            if (this.isDark != 1) goto L32;
            return true;
        L32:
            return false;
        L10:
            if ("Night".equals(this.name) == true) goto L25;
            if ("Monet Dark".equals(this.name) == true) goto L25;
            if ("Monet Black".equals(this.name) == true) goto L25;
            if ("Blue".equals(this.name) == false) goto L19;
        L24:
            this.isDark = 0;
            goto L27
        L19:
            if ("Arctic Blue".equals(this.name) == true) goto L24;
            if ("Day".equals(this.name) == true) goto L24;
            if ("Monet Light".equals(this.name) == false) goto L27;
            goto L24
        }

        public boolean isLight() {
            if (this.pathToFile == null) goto L5;
            return false;
        L5:
            if (isDark() == true) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isMonet() {
            if ("Monet Dark".equals(this.name) == false) goto L5;
            return true;
        L5:
            if ("Monet Light".equals(this.name) == false) goto L7;
            return true;
        L7:
            if ("Monet Black".equals(this.name) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public void setCurrentAccentId(int r1) {
            this.currentAccentId = r1;
            ThemeAccent r12 = getAccent(false);
            if (r12 == null) goto L6;
            this.overrideWallpaper = r12.overrideWallpaper;
            return;
        }

        public void setOverrideWallpaper(OverrideWallpaperInfo r3) {
            if (this.overrideWallpaper == r3) goto L15;
            ThemeAccent r0 = getAccent(false);
            OverrideWallpaperInfo r1 = this.overrideWallpaper;
            if (r1 == null) goto L8;
            OverrideWallpaperInfo.m5823$$Nest$mdelete(r1);
        L8:
            if (r3 == null) goto L10;
            r3.parentAccent = r0;
            r3.parentTheme = this;
            OverrideWallpaperInfo.m5824$$Nest$msave(r3);
        L10:
            this.overrideWallpaper = r3;
            if (r0 == null) goto L14;
            r0.overrideWallpaper = r3;
            return;
        L14:
            return;
        }

        public void setPreviewBackgroundColor(int r1) {
            this.previewBackgroundColor = r1;
        }

        public void setPreviewInColor(int r1) {
            this.previewInColor = r1;
        }

        public void setPreviewOutColor(int r1) {
            this.previewOutColor = r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$2reH0REoIt7jPYeLj9QuBt4F0Fk(BaseFragment r2) {
        r2.presentFragment(new ThemeActivity(1));
    }

    /* renamed from: $r8$lambda$5DwEtG8o-ZHyp3J5BcUaprxPJb8, reason: not valid java name */
    public static /* synthetic */ int m5791$r8$lambda$5DwEtG8oZHyp3J5BcUaprxPJb8(ThemeInfo r1, ThemeInfo r2) {
        if (r1.pathToFile != null) goto L9;
        if (r1.assetName != null) goto L9;
        return -1;
    L9:
        if (r2.pathToFile != null) goto L15;
        if (r2.assetName != null) goto L15;
        return 1;
    L15:
        return r1.name.compareTo(r2.name);
    }

    public static /* synthetic */ void $r8$lambda$Fw3ecpL5lIcIDhHXr1csyUDY9ms(Runnable r0, SparseIntArray r1) {
        currentColorsNoAccent = r1;
        r0.run();
    }

    /* renamed from: $r8$lambda$HzQiQ7SzWbHkRbqdF1shtwb-Xbs, reason: not valid java name */
    public static /* synthetic */ void m5792$r8$lambda$HzQiQ7SzWbHkRbqdF1shtwbXbs(int r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new Theme$$ExternalSyntheticLambda13(r0, r1));
    }

    /* renamed from: $r8$lambda$IfQcXDUUx0gKIvGuTBUT9VHuM-I, reason: not valid java name */
    public static /* synthetic */ void m5793$r8$lambda$IfQcXDUUx0gKIvGuTBUT9VHuMI(MessageObject r1) {
        AudioVisualizerDrawable r12 = animatedOutVisualizerDrawables.remove(r1);
        if (r12 == null) goto L6;
        r12.setParentView(null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$Mu0Qxx9lco6NYPpjphkArSoB7e8(Drawable r2) {
        wallpaperLoadTask = null;
        createCommonChatResources();
        if (disallowChangeServiceMessageColor == true) goto L5;
        applyChatServiceMessageColor(null, null, r2);
        applyChatMessageSelectedBackgroundColor(null, r2);
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$QJ4M_NRBSxhPO8JEoHapVMMxNAI(int r17, TLObject r18) {
        int r4 = 0;
        loadingRemoteThemes[r17] = false;
        if ((r18 instanceof TL_account.TL_themes) == false) goto L151;
        TL_account.TL_themes r1 = (TL_account.TL_themes) r18;
        remoteThemesHash[r17] = r1.hash;
        lastLoadingThemesTime[r17] = (int) (System.currentTimeMillis() / 1000);
        ArrayList<TLRPC.TL_theme> r3 = new ArrayList();
        ArrayList r5 = new ArrayList();
        int r6 = themes.size();
        int r7 = 0;
    L5:
        if (r7 >= r6) goto L23;
        ThemeInfo r8 = themes.get(r7);
        if (r8.info == null) goto L12;
        if (r8.account != r17) goto L12;
        r5.add(r8);
    L22:
        r7 = r7 + 1;
    L12:
        if (r8.themeAccents == null) goto L22;
        int r9 = 0;
    L15:
        if (r9 >= r8.themeAccents.size()) goto L22;
        ThemeAccent r10 = r8.themeAccents.get(r9);
        if (r10.info == null) goto L21;
        if (r10.account != r17) goto L21;
        r5.add(r10);
    L21:
        r9 = r9 + 1;
        goto L15
    L23:
        int r62 = r1.themes.size();
        int r72 = 0;
        boolean r82 = false;
        boolean r92 = false;
    L25:
        if (r72 >= r62) goto L87;
        TLRPC.TL_theme r12 = r1.themes.get(r72);
        if (r12 == null) goto L86;
        if (r12.isDefault == false) goto L32;
        r3.add(r12);
    L32:
        ArrayList<TLRPC.ThemeSettings> r13 = r12.settings;
        if (r13 != null) goto L35;
    L81:
        String r42 = "remote" + r12.id;
        ThemeInfo r102 = themesDict.get(r42);
        if (r102 != null) goto L84;
        r102 = new ThemeInfo();
        r102.account = r17;
        r102.pathToFile = new File(ApplicationLoader.getFilesDirFixed(), r42.concat(".attheme")).getAbsolutePath();
        themes.add(r102);
        otherThemes.add(r102);
        r92 = true;
    L85:
        r102.name = r12.title;
        r102.info = r12;
        themesDict.put(r102.getKey(), r102);
        goto L86
    L84:
        r5.remove(r102);
        goto L85
    L35:
        if (r13.size() <= 0) goto L81;
        int r132 = r4;
    L38:
        if (r132 >= r12.settings.size()) goto L86;
        TLRPC.ThemeSettings r14 = r12.settings.get(r132);
        if (r14 == null) goto L80;
        String r15 = getBaseThemeKey(r14);
        if (r15 == null) goto L80;
        ThemeInfo r11 = themesDict.get(r15);
        if (r11 == null) goto L80;
        if (r11.themeAccents == null) goto L80;
        ArrayList r16 = r5;
        ThemeAccent r43 = r11.accentsByThemeId.get(r12.id);
        if (r43 != null) goto L52;
        r5 = r16;
        ThemeAccent r112 = r11.createNewAccent(r12, r17, false, r132);
        if (TextUtils.isEmpty(r112.patternSlug) == true) goto L78;
        r43 = r112;
        r82 = true;
    L79:
        r43.isDefault = r12.isDefault;
        goto L80
    L78:
        r43 = r112;
        goto L79
    L52:
        if (ThemeInfo.accentEquals(r43, r14) == true) goto L66;
        File r52 = r43.getPathToWallpaper();
        if (r52 == null) goto L56;
        r52.delete();
    L56:
        ThemeInfo.fillAccentValues(r43, r14);
        ThemeInfo r53 = currentTheme;
        if (r53 == r11) goto L59;
    L65:
        r82 = true;
        r92 = true;
        goto L66
    L59:
        if (r53.currentAccentId != r43.id) goto L65;
        refreshThemeColors();
        NotificationCenter r54 = NotificationCenter.getGlobalInstance();
        int r83 = NotificationCenter.needSetDayNightTheme;
        ThemeInfo r93 = currentTheme;
        if (currentNightTheme != r93) goto L63;
        boolean r113 = true;
    L64:
        r54.postNotificationName(r83, new Object[]{r93, Boolean.valueOf(r113), null, -1});
        goto L65
    L63:
        r113 = false;
    L66:
        TLRPC.WallPaper r55 = r14.wallpaper;
        if (r55 == null) goto L73;
        TLRPC.WallPaperSettings r56 = r55.settings;
        if (r56 == null) goto L73;
        if (r56.motion == false) goto L73;
        boolean r57 = true;
    L74:
        r43.patternMotion = r57;
        r5 = r16;
        r5.remove(r43);
    L73:
        r57 = false;
    L80:
        r132 = r132 + 1;
    L86:
        r72 = r72 + 1;
        r4 = 0;
        goto L25
    L87:
        int r19 = r5.size();
        int r44 = 0;
    L88:
        if (r44 >= r19) goto L121;
        Object r63 = r5.get(r44);
        if ((r63 instanceof ThemeInfo) == false) goto L110;
        ThemeInfo r64 = (ThemeInfo) r63;
        ThemeInfo.m5837$$Nest$mremoveObservers(r64);
        otherThemes.remove(r64);
        themesDict.remove(r64.name);
        OverrideWallpaperInfo r73 = r64.overrideWallpaper;
        if (r73 == null) goto L94;
        OverrideWallpaperInfo.m5823$$Nest$mdelete(r73);
    L94:
        themes.remove(r64);
        new File(r64.pathToFile).delete();
        if (currentDayTheme != r64) goto L98;
        currentDayTheme = defaultTheme;
    L100:
        boolean r74 = false;
    L102:
        if (currentTheme != r64) goto L120;
        if (r74 == false) goto L106;
        ThemeInfo r65 = currentNightTheme;
    L107:
        applyTheme(r65, true, false, r74);
        goto L120
    L106:
        r65 = currentDayTheme;
    L120:
        r44 = r44 + 1;
        goto L88
    L98:
        if (currentNightTheme != r64) goto L100;
        currentNightTheme = themesDict.get("Dark Blue");
        r74 = true;
        goto L102
    L110:
        if ((r63 instanceof ThemeAccent) == false) goto L120;
        ThemeAccent r66 = (ThemeAccent) r63;
        if (deleteThemeAccent(r66.parentTheme, r66, false) == false) goto L120;
        if (currentTheme != r66.parentTheme) goto L120;
        refreshThemeColors();
        NotificationCenter r67 = NotificationCenter.getGlobalInstance();
        int r75 = NotificationCenter.needSetDayNightTheme;
        ThemeInfo r114 = currentTheme;
        if (currentNightTheme != r114) goto L118;
        boolean r122 = true;
    L119:
        r67.postNotificationName(r75, new Object[]{r114, Boolean.valueOf(r122), null, -1});
        goto L120
    L118:
        r122 = false;
        goto L119
    L121:
        saveOtherThemes(true);
        sortThemes();
        if (r92 == false) goto L124;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.themeListUpdated, new Object[0]);
    L124:
        if (r82 == false) goto L126;
        PatternsLoader.createLoader(true);
    L126:
        MediaDataController.getInstance(r17).generateEmojiPreviewThemes(r3, r17);
        return;
    }

    /* renamed from: $r8$lambda$Vo20CqMySD-r9zmfRwMdqcn95C4, reason: not valid java name */
    public static /* synthetic */ void m5794$r8$lambda$Vo20CqMySDr9zmfRwMdqcn95C4(boolean r4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewTheme, new Object[]{Boolean.FALSE, Boolean.valueOf(r4), Boolean.TRUE});
    }

    /* renamed from: $r8$lambda$Y-aQiMDK_ThvoQBl6Anz-eXT7s8, reason: not valid java name */
    public static /* synthetic */ void m5795$r8$lambda$YaQiMDK_ThvoQBl6AnzeXT7s8(TLObject r7, ThemeAccent r8, ThemeInfo r9, TLRPC.TL_theme r10) {
        boolean r1 = true;
        loadingCurrentTheme--;
        if ((r7 instanceof TLRPC.TL_theme) == false) goto L42;
        TLRPC.TL_theme r72 = (TLRPC.TL_theme) r7;
        if (r72.settings.size() <= 0) goto L7;
        TLRPC.ThemeSettings r0 = r72.settings.get(0);
    L8:
        if (r8 == null) goto L35;
        if (r0 == null) goto L35;
        if (ThemeInfo.accentEquals(r8, r0) == true) goto L25;
        File r73 = r8.getPathToWallpaper();
        if (r73 == null) goto L15;
        r73.delete();
    L15:
        ThemeInfo.fillAccentValues(r8, r0);
        ThemeInfo r74 = currentTheme;
        if (r74 == r9) goto L18;
    L24:
        PatternsLoader.createLoader(true);
        boolean r75 = true;
    L26:
        TLRPC.WallPaper r92 = r0.wallpaper;
        if (r92 == null) goto L33;
        TLRPC.WallPaperSettings r93 = r92.settings;
        if (r93 == null) goto L33;
        if (r93.motion == false) goto L33;
    L34:
        r8.patternMotion = r1;
        r1 = r75;
    L44:
        if (loadingCurrentTheme != 0) goto L47;
        lastLoadingCurrentThemeTime = (int) (System.currentTimeMillis() / 1000);
        saveOtherThemes(r1);
        return;
    L47:
        return;
    L33:
        r1 = false;
        goto L34
    L18:
        if (r74.currentAccentId != r8.id) goto L24;
        refreshThemeColors();
        createChatResources(ApplicationLoader.applicationContext, false);
        NotificationCenter r76 = NotificationCenter.getGlobalInstance();
        int r94 = NotificationCenter.needSetDayNightTheme;
        ThemeInfo r102 = currentTheme;
        if (currentNightTheme != r102) goto L22;
        boolean r4 = true;
    L23:
        r76.postNotificationName(r94, new Object[]{r102, Boolean.valueOf(r4), null, -1});
        goto L24
    L22:
        r4 = false;
        goto L23
    L25:
        r75 = false;
    L35:
        TLRPC.Document r02 = r72.document;
        if (r02 == null) goto L42;
        if (r02.id == r10.document.id) goto L42;
        if (r8 == null) goto L41;
        r8.info = r72;
        goto L44
    L41:
        r9.info = r72;
        ThemeInfo.m5835$$Nest$mloadThemeDocument(r9);
        goto L44
    L7:
        r0 = null;
    L42:
        r1 = false;
        goto L44
    }

    public static /* synthetic */ void $r8$lambda$ZxatGSrZQ0QJmceQ1RfAOuhjlDY(Utilities.Callback r0, File r1, String r2, String[] r3) {
        r0.run(getThemeFileValues(r1, r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$av_bcMFY24nVQKe60itCAqBpiU8(ThemeAccent r0, ThemeInfo r1, TLRPC.TL_theme r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new Theme$$ExternalSyntheticLambda6(r3, r0, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$gvgZQHt_aksR0W7l4hMLnrufm7A(OverrideWallpaperInfo r0, File r1, int r2, boolean r3, TLRPC.Document r4, boolean r5) {
        AndroidUtilities.runOnUIThread(new Theme$$ExternalSyntheticLambda8(loadWallpaperInternal(r0, r1, r2, r3, r4, r5)));
    }

    public static /* synthetic */ void $r8$lambda$hXZO8feR59GGnPGakQxZrmdaYUg(String[] r7, ThemeInfo r8, boolean r9, boolean r10, Runnable r11) {
        themedWallpaperFileOffset = currentColorsNoAccent.get(key_wallpaperFileOffset, -1);     // Catch: Exception -> L49
        if (TextUtils.isEmpty(r7[0]) == true) goto L82;
        themedWallpaperLink = r7[0];     // Catch: Exception -> L49
        String r72 = new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(themedWallpaperLink) + ".wp").getAbsolutePath();     // Catch: Exception -> L49
        String r1 = r8.pathToWallpaper;     // Catch: Exception -> L74
        if (r1 == null) goto L11;
        if (r1.equals(r72) == true) goto L11;
        new File(r8.pathToWallpaper).delete();     // Catch: Exception -> L74
    L11:
        r8.pathToWallpaper = r72;     // Catch: Exception -> L49
        Uri r73 = Uri.parse(themedWallpaperLink);     // Catch: Throwable -> L19
        r8.slug = r73.getQueryParameter("slug");     // Catch: Throwable -> L19
        String r12 = r73.getQueryParameter("mode");     // Catch: Throwable -> L19
        if (r12 == null) goto L25;
        String[] r13 = r12.toLowerCase().split(" ");     // Catch: Throwable -> L19
        int r2 = r13.length;     // Catch: Throwable -> L19
        int r3 = 0;
    L15:
        if (r3 >= r2) goto L25;
        String r4 = r13[r3];     // Catch: Throwable -> L19
        if ("blur".equals(r4) == false) goto L22;
        r8.isBlured = true;     // Catch: Throwable -> L19
    L24:
        r3 = r3 + 1;     // Catch: Throwable -> L19
        goto L15
    L22:
        if ("motion".equals(r4) == false) goto L24;
        r8.isMotion = true;     // Catch: Throwable -> L19
    L25:
        r8.patternBgGradientRotation = 45;     // Catch: Throwable -> L19
        String r14 = r73.getQueryParameter("bg_color");     // Catch: Throwable -> L19 Exception -> L75
        if (TextUtils.isEmpty(r14) == true) goto L78;
        r8.patternBgColor = Integer.parseInt(r14.substring(0, 6), 16) | (-16777216);     // Catch: Throwable -> L19 Exception -> L75
        if (r14.length() < 13) goto L34;
        if (AndroidUtilities.isValidWallChar(r14.charAt(6)) == false) goto L34;
        r8.patternBgGradientColor1 = Integer.parseInt(r14.substring(7, 13), 16) | (-16777216);     // Catch: Throwable -> L19 Exception -> L75
    L34:
        if (r14.length() < 20) goto L39;
        if (AndroidUtilities.isValidWallChar(r14.charAt(13)) == false) goto L39;
        r8.patternBgGradientColor2 = Integer.parseInt(r14.substring(14, 20), 16) | (-16777216);     // Catch: Throwable -> L19 Exception -> L75
    L39:
        if (r14.length() != 27) goto L78;
        if (AndroidUtilities.isValidWallChar(r14.charAt(20)) == false) goto L78;
        r8.patternBgGradientColor3 = Integer.parseInt(r14.substring(21), 16) | (-16777216);     // Catch: Throwable -> L19 Exception -> L75
    L78:
        String r74 = r73.getQueryParameter("rotation");     // Catch: Throwable -> L19 Exception -> L76
        if (TextUtils.isEmpty(r74) == true) goto L56;
        r8.patternBgGradientRotation = Utilities.parseInt(r74);     // Catch: Throwable -> L19 Exception -> L76
    L56:
        if (r9 == false) goto L58;
    L62:
        currentTheme = r8;     // Catch: Exception -> L49
        refreshThemeColors();     // Catch: Exception -> L49
    L66:
        if (previousTheme != null) goto L71;
        if (r10 == false) goto L71;
        if (switchingNightTheme == true) goto L71;
        MessagesController.getInstance(r8.account).saveTheme(r8, r8.getAccent(false), r9, false);
    L71:
        if (r11 == null) goto L93;
        r11.run();
        return;
    L93:
        return;
    L58:
        if (previousTheme != null) goto L62;
        currentDayTheme = r8;     // Catch: Exception -> L49
        if (isCurrentThemeNight() == false) goto L62;
        switchNightThemeDelay = CastStatusCodes.AUTHENTICATION_FAILED;     // Catch: Exception -> L49
        lastDelayUpdateTime = SystemClock.elapsedRealtime();     // Catch: Exception -> L49
        AndroidUtilities.runOnUIThread(new MessagesController$$ExternalSyntheticLambda83(), 2100);     // Catch: Exception -> L49
    L19:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L49
        goto L56
    L82:
        if (r8.pathToWallpaper == null) goto L55;
        new File(r8.pathToWallpaper).delete();     // Catch: Exception -> L77
    L55:
        r8.pathToWallpaper = null;     // Catch: Exception -> L49
        themedWallpaperLink = null;     // Catch: Exception -> L49
    L49:
        e = move-exception;
        FileLog.e(e);
        goto L66
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    /* renamed from: $r8$lambda$iIb89iYIwD07Wnq6O3Bw-32oCr8, reason: not valid java name */
    public static /* synthetic */ int m5796$r8$lambda$iIb89iYIwD07Wnq6O3Bw32oCr8(ThemeAccent r2, ThemeAccent r3) {
        boolean r0 = MonetAccentHelper.isMonetAccent(r2);
        if (r0 == MonetAccentHelper.isMonetAccent(r3)) goto L6;
        if (r0 == true) goto L35;
        return 1;
    L35:
        return -1;
    L6:
        if (r0 == false) goto L10;
        return Integer.compare(r3.id, r2.id);
    L10:
        if (isHome(r2) == false) goto L13;
        return -1;
    L13:
        if (isHome(r3) == true) goto L40;
        ?? r02 = r2.isDefault;
        ?? r1 = r3.isDefault;
        if (r02 != r1) goto L29;
        int r22 = r2.id;
        int r32 = r3.id;
        if (r02 == 0) goto L23;
        if (r22 > r32) goto L41;
        if (r22 < r32) goto L37;
        return 0;
    L37:
        return -1;
    L41:
        return 1;
    L23:
        if (r22 > r32) goto L38;
        if (r22 < r32) goto L42;
        return 0;
    L42:
        return 1;
    L38:
        return -1;
    L29:
        if (r02 <= r1) goto L39;
        return -1;
    L39:
        return 1;
    L40:
        return 1;
    }

    public static /* synthetic */ void $r8$lambda$uoADhlIjZ0L8lu1XOfNoRRUgOGw(Runnable r0, SparseIntArray r1) {
        currentColorsNoAccent = r1;
        r0.run();
    }

    /* renamed from: -$$Nest$sfgetcurrentNightTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThemeInfo m5797$$Nest$sfgetcurrentNightTheme() {
        return currentNightTheme;
    }

    /* renamed from: -$$Nest$sfgetcurrentTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThemeInfo m5798$$Nest$sfgetcurrentTheme() {
        return currentTheme;
    }

    /* renamed from: -$$Nest$sfgetdefaultColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m5799$$Nest$sfgetdefaultColors() {
        return defaultColors;
    }

    /* renamed from: -$$Nest$sfgetfallbackKeys, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m5800$$Nest$sfgetfallbackKeys() {
        return fallbackKeys;
    }

    /* renamed from: -$$Nest$sfgetlastBrightnessValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5801$$Nest$sfgetlastBrightnessValue() {
        return lastBrightnessValue;
    }

    /* renamed from: -$$Nest$sfgetmaskPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m5802$$Nest$sfgetmaskPaint() {
        return maskPaint;
    }

    /* renamed from: -$$Nest$sfgetmyMessagesAccentExtraKeys, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m5803$$Nest$sfgetmyMessagesAccentExtraKeys() {
        return myMessagesAccentExtraKeys;
    }

    /* renamed from: -$$Nest$sfgetpreviousTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThemeInfo m5804$$Nest$sfgetpreviousTheme() {
        return previousTheme;
    }

    /* renamed from: -$$Nest$sfgetswitchDayBrightnessRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5805$$Nest$sfgetswitchDayBrightnessRunnable() {
        return switchDayBrightnessRunnable;
    }

    /* renamed from: -$$Nest$sfgetswitchDayRunnableScheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5806$$Nest$sfgetswitchDayRunnableScheduled() {
        return switchDayRunnableScheduled;
    }

    /* renamed from: -$$Nest$sfgetswitchNightBrightnessRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5807$$Nest$sfgetswitchNightBrightnessRunnable() {
        return switchNightBrightnessRunnable;
    }

    /* renamed from: -$$Nest$sfgetswitchNightRunnableScheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5808$$Nest$sfgetswitchNightRunnableScheduled() {
        return switchNightRunnableScheduled;
    }

    /* renamed from: -$$Nest$sfgetthemeAccentExclusionKeys, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m5809$$Nest$sfgetthemeAccentExclusionKeys() {
        return themeAccentExclusionKeys;
    }

    /* renamed from: -$$Nest$sfgetthemesDict, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5810$$Nest$sfgetthemesDict() {
        return themesDict;
    }

    /* renamed from: -$$Nest$sfputlastBrightnessValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5811$$Nest$sfputlastBrightnessValue(float r0) {
        lastBrightnessValue = r0;
    }

    /* renamed from: -$$Nest$sfputswitchDayRunnableScheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5812$$Nest$sfputswitchDayRunnableScheduled(boolean r0) {
        switchDayRunnableScheduled = r0;
    }

    /* renamed from: -$$Nest$sfputswitchNightRunnableScheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5813$$Nest$sfputswitchNightRunnableScheduled(boolean r0) {
        switchNightRunnableScheduled = r0;
    }

    /* renamed from: -$$Nest$smapplyCalculatedArticleCodeColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5814$$Nest$smapplyCalculatedArticleCodeColors(SparseIntArray r0, SparseIntArray r1, boolean r2) {
        applyCalculatedArticleCodeColors(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smapplyCalculatedTableColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5815$$Nest$smapplyCalculatedTableColors(SparseIntArray r0, SparseIntArray r1, boolean r2) {
        applyCalculatedTableColors(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smcheckIsDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5816$$Nest$smcheckIsDark(SparseIntArray r0, ThemeInfo r1) {
        checkIsDark(r0, r1);
    }

    /* renamed from: -$$Nest$smgetAccentColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5817$$Nest$smgetAccentColor(float[] r0, int r1, int r2) {
        return getAccentColor(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smgetAutoNightSwitchThemeDelay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5818$$Nest$smgetAutoNightSwitchThemeDelay() {
        return getAutoNightSwitchThemeDelay();
    }

    /* renamed from: -$$Nest$smloadScreenSizedBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m5819$$Nest$smloadScreenSizedBitmap(FileInputStream r0, int r1) {
        return loadScreenSizedBitmap(r0, r1);
    }

    /* renamed from: -$$Nest$smsaveOtherThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5820$$Nest$smsaveOtherThemes(boolean r0) {
        saveOtherThemes(r0);
    }

    /* renamed from: -$$Nest$smsortAccents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5821$$Nest$smsortAccents(ThemeInfo r0) {
        sortAccents(r0);
    }

    /* renamed from: -$$Nest$smuseBlackText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5822$$Nest$smuseBlackText(int r0, int r1) {
        return useBlackText(r0, r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v75, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v81 */
    /* JADX WARN: Type inference failed for: r10v92 */
    static {
        default_shadow_color = ColorUtils.setAlphaComponent(-16777216, 27);
        sync = new Object();
        lastBrightnessValue = 1.0f;
        switchDayBrightnessRunnable = new AnonymousClass1();
        switchNightBrightnessRunnable = new AnonymousClass2();
        DEFALT_THEME_ACCENT_ID = 99;
        maskPaint = new Paint(1);
        loadingRemoteThemes = new boolean[16];
        lastLoadingThemesTime = new int[16];
        remoteThemesHash = new long[16];
        avatarDrawables = new Drawable[26];
        chat_status_drawables = new StatusDrawable[6];
        chat_msgInCallDrawable = new Drawable[2];
        chat_msgInCallSelectedDrawable = new Drawable[2];
        chat_msgOutCallDrawable = new Drawable[2];
        chat_msgOutCallSelectedDrawable = new Drawable[2];
        chat_pollCheckDrawable = new Drawable[2];
        chat_pollCrossDrawable = new Drawable[2];
        chat_pollHintDrawable = new Drawable[2];
        chat_psaHelpDrawable = new Drawable[2];
        chat_locationDrawable = new Drawable[2];
        chat_contactDrawable = new Drawable[2];
        chat_fileStatesDrawable = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{5, 2});
        chat_filePath = new Path[2];
        chat_updatePath = new Path[3];
        int r7 = colorsCount;
        key_wallpaperFileOffset = r7;
        key_dialogBackground = r7 + 1;
        key_dialogBackgroundGray = r7 + 2;
        key_dialogTextBlack = r7 + 3;
        key_dialogTextLink = r7 + 4;
        key_dialogLinkSelection = r7 + 5;
        key_dialogTextBlue = r7 + 6;
        key_dialogTextBlue2 = r7 + 7;
        key_dialogTextBlue4 = r7 + 8;
        key_dialogTextGray = r7 + 9;
        key_dialogTextGray2 = r7 + 10;
        key_dialogTextGray3 = r7 + 11;
        key_dialogTextGray4 = r7 + 12;
        key_dialogTextHint = r7 + 13;
        key_dialogInputField = r7 + 14;
        key_dialogInputFieldActivated = r7 + 15;
        key_dialogCheckboxSquareBackground = r7 + 16;
        key_dialogCheckboxSquareCheck = r7 + 17;
        key_dialogCheckboxSquareUnchecked = r7 + 18;
        key_dialogCheckboxSquareDisabled = r7 + 19;
        key_dialogScrollGlow = r7 + 20;
        key_dialogRoundCheckBox = r7 + 21;
        key_dialogRoundCheckBoxCheck = r7 + 22;
        key_dialogRadioBackground = r7 + 23;
        key_dialogRadioBackgroundChecked = r7 + 24;
        key_dialogLineProgress = r7 + 25;
        key_dialogLineProgressBackground = r7 + 26;
        key_dialogButton = r7 + 27;
        key_dialogButtonSelector = r7 + 28;
        key_dialogIcon = r7 + 29;
        key_dialogGrayLine = r7 + 30;
        key_dialogTopBackground = r7 + 31;
        key_dialog_inlineProgressBackground = r7 + 32;
        key_dialog_inlineProgress = r7 + 33;
        key_dialogSearchBackground = r7 + 34;
        key_dialogSearchHint = r7 + 35;
        key_dialogSearchIcon = r7 + 36;
        key_dialogSearchText = r7 + 37;
        key_dialogFloatingButton = r7 + 38;
        key_dialogFloatingButtonPressed = r7 + 39;
        key_dialogFloatingIcon = r7 + 40;
        key_dialogShadowLine = r7 + 41;
        colorsCount = r7 + 43;
        key_dialogEmptyImage = r7 + 42;
        int r72 = colorsCount;
        colorsCount = r72 + 1;
        key_dialogEmptyText = r72;
        int r73 = colorsCount;
        colorsCount = r73 + 1;
        key_dialogSwipeRemove = r73;
        int r74 = colorsCount;
        colorsCount = r74 + 1;
        key_dialogReactionMentionBackground = r74;
        int r75 = colorsCount;
        colorsCount = r75 + 1;
        key_dialogCardShadow = r75;
        int r76 = colorsCount;
        colorsCount = r76 + 1;
        key_dialogGiftsBackground = r76;
        int r77 = colorsCount;
        colorsCount = r77 + 1;
        key_dialogGiftsTabText = r77;
        int r78 = colorsCount;
        colorsCount = r78 + 1;
        key_windowBackgroundWhite = r78;
        int r79 = colorsCount;
        colorsCount = r79 + 1;
        key_windowBackgroundUnchecked = r79;
        int r710 = colorsCount;
        colorsCount = r710 + 1;
        key_windowBackgroundChecked = r710;
        int r711 = colorsCount;
        colorsCount = r711 + 1;
        key_windowBackgroundCheckText = r711;
        int r712 = colorsCount;
        colorsCount = r712 + 1;
        key_progressCircle = r712;
        int r713 = colorsCount;
        colorsCount = r713 + 1;
        key_listSelector = r713;
        int r714 = colorsCount;
        colorsCount = r714 + 1;
        key_settings_listSelector = r714;
        int r715 = colorsCount;
        colorsCount = r715 + 1;
        key_windowBackgroundWhiteInputField = r715;
        int r716 = colorsCount;
        colorsCount = r716 + 1;
        key_windowBackgroundWhiteInputFieldActivated = r716;
        int r717 = colorsCount;
        colorsCount = r717 + 1;
        key_windowBackgroundWhiteGrayIcon = r717;
        int r718 = colorsCount;
        colorsCount = r718 + 1;
        key_windowBackgroundWhiteBlueText = r718;
        int r719 = colorsCount;
        colorsCount = r719 + 1;
        key_windowBackgroundWhiteBlueText2 = r719;
        int r720 = colorsCount;
        colorsCount = r720 + 1;
        key_windowBackgroundWhiteBlueText3 = r720;
        int r721 = colorsCount;
        colorsCount = r721 + 1;
        key_windowBackgroundWhiteBlueText4 = r721;
        int r722 = colorsCount;
        colorsCount = r722 + 1;
        key_windowBackgroundWhiteBlueText5 = r722;
        int r723 = colorsCount;
        colorsCount = r723 + 1;
        key_windowBackgroundWhiteBlueText6 = r723;
        int r724 = colorsCount;
        colorsCount = r724 + 1;
        key_windowBackgroundWhiteBlueText7 = r724;
        int r725 = colorsCount;
        colorsCount = r725 + 1;
        key_windowBackgroundWhiteBlueButton = r725;
        int r726 = colorsCount;
        colorsCount = r726 + 1;
        key_windowBackgroundWhiteBlueIcon = r726;
        int r727 = colorsCount;
        colorsCount = r727 + 1;
        key_windowBackgroundWhiteGreenText = r727;
        int r728 = colorsCount;
        colorsCount = r728 + 1;
        key_windowBackgroundWhiteGreenText2 = r728;
        int r729 = colorsCount;
        colorsCount = r729 + 1;
        key_windowBackgroundWhiteGrayText = r729;
        int r730 = colorsCount;
        colorsCount = r730 + 1;
        key_windowBackgroundWhiteGrayText2 = r730;
        int r731 = colorsCount;
        colorsCount = r731 + 1;
        key_windowBackgroundWhiteGrayText3 = r731;
        int r732 = colorsCount;
        colorsCount = r732 + 1;
        key_windowBackgroundWhiteGrayText4 = r732;
        int r733 = colorsCount;
        colorsCount = r733 + 1;
        key_windowBackgroundWhiteGrayText5 = r733;
        int r734 = colorsCount;
        colorsCount = r734 + 1;
        key_windowBackgroundWhiteGrayText6 = r734;
        int r735 = colorsCount;
        colorsCount = r735 + 1;
        key_windowBackgroundWhiteGrayText7 = r735;
        int r736 = colorsCount;
        colorsCount = r736 + 1;
        key_windowBackgroundWhiteGrayText8 = r736;
        int r737 = colorsCount;
        colorsCount = r737 + 1;
        key_windowBackgroundWhiteBlackText = r737;
        int r738 = colorsCount;
        colorsCount = r738 + 1;
        key_windowBackgroundWhiteHintText = r738;
        int r739 = colorsCount;
        colorsCount = r739 + 1;
        key_windowBackgroundWhiteValueText = r739;
        int r740 = colorsCount;
        colorsCount = r740 + 1;
        key_windowBackgroundWhiteLinkText = r740;
        int r741 = colorsCount;
        colorsCount = r741 + 1;
        key_windowBackgroundWhiteLinkSelection = r741;
        int r742 = colorsCount;
        colorsCount = r742 + 1;
        key_windowBackgroundWhiteBlueHeader = r742;
        int r743 = colorsCount;
        colorsCount = r743 + 1;
        key_switchTrack = r743;
        int r744 = colorsCount;
        colorsCount = r744 + 1;
        key_switchTrackChecked = r744;
        int r745 = colorsCount;
        colorsCount = r745 + 1;
        key_switchTrackBlue = r745;
        int r746 = colorsCount;
        colorsCount = r746 + 1;
        key_switchTrackBlueChecked = r746;
        int r747 = colorsCount;
        colorsCount = r747 + 1;
        key_switchTrackBlueThumb = r747;
        int r748 = colorsCount;
        colorsCount = r748 + 1;
        key_switchTrackBlueThumbChecked = r748;
        int r749 = colorsCount;
        colorsCount = r749 + 1;
        key_switchTrackBlueSelector = r749;
        int r750 = colorsCount;
        colorsCount = r750 + 1;
        key_switchTrackBlueSelectorChecked = r750;
        int r751 = colorsCount;
        colorsCount = r751 + 1;
        key_switch2Track = r751;
        int r752 = colorsCount;
        colorsCount = r752 + 1;
        key_switch2TrackChecked = r752;
        int r753 = colorsCount;
        colorsCount = r753 + 1;
        key_checkboxSquareBackground = r753;
        int r754 = colorsCount;
        colorsCount = r754 + 1;
        key_checkboxSquareCheck = r754;
        int r755 = colorsCount;
        colorsCount = r755 + 1;
        key_checkboxSquareUnchecked = r755;
        int r756 = colorsCount;
        colorsCount = r756 + 1;
        key_checkboxSquareDisabled = r756;
        int r757 = colorsCount;
        colorsCount = r757 + 1;
        key_windowBackgroundGray = r757;
        int r758 = colorsCount;
        colorsCount = r758 + 1;
        key_windowBackgroundGrayShadow = r758;
        int r759 = colorsCount;
        colorsCount = r759 + 1;
        key_emptyListPlaceholder = r759;
        int r760 = colorsCount;
        colorsCount = r760 + 1;
        key_divider = r760;
        int r761 = colorsCount;
        colorsCount = r761 + 1;
        key_graySection = r761;
        int r762 = colorsCount;
        colorsCount = r762 + 1;
        key_graySectionText = r762;
        int r763 = colorsCount;
        colorsCount = r763 + 1;
        key_radioBackground = r763;
        int r764 = colorsCount;
        colorsCount = r764 + 1;
        key_radioBackgroundChecked = r764;
        int r765 = colorsCount;
        colorsCount = r765 + 1;
        key_checkbox = r765;
        int r766 = colorsCount;
        colorsCount = r766 + 1;
        key_checkboxDisabled = r766;
        int r767 = colorsCount;
        colorsCount = r767 + 1;
        key_checkboxCheck = r767;
        int r768 = colorsCount;
        colorsCount = r768 + 1;
        key_fastScrollActive = r768;
        int r769 = colorsCount;
        colorsCount = r769 + 1;
        key_fastScrollInactive = r769;
        int r770 = colorsCount;
        colorsCount = r770 + 1;
        key_fastScrollText = r770;
        int r771 = colorsCount;
        colorsCount = r771 + 1;
        key_pollCreateIcons = r771;
        int r772 = colorsCount;
        colorsCount = r772 + 1;
        key_text_RedRegular = r772;
        int r773 = colorsCount;
        colorsCount = r773 + 1;
        key_text_RedBold = r773;
        int r774 = colorsCount;
        colorsCount = r774 + 1;
        key_fill_RedNormal = r774;
        int r775 = colorsCount;
        colorsCount = r775 + 1;
        key_fill_RedDark = r775;
        int r776 = colorsCount;
        colorsCount = r776 + 1;
        key_inappPlayerPerformer = r776;
        int r777 = colorsCount;
        colorsCount = r777 + 1;
        key_inappPlayerTitle = r777;
        int r778 = colorsCount;
        colorsCount = r778 + 1;
        key_inappPlayerBackground = r778;
        int r779 = colorsCount;
        colorsCount = r779 + 1;
        key_inappPlayerPlayPause = r779;
        int r780 = colorsCount;
        colorsCount = r780 + 1;
        key_inappPlayerClose = r780;
        int r781 = colorsCount;
        colorsCount = r781 + 1;
        key_returnToCallBackground = r781;
        int r782 = colorsCount;
        colorsCount = r782 + 1;
        key_returnToCallMutedBackground = r782;
        int r783 = colorsCount;
        colorsCount = r783 + 1;
        key_returnToCallText = r783;
        int r784 = colorsCount;
        colorsCount = r784 + 1;
        key_contextProgressInner1 = r784;
        int r785 = colorsCount;
        colorsCount = r785 + 1;
        key_contextProgressOuter1 = r785;
        int r786 = colorsCount;
        colorsCount = r786 + 1;
        key_contextProgressInner2 = r786;
        int r787 = colorsCount;
        colorsCount = r787 + 1;
        key_contextProgressOuter2 = r787;
        int r788 = colorsCount;
        colorsCount = r788 + 1;
        key_contextProgressInner3 = r788;
        int r789 = colorsCount;
        colorsCount = r789 + 1;
        key_contextProgressOuter3 = r789;
        int r790 = colorsCount;
        colorsCount = r790 + 1;
        key_contextProgressInner4 = r790;
        int r791 = colorsCount;
        colorsCount = r791 + 1;
        key_contextProgressOuter4 = r791;
        int r792 = colorsCount;
        colorsCount = r792 + 1;
        key_avatar_text = r792;
        int r793 = colorsCount;
        colorsCount = r793 + 1;
        key_avatar_backgroundSaved = r793;
        int r794 = colorsCount;
        colorsCount = r794 + 1;
        key_avatar_background2Saved = r794;
        int r795 = colorsCount;
        colorsCount = r795 + 1;
        key_avatar_backgroundArchived = r795;
        int r796 = colorsCount;
        colorsCount = r796 + 1;
        key_avatar_backgroundArchivedHidden = r796;
        int r9 = colorsCount;
        colorsCount = r9 + 1;
        key_avatar_backgroundRed = r9;
        int r10 = colorsCount;
        colorsCount = r10 + 1;
        key_avatar_backgroundOrange = r10;
        int r11 = colorsCount;
        colorsCount = r11 + 1;
        key_avatar_backgroundViolet = r11;
        int r12 = colorsCount;
        colorsCount = r12 + 1;
        key_avatar_backgroundGreen = r12;
        int r13 = colorsCount;
        colorsCount = r13 + 1;
        key_avatar_backgroundCyan = r13;
        int r14 = colorsCount;
        colorsCount = r14 + 1;
        key_avatar_backgroundBlue = r14;
        int r15 = colorsCount;
        colorsCount = r15 + 1;
        key_avatar_backgroundPink = r15;
        int r16 = colorsCount;
        colorsCount = r16 + 1;
        key_avatar_background2Red = r16;
        int r17 = colorsCount;
        colorsCount = r17 + 1;
        key_avatar_background2Orange = r17;
        int r18 = colorsCount;
        colorsCount = r18 + 1;
        key_avatar_background2Violet = r18;
        int r19 = colorsCount;
        colorsCount = r19 + 1;
        key_avatar_background2Green = r19;
        int r20 = colorsCount;
        colorsCount = r20 + 1;
        key_avatar_background2Cyan = r20;
        int r21 = colorsCount;
        colorsCount = r21 + 1;
        key_avatar_background2Blue = r21;
        int r22 = colorsCount;
        colorsCount = r22 + 1;
        key_avatar_background2Pink = r22;
        int r797 = colorsCount;
        colorsCount = r797 + 1;
        key_avatar_backgroundGray = r797;
        int r798 = colorsCount;
        colorsCount = r798 + 1;
        key_avatar_backgroundInProfileBlue = r798;
        int r799 = colorsCount;
        colorsCount = r799 + 1;
        key_avatar_backgroundActionBarBlue = r799;
        int r7100 = colorsCount;
        colorsCount = r7100 + 1;
        key_avatar_actionBarSelectorBlue = r7100;
        int r7101 = colorsCount;
        colorsCount = r7101 + 1;
        key_avatar_actionBarIconBlue = r7101;
        int r7102 = colorsCount;
        colorsCount = r7102 + 1;
        key_avatar_subtitleInProfileBlue = r7102;
        int r23 = colorsCount;
        colorsCount = r23 + 1;
        key_avatar_nameInMessageRed = r23;
        int r24 = colorsCount;
        colorsCount = r24 + 1;
        key_avatar_nameInMessageOrange = r24;
        int r25 = colorsCount;
        colorsCount = r25 + 1;
        key_avatar_nameInMessageViolet = r25;
        int r26 = colorsCount;
        colorsCount = r26 + 1;
        key_avatar_nameInMessageGreen = r26;
        int r27 = colorsCount;
        colorsCount = r27 + 1;
        key_avatar_nameInMessageCyan = r27;
        int r28 = colorsCount;
        colorsCount = r28 + 1;
        key_avatar_nameInMessageBlue = r28;
        int r29 = colorsCount;
        colorsCount = r29 + 1;
        key_avatar_nameInMessagePink = r29;
        keys_avatar_background = new int[]{r9, r10, r11, r12, r13, r14, r15};
        keys_avatar_background2 = new int[]{r16, r17, r18, r19, r20, r21, r22};
        keys_avatar_nameInMessage = new int[]{r23, r24, r25, r26, r27, r28, r29};
        int r7103 = colorsCount;
        colorsCount = r7103 + 1;
        key_actionBarDefault = r7103;
        int r7104 = colorsCount;
        colorsCount = r7104 + 1;
        key_actionBarDefaultSelector = r7104;
        int r7105 = colorsCount;
        colorsCount = r7105 + 1;
        key_actionBarWhiteSelector = r7105;
        int r7106 = colorsCount;
        colorsCount = r7106 + 1;
        key_actionBarDefaultIcon = r7106;
        int r7107 = colorsCount;
        colorsCount = r7107 + 1;
        key_actionBarActionModeDefault = r7107;
        int r7108 = colorsCount;
        colorsCount = r7108 + 1;
        key_actionBarActionModeDefaultTop = r7108;
        int r7109 = colorsCount;
        colorsCount = r7109 + 1;
        key_actionBarActionModeDefaultIcon = r7109;
        int r7110 = colorsCount;
        colorsCount = r7110 + 1;
        key_actionBarActionModeDefaultSelector = r7110;
        int r7111 = colorsCount;
        colorsCount = r7111 + 1;
        key_actionBarDefaultTitle = r7111;
        int r7112 = colorsCount;
        colorsCount = r7112 + 1;
        key_actionBarDefaultSubtitle = r7112;
        int r7113 = colorsCount;
        colorsCount = r7113 + 1;
        key_actionBarDefaultSearch = r7113;
        int r7114 = colorsCount;
        colorsCount = r7114 + 1;
        key_actionBarDefaultSearchPlaceholder = r7114;
        int r7115 = colorsCount;
        colorsCount = r7115 + 1;
        key_actionBarDefaultSubmenuItem = r7115;
        int r7116 = colorsCount;
        colorsCount = r7116 + 1;
        key_actionBarDefaultSubmenuItemIcon = r7116;
        int r7117 = colorsCount;
        colorsCount = r7117 + 1;
        key_actionBarDefaultSubmenuBackground = r7117;
        int r7118 = colorsCount;
        colorsCount = r7118 + 1;
        key_actionBarDefaultSubmenuSeparator = r7118;
        int r7119 = colorsCount;
        colorsCount = r7119 + 1;
        key_actionBarTabActiveText = r7119;
        int r7120 = colorsCount;
        colorsCount = r7120 + 1;
        key_actionBarTabUnactiveText = r7120;
        int r7121 = colorsCount;
        colorsCount = r7121 + 1;
        key_actionBarTabLine = r7121;
        int r7122 = colorsCount;
        colorsCount = r7122 + 1;
        key_actionBarTabSelector = r7122;
        int r7123 = colorsCount;
        colorsCount = r7123 + 1;
        key_actionBarDefaultArchived = r7123;
        int r7124 = colorsCount;
        colorsCount = r7124 + 1;
        key_actionBarDefaultArchivedSelector = r7124;
        int r7125 = colorsCount;
        colorsCount = r7125 + 1;
        key_actionBarDefaultArchivedIcon = r7125;
        int r7126 = colorsCount;
        colorsCount = r7126 + 1;
        key_actionBarDefaultArchivedTitle = r7126;
        int r7127 = colorsCount;
        colorsCount = r7127 + 1;
        key_actionBarDefaultArchivedSearch = r7127;
        int r7128 = colorsCount;
        colorsCount = r7128 + 1;
        key_actionBarDefaultArchivedSearchPlaceholder = r7128;
        int r7129 = colorsCount;
        colorsCount = r7129 + 1;
        key_actionBarBrowser = r7129;
        int r7130 = colorsCount;
        colorsCount = r7130 + 1;
        key_chats_onlineCircle = r7130;
        int r7131 = colorsCount;
        colorsCount = r7131 + 1;
        key_chats_unreadCounter = r7131;
        int r7132 = colorsCount;
        colorsCount = r7132 + 1;
        key_chats_unreadCounterMuted = r7132;
        int r7133 = colorsCount;
        colorsCount = r7133 + 1;
        key_chats_unreadCounterText = r7133;
        int r7134 = colorsCount;
        colorsCount = r7134 + 1;
        key_chats_name = r7134;
        int r7135 = colorsCount;
        colorsCount = r7135 + 1;
        key_chats_nameArchived = r7135;
        int r7136 = colorsCount;
        colorsCount = r7136 + 1;
        key_chats_secretName = r7136;
        int r7137 = colorsCount;
        colorsCount = r7137 + 1;
        key_chats_secretIcon = r7137;
        int r7138 = colorsCount;
        colorsCount = r7138 + 1;
        key_chats_pinnedIcon = r7138;
        int r7139 = colorsCount;
        colorsCount = r7139 + 1;
        key_chats_archiveBackground = r7139;
        int r7140 = colorsCount;
        colorsCount = r7140 + 1;
        key_chats_archivePinBackground = r7140;
        int r7141 = colorsCount;
        colorsCount = r7141 + 1;
        key_chats_archiveIcon = r7141;
        int r7142 = colorsCount;
        colorsCount = r7142 + 1;
        key_chats_archiveText = r7142;
        int r7143 = colorsCount;
        colorsCount = r7143 + 1;
        key_chats_message = r7143;
        int r7144 = colorsCount;
        colorsCount = r7144 + 1;
        key_chats_messageArchived = r7144;
        int r7145 = colorsCount;
        colorsCount = r7145 + 1;
        key_chats_message_threeLines = r7145;
        int r7146 = colorsCount;
        colorsCount = r7146 + 1;
        key_chats_draft = r7146;
        int r7147 = colorsCount;
        colorsCount = r7147 + 1;
        key_chats_nameMessage = r7147;
        int r7148 = colorsCount;
        colorsCount = r7148 + 1;
        key_chats_nameMessageArchived = r7148;
        int r7149 = colorsCount;
        colorsCount = r7149 + 1;
        key_chats_nameMessage_threeLines = r7149;
        int r7150 = colorsCount;
        colorsCount = r7150 + 1;
        key_chats_nameMessageArchived_threeLines = r7150;
        int r7151 = colorsCount;
        colorsCount = r7151 + 1;
        key_chats_attachMessage = r7151;
        int r7152 = colorsCount;
        colorsCount = r7152 + 1;
        key_chats_actionMessage = r7152;
        int r7153 = colorsCount;
        colorsCount = r7153 + 1;
        key_chats_date = r7153;
        int r7154 = colorsCount;
        colorsCount = r7154 + 1;
        key_chats_date_bold = r7154;
        int r7155 = colorsCount;
        colorsCount = r7155 + 1;
        key_chats_pinnedOverlay = r7155;
        int r7156 = colorsCount;
        colorsCount = r7156 + 1;
        key_chats_tabletSelectedOverlay = r7156;
        int r7157 = colorsCount;
        colorsCount = r7157 + 1;
        key_chats_sentCheck = r7157;
        int r7158 = colorsCount;
        colorsCount = r7158 + 1;
        key_chats_sentReadCheck = r7158;
        int r7159 = colorsCount;
        colorsCount = r7159 + 1;
        key_chats_sentClock = r7159;
        int r7160 = colorsCount;
        colorsCount = r7160 + 1;
        key_chats_sentError = r7160;
        int r7161 = colorsCount;
        colorsCount = r7161 + 1;
        key_chats_sentErrorIcon = r7161;
        int r7162 = colorsCount;
        colorsCount = r7162 + 1;
        key_chats_verifiedBackground = r7162;
        int r7163 = colorsCount;
        colorsCount = r7163 + 1;
        key_chats_verifiedCheck = r7163;
        int r7164 = colorsCount;
        colorsCount = r7164 + 1;
        key_chats_muteIcon = r7164;
        int r7165 = colorsCount;
        colorsCount = r7165 + 1;
        key_chats_mentionIcon = r7165;
        int r7166 = colorsCount;
        colorsCount = r7166 + 1;
        key_chats_menuTopShadow = r7166;
        int r7167 = colorsCount;
        colorsCount = r7167 + 1;
        key_chats_menuTopShadowCats = r7167;
        int r7168 = colorsCount;
        colorsCount = r7168 + 1;
        key_chats_menuBackground = r7168;
        int r7169 = colorsCount;
        colorsCount = r7169 + 1;
        key_chats_menuItemText = r7169;
        int r7170 = colorsCount;
        colorsCount = r7170 + 1;
        key_chats_menuItemCheck = r7170;
        int r7171 = colorsCount;
        colorsCount = r7171 + 1;
        key_chats_menuItemIcon = r7171;
        int r7172 = colorsCount;
        colorsCount = r7172 + 1;
        key_chats_menuName = r7172;
        int r7173 = colorsCount;
        colorsCount = r7173 + 1;
        key_chats_menuPhone = r7173;
        int r7174 = colorsCount;
        colorsCount = r7174 + 1;
        key_chats_menuPhoneCats = r7174;
        int r7175 = colorsCount;
        colorsCount = r7175 + 1;
        key_chats_menuTopBackgroundCats = r7175;
        int r7176 = colorsCount;
        colorsCount = r7176 + 1;
        key_chats_menuTopBackground = r7176;
        int r7177 = colorsCount;
        colorsCount = r7177 + 1;
        key_chats_actionIcon = r7177;
        int r7178 = colorsCount;
        colorsCount = r7178 + 1;
        key_chats_actionBackground = r7178;
        int r7179 = colorsCount;
        colorsCount = r7179 + 1;
        key_chats_actionPressedBackground = r7179;
        int r7180 = colorsCount;
        colorsCount = r7180 + 1;
        key_chats_archivePullDownBackground = r7180;
        int r7181 = colorsCount;
        colorsCount = r7181 + 1;
        key_chats_archivePullDownBackgroundActive = r7181;
        int r7182 = colorsCount;
        colorsCount = r7182 + 1;
        key_chats_tabUnreadActiveBackground = r7182;
        int r7183 = colorsCount;
        colorsCount = r7183 + 1;
        key_chats_tabUnreadUnactiveBackground = r7183;
        int r7184 = colorsCount;
        colorsCount = r7184 + 1;
        key_chat_attachCheckBoxCheck = r7184;
        int r7185 = colorsCount;
        colorsCount = r7185 + 1;
        key_chat_attachCheckBoxBackground = r7185;
        int r7186 = colorsCount;
        colorsCount = r7186 + 1;
        key_chat_attachPhotoBackground = r7186;
        int r7187 = colorsCount;
        colorsCount = r7187 + 1;
        key_chat_attachActiveTab = r7187;
        int r7188 = colorsCount;
        colorsCount = r7188 + 1;
        key_chat_attachUnactiveTab = r7188;
        int r7189 = colorsCount;
        colorsCount = r7189 + 1;
        key_chat_attachPermissionImage = r7189;
        int r7190 = colorsCount;
        colorsCount = r7190 + 1;
        key_chat_attachPermissionMark = r7190;
        int r7191 = colorsCount;
        colorsCount = r7191 + 1;
        key_chat_attachPermissionText = r7191;
        int r7192 = colorsCount;
        colorsCount = r7192 + 1;
        key_chat_attachEmptyImage = r7192;
        int r7193 = colorsCount;
        colorsCount = r7193 + 1;
        key_chat_inPollCorrectAnswer = r7193;
        int r7194 = colorsCount;
        colorsCount = r7194 + 1;
        key_chat_outPollCorrectAnswer = r7194;
        int r7195 = colorsCount;
        colorsCount = r7195 + 1;
        key_chat_inPollWrongAnswer = r7195;
        int r7196 = colorsCount;
        colorsCount = r7196 + 1;
        key_chat_outPollWrongAnswer = r7196;
        int r7197 = colorsCount;
        colorsCount = r7197 + 1;
        key_chat_attachIcon = r7197;
        int r7198 = colorsCount;
        colorsCount = r7198 + 1;
        key_chat_attachGalleryBackground = r7198;
        int r7199 = colorsCount;
        colorsCount = r7199 + 1;
        key_chat_attachAudioBackground = r7199;
        int r7200 = colorsCount;
        colorsCount = r7200 + 1;
        key_chat_attachContactBackground = r7200;
        int r7201 = colorsCount;
        colorsCount = r7201 + 1;
        key_chat_attachContactText = r7201;
        int r7202 = colorsCount;
        colorsCount = r7202 + 1;
        key_chat_attachLocationBackground = r7202;
        int r7203 = colorsCount;
        colorsCount = r7203 + 1;
        key_chat_attachPollBackground = r7203;
        int r7204 = colorsCount;
        colorsCount = r7204 + 1;
        key_chat_status = r7204;
        int r7205 = colorsCount;
        colorsCount = r7205 + 1;
        key_chat_inGreenCall = r7205;
        int r7206 = colorsCount;
        colorsCount = r7206 + 1;
        key_chat_inBubble = r7206;
        int r7207 = colorsCount;
        colorsCount = r7207 + 1;
        key_chat_inBubbleSelectedOverlay = r7207;
        int r7208 = colorsCount;
        colorsCount = r7208 + 1;
        key_chat_inBubbleShadow = r7208;
        int r7209 = colorsCount;
        colorsCount = r7209 + 1;
        key_actionBarActionModeReaction = r7209;
        int r7210 = colorsCount;
        colorsCount = r7210 + 1;
        key_actionBarActionModeReactionText = r7210;
        int r7211 = colorsCount;
        colorsCount = r7211 + 1;
        key_actionBarActionModeReactionDot = r7211;
        int r7212 = colorsCount;
        colorsCount = r7212 + 1;
        key_buttonNeutral = r7212;
        int r7213 = colorsCount;
        colorsCount = r7213 + 1;
        key_buttonNeutralText = r7213;
        myMessagesBubblesStartIndex = colorsCount;
        int r7214 = colorsCount;
        colorsCount = r7214 + 1;
        key_chat_outBubble = r7214;
        int r7215 = colorsCount;
        colorsCount = r7215 + 1;
        key_chat_outBubbleSelected = r7215;
        int r7216 = colorsCount;
        colorsCount = r7216 + 1;
        key_chat_outBubbleShadow = r7216;
        int r7217 = colorsCount;
        colorsCount = r7217 + 1;
        key_chat_outBubbleGradient1 = r7217;
        int r7218 = colorsCount;
        colorsCount = r7218 + 1;
        key_chat_outBubbleGradient2 = r7218;
        int r7219 = colorsCount;
        colorsCount = r7219 + 1;
        key_chat_outBubbleGradient3 = r7219;
        myMessagesBubblesEndIndex = colorsCount;
        myMessagesStartIndex = colorsCount;
        int r7220 = colorsCount;
        colorsCount = r7220 + 1;
        key_chat_outGreenCall = r7220;
        int r7221 = colorsCount;
        colorsCount = r7221 + 1;
        key_chat_outSentCheck = r7221;
        int r7222 = colorsCount;
        colorsCount = r7222 + 1;
        key_chat_outSentCheckSelected = r7222;
        int r7223 = colorsCount;
        colorsCount = r7223 + 1;
        key_chat_outSentCheckRead = r7223;
        int r7224 = colorsCount;
        colorsCount = r7224 + 1;
        key_chat_outSentCheckReadSelected = r7224;
        int r7225 = colorsCount;
        colorsCount = r7225 + 1;
        key_chat_outSentClock = r7225;
        int r7226 = colorsCount;
        colorsCount = r7226 + 1;
        key_chat_outSentClockSelected = r7226;
        int r7227 = colorsCount;
        colorsCount = r7227 + 1;
        key_chat_outMediaIcon = r7227;
        int r7228 = colorsCount;
        colorsCount = r7228 + 1;
        key_chat_outMediaIconSelected = r7228;
        int r7229 = colorsCount;
        colorsCount = r7229 + 1;
        key_chat_outViews = r7229;
        int r7230 = colorsCount;
        colorsCount = r7230 + 1;
        key_chat_outViewsSelected = r7230;
        int r7231 = colorsCount;
        colorsCount = r7231 + 1;
        key_chat_outMenu = r7231;
        int r7232 = colorsCount;
        colorsCount = r7232 + 1;
        key_chat_outMenuSelected = r7232;
        int r7233 = colorsCount;
        colorsCount = r7233 + 1;
        key_chat_outInstant = r7233;
        int r7234 = colorsCount;
        colorsCount = r7234 + 1;
        key_chat_outInstantSelected = r7234;
        int r7235 = colorsCount;
        colorsCount = r7235 + 1;
        key_chat_outPreviewInstantText = r7235;
        int r7236 = colorsCount;
        colorsCount = r7236 + 1;
        key_chat_outForwardedNameText = r7236;
        int r7237 = colorsCount;
        colorsCount = r7237 + 1;
        key_chat_outViaBotNameText = r7237;
        int r7238 = colorsCount;
        colorsCount = r7238 + 1;
        key_chat_outReplyLine = r7238;
        int r7239 = colorsCount;
        colorsCount = r7239 + 1;
        key_chat_outReplyLine2 = r7239;
        int r7240 = colorsCount;
        colorsCount = r7240 + 1;
        key_chat_outReplyNameText = r7240;
        int r7241 = colorsCount;
        colorsCount = r7241 + 1;
        key_chat_outReplyMessageText = r7241;
        int r7242 = colorsCount;
        colorsCount = r7242 + 1;
        key_chat_outReplyMediaMessageText = r7242;
        int r7243 = colorsCount;
        colorsCount = r7243 + 1;
        key_chat_outReplyMediaMessageSelectedText = r7243;
        int r7244 = colorsCount;
        colorsCount = r7244 + 1;
        key_chat_outPreviewLine = r7244;
        int r7245 = colorsCount;
        colorsCount = r7245 + 1;
        key_chat_outSiteNameText = r7245;
        int r7246 = colorsCount;
        colorsCount = r7246 + 1;
        key_chat_outContactNameText = r7246;
        int r7247 = colorsCount;
        colorsCount = r7247 + 1;
        key_chat_outContactPhoneText = r7247;
        int r7248 = colorsCount;
        colorsCount = r7248 + 1;
        key_chat_outContactPhoneSelectedText = r7248;
        int r7249 = colorsCount;
        colorsCount = r7249 + 1;
        key_chat_outAudioPerformerText = r7249;
        int r7250 = colorsCount;
        colorsCount = r7250 + 1;
        key_chat_outAudioPerformerSelectedText = r7250;
        int r7251 = colorsCount;
        colorsCount = r7251 + 1;
        key_chat_outTimeSelectedText = r7251;
        int r7252 = colorsCount;
        colorsCount = r7252 + 1;
        key_chat_outAdminText = r7252;
        int r7253 = colorsCount;
        colorsCount = r7253 + 1;
        key_chat_outAdminSelectedText = r7253;
        int r7254 = colorsCount;
        colorsCount = r7254 + 1;
        key_chat_outAudioProgress = r7254;
        int r7255 = colorsCount;
        colorsCount = r7255 + 1;
        key_chat_outAudioSelectedProgress = r7255;
        int r7256 = colorsCount;
        colorsCount = r7256 + 1;
        key_chat_outTimeText = r7256;
        int r7257 = colorsCount;
        colorsCount = r7257 + 1;
        key_chat_outAudioTitleText = r7257;
        int r7258 = colorsCount;
        colorsCount = r7258 + 1;
        key_chat_outAudioDurationText = r7258;
        int r7259 = colorsCount;
        colorsCount = r7259 + 1;
        key_chat_outAudioDurationSelectedText = r7259;
        int r7260 = colorsCount;
        colorsCount = r7260 + 1;
        key_chat_outAudioSeekbar = r7260;
        int r7261 = colorsCount;
        colorsCount = r7261 + 1;
        key_chat_outAudioCacheSeekbar = r7261;
        int r7262 = colorsCount;
        colorsCount = r7262 + 1;
        key_chat_outAudioSeekbarSelected = r7262;
        int r7263 = colorsCount;
        colorsCount = r7263 + 1;
        key_chat_outAudioSeekbarFill = r7263;
        int r7264 = colorsCount;
        colorsCount = r7264 + 1;
        key_chat_outVoiceSeekbar = r7264;
        int r7265 = colorsCount;
        colorsCount = r7265 + 1;
        key_chat_outVoiceSeekbarSelected = r7265;
        int r7266 = colorsCount;
        colorsCount = r7266 + 1;
        key_chat_outVoiceSeekbarFill = r7266;
        int r7267 = colorsCount;
        colorsCount = r7267 + 1;
        key_chat_outFileProgress = r7267;
        int r7268 = colorsCount;
        colorsCount = r7268 + 1;
        key_chat_outFileProgressSelected = r7268;
        int r7269 = colorsCount;
        colorsCount = r7269 + 1;
        key_chat_outFileNameText = r7269;
        int r7270 = colorsCount;
        colorsCount = r7270 + 1;
        key_chat_outFileInfoText = r7270;
        int r7271 = colorsCount;
        colorsCount = r7271 + 1;
        key_chat_outFileInfoSelectedText = r7271;
        int r7272 = colorsCount;
        colorsCount = r7272 + 1;
        key_chat_outFileBackground = r7272;
        int r7273 = colorsCount;
        colorsCount = r7273 + 1;
        key_chat_outFileBackgroundSelected = r7273;
        int r7274 = colorsCount;
        colorsCount = r7274 + 1;
        key_chat_outVenueInfoText = r7274;
        int r7275 = colorsCount;
        colorsCount = r7275 + 1;
        key_chat_outVenueInfoSelectedText = r7275;
        int r7276 = colorsCount;
        colorsCount = r7276 + 1;
        key_chat_outLinkSelectBackground = r7276;
        int r7277 = colorsCount;
        colorsCount = r7277 + 1;
        key_chat_outLoader = r7277;
        int r7278 = colorsCount;
        colorsCount = r7278 + 1;
        key_chat_outLoaderSelected = r7278;
        int r7279 = colorsCount;
        colorsCount = r7279 + 1;
        key_chat_outLocationIcon = r7279;
        int r7280 = colorsCount;
        colorsCount = r7280 + 1;
        key_chat_outContactBackground = r7280;
        int r7281 = colorsCount;
        colorsCount = r7281 + 1;
        key_chat_outContactIcon = r7281;
        int r7282 = colorsCount;
        colorsCount = r7282 + 1;
        key_chat_outReactionButtonBackground = r7282;
        myMessagesEndIndex = colorsCount;
        myMessages2StartIndex = colorsCount;
        int r7283 = colorsCount;
        colorsCount = r7283 + 1;
        key_chat_outTextSelectionHighlight = r7283;
        int r7284 = colorsCount;
        colorsCount = r7284 + 1;
        key_chat_outTextSelectionCursor = r7284;
        int r7285 = colorsCount;
        colorsCount = r7285 + 1;
        key_chat_outBubbleLocationPlaceholder = r7285;
        int r7286 = colorsCount;
        colorsCount = r7286 + 1;
        key_chat_outBubbleSelectedOverlay = r7286;
        int r7287 = colorsCount;
        colorsCount = r7287 + 1;
        key_chat_outPsaNameText = r7287;
        int r7288 = colorsCount;
        colorsCount = r7288 + 1;
        key_chat_outBubbleGradientAnimated = r7288;
        int r7289 = colorsCount;
        colorsCount = r7289 + 1;
        key_chat_outBubbleGradientSelectedOverlay = r7289;
        myMessages2EndIndex = colorsCount;
        int r7290 = colorsCount;
        colorsCount = r7290 + 1;
        key_chat_inBubbleSelected = r7290;
        int r7291 = colorsCount;
        colorsCount = r7291 + 1;
        key_chat_messageTextIn = r7291;
        int r7292 = colorsCount;
        colorsCount = r7292 + 1;
        key_chat_messageTextOut = r7292;
        int r7293 = colorsCount;
        colorsCount = r7293 + 1;
        key_chat_messageLinkIn = r7293;
        int r7294 = colorsCount;
        colorsCount = r7294 + 1;
        key_chat_messageLinkOut = r7294;
        int r7295 = colorsCount;
        colorsCount = r7295 + 1;
        key_chat_serviceText = r7295;
        int r7296 = colorsCount;
        colorsCount = r7296 + 1;
        key_chat_serviceLink = r7296;
        int r7297 = colorsCount;
        colorsCount = r7297 + 1;
        key_chat_serviceIcon = r7297;
        int r7298 = colorsCount;
        colorsCount = r7298 + 1;
        key_chat_serviceBackground = r7298;
        int r7299 = colorsCount;
        colorsCount = r7299 + 1;
        key_chat_serviceBackgroundSelected = r7299;
        int r7300 = colorsCount;
        colorsCount = r7300 + 1;
        key_chat_serviceBackgroundSelector = r7300;
        int r7301 = colorsCount;
        colorsCount = r7301 + 1;
        key_chat_muteIcon = r7301;
        int r7302 = colorsCount;
        colorsCount = r7302 + 1;
        key_chat_lockIcon = r7302;
        int r7303 = colorsCount;
        colorsCount = r7303 + 1;
        key_chat_inSentClock = r7303;
        int r7304 = colorsCount;
        colorsCount = r7304 + 1;
        key_chat_inSentClockSelected = r7304;
        int r7305 = colorsCount;
        colorsCount = r7305 + 1;
        key_chat_mediaSentCheck = r7305;
        int r7306 = colorsCount;
        colorsCount = r7306 + 1;
        key_chat_mediaSentClock = r7306;
        int r7307 = colorsCount;
        colorsCount = r7307 + 1;
        key_chat_inMediaIcon = r7307;
        int r7308 = colorsCount;
        colorsCount = r7308 + 1;
        key_chat_inMediaIconSelected = r7308;
        int r7309 = colorsCount;
        colorsCount = r7309 + 1;
        key_chat_mediaTimeBackground = r7309;
        int r7310 = colorsCount;
        colorsCount = r7310 + 1;
        key_chat_inViews = r7310;
        int r7311 = colorsCount;
        colorsCount = r7311 + 1;
        key_chat_inViewsSelected = r7311;
        int r7312 = colorsCount;
        colorsCount = r7312 + 1;
        key_chat_mediaViews = r7312;
        int r7313 = colorsCount;
        colorsCount = r7313 + 1;
        key_chat_inMenu = r7313;
        int r7314 = colorsCount;
        colorsCount = r7314 + 1;
        key_chat_inMenuSelected = r7314;
        int r7315 = colorsCount;
        colorsCount = r7315 + 1;
        key_chat_mediaMenu = r7315;
        int r7316 = colorsCount;
        colorsCount = r7316 + 1;
        key_chat_inInstant = r7316;
        int r7317 = colorsCount;
        colorsCount = r7317 + 1;
        key_chat_inInstantSelected = r7317;
        int r7318 = colorsCount;
        colorsCount = r7318 + 1;
        key_chat_sentError = r7318;
        int r7319 = colorsCount;
        colorsCount = r7319 + 1;
        key_chat_sentErrorIcon = r7319;
        int r7320 = colorsCount;
        colorsCount = r7320 + 1;
        key_chat_selectedBackground = r7320;
        int r7321 = colorsCount;
        colorsCount = r7321 + 1;
        key_chat_previewDurationText = r7321;
        int r7322 = colorsCount;
        colorsCount = r7322 + 1;
        key_chat_previewGameText = r7322;
        int r7323 = colorsCount;
        colorsCount = r7323 + 1;
        key_chat_inPreviewInstantText = r7323;
        int r7324 = colorsCount;
        colorsCount = r7324 + 1;
        key_chat_secretTimeText = r7324;
        int r7325 = colorsCount;
        colorsCount = r7325 + 1;
        key_chat_stickerNameText = r7325;
        int r7326 = colorsCount;
        colorsCount = r7326 + 1;
        key_chat_botButtonText = r7326;
        int r7327 = colorsCount;
        colorsCount = r7327 + 1;
        key_chat_inForwardedNameText = r7327;
        int r7328 = colorsCount;
        colorsCount = r7328 + 1;
        key_chat_inPsaNameText = r7328;
        int r7329 = colorsCount;
        colorsCount = r7329 + 1;
        key_chat_inViaBotNameText = r7329;
        int r7330 = colorsCount;
        colorsCount = r7330 + 1;
        key_chat_stickerViaBotNameText = r7330;
        int r7331 = colorsCount;
        colorsCount = r7331 + 1;
        key_chat_inQuote = r7331;
        int r7332 = colorsCount;
        colorsCount = r7332 + 1;
        key_chat_outQuote = r7332;
        int r7333 = colorsCount;
        colorsCount = r7333 + 1;
        key_chat_inReplyLine = r7333;
        int r7334 = colorsCount;
        colorsCount = r7334 + 1;
        key_chat_stickerReplyLine = r7334;
        int r7335 = colorsCount;
        colorsCount = r7335 + 1;
        key_chat_inReplyNameText = r7335;
        int r7336 = colorsCount;
        colorsCount = r7336 + 1;
        key_chat_stickerReplyNameText = r7336;
        int r7337 = colorsCount;
        colorsCount = r7337 + 1;
        key_chat_inReplyMessageText = r7337;
        int r7338 = colorsCount;
        colorsCount = r7338 + 1;
        key_chat_inReplyMediaMessageText = r7338;
        int r7339 = colorsCount;
        colorsCount = r7339 + 1;
        key_chat_inReplyMediaMessageSelectedText = r7339;
        int r7340 = colorsCount;
        colorsCount = r7340 + 1;
        key_chat_stickerReplyMessageText = r7340;
        int r7341 = colorsCount;
        colorsCount = r7341 + 1;
        key_chat_inPreviewLine = r7341;
        int r7342 = colorsCount;
        colorsCount = r7342 + 1;
        key_chat_inSiteNameText = r7342;
        int r7343 = colorsCount;
        colorsCount = r7343 + 1;
        key_chat_inContactNameText = r7343;
        int r7344 = colorsCount;
        colorsCount = r7344 + 1;
        key_chat_inContactPhoneText = r7344;
        int r7345 = colorsCount;
        colorsCount = r7345 + 1;
        key_chat_inContactPhoneSelectedText = r7345;
        int r7346 = colorsCount;
        colorsCount = r7346 + 1;
        key_chat_mediaProgress = r7346;
        int r7347 = colorsCount;
        colorsCount = r7347 + 1;
        key_chat_inAudioProgress = r7347;
        int r7348 = colorsCount;
        colorsCount = r7348 + 1;
        key_chat_inAudioSelectedProgress = r7348;
        int r7349 = colorsCount;
        colorsCount = r7349 + 1;
        key_chat_mediaTimeText = r7349;
        int r7350 = colorsCount;
        colorsCount = r7350 + 1;
        key_chat_inAdminText = r7350;
        int r7351 = colorsCount;
        colorsCount = r7351 + 1;
        key_chat_inAdminSelectedText = r7351;
        int r7352 = colorsCount;
        colorsCount = r7352 + 1;
        key_chat_inTimeText = r7352;
        int r7353 = colorsCount;
        colorsCount = r7353 + 1;
        key_chat_inTimeSelectedText = r7353;
        int r7354 = colorsCount;
        colorsCount = r7354 + 1;
        key_chat_inAudioPerformerText = r7354;
        int r7355 = colorsCount;
        colorsCount = r7355 + 1;
        key_chat_inAudioPerformerSelectedText = r7355;
        int r7356 = colorsCount;
        colorsCount = r7356 + 1;
        key_chat_inAudioTitleText = r7356;
        int r7357 = colorsCount;
        colorsCount = r7357 + 1;
        key_chat_inAudioDurationText = r7357;
        int r7358 = colorsCount;
        colorsCount = r7358 + 1;
        key_chat_inAudioDurationSelectedText = r7358;
        int r7359 = colorsCount;
        colorsCount = r7359 + 1;
        key_chat_inAudioSeekbar = r7359;
        int r7360 = colorsCount;
        colorsCount = r7360 + 1;
        key_chat_inAudioCacheSeekbar = r7360;
        int r7361 = colorsCount;
        colorsCount = r7361 + 1;
        key_chat_inAudioSeekbarSelected = r7361;
        int r7362 = colorsCount;
        colorsCount = r7362 + 1;
        key_chat_inAudioSeekbarFill = r7362;
        int r7363 = colorsCount;
        colorsCount = r7363 + 1;
        key_chat_inVoiceSeekbar = r7363;
        int r7364 = colorsCount;
        colorsCount = r7364 + 1;
        key_chat_inVoiceSeekbarSelected = r7364;
        int r7365 = colorsCount;
        colorsCount = r7365 + 1;
        key_chat_inVoiceSeekbarFill = r7365;
        int r7366 = colorsCount;
        colorsCount = r7366 + 1;
        key_chat_inFileProgress = r7366;
        int r7367 = colorsCount;
        colorsCount = r7367 + 1;
        key_chat_inFileProgressSelected = r7367;
        int r7368 = colorsCount;
        colorsCount = r7368 + 1;
        key_chat_inFileNameText = r7368;
        int r7369 = colorsCount;
        colorsCount = r7369 + 1;
        key_chat_inFileInfoText = r7369;
        int r7370 = colorsCount;
        colorsCount = r7370 + 1;
        key_chat_inFileInfoSelectedText = r7370;
        int r7371 = colorsCount;
        colorsCount = r7371 + 1;
        key_chat_inFileBackground = r7371;
        int r7372 = colorsCount;
        colorsCount = r7372 + 1;
        key_chat_inFileBackgroundSelected = r7372;
        int r7373 = colorsCount;
        colorsCount = r7373 + 1;
        key_chat_inVenueInfoText = r7373;
        int r7374 = colorsCount;
        colorsCount = r7374 + 1;
        key_chat_inVenueInfoSelectedText = r7374;
        int r7375 = colorsCount;
        colorsCount = r7375 + 1;
        key_chat_mediaInfoText = r7375;
        int r7376 = colorsCount;
        colorsCount = r7376 + 1;
        key_chat_linkSelectBackground = r7376;
        int r7377 = colorsCount;
        colorsCount = r7377 + 1;
        key_chat_textSelectBackground = r7377;
        int r7378 = colorsCount;
        colorsCount = r7378 + 1;
        key_chat_wallpaper = r7378;
        int r7379 = colorsCount;
        colorsCount = r7379 + 1;
        key_chat_wallpaper_gradient_to1 = r7379;
        int r7380 = colorsCount;
        colorsCount = r7380 + 1;
        key_chat_wallpaper_gradient_to2 = r7380;
        int r7381 = colorsCount;
        colorsCount = r7381 + 1;
        key_chat_wallpaper_gradient_to3 = r7381;
        int r7382 = colorsCount;
        colorsCount = r7382 + 1;
        key_chat_wallpaper_gradient_rotation = r7382;
        int r7383 = colorsCount;
        colorsCount = r7383 + 1;
        key_chat_messagePanelBackground = r7383;
        int r7384 = colorsCount;
        colorsCount = r7384 + 1;
        key_chat_messagePanelShadow = r7384;
        int r7385 = colorsCount;
        colorsCount = r7385 + 1;
        key_chat_messagePanelText = r7385;
        int r7386 = colorsCount;
        colorsCount = r7386 + 1;
        key_chat_messagePanelHint = r7386;
        int r7387 = colorsCount;
        colorsCount = r7387 + 1;
        key_chat_messagePanelCursor = r7387;
        int r7388 = colorsCount;
        colorsCount = r7388 + 1;
        key_chat_messagePanelIcons = r7388;
        int r7389 = colorsCount;
        colorsCount = r7389 + 1;
        key_chat_messagePanelSend = r7389;
        int r7390 = colorsCount;
        colorsCount = r7390 + 1;
        key_chat_messagePanelVoiceLock = r7390;
        int r7391 = colorsCount;
        colorsCount = r7391 + 1;
        key_chat_messagePanelVoiceLockBackground = r7391;
        int r7392 = colorsCount;
        colorsCount = r7392 + 1;
        key_chat_messagePanelVoiceLockShadow = r7392;
        int r7393 = colorsCount;
        colorsCount = r7393 + 1;
        key_chat_topPanelBackground = r7393;
        int r7394 = colorsCount;
        colorsCount = r7394 + 1;
        key_chat_topPanelClose = r7394;
        int r7395 = colorsCount;
        colorsCount = r7395 + 1;
        key_chat_topPanelLine = r7395;
        int r7396 = colorsCount;
        colorsCount = r7396 + 1;
        key_chat_topPanelTitle = r7396;
        int r7397 = colorsCount;
        colorsCount = r7397 + 1;
        key_chat_topPanelMessage = r7397;
        int r7398 = colorsCount;
        colorsCount = r7398 + 1;
        key_chat_addContact = r7398;
        int r7399 = colorsCount;
        colorsCount = r7399 + 1;
        key_chat_inLoader = r7399;
        int r7400 = colorsCount;
        colorsCount = r7400 + 1;
        key_chat_inLoaderSelected = r7400;
        int r7401 = colorsCount;
        colorsCount = r7401 + 1;
        key_chat_inLoaderPhoto = r7401;
        int r7402 = colorsCount;
        colorsCount = r7402 + 1;
        key_chat_mediaLoaderPhoto = r7402;
        int r7403 = colorsCount;
        colorsCount = r7403 + 1;
        key_chat_mediaLoaderPhotoSelected = r7403;
        int r7404 = colorsCount;
        colorsCount = r7404 + 1;
        key_chat_mediaLoaderPhotoIcon = r7404;
        int r7405 = colorsCount;
        colorsCount = r7405 + 1;
        key_chat_mediaLoaderPhotoIconSelected = r7405;
        int r7406 = colorsCount;
        colorsCount = r7406 + 1;
        key_chat_inLocationBackground = r7406;
        int r7407 = colorsCount;
        colorsCount = r7407 + 1;
        key_chat_inLocationIcon = r7407;
        int r7408 = colorsCount;
        colorsCount = r7408 + 1;
        key_chat_inContactBackground = r7408;
        int r7409 = colorsCount;
        colorsCount = r7409 + 1;
        key_chat_inContactIcon = r7409;
        int r7410 = colorsCount;
        colorsCount = r7410 + 1;
        key_chat_replyPanelIcons = r7410;
        int r7411 = colorsCount;
        colorsCount = r7411 + 1;
        key_chat_replyPanelClose = r7411;
        int r7412 = colorsCount;
        colorsCount = r7412 + 1;
        key_chat_replyPanelName = r7412;
        int r7413 = colorsCount;
        colorsCount = r7413 + 1;
        key_chat_replyPanelLine = r7413;
        int r7414 = colorsCount;
        colorsCount = r7414 + 1;
        key_chat_searchPanelIcons = r7414;
        int r7415 = colorsCount;
        colorsCount = r7415 + 1;
        key_chat_searchPanelText = r7415;
        int r7416 = colorsCount;
        colorsCount = r7416 + 1;
        key_chat_secretChatStatusText = r7416;
        int r7417 = colorsCount;
        colorsCount = r7417 + 1;
        key_chat_fieldOverlayText = r7417;
        int r7418 = colorsCount;
        colorsCount = r7418 + 1;
        key_chat_stickersHintPanel = r7418;
        int r7419 = colorsCount;
        colorsCount = r7419 + 1;
        key_chat_botSwitchToInlineText = r7419;
        int r7420 = colorsCount;
        colorsCount = r7420 + 1;
        key_chat_unreadMessagesStartArrowIcon = r7420;
        int r7421 = colorsCount;
        colorsCount = r7421 + 1;
        key_chat_unreadMessagesStartText = r7421;
        int r7422 = colorsCount;
        colorsCount = r7422 + 1;
        key_chat_unreadMessagesStartBackground = r7422;
        int r7423 = colorsCount;
        colorsCount = r7423 + 1;
        key_chat_inlineResultIcon = r7423;
        int r7424 = colorsCount;
        colorsCount = r7424 + 1;
        key_chat_emojiPanelBackground = r7424;
        int r7425 = colorsCount;
        colorsCount = r7425 + 1;
        key_chat_emojiSearchBackground = r7425;
        int r7426 = colorsCount;
        colorsCount = r7426 + 1;
        key_chat_emojiSearchIcon = r7426;
        int r7427 = colorsCount;
        colorsCount = r7427 + 1;
        key_chat_emojiPanelShadowLine = r7427;
        int r7428 = colorsCount;
        colorsCount = r7428 + 1;
        key_chat_emojiPanelEmptyText = r7428;
        int r7429 = colorsCount;
        colorsCount = r7429 + 1;
        key_chat_emojiPanelIcon = r7429;
        int r7430 = colorsCount;
        colorsCount = r7430 + 1;
        key_chat_emojiBottomPanelIcon = r7430;
        int r7431 = colorsCount;
        colorsCount = r7431 + 1;
        key_chat_emojiPanelIconSelected = r7431;
        int r7432 = colorsCount;
        colorsCount = r7432 + 1;
        key_chat_emojiPanelStickerPackSelector = r7432;
        int r7433 = colorsCount;
        colorsCount = r7433 + 1;
        key_chat_emojiPanelStickerPackSelectorLine = r7433;
        int r7434 = colorsCount;
        colorsCount = r7434 + 1;
        key_chat_emojiPanelBackspace = r7434;
        int r7435 = colorsCount;
        colorsCount = r7435 + 1;
        key_chat_emojiPanelTrendingTitle = r7435;
        int r7436 = colorsCount;
        colorsCount = r7436 + 1;
        key_chat_emojiPanelStickerSetName = r7436;
        int r7437 = colorsCount;
        colorsCount = r7437 + 1;
        key_chat_emojiPanelStickerSetNameHighlight = r7437;
        int r7438 = colorsCount;
        colorsCount = r7438 + 1;
        key_chat_emojiPanelStickerSetNameIcon = r7438;
        int r7439 = colorsCount;
        colorsCount = r7439 + 1;
        key_chat_emojiPanelTrendingDescription = r7439;
        int r7440 = colorsCount;
        colorsCount = r7440 + 1;
        key_chat_botKeyboardButtonText = r7440;
        int r7441 = colorsCount;
        colorsCount = r7441 + 1;
        key_chat_botKeyboardButtonBackground = r7441;
        int r7442 = colorsCount;
        colorsCount = r7442 + 1;
        key_chat_botKeyboardButtonBackgroundPressed = r7442;
        int r7443 = colorsCount;
        colorsCount = r7443 + 1;
        key_chat_emojiPanelNewTrending = r7443;
        int r7444 = colorsCount;
        colorsCount = r7444 + 1;
        key_chat_messagePanelVoicePressed = r7444;
        int r7445 = colorsCount;
        colorsCount = r7445 + 1;
        key_chat_messagePanelVoiceBackground = r7445;
        int r7446 = colorsCount;
        colorsCount = r7446 + 1;
        key_chat_messagePanelVoiceDelete = r7446;
        int r7447 = colorsCount;
        colorsCount = r7447 + 1;
        key_chat_messagePanelVoiceDuration = r7447;
        int r7448 = colorsCount;
        colorsCount = r7448 + 1;
        key_chat_recordedVoicePlayPause = r7448;
        int r7449 = colorsCount;
        colorsCount = r7449 + 1;
        key_chat_recordedVoiceProgress = r7449;
        int r7450 = colorsCount;
        colorsCount = r7450 + 1;
        key_chat_recordedVoiceProgressInner = r7450;
        int r7451 = colorsCount;
        colorsCount = r7451 + 1;
        key_chat_recordedVoiceDot = r7451;
        int r7452 = colorsCount;
        colorsCount = r7452 + 1;
        key_chat_recordedVoiceBackground = r7452;
        int r7453 = colorsCount;
        colorsCount = r7453 + 1;
        key_chat_recordedVoiceDarkerBackground = r7453;
        int r7454 = colorsCount;
        colorsCount = r7454 + 1;
        key_chat_recordVoiceCancel = r7454;
        int r7455 = colorsCount;
        colorsCount = r7455 + 1;
        key_chat_recordTime = r7455;
        int r7456 = colorsCount;
        colorsCount = r7456 + 1;
        key_chat_messagePanelCancelInlineBot = r7456;
        int r7457 = colorsCount;
        colorsCount = r7457 + 1;
        key_chat_gifSaveHintText = r7457;
        int r7458 = colorsCount;
        colorsCount = r7458 + 1;
        key_chat_gifSaveHintBackground = r7458;
        int r7459 = colorsCount;
        colorsCount = r7459 + 1;
        key_chat_goDownButton = r7459;
        int r7460 = colorsCount;
        colorsCount = r7460 + 1;
        key_chat_goDownButtonCounter = r7460;
        int r7461 = colorsCount;
        colorsCount = r7461 + 1;
        key_chat_goDownButtonCounterBackground = r7461;
        int r7462 = colorsCount;
        colorsCount = r7462 + 1;
        key_chat_inTextSelectionHighlight = r7462;
        int r7463 = colorsCount;
        colorsCount = r7463 + 1;
        key_chat_TextSelectionCursor = r7463;
        int r7464 = colorsCount;
        colorsCount = r7464 + 1;
        key_chat_inBubbleLocationPlaceholder = r7464;
        int r7465 = colorsCount;
        colorsCount = r7465 + 1;
        key_chat_BlurAlpha = r7465;
        int r7466 = colorsCount;
        colorsCount = r7466 + 1;
        key_chat_BlurAlphaSlow = r7466;
        int r7467 = colorsCount;
        colorsCount = r7467 + 1;
        key_chat_editMediaButton = r7467;
        int r7468 = colorsCount;
        colorsCount = r7468 + 1;
        key_chat_msgIvButtonDefaultIn = r7468;
        int r7469 = colorsCount;
        colorsCount = r7469 + 1;
        key_chat_msgIvButtonDefaultInPressed = r7469;
        int r7470 = colorsCount;
        colorsCount = r7470 + 1;
        key_chat_msgIvButtonDefaultInText = r7470;
        int r7471 = colorsCount;
        colorsCount = r7471 + 1;
        key_chat_msgIvButtonPrimaryIn = r7471;
        int r7472 = colorsCount;
        colorsCount = r7472 + 1;
        key_chat_msgIvButtonPrimaryInPressed = r7472;
        int r7473 = colorsCount;
        colorsCount = r7473 + 1;
        key_chat_msgIvButtonPrimaryInText = r7473;
        int r7474 = colorsCount;
        colorsCount = r7474 + 1;
        key_chat_msgIvButtonDangerIn = r7474;
        int r7475 = colorsCount;
        colorsCount = r7475 + 1;
        key_chat_msgIvButtonDangerInPressed = r7475;
        int r7476 = colorsCount;
        colorsCount = r7476 + 1;
        key_chat_msgIvButtonDangerInText = r7476;
        int r7477 = colorsCount;
        colorsCount = r7477 + 1;
        key_chat_msgIvButtonSuccessIn = r7477;
        int r7478 = colorsCount;
        colorsCount = r7478 + 1;
        key_chat_msgIvButtonSuccessInPressed = r7478;
        int r7479 = colorsCount;
        colorsCount = r7479 + 1;
        key_chat_msgIvButtonSuccessInText = r7479;
        int r7480 = colorsCount;
        colorsCount = r7480 + 1;
        key_chat_msgIvButtonDefaultInlineIn = r7480;
        int r7481 = colorsCount;
        colorsCount = r7481 + 1;
        key_chat_msgIvButtonDefaultInlineInPressed = r7481;
        int r7482 = colorsCount;
        colorsCount = r7482 + 1;
        key_chat_msgIvButtonDefaultInlineInText = r7482;
        int r7483 = colorsCount;
        colorsCount = r7483 + 1;
        key_chat_msgIvButtonDefaultOut = r7483;
        int r7484 = colorsCount;
        colorsCount = r7484 + 1;
        key_chat_msgIvButtonDefaultOutPressed = r7484;
        int r7485 = colorsCount;
        colorsCount = r7485 + 1;
        key_chat_msgIvButtonDefaultOutText = r7485;
        int r7486 = colorsCount;
        colorsCount = r7486 + 1;
        key_chat_msgIvButtonPrimaryOut = r7486;
        int r7487 = colorsCount;
        colorsCount = r7487 + 1;
        key_chat_msgIvButtonPrimaryOutPressed = r7487;
        int r7488 = colorsCount;
        colorsCount = r7488 + 1;
        key_chat_msgIvButtonPrimaryOutText = r7488;
        int r7489 = colorsCount;
        colorsCount = r7489 + 1;
        key_chat_msgIvButtonDangerOut = r7489;
        int r7490 = colorsCount;
        colorsCount = r7490 + 1;
        key_chat_msgIvButtonDangerOutPressed = r7490;
        int r7491 = colorsCount;
        colorsCount = r7491 + 1;
        key_chat_msgIvButtonDangerOutText = r7491;
        int r7492 = colorsCount;
        colorsCount = r7492 + 1;
        key_chat_msgIvButtonSuccessOut = r7492;
        int r7493 = colorsCount;
        colorsCount = r7493 + 1;
        key_chat_msgIvButtonSuccessOutPressed = r7493;
        int r7494 = colorsCount;
        colorsCount = r7494 + 1;
        key_chat_msgIvButtonSuccessOutText = r7494;
        int r7495 = colorsCount;
        colorsCount = r7495 + 1;
        key_chat_msgIvButtonDefaultInlineOut = r7495;
        int r7496 = colorsCount;
        colorsCount = r7496 + 1;
        key_chat_msgIvButtonDefaultInlineOutPressed = r7496;
        int r7497 = colorsCount;
        colorsCount = r7497 + 1;
        key_chat_msgIvButtonDefaultInlineOutText = r7497;
        int r7498 = colorsCount;
        colorsCount = r7498 + 1;
        key_voipgroup_listSelector = r7498;
        int r7499 = colorsCount;
        colorsCount = r7499 + 1;
        key_voipgroup_inviteMembersBackground = r7499;
        int r7500 = colorsCount;
        colorsCount = r7500 + 1;
        key_voipgroup_actionBar = r7500;
        int r7501 = colorsCount;
        colorsCount = r7501 + 1;
        key_voipgroup_actionBarItems = r7501;
        int r7502 = colorsCount;
        colorsCount = r7502 + 1;
        key_voipgroup_actionBarItemsSelector = r7502;
        int r7503 = colorsCount;
        colorsCount = r7503 + 1;
        key_voipgroup_actionBarUnscrolled = r7503;
        int r7504 = colorsCount;
        colorsCount = r7504 + 1;
        key_voipgroup_listViewBackgroundUnscrolled = r7504;
        int r7505 = colorsCount;
        colorsCount = r7505 + 1;
        key_voipgroup_lastSeenTextUnscrolled = r7505;
        int r7506 = colorsCount;
        colorsCount = r7506 + 1;
        key_voipgroup_mutedIconUnscrolled = r7506;
        int r7507 = colorsCount;
        colorsCount = r7507 + 1;
        key_voipgroup_nameText = r7507;
        int r7508 = colorsCount;
        colorsCount = r7508 + 1;
        key_voipgroup_lastSeenText = r7508;
        int r7509 = colorsCount;
        colorsCount = r7509 + 1;
        key_voipgroup_listeningText = r7509;
        int r7510 = colorsCount;
        colorsCount = r7510 + 1;
        key_voipgroup_speakingText = r7510;
        int r7511 = colorsCount;
        colorsCount = r7511 + 1;
        key_voipgroup_mutedIcon = r7511;
        int r7512 = colorsCount;
        colorsCount = r7512 + 1;
        key_voipgroup_mutedByAdminIcon = r7512;
        int r7513 = colorsCount;
        colorsCount = r7513 + 1;
        key_voipgroup_listViewBackground = r7513;
        int r7514 = colorsCount;
        colorsCount = r7514 + 1;
        key_voipgroup_dialogBackground = r7514;
        int r7515 = colorsCount;
        colorsCount = r7515 + 1;
        key_voipgroup_leaveCallMenu = r7515;
        int r7516 = colorsCount;
        colorsCount = r7516 + 1;
        key_voipgroup_checkMenu = r7516;
        int r7517 = colorsCount;
        colorsCount = r7517 + 1;
        key_voipgroup_soundButton = r7517;
        int r7518 = colorsCount;
        colorsCount = r7518 + 1;
        key_voipgroup_soundButtonActive = r7518;
        int r7519 = colorsCount;
        colorsCount = r7519 + 1;
        key_voipgroup_soundButtonActiveScrolled = r7519;
        int r7520 = colorsCount;
        colorsCount = r7520 + 1;
        key_voipgroup_soundButton2 = r7520;
        int r7521 = colorsCount;
        colorsCount = r7521 + 1;
        key_voipgroup_soundButtonActive2 = r7521;
        int r7522 = colorsCount;
        colorsCount = r7522 + 1;
        key_voipgroup_soundButtonActive2Scrolled = r7522;
        int r7523 = colorsCount;
        colorsCount = r7523 + 1;
        key_voipgroup_leaveButton = r7523;
        int r7524 = colorsCount;
        colorsCount = r7524 + 1;
        key_voipgroup_leaveButtonScrolled = r7524;
        int r7525 = colorsCount;
        colorsCount = r7525 + 1;
        key_voipgroup_muteButton = r7525;
        int r7526 = colorsCount;
        colorsCount = r7526 + 1;
        key_voipgroup_muteButton2 = r7526;
        int r7527 = colorsCount;
        colorsCount = r7527 + 1;
        key_voipgroup_muteButton3 = r7527;
        int r7528 = colorsCount;
        colorsCount = r7528 + 1;
        key_voipgroup_unmuteButton = r7528;
        int r7529 = colorsCount;
        colorsCount = r7529 + 1;
        key_voipgroup_unmuteButton2 = r7529;
        int r7530 = colorsCount;
        colorsCount = r7530 + 1;
        key_voipgroup_disabledButton = r7530;
        int r7531 = colorsCount;
        colorsCount = r7531 + 1;
        key_voipgroup_rtmpButton = r7531;
        int r7532 = colorsCount;
        colorsCount = r7532 + 1;
        key_voipgroup_disabledButtonActive = r7532;
        int r7533 = colorsCount;
        colorsCount = r7533 + 1;
        key_voipgroup_disabledButtonActiveScrolled = r7533;
        int r7534 = colorsCount;
        colorsCount = r7534 + 1;
        key_voipgroup_connectingProgress = r7534;
        int r7535 = colorsCount;
        colorsCount = r7535 + 1;
        key_voipgroup_scrollUp = r7535;
        int r7536 = colorsCount;
        colorsCount = r7536 + 1;
        key_voipgroup_searchPlaceholder = r7536;
        int r7537 = colorsCount;
        colorsCount = r7537 + 1;
        key_voipgroup_searchBackground = r7537;
        int r7538 = colorsCount;
        colorsCount = r7538 + 1;
        key_voipgroup_searchText = r7538;
        int r7539 = colorsCount;
        colorsCount = r7539 + 1;
        key_voipgroup_overlayGreen1 = r7539;
        int r7540 = colorsCount;
        colorsCount = r7540 + 1;
        key_voipgroup_overlayGreen2 = r7540;
        int r7541 = colorsCount;
        colorsCount = r7541 + 1;
        key_voipgroup_overlayBlue1 = r7541;
        int r7542 = colorsCount;
        colorsCount = r7542 + 1;
        key_voipgroup_overlayBlue2 = r7542;
        int r7543 = colorsCount;
        colorsCount = r7543 + 1;
        key_voipgroup_topPanelGreen1 = r7543;
        int r7544 = colorsCount;
        colorsCount = r7544 + 1;
        key_voipgroup_topPanelGreen2 = r7544;
        int r7545 = colorsCount;
        colorsCount = r7545 + 1;
        key_voipgroup_topPanelBlue1 = r7545;
        int r7546 = colorsCount;
        colorsCount = r7546 + 1;
        key_voipgroup_topPanelBlue2 = r7546;
        int r7547 = colorsCount;
        colorsCount = r7547 + 1;
        key_voipgroup_topPanelGray = r7547;
        int r7548 = colorsCount;
        colorsCount = r7548 + 1;
        key_voipgroup_overlayAlertGradientMuted = r7548;
        int r7549 = colorsCount;
        colorsCount = r7549 + 1;
        key_voipgroup_overlayAlertGradientMuted2 = r7549;
        int r7550 = colorsCount;
        colorsCount = r7550 + 1;
        key_voipgroup_overlayAlertGradientUnmuted = r7550;
        int r7551 = colorsCount;
        colorsCount = r7551 + 1;
        key_voipgroup_overlayAlertGradientUnmuted2 = r7551;
        int r7552 = colorsCount;
        colorsCount = r7552 + 1;
        key_voipgroup_overlayAlertMutedByAdmin = r7552;
        int r7553 = colorsCount;
        colorsCount = r7553 + 1;
        key_voipgroup_overlayAlertMutedByAdmin2 = r7553;
        int r7554 = colorsCount;
        colorsCount = r7554 + 1;
        key_voipgroup_mutedByAdminGradient = r7554;
        int r7555 = colorsCount;
        colorsCount = r7555 + 1;
        key_voipgroup_mutedByAdminGradient2 = r7555;
        int r7556 = colorsCount;
        colorsCount = r7556 + 1;
        key_voipgroup_mutedByAdminGradient3 = r7556;
        int r7557 = colorsCount;
        colorsCount = r7557 + 1;
        key_voipgroup_mutedByAdminMuteButton = r7557;
        int r7558 = colorsCount;
        colorsCount = r7558 + 1;
        key_voipgroup_mutedByAdminMuteButtonDisabled = r7558;
        int r7559 = colorsCount;
        colorsCount = r7559 + 1;
        key_voipgroup_windowBackgroundWhiteInputField = r7559;
        int r7560 = colorsCount;
        colorsCount = r7560 + 1;
        key_voipgroup_windowBackgroundWhiteInputFieldActivated = r7560;
        int r7561 = colorsCount;
        colorsCount = r7561 + 1;
        key_table_background = r7561;
        int r7562 = colorsCount;
        colorsCount = r7562 + 1;
        key_table_border = r7562;
        int r7563 = colorsCount;
        colorsCount = r7563 + 1;
        key_passport_authorizeBackground = r7563;
        int r7564 = colorsCount;
        colorsCount = r7564 + 1;
        key_passport_authorizeBackgroundSelected = r7564;
        int r7565 = colorsCount;
        colorsCount = r7565 + 1;
        key_passport_authorizeText = r7565;
        int r7566 = colorsCount;
        colorsCount = r7566 + 1;
        key_profile_creatorIcon = r7566;
        int r7567 = colorsCount;
        colorsCount = r7567 + 1;
        key_profile_title = r7567;
        int r7568 = colorsCount;
        colorsCount = r7568 + 1;
        key_profile_actionIcon = r7568;
        int r7569 = colorsCount;
        colorsCount = r7569 + 1;
        key_profile_actionBackground = r7569;
        int r7570 = colorsCount;
        colorsCount = r7570 + 1;
        key_profile_actionPressedBackground = r7570;
        int r7571 = colorsCount;
        colorsCount = r7571 + 1;
        key_profile_verifiedBackground = r7571;
        int r7572 = colorsCount;
        colorsCount = r7572 + 1;
        key_profile_verifiedCheck = r7572;
        int r7573 = colorsCount;
        colorsCount = r7573 + 1;
        key_profile_status = r7573;
        int r7574 = colorsCount;
        colorsCount = r7574 + 1;
        key_chat_tagAdmin = r7574;
        int r7575 = colorsCount;
        colorsCount = r7575 + 1;
        key_chat_tagCreator = r7575;
        int r7576 = colorsCount;
        colorsCount = r7576 + 1;
        key_profile_tabText = r7576;
        int r7577 = colorsCount;
        colorsCount = r7577 + 1;
        key_profile_tabSelectedText = r7577;
        int r7578 = colorsCount;
        colorsCount = r7578 + 1;
        key_profile_tabSelectedLine = r7578;
        int r7579 = colorsCount;
        colorsCount = r7579 + 1;
        key_profile_tabSelector = r7579;
        int r7580 = colorsCount;
        colorsCount = r7580 + 1;
        key_sharedMedia_startStopLoadIcon = r7580;
        int r7581 = colorsCount;
        colorsCount = r7581 + 1;
        key_sharedMedia_linkPlaceholder = r7581;
        int r7582 = colorsCount;
        colorsCount = r7582 + 1;
        key_sharedMedia_linkPlaceholderText = r7582;
        int r7583 = colorsCount;
        colorsCount = r7583 + 1;
        key_sharedMedia_photoPlaceholder = r7583;
        int r7584 = colorsCount;
        colorsCount = r7584 + 1;
        key_featuredStickers_addedIcon = r7584;
        int r7585 = colorsCount;
        colorsCount = r7585 + 1;
        key_featuredStickers_buttonProgress = r7585;
        int r7586 = colorsCount;
        colorsCount = r7586 + 1;
        key_featuredStickers_addButton = r7586;
        int r7587 = colorsCount;
        colorsCount = r7587 + 1;
        key_featuredStickers_addButton2 = r7587;
        int r7588 = colorsCount;
        colorsCount = r7588 + 1;
        key_featuredStickers_addButtonPressed = r7588;
        int r7589 = colorsCount;
        colorsCount = r7589 + 1;
        key_featuredStickers_removeButtonText = r7589;
        int r7590 = colorsCount;
        colorsCount = r7590 + 1;
        key_featuredStickers_buttonText = r7590;
        int r7591 = colorsCount;
        colorsCount = r7591 + 1;
        key_featuredStickers_unread = r7591;
        int r7592 = colorsCount;
        colorsCount = r7592 + 1;
        key_stickers_menu = r7592;
        int r7593 = colorsCount;
        colorsCount = r7593 + 1;
        key_stickers_menuSelector = r7593;
        int r7594 = colorsCount;
        colorsCount = r7594 + 1;
        key_changephoneinfo_image2 = r7594;
        int r7595 = colorsCount;
        colorsCount = r7595 + 1;
        key_groupcreate_hintText = r7595;
        int r7596 = colorsCount;
        colorsCount = r7596 + 1;
        key_groupcreate_cursor = r7596;
        int r7597 = colorsCount;
        colorsCount = r7597 + 1;
        key_groupcreate_sectionShadow = r7597;
        int r7598 = colorsCount;
        colorsCount = r7598 + 1;
        key_groupcreate_sectionText = r7598;
        int r7599 = colorsCount;
        colorsCount = r7599 + 1;
        key_groupcreate_spanText = r7599;
        int r7600 = colorsCount;
        colorsCount = r7600 + 1;
        key_groupcreate_spanBackground = r7600;
        int r7601 = colorsCount;
        colorsCount = r7601 + 1;
        key_groupcreate_spanDelete = r7601;
        int r7602 = colorsCount;
        colorsCount = r7602 + 1;
        key_contacts_inviteBackground = r7602;
        int r7603 = colorsCount;
        colorsCount = r7603 + 1;
        key_contacts_inviteText = r7603;
        int r7604 = colorsCount;
        colorsCount = r7604 + 1;
        key_login_progressInner = r7604;
        int r7605 = colorsCount;
        colorsCount = r7605 + 1;
        key_login_progressOuter = r7605;
        int r7606 = colorsCount;
        colorsCount = r7606 + 1;
        key_picker_enabledButton = r7606;
        int r7607 = colorsCount;
        colorsCount = r7607 + 1;
        key_picker_disabledButton = r7607;
        int r7608 = colorsCount;
        colorsCount = r7608 + 1;
        key_picker_badge = r7608;
        int r7609 = colorsCount;
        colorsCount = r7609 + 1;
        key_picker_badgeText = r7609;
        int r7610 = colorsCount;
        colorsCount = r7610 + 1;
        key_location_sendLocationBackground = r7610;
        int r7611 = colorsCount;
        colorsCount = r7611 + 1;
        key_location_sendLocationIcon = r7611;
        int r7612 = colorsCount;
        colorsCount = r7612 + 1;
        key_location_sendLocationText = r7612;
        int r7613 = colorsCount;
        colorsCount = r7613 + 1;
        key_location_sendLiveLocationBackground = r7613;
        int r7614 = colorsCount;
        colorsCount = r7614 + 1;
        key_location_sendLiveLocationIcon = r7614;
        int r7615 = colorsCount;
        colorsCount = r7615 + 1;
        key_location_sendLiveLocationText = r7615;
        int r7616 = colorsCount;
        colorsCount = r7616 + 1;
        key_location_liveLocationProgress = r7616;
        int r7617 = colorsCount;
        colorsCount = r7617 + 1;
        key_location_placeLocationBackground = r7617;
        int r7618 = colorsCount;
        colorsCount = r7618 + 1;
        key_location_actionIcon = r7618;
        int r7619 = colorsCount;
        colorsCount = r7619 + 1;
        key_location_actionActiveIcon = r7619;
        int r7620 = colorsCount;
        colorsCount = r7620 + 1;
        key_location_actionBackground = r7620;
        int r7621 = colorsCount;
        colorsCount = r7621 + 1;
        key_location_actionPressedBackground = r7621;
        int r7622 = colorsCount;
        colorsCount = r7622 + 1;
        key_dialog_liveLocationProgress = r7622;
        int r7623 = colorsCount;
        colorsCount = r7623 + 1;
        key_files_folderIcon = r7623;
        int r7624 = colorsCount;
        colorsCount = r7624 + 1;
        key_files_folderIconBackground = r7624;
        int r7625 = colorsCount;
        colorsCount = r7625 + 1;
        key_files_iconText = r7625;
        int r7626 = colorsCount;
        colorsCount = r7626 + 1;
        key_sessions_devicesImage = r7626;
        int r7627 = colorsCount;
        colorsCount = r7627 + 1;
        key_calls_callReceivedGreenIcon = r7627;
        int r7628 = colorsCount;
        colorsCount = r7628 + 1;
        key_calls_callReceivedRedIcon = r7628;
        int r7629 = colorsCount;
        colorsCount = r7629 + 1;
        key_undo_background = r7629;
        int r7630 = colorsCount;
        colorsCount = r7630 + 1;
        key_undo_cancelColor = r7630;
        int r7631 = colorsCount;
        colorsCount = r7631 + 1;
        key_undo_infoColor = r7631;
        int r7632 = colorsCount;
        colorsCount = r7632 + 1;
        key_sheet_scrollUp = r7632;
        int r7633 = colorsCount;
        colorsCount = r7633 + 1;
        key_sheet_other = r7633;
        int r7634 = colorsCount;
        colorsCount = r7634 + 1;
        key_bot_loadingIcon = r7634;
        int r7635 = colorsCount;
        colorsCount = r7635 + 1;
        key_gift_ribbon = r7635;
        int r7636 = colorsCount;
        colorsCount = r7636 + 1;
        key_gift_ribbon_soldout = r7636;
        int r7637 = colorsCount;
        colorsCount = r7637 + 1;
        key_player_actionBarSelector = r7637;
        int r7638 = colorsCount;
        colorsCount = r7638 + 1;
        key_player_actionBarTitle = r7638;
        int r7639 = colorsCount;
        colorsCount = r7639 + 1;
        key_player_actionBarSubtitle = r7639;
        int r7640 = colorsCount;
        colorsCount = r7640 + 1;
        key_player_actionBarItems = r7640;
        int r7641 = colorsCount;
        colorsCount = r7641 + 1;
        key_player_background = r7641;
        int r7642 = colorsCount;
        colorsCount = r7642 + 1;
        key_player_time = r7642;
        int r7643 = colorsCount;
        colorsCount = r7643 + 1;
        key_player_progressBackground = r7643;
        int r7644 = colorsCount;
        colorsCount = r7644 + 1;
        key_player_progressCachedBackground = r7644;
        int r7645 = colorsCount;
        colorsCount = r7645 + 1;
        key_player_progress = r7645;
        int r7646 = colorsCount;
        colorsCount = r7646 + 1;
        key_player_button = r7646;
        int r7647 = colorsCount;
        colorsCount = r7647 + 1;
        key_player_buttonActive = r7647;
        int r7648 = colorsCount;
        colorsCount = r7648 + 1;
        key_statisticChartSignature = r7648;
        int r7649 = colorsCount;
        colorsCount = r7649 + 1;
        key_statisticChartSignatureAlpha = r7649;
        int r7650 = colorsCount;
        colorsCount = r7650 + 1;
        key_statisticChartHintLine = r7650;
        int r7651 = colorsCount;
        colorsCount = r7651 + 1;
        key_statisticChartActiveLine = r7651;
        int r7652 = colorsCount;
        colorsCount = r7652 + 1;
        key_statisticChartInactivePickerChart = r7652;
        int r7653 = colorsCount;
        colorsCount = r7653 + 1;
        key_statisticChartActivePickerChart = r7653;
        int r7654 = colorsCount;
        colorsCount = r7654 + 1;
        key_statisticChartRipple = r7654;
        int r7655 = colorsCount;
        colorsCount = r7655 + 1;
        key_statisticChartBackZoomColor = r7655;
        int r7656 = colorsCount;
        colorsCount = r7656 + 1;
        key_statisticChartChevronColor = r7656;
        int r7657 = colorsCount;
        colorsCount = r7657 + 1;
        key_statisticChartLine_blue = r7657;
        int r7658 = colorsCount;
        colorsCount = r7658 + 1;
        key_statisticChartLine_green = r7658;
        int r7659 = colorsCount;
        colorsCount = r7659 + 1;
        key_statisticChartLine_red = r7659;
        int r7660 = colorsCount;
        colorsCount = r7660 + 1;
        key_statisticChartLine_golden = r7660;
        int r7661 = colorsCount;
        colorsCount = r7661 + 1;
        key_statisticChartLine_lightblue = r7661;
        int r7662 = colorsCount;
        colorsCount = r7662 + 1;
        key_statisticChartLine_lightgreen = r7662;
        int r7663 = colorsCount;
        colorsCount = r7663 + 1;
        key_statisticChartLine_orange = r7663;
        int r7664 = colorsCount;
        colorsCount = r7664 + 1;
        key_statisticChartLine_indigo = r7664;
        int r7665 = colorsCount;
        colorsCount = r7665 + 1;
        key_statisticChartLine_purple = r7665;
        int r7666 = colorsCount;
        colorsCount = r7666 + 1;
        key_statisticChartLine_cyan = r7666;
        int r7667 = colorsCount;
        colorsCount = r7667 + 1;
        key_statisticChartLineEmpty = r7667;
        int r92 = colorsCount;
        colorsCount = r92 + 1;
        key_color_lightblue = r92;
        int r102 = colorsCount;
        colorsCount = r102 + 1;
        key_color_blue = r102;
        int r112 = colorsCount;
        colorsCount = r112 + 1;
        key_color_green = r112;
        int r122 = colorsCount;
        colorsCount = r122 + 1;
        key_color_lightgreen = r122;
        int r132 = colorsCount;
        colorsCount = r132 + 1;
        key_color_red = r132;
        int r142 = colorsCount;
        colorsCount = r142 + 1;
        key_color_orange = r142;
        int r152 = colorsCount;
        colorsCount = r152 + 1;
        key_color_yellow = r152;
        int r162 = colorsCount;
        colorsCount = r162 + 1;
        key_color_purple = r162;
        int r172 = colorsCount;
        colorsCount = r172 + 1;
        key_color_cyan = r172;
        keys_colors = new int[]{r92, r102, r112, r122, r132, r142, r152, r162, r172};
        int r7668 = colorsCount;
        colorsCount = r7668 + 1;
        key_chat_inReactionButtonBackground = r7668;
        int r7669 = colorsCount;
        colorsCount = r7669 + 1;
        key_chat_outReactionButtonText = r7669;
        int r7670 = colorsCount;
        colorsCount = r7670 + 1;
        key_chat_inReactionButtonText = r7670;
        int r7671 = colorsCount;
        colorsCount = r7671 + 1;
        key_chat_inReactionButtonTextSelected = r7671;
        int r7672 = colorsCount;
        colorsCount = r7672 + 1;
        key_chat_outReactionButtonTextSelected = r7672;
        int r7673 = colorsCount;
        colorsCount = r7673 + 1;
        key_chat_reactionServiceButtonBackgroundSelected = r7673;
        int r7674 = colorsCount;
        colorsCount = r7674 + 1;
        key_chat_reactionServiceButtonTextSelected = r7674;
        int r7675 = colorsCount;
        colorsCount = r7675 + 1;
        key_reactionStarSelector = r7675;
        int r7676 = colorsCount;
        colorsCount = r7676 + 1;
        key_premiumGradient0 = r7676;
        int r7677 = colorsCount;
        colorsCount = r7677 + 1;
        key_premiumGradient1 = r7677;
        int r7678 = colorsCount;
        colorsCount = r7678 + 1;
        key_premiumGradient2 = r7678;
        int r7679 = colorsCount;
        colorsCount = r7679 + 1;
        key_premiumGradient3 = r7679;
        int r7680 = colorsCount;
        colorsCount = r7680 + 1;
        key_premiumGradient4 = r7680;
        int r7681 = colorsCount;
        colorsCount = r7681 + 1;
        key_premiumGradientBackground1 = r7681;
        int r7682 = colorsCount;
        colorsCount = r7682 + 1;
        key_premiumGradientBackground2 = r7682;
        int r7683 = colorsCount;
        colorsCount = r7683 + 1;
        key_premiumGradientBackground3 = r7683;
        int r7684 = colorsCount;
        colorsCount = r7684 + 1;
        key_premiumGradientBackground4 = r7684;
        int r7685 = colorsCount;
        colorsCount = r7685 + 1;
        key_premiumGradientBackgroundOverlay = r7685;
        int r7686 = colorsCount;
        colorsCount = r7686 + 1;
        key_premiumStartSmallStarsColor = r7686;
        int r7687 = colorsCount;
        colorsCount = r7687 + 1;
        key_premiumStarGradient1 = r7687;
        int r7688 = colorsCount;
        colorsCount = r7688 + 1;
        key_premiumStarGradient2 = r7688;
        int r7689 = colorsCount;
        colorsCount = r7689 + 1;
        key_premiumCoinGradient1 = r7689;
        int r7690 = colorsCount;
        colorsCount = r7690 + 1;
        key_premiumCoinGradient2 = r7690;
        int r7691 = colorsCount;
        colorsCount = r7691 + 1;
        key_premiumStartSmallStarsColor2 = r7691;
        int r7692 = colorsCount;
        colorsCount = r7692 + 1;
        key_premiumGradientBottomSheet1 = r7692;
        int r7693 = colorsCount;
        colorsCount = r7693 + 1;
        key_premiumGradientBottomSheet2 = r7693;
        int r7694 = colorsCount;
        colorsCount = r7694 + 1;
        key_premiumGradientBottomSheet3 = r7694;
        int r7695 = colorsCount;
        colorsCount = r7695 + 1;
        key_topics_unreadCounter = r7695;
        int r7696 = colorsCount;
        colorsCount = r7696 + 1;
        key_topics_unreadCounterMuted = r7696;
        int r7697 = colorsCount;
        colorsCount = r7697 + 1;
        key_starsGradient1 = r7697;
        int r7698 = colorsCount;
        colorsCount = r7698 + 1;
        key_starsGradient2 = r7698;
        int r7699 = colorsCount;
        colorsCount = r7699 + 1;
        key_stories_circle1 = r7699;
        int r7700 = colorsCount;
        colorsCount = r7700 + 1;
        key_stories_circle2 = r7700;
        int r7701 = colorsCount;
        colorsCount = r7701 + 1;
        key_stories_circle_dialog1 = r7701;
        int r7702 = colorsCount;
        colorsCount = r7702 + 1;
        key_stories_circle_dialog2 = r7702;
        int r7703 = colorsCount;
        colorsCount = r7703 + 1;
        key_stories_circle_closeFriends1 = r7703;
        int r7704 = colorsCount;
        colorsCount = r7704 + 1;
        key_stories_circle_closeFriends2 = r7704;
        int r7705 = colorsCount;
        colorsCount = r7705 + 1;
        key_stories_circle_live1 = r7705;
        int r7706 = colorsCount;
        colorsCount = r7706 + 1;
        key_stories_circle_live2 = r7706;
        int r7707 = colorsCount;
        colorsCount = r7707 + 1;
        key_chat_inCodeBackground = r7707;
        int r7708 = colorsCount;
        colorsCount = r7708 + 1;
        key_chat_outCodeBackground = r7708;
        int r7709 = colorsCount;
        colorsCount = r7709 + 1;
        key_chat_inTableBackground = r7709;
        int r7710 = colorsCount;
        colorsCount = r7710 + 1;
        key_chat_outTableBackground = r7710;
        int r7711 = colorsCount;
        colorsCount = r7711 + 1;
        key_chat_inTableBorder = r7711;
        int r7712 = colorsCount;
        colorsCount = r7712 + 1;
        key_chat_outTableBorder = r7712;
        int r7713 = colorsCount;
        colorsCount = r7713 + 1;
        key_chat_inDivider = r7713;
        int r7714 = colorsCount;
        colorsCount = r7714 + 1;
        key_chat_outDivider = r7714;
        int r8 = colorsCount;
        colorsCount = r8 + 1;
        key_chat_inArticleCodeBackground = r8;
        int r82 = colorsCount;
        colorsCount = r82 + 1;
        key_chat_outArticleCodeBackground = r82;
        int r83 = colorsCount;
        colorsCount = r83 + 1;
        key_chat_inArticleCodeScrollbarBackground = r83;
        int r84 = colorsCount;
        colorsCount = r84 + 1;
        key_chat_inArticleCodeScrollbar = r84;
        int r85 = colorsCount;
        colorsCount = r85 + 1;
        key_chat_outArticleCodeScrollbarBackground = r85;
        int r86 = colorsCount;
        colorsCount = r86 + 1;
        key_chat_outArticleCodeScrollbar = r86;
        int r87 = colorsCount;
        colorsCount = r87 + 1;
        key_chat_inArticleDetailsArrow = r87;
        int r88 = colorsCount;
        colorsCount = r88 + 1;
        key_chat_outArticleDetailsArrow = r88;
        int r89 = colorsCount;
        colorsCount = r89 + 1;
        key_chat_inArticleDetailsLine = r89;
        int r810 = colorsCount;
        colorsCount = r810 + 1;
        key_chat_outArticleDetailsLine = r810;
        myMessagesAccentExtraKeys = new int[]{r7714};
        int r7715 = colorsCount;
        colorsCount = r7715 + 1;
        key_code_keyword = r7715;
        int r7716 = colorsCount;
        colorsCount = r7716 + 1;
        key_code_operator = r7716;
        int r7717 = colorsCount;
        colorsCount = r7717 + 1;
        key_code_constant = r7717;
        int r7718 = colorsCount;
        colorsCount = r7718 + 1;
        key_code_string = r7718;
        int r7719 = colorsCount;
        colorsCount = r7719 + 1;
        key_code_number = r7719;
        int r7720 = colorsCount;
        colorsCount = r7720 + 1;
        key_code_comment = r7720;
        int r7721 = colorsCount;
        colorsCount = r7721 + 1;
        key_code_function = r7721;
        int r7722 = colorsCount;
        colorsCount = r7722 + 1;
        key_iv_background = r7722;
        int r811 = colorsCount;
        colorsCount = r811 + 1;
        key_iv_backgroundGray = r811;
        int r93 = colorsCount;
        colorsCount = r93 + 1;
        key_iv_ab_progress = r93;
        int r103 = colorsCount;
        colorsCount = r103 + 1;
        key_iv_navigationBackground = r103;
        int r113 = colorsCount;
        colorsCount = r113 + 1;
        key_share_linkText = r113;
        int r114 = colorsCount;
        colorsCount = r114 + 1;
        key_share_linkBackground = r114;
        int r115 = colorsCount;
        colorsCount = r115 + 1;
        key_share_icon = r115;
        int r116 = colorsCount;
        colorsCount = r116 + 1;
        key_glass_defaultIcon = r116;
        int r117 = colorsCount;
        colorsCount = r117 + 1;
        key_glass_defaultText = r117;
        int r118 = colorsCount;
        colorsCount = r118 + 1;
        key_glass_targetMainTabs = r118;
        int r119 = colorsCount;
        colorsCount = r119 + 1;
        key_glass_targetMainTopPanel = r119;
        int r1110 = colorsCount;
        colorsCount = r1110 + 1;
        key_glass_tabSelected = r1110;
        int r1111 = colorsCount;
        colorsCount = r1111 + 1;
        key_glass_tabSelectedText = r1111;
        int r1112 = colorsCount;
        colorsCount = r1112 + 1;
        key_glass_tabUnselected = r1112;
        int r1113 = colorsCount;
        colorsCount = r1113 + 1;
        key_botKeyboard_button_primary = r1113;
        int r1114 = colorsCount;
        colorsCount = r1114 + 1;
        key_botKeyboard_button_danger = r1114;
        int r1115 = colorsCount;
        colorsCount = r1115 + 1;
        key_botKeyboard_button_success = r1115;
        int r1116 = colorsCount;
        colorsCount = r1116 + 1;
        key_telegram_color_dialogsLogo = r1116;
        int r1117 = colorsCount;
        colorsCount = r1117 + 1;
        key_telegram_color = r1117;
        int r1118 = colorsCount;
        colorsCount = r1118 + 1;
        key_telegram_color_text = r1118;
        defaultChatDrawables = new HashMap();
        defaultChatDrawableColorKeys = new HashMap();
        defaultChatPaints = new HashMap();
        defaultChatPaintColors = new HashMap();
        fallbackKeys = new SparseIntArray();
        themeAccentExclusionKeys = new HashSet();
        hsvTemp1Local = new ThreadLocal();
        hsvTemp2Local = new ThreadLocal();
        hsvTemp3Local = new ThreadLocal();
        hsvTemp4Local = new ThreadLocal();
        hsvTemp5Local = new ThreadLocal();
        defaultColors = ThemeColors.createDefaultColors();
        fallbackKeys.put(r7722, key_windowBackgroundWhite);
        SparseIntArray r7723 = fallbackKeys;
        int r1119 = key_windowBackgroundGray;
        r7723.put(r811, r1119);
        fallbackKeys.put(r103, r1119);
        fallbackKeys.put(key_bot_loadingIcon, key_groupcreate_spanBackground);
        fallbackKeys.put(key_gift_ribbon_soldout, key_text_RedBold);
        SparseIntArray r7724 = fallbackKeys;
        int r812 = key_featuredStickers_addButton;
        r7724.put(r93, r812);
        fallbackKeys.put(key_featuredStickers_addButton2, r812);
        fallbackKeys.put(key_dialogGiftsBackground, r1119);
        SparseIntArray r7725 = fallbackKeys;
        int r94 = key_dialogGiftsTabText;
        int r104 = key_windowBackgroundWhiteGrayText2;
        r7725.put(r94, r104);
        fallbackKeys.put(key_chat_inQuote, key_featuredStickers_addButtonPressed);
        SparseIntArray r7726 = fallbackKeys;
        int r95 = key_chat_outQuote;
        int r123 = key_chat_outReplyLine;
        r7726.put(r95, r123);
        fallbackKeys.put(key_chat_outReplyLine2, r123);
        fallbackKeys.put(key_chat_inAdminText, key_chat_inTimeText);
        fallbackKeys.put(key_chat_inAdminSelectedText, key_chat_inTimeSelectedText);
        fallbackKeys.put(key_player_progressCachedBackground, key_player_progressBackground);
        fallbackKeys.put(key_chat_inAudioCacheSeekbar, key_chat_inAudioSeekbar);
        fallbackKeys.put(key_chat_outAudioCacheSeekbar, key_chat_outAudioSeekbar);
        fallbackKeys.put(key_chat_emojiSearchBackground, key_chat_emojiPanelStickerPackSelector);
        fallbackKeys.put(key_location_sendLiveLocationIcon, key_location_sendLocationIcon);
        fallbackKeys.put(key_changephoneinfo_image2, r812);
        fallbackKeys.put(key_graySectionText, r104);
        fallbackKeys.put(key_chat_inMediaIcon, key_chat_inBubble);
        fallbackKeys.put(key_chat_outMediaIcon, key_chat_outBubble);
        fallbackKeys.put(key_chat_inMediaIconSelected, key_chat_inBubbleSelected);
        fallbackKeys.put(key_chat_outMediaIconSelected, key_chat_outBubbleSelected);
        fallbackKeys.put(key_dialog_inlineProgressBackground, r1119);
        fallbackKeys.put(key_dialog_inlineProgress, key_chats_menuItemIcon);
        fallbackKeys.put(key_groupcreate_spanDelete, key_chats_actionIcon);
        fallbackKeys.put(key_sharedMedia_photoPlaceholder, r1119);
        fallbackKeys.put(key_chat_attachPollBackground, key_chat_attachAudioBackground);
        fallbackKeys.put(key_chats_onlineCircle, key_windowBackgroundWhiteBlueText);
        SparseIntArray r7727 = fallbackKeys;
        int r813 = key_windowBackgroundWhiteBlueButton;
        int r96 = key_windowBackgroundWhiteValueText;
        r7727.put(r813, r96);
        fallbackKeys.put(key_windowBackgroundWhiteBlueIcon, r96);
        fallbackKeys.put(key_undo_background, key_chat_gifSaveHintBackground);
        SparseIntArray r7728 = fallbackKeys;
        int r814 = key_undo_cancelColor;
        int r97 = key_chat_gifSaveHintText;
        r7728.put(r814, r97);
        fallbackKeys.put(key_undo_infoColor, r97);
        SparseIntArray r7729 = fallbackKeys;
        int r815 = key_windowBackgroundUnchecked;
        int r98 = key_windowBackgroundWhite;
        r7729.put(r815, r98);
        fallbackKeys.put(key_windowBackgroundChecked, r98);
        fallbackKeys.put(key_switchTrackBlue, key_switchTrack);
        fallbackKeys.put(key_switchTrackBlueChecked, key_switchTrackChecked);
        fallbackKeys.put(key_switchTrackBlueThumb, r98);
        fallbackKeys.put(key_switchTrackBlueThumbChecked, r98);
        fallbackKeys.put(key_windowBackgroundCheckText, r98);
        fallbackKeys.put(key_contextProgressInner4, key_contextProgressInner1);
        fallbackKeys.put(key_contextProgressOuter4, key_contextProgressOuter1);
        SparseIntArray r7730 = fallbackKeys;
        int r816 = key_switchTrackBlueSelector;
        int r99 = key_listSelector;
        r7730.put(r816, r99);
        fallbackKeys.put(key_switchTrackBlueSelectorChecked, r99);
        fallbackKeys.put(key_settings_listSelector, r99);
        SparseIntArray r7731 = fallbackKeys;
        int r817 = key_chat_emojiBottomPanelIcon;
        int r910 = key_chat_emojiPanelIcon;
        r7731.put(r817, r910);
        fallbackKeys.put(key_chat_emojiSearchIcon, r910);
        fallbackKeys.put(key_chat_emojiPanelStickerSetNameHighlight, key_windowBackgroundWhiteBlueText4);
        fallbackKeys.put(key_chat_emojiPanelStickerPackSelectorLine, key_chat_emojiPanelIconSelected);
        SparseIntArray r7732 = fallbackKeys;
        int r818 = key_sheet_scrollUp;
        int r105 = key_chat_emojiPanelStickerPackSelector;
        r7732.put(r818, r105);
        fallbackKeys.put(key_sheet_other, key_player_actionBarItems);
        fallbackKeys.put(key_dialogSearchBackground, r105);
        fallbackKeys.put(key_dialogSearchHint, r910);
        fallbackKeys.put(key_dialogSearchIcon, r910);
        SparseIntArray r7733 = fallbackKeys;
        int r819 = key_dialogSearchText;
        int r911 = key_windowBackgroundWhiteBlackText;
        r7733.put(r819, r911);
        fallbackKeys.put(key_buttonNeutral, key_chat_replyPanelLine);
        fallbackKeys.put(key_buttonNeutralText, r911);
        SparseIntArray r7734 = fallbackKeys;
        int r820 = key_dialogFloatingButton;
        int r106 = key_dialogRoundCheckBox;
        r7734.put(r820, r106);
        fallbackKeys.put(key_dialogFloatingButtonPressed, r106);
        fallbackKeys.put(key_dialogFloatingIcon, key_dialogRoundCheckBoxCheck);
        fallbackKeys.put(key_dialogShadowLine, key_chat_emojiPanelShadowLine);
        fallbackKeys.put(key_actionBarDefaultArchived, key_actionBarDefault);
        SparseIntArray r7735 = fallbackKeys;
        int r821 = key_actionBarDefaultArchivedSelector;
        int r107 = key_actionBarDefaultSelector;
        r7735.put(r821, r107);
        fallbackKeys.put(key_actionBarDefaultArchivedIcon, key_actionBarDefaultIcon);
        fallbackKeys.put(key_actionBarDefaultArchivedTitle, r911);
        fallbackKeys.put(key_actionBarDefaultArchivedSearch, key_actionBarDefaultSearch);
        fallbackKeys.put(key_actionBarDefaultArchivedSearchPlaceholder, key_actionBarDefaultSearchPlaceholder);
        SparseIntArray r7736 = fallbackKeys;
        int r822 = key_chats_message_threeLines;
        int r912 = key_chats_message;
        r7736.put(r822, r912);
        SparseIntArray r7737 = fallbackKeys;
        int r823 = key_chats_nameMessage_threeLines;
        int r1120 = key_chats_nameMessage;
        r7737.put(r823, r1120);
        fallbackKeys.put(key_chats_nameArchived, key_chats_name);
        fallbackKeys.put(key_chats_nameMessageArchived, r1120);
        fallbackKeys.put(key_chats_nameMessageArchived_threeLines, r1120);
        fallbackKeys.put(key_chats_messageArchived, r912);
        SparseIntArray r7738 = fallbackKeys;
        int r824 = key_avatar_backgroundArchived;
        int r913 = key_chats_unreadCounterMuted;
        r7738.put(r824, r913);
        SparseIntArray r7739 = fallbackKeys;
        int r825 = key_chats_archiveBackground;
        int r1121 = key_chats_actionBackground;
        r7739.put(r825, r1121);
        fallbackKeys.put(key_chats_archivePinBackground, r913);
        SparseIntArray r7740 = fallbackKeys;
        int r826 = key_chats_archiveIcon;
        int r124 = key_chats_actionIcon;
        r7740.put(r826, r124);
        fallbackKeys.put(key_chats_archiveText, r124);
        fallbackKeys.put(key_actionBarDefaultSubmenuItemIcon, key_dialogIcon);
        fallbackKeys.put(key_checkboxDisabled, r913);
        SparseIntArray r7741 = fallbackKeys;
        int r827 = key_chat_status;
        int r125 = key_actionBarDefaultSubtitle;
        r7741.put(r827, r125);
        SparseIntArray r7742 = fallbackKeys;
        int r828 = key_chat_inGreenCall;
        int r133 = key_calls_callReceivedGreenIcon;
        r7742.put(r828, r133);
        fallbackKeys.put(key_chat_outGreenCall, r133);
        SparseIntArray r7743 = fallbackKeys;
        int r829 = key_actionBarTabActiveText;
        int r134 = key_actionBarDefaultTitle;
        r7743.put(r829, r134);
        fallbackKeys.put(key_actionBarTabUnactiveText, r125);
        fallbackKeys.put(key_actionBarTabLine, r134);
        fallbackKeys.put(key_actionBarTabSelector, r107);
        fallbackKeys.put(key_profile_status, key_avatar_subtitleInProfileBlue);
        fallbackKeys.put(key_chats_menuTopBackgroundCats, key_avatar_backgroundActionBarBlue);
        fallbackKeys.put(key_chat_outLinkSelectBackground, key_chat_linkSelectBackground);
        fallbackKeys.put(key_actionBarDefaultSubmenuSeparator, key_windowBackgroundGray);
        SparseIntArray r7744 = fallbackKeys;
        int r830 = key_chat_attachPermissionImage;
        int r108 = key_dialogTextBlack;
        r7744.put(r830, r108);
        fallbackKeys.put(key_chat_attachPermissionMark, key_chat_sentError);
        fallbackKeys.put(key_chat_attachPermissionText, r108);
        fallbackKeys.put(key_chat_attachEmptyImage, key_emptyListPlaceholder);
        fallbackKeys.put(key_actionBarBrowser, key_actionBarDefault);
        fallbackKeys.put(key_chats_sentReadCheck, key_chats_sentCheck);
        fallbackKeys.put(key_chat_outSentCheckRead, key_chat_outSentCheck);
        fallbackKeys.put(key_chat_outSentCheckReadSelected, key_chat_outSentCheckSelected);
        fallbackKeys.put(key_chats_archivePullDownBackground, r913);
        fallbackKeys.put(key_chats_archivePullDownBackgroundActive, r1121);
        fallbackKeys.put(key_avatar_backgroundArchivedHidden, key_avatar_backgroundSaved);
        fallbackKeys.put(key_featuredStickers_removeButtonText, key_featuredStickers_addButtonPressed);
        SparseIntArray r7745 = fallbackKeys;
        int r831 = key_dialogEmptyImage;
        int r914 = key_player_time;
        r7745.put(r831, r914);
        fallbackKeys.put(key_dialogEmptyText, r914);
        fallbackKeys.put(key_location_actionIcon, r108);
        SparseIntArray r7746 = fallbackKeys;
        int r832 = key_location_actionActiveIcon;
        int r915 = key_windowBackgroundWhiteBlueText7;
        r7746.put(r832, r915);
        fallbackKeys.put(key_location_actionBackground, key_dialogBackground);
        fallbackKeys.put(key_location_actionPressedBackground, key_dialogBackgroundGray);
        fallbackKeys.put(key_location_sendLocationText, r915);
        fallbackKeys.put(key_location_sendLiveLocationText, key_windowBackgroundWhiteGreenText);
        SparseIntArray r7747 = fallbackKeys;
        int r833 = key_chat_outTextSelectionHighlight;
        int r916 = key_chat_textSelectBackground;
        r7747.put(r833, r916);
        fallbackKeys.put(key_chat_inTextSelectionHighlight, r916);
        SparseIntArray r7748 = fallbackKeys;
        int r834 = key_chat_TextSelectionCursor;
        r7748.put(r834, key_chat_messagePanelCursor);
        fallbackKeys.put(key_chat_outTextSelectionCursor, r834);
        SparseIntArray r7749 = fallbackKeys;
        int r835 = key_chat_inPollCorrectAnswer;
        int r917 = key_chat_attachLocationBackground;
        r7749.put(r835, r917);
        fallbackKeys.put(key_chat_outPollCorrectAnswer, r917);
        SparseIntArray r7750 = fallbackKeys;
        int r836 = key_chat_inPollWrongAnswer;
        int r918 = key_chat_attachAudioBackground;
        r7750.put(r836, r918);
        fallbackKeys.put(key_chat_outPollWrongAnswer, r918);
        fallbackKeys.put(key_chat_editMediaButton, key_dialogFloatingButton);
        fallbackKeys.put(key_chat_attachCheckBoxBackground, key_dialogRoundCheckBox);
        fallbackKeys.put(key_pollCreateIcons, key_windowBackgroundWhiteGrayIcon);
        fallbackKeys.put(key_profile_tabText, key_windowBackgroundWhiteGrayText);
        SparseIntArray r7751 = fallbackKeys;
        int r837 = key_profile_tabSelectedText;
        int r919 = key_windowBackgroundWhiteBlueHeader;
        r7751.put(r837, r919);
        fallbackKeys.put(key_profile_tabSelectedLine, r919);
        fallbackKeys.put(key_profile_tabSelector, key_listSelector);
        fallbackKeys.put(key_chat_attachContactText, key_chat_attachContactBackground);
        SparseIntArray r7752 = fallbackKeys;
        int r838 = key_chat_inPsaNameText;
        int r920 = key_avatar_nameInMessageGreen;
        r7752.put(r838, r920);
        fallbackKeys.put(key_chat_outPsaNameText, r920);
        fallbackKeys.put(key_chat_outAdminText, key_chat_outTimeText);
        fallbackKeys.put(key_chat_outAdminSelectedText, key_chat_outTimeSelectedText);
        SparseIntArray r7753 = fallbackKeys;
        int r839 = key_returnToCallMutedBackground;
        int r921 = key_windowBackgroundWhite;
        r7753.put(r839, r921);
        SparseIntArray r7754 = fallbackKeys;
        int r840 = key_dialogSwipeRemove;
        int r109 = key_avatar_backgroundRed;
        r7754.put(r840, r109);
        fallbackKeys.put(key_chat_inReactionButtonBackground, key_chat_inLoader);
        fallbackKeys.put(key_chat_outReactionButtonBackground, key_chat_outLoader);
        fallbackKeys.put(key_chat_reactionServiceButtonBackgroundSelected, key_chat_outBubble);
        fallbackKeys.put(key_chat_reactionServiceButtonTextSelected, key_chat_messageTextOut);
        SparseIntArray r7755 = fallbackKeys;
        int r841 = key_chat_inReactionButtonText;
        r7755.put(r841, key_chat_inPreviewInstantText);
        fallbackKeys.put(key_chat_outReactionButtonText, key_chat_outPreviewInstantText);
        fallbackKeys.put(key_chat_inReactionButtonTextSelected, r921);
        fallbackKeys.put(key_chat_outReactionButtonTextSelected, r921);
        fallbackKeys.put(key_dialogReactionMentionBackground, key_voipgroup_mutedByAdminGradient2);
        fallbackKeys.put(key_topics_unreadCounter, key_chats_unreadCounter);
        fallbackKeys.put(key_topics_unreadCounterMuted, key_chats_message);
        fallbackKeys.put(key_avatar_background2Saved, key_avatar_backgroundSaved);
        fallbackKeys.put(key_avatar_background2Red, r109);
        fallbackKeys.put(key_avatar_background2Orange, key_avatar_backgroundOrange);
        fallbackKeys.put(key_avatar_background2Violet, key_avatar_backgroundViolet);
        fallbackKeys.put(key_avatar_background2Green, key_avatar_backgroundGreen);
        fallbackKeys.put(key_avatar_background2Cyan, key_avatar_backgroundCyan);
        fallbackKeys.put(key_avatar_background2Blue, key_avatar_backgroundBlue);
        fallbackKeys.put(key_avatar_background2Pink, key_avatar_backgroundPink);
        fallbackKeys.put(key_chats_date_bold, key_chats_date);
        fallbackKeys.put(key_statisticChartLine_orange, key_color_orange);
        fallbackKeys.put(key_statisticChartLine_blue, key_color_blue);
        fallbackKeys.put(key_statisticChartLine_red, key_color_red);
        fallbackKeys.put(key_statisticChartLine_lightblue, key_color_lightblue);
        fallbackKeys.put(key_statisticChartLine_golden, key_color_yellow);
        SparseIntArray r7756 = fallbackKeys;
        int r922 = key_statisticChartLine_purple;
        int r1010 = key_color_purple;
        r7756.put(r922, r1010);
        fallbackKeys.put(key_statisticChartLine_indigo, r1010);
        fallbackKeys.put(key_statisticChartLine_cyan, key_color_cyan);
        SparseIntArray r7757 = fallbackKeys;
        int r923 = key_actionBarActionModeReaction;
        int r1011 = key_windowBackgroundGray;
        r7757.put(r923, r1011);
        fallbackKeys.put(key_actionBarActionModeReactionText, r841);
        SparseIntArray r7758 = fallbackKeys;
        int r842 = key_table_background;
        r7758.put(r842, key_graySection);
        SparseIntArray r7759 = fallbackKeys;
        int r924 = key_table_border;
        r7759.put(r924, key_divider);
        fallbackKeys.put(key_chat_inTableBackground, r842);
        SparseIntArray r7760 = fallbackKeys;
        int r843 = key_chat_outTableBackground;
        int r1122 = key_chat_outCodeBackground;
        r7760.put(r843, r1122);
        fallbackKeys.put(key_chat_inTableBorder, r924);
        fallbackKeys.put(key_chat_outTableBorder, r924);
        fallbackKeys.put(key_chat_outArticleCodeBackground, r1122);
        SparseIntArray r7761 = fallbackKeys;
        int r844 = key_share_icon;
        int r925 = key_windowBackgroundWhiteBlackText;
        r7761.put(r844, r925);
        fallbackKeys.put(key_share_linkBackground, r1011);
        fallbackKeys.put(key_share_linkText, r925);
        fallbackKeys.put(key_glass_defaultIcon, key_chat_messagePanelIcons);
        fallbackKeys.put(key_glass_defaultText, key_chat_messagePanelText);
        SparseIntArray r7762 = fallbackKeys;
        int r845 = key_glass_targetMainTabs;
        int r1012 = key_dialogBackground;
        r7762.put(r845, r1012);
        fallbackKeys.put(key_glass_targetMainTopPanel, r1012);
        SparseIntArray r7763 = fallbackKeys;
        int r846 = key_glass_tabSelected;
        int r1013 = key_chat_messagePanelSend;
        r7763.put(r846, r1013);
        fallbackKeys.put(key_glass_tabSelectedText, r1013);
        fallbackKeys.put(key_glass_tabUnselected, r925);
        fallbackKeys.put(key_actionBarDefaultTitle, r925);
        fallbackKeys.put(key_telegram_color_dialogsLogo, r925);
        fallbackKeys.put(key_profile_title, r925);
        fallbackKeys.put(key_telegram_color, r1013);
        SparseIntArray r7764 = fallbackKeys;
        int r847 = key_telegram_color_text;
        r7764.put(r847, key_windowBackgroundWhiteBlueText4);
        fallbackKeys.put(key_chat_msgIvButtonDefaultInText, key_chat_messageTextIn);
        SparseIntArray r7765 = fallbackKeys;
        int r926 = key_chat_msgIvButtonPrimaryIn;
        int r1014 = key_featuredStickers_addButton;
        r7765.put(r926, r1014);
        SparseIntArray r7766 = fallbackKeys;
        int r927 = key_chat_msgIvButtonPrimaryInPressed;
        int r1123 = key_featuredStickers_addButtonPressed;
        r7766.put(r927, r1123);
        SparseIntArray r7767 = fallbackKeys;
        int r928 = key_chat_msgIvButtonPrimaryInText;
        int r126 = key_featuredStickers_buttonText;
        r7767.put(r928, r126);
        SparseIntArray r7768 = fallbackKeys;
        int r929 = key_chat_msgIvButtonDangerInText;
        int r135 = key_avatar_nameInMessageRed;
        r7768.put(r929, r135);
        SparseIntArray r7769 = fallbackKeys;
        int r930 = key_chat_msgIvButtonSuccessInText;
        int r143 = key_avatar_nameInMessageGreen;
        r7769.put(r930, r143);
        fallbackKeys.put(key_chat_msgIvButtonDefaultInlineInText, r847);
        fallbackKeys.put(key_chat_msgIvButtonDefaultOutText, key_chat_messageTextOut);
        fallbackKeys.put(key_chat_msgIvButtonPrimaryOut, r1014);
        fallbackKeys.put(key_chat_msgIvButtonPrimaryOutPressed, r1123);
        fallbackKeys.put(key_chat_msgIvButtonPrimaryOutText, r126);
        fallbackKeys.put(key_chat_msgIvButtonDangerOutText, r135);
        fallbackKeys.put(key_chat_msgIvButtonSuccessOutText, r143);
        fallbackKeys.put(key_chat_msgIvButtonDefaultInlineOutText, r847);
        int r7770 = 0;
    L3:
        int[] r848 = keys_avatar_background;
        if (r7770 >= r848.length) goto L6;
        themeAccentExclusionKeys.add(Integer.valueOf(r848[r7770]));
        r7770 = r7770 + 1;
        goto L3
    L6:
        int r7771 = 0;
    L7:
        int[] r849 = keys_avatar_background2;
        if (r7771 >= r849.length) goto L10;
        themeAccentExclusionKeys.add(Integer.valueOf(r849[r7771]));
        r7771 = r7771 + 1;
        goto L7
    L10:
        int r7772 = 0;
    L11:
        int[] r850 = keys_avatar_nameInMessage;
        if (r7772 >= r850.length) goto L14;
        themeAccentExclusionKeys.add(Integer.valueOf(r850[r7772]));
        r7772 = r7772 + 1;
        goto L11
    L14:
        int r7773 = 0;
    L15:
        int[] r851 = keys_colors;
        if (r7773 >= r851.length) goto L18;
        themeAccentExclusionKeys.add(Integer.valueOf(r851[r7773]));
        r7773 = r7773 + 1;
        goto L15
    L18:
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_attachGalleryBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_blue));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_green));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_red));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_golden));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_lightblue));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_lightgreen));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_orange));
        themeAccentExclusionKeys.add(Integer.valueOf(key_statisticChartLine_indigo));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inCodeBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inTableBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outTableBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inTableBorder));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outTableBorder));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inDivider));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inArticleCodeBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outArticleCodeBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inArticleCodeScrollbarBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inArticleCodeScrollbar));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outArticleCodeScrollbarBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outArticleCodeScrollbar));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inArticleDetailsArrow));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outArticleDetailsArrow));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_inArticleDetailsLine));
        themeAccentExclusionKeys.add(Integer.valueOf(key_chat_outArticleDetailsLine));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_checkMenu));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_muteButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_muteButton2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_muteButton3));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_searchText));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_searchPlaceholder));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_searchBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_leaveCallMenu));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_scrollUp));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_soundButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_soundButtonActive));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_soundButtonActiveScrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_soundButton2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_soundButtonActive2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_soundButtonActive2Scrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_leaveButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_leaveButtonScrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_connectingProgress));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_disabledButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_rtmpButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_disabledButtonActive));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_disabledButtonActiveScrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_unmuteButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_unmuteButton2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_actionBarUnscrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_listViewBackgroundUnscrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_lastSeenTextUnscrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedIconUnscrolled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_actionBar));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_actionBarItems));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_actionBarItemsSelector));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedByAdminIcon));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedIcon));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_lastSeenText));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_nameText));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_listViewBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_listeningText));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_speakingText));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_listSelector));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_inviteMembersBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_dialogBackground));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayGreen1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayGreen2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayBlue1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayBlue2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_topPanelGreen1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_topPanelGreen2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_topPanelBlue1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_topPanelBlue2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_topPanelGray));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayAlertGradientMuted));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayAlertGradientMuted2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayAlertGradientUnmuted));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayAlertGradientUnmuted2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayAlertMutedByAdmin));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_overlayAlertMutedByAdmin2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedByAdminGradient));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedByAdminGradient2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedByAdminGradient3));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedByAdminMuteButton));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_mutedByAdminMuteButtonDisabled));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_windowBackgroundWhiteInputField));
        themeAccentExclusionKeys.add(Integer.valueOf(key_voipgroup_windowBackgroundWhiteInputFieldActivated));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradient0));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradient1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradient2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradient3));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradient4));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradientBackground1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradientBackground2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradientBackground3));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumGradientBackground4));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumStartSmallStarsColor));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumStarGradient1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_premiumStarGradient2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_stories_circle1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_stories_circle2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_stories_circle_dialog1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_stories_circle_dialog2));
        themeAccentExclusionKeys.add(Integer.valueOf(key_stories_circle_closeFriends1));
        themeAccentExclusionKeys.add(Integer.valueOf(key_stories_circle_closeFriends2));
        themes = new ArrayList();
        otherThemes = new ArrayList();
        themesDict = new HashMap();
        currentColorsNoAccent = new SparseIntArray();
        currentColors = new SparseIntArray();
        SharedPreferences r7774 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        ThemeInfo r852 = new ThemeInfo();
        r852.name = "Blue";
        r852.assetName = "bluebubbles.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r852, -6963476);
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r852, -1);
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r852, -3086593);
        r852.firstAccentIsDefault = true;
        r852.currentAccentId = DEFALT_THEME_ACCENT_ID;
        r852.sortIndex = 1;
        ThemeInfo.m5838$$Nest$msetAccentColorOptions(r852, new int[]{-10972987, -14444461, -3252606, -8428605, -14380627, -14050257, -7842636, -13464881, -12342073, -11359164, -3317869, -2981834, -8165684, -3256745, -2904512, -8681301}, new int[]{-4660851, -328756, -1572, -4108434, -3031781, -1335, -198952, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -853047, -264993, 0, 0, -135756, -198730, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, -2104672, -937328, -2637335, -2639714, -1270157, -3428124, -6570777, -7223828, -6567550, -1793599, -1855875, -4674838, -1336199, -2900876, -6247730}, new int[]{0, -4532067, -1257580, -1524266, -1646910, -1519483, -1324823, -4138509, -4202516, -2040429, -1458474, -1256030, -3814930, -1000039, -1450082, -3485987}, new int[]{0, -1909081, -1592444, -2969879, -2439762, -1137033, -2119471, -6962197, -4857383, -4270699, -3364639, -2117514, -5000734, -1598028, -2045813, -5853742}, new int[]{0, -6371440, -1319256, -1258616, -1712961, -1186647, -1193816, -4467224, -4203544, -3023977, -1061929, -1255788, -2113811, -806526, -1715305, -3485976}, new int[]{99, 9, 10, 11, 12, 13, 14, 0, 1, 2, 3, 4, 5, 6, 7, 8}, new String[]{_UrlKt.FRAGMENT_ENCODE_SET, "p-pXcflrmFIBAAAAvXYQk-mCwZU", "JqSUrO0-mFIBAAAAWwTvLzoWGQI", "O-wmAfBPSFADAAAA4zINVfD_bro", "RepJ5uE_SVABAAAAr4d0YhgB850", "-Xc-np9y2VMCAAAARKr0yNNPYW0", "fqv01SQemVIBAAAApND8LDRUhRU", "fqv01SQemVIBAAAApND8LDRUhRU", "RepJ5uE_SVABAAAAr4d0YhgB850", "lp0prF8ISFAEAAAA_p385_CvG0w", "heptcj-hSVACAAAAC9RrMzOa-cs", "PllZ-bf_SFAEAAAA8crRfwZiDNg", "dhf9pceaQVACAAAAbzdVo4SCiZA", "Ujx2TFcJSVACAAAARJ4vLa50MkM", "p-pXcflrmFIBAAAAvXYQk-mCwZU", "dk_wwlghOFACAAAAfz9xrxi6euw"}, new int[]{0, 180, 45, 0, 45, 180, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 52, 46, 57, 45, 64, 52, 35, 36, 41, 50, 50, 35, 38, 37, 30});
        sortAccents(r852);
        ArrayList<ThemeInfo> r2 = themes;
        defaultTheme = r852;
        currentDayTheme = r852;
        r2.add(r852);
        themesDict.put("Blue", r852);
        ThemeInfo r210 = new ThemeInfo();
        r210.name = "Dark Blue";
        r210.assetName = "darkblue.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r210, -10523006);
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r210, -9009508);
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r210, -8214301);
        r210.sortIndex = 3;
        ThemeInfo.m5838$$Nest$msetAccentColorOptions(r210, new int[]{-7177260, -9860357, -14440464, -8687151, -9848491, -14053142, -9403671, -10044691, -13203974, -12138259, -10179489, -1344335, -1142742, -6127120, -2931932, -1131212, -8417365, -13270557}, new int[]{-6464359, -10267323, -13532789, -5413850, -11898828, -13410942, -13215889, -10914461, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-10465880, -9937588, -14983040, -6736562, -14197445, -13534568, -13144441, -10587280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-14213586, -15263198, -16310753, -15724781, -15853551, -16051428, -14868183, -14668758, -15854566, -15326427, -15327979, -14411490, -14345453, -14738135, -14543346, -14212843, -15263205, -15854566}, new int[]{-15659501, -14277074, -15459034, -14542297, -14735336, -15129808, -15591910, -15459810, -15260623, -15853800, -15259879, -14477540, -14674936, -15461604, -13820650, -15067635, -14605528, -15260623}, new int[]{-13951445, -15395557, -15985382, -15855853, -16050417, -15525854, -15260627, -15327189, -15788258, -14799314, -15458796, -13952727, -13754603, -14081231, -14478324, -14081004, -15197667, -15788258}, new int[]{-15330777, -15066858, -15915220, -14213847, -15262439, -15260879, -15657695, -16443625, -15459285, -15589601, -14932454, -14740451, -15002870, -15264997, -13821660, -14805234, -14605784, -15459285}, new int[]{11, 12, 13, 14, 15, 16, 17, 18, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new String[]{"O-wmAfBPSFADAAAA4zINVfD_bro", "RepJ5uE_SVABAAAAr4d0YhgB850", "dk_wwlghOFACAAAAfz9xrxi6euw", "9LW_RcoOSVACAAAAFTk3DTyXN-M", "PllZ-bf_SFAEAAAA8crRfwZiDNg", "-Xc-np9y2VMCAAAARKr0yNNPYW0", "kO4jyq55SFABAAAA0WEpcLfahXk", "CJNyxPMgSVAEAAAAvW9sMwc51cw", "fqv01SQemVIBAAAApND8LDRUhRU", "RepJ5uE_SVABAAAAr4d0YhgB850", "CJNyxPMgSVAEAAAAvW9sMwc51cw", "9LW_RcoOSVACAAAAFTk3DTyXN-M", "9GcNVISdSVADAAAAUcw5BYjELW4", "F5oWoCs7QFACAAAAgf2bD_mg8Bw", "9ShF73d1MFIIAAAAjWnm8_ZMe8Q", "3rX-PaKbSFACAAAAEiHNvcEm6X4", "dk_wwlghOFACAAAAfz9xrxi6euw", "fqv01SQemVIBAAAApND8LDRUhRU"}, new int[]{Opcodes.SHR_INT_LIT8, 45, Opcodes.SHR_INT_LIT8, 135, 45, Opcodes.SHR_INT_LIT8, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{40, 40, 31, 50, 25, 34, 35, 35, 38, 29, 24, 34, 34, 31, 29, 37, 21, 38});
        sortAccents(r210);
        themes.add(r210);
        HashMap<String, ThemeInfo> r211 = themesDict;
        currentNightTheme = r210;
        r211.put("Dark Blue", r210);
        ThemeInfo r0 = new ThemeInfo();
        r0.name = "Arctic Blue";
        r0.assetName = "arctic.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r0, -1971728);
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r0, -1);
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r0, -9657877);
        r0.sortIndex = 5;
        ThemeInfo.m5838$$Nest$msetAccentColorOptions(r0, new int[]{-12537374, -12472227, -3240928, -11033621, -2194124, -3382903, -13332245, -12342073, -11359164, -3317869, -2981834, -8165684, -3256745, -2904512, -8681301}, new int[]{-13525046, -14113959, -7579073, -13597229, -3581840, -8883763, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-11616542, -9716647, -6400452, -12008744, -2592697, -4297041, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-3808528, -2433367, -2700891, -1838093, -1120848, -1712148, -2037779, -4202261, -4005713, -1058332, -925763, -1975316, -1189672, -1318451, -2302235}, new int[]{-1510157, -4398164, -1647697, -3610898, -1130838, -1980692, -4270093, -4202261, -3415654, -1259815, -1521765, -4341268, -1127744, -1318219, -3945761}, new int[]{-4924688, -3283031, -1523567, -2494477, -1126510, -595210, -2037517, -3478548, -4661623, -927514, -796762, -2696971, -1188403, -1319735, -1577487}, new int[]{-3149585, -5714021, -1978209, -4925720, -1134713, -1718833, -3613709, -5317397, -3218014, -999207, -2116466, -4343054, -931397, -1583186, -3815718}, new int[]{9, 10, 11, 12, 13, 14, 0, 1, 2, 3, 4, 5, 6, 7, 8}, new String[]{"MIo6r0qGSFAFAAAAtL8TsDzNX60", "dhf9pceaQVACAAAAbzdVo4SCiZA", "fqv01SQemVIBAAAApND8LDRUhRU", "p-pXcflrmFIBAAAAvXYQk-mCwZU", "JqSUrO0-mFIBAAAAWwTvLzoWGQI", "F5oWoCs7QFACAAAAgf2bD_mg8Bw", "fqv01SQemVIBAAAApND8LDRUhRU", "RepJ5uE_SVABAAAAr4d0YhgB850", "PllZ-bf_SFAEAAAA8crRfwZiDNg", "pgJfpFNRSFABAAAACDT8s5sEjfc", "ptuUd96JSFACAAAATobI23sPpz0", "dhf9pceaQVACAAAAbzdVo4SCiZA", "JqSUrO0-mFIBAAAAWwTvLzoWGQI", "9iklpvIPQVABAAAAORQXKur_Eyc", "F5oWoCs7QFACAAAAgf2bD_mg8Bw"}, new int[]{MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, Opcodes.SHR_INT_LIT8, MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, 0, 180, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{50, 50, 58, 47, 46, 50, 49, 46, 51, 50, 49, 34, 54, 50, 40});
        sortAccents(r0);
        themes.add(r0);
        themesDict.put("Arctic Blue", r0);
        ThemeInfo r02 = new ThemeInfo();
        r02.name = "Day";
        r02.assetName = "day.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r02, -1);
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r02, -1315084);
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r02, -8604930);
        r02.sortIndex = 2;
        ThemeInfo.m5838$$Nest$msetAccentColorOptions(r02, new int[]{-11099447, -3379581, -3109305, -3382174, -7963438, -11759137, -11029287, -11226775, -2506945, -3382174, -3379581, -6587438, -2649788, -8681301}, new int[]{-10125092, -9671214, -3451775, -3978678, -10711329, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-12664362, -3642988, -2383569, -3109317, -11422261, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, null, null, new int[]{9, 10, 11, 12, 13, 0, 1, 2, 3, 4, 5, 6, 7, 8}, new String[]{_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sortAccents(r02);
        themes.add(r02);
        themesDict.put("Day", r02);
        ThemeInfo r03 = new ThemeInfo();
        r03.name = "Night";
        r03.assetName = "night.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r03, -11315623);
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r03, -9143676);
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r03, -9067802);
        r03.sortIndex = 4;
        ThemeInfo.m5838$$Nest$msetAccentColorOptions(r03, new int[]{-9781697, -7505693, -2204034, -10913816, -2375398, -12678921, -11881005, -11880383, -2534026, -1934037, -7115558, -3128522, -1528292, -8812381}, new int[]{-7712108, -4953061, -5288081, -14258547, -9154889, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-9939525, -5948598, -10335844, -13659747, -14054507, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{-15330532, -14806760, -15791344, -16184308, -16313063, -15921641, -15656164, -15986420, -15856883, -14871025, -16185078, -14937584, -14869736, -15855598}, new int[]{-14673881, -15724781, -15002342, -15458526, -15987697, -16184820, -16118258, -16250616, -15067624, -15527923, -14804447, -15790836, -15987960, -16316665}, new int[]{-15856877, -14608861, -15528430, -15921391, -15722209, -15197144, -15458015, -15591406, -15528431, -15068401, -16053749, -15594229, -15395825, -15724012}, new int[]{-14804694, -15658986, -14609382, -15656421, -16118509, -15855854, -16315381, -16052981, -14544354, -15791092, -15659241, -16316922, -15988214, -16185077}, new int[]{9, 10, 11, 12, 13, 0, 1, 2, 3, 4, 5, 6, 7, 8}, new String[]{"YIxYGEALQVADAAAAA3QbEH0AowY", "9LW_RcoOSVACAAAAFTk3DTyXN-M", "O-wmAfBPSFADAAAA4zINVfD_bro", "F5oWoCs7QFACAAAAgf2bD_mg8Bw", "-Xc-np9y2VMCAAAARKr0yNNPYW0", "fqv01SQemVIBAAAApND8LDRUhRU", "F5oWoCs7QFACAAAAgf2bD_mg8Bw", "ptuUd96JSFACAAAATobI23sPpz0", "p-pXcflrmFIBAAAAvXYQk-mCwZU", "Nl8Pg2rBQVACAAAA25Lxtb8SDp0", "dhf9pceaQVACAAAAbzdVo4SCiZA", "9GcNVISdSVADAAAAUcw5BYjELW4", "9LW_RcoOSVACAAAAFTk3DTyXN-M", "dk_wwlghOFACAAAAfz9xrxi6euw"}, new int[]{45, 135, 0, 180, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{34, 47, 52, 48, 54, 50, 37, 56, 48, 49, 40, 64, 38, 48});
        sortAccents(r03);
        themes.add(r03);
        themesDict.put("Night", r03);
        if (Build.VERSION.SDK_INT < 31) goto L21;
        ThemeInfo r04 = new ThemeInfo();
        r04.name = "Monet Light";
        r04.assetName = "monet_light.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r04, MonetUtils.getColor("n1_10"));
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r04, MonetUtils.getColor("n1_50"));
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r04, MonetUtils.getColor("a1_600"));
        r04.sortIndex = 6;
        themes.add(r04);
        themesDict.put(r04.name, r04);
        ThemeInfo r05 = new ThemeInfo();
        r05.name = "Monet Dark";
        r05.assetName = "monet_dark.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r05, MonetUtils.getColor("n1_900"));
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r05, MonetUtils.getColor("n1_800"));
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r05, MonetUtils.getColor("a1_200"));
        r05.sortIndex = 7;
        themes.add(r05);
        themesDict.put(r05.name, r05);
        ThemeInfo r06 = new ThemeInfo();
        r06.name = "Monet Black";
        r06.assetName = "monet_black.attheme";
        ThemeInfo.m5829$$Nest$fputpreviewBackgroundColor(r06, MonetUtils.getColor("mBlack"));
        ThemeInfo.m5830$$Nest$fputpreviewInColor(r06, MonetUtils.getColor("n1_800"));
        ThemeInfo.m5831$$Nest$fputpreviewOutColor(r06, MonetUtils.getColor("a1_200"));
        r06.sortIndex = 8;
        themes.add(r06);
        themesDict.put(r06.name, r06);
    L21:
        String r212 = null;
        String r07 = r7774.getString("themes2", null);
        if (r7774.getInt("remote_version", 0) != 1) goto L34;
        int r4 = 0;
    L24:
        if (r4 >= 16) goto L34;
        long[] r1015 = remoteThemesHash;
        StringBuilder r1124 = new StringBuilder("2remoteThemesHash");
        if (r4 == 0) goto L28;
        Object r127 = Integer.valueOf(r4);
    L29:
        r1124.append(r127);
        r1015[r4] = r7774.getLong(r1124.toString(), 0);
        int[] r1016 = lastLoadingThemesTime;
        StringBuilder r1125 = new StringBuilder("lastLoadingThemesTime");
        if (r4 == 0) goto L32;
        Object r128 = Integer.valueOf(r4);
    L33:
        r1125.append(r128);
        r1016[r4] = r7774.getInt(r1125.toString(), 0);
        r4 = r4 + 1;
        goto L24
    L32:
        r128 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L33
    L28:
        r127 = _UrlKt.FRAGMENT_ENCODE_SET;
    L34:
        r7774.edit().putInt("remote_version", 1).apply();
        if (TextUtils.isEmpty(r07) == false) goto L234;
        String r08 = r7774.getString("themes", null);
        if (TextUtils.isEmpty(r08) == true) goto L56;
        String[] r09 = r08.split("&");
        int r42 = r09.length;
        int r931 = 0;
    L50:
        if (r931 >= r42) goto L55;
        ThemeInfo r1017 = ThemeInfo.createWithString(r09[r931]);
        if (r1017 == null) goto L54;
        otherThemes.add(r1017);
        themes.add(r1017);
        themesDict.put(r1017.getKey(), r1017);
    L54:
        r931 = r931 + 1;
        goto L50
    L55:
        saveOtherThemes(true, true);
        r7774.edit().remove("themes").apply();
    L56:
        sortThemes();
        SharedPreferences r010 = MessagesController.getGlobalMainSettings();
        ThemeInfo r43 = themesDict.get("Dark Blue");     // Catch: Exception -> L227
        String r5 = r010.getString("theme", null);     // Catch: Exception -> L227
        if ("Default".equals(r5) == false) goto L61;
        ThemeInfo r52 = themesDict.get("Blue");     // Catch: Exception -> L227
        r52.currentAccentId = DEFALT_THEME_ACCENT_ID;     // Catch: Exception -> L227
    L70:
        String r932 = r010.getString("nighttheme", null);     // Catch: Exception -> L227
        if ("Default".equals(r932) == false) goto L74;
        r52 = themesDict.get("Blue");     // Catch: Exception -> L227
        r52.currentAccentId = DEFALT_THEME_ACCENT_ID;     // Catch: Exception -> L227
    L81:
        if (currentNightTheme != null) goto L83;
    L85:
        Iterator<ThemeInfo> r1 = themesDict.values().iterator();     // Catch: Exception -> L227
        SharedPreferences.Editor r44 = null;
        SharedPreferences.Editor r933 = null;
    L87:
        if (r1.hasNext() == false) goto L182;
        ThemeInfo r1126 = r1.next();     // Catch: Exception -> L227
        if (r1126.assetName != null) goto L91;
    L178:
        Iterator<ThemeInfo> r129 = r1;
        if (r1126.isMonet() == false) goto L181;
        ThemeInfo.m5836$$Nest$mloadWallpapers(r1126, r7774);     // Catch: Exception -> L227
    L181:
        r1 = r129;
        r212 = null;
        goto L87
    L91:
        if (r1126.accentBaseColor == 0) goto L178;
        String r1210 = r7774.getString("accents_" + r1126.assetName, r212);     // Catch: Exception -> L227
        String r136 = "accent_current_" + r1126.assetName;     // Catch: Exception -> L227
        if (r1126.firstAccentIsDefault == false) goto L95;
        int r144 = DEFALT_THEME_ACCENT_ID;     // Catch: Exception -> L227
    L96:
        r1126.currentAccentId = r7774.getInt(r136, r144);     // Catch: Exception -> L227
        ArrayList r137 = new ArrayList();     // Catch: Exception -> L227
        if (TextUtils.isEmpty(r1210) == false) goto L230;
        r129 = r1;
        String r110 = "accent_for_" + r1126.assetName;     // Catch: Exception -> L227
        int r3 = r010.getInt(r110, 0);     // Catch: Exception -> L227
        if (r3 == 0) goto L164;
        if (r44 != null) goto L154;
        SharedPreferences.Editor r213 = r010.edit();     // Catch: Exception -> L227
        r933 = r7774.edit();     // Catch: Exception -> L227
        r44 = r213;
    L154:
        r44.remove(r110);     // Catch: Exception -> L227
        int r111 = r1126.themeAccents.size();     // Catch: Exception -> L227
        int r214 = 0;
    L155:
        if (r214 >= r111) goto L160;
        ThemeAccent r6 = r1126.themeAccents.get(r214);     // Catch: Exception -> L227
        if (r6.accentColor == r3) goto L158;
        r214 = r214 + 1;     // Catch: Exception -> L227
        goto L155
    L158:
        r1126.currentAccentId = r6.id;     // Catch: Exception -> L227
    L161:
        r933.putInt("accent_current_" + r1126.assetName, r1126.currentAccentId);     // Catch: Exception -> L227
        goto L164
    L160:
        ThemeAccent r120 = new ThemeAccent();     // Catch: Exception -> L227
        r120.id = 100;     // Catch: Exception -> L227
        r120.accentColor = r3;     // Catch: Exception -> L227
        r120.parentTheme = r1126;     // Catch: Exception -> L227
        r1126.themeAccentsMap.put(100, r120);     // Catch: Exception -> L227
        r137.add(0, r120);     // Catch: Exception -> L227
        r1126.currentAccentId = 100;     // Catch: Exception -> L227
        r1126.lastAccentId = 101;     // Catch: Exception -> L227
        SerializedData r215 = new SerializedData(72);     // Catch: Exception -> L227
        r215.writeInt32(9);     // Catch: Exception -> L227
        r215.writeInt32(1);     // Catch: Exception -> L227
        r215.writeInt32(r120.id);     // Catch: Exception -> L227
        r215.writeInt32(r120.accentColor);     // Catch: Exception -> L227
        r215.writeInt32(r120.myMessagesAccentColor);     // Catch: Exception -> L227
        r215.writeInt32(r120.myMessagesGradientAccentColor1);     // Catch: Exception -> L227
        r215.writeInt32(r120.myMessagesGradientAccentColor2);     // Catch: Exception -> L227
        r215.writeInt32(r120.myMessagesGradientAccentColor3);     // Catch: Exception -> L227
        r215.writeBool(r120.myMessagesAnimated);     // Catch: Exception -> L227
        r215.writeInt64(r120.backgroundOverrideColor);     // Catch: Exception -> L227
        r215.writeInt64(r120.backgroundGradientOverrideColor1);     // Catch: Exception -> L227
        r215.writeInt64(r120.backgroundGradientOverrideColor2);     // Catch: Exception -> L227
        r215.writeInt64(r120.backgroundGradientOverrideColor3);     // Catch: Exception -> L227
        r215.writeInt32(r120.backgroundRotation);     // Catch: Exception -> L227
        r215.writeInt64(0);     // Catch: Exception -> L227
        r215.writeDouble(r120.patternIntensity);     // Catch: Exception -> L227
        r215.writeBool(r120.patternMotion);     // Catch: Exception -> L227
        r215.writeString(r120.patternSlug);     // Catch: Exception -> L227
        r215.writeBool(false);     // Catch: Exception -> L227
        r933.putString("accents_" + r1126.assetName, Base64.encodeToString(r215.toByteArray(), 3));     // Catch: Exception -> L227
    L164:
        if (r137.isEmpty() == true) goto L166;
        r1126.themeAccents.addAll(0, r137);     // Catch: Exception -> L227
        sortAccents(r1126);     // Catch: Exception -> L227
    L166:
        SparseArray<ThemeAccent> r121 = r1126.themeAccentsMap;     // Catch: Exception -> L227
        if (r121 != null) goto L169;
    L175:
        ThemeInfo.m5836$$Nest$mloadWallpapers(r1126, r7774);     // Catch: Exception -> L227
        ThemeAccent r130 = r1126.getAccent(false);     // Catch: Exception -> L227
        if (r130 == null) goto L181;
        r1126.overrideWallpaper = r130.overrideWallpaper;     // Catch: Exception -> L227
        goto L181
    L169:
        if (r121.get(r1126.currentAccentId) != null) goto L175;
        if (r1126.firstAccentIsDefault == false) goto L173;
        int r131 = DEFALT_THEME_ACCENT_ID;     // Catch: Exception -> L227
    L174:
        r1126.currentAccentId = r131;     // Catch: Exception -> L227
        goto L175
    L173:
        r131 = 0;
        goto L174
    L230:
        SerializedData r145 = new SerializedData(Base64.decode(r1210, 3));     // Catch: Throwable -> L147
        boolean r1211 = true;
        int r153 = r145.readInt32(true);     // Catch: Throwable -> L147
        int r216 = r145.readInt32(true);     // Catch: Throwable -> L147
        int r62 = 0;
    L100:
        if (r62 >= r216) goto L145;
        ThemeAccent r32 = new ThemeAccent();     // Catch: Throwable -> L142
        r32.id = r145.readInt32(r1211);     // Catch: Throwable -> L142
        r32.accentColor = r145.readInt32(r1211);     // Catch: Throwable -> L142
        if (r153 < 9) goto L104;
        r32.accentColor2 = r145.readInt32(r1211);     // Catch: Throwable -> L142
    L104:
        r32.parentTheme = r1126;     // Catch: Throwable -> L142
        r32.myMessagesAccentColor = r145.readInt32(true);     // Catch: Throwable -> L142
        r32.myMessagesGradientAccentColor1 = r145.readInt32(true);     // Catch: Throwable -> L142
        if (r153 < 7) goto L108;
        r32.myMessagesGradientAccentColor2 = r145.readInt32(true);     // Catch: Throwable -> L142
        r32.myMessagesGradientAccentColor3 = r145.readInt32(true);     // Catch: Throwable -> L142
    L108:
        if (r153 < 8) goto L111;
        boolean r1018 = true;
        r32.myMessagesAnimated = r145.readBool(true);     // Catch: Throwable -> L142
    L112:
        if (r153 < 3) goto L115;
        Iterator<ThemeInfo> r1212 = r1;
        int r242 = r216;
        r32.backgroundOverrideColor = r145.readInt64(r1018);     // Catch: Throwable -> L142
    L116:
        if (r153 < 2) goto L119;
        r32.backgroundGradientOverrideColor1 = r145.readInt64(r1018);     // Catch: Throwable -> L142
    L118:
        ?? r1019 = r1018;
        if (r153 < 6) goto L122;
        r32.backgroundGradientOverrideColor2 = r145.readInt64(r1018);     // Catch: Throwable -> L142
        r32.backgroundGradientOverrideColor3 = r145.readInt64(r1018);     // Catch: Throwable -> L142
        r1019 = 1;
    L122:
        if (r153 < r1019) goto L125;
        r32.backgroundRotation = r145.readInt32(r1019);     // Catch: Throwable -> L142
    L125:
        if (r153 < 4) goto L129;
        r145.readInt64(true);     // Catch: Throwable -> L142
        r32.patternIntensity = (float) r145.readDouble(true);     // Catch: Throwable -> L142
        r32.patternMotion = r145.readBool(true);     // Catch: Throwable -> L142
        int r138 = 5;
        if (r153 < 5) goto L130;
        r32.patternSlug = r145.readString(true);     // Catch: Throwable -> L142
    L130:
        if (r153 >= r138) goto L132;
    L134:
        TLRPC.TL_theme r217 = r32.info;     // Catch: Throwable -> L142
        if (r217 == null) goto L137;
        r32.isDefault = r217.isDefault;     // Catch: Throwable -> L142
    L137:
        r1126.themeAccentsMap.put(r32.id, r32);     // Catch: Throwable -> L142
        TLRPC.TL_theme r218 = r32.info;     // Catch: Throwable -> L142
        if (r218 == null) goto L140;
        r1126.accentsByThemeId.put(r218.id, r32);     // Catch: Throwable -> L142
    L140:
        r137.add(r32);     // Catch: Throwable -> L142
        r1126.lastAccentId = Math.max(r1126.lastAccentId, r32.id);     // Catch: Throwable -> L142
        r62 = r62 + 1;
        r1 = r1212;
        r216 = r242;
        r1211 = true;
        goto L100
    L132:
        if (r145.readBool(true) == false) goto L134;
        r32.account = r145.readInt32(true);     // Catch: Throwable -> L142
        r32.info = TLRPC.Theme.TLdeserialize(r145, r145.readInt32(true), true);     // Catch: Throwable -> L142
    L129:
        r138 = 5;
        goto L130
    L119:
        r32.backgroundGradientOverrideColor1 = r145.readInt32(r1018);     // Catch: Throwable -> L142
        goto L118
    L115:
        r1212 = r1;
        r242 = r216;
        r32.backgroundOverrideColor = r145.readInt32(r1018);     // Catch: Throwable -> L142
        goto L116
    L111:
        r1018 = true;
        goto L112
    L142:
        th = move-exception;
        throw new RuntimeException(th);     // Catch: Throwable -> L147
    L145:
        r129 = r1;
    L147:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L227
        throw new RuntimeException(th);     // Catch: Exception -> L227
    L95:
        r144 = 0;
        goto L96
    L182:
        if (r44 == null) goto L184;
        r44.apply();     // Catch: Exception -> L227
        r933.apply();     // Catch: Exception -> L227
    L184:
        selectedAutoNightType = r010.getInt("selectedAutoNightType", 0);     // Catch: Exception -> L227
        autoNightScheduleByLocation = r010.getBoolean("autoNightScheduleByLocation", false);     // Catch: Exception -> L227
        autoNightBrighnessThreshold = r010.getFloat("autoNightBrighnessThreshold", 0.25f);     // Catch: Exception -> L227
        autoNightDayStartTime = r010.getInt("autoNightDayStartTime", 1320);     // Catch: Exception -> L227
        autoNightDayEndTime = r010.getInt("autoNightDayEndTime", 480);     // Catch: Exception -> L227
        autoNightSunsetTime = r010.getInt("autoNightSunsetTime", 1320);     // Catch: Exception -> L227
        autoNightSunriseTime = r010.getInt("autoNightSunriseTime", 480);     // Catch: Exception -> L227
        autoNightCityName = r010.getString("autoNightCityName", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L227
        long r63 = r010.getLong("autoNightLocationLatitude3", NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L227
        if (r63 == NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L187;
        autoNightLocationLatitude = Double.longBitsToDouble(r63);     // Catch: Exception -> L227
    L188:
        long r64 = r010.getLong("autoNightLocationLongitude3", NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L227
        if (r64 == NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L191;
        autoNightLocationLongitude = Double.longBitsToDouble(r64);     // Catch: Exception -> L227
    L192:
        autoNightLastSunCheckDay = r010.getInt("autoNightLastSunCheckDay", -1);     // Catch: Exception -> L227
        if (r52 != null) goto L195;
        r52 = defaultTheme;
    L197:
        if (r010.contains("overrideThemeWallpaper") == false) goto L199;
    L200:
        boolean r45 = r010.getBoolean("overrideThemeWallpaper", false);
        long r65 = r010.getLong("selectedBackground2", 1000001);
        if (r65 == (-1)) goto L207;
        if (r45 == true) goto L204;
    L217:
        r010.edit().remove("overrideThemeWallpaper").remove("selectedBackground2").apply();
    L218:
        int r011 = needSwitchToTheme();
        if (r011 != 2) goto L221;
        r52 = currentNightTheme;
    L221:
        if (r011 != 2) goto L224;
        boolean r853 = true;
    L225:
        applyTheme(r52, false, false, r853);
        AndroidUtilities.runOnUIThread(new MessagesController$$ExternalSyntheticLambda83());
        ambientSensorListener = new AnonymousClass11();
        viewPos = new int[2];
        PAINT_FILLING = new Paint(1);
        Paint r012 = new Paint(1);
        PAINT_CLEAR = r012;
        r012.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r013 = new Paint();
        DEBUG_RED = r013;
        r013.setColor(org.mvel2.asm.Opcodes.V_PREVIEW);
        Paint r014 = new Paint();
        DEBUG_BLUE = r014;
        r014.setColor(-16776961);
        Paint r015 = new Paint();
        DEBUG_GREEN_40 = r015;
        r015.setColor(1073807104);
        Paint r016 = new Paint();
        DEBUG_GREEN_B0 = r016;
        r016.setColor(-1342112000);
        Paint r017 = new Paint();
        DEBUG_RED_STROKE = r017;
        r017.setColor(org.mvel2.asm.Opcodes.V_PREVIEW);
        DEBUG_RED_STROKE.setStrokeWidth(2.0f);
        Paint r018 = DEBUG_RED_STROKE;
        Paint.Style r139 = Paint.Style.STROKE;
        r018.setStyle(r139);
        Paint r019 = new Paint();
        DEBUG_GREEN_STROKE = r019;
        r019.setColor(-16711936);
        DEBUG_GREEN_STROKE.setStrokeWidth(2.0f);
        DEBUG_GREEN_STROKE.setStyle(r139);
        return;
    L224:
        r853 = false;
        goto L225
    L204:
        if (r65 == (-2)) goto L217;
        if (r65 == 1000001) goto L217;
    L207:
        OverrideWallpaperInfo r46 = new OverrideWallpaperInfo();
        r46.color = r010.getInt("selectedColor", 0);
        r46.slug = r010.getString("selectedBackgroundSlug", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r65 < (-100)) goto L213;
        if (r65 > (-1)) goto L213;
        if (r46.color == 0) goto L213;
        r46.slug = "c";
        r46.fileName = _UrlKt.FRAGMENT_ENCODE_SET;
        r46.originalFileName = _UrlKt.FRAGMENT_ENCODE_SET;
    L214:
        r46.gradientColor1 = r010.getInt("selectedGradientColor", 0);
        r46.gradientColor2 = r010.getInt("selectedGradientColor2", 0);
        r46.gradientColor3 = r010.getInt("selectedGradientColor3", 0);
        r46.rotation = r010.getInt("selectedGradientRotation", 45);
        r46.isBlurred = r010.getBoolean("selectedBackgroundBlurred", false);
        r46.isMotion = r010.getBoolean("selectedBackgroundMotion", false);
        r46.intensity = r010.getFloat("selectedIntensity", 0.5f);
        currentDayTheme.setOverrideWallpaper(r46);
        if (selectedAutoNightType == 0) goto L217;
        currentNightTheme.setOverrideWallpaper(r46);
    L213:
        r46.fileName = "wallpaper.jpg";
        r46.originalFileName = "wallpaper_original.jpg";
        goto L214
    L199:
        if (r010.contains("selectedBackground2") == false) goto L218;
    L195:
        currentDayTheme = r52;
        goto L197
    L191:
        autoNightLocationLongitude = 10000.0d;     // Catch: Exception -> L227
        goto L192
    L187:
        autoNightLocationLatitude = 10000.0d;     // Catch: Exception -> L227
        goto L188
    L83:
        if (r7774.contains("lastDarkTheme") == true) goto L85;
        SharedPreferences.Editor r140 = r7774.edit();     // Catch: Exception -> L227
        r140.putString("lastDarkTheme", currentNightTheme.getKey());     // Catch: Exception -> L227
        r140.apply();     // Catch: Exception -> L227
        goto L85
    L74:
        if ("Dark".equals(r932) == false) goto L76;
        currentNightTheme = r43;     // Catch: Exception -> L227
        r43.currentAccentId = 9;     // Catch: Exception -> L227
        goto L81
    L76:
        if (r932 == null) goto L81;
        ThemeInfo r141 = themesDict.get(r932);     // Catch: Exception -> L227
        if (r141 == null) goto L81;
        currentNightTheme = r141;     // Catch: Exception -> L227
        goto L81
    L61:
        if ("Dark".equals(r5) == false) goto L63;
        r43.currentAccentId = 9;     // Catch: Exception -> L227
        r52 = r43;
        goto L70
    L63:
        if (r5 == null) goto L69;
        r52 = themesDict.get(r5);     // Catch: Exception -> L227
        if (r52 == null) goto L70;
        if (r7774.contains("lastDayTheme") == true) goto L70;
        SharedPreferences.Editor r934 = r7774.edit();     // Catch: Exception -> L227
        r934.putString("lastDayTheme", r52.getKey());     // Catch: Exception -> L227
        r934.apply();     // Catch: Exception -> L227
        goto L70
    L69:
        r52 = null;
    L227:
        e = move-exception;
        FileLog.e(e);
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L234:
        JSONArray r47 = new JSONArray(r07);     // Catch: Exception -> L43
        int r020 = 0;
    L37:
        if (r020 >= r47.length()) goto L56;
        ThemeInfo r935 = ThemeInfo.createWithJson(r47.getJSONObject(r020));     // Catch: Exception -> L43
        if (r935 == null) goto L45;
        otherThemes.add(r935);     // Catch: Exception -> L43
        themes.add(r935);     // Catch: Exception -> L43
        themesDict.put(r935.getKey(), r935);     // Catch: Exception -> L43
        ThemeInfo.m5836$$Nest$mloadWallpapers(r935, r7774);     // Catch: Exception -> L43
    L45:
        r020 = r020 + 1;
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L56
    }

    private static float abs(float r1) {
        if (r1 <= 0.0f) goto L6;
        return r1;
    L6:
        return -r1;
    }

    public static int adaptHSV(int r6, float r7, float r8) {
        float[] r0 = getTempHsv(5);
        Color.colorToHSV(r6, r0);
        float r2 = r0[1];
        if (r2 > 0.1f) goto L5;
    L7:
        r0[2] = MathUtils.clamp(r0[2] + r8, 0.0f, 1.0f);
        return Color.HSVToColor(Color.alpha(r6), r0);
    L5:
        if (r2 >= 0.9f) goto L7;
        r0[1] = MathUtils.clamp(r2 + r7, 0.0f, 1.0f);
        goto L7
    }

    public static int adaptHue(int r4, int r5) {
        float[] r0 = getTempHsv(5);
        Color.colorToHSV(r5, r0);
        float r1 = r0[0];
        float r3 = r0[1];
        Color.colorToHSV(r4, r0);
        r0[0] = r1;
        r0[1] = AndroidUtilities.lerp(r0[1], r3, 0.25f);
        return Color.HSVToColor(Color.alpha(r4), r0);
    }

    private static void addChatDrawable(String r1, Drawable r2, int r3) {
        defaultChatDrawables.put(r1, r2);
        defaultChatDrawableColorKeys.put(r1, Integer.valueOf(r3));
    }

    private static void addChatPaint(String r1, Paint r2, int r3) {
        defaultChatPaints.put(r1, r2);
        defaultChatPaintColors.put(r1, Integer.valueOf(r3));
    }

    private static void applyCalculatedArticleCodeColors(SparseIntArray r6, SparseIntArray r7, boolean r8) {
        if (r8 == false) goto L6;
        int r0 = key_chat_inArticleCodeBackground;
        if (r6.indexOfKey(r0) >= 0) goto L6;
        r7.put(r0, multAlpha(-1, 0.1f));
    L6:
        int r02 = key_chat_inBubble;
        int r03 = r7.get(r02, defaultColors[r02]);
        int r1 = tableOutBubble(r7);
        int r2 = key_chat_inArticleCodeScrollbarBackground;
        if (r6.indexOfKey(r2) >= 0) goto L9;
        r7.put(r2, calculatedArticleCodeScrollbar(r03, r8, false));
    L9:
        int r22 = key_chat_inArticleCodeScrollbar;
        if (r6.indexOfKey(r22) >= 0) goto L12;
        r7.put(r22, calculatedArticleCodeScrollbar(r03, r8, true));
    L12:
        int r23 = key_chat_outArticleCodeScrollbarBackground;
        if (r6.indexOfKey(r23) >= 0) goto L15;
        r7.put(r23, calculatedArticleCodeScrollbar(r1, r8, false));
    L15:
        int r24 = key_chat_outArticleCodeScrollbar;
        if (r6.indexOfKey(r24) >= 0) goto L18;
        r7.put(r24, calculatedArticleCodeScrollbar(r1, r8, true));
    L18:
        int r25 = key_chat_inArticleDetailsArrow;
        if (r6.indexOfKey(r25) >= 0) goto L21;
        r7.put(r25, calculatedArticleDetailsColor(r03, r8, false, true));
    L21:
        int r26 = key_chat_outArticleDetailsArrow;
        if (r6.indexOfKey(r26) >= 0) goto L24;
        r7.put(r26, calculatedArticleDetailsColor(r1, r8, true, true));
    L24:
        int r27 = key_chat_inArticleDetailsLine;
        if (r6.indexOfKey(r27) >= 0) goto L27;
        r7.put(r27, calculatedArticleDetailsColor(r03, r8, false, false));
    L27:
        int r04 = key_chat_outArticleDetailsLine;
        if (r6.indexOfKey(r04) >= 0) goto L31;
        r7.put(r04, calculatedArticleDetailsColor(r1, r8, true, false));
        return;
    }

    private static void applyCalculatedTableColors(SparseIntArray r6, SparseIntArray r7, boolean r8) {
        int r0 = key_chat_inBubble;
        int r02 = r7.get(r0, defaultColors[r0]);
        int r1 = tableOutBubble(r7);
        int r2 = key_chat_inTableBackground;
        if (r6.indexOfKey(r2) >= 0) goto L5;
        r7.put(r2, calculatedTableBackground(r02, r8, false));
    L5:
        int r22 = key_chat_outTableBackground;
        if (r6.indexOfKey(r22) >= 0) goto L8;
        r7.put(r22, calculatedTableBackground(r1, r8, true));
    L8:
        int r23 = key_chat_inTableBorder;
        if (r6.indexOfKey(r23) >= 0) goto L11;
        r7.put(r23, calculatedTableBorder(r02, r8, false));
    L11:
        int r03 = key_chat_outTableBorder;
        if (r6.indexOfKey(r03) >= 0) goto L14;
        r7.put(r03, calculatedTableBorder(r1, r8, true));
    L14:
        int r04 = key_chat_outDivider;
        if (r6.indexOfKey(r04) >= 0) goto L17;
        int r12 = key_chat_outReplyLine;
        r7.put(r04, multAlpha(r7.get(r12, defaultColors[r12]), 0.2f));
    L17:
        if (r8 == false) goto L22;
        int r82 = key_chat_inDivider;
        if (r6.indexOfKey(r82) >= 0) goto L23;
        int r62 = key_chat_inReplyMessageText;
        r7.put(r82, multAlpha(r7.get(r62, defaultColors[r62]), 0.2f));
        return;
    L23:
        return;
    }

    public static void applyChatMessageSelectedBackgroundColor() {
        applyChatMessageSelectedBackgroundColor(null, wallpaper);
    }

    public static void applyChatMessageSelectedBackgroundColor(Drawable r3, Drawable r4) {
        if (chat_messageBackgroundSelectedPaint != null) goto L5;
        return;
    L5:
        int r0 = currentColors.get(key_chat_selectedBackground);
        if (r3 != null) goto L10;
        r3 = r4;
    L10:
        if ((r3 instanceof MotionBackgroundDrawable) == true) goto L12;
    L15:
        boolean r42 = false;
    L16:
        if (r42 == false) goto L23;
        Bitmap r32 = ((MotionBackgroundDrawable) r3).getBitmap();
        if (serviceBitmap == r32) goto L23;
        serviceBitmap = r32;
        Bitmap r1 = serviceBitmap;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        serviceBitmapShader = new BitmapShader(r1, r2, r2);
        if (serviceBitmapMatrix != null) goto L23;
        serviceBitmapMatrix = new Matrix();
    L23:
        if (serviceBitmapShader == null) goto L28;
        if (r0 != 0) goto L28;
        if (r42 == false) goto L28;
        ColorMatrix r33 = new ColorMatrix();
        AndroidUtilities.adjustSaturationColorMatrix(r33, 2.5f);
        AndroidUtilities.multiplyBrightnessColorMatrix(r33, 0.75f);
        chat_messageBackgroundSelectedPaint.setShader(serviceBitmapShader);
        chat_messageBackgroundSelectedPaint.setColorFilter(new ColorMatrixColorFilter(r33));
        chat_messageBackgroundSelectedPaint.setAlpha(64);
        return;
    L28:
        Paint r34 = chat_messageBackgroundSelectedPaint;
        if (r0 != 0) goto L31;
        r0 = TLObject.FLAG_30;
    L31:
        r34.setColor(r0);
        chat_messageBackgroundSelectedPaint.setColorFilter(null);
        chat_messageBackgroundSelectedPaint.setShader(null);
        return;
    L12:
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L15;
        if (r0 != 0) goto L15;
        r42 = true;
        goto L16
    }

    public static void applyChatServiceMessageColor() {
        Drawable r0 = wallpaper;
        if (r0 == null) goto L6;
        applyChatServiceMessageColor(null, null, r0);
        return;
    }

    public static void applyChatServiceMessageColor(int[] r8, Drawable r9, Drawable r10) {
        if (chat_actionBackgroundPaint != null) goto L5;
        return;
    L5:
        serviceMessageColor = serviceMessageColorBackup;
        serviceSelectedMessageColor = serviceSelectedMessageColorBackup;
        if (r8 != null) goto L8;
    L10:
        int r82 = currentColors.indexOfKey(key_chat_serviceBackground);
        if (r82 < 0) goto L14;
        int r83 = currentColors.valueAt(r82);
    L13:
        int r3 = r83;
        int r84 = currentColors.indexOfKey(key_chat_serviceBackgroundSelected);
        if (r84 < 0) goto L18;
        int r85 = currentColors.valueAt(r84);
    L19:
        if (r9 != null) goto L22;
        r9 = r10;
    L22:
        boolean r102 = r9 instanceof MotionBackgroundDrawable;
        if (r102 == true) goto L27;
        if ((r9 instanceof BitmapDrawable) == true) goto L27;
    L45:
        serviceBitmap = null;
        serviceBitmapShader = null;
        Drawable r1 = chat_msgStickerPinnedDrawable;
        int r5 = key_chat_serviceText;
        setDrawableColorByKey(r1, r5);
        setDrawableColorByKey(chat_msgStickerCheckDrawable, r5);
        setDrawableColorByKey(chat_msgStickerHalfCheckDrawable, r5);
        setDrawableColorByKey(chat_msgStickerViewsDrawable, r5);
        setDrawableColorByKey(chat_msgStickerRepliesDrawable, r5);
        chat_actionTextPaint.setColor(getColor(r5));
        chat_actionTextPaint2.setColor(getColor(r5));
        ((android.text.TextPaint) chat_actionTextPaint).linkColor = getColor(key_chat_serviceLink);
        chat_unlockExtendedMediaTextPaint.setColor(getColor(r5));
        Drawable r12 = chat_commentStickerDrawable;
        int r52 = key_chat_serviceIcon;
        setDrawableColorByKey(r12, r52);
        setDrawableColorByKey(chat_shareIconDrawable, r52);
        setDrawableColorByKey(chat_replyIconDrawable, r52);
        setDrawableColorByKey(chat_goIconDrawable, r52);
        setDrawableColorByKey(chat_botInlineDrawable, r52);
        setDrawableColorByKey(chat_botWebViewDrawable, r52);
        setDrawableColorByKey(chat_botLockDrawable, r52);
        setDrawableColorByKey(chat_botInviteDrawable, r52);
        setDrawableColorByKey(chat_botLinkDrawable, r52);
        chat_botButtonPaint.setColor(getColor(key_chat_botButtonText));
    L46:
        chat_actionBackgroundPaint.setColor(r3);
        chat_actionBackgroundSelectedPaint.setColor(r85);
        currentColor = r3;
        if (serviceBitmapShader != null) goto L49;
    L84:
        chat_actionBackgroundPaint.setColorFilter(null);
        chat_actionBackgroundPaint.setShader(null);
        chat_actionBackgroundSelectedPaint.setColorFilter(null);
        chat_actionBackgroundSelectedPaint.setShader(null);
        chat_actionBackgroundGradientDarkenPaint.setAlpha(21);
        return;
    L49:
        if (currentColors.indexOfKey(key_chat_serviceBackground) < 0) goto L53;
        if (r102 == true) goto L53;
        if ((r9 instanceof BitmapDrawable) == false) goto L84;
    L53:
        ColorMatrix r86 = new ColorMatrix();
        if (r102 == false) goto L73;
        float r103 = -0.06f;
        if (((MotionBackgroundDrawable) r9).getIntensity() < 0.0f) goto L65;
        r86.setSaturation(1.6f);
        if (isCurrentThemeDark() == false) goto L60;
        float r92 = 0.97f;
    L61:
        AndroidUtilities.multiplyBrightnessColorMatrix(r86, r92);
        if (isCurrentThemeDark() == false) goto L64;
        r103 = 0.12f;
    L64:
        AndroidUtilities.adjustBrightnessColorMatrix(r86, r103);
    L82:
        chat_actionBackgroundPaint.setFilterBitmap(true);
        chat_actionBackgroundPaint.setShader(serviceBitmapShader);
        chat_actionBackgroundPaint.setColorFilter(new ColorMatrixColorFilter(r86));
        chat_actionBackgroundPaint.setAlpha(255);
        chat_actionBackgroundSelectedPaint.setFilterBitmap(true);
        chat_actionBackgroundSelectedPaint.setShader(serviceBitmapShader);
        ColorMatrix r93 = new ColorMatrix(r86);
        AndroidUtilities.adjustSaturationColorMatrix(r93, 0.26f);
        isCurrentThemeDark();
        AndroidUtilities.multiplyBrightnessColorMatrix(r93, 0.92f);
        chat_actionBackgroundSelectedPaint.setColorFilter(new ColorMatrixColorFilter(r93));
        chat_actionBackgroundSelectedPaint.setAlpha(255);
        chat_actionBackgroundGradientDarkenPaint.setAlpha(0);
        return;
    L60:
        r92 = 0.92f;
        goto L61
    L65:
        r86.setSaturation(1.1f);
        if (isCurrentThemeDark() == false) goto L68;
        float r94 = 0.4f;
    L69:
        AndroidUtilities.multiplyBrightnessColorMatrix(r86, r94);
        if (isCurrentThemeDark() == false) goto L72;
        r103 = 0.08f;
    L72:
        AndroidUtilities.adjustBrightnessColorMatrix(r86, r103);
        goto L82
    L68:
        r94 = 0.8f;
        goto L69
    L73:
        r86.setSaturation(1.6f);
        if (isCurrentThemeDark() == false) goto L76;
        float r95 = 0.9f;
    L77:
        AndroidUtilities.multiplyBrightnessColorMatrix(r86, r95);
        if (isCurrentThemeDark() == false) goto L80;
        float r96 = -0.04f;
    L81:
        AndroidUtilities.adjustBrightnessColorMatrix(r86, r96);
        goto L82
    L80:
        r96 = 0.06f;
        goto L81
    L76:
        r95 = 0.84f;
    L27:
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L45;
        if (LiteMode.isEnabled(32) == false) goto L45;
        if (r102 == false) goto L33;
        Bitmap r53 = ((MotionBackgroundDrawable) r9).getBitmap();
    L37:
        if (serviceBitmap == r53) goto L44;
        serviceBitmap = r53;
        Bitmap r6 = serviceBitmap;
        Shader.TileMode r7 = Shader.TileMode.CLAMP;
        serviceBitmapShader = new BitmapShader(r6, r7, r7);
        if (Build.VERSION.SDK_INT < 33) goto L42;
        serviceBitmapShader.setFilterMode(2);
    L42:
        if (serviceBitmapMatrix != null) goto L44;
        serviceBitmapMatrix = new Matrix();
    L44:
        setDrawableColor(chat_msgStickerPinnedDrawable, -1);
        setDrawableColor(chat_msgStickerCheckDrawable, -1);
        setDrawableColor(chat_msgStickerHalfCheckDrawable, -1);
        setDrawableColor(chat_msgStickerViewsDrawable, -1);
        setDrawableColor(chat_msgStickerRepliesDrawable, -1);
        chat_actionTextPaint.setColor(-1);
        chat_actionTextPaint2.setColor(-1);
        chat_actionTextPaint3.setColor(-1);
        ((android.text.TextPaint) chat_actionTextPaint).linkColor = -1;
        chat_unlockExtendedMediaTextPaint.setColor(-1);
        chat_botButtonPaint.setColor(-1);
        setDrawableColor(chat_commentStickerDrawable, -1);
        setDrawableColor(chat_shareIconDrawable, -1);
        setDrawableColor(chat_replyIconDrawable, -1);
        setDrawableColor(chat_goIconDrawable, -1);
        setDrawableColor(chat_botInlineDrawable, -1);
        setDrawableColor(chat_botWebViewDrawable, -1);
        setDrawableColor(chat_botLockDrawable, -1);
        setDrawableColor(chat_botInviteDrawable, -1);
        setDrawableColor(chat_botLinkDrawable, -1);
        goto L46
    L33:
        if ((r9 instanceof BitmapDrawable) == false) goto L35;
        r53 = checkBlur(r9);
        goto L37
    L35:
        r53 = null;
        goto L37
    L18:
        r85 = serviceSelectedMessageColor;
        goto L19
    L14:
        r83 = serviceMessageColor;
        goto L13
    L8:
        if (r8.length < 2) goto L10;
        r3 = r8[0];
        r85 = r8[1];
        serviceMessageColor = r3;
        serviceSelectedMessageColor = r85;
        goto L19
    }

    public static void applyChatTheme(boolean r7, boolean r8) {
        if (chat_msgTextPaint != null) goto L6;
        return;
    L6:
        if (chat_msgInDrawable == null) goto L31;
        if (r7 == true) goto L32;
        chat_gamePaint.setColor(getColor(key_chat_previewGameText));
        chat_durationPaint.setColor(getColor(key_chat_previewDurationText));
        chat_botButtonPaint.setColor(getColor(key_chat_botButtonText));
        chat_urlPaint.setColor(getColor(key_chat_linkSelectBackground));
        chat_outUrlPaint.setColor(getColor(key_chat_outLinkSelectBackground));
        chat_textSearchSelectionPaint.setColor(getColor(key_chat_textSelectBackground));
        chat_msgErrorPaint.setColor(getColor(key_chat_sentError));
        Paint r72 = chat_statusPaint;
        int r0 = key_chat_status;
        r72.setColor(getColor(r0));
        chat_statusRecordPaint.setColor(getColor(r0));
        TextPaint r73 = chat_actionTextPaint;
        int r02 = key_chat_serviceText;
        r73.setColor(getColor(r02));
        chat_actionTextPaint2.setColor(getColor(r02));
        chat_actionTextPaint3.setColor(getColor(r02));
        ((android.text.TextPaint) chat_actionTextPaint).linkColor = getColor(key_chat_serviceLink);
        chat_unlockExtendedMediaTextPaint.setColor(getColor(r02));
        chat_contextResult_titleTextPaint.setColor(getColor(key_windowBackgroundWhiteBlackText));
        chat_composeBackgroundPaint.setColor(getColor(key_chat_messagePanelBackground));
        chat_timeBackgroundPaint.setColor(getColor(key_chat_mediaTimeBackground));
        setDrawableColorByKey(chat_msgNoSoundDrawable, key_chat_mediaTimeText);
        MessageDrawable r74 = chat_msgInDrawable;
        int r1 = key_chat_inBubble;
        setDrawableColorByKey(r74, r1);
        MessageDrawable r75 = chat_msgInSelectedDrawable;
        int r2 = key_chat_inBubbleSelected;
        setDrawableColorByKey(r75, r2);
        setDrawableColorByKey(chat_msgInMediaDrawable, r1);
        setDrawableColorByKey(chat_msgInMediaSelectedDrawable, r2);
        setDrawableColorByKey(chat_msgOutCheckDrawable, key_chat_outSentCheck);
        setDrawableColorByKey(chat_msgOutCheckSelectedDrawable, key_chat_outSentCheckSelected);
        Drawable r76 = chat_msgOutCheckReadDrawable;
        int r12 = key_chat_outSentCheckRead;
        setDrawableColorByKey(r76, r12);
        Drawable r77 = chat_msgOutCheckReadSelectedDrawable;
        int r22 = key_chat_outSentCheckReadSelected;
        setDrawableColorByKey(r77, r22);
        setDrawableColorByKey(chat_msgOutHalfCheckDrawable, r12);
        setDrawableColorByKey(chat_msgOutHalfCheckSelectedDrawable, r22);
        Drawable r78 = chat_msgMediaCheckDrawable;
        int r13 = key_chat_mediaSentCheck;
        setDrawableColorByKey(r78, r13);
        setDrawableColorByKey(chat_msgMediaHalfCheckDrawable, r13);
        setDrawableColorByKey(chat_msgStickerCheckDrawable, r02);
        setDrawableColorByKey(chat_msgStickerHalfCheckDrawable, r02);
        setDrawableColorByKey(chat_msgStickerViewsDrawable, r02);
        setDrawableColorByKey(chat_msgStickerRepliesDrawable, r02);
        setDrawableColorByKey(chat_msgUnlockDrawable, r02);
        Drawable r79 = chat_shareIconDrawable;
        int r14 = key_chat_serviceIcon;
        setDrawableColorByKey(r79, r14);
        setDrawableColorByKey(chat_replyIconDrawable, r14);
        setDrawableColorByKey(chat_goIconDrawable, r14);
        setDrawableColorByKey(chat_botInlineDrawable, r14);
        setDrawableColorByKey(chat_botWebViewDrawable, r14);
        Drawable r710 = chat_botLockDrawable;
        int r23 = key_chat_lockIcon;
        setDrawableColorByKey(r710, r23);
        setDrawableColorByKey(chat_botInviteDrawable, r14);
        setDrawableColorByKey(chat_botLinkDrawable, r14);
        Drawable r711 = chat_msgInViewsDrawable;
        int r3 = key_chat_inViews;
        setDrawableColorByKey(r711, r3);
        Drawable r712 = chat_msgInViewsSelectedDrawable;
        int r4 = key_chat_inViewsSelected;
        setDrawableColorByKey(r712, r4);
        Drawable r713 = chat_msgOutViewsDrawable;
        int r5 = key_chat_outViews;
        setDrawableColorByKey(r713, r5);
        Drawable r714 = chat_msgOutViewsSelectedDrawable;
        int r6 = key_chat_outViewsSelected;
        setDrawableColorByKey(r714, r6);
        setDrawableColorByKey(chat_msgInRepliesDrawable, r3);
        setDrawableColorByKey(chat_msgInRepliesSelectedDrawable, r4);
        setDrawableColorByKey(chat_msgOutRepliesDrawable, r5);
        setDrawableColorByKey(chat_msgOutRepliesSelectedDrawable, r6);
        setDrawableColorByKey(chat_msgInPinnedDrawable, r3);
        setDrawableColorByKey(chat_msgInPinnedSelectedDrawable, r4);
        setDrawableColorByKey(chat_msgOutPinnedDrawable, r5);
        setDrawableColorByKey(chat_msgOutPinnedSelectedDrawable, r6);
        Drawable r715 = chat_msgMediaPinnedDrawable;
        int r32 = key_chat_mediaViews;
        setDrawableColorByKey(r715, r32);
        setDrawableColorByKey(chat_msgStickerPinnedDrawable, r02);
        setDrawableColorByKey(chat_msgMediaViewsDrawable, r32);
        setDrawableColorByKey(chat_msgMediaRepliesDrawable, r32);
        setDrawableColorByKey(chat_msgInMenuDrawable, key_chat_inMenu);
        setDrawableColorByKey(chat_msgInMenuSelectedDrawable, key_chat_inMenuSelected);
        setDrawableColorByKey(chat_msgOutMenuDrawable, key_chat_outMenu);
        setDrawableColorByKey(chat_msgOutMenuSelectedDrawable, key_chat_outMenuSelected);
        setDrawableColorByKey(chat_msgMediaMenuDrawable, key_chat_mediaMenu);
        setDrawableColorByKey(chat_msgOutInstantDrawable, key_chat_outInstant);
        Drawable r716 = chat_msgInInstantDrawable;
        int r03 = key_chat_inInstant;
        setDrawableColorByKey(r716, r03);
        setDrawableColorByKey(chat_msgErrorDrawable, key_chat_sentErrorIcon);
        setDrawableColorByKey(chat_muteIconDrawable, key_chat_muteIcon);
        setDrawableColorByKey(chat_lockIconDrawable, r23);
        Drawable r717 = chat_inlineResultFile;
        int r24 = key_chat_inlineResultIcon;
        setDrawableColorByKey(r717, r24);
        setDrawableColorByKey(chat_inlineResultAudio, r24);
        setDrawableColorByKey(chat_inlineResultLocation, r24);
        setDrawableColorByKey(chat_commentDrawable, r03);
        setDrawableColorByKey(chat_commentStickerDrawable, r14);
        setDrawableColorByKey(chat_commentArrowDrawable, r03);
        Drawable r718 = chat_gradientLeftDrawable;
        int r04 = key_chat_stickersHintPanel;
        setDrawableColorByKey(r718, r04);
        setDrawableColorByKey(chat_gradientRightDrawable, r04);
        int r05 = 0;
    L10:
        if (r05 >= 2) goto L12;
        setDrawableColorByKey(chat_msgInCallDrawable[r05], key_chat_inInstant);
        setDrawableColorByKey(chat_msgInCallSelectedDrawable[r05], key_chat_inInstantSelected);
        setDrawableColorByKey(chat_msgOutCallDrawable[r05], key_chat_outInstant);
        setDrawableColorByKey(chat_msgOutCallSelectedDrawable[r05], key_chat_outInstantSelected);
        r05 = r05 + 1;
        goto L10
    L12:
        setDrawableColorByKey(chat_msgCallUpGreenDrawable, key_chat_outGreenCall);
        Drawable r06 = chat_msgCallDownRedDrawable;
        int r15 = key_fill_RedNormal;
        setDrawableColorByKey(r06, r15);
        setDrawableColorByKey(chat_msgCallDownGreenDrawable, key_chat_inGreenCall);
        setDrawableColorByKey(calllog_msgCallUpRedDrawable, r15);
        Drawable r07 = calllog_msgCallUpGreenDrawable;
        int r25 = key_calls_callReceivedGreenIcon;
        setDrawableColorByKey(r07, r25);
        setDrawableColorByKey(calllog_msgCallDownRedDrawable, r15);
        setDrawableColorByKey(calllog_msgCallDownGreenDrawable, r25);
        StatusDrawable[] r08 = chat_status_drawables;
        int r16 = r08.length;
        int r26 = 0;
    L13:
        if (r26 >= r16) goto L15;
        setDrawableColorByKey(r08[r26], key_chats_actionMessage);
        r26 = r26 + 1;
        goto L13
    L15:
        int r09 = 0;
    L17:
        if (r09 >= 5) goto L19;
        setCombinedDrawableColor(chat_fileStatesDrawable[r09][0], getColor(key_chat_inLoader), false);
        setCombinedDrawableColor(chat_fileStatesDrawable[r09][0], getColor(key_chat_inMediaIcon), true);
        setCombinedDrawableColor(chat_fileStatesDrawable[r09][1], getColor(key_chat_inLoaderSelected), false);
        setCombinedDrawableColor(chat_fileStatesDrawable[r09][1], getColor(key_chat_inMediaIconSelected), true);
        r09 = r09 + 1;
        goto L17
    L19:
        setCombinedDrawableColor(chat_contactDrawable[0], getColor(key_chat_inContactBackground), false);
        setCombinedDrawableColor(chat_contactDrawable[0], getColor(key_chat_inContactIcon), true);
        setCombinedDrawableColor(chat_contactDrawable[1], getColor(key_chat_outContactBackground), false);
        setCombinedDrawableColor(chat_contactDrawable[1], getColor(key_chat_outContactIcon), true);
        setDrawableColor(chat_locationDrawable[0], getColor(key_chat_inLocationIcon));
        setDrawableColor(chat_locationDrawable[1], getColor(key_chat_outLocationIcon));
        setDrawableColor(chat_pollHintDrawable[0], getColor(key_chat_inPreviewInstantText));
        setDrawableColor(chat_pollHintDrawable[1], getColor(key_chat_outPreviewInstantText));
        setDrawableColor(chat_psaHelpDrawable[0], getColor(key_chat_inViews));
        setDrawableColor(chat_psaHelpDrawable[1], getColor(key_chat_outViews));
        setDrawableColorByKey(chat_composeShadowDrawable, key_chat_messagePanelShadow);
        setDrawableColorByKey(chat_composeShadowRoundDrawable, key_chat_messagePanelBackground);
        int r010 = -1;
        if (getColor(key_chat_outAudioSeekbarFill) != (-1)) goto L22;
        r010 = getColor(key_chat_outBubble);
    L22:
        setDrawableColor(chat_pollCheckDrawable[1], r010);
        setDrawableColor(chat_pollCrossDrawable[1], r010);
        setDrawableColor(chat_attachEmptyDrawable, getColor(key_chat_attachEmptyImage));
        if (r8 == false) goto L25;
        return;
    L25:
        if (disallowChangeServiceMessageColor == true) goto L34;
        applyChatServiceMessageColor();
        applyChatMessageSelectedBackgroundColor();
        return;
    L34:
        return;
    L32:
        return;
    }

    public static void applyCommonTheme() {
        Paint r0 = dividerPaint;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setColor(getColor(key_divider));
        forcedDividerPaint.setColor(getDividerColor(null));
        linkSelectionPaint.setColor(getColor(key_windowBackgroundWhiteLinkSelection));
        Drawable[] r02 = avatarDrawables;
        int r1 = r02.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        setDrawableColorByKey(r02[r3], key_avatar_text);
        r3 = r3 + 1;
        goto L6
    L8:
        dialogs_archiveAvatarDrawable.beginApplyLayerColors();
        RLottieDrawable r03 = dialogs_archiveAvatarDrawable;
        int r12 = key_avatar_backgroundArchived;
        r03.setLayerColor("Arrow1", getNonAnimatedColor(r12));
        dialogs_archiveAvatarDrawable.setLayerColor("Arrow2", getNonAnimatedColor(r12));
        RLottieDrawable r04 = dialogs_archiveAvatarDrawable;
        int r13 = key_avatar_text;
        r04.setLayerColor("Box2", getNonAnimatedColor(r13));
        dialogs_archiveAvatarDrawable.setLayerColor("Box1", getNonAnimatedColor(r13));
        dialogs_archiveAvatarDrawable.commitApplyLayerColors();
        dialogs_archiveAvatarDrawableRecolored = false;
        dialogs_archiveAvatarDrawable.setAllowDecodeSingleFrame(true);
        dialogs_pinArchiveDrawable.beginApplyLayerColors();
        RLottieDrawable r05 = dialogs_pinArchiveDrawable;
        int r14 = key_chats_archiveIcon;
        r05.setLayerColor("Arrow", getNonAnimatedColor(r14));
        dialogs_pinArchiveDrawable.setLayerColor("Line", getNonAnimatedColor(r14));
        dialogs_pinArchiveDrawable.commitApplyLayerColors();
        dialogs_unpinArchiveDrawable.beginApplyLayerColors();
        dialogs_unpinArchiveDrawable.setLayerColor("Arrow", getNonAnimatedColor(r14));
        dialogs_unpinArchiveDrawable.setLayerColor("Line", getNonAnimatedColor(r14));
        dialogs_unpinArchiveDrawable.commitApplyLayerColors();
        dialogs_hidePsaDrawable.beginApplyLayerColors();
        RLottieDrawable r06 = dialogs_hidePsaDrawable;
        int r7 = key_chats_archiveBackground;
        r06.setLayerColor("Line 1", getNonAnimatedColor(r7));
        dialogs_hidePsaDrawable.setLayerColor("Line 2", getNonAnimatedColor(r7));
        dialogs_hidePsaDrawable.setLayerColor("Line 3", getNonAnimatedColor(r7));
        dialogs_hidePsaDrawable.setLayerColor("Cup Red", getNonAnimatedColor(r14));
        dialogs_hidePsaDrawable.setLayerColor("Box", getNonAnimatedColor(r14));
        dialogs_hidePsaDrawable.commitApplyLayerColors();
        dialogs_hidePsaDrawableRecolored = false;
        dialogs_archiveDrawable.beginApplyLayerColors();
        dialogs_archiveDrawable.setLayerColor("Arrow", getNonAnimatedColor(r7));
        dialogs_archiveDrawable.setLayerColor("Box2", getNonAnimatedColor(r14));
        dialogs_archiveDrawable.setLayerColor("Box1", getNonAnimatedColor(r14));
        dialogs_archiveDrawable.commitApplyLayerColors();
        dialogs_archiveDrawableRecolored = false;
        dialogs_unarchiveDrawable.beginApplyLayerColors();
        dialogs_unarchiveDrawable.setLayerColor("Arrow1", getNonAnimatedColor(r14));
        dialogs_unarchiveDrawable.setLayerColor("Arrow2", getNonAnimatedColor(key_chats_archivePinBackground));
        dialogs_unarchiveDrawable.setLayerColor("Box2", getNonAnimatedColor(r14));
        dialogs_unarchiveDrawable.setLayerColor("Box1", getNonAnimatedColor(r14));
        dialogs_unarchiveDrawable.commitApplyLayerColors();
        int r15 = getColor(key_windowBackgroundWhiteBlackText);
        PorterDuff.Mode r2 = PorterDuff.Mode.SRC_IN;
        chat_animatedEmojiTextColorFilter = new PorterDuffColorFilter(r15, r2);
        chat_outAnimatedEmojiTextColorFilter = new PorterDuffColorFilter(getColor(key_chat_messageTextOut), r2);
        PremiumGradient.getInstance().checkIconColors();
    }

    public static void applyDayNightThemeMaybe(boolean r6) {
        if (previousTheme == null) goto L6;
        return;
    L6:
        if (r6 == false) goto L15;
        ThemeInfo r62 = currentTheme;
        ThemeInfo r4 = currentNightTheme;
        if (r62 == r4) goto L32;
        if (r62 == null) goto L13;
        if (r4 != null) goto L12;
        return;
    L12:
        if (r62.isDark() != currentNightTheme.isDark()) goto L13;
        return;
    L13:
        isInNigthMode = true;
        lastThemeSwitchTime = SystemClock.elapsedRealtime();
        switchingNightTheme = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{currentNightTheme, Boolean.TRUE, null, -1});
        switchingNightTheme = false;
        return;
    L32:
        return;
    L15:
        ThemeInfo r63 = currentDayTheme;
        if (r63 != null) goto L18;
    L24:
        ThemeInfo r42 = currentTheme;
        if (r42 == r63) goto L35;
        if (r42 == null) goto L30;
        if (r63 != null) goto L29;
        return;
    L29:
        if (r42.isDark() != r63.isDark()) goto L30;
        return;
    L30:
        isInNigthMode = false;
        lastThemeSwitchTime = SystemClock.elapsedRealtime();
        switchingNightTheme = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r63, Boolean.TRUE, null, -1});
        switchingNightTheme = false;
        return;
    L35:
        return;
    L18:
        if (r63.isDark() == false) goto L24;
        if (selectedAutoNightType == 0) goto L24;
        ThemeInfo r43 = defaultTheme;
        if (r43 == null) goto L24;
        r63 = r43;
        goto L24
    }

    public static void applyDefaultShadow(Paint r4) {
        r4.setShadowLayer(AndroidUtilities.dpf2(1.0f), 0.0f, AndroidUtilities.dpf2(0.33f), default_shadow_color);
    }

    public static void applyDialogsTheme() {
        if (dialogs_namePaint != null) goto L5;
        return;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= 2) goto L9;
        dialogs_namePaint[r0].setColor(getColor(key_chats_name));
        dialogs_nameEncryptedPaint[r0].setColor(getColor(key_chats_secretName));
        TextPaint r1 = dialogs_messagePaint[r0];
        int r2 = getColor(key_chats_message);
        ((android.text.TextPaint) r1).linkColor = r2;
        r1.setColor(r2);
        dialogs_messagePrintingPaint[r0].setColor(getColor(key_chats_actionMessage));
        r0 = r0 + 1;
        goto L7
    L9:
        dialogs_searchNamePaint.setColor(getColor(key_chats_name));
        dialogs_searchNameEncryptedPaint.setColor(getColor(key_chats_secretName));
        TextPaint r02 = dialogs_messageNamePaint;
        int r12 = getColor(key_chats_nameMessage_threeLines);
        ((android.text.TextPaint) r02).linkColor = r12;
        r02.setColor(r12);
        dialogs_tabletSeletedPaint.setColor(getColor(key_chats_tabletSelectedOverlay));
        dialogs_pinnedPaint.setColor(getColor(key_chats_pinnedOverlay));
        dialogs_timePaint.setColor(getColor(key_chats_date));
        dialogs_timePaintBold.setColor(getColor(key_chats_date_bold));
        TextPaint r03 = dialogs_timePaintBoldAccent;
        int r13 = key_telegram_color_text;
        r03.setColor(getColor(r13));
        TextPaint r04 = dialogs_countTextPaint;
        int r22 = key_chats_unreadCounterText;
        r04.setColor(getColor(r22));
        dialogs_countTextPaint2.setColor(getColor(r22));
        TextPaint r05 = dialogs_archiveTextPaint;
        int r23 = key_chats_archiveText;
        r05.setColor(getColor(r23));
        dialogs_archiveTextPaintSmall.setColor(getColor(r23));
        Paint r06 = dialogs_countPaint;
        int r24 = key_chats_unreadCounter;
        r06.setColor(getColor(r24));
        Paint r07 = dialogs_countGrayPaint;
        int r3 = key_chats_unreadCounterMuted;
        r07.setColor(getColor(r3));
        dialogs_actionMessagePaint.setColor(getColor(key_chats_actionMessage));
        dialogs_errorPaint.setColor(getColor(key_chats_sentError));
        dialogs_onlinePaint.setColor(getColor(key_windowBackgroundWhiteBlueText3));
        dialogs_offlinePaint.setColor(getColor(key_windowBackgroundWhiteGrayText3));
        setDrawableColorByKey(dialogs_lockDrawable, key_chats_secretIcon);
        Drawable r08 = dialogs_lock2Drawable;
        int r4 = key_chats_pinnedIcon;
        setDrawableColorByKey(r08, r4);
        setDrawableColorByKey(dialogs_checkDrawable, key_chats_sentCheck);
        setDrawableColorByKey(dialogs_communityCardsDrawable, key_windowBackgroundWhiteBlackText);
        Drawable r09 = dialogs_checkReadDrawable;
        int r5 = key_chats_sentReadCheck;
        setDrawableColorByKey(r09, r5);
        setDrawableColorByKey(dialogs_halfCheckDrawable, r5);
        setDrawableColorByKey(dialogs_clockDrawable, key_chats_sentClock);
        setDrawableColorByKey(dialogs_errorDrawable, key_chats_sentErrorIcon);
        setDrawableColorByKey(dialogs_pinnedDrawable, r4);
        setDrawableColorByKey(dialogs_pinnedDrawable2, r4);
        setDrawableColorByKey(dialogs_pinnedDrawable2Accent, r13);
        setDrawableColorByKey(dialogs_reorderDrawable, r4);
        Drawable r010 = dialogs_muteDrawable;
        int r14 = key_chats_muteIcon;
        setDrawableColorByKey(r010, r14);
        setDrawableColorByKey(dialogs_unmuteDrawable, r14);
        setDrawableColorByKey(dialogs_hiddenDrawable, r14);
        setDrawableColorByKey(dialogs_mentionDrawable, r24);
        setDrawableColorByKey(dialogs_reactionsMentionDrawable, key_dialogReactionMentionBackground);
        setDrawableColorByKey(dialogs_pollMentionDrawable, key_color_purple);
        setDrawableColorByKey(dialogs_mentionDrawableMuted, r3);
        setDrawableColorByKey(dialogs_reactionsMentionDrawableMuted, r3);
        setDrawableColorByKey(dialogs_pollMentionDrawableMuted, r3);
        setDrawableColorByKey(dialogs_forum_arrowDrawable, key_chats_message);
        setDrawableColorByKey(dialogs_verifiedDrawable, key_chats_verifiedBackground);
        setDrawableColorByKey(dialogs_verifiedCheckDrawable, key_chats_verifiedCheck);
        setDrawableColorByKey(dialogs_holidayDrawable, key_actionBarDefaultTitle);
        ScamDrawable r011 = dialogs_scamDrawable;
        int r15 = key_chats_draft;
        setDrawableColorByKey(r011, r15);
        setDrawableColorByKey(dialogs_fakeDrawable, r15);
    }

    public static void applyPreviousTheme() {
        ThemeInfo r0 = previousTheme;
        if (r0 != null) goto L6;
        return;
    L6:
        if (currentTheme == r0) goto L8;
        boolean r1 = true;
    L9:
        hasPreviousTheme = false;
        if (isInNigthMode == false) goto L15;
        ThemeInfo r4 = currentNightTheme;
        if (r4 == null) goto L15;
        applyTheme(r4, true, false, true);
    L18:
        isApplyingAccent = false;
        previousTheme = null;
        checkAutoNightThemeConditions();
        return;
    L15:
        if (isApplyingAccent == false) goto L17;
        if (r1 == false) goto L18;
    L17:
        applyTheme(r0, true, false, false);
        goto L18
    L8:
        r1 = false;
        goto L9
    }

    public static void applyProfileTheme() {
        if (profile_verifiedDrawable != null) goto L5;
        return;
    L5:
        profile_aboutTextPaint.setColor(getColor(key_windowBackgroundWhiteBlackText));
        TextPaint r0 = profile_aboutTextPaint;
        ((android.text.TextPaint) r0).linkColor = getColor(key_windowBackgroundWhiteLinkText);
        setDrawableColorByKey(profile_verifiedDrawable, key_profile_verifiedBackground);
        setDrawableColorByKey(profile_verifiedCheckDrawable, key_profile_verifiedCheck);
    }

    public static void applyServiceShaderMatrix(int r7, int r8, float r9, float r10) {
        applyServiceShaderMatrix(serviceBitmap, serviceBitmapShader, serviceBitmapMatrix, r7, r8, r9, r10);
    }

    public static void applyServiceShaderMatrix(Bitmap r3, BitmapShader r4, Matrix r5, int r6, int r7, float r8, float r9) {
        if (r4 == null) goto L7;
        if (r5 == null) goto L8;
        float r0 = r3.getWidth();
        float r32 = r3.getHeight();
        float r62 = r6;
        float r72 = r7;
        float r1 = Math.max(r62 / r0, r72 / r32);
        r5.reset();
        r5.setTranslate(((r62 - (r0 * r1)) / 2.0f) - r8, ((r72 - (r32 * r1)) / 2.0f) - r9);
        r5.preScale(r1, r1);
        r4.setLocalMatrix(r5);
        return;
    L8:
        return;
    }

    public static void applyServiceShaderMatrixForView(View r1, View r2) {
        applyServiceShaderMatrixForView(r1, r2, null);
    }

    public static void applyServiceShaderMatrixForView(View r6, View r7, ResourcesProvider r8) {
        if (r6 == null) goto L18;
        if (r7 == null) goto L19;
        r6.getLocationOnScreen(viewPos);
        int[] r62 = viewPos;
        int r0 = r62[0];
        int r2 = r62[1];
        r7.getLocationOnScreen(r62);
        if ((r7 instanceof ThemePreviewActivity.BackgroundView) == false) goto L13;
        Bitmap r63 = serviceBitmap;
        if (r63 == null) goto L11;
        float r64 = r63.getWidth();
        int r65 = (int) (r0 + (((r7.getMeasuredWidth() - (r64 * Math.max(r7.getMeasuredWidth() / r64, r7.getMeasuredHeight() / serviceBitmap.getHeight()))) / 2.0f) - ((ThemePreviewActivity.BackgroundView) r7).tx));
    L10:
        r0 = r65;
        r2 = (int) (r2 + (-((ThemePreviewActivity.BackgroundView) r7).ty));
        goto L13
    L11:
        r65 = (int) (r0 + (-((ThemePreviewActivity.BackgroundView) r7).tx));
    L13:
        if (r8 == null) goto L16;
        r8.applyServiceShaderMatrix(r7.getMeasuredWidth(), r7.getMeasuredHeight(), r0, r2 - viewPos[1]);
        return;
    L16:
        applyServiceShaderMatrix(r7.getMeasuredWidth(), r7.getMeasuredHeight(), r0, r2 - viewPos[1]);
        return;
    L19:
        return;
    }

    public static void applyTheme(ThemeInfo r2) {
        applyTheme(r2, true, true, false);
    }

    public static void applyTheme(ThemeInfo r1, boolean r2) {
        applyTheme(r1, true, r2);
    }

    public static void applyTheme(ThemeInfo r1, boolean r2, boolean r3) {
        applyTheme(r1, r2, true, r3);
    }

    private static void applyTheme(ThemeInfo r7, boolean r8, boolean r9, boolean r10) {
        if (r7 == null) goto L115;
        ThemeEditorView r92 = ThemeEditorView.getInstance();
        if (r92 == null) goto L99;
        r92.destroy();
    L99:
    L17:
        e = move-exception;
        FileLog.e(e);
    L86:
        BlurSettingsBottomSheet.onThemeApplyed();
        if (previousTheme != null) goto L112;
        if (r8 == true) goto L90;
        return;
    L90:
        if (switchingNightTheme == true) goto L114;
        MessagesController.getInstance(r7.account).saveTheme(r7, r7.getAccent(false), r10, false);
        return;
    L114:
        return;
    L112:
        return;
    L10:
        if (r7.pathToFile == null) goto L12;
    L20:
        if (r10 == true) goto L23;
        if (r8 == false) goto L23;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();     // Catch: Exception -> L17
        r0.putString("theme", r7.getKey());     // Catch: Exception -> L17
        r0.apply();     // Catch: Exception -> L17
    L23:
        String[] r1 = new String[1];     // Catch: Exception -> L17
        String r3 = r7.assetName;     // Catch: Exception -> L17
        if (r3 == null) goto L26;
        currentColorsNoAccent = getThemeFileValues(null, r3, null);     // Catch: Exception -> L17
    L27:
        themedWallpaperFileOffset = currentColorsNoAccent.get(key_wallpaperFileOffset, -1);     // Catch: Exception -> L17
        if (TextUtils.isEmpty(r1[0]) == true) goto L104;
        themedWallpaperLink = r1[0];     // Catch: Exception -> L17
        String r12 = new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(themedWallpaperLink) + ".wp").getAbsolutePath();     // Catch: Exception -> L17
        String r2 = r7.pathToWallpaper;     // Catch: Exception -> L94
        if (r2 == null) goto L35;
        if (r2.equals(r12) == true) goto L35;
        new File(r7.pathToWallpaper).delete();     // Catch: Exception -> L94
    L35:
        r7.pathToWallpaper = r12;     // Catch: Exception -> L17
        Uri r13 = Uri.parse(themedWallpaperLink);     // Catch: Throwable -> L43
        r7.slug = r13.getQueryParameter("slug");     // Catch: Throwable -> L43
        String r22 = r13.getQueryParameter("mode");     // Catch: Throwable -> L43
        if (r22 == null) goto L49;
        String[] r23 = r22.toLowerCase().split(" ");     // Catch: Throwable -> L43
        int r32 = r23.length;     // Catch: Throwable -> L43
        int r4 = 0;
    L39:
        if (r4 >= r32) goto L49;
        String r5 = r23[r4];     // Catch: Throwable -> L43
        if ("blur".equals(r5) == false) goto L46;
        r7.isBlured = true;     // Catch: Throwable -> L43
    L48:
        r4 = r4 + 1;     // Catch: Throwable -> L43
        goto L39
    L46:
        if ("motion".equals(r5) == false) goto L48;
        r7.isMotion = true;     // Catch: Throwable -> L43
    L49:
        r7.patternBgGradientRotation = 45;     // Catch: Throwable -> L43
        String r02 = r13.getQueryParameter("bg_color");     // Catch: Throwable -> L43 Exception -> L95
        if (TextUtils.isEmpty(r02) == true) goto L101;
        r7.patternBgColor = Integer.parseInt(r02.substring(0, 6), 16) | (-16777216);     // Catch: Throwable -> L43 Exception -> L95
        if (r02.length() < 13) goto L58;
        if (AndroidUtilities.isValidWallChar(r02.charAt(6)) == false) goto L58;
        r7.patternBgGradientColor1 = Integer.parseInt(r02.substring(7, 13), 16) | (-16777216);     // Catch: Throwable -> L43 Exception -> L95
    L58:
        if (r02.length() < 20) goto L63;
        if (AndroidUtilities.isValidWallChar(r02.charAt(13)) == false) goto L63;
        r7.patternBgGradientColor2 = Integer.parseInt(r02.substring(14, 20), 16) | (-16777216);     // Catch: Throwable -> L43 Exception -> L95
    L63:
        if (r02.length() != 27) goto L101;
        if (AndroidUtilities.isValidWallChar(r02.charAt(20)) == false) goto L101;
        r7.patternBgGradientColor3 = Integer.parseInt(r02.substring(21), 16) | (-16777216);     // Catch: Throwable -> L43 Exception -> L95
    L101:
        String r03 = r13.getQueryParameter("rotation");     // Catch: Throwable -> L43 Exception -> L93
        if (TextUtils.isEmpty(r03) == true) goto L77;
        r7.patternBgGradientRotation = Utilities.parseInt(r03);     // Catch: Throwable -> L43 Exception -> L93
    L77:
        if (r10 == false) goto L79;
    L83:
        currentTheme = r7;     // Catch: Exception -> L17
        refreshThemeColors();     // Catch: Exception -> L17
        goto L86
    L79:
        if (previousTheme != null) goto L83;
        currentDayTheme = r7;     // Catch: Exception -> L17
        if (isCurrentThemeNight() == false) goto L83;
        switchNightThemeDelay = CastStatusCodes.AUTHENTICATION_FAILED;     // Catch: Exception -> L17
        lastDelayUpdateTime = SystemClock.elapsedRealtime();     // Catch: Exception -> L17
        AndroidUtilities.runOnUIThread(new MessagesController$$ExternalSyntheticLambda83(), 2100);     // Catch: Exception -> L17
    L43:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L17
        goto L77
    L104:
        if (r7.pathToWallpaper == null) goto L76;
        new File(r7.pathToWallpaper).delete();     // Catch: Exception -> L96
    L76:
        r7.pathToWallpaper = null;     // Catch: Exception -> L17
        themedWallpaperLink = null;     // Catch: Exception -> L17
        goto L77
    L26:
        currentColorsNoAccent = getThemeFileValues(new File(r7.pathToFile), null, r1);     // Catch: Exception -> L17
        goto L27
    L12:
        if (r7.assetName != null) goto L20;
        if (r10 == true) goto L19;
        if (r8 == false) goto L19;
        SharedPreferences.Editor r04 = MessagesController.getGlobalMainSettings().edit();     // Catch: Exception -> L17
        r04.remove("theme");     // Catch: Exception -> L17
        r04.commit();     // Catch: Exception -> L17
    L19:
        currentColorsNoAccent.clear();     // Catch: Exception -> L17
        themedWallpaperFileOffset = 0;     // Catch: Exception -> L17
        themedWallpaperLink = null;     // Catch: Exception -> L17
        wallpaper = null;     // Catch: Exception -> L17
        themedWallpaper = null;     // Catch: Exception -> L17
        goto L77
    }

    public static ThemeInfo applyThemeFile(File r6, String r7, TLRPC.TL_theme r8, boolean r9) {
    L6:
        e = move-exception;
        FileLog.e(e);
        return null;
    L4:
        if (r7.toLowerCase().endsWith(".attheme") == true) goto L9;
        r7 = r7.concat(".attheme");     // Catch: Exception -> L6
    L9:
        if (r9 == false) goto L12;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.goingToPreviewTheme, new Object[0]);     // Catch: Exception -> L6
        ThemeInfo r92 = new ThemeInfo();     // Catch: Exception -> L6
        r92.name = r7;     // Catch: Exception -> L6
        r92.info = r8;     // Catch: Exception -> L6
        r92.pathToFile = r6.getAbsolutePath();     // Catch: Exception -> L6
        r92.account = UserConfig.selectedAccount;     // Catch: Exception -> L6
        applyThemeTemporary(r92, false);     // Catch: Exception -> L6
        return r92;
    L12:
        if (r8 == null) goto L14;
        String r93 = "remote" + r8.id;     // Catch: Exception -> L6
        File r1 = new File(ApplicationLoader.getFilesDirFixed(), r93.concat(".attheme"));     // Catch: Exception -> L6
    L16:
        if (AndroidUtilities.copyFile(r6, r1) == true) goto L19;
        applyPreviousTheme();     // Catch: Exception -> L6
        return null;
    L19:
        previousTheme = null;     // Catch: Exception -> L6
        hasPreviousTheme = false;     // Catch: Exception -> L6
        isApplyingAccent = false;     // Catch: Exception -> L6
        ThemeInfo r62 = themesDict.get(r93);     // Catch: Exception -> L6
        if (r62 != null) goto L22;
        r62 = new ThemeInfo();     // Catch: Exception -> L6
        r62.name = r7;     // Catch: Exception -> L6
        r62.account = UserConfig.selectedAccount;     // Catch: Exception -> L6
        themes.add(r62);     // Catch: Exception -> L6
        otherThemes.add(r62);     // Catch: Exception -> L6
        sortThemes();     // Catch: Exception -> L6
    L23:
        r62.info = r8;     // Catch: Exception -> L6
        r62.pathToFile = r1.getAbsolutePath();     // Catch: Exception -> L6
        themesDict.put(r62.getKey(), r62);     // Catch: Exception -> L6
        saveOtherThemes(true);     // Catch: Exception -> L6
        applyTheme(r62, true, true, false);     // Catch: Exception -> L6
        return r62;
    L22:
        themesDict.remove(r93);     // Catch: Exception -> L6
        goto L23
    L14:
        r1 = new File(ApplicationLoader.getFilesDirFixed(), r7);     // Catch: Exception -> L6
        r93 = r7;
        goto L16
    }

    public static void applyThemeInBackground(ThemeInfo r1, boolean r2, Runnable r3) {
        applyThemeInBackground(r1, true, true, r2, r3);
    }

    private static void applyThemeInBackground(ThemeInfo r9, boolean r10, boolean r11, boolean r12, Runnable r13) {
        if (r9 != null) goto L6;
        if (r13 == null) goto L54;
        r13.run();
        return;
    L54:
        return;
    L6:
        ThemeEditorView r112 = ThemeEditorView.getInstance();
        if (r112 == null) goto L52;
        r112.destroy();
    L52:
    L19:
        e = e;
        ThemeInfo r5 = r9;
        boolean r7 = r10;
        boolean r6 = r12;
        Runnable r8 = r13;
    L42:
        FileLog.e(e);
    L44:
        if (previousTheme != null) goto L49;
        if (r7 == false) goto L49;
        if (switchingNightTheme == true) goto L49;
        MessagesController.getInstance(r5.account).saveTheme(r5, r5.getAccent(false), r6, false);
    L49:
        if (r8 == null) goto L55;
        r8.run();
        return;
    L55:
        return;
    L12:
        if (r9.pathToFile == null) goto L14;
    L29:
        if (r12 == true) goto L32;
        if (r10 == false) goto L32;
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();     // Catch: Exception -> L19
        r0.putString("theme", r9.getKey());     // Catch: Exception -> L19
        r0.apply();     // Catch: Exception -> L19
    L32:
        String[] r4 = new String[1];     // Catch: Exception -> L19
        r5 = r9;
        r7 = r10;
        r6 = r12;
        r8 = r13;
        Theme$$ExternalSyntheticLambda10 r3 = new Theme$$ExternalSyntheticLambda10(r4, r5, r6, r7, r8);     // Catch: Exception -> L38
        String r92 = r5.assetName;     // Catch: Exception -> L38
        if (r92 == null) goto L40;
        getThemeFileValuesInBackground(null, r92, null, new Theme$$ExternalSyntheticLambda11(r3));     // Catch: Exception -> L38
        return;
    L40:
        getThemeFileValuesInBackground(new File(r5.pathToFile), null, r4, new Theme$$ExternalSyntheticLambda12(r3));     // Catch: Exception -> L38
        return;
    L38:
        e = e;
        goto L42
    L14:
        if (r9.assetName != null) goto L29;
        if (r12 == true) goto L21;
        if (r10 == false) goto L21;
        SharedPreferences.Editor r02 = MessagesController.getGlobalMainSettings().edit();     // Catch: Exception -> L19
        r02.remove("theme");     // Catch: Exception -> L19
        r02.apply();     // Catch: Exception -> L19
    L21:
        currentColorsNoAccent.clear();     // Catch: Exception -> L19
        themedWallpaperFileOffset = 0;     // Catch: Exception -> L19
        themedWallpaperLink = null;     // Catch: Exception -> L19
        wallpaper = null;     // Catch: Exception -> L19
        themedWallpaper = null;     // Catch: Exception -> L19
        if (r12 == false) goto L24;
    L28:
        currentTheme = r9;     // Catch: Exception -> L19
        refreshThemeColors();     // Catch: Exception -> L19
        r5 = r9;
        r7 = r10;
        r6 = r12;
        r8 = r13;
        goto L44
    L24:
        if (previousTheme != null) goto L28;
        currentDayTheme = r9;     // Catch: Exception -> L19
        if (isCurrentThemeNight() == false) goto L28;
        switchNightThemeDelay = CastStatusCodes.AUTHENTICATION_FAILED;     // Catch: Exception -> L19
        lastDelayUpdateTime = SystemClock.elapsedRealtime();     // Catch: Exception -> L19
        AndroidUtilities.runOnUIThread(new MessagesController$$ExternalSyntheticLambda83(), 2100);     // Catch: Exception -> L19
        goto L28
    }

    public static void applyThemeTemporary(ThemeInfo r1, boolean r2) {
        previousTheme = getCurrentTheme();
        hasPreviousTheme = true;
        isApplyingAccent = r2;
        applyTheme(r1, false, false, false);
    }

    public static int blendOver(int r8, int r9) {
        float r0 = Color.alpha(r9) / 255.0f;
        float r2 = Color.alpha(r8) / 255.0f;
        float r3 = 1.0f - r0;
        float r4 = (r2 * r3) + r0;
        if (r4 != 0.0f) goto L7;
        return 0;
    L7:
        return Color.argb((int) (255.0f * r4), (int) (((Color.red(r9) * r0) + ((Color.red(r8) * r2) * r3)) / r4), (int) (((Color.green(r9) * r0) + ((Color.green(r8) * r2) * r3)) / r4), (int) (((Color.blue(r9) * r0) + ((Color.blue(r8) * r2) * r3)) / r4));
    }

    private static void calcBackgroundColor(Drawable r1, int r2) {
        if (r2 == 2) goto L6;
        int[] r12 = AndroidUtilities.calcDrawableColor(r1);
        int r22 = r12[0];
        serviceMessageColorBackup = r22;
        serviceMessageColor = r22;
        int r13 = r12[1];
        serviceSelectedMessageColorBackup = r13;
        serviceSelectedMessageColor = r13;
        return;
    }

    private static int calculatedArticleCodeScrollbar(int r1, boolean r2, boolean r3) {
        if (r2 == false) goto L8;
        if (r3 == false) goto L5;
        float r12 = 0.22f;
    L7:
        return multAlpha(-1, r12);
    L5:
        r12 = 0.12f;
        goto L7
    L8:
        if (r3 == false) goto L10;
        int r22 = -3813931;
    L11:
        float[] r32 = getTempHsv(3);
        Color.colorToHSV(r1, r32);
        if (r32[1] > 0.02f) goto L14;
        return r22;
    L14:
        return adaptHue(r22, r1);
    L10:
        r22 = -1972501;
        goto L11
    }

    private static int calculatedArticleDetailsColor(int r0, boolean r1, boolean r2, boolean r3) {
        if (r1 == false) goto L8;
        if (r3 == false) goto L5;
        float r02 = 0.62f;
    L7:
        return multAlpha(-1, r02);
    L5:
        r02 = 0.18f;
        goto L7
    L8:
        if (r3 == false) goto L10;
        int r12 = -6380376;
    L11:
        float[] r32 = getTempHsv(3);
        Color.colorToHSV(r0, r32);
        if (r2 == true) goto L14;
    L17:
        return r12;
    L14:
        if (r32[1] <= 0.02f) goto L17;
        return adaptHue(r12, r0);
    L10:
        r12 = -2565928;
        goto L11
    }

    private static int calculatedTableBackground(int r6, boolean r7, boolean r8) {
        if (r7 == false) goto L7;
        if (r8 == false) goto L7;
        return multAlpha(-1, 0.07f);
    L7:
        float[] r1 = getTempHsv(3);
        Color.colorToHSV(r6, r1);
        if (r7 == false) goto L12;
        r1[2] = Math.min(1.0f, r1[2] + 0.07f);
        if (r8 == false) goto L22;
        r1[1] = Math.min(1.0f, r1[1] + 0.02f);
    L22:
        return Color.HSVToColor(Color.alpha(r6), r1);
    L12:
        float r72 = r1[2];
        if (r8 == false) goto L15;
        float r0 = 0.06f;
    L16:
        r1[2] = Math.max(0.0f, r72 - r0);
        if (r8 == false) goto L22;
        float r73 = r1[1];
        if (r73 <= 0.02f) goto L22;
        r1[1] = Math.min(1.0f, r73 + 0.02f);
        goto L22
    L15:
        r0 = 0.03f;
        goto L16
    }

    private static int calculatedTableBorder(int r5, boolean r6, boolean r7) {
        float r0 = 0.14f;
        if (r6 == false) goto L7;
        if (r7 == false) goto L7;
        return multAlpha(-1, 0.14f);
    L7:
        float[] r1 = getTempHsv(3);
        Color.colorToHSV(r5, r1);
        if (r6 == false) goto L12;
        r1[2] = Math.min(1.0f, r1[2] + 0.14f);
        if (r7 == false) goto L22;
        r1[1] = Math.min(1.0f, r1[1] + 0.03f);
    L22:
        return Color.HSVToColor(Color.alpha(r5), r1);
    L12:
        float r62 = r1[2];
        if (r7 == true) goto L16;
        r0 = 0.12f;
    L16:
        r1[2] = Math.max(0.0f, r62 - r0);
        if (r7 == false) goto L22;
        float r63 = r1[1];
        if (r63 <= 0.02f) goto L22;
        r1[1] = Math.min(1.0f, r63 + 0.04f);
        goto L22
    }

    public static boolean canStartHolidayAnimation() {
        if (canStartHolidayAnimation == false) goto L5;
        return true;
    L5:
        if (ExteraConfig.getForceSnow() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static void cancelAutoNightThemeCallbacks() {
        if (selectedAutoNightType != 2) goto L5;
        return;
    L5:
        if (switchNightRunnableScheduled == false) goto L8;
        switchNightRunnableScheduled = false;
        AndroidUtilities.cancelRunOnUIThread(switchNightBrightnessRunnable);
    L8:
        if (switchDayRunnableScheduled == false) goto L11;
        switchDayRunnableScheduled = false;
        AndroidUtilities.cancelRunOnUIThread(switchDayBrightnessRunnable);
    L11:
        if (lightSensorRegistered == false) goto L17;
        lastBrightnessValue = 1.0f;
        sensorManager.unregisterListener(ambientSensorListener, lightSensor);
        lightSensorRegistered = false;
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.d("light sensor unregistered");
        return;
    L18:
        return;
    }

    private static int changeBrightness(int r4, float r5) {
        int r0 = (int) (Color.red(r4) * r5);
        int r1 = (int) (Color.green(r4) * r5);
        int r52 = (int) (Color.blue(r4) * r5);
        int r3 = 0;
        if (r0 >= 0) goto L5;
        int r02 = 0;
    L6:
        if (r1 >= 0) goto L8;
        int r12 = 0;
    L9:
        if (r52 < 0) goto L13;
        r3 = Math.min(r52, 255);
    L13:
        return Color.argb(Color.alpha(r4), r02, r12, r3);
    L8:
        r12 = Math.min(r1, 255);
        goto L9
    L5:
        r02 = Math.min(r0, 255);
        goto L6
    }

    public static int changeColorAccent(int r3) {
        int r1 = 0;
        ThemeAccent r0 = currentTheme.getAccent(false);
        ThemeInfo r2 = currentTheme;
        if (r0 == null) goto L6;
        r1 = r0.accentColor;
    L6:
        return changeColorAccent(r2, r1, r3);
    }

    public static int changeColorAccent(int r2, int r3, int r4, boolean r5, int r6) {
        float[] r0 = getTempHsv(3);
        float[] r1 = getTempHsv(4);
        Color.colorToHSV(r2, r0);
        Color.colorToHSV(r3, r1);
        return changeColorAccent(r0, r1, r4, r5, r6);
    }

    public static int changeColorAccent(ThemeInfo r3, int r4, int r5) {
        if (r4 == 0) goto L13;
        int r0 = r3.accentBaseColor;
        if (r0 == 0) goto L13;
        if (r4 == r0) goto L13;
        if (r3.firstAccentIsDefault == true) goto L9;
    L11:
        float[] r02 = getTempHsv(3);
        float[] r1 = getTempHsv(4);
        Color.colorToHSV(r3.accentBaseColor, r02);
        Color.colorToHSV(r4, r1);
        return changeColorAccent(r02, r1, r5, r3.isDark(), r5);
    L9:
        if (r3.currentAccentId != DEFALT_THEME_ACCENT_ID) goto L11;
    L13:
        return r5;
    }

    public static int changeColorAccent(float[] r6, float[] r7, int r8, boolean r9, int r10) {
        if (tmpHSV5 != null) goto L5;
        tmpHSV5 = new float[3];
    L5:
        float[] r0 = tmpHSV5;
        Color.colorToHSV(r8, r0);
        if (Math.min(abs(r0[0] - r6[0]), abs((r0[0] - r6[0]) - 360.0f)) <= 30.0f) goto L8;
        return r10;
    L8:
        float r3 = Math.min((r0[1] * 1.5f) / r6[1], 1.0f);
        r0[0] = (r0[0] + r7[0]) - r6[0];
        r0[1] = (r0[1] * r7[1]) / r6[1];
        r0[2] = r0[2] * ((1.0f - r3) + ((r3 * r7[2]) / r6[2]));
        int r62 = Color.HSVToColor(Color.alpha(r8), r0);
        float r72 = AndroidUtilities.computePerceivedBrightness(r8);
        float r82 = AndroidUtilities.computePerceivedBrightness(r62);
        if (r9 == false) goto L15;
        if (r72 > r82) goto L17;
        return r62;
    L17:
        return changeBrightness(r62, ((0.39999998f * r72) / r82) + 0.6f);
    L15:
        if (r72 < r82) goto L17;
        return r62;
    }

    public static void checkAutoNightThemeConditions() {
        checkAutoNightThemeConditions(false);
    }

    public static void checkAutoNightThemeConditions(boolean r4) {
        if (previousTheme == null) goto L5;
        return;
    L5:
        if (changingWallpaper == true) goto L32;
        if (r4 == false) goto L9;
    L13:
        boolean r0 = false;
        if (r4 == true) goto L16;
    L21:
        cancelAutoNightThemeCallbacks();
        int r1 = needSwitchToTheme();
        if (r1 != 0) goto L24;
    L27:
        if (r4 == false) goto L31;
        lastThemeSwitchTime = 0;
        return;
    L31:
        return;
    L24:
        if (r1 != 2) goto L26;
        r0 = true;
    L26:
        applyDayNightThemeMaybe(r0);
        goto L27
    L16:
        if (switchNightRunnableScheduled == false) goto L19;
        switchNightRunnableScheduled = false;
        AndroidUtilities.cancelRunOnUIThread(switchNightBrightnessRunnable);
    L19:
        if (switchDayRunnableScheduled == false) goto L21;
        switchDayRunnableScheduled = false;
        AndroidUtilities.cancelRunOnUIThread(switchDayBrightnessRunnable);
        goto L21
    L9:
        if (switchNightThemeDelay <= 0) goto L13;
        long r02 = SystemClock.elapsedRealtime();
        long r2 = r02 - lastDelayUpdateTime;
        lastDelayUpdateTime = r02;
        int r03 = (int) (switchNightThemeDelay - r2);
        switchNightThemeDelay = r03;
        if (r03 <= 0) goto L13;
        return;
    }

    private static Bitmap checkBlur(Drawable r4) {
        WeakReference<Drawable> r0 = lastDrawableToBlur;
        if (r0 != null) goto L5;
    L8:
        WeakReference<Drawable> r02 = lastDrawableToBlur;
        if (r02 == null) goto L11;
        r02.clear();
    L11:
        lastDrawableToBlur = null;
        if (r4 != null) goto L14;
    L20:
        blurredBitmap = null;
        return null;
    L14:
        if (r4.getIntrinsicWidth() == 0) goto L20;
        if (r4.getIntrinsicHeight() == 0) goto L20;
        lastDrawableToBlur = new WeakReference(r4);
        int r03 = (int) ((r4.getIntrinsicWidth() / r4.getIntrinsicHeight()) * 24.0f);
        Bitmap r1 = Bitmap.createBitmap(r03, 24, Bitmap.Config.ARGB_8888);
        r4.setBounds(0, 0, r03, 24);
        ColorFilter r04 = r4.getColorFilter();
        ColorMatrix r2 = new ColorMatrix();
        r2.setSaturation(1.3f);
        AndroidUtilities.multiplyBrightnessColorMatrix(r2, 0.94f);
        r4.setColorFilter(new ColorMatrixColorFilter(r2));
        r4.draw(new Canvas(r1));
        r4.setColorFilter(r04);
        Utilities.blurBitmap(r1, 3);
        blurredBitmap = r1;
        return r1;
    L5:
        if (r0.get() != r4) goto L8;
        return blurredBitmap;
    }

    public static void checkCurrentRemoteTheme(boolean r9) {
        if (loadingCurrentTheme != 0) goto L30;
        if (r9 == false) goto L6;
    L8:
        int r0 = 0;
    L10:
        if (r0 >= 2) goto L39;
        if (r0 != 0) goto L13;
        ThemeInfo r1 = currentDayTheme;
    L14:
        if (r1 == null) goto L29;
        if (UserConfig.getInstance(r1.account).isClientActivated() == false) goto L29;
        ThemeAccent r2 = r1.getAccent(false);
        TLRPC.TL_theme r3 = r1.info;
        if (r3 == null) goto L21;
        int r4 = r1.account;
    L26:
        if (r3.document == null) goto L29;
        loadingCurrentTheme++;
        TL_account.getTheme r5 = new TL_account.getTheme();
        r5.document_id = r3.document.id;
        r5.format = "android";
        TLRPC.TL_inputTheme r6 = new TLRPC.TL_inputTheme();
        r6.access_hash = r3.access_hash;
        r6.id = r3.id;
        r5.theme = r6;
        ConnectionsManager.getInstance(r4).sendRequest(r5, new Theme$$ExternalSyntheticLambda5(r2, r1, r3));
        goto L29
    L21:
        if (r2 == null) goto L29;
        r3 = r2.info;
        if (r3 == null) goto L29;
        r4 = UserConfig.selectedAccount;
    L29:
        r0 = r0 + 1;
        goto L10
    L13:
        r1 = currentNightTheme;
        goto L14
    L39:
        return;
    L6:
        if (Math.abs((System.currentTimeMillis() / 1000) - lastLoadingCurrentThemeTime) >= 3600) goto L8;
        return;
    }

    private static void checkIsDark(SparseIntArray r4, ThemeInfo r5) {
        if (r5 == null) goto L13;
        if (r4 != null) goto L6;
        return;
    L6:
        if (ThemeInfo.m5827$$Nest$fgetisDark(r5) != (-1)) goto L14;
        int r0 = key_windowBackgroundWhite;
        if (ColorUtils.calculateLuminance(ColorUtils.blendARGB(getPreviewColor(r4, r0), getPreviewColor(r4, r0), 0.5f)) >= 0.5d) goto L11;
        ThemeInfo.m5828$$Nest$fputisDark(r5, 1);
        return;
    L11:
        ThemeInfo.m5828$$Nest$fputisDark(r5, 0);
        return;
    L14:
        return;
    }

    public static void clearPreviousTheme() {
        if (previousTheme != null) goto L5;
        return;
    L5:
        hasPreviousTheme = false;
        isApplyingAccent = false;
        previousTheme = null;
    }

    public static BackgroundDrawableSettings createBackgroundDrawable(ThemeInfo r14, SparseIntArray r15, String r16, int r17, boolean r18) {
        if (r14.firstAccentIsDefault == true) goto L5;
    L7:
        boolean r8 = false;
    L8:
        ThemeAccent r1 = r14.getAccent(false);
        if (r1 == null) goto L11;
        File r4 = r1.getPathToWallpaper();
    L12:
        if (r1 != null) goto L14;
    L16:
        boolean r11 = false;
    L17:
        OverrideWallpaperInfo r2 = r14.overrideWallpaper;
        if (r2 == null) goto L22;
        float r12 = r2.intensity;
    L20:
        float r13 = r12 * 100.0f;
    L21:
        int r19 = (int) r13;
        return createBackgroundDrawable(r14, r2, r15, r4, r16, currentColorsNoAccent.get(key_wallpaperFileOffset, -1), r19, r17, r8, false, false, r11, null, r18);
    L22:
        if (r1 == null) goto L24;
        r12 = r1.patternIntensity;
        goto L20
    L24:
        r13 = r14.patternIntensity;
        goto L21
    L14:
        if (r1.patternMotion == false) goto L16;
        r11 = true;
        goto L17
    L11:
        r4 = null;
        goto L12
    L5:
        if (r14.currentAccentId != DEFALT_THEME_ACCENT_ID) goto L7;
        r8 = true;
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static BackgroundDrawableSettings createBackgroundDrawable(ThemeInfo r21, OverrideWallpaperInfo r22, SparseIntArray r23, File r24, String r25, int r26, int r27, int r28, boolean r29, boolean r30, boolean r31, boolean r32, TLRPC.Document r33, boolean r34) {
        BackgroundDrawableSettings r9 = new BackgroundDrawableSettings();
        if (r34 == false) goto L5;
        Drawable r0 = null;
    L6:
        r9.wallpaper = r0;
        if (r30 == false) goto L9;
        if (r31 == true) goto L9;
    L11:
        boolean r02 = false;
    L12:
        if (r22 == null) goto L22;
        r9.isWallpaperMotion = Boolean.valueOf(r22.isMotion);
        if (r22.color != 0) goto L16;
    L20:
        boolean r11 = false;
    L21:
        Boolean r112 = Boolean.valueOf(r11);
        r9.isPatternWallpaper = r112;
        int r113 = r112;
    L27:
        if (r02 == true) goto L121;
        if (r29 == false) goto L30;
        int r14 = 0;
    L31:
        int r17 = r23.get(key_chat_wallpaper_gradient_to3);
        int r16 = r23.get(key_chat_wallpaper_gradient_to2);
        int r15 = r23.get(key_chat_wallpaper_gradient_to1);
        ThemeAccent r03 = r21.getAccent(false);
        if (r14 != 0) goto L34;
    L37:
        if (r24 != null) goto L39;
    L69:
        int r7 = r27;
        int r114 = r28;
        int r19 = 45;
        boolean r8 = false;
    L70:
        if (r8 == true) goto L123;
        if (r14 == 0) goto L98;
        int r13 = r23.get(key_chat_wallpaper_gradient_rotation, -1);
        if (r13 != (-1)) goto L76;
        r13 = r19;
    L76:
        if (r15 == 0) goto L92;
        if (r16 == 0) goto L92;
        MotionBackgroundDrawable r132 = new MotionBackgroundDrawable(r14, r15, r16, r17, false);
        if (r24 == null) goto L90;
        Point r04 = AndroidUtilities.displaySize;
        int r152 = Math.min(r04.x, r04.y);
        Point r05 = AndroidUtilities.displaySize;
        int r06 = Math.max(r05.x, r05.y);
        if (r33 == null) goto L84;
        Bitmap r07 = SvgHelper.getBitmap(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r33, true), r152, r06, false, SvgHelper.ScaleMode.ByWidth);
    L83:
        Bitmap r1 = r07;
        if (r1 != null) goto L191;
    L91:
        r132.setPatternBitmap(r7, r1);
        r132.setPhase(r114 == true ? 1 : 0);
        r9.wallpaper = r132;
    L97:
        r9.isCustomTheme = Boolean.TRUE;
        goto L123
    L191:
        FileOutputStream r08 = new FileOutputStream(r24);     // Catch: Exception -> L88
        Bitmap r3 = r1.copy(Bitmap.Config.ARGB_8888, true);     // Catch: Exception -> L88
        r3.compress(Bitmap.CompressFormat.PNG, 90, r08);     // Catch: Exception -> L88
        r3.recycle();     // Catch: Exception -> L88
        r08.close();     // Catch: Exception -> L88
    L88:
        e = move-exception;
        FileLog.e(e);
        e.printStackTrace();
        goto L91
    L84:
        r07 = SvgHelper.getBitmap(R.raw.default_pattern, r152, r06, -1, 1.0f, SvgHelper.ScaleMode.ByWidth);
        goto L83
    L90:
        r1 = null;
    L92:
        if (r15 == 0) goto L96;
        if (r15 == r14) goto L96;
        BackgroundGradientDrawable r35 = new BackgroundGradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r13), new int[]{r14, r15});
        backgroundGradientDisposable = r35.startDithering(BackgroundGradientDrawable.Sizes.ofDeviceScreen(), new AnonymousClass13(), 100);
        r9.wallpaper = r35;
    L96:
        r9.wallpaper = new ColorDrawable(r14);
        goto L97
    L98:
        if (r25 != null) goto L195;
        if (r26 <= 0) goto L123;
        if (r21.pathToFile == null) goto L109;
    L193:
        String r09 = r21.assetName;     // Catch: Throwable -> L113
        if (r09 == null) goto L115;
        File r010 = getAssetFile(r09);     // Catch: Throwable -> L113
    L116:
        Bitmap r011 = loadScreenSizedBitmap(new FileInputStream(r010), r26);     // Catch: Throwable -> L113
        if (r011 == null) goto L123;
        BitmapDrawable r12 = new BitmapDrawable(r011);     // Catch: Throwable -> L113
        wallpaper = r12;     // Catch: Throwable -> L113
        r9.themedWallpaper = r12;     // Catch: Throwable -> L113
        r9.wallpaper = r12;     // Catch: Throwable -> L113
        r12.setFilterBitmap(true);     // Catch: Throwable -> L113
        r9.isCustomTheme = Boolean.TRUE;     // Catch: Throwable -> L113
        goto L123
    L115:
        r010 = new File(r21.pathToFile);     // Catch: Throwable -> L113
    L113:
        th = move-exception;
        FileLog.e(th);
        goto L123
    L109:
        if (r21.assetName == null) goto L123;
    L195:
        Bitmap r012 = loadScreenSizedBitmap(new FileInputStream(new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(r25) + ".wp")), 0);     // Catch: Exception -> L103
        if (r012 == null) goto L123;
        BitmapDrawable r18 = new BitmapDrawable(r012);     // Catch: Exception -> L103
        r9.wallpaper = r18;     // Catch: Exception -> L103
        r9.themedWallpaper = r18;     // Catch: Exception -> L103
        r9.isCustomTheme = Boolean.TRUE;     // Catch: Exception -> L103
    L103:
        e = move-exception;
        FileLog.e(e);
    L123:
        if (r9.wallpaper != null) goto L176;
        if (r22 == null) goto L126;
        int r013 = r22.color;
    L127:
        if (r22 != null) goto L190;
    L169:
        r9.wallpaper = createDefaultWallpaper();     // Catch: Throwable -> L185
        r9.isCustomTheme = Boolean.FALSE;     // Catch: Throwable -> L185
    L171:
        if (r9.wallpaper != null) goto L176;
        if (r013 != 0) goto L174;
        r013 = -2693905;
    L174:
        r9.wallpaper = new ColorDrawable(r013);
        goto L176
    L190:
        if (r22.isDefault() == true) goto L169;
        if (r22.isColor() == true) goto L134;
    L135:
        if (r013 != 0) goto L137;
    L161:
        File r110 = new File(ApplicationLoader.getFilesDirFixed(), r22.fileName);     // Catch: Throwable -> L185
        if (r110.exists() == false) goto L167;
        Bitmap r111 = loadScreenSizedBitmap(new FileInputStream(r110), 0);     // Catch: Throwable -> L185
        if (r111 == null) goto L167;
        BitmapDrawable r2 = new BitmapDrawable(r111);     // Catch: Throwable -> L185
        r9.wallpaper = r2;     // Catch: Throwable -> L185
        r2.setFilterBitmap(true);     // Catch: Throwable -> L185
        r9.isCustomTheme = Boolean.TRUE;     // Catch: Throwable -> L185
    L167:
        if (r9.wallpaper != null) goto L171;
        r9.wallpaper = createDefaultWallpaper();     // Catch: Throwable -> L185
        r9.isCustomTheme = Boolean.FALSE;     // Catch: Throwable -> L185
        goto L171
    L137:
        if (isPatternWallpaper == false) goto L141;
        if (r22.gradientColor2 == 0) goto L161;
    L141:
        if (r22.gradientColor1 == 0) goto L151;
        if (r22.gradientColor2 == 0) goto L151;
        MotionBackgroundDrawable r36 = new MotionBackgroundDrawable(r22.color, r22.gradientColor1, r22.gradientColor2, r22.gradientColor3, false);     // Catch: Throwable -> L185
        r36.setPhase(r114);     // Catch: Throwable -> L185
        if (r9.isPatternWallpaper.booleanValue() == false) goto L149;
        File r115 = new File(ApplicationLoader.getFilesDirFixed(), r22.fileName);     // Catch: Throwable -> L185
        if (r115.exists() == false) goto L149;
        r36.setPatternBitmap((int) (r22.intensity * 100.0f), loadScreenSizedBitmap(new FileInputStream(r115), 0));     // Catch: Throwable -> L185
        r9.isCustomTheme = Boolean.TRUE;     // Catch: Throwable -> L185
    L149:
        r9.wallpaper = r36;     // Catch: Throwable -> L185
    L151:
        if (r9.isPatternWallpaper.booleanValue() == false) goto L157;
        File r116 = new File(ApplicationLoader.getFilesDirFixed(), r22.fileName);     // Catch: Throwable -> L185
        if (r116.exists() == false) goto L171;
        Bitmap r117 = loadScreenSizedBitmap(new FileInputStream(r116), 0);     // Catch: Throwable -> L185
        if (r117 == null) goto L171;
        BitmapDrawable r210 = new BitmapDrawable(r117);     // Catch: Throwable -> L185
        r9.wallpaper = r210;     // Catch: Throwable -> L185
        r210.setFilterBitmap(true);     // Catch: Throwable -> L185
        r9.isCustomTheme = Boolean.TRUE;     // Catch: Throwable -> L185
        goto L171
    L157:
        int r118 = r22.gradientColor1;     // Catch: Throwable -> L185
        if (r118 == 0) goto L160;
        BackgroundGradientDrawable r37 = new BackgroundGradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r22.rotation), new int[]{r013, r118});     // Catch: Throwable -> L185
        backgroundGradientDisposable = r37.startDithering(BackgroundGradientDrawable.Sizes.ofDeviceScreen(), new AnonymousClass14(), 100);     // Catch: Throwable -> L185
        r9.wallpaper = r37;     // Catch: Throwable -> L185
        goto L171
    L160:
        r9.wallpaper = new ColorDrawable(r013);     // Catch: Throwable -> L185
        goto L171
    L134:
        if (r22.gradientColor1 == 0) goto L171;
    L126:
        r013 = 0;
    L176:
        if (LiteMode.isEnabled(32) == true) goto L184;
        Drawable r014 = r9.wallpaper;
        if ((r014 instanceof MotionBackgroundDrawable) == false) goto L184;
        MotionBackgroundDrawable r015 = (MotionBackgroundDrawable) r014;
        if (r015.getPatternBitmap() != null) goto L182;
        Point r016 = AndroidUtilities.displaySize;
        int r017 = Math.min(r016.x, r016.y);
        Point r119 = AndroidUtilities.displaySize;
        int r120 = Math.max(r119.x, r119.y);
    L183:
        Bitmap r018 = Bitmap.createBitmap(r017, r120, Bitmap.Config.ARGB_8888);
        Canvas r121 = new Canvas(r018);
        r9.wallpaper.setBounds(0, 0, r018.getWidth(), r018.getHeight());
        r9.wallpaper.draw(r121);
        r9.wallpaper = new BitmapDrawable(r018);
        goto L184
    L182:
        int r122 = r015.getPatternBitmap().getWidth();
        r120 = r015.getPatternBitmap().getHeight();
        r017 = r122;
    L184:
        return r9;
    L39:
        if (r24.exists() == false) goto L69;
        if (r14 == 0) goto L63;
        if (r15 == 0) goto L63;
        if (r16 == 0) goto L63;
        r19 = 45;
        r7 = r27;
        r113 = r28;
        MotionBackgroundDrawable r133 = new MotionBackgroundDrawable(r14, r15, r16, r17, false);     // Catch: Throwable -> L52
        BitmapFactory.Options r019 = new BitmapFactory.Options();     // Catch: Throwable -> L52
        Bitmap.Config r123 = Bitmap.Config.ALPHA_8;     // Catch: Throwable -> L52
        r019.inPreferredConfig = r123;     // Catch: Throwable -> L52
        Bitmap r020 = BitmapFactory.decodeFile(r24.getAbsolutePath(), r019);     // Catch: Throwable -> L52
        if (r020 != null) goto L49;
    L54:
        if (r020 != null) goto L56;
        r8 = false;
    L186:
        r133.setPatternBitmap(r7, r020);     // Catch: Throwable -> L59
        r133.setPatternColorFilter(r133.getPatternColor());     // Catch: Throwable -> L59
        r9.wallpaper = r133;     // Catch: Throwable -> L59
        r113 = r113;
    L66:
        r9.isWallpaperMotion = Boolean.valueOf(r32);     // Catch: Throwable -> L59
        Boolean r021 = Boolean.TRUE;     // Catch: Throwable -> L59
        r9.isPatternWallpaper = r021;     // Catch: Throwable -> L59
        r9.isCustomTheme = r021;     // Catch: Throwable -> L59
        r114 = r113;
    L59:
        th = th;
    L68:
        FileLog.e(th);
        r114 = r113;
        goto L70
    L56:
        r8 = true;
        goto L186
    L49:
        if (r020.getConfig() == r123) goto L54;
        Bitmap r82 = r020.copy(r123, false);     // Catch: Throwable -> L52
        r020.recycle();     // Catch: Throwable -> L52
        r020 = r82;
    L61:
        th = th;
        r7 = r27;
        r113 = r28;
        r19 = 45;
    L53:
        r8 = true;
    L63:
        r7 = r27;
        r113 = r28;
        r19 = 45;
        r9.wallpaper = Drawable.createFromPath(r24.getAbsolutePath());     // Catch: Throwable -> L52
        r8 = true;
    L52:
        th = th;
        goto L53
    L34:
        if (MonetAccentHelper.isFallbackPattern(r03) == false) goto L37;
        r9.wallpaper = MonetAccentHelper.createFallbackPatternDrawable(r14, r15, r16, r17, r23.get(key_chat_wallpaper_gradient_rotation, 45), r27, false, r28);
        r9.isWallpaperMotion = Boolean.valueOf(r32);
        Boolean r022 = Boolean.TRUE;
        r9.isPatternWallpaper = r022;
        r9.isCustomTheme = r022;
        return r9;
    L30:
        r14 = r23.get(key_chat_wallpaper);
        goto L31
    L121:
        r114 = r28;
        goto L123
    L16:
        if (r22.isDefault() == true) goto L20;
        if (r22.isColor() == true) goto L20;
        r11 = true;
        goto L21
    L22:
        r9.isWallpaperMotion = Boolean.valueOf(r21.isMotion);
        if (r21.patternBgColor == 0) goto L25;
        boolean r1110 = true;
    L26:
        Boolean r1111 = Boolean.valueOf(r1110);
        r9.isPatternWallpaper = r1111;
        r113 = r1111;
        goto L27
    L25:
        r1110 = false;
    L9:
        if (r22 == null) goto L11;
        r02 = true;
        goto L12
    L5:
        r0 = wallpaper;
        goto L6
    }

    public static void createChatResources(Context r20, boolean r21) {
        createCommonChatResources();
        if (r21 == false) goto L5;
    L10:
        float r16 = 1.0f;
        float r19 = 14.0f;
    L11:
        if (r21 == true) goto L17;
        TextPaint r0 = chat_infoPaint;
        if (r0 == null) goto L18;
        r0.setTextSize(AndroidUtilities.dp(12.0f));
        chat_infoBoldPaint.setTextSize(AndroidUtilities.dp(12.0f));
        chat_stickerCommentCountPaint.setTextSize(AndroidUtilities.dp(11.0f));
        chat_docNamePaint.setTextSize(AndroidUtilities.dp(15.0f));
        chat_locationTitlePaint.setTextSize(AndroidUtilities.dp(15.0f));
        chat_locationAddressPaint.setTextSize(AndroidUtilities.dp(13.0f));
        chat_audioTimePaint.setTextSize(AndroidUtilities.dp(12.0f));
        chat_livePaint.setTextSize(AndroidUtilities.dp(12.0f));
        chat_audioTitlePaint.setTextSize(AndroidUtilities.dp(16.0f));
        chat_audioPerformerPaint.setTextSize(AndroidUtilities.dp(15.0f));
        chat_botButtonPaint.setTextSize(AndroidUtilities.dp(15.0f));
        chat_contactNamePaint.setTextSize(AndroidUtilities.dp(15.0f));
        chat_contactPhonePaint.setTextSize(AndroidUtilities.dp(13.0f));
        chat_durationPaint.setTextSize(AndroidUtilities.dp(12.0f));
        chat_namePaint.setTextSize(AndroidUtilities.dp(r0));
        chat_replyNamePaint.setTextSize(AndroidUtilities.dp(r0));
        chat_replyTextPaint.setTextSize(AndroidUtilities.dp(r0));
        float r7 = (((SharedConfig.fontSize * 2) + 10) / 3.0f) - r16;
        chat_topicTextPaint.setTextSize(AndroidUtilities.dp(r7));
        chat_forwardNamePaint.setTextSize(AndroidUtilities.dp(r0));
        chat_adminPaint.setTextSize(AndroidUtilities.dp(r7));
        chat_ephemeralPaint.setTextSize(AndroidUtilities.dp(12.0f));
        int r02 = SharedConfig.PASSCODE_TYPE_PIN;
        chat_timePaint.setTextSize(AndroidUtilities.dp(12.0f));
        chat_gamePaint.setTextSize(AndroidUtilities.dp(13.0f));
        chat_shipmentPaint.setTextSize(AndroidUtilities.dp(13.0f));
        chat_instantViewPaint.setTextSize(AndroidUtilities.dp(13.0f));
        chat_instantViewRectPaint.setStrokeWidth(AndroidUtilities.dp(r16));
        chat_pollTimerPaint.setStrokeWidth(AndroidUtilities.dp(1.1f));
        chat_actionTextPaint.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
        chat_actionTextPaint2.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
        chat_actionTextPaint3.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 3));
        chat_unlockExtendedMediaTextPaint.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize)));
        chat_contextResult_titleTextPaint.setTextSize(AndroidUtilities.dp(15.0f));
        chat_contextResult_descriptionTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        chat_radialProgressPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        chat_radialProgress2Paint.setStrokeWidth(AndroidUtilities.dp(2.33f));
        chat_commentTextPaint.setTextSize(AndroidUtilities.dp(r19));
        chat_commentTextPaint.setTypeface(AndroidUtilities.bold());
        return;
    L18:
        return;
    L17:
        return;
    L5:
        if (chat_msgInDrawable != null) goto L10;
        Resources r6 = r20.getResources();
        chat_msgNoSoundDrawable = r6.getDrawable(R.drawable.video_muted);
        chat_livePhoto = r6.getDrawable(R.drawable.media_live_on).mutate();
        chat_msgInDrawable = new MessageDrawable(0, false, false);
        chat_msgInSelectedDrawable = new MessageDrawable(0, false, true);
        chat_msgOutDrawable = new MessageDrawable(0, true, false);
        chat_msgOutSelectedDrawable = new MessageDrawable(0, true, true);
        chat_msgInMediaDrawable = new MessageDrawable(1, false, false);
        chat_msgInMediaSelectedDrawable = new MessageDrawable(1, false, true);
        chat_msgOutMediaDrawable = new MessageDrawable(1, true, false);
        chat_msgOutMediaSelectedDrawable = new MessageDrawable(1, true, true);
        PathAnimator r72 = new PathAnimator(0.293f, -26.0f, -28.0f, 1.0f);
        playPauseAnimator = r72;
        r72.addSvgKeyFrame("M 34.141 16.042 C 37.384 17.921 40.886 20.001 44.211 21.965 C 46.139 23.104 49.285 24.729 49.586 25.917 C 50.289 28.687 48.484 30 46.274 30 L 6 30.021 C 3.79 30.021 2.075 30.023 2 26.021 L 2.009 3.417 C 2.009 0.417 5.326 -0.58 7.068 0.417 C 10.545 2.406 25.024 10.761 34.141 16.042 Z", 166.0f);
        playPauseAnimator.addSvgKeyFrame("M 37.843 17.769 C 41.143 19.508 44.131 21.164 47.429 23.117 C 48.542 23.775 49.623 24.561 49.761 25.993 C 50.074 28.708 48.557 30 46.347 30 L 6 30.012 C 3.79 30.012 2 28.222 2 26.012 L 2.009 4.609 C 2.009 1.626 5.276 0.664 7.074 1.541 C 10.608 3.309 28.488 12.842 37.843 17.769 Z", 200.0f);
        playPauseAnimator.addSvgKeyFrame("M 40.644 18.756 C 43.986 20.389 49.867 23.108 49.884 25.534 C 49.897 27.154 49.88 24.441 49.894 26.059 C 49.911 28.733 48.6 30 46.39 30 L 6 30.013 C 3.79 30.013 2 28.223 2 26.013 L 2.008 5.52 C 2.008 2.55 5.237 1.614 7.079 2.401 C 10.656 4 31.106 14.097 40.644 18.756 Z", 217.0f);
        playPauseAnimator.addSvgKeyFrame("M 43.782 19.218 C 47.117 20.675 50.075 21.538 50.041 24.796 C 50.022 26.606 50.038 24.309 50.039 26.104 C 50.038 28.736 48.663 30 46.453 30 L 6 29.986 C 3.79 29.986 2 28.196 2 25.986 L 2.008 6.491 C 2.008 3.535 5.196 2.627 7.085 3.316 C 10.708 4.731 33.992 14.944 43.782 19.218 Z", 234.0f);
        playPauseAnimator.addSvgKeyFrame("M 47.421 16.941 C 50.544 18.191 50.783 19.91 50.769 22.706 C 50.761 24.484 50.76 23.953 50.79 26.073 C 50.814 27.835 49.334 30 47.124 30 L 5 30.01 C 2.79 30.01 1 28.22 1 26.01 L 1.001 10.823 C 1.001 8.218 3.532 6.895 5.572 7.26 C 7.493 8.01 47.421 16.941 47.421 16.941 Z", 267.0f);
        playPauseAnimator.addSvgKeyFrame("M 47.641 17.125 C 50.641 18.207 51.09 19.935 51.078 22.653 C 51.07 24.191 51.062 21.23 51.088 23.063 C 51.109 24.886 49.587 27 47.377 27 L 5 27.009 C 2.79 27.009 1 25.219 1 23.009 L 0.983 11.459 C 0.983 8.908 3.414 7.522 5.476 7.838 C 7.138 8.486 47.641 17.125 47.641 17.125 Z", 300.0f);
        playPauseAnimator.addSvgKeyFrame("M 48 7 C 50.21 7 52 8.79 52 11 C 52 19 52 19 52 19 C 52 21.21 50.21 23 48 23 L 4 23 C 1.79 23 0 21.21 0 19 L 0 11 C 0 8.79 1.79 7 4 7 C 48 7 48 7 48 7 Z", 383.0f);
        chat_msgOutCheckDrawable = r6.getDrawable(R.drawable.msg_check_s).mutate();
        chat_msgOutCheckSelectedDrawable = r6.getDrawable(R.drawable.msg_check_s).mutate();
        chat_msgOutCheckReadDrawable = r6.getDrawable(R.drawable.msg_check_s).mutate();
        chat_msgOutCheckReadSelectedDrawable = r6.getDrawable(R.drawable.msg_check_s).mutate();
        chat_msgMediaCheckDrawable = r6.getDrawable(R.drawable.msg_check_s).mutate();
        chat_msgStickerCheckDrawable = r6.getDrawable(R.drawable.msg_check_s).mutate();
        chat_msgOutHalfCheckDrawable = r6.getDrawable(R.drawable.msg_halfcheck).mutate();
        chat_msgOutHalfCheckSelectedDrawable = r6.getDrawable(R.drawable.msg_halfcheck).mutate();
        chat_msgMediaHalfCheckDrawable = r6.getDrawable(R.drawable.msg_halfcheck_s).mutate();
        chat_msgStickerHalfCheckDrawable = r6.getDrawable(R.drawable.msg_halfcheck_s).mutate();
        chat_msgClockDrawable = new MsgClockDrawable();
        chat_msgUnlockDrawable = r6.getDrawable(R.drawable.ic_lock_header).mutate();
        chat_msgInViewsDrawable = r6.getDrawable(R.drawable.msg_views).mutate();
        chat_msgInViewsSelectedDrawable = r6.getDrawable(R.drawable.msg_views).mutate();
        chat_msgOutViewsDrawable = r6.getDrawable(R.drawable.msg_views).mutate();
        chat_msgOutViewsSelectedDrawable = r6.getDrawable(R.drawable.msg_views).mutate();
        chat_msgInRepliesDrawable = r6.getDrawable(R.drawable.msg_reply_small).mutate();
        chat_msgInRepliesSelectedDrawable = r6.getDrawable(R.drawable.msg_reply_small).mutate();
        chat_msgOutRepliesDrawable = r6.getDrawable(R.drawable.msg_reply_small).mutate();
        chat_msgOutRepliesSelectedDrawable = r6.getDrawable(R.drawable.msg_reply_small).mutate();
        chat_msgInPinnedDrawable = r6.getDrawable(R.drawable.msg_pin_mini).mutate();
        chat_msgInPinnedSelectedDrawable = r6.getDrawable(R.drawable.msg_pin_mini).mutate();
        chat_msgOutPinnedDrawable = r6.getDrawable(R.drawable.msg_pin_mini).mutate();
        chat_msgOutPinnedSelectedDrawable = r6.getDrawable(R.drawable.msg_pin_mini).mutate();
        chat_msgMediaPinnedDrawable = r6.getDrawable(R.drawable.msg_pin_mini).mutate();
        chat_msgStickerPinnedDrawable = r6.getDrawable(R.drawable.msg_pin_mini).mutate();
        chat_msgMediaViewsDrawable = r6.getDrawable(R.drawable.msg_views).mutate();
        chat_msgMediaRepliesDrawable = r6.getDrawable(R.drawable.msg_reply_small).mutate();
        chat_msgStickerViewsDrawable = r6.getDrawable(R.drawable.msg_views).mutate();
        chat_msgStickerRepliesDrawable = r6.getDrawable(R.drawable.msg_reply_small).mutate();
        chat_msgInMenuDrawable = r6.getDrawable(R.drawable.msg_actions).mutate();
        chat_msgInMenuSelectedDrawable = r6.getDrawable(R.drawable.msg_actions).mutate();
        chat_msgOutMenuDrawable = r6.getDrawable(R.drawable.msg_actions).mutate();
        chat_msgOutMenuSelectedDrawable = r6.getDrawable(R.drawable.msg_actions).mutate();
        chat_msgMediaMenuDrawable = r6.getDrawable(R.drawable.video_actions);
        chat_msgInInstantDrawable = r6.getDrawable(R.drawable.msg_instant).mutate();
        chat_msgOutInstantDrawable = r6.getDrawable(R.drawable.msg_instant).mutate();
        chat_msgErrorDrawable = r6.getDrawable(R.drawable.msg_warning);
        chat_muteIconDrawable = r6.getDrawable(R.drawable.list_mute).mutate();
        chat_lockIconDrawable = r6.getDrawable(R.drawable.ic_lock_header);
        chat_msgInCallDrawable[0] = r6.getDrawable(R.drawable.chat_calls_voice).mutate();
        chat_msgInCallSelectedDrawable[0] = r6.getDrawable(R.drawable.chat_calls_voice).mutate();
        chat_msgOutCallDrawable[0] = r6.getDrawable(R.drawable.chat_calls_voice).mutate();
        chat_msgOutCallSelectedDrawable[0] = r6.getDrawable(R.drawable.chat_calls_voice).mutate();
        chat_msgInCallDrawable[1] = r6.getDrawable(R.drawable.chat_calls_video).mutate();
        chat_msgInCallSelectedDrawable[1] = r6.getDrawable(R.drawable.chat_calls_video).mutate();
        chat_msgOutCallDrawable[1] = r6.getDrawable(R.drawable.chat_calls_video).mutate();
        chat_msgOutCallSelectedDrawable[1] = r6.getDrawable(R.drawable.chat_calls_video).mutate();
        chat_msgCallUpGreenDrawable = r6.getDrawable(R.drawable.chat_calls_outgoing).mutate();
        chat_msgCallDownRedDrawable = r6.getDrawable(R.drawable.chat_calls_incoming).mutate();
        chat_msgCallDownGreenDrawable = r6.getDrawable(R.drawable.chat_calls_incoming).mutate();
        int r73 = 0;
    L7:
        if (r73 >= 2) goto L9;
        chat_pollCheckDrawable[r73] = r6.getDrawable(R.drawable.poll_right).mutate();
        chat_pollCrossDrawable[r73] = r6.getDrawable(R.drawable.poll_wrong).mutate();
        chat_pollHintDrawable[r73] = r6.getDrawable(R.drawable.msg_emoji_objects).mutate();
        chat_psaHelpDrawable[r73] = r6.getDrawable(R.drawable.msg_psa).mutate();
        r73 = r73 + 1;
        goto L7
    L9:
        chat_pencilIconDrawable = r6.getDrawable(R.drawable.pencil).mutate();
        chat_channelIconDrawable = r6.getDrawable(R.drawable.channel).mutate();
        calllog_msgCallUpRedDrawable = r6.getDrawable(R.drawable.mini_call_out_16).mutate();
        calllog_msgCallUpGreenDrawable = r6.getDrawable(R.drawable.mini_call_out_16).mutate();
        calllog_msgCallDownRedDrawable = r6.getDrawable(R.drawable.mini_call_in_16).mutate();
        calllog_msgCallDownGreenDrawable = r6.getDrawable(R.drawable.mini_call_in_16).mutate();
        chat_inlineResultFile = r6.getDrawable(R.drawable.bot_file);
        chat_inlineResultAudio = r6.getDrawable(R.drawable.bot_music);
        chat_inlineResultLocation = r6.getDrawable(R.drawable.bot_location);
        chat_botLinkDrawable = r6.getDrawable(R.drawable.bot_link);
        chat_botInlineDrawable = r6.getDrawable(R.drawable.bot_lines);
        chat_botCardDrawable = r6.getDrawable(R.drawable.bot_card);
        chat_botWebViewDrawable = r6.getDrawable(R.drawable.bot_webview);
        chat_botInviteDrawable = r6.getDrawable(R.drawable.bot_invite);
        chat_botLockDrawable = r6.getDrawable(R.drawable.permission_locked);
        chat_commentDrawable = r6.getDrawable(R.drawable.msg_msgbubble);
        chat_commentStickerDrawable = r6.getDrawable(R.drawable.msg_msgbubble2);
        chat_commentArrowDrawable = r6.getDrawable(R.drawable.msg_arrowright);
        chat_gradientLeftDrawable = r6.getDrawable(R.drawable.gradient_left);
        chat_gradientRightDrawable = r6.getDrawable(R.drawable.gradient_right);
        chat_contextResult_shadowUnderSwitchDrawable = r6.getDrawable(R.drawable.header_shadow).mutate();
        chat_attachEmptyDrawable = r6.getDrawable(R.drawable.nophotos3);
        chat_shareIconDrawable = r6.getDrawable(R.drawable.filled_button_share).mutate();
        chat_replyIconDrawable = r6.getDrawable(R.drawable.filled_button_reply);
        chat_closeIconDrawable = r6.getDrawable(R.drawable.msg_voiceclose).mutate();
        chat_moreIconDrawable = r6.getDrawable(R.drawable.media_more).mutate();
        chat_goIconDrawable = r6.getDrawable(R.drawable.filled_open_message);
        int r74 = AndroidUtilities.dp(2.0f);
        RectF r10 = new RectF();
        chat_filePath[0] = new Path();
        chat_filePath[0].moveTo(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(3.0f));
        chat_filePath[0].lineTo(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(3.0f));
        chat_filePath[0].lineTo(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f));
        chat_filePath[0].lineTo(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(20.0f));
        r16 = 1.0f;
        r19 = 14.0f;
        r10.set(AndroidUtilities.dp(21.0f) - (r74 * 2), AndroidUtilities.dp(19.0f) - r74, AndroidUtilities.dp(21.0f), AndroidUtilities.dp(19.0f) + r74);
        chat_filePath[0].arcTo(r10, 0.0f, 90.0f, false);
        chat_filePath[0].lineTo(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(21.0f));
        r10.set(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(19.0f) - r74, AndroidUtilities.dp(5.0f) + r13, AndroidUtilities.dp(19.0f) + r74);
        chat_filePath[0].arcTo(r10, 90.0f, 90.0f, false);
        chat_filePath[0].lineTo(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(4.0f));
        r10.set(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(5.0f) + r13, AndroidUtilities.dp(3.0f) + r13);
        chat_filePath[0].arcTo(r10, 180.0f, 90.0f, false);
        chat_filePath[0].close();
        chat_filePath[1] = new Path();
        chat_filePath[1].moveTo(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(5.0f));
        chat_filePath[1].lineTo(AndroidUtilities.dp(19.0f), AndroidUtilities.dp(10.0f));
        chat_filePath[1].lineTo(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(10.0f));
        chat_filePath[1].close();
        chat_flameIcon = r6.getDrawable(R.drawable.filled_fire).mutate();
        chat_gifIcon = r6.getDrawable(R.drawable.msg_round_gif_m).mutate();
        chat_pluginIcon = r6.getDrawable(R.drawable.plugins_filled).mutate();
        chat_settingsIcon = r6.getDrawable(R.drawable.filled_profile_settings).mutate();
        chat_stickersIcon = r6.getDrawable(R.drawable.stickers_filled).mutate();
        chat_fileStatesDrawable[0][0] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_play_m);
        chat_fileStatesDrawable[0][1] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_play_m);
        chat_fileStatesDrawable[1][0] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_pause_m);
        chat_fileStatesDrawable[1][1] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_pause_m);
        chat_fileStatesDrawable[2][0] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_load_m);
        chat_fileStatesDrawable[2][1] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_load_m);
        chat_fileStatesDrawable[3][0] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_file_s);
        chat_fileStatesDrawable[3][1] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_file_s);
        chat_fileStatesDrawable[4][0] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_cancel_m);
        chat_fileStatesDrawable[4][1] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_round_cancel_m);
        chat_contactDrawable[0] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_contact);
        chat_contactDrawable[1] = createCircleDrawableWithIcon(AndroidUtilities.dp(44.0f), R.drawable.msg_contact);
        chat_locationDrawable[0] = r6.getDrawable(R.drawable.msg_location).mutate();
        chat_locationDrawable[1] = r6.getDrawable(R.drawable.msg_location).mutate();
        chat_composeShadowDrawable = r20.getResources().getDrawable(R.drawable.compose_panel_shadow).mutate();
        chat_composeShadowRoundDrawable = r20.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        chat_roundVideoShadow = new RoundVideoShadow();
        defaultChatDrawables.clear();
        defaultChatDrawableColorKeys.clear();
        Drawable r1 = chat_botInlineDrawable;
        int r2 = key_chat_serviceIcon;
        addChatDrawable("drawableBotInline", r1, r2);
        addChatDrawable("drawableBotWebView", chat_botWebViewDrawable, r2);
        addChatDrawable("drawableBotLock", chat_botLockDrawable, r2);
        addChatDrawable("drawableBotLink", chat_botLinkDrawable, r2);
        addChatDrawable("drawable_botInvite", chat_botInviteDrawable, r2);
        addChatDrawable("drawableGoIcon", chat_goIconDrawable, r2);
        addChatDrawable("drawableCommentSticker", chat_commentStickerDrawable, r2);
        addChatDrawable("drawableMsgError", chat_msgErrorDrawable, key_chat_sentErrorIcon);
        addChatDrawable("drawableMsgIn", chat_msgInDrawable, -1);
        addChatDrawable("drawableMsgInSelected", chat_msgInSelectedDrawable, -1);
        addChatDrawable("drawableMsgInMedia", chat_msgInMediaDrawable, -1);
        addChatDrawable("drawableMsgInMediaSelected", chat_msgInMediaSelectedDrawable, -1);
        addChatDrawable("drawableMsgInInstant", chat_msgInInstantDrawable, key_chat_inInstant);
        addChatDrawable("drawableMsgOut", chat_msgOutDrawable, -1);
        addChatDrawable("drawableMsgOutSelected", chat_msgOutSelectedDrawable, -1);
        addChatDrawable("drawableMsgOutMedia", chat_msgOutMediaDrawable, -1);
        addChatDrawable("drawableMsgOutMediaSelected", chat_msgOutMediaSelectedDrawable, -1);
        Drawable r12 = chat_msgOutCallDrawable[0];
        int r3 = key_chat_outInstant;
        addChatDrawable("drawableMsgOutCallAudio", r12, r3);
        Drawable r13 = chat_msgOutCallSelectedDrawable[0];
        int r62 = key_chat_outInstantSelected;
        addChatDrawable("drawableMsgOutCallAudioSelected", r13, r62);
        addChatDrawable("drawableMsgOutCallVideo", chat_msgOutCallDrawable[1], r3);
        addChatDrawable("drawableMsgOutCallVideo", chat_msgOutCallSelectedDrawable[1], r62);
        addChatDrawable("drawableMsgOutCheck", chat_msgOutCheckDrawable, key_chat_outSentCheck);
        addChatDrawable("drawableMsgOutCheckSelected", chat_msgOutCheckSelectedDrawable, key_chat_outSentCheckSelected);
        Drawable r14 = chat_msgOutCheckReadDrawable;
        int r63 = key_chat_outSentCheckRead;
        addChatDrawable("drawableMsgOutCheckRead", r14, r63);
        Drawable r15 = chat_msgOutCheckReadSelectedDrawable;
        int r75 = key_chat_outSentCheckReadSelected;
        addChatDrawable("drawableMsgOutCheckReadSelected", r15, r75);
        addChatDrawable("drawableMsgOutHalfCheck", chat_msgOutHalfCheckDrawable, r63);
        addChatDrawable("drawableMsgOutHalfCheckSelected", chat_msgOutHalfCheckSelectedDrawable, r75);
        addChatDrawable("drawableMsgOutInstant", chat_msgOutInstantDrawable, r3);
        addChatDrawable("drawableMsgOutMenu", chat_msgOutMenuDrawable, key_chat_outMenu);
        addChatDrawable("drawableMsgOutMenuSelected", chat_msgOutMenuSelectedDrawable, key_chat_outMenuSelected);
        Drawable r17 = chat_msgOutPinnedDrawable;
        int r32 = key_chat_outViews;
        addChatDrawable("drawableMsgOutPinned", r17, r32);
        Drawable r18 = chat_msgOutPinnedSelectedDrawable;
        int r64 = key_chat_outViewsSelected;
        addChatDrawable("drawableMsgOutPinnedSelected", r18, r64);
        addChatDrawable("drawableMsgOutReplies", chat_msgOutRepliesDrawable, r32);
        addChatDrawable("drawableMsgOutReplies", chat_msgOutRepliesSelectedDrawable, r64);
        addChatDrawable("drawableMsgOutViews", chat_msgOutViewsDrawable, r32);
        addChatDrawable("drawableMsgOutViewsSelected", chat_msgOutViewsSelectedDrawable, r64);
        Drawable r110 = chat_msgStickerCheckDrawable;
        int r33 = key_chat_serviceText;
        addChatDrawable("drawableMsgStickerCheck", r110, r33);
        addChatDrawable("drawableMsgStickerHalfCheck", chat_msgStickerHalfCheckDrawable, r33);
        addChatDrawable("drawableMsgStickerPinned", chat_msgStickerPinnedDrawable, r33);
        addChatDrawable("drawableMsgStickerReplies", chat_msgStickerRepliesDrawable, r33);
        addChatDrawable("drawableMsgStickerViews", chat_msgStickerViewsDrawable, r33);
        addChatDrawable("drawableReplyIcon", chat_replyIconDrawable, r2);
        addChatDrawable("drawableCloseIcon", chat_closeIconDrawable, r2);
        addChatDrawable("drawableMoreIcon", chat_moreIconDrawable, r2);
        addChatDrawable("drawableShareIcon", chat_shareIconDrawable, r2);
        addChatDrawable("drawableMuteIcon", chat_muteIconDrawable, key_chat_muteIcon);
        addChatDrawable("drawableLockIcon", chat_lockIconDrawable, key_chat_lockIcon);
        addChatDrawable("drawable_chat_pollHintDrawableOut", chat_pollHintDrawable[1], key_chat_outPreviewInstantText);
        addChatDrawable("drawable_chat_pollHintDrawableIn", chat_pollHintDrawable[0], key_chat_inPreviewInstantText);
        applyChatTheme(r21, false);
        goto L11
    }

    public static ShapeDrawable createCircleDrawable(int r2, int r3) {
        OvalShape r0 = new OvalShape();
        float r1 = r2;
        r0.resize(r1, r1);
        ShapeDrawable r12 = new ShapeDrawable(r0);
        r12.setIntrinsicWidth(r2);
        r12.setIntrinsicHeight(r2);
        r12.getPaint().setColor(r3);
        return r12;
    }

    public static ShapeDrawable createCircleDrawable(int r10, int r11, int r12) {
        OvalShape r0 = new OvalShape();
        float r5 = r10;
        r0.resize(r5, r5);
        ShapeDrawable r9 = new ShapeDrawable(r0);
        r9.setIntrinsicWidth(r10);
        r9.setIntrinsicHeight(r10);
        r9.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, r5, r11, r12, Shader.TileMode.CLAMP));
        return r9;
    }

    public static CombinedDrawable createCircleDrawableWithIcon(int r1, int r2) {
        return createCircleDrawableWithIcon(r1, r2, 0);
    }

    public static CombinedDrawable createCircleDrawableWithIcon(int r1, int r2, int r3) {
        if (r2 == 0) goto L4;
        Drawable r22 = ContextCompat.getDrawable(ApplicationLoader.applicationContext, r2).mutate();
    L6:
        return createCircleDrawableWithIcon(r1, r22, r3);
    L4:
        r22 = null;
        goto L6
    }

    public static CombinedDrawable createCircleDrawableWithIcon(int r3, Drawable r4, int r5) {
        OvalShape r0 = new OvalShape();
        float r1 = r3;
        r0.resize(r1, r1);
        ShapeDrawable r12 = new ShapeDrawable(r0);
        Paint r02 = r12.getPaint();
        r02.setColor(-1);
        if (r5 != 1) goto L6;
        r02.setStyle(Paint.Style.STROKE);
        r02.setStrokeWidth(AndroidUtilities.dp(2.0f));
    L8:
        CombinedDrawable r52 = new CombinedDrawable(r12, r4);
        r52.setCustomSize(r3, r3);
        return r52;
    L6:
        if (r5 != 2) goto L8;
        r02.setAlpha(0);
        goto L8
    }

    public static Drawable createCircleSelectorDrawable(int r2, int r3, int r4) {
        maskPaint.setColor(-1);
        AnonymousClass10 r0 = new AnonymousClass10(r3, r4);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r2}), null, r0);
    }

    public static void createCommonChatResources() {
        createCommonMessageResources();
        if (chat_infoPaint != null) goto L6;
        chat_infoPaint = new TextPaint(1);
        TextPaint r0 = new TextPaint(1);
        chat_infoBoldPaint = r0;
        r0.setTypeface(AndroidUtilities.bold());
        TextPaint r02 = new TextPaint(1);
        chat_stickerCommentCountPaint = r02;
        r02.setTypeface(AndroidUtilities.bold());
        TextPaint r03 = new TextPaint(1);
        chat_docNamePaint = r03;
        r03.setTypeface(AndroidUtilities.bold());
        chat_docBackPaint = new Paint(1);
        Paint r04 = new Paint(1);
        chat_deleteProgressPaint = r04;
        Paint.Style r2 = Paint.Style.STROKE;
        r04.setStyle(r2);
        Paint r05 = chat_deleteProgressPaint;
        Paint.Cap r3 = Paint.Cap.ROUND;
        r05.setStrokeCap(r3);
        TextPaint r06 = new TextPaint(1);
        chat_locationTitlePaint = r06;
        r06.setTypeface(AndroidUtilities.bold());
        chat_locationAddressPaint = new TextPaint(1);
        Paint r07 = new Paint();
        chat_urlPaint = r07;
        r07.setPathEffect(LinkPath.getRoundedEffect());
        Paint r08 = new Paint();
        chat_outUrlPaint = r08;
        r08.setPathEffect(LinkPath.getRoundedEffect());
        Paint r09 = new Paint();
        chat_textSearchSelectionPaint = r09;
        r09.setPathEffect(LinkPath.getRoundedEffect());
        Paint r010 = new Paint(1);
        chat_radialProgressPaint = r010;
        r010.setStrokeCap(r3);
        chat_radialProgressPaint.setStyle(r2);
        chat_radialProgressPaint.setColor(-1610612737);
        Paint r011 = new Paint(1);
        chat_radialProgress2Paint = r011;
        r011.setStrokeCap(r3);
        chat_radialProgress2Paint.setStyle(r2);
        chat_audioTimePaint = new TextPaint(1);
        TextPaint r012 = new TextPaint(1);
        chat_livePaint = r012;
        r012.setTypeface(Typeface.DEFAULT_BOLD);
        TextPaint r013 = new TextPaint(1);
        chat_audioTitlePaint = r013;
        r013.setTypeface(AndroidUtilities.bold());
        chat_audioPerformerPaint = new TextPaint(1);
        TextPaint r014 = new TextPaint(1);
        chat_botButtonPaint = r014;
        r014.setTypeface(AndroidUtilities.bold());
        TextPaint r015 = new TextPaint(1);
        chat_contactNamePaint = r015;
        r015.setTypeface(AndroidUtilities.bold());
        chat_contactPhonePaint = new TextPaint(1);
        chat_durationPaint = new TextPaint(1);
        TextPaint r016 = new TextPaint(1);
        chat_gamePaint = r016;
        r016.setTypeface(AndroidUtilities.bold());
        chat_shipmentPaint = new TextPaint(1);
        chat_timePaint = new TextPaint(1);
        chat_adminPaint = new TextPaint(1);
        chat_ephemeralPaint = new TextPaint(1);
        TextPaint r017 = new TextPaint(1);
        chat_namePaint = r017;
        r017.setTypeface(AndroidUtilities.bold());
        chat_forwardNamePaint = new TextPaint(1);
        TextPaint r018 = new TextPaint(1);
        chat_replyNamePaint = r018;
        r018.setTypeface(AndroidUtilities.bold());
        chat_replyTextPaint = new TextPaint(1);
        TextPaint r019 = new TextPaint(1);
        chat_topicTextPaint = r019;
        r019.setTypeface(AndroidUtilities.bold());
        chat_titleLabelTextPaint = new TextPaint(1);
        chat_commentTextPaint = new TextPaint(1);
        TextPaint r020 = new TextPaint(1);
        chat_instantViewPaint = r020;
        r020.setTypeface(AndroidUtilities.bold());
        Paint r021 = new Paint(1);
        chat_instantViewRectPaint = r021;
        r021.setStyle(r2);
        chat_instantViewRectPaint.setStrokeCap(r3);
        chat_instantViewButtonPaint = new Paint(1);
        Paint r022 = new Paint(1);
        chat_pollTimerPaint = r022;
        r022.setStyle(r2);
        chat_pollTimerPaint.setStrokeCap(r3);
        chat_replyLinePaint = new Paint(1);
        chat_msgErrorPaint = new Paint(1);
        chat_statusPaint = new Paint(1);
        Paint r023 = new Paint(1);
        chat_statusRecordPaint = r023;
        r023.setStyle(r2);
        chat_statusRecordPaint.setStrokeCap(r3);
        chat_actionTextPaint = new TextPaint(1);
        chat_actionTextPaint2 = new TextPaint(1);
        chat_actionTextPaint3 = new TextPaint(1);
        chat_actionTextPaint.setTypeface(AndroidUtilities.bold());
        TextPaint r024 = new TextPaint(1);
        chat_unlockExtendedMediaTextPaint = r024;
        r024.setTypeface(AndroidUtilities.bold());
        Paint r025 = new Paint(1);
        chat_actionBackgroundGradientDarkenPaint = r025;
        r025.setColor(352321536);
        chat_timeBackgroundPaint = new Paint(1);
        TextPaint r026 = new TextPaint(1);
        chat_contextResult_titleTextPaint = r026;
        r026.setTypeface(AndroidUtilities.bold());
        chat_contextResult_descriptionTextPaint = new TextPaint(1);
        chat_composeBackgroundPaint = new Paint();
        chat_radialProgressPausedPaint = new Paint(1);
        chat_radialProgressPausedSeekbarPaint = new Paint(1);
        chat_videoProgressPaint = new Paint(1);
        chat_messageBackgroundSelectedPaint = new Paint(1);
        chat_actionBackgroundPaint = new Paint(7);
        chat_actionBackgroundSelectedPaint = new Paint(7);
        addChatPaint("paintChatMessageBackgroundSelected", chat_messageBackgroundSelectedPaint, key_chat_selectedBackground);
        Paint r027 = chat_actionBackgroundPaint;
        int r1 = key_chat_serviceBackground;
        addChatPaint("paintChatActionBackground", r027, r1);
        addChatPaint("paintChatActionBackgroundDarken", chat_actionBackgroundGradientDarkenPaint, r1);
        addChatPaint("paintChatActionBackgroundSelected", chat_actionBackgroundSelectedPaint, key_chat_serviceBackgroundSelected);
        TextPaint r028 = chat_actionTextPaint;
        int r12 = key_chat_serviceText;
        addChatPaint("paintChatActionText", r028, r12);
        addChatPaint("paintChatActionText2", chat_actionTextPaint2, r12);
        addChatPaint("paintChatActionText3", chat_actionTextPaint3, r12);
        addChatPaint("paintChatBotButton", chat_botButtonPaint, key_chat_botButtonText);
        addChatPaint("paintChatComposeBackground", chat_composeBackgroundPaint, key_chat_messagePanelBackground);
        addChatPaint("paintChatTimeBackground", chat_timeBackgroundPaint, key_chat_mediaTimeBackground);
        return;
    }

    public static void createCommonDialogResources(Context r2) {
        if (dialogs_countTextPaint != null) goto L5;
        TextPaint r22 = new TextPaint(1);
        dialogs_countTextPaint = r22;
        r22.setTypeface(AndroidUtilities.bold());
        TextPaint r23 = new TextPaint(1);
        dialogs_countTextPaint2 = r23;
        r23.setTypeface(AndroidUtilities.bold());
        dialogs_countPaint = new Paint(1);
        dialogs_onlineCirclePaint = new Paint(1);
        dialogs_tagPaint = new Paint(1);
    L5:
        dialogs_countTextPaint.setTextSize(AndroidUtilities.dp(12.0f));
        dialogs_countTextPaint2.setTextSize(AndroidUtilities.dp(13.0f));
    }

    public static void createCommonMessageResources() {
        Object r0 = sync;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (chat_msgTextPaint != null) goto L9;
        chat_msgTextPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgGameTextPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextPaintEmoji = new TextPaint[6];     // Catch: Throwable -> L7
        chat_msgTextPaintOneEmoji = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextPaintTwoEmoji = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextPaintThreeEmoji = new TextPaint(1);     // Catch: Throwable -> L7
        TextPaint r1 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgBotButtonPaint = r1;     // Catch: Throwable -> L7
        r1.setTypeface(AndroidUtilities.bold());     // Catch: Throwable -> L7
        TextPaint r12 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_namePaint = r12;     // Catch: Throwable -> L7
        r12.setTypeface(AndroidUtilities.bold());     // Catch: Throwable -> L7
        TextPaint r13 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_replyNamePaint = r13;     // Catch: Throwable -> L7
        r13.setTypeface(AndroidUtilities.bold());     // Catch: Throwable -> L7
        chat_replyTextPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_quoteTextPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_explanationTextPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_titleLabelTextPaint = new TextPaint(1);     // Catch: Throwable -> L7
        TextPaint r14 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_topicTextPaint = r14;     // Catch: Throwable -> L7
        r14.setTypeface(AndroidUtilities.bold());     // Catch: Throwable -> L7
        chat_forwardNamePaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_adminPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_timePaint = new TextPaint(1);     // Catch: Throwable -> L7
        TextPaint r15 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextCodePaint = r15;     // Catch: Throwable -> L7
        Typeface r4 = Typeface.MONOSPACE;     // Catch: Throwable -> L7
        r15.setTypeface(r4);     // Catch: Throwable -> L7
        TextPaint r16 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextCode2Paint = r16;     // Catch: Throwable -> L7
        r16.setTypeface(r4);     // Catch: Throwable -> L7
        TextPaint r17 = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextCode3Paint = r17;     // Catch: Throwable -> L7
        r17.setTypeface(r4);     // Catch: Throwable -> L7
        chat_msgCodeBgPaint = new TextPaint(1);     // Catch: Throwable -> L7
        chat_ephemeralPaint = new TextPaint(1);     // Catch: Throwable -> L7
    L9:
        float[] r18 = {0.68f, 0.46f, 0.34f, 0.28f, 0.22f, 0.19f};     // Catch: Throwable -> L7
        int r2 = 0;
    L10:
        TextPaint[] r42 = chat_msgTextPaintEmoji;     // Catch: Throwable -> L7
        if (r2 >= r42.length) goto L13;
        r42[r2] = new TextPaint(1);     // Catch: Throwable -> L7
        chat_msgTextPaintEmoji[r2].setTextSize(AndroidUtilities.dp(r18[r2] * 120.0f));     // Catch: Throwable -> L7
        r2 = r2 + 1;     // Catch: Throwable -> L7
        goto L10
    L13:
        chat_msgTextPaintOneEmoji.setTextSize(AndroidUtilities.dp(46.0f));     // Catch: Throwable -> L7
        chat_msgTextPaintTwoEmoji.setTextSize(AndroidUtilities.dp(38.0f));     // Catch: Throwable -> L7
        chat_msgTextPaintThreeEmoji.setTextSize(AndroidUtilities.dp(30.0f));     // Catch: Throwable -> L7
        chat_msgTextPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));     // Catch: Throwable -> L7
        chat_msgGameTextPaint.setTextSize(AndroidUtilities.dp(14.0f));     // Catch: Throwable -> L7
        chat_msgBotButtonPaint.setTextSize(AndroidUtilities.dp(15.0f));     // Catch: Throwable -> L7
        chat_namePaint.setTextSize(AndroidUtilities.dp(r1));     // Catch: Throwable -> L7
        chat_replyNamePaint.setTextSize(AndroidUtilities.dp(r1));     // Catch: Throwable -> L7
        chat_replyTextPaint.setTextSize(AndroidUtilities.dp(r1));     // Catch: Throwable -> L7
        float r5 = (((SharedConfig.fontSize * 2) + 10) / 3.0f) - 1.0f;     // Catch: Throwable -> L7
        chat_quoteTextPaint.setTextSize(AndroidUtilities.dp(r5));     // Catch: Throwable -> L7
        chat_explanationTextPaint.setTextSize(AndroidUtilities.dp(r1));     // Catch: Throwable -> L7
        chat_ephemeralPaint.setTextSize(AndroidUtilities.dp(12.0f));     // Catch: Throwable -> L7
        chat_topicTextPaint.setTextSize(AndroidUtilities.dp(r5));     // Catch: Throwable -> L7
        chat_titleLabelTextPaint.setTextSize(AndroidUtilities.dp(r1 - 2.0f));     // Catch: Throwable -> L7
        chat_forwardNamePaint.setTextSize(AndroidUtilities.dp(r1));     // Catch: Throwable -> L7
        chat_adminPaint.setTextSize(AndroidUtilities.dp(r5));     // Catch: Throwable -> L7
        chat_msgTextCodePaint.setTextSize(AndroidUtilities.dp(Math.max(Math.min(10, SharedConfig.fontSize - 1), SharedConfig.fontSize - 2)));     // Catch: Throwable -> L7
        chat_msgTextCode2Paint.setTextSize(AndroidUtilities.dp(Math.max(Math.min(10, SharedConfig.fontSize - 2), SharedConfig.fontSize - 3)));     // Catch: Throwable -> L7
        chat_msgTextCode3Paint.setTextSize(AndroidUtilities.dp(Math.max(Math.min(10, SharedConfig.fontSize - 2), SharedConfig.fontSize - 5)));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public static void createCommonResources(Context r12) {
        if (dividerPaint != null) goto L24;
        Paint r0 = new Paint();
        dividerPaint = r0;
        r0.setStrokeWidth(1.0f);
        Paint r02 = new Paint();
        dividerExtraPaint = r02;
        r02.setStrokeWidth(1.0f);
        Paint r03 = new Paint();
        forcedDividerPaint = r03;
        r03.setStrokeWidth(1.0f);
        avatar_backgroundPaint = new Paint(1);
        Paint r04 = new Paint(1);
        checkboxSquare_checkPaint = r04;
        r04.setStyle(Paint.Style.STROKE);
        checkboxSquare_checkPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        checkboxSquare_checkPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint r05 = new Paint(1);
        checkboxSquare_eraserPaint = r05;
        r05.setColor(0);
        checkboxSquare_eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        checkboxSquare_backgroundPaint = new Paint(1);
        Paint r06 = new Paint();
        linkSelectionPaint = r06;
        r06.setPathEffect(LinkPath.getRoundedEffect());
        Resources r122 = r12.getResources();
        avatarDrawables[0] = r122.getDrawable(R.drawable.chats_saved);
        avatarDrawables[1] = r122.getDrawable(R.drawable.ghost);
        avatarDrawables[2] = r122.getDrawable(R.drawable.msg_folders_private);
        avatarDrawables[3] = r122.getDrawable(R.drawable.msg_folders_requests);
        avatarDrawables[4] = r122.getDrawable(R.drawable.msg_folders_groups);
        avatarDrawables[5] = r122.getDrawable(R.drawable.msg_folders_channels);
        avatarDrawables[6] = r122.getDrawable(R.drawable.msg_folders_bots);
        avatarDrawables[7] = r122.getDrawable(R.drawable.msg_folders_muted);
        avatarDrawables[8] = r122.getDrawable(R.drawable.msg_folders_read);
        avatarDrawables[9] = r122.getDrawable(R.drawable.msg_folders_archive);
        avatarDrawables[10] = r122.getDrawable(R.drawable.msg_folders_private);
        avatarDrawables[11] = r122.getDrawable(R.drawable.chats_replies);
        avatarDrawables[12] = r122.getDrawable(R.drawable.other_chats);
        avatarDrawables[13] = r122.getDrawable(R.drawable.msg_stories_closefriends);
        avatarDrawables[14] = r122.getDrawable(R.drawable.filled_gift_premium);
        avatarDrawables[15] = r122.getDrawable(R.drawable.filled_unknown);
        avatarDrawables[16] = r122.getDrawable(R.drawable.filled_unclaimed);
        avatarDrawables[17] = r122.getDrawable(R.drawable.large_repost_story);
        avatarDrawables[18] = r122.getDrawable(R.drawable.large_hidden);
        avatarDrawables[19] = r122.getDrawable(R.drawable.large_notes);
        avatarDrawables[20] = r122.getDrawable(R.drawable.filled_folder_new);
        avatarDrawables[21] = r122.getDrawable(R.drawable.filled_folder_existing);
        avatarDrawables[22] = r122.getDrawable(R.drawable.filled_giveaway_premium);
        avatarDrawables[23] = r122.getDrawable(R.drawable.filled_giveaway_stars);
        avatarDrawables[24] = r122.getDrawable(R.drawable.filled_suggest_chat_avatar);
        avatarDrawables[25] = r122.getDrawable(R.drawable.ic_feed_filled);
        RLottieDrawable r123 = dialogs_archiveAvatarDrawable;
        if (r123 == null) goto L7;
        r123.setCallback(null);
        dialogs_archiveAvatarDrawable.recycle(false);
    L7:
        RLottieDrawable r124 = dialogs_archiveDrawable;
        if (r124 == null) goto L10;
        r124.recycle(false);
    L10:
        RLottieDrawable r125 = dialogs_unarchiveDrawable;
        if (r125 == null) goto L13;
        r125.recycle(false);
    L13:
        RLottieDrawable r126 = dialogs_pinArchiveDrawable;
        if (r126 == null) goto L16;
        r126.recycle(false);
    L16:
        RLottieDrawable r127 = dialogs_unpinArchiveDrawable;
        if (r127 == null) goto L19;
        r127.recycle(false);
    L19:
        RLottieDrawable r128 = dialogs_hidePsaDrawable;
        if (r128 == null) goto L22;
        r128.recycle(false);
    L22:
        dialogs_archiveAvatarDrawable = new RLottieDrawable(R.raw.chats_archiveavatar, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_archiveDrawable = new RLottieDrawable(R.raw.chats_archive, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_unarchiveDrawable = new RLottieDrawable(R.raw.chats_unarchive, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_pinArchiveDrawable = new RLottieDrawable(R.raw.chats_hide, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_unpinArchiveDrawable = new RLottieDrawable(R.raw.chats_unhide, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_hidePsaDrawable = new RLottieDrawable(R.raw.chat_audio_record_delete, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f), false, null);
        dialogs_swipeMuteDrawable = new RLottieDrawable(R.raw.swipe_mute, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipeUnmuteDrawable = new RLottieDrawable(R.raw.swipe_unmute, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipeReadDrawable = new RLottieDrawable(R.raw.swipe_read, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipeUnreadDrawable = new RLottieDrawable(R.raw.swipe_unread, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipeDeleteDrawable = new RLottieDrawable(R.raw.swipe_delete, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipeUnpinDrawable = new RLottieDrawable(R.raw.swipe_unpin, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipePinDrawable = new RLottieDrawable(R.raw.swipe_pin, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), false, null);
        dialogs_swipeCommunityUngroup = new RLottieDrawable(R.raw.swipe_community_ungroup, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), false, null);
        applyCommonTheme();
        return;
    }

    public static Drawable createDefaultWallpaper() {
        return createDefaultWallpaper(0, 0);
    }

    public static Drawable createDefaultWallpaper(int r7, int r8) {
        if (r7 == 0) goto L6;
        boolean r1 = true;
    L7:
        MotionBackgroundDrawable r0 = new MotionBackgroundDrawable(-2368069, -9722489, -2762611, -7817084, r1);
        if (r7 <= 0) goto L12;
        if (r8 <= 0) goto L12;
    L11:
        int r2 = r7;
        int r3 = r8;
        r0.setPatternBitmap(34, SvgHelper.getBitmap(R.raw.default_pattern, r2, r3, -16777216, 1.0f, SvgHelper.ScaleMode.ByWidth));
        r0.setPatternColorFilter(r0.getPatternColor());
        return r0;
    L12:
        Point r72 = AndroidUtilities.displaySize;
        r7 = Math.min(r72.x, r72.y);
        Point r82 = AndroidUtilities.displaySize;
        r8 = Math.max(r82.x, r82.y);
        goto L11
    L6:
        r1 = false;
        goto L7
    }

    public static void createDialogsResources(Context r11) {
        createCommonResources(r11);
        createCommonDialogResources(r11);
        if (dialogs_namePaint != null) goto L9;
        Resources r112 = r11.getResources();
        dialogs_namePaint = new TextPaint[2];
        dialogs_nameEncryptedPaint = new TextPaint[2];
        dialogs_messagePaint = new TextPaint[2];
        dialogs_messagePrintingPaint = new TextPaint[2];
        int r3 = 0;
    L6:
        if (r3 >= 2) goto L8;
        dialogs_namePaint[r3] = new TextPaint(1);
        dialogs_namePaint[r3].setTypeface(AndroidUtilities.bold());
        dialogs_nameEncryptedPaint[r3] = new TextPaint(1);
        dialogs_nameEncryptedPaint[r3].setTypeface(AndroidUtilities.bold());
        dialogs_messagePaint[r3] = new TextPaint(1);
        dialogs_messagePrintingPaint[r3] = new TextPaint(1);
        r3 = r3 + 1;
        goto L6
    L8:
        TextPaint r32 = new TextPaint(1);
        dialogs_searchNamePaint = r32;
        r32.setTypeface(AndroidUtilities.bold());
        TextPaint r33 = new TextPaint(1);
        dialogs_searchNameEncryptedPaint = r33;
        r33.setTypeface(AndroidUtilities.bold());
        TextPaint r34 = new TextPaint(1);
        dialogs_messageNamePaint = r34;
        r34.setTypeface(AndroidUtilities.bold());
        dialogs_timePaint = new TextPaint(1);
        dialogs_timePaintBold = new TextPaint(1);
        dialogs_timePaintBoldAccent = new TextPaint(1);
        TextPaint r35 = new TextPaint(1);
        dialogs_archiveTextPaint = r35;
        r35.setTypeface(AndroidUtilities.bold());
        TextPaint r36 = new TextPaint(1);
        dialogs_archiveTextPaintSmall = r36;
        r36.setTypeface(AndroidUtilities.bold());
        dialogs_onlinePaint = new TextPaint(1);
        dialogs_offlinePaint = new TextPaint(1);
        TextPaint r37 = new TextPaint(1);
        dialogs_tagTextPaint = r37;
        r37.setTypeface(AndroidUtilities.bold());
        dialogs_tabletSeletedPaint = new Paint();
        dialogs_pinnedPaint = new Paint(1);
        dialogs_countGrayPaint = new Paint(1);
        dialogs_errorPaint = new Paint(1);
        dialogs_actionMessagePaint = new Paint(1);
        dialogs_lockDrawable = r112.getDrawable(R.drawable.list_secret);
        dialogs_lock2Drawable = r112.getDrawable(R.drawable.msg_mini_lock2);
        dialogs_checkDrawable = r112.getDrawable(R.drawable.list_check).mutate();
        dialogs_communityCardsDrawable = r112.getDrawable(R.drawable.community_cards).mutate();
        dialogs_playDrawable = r112.getDrawable(R.drawable.minithumb_play).mutate();
        dialogs_checkReadDrawable = r112.getDrawable(R.drawable.list_check).mutate();
        dialogs_halfCheckDrawable = r112.getDrawable(R.drawable.list_halfcheck);
        dialogs_clockDrawable = new MsgClockDrawable();
        dialogs_errorDrawable = r112.getDrawable(R.drawable.list_warning_sign);
        dialogs_reorderDrawable = r112.getDrawable(R.drawable.list_reorder).mutate();
        dialogs_muteDrawable = r112.getDrawable(R.drawable.list_mute).mutate();
        dialogs_unmuteDrawable = r112.getDrawable(R.drawable.list_unmute).mutate();
        dialogs_hiddenDrawable = r112.getDrawable(R.drawable.mini_ephemeral_hidden_16).mutate();
        dialogs_verifiedDrawable = r112.getDrawable(R.drawable.verified_area).mutate();
        dialogs_holidayDrawable = r112.getDrawable(R.drawable.newyear).mutate();
        dialogs_scamDrawable = new ScamDrawable(11, 0);
        dialogs_fakeDrawable = new ScamDrawable(11, 1);
        dialogs_verifiedCheckDrawable = r112.getDrawable(R.drawable.verified_check).mutate();
        dialogs_mentionDrawable = r112.getDrawable(R.drawable.filled_chatlist_mention).mutate();
        dialogs_reactionsMentionDrawable = r112.getDrawable(R.drawable.filled_chatlist_reaction).mutate();
        dialogs_pollMentionDrawable = r112.getDrawable(R.drawable.filled_chatlist_poll).mutate();
        dialogs_mentionDrawableMuted = r112.getDrawable(R.drawable.filled_chatlist_mention).mutate();
        dialogs_reactionsMentionDrawableMuted = r112.getDrawable(R.drawable.filled_chatlist_reaction).mutate();
        dialogs_pollMentionDrawableMuted = r112.getDrawable(R.drawable.filled_chatlist_poll).mutate();
        dialogs_pinnedDrawable = r112.getDrawable(R.drawable.list_pin);
        dialogs_pinnedDrawable2 = r112.getDrawable(R.drawable.msg_pin_mini).mutate();
        dialogs_pinnedDrawable2Accent = r112.getDrawable(R.drawable.msg_pin_mini).mutate();
        dialogs_forum_arrowDrawable = r112.getDrawable(R.drawable.msg_mini_forumarrow);
        moveUpDrawable = r112.getDrawable(R.drawable.preview_arrow);
        RectF r113 = new RectF();
        chat_updatePath[0] = new Path();
        chat_updatePath[2] = new Path();
        float r38 = AndroidUtilities.dp(12.0f);
        float r5 = AndroidUtilities.dp(12.0f);
        r113.set(r38 - AndroidUtilities.dp(5.0f), r5 - AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f) + r38, AndroidUtilities.dp(5.0f) + r5);
        chat_updatePath[2].arcTo(r113, -160.0f, -110.0f, true);
        chat_updatePath[2].arcTo(r113, 20.0f, -110.0f, true);
        chat_updatePath[0].moveTo(r38, AndroidUtilities.dp(8.0f) + r5);
        chat_updatePath[0].lineTo(r38, AndroidUtilities.dp(2.0f) + r5);
        chat_updatePath[0].lineTo(AndroidUtilities.dp(3.0f) + r38, AndroidUtilities.dp(5.0f) + r5);
        chat_updatePath[0].close();
        chat_updatePath[0].moveTo(r38, r5 - AndroidUtilities.dp(8.0f));
        chat_updatePath[0].lineTo(r38, r5 - AndroidUtilities.dp(2.0f));
        chat_updatePath[0].lineTo(r38 - AndroidUtilities.dp(3.0f), r5 - AndroidUtilities.dp(5.0f));
        chat_updatePath[0].close();
        applyDialogsTheme();
    L9:
        dialogs_messageNamePaint.setTextSize(AndroidUtilities.dp(14.0f));
        dialogs_timePaint.setTextSize(AndroidUtilities.dp(12.0f));
        dialogs_timePaintBold.setTextSize(AndroidUtilities.dp(12.0f));
        dialogs_timePaintBold.setTypeface(AndroidUtilities.bold());
        dialogs_timePaintBoldAccent.setTextSize(AndroidUtilities.dp(12.0f));
        dialogs_timePaintBoldAccent.setTypeface(AndroidUtilities.bold());
        dialogs_archiveTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        dialogs_archiveTextPaintSmall.setTextSize(AndroidUtilities.dp(11.0f));
        dialogs_onlinePaint.setTextSize(AndroidUtilities.dp(15.0f));
        dialogs_offlinePaint.setTextSize(AndroidUtilities.dp(15.0f));
        dialogs_tagTextPaint.setTextSize(AndroidUtilities.dp(10.0f));
        dialogs_searchNamePaint.setTextSize(AndroidUtilities.dp(16.0f));
        dialogs_searchNameEncryptedPaint.setTextSize(AndroidUtilities.dp(16.0f));
    }

    public static Drawable createEditTextDrawable(Context r3, int r4, int r5) {
        Resources r32 = r3.getResources();
        Drawable r0 = r32.getDrawable(R.drawable.search_dark).mutate();
        PorterDuff.Mode r2 = PorterDuff.Mode.MULTIPLY;
        r0.setColorFilter(new PorterDuffColorFilter(r4, r2));
        Drawable r33 = r32.getDrawable(R.drawable.search_dark_activated).mutate();
        r33.setColorFilter(new PorterDuffColorFilter(r5, r2));
        AnonymousClass4 r42 = new AnonymousClass4();
        r42.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, r33);
        r42.addState(new int[]{android.R.attr.state_focused}, r33);
        r42.addState(StateSet.WILD_CARD, r0);
        return r42;
    }

    public static Drawable createEditTextDrawable(Context r1, boolean r2) {
        if (r2 == false) goto L4;
        int r0 = key_dialogInputField;
    L5:
        int r02 = getColor(r0);
        if (r2 == false) goto L8;
        int r22 = key_dialogInputFieldActivated;
    L10:
        return createEditTextDrawable(r1, r02, getColor(r22));
    L8:
        r22 = key_windowBackgroundWhiteInputFieldActivated;
        goto L10
    L4:
        r0 = key_windowBackgroundWhiteInputField;
        goto L5
    }

    public static Drawable createEmojiIconSelectorDrawable(Context r3, int r4, int r5, int r6) {
        Resources r32 = r3.getResources();
        Drawable r0 = r32.getDrawable(r4).mutate();
        if (r5 == 0) goto L5;
        r0.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));
    L5:
        Drawable r33 = r32.getDrawable(r4).mutate();
        if (r6 == 0) goto L8;
        r33.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.MULTIPLY));
    L8:
        AnonymousClass3 r42 = new AnonymousClass3();
        r42.setEnterFadeDuration(1);
        r42.setExitFadeDuration(200);
        r42.addState(new int[]{android.R.attr.state_selected}, r33);
        r42.addState(new int[0], r0);
        return r42;
    }

    public static Drawable createInsetRoundRectDrawable(int r0, float r1, int r2) {
        return createInsetRoundRectDrawable(r0, r1, r2, r2);
    }

    public static Drawable createInsetRoundRectDrawable(int r6, float r7, int r8, int r9) {
        return createInsetRoundRectDrawable(r6, r7, r8, r9, r8, r9);
    }

    public static Drawable createInsetRoundRectDrawable(int r8, float r9, int r10, int r11, int r12, int r13) {
        maskPaint.setColor(-1);
        AnonymousClass9 r2 = new AnonymousClass9(r10, r11, r12, r13, r9);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r8}), null, r2);
    }

    public static ThemeInfo createNewTheme(String r6) {
        ThemeInfo r0 = new ThemeInfo();
        r0.pathToFile = new File(ApplicationLoader.getFilesDirFixed(), "theme" + Utilities.random.nextLong() + ".attheme").getAbsolutePath();
        r0.name = r6;
        themedWallpaperLink = getWallpaperUrl(currentTheme.overrideWallpaper);
        r0.account = UserConfig.selectedAccount;
        saveCurrentTheme(r0, true, true, false);
        return r0;
    }

    public static Drawable createOutlineCircleDrawable(int r1, int r2, int r3) {
        return new AnonymousClass6(r3, r2, r1);
    }

    public static void createProfileResources(Context r2) {
        if (profile_verifiedDrawable != null) goto L5;
        profile_aboutTextPaint = new TextPaint(1);
        Resources r22 = r2.getResources();
        profile_verifiedDrawable = r22.getDrawable(R.drawable.verified_area).mutate();
        profile_verifiedCheckDrawable = r22.getDrawable(R.drawable.verified_check).mutate();
        applyProfileTheme();
    L5:
        profile_aboutTextPaint.setTextSize(AndroidUtilities.dp(16.0f));
    }

    public static Drawable createRadSelectorDrawable(int r2, int r3, int r4) {
        maskPaint.setColor(-1);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r2}), null, new RippleRadMaskDrawable(r3, r4));
    }

    public static Drawable createRadSelectorDrawable(int r3, int r4, int r5, int r6) {
        maskPaint.setColor(-1);
        float r52 = r5;
        float r62 = r6;
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r4}), createRoundRectDrawable(AndroidUtilities.dp(r52), AndroidUtilities.dp(r62), r3), new RippleRadMaskDrawable(r52, r62));
    }

    public static Drawable createRadSelectorDrawable(int r2, int r3, int r4, int r5, int r6) {
        maskPaint.setColor(-1);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r2}), null, new RippleRadMaskDrawable(r3, r4, r5, r6));
    }

    public static ShapeDrawable createRoundRectDrawable(int r4, int r5) {
        float r42 = r4;
        ShapeDrawable r0 = new ShapeDrawable(new RoundRectShape(new float[]{r42, r42, r42, r42, r42, r42, r42, r42}, null, null));
        r0.getPaint().setColor(r5);
        return r0;
    }

    public static ShapeDrawable createRoundRectDrawable(int r4, int r5, int r6) {
        float r42 = r4;
        float r52 = r5;
        ShapeDrawable r0 = new ShapeDrawable(new RoundRectShape(new float[]{r42, r42, r42, r42, r52, r52, r52, r52}, null, null));
        r0.getPaint().setColor(r6);
        return r0;
    }

    public static ShapeDrawable createRoundRectDrawable(int r4, int r5, int r6, int r7, int r8) {
        float r42 = r4;
        float r52 = r5;
        float r62 = r6;
        float r72 = r7;
        ShapeDrawable r0 = new ShapeDrawable(new RoundRectShape(new float[]{r42, r42, r52, r52, r62, r62, r72, r72}, null, null));
        r0.getPaint().setColor(r8);
        return r0;
    }

    public static InsetDrawable createRoundRectDrawableShadowed(int r6, int r7) {
        float r62 = r6;
        ShapeDrawable r1 = new ShapeDrawable(new RoundRectShape(new float[]{r62, r62, r62, r62, r62, r62, r62, r62}, null, null));
        r1.getPaint().setColor(r7);
        r1.getPaint().setShadowLayer(AndroidUtilities.dpf2(2.0f), 0.0f, AndroidUtilities.dpf2(0.33f), multAlpha(285212672, Color.alpha(r7) / 255.0f));
        return new InsetDrawable(r1, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
    }

    public static GradientDrawable createRoundRectGradientDrawable(int r2, int r3, int r4) {
        GradientDrawable r0 = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, new int[]{r3, r4});
        r0.setShape(0);
        r0.setCornerRadius(r2);
        return r0;
    }

    public static Drawable createSelectorDrawable(int r2) {
        return createSelectorDrawable(r2, 1, -1);
    }

    public static Drawable createSelectorDrawable(int r1, int r2) {
        return createSelectorDrawable(r1, r2, -1);
    }

    public static Drawable createSelectorDrawable(int r7, int r8, int r9) {
        if (r8 == 1) goto L20;
        if (r8 == 5) goto L20;
        if (r8 != 1) goto L8;
    L21:
        maskPaint.setColor(-1);
        Drawable r4 = new AnonymousClass8(r8, r9);
    L22:
        BaseCell.RippleDrawableSafe r72 = new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r7}), null, r4);
        if (r8 != 1) goto L28;
        if (r9 > 0) goto L26;
        r9 = AndroidUtilities.dp(20.0f);
    L26:
        r72.setRadius(r9);
        return r72;
    L28:
        if (r8 != 5) goto L30;
        r72.setRadius(-1);
    L30:
        return r72;
    L8:
        if (r8 == 3) goto L21;
        if (r8 == 4) goto L21;
        if (r8 == 5) goto L21;
        if (r8 == 6) goto L21;
        if (r8 == 7) goto L21;
        if (r8 != 2) goto L20;
        r4 = new ColorDrawable(-1);
    L20:
        r4 = null;
        goto L22
    }

    public static Drawable createSelectorDrawableFromDrawables(Drawable r2, Drawable r3) {
        StateListDrawable r0 = new StateListDrawable();
        r0.addState(new int[]{android.R.attr.state_pressed}, r3);
        r0.addState(new int[]{android.R.attr.state_selected}, r3);
        r0.addState(StateSet.WILD_CARD, r2);
        return r0;
    }

    public static Drawable createSelectorWithBackgroundDrawable(int r3, int r4) {
        ColorDrawable r0 = new ColorDrawable(r3);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r4}), new ColorDrawable(r3), r0);
    }

    public static Drawable createServiceDrawable(int r1, View r2, View r3) {
        return createServiceDrawable(r1, r2, r3, chat_actionBackgroundPaint);
    }

    public static Drawable createServiceDrawable(int r1, View r2, View r3, Paint r4) {
        return createServiceDrawable(r1, r2, r3, r4, null);
    }

    public static Drawable createServiceDrawable(int r6, View r7, View r8, Paint r9, ResourcesProvider r10) {
        return new AnonymousClass7(r7, r8, r6, r9, r10);
    }

    public static Drawable createSimpleSelectorCircleDrawable(int r2, int r3, int r4) {
        OvalShape r0 = new OvalShape();
        float r22 = r2;
        r0.resize(r22, r22);
        ShapeDrawable r23 = new ShapeDrawable(r0);
        r23.getPaint().setColor(r3);
        ShapeDrawable r32 = new ShapeDrawable(r0);
        r32.getPaint().setColor(-1);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r4}), r23, r32);
    }

    public static Drawable createSimpleSelectorRoundRectDrawable(int r0, int r1, int r2) {
        return createSimpleSelectorRoundRectDrawable(r0, r1, r2, r2);
    }

    public static Drawable createSimpleSelectorRoundRectDrawable(int r7, int r8, int r9, int r10) {
        return createSimpleSelectorRoundRectDrawable(r7, r7, r7, r7, r8, r9, r10);
    }

    public static Drawable createSimpleSelectorRoundRectDrawable(int r2, int r3, int r4, int r5, int r6, int r7, int r8) {
        float r22 = r2;
        float r32 = r3;
        float r42 = r4;
        float r52 = r5;
        return createSimpleSelectorRoundRectDrawable(new float[]{r22, r22, r32, r32, r42, r42, r52, r52}, r6, r7, r8);
    }

    private static Drawable createSimpleSelectorRoundRectDrawable(float[] r1, int r2, int r3, int r4) {
        return createSimpleSelectorRoundRectDrawable(r1, r2, r3, r4, 0);
    }

    private static Drawable createSimpleSelectorRoundRectDrawable(float[] r3, int r4, int r5, int r6, int r7) {
        ShapeDrawable r0 = new ShapeDrawable(new RoundRectShape(r3, null, null));
        r0.setPadding(r7, r7, r7, r7);
        r0.getPaint().setColor(r4);
        ShapeDrawable r42 = new ShapeDrawable(new RoundRectShape(r3, null, null));
        r42.getPaint().setColor(r6);
        r42.setPadding(r7, r7, r7, r7);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r5}), r0, r42);
    }

    public static String createThemePreviewImage(String r35, String r36, ThemeAccent r37) {
        String[] r6 = new String[1];     // Catch: Throwable -> L6
        SparseIntArray r7 = getThemeFileValues(new File(r35), null, r6);     // Catch: Throwable -> L6
        if (r37 == null) goto L8;
        checkIsDark(r7, r37.parentTheme);     // Catch: Throwable -> L6
    L8:
        int r8 = currentColorsNoAccent.get(key_wallpaperFileOffset, -1);     // Catch: Throwable -> L6
        Bitmap r10 = Bitmaps.createBitmap(560, 678, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L6
        Canvas r11 = new Canvas(r10);     // Catch: Throwable -> L6
        Paint r12 = new Paint();     // Catch: Throwable -> L6
        int r13 = getPreviewColor(r7, key_actionBarDefault);     // Catch: Throwable -> L6
        int r14 = getPreviewColor(r7, key_actionBarDefaultIcon);     // Catch: Throwable -> L6
        int r15 = getPreviewColor(r7, key_chat_messagePanelBackground);     // Catch: Throwable -> L6
        int r9 = getPreviewColor(r7, key_chat_messagePanelIcons);     // Catch: Throwable -> L6
        int r17 = getPreviewColor(r7, key_chat_inBubble);     // Catch: Throwable -> L6
        int r18 = getPreviewColor(r7, key_chat_outBubble);     // Catch: Throwable -> L6
        r7.get(key_chat_outBubbleGradient1);     // Catch: Throwable -> L6
        int r0 = r7.get(key_chat_wallpaper);     // Catch: Throwable -> L6
        int r4 = r7.get(key_chat_wallpaper_gradient_to1);     // Catch: Throwable -> L6
        int r5 = r7.get(key_chat_wallpaper_gradient_to2);     // Catch: Throwable -> L6
        int r21 = r0;
        int r02 = r7.get(key_chat_wallpaper_gradient_to3);     // Catch: Throwable -> L6
        if (r37 == null) goto L11;
        Paint r23 = r12;
        int r24 = r13;
        int r122 = (int) r37.backgroundOverrideColor;     // Catch: Throwable -> L6
    L13:
        if (r122 != 0) goto L18;
        if (r37 == null) goto L18;
        int r27 = r122;
        if (r37.backgroundOverrideColor == 0) goto L19;
        r21 = 0;
    L21:
        if (r37 == null) goto L23;
        int r123 = (int) r37.backgroundGradientOverrideColor1;     // Catch: Throwable -> L6
    L24:
        if (r123 != 0) goto L29;
        if (r37 == null) goto L29;
        int r272 = r123;
        if (r37.backgroundGradientOverrideColor1 == 0) goto L30;
        r4 = 0;
    L32:
        if (r37 == null) goto L34;
        int r124 = (int) r37.backgroundGradientOverrideColor2;     // Catch: Throwable -> L6
    L35:
        if (r124 != 0) goto L40;
        if (r37 == null) goto L40;
        int r273 = r124;
        if (r37.backgroundGradientOverrideColor2 == 0) goto L41;
        int r125 = 0;
    L44:
        if (r37 == null) goto L46;
        int r274 = r125;
        int r126 = (int) r37.backgroundGradientOverrideColor3;     // Catch: Throwable -> L6
    L47:
        if (r126 != 0) goto L52;
        if (r37 == null) goto L52;
        int r28 = r126;
        if (r37.backgroundGradientOverrideColor3 == 0) goto L53;
        int r127 = 0;
    L57:
        int r25 = r127;
        if (TextUtils.isEmpty(r6[0]) == false) goto L270;
        int r31 = r4;
        int r30 = r5;
        String[] r32 = r6;
        int r52 = r25;
        int r42 = r274;
        int r62 = r31;
    L121:
        Drawable r128 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_back).mutate();     // Catch: Throwable -> L6
        setDrawableColor(r128, r14);     // Catch: Throwable -> L6
        Drawable r132 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_dots).mutate();     // Catch: Throwable -> L6
        setDrawableColor(r132, r14);     // Catch: Throwable -> L6
        Drawable r142 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_smile).mutate();     // Catch: Throwable -> L6
        setDrawableColor(r142, r9);     // Catch: Throwable -> L6
        Drawable r152 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_mic).mutate();     // Catch: Throwable -> L6
        setDrawableColor(r152, r9);     // Catch: Throwable -> L6
        int r92 = 2;
        MessageDrawable[] r129 = new MessageDrawable[2];     // Catch: Throwable -> L6
        int r03 = 0;
    L122:
        if (r03 >= r92) goto L133;
        int r312 = r42;
        if (r03 != 1) goto L127;
        boolean r43 = true;
    L126:
        int r33 = r52;
        int r34 = r62;
        AnonymousClass12 r93 = new AnonymousClass12(2, r43, false, r7);     // Catch: Throwable -> L6
        r129[r03] = r93;     // Catch: Throwable -> L6
        if (r03 != 0) goto L131;
        int r44 = r17;
    L132:
        setDrawableColor(r93, r44);     // Catch: Throwable -> L6
        r03 = r03 + 1;     // Catch: Throwable -> L6
        r42 = r312;
        r52 = r33;
        r62 = r34;
        r92 = 2;
        goto L122
    L131:
        r44 = r18;
        goto L132
    L127:
        r43 = false;
        goto L126
    L133:
        int r313 = r42;
        int r332 = r52;
        int r342 = r62;
        RectF r04 = new RectF();     // Catch: Throwable -> L6
        int r182 = 80;
        if (r36 != null) goto L299;
        int r2 = r21;
        if (r2 == 0) goto L184;
        if (r342 != 0) goto L177;
        Drawable r05 = new ColorDrawable(r2);     // Catch: Throwable -> L6
    L183:
        r05.setBounds(0, 120, r10.getWidth(), r10.getHeight() - 120);     // Catch: Throwable -> L6
        r05.draw(r11);     // Catch: Throwable -> L6
        int r06 = r182;
        boolean r45 = true;
    L232:
        if (r45 == true) goto L235;
        Drawable r1 = createDefaultWallpaper(r10.getWidth(), r10.getHeight() - 120);     // Catch: Throwable -> L6
        boolean r63 = false;
        r1.setBounds(0, 120, r10.getWidth(), r10.getHeight() - 120);     // Catch: Throwable -> L6
        r1.draw(r11);     // Catch: Throwable -> L6
    L234:
        Paint r16 = r23;
        r16.setColor(r24);     // Catch: Throwable -> L6
        boolean r29 = r63;
        r11.drawRect(0.0f, 0.0f, r10.getWidth(), 120.0f, r16);     // Catch: Throwable -> L6
        if (r128 == null) goto L239;
        int r1210 = (120 - r128.getIntrinsicHeight()) / 2;     // Catch: Throwable -> L6
        r128.setBounds(13, r1210, r128.getIntrinsicWidth() + 13, r128.getIntrinsicHeight() + r1210);     // Catch: Throwable -> L6
        r128.draw(r11);     // Catch: Throwable -> L6
    L239:
        if (r132 == null) goto L241;
        int r22 = (r10.getWidth() - r132.getIntrinsicWidth()) - 10;
        int r1211 = (120 - r132.getIntrinsicHeight()) / 2;     // Catch: Throwable -> L6
        r132.setBounds(r22, r1211, r132.getIntrinsicWidth() + r22, r132.getIntrinsicHeight() + r1211);     // Catch: Throwable -> L6
        r132.draw(r11);     // Catch: Throwable -> L6
    L241:
        r129[1].setBounds(161, Opcodes.ADD_INT_LIT8, r10.getWidth() - 20, HttpStatusCodesKt.HTTP_PERM_REDIRECT);     // Catch: Throwable -> L6
        r129[1].setTop(0, 560, 522, false, false);     // Catch: Throwable -> L6
        r129[1].draw(r11);     // Catch: Throwable -> L6
        r129[1].setBounds(161, 430, r10.getWidth() - 20, 522);     // Catch: Throwable -> L6
        r129[1].setTop(430, 560, 522, false, false);     // Catch: Throwable -> L6
        r129[1].draw(r11);     // Catch: Throwable -> L6
        r129[r29 ? 1 : 0].setBounds(20, 323, 399, 415);     // Catch: Throwable -> L6
        r129[r29 ? 1 : 0].setTop(323, 560, 522, false, false);     // Catch: Throwable -> L6
        r129[r29 ? 1 : 0].draw(r11);     // Catch: Throwable -> L6
        r16.setColor(r15);     // Catch: Throwable -> L6
        r11.drawRect(0.0f, r10.getHeight() - 120, r10.getWidth(), r10.getHeight(), r16);     // Catch: Throwable -> L6
        if (r142 == null) goto L244;
        int r26 = (r10.getHeight() - 120) + ((120 - r142.getIntrinsicHeight()) / 2);     // Catch: Throwable -> L6
        r142.setBounds(22, r26, r142.getIntrinsicWidth() + 22, r142.getIntrinsicHeight() + r26);     // Catch: Throwable -> L6
        r142.draw(r11);     // Catch: Throwable -> L6
    L244:
        if (r152 == null) goto L246;
        int r210 = (r10.getWidth() - r152.getIntrinsicWidth()) - 22;     // Catch: Throwable -> L6
        int r19 = (r10.getHeight() - 120) + ((120 - r152.getIntrinsicHeight()) / 2);     // Catch: Throwable -> L6
        r152.setBounds(r210, r19, r152.getIntrinsicWidth() + r210, r152.getIntrinsicHeight() + r19);     // Catch: Throwable -> L6
        r152.draw(r11);     // Catch: Throwable -> L6
    L246:
        r11.setBitmap(null);     // Catch: Throwable -> L6
        File r211 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".jpg");     // Catch: Throwable -> L6
        r10.compress(Bitmap.CompressFormat.JPEG, r06, new FileOutputStream(r211));     // Catch: Throwable -> L249
        SharedConfig.saveConfig();     // Catch: Throwable -> L249
        return r211.getAbsolutePath();
    L249:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L6
        return null;
    L235:
        r63 = false;
        goto L234
    L177:
        if (r313 == 0) goto L179;
        r05 = new MotionBackgroundDrawable(r2, r342, r313, r332, true);     // Catch: Throwable -> L6
        goto L183
    L179:
        int r07 = r7.get(key_chat_wallpaper_gradient_rotation, -1);     // Catch: Throwable -> L6
        if (r07 != (-1)) goto L182;
        r07 = 45;
    L182:
        r05 = BackgroundGradientDrawable.createDitheredGradientBitmapDrawable(r07, new int[]{r2, r30}, r10.getWidth(), r10.getHeight() - 120);     // Catch: Throwable -> L6
        r182 = 90;
        goto L183
    L184:
        if (r8 < 0) goto L186;
    L284:
        BitmapFactory.Options r212 = new BitmapFactory.Options();     // Catch: Throwable -> L192
        r212.inJustDecodeBounds = true;     // Catch: Throwable -> L192
        if (TextUtils.isEmpty(r32[0]) == true) goto L194;
        File r3 = ApplicationLoader.getFilesDirFixed();     // Catch: Throwable -> L192
        StringBuilder r72 = new StringBuilder();     // Catch: Throwable -> L192
        float r213 = 1.0f;
        r72.append(Utilities.MD5(r32[0]));     // Catch: Throwable -> L192
        r72.append(".wp");     // Catch: Throwable -> L192
        File r110 = new File(r3, r72.toString());     // Catch: Throwable -> L192
        BitmapFactory.decodeFile(r110.getAbsolutePath(), r212);     // Catch: Throwable -> L192
        float r73 = 560.0f;
        Bitmap r46 = r10;
        FileInputStream r38 = null;
    L198:
        int r94 = r212.outWidth;     // Catch: Throwable -> L207
        if (r94 <= 0) goto L216;
        int r102 = r212.outHeight;     // Catch: Throwable -> L207
        if (r102 <= 0) goto L216;
        float r95 = Math.min(r94 / r73, r102 / r73);     // Catch: Throwable -> L207
        r212.inSampleSize = 1;     // Catch: Throwable -> L207
        if (r95 <= r213) goto L209;
    L204:
        int r103 = r212.inSampleSize * 2;     // Catch: Throwable -> L207
        r212.inSampleSize = r103;     // Catch: Throwable -> L207
        if (r103 < r95) goto L204;
    L209:
        r212.inJustDecodeBounds = false;     // Catch: Throwable -> L207
        if (r110 == null) goto L212;
        Bitmap r111 = BitmapFactory.decodeFile(r110.getAbsolutePath(), r212);     // Catch: Throwable -> L207
    L213:
        if (r111 == null) goto L216;
        Paint r214 = new Paint();     // Catch: Throwable -> L207
        r214.setFilterBitmap(true);     // Catch: Throwable -> L207
        float r74 = Math.min(r111.getWidth() / r73, r111.getHeight() / r73);     // Catch: Throwable -> L207
        r04.set(0.0f, 0.0f, r111.getWidth() / r74, r111.getHeight() / r74);     // Catch: Throwable -> L207
        r04.offset((r46.getWidth() - r04.width()) / 2.0f, (r46.getHeight() - r04.height()) / 2.0f);     // Catch: Throwable -> L207
        r11.drawBitmap(r111, null, r04, r214);     // Catch: Throwable -> L207
        boolean r112 = true;
    L217:
        if (r38 != null) goto L287;
    L222:
        r10 = r46;
        r06 = 80;
        r45 = r112;
        goto L232
    L287:
        r38.close();     // Catch: Throwable -> L6 Exception -> L220
    L220:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
    L207:
        th = th;
    L280:
        FileLog.e(th);     // Catch: Throwable -> L252
        if (r38 != null) goto L264;
    L231:
        r10 = r46;
    L172:
        r06 = 80;
        r45 = false;
        goto L232
    L264:
        r38.close();     // Catch: Throwable -> L6 Exception -> L229
    L229:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        goto L231
    L252:
        th = move-exception;
        if (r38 == null) goto L308;
        r38.close();     // Catch: Throwable -> L6 Exception -> L257
    L309:
        throw th;     // Catch: Throwable -> L6
    L257:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        throw th;     // Catch: Throwable -> L6
    L308:
        throw th;     // Catch: Throwable -> L6
    L212:
        r38.getChannel().position(r8);     // Catch: Throwable -> L207
        r111 = BitmapFactory.decodeStream(r38, null, r212);     // Catch: Throwable -> L207
    L216:
        r112 = false;
        goto L217
    L194:
        r213 = 1.0f;
        r38 = new FileInputStream(r35);     // Catch: Throwable -> L192
        r73 = 560.0f;
        r46 = r10;
        r38.getChannel().position(r8);     // Catch: Throwable -> L207
        BitmapFactory.decodeStream(r38, null, r212);     // Catch: Throwable -> L207
        r110 = null;
    L223:
        th = th;
        r46 = r10;
    L192:
        th = th;
        r46 = r10;
        r38 = null;
        goto L280
    L186:
        if (TextUtils.isEmpty(r32[0]) == false) goto L284;
        r46 = r10;
        goto L231
    L299:
        BitmapFactory.Options r113 = new BitmapFactory.Options();     // Catch: Throwable -> L149
        r113.inJustDecodeBounds = true;     // Catch: Throwable -> L149
        BitmapFactory.decodeFile(r36, r113);     // Catch: Throwable -> L149
        int r75 = r113.outWidth;     // Catch: Throwable -> L149
        if (r75 <= 0) goto L169;
        int r82 = r113.outHeight;     // Catch: Throwable -> L149
        if (r82 <= 0) goto L169;
        float r76 = Math.min(r75 / 560.0f, r82 / 560.0f);     // Catch: Throwable -> L149
        r113.inSampleSize = 1;     // Catch: Throwable -> L149
        if (r76 <= 1.0f) goto L152;
    L142:
        int r47 = r113.inSampleSize * 2;
        r113.inSampleSize = r47;     // Catch: Throwable -> L147
        if (r47 < r76) goto L142;
    L147:
        th = th;
    L171:
        FileLog.e(th);     // Catch: Throwable -> L6
    L152:
        Bitmap.Config r48 = Bitmap.Config.ALPHA_8;     // Catch: Throwable -> L147
        r113.inPreferredConfig = r48;     // Catch: Throwable -> L147
        r113.inJustDecodeBounds = false;     // Catch: Throwable -> L147
        Bitmap r114 = BitmapFactory.decodeFile(r36, r113);     // Catch: Throwable -> L147
        if (r114 == null) goto L169;
        if (r313 == 0) goto L166;
        if (r37 == null) goto L166;
        MotionBackgroundDrawable r49 = new MotionBackgroundDrawable(r21, r342, r313, r332, true);     // Catch: Throwable -> L147
        if (r10 != null) goto L159;
    L165:
        r49.setPatternBitmap((int) (r37.patternIntensity * 100.0f), r114);     // Catch: Throwable -> L147
        r49.setBounds(0, 0, r10.getWidth(), r10.getHeight());     // Catch: Throwable -> L147
        r49.draw(r11);     // Catch: Throwable -> L147
    L167:
        boolean r08 = true;
    L170:
        r45 = r08;
        r06 = 80;
        goto L232
    L159:
        if (r10.getConfig() == r48) goto L165;
        Bitmap r215 = r10.copy(r48, false);     // Catch: Throwable -> L147
        r10.recycle();     // Catch: Throwable -> L163
        r10 = r215;
    L163:
        th = th;
        r10 = r215;
    L166:
        Paint r216 = new Paint();     // Catch: Throwable -> L147
        r216.setFilterBitmap(true);     // Catch: Throwable -> L147
        float r39 = Math.min(r114.getWidth() / 560.0f, r114.getHeight() / 560.0f);     // Catch: Throwable -> L147
        r04.set(0.0f, 0.0f, r114.getWidth() / r39, r114.getHeight() / r39);     // Catch: Throwable -> L147
        r04.offset((r10.getWidth() - r04.width()) / 2.0f, (r10.getHeight() - r04.height()) / 2.0f);     // Catch: Throwable -> L147
        r11.drawBitmap(r114, null, r04, r216);     // Catch: Throwable -> L147
    L169:
        r08 = false;
    L149:
        th = th;
        goto L171
    L270:
        String r09 = Uri.parse(r6[0]).getQueryParameter("bg_color");     // Catch: Throwable -> L6 Exception -> L115
        if (r37 != null) goto L62;
    L117:
        r30 = r5;
        r32 = r6;
        int r1212 = r274;
        r4 = r4;
    L118:
        r62 = r4;
        r42 = r1212;
        r52 = r25;
        goto L121
    L62:
        if (TextUtils.isEmpty(r09) == true) goto L117;
        int r133 = Integer.parseInt(r09.substring(0, 6), 16) | (-16777216);
        int r314 = r4;
        r30 = r5;
        r37.backgroundOverrideColor = r133;     // Catch: Throwable -> L6 Exception -> L113
        if (r09.length() >= 13) goto L285;
    L80:
        String[] r1213 = r6;
        r4 = r314;
    L291:
        if (r09.length() >= 20) goto L289;
    L95:
        r32 = r1213;
        int r64 = r133;
        r1212 = r274;
    L272:
    L108:
        e = e;
        int r153 = r64;
    L107:
        r21 = r153;
    L119:
        FileLog.e(e);     // Catch: Throwable -> L6
        goto L118
    L97:
        if (r09.length() == 27) goto L99;
    L110:
        r153 = r64;
    L104:
        r21 = r153;
        goto L118
    L99:
        if (AndroidUtilities.isValidWallChar(r09.charAt(20)) == false) goto L110;
        int r134 = Integer.parseInt(r09.substring(21), 16) | (-16777216);
        r153 = r64;
        r37.backgroundGradientOverrideColor3 = r134;     // Catch: Throwable -> L6 Exception -> L105
        r25 = r134;
    L105:
        e = e;
        r25 = r134;
        goto L107
    L289:
    L93:
        e = e;
        r32 = r1213;
        r21 = r133;
    L76:
        r1212 = r274;
        goto L119
    L86:
        if (AndroidUtilities.isValidWallChar(r09.charAt(13)) == false) goto L95;
        int r53 = Integer.parseInt(r09.substring(14, 20), 16) | (-16777216);
        r32 = r1213;
        r64 = r133;
        r37.backgroundGradientOverrideColor2 = r53;     // Catch: Throwable -> L6 Exception -> L91
        r1212 = r53;
    L91:
        e = e;
        r1212 = r53;
        r21 = r64;
    L111:
        e = e;
        r32 = r1213;
        r21 = r133;
        goto L76
    L285:
    L77:
        e = e;
        r32 = r6;
        r21 = r133;
    L79:
        r1212 = r274;
        r4 = r314;
        goto L119
    L69:
        if (AndroidUtilities.isValidWallChar(r09.charAt(6)) == false) goto L80;
        r4 = Integer.parseInt(r09.substring(7, 13), 16) | (-16777216);
        r1213 = r6;
        r37.backgroundGradientOverrideColor1 = r4;     // Catch: Throwable -> L6 Exception -> L74
    L74:
        e = e;
        r32 = r1213;
        r21 = r133;
    L113:
        e = e;
        r32 = r6;
        r21 = r133;
    L115:
        e = e;
        r30 = r5;
        r32 = r6;
    L53:
        if (r28 == 0) goto L55;
        r02 = r28;
    L55:
        r127 = r02;
    L52:
        r28 = r126;
        goto L53
    L46:
        r274 = r125;
        r126 = 0;
    L41:
        if (r273 == 0) goto L43;
        r125 = r273;
        goto L44
    L43:
        r125 = r5;
    L40:
        r273 = r124;
        goto L41
    L34:
        r124 = 0;
    L30:
        if (r272 == 0) goto L32;
        r4 = r272;
    L29:
        r272 = r123;
        goto L30
    L23:
        r123 = 0;
    L19:
        if (r27 == 0) goto L21;
        r21 = r27;
    L18:
        r27 = r122;
        goto L19
    L11:
        r23 = r12;
        r24 = r13;
        r122 = 0;
    L6:
        th = move-exception;
        FileLog.e(th);
        return null;
    }

    public static boolean deleteTheme(ThemeInfo r4) {
        boolean r1 = false;
        if (r4.pathToFile != null) goto L6;
        return false;
    L6:
        if (currentTheme != r4) goto L9;
        applyTheme(defaultTheme, true, false, false);
        r1 = true;
    L9:
        if (r4 != currentNightTheme) goto L11;
        currentNightTheme = themesDict.get("Dark Blue");
    L11:
        ThemeInfo.m5837$$Nest$mremoveObservers(r4);
        otherThemes.remove(r4);
        themesDict.remove(r4.name);
        OverrideWallpaperInfo r0 = r4.overrideWallpaper;
        if (r0 == null) goto L14;
        OverrideWallpaperInfo.m5823$$Nest$mdelete(r0);
    L14:
        themes.remove(r4);
        new File(r4.pathToFile).delete();
        saveOtherThemes(true);
        return r1;
    }

    public static boolean deleteThemeAccent(ThemeInfo r7, ThemeAccent r8, boolean r9) {
        boolean r0 = false;
        if (r8 == null) goto L33;
        if (r7 == null) goto L33;
        if (r7.themeAccents == null) goto L33;
        if (r8.id != r7.currentAccentId) goto L11;
        boolean r1 = true;
    L12:
        File r2 = r8.getPathToWallpaper();
        if (r2 == null) goto L15;
        r2.delete();
    L15:
        r7.themeAccentsMap.remove(r8.id);
        r7.themeAccents.remove(r8);
        TLRPC.TL_theme r22 = r8.info;
        if (r22 == null) goto L18;
        r7.accentsByThemeId.remove(r22.id);
    L18:
        OverrideWallpaperInfo r23 = r8.overrideWallpaper;
        if (r23 == null) goto L21;
        OverrideWallpaperInfo.m5823$$Nest$mdelete(r23);
    L21:
        if (r1 == false) goto L23;
        r7.setCurrentAccentId(r7.themeAccents.get(0).id);
    L23:
        if (r9 == false) goto L32;
        saveThemeAccents(r7, true, false, false, false);
        if (r8.info == null) goto L32;
        MessagesController r92 = MessagesController.getInstance(r8.account);
        if (r1 == true) goto L29;
    L31:
        r92.saveTheme(r7, r8, r0, true);
        goto L32
    L29:
        if (r7 != currentNightTheme) goto L31;
        r0 = true;
    L32:
        return r1;
    L11:
        r1 = false;
    L33:
        return false;
    }

    public static void destroyResources() {
    }

    public static ThemeInfo fillThemeValues(File r7, String r8, TLRPC.TL_theme r9) {
        ThemeInfo r1 = new ThemeInfo();     // Catch: Exception -> L52
        r1.name = r8;     // Catch: Exception -> L52
        r1.info = r9;     // Catch: Exception -> L52
        r1.pathToFile = r7.getAbsolutePath();     // Catch: Exception -> L52
        r1.account = UserConfig.selectedAccount;     // Catch: Exception -> L52
        String[] r82 = new String[1];     // Catch: Exception -> L52
        checkIsDark(getThemeFileValues(new File(r1.pathToFile), null, r82), r1);     // Catch: Exception -> L52
        if (TextUtils.isEmpty(r82[0]) == true) goto L54;
        String r83 = r82[0];     // Catch: Exception -> L52
        r1.pathToWallpaper = new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(r83) + ".wp").getAbsolutePath();     // Catch: Exception -> L52
        Uri r84 = Uri.parse(r83);     // Catch: Throwable -> L15
        r1.slug = r84.getQueryParameter("slug");     // Catch: Throwable -> L15
        String r2 = r84.getQueryParameter("mode");     // Catch: Throwable -> L15
        if (r2 == null) goto L21;
        String[] r22 = r2.toLowerCase().split(" ");     // Catch: Throwable -> L15
        if (r22.length <= 0) goto L21;
        int r3 = r22.length;     // Catch: Throwable -> L15
        int r4 = 0;
    L11:
        if (r4 >= r3) goto L21;
        String r5 = r22[r4];     // Catch: Throwable -> L15
        if ("blur".equals(r5) == false) goto L18;
        r1.isBlured = true;     // Catch: Throwable -> L15
    L20:
        r4 = r4 + 1;     // Catch: Throwable -> L15
        goto L11
    L18:
        if ("motion".equals(r5) == false) goto L20;
        r1.isMotion = true;     // Catch: Throwable -> L15
    L21:
        String r72 = r84.getQueryParameter("intensity");     // Catch: Throwable -> L15
        if (TextUtils.isEmpty(r72) == false) goto L62;
    L51:
        return r1;
    L62:
        String r23 = r84.getQueryParameter("bg_color");     // Catch: Throwable -> L15 Exception -> L58
        if (TextUtils.isEmpty(r23) == true) goto L60;
        r1.patternBgColor = Integer.parseInt(r23.substring(0, 6), 16) | (-16777216);     // Catch: Throwable -> L15 Exception -> L58
        if (r23.length() < 13) goto L31;
        if (AndroidUtilities.isValidWallChar(r23.charAt(6)) == false) goto L31;
        r1.patternBgGradientColor1 = Integer.parseInt(r23.substring(7, 13), 16) | (-16777216);     // Catch: Throwable -> L15 Exception -> L58
    L31:
        if (r23.length() < 20) goto L36;
        if (AndroidUtilities.isValidWallChar(r23.charAt(13)) == false) goto L36;
        r1.patternBgGradientColor2 = Integer.parseInt(r23.substring(14, 20), 16) | (-16777216);     // Catch: Throwable -> L15 Exception -> L58
    L36:
        if (r23.length() != 27) goto L60;
        if (AndroidUtilities.isValidWallChar(r23.charAt(20)) == false) goto L60;
        r1.patternBgGradientColor3 = Integer.parseInt(r23.substring(21), 16) | (-16777216);     // Catch: Throwable -> L15 Exception -> L58
    L60:
        String r85 = r84.getQueryParameter("rotation");     // Catch: Throwable -> L15 Exception -> L59
        if (TextUtils.isEmpty(r85) == true) goto L44;
        r1.patternBgGradientRotation = Utilities.parseInt(r85);     // Catch: Throwable -> L15 Exception -> L59
    L44:
        if (TextUtils.isEmpty(r72) == true) goto L47;
        r1.patternIntensity = Utilities.parseInt(r72);     // Catch: Throwable -> L15
    L47:
        if (r1.patternIntensity != 0) goto L51;
        r1.patternIntensity = 50;     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L52
        goto L51
    L54:
        themedWallpaperLink = null;     // Catch: Exception -> L52
        return r1;
    L52:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Paint fillingPaint(int r2) {
        Paint r0 = PAINT_FILLING;
        if (r0.getColor() == r2) goto L5;
        r0.setColor(r2);
    L5:
        return r0;
    }

    private static int getAccentColor(float[] r7, int r8, int r9) {
        float[] r0 = getTempHsv(3);
        float[] r1 = getTempHsv(4);
        Color.colorToHSV(r8, r0);
        Color.colorToHSV(r9, r1);
        float r2 = Math.min((r0[1] * 1.5f) / r7[1], 1.0f);
        r0[0] = (r1[0] - r0[0]) + r7[0];
        r0[1] = (r1[1] * r7[1]) / r0[1];
        float r12 = ((((r1[2] / r0[2]) + r2) - 1.0f) * r7[2]) / r2;
        r0[2] = r12;
        if (r12 >= 0.3f) goto L6;
        return r9;
    L6:
        return Color.HSVToColor(255, r0);
    }

    public static ThemeInfo getActiveTheme() {
        return currentTheme;
    }

    public static ColorFilter getAnimatedEmojiColorFilter(ResourcesProvider r0) {
        if (r0 == null) goto L6;
        return r0.getAnimatedEmojiColorFilter();
    L6:
        return chat_animatedEmojiTextColorFilter;
    }

    public static AudioVisualizerDrawable getAnimatedOutAudioVisualizerDrawable(MessageObject r1) {
        HashMap<MessageObject, AudioVisualizerDrawable> r0 = animatedOutVisualizerDrawables;
        if (r0 == null) goto L8;
        if (r1 != null) goto L7;
        return null;
    L7:
        return r0.get(r1);
    L8:
        return null;
    }

    public static File getAssetFile(String r6) {
        File r0 = new File(ApplicationLoader.getFilesDirFixed(), r6);
        InputStream r3 = ApplicationLoader.applicationContext.getAssets().open(r6);     // Catch: Exception -> L5
        long r4 = r3.available();     // Catch: Exception -> L5
        r3.close();     // Catch: Exception -> L5
    L8:
        if (r0.exists() == true) goto L10;
    L33:
        InputStream r62 = ApplicationLoader.applicationContext.getAssets().open(r6);     // Catch: Exception -> L18
        AndroidUtilities.copyFile(r62, r0);     // Catch: Throwable -> L20
        if (r62 == null) goto L28;
        r62.close();     // Catch: Exception -> L18
    L28:
        return r0;
    L20:
        th = move-exception;
        if (r62 != null) goto L29;
    L26:
        throw th;     // Catch: Exception -> L18
    L29:
        r62.close();     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L18
    L18:
        e = move-exception;
        FileLog.e(e);
        goto L28
    L10:
        if (r4 == 0) goto L28;
        if (r0.length() == r4) goto L28;
    L5:
        e = move-exception;
        FileLog.e(e);
        r4 = 0;
        goto L8
    }

    private static long getAutoNightSwitchThemeDelay() {
        long r0 = SystemClock.elapsedRealtime();
        if (Math.abs(lastThemeSwitchTime - r0) < 12000) goto L6;
        return 1800;
    L6:
        return 12000;
    }

    public static TLRPC.BaseTheme getBaseThemeByKey(String r1) {
        if ("Blue".equals(r1) == false) goto L7;
        return new TLRPC.TL_baseThemeClassic();
    L7:
        if ("Day".equals(r1) == false) goto L11;
        return new TLRPC.TL_baseThemeDay();
    L11:
        if ("Dark Blue".equals(r1) == false) goto L15;
        return new TLRPC.TL_baseThemeTinted();
    L15:
        if ("Arctic Blue".equals(r1) == false) goto L19;
        return new TLRPC.TL_baseThemeArctic();
    L19:
        if ("Night".equals(r1) == true) goto L21;
        return null;
    L21:
        return new TLRPC.TL_baseThemeNight();
    }

    public static String getBaseThemeKey(TLRPC.ThemeSettings r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        TLRPC.BaseTheme r22 = r2.base_theme;
        if ((r22 instanceof TLRPC.TL_baseThemeClassic) == false) goto L10;
        return "Blue";
    L10:
        if ((r22 instanceof TLRPC.TL_baseThemeDay) == false) goto L14;
        return "Day";
    L14:
        if ((r22 instanceof TLRPC.TL_baseThemeTinted) == false) goto L18;
        return "Dark Blue";
    L18:
        if ((r22 instanceof TLRPC.TL_baseThemeArctic) == false) goto L22;
        return "Arctic Blue";
    L22:
        if ((r22 instanceof TLRPC.TL_baseThemeNight) == false) goto L25;
        return "Night";
    L25:
        return null;
    }

    public static Drawable getCachedWallpaper() {
        Drawable r0 = getCachedWallpaperNonBlocking();
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (wallpaperLoadTask == null) goto L16;
        CountDownLatch r02 = new CountDownLatch(1);
        Utilities.themeQueue.postRunnable(new Theme$$ExternalSyntheticLambda9(r02));
        r02.await();     // Catch: Exception -> L9
    L12:
        return getCachedWallpaperNonBlocking();
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L12
    L16:
        return r0;
    }

    public static Drawable getCachedWallpaperNonBlocking() {
        Drawable r0 = themedWallpaper;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return wallpaper;
    }

    public static StatusDrawable getChatStatusDrawable(int r5) {
        if (r5 >= 0) goto L5;
    L29:
        return null;
    L5:
        if (r5 > 5) goto L29;
        StatusDrawable[] r2 = chat_status_drawables;
        StatusDrawable r3 = r2[r5];
        if (r3 == null) goto L11;
        return r3;
    L11:
        if (r5 == 0) goto L26;
        if (r5 != 1) goto L14;
        r2[1] = new RecordStatusDrawable(true);
    L27:
        StatusDrawable r52 = chat_status_drawables[r5];
        r52.start();
        r52.setColor(getColor(key_chats_actionMessage));
        return r52;
    L14:
        if (r5 != 2) goto L16;
        r2[2] = new SendingFileDrawable(true);
        goto L27
    L16:
        if (r5 != 3) goto L18;
        r2[3] = new PlayingGameDrawable(true, null);
        goto L27
    L18:
        if (r5 == 4) goto L22;
        if (r5 != 5) goto L27;
        r2[5] = new ChoosingStickerStatusDrawable(true);
        goto L27
    L22:
        r2[4] = new RoundStatusDrawable(true);
        goto L27
    L26:
        r2[0] = new TypingDotsDrawable(true);
        goto L27
    }

    public static int getColor(int r2) {
        return getColor(r2, null, false);
    }

    public static int getColor(int r2, ResourcesProvider r3) {
        if (r2 == key_divider) goto L5;
    L10:
        if (r3 == null) goto L14;
        return r3.getColor(r2);
    L14:
        return getColor(r2);
    L5:
        if (resolvingDividerColor == true) goto L10;
        if (ExteraConfig.getDividerStyle() == DividerStyle.LINE) goto L10;
        return 16777215;
    }

    public static int getColor(int r1, boolean[] r2) {
        return getColor(r1, r2, false);
    }

    public static int getColor(int r2, boolean[] r3, boolean r4) {
        if (r2 != key_divider) goto L11;
        if (resolvingDividerColor == true) goto L11;
        if (ExteraConfig.getDividerStyle() == DividerStyle.LINE) goto L11;
        return 16777215;
    L11:
        return getRawColor(r2, r3, r4);
    }

    public static AudioVisualizerDrawable getCurrentAudiVisualizerDrawable() {
        if (chat_msgAudioVisualizeDrawable != null) goto L6;
        chat_msgAudioVisualizeDrawable = new AudioVisualizerDrawable();
    L6:
        return chat_msgAudioVisualizeDrawable;
    }

    public static int getCurrentColor(int r1) {
        return currentColors.get(r1);
    }

    public static BackgroundGradientDrawable getCurrentGradientWallpaper() {
        OverrideWallpaperInfo r0 = currentTheme.overrideWallpaper;
        if (r0 == null) goto L10;
        int r1 = r0.color;
        if (r1 == 0) goto L12;
        int r2 = r0.gradientColor1;
        if (r2 == 0) goto L13;
        GradientDrawable.Orientation r02 = BackgroundGradientDrawable.getGradientOrientation(r0.rotation);
        return new BackgroundGradientDrawable(r02, new int[]{r1, r2});
    L13:
        return null;
    L12:
        return null;
    L10:
        return null;
    }

    public static Drawable getCurrentHolidayDrawable() {
        return null;
    }

    public static int getCurrentHolidayDrawableXOffset() {
        return dialogs_holidayDrawableOffsetX;
    }

    public static int getCurrentHolidayDrawableYOffset() {
        return dialogs_holidayDrawableOffsetY;
    }

    public static ThemeInfo getCurrentNightTheme() {
        return currentNightTheme;
    }

    public static String getCurrentNightThemeName() {
        ThemeInfo r0 = currentNightTheme;
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r02 = r0.getName();
        if (r02.toLowerCase().endsWith(".attheme") == true) goto L9;
        return r02;
    L9:
        return r02.substring(0, r02.lastIndexOf(46));
    }

    public static ThemeInfo getCurrentTheme() {
        ThemeInfo r0 = currentDayTheme;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return defaultTheme;
    }

    public static int getDefaultAccentColor(int r4) {
        int r42 = currentColorsNoAccent.indexOfKey(r4);
        if (r42 < 0) goto L9;
        int r43 = currentColorsNoAccent.valueAt(r42);
        ThemeAccent r1 = currentTheme.getAccent(false);
        if (r1 != null) goto L7;
        return 0;
    L7:
        float[] r0 = getTempHsv(1);
        float[] r2 = getTempHsv(2);
        Color.colorToHSV(currentTheme.accentBaseColor, r0);
        Color.colorToHSV(r1.accentColor, r2);
        return changeColorAccent(r0, r2, r43, currentTheme.isDark(), r43);
    L9:
        return 0;
    }

    public static int getDefaultColor(int r2) {
        int r0 = defaultColors[r2];
        if (r0 != 0) goto L25;
        int r02 = fallbackKeys.get(r2, -1);
        if (r02 == (-1)) goto L9;
        return getDefaultColor(r02);
    L9:
        if (isMyMessagesBubbles(r2) == false) goto L11;
        return 0;
    L11:
        if (r2 != key_chats_menuTopShadow) goto L13;
        return 0;
    L13:
        if (r2 != key_chats_menuTopBackground) goto L15;
        return 0;
    L15:
        if (r2 != key_chats_menuTopShadowCats) goto L17;
        return 0;
    L17:
        if (r2 != key_chat_wallpaper_gradient_to2) goto L19;
        return 0;
    L19:
        if (r2 == key_chat_wallpaper_gradient_to3) goto L30;
        return org.mvel2.asm.Opcodes.V_PREVIEW;
    L30:
        return 0;
    L25:
        return r0;
    }

    public static int[] getDefaultColors() {
        return defaultColors;
    }

    public static int getDividerColor(ResourcesProvider r4) {
        DividerStyle r0 = ExteraConfig.getDividerStyle();
        if (r0 != DividerStyle.HIDDEN) goto L6;
        return 16777215;
    L6:
        boolean r1 = true;
        resolvingDividerColor = true;
        if (r4 == null) goto L13;
        int r42 = r4.getColor(key_divider);     // Catch: Throwable -> L11
        resolvingDividerColor = false;
        return r42;
    L13:
        int r43 = key_divider;     // Catch: Throwable -> L11
        if (r0 != DividerStyle.LINE) goto L17;
        r1 = false;
    L17:
        int r44 = getRawColor(r43, null, r1);     // Catch: Throwable -> L11
        resolvingDividerColor = false;
        return r44;
    L11:
        th = move-exception;
        resolvingDividerColor = false;
        throw th;
    }

    public static int getEventType() {
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(System.currentTimeMillis());
        int r2 = r0.get(2);
        int r3 = r0.get(5);
        int r02 = r0.get(11);
        if (ExteraConfig.getEventType() != 0) goto L24;
        if (r2 == 11) goto L6;
    L7:
        if (r2 != 0) goto L10;
        if (r3 != 1) goto L10;
    L9:
        return 0;
    L10:
        if (r2 != 1) goto L15;
        if (r3 != 14) goto L15;
        return 1;
    L15:
        if (r2 != 9) goto L19;
        if (r3 < 30) goto L19;
    L23:
        return 2;
    L19:
        if (r2 != 10) goto L30;
        if (r3 == 1) goto L22;
        return -1;
    L22:
        if (r02 < 12) goto L23;
        return -1;
    L30:
        return -1;
    L6:
        if (r3 >= 24) goto L9;
    L24:
        int r03 = ExteraConfig.getEventType();
        if (r03 != 2) goto L27;
        return 0;
    L27:
        if (r03 != 3) goto L29;
        return 1;
    L29:
        if (r03 != 4) goto L37;
        return 2;
    L37:
        return -1;
    }

    public static int getFallbackKey(int r1) {
        return fallbackKeys.get(r1);
    }

    public static SparseIntArray getFallbackKeys() {
        return fallbackKeys;
    }

    public static FragmentContextViewWavesDrawable getFragmentContextViewWavesDrawable() {
        if (fragmentContextViewWavesDrawable != null) goto L6;
        fragmentContextViewWavesDrawable = new FragmentContextViewWavesDrawable();
    L6:
        return fragmentContextViewWavesDrawable;
    }

    public static int getNonAnimatedColor(int r2) {
        return getColor(r2, null, true);
    }

    public static int getNonAnimatedColor(int r2, ResourcesProvider r3) {
        if (r2 == key_divider) goto L5;
    L10:
        if (r3 == null) goto L14;
        return r3.getColor(r2);
    L14:
        return getColor(r2, null, true);
    L5:
        if (resolvingDividerColor == true) goto L10;
        if (ExteraConfig.getDividerStyle() == DividerStyle.LINE) goto L10;
        return 16777215;
    }

    public static int getPreviewColor(SparseIntArray r1, int r2) {
        int r0 = r1.indexOfKey(r2);
        if (r0 < 0) goto L7;
        return r1.valueAt(r0);
    L7:
        return defaultColors[r2];
    }

    public static ThemeInfo getPreviousTheme() {
        return previousTheme;
    }

    public static RoundVideoProgressShadow getRadialSeekbarShadowDrawable() {
        if (roundPlayDrawable != null) goto L6;
        roundPlayDrawable = new RoundVideoProgressShadow();
    L6:
        return roundPlayDrawable;
    }

    private static int getRawColor(int r3, boolean[] r4, boolean r5) {
        if (r5 == true) goto L10;
        SparseIntArray r52 = animatingColors;
        if (r52 == null) goto L10;
        int r53 = r52.indexOfKey(r3);
        if (r53 < 0) goto L10;
        return animatingColors.valueAt(r53);
    L10:
        if (serviceBitmapShader == null) goto L25;
        if (key_chat_serviceText != r3) goto L14;
    L23:
        return -1;
    L14:
        if (key_chat_serviceLink == r3) goto L23;
        if (key_chat_serviceIcon == r3) goto L23;
        if (key_chat_stickerReplyLine == r3) goto L23;
        if (key_chat_stickerReplyNameText == r3) goto L23;
        if (key_chat_stickerReplyMessageText == r3) goto L23;
    L25:
        if (currentTheme == defaultTheme) goto L27;
    L54:
        int r54 = currentColors.indexOfKey(r3);
        if (r54 >= 0) goto L74;
        int r55 = fallbackKeys.get(r3, -1);
        if (r55 == (-1)) goto L62;
        int r56 = currentColors.indexOfKey(r55);
        if (r56 < 0) goto L62;
        return currentColors.valueAt(r56);
    L62:
        if (r4 == null) goto L65;
        r4[0] = true;
    L65:
        if (r3 != key_chat_serviceBackground) goto L69;
        return serviceMessageColor;
    L69:
        if (r3 != key_chat_serviceBackgroundSelected) goto L73;
        return serviceSelectedMessageColor;
    L73:
        return getDefaultColor(r3);
    L74:
        int r42 = currentColors.valueAt(r54);
        if (key_windowBackgroundWhite == r3) goto L85;
        if (key_windowBackgroundGray == r3) goto L85;
        if (key_actionBarDefault == r3) goto L85;
        if (key_actionBarDefaultArchived == r3) goto L85;
        return r42;
    L85:
        return (-16777216) | r42;
    L27:
        if (isMyMessagesBubbles(r3) == false) goto L30;
        boolean r57 = ThemeInfo.m5834$$Nest$misDefaultMyMessagesBubbles(currentTheme);
    L43:
        if (r57 == false) goto L54;
        if (r3 != key_chat_serviceBackground) goto L49;
        return serviceMessageColor;
    L49:
        if (r3 != key_chat_serviceBackgroundSelected) goto L53;
        return serviceSelectedMessageColor;
    L53:
        return getDefaultColor(r3);
    L30:
        if (isMyMessages(r3) == false) goto L33;
        r57 = ThemeInfo.m5833$$Nest$misDefaultMyMessages(currentTheme);
        goto L43
    L33:
        if (key_chat_wallpaper != r3) goto L35;
    L42:
        r57 = false;
        goto L43
    L35:
        if (key_chat_wallpaper_gradient_to1 == r3) goto L42;
        if (key_chat_wallpaper_gradient_to2 == r3) goto L42;
        if (key_chat_wallpaper_gradient_to3 == r3) goto L42;
        r57 = ThemeInfo.m5832$$Nest$misDefaultMainAccent(currentTheme);
        goto L43
    }

    public static Drawable getRoundRectSelectorDrawable(int r1) {
        return getRoundRectSelectorDrawable(AndroidUtilities.dp(3.0f), r1);
    }

    public static Drawable getRoundRectSelectorDrawable(int r3, int r4) {
        ShapeDrawable r32 = createRoundRectDrawable(r3, -1);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{(r4 & 16777215) | 419430400}), null, r32);
    }

    public static String getSelectedBackgroundSlug() {
        OverrideWallpaperInfo r0 = currentTheme.overrideWallpaper;
        if (r0 == null) goto L7;
        return r0.slug;
    L7:
        if (hasWallpaperFromTheme() == false) goto L10;
        return "t";
    L10:
        return "d";
    }

    public static Drawable getSelectorDrawable(int r1, int r2) {
        return getSelectorDrawable(r1, r2, null);
    }

    public static Drawable getSelectorDrawable(int r3, int r4, ResourcesProvider r5) {
        if (r4 < 0) goto L6;
        ColorDrawable r0 = new ColorDrawable(-1);
        return new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r3}), new ColorDrawable(getColor(r4, r5)), r0);
    L6:
        return createSelectorDrawable(r3, 2);
    }

    public static Drawable getSelectorDrawable(int r0, boolean r1) {
        if (r1 == false) goto L6;
        return getSelectorDrawable(r0, key_windowBackgroundWhite);
    L6:
        return createSelectorDrawable(r0, 2);
    }

    public static Drawable getSelectorDrawable(boolean r1) {
        return getSelectorDrawable(getColor(key_listSelector), r1);
    }

    public static Drawable getSelectorDrawable(boolean r1, ResourcesProvider r2) {
        int r0 = getColor(key_listSelector, r2);
        if (r1 == false) goto L7;
        return getSelectorDrawable(r0, key_windowBackgroundWhite, r2);
    L7:
        return createSelectorDrawable(r0, 2);
    }

    public static int getServiceMessageColor() {
        int r0 = currentColors.indexOfKey(key_chat_serviceBackground);
        if (r0 < 0) goto L7;
        return currentColors.valueAt(r0);
    L7:
        return serviceMessageColor;
    }

    @SuppressLint({"PrivateApi"})
    private static Drawable getStateDrawable(Drawable r6, int r7) {
        if (Build.VERSION.SDK_INT < 29) goto L9;
        if ((r6 instanceof StateListDrawable) == false) goto L9;
        return ((StateListDrawable) r6).getStateDrawable(r7);
    L9:
        if (StateListDrawable_getStateDrawableMethod == null) goto L20;
    L11:
        Method r0 = StateListDrawable_getStateDrawableMethod;
        if (r0 != null) goto L18;
        return null;
    L18:
        return (Drawable) r0.invoke(r6, new Object[]{Integer.valueOf(r7)});
    L16:
        return null;
    L20:
        StateListDrawable_getStateDrawableMethod = StateListDrawable.class.getDeclaredMethod("getStateDrawable", new Class[]{Integer.TYPE});     // Catch: Throwable -> L17
        goto L11
    }

    public static float[] getTempHsv(int r2) {
        if (r2 != 1) goto L5;
        ThreadLocal<float[]> r22 = hsvTemp1Local;
    L14:
        float[] r0 = r22.get();
        if (r0 != null) goto L18;
        float[] r02 = new float[3];
        r22.set(r02);
        return r02;
    L18:
        return r0;
    L5:
        if (r2 == 2) goto L12;
        if (r2 != 3) goto L8;
        r22 = hsvTemp3Local;
        goto L14
    L8:
        if (r2 == 4) goto L10;
        r22 = hsvTemp5Local;
        goto L14
    L10:
        r22 = hsvTemp4Local;
        goto L14
    L12:
        r22 = hsvTemp2Local;
        goto L14
    }

    public static ThemeInfo getTheme(String r1) {
        return themesDict.get(r1);
    }

    public static Drawable getThemeDrawable(String r1) {
        return defaultChatDrawables.get(r1);
    }

    public static Drawable getThemeDrawable(String r0, ResourcesProvider r1) {
        if (r1 == null) goto L4;
        Drawable r12 = r1.getDrawable(r0);
    L5:
        if (r12 == null) goto L8;
        return r12;
    L8:
        return defaultChatDrawables.get(r0);
    L4:
        r12 = null;
        goto L5
    }

    public static int getThemeDrawableColorKey(String r1) {
        return defaultChatDrawableColorKeys.get(r1).intValue();
    }

    public static Map<String, Drawable> getThemeDrawablesMap() {
        return defaultChatDrawables;
    }

    public static SparseIntArray getThemeFileValues(File r18, String r19, String[] r20) {
        SparseIntArray r1 = new SparseIntArray();
        FileInputStream r3 = null;
        byte[] r2 = new byte[1024];     // Catch: Throwable -> L6
        if (r19 == null) goto L8;
        File r4 = getAssetFile(r19);     // Catch: Throwable -> L6
    L9:
        FileInputStream r5 = new FileInputStream(r4);     // Catch: Throwable -> L6
        int r32 = -1;
        int r42 = 0;
        int r7 = -1;
        int r6 = 0;
        boolean r8 = false;
    L83:
        int r9 = r5.read(r2);     // Catch: Throwable -> L23
        if (r9 == r32) goto L64;
        int r10 = r42;
        int r11 = r10;
        int r12 = r6;
    L14:
        if (r10 >= r9) goto L58;
        if (r2[r10] != 10) goto L57;
        int r13 = r10 - r11;     // Catch: Throwable -> L23
        int r14 = r13 + 1;     // Catch: Throwable -> L23
        String r15 = new String(r2, r11, r13);     // Catch: Throwable -> L23
        if (r15.startsWith("WLS=") == false) goto L26;
        if (r20 != null) goto L21;
    L56:
        r11 = r11 + r14;     // Catch: Throwable -> L23
        r12 = r12 + r14;     // Catch: Throwable -> L23
        goto L57
    L21:
        if (r20.length <= 0) goto L56;
        r20[r42] = r15.substring(4);     // Catch: Throwable -> L23
        goto L56
    L26:
        if (r15.startsWith("WPS") == true) goto L27;
        int r132 = r15.indexOf(61);     // Catch: Throwable -> L23
        if (r132 == r32) goto L56;
        String r17 = r15.substring(r42, r132);     // Catch: Throwable -> L23
        String r133 = r15.substring(r132 + 1);     // Catch: Throwable -> L23
        boolean r33 = r133.trim().endsWith("h");     // Catch: Throwable -> L23
        if (r33 == false) goto L35;
        r133 = r133.substring(r42, r133.length() - 1);     // Catch: Throwable -> L23
    L35:
        if (r133.startsWith("#") == false) goto L40;
        int r134 = Color.parseColor(r133);     // Catch: Throwable -> L23 Exception -> L38
    L50:
        if (Build.VERSION.SDK_INT < 31) goto L53;
        if (r33 == false) goto L53;
        r134 = MonetUtils.harmonize(r134);     // Catch: Throwable -> L23
    L53:
        int r34 = ThemeColors.stringKeyToInt(r17);     // Catch: Throwable -> L23
        if (r34 < 0) goto L56;
        r1.put(r34, r134);     // Catch: Throwable -> L23
    L38:
        r134 = Utilities.parseInt(r133);     // Catch: Throwable -> L23
        goto L50
    L40:
        if (Build.VERSION.SDK_INT >= 31) goto L42;
    L48:
        r134 = Utilities.parseInt(r133);     // Catch: Throwable -> L23
        goto L50
    L42:
        if (r133.startsWith("a") == false) goto L44;
    L47:
        r134 = MonetUtils.getColor(r133.trim());     // Catch: Throwable -> L23
        goto L50
    L44:
        if (r133.startsWith("n") == true) goto L47;
        if (r133.startsWith("m") == false) goto L48;
    L27:
        r7 = r12 + r14;     // Catch: Throwable -> L23
        r8 = true;
    L57:
        r10 = r10 + 1;     // Catch: Throwable -> L23
        r32 = -1;
        r42 = 0;
    L58:
        if (r6 == r12) goto L64;
        r5.getChannel().position(r12);     // Catch: Throwable -> L23
        if (r8 == true) goto L64;
        r6 = r12;
        r32 = -1;
        r42 = 0;
    L64:
        r1.put(key_wallpaperFileOffset, r7);     // Catch: Throwable -> L23
        r5.close();     // Catch: Exception -> L67
    L91:
    L72:
        return r1;
    L23:
        th = th;
        r3 = r5;
    L81:
        FileLog.e(th);     // Catch: Throwable -> L73
        if (r3 == null) goto L72;
        r3.close();     // Catch: Exception -> L67
    L73:
        th = move-exception;
        if (r3 == null) goto L101;
        r3.close();     // Catch: Exception -> L78
        throw th;
    L78:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L101:
        throw th;
    L8:
        r4 = r18;
    L6:
        th = th;
    L67:
        e = move-exception;
        FileLog.e(e);
        goto L72
    }

    public static void getThemeFileValuesInBackground(File r2, String r3, String[] r4, Utilities.Callback<SparseIntArray> r5) {
        Utilities.themeQueue.postRunnable(new Theme$$ExternalSyntheticLambda15(r5, r2, r3, r4));
    }

    public static float getThemeIntensity(float r1) {
        if (r1 < 0.0f) goto L5;
        return r1;
    L5:
        if (getActiveTheme().isDark() == false) goto L7;
        return r1;
    L7:
        return -r1;
    }

    public static Paint getThemePaint(String r1) {
        if (Objects.equals(r1, "paintDivider") == false) goto L7;
        return dividerPaint;
    L7:
        return defaultChatPaints.get(r1);
    }

    public static Paint getThemePaint(String r0, ResourcesProvider r1) {
        if (r1 == null) goto L7;
        Paint r12 = r1.getPaint(r0);
        if (r12 == null) goto L7;
        return r12;
    L7:
        return getThemePaint(r0);
    }

    public static int getThemePaintColorKey(String r1) {
        return defaultChatPaintColors.get(r1).intValue();
    }

    public static Map<String, Paint> getThemePaintsMap() {
        return defaultChatPaints;
    }

    public static Drawable getThemedDrawable(Context r1, int r2, int r3) {
        if (r1 != null) goto L5;
        return null;
    L5:
        Drawable r12 = r1.getResources().getDrawable(r2).mutate();
        r12.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
        return r12;
    }

    public static Drawable getThemedDrawableByKey(Context r0, int r1, int r2) {
        return getThemedDrawable(r0, r1, getColor(r2));
    }

    public static Drawable getThemedDrawableByKey(Context r0, int r1, int r2, ResourcesProvider r3) {
        return getThemedDrawable(r0, r1, getColor(r2, r3));
    }

    public static Drawable getThemedWallpaper(boolean r12, View r13) {
        int r2 = currentColors.get(key_chat_wallpaper);
        AnonymousClass15 r1 = null;
        if (r2 == 0) goto L54;
        int r3 = currentColors.get(key_chat_wallpaper_gradient_to1);
        int r4 = currentColors.get(key_chat_wallpaper_gradient_to2);
        int r5 = currentColors.get(key_chat_wallpaper_gradient_to3);
        int r6 = currentColors.get(key_chat_wallpaper_gradient_rotation, -1);
        if (r6 != (-1)) goto L7;
        r6 = 45;
    L7:
        ThemeAccent r7 = currentTheme.getAccent(false);
        if (r7 == null) goto L10;
        int r9 = (int) (r7.patternIntensity * 100.0f);
    L11:
        if (r3 == 0) goto L13;
        int r11 = r9;
        if (MonetAccentHelper.isFallbackPattern(r7) == true) goto L23;
    L26:
        if (r7 != null) goto L28;
    L36:
        File r92 = null;
    L37:
        if (r4 == 0) goto L41;
        MotionBackgroundDrawable r22 = new MotionBackgroundDrawable(r2, r3, r4, r5, true);
        if (r92 != null) goto L52;
        return r22;
    L52:
        int r132 = 0;
    L66:
        if (r92 != null) goto L127;
    L118:
        return null;
    L127:
        FileInputStream r32 = new FileInputStream(r92);     // Catch: Throwable -> L105
        r32.getChannel().position(r132);     // Catch: Throwable -> L76
        BitmapFactory.Options r133 = new BitmapFactory.Options();     // Catch: Throwable -> L76
        int r42 = 1;
        if (r12 == false) goto L78;
        r133.inJustDecodeBounds = true;     // Catch: Throwable -> L76
        float r122 = r133.outWidth;     // Catch: Throwable -> L76
        float r52 = r133.outHeight;     // Catch: Throwable -> L76
        int r62 = AndroidUtilities.dp(100.0f);     // Catch: Throwable -> L76
    L71:
        float r72 = r62;
        if (r122 > r72) goto L75;
        if (r52 <= r72) goto L78;
    L75:
        r42 = r42 * 2;     // Catch: Throwable -> L76
        r122 = r122 / 2.0f;     // Catch: Throwable -> L76
        r52 = r52 / 2.0f;     // Catch: Throwable -> L76
    L78:
        Bitmap.Config r123 = Bitmap.Config.ALPHA_8;     // Catch: Throwable -> L76
        r133.inPreferredConfig = r123;     // Catch: Throwable -> L76
        r133.inJustDecodeBounds = false;     // Catch: Throwable -> L76
        r133.inSampleSize = r42;     // Catch: Throwable -> L76
        Bitmap r134 = BitmapFactory.decodeStream(r32, null, r133);     // Catch: Throwable -> L76
        if (r22 == null) goto L94;
        ThemeAccent r43 = currentTheme.getAccent(false);     // Catch: Throwable -> L76
        if (r43 == null) goto L83;
        int r0 = (int) (r43.patternIntensity * 100.0f);     // Catch: Throwable -> L76
    L84:
        if (r134 != null) goto L86;
    L88:
        r22.setPatternBitmap(r0, r134);     // Catch: Throwable -> L76
        r22.setPatternColorFilter(r22.getPatternColor());     // Catch: Throwable -> L76
        r32.close();     // Catch: Exception -> L91
        return r22;
    L91:
        e = move-exception;
        FileLog.e(e);
        return r22;
    L86:
        if (r134.getConfig() == r123) goto L88;
        Bitmap r124 = r134.copy(r123, false);     // Catch: Throwable -> L76
        r134.recycle();     // Catch: Throwable -> L76
        r134 = r124;
        goto L88
    L83:
        r0 = 100;
        goto L84
    L94:
        if (r134 == null) goto L130;
        BitmapDrawable r125 = new BitmapDrawable(r134);     // Catch: Throwable -> L76
        r32.close();     // Catch: Exception -> L98
        return r125;
    L98:
        e = move-exception;
        FileLog.e(e);
        return r125;
    L130:
        r32.close();     // Catch: Exception -> L103
    L103:
        e = move-exception;
        FileLog.e(e);
    L76:
        th = move-exception;
        Throwable r126 = th;
    L119:
        FileLog.e(r126);     // Catch: Throwable -> L110
        if (r32 == null) goto L118;
    L110:
        th = move-exception;
        if (r32 == null) goto L135;
        r32.close();     // Catch: Exception -> L115
        throw th;
    L115:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L135:
        throw th;
    L105:
        th = move-exception;
        r126 = th;
        r32 = null;
        goto L119
    L41:
        int r23 = r6;
        if (r92 != null) goto L51;
        BackgroundGradientDrawable r33 = new BackgroundGradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r23), new int[]{r2, r3});
        if (r12 == true) goto L46;
        BackgroundGradientDrawable.Sizes r02 = BackgroundGradientDrawable.Sizes.ofDeviceScreen();
    L47:
        if (r13 == null) goto L49;
        r1 = new AnonymousClass15(r12, r13);
    L49:
        r33.startDithering(r02, r1);
        return r33;
    L46:
        r02 = BackgroundGradientDrawable.Sizes.ofDeviceScreen(0.125f, BackgroundGradientDrawable.Sizes.Orientation.PORTRAIT);
        goto L47
    L51:
        r22 = null;
        goto L52
    L28:
        if (TextUtils.isEmpty(r7.patternSlug) == true) goto L36;
        if (previousTheme != null) goto L36;
        File r73 = r7.getPathToWallpaper();
        if (r73 == null) goto L36;
        if (r73.exists() == false) goto L36;
        r92 = r73;
        goto L37
    L23:
        if (previousTheme != null) goto L26;
        return MonetAccentHelper.createFallbackPatternDrawable(r2, r3, r4, r5, r6, r11, true, 0);
    L13:
        if (MonetAccentHelper.isFallbackPattern(r7) == false) goto L19;
        if (previousTheme != null) goto L19;
        return MonetAccentHelper.createFallbackPatternDrawable(r2, 0, 0, 0, r6, r9, true, 0);
    L19:
        return new ColorDrawable(r2);
    L10:
        r9 = 34;
        goto L11
    L54:
        if (themedWallpaperFileOffset <= 0) goto L65;
        ThemeInfo r135 = currentTheme;
        if (r135.pathToFile == null) goto L58;
    L59:
        String r136 = r135.assetName;
        if (r136 == null) goto L63;
        File r137 = getAssetFile(r136);
    L62:
        r92 = r137;
        r132 = themedWallpaperFileOffset;
        r22 = null;
        goto L66
    L63:
        r137 = new File(currentTheme.pathToFile);
        goto L62
    L58:
        if (r135.assetName != null) goto L59;
    L65:
        r22 = null;
        r92 = null;
        goto L52
    }

    public static int getWallpaperColor(int r1) {
        if (r1 != 0) goto L6;
        return 0;
    L6:
        return r1 | (-16777216);
    }

    private static String getWallpaperUrl(OverrideWallpaperInfo r9) {
        String r0 = null;
        if (r9 != null) goto L5;
    L45:
        return null;
    L5:
        if (TextUtils.isEmpty(r9.slug) == true) goto L45;
        if (r9.slug.equals("d") == true) goto L45;
        StringBuilder r1 = new StringBuilder();
        if (r9.isBlurred == false) goto L13;
        r1.append("blur");
    L13:
        if (r9.isMotion == true) goto L15;
    L18:
        int r2 = r9.color;
        if (r2 != 0) goto L21;
        String r92 = "https://attheme.org?slug=" + r9.slug;
    L42:
        if (r1.length() > 0) goto L44;
        return r92;
    L44:
        return r92 + "&mode=" + r1;
    L21:
        String r22 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r2 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r9.color >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r9.color & 255))}).toLowerCase();
        int r5 = r9.gradientColor1;
        if (r5 == 0) goto L24;
        String r52 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r5 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r9.gradientColor1 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r9.gradientColor1 & 255))}).toLowerCase();
    L25:
        int r6 = r9.gradientColor2;
        if (r6 == 0) goto L28;
        String r62 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r6 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r9.gradientColor2 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r9.gradientColor2 & 255))}).toLowerCase();
    L29:
        int r7 = r9.gradientColor3;
        if (r7 == 0) goto L32;
        r0 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r7 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (r9.gradientColor3 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (r9.gradientColor3 & 255))}).toLowerCase();
    L32:
        if (r52 == null) goto L38;
        if (r62 == null) goto L38;
        if (r0 == null) goto L37;
        r22 = r22 + "~" + r52 + "~" + r62 + "~" + r0;
    L40:
        r92 = "https://attheme.org?slug=" + r9.slug + "&intensity=" + ((int) (r9.intensity * 100.0f)) + "&bg_color=" + r22;
        goto L42
    L37:
        r22 = r22 + "~" + r52 + "~" + r62;
    L38:
        if (r52 == null) goto L40;
        r22 = (r22 + "-" + r52) + "&rotation=" + r9.rotation;
        goto L40
    L28:
        r62 = null;
        goto L29
    L24:
        r52 = null;
        goto L25
    L15:
        if (r1.length() <= 0) goto L17;
        r1.append("+");
    L17:
        r1.append("motion");
        goto L18
    }

    public static boolean hasCustomWallpaper() {
        if (isApplyingAccent == true) goto L5;
        return false;
    L5:
        if (currentTheme.overrideWallpaper == null) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean hasGradientService() {
        if (serviceBitmapShader == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean hasHue(int r2) {
        float[] r0 = getTempHsv(3);
        Color.colorToHSV(r2, r0);
        float r02 = r0[1];
        if (r02 > 0.1f) goto L5;
        return false;
    L5:
        if (r02 >= 0.9f) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean hasThemeKey(int r1) {
        if (currentColors.indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean hasWallpaperFromTheme() {
        ThemeInfo r0 = currentTheme;
        if (r0.firstAccentIsDefault == false) goto L8;
        if (r0.currentAccentId != DEFALT_THEME_ACCENT_ID) goto L8;
        return false;
    L8:
        if (currentColors.indexOfKey(key_chat_wallpaper) < 0) goto L10;
        return true;
    L10:
        if (themedWallpaperFileOffset <= 0) goto L12;
        return true;
    L12:
        if (TextUtils.isEmpty(themedWallpaperLink) == false) goto L18;
        return false;
    L18:
        return true;
    }

    public static boolean isAnimatingColor() {
        if (animatingColors == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isCurrentAccentMonet() {
        ThemeInfo r0 = currentTheme;
        if (r0 == null) goto L5;
        ThemeAccent r02 = r0.getAccent(false);
    L7:
        return MonetAccentHelper.isMonetAccent(r02);
    L5:
        r02 = null;
        goto L7
    }

    public static boolean isCurrentThemeDark() {
        return currentTheme.isDark();
    }

    public static boolean isCurrentThemeDay() {
        return !getActiveTheme().isDark();
    }

    public static boolean isCurrentThemeMonet() {
        return currentTheme.isMonet();
    }

    public static boolean isCurrentThemeMonet(ResourcesProvider r0) {
        if (r0 == null) goto L6;
        return r0.isMonet();
    L6:
        return isCurrentThemeMonet();
    }

    public static boolean isCurrentThemeNight() {
        if (currentTheme != currentNightTheme) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isCustomTheme() {
        return isCustomTheme;
    }

    public static boolean isCustomWallpaperColor() {
        if (hasCustomWallpaper() == true) goto L5;
        return false;
    L5:
        if (currentTheme.overrideWallpaper.color == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isHome(ThemeAccent r4) {
        ThemeInfo r0 = r4.parentTheme;
        if (r0 != null) goto L5;
    L21:
        return false;
    L5:
        if (r0.getKey().equals("Blue") == false) goto L10;
        if (r4.id != 99) goto L10;
        return true;
    L10:
        if (r4.parentTheme.getKey().equals("Day") == false) goto L15;
        if (r4.id != 9) goto L15;
        return true;
    L15:
        if (r4.parentTheme.getKey().equals("Night") == true) goto L19;
        if (r4.parentTheme.getKey().equals("Dark Blue") == false) goto L21;
    L19:
        if (r4.id != 0) goto L21;
        return true;
    }

    private static boolean isMyMessages(int r1) {
        if (r1 >= myMessagesStartIndex) goto L5;
        return false;
    L5:
        if (r1 >= myMessagesEndIndex) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isMyMessagesBubbles(int r1) {
        if (r1 >= myMessagesBubblesStartIndex) goto L5;
        return false;
    L5:
        if (r1 >= myMessagesBubblesEndIndex) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isPatternWallpaper() {
        String r0 = getSelectedBackgroundSlug();
        if (isPatternWallpaper == false) goto L5;
        return true;
    L5:
        if ("CJz3BZ6YGEYBAAAABboWp6SAv04".equals(r0) == false) goto L7;
        return true;
    L7:
        if ("qeZWES8rGVIEAAAARfWlK1lnfiI".equals(r0) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isThemeInstalled(ThemeInfo r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (themesDict.get(r1.getKey()) == null) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isThemeWallpaperPublic() {
        return !TextUtils.isEmpty(themedWallpaperLink);
    }

    public static boolean isWallpaperMotion() {
        return isWallpaperMotion;
    }

    public static void loadRemoteThemes(int r4, boolean r5) {
        if (loadingRemoteThemes[r4] == true) goto L18;
        if (r5 == true) goto L8;
        if (Math.abs((System.currentTimeMillis() / 1000) - lastLoadingThemesTime[r4]) >= 3600) goto L8;
        return;
    L8:
        if (UserConfig.getInstance(r4).isClientActivated() == false) goto L20;
        loadingRemoteThemes[r4] = true;
        TL_account.getThemes r52 = new TL_account.getThemes();
        r52.format = "android";
        if (MediaDataController.getInstance(r4).defaultEmojiThemes.isEmpty() == true) goto L14;
        r52.hash = remoteThemesHash[r4];
    L14:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        Log.i("theme", "loading remote themes, hash " + r52.hash);
    L16:
        ConnectionsManager.getInstance(r4).sendRequest(r52, new Theme$$ExternalSyntheticLambda4(r4));
        return;
    L20:
        return;
    }

    private static Bitmap loadScreenSizedBitmap(FileInputStream r11, int r12) {
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Throwable -> L8 Exception -> L10
        r1.inSampleSize = 1;     // Catch: Throwable -> L8 Exception -> L10
        r1.inJustDecodeBounds = true;     // Catch: Throwable -> L8 Exception -> L10
        long r4 = r12;
        r11.getChannel().position(r4);     // Catch: Throwable -> L8 Exception -> L10
        BitmapFactory.decodeStream(r11, null, r1);     // Catch: Throwable -> L8 Exception -> L10
        float r122 = r1.outWidth;     // Catch: Throwable -> L8 Exception -> L10
        float r3 = r1.outHeight;     // Catch: Throwable -> L8 Exception -> L10
        Point r6 = AndroidUtilities.displaySize;     // Catch: Throwable -> L8 Exception -> L10
        int r62 = Math.min(r6.x, r6.y);     // Catch: Throwable -> L8 Exception -> L10
        Point r7 = AndroidUtilities.displaySize;     // Catch: Throwable -> L8 Exception -> L10
        int r72 = Math.max(r7.x, r7.y);     // Catch: Throwable -> L8 Exception -> L10
        if (r62 >= r72) goto L6;
    L12:
        float r8 = Math.min(r122 / r62, r3 / r72);     // Catch: Throwable -> L8 Exception -> L10
    L14:
        if (r8 >= 1.2f) goto L16;
        r8 = 1.0f;
    L16:
        r1.inJustDecodeBounds = false;     // Catch: Throwable -> L8 Exception -> L10
        if (r8 > 1.0f) goto L19;
    L27:
        r1.inSampleSize = (int) r8;     // Catch: Throwable -> L8 Exception -> L10
    L28:
        r11.getChannel().position(r4);     // Catch: Throwable -> L8 Exception -> L10
        Bitmap r123 = BitmapFactory.decodeStream(r11, null, r1);     // Catch: Throwable -> L8 Exception -> L10
        if (r123.getWidth() >= r62) goto L31;
    L32:
        float r13 = Math.max(r62 / r123.getWidth(), r72 / r123.getHeight());     // Catch: Throwable -> L8 Exception -> L10
        if (r13 < 1.02f) goto L51;
        Bitmap r14 = Bitmap.createScaledBitmap(r123, (int) (r123.getWidth() * r13), (int) (r123.getHeight() * r13), true);     // Catch: Throwable -> L8 Exception -> L10
        r123.recycle();     // Catch: Throwable -> L8 Exception -> L10
        r11.close();     // Catch: Exception -> L47
    L36:
        return r14;
    L51:
        r11.close();     // Catch: Exception -> L46
    L38:
        return r123;
    L31:
        if (r123.getHeight() >= r72) goto L51;
    L19:
        if (r122 <= r62) goto L21;
    L22:
        int r124 = 1;
    L23:
        int r32 = r124 * 2;     // Catch: Throwable -> L8 Exception -> L10
        if ((r124 * 4) >= r8) goto L25;
        r124 = r32;
        goto L23
    L25:
        r1.inSampleSize = r32;     // Catch: Throwable -> L8 Exception -> L10
        goto L28
    L21:
        if (r3 <= r72) goto L27;
    L6:
        if (r122 <= r3) goto L12;
        r8 = Math.max(r122 / r62, r3 / r72);     // Catch: Throwable -> L8 Exception -> L10
    L8:
        th = move-exception;
        if (r11 != null) goto L53;
    L45:
        throw th;
    L53:
        r11.close();     // Catch: Exception -> L48
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        if (r11 != null) goto L58;
    L42:
        return null;
    L58:
        r11.close();     // Catch: Exception -> L49
        goto L42
    }

    public static void loadWallpaper(boolean r10) {
        if (wallpaper == null) goto L5;
        return;
    L5:
        ThemeInfo r0 = currentTheme;
        if (r0.firstAccentIsDefault == true) goto L8;
    L10:
        boolean r8 = false;
    L11:
        ThemeAccent r02 = r0.getAccent(false);
        if (r02 == null) goto L27;
        File r3 = r02.getPathToWallpaper();
        boolean r4 = r02.patternMotion;
        TLRPC.TL_theme r5 = r02.info;
        if (r5 != null) goto L16;
    L18:
        TLRPC.ThemeSettings r52 = null;
    L20:
        if (r02.info == null) goto L25;
        if (r52 == null) goto L25;
        TLRPC.WallPaper r53 = r52.wallpaper;
        if (r53 == null) goto L25;
        TLRPC.Document r54 = r53.document;
    L26:
        boolean r6 = r4;
        TLRPC.Document r7 = r54;
        File r42 = r3;
    L28:
        ThemeInfo r32 = currentTheme;
        File r55 = r42;
        OverrideWallpaperInfo r43 = r32.overrideWallpaper;
        if (r43 == null) goto L33;
        float r03 = r43.intensity;
    L31:
        float r04 = r03 * 100.0f;
    L32:
        int r05 = (int) r04;
        if (r10 == false) goto L39;
        DispatchQueue r102 = Utilities.themeQueue;
        boolean r9 = r8;
        Theme$$ExternalSyntheticLambda0 r33 = new Theme$$ExternalSyntheticLambda0(r43, r55, r05, r6, r7, r9);
        wallpaperLoadTask = r33;
        r102.postRunnable(r33);
        return;
    L39:
        Drawable r103 = loadWallpaperInternal(r43, r55, r05, r6, r7, r8);
        createCommonChatResources();
        if (disallowChangeServiceMessageColor == true) goto L42;
        applyChatServiceMessageColor(null, null, r103);
        applyChatMessageSelectedBackgroundColor(null, r103);
    L42:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
        return;
    L33:
        if (r02 == null) goto L35;
        r03 = r02.patternIntensity;
        goto L31
    L35:
        r04 = r32.patternIntensity;
    L25:
        r54 = null;
        goto L26
    L16:
        if (r5.settings.size() <= 0) goto L18;
        r52 = r02.info.settings.get(0);
        goto L20
    L27:
        r42 = null;
        r7 = null;
        r6 = false;
        goto L28
    L8:
        if (r0.currentAccentId != DEFALT_THEME_ACCENT_ID) goto L10;
        r8 = true;
        goto L11
    }

    private static Drawable loadWallpaperInternal(OverrideWallpaperInfo r14, File r15, int r16, boolean r17, TLRPC.Document r18, boolean r19) {
        BackgroundDrawableSettings r142 = createBackgroundDrawable(currentTheme, r14, currentColors, r15, themedWallpaperLink, themedWallpaperFileOffset, r16, previousPhase, r19, hasPreviousTheme, isApplyingAccent, r17, r18, false);
        Boolean r152 = r142.isWallpaperMotion;
        if (r152 == null) goto L5;
        boolean r153 = r152.booleanValue();
    L6:
        isWallpaperMotion = r153;
        Boolean r154 = r142.isPatternWallpaper;
        if (r154 == null) goto L9;
        boolean r155 = r154.booleanValue();
    L10:
        isPatternWallpaper = r155;
        Boolean r156 = r142.isCustomTheme;
        if (r156 == null) goto L13;
        boolean r157 = r156.booleanValue();
    L14:
        isCustomTheme = r157;
        patternIntensity = r16;
        Drawable r143 = r142.wallpaper;
        if (r143 == null) goto L17;
        Drawable r158 = r143;
    L18:
        wallpaper = r158;
        calcBackgroundColor(r143, 1);
        applyChatServiceMessageColor();
        return r143;
    L17:
        r158 = wallpaper;
        goto L18
    L13:
        r157 = isCustomTheme;
        goto L14
    L9:
        r155 = isPatternWallpaper;
        goto L10
    L5:
        r153 = isWallpaperMotion;
        goto L6
    }

    public static int multAlpha(int r2, float r3) {
        if (r3 != 1.0f) goto L6;
        return r2;
    L6:
        return ColorUtils.setAlphaComponent(r2, MathUtils.clamp((int) (Color.alpha(r2) * r3), 0, 255));
    }

    private static int needSwitchToTheme() {
        int r0 = selectedAutoNightType;
        if (r0 != 1) goto L27;
        Calendar r02 = Calendar.getInstance();
        r02.setTimeInMillis(System.currentTimeMillis());
        int r5 = (r02.get(11) * 60) + r02.get(12);
        if (autoNightScheduleByLocation == false) goto L14;
        int r03 = r02.get(5);
        if (autoNightLastSunCheckDay == r03) goto L13;
        double r6 = autoNightLocationLatitude;
        if (r6 == 10000.0d) goto L13;
        double r10 = autoNightLocationLongitude;
        if (r10 == 10000.0d) goto L13;
        int[] r2 = SunDate.calculateSunriseSunset(r6, r10);
        autoNightSunriseTime = r2[0];
        autoNightSunsetTime = r2[1];
        autoNightLastSunCheckDay = r03;
        saveAutoNightThemeConfig();
    L13:
        int r04 = autoNightSunsetTime;
        int r1 = autoNightSunriseTime;
    L15:
        if (r04 >= r1) goto L20;
        if (r04 > r5) goto L19;
        if (r5 > r1) goto L19;
        return 2;
    L19:
        return 1;
    L20:
        if (r04 <= r5) goto L22;
    L23:
        if (r5 < 0) goto L26;
        if (r5 > r1) goto L26;
    L25:
        return 2;
    L26:
        return 1;
    L22:
        if (r5 <= 1440) goto L25;
    L14:
        r04 = autoNightDayStartTime;
        r1 = autoNightDayEndTime;
        goto L15
    L27:
        if (r0 != 2) goto L49;
        if (lightSensor != null) goto L32;
        SensorManager r05 = (SensorManager) ApplicationLoader.applicationContext.getSystemService("sensor");
        sensorManager = r05;
        lightSensor = r05.getDefaultSensor(5);
    L32:
        if (lightSensorRegistered == true) goto L41;
        Sensor r06 = lightSensor;
        if (r06 == null) goto L41;
        SensorEventListener r22 = ambientSensorListener;
        if (r22 == null) goto L41;
        sensorManager.registerListener(r22, r06, 500000);
        lightSensorRegistered = true;
        if (BuildVars.LOGS_ENABLED == false) goto L41;
        FileLog.d("light sensor registered");
    L41:
        if (lastBrightnessValue > autoNightBrighnessThreshold) goto L46;
        if (switchNightRunnableScheduled == true) goto L61;
        return 2;
    L61:
        return 0;
    L46:
        if (switchDayRunnableScheduled == true) goto L61;
        return 1;
    L49:
        if (r0 != 3) goto L59;
        int r07 = ApplicationLoader.applicationContext.getResources().getConfiguration().uiMode & 48;
        if (r07 != 0) goto L53;
    L58:
        return 1;
    L53:
        if (r07 == 16) goto L58;
        if (r07 != 32) goto L61;
        return 2;
    L59:
        if (r0 != 0) goto L61;
        return 1;
    }

    public static void refreshMonetColors() {
        if (Build.VERSION.SDK_INT < 31) goto L21;
        ArrayList<ThemeInfo> r0 = themes;
        if (r0 == null) goto L22;
        int r02 = r0.size();
        int r2 = 0;
        boolean r3 = false;
    L8:
        if (r2 >= r02) goto L14;
        ThemeInfo r4 = themes.get(r2);
        if (r4 == null) goto L13;
        r3 = r3 | MonetAccentHelper.refresh(r4);
    L13:
        r2 = r2 + 1;
        goto L8
    L14:
        if (r3 == false) goto L16;
        PatternsLoader.createLoader(true);
    L16:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.themeListUpdated, new Object[0]);
        return;
    L22:
        return;
    }

    public static void refreshThemeColors() {
        refreshThemeColors(false, false);
    }

    public static void refreshThemeColors(boolean r5, boolean r6) {
        currentColors = currentColorsNoAccent.clone();
        shouldDrawGradientIcons = true;
        ThemeAccent r1 = currentTheme.getAccent(false);
        if (r1 == null) goto L5;
        shouldDrawGradientIcons = r1.fillAccentColors(currentColorsNoAccent, currentColors);
    L5:
        applyCalculatedTableColors(currentColorsNoAccent, currentColors, currentTheme.isDark());
        applyCalculatedArticleCodeColors(currentColorsNoAccent, currentColors, currentTheme.isDark());
        if (r6 == true) goto L8;
        reloadWallpaper(!(LaunchActivity.getLastFragment() instanceof ChatActivity));
    L8:
        applyCommonTheme();
        applyDialogsTheme();
        applyProfileTheme();
        applyChatTheme(false, r5);
        AndroidUtilities.runOnUIThread(new Theme$$ExternalSyntheticLambda2(!hasPreviousTheme));
    }

    public static void reloadAllResources(Context r2) {
        destroyResources();
        if (chat_msgInDrawable == null) goto L6;
        chat_msgInDrawable = null;
        currentColor = 0;
        createChatResources(r2, false);
    L6:
        if (dialogs_namePaint == null) goto L9;
        dialogs_namePaint = null;
        dividerPaint = null;
        createDialogsResources(r2);
    L9:
        if (profile_verifiedDrawable == null) goto L12;
        profile_verifiedDrawable = null;
        createProfileResources(r2);
        return;
    }

    public static void reloadWallpaper(boolean r3) {
        BackgroundGradientDrawable.Disposable r0 = backgroundGradientDisposable;
        if (r0 == null) goto L5;
        r0.dispose();
        backgroundGradientDisposable = null;
    L5:
        Drawable r02 = wallpaper;
        if ((r02 instanceof MotionBackgroundDrawable) == false) goto L8;
        previousPhase = ((MotionBackgroundDrawable) r02).getPhase();
    L9:
        wallpaper = null;
        themedWallpaper = null;
        loadWallpaper(r3);
        return;
    L8:
        previousPhase = 0;
        goto L9
    }

    public static void resetCustomWallpaper(boolean r1) {
        if (r1 == false) goto L5;
        isApplyingAccent = false;
        reloadWallpaper(true);
        return;
    L5:
        currentTheme.setOverrideWallpaper(null);
    }

    public static void saveAutoNightThemeConfig() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putInt("selectedAutoNightType", selectedAutoNightType);
        r0.putBoolean("autoNightScheduleByLocation", autoNightScheduleByLocation);
        r0.putFloat("autoNightBrighnessThreshold", autoNightBrighnessThreshold);
        r0.putInt("autoNightDayStartTime", autoNightDayStartTime);
        r0.putInt("autoNightDayEndTime", autoNightDayEndTime);
        r0.putInt("autoNightSunriseTime", autoNightSunriseTime);
        r0.putString("autoNightCityName", autoNightCityName);
        r0.putInt("autoNightSunsetTime", autoNightSunsetTime);
        r0.putLong("autoNightLocationLatitude3", Double.doubleToRawLongBits(autoNightLocationLatitude));
        r0.putLong("autoNightLocationLongitude3", Double.doubleToRawLongBits(autoNightLocationLongitude));
        r0.putInt("autoNightLastSunCheckDay", autoNightLastSunCheckDay);
        ThemeInfo r1 = currentNightTheme;
        if (r1 == null) goto L5;
        r0.putString("nighttheme", r1.getKey());
    L6:
        r0.apply();
        return;
    L5:
        r0.remove("nighttheme");
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:121:0x01fb -> B:139:0x020b). Please report as a decompilation issue!!! */
    public static void saveCurrentTheme(ThemeInfo r14, boolean r15, boolean r16, boolean r17) {
        OverrideWallpaperInfo r0 = r14.overrideWallpaper;
        if (r0 == null) goto L5;
        String r02 = getWallpaperUrl(r0);
    L6:
        if (r16 == false) goto L8;
        Drawable r1 = wallpaper;
    L9:
        if (r16 == false) goto L12;
        if (r1 == null) goto L12;
        themedWallpaper = wallpaper;
    L12:
        ThemeAccent r2 = currentTheme.getAccent(false);
        if (currentTheme.firstAccentIsDefault == true) goto L15;
    L17:
        Object[] r4 = false;
    L18:
        FileOutputStream r6 = null;
        if (r4 == false) goto L21;
        SparseIntArray r7 = null;
    L22:
        StringBuilder r8 = new StringBuilder();
        if (r4 == true) goto L45;
        if (r2 == null) goto L26;
        int r9 = r2.myMessagesAccentColor;
    L27:
        if (r2 == null) goto L29;
        int r10 = r2.myMessagesGradientAccentColor1;
    L30:
        if (r2 == null) goto L32;
        int r11 = r2.myMessagesGradientAccentColor2;
    L33:
        if (r2 == null) goto L35;
        int r12 = r2.myMessagesGradientAccentColor3;
    L36:
        if (r9 == 0) goto L45;
        if (r10 == 0) goto L45;
        r7.put(key_chat_outBubble, r9);
        r7.put(key_chat_outBubbleGradient1, r10);
        if (r11 == 0) goto L43;
        r7.put(key_chat_outBubbleGradient2, r11);
        if (r12 == 0) goto L43;
        r7.put(key_chat_outBubbleGradient3, r12);
    L43:
        r7.put(key_chat_outBubbleGradientAnimated, r2.myMessagesAnimated ? 1 : 0);
        goto L45
    L35:
        r12 = 0;
        goto L36
    L32:
        r11 = 0;
        goto L33
    L29:
        r10 = 0;
        goto L30
    L26:
        r9 = 0;
    L45:
        if (r4 == false) goto L63;
        int r72 = 0;
    L47:
        int[] r102 = defaultColors;
        if (r72 >= r102.length) goto L140;
        int r103 = r102[r72];
        if ((r1 instanceof BitmapDrawable) == true) goto L53;
        if (r02 != null) goto L53;
    L61:
        r8.append(ThemeColors.getStringName(r72));
        r8.append("=");
        r8.append(r103);
        r8.append("\n");
    L62:
        r72 = r72 + 1;
    L53:
        if (key_chat_wallpaper == r72) goto L62;
        if (key_chat_wallpaper_gradient_to1 == r72) goto L62;
        if (key_chat_wallpaper_gradient_to2 == r72) goto L62;
        if (key_chat_wallpaper_gradient_to3 != r72) goto L61;
    L140:
        FileOutputStream r22 = new FileOutputStream(r14.pathToFile);     // Catch: Throwable -> L122 Exception -> L124
    L90:
        e = e;
        r6 = r22;
    L125:
        FileLog.e(e);     // Catch: Throwable -> L122
        if (r6 == null) goto L128;
        r6.close();     // Catch: Exception -> L120
    L139:
    L128:
        if (r15 == false) goto L158;
        MessagesController.getInstance(r14.account).saveThemeToServer(r14, r14.getAccent(false));
        return;
    L158:
        return;
    L88:
        th = move-exception;
        Throwable r142 = th;
        r6 = r22;
    L131:
        if (r6 == null) goto L159;
        r6.close();     // Catch: Exception -> L134
        throw r142;
    L134:
        e = move-exception;
        FileLog.e(e);
        throw r142;
    L159:
        throw r142;
    L82:
        if (r8.length() == 0) goto L84;
    L92:
        r22.write(AndroidUtilities.getStringBytes(r8.toString()));     // Catch: Throwable -> L88 Exception -> L90
        if (TextUtils.isEmpty(r02) == true) goto L101;
        r22.write(AndroidUtilities.getStringBytes("WLS=" + r02 + "\n"));     // Catch: Throwable -> L88 Exception -> L90
        if (r16 == true) goto L137;
    L108:
        if (r17 == false) goto L110;
    L118:
        r22.close();     // Catch: Exception -> L120
        goto L139
    L110:
        if (themesDict.get(r14.getKey()) != null) goto L112;
        themes.add(r14);     // Catch: Throwable -> L88 Exception -> L90
        themesDict.put(r14.getKey(), r14);     // Catch: Throwable -> L88 Exception -> L90
        otherThemes.add(r14);     // Catch: Throwable -> L88 Exception -> L90
        saveOtherThemes(true);     // Catch: Throwable -> L88 Exception -> L90
        sortThemes();     // Catch: Throwable -> L88 Exception -> L90
    L112:
        currentTheme = r14;     // Catch: Throwable -> L88 Exception -> L90
        if (r14 == currentNightTheme) goto L115;
        currentDayTheme = r14;     // Catch: Throwable -> L88 Exception -> L90
    L115:
        if (r4 == false) goto L117;
        currentColorsNoAccent.clear();     // Catch: Throwable -> L88 Exception -> L90
        refreshThemeColors();     // Catch: Throwable -> L88 Exception -> L90
    L117:
        SharedPreferences.Editor r03 = MessagesController.getGlobalMainSettings().edit();     // Catch: Throwable -> L88 Exception -> L90
        r03.putString("theme", currentDayTheme.getKey());     // Catch: Throwable -> L88 Exception -> L90
        r03.apply();     // Catch: Throwable -> L88 Exception -> L90
    L137:
        Bitmap r13 = ((BitmapDrawable) r1).getBitmap();     // Catch: Throwable -> L98
        FileOutputStream r62 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(r02) + ".wp"));     // Catch: Throwable -> L98
        r13.compress(Bitmap.CompressFormat.JPEG, 87, r62);     // Catch: Throwable -> L98
        r62.close();     // Catch: Throwable -> L98
    L98:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L88 Exception -> L90
        goto L108
    L101:
        if ((r1 instanceof BitmapDrawable) == false) goto L108;
        Bitmap r04 = ((BitmapDrawable) r1).getBitmap();     // Catch: Throwable -> L88 Exception -> L90
        if (r04 == null) goto L105;
        r22.write(new byte[]{87, 80, 83, 10});     // Catch: Throwable -> L88 Exception -> L90
        r04.compress(Bitmap.CompressFormat.JPEG, 87, r22);     // Catch: Throwable -> L88 Exception -> L90
        r22.write(new byte[]{10, 87, 80, 69, 10});     // Catch: Throwable -> L88 Exception -> L90
    L105:
        if (r15 == false) goto L108;
        if (r17 == true) goto L108;
        wallpaper = r1;     // Catch: Throwable -> L88 Exception -> L90
        calcBackgroundColor(r1, 2);     // Catch: Throwable -> L88 Exception -> L90
        goto L108
    L84:
        if ((r1 instanceof BitmapDrawable) == true) goto L92;
        if (TextUtils.isEmpty(r02) == false) goto L92;
        r8.append(' ');     // Catch: Throwable -> L88 Exception -> L90
    L120:
        e = move-exception;
        FileLog.e(e);
    L124:
        e = e;
    L122:
        th = move-exception;
        r142 = th;
        goto L131
    L63:
        int r104 = 0;
    L65:
        if (r104 >= r7.size()) goto L140;
        int r112 = r7.keyAt(r104);
        int r122 = r7.valueAt(r104);
        if ((r1 instanceof BitmapDrawable) == true) goto L70;
        if (r02 != null) goto L70;
    L78:
        r8.append(ThemeColors.getStringName(r112));
        r8.append("=");
        r8.append(r122);
        r8.append("\n");
    L79:
        r104 = r104 + 1;
    L70:
        if (key_chat_wallpaper == r112) goto L79;
        if (key_chat_wallpaper_gradient_to1 == r112) goto L79;
        if (key_chat_wallpaper_gradient_to2 == r112) goto L79;
        if (key_chat_wallpaper_gradient_to3 != r112) goto L78;
    L21:
        r7 = currentColors;
        goto L22
    L15:
        if (r2.id != DEFALT_THEME_ACCENT_ID) goto L17;
        r4 = true;
        goto L18
    L8:
        r1 = themedWallpaper;
        goto L9
    L5:
        r02 = themedWallpaperLink;
        goto L6
    }

    private static void saveOtherThemes(boolean r1) {
        saveOtherThemes(r1, false);
    }

    private static void saveOtherThemes(boolean r9, boolean r10) {
        int r2 = 0;
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();
        if (r9 == false) goto L12;
        JSONArray r1 = new JSONArray();
        int r3 = 0;
    L6:
        if (r3 >= otherThemes.size()) goto L11;
        JSONObject r4 = otherThemes.get(r3).getSaveJson();
        if (r4 == null) goto L10;
        r1.put(r4);
    L10:
        r3 = r3 + 1;
        goto L6
    L11:
        r0.putString("themes2", r1.toString());
    L12:
        int r12 = 0;
    L14:
        if (r12 >= 16) goto L23;
        StringBuilder r32 = new StringBuilder("2remoteThemesHash");
        Object r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r12 == 0) goto L18;
        Object r5 = Integer.valueOf(r12);
    L19:
        r32.append(r5);
        r0.putLong(r32.toString(), remoteThemesHash[r12]);
        StringBuilder r33 = new StringBuilder("lastLoadingThemesTime");
        if (r12 == 0) goto L22;
        r42 = Integer.valueOf(r12);
    L22:
        r33.append(r42);
        r0.putInt(r33.toString(), lastLoadingThemesTime[r12]);
        r12 = r12 + 1;
        goto L14
    L18:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L23:
        r0.putInt("lastLoadingCurrentThemeTime", lastLoadingCurrentThemeTime);
        r0.apply();
        if (r9 == true) goto L26;
        return;
    L26:
        if (r2 >= 5) goto L58;
        if (r2 != 0) goto L29;
        String r92 = "Blue";
    L39:
        ThemeInfo r34 = themesDict.get(r92);
        if (r34 == null) goto L45;
        ArrayList<ThemeAccent> r93 = r34.themeAccents;
        if (r93 == null) goto L45;
        if (r93.isEmpty() == true) goto L45;
        boolean r8 = r10;
        saveThemeAccents(r34, true, false, false, false, r8);
    L47:
        r2 = r2 + 1;
        r10 = r8;
    L45:
        r8 = r10;
        goto L47
    L29:
        if (r2 != 1) goto L31;
        r92 = "Dark Blue";
        goto L39
    L31:
        if (r2 != 2) goto L33;
        r92 = "Arctic Blue";
        goto L39
    L33:
        if (r2 == 3) goto L35;
        r92 = "Night";
        goto L39
    L35:
        r92 = "Day";
        goto L39
    }

    public static void saveThemeAccents(ThemeInfo r6, boolean r7, boolean r8, boolean r9, boolean r10) {
        saveThemeAccents(r6, r7, r8, r9, r10, false);
    }

    public static void saveThemeAccents(ThemeInfo r6, boolean r7, boolean r8, boolean r9, boolean r10, boolean r11) {
        if (r7 == false) goto L26;
        SharedPreferences.Editor r72 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();
        if (r9 == true) goto L24;
        int r82 = r6.themeAccents.size();
        int r92 = Math.max(0, r82 - r6.defaultAccentCount);
        SerializedData r2 = new SerializedData(((r92 * 16) + 2) * 4);
        r2.writeInt32(9);
        r2.writeInt32(r92);
        int r93 = 0;
    L7:
        if (r93 >= r82) goto L19;
        ThemeAccent r3 = r6.themeAccents.get(r93);
        int r4 = r3.id;
        if (r4 < 100) goto L18;
        r2.writeInt32(r4);
        r2.writeInt32(r3.accentColor);
        r2.writeInt32(r3.accentColor2);
        r2.writeInt32(r3.myMessagesAccentColor);
        r2.writeInt32(r3.myMessagesGradientAccentColor1);
        r2.writeInt32(r3.myMessagesGradientAccentColor2);
        r2.writeInt32(r3.myMessagesGradientAccentColor3);
        r2.writeBool(r3.myMessagesAnimated);
        r2.writeInt64(r3.backgroundOverrideColor);
        r2.writeInt64(r3.backgroundGradientOverrideColor1);
        r2.writeInt64(r3.backgroundGradientOverrideColor2);
        r2.writeInt64(r3.backgroundGradientOverrideColor3);
        r2.writeInt32(r3.backgroundRotation);
        r2.writeInt64(0);
        r2.writeDouble(r3.patternIntensity);
        r2.writeBool(r3.patternMotion);
        r2.writeString(r3.patternSlug);
        if (r3.info == null) goto L14;
        boolean r42 = true;
    L15:
        r2.writeBool(r42);
        if (r3.info == null) goto L18;
        r2.writeInt32(r3.account);
        r3.info.serializeToStream(r2);
        goto L18
    L14:
        r42 = false;
    L18:
        r93 = r93 + 1;
        goto L7
    L19:
        r72.putString("accents_" + r6.assetName, Base64.encodeToString(r2.toByteArray(), 3));
        if (r11 == true) goto L22;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.themeAccentListUpdated, new Object[0]);
    L22:
        if (r10 == false) goto L24;
        MessagesController.getInstance(UserConfig.selectedAccount).saveThemeToServer(r6, r6.getAccent(false));
    L24:
        r72.putInt("accent_current_" + r6.assetName, r6.currentAccentId);
        r72.apply();
    L38:
        r6.prevAccentId = -1;
        return;
    L26:
        if (r6.prevAccentId == (-1)) goto L36;
        if (r8 == false) goto L31;
        ThemeAccent r73 = r6.themeAccentsMap.get(r6.currentAccentId);
        r6.themeAccentsMap.remove(r73.id);
        r6.themeAccents.remove(r73);
        TLRPC.TL_theme r74 = r73.info;
        if (r74 == null) goto L31;
        r6.accentsByThemeId.remove(r74.id);
    L31:
        r6.currentAccentId = r6.prevAccentId;
        ThemeAccent r75 = r6.getAccent(false);
        if (r75 == null) goto L34;
        r6.overrideWallpaper = r75.overrideWallpaper;
        goto L36
    L34:
        r6.overrideWallpaper = null;
    L36:
        if (currentTheme != r6) goto L38;
        refreshThemeColors();
        goto L38
    }

    public static void setAnimatedColor(int r1, int r2) {
        SparseIntArray r0 = animatingColors;
        if (r0 == null) goto L13;
        r0.put(r1, r2);
        if (r1 != key_divider) goto L11;
        Paint r12 = forcedDividerPaint;
        if (r12 == null) goto L12;
        r12.setColor(getDividerColor(null));
        return;
    L12:
        return;
    L11:
        return;
    }

    public static void setAnimatingColor(boolean r0) {
        if (r0 == false) goto L4;
        SparseIntArray r02 = new SparseIntArray();
    L5:
        animatingColors = r02;
        return;
    L4:
        r02 = null;
        goto L5
    }

    public static void setChangingWallpaper(boolean r0) {
        changingWallpaper = r0;
        if (r0 == true) goto L6;
        checkAutoNightThemeConditions(false);
        return;
    }

    public static void setColor(int r2, int r3, boolean r4) {
        int r0 = key_chat_wallpaper;
        if (r2 != r0) goto L5;
    L18:
        r3 = r3 | (-16777216);
    L19:
        if (r4 == false) goto L21;
        currentColors.delete(r2);
    L23:
        if (r2 != key_chat_selectedBackground) goto L27;
        applyChatMessageSelectedBackgroundColor();
        return;
    L27:
        if (r2 != key_chat_serviceBackground) goto L29;
    L53:
        applyChatServiceMessageColor();
        return;
    L29:
        if (r2 == key_chat_serviceBackgroundSelected) goto L53;
        if (r2 != r0) goto L33;
    L51:
        reloadWallpaper(true);
        return;
    L33:
        if (r2 == key_chat_wallpaper_gradient_to1) goto L51;
        if (r2 == key_chat_wallpaper_gradient_to2) goto L51;
        if (r2 == key_chat_wallpaper_gradient_to3) goto L51;
        if (r2 == key_chat_wallpaper_gradient_rotation) goto L51;
        if (r2 != key_actionBarDefault) goto L46;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        return;
    L46:
        if (r2 == key_windowBackgroundGray) goto L48;
        return;
    L48:
        if (Build.VERSION.SDK_INT < 26) goto L56;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        return;
    L56:
        return;
    L21:
        currentColors.put(r2, r3);
        goto L23
    L5:
        if (r2 == key_chat_wallpaper_gradient_to1) goto L18;
        if (r2 == key_chat_wallpaper_gradient_to2) goto L18;
        if (r2 == key_chat_wallpaper_gradient_to3) goto L18;
        if (r2 == key_windowBackgroundWhite) goto L18;
        if (r2 == key_windowBackgroundGray) goto L18;
        if (r2 == key_actionBarDefault) goto L18;
        if (r2 != key_actionBarDefaultArchived) goto L19;
        goto L18
    }

    public static void setCombinedDrawableColor(Drawable r1, int r2, boolean r3) {
        if ((r1 instanceof CombinedDrawable) == true) goto L5;
        return;
    L5:
        if (r3 == false) goto L7;
        Drawable r12 = ((CombinedDrawable) r1).getIcon();
    L9:
        if ((r12 instanceof ColorDrawable) == false) goto L12;
        ((ColorDrawable) r12).setColor(r2);
        return;
    L12:
        r12.setColorFilter(new PorterDuffColorFilter(r2, PorterDuff.Mode.MULTIPLY));
        return;
    L7:
        r12 = ((CombinedDrawable) r1).getBackground();
        goto L9
    }

    public static void setCurrentNightTheme(ThemeInfo r3) {
        if (currentTheme != currentNightTheme) goto L5;
        boolean r0 = true;
    L6:
        currentNightTheme = r3;
        if (r0 == false) goto L10;
        applyDayNightThemeMaybe(true);
        return;
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static void setDefaultColor(int r1, int r2) {
        defaultColors[r1] = r2;
    }

    public static void setDrawableColor(Drawable r2, int r3) {
        if (r2 != null) goto L5;
        return;
    L5:
        if ((r2 instanceof StatusDrawable) == false) goto L9;
        ((StatusDrawable) r2).setColor(r3);
        return;
    L9:
        if ((r2 instanceof MsgClockDrawable) == false) goto L13;
        ((MsgClockDrawable) r2).setColor(r3);
        return;
    L13:
        if ((r2 instanceof ShapeDrawable) == false) goto L17;
        ((ShapeDrawable) r2).getPaint().setColor(r3);
        return;
    L17:
        if ((r2 instanceof ScamDrawable) == false) goto L20;
        ((ScamDrawable) r2).setColor(r3);
        return;
    L20:
        r2.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
    }

    public static void setDrawableColorByKey(Drawable r0, int r1) {
        setDrawableColor(r0, getColor(r1));
    }

    public static void setEmojiDrawableColor(Drawable r1, int r2, boolean r3) {
        if ((r1 instanceof StateListDrawable) == false) goto L16;
        if (r3 == true) goto L6;
        Drawable r12 = getStateDrawable(r1, 1);     // Catch: Throwable -> L14
    L9:
        if ((r12 instanceof ShapeDrawable) == false) goto L12;
        ((ShapeDrawable) r12).getPaint().setColor(r2);     // Catch: Throwable -> L14
        return;
    L12:
        r12.setColorFilter(new PorterDuffColorFilter(r2, PorterDuff.Mode.MULTIPLY));     // Catch: Throwable -> L14
        return;
    L6:
        r12 = getStateDrawable(r1, 0);     // Catch: Throwable -> L14
        goto L9
    L17:
        return;
    }

    public static void setMaskDrawableRad(Drawable r4, float r5, float r6, float r7, float r8) {
        if ((r4 instanceof RippleDrawable) == false) goto L11;
        RippleDrawable r42 = (RippleDrawable) r4;
        int r0 = r42.getNumberOfLayers();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        Drawable r2 = r42.getDrawable(r1);
        if ((r2 instanceof RippleRadMaskDrawable) == true) goto L8;
        r1 = r1 + 1;
        goto L5
    L8:
        ((RippleRadMaskDrawable) r2).setRadius(r5, r6, r7, r8);
        return;
    L14:
        return;
    }

    public static void setMaskDrawableRad(Drawable r4, int r5, int r6) {
        if ((r4 instanceof RippleDrawable) == false) goto L11;
        RippleDrawable r42 = (RippleDrawable) r4;
        int r0 = r42.getNumberOfLayers();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        Drawable r2 = r42.getDrawable(r1);
        if ((r2 instanceof RippleRadMaskDrawable) == true) goto L8;
        r1 = r1 + 1;
        goto L5
    L8:
        ((RippleRadMaskDrawable) r2).setRadius(r5, r6);
        return;
    L14:
        return;
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public static void setRippleDrawableForceSoftware(RippleDrawable r3) {
        if (r3 == null) goto L9;
        RippleDrawable.class.getDeclaredMethod("setForceSoftware", new Class[]{Boolean.TYPE}).invoke(r3, new Object[]{Boolean.TRUE});     // Catch: Throwable -> L6
        return;
    L10:
        return;
    }

    public static boolean setSelectorDrawableColor(Drawable r4, int r5, boolean r6) {
        boolean r1 = true;
        if ((r4 instanceof StateListDrawable) == false) goto L35;
        if (r6 == false) goto L19;
        Drawable r62 = getStateDrawable(r4, 0);     // Catch: Throwable -> L51
        if ((r62 instanceof ShapeDrawable) == true) goto L8;
        r62.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));     // Catch: Throwable -> L51
        boolean r0 = false;
    L16:
        Drawable r42 = getStateDrawable(r4, 1);     // Catch: Throwable -> L13
    L22:
        if ((r42 instanceof ShapeDrawable) == true) goto L24;
        r42.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));     // Catch: Throwable -> L13
        return r0;
    L24:
        if (((ShapeDrawable) r42).getPaint().getColor() != r5) goto L52;
        if (r0 == true) goto L52;
        r1 = false;
    L52:
        ((ShapeDrawable) r42).getPaint().setColor(r5);     // Catch: Throwable -> L30
        return r1;
    L56:
        return r1;
    L8:
        if (((ShapeDrawable) r62).getPaint().getColor() != r5) goto L10;
        r0 = false;
    L54:
        ((ShapeDrawable) r62).getPaint().setColor(r5);     // Catch: Throwable -> L13
        goto L16
    L33:
        return r0;
    L10:
        r0 = true;
        goto L54
    L19:
        r42 = getStateDrawable(r4, 2);     // Catch: Throwable -> L51
        r0 = false;
        goto L22
    L57:
        return false;
    L35:
        if ((r4 instanceof RippleDrawable) == false) goto L50;
        RippleDrawable r43 = (RippleDrawable) r4;
        if (r6 == false) goto L41;
        r43.setColor(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{r5}));
        return false;
    L41:
        if (r43.getNumberOfLayers() <= 0) goto L50;
        Drawable r44 = r43.getDrawable(0);
        if ((r44 instanceof ShapeDrawable) == false) goto L49;
        ShapeDrawable r45 = (ShapeDrawable) r44;
        if (r45.getPaint().getColor() != r5) goto L47;
        r1 = false;
    L47:
        r45.getPaint().setColor(r5);
        return r1;
    L49:
        r44.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));
    L50:
        return false;
    }

    public static void setThemeFileReference(TLRPC.TL_theme r7) {
        int r0 = themes.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L21;
        TLRPC.TL_theme r2 = themes.get(r1).info;
        if (r2 == null) goto L14;
        if (r2.id != r7.id) goto L14;
        TLRPC.Document r02 = r2.document;
        if (r02 == null) goto L15;
        TLRPC.Document r72 = r7.document;
        if (r72 == null) goto L20;
        r02.file_reference = r72.file_reference;
        saveOtherThemes(true);
        return;
    L20:
        return;
    L15:
        return;
    L14:
        r1 = r1 + 1;
        goto L3
    }

    public static void setThemeUploadInfo(ThemeInfo r7, ThemeAccent r8, TLRPC.TL_theme r9, int r10, boolean r11) {
        if (r9 != null) goto L5;
        return;
    L5:
        if (r9.settings.size() <= 0) goto L7;
        TLRPC.ThemeSettings r0 = r9.settings.get(0);
    L9:
        if (r0 == null) goto L47;
        if (r7 != null) goto L18;
        String r72 = getBaseThemeKey(r0);
        if (r72 == null) goto L67;
        r7 = themesDict.get(r72);
        if (r7 == null) goto L68;
        r8 = r7.accentsByThemeId.get(r9.id);
        goto L18
    L68:
        return;
    L67:
        return;
    L18:
        if (r8 == null) goto L69;
        TLRPC.TL_theme r112 = r8.info;
        if (r112 == null) goto L23;
        r7.accentsByThemeId.remove(r112.id);
    L23:
        r8.info = r9;
        r8.account = r10;
        r7.accentsByThemeId.put(r9.id, r8);
        if (ThemeInfo.accentEquals(r8, r0) == true) goto L38;
        File r92 = r8.getPathToWallpaper();
        if (r92 == null) goto L28;
        r92.delete();
    L28:
        ThemeInfo.fillAccentValues(r8, r0);
        ThemeInfo r93 = currentTheme;
        if (r93 == r7) goto L31;
    L37:
        PatternsLoader.createLoader(true);
        goto L38
    L31:
        if (r93.currentAccentId != r8.id) goto L37;
        refreshThemeColors();
        NotificationCenter r94 = NotificationCenter.getGlobalInstance();
        int r102 = NotificationCenter.needSetDayNightTheme;
        ThemeInfo r113 = currentTheme;
        if (currentNightTheme != r113) goto L35;
        boolean r4 = true;
    L36:
        r94.postNotificationName(r102, new Object[]{r113, Boolean.valueOf(r4), null, -1});
        goto L37
    L35:
        r4 = false;
    L38:
        TLRPC.WallPaper r95 = r0.wallpaper;
        if (r95 == null) goto L45;
        TLRPC.WallPaperSettings r96 = r95.settings;
        if (r96 == null) goto L45;
        if (r96.motion == false) goto L45;
        boolean r97 = true;
    L46:
        r8.patternMotion = r97;
        r7.previewParsed = false;
    L62:
        saveOtherThemes(true);
        return;
    L45:
        r97 = false;
        goto L46
    L69:
        return;
    L47:
        if (r7 == null) goto L49;
        HashMap<String, ThemeInfo> r82 = themesDict;
        String r103 = r7.getKey();
        r82.remove(r103);
    L50:
        if (r7 != null) goto L52;
        return;
    L52:
        r7.info = r9;
        r7.name = r9.title;
        File r83 = new File(r7.pathToFile);
        File r98 = new File(ApplicationLoader.getFilesDirFixed(), r103 + ".attheme");
        if (r83.equals(r98) == false) goto L64;
    L58:
        if (r11 == false) goto L60;
        ThemeInfo.m5835$$Nest$mloadThemeDocument(r7);
    L61:
        themesDict.put(r7.getKey(), r7);
        goto L62
    L60:
        r7.previewParsed = false;
        goto L61
    L64:
        AndroidUtilities.copyFile(r83, r98);     // Catch: Exception -> L56
        r7.pathToFile = r98.getAbsolutePath();     // Catch: Exception -> L56
    L56:
        e = move-exception;
        FileLog.e(e);
        goto L58
    L49:
        HashMap<String, ThemeInfo> r73 = themesDict;
        r103 = "remote" + r9.id;
        r7 = r73.get(r103);
        goto L50
    L7:
        r0 = null;
        goto L9
    }

    public static void setThemeWallpaper(ThemeInfo r1, Bitmap r2, File r3) {
        currentColors.delete(key_chat_wallpaper);
        currentColors.delete(key_chat_wallpaper_gradient_to1);
        currentColors.delete(key_chat_wallpaper_gradient_to2);
        currentColors.delete(key_chat_wallpaper_gradient_to3);
        currentColors.delete(key_chat_wallpaper_gradient_rotation);
        themedWallpaperLink = null;
        r1.setOverrideWallpaper(null);
        if (r2 == null) goto L6;
        themedWallpaper = new BitmapDrawable(r2);
        saveCurrentTheme(r1, false, false, false);
        calcBackgroundColor(themedWallpaper, 0);
        applyChatServiceMessageColor();
        applyChatMessageSelectedBackgroundColor();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
        return;
    L6:
        themedWallpaper = null;
        wallpaper = null;
        saveCurrentTheme(r1, false, false, false);
        reloadWallpaper(true);
    }

    private static void sortAccents(ThemeInfo r1) {
        Collections.sort(r1.themeAccents, new Theme$$ExternalSyntheticLambda7());
    }

    private static void sortThemes() {
        Collections.sort(themes, new Theme$$ExternalSyntheticLambda1());
    }

    private static int tableOutBubble(SparseIntArray r8) {
        int[] r0 = {key_chat_outBubble, key_chat_outBubbleGradient1, key_chat_outBubbleGradient2, key_chat_outBubbleGradient3};
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r1 >= 4) goto L13;
        int r6 = r0[r1];
        if (r6 != key_chat_outBubble) goto L8;
    L10:
        int r62 = r8.get(r6, defaultColors[r6]);
        r2 = r2 + Color.red(r62);
        r4 = r4 + Color.green(r62);
        r5 = r5 + Color.blue(r62);
        r3 = r3 + 1;
    L11:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r8.indexOfKey(r6) >= 0) goto L10;
    L13:
        return Color.rgb(r2 / r3, r4 / r3, r5 / r3);
    }

    public static void turnOffAutoNight(BaseFragment r7) {
        if (selectedAutoNightType == 0) goto L18;
        if (r7 != null) goto L16;
    L14:
        selectedAutoNightType = 0;
        saveAutoNightThemeConfig();
        cancelAutoNightThemeCallbacks();
        return;
    L16:
        BulletinFactory r1 = BulletinFactory.of(r7);     // Catch: Exception -> L12
        int r2 = R.raw.auto_night_off;     // Catch: Exception -> L12
        if (selectedAutoNightType != 3) goto L9;
        String r0 = LocaleController.getString("AutoNightSystemModeOff", R.string.AutoNightSystemModeOff);     // Catch: Exception -> L12
    L8:
        String r3 = r0;
        r1.createSimpleBulletin(r2, r3, LocaleController.getString("Settings", R.string.Settings), 5000, new Theme$$ExternalSyntheticLambda14(r7)).show();     // Catch: Exception -> L12
        goto L14
    L9:
        r0 = LocaleController.getString("AutoNightModeOff", R.string.AutoNightModeOff);     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    }

    public static void turnOffAutoNight(BulletinFactory r7, Runnable r8) {
        int r0 = selectedAutoNightType;
        if (r0 == 0) goto L19;
        if (r7 == null) goto L15;
        if (r8 == null) goto L15;
        int r2 = R.raw.auto_night_off;     // Catch: Exception -> L13
        if (r0 != 3) goto L10;
        String r02 = LocaleController.getString("AutoNightSystemModeOff", R.string.AutoNightSystemModeOff);     // Catch: Exception -> L13
    L9:
        String r3 = r02;
        r7.createSimpleBulletin(r2, r3, LocaleController.getString("Settings", R.string.Settings), 5000, r8).show();     // Catch: Exception -> L13
        goto L15
    L10:
        r02 = LocaleController.getString("AutoNightModeOff", R.string.AutoNightModeOff);     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
    L15:
        selectedAutoNightType = 0;
        saveAutoNightThemeConfig();
        cancelAutoNightThemeCallbacks();
        return;
    }

    public static void unrefAudioVisualizeDrawable(MessageObject r4) {
        AudioVisualizerDrawable r0 = chat_msgAudioVisualizeDrawable;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getParentView() == null) goto L14;
        if (r4 == null) goto L14;
        if (animatedOutVisualizerDrawables != null) goto L12;
        animatedOutVisualizerDrawables = new HashMap();
    L12:
        animatedOutVisualizerDrawables.put(r4, chat_msgAudioVisualizeDrawable);
        chat_msgAudioVisualizeDrawable.setWaveform(false, true, null);
        AndroidUtilities.runOnUIThread(new Theme$$ExternalSyntheticLambda3(r4), 200);
        chat_msgAudioVisualizeDrawable = null;
        return;
    L14:
        chat_msgAudioVisualizeDrawable.setParentView(null);
    }

    private static boolean useBlackText(int r6, int r7) {
        float r0 = Color.red(r6) / 255.0f;
        float r3 = Color.green(r6) / 255.0f;
        float r62 = Color.blue(r6) / 255.0f;
        float r5 = (r0 * 0.5f) + ((Color.red(r7) / 255.0f) * 0.5f);
        float r02 = ((r0 * 0.2126f) + (r3 * 0.7152f)) + (r62 * 0.0722f);
        if ((((r5 * 0.2126f) + (((r3 * 0.5f) + ((Color.green(r7) / 255.0f) * 0.5f)) * 0.7152f)) + (((r62 * 0.5f) + ((Color.blue(r7) / 255.0f) * 0.5f)) * 0.0722f)) <= 0.705f) goto L5;
        return true;
    L5:
        if (r02 > 0.705f) goto L11;
        return false;
    L11:
        return true;
    }
}
