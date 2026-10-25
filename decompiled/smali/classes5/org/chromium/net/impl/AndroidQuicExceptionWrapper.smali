.class Lorg/chromium/net/impl/AndroidQuicExceptionWrapper;
.super Lorg/chromium/net/QuicException;
.source "SourceFile"


# instance fields
.field private final mBackend:Lorg/chromium/net/impl/AndroidNetworkExceptionWrapper;


# direct methods
.method public constructor <init>(Landroid/net/http/QuicException;)V
    .locals 2

    .line 19
    invoke-virtual {p1}, Landroid/net/http/QuicException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0, p1}, Lorg/chromium/net/QuicException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 20
    new-instance v0, Lorg/chromium/net/impl/AndroidNetworkExceptionWrapper;

    const/4 v1, 0x1

    invoke-direct {v0, p1, v1}, Lorg/chromium/net/impl/AndroidNetworkExceptionWrapper;-><init>(Landroid/net/http/NetworkException;Z)V

    iput-object v0, p0, Lorg/chromium/net/impl/AndroidQuicExceptionWrapper;->mBackend:Lorg/chromium/net/impl/AndroidNetworkExceptionWrapper;

    return-void
.end method
