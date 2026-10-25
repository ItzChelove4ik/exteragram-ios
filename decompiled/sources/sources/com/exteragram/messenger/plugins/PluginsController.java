package com.exteragram.messenger.plugins;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.hooks.EventHookRecord;
import com.exteragram.messenger.plugins.hooks.HookRecord;
import com.exteragram.messenger.plugins.hooks.MenuItemRecord;
import com.exteragram.messenger.plugins.hooks.PluginsHooks;
import com.exteragram.messenger.plugins.hooks.XposedHookRecord;
import com.exteragram.messenger.plugins.models.SettingItem;
import com.exteragram.messenger.plugins.pip.PipController;
import com.exteragram.messenger.plugins.ui.PluginsActivity;
import com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet;
import com.exteragram.messenger.plugins.ui.components.SafeModeBottomSheet;
import com.exteragram.messenger.plugins.utils.MenuContextBuilder;
import com.exteragram.messenger.plugins.utils.NativeCrashHandler;
import com.exteragram.messenger.plugins.utils.PluginsWatchdog;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.sun.jna.Callback;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.SimpliFiles;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 ¹\u00012\u00020\u0001:\f´\u0001µ\u0001¶\u0001·\u0001¸\u0001¹\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010\u0006J\u0006\u0010:\u001a\u00020;J\u0010\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u000106J\u000e\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u00020#J\u0018\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u00020#2\b\u0010<\u001a\u0004\u0018\u000106J\u0006\u0010>\u001a\u00020;J\u0010\u0010?\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u000106J\u0006\u0010@\u001a\u00020;J\u000e\u0010@\u001a\u00020;2\u0006\u0010=\u001a\u00020#J\u0006\u0010A\u001a\u00020#J\u0017\u0010B\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\bCJ\u0017\u0010B\u001a\u00020#2\b\u0010D\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\bCJ\u001c\u0010E\u001a\u00020\u00172\b\u0010F\u001a\u0004\u0018\u00010\u00062\b\u0010G\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010H\u001a\u00020;2\b\u0010F\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010I\u001a\u00020\u00172\b\u0010J\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010K\u001a\u00020;H\u0002J\u0014\u0010L\u001a\u0004\u0018\u00010\u00062\b\u0010F\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010M\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\b\u00109\u001a\u0004\u0018\u00010\u0006J(\u0010N\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010O\u001a\u00020#2\u000e\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010QJ \u0010R\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\u000e\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010QJ\u0010\u0010S\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u0006J\u0012\u0010T\u001a\u0004\u0018\u00010\u00062\b\u0010U\u001a\u0004\u0018\u00010\u0006J\u001a\u0010V\u001a\u00020;2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010Y\u001a\u0004\u0018\u00010ZJ\"\u0010V\u001a\u00020;2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010[\u001a\u0004\u0018\u00010\u00062\u0006\u0010\\\u001a\u00020#J\u001c\u0010V\u001a\u00020;2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010]\u001a\u0004\u0018\u00010^H\u0002J\u0006\u0010_\u001a\u00020;J\u0010\u0010_\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u0006J\u0010\u0010`\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u0006J\u0010\u0010a\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u0006J\u001c\u0010b\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010c\u001a\u00020#H\u0007J\u001c\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u00010\u001f2\b\u00109\u001a\u0004\u0018\u00010\u0006J\u0010\u0010e\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u0006J\"\u0010f\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010g\u001a\u0004\u0018\u00010\u00062\u0006\u0010h\u001a\u00020#J&\u0010i\u001a\u0004\u0018\u00010\u00062\b\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010g\u001a\u0004\u0018\u00010\u00062\b\u0010h\u001a\u0004\u0018\u00010\u0006J\"\u0010j\u001a\u00020\u00172\b\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010g\u001a\u0004\u0018\u00010\u00062\u0006\u0010h\u001a\u00020\u0017J$\u0010k\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010g\u001a\u0004\u0018\u00010\u00062\b\u0010l\u001a\u0004\u0018\u00010!J.\u0010m\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010g\u001a\u0004\u0018\u00010\u00062\b\u0010l\u001a\u0004\u0018\u00010!2\b\u0010n\u001a\u0004\u0018\u00010oJ$\u0010p\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010q\u001a\u0004\u0018\u00010\u00142\u0006\u0010r\u001a\u00020\u0006H\u0002J(\u0010s\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020#2\u0006\u0010v\u001a\u00020\u0017J.\u0010w\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\u0012\u0010x\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020#0y2\u0006\u0010r\u001a\u00020\u0006H\u0002J\u0018\u0010z\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010t\u001a\u00020\u0006J\u001e\u0010{\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\f\u0010|\u001a\b\u0018\u00010}R\u00020~J7\u0010\u007f\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062%\u0010\u0080\u0001\u001a \u0012\b\u0012\u00060}R\u00020~\u0018\u00010\u0081\u0001j\u000f\u0012\b\u0012\u00060}R\u00020~\u0018\u0001`\u0082\u0001J\u001f\u0010\u0083\u0001\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u00062\f\u0010|\u001a\b\u0018\u00010}R\u00020~J\u0011\u0010\u0084\u0001\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u0006J\u001e\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00062\b\u00109\u001a\u0004\u0018\u00010\u00062\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010oJ\u001c\u0010\u0087\u0001\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u00062\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0006J\u0011\u0010\u0089\u0001\u001a\u00020;2\b\u00109\u001a\u0004\u0018\u00010\u0006J$\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00062\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001J/\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00062\u0015\u0010\u008e\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020!\u0018\u00010\u001fJ\u0007\u0010\u008f\u0001\u001a\u00020;J\u0012\u0010\u0090\u0001\u001a\u00020;2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\t\u0010\u0094\u0001\u001a\u00020;H\u0002J\t\u0010\u0095\u0001\u001a\u00020;H\u0002J\t\u0010\u0096\u0001\u001a\u00020#H\u0002J\u0011\u0010\u0097\u0001\u001a\u00020;2\u0006\u00109\u001a\u00020\u0006H\u0002J\u0011\u0010\u0098\u0001\u001a\u00020;2\u0006\u00109\u001a\u00020\u0006H\u0002J\t\u0010\u0099\u0001\u001a\u00020;H\u0002J>\u0010\u009a\u0001\u001a\u0005\u0018\u0001H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u0006\u0010t\u001a\u00020\u00062\n\u0010\u009c\u0001\u001a\u0005\u0018\u0001H\u009b\u00012\u000f\u0010\u009d\u0001\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u009e\u0001H\u0002¢\u0006\u0003\u0010\u009f\u0001J(\u0010 \u0001\u001a\u0005\u0018\u00010¡\u00012\u0007\u0010¢\u0001\u001a\u00020\u00062\u0007\u0010£\u0001\u001a\u00020\u00172\b\u0010¤\u0001\u001a\u00030¡\u0001H\u0016J4\u0010¥\u0001\u001a\u00030¦\u00012\u0007\u0010¢\u0001\u001a\u00020\u00062\u0007\u0010£\u0001\u001a\u00020\u00172\n\u0010§\u0001\u001a\u0005\u0018\u00010¡\u00012\n\u0010¨\u0001\u001a\u0005\u0018\u00010©\u0001H\u0016J(\u0010ª\u0001\u001a\u0005\u0018\u00010«\u00012\u0007\u0010¬\u0001\u001a\u00020\u00062\u0007\u0010£\u0001\u001a\u00020\u00172\b\u0010\u00ad\u0001\u001a\u00030«\u0001H\u0016J(\u0010®\u0001\u001a\u0005\u0018\u00010¯\u00012\u0007\u0010°\u0001\u001a\u00020\u00062\u0007\u0010£\u0001\u001a\u00020\u00172\b\u0010±\u0001\u001a\u00030¯\u0001H\u0016J\u001e\u0010²\u0001\u001a\u0005\u0018\u00010³\u00012\u0007\u0010£\u0001\u001a\u00020\u00172\u0007\u0010]\u001a\u00030³\u0001H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR#\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00170\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00190\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000b0\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006º\u0001"}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController;", "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks;", "<init>", "()V", PluginsConstants.PLUGINS, "Ljava/util/concurrent/ConcurrentHashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/Plugin;", "getPlugins", "()Ljava/util/concurrent/ConcurrentHashMap;", "settings", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/models/SettingItem;", "getSettings", "menuItemsById", "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;", "menuItemsByMenuType", "Ljava/util/concurrent/CopyOnWriteArrayList;", "hooks", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/hooks/HookRecord;", "interestedPluginsCache", "fileIconIdsByExtension", _UrlKt.FRAGMENT_ENCODE_SET, "fileIconDrawablesById", "Landroid/graphics/drawable/Drawable;", "nextFileIconId", "Ljava/util/concurrent/atomic/AtomicInteger;", "substringMatchEventHooksCache", "Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;", "exactMatchEventHooksCache", _UrlKt.FRAGMENT_ENCODE_SET, "hooksCacheLock", _UrlKt.FRAGMENT_ENCODE_SET, "hooksCacheDirty", _UrlKt.FRAGMENT_ENCODE_SET, "initialized", "pluginsDir", "Ljava/io/File;", "getPluginsDir", "()Ljava/io/File;", "setPluginsDir", "(Ljava/io/File;)V", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "setPreferences", "(Landroid/content/SharedPreferences;)V", "watchdog", "Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;", "getWatchdog", "()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;", "updateNotificationRunnable", "Ljava/lang/Runnable;", "getPluginEngine", "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "pluginId", "init", _UrlKt.FRAGMENT_ENCODE_SET, "onDone", "startWithSafeMode", "checkDevServers", "shutdown", "restart", "isInitialized", "isPluginActive", "isPluginActive$TMessagesProj", "plugin", "registerFileIconInternal", "extension", "drawable", "unregisterFileIconInternal", "getFileIconIdInternal", "fileName", "clearFileIconsInternal", "normalizeFileExtension", "getPluginSettingsList", "setPluginEnabled", "enabled", Callback.METHOD_NAME, "Lorg/telegram/messenger/Utilities$Callback;", "deletePlugin", "cleanupPlugin", "getPluginPath", "id", "showInstallDialog", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "messageObject", "Lorg/telegram/messenger/MessageObject;", "filePath", "trusted", PluginsConstants.PARAMS, "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;", "loadPluginSettings", "hasPluginSettings", "invalidatePluginSettings", "clearPluginSettingsPreferences", "clearEnabledState", "getPluginSettingsPreferences", "hasPluginSettingsPreferences", "getPluginSettingBoolean", PluginsConstants.Settings.KEY, "defaultValue", "getPluginSettingString", "getPluginSettingInt", "setPluginSetting", "value", "setPluginSettingAndTriggerOnChange", "onChangeCallback", "Lcom/chaquo/python/PyObject;", "addHook", "newHook", "logMessage", "addEventHook", "hookName", "matchSubstring", PluginsConstants.MenuItemProperties.PRIORITY, "removeHook", "filter", "Lkotlin/Function1;", "removeEventHook", "addXposedHook", "unhook", "Lde/robv/android/xposed/XC_MethodHook$Unhook;", "Lde/robv/android/xposed/XC_MethodHook;", "addXposedHooks", "unhooks", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "removeXposedHook", "removeHooksByPluginId", "addMenuItem", "pyMenuItemData", "removeMenuItem", "itemId", "removeMenuItemsByPluginId", "getMenuItemsForLocation", "menuType", "builder", "Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;", "contextData", "notifyPluginsChanged", "executeOnAppEvent", "eventType", "getInterestedPluginIds", "eventName", "rebuildHooksCacheIfNeeded", "ensurePreferences", "isOnPluginsQueueThread", "notifyPluginSettingsRegistered", "notifyPluginSettingsUnregistered", "notifyMenuItemsUpdated", "executeGenericHook", "T", "initialObject", "caller", "Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;", "(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;", "executePreRequestHook", "Lorg/telegram/tgnet/TLObject;", "requestName", "account", PluginsConstants.REQUEST, "executePostRequestHook", "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;", PluginsConstants.RESPONSE, "error", "Lorg/telegram/tgnet/TLRPC$TL_error;", "executeUpdateHook", "Lorg/telegram/tgnet/TLRPC$Update;", "updateName", PluginsConstants.UPDATE, "executeUpdatesHook", "Lorg/telegram/tgnet/TLRPC$Updates;", "containerName", PluginsConstants.UPDATES, "executeSendMessageHook", "Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;", "EngineHookCaller", "HookResult", "PluginValidationResult", "PluginsEngine", "SingletonHolder", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginsController.kt\ncom/exteragram/messenger/plugins/PluginsController\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1337:1\n1#2:1338\n41#3,12:1339\n41#3,12:1351\n1915#4,2:1363\n1915#4,2:1365\n*S KotlinDebug\n*F\n+ 1 PluginsController.kt\ncom/exteragram/messenger/plugins/PluginsController\n*L\n164#1:1339,12\n478#1:1351,12\n598#1:1363,2\n813#1:1365,2\n*E\n"})
/* loaded from: classes.dex */
public final class PluginsController implements PluginsHooks {
    public static final Companion Companion = null;
    public static final int PLUGIN_FILE_ICON_ID_START = 101;
    public static final int PLUGIN_FILE_ICON_NONE = -1;
    public static final String PREF_PLUGIN_ENABLED_KEY_PREFIX = "plugin_enabled_";
    private static final ConcurrentHashMap<String, PluginsEngine> enginesMap = null;
    private volatile Map<String, ? extends List<EventHookRecord>> exactMatchEventHooksCache;
    private final ConcurrentHashMap<Integer, Drawable> fileIconDrawablesById;
    private final ConcurrentHashMap<String, Integer> fileIconIdsByExtension;
    private final ConcurrentHashMap<String, Set<HookRecord>> hooks;
    private volatile boolean hooksCacheDirty;
    private final Object hooksCacheLock;
    private volatile boolean initialized;
    private final ConcurrentHashMap<String, List<String>> interestedPluginsCache;
    private final ConcurrentHashMap<String, MenuItemRecord> menuItemsById;
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<MenuItemRecord>> menuItemsByMenuType;
    private final AtomicInteger nextFileIconId;
    private final ConcurrentHashMap<String, Plugin> plugins;
    private File pluginsDir;
    private SharedPreferences preferences;
    private final ConcurrentHashMap<String, List<SettingItem>> settings;
    private volatile List<EventHookRecord> substringMatchEventHooksCache;
    private final Runnable updateNotificationRunnable;
    private final PluginsWatchdog watchdog;

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\nH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\u001c\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007J\b\u0010\u0015\u001a\u00020\u0014H\u0007J\u0012\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0007J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0007J\b\u0010\u001c\u001a\u00020\u0019H\u0007J\b\u0010\u001d\u001a\u00020\u0019H\u0007J\b\u0010\u001e\u001a\u00020\u0014H\u0007J\u0012\u0010\u001f\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J\u001c\u0010\u001f\u001a\u00020\u00192\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J\u0014\u0010$\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0007J\u0012\u0010%\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010\u0005H\u0007J\u001c\u0010%\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010\u00052\b\u0010'\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010(\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010)\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010\u00052\u0006\u0010*\u001a\u00020\u0019H\u0007J\u0010\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020-H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PREF_PLUGIN_ENABLED_KEY_PREFIX", _UrlKt.FRAGMENT_ENCODE_SET, "PLUGIN_FILE_ICON_NONE", _UrlKt.FRAGMENT_ENCODE_SET, "PLUGIN_FILE_ICON_ID_START", "enginesMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "getEngines", "getInstance", "Lcom/exteragram/messenger/plugins/PluginsController;", "registerFileIcon", "extension", "drawable", "Landroid/graphics/drawable/Drawable;", "unregisterFileIcon", _UrlKt.FRAGMENT_ENCODE_SET, "clearFileIcons", "getFileIconId", "fileName", "isPluginFileIcon", _UrlKt.FRAGMENT_ENCODE_SET, "icon", "getPluginFileIconDrawable", "isPluginEngineSupported", "isPluginEngineAvailable", "applyArtOpts", "isPlugin", "messageObject", "Lorg/telegram/messenger/MessageObject;", "file", "Ljava/io/File;", "getPluginEngine", "openPluginSettings", "pluginId", "linkAlias", "isPluginPinned", "setPluginPinned", "isPinned", "runOnPluginsQueue", "runnable", "Ljava/lang/Runnable;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public static void $r8$lambda$CDU5DpkPmtTSVHIYTkRbZ4hinN8(BaseFragment r1) {
            r1.presentFragment(new PluginsActivity());
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final void applyArtOpts() {
            if (ExteraConfig.getPreferences().getBoolean("pluginsEngine", false) == true) goto L5;
            return;
        L5:
            if (ExteraConfig.getPluginsDisableArtOpts() == true) goto L7;
            return;
        L7:
            if (isPluginEngineSupported() == false) goto L17;
            XposedBridge.disableProfileSaver();     // Catch: Throwable -> L10
            return;
        L10:
            th = move-exception;
            FileLog.e(th);
            return;
        }

        @JvmStatic
        public final void clearFileIcons() {
            PluginsController.access$clearFileIconsInternal(getInstance());
        }

        @JvmStatic
        public final ConcurrentHashMap<String, PluginsEngine> getEngines() {
            return PluginsController.access$getEnginesMap$cp();
        }

        @JvmStatic
        public final int getFileIconId(String r1) {
            return PluginsController.access$getFileIconIdInternal(getInstance(), r1);
        }

        @JvmStatic
        public final PluginsController getInstance() {
            return SingletonHolder.INSTANCE.getINSTANCE();
        }

        @JvmStatic
        public final PluginsEngine getPluginEngine(File r4) {
            if (r4 != null) goto L5;
            return null;
        L5:
            Iterator<PluginsEngine> r3 = getEngines().values().iterator();
        L7:
            if (r3.hasNext() == false) goto L11;
            PluginsEngine r1 = r3.next();
            if (r1.isPlugin(r4, null) == false) goto L7;
            return r1;
        L11:
            return null;
        }

        @JvmStatic
        public final Drawable getPluginFileIconDrawable(int r1) {
            return (Drawable) PluginsController.access$getFileIconDrawablesById$p(getInstance()).get(Integer.valueOf(r1));
        }

        @JvmStatic
        public final boolean isPlugin(File r3, MessageObject r4) {
            if (r3 != null) goto L5;
            return false;
        L5:
            Iterator<PluginsEngine> r2 = getEngines().values().iterator();
        L7:
            if (r2.hasNext() == false) goto L12;
            if (r2.next().isPlugin(r3, r4) == false) goto L7;
            return true;
        L12:
            return false;
        }

        @JvmStatic
        public final boolean isPlugin(MessageObject r3) {
            String r0 = ChatUtils.getInstance().getPathToMessage(r3);
            if (r3 != null) goto L5;
            return false;
        L5:
            if (r3.getDocumentName() != null) goto L7;
            return false;
        L7:
            if (TextUtils.isEmpty(r0) == false) goto L9;
            return false;
        L9:
            if (isPlugin(new File(r0), r3) == true) goto L11;
            return false;
        L11:
            if (isPluginEngineSupported() == false) goto L19;
            return true;
        L19:
            return false;
        }

        @JvmStatic
        public final boolean isPluginEngineAvailable() {
            if (isPluginEngineSupported() == true) goto L5;
        L25:
            return false;
        L5:
            if (ExteraConfig.getPluginsEngine() == false) goto L25;
            if (ExteraConfig.getPluginsSafeMode() == true) goto L25;
            PluginsEngine r0 = (PluginsEngine) PluginsController.access$getEnginesMap$cp().get(PluginsConstants.PYTHON);
            if (r0 != null) goto L12;
        L14:
            Iterator<PluginsEngine> r5 = getEngines().values().iterator();
        L16:
            if (r5.hasNext() == false) goto L25;
            PluginsEngine r3 = r5.next();
            if (r3 == r0) goto L16;
            if (r3.isEngineAvailable() == false) goto L16;
            return true;
        L23:
            th = move-exception;
            FileLog.e("Error checking engine availability.", th);
            goto L16
        L12:
            if (r0.isEngineAvailable() != true) goto L14;
            return true;
        }

        @JvmStatic
        public final boolean isPluginEngineSupported() {
            return true;
        }

        @JvmStatic
        public final boolean isPluginFileIcon(int r2) {
            if (r2 >= 101) goto L7;
            return false;
        L7:
            return PluginsController.access$getFileIconDrawablesById$p(getInstance()).containsKey(Integer.valueOf(r2));
        }

        @JvmStatic
        public final boolean isPluginPinned(String r1) {
            if (r1 != null) goto L4;
            return false;
        L4:
            if (r1.length() != 0) goto L7;
            return false;
        L7:
            if (ExteraConfig.getPinnedPlugins().contains(r1) == false) goto L12;
            return true;
        L12:
            return false;
        }

        @JvmStatic
        public final void openPluginSettings(String r2) {
            openPluginSettings(r2, null);
        }

        @JvmStatic
        public final void openPluginSettings(String r9, String r10) {
            if (r9 != null) goto L4;
            return;
        L4:
            if (r9.length() == 0) goto L30;
            BaseFragment r0 = LaunchActivity.getLastFragment();
            if (r0 != null) goto L10;
            return;
        L10:
            if (ExteraConfig.getPluginsEngine() == true) goto L13;
            BulletinFactory.of(r0).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.PluginEngineNotEnabled, new Object[]{r9}), LocaleController.getString(R.string.Enable), 2750, new PluginsController$Companion$$ExternalSyntheticLambda0(r0)).show();
            return;
        L13:
            Plugin r1 = getInstance().getPlugins().get(r9);
            if (r1 != null) goto L18;
            BulletinFactory.of(r0).createEmojiBulletin("🤷\u200d♂️", LocaleController.formatString(R.string.PluginNotFound, new Object[]{r9})).show();
            return;
        L18:
            if (getInstance().hasPluginSettings(r9) == true) goto L21;
            BulletinFactory.of(r0).createEmojiBulletin("🤷\u200d♂️", LocaleController.formatString(R.string.PluginHasNoSettings, new Object[]{r1.getName()})).show();
            return;
        L21:
            PluginsEngine r8 = getInstance().getPluginEngine(r9);
            if (r8 == null) goto L29;
            if (r10 != null) goto L26;
            r8.openPluginSettings(r9, r0);
            return;
        L26:
            r8.openPluginSetting(r9, r10, r0);
            return;
        L29:
            return;
        }

        @JvmStatic
        public final int registerFileIcon(String r1, Drawable r2) {
            return PluginsController.access$registerFileIconInternal(getInstance(), r1, r2);
        }

        @JvmStatic
        public final void runOnPluginsQueue(Runnable r3) {
            if (Utilities.pluginsQueue == null) goto L7;
            if (Utilities.pluginsQueue.isAlive() == false) goto L7;
        L18:
            Utilities.pluginsQueue.postRunnable(r3);
            return;
        L7:
            monitor-enter(PluginsController.class);
        L13:
            th = move-exception;
            throw th;
        L9:
            if (Utilities.pluginsQueue != null) goto L11;
        L15:
            Utilities.pluginsQueue = new DispatchQueue("pluginsQueue");     // Catch: Throwable -> L13
        L16:
            Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L13
            monitor-exit(PluginsController.class);
            goto L18
        L11:
            if (Utilities.pluginsQueue.isAlive() == true) goto L16;
            goto L16
        }

        @JvmStatic
        public final void setPluginPinned(String r3, boolean r4) {
            if (r3 != null) goto L4;
            return;
        L4:
            if (r3.length() == 0) goto L13;
            HashSet r0 = new HashSet(ExteraConfig.getPinnedPlugins());
            if (r4 == true) goto L9;
            r0.remove(r3);
        L10:
            ExteraConfig.setPinnedPlugins(r0);
            ExteraConfig.getEditor().putStringSet("pinnedPlugins", r0).apply();
            getInstance().notifyPluginsChanged();
            return;
        L9:
            r0.add(r3);
            goto L10
        }

        @JvmStatic
        public final void unregisterFileIcon(String r1) {
            PluginsController.access$unregisterFileIconInternal(getInstance(), r1);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bâ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J-\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\n¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "engine", "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "obj", "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "(Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Ljava/lang/Object;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface EngineHookCaller<T> {
        HookResult<T> call(PluginsEngine r1, T r2, String r3);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001e\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000f\"\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "result", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "isFinal", "<init>", "(Ljava/lang/Object;ZZ)V", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getCancel", "()Z", "setCancel", "(Z)V", "setFinal", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HookResult<T> {
        private boolean cancel;
        private boolean isFinal;
        private T result;

        public HookResult(T r1, boolean r2, boolean r3) {
            this.result = r1;
            this.cancel = r2;
            this.isFinal = r3;
        }

        public final boolean getCancel() {
            return this.cancel;
        }

        public final T getResult() {
            return this.result;
        }

        public final boolean isFinal() {
            return this.isFinal;
        }

        public final void setCancel(boolean r1) {
            this.cancel = r1;
        }

        public final void setFinal(boolean r1) {
            this.isFinal = r1;
        }

        public final void setResult(T r1) {
            this.result = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;", _UrlKt.FRAGMENT_ENCODE_SET, "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "error", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V", "getPlugin", "()Lcom/exteragram/messenger/plugins/Plugin;", "setPlugin", "(Lcom/exteragram/messenger/plugins/Plugin;)V", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PluginValidationResult {
        private String error;
        private Plugin plugin;

        public PluginValidationResult(Plugin r1, String r2) {
            this.plugin = r1;
            this.error = r2;
        }

        public final String getError() {
            return this.error;
        }

        public final Plugin getPlugin() {
            return this.plugin;
        }

        public final void setError(String r1) {
            this.error = r1;
        }

        public final void setPlugin(Plugin r1) {
            this.plugin = r1;
        }
    }

    @Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\nH&J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J(\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013H&J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013H&J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u0011H&J\b\u0010\u0017\u001a\u00020\u0003H&J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0011H&J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0011H&J\u0018\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\u0006\u0010\u0016\u001a\u00020\u0011H&J$\u0010\u001d\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H&J\"\u0010 \u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\u0001H&J\u0010\u0010\"\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u001c\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0002\b\u0003\u0018\u00010$2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u0011H&J0\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010)2\u0006\u0010\u0010\u001a\u00020\u0011H&J:\u0010.\u001a\b\u0012\u0004\u0012\u00020/0(2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,2\b\u00100\u001a\u0004\u0018\u00010)2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u0010\u0010\u001a\u00020\u0011H&J0\u00103\u001a\b\u0012\u0004\u0012\u0002040(2\u0006\u00105\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,2\b\u00106\u001a\u0004\u0018\u0001042\u0006\u0010\u0010\u001a\u00020\u0011H&J0\u00107\u001a\b\u0012\u0004\u0012\u0002080(2\u0006\u00109\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,2\b\u0010:\u001a\u0004\u0018\u0001082\u0006\u0010\u0010\u001a\u00020\u0011H&J(\u0010;\u001a\b\u0012\u0004\u0012\u00020<0(2\u0006\u0010+\u001a\u00020,2\b\u0010=\u001a\u0004\u0018\u00010<2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020@2\u0006\u0010=\u001a\u00020AH&J4\u0010B\u001a\u00020\n2\u0006\u0010C\u001a\u00020\u00112\b\u0010D\u001a\u0004\u0018\u00010E2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00132\b\u0010F\u001a\u0004\u0018\u00010GH&J\u0018\u0010H\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010?\u001a\u00020@H&J\u0018\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020E2\u0006\u0010?\u001a\u00020@H&J \u0010J\u001a\u00020\n2\u0006\u0010I\u001a\u00020E2\u0006\u0010K\u001a\u00020\u00112\u0006\u0010?\u001a\u00020@H&J \u0010J\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010K\u001a\u00020\u00112\u0006\u0010?\u001a\u00020@H&¨\u0006LÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", _UrlKt.FRAGMENT_ENCODE_SET, "isPlugin", _UrlKt.FRAGMENT_ENCODE_SET, "file", "Ljava/io/File;", "messageObject", "Lorg/telegram/messenger/MessageObject;", "isEngineAvailable", "init", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Ljava/lang/Runnable;", "checkDevServer", "shutdown", "setPluginEnabled", "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", "Lorg/telegram/messenger/Utilities$Callback;", "deletePlugin", "getPluginPath", "id", "canOpenInExternalApp", "openInExternalApp", "sharePlugin", "loadPluginSettings", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/models/SettingItem;", "getPluginSetting", PluginsConstants.Settings.KEY, "defaultValue", "setPluginSetting", "value", "clearPluginSettings", "getAllPluginSettings", _UrlKt.FRAGMENT_ENCODE_SET, "executeOnAppEvent", "eventType", "executePreRequestHook", "Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "Lorg/telegram/tgnet/TLObject;", "requestName", "account", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "executePostRequestHook", "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;", PluginsConstants.RESPONSE, "error", "Lorg/telegram/tgnet/TLRPC$TL_error;", "executeUpdateHook", "Lorg/telegram/tgnet/TLRPC$Update;", "updateName", PluginsConstants.UPDATE, "executeUpdatesHook", "Lorg/telegram/tgnet/TLRPC$Updates;", "containerName", PluginsConstants.UPDATES, "executeSendMessageHook", "Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;", PluginsConstants.PARAMS, "showInstallDialog", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;", "loadPluginFromFile", "filePath", "pluginMetadata", "Lcom/exteragram/messenger/plugins/Plugin;", "delegate", "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;", "openPluginSettings", "plugin", "openPluginSetting", "linkAlias", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface PluginsEngine {
        boolean canOpenInExternalApp();

        void checkDevServer();

        void clearPluginSettings(String r1);

        void deletePlugin(String r1, Utilities.Callback<String> r2);

        void executeOnAppEvent(String r1);

        HookResult<PluginsHooks.PostRequestResult> executePostRequestHook(String r1, int r2, TLObject r3, TLRPC.TL_error r4, String r5);

        HookResult<TLObject> executePreRequestHook(String r1, int r2, TLObject r3, String r4);

        HookResult<SendMessagesHelper.SendMessageParams> executeSendMessageHook(int r1, SendMessagesHelper.SendMessageParams r2, String r3);

        HookResult<TLRPC.Update> executeUpdateHook(String r1, int r2, TLRPC.Update r3, String r4);

        HookResult<TLRPC.Updates> executeUpdatesHook(String r1, int r2, TLRPC.Updates r3, String r4);

        Map<String, ?> getAllPluginSettings(String r1);

        String getPluginPath(String r1);

        Object getPluginSetting(String r1, String r2, Object r3);

        void init(Runnable r1);

        boolean isEngineAvailable();

        boolean isPlugin(File r1, MessageObject r2);

        void loadPluginFromFile(String r1, Plugin r2, Utilities.Callback<String> r3, PipController.InstallerDelegate r4);

        List<SettingItem> loadPluginSettings(String r1);

        void openInExternalApp(String r1);

        void openPluginSetting(Plugin r1, String r2, BaseFragment r3);

        void openPluginSetting(String r1, String r2, BaseFragment r3);

        void openPluginSettings(Plugin r1, BaseFragment r2);

        void openPluginSettings(String r1, BaseFragment r2);

        void setPluginEnabled(String r1, boolean r2, Utilities.Callback<String> r3);

        void setPluginSetting(String r1, String r2, Object r3);

        void sharePlugin(String r1);

        void showInstallDialog(BaseFragment r1, InstallPluginBottomSheet.PluginInstallParams r2);

        void shutdown(Runnable r1);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\b\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/plugins/PluginsController$SingletonHolder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "INSTANCE", "Lcom/exteragram/messenger/plugins/PluginsController;", "getINSTANCE", "()Lcom/exteragram/messenger/plugins/PluginsController;", "INSTANCE$1", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = null;
        private static final PluginsController INSTANCE$1 = null;

        static {
            INSTANCE = new SingletonHolder();
            INSTANCE$1 = new PluginsController(null);
        }

        private SingletonHolder() {
        }

        public final PluginsController getINSTANCE() {
            return INSTANCE$1;
        }
    }

    /* renamed from: $r8$lambda$2rLdY-VQByxv_QUsag19aQTQmFQ, reason: not valid java name */
    public static HookResult m1542$r8$lambda$2rLdYVQByxv_QUsag19aQTQmFQ(String r0, int r1, PluginsEngine r2, TLRPC.Update r3, String r4) {
        return r2.executeUpdateHook(r0, r1, r3, r4);
    }

    /* renamed from: $r8$lambda$8YQxS2Zi9C0fnqZ5SFsFuLuS-Ck, reason: not valid java name */
    public static void m1543$r8$lambda$8YQxS2Zi9C0fnqZ5SFsFuLuSCk(BaseFragment r1) {
        r1.presentFragment(new PluginsActivity());
    }

    /* renamed from: $r8$lambda$8p8Kwk-k37JFIqGET_TR6NbwgbQ, reason: not valid java name */
    public static void m1544$r8$lambda$8p8Kwkk37JFIqGET_TR6NbwgbQ(Runnable r3) {
        Iterator<PluginsEngine> r0 = Companion.getEngines().values().iterator();
    L4:
        if (r0.hasNext() == false) goto L10;
        r0.next().init(r3);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        FileLog.e("Failed to initialize plugin engine", th);
        r3.run();
        goto L4
    }

    /* renamed from: $r8$lambda$BZJAQnCz8fBF-5ISJW3SPVHCvis, reason: not valid java name */
    public static Integer m1545$r8$lambda$BZJAQnCz8fBF5ISJW3SPVHCvis(EventHookRecord r0, String r1, Integer r2) {
        if (r2 != null) goto L6;
        int r02 = r0.getPriority();
    L5:
        return Integer.valueOf(r02);
    L6:
        r02 = Math.max(r2.intValue(), r0.getPriority());
        goto L5
    }

    /* renamed from: $r8$lambda$CYXYoZ0xzdvz-t3MkDUbIPdLop8, reason: not valid java name */
    public static void m1546$r8$lambda$CYXYoZ0xzdvzt3MkDUbIPdLop8() {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L6;
        new SafeModeBottomSheet(r0).show();
        return;
    }

    /* renamed from: $r8$lambda$DcwyhKBuZ-N-JhR0i98Zmug-Je0, reason: not valid java name */
    public static void m1547$r8$lambda$DcwyhKBuZNJhR0i98ZmugJe0(String r2) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pluginSettingsUnregistered, new Object[]{r2});
    }

    public static void $r8$lambda$GRs7We6PFyg6apyapVWYinmnxH0(PluginsController r1, String r2, boolean r3, Utilities.Callback r4) {
        PluginsEngine r0 = r1.getPluginEngine(r2);
        if (r0 == null) goto L7;
        if (r2 == null) goto L8;
        r0.setPluginEnabled(r2, r3, r4);
        r1.interestedPluginsCache.clear();
        return;
    L8:
        return;
    }

    public static void $r8$lambda$GqXcZtNTihu2o6QC3dyJOsjNgck(PluginsController r0, String r1, Utilities.Callback r2) {
        PluginsEngine r02 = r0.getPluginEngine(r1);
        if (r02 == null) goto L7;
        if (r1 == null) goto L8;
        r02.deletePlugin(r1, r2);
        return;
    L8:
        return;
    }

    public static HookResult $r8$lambda$IWnCzRTqaOHWXjeZmvg6EKw6nq0(String r8, int r9, PluginsEngine r10, PluginsHooks.PostRequestResult r11, String r12) {
        TLRPC.TL_error r0 = null;
        if (r11 == null) goto L5;
        TLObject r5 = r11.getResponse();
    L6:
        if (r11 == null) goto L9;
        r0 = r11.getError();
    L9:
        return r10.executePostRequestHook(r8, r9, r5, r0, r12);
    L5:
        r5 = null;
        goto L6
    }

    /* renamed from: $r8$lambda$JEt-MS2RwmV7tl-Tz-EeauQefL0, reason: not valid java name */
    public static void m1548$r8$lambda$JEtMS2RwmV7tlTzEeauQefL0() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pluginMenuItemsUpdated, new Object[0]);
    }

    /* renamed from: $r8$lambda$JqkXOllW4WPbvRAkDKW_90-FcXQ, reason: not valid java name */
    public static void m1549$r8$lambda$JqkXOllW4WPbvRAkDKW_90FcXQ(AtomicInteger r1, PluginsController r2, Runnable r3) {
        if (r1.incrementAndGet() < Companion.getEngines().size()) goto L8;
        r2.watchdog.stop();
        r2.plugins.clear();
        r2.settings.clear();
        r2.initialized = false;
        FileLog.d("Plugin system shut down.");
        if (r3 == null) goto L9;
        r3.run();
        return;
    L9:
        return;
    }

    public static int $r8$lambda$LvFWMJ2QaDesJqlG3S5k6tYhkXM(MenuItemRecord r0) {
        return r0.getPriority();
    }

    public static HookResult $r8$lambda$MmT7vCRk4Nquw9rR9nIOFmGcmH0(int r0, PluginsEngine r1, SendMessagesHelper.SendMessageParams r2, String r3) {
        return r1.executeSendMessageHook(r0, r2, r3);
    }

    public static void $r8$lambda$NIummZv5ylJ7GWVYy9_HekCWNy0(PluginsController r3, String r4) {
        PluginsEngine r1 = r3.getPluginEngine(r4);     // Catch: Throwable -> L10
        if (r1 != null) goto L6;
        return;
    L6:
        List<SettingItem> r12 = r1.loadPluginSettings(r4);     // Catch: Throwable -> L10
        if (r12 != null) goto L12;
        r3.invalidatePluginSettings(r4);     // Catch: Throwable -> L10
        return;
    L12:
        r3.settings.put(r4, r12);     // Catch: Throwable -> L10
        FileLog.d("Registered settings for plugin " + r4);     // Catch: Throwable -> L10
        r3.notifyPluginSettingsRegistered(r4);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        FileLog.e(th);
        r3.invalidatePluginSettings(r4);
    }

    /* renamed from: $r8$lambda$NrbUy-SgvJM8bTBfeR7YR1Brtxo, reason: not valid java name */
    public static void m1550$r8$lambda$NrbUySgvJM8bTBfeR7YR1Brtxo(AtomicInteger r1, PluginsController r2, Runnable r3) {
        if (r1.incrementAndGet() < Companion.getEngines().size()) goto L8;
        r2.initialized = true;
        if (r3 == null) goto L9;
        r3.run();
        return;
    L9:
        return;
    }

    /* renamed from: $r8$lambda$OFLyrLWoy1BqK-JytUTNBnTZ95Y, reason: not valid java name */
    public static boolean m1551$r8$lambda$OFLyrLWoy1BqKJytUTNBnTZ95Y(MenuItemRecord r0, MenuItemRecord r1) {
        return Intrinsics.areEqual(r1.getItemId(), r0.getItemId());
    }

    public static CopyOnWriteArrayList $r8$lambda$ObuTyILOrv8Lv83Lljknveq0jHI(Function2 r0, Object r1, Object r2) {
        return (CopyOnWriteArrayList) r0.invoke(r1, r2);
    }

    /* renamed from: $r8$lambda$PUoMq-cdhPvnEq59OGyG-EAdPqA, reason: not valid java name */
    public static int m1552$r8$lambda$PUoMqcdhPvnEq59OGyGEAdPqA(Map.Entry r2, Map.Entry r3) {
        int r0 = Intrinsics.compare(((Number) r3.getValue()).intValue(), ((Number) r2.getValue()).intValue());
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return ((String) r2.getKey()).compareTo((String) r3.getKey());
    }

    public static CopyOnWriteArrayList $r8$lambda$PnMDHZ4aRveF9I0VgNHW2f1JUEU(MenuItemRecord r0, String r1, CopyOnWriteArrayList r2) {
        if (r2 != null) goto L4;
        ArrayList r12 = new ArrayList();
    L5:
        r12.add(r0);
        CollectionsKt.sortWith(r12, Comparator.comparingInt(new PluginsController$$ExternalSyntheticLambda35(new PluginsController$$ExternalSyntheticLambda34())).reversed());
        return new CopyOnWriteArrayList(r12);
    L4:
        r12 = new ArrayList(r2);
        CollectionsKt.removeAll(r12, new PluginsController$$ExternalSyntheticLambda33(r0));
        goto L5
    }

    public static Integer $r8$lambda$QHTU7IyTiTCaSj6gAqHjOopsCdM(EventHookRecord r0, String r1, Integer r2) {
        if (r2 != null) goto L6;
        return Integer.valueOf(r0.getPriority());
    L6:
        return Integer.valueOf(Math.max(r2.intValue(), r0.getPriority()));
    }

    /* renamed from: $r8$lambda$RcBK-0orj8QEYVxVVjeONxndSuU, reason: not valid java name */
    public static HookResult m1553$r8$lambda$RcBK0orj8QEYVxVVjeONxndSuU(String r0, int r1, PluginsEngine r2, TLRPC.Updates r3, String r4) {
        return r2.executeUpdatesHook(r0, r1, r3, r4);
    }

    public static Integer $r8$lambda$SCSdz8X41keL06LCq_mlr4PCqFw(Function2 r0, Object r1, Object r2) {
        return (Integer) r0.invoke(r1, r2);
    }

    /* renamed from: $r8$lambda$SzAjDsiXrUe0-Canxc1jh2pTN4A, reason: not valid java name */
    public static int m1554$r8$lambda$SzAjDsiXrUe0Canxc1jh2pTN4A(Function1 r0, Object r1) {
        return ((Number) r0.invoke(r1)).intValue();
    }

    public static void $r8$lambda$TRI271OAbh24WtM0hRmdWUfkDcA() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pluginsUpdated, new Object[0]);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pluginMenuItemsUpdated, new Object[0]);
    }

    public static Integer $r8$lambda$TzwASltnFDcVN1Ktw3tUlrRKJck(Function2 r0, Object r1, Object r2) {
        return (Integer) r0.invoke(r1, r2);
    }

    /* renamed from: $r8$lambda$V9DYDu372WRW1KDk_icvlxw3n-U, reason: not valid java name */
    public static Set m1555$r8$lambda$V9DYDu372WRW1KDk_icvlxw3nU(Function1 r0, Object r1) {
        return (Set) r0.invoke(r1);
    }

    public static List $r8$lambda$WeWrzIubkO0GZO8e1wm7MHFmOjc(String r0) {
        return new ArrayList();
    }

    /* renamed from: $r8$lambda$Wj0eV-55jSQXjcbRqUevINmXVNM, reason: not valid java name */
    public static int m1556$r8$lambda$Wj0eV55jSQXjcbRqUevINmXVNM(Function2 r0, Object r1, Object r2) {
        return ((Number) r0.invoke(r1, r2)).intValue();
    }

    public static List $r8$lambda$YLssiS3cthxbw0lcEiFr_hJJwFI(Function1 r0, Object r1) {
        return (List) r0.invoke(r1);
    }

    public static void $r8$lambda$ZPkFPPTQ3SLZXEDTuCogRsCGwUM(PluginsController r1, boolean r2) {
        if (ExteraConfig.getPluginsEngine() == false) goto L6;
        r1.init(r2, new PluginsController$$ExternalSyntheticLambda26());
        return;
    }

    public static Set $r8$lambda$bFCGo4qve6jYoEhoW_CcYQyIBq8(String r0) {
        return new CopyOnWriteArraySet();
    }

    /* renamed from: $r8$lambda$eTddhbt-eklHVrAGcUqAfEmORgE, reason: not valid java name */
    public static HookResult m1557$r8$lambda$eTddhbteklHVrAGcUqAfEmORgE(String r0, int r1, PluginsEngine r2, TLObject r3, String r4) {
        return r2.executePreRequestHook(r0, r1, r3, r4);
    }

    public static void $r8$lambda$emcHIMWtwJZR4u5gxpb7_ieRy4g(String r2) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pluginSettingsRegistered, new Object[]{r2});
    }

    public static boolean $r8$lambda$htrrOUG8xUM_bIJfiVnNVxY_i18(XC_MethodHook.Unhook r1, HookRecord r2) {
        if ((r2 instanceof XposedHookRecord) == true) goto L5;
        return false;
    L5:
        if (((XposedHookRecord) r2).matches(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    /* renamed from: $r8$lambda$irUSMfwTkkVsshZ-k4DQLFIDV2Q, reason: not valid java name */
    public static boolean m1558$r8$lambda$irUSMfwTkkVsshZk4DQLFIDV2Q(String r1, HookRecord r2) {
        if ((r2 instanceof EventHookRecord) == true) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(((EventHookRecord) r2).getHookName(), r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void $r8$lambda$izbPeygnK0RqsbS2EM_K8BGUT8A(PluginsController r2, Runnable r3) {
        PluginsController$$ExternalSyntheticLambda14 r1 = new PluginsController$$ExternalSyntheticLambda14(new AtomicInteger(0), r2, r3);
        Iterator<PluginsEngine> r22 = Companion.getEngines().values().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().shutdown(r1);
        goto L4
    }

    public static void $r8$lambda$m3KeaGYDcYvARlD1kB8rCf1bUjY() {
        FileLog.d("Plugins engine restarted.");
    }

    static {
        Companion = new Companion(null);
        enginesMap = new ConcurrentHashMap(MapsKt.mapOf(TuplesKt.to(PluginsConstants.PYTHON, new PythonPluginsEngine())));
    }

    private PluginsController() {
        this.plugins = new ConcurrentHashMap();
        this.settings = new ConcurrentHashMap();
        this.menuItemsById = new ConcurrentHashMap();
        this.menuItemsByMenuType = new ConcurrentHashMap();
        this.hooks = new ConcurrentHashMap();
        this.interestedPluginsCache = new ConcurrentHashMap();
        this.fileIconIdsByExtension = new ConcurrentHashMap();
        this.fileIconDrawablesById = new ConcurrentHashMap();
        this.nextFileIconId = new AtomicInteger(101);
        this.substringMatchEventHooksCache = CollectionsKt.emptyList();
        this.exactMatchEventHooksCache = MapsKt.emptyMap();
        this.hooksCacheLock = new Object();
        this.hooksCacheDirty = true;
        this.pluginsDir = new File(ApplicationLoader.getFilesDirFixed(), PluginsConstants.PLUGINS);
        this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("plugin_settings", 0);
        this.watchdog = new PluginsWatchdog(this);
        this.updateNotificationRunnable = new PluginsController$$ExternalSyntheticLambda15();
    }

    public /* synthetic */ PluginsController(DefaultConstructorMarker r1) {
        this();
    }

    public static final /* synthetic */ void access$clearFileIconsInternal(PluginsController r0) {
        r0.clearFileIconsInternal();
    }

    public static final /* synthetic */ ConcurrentHashMap access$getEnginesMap$cp() {
        return enginesMap;
    }

    public static final /* synthetic */ ConcurrentHashMap access$getFileIconDrawablesById$p(PluginsController r0) {
        return r0.fileIconDrawablesById;
    }

    public static final /* synthetic */ int access$getFileIconIdInternal(PluginsController r0, String r1) {
        return r0.getFileIconIdInternal(r1);
    }

    public static final /* synthetic */ int access$registerFileIconInternal(PluginsController r0, String r1, Drawable r2) {
        return r0.registerFileIconInternal(r1, r2);
    }

    public static final /* synthetic */ void access$unregisterFileIconInternal(PluginsController r0, String r1) {
        r0.unregisterFileIconInternal(r1);
    }

    private final void addHook(String r4, HookRecord r5, String r6) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.length() == 0) goto L14;
        if (r5 != null) goto L9;
        return;
    L9:
        if (this.hooks.computeIfAbsent(r4, new PluginsController$$ExternalSyntheticLambda19(new PluginsController$$ExternalSyntheticLambda18())).add(r5) == false) goto L13;
        FileLog.d(r6);
        this.interestedPluginsCache.clear();
        this.hooksCacheDirty = true;
        return;
    L13:
        return;
    }

    @JvmStatic
    public static final void applyArtOpts() {
        Companion.applyArtOpts();
    }

    @JvmStatic
    public static final void clearFileIcons() {
        Companion.clearFileIcons();
    }

    private final void clearFileIconsInternal() {
        this.fileIconIdsByExtension.clear();
        this.fileIconDrawablesById.clear();
    }

    public static /* synthetic */ void clearPluginSettingsPreferences$default(PluginsController r0, String r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = false;
    L5:
        r0.clearPluginSettingsPreferences(r1, r2);
    }

    private final void ensurePreferences() {
        this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("plugin_settings", 0);
    }

    private final <T> T executeGenericHook(String r4, T r5, EngineHookCaller<T> r6) {
        if (Companion.isPluginEngineAvailable() == false) goto L7;
        List<String> r42 = getInterestedPluginIds(r4);
        if (r42.isEmpty() == true) goto L7;
        Iterator<String> r43 = r42.iterator();
    L10:
        if (r43.hasNext() == false) goto L30;
        String r0 = r43.next();
        if (isPluginActive$TMessagesProj(r0) == false) goto L10;
        PluginsEngine r1 = getPluginEngine(r0);
        if (r1 == null) goto L10;
        this.watchdog.onPluginExecutionStarted(r0);
        HookResult<T> r52 = r6.call(r1, r5, r0);     // Catch: Throwable -> L27
        T r12 = r52.getResult();     // Catch: Throwable -> L27
        if (r52.getCancel() == true) goto L19;
        boolean r53 = r52.isFinal();     // Catch: Throwable -> L27
        PluginsWatchdog r2 = this.watchdog;
        if (r53 == true) goto L24;
        r2.onPluginExecutionFinished(r0);
        r5 = r12;
        goto L10
    L24:
        r2.onPluginExecutionFinished(r0);
        return r12;
    L19:
        this.watchdog.onPluginExecutionFinished(r0);
        return null;
    L27:
        th = move-exception;
        this.watchdog.onPluginExecutionFinished(r0);
        throw th;
    L30:
        return r5;
    L7:
        return r5;
    }

    @JvmStatic
    public static final ConcurrentHashMap<String, PluginsEngine> getEngines() {
        return Companion.getEngines();
    }

    @JvmStatic
    public static final int getFileIconId(String r1) {
        return Companion.getFileIconId(r1);
    }

    private final int getFileIconIdInternal(String r9) {
        if (r9 != null) goto L5;
    L21:
        return -1;
    L5:
        if (r9.length() == 0) goto L21;
        if (this.fileIconIdsByExtension.isEmpty() == true) goto L21;
        int r92 = StringsKt.lastIndexOf$default(r9, '.', 0, false, 6, null);
        if (r92 < 0) goto L13;
        String r93 = r9.substring(r92 + 1);
    L14:
        String r94 = normalizeFileExtension(r93);
        if (r94 != null) goto L17;
        return -1;
    L17:
        Integer r8 = this.fileIconIdsByExtension.get(r94);
        if (r8 == null) goto L21;
        return r8.intValue();
    L13:
        r93 = r9;
        goto L14
    }

    @JvmStatic
    public static final PluginsController getInstance() {
        return Companion.getInstance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.StringBuilder] */
    private final List<String> getInterestedPluginIds(String r6) {
        if (r6 == null) goto L46;
        if (r6.length() == 0) goto L46;
        List<String> r0 = this.interestedPluginsCache.get(r6);
        List<String> r02 = r0;
        if (r0 != null) goto L41;
        rebuildHooksCacheIfNeeded();
        HashMap r03 = new HashMap();
        List<EventHookRecord> r1 = this.exactMatchEventHooksCache.get(r6);
        if (r1 == null) goto L17;
        Iterator r12 = r1.iterator();
    L12:
        if (r12.hasNext() == false) goto L17;
        EventHookRecord r2 = (EventHookRecord) r12.next();
        String r3 = r2.getPluginId();
        if (r3 == null) goto L12;
        r03.compute(r3, new PluginsController$$ExternalSyntheticLambda28(new PluginsController$$ExternalSyntheticLambda27(r2)));
    L17:
        Iterator<EventHookRecord> r13 = this.substringMatchEventHooksCache.iterator();
    L19:
        if (r13.hasNext() == false) goto L27;
        EventHookRecord r22 = r13.next();
        if (r22.matches(r6) == false) goto L19;
        String r32 = r22.getPluginId();
        if (r32 == null) goto L19;
        r03.compute(r32, new PluginsController$$ExternalSyntheticLambda30(new PluginsController$$ExternalSyntheticLambda29(r22)));
        goto L19
    L27:
        if (r03.isEmpty() == false) goto L29;
        Collection r04 = Collections.EMPTY_LIST;
    L35:
        this.interestedPluginsCache.put(r6, r04);
        Collection r5 = r04;
        r02 = r04;
        if (r5 == null) goto L41;
        r02 = r04;
        if (r5.isEmpty() == true) goto L41;
        FileLog.d("Calculated and cached potential plugins for '" + r6 + "': " + r04);
        r02 = r04;
        goto L41
    L29:
        ArrayList r14 = new ArrayList(r03.entrySet());
        CollectionsKt.sortWith(r14, new PluginsController$$ExternalSyntheticLambda32(new PluginsController$$ExternalSyntheticLambda31()));
        r04 = new ArrayList();
        Iterator r15 = r14.iterator();
    L31:
        if (r15.hasNext() == false) goto L35;
        Map.Entry r23 = (Map.Entry) r15.next();
        if (isPluginActive$TMessagesProj((String) r23.getKey()) == false) goto L31;
        r04.add(r23.getKey());
    L41:
        if (r02 == null) goto L43;
        return r02;
    L43:
        return Collections.EMPTY_LIST;
    L46:
        return Collections.EMPTY_LIST;
    }

    @JvmStatic
    public static final PluginsEngine getPluginEngine(File r1) {
        return Companion.getPluginEngine(r1);
    }

    @JvmStatic
    public static final Drawable getPluginFileIconDrawable(int r1) {
        return Companion.getPluginFileIconDrawable(r1);
    }

    private final boolean isOnPluginsQueueThread() {
        if (Utilities.pluginsQueue != null) goto L5;
        return false;
    L5:
        if (Utilities.pluginsQueue.getHandler() != null) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(Thread.currentThread(), Utilities.pluginsQueue.getHandler().getLooper().getThread()) == false) goto L13;
        return true;
    L13:
        return false;
    }

    @JvmStatic
    public static final boolean isPlugin(File r1, MessageObject r2) {
        return Companion.isPlugin(r1, r2);
    }

    @JvmStatic
    public static final boolean isPlugin(MessageObject r1) {
        return Companion.isPlugin(r1);
    }

    @JvmStatic
    public static final boolean isPluginEngineAvailable() {
        return Companion.isPluginEngineAvailable();
    }

    @JvmStatic
    public static final boolean isPluginEngineSupported() {
        return Companion.isPluginEngineSupported();
    }

    @JvmStatic
    public static final boolean isPluginFileIcon(int r1) {
        return Companion.isPluginFileIcon(r1);
    }

    @JvmStatic
    public static final boolean isPluginPinned(String r1) {
        return Companion.isPluginPinned(r1);
    }

    private final String normalizeFileExtension(String r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.length() != 0) goto L7;
        return null;
    L7:
        return r1.toLowerCase(Locale.ROOT);
    }

    private final void notifyMenuItemsUpdated() {
        AndroidUtilities.runOnUIThread(new PluginsController$$ExternalSyntheticLambda11());
    }

    private final void notifyPluginSettingsRegistered(String r1) {
        AndroidUtilities.runOnUIThread(new PluginsController$$ExternalSyntheticLambda7(r1));
    }

    private final void notifyPluginSettingsUnregistered(String r1) {
        AndroidUtilities.runOnUIThread(new PluginsController$$ExternalSyntheticLambda24(r1));
    }

    @JvmStatic
    public static final void openPluginSettings(String r1) {
        Companion.openPluginSettings(r1);
    }

    @JvmStatic
    public static final void openPluginSettings(String r1, String r2) {
        Companion.openPluginSettings(r1, r2);
    }

    private final void rebuildHooksCacheIfNeeded() {
        if (this.hooksCacheDirty == true) goto L5;
        return;
    L5:
        Object r0 = this.hooksCacheLock;
        monitor-enter(r0);
    L22:
        th = move-exception;
        throw th;
    L8:
        if (this.hooksCacheDirty == true) goto L11;
        monitor-exit(r0);
        return;
    L11:
        HashMap r1 = new HashMap();     // Catch: Throwable -> L22
        ArrayList r2 = new ArrayList();     // Catch: Throwable -> L22
        Iterator<Set<HookRecord>> r3 = this.hooks.values().iterator();     // Catch: Throwable -> L22
    L13:
        if (r3.hasNext() == false) goto L28;
        Iterator<HookRecord> r4 = r3.next().iterator();     // Catch: Throwable -> L22
    L16:
        if (r4.hasNext() == false) goto L13;
        HookRecord r5 = r4.next();     // Catch: Throwable -> L22
        if ((r5 instanceof EventHookRecord) == false) goto L16;
        if (((EventHookRecord) r5).isMatchSubstring() == true) goto L21;
        String r6 = ((EventHookRecord) r5).getHookName();     // Catch: Throwable -> L22
        if (r6 == null) goto L16;
        ((List) r1.computeIfAbsent(r6, new PluginsController$$ExternalSyntheticLambda21(new PluginsController$$ExternalSyntheticLambda20()))).add(r5);     // Catch: Throwable -> L22
        goto L16
    L21:
        r2.add(r5);     // Catch: Throwable -> L22
        goto L16
    L28:
        this.exactMatchEventHooksCache = r1;     // Catch: Throwable -> L22
        this.substringMatchEventHooksCache = r2;     // Catch: Throwable -> L22
        this.hooksCacheDirty = false;     // Catch: Throwable -> L22
        Unit r9 = Unit.INSTANCE;     // Catch: Throwable -> L22
        monitor-exit(r0);
    }

    @JvmStatic
    public static final int registerFileIcon(String r1, Drawable r2) {
        return Companion.registerFileIcon(r1, r2);
    }

    private final int registerFileIconInternal(String r4, Drawable r5) {
        String r42 = normalizeFileExtension(r4);
        if (r42 == null) goto L15;
        if (r5 == null) goto L17;
        Integer r0 = this.fileIconIdsByExtension.get(r42);
        if (r0 == null) goto L10;
        this.fileIconDrawablesById.put(r0, r5);
        return r0.intValue();
    L10:
        int r02 = this.nextFileIconId.getAndIncrement();
        Integer r43 = this.fileIconIdsByExtension.putIfAbsent(r42, Integer.valueOf(r02));
        if (r43 == null) goto L13;
        r02 = r43.intValue();
    L13:
        this.fileIconDrawablesById.put(Integer.valueOf(r02), r5);
        return r02;
    L17:
        return -1;
    L15:
        return -1;
    }

    private final void removeHook(String r6, Function1<? super HookRecord, Boolean> r7, String r8) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.length() == 0) goto L39;
        Set<HookRecord> r0 = this.hooks.get(r6);
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.isEmpty() == true) goto L40;
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        Iterator<HookRecord> r02 = r0.iterator();
    L13:
        if (r02.hasNext() == false) goto L19;
        HookRecord r3 = r02.next();
        if (r7.invoke(r3).booleanValue() == true) goto L16;
        r1.add(r3);
        goto L13
    L16:
        r2.add(r3);
        goto L13
    L19:
        if (r2.isEmpty() == true) goto L38;
        int r72 = r2.size();
        int r03 = 0;
    L21:
        if (r03 >= r72) goto L23;
        Object r32 = r2.get(r03);
        r03 = r03 + 1;
        ((HookRecord) r32).cleanup();
        goto L21
    L23:
        boolean r73 = r1.isEmpty();
        ConcurrentHashMap<String, Set<HookRecord>> r04 = this.hooks;
        if (r73 == false) goto L26;
        r04.remove(r6);
    L27:
        FileLog.d(r8);
        this.interestedPluginsCache.clear();
        this.hooksCacheDirty = true;
        return;
    L26:
        r04.put(r6, new CopyOnWriteArraySet(r1));
        goto L27
    L38:
        return;
    L40:
        return;
    }

    @JvmStatic
    public static final void runOnPluginsQueue(Runnable r1) {
        Companion.runOnPluginsQueue(r1);
    }

    @JvmStatic
    public static final void setPluginPinned(String r1, boolean r2) {
        Companion.setPluginPinned(r1, r2);
    }

    private final void showInstallDialog(BaseFragment r8, InstallPluginBottomSheet.PluginInstallParams r9) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (AndroidUtilities.isActivityRunning(r8.getParentActivity()) == false) goto L22;
        if (r9 == null) goto L7;
        String r7 = r9.getFilePath();
    L8:
        if (r7 != null) goto L10;
        return;
    L10:
        if (r7.length() == 0) goto L24;
        File r72 = new File(r9.getFilePath());
        if (ExteraConfig.getPluginsEngine() == true) goto L16;
        BulletinFactory.of(r8).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.PluginNotEnabled, new Object[]{r72.getName()}), LocaleController.getString(R.string.Enable), 2750, new PluginsController$$ExternalSyntheticLambda9(r8)).show();
        return;
    L16:
        PluginsEngine r73 = Companion.getPluginEngine(r72);
        if (r73 == null) goto L25;
        r73.showInstallDialog(r8, r9);
        return;
    L25:
        return;
    L24:
        return;
    L7:
        r7 = null;
        goto L8
    }

    @JvmStatic
    public static final void unregisterFileIcon(String r1) {
        Companion.unregisterFileIcon(r1);
    }

    private final void unregisterFileIconInternal(String r2) {
        String r22 = normalizeFileExtension(r2);
        if (r22 == null) goto L10;
        Integer r23 = this.fileIconIdsByExtension.remove(r22);
        if (r23 == null) goto L9;
        this.fileIconDrawablesById.remove(r23);
        return;
    L9:
        return;
    }

    public final void addEventHook(String r2, String r3, boolean r4, int r5) {
        addHook(r2, new EventHookRecord(r2, r3, r4, r5), "Added event hook '" + r3 + "' for plugin " + r2);
    }

    public final String addMenuItem(String r7, PyObject r8) {
        if (Companion.isPluginEngineAvailable() == false) goto L25;
        if (r8 == null) goto L25;
        if (r7 == null) goto L25;
        if (r7.length() == 0) goto L25;
        MenuItemRecord r2 = new MenuItemRecord(r7, r8);     // Catch: Exception -> L26
        String r82 = r2.getMenuType();     // Catch: Exception -> L26
        if (r82 != null) goto L12;
        return null;
    L12:
        MenuItemRecord r4 = this.menuItemsById.get(r2.getItemId());     // Catch: Exception -> L26
        if (r4 != null) goto L15;
    L18:
        if (r4 == null) goto L23;
        CopyOnWriteArrayList<MenuItemRecord> r1 = this.menuItemsByMenuType.get(r4.getMenuType());     // Catch: Exception -> L26
        if (r1 == null) goto L22;
        r1.remove(r4);     // Catch: Exception -> L26
    L22:
        r4.markRemoved();     // Catch: Exception -> L26
    L23:
        this.menuItemsById.put(r2.getItemId(), r2);     // Catch: Exception -> L26
        this.menuItemsByMenuType.compute(r82, new PluginsController$$ExternalSyntheticLambda4(new PluginsController$$ExternalSyntheticLambda3(r2)));     // Catch: Exception -> L26
        FileLog.d("Added menu item: " + r2.getItemId() + " for plugin " + r7 + " in type " + r82);     // Catch: Exception -> L26
        notifyMenuItemsUpdated();     // Catch: Exception -> L26
        return r2.getItemId();
    L15:
        if (Intrinsics.areEqual(r4.getPluginId(), r7) == true) goto L18;
        FileLog.w("Plugin " + r7 + " tried to add a menu item: " + r2.getItemId() + ", which is already used by plugin " + r4.getPluginId());     // Catch: Exception -> L26
        return null;
    L25:
        return null;
    }

    public final void addXposedHook(String r3, XC_MethodHook.Unhook r4) {
        if (r4 == null) goto L4;
        XposedHookRecord r0 = new XposedHookRecord(r4);
    L5:
        addHook(r3, r0, "Added Xposed hook for plugin " + r3);
        return;
    L4:
        r0 = null;
        goto L5
    }

    public final void addXposedHooks(String r2, ArrayList<XC_MethodHook.Unhook> r3) {
        if (r3 == null) goto L8;
        Iterator<XC_MethodHook.Unhook> r32 = r3.iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        addXposedHook(r2, r32.next());
        goto L6
    L10:
        return;
    }

    public final void checkDevServers() {
        Iterator<PluginsEngine> r1 = Companion.getEngines().values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().checkDevServer();
        goto L4
    }

    public final void cleanupPlugin(String r1) {
        removeHooksByPluginId(r1);
        invalidatePluginSettings(r1);
        removeMenuItemsByPluginId(r1);
    }

    @JvmOverloads
    public final void clearPluginSettingsPreferences(String r4) {
        clearPluginSettingsPreferences$default(this, r4, false, 2, null);
    }

    @JvmOverloads
    public final void clearPluginSettingsPreferences(String r3, boolean r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.length() == 0) goto L23;
        PluginsEngine r0 = getPluginEngine(r3);
        if (r0 == null) goto L9;
        r0.clearPluginSettings(r3);
    L13:
        ensurePreferences();
        if (r4 == false) goto L21;
        String r32 = PREF_PLUGIN_ENABLED_KEY_PREFIX + r3;
        if (this.preferences.contains(r32) == false) goto L22;
        SharedPreferences.Editor r2 = this.preferences.edit();
        r2.remove(r32);
        r2.apply();
        return;
    L22:
        return;
    L21:
        return;
    L9:
        Iterator<PluginsEngine> r02 = Companion.getEngines().values().iterator();
    L11:
        if (r02.hasNext() == false) goto L13;
        r02.next().clearPluginSettings(r3);
        goto L11
    }

    public final void deletePlugin(String r3, Utilities.Callback<String> r4) {
        Companion.runOnPluginsQueue(new PluginsController$$ExternalSyntheticLambda36(this, r3, r4));
    }

    public final void executeOnAppEvent(String r2) {
        if (this.initialized == false) goto L12;
        Companion r1 = Companion;
        if (r1.isPluginEngineAvailable() == false) goto L14;
        if (r2 == null) goto L15;
        FileLog.d("Execute scripts on app event ".concat(r2));
        Iterator<PluginsEngine> r12 = r1.getEngines().values().iterator();
    L10:
        if (r12.hasNext() == false) goto L16;
        r12.next().executeOnAppEvent(r2);
        goto L10
    L16:
        return;
    L15:
        return;
    L14:
        return;
    }

    @Override // com.exteragram.messenger.plugins.hooks.PluginsHooks
    public PluginsHooks.PostRequestResult executePostRequestHook(String r3, int r4, TLObject r5, TLRPC.TL_error r6) {
        PluginsHooks.PostRequestResult r2 = (PluginsHooks.PostRequestResult) executeGenericHook(r3, new PluginsHooks.PostRequestResult(r5, r6), new PluginsController$$ExternalSyntheticLambda8(r3, r4));
        if (r2 == null) goto L5;
        return r2;
    L5:
        return new PluginsHooks.PostRequestResult(r5, r6);
    }

    @Override // com.exteragram.messenger.plugins.hooks.PluginsHooks
    public TLObject executePreRequestHook(String r2, int r3, TLObject r4) {
        return (TLObject) executeGenericHook(r2, r4, new PluginsController$$ExternalSyntheticLambda22(r2, r3));
    }

    @Override // com.exteragram.messenger.plugins.hooks.PluginsHooks
    public SendMessagesHelper.SendMessageParams executeSendMessageHook(int r2, SendMessagesHelper.SendMessageParams r3) {
        return (SendMessagesHelper.SendMessageParams) executeGenericHook(PluginsConstants.SEND_MESSAGE_HOOK, r3, new PluginsController$$ExternalSyntheticLambda16(r2));
    }

    @Override // com.exteragram.messenger.plugins.hooks.PluginsHooks
    public TLRPC.Update executeUpdateHook(String r2, int r3, TLRPC.Update r4) {
        return (TLRPC.Update) executeGenericHook(r2, r4, new PluginsController$$ExternalSyntheticLambda13(r2, r3));
    }

    @Override // com.exteragram.messenger.plugins.hooks.PluginsHooks
    public TLRPC.Updates executeUpdatesHook(String r2, int r3, TLRPC.Updates r4) {
        return (TLRPC.Updates) executeGenericHook(r2, r4, new PluginsController$$ExternalSyntheticLambda25(r2, r3));
    }

    public final List<MenuItemRecord> getMenuItemsForLocation(String r1, MenuContextBuilder r2) {
        if (r2 != null) goto L6;
        return getMenuItemsForLocation(r1, new HashMap());
    L6:
        return getMenuItemsForLocation(r1, r2.build());
    }

    public final List<MenuItemRecord> getMenuItemsForLocation(String r5, Map<String, ? extends Object> r6) {
        if (Companion.isPluginEngineAvailable() == false) goto L43;
        if (r5 == null) goto L43;
        if (r5.length() == 0) goto L43;
        LinkedHashSet r0 = new LinkedHashSet();
        CopyOnWriteArrayList<MenuItemRecord> r1 = this.menuItemsByMenuType.get(r5);
        if (r1 == null) goto L15;
        if (r1.isEmpty() == true) goto L15;
        r0.addAll(r1);
    L15:
        if (Intrinsics.areEqual(PluginsConstants.MenuItemTypes.MAIN_MENU, r5) == false) goto L23;
        CopyOnWriteArrayList<MenuItemRecord> r52 = this.menuItemsByMenuType.get(PluginsConstants.MenuItemTypes.DRAWER_MENU);
        if (r52 == null) goto L23;
        if (r52.isEmpty() == true) goto L23;
        r0.addAll(r52);
    L23:
        if (r0.isEmpty() == true) goto L25;
        ArrayList r53 = new ArrayList();
        Iterator r02 = r0.iterator();
    L28:
        if (r02.hasNext() == false) goto L41;
        MenuItemRecord r12 = (MenuItemRecord) r02.next();
        if (isPluginActive$TMessagesProj(r12.getPluginId()) == false) goto L28;
        this.watchdog.onPluginExecutionStarted(r12.getPluginId());
        if (r12.checkCondition(r6) == false) goto L38;
        r53.add(r12);     // Catch: Throwable -> L36
    L38:
        this.watchdog.onPluginExecutionFinished(r12.getPluginId());
    L36:
        th = move-exception;
        this.watchdog.onPluginExecutionFinished(r12.getPluginId());
        throw th;
    L41:
        return r53;
    L25:
        return Collections.EMPTY_LIST;
    L43:
        return Collections.EMPTY_LIST;
    }

    public final PluginsEngine getPluginEngine(String r3) {
        if (r3 != null) goto L5;
    L20:
        return null;
    L5:
        if (r3.length() == 0) goto L20;
        Plugin r2 = this.plugins.get(r3);
        if (r2 != null) goto L10;
        return null;
    L10:
        PluginsEngine r32 = r2.getCachedEngine();
        if (r32 == null) goto L13;
        return r32;
    L13:
        String r33 = r2.getEngine();
        if (r33 != null) goto L16;
        return null;
    L16:
        PluginsEngine r34 = Companion.getEngines().get(r33);
        if (r34 == null) goto L19;
        r2.setCachedEngine(r34);
    L19:
        return r34;
    }

    public final String getPluginPath(String r3) {
        if (r3 != null) goto L5;
    L11:
        return null;
    L5:
        if (r3.length() == 0) goto L11;
        PluginsEngine r2 = getPluginEngine(r3);
        if (r2 == null) goto L11;
        return r2.getPluginPath(r3);
    }

    public final boolean getPluginSettingBoolean(String r2, String r3, boolean r4) {
        if (r2 != null) goto L4;
    L16:
        return r4;
    L4:
        if (r2.length() == 0) goto L16;
        if (r3 == null) goto L16;
        if (r3.length() == 0) goto L16;
        PluginsEngine r1 = getPluginEngine(r2);
        if (r1 == null) goto L16;
        Object r12 = r1.getPluginSetting(r2, r3, Boolean.valueOf(r4));
        if ((r12 instanceof Boolean) == false) goto L16;
        return ((Boolean) r12).booleanValue();
    }

    public final int getPluginSettingInt(String r2, String r3, int r4) {
        if (r2 != null) goto L4;
    L16:
        return r4;
    L4:
        if (r2.length() == 0) goto L16;
        if (r3 == null) goto L16;
        if (r3.length() == 0) goto L16;
        PluginsEngine r1 = getPluginEngine(r2);
        if (r1 == null) goto L16;
        Object r12 = r1.getPluginSetting(r2, r3, Integer.valueOf(r4));
        if ((r12 instanceof Number) == false) goto L16;
        return ((Number) r12).intValue();
    }

    public final String getPluginSettingString(String r2, String r3, String r4) {
        if (r2 != null) goto L4;
    L16:
        return r4;
    L4:
        if (r2.length() == 0) goto L16;
        if (r3 == null) goto L16;
        if (r3.length() == 0) goto L16;
        PluginsEngine r1 = getPluginEngine(r2);
        if (r1 == null) goto L16;
        Object r12 = r1.getPluginSetting(r2, r3, r4);
        if (r12 == null) goto L16;
        return r12.toString();
    }

    public final List<SettingItem> getPluginSettingsList(String r2) {
        if (r2 != null) goto L4;
        return null;
    L4:
        if (r2.length() != 0) goto L7;
        return null;
    L7:
        return this.settings.get(r2);
    }

    public final Map<String, ?> getPluginSettingsPreferences(String r1) {
        PluginsEngine r0 = getPluginEngine(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getAllPluginSettings(r1);
    }

    public final ConcurrentHashMap<String, Plugin> getPlugins() {
        return this.plugins;
    }

    public final File getPluginsDir() {
        return this.pluginsDir;
    }

    public final SharedPreferences getPreferences() {
        return this.preferences;
    }

    public final ConcurrentHashMap<String, List<SettingItem>> getSettings() {
        return this.settings;
    }

    public final PluginsWatchdog getWatchdog() {
        return this.watchdog;
    }

    public final boolean hasPluginSettings(String r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.length() != 0) goto L7;
        return false;
    L7:
        if (this.settings.containsKey(r2) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final boolean hasPluginSettingsPreferences(String r1) {
        Map<String, ?> r0 = getPluginSettingsPreferences(r1);
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
    L10:
        return !r02;
    L5:
        if (r0.isEmpty() == true) goto L8;
        r02 = false;
        goto L10
    }

    public final void init() {
        init(false, null);
    }

    public final void init(Runnable r2) {
        init(false, r2);
    }

    public final void init(boolean r2) {
        init(r2, null);
    }

    public final void init(boolean r10, Runnable r11) {
        if (Companion.isPluginEngineSupported() == true) goto L5;
    L30:
        this.initialized = false;
        if (r11 == null) goto L36;
        r11.run();
        return;
    L36:
        return;
    L5:
        if (ExteraConfig.getPluginsEngine() == false) goto L30;
        NativeCrashHandler.checkAndHandleNativeCrash();
        this.watchdog.start();
        ensurePreferences();
        boolean r3 = this.preferences.getBoolean("had_crash", false);     // Catch: Exception -> L34
        String r5 = this.preferences.getString("crashed_plugin_id", null);     // Catch: Exception -> L34
        if (r5 != null) goto L11;
    L12:
        if (r10 == false) goto L14;
    L13:
        boolean r7 = true;
    L15:
        this.preferences.edit().remove("had_crash").remove("crashed_plugin_id").apply();     // Catch: Exception -> L34
        if (r3 == false) goto L24;
        if (r5 == null) goto L21;
        if (r7 == true) goto L21;
        SharedPreferences.Editor r102 = this.preferences.edit();     // Catch: Exception -> L34
        r102.putBoolean(PREF_PLUGIN_ENABLED_KEY_PREFIX.concat(r5), false);     // Catch: Exception -> L34
        r102.apply();     // Catch: Exception -> L34
    L22:
        if (r7 == true) goto L25;
        AndroidUtilities.runOnUIThread(new PluginsController$$ExternalSyntheticLambda0(), 800);     // Catch: Exception -> L34
    L21:
        SharedPreferences.Editor r103 = ExteraConfig.getEditor();     // Catch: Exception -> L34
        ExteraConfig.setPluginsSafeMode(true);     // Catch: Exception -> L34
        Unit r1 = Unit.INSTANCE;     // Catch: Exception -> L34
        r103.putBoolean("pluginsSafeMode", true).apply();     // Catch: Exception -> L34
        goto L22
    L24:
        SharedPreferences.Editor r12 = ExteraConfig.getEditor();     // Catch: Exception -> L34
        ExteraConfig.setPluginsSafeMode(r10);     // Catch: Exception -> L34
        Unit r2 = Unit.INSTANCE;     // Catch: Exception -> L34
        r12.putBoolean("pluginsSafeMode", r10).apply();     // Catch: Exception -> L34
        goto L25
    L14:
        r7 = false;
        goto L15
    L11:
        if (Intrinsics.areEqual(r5, "manual!") == true) goto L13;
    L25:
        File r104 = new File(ApplicationLoader.getFilesDirFixed(), PluginsConstants.PLUGINS);
        this.pluginsDir = r104;
        if (r104.exists() == true) goto L28;
        SimpliFiles.directory(this.pluginsDir).create();
    L28:
        Companion.runOnPluginsQueue(new PluginsController$$ExternalSyntheticLambda2(new PluginsController$$ExternalSyntheticLambda1(new AtomicInteger(0), this, r11)));
    }

    public final void invalidatePluginSettings(String r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.length() == 0) goto L17;
        List<SettingItem> r0 = this.settings.remove(r3);
        if (r0 == null) goto L16;
        Iterator<SettingItem> r02 = r0.iterator();
    L10:
        if (r02.hasNext() == false) goto L12;
        r02.next().cleanup();
        goto L10
    L12:
        notifyPluginSettingsUnregistered(r3);
        return;
    L16:
        return;
    }

    public final boolean isInitialized() {
        return this.initialized;
    }

    public final boolean isPluginActive$TMessagesProj(Plugin r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (this.plugins.get(r2.getId()) == r2) goto L6;
        return false;
    L6:
        if (r2.isEnabled() == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final boolean isPluginActive$TMessagesProj(String r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.length() == 0) goto L15;
        Plugin r1 = this.plugins.get(r2);
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.isEnabled() != true) goto L14;
        return true;
    L14:
        return false;
    L15:
        return false;
    }

    public final void loadPluginSettings() {
        loadPluginSettings(null);
    }

    public final void loadPluginSettings(String r4) {
        if (r4 != null) goto L4;
    L12:
        Iterator<String> r42 = this.plugins.keySet().iterator();
    L14:
        if (r42.hasNext() == false) goto L20;
        String r0 = r42.next();
        Plugin r1 = this.plugins.get(r0);
        if (isPluginActive$TMessagesProj(r1) == true) goto L17;
        if (r1 == null) goto L14;
        invalidatePluginSettings(r0);
        goto L14
    L17:
        loadPluginSettings(r0);
        goto L14
    L20:
        return;
    L4:
        if (r4.length() == 0) goto L12;
        PluginsController$$ExternalSyntheticLambda12 r02 = new PluginsController$$ExternalSyntheticLambda12(this, r4);
        if (isOnPluginsQueueThread() == false) goto L10;
        r02.run();
        return;
    L10:
        Companion.runOnPluginsQueue(r02);
    }

    public final void notifyPluginsChanged() {
        AndroidUtilities.cancelRunOnUIThread(this.updateNotificationRunnable);
        AndroidUtilities.runOnUIThread(this.updateNotificationRunnable, 150);
    }

    public final void removeEventHook(String r4, String r5) {
        removeHook(r4, new PluginsController$$ExternalSyntheticLambda17(r5), "Removed event hook(s) matching name '" + r5 + "' for plugin " + r4);
    }

    public final void removeHooksByPluginId(String r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.length() == 0) goto L17;
        Set<HookRecord> r0 = this.hooks.remove(r4);
        if (r0 == null) goto L16;
        Iterator<HookRecord> r1 = r0.iterator();
    L10:
        if (r1.hasNext() == false) goto L12;
        r1.next().cleanup();
        goto L10
    L12:
        FileLog.d("Removed all (" + r0.size() + ") hooks for plugin " + r4);
        this.interestedPluginsCache.clear();
        this.hooksCacheDirty = true;
        return;
    L16:
        return;
    }

    public final boolean removeMenuItem(String r4, String r5) {
        if (r5 != null) goto L5;
    L20:
        return false;
    L5:
        if (r5.length() == 0) goto L20;
        MenuItemRecord r1 = this.menuItemsById.remove(r5);
        if (r1 == null) goto L20;
        if (r1.getMenuType() == null) goto L20;
        if (Intrinsics.areEqual(r1.getPluginId(), r4) == true) goto L15;
        this.menuItemsById.put(r5, r1);
        return false;
    L15:
        CopyOnWriteArrayList<MenuItemRecord> r0 = this.menuItemsByMenuType.get(r1.getMenuType());
        if (r0 == null) goto L18;
        r0.remove(r1);
    L18:
        r1.markRemoved();
        FileLog.d("Removed menu item: " + r5 + " for plugin " + r4);
        notifyMenuItemsUpdated();
        return true;
    }

    public final void removeMenuItemsByPluginId(String r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.length() == 0) goto L28;
        ArrayList r0 = new ArrayList();
        Iterator<MenuItemRecord> r1 = this.menuItemsById.values().iterator();
    L8:
        if (r1.hasNext() == false) goto L13;
        MenuItemRecord r2 = r1.next();
        if (Intrinsics.areEqual(r2.getPluginId(), r5) == false) goto L8;
        r0.add(r2.getItemId());
        goto L8
    L13:
        if (r0.isEmpty() == false) goto L15;
        return;
    L15:
        Iterator r02 = r0.iterator();
    L17:
        if (r02.hasNext() == false) goto L19;
        removeMenuItem(r5, (String) r02.next());
        goto L17
    L19:
        FileLog.d("Removed all menu items for plugin: " + r5);
        return;
    }

    public final void removeXposedHook(String r3, XC_MethodHook.Unhook r4) {
        removeHook(r3, new PluginsController$$ExternalSyntheticLambda6(r4), "Removed Xposed hook for plugin " + r3);
    }

    public final void restart() {
        restart(ExteraConfig.getPluginsSafeMode());
    }

    public final void restart(boolean r2) {
        FileLog.d("Restarting plugins engine...");
        shutdown(new PluginsController$$ExternalSyntheticLambda5(this, r2));
    }

    public final void setPluginEnabled(String r3, boolean r4, Utilities.Callback<String> r5) {
        Companion.runOnPluginsQueue(new PluginsController$$ExternalSyntheticLambda10(this, r3, r4, r5));
    }

    public final void setPluginSetting(String r2, String r3, Object r4) {
        setPluginSettingAndTriggerOnChange(r2, r3, r4, null);
    }

    public final void setPluginSettingAndTriggerOnChange(String r2, String r3, Object r4, PyObject r5) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.length() == 0) goto L28;
        if (r3 != null) goto L8;
        return;
    L8:
        if (r3.length() != 0) goto L11;
        return;
    L11:
        if (isPluginActive$TMessagesProj(r2) == false) goto L30;
        PluginsEngine r0 = getPluginEngine(r2);
        if (r0 == null) goto L27;
        r0.setPluginSetting(r2, r3, r4);
        if (r5 != null) goto L23;
    L21:
        loadPluginSettings(r2);
        return;
    L23:
        r5.call(new Object[]{r4});     // Catch: Exception -> L19
    L19:
        e = move-exception;
        FileLog.e("Error executing on_change callback for " + r2 + "/" + r3, e);
        goto L21
    L27:
        return;
    L30:
        return;
    }

    public final void setPluginsDir(File r1) {
        this.pluginsDir = r1;
    }

    public final void setPreferences(SharedPreferences r1) {
        this.preferences = r1;
    }

    public final void showInstallDialog(BaseFragment r2, String r3, boolean r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.length() == 0) goto L9;
        showInstallDialog(r2, new InstallPluginBottomSheet.PluginInstallParams(r3, r4));
        return;
    }

    public final void showInstallDialog(BaseFragment r2, MessageObject r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        showInstallDialog(r2, InstallPluginBottomSheet.PluginInstallParams.Companion.of(r3));
    }

    public final void shutdown(Runnable r3) {
        if (this.initialized == true) goto L7;
        if (r3 == null) goto L9;
        r3.run();
        return;
    L9:
        return;
    L7:
        Companion.runOnPluginsQueue(new PluginsController$$ExternalSyntheticLambda23(this, r3));
    }
}
