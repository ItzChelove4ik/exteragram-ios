.class public final Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0008\u00c6\u0002\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0012\u0010\u0008\u001a\u00020\t2\u0008\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0010\u0010\u000c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J(\u0010\r\u001a\u00020\t2\u0008\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0008\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\u0008\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J&\u0010\u0013\u001a\u00020\t2\u0008\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0008\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\u0008\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0016\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0008\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;",
        "",
        "<init>",
        "()V",
        "MAX_FILE_SIZE",
        "",
        "MAX_BLOCK_LENGTH",
        "SNIFF_LENGTH",
        "canOpen",
        "",
        "file",
        "Ljava/io/File;",
        "isText",
        "openExternally",
        "fragment",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
        "fileName",
        "",
        "isZip",
        "open",
        "createMessageObject",
        "Lorg/telegram/messenger/MessageObject;",
        "normalizeFileName",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nPluginFileViewer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginFileViewer.kt\ncom/exteragram/messenger/plugins/ui/components/PluginFileViewer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,198:1\n2792#2,3:199\n1#3:202\n*S KotlinDebug\n*F\n+ 1 PluginFileViewer.kt\ncom/exteragram/messenger/plugins/ui/components/PluginFileViewer\n*L\n49#1:199,3\n*E\n"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

.field private static final MAX_BLOCK_LENGTH:I = 0x2000

.field private static final MAX_FILE_SIZE:I = 0x80000

.field private static final SNIFF_LENGTH:I = 0x2000


# direct methods
.method public static $r8$lambda$0xZUrpuuyQT7Lkt43HYs6kdYNiE(Lorg/telegram/ui/ActionBar/AlertDialog;[ZLorg/telegram/messenger/MessageObject;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 0

    .line 138
    :try_start_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    const/4 p0, 0x0

    .line 141
    aget-boolean p1, p1, p0

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_1

    .line 145
    invoke-virtual {p3, p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->createArticleViewer(Z)Lorg/telegram/ui/ArticleViewer;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/telegram/ui/ArticleViewer;->open(Lorg/telegram/messenger/MessageObject;)Z

    goto :goto_0

    .line 147
    :cond_1
    invoke-static {p3}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 148
    sget p1, Lorg/telegram/messenger/R$raw;->error:I

    sget p2, Lorg/telegram/messenger/R$string;->ErrorOccurred:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 149
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :goto_0
    return-void
.end method

.method public static $r8$lambda$vQwAKkakqkeP6_e0FaokhJK-Wvo(Ljava/io/File;Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;[ZLorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 1

    .line 130
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->normalizeFileName(Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->createMessageObject(Ljava/io/File;Ljava/lang/String;)Lorg/telegram/messenger/MessageObject;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    .line 132
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    .line 136
    :goto_0
    new-instance p1, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer$$ExternalSyntheticLambda2;

    invoke-direct {p1, p2, p3, p0, p4}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;[ZLorg/telegram/messenger/MessageObject;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$vbpziXMO_H-wI7YqbW1Hz2wiHAQ([ZLandroid/content/DialogInterface;)V
    .locals 1

    const/4 p1, 0x0

    const/4 v0, 0x1

    .line 125
    aput-boolean v0, p0, p1

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 31
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final canOpen(Ljava/io/File;)Z
    .locals 4
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    if-eqz p0, :cond_1

    .line 38
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Ljava/io/File;->isFile()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Ljava/io/File;->length()J

    move-result-wide v0

    const-wide/32 v2, 0x80000

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    goto :goto_0

    .line 41
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->isText(Ljava/io/File;)Z

    move-result p0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method private final createMessageObject(Ljava/io/File;Ljava/lang/String;)Lorg/telegram/messenger/MessageObject;
    .locals 7

    .line 158
    sget-object p0, Lkotlin/text/Charsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-static {p1, p0}, Lkotlin/io/FilesKt;->readText(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/lang/String;

    move-result-object p0

    .line 160
    new-instance v0, Lorg/telegram/tgnet/tl/TL_iv$TL_page;

    invoke-direct {v0}, Lorg/telegram/tgnet/tl/TL_iv$TL_page;-><init>()V

    .line 161
    iput-object p1, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->local:Ljava/io/File;

    .line 162
    iput-object p2, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    .line 164
    iget-object v1, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->blocks:Ljava/util/ArrayList;

    const-string v2, "python"

    const/16 v3, 0x2000

    invoke-static {v1, p0, v2, v3}, Lcom/exteragram/messenger/utils/MarkdownUtils;->appendPreformattedBlocks(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;I)V

    .line 166
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_webPage;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_webPage;-><init>()V

    .line 167
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result p1

    int-to-long v1, p1

    iput-wide v1, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    .line 168
    iput-object p2, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    .line 169
    iput-object p2, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->display_url:Ljava/lang/String;

    .line 170
    iput-object p2, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->title:Ljava/lang/String;

    .line 171
    iget p1, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->flags:I

    or-int/lit16 p1, p1, 0x404

    iput p1, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->flags:I

    .line 172
    iput-object v0, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    .line 175
    sget p1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object p1

    iget-wide v0, p1, Lorg/telegram/messenger/UserConfig;->clientUserId:J

    .line 176
    new-instance p1, Lorg/telegram/tgnet/TLRPC$TL_message;

    invoke-direct {p1}, Lorg/telegram/tgnet/TLRPC$TL_message;-><init>()V

    const/4 v2, 0x0

    .line 177
    iput v2, p1, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    .line 178
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    const-wide/16 v5, 0x3e8

    div-long/2addr v3, v5

    long-to-int v3, v3

    iput v3, p1, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    .line 179
    iput-object p2, p1, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const/4 p2, 0x1

    .line 180
    iput-boolean p2, p1, Lorg/telegram/tgnet/TLRPC$Message;->out:Z

    .line 181
    new-instance v3, Lorg/telegram/tgnet/TLRPC$TL_peerUser;

    invoke-direct {v3}, Lorg/telegram/tgnet/TLRPC$TL_peerUser;-><init>()V

    .line 182
    iput-wide v0, v3, Lorg/telegram/tgnet/TLRPC$Peer;->user_id:J

    .line 181
    iput-object v3, p1, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 184
    new-instance v3, Lorg/telegram/tgnet/TLRPC$TL_peerUser;

    invoke-direct {v3}, Lorg/telegram/tgnet/TLRPC$TL_peerUser;-><init>()V

    .line 185
    iput-wide v0, v3, Lorg/telegram/tgnet/TLRPC$Peer;->user_id:J

    .line 184
    iput-object v3, p1, Lorg/telegram/tgnet/TLRPC$Message;->from_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 187
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_messageMediaWebPage;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_messageMediaWebPage;-><init>()V

    .line 188
    iput-object p0, v0, Lorg/telegram/tgnet/TLRPC$MessageMedia;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 187
    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    .line 191
    new-instance p0, Lorg/telegram/messenger/MessageObject;

    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-direct {p0, v0, p1, v2, p2}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;ZZ)V

    return-object p0
.end method

.method private final isText(Ljava/io/File;)Z
    .locals 2

    const/4 p0, 0x0

    .line 45
    :try_start_0
    new-instance v0, Ljava/io/FileInputStream;

    .line 46
    invoke-direct {v0, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    const/16 p1, 0x2000

    .line 47
    :try_start_1
    new-array p1, p1, [B

    .line 48
    invoke-virtual {v0, p1}, Ljava/io/FileInputStream;->read([B)I

    move-result v1

    if-lez v1, :cond_3

    .line 49
    invoke-static {p1, v1}, Lkotlin/collections/ArraysKt;->take([BI)Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 2792
    instance-of v1, p1, Ljava/util/Collection;

    if-eqz v1, :cond_0

    move-object v1, p1

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_3

    .line 2793
    :cond_0
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Number;

    invoke-virtual {v1}, Ljava/lang/Number;->byteValue()B

    move-result v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-nez v1, :cond_1

    goto :goto_1

    :cond_2
    :goto_0
    const/4 p1, 0x1

    goto :goto_2

    :cond_3
    :goto_1
    move p1, p0

    :goto_2
    const/4 v1, 0x0

    .line 46
    :try_start_2
    invoke-static {v0, v1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    return p1

    :catchall_1
    move-exception p1

    goto :goto_4

    :goto_3
    :try_start_3
    throw p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    :catchall_2
    move-exception v1

    :try_start_4
    invoke-static {v0, p1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v1
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 52
    :goto_4
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return p0
.end method

.method private final isZip(Ljava/io/File;)Z
    .locals 5

    const/4 p0, 0x0

    .line 98
    :try_start_0
    new-instance v0, Ljava/io/FileInputStream;

    .line 99
    invoke-direct {v0, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 p1, 0x4

    .line 100
    :try_start_1
    new-array v1, p1, [B

    .line 101
    invoke-virtual {v0, v1}, Ljava/io/FileInputStream;->read([B)I

    move-result v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    const/4 v3, 0x0

    if-eq v2, p1, :cond_0

    .line 102
    :try_start_2
    invoke-static {v0, v3}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    return p0

    :catchall_0
    move-exception p1

    goto :goto_2

    .line 104
    :cond_0
    :try_start_3
    aget-byte p1, v1, p0

    const/16 v2, 0x50

    if-ne p1, v2, :cond_1

    const/4 p1, 0x1

    aget-byte v2, v1, p1

    const/16 v4, 0x4b

    if-ne v2, v4, :cond_1

    const/4 v2, 0x2

    .line 105
    aget-byte v1, v1, v2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    const/4 v2, 0x3

    if-eq v1, v2, :cond_2

    const/4 v2, 0x5

    if-eq v1, v2, :cond_2

    const/4 v2, 0x7

    if-ne v1, v2, :cond_1

    goto :goto_0

    :catchall_1
    move-exception p1

    goto :goto_1

    :cond_1
    move p1, p0

    .line 99
    :cond_2
    :goto_0
    :try_start_4
    invoke-static {v0, v3}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    return p1

    :goto_1
    :try_start_5
    throw p1
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    :catchall_2
    move-exception v1

    :try_start_6
    invoke-static {v0, p1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v1
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 108
    :goto_2
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return p0
.end method

.method private final normalizeFileName(Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    const/4 p0, 0x0

    if-eqz p2, :cond_1

    .line 195
    invoke-static {p2}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    move-object p2, p0

    :goto_0
    if-nez p2, :cond_2

    :cond_1
    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p2

    :cond_2
    const/4 p1, 0x0

    const/4 v0, 0x2

    const/16 v1, 0x2e

    .line 196
    invoke-static {p2, v1, p1, v0, p0}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_3

    return-object p2

    :cond_3
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ".plugin"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic open$default(Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;ILjava/lang/Object;)Z
    .locals 0

    and-int/lit8 p4, p4, 0x4

    if-eqz p4, :cond_0

    const/4 p3, 0x0

    .line 113
    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->open(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public static final openExternally(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z
    .locals 8
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    const/4 v1, 0x0

    if-eqz p0, :cond_b

    .line 59
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v5

    if-nez v5, :cond_0

    goto/16 :goto_6

    :cond_0
    if-eqz p1, :cond_b

    .line 60
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_b

    invoke-virtual {p1}, Ljava/io/File;->isFile()Z

    move-result v0

    if-nez v0, :cond_1

    goto/16 :goto_6

    .line 64
    :cond_1
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->isZip(Ljava/io/File;)Z

    move-result v2

    if-nez v2, :cond_2

    .line 65
    invoke-direct {v0, p1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->isText(Ljava/io/File;)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 v0, 0x1

    goto :goto_0

    :cond_2
    move v0, v1

    :goto_0
    const/4 v3, 0x0

    if-eqz p2, :cond_4

    .line 66
    invoke-static {p2}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_3

    goto :goto_1

    :cond_3
    move-object p2, v3

    :goto_1
    if-nez p2, :cond_5

    :cond_4
    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p2

    :cond_5
    const/16 v4, 0x2e

    const/4 v6, 0x2

    .line 67
    invoke-static {p2, v4, v1, v6, v3}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_8

    if-eqz v2, :cond_6

    .line 69
    const-string v3, ".zip"

    goto :goto_2

    :cond_6
    if-eqz v0, :cond_7

    .line 70
    const-string v3, ".txt"

    goto :goto_2

    .line 71
    :cond_7
    const-string v3, ".bin"

    :goto_2
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    :cond_8
    move-object v3, p2

    if-eqz v2, :cond_9

    .line 80
    :try_start_0
    const-string p2, "application/zip"

    :goto_3
    move-object v4, p2

    goto :goto_4

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto :goto_5

    :cond_9
    if-eqz v0, :cond_a

    .line 81
    const-string p2, "text/plain"

    goto :goto_3

    .line 82
    :cond_a
    const-string p2, "application/octet-stream"

    goto :goto_3

    .line 85
    :goto_4
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v6

    const/4 v7, 0x0

    move-object v2, p1

    .line 76
    invoke-static/range {v2 .. v7}, Lorg/telegram/messenger/AndroidUtilities;->openForView(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)Z

    move-result p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return p0

    .line 89
    :goto_5
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 90
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 91
    sget p1, Lorg/telegram/messenger/R$raw;->error:I

    sget p2, Lorg/telegram/messenger/R$string;->ErrorOccurred:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 92
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_b
    :goto_6
    return v1
.end method

.method public static synthetic openExternally$default(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;ILjava/lang/Object;)Z
    .locals 0

    and-int/lit8 p3, p3, 0x4

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 57
    :cond_0
    invoke-static {p0, p1, p2}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->openExternally(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z

    move-result p0

    return p0
.end method


# virtual methods
.method public final open(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z
    .locals 7

    const/4 p0, 0x0

    if-eqz p1, :cond_3

    .line 114
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_3

    .line 115
    invoke-virtual {p2}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-virtual {p2}, Ljava/io/File;->isFile()Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 118
    :cond_1
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->canOpen(Ljava/io/File;)Z

    move-result v1

    if-nez v1, :cond_2

    .line 119
    invoke-static {p1, p2, p3}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->openExternally(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z

    move-result p0

    return p0

    .line 122
    :cond_2
    new-instance v3, Lorg/telegram/ui/ActionBar/AlertDialog;

    const/4 v1, 0x3

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v2

    invoke-direct {v3, v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog;-><init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 123
    invoke-virtual {v3, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->setCanceledOnTouchOutside(Z)V

    const/4 v6, 0x1

    .line 124
    new-array v4, v6, [Z

    aput-boolean p0, v4, p0

    .line 125
    new-instance p0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer$$ExternalSyntheticLambda0;

    invoke-direct {p0, v4}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer$$ExternalSyntheticLambda0;-><init>([Z)V

    invoke-virtual {v3, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)V

    const-wide/16 v0, 0x96

    .line 126
    invoke-virtual {v3, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog;->showDelayed(J)V

    .line 128
    sget-object p0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer$$ExternalSyntheticLambda1;

    move-object v5, p1

    move-object v1, p2

    move-object v2, p3

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer$$ExternalSyntheticLambda1;-><init>(Ljava/io/File;Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;[ZLorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return v6

    :cond_3
    :goto_0
    return p0
.end method
