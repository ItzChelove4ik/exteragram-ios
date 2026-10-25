.class public final synthetic Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/chromium/net/impl/CronetExceptionTranslationUtils$CronetWork;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;

.field public final synthetic f$1:Landroid/net/http/UrlResponseInfo;

.field public final synthetic f$2:Ljava/nio/ByteBuffer;


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;->f$0:Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;

    iput-object p2, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;->f$1:Landroid/net/http/UrlResponseInfo;

    iput-object p3, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;->f$2:Ljava/nio/ByteBuffer;

    return-void
.end method


# virtual methods
.method public final run()Ljava/lang/Object;
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;->f$0:Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;

    iget-object v1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;->f$1:Landroid/net/http/UrlResponseInfo;

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;->f$2:Ljava/nio/ByteBuffer;

    invoke-static {v0, v1, p0}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->$r8$lambda$0YSUpRBhWItOzjE5XTPKTjltwwU(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
