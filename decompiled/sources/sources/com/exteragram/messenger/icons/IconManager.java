package com.exteragram.messenger.icons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts$PickVisualMedia;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.IconCompat;
import com.caverock.androidsvg.SVG;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.IconPackType;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.icons.IconPackStorageResult;
import com.exteragram.messenger.icons.ui.components.InstallIconPackBottomSheet;
import com.exteragram.messenger.icons.ui.components.ReplaceIconBottomSheet;
import com.exteragram.messenger.icons.ui.picker.IconPickerController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.sun.jna.Callback;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.simplifiles.SimpliFiles;
import org.simplifiles.files.OverwritePolicy;
import org.simplifiles.files.SimpliDirectory;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000®\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0002¤\u0001\bÆ\u0002\u0018\u00002\u00020\u0001:\b½\u0001¾\u0001¿\u0001À\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ/\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J?\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u00192\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0012H\u0002¢\u0006\u0004\b \u0010\u0003J\u000f\u0010!\u001a\u00020\u0012H\u0002¢\u0006\u0004\b!\u0010\u0003J%\u0010$\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u00072\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH\u0002¢\u0006\u0004\b$\u0010%J!\u0010(\u001a\u0004\u0018\u00010'2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010&\u001a\u00020\fH\u0002¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0007H\u0002¢\u0006\u0004\b*\u0010+J)\u0010,\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b,\u0010-JM\u00103\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u00100\u001a\b\u0018\u00010.R\u00020/2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\f2\b\b\u0002\u00102\u001a\u00020\u0019H\u0002¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u000205H\u0007¢\u0006\u0004\b6\u00107J\u000f\u00109\u001a\u000208H\u0007¢\u0006\u0004\b9\u0010:J\u0017\u0010=\u001a\u00020\f2\u0006\u0010<\u001a\u00020;H\u0002¢\u0006\u0004\b=\u0010>J\u001f\u0010A\u001a\u00020\u00122\u0006\u0010@\u001a\u00020?2\u0006\u0010<\u001a\u00020;H\u0002¢\u0006\u0004\bA\u0010BJ\u0015\u0010D\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\f¢\u0006\u0004\bD\u0010EJ\r\u0010F\u001a\u00020\u0012¢\u0006\u0004\bF\u0010\u0003J-\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u00100\u001a\b\u0018\u00010.R\u00020/¢\u0006\u0004\bH\u0010IJ\u0017\u0010J\u001a\u0004\u0018\u00010G2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bJ\u0010KJ\u001f\u0010L\u001a\u0004\u0018\u00010G2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bL\u0010MJ\u001f\u0010N\u001a\u0004\u0018\u00010G2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bN\u0010MJ+\u0010Q\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010P\u001a\b\u0012\u0004\u0012\u00020G0O¢\u0006\u0004\bQ\u0010RJ#\u0010S\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010P\u001a\b\u0012\u0004\u0012\u00020G0O¢\u0006\u0004\bS\u0010TJ5\u0010W\u001a\u0004\u0018\u00010\u00172\u0006\u0010U\u001a\u00020\f2\u0006\u0010V\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u00100\u001a\b\u0018\u00010.R\u00020/¢\u0006\u0004\bW\u0010XJ/\u0010[\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020'2\b\u0010Z\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b[\u0010\\J\u001d\u0010]\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b]\u0010\u0014J\u0015\u0010^\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b^\u0010_J\u0017\u0010a\u001a\u00020\u00122\b\b\u0002\u0010`\u001a\u00020\u0019¢\u0006\u0004\ba\u0010bJ\r\u0010c\u001a\u00020\u0012¢\u0006\u0004\bc\u0010\u0003J\u0017\u0010d\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\f¢\u0006\u0004\bd\u0010eJ\u0017\u0010f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\bf\u0010gJ\u0017\u0010h\u001a\u0004\u0018\u00010'2\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\bh\u0010iJ\u0015\u0010k\u001a\u00020\u00192\u0006\u0010j\u001a\u00020\n¢\u0006\u0004\bk\u0010lJ\u0015\u0010m\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\bm\u0010eJ\u0017\u0010p\u001a\u00020\u00192\b\u0010o\u001a\u0004\u0018\u00010n¢\u0006\u0004\bp\u0010qJ\u001f\u0010p\u001a\u00020\u00192\u0006\u0010U\u001a\u00020\f2\b\u0010o\u001a\u0004\u0018\u00010n¢\u0006\u0004\bp\u0010rJ\u001d\u0010s\u001a\u00020\u00122\u0006\u0010@\u001a\u00020?2\u0006\u0010o\u001a\u00020n¢\u0006\u0004\bs\u0010tJ\u001d\u0010s\u001a\u00020\u00122\u0006\u0010@\u001a\u00020?2\u0006\u0010U\u001a\u00020\f¢\u0006\u0004\bs\u0010uJ'\u0010z\u001a\u00020\u00192\u0006\u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u00042\b\u0010y\u001a\u0004\u0018\u00010x¢\u0006\u0004\bz\u0010{J7\u0010\u0081\u0001\u001a\u00020\u00122\u0006\u0010}\u001a\u00020|2\u0006\u0010~\u001a\u00020\u00192\u0015\u0010P\u001a\u0011\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u0001\u0012\u0004\u0012\u00020\u00120\u007f¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J0\u0010\u0085\u0001\u001a\u00020\u00122\b\u0010\u0084\u0001\u001a\u00030\u0083\u00012\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010j\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u001a\u0010\u0089\u0001\u001a\u00020\u00192\b\u0010\u0088\u0001\u001a\u00030\u0087\u0001¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0091\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u0090\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0090\u0001R\u001e\u0010\u0094\u0001\u001a\t\u0012\u0004\u0012\u00020\f0\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R)\u0010\u0097\u0001\u001a\u000f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u0096\u00018\u0006¢\u0006\u0010\n\u0006\b\u0097\u0001\u0010\u0098\u0001\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001c\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009b\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0017\u0010\u009e\u0001\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0017\u0010 \u0001\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\b\n\u0006\b \u0001\u0010\u009f\u0001R\u0018\u0010¢\u0001\u001a\u00030¡\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010£\u0001R\u0018\u0010¥\u0001\u001a\u00030¤\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R\u001e\u0010¨\u0001\u001a\t\u0012\u0004\u0012\u00020\n0§\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¨\u0001\u0010©\u0001R$\u0010ª\u0001\u001a\u000f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0001\u0010\u0098\u0001R\u001c\u0010¬\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¬\u0001\u0010\u00ad\u0001R\u001c\u0010¯\u0001\u001a\u0005\u0018\u00010®\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¯\u0001\u0010°\u0001R\u001c\u0010±\u0001\u001a\u0005\u0018\u00010®\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b±\u0001\u0010°\u0001R\u0018\u0010³\u0001\u001a\u00030²\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b³\u0001\u0010´\u0001R\u0019\u0010µ\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R\u0019\u0010·\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b·\u0001\u0010¶\u0001R\u0019\u0010¸\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¸\u0001\u0010¹\u0001R-\u0010»\u0001\u001a\u0018\u0012\u0013\u0012\u0011\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u0001\u0012\u0004\u0012\u00020\u00120\u007f0º\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b»\u0001\u0010¼\u0001¨\u0006Á\u0001"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "resId", "density", _UrlKt.FRAGMENT_ENCODE_SET, "resolvedCacheKey", "(II)J", "Lcom/exteragram/messenger/icons/IconPack;", "pack", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", "Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "sourceCacheKey", "(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "packId", _UrlKt.FRAGMENT_ENCODE_SET, "invalidateIconCaches", "(Ljava/lang/String;I)V", "generation", "resourceName", "Landroid/graphics/Bitmap;", "bitmap", _UrlKt.FRAGMENT_ENCODE_SET, "publishBitmap", "(JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "packs", "syncInstalledCustomPacks", "(Ljava/util/List;)Z", "rebuildOwnerMap", "rebuildBasePreinstalledMap", "newActivePacks", "Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;", "updateActivePacks", "(JLjava/util/List;)Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;", "iconFileName", "Ljava/io/File;", "resolvePackIconFile", "(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;", "launchPrewarm", "(J)V", "cachedPackIconBitmap", "(Lcom/exteragram/messenger/icons/IconPack;II)Landroid/graphics/Bitmap;", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "theme", "knownResourceName", "cacheResult", "getPackIconBitmap", "(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;", "Landroidx/core/graphics/drawable/IconCompat;", "getNotificationIcon", "()Landroidx/core/graphics/drawable/IconCompat;", "Landroid/graphics/drawable/Icon;", "getNotificationSystemIcon", "()Landroid/graphics/drawable/Icon;", "Lcom/exteragram/messenger/icons/IconPackStorageError;", "error", "iconPackErrorText", "(Lcom/exteragram/messenger/icons/IconPackStorageError;)Ljava/lang/String;", "Lorg/telegram/ui/ActionBar/BaseFragment;", "baseFragment", "showIconPackError", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/icons/IconPackStorageError;)V", "name", "isBlacklisted", "(Ljava/lang/String;)Z", "prefetchCustomPacks", "Landroid/graphics/drawable/Drawable;", "getDrawable", "(IILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;", "getCachedDrawable", "(I)Landroid/graphics/drawable/Drawable;", "getPackIconDrawable", "(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;", "getCachedPackIconDrawable", "Lorg/telegram/messenger/Utilities$Callback;", Callback.METHOD_NAME, "requestPackIconDrawable", "(Lcom/exteragram/messenger/icons/IconPack;ILorg/telegram/messenger/Utilities$Callback;)V", "requestDrawable", "(ILorg/telegram/messenger/Utilities$Callback;)V", "path", "originalResId", "createBitmapFromFile", "(Ljava/lang/String;IILandroid/content/res/Resources$Theme;)Landroid/graphics/Bitmap;", "tempFile", "originalName", "saveCustomIcon", "(Ljava/lang/String;ILjava/io/File;Ljava/lang/String;)V", "resetCustomIcon", "getIcon", "(I)I", PluginsConstants.UPDATE, "initialize", "(Z)V", "awaitInitialization", "setActiveCustomPack", "(Ljava/lang/String;)V", "findPackById", "(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;", "bundlePackBlocking", "(Ljava/lang/String;)Ljava/io/File;", "iconPack", "saveIconPackMetadata", "(Lcom/exteragram/messenger/icons/IconPack;)Z", "deletePack", "Lorg/telegram/messenger/MessageObject;", "messageObject", "isIconPack", "(Lorg/telegram/messenger/MessageObject;)Z", "(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;)Z", "handleIconPack", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/messenger/MessageObject;)V", "(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;)V", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "onActivityResult", "(IILandroid/content/Intent;)Z", "Landroid/app/Activity;", "activity", "selectFromFiles", "Lkotlin/Function1;", "Landroid/net/Uri;", "startIconPicker", "(Landroid/app/Activity;ZLkotlin/jvm/functions/Function1;)V", "Landroid/content/Context;", "context", "showReplaceAlert", "(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V", "Lcom/exteragram/messenger/IconPackType;", "basePackType", "isBasePackOnly", "(Lcom/exteragram/messenger/IconPackType;)Z", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/CoroutineDispatcher;", "mutationDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "decodeDispatcher", "prewarmDispatcher", _UrlKt.FRAGMENT_ENCODE_SET, "blacklistedIcons", "Ljava/util/Set;", "Ljava/util/concurrent/ConcurrentHashMap;", "systemIcons", "Ljava/util/concurrent/ConcurrentHashMap;", "getSystemIcons", "()Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;", "systemNames", "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;", "maxMemory", "I", "cacheSize", "Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;", "resolvedCache", "Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;", "com/exteragram/messenger/icons/IconManager$sourceCache$1", "sourceCache", "Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "activePacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "iconOwnerMap", "Landroid/util/SparseIntArray;", "basePreinstalledMap", "Landroid/util/SparseIntArray;", "Lkotlinx/coroutines/Job;", "initializationJob", "Lkotlinx/coroutines/Job;", "prewarmJob", "Ljava/util/concurrent/CountDownLatch;", "initialPacksLoaded", "Ljava/util/concurrent/CountDownLatch;", "activePacksPublished", "Z", "initializationTimedOut", "initializationGeneration", "J", "Landroid/util/SparseArray;", "resultCallbacks", "Landroid/util/SparseArray;", "IntStringMap", "SourceCacheKey", "ResolvedIndex", "ActivePacksUpdate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 SparseIntArray.kt\nandroidx/core/util/SparseIntArrayKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n+ 6 Bitmap.kt\nandroidx/core/graphics/BitmapKt\n+ 7 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,1058:1\n777#2:1059\n873#2,2:1060\n1915#2,2:1062\n296#2,2:1084\n296#2,2:1086\n2792#2,3:1088\n25#3:1064\n56#3:1065\n1#4:1066\n27#5:1067\n27#5:1068\n27#5:1069\n27#5:1070\n27#5:1071\n27#5:1072\n83#6,6:1073\n71#6:1079\n184#7,2:1080\n184#7,2:1082\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager\n*L\n285#1:1059\n285#1:1060,2\n286#1:1062,2\n1045#1:1084,2\n1047#1:1086,2\n1055#1:1088,3\n355#1:1064\n362#1:1065\n422#1:1067\n437#1:1068\n448#1:1069\n453#1:1070\n464#1:1071\n470#1:1072\n547#1:1073,6\n584#1:1079\n721#1:1080,2\n749#1:1082,2\n*E\n"})
/* loaded from: classes.dex */
public final class IconManager {
    public static final IconManager INSTANCE = null;
    private static final CopyOnWriteArrayList<IconPack> activePacks = null;
    private static boolean activePacksPublished;
    private static volatile SparseIntArray basePreinstalledMap;
    private static final Set<String> blacklistedIcons = null;
    private static final int cacheSize = 0;
    private static final CoroutineDispatcher decodeDispatcher = null;
    private static final ConcurrentHashMap<String, IconPack> iconOwnerMap = null;
    private static final CountDownLatch initialPacksLoaded = null;
    private static volatile long initializationGeneration;
    private static Job initializationJob;
    private static volatile boolean initializationTimedOut;
    private static final int maxMemory = 0;
    private static final CoroutineDispatcher mutationDispatcher = null;
    private static final CoroutineDispatcher prewarmDispatcher = null;
    private static Job prewarmJob;
    private static final ResolvedIndex resolvedCache = null;
    private static final SparseArray<Function1<Uri, Unit>> resultCallbacks = null;
    private static final CoroutineScope scope = null;
    private static final IconManager$sourceCache$1 sourceCache = null;
    private static final ConcurrentHashMap<String, Integer> systemIcons = null;
    private static volatile IntStringMap systemNames;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "STALE", "UNCHANGED", "CHANGED", "INITIAL", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum ActivePacksUpdate extends Enum<ActivePacksUpdate> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ActivePacksUpdate[] $VALUES = null;
        public static final ActivePacksUpdate CHANGED = null;
        public static final ActivePacksUpdate INITIAL = null;
        public static final ActivePacksUpdate STALE = null;
        public static final ActivePacksUpdate UNCHANGED = null;

        private static final /* synthetic */ ActivePacksUpdate[] $values() {
            return new ActivePacksUpdate[]{STALE, UNCHANGED, CHANGED, INITIAL};
        }

        static {
            STALE = new ActivePacksUpdate("STALE", 0);
            UNCHANGED = new ActivePacksUpdate("UNCHANGED", 1);
            CHANGED = new ActivePacksUpdate("CHANGED", 2);
            INITIAL = new ActivePacksUpdate("INITIAL", 3);
            ActivePacksUpdate[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ActivePacksUpdate(String r1, int r2) {
        }

        public static ActivePacksUpdate valueOf(String r1) {
            return (ActivePacksUpdate) Enum.valueOf(ActivePacksUpdate.class, r1);
        }

        public static ActivePacksUpdate[] values() {
            return (ActivePacksUpdate[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nJ\b\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$IntStringMap;", _UrlKt.FRAGMENT_ENCODE_SET, "expected", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "keys", _UrlKt.FRAGMENT_ENCODE_SET, "values", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", PluginsConstants.Settings.MASK, "shift", "size", "allocate", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "get", PluginsConstants.Settings.KEY, "put", "value", "grow", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class IntStringMap {
        public static final Companion Companion = null;
        private int[] keys;
        private int mask;
        private int shift;
        private int size;
        private String[] values;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$IntStringMap$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "GOLDEN", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
        }

        public IntStringMap(int r3) {
            this.keys = new int[0];
            this.values = new String[0];
            int r0 = 4;
        L4:
            if (r0 >= (r3 * 2)) goto L6;
            r0 = r0 << 1;
            goto L4
        L6:
            allocate(r0);
        }

        private final void allocate(int r2) {
            this.keys = new int[r2];
            this.values = new String[r2];
            this.mask = r2 - 1;
            this.shift = 32 - Integer.numberOfTrailingZeros(r2);
        }

        private final void grow() {
            int[] r0 = this.keys;
            String[] r1 = this.values;
            allocate(r0.length << 1);
            int r2 = 0;
            this.size = 0;
            int r3 = r0.length;
        L3:
            if (r2 >= r3) goto L8;
            int r4 = r0[r2];
            if (r4 == 0) goto L7;
            put(r4, r1[r2]);
        L7:
            r2 = r2 + 1;
            goto L3
        }

        public final String get(int r4) {
            if (r4 != 0) goto L5;
            return null;
        L5:
            int r1 = ((-1640531527) * r4) >>> this.shift;
        L6:
            int r2 = this.keys[r1];
            if (r2 == r4) goto L9;
            if (r2 == 0) goto L11;
            r1 = (r1 + 1) & this.mask;
            goto L6
        L11:
            return null;
        L9:
            return this.values[r1];
        }

        public final void put(int r4, String r5) {
            if (r4 != 0) goto L5;
            return;
        L5:
            if (((this.size + 1) * 2) <= this.keys.length) goto L7;
            grow();
        L7:
            int r0 = ((-1640531527) * r4) >>> this.shift;
        L8:
            int[] r1 = this.keys;
            int r2 = r1[r0];
            if (r2 == 0) goto L10;
            if (r2 == r4) goto L13;
            r0 = (r0 + 1) & this.mask;
            goto L8
        L13:
            this.values[r0] = r5;
            return;
        L10:
            r1[r0] = r4;
            this.values[r0] = r5;
            this.size++;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "entries", "Landroid/util/LongSparseArray;", "Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "get", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "put", _UrlKt.FRAGMENT_ENCODE_SET, "value", "clear", "removeByResId", "resId", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ResolvedIndex {
        private final int capacity;
        private final LongSparseArray<SourceCacheKey> entries;

        public ResolvedIndex(int r1) {
            this.capacity = r1;
            this.entries = new LongSparseArray();
        }

        public final synchronized void clear() {
            monitor-enter(this);
            this.entries.clear();     // Catch: Throwable -> L6
            monitor-exit(this);
            return;
        L6:
            th = move-exception;
            throw th;
        }

        public final synchronized SourceCacheKey get(long r2) {
            monitor-enter(this);
            SourceCacheKey r22 = this.entries.get(r2);     // Catch: Throwable -> L6
            monitor-exit(this);
            return r22;
        L6:
            th = move-exception;
            throw th;
        }

        public final synchronized void put(long r3, SourceCacheKey r5) {
            monitor-enter(this);
        L6:
            th = move-exception;
            throw th;
        L4:
            if (this.entries.size() < this.capacity) goto L8;
            this.entries.clear();     // Catch: Throwable -> L6
        L8:
            this.entries.put(r3, r5);     // Catch: Throwable -> L6
            monitor-exit(this);
        }

        public final synchronized void removeByResId(int r5) {
            monitor-enter(this);
            int r0 = this.entries.size();     // Catch: Throwable -> L10
        L4:
            r0 = r0 - 1;
            if ((-1) >= r0) goto L13;
            if (((int) (this.entries.keyAt(r0) >> 32)) != r5) goto L4;
            this.entries.removeAt(r0);     // Catch: Throwable -> L10
            goto L4
        L13:
            monitor-exit(this);
            return;
        L10:
            th = move-exception;
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\r\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001a\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001b\u0010\u000eR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b\u001d\u0010\u0010R\u0017\u0010\n\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u001c\u001a\u0004\b\u001e\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "generation", _UrlKt.FRAGMENT_ENCODE_SET, "packId", "location", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "resId", "density", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "J", "getGeneration", "()J", "Ljava/lang/String;", "getPackId", "getLocation", "getFileName", "I", "getResId", "getDensity", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SourceCacheKey {
        private final int density;
        private final String fileName;
        private final long generation;
        private final String location;
        private final String packId;
        private final int resId;

        public SourceCacheKey(long r1, String r3, String r4, String r5, int r6, int r7) {
            this.generation = r1;
            this.packId = r3;
            this.location = r4;
            this.fileName = r5;
            this.resId = r6;
            this.density = r7;
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof SourceCacheKey) == true) goto L8;
            return false;
        L8:
            SourceCacheKey r82 = (SourceCacheKey) r8;
            if (this.generation == r82.generation) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.packId, r82.packId) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.location, r82.location) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.fileName, r82.fileName) == true) goto L21;
            return false;
        L21:
            if (this.resId == r82.resId) goto L24;
            return false;
        L24:
            if (this.density == r82.density) goto L26;
            return false;
        L26:
            return true;
        }

        public final String getPackId() {
            return this.packId;
        }

        public final int getResId() {
            return this.resId;
        }

        public int hashCode() {
            int r0 = ((Long.hashCode(this.generation) * 31) + this.packId.hashCode()) * 31;
            String r1 = this.location;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return ((((((r0 + r12) * 31) + this.fileName.hashCode()) * 31) + Integer.hashCode(this.resId)) * 31) + Integer.hashCode(this.density);
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public String toString() {
            return "SourceCacheKey(generation=" + this.generation + ", packId=" + this.packId + ", location=" + this.location + ", fileName=" + this.fileName + ", resId=" + this.resId + ", density=" + this.density + ")";
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[IconPackStorageError.values().length];
            r0[IconPackStorageError.INVALID_ARCHIVE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
        L27:
            r0[IconPackStorageError.MISSING_METADATA.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
        L43:
            r0[IconPackStorageError.METADATA_TOO_LARGE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L17
        L31:
            r0[IconPackStorageError.INVALID_METADATA.ordinal()] = 4;     // Catch: NoSuchFieldError -> L18
        L35:
            r0[IconPackStorageError.TOO_MANY_FILES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L19
        L25:
            r0[IconPackStorageError.ARCHIVE_TOO_LARGE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L20
        L37:
            r0[IconPackStorageError.FILE_TOO_LARGE.ordinal()] = 7;     // Catch: NoSuchFieldError -> L21
        L39:
            r0[IconPackStorageError.COMPRESSION_RATIO_TOO_HIGH.ordinal()] = 8;     // Catch: NoSuchFieldError -> L22
        L29:
            r0[IconPackStorageError.STORAGE_ERROR.ordinal()] = 9;     // Catch: NoSuchFieldError -> L23
        L33:
            r0[IconPackStorageError.UNKNOWN.ordinal()] = 10;     // Catch: NoSuchFieldError -> L24
        L13:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$deletePack$1", f = "IconManager.kt", i = {}, l = {928}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconManager$deletePack$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $packId;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$deletePack$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.exteragram.messenger.icons.IconManager$deletePack$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $packId;
            int label;

            public C00181(String r1, Continuation<? super C00181> r2) {
                this.$packId = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
                return new C00181(this.$packId, r2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((C00181) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L11;
                ResultKt.throwOnFailure(r2);
                if (ExteraConfig.getIconPacksLayout().contains(this.$packId) == false) goto L7;
            L8:
                ExteraConfig.getIconPacksLayout().remove(this.$packId);
                ExteraConfig.getIconPacksHidden().remove(this.$packId);
                ExteraConfig.saveIconPacksLayout();
            L9:
                IconManager.INSTANCE.initialize(true);
                return Unit.INSTANCE;
            L7:
                if (ExteraConfig.getIconPacksHidden().contains(this.$packId) == false) goto L9;
            L11:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public AnonymousClass1(String r1, Continuation<? super AnonymousClass1> r2) {
            this.$packId = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.$packId, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            IconPackStorage.INSTANCE.deletePack(this.$packId);
            MainCoroutineDispatcher r62 = Dispatchers.getMain();
            C00181 r12 = new C00181(this.$packId, null);
            this.label = 1;
            if (BuildersKt.withContext(r62, r12, this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1", f = "IconManager.kt", i = {0, 1, 1}, l = {973, 974}, m = "invokeSuspend", n = {"file", "file", "packResult"}, s = {"L$0", "L$0", "L$1"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$handleIconPack$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseFragment $baseFragment;
        final /* synthetic */ String $path;
        Object L$0;
        Object L$1;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.exteragram.messenger.icons.IconManager$handleIconPack$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ BaseFragment $baseFragment;
            final /* synthetic */ File $file;
            final /* synthetic */ IconPackStorageResult<IconPack> $packResult;
            int label;

            /* renamed from: $r8$lambda$f6uYi-7_KIxph7kqQsGpPNgvjfM, reason: not valid java name */
            public static void m1440$r8$lambda$f6uYi7_KIxph7kqQsGpPNgvjfM(File r8, BaseFragment r9, IconPack r10, boolean r11, boolean r12) {
                BuildersKt.launch$default(IconManager.access$getScope$p(), null, null, new IconManager$handleIconPack$1$1$bottomSheet$1$1(r8, r9, r12, r10, r11, null), 3, null);
            }

            public C00191(IconPackStorageResult<IconPack> r1, BaseFragment r2, File r3, Continuation<? super C00191> r4) {
                this.$packResult = r1;
                this.$baseFragment = r2;
                this.$file = r3;
                super(2, r4);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                return new C00191(this.$packResult, this.$baseFragment, this.$file, r4);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((C00191) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r6) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L15;
                ResultKt.throwOnFailure(r6);
                IconPackStorageResult<IconPack> r62 = this.$packResult;
                if ((r62 instanceof IconPackStorageResult.Success) == false) goto L8;
                IconPack r63 = (IconPack) ((IconPackStorageResult.Success) r62).getValue();
                InstallIconPackBottomSheet r0 = new InstallIconPackBottomSheet(this.$baseFragment.getParentActivity(), r63, new IconManager$handleIconPack$1$1$$ExternalSyntheticLambda0(this.$file, this.$baseFragment, r63));
                this.$baseFragment.showDialog(r0);
            L11:
                return Unit.INSTANCE;
            L8:
                if ((r62 instanceof IconPackStorageResult.Failure) == false) goto L12;
                IconManager.access$showIconPackError(IconManager.INSTANCE, this.$baseFragment, ((IconPackStorageResult.Failure) r62).getError());
                goto L11
            L12:
                LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
                return null;
            L15:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01411(String r1, BaseFragment r2, Continuation<? super C01411> r3) {
            this.$path = r1;
            this.$baseFragment = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new C01411(this.$path, this.$baseFragment, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01411) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            IconPackStorageResult r02 = (IconPackStorageResult) this.L$1;
            File r7 = (File) this.L$0;
            ResultKt.throwOnFailure(r8);
        L17:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            File r12 = (File) this.L$0;
            ResultKt.throwOnFailure(r8);
        L13:
            IconPackStorageResult r82 = (IconPackStorageResult) r8;
            MainCoroutineDispatcher r3 = Dispatchers.getMain();
            C00191 r4 = new C00191(r82, this.$baseFragment, r12, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r12);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r82);
            this.label = 2;
            if (BuildersKt.withContext(r3, r4, this) != r0) goto L17;
        L15:
            return r0;
        L10:
            ResultKt.throwOnFailure(r8);
            r12 = new File(this.$path);
            IconPackStorage r83 = IconPackStorage.INSTANCE;
            this.L$0 = r12;
            this.label = 1;
            r8 = r83.parsePackFromZip(r12, this);
            if (r8 != r0) goto L13;
            goto L13
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$initialize$1", f = "IconManager.kt", i = {0, 1}, l = {820, 834}, m = "invokeSuspend", n = {"result", "result"}, s = {"L$0", "L$0"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$initialize$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $generation;
        final /* synthetic */ boolean $update;
        Object L$0;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$initialize$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.exteragram.messenger.icons.IconManager$initialize$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C00201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ long $generation;
            int label;

            public C00201(long r1, Continuation<? super C00201> r3) {
                this.$generation = r1;
                super(2, r3);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                return new C00201(this.$generation, r4);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((C00201) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r3) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L10;
                ResultKt.throwOnFailure(r3);
                if (this.$generation != IconManager.access$getInitializationGeneration$p()) goto L7;
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.iconPackUpdated, new Object[0]);
                return Unit.INSTANCE;
            L7:
                return Unit.INSTANCE;
            L10:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$initialize$1$2", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$initialize$1$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1058:1\n296#2,2:1059\n296#2,2:1061\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$initialize$1$2\n*L\n839#1:1059,2\n840#1:1061,2\n*E\n"})
        /* renamed from: com.exteragram.messenger.icons.IconManager$initialize$1$2, reason: invalid class name */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ long $generation;
            final /* synthetic */ ActivePacksUpdate $result;
            int label;

            public AnonymousClass2(long r1, ActivePacksUpdate r3, Continuation<? super AnonymousClass2> r4) {
                this.$generation = r1;
                this.$result = r3;
                super(2, r4);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                return new AnonymousClass2(this.$generation, this.$result, r4);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r7) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                LaunchActivity r1 = null;
                if (this.label != 0) goto L67;
                ResultKt.throwOnFailure(r7);
                if (this.$generation == IconManager.access$getInitializationGeneration$p()) goto L9;
                return Unit.INSTANCE;
            L9:
                if (ExteraConfig.getEditingIconPackId() == null) goto L47;
                Iterator r72 = IconManager.access$getActivePacks$p().iterator();
            L12:
                if (r72.hasNext() == false) goto L16;
                Object r3 = r72.next();
                if (Intrinsics.areEqual(((IconPack) r3).getId(), ExteraConfig.getEditingIconPackId()) == false) goto L12;
            L17:
                IconPack r32 = (IconPack) r3;
                Iterator r73 = IconManager.access$getActivePacks$p().iterator();
            L19:
                if (r73.hasNext() == false) goto L23;
                Object r4 = r73.next();
                if (((IconPack) r4).isBase() == true) goto L19;
            L24:
                IconPack r42 = (IconPack) r4;
                if (r32 == null) goto L40;
                String r74 = r32.getId();
                if (r42 == null) goto L29;
                String r33 = r42.getId();
            L31:
                if (Intrinsics.areEqual(r74, r33) == false) goto L40;
                BaseFragment r75 = LaunchActivity.getSafeLastFragment();
                if (r75 == null) goto L47;
                Activity r76 = r75.getParentActivity();
                if (r76 == null) goto L47;
                if ((r76 instanceof LaunchActivity) == false) goto L47;
                IconPickerController.setActive((LaunchActivity) r76, true);
                goto L47
            L29:
                r33 = null;
            L40:
                ExteraConfig.setEditingIconPackId(null);
                BaseFragment r77 = LaunchActivity.getSafeLastFragment();
                if (r77 == null) goto L47;
                Activity r78 = r77.getParentActivity();
                if (r78 == null) goto L47;
                if ((r78 instanceof LaunchActivity) == false) goto L47;
                IconPickerController.setActive((LaunchActivity) r78, false);
                goto L47
            L23:
                r4 = null;
                goto L24
            L16:
                r3 = null;
            L47:
                ActivePacksUpdate r79 = this.$result;
                ActivePacksUpdate r34 = ActivePacksUpdate.INITIAL;
                if (r79 == r34) goto L50;
            L53:
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.iconPackUpdated, new Object[0]);
                BaseFragment r710 = LaunchActivity.getSafeLastFragment();
                if (r710 == null) goto L66;
                ActivePacksUpdate r6 = this.$result;
                Theme.reloadAllResources(r710.getParentActivity());
                if (r6 != r34) goto L62;
                Activity r62 = r710.getParentActivity();
                if ((r62 instanceof LaunchActivity) == false) goto L60;
                r1 = (LaunchActivity) r62;
            L60:
                if (r1 == null) goto L66;
                r1.rebuildAllFragments(true);
                goto L66
            L62:
                INavigationLayout r63 = r710.getParentLayout();
                if (r63 == null) goto L66;
                r63.rebuildFragments(0);
            L66:
                return Unit.INSTANCE;
            L50:
                if (IconManager.access$getInitializationTimedOut$p() == true) goto L53;
                return Unit.INSTANCE;
            L67:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        /* renamed from: com.exteragram.messenger.icons.IconManager$initialize$1$WhenMappings */
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[ActivePacksUpdate.values().length];
                r0[ActivePacksUpdate.STALE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
            L13:
                r0[ActivePacksUpdate.UNCHANGED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
            L19:
                r0[ActivePacksUpdate.CHANGED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
            L15:
                r0[ActivePacksUpdate.INITIAL.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
            L7:
                $EnumSwitchMapping$0 = r0;
            }
        }

        public C01421(long r1, boolean r3, Continuation<? super C01421> r4) {
            this.$generation = r1;
            this.$update = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01421(this.$generation, this.$update, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01421) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r11) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            ActivePacksUpdate r02 = (ActivePacksUpdate) this.L$0;
            ResultKt.throwOnFailure(r11);
        L52:
            IconManager.access$launchPrewarm(IconManager.INSTANCE, this.$generation);
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ActivePacksUpdate r03 = (ActivePacksUpdate) this.L$0;
            ResultKt.throwOnFailure(r11);
        L59:
            IconManager.access$launchPrewarm(IconManager.INSTANCE, this.$generation);
            return Unit.INSTANCE;
        L10:
            ResultKt.throwOnFailure(r11);
            ExteraConfig.loadConfig();     // Catch: Throwable -> L63
            ArrayList r112 = new ArrayList();     // Catch: Throwable -> L63
            Iterator<String> r12 = ExteraConfig.getIconPacksLayout().iterator();     // Catch: Throwable -> L63
        L13:
            if (r12.hasNext() == false) goto L21;
            String r5 = r12.next();     // Catch: Throwable -> L63
            if (StringsKt.startsWith$default(r5, "base.", false, 2, null) == false) goto L17;
            IconPack r52 = BaseIconPacks.INSTANCE.getBasePack(r5);     // Catch: Throwable -> L63
        L18:
            if (r52 == null) goto L13;
            r112.add(r52);     // Catch: Throwable -> L63
            goto L13
        L17:
            r52 = IconPackStorage.INSTANCE.findPackById(r5);     // Catch: Throwable -> L63
            goto L18
        L21:
            if (this.$generation == IconManager.access$getInitializationGeneration$p()) goto L25;
            Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L63
            IconManager.access$getInitialPacksLoaded$p().countDown();
            return r10;
        L25:
            IconManager r13 = IconManager.INSTANCE;     // Catch: Throwable -> L63
            ActivePacksUpdate r113 = IconManager.access$updateActivePacks(r13, this.$generation, r112);     // Catch: Throwable -> L63
            IconManager.access$getInitialPacksLoaded$p().countDown();
            if (r13.getSystemIcons().isEmpty() == true) goto L66;
        L39:
            int r14 = WhenMappings.$EnumSwitchMapping$0[r113.ordinal()];
            if (r14 == 1) goto L62;
            if (r14 == 2) goto L55;
            if (r14 != 3) goto L45;
        L49:
            MainCoroutineDispatcher r15 = Dispatchers.getMain();
            AnonymousClass2 r2 = new AnonymousClass2(this.$generation, r113, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r113);
            this.label = 2;
            if (BuildersKt.withContext(r15, r2, this) != r0) goto L52;
        L58:
            return r0;
        L45:
            if (r14 == 4) goto L49;
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L55:
            if (this.$update == false) goto L59;
            IconManager.access$getResolvedCache$p().clear();
            IconManager.access$getSourceCache$p().evictAll();
            MainCoroutineDispatcher r16 = Dispatchers.getMain();
            C00201 r4 = new C00201(this.$generation, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r113);
            this.label = 1;
            if (BuildersKt.withContext(r16, r4, this) != r0) goto L59;
        L62:
            return Unit.INSTANCE;
        L66:
            Field[] r17 = R.drawable.class.getFields();     // Catch: Exception -> L34
            IntStringMap r53 = new IntStringMap(2048);     // Catch: Exception -> L34
            Iterator r18 = ArrayIteratorKt.iterator(r17);     // Catch: Exception -> L34
        L30:
            if (r18.hasNext() == false) goto L36;
            Field r6 = (Field) r18.next();     // Catch: Exception -> L34
            String r7 = r6.getName();     // Catch: Exception -> L34
            IconManager r8 = IconManager.INSTANCE;     // Catch: Exception -> L34
            if (r8.isBlacklisted(r7) == true) goto L30;
            int r62 = r6.getInt(null);     // Catch: Exception -> L34
            r8.getSystemIcons().put(r7, Boxing.boxInt(r62));     // Catch: Exception -> L34
            r53.put(r62, r7);     // Catch: Exception -> L34
            goto L30
        L36:
            IconManager.access$setSystemNames$p(r53);     // Catch: Exception -> L34
        L34:
            e = move-exception;
            FileLog.e(e);
        L63:
            th = move-exception;
            IconManager.access$getInitialPacksLoaded$p().countDown();
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$launchPrewarm$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconManager$launchPrewarm$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $generation;
        private /* synthetic */ Object L$0;
        int label;

        public C01431(long r1, Continuation<? super C01431> r3) {
            this.$generation = r1;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            C01431 r0 = new C01431(this.$generation, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01431) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r19) {
            CoroutineScope r1 = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L38;
            ResultKt.throwOnFailure(r19);
            int r6 = AndroidUtilities.displayMetrics.densityDpi;
            long r11 = SystemClock.elapsedRealtime() + 150;
            Iterator r2 = IconManager.access$getIconOwnerMap$p().entrySet().iterator();
            int r3 = 0;
        L6:
            if (r2.hasNext() == false) goto L37;
            Map.Entry r4 = (Map.Entry) r2.next();
            String r8 = (String) r4.getKey();
            IconPack r42 = (IconPack) r4.getValue();
            if (CoroutineScopeKt.isActive(r1) == false) goto L35;
            if (this.$generation != IconManager.access$getInitializationGeneration$p()) goto L35;
            IconManager r5 = IconManager.INSTANCE;
            Integer r7 = r5.getSystemIcons().get(r8);
            if (r7 == null) goto L6;
            int r72 = r7.intValue();
            int r13 = r3 + 1;
            if (r3 >= 256) goto L17;
            if (SystemClock.elapsedRealtime() >= r11) goto L21;
            Bitmap r10 = IconManager.access$getPackIconBitmap(r5, r42, r72, r6, null, r8, false);
            if (r10 != null) goto L26;
        L31:
            r3 = r13;
            goto L6
        L26:
            if (CoroutineScopeKt.isActive(r1) == false) goto L33;
            if (this.$generation != IconManager.access$getInitializationGeneration$p()) goto L33;
            int r9 = r6;
            IconManager.access$publishBitmap(r5, this.$generation, r8, r42, r72, r9, r10);
            r6 = r9;
        L33:
            return Unit.INSTANCE;
        L21:
            return Unit.INSTANCE;
        L17:
            return Unit.INSTANCE;
        L35:
            return Unit.INSTANCE;
        L37:
            return Unit.INSTANCE;
        L38:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$prefetchCustomPacks$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconManager$prefetchCustomPacks$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public C01441(Continuation<? super C01441> r2) {
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01441(r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01441) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r1) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r1);
            IconManager.access$syncInstalledCustomPacks(IconManager.INSTANCE, IconPackStorage.INSTANCE.getCustomPacks());
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestDrawable$2", f = "IconManager.kt", i = {0}, l = {490}, m = "invokeSuspend", n = {"drawable"}, s = {"L$0"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$requestDrawable$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Utilities.Callback<Drawable> $callback;
        final /* synthetic */ int $density;
        final /* synthetic */ int $resId;
        Object L$0;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestDrawable$2$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.exteragram.messenger.icons.IconManager$requestDrawable$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Utilities.Callback<Drawable> $callback;
            final /* synthetic */ Drawable $drawable;
            int label;

            public AnonymousClass1(Utilities.Callback<Drawable> r1, Drawable r2, Continuation<? super AnonymousClass1> r3) {
                this.$callback = r1;
                this.$drawable = r2;
                super(2, r3);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                return new AnonymousClass1(this.$callback, this.$drawable, r3);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L6;
                ResultKt.throwOnFailure(r2);
                this.$callback.run(this.$drawable);
                return Unit.INSTANCE;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public AnonymousClass2(int r1, int r2, Utilities.Callback<Drawable> r3, Continuation<? super AnonymousClass2> r4) {
            this.$resId = r1;
            this.$density = r2;
            this.$callback = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new AnonymousClass2(this.$resId, this.$density, this.$callback, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            Drawable r6 = (Drawable) this.L$0;
            ResultKt.throwOnFailure(r7);
        L16:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            Drawable r72 = IconManager.INSTANCE.getDrawable(this.$resId, this.$density, null);
            if (r72 == null) goto L11;
            MainCoroutineDispatcher r12 = Dispatchers.getMain();
            AnonymousClass1 r4 = new AnonymousClass1(this.$callback, r72, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r72);
            this.label = 1;
            if (BuildersKt.withContext(r12, r4, this) != r0) goto L16;
            return r0;
        L11:
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestPackIconDrawable$2", f = "IconManager.kt", i = {0}, l = {475}, m = "invokeSuspend", n = {"bitmap"}, s = {"L$0"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$requestPackIconDrawable$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01452 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Utilities.Callback<Drawable> $callback;
        final /* synthetic */ int $density;
        final /* synthetic */ IconPack $pack;
        final /* synthetic */ int $resId;
        Object L$0;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestPackIconDrawable$2$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$requestPackIconDrawable$2$1\n+ 2 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n*L\n1#1,1058:1\n27#2:1059\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$requestPackIconDrawable$2$1\n*L\n476#1:1059\n*E\n"})
        /* renamed from: com.exteragram.messenger.icons.IconManager$requestPackIconDrawable$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Bitmap $bitmap;
            final /* synthetic */ Utilities.Callback<Drawable> $callback;
            int label;

            public AnonymousClass1(Utilities.Callback<Drawable> r1, Bitmap r2, Continuation<? super AnonymousClass1> r3) {
                this.$callback = r1;
                this.$bitmap = r2;
                super(2, r3);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                return new AnonymousClass1(this.$callback, this.$bitmap, r3);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r3) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L6;
                ResultKt.throwOnFailure(r3);
                Utilities.Callback<Drawable> r32 = this.$callback;
                Bitmap r2 = this.$bitmap;
                r32.run(new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r2));
                return Unit.INSTANCE;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01452(IconPack r1, int r2, int r3, Utilities.Callback<Drawable> r4, Continuation<? super C01452> r5) {
            this.$pack = r1;
            this.$resId = r2;
            this.$density = r3;
            this.$callback = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new C01452(this.$pack, this.$resId, this.$density, this.$callback, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01452) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r14) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            Bitmap r13 = (Bitmap) this.L$0;
            ResultKt.throwOnFailure(r14);
        L16:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r14);
            Bitmap r142 = IconManager.getPackIconBitmap$default(IconManager.INSTANCE, this.$pack, this.$resId, this.$density, null, null, false, 48, null);
            if (r142 == null) goto L11;
            MainCoroutineDispatcher r12 = Dispatchers.getMain();
            AnonymousClass1 r4 = new AnonymousClass1(this.$callback, r142, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r142);
            this.label = 1;
            if (BuildersKt.withContext(r12, r4, this) != r0) goto L16;
            return r0;
        L11:
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$resetCustomIcon$1", f = "IconManager.kt", i = {0, 0, 0, 0, 0}, l = {692}, m = "invokeSuspend", n = {"$this$launch", "packToEdit", "iconFileName", "updatedMap", "updatedPack"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$resetCustomIcon$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1058:1\n1#2:1059\n*E\n"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$resetCustomIcon$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $packId;
        final /* synthetic */ int $resId;
        final /* synthetic */ String $resourceName;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$resetCustomIcon$1$3", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$resetCustomIcon$1$3\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1058:1\n363#2,7:1059\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$resetCustomIcon$1$3\n*L\n693#1:1059,7\n*E\n"})
        /* renamed from: com.exteragram.messenger.icons.IconManager$resetCustomIcon$1$3, reason: invalid class name */
        public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $resId;
            final /* synthetic */ IconPack $updatedPack;
            int label;

            public AnonymousClass3(IconPack r1, int r2, Continuation<? super AnonymousClass3> r3) {
                this.$updatedPack = r1;
                this.$resId = r2;
                super(2, r3);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                return new AnonymousClass3(this.$updatedPack, this.$resId, r3);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((AnonymousClass3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r7) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L16;
                ResultKt.throwOnFailure(r7);
                CopyOnWriteArrayList r72 = IconManager.access$getActivePacks$p();
                IconPack r0 = this.$updatedPack;
                Iterator r73 = r72.iterator();
                int r2 = 0;
            L6:
                if (r73.hasNext() == false) goto L11;
                if (Intrinsics.areEqual(((IconPack) r73.next()).getId(), r0.getId()) == true) goto L12;
                r2 = r2 + 1;
            L12:
                if (r2 == (-1)) goto L14;
                IconManager.access$getActivePacks$p().set(r2, this.$updatedPack);
                IconManager r74 = IconManager.INSTANCE;
                IconManager.access$rebuildOwnerMap(r74);
                IconManager.access$invalidateIconCaches(r74, this.$updatedPack.getId(), this.$resId);
            L14:
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.iconPackUpdated, new Object[0]);
                return Unit.INSTANCE;
            L11:
                r2 = -1;
                goto L12
            L16:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01461(String r1, String r2, int r3, Continuation<? super C01461> r4) {
            this.$packId = r1;
            this.$resourceName = r2;
            this.$resId = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            C01461 r0 = new C01461(this.$packId, this.$resourceName, this.$resId, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01461) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r18) {
            CoroutineScope r2 = (CoroutineScope) this.L$0;
            Object r3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r0 = this.label;
            if (r0 == 0) goto L8;
            if (r0 != 1) goto L6;
            IconPack r02 = (IconPack) this.L$4;
            Map r03 = (Map) this.L$3;
            String r04 = (String) this.L$2;
            IconPack r05 = (IconPack) this.L$1;
            ResultKt.throwOnFailure(r18);
        L39:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r18);
            IconPackStorage r06 = IconPackStorage.INSTANCE;
            IconPack r7 = r06.findPackById(this.$packId);
            if (r7 == null) goto L11;
            String r6 = r7.getIcons().get(this.$resourceName);
            if (r6 == null) goto L15;
            Map r12 = MapsKt.toMutableMap(r7.getIcons());
            r12.remove(this.$resourceName);
            IconPack r8 = IconPack.copy$default(r7, null, null, null, null, r12, null, null, 111, null);
            if (r06.saveIconPackMetadata(r8) == true) goto L21;
            return Unit.INSTANCE;
        L21:
            if (r12.values().contains(r6) == false) goto L40;
        L35:
            MainCoroutineDispatcher r07 = Dispatchers.getMain();
            AnonymousClass3 r9 = new AnonymousClass3(r8, this.$resId, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r2);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r7);
            this.L$2 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$3 = SpillingKt.nullOutSpilledVariable(r12);
            this.L$4 = SpillingKt.nullOutSpilledVariable(r8);
            this.label = 1;
            if (BuildersKt.withContext(r07, r9, this) != r3) goto L39;
            return r3;
        L40:
            Result.Companion r08 = Result.Companion;     // Catch: Throwable -> L27
            File r09 = IconManager.access$resolvePackIconFile(IconManager.INSTANCE, r7, r6);     // Catch: Throwable -> L27
            if (r09 != null) goto L25;
        L29:
            Object r010 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L27
        L32:
            Throwable r011 = Result.m2021exceptionOrNullimpl(r010);
            if (r011 == null) goto L35;
            FileLog.e("Failed to delete old icon", r011);
            goto L35
        L25:
            if (r09.exists() == false) goto L29;
            SimpliFiles.file(r09).delete();     // Catch: Throwable -> L27
        L27:
            th = move-exception;
            Result.Companion r92 = Result.Companion;
            r010 = Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L32
        L15:
            return Unit.INSTANCE;
        L11:
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveCustomIcon$1", f = "IconManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {646}, m = "invokeSuspend", n = {"$this$launch", "packToEdit", "ext", "sanitized", "baseName", "stem", "packDirectory", "destination", "updatedMap", "previousFileName", "updatedPack", "preDecoded", "density"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"})
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1058:1\n1#2:1059\n392#3,4:1060\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1\n*L\n620#1:1060,4\n*E\n"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$saveCustomIcon$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $originalName;
        final /* synthetic */ String $packId;
        final /* synthetic */ int $resId;
        final /* synthetic */ String $resourceName;
        final /* synthetic */ File $tempFile;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveCustomIcon$1$2", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1058:1\n363#2,7:1059\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1$2\n*L\n647#1:1059,7\n*E\n"})
        /* renamed from: com.exteragram.messenger.icons.IconManager$saveCustomIcon$1$2, reason: invalid class name */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $density;
            final /* synthetic */ Bitmap $preDecoded;
            final /* synthetic */ int $resId;
            final /* synthetic */ String $resourceName;
            final /* synthetic */ IconPack $updatedPack;
            int label;

            public AnonymousClass2(IconPack r1, int r2, Bitmap r3, String r4, int r5, Continuation<? super AnonymousClass2> r6) {
                this.$updatedPack = r1;
                this.$resId = r2;
                this.$preDecoded = r3;
                this.$resourceName = r4;
                this.$density = r5;
                super(2, r6);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
                return new AnonymousClass2(this.$updatedPack, this.$resId, this.$preDecoded, this.$resourceName, this.$density, r9);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r12) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L18;
                ResultKt.throwOnFailure(r12);
                CopyOnWriteArrayList r122 = IconManager.access$getActivePacks$p();
                IconPack r0 = this.$updatedPack;
                Iterator r123 = r122.iterator();
                int r2 = 0;
            L6:
                if (r123.hasNext() == false) goto L11;
                if (Intrinsics.areEqual(((IconPack) r123.next()).getId(), r0.getId()) == true) goto L12;
                r2 = r2 + 1;
            L12:
                if (r2 == (-1)) goto L16;
                IconManager.access$getActivePacks$p().set(r2, this.$updatedPack);
                IconManager r3 = IconManager.INSTANCE;
                IconManager.access$rebuildOwnerMap(r3);
                IconManager.access$invalidateIconCaches(r3, this.$updatedPack.getId(), this.$resId);
                Bitmap r10 = this.$preDecoded;
                if (r10 == null) goto L16;
                Boxing.boxBoolean(IconManager.access$publishBitmap(r3, IconManager.access$getInitializationGeneration$p(), this.$resourceName, this.$updatedPack, this.$resId, this.$density, r10));
            L16:
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.iconPackUpdated, new Object[0]);
                return Unit.INSTANCE;
            L11:
                r2 = -1;
                goto L12
            L18:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01471(String r1, File r2, String r3, String r4, int r5, Continuation<? super C01471> r6) {
            this.$packId = r1;
            this.$tempFile = r2;
            this.$originalName = r3;
            this.$resourceName = r4;
            this.$resId = r5;
            super(2, r6);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
            C01471 r0 = new C01471(this.$packId, this.$tempFile, this.$originalName, this.$resourceName, this.$resId, r9);
            r0.L$0 = r8;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01471) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r31) {
            CoroutineScope r2 = (CoroutineScope) this.L$0;
            Object r3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r0 = this.label;
            File r5 = null;
            if (r0 == 0) goto L14;
            if (r0 != 1) goto L12;
            Bitmap r02 = (Bitmap) this.L$11;
            IconPack r03 = (IconPack) this.L$10;
            String r04 = (String) this.L$9;
            Map r05 = (Map) this.L$8;
            File r06 = (File) this.L$7;
            SimpliDirectory r07 = (SimpliDirectory) this.L$6;
            String r08 = (String) this.L$5;
            String r09 = (String) this.L$4;
            String r010 = (String) this.L$3;
            String r011 = (String) this.L$2;
            IconPack r012 = (IconPack) this.L$1;
            ResultKt.throwOnFailure(r31);     // Catch: Throwable -> L8 Exception -> L10
        L102:
            File r013 = this.$tempFile;
            Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L108
            if (r013.exists() == false) goto L106;
            SimpliFiles.file(r013).delete();     // Catch: Throwable -> L108
        L106:
            Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L108
        L134:
            return Unit.INSTANCE;
        L108:
            th = move-exception;
            Result.Companion r12 = Result.Companion;
            Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L134
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L14:
            ResultKt.throwOnFailure(r31);
            IconPack r7 = IconPackStorage.INSTANCE.findPackById(this.$packId);     // Catch: Throwable -> L8 Exception -> L10
            if (r7 != null) goto L28;
            Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L8 Exception -> L10
            File r014 = this.$tempFile;
            Result.Companion r13 = Result.Companion;     // Catch: Throwable -> L22
            if (r014.exists() == false) goto L24;
            SimpliFiles.file(r014).delete();     // Catch: Throwable -> L22
        L24:
            Result.m2018constructorimpl(r22);     // Catch: Throwable -> L22
            return r22;
        L22:
            th = move-exception;
            Result.Companion r14 = Result.Companion;
            Result.m2018constructorimpl(ResultKt.createFailure(th));
            return r22;
        L28:
            String r6 = FilesKt.getExtension(this.$tempFile);     // Catch: Throwable -> L8 Exception -> L10
            String r015 = this.$originalName;     // Catch: Throwable -> L8 Exception -> L10
            if (r015 == null) goto L32;
            String r016 = FileManager.fileNameFromUserString(r015);     // Catch: Throwable -> L8 Exception -> L10
        L33:
            String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r016 != null) goto L36;
            String r17 = _UrlKt.FRAGMENT_ENCODE_SET;
        L37:
            String r017 = this.$resourceName;     // Catch: Throwable -> L8 Exception -> L10
            if (r17.length() != 0) goto L40;
            String r9 = r017;
        L41:
            String r018 = StringsKt.substringBeforeLast(r9, '.', r9);     // Catch: Throwable -> L8 Exception -> L10
            String r10 = this.$resourceName;     // Catch: Throwable -> L8 Exception -> L10
            if (r018.length() != 0) goto L44;
            r018 = r10;
        L44:
            String r019 = StringsKt.take(r018, 64);     // Catch: Throwable -> L8 Exception -> L10
            int r102 = StringsKt.getLastIndex(r019);     // Catch: Throwable -> L8 Exception -> L10
        L46:
            if ((-1) >= r102) goto L51;
            if (Character.isHighSurrogate(r019.charAt(r102)) == false) goto L49;
            r102 = r102 - 1;
            goto L46
        L49:
            r8 = r019.substring(0, r102 + 1);     // Catch: Throwable -> L8 Exception -> L10
        L51:
            SimpliDirectory r103 = SimpliFiles.directory(new File(IconPackStorage.INSTANCE.getIconPacksDirectory(), r7.getId())).create();     // Catch: Throwable -> L8 Exception -> L10
        L52:
            File r11 = r103.file(r8 + "_" + UUID.randomUUID() + "." + r6).getFile();     // Catch: Throwable -> L8 Exception -> L10
            if (r11.exists() == true) goto L52;
            SimpliFiles.file(this.$tempFile).copyTo(r11, OverwritePolicy.ERROR);     // Catch: Throwable -> L110 Exception -> L112
            Map r122 = MapsKt.toMutableMap(r7.getIcons());     // Catch: Throwable -> L110 Exception -> L112
            String r132 = (String) r122.put(this.$resourceName, r11.getName());     // Catch: Throwable -> L110 Exception -> L112
            String r142 = r8;
            String r18 = r9;
            File r20 = r11;
            IconPack r82 = IconPack.copy$default(r7, null, null, null, null, r122, null, null, 111, null);     // Catch: Throwable -> L78 Exception -> L80
            if (IconPackStorage.INSTANCE.saveIconPackMetadata(r82) == true) goto L82;
            Unit r23 = Unit.INSTANCE;     // Catch: Throwable -> L78 Exception -> L80
            File r020 = this.$tempFile;
            Result.Companion r15 = Result.Companion;     // Catch: Throwable -> L63
            if (r020.exists() == false) goto L65;
            SimpliFiles.file(r020).delete();     // Catch: Throwable -> L63
        L65:
            Result.m2018constructorimpl(r23);     // Catch: Throwable -> L63
        L158:
        L71:
            th = move-exception;
            Result.Companion r16 = Result.Companion;
            Object r021 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        L76:
            Result.m2017boximpl(r021);
            return r23;
        L69:
            if (r20.exists() == false) goto L73;
            SimpliFiles.file(r20).delete();     // Catch: Throwable -> L71
        L73:
            r021 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L71
        L63:
            th = move-exception;
            Result.Companion r19 = Result.Companion;
            Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L158
        L82:
            if (r132 != null) goto L84;
        L99:
            int r022 = AndroidUtilities.displayMetrics.densityDpi;     // Catch: Throwable -> L8 Exception -> L10
            Bitmap r26 = IconManager.INSTANCE.createBitmapFromFile(r20.getAbsolutePath(), this.$resId, r022, null);     // Catch: Throwable -> L8 Exception -> L10
            MainCoroutineDispatcher r92 = Dispatchers.getMain();     // Catch: Throwable -> L8 Exception -> L10
            AnonymousClass2 r232 = new AnonymousClass2(r82, this.$resId, r26, this.$resourceName, r022, null);     // Catch: Throwable -> L8 Exception -> L10
            this.L$0 = r2;     // Catch: Throwable -> L8 Exception -> L10
            this.L$1 = SpillingKt.nullOutSpilledVariable(r7);     // Catch: Throwable -> L8 Exception -> L10
            this.L$2 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: Throwable -> L8 Exception -> L10
            this.L$3 = SpillingKt.nullOutSpilledVariable(r17);     // Catch: Throwable -> L8 Exception -> L10
            this.L$4 = SpillingKt.nullOutSpilledVariable(r18);     // Catch: Throwable -> L8 Exception -> L10
            this.L$5 = SpillingKt.nullOutSpilledVariable(r142);     // Catch: Throwable -> L8 Exception -> L10
            this.L$6 = SpillingKt.nullOutSpilledVariable(r103);     // Catch: Throwable -> L8 Exception -> L10
            this.L$7 = SpillingKt.nullOutSpilledVariable(r20);     // Catch: Throwable -> L8 Exception -> L10
            this.L$8 = SpillingKt.nullOutSpilledVariable(r122);     // Catch: Throwable -> L8 Exception -> L10
            this.L$9 = SpillingKt.nullOutSpilledVariable(r132);     // Catch: Throwable -> L8 Exception -> L10
            this.L$10 = SpillingKt.nullOutSpilledVariable(r82);     // Catch: Throwable -> L8 Exception -> L10
            this.L$11 = SpillingKt.nullOutSpilledVariable(r26);     // Catch: Throwable -> L8 Exception -> L10
            this.I$0 = r022;     // Catch: Throwable -> L8 Exception -> L10
            this.label = 1;     // Catch: Throwable -> L8 Exception -> L10
            if (BuildersKt.withContext(r92, r232, this) != r3) goto L102;
            return r3;
        L84:
            if (r122.values().contains(r132) == true) goto L99;
            File r023 = IconManager.access$resolvePackIconFile(IconManager.INSTANCE, r7, r132);     // Catch: Throwable -> L8 Exception -> L10
            if (r023 == null) goto L99;
            Result.Companion r93 = Result.Companion;     // Catch: Throwable -> L90
            if (r023.exists() == false) goto L92;
            SimpliFiles.file(r023).delete();     // Catch: Throwable -> L90
        L92:
            Object r024 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L90
        L95:
            Throwable r94 = Result.m2021exceptionOrNullimpl(r024);     // Catch: Throwable -> L8 Exception -> L10
            if (r94 == null) goto L98;
            FileLog.e("Failed to delete old icon", r94);     // Catch: Throwable -> L8 Exception -> L10
        L98:
            Result.m2017boximpl(r024);     // Catch: Throwable -> L8 Exception -> L10
        L90:
            th = move-exception;
            Result.Companion r95 = Result.Companion;     // Catch: Throwable -> L8 Exception -> L10
            r024 = Result.m2018constructorimpl(ResultKt.createFailure(th));     // Catch: Throwable -> L8 Exception -> L10
        L80:
            e = e;
        L81:
            r5 = r20;
        L114:
            FileLog.e("Failed to save custom icon", e);     // Catch: Throwable -> L8
            File r025 = this.$tempFile;
            Result.Companion r110 = Result.Companion;     // Catch: Throwable -> L119
            if (r025.exists() == false) goto L121;
            SimpliFiles.file(r025).delete();     // Catch: Throwable -> L119
        L121:
            Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L119
        L124:
            if (r5 == null) goto L134;
        L130:
            th = move-exception;
            Result.Companion r111 = Result.Companion;
            Object r026 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        L132:
            Result.m2017boximpl(r026);
            goto L134
        L126:
            if (r5.exists() == false) goto L128;
            SimpliFiles.file(r5).delete();     // Catch: Throwable -> L130
        L128:
            r026 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L130
        L119:
            th = move-exception;
            Result.Companion r112 = Result.Companion;
            Result.m2018constructorimpl(ResultKt.createFailure(th));
        L78:
            th = th;
        L79:
            Throwable r24 = th;
            r5 = r20;
        L135:
            File r027 = this.$tempFile;
            Result.Companion r113 = Result.Companion;     // Catch: Throwable -> L139
            if (r027.exists() == false) goto L141;
            SimpliFiles.file(r027).delete();     // Catch: Throwable -> L139
        L141:
            Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L139
        L144:
            if (r5 == null) goto L181;
        L148:
            th = move-exception;
            Result.Companion r114 = Result.Companion;
            Object r028 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        L153:
            Result.m2017boximpl(r028);
            throw r24;
        L146:
            if (r5.exists() == false) goto L150;
            SimpliFiles.file(r5).delete();     // Catch: Throwable -> L148
        L150:
            r028 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L148
            goto L153
        L181:
            throw r24;
        L139:
            th = move-exception;
            Result.Companion r115 = Result.Companion;
            Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L144
        L112:
            e = e;
            r20 = r11;
        L110:
            th = th;
            r20 = r11;
            goto L79
        L40:
            r9 = r17;
            goto L41
        L36:
            r17 = r016;
            goto L37
        L32:
            r016 = null;
        L8:
            th = move-exception;
            r24 = th;
        L10:
            e = e;
            goto L114
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveIconPackMetadata$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1058:1\n363#2,7:1059\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$1\n*L\n912#1:1059,7\n*E\n"})
    /* renamed from: com.exteragram.messenger.icons.IconManager$saveIconPackMetadata$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ IconPack $savedPack;
        int label;

        public C01481(IconPack r1, Continuation<? super C01481> r2) {
            this.$savedPack = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01481(this.$savedPack, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01481) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L16;
            ResultKt.throwOnFailure(r7);
            CopyOnWriteArrayList r72 = IconManager.access$getActivePacks$p();
            IconPack r0 = this.$savedPack;
            Iterator r73 = r72.iterator();
            int r2 = 0;
        L6:
            if (r73.hasNext() == false) goto L11;
            if (Intrinsics.areEqual(((IconPack) r73.next()).getId(), r0.getId()) == true) goto L12;
            r2 = r2 + 1;
        L12:
            if (r2 == (-1)) goto L14;
            IconManager.access$getActivePacks$p().set(r2, this.$savedPack);
            IconManager.access$rebuildOwnerMap(IconManager.INSTANCE);
            IconManager.access$getResolvedCache$p().clear();
            IconManager.access$getSourceCache$p().evictAll();
        L14:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.iconPackUpdated, new Object[0]);
            return Unit.INSTANCE;
        L11:
            r2 = -1;
            goto L12
        L16:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* renamed from: $r8$lambda$8195jxe_T9ZAmG347S-81JjIZH0, reason: not valid java name */
    public static boolean m1439$r8$lambda$8195jxe_T9ZAmG347S81JjIZH0(String r4) {
        return StringsKt.startsWith$default(r4, "base.", false, 2, null);
    }

    public static IconPack $r8$lambda$E6tDhC_y1b8zP0tKCJrvjcsgNzw(String r1) {
        return IconPackStorage.INSTANCE.findPackById(r1);
    }

    public static int $r8$lambda$HH0pVZuhCCTzorOy7KzrzVfUcNw(SparseIntArray r2) {
        return r2.get(R.drawable.notification, -1);
    }

    public static SparseIntArray $r8$lambda$Vs1GV53ha9TZG53mOuPSdSA1wwU(String r1) {
        IconPack r12 = BaseIconPacks.INSTANCE.getBasePack(r1);
        if (r12 != null) goto L5;
        return null;
    L5:
        return r12.getPreinstalledMap();
    }

    public static boolean $r8$lambda$iEQWJ4hI0MeHcC2H1Bp3U61udlc(String r4) {
        return StringsKt.startsWith$default(r4, "base.", false, 2, null);
    }

    static {
        IconManager r0 = new IconManager();
        INSTANCE = r0;
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default(null, 1, null)));
        mutationDispatcher = CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), 1, null, 2, null);
        decodeDispatcher = CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), 2, null, 2, null);
        prewarmDispatcher = CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), 1, null, 2, null);
        blacklistedIcons = SetsKt.setOf(new String[]{"system", "smiles_popup", "camera_btn", "cancel_big", "chats_archive_box", "chats_archive_arrow", "chats_archive_muted", "chats_archive_pin", "chats_widget_preview", "circle_big", "contacts_widget_preview", "etg_splash", "ev_minus", "ev_plus", "filled_chatlink_large", "field_carret_empty", "dino_pic", "circle", "widgets_light_badgebg", "greydivider", "greydivider_bottom", "greydivider_top", "groups_limit1", "ic_ab_new", "ic_foreground", "ic_foreground_monet", "ic_foreground_solid", "ic_player", "ic_reply_icon", "icon_background_clip", "icon_background_clip_round", "icon_plane", "icplaceholder", "large_ads_info", "large_away", "large_greeting", "large_log_actions", "large_monetize", "large_quickreplies", "list_selector_ex", "livepin", "load_big", "location_empty", "login_arrow1", "login_phone1", "logo_middle", "map_pin", "map_pin2", "map_pin3", "map_pin_circle", "map_pin_cone2", "map_pin_photo", "msg_media_gallery", "music_empty", "no_passport", "no_password", "nophotos", "nophotos3", "paint_elliptical_brush", "paint_neon_brush", "paint_radial_brush", "phone_activate", "photo_placeholder_in", "photo_tooltip2", "photoview_placeholder", "screencast_big", "scrollbar_vertical_thumb", "scrollbar_vertical_thumb_inset", "newmsg_divider", "ic_launcher_dr", "smiles_info", "sms_bubble", "sms_devices", "stats_tooltip", "sticker", "story_camera", "theme_preview_image", "transparent", "venue_tooltip", "videopreview", "bluecounter", "photobadge", "photos_rounded", "calendar_date", "menu_copy", "redcircle", "tooltip_arrow", "tooltip_arrow_up", "newyear", "cards_chat", "community_cards", "ic_call_notification_answer", "ic_call_notification_decline", "shortcut_compose", "shortcut_user", "slide_dot_big", "slide_dot_small", "cocoon_logo", "cocoon_text", "telegram_logo", "telegram_logo_2", "mastercard_icon", "ton_icon", "diamond"});
        systemIcons = new ConcurrentHashMap();
        int r1 = (int) (Runtime.getRuntime().maxMemory() / 1024);
        maxMemory = r1;
        int r12 = Math.max(1024, r1 / 8);
        cacheSize = r12;
        resolvedCache = new ResolvedIndex(2048);
        sourceCache = new IconManager$sourceCache$1(r12);
        activePacks = new CopyOnWriteArrayList();
        iconOwnerMap = new ConcurrentHashMap();
        initialPacksLoaded = new CountDownLatch(1);
        initialize$default(r0, false, 1, null);
        resultCallbacks = new SparseArray();
    }

    private IconManager() {
    }

    public static final /* synthetic */ CopyOnWriteArrayList access$getActivePacks$p() {
        return activePacks;
    }

    public static final /* synthetic */ ConcurrentHashMap access$getIconOwnerMap$p() {
        return iconOwnerMap;
    }

    public static final /* synthetic */ CountDownLatch access$getInitialPacksLoaded$p() {
        return initialPacksLoaded;
    }

    public static final /* synthetic */ long access$getInitializationGeneration$p() {
        return initializationGeneration;
    }

    public static final /* synthetic */ boolean access$getInitializationTimedOut$p() {
        return initializationTimedOut;
    }

    public static final /* synthetic */ Bitmap access$getPackIconBitmap(IconManager r0, IconPack r1, int r2, int r3, Resources.Theme r4, String r5, boolean r6) {
        return r0.getPackIconBitmap(r1, r2, r3, r4, r5, r6);
    }

    public static final /* synthetic */ ResolvedIndex access$getResolvedCache$p() {
        return resolvedCache;
    }

    public static final /* synthetic */ CoroutineScope access$getScope$p() {
        return scope;
    }

    public static final /* synthetic */ IconManager$sourceCache$1 access$getSourceCache$p() {
        return sourceCache;
    }

    public static final /* synthetic */ void access$invalidateIconCaches(IconManager r0, String r1, int r2) {
        r0.invalidateIconCaches(r1, r2);
    }

    public static final /* synthetic */ void access$launchPrewarm(IconManager r0, long r1) {
        r0.launchPrewarm(r1);
    }

    public static final /* synthetic */ boolean access$publishBitmap(IconManager r0, long r1, String r3, IconPack r4, int r5, int r6, Bitmap r7) {
        return r0.publishBitmap(r1, r3, r4, r5, r6, r7);
    }

    public static final /* synthetic */ void access$rebuildOwnerMap(IconManager r0) {
        r0.rebuildOwnerMap();
    }

    public static final /* synthetic */ File access$resolvePackIconFile(IconManager r0, IconPack r1, String r2) {
        return r0.resolvePackIconFile(r1, r2);
    }

    public static final /* synthetic */ void access$setSystemNames$p(IntStringMap r0) {
        systemNames = r0;
    }

    public static final /* synthetic */ void access$showIconPackError(IconManager r0, BaseFragment r1, IconPackStorageError r2) {
        r0.showIconPackError(r1, r2);
    }

    public static final /* synthetic */ boolean access$syncInstalledCustomPacks(IconManager r0, List r1) {
        return r0.syncInstalledCustomPacks(r1);
    }

    public static final /* synthetic */ ActivePacksUpdate access$updateActivePacks(IconManager r0, long r1, List r3) {
        return r0.updateActivePacks(r1, r3);
    }

    private final Bitmap cachedPackIconBitmap(IconPack r4, int r5, int r6) {
        IntStringMap r0 = systemNames;
        if (r0 == null) goto L12;
        String r02 = r0.get(r5);
        if (r02 == null) goto L12;
        String r03 = r4.getIcons().get(r02);
        if (r03 != null) goto L11;
        return null;
    L11:
        return sourceCache.get(sourceCacheKey(r4, r03, r5, r6));
    L12:
        return null;
    }

    @JvmStatic
    public static final IconCompat getNotificationIcon() {
        ExteraConfig.loadConfig();     // Catch: Exception -> L38
        Iterator r0 = SequencesKt.mapNotNull(SequencesKt.filterNot(CollectionsKt.asSequence(ExteraConfig.getIconPacksLayout()), new IconManager$$ExternalSyntheticLambda0()), new IconManager$$ExternalSyntheticLambda1()).iterator();     // Catch: Exception -> L38
    L3:
        Object r9 = null;
        if (r0.hasNext() == false) goto L8;
        Object r1 = r0.next();     // Catch: Exception -> L38
        if (((IconPack) r1).getIcons().containsKey("notification") == false) goto L3;
    L9:
        IconPack r12 = (IconPack) r1;     // Catch: Exception -> L38
        if (r12 != null) goto L12;
    L26:
        Iterator r02 = SequencesKt.map(SequencesKt.mapNotNull(SequencesKt.filter(CollectionsKt.asSequence(ExteraConfig.getIconPacksLayout()), new IconManager$$ExternalSyntheticLambda2()), new IconManager$$ExternalSyntheticLambda3()), new IconManager$$ExternalSyntheticLambda4()).iterator();     // Catch: Exception -> L38
    L28:
        if (r02.hasNext() == false) goto L32;
        Object r13 = r02.next();     // Catch: Exception -> L38
        if (((Number) r13).intValue() == (-1)) goto L28;
        r9 = r13;
    L32:
        Integer r92 = (Integer) r9;     // Catch: Exception -> L38
        if (r92 == null) goto L35;
        int r03 = r92.intValue();     // Catch: Exception -> L38
    L36:
        return IconCompat.createWithResource(ApplicationLoader.applicationContext, r03);
    L35:
        r03 = R.drawable.notification;     // Catch: Exception -> L38
        goto L36
    L12:
        if (Build.VERSION.SDK_INT < 31) goto L21;
        Uri r14 = IconPackProvider.Companion.getIconUri(r12.getId(), "notification");     // Catch: Exception -> L38
        if (r14 != null) goto L43;
    L20:
        IconCompat r04 = null;
    L24:
        if (r04 == null) goto L26;
        return r04;
    L43:
        ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui", r14, 1);     // Catch: Exception -> L17
    L19:
        r04 = IconCompat.createWithContentUri(r14);     // Catch: Exception -> L38
        goto L24
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L38
        goto L19
    L21:
        Bitmap r05 = getPackIconBitmap$default(INSTANCE, r12, R.drawable.notification, AndroidUtilities.displayMetrics.densityDpi, null, "notification", false, 32, null);     // Catch: Exception -> L38
        if (r05 == null) goto L20;
        r04 = IconCompat.createWithBitmap(r05);     // Catch: Exception -> L38
        goto L24
    L8:
        r1 = null;
    L38:
        e = move-exception;
        FileLog.e("Failed to resolve notification icon", e);
        return IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.notification);
    }

    @JvmStatic
    public static final Icon getNotificationSystemIcon() {
        return getNotificationIcon().toIcon(ApplicationLoader.applicationContext);
    }

    private final Bitmap getPackIconBitmap(IconPack r5, int r6, int r7, Resources.Theme r8, String r9, boolean r10) {
        if (r9 != null) goto L12;
        IntStringMap r92 = systemNames;
        if (r92 == null) goto L7;
        r9 = r92.get(r6);
    L8:
        if (r9 != null) goto L12;
        r9 = ApplicationLoader.applicationContext.getResources().getResourceEntryName(r6);     // Catch: Exception -> L11
    L11:
        return null;
    L7:
        r9 = null;
    L12:
        String r93 = r5.getIcons().get(r9);
        if (r93 != null) goto L15;
        return null;
    L15:
        SourceCacheKey r1 = sourceCacheKey(r5, r93, r6, r7);
        IconManager$sourceCache$1 r2 = sourceCache;
        Bitmap r3 = r2.get(r1);
        if (r3 == null) goto L18;
        return r3;
    L18:
        File r52 = resolvePackIconFile(r5, r93);
        if (r52 != null) goto L21;
        return null;
    L21:
        Bitmap r4 = createBitmapFromFile(r52.getAbsolutePath(), r6, r7, r8);
        if (r4 == null) goto L25;
        if (r10 == false) goto L25;
        r2.put(r1, r4);
    L25:
        return r4;
    }

    public static /* synthetic */ Bitmap getPackIconBitmap$default(IconManager r7, IconPack r8, int r9, int r10, Resources.Theme r11, String r12, boolean r13, int r14, Object r15) {
        if ((r14 & 16) == 0) goto L5;
        r12 = null;
    L5:
        String r5 = r12;
        if ((r14 & 32) == 0) goto L9;
        r13 = true;
    L9:
        return r7.getPackIconBitmap(r8, r9, r10, r11, r5, r13);
    }

    private final String iconPackErrorText(IconPackStorageError r1) {
        switch(WhenMappings.$EnumSwitchMapping$0[r1.ordinal()]) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            case 9: goto L7;
            case 10: goto L6;
            default: goto L4;
        };
    L4:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L6:
        int r0 = R.string.UnknownError;
    L17:
        return LocaleController.getString(r0);
    L7:
        r0 = R.string.IconPackErrorStorage;
        goto L17
    L8:
        r0 = R.string.IconPackErrorCompressionRatioTooHigh;
        goto L17
    L9:
        r0 = R.string.IconPackErrorFileTooLarge;
        goto L17
    L10:
        r0 = R.string.IconPackErrorArchiveTooLarge;
        goto L17
    L11:
        r0 = R.string.IconPackErrorTooManyFiles;
        goto L17
    L12:
        r0 = R.string.IconPackErrorInvalidMetadata;
        goto L17
    L13:
        r0 = R.string.IconPackErrorMetadataTooLarge;
        goto L17
    L14:
        r0 = R.string.IconPackErrorMissingMetadata;
        goto L17
    L15:
        r0 = R.string.IconPackErrorInvalidArchive;
        goto L17
    }

    public static /* synthetic */ void initialize$default(IconManager r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = false;
    L5:
        r0.initialize(r1);
    }

    private final void invalidateIconCaches(String r5, int r6) {
        resolvedCache.removeByResId(r6);
        Set<SourceCacheKey> r4 = sourceCache.snapshot().keySet();
        ArrayList r0 = new ArrayList();
        Iterator<T> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L10;
        Object r1 = r42.next();
        SourceCacheKey r2 = (SourceCacheKey) r1;
        if (Intrinsics.areEqual(r2.getPackId(), r5) == false) goto L4;
        if (r2.getResId() != r6) goto L4;
        r0.add(r1);
        goto L4
    L10:
        int r43 = r0.size();
        int r52 = 0;
    L11:
        if (r52 >= r43) goto L13;
        Object r62 = r0.get(r52);
        r52 = r52 + 1;
        IconManager$sourceCache$1 r12 = sourceCache;
        r12.remove((SourceCacheKey) r62);
        goto L11
    }

    private final synchronized void launchPrewarm(long r7) {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (r7 == initializationGeneration) goto L8;
        monitor-exit(this);
        return;
    L8:
        Job r0 = prewarmJob;     // Catch: Throwable -> L11
        if (r0 == null) goto L13;
        Job.cancel$default(r0, null, 1, null);     // Catch: Throwable -> L11
    L13:
        prewarmJob = BuildersKt.launch$default(scope, prewarmDispatcher, null, new C01431(r7, null), 2, null);     // Catch: Throwable -> L11
        monitor-exit(this);
    }

    private final synchronized boolean publishBitmap(long r3, String r5, IconPack r6, int r7, int r8, Bitmap r9) {
        monitor-enter(this);
    L16:
        th = move-exception;
        throw th;
    L4:
        if (r3 == initializationGeneration) goto L6;
    L18:
        monitor-exit(this);
        return false;
    L6:
        if (Intrinsics.areEqual(iconOwnerMap.get(r5), r6) == false) goto L18;
        String r32 = r6.getIcons().get(r5);     // Catch: Throwable -> L16
        if (r32 != null) goto L12;
        monitor-exit(this);
        return false;
    L12:
        SourceCacheKey r33 = sourceCacheKey(r6, r32, r7, r8);     // Catch: Throwable -> L16
        sourceCache.put(r33, r9);     // Catch: Throwable -> L16
        resolvedCache.put(resolvedCacheKey(r7, r8), r33);     // Catch: Throwable -> L16
        monitor-exit(this);
        return true;
    }

    private final void rebuildBasePreinstalledMap() {
        SparseIntArray r8 = new SparseIntArray();
        int r0 = activePacks.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L17;
        IconPack r3 = activePacks.get(r2);
        SparseIntArray r4 = r3.getPreinstalledMap();
        if (r4 == null) goto L15;
        if (r3.isBase() == false) goto L15;
        int r32 = r4.size();
        int r5 = 0;
    L10:
        if (r5 >= r32) goto L15;
        int r6 = r4.keyAt(r5);
        if (r8.indexOfKey(r6) >= 0) goto L14;
        r8.put(r6, r4.valueAt(r5));
    L14:
        r5 = r5 + 1;
    L15:
        r2 = r2 + 1;
        goto L3
    L17:
        if (r8.size() != 0) goto L19;
        r8 = null;
    L19:
        basePreinstalledMap = r8;
    }

    private final void rebuildOwnerMap() {
        iconOwnerMap.clear();
        int r0 = activePacks.size() - 1;
        if (r0 < 0) goto L13;
    L4:
        int r1 = r0 - 1;
        IconPack r02 = activePacks.get(r0);
        if (r02.isBase() == true) goto L10;
        Iterator<String> r2 = r02.getIcons().keySet().iterator();
    L8:
        if (r2.hasNext() == false) goto L10;
        iconOwnerMap.put(r2.next(), r02);
    L10:
        if (r1 < 0) goto L13;
        r0 = r1;
    L13:
        rebuildBasePreinstalledMap();
    }

    private final File resolvePackIconFile(IconPack r3, String r4) {
        File r2 = r3.getLocation();
        if (r2 != null) goto L10;
        r2 = new File(IconPackStorage.INSTANCE.getIconPacksDirectory(), r3.getId());
    L10:
        return SimpliFiles.directory(r2).file(r4).getFile();
    L7:
        e = move-exception;
        FileLog.e("Failed to resolve icon file for pack " + r3.getId(), e);
        return null;
    }

    private final long resolvedCacheKey(int r5, int r6) {
        return (r5 << 32) | (r6 & 4294967295L);
    }

    private final void showIconPackError(BaseFragment r2, IconPackStorageError r3) {
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.error, iconPackErrorText(r3)).show();
    }

    public static /* synthetic */ void showReplaceAlert$default(IconManager r0, Context r1, int r2, IconPack r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = null;
    L5:
        r0.showReplaceAlert(r1, r2, r3);
    }

    private final SourceCacheKey sourceCacheKey(IconPack r9, String r10, int r11, int r12) {
        long r1 = initializationGeneration;
        String r3 = r9.getId();
        File r8 = r9.getLocation();
        if (r8 == null) goto L6;
        String r82 = r8.getAbsolutePath();
    L8:
        return new SourceCacheKey(r1, r3, r82, r10, r11, r12);
    L6:
        r82 = null;
        goto L8
    }

    private final synchronized boolean syncInstalledCustomPacks(List<IconPack> r4) {
        monitor-enter(this);
        Iterator<IconPack> r42 = r4.iterator();     // Catch: Throwable -> L11
        boolean r0 = false;
    L5:
        if (r42.hasNext() == false) goto L13;
        String r1 = r42.next().getId();     // Catch: Throwable -> L11
        if (ExteraConfig.getIconPacksLayout().contains(r1) == true) goto L5;
        if (ExteraConfig.getIconPacksHidden().contains(r1) == true) goto L5;
        ExteraConfig.getIconPacksHidden().add(r1);     // Catch: Throwable -> L11
        r0 = true;
        goto L5
    L13:
        if (r0 == false) goto L15;
        ExteraConfig.saveIconPacksLayout();     // Catch: Throwable -> L11
    L15:
        monitor-exit(this);
        return r0;
    L11:
        th = move-exception;
        throw th;
    }

    private final synchronized ActivePacksUpdate updateActivePacks(long r3, List<IconPack> r5) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (r3 == initializationGeneration) goto L10;
        ActivePacksUpdate r32 = ActivePacksUpdate.STALE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r32;
    L10:
        boolean r33 = activePacksPublished;     // Catch: Throwable -> L8
        activePacksPublished = true;     // Catch: Throwable -> L8
        if (r33 == true) goto L13;
    L17:
        CopyOnWriteArrayList<IconPack> r4 = activePacks;     // Catch: Throwable -> L8
        r4.clear();     // Catch: Throwable -> L8
        r4.addAll(r5);     // Catch: Throwable -> L8
        rebuildOwnerMap();     // Catch: Throwable -> L8
        resolvedCache.clear();     // Catch: Throwable -> L8
        sourceCache.evictAll();     // Catch: Throwable -> L8
        if (r33 == true) goto L20;
        ActivePacksUpdate r34 = ActivePacksUpdate.INITIAL;     // Catch: Throwable -> L8
    L21:
        monitor-exit(this);
        return r34;
    L20:
        r34 = ActivePacksUpdate.CHANGED;     // Catch: Throwable -> L8
        goto L21
    L13:
        if (Intrinsics.areEqual(activePacks, r5) == false) goto L17;
        ActivePacksUpdate r35 = ActivePacksUpdate.UNCHANGED;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r35;
    }

    public final void awaitInitialization() {
    L6:
        Thread.currentThread().interrupt();
        return;
    L3:
        if (initialPacksLoaded.await(1000, TimeUnit.MILLISECONDS) == true) goto L10;
        initializationTimedOut = true;     // Catch: InterruptedException -> L6
        FileLog.e("Icon packs were not loaded in 1000ms");     // Catch: InterruptedException -> L6
        return;
    }

    public final File bundlePackBlocking(String r1) {
        return IconPackStorage.INSTANCE.bundlePackBlocking(r1);
    }

    public final Bitmap createBitmapFromFile(String r21, int r22, int r23, Resources.Theme r24) {
        Bitmap r8 = null;
        Resources r2 = ApplicationLoader.applicationContext.getResources();     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if ((r2 instanceof ExteraResources) == false) goto L10;
        ExteraResources r25 = (ExteraResources) r2;     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L11:
        if (r25 == null) goto L14;
        Drawable r26 = r25.getOriginalDrawable(r22);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if (r26 == null) goto L14;
    L16:
        if (r26 == null) goto L18;
        int r3 = r26.getIntrinsicWidth();     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L19:
        int r32 = Math.max(1, r3);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if (r26 == null) goto L22;
        int r0 = r26.getIntrinsicHeight();     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L23:
        int r5 = Math.max(1, r0);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        File r02 = new File(r21);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        DecodedIconStore r27 = DecodedIconStore.INSTANCE;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        Bitmap r7 = r27.get(r02, r32, r5, r23);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if (r7 == null) goto L27;
        return r7;
    L27:
        if (StringsKt.endsWith(r21, ".svg", true) == false) goto L38;
        FileInputStream r4 = new FileInputStream(r21);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        SVG r72 = SVG.getFromInputStream(r4);     // Catch: Throwable -> L32
        CloseableKt.closeFinally(r4, null);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        Bitmap r42 = Bitmap.createBitmap(r32, r5, Bitmap.Config.ARGB_8888);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        Canvas r9 = new Canvas(r42);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        r72.setDocumentWidth(r32);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        r72.setDocumentHeight(r5);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        r72.renderToCanvas(r9);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        r42.setDensity(r23);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        r27.put(r02, r32, r5, r23, r42);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        return r42;
    L32:
        th = move-exception;
        throw th;     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        CloseableKt.closeFinally(r4, th);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        throw th;     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L38:
        BitmapFactory.Options r28 = new BitmapFactory.Options();     // Catch: Exception -> L6 OutOfMemoryError -> L8
        r28.inJustDecodeBounds = true;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        BitmapFactory.decodeFile(r21, r28);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        int r73 = r28.outWidth;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if (r73 <= 0) goto L79;
        int r92 = r28.outHeight;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if (r92 <= 0) goto L79;
        if ((r73 * r92) > 100000000) goto L79;
        r28.inSampleSize = 1;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if (r92 > r5) goto L48;
        if (r73 > r32) goto L48;
    L54:
        long r93 = Math.max(r32 * r5, 1048576);     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L55:
        long r11 = r28.outWidth;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        int r74 = r28.inSampleSize;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        Bitmap r20 = r8;
        long r15 = r93;
    L60:
        e = e;
    L80:
        FileLog.e("Error loading icon bitmap: " + r21, e);
    L82:
        return r20;
    L62:
        e = e;
    L81:
        FileLog.e("Out of memory loading icon bitmap: " + r21, e);
        goto L82
    L58:
        if (((((r11 + r74) - 1) / r74) * (((r28.outHeight + r74) - 1) / r74)) <= r15) goto L64;
        r28.inSampleSize = r74 * 2;     // Catch: Exception -> L60 OutOfMemoryError -> L62
        r8 = r20;
        r93 = r15;
        goto L55
    L64:
        r28.inJustDecodeBounds = false;     // Catch: Exception -> L60 OutOfMemoryError -> L62
        r28.inPreferredConfig = Bitmap.Config.ARGB_8888;     // Catch: Exception -> L60 OutOfMemoryError -> L62
        Bitmap r29 = BitmapFactory.decodeFile(r21, r28);     // Catch: Exception -> L60 OutOfMemoryError -> L62
        if (r29 != null) goto L68;
        return r20;
    L68:
        if (r29.getWidth() == r32) goto L70;
    L73:
        Bitmap r43 = Bitmap.createScaledBitmap(r29, r32, r5, true);     // Catch: Exception -> L60 OutOfMemoryError -> L62
        if (Intrinsics.areEqual(r43, r29) == true) goto L76;
        r29.recycle();     // Catch: Exception -> L60 OutOfMemoryError -> L62
    L76:
        r43.setDensity(r23);     // Catch: Exception -> L60 OutOfMemoryError -> L62
        Bitmap r75 = r43;
    L77:
        DecodedIconStore.INSTANCE.put(r02, r32, r5, r23, r75);     // Catch: Exception -> L60 OutOfMemoryError -> L62
        return r75;
    L70:
        if (r29.getHeight() != r5) goto L73;
        r29.setDensity(r23);     // Catch: Exception -> L60 OutOfMemoryError -> L62
        r75 = r29;
    L48:
        int r94 = r92 / 2;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        int r76 = r73 / 2;     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L49:
        int r10 = r28.inSampleSize;     // Catch: Exception -> L6 OutOfMemoryError -> L8
        if ((r94 / r10) < r5) goto L54;
        if ((r76 / r10) < r32) goto L54;
        r28.inSampleSize = r10 * 2;     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L79:
        return null;
    L22:
        r0 = AndroidUtilities.dp(24.0f);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        goto L23
    L18:
        r3 = AndroidUtilities.dp(24.0f);     // Catch: Exception -> L6 OutOfMemoryError -> L8
    L14:
        r26 = ResourcesCompat.getDrawableForDensity(ApplicationLoader.applicationContext.getResources(), r22, r23, r24);     // Catch: Exception -> L6 OutOfMemoryError -> L8
        goto L16
    L10:
        r25 = null;
    L6:
        e = e;
        r20 = null;
    L8:
        e = e;
        r20 = null;
        goto L81
    }

    public final void deletePack(String r7) {
        BuildersKt.launch$default(scope, mutationDispatcher, null, new AnonymousClass1(r7, null), 2, null);
    }

    public final IconPack findPackById(String r1) {
        return IconPackStorage.INSTANCE.findPackById(r1);
    }

    public final Drawable getCachedDrawable(int r4) {
        if (iconOwnerMap.isEmpty() == false) goto L5;
        return null;
    L5:
        SourceCacheKey r3 = resolvedCache.get(resolvedCacheKey(r4, AndroidUtilities.displayMetrics.densityDpi));
        if (r3 == null) goto L11;
        Bitmap r32 = sourceCache.get(r3);
        if (r32 == null) goto L11;
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r32);
    L11:
        return null;
    }

    public final Drawable getCachedPackIconDrawable(IconPack r2, int r3) {
        Bitmap r1 = cachedPackIconBitmap(r2, r3, AndroidUtilities.displayMetrics.densityDpi);
        if (r1 != null) goto L5;
        return null;
    L5:
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r1);
    }

    public final Drawable getDrawable(int r12, int r13, Resources.Theme r14) {
        ConcurrentHashMap<String, IconPack> r0 = iconOwnerMap;
        if (r0.isEmpty() == false) goto L5;
        return null;
    L5:
        if (r13 != 0) goto L7;
        r13 = AndroidUtilities.displayMetrics.densityDpi;
    L7:
        int r6 = r13;
        SourceCacheKey r132 = resolvedCache.get(resolvedCacheKey(r12, r6));
        if (r132 == null) goto L10;
        Bitmap r133 = sourceCache.get(r132);
    L11:
        if (r133 != null) goto L13;
        IntStringMap r134 = systemNames;
        if (r134 == null) goto L30;
        String r135 = r134.get(r12);
        if (r135 == null) goto L30;
    L19:
        String r8 = r135;
        IconPack r4 = r0.get(r8);
        if (r4 == null) goto L28;
        long r02 = initializationGeneration;
        Bitmap r10 = getPackIconBitmap(r4, r12, r6, r14, r8, false);
        if (r10 == null) goto L28;
        publishBitmap(r02, r8, r4, r12, r6, r10);
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r10);
    L28:
        return null;
    L30:
        r135 = ApplicationLoader.applicationContext.getResources().getResourceEntryName(r12);     // Catch: Exception -> L29
    L13:
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r133);
    L10:
        r133 = null;
        goto L11
    }

    public final int getIcon(int r2) {
        SparseIntArray r1 = basePreinstalledMap;
        if (r1 == null) goto L8;
        int r12 = r1.get(r2, -1);
        if (r12 == (-1)) goto L8;
        return r12;
    L8:
        return r2;
    }

    public final Drawable getPackIconDrawable(IconPack r11, int r12) {
        Bitmap r10 = getPackIconBitmap$default(this, r11, r12, AndroidUtilities.displayMetrics.densityDpi, null, null, false, 48, null);
        if (r10 != null) goto L5;
        return null;
    L5:
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r10);
    }

    public final ConcurrentHashMap<String, Integer> getSystemIcons() {
        return systemIcons;
    }

    public final void handleIconPack(BaseFragment r7, String r8) {
        BuildersKt.launch$default(scope, null, null, new C01411(r8, r7, null), 3, null);
    }

    public final void handleIconPack(BaseFragment r2, MessageObject r3) {
        handleIconPack(r2, ChatUtils.getInstance().getPathToMessage(r3));
    }

    public final synchronized void initialize(boolean r10) {
        monitor-enter(this);
        Job r0 = initializationJob;     // Catch: Throwable -> L10
        if (r0 == null) goto L13;
        if (r0.isActive() != true) goto L13;
        if (r10 == true) goto L13;
        monitor-exit(this);
        return;
    L13:
        if (r10 == false) goto L17;
        Job r2 = initializationJob;     // Catch: Throwable -> L10
        if (r2 == null) goto L17;
        Job.cancel$default(r2, null, 1, null);     // Catch: Throwable -> L10
    L17:
        Job r22 = prewarmJob;     // Catch: Throwable -> L10
        if (r22 == null) goto L20;
        Job.cancel$default(r22, null, 1, null);     // Catch: Throwable -> L10
    L20:
        initializationGeneration++;
        initializationJob = BuildersKt.launch$default(scope, null, null, new C01421(initializationGeneration, r10, null), 3, null);     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final boolean isBasePackOnly(IconPackType r1) {
        if (ExteraConfig.getIconPack() != r1) goto L17;
        CopyOnWriteArrayList<IconPack> r0 = activePacks;
        if (r0 != null) goto L7;
    L9:
        Iterator<T> r02 = r0.iterator();
    L11:
        if (r02.hasNext() == false) goto L22;
        if (((IconPack) r02.next()).isBase() == true) goto L11;
        return false;
    L22:
        return true;
    L7:
        if (r0.isEmpty() == false) goto L9;
        return true;
    L17:
        return false;
    }

    public final boolean isBlacklisted(String r4) {
        if (blacklistedIcons.contains(r4) == false) goto L5;
        return true;
    L5:
        if (StringsKt.contains$default(r4, "avd", false, 2, null) == false) goto L7;
        return true;
    L7:
        if (StringsKt.endsWith$default(r4, "_solar", false, 2, null) == false) goto L9;
        return true;
    L9:
        if (StringsKt.endsWith$default(r4, "_remix", false, 2, null) == false) goto L11;
        return true;
    L11:
        if (StringsKt.contains$default(r4, "$", false, 2, null) == false) goto L13;
        return true;
    L13:
        if (StringsKt.contains$default(r4, "animationpin", false, 2, null) == false) goto L15;
        return true;
    L15:
        if (StringsKt.contains$default(r4, "googlepay", false, 2, null) == false) goto L17;
        return true;
    L17:
        if (StringsKt.contains$default(r4, "shadow", false, 2, null) == false) goto L19;
        return true;
    L19:
        if (StringsKt.startsWith$default(r4, "ic_monochrome", false, 2, null) == false) goto L21;
        return true;
    L21:
        if (StringsKt.startsWith$default(r4, "nocover", false, 2, null) == false) goto L23;
        return true;
    L23:
        if (StringsKt.startsWith$default(r4, "gradient_", false, 2, null) == false) goto L25;
        return true;
    L25:
        if (StringsKt.startsWith$default(r4, "stickers_back_", false, 2, null) == false) goto L27;
        return true;
    L27:
        if (StringsKt.startsWith$default(r4, "media_doc_", false, 2, null) == false) goto L29;
        return true;
    L29:
        if (StringsKt.startsWith$default(r4, "loading_animation", false, 2, null) == false) goto L31;
        return true;
    L31:
        if (StringsKt.startsWith$default(r4, "intro_", false, 2, null) == false) goto L33;
        return true;
    L33:
        if (StringsKt.startsWith$default(r4, "minibubble_", false, 2, null) == false) goto L35;
        return true;
    L35:
        if (StringsKt.startsWith$default(r4, "book_", false, 2, null) == false) goto L37;
        return true;
    L37:
        if (StringsKt.startsWith$default(r4, "call_", false, 2, null) == false) goto L39;
        return true;
    L39:
        if (StringsKt.startsWith$default(r4, "dice", false, 2, null) == false) goto L41;
        return true;
    L41:
        if (StringsKt.startsWith$default(r4, "msg_other_new_filled", false, 2, null) == false) goto L43;
        return true;
    L43:
        if (StringsKt.startsWith$default(r4, "profile_level", false, 2, null) == false) goto L45;
        return true;
    L45:
        if (StringsKt.startsWith$default(r4, "widget_", false, 2, null) == false) goto L47;
        return true;
    L47:
        if (StringsKt.startsWith$default(r4, "zoom_slide", false, 2, null) == false) goto L49;
        return true;
    L49:
        if (StringsKt.startsWith$default(r4, "zoom_round", false, 2, null) == false) goto L51;
        return true;
    L51:
        if (StringsKt.startsWith$default(r4, "popup_fixed_alert", false, 2, null) == false) goto L53;
        return true;
    L53:
        if (StringsKt.startsWith$default(r4, "search_dark", false, 2, null) == false) goto L55;
        return true;
    L55:
        if (StringsKt.startsWith$default(r4, "bar_selector", false, 2, null) == true) goto L85;
        return false;
    L85:
        return true;
    }

    public final boolean isIconPack(String r3, MessageObject r4) {
        if (r4 == null) goto L5;
        String r42 = r4.getDocumentName();
    L7:
        if (r42 != null) goto L9;
    L14:
        return false;
    L9:
        if (r3.length() <= 0) goto L14;
        if (StringsKt.endsWith$default(r3, ".icons", false, 2, null) == false) goto L14;
        return true;
    L5:
        r42 = null;
        goto L7
    }

    public final boolean isIconPack(MessageObject r2) {
        return isIconPack(ChatUtils.getInstance().getPathToMessage(r2), r2);
    }

    public final boolean onActivityResult(int r1, int r2, Intent r3) {
        SparseArray<Function1<Uri, Unit>> r0 = resultCallbacks;
        Function1<Uri, Unit> r22 = r0.get(r1);
        if (r22 != null) goto L6;
        return false;
    L6:
        r0.remove(r1);
        if (r3 == null) goto L9;
        Uri r02 = r3.getData();
    L10:
        r22.invoke(r02);
        return true;
    L9:
        r02 = null;
        goto L10
    }

    public final void prefetchCustomPacks() {
        BuildersKt.launch$default(scope, null, null, new C01441(null), 3, null);
    }

    public final void requestDrawable(int r7, Utilities.Callback<Drawable> r8) {
        if (iconOwnerMap.isEmpty() == false) goto L5;
        return;
    L5:
        Drawable r6 = getCachedDrawable(r7);
        if (r6 == null) goto L9;
        r8.run(r6);
        return;
    L9:
        int r62 = AndroidUtilities.displayMetrics.densityDpi;
        BuildersKt.launch$default(scope, decodeDispatcher, null, new AnonymousClass2(r7, r62, r8, null), 2, null);
    }

    public final void requestPackIconDrawable(IconPack r8, int r9, Utilities.Callback<Drawable> r10) {
        int r4 = AndroidUtilities.displayMetrics.densityDpi;
        Bitmap r7 = cachedPackIconBitmap(r8, r9, r4);
        if (r7 == null) goto L6;
        r10.run(new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), r7));
        return;
    L6:
        BuildersKt.launch$default(scope, decodeDispatcher, null, new C01452(r8, r9, r4, r10, null), 2, null);
    }

    public final void resetCustomIcon(String r7, int r8) {
        IntStringMap r6 = systemNames;
        if (r6 == null) goto L10;
        String r62 = r6.get(r8);
        if (r62 == null) goto L10;
    L7:
        BuildersKt.launch$default(scope, mutationDispatcher, null, new C01461(r7, r62, r8, null), 2, null);
        return;
    L10:
        r62 = ApplicationLoader.applicationContext.getResources().getResourceEntryName(r8);     // Catch: Exception -> L9
    }

    public final void saveCustomIcon(String r12, int r13, File r14, String r15) {
        IntStringMap r11 = systemNames;
        if (r11 == null) goto L13;
        String r112 = r11.get(r13);
        if (r112 == null) goto L13;
    L7:
        String r4 = r112;
        BuildersKt.launch$default(scope, mutationDispatcher, null, new C01471(r12, r14, r15, r4, r13, null), 2, null);
        return;
    L13:
        r112 = ApplicationLoader.applicationContext.getResources().getResourceEntryName(r13);     // Catch: Exception -> L12
    }

    public final boolean saveIconPackMetadata(IconPack r7) {
        IconPack r6 = (IconPack) BuildersKt.runBlockingK(mutationDispatcher, new IconManager$saveIconPackMetadata$savedPack$1(r7, null));
        if (r6 != null) goto L6;
        return false;
    L6:
        BuildersKt.launch$default(scope, Dispatchers.getMain(), null, new C01481(r6, null), 2, null);
        return true;
    }

    public final void setActiveCustomPack(String r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (ExteraConfig.getIconPacksLayout().contains(r2) == true) goto L8;
        ExteraConfig.getIconPacksLayout().add(r2);
        ExteraConfig.getIconPacksHidden().remove(r2);
        ExteraConfig.saveIconPacksLayout();
        initialize(true);
        return;
    }

    @JvmOverloads
    public final void showReplaceAlert(Context r7, int r8) {
        showReplaceAlert$default(this, r7, r8, null, 4, null);
    }

    @JvmOverloads
    public final void showReplaceAlert(Context r4, int r5, IconPack r6) {
        if (r6 != null) goto L21;
        Object r62 = null;
        if (ExteraConfig.getEditingIconPackId() == null) goto L12;
        Iterator<T> r3 = activePacks.iterator();
    L7:
        if (r3.hasNext() == false) goto L11;
        Object r0 = r3.next();
        if (Intrinsics.areEqual(((IconPack) r0).getId(), ExteraConfig.getEditingIconPackId()) == false) goto L7;
        r62 = r0;
    L11:
        r6 = (IconPack) r62;
    L19:
        if (r6 != null) goto L21;
        return;
    L12:
        Iterator<T> r32 = activePacks.iterator();
    L14:
        if (r32.hasNext() == false) goto L18;
        Object r02 = r32.next();
        if (((IconPack) r02).isBase() == true) goto L14;
        r62 = r02;
    L18:
        r6 = (IconPack) r62;
    L21:
        BaseFragment r33 = LaunchActivity.getSafeLastFragment();
        if (r33 != null) goto L24;
        return;
    L24:
        r33.showDialog(new ReplaceIconBottomSheet(r4, r5, r6));
    }

    public final void startIconPicker(Activity r3, boolean r4, Function1<? super Uri, Unit> r5) {
        resultCallbacks.put(43, r5);
        if (ActivityResultContracts$PickVisualMedia.Companion.isPhotoPickerAvailable(r3) == false) goto L6;
        if (r4 == true) goto L6;
        Intent r2 = new ActivityResultContracts$PickVisualMedia().createIntent2(r3, PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts$PickVisualMedia.ImageOnly.INSTANCE));
    L13:
        r3.startActivityForResult(r2, 43);
        return;
    L6:
        if (r4 == false) goto L8;
        String r22 = "android.intent.action.OPEN_DOCUMENT";
    L9:
        Intent r52 = new Intent(r22);
        r52.setType("*/*");
        r52.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "image/svg+xml"});
        if (r4 == false) goto L12;
        r52.addCategory("android.intent.category.OPENABLE");
    L12:
        r2 = r52;
        goto L13
    L8:
        r22 = "android.intent.action.GET_CONTENT";
        goto L9
    }
}
