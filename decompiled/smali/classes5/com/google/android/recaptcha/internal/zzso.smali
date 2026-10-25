.class public final Lcom/google/android/recaptcha/internal/zzso;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzso;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzso;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzso;

.field public static final zzd:Lcom/google/android/recaptcha/internal/zzso;

.field public static final zze:Lcom/google/android/recaptcha/internal/zzso;

.field public static final zzf:Lcom/google/android/recaptcha/internal/zzso;


# instance fields
.field private final zzg:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzso;

    const-string v1, "UNKNOWN_PREFIX"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzso;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzso;->zza:Lcom/google/android/recaptcha/internal/zzso;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzso;

    const-string v1, "TINK"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzso;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzso;

    const-string v1, "LEGACY"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzso;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzso;

    const-string v1, "RAW"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzso;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzso;

    const-string v1, "CRUNCHY"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzso;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzso;

    const-string v1, "WITH_ID_REQUIREMENT"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzso;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzso;->zzf:Lcom/google/android/recaptcha/internal/zzso;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzso;->zzg:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzso;->zzg:Ljava/lang/String;

    return-object p0
.end method
