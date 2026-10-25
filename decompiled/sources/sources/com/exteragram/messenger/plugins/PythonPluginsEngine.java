package com.exteragram.messenger.plugins;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.FileProvider;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.RegisterSpec;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.PluginsHooks;
import com.exteragram.messenger.plugins.models.CustomSetting;
import com.exteragram.messenger.plugins.models.DividerSetting;
import com.exteragram.messenger.plugins.models.EditTextSetting;
import com.exteragram.messenger.plugins.models.HeaderSetting;
import com.exteragram.messenger.plugins.models.InputSetting;
import com.exteragram.messenger.plugins.models.SelectorSetting;
import com.exteragram.messenger.plugins.models.SettingItem;
import com.exteragram.messenger.plugins.models.SwitchSetting;
import com.exteragram.messenger.plugins.models.TextSetting;
import com.exteragram.messenger.plugins.pip.PipController;
import com.exteragram.messenger.plugins.ui.PluginSettingsActivity;
import com.exteragram.messenger.plugins.ui.components.InstallPluginBottomSheet;
import com.exteragram.messenger.plugins.ui.components.PluginFileViewer;
import com.exteragram.messenger.plugins.ui.components.PythonSdkUpdateAlert;
import com.exteragram.messenger.plugins.utils.PyObjectUtils;
import com.exteragram.messenger.plugins.utils.SdkSignature;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import com.sun.jna.Callback;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.simplifiles.SimpliFiles;
import org.simplifiles.archive.ArchiveExtractionOptions;
import org.simplifiles.archive.ExtractionTargetPolicy;
import org.simplifiles.archive.security.SecurityPolicy;
import org.simplifiles.files.OverwritePolicy;
import org.simplifiles.files.SimpliFile;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u009b\u00012\u00020\u0001:\u0006\u009b\u0001\u009c\u0001\u009d\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u0010 \u001a\u00020\u000f2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u000fH\u0016J\b\u0010&\u001a\u00020\u0007H\u0002J\u0018\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\u000fH\u0002J\b\u0010*\u001a\u00020\u000fH\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J \u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u0006H\u0002J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u001fH\u0016J\b\u00103\u001a\u00020\u001fH\u0002J\b\u00104\u001a\u00020\u001fH\u0002J\u0010\u00105\u001a\u00020\u001f2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00106\u001a\u00020\u001f2\b\u00100\u001a\u0004\u0018\u000101J\u0016\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0006J \u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\b\u0010:\u001a\u0004\u0018\u00010;J*\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010=J\"\u0010>\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u0010?\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J&\u0010@\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u0010A\u001a\u00020\u00062\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00060CH\u0002J\u0010\u0010D\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u0006H\u0002J\b\u0010E\u001a\u00020\u001fH\u0002J\u0018\u0010F\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u0006H\u0002J\b\u0010H\u001a\u00020\u001fH\u0002J\u0018\u0010I\u001a\u00020\u001f2\u0006\u0010J\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u0006H\u0002J*\u0010K\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010?\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J\u000e\u0010L\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u0006J\u001a\u0010M\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\b\u0010N\u001a\u0004\u0018\u00010\"H\u0002J\"\u0010O\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00062\b\u0010N\u001a\u0004\u0018\u00010\"H\u0002J(\u0010P\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u000f2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010RH\u0016J \u0010S\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010RH\u0016J\u0010\u0010T\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0006H\u0016J\b\u0010V\u001a\u00020\u000fH\u0016J\u0010\u0010W\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u0006H\u0016J\u0010\u0010X\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u0006H\u0016J(\u0010Y\u001a\u00020\u001f2\u0006\u00109\u001a\u00020\u00062\b\u0010?\u001a\u0004\u0018\u00010;2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010RJ4\u0010Y\u001a\u00020\u001f2\u0006\u00109\u001a\u00020\u00062\b\u0010?\u001a\u0004\u0018\u00010;2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010R2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0012\u0010Z\u001a\u0004\u0018\u00010\u00062\u0006\u00108\u001a\u00020\u0006H\u0002J\u000e\u0010[\u001a\u00020\\2\u0006\u00109\u001a\u00020\u0006J\u001a\u0010]\u001a\b\u0012\u0004\u0012\u00020_0^2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00070^J\u0018\u0010a\u001a\n\u0012\u0004\u0012\u00020_\u0018\u00010^2\u0006\u0010U\u001a\u00020\u0006H\u0016J\u0010\u0010b\u001a\u00020\u001f2\u0006\u0010c\u001a\u00020\u0006H\u0016J_\u0010d\u001a\b\u0012\u0004\u0012\u0002Hf0e\"\u0004\b\u0000\u0010f2\b\u0010g\u001a\u0004\u0018\u00010\u00072\b\u0010h\u001a\u0004\u0018\u0001Hf2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hf0j2\u0006\u0010k\u001a\u00020\u00062\f\u0010l\u001a\b\u0012\u0004\u0012\u0002Hf0m2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020o0RH\u0002¢\u0006\u0002\u0010pJ]\u0010d\u001a\b\u0012\u0004\u0012\u0002Hf0e\"\u0004\b\u0000\u0010f2\u0006\u00108\u001a\u00020\u00062\b\u0010h\u001a\u0004\u0018\u0001Hf2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hf0j2\u0006\u0010k\u001a\u00020\u00062\f\u0010l\u001a\b\u0012\u0004\u0012\u0002Hf0m2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020o0RH\u0002¢\u0006\u0002\u0010qJ0\u0010r\u001a\b\u0012\u0004\u0012\u00020s0e2\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\b\u0010w\u001a\u0004\u0018\u00010s2\u0006\u00108\u001a\u00020\u0006H\u0016J:\u0010x\u001a\b\u0012\u0004\u0012\u00020y0e2\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\b\u0010z\u001a\u0004\u0018\u00010s2\b\u0010{\u001a\u0004\u0018\u00010|2\b\u0010g\u001a\u0004\u0018\u00010\u0007J:\u0010x\u001a\b\u0012\u0004\u0012\u00020y0e2\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\b\u0010z\u001a\u0004\u0018\u00010s2\b\u0010{\u001a\u0004\u0018\u00010|2\u0006\u00108\u001a\u00020\u0006H\u0016J1\u0010}\u001a\b\u0012\u0004\u0012\u00020~0e2\u0006\u0010\u007f\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010~2\u0006\u00108\u001a\u00020\u0006H\u0016J5\u0010\u0081\u0001\u001a\t\u0012\u0005\u0012\u00030\u0082\u00010e2\u0007\u0010\u0083\u0001\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0082\u00012\u0006\u00108\u001a\u00020\u0006H\u0016J,\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0086\u00010e2\u0006\u0010u\u001a\u00020v2\n\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u00012\u0006\u00108\u001a\u00020\u0006H\u0016J\u001c\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00062\b\u00109\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0089\u0001\u001a\u00020\u0006J\u001e\u0010\u008a\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u008b\u00012\b\u00109\u001a\u0004\u0018\u00010\u0006J'\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u000b2\u0006\u00108\u001a\u00020\u00062\u0007\u0010\u008d\u0001\u001a\u00020\u00062\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u000bH\u0016J%\u0010\u008f\u0001\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0007\u0010\u008d\u0001\u001a\u00020\u00062\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u000bH\u0016J\u0011\u0010\u0091\u0001\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u0006H\u0016J\u001e\u0010\u0092\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u00010\u008b\u00012\u0006\u00108\u001a\u00020\u0006H\u0016J\u001d\u0010\u0093\u0001\u001a\u00020\u001f2\b\u0010\u0094\u0001\u001a\u00030\u0095\u00012\b\u0010\u0087\u0001\u001a\u00030\u0096\u0001H\u0016J\u001b\u0010\u0097\u0001\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u00062\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J\u001c\u0010\u0097\u0001\u001a\u00020\u001f2\u0007\u0010\u0098\u0001\u001a\u00020;2\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J%\u0010\u0099\u0001\u001a\u00020\u001f2\u0007\u0010\u0098\u0001\u001a\u00020;2\u0007\u0010\u009a\u0001\u001a\u00020\u00062\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J$\u0010\u0099\u0001\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0007\u0010\u009a\u0001\u001a\u00020\u00062\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR(\u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\f\u001aB\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000f0\u000f \u000e* \u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006\u009e\u0001"}, d2 = {"Lcom/exteragram/messenger/plugins/PythonPluginsEngine;", "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "<init>", "()V", "pluginInstances", "Ljava/util/concurrent/ConcurrentHashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/chaquo/python/PyObject;", "getPluginInstances", "()Ljava/util/concurrent/ConcurrentHashMap;", "settingsCache", _UrlKt.FRAGMENT_ENCODE_SET, "dependencyPaths", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.PYTHON, "Lcom/chaquo/python/Python;", "devServerClass", "debuggerListener", "getDebuggerListener", "()Lcom/chaquo/python/PyObject;", "setDebuggerListener", "(Lcom/chaquo/python/PyObject;)V", "basePluginClass", "getBasePluginClass", "setBasePluginClass", "getPluginsController", "Lcom/exteragram/messenger/plugins/PluginsController;", "getPython", "initPython", _UrlKt.FRAGMENT_ENCODE_SET, "isPlugin", "file", "Ljava/io/File;", "messageObject", "Lorg/telegram/messenger/MessageObject;", "isEngineAvailable", "requireBasePluginClass", "installSdkArchive", "archiveFile", "fromApk", "initSdk", "stopAndUnloadSdk", "removeModulesRecursive", "sysModules", "prefix", "init", Callback.METHOD_NAME, "Ljava/lang/Runnable;", "checkDevServer", "runDevServer", "stopDevServer", "shutdown", "loadPlugins", "loadPlugin", "pluginId", "filePath", "metadata", "Lcom/exteragram/messenger/plugins/Plugin;", "delegate", "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;", "installPluginDependencies", "pluginMetadata", "disableShadowedPlugins", "dependencyName", "providedModules", _UrlKt.FRAGMENT_ENCODE_SET, "removePluginDependencies", "pruneDependencyPaths", "removeModulesUnderPath", "path", "removePluginPathsFromSysPath", "removeFromSysPath", "sysPath", "createPluginInstance", "unloadPlugin", "refreshImportCaches", "moduleDir", "evictPluginModule", "setPluginEnabled", "enabled", "Lorg/telegram/messenger/Utilities$Callback;", "deletePlugin", "getPluginPath", "id", "canOpenInExternalApp", "openInExternalApp", "sharePlugin", "loadPluginFromFile", "findModuleNameOwner", "validatePluginFromFile", "Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;", "parsePySettingDefinitions", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/models/SettingItem;", "pyDefinitionsList", "loadPluginSettings", "executeOnAppEvent", "eventType", "executeHook", "Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "T", "pluginInstance", "initialValue", "valueClass", "Ljava/lang/Class;", "pyResultKey", "caller", "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;", "errorLogger", _UrlKt.FRAGMENT_ENCODE_SET, "(Lcom/chaquo/python/PyObject;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;", "executePreRequestHook", "Lorg/telegram/tgnet/TLObject;", "requestName", "account", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "executePostRequestHook", "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;", PluginsConstants.RESPONSE, "error", "Lorg/telegram/tgnet/TLRPC$TL_error;", "executeUpdateHook", "Lorg/telegram/tgnet/TLRPC$Update;", "updateName", PluginsConstants.UPDATE, "executeUpdatesHook", "Lorg/telegram/tgnet/TLRPC$Updates;", "containerName", PluginsConstants.UPDATES, "executeSendMessageHook", "Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;", PluginsConstants.PARAMS, "fetchParameterValue", "parameterName", "parsePluginMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "getPluginSetting", PluginsConstants.Settings.KEY, "defaultValue", "setPluginSetting", "value", "clearPluginSettings", "getAllPluginSettings", "showInstallDialog", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;", "openPluginSettings", "plugin", "openPluginSetting", "linkAlias", "Companion", "PyMethodCaller", "Updater", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPythonPluginsEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PythonPluginsEngine.kt\ncom/exteragram/messenger/plugins/PythonPluginsEngine\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,2719:1\n1#2:2720\n41#3,12:2721\n41#3,12:2733\n41#3,12:2751\n41#3,12:2766\n41#3,12:2778\n41#3,12:2790\n41#3,12:2819\n1300#4,2:2745\n1315#4,4:2747\n777#4:2763\n873#4,2:2764\n1586#4:2802\n1661#4,3:2803\n296#4,2:2806\n1586#4:2808\n1661#4,3:2809\n777#4:2812\n873#4,2:2813\n1834#4,4:2815\n37#5,2:2831\n*S KotlinDebug\n*F\n+ 1 PythonPluginsEngine.kt\ncom/exteragram/messenger/plugins/PythonPluginsEngine\n*L\n497#1:2721,12\n734#1:2733,12\n795#1:2751,12\n1045#1:2766,12\n1050#1:2778,12\n1065#1:2790,12\n1231#1:2819,12\n755#1:2745,2\n755#1:2747,4\n817#1:2763\n817#1:2764,2\n1302#1:2802\n1302#1:2803,3\n1303#1:2806,2\n1421#1:2808\n1421#1:2809,3\n1422#1:2812\n1422#1:2813,2\n664#1:2815,4\n2135#1:2831,2\n*E\n"})
/* loaded from: classes.dex */
public final class PythonPluginsEngine implements PluginsController.PluginsEngine {
    public static final Companion Companion = null;
    private static final long MAX_SDK_VERSION_BYTES = 65536;
    private static final String SAFE_MODE_ENABLE_ERROR = "Plugins cannot be enabled while safe mode is active";
    private static final ArchiveExtractionOptions SDK_ARCHIVE_OPTIONS = null;
    private static final SecurityPolicy SDK_ARCHIVE_POLICY = null;
    private static boolean SDK_BETA;
    private static File SDK_DIR;
    private static final String[] SDK_REQUIRED_MODULES = null;
    private static String SDK_VERSION;
    private static final Pattern VERSION_PATTERN = null;
    private static volatile boolean sdkInitialized;
    private volatile PyObject basePluginClass;
    private PyObject debuggerListener;
    private final ConcurrentHashMap.KeySetView<String, Boolean> dependencyPaths;
    private PyObject devServerClass;
    private final ConcurrentHashMap<String, PyObject> pluginInstances;
    private volatile Python python;
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> settingsCache;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\tH\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u001eH\u0002J\u0012\u0010&\u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\u001eH\u0002J\u0014\u0010'\u001a\u0004\u0018\u00010\t2\b\u0010(\u001a\u0004\u0018\u00010\tH\u0002J\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0*2\b\u0010+\u001a\u0004\u0018\u00010\u001eH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "VERSION_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "SDK_REQUIRED_MODULES", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", "SDK_ARCHIVE_POLICY", "Lorg/simplifiles/archive/security/SecurityPolicy;", "SDK_ARCHIVE_OPTIONS", "Lorg/simplifiles/archive/ArchiveExtractionOptions;", "MAX_SDK_VERSION_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "SAFE_MODE_ENABLE_ERROR", "SDK_VERSION", "getSDK_VERSION", "()Ljava/lang/String;", "setSDK_VERSION", "(Ljava/lang/String;)V", "SDK_BETA", _UrlKt.FRAGMENT_ENCODE_SET, "getSDK_BETA", "()Z", "setSDK_BETA", "(Z)V", "SDK_DIR", "Ljava/io/File;", "sdkInitialized", "sdkModuleExists", "sdkDir", "moduleName", "deleteFileIfExists", _UrlKt.FRAGMENT_ENCODE_SET, "file", "isSdkDirValid", "canonicalPathOrNull", "path", "topLevelModuleNames", _UrlKt.FRAGMENT_ENCODE_SET, "dir", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPythonPluginsEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PythonPluginsEngine.kt\ncom/exteragram/messenger/plugins/PythonPluginsEngine$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,2719:1\n1#2:2720\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ String access$canonicalPathOrNull(Companion r0, String r1) {
            return r0.canonicalPathOrNull(r1);
        }

        public static final /* synthetic */ void access$deleteFileIfExists(Companion r0, File r1) {
            r0.deleteFileIfExists(r1);
        }

        public static final /* synthetic */ boolean access$isSdkDirValid(Companion r0, File r1) {
            return r0.isSdkDirValid(r1);
        }

        public static final /* synthetic */ Set access$topLevelModuleNames(Companion r0, File r1) {
            return r0.topLevelModuleNames(r1);
        }

        private final String canonicalPathOrNull(String r2) {
            Object r1 = null;
            if (r2 != null) goto L5;
            return null;
        L5:
            if (r2.length() == 0) goto L20;
            Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L9
            Object r22 = Result.m2018constructorimpl(new File(r2).getCanonicalPath());     // Catch: Throwable -> L9
        L12:
            if (Result.m2024isFailureimpl(r22) == true) goto L16;
            r1 = r22;
        L16:
            return (String) r1;
        L9:
            th = move-exception;
            Result.Companion r02 = Result.Companion;
            r22 = Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L12
        L20:
            return null;
        }

        private final void deleteFileIfExists(File r1) {
            if (r1 != null) goto L4;
            return;
        L4:
            if (r1.exists() == false) goto L18;
            Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L8
            Object r02 = Result.m2018constructorimpl(Boolean.valueOf(SimpliFiles.file(r1).delete()));     // Catch: Throwable -> L8
        L10:
            Throwable r03 = Result.m2021exceptionOrNullimpl(r02);
            if (r03 == null) goto L17;
            FileLog.e(r03);
            return;
        L17:
            return;
        L8:
            th = move-exception;
            Result.Companion r12 = Result.Companion;
            r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L10
        }

        private final boolean isSdkDirValid(File r6) {
            if (r6 != null) goto L5;
        L15:
            return false;
        L5:
            if (r6.isDirectory() == false) goto L15;
            String[] r1 = PythonPluginsEngine.access$getSDK_REQUIRED_MODULES$cp();
            int r2 = r1.length;
            int r3 = 0;
        L8:
            if (r3 >= r2) goto L13;
            if (sdkModuleExists(r6, r1[r3]) == false) goto L11;
            r3 = r3 + 1;
            goto L8
        L11:
            return false;
        L13:
            return true;
        }

        private final boolean sdkModuleExists(File r3, String r4) {
            if (new File(r3, r4 + ".so").exists() == false) goto L5;
            return true;
        L5:
            if (new File(r3, r4 + ".pyc").exists() == true) goto L11;
            return false;
        L11:
            return true;
        }

        private final Set<String> topLevelModuleNames(File r8) {
            if (r8 == null) goto L28;
            File[] r7 = r8.listFiles();
            if (r7 == null) goto L28;
            HashSet r82 = new HashSet();
            int r0 = r7.length;
            int r2 = 0;
        L7:
            if (r2 >= r0) goto L26;
            File r3 = r7[r2];
            String r4 = r3.getName();
            if (r3.isDirectory() == false) goto L19;
            if (Intrinsics.areEqual(r4, "__pycache__") == true) goto L25;
            if (StringsKt.endsWith$default(r4, ".dist-info", false, 2, null) == true) goto L25;
            if (StringsKt.endsWith$default(r4, ".data", false, 2, null) == true) goto L25;
            r82.add(r4);
        L25:
            r2 = r2 + 1;
            goto L7
        L19:
            if (StringsKt.endsWith$default(r4, ".py", false, 2, null) == false) goto L21;
        L24:
            r82.add(StringsKt.substringBefore$default(r4, '.', null, 2, null));
            goto L25
        L21:
            if (StringsKt.endsWith$default(r4, ".pyc", false, 2, null) == true) goto L24;
            if (StringsKt.endsWith$default(r4, ".so", false, 2, null) == false) goto L25;
        L26:
            return r82;
        L28:
            return SetsKt.emptySet();
        }

        public final boolean getSDK_BETA() {
            return PythonPluginsEngine.access$getSDK_BETA$cp();
        }

        public final String getSDK_VERSION() {
            return PythonPluginsEngine.access$getSDK_VERSION$cp();
        }

        public final void setSDK_BETA(boolean r1) {
            PythonPluginsEngine.access$setSDK_BETA$cp(r1);
        }

        public final void setSDK_VERSION(String r1) {
            PythonPluginsEngine.access$setSDK_VERSION$cp(r1);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bâ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J!\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lcom/chaquo/python/PyObject;", "instance", "value", "(Lcom/chaquo/python/PyObject;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface PyMethodCaller<T> {
        PyObject call(PyObject r1, T r2);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Updater {
        public static final Companion Companion = null;
        private static final Pattern PYTHON_SDK_APP_VERSION_CODE_PATTERN = null;
        private static final Pattern PYTHON_SDK_APP_VERSION_PATTERN = null;
        private static int TAG;
        private static volatile boolean isLoading;
        private static long lastCheckUpdateTime;
        private static final Runnable notifyRunnable = null;
        private static boolean notifyWhenChangeStatus;
        private static int status;

        @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001MB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0007J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0007J\b\u0010\u001d\u001a\u00020\u001eH\u0007J\b\u0010\u001f\u001a\u00020\u000bH\u0007J\u0006\u0010 \u001a\u00020\u000bJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000bH\u0007J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u001eH\u0007J\b\u0010*\u001a\u00020%H\u0007J\b\u0010+\u001a\u00020%H\u0007J\b\u0010,\u001a\u00020%H\u0007J\u0010\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020%H\u0007J\b\u0010/\u001a\u00020%H\u0007J\b\u00100\u001a\u00020\"H\u0007J\b\u00101\u001a\u00020\"H\u0007J\b\u00102\u001a\u00020\"H\u0007J\u0010\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020\u000bH\u0007J\b\u00104\u001a\u00020\"H\u0007J\u0010\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020%H\u0002J\u0010\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020\rH\u0002J\u0012\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020<H\u0007J\u0018\u0010=\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020'2\u0006\u0010?\u001a\u00020\u000bH\u0007J\u0018\u0010@\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u00020'H\u0007J\u0018\u0010C\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u00020'H\u0007J\u0018\u0010D\u001a\u00020\"2\u0006\u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020%H\u0007J \u0010G\u001a\u00020\"2\u0006\u0010H\u001a\u00020:2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u000bH\u0002J\u001a\u0010L\u001a\u00020\"2\u0006\u0010H\u001a\u00020:2\b\b\u0002\u0010K\u001a\u00020\u000bH\u0007R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PYTHON_SDK_APP_VERSION_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "PYTHON_SDK_APP_VERSION_CODE_PATTERN", "notifyRunnable", "Ljava/lang/Runnable;", "isLoading", _UrlKt.FRAGMENT_ENCODE_SET, "status", _UrlKt.FRAGMENT_ENCODE_SET, "getStatus", "()I", "setStatus", "(I)V", "notifyWhenChangeStatus", "getNotifyWhenChangeStatus", "()Z", "setNotifyWhenChangeStatus", "(Z)V", "lastCheckUpdateTime", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "getVersion", _UrlKt.FRAGMENT_ENCODE_SET, "getStateString", "sdkFromApk", "Ljava/io/InputStream;", "isSdkFromApk", "isSdkFromCurrentApk", "setBuildFromApk", _UrlKt.FRAGMENT_ENCODE_SET, "fromApk", "sdkFromApkMarker", "Ljava/io/File;", "currentApkStamp", _UrlKt.FRAGMENT_ENCODE_SET, "hashBytes", "inputStream", "getPythonSdkUpdateFile", "getPythonCurrentSdkFile", "getPythonSdkUpdateMetaFile", "isPendingUpdateVerified", "archive", "requestSdkFromApkFile", "deleteSdkUpdateFile", "enableAutoUpdate", "checkUpdates", "force", "restoreSdkFromApk", "touchFile", "file", "updateStatus", "newStatus", "parsePythonSdkUpdateResponse", "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;", "res", "Lorg/telegram/tgnet/TLRPC$messages_Messages;", "isSdkVersionNewer", "remoteVersion", "isBeta", "isAppVersionCompatible", "operator", "targetVersion", "isAppVersionCodeCompatible", "zipFolder", "sourceDir", "zipFile", "copyArchiveToPluginsDirectory", PluginsConstants.UPDATE, "document", "Lorg/telegram/tgnet/TLRPC$Document;", "autoRestartEngine", "savePythonSdkArchive", "PythonSdkUpdateInfo", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u001c\u0010%\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015¨\u0006+"}, d2 = {"Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$PythonSdkUpdateInfo;", "Lorg/telegram/tgnet/TLRPC$TL_help_appUpdate;", "<init>", "()V", "message", "Lorg/telegram/tgnet/TLRPC$Message;", "getMessage", "()Lorg/telegram/tgnet/TLRPC$Message;", "setMessage", "(Lorg/telegram/tgnet/TLRPC$Message;)V", "available", _UrlKt.FRAGMENT_ENCODE_SET, "getAvailable", "()Z", "setAvailable", "(Z)V", "channel", _UrlKt.FRAGMENT_ENCODE_SET, "getChannel", "()Ljava/lang/String;", "setChannel", "(Ljava/lang/String;)V", "appVersionOperator", "getAppVersionOperator", "setAppVersionOperator", "appVersion", "getAppVersion", "setAppVersion", "appVersionCodeOperator", "getAppVersionCodeOperator", "setAppVersionCodeOperator", "appVersionCode", "getAppVersionCode", "setAppVersionCode", "abi", "getAbi", "setAbi", "signature", "getSignature", "setSignature", "clear", _UrlKt.FRAGMENT_ENCODE_SET, "canInstall", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
            /* loaded from: classes4.dex */
            public static final class PythonSdkUpdateInfo extends TLRPC.TL_help_appUpdate {
                private String abi;
                private String appVersion;
                private String appVersionCode;
                private String appVersionCodeOperator;
                private String appVersionOperator;
                private boolean available;
                private String channel;
                private TLRPC.Message message;
                private String signature;

                public PythonSdkUpdateInfo() {
                    clear();
                }

                public final boolean canInstall() {
                    String r0 = this.appVersion;
                    String r1 = this.appVersionOperator;
                    String r2 = this.appVersionCode;
                    String r3 = this.appVersionCodeOperator;
                    String r4 = this.version;
                    if (r0 == null) goto L7;
                    if (r1 == null) goto L7;
                    if (Updater.Companion.isAppVersionCompatible(r1, r0) == true) goto L7;
                L28:
                    return false;
                L7:
                    if (r2 == null) goto L11;
                    if (r3 == null) goto L11;
                    if (Updater.Companion.isAppVersionCodeCompatible(r3, r2) == false) goto L28;
                L11:
                    if (r4 == null) goto L28;
                    if (r4.length() == 0) goto L28;
                    if (Updater.Companion.isSdkVersionNewer(r4, Intrinsics.areEqual(this.channel, "beta")) == false) goto L28;
                    if (this.document == null) goto L28;
                    if (Intrinsics.areEqual(this.abi, Build.SUPPORTED_ABIS[0]) == false) goto L28;
                    String r7 = this.signature;
                    if (r7 == null) goto L28;
                    if (r7.length() == 0) goto L28;
                    return true;
                }

                public final void clear() {
                    this.message = null;
                    this.available = false;
                    this.can_not_skip = false;
                    this.channel = null;
                    this.version = null;
                    this.appVersion = null;
                    this.appVersionOperator = null;
                    this.appVersionCode = null;
                    this.appVersionCodeOperator = null;
                    this.document = null;
                    this.abi = null;
                    this.signature = null;
                }

                public final String getAbi() {
                    return this.abi;
                }

                public final String getAppVersion() {
                    return this.appVersion;
                }

                public final String getAppVersionCode() {
                    return this.appVersionCode;
                }

                public final String getAppVersionCodeOperator() {
                    return this.appVersionCodeOperator;
                }

                public final String getAppVersionOperator() {
                    return this.appVersionOperator;
                }

                public final boolean getAvailable() {
                    return this.available;
                }

                public final String getChannel() {
                    return this.channel;
                }

                public final TLRPC.Message getMessage() {
                    return this.message;
                }

                public final String getSignature() {
                    return this.signature;
                }

                public final void setAbi(String r1) {
                    this.abi = r1;
                }

                public final void setAppVersion(String r1) {
                    this.appVersion = r1;
                }

                public final void setAppVersionCode(String r1) {
                    this.appVersionCode = r1;
                }

                public final void setAppVersionCodeOperator(String r1) {
                    this.appVersionCodeOperator = r1;
                }

                public final void setAppVersionOperator(String r1) {
                    this.appVersionOperator = r1;
                }

                public final void setAvailable(boolean r1) {
                    this.available = r1;
                }

                public final void setChannel(String r1) {
                    this.channel = r1;
                }

                public final void setMessage(TLRPC.Message r1) {
                    this.message = r1;
                }

                public final void setSignature(String r1) {
                    this.signature = r1;
                }
            }

            /* renamed from: $r8$lambda$6ut4Ap5MRyyIRCTm-PYnUBe_pW4, reason: not valid java name */
            public static void m1570$r8$lambda$6ut4Ap5MRyyIRCTmPYnUBe_pW4(Ref.BooleanRef r1, boolean r2) {
                Updater.access$setLoading$cp(false);
                if (r1.element == true) goto L6;
                Updater.Companion.updateStatus(2);
                return;
            L6:
                if (r2 == false) goto L9;
                PluginsController.Companion.getInstance().restart();
                return;
            L9:
                Updater.Companion.updateStatus(4);
            }

            /* renamed from: $r8$lambda$KbqAV-6oc3FrQNFkIEYX01uJMDM, reason: not valid java name */
            public static void m1571$r8$lambda$KbqAV6oc3FrQNFkIEYX01uJMDM(TLRPC.messages_Messages r5, TLRPC.TL_error r6) {
                boolean r1 = false;
                if (r6 != null) goto L24;
                if (r5 == null) goto L25;
                Companion r62 = Updater.Companion;
                PythonSdkUpdateInfo r52 = r62.parsePythonSdkUpdateResponse(r5);
                if (r52 == null) goto L25;
                if (ExteraConfig.getPluginsPySdkAutoUpdate() == true) goto L13;
                BaseFragment r2 = LaunchActivity.getSafeLastFragment();
                if (r2 == null) goto L12;
                AndroidUtilities.runOnUIThread(new PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda2(r2, r52));
            L12:
                boolean r22 = true;
            L14:
                if (r22 == true) goto L23;
                Integer r4 = null;
                savePythonSdkArchive$default(r62, r52, false, 2, null);     // Catch: IOException -> L18
                r1 = true;
            L18:
                e = move-exception;
                String r12 = r52.getChannel();
                TLRPC.Message r53 = r52.getMessage();
                if (r53 == null) goto L22;
                r4 = Integer.valueOf(r53.id);
            L22:
                FileLog.e("Failed to load python-plugins-sdk file (" + r12 + ", message id = " + r4 + ")", e);
            L23:
                r1 = r22;
                goto L25
            L13:
                r22 = false;
            L25:
                if (r1 == true) goto L30;
                Updater.Companion.updateStatus(2);
                return;
            L30:
                return;
            L24:
                FileLog.e("Failed to search messages with sdk updates: " + r6.text);
                goto L25
            }

            /* renamed from: $r8$lambda$Nb4_qHB-J0JtMgKWq7nD16D-hLg, reason: not valid java name */
            public static void m1572$r8$lambda$Nb4_qHBJ0JtMgKWq7nD16DhLg(File r5, PythonSdkUpdateInfo r6, File r7, boolean r8) {
                Ref.BooleanRef r1 = new Ref.BooleanRef();
                Integer r3 = null;
                if (SdkSignature.verify(r5, r6.version, r6.getAbi(), r6.getSignature()) == true) goto L14;
                String r52 = r6.getChannel();
                TLRPC.Message r62 = r6.getMessage();
                if (r62 == null) goto L7;
                r3 = Integer.valueOf(r62.id);
            L7:
                FileLog.e("Rejected Python SDK update with an invalid signature (" + r52 + ", message id = " + r3 + ")");
            L12:
                AndroidUtilities.runOnUIThread(new PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda0(r1, r8));
                return;
            L14:
                SimpliFiles.file(r5).copyTo(r7, OverwritePolicy.REPLACE);     // Catch: Exception -> L10
                SimpliFile.writeTextAtomic$default(SimpliFiles.file(Updater.Companion.getPythonSdkUpdateMetaFile()), r6.version + "|" + r6.getAbi() + "|" + r6.getSignature(), null, 2, null);     // Catch: Exception -> L10
                r1.element = true;     // Catch: Exception -> L10
            L10:
                e = move-exception;
                FileLog.e("Failed to copy plugins-sdk file", e);
                goto L12
            }

            public static void $r8$lambda$VRguszw8oEgH7cLSI7U0KmCyDcA(BaseFragment r1, PythonSdkUpdateInfo r2) {
                r1.showDialog(new PythonSdkUpdateAlert(r1, r2));
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public static final /* synthetic */ void access$copyArchiveToPluginsDirectory(Companion r0, PythonSdkUpdateInfo r1, TLRPC.Document r2, boolean r3) {
                r0.copyArchiveToPluginsDirectory(r1, r2, r3);
            }

            public static final /* synthetic */ void access$updateStatus(Companion r0, int r1) {
                r0.updateStatus(r1);
            }

            private final void copyArchiveToPluginsDirectory(PythonSdkUpdateInfo r3, TLRPC.Document r4, boolean r5) {
                File r2 = getPythonSdkUpdateFile();
                File r42 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r4);
                Utilities.globalQueue.postRunnable(new PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda3(r42, r3, r2, r5));
            }

            private final String currentApkStamp() {
                String r3 = ApplicationLoader.applicationContext.getApplicationInfo().sourceDir;
                return r3 + ":" + new File(r3).lastModified();
            }

            public static /* synthetic */ void savePythonSdkArchive$default(Companion r0, PythonSdkUpdateInfo r1, boolean r2, int r3, Object r4) {
                if ((r3 & 2) == 0) goto L5;
                r2 = false;
            L5:
                r0.savePythonSdkArchive(r1, r2);
            }

            private final File sdkFromApkMarker() {
                return new File(new File(ApplicationLoader.getFilesDirFixed(), Common.ASSET_DIR), ".currentSdkFromApk");
            }

            private final void touchFile(File r1) {
                Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L4
                Object r02 = Result.m2018constructorimpl(SimpliFiles.file(r1).touch());     // Catch: Throwable -> L4
            L6:
                Throwable r03 = Result.m2021exceptionOrNullimpl(r02);
                if (r03 == null) goto L12;
                FileLog.e(r03);
                return;
            L12:
                return;
            L4:
                th = move-exception;
                Result.Companion r12 = Result.Companion;
                r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
                goto L6
            }

            private final void updateStatus(int r3) {
                setStatus(r3);
                if (getNotifyWhenChangeStatus() == false) goto L10;
                AndroidUtilities.cancelRunOnUIThread(Updater.access$getNotifyRunnable$cp());
                Runnable r2 = Updater.access$getNotifyRunnable$cp();
                if (r3 != 1) goto L7;
                long r0 = 0;
            L8:
                AndroidUtilities.runOnUIThread(r2, r0);
                return;
            L7:
                r0 = 600;
                goto L8
            }

            @JvmStatic
            public final void checkUpdates() {
                checkUpdates(false);
            }

            @JvmStatic
            public final void checkUpdates(boolean r9) {
                long r0 = System.currentTimeMillis();
                if (getStatus() != 1) goto L7;
                if (Math.abs(r0 - Updater.access$getLastCheckUpdateTime$cp()) >= 6000) goto L7;
                return;
            L7:
                if (getStatus() > 2) goto L25;
                if (r9 == false) goto L11;
            L20:
                ExteraConfig.setSdkUpdateScheduleTimestamp(r0);
                updateStatus(1);
                Updater.access$setLastCheckUpdateTime$cp(r0);
                RemoteUtils.searchMessages("python_sdk", new TLRPC.TL_inputMessagesFilterDocument(), new PythonPluginsEngine$Updater$Companion$$ExternalSyntheticLambda1(), 3000);
                return;
            L11:
                if (Math.abs(r0 - ExteraConfig.getSdkUpdateScheduleTimestamp()) >= DurationKt.MILLIS_IN_HOUR) goto L13;
                boolean r92 = true;
            L15:
                if (ExteraConfig.getPluginsEngine() == true) goto L17;
                return;
            L17:
                if (ExteraConfig.getPluginsSafeMode() == true) goto L23;
                if (r92 == false) goto L20;
                return;
            L23:
                return;
            L13:
                r92 = false;
                goto L15
            }

            @JvmStatic
            public final void deleteSdkUpdateFile() {
                Companion r0 = PythonPluginsEngine.Companion;
                Companion.access$deleteFileIfExists(r0, getPythonSdkUpdateMetaFile());
                File r1 = getPythonSdkUpdateFile();
                if (r1.exists() == false) goto L6;
                Companion.access$deleteFileIfExists(r0, r1);
                updateStatus(0);
                return;
            }

            @JvmStatic
            public final void enableAutoUpdate() {
                ExteraConfig.setPluginsPySdkAutoUpdate(true);
                if (getNotifyWhenChangeStatus() == false) goto L6;
                Updater.access$getNotifyRunnable$cp().run();
                return;
            }

            public final boolean getNotifyWhenChangeStatus() {
                return Updater.access$getNotifyWhenChangeStatus$cp();
            }

            @JvmStatic
            public final File getPythonCurrentSdkFile() {
                return new File(new File(ApplicationLoader.getFilesDirFixed(), Common.ASSET_DIR), "currentSdk");
            }

            @JvmStatic
            public final File getPythonSdkUpdateFile() {
                return new File(new File(ApplicationLoader.getFilesDirFixed(), Common.ASSET_DIR), "newSdk");
            }

            @JvmStatic
            public final File getPythonSdkUpdateMetaFile() {
                return new File(new File(ApplicationLoader.getFilesDirFixed(), Common.ASSET_DIR), "newSdk.meta");
            }

            @JvmStatic
            public final CharSequence getStateString() {
                int r0 = getStatus();
                if (r0 == 0) goto L23;
                if (r0 == 1) goto L21;
                if (r0 == 2) goto L19;
                if (r0 == 3) goto L17;
                if (r0 == 4) goto L15;
                return null;
            L15:
                return LocaleController.getString(R.string.RestartPluginSystemToApplyUpdate);
            L17:
                return LocaleController.getString(R.string.LoadingUpdate);
            L19:
                return LocaleController.getString(R.string.LatestVersionInstalled) + " (" + getVersion() + ")";
            L21:
                return LocaleController.getString(R.string.CheckingForUpdates);
            L23:
                return getVersion();
            }

            public final int getStatus() {
                return Updater.access$getStatus$cp();
            }

            @JvmStatic
            public final CharSequence getVersion() {
                if (ExteraConfig.getPluginsEngine() == true) goto L5;
            L7:
                String r10 = null;
            L8:
                boolean r1 = PythonPluginsEngine.Companion.getSDK_BETA();
                if (r10 == null) goto L11;
            L15:
                if (r10 == null) goto L21;
                if (r1 == false) goto L18;
                String r0 = "-beta";
            L20:
                return RegisterSpec.PREFIX + r10 + r0;
            L18:
                r0 = _UrlKt.FRAGMENT_ENCODE_SET;
                goto L20
            L21:
                return "SDK not unpacked";
            L11:
                if (PythonPluginsEngine.access$getSDK_DIR$cp() == null) goto L15;
                File r2 = new File(PythonPluginsEngine.access$getSDK_DIR$cp(), "v.txt");
                if (r2.exists() == false) goto L15;
                String r102 = StringsKt.trim(SimpliFile.readText$default(SimpliFiles.file(r2), 65536, null, 2, null)).toString();
                r1 = StringsKt.endsWith$default(r102, "|1", false, 2, null);
                r10 = StringsKt.substringBefore$default(r102, '|', null, 2, null);
                goto L15
            L5:
                if (PythonPluginsEngine.access$getSdkInitialized$cp() == false) goto L7;
                r10 = PythonPluginsEngine.Companion.getSDK_VERSION();
                goto L8
            }

            @JvmStatic
            public final String hashBytes(InputStream r8) {
                MessageDigest r0 = MessageDigest.getInstance("SHA-1");     // Catch: Throwable -> L9
                byte[] r1 = new byte[1048576];     // Catch: Throwable -> L9
            L4:
                int r2 = r8.read(r1);     // Catch: Throwable -> L9
                int r3 = 0;
                if (r2 <= 0) goto L6;
                r0.update(r1, 0, r2);     // Catch: Throwable -> L9
                goto L4
            L6:
                byte[] r02 = r0.digest();     // Catch: Throwable -> L9
                StringBuilder r12 = new StringBuilder(r02.length * 2);     // Catch: Throwable -> L9
                int r22 = r02.length;     // Catch: Throwable -> L9
            L7:
                if (r3 >= r22) goto L11;
                byte r4 = r02[r3];     // Catch: Throwable -> L9
                StringCompanionObject r5 = StringCompanionObject.INSTANCE;     // Catch: Throwable -> L9
                r12.append(String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(r4)}, 1)));     // Catch: Throwable -> L9
                r3 = r3 + 1;     // Catch: Throwable -> L9
                goto L7
            L11:
                String r03 = r12.toString();     // Catch: Throwable -> L9
                CloseableKt.closeFinally(r8, null);     // Catch: NoSuchAlgorithmException -> L14 IOException -> L16
                return r03;
            L9:
                th = move-exception;
                throw th;     // Catch: Throwable -> L21
            L21:
                th = move-exception;
                CloseableKt.closeFinally(r8, th);     // Catch: NoSuchAlgorithmException -> L14 IOException -> L16
                throw th;     // Catch: NoSuchAlgorithmException -> L14 IOException -> L16
            L16:
                e = move-exception;
                HttpUrl$$ExternalSyntheticBUOutline0.m(e);
                return null;
            L14:
                e = move-exception;
                HttpUrl$$ExternalSyntheticBUOutline0.m(e);
                return null;
            }

            @JvmStatic
            public final boolean isAppVersionCodeCompatible(String r1, String r2) {
                return AppUtils.compareVersions(r1, BuildVars.BUILD_VERSION, Integer.parseInt(r2));
            }

            @JvmStatic
            public final boolean isAppVersionCompatible(String r1, String r2) {
                return AppUtils.compareVersions(r1, BuildVars.BUILD_VERSION_STRING, r2);
            }

            @JvmStatic
            public final boolean isPendingUpdateVerified(File r9) {
                File r8 = getPythonSdkUpdateMetaFile();
                if (r8.exists() == true) goto L16;
                FileLog.e("Pending Python SDK update has no signature");
                return false;
            L16:
                List r82 = StringsKt.split$default(StringsKt.trim(SimpliFile.readText$default(SimpliFiles.file(r8), 65536, null, 2, null)).toString(), new String[]{"|"}, false, 0, 6, null);     // Catch: IOException -> L13
                if (r82.size() == 3) goto L12;
                FileLog.e("Malformed signature of a pending Python SDK update");
                return false;
            L12:
                return SdkSignature.verify(r9, (String) r82.get(0), (String) r82.get(1), (String) r82.get(2));
            L13:
                e = move-exception;
                FileLog.e("Failed to read pending Python SDK update signature", e);
                return false;
            }

            @JvmStatic
            public final boolean isSdkFromApk() {
                if (sdkFromApkMarker().exists() == false) goto L5;
                return true;
            L5:
                if (requestSdkFromApkFile().exists() == true) goto L11;
                return false;
            L11:
                return true;
            }

            public final boolean isSdkFromCurrentApk() {
                File r0 = sdkFromApkMarker();
                if (r0.exists() == true) goto L16;
            L14:
                return false;
            L16:
                Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L6
                Object r02 = Result.m2018constructorimpl(SimpliFile.readText$default(SimpliFiles.file(r0), 65536, null, 2, null));     // Catch: Throwable -> L6
            L9:
                if (Result.m2024isFailureimpl(r02) == false) goto L12;
                r02 = null;
            L12:
                if (Intrinsics.areEqual(r02, currentApkStamp()) == false) goto L14;
                return true;
            L6:
                th = move-exception;
                Result.Companion r12 = Result.Companion;
                r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
                goto L9
            }

            @JvmStatic
            public final boolean isSdkVersionNewer(String r1, boolean r2) {
                if (ExteraConfig.getPluginsPySdkBetaVersions() == false) goto L5;
            L8:
                Companion r0 = PythonPluginsEngine.Companion;
                if (r0.getSDK_VERSION() != null) goto L11;
                return false;
            L11:
                return AppUtils.compareVersions(">", r1, r0.getSDK_VERSION());
            L5:
                if (PythonPluginsEngine.Companion.getSDK_BETA() == false) goto L8;
                return !r2;
            }

            @JvmStatic
            public final PythonSdkUpdateInfo parsePythonSdkUpdateResponse(TLRPC.messages_Messages r20) {
                PythonSdkUpdateInfo r0 = new PythonSdkUpdateInfo();
                Iterator<TLRPC.Message> r1 = r20.messages.iterator();
            L4:
                if (r1.hasNext() == false) goto L81;
                TLRPC.Message r2 = r1.next();
                if ((r2 instanceof TLRPC.TL_message) == false) goto L4;
                TLRPC.TL_message r6 = (TLRPC.TL_message) r2;
                if (TextUtils.isEmpty(r6.message) == true) goto L4;
                if ((r6.media instanceof TLRPC.TL_messageMediaDocument) == false) goto L4;
                boolean r7 = StringsKt.contains$default(r6.message, "python_sdk_stable", false, 2, null);
                boolean r8 = StringsKt.contains$default(r6.message, "python_sdk_beta", false, 2, null);
                if (r7 == true) goto L16;
                if (r8 == false) goto L4;
            L16:
                if (r8 == false) goto L20;
                if (ExteraConfig.getPluginsPySdkBetaVersions() == false) goto L4;
            L20:
                StringBuilder r72 = new StringBuilder();
                Iterator r10 = StringsKt.split$default(r6.message, new String[]{"\n"}, false, 0, 6, null).iterator();
                boolean r11 = false;
            L22:
                if (r10.hasNext() == false) goto L71;
                String r13 = StringsKt.trim((String) r10.next()).toString();
                if (TextUtils.isEmpty(r13) == false) goto L28;
                if (r11 == true) goto L22;
            L28:
                if (StringsKt.startsWith$default(r13, "python_sdk_", false, 2, null) == false) goto L33;
                if (r8 == false) goto L31;
                String r112 = "beta";
            L32:
                r0.setChannel(r112);
                r11 = true;
                goto L22
            L31:
                r112 = "stable";
                goto L32
            L33:
                if (r11 == false) goto L34;
                Matcher r12 = Updater.access$getPYTHON_SDK_APP_VERSION_PATTERN$cp().matcher(r13);
                if (r12.matches() == true) goto L37;
                Matcher r122 = Updater.access$getPYTHON_SDK_APP_VERSION_CODE_PATTERN$cp().matcher(r13);
                if (r122.matches() == true) goto L44;
                List r123 = StringsKt.split$default(r13, new String[]{"="}, false, 2, 2, null);
                if (r123.size() != 2) goto L22;
                String r132 = StringsKt.trim((String) r123.get(0)).toString();
                String r124 = StringsKt.trim((String) r123.get(1)).toString();
                switch(r132.hashCode()) {
                    case -1085916422: goto L68;
                    case 96360: goto L64;
                    case 351608024: goto L60;
                    case 1073584312: goto L56;
                    default: goto L22;
                };
            L56:
                if (r132.equals("signature") == false) goto L22;
                r0.setSignature(r124);
                goto L22
            L60:
                if (r132.equals("version") == false) goto L22;
                r0.version = r124;
                goto L22
            L64:
                if (r132.equals("abi") == false) goto L22;
                r0.setAbi(r124);
                goto L22
            L68:
                if (r132.equals("can_not_skip") == false) goto L22;
                r0.can_not_skip = Boolean.parseBoolean(r124);
                goto L22
            L44:
                r0.setAppVersionCodeOperator(r122.group(1));
                String r125 = r122.group(2);
                if (r125 == null) goto L47;
                String r126 = StringsKt.trim(r125).toString();
            L48:
                r0.setAppVersionCode(r126);
                goto L22
            L47:
                r126 = null;
                goto L48
            L37:
                r0.setAppVersionOperator(r12.group(1));
                String r127 = r12.group(2);
                if (r127 == null) goto L40;
                String r128 = StringsKt.trim(r127).toString();
            L41:
                r0.setAppVersion(r128);
                goto L22
            L40:
                r128 = null;
                goto L41
            L34:
                r72.append(r13);
                r72.append("\n");
                goto L22
            L71:
                boolean r14 = true;
                r0.document = r6.media.document;
                if (r0.canInstall() == true) goto L74;
                r0.clear();
                goto L4
            L74:
                r0.text = r72.toString();
                ArrayList<TLRPC.MessageEntity> r15 = new ArrayList();
                Iterator<TLRPC.MessageEntity> r3 = r6.entities.iterator();
            L76:
                if (r3.hasNext() == false) goto L80;
                TLRPC.MessageEntity r62 = r3.next();
                if ((r62 instanceof TLRPC.TL_messageEntityPre) == true) goto L76;
                r15.add(r62);
                goto L76
            L80:
                r0.entities = r15;
                r0.setMessage(r2);
            L83:
                if (r0.getMessage() != null) goto L86;
                return null;
            L86:
                if (r0.document != null) goto L88;
            L90:
                boolean r32 = false;
            L91:
                r0.setAvailable(r32);
                return r0;
            L88:
                if (TextUtils.isEmpty(r0.version) == true) goto L90;
                r32 = r14;
                goto L91
            L81:
                r14 = true;
                goto L83
            }

            @JvmStatic
            public final File requestSdkFromApkFile() {
                return new File(new File(ApplicationLoader.getFilesDirFixed(), Common.ASSET_DIR), ".restoreSdk");
            }

            @JvmStatic
            public final void restoreSdkFromApk() {
                touchFile(requestSdkFromApkFile());
            }

            @JvmStatic
            public final void savePythonSdkArchive(PythonSdkUpdateInfo r7, boolean r8) {
                TLRPC.Message r0 = r7.getMessage();
                TLRPC.Document r1 = r7.document;
                if (Updater.access$isLoading$cp() == true) goto L13;
                if (r0 == null) goto L14;
                if (r1 == null) goto L15;
                MessageObject r2 = new MessageObject(UserConfig.selectedAccount, r0, false, true);
                Updater.access$setLoading$cp(true);
                updateStatus(3);
                if (r2.mediaExists == false) goto L11;
                copyArchiveToPluginsDirectory(r7, r1, r8);
                return;
            L11:
                Updater.access$setTAG$cp(DownloadController.getInstance(UserConfig.selectedAccount).generateObserverTag());
                FileLoader.getInstance(UserConfig.selectedAccount).loadFile(r1, r2, 1, 0);
                DownloadController.getInstance(UserConfig.selectedAccount).addLoadingFileObserver(FileLoader.getAttachFileName(r1), r2, new PythonPluginsEngine$Updater$Companion$savePythonSdkArchive$1(r7, r1, r8));
                return;
            L15:
                return;
            L14:
                return;
            }

            @JvmStatic
            public final InputStream sdkFromApk() {
                return ApplicationLoader.applicationContext.getAssets().open("plugins_pysdk/sdk-" + Build.SUPPORTED_ABIS[0] + ".zip");
            }

            @JvmStatic
            public final void setBuildFromApk(boolean r3) {
                File r0 = sdkFromApkMarker();
                if (r3 == true) goto L14;
                Companion.access$deleteFileIfExists(PythonPluginsEngine.Companion, r0);
                return;
            L14:
                Result.Companion r32 = Result.Companion;     // Catch: Throwable -> L8
                SimpliFile.writeTextAtomic$default(SimpliFiles.file(r0), currentApkStamp(), null, 2, null);     // Catch: Throwable -> L8
                Object r2 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L8
            L10:
                Throwable r22 = Result.m2021exceptionOrNullimpl(r2);
                if (r22 == null) goto L16;
                FileLog.e(r22);
                return;
            L16:
                return;
            L8:
                th = move-exception;
                Result.Companion r33 = Result.Companion;
                r2 = Result.m2018constructorimpl(ResultKt.createFailure(th));
                goto L10
            }

            public final void setNotifyWhenChangeStatus(boolean r1) {
                Updater.access$setNotifyWhenChangeStatus$cp(r1);
            }

            public final void setStatus(int r1) {
                Updater.access$setStatus$cp(r1);
            }

            @JvmStatic
            public final void zipFolder(File r1, File r2) {
                SimpliFiles.directory(r1).zipTo(r2, OverwritePolicy.REPLACE);
            }
        }

        /* renamed from: $r8$lambda$O8ie6jrzLCet2KsdWtRqdEkrc-A, reason: not valid java name */
        public static void m1569$r8$lambda$O8ie6jrzLCet2KsdWtRqdEkrcA() {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pluginsPySdkInfoChanged, new Object[0]);
        }

        static {
            Companion = new Companion(null);
            PYTHON_SDK_APP_VERSION_PATTERN = Pattern.compile("^app_version(>=|<=|==)(.+)$");
            PYTHON_SDK_APP_VERSION_CODE_PATTERN = Pattern.compile("^app_version_code(>=|<=|==)(.+)$");
            notifyRunnable = new PythonPluginsEngine$Updater$$ExternalSyntheticLambda0();
        }

        private Updater() {
        }

        public static final /* synthetic */ long access$getLastCheckUpdateTime$cp() {
            return lastCheckUpdateTime;
        }

        public static final /* synthetic */ Runnable access$getNotifyRunnable$cp() {
            return notifyRunnable;
        }

        public static final /* synthetic */ boolean access$getNotifyWhenChangeStatus$cp() {
            return notifyWhenChangeStatus;
        }

        public static final /* synthetic */ Pattern access$getPYTHON_SDK_APP_VERSION_CODE_PATTERN$cp() {
            return PYTHON_SDK_APP_VERSION_CODE_PATTERN;
        }

        public static final /* synthetic */ Pattern access$getPYTHON_SDK_APP_VERSION_PATTERN$cp() {
            return PYTHON_SDK_APP_VERSION_PATTERN;
        }

        public static final /* synthetic */ int access$getStatus$cp() {
            return status;
        }

        public static final /* synthetic */ int access$getTAG$cp() {
            return TAG;
        }

        public static final /* synthetic */ boolean access$isLoading$cp() {
            return isLoading;
        }

        public static final /* synthetic */ void access$setLastCheckUpdateTime$cp(long r0) {
            lastCheckUpdateTime = r0;
        }

        public static final /* synthetic */ void access$setLoading$cp(boolean r0) {
            isLoading = r0;
        }

        public static final /* synthetic */ void access$setNotifyWhenChangeStatus$cp(boolean r0) {
            notifyWhenChangeStatus = r0;
        }

        public static final /* synthetic */ void access$setStatus$cp(int r0) {
            status = r0;
        }

        public static final /* synthetic */ void access$setTAG$cp(int r0) {
            TAG = r0;
        }

        @JvmStatic
        public static final void checkUpdates() {
            Companion.checkUpdates();
        }

        @JvmStatic
        public static final void checkUpdates(boolean r1) {
            Companion.checkUpdates(r1);
        }

        @JvmStatic
        public static final void deleteSdkUpdateFile() {
            Companion.deleteSdkUpdateFile();
        }

        @JvmStatic
        public static final void enableAutoUpdate() {
            Companion.enableAutoUpdate();
        }

        @JvmStatic
        public static final File getPythonCurrentSdkFile() {
            return Companion.getPythonCurrentSdkFile();
        }

        @JvmStatic
        public static final File getPythonSdkUpdateFile() {
            return Companion.getPythonSdkUpdateFile();
        }

        @JvmStatic
        public static final File getPythonSdkUpdateMetaFile() {
            return Companion.getPythonSdkUpdateMetaFile();
        }

        @JvmStatic
        public static final CharSequence getStateString() {
            return Companion.getStateString();
        }

        @JvmStatic
        public static final CharSequence getVersion() {
            return Companion.getVersion();
        }

        @JvmStatic
        public static final String hashBytes(InputStream r1) {
            return Companion.hashBytes(r1);
        }

        @JvmStatic
        public static final boolean isAppVersionCodeCompatible(String r1, String r2) {
            return Companion.isAppVersionCodeCompatible(r1, r2);
        }

        @JvmStatic
        public static final boolean isAppVersionCompatible(String r1, String r2) {
            return Companion.isAppVersionCompatible(r1, r2);
        }

        @JvmStatic
        public static final boolean isPendingUpdateVerified(File r1) {
            return Companion.isPendingUpdateVerified(r1);
        }

        @JvmStatic
        public static final boolean isSdkFromApk() {
            return Companion.isSdkFromApk();
        }

        @JvmStatic
        public static final boolean isSdkVersionNewer(String r1, boolean r2) {
            return Companion.isSdkVersionNewer(r1, r2);
        }

        @JvmStatic
        public static final Companion.PythonSdkUpdateInfo parsePythonSdkUpdateResponse(TLRPC.messages_Messages r1) {
            return Companion.parsePythonSdkUpdateResponse(r1);
        }

        @JvmStatic
        public static final File requestSdkFromApkFile() {
            return Companion.requestSdkFromApkFile();
        }

        @JvmStatic
        public static final void restoreSdkFromApk() {
            Companion.restoreSdkFromApk();
        }

        @JvmStatic
        public static final void savePythonSdkArchive(Companion.PythonSdkUpdateInfo r1, boolean r2) {
            Companion.savePythonSdkArchive(r1, r2);
        }

        @JvmStatic
        public static final InputStream sdkFromApk() {
            return Companion.sdkFromApk();
        }

        @JvmStatic
        public static final void setBuildFromApk(boolean r1) {
            Companion.setBuildFromApk(r1);
        }

        @JvmStatic
        public static final void zipFolder(File r1, File r2) {
            Companion.zipFolder(r1, r2);
        }
    }

    /* renamed from: $r8$lambda$-0E5o7fqS78X1iCQH_4FWQg9Gbs, reason: not valid java name */
    public static PyObject m1559$r8$lambda$0E5o7fqS78X1iCQH_4FWQg9Gbs(String r0, int r1, PyObject r2, TLRPC.Update r3) {
        return r2.callAttr("on_update_hook", new Object[]{r0, Integer.valueOf(r1), r3});
    }

    public static void $r8$lambda$5rj7AbfcOjfun3xF4Yfzy6NHuw4(String r2, Throwable r3) {
        FileLog.e("Failed to execute on_update_hook for " + r2, r3);
    }

    /* renamed from: $r8$lambda$6O-J6o-SaqdBOobToMF6pnkn4OM, reason: not valid java name */
    public static ConcurrentHashMap m1560$r8$lambda$6OJ6oSaqdBOobToMF6pnkn4OM(Function1 r0, Object r1) {
        return (ConcurrentHashMap) r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$7feSxZkai0u2wy1FkRT-3cJE8Us, reason: not valid java name */
    public static void m1561$r8$lambda$7feSxZkai0u2wy1FkRT3cJE8Us(String r2, Throwable r3) {
        FileLog.e("Failed to execute on_updates_hook for " + r2, r3);
    }

    public static void $r8$lambda$9hTMkDvZ_f5UE5Nq3UiVjEBnuis(PluginsController.PluginValidationResult r0, BaseFragment r1) {
        if (AndroidUtilities.addToClipboard(r0.getError()) == false) goto L6;
        BulletinFactory.of(r1).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    public static void $r8$lambda$AXaNKDWBBrxpVO4xVsgtXiGwau4(BaseFragment r3, Plugin r4) {
        r3.presentFragment(new PluginSettingsActivity(r4, null, 2, null));
    }

    public static void $r8$lambda$CLAasDeXOmfcISlYKM6qtJ2i2Yc(Utilities.Callback r1) {
        r1.run(null);
    }

    public static boolean $r8$lambda$FbLcqOJupzGwHMPwidTBuyt6HPM(File r3, String r4) {
        return StringsKt.endsWith$default(r4.toLowerCase(Locale.ROOT), ".py", false, 2, null);
    }

    public static void $r8$lambda$Huxv3v3qvzipy9seRBzlP2cJyQA(Utilities.Callback r1) {
        r1.run(null);
    }

    /* renamed from: $r8$lambda$HwG9F-Db6KK4BeK6Uwi_5nVHUrA, reason: not valid java name */
    public static void m1562$r8$lambda$HwG9FDb6KK4BeK6Uwi_5nVHUrA(PythonPluginsEngine r18, Plugin r19, String r20, BaseFragment r21) {
        if (r18.getPluginsController().isPluginActive$TMessagesProj(r19) == false) goto L62;
        FileLog.d("Opening plugin setting: " + r19.getId() + "/" + r20);
        if (StringsKt.contains$default(r20, ":", false, 2, null) == true) goto L8;
        PluginSettingsActivity r0 = new PluginSettingsActivity(r19, r20);
    L43:
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda2(r21, r0));
        return;
    L8:
        List<SettingItem> r02 = r18.getPluginsController().getSettings().get(r19.getId());
        if (r02 == null) goto L63;
        String[] r6 = (String[]) StringsKt.split$default(r20, new String[]{":"}, false, 0, 6, null).toArray(new String[0]);
        int r2 = r6.length - 1;
        List<SettingItem> r3 = r02;
        int r03 = 0;
        TextSetting r4 = null;
    L12:
        if (r03 >= r2) goto L40;
        String r5 = r6[r03];
        Iterator<SettingItem> r7 = r3.iterator();
    L15:
        if (r7.hasNext() == false) goto L35;
        SettingItem r11 = r7.next();
        if ((r11 instanceof TextSetting) == false) goto L15;
        if (Intrinsics.areEqual(r5, r11.getLinkAlias()) == false) goto L15;
        r4 = (TextSetting) r11;
        if (r18.getPluginsController().isPluginActive$TMessagesProj(r19) == false) goto L60;
        PyObject r52 = ((TextSetting) r11).getCreateSubFragmentCallback();     // Catch: Exception -> L46
        if (r52 == null) goto L27;
        PyObject r53 = r52.call(new Object[0]);     // Catch: Exception -> L46
    L28:
        if (r53 == null) goto L35;
        r3 = r18.parsePySettingDefinitions(r53.asList());     // Catch: Exception -> L45
        goto L35
    L27:
        r53 = null;
        goto L28
    L60:
        return;
    L35:
        if (r4 != null) goto L39;
        if (r3.isEmpty() == false) goto L39;
        SettingsRegistry.getInstance().onSettingNotFound(r21);
        return;
    L39:
        r03 = r03 + 1;
        goto L12
    L40:
        if (r4 == null) goto L64;
        r0 = new PluginSettingsActivity(r19, r4.getText(), r3, r4.getCreateSubFragmentCallback(), r6[r6.length - 1]).setSettingsLinkPrefix(ArraysKt.joinToString$default(Arrays.copyOf(r6, r6.length - 1), ":", null, null, 0, null, null, 62, null));
        goto L43
    L64:
        return;
    L63:
        return;
    }

    public static void $r8$lambda$JSPzY6YTUQGoel99NiGV2zLLX4Q(Utilities.Callback r0, Throwable r1) {
        r0.run(AppUtils.stackTraceToString(r1));
    }

    /* renamed from: $r8$lambda$NGT_LVDpZf-x1FtJ6x4iJPoreg0, reason: not valid java name */
    public static void m1563$r8$lambda$NGT_LVDpZfx1FtJ6x4iJPoreg0(Plugin r16, PythonPluginsEngine r17, String r18, PipController.InstallerDelegate r19, Utilities.Callback r20) {
        File r6 = null;
        boolean r7 = false;
        if (r16 == null) goto L87;
        Plugin r0 = r16;
    L18:
        String r8 = r0.getId();     // Catch: Throwable -> L11
        boolean r9 = r17.getPluginsController().getPreferences().getBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r8, false);     // Catch: Throwable -> L43
        File r10 = new File(r17.getPluginsController().getPluginsDir(), r8 + ".py");     // Catch: Throwable -> L41
        boolean r11 = r10.exists();     // Catch: Throwable -> L38
        if (r11 == false) goto L31;
        r17.unloadPlugin(r8);     // Catch: Throwable -> L28
        File r12 = new File(r17.getPluginsController().getPluginsDir(), r8 + ".py.bak");     // Catch: Throwable -> L28
        Companion.access$deleteFileIfExists(Companion, r12);     // Catch: Throwable -> L26
        SimpliFiles.file(r10).moveTo(r12, OverwritePolicy.ERROR);     // Catch: Throwable -> L26
        r17.removePluginDependencies(r8);     // Catch: Throwable -> L26
        r6 = r12;
    L26:
        th = th;
    L14:
        Throwable r62 = th;
        FileLog.e("Unexpected error loading plugin from file: " + r18, r62);
        if (r8 == null) goto L77;
        Companion.access$deleteFileIfExists(Companion, r10);
        if (r11 == false) goto L67;
        if (r12 == null) goto L67;
        if (r12.exists() == false) goto L67;
        if (r10 == null) goto L67;
        SimpliFiles.file(r12).moveTo(r10, OverwritePolicy.REPLACE);     // Catch: Exception -> L65
        SharedPreferences.Editor r02 = r17.getPluginsController().getPreferences().edit();     // Catch: Exception -> L62
        r02.putBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r8, r9);     // Catch: Exception -> L62
        r02.apply();     // Catch: Exception -> L62
        r17.loadPlugin(r8, r10.getAbsolutePath());     // Catch: Exception -> L57
        Unit r03 = Unit.INSTANCE;     // Catch: Exception -> L57
    L64:
        r7 = true;
        goto L67
    L57:
        e = move-exception;
        FileLog.e("Failed to reload original plugin after update failure for " + r8, e);     // Catch: Exception -> L62
        Plugin r4 = r17.getPluginsController().getPlugins().get(r8);     // Catch: Exception -> L62
        if (r4 == null) goto L64;
        r4.setError(e);     // Catch: Exception -> L62
        Unit r04 = Unit.INSTANCE;     // Catch: Exception -> L62
    L62:
        e = e;
        r7 = true;
    L66:
        FileLog.e("Failed to restore backup for plugin " + r8, e);
    L65:
        e = e;
    L67:
        if (r7 == true) goto L77;
        r17.getPluginsController().cleanupPlugin(r8);
        r17.removePluginDependencies(r8);     // Catch: Exception -> L71
    L73:
        PyObject r05 = r17.pluginInstances.remove(r8);
        if (r05 == null) goto L76;
        r05.close();
    L76:
        r17.getPluginsController().clearPluginSettingsPreferences(r8, true);
        r17.getPluginsController().getPlugins().remove(r8);
        Companion.access$deleteFileIfExists(Companion, r10);
    L71:
        e = move-exception;
        FileLog.e(e);
    L77:
        r17.getPluginsController().notifyPluginsChanged();
        if (r20 == null) goto L99;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda19(r20, r62));
        return;
    L99:
        return;
    L28:
        th = th;
        r12 = null;
    L31:
        if (new File(r18).exists() == false) goto L37;
        SimpliFiles.file(r18).copyTo(r10, OverwritePolicy.REPLACE);     // Catch: Throwable -> L28
        r17.loadPlugin(r8, r10.getAbsolutePath(), r0, r19);     // Catch: Throwable -> L28
        Companion.access$deleteFileIfExists(Companion, r6);     // Catch: Throwable -> L28
        r17.getPluginsController().notifyPluginsChanged();     // Catch: Throwable -> L28
        if (r20 == null) goto L98;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda18(r20));     // Catch: Throwable -> L28
        return;
    L98:
        return;
    L37:
        throw new Exception("Plugin file is no longer available. It may have been moved or deleted.");     // Catch: Throwable -> L28
    L38:
        th = th;
        r12 = null;
    L40:
        r11 = false;
    L41:
        th = th;
        r10 = null;
        r12 = null;
    L43:
        th = th;
        r10 = null;
    L13:
        r12 = r10;
        r9 = false;
        r11 = false;
        goto L14
    L87:
        PluginsController.PluginValidationResult r06 = r17.validatePluginFromFile(r18);     // Catch: Throwable -> L11
        if (r06.getError() != null) goto L16;
        r0 = r06.getPlugin();     // Catch: Throwable -> L11
        if (r0 != null) goto L18;
        throw new IllegalArgumentException("Required value was null.");     // Catch: Throwable -> L11
    L16:
        throw new Exception(r06.getError());     // Catch: Throwable -> L11
    L11:
        th = th;
        r8 = null;
        r10 = null;
        goto L13
    }

    /* renamed from: $r8$lambda$R9eA3kAiq8LxF0pb-ZLXEwqc7Mw, reason: not valid java name */
    public static void m1564$r8$lambda$R9eA3kAiq8LxF0pbZLXEwqc7Mw(BaseFragment r4, String r5, PluginsController.PluginValidationResult r6) {
        BulletinFactory.of(r4).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.PluginInstallError, new Object[]{r5}), LocaleUtils.createCopySpan(r4), new PythonPluginsEngine$$ExternalSyntheticLambda7(r6, r4)).show();
    }

    /* renamed from: $r8$lambda$ViM9rtYG_B9-ookEK5EXHR-GFio, reason: not valid java name */
    public static PyObject m1565$r8$lambda$ViM9rtYG_B9ookEK5EXHRGFio(String r0, int r1, PyObject r2, TLObject r3) {
        return r2.callAttr("pre_request_hook", new Object[]{r0, Integer.valueOf(r1), r3});
    }

    public static void $r8$lambda$XvNtpK1Osqm6XpkwzFDoVJPVJ9E(Utilities.Callback r1) {
        r1.run(null);
    }

    public static void $r8$lambda$Yu6Vo0jC7uBHzV8I4HTCUhN5Nfk(Utilities.Callback r0, Throwable r1) {
        r0.run(AppUtils.stackTraceToString(r1));
    }

    public static void $r8$lambda$ZHZbkOYG7xmxl2kV0ImdQjtKwC4(PythonPluginsEngine r10, Runnable r11) {
        Python r0 = r10.getPython();
        if (r0 != null) goto L61;
        if (r11 == null) goto L81;
        AndroidUtilities.runOnUIThread(r11);
        return;
    L81:
        return;
    L61:
        PyObject r02 = (PyObject) r0.getModule("sys").get("path");     // Catch: PyException -> L14
        String r1 = r10.getPluginsController().getPluginsDir().getAbsolutePath();     // Catch: PyException -> L14
        if (ExteraConfig.getPluginsSafeMode() == true) goto L16;
        if (r02 == null) goto L16;
        if (r02.callAttr("__contains__", new Object[]{r1}).toBoolean() == true) goto L16;
        r02.callAttr("append", new Object[]{r1});     // Catch: PyException -> L14
    L16:
        File[] r03 = r10.getPluginsController().getPluginsDir().listFiles(new PythonPluginsEngine$$ExternalSyntheticLambda6());
        if (r03 != null) goto L22;
        r10.getPluginsController().notifyPluginsChanged();
        if (r11 == null) goto L82;
        AndroidUtilities.runOnUIThread(r11);
        return;
    L82:
        return;
    L22:
        Iterator r04 = ArrayIteratorKt.iterator(r03);
    L23:
        int r2 = 0;
        if (r04.hasNext() == false) goto L43;
        File r12 = (File) r04.next();
        String r3 = r12.getName().substring(0, r12.getName().length() - 3);
        Plugin r4 = null;
        PluginsController.PluginValidationResult r5 = r10.validatePluginFromFile(r12.getAbsolutePath());     // Catch: Throwable -> L34
    L30:
        th = th;
    L36:
        FileLog.e("Failed to load plugin " + r12.getName() + ". Reason: " + th.getMessage(), th);
        if (r5 == null) goto L39;
        r4 = r5.getPlugin();
    L39:
        if (r4 == null) goto L41;
        Plugin r13 = new Plugin(r3, r4.getName());
        r13.setAuthor(r4.getAuthor());
        r13.setDescription(r4.getDescription());
        r13.setIcon(r4.getIcon());
        r13.setVersion(r4.getVersion());
        r13.setAppVersion(r4.getAppVersion());
        r13.setSdkVersion(r4.getSdkVersion());
        r13.setRequirements(r4.getRequirements());
        r13.setEngine(r4.getEngine());
    L42:
        r13.setError(th);
        r13.setEnabled(false);
        r10.getPluginsController().getPlugins().put(r3, r13);
        goto L23
    L41:
        r13 = new Plugin(r3, r3);
        r13.setAuthor(LocaleController.getString(R.string.PluginNoAuthor));
        r13.setVersion("1.0");
        r13.setEngine(PluginsConstants.PYTHON);
        goto L42
    L28:
        if (r5.getError() == null) goto L29;
        throw new Exception(r5.getError());     // Catch: Throwable -> L30
    L29:
        r10.loadPlugin(r3, r12.getAbsolutePath(), r5.getPlugin());     // Catch: Throwable -> L30
    L34:
        th = th;
        r5 = null;
        goto L36
    L43:
        r10.getPluginsController().notifyPluginsChanged();
        Collection<Plugin> r05 = r10.getPluginsController().getPlugins().values();
        if (r05.isEmpty() == true) goto L54;
        Iterator<T> r06 = r05.iterator();
    L48:
        if (r06.hasNext() == false) goto L54;
        if (((Plugin) r06.next()).isEnabled() == false) goto L48;
        r2 = r2 + 1;
        if (r2 >= 0) goto L48;
        CollectionsKt.throwCountOverflow();
    L54:
        FileLog.d("Python plugin system initialized. Total: " + r10.getPluginsController().getPlugins().size() + ", Enabled: " + r2);
        if (r11 == null) goto L83;
        AndroidUtilities.runOnUIThread(r11);
        return;
    L83:
        return;
    L14:
        e = move-exception;
        FileLog.e("Failed to setup Python environment for plugins", e);
        if (r11 == null) goto L85;
        AndroidUtilities.runOnUIThread(r11);
        return;
    }

    public static ConcurrentHashMap $r8$lambda$b_SWPjLcoxAUmuhmOu3Q7ftR5Fo(String r0) {
        return new ConcurrentHashMap();
    }

    /* renamed from: $r8$lambda$cwkMqqP-Ork5Iq87zec6AWD3pb0, reason: not valid java name */
    public static PyObject m1566$r8$lambda$cwkMqqPOrk5Iq87zec6AWD3pb0(int r0, PyObject r1, SendMessagesHelper.SendMessageParams r2) {
        return r1.callAttr("on_send_message_hook", new Object[]{Integer.valueOf(r0), r2});
    }

    public static void $r8$lambda$eeXz2PiC7JLWvOZ286cT7LVckQE(String r2, Throwable r3) {
        FileLog.e("Failed to execute on_send_message_hook for " + r2, r3);
    }

    public static void $r8$lambda$kKVWL1eL2TCAIGWICttRb9fqocc(Utilities.Callback r1) {
        r1.run(SAFE_MODE_ENABLE_ERROR);
    }

    public static ConcurrentHashMap $r8$lambda$nvRtZjLfjaRHKshQM4MzuvmjaU8(Function1 r0, Object r1) {
        return (ConcurrentHashMap) r0.invoke(r1);
    }

    public static PyObject $r8$lambda$oSwq0KS9WeQUq_sw29d6K3u_EmA(String r0, int r1, PyObject r2, TLRPC.Updates r3) {
        return r2.callAttr("on_updates_hook", new Object[]{r0, Integer.valueOf(r1), r3});
    }

    /* renamed from: $r8$lambda$ojQv3mpXW-y1kA7sYxb1icPtjFU, reason: not valid java name */
    public static void m1567$r8$lambda$ojQv3mpXWy1kA7sYxb1icPtjFU(BaseFragment r0, PluginSettingsActivity r1) {
        r0.presentFragment(r1);
    }

    /* renamed from: $r8$lambda$w3FOi-q-YECzQmx8rtkflkZAy88, reason: not valid java name */
    public static void m1568$r8$lambda$w3FOiqYECzQmx8rtkflkZAy88(String r2, String r3, Throwable r4) {
        FileLog.e("Failed to execute pre_request_hook in " + r2 + " for " + r3, r4);
    }

    public static ConcurrentHashMap $r8$lambda$z4QgCtHpe2floOOtUUiTr6P9Yi8(String r0) {
        return new ConcurrentHashMap();
    }

    static {
        Companion = new Companion(null);
        VERSION_PATTERN = Pattern.compile("^(>=|<=|==|>|<)(.+)$");
        SDK_REQUIRED_MODULES = new String[]{"_sdk_version", "base_plugin", "plugin_settings"};
        SDK_ARCHIVE_POLICY = SecurityPolicy.Companion.builder().maxEntries(100000).maxTotalUncompressedSize(2147483648L).maxSingleFileSize(536870912).maxCompressionRatio(500.0d).build();
        SDK_ARCHIVE_OPTIONS = ArchiveExtractionOptions.Companion.builder().targetPolicy(ExtractionTargetPolicy.REPLACE).build();
    }

    public PythonPluginsEngine() {
        this.pluginInstances = new ConcurrentHashMap();
        this.settingsCache = new ConcurrentHashMap();
        this.dependencyPaths = ConcurrentHashMap.newKeySet();
    }

    public static final /* synthetic */ boolean access$getSDK_BETA$cp() {
        return SDK_BETA;
    }

    public static final /* synthetic */ File access$getSDK_DIR$cp() {
        return SDK_DIR;
    }

    public static final /* synthetic */ String[] access$getSDK_REQUIRED_MODULES$cp() {
        return SDK_REQUIRED_MODULES;
    }

    public static final /* synthetic */ String access$getSDK_VERSION$cp() {
        return SDK_VERSION;
    }

    public static final /* synthetic */ boolean access$getSdkInitialized$cp() {
        return sdkInitialized;
    }

    public static final /* synthetic */ void access$setSDK_BETA$cp(boolean r0) {
        SDK_BETA = r0;
    }

    public static final /* synthetic */ void access$setSDK_VERSION$cp(String r0) {
        SDK_VERSION = r0;
    }

    private final void createPluginInstance(String r6, String r7, Plugin r8, PipController.InstallerDelegate r9) {
        PyObject r1 = requireBasePluginClass();
        installPluginDependencies(r6, r8, r9);
        refreshImportCaches(r6, new File(r7).getParentFile());
        getPluginsController().getWatchdog().onPluginExecutionStarted(r6);
        Python r92 = getPython();     // Catch: Throwable -> L24 PyException -> L26
        if (r92 == null) goto L29;
        PyObject r93 = r92.getModule(r6);     // Catch: Throwable -> L24 PyException -> L26
        if (r93 == null) goto L29;
        getPluginsController().getWatchdog().onPluginExecutionFinished(r6);
        PyObject r0 = (PyObject) r93.get("__file__");
        if (r0 == null) goto L10;
        String r02 = r0.toString();
    L11:
        Companion r3 = Companion;
        if (Intrinsics.areEqual(Companion.access$canonicalPathOrNull(r3, r02), Companion.access$canonicalPathOrNull(r3, r7)) == false) goto L14;
        PyObject r72 = r1.callAttr("_findPluginClass", new Object[]{r93});
        if (r72 == null) goto L23;
        PyObject r73 = r72.call(new Object[0]);
        r73.put("id", r8.getId());
        r73.put("name", r8.getName());
        r73.put(MediaTrack.ROLE_DESCRIPTION, r8.getDescription());
        r73.put("author", r8.getAuthor());
        r73.put("version", r8.getVersion());
        r73.put("icon", r8.getIcon());
        r73.put("app_version", r8.getAppVersion());
        r73.put("sdk_version", r8.getSdkVersion());
        r73.put(Common.ASSET_REQUIREMENTS, r8.getRequirements());
        Boolean r82 = Boolean.FALSE;
        r73.put("enabled", r82);
        r73.put("initialized", r82);
        r73.put("error_message", null);
        this.pluginInstances.put(r6, r73);
        return;
    L23:
        throw new Exception("Could not find a class inheriting from BasePlugin in " + r6 + ".py. Make sure your main plugin class extends BasePlugin.");
    L14:
        if (r02 != null) goto L17;
        r02 = "built into the runtime";
    L17:
        throw new Exception("Plugin id '" + r6 + "' is already taken by the Python module " + r02 + ". Change '__id__' to a name that no other module uses.");
    L10:
        r02 = null;
    L29:
        throw new Exception("Failed to import plugin module: " + r6);     // Catch: Throwable -> L24 PyException -> L26
    L26:
        e = move-exception;
        throw new Exception("Failed to import plugin module: " + e.getMessage(), e);     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        getPluginsController().getWatchdog().onPluginExecutionFinished(r6);
        throw th;
    }

    private final void disableShadowedPlugins(String r6, String r7, Set<String> r8) {
        Iterator<String> r82 = r8.iterator();
    L4:
        if (r82.hasNext() == false) goto L13;
        String r0 = r82.next();
        if (Intrinsics.areEqual(r0, r6) == true) goto L4;
        if (new File(getPluginsController().getPluginsDir(), r0 + ".py").exists() == false) goto L4;
        FileLog.w("Disabling plugin '" + r0 + "': dependency " + r7 + " of " + r6 + " now owns that module name");
        unloadPlugin(r0);
        SharedPreferences.Editor r1 = getPluginsController().getPreferences().edit();
        StringBuilder r2 = new StringBuilder(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX);
        r2.append(r0);
        r1.putBoolean(r2.toString(), false);
        r1.apply();
        Plugin r12 = getPluginsController().getPlugins().get(r0);
        if (r12 == null) goto L4;
        r12.setError(new Exception("Plugin id '" + r0 + "' is now provided by " + r7 + ", a dependency of '" + r6 + "'. Rename or delete one of them."));
        goto L4
    }

    private final void evictPluginModule(PyObject r6, String r7, File r8) {
        PyObject r5 = r6.callAttr("get", new Object[]{r7});
        if (r5 != null) goto L5;
        return;
    L5:
        PyObject r52 = (PyObject) r5.get("__file__");
        if (r52 == null) goto L8;
        String r53 = r52.toString();
    L9:
        if (r8 == null) goto L11;
        String r82 = Companion.access$canonicalPathOrNull(Companion, new File(r8, r7 + ".py").getAbsolutePath());
    L12:
        if (r82 != null) goto L14;
    L18:
        if (r53 != null) goto L20;
        r53 = "the Python runtime";
    L20:
        FileLog.w("Keeping module '" + r7 + "' loaded: it belongs to " + r53 + ", not to a plugin");
        return;
    L14:
        if (Intrinsics.areEqual(Companion.access$canonicalPathOrNull(Companion, r53), r82) == false) goto L18;
        r6.callAttr("pop", new Object[]{r7, null});
        return;
    L11:
        r82 = null;
        goto L12
    L8:
        r53 = null;
        goto L9
    }

    private final <T> PluginsController.HookResult<T> executeHook(PyObject r6, T r7, Class<T> r8, String r9, PyMethodCaller<T> r10, Utilities.Callback<Throwable> r11) {
        if (r6 == null) goto L39;
        PyObject r62 = r10.call(r6, r7);     // Catch: Throwable -> L13
        if (r62 == null) goto L39;
        String r102 = PyObjectUtils.getString(r62, PluginsConstants.STRATEGY, PluginsConstants.Strategy.DEFAULT);     // Catch: Throwable -> L15
        if (r102 != null) goto L9;
    L17:
        if (r102 != null) goto L20;
    L34:
        PyObjectUtils.closeSafely(r62);     // Catch: Throwable -> L13
        goto L39
    L20:
        if (StringsKt.endsWith$default(r102, PluginsConstants.Strategy.MODIFY, false, 2, null) == false) goto L22;
    L23:
        PyObject r92 = (PyObject) r62.get(r9);     // Catch: Throwable -> L15
        if (r92 == null) goto L30;
        r7 = (T) r92.toJava(r8);
    L27:
        e = move-exception;
        r11.run(e);     // Catch: Throwable -> L15
    L30:
        if (StringsKt.endsWith$default(r102, PluginsConstants.Strategy.MODIFY_FINAL, false, 2, null) == false) goto L34;
        PluginsController.HookResult<T> r82 = new PluginsController.HookResult(r7, false, true);     // Catch: Throwable -> L15
        PyObjectUtils.closeSafely(r62);     // Catch: Throwable -> L13
        return r82;
    L22:
        if (StringsKt.endsWith$default(r102, PluginsConstants.Strategy.MODIFY_FINAL, false, 2, null) == false) goto L34;
    L9:
        if (StringsKt.endsWith$default(r102, PluginsConstants.Strategy.CANCEL, false, 2, null) == false) goto L17;
        PluginsController.HookResult<T> r83 = new PluginsController.HookResult(null, true, false);     // Catch: Throwable -> L15
        PyObjectUtils.closeSafely(r62);     // Catch: Throwable -> L13
        return r83;
    L15:
        th = move-exception;
        PyObjectUtils.closeSafely(r62);     // Catch: Throwable -> L13
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        r11.run(th);
    L39:
        return new PluginsController.HookResult(r7, false, false);
    }

    private final <T> PluginsController.HookResult<T> executeHook(String r8, T r9, Class<T> r10, String r11, PyMethodCaller<T> r12, Utilities.Callback<Throwable> r13) {
        return executeHook(this.pluginInstances.get(r8), r9, r10, r11, r12, r13);
    }

    private final String findModuleNameOwner(String r9) {
        if (Companion.access$topLevelModuleNames(Companion, SDK_DIR).contains(r9) == false) goto L6;
        return "the plugins SDK";
    L6:
        Iterator<String> r1 = PipController.INSTANCE.activeLibraryPaths().iterator();
    L8:
        if (r1.hasNext() == false) goto L13;
        File r3 = new File(r1.next());
        if (Companion.access$topLevelModuleNames(Companion, r3).contains(r9) == false) goto L8;
        return "the installed dependency " + r3.getName();
    L13:
        Python r12 = getPython();
        if (r12 != null) goto L50;
        return null;
    L50:
        PyObject r32 = r12.getModule("importlib.util").callAttr("find_spec", new Object[]{r9});     // Catch: Throwable -> L22 PyException -> L24
        if (r32 != null) goto L19;
        return null;
    L19:
        PyObject r4 = (PyObject) r32.get("origin");     // Catch: Throwable -> L22 PyException -> L24
        if (r4 == null) goto L26;
        String r42 = r4.toString();     // Catch: Throwable -> L22 PyException -> L24
    L27:
        if (r42 == null) goto L32;
        Companion r13 = Companion;     // Catch: Throwable -> L22 PyException -> L24
        if (Intrinsics.areEqual(Companion.access$canonicalPathOrNull(r13, r42), Companion.access$canonicalPathOrNull(r13, new File(getPluginsController().getPluginsDir(), r9 + ".py").getAbsolutePath())) == false) goto L31;
        return null;
    L31:
        return r42;
    L32:
        PyObject r33 = (PyObject) r32.get("submodule_search_locations");     // Catch: Throwable -> L22 PyException -> L24
        if (r33 != null) goto L35;
        return "another Python module";
    L35:
        String r8 = Companion.access$canonicalPathOrNull(Companion, new File(getPluginsController().getPluginsDir(), r9).getAbsolutePath());     // Catch: Throwable -> L22 PyException -> L24
        List<PyObject> r14 = r12.getBuiltins().callAttr("list", new Object[]{r33}).asList();     // Catch: Throwable -> L22 PyException -> L24
        ArrayList r34 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r14, 10));     // Catch: Throwable -> L22 PyException -> L24
        Iterator<T> r15 = r14.iterator();     // Catch: Throwable -> L22 PyException -> L24
    L37:
        if (r15.hasNext() == false) goto L39;
        r34.add(((PyObject) r15.next()).toString());     // Catch: Throwable -> L22 PyException -> L24
        goto L37
    L39:
        int r16 = r34.size();     // Catch: Throwable -> L22 PyException -> L24
        int r43 = 0;
    L40:
        if (r43 >= r16) goto L44;
        Object r5 = r34.get(r43);     // Catch: Throwable -> L22 PyException -> L24
        r43 = r43 + 1;     // Catch: Throwable -> L22 PyException -> L24
        if (Intrinsics.areEqual(Companion.access$canonicalPathOrNull(Companion, (String) r5), r8) == true) goto L40;
    L46:
        return (String) r5;
    L44:
        r5 = null;
        goto L46
    L26:
        r42 = null;
    L24:
        e = move-exception;
        FileLog.w("Treating plugin id '" + r9 + "' as taken: " + e.getMessage());
        return "another Python module";
    L22:
        th = move-exception;
        FileLog.e("Failed to check whether plugin id '" + r9 + "' is free", th);
        return null;
    }

    private final PluginsController getPluginsController() {
        return PluginsController.Companion.getInstance();
    }

    private final synchronized Python getPython() {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L4:
        if (this.python != null) goto L13;
        initPython();     // Catch: Throwable -> L11
        if (this.python != null) goto L13;
        FileLog.e("Python initialization failed, unable to proceed.");     // Catch: Throwable -> L11
        monitor-exit(this);
        return null;
    L13:
        Python r0 = this.python;     // Catch: Throwable -> L11
        monitor-exit(this);
        return r0;
    }

    private final void initPython() {
    L7:
        e = move-exception;
        FileLog.e("Failed to initialize Python", e);
        return;
    L3:
        if (Python.isStarted() == true) goto L5;
        Python.start(new AndroidPlatform(ApplicationLoader.applicationContext));     // Catch: Exception -> L7
    L5:
        this.python = Python.getInstance();     // Catch: Exception -> L7
    }

    private final boolean initSdk() {
        sdkInitialized = false;
        Python r2 = this.python;
        if (r2 != null) goto L6;
        return false;
    L6:
        if (SDK_DIR != null) goto L14;
        File r0 = new File(new File(ApplicationLoader.getFilesDirFixed(), Common.ASSET_DIR), PluginsConstants.PLUGINS_SDK);
        SDK_DIR = r0;
        if (r0.exists() == true) goto L14;
        File r02 = SDK_DIR;
        if (r02 == null) goto L12;
        SimpliFiles.directory(r02).create();
        goto L14
    L12:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    L14:
        File r4 = SDK_DIR;
        if (r4 == null) goto L132;
        Updater.Companion r03 = Updater.Companion;
        File r3 = r03.requestSdkFromApkFile();
        File r5 = r03.getPythonSdkUpdateFile();
        File r6 = r03.getPythonCurrentSdkFile();
        boolean r7 = r3.exists();
        if (r7 == false) goto L19;
        SimpliFiles.directory(r4).clean();
        Companion.access$deleteFileIfExists(Companion, r3);
    L19:
        boolean r32 = true;
        if (r7 == false) goto L22;
    L30:
        File r04 = new File(r4, "v.txt");
        if (r04.exists() == false) goto L72;
        String r05 = StringsKt.trim(SimpliFile.readText$default(SimpliFiles.file(r04), 65536, null, 2, null)).toString();
        boolean r52 = StringsKt.endsWith$default(r05, "|1", false, 2, null);
        String r06 = StringsKt.substringBefore$default(r05, '|', null, 2, null);
        InputStream r9 = ApplicationLoader.applicationContext.getAssets().open("plugins_pysdk/v.txt");     // Catch: IOException -> L56
        BufferedReader r10 = new BufferedReader(new InputStreamReader(r9, StandardCharsets.UTF_8));     // Catch: Throwable -> L58
        StringBuilder r11 = new StringBuilder();     // Catch: Throwable -> L43
    L36:
        String r12 = r10.readLine();     // Catch: Throwable -> L43
        if (r12 == null) goto L38;
        r11.append(r12);     // Catch: Throwable -> L43
        r11.append('\n');     // Catch: Throwable -> L43
        goto L36
    L38:
        String r112 = StringsKt.trim(r11.toString()).toString();     // Catch: Throwable -> L43
        if (r52 == true) goto L146;
    L47:
        String r53 = ">";
    L48:
        if (AppUtils.compareVersions(r53, r112, r06) == false) goto L52;
    L50:
        r7 = true;
    L52:
        Unit r07 = Unit.INSTANCE;     // Catch: Throwable -> L43
        CloseableKt.closeFinally(r10, null);     // Catch: Throwable -> L58
        CloseableKt.closeFinally(r9, null);     // Catch: IOException -> L56
        goto L72
    L45:
        e = move-exception;
        FileLog.e("Invalid Python SDK version file. Restoring SDK from APK.", e);     // Catch: Throwable -> L43
        goto L50
    L146:
        if (Updater.Companion.isSdkFromCurrentApk() == true) goto L47;
        r53 = ">=";
    L43:
        th = move-exception;
        throw th;     // Catch: Throwable -> L63
    L63:
        th = move-exception;
        CloseableKt.closeFinally(r10, th);     // Catch: Throwable -> L58
        throw th;     // Catch: Throwable -> L58
    L58:
        th = move-exception;
        throw th;     // Catch: Throwable -> L67
    L67:
        th = move-exception;
        CloseableKt.closeFinally(r9, th);     // Catch: IOException -> L56
        throw th;     // Catch: IOException -> L56
    L56:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Failed to read Python SDK version (v.txt) from APK assets", e);
        return false;
    L72:
        if (r7 == false) goto L74;
    L75:
        if (r7 == true) goto L77;
        FileLog.w("Python SDK directory is missing required files. Restoring SDK from APK.");
    L77:
        SimpliFiles.directory(r4).clean();
        InputStream r102 = Updater.Companion.sdkFromApk();     // Catch: IOException -> L122
        SimpliFile.writeFromAtomic$default(SimpliFiles.file(r6), r102, 0, 2, null);     // Catch: Throwable -> L124
        CloseableKt.closeFinally(r102, null);     // Catch: IOException -> L122
        installSdkArchive(r6, true);     // Catch: IOException -> L122
    L81:
        Updater.Companion.deleteSdkUpdateFile();
        PyObject r08 = (PyObject) r2.getModule("sys").get("path");
        if (r08 == null) goto L137;
        r08.callAttr("append", new Object[]{r4.getAbsolutePath()});
    L137:
        PyObject r09 = r2.getModule("_sdk_version");     // Catch: Throwable -> L90
        PyObject r22 = r09.callAttr("__start__", new Object[0]);     // Catch: Throwable -> L90
        if (r22 != null) goto L87;
    L89:
        r32 = false;
    L92:
        sdkInitialized = r32;     // Catch: Throwable -> L90
        PyObject r23 = (PyObject) r09.get("__version__");     // Catch: Throwable -> L90
        if (r23 == null) goto L96;
        String r24 = (String) r23.toJava(String.class);     // Catch: Throwable -> L90
        if (r24 == null) goto L96;
    L97:
        SDK_VERSION = r24;     // Catch: Throwable -> L90
        PyObject r010 = (PyObject) r09.get("__beta__");     // Catch: Throwable -> L90
        if (r010 == null) goto L102;
        Boolean r011 = (Boolean) r010.toJava(Boolean.TYPE);     // Catch: Throwable -> L90
        if (r011 == null) goto L102;
        boolean r012 = r011.booleanValue();     // Catch: Throwable -> L90
    L103:
        SDK_BETA = r012;     // Catch: Throwable -> L90
        if (this.basePluginClass != null) goto L115;
        if (sdkInitialized == false) goto L115;
        if (ExteraConfig.getPluginsSafeMode() == true) goto L115;
        requireBasePluginClass();     // Catch: Exception -> L112
    L112:
        e = move-exception;
        FileLog.e("Failed to load BasePlugin class", e);
    L115:
        return sdkInitialized;
    L102:
        r012 = SDK_BETA;     // Catch: Throwable -> L90
    L96:
        r24 = SDK_VERSION;     // Catch: Throwable -> L90
        goto L97
    L87:
        if (r22.toBoolean() != true) goto L89;
    L90:
        th = move-exception;
        FileLog.e("Failed to initialize Python SDK bootstrap", th);
        Updater.Companion.restoreSdkFromApk();     // Catch: Throwable -> L119
    L121:
        return false;
    L119:
        th = move-exception;
        FileLog.e("Failed to schedule Python SDK restore from APK", th);
        goto L121
    L124:
        th = move-exception;
        throw th;     // Catch: Throwable -> L127
    L127:
        th = move-exception;
        CloseableKt.closeFinally(r102, th);     // Catch: IOException -> L122
        throw th;     // Catch: IOException -> L122
    L122:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Failed to install Python SDK from APK", e);
        return false;
    L74:
        if (Companion.access$isSdkDirValid(Companion, r4) == true) goto L81;
    L22:
        if (r5.exists() == false) goto L30;
        if (r03.isPendingUpdateVerified(r5) == true) goto L154;
        FileLog.e("Discarding Python SDK update that failed signature verification");
        r03.deleteSdkUpdateFile();
        goto L30
    L154:
        SimpliFiles.file(r5).copyTo(r6, OverwritePolicy.REPLACE);     // Catch: IOException -> L28
        installSdkArchive(r6, false);     // Catch: IOException -> L28
    L28:
        e = move-exception;
        FileLog.e("Failed to install updated Python SDK archive", e);
        r7 = true;
        goto L30
    L132:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    }

    private final void installPluginDependencies(String r18, Plugin r19, PipController.InstallerDelegate r20) {
        List<String> r2 = r19.getRequirements();
        List<String> r3 = r2;
        if (r3 != null) goto L5;
        return;
    L5:
        if (r3.isEmpty() == true) goto L42;
        List<String> r22 = PipController.INSTANCE.installDependencies(r2, r18, r20);
        List<String> r32 = r22;
        LinkedHashMap r4 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r32, 10)), 16));
        Iterator<T> r33 = r32.iterator();
    L9:
        if (r33.hasNext() == false) goto L11;
        Object r5 = r33.next();
        r4.put(r5, Companion.access$topLevelModuleNames(Companion, new File((String) r5)));
        goto L9
    L11:
        Set r34 = Companion.access$topLevelModuleNames(Companion, SDK_DIR);
        Iterator r52 = r4.entrySet().iterator();
    L13:
        if (r52.hasNext() == false) goto L19;
        Map.Entry r6 = (Map.Entry) r52.next();
        String r7 = (String) r6.getKey();
        Set r8 = CollectionsKt.intersect((Set) r6.getValue(), r34);
        if (r8.isEmpty() == true) goto L13;
        throw new Exception("Dependency " + new File(r7).getName() + " of " + r18 + " provides " + CollectionsKt.joinToString$default(r8, null, null, null, 0, null, null, 63, null) + ", which would replace the plugins SDK module of the same name.");
    L19:
        Iterator r35 = r4.entrySet().iterator();
    L21:
        if (r35.hasNext() == false) goto L23;
        Map.Entry r42 = (Map.Entry) r35.next();
        String r53 = (String) r42.getKey();
        disableShadowedPlugins(r18, new File(r53).getName(), (Set) r42.getValue());
        goto L21
    L23:
        Python r1 = getPython();
        if (r1 == null) goto L28;
        PyObject r12 = r1.getModule("sys");
        if (r12 == null) goto L28;
        PyObject r13 = (PyObject) r12.get("path");
    L29:
        if (r13 == null) goto L41;
        int r36 = r22.size();
    L31:
        r36 = r36 - 1;
        if ((-1) >= r36) goto L43;
        String r43 = r22.get(r36);
        removeFromSysPath(r13, r43);
        r13.callAttr("insert", new Object[]{0, r43});
        this.dependencyPaths.add(r43);
        goto L31
    L43:
        return;
    L41:
        return;
    L28:
        r13 = null;
        goto L29
    }

    private final void installSdkArchive(File r4, boolean r5) {
        File r3 = SDK_DIR;
        if (r3 == null) goto L38;
        File r0 = new File(r3.getParentFile(), "plugins-sdk.tmp");
        SimpliFiles.archive(r4).withPolicy(SDK_ARCHIVE_POLICY).extractToDirectory(r0, SDK_ARCHIVE_OPTIONS);     // Catch: Throwable -> L21
        if (Companion.access$isSdkDirValid(Companion, r0) == false) goto L24;
        SimpliFiles.directory(r0).moveTo(r3, OverwritePolicy.REPLACE);     // Catch: Throwable -> L21
        Updater.Companion.setBuildFromApk(r5);     // Catch: Throwable -> L21
        if (r0.exists() == false) goto L18;
        Result.Companion r42 = Result.Companion;     // Catch: Throwable -> L12
        Object r43 = Result.m2018constructorimpl(Boolean.valueOf(SimpliFiles.directory(r0).deleteRecursively()));     // Catch: Throwable -> L12
    L14:
        Throwable r44 = Result.m2021exceptionOrNullimpl(r43);
        if (r44 == null) goto L18;
        FileLog.e(r44);
    L12:
        th = move-exception;
        Result.Companion r52 = Result.Companion;
        r43 = Result.m2018constructorimpl(ResultKt.createFailure(th));
    L18:
        if (r3.exists() == true) goto L45;
        SimpliFiles.directory(r3).create();
        return;
    L45:
        return;
    L24:
        throw new IOException("Python SDK archive unpacked with missing required files");     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        if (r0.exists() == false) goto L35;
        Result.Companion r53 = Result.Companion;     // Catch: Throwable -> L29
        Object r54 = Result.m2018constructorimpl(Boolean.valueOf(SimpliFiles.directory(r0).deleteRecursively()));     // Catch: Throwable -> L29
    L31:
        Throwable r55 = Result.m2021exceptionOrNullimpl(r54);
        if (r55 == null) goto L35;
        FileLog.e(r55);
    L29:
        th = move-exception;
        Result.Companion r02 = Result.Companion;
        r54 = Result.m2018constructorimpl(ResultKt.createFailure(th));
    L35:
        if (r3.exists() == true) goto L37;
        SimpliFiles.directory(r3).create();
    L37:
        throw th;
    L38:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
    }

    private final void pruneDependencyPaths() {
        if (this.dependencyPaths.isEmpty() == true) goto L58;
        Set<String> r0 = PipController.INSTANCE.activeLibraryPaths();
        ConcurrentHashMap.KeySetView<String, Boolean> r1 = this.dependencyPaths;
        ArrayList r2 = new ArrayList();
        Iterator<T> r12 = r1.iterator();
    L7:
        if (r12.hasNext() == false) goto L12;
        Object r3 = r12.next();
        if (r0.contains((String) r3) == true) goto L7;
        r2.add(r3);
        goto L7
    L12:
        if (r2.isEmpty() == true) goto L59;
        Python r02 = this.python;     // Catch: PyException -> L43
        if (r02 == null) goto L17;
        PyObject r03 = r02.getModule("sys");     // Catch: PyException -> L43
    L18:
        if (r03 == null) goto L20;
        PyObject r32 = (PyObject) r03.get("path");     // Catch: PyException -> L43
    L21:
        if (r03 == null) goto L23;
        PyObject r4 = (PyObject) r03.get("modules");     // Catch: PyException -> L43
    L24:
        if (r03 == null) goto L26;
        PyObject r04 = (PyObject) r03.get("path_importer_cache");     // Catch: PyException -> L43
    L27:
        int r5 = r2.size();     // Catch: PyException -> L43
        int r7 = 0;
    L28:
        if (r7 >= r5) goto L37;
        Object r8 = r2.get(r7);     // Catch: PyException -> L43
        r7 = r7 + 1;     // Catch: PyException -> L43
        String r82 = (String) r8;     // Catch: PyException -> L43
        if (r32 == null) goto L32;
        removeFromSysPath(r32, r82);     // Catch: PyException -> L43
    L32:
        if (r04 == null) goto L34;
        r04.callAttr("pop", new Object[]{r82, null});     // Catch: PyException -> L43
    L34:
        if (r4 == null) goto L36;
        removeModulesUnderPath(r4, r82);     // Catch: PyException -> L43
    L36:
        this.dependencyPaths.remove(r82);     // Catch: PyException -> L43
        goto L28
    L37:
        Python r11 = this.python;     // Catch: PyException -> L43
        if (r11 == null) goto L56;
        PyObject r112 = r11.getModule("importlib");     // Catch: PyException -> L43
        if (r112 == null) goto L57;
        r112.callAttr("invalidate_caches", new Object[0]);     // Catch: PyException -> L43
        return;
    L57:
        return;
    L56:
        return;
    L26:
        r04 = null;
        goto L27
    L23:
        r4 = null;
        goto L24
    L20:
        r32 = null;
        goto L21
    L17:
        r03 = null;
    L43:
        e = move-exception;
        FileLog.e("Failed to drop unused dependency paths from the Python environment", e);
        return;
    L59:
        return;
    }

    private final void refreshImportCaches(String r4, File r5) {
        Python r0 = this.python;
        if (r0 != null) goto L18;
        return;
    L18:
        PyObject r1 = r0.getModule("sys");     // Catch: PyException -> L8
        PyObject r2 = (PyObject) r1.get("modules");     // Catch: PyException -> L8
        if (r2 == null) goto L10;
        evictPluginModule(r2, r4, r5);     // Catch: PyException -> L8
    L10:
        PyObject r3 = (PyObject) r1.get("path_importer_cache");     // Catch: PyException -> L8
        if (r5 == null) goto L14;
        if (r3 == null) goto L14;
        r3.callAttr("pop", new Object[]{r5.getAbsolutePath(), null});     // Catch: PyException -> L8
    L14:
        r0.getModule("importlib").callAttr("invalidate_caches", new Object[0]);     // Catch: PyException -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e("Failed to refresh import caches for " + r4, e);
    }

    private final void removeFromSysPath(PyObject r2, String r3) {
    L3:
        if (r2.callAttr("__contains__", new Object[]{r3}).toBoolean() == false) goto L5;
        r2.callAttr("remove", new Object[]{r3});
        goto L3
    }

    private final void removeModulesRecursive(PyObject r7, File r8, String r9) {
        if (Intrinsics.areEqual(r9, "plugins-sdk.") == false) goto L6;
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r8.isDirectory() == true) goto L8;
    L10:
        File[] r0 = r8.listFiles();
        if (r0 == null) goto L16;
        Iterator r02 = ArrayIteratorKt.iterator(r0);
    L14:
        if (r02.hasNext() == false) goto L16;
        removeModulesRecursive(r7, (File) r02.next(), r8.getName() + ".");
    L16:
        String r6 = StringsKt.substringBefore$default(r8.getName(), '.', null, 2, null);
        if (r8.isDirectory() == false) goto L19;
        return;
    L19:
        if (r7.callAttr("__contains__", new Object[]{r9 + r6}).toBoolean() == false) goto L24;
        r7.callAttr("pop", new Object[]{r9 + r6});
        return;
    L24:
        return;
    L8:
        if (r7.callAttr("__contains__", new Object[]{r9 + r8.getName()}).toBoolean() == false) goto L10;
        r7.callAttr("pop", new Object[]{r9 + r8.getName()});
        goto L10
    }

    private final void removeModulesUnderPath(PyObject r10, String r11) {
        Python r9 = this.python;
        if (r9 == null) goto L79;
        String r0 = File.separator;
        String r1 = r11 + r0;
        String r2 = Companion.access$canonicalPathOrNull(Companion, r11);
        if (r2 == null) goto L8;
        String r02 = r2 + r0;
    L9:
        ArrayList r22 = new ArrayList();
        Iterator<PyObject> r92 = r9.getBuiltins().callAttr("list", new Object[]{r10}).asList().iterator();
    L11:
        if (r92.hasNext() == false) goto L50;
        PyObject r3 = r92.next();
        if (r3 == null) goto L11;
        String r32 = r3.toString();
        if (r32 == null) goto L11;
        PyObject r5 = r10.callAttr("get", new Object[]{r32});
        if (r5 == null) goto L11;
        PyObject r6 = (PyObject) r5.get("__file__");
        if (r6 == null) goto L58;
        String r62 = r6.toString();
        if (r62 == null) goto L58;
    L44:
        if (StringsKt.startsWith$default(r62, r1, false, 2, null) == true) goto L49;
        String r63 = Companion.access$canonicalPathOrNull(Companion, r62);
        if (r63 == null) goto L11;
        if (StringsKt.startsWith$default(r63, r02, false, 2, null) != true) goto L11;
    L49:
        r22.add(r32);
    L58:
        Result.Companion r64 = Result.Companion;     // Catch: Throwable -> L31
        PyObject r52 = (PyObject) r5.get("__path__");     // Catch: Throwable -> L31
        if (r52 == null) goto L33;
        List<PyObject> r53 = r52.asList();     // Catch: Throwable -> L31
        if (r53 == null) goto L33;
        PyObject r54 = (PyObject) CollectionsKt.firstOrNull(r53);     // Catch: Throwable -> L31
        if (r54 == null) goto L33;
        String r55 = r54.toString();     // Catch: Throwable -> L31
    L34:
        Object r56 = Result.m2018constructorimpl(r55);     // Catch: Throwable -> L31
    L37:
        boolean r65 = Result.m2024isFailureimpl(r56);
        Object r57 = r56;
        if (r65 == false) goto L40;
        r57 = null;
    L40:
        r62 = (String) r57;
        if (r62 != null) goto L44;
    L33:
        r55 = null;
    L31:
        th = move-exception;
        Result.Companion r66 = Result.Companion;
        r56 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L37
    L50:
        Iterator r93 = r22.iterator();
    L52:
        if (r93.hasNext() == false) goto L55;
        r10.callAttr("pop", new Object[]{(String) r93.next(), null});
        goto L52
    L55:
        if (r22.isEmpty() == true) goto L78;
        FileLog.d("Unloaded " + r22.size() + " module(s) belonging to removed dependency " + new File(r11).getName());
        return;
    L78:
        return;
    L8:
        r02 = r1;
        goto L9
    }

    private final void removePluginDependencies(String r2) {
        PipController.INSTANCE.uninstallDependencies(r2);
        pruneDependencyPaths();
    }

    private final void removePluginPathsFromSysPath() {
        Python r0 = this.python;
        if (r0 == null) goto L16;
        PyObject r02 = r0.getModule("sys");
        if (r02 == null) goto L17;
        PyObject r03 = (PyObject) r02.get("path");
        if (r03 == null) goto L18;
        ArrayList r1 = new ArrayList(this.dependencyPaths);
        r1.add(getPluginsController().getPluginsDir().getAbsolutePath());
        Iterator r12 = r1.iterator();
    L11:
        if (r12.hasNext() == false) goto L13;
        removeFromSysPath(r03, (String) r12.next());
        goto L11
    L13:
        this.dependencyPaths.clear();
        return;
    L18:
        return;
    L17:
        return;
    }

    private final synchronized PyObject requireBasePluginClass() {
        monitor-enter(this);
    L24:
        th = move-exception;
        throw th;
    L4:
        if (ExteraConfig.getPluginsSafeMode() == true) goto L31;
        if (sdkInitialized == false) goto L29;
        PyObject r0 = this.basePluginClass;     // Catch: Throwable -> L24
        if (r0 == null) goto L11;
        monitor-exit(this);
        return r0;
    L11:
        Python r02 = this.python;     // Catch: Throwable -> L24
        if (r02 == null) goto L27;
        PyObject r03 = (PyObject) r02.getModule("base_plugin").get("BasePlugin");     // Catch: Throwable -> L18
        if (r03 == null) goto L21;
        this.basePluginClass = r03;     // Catch: Throwable -> L18
        monitor-exit(this);
        return r03;
    L21:
        throw new Exception("BasePlugin class is missing from the Python SDK");     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        throw new Exception("Failed to initialize Python plugin runtime", th);     // Catch: Throwable -> L24
    L27:
        throw new Exception("Python interpreter is not initialized");     // Catch: Throwable -> L24
    L29:
        throw new Exception("Python plugin SDK is not initialized");     // Catch: Throwable -> L24
    L31:
        throw new Exception(SAFE_MODE_ENABLE_ERROR);     // Catch: Throwable -> L24
    }

    private final void runDevServer() {
        if (ExteraConfig.getPluginsSafeMode() == true) goto L24;
        Python r0 = getPython();
        if (r0 != null) goto L9;
        return;
    L9:
        if (this.devServerClass == null) goto L22;
        stopDevServer();
    L22:
        PyObject r02 = (PyObject) r0.getModule(PluginsConstants.DevServer.MODULE).get(PluginsConstants.DevServer.CLASS);     // Catch: Throwable -> L16
        this.devServerClass = r02;     // Catch: Throwable -> L16
        if (r02 != null) goto L14;
        return;
    L14:
        if (r02 == null) goto L18;
        r02.callAttrThrows(PluginsConstants.DevServer.START_SERVER, new Object[0]);     // Catch: Throwable -> L16
    L18:
        FileLog.d("Dev server started successfully.");     // Catch: Throwable -> L16
        return;
    L16:
        th = move-exception;
        FileLog.e("Failed to initialize dev server", th);
        this.devServerClass = null;
        return;
    }

    private final void stopAndUnloadSdk() {
        Python r0 = this.python;
        if (r0 == null) goto L27;
        this.basePluginClass = null;
        PyObject r02 = r0.getModule("sys");
        PyObject r1 = (PyObject) r02.get("modules");
        if (r1 != null) goto L23;
    L13:
        sdkInitialized = false;
        File r2 = SDK_DIR;
        PyObject r03 = (PyObject) r02.get("path");
        if (r2 == null) goto L19;
        if (r03 == null) goto L19;
        if (r03.callAttr("__contains__", new Object[]{r2.getAbsolutePath()}).toBoolean() == false) goto L19;
        r03.callAttr("remove", new Object[]{r2.getAbsolutePath()});
    L19:
        if (r2 == null) goto L25;
        if (r1 == null) goto L26;
        removeModulesRecursive(r1, r2, _UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L26:
        return;
    L25:
        return;
    L23:
        PyObject r3 = r1.callAttr("get", new Object[]{"_sdk_version"});     // Catch: Throwable -> L11
        if (r3 == null) goto L13;
        r3.callAttr("__stop__", new Object[0]);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        FileLog.e("Failed to stop Python SDK bootstrap", th);
        goto L13
    }

    private final void stopDevServer() {
        PyObject r0 = this.devServerClass;
        if (r0 != null) goto L18;
        return;
    L18:
        r0.callAttrThrows(PluginsConstants.DevServer.STOP_SERVER, new Object[0]);     // Catch: Throwable -> L9
        FileLog.d("Dev server stopped successfully.");     // Catch: Throwable -> L9
        this.devServerClass = null;
        return;
    L9:
        th = move-exception;
        FileLog.e("Failed to stop dev server", th);     // Catch: Throwable -> L13
        this.devServerClass = null;
        return;
    L13:
        th = move-exception;
        this.devServerClass = null;
        throw th;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public boolean canOpenInExternalApp() {
        return true;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void checkDevServer() {
        if (ExteraConfig.getPluginsSafeMode() == false) goto L6;
        return;
    L6:
        if (ExteraConfig.getPluginsDevMode() == false) goto L9;
        runDevServer();
        return;
    L9:
        stopDevServer();
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void clearPluginSettings(String r3) {
        this.settingsCache.remove(r3);
        Python r2 = getPython();
        if (r2 != null) goto L10;
        return;
    L10:
        r2.getModule("plugin_settings").callAttr("clear_settings", new Object[]{r3});     // Catch: PyException -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e("Failed to clear plugin settings for " + r3, e);
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void deletePlugin(String r5, Utilities.Callback<String> r6) {
        unloadPlugin(r5);
        removePluginDependencies(r5);     // Catch: Exception -> L5
    L7:
        Companion.access$deleteFileIfExists(Companion, new File(getPluginsController().getPluginsDir(), r5 + ".py"));
        PluginsController.Companion r0 = PluginsController.Companion;
        if (r0.isPluginPinned(r5) == false) goto L10;
        r0.setPluginPinned(r5, false);
    L10:
        getPluginsController().clearPluginSettingsPreferences(r5, true);
        getPluginsController().getPlugins().remove(r5);
        getPluginsController().notifyPluginsChanged();
        if (r6 == null) goto L16;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda13(r6));
        return;
    L16:
        return;
    L5:
        e = move-exception;
        FileLog.e("Failed to uninstall dependencies for " + r5, e);
        goto L7
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void executeOnAppEvent(String r8) {
        if (sdkInitialized == true) goto L5;
        return;
    L5:
        if (ExteraConfig.getPluginsSafeMode() == true) goto L60;
        Python r0 = getPython();
        if (r0 == null) goto L58;
        PyObject r02 = r0.getModule("base_plugin");
        if (r02 == null) goto L59;
        PyObject r03 = (PyObject) r02.get("AppEvent");
        if (r03 == null) goto L61;
        PyObject r04 = r03.call(new Object[]{r8});
        PyObject r1 = this.debuggerListener;     // Catch: Throwable -> L20
        if (r1 != null) goto L51;
    L24:
        Iterator<Map.Entry<String, PyObject>> r12 = this.pluginInstances.entrySet().iterator();     // Catch: Throwable -> L20
    L26:
        if (r12.hasNext() == false) goto L42;
        Map.Entry<String, PyObject> r3 = r12.next();     // Catch: Throwable -> L20
        String r4 = r3.getKey();     // Catch: Throwable -> L20
        PyObject r32 = r3.getValue();     // Catch: Throwable -> L20
        if (getPluginsController().isPluginActive$TMessagesProj(r4) == false) goto L26;
        getPluginsController().getWatchdog().onPluginExecutionStarted(r4);     // Catch: Throwable -> L20
        r32.callAttr(PluginsConstants.ON_APP_EVENT, new Object[]{r04});     // Catch: Throwable -> L34 PyException -> L36
        PluginsController r33 = getPluginsController();     // Catch: Throwable -> L20
    L39:
        r33.getWatchdog().onPluginExecutionFinished(r4);     // Catch: Throwable -> L20
        goto L26
    L36:
        e = move-exception;
        FileLog.e("Failed to execute app " + r8 + " for " + r4, e);     // Catch: Throwable -> L34
        r33 = getPluginsController();     // Catch: Throwable -> L20
    L34:
        th = move-exception;
        getPluginsController().getWatchdog().onPluginExecutionFinished(r4);     // Catch: Throwable -> L20
        throw th;     // Catch: Throwable -> L20
    L42:
        PyObjectUtils.closeSafely(r04);
        return;
    L51:
        r1.callAttr(PluginsConstants.ON_APP_EVENT, new Object[]{r04});     // Catch: Throwable -> L20 PyException -> L22
    L22:
        e = move-exception;
        FileLog.e("Failed to execute app event for debugger listener", e);     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        PyObjectUtils.closeSafely(r04);
        throw th;
    L61:
        return;
    L59:
        return;
    L58:
        return;
    }

    public final PluginsController.HookResult<PluginsHooks.PostRequestResult> executePostRequestHook(String r8, int r9, TLObject r10, TLRPC.TL_error r11, PyObject r12) {
        if (r12 == null) goto L40;
        PyObject r92 = r12.callAttr("post_request_hook", new Object[]{r8, Integer.valueOf(r9), r10, r11});     // Catch: Throwable -> L33
        if (r92 == null) goto L40;
        String r122 = PyObjectUtils.getString(r92, PluginsConstants.STRATEGY, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L14
        if (r122 != null) goto L10;
    L35:
        PyObjectUtils.closeSafely(r92);     // Catch: Throwable -> L33
        goto L40
    L10:
        if (StringsKt.endsWith$default(r122, PluginsConstants.Strategy.MODIFY, false, 2, null) == false) goto L12;
    L16:
        PyObject r2 = (PyObject) r92.get(PluginsConstants.RESPONSE);     // Catch: Throwable -> L14
        if (r2 != null) goto L44;
    L22:
        PyObject r0 = (PyObject) r92.get("error");     // Catch: Throwable -> L14
        if (r0 == null) goto L29;
        r11 = (TLRPC.TL_error) r0.toJava(TLRPC.TL_error.class);     // Catch: Throwable -> L14 ClassCastException -> L26
        goto L29
    L26:
        e = move-exception;
        FileLog.e("Bad error type from post_request_hook for " + r8, e);     // Catch: Throwable -> L14
    L29:
        if (StringsKt.endsWith$default(r122, PluginsConstants.Strategy.MODIFY_FINAL, false, 2, null) == false) goto L35;
        PluginsController.HookResult<PluginsHooks.PostRequestResult> r7 = new PluginsController.HookResult(new PluginsHooks.PostRequestResult(r10, r11), false, true);     // Catch: Throwable -> L14
        PyObjectUtils.closeSafely(r92);     // Catch: Throwable -> L33
        return r7;
    L44:
        r10 = (TLObject) r2.toJava(TLObject.class);     // Catch: Throwable -> L14 ClassCastException -> L20
        goto L22
    L20:
        e = move-exception;
        FileLog.e("Bad response type from post_request_hook for " + r8, e);     // Catch: Throwable -> L14
        goto L22
    L12:
        if (StringsKt.endsWith$default(r122, PluginsConstants.Strategy.MODIFY_FINAL, false, 2, null) == false) goto L35;
    L14:
        th = move-exception;
        PyObjectUtils.closeSafely(r92);     // Catch: Throwable -> L33
        throw th;     // Catch: Throwable -> L33
    L33:
        th = move-exception;
        FileLog.e("Failed to execute post_request_hook for " + r8, th);
    L40:
        return new PluginsController.HookResult(new PluginsHooks.PostRequestResult(r10, r11), false, false);
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public PluginsController.HookResult<PluginsHooks.PostRequestResult> executePostRequestHook(String r7, int r8, TLObject r9, TLRPC.TL_error r10, String r11) {
        return executePostRequestHook(r7, r8, r9, r10, this.pluginInstances.get(r11));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public PluginsController.HookResult<TLObject> executePreRequestHook(String r8, int r9, TLObject r10, String r11) {
        return executeHook(r11, r10, TLObject.class, PluginsConstants.REQUEST, new PythonPluginsEngine$$ExternalSyntheticLambda23(r8, r9), new PythonPluginsEngine$$ExternalSyntheticLambda24(r11, r8));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public PluginsController.HookResult<SendMessagesHelper.SendMessageParams> executeSendMessageHook(int r8, SendMessagesHelper.SendMessageParams r9, String r10) {
        return executeHook(r10, r9, SendMessagesHelper.SendMessageParams.class, PluginsConstants.PARAMS, new PythonPluginsEngine$$ExternalSyntheticLambda20(r8), new PythonPluginsEngine$$ExternalSyntheticLambda21(r10));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public PluginsController.HookResult<TLRPC.Update> executeUpdateHook(String r8, int r9, TLRPC.Update r10, String r11) {
        return executeHook(r11, r10, TLRPC.Update.class, PluginsConstants.UPDATE, new PythonPluginsEngine$$ExternalSyntheticLambda3(r8, r9), new PythonPluginsEngine$$ExternalSyntheticLambda4(r8));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public PluginsController.HookResult<TLRPC.Updates> executeUpdatesHook(String r8, int r9, TLRPC.Updates r10, String r11) {
        return executeHook(r11, r10, TLRPC.Updates.class, PluginsConstants.UPDATES, new PythonPluginsEngine$$ExternalSyntheticLambda10(r8, r9), new PythonPluginsEngine$$ExternalSyntheticLambda11(r8));
    }

    public final String fetchParameterValue(String r4, String r5) {
        if (r4 != null) goto L14;
        return null;
    L14:
        File r1 = new File(r4);     // Catch: Exception -> L13
        if (r1.exists() == false) goto L12;
        if (r1.isFile() == false) goto L12;
        return parsePluginMetadata(r4).get(r5);
    L12:
        return null;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public Map<String, ?> getAllPluginSettings(String r7) {
        Python r0 = getPython();
        if (r0 != null) goto L23;
        return null;
    L23:
        PyObject r02 = r0.getModule("plugin_settings").callAttr("get_all_settings", new Object[]{r7});     // Catch: PyException -> L15
        if (r02 == null) goto L22;
        HashMap r2 = new HashMap();     // Catch: PyException -> L15
        Iterator<Map.Entry<PyObject, PyObject>> r03 = r02.asMap().entrySet().iterator();     // Catch: PyException -> L15
    L9:
        if (r03.hasNext() == false) goto L19;
        Map.Entry<PyObject, PyObject> r3 = r03.next();     // Catch: PyException -> L15
        PyObject r4 = r3.getKey();     // Catch: PyException -> L15
        PyObject r32 = r3.getValue();     // Catch: PyException -> L15
        if (r4 == null) goto L9;
        String r42 = r4.toString();     // Catch: PyException -> L15
        if (r32 == null) goto L17;
        Object r33 = r32.toJava(Object.class);     // Catch: PyException -> L15
    L18:
        r2.put(r42, r33);     // Catch: PyException -> L15
        goto L9
    L17:
        r33 = null;
        goto L18
    L19:
        this.settingsCache.put(r7, new ConcurrentHashMap(r2));     // Catch: PyException -> L15
        return r2;
    L22:
        return null;
    L15:
        e = move-exception;
        FileLog.e("Failed to get all plugin settings for " + r7, e);
        goto L22
    }

    public final PyObject getBasePluginClass() {
        return this.basePluginClass;
    }

    public final PyObject getDebuggerListener() {
        return this.debuggerListener;
    }

    public final ConcurrentHashMap<String, PyObject> getPluginInstances() {
        return this.pluginInstances;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public String getPluginPath(String r3) {
        return getPluginsController().getPluginsDir().getAbsolutePath() + File.separator + r3 + ".py";
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public Object getPluginSetting(String r4, String r5, Object r6) {
        ConcurrentHashMap<String, Object> r0 = this.settingsCache.get(r4);
        if (r0 != null) goto L5;
    L8:
        Python r02 = getPython();
        if (r02 != null) goto L38;
    L13:
        return r6;
    L38:
        PyObject r03 = r02.getModule("plugin_settings").callAttr("get_setting", new Object[]{r4, r5, r6});     // Catch: PyException -> L17
        if (r03 == null) goto L13;
        if ((r6 instanceof Boolean) == false) goto L20;
        Object r04 = Boolean.valueOf(r03.toBoolean());     // Catch: PyException -> L17
    L34:
        this.settingsCache.computeIfAbsent(r4, new PythonPluginsEngine$$ExternalSyntheticLambda26(new PythonPluginsEngine$$ExternalSyntheticLambda25())).put(r5, r04);     // Catch: PyException -> L17
        return r04;
    L20:
        if ((r6 instanceof Integer) == false) goto L23;
        r04 = Integer.valueOf(r03.toInt());     // Catch: PyException -> L17
        goto L34
    L23:
        if ((r6 instanceof String) == false) goto L26;
        r04 = r03.toString();     // Catch: PyException -> L17
        goto L34
    L26:
        if ((r6 instanceof Float) == false) goto L29;
        r04 = Float.valueOf(r03.toFloat());     // Catch: PyException -> L17
        goto L34
    L29:
        if ((r6 instanceof Long) == false) goto L31;
        r04 = Long.valueOf(r03.toLong());     // Catch: PyException -> L17
        goto L34
    L31:
        if (r6 != null) goto L33;
        r04 = r03.toJava(Object.class);     // Catch: PyException -> L17
        goto L34
    L33:
        r04 = r03.toJava(r6.getClass());     // Catch: PyException -> L17
    L17:
        e = move-exception;
        FileLog.e("Failed to get plugin setting " + r4 + "/" + r5, e);
        return r6;
    L5:
        if (r0.containsKey(r5) == false) goto L8;
        return r0.get(r5);
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void init(Runnable r6) {
        if (getPython() != null) goto L38;
        r6.run();
        return;
    L38:
    L10:
        th = move-exception;
        FileLog.e("Failed to initialize Python plugins engine", th);
        r6.run();
        return;
    L7:
        if (initSdk() == true) goto L13;
        r6.run();     // Catch: Throwable -> L10
        return;
    L13:
        if (ExteraConfig.getPluginsSafeMode() == true) goto L15;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda8(Updater.Companion), 5000);
    L15:
        Python r0 = this.python;
        if (r0 != null) goto L20;
        r6.run();
        return;
    L20:
        if (ExteraConfig.getPluginsSafeMode() == false) goto L40;
    L34:
        PipController.INSTANCE.cleanup();
        loadPlugins(r6);
        checkDevServer();
        return;
    L40:
        String[] r02 = (String[]) r0.getModule("plugin_settings").callAttr("init", new Object[]{getPluginsController().getPluginsDir().getAbsolutePath(), getPluginsController().getPreferences().getAll()}).toJava(String[].class);     // Catch: PyException -> L29
        int r2 = 0;
        if (r02.length != 0) goto L24;
        boolean r1 = true;
    L25:
        if (r1 == true) goto L34;
        SharedPreferences.Editor r12 = getPluginsController().getPreferences().edit();     // Catch: PyException -> L29
        int r3 = r02.length;     // Catch: PyException -> L29
    L27:
        if (r2 >= r3) goto L31;
        r12.remove(r02[r2]);     // Catch: PyException -> L29
        r2 = r2 + 1;     // Catch: PyException -> L29
        goto L27
    L31:
        r12.apply();     // Catch: PyException -> L29
        FileLog.d("Migrated " + r02.length + " plugin settings from SharedPreferences to JSON.");     // Catch: PyException -> L29
        goto L34
    L24:
        r1 = false;
    L29:
        e = move-exception;
        FileLog.e("Failed to initialize plugin_settings module", e);
        goto L34
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public boolean isEngineAvailable() {
        if (this.python != null) goto L5;
        return false;
    L5:
        if (sdkInitialized == true) goto L7;
        return false;
    L7:
        if (this.basePluginClass == null) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public boolean isPlugin(File r3, MessageObject r4) {
        if (r3 != null) goto L5;
        return false;
    L5:
        if (StringsKt.endsWith$default(r3.getName().toLowerCase(Locale.ROOT), PluginsConstants.PLUGINS_EXT, false, 2, null) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public final void loadPlugin(String r2, String r3) {
        loadPlugin(r2, r3, null, null);
    }

    public final void loadPlugin(String r2, String r3, Plugin r4) {
        loadPlugin(r2, r3, r4, null);
    }

    public final void loadPlugin(String r6, String r7, Plugin r8, PipController.InstallerDelegate r9) {
        boolean r0 = getPluginsController().getPreferences().getBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r6, false);
        File r1 = new File(r7);
        if (r1.exists() == false) goto L36;
        if (r1.isFile() == false) goto L36;
        if (r8 != null) goto L12;
        PluginsController.PluginValidationResult r82 = validatePluginFromFile(r7);
        if (r82.getError() != null) goto L11;
        r8 = r82.getPlugin();
        goto L12
    L11:
        throw new Exception(r82.getError());
    L12:
        if (r8 != null) goto L14;
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L14:
        if (Intrinsics.areEqual(r6, r8.getId()) == false) goto L32;
        if (this.pluginInstances.containsKey(r6) == false) goto L18;
        unloadPlugin(r6);
    L18:
        r8.setEnabled(false);
        r8.setError(null);
        getPluginsController().getPlugins().put(r6, r8);
        if (ExteraConfig.getPluginsSafeMode() == true) goto L40;
        if (r0 == true) goto L37;
        if (r9 == null) goto L39;
        installPluginDependencies(r6, r8, r9);
        return;
    L39:
        return;
    L37:
        createPluginInstance(r6, r7, r8, r9);     // Catch: Throwable -> L28
        setPluginEnabled(r6, true, null);
        return;
    L28:
        th = move-exception;
        SharedPreferences.Editor r83 = getPluginsController().getPreferences().edit();
        r83.putBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r6, false);
        r83.apply();
        unloadPlugin(r6);
        throw th;
    L40:
        return;
    L32:
        throw new Exception("Plugin ID mismatch. Expected: " + r6 + ", but found: " + r8.getId() + " in metadata.");
    L36:
        throw new Exception("Plugin file not found: " + r7);
    }

    public final void loadPluginFromFile(String r2, Plugin r3, Utilities.Callback<String> r4) {
        loadPluginFromFile(r2, r3, r4, null);
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void loadPluginFromFile(String r8, Plugin r9, Utilities.Callback<String> r10, PipController.InstallerDelegate r11) {
        PluginsController.Companion.runOnPluginsQueue(new PythonPluginsEngine$$ExternalSyntheticLambda9(r9, this, r8, r11, r10));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public List<SettingItem> loadPluginSettings(String r5) {
        Plugin r1 = getPluginsController().getPlugins().get(r5);     // Catch: Exception -> L15
        PyObject r2 = this.pluginInstances.get(r5);     // Catch: Exception -> L15
        if (getPluginsController().isPluginActive$TMessagesProj(r1) == false) goto L17;
        if (r2 == null) goto L17;
        PyObject r52 = r2.callAttr(PluginsConstants.CREATE_SETTINGS, new Object[0]);     // Catch: Exception -> L15
        if (r52 != null) goto L10;
        return null;
    L10:
        List<PyObject> r53 = r52.asList();     // Catch: Exception -> L15
        if (r53.isEmpty() == false) goto L14;
        return null;
    L14:
        return parsePySettingDefinitions(r53);
    L17:
        getPluginsController().invalidatePluginSettings(r5);     // Catch: Exception -> L15
        return null;
    L15:
        e = move-exception;
        FileLog.e("Failed to load plugin settings", e);
        return null;
    }

    public final void loadPlugins(Runnable r3) {
        PluginsController.Companion.runOnPluginsQueue(new PythonPluginsEngine$$ExternalSyntheticLambda22(this, r3));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void openInExternalApp(String r4) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L10;
        File r1 = new File(getPluginPath(r4));
        if (r1.exists() == false) goto L9;
        PluginFileViewer.INSTANCE.open(r0, r1, r4 + PluginsConstants.PLUGINS_EXT);
        return;
    L9:
        return;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void openPluginSetting(Plugin r3, String r4, BaseFragment r5) {
        PluginsController.Companion.runOnPluginsQueue(new PythonPluginsEngine$$ExternalSyntheticLambda12(this, r3, r4, r5));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void openPluginSetting(String r2, String r3, BaseFragment r4) {
        Plugin r22 = getPluginsController().getPlugins().get(r2);
        if (r22 == null) goto L6;
        openPluginSetting(r22, r3, r4);
        return;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void openPluginSettings(Plugin r1, BaseFragment r2) {
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda5(r2, r1));
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void openPluginSettings(String r2, BaseFragment r3) {
        Plugin r22 = getPluginsController().getPlugins().get(r2);
        if (r22 == null) goto L6;
        openPluginSettings(r22, r3);
        return;
    }

    public final Map<String, String> parsePluginMetadata(String r4) {
        HashMap r0 = new HashMap();
        if (r4 == null) goto L24;
        File r1 = new File(r4);
        if (r1.exists() == false) goto L24;
        if (r1.isFile() == false) goto L24;
        Python r3 = getPython();
        if (r3 != null) goto L25;
        FileLog.e("Python engine not initialized, cannot parse metadata for ".concat(r4));
        return r0;
    L25:
        PyObject r32 = r3.getModule("extera_utils.metadata_parser").callAttr("get_metadata", new Object[]{r4});     // Catch: PyException -> L21
        if (r32 == null) goto L24;
        Iterator<Map.Entry<PyObject, PyObject>> r33 = r32.asMap().entrySet().iterator();     // Catch: PyException -> L21
    L17:
        if (r33.hasNext() == false) goto L24;
        Map.Entry<PyObject, PyObject> r12 = r33.next();     // Catch: PyException -> L21
        r0.put(r12.getKey().toString(), r12.getValue().toString());     // Catch: PyException -> L21
    L21:
        e = move-exception;
        FileLog.e("Failed to parse metadata from " + r4 + ". Error: " + e.getMessage(), e);
        throw e;
    L24:
        return r0;
    }

    public final List<SettingItem> parsePySettingDefinitions(List<? extends PyObject> r22) {
        ArrayList r0 = new ArrayList(r22.size());
        Iterator<? extends PyObject> r1 = r22.iterator();
    L4:
        if (r1.hasNext() == false) goto L97;
        PyObject r2 = r1.next();
        String r3 = PyObjectUtils.getString(r2, "type", null);
        if (r3 == null) goto L7;
        String r7 = PyObjectUtils.getString(r2, PluginsConstants.Settings.KEY, null);
        String r8 = PyObjectUtils.getString(r2, "text", null);
        String r10 = PyObjectUtils.getString(r2, "subtext", null);
        String r11 = PyObjectUtils.getString(r2, "icon", null);
        PyObject r12 = (PyObject) r2.get(PluginsConstants.Settings.ON_CHANGE);
        PyObject r17 = (PyObject) r2.get(PluginsConstants.Settings.ON_LONG_CLICK);
        String r14 = PyObjectUtils.getString(r2, PluginsConstants.Settings.LINK_ALIAS, null);
        PyObject r6 = (PyObject) r2.get(PluginsConstants.Settings.DEFAULT);
        PyObject r15 = (PyObject) r2.get("on_click");
        PyObject r16 = (PyObject) r2.get(PluginsConstants.Settings.CREATE_SUB_FRAGMENT);
        switch(r3.hashCode()) {
            case -1866021310: goto L81;
            case -1349088399: goto L61;
            case -1221270899: goto L56;
            case -889473228: goto L49;
            case 3556653: goto L43;
            case 100358090: goto L29;
            case 1191572447: goto L16;
            case 1674318617: goto L12;
            default: goto L82;
        };
    L82:
        Object r4 = null;
    L95:
        if (r4 == null) goto L4;
        r0.add(r4);
        goto L4
    L12:
        if (r3.equals(PluginsConstants.Settings.TYPE_DIVIDER) == false) goto L82;
        r4 = new DividerSetting(r8);
        goto L95
    L16:
        if (r3.equals(PluginsConstants.Settings.TYPE_SELECTOR) == false) goto L82;
        String[] r102 = PyObjectUtils.getStringArray(r2, PluginsConstants.Settings.ITEMS, null);
        if (r7 == null) goto L82;
        if (r8 == null) goto L82;
        if (r102 == null) goto L82;
        if (r102.length == 0) goto L82;
        if (r6 == null) goto L82;
        Object r62 = new SelectorSetting(r7, r8, r6.toInt(), r102, r11, r12, r17, r14);
    L27:
        r4 = r62;
        goto L95
    L29:
        if (r3.equals(PluginsConstants.Settings.TYPE_INPUT) == false) goto L82;
        if (r7 == null) goto L82;
        if (r8 == null) goto L82;
        if (r6 == null) goto L40;
        String r23 = r6.toString();
        if (r23 == null) goto L40;
        String r9 = r23;
    L41:
        r62 = new InputSetting(r7, r8, r9, r10, r11, r12, r17, r14);
    L40:
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L41
    L43:
        if (r3.equals("text") == false) goto L82;
        boolean r122 = PyObjectUtils.getBoolean(r2, PluginsConstants.Settings.ACCENT, false);
        boolean r13 = PyObjectUtils.getBoolean(r2, PluginsConstants.Settings.RED, false);
        if (r8 == null) goto L82;
        r4 = new TextSetting(r8, r10, r11, r122, r13, r15, r16, r17, r14);
        goto L95
    L49:
        if (r3.equals(PluginsConstants.Settings.TYPE_SWITCH) == false) goto L82;
        if (r7 == null) goto L82;
        if (r8 == null) goto L82;
        if (r6 == null) goto L82;
        r62 = new SwitchSetting(r7, r8, r6.toBoolean(), r10, r11, r12, r17, r14);
        goto L27
    L56:
        if (r3.equals(PluginsConstants.Settings.TYPE_HEADER) == false) goto L82;
        if (r8 == null) goto L82;
        r4 = new HeaderSetting(r8);
        goto L95
    L61:
        if (r3.equals(PluginsConstants.Settings.TYPE_CUSTOM) == false) goto L82;
        PyObject r32 = (PyObject) r2.get(PluginsConstants.Settings.VIEW);
        PyObject r42 = (PyObject) r2.get(PluginsConstants.Settings.ITEM);
        PyObject r5 = (PyObject) r2.get(PluginsConstants.Settings.FACTORY);
        PyObject r24 = (PyObject) r2.get(PluginsConstants.Settings.FACTORY_ARGS);
        if (r5 == null) goto L72;
        CustomSetting.Factory r33 = (CustomSetting.Factory) PyObjectUtils.toJavaCompat(r5, CustomSetting.Factory.class);
        if (r33 == null) goto L82;
        if (r24 != null) goto L71;
        Object r132 = new CustomSetting(r33, r15, r16, r17, r14);
    L70:
        r4 = r132;
        goto L95
    L71:
        r132 = new CustomSetting(r33, r24, r15, r16, r17, r14);
        goto L70
    L72:
        if (r42 == null) goto L76;
        UItem r25 = (UItem) PyObjectUtils.toJavaCompat(r42, UItem.class);
        if (r25 == null) goto L82;
        r132 = new CustomSetting(r25, r15, r16, r17, r14);
        goto L70
    L76:
        if (r32 == null) goto L82;
        View r26 = (View) PyObjectUtils.toJavaCompat(r32, View.class);
        if (r26 == null) goto L82;
        r132 = new CustomSetting(r26, r15, r16, r17, r14);
        goto L70
    L81:
        if (r3.equals(PluginsConstants.Settings.TYPE_EDIT_TEXT) == false) goto L82;
        r62 = null;
        String r82 = PyObjectUtils.getString(r2, PluginsConstants.Settings.HINT, null);
        boolean r103 = PyObjectUtils.getBoolean(r2, PluginsConstants.Settings.MULTILINE, false);
        int r112 = PyObjectUtils.getInt(r2, PluginsConstants.Settings.MAX_LENGTH, 256);
        String r27 = PyObjectUtils.getString(r2, PluginsConstants.Settings.MASK, null);
        if (r7 == null) goto L27;
        if (r82 == null) goto L27;
        if (r6 == null) goto L93;
        String r34 = r6.toString();
        if (r34 == null) goto L93;
        String r92 = r34;
    L94:
        r62 = new EditTextSetting(r7, r82, r92, r103, r112, r27, r12);
    L93:
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L94
    L7:
        FileLog.w("A setting item in a plugin is missing its 'type'. Skipping.");
        goto L4
    L97:
        return r0;
    }

    public final void setBasePluginClass(PyObject r1) {
        this.basePluginClass = r1;
    }

    public final void setDebuggerListener(PyObject r1) {
        this.debuggerListener = r1;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void setPluginEnabled(String r10, boolean r11, Utilities.Callback<String> r12) {
        Plugin r4 = getPluginsController().getPlugins().get(r10);     // Catch: Throwable -> L11
        if (r4 == null) goto L49;
        if (r11 == true) goto L7;
    L13:
        PyObject r2 = this.pluginInstances.get(r10);     // Catch: Throwable -> L11
        if (r11 == false) goto L22;
        if (r2 != null) goto L22;
        createPluginInstance(r10, getPluginPath(r10), r4, null);     // Catch: Throwable -> L11
        r2 = this.pluginInstances.get(r10);     // Catch: Throwable -> L11
        if (r2 != null) goto L22;
        throw new Exception("Failed to create plugin instance: " + r10);     // Catch: Throwable -> L11
    L22:
        if (r2 != null) goto L24;
    L26:
        boolean r7 = false;
    L27:
        if (r7 == r11) goto L29;
    L33:
        if (r11 == false) goto L43;
        if (r2 == null) goto L42;
        getPluginsController().cleanupPlugin(r10);     // Catch: Throwable -> L11
        getPluginsController().getWatchdog().onPluginExecutionStarted(r10);     // Catch: Throwable -> L11
        r2.callAttr(PluginsConstants.ON_PLUGIN_LOAD, new Object[0]);     // Catch: Throwable -> L38
        getPluginsController().getWatchdog().onPluginExecutionFinished(r10);     // Catch: Throwable -> L11
        Boolean r72 = Boolean.TRUE;     // Catch: Throwable -> L11
        r2.put("initialized", r72);     // Catch: Throwable -> L11
        r2.put("error_message", null);     // Catch: Throwable -> L11
        r4.setError(null);     // Catch: Throwable -> L11
        r2.put("enabled", r72);     // Catch: Throwable -> L11
        r4.setEnabled(true);     // Catch: Throwable -> L11
        SharedPreferences.Editor r1 = getPluginsController().getPreferences().edit();     // Catch: Throwable -> L11
        r1.putBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r10, true);     // Catch: Throwable -> L11
        r1.apply();     // Catch: Throwable -> L11
        getPluginsController().loadPluginSettings(r10);     // Catch: Throwable -> L11
    L44:
        getPluginsController().notifyPluginsChanged();     // Catch: Throwable -> L11
        if (r12 == null) goto L64;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda15(r12));     // Catch: Throwable -> L11
        return;
    L64:
        return;
    L38:
        th = move-exception;
        getPluginsController().getWatchdog().onPluginExecutionFinished(r10);     // Catch: Throwable -> L11
        throw th;     // Catch: Throwable -> L11
    L42:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Throwable -> L11
    L43:
        SharedPreferences.Editor r13 = getPluginsController().getPreferences().edit();     // Catch: Throwable -> L11
        r13.putBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r10, false);     // Catch: Throwable -> L11
        r13.apply();     // Catch: Throwable -> L11
        unloadPlugin(r10);     // Catch: Throwable -> L11
        goto L44
    L29:
        if (r4.hasError() == true) goto L33;
        if (r12 == null) goto L63;
        r12.run(null);     // Catch: Throwable -> L11
        return;
    L63:
        return;
    L24:
        if (PyObjectUtils.getBoolean(r2, "initialized", false) == false) goto L26;
        r7 = true;
        goto L27
    L7:
        if (ExteraConfig.getPluginsSafeMode() == false) goto L13;
        if (r12 == null) goto L62;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda14(r12));     // Catch: Throwable -> L11
        return;
    L62:
        return;
    L49:
        throw new Exception("Plugin not found: " + r10);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        FileLog.e("Unexpected error setting enabled state for " + r10, th);
        if (r11 == false) goto L55;
        Plugin r112 = getPluginsController().getPlugins().get(r10);
        if (r112 == null) goto L55;
        r112.setError(th);
    L55:
        SharedPreferences.Editor r113 = getPluginsController().getPreferences().edit();
        r113.putBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r10, false);
        r113.apply();
        unloadPlugin(r10);
        if (r12 == null) goto L65;
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda16(r12, th));
        return;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void setPluginSetting(String r4, String r5, Object r6) {
        this.settingsCache.computeIfAbsent(r4, new PythonPluginsEngine$$ExternalSyntheticLambda1(new PythonPluginsEngine$$ExternalSyntheticLambda0())).put(r5, r6);
        Python r3 = getPython();
        if (r3 != null) goto L10;
        return;
    L10:
        r3.getModule("plugin_settings").callAttr("set_setting", new Object[]{r4, r5, r6});     // Catch: PyException -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e("Failed to set plugin setting " + r4 + "/" + r5, e);
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void sharePlugin(String r6) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L23;
        Activity r1 = r0.getParentActivity();
        if (r1 != null) goto L8;
        return;
    L8:
        String r5 = getPluginPath(r6);
        File r2 = new File(ApplicationLoader.getFilesDirFixed(), "temp");
        SimpliFiles.directory(r2).create();
        File r3 = new File(r2, r6 + PluginsConstants.PLUGINS_EXT);
        SimpliFiles.file(r5).copyTo(r3, OverwritePolicy.REPLACE);     // Catch: Exception -> L12 IllegalArgumentException -> L15 IOException -> L18
        Uri r52 = FileProvider.getUriForFile(r1, ApplicationLoader.getApplicationId() + ".provider", r3);     // Catch: Exception -> L12 IllegalArgumentException -> L15 IOException -> L18
        Intent r62 = new Intent("android.intent.action.SEND");
        r62.setFlags(1);
        r62.putExtra("android.intent.extra.STREAM", r52);
        r62.setType("application/x-plugin");
        r0.startActivityForResult(Intent.createChooser(r62, LocaleController.getString(R.string.ShareFile)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        r3.deleteOnExit();
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void showInstallDialog(BaseFragment r4, InstallPluginBottomSheet.PluginInstallParams r5) {
        File r0 = new File(r5.getFilePath());
        String r1 = fetchParameterValue(r5.getFilePath(), "name");
        if (TextUtils.isEmpty(r1) == true) goto L5;
    L7:
        PluginsController.PluginValidationResult r3 = validatePluginFromFile(r5.getFilePath());
        if (r3.getPlugin() == null) goto L11;
        new InstallPluginBottomSheet(r4, r3, r5).show();
        return;
    L11:
        AndroidUtilities.runOnUIThread(new PythonPluginsEngine$$ExternalSyntheticLambda17(r4, r1, r3));
        return;
    L5:
        if (r0.exists() == false) goto L7;
        r1 = r0.getName();
        goto L7
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.PluginsEngine
    public void shutdown(Runnable r3) {
        if (getPython() != null) goto L28;
        r3.run();
        return;
    L28:
        stopDevServer();     // Catch: Exception -> L10
        Iterator r0 = new ArrayList(this.pluginInstances.keySet()).iterator();     // Catch: Exception -> L10
    L8:
        if (r0.hasNext() == false) goto L12;
        unloadPlugin((String) r0.next());     // Catch: Exception -> L10
        goto L8
    L12:
        PyObject r02 = this.debuggerListener;     // Catch: Exception -> L10
        if (r02 == null) goto L15;
        r02.close();     // Catch: Exception -> L10
    L15:
        this.debuggerListener = null;     // Catch: Exception -> L10
        this.pluginInstances.clear();     // Catch: Exception -> L10
        monitor-enter(this);     // Catch: Exception -> L10
        removePluginPathsFromSysPath();     // Catch: Throwable -> L20
        stopAndUnloadSdk();     // Catch: Throwable -> L20
        this.python = null;     // Catch: Throwable -> L20
        sdkInitialized = false;     // Catch: Throwable -> L20
        Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L20
        monitor-exit(this);     // Catch: Exception -> L10
        FileLog.d("Python plugin engine shut down.");     // Catch: Exception -> L10
    L24:
        r3.run();
        return;
    L20:
        th = move-exception;
        throw th;     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L24
    }

    public final void unloadPlugin(String r12) {
        this.settingsCache.remove(r12);
        PyObject r1 = this.pluginInstances.remove(r12);
        Plugin r2 = getPluginsController().getPlugins().get(r12);
        if (r2 == null) goto L5;
        r2.setEnabled(false);
    L5:
        Unit r5 = null;
        if (r1 != null) goto L94;
    L24:
        if (r1 == null) goto L26;
        r1.put("initialized", Boolean.FALSE);     // Catch: Throwable -> L14 PyException -> L16
    L26:
        if (r1 == null) goto L28;
        r1.put("enabled", Boolean.FALSE);     // Catch: Throwable -> L14 PyException -> L16
    L28:
        File r0 = new File(getPluginsController().getPluginsDir(), "__pycache__");     // Catch: Throwable -> L14 PyException -> L16
        if (r0.exists() == true) goto L31;
    L41:
        File r7 = null;
    L42:
        if (r7 == null) goto L44;
        Companion.access$deleteFileIfExists(Companion, r7);     // Catch: Throwable -> L14 PyException -> L16
    L44:
        refreshImportCaches(r12, getPluginsController().getPluginsDir());     // Catch: Throwable -> L14 PyException -> L16
        getPluginsController().cleanupPlugin(r12);
        Result.Companion r11 = Result.Companion;     // Catch: Throwable -> L49
        if (r1 == null) goto L51;
        r1.close();     // Catch: Throwable -> L49
        r5 = Unit.INSTANCE;     // Catch: Throwable -> L49
    L51:
        Object r112 = Result.m2018constructorimpl(r5);     // Catch: Throwable -> L49
    L54:
        Throwable r113 = Result.m2021exceptionOrNullimpl(r112);
        if (r113 == null) goto L71;
        StringBuilder r02 = new StringBuilder("Failed to close plugin instance ");
    L57:
        r02.append(r12);
        FileLog.e(r02.toString(), r113);
        return;
    L71:
        return;
    L49:
        th = move-exception;
        Result.Companion r03 = Result.Companion;
        r112 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L54
    L31:
        if (r0.isDirectory() == false) goto L41;
        File[] r04 = r0.listFiles();     // Catch: Throwable -> L14 PyException -> L16
        if (r04 == null) goto L41;
        int r22 = r04.length;     // Catch: Throwable -> L14 PyException -> L16
        int r6 = 0;
    L36:
        if (r6 >= r22) goto L41;
        r7 = r04[r6];     // Catch: Throwable -> L14 PyException -> L16
        if (StringsKt.startsWith$default(r7.getName(), r12 + ".cpython-", false, 2, null) == true) goto L42;
        r6 = r6 + 1;     // Catch: Throwable -> L14 PyException -> L16
        goto L36
    L94:
    L16:
        e = move-exception;
        FileLog.e("Failed to unload plugin " + r12, e);     // Catch: Throwable -> L14
        getPluginsController().cleanupPlugin(r12);
        Result.Companion r114 = Result.Companion;     // Catch: Throwable -> L63
        if (r1 == null) goto L65;
        r1.close();     // Catch: Throwable -> L63
        r5 = Unit.INSTANCE;     // Catch: Throwable -> L63
    L65:
        Object r115 = Result.m2018constructorimpl(r5);     // Catch: Throwable -> L63
    L68:
        r113 = Result.m2021exceptionOrNullimpl(r115);
        if (r113 == null) goto L100;
        r02 = new StringBuilder("Failed to close plugin instance ");
        goto L57
    L100:
        return;
    L63:
        th = move-exception;
        Result.Companion r05 = Result.Companion;
        r115 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L68
    L8:
        if (PyObjectUtils.getBoolean(r1, "initialized", false) == false) goto L24;
        getPluginsController().getWatchdog().onPluginExecutionStarted(r12);     // Catch: Throwable -> L14 PyException -> L16
        r1.callAttr(PluginsConstants.ON_PLUGIN_UNLOAD, new Object[0]);     // Catch: Throwable -> L18
        PluginsController r06 = getPluginsController();     // Catch: Throwable -> L14 PyException -> L16
    L12:
        r06.getWatchdog().onPluginExecutionFinished(r12);     // Catch: Throwable -> L14 PyException -> L16
    L18:
        th = move-exception;
        FileLog.e("Error during on_plugin_unload for " + r12, th);     // Catch: Throwable -> L21
        r06 = getPluginsController();     // Catch: Throwable -> L14 PyException -> L16
    L21:
        th = move-exception;
        getPluginsController().getWatchdog().onPluginExecutionFinished(r12);     // Catch: Throwable -> L14 PyException -> L16
        throw th;     // Catch: Throwable -> L14 PyException -> L16
    L14:
        th = move-exception;
        getPluginsController().cleanupPlugin(r12);
        Result.Companion r116 = Result.Companion;     // Catch: Throwable -> L76
        if (r1 == null) goto L78;
        r1.close();     // Catch: Throwable -> L76
        r5 = Unit.INSTANCE;     // Catch: Throwable -> L76
    L78:
        Object r117 = Result.m2018constructorimpl(r5);     // Catch: Throwable -> L76
    L81:
        Throwable r118 = Result.m2021exceptionOrNullimpl(r117);
        if (r118 == null) goto L101;
        FileLog.e("Failed to close plugin instance " + r12, r118);
        throw th;
    L101:
        throw th;
    L76:
        th = move-exception;
        Result.Companion r13 = Result.Companion;
        r117 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L81
    }

    public final PluginsController.PluginValidationResult validatePluginFromFile(String r15) {
        if (new File(r15).exists() == false) goto L5;
        Map<String, String> r3 = parsePluginMetadata(r15);     // Catch: Throwable -> L17 PyException -> L19
        String r5 = r3.get("id");     // Catch: Throwable -> L17 PyException -> L19
        String r6 = r3.get("name");     // Catch: Throwable -> L17 PyException -> L19
        if (TextUtils.isEmpty(r5) == true) goto L103;
        if (TextUtils.isEmpty(r6) == true) goto L103;
        if (r5 == null) goto L101;
        if (new Regex("^[a-zA-Z][a-zA-Z0-9_-]{1,31}$").matches(r5) == false) goto L16;
        String r8 = findModuleNameOwner(r5);     // Catch: Throwable -> L17 PyException -> L19
        if (r8 != null) goto L24;
        String r2 = r3.get("app_version");     // Catch: Throwable -> L17 PyException -> L19
        if (r2 == null) goto L48;
        Matcher r11 = VERSION_PATTERN.matcher(r2);     // Catch: Throwable -> L17 PyException -> L19
        if (r11.matches() == false) goto L32;
        String r12 = r11.group(1);     // Catch: Throwable -> L17 PyException -> L19
        if (r12 == null) goto L36;
        String r112 = r11.group(2);     // Catch: Throwable -> L17 PyException -> L19
        if (r112 == null) goto L47;
        String r113 = StringsKt.trim(r112).toString();     // Catch: Throwable -> L17 PyException -> L19
        if (r113 == null) goto L47;
        if (AppUtils.compareVersions(r12, BuildVars.BUILD_VERSION_STRING, r113) == true) goto L48;
        return new PluginsController.PluginValidationResult(null, "Plugin requires app version " + r2 + ", but current is " + BuildVars.BUILD_VERSION_STRING);
    L47:
        return new PluginsController.PluginValidationResult(null, "Invalid appVersion: ".concat(r2));
    L36:
        return new PluginsController.PluginValidationResult(null, "Invalid appVersion: ".concat(r2));
    L32:
        return new PluginsController.PluginValidationResult(null, "Invalid appVersion: ".concat(r2));
    L48:
        String r1 = r3.get("sdk_version");     // Catch: Throwable -> L17 PyException -> L19
        if (r1 == null) goto L71;
        Matcher r114 = VERSION_PATTERN.matcher(r1);     // Catch: Throwable -> L17 PyException -> L19
        if (r114.matches() == false) goto L54;
        String r10 = r114.group(1);     // Catch: Throwable -> L17 PyException -> L19
        if (r10 == null) goto L58;
        String r9 = r114.group(2);     // Catch: Throwable -> L17 PyException -> L19
        if (r9 == null) goto L69;
        String r92 = StringsKt.trim(r9).toString();     // Catch: Throwable -> L17 PyException -> L19
        if (r92 == null) goto L69;
        if (AppUtils.compareVersions(r10, SDK_VERSION, r92) == true) goto L71;
        return new PluginsController.PluginValidationResult(null, "Plugin requires sdk version " + r1 + ", but current is " + SDK_VERSION);
    L69:
        return new PluginsController.PluginValidationResult(null, "Invalid sdkVersion: ".concat(r1));
    L58:
        return new PluginsController.PluginValidationResult(null, "Invalid sdkVersion: ".concat(r1));
    L54:
        return new PluginsController.PluginValidationResult(null, "Invalid sdkVersion: ".concat(r1));
    L71:
        if (r6 == null) goto L99;
        Plugin r0 = new Plugin(r5, r6);     // Catch: Throwable -> L17 PyException -> L19
        r0.setEngine(PluginsConstants.PYTHON);     // Catch: Throwable -> L17 PyException -> L19
        String r62 = r3.get("author");     // Catch: Throwable -> L17 PyException -> L19
        if (r62 != null) goto L75;
        r62 = LocaleController.getString(R.string.PluginNoAuthor);     // Catch: Throwable -> L17 PyException -> L19
    L75:
        r0.setAuthor(r62);     // Catch: Throwable -> L17 PyException -> L19
        String r63 = r3.get(MediaTrack.ROLE_DESCRIPTION);     // Catch: Throwable -> L17 PyException -> L19
        if (r63 != null) goto L78;
        r63 = LocaleController.getString(R.string.PluginNoDescription);     // Catch: Throwable -> L17 PyException -> L19
    L78:
        r0.setDescription(r63);     // Catch: Throwable -> L17 PyException -> L19
        r0.setIcon(r3.get("icon"));     // Catch: Throwable -> L17 PyException -> L19
        String r64 = r3.get("version");     // Catch: Throwable -> L17 PyException -> L19
        if (r64 != null) goto L81;
        r64 = "1.0";
    L81:
        r0.setVersion(r64);     // Catch: Throwable -> L17 PyException -> L19
        r0.setAppVersion(r2);     // Catch: Throwable -> L17 PyException -> L19
        r0.setSdkVersion(r1);     // Catch: Throwable -> L17 PyException -> L19
        String r13 = r3.get(Common.ASSET_REQUIREMENTS);     // Catch: Throwable -> L17 PyException -> L19
        if (r13 != null) goto L84;
    L96:
        r0.setEnabled(getPluginsController().getPreferences().getBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r5, false));     // Catch: Throwable -> L17 PyException -> L19
        return new PluginsController.PluginValidationResult(r0, null);
    L84:
        if (r13.length() == 0) goto L96;
        List<String> r14 = new Regex(",(?!\\s*[<>=!~(])").split(r13, 0);     // Catch: Throwable -> L17 PyException -> L19
        ArrayList r32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r14, 10));     // Catch: Throwable -> L17 PyException -> L19
        Iterator<T> r16 = r14.iterator();     // Catch: Throwable -> L17 PyException -> L19
    L88:
        if (r16.hasNext() == false) goto L90;
        r32.add(StringsKt.trim((String) r16.next()).toString());     // Catch: Throwable -> L17 PyException -> L19
        goto L88
    L90:
        ArrayList r17 = new ArrayList();     // Catch: Throwable -> L17 PyException -> L19
        int r65 = r32.size();     // Catch: Throwable -> L17 PyException -> L19
        int r7 = 0;
    L91:
        if (r7 >= r65) goto L95;
        Object r82 = r32.get(r7);     // Catch: Throwable -> L17 PyException -> L19
        r7 = r7 + 1;     // Catch: Throwable -> L17 PyException -> L19
        if (((String) r82).length() <= 0) goto L91;
        r17.add(r82);     // Catch: Throwable -> L17 PyException -> L19
        goto L91
    L95:
        r0.setRequirements(r17);     // Catch: Throwable -> L17 PyException -> L19
        goto L96
    L99:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Throwable -> L17 PyException -> L19
    L24:
        return new PluginsController.PluginValidationResult(null, "Plugin '__id__' is already used as a Python module name by " + r8 + ". Choose a different '__id__'.");
    L16:
        return new PluginsController.PluginValidationResult(null, "Plugin '__id__' must be 2-32 characters long, start with a letter, and contain only latin letters, numbers, dashes and underscores.");
    L101:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Throwable -> L17 PyException -> L19
    L103:
        return new PluginsController.PluginValidationResult(null, "Plugin metadata must contain non-empty '__id__' and '__name__'.");
    L19:
        e = move-exception;
        FileLog.e("Failed to parse metadata from " + r15 + ". Error: " + e.getMessage(), e);
        return new PluginsController.PluginValidationResult(null, e.getMessage());
    L17:
        th = move-exception;
        FileLog.e("Unexpected error validating plugin " + r15, th);
        return new PluginsController.PluginValidationResult(null, th.getMessage());
    L5:
        return new PluginsController.PluginValidationResult(null, "Plugin file not found.");
    }
}
