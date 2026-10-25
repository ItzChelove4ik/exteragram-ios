.class final synthetic Lcom/google/android/recaptcha/internal/zzva;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzwa;


# static fields
.field static final synthetic zza:Lcom/google/android/recaptcha/internal/zzva;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzva;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzva;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzva;->zza:Lcom/google/android/recaptcha/internal/zzva;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic zza()Ljava/lang/Object;
    .locals 4

    .line 1
    new-instance p0, Lcom/google/android/recaptcha/internal/zzvb;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzvb;-><init>()V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzuy;->zza:Lcom/google/android/recaptcha/internal/zzuy;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzuc;

    const-class v2, Lcom/google/android/recaptcha/internal/zzui;

    invoke-direct {v1, v2, v0}, Lcom/google/android/recaptcha/internal/zzuc;-><init>(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzud;)V

    .line 2
    invoke-virtual {p0, v1}, Lcom/google/android/recaptcha/internal/zzvb;->zzb(Lcom/google/android/recaptcha/internal/zzue;)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzuz;->zza:Lcom/google/android/recaptcha/internal/zzuz;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzvg;

    const-class v2, Lcom/google/android/recaptcha/internal/zzuj;

    const-class v3, Lcom/google/android/recaptcha/internal/zzsr;

    invoke-direct {v1, v2, v3, v0}, Lcom/google/android/recaptcha/internal/zzvg;-><init>(Ljava/lang/Class;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzvh;)V

    .line 3
    invoke-virtual {p0, v1}, Lcom/google/android/recaptcha/internal/zzvb;->zzd(Lcom/google/android/recaptcha/internal/zzvi;)V

    return-object p0
.end method
