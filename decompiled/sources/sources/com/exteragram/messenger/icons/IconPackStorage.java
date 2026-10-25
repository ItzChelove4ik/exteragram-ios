package com.exteragram.messenger.icons;

import androidx.mediarouter.media.GlobalMediaRouter;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.icons.IconPackStorageResult;
import com.google.android.gms.cast.MediaStatus;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONException;
import org.json.JSONObject;
import org.simplifiles.SimpliFiles;
import org.simplifiles.archive.ArchiveFile;
import org.simplifiles.archive.ArchiveIssue;
import org.simplifiles.archive.ArchiveSaveOptions;
import org.simplifiles.archive.security.SecurityPolicy;
import org.simplifiles.exception.ArchiveValidationException;
import org.simplifiles.exception.CorruptedArchiveException;
import org.simplifiles.files.OverwritePolicy;
import org.simplifiles.files.SimpliDirectory;
import org.simplifiles.files.SimpliFile;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SaveToGallerySettingsHelper;

@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001UB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\t¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010\u001d\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u001f\u0010 J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u001e2\u0006\u0010\u001d\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b!\u0010 J\u0017\u0010#\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0007H\u0002¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b%\u0010$J/\u0010*\u001a\u00020\t2\u0006\u0010'\u001a\u00020&2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b*\u0010+J\u0019\u0010,\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001d\u001a\u00020\u0004H\u0002¢\u0006\u0004\b,\u0010-J\u001f\u0010/\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0002¢\u0006\u0004\b/\u00100J\u0017\u00104\u001a\u0002032\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0004\b4\u00105J\u001b\u00108\u001a\u0002032\n\u00102\u001a\u000606j\u0002`7H\u0002¢\u0006\u0004\b8\u00109J\u0017\u0010<\u001a\u00020;2\u0006\u0010:\u001a\u00020\u0007H\u0002¢\u0006\u0004\b<\u0010=J\u0017\u0010?\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020;H\u0002¢\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\bA\u0010\u001cJ\u000f\u0010B\u001a\u00020\u001aH\u0002¢\u0006\u0004\bB\u0010\u0003R$\u0010D\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER4\u0010H\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0Fj\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t`G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010T¨\u0006V"}, d2 = {"Lcom/exteragram/messenger/icons/IconPackStorage;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Ljava/io/File;", "getIconPacksDirectory", "()Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "packId", "Lcom/exteragram/messenger/icons/IconPack;", "findPackById", "(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;", "resourceName", "resolveIconFile", "(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;", "bundlePack", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bundlePackBlocking", "(Ljava/lang/String;)Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "getCustomPacks", "()Ljava/util/List;", "iconPack", _UrlKt.FRAGMENT_ENCODE_SET, "saveIconPackMetadata", "(Lcom/exteragram/messenger/icons/IconPack;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "deletePack", "(Ljava/lang/String;)V", "file", "Lcom/exteragram/messenger/icons/IconPackStorageResult;", "installPack", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parsePackFromZip", "path", "isPlainFileName", "(Ljava/lang/String;)Z", "isValidPackId", "Lorg/json/JSONObject;", "jsonObject", "packRoot", "location", "parseMetadata", "(Lorg/json/JSONObject;Ljava/io/File;Ljava/io/File;)Lcom/exteragram/messenger/icons/IconPack;", "parseMetadataFile", "(Ljava/io/File;)Lcom/exteragram/messenger/icons/IconPack;", "targetDir", "extractPackArchive", "(Ljava/io/File;Ljava/io/File;)Lorg/json/JSONObject;", "Lorg/simplifiles/exception/ArchiveValidationException;", "e", "Lcom/exteragram/messenger/icons/IconPackStorageError;", "errorFromValidationException", "(Lorg/simplifiles/exception/ArchiveValidationException;)Lcom/exteragram/messenger/icons/IconPackStorageError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorFromException", "(Ljava/lang/Exception;)Lcom/exteragram/messenger/icons/IconPackStorageError;", "prefix", "Lorg/simplifiles/files/SimpliDirectory;", "createTempCacheDirectory", "(Ljava/lang/String;)Lorg/simplifiles/files/SimpliDirectory;", "directory", "deleteDirectoryIfExists", "(Lorg/simplifiles/files/SimpliDirectory;)V", "invalidatePack", "invalidateAllPacks", _UrlKt.FRAGMENT_ENCODE_SET, "cachedCustomPacks", "Ljava/util/Map;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "cachedPacksById", "Ljava/util/HashMap;", "iconPacksDirectory", "Ljava/io/File;", "Lkotlin/text/Regex;", "resourceNameRegex", "Lkotlin/text/Regex;", "Lorg/simplifiles/archive/security/SecurityPolicy;", "iconPackArchivePolicy", "Lorg/simplifiles/archive/security/SecurityPolicy;", "Lorg/simplifiles/archive/ArchiveSaveOptions;", "iconPackArchiveSaveOptions", "Lorg/simplifiles/archive/ArchiveSaveOptions;", "IconPackStorageException", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconPackStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPackStorage.kt\ncom/exteragram/messenger/icons/IconPackStorage\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,394:1\n1#2:395\n32#3,2:396\n14048#4,2:398\n1220#5,2:400\n1249#5,4:402\n*S KotlinDebug\n*F\n+ 1 IconPackStorage.kt\ncom/exteragram/messenger/icons/IconPackStorage\n*L\n95#1:396,2\n268#1:398,2\n275#1:400,2\n275#1:402,4\n*E\n"})
/* loaded from: classes.dex */
public final class IconPackStorage {
    public static final IconPackStorage INSTANCE = null;
    private static Map<String, IconPack> cachedCustomPacks;
    private static final HashMap<String, IconPack> cachedPacksById = null;
    private static final SecurityPolicy iconPackArchivePolicy = null;
    private static final ArchiveSaveOptions iconPackArchiveSaveOptions = null;
    private static volatile File iconPacksDirectory;
    private static final Regex resourceNameRegex = null;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/icons/IconPackStorage$IconPackStorageException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "error", "Lcom/exteragram/messenger/icons/IconPackStorageError;", "<init>", "(Lcom/exteragram/messenger/icons/IconPackStorageError;)V", "getError", "()Lcom/exteragram/messenger/icons/IconPackStorageError;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class IconPackStorageException extends Exception {
        private final IconPackStorageError error;

        public IconPackStorageException(IconPackStorageError r2) {
            super(r2.name());
            this.error = r2;
        }

        public final IconPackStorageError getError() {
            return this.error;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconPackStorage$bundlePack$2", f = "IconPackStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconPackStorage$bundlePack$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
        final /* synthetic */ String $packId;
        int label;

        public AnonymousClass2(String r1, Continuation<? super AnonymousClass2> r2) {
            this.$packId = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.$packId, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super File> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super File> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L23;
            ResultKt.throwOnFailure(r6);
            IconPackStorage r62 = IconPackStorage.INSTANCE;
            if (IconPackStorage.access$isValidPackId(r62, this.$packId) == true) goto L7;
            return null;
        L7:
            IconPack r0 = r62.findPackById(this.$packId);
            if (r0 != null) goto L10;
            return null;
        L10:
            SimpliDirectory r63 = SimpliFiles.directory(new File(r62.getIconPacksDirectory(), this.$packId));
            if (r63.exists() == true) goto L13;
            return null;
        L13:
            SimpliDirectory r2 = SimpliFiles.directory(new File(ApplicationLoader.applicationContext.getCacheDir(), "shared_packs"));
        L17:
            e = move-exception;
            FileLog.e("Failed to bundle pack: " + this.$packId, e);
            return null;
        L15:
            if (r2.exists() == false) goto L19;
            r2.deleteRecursively();     // Catch: Exception -> L17
        L19:
            r2.create();     // Catch: Exception -> L17
            return r63.zipTo(r2.file(FileManager.fileNameFromUserString(r0.getName()) + ".icons").getFile(), IconPackStorage.access$getIconPackArchiveSaveOptions$p()).getFile();
        L23:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconPackStorage$bundlePackBlocking$1", f = "IconPackStorage.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconPackStorage$bundlePackBlocking$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
        final /* synthetic */ String $packId;
        int label;

        public AnonymousClass1(String r1, Continuation<? super AnonymousClass1> r2) {
            this.$packId = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.$packId, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super File> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super File> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            IconPackStorage r42 = IconPackStorage.INSTANCE;
            String r12 = this.$packId;
            this.label = 1;
            Object r3 = r42.bundlePack(r12, this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/exteragram/messenger/icons/IconPackStorageResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconPackStorage$installPack$2", f = "IconPackStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nIconPackStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPackStorage.kt\ncom/exteragram/messenger/icons/IconPackStorage$installPack$2\n+ 2 IconPackStorage.kt\ncom/exteragram/messenger/icons/IconPackStorage\n*L\n1#1,394:1\n177#2,6:395\n*S KotlinDebug\n*F\n+ 1 IconPackStorage.kt\ncom/exteragram/messenger/icons/IconPackStorage$installPack$2\n*L\n323#1:395,6\n*E\n"})
    /* renamed from: com.exteragram.messenger.icons.IconPackStorage$installPack$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01492 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IconPackStorageResult<? extends Unit>>, Object> {
        final /* synthetic */ File $file;
        private /* synthetic */ Object L$0;
        int label;

        public C01492(File r1, Continuation<? super C01492> r2) {
            this.$file = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            C01492 r0 = new C01492(this.$file, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super IconPackStorageResult<? extends Unit>> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super IconPackStorageResult<Unit>> r2) {
            return ((C01492) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            CoroutineScope r0 = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L60;
            ResultKt.throwOnFailure(r8);
            IconPackStorage r1 = IconPackStorage.INSTANCE;     // Catch: Exception -> L54 IconPackStorageException -> L57
            File r7 = this.$file;     // Catch: Exception -> L54 IconPackStorageException -> L57
            SimpliDirectory r82 = IconPackStorage.access$createTempCacheDirectory(r1, "install");     // Catch: Exception -> L54 IconPackStorageException -> L57
            String r72 = IconPackStorage.parseMetadata$default(r1, IconPackStorage.access$extractPackArchive(r1, r7, r82.getFile()), r82.getFile(), null, 4, null).getId();     // Catch: Throwable -> L18
            File r2 = new File(r1.getIconPacksDirectory(), r72);     // Catch: Throwable -> L18
            File r02 = new File(r1.getIconPacksDirectory(), r72 + "_trash_" + System.currentTimeMillis());     // Catch: Throwable -> L18
            SimpliDirectory r73 = SimpliFiles.directory(r2);     // Catch: Throwable -> L18
            SimpliDirectory r3 = SimpliFiles.directory(r02);     // Catch: Throwable -> L18
            if (r73.exists() == false) goto L67;
            Result.Companion r4 = Result.Companion;     // Catch: Throwable -> L10
            Object r03 = Result.m2018constructorimpl(r73.moveTo(r02, OverwritePolicy.ERROR));     // Catch: Throwable -> L10
        L13:
            if (Result.m2021exceptionOrNullimpl(r03) == null) goto L67;
            if (r73.deleteRecursively() == true) goto L67;
            Object r74 = new IconPackStorageResult.Failure(IconPackStorageError.STORAGE_ERROR);     // Catch: Throwable -> L18
        L47:
            IconPackStorage.access$deleteDirectoryIfExists(r1, r82);     // Catch: Exception -> L54 IconPackStorageException -> L57
            return r74;
        L10:
            th = move-exception;
            Result.Companion r42 = Result.Companion;     // Catch: Throwable -> L18
            r03 = Result.m2018constructorimpl(ResultKt.createFailure(th));     // Catch: Throwable -> L18
        L67:
            Result.Companion r04 = Result.Companion;     // Catch: Throwable -> L22
            Object r05 = Result.m2018constructorimpl(r82.moveTo(r2, OverwritePolicy.ERROR));     // Catch: Throwable -> L22
        L25:
            if (Result.m2021exceptionOrNullimpl(r05) == null) goto L40;
            Object r06 = Result.m2018constructorimpl(r82.copyTo(r2, OverwritePolicy.ERROR));     // Catch: Throwable -> L28
        L31:
            if (Result.m2021exceptionOrNullimpl(r06) == null) goto L40;
            if (r3.exists() == true) goto L35;
        L38:
            r74 = new IconPackStorageResult.Failure(IconPackStorageError.STORAGE_ERROR);     // Catch: Throwable -> L18
            goto L47
        L35:
            if (r73.exists() == false) goto L37;
            r73.deleteRecursively();     // Catch: Throwable -> L18
        L37:
            r3.moveTo(r2, OverwritePolicy.ERROR);     // Catch: Throwable -> L18
        L28:
            th = move-exception;
            Result.Companion r43 = Result.Companion;     // Catch: Throwable -> L18
            r06 = Result.m2018constructorimpl(ResultKt.createFailure(th));     // Catch: Throwable -> L18
        L40:
            if (r3.exists() == false) goto L42;
            r3.deleteRecursively();     // Catch: Throwable -> L18
        L42:
            IconPackStorage r75 = IconPackStorage.INSTANCE;     // Catch: Throwable -> L18
            monitor-enter(r75);     // Catch: Throwable -> L18
            IconPackStorage.access$invalidateAllPacks(r75);     // Catch: Throwable -> L49
            monitor-exit(r75);     // Catch: Throwable -> L18
            r74 = new IconPackStorageResult.Success(Unit.INSTANCE);     // Catch: Throwable -> L18
            goto L47
        L49:
            th = move-exception;
            throw th;     // Catch: Throwable -> L18
        L22:
            th = move-exception;
            Result.Companion r44 = Result.Companion;     // Catch: Throwable -> L18
            r05 = Result.m2018constructorimpl(ResultKt.createFailure(th));     // Catch: Throwable -> L18
        L18:
            th = move-exception;
            IconPackStorage.access$deleteDirectoryIfExists(r1, r82);     // Catch: Exception -> L54 IconPackStorageException -> L57
            throw th;     // Catch: Exception -> L54 IconPackStorageException -> L57
        L57:
            e = move-exception;
            FileLog.e("Pack installation failed: " + e.getError());
            IconPackStorageResult.Failure r83 = new IconPackStorageResult.Failure(e.getError());
        L59:
            return r83;
        L54:
            e = move-exception;
            FileLog.e("Pack installation failed", e);
            r83 = new IconPackStorageResult.Failure(IconPackStorage.access$errorFromException(IconPackStorage.INSTANCE, e));
            goto L59
        L60:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/exteragram/messenger/icons/IconPackStorageResult;", "Lcom/exteragram/messenger/icons/IconPack;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconPackStorage$parsePackFromZip$2", f = "IconPackStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconPackStorage$parsePackFromZip$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01502 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IconPackStorageResult<? extends IconPack>>, Object> {
        final /* synthetic */ File $file;
        int label;

        public C01502(File r1, Continuation<? super C01502> r2) {
            this.$file = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01502(this.$file, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super IconPackStorageResult<? extends IconPack>> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super IconPackStorageResult<IconPack>> r2) {
            return ((C01502) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L14;
            ResultKt.throwOnFailure(r4);
            IconPackStorage r42 = IconPackStorage.INSTANCE;
            SimpliDirectory r0 = IconPackStorage.access$createTempCacheDirectory(r42, "preview");
            return new IconPackStorageResult.Success(IconPackStorage.access$parseMetadata(r42, IconPackStorage.access$extractPackArchive(r42, this.$file, r0.getFile()), r0.getFile(), r0.getFile()));
        L9:
            e = move-exception;
            FileLog.e("Failed to parse pack for preview: " + e.getError());
            IconPackStorage.access$deleteDirectoryIfExists(IconPackStorage.INSTANCE, r0);
            return new IconPackStorageResult.Failure(e.getError());
        L7:
            e = move-exception;
            FileLog.e("Failed to parse pack for preview", e);
            IconPackStorage r43 = IconPackStorage.INSTANCE;
            IconPackStorage.access$deleteDirectoryIfExists(r43, r0);
            return new IconPackStorageResult.Failure(IconPackStorage.access$errorFromException(r43, e));
        L14:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    static {
        INSTANCE = new IconPackStorage();
        cachedPacksById = new HashMap();
        resourceNameRegex = new Regex("[A-Za-z0-9_]+");
        iconPackArchivePolicy = SecurityPolicy.Companion.builder().maxEntries(3000).maxTotalUncompressedSize(SaveToGallerySettingsHelper.DEFAULT_VIDEO_LIMIT).maxSingleFileSize(10485760).maxCompressionRatio(250.0d).build();
        iconPackArchiveSaveOptions = ArchiveSaveOptions.Companion.builder().compressionLevel(0).build();
    }

    private IconPackStorage() {
    }

    public static final /* synthetic */ SimpliDirectory access$createTempCacheDirectory(IconPackStorage r0, String r1) {
        return r0.createTempCacheDirectory(r1);
    }

    public static final /* synthetic */ void access$deleteDirectoryIfExists(IconPackStorage r0, SimpliDirectory r1) {
        r0.deleteDirectoryIfExists(r1);
    }

    public static final /* synthetic */ IconPackStorageError access$errorFromException(IconPackStorage r0, Exception r1) {
        return r0.errorFromException(r1);
    }

    public static final /* synthetic */ JSONObject access$extractPackArchive(IconPackStorage r0, File r1, File r2) {
        return r0.extractPackArchive(r1, r2);
    }

    public static final /* synthetic */ ArchiveSaveOptions access$getIconPackArchiveSaveOptions$p() {
        return iconPackArchiveSaveOptions;
    }

    public static final /* synthetic */ void access$invalidateAllPacks(IconPackStorage r0) {
        r0.invalidateAllPacks();
    }

    public static final /* synthetic */ boolean access$isValidPackId(IconPackStorage r0, String r1) {
        return r0.isValidPackId(r1);
    }

    public static final /* synthetic */ IconPack access$parseMetadata(IconPackStorage r0, JSONObject r1, File r2, File r3) {
        return r0.parseMetadata(r1, r2, r3);
    }

    private final SimpliDirectory createTempCacheDirectory(String r5) {
        return SimpliFiles.directory(new File(ApplicationLoader.applicationContext.getCacheDir(), r5 + "_" + System.currentTimeMillis())).create();
    }

    private final void deleteDirectoryIfExists(SimpliDirectory r1) {
        if (r1.exists() == false) goto L6;
        r1.deleteRecursively();
        return;
    }

    private final IconPackStorageError errorFromException(Exception r2) {
        if ((r2 instanceof IconPackStorageException) == false) goto L7;
        return ((IconPackStorageException) r2).getError();
    L7:
        if ((r2 instanceof ArchiveValidationException) == false) goto L11;
        return errorFromValidationException((ArchiveValidationException) r2);
    L11:
        if ((r2 instanceof CorruptedArchiveException) == true) goto L23;
        if ((r2 instanceof SecurityException) == false) goto L17;
        return IconPackStorageError.INVALID_METADATA;
    L17:
        if ((r2 instanceof JSONException) == false) goto L21;
        return IconPackStorageError.INVALID_METADATA;
    L21:
        return IconPackStorageError.UNKNOWN;
    L23:
        return IconPackStorageError.INVALID_ARCHIVE;
    }

    private final IconPackStorageError errorFromValidationException(ArchiveValidationException r1) {
        ArchiveIssue r0 = (ArchiveIssue) CollectionsKt.firstOrNull(r1.getReport().getIssues());
        if (r0 == null) goto L5;
        String r02 = r0.getCode();
    L6:
        if (r02 == null) goto L31;
        switch(r02.hashCode()) {
            case -1169710668: goto L26;
            case -964476182: goto L21;
            case 51248523: goto L16;
            case 1340485329: goto L11;
            default: goto L31;
        };
    L11:
        if (r02.equals("archive.entry.compression_ratio.too_high") == false) goto L31;
        return IconPackStorageError.COMPRESSION_RATIO_TOO_HIGH;
    L16:
        if (r02.equals("archive.entry.size.too_large") == false) goto L31;
        return IconPackStorageError.FILE_TOO_LARGE;
    L21:
        if (r02.equals("archive.total_size.too_large") == false) goto L31;
        return IconPackStorageError.ARCHIVE_TOO_LARGE;
    L26:
        if (r02.equals("archive.entries.too_many") == false) goto L31;
        return IconPackStorageError.TOO_MANY_FILES;
    L31:
        return IconPackStorageError.INVALID_ARCHIVE;
    L5:
        r02 = null;
        goto L6
    }

    private final JSONObject extractPackArchive(File r3, File r4) {
        ArchiveFile r2 = SimpliFiles.archive(r3).withPolicy(iconPackArchivePolicy).extractTo(r4).file("metadata.json");
        if (r2.exists() == true) goto L5;
        FileLog.e("Icon pack archive does not contain metadata.json");
        throw new IconPackStorageException(IconPackStorageError.MISSING_METADATA);
    L5:
        if (r2.getSize() <= MediaStatus.COMMAND_STREAM_TRANSFER) goto L7;
        FileLog.e("Icon pack metadata.json is too large");
        throw new IconPackStorageException(IconPackStorageError.METADATA_TOO_LARGE);
    L7:
        return new JSONObject(ArchiveFile.readText$default(r2, null, 1, null));
    }

    private final void invalidateAllPacks() {
        cachedCustomPacks = null;
        cachedPacksById.clear();
    }

    private final void invalidatePack(String r1) {
        cachedCustomPacks = null;
        cachedPacksById.remove(r1);
    }

    private final boolean isPlainFileName(String r5) {
        if (r5.length() != 0) goto L6;
    L22:
        return false;
    L6:
        if (Intrinsics.areEqual(r5, ".") == true) goto L22;
        if (Intrinsics.areEqual(r5, "..") == true) goto L22;
        int r4 = r5.length();
        int r1 = 0;
    L11:
        if (r1 >= r4) goto L20;
        char r2 = r5.charAt(r1);
        if (r2 == 0) goto L19;
        if (r2 == '/') goto L19;
        if (r2 == '\\') goto L19;
        r1 = r1 + 1;
    L19:
        return false;
    L20:
        return true;
    }

    private final boolean isValidPackId(String r4) {
        if (StringsKt.isBlank(r4) == false) goto L5;
    L14:
        return false;
    L5:
        if (StringsKt.contains$default(r4, '/', false, 2, null) == true) goto L14;
        if (StringsKt.contains$default(r4, '\\', false, 2, null) == true) goto L14;
        if (Intrinsics.areEqual(r4, ".") == true) goto L14;
        if (Intrinsics.areEqual(r4, "..") == true) goto L14;
        return true;
    }

    private final IconPack parseMetadata(JSONObject r12, File r13, File r14) {
        String r3 = r12.getString("packName");
        String r2 = r12.getString("packId");
        if (isValidPackId(r2) == false) goto L24;
        String r4 = r12.optString("author", "Unknown");
        String r5 = r12.optString("version", "1.0");
        JSONObject r11 = r12.optJSONObject("icons");
        LinkedHashMap r6 = new LinkedHashMap();
        if (r13 == null) goto L7;
        File r122 = r13.getCanonicalFile();
    L8:
        if (r11 == null) goto L22;
        Iterator<String> r0 = r11.keys();
        if (r0 == null) goto L22;
    L12:
        if (r0.hasNext() == false) goto L22;
        String r1 = r0.next();
        String r7 = r11.getString(r1);
        if (r13 == null) goto L20;
        if (r122 == null) goto L20;
        SimpliFiles.directory(r13).resolveInside(r7);
        if (INSTANCE.isPlainFileName(r7) == true) goto L20;
        r7 = FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo(new File(r13, r7).getCanonicalFile(), r122));
    L20:
        r6.put(r1, r7);
    L22:
        return new IconPack(r2, r3, r4, r5, r6, null, r14, 32, null);
    L7:
        r122 = null;
        goto L8
    L24:
        throw new SecurityException("Invalid icon pack id: " + r2);
    }

    public static /* synthetic */ IconPack parseMetadata$default(IconPackStorage r1, JSONObject r2, File r3, File r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = null;
    L9:
        return r1.parseMetadata(r2, r3, r4);
    }

    private final IconPack parseMetadataFile(File r9) {
        return parseMetadata$default(this, new JSONObject(SimpliFile.readText$default(SimpliFiles.file(r9), MediaStatus.COMMAND_STREAM_TRANSFER, null, 2, null)), r9.getParentFile(), null, 4, null);
    L4:
        e = move-exception;
        FileLog.e("Error parsing metadata.json", e);
        return null;
    }

    public final Object bundlePack(String r3, Continuation<? super File> r4) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(r3, null), r4);
    }

    public final File bundlePackBlocking(String r2) {
        return (File) BuildersKt.runBlockingK$default(null, new AnonymousClass1(r2, null), 1, null);
    }

    public final synchronized void deletePack(String r3) {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L4:
        if (isValidPackId(r3) == true) goto L7;
        monitor-exit(this);
        return;
    L7:
        SimpliDirectory r0 = SimpliFiles.directory(new File(getIconPacksDirectory(), r3));     // Catch: Throwable -> L11
        if (r0.exists() == false) goto L13;
        deleteDirectoryIfExists(r0);     // Catch: Throwable -> L11
        invalidatePack(r3);     // Catch: Throwable -> L11
    L13:
        monitor-exit(this);
    }

    public final synchronized IconPack findPackById(String r5) {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        IconPack r1 = null;
        if (isValidPackId(r5) == true) goto L8;
        monitor-exit(this);
        return null;
    L8:
        Map<String, IconPack> r0 = cachedCustomPacks;     // Catch: Throwable -> L14
        if (r0 == null) goto L16;
        IconPack r02 = r0.get(r5);     // Catch: Throwable -> L14
        if (r02 == null) goto L16;
        monitor-exit(this);
        return r02;
    L16:
        HashMap<String, IconPack> r03 = cachedPacksById;     // Catch: Throwable -> L14
        if (r03.containsKey(r5) == false) goto L21;
        IconPack r52 = r03.get(r5);     // Catch: Throwable -> L14
        monitor-exit(this);
        return r52;
    L21:
        SimpliDirectory r2 = SimpliFiles.directory(new File(getIconPacksDirectory(), r5));     // Catch: Throwable -> L14
        if (r2.exists() == false) goto L26;
        SimpliFile r22 = r2.file("metadata.json");     // Catch: Throwable -> L14
        if (r22.exists() == false) goto L26;
        r1 = parseMetadataFile(r22.getFile());     // Catch: Throwable -> L14
    L26:
        r03.put(r5, r1);     // Catch: Throwable -> L14
        monitor-exit(this);
        return r1;
    }

    public final synchronized List<IconPack> getCustomPacks() {
        monitor-enter(this);
        Map<String, IconPack> r0 = cachedCustomPacks;     // Catch: Throwable -> L10
        if (r0 == null) goto L12;
        Collection<IconPack> r02 = r0.values();     // Catch: Throwable -> L10
        if (r02 == null) goto L12;
        ArrayList r1 = new ArrayList(r02);     // Catch: Throwable -> L10
        monitor-exit(this);
        return r1;
    L12:
        ArrayList r03 = new ArrayList();     // Catch: Throwable -> L10
        File[] r12 = getIconPacksDirectory().listFiles();     // Catch: Throwable -> L10
        int r2 = 0;
        if (r12 == null) goto L22;
        int r3 = r12.length;     // Catch: Throwable -> L10
        int r4 = 0;
    L15:
        if (r4 >= r3) goto L22;
        SimpliFile r5 = SimpliFiles.directory(r12[r4]).file("metadata.json");     // Catch: Throwable -> L10
        if (r5.exists() == false) goto L21;
        IconPack r52 = INSTANCE.parseMetadataFile(r5.getFile());     // Catch: Throwable -> L10
        if (r52 == null) goto L21;
        r03.add(r52);     // Catch: Throwable -> L10
    L21:
        r4 = r4 + 1;     // Catch: Throwable -> L10
    L22:
        LinkedHashMap r32 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r03, 10)), 16));     // Catch: Throwable -> L10
        int r13 = r03.size();     // Catch: Throwable -> L10
    L23:
        if (r2 >= r13) goto L25;
        Object r42 = r03.get(r2);     // Catch: Throwable -> L10
        r2 = r2 + 1;     // Catch: Throwable -> L10
        r32.put(((IconPack) r42).getId(), r42);     // Catch: Throwable -> L10
        goto L23
    L25:
        cachedCustomPacks = r32;     // Catch: Throwable -> L10
        cachedPacksById.clear();     // Catch: Throwable -> L10
        monitor-exit(this);
        return r03;
    L10:
        th = move-exception;
        throw th;
    }

    public final File getIconPacksDirectory() {
        File r2 = iconPacksDirectory;
        if (r2 == null) goto L5;
        return r2;
    L5:
        File r0 = new File(ApplicationLoader.applicationContext.getFilesDir(), "icon_packs");
        SimpliFiles.directory(r0).create();
        iconPacksDirectory = r0;
        return r0;
    }

    public final Object installPack(File r3, Continuation<? super IconPackStorageResult<Unit>> r4) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C01492(r3, null), r4);
    }

    public final Object parsePackFromZip(File r3, Continuation<? super IconPackStorageResult<IconPack>> r4) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C01502(r3, null), r4);
    }

    public final File resolveIconFile(String r5, String r6) {
        if (isValidPackId(r5) == true) goto L5;
    L23:
        return null;
    L5:
        if (resourceNameRegex.matches(r6) == false) goto L23;
        IconPack r0 = findPackById(r5);     // Catch: Exception -> L19
        if (r0 != null) goto L10;
        return null;
    L10:
        String r62 = r0.getIcons().get(r6);     // Catch: Exception -> L19
        if (r62 != null) goto L13;
        return null;
    L13:
        File r4 = new File(getIconPacksDirectory(), r5).getCanonicalFile();     // Catch: Exception -> L19
        File r63 = new File(r4, r62).getCanonicalFile();     // Catch: Exception -> L19
        if (StringsKt.startsWith$default(r63.getPath(), r4.getPath() + File.separator, false, 2, null) == false) goto L21;
        if (r63.isFile() == false) goto L21;
        return r63;
    L21:
        return null;
    L19:
        e = move-exception;
        FileLog.e("Failed to resolve icon file for pack " + r5, e);
        goto L23
    }

    public final synchronized boolean saveIconPackMetadata(IconPack r9) {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (isValidPackId(r9.getId()) == true) goto L26;
        FileLog.e("Invalid icon pack id: " + r9.getId());     // Catch: Throwable -> L9
        monitor-exit(this);
        return false;
    L26:
        SimpliFile r0 = SimpliFiles.directory(new File(getIconPacksDirectory(), r9.getId())).create().file("metadata.json");     // Catch: Throwable -> L9 Exception -> L15
        JSONObject r1 = new JSONObject();     // Catch: Throwable -> L9 Exception -> L15
        r1.put("schemaVersion", 1);     // Catch: Throwable -> L9 Exception -> L15
        r1.put("packName", r9.getName());     // Catch: Throwable -> L9 Exception -> L15
        r1.put("packId", r9.getId());     // Catch: Throwable -> L9 Exception -> L15
        r1.put("author", r9.getAuthor());     // Catch: Throwable -> L9 Exception -> L15
        r1.put("version", r9.getVersion());     // Catch: Throwable -> L9 Exception -> L15
        JSONObject r3 = new JSONObject();     // Catch: Throwable -> L9 Exception -> L15
        Iterator<Map.Entry<String, String>> r5 = r9.getIcons().entrySet().iterator();     // Catch: Throwable -> L9 Exception -> L15
    L13:
        if (r5.hasNext() == false) goto L17;
        Map.Entry<String, String> r6 = r5.next();     // Catch: Throwable -> L9 Exception -> L15
        r3.put(r6.getKey(), r6.getValue());     // Catch: Throwable -> L9 Exception -> L15
        goto L13
    L17:
        r1.put("icons", r3);     // Catch: Throwable -> L9 Exception -> L15
        SimpliFile.writeTextAtomic$default(r0, r1.toString(4), null, 2, null);     // Catch: Throwable -> L9 Exception -> L15
        invalidatePack(r9.getId());     // Catch: Throwable -> L9 Exception -> L15
        monitor-exit(this);
        return true;
    L15:
        e = move-exception;
        FileLog.e("Error saving metadata", e);     // Catch: Throwable -> L9
        return false;
    }
}
