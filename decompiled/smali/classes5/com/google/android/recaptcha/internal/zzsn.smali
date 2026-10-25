.class public final Lcom/google/android/recaptcha/internal/zzsn;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzsn;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzsn;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzsn;

.field public static final zzd:Lcom/google/android/recaptcha/internal/zzsn;

.field public static final zze:Lcom/google/android/recaptcha/internal/zzsn;


# instance fields
.field private final zzf:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsn;

    const-string v1, "UNKNOWN_KEYMATERIAL"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsn;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zza:Lcom/google/android/recaptcha/internal/zzsn;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsn;

    const-string v1, "SYMMETRIC"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsn;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzb:Lcom/google/android/recaptcha/internal/zzsn;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsn;

    const-string v1, "ASYMMETRIC_PRIVATE"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsn;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsn;

    const-string v1, "ASYMMETRIC_PUBLIC"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsn;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzd:Lcom/google/android/recaptcha/internal/zzsn;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsn;

    const-string v1, "REMOTE"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsn;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zze:Lcom/google/android/recaptcha/internal/zzsn;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzsn;->zzf:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsn;->zzf:Ljava/lang/String;

    return-object p0
.end method
