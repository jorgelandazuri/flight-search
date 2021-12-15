
package com.jalch.flightsearch.model.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Symbol")
    @Expose
    private String symbol;
    @SerializedName("ThousandsSeparator")
    @Expose
    private String thousandsSeparator;
    @SerializedName("DecimalSeparator")
    @Expose
    private String decimalSeparator;
    @SerializedName("SymbolOnLeft")
    @Expose
    private boolean symbolOnLeft;
    @SerializedName("SpaceBetweenAmountAndSymbol")
    @Expose
    private boolean spaceBetweenAmountAndSymbol;
    @SerializedName("RoundingCoefficient")
    @Expose
    private long roundingCoefficient;
    @SerializedName("DecimalDigits")
    @Expose
    private long decimalDigits;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getThousandsSeparator() {
        return thousandsSeparator;
    }

    public void setThousandsSeparator(String thousandsSeparator) {
        this.thousandsSeparator = thousandsSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public boolean isSymbolOnLeft() {
        return symbolOnLeft;
    }

    public void setSymbolOnLeft(boolean symbolOnLeft) {
        this.symbolOnLeft = symbolOnLeft;
    }

    public boolean isSpaceBetweenAmountAndSymbol() {
        return spaceBetweenAmountAndSymbol;
    }

    public void setSpaceBetweenAmountAndSymbol(boolean spaceBetweenAmountAndSymbol) {
        this.spaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
    }

    public long getRoundingCoefficient() {
        return roundingCoefficient;
    }

    public void setRoundingCoefficient(long roundingCoefficient) {
        this.roundingCoefficient = roundingCoefficient;
    }

    public long getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(long decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

}
