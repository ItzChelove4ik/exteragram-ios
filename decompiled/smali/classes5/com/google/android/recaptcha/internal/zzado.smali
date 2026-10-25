.class public final enum Lcom/google/android/recaptcha/internal/zzado;
.super Ljava/lang/Enum;
.source "SourceFile"


# static fields
.field public static final enum zza:Lcom/google/android/recaptcha/internal/zzado;

.field public static final enum zzb:Lcom/google/android/recaptcha/internal/zzado;

.field public static final enum zzc:Lcom/google/android/recaptcha/internal/zzado;

.field public static final enum zzd:Lcom/google/android/recaptcha/internal/zzado;

.field public static final enum zze:Lcom/google/android/recaptcha/internal/zzado;

.field private static final synthetic zzf:[Lcom/google/android/recaptcha/internal/zzado;


# direct methods
.method static constructor <clinit>()V
    .locals 7

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzado;

    const-string v1, "SHA1"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzado;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzado;->zza:Lcom/google/android/recaptcha/internal/zzado;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzado;

    const-string v2, "SHA224"

    const/4 v3, 0x1

    .line 2
    invoke-direct {v1, v2, v3}, Lcom/google/android/recaptcha/internal/zzado;-><init>(Ljava/lang/String;I)V

    sput-object v1, Lcom/google/android/recaptcha/internal/zzado;->zzb:Lcom/google/android/recaptcha/internal/zzado;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzado;

    const-string v3, "SHA256"

    const/4 v4, 0x2

    .line 3
    invoke-direct {v2, v3, v4}, Lcom/google/android/recaptcha/internal/zzado;-><init>(Ljava/lang/String;I)V

    sput-object v2, Lcom/google/android/recaptcha/internal/zzado;->zzc:Lcom/google/android/recaptcha/internal/zzado;

    new-instance v3, Lcom/google/android/recaptcha/internal/zzado;

    const-string v4, "SHA384"

    const/4 v5, 0x3

    .line 4
    invoke-direct {v3, v4, v5}, Lcom/google/android/recaptcha/internal/zzado;-><init>(Ljava/lang/String;I)V

    sput-object v3, Lcom/google/android/recaptcha/internal/zzado;->zzd:Lcom/google/android/recaptcha/internal/zzado;

    new-instance v4, Lcom/google/android/recaptcha/internal/zzado;

    const-string v5, "SHA512"

    const/4 v6, 0x4

    .line 5
    invoke-direct {v4, v5, v6}, Lcom/google/android/recaptcha/internal/zzado;-><init>(Ljava/lang/String;I)V

    sput-object v4, Lcom/google/android/recaptcha/internal/zzado;->zze:Lcom/google/android/recaptcha/internal/zzado;

    filled-new-array {v0, v1, v2, v3, v4}, [Lcom/google/android/recaptcha/internal/zzado;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzado;->zzf:[Lcom/google/android/recaptcha/internal/zzado;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static values()[Lcom/google/android/recaptcha/internal/zzado;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzado;->zzf:[Lcom/google/android/recaptcha/internal/zzado;

    invoke-virtual {v0}, [Lcom/google/android/recaptcha/internal/zzado;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/recaptcha/internal/zzado;

    return-object v0
.end method
