.class Lorg/chromium/net/impl/AndroidUploadDataProviderWrapper;
.super Landroid/net/http/UploadDataProvider;
.source "SourceFile"


# instance fields
.field private final mBackend:Lorg/chromium/net/UploadDataProvider;


# direct methods
.method public constructor <init>(Lorg/chromium/net/UploadDataProvider;)V
    .locals 1

    .line 21
    invoke-direct {p0}, Landroid/net/http/UploadDataProvider;-><init>()V

    .line 22
    const-string v0, "Invalid UploadDataProvider."

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUploadDataProviderWrapper;->mBackend:Lorg/chromium/net/UploadDataProvider;

    return-void
.end method


# virtual methods
.method public close()V
    .locals 0

    .line 45
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUploadDataProviderWrapper;->mBackend:Lorg/chromium/net/UploadDataProvider;

    invoke-virtual {p0}, Lorg/chromium/net/UploadDataProvider;->close()V

    return-void
.end method

.method public getLength()J
    .locals 2

    .line 27
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUploadDataProviderWrapper;->mBackend:Lorg/chromium/net/UploadDataProvider;

    invoke-virtual {p0}, Lorg/chromium/net/UploadDataProvider;->getLength()J

    move-result-wide v0

    return-wide v0
.end method

.method public read(Landroid/net/http/UploadDataSink;Ljava/nio/ByteBuffer;)V
    .locals 1

    .line 33
    new-instance v0, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;

    invoke-direct {v0, p1}, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;-><init>(Landroid/net/http/UploadDataSink;)V

    .line 34
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUploadDataProviderWrapper;->mBackend:Lorg/chromium/net/UploadDataProvider;

    invoke-virtual {p0, v0, p2}, Lorg/chromium/net/UploadDataProvider;->read(Lorg/chromium/net/UploadDataSink;Ljava/nio/ByteBuffer;)V

    return-void
.end method

.method public rewind(Landroid/net/http/UploadDataSink;)V
    .locals 1

    .line 39
    new-instance v0, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;

    invoke-direct {v0, p1}, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;-><init>(Landroid/net/http/UploadDataSink;)V

    .line 40
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUploadDataProviderWrapper;->mBackend:Lorg/chromium/net/UploadDataProvider;

    invoke-virtual {p0, v0}, Lorg/chromium/net/UploadDataProvider;->rewind(Lorg/chromium/net/UploadDataSink;)V

    return-void
.end method
