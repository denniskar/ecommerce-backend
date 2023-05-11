package com.ecommerce.agroproducts.utils;
public enum UserStatus {
    Active("Active"),
    Deactivated("Deactivated"),
    SUSPENDED("SUSPENDED");

    private final String label;

    private UserStatus(String label) {
        this.label = label;
    }

    public static UserStatus getValue(String label) {
        UserStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserStatus b = var1[var3];
            if (b.label.equalsIgnoreCase(label)) {
                return b;
            }
        }

        return null;
    }

    public String getLabel() {
        return this.label;
    }
}
