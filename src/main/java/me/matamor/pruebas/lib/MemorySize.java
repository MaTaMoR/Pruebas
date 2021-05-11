package me.matamor.pruebas.lib;

public enum MemorySize {

    BIT(MemorySize.BIT_SCALE),
    BYTE(MemorySize.BYTE_SCALE),
    KILO_BYTE(MemorySize.KILO_BYTE_SCALE),
    MEGA_BYTE(MemorySize.MEGA_BYTE_SCALE),
    GIGA_BYTE(MemorySize.GIGA_BYTE_SCALE);

    private final long scale;

    private MemorySize(long scale) {
        this.scale = scale;
    }

    private static final long BIT_SCALE = 1;
    private static final long BYTE_SCALE = BIT_SCALE * 8;
    private static final long KILO_BYTE_SCALE = BYTE_SCALE * 1024;
    private static final long MEGA_BYTE_SCALE = KILO_BYTE_SCALE * 1024;
    private static final long GIGA_BYTE_SCALE = MEGA_BYTE_SCALE * 1024;

    public long toBits(long value) {
        return safeMulti(value, this.scale);
    }

    public long toBytes(long value) {
        return toBits(value) / BIT_SCALE;
    }

    public long toKiloBytes(long value) {
        return toBits(value) / KILO_BYTE_SCALE;
    }

    public long toMegaBytes(long value) {
        return toBits(value) / MEGA_BYTE_SCALE;
    }

    public long toGigaBytes(long value) {
        return toBits(value) / GIGA_BYTE_SCALE;
    }

    public long convert(long value, MemorySize memorySize) {
        return memorySize.toBits(value) / this.scale;
    }

    private static long safeMulti(long value, long multiplicador) {
        long limite = Long.MAX_VALUE / multiplicador;

        if (value > limite) {
            return Long.MAX_VALUE;
        } else if (value < -limite) {
            return Long.MIN_VALUE;
        }

        return value * multiplicador;
    }
}
