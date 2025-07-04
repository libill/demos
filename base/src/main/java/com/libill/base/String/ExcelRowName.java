package com.libill.base.String;

public class ExcelRowName {
    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            int a0 = (columnNumber - 1) % 26 + 1;
            sb.append(getResult(a0));
            columnNumber = (columnNumber - a0) / 26;
        }
        return sb.reverse().toString();
    }

    private char getResult(int b) {
        return ((char) (b - 1 + 'A'));
    }
}
