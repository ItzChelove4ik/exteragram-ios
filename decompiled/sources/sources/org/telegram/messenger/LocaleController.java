package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Xml;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.material.timepicker.TimeModel;
import j$.util.DesugarTimeZone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.localization.LocalizationUtils;
import org.telegram.messenger.time.FastDateFormat;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.RestrictedLanguagesSelectActivity;
import org.telegram.utils.localization.Localization;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class LocaleController {
    private static volatile LocaleController Instance = null;
    private static final HashMap<String, String> LEGACY_LANGUAGE_ALIASES = null;
    static final int QUANTITY_FEW = 8;
    static final int QUANTITY_MANY = 16;
    static final int QUANTITY_ONE = 2;
    static final int QUANTITY_OTHER = 0;
    static final int QUANTITY_TWO = 4;
    static final int QUANTITY_ZERO = 1;
    private static char[] defaultNumbers = null;
    public static boolean is24HourFormat = false;
    public static boolean isRTL = false;
    public static int nameDisplayOrder = 1;
    private static char[][] otherNumbers;
    private static Boolean useImperialSystemType;
    private HashMap<String, PluralRules> allRules;
    private boolean changingConfiguration;
    private volatile FastDateFormat chatDate;
    private volatile FastDateFormat chatDateShort;
    private volatile FastDateFormat chatFullDate;
    private boolean checkingUpdateForCurrentRemoteLocale;
    private HashMap<String, String> currencyValues;
    private Locale currentLocale;
    private LocaleInfo currentLocaleInfo;
    private PluralRules currentPluralRules;
    private String currentSystemLocale;
    private volatile FastDateFormat exportFileFormatter;
    private volatile FastDateFormat exportFullDateFormatter;
    private volatile FastDateFormat formatterBannedUntil;
    private volatile FastDateFormat formatterBannedUntilThisYear;
    private volatile FastDateFormat formatterBoostExpired;
    private volatile FastDateFormat formatterConstDay;
    private volatile FastDateFormat formatterDay;
    private volatile FastDateFormat formatterDayMonth;
    private volatile FastDateFormat formatterDayWithSeconds;
    private volatile FastDateFormat formatterGiveawayCard;
    private volatile FastDateFormat formatterGiveawayMonthDay;
    private volatile FastDateFormat formatterGiveawayMonthDayYear;
    private volatile FastDateFormat formatterMonthOnly;
    private volatile FastDateFormat formatterMonthYear;
    private volatile FastDateFormat formatterScheduleDay;
    private final FastDateFormat[] formatterScheduleSend;
    private volatile FastDateFormat formatterScheduleYear;
    private volatile FastDateFormat formatterStats;
    private volatile FastDateFormat formatterWeek;
    private volatile FastDateFormat formatterWeekLong;
    private volatile FastDateFormat formatterYear;
    private volatile FastDateFormat formatterYearMax;
    private String languageOverride;
    public ArrayList<LocaleInfo> languages;
    public HashMap<String, LocaleInfo> languagesDict;
    private boolean loadingRemoteLanguages;
    private Localization localizationExternal;
    private int localizationExternalSize;
    private volatile Localization localizationInternal;
    private Localization localizationInternalDefault;
    private volatile Locale localizationInternalLastLocale;
    private volatile boolean localizationInternalPending;
    private ArrayList<LocaleInfo> otherLanguages;
    private boolean patching;
    private boolean reloadLastFile;
    public ArrayList<LocaleInfo> remoteLanguages;
    public HashMap<String, LocaleInfo> remoteLanguagesDict;
    private HashMap<String, String> ruTranslitChars;
    private Locale systemDefaultLocale;
    private HashMap<String, String> translitChars;
    public ArrayList<LocaleInfo> unofficialLanguages;

    public static class LocaleInfo {
        public String baseLangCode;
        public int baseVersion;
        public boolean builtIn;
        public boolean isRtl;
        public String name;
        public String nameEnglish;
        public String pathToFile;
        public String pluralLangCode;
        public int serverIndex;
        public String shortName;
        public int version;

        public LocaleInfo() {
        }

        public static LocaleInfo createWithString(String r6) {
            LocaleInfo r0 = null;
            if (r6 != null) goto L5;
        L36:
            return r0;
        L5:
            if (r6.length() == 0) goto L36;
            String[] r62 = r6.split("\\|");
            if (r62.length < 4) goto L36;
            r0 = new LocaleInfo();
            boolean r1 = false;
            r0.name = r62[0];
            r0.nameEnglish = r62[1];
            r0.shortName = r62[2].toLowerCase();
            r0.pathToFile = r62[3];
            if (r62.length < 5) goto L13;
            r0.version = Utilities.parseInt(r62[4]);
        L13:
            if (r62.length < 6) goto L15;
            String r2 = r62[5];
        L16:
            r0.baseLangCode = r2;
            if (r62.length < 7) goto L19;
            String r22 = r62[6];
        L20:
            r0.pluralLangCode = r22;
            if (r62.length < 8) goto L27;
            if (Utilities.parseInt(r62[7]) != 1) goto L25;
            r1 = true;
        L25:
            r0.isRtl = r1;
        L27:
            if (r62.length < 9) goto L30;
            r0.baseVersion = Utilities.parseInt(r62[8]);
        L30:
            if (r62.length < 10) goto L32;
            r0.serverIndex = Utilities.parseInt(r62[9]);
        L34:
            if (TextUtils.isEmpty(r0.baseLangCode) == true) goto L36;
            r0.baseLangCode = r0.baseLangCode.replace("-", "_");
            goto L36
        L32:
            r0.serverIndex = Integer.MAX_VALUE;
            goto L34
        L19:
            r22 = r0.shortName;
            goto L20
        L15:
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L16
        }

        public String getBaseLangCode() {
            String r2 = this.baseLangCode;
            if (r2 != null) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return r2.replace("_", "-");
        }

        public String getKey() {
            if (this.pathToFile != null) goto L5;
        L10:
            boolean r0 = isUnofficial();
            String r2 = this.shortName;
            if (r0 == true) goto L13;
            return r2;
        L13:
            return "unofficial_" + r2;
        L5:
            if (isRemote() == true) goto L10;
            if (isUnofficial() == true) goto L10;
            return "local_" + this.shortName;
        }

        public String getLangCode() {
            return this.shortName.replace("_", "-");
        }

        public File getPathToBaseFile() {
            if (isUnofficial() == true) goto L5;
            return null;
        L5:
            return new File(ApplicationLoader.getFilesDirFixed(), "unofficial_base_" + this.shortName + ".xml");
        }

        public File getPathToFile() {
            if (isRemote() == false) goto L7;
            return new File(ApplicationLoader.getFilesDirFixed(), "remote_" + this.shortName + ".xml");
        L7:
            if (isUnofficial() == false) goto L11;
            return new File(ApplicationLoader.getFilesDirFixed(), "unofficial_" + this.shortName + ".xml");
        L11:
            if (TextUtils.isEmpty(this.pathToFile) == false) goto L13;
            return null;
        L13:
            return new File(this.pathToFile);
        }

        public String getSaveString() {
            String r0 = this.baseLangCode;
            if (r0 != null) goto L5;
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            TextUtils.isEmpty(this.pluralLangCode);
            return this.name + "|" + this.nameEnglish + "|" + this.shortName + "|" + this.pathToFile + "|" + this.version + "|" + r0 + "|" + this.pluralLangCode + "|" + (this.isRtl ? 1 : 0) + "|" + this.baseVersion + "|" + this.serverIndex;
        }

        public boolean hasBaseLang() {
            if (isUnofficial() == true) goto L5;
            return false;
        L5:
            if (TextUtils.isEmpty(this.baseLangCode) == false) goto L7;
            return false;
        L7:
            if (this.baseLangCode.equals(this.shortName) == true) goto L13;
            return true;
        L13:
            return false;
        }

        public boolean isBuiltIn() {
            return this.builtIn;
        }

        public boolean isLocal() {
            if (TextUtils.isEmpty(this.pathToFile) == false) goto L5;
            return false;
        L5:
            if (isRemote() == false) goto L7;
            return false;
        L7:
            if (isUnofficial() == true) goto L13;
            return true;
        L13:
            return false;
        }

        public boolean isRemote() {
            return "remote".equals(this.pathToFile);
        }

        public boolean isUnofficial() {
            return "unofficial".equals(this.pathToFile);
        }
    }

    public static abstract class PluralRules {
        public PluralRules() {
        }

        public abstract int quantityForNumber(int r1);
    }

    public static class PluralRules_Arabic extends PluralRules {
        public PluralRules_Arabic() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r3) {
            int r2 = r3 % 100;
            if (r3 != 0) goto L6;
            return 1;
        L6:
            if (r3 != 1) goto L8;
            return 2;
        L8:
            if (r3 != 2) goto L12;
            return 4;
        L12:
            if (r2 < 3) goto L18;
            if (r2 > 10) goto L18;
            return 8;
        L18:
            if (r2 >= 11) goto L20;
            return 0;
        L20:
            if (r2 > 99) goto L25;
            return 16;
        L25:
            return 0;
        }
    }

    public static class PluralRules_Balkan extends PluralRules {
        public PluralRules_Balkan() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r4) {
            int r3 = r4 % 100;
            int r42 = r4 % 10;
            if (r42 != 1) goto L7;
            if (r3 == 11) goto L7;
            return 2;
        L7:
            if (r42 >= 2) goto L9;
        L15:
            if (r42 != 0) goto L17;
            return 16;
        L17:
            if (r42 >= 5) goto L19;
        L20:
            if (r3 < 11) goto L23;
            if (r3 <= 14) goto L30;
            return 0;
        L30:
            return 16;
        L23:
            return 0;
        L19:
            if (r42 > 9) goto L20;
            return 16;
        L9:
            if (r42 > 4) goto L15;
            if (r3 < 12) goto L13;
            if (r3 <= 14) goto L15;
            return 8;
        L13:
            return 8;
        }
    }

    public static class PluralRules_Breton extends PluralRules {
        public PluralRules_Breton() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if (r2 != 0) goto L6;
            return 1;
        L6:
            if (r2 != 1) goto L8;
            return 2;
        L8:
            if (r2 != 2) goto L12;
            return 4;
        L12:
            if (r2 != 3) goto L16;
            return 8;
        L16:
            if (r2 != 6) goto L19;
            return 16;
        L19:
            return 0;
        }
    }

    public static class PluralRules_Czech extends PluralRules {
        public PluralRules_Czech() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if (r2 != 1) goto L5;
            return 2;
        L5:
            if (r2 >= 2) goto L7;
            return 0;
        L7:
            if (r2 > 4) goto L12;
            return 8;
        L12:
            return 0;
        }
    }

    public static class PluralRules_French extends PluralRules {
        public PluralRules_French() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r1) {
            if (r1 >= 0) goto L4;
            return 0;
        L4:
            if (r1 >= 2) goto L8;
            return 2;
        L8:
            return 0;
        }
    }

    public static class PluralRules_Langi extends PluralRules {
        public PluralRules_Langi() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r1) {
            if (r1 != 0) goto L5;
            return 1;
        L5:
            if (r1 != 1) goto L8;
            return 2;
        L8:
            return 0;
        }
    }

    public static class PluralRules_Latvian extends PluralRules {
        public PluralRules_Latvian() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if (r2 != 0) goto L6;
            return 1;
        L6:
            if ((r2 % 10) == 1) goto L8;
            return 0;
        L8:
            if ((r2 % 100) == 11) goto L13;
            return 2;
        L13:
            return 0;
        }
    }

    public static class PluralRules_Lithuanian extends PluralRules {
        public PluralRules_Lithuanian() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r5) {
            int r4 = r5 % 100;
            int r52 = r5 % 10;
            if (r52 != 1) goto L7;
            if (r4 < 11) goto L6;
            if (r4 <= 19) goto L7;
        L6:
            return 2;
        L7:
            if (r52 >= 2) goto L9;
            return 0;
        L9:
            if (r52 > 9) goto L17;
            if (r4 < 11) goto L12;
            if (r4 <= 19) goto L18;
            return 8;
        L18:
            return 0;
        L12:
            return 8;
        L17:
            return 0;
        }
    }

    public static class PluralRules_Macedonian extends PluralRules {
        public PluralRules_Macedonian() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if ((r2 % 10) == 1) goto L5;
            return 0;
        L5:
            if (r2 == 11) goto L10;
            return 2;
        L10:
            return 0;
        }
    }

    public static class PluralRules_Maltese extends PluralRules {
        public PluralRules_Maltese() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r3) {
            int r2 = r3 % 100;
            if (r3 != 1) goto L5;
            return 2;
        L5:
            if (r3 == 0) goto L18;
            if (r2 < 2) goto L11;
            if (r2 > 10) goto L11;
            return 8;
        L11:
            if (r2 >= 11) goto L13;
            return 0;
        L13:
            if (r2 > 19) goto L20;
            return 16;
        L20:
            return 0;
        L18:
            return 8;
        }
    }

    public static class PluralRules_None extends PluralRules {
        public PluralRules_None() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r1) {
            return 0;
        }
    }

    public static class PluralRules_One extends PluralRules {
        public PluralRules_One() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r1) {
            if (r1 != 1) goto L6;
            return 2;
        L6:
            return 0;
        }
    }

    public static class PluralRules_Polish extends PluralRules {
        public PluralRules_Polish() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r5) {
            int r4 = r5 % 100;
            int r0 = r5 % 10;
            if (r5 != 1) goto L6;
            return 2;
        L6:
            if (r0 >= 2) goto L8;
        L13:
            if (r0 < 0) goto L16;
            if (r0 > 1) goto L16;
            return 16;
        L16:
            if (r0 >= 5) goto L18;
        L19:
            if (r4 < 12) goto L23;
            if (r4 > 14) goto L28;
            return 16;
        L28:
            return 0;
        L23:
            return 0;
        L18:
            if (r0 > 9) goto L19;
            return 16;
        L8:
            if (r0 > 4) goto L13;
            if (r4 < 12) goto L11;
            if (r4 <= 14) goto L13;
            return 8;
        L11:
            return 8;
        }
    }

    public static class PluralRules_Romanian extends PluralRules {
        public PluralRules_Romanian() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            int r1 = r2 % 100;
            if (r2 != 1) goto L6;
            return 2;
        L6:
            if (r2 == 0) goto L13;
            if (r1 >= 1) goto L9;
            return 0;
        L9:
            if (r1 <= 19) goto L16;
            return 0;
        L16:
            return 8;
        L13:
            return 8;
        }
    }

    public static class PluralRules_Serbian extends PluralRules {
        public PluralRules_Serbian() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r3) {
            int r2 = r3 % 100;
            int r32 = r3 % 10;
            if (r32 == 1) goto L5;
        L7:
            if (r32 >= 2) goto L9;
            return 0;
        L9:
            if (r32 <= 4) goto L11;
            return 0;
        L11:
            if (r2 >= 12) goto L13;
            return 8;
        L13:
            if (r2 <= 14) goto L20;
            return 8;
        L20:
            return 0;
        L5:
            if (r2 == 11) goto L7;
            return 2;
        }
    }

    public static class PluralRules_Slovenian extends PluralRules {
        public PluralRules_Slovenian() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            int r22 = r2 % 100;
            if (r22 != 1) goto L6;
            return 2;
        L6:
            if (r22 != 2) goto L9;
            return 4;
        L9:
            if (r22 < 3) goto L13;
            if (r22 > 4) goto L15;
            return 8;
        L15:
            return 0;
        L13:
            return 0;
        }
    }

    public static class PluralRules_Tachelhit extends PluralRules {
        public PluralRules_Tachelhit() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if (r2 >= 0) goto L5;
        L7:
            if (r2 >= 2) goto L9;
            return 0;
        L9:
            if (r2 > 10) goto L14;
            return 8;
        L14:
            return 0;
        L5:
            if (r2 > 1) goto L7;
            return 2;
        }
    }

    public static class PluralRules_Two extends PluralRules {
        public PluralRules_Two() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if (r2 != 1) goto L5;
            return 2;
        L5:
            if (r2 != 2) goto L8;
            return 4;
        L8:
            return 0;
        }
    }

    public static class PluralRules_Welsh extends PluralRules {
        public PluralRules_Welsh() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r2) {
            if (r2 != 0) goto L6;
            return 1;
        L6:
            if (r2 != 1) goto L8;
            return 2;
        L8:
            if (r2 != 2) goto L12;
            return 4;
        L12:
            if (r2 != 3) goto L16;
            return 8;
        L16:
            if (r2 != 6) goto L19;
            return 16;
        L19:
            return 0;
        }
    }

    public static class PluralRules_Zero extends PluralRules {
        public PluralRules_Zero() {
        }

        @Override // org.telegram.messenger.LocaleController.PluralRules
        public int quantityForNumber(int r1) {
            if (r1 != 0) goto L4;
            return 2;
        L4:
            if (r1 == 1) goto L10;
            return 0;
        L10:
            return 2;
        }
    }

    /* loaded from: classes5.dex */
    public static final class RelativeIcu {
        private RelativeIcu() {
        }

        public static String format(long r4, Locale r6) {
            RelativeDateTimeFormatter r62 = RelativeDateTimeFormatter.getInstance(r6);
            if (r4 <= 0) goto L5;
            boolean r0 = true;
        L6:
            long r42 = Math.max(1, Math.round(Math.abs(r4) / 1000.0d));
            if (r42 >= 60) goto L10;
            RelativeDateTimeFormatter.RelativeUnit r1 = RelativeDateTimeFormatter.RelativeUnit.SECONDS;
        L22:
            if (r0 == false) goto L24;
            RelativeDateTimeFormatter.Direction r02 = RelativeDateTimeFormatter.Direction.NEXT;
        L26:
            return r62.format(r42, r02, r1);
        L24:
            r02 = RelativeDateTimeFormatter.Direction.LAST;
            goto L26
        L10:
            if (r42 >= 3600) goto L13;
            r1 = RelativeDateTimeFormatter.RelativeUnit.MINUTES;
            r42 = Math.round(r42 / 60.0d);
            goto L22
        L13:
            if (r42 >= 86400) goto L16;
            r1 = RelativeDateTimeFormatter.RelativeUnit.HOURS;
            r42 = Math.round(r42 / 3600.0d);
            goto L22
        L16:
            if (r42 >= 2592000) goto L19;
            r1 = RelativeDateTimeFormatter.RelativeUnit.DAYS;
            r42 = Math.round(r42 / 86400.0d);
            goto L22
        L19:
            if (r42 >= 31536000) goto L21;
            r1 = RelativeDateTimeFormatter.RelativeUnit.MONTHS;
            r42 = Math.round(r42 / 2592000.0d);
            goto L22
        L21:
            r1 = RelativeDateTimeFormatter.RelativeUnit.YEARS;
            r42 = Math.round(r42 / 3.1536E7d);
            goto L22
        L5:
            r0 = false;
            goto L6
        }
    }

    public class TimeZoneChangedReceiver extends BroadcastReceiver {
        final /* synthetic */ LocaleController this$0;

        public static /* synthetic */ void $r8$lambda$By246fIk9yE4bC6c47wtif3tUGU(TimeZoneChangedReceiver r0) {
            r0.lambda$onReceive$0();
        }

        private TimeZoneChangedReceiver(LocaleController r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TimeZoneChangedReceiver(LocaleController r1, LocaleControllerIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onReceive$0() {
            if (this.this$0.getFormatterDayMonth().getTimeZone().equals(TimeZone.getDefault()) == true) goto L6;
            LocaleController.getInstance().recreateFormatters();
            return;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            ApplicationLoader.applicationHandler.post(new LocaleController$TimeZoneChangedReceiver$$ExternalSyntheticLambda0(this));
        }
    }

    public static /* synthetic */ void $r8$lambda$64YSNlPhGyutsumbVkHOMUhUarM(LocaleController r0, String r1, Runnable r2) {
        r0.lambda$checkForcePatchLangpack$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6hMdYnQRhjZInbgcdpkngvUvqgI(LocaleController r0, LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        r0.lambda$applyRemoteLanguage$18(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$FCunhbozLbGYmln_ERn0NZAd-Zo, reason: not valid java name */
    public static /* synthetic */ void m4235$r8$lambda$FCunhbozLbGYmln_ERn0NZAdZo(LocaleController r0, boolean r1, int r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadRemoteLanguages$12(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$FYgTUeWZxo8fXiPIx-r-f6Cr7OU, reason: not valid java name */
    public static /* synthetic */ void m4236$r8$lambda$FYgTUeWZxo8fXiPIxrf6Cr7OU(LocaleController r0, LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        r0.lambda$applyRemoteLanguage$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$J_UbSrw8XmgcXCHrbrK9yiUwLZQ(LocaleController r0, String r1, Runnable r2) {
        r0.lambda$checkForcePatchLangpack$5(r1, r2);
    }

    /* renamed from: $r8$lambda$M8xnqQcNw5oaI4ZxKjhICarw5-Y, reason: not valid java name */
    public static /* synthetic */ void m4237$r8$lambda$M8xnqQcNw5oaI4ZxKjhICarw5Y(int[] r2, int[] r3, Runnable r4) {
        int r1 = r2[0] + 1;
        r2[0] = r1;
        if (r1 < r3[0]) goto L7;
        if (r4 == null) goto L8;
        r4.run();
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$Pg2PBPxRCoepG2Tkpuyt86V0Onw(LocaleController r0, int r1, LocaleInfo r2, TLRPC.TL_langPackDifference r3, HashMap r4, Runnable r5) {
        r0.lambda$saveRemoteLocaleStrings$10(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$Wk6G2PmqFWLvh5jGtMb-PM3Tym4, reason: not valid java name */
    public static /* synthetic */ void m4238$r8$lambda$Wk6G2PmqFWLvh5jGtMbPM3Tym4(LocaleController r0, LocaleInfo r1, int r2, Runnable r3) {
        r0.lambda$applyLanguage$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$_HFbr_OEe1ylOUlzL1-LC1rmtR0, reason: not valid java name */
    public static /* synthetic */ void m4239$r8$lambda$_HFbr_OEe1ylOUlzL1LC1rmtR0(LocaleController r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$_hLr1MsuU8zyuxAj2dvolS1K9dQ(LocaleController r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$cD3L_IgX18PUWqtmb98mhjvbtAQ() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$eTGQJpznHRuluv4gsS5mcdnSBYM(LocaleController r0, LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        r0.lambda$applyRemoteLanguage$20(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$jVzPUvXqiLacCBr0XWIcEBv2d34(LocaleController r0, LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyRemoteLanguage$19(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$lVUOg9b39EGnADHk3Jz7OxG-3Ro, reason: not valid java name */
    public static /* synthetic */ void m4240$r8$lambda$lVUOg9b39EGnADHk3Jz7OxG3Ro(LocaleController r0, TLObject r1, boolean r2, int r3) {
        r0.lambda$loadRemoteLanguages$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$n3VAxDd6aRdYQtckzKlSSJEOjEQ(LocaleController r0, int r1) {
        r0.lambda$checkUpdateForCurrentRemoteLocale$2(r1);
    }

    /* renamed from: $r8$lambda$nNsKsounVhyXZT07xjmrz5-wGDk, reason: not valid java name */
    public static /* synthetic */ void m4241$r8$lambda$nNsKsounVhyXZT07xjmrz5wGDk(LocaleController r0, LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyRemoteLanguage$21(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$oOyMjA_1y7AiE8hg6hTg3YhZziI(LocaleController r0, int r1) {
        r0.lambda$checkPatchLangpack$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$q6DTnGREibH6summfBCYGWjo1H8(LocaleController r0, int r1) {
        r0.lambda$checkUpdateForCurrentRemoteLocale$3(r1);
    }

    /* renamed from: $r8$lambda$qS9PDfpRGfu9Onc5hg3kdW-CEQ8, reason: not valid java name */
    public static /* synthetic */ void m4242$r8$lambda$qS9PDfpRGfu9Onc5hg3kdWCEQ8(LocaleController r0, int r1) {
        r0.lambda$applyLanguage$8(r1);
    }

    /* renamed from: $r8$lambda$qwLv0TnG7vuDQxmix6g-lhuh1QI, reason: not valid java name */
    public static /* synthetic */ void m4243$r8$lambda$qwLv0TnG7vuDQxmix6glhuh1QI(LocaleController r0, LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyRemoteLanguage$15(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$t45j5nZcXoa7lSmpb3Rb3-jT6q4, reason: not valid java name */
    public static /* synthetic */ void m4244$r8$lambda$t45j5nZcXoa7lSmpb3Rb3jT6q4(LocaleController r0, LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyRemoteLanguage$17(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$yLbW1x63OIEqPblwq1OpGOXWzIw(LocaleController r0, LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        r0.lambda$applyRemoteLanguage$14(r1, r2, r3, r4);
    }

    static {
        HashMap<String, String> r0 = new HashMap();
        LEGACY_LANGUAGE_ALIASES = r0;
        r0.put("in", "id");
        r0.put("id", "in");
        r0.put("iw", "he");
        r0.put("he", "iw");
        r0.put("ji", "yi");
        r0.put("yi", "ji");
        defaultNumbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        otherNumbers = new char[][]{new char[]{1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641}, new char[]{1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785}, new char[]{2406, 2407, 2408, 2409, 2410, 2411, 2412, 2413, 2414, 2415}, new char[]{2790, 2791, 2792, 2793, 2794, 2795, 2796, 2797, 2798, 2799}, new char[]{2662, 2663, 2664, 2665, 2666, 2667, 2668, 2669, 2670, 2671}, new char[]{2534, 2535, 2536, 2537, 2538, 2539, 2540, 2541, 2542, 2543}, new char[]{3302, 3303, 3304, 3305, 3306, 3307, 3308, 3309, 3310, 3311}, new char[]{2918, 2919, 2920, 2921, 2922, 2923, 2924, 2925, 2926, 2927}, new char[]{3430, 3431, 3432, 3433, 3434, 3435, 3436, 3437, 3438, 3439}, new char[]{3046, 3047, 3048, 3049, 3050, 3051, 3052, 3053, 3054, 3055}, new char[]{3174, 3175, 3176, 3177, 3178, 3179, 3180, 3181, 3182, 3183}, new char[]{4160, 4161, 4162, 4163, 4164, 4165, 4166, 4167, 4168, 4169}, new char[]{3872, 3873, 3874, 3875, 3876, 3877, 3878, 3879, 3880, 3881}, new char[]{6160, 6161, 6162, 6163, 6164, 6165, 6166, 6167, 6168, 6169}, new char[]{6112, 6113, 6114, 6115, 6116, 6117, 6118, 6119, 6120, 6121}, new char[]{3664, 3665, 3666, 3667, 3668, 3669, 3670, 3671, 3672, 3673}, new char[]{3792, 3793, 3794, 3795, 3796, 3797, 3798, 3799, 3800, 3801}, new char[]{43472, 43473, 43474, 43475, 43476, 43477, 43478, 43479, 43480, 43481}};
    }

    public LocaleController() {
        this.formatterScheduleSend = new FastDateFormat[18];
        this.allRules = new HashMap();
        boolean r0 = false;
        this.changingConfiguration = false;
        this.languages = new ArrayList();
        this.unofficialLanguages = new ArrayList();
        this.remoteLanguages = new ArrayList();
        this.remoteLanguagesDict = new HashMap();
        this.languagesDict = new HashMap();
        this.otherLanguages = new ArrayList();
        this.patching = false;
        Localization r2 = Localization.EMPTY;
        this.localizationInternal = r2;
        this.localizationExternal = r2;
        addRules(new String[]{"bem", "brx", "da", "de", "el", "en", "eo", "es", "et", "fi", "fo", ImageLoader.AUTOPLAY_FILTER_NONLOOP, "he", "iw", "it", "nb", "nl", "nn", "no", "sv", "af", "bg", "bn", "ca", "eu", "fur", "fy", "gu", "ha", "is", "ku", "lb", "ml", "mr", "nah", "ne", "om", PluginsConstants.HookFilterTypes.OR, "pa", "pap", "ps", "so", "sq", "sw", "ta", "te", "tk", "ur", "zu", "mn", "gsw", "chr", "rm", "pt", "an", "ast"}, new PluralRules_One());
        addRules(new String[]{"cs", "sk"}, new PluralRules_Czech());
        addRules(new String[]{"ff", "fr", "kab"}, new PluralRules_French());
        addRules(new String[]{"ru", "uk", "be"}, new PluralRules_Balkan());
        addRules(new String[]{"sr", "hr", "bs", "sh"}, new PluralRules_Serbian());
        addRules(new String[]{"lv"}, new PluralRules_Latvian());
        addRules(new String[]{"lt"}, new PluralRules_Lithuanian());
        addRules(new String[]{"pl"}, new PluralRules_Polish());
        addRules(new String[]{"ro", "mo"}, new PluralRules_Romanian());
        addRules(new String[]{"sl"}, new PluralRules_Slovenian());
        addRules(new String[]{"ar"}, new PluralRules_Arabic());
        addRules(new String[]{"mk"}, new PluralRules_Macedonian());
        addRules(new String[]{"cy"}, new PluralRules_Welsh());
        addRules(new String[]{"br"}, new PluralRules_Breton());
        addRules(new String[]{"lag"}, new PluralRules_Langi());
        addRules(new String[]{"shi"}, new PluralRules_Tachelhit());
        addRules(new String[]{"mt"}, new PluralRules_Maltese());
        addRules(new String[]{"ga", "se", "sma", "smi", "smj", "smn", "sms"}, new PluralRules_Two());
        addRules(new String[]{"ak", "am", "bh", "fil", "tl", "guw", "hi", "ln", "mg", "nso", "ti", "wa"}, new PluralRules_Zero());
        addRules(new String[]{"az", "bm", "fa", "ig", "hu", "ja", "kde", "kea", "ko", "my", "ses", "sg", "to", "tr", "vi", "wo", "yo", "zh", "bo", "dz", "id", "jv", "jw", "ka", "km", "kn", "ms", "th", "in"}, new PluralRules_None());
        LocaleInfo r5 = new LocaleInfo();
        r5.name = "English";
        r5.nameEnglish = "English";
        r5.pluralLangCode = "en";
        r5.shortName = "en";
        r5.pathToFile = null;
        r5.builtIn = true;
        this.languages.add(r5);
        this.languagesDict.put(r5.shortName, r5);
        LocaleInfo r52 = new LocaleInfo();
        r52.name = "Italiano";
        r52.nameEnglish = "Italian";
        r52.pluralLangCode = "it";
        r52.shortName = "it";
        r52.pathToFile = null;
        r52.builtIn = true;
        this.languages.add(r52);
        this.languagesDict.put(r52.shortName, r52);
        LocaleInfo r53 = new LocaleInfo();
        r53.name = "Español";
        r53.nameEnglish = "Spanish";
        r53.pluralLangCode = "es";
        r53.shortName = "es";
        r53.builtIn = true;
        this.languages.add(r53);
        this.languagesDict.put(r53.shortName, r53);
        LocaleInfo r54 = new LocaleInfo();
        r54.name = "Deutsch";
        r54.nameEnglish = "German";
        r54.pluralLangCode = "de";
        r54.shortName = "de";
        r54.pathToFile = null;
        r54.builtIn = true;
        this.languages.add(r54);
        this.languagesDict.put(r54.shortName, r54);
        LocaleInfo r55 = new LocaleInfo();
        r55.name = "Nederlands";
        r55.nameEnglish = "Dutch";
        r55.pluralLangCode = "nl";
        r55.shortName = "nl";
        r55.pathToFile = null;
        r55.builtIn = true;
        this.languages.add(r55);
        this.languagesDict.put(r55.shortName, r55);
        LocaleInfo r56 = new LocaleInfo();
        r56.name = "العربية";
        r56.nameEnglish = "Arabic";
        r56.pluralLangCode = "ar";
        r56.shortName = "ar";
        r56.pathToFile = null;
        r56.builtIn = true;
        r56.isRtl = true;
        this.languages.add(r56);
        this.languagesDict.put(r56.shortName, r56);
        LocaleInfo r22 = new LocaleInfo();
        r22.name = "Português (Brasil)";
        r22.nameEnglish = "Portuguese (Brazil)";
        r22.pluralLangCode = "pt_br";
        r22.shortName = "pt_br";
        r22.pathToFile = null;
        r22.builtIn = true;
        this.languages.add(r22);
        this.languagesDict.put(r22.shortName, r22);
        LocaleInfo r23 = new LocaleInfo();
        r23.name = "한국어";
        r23.nameEnglish = "Korean";
        r23.pluralLangCode = "ko";
        r23.shortName = "ko";
        r23.pathToFile = null;
        r23.builtIn = true;
        this.languages.add(r23);
        this.languagesDict.put(r23.shortName, r23);
        LocaleInfo r24 = new LocaleInfo();
        r24.name = "Українська";
        r24.nameEnglish = "Ukrainian";
        r24.pluralLangCode = "uk";
        r24.shortName = "uk";
        r24.pathToFile = null;
        r24.builtIn = true;
        this.languages.add(r24);
        this.languagesDict.put(r24.shortName, r24);
        LocaleInfo r25 = new LocaleInfo();
        r25.name = "Русский";
        r25.nameEnglish = "Russian";
        r25.pluralLangCode = "ru";
        r25.shortName = "ru";
        r25.pathToFile = null;
        r25.builtIn = true;
        this.languages.add(r25);
        this.languagesDict.put(r25.shortName, r25);
        LocaleInfo r26 = new LocaleInfo();
        r26.name = "日本語";
        r26.nameEnglish = "Japanese";
        r26.shortName = "ja_raw";
        r26.baseLangCode = null;
        r26.isRtl = false;
        r26.pathToFile = "unofficial";
        r26.pluralLangCode = "ja";
        r26.builtIn = true;
        this.languages.add(r26);
        this.languagesDict.put(r26.getKey(), r26);
        this.languagesDict.put("ja", r26);
        loadOtherLanguages();
        if (this.remoteLanguages.isEmpty() == false) goto L5;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda5(this));
    L5:
        int r27 = 0;
    L7:
        if (r27 >= this.otherLanguages.size()) goto L9;
        LocaleInfo r3 = this.otherLanguages.get(r27);
        this.languages.add(r3);
        this.languagesDict.put(r3.getKey(), r3);
        r27 = r27 + 1;
        goto L7
    L9:
        int r28 = 0;
    L11:
        if (r28 >= this.remoteLanguages.size()) goto L17;
        LocaleInfo r32 = this.remoteLanguages.get(r28);
        LocaleInfo r4 = getLanguageFromDict(r32.getKey());
        if (r4 == null) goto L15;
        r4.pathToFile = r32.pathToFile;
        r4.version = r32.version;
        r4.baseVersion = r32.baseVersion;
        r4.serverIndex = r32.serverIndex;
        this.remoteLanguages.set(r28, r4);
    L16:
        r28 = r28 + 1;
        goto L11
    L15:
        this.languages.add(r32);
        this.languagesDict.put(r32.getKey(), r32);
        goto L16
    L17:
        int r29 = 0;
    L19:
        if (r29 >= this.unofficialLanguages.size()) goto L25;
        LocaleInfo r33 = this.unofficialLanguages.get(r29);
        LocaleInfo r42 = getLanguageFromDict(r33.getKey());
        if (r42 == null) goto L23;
        r42.pathToFile = r33.pathToFile;
        r42.version = r33.version;
        r42.baseVersion = r33.baseVersion;
        r42.serverIndex = r33.serverIndex;
        this.unofficialLanguages.set(r29, r42);
    L24:
        r29 = r29 + 1;
        goto L19
    L23:
        this.languagesDict.put(r33.getKey(), r33);
        goto L24
    L25:
        this.systemDefaultLocale = Locale.getDefault();
        is24HourFormat = DateFormat.is24HourFormat(ApplicationLoader.applicationContext);
        String r210 = MessagesController.getGlobalMainSettings().getString("language", null);     // Catch: Exception -> L31
        if (r210 == null) goto L33;
        LocaleInfo r211 = getLanguageFromDict(r210);     // Catch: Exception -> L31
        if (r211 == null) goto L34;
        r0 = true;
    L34:
        if (r211 == null) goto L36;
    L38:
        if (r211 != null) goto L42;
        r211 = getLanguageFromDict(getLocaleString(this.systemDefaultLocale));     // Catch: Exception -> L31
        if (r211 != null) goto L42;
        r211 = getLanguageFromDict("en");     // Catch: Exception -> L31
    L42:
        applyLanguage(r211, r0, true, UserConfig.selectedAccount);     // Catch: Exception -> L31
    L57:
        IntentFilter r02 = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");     // Catch: Exception -> L48
        if (Build.VERSION.SDK_INT < 33) goto L50;
        ApplicationLoader.applicationContext.registerReceiver(new TimeZoneChangedReceiver(this, null), r02, 4);     // Catch: Exception -> L48
    L53:
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda6(this));
        return;
    L50:
        ApplicationLoader.applicationContext.registerReceiver(new TimeZoneChangedReceiver(this, null), r02);     // Catch: Exception -> L48
    L48:
        e = move-exception;
        FileLog.e(e);
        goto L53
    L36:
        if (this.systemDefaultLocale.getLanguage() == null) goto L38;
        r211 = getLanguageFromDict(this.systemDefaultLocale.getLanguage());     // Catch: Exception -> L31
        goto L38
    L33:
        r211 = null;
    L31:
        e = move-exception;
        FileLog.e(e);
        goto L57
    }

    private void addExteraLocaleAssetPaths(LinkedHashSet<String> r5, String r6) {
        if (TextUtils.isEmpty(r6) == true) goto L22;
        String[] r4 = r6.replace(SignatureVisitor.SUPER, '_').split("_");
        if (r4.length != 0) goto L8;
        return;
    L8:
        if (TextUtils.isEmpty(r4[0]) == true) goto L23;
        String r62 = LocaleUtils.normalizeResourceLanguage(r4[0]);
        if (TextUtils.isEmpty(r62) == true) goto L24;
        r5.add("extera_locales/values-" + r62 + "/extera.xml");
        if (r4.length > 1) goto L16;
        return;
    L16:
        if (TextUtils.isEmpty(r4[1]) == true) goto L21;
        r5.add("extera_locales/values-" + r62 + "-r" + LocaleUtils.normalizeResourceRegion(r62, r4[1]) + "/extera.xml");
        return;
    L21:
        return;
    L24:
        return;
    L23:
        return;
    }

    private void addExteraLocaleAssetPaths(LinkedHashSet<String> r3, Locale r4) {
        if (r4 == null) goto L9;
        addExteraLocaleAssetPaths(r3, r4.getLanguage());
        if (TextUtils.isEmpty(r4.getCountry()) == true) goto L8;
        addExteraLocaleAssetPaths(r3, r4.getLanguage() + "_" + r4.getCountry());
        return;
    L8:
        return;
    }

    public static String addNbsp(String r2) {
        return r2.replace(' ', Typography.nbsp);
    }

    private void addRules(String[] r5, PluralRules r6) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        String r2 = r5[r1];
        this.allRules.put(r2, r6);
        r1 = r1 + 1;
        goto L3
    }

    private static void appendPmCompactToken(StringBuilder r4, char r5, Calendar r6, Locale r7) {
        if (r4.length() <= 0) goto L8;
        if (r4.charAt(r4.length() - 1) == '.') goto L8;
        r4.append('.');
    L8:
        if (r5 != 'd') goto L12;
        r4.append(String.format(r7, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r6.get(5))}));
        return;
    L12:
        if (r5 != 'M') goto L16;
        r4.append(String.format(r7, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r6.get(2) + 1)}));
        return;
    L16:
        if (r5 != 'y') goto L19;
        r4.append(String.format(r7, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r6.get(1) % 100)}));
        return;
    }

    private int applyRemoteLanguage(LocaleInfo r9, String r10, boolean r11, int r12, Runnable r13) {
        if (r9 != null) goto L5;
    L40:
        return 0;
    L5:
        if (r9.isRemote() == false) goto L7;
    L9:
        FileLog.d("applyRemoteLanguage " + r10 + " force=" + r11 + " currentAccount=" + r12);
        int[] r2 = {0};
        LocaleController$$ExternalSyntheticLambda0 r4 = new LocaleController$$ExternalSyntheticLambda0(new int[]{0}, r2, r13);
        if (r11 == false) goto L13;
        patched(r9.shortName);
    L13:
        if (r9.hasBaseLang() == false) goto L24;
        if (r10 == null) goto L18;
        if (r10.equals(r9.baseLangCode) == false) goto L24;
    L18:
        if (r9.baseVersion == 0) goto L23;
        if (r11 == true) goto L23;
        if (r9.hasBaseLang() == false) goto L24;
        FileLog.d("applyRemoteLanguage getDifference of base");
        TLRPC.TL_langpack_getDifference r132 = new TLRPC.TL_langpack_getDifference();
        r132.from_version = r9.baseVersion;
        r132.lang_code = r9.getBaseLangCode();
        r132.lang_pack = _UrlKt.FRAGMENT_ENCODE_SET;
        r2[0] = r2[0] + 1;
        ConnectionsManager.getInstance(r12).sendRequest(r132, new LocaleController$$ExternalSyntheticLambda1(this, r9, r12, r4), 8);
    L23:
        FileLog.d("applyRemoteLanguage getLangPack of base");
        TLRPC.TL_langpack_getLangPack r133 = new TLRPC.TL_langpack_getLangPack();
        r133.lang_code = r9.getBaseLangCode();
        r2[0] = r2[0] + 1;
        ConnectionsManager.getInstance(r12).sendRequest(r133, new LocaleController$$ExternalSyntheticLambda2(this, r9, r12, r4), 8);
    L24:
        if (r10 == null) goto L30;
        if (r10.equals(r9.shortName) == true) goto L30;
        return 0;
    L30:
        if (r9.version == 0) goto L34;
        if (r11 == true) goto L34;
        FileLog.d("applyRemoteLanguage getDifference");
        TLRPC.TL_langpack_getDifference r102 = new TLRPC.TL_langpack_getDifference();
        r102.from_version = r9.version;
        r102.lang_code = r9.getLangCode();
        r102.lang_pack = _UrlKt.FRAGMENT_ENCODE_SET;
        r2[0] = r2[0] + 1;
        return ConnectionsManager.getInstance(r12).sendRequest(r102, new LocaleController$$ExternalSyntheticLambda3(this, r9, r12, r4), 8);
    L34:
        int r103 = 0;
    L36:
        if (r103 >= 16) goto L38;
        ConnectionsManager.setLangCode(r9.getLangCode());
        r103 = r103 + 1;
        goto L36
    L38:
        FileLog.d("applyRemoteLanguage getLangPack");
        TLRPC.TL_langpack_getLangPack r104 = new TLRPC.TL_langpack_getLangPack();
        r104.lang_code = r9.getLangCode();
        r2[0] = r2[0] + 1;
        return ConnectionsManager.getInstance(r12).sendRequest(r104, new LocaleController$$ExternalSyntheticLambda4(this, r9, r12, r4), 8);
    L7:
        if (r9.isUnofficial() == true) goto L9;
        goto L9
    }

    public static CharSequence bold(CharSequence r5) {
        if ((r5 instanceof Spannable) == false) goto L6;
        ((Spannable) r5).setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r5.length(), 33);
        return r5;
    L6:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r5);
        r0.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r5.length(), 33);
        return r0;
    }

    private void checkLocalizationInternal() {
        Locale r0 = this.currentLocale;
        if (Objects.equals(this.localizationInternalLastLocale, r0) == true) goto L5;
    L10:
        this.localizationInternalPending = true;
        monitor-enter(this);
        Locale r02 = this.currentLocale;     // Catch: Throwable -> L17
        if (Objects.equals(this.localizationInternalLastLocale, r02) == false) goto L20;
        if (this.localizationInternal == null) goto L20;
    L32:
        monitor-exit(this);     // Catch: Throwable -> L17
        this.localizationInternalPending = false;
        return;
    L20:
        if (this.localizationInternalDefault != null) goto L22;
        this.localizationInternalDefault = new Localization.Builder().addResLocalization(ApplicationLoader.applicationContext, "localization_en.bin").build();     // Catch: Throwable -> L17
    L22:
        String r1 = LocalizationUtils.getLocalizationAsset(r02);     // Catch: Throwable -> L17
        if (r1 != null) goto L25;
        r1 = getLocalizationAssetByResourceQualifier(this.currentLocaleInfo);     // Catch: Throwable -> L17
    L25:
        if (r1 != null) goto L27;
    L30:
        this.localizationInternal = this.localizationInternalDefault;     // Catch: Throwable -> L17
    L31:
        this.localizationInternalLastLocale = r02;     // Catch: Throwable -> L17
        goto L32
    L27:
        if (TextUtils.equals(r1, "localization_en.bin") == true) goto L30;
        this.localizationInternal = new Localization.Builder().addLocalization(this.localizationInternalDefault).addResLocalization(ApplicationLoader.applicationContext, r1).build();     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        throw th;
    L5:
        if (this.localizationInternal == null) goto L10;
        if (this.localizationInternalPending == true) goto L10;
    }

    private FastDateFormat createFormatter(Locale r1, String r2, String r3) {
        if (r2 != null) goto L4;
    L5:
        r2 = r3;
    L10:
        return FastDateFormat.getInstance(r2, r1);
    L9:
        return FastDateFormat.getInstance(r3, r1);
    L4:
        if (r2.length() != 0) goto L10;
        goto L5
    }

    public static void ensureImperialSystemInit() {
        if (useImperialSystemType != null) goto L33;
        int r0 = SharedConfig.distanceSystemType;
        boolean r1 = true;
        if (r0 != 0) goto L25;
        TelephonyManager r02 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L21
        if (r02 == null) goto L32;
        String r03 = r02.getSimCountryIso().toUpperCase();     // Catch: Exception -> L21
        if ("US".equals(r03) == false) goto L12;
    L19:
        useImperialSystemType = Boolean.valueOf(r1);     // Catch: Exception -> L21
        return;
    L12:
        if ("GB".equals(r03) == true) goto L19;
        if ("MM".equals(r03) == true) goto L19;
        if ("LR".equals(r03) == true) goto L19;
        r1 = false;
        goto L19
    L32:
        return;
    L21:
        e = move-exception;
        useImperialSystemType = Boolean.FALSE;
        FileLog.e(e);
        return;
    L25:
        if (r0 == 2) goto L28;
        r1 = false;
    L28:
        useImperialSystemType = Boolean.valueOf(r1);
        return;
    }

    private String escapeString(String r2) {
        if (r2.contains("[CDATA") == false) goto L6;
        return r2;
    L6:
        return r2.replace("<", "&lt;").replace(">", "&gt;").replace("& ", "&amp; ");
    }

    public static String fixNumbers(CharSequence r8) {
        StringBuilder r0 = new StringBuilder(r8);
        int r82 = r0.length();
        int r2 = 0;
    L3:
        if (r2 >= r82) goto L26;
        char r3 = r0.charAt(r2);
        if (r3 < '0') goto L9;
        if (r3 > '9') goto L9;
    L24:
        r2 = r2 + 1;
    L9:
        if (r3 == '.') goto L24;
        if (r3 == ',') goto L24;
        int r4 = 0;
    L15:
        if (r4 >= otherNumbers.length) goto L24;
        int r5 = 0;
    L17:
        char[] r6 = otherNumbers[r4];
        if (r5 >= r6.length) goto L23;
        if (r3 == r6[r5]) goto L21;
        r5 = r5 + 1;
        goto L17
    L21:
        r0.setCharAt(r2, defaultNumbers[r5]);
        r4 = otherNumbers.length;
    L23:
        r4 = r4 + 1;
        goto L15
    L26:
        return r0.toString();
    }

    public static String formatCallDuration(int r7) {
        if (r7 <= 3600) goto L9;
        String r4 = formatPluralString("Hours", r7 / 3600, new Object[0]);
        int r72 = (r7 % 3600) / 60;
        if (r72 > 0) goto L7;
        return r4;
    L7:
        return r4 + ", " + formatPluralString("Minutes", r72, new Object[0]);
    L9:
        if (r7 <= 60) goto L13;
        return formatPluralString("Minutes", r7 / 60, new Object[0]);
    L13:
        return formatPluralString("Seconds", r7, new Object[0]);
    }

    public static String formatDate(long r1) {
        return formatDate(r1, false);
    }

    public static String formatDate(long r5, boolean r7) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L22
        int r2 = r0.get(6);     // Catch: Exception -> L22
        int r4 = r0.get(1);     // Catch: Exception -> L22
        r0.setTimeInMillis(r52);     // Catch: Exception -> L22
        int r1 = r0.get(6);     // Catch: Exception -> L22
        int r02 = r0.get(1);     // Catch: Exception -> L22
        if (r1 != r2) goto L12;
        if (r4 != r02) goto L12;
        if (r7 == false) goto L10;
        return getString(R.string.ShortToday);
    L10:
        return getInstance().getFormatterDay().format(new Date(r52));
    L12:
        if ((r1 + 1) != r2) goto L17;
        if (r4 != r02) goto L17;
        return getString("Yesterday", R.string.Yesterday);
    L17:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L21;
        return getInstance().getFormatterDayMonth().format(new Date(r52));
    L21:
        return getInstance().getFormatterYear().format(new Date(r52));
    L22:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: formatDate";
    }

    public static String formatDateAudio(long r5, boolean r7) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L22
        int r2 = r0.get(6);     // Catch: Exception -> L22
        int r4 = r0.get(1);     // Catch: Exception -> L22
        r0.setTimeInMillis(r52);     // Catch: Exception -> L22
        int r1 = r0.get(6);     // Catch: Exception -> L22
        int r02 = r0.get(1);     // Catch: Exception -> L22
        if (r1 != r2) goto L12;
        if (r4 != r02) goto L12;
        if (r7 == false) goto L10;
        return formatString(R.string.TodayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L10:
        return formatString(R.string.TodayAtFormattedWithToday, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L12:
        if ((r1 + 1) != r2) goto L17;
        if (r4 != r02) goto L17;
        return formatString(R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L17:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L21;
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L21:
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L22:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatDateCallLog(long r5) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L19
        int r2 = r0.get(6);     // Catch: Exception -> L19
        int r4 = r0.get(1);     // Catch: Exception -> L19
        r0.setTimeInMillis(r52);     // Catch: Exception -> L19
        int r1 = r0.get(6);     // Catch: Exception -> L19
        int r02 = r0.get(1);     // Catch: Exception -> L19
        if (r1 != r2) goto L9;
        if (r4 != r02) goto L9;
        return getInstance().getFormatterDay().format(new Date(r52));
    L9:
        if ((r1 + 1) != r2) goto L14;
        if (r4 != r02) goto L14;
        return formatString(R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L14:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L18;
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getChatDateShort().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L18:
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getChatFullDate().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L19:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatDateChat(long r1) {
        return formatDateChat(r1, false);
    }

    public static String formatDateChat(long r6, boolean r8) {
        long r62 = r6 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L26
        r0.setTimeInMillis(r62);     // Catch: Exception -> L26
        Calendar r1 = Calendar.getInstance();     // Catch: Exception -> L26
        if (r1.get(1) == r0.get(1)) goto L6;
    L9:
        r1.add(6, -1);     // Catch: Exception -> L26
        if (r1.get(1) == r0.get(1)) goto L12;
    L15:
        int r12 = Calendar.getInstance().get(1);     // Catch: Exception -> L26
        if (r8 == true) goto L18;
    L19:
        if (r8 == true) goto L25;
        if (Math.abs(System.currentTimeMillis() - r62) >= 31536000000L) goto L25;
    L23:
        return getInstance().getChatDate().format(r62);
    L25:
        return getInstance().getChatFullDate().format(r62);
    L18:
        if (r12 == r0.get(1)) goto L23;
    L12:
        if (r1.get(6) != r0.get(6)) goto L15;
        return getString(R.string.SearchTipYesterday);
    L6:
        if (r1.get(6) != r0.get(6)) goto L9;
        return getString(R.string.SearchTipToday);
    L26:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: formatDateChat";
    }

    public static String formatDateForBan(long r3) {
        long r32 = r3 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L9
        int r2 = r0.get(1);     // Catch: Exception -> L9
        r0.setTimeInMillis(r32);     // Catch: Exception -> L9
        if (r2 != r0.get(1)) goto L8;
        return getInstance().getFormatterBannedUntilThisYear().format(new Date(r32));
    L8:
        return getInstance().getFormatterBannedUntil().format(new Date(r32));
    L9:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatDateOnline(long r1, boolean[] r3) {
        return formatDateOnline(r1, r3, null);
    }

    public static String formatDateOnline(long r8, boolean[] r10, boolean[] r11) {
        if (r11 != null) goto L44;
    L13:
        long r82 = r8 * 1000;     // Catch: Exception -> L41
        Calendar r112 = Calendar.getInstance();     // Catch: Exception -> L41
        int r1 = r112.get(6);     // Catch: Exception -> L41
        int r3 = r112.get(1);     // Catch: Exception -> L41
        int r5 = r112.get(11);     // Catch: Exception -> L41
        r112.setTimeInMillis(r82);     // Catch: Exception -> L41
        int r6 = r112.get(6);     // Catch: Exception -> L41
        int r7 = r112.get(1);     // Catch: Exception -> L41
        int r113 = r112.get(11);     // Catch: Exception -> L41
        if (r6 != r1) goto L19;
        if (r3 != r7) goto L19;
        return formatString(R.string.LastSeenFormatted, new Object[]{formatString("TodayAtFormatted", R.string.TodayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r82))})});
    L19:
        if ((r6 + 1) != r1) goto L36;
        if (r3 != r7) goto L36;
        if (r10 == null) goto L33;
        r10[0] = true;     // Catch: Exception -> L41
        if (r5 > 6) goto L31;
        if (r113 <= 18) goto L31;
        if (is24HourFormat == false) goto L31;
        return formatString(R.string.LastSeenFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r82))});
    L31:
        return formatString(R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r82))});
    L33:
        return formatString(R.string.LastSeenFormatted, new Object[]{formatString("YesterdayAtFormatted", R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r82))})});
    L36:
        if (Math.abs(System.currentTimeMillis() - r82) >= 31536000000L) goto L40;
        return formatString("LastSeenDateFormatted", R.string.LastSeenDateFormatted, new Object[]{formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r82)), getInstance().getFormatterDay().format(new Date(r82))})});
    L40:
        return formatString("LastSeenDateFormatted", R.string.LastSeenDateFormatted, new Object[]{formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r82)), getInstance().getFormatterDay().format(new Date(r82))})});
    L44:
    L41:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    L5:
        if (ExteraConfig.getRelativeLastSeen() == false) goto L13;
        long r2 = (System.currentTimeMillis() / 1000) - r8;     // Catch: Exception -> L41
        if (r2 >= 0) goto L10;
        r2 = 0;
    L10:
        if (r2 >= 86400) goto L13;
        return formatString(R.string.LastSeenDateFormatted, new Object[]{formatRelativeDate(r2)});
    }

    public static String formatDateTime(long r5, boolean r7) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L21
        int r2 = r0.get(6);     // Catch: Exception -> L21
        int r4 = r0.get(1);     // Catch: Exception -> L21
        r0.setTimeInMillis(r52);     // Catch: Exception -> L21
        int r1 = r0.get(6);     // Catch: Exception -> L21
        int r02 = r0.get(1);     // Catch: Exception -> L21
        if (r1 != r2) goto L10;
        if (r4 != r02) goto L10;
        if (r7 == false) goto L10;
        return formatString(R.string.TodayAtFormattedWithToday, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L10:
        if ((r1 + 1) != r2) goto L16;
        if (r4 != r02) goto L16;
        if (r7 == false) goto L16;
        return formatString(R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L16:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L20;
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getChatDate().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L20:
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getChatFullDate().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L21:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatDistance(float r1, int r2) {
        return formatDistance(r1, r2, null);
    }

    public static String formatDistance(float r7, int r8, Boolean r9) {
        ensureImperialSystemInit();
        if (r9 != null) goto L5;
    L6:
        if (r9 != null) goto L32;
        if (useImperialSystemType.booleanValue() == false) goto L32;
    L9:
        float r72 = r7 * 3.28084f;
        if (r72 >= 1000.0f) goto L20;
        if (r8 == 0) goto L18;
        if (r8 == 1) goto L16;
        return formatString("FootsShort", R.string.FootsShort, new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) Math.max(1.0f, r72))})});
    L16:
        return formatString("FootsFromYou", R.string.FootsFromYou, new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) Math.max(1.0f, r72))})});
    L18:
        return formatString("FootsAway", R.string.FootsAway, new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) Math.max(1.0f, r72))})});
    L20:
        if ((r72 % 5280.0f) != 0.0f) goto L22;
        String r73 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) (r72 / 5280.0f))});
    L23:
        if (r8 == 0) goto L30;
        if (r8 == 1) goto L28;
        return formatString("MilesShort", R.string.MilesShort, new Object[]{r73});
    L28:
        return formatString("MilesFromYou", R.string.MilesFromYou, new Object[]{r73});
    L30:
        return formatString("MilesAway", R.string.MilesAway, new Object[]{r73});
    L22:
        r73 = String.format("%.2f", new Object[]{Float.valueOf(r72 / 5280.0f)});
    L32:
        if (r7 >= 1000.0f) goto L42;
        if (r8 == 0) goto L40;
        if (r8 == 1) goto L38;
        return formatString("MetersShort", R.string.MetersShort, new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) Math.max(1.0f, r7))})});
    L38:
        return formatString("MetersFromYou2", R.string.MetersFromYou2, new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) Math.max(1.0f, r7))})});
    L40:
        return formatString("MetersAway2", R.string.MetersAway2, new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) Math.max(1.0f, r7))})});
    L42:
        if ((r7 % 1000.0f) != 0.0f) goto L44;
        String r74 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) (r7 / 1000.0f))});
    L45:
        if (r8 == 0) goto L52;
        if (r8 == 1) goto L50;
        return formatString("KMetersShort", R.string.KMetersShort, new Object[]{r74});
    L50:
        return formatString("KMetersFromYou2", R.string.KMetersFromYou2, new Object[]{r74});
    L52:
        return formatString("KMetersAway2", R.string.KMetersAway2, new Object[]{r74});
    L44:
        r74 = String.format("%.2f", new Object[]{Float.valueOf(r7 / 1000.0f)});
        goto L45
    L5:
        if (r9.booleanValue() == true) goto L9;
        goto L6
    }

    public static String formatDuration(int r7) {
        if (r7 <= 0) goto L5;
        int r2 = r7 / 3600;
        int r3 = (r7 / 60) % 60;
        int r72 = r7 % 60;
        StringBuilder r4 = new StringBuilder();
        if (r2 <= 0) goto L10;
        r4.append(formatPluralString("Hours", r2, new Object[0]));
    L10:
        if (r3 > 0) goto L12;
    L15:
        if (r72 <= 0) goto L21;
        if (r4.length() <= 0) goto L19;
        r4.append(' ');
    L19:
        r4.append(formatPluralString("Seconds", r72, new Object[0]));
    L21:
        return r4.toString();
    L12:
        if (r4.length() <= 0) goto L14;
        r4.append(' ');
    L14:
        r4.append(formatPluralString("Minutes", r3, new Object[0]));
        goto L15
    L5:
        return formatPluralString("Seconds", 0, new Object[0]);
    }

    public static String formatEntityFormattedDate(TLRPC.TL_messageEntityFormattedDate r4) {
        return formatEntityFormattedDate(r4, System.currentTimeMillis(), Locale.getDefault(), false);
    }

    private static String formatEntityFormattedDate(TLRPC.TL_messageEntityFormattedDate r4, long r5, Locale r7, boolean r8) {
        long r0 = r4.date * 1000;
        if (r4.relative == false) goto L8;
        if (r8 == true) goto L8;
        return formatEntityFormattedDateRelative(r0, r5, r7);
    L8:
        if (r4.flags != 0) goto L10;
        boolean r52 = true;
    L11:
        boolean r53 = r52 | r8;
        boolean r6 = r4.day_of_week;
        String r72 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r6 == false) goto L14;
        String r62 = getInstance().getFormatterWeekLong().format(r0);
    L15:
        if (r53 == false) goto L18;
        String r82 = getInstance().getFormatterGiveawayCard().format(r0);
    L24:
        if (r53 == false) goto L31;
        Calendar r42 = Calendar.getInstance();
        r42.setTimeInMillis(r0);
        if (r42.get(13) == 0) goto L29;
        String r43 = getInstance().getFormatterDayWithSeconds().format(r0);
    L28:
        r72 = r43;
    L36:
        boolean r44 = TextUtils.isEmpty(r62);
        boolean r54 = TextUtils.isEmpty(r82);
        boolean r02 = TextUtils.isEmpty(r72);
        if (r54 == false) goto L40;
        if (r02 == false) goto L40;
        return r62;
    L40:
        if (r54 == true) goto L43;
        if (r02 == true) goto L43;
        String r55 = formatString(R.string.formatDateAtTime, new Object[]{r82, r72});
    L47:
        if (r44 == false) goto L49;
        return r55;
    L49:
        return formatString(R.string.RelativeDateFormatterWeek2, new Object[]{r62, r55});
    L43:
        if (r54 == false) goto L46;
        r82 = r72;
    L46:
        r55 = r82;
        goto L47
    L29:
        r43 = getInstance().getFormatterDay().format(r0);
        goto L28
    L31:
        if (r4.long_time == false) goto L34;
        r72 = getInstance().getFormatterDayWithSeconds().format(r0);
        goto L36
    L34:
        if (r4.short_time == false) goto L36;
        r72 = getInstance().getFormatterDay().format(r0);
        goto L36
    L18:
        if (r4.long_date == false) goto L21;
        r82 = getInstance().getChatFullDate().format(r0);
        goto L24
    L21:
        if (r4.short_date == false) goto L23;
        r82 = getInstance().getFormatterYear().format(r0);
        goto L24
    L23:
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L24
    L14:
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L15
    L10:
        r52 = false;
        goto L11
    }

    public static String formatEntityFormattedDate(TLRPC.TL_messageEntityFormattedDate r3, boolean r4) {
        return formatEntityFormattedDate(r3, System.currentTimeMillis(), Locale.getDefault(), r4);
    }

    private static String formatEntityFormattedDateRelative(long r0, long r2, Locale r4) {
        return RelativeIcu.format(r0 - r2, r4);
    }

    public static String formatGroupMemberJoined(long r4) {
        long r42 = r4 * 1000;
    L9:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    L4:
        if (Math.abs(System.currentTimeMillis() - r42) >= 31536000000L) goto L6;
        String r43 = formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r42)), getInstance().getFormatterDay().format(new Date(r42))});     // Catch: Exception -> L9
    L7:
        return formatString(R.string.GroupMemberJoined, new Object[]{r43});
    L6:
        r43 = formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r42)), getInstance().getFormatterDay().format(new Date(r42))});     // Catch: Exception -> L9
        goto L7
    }

    public static String formatImportedDate(long r2) {
        Date r0 = new Date(r2 * 1000);     // Catch: Exception -> L5
        return String.format("%1$s, %2$s", new Object[]{getInstance().getFormatterYear().format(r0), getInstance().getFormatterDay().format(r0)});
    L5:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatJoined(long r4) {
        long r42 = r4 * 1000;
    L9:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    L4:
        if (Math.abs(System.currentTimeMillis() - r42) >= 31536000000L) goto L6;
        String r43 = formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r42)), getInstance().getFormatterDay().format(new Date(r42))});     // Catch: Exception -> L9
    L7:
        return formatString(R.string.ChannelOtherSubscriberJoined, new Object[]{r43});
    L6:
        r43 = formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r42)), getInstance().getFormatterDay().format(new Date(r42))});     // Catch: Exception -> L9
        goto L7
    }

    public static String formatLocationLeftTime(int r5) {
        int r0 = (r5 / 60) / 60;
        int r52 = r5 - (r0 * 3600);
        int r1 = r52 / 60;
        int r53 = r52 - (r1 * 60);
        int r2 = 0;
        if (r0 == 0) goto L9;
        if (r1 <= 30) goto L7;
        r2 = 1;
    L7:
        return String.format("%dh", new Object[]{Integer.valueOf(r0 + r2)});
    L9:
        if (r1 == 0) goto L15;
        if (r53 <= 30) goto L13;
        r2 = 1;
    L13:
        return String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r1 + r2)});
    L15:
        return String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r53)});
    }

    public static String formatLocationUpdateDate(long r7) {
        long r72 = r7 * 1000;
        Calendar r2 = Calendar.getInstance();     // Catch: Exception -> L26
        int r4 = r2.get(6);     // Catch: Exception -> L26
        int r6 = r2.get(1);     // Catch: Exception -> L26
        r2.setTimeInMillis(r72);     // Catch: Exception -> L26
        int r3 = r2.get(6);     // Catch: Exception -> L26
        int r22 = r2.get(1);     // Catch: Exception -> L26
        if (r3 != r4) goto L16;
        if (r6 != r22) goto L16;
        int r0 = ((int) (ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime() - (r72 / 1000))) / 60;     // Catch: Exception -> L26
        if (r0 < 1) goto L9;
        if (r0 >= 60) goto L14;
        return formatPluralString("UpdatedMinutes", r0, new Object[0]);
    L14:
        return formatString(R.string.LocationUpdatedFormatted, new Object[]{formatString("TodayAtFormatted", R.string.TodayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r72))})});
    L9:
        return getString(R.string.LocationUpdatedJustNow);
    L16:
        if ((r3 + 1) != r4) goto L21;
        if (r6 != r22) goto L21;
        return formatString(R.string.LocationUpdatedFormatted, new Object[]{formatString("YesterdayAtFormatted", R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r72))})});
    L21:
        if (Math.abs(System.currentTimeMillis() - r72) >= 31536000000L) goto L25;
        return formatString(R.string.LocationUpdatedFormatted, new Object[]{formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r72)), getInstance().getFormatterDay().format(new Date(r72))})});
    L25:
        return formatString(R.string.LocationUpdatedFormatted, new Object[]{formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r72)), getInstance().getFormatterDay().format(new Date(r72))})});
    L26:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatNumber(long r2, char r4) {
        if (r2 < 0) goto L5;
        StringBuilder r0 = new StringBuilder(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Long.valueOf(r2)}));
        int r22 = r0.length() - 3;
    L7:
        if (r22 <= 0) goto L10;
        r0.insert(r22, r4);
        r22 = r22 - 3;
        goto L7
    L10:
        return r0.toString();
    L5:
        return "-" + formatNumber(-r2, r4);
    }

    public static String formatNumberWithMillion(long r2, char r4) {
        if (r2 >= 1000000) goto L7;
        return formatNumber(r2, r4);
    L7:
        return AndroidUtilities.formatWholeNumber((int) r2, DurationKt.NANOS_IN_MILLIS);
    }

    public static CharSequence formatPluralSpannable(String r4, int r5, CharSequence... r6) {
        if (r4 == null) goto L11;
        if (r4.length() == 0) goto L11;
        if (getInstance().currentPluralRules == null) goto L11;
        String r0 = r4 + "_" + getInstance().stringForQuantity(getInstance().currentPluralRules.quantityForNumber(r5));
        Object[] r1 = new Object[r6.length + 1];
        r1[0] = Integer.valueOf(r5);
        System.arraycopy(r6, 0, r1, 1, r6.length);
        return formatSpannable(r0, r4.concat("_other"), 0, r1);
    L11:
        return "LOC_ERR:" + r4;
    }

    public static String formatPluralString(String r4, int r5, Object... r6) {
        if (r4 == null) goto L11;
        if (r4.length() == 0) goto L11;
        if (getInstance().currentPluralRules == null) goto L11;
        String r0 = r4 + "_" + getInstance().stringForQuantity(getInstance().currentPluralRules.quantityForNumber(r5));
        Object[] r1 = new Object[r6.length + 1];
        r1[0] = Integer.valueOf(r5);
        System.arraycopy(r6, 0, r1, 1, r6.length);
        return formatString(r0, r4.concat("_other"), 0, r1);
    L11:
        return "LOC_ERR:" + r4;
    }

    public static String formatPluralStringComma(String r1, int r2) {
        return formatPluralStringComma(r1, r2, ',');
    }

    public static String formatPluralStringComma(String r1, int r2, char r3) {
        return formatPluralStringComma(r1, r2, r3, new Object[0]);
    }

    public static String formatPluralStringComma(String r5, int r6, char r7, Object... r8) {
        if (r5 == null) goto L46;
    L12:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: " + r5;
    L5:
        if (r5.length() == 0) goto L46;
        if (getInstance().currentPluralRules == null) goto L46;
        String r2 = r5 + "_" + getInstance().stringForQuantity(getInstance().currentPluralRules.quantityForNumber(r6));     // Catch: Exception -> L12
        StringBuilder r3 = new StringBuilder(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r6)}));     // Catch: Exception -> L12
        int r62 = r3.length() - 3;
    L10:
        if (r62 <= 0) goto L14;
        r3.insert(r62, r7);     // Catch: Exception -> L12
        r62 = r62 - 3;
        goto L10
    L14:
        String r72 = null;
        if (BuildVars.USE_CLOUD_STRINGS == false) goto L18;
        String r63 = getInstance().localizationExternal.getByResName(r2);     // Catch: Exception -> L12
    L20:
        if (r63 == null) goto L22;
    L25:
        if (r63 == null) goto L50;
    L27:
        if (r63 != null) goto L29;
        r63 = getInstance().getLocalizedString(r5 + "_other");     // Catch: Exception -> L12
    L29:
        String r64 = r63.replace(TimeModel.NUMBER_FORMAT, "%1$s").replace("%1$d", "%1$s");     // Catch: Exception -> L12
        int r73 = 0;
        if (r8 != null) goto L32;
        int r0 = 0;
    L33:
        int r02 = r0 + 1;     // Catch: Exception -> L12
        Object[] r1 = new Object[r02];     // Catch: Exception -> L12
    L34:
        if (r73 >= r02) goto L40;
        if (r73 != 0) goto L37;
        Object r22 = r3;
    L38:
        r1[r73] = r22;     // Catch: Exception -> L12
        r73 = r73 + 1;     // Catch: Exception -> L12
        goto L34
    L37:
        r22 = r8[r73 - 1];     // Catch: Exception -> L12
        goto L38
    L40:
        if (getInstance().currentLocale == null) goto L44;
        return String.format(getInstance().currentLocale, r64, r1);
    L44:
        return String.format(r64, r1);
    L32:
        r0 = r8.length;     // Catch: Exception -> L12
        goto L33
    L50:
        r63 = getInstance().getLocalizedString(r2);     // Catch: Exception -> L49
        goto L27
    L22:
        if (BuildVars.USE_CLOUD_STRINGS == false) goto L24;
        r72 = getInstance().localizationExternal.getByResName(r5 + "_other");     // Catch: Exception -> L12
    L24:
        r63 = r72;
        goto L25
    L18:
        r63 = null;
    L46:
        return "LOC_ERR:" + r5;
    }

    public static String formatPluralStringComma(String r1, int r2, Object... r3) {
        return formatPluralStringComma(r1, r2, ',', r3);
    }

    public static String formatPluralStringSpaced(String r1, int r2) {
        return formatPluralStringComma(r1, r2, ' ');
    }

    public static String formatPluralStringSpaced(String r1, int r2, Object... r3) {
        return formatPluralStringComma(r1, r2, ' ', r3);
    }

    private static String formatPmCompactDate(long r7, boolean r9) {
        long r72 = r7 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L31
        int r2 = r0.get(6);     // Catch: Exception -> L31
        int r4 = r0.get(1);     // Catch: Exception -> L31
        r0.setTimeInMillis(r72);     // Catch: Exception -> L31
        int r1 = r0.get(6);     // Catch: Exception -> L31
        int r5 = r0.get(1);     // Catch: Exception -> L31
        Date r6 = new Date(r72);     // Catch: Exception -> L31
        if (r1 != r2) goto L12;
        if (r4 != r5) goto L12;
        if (r9 == false) goto L8;
        int r73 = R.string.PmEditedTodayAt;     // Catch: Exception -> L31
    L10:
        return formatString(r73, new Object[]{getInstance().getFormatterDay().format(r6)});
    L8:
        r73 = R.string.PmFwdOriginalTodayAt;     // Catch: Exception -> L31
    L12:
        if ((r1 + 1) != r2) goto L20;
        if (r4 != r5) goto L20;
        if (r9 == false) goto L16;
        int r74 = R.string.PmEditedYesterdayAt;     // Catch: Exception -> L31
    L18:
        return formatString(r74, new Object[]{getInstance().getFormatterDay().format(r6)});
    L16:
        r74 = R.string.PmFwdOriginalYesterdayAt;     // Catch: Exception -> L31
    L20:
        if (Math.abs(System.currentTimeMillis() - r72) >= 31536000000L) goto L26;
        if (r9 == false) goto L23;
        int r75 = R.string.PmEditedDateTimeAt;     // Catch: Exception -> L31
    L25:
        return formatString(r75, new Object[]{formatPmCompactDayMonth(r0), getInstance().getFormatterDay().format(r6)});
    L23:
        r75 = R.string.PmFwdOriginalDateTimeAt;     // Catch: Exception -> L31
        goto L25
    L26:
        if (r9 == false) goto L28;
        int r76 = R.string.PmEditedDateTimeAt;     // Catch: Exception -> L31
    L30:
        return formatString(r76, new Object[]{formatPmCompactYear(r0), getInstance().getFormatterDay().format(r6)});
    L28:
        r76 = R.string.PmFwdOriginalDateTimeAt;     // Catch: Exception -> L31
    L31:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    private static String formatPmCompactDatePart(Calendar r8, boolean r9) {
        if (getInstance().currentLocale != null) goto L5;
        Locale r0 = Locale.getDefault();
    L6:
        if (r9 == false) goto L8;
        String r92 = "yMd";
    L9:
        String r93 = DateFormat.getBestDateTimePattern(r0, r92);
        StringBuilder r1 = new StringBuilder();
        StringBuilder r2 = new StringBuilder();
        int r4 = 0;
    L11:
        if (r4 >= r93.length()) goto L40;
        char r5 = r93.charAt(r4);
        if (r5 == 'd') goto L32;
        if (r5 == 'M') goto L32;
        if (r5 == 'y') goto L32;
        if (r2.length() <= 0) goto L23;
        appendPmCompactToken(r1, r2.charAt(0), r8, r0);
        r2.setLength(0);
    L23:
        if (r1.length() <= 0) goto L28;
        if (Character.isWhitespace(r5) == true) goto L28;
        r1.append('.');
    L38:
        r4 = r4 + 1;
    L28:
        if (r1.length() != 0) goto L38;
        if (r5 != '.') goto L38;
        r1.append(r5);
    L32:
        if (r2.length() != 0) goto L34;
    L37:
        r2.append(r5);
        goto L38
    L34:
        if (r2.charAt(0) == r5) goto L37;
        appendPmCompactToken(r1, r2.charAt(0), r8, r0);
        r2.setLength(0);
        r2.append(r5);
        goto L38
    L40:
        if (r2.length() <= 0) goto L43;
        appendPmCompactToken(r1, r2.charAt(0), r8, r0);
    L43:
        return r1.toString();
    L8:
        r92 = "Md";
        goto L9
    L5:
        r0 = getInstance().currentLocale;
        goto L6
    }

    private static String formatPmCompactDayMonth(Calendar r1) {
        return formatPmCompactDatePart(r1, false);
    }

    private static String formatPmCompactYear(Calendar r1) {
        return formatPmCompactDatePart(r1, true);
    }

    public static String formatPmEditedDate(long r1) {
        return formatPmCompactDate(r1, true);
    }

    public static String formatPmFwdDate(long r1) {
        return formatPmCompactDate(r1, false);
    }

    public static String formatPmSeenDate(long r5) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L19
        int r2 = r0.get(6);     // Catch: Exception -> L19
        int r4 = r0.get(1);     // Catch: Exception -> L19
        r0.setTimeInMillis(r52);     // Catch: Exception -> L19
        int r1 = r0.get(6);     // Catch: Exception -> L19
        int r02 = r0.get(1);     // Catch: Exception -> L19
        if (r1 != r2) goto L9;
        if (r4 != r02) goto L9;
        return formatString(R.string.PmReadTodayAt, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L9:
        if ((r1 + 1) != r2) goto L14;
        if (r4 != r02) goto L14;
        return formatString(R.string.PmReadYesterdayAt, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L14:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L18;
        return formatString(R.string.PmReadDateTimeAt, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L18:
        return formatString(R.string.PmReadDateTimeAt, new Object[]{getInstance().getFormatterYear().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L19:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatPmSentDate(long r5) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L19
        int r2 = r0.get(6);     // Catch: Exception -> L19
        int r4 = r0.get(1);     // Catch: Exception -> L19
        r0.setTimeInMillis(r52);     // Catch: Exception -> L19
        int r1 = r0.get(6);     // Catch: Exception -> L19
        int r02 = r0.get(1);     // Catch: Exception -> L19
        if (r1 != r2) goto L9;
        if (r4 != r02) goto L9;
        return formatString(R.string.PmSentTodayAt, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L9:
        if ((r1 + 1) != r2) goto L14;
        if (r4 != r02) goto L14;
        return formatString(R.string.PmSentYesterdayAt, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L14:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L18;
        return formatString(R.string.PmSentDateTimeAt, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L18:
        return formatString(R.string.PmSentDateTimeAt, new Object[]{getInstance().getFormatterYear().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L19:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatPollEndTime(int r2, boolean r3) {
        if (r2 >= 86400) goto L5;
        String r22 = formatShortDuration(r2);
    L6:
        if (r3 == false) goto L8;
        int r32 = R.string.PollResultsIn;
    L10:
        return formatString(r32, new Object[]{r22});
    L8:
        r32 = R.string.PollEndsIn;
        goto L10
    L5:
        r22 = formatPluralString("Days", r2 / 86400, new Object[0]);
        goto L6
    }

    public static String formatRelativeDate(long r11) {
        long r112 = r11 / 60;     // Catch: Exception -> L40
        long r0 = r112 / 60;     // Catch: Exception -> L40
        long r2 = r0 / 24;     // Catch: Exception -> L40
        long r4 = r2 / 30;     // Catch: Exception -> L40
        long r6 = r2 / 365;     // Catch: Exception -> L40
        if (r6 < 1) goto L11;
        if (r6 != 1) goto L9;
        return getString(R.string.YearAgo);
    L9:
        return formatPluralStringComma("YearsAgo", (int) r6);
    L11:
        if (r4 < 1) goto L18;
        if (r4 != 1) goto L16;
        return getString(R.string.MonthAgo);
    L16:
        return formatPluralStringComma("MonthsAgo", (int) r4);
    L18:
        if (r2 < 1) goto L25;
        if (r2 != 1) goto L23;
        return getString(R.string.DayAgo);
    L23:
        return formatPluralStringComma("DaysAgo", (int) r2);
    L25:
        if (r0 < 1) goto L32;
        if (r0 != 1) goto L30;
        return getString(R.string.HourAgo);
    L30:
        return formatPluralStringComma("HoursAgo", (int) r0);
    L32:
        if (r112 < 1) goto L39;
        if (r112 != 1) goto L37;
        return getString(R.string.MinuteAgo);
    L37:
        return formatPluralStringComma("MinutesAgo", (int) r112);
    L39:
        return getString(R.string.LessMinuteAgo);
    L40:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatSectionDate(long r1) {
        return formatYearMont(r1, false);
    }

    public static String formatSeenDate(long r5) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L19
        int r2 = r0.get(6);     // Catch: Exception -> L19
        int r4 = r0.get(1);     // Catch: Exception -> L19
        r0.setTimeInMillis(r52);     // Catch: Exception -> L19
        int r1 = r0.get(6);     // Catch: Exception -> L19
        int r02 = r0.get(1);     // Catch: Exception -> L19
        if (r1 != r2) goto L9;
        if (r4 != r02) goto L9;
        return formatString(R.string.TodayAtFormattedWithToday, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L9:
        if ((r1 + 1) != r2) goto L14;
        if (r4 != r02) goto L14;
        return formatString(R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L14:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L18;
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L18:
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L19:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatShortDate(long r13) {
        long r132 = r13 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L30
        int r2 = r0.get(6);     // Catch: Exception -> L30
        int r4 = r0.get(1);     // Catch: Exception -> L30
        long r5 = r0.getTimeInMillis();     // Catch: Exception -> L30
        r0.setTimeInMillis(r132);     // Catch: Exception -> L30
        int r1 = r0.get(6);     // Catch: Exception -> L30
        int r02 = r0.get(1);     // Catch: Exception -> L30
        long r52 = r5 - r132;     // Catch: Exception -> L30
        if (r52 >= 60000) goto L8;
        return getString(R.string.ShortNow);
    L8:
        if (r52 < DurationKt.MILLIS_IN_HOUR) goto L10;
        if (r1 != r2) goto L20;
        if (r4 != r02) goto L20;
        if (r52 >= 43200000) goto L18;
        return formatPluralString("ShortHoursAgo", (int) (r52 / DurationKt.MILLIS_IN_HOUR), new Object[0]);
    L18:
        return getString(R.string.ShortToday);
    L20:
        if ((r1 + 1) != r2) goto L25;
        if (r4 != r02) goto L25;
        return getString(R.string.ShortYesterday);
    L25:
        if (Math.abs(System.currentTimeMillis() - r132) >= 31536000000L) goto L29;
        return getInstance().getFormatterDayMonth().format(new Date(r132));
    L29:
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r132)), getInstance().getFormatterDay().format(new Date(r132))});
    L10:
        return formatPluralString("ShortMinutesAgo", (int) (r52 / 60000), new Object[0]);
    L30:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatShortDateTime(long r4) {
        long r42 = r4 * 1000;
    L10:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    L5:
        if (Math.abs(System.currentTimeMillis() - r42) >= 31536000000L) goto L9;
        return getInstance().getFormatterScheduleDay().format(new Date(r42)) + ", " + getInstance().getFormatterDay().format(new Date(r42));
    L9:
        return getInstance().getFormatterScheduleYear().format(new Date(r42)) + ", " + getInstance().getFormatterDay().format(new Date(r42));
    }

    public static String formatShortDuration(int r8) {
        int r0 = r8 / 3600;
        int r1 = (r8 / 60) % 60;
        int r82 = r8 % 60;
        StringBuilder r2 = new StringBuilder();
        String r3 = MVEL.VERSION_SUB;
        if (r0 <= 0) goto L12;
        if (r2.length() <= 0) goto L7;
        r2.append(":");
    L7:
        if (r0 < 10) goto L9;
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r2.append(r7);
        r2.append(r0);
        goto L12
    L9:
        r7 = MVEL.VERSION_SUB;
    L12:
        if (r2.length() <= 0) goto L14;
        r2.append(":");
    L14:
        if (r1 < 10) goto L16;
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        r2.append(r02);
        r2.append(r1);
        if (r2.length() <= 0) goto L20;
        r2.append(":");
    L20:
        if (r82 < 10) goto L22;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        r2.append(r3);
        r2.append(r82);
        return r2.toString();
    L16:
        r02 = MVEL.VERSION_SUB;
        goto L17
    }

    public static String formatShortDuration2(int r6) {
        int r0 = r6 / 60;
        int r62 = r6 / 3600;
        if (r62 <= 0) goto L7;
        return formatPluralString("ShortHoursAgo", r62, new Object[0]) + " " + formatPluralString("ShortMinutesAgo", r0 % 60, new Object[0]);
    L7:
        return formatPluralString("ShortMinutesAgo", r0, new Object[0]);
    }

    public static String formatShortNumber(int r9, int[] r10) {
        if (ExteraConfig.getDisableNumberRounding() == false) goto L9;
        StringBuilder r102 = new StringBuilder(String.format(Locale.US, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r9)}));
        int r92 = r102.length() - 3;
    L5:
        if (r92 <= 0) goto L8;
        r102.insert(r92, ',');
        r92 = r92 - 3;
        goto L5
    L8:
        return r102.toString();
    L9:
        StringBuilder r0 = new StringBuilder();
        int r2 = 0;
    L10:
        int r3 = r9 / 1000;
        if (r3 <= 0) goto L13;
        r0.append("K");
        r2 = (r9 % 1000) / 100;
        r9 = r3;
        goto L10
    L13:
        if (r10 == null) goto L20;
        double r32 = r9 + (r2 / 10.0d);
        int r5 = 0;
    L16:
        if (r5 >= r0.length()) goto L18;
        r32 = r32 * 1000.0d;
        r5 = r5 + 1;
        goto L16
    L18:
        r10[0] = (int) r32;
    L20:
        if (r2 == 0) goto L30;
        if (r0.length() <= 0) goto L30;
        if (r0.length() != 2) goto L28;
        return String.format(Locale.US, "%d.%dM", new Object[]{Integer.valueOf(r9), Integer.valueOf(r2)});
    L28:
        return String.format(Locale.US, "%d.%d%s", new Object[]{Integer.valueOf(r9), Integer.valueOf(r2), r0.toString()});
    L30:
        if (r0.length() != 2) goto L34;
        return String.format(Locale.US, "%dM", new Object[]{Integer.valueOf(r9)});
    L34:
        return String.format(Locale.US, "%d%s", new Object[]{Integer.valueOf(r9), r0.toString()});
    }

    public static String formatSmallDateChat(long r1) {
        return formatSmallDateChat(r1, false);
    }

    public static String formatSmallDateChat(long r5, boolean r7) {
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L10
        r0.setTimeInMillis(System.currentTimeMillis());     // Catch: Exception -> L10
        int r2 = r0.get(1);     // Catch: Exception -> L10
        long r52 = r5 * 1000;     // Catch: Exception -> L10
        r0.setTimeInMillis(r52);     // Catch: Exception -> L10
        if (r7 == true) goto L9;
        if (r2 != r0.get(1)) goto L9;
        return getInstance().getFormatterDayMonth().format(r52);
    L9:
        return getInstance().getFormatterDayMonth().format(r52) + ", " + r0.get(1);
    L10:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: formatDateChat";
    }

    public static CharSequence formatSpannable(int r1, Object... r2) {
        return formatSpannable(null, r1, r2);
    }

    public static CharSequence formatSpannable(String r1, int r2, Object... r3) {
        return formatSpannable(r1, null, r2, r3);
    }

    private static CharSequence formatSpannable(String r7, String r8, int r9, Object... r10) {
        String r82 = getInstance().getStringV2(r7, r9, r8);     // Catch: Exception -> L7
        if (r82 == null) goto L6;
        SpannableStringBuilder r92 = new SpannableStringBuilder(r82);     // Catch: Exception -> L7
        int r83 = 0;
    L11:
        if (r83 >= r10.length) goto L33;
        String r2 = "s";
        Object r3 = r10[r83];     // Catch: Exception -> L7
        if ((r3 instanceof CharSequence) == false) goto L17;
        CharSequence r32 = (CharSequence) r3;     // Catch: Exception -> L7
    L26:
        StringBuilder r4 = new StringBuilder();     // Catch: Exception -> L7
        r4.append("%");     // Catch: Exception -> L7
        r83 = r83 + 1;     // Catch: Exception -> L7
        r4.append(r83);     // Catch: Exception -> L7
        r4.append("$");     // Catch: Exception -> L7
        r4.append(r2);     // Catch: Exception -> L7
        String r42 = r4.toString();     // Catch: Exception -> L7
        int r5 = r92.toString().indexOf(r42);     // Catch: Exception -> L7
        if (r5 != (-1)) goto L28;
        String r22 = "%" + r2;     // Catch: Exception -> L7
        int r43 = r92.toString().indexOf(r22);     // Catch: Exception -> L7
        if (r43 == (-1)) goto L11;
        r92.replace(r43, r22.length() + r43, r32);     // Catch: Exception -> L7
        goto L11
    L28:
        r92.replace(r5, r42.length() + r5, r32);     // Catch: Exception -> L7
        goto L11
    L17:
        if ((r3 instanceof Integer) == false) goto L21;
        r32 = _UrlKt.FRAGMENT_ENCODE_SET + ((Integer) r10[r83]);     // Catch: Exception -> L7
    L19:
        r2 = "d";
        goto L26
    L21:
        if ((r3 instanceof Long) == false) goto L23;
        r32 = _UrlKt.FRAGMENT_ENCODE_SET + ((Long) r10[r83]);     // Catch: Exception -> L7
        goto L19
    L23:
        if (r3 != null) goto L25;
        r32 = "null";
        goto L26
    L25:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L26
    L33:
        return r92;
    L6:
        return "LOC_ERR: " + r7;
    L7:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: " + r7;
    }

    public static String formatStartsTime(long r1, int r3) {
        return formatStartsTime(r1, r3, true);
    }

    public static String formatStartsTime(long r7, int r9, boolean r10) {
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(System.currentTimeMillis());
        int r2 = r0.get(1);
        int r4 = r0.get(6);
        r0.setTimeInMillis(r7 * 1000);
        int r72 = r0.get(1);
        int r8 = r0.get(6);
        if (r2 != r72) goto L8;
        if (r10 == false) goto L7;
        if (r8 != r4) goto L7;
        int r73 = 0;
    L9:
        if (r9 != 1) goto L11;
        r73 = r73 + 3;
    L20:
        return getInstance().getFormatterScheduleSend(r73).format(r0.getTimeInMillis());
    L11:
        if (r9 != 2) goto L14;
        r73 = r73 + 6;
        goto L20
    L14:
        if (r9 != 3) goto L17;
        r73 = r73 + 9;
        goto L20
    L17:
        if (r9 != 4) goto L20;
        r73 = r73 + 12;
    L7:
        r73 = 1;
        goto L9
    L8:
        r73 = 2;
        goto L9
    }

    public static String formatStatusExpireDateTime(long r5) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L14
        int r2 = r0.get(6);     // Catch: Exception -> L14
        int r4 = r0.get(1);     // Catch: Exception -> L14
        r0.setTimeInMillis(r52);     // Catch: Exception -> L14
        int r1 = r0.get(6);     // Catch: Exception -> L14
        int r02 = r0.get(1);     // Catch: Exception -> L14
        if (r1 != r2) goto L9;
        if (r4 != r02) goto L9;
        return formatString(R.string.TodayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L9:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L13;
        return getInstance().getFormatterScheduleDay().format(new Date(r52));
    L13:
        return getInstance().getChatFullDate().format(new Date(r52));
    L14:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatStoryDate(long r11) {
        long r112 = r11 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L26
        int r2 = r0.get(6);     // Catch: Exception -> L26
        int r4 = r0.get(1);     // Catch: Exception -> L26
        long r5 = r0.getTimeInMillis();     // Catch: Exception -> L26
        r0.setTimeInMillis(r112);     // Catch: Exception -> L26
        int r1 = r0.get(6);     // Catch: Exception -> L26
        int r02 = r0.get(1);     // Catch: Exception -> L26
        long r52 = r5 - r112;     // Catch: Exception -> L26
        if (r52 >= 60000) goto L8;
        return getString(R.string.RightNow);
    L8:
        if (r52 >= DurationKt.MILLIS_IN_HOUR) goto L11;
        int r113 = (int) (r52 / 60000);     // Catch: Exception -> L26
        return formatPluralString("MinutesAgo", r113, new Object[]{Integer.valueOf(r113)});
    L11:
        if (r1 != r2) goto L16;
        if (r4 != r02) goto L16;
        return formatString(R.string.TodayAtFormattedWithToday, new Object[]{getInstance().getFormatterDay().format(new Date(r112))});
    L16:
        if ((r1 + 1) != r2) goto L21;
        if (r4 != r02) goto L21;
        return formatString(R.string.YesterdayAtFormatted, new Object[]{getInstance().getFormatterDay().format(new Date(r112))});
    L21:
        if (Math.abs(System.currentTimeMillis() - r112) >= 31536000000L) goto L25;
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r112)), getInstance().getFormatterDay().format(new Date(r112))});
    L25:
        return formatString(R.string.formatDateAtTime, new Object[]{getInstance().getFormatterYear().format(new Date(r112)), getInstance().getFormatterDay().format(new Date(r112))});
    L26:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatString(int r1, Object... r2) {
        return formatString(null, r1, r2);
    }

    @Deprecated
    public static String formatString(String r1, int r2, Object... r3) {
        return formatString(r1, null, r2, r3);
    }

    private static String formatString(String r2, String r3, int r4, Object... r5) {
        String r32 = getInstance().getStringV2(r2, r4, r3);     // Catch: Exception -> L7
        if (r32 != null) goto L10;
        return "LOC_ERR: " + r2;
    L10:
        if (getInstance().currentLocale == null) goto L14;
        return String.format(getInstance().currentLocale, r32, r5);
    L14:
        return String.format(r32, r5);
    L7:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: " + r2;
    }

    public static String formatStringSimple(String r1, Object... r2) {
    L6:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR: " + r1;
    L3:
        if (getInstance().currentLocale == null) goto L9;
        return String.format(getInstance().currentLocale, r1, r2);
    L9:
        return String.format(r1, r2);
    }

    public static String formatTTLString(int r5) {
        if (r5 >= 60) goto L7;
        return formatPluralString("Seconds", r5, new Object[0]);
    L7:
        if (r5 >= 3600) goto L11;
        return formatPluralString("Minutes", r5 / 60, new Object[0]);
    L11:
        if (r5 >= 86400) goto L15;
        return formatPluralString("Hours", (r5 / 60) / 60, new Object[0]);
    L15:
        if (r5 >= 604800) goto L19;
        return formatPluralString("Days", ((r5 / 60) / 60) / 24, new Object[0]);
    L19:
        if (r5 >= 2678400) goto L27;
        int r2 = ((r5 / 60) / 60) / 24;
        if ((r5 % 7) != 0) goto L25;
        return formatPluralString("Weeks", r2 / 7, new Object[0]);
    L25:
        return String.format("%s %s", new Object[]{formatPluralString("Weeks", r2 / 7, new Object[0]), formatPluralString("Days", r2 % 7, new Object[0])});
    L27:
        return formatPluralString("Months", (((r5 / 60) / 60) / 24) / 30, new Object[0]);
    }

    public static String formatTodoCompletedDate(long r5) {
        long r52 = r5 * 1000;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L19
        int r2 = r0.get(6);     // Catch: Exception -> L19
        int r4 = r0.get(1);     // Catch: Exception -> L19
        r0.setTimeInMillis(r52);     // Catch: Exception -> L19
        int r1 = r0.get(6);     // Catch: Exception -> L19
        int r02 = r0.get(1);     // Catch: Exception -> L19
        if (r1 != r2) goto L9;
        if (r4 != r02) goto L9;
        return formatString(R.string.TodoCompletedTodayAt, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L9:
        if ((r1 + 1) != r2) goto L14;
        if (r4 != r02) goto L14;
        return formatString(R.string.TodoCompletedYesterdayAt, new Object[]{getInstance().getFormatterDay().format(new Date(r52))});
    L14:
        if (Math.abs(System.currentTimeMillis() - r52) >= 31536000000L) goto L18;
        return formatString(R.string.TodoCompletedDateTimeAt, new Object[]{getInstance().getFormatterDayMonth().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L18:
        return formatString(R.string.TodoCompletedDateTimeAt, new Object[]{getInstance().getFormatterYear().format(new Date(r52)), getInstance().getFormatterDay().format(new Date(r52))});
    L19:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatUserStatus(int r2, TLRPC.User r3) {
        return formatUserStatus(r2, r3, null, null, new boolean[1]);
    }

    public static String formatUserStatus(int r1, TLRPC.User r2, boolean[] r3) {
        return formatUserStatus(r1, r2, r3, null);
    }

    public static String formatUserStatus(int r1, TLRPC.User r2, boolean[] r3, boolean[] r4) {
        return formatUserStatus(r1, r2, r3, r4, null);
    }

    public static String formatUserStatus(int r12, TLRPC.User r13, boolean[] r14, boolean[] r15, boolean[] r16) {
        if (r13 == null) goto L30;
        TLRPC.UserStatus r6 = r13.status;
        if (r6 == null) goto L30;
        if (r6.expires != 0) goto L30;
        if ((r6 instanceof TLRPC.TL_userStatusRecently) == false) goto L16;
        if (r6.by_me == false) goto L13;
        int r7 = -1000;
    L14:
        r6.expires = r7;
        goto L30
    L13:
        r7 = -100;
        goto L14
    L16:
        if ((r6 instanceof TLRPC.TL_userStatusLastWeek) == false) goto L23;
        if (r6.by_me == false) goto L20;
        int r72 = -1001;
    L21:
        r6.expires = r72;
        goto L30
    L20:
        r72 = -101;
        goto L21
    L23:
        if ((r6 instanceof TLRPC.TL_userStatusLastMonth) == false) goto L30;
        if (r6.by_me == false) goto L27;
        int r73 = -1002;
    L28:
        r6.expires = r73;
        goto L30
    L27:
        r73 = -102;
    L30:
        if (r13 == null) goto L41;
        TLRPC.UserStatus r9 = r13.status;
        if (r9 == null) goto L41;
        if (r9.expires > 0) goto L41;
        if (MessagesController.getInstance(r12).onlinePrivacy.containsKey(Long.valueOf(r13.id)) == false) goto L41;
        if (r14 == null) goto L40;
        r14[0] = true;
    L40:
        return getString("Online", R.string.Online);
    L41:
        if (r13 == null) goto L79;
        TLRPC.UserStatus r92 = r13.status;
        if (r92 == null) goto L79;
        if (r92.expires == 0) goto L79;
        if (UserObject.isDeleted(r13) == true) goto L79;
        if ((r13 instanceof TLRPC.TL_userEmpty) == true) goto L79;
        int r122 = ConnectionsManager.getInstance(r12).getCurrentTime();
        int r132 = r13.status.expires;
        if (r132 <= r122) goto L58;
        if (r14 == null) goto L56;
        r14[0] = true;
    L56:
        return getString("Online", R.string.Online);
    L58:
        if (r132 == (-1)) goto L60;
        if (r132 == (-100)) goto L77;
        if (r132 == (-1000)) goto L77;
        if (r132 == (-101)) goto L75;
        if (r132 == (-1001)) goto L75;
        if (r132 == (-102)) goto L73;
        if (r132 == (-1002)) goto L73;
        return formatDateOnline(r132, r15, r16);
    L73:
        return getString("WithinAMonth", R.string.WithinAMonth);
    L75:
        return getString("WithinAWeek", R.string.WithinAWeek);
    L77:
        return getString("Lately", R.string.Lately);
    L60:
        return getString("Invisible", R.string.Invisible);
    L79:
        return getString("ALongTimeAgo", R.string.ALongTimeAgo);
    }

    public static String formatYearMont(long r17, boolean r19) {
        long r0 = 1000 * r17;
        Calendar r2 = Calendar.getInstance();     // Catch: Exception -> L10
        int r4 = r2.get(1);     // Catch: Exception -> L10
        r2.setTimeInMillis(r0);     // Catch: Exception -> L10
        int r02 = r2.get(1);     // Catch: Exception -> L10
        int r1 = r2.get(2);     // Catch: Exception -> L10
        String[] r22 = {getString(R.string.January), getString(R.string.February), getString(R.string.March), getString(R.string.April), getString(R.string.May), getString(R.string.June), getString(R.string.July), getString(R.string.August), getString(R.string.September), getString(R.string.October), getString(R.string.November), getString(R.string.December)};     // Catch: Exception -> L10
        if (r4 != r02) goto L9;
        if (r19 == true) goto L9;
        return r22[r1];
    L9:
        return r22[r1] + " " + r02;
    L10:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String formatYearMonthDay(long r17, boolean r19) {
        long r0 = 1000 * r17;
        Calendar r2 = Calendar.getInstance();     // Catch: Exception -> L11
        int r4 = r2.get(1);     // Catch: Exception -> L11
        r2.setTimeInMillis(r0);     // Catch: Exception -> L11
        int r02 = r2.get(1);     // Catch: Exception -> L11
        int r1 = r2.get(2);     // Catch: Exception -> L11
        int r22 = r2.get(5);     // Catch: Exception -> L11
        String[] r3 = {getString(R.string.January), getString(R.string.February), getString(R.string.March), getString(R.string.April), getString(R.string.May), getString(R.string.June), getString(R.string.July), getString(R.string.August), getString(R.string.September), getString(R.string.October), getString(R.string.November), getString(R.string.December)};     // Catch: Exception -> L11
        if (r4 != r02) goto L10;
        if (r19 == true) goto L10;
        return r3[r1] + " " + r22;
    L10:
        return r3[r1] + " " + r22 + ", " + r02;
    L11:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    }

    public static String getCountryName(String r1) {
        return getCountryName(r1, R.string.Fragment);
    }

    public static String getCountryName(String r1, int r2) {
        if (r1 != null) goto L4;
    L12:
        return new Locale(_UrlKt.FRAGMENT_ENCODE_SET, r1).getDisplayCountry(getInstance().getCurrentLocale());
    L9:
        e = move-exception;
        FileLog.e(e);
        return null;
    L4:
        if (r1.equalsIgnoreCase("ft") == false) goto L12;
        return getString(r2);
    }

    public static CharSequence getCountryWithFlag(String r1, int r2) {
        return getCountryWithFlag(r1, r2, R.string.Fragment);
    }

    public static CharSequence getCountryWithFlag(String r3, int r4, int r5) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        String r1 = getLanguageFlag(r3);
        if (TextUtils.isEmpty(r1) == true) goto L5;
        r0.append(r1).append(" ");
    L5:
        String r52 = getCountryName(r3, r5);
        if (TextUtils.isEmpty(r52) == true) goto L8;
        r0.append(r52);
    L9:
        TextPaint r32 = new TextPaint();
        r32.setTextSize(AndroidUtilities.dp(r4));
        return Emoji.replaceEmoji(r0, r32.getFontMetricsInt(), true);
    L8:
        r0.append(r3);
        goto L9
    }

    public static int getCurrencyExpDivider(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case 65726: goto L118;
            case 65759: goto L114;
            case 66267: goto L110;
            case 66813: goto L106;
            case 66823: goto L102;
            case 67122: goto L98;
            case 67712: goto L94;
            case 70719: goto L90;
            case 72732: goto L86;
            case 72801: goto L82;
            case 73631: goto L78;
            case 73683: goto L74;
            case 74532: goto L70;
            case 74704: goto L66;
            case 74840: goto L62;
            case 75863: goto L58;
            case 76263: goto L54;
            case 76618: goto L50;
            case 78388: goto L46;
            case 79710: goto L42;
            case 81569: goto L38;
            case 83210: goto L34;
            case 83974: goto L30;
            case 84517: goto L26;
            case 85132: goto L22;
            case 85367: goto L18;
            case 86653: goto L14;
            case 87087: goto L10;
            case 87118: goto L6;
            default: goto L121;
        };
    L121:
        switch(r3) {
            case 0: goto L128;
            case 1: goto L127;
            case 2: goto L127;
            case 3: goto L125;
            case 4: goto L127;
            case 5: goto L127;
            case 6: goto L127;
            case 7: goto L127;
            case 8: goto L128;
            case 9: goto L127;
            case 10: goto L128;
            case 11: goto L127;
            case 12: goto L127;
            case 13: goto L127;
            case 14: goto L128;
            case 15: goto L128;
            case 16: goto L127;
            case 17: goto L124;
            case 18: goto L128;
            case 19: goto L127;
            case 20: goto L127;
            case 21: goto L128;
            case 22: goto L127;
            case 23: goto L127;
            case 24: goto L127;
            case 25: goto L127;
            case 26: goto L127;
            case 27: goto L127;
            case 28: goto L127;
            default: goto L122;
        };
    L122:
        return 100;
    L124:
        return 10;
    L125:
        return 10000;
    L127:
        return 1;
    L128:
        return 1000;
    L6:
        if (r4.equals("XPF") == false) goto L121;
        r3 = 28;
        goto L121
    L10:
        if (r4.equals("XOF") == false) goto L121;
        r3 = 27;
        goto L121
    L14:
        if (r4.equals("XAF") == false) goto L121;
        r3 = 26;
        goto L121
    L18:
        if (r4.equals("VUV") == false) goto L121;
        r3 = 25;
        goto L121
    L22:
        if (r4.equals("VND") == false) goto L121;
        r3 = 24;
        goto L121
    L26:
        if (r4.equals("UYI") == false) goto L121;
        r3 = 23;
        goto L121
    L30:
        if (r4.equals("UGX") == false) goto L121;
        r3 = 22;
        goto L121
    L34:
        if (r4.equals("TND") == false) goto L121;
        r3 = 21;
        goto L121
    L38:
        if (r4.equals("RWF") == false) goto L121;
        r3 = 20;
        goto L121
    L42:
        if (r4.equals("PYG") == false) goto L121;
        r3 = 19;
        goto L121
    L46:
        if (r4.equals("OMR") == false) goto L121;
        r3 = 18;
        goto L121
    L50:
        if (r4.equals("MRO") == false) goto L121;
        r3 = 17;
        goto L121
    L54:
        if (r4.equals("MGA") == false) goto L121;
        r3 = 16;
        goto L121
    L58:
        if (r4.equals("LYD") == false) goto L121;
        r3 = 15;
        goto L121
    L62:
        if (r4.equals("KWD") == false) goto L121;
        r3 = 14;
        goto L121
    L66:
        if (r4.equals("KRW") == false) goto L121;
        r3 = '\r';
        goto L121
    L70:
        if (r4.equals("KMF") == false) goto L121;
        r3 = '\f';
        goto L121
    L74:
        if (r4.equals("JPY") == false) goto L121;
        r3 = 11;
        goto L121
    L78:
        if (r4.equals("JOD") == false) goto L121;
        r3 = '\n';
        goto L121
    L82:
        if (r4.equals("ISK") == false) goto L121;
        r3 = '\t';
        goto L121
    L86:
        if (r4.equals("IQD") == false) goto L121;
        r3 = '\b';
        goto L121
    L90:
        if (r4.equals("GNF") == false) goto L121;
        r3 = 7;
        goto L121
    L94:
        if (r4.equals("DJF") == false) goto L121;
        r3 = 6;
        goto L121
    L98:
        if (r4.equals("CVE") == false) goto L121;
        r3 = 5;
        goto L121
    L102:
        if (r4.equals("CLP") == false) goto L121;
        r3 = 4;
        goto L121
    L106:
        if (r4.equals("CLF") == false) goto L121;
        r3 = 3;
        goto L121
    L110:
        if (r4.equals("BYR") == false) goto L121;
        r3 = 2;
        goto L121
    L114:
        if (r4.equals("BIF") == false) goto L121;
        r3 = 1;
        goto L121
    L118:
        if (r4.equals("BHD") == false) goto L121;
        r3 = 0;
        goto L121
    }

    public static String getCurrentLanguageName() {
        LocaleInfo r0 = getInstance().currentLocaleInfo;
        if (r0 == null) goto L10;
        if (TextUtils.isEmpty(r0.name) == true) goto L10;
        return r0.name;
    L10:
        return getString("LanguageName", R.string.LanguageName);
    }

    private LinkedHashSet<String> getExteraLocaleAssetPaths(LocaleInfo r3) {
        LinkedHashSet<String> r0 = new LinkedHashSet();
        r0.add("extera_locales/values/extera.xml");
        if (r3 == null) goto L6;
        addExteraLocaleAssetPaths(r0, r3.pluralLangCode);
        addExteraLocaleAssetPaths(r0, r3.baseLangCode);
        addExteraLocaleAssetPaths(r0, r3.shortName);
        return r0;
    L6:
        Locale r32 = this.currentLocale;
        if (r32 == null) goto L9;
        addExteraLocaleAssetPaths(r0, r32);
    L9:
        Locale r33 = this.systemDefaultLocale;
        if (r33 == null) goto L12;
        addExteraLocaleAssetPaths(r0, r33);
    L12:
        return r0;
    }

    public static LocaleController getInstance() {
        LocaleController r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(LocaleController.class);
        LocaleController r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new LocaleController();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(LocaleController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static String getLanguageFlag(String r7) {
        if (r7.length() == 2) goto L5;
        return null;
    L5:
        if (r7.equals("YL") == false) goto L8;
        return null;
    L8:
        if (r7.equals("FT") == false) goto L12;
        return "🏴\u200d☠️";
    L12:
        if (r7.equals("XG") == false) goto L16;
        return "🛰";
    L16:
        if (r7.equals("XV") == false) goto L19;
        return "🌍";
    L19:
        char[] r72 = r7.toCharArray();
        return new String(new char[]{CharacterCompat.highSurrogate(127397), CharacterCompat.lowSurrogate(r72[0] + 61861), CharacterCompat.highSurrogate(127397), CharacterCompat.lowSurrogate(r72[1] + 61861)});
    }

    public static String getLocaleAlias(String r15) {
        if (r15 != null) goto L5;
        return null;
    L5:
        char r14 = 65535;
        switch(r15.hashCode()) {
            case 3325: goto L53;
            case 3355: goto L49;
            case 3365: goto L45;
            case 3374: goto L41;
            case 3391: goto L37;
            case 3404: goto L33;
            case 3405: goto L29;
            case 3508: goto L25;
            case 3521: goto L21;
            case 3704: goto L17;
            case 3856: goto L13;
            case 101385: goto L9;
            default: goto L56;
        };
    L56:
        switch(r14) {
            case 0: goto L69;
            case 1: goto L68;
            case 2: goto L67;
            case 3: goto L66;
            case 4: goto L65;
            case 5: goto L64;
            case 6: goto L63;
            case 7: goto L62;
            case 8: goto L61;
            case 9: goto L60;
            case 10: goto L59;
            case 11: goto L58;
            default: goto L57;
        };
    L57:
        return null;
    L58:
        return "tl";
    L59:
        return "ji";
    L60:
        return "fil";
    L61:
        return "nb";
    L62:
        return "no";
    L63:
        return "jv";
    L64:
        return "jw";
    L65:
        return "yi";
    L66:
        return "he";
    L67:
        return "id";
    L68:
        return "in";
    L69:
        return "iw";
    L9:
        if (r15.equals("fil") == false) goto L56;
        r14 = 11;
        goto L56
    L13:
        if (r15.equals("yi") == false) goto L56;
        r14 = '\n';
        goto L56
    L17:
        if (r15.equals("tl") == false) goto L56;
        r14 = '\t';
        goto L56
    L21:
        if (r15.equals("no") == false) goto L56;
        r14 = '\b';
        goto L56
    L25:
        if (r15.equals("nb") == false) goto L56;
        r14 = 7;
        goto L56
    L29:
        if (r15.equals("jw") == false) goto L56;
        r14 = 6;
        goto L56
    L33:
        if (r15.equals("jv") == false) goto L56;
        r14 = 5;
        goto L56
    L37:
        if (r15.equals("ji") == false) goto L56;
        r14 = 4;
        goto L56
    L41:
        if (r15.equals("iw") == false) goto L56;
        r14 = 3;
        goto L56
    L45:
        if (r15.equals("in") == false) goto L56;
        r14 = 2;
        goto L56
    L49:
        if (r15.equals("id") == false) goto L56;
        r14 = 1;
        goto L56
    L53:
        if (r15.equals("he") == false) goto L56;
        r14 = 0;
        goto L56
    }

    private HashMap<String, String> getLocaleAssetStrings(String r2) {
        InputStream r22 = ApplicationLoader.applicationContext.getAssets().open(r2);     // Catch: Exception -> L15 FileNotFoundException -> L18
        HashMap<String, String> r1 = parseLocaleStrings(r22, false);     // Catch: Throwable -> L8
        if (r22 == null) goto L7;
        r22.close();     // Catch: Exception -> L15 FileNotFoundException -> L18
    L7:
        return r1;
    L8:
        th = move-exception;
        if (r22 != null) goto L22;
    L14:
        throw th;     // Catch: Exception -> L15 FileNotFoundException -> L18
    L22:
        r22.close();     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L15 FileNotFoundException -> L18
    L15:
        e = move-exception;
        FileLog.e(e);
        return new HashMap();
    L19:
        return new HashMap();
    }

    private HashMap<String, String> getLocaleFileStrings(File r2) {
        return getLocaleFileStrings(r2, false);
    }

    private HashMap<String, String> getLocaleFileStrings(File r4, boolean r5) {
        this.reloadLastFile = false;
        FileLog.d("getLocaleFileStrings: reloadLastFile = false");
    L7:
        e = move-exception;
        File r1 = new File(ApplicationLoader.getFilesDirFixed(), "malformed_locales/");     // Catch: Exception -> L22
        r1.mkdirs();     // Catch: Exception -> L22
        AndroidUtilities.copyFile(r4, new File(r1, r4.getName()));     // Catch: Exception -> L22
    L20:
        FileLog.e(e);
        FileLog.d("getLocaleFileStrings: error, reloadLastFile = true;");
        this.reloadLastFile = true;
        return new HashMap();
    L4:
        if (r4.exists() == false) goto L6;
        FileInputStream r0 = new FileInputStream(r4);     // Catch: Exception -> L7
        HashMap<String, String> r52 = parseLocaleStrings(r0, r5);     // Catch: Throwable -> L13
        r0.close();     // Catch: Exception -> L7
        return r52;
    L13:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L16
    L18:
        throw th;     // Catch: Exception -> L7
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L7
        goto L18
    L6:
        return new HashMap();
    }

    private LinkedHashSet<String> getLocaleResourceQualifiers(LocaleInfo r5) {
        LinkedHashSet<String> r0 = new LinkedHashSet();
        Collection<? extends String> r1 = new LinkedHashSet();
        Iterator<String> r4 = getExteraLocaleAssetPaths(r5).iterator();
    L4:
        if (r4.hasNext() == false) goto L13;
        String r52 = r4.next().substring(21, r5.length() - 11);
        if (r52.isEmpty() == true) goto L4;
        if (r52.contains("-r") == false) goto L11;
        LinkedHashSet<String> r2 = r0;
    L12:
        r2.add(r52.substring(1));
        goto L4
    L11:
        r2 = r1;
        goto L12
    L13:
        r0.addAll(r1);
        return r0;
    }

    private String getLocaleString(Locale r4) {
        if (r4 != null) goto L5;
        return "en";
    L5:
        String r0 = r4.getLanguage();
        String r1 = r4.getCountry();
        String r42 = r4.getVariant();
        if (r0.length() == 0) goto L8;
    L10:
        StringBuilder r3 = new StringBuilder(11);
        r3.append(r0);
        if (r1.length() <= 0) goto L13;
    L14:
        r3.append('_');
    L15:
        r3.append(r1);
        if (r42.length() <= 0) goto L18;
        r3.append('_');
    L18:
        r3.append(r42);
        return r3.toString();
    L13:
        if (r42.length() <= 0) goto L15;
    L8:
        if (r1.length() != 0) goto L10;
        return "en";
    }

    public static String getLocaleStringIso639() {
        LocaleInfo r0 = getInstance().currentLocaleInfo;
        if (r0 != null) goto L5;
        Locale r02 = getInstance().currentLocale;
        if (r02 != null) goto L9;
        return "en";
    L9:
        String r2 = r02.getLanguage();
        String r3 = r02.getCountry();
        String r03 = r02.getVariant();
        if (r2.length() == 0) goto L12;
    L14:
        StringBuilder r1 = new StringBuilder(11);
        r1.append(r2);
        if (r3.length() <= 0) goto L17;
    L18:
        r1.append(SignatureVisitor.SUPER);
    L19:
        r1.append(r3);
        if (r03.length() <= 0) goto L22;
        r1.append('_');
    L22:
        r1.append(r03);
        return r1.toString();
    L17:
        if (r03.length() <= 0) goto L19;
    L12:
        if (r3.length() != 0) goto L14;
        return "en";
    L5:
        return r0.getLangCode();
    }

    private String getLocalizationAssetByResourceQualifier(LocaleInfo r6) {
        Iterator<String> r62 = getLocaleResourceQualifiers(r6).iterator();
    L4:
        if (r62.hasNext() == false) goto L19;
        String r0 = r62.next().replace("-r", "_").replace(SignatureVisitor.SUPER, '_').toLowerCase(Locale.US);
        int r1 = r0.indexOf(95);
        if (r1 != (-1)) goto L8;
        String r3 = r0;
    L9:
        String r32 = LEGACY_LANGUAGE_ALIASES.get(r3);
        String r4 = openableLocalizationAsset(r0);
        if (r4 != null) goto L11;
        if (r32 == null) goto L4;
        if (r1 != (-1)) goto L15;
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L16:
        String r03 = openableLocalizationAsset(r32.concat(r02));
        if (r03 == null) goto L4;
        return r03;
    L15:
        r02 = r0.substring(r1);
        goto L16
    L11:
        return r4;
    L8:
        r3 = r0.substring(0, r1);
        goto L9
    L19:
        return null;
    }

    private String getLocalizedString(String r1) {
        checkLocalizationInternal();
        return this.localizationInternal.getByResName(r1);
    }

    public static String getPluralString(String r2, int r3) {
        if (r2 == null) goto L11;
        if (r2.length() == 0) goto L11;
        if (getInstance().currentPluralRules == null) goto L11;
        return getInstance().getStringInternal(r2 + "_" + getInstance().stringForQuantity(getInstance().currentPluralRules.quantityForNumber(r3)), r2.concat("_other"), 0);
    L11:
        return "LOC_ERR:" + r2;
    }

    public static String getServerString(String r1) {
        String r0 = getInstance().localizationExternal.getByResName(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return getInstance().getLocalizedString(r1);
    }

    public static String getString(int r1) {
        return getString(null, r1);
    }

    @Deprecated
    public static String getString(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return "LOC_ERR:" + r2;
    L7:
        return getString(r2, 0);
    }

    @Deprecated
    public static String getString(String r1, int r2) {
        return getInstance().getStringInternal(r1, r2);
    }

    private String getStringInternal(String r2, int r3) {
        return getStringInternal(r2, null, r3);
    }

    private String getStringInternal(String r2, String r3, int r4) {
        if (isAppNameResource(r2, r4) == true) goto L5;
        String r1 = getStringV2(r2, r4, r3);
        if (r1 != null) goto L11;
        return "LOC_ERR:" + r2;
    L11:
        if (r4 == R.string.AppUpdate) goto L17;
        if ("AppUpdate".equals(r2) == true) goto L17;
        return r1;
    L17:
        return r1.replaceAll("Telegram", "exteraGram");
    L5:
        return LocaleUtils.getAppName();
    }

    public static String getStringParamForNumber(int r2) {
        return getInstance().stringForQuantity(getInstance().currentPluralRules.quantityForNumber(r2));
    }

    private String getStringV2(String r3, int r4, String r5) {
        Context r0 = ApplicationLoader.applicationContext;
        if (BuildVars.USE_CLOUD_STRINGS == false) goto L10;
        String r1 = this.localizationExternal.getByResNameOrResId(r0, r3, r4);
        if (r1 == null) goto L7;
        return r1;
    L7:
        String r12 = this.localizationExternal.getByResName(r5);
        if (r12 == null) goto L10;
        return r12;
    L10:
        checkLocalizationInternal();
        String r32 = this.localizationInternal.getByResNameOrResId(r0, r3, r4);
        if (r32 == null) goto L14;
        return r32;
    L14:
        return this.localizationInternal.getByResName(r5);
    }

    public static String getSystemLocaleStringIso639() {
        Locale r0 = getInstance().getSystemDefaultLocale();
        if (r0 != null) goto L5;
        return "en";
    L5:
        String r2 = r0.getLanguage();
        String r3 = r0.getCountry();
        String r02 = r0.getVariant();
        if (r2.length() == 0) goto L8;
    L10:
        StringBuilder r1 = new StringBuilder(11);
        r1.append(r2);
        if (r3.length() <= 0) goto L13;
    L14:
        r1.append(SignatureVisitor.SUPER);
    L15:
        r1.append(r3);
        if (r02.length() <= 0) goto L18;
        r1.append('_');
    L18:
        r1.append(r02);
        return r1.toString();
    L13:
        if (r02.length() <= 0) goto L15;
    L8:
        if (r3.length() != 0) goto L10;
        return "en";
    }

    public static String getTimeZoneName(String r3, boolean r4) {
        TimeZone r32 = DesugarTimeZone.getTimeZone(r3);
        if (r32 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r0 = r32.getDisplayName(true, 0, getInstance().getCurrentLocale());
        if (r4 == false) goto L12;
        String r33 = r32.getDisplayName(true, 1, getInstance().getCurrentLocale());
        if (TextUtils.equals(r33, r0) == true) goto L12;
        return r33 + ", " + r0;
    L12:
        return r0;
    }

    public static boolean getUseImperialSystemType() {
        ensureImperialSystemInit();
        return useImperialSystemType.booleanValue();
    }

    private static boolean isAppNameResource(String r2, int r3) {
        if (r3 != R.string.AppName) goto L5;
    L15:
        return true;
    L5:
        if (r3 == R.string.AppNameBeta) goto L15;
        if (r3 == R.string.exteraAppName) goto L15;
        if (r2 != null) goto L11;
        return false;
    L11:
        if (r2.contains("AppName") == false) goto L16;
        return true;
    L16:
        return false;
    }

    public static boolean isRTLCharacter(char r3) {
        if (Character.getDirectionality(r3) != 1) goto L5;
    L13:
        return true;
    L5:
        if (Character.getDirectionality(r3) == 2) goto L13;
        if (Character.getDirectionality(r3) == 16) goto L13;
        if (Character.getDirectionality(r3) == 17) goto L13;
        return false;
    }

    private /* synthetic */ void lambda$applyLanguage$7(LocaleInfo r7, int r8, Runnable r9) {
        applyRemoteLanguage(r7, null, true, r8, r9);
    }

    private /* synthetic */ void lambda$applyLanguage$8(int r3) {
        reloadCurrentRemoteLocale(r3, null, true, null);
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$14(LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        saveRemoteLocaleStrings(r1, (TLRPC.TL_langPackDifference) r2, r3, r4);
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$15(LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L5;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda21(this, r1, r4, r2, r3));
        return;
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$16(LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        saveRemoteLocaleStrings(r1, (TLRPC.TL_langPackDifference) r2, r3, r4);
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$17(LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L5;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda10(this, r1, r4, r2, r3));
        return;
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$18(LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        saveRemoteLocaleStrings(r1, (TLRPC.TL_langPackDifference) r2, r3, r4);
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$19(LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L5;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda16(this, r1, r4, r2, r3));
        return;
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$20(LocaleInfo r1, TLObject r2, int r3, Runnable r4) {
        saveRemoteLocaleStrings(r1, (TLRPC.TL_langPackDifference) r2, r3, r4);
    }

    private /* synthetic */ void lambda$applyRemoteLanguage$21(LocaleInfo r1, int r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L5;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda15(this, r1, r4, r2, r3));
        return;
    }

    private /* synthetic */ void lambda$checkForcePatchLangpack$5(String r4, Runnable r5) {
        MessagesController.getGlobalMainSettings().edit().putBoolean("langpack_patched" + r4, true).apply();
        if (r5 == null) goto L5;
        r5.run();
    L5:
        this.patching = false;
    }

    private /* synthetic */ void lambda$checkForcePatchLangpack$6(String r2, Runnable r3) {
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda7(this, r2, r3));
    }

    private /* synthetic */ void lambda$checkPatchLangpack$4(int r3) {
        reloadCurrentRemoteLocale(r3, null, true, null);
    }

    private /* synthetic */ void lambda$checkUpdateForCurrentRemoteLocale$2(int r2) {
        this.checkingUpdateForCurrentRemoteLocale = false;
        checkPatchLangpack(r2);
    }

    private /* synthetic */ void lambda$checkUpdateForCurrentRemoteLocale$3(int r2) {
        this.checkingUpdateForCurrentRemoteLocale = false;
        checkPatchLangpack(r2);
    }

    private /* synthetic */ void lambda$loadRemoteLanguages$11(TLObject r10, boolean r11, int r12) {
        this.loadingRemoteLanguages = false;
        Vector r102 = (Vector) r10;
        int r1 = this.remoteLanguages.size();
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L6;
        this.remoteLanguages.get(r2).serverIndex = Integer.MAX_VALUE;
        r2 = r2 + 1;
        goto L4
    L6:
        int r13 = r102.objects.size();
        int r22 = 0;
    L7:
        if (r22 >= r13) goto L23;
        TLRPC.TL_langPackLanguage r4 = (TLRPC.TL_langPackLanguage) r102.objects.get(r22);
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("loaded lang " + r4.name);
    L11:
        LocaleInfo r5 = new LocaleInfo();
        r5.nameEnglish = r4.name;
        r5.name = r4.native_name;
        r5.shortName = r4.lang_code.replace(SignatureVisitor.SUPER, '_').toLowerCase();
        String r6 = r4.base_lang_code;
        if (r6 == null) goto L14;
        r5.baseLangCode = r6.replace(SignatureVisitor.SUPER, '_').toLowerCase();
    L15:
        r5.pluralLangCode = r4.plural_code.replace(SignatureVisitor.SUPER, '_').toLowerCase();
        r5.isRtl = r4.rtl;
        r5.pathToFile = "remote";
        r5.serverIndex = r22;
        LocaleInfo r42 = getLanguageFromDict(r5.getKey());
        if (r42 != null) goto L18;
        this.languages.add(r5);
        this.languagesDict.put(r5.getKey(), r5);
    L20:
        if (this.remoteLanguagesDict.containsKey(r5.getKey()) == true) goto L22;
        this.remoteLanguages.add(r5);
        this.remoteLanguagesDict.put(r5.getKey(), r5);
    L22:
        r22 = r22 + 1;
        goto L7
    L18:
        r42.nameEnglish = r5.nameEnglish;
        r42.name = r5.name;
        r42.baseLangCode = r5.baseLangCode;
        r42.pluralLangCode = r5.pluralLangCode;
        r42.pathToFile = r5.pathToFile;
        r42.serverIndex = r5.serverIndex;
        r5 = r42;
        goto L20
    L14:
        r5.baseLangCode = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L15
    L23:
        int r103 = 0;
    L25:
        if (r103 >= this.remoteLanguages.size()) goto L36;
        LocaleInfo r14 = this.remoteLanguages.get(r103);
        if (r14.serverIndex != Integer.MAX_VALUE) goto L35;
        if (r14 == this.currentLocaleInfo) goto L35;
        if (BuildVars.LOGS_ENABLED == false) goto L34;
        FileLog.d("remove lang " + r14.getKey());
    L34:
        this.remoteLanguages.remove(r103);
        this.remoteLanguagesDict.remove(r14.getKey());
        this.languages.remove(r14);
        this.languagesDict.remove(r14.getKey());
        r103 = r103 - 1;
    L35:
        r103 = r103 + 1;
        goto L25
    L36:
        saveOtherLanguages();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.suggestedLangpack, new Object[0]);
        if (r11 == false) goto L48;
        applyLanguage(this.currentLocaleInfo, true, false, r12);
        return;
    }

    private /* synthetic */ void lambda$loadRemoteLanguages$12(boolean r1, int r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof Vector) == false) goto L6;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda20(this, r3, r1, r2));
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        loadRemoteLanguages(UserConfig.selectedAccount);
    }

    private /* synthetic */ void lambda$new$1() {
        this.currentSystemLocale = getSystemLocaleStringIso639();
    }

    private /* synthetic */ void lambda$saveRemoteLocaleStrings$10(int r5, LocaleInfo r6, TLRPC.TL_langPackDifference r7, HashMap r8, Runnable r9) {
        if (r5 != 0) goto L4;
        r6.version = r7.version;
    L5:
        saveOtherLanguages();
    L12:
        e = move-exception;
        FileLog.e(e);
        this.changingConfiguration = false;
    L34:
        recreateFormatters();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
        if (r9 == null) goto L39;
        r9.run();
        return;
    L39:
        return;
    L7:
        if (this.currentLocaleInfo == r6) goto L10;
        FileLog.d("saveRemoteLocaleStrings: currentLocaleInfo != localeInfo, do nothing");     // Catch: Exception -> L12
        goto L34
    L10:
        if (TextUtils.isEmpty(r6.pluralLangCode) == true) goto L15;
        String[] r72 = r6.pluralLangCode.split("_");     // Catch: Exception -> L12
    L19:
        if (r72.length != 1) goto L21;
        Locale r0 = new Locale(r72[0]);     // Catch: Exception -> L12
    L22:
        this.languageOverride = r6.shortName;     // Catch: Exception -> L12
        SharedPreferences.Editor r73 = MessagesController.getGlobalMainSettings().edit();     // Catch: Exception -> L12
        r73.putString("language", r6.getKey());     // Catch: Exception -> L12
        r73.apply();     // Catch: Exception -> L12
        this.localizationExternal = new Localization.Builder().addLocalization(r8).build();     // Catch: Exception -> L12
        this.localizationExternalSize = calculateTranslatedCount(r8);     // Catch: Exception -> L12
        this.currentLocale = r0;     // Catch: Exception -> L12
        this.currentLocaleInfo = r6;     // Catch: Exception -> L12
        if (TextUtils.isEmpty(r6.pluralLangCode) == true) goto L26;
        this.currentPluralRules = this.allRules.get(this.currentLocaleInfo.pluralLangCode);     // Catch: Exception -> L12
    L26:
        if (this.currentPluralRules != null) goto L30;
        PluralRules r62 = this.allRules.get(this.currentLocale.getLanguage());     // Catch: Exception -> L12
        this.currentPluralRules = r62;     // Catch: Exception -> L12
        if (r62 != null) goto L30;
        this.currentPluralRules = this.allRules.get("en");     // Catch: Exception -> L12
    L30:
        this.changingConfiguration = true;     // Catch: Exception -> L12
        Locale.setDefault(this.currentLocale);     // Catch: Exception -> L12
        Configuration r63 = new Configuration();     // Catch: Exception -> L12
        r63.locale = this.currentLocale;     // Catch: Exception -> L12
        ApplicationLoader.applicationContext.getResources().updateConfiguration(r63, ApplicationLoader.applicationContext.getResources().getDisplayMetrics());     // Catch: Exception -> L12
        this.changingConfiguration = false;     // Catch: Exception -> L12
        RestrictedLanguagesSelectActivity.invalidateRestrictedLanguages();     // Catch: Exception -> L12
        goto L34
    L21:
        r0 = new Locale(r72[0], r72[1]);     // Catch: Exception -> L12
        goto L22
    L15:
        if (TextUtils.isEmpty(r6.baseLangCode) == true) goto L17;
        r72 = r6.baseLangCode.split("_");     // Catch: Exception -> L12
        goto L19
    L17:
        r72 = r6.shortName.split("_");     // Catch: Exception -> L12
        goto L19
    L4:
        r6.baseVersion = r7.version;
        goto L5
    }

    private void loadOtherLanguages() {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("langconfig", 0);
        String r1 = r0.getString("locales", null);
        if (TextUtils.isEmpty(r1) == true) goto L10;
        String[] r12 = r1.split("&");
        int r4 = r12.length;
        int r6 = 0;
    L5:
        if (r6 >= r4) goto L10;
        LocaleInfo r7 = LocaleInfo.createWithString(r12[r6]);
        if (r7 == null) goto L9;
        this.otherLanguages.add(r7);
    L9:
        r6 = r6 + 1;
    L10:
        String r13 = r0.getString("remote", null);
        if (TextUtils.isEmpty(r13) == true) goto L19;
        String[] r14 = r13.split("&");
        int r42 = r14.length;
        int r8 = 0;
    L13:
        if (r8 >= r42) goto L19;
        LocaleInfo r9 = LocaleInfo.createWithString(r14[r8]);
        r9.shortName = r9.shortName.replace("-", "_");
        if (this.remoteLanguagesDict.containsKey(r9.getKey()) == true) goto L18;
        this.remoteLanguages.add(r9);
        this.remoteLanguagesDict.put(r9.getKey(), r9);
    L18:
        r8 = r8 + 1;
    L19:
        String r02 = r0.getString("unofficial", null);
        if (TextUtils.isEmpty(r02) == true) goto L28;
        String[] r03 = r02.split("&");
        int r15 = r03.length;
    L22:
        if (r2 >= r15) goto L38;
        LocaleInfo r3 = LocaleInfo.createWithString(r03[r2]);
        if (r3 == null) goto L27;
        r3.shortName = r3.shortName.replace("-", "_");
        this.unofficialLanguages.add(r3);
    L27:
        r2 = r2 + 1;
        goto L22
    L38:
        return;
    }

    private void mergeExteraLocaleValues(HashMap<String, String> r2, LocaleInfo r3) {
        Iterator<String> r32 = getExteraLocaleAssetPaths(r3).iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r2.putAll(getLocaleAssetStrings(r32.next()));
        goto L4
    }

    public static String nullable(String r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.startsWith("LOC_ERR") == true) goto L9;
        return r1;
    L9:
        return null;
    }

    private String openableLocalizationAsset(String r2) {
        String r1 = "localization_" + r2 + ".bin";
        InputStream r22 = ApplicationLoader.applicationContext.getAssets().open(r1);     // Catch: Exception -> L7
        if (r22 == null) goto L6;
        r22.close();     // Catch: Exception -> L7
    L6:
        return r1;
    L7:
        return null;
    }

    private HashMap<String, String> parseLocaleStrings(InputStream r11, boolean r12) {
        HashMap<String, String> r0 = new HashMap(10000);
        XmlPullParser r1 = Xml.newPullParser();
        r1.setInput(r11, "UTF-8");
        int r112 = r1.getEventType();
        String r3 = null;
        String r4 = null;
        String r5 = null;
    L4:
        if (r112 == 1) goto L37;
        if (r112 != 2) goto L11;
        r4 = r1.getName();
        if (r1.getAttributeCount() <= 0) goto L26;
        r3 = r1.getAttributeValue(0);
    L26:
        if (r4 == null) goto L36;
        if (r4.equals("string") == false) goto L36;
        if (r5 == null) goto L36;
        if (r3 == null) goto L36;
        if (r5.length() == 0) goto L36;
        if (r3.length() == 0) goto L36;
        r0.put(r3, r5);
        r3 = null;
        r4 = null;
        r5 = null;
    L36:
        r112 = r1.next();
        goto L4
    L11:
        if (r112 != 4) goto L24;
        if (r3 == null) goto L26;
        r5 = r1.getText();
        if (r5 == null) goto L26;
        String r113 = r5.trim();
        if (r12 == false) goto L18;
        r5 = r113.replace("<", "&lt;").replace(">", "&gt;").replace("'", "\\'").replace("& ", "&amp; ");
        goto L26
    L18:
        String r114 = r113.replace("\\n", "\n").replace("\\", _UrlKt.FRAGMENT_ENCODE_SET);
        r5 = r114.replace("&lt;", "<");
        if (this.reloadLastFile == true) goto L26;
        if (r5.equals(r114) == true) goto L26;
        this.reloadLastFile = true;
        FileLog.d("getLocaleFileStrings: value != old, reloadLastFile = true;");
        goto L26
    L24:
        if (r112 != 3) goto L26;
        r3 = null;
        r4 = null;
        r5 = null;
        goto L26
    L37:
        return r0;
    }

    private void patched(String r3) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("set as patched " + r3 + " langpack");
    L5:
        MessagesController.getGlobalMainSettings().edit().putBoolean("lngpack_patched_" + r3, true).apply();
    }

    public static void resetImperialSystemType() {
        useImperialSystemType = null;
    }

    private void saveOtherLanguages() {
        int r2 = 0;
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("langconfig", 0).edit();
        StringBuilder r1 = new StringBuilder();
        int r3 = 0;
    L4:
        if (r3 >= this.otherLanguages.size()) goto L12;
        String r4 = this.otherLanguages.get(r3).getSaveString();
        if (r4 == null) goto L11;
        if (r1.length() == 0) goto L10;
        r1.append("&");
    L10:
        r1.append(r4);
    L11:
        r3 = r3 + 1;
        goto L4
    L12:
        r0.putString("locales", r1.toString());
        r1.setLength(0);
        int r32 = 0;
    L14:
        if (r32 >= this.remoteLanguages.size()) goto L22;
        String r42 = this.remoteLanguages.get(r32).getSaveString();
        if (r42 == null) goto L21;
        if (r1.length() == 0) goto L20;
        r1.append("&");
    L20:
        r1.append(r42);
    L21:
        r32 = r32 + 1;
        goto L14
    L22:
        r0.putString("remote", r1.toString());
        r1.setLength(0);
    L24:
        if (r2 >= this.unofficialLanguages.size()) goto L32;
        String r33 = this.unofficialLanguages.get(r2).getSaveString();
        if (r33 == null) goto L31;
        if (r1.length() == 0) goto L30;
        r1.append("&");
    L30:
        r1.append(r33);
    L31:
        r2 = r2 + 1;
        goto L24
    L32:
        r0.putString("unofficial", r1.toString());
        r0.apply();
    }

    private boolean shouldReinstallLangpack(String r6) {
        int r0 = MessagesController.getInstance(UserConfig.selectedAccount).checkResetLangpack;
        if (r0 > 0) goto L6;
        return false;
    L6:
        if (MessagesController.getGlobalMainSettings().getBoolean("lngpack_patched_" + r6, false) == false) goto L8;
        return false;
    L8:
        int r2 = this.localizationExternalSize;
        if (r2 < r0) goto L11;
        return false;
    L11:
        FileLog.e("reinstalling " + r6 + " langpack because of patch (" + r2 + " keys, must be at least " + r0 + ")");
        patched(r6);
        return true;
    }

    public static String stringForMessageListDate(long r6) {
        long r62 = r6 * 1000;
    L23:
        e = move-exception;
        FileLog.e(e);
        return "LOC_ERR";
    L4:
        if (Math.abs(System.currentTimeMillis() - r62) >= 31536000000L) goto L6;
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L23
        int r2 = r0.get(6);     // Catch: Exception -> L23
        r0.setTimeInMillis(r62);     // Catch: Exception -> L23
        int r02 = r0.get(6) - r2;     // Catch: Exception -> L23
        if (r02 == 0) goto L22;
        if (r02 != (-1)) goto L15;
        if ((System.currentTimeMillis() - r62) < 28800000) goto L22;
    L15:
        if (r02 <= (-7)) goto L20;
        if (r02 > (-1)) goto L20;
        return getInstance().getFormatterWeek().format(new Date(r62));
    L20:
        return getInstance().getFormatterDayMonth().format(new Date(r62));
    L22:
        return getInstance().getFormatterDay().format(new Date(r62));
    L6:
        return getInstance().getFormatterYear().format(new Date(r62));
    }

    private String stringForQuantity(int r1) {
        if (r1 != 1) goto L5;
        return "zero";
    L5:
        if (r1 != 2) goto L7;
        return "one";
    L7:
        if (r1 != 4) goto L9;
        return "two";
    L9:
        if (r1 != 8) goto L11;
        return "few";
    L11:
        if (r1 == 16) goto L14;
        return "other";
    L14:
        return "many";
    }

    public int applyLanguage(LocaleInfo r9, boolean r10, boolean r11, int r12) {
        return applyLanguage(r9, r10, r11, false, false, r12, null);
    }

    public int applyLanguage(LocaleInfo r22, boolean r23, boolean r24, boolean r25, boolean r26, int r27, Runnable r28) {
        if (r22 != null) goto L5;
        return 0;
    L5:
        boolean r12 = r22.hasBaseLang();
        File r13 = r22.getPathToFile();
        File r14 = r22.getPathToBaseFile();
        if (r24 == true) goto L9;
        ConnectionsManager.setLangCode(r22.getLangCode());
    L9:
        if (getLanguageFromDict(r22.getKey()) != null) goto L17;
        if (r22.isRemote() == false) goto L14;
        this.remoteLanguages.add(r22);
        this.remoteLanguagesDict.put(r22.getKey(), r22);
        this.languages.add(r22);
        this.languagesDict.put(r22.getKey(), r22);
        saveOtherLanguages();
        goto L17
    L14:
        if (r22.isUnofficial() == false) goto L17;
        this.unofficialLanguages.add(r22);
        this.languagesDict.put(r22.getKey(), r22);
        saveOtherLanguages();
    L17:
        if (r22.isRemote() == false) goto L19;
    L20:
        if (r26 == true) goto L30;
        if (r13.exists() == false) goto L30;
        if (r12 == true) goto L25;
    L27:
        int r3 = 0;
        boolean r4 = false;
        int r17 = 0;
    L104:
    L39:
        e = e;
        int r18 = r3;
    L99:
        FileLog.e(e);
        boolean r6 = false;
        this.changingConfiguration = false;
    L100:
        recreateFormatters();
        if (r26 == false) goto L103;
        MediaDataController.getInstance(r27).loadAttachMenuBots(r6, true);
    L103:
        return r18;
    L37:
        if (TextUtils.isEmpty(r22.pluralLangCode) == true) goto L42;
        String[] r62 = r22.pluralLangCode.split("_");     // Catch: Exception -> L39
    L45:
        r18 = r3;
        if (r62.length != 1) goto L51;
        Locale r32 = new Locale(r62[r17]);     // Catch: Exception -> L49
        String[] r19 = r62;
    L52:
        if (r23 == false) goto L54;
        this.languageOverride = r22.shortName;     // Catch: Exception -> L49
        SharedPreferences.Editor r63 = MessagesController.getGlobalMainSettings().edit();     // Catch: Exception -> L49
        boolean r20 = r12;
        r63.putString("language", r22.getKey());     // Catch: Exception -> L49
        r63.apply();     // Catch: Exception -> L49
    L55:
        if (r13 != null) goto L57;
        this.localizationExternal = Localization.EMPTY;     // Catch: Exception -> L49
        this.localizationExternalSize = r17;     // Catch: Exception -> L49
    L65:
        HashMap<String, String> r64 = new HashMap();     // Catch: Exception -> L49
        mergeExteraLocaleValues(r64, r22);     // Catch: Exception -> L49
        this.localizationExternal = new Localization.Builder().addLocalization(this.localizationExternal).addLocalization(r64).build();     // Catch: Exception -> L49
        this.currentLocale = r32;     // Catch: Exception -> L49
        this.currentLocaleInfo = r22;     // Catch: Exception -> L49
        FileLog.d("applyLanguage: currentLocaleInfo is set");     // Catch: Exception -> L49
        if (TextUtils.isEmpty(this.currentLocaleInfo.pluralLangCode) == true) goto L69;
        this.currentPluralRules = this.allRules.get(this.currentLocaleInfo.pluralLangCode);     // Catch: Exception -> L49
    L69:
        if (this.currentPluralRules != null) goto L72;
        this.currentPluralRules = this.allRules.get(r19[0]);     // Catch: Exception -> L49
    L72:
        if (this.currentPluralRules != null) goto L75;
        this.currentPluralRules = this.allRules.get(this.currentLocale.getLanguage());     // Catch: Exception -> L49
    L75:
        if (this.currentPluralRules != null) goto L77;
        this.currentPluralRules = new PluralRules_None();     // Catch: Exception -> L49
    L77:
        this.changingConfiguration = true;     // Catch: Exception -> L49
        Locale.setDefault(this.currentLocale);     // Catch: Exception -> L49
        Configuration r33 = new Configuration();     // Catch: Exception -> L49
        r33.locale = this.currentLocale;     // Catch: Exception -> L49
        ApplicationLoader.applicationContext.getResources().updateConfiguration(r33, ApplicationLoader.applicationContext.getResources().getDisplayMetrics());     // Catch: Exception -> L49
        this.changingConfiguration = false;     // Catch: Exception -> L49
        FileLog.d("applyLanguage: reloadLastFile=" + this.reloadLastFile + " force=" + r26 + " isLoadingRemote=" + r4);     // Catch: Exception -> L49
        if (this.reloadLastFile == true) goto L84;
        if (r4 == true) goto L91;
        if (r26 == true) goto L91;
        if (shouldReinstallLangpack(r22.shortName) == true) goto L84;
    L91:
        if (r4 == true) goto L98;
        if (r24 == false) goto L94;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda14());     // Catch: Exception -> L49
    L95:
        RestrictedLanguagesSelectActivity.invalidateRestrictedLanguages();     // Catch: Exception -> L49
        if (r28 == null) goto L98;
        r28.run();     // Catch: Exception -> L49
        goto L98
    L94:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);     // Catch: Exception -> L49
    L98:
        r6 = false;
    L84:
        if (BuildVars.LOGS_ENABLED == false) goto L86;
        FileLog.d("reload locale because one of file is corrupted " + r13 + " " + r14 + " reloadLastFile=" + this.reloadLastFile + " isLoadingRemote=" + r4 + " force=" + r26);     // Catch: Exception -> L49
    L86:
        if (r24 == false) goto L89;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda13(this, r27));     // Catch: Exception -> L49
    L90:
        this.reloadLastFile = false;     // Catch: Exception -> L49
        FileLog.d("reloadLastFile=false");     // Catch: Exception -> L49
        goto L91
    L89:
        reloadCurrentRemoteLocale(r27, null, true, null);     // Catch: Exception -> L49
        goto L90
    L57:
        if (r25 == true) goto L65;
        if (r20 == false) goto L60;
        File r65 = r22.getPathToBaseFile();     // Catch: Exception -> L49
    L61:
        HashMap<String, String> r66 = getLocaleFileStrings(r65);     // Catch: Exception -> L49
        if (r20 == false) goto L64;
        r66.putAll(getLocaleFileStrings(r22.getPathToFile()));     // Catch: Exception -> L49
    L64:
        this.localizationExternal = new Localization.Builder().addLocalization(r66).build();     // Catch: Exception -> L49
        this.localizationExternalSize = calculateTranslatedCount(r66);     // Catch: Exception -> L49
        goto L65
    L60:
        r65 = r22.getPathToFile();     // Catch: Exception -> L49
        goto L61
    L54:
        r20 = r12;
        goto L55
    L51:
        r19 = r62;
        r32 = new Locale(r62[r17], r19[1]);     // Catch: Exception -> L49
    L49:
        e = e;
        goto L99
    L42:
        if (TextUtils.isEmpty(r22.baseLangCode) == true) goto L44;
        r62 = r22.baseLangCode.split("_");     // Catch: Exception -> L39
        goto L45
    L44:
        r62 = r22.shortName.split("_");     // Catch: Exception -> L39
        goto L45
    L25:
        if (r14.exists() == true) goto L27;
    L30:
        if (BuildVars.LOGS_ENABLED == false) goto L32;
        FileLog.d("reload locale because one of file doesn't exist " + r13 + " " + r14);
    L32:
        if (r24 == false) goto L34;
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda12(this, r22, r27, r28));
        r3 = 0;
        r17 = 0;
        r4 = true;
        goto L104
    L34:
        r17 = 0;
        r3 = applyRemoteLanguage(r22, null, true, r27, r28);
        r4 = true;
        goto L104
    L19:
        if (r22.isUnofficial() == false) goto L27;
        goto L20
    }

    public boolean applyLanguageFile(File r14, int r15) {
        HashMap<String, String> r5 = getLocaleFileStrings(r14);     // Catch: Exception -> L39
        String r6 = r5.get("LanguageName");     // Catch: Exception -> L39
        String r7 = r5.get("LanguageNameInEnglish");     // Catch: Exception -> L39
        String r8 = r5.get("LanguageCode");     // Catch: Exception -> L39
        if (r6 != null) goto L6;
    L46:
        return false;
    L6:
        if (r6.length() <= 0) goto L46;
        if (r7 == null) goto L46;
        if (r7.length() <= 0) goto L46;
        if (r8 == null) goto L46;
        if (r8.length() <= 0) goto L46;
        if (r6.contains("&") == false) goto L16;
    L44:
        return false;
    L16:
        if (r6.contains("|") == true) goto L44;
        if (r7.contains("&") == true) goto L44;
        if (r7.contains("|") == true) goto L44;
        if (r8.contains("&") == true) goto L44;
        if (r8.contains("|") == true) goto L44;
        if (r8.contains("/") == true) goto L44;
        if (r8.contains("\\") == true) goto L44;
        File r0 = new File(ApplicationLoader.getFilesDirFixed(), r8.concat(".xml"));     // Catch: Exception -> L39
        if (AndroidUtilities.copyFile(r14, r0) == true) goto L35;
        return false;
    L35:
        LocaleInfo r142 = getLanguageFromDict("local_" + r8.toLowerCase());     // Catch: Exception -> L39
        if (r142 != null) goto L38;
        r142 = new LocaleInfo();     // Catch: Exception -> L39
        r142.name = r6;     // Catch: Exception -> L39
        r142.nameEnglish = r7;     // Catch: Exception -> L39
        String r1 = r8.toLowerCase();     // Catch: Exception -> L39
        r142.shortName = r1;     // Catch: Exception -> L39
        r142.pluralLangCode = r1;     // Catch: Exception -> L39
        r142.pathToFile = r0.getAbsolutePath();     // Catch: Exception -> L39
        this.languages.add(r142);     // Catch: Exception -> L39
        this.languagesDict.put(r142.getKey(), r142);     // Catch: Exception -> L39
        this.otherLanguages.add(r142);     // Catch: Exception -> L39
        saveOtherLanguages();     // Catch: Exception -> L39
    L38:
        LocaleInfo r62 = r142;
        this.localizationExternal = new Localization.Builder().addLocalization(r5).build();     // Catch: Exception -> L39
        this.localizationExternalSize = calculateTranslatedCount(r5);     // Catch: Exception -> L39
        applyLanguage(r62, true, false, true, false, r15, null);     // Catch: Exception -> L39
        return true;
    L39:
        e = move-exception;
        FileLog.e(e);
        goto L46
    }

    public int calculateTranslatedCount(HashMap<String, String> r5) {
        HashSet r4 = new HashSet();
        Iterator<String> r52 = r5.keySet().iterator();
        int r1 = 0;
    L4:
        if (r52.hasNext() == false) goto L31;
        String r2 = r52.next();
        if (r2 == null) goto L4;
        if (r2.endsWith("_other") == false) goto L12;
        String r22 = r2.substring(0, r2.length() - 6);
    L26:
        if (r22 == null) goto L27;
        if (r4.contains(r22) == true) goto L4;
        r4.add(r22);
    L27:
        r1 = r1 + 1;
        goto L4
    L12:
        if (r2.endsWith("_zero") == false) goto L14;
    L25:
        r22 = r2.substring(0, r2.length() - 5);
        goto L26
    L14:
        if (r2.endsWith("_many") == true) goto L25;
        if (r2.endsWith("_one") == false) goto L19;
    L24:
        r22 = r2.substring(0, r2.length() - 4);
        goto L26
    L19:
        if (r2.endsWith("_two") == true) goto L24;
        if (r2.endsWith("_few") == true) goto L24;
        r22 = null;
        goto L26
    L31:
        r4.clear();
        return r1;
    }

    public void checkForcePatchLangpack(int r5, Runnable r6) {
        String r0 = getCurrentLanguageName();
        if (MessagesController.getInstance(r5).checkResetLangpack > 0) goto L5;
        return;
    L5:
        if (MessagesController.getGlobalMainSettings().getBoolean("langpack_patched" + r0, false) == false) goto L7;
        return;
    L7:
        if (this.patching == true) goto L12;
        this.patching = true;
        reloadCurrentRemoteLocale(r5, null, true, new LocaleController$$ExternalSyntheticLambda18(this, r0, r6));
        return;
    }

    public void checkPatchLangpack(int r3) {
        LocaleInfo r0 = this.currentLocaleInfo;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.checkingUpdateForCurrentRemoteLocale == false) goto L8;
        return;
    L8:
        if (shouldReinstallLangpack(r0.shortName) == true) goto L10;
        return;
    L10:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("reload locale because locale file is not enough");
    L12:
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda19(this, r3));
    }

    public void checkUpdateForCurrentRemoteLocale(int r10, int r11, int r12) {
        LocaleInfo r2 = this.currentLocaleInfo;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.isRemote() == true) goto L10;
        if (this.currentLocaleInfo.isUnofficial() == true) goto L10;
        return;
    L10:
        if (this.currentLocaleInfo.hasBaseLang() == false) goto L15;
        int r22 = this.currentLocaleInfo.baseVersion;
        LocaleInfo r3 = this.currentLocaleInfo;
        if (r22 >= r12) goto L14;
        FileLog.d("LocaleController checkUpdateForCurrentRemoteLocale base version is out of date, applying (local is " + r3.baseVersion + ", remote is " + r12 + ")");
        this.checkingUpdateForCurrentRemoteLocale = true;
        LocaleInfo r1 = this.currentLocaleInfo;
        applyRemoteLanguage(r1, r1.baseLangCode, false, r10, new LocaleController$$ExternalSyntheticLambda8(this, r10));
        goto L15
    L14:
        FileLog.d("LocaleController checkUpdateForCurrentRemoteLocale base version is up to date (local is " + r3.baseVersion + ", remote is " + r12 + ")");
    L15:
        int r13 = this.currentLocaleInfo.version;
        LocaleInfo r23 = this.currentLocaleInfo;
        if (r13 >= r11) goto L19;
        FileLog.d("LocaleController checkUpdateForCurrentRemoteLocale version is out of date, applying (local is " + r23.version + ", remote is " + r11 + ")");
        this.checkingUpdateForCurrentRemoteLocale = true;
        LocaleInfo r14 = this.currentLocaleInfo;
        applyRemoteLanguage(r14, r14.shortName, false, r10, new LocaleController$$ExternalSyntheticLambda9(this, r10));
        return;
    L19:
        FileLog.d("LocaleController checkUpdateForCurrentRemoteLocale version is up to date (local is " + r23.version + ", remote is " + r11 + ")");
    }

    public boolean deleteLanguage(LocaleInfo r4, int r5) {
        if (r4.pathToFile != null) goto L5;
    L22:
        return false;
    L5:
        if (r4.isRemote() == false) goto L10;
        if (r4.serverIndex != Integer.MAX_VALUE) goto L22;
    L10:
        if (this.currentLocaleInfo == r4) goto L12;
    L20:
        this.unofficialLanguages.remove(r4);
        this.remoteLanguages.remove(r4);
        this.remoteLanguagesDict.remove(r4.getKey());
        this.otherLanguages.remove(r4);
        this.languages.remove(r4);
        this.languagesDict.remove(r4.getKey());
        new File(r4.pathToFile).delete();
        saveOtherLanguages();
        return true;
    L12:
        if (this.systemDefaultLocale.getLanguage() == null) goto L14;
        LocaleInfo r0 = getLanguageFromDict(this.systemDefaultLocale.getLanguage());
    L15:
        if (r0 != null) goto L17;
        r0 = getLanguageFromDict(getLocaleString(this.systemDefaultLocale));
    L17:
        if (r0 != null) goto L19;
        r0 = getLanguageFromDict("en");
    L19:
        applyLanguage(r0, true, false, r5);
        goto L20
    L14:
        r0 = null;
        goto L15
    }

    public String formatCurrencyDecimalString(long r7, String r9, boolean r10) {
        String r6 = r9.toUpperCase();
        long r72 = Math.abs(r7);
        r6.getClass();
        char r0 = 65535;
        switch(r6.hashCode()) {
            case 65726: goto L122;
            case 65759: goto L118;
            case 66267: goto L114;
            case 66813: goto L110;
            case 66823: goto L106;
            case 67122: goto L102;
            case 67712: goto L98;
            case 70719: goto L94;
            case 72732: goto L90;
            case 72777: goto L86;
            case 72801: goto L82;
            case 73631: goto L78;
            case 73683: goto L74;
            case 74532: goto L70;
            case 74704: goto L66;
            case 74840: goto L62;
            case 75863: goto L58;
            case 76263: goto L54;
            case 76618: goto L50;
            case 78388: goto L46;
            case 79710: goto L42;
            case 81569: goto L38;
            case 83210: goto L34;
            case 83974: goto L30;
            case 84517: goto L26;
            case 85132: goto L22;
            case 85367: goto L18;
            case 86653: goto L14;
            case 87087: goto L10;
            case 87118: goto L6;
            default: goto L125;
        };
    L125:
        String r92 = " %.0f";
        switch(r0) {
            case 0: goto L135;
            case 1: goto L134;
            case 2: goto L134;
            case 3: goto L133;
            case 4: goto L134;
            case 5: goto L134;
            case 6: goto L134;
            case 7: goto L134;
            case 8: goto L135;
            case 9: goto L129;
            case 10: goto L134;
            case 11: goto L135;
            case 12: goto L134;
            case 13: goto L134;
            case 14: goto L134;
            case 15: goto L135;
            case 16: goto L135;
            case 17: goto L134;
            case 18: goto L128;
            case 19: goto L135;
            case 20: goto L134;
            case 21: goto L134;
            case 22: goto L135;
            case 23: goto L134;
            case 24: goto L134;
            case 25: goto L134;
            case 26: goto L134;
            case 27: goto L134;
            case 28: goto L134;
            case 29: goto L134;
            default: goto L127;
        };
    L127:
        double r73 = r72 / 100.0d;
        r92 = " %.2f";
    L136:
        Locale r02 = Locale.US;
        if (r10 == true) goto L141;
        r6 = r92;
    L141:
        return String.format(r02, r6, new Object[]{Double.valueOf(r73)}).trim();
    L128:
        r73 = r72 / 10.0d;
        r92 = " %.1f";
        goto L136
    L129:
        double r2 = r72 / 100.0f;
        if ((r72 % 100) != 0) goto L132;
    L131:
        r73 = r2;
        goto L136
    L132:
        r92 = " %.2f";
        goto L131
    L133:
        r73 = r72 / 10000.0d;
        r92 = " %.4f";
        goto L136
    L134:
        r73 = r72;
        goto L136
    L135:
        r73 = r72 / 1000.0d;
        r92 = " %.3f";
        goto L136
    L6:
        if (r6.equals("XPF") == false) goto L125;
        r0 = 29;
        goto L125
    L10:
        if (r6.equals("XOF") == false) goto L125;
        r0 = 28;
        goto L125
    L14:
        if (r6.equals("XAF") == false) goto L125;
        r0 = 27;
        goto L125
    L18:
        if (r6.equals("VUV") == false) goto L125;
        r0 = 26;
        goto L125
    L22:
        if (r6.equals("VND") == false) goto L125;
        r0 = 25;
        goto L125
    L26:
        if (r6.equals("UYI") == false) goto L125;
        r0 = 24;
        goto L125
    L30:
        if (r6.equals("UGX") == false) goto L125;
        r0 = 23;
        goto L125
    L34:
        if (r6.equals("TND") == false) goto L125;
        r0 = 22;
        goto L125
    L38:
        if (r6.equals("RWF") == false) goto L125;
        r0 = 21;
        goto L125
    L42:
        if (r6.equals("PYG") == false) goto L125;
        r0 = 20;
        goto L125
    L46:
        if (r6.equals("OMR") == false) goto L125;
        r0 = 19;
        goto L125
    L50:
        if (r6.equals("MRO") == false) goto L125;
        r0 = 18;
        goto L125
    L54:
        if (r6.equals("MGA") == false) goto L125;
        r0 = 17;
        goto L125
    L58:
        if (r6.equals("LYD") == false) goto L125;
        r0 = 16;
        goto L125
    L62:
        if (r6.equals("KWD") == false) goto L125;
        r0 = 15;
        goto L125
    L66:
        if (r6.equals("KRW") == false) goto L125;
        r0 = 14;
        goto L125
    L70:
        if (r6.equals("KMF") == false) goto L125;
        r0 = '\r';
        goto L125
    L74:
        if (r6.equals("JPY") == false) goto L125;
        r0 = '\f';
        goto L125
    L78:
        if (r6.equals("JOD") == false) goto L125;
        r0 = 11;
        goto L125
    L82:
        if (r6.equals("ISK") == false) goto L125;
        r0 = '\n';
        goto L125
    L86:
        if (r6.equals("IRR") == false) goto L125;
        r0 = '\t';
        goto L125
    L90:
        if (r6.equals("IQD") == false) goto L125;
        r0 = '\b';
        goto L125
    L94:
        if (r6.equals("GNF") == false) goto L125;
        r0 = 7;
        goto L125
    L98:
        if (r6.equals("DJF") == false) goto L125;
        r0 = 6;
        goto L125
    L102:
        if (r6.equals("CVE") == false) goto L125;
        r0 = 5;
        goto L125
    L106:
        if (r6.equals("CLP") == false) goto L125;
        r0 = 4;
        goto L125
    L110:
        if (r6.equals("CLF") == false) goto L125;
        r0 = 3;
        goto L125
    L114:
        if (r6.equals("BYR") == false) goto L125;
        r0 = 2;
        goto L125
    L118:
        if (r6.equals("BIF") == false) goto L125;
        r0 = 1;
        goto L125
    L122:
        if (r6.equals("BHD") == false) goto L125;
        r0 = 0;
        goto L125
    }

    public String formatCurrencyString(long r8, String r10) {
        return formatCurrencyString(r8, true, true, false, r10);
    }

    public String formatCurrencyString(long r18, boolean r20, boolean r21, boolean r22, String r23) {
        String r2 = r23.toUpperCase();
        char r5 = 1;
        if (r18 >= 0) goto L5;
        boolean r7 = true;
    L6:
        long r8 = Math.abs(r18);
        Currency r0 = Currency.getInstance(r2);     // Catch: Exception -> L9
    L11:
        r2.getClass();
        switch(r2.hashCode()) {
            case 65726: goto L134;
            case 65759: goto L131;
            case 66267: goto L127;
            case 66813: goto L123;
            case 66823: goto L119;
            case 67122: goto L115;
            case 67712: goto L111;
            case 70719: goto L107;
            case 72732: goto L103;
            case 72777: goto L99;
            case 72801: goto L95;
            case 73631: goto L91;
            case 73683: goto L87;
            case 74532: goto L83;
            case 74704: goto L79;
            case 74840: goto L75;
            case 75863: goto L71;
            case 76263: goto L67;
            case 76618: goto L63;
            case 78388: goto L59;
            case 79710: goto L55;
            case 81569: goto L51;
            case 83210: goto L47;
            case 83974: goto L43;
            case 84517: goto L39;
            case 85132: goto L35;
            case 85367: goto L31;
            case 86653: goto L27;
            case 87087: goto L23;
            case 87118: goto L19;
            case 87254: goto L15;
            default: goto L13;
        };
    L13:
        r5 = 65535;
    L137:
        String r10 = " %.2f";
        String r12 = " %.0f";
        switch(r5) {
            case 0: goto L149;
            case 1: goto L140;
            case 2: goto L140;
            case 3: goto L148;
            case 4: goto L140;
            case 5: goto L140;
            case 6: goto L140;
            case 7: goto L140;
            case 8: goto L149;
            case 9: goto L142;
            case 10: goto L140;
            case 11: goto L149;
            case 12: goto L140;
            case 13: goto L140;
            case 14: goto L140;
            case 15: goto L149;
            case 16: goto L149;
            case 17: goto L140;
            case 18: goto L141;
            case 19: goto L149;
            case 20: goto L140;
            case 21: goto L140;
            case 22: goto L149;
            case 23: goto L140;
            case 24: goto L140;
            case 25: goto L140;
            case 26: goto L140;
            case 27: goto L140;
            case 28: goto L140;
            case 29: goto L140;
            case 30: goto L140;
            default: goto L139;
        };
    L139:
        double r3 = r8 / 100.0d;
    L150:
        if (r21 == false) goto L153;
        r12 = r10;
    L153:
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == null) goto L178;
        Locale r9 = this.currentLocale;
        if (r9 != null) goto L159;
        r9 = this.systemDefaultLocale;
    L159:
        NumberFormat r1 = NumberFormat.getCurrencyInstance(r9);
        r1.setCurrency(r0);
        if (r22 == false) goto L162;
        r1.setGroupingUsed(false);
    L162:
        if (r21 == false) goto L166;
        if (r20 == true) goto L165;
    L167:
        StringBuilder r02 = new StringBuilder();
        if (r7 == false) goto L170;
        r52 = "-";
    L170:
        r02.append(r52);
        r02.append(r1.format(r3));
        String r03 = r02.toString();
        int r13 = r03.indexOf(r2);
        if (r13 < 0) goto L184;
        int r14 = r13 + r2.length();
        if (r14 < r03.length()) goto L175;
        return r03;
    L175:
        if (r03.charAt(r14) != ' ') goto L177;
        return r03;
    L177:
        return r03.substring(0, r14) + " " + r03.substring(r14);
    L184:
        return r03;
    L165:
        if (r2.equals("IRR") == false) goto L167;
    L166:
        r1.setMaximumFractionDigits(0);
        goto L167
    L178:
        if (r7 == false) goto L181;
        r52 = "-";
    L181:
        return r52.concat(String.format(Locale.US, r2.concat(r12), new Object[]{Double.valueOf(r3)}));
    L140:
        r3 = r8;
        r10 = " %.0f";
        goto L150
    L141:
        r3 = r8 / 10.0d;
        r10 = " %.1f";
        goto L150
    L142:
        double r132 = r8 / 100.0f;
        if (r20 == true) goto L145;
    L147:
        r3 = r132;
        goto L150
    L145:
        if ((r8 % 100) != 0) goto L147;
        r10 = " %.0f";
        goto L147
    L148:
        r3 = r8 / 10000.0d;
        r10 = " %.4f";
        goto L150
    L149:
        r3 = r8 / 1000.0d;
        r10 = " %.3f";
        goto L150
    L15:
        if (r2.equals("XTR") == false) goto L13;
        r5 = 30;
        goto L137
    L19:
        if (r2.equals("XPF") == false) goto L13;
        r5 = 29;
        goto L137
    L23:
        if (r2.equals("XOF") == false) goto L13;
        r5 = 28;
        goto L137
    L27:
        if (r2.equals("XAF") == false) goto L13;
        r5 = 27;
        goto L137
    L31:
        if (r2.equals("VUV") == false) goto L13;
        r5 = 26;
        goto L137
    L35:
        if (r2.equals("VND") == false) goto L13;
        r5 = 25;
        goto L137
    L39:
        if (r2.equals("UYI") == false) goto L13;
        r5 = 24;
        goto L137
    L43:
        if (r2.equals("UGX") == false) goto L13;
        r5 = 23;
        goto L137
    L47:
        if (r2.equals("TND") == false) goto L13;
        r5 = 22;
        goto L137
    L51:
        if (r2.equals("RWF") == false) goto L13;
        r5 = 21;
        goto L137
    L55:
        if (r2.equals("PYG") == false) goto L13;
        r5 = 20;
        goto L137
    L59:
        if (r2.equals("OMR") == false) goto L13;
        r5 = 19;
        goto L137
    L63:
        if (r2.equals("MRO") == false) goto L13;
        r5 = 18;
        goto L137
    L67:
        if (r2.equals("MGA") == false) goto L13;
        r5 = 17;
        goto L137
    L71:
        if (r2.equals("LYD") == false) goto L13;
        r5 = 16;
        goto L137
    L75:
        if (r2.equals("KWD") == false) goto L13;
        r5 = 15;
        goto L137
    L79:
        if (r2.equals("KRW") == false) goto L13;
        r5 = 14;
        goto L137
    L83:
        if (r2.equals("KMF") == false) goto L13;
        r5 = '\r';
        goto L137
    L87:
        if (r2.equals("JPY") == false) goto L13;
        r5 = '\f';
        goto L137
    L91:
        if (r2.equals("JOD") == false) goto L13;
        r5 = 11;
        goto L137
    L95:
        if (r2.equals("ISK") == false) goto L13;
        r5 = '\n';
        goto L137
    L99:
        if (r2.equals("IRR") == false) goto L13;
        r5 = '\t';
        goto L137
    L103:
        if (r2.equals("IQD") == false) goto L13;
        r5 = '\b';
        goto L137
    L107:
        if (r2.equals("GNF") == false) goto L13;
        r5 = 7;
        goto L137
    L111:
        if (r2.equals("DJF") == false) goto L13;
        r5 = 6;
        goto L137
    L115:
        if (r2.equals("CVE") == false) goto L13;
        r5 = 5;
        goto L137
    L119:
        if (r2.equals("CLP") == false) goto L13;
        r5 = 4;
        goto L137
    L123:
        if (r2.equals("CLF") == false) goto L13;
        r5 = 3;
        goto L137
    L127:
        if (r2.equals("BYR") == false) goto L13;
        r5 = 2;
        goto L137
    L131:
        if (r2.equals("BIF") == true) goto L137;
    L134:
        if (r2.equals("BHD") == false) goto L13;
        r5 = 0;
    L9:
        e = move-exception;
        FileLog.e(e);
        r0 = null;
        goto L11
    L5:
        r7 = false;
        goto L6
    }

    public LocaleInfo getBuiltinLanguageByPlural(String r4) {
        Iterator<LocaleInfo> r3 = this.languagesDict.values().iterator();
    L4:
        if (r3.hasNext() == false) goto L18;
        LocaleInfo r0 = r3.next();
        String r1 = r0.pathToFile;
        if (r1 == null) goto L4;
        if (r1.equals("remote") == false) goto L4;
        String r12 = r0.shortName;
        if (r12 == null) goto L13;
        if (r12.endsWith("_raw") == true) goto L4;
    L13:
        String r13 = r0.pluralLangCode;
        if (r13 == null) goto L4;
        if (r13.equals(r4) == false) goto L4;
        return r0;
    L18:
        return null;
    }

    public FastDateFormat getChatDate() {
        if (this.chatDate != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.chatDate != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.chatDate = createFormatter(r0, getStringInternal("chatDate", R.string.chatDate), "d MMMM");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.chatDate;
    }

    public FastDateFormat getChatDateShort() {
        if (this.chatDateShort != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.chatDateShort != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.chatDateShort = createFormatter(r0, getStringInternal("chatDateShort", R.string.chatDateShort), "d MMM");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.chatDateShort;
    }

    public FastDateFormat getChatFullDate() {
        if (this.chatFullDate != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.chatFullDate != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.chatFullDate = createFormatter(r0, getStringInternal("chatFullDate", R.string.chatFullDate), "d MMMM yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.chatFullDate;
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public LocaleInfo getCurrentLocaleInfo() {
        return this.currentLocaleInfo;
    }

    public FastDateFormat getExportFileFormatter() {
        if (this.exportFileFormatter != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.exportFileFormatter != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.exportFileFormatter = createFormatter(r0, null, "dd-MM-yyyy_HH-mm");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.exportFileFormatter;
    }

    public FastDateFormat getExportFullDateFormatter() {
        if (this.exportFullDateFormatter != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.exportFullDateFormatter != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.exportFullDateFormatter = createFormatter(r0, null, "dd MMM yyyy HH:mm:ss");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.exportFullDateFormatter;
    }

    public FastDateFormat getFormatterBannedUntil() {
        if (this.formatterBannedUntil != null) goto L27;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterBannedUntil != null) goto L22;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L13;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L13:
        if (is24HourFormat == false) goto L16;
        String r1 = "formatterBannedUntil24H";
        int r2 = R.string.formatterBannedUntil24H;     // Catch: Throwable -> L10
    L15:
        String r12 = getStringInternal(r1, r2);     // Catch: Throwable -> L10
        if (is24HourFormat == false) goto L20;
        String r22 = "MMM dd yyyy, HH:mm";
    L21:
        this.formatterBannedUntil = createFormatter(r0, r12, r22);     // Catch: Throwable -> L10
        goto L22
    L20:
        r22 = "MMM dd yyyy, h:mm a";
        goto L21
    L16:
        r1 = "formatterBannedUntil12H";
        r2 = R.string.formatterBannedUntil12H;     // Catch: Throwable -> L10
    L22:
        monitor-exit(this);     // Catch: Throwable -> L10
    L27:
        return this.formatterBannedUntil;
    }

    public FastDateFormat getFormatterBannedUntilThisYear() {
        if (this.formatterBannedUntilThisYear != null) goto L27;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterBannedUntilThisYear != null) goto L22;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L13;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L13:
        if (is24HourFormat == false) goto L16;
        String r1 = "formatterBannedUntilThisYear24H";
        int r2 = R.string.formatterBannedUntilThisYear24H;     // Catch: Throwable -> L10
    L15:
        String r12 = getStringInternal(r1, r2);     // Catch: Throwable -> L10
        if (is24HourFormat == false) goto L20;
        String r22 = "MMM dd, HH:mm";
    L21:
        this.formatterBannedUntilThisYear = createFormatter(r0, r12, r22);     // Catch: Throwable -> L10
        goto L22
    L20:
        r22 = "MMM dd, h:mm a";
        goto L21
    L16:
        r1 = "formatterBannedUntilThisYear12H";
        r2 = R.string.formatterBannedUntilThisYear12H;     // Catch: Throwable -> L10
    L22:
        monitor-exit(this);     // Catch: Throwable -> L10
    L27:
        return this.formatterBannedUntilThisYear;
    }

    public FastDateFormat getFormatterBoostExpired() {
        if (this.formatterBoostExpired != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterBoostExpired != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterBoostExpired = createFormatter(r0, getStringInternal("formatterBoostExpired", R.string.formatterBoostExpired), "MMM dd, yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterBoostExpired;
    }

    public FastDateFormat getFormatterConstDay() {
        if (this.formatterConstDay != null) goto L34;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterConstDay != null) goto L29;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        String r1 = r0.getLanguage();     // Catch: Throwable -> L10
        if (r1 != null) goto L15;
        r1 = "en";
    L15:
        String r12 = r1.toLowerCase();     // Catch: Throwable -> L10
        if (r12.toLowerCase().equals("ar") == false) goto L18;
    L21:
        boolean r13 = is24HourFormat;     // Catch: Throwable -> L10
        if (r13 == false) goto L24;
        String r2 = "HH:mm";
    L25:
        if (r13 == false) goto L27;
        String r14 = "HH:mm";
    L28:
        this.formatterConstDay = createFormatter(r0, r2, r14);     // Catch: Throwable -> L10
        goto L29
    L27:
        r14 = "h:mm a";
        goto L28
    L24:
        r2 = "h:mm a";
        goto L25
    L18:
        if (r12.toLowerCase().equals("ko") == true) goto L21;
        r0 = Locale.US;     // Catch: Throwable -> L10
    L29:
        monitor-exit(this);     // Catch: Throwable -> L10
    L34:
        return this.formatterConstDay;
    }

    public FastDateFormat getFormatterDay() {
        if (this.formatterDay != null) goto L45;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterDay != null) goto L40;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        String r1 = r0.getLanguage().toLowerCase();     // Catch: Throwable -> L10
        if (r1.equalsIgnoreCase("ar") == true) goto L19;
        if (r1.equalsIgnoreCase("ko") == true) goto L19;
        r0 = Locale.US;     // Catch: Throwable -> L10
    L19:
        if (is24HourFormat == false) goto L26;
        if (ExteraConfig.getFormatTimeWithSeconds() == false) goto L23;
        String r12 = "formatterDay24HSec";
        int r2 = R.string.formatterDay24HSec;     // Catch: Throwable -> L10
    L24:
        String r13 = getStringInternal(r12, r2);     // Catch: Throwable -> L10
        if (is24HourFormat == false) goto L36;
        if (ExteraConfig.getFormatTimeWithSeconds() == false) goto L34;
        String r22 = "HH:mm:ss";
    L39:
        this.formatterDay = createFormatter(r0, r13, r22);     // Catch: Throwable -> L10
        goto L40
    L34:
        r22 = "HH:mm";
        goto L39
    L36:
        if (ExteraConfig.getFormatTimeWithSeconds() == false) goto L38;
        r22 = "h:mm:ss a";
        goto L39
    L38:
        r22 = "h:mm a";
        goto L39
    L23:
        r12 = "formatterDay24H";
        r2 = R.string.formatterDay24H;     // Catch: Throwable -> L10
        goto L24
    L26:
        if (ExteraConfig.getFormatTimeWithSeconds() == false) goto L28;
        r12 = "formatterDay12HSec";
        r2 = R.string.formatterDay12HSec;     // Catch: Throwable -> L10
        goto L24
    L28:
        r12 = "formatterDay12H";
        r2 = R.string.formatterDay12H;     // Catch: Throwable -> L10
    L40:
        monitor-exit(this);     // Catch: Throwable -> L10
    L45:
        return this.formatterDay;
    }

    public FastDateFormat getFormatterDayMonth() {
        if (this.formatterDayMonth != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterDayMonth != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterDayMonth = createFormatter(r0, getStringInternal("formatterMonth", R.string.formatterMonth), "dd MMM");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterDayMonth;
    }

    public FastDateFormat getFormatterDayWithSeconds() {
        if (this.formatterDayWithSeconds != null) goto L33;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterDayWithSeconds != null) goto L28;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        String r1 = r0.getLanguage().toLowerCase();     // Catch: Throwable -> L10
        if (r1.equalsIgnoreCase("ar") == true) goto L19;
        if (r1.equalsIgnoreCase("ko") == true) goto L19;
        r0 = Locale.US;     // Catch: Throwable -> L10
    L19:
        if (is24HourFormat == false) goto L22;
        String r12 = "formatterDayWithSeconds24H";
        int r2 = R.string.formatterDayWithSeconds24H;     // Catch: Throwable -> L10
    L21:
        String r13 = getStringInternal(r12, r2);     // Catch: Throwable -> L10
        if (is24HourFormat == false) goto L26;
        String r22 = "HH:mm:ss";
    L27:
        this.formatterDayWithSeconds = createFormatter(r0, r13, r22);     // Catch: Throwable -> L10
        goto L28
    L26:
        r22 = "h:mm:ss a";
        goto L27
    L22:
        r12 = "formatterDayWithSeconds12H";
        r2 = R.string.formatterDayWithSeconds12H;     // Catch: Throwable -> L10
    L28:
        monitor-exit(this);     // Catch: Throwable -> L10
    L33:
        return this.formatterDayWithSeconds;
    }

    public FastDateFormat getFormatterGiveawayCard() {
        if (this.formatterGiveawayCard != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterGiveawayCard != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterGiveawayCard = createFormatter(r0, getStringInternal("formatterGiveawayCard", R.string.formatterGiveawayCard), "dd MMM yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterGiveawayCard;
    }

    public FastDateFormat getFormatterGiveawayMonthDay() {
        if (this.formatterGiveawayMonthDay != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterGiveawayMonthDay != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterGiveawayMonthDay = createFormatter(r0, getStringInternal("formatterGiveawayMonthDay", R.string.formatterGiveawayMonthDay), "MMMM dd");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterGiveawayMonthDay;
    }

    public FastDateFormat getFormatterGiveawayMonthDayYear() {
        if (this.formatterGiveawayMonthDayYear != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterGiveawayMonthDayYear != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterGiveawayMonthDayYear = createFormatter(r0, getStringInternal("formatterGiveawayMonthDayYear", R.string.formatterGiveawayMonthDayYear), "MMMM dd, yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterGiveawayMonthDayYear;
    }

    public FastDateFormat getFormatterMonthOnly() {
        if (this.formatterMonthOnly != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterMonthOnly != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterMonthOnly = createFormatter(r0, getStringInternal("formatterMonthOnly", R.string.formatterMonthOnly), "MMMM");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterMonthOnly;
    }

    public FastDateFormat getFormatterMonthYear() {
        if (this.formatterMonthYear != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterMonthYear != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterMonthYear = createFormatter(r0, getStringInternal("formatterMonthYear", R.string.formatterMonthYear), "MMM yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterMonthYear;
    }

    public FastDateFormat getFormatterScheduleDay() {
        if (this.formatterScheduleDay != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterScheduleDay != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterScheduleDay = createFormatter(r0, getStringInternal("formatDateSchedule", R.string.formatDateSchedule), "MMM d");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterScheduleDay;
    }

    public FastDateFormat getFormatterScheduleSend(int r5) {
        if (r5 < 0) goto L33;
        FastDateFormat[] r0 = this.formatterScheduleSend;
        if (r5 < r0.length) goto L7;
        return null;
    L7:
        if (r0[r5] != null) goto L32;
        Locale r02 = this.currentLocale;
        if (r02 != null) goto L11;
        r02 = Locale.getDefault();
    L11:
        switch(r5) {
            case 0: goto L30;
            case 1: goto L29;
            case 2: goto L28;
            case 3: goto L27;
            case 4: goto L26;
            case 5: goto L25;
            case 6: goto L24;
            case 7: goto L23;
            case 8: goto L22;
            case 9: goto L21;
            case 10: goto L20;
            case 11: goto L19;
            case 12: goto L18;
            case 13: goto L17;
            case 14: goto L16;
            case 15: goto L15;
            case 16: goto L14;
            case 17: goto L13;
            default: goto L32;
        };
    L13:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("PublishDayYearAt", R.string.PublishDayYearAt), "'Publish on' MMM d yyyy 'at' HH:mm");
        goto L32
    L14:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("PublishDayAt", R.string.PublishDayAt), "'Publish on' MMM d 'at' HH:mm");
        goto L32
    L15:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("PublishTodayAt", R.string.PublishTodayAt), "'Publish today at' HH:mm");
        goto L32
    L16:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartsDayYearAt", R.string.StartsDayYearAt), "'Starts on' MMM d yyyy 'at' HH:mm");
        goto L32
    L17:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartsDayAt", R.string.StartsDayAt), "'Starts on' MMM d 'at' HH:mm");
        goto L32
    L18:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartsTodayAt", R.string.StartsTodayAt), "'Starts today at' HH:mm");
        goto L32
    L19:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartShortDayYearAt", R.string.StartShortDayYearAt), "MMM d yyyy, HH:mm");
        goto L32
    L20:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartShortDayAt", R.string.StartShortDayAt), "MMM d',' HH:mm");
        goto L32
    L21:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartShortTodayAt", R.string.StartShortTodayAt), "'Today,' HH:mm");
        goto L32
    L22:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartDayYearAt", R.string.StartDayYearAt), "'Start on' MMM d yyyy 'at' HH:mm");
        goto L32
    L23:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartDayAt", R.string.StartDayAt), "'Start on' MMM d 'at' HH:mm");
        goto L32
    L24:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("StartTodayAt", R.string.StartTodayAt), "'Start today at' HH:mm");
        goto L32
    L25:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("RemindDayYearAt", R.string.RemindDayYearAt), "'Remind on' MMM d yyyy 'at' HH:mm");
        goto L32
    L26:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("RemindDayAt", R.string.RemindDayAt), "'Remind on' MMM d 'at' HH:mm");
        goto L32
    L27:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("RemindTodayAt", R.string.RemindTodayAt), "'Remind today at' HH:mm");
        goto L32
    L28:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("SendDayYearAt", R.string.SendDayYearAt), "'Send on' MMM d yyyy 'at' HH:mm");
        goto L32
    L29:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("SendDayAt", R.string.SendDayAt), "'Send on' MMM d 'at' HH:mm");
        goto L32
    L30:
        this.formatterScheduleSend[r5] = createFormatter(r02, getStringInternal("SendTodayAt", R.string.SendTodayAt), "'Send today at' HH:mm");
    L32:
        return this.formatterScheduleSend[r5];
    L33:
        return null;
    }

    public FastDateFormat getFormatterScheduleYear() {
        if (this.formatterScheduleYear != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterScheduleYear != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterScheduleYear = createFormatter(r0, getStringInternal("formatDateScheduleYear", R.string.formatDateScheduleYear), "MMM d yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterScheduleYear;
    }

    public FastDateFormat getFormatterStats() {
        if (this.formatterStats != null) goto L27;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterStats != null) goto L22;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L13;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L13:
        if (is24HourFormat == false) goto L16;
        String r1 = "formatterStats24H";
        int r2 = R.string.formatterStats24H;     // Catch: Throwable -> L10
    L15:
        String r12 = getStringInternal(r1, r2);     // Catch: Throwable -> L10
        if (is24HourFormat == false) goto L20;
        String r22 = "MMM dd yyyy, HH:mm";
    L21:
        this.formatterStats = createFormatter(r0, r12, r22);     // Catch: Throwable -> L10
        goto L22
    L20:
        r22 = "MMM dd yyyy, h:mm a";
        goto L21
    L16:
        r1 = "formatterStats12H";
        r2 = R.string.formatterStats12H;     // Catch: Throwable -> L10
    L22:
        monitor-exit(this);     // Catch: Throwable -> L10
    L27:
        return this.formatterStats;
    }

    public FastDateFormat getFormatterWeek() {
        if (this.formatterWeek != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterWeek != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterWeek = createFormatter(r0, getStringInternal("formatterWeek", R.string.formatterWeek), "EEE");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterWeek;
    }

    public FastDateFormat getFormatterWeekLong() {
        if (this.formatterWeekLong != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterWeekLong != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterWeekLong = createFormatter(r0, getStringInternal("formatterWeekLong", R.string.formatterWeekLong), "EEEE");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterWeekLong;
    }

    public FastDateFormat getFormatterYear() {
        if (this.formatterYear != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterYear != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterYear = createFormatter(r0, getStringInternal("formatterYear", R.string.formatterYear), "dd.MM.yy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterYear;
    }

    public FastDateFormat getFormatterYearMax() {
        if (this.formatterYearMax != null) goto L18;
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.formatterYearMax != null) goto L13;
        Locale r0 = this.currentLocale;     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = Locale.getDefault();     // Catch: Throwable -> L10
    L12:
        this.formatterYearMax = createFormatter(r0, getStringInternal("formatterYearMax", R.string.formatterYearMax), "dd.MM.yyyy");     // Catch: Throwable -> L10
    L13:
        monitor-exit(this);     // Catch: Throwable -> L10
    L18:
        return this.formatterYearMax;
    }

    public LocaleInfo getLanguageFromDict(String r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        return this.languagesDict.get(r3.toLowerCase().replace("-", "_"));
    }

    public Locale getSystemDefaultLocale() {
        return this.systemDefaultLocale;
    }

    public String getTranslitString(String r3) {
        return getTranslitString(r3, true, false);
    }

    public String getTranslitString(String r2, boolean r3) {
        return getTranslitString(r2, true, r3);
    }

    public String getTranslitString(String r38, boolean r39, boolean r40) {
        if (r38 != null) goto L6;
        return null;
    L6:
        if (this.ruTranslitChars != null) goto L8;
        HashMap<String, String> r10 = new HashMap(33);
        this.ruTranslitChars = r10;
        r10.put("а", "a");
        this.ruTranslitChars.put("б", "b");
        this.ruTranslitChars.put("в", RegisterSpec.PREFIX);
        this.ruTranslitChars.put("г", ImageLoader.AUTOPLAY_FILTER);
        this.ruTranslitChars.put("д", "d");
        this.ruTranslitChars.put("е", "e");
        HashMap<String, String> r6 = this.ruTranslitChars;
        String r24 = ImageLoader.AUTOPLAY_FILTER;
        r6.put("ё", "yo");
        this.ruTranslitChars.put("ж", "zh");
        this.ruTranslitChars.put("з", "z");
        this.ruTranslitChars.put("и", "i");
        this.ruTranslitChars.put("й", "i");
        this.ruTranslitChars.put("к", "k");
        this.ruTranslitChars.put("л", "l");
        this.ruTranslitChars.put("м", "m");
        this.ruTranslitChars.put("н", "n");
        this.ruTranslitChars.put("о", "o");
        this.ruTranslitChars.put("п", "p");
        String r23 = "m";
        String r5 = "r";
        this.ruTranslitChars.put("р", r5);
        String r17 = "z";
        this.ruTranslitChars.put("с", "s");
        HashMap<String, String> r62 = this.ruTranslitChars;
        String r22 = RegisterSpec.PREFIX;
        r62.put("т", "t");
        String r7 = "u";
        this.ruTranslitChars.put("у", r7);
        String r20 = "s";
        this.ruTranslitChars.put("ф", "f");
        String r11 = "h";
        this.ruTranslitChars.put("х", r11);
        String r18 = "p";
        this.ruTranslitChars.put("ц", HlsSegmentFormat.TS);
        this.ruTranslitChars.put("ч", "ch");
        this.ruTranslitChars.put("ш", "sh");
        this.ruTranslitChars.put("щ", "sch");
        this.ruTranslitChars.put("ы", "i");
        this.ruTranslitChars.put("ь", _UrlKt.FRAGMENT_ENCODE_SET);
        this.ruTranslitChars.put("ъ", _UrlKt.FRAGMENT_ENCODE_SET);
        this.ruTranslitChars.put("э", "e");
        this.ruTranslitChars.put("ю", "yu");
        this.ruTranslitChars.put("я", "ya");
    L10:
        if (this.translitChars != null) goto L12;
        HashMap<String, String> r63 = new HashMap(488);
        this.translitChars = r63;
        r63.put("ȼ", "c");
        this.translitChars.put("ᶇ", "n");
        this.translitChars.put("ɖ", "d");
        this.translitChars.put("ỿ", "y");
        this.translitChars.put("ᴓ", "o");
        this.translitChars.put("ø", "o");
        this.translitChars.put("ḁ", "a");
        this.translitChars.put("ʯ", r11);
        this.translitChars.put("ŷ", "y");
        this.translitChars.put("ʞ", "k");
        this.translitChars.put("ừ", r7);
        String r26 = r7;
        this.translitChars.put("ꜳ", "aa");
        this.translitChars.put("ĳ", "ij");
        this.translitChars.put("ḽ", "l");
        this.translitChars.put("ɪ", "i");
        this.translitChars.put("ḇ", "b");
        this.translitChars.put("ʀ", r5);
        this.translitChars.put("ě", "e");
        this.translitChars.put("ﬃ", "ffi");
        this.translitChars.put("ơ", "o");
        this.translitChars.put("ⱹ", r5);
        this.translitChars.put("ồ", "o");
        this.translitChars.put("ǐ", "i");
        String r102 = r18;
        this.translitChars.put("ꝕ", r102);
        this.translitChars.put("ý", "y");
        this.translitChars.put("ḝ", "e");
        this.translitChars.put("ₒ", "o");
        this.translitChars.put("ⱥ", "a");
        this.translitChars.put("ʙ", "b");
        this.translitChars.put("ḛ", "e");
        this.translitChars.put("ƈ", "c");
        this.translitChars.put("ɦ", r11);
        this.translitChars.put("ᵬ", "b");
        String r182 = r11;
        String r112 = r20;
        this.translitChars.put("ṣ", r112);
        this.translitChars.put("đ", "d");
        this.translitChars.put("ỗ", "o");
        this.translitChars.put("ɟ", "j");
        this.translitChars.put("ẚ", "a");
        this.translitChars.put("ɏ", "y");
        this.translitChars.put("ʌ", r22);
        this.translitChars.put("ꝓ", r102);
        this.translitChars.put("ﬁ", "fi");
        this.translitChars.put("ᶄ", "k");
        this.translitChars.put("ḏ", "d");
        this.translitChars.put("ᴌ", "l");
        this.translitChars.put("ė", "e");
        this.translitChars.put("ᴋ", "k");
        this.translitChars.put("ċ", "c");
        this.translitChars.put("ʁ", r5);
        this.translitChars.put("ƕ", "hv");
        this.translitChars.put("ƀ", "b");
        this.translitChars.put("ṍ", "o");
        this.translitChars.put("ȣ", "ou");
        this.translitChars.put("ǰ", "j");
        String r14 = r24;
        this.translitChars.put("ᶃ", r14);
        this.translitChars.put("ṋ", "n");
        this.translitChars.put("ɉ", "j");
        this.translitChars.put("ǧ", r14);
        this.translitChars.put("ǳ", "dz");
        String r1 = r17;
        this.translitChars.put("ź", r1);
        this.translitChars.put("ꜷ", "au");
        this.translitChars.put("ǖ", r26);
        this.translitChars.put("ᵹ", r14);
        this.translitChars.put("ȯ", "o");
        this.translitChars.put("ɐ", "a");
        this.translitChars.put("ą", "a");
        this.translitChars.put("õ", "o");
        this.translitChars.put("ɻ", r5);
        this.translitChars.put("ꝍ", "o");
        this.translitChars.put("ǟ", "a");
        this.translitChars.put("ȴ", "l");
        this.translitChars.put("ʂ", r112);
        this.translitChars.put("ﬂ", "fl");
        this.translitChars.put("ȉ", "i");
        this.translitChars.put("ⱻ", "e");
        this.translitChars.put("ṉ", "n");
        this.translitChars.put("ï", "i");
        this.translitChars.put("ñ", "n");
        this.translitChars.put("ᴉ", "i");
        this.translitChars.put("ʇ", "t");
        this.translitChars.put("ẓ", r1);
        this.translitChars.put("ỷ", "y");
        this.translitChars.put("ȳ", "y");
        this.translitChars.put("ṩ", r112);
        this.translitChars.put("ɽ", r5);
        this.translitChars.put("ĝ", r14);
        this.translitChars.put("ᴝ", r26);
        this.translitChars.put("ḳ", "k");
        this.translitChars.put("ꝫ", "et");
        this.translitChars.put("ī", "i");
        this.translitChars.put("ť", "t");
        this.translitChars.put("ꜿ", "c");
        this.translitChars.put("ʟ", "l");
        this.translitChars.put("ꜹ", "av");
        this.translitChars.put("û", r26);
        this.translitChars.put("æ", "ae");
        this.translitChars.put("ă", "a");
        this.translitChars.put("ǘ", r26);
        this.translitChars.put("ꞅ", r112);
        this.translitChars.put("ᵣ", r5);
        this.translitChars.put("ᴀ", "a");
        this.translitChars.put("ƃ", "b");
        this.translitChars.put("ḩ", r182);
        this.translitChars.put("ṧ", r112);
        this.translitChars.put("ₑ", "e");
        this.translitChars.put("ʜ", r182);
        this.translitChars.put("ẋ", "x");
        this.translitChars.put("ꝅ", "k");
        this.translitChars.put("ḋ", "d");
        this.translitChars.put("ƣ", "oi");
        this.translitChars.put("ꝑ", r102);
        this.translitChars.put("ħ", r182);
        String r3 = r22;
        this.translitChars.put("ⱴ", r3);
        this.translitChars.put("ẇ", "w");
        this.translitChars.put("ǹ", "n");
        String r9 = r23;
        this.translitChars.put("ɯ", r9);
        this.translitChars.put("ɡ", r14);
        this.translitChars.put("ɴ", "n");
        this.translitChars.put("ᴘ", r102);
        this.translitChars.put("ᵥ", r3);
        this.translitChars.put("ū", r26);
        this.translitChars.put("ḃ", "b");
        this.translitChars.put("ṗ", r102);
        this.translitChars.put("å", "a");
        this.translitChars.put("ɕ", "c");
        this.translitChars.put("ọ", "o");
        this.translitChars.put("ắ", "a");
        this.translitChars.put("ƒ", "f");
        this.translitChars.put("ǣ", "ae");
        this.translitChars.put("ꝡ", "vy");
        this.translitChars.put("ﬀ", "ff");
        this.translitChars.put("ᶉ", r5);
        this.translitChars.put("ô", "o");
        this.translitChars.put("ǿ", "o");
        this.translitChars.put("ṳ", r26);
        this.translitChars.put("ȥ", r1);
        this.translitChars.put("ḟ", "f");
        this.translitChars.put("ḓ", "d");
        this.translitChars.put("ȇ", "e");
        this.translitChars.put("ȕ", r26);
        this.translitChars.put("ȵ", "n");
        this.translitChars.put("ʠ", "q");
        this.translitChars.put("ấ", "a");
        this.translitChars.put("ǩ", "k");
        this.translitChars.put("ĩ", "i");
        this.translitChars.put("ṵ", r26);
        this.translitChars.put("ŧ", "t");
        this.translitChars.put("ɾ", r5);
        this.translitChars.put("ƙ", "k");
        this.translitChars.put("ṫ", "t");
        this.translitChars.put("ꝗ", "q");
        this.translitChars.put("ậ", "a");
        this.translitChars.put("ʄ", "j");
        this.translitChars.put("ƚ", "l");
        this.translitChars.put("ᶂ", "f");
        this.translitChars.put("ᵴ", r112);
        this.translitChars.put("ꞃ", r5);
        this.translitChars.put("ᶌ", r3);
        this.translitChars.put("ɵ", "o");
        this.translitChars.put("ḉ", "c");
        this.translitChars.put("ᵤ", r26);
        this.translitChars.put("ẑ", r1);
        this.translitChars.put("ṹ", r26);
        this.translitChars.put("ň", "n");
        this.translitChars.put("ʍ", "w");
        this.translitChars.put("ầ", "a");
        this.translitChars.put("ǉ", "lj");
        this.translitChars.put("ɓ", "b");
        this.translitChars.put("ɼ", r5);
        this.translitChars.put("ò", "o");
        this.translitChars.put("ẘ", "w");
        this.translitChars.put("ɗ", "d");
        this.translitChars.put("ꜽ", "ay");
        this.translitChars.put("ư", r26);
        this.translitChars.put("ᶀ", "b");
        this.translitChars.put("ǜ", r26);
        this.translitChars.put("ẹ", "e");
        this.translitChars.put("ǡ", "a");
        this.translitChars.put("ɥ", r182);
        this.translitChars.put("ṏ", "o");
        this.translitChars.put("ǔ", r26);
        this.translitChars.put("ʎ", "y");
        this.translitChars.put("ȱ", "o");
        this.translitChars.put("ệ", "e");
        this.translitChars.put("ế", "e");
        this.translitChars.put("ĭ", "i");
        this.translitChars.put("ⱸ", "e");
        this.translitChars.put("ṯ", "t");
        this.translitChars.put("ᶑ", "d");
        this.translitChars.put("ḧ", r182);
        this.translitChars.put("ṥ", r112);
        this.translitChars.put("ë", "e");
        this.translitChars.put("ᴍ", r9);
        this.translitChars.put("ö", "o");
        this.translitChars.put("é", "e");
        this.translitChars.put("ı", "i");
        this.translitChars.put("ď", "d");
        this.translitChars.put("ᵯ", r9);
        this.translitChars.put("ỵ", "y");
        this.translitChars.put("ŵ", "w");
        this.translitChars.put("ề", "e");
        this.translitChars.put("ứ", r26);
        this.translitChars.put("ƶ", r1);
        this.translitChars.put("ĵ", "j");
        this.translitChars.put("ḍ", "d");
        this.translitChars.put("ŭ", r26);
        this.translitChars.put("ʝ", "j");
        this.translitChars.put("ê", "e");
        this.translitChars.put("ǚ", r26);
        this.translitChars.put("ġ", r14);
        this.translitChars.put("ṙ", r5);
        this.translitChars.put("ƞ", "n");
        this.translitChars.put("ḗ", "e");
        this.translitChars.put("ẝ", r112);
        this.translitChars.put("ᶁ", "d");
        this.translitChars.put("ķ", "k");
        this.translitChars.put("ᴂ", "ae");
        this.translitChars.put("ɘ", "e");
        this.translitChars.put("ợ", "o");
        this.translitChars.put("ḿ", r9);
        this.translitChars.put("ꜰ", "f");
        this.translitChars.put("ẵ", "a");
        this.translitChars.put("ꝏ", "oo");
        this.translitChars.put("ᶆ", r9);
        this.translitChars.put("ᵽ", r102);
        this.translitChars.put("ữ", r26);
        this.translitChars.put("ⱪ", "k");
        this.translitChars.put("ḥ", r182);
        this.translitChars.put("ţ", "t");
        this.translitChars.put("ᵱ", r102);
        this.translitChars.put("ṁ", r9);
        this.translitChars.put("á", "a");
        this.translitChars.put("ᴎ", "n");
        this.translitChars.put("ꝟ", r3);
        this.translitChars.put("è", "e");
        this.translitChars.put("ᶎ", r1);
        this.translitChars.put("ꝺ", "d");
        this.translitChars.put("ᶈ", r102);
        this.translitChars.put("ɫ", "l");
        this.translitChars.put("ᴢ", r1);
        this.translitChars.put("ɱ", r9);
        this.translitChars.put("ṝ", r5);
        this.translitChars.put("ṽ", r3);
        this.translitChars.put("ũ", r26);
        this.translitChars.put("ß", "ss");
        this.translitChars.put("ĥ", r182);
        this.translitChars.put("ᵵ", "t");
        this.translitChars.put("ʐ", r1);
        this.translitChars.put("ṟ", r5);
        this.translitChars.put("ɲ", "n");
        this.translitChars.put("à", "a");
        this.translitChars.put("ẙ", "y");
        this.translitChars.put("ỳ", "y");
        this.translitChars.put("ᴔ", "oe");
        this.translitChars.put("ₓ", "x");
        this.translitChars.put("ȗ", r26);
        this.translitChars.put("ⱼ", "j");
        this.translitChars.put("ẫ", "a");
        this.translitChars.put("ʑ", r1);
        this.translitChars.put("ẛ", r112);
        this.translitChars.put("ḭ", "i");
        String r27 = r5;
        this.translitChars.put("ꜵ", "ao");
        this.translitChars.put("ɀ", r1);
        this.translitChars.put("ÿ", "y");
        this.translitChars.put("ǝ", "e");
        this.translitChars.put("ǭ", "o");
        this.translitChars.put("ᴅ", "d");
        this.translitChars.put("ᶅ", "l");
        this.translitChars.put("ù", r26);
        this.translitChars.put("ạ", "a");
        this.translitChars.put("ḅ", "b");
        this.translitChars.put("ụ", r26);
        this.translitChars.put("ằ", "a");
        this.translitChars.put("ᴛ", "t");
        this.translitChars.put("ƴ", "y");
        this.translitChars.put("ⱦ", "t");
        this.translitChars.put("ⱡ", "l");
        this.translitChars.put("ȷ", "j");
        this.translitChars.put("ᵶ", r1);
        this.translitChars.put("ḫ", r182);
        this.translitChars.put("ⱳ", "w");
        this.translitChars.put("ḵ", "k");
        this.translitChars.put("ờ", "o");
        this.translitChars.put("î", "i");
        this.translitChars.put("ģ", r14);
        this.translitChars.put("ȅ", "e");
        this.translitChars.put("ȧ", "a");
        this.translitChars.put("ẳ", "a");
        this.translitChars.put("ɋ", "q");
        this.translitChars.put("ṭ", "t");
        this.translitChars.put("ꝸ", "um");
        this.translitChars.put("ᴄ", "c");
        this.translitChars.put("ẍ", "x");
        this.translitChars.put("ủ", r26);
        this.translitChars.put("ỉ", "i");
        this.translitChars.put("ᴚ", r27);
        this.translitChars.put("ś", r112);
        this.translitChars.put("ꝋ", "o");
        this.translitChars.put("ỹ", "y");
        this.translitChars.put("ṡ", r112);
        this.translitChars.put("ǌ", "nj");
        this.translitChars.put("ȁ", "a");
        this.translitChars.put("ẗ", "t");
        this.translitChars.put("ĺ", "l");
        this.translitChars.put("ž", r1);
        this.translitChars.put("ᵺ", "th");
        this.translitChars.put("ƌ", "d");
        this.translitChars.put("ș", r112);
        this.translitChars.put("š", r112);
        this.translitChars.put("ᶙ", r26);
        this.translitChars.put("ẽ", "e");
        this.translitChars.put("ẜ", r112);
        this.translitChars.put("ɇ", "e");
        this.translitChars.put("ṷ", r26);
        this.translitChars.put("ố", "o");
        this.translitChars.put("ȿ", r112);
        this.translitChars.put("ᴠ", r3);
        this.translitChars.put("ꝭ", "is");
        this.translitChars.put("ᴏ", "o");
        this.translitChars.put("ɛ", "e");
        this.translitChars.put("ǻ", "a");
        this.translitChars.put("ﬄ", "ffl");
        this.translitChars.put("ⱺ", "o");
        this.translitChars.put("ȋ", "i");
        this.translitChars.put("ᵫ", "ue");
        this.translitChars.put("ȡ", "d");
        this.translitChars.put("ⱬ", r1);
        this.translitChars.put("ẁ", "w");
        this.translitChars.put("ᶏ", "a");
        this.translitChars.put("ꞇ", "t");
        this.translitChars.put("ğ", r14);
        this.translitChars.put("ɳ", "n");
        this.translitChars.put("ʛ", r14);
        this.translitChars.put("ᴜ", r26);
        this.translitChars.put("ẩ", "a");
        this.translitChars.put("ṅ", "n");
        this.translitChars.put("ɨ", "i");
        this.translitChars.put("ᴙ", r27);
        this.translitChars.put("ǎ", "a");
        this.translitChars.put("ſ", r112);
        this.translitChars.put("ȫ", "o");
        this.translitChars.put("ɿ", r27);
        this.translitChars.put("ƭ", "t");
        this.translitChars.put("ḯ", "i");
        this.translitChars.put("ǽ", "ae");
        this.translitChars.put("ⱱ", r3);
        this.translitChars.put("ɶ", "oe");
        this.translitChars.put("ṃ", r9);
        this.translitChars.put("ż", r1);
        this.translitChars.put("ĕ", "e");
        this.translitChars.put("ꜻ", "av");
        this.translitChars.put("ở", "o");
        this.translitChars.put("ễ", "e");
        this.translitChars.put("ɬ", "l");
        this.translitChars.put("ị", "i");
        this.translitChars.put("ᵭ", "d");
        this.translitChars.put("ﬆ", "st");
        this.translitChars.put("ḷ", "l");
        this.translitChars.put("ŕ", r27);
        this.translitChars.put("ᴕ", "ou");
        this.translitChars.put("ʈ", "t");
        this.translitChars.put("ā", "a");
        this.translitChars.put("ḙ", "e");
        this.translitChars.put("ᴑ", "o");
        this.translitChars.put("ç", "c");
        this.translitChars.put("ᶊ", r112);
        this.translitChars.put("ặ", "a");
        this.translitChars.put("ų", r26);
        this.translitChars.put("ả", "a");
        this.translitChars.put("ǥ", r14);
        this.translitChars.put("ꝁ", "k");
        this.translitChars.put("ẕ", r1);
        this.translitChars.put("ŝ", r112);
        this.translitChars.put("ḕ", "e");
        this.translitChars.put("ɠ", r14);
        this.translitChars.put("ꝉ", "l");
        this.translitChars.put("ꝼ", "f");
        this.translitChars.put("ᶍ", "x");
        this.translitChars.put("ǒ", "o");
        this.translitChars.put("ę", "e");
        this.translitChars.put("ổ", "o");
        this.translitChars.put("ƫ", "t");
        this.translitChars.put("ǫ", "o");
        this.translitChars.put("i̇", "i");
        this.translitChars.put("ṇ", "n");
        this.translitChars.put("ć", "c");
        this.translitChars.put("ᵷ", r14);
        this.translitChars.put("ẅ", "w");
        this.translitChars.put("ḑ", "d");
        this.translitChars.put("ḹ", "l");
        this.translitChars.put("œ", "oe");
        this.translitChars.put("ᵳ", r27);
        this.translitChars.put("ļ", "l");
        this.translitChars.put("ȑ", r27);
        this.translitChars.put("ȭ", "o");
        this.translitChars.put("ᵰ", "n");
        this.translitChars.put("ᴁ", "ae");
        this.translitChars.put("ŀ", "l");
        this.translitChars.put("ä", "a");
        this.translitChars.put("ƥ", r102);
        this.translitChars.put("ỏ", "o");
        this.translitChars.put("į", "i");
        this.translitChars.put("ȓ", r27);
        this.translitChars.put("ǆ", "dz");
        this.translitChars.put("ḡ", r14);
        this.translitChars.put("ṻ", r26);
        this.translitChars.put("ō", "o");
        this.translitChars.put("ľ", "l");
        this.translitChars.put("ẃ", "w");
        this.translitChars.put("ț", "t");
        this.translitChars.put("ń", "n");
        this.translitChars.put("ɍ", r27);
        this.translitChars.put("ȃ", "a");
        this.translitChars.put("ü", r26);
        this.translitChars.put("ꞁ", "l");
        this.translitChars.put("ᴐ", "o");
        this.translitChars.put("ớ", "o");
        this.translitChars.put("ᴃ", "b");
        this.translitChars.put("ɹ", r27);
        this.translitChars.put("ᵲ", r27);
        this.translitChars.put("ʏ", "y");
        this.translitChars.put("ᵮ", "f");
        this.translitChars.put("ⱨ", r182);
        this.translitChars.put("ŏ", "o");
        this.translitChars.put("ú", r26);
        this.translitChars.put("ṛ", r27);
        this.translitChars.put("ʮ", r182);
        this.translitChars.put("ó", "o");
        this.translitChars.put("ů", r26);
        this.translitChars.put("ỡ", "o");
        this.translitChars.put("ṕ", r102);
        this.translitChars.put("ᶖ", "i");
        this.translitChars.put("ự", r26);
        this.translitChars.put("ã", "a");
        this.translitChars.put("ᵢ", "i");
        this.translitChars.put("ṱ", "t");
        this.translitChars.put("ể", "e");
        this.translitChars.put("ử", r26);
        this.translitChars.put("í", "i");
        this.translitChars.put("ɔ", "o");
        this.translitChars.put("ɺ", r27);
        this.translitChars.put("ɢ", r14);
        this.translitChars.put("ř", r27);
        this.translitChars.put("ẖ", r182);
        this.translitChars.put("ű", r26);
        this.translitChars.put("ȍ", "o");
        this.translitChars.put("ḻ", "l");
        this.translitChars.put("ḣ", r182);
        this.translitChars.put("ȶ", "t");
        this.translitChars.put("ņ", "n");
        this.translitChars.put("ᶒ", "e");
        this.translitChars.put("ì", "i");
        this.translitChars.put("ẉ", "w");
        this.translitChars.put("ē", "e");
        this.translitChars.put("ᴇ", "e");
        this.translitChars.put("ł", "l");
        this.translitChars.put("ộ", "o");
        this.translitChars.put("ɭ", "l");
        this.translitChars.put("ẏ", "y");
        this.translitChars.put("ᴊ", "j");
        this.translitChars.put("ḱ", "k");
        this.translitChars.put("ṿ", r3);
        this.translitChars.put("ȩ", "e");
        this.translitChars.put("â", "a");
        this.translitChars.put("ş", r112);
        this.translitChars.put("ŗ", r27);
        this.translitChars.put("ʋ", r3);
        this.translitChars.put("ₐ", "a");
        this.translitChars.put("ↄ", "c");
        this.translitChars.put("ᶓ", "e");
        this.translitChars.put("ɰ", r9);
        this.translitChars.put("ᴡ", "w");
        this.translitChars.put("ȏ", "o");
        this.translitChars.put("č", "c");
        this.translitChars.put("ǵ", r14);
        this.translitChars.put("ĉ", "c");
        this.translitChars.put("ᶗ", "o");
        this.translitChars.put("ꝃ", "k");
        this.translitChars.put("ꝙ", "q");
        this.translitChars.put("ṑ", "o");
        this.translitChars.put("ꜱ", r112);
        this.translitChars.put("ṓ", "o");
        this.translitChars.put("ȟ", r182);
        this.translitChars.put("ő", "o");
        this.translitChars.put("ꜩ", "tz");
        this.translitChars.put("ẻ", "e");
        this.translitChars.put("і", "i");
        this.translitChars.put("ї", "i");
    L12:
        StringBuilder r12 = new StringBuilder(r38.length());
        int r2 = r38.length();
        int r4 = 0;
        boolean r52 = false;
    L13:
        if (r4 >= r2) goto L58;
        int r64 = r4 + 1;
        String r42 = r38.substring(r4, r64);
        if (r40 == false) goto L17;
        String r53 = r42.toLowerCase();
        r52 = !r42.equals(r53);
        r42 = r53;
    L17:
        String r92 = this.translitChars.get(r42);
        if (r92 != null) goto L21;
        if (r39 == false) goto L21;
        r92 = this.ruTranslitChars.get(r42);
    L21:
        if (r92 == null) goto L29;
        if (r40 == false) goto L28;
        if (r52 == false) goto L28;
        if (r92.length() <= 1) goto L27;
        r92 = r92.substring(0, 1).toUpperCase() + r92.substring(1);
        goto L28
    L27:
        r92 = r92.toUpperCase();
    L28:
        r12.append(r92);
    L56:
        r4 = r64;
        goto L13
    L29:
        if (r40 == false) goto L55;
        char r8 = r42.charAt(0);
        if (r8 < 'a') goto L39;
        if (r8 > 'z') goto L39;
        if (r8 < '0') goto L39;
        if (r8 > '9') goto L39;
    L53:
        if (r52 == false) goto L55;
        r42 = r42.toUpperCase();
    L39:
        if (r8 == ' ') goto L53;
        if (r8 == '\'') goto L53;
        if (r8 == ',') goto L53;
        if (r8 == '.') goto L53;
        if (r8 == '&') goto L53;
        if (r8 == '-') goto L53;
        if (r8 == '/') goto L53;
        return null;
    L55:
        r12.append(r42);
        goto L56
    L58:
        return r12.toString();
    L8:
        r22 = RegisterSpec.PREFIX;
        r7 = "u";
        r20 = "s";
        r23 = "m";
        r5 = "r";
        r17 = "z";
        r24 = ImageLoader.AUTOPLAY_FILTER;
        r11 = "h";
        r18 = "p";
        goto L10
    }

    public boolean isCurrentLocalLocale() {
        return this.currentLocaleInfo.isLocal();
    }

    public void loadRemoteLanguages(int r2) {
        loadRemoteLanguages(r2, true);
    }

    public void loadRemoteLanguages(int r4, boolean r5) {
        if (this.loadingRemoteLanguages == false) goto L5;
        return;
    L5:
        this.loadingRemoteLanguages = true;
        TLRPC.TL_langpack_getLanguages r0 = new TLRPC.TL_langpack_getLanguages();
        ConnectionsManager.getInstance(r4).sendRequest(r0, new LocaleController$$ExternalSyntheticLambda17(this, r5, r4), 8);
    }

    public void onDeviceConfigurationChange(Configuration r3) {
        if (this.changingConfiguration == false) goto L5;
        return;
    L5:
        is24HourFormat = DateFormat.is24HourFormat(ApplicationLoader.applicationContext);
        Locale r32 = r3.locale;
        this.systemDefaultLocale = r32;
        if (this.languageOverride == null) goto L8;
        LocaleInfo r33 = this.currentLocaleInfo;
        this.currentLocaleInfo = null;
        applyLanguage(r33, false, false, UserConfig.selectedAccount);
    L25:
        String r34 = getSystemLocaleStringIso639();
        String r0 = this.currentSystemLocale;
        if (r0 != null) goto L28;
    L30:
        checkLocalizationInternal();
        return;
    L28:
        if (r34.equals(r0) == true) goto L30;
        this.currentSystemLocale = r34;
        ConnectionsManager.setSystemLangCode(r34);
        goto L30
    L8:
        if (r32 == null) goto L25;
        String r02 = r32.getDisplayName();
        String r1 = this.currentLocale.getDisplayName();
        if (r02 == null) goto L15;
        if (r1 == null) goto L15;
        if (r02.equals(r1) == true) goto L15;
        recreateFormatters();
    L15:
        this.currentLocale = r32;
        LocaleInfo r35 = this.currentLocaleInfo;
        if (r35 == null) goto L21;
        if (TextUtils.isEmpty(r35.pluralLangCode) == true) goto L21;
        this.currentPluralRules = this.allRules.get(this.currentLocaleInfo.pluralLangCode);
    L21:
        if (this.currentPluralRules != null) goto L25;
        PluralRules r36 = this.allRules.get(this.currentLocale.getLanguage());
        this.currentPluralRules = r36;
        if (r36 != null) goto L25;
        this.currentPluralRules = this.allRules.get("en");
        goto L25
    }

    public void recreateFormatters() {
        Locale r0 = this.currentLocale;
        if (r0 != null) goto L5;
        r0 = Locale.getDefault();
    L5:
        String r02 = r0.getLanguage();
        if (r02 != null) goto L8;
        r02 = "en";
    L8:
        String r03 = r02.toLowerCase();
        int r2 = 0;
        int r3 = 2;
        if (r03.length() != 2) goto L19;
        if (r03.equals("ar") == false) goto L13;
    L32:
        boolean r1 = true;
    L33:
        isRTL = r1;
        if (r03.equals("ko") == true) goto L37;
        r3 = 1;
    L37:
        nameDisplayOrder = r3;
        this.formatterBoostExpired = null;
        this.formatterGiveawayCard = null;
        this.formatterGiveawayMonthDay = null;
        this.formatterGiveawayMonthDayYear = null;
        this.formatterMonthYear = null;
        this.formatterMonthOnly = null;
        this.formatterDayMonth = null;
        this.formatterYear = null;
        this.formatterYearMax = null;
        this.chatDate = null;
        this.chatFullDate = null;
        this.formatterWeek = null;
        this.formatterWeekLong = null;
        this.formatterScheduleDay = null;
        this.formatterScheduleYear = null;
        this.formatterDay = null;
        this.formatterDayWithSeconds = null;
        this.formatterConstDay = null;
        this.formatterStats = null;
        this.formatterBannedUntil = null;
        this.formatterBannedUntilThisYear = null;
    L38:
        FastDateFormat[] r12 = this.formatterScheduleSend;
        if (r2 >= r12.length) goto L41;
        r12[r2] = null;
        r2 = r2 + 1;
        goto L38
    L41:
        return;
    L13:
        if (r03.equals("fa") == true) goto L32;
        if (r03.equals("he") == true) goto L32;
        if (r03.equals("iw") == true) goto L32;
    L19:
        if (r03.startsWith("ar_") == true) goto L32;
        if (r03.startsWith("fa_") == true) goto L32;
        if (r03.startsWith("he_") == true) goto L32;
        if (r03.startsWith("iw_") == true) goto L32;
        LocaleInfo r13 = this.currentLocaleInfo;
        if (r13 != null) goto L29;
    L31:
        r1 = false;
        goto L33
    L29:
        if (r13.isRtl == false) goto L31;
        goto L31
    }

    public void reloadCurrentRemoteLocale(int r7, String r8, boolean r9, Runnable r10) {
        if (r8 == null) goto L4;
        r8 = r8.replace("-", "_");
    L4:
        String r2 = r8;
        if (r2 == null) goto L14;
        LocaleInfo r82 = this.currentLocaleInfo;
        if (r82 != null) goto L9;
        return;
    L9:
        if (r2.equals(r82.shortName) == true) goto L14;
        if (r2.equals(this.currentLocaleInfo.baseLangCode) == true) goto L14;
        return;
    L14:
        applyRemoteLanguage(this.currentLocaleInfo, r2, r9, r7, r10);
    }

    public void saveRemoteLocaleStrings(LocaleInfo r12, TLRPC.TL_langPackDifference r13, int r14, Runnable r15) {
        boolean r2 = true;
        int r5 = 0;
        if (r13 != null) goto L5;
    L80:
        StringBuilder r0 = new StringBuilder("saveRemoteLocaleStrings: empty difference=");
        if (r13 != null) goto L83;
    L86:
        boolean r1 = true;
    L87:
        r0.append(r1);
        r0.append("; locale is local or null=");
        if (r12 != null) goto L90;
    L93:
        r0.append(r2);
        FileLog.d(r0.toString());
        recreateFormatters();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
        if (r15 == null) goto L106;
        r15.run();
        return;
    L106:
        return;
    L90:
        if (r12.isLocal() == true) goto L93;
        r2 = false;
        goto L93
    L83:
        if (r13.strings.isEmpty() == true) goto L86;
        r1 = false;
        goto L87
    L5:
        if (r13.strings.isEmpty() == true) goto L80;
        if (r12 == null) goto L80;
        if (r12.isLocal() == true) goto L80;
        String r6 = r13.lang_code.replace(SignatureVisitor.SUPER, '_').toLowerCase();
        if (r6.equals(r12.shortName) == false) goto L14;
        int r7 = 0;
    L18:
        if (r7 != (-1)) goto L21;
        FileLog.d("saveRemoteLocaleStrings: unknown language " + r6 + " (locale short=" + r12.shortName + ", base=" + r12.baseLangCode + ")");
        return;
    L21:
        if (r7 != 0) goto L23;
        File r62 = r12.getPathToFile();
    L97:
    L77:
        e = move-exception;
        FileLog.e(e);
        return;
    L25:
        if (r13.from_version != 0) goto L27;
        FileLog.d("saveRemoteLocaleStrings: difference is straight from the beginning");     // Catch: Exception -> L77
        HashMap<String, String> r02 = new HashMap();     // Catch: Exception -> L77
    L29:
        if (r5 >= r13.strings.size()) goto L64;
        TLRPC.LangPackString r22 = r13.strings.get(r5);     // Catch: Exception -> L77
        if ((r22 instanceof TLRPC.TL_langPackString) == false) goto L34;
        r02.put(r22.key, escapeString(r22.value));     // Catch: Exception -> L77
    L63:
        r5 = r5 + 1;     // Catch: Exception -> L77
        goto L29
    L34:
        if ((r22 instanceof TLRPC.TL_langPackStringPluralized) == false) goto L61;
        String r8 = r22.key + "_zero";     // Catch: Exception -> L77
        String r9 = r22.zero_value;     // Catch: Exception -> L77
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r9 == null) goto L39;
        String r92 = escapeString(r9);     // Catch: Exception -> L77
    L40:
        r02.put(r8, r92);     // Catch: Exception -> L77
        String r82 = r22.key + "_one";     // Catch: Exception -> L77
        String r93 = r22.one_value;     // Catch: Exception -> L77
        if (r93 == null) goto L43;
        String r94 = escapeString(r93);     // Catch: Exception -> L77
    L44:
        r02.put(r82, r94);     // Catch: Exception -> L77
        String r83 = r22.key + "_two";     // Catch: Exception -> L77
        String r95 = r22.two_value;     // Catch: Exception -> L77
        if (r95 == null) goto L47;
        String r96 = escapeString(r95);     // Catch: Exception -> L77
    L48:
        r02.put(r83, r96);     // Catch: Exception -> L77
        String r84 = r22.key + "_few";     // Catch: Exception -> L77
        String r97 = r22.few_value;     // Catch: Exception -> L77
        if (r97 == null) goto L51;
        String r98 = escapeString(r97);     // Catch: Exception -> L77
    L52:
        r02.put(r84, r98);     // Catch: Exception -> L77
        String r85 = r22.key + "_many";     // Catch: Exception -> L77
        String r99 = r22.many_value;     // Catch: Exception -> L77
        if (r99 == null) goto L55;
        String r910 = escapeString(r99);     // Catch: Exception -> L77
    L56:
        r02.put(r85, r910);     // Catch: Exception -> L77
        String r86 = r22.key + "_other";     // Catch: Exception -> L77
        String r23 = r22.other_value;     // Catch: Exception -> L77
        if (r23 == null) goto L59;
        r10 = escapeString(r23);     // Catch: Exception -> L77
    L59:
        r02.put(r86, r10);     // Catch: Exception -> L77
        goto L63
    L55:
        r910 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L56
    L51:
        r98 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L52
    L47:
        r96 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L48
    L43:
        r94 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L44
    L39:
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L40
    L61:
        if ((r22 instanceof TLRPC.TL_langPackStringDeleted) == false) goto L63;
        r02.remove(r22.key);     // Catch: Exception -> L77
        goto L63
    L64:
        FileLog.d("save locale file to " + r62);     // Catch: Exception -> L77
        BufferedWriter r24 = new BufferedWriter(new FileWriter(r62));     // Catch: Exception -> L77
        r24.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");     // Catch: Exception -> L77
        r24.write("<resources>\n");     // Catch: Exception -> L77
        Iterator<Map.Entry<String, String>> r03 = r02.entrySet().iterator();     // Catch: Exception -> L77
    L66:
        if (r03.hasNext() == false) goto L68;
        Map.Entry<String, String> r52 = r03.next();     // Catch: Exception -> L77
        r24.write(String.format("<string name=\"%1$s\">%2$s</string>\n", new Object[]{r52.getKey(), r52.getValue()}));     // Catch: Exception -> L77
        goto L66
    L68:
        r24.write("</resources>");     // Catch: Exception -> L77
        r24.close();     // Catch: Exception -> L77
        boolean r04 = r12.hasBaseLang();     // Catch: Exception -> L77
        if (r04 == false) goto L71;
        File r25 = r12.getPathToBaseFile();     // Catch: Exception -> L77
    L72:
        HashMap<String, String> r53 = getLocaleFileStrings(r25);     // Catch: Exception -> L77
        if (r04 == false) goto L75;
        r53.putAll(getLocaleFileStrings(r12.getPathToFile()));     // Catch: Exception -> L77
    L75:
        mergeExteraLocaleValues(r53, r12);     // Catch: Exception -> L77
        FileLog.d("saved locale file to " + r62);     // Catch: Exception -> L77
        AndroidUtilities.runOnUIThread(new LocaleController$$ExternalSyntheticLambda11(this, r7, r12, r13, r53, r15));     // Catch: Exception -> L77
        return;
    L71:
        r25 = r12.getPathToFile();     // Catch: Exception -> L77
        goto L72
    L27:
        FileLog.d("saveRemoteLocaleStrings: difference is from version " + r13.from_version + " ours " + r12.version + " (base version " + r12.baseLangCode + ")");     // Catch: Exception -> L77
        r02 = getLocaleFileStrings(r62, true);     // Catch: Exception -> L77
        goto L29
    L23:
        r62 = r12.getPathToBaseFile();
        goto L97
    L14:
        if (r6.equals(r12.baseLangCode) == false) goto L16;
        r7 = 1;
        goto L18
    L16:
        r7 = -1;
        goto L18
    }

    public void saveRemoteLocaleStringsForCurrentLocale(TLRPC.TL_langPackDifference r4, int r5) {
        if (this.currentLocaleInfo == null) goto L12;
        String r0 = r4.lang_code.replace(SignatureVisitor.SUPER, '_').toLowerCase();
        if (r0.equals(this.currentLocaleInfo.shortName) == false) goto L8;
    L10:
        saveRemoteLocaleStrings(this.currentLocaleInfo, r4, r5, null);
        return;
    L8:
        if (r0.equals(this.currentLocaleInfo.baseLangCode) == true) goto L10;
        return;
    }
}
