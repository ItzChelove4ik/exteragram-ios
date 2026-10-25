.class Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;
.super Lorg/chromium/net/UploadDataSink;
.source "SourceFile"


# instance fields
.field private final mBackend:Landroid/net/http/UploadDataSink;


# direct methods
.method public constructor <init>(Landroid/net/http/UploadDataSink;)V
    .locals 0

    .line 16
    invoke-direct {p0}, Lorg/chromium/net/UploadDataSink;-><init>()V

    .line 17
    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;->mBackend:Landroid/net/http/UploadDataSink;

    return-void
.end method


# virtual methods
.method public onReadSucceeded(Z)V
    .locals 0

    .line 22
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;->mBackend:Landroid/net/http/UploadDataSink;

    invoke-virtual {p0, p1}, Landroid/net/http/UploadDataSink;->onReadSucceeded(Z)V

    return-void
.end method

.method public onRewindSucceeded()V
    .locals 0

    .line 32
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUploadDataSinkWrapper;->mBackend:Landroid/net/http/UploadDataSink;

    invoke-virtual {p0}, Landroid/net/http/UploadDataSink;->onRewindSucceeded()V

    return-void
.end method
