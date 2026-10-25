.class public final Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/datasource/DataSource$Factory;


# instance fields
.field private final baseDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

.field private final context:Landroid/content/Context;

.field private final listener:Landroidx/media3/datasource/TransferListener;

.field private final mtprotoUris:Landroid/util/LongSparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/LongSparseArray<",
            "Landroid/net/Uri;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroidx/media3/datasource/TransferListener;Landroidx/media3/datasource/DataSource$Factory;)V
    .locals 1

    .line 57
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 63
    new-instance v0, Landroid/util/LongSparseArray;

    invoke-direct {v0}, Landroid/util/LongSparseArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->mtprotoUris:Landroid/util/LongSparseArray;

    .line 58
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->context:Landroid/content/Context;

    .line 59
    iput-object p2, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->listener:Landroidx/media3/datasource/TransferListener;

    .line 60
    iput-object p3, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->baseDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    .line 33
    invoke-direct {p0, p1, p2, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;-><init>(Landroid/content/Context;Ljava/lang/String;Landroidx/media3/datasource/TransferListener;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;Landroidx/media3/datasource/TransferListener;)V
    .locals 1

    .line 43
    new-instance v0, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    invoke-direct {v0}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;-><init>()V

    .line 44
    invoke-virtual {v0, p2}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setUserAgent(Ljava/lang/String;)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    .line 45
    invoke-virtual {p2, p3}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setTransferListener(Landroidx/media3/datasource/TransferListener;)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    const/4 v0, 0x1

    .line 46
    invoke-virtual {p2, v0}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setAllowCrossProtocolRedirects(Z)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    .line 43
    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;-><init>(Landroid/content/Context;Landroidx/media3/datasource/TransferListener;Landroidx/media3/datasource/DataSource$Factory;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic createDataSource()Landroidx/media3/datasource/DataSource;
    .locals 0

    .line 21
    invoke-virtual {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->createDataSource()Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;

    move-result-object p0

    return-object p0
.end method

.method public createDataSource()Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;
    .locals 4

    .line 67
    new-instance v0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;

    iget-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->context:Landroid/content/Context;

    iget-object v2, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->listener:Landroidx/media3/datasource/TransferListener;

    iget-object v3, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->baseDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    invoke-interface {v3}, Landroidx/media3/datasource/DataSource$Factory;->createDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v3

    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->mtprotoUris:Landroid/util/LongSparseArray;

    invoke-direct {v0, v1, v2, v3, p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;-><init>(Landroid/content/Context;Landroidx/media3/datasource/TransferListener;Landroidx/media3/datasource/DataSource;Landroid/util/LongSparseArray;)V

    return-object v0
.end method

.method public putDocumentUri(JLandroid/net/Uri;)V
    .locals 0

    .line 71
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->mtprotoUris:Landroid/util/LongSparseArray;

    invoke-virtual {p0, p1, p2, p3}, Landroid/util/LongSparseArray;->put(JLjava/lang/Object;)V

    return-void
.end method
