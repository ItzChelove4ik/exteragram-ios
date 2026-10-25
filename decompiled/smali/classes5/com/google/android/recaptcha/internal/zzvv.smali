.class final Lcom/google/android/recaptcha/internal/zzvv;
.super Ljava/lang/ThreadLocal;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/ThreadLocal;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic initialValue()Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvw;->zzb()Ljava/security/SecureRandom;

    move-result-object p0

    return-object p0
.end method
