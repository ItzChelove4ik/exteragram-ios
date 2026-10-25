.class Lorg/chromium/net/impl/AndroidHttpExceptionWrapper;
.super Lorg/chromium/net/CronetException;
.source "SourceFile"


# direct methods
.method public constructor <init>(Landroid/net/http/HttpException;)V
    .locals 1

    .line 19
    invoke-virtual {p1}, Landroid/net/http/HttpException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0, p1}, Lorg/chromium/net/CronetException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method
