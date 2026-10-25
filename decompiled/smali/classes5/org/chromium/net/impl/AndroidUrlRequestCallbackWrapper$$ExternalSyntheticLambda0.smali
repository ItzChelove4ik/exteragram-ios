.class public final synthetic Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/chromium/net/impl/CronetExceptionTranslationUtils$CronetWork;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;

.field public final synthetic f$1:Landroid/net/http/UrlResponseInfo;


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;->f$0:Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;

    iput-object p2, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;->f$1:Landroid/net/http/UrlResponseInfo;

    return-void
.end method


# virtual methods
.method public final run()Ljava/lang/Object;
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;->f$0:Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;->f$1:Landroid/net/http/UrlResponseInfo;

    invoke-static {v0, p0}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->$r8$lambda$g4UEVjfEGVPQs1LW7r7pKONVtkI(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
