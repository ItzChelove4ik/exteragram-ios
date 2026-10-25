.class final Lcom/google/android/recaptcha/internal/zzpm;
.super Lcom/google/android/recaptcha/internal/zzqe;
.source "SourceFile"


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zzpm;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzpm;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzpm;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzpm;->zza:Lcom/google/android/recaptcha/internal/zzpm;

    return-void
.end method

.method private constructor <init>()V
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzqq;->zza:Lcom/google/android/recaptcha/internal/zzpw;

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-direct {p0, v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzqe;-><init>(Lcom/google/android/recaptcha/internal/zzpw;ILjava/util/Comparator;)V

    return-void
.end method


# virtual methods
.method public final synthetic zza()Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpz;->map:Lcom/google/android/recaptcha/internal/zzpw;

    return-object p0
.end method
