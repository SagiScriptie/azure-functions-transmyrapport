package org.sogeti.functions.exportTransaction.enums;

public enum VATRate {
    VAT21(21),
    VAT9(9),
    VAT0(0);

    private final int value;

    VATRate(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
