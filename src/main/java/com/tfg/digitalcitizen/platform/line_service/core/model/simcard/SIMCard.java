package com.tfg.digitalcitizen.platform.line_service.core.model.simcard;

public final class SIMCard {

    private final ICCIDNumber iccid; // Integrated Circuit Card Identifier
    private final SIMType type; // ESIM or Physical SIM, DUAL SIM
    private final SIMPin pin;
    private final SIMPuk puk;
    private final Operator operator; // Mobile network operator, orange, vodafone, movistar, etc.

    private SIMCard(String iccid, String type, String pin, String puk, String operator) {
        this.iccid = ICCIDNumber.fromPrimitive(iccid);
        this.type = SIMType.fromPrimitive(type);
        this.pin = SIMPin.fromPrimitive(pin);
        this.puk = SIMPuk.fromPrimitive(puk);
        this.operator = Operator.fromPrimitive(operator);
    }

    public static SIMCard fromPrimitives(String iccid, String type, String pin, String puk, String operator) {
        return new SIMCard(iccid, type, pin, puk, operator);
    }

    public String iccid() { return iccid.toPrimitive(); }
    public String type() { return type.toPrimitive(); }
    public String pin() { return pin.toPrimitive(); }
    public String puk() { return puk.toPrimitive(); }
    public String operator() { return operator.toPrimitive(); }
}

