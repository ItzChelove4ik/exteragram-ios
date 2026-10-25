.class public final Lcom/google/android/recaptcha/internal/zzih;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzii;


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzih;

.field private static final zzb:Ljava/lang/String;

.field private static final zzc:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzih;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzih;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzih;->zza:Lcom/google/android/recaptcha/internal/zzih;

    const-string v0, "https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt"

    sput-object v0, Lcom/google/android/recaptcha/internal/zzih;->zzb:Ljava/lang/String;

    const-string v0, "https://www.recaptcha.net/recaptcha/api3"

    sput-object v0, Lcom/google/android/recaptcha/internal/zzih;->zzc:Ljava/lang/String;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzig;->zza:Lcom/google/android/recaptcha/internal/zzig;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzih;->zzb:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzih;->zzc:Ljava/lang/String;

    return-object p0
.end method
