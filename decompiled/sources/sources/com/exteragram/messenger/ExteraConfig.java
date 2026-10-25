package com.exteragram.messenger;

import android.content.SharedPreferences;
import android.util.Pair;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.adblock.backend.AdBlockManager;
import com.exteragram.messenger.api.ApiController;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.config.BasePref;
import com.exteragram.messenger.config.BooleanPref;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.config.EnumPref;
import com.exteragram.messenger.config.FloatPref;
import com.exteragram.messenger.config.IntegerPref;
import com.exteragram.messenger.config.LongPref;
import com.exteragram.messenger.config.NullableStringPref;
import com.exteragram.messenger.config.PrefClassesKt;
import com.exteragram.messenger.config.SanitizedIntegerPref;
import com.exteragram.messenger.config.StringPref;
import com.exteragram.messenger.config.StringSetPref;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.web.SearchEngine;

@Metadata(d1 = {"\u0000ç\u0001\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bK\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0003\bº\u0001\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\\\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0019\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\f\u001a\u001d\u0010\u000e\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\u000f\u001a\r\u0010\u0010\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u0002\u001a\u000f\u0010\u0011\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0011\u0010\u0002\u001a\u000f\u0010\u0012\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0002\u001a\u000f\u0010\u0013\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0013\u0010\u0002\u001a\u000f\u0010\u0014\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0014\u0010\u0002\u001a\u000f\u0010\u0015\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0015\u0010\u0002\u001a\r\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0002\u001a\r\u0010\u0017\u001a\u00020\u0000¢\u0006\u0004\b\u0017\u0010\u0002\u001a\r\u0010\u0018\u001a\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0002\u001a\r\u0010\u0019\u001a\u00020\u0000¢\u0006\u0004\b\u0019\u0010\u0002\u001a\u001d\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c¢\u0006\u0004\b\u001d\u0010\u001e\u001a\r\u0010\u001f\u001a\u00020\u0000¢\u0006\u0004\b\u001f\u0010\u0002\u001a\r\u0010 \u001a\u00020\u0000¢\u0006\u0004\b \u0010\u0002\u001a+\u0010%\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020\n2\b\b\u0002\u0010$\u001a\u00020\nH\u0007¢\u0006\u0004\b%\u0010&\u001a%\u0010%\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\n2\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b%\u0010)\u001a\r\u0010*\u001a\u00020!¢\u0006\u0004\b*\u0010+\u001a\r\u0010,\u001a\u00020\u001b¢\u0006\u0004\b,\u0010-\u001a\r\u0010.\u001a\u00020\u001b¢\u0006\u0004\b.\u0010-\u001a\u001d\u00101\u001a\u00020!2\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020!¢\u0006\u0004\b1\u00102\u001a\r\u00103\u001a\u00020\u001b¢\u0006\u0004\b3\u0010-\u001a\u0017\u00105\u001a\u00020!2\u0006\u00104\u001a\u00020!H\u0002¢\u0006\u0004\b5\u00106\u001a\u000f\u00108\u001a\u000207H\u0002¢\u0006\u0004\b8\u00109\u001a\r\u0010:\u001a\u00020\u0000¢\u0006\u0004\b:\u0010\u0002\u001a\r\u0010;\u001a\u00020\n¢\u0006\u0004\b;\u0010<\u001a\u0015\u0010>\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\n¢\u0006\u0004\b>\u0010?\u001a\r\u0010@\u001a\u00020\u0005¢\u0006\u0004\b@\u0010A\u001a\r\u0010B\u001a\u00020\u001b¢\u0006\u0004\bB\u0010-\"\u0017\u0010D\u001a\u00020C8\u0006¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"$\u0010H\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010I\"\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010L\"\u0017\u0010M\u001a\u0002078\u0006¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u00109\"\u0017\u0010Q\u001a\u00020P8\u0006¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010W\"\u0016\u0010X\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010Y\"\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\\"+\u0010c\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b^\u0010_\u001a\u0004\b`\u0010-\"\u0004\ba\u0010b\"+\u0010g\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bd\u0010_\u001a\u0004\be\u0010<\"\u0004\bf\u0010?\"+\u0010k\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bh\u0010_\u001a\u0004\bi\u0010<\"\u0004\bj\u0010?\"+\u0010o\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bl\u0010_\u001a\u0004\bm\u0010<\"\u0004\bn\u0010?\"+\u0010s\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bp\u0010_\u001a\u0004\bq\u0010<\"\u0004\br\u0010?\"+\u0010w\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bt\u0010_\u001a\u0004\bu\u0010<\"\u0004\bv\u0010?\"+\u0010{\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bx\u0010_\u001a\u0004\by\u0010<\"\u0004\bz\u0010?\"+\u0010\u007f\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b|\u0010_\u001a\u0004\b}\u0010-\"\u0004\b~\u0010b\"/\u0010\u0083\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0080\u0001\u0010_\u001a\u0005\b\u0081\u0001\u0010<\"\u0005\b\u0082\u0001\u0010?\"/\u0010\u0087\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010_\u001a\u0005\b\u0085\u0001\u0010<\"\u0005\b\u0086\u0001\u0010?\"/\u0010\u008b\u0001\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0088\u0001\u0010_\u001a\u0005\b\u0089\u0001\u0010-\"\u0005\b\u008a\u0001\u0010b\"/\u0010\u008f\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008c\u0001\u0010_\u001a\u0005\b\u008d\u0001\u0010<\"\u0005\b\u008e\u0001\u0010?\"/\u0010\u0093\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0090\u0001\u0010_\u001a\u0005\b\u0091\u0001\u0010<\"\u0005\b\u0092\u0001\u0010?\"/\u0010\u0097\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0094\u0001\u0010_\u001a\u0005\b\u0095\u0001\u0010<\"\u0005\b\u0096\u0001\u0010?\"/\u0010\u009b\u0001\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0098\u0001\u0010_\u001a\u0005\b\u0099\u0001\u0010-\"\u0005\b\u009a\u0001\u0010b\"0\u0010 \u0001\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u009c\u0001\u0010_\u001a\u0005\b\u009d\u0001\u0010A\"\u0006\b\u009e\u0001\u0010\u009f\u0001\"8\u0010¡\u0001\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001aj\b\u0012\u0004\u0012\u00020\u0005`\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b¡\u0001\u0010¢\u0001\u001a\u0005\b£\u0001\u0010\u001e\"\u0006\b¤\u0001\u0010¥\u0001\"G\u0010¨\u0001\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040¦\u0001j\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0004`§\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b¨\u0001\u0010©\u0001\u001a\u0006\bª\u0001\u0010«\u0001\"\u0006\b¬\u0001\u0010\u00ad\u0001\"3\u0010´\u0001\u001a\u00030®\u00012\u0007\u0010]\u001a\u00030®\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b¯\u0001\u0010_\u001a\u0006\b°\u0001\u0010±\u0001\"\u0006\b²\u0001\u0010³\u0001\"4\u0010¸\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010]\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\bµ\u0001\u0010_\u001a\u0005\b¶\u0001\u0010A\"\u0006\b·\u0001\u0010\u009f\u0001\"8\u0010¹\u0001\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001aj\b\u0012\u0004\u0012\u00020\u0005`\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b¹\u0001\u0010¢\u0001\u001a\u0005\bº\u0001\u0010\u001e\"\u0006\b»\u0001\u0010¥\u0001\"8\u0010¼\u0001\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001aj\b\u0012\u0004\u0012\u00020\u0005`\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b¼\u0001\u0010¢\u0001\u001a\u0005\b½\u0001\u0010\u001e\"\u0006\b¾\u0001\u0010¥\u0001\"/\u0010Â\u0001\u001a\u00020!2\u0006\u0010]\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¿\u0001\u0010_\u001a\u0004\b%\u0010+\"\u0006\bÀ\u0001\u0010Á\u0001\"/\u0010Æ\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÃ\u0001\u0010_\u001a\u0005\bÄ\u0001\u0010<\"\u0005\bÅ\u0001\u0010?\"3\u0010Í\u0001\u001a\u00030Ç\u00012\u0007\u0010]\u001a\u00030Ç\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bÈ\u0001\u0010_\u001a\u0006\bÉ\u0001\u0010Ê\u0001\"\u0006\bË\u0001\u0010Ì\u0001\"/\u0010Ñ\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÎ\u0001\u0010_\u001a\u0005\bÏ\u0001\u0010<\"\u0005\bÐ\u0001\u0010?\"/\u0010Õ\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÒ\u0001\u0010_\u001a\u0005\bÓ\u0001\u0010<\"\u0005\bÔ\u0001\u0010?\"/\u0010Ù\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÖ\u0001\u0010_\u001a\u0005\b×\u0001\u0010<\"\u0005\bØ\u0001\u0010?\"/\u0010Ý\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÚ\u0001\u0010_\u001a\u0005\bÛ\u0001\u0010<\"\u0005\bÜ\u0001\u0010?\"/\u0010á\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÞ\u0001\u0010_\u001a\u0005\bß\u0001\u0010<\"\u0005\bà\u0001\u0010?\"/\u0010å\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bâ\u0001\u0010_\u001a\u0005\bã\u0001\u0010<\"\u0005\bä\u0001\u0010?\"/\u0010é\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bæ\u0001\u0010_\u001a\u0005\bç\u0001\u0010<\"\u0005\bè\u0001\u0010?\"/\u0010í\u0001\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bê\u0001\u0010_\u001a\u0005\bë\u0001\u0010-\"\u0005\bì\u0001\u0010b\"3\u0010ô\u0001\u001a\u00030î\u00012\u0007\u0010]\u001a\u00030î\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bï\u0001\u0010_\u001a\u0006\bð\u0001\u0010ñ\u0001\"\u0006\bò\u0001\u0010ó\u0001\"3\u0010û\u0001\u001a\u00030õ\u00012\u0007\u0010]\u001a\u00030õ\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bö\u0001\u0010_\u001a\u0006\b÷\u0001\u0010ø\u0001\"\u0006\bù\u0001\u0010ú\u0001\"/\u0010ÿ\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bü\u0001\u0010_\u001a\u0005\bý\u0001\u0010<\"\u0005\bþ\u0001\u0010?\"/\u0010\u0083\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0080\u0002\u0010_\u001a\u0005\b\u0081\u0002\u0010<\"\u0005\b\u0082\u0002\u0010?\"0\u0010\u0087\u0002\u001a\u00020!2\u0006\u0010]\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u0084\u0002\u0010_\u001a\u0005\b\u0085\u0002\u0010+\"\u0006\b\u0086\u0002\u0010Á\u0001\"/\u0010\u008b\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0015\n\u0005\b\u0088\u0002\u0010_\u001a\u0005\b\u0089\u0002\u0010<\"\u0005\b\u008a\u0002\u0010?\"/\u0010\u008f\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008c\u0002\u0010_\u001a\u0005\b\u008d\u0002\u0010<\"\u0005\b\u008e\u0002\u0010?\"/\u0010\u0093\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0090\u0002\u0010_\u001a\u0005\b\u0091\u0002\u0010<\"\u0005\b\u0092\u0002\u0010?\"/\u0010\u0097\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0094\u0002\u0010_\u001a\u0005\b\u0095\u0002\u0010<\"\u0005\b\u0096\u0002\u0010?\"/\u0010\u009b\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0098\u0002\u0010_\u001a\u0005\b\u0099\u0002\u0010<\"\u0005\b\u009a\u0002\u0010?\"/\u0010\u009f\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u009c\u0002\u0010_\u001a\u0005\b\u009d\u0002\u0010<\"\u0005\b\u009e\u0002\u0010?\"/\u0010£\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b \u0002\u0010_\u001a\u0005\b¡\u0002\u0010<\"\u0005\b¢\u0002\u0010?\"/\u0010§\u0002\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¤\u0002\u0010_\u001a\u0005\b¥\u0002\u0010-\"\u0005\b¦\u0002\u0010b\"/\u0010«\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¨\u0002\u0010_\u001a\u0005\b©\u0002\u0010<\"\u0005\bª\u0002\u0010?\"/\u0010¯\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¬\u0002\u0010_\u001a\u0005\b\u00ad\u0002\u0010<\"\u0005\b®\u0002\u0010?\"/\u0010³\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b°\u0002\u0010_\u001a\u0005\b±\u0002\u0010<\"\u0005\b²\u0002\u0010?\"0\u0010·\u0002\u001a\u00020!2\u0006\u0010]\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b´\u0002\u0010_\u001a\u0005\bµ\u0002\u0010+\"\u0006\b¶\u0002\u0010Á\u0001\"3\u0010¾\u0002\u001a\u00030¸\u00022\u0007\u0010]\u001a\u00030¸\u00028F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b¹\u0002\u0010_\u001a\u0006\bº\u0002\u0010»\u0002\"\u0006\b¼\u0002\u0010½\u0002\"3\u0010Å\u0002\u001a\u00030¿\u00022\u0007\u0010]\u001a\u00030¿\u00028F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bÀ\u0002\u0010_\u001a\u0006\bÁ\u0002\u0010Â\u0002\"\u0006\bÃ\u0002\u0010Ä\u0002\"/\u0010É\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÆ\u0002\u0010_\u001a\u0005\bÇ\u0002\u0010<\"\u0005\bÈ\u0002\u0010?\"/\u0010Í\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÊ\u0002\u0010_\u001a\u0005\bË\u0002\u0010<\"\u0005\bÌ\u0002\u0010?\"/\u0010Ñ\u0002\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÎ\u0002\u0010_\u001a\u0005\bÏ\u0002\u0010-\"\u0005\bÐ\u0002\u0010b\"/\u0010Õ\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÒ\u0002\u0010_\u001a\u0005\bÓ\u0002\u0010<\"\u0005\bÔ\u0002\u0010?\"/\u0010Ù\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÖ\u0002\u0010_\u001a\u0005\b×\u0002\u0010<\"\u0005\bØ\u0002\u0010?\"/\u0010Ý\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÚ\u0002\u0010_\u001a\u0005\bÛ\u0002\u0010<\"\u0005\bÜ\u0002\u0010?\"/\u0010á\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÞ\u0002\u0010_\u001a\u0005\bß\u0002\u0010<\"\u0005\bà\u0002\u0010?\"8\u0010â\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\bâ\u0002\u0010¢\u0001\u001a\u0005\bã\u0002\u0010\u001e\"\u0006\bä\u0002\u0010¥\u0001\"8\u0010å\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\bå\u0002\u0010¢\u0001\u001a\u0005\bæ\u0002\u0010\u001e\"\u0006\bç\u0002\u0010¥\u0001\"0\u0010ë\u0002\u001a\u00020!2\u0006\u0010]\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\bè\u0002\u0010_\u001a\u0005\bé\u0002\u0010+\"\u0006\bê\u0002\u0010Á\u0001\"3\u0010ò\u0002\u001a\u00030ì\u00022\u0007\u0010]\u001a\u00030ì\u00028F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bí\u0002\u0010_\u001a\u0006\bî\u0002\u0010ï\u0002\"\u0006\bð\u0002\u0010ñ\u0002\"/\u0010ö\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bó\u0002\u0010_\u001a\u0005\bô\u0002\u0010<\"\u0005\bõ\u0002\u0010?\"/\u0010ú\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b÷\u0002\u0010_\u001a\u0005\bø\u0002\u0010<\"\u0005\bù\u0002\u0010?\"/\u0010þ\u0002\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bû\u0002\u0010_\u001a\u0005\bü\u0002\u0010<\"\u0005\bý\u0002\u0010?\"/\u0010\u0082\u0003\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÿ\u0002\u0010_\u001a\u0005\b\u0080\u0003\u0010-\"\u0005\b\u0081\u0003\u0010b\"/\u0010\u0086\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0083\u0003\u0010_\u001a\u0005\b\u0084\u0003\u0010<\"\u0005\b\u0085\u0003\u0010?\"/\u0010\u008a\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0087\u0003\u0010_\u001a\u0005\b\u0088\u0003\u0010<\"\u0005\b\u0089\u0003\u0010?\"/\u0010\u008e\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008b\u0003\u0010_\u001a\u0005\b\u008c\u0003\u0010<\"\u0005\b\u008d\u0003\u0010?\"/\u0010\u0092\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008f\u0003\u0010_\u001a\u0005\b\u0090\u0003\u0010<\"\u0005\b\u0091\u0003\u0010?\"/\u0010\u0096\u0003\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0093\u0003\u0010_\u001a\u0005\b\u0094\u0003\u0010-\"\u0005\b\u0095\u0003\u0010b\"/\u0010\u009a\u0003\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0097\u0003\u0010_\u001a\u0005\b\u0098\u0003\u0010-\"\u0005\b\u0099\u0003\u0010b\"0\u0010\u009e\u0003\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u009b\u0003\u0010_\u001a\u0005\b\u009c\u0003\u0010A\"\u0006\b\u009d\u0003\u0010\u009f\u0001\"/\u0010¢\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u009f\u0003\u0010_\u001a\u0005\b \u0003\u0010<\"\u0005\b¡\u0003\u0010?\"/\u0010¦\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b£\u0003\u0010_\u001a\u0005\b¤\u0003\u0010<\"\u0005\b¥\u0003\u0010?\"/\u0010ª\u0003\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b§\u0003\u0010_\u001a\u0005\b¨\u0003\u0010-\"\u0005\b©\u0003\u0010b\"/\u0010®\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b«\u0003\u0010_\u001a\u0005\b¬\u0003\u0010<\"\u0005\b\u00ad\u0003\u0010?\"/\u0010²\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¯\u0003\u0010_\u001a\u0005\b°\u0003\u0010<\"\u0005\b±\u0003\u0010?\"/\u0010¶\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b³\u0003\u0010_\u001a\u0005\b´\u0003\u0010<\"\u0005\bµ\u0003\u0010?\"/\u0010º\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b·\u0003\u0010_\u001a\u0005\b¸\u0003\u0010<\"\u0005\b¹\u0003\u0010?\"/\u0010¾\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b»\u0003\u0010_\u001a\u0005\b¼\u0003\u0010<\"\u0005\b½\u0003\u0010?\"/\u0010Â\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¿\u0003\u0010_\u001a\u0005\bÀ\u0003\u0010<\"\u0005\bÁ\u0003\u0010?\"/\u0010Æ\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÃ\u0003\u0010_\u001a\u0005\bÄ\u0003\u0010<\"\u0005\bÅ\u0003\u0010?\"/\u0010Ê\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÇ\u0003\u0010_\u001a\u0005\bÈ\u0003\u0010<\"\u0005\bÉ\u0003\u0010?\"/\u0010Î\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bË\u0003\u0010_\u001a\u0005\bÌ\u0003\u0010<\"\u0005\bÍ\u0003\u0010?\"/\u0010Ò\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÏ\u0003\u0010_\u001a\u0005\bÐ\u0003\u0010<\"\u0005\bÑ\u0003\u0010?\"/\u0010Ö\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÓ\u0003\u0010_\u001a\u0005\bÔ\u0003\u0010<\"\u0005\bÕ\u0003\u0010?\"/\u0010Ú\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b×\u0003\u0010_\u001a\u0005\bØ\u0003\u0010<\"\u0005\bÙ\u0003\u0010?\"/\u0010Þ\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÛ\u0003\u0010_\u001a\u0005\bÜ\u0003\u0010<\"\u0005\bÝ\u0003\u0010?\"/\u0010â\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bß\u0003\u0010_\u001a\u0005\bà\u0003\u0010<\"\u0005\bá\u0003\u0010?\"/\u0010æ\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bã\u0003\u0010_\u001a\u0005\bä\u0003\u0010<\"\u0005\bå\u0003\u0010?\"/\u0010ê\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bç\u0003\u0010_\u001a\u0005\bè\u0003\u0010<\"\u0005\bé\u0003\u0010?\"/\u0010î\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bë\u0003\u0010_\u001a\u0005\bì\u0003\u0010<\"\u0005\bí\u0003\u0010?\"/\u0010ò\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bï\u0003\u0010_\u001a\u0005\bð\u0003\u0010<\"\u0005\bñ\u0003\u0010?\"/\u0010ö\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bó\u0003\u0010_\u001a\u0005\bô\u0003\u0010<\"\u0005\bõ\u0003\u0010?\"/\u0010ú\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b÷\u0003\u0010_\u001a\u0005\bø\u0003\u0010<\"\u0005\bù\u0003\u0010?\"/\u0010þ\u0003\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bû\u0003\u0010_\u001a\u0005\bü\u0003\u0010<\"\u0005\bý\u0003\u0010?\"/\u0010\u0082\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÿ\u0003\u0010_\u001a\u0005\b\u0080\u0004\u0010<\"\u0005\b\u0081\u0004\u0010?\"/\u0010\u0086\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0083\u0004\u0010_\u001a\u0005\b\u0084\u0004\u0010<\"\u0005\b\u0085\u0004\u0010?\"/\u0010\u008a\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0087\u0004\u0010_\u001a\u0005\b\u0088\u0004\u0010<\"\u0005\b\u0089\u0004\u0010?\"/\u0010\u008e\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008b\u0004\u0010_\u001a\u0005\b\u008c\u0004\u0010<\"\u0005\b\u008d\u0004\u0010?\"/\u0010\u0092\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008f\u0004\u0010_\u001a\u0005\b\u0090\u0004\u0010<\"\u0005\b\u0091\u0004\u0010?\"/\u0010\u0096\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0093\u0004\u0010_\u001a\u0005\b\u0094\u0004\u0010<\"\u0005\b\u0095\u0004\u0010?\"/\u0010\u009a\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0097\u0004\u0010_\u001a\u0005\b\u0098\u0004\u0010<\"\u0005\b\u0099\u0004\u0010?\"/\u0010\u009e\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u009b\u0004\u0010_\u001a\u0005\b\u009c\u0004\u0010<\"\u0005\b\u009d\u0004\u0010?\"0\u0010¢\u0004\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u009f\u0004\u0010_\u001a\u0005\b \u0004\u0010A\"\u0006\b¡\u0004\u0010\u009f\u0001\"/\u0010¦\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b£\u0004\u0010_\u001a\u0005\b¤\u0004\u0010<\"\u0005\b¥\u0004\u0010?\"3\u0010\u00ad\u0004\u001a\u00030§\u00042\u0007\u0010]\u001a\u00030§\u00048F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b¨\u0004\u0010_\u001a\u0006\b©\u0004\u0010ª\u0004\"\u0006\b«\u0004\u0010¬\u0004\"/\u0010±\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b®\u0004\u0010_\u001a\u0005\b¯\u0004\u0010<\"\u0005\b°\u0004\u0010?\"/\u0010µ\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b²\u0004\u0010_\u001a\u0005\b³\u0004\u0010<\"\u0005\b´\u0004\u0010?\"/\u0010¹\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¶\u0004\u0010_\u001a\u0005\b·\u0004\u0010<\"\u0005\b¸\u0004\u0010?\"3\u0010À\u0004\u001a\u00030º\u00042\u0007\u0010]\u001a\u00030º\u00048F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b»\u0004\u0010_\u001a\u0006\b¼\u0004\u0010½\u0004\"\u0006\b¾\u0004\u0010¿\u0004\"/\u0010Ä\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÁ\u0004\u0010_\u001a\u0005\bÂ\u0004\u0010<\"\u0005\bÃ\u0004\u0010?\"/\u0010È\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÅ\u0004\u0010_\u001a\u0005\bÆ\u0004\u0010<\"\u0005\bÇ\u0004\u0010?\"/\u0010Ì\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÉ\u0004\u0010_\u001a\u0005\bÊ\u0004\u0010<\"\u0005\bË\u0004\u0010?\"/\u0010Ð\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÍ\u0004\u0010_\u001a\u0005\bÎ\u0004\u0010<\"\u0005\bÏ\u0004\u0010?\"/\u0010Ô\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÑ\u0004\u0010_\u001a\u0005\bÒ\u0004\u0010<\"\u0005\bÓ\u0004\u0010?\"/\u0010Ø\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÕ\u0004\u0010_\u001a\u0005\bÖ\u0004\u0010<\"\u0005\b×\u0004\u0010?\"/\u0010Ü\u0004\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÙ\u0004\u0010_\u001a\u0005\bÚ\u0004\u0010-\"\u0005\bÛ\u0004\u0010b\"/\u0010à\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÝ\u0004\u0010_\u001a\u0005\bÞ\u0004\u0010<\"\u0005\bß\u0004\u0010?\"/\u0010ä\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bá\u0004\u0010_\u001a\u0005\bâ\u0004\u0010<\"\u0005\bã\u0004\u0010?\"/\u0010è\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bå\u0004\u0010_\u001a\u0005\bæ\u0004\u0010<\"\u0005\bç\u0004\u0010?\"/\u0010ì\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bé\u0004\u0010_\u001a\u0005\bê\u0004\u0010<\"\u0005\bë\u0004\u0010?\"/\u0010ð\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bí\u0004\u0010_\u001a\u0005\bî\u0004\u0010<\"\u0005\bï\u0004\u0010?\"/\u0010ô\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bñ\u0004\u0010_\u001a\u0005\bò\u0004\u0010<\"\u0005\bó\u0004\u0010?\"/\u0010ø\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bõ\u0004\u0010_\u001a\u0005\bö\u0004\u0010<\"\u0005\b÷\u0004\u0010?\"/\u0010ü\u0004\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bù\u0004\u0010_\u001a\u0005\bú\u0004\u0010<\"\u0005\bû\u0004\u0010?\"/\u0010\u0080\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bý\u0004\u0010_\u001a\u0005\bþ\u0004\u0010<\"\u0005\bÿ\u0004\u0010?\"1\u0010\u0086\u0005\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0081\u0005\u0010_\u001a\u0006\b\u0082\u0005\u0010\u0083\u0005\"\u0006\b\u0084\u0005\u0010\u0085\u0005\"1\u0010\u008a\u0005\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0087\u0005\u0010_\u001a\u0006\b\u0088\u0005\u0010\u0083\u0005\"\u0006\b\u0089\u0005\u0010\u0085\u0005\"0\u0010\u008e\u0005\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u008b\u0005\u0010_\u001a\u0005\b\u008c\u0005\u0010A\"\u0006\b\u008d\u0005\u0010\u009f\u0001\"0\u0010\u0092\u0005\u001a\u00020!2\u0006\u0010]\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u008f\u0005\u0010_\u001a\u0005\b\u0090\u0005\u0010+\"\u0006\b\u0091\u0005\u0010Á\u0001\"0\u0010\u0096\u0005\u001a\u00020!2\u0006\u0010]\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u0093\u0005\u0010_\u001a\u0005\b\u0094\u0005\u0010+\"\u0006\b\u0095\u0005\u0010Á\u0001\"\u001d\u0010\u0098\u0005\u001a\u00030\u0097\u00058\u0006¢\u0006\u0010\n\u0006\b\u0098\u0005\u0010\u0099\u0005\u001a\u0006\b\u009a\u0005\u0010\u009b\u0005\"&\u0010\u009c\u0005\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u009c\u0005\u0010Y\u001a\u0005\b\u009d\u0005\u0010<\"\u0005\b\u009e\u0005\u0010?\"/\u0010¢\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u009f\u0005\u0010_\u001a\u0005\b \u0005\u0010<\"\u0005\b¡\u0005\u0010?\"/\u0010¦\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b£\u0005\u0010_\u001a\u0005\b¤\u0005\u0010<\"\u0005\b¥\u0005\u0010?\"/\u0010ª\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b§\u0005\u0010_\u001a\u0005\b¨\u0005\u0010<\"\u0005\b©\u0005\u0010?\"/\u0010®\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b«\u0005\u0010_\u001a\u0005\b¬\u0005\u0010<\"\u0005\b\u00ad\u0005\u0010?\"/\u0010²\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b¯\u0005\u0010_\u001a\u0005\b°\u0005\u0010<\"\u0005\b±\u0005\u0010?\"/\u0010¶\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b³\u0005\u0010_\u001a\u0005\b´\u0005\u0010<\"\u0005\bµ\u0005\u0010?\"/\u0010º\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b·\u0005\u0010_\u001a\u0005\b¸\u0005\u0010<\"\u0005\b¹\u0005\u0010?\"?\u0010Á\u0005\u001a\t\u0012\u0004\u0012\u00020\u00050»\u00052\r\u0010]\u001a\t\u0012\u0004\u0012\u00020\u00050»\u00058F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b¼\u0005\u0010_\u001a\u0006\b½\u0005\u0010¾\u0005\"\u0006\b¿\u0005\u0010À\u0005\"/\u0010Å\u0005\u001a\u00020\n2\u0006\u0010]\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÂ\u0005\u0010_\u001a\u0005\bÃ\u0005\u0010<\"\u0005\bÄ\u0005\u0010?\"\u001c\u0010Ê\u0005\u001a\n\u0012\u0005\u0012\u00030Ç\u00050Æ\u00058F¢\u0006\b\u001a\u0006\bÈ\u0005\u0010É\u0005\"'\u0010Í\u0005\u001a\u00020\n2\u0006\u00104\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\bË\u0005\u0010<\"\u0005\bÌ\u0005\u0010?\"\u0013\u0010Ï\u0005\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÎ\u0005\u0010<\"\u0013\u0010Ñ\u0005\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÐ\u0005\u0010<\"\u0013\u0010Ó\u0005\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÒ\u0005\u0010<¨\u0006Ô\u0005"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "ensureApiBotListener", "()V", "Landroid/util/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getApiBotInfo", "()Landroid/util/Pair;", "Lcom/exteragram/messenger/ProxyDisableCondition;", "condition", _UrlKt.FRAGMENT_ENCODE_SET, "isProxyDisabledOn", "(Lcom/exteragram/messenger/ProxyDisableCondition;)Z", "disable", "setProxyDisabledOn", "(Lcom/exteragram/messenger/ProxyDisableCondition;Z)V", "init", "migrateProxyConditions", "migrateTelegramAiInstantViewSummaries", "migrateTransitionAnimation", "migrateStickerTimeMode", "migrateTabCounterMode", "loadConfig", "reloadConfig", "saveMainMenuLayout", "saveIconPacksLayout", "Ljava/util/ArrayList;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/ArrayList;", "getDefaultMainMenuLayout", "()Ljava/util/ArrayList;", "ensureSettingsVisibility", "sanitizeMenu", _UrlKt.FRAGMENT_ENCODE_SET, "size", "px", "forum", "getAvatarCorners", "(FZZ)I", "Lcom/exteragram/messenger/AvatarCornerType;", "type", "(FZLcom/exteragram/messenger/AvatarCornerType;)I", "getAvatarSquareness", "()F", "getOnlineDotOuterRadius", "()I", "getOnlineDotInnerRadius", "baseOffset", "outerRadius", "getOnlineDotOffset", "(FF)F", "getSectionRadiusDp", "value", "sanitizeSectionRadius", "(F)F", "Landroid/content/SharedPreferences;", "systemConfigPrefs", "()Landroid/content/SharedPreferences;", "toggleLogging", "getLogging", "()Z", "enabled", "setLogging", "(Z)V", "getCurrentLangName", "()Ljava/lang/String;", "getDoubleTapSeekDurationMillis", "Lcom/google/gson/Gson;", "GSON", "Lcom/google/gson/Gson;", "getGSON", "()Lcom/google/gson/Gson;", "currentApiBot", "Landroid/util/Pair;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "apiBotListener", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "Landroid/content/SharedPreferences$Editor;", "editor", "Landroid/content/SharedPreferences$Editor;", "getEditor", "()Landroid/content/SharedPreferences$Editor;", _UrlKt.FRAGMENT_ENCODE_SET, "sync", "Ljava/lang/Object;", "configLoaded", "Z", "Ljava/util/concurrent/atomic/AtomicBoolean;", "servicesStarted", "Ljava/util/concurrent/atomic/AtomicBoolean;", "<set-?>", "translationProvider$delegate", "Lcom/exteragram/messenger/config/BasePref;", "getTranslationProvider", "setTranslationProvider", "(I)V", "translationProvider", "disableNumberRounding$delegate", "getDisableNumberRounding", "setDisableNumberRounding", "disableNumberRounding", "formatTimeWithSeconds$delegate", "getFormatTimeWithSeconds", "setFormatTimeWithSeconds", "formatTimeWithSeconds", "relativeLastSeen$delegate", "getRelativeLastSeen", "setRelativeLastSeen", "relativeLastSeen", "inAppVibration$delegate", "getInAppVibration", "setInAppVibration", "inAppVibration", "disableNotificationDelay$delegate", "getDisableNotificationDelay", "setDisableNotificationDelay", "disableNotificationDelay", "filterZalgo$delegate", "getFilterZalgo", "setFilterZalgo", "filterZalgo", "downloadSpeedBoost$delegate", "getDownloadSpeedBoost", "setDownloadSpeedBoost", "downloadSpeedBoost", "uploadSpeedBoost$delegate", "getUploadSpeedBoost", "setUploadSpeedBoost", "uploadSpeedBoost", "hidePhoneNumber$delegate", "getHidePhoneNumber", "setHidePhoneNumber", "hidePhoneNumber", "showIdAndDc$delegate", "getShowIdAndDc", "setShowIdAndDc", "showIdAndDc", "hideArchiveFolder$delegate", "getHideArchiveFolder", "setHideArchiveFolder", "hideArchiveFolder", "archiveOnPull$delegate", "getArchiveOnPull", "setArchiveOnPull", "archiveOnPull", "disableUnarchiveSwipe$delegate", "getDisableUnarchiveSwipe", "setDisableUnarchiveSwipe", "disableUnarchiveSwipe", "doNotUseProxy$delegate", "getDoNotUseProxy", "setDoNotUseProxy", "doNotUseProxy", "customSavePath$delegate", "getCustomSavePath", "setCustomSavePath", "(Ljava/lang/String;)V", "customSavePath", "doNotMarkAsNew", "Ljava/util/ArrayList;", "getDoNotMarkAsNew", "setDoNotMarkAsNew", "(Ljava/util/ArrayList;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "newFeaturesShowedAt", "Ljava/util/HashMap;", "getNewFeaturesShowedAt", "()Ljava/util/HashMap;", "setNewFeaturesShowedAt", "(Ljava/util/HashMap;)V", "Lcom/exteragram/messenger/IconPackType;", "iconPack$delegate", "getIconPack", "()Lcom/exteragram/messenger/IconPackType;", "setIconPack", "(Lcom/exteragram/messenger/IconPackType;)V", "iconPack", "editingIconPackId$delegate", "getEditingIconPackId", "setEditingIconPackId", "editingIconPackId", "iconPacksLayout", "getIconPacksLayout", "setIconPacksLayout", "iconPacksHidden", "getIconPacksHidden", "setIconPacksHidden", "avatarCorners$delegate", "setAvatarCorners", "(F)V", "avatarCorners", "singleCornerRadius$delegate", "getSingleCornerRadius", "setSingleCornerRadius", "singleCornerRadius", "Lcom/exteragram/messenger/DividerStyle;", "dividerStyle$delegate", "getDividerStyle", "()Lcom/exteragram/messenger/DividerStyle;", "setDividerStyle", "(Lcom/exteragram/messenger/DividerStyle;)V", "dividerStyle", "forceSnow$delegate", "getForceSnow", "setForceSnow", "forceSnow", "hideActionBarStatus$delegate", "getHideActionBarStatus", "setHideActionBarStatus", "hideActionBarStatus", "centerTitle$delegate", "getCenterTitle", "setCenterTitle", "centerTitle", "hideStories$delegate", "getHideStories", "setHideStories", "hideStories", "hideFloatingButton$delegate", "getHideFloatingButton", "setHideFloatingButton", "hideFloatingButton", "hideDialogsSearchBar$delegate", "getHideDialogsSearchBar", "setHideDialogsSearchBar", "hideDialogsSearchBar", "senderMiniAvatars$delegate", "getSenderMiniAvatars", "setSenderMiniAvatars", "senderMiniAvatars", "titleText$delegate", "getTitleText", "setTitleText", "titleText", "Lcom/exteragram/messenger/TabIconsMode;", "tabIcons$delegate", "getTabIcons", "()Lcom/exteragram/messenger/TabIconsMode;", "setTabIcons", "(Lcom/exteragram/messenger/TabIconsMode;)V", "tabIcons", "Lcom/exteragram/messenger/TabCounterMode;", "tabCounterMode$delegate", "getTabCounterMode", "()Lcom/exteragram/messenger/TabCounterMode;", "setTabCounterMode", "(Lcom/exteragram/messenger/TabCounterMode;)V", "tabCounterMode", "hideAllChats$delegate", "getHideAllChats", "setHideAllChats", "hideAllChats", "squareFab$delegate", "getSquareFab", "setSquareFab", "squareFab", "sectionRadius$delegate", "getSectionRadius", "setSectionRadius", "sectionRadius", "sectionsSeparatedHeadersPreference$delegate", "getSectionsSeparatedHeadersPreference", "setSectionsSeparatedHeadersPreference", "sectionsSeparatedHeadersPreference", "newLoadingStyle$delegate", "getNewLoadingStyle", "setNewLoadingStyle", "newLoadingStyle", "newSliderStyle$delegate", "getNewSliderStyle", "setNewSliderStyle", "newSliderStyle", "newSwitchStyle$delegate", "getNewSwitchStyle", "setNewSwitchStyle", "newSwitchStyle", "newChatHeaderStyle$delegate", "getNewChatHeaderStyle", "setNewChatHeaderStyle", "newChatHeaderStyle", "newNavigationBarStyle$delegate", "getNewNavigationBarStyle", "setNewNavigationBarStyle", "newNavigationBarStyle", "newFabStyle$delegate", "getNewFabStyle", "setNewFabStyle", "newFabStyle", "tabletMode$delegate", "getTabletMode", "setTabletMode", "tabletMode", "useSystemFonts$delegate", "getUseSystemFonts", "setUseSystemFonts", "useSystemFonts", "gooeyAvatarAnimation$delegate", "getGooeyAvatarAnimation", "setGooeyAvatarAnimation", "gooeyAvatarAnimation", "customThemes$delegate", "getCustomThemes", "setCustomThemes", "customThemes", "predictiveBackIntensity$delegate", "getPredictiveBackIntensity", "setPredictiveBackIntensity", "predictiveBackIntensity", "Lcom/exteragram/messenger/TransitionAnimation;", "transitionAnimation$delegate", "getTransitionAnimation", "()Lcom/exteragram/messenger/TransitionAnimation;", "setTransitionAnimation", "(Lcom/exteragram/messenger/TransitionAnimation;)V", "transitionAnimation", "Lcom/exteragram/messenger/GlassOutlineStyle;", "glassOutlineStyle$delegate", "getGlassOutlineStyle", "()Lcom/exteragram/messenger/GlassOutlineStyle;", "setGlassOutlineStyle", "(Lcom/exteragram/messenger/GlassOutlineStyle;)V", "glassOutlineStyle", "glassMessageMenu$delegate", "getGlassMessageMenu", "setGlassMessageMenu", "glassMessageMenu", "forceBlur$delegate", "getForceBlur", "setForceBlur", "forceBlur", "eventType$delegate", "getEventType", "setEventType", "eventType", "navigationDrawer$delegate", "getNavigationDrawer", "setNavigationDrawer", "navigationDrawer", "immersiveDrawerAnimation$delegate", "getImmersiveDrawerAnimation", "setImmersiveDrawerAnimation", "immersiveDrawerAnimation", "showFeedTab$delegate", "getShowFeedTab", "setShowFeedTab", "showFeedTab", "showFeedUnreadCounter$delegate", "getShowFeedUnreadCounter", "setShowFeedUnreadCounter", "showFeedUnreadCounter", "mainMenuLayout", "getMainMenuLayout", "setMainMenuLayout", "mainMenuHiddenItems", "getMainMenuHiddenItems", "setMainMenuHiddenItems", "stickerSize$delegate", "getStickerSize", "setStickerSize", "stickerSize", "Lcom/exteragram/messenger/StickerTimeMode;", "stickerTimeMode$delegate", "getStickerTimeMode", "()Lcom/exteragram/messenger/StickerTimeMode;", "setStickerTimeMode", "(Lcom/exteragram/messenger/StickerTimeMode;)V", "stickerTimeMode", "replyColors$delegate", "getReplyColors", "setReplyColors", "replyColors", "replyEmoji$delegate", "getReplyEmoji", "setReplyEmoji", "replyEmoji", "replyBackground$delegate", "getReplyBackground", "setReplyBackground", "replyBackground", "stickerShape$delegate", "getStickerShape", "setStickerShape", "stickerShape", "unlimitedRecentStickers$delegate", "getUnlimitedRecentStickers", "setUnlimitedRecentStickers", "unlimitedRecentStickers", "hideReactionsInPrivateChats$delegate", "getHideReactionsInPrivateChats", "setHideReactionsInPrivateChats", "hideReactionsInPrivateChats", "hideReactionsInChannels$delegate", "getHideReactionsInChannels", "setHideReactionsInChannels", "hideReactionsInChannels", "hideReactionsInGroups$delegate", "getHideReactionsInGroups", "setHideReactionsInGroups", "hideReactionsInGroups", "doubleTapAction$delegate", "getDoubleTapAction", "setDoubleTapAction", "doubleTapAction", "doubleTapActionOutOwner$delegate", "getDoubleTapActionOutOwner", "setDoubleTapActionOutOwner", "doubleTapActionOutOwner", "swipeActions$delegate", "getSwipeActions", "setSwipeActions", "swipeActions", "swipeActionsLoop$delegate", "getSwipeActionsLoop", "setSwipeActionsLoop", "swipeActionsLoop", "swipeActionsReversed$delegate", "getSwipeActionsReversed", "setSwipeActionsReversed", "swipeActionsReversed", "bottomButton$delegate", "getBottomButton", "setBottomButton", "bottomButton", "widePostsInFeed$delegate", "getWidePostsInFeed", "setWidePostsInFeed", "widePostsInFeed", "widePostsInChannels$delegate", "getWidePostsInChannels", "setWidePostsInChannels", "widePostsInChannels", "telegramAiEditor$delegate", "getTelegramAiEditor", "setTelegramAiEditor", "telegramAiEditor", "telegramAiSummaries$delegate", "getTelegramAiSummaries", "setTelegramAiSummaries", "telegramAiSummaries", "telegramAiInstantViewSummaries$delegate", "getTelegramAiInstantViewSummaries", "setTelegramAiInstantViewSummaries", "telegramAiInstantViewSummaries", "quickAdminShortcuts$delegate", "getQuickAdminShortcuts", "setQuickAdminShortcuts", "quickAdminShortcuts", "quickTransitionForChannels$delegate", "getQuickTransitionForChannels", "setQuickTransitionForChannels", "quickTransitionForChannels", "quickTransitionForTopics$delegate", "getQuickTransitionForTopics", "setQuickTransitionForTopics", "quickTransitionForTopics", "disableGreetingSticker$delegate", "getDisableGreetingSticker", "setDisableGreetingSticker", "disableGreetingSticker", "hideKeyboardOnScroll$delegate", "getHideKeyboardOnScroll", "setHideKeyboardOnScroll", "hideKeyboardOnScroll", "addCommaAfterMention$delegate", "getAddCommaAfterMention", "setAddCommaAfterMention", "addCommaAfterMention", "inlineMathResult$delegate", "getInlineMathResult", "setInlineMathResult", "inlineMathResult", "disableMarkdown$delegate", "getDisableMarkdown", "setDisableMarkdown", "disableMarkdown", "hideSendAsPeer$delegate", "getHideSendAsPeer", "setHideSendAsPeer", "hideSendAsPeer", "removeMessageTail$delegate", "getRemoveMessageTail", "setRemoveMessageTail", "removeMessageTail", "replaceEditedWithIcon$delegate", "getReplaceEditedWithIcon", "setReplaceEditedWithIcon", "replaceEditedWithIcon", "showOnlineStatus$delegate", "getShowOnlineStatus", "setShowOnlineStatus", "showOnlineStatus", "showForwardsCount$delegate", "getShowForwardsCount", "setShowForwardsCount", "showForwardsCount", "hideShareButton$delegate", "getHideShareButton", "setHideShareButton", "hideShareButton", "showResultsBeforeVoting$delegate", "getShowResultsBeforeVoting", "setShowResultsBeforeVoting", "showResultsBeforeVoting", "showCopyPhotoButton$delegate", "getShowCopyPhotoButton", "setShowCopyPhotoButton", "showCopyPhotoButton", "showSaveMessageButton$delegate", "getShowSaveMessageButton", "setShowSaveMessageButton", "showSaveMessageButton", "showRepeatMessageButton$delegate", "getShowRepeatMessageButton", "setShowRepeatMessageButton", "showRepeatMessageButton", "showClearButton$delegate", "getShowClearButton", "setShowClearButton", "showClearButton", "showHistoryButton$delegate", "getShowHistoryButton", "setShowHistoryButton", "showHistoryButton", "showReportButton$delegate", "getShowReportButton", "setShowReportButton", "showReportButton", "showGenerateButton$delegate", "getShowGenerateButton", "setShowGenerateButton", "showGenerateButton", "showDetailsButton$delegate", "getShowDetailsButton", "setShowDetailsButton", "showDetailsButton", "groupMessageMenu$delegate", "getGroupMessageMenu", "setGroupMessageMenu", "groupMessageMenu", "recognitionLanguage$delegate", "getRecognitionLanguage", "setRecognitionLanguage", "recognitionLanguage", "postprocessingWithAi$delegate", "getPostprocessingWithAi", "setPostprocessingWithAi", "postprocessingWithAi", "Lcom/exteragram/messenger/CameraType;", "cameraType$delegate", "getCameraType", "()Lcom/exteragram/messenger/CameraType;", "setCameraType", "(Lcom/exteragram/messenger/CameraType;)V", "cameraType", "extendedFramesPerSecond$delegate", "getExtendedFramesPerSecond", "setExtendedFramesPerSecond", "extendedFramesPerSecond", "cameraStabilization$delegate", "getCameraStabilization", "setCameraStabilization", "cameraStabilization", "cameraMirrorMode$delegate", "getCameraMirrorMode", "setCameraMirrorMode", "cameraMirrorMode", "Lcom/exteragram/messenger/VideoMessagesCamera;", "videoMessagesCamera$delegate", "getVideoMessagesCamera", "()Lcom/exteragram/messenger/VideoMessagesCamera;", "setVideoMessagesCamera", "(Lcom/exteragram/messenger/VideoMessagesCamera;)V", "videoMessagesCamera", "rememberLastUsedCamera$delegate", "getRememberLastUsedCamera", "setRememberLastUsedCamera", "rememberLastUsedCamera", "startWithWideAngleCamera$delegate", "getStartWithWideAngleCamera", "setStartWithWideAngleCamera", "startWithWideAngleCamera", "zoomSlider$delegate", "getZoomSlider", "setZoomSlider", "zoomSlider", "staticZoom$delegate", "getStaticZoom", "setStaticZoom", "staticZoom", "alwaysSendInHD$delegate", "getAlwaysSendInHD", "setAlwaysSendInHD", "alwaysSendInHD", "hideCameraTile$delegate", "getHideCameraTile", "setHideCameraTile", "hideCameraTile", "doubleTapSeekDuration$delegate", "getDoubleTapSeekDuration", "setDoubleTapSeekDuration", "doubleTapSeekDuration", "preferOriginalQuality$delegate", "getPreferOriginalQuality", "setPreferOriginalQuality", "preferOriginalQuality", "swipeToPip$delegate", "getSwipeToPip", "setSwipeToPip", "swipeToPip", "unmuteWithVolumeButtons$delegate", "getUnmuteWithVolumeButtons", "setUnmuteWithVolumeButtons", "unmuteWithVolumeButtons", "pauseOnMinimizeVideo$delegate", "getPauseOnMinimizeVideo", "setPauseOnMinimizeVideo", "pauseOnMinimizeVideo", "pauseOnMinimizeVoice$delegate", "getPauseOnMinimizeVoice", "setPauseOnMinimizeVoice", "pauseOnMinimizeVoice", "pauseOnMinimizeRound$delegate", "getPauseOnMinimizeRound", "setPauseOnMinimizeRound", "pauseOnMinimizeRound", "useGoogleCrashlytics$delegate", "getUseGoogleCrashlytics", "setUseGoogleCrashlytics", "useGoogleCrashlytics", "useGoogleAnalytics$delegate", "getUseGoogleAnalytics", "setUseGoogleAnalytics", "useGoogleAnalytics", "enableAdBlock$delegate", "getEnableAdBlock", "setEnableAdBlock", "enableAdBlock", "updateScheduleTimestamp$delegate", "getUpdateScheduleTimestamp", "()J", "setUpdateScheduleTimestamp", "(J)V", "updateScheduleTimestamp", "sdkUpdateScheduleTimestamp$delegate", "getSdkUpdateScheduleTimestamp", "setSdkUpdateScheduleTimestamp", "sdkUpdateScheduleTimestamp", "targetLang$delegate", "getTargetLang", "setTargetLang", "targetLang", "flashWarmth$delegate", "getFlashWarmth", "setFlashWarmth", "flashWarmth", "flashIntensity$delegate", "getFlashIntensity", "setFlashIntensity", "flashIntensity", "Lorg/telegram/ui/web/SearchEngine;", "yandexSearchEngine", "Lorg/telegram/ui/web/SearchEngine;", "getYandexSearchEngine", "()Lorg/telegram/ui/web/SearchEngine;", "pluginsEngine", "getPluginsEngine", "setPluginsEngine", "pluginsDevMode$delegate", "getPluginsDevMode", "setPluginsDevMode", "pluginsDevMode", "pluginsSafeMode$delegate", "getPluginsSafeMode", "setPluginsSafeMode", "pluginsSafeMode", "pluginsCompactView$delegate", "getPluginsCompactView", "setPluginsCompactView", "pluginsCompactView", "pluginsPySdkAutoUpdate$delegate", "getPluginsPySdkAutoUpdate", "setPluginsPySdkAutoUpdate", "pluginsPySdkAutoUpdate", "pluginsPySdkBetaVersions$delegate", "getPluginsPySdkBetaVersions", "setPluginsPySdkBetaVersions", "pluginsPySdkBetaVersions", "pluginsDisableArtOpts$delegate", "getPluginsDisableArtOpts", "setPluginsDisableArtOpts", "pluginsDisableArtOpts", "pluginsUnknownSources$delegate", "getPluginsUnknownSources", "setPluginsUnknownSources", "pluginsUnknownSources", _UrlKt.FRAGMENT_ENCODE_SET, "pinnedPlugins$delegate", "getPinnedPlugins", "()Ljava/util/Set;", "setPinnedPlugins", "(Ljava/util/Set;)V", "pinnedPlugins", "useSystemIconShape$delegate", "getUseSystemIconShape", "setUseSystemIconShape", "useSystemIconShape", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;", "getBackupKeys", "()[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;", "backupKeys", "getSectionsSeparatedHeaders", "setSectionsSeparatedHeaders", "sectionsSeparatedHeaders", "getSpringAnimations", "springAnimations", "getAospTransitions", "aospTransitions", "getSpringSwipeback", "springSwipeback", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "ExteraConfig")
@SourceDebugExtension({"SMAP\nExteraConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExteraConfig.kt\ncom/exteragram/messenger/ExteraConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,704:1\n1#2:705\n37#3,2:706\n14113#4,3:708\n1915#5,2:711\n296#5,2:713\n41#6,12:715\n41#6,12:727\n*S KotlinDebug\n*F\n+ 1 ExteraConfig.kt\ncom/exteragram/messenger/ExteraConfig\n*L\n185#1:706,2\n501#1:708,3\n587#1:711,2\n599#1:713,2\n686#1:715,12\n694#1:727,12\n*E\n"})
/* loaded from: classes.dex */
public abstract class ExteraConfig {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = null;
    private static final Gson GSON = null;
    private static final BasePref addCommaAfterMention$delegate = null;
    private static final BasePref alwaysSendInHD$delegate = null;
    private static SharedPreferences.OnSharedPreferenceChangeListener apiBotListener;
    private static final BasePref archiveOnPull$delegate = null;
    private static final BasePref avatarCorners$delegate = null;
    private static final BasePref bottomButton$delegate = null;
    private static final BasePref cameraMirrorMode$delegate = null;
    private static final BasePref cameraStabilization$delegate = null;
    private static final BasePref cameraType$delegate = null;
    private static final BasePref centerTitle$delegate = null;
    private static boolean configLoaded;
    private static volatile Pair<Long, String> currentApiBot;
    private static final BasePref customSavePath$delegate = null;
    private static final BasePref customThemes$delegate = null;
    private static final BasePref disableGreetingSticker$delegate = null;
    private static final BasePref disableMarkdown$delegate = null;
    private static final BasePref disableNotificationDelay$delegate = null;
    private static final BasePref disableNumberRounding$delegate = null;
    private static final BasePref disableUnarchiveSwipe$delegate = null;
    private static final BasePref dividerStyle$delegate = null;
    private static ArrayList<String> doNotMarkAsNew;
    private static final BasePref doNotUseProxy$delegate = null;
    private static final BasePref doubleTapAction$delegate = null;
    private static final BasePref doubleTapActionOutOwner$delegate = null;
    private static final BasePref doubleTapSeekDuration$delegate = null;
    private static final BasePref downloadSpeedBoost$delegate = null;
    private static final BasePref editingIconPackId$delegate = null;
    private static final SharedPreferences.Editor editor = null;
    private static final BasePref enableAdBlock$delegate = null;
    private static final BasePref eventType$delegate = null;
    private static final BasePref extendedFramesPerSecond$delegate = null;
    private static final BasePref filterZalgo$delegate = null;
    private static final BasePref flashIntensity$delegate = null;
    private static final BasePref flashWarmth$delegate = null;
    private static final BasePref forceBlur$delegate = null;
    private static final BasePref forceSnow$delegate = null;
    private static final BasePref formatTimeWithSeconds$delegate = null;
    private static final BasePref glassMessageMenu$delegate = null;
    private static final BasePref glassOutlineStyle$delegate = null;
    private static final BasePref gooeyAvatarAnimation$delegate = null;
    private static final BasePref groupMessageMenu$delegate = null;
    private static final BasePref hideActionBarStatus$delegate = null;
    private static final BasePref hideAllChats$delegate = null;
    private static final BasePref hideArchiveFolder$delegate = null;
    private static final BasePref hideCameraTile$delegate = null;
    private static final BasePref hideDialogsSearchBar$delegate = null;
    private static final BasePref hideFloatingButton$delegate = null;
    private static final BasePref hideKeyboardOnScroll$delegate = null;
    private static final BasePref hidePhoneNumber$delegate = null;
    private static final BasePref hideReactionsInChannels$delegate = null;
    private static final BasePref hideReactionsInGroups$delegate = null;
    private static final BasePref hideReactionsInPrivateChats$delegate = null;
    private static final BasePref hideSendAsPeer$delegate = null;
    private static final BasePref hideShareButton$delegate = null;
    private static final BasePref hideStories$delegate = null;
    private static final BasePref iconPack$delegate = null;
    private static ArrayList<String> iconPacksHidden;
    private static ArrayList<String> iconPacksLayout;
    private static final BasePref immersiveDrawerAnimation$delegate = null;
    private static final BasePref inAppVibration$delegate = null;
    private static final BasePref inlineMathResult$delegate = null;
    private static ArrayList<Integer> mainMenuHiddenItems;
    private static ArrayList<Integer> mainMenuLayout;
    private static final BasePref navigationDrawer$delegate = null;
    private static final BasePref newChatHeaderStyle$delegate = null;
    private static final BasePref newFabStyle$delegate = null;
    private static HashMap<String, Long> newFeaturesShowedAt;
    private static final BasePref newLoadingStyle$delegate = null;
    private static final BasePref newNavigationBarStyle$delegate = null;
    private static final BasePref newSliderStyle$delegate = null;
    private static final BasePref newSwitchStyle$delegate = null;
    private static final BasePref pauseOnMinimizeRound$delegate = null;
    private static final BasePref pauseOnMinimizeVideo$delegate = null;
    private static final BasePref pauseOnMinimizeVoice$delegate = null;
    private static final BasePref pinnedPlugins$delegate = null;
    private static final BasePref pluginsCompactView$delegate = null;
    private static final BasePref pluginsDevMode$delegate = null;
    private static final BasePref pluginsDisableArtOpts$delegate = null;
    private static boolean pluginsEngine;
    private static final BasePref pluginsPySdkAutoUpdate$delegate = null;
    private static final BasePref pluginsPySdkBetaVersions$delegate = null;
    private static final BasePref pluginsSafeMode$delegate = null;
    private static final BasePref pluginsUnknownSources$delegate = null;
    private static final BasePref postprocessingWithAi$delegate = null;
    private static final BasePref predictiveBackIntensity$delegate = null;
    private static final BasePref preferOriginalQuality$delegate = null;
    private static final SharedPreferences preferences = null;
    private static final BasePref quickAdminShortcuts$delegate = null;
    private static final BasePref quickTransitionForChannels$delegate = null;
    private static final BasePref quickTransitionForTopics$delegate = null;
    private static final BasePref recognitionLanguage$delegate = null;
    private static final BasePref relativeLastSeen$delegate = null;
    private static final BasePref rememberLastUsedCamera$delegate = null;
    private static final BasePref removeMessageTail$delegate = null;
    private static final BasePref replaceEditedWithIcon$delegate = null;
    private static final BasePref replyBackground$delegate = null;
    private static final BasePref replyColors$delegate = null;
    private static final BasePref replyEmoji$delegate = null;
    private static final BasePref sdkUpdateScheduleTimestamp$delegate = null;
    private static final BasePref sectionRadius$delegate = null;
    private static final BasePref sectionsSeparatedHeadersPreference$delegate = null;
    private static final BasePref senderMiniAvatars$delegate = null;
    private static final AtomicBoolean servicesStarted = null;
    private static final BasePref showClearButton$delegate = null;
    private static final BasePref showCopyPhotoButton$delegate = null;
    private static final BasePref showDetailsButton$delegate = null;
    private static final BasePref showFeedTab$delegate = null;
    private static final BasePref showFeedUnreadCounter$delegate = null;
    private static final BasePref showForwardsCount$delegate = null;
    private static final BasePref showGenerateButton$delegate = null;
    private static final BasePref showHistoryButton$delegate = null;
    private static final BasePref showIdAndDc$delegate = null;
    private static final BasePref showOnlineStatus$delegate = null;
    private static final BasePref showRepeatMessageButton$delegate = null;
    private static final BasePref showReportButton$delegate = null;
    private static final BasePref showResultsBeforeVoting$delegate = null;
    private static final BasePref showSaveMessageButton$delegate = null;
    private static final BasePref singleCornerRadius$delegate = null;
    private static final BasePref squareFab$delegate = null;
    private static final BasePref startWithWideAngleCamera$delegate = null;
    private static final BasePref staticZoom$delegate = null;
    private static final BasePref stickerShape$delegate = null;
    private static final BasePref stickerSize$delegate = null;
    private static final BasePref stickerTimeMode$delegate = null;
    private static final BasePref swipeActions$delegate = null;
    private static final BasePref swipeActionsLoop$delegate = null;
    private static final BasePref swipeActionsReversed$delegate = null;
    private static final BasePref swipeToPip$delegate = null;
    private static final Object sync = null;
    private static final BasePref tabCounterMode$delegate = null;
    private static final BasePref tabIcons$delegate = null;
    private static final BasePref tabletMode$delegate = null;
    private static final BasePref targetLang$delegate = null;
    private static final BasePref telegramAiEditor$delegate = null;
    private static final BasePref telegramAiInstantViewSummaries$delegate = null;
    private static final BasePref telegramAiSummaries$delegate = null;
    private static final BasePref titleText$delegate = null;
    private static final BasePref transitionAnimation$delegate = null;
    private static final BasePref translationProvider$delegate = null;
    private static final BasePref unlimitedRecentStickers$delegate = null;
    private static final BasePref unmuteWithVolumeButtons$delegate = null;
    private static final BasePref updateScheduleTimestamp$delegate = null;
    private static final BasePref uploadSpeedBoost$delegate = null;
    private static final BasePref useGoogleAnalytics$delegate = null;
    private static final BasePref useGoogleCrashlytics$delegate = null;
    private static final BasePref useSystemFonts$delegate = null;
    private static final BasePref useSystemIconShape$delegate = null;
    private static final BasePref videoMessagesCamera$delegate = null;
    private static final BasePref widePostsInChannels$delegate = null;
    private static final BasePref widePostsInFeed$delegate = null;
    private static final SearchEngine yandexSearchEngine = null;
    private static final BasePref zoomSlider$delegate = null;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[AvatarCornerType.values().length];
            r0[AvatarCornerType.DEFAULT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[AvatarCornerType.FORUM.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[AvatarCornerType.COMMUNITY.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static boolean $r8$lambda$LBWoeDTzWk37H7bS_D2ZWFl9Qpk(Function1 r0, Object r1) {
        return ((Boolean) r0.invoke(r1)).booleanValue();
    }

    public static boolean $r8$lambda$cwagzyc4j5wuDisLM2aTqpkp_mA(Integer r2) {
        int r0 = MainMenuItem.DIVIDER.getId();
        if (r2.intValue() != r0) goto L5;
        return false;
    L5:
        if (MainMenuItem.Companion.getById(r2.intValue()) != null) goto L10;
        return true;
    L10:
        return false;
    }

    public static void $r8$lambda$qDl6zRULA7E8NgQw7QvqN3PusmY() {
        PluginsController.Companion r0 = PluginsController.Companion;
        r0.getInstance().executeOnAppEvent(PluginsConstants.APP_START);
        if (ApplicationLoader.mainInterfacePaused == true) goto L6;
        r0.getInstance().executeOnAppEvent(PluginsConstants.APP_RESUME);
        return;
    }

    public static boolean $r8$lambda$rTCyWqjIjy2iDhNn3CjDtRhd4CQ(Function1 r0, Object r1) {
        return ((Boolean) r0.invoke(r1)).booleanValue();
    }

    public static void $r8$lambda$sJiRp01jFTgefgfVL2kALhOxcDI(SharedPreferences r0, String r1) {
        if (Intrinsics.areEqual("extera_api_bot", r1) == false) goto L6;
        currentApiBot = null;
        return;
    }

    public static boolean $r8$lambda$vNyuVIykpVCqHa3SuBwUTSLnUXM(Integer r2) {
        int r0 = MainMenuItem.DIVIDER.getId();
        if (r2.intValue() != r0) goto L5;
        return false;
    L5:
        if (MainMenuItem.Companion.getById(r2.intValue()) != null) goto L10;
        return true;
    L10:
        return false;
    }

    static {
        KProperty<?>[] r1 = {Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "translationProvider", "getTranslationProvider()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "disableNumberRounding", "getDisableNumberRounding()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "formatTimeWithSeconds", "getFormatTimeWithSeconds()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "relativeLastSeen", "getRelativeLastSeen()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "inAppVibration", "getInAppVibration()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "disableNotificationDelay", "getDisableNotificationDelay()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "filterZalgo", "getFilterZalgo()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "downloadSpeedBoost", "getDownloadSpeedBoost()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "uploadSpeedBoost", "getUploadSpeedBoost()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hidePhoneNumber", "getHidePhoneNumber()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showIdAndDc", "getShowIdAndDc()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideArchiveFolder", "getHideArchiveFolder()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "archiveOnPull", "getArchiveOnPull()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "disableUnarchiveSwipe", "getDisableUnarchiveSwipe()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "doNotUseProxy", "getDoNotUseProxy()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "customSavePath", "getCustomSavePath()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "iconPack", "getIconPack()Lcom/exteragram/messenger/IconPackType;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "editingIconPackId", "getEditingIconPackId()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "avatarCorners", "getAvatarCorners()F", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "singleCornerRadius", "getSingleCornerRadius()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "dividerStyle", "getDividerStyle()Lcom/exteragram/messenger/DividerStyle;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "forceSnow", "getForceSnow()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideActionBarStatus", "getHideActionBarStatus()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "centerTitle", "getCenterTitle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideStories", "getHideStories()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideFloatingButton", "getHideFloatingButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideDialogsSearchBar", "getHideDialogsSearchBar()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "senderMiniAvatars", "getSenderMiniAvatars()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "titleText", "getTitleText()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "tabIcons", "getTabIcons()Lcom/exteragram/messenger/TabIconsMode;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "tabCounterMode", "getTabCounterMode()Lcom/exteragram/messenger/TabCounterMode;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideAllChats", "getHideAllChats()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "squareFab", "getSquareFab()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "sectionRadius", "getSectionRadius()F", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "sectionsSeparatedHeadersPreference", "getSectionsSeparatedHeadersPreference()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "newLoadingStyle", "getNewLoadingStyle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "newSliderStyle", "getNewSliderStyle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "newSwitchStyle", "getNewSwitchStyle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "newChatHeaderStyle", "getNewChatHeaderStyle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "newNavigationBarStyle", "getNewNavigationBarStyle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "newFabStyle", "getNewFabStyle()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "tabletMode", "getTabletMode()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "useSystemFonts", "getUseSystemFonts()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "gooeyAvatarAnimation", "getGooeyAvatarAnimation()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "customThemes", "getCustomThemes()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "predictiveBackIntensity", "getPredictiveBackIntensity()F", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "transitionAnimation", "getTransitionAnimation()Lcom/exteragram/messenger/TransitionAnimation;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "glassOutlineStyle", "getGlassOutlineStyle()Lcom/exteragram/messenger/GlassOutlineStyle;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "glassMessageMenu", "getGlassMessageMenu()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "forceBlur", "getForceBlur()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "eventType", "getEventType()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "navigationDrawer", "getNavigationDrawer()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "immersiveDrawerAnimation", "getImmersiveDrawerAnimation()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showFeedTab", "getShowFeedTab()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showFeedUnreadCounter", "getShowFeedUnreadCounter()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "stickerSize", "getStickerSize()F", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "stickerTimeMode", "getStickerTimeMode()Lcom/exteragram/messenger/StickerTimeMode;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "replyColors", "getReplyColors()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "replyEmoji", "getReplyEmoji()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "replyBackground", "getReplyBackground()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "stickerShape", "getStickerShape()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "unlimitedRecentStickers", "getUnlimitedRecentStickers()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideReactionsInPrivateChats", "getHideReactionsInPrivateChats()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideReactionsInChannels", "getHideReactionsInChannels()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideReactionsInGroups", "getHideReactionsInGroups()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "doubleTapAction", "getDoubleTapAction()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "doubleTapActionOutOwner", "getDoubleTapActionOutOwner()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "swipeActions", "getSwipeActions()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "swipeActionsLoop", "getSwipeActionsLoop()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "swipeActionsReversed", "getSwipeActionsReversed()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "bottomButton", "getBottomButton()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "widePostsInFeed", "getWidePostsInFeed()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "widePostsInChannels", "getWidePostsInChannels()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "telegramAiEditor", "getTelegramAiEditor()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "telegramAiSummaries", "getTelegramAiSummaries()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "telegramAiInstantViewSummaries", "getTelegramAiInstantViewSummaries()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "quickAdminShortcuts", "getQuickAdminShortcuts()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "quickTransitionForChannels", "getQuickTransitionForChannels()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "quickTransitionForTopics", "getQuickTransitionForTopics()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "disableGreetingSticker", "getDisableGreetingSticker()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideKeyboardOnScroll", "getHideKeyboardOnScroll()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "addCommaAfterMention", "getAddCommaAfterMention()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "inlineMathResult", "getInlineMathResult()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "disableMarkdown", "getDisableMarkdown()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideSendAsPeer", "getHideSendAsPeer()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "removeMessageTail", "getRemoveMessageTail()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "replaceEditedWithIcon", "getReplaceEditedWithIcon()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showOnlineStatus", "getShowOnlineStatus()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showForwardsCount", "getShowForwardsCount()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideShareButton", "getHideShareButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showResultsBeforeVoting", "getShowResultsBeforeVoting()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showCopyPhotoButton", "getShowCopyPhotoButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showSaveMessageButton", "getShowSaveMessageButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showRepeatMessageButton", "getShowRepeatMessageButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showClearButton", "getShowClearButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showHistoryButton", "getShowHistoryButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showReportButton", "getShowReportButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showGenerateButton", "getShowGenerateButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "showDetailsButton", "getShowDetailsButton()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "groupMessageMenu", "getGroupMessageMenu()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "recognitionLanguage", "getRecognitionLanguage()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "postprocessingWithAi", "getPostprocessingWithAi()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "cameraType", "getCameraType()Lcom/exteragram/messenger/CameraType;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "extendedFramesPerSecond", "getExtendedFramesPerSecond()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "cameraStabilization", "getCameraStabilization()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "cameraMirrorMode", "getCameraMirrorMode()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "videoMessagesCamera", "getVideoMessagesCamera()Lcom/exteragram/messenger/VideoMessagesCamera;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "rememberLastUsedCamera", "getRememberLastUsedCamera()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "startWithWideAngleCamera", "getStartWithWideAngleCamera()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "zoomSlider", "getZoomSlider()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "staticZoom", "getStaticZoom()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "alwaysSendInHD", "getAlwaysSendInHD()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "hideCameraTile", "getHideCameraTile()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "doubleTapSeekDuration", "getDoubleTapSeekDuration()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "preferOriginalQuality", "getPreferOriginalQuality()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "swipeToPip", "getSwipeToPip()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "unmuteWithVolumeButtons", "getUnmuteWithVolumeButtons()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pauseOnMinimizeVideo", "getPauseOnMinimizeVideo()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pauseOnMinimizeVoice", "getPauseOnMinimizeVoice()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pauseOnMinimizeRound", "getPauseOnMinimizeRound()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "useGoogleCrashlytics", "getUseGoogleCrashlytics()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "useGoogleAnalytics", "getUseGoogleAnalytics()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "enableAdBlock", "getEnableAdBlock()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "updateScheduleTimestamp", "getUpdateScheduleTimestamp()J", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "sdkUpdateScheduleTimestamp", "getSdkUpdateScheduleTimestamp()J", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "targetLang", "getTargetLang()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "flashWarmth", "getFlashWarmth()F", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "flashIntensity", "getFlashIntensity()F", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsDevMode", "getPluginsDevMode()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsSafeMode", "getPluginsSafeMode()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsCompactView", "getPluginsCompactView()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsPySdkAutoUpdate", "getPluginsPySdkAutoUpdate()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsPySdkBetaVersions", "getPluginsPySdkBetaVersions()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsDisableArtOpts", "getPluginsDisableArtOpts()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pluginsUnknownSources", "getPluginsUnknownSources()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "pinnedPlugins", "getPinnedPlugins()Ljava/util/Set;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(ExteraConfig.class, "useSystemIconShape", "getUseSystemIconShape()Z", 1))};
        $$delegatedProperties = r1;
        GSON = new Gson();
        SharedPreferences r0 = PreferencesUtils.getPreferences("exteraconfig");
        preferences = r0;
        editor = r0.edit();
        sync = new Object();
        servicesStarted = new AtomicBoolean(false);
        translationProvider$delegate = new IntegerPref(0, null, 2, null).provideDelegate(null, r1[0]);
        disableNumberRounding$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[1]);
        formatTimeWithSeconds$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[2]);
        relativeLastSeen$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[3]);
        inAppVibration$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[4]);
        disableNotificationDelay$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[5]);
        filterZalgo$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[6]);
        downloadSpeedBoost$delegate = new IntegerPref(0, null, 2, null).provideDelegate(null, r1[7]);
        uploadSpeedBoost$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[8]);
        hidePhoneNumber$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[9]);
        showIdAndDc$delegate = new IntegerPref(1, null, 2, null).provideDelegate(null, r1[10]);
        hideArchiveFolder$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[11]);
        archiveOnPull$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[12]);
        disableUnarchiveSwipe$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[13]);
        doNotUseProxy$delegate = new IntegerPref(0, null, 2, null).provideDelegate(null, r1[14]);
        customSavePath$delegate = new StringPref("exteraGram", null, 2, null).provideDelegate(null, r1[15]);
        doNotMarkAsNew = new ArrayList();
        newFeaturesShowedAt = new HashMap();
        iconPack$delegate = new EnumPref(IconPackType.DEFAULT, null, 2, null).provideDelegate(null, r1[16]);
        editingIconPackId$delegate = new NullableStringPref(null, null, 2, null).provideDelegate(null, r1[17]);
        iconPacksLayout = new ArrayList();
        iconPacksHidden = new ArrayList();
        avatarCorners$delegate = new FloatPref(28.0f, null, 2, null).provideDelegate(null, r1[18]);
        singleCornerRadius$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[19]);
        dividerStyle$delegate = new EnumPref(DividerStyle.LINE, null, 2, null).provideDelegate(null, r1[20]);
        forceSnow$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[21]);
        hideActionBarStatus$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[22]);
        centerTitle$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[23]);
        hideStories$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[24]);
        hideFloatingButton$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[25]);
        hideDialogsSearchBar$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[26]);
        senderMiniAvatars$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[27]);
        titleText$delegate = new IntegerPref(0, null, 2, null).provideDelegate(null, r1[28]);
        tabIcons$delegate = new EnumPref(TabIconsMode.TITLES_ONLY, null, 2, null).provideDelegate(null, r1[29]);
        tabCounterMode$delegate = new EnumPref(TabCounterMode.ALL, null, 2, null).provideDelegate(null, r1[30]);
        hideAllChats$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[31]);
        squareFab$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[32]);
        sectionRadius$delegate = new FloatPref(20.0f, null, 2, null).provideDelegate(null, r1[33]);
        sectionsSeparatedHeadersPreference$delegate = new BooleanPref(true, "sectionsSeparatedHeaders").provideDelegate(null, r1[34]);
        newLoadingStyle$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[35]);
        newSliderStyle$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[36]);
        newSwitchStyle$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[37]);
        newChatHeaderStyle$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[38]);
        newNavigationBarStyle$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[39]);
        newFabStyle$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[40]);
        tabletMode$delegate = new IntegerPref(0, null, 2, null).provideDelegate(null, r1[41]);
        useSystemFonts$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[42]);
        gooeyAvatarAnimation$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[43]);
        customThemes$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[44]);
        predictiveBackIntensity$delegate = new FloatPref(1.0f, null, 2, null).provideDelegate(null, r1[45]);
        transitionAnimation$delegate = new EnumPref(TransitionAnimation.SPRING, null, 2, null).provideDelegate(null, r1[46]);
        glassOutlineStyle$delegate = new EnumPref(GlassOutlineStyle.GLARE, null, 2, null).provideDelegate(null, r1[47]);
        glassMessageMenu$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[48]);
        forceBlur$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[49]);
        eventType$delegate = new IntegerPref(0, null, 2, null).provideDelegate(null, r1[50]);
        navigationDrawer$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[51]);
        immersiveDrawerAnimation$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[52]);
        showFeedTab$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[53]);
        showFeedUnreadCounter$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[54]);
        mainMenuLayout = new ArrayList();
        mainMenuHiddenItems = new ArrayList();
        stickerSize$delegate = new FloatPref(12.0f, null, 2, null).provideDelegate(null, r1[55]);
        stickerTimeMode$delegate = new EnumPref(StickerTimeMode.DEFAULT, null, 2, null).provideDelegate(null, r1[56]);
        replyColors$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[57]);
        replyEmoji$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[58]);
        replyBackground$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[59]);
        stickerShape$delegate = new IntegerPref(1, null, 2, null).provideDelegate(null, r1[60]);
        unlimitedRecentStickers$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[61]);
        hideReactionsInPrivateChats$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[62]);
        hideReactionsInChannels$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[63]);
        hideReactionsInGroups$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[64]);
        doubleTapAction$delegate = new SanitizedIntegerPref(1, null, 2, null).provideDelegate(null, r1[65]);
        doubleTapActionOutOwner$delegate = new SanitizedIntegerPref(1, null, 2, null).provideDelegate(null, r1[66]);
        swipeActions$delegate = new StringPref("2", null, 2, null).provideDelegate(null, r1[67]);
        swipeActionsLoop$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[68]);
        swipeActionsReversed$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[69]);
        bottomButton$delegate = new IntegerPref(2, null, 2, null).provideDelegate(null, r1[70]);
        widePostsInFeed$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[71]);
        widePostsInChannels$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[72]);
        telegramAiEditor$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[73]);
        telegramAiSummaries$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[74]);
        telegramAiInstantViewSummaries$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[75]);
        quickAdminShortcuts$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[76]);
        quickTransitionForChannels$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[77]);
        quickTransitionForTopics$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[78]);
        disableGreetingSticker$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[79]);
        hideKeyboardOnScroll$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[80]);
        addCommaAfterMention$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[81]);
        inlineMathResult$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[82]);
        disableMarkdown$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[83]);
        hideSendAsPeer$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[84]);
        removeMessageTail$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[85]);
        replaceEditedWithIcon$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[86]);
        showOnlineStatus$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[87]);
        showForwardsCount$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[88]);
        hideShareButton$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[89]);
        showResultsBeforeVoting$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[90]);
        showCopyPhotoButton$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[91]);
        showSaveMessageButton$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[92]);
        showRepeatMessageButton$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[93]);
        showClearButton$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[94]);
        showHistoryButton$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[95]);
        showReportButton$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[96]);
        showGenerateButton$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[97]);
        showDetailsButton$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[98]);
        groupMessageMenu$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[99]);
        recognitionLanguage$delegate = new StringPref("none", null, 2, null).provideDelegate(null, r1[100]);
        postprocessingWithAi$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[101(0x65, float:1.42E-43)]);
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L5;
        CameraType r6 = CameraType.CAMERA_X;
    L6:
        cameraType$delegate = new EnumPref(r6, null, 2, null).provideDelegate(null, r1[102(0x66, float:1.43E-43)]);
        extendedFramesPerSecond$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[103(0x67, float:1.44E-43)]);
        cameraStabilization$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[104(0x68, float:1.46E-43)]);
        cameraMirrorMode$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[105(0x69, float:1.47E-43)]);
        videoMessagesCamera$delegate = new EnumPref(VideoMessagesCamera.FRONT, null, 2, null).provideDelegate(null, r1[106(0x6a, float:1.49E-43)]);
        rememberLastUsedCamera$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[107(0x6b, float:1.5E-43)]);
        startWithWideAngleCamera$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[108(0x6c, float:1.51E-43)]);
        zoomSlider$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[109(0x6d, float:1.53E-43)]);
        staticZoom$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[110(0x6e, float:1.54E-43)]);
        alwaysSendInHD$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[111(0x6f, float:1.56E-43)]);
        hideCameraTile$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[112(0x70, float:1.57E-43)]);
        doubleTapSeekDuration$delegate = new IntegerPref(1, null, 2, null).provideDelegate(null, r1[113(0x71, float:1.58E-43)]);
        preferOriginalQuality$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[114(0x72, float:1.6E-43)]);
        swipeToPip$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[115(0x73, float:1.61E-43)]);
        unmuteWithVolumeButtons$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[116(0x74, float:1.63E-43)]);
        pauseOnMinimizeVideo$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[117(0x75, float:1.64E-43)]);
        pauseOnMinimizeVoice$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[118(0x76, float:1.65E-43)]);
        pauseOnMinimizeRound$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[119(0x77, float:1.67E-43)]);
        useGoogleCrashlytics$delegate = new BooleanPref(BuildVars.isBetaApp(), null, 2, null).provideDelegate(null, r1[120(0x78, float:1.68E-43)]);
        useGoogleAnalytics$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[121(0x79, float:1.7E-43)]);
        enableAdBlock$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[122(0x7a, float:1.71E-43)]);
        updateScheduleTimestamp$delegate = new LongPref(0, null, 2, null).provideDelegate(null, r1[123(0x7b, float:1.72E-43)]);
        sdkUpdateScheduleTimestamp$delegate = new LongPref(0, null, 2, null).provideDelegate(null, r1[124(0x7c, float:1.74E-43)]);
        targetLang$delegate = new StringPref(Common.ASSET_APP, null, 2, null).provideDelegate(null, r1[125(0x7d, float:1.75E-43)]);
        flashWarmth$delegate = new FloatPref(0.5f, null, 2, null).provideDelegate(null, r1[126(0x7e, float:1.77E-43)]);
        flashIntensity$delegate = new FloatPref(1.0f, null, 2, null).provideDelegate(null, r1[127(0x7f, float:1.78E-43)]);
        yandexSearchEngine = new SearchEngine("Yandex", "https://mini.ya.ru/", "https://ya.ru/search/?text=", "https://suggestqueries.google.com/complete/search?client=chrome&q=", "https://yandex.ru/legal/confidential");
        pluginsDevMode$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[128(0x80, float:1.8E-43)]);
        pluginsSafeMode$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[129(0x81, float:1.81E-43)]);
        pluginsCompactView$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[130(0x82, float:1.82E-43)]);
        pluginsPySdkAutoUpdate$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[131(0x83, float:1.84E-43)]);
        pluginsPySdkBetaVersions$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[132(0x84, float:1.85E-43)]);
        pluginsDisableArtOpts$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[133(0x85, float:1.86E-43)]);
        pluginsUnknownSources$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r1[134(0x86, float:1.88E-43)]);
        pinnedPlugins$delegate = new StringSetPref(SetsKt.emptySet(), null, 2, null).provideDelegate(null, r1[135(0x87, float:1.89E-43)]);
        useSystemIconShape$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r1[136(0x88, float:1.9E-43)]);
        return;
    L5:
        r6 = CameraType.CAMERA_1;
        goto L6
    }

    private static final void ensureApiBotListener() {
        if (apiBotListener != null) goto L15;
        RemoteUtils.initCached();     // Catch: Exception -> L10
        SharedPreferences r0 = RemoteUtils.sharedPreferences;     // Catch: Exception -> L10
        if (r0 != null) goto L8;
        return;
    L8:
        ExteraConfig$$ExternalSyntheticLambda5 r1 = new ExteraConfig$$ExternalSyntheticLambda5();     // Catch: Exception -> L10
        r0.registerOnSharedPreferenceChangeListener(r1);     // Catch: Exception -> L10
        apiBotListener = r1;     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static final void ensureSettingsVisibility() {
        int r0 = MainMenuItem.SETTINGS.getId();
        if (BottomNavigationBar.hidden() == true) goto L6;
        return;
    L6:
        if (mainMenuLayout.contains(Integer.valueOf(r0)) == false) goto L8;
        return;
    L8:
        mainMenuHiddenItems.remove(Integer.valueOf(r0));
        mainMenuLayout.add(Integer.valueOf(r0));
        saveMainMenuLayout();
    }

    public static final boolean getAddCommaAfterMention() {
        return ((Boolean) addCommaAfterMention$delegate.getValue(null, $$delegatedProperties[81])).booleanValue();
    }

    public static final boolean getAlwaysSendInHD() {
        return ((Boolean) alwaysSendInHD$delegate.getValue(null, $$delegatedProperties[111(0x6f, float:1.56E-43)])).booleanValue();
    }

    public static final boolean getAospTransitions() {
        if (getTransitionAnimation() != TransitionAnimation.AOSP) goto L6;
        return true;
    L6:
        return false;
    }

    public static final Pair<Long, String> getApiBotInfo() {
        Pair<Long, String> r0 = currentApiBot;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Object r1 = sync;
        monitor-enter(r1);
        Pair<Long, String> r02 = currentApiBot;     // Catch: Throwable -> L18
        if (r02 == null) goto L11;
        monitor-exit(r1);
        return r02;
    L11:
        ensureApiBotListener();     // Catch: Throwable -> L18
        String r3 = RemoteUtils.getStringConfigValue("extera_api_bot", "8083294286:exteraAuthBot");     // Catch: Throwable -> L18
        if (r3 != null) goto L27;
    L22:
        Pair<Long, String> r03 = new Pair(8083294286L, "exteraAuthBot");     // Catch: Throwable -> L18
        currentApiBot = r03;     // Catch: Throwable -> L18
        monitor-exit(r1);
        return r03;
    L27:
        List r04 = StringsKt.split$default(r3, new String[]{":"}, false, 2, 2, null);     // Catch: Throwable -> L18 Exception -> L20
        if (r04.size() != 2) goto L22;
        Pair<Long, String> r2 = new Pair(Long.valueOf(Long.parseLong((String) r04.get(0))), r04.get(1));     // Catch: Throwable -> L18 Exception -> L20
        currentApiBot = r2;     // Catch: Throwable -> L18 Exception -> L20
        monitor-exit(r1);
        return r2;
    L20:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        throw th;
    }

    public static final boolean getArchiveOnPull() {
        return ((Boolean) archiveOnPull$delegate.getValue(null, $$delegatedProperties[12])).booleanValue();
    }

    public static final float getAvatarCorners() {
        return ((Number) avatarCorners$delegate.getValue(null, $$delegatedProperties[18])).floatValue();
    }

    @JvmOverloads
    public static final int getAvatarCorners(float r3) {
        return getAvatarCorners$default(r3, false, false, 6, null);
    }

    @JvmOverloads
    public static final int getAvatarCorners(float r3, boolean r4) {
        return getAvatarCorners$default(r3, r4, false, 4, null);
    }

    public static final int getAvatarCorners(float r2, boolean r3, AvatarCornerType r4) {
        if (getAvatarCorners() != 0.0f) goto L5;
        return 0;
    L5:
        float r0 = (getAvatarCorners() * r2) / 56.0f;
        if (r3 == true) goto L9;
        r0 = AndroidUtilities.dp(r0);
    L9:
        if (getSingleCornerRadius() == true) goto L21;
        int r22 = WhenMappings.$EnumSwitchMapping$0[r4.ordinal()];
        if (r22 == 1) goto L21;
        if (r22 != 2) goto L15;
        r0 = (((int) r0) * 42) >> 6;
        goto L21
    L15:
        if (r22 != 3) goto L17;
        r0 = (r0 * 40.0f) / 72.0f;
        goto L21
    L17:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L21:
        return RangesKt.coerceAtLeast((int) Math.ceil(r0), 0);
    }

    @JvmOverloads
    public static final int getAvatarCorners(float r0, boolean r1, boolean r2) {
        if (r2 == false) goto L4;
        AvatarCornerType r22 = AvatarCornerType.FORUM;
    L6:
        return getAvatarCorners(r0, r1, r22);
    L4:
        r22 = AvatarCornerType.DEFAULT;
        goto L6
    }

    public static /* synthetic */ int getAvatarCorners$default(float r1, boolean r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = false;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return getAvatarCorners(r1, r2, r3);
    }

    public static final float getAvatarSquareness() {
        return RangesKt.coerceIn(1.0f - (getAvatarCorners() / 28.0f), 0.0f, 1.0f);
    }

    public static final PreferencesUtils.BackupItem[] getBackupKeys() {
        return (PreferencesUtils.BackupItem[]) PrefClassesKt.getRegisteredKeys().toArray(new PreferencesUtils.BackupItem[0]);
    }

    public static final int getBottomButton() {
        return ((Number) bottomButton$delegate.getValue(null, $$delegatedProperties[70])).intValue();
    }

    public static final boolean getCameraMirrorMode() {
        return ((Boolean) cameraMirrorMode$delegate.getValue(null, $$delegatedProperties[105(0x69, float:1.47E-43)])).booleanValue();
    }

    public static final boolean getCameraStabilization() {
        return ((Boolean) cameraStabilization$delegate.getValue(null, $$delegatedProperties[104(0x68, float:1.46E-43)])).booleanValue();
    }

    public static final CameraType getCameraType() {
        return (CameraType) cameraType$delegate.getValue(null, $$delegatedProperties[102(0x66, float:1.43E-43)]);
    }

    public static final boolean getCenterTitle() {
        return ((Boolean) centerTitle$delegate.getValue(null, $$delegatedProperties[23])).booleanValue();
    }

    public static final String getCurrentLangName() {
        return TranslatorUtils.getTargetLanguageTitle();
    }

    public static final String getCustomSavePath() {
        return (String) customSavePath$delegate.getValue(null, $$delegatedProperties[15]);
    }

    public static final boolean getCustomThemes() {
        return ((Boolean) customThemes$delegate.getValue(null, $$delegatedProperties[44])).booleanValue();
    }

    public static final ArrayList<Integer> getDefaultMainMenuLayout() {
        ArrayList<Integer> r0 = new ArrayList();
        r0.add(Integer.valueOf(MainMenuItem.ARCHIVE.getId()));
        ExtensionsKt.addIf(r0, BottomNavigationBar.hidden(), Integer.valueOf(MainMenuItem.PROFILE.getId()));
        r0.add(Integer.valueOf(MainMenuItem.NEW_GROUP.getId()));
        ExtensionsKt.addIf(r0, BottomNavigationBar.hidden(), Integer.valueOf(MainMenuItem.CONTACTS.getId()));
        r0.add(Integer.valueOf(MainMenuItem.SAVED.getId()));
        r0.add(Integer.valueOf(MainMenuItem.FEED.getId()));
        r0.add(Integer.valueOf(MainMenuItem.BOTS.getId()));
        ExtensionsKt.addIf(r0, BottomNavigationBar.hidden(), Integer.valueOf(MainMenuItem.SETTINGS.getId()));
        return r0;
    }

    public static final boolean getDisableGreetingSticker() {
        return ((Boolean) disableGreetingSticker$delegate.getValue(null, $$delegatedProperties[79])).booleanValue();
    }

    public static final boolean getDisableMarkdown() {
        return ((Boolean) disableMarkdown$delegate.getValue(null, $$delegatedProperties[83])).booleanValue();
    }

    public static final boolean getDisableNotificationDelay() {
        return ((Boolean) disableNotificationDelay$delegate.getValue(null, $$delegatedProperties[5])).booleanValue();
    }

    public static final boolean getDisableNumberRounding() {
        return ((Boolean) disableNumberRounding$delegate.getValue(null, $$delegatedProperties[1])).booleanValue();
    }

    public static final boolean getDisableUnarchiveSwipe() {
        return ((Boolean) disableUnarchiveSwipe$delegate.getValue(null, $$delegatedProperties[13])).booleanValue();
    }

    public static final DividerStyle getDividerStyle() {
        return (DividerStyle) dividerStyle$delegate.getValue(null, $$delegatedProperties[20]);
    }

    public static final ArrayList<String> getDoNotMarkAsNew() {
        return doNotMarkAsNew;
    }

    public static final int getDoNotUseProxy() {
        return ((Number) doNotUseProxy$delegate.getValue(null, $$delegatedProperties[14])).intValue();
    }

    public static final int getDoubleTapAction() {
        return ((Number) doubleTapAction$delegate.getValue(null, $$delegatedProperties[65])).intValue();
    }

    public static final int getDoubleTapActionOutOwner() {
        return ((Number) doubleTapActionOutOwner$delegate.getValue(null, $$delegatedProperties[66])).intValue();
    }

    public static final int getDoubleTapSeekDuration() {
        return ((Number) doubleTapSeekDuration$delegate.getValue(null, $$delegatedProperties[113(0x71, float:1.58E-43)])).intValue();
    }

    public static final int getDoubleTapSeekDurationMillis() {
        int r0 = getDoubleTapSeekDuration();
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L10;
        if (r0 == 2) goto L10;
        return 30000;
    L10:
        return (getDoubleTapSeekDuration() + 1) * 5000;
    }

    public static final int getDownloadSpeedBoost() {
        return ((Number) downloadSpeedBoost$delegate.getValue(null, $$delegatedProperties[7])).intValue();
    }

    public static final String getEditingIconPackId() {
        return (String) editingIconPackId$delegate.getValue(null, $$delegatedProperties[17]);
    }

    public static final SharedPreferences.Editor getEditor() {
        return editor;
    }

    public static final boolean getEnableAdBlock() {
        return ((Boolean) enableAdBlock$delegate.getValue(null, $$delegatedProperties[122(0x7a, float:1.71E-43)])).booleanValue();
    }

    public static final int getEventType() {
        return ((Number) eventType$delegate.getValue(null, $$delegatedProperties[50])).intValue();
    }

    public static final boolean getExtendedFramesPerSecond() {
        return ((Boolean) extendedFramesPerSecond$delegate.getValue(null, $$delegatedProperties[103(0x67, float:1.44E-43)])).booleanValue();
    }

    public static final boolean getFilterZalgo() {
        return ((Boolean) filterZalgo$delegate.getValue(null, $$delegatedProperties[6])).booleanValue();
    }

    public static final float getFlashIntensity() {
        return ((Number) flashIntensity$delegate.getValue(null, $$delegatedProperties[127(0x7f, float:1.78E-43)])).floatValue();
    }

    public static final float getFlashWarmth() {
        return ((Number) flashWarmth$delegate.getValue(null, $$delegatedProperties[126(0x7e, float:1.77E-43)])).floatValue();
    }

    public static final boolean getForceBlur() {
        return ((Boolean) forceBlur$delegate.getValue(null, $$delegatedProperties[49])).booleanValue();
    }

    public static final boolean getForceSnow() {
        return ((Boolean) forceSnow$delegate.getValue(null, $$delegatedProperties[21])).booleanValue();
    }

    public static final boolean getFormatTimeWithSeconds() {
        return ((Boolean) formatTimeWithSeconds$delegate.getValue(null, $$delegatedProperties[2])).booleanValue();
    }

    public static final Gson getGSON() {
        return GSON;
    }

    public static final boolean getGlassMessageMenu() {
        return ((Boolean) glassMessageMenu$delegate.getValue(null, $$delegatedProperties[48])).booleanValue();
    }

    public static final GlassOutlineStyle getGlassOutlineStyle() {
        return (GlassOutlineStyle) glassOutlineStyle$delegate.getValue(null, $$delegatedProperties[47]);
    }

    public static final boolean getGooeyAvatarAnimation() {
        return ((Boolean) gooeyAvatarAnimation$delegate.getValue(null, $$delegatedProperties[43])).booleanValue();
    }

    public static final boolean getGroupMessageMenu() {
        return ((Boolean) groupMessageMenu$delegate.getValue(null, $$delegatedProperties[99])).booleanValue();
    }

    public static final boolean getHideActionBarStatus() {
        return ((Boolean) hideActionBarStatus$delegate.getValue(null, $$delegatedProperties[22])).booleanValue();
    }

    public static final boolean getHideAllChats() {
        return ((Boolean) hideAllChats$delegate.getValue(null, $$delegatedProperties[31])).booleanValue();
    }

    public static final boolean getHideArchiveFolder() {
        return ((Boolean) hideArchiveFolder$delegate.getValue(null, $$delegatedProperties[11])).booleanValue();
    }

    public static final boolean getHideCameraTile() {
        return ((Boolean) hideCameraTile$delegate.getValue(null, $$delegatedProperties[112(0x70, float:1.57E-43)])).booleanValue();
    }

    public static final boolean getHideDialogsSearchBar() {
        return ((Boolean) hideDialogsSearchBar$delegate.getValue(null, $$delegatedProperties[26])).booleanValue();
    }

    public static final boolean getHideFloatingButton() {
        return ((Boolean) hideFloatingButton$delegate.getValue(null, $$delegatedProperties[25])).booleanValue();
    }

    public static final boolean getHideKeyboardOnScroll() {
        return ((Boolean) hideKeyboardOnScroll$delegate.getValue(null, $$delegatedProperties[80])).booleanValue();
    }

    public static final boolean getHidePhoneNumber() {
        return ((Boolean) hidePhoneNumber$delegate.getValue(null, $$delegatedProperties[9])).booleanValue();
    }

    public static final boolean getHideReactionsInChannels() {
        return ((Boolean) hideReactionsInChannels$delegate.getValue(null, $$delegatedProperties[63])).booleanValue();
    }

    public static final boolean getHideReactionsInGroups() {
        return ((Boolean) hideReactionsInGroups$delegate.getValue(null, $$delegatedProperties[64])).booleanValue();
    }

    public static final boolean getHideReactionsInPrivateChats() {
        return ((Boolean) hideReactionsInPrivateChats$delegate.getValue(null, $$delegatedProperties[62])).booleanValue();
    }

    public static final boolean getHideSendAsPeer() {
        return ((Boolean) hideSendAsPeer$delegate.getValue(null, $$delegatedProperties[84])).booleanValue();
    }

    public static final boolean getHideShareButton() {
        return ((Boolean) hideShareButton$delegate.getValue(null, $$delegatedProperties[89])).booleanValue();
    }

    public static final boolean getHideStories() {
        return ((Boolean) hideStories$delegate.getValue(null, $$delegatedProperties[24])).booleanValue();
    }

    public static final IconPackType getIconPack() {
        return (IconPackType) iconPack$delegate.getValue(null, $$delegatedProperties[16]);
    }

    public static final ArrayList<String> getIconPacksHidden() {
        return iconPacksHidden;
    }

    public static final ArrayList<String> getIconPacksLayout() {
        return iconPacksLayout;
    }

    public static final boolean getImmersiveDrawerAnimation() {
        return ((Boolean) immersiveDrawerAnimation$delegate.getValue(null, $$delegatedProperties[52])).booleanValue();
    }

    public static final boolean getInAppVibration() {
        return ((Boolean) inAppVibration$delegate.getValue(null, $$delegatedProperties[4])).booleanValue();
    }

    public static final boolean getInlineMathResult() {
        return ((Boolean) inlineMathResult$delegate.getValue(null, $$delegatedProperties[82])).booleanValue();
    }

    public static final boolean getLogging() {
        return systemConfigPrefs().getBoolean("logsEnabled", false);
    }

    public static final ArrayList<Integer> getMainMenuHiddenItems() {
        return mainMenuHiddenItems;
    }

    public static final ArrayList<Integer> getMainMenuLayout() {
        return mainMenuLayout;
    }

    public static final boolean getNavigationDrawer() {
        return ((Boolean) navigationDrawer$delegate.getValue(null, $$delegatedProperties[51])).booleanValue();
    }

    public static final boolean getNewChatHeaderStyle() {
        return ((Boolean) newChatHeaderStyle$delegate.getValue(null, $$delegatedProperties[38])).booleanValue();
    }

    public static final boolean getNewFabStyle() {
        return ((Boolean) newFabStyle$delegate.getValue(null, $$delegatedProperties[40])).booleanValue();
    }

    public static final HashMap<String, Long> getNewFeaturesShowedAt() {
        return newFeaturesShowedAt;
    }

    public static final boolean getNewLoadingStyle() {
        return ((Boolean) newLoadingStyle$delegate.getValue(null, $$delegatedProperties[35])).booleanValue();
    }

    public static final boolean getNewNavigationBarStyle() {
        return ((Boolean) newNavigationBarStyle$delegate.getValue(null, $$delegatedProperties[39])).booleanValue();
    }

    public static final boolean getNewSliderStyle() {
        return ((Boolean) newSliderStyle$delegate.getValue(null, $$delegatedProperties[36])).booleanValue();
    }

    public static final boolean getNewSwitchStyle() {
        return ((Boolean) newSwitchStyle$delegate.getValue(null, $$delegatedProperties[37])).booleanValue();
    }

    public static final int getOnlineDotInnerRadius() {
        return AndroidUtilities.dp(getAvatarSquareness() + 5.0f);
    }

    public static final float getOnlineDotOffset(float r4, float r5) {
        return r4 + ((((float) (r5 / Math.sqrt(2.0d))) - r4) * getAvatarSquareness());
    }

    public static final int getOnlineDotOuterRadius() {
        return AndroidUtilities.dp((getAvatarSquareness() * 2.0f) + 7.0f);
    }

    public static final boolean getPauseOnMinimizeRound() {
        return ((Boolean) pauseOnMinimizeRound$delegate.getValue(null, $$delegatedProperties[119(0x77, float:1.67E-43)])).booleanValue();
    }

    public static final boolean getPauseOnMinimizeVideo() {
        return ((Boolean) pauseOnMinimizeVideo$delegate.getValue(null, $$delegatedProperties[117(0x75, float:1.64E-43)])).booleanValue();
    }

    public static final boolean getPauseOnMinimizeVoice() {
        return ((Boolean) pauseOnMinimizeVoice$delegate.getValue(null, $$delegatedProperties[118(0x76, float:1.65E-43)])).booleanValue();
    }

    public static final Set<String> getPinnedPlugins() {
        return (Set) pinnedPlugins$delegate.getValue(null, $$delegatedProperties[135(0x87, float:1.89E-43)]);
    }

    public static final boolean getPluginsCompactView() {
        return ((Boolean) pluginsCompactView$delegate.getValue(null, $$delegatedProperties[130(0x82, float:1.82E-43)])).booleanValue();
    }

    public static final boolean getPluginsDevMode() {
        return ((Boolean) pluginsDevMode$delegate.getValue(null, $$delegatedProperties[128(0x80, float:1.8E-43)])).booleanValue();
    }

    public static final boolean getPluginsDisableArtOpts() {
        return ((Boolean) pluginsDisableArtOpts$delegate.getValue(null, $$delegatedProperties[133(0x85, float:1.86E-43)])).booleanValue();
    }

    public static final boolean getPluginsEngine() {
        return pluginsEngine;
    }

    public static final boolean getPluginsPySdkAutoUpdate() {
        return ((Boolean) pluginsPySdkAutoUpdate$delegate.getValue(null, $$delegatedProperties[131(0x83, float:1.84E-43)])).booleanValue();
    }

    public static final boolean getPluginsPySdkBetaVersions() {
        return ((Boolean) pluginsPySdkBetaVersions$delegate.getValue(null, $$delegatedProperties[132(0x84, float:1.85E-43)])).booleanValue();
    }

    public static final boolean getPluginsSafeMode() {
        return ((Boolean) pluginsSafeMode$delegate.getValue(null, $$delegatedProperties[129(0x81, float:1.81E-43)])).booleanValue();
    }

    public static final boolean getPluginsUnknownSources() {
        return ((Boolean) pluginsUnknownSources$delegate.getValue(null, $$delegatedProperties[134(0x86, float:1.88E-43)])).booleanValue();
    }

    public static final boolean getPostprocessingWithAi() {
        return ((Boolean) postprocessingWithAi$delegate.getValue(null, $$delegatedProperties[101(0x65, float:1.42E-43)])).booleanValue();
    }

    public static final float getPredictiveBackIntensity() {
        return ((Number) predictiveBackIntensity$delegate.getValue(null, $$delegatedProperties[45])).floatValue();
    }

    public static final boolean getPreferOriginalQuality() {
        return ((Boolean) preferOriginalQuality$delegate.getValue(null, $$delegatedProperties[114(0x72, float:1.6E-43)])).booleanValue();
    }

    public static final SharedPreferences getPreferences() {
        return preferences;
    }

    public static final boolean getQuickAdminShortcuts() {
        return ((Boolean) quickAdminShortcuts$delegate.getValue(null, $$delegatedProperties[76])).booleanValue();
    }

    public static final boolean getQuickTransitionForChannels() {
        return ((Boolean) quickTransitionForChannels$delegate.getValue(null, $$delegatedProperties[77])).booleanValue();
    }

    public static final boolean getQuickTransitionForTopics() {
        return ((Boolean) quickTransitionForTopics$delegate.getValue(null, $$delegatedProperties[78])).booleanValue();
    }

    public static final String getRecognitionLanguage() {
        return (String) recognitionLanguage$delegate.getValue(null, $$delegatedProperties[100]);
    }

    public static final boolean getRelativeLastSeen() {
        return ((Boolean) relativeLastSeen$delegate.getValue(null, $$delegatedProperties[3])).booleanValue();
    }

    public static final boolean getRememberLastUsedCamera() {
        return ((Boolean) rememberLastUsedCamera$delegate.getValue(null, $$delegatedProperties[107(0x6b, float:1.5E-43)])).booleanValue();
    }

    public static final boolean getRemoveMessageTail() {
        return ((Boolean) removeMessageTail$delegate.getValue(null, $$delegatedProperties[85])).booleanValue();
    }

    public static final boolean getReplaceEditedWithIcon() {
        return ((Boolean) replaceEditedWithIcon$delegate.getValue(null, $$delegatedProperties[86])).booleanValue();
    }

    public static final boolean getReplyBackground() {
        return ((Boolean) replyBackground$delegate.getValue(null, $$delegatedProperties[59])).booleanValue();
    }

    public static final boolean getReplyColors() {
        return ((Boolean) replyColors$delegate.getValue(null, $$delegatedProperties[57])).booleanValue();
    }

    public static final boolean getReplyEmoji() {
        return ((Boolean) replyEmoji$delegate.getValue(null, $$delegatedProperties[58])).booleanValue();
    }

    public static final long getSdkUpdateScheduleTimestamp() {
        return ((Number) sdkUpdateScheduleTimestamp$delegate.getValue(null, $$delegatedProperties[124(0x7c, float:1.74E-43)])).longValue();
    }

    public static final float getSectionRadius() {
        return ((Number) sectionRadius$delegate.getValue(null, $$delegatedProperties[33])).floatValue();
    }

    public static final int getSectionRadiusDp() {
        return MathKt.roundToInt(sanitizeSectionRadius(getSectionRadius()));
    }

    public static final boolean getSectionsSeparatedHeaders() {
        if (getDividerStyle() != DividerStyle.SEGMENTS) goto L5;
        return true;
    L5:
        if (getSectionsSeparatedHeadersPreference() == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static final boolean getSectionsSeparatedHeadersPreference() {
        return ((Boolean) sectionsSeparatedHeadersPreference$delegate.getValue(null, $$delegatedProperties[34])).booleanValue();
    }

    public static final boolean getSenderMiniAvatars() {
        return ((Boolean) senderMiniAvatars$delegate.getValue(null, $$delegatedProperties[27])).booleanValue();
    }

    public static final boolean getShowClearButton() {
        return ((Boolean) showClearButton$delegate.getValue(null, $$delegatedProperties[94])).booleanValue();
    }

    public static final boolean getShowCopyPhotoButton() {
        return ((Boolean) showCopyPhotoButton$delegate.getValue(null, $$delegatedProperties[91])).booleanValue();
    }

    public static final boolean getShowDetailsButton() {
        return ((Boolean) showDetailsButton$delegate.getValue(null, $$delegatedProperties[98])).booleanValue();
    }

    public static final boolean getShowFeedTab() {
        return ((Boolean) showFeedTab$delegate.getValue(null, $$delegatedProperties[53])).booleanValue();
    }

    public static final boolean getShowFeedUnreadCounter() {
        return ((Boolean) showFeedUnreadCounter$delegate.getValue(null, $$delegatedProperties[54])).booleanValue();
    }

    public static final boolean getShowForwardsCount() {
        return ((Boolean) showForwardsCount$delegate.getValue(null, $$delegatedProperties[88])).booleanValue();
    }

    public static final boolean getShowGenerateButton() {
        return ((Boolean) showGenerateButton$delegate.getValue(null, $$delegatedProperties[97])).booleanValue();
    }

    public static final boolean getShowHistoryButton() {
        return ((Boolean) showHistoryButton$delegate.getValue(null, $$delegatedProperties[95])).booleanValue();
    }

    public static final int getShowIdAndDc() {
        return ((Number) showIdAndDc$delegate.getValue(null, $$delegatedProperties[10])).intValue();
    }

    public static final boolean getShowOnlineStatus() {
        return ((Boolean) showOnlineStatus$delegate.getValue(null, $$delegatedProperties[87])).booleanValue();
    }

    public static final boolean getShowRepeatMessageButton() {
        return ((Boolean) showRepeatMessageButton$delegate.getValue(null, $$delegatedProperties[93])).booleanValue();
    }

    public static final boolean getShowReportButton() {
        return ((Boolean) showReportButton$delegate.getValue(null, $$delegatedProperties[96])).booleanValue();
    }

    public static final boolean getShowResultsBeforeVoting() {
        return ((Boolean) showResultsBeforeVoting$delegate.getValue(null, $$delegatedProperties[90])).booleanValue();
    }

    public static final boolean getShowSaveMessageButton() {
        return ((Boolean) showSaveMessageButton$delegate.getValue(null, $$delegatedProperties[92])).booleanValue();
    }

    public static final boolean getSingleCornerRadius() {
        return ((Boolean) singleCornerRadius$delegate.getValue(null, $$delegatedProperties[19])).booleanValue();
    }

    public static final boolean getSpringAnimations() {
        if (getTransitionAnimation() != TransitionAnimation.SPRING) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean getSpringSwipeback() {
        if (getTransitionAnimation() == TransitionAnimation.DEFAULT) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean getSquareFab() {
        return ((Boolean) squareFab$delegate.getValue(null, $$delegatedProperties[32])).booleanValue();
    }

    public static final boolean getStartWithWideAngleCamera() {
        return ((Boolean) startWithWideAngleCamera$delegate.getValue(null, $$delegatedProperties[108(0x6c, float:1.51E-43)])).booleanValue();
    }

    public static final boolean getStaticZoom() {
        return ((Boolean) staticZoom$delegate.getValue(null, $$delegatedProperties[110(0x6e, float:1.54E-43)])).booleanValue();
    }

    public static final int getStickerShape() {
        return ((Number) stickerShape$delegate.getValue(null, $$delegatedProperties[60])).intValue();
    }

    public static final float getStickerSize() {
        return ((Number) stickerSize$delegate.getValue(null, $$delegatedProperties[55])).floatValue();
    }

    public static final StickerTimeMode getStickerTimeMode() {
        return (StickerTimeMode) stickerTimeMode$delegate.getValue(null, $$delegatedProperties[56]);
    }

    public static final String getSwipeActions() {
        return (String) swipeActions$delegate.getValue(null, $$delegatedProperties[67]);
    }

    public static final boolean getSwipeActionsLoop() {
        return ((Boolean) swipeActionsLoop$delegate.getValue(null, $$delegatedProperties[68])).booleanValue();
    }

    public static final boolean getSwipeActionsReversed() {
        return ((Boolean) swipeActionsReversed$delegate.getValue(null, $$delegatedProperties[69])).booleanValue();
    }

    public static final boolean getSwipeToPip() {
        return ((Boolean) swipeToPip$delegate.getValue(null, $$delegatedProperties[115(0x73, float:1.61E-43)])).booleanValue();
    }

    public static final TabCounterMode getTabCounterMode() {
        return (TabCounterMode) tabCounterMode$delegate.getValue(null, $$delegatedProperties[30]);
    }

    public static final TabIconsMode getTabIcons() {
        return (TabIconsMode) tabIcons$delegate.getValue(null, $$delegatedProperties[29]);
    }

    public static final int getTabletMode() {
        return ((Number) tabletMode$delegate.getValue(null, $$delegatedProperties[41])).intValue();
    }

    public static final String getTargetLang() {
        return (String) targetLang$delegate.getValue(null, $$delegatedProperties[125(0x7d, float:1.75E-43)]);
    }

    public static final boolean getTelegramAiEditor() {
        return ((Boolean) telegramAiEditor$delegate.getValue(null, $$delegatedProperties[73])).booleanValue();
    }

    public static final boolean getTelegramAiInstantViewSummaries() {
        return ((Boolean) telegramAiInstantViewSummaries$delegate.getValue(null, $$delegatedProperties[75])).booleanValue();
    }

    public static final boolean getTelegramAiSummaries() {
        return ((Boolean) telegramAiSummaries$delegate.getValue(null, $$delegatedProperties[74])).booleanValue();
    }

    public static final int getTitleText() {
        return ((Number) titleText$delegate.getValue(null, $$delegatedProperties[28])).intValue();
    }

    public static final TransitionAnimation getTransitionAnimation() {
        return (TransitionAnimation) transitionAnimation$delegate.getValue(null, $$delegatedProperties[46]);
    }

    public static final int getTranslationProvider() {
        return ((Number) translationProvider$delegate.getValue(null, $$delegatedProperties[0])).intValue();
    }

    public static final boolean getUnlimitedRecentStickers() {
        return ((Boolean) unlimitedRecentStickers$delegate.getValue(null, $$delegatedProperties[61])).booleanValue();
    }

    public static final boolean getUnmuteWithVolumeButtons() {
        return ((Boolean) unmuteWithVolumeButtons$delegate.getValue(null, $$delegatedProperties[116(0x74, float:1.63E-43)])).booleanValue();
    }

    public static final long getUpdateScheduleTimestamp() {
        return ((Number) updateScheduleTimestamp$delegate.getValue(null, $$delegatedProperties[123(0x7b, float:1.72E-43)])).longValue();
    }

    public static final boolean getUploadSpeedBoost() {
        return ((Boolean) uploadSpeedBoost$delegate.getValue(null, $$delegatedProperties[8])).booleanValue();
    }

    public static final boolean getUseGoogleAnalytics() {
        return ((Boolean) useGoogleAnalytics$delegate.getValue(null, $$delegatedProperties[121(0x79, float:1.7E-43)])).booleanValue();
    }

    public static final boolean getUseGoogleCrashlytics() {
        return ((Boolean) useGoogleCrashlytics$delegate.getValue(null, $$delegatedProperties[120(0x78, float:1.68E-43)])).booleanValue();
    }

    public static final boolean getUseSystemFonts() {
        return ((Boolean) useSystemFonts$delegate.getValue(null, $$delegatedProperties[42])).booleanValue();
    }

    public static final boolean getUseSystemIconShape() {
        return ((Boolean) useSystemIconShape$delegate.getValue(null, $$delegatedProperties[136(0x88, float:1.9E-43)])).booleanValue();
    }

    public static final VideoMessagesCamera getVideoMessagesCamera() {
        return (VideoMessagesCamera) videoMessagesCamera$delegate.getValue(null, $$delegatedProperties[106(0x6a, float:1.49E-43)]);
    }

    public static final boolean getWidePostsInChannels() {
        return ((Boolean) widePostsInChannels$delegate.getValue(null, $$delegatedProperties[72])).booleanValue();
    }

    public static final boolean getWidePostsInFeed() {
        return ((Boolean) widePostsInFeed$delegate.getValue(null, $$delegatedProperties[71])).booleanValue();
    }

    public static final SearchEngine getYandexSearchEngine() {
        return yandexSearchEngine;
    }

    public static final boolean getZoomSlider() {
        return ((Boolean) zoomSlider$delegate.getValue(null, $$delegatedProperties[109(0x6d, float:1.53E-43)])).booleanValue();
    }

    public static final void init() {
        loadConfig();
        if (servicesStarted.compareAndSet(false, true) == true) goto L5;
        return;
    L5:
        ApiController.init();
        RemoteUtils.init();
        PluginsController.Companion.getInstance().init(getPluginsSafeMode(), new ExteraConfig$$ExternalSyntheticLambda4());
        AdBlockManager.preload();
        IconManager.INSTANCE.prefetchCustomPacks();
    }

    public static final boolean isProxyDisabledOn(ProxyDisableCondition r1) {
        int r0 = getDoNotUseProxy();
        if ((r1.getFlag() & r0) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static final void loadConfig() {
        Object r0 = sync;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (configLoaded == false) goto L8;
        monitor-exit(r0);
        return;
    L8:
        SharedPreferences r1 = preferences;     // Catch: Throwable -> L13
        BottomNavigationBar.setMode(r1.getInt("bottomNavigationBarMode", 0));     // Catch: Throwable -> L13
        String r2 = r1.getString("iconPacksLayout", null);     // Catch: Throwable -> L13
        String r12 = r1.getString("iconPacksHidden", null);     // Catch: Throwable -> L13
        if (r2 == null) goto L28;
        Gson r7 = GSON;     // Catch: Throwable -> L13
        iconPacksLayout = (ArrayList) r7.fromJson(r2, new ExteraConfig$loadConfig$1$1().getType());     // Catch: Throwable -> L13
        if (r12 == null) goto L15;
        ArrayList<String> r13 = (ArrayList) r7.fromJson(r12, new ExteraConfig$loadConfig$1$2().getType());     // Catch: Throwable -> L13
    L16:
        iconPacksHidden = r13;     // Catch: Throwable -> L13
        Iterator<String> r14 = iconPacksLayout.iterator();     // Catch: Throwable -> L13
        boolean r72 = false;
        String r22 = null;
    L18:
        if (r14.hasNext() == false) goto L24;
        String r8 = r14.next();     // Catch: Throwable -> L13
        if (StringsKt.startsWith$default(r8, "base.", false, 2, null) == false) goto L18;
        if (r22 == null) goto L22;
        r14.remove();     // Catch: Throwable -> L13
        r72 = true;
        goto L18
    L22:
        r22 = r8;
        goto L18
    L24:
        if (r22 != null) goto L26;
        iconPacksLayout.add("base.default");     // Catch: Throwable -> L13
        r72 = true;
    L26:
        if (r72 == false) goto L37;
        saveIconPacksLayout();     // Catch: Throwable -> L13
    L37:
        String[] r15 = {"base.default", "base.solar", "base.remix"};     // Catch: Throwable -> L13
        int r23 = 0;
        boolean r73 = false;
    L38:
        if (r23 >= 3) goto L45;
        String r82 = r15[r23];     // Catch: Throwable -> L13
        if (iconPacksLayout.contains(r82) == true) goto L44;
        if (iconPacksHidden.contains(r82) == true) goto L44;
        iconPacksHidden.add(r82);     // Catch: Throwable -> L13
        r73 = true;
    L44:
        r23 = r23 + 1;     // Catch: Throwable -> L13
        goto L38
    L45:
        if (r73 == false) goto L47;
        saveIconPacksLayout();     // Catch: Throwable -> L13
    L47:
        SharedPreferences r16 = preferences;     // Catch: Throwable -> L13
        String r24 = r16.getString("doNotMarkAsNew", null);     // Catch: Throwable -> L13
        if (r24 == null) goto L52;
        ArrayList<String> r25 = (ArrayList) GSON.fromJson(r24, new ExteraConfig$loadConfig$1$4$1().getType());     // Catch: Throwable -> L13
        if (r25 == null) goto L52;
    L53:
        doNotMarkAsNew = r25;     // Catch: Throwable -> L13
        String r26 = r16.getString("newFeaturesShowedAt", null);     // Catch: Throwable -> L13
        if (r26 == null) goto L58;
        HashMap<String, Long> r27 = (HashMap) GSON.fromJson(r26, new ExteraConfig$loadConfig$1$5$1().getType());     // Catch: Throwable -> L13
        if (r27 == null) goto L58;
    L59:
        newFeaturesShowedAt = r27;     // Catch: Throwable -> L13
        String r28 = r16.getString("mainMenuLayout", null);     // Catch: Throwable -> L13
        String r17 = r16.getString("mainMenuHiddenItems", null);     // Catch: Throwable -> L13
        if (r28 == null) goto L66;
        Gson r4 = GSON;     // Catch: Throwable -> L13
        mainMenuLayout = (ArrayList) r4.fromJson(r28, new ExteraConfig$loadConfig$1$6().getType());     // Catch: Throwable -> L13
        if (r17 == null) goto L64;
        ArrayList<Integer> r18 = (ArrayList) r4.fromJson(r17, new ExteraConfig$loadConfig$1$7().getType());     // Catch: Throwable -> L13
    L65:
        mainMenuHiddenItems = r18;     // Catch: Throwable -> L13
    L80:
        if (PluginsController.Companion.isPluginEngineSupported() == true) goto L85;
        pluginsEngine = false;     // Catch: Throwable -> L13
        if (preferences.getBoolean("pluginsEngine", false) == false) goto L84;
        editor.putBoolean("pluginsEngine", false).apply();     // Catch: Throwable -> L13
    L84:
        int r19 = MainMenuItem.PLUGINS.getId();     // Catch: Throwable -> L13
        mainMenuLayout.remove(Integer.valueOf(r19));     // Catch: Throwable -> L13
        mainMenuHiddenItems.remove(Integer.valueOf(r19));     // Catch: Throwable -> L13
    L90:
        int r110 = MainMenuItem.FEED.getId();     // Catch: Throwable -> L13
        if (mainMenuLayout.contains(Integer.valueOf(r110)) == false) goto L93;
    L95:
        mainMenuLayout.removeAll(CollectionsKt.toSet(mainMenuHiddenItems));     // Catch: Throwable -> L13
        ensureSettingsVisibility();     // Catch: Throwable -> L13
        sanitizeMenu();     // Catch: Throwable -> L13
        migrateProxyConditions();     // Catch: Throwable -> L13
        migrateTelegramAiInstantViewSummaries();     // Catch: Throwable -> L13
        migrateStickerTimeMode();     // Catch: Throwable -> L13
        migrateTransitionAnimation();     // Catch: Throwable -> L13
        migrateTabCounterMode();     // Catch: Throwable -> L13
        TranslatorUtils.ensureTargetLanguageCompatibleWithProvider();     // Catch: Throwable -> L13
        configLoaded = true;     // Catch: Throwable -> L13
        Unit r111 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r0);
        return;
    L93:
        if (mainMenuHiddenItems.contains(Integer.valueOf(r110)) == true) goto L95;
        mainMenuLayout.add(Integer.valueOf(r110));     // Catch: Throwable -> L13
        saveMainMenuLayout();     // Catch: Throwable -> L13
        goto L95
    L85:
        pluginsEngine = preferences.getBoolean("pluginsEngine", false);     // Catch: Throwable -> L13
        int r112 = MainMenuItem.PLUGINS.getId();     // Catch: Throwable -> L13
        if (mainMenuLayout.contains(Integer.valueOf(r112)) == true) goto L90;
        if (mainMenuHiddenItems.contains(Integer.valueOf(r112)) == true) goto L90;
        mainMenuHiddenItems.add(Integer.valueOf(r112));     // Catch: Throwable -> L13
        goto L90
    L64:
        r18 = new ArrayList();     // Catch: Throwable -> L13
        goto L65
    L66:
        mainMenuLayout = new ArrayList();     // Catch: Throwable -> L13
        mainMenuHiddenItems = new ArrayList();     // Catch: Throwable -> L13
        mainMenuLayout.addAll(getDefaultMainMenuLayout());     // Catch: Throwable -> L13
        Iterator<MainMenuItem> r113 = MainMenuItem.getEntries().iterator();     // Catch: Throwable -> L13
    L68:
        if (r113.hasNext() == false) goto L78;
        MainMenuItem r29 = r113.next();     // Catch: Throwable -> L13
        if (r29 == MainMenuItem.DIVIDER) goto L68;
        if (mainMenuLayout.contains(Integer.valueOf(r29.getId())) == true) goto L68;
        if (r29 != MainMenuItem.PLUGINS) goto L77;
        if (PluginsController.Companion.isPluginEngineSupported() == false) goto L68;
    L77:
        mainMenuHiddenItems.add(Integer.valueOf(r29.getId()));     // Catch: Throwable -> L13
        goto L68
    L78:
        saveMainMenuLayout();     // Catch: Throwable -> L13
    L58:
        r27 = new HashMap();     // Catch: Throwable -> L13
    L52:
        r25 = new ArrayList();     // Catch: Throwable -> L13
        goto L53
    L15:
        r13 = new ArrayList();     // Catch: Throwable -> L13
        goto L16
    L28:
        iconPacksLayout = new ArrayList();     // Catch: Throwable -> L13
        iconPacksHidden = new ArrayList();     // Catch: Throwable -> L13
        String[] r114 = {"base.default", "base.solar", "base.remix"};     // Catch: Throwable -> L13
        int r210 = 0;
        int r74 = 0;
    L29:
        if (r210 >= 3) goto L36;
        String r83 = r114[r210];     // Catch: Throwable -> L13
        int r9 = r74 + 1;     // Catch: Throwable -> L13
        if (r74 != getIconPack().ordinal()) goto L34;
        ArrayList<String> r75 = iconPacksLayout;     // Catch: Throwable -> L13
    L33:
        r75.add(r83);     // Catch: Throwable -> L13
        r210 = r210 + 1;     // Catch: Throwable -> L13
        r74 = r9;
        goto L29
    L34:
        r75 = iconPacksHidden;     // Catch: Throwable -> L13
        goto L33
    L36:
        saveIconPacksLayout();     // Catch: Throwable -> L13
        goto L37
    }

    private static final void migrateProxyConditions() {
        SharedPreferences r0 = preferences;
        if (r0.contains("doNotUseProxyWithVpn") == true) goto L6;
        return;
    L6:
        if (r0.getBoolean("doNotUseProxyWithVpn", false) == false) goto L8;
        setProxyDisabledOn(ProxyDisableCondition.VPN, true);
    L8:
        editor.remove("doNotUseProxyWithVpn").apply();
    }

    private static final void migrateStickerTimeMode() {
        SharedPreferences r0 = preferences;
        if (r0.contains("hideStickerTime") == true) goto L6;
        return;
    L6:
        if (r0.getBoolean("hideStickerTime", false) == false) goto L8;
        setStickerTimeMode(StickerTimeMode.HIDDEN);
    L8:
        editor.remove("hideStickerTime").apply();
    }

    private static final void migrateTabCounterMode() {
        SharedPreferences r0 = preferences;
        if (r0.contains("tabCounter") == true) goto L6;
        return;
    L6:
        if (r0.getBoolean("tabCounter", true) == true) goto L8;
        setTabCounterMode(TabCounterMode.HIDDEN);
    L8:
        editor.remove("tabCounter").apply();
    }

    private static final void migrateTelegramAiInstantViewSummaries() {
        if (preferences.contains("telegramAiInstantViewSummaries") == false) goto L5;
        return;
    L5:
        setTelegramAiInstantViewSummaries(getTelegramAiSummaries());
    }

    private static final void migrateTransitionAnimation() {
        SharedPreferences r0 = preferences;
        if (r0.contains("springAnimations") == true) goto L8;
        if (r0.contains("aospTransitions") == true) goto L8;
        return;
    L8:
        if (r0.getBoolean("aospTransitions", false) == false) goto L11;
        TransitionAnimation r02 = TransitionAnimation.AOSP;
    L14:
        setTransitionAnimation(r02);
        editor.remove("springAnimations").remove("aospTransitions").apply();
        return;
    L11:
        if (r0.getBoolean("springAnimations", true) == false) goto L13;
        r02 = TransitionAnimation.SPRING;
        goto L14
    L13:
        r02 = TransitionAnimation.DEFAULT;
        goto L14
    }

    public static final void reloadConfig() {
        Object r0 = sync;
        monitor-enter(r0);
        configLoaded = false;     // Catch: Throwable -> L9
        Iterator<T> r1 = PrefClassesKt.getAllDelegates().iterator();     // Catch: Throwable -> L9
    L7:
        if (r1.hasNext() == false) goto L11;
        ((BasePref) r1.next()).invalidate();     // Catch: Throwable -> L9
        goto L7
    L11:
        loadConfig();     // Catch: Throwable -> L9
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public static final void sanitizeMenu() {
        boolean r0 = mainMenuLayout.removeIf(new ExteraConfig$$ExternalSyntheticLambda1(new ExteraConfig$$ExternalSyntheticLambda0())) | mainMenuHiddenItems.removeIf(new ExteraConfig$$ExternalSyntheticLambda3(new ExteraConfig$$ExternalSyntheticLambda2()));
        Iterator<MainMenuItem> r1 = MainMenuItem.getEntries().iterator();
    L4:
        if (r1.hasNext() == false) goto L16;
        MainMenuItem r2 = r1.next();
        if (r2 == MainMenuItem.DIVIDER) goto L4;
        if (r2 != MainMenuItem.PLUGINS) goto L12;
        if (PluginsController.Companion.isPluginEngineSupported() == false) goto L4;
    L12:
        if (mainMenuLayout.contains(Integer.valueOf(r2.getId())) == true) goto L4;
        if (mainMenuHiddenItems.contains(Integer.valueOf(r2.getId())) == true) goto L4;
        mainMenuHiddenItems.add(Integer.valueOf(r2.getId()));
        r0 = true;
        goto L4
    L16:
        if (r0 == false) goto L31;
        saveMainMenuLayout();
        return;
    }

    private static final float sanitizeSectionRadius(float r2) {
        if (Math.abs(r2) <= Float.MAX_VALUE) goto L5;
        return 20.0f;
    L5:
        return RangesKt.coerceIn(r2, 0.0f, 28.0f);
    }

    public static final void saveIconPacksLayout() {
        ArrayList<String> r0 = iconPacksLayout;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        String r4 = null;
        if (r3 >= r1) goto L8;
        String r5 = r0.get(r3);
        r3 = r3 + 1;
        if (StringsKt.startsWith$default(r5, "base.", false, 2, null) == false) goto L3;
        r4 = r5;
    L8:
        String r42 = r4;
        if (Intrinsics.areEqual(r42, "base.solar") == false) goto L12;
        IconPackType r02 = IconPackType.SOLAR;
    L15:
        setIconPack(r02);
        SharedPreferences.Editor r03 = editor;
        Gson r12 = GSON;
        r03.putString("iconPacksLayout", r12.toJson(iconPacksLayout)).putString("iconPacksHidden", r12.toJson(iconPacksHidden)).apply();
        return;
    L12:
        if (Intrinsics.areEqual(r42, "base.remix") == false) goto L14;
        r02 = IconPackType.REMIX;
        goto L15
    L14:
        r02 = IconPackType.DEFAULT;
        goto L15
    }

    public static final void saveMainMenuLayout() {
        SharedPreferences.Editor r0 = editor;
        Gson r1 = GSON;
        r0.putString("mainMenuLayout", r1.toJson(mainMenuLayout)).putString("mainMenuHiddenItems", r1.toJson(mainMenuHiddenItems)).apply();
    }

    public static final void setAddCommaAfterMention(boolean r3) {
        addCommaAfterMention$delegate.setValue(null, $$delegatedProperties[81], Boolean.valueOf(r3));
    }

    public static final void setAlwaysSendInHD(boolean r3) {
        alwaysSendInHD$delegate.setValue(null, $$delegatedProperties[111(0x6f, float:1.56E-43)], Boolean.valueOf(r3));
    }

    public static final void setArchiveOnPull(boolean r3) {
        archiveOnPull$delegate.setValue(null, $$delegatedProperties[12], Boolean.valueOf(r3));
    }

    public static final void setAvatarCorners(float r3) {
        avatarCorners$delegate.setValue(null, $$delegatedProperties[18], Float.valueOf(r3));
    }

    public static final void setBottomButton(int r3) {
        bottomButton$delegate.setValue(null, $$delegatedProperties[70], Integer.valueOf(r3));
    }

    public static final void setCameraMirrorMode(boolean r3) {
        cameraMirrorMode$delegate.setValue(null, $$delegatedProperties[105(0x69, float:1.47E-43)], Boolean.valueOf(r3));
    }

    public static final void setCameraStabilization(boolean r3) {
        cameraStabilization$delegate.setValue(null, $$delegatedProperties[104(0x68, float:1.46E-43)], Boolean.valueOf(r3));
    }

    public static final void setCameraType(CameraType r3) {
        cameraType$delegate.setValue(null, $$delegatedProperties[102(0x66, float:1.43E-43)], r3);
    }

    public static final void setCenterTitle(boolean r3) {
        centerTitle$delegate.setValue(null, $$delegatedProperties[23], Boolean.valueOf(r3));
    }

    public static final void setCustomSavePath(String r3) {
        customSavePath$delegate.setValue(null, $$delegatedProperties[15], r3);
    }

    public static final void setCustomThemes(boolean r3) {
        customThemes$delegate.setValue(null, $$delegatedProperties[44], Boolean.valueOf(r3));
    }

    public static final void setDisableGreetingSticker(boolean r3) {
        disableGreetingSticker$delegate.setValue(null, $$delegatedProperties[79], Boolean.valueOf(r3));
    }

    public static final void setDisableMarkdown(boolean r3) {
        disableMarkdown$delegate.setValue(null, $$delegatedProperties[83], Boolean.valueOf(r3));
    }

    public static final void setDisableNotificationDelay(boolean r3) {
        disableNotificationDelay$delegate.setValue(null, $$delegatedProperties[5], Boolean.valueOf(r3));
    }

    public static final void setDisableNumberRounding(boolean r3) {
        disableNumberRounding$delegate.setValue(null, $$delegatedProperties[1], Boolean.valueOf(r3));
    }

    public static final void setDisableUnarchiveSwipe(boolean r3) {
        disableUnarchiveSwipe$delegate.setValue(null, $$delegatedProperties[13], Boolean.valueOf(r3));
    }

    public static final void setDividerStyle(DividerStyle r3) {
        dividerStyle$delegate.setValue(null, $$delegatedProperties[20], r3);
    }

    public static final void setDoNotUseProxy(int r3) {
        doNotUseProxy$delegate.setValue(null, $$delegatedProperties[14], Integer.valueOf(r3));
    }

    public static final void setDoubleTapAction(int r3) {
        doubleTapAction$delegate.setValue(null, $$delegatedProperties[65], Integer.valueOf(r3));
    }

    public static final void setDoubleTapActionOutOwner(int r3) {
        doubleTapActionOutOwner$delegate.setValue(null, $$delegatedProperties[66], Integer.valueOf(r3));
    }

    public static final void setDoubleTapSeekDuration(int r3) {
        doubleTapSeekDuration$delegate.setValue(null, $$delegatedProperties[113(0x71, float:1.58E-43)], Integer.valueOf(r3));
    }

    public static final void setDownloadSpeedBoost(int r3) {
        downloadSpeedBoost$delegate.setValue(null, $$delegatedProperties[7], Integer.valueOf(r3));
    }

    public static final void setEditingIconPackId(String r3) {
        editingIconPackId$delegate.setValue(null, $$delegatedProperties[17], r3);
    }

    public static final void setEnableAdBlock(boolean r3) {
        enableAdBlock$delegate.setValue(null, $$delegatedProperties[122(0x7a, float:1.71E-43)], Boolean.valueOf(r3));
    }

    public static final void setExtendedFramesPerSecond(boolean r3) {
        extendedFramesPerSecond$delegate.setValue(null, $$delegatedProperties[103(0x67, float:1.44E-43)], Boolean.valueOf(r3));
    }

    public static final void setFilterZalgo(boolean r3) {
        filterZalgo$delegate.setValue(null, $$delegatedProperties[6], Boolean.valueOf(r3));
    }

    public static final void setFlashIntensity(float r3) {
        flashIntensity$delegate.setValue(null, $$delegatedProperties[127(0x7f, float:1.78E-43)], Float.valueOf(r3));
    }

    public static final void setFlashWarmth(float r3) {
        flashWarmth$delegate.setValue(null, $$delegatedProperties[126(0x7e, float:1.77E-43)], Float.valueOf(r3));
    }

    public static final void setForceBlur(boolean r3) {
        forceBlur$delegate.setValue(null, $$delegatedProperties[49], Boolean.valueOf(r3));
    }

    public static final void setForceSnow(boolean r3) {
        forceSnow$delegate.setValue(null, $$delegatedProperties[21], Boolean.valueOf(r3));
    }

    public static final void setFormatTimeWithSeconds(boolean r3) {
        formatTimeWithSeconds$delegate.setValue(null, $$delegatedProperties[2], Boolean.valueOf(r3));
    }

    public static final void setGlassMessageMenu(boolean r3) {
        glassMessageMenu$delegate.setValue(null, $$delegatedProperties[48], Boolean.valueOf(r3));
    }

    public static final void setGlassOutlineStyle(GlassOutlineStyle r3) {
        glassOutlineStyle$delegate.setValue(null, $$delegatedProperties[47], r3);
    }

    public static final void setGooeyAvatarAnimation(boolean r3) {
        gooeyAvatarAnimation$delegate.setValue(null, $$delegatedProperties[43], Boolean.valueOf(r3));
    }

    public static final void setGroupMessageMenu(boolean r3) {
        groupMessageMenu$delegate.setValue(null, $$delegatedProperties[99], Boolean.valueOf(r3));
    }

    public static final void setHideActionBarStatus(boolean r3) {
        hideActionBarStatus$delegate.setValue(null, $$delegatedProperties[22], Boolean.valueOf(r3));
    }

    public static final void setHideAllChats(boolean r3) {
        hideAllChats$delegate.setValue(null, $$delegatedProperties[31], Boolean.valueOf(r3));
    }

    public static final void setHideArchiveFolder(boolean r3) {
        hideArchiveFolder$delegate.setValue(null, $$delegatedProperties[11], Boolean.valueOf(r3));
    }

    public static final void setHideCameraTile(boolean r3) {
        hideCameraTile$delegate.setValue(null, $$delegatedProperties[112(0x70, float:1.57E-43)], Boolean.valueOf(r3));
    }

    public static final void setHideDialogsSearchBar(boolean r3) {
        hideDialogsSearchBar$delegate.setValue(null, $$delegatedProperties[26], Boolean.valueOf(r3));
    }

    public static final void setHideFloatingButton(boolean r3) {
        hideFloatingButton$delegate.setValue(null, $$delegatedProperties[25], Boolean.valueOf(r3));
    }

    public static final void setHideKeyboardOnScroll(boolean r3) {
        hideKeyboardOnScroll$delegate.setValue(null, $$delegatedProperties[80], Boolean.valueOf(r3));
    }

    public static final void setHidePhoneNumber(boolean r3) {
        hidePhoneNumber$delegate.setValue(null, $$delegatedProperties[9], Boolean.valueOf(r3));
    }

    public static final void setHideReactionsInChannels(boolean r3) {
        hideReactionsInChannels$delegate.setValue(null, $$delegatedProperties[63], Boolean.valueOf(r3));
    }

    public static final void setHideReactionsInGroups(boolean r3) {
        hideReactionsInGroups$delegate.setValue(null, $$delegatedProperties[64], Boolean.valueOf(r3));
    }

    public static final void setHideReactionsInPrivateChats(boolean r3) {
        hideReactionsInPrivateChats$delegate.setValue(null, $$delegatedProperties[62], Boolean.valueOf(r3));
    }

    public static final void setHideSendAsPeer(boolean r3) {
        hideSendAsPeer$delegate.setValue(null, $$delegatedProperties[84], Boolean.valueOf(r3));
    }

    public static final void setHideShareButton(boolean r3) {
        hideShareButton$delegate.setValue(null, $$delegatedProperties[89], Boolean.valueOf(r3));
    }

    public static final void setHideStories(boolean r3) {
        hideStories$delegate.setValue(null, $$delegatedProperties[24], Boolean.valueOf(r3));
    }

    public static final void setIconPack(IconPackType r3) {
        iconPack$delegate.setValue(null, $$delegatedProperties[16], r3);
    }

    public static final void setImmersiveDrawerAnimation(boolean r3) {
        immersiveDrawerAnimation$delegate.setValue(null, $$delegatedProperties[52], Boolean.valueOf(r3));
    }

    public static final void setInAppVibration(boolean r3) {
        inAppVibration$delegate.setValue(null, $$delegatedProperties[4], Boolean.valueOf(r3));
    }

    public static final void setInlineMathResult(boolean r3) {
        inlineMathResult$delegate.setValue(null, $$delegatedProperties[82], Boolean.valueOf(r3));
    }

    public static final void setLogging(boolean r2) {
        BuildVars.LOGS_ENABLED = r2;
        SharedPreferences.Editor r0 = systemConfigPrefs().edit();
        r0.putBoolean("logsEnabled", r2);
        r0.apply();
        if (r2 == true) goto L6;
        FileLog.cleanupLogs();
        return;
    }

    public static final void setNavigationDrawer(boolean r3) {
        navigationDrawer$delegate.setValue(null, $$delegatedProperties[51], Boolean.valueOf(r3));
    }

    public static final void setNewChatHeaderStyle(boolean r3) {
        newChatHeaderStyle$delegate.setValue(null, $$delegatedProperties[38], Boolean.valueOf(r3));
    }

    public static final void setNewFabStyle(boolean r3) {
        newFabStyle$delegate.setValue(null, $$delegatedProperties[40], Boolean.valueOf(r3));
    }

    public static final void setNewLoadingStyle(boolean r3) {
        newLoadingStyle$delegate.setValue(null, $$delegatedProperties[35], Boolean.valueOf(r3));
    }

    public static final void setNewNavigationBarStyle(boolean r3) {
        newNavigationBarStyle$delegate.setValue(null, $$delegatedProperties[39], Boolean.valueOf(r3));
    }

    public static final void setNewSliderStyle(boolean r3) {
        newSliderStyle$delegate.setValue(null, $$delegatedProperties[36], Boolean.valueOf(r3));
    }

    public static final void setNewSwitchStyle(boolean r3) {
        newSwitchStyle$delegate.setValue(null, $$delegatedProperties[37], Boolean.valueOf(r3));
    }

    public static final void setPauseOnMinimizeRound(boolean r3) {
        pauseOnMinimizeRound$delegate.setValue(null, $$delegatedProperties[119(0x77, float:1.67E-43)], Boolean.valueOf(r3));
    }

    public static final void setPauseOnMinimizeVideo(boolean r3) {
        pauseOnMinimizeVideo$delegate.setValue(null, $$delegatedProperties[117(0x75, float:1.64E-43)], Boolean.valueOf(r3));
    }

    public static final void setPauseOnMinimizeVoice(boolean r3) {
        pauseOnMinimizeVoice$delegate.setValue(null, $$delegatedProperties[118(0x76, float:1.65E-43)], Boolean.valueOf(r3));
    }

    public static final void setPinnedPlugins(Set<String> r3) {
        pinnedPlugins$delegate.setValue(null, $$delegatedProperties[135(0x87, float:1.89E-43)], r3);
    }

    public static final void setPluginsCompactView(boolean r3) {
        pluginsCompactView$delegate.setValue(null, $$delegatedProperties[130(0x82, float:1.82E-43)], Boolean.valueOf(r3));
    }

    public static final void setPluginsDevMode(boolean r3) {
        pluginsDevMode$delegate.setValue(null, $$delegatedProperties[128(0x80, float:1.8E-43)], Boolean.valueOf(r3));
    }

    public static final void setPluginsDisableArtOpts(boolean r3) {
        pluginsDisableArtOpts$delegate.setValue(null, $$delegatedProperties[133(0x85, float:1.86E-43)], Boolean.valueOf(r3));
    }

    public static final void setPluginsEngine(boolean r0) {
        pluginsEngine = r0;
    }

    public static final void setPluginsPySdkAutoUpdate(boolean r3) {
        pluginsPySdkAutoUpdate$delegate.setValue(null, $$delegatedProperties[131(0x83, float:1.84E-43)], Boolean.valueOf(r3));
    }

    public static final void setPluginsPySdkBetaVersions(boolean r3) {
        pluginsPySdkBetaVersions$delegate.setValue(null, $$delegatedProperties[132(0x84, float:1.85E-43)], Boolean.valueOf(r3));
    }

    public static final void setPluginsSafeMode(boolean r3) {
        pluginsSafeMode$delegate.setValue(null, $$delegatedProperties[129(0x81, float:1.81E-43)], Boolean.valueOf(r3));
    }

    public static final void setPluginsUnknownSources(boolean r3) {
        pluginsUnknownSources$delegate.setValue(null, $$delegatedProperties[134(0x86, float:1.88E-43)], Boolean.valueOf(r3));
    }

    public static final void setPostprocessingWithAi(boolean r3) {
        postprocessingWithAi$delegate.setValue(null, $$delegatedProperties[101(0x65, float:1.42E-43)], Boolean.valueOf(r3));
    }

    public static final void setPredictiveBackIntensity(float r3) {
        predictiveBackIntensity$delegate.setValue(null, $$delegatedProperties[45], Float.valueOf(r3));
    }

    public static final void setPreferOriginalQuality(boolean r3) {
        preferOriginalQuality$delegate.setValue(null, $$delegatedProperties[114(0x72, float:1.6E-43)], Boolean.valueOf(r3));
    }

    public static final void setProxyDisabledOn(ProxyDisableCondition r0, boolean r1) {
        if (r1 == false) goto L4;
        int r12 = getDoNotUseProxy();
        int r02 = r0.getFlag() | r12;
    L5:
        setDoNotUseProxy(r02);
        return;
    L4:
        int r13 = getDoNotUseProxy();
        r02 = (~r0.getFlag()) & r13;
        goto L5
    }

    public static final void setQuickAdminShortcuts(boolean r3) {
        quickAdminShortcuts$delegate.setValue(null, $$delegatedProperties[76], Boolean.valueOf(r3));
    }

    public static final void setQuickTransitionForChannels(boolean r3) {
        quickTransitionForChannels$delegate.setValue(null, $$delegatedProperties[77], Boolean.valueOf(r3));
    }

    public static final void setQuickTransitionForTopics(boolean r3) {
        quickTransitionForTopics$delegate.setValue(null, $$delegatedProperties[78], Boolean.valueOf(r3));
    }

    public static final void setRecognitionLanguage(String r3) {
        recognitionLanguage$delegate.setValue(null, $$delegatedProperties[100], r3);
    }

    public static final void setRelativeLastSeen(boolean r3) {
        relativeLastSeen$delegate.setValue(null, $$delegatedProperties[3], Boolean.valueOf(r3));
    }

    public static final void setRememberLastUsedCamera(boolean r3) {
        rememberLastUsedCamera$delegate.setValue(null, $$delegatedProperties[107(0x6b, float:1.5E-43)], Boolean.valueOf(r3));
    }

    public static final void setRemoveMessageTail(boolean r3) {
        removeMessageTail$delegate.setValue(null, $$delegatedProperties[85], Boolean.valueOf(r3));
    }

    public static final void setReplaceEditedWithIcon(boolean r3) {
        replaceEditedWithIcon$delegate.setValue(null, $$delegatedProperties[86], Boolean.valueOf(r3));
    }

    public static final void setReplyBackground(boolean r3) {
        replyBackground$delegate.setValue(null, $$delegatedProperties[59], Boolean.valueOf(r3));
    }

    public static final void setReplyColors(boolean r3) {
        replyColors$delegate.setValue(null, $$delegatedProperties[57], Boolean.valueOf(r3));
    }

    public static final void setReplyEmoji(boolean r3) {
        replyEmoji$delegate.setValue(null, $$delegatedProperties[58], Boolean.valueOf(r3));
    }

    public static final void setSdkUpdateScheduleTimestamp(long r3) {
        sdkUpdateScheduleTimestamp$delegate.setValue(null, $$delegatedProperties[124(0x7c, float:1.74E-43)], Long.valueOf(r3));
    }

    public static final void setSectionRadius(float r3) {
        sectionRadius$delegate.setValue(null, $$delegatedProperties[33], Float.valueOf(r3));
    }

    public static final void setSectionsSeparatedHeaders(boolean r2) {
        if (getDividerStyle() != DividerStyle.SEGMENTS) goto L5;
        r2 = true;
    L5:
        setSectionsSeparatedHeadersPreference(r2);
    }

    private static final void setSectionsSeparatedHeadersPreference(boolean r3) {
        sectionsSeparatedHeadersPreference$delegate.setValue(null, $$delegatedProperties[34], Boolean.valueOf(r3));
    }

    public static final void setSenderMiniAvatars(boolean r3) {
        senderMiniAvatars$delegate.setValue(null, $$delegatedProperties[27], Boolean.valueOf(r3));
    }

    public static final void setShowClearButton(boolean r3) {
        showClearButton$delegate.setValue(null, $$delegatedProperties[94], Boolean.valueOf(r3));
    }

    public static final void setShowCopyPhotoButton(boolean r3) {
        showCopyPhotoButton$delegate.setValue(null, $$delegatedProperties[91], Boolean.valueOf(r3));
    }

    public static final void setShowDetailsButton(boolean r3) {
        showDetailsButton$delegate.setValue(null, $$delegatedProperties[98], Boolean.valueOf(r3));
    }

    public static final void setShowFeedTab(boolean r3) {
        showFeedTab$delegate.setValue(null, $$delegatedProperties[53], Boolean.valueOf(r3));
    }

    public static final void setShowFeedUnreadCounter(boolean r3) {
        showFeedUnreadCounter$delegate.setValue(null, $$delegatedProperties[54], Boolean.valueOf(r3));
    }

    public static final void setShowForwardsCount(boolean r3) {
        showForwardsCount$delegate.setValue(null, $$delegatedProperties[88], Boolean.valueOf(r3));
    }

    public static final void setShowGenerateButton(boolean r3) {
        showGenerateButton$delegate.setValue(null, $$delegatedProperties[97], Boolean.valueOf(r3));
    }

    public static final void setShowHistoryButton(boolean r3) {
        showHistoryButton$delegate.setValue(null, $$delegatedProperties[95], Boolean.valueOf(r3));
    }

    public static final void setShowIdAndDc(int r3) {
        showIdAndDc$delegate.setValue(null, $$delegatedProperties[10], Integer.valueOf(r3));
    }

    public static final void setShowOnlineStatus(boolean r3) {
        showOnlineStatus$delegate.setValue(null, $$delegatedProperties[87], Boolean.valueOf(r3));
    }

    public static final void setShowRepeatMessageButton(boolean r3) {
        showRepeatMessageButton$delegate.setValue(null, $$delegatedProperties[93], Boolean.valueOf(r3));
    }

    public static final void setShowReportButton(boolean r3) {
        showReportButton$delegate.setValue(null, $$delegatedProperties[96], Boolean.valueOf(r3));
    }

    public static final void setShowResultsBeforeVoting(boolean r3) {
        showResultsBeforeVoting$delegate.setValue(null, $$delegatedProperties[90], Boolean.valueOf(r3));
    }

    public static final void setShowSaveMessageButton(boolean r3) {
        showSaveMessageButton$delegate.setValue(null, $$delegatedProperties[92], Boolean.valueOf(r3));
    }

    public static final void setSingleCornerRadius(boolean r3) {
        singleCornerRadius$delegate.setValue(null, $$delegatedProperties[19], Boolean.valueOf(r3));
    }

    public static final void setSquareFab(boolean r3) {
        squareFab$delegate.setValue(null, $$delegatedProperties[32], Boolean.valueOf(r3));
    }

    public static final void setStartWithWideAngleCamera(boolean r3) {
        startWithWideAngleCamera$delegate.setValue(null, $$delegatedProperties[108(0x6c, float:1.51E-43)], Boolean.valueOf(r3));
    }

    public static final void setStaticZoom(boolean r3) {
        staticZoom$delegate.setValue(null, $$delegatedProperties[110(0x6e, float:1.54E-43)], Boolean.valueOf(r3));
    }

    public static final void setStickerShape(int r3) {
        stickerShape$delegate.setValue(null, $$delegatedProperties[60], Integer.valueOf(r3));
    }

    public static final void setStickerSize(float r3) {
        stickerSize$delegate.setValue(null, $$delegatedProperties[55], Float.valueOf(r3));
    }

    public static final void setStickerTimeMode(StickerTimeMode r3) {
        stickerTimeMode$delegate.setValue(null, $$delegatedProperties[56], r3);
    }

    public static final void setSwipeActions(String r3) {
        swipeActions$delegate.setValue(null, $$delegatedProperties[67], r3);
    }

    public static final void setSwipeActionsLoop(boolean r3) {
        swipeActionsLoop$delegate.setValue(null, $$delegatedProperties[68], Boolean.valueOf(r3));
    }

    public static final void setSwipeActionsReversed(boolean r3) {
        swipeActionsReversed$delegate.setValue(null, $$delegatedProperties[69], Boolean.valueOf(r3));
    }

    public static final void setSwipeToPip(boolean r3) {
        swipeToPip$delegate.setValue(null, $$delegatedProperties[115(0x73, float:1.61E-43)], Boolean.valueOf(r3));
    }

    public static final void setTabCounterMode(TabCounterMode r3) {
        tabCounterMode$delegate.setValue(null, $$delegatedProperties[30], r3);
    }

    public static final void setTabIcons(TabIconsMode r3) {
        tabIcons$delegate.setValue(null, $$delegatedProperties[29], r3);
    }

    public static final void setTabletMode(int r3) {
        tabletMode$delegate.setValue(null, $$delegatedProperties[41], Integer.valueOf(r3));
    }

    public static final void setTargetLang(String r3) {
        targetLang$delegate.setValue(null, $$delegatedProperties[125(0x7d, float:1.75E-43)], r3);
    }

    public static final void setTelegramAiEditor(boolean r3) {
        telegramAiEditor$delegate.setValue(null, $$delegatedProperties[73], Boolean.valueOf(r3));
    }

    public static final void setTelegramAiInstantViewSummaries(boolean r3) {
        telegramAiInstantViewSummaries$delegate.setValue(null, $$delegatedProperties[75], Boolean.valueOf(r3));
    }

    public static final void setTelegramAiSummaries(boolean r3) {
        telegramAiSummaries$delegate.setValue(null, $$delegatedProperties[74], Boolean.valueOf(r3));
    }

    public static final void setTitleText(int r3) {
        titleText$delegate.setValue(null, $$delegatedProperties[28], Integer.valueOf(r3));
    }

    public static final void setTransitionAnimation(TransitionAnimation r3) {
        transitionAnimation$delegate.setValue(null, $$delegatedProperties[46], r3);
    }

    public static final void setTranslationProvider(int r3) {
        translationProvider$delegate.setValue(null, $$delegatedProperties[0], Integer.valueOf(r3));
    }

    public static final void setUnlimitedRecentStickers(boolean r3) {
        unlimitedRecentStickers$delegate.setValue(null, $$delegatedProperties[61], Boolean.valueOf(r3));
    }

    public static final void setUnmuteWithVolumeButtons(boolean r3) {
        unmuteWithVolumeButtons$delegate.setValue(null, $$delegatedProperties[116(0x74, float:1.63E-43)], Boolean.valueOf(r3));
    }

    public static final void setUpdateScheduleTimestamp(long r3) {
        updateScheduleTimestamp$delegate.setValue(null, $$delegatedProperties[123(0x7b, float:1.72E-43)], Long.valueOf(r3));
    }

    public static final void setUploadSpeedBoost(boolean r3) {
        uploadSpeedBoost$delegate.setValue(null, $$delegatedProperties[8], Boolean.valueOf(r3));
    }

    public static final void setUseGoogleAnalytics(boolean r3) {
        useGoogleAnalytics$delegate.setValue(null, $$delegatedProperties[121(0x79, float:1.7E-43)], Boolean.valueOf(r3));
    }

    public static final void setUseGoogleCrashlytics(boolean r3) {
        useGoogleCrashlytics$delegate.setValue(null, $$delegatedProperties[120(0x78, float:1.68E-43)], Boolean.valueOf(r3));
    }

    public static final void setUseSystemFonts(boolean r3) {
        useSystemFonts$delegate.setValue(null, $$delegatedProperties[42], Boolean.valueOf(r3));
    }

    public static final void setUseSystemIconShape(boolean r3) {
        useSystemIconShape$delegate.setValue(null, $$delegatedProperties[136(0x88, float:1.9E-43)], Boolean.valueOf(r3));
    }

    public static final void setVideoMessagesCamera(VideoMessagesCamera r3) {
        videoMessagesCamera$delegate.setValue(null, $$delegatedProperties[106(0x6a, float:1.49E-43)], r3);
    }

    public static final void setWidePostsInChannels(boolean r3) {
        widePostsInChannels$delegate.setValue(null, $$delegatedProperties[72], Boolean.valueOf(r3));
    }

    public static final void setWidePostsInFeed(boolean r3) {
        widePostsInFeed$delegate.setValue(null, $$delegatedProperties[71], Boolean.valueOf(r3));
    }

    public static final void setZoomSlider(boolean r3) {
        zoomSlider$delegate.setValue(null, $$delegatedProperties[109(0x6d, float:1.53E-43)], Boolean.valueOf(r3));
    }

    private static final SharedPreferences systemConfigPrefs() {
        return ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0);
    }

    public static final void toggleLogging() {
        BuildVars.LOGS_ENABLED = !BuildVars.LOGS_ENABLED;
        SharedPreferences.Editor r0 = systemConfigPrefs().edit();
        r0.putBoolean("logsEnabled", BuildVars.LOGS_ENABLED);
        r0.apply();
        if (BuildVars.LOGS_ENABLED == true) goto L6;
        FileLog.cleanupLogs();
        return;
    }
}
